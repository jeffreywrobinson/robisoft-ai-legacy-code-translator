package com.robisoft.legacy.translator.helpers;

public class Constants {

	private static final String DEFAULT_MODEL = "gpt-5.5";

	//*ATTENTION: update this filename each season with current Year value ***
   //public static String ConfDB = "/Users/jeffreyrobinson/Documents/NFL/DB/ConfStats-2022.csv";
   
   public static String prompt1 = "Base64:\r\n"
   		+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCwgZXhhbWluZSB0aGUgZm9sbG93aW5nIHNvdXJjZSBjb2RlIGFuZCByZXR1cm4gb25seSBzdGF0aXN0aWNzCmFib3V0IHRoZSBwcm9ncmFtIHN1Y2ggYXMgOiAKMC4gU291cmNlIHByb2dyYW1taW5nIGxhbmd1YWdlOgoxLiBOdW1iZXIgb2Ygc291cmNlIGxpbmVzIDogPyAKMi4gTnVtYmVyIG9mIGRlY2lzaW9uIHN0YXRlbWVudHMKMy4gTnVtYmVyIG9mIGxvb3Bpbmcgc3RhdGVtZW50czogPyAKNC4gTnVtYmVyIG9mIGNsYXNzZWVzOiA/IAo1LiBOdW1iZXIgb2YgZGF0YSBzdHJ1Y3R1cmVzOiA/CjYuIE51bWJlciBvZiBEYXRhYmFzZSBJL08gcHJvY2Vzc2VzOiA/IAo3LiBOdW1iZXIgb2Ygc3lzdGVtIHJlbGF0ZWQgYWN0aW9uczogPw==";
   public static String prompt2 = "Base 64:\r\n"
   		+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCwgZXhhbWluZSB0aGUgZm9sbG93aW5nIHNvdXJjZSBjb2RlIGFuZCByZXR1cm4gc3RlcHMgdGhlIGFuYWx5c3QKd291bGQgc3VnZ2VzdCB0byByZS1kZXNpZ24gdGhpcyBjb2RlIHRvIGEgbW9kZXJuIHByb2dyYW1taW5nIGxhbmd1YWdlIGxpa2UgSmF2YSBvciBQeXRob24uIEluY2x1ZGUgCnN1Z2dlc3Rpb25zIG9uIG9iamVjdC1vcmllbnRlZCBhcHByb2FjaGVzIGFuZCB1c2luZyBtb2Rlcm4gZGF0YWJhc2UgcmVsYXRlZCBjaGFuZ2VzLiAK";
   public static String prompt3 = "Base64:\r\n"
   		+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCwgZXhhbWluZSB0aGUgZm9sbG93aW5nIHNvdXJjZSBjb2RlIGFuZCBjb252ZXJ0IGl0IHRvCmdlbmVyaWMgcHNldWRvLWNvZGUgdGhhdCBjYW4gbGF0ZXIgYmUgZGV2ZWxvcGVkIGluIGFub3RoZXIgcHJvZ3JhbW1pbmcgbGFuZ3VhZ2UuICAuIA==";
   public static String prompt4 = "Base64:\r\n"
	   	+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCwgZXhhbWluZSB0aGUgZm9sbG93aW5nIHNvdXJjZSBjb2RlIGFuZCBjcmVhdGUgYSAKdmlzdWFsIGdyYXBoaWNhbCBmbG93Y2hhcnQgZnJvbSBpdC4g";
   public static String prompt5 = "Base64:\r\n"
   		+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCBjb252ZXJ0aW5nIGxlZ2FjeSBjb2RlIHRvIEphdmEsIGV4YW1pbmUgdGhlIGZvbGxvd2luZyBzb3VyY2UgY29kZSBhbmQgcmV0dXJuIGEKdGFibGUgZGlzcGxheWluZyBTYWJyZVRhbGsgdmFyaWFibGVzIHdpdGggdGhlaXIgY29ycmVzcG9uZGluZyBKYXZhIG5hbWVzLiBVc2UgSmF2YSBiZXN0IHByYWN0aWNlcyB0byBnZW5lcmF0ZSB0aGUgCkphdmEgbmFtZXMuIAo=";
   public static String prompt6 = "Base 64: \r\n"
   		+ "QWN0aW5nIGFzIGEgbGVnYWN5IHByb2dyYW1tZXIvYW5hbHlzdCwgY29udmVydCB0aGUgbGVnYWN5IGNvZGUgdG8gSmF2YTsgcmVwbGFjZSBhbnkgb3V0ZGF0ZSBsZWdhY3kgCmNvZGluZyBhc3N1bXB0aW9ucyB3aXRoIG1vZGVybiBKYXZhIGNvZGUgYW5kIG1vZGVybiBEYXRhYmFzZSBjb2RpbmcgcmVwbGFjZW1lbnRzLiBPbmx5IHJldHVybiB0aGUKZmluYWwgY29kZS4gRG8gbm90IGluY2x1ZGUgZXhwbGFuYXRpb25zLCBjYXZlYXRzLCBvciBlbGFib3JhdGlvbnMuIAoK";
   
   
   public static final String[] models = new String[] {
		      "gpt-5.5",
		      "gpt-5.4",
		      "nova-pro-5",
		      "gemini-flash-latest"
		   	          
   };
   
   public static String getModel() {
	    String configuredModel = System.getenv("OPENAI_MODEL");

	    return configuredModel == null || configuredModel.isBlank()
	            ? DEFAULT_MODEL
	            : configuredModel;
	} 
  
}
