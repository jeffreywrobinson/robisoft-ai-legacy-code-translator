# Project Title

An experimental Java/JavaFx application using OpenAI APIs to aid in converting Legacy Software (Assembly,COBOL,PL1, etc.)
to Java.


Requirements
-------------
Before running, a valid OPENAI access token must be acquired and set in the environment running
the application.  Example:

OPENAI_API_KEY=(access token)

Never commit the API key or place it in source code. The application reads it
from the process environment through the OpenAI SDK.

The model can be overridden with `OPENAI_MODEL`. Source files are interpreted as
UTF-8 and limited to 1 MB to prevent accidental excessive memory and API usage.
