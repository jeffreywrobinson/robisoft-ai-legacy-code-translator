package com.robisoft.legacy.translator;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.robisoft.legacy.translator.helpers.Constants;

public final class ProcessAction {

  private static final int MAX_PROMPT_LENGTH = 16_384;
  private static final int MAX_INPUT_LENGTH = 1_000_000;
  
  
  /**
   * Generic method to send each line to the OpenAI API
   * 
   */
  public String executeAPI(String prompt, String inputStr) {
      validateInput(prompt, inputStr);
      String payload = prompt + "\n\n--- SOURCE CODE ---\n" + inputStr;
      OpenAIClient client = OpenAIOkHttpClient.fromEnv();

      ResponseCreateParams params = ResponseCreateParams.builder()
              .input(payload)
              .model(Constants.getModel())
              .build();

      Response response = client.responses().create(params);
    
      String text = response.output().stream()
    	        .flatMap(item -> item.message().stream())
    	        .flatMap(message -> message.content().stream())
    	        .flatMap(content -> content.outputText().stream())
    	        .map(com.openai.models.responses.ResponseOutputText::text)
    	        .collect(java.util.stream.Collectors.joining());

      if (text.isBlank()) {
          throw new IllegalStateException("The model returned an empty response.");
      }
      return text;
  }

  private static void validateInput(String prompt, String inputStr) {
      if (prompt == null || prompt.isBlank()) {
          throw new IllegalArgumentException("Prompt must not be blank.");
      }
      if (inputStr == null || inputStr.isBlank()) {
          throw new IllegalArgumentException("Source code must not be blank.");
      }
      if (prompt.length() > MAX_PROMPT_LENGTH) {
          throw new IllegalArgumentException("Prompt is too large.");
      }
      if (inputStr.length() > MAX_INPUT_LENGTH) {
          throw new IllegalArgumentException("Source code is too large.");
      }
  }
}
