package org.apache.derby.iapi.services.io;

import java.io.ObjectInput;

public interface ErrorObjectInput extends ObjectInput, ErrorInfo {
   String getErrorInfo();

   Exception getNestedException();
}
