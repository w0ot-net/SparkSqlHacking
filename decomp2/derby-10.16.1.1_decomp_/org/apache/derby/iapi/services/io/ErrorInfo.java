package org.apache.derby.iapi.services.io;

interface ErrorInfo {
   String getErrorInfo();

   Exception getNestedException();
}
