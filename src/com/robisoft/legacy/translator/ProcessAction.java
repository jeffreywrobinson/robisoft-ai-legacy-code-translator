package com.robisoft.legacy.translator;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.robisoft.legacy.translator.helpers.Constants;


public class ProcessAction {

	public String previousURL=null, currentURL=null;
	private boolean AbortFlag;
	private boolean active=false;
	private boolean pause=false;
  
  
  /**
   * Generic method to send each line to the OpenAI API
   * 
   */
  public String executeAPI(String prompt,String inputStr) {	 
      StringBuilder payload = new StringBuilder(prompt + ":" + inputStr);
      OpenAIClient client = OpenAIOkHttpClient.fromEnv();

      ResponseCreateParams params = ResponseCreateParams.builder()
              .input(payload.toString())
              .model(Constants.getModel())
              .build();

      Response response = client.responses().create(params);
    
      String text = response.output().stream()
    	        .flatMap(item -> item.message().stream())
    	        .flatMap(message -> message.content().stream())
    	        .flatMap(content -> content.outputText().stream())
    	        .map(com.openai.models.responses.ResponseOutputText::text)
    	        .collect(java.util.stream.Collectors.joining());

    	System.out.println(text);
    	return text;
  }	  

 
   //Getters and Setters
  //-----------------------------------------------------
  public boolean isAbortFlag() {
	  return(AbortFlag);
  }
  
  public void setAbortFlag(boolean flag) {
	  AbortFlag = flag;
  }
   public boolean isActive() {
	  return(active);
  }
  
  public void setActive(boolean flag) {
	  active = flag;
  }
  public boolean isPause() {
	  return(pause);
  }
  
  public void setPause(boolean flag) {
	  pause = flag;
  }
  
}
