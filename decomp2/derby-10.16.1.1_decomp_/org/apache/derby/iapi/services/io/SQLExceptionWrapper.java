package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.sql.SQLException;

class SQLExceptionWrapper extends SQLException {
   private Exception myException;

   SQLExceptionWrapper(Exception var1) {
      this.myException = var1;
   }

   void handleMe() throws IOException, ClassNotFoundException {
      if (this.myException instanceof IOException) {
         throw (IOException)this.myException;
      } else if (this.myException instanceof ClassNotFoundException) {
         throw (ClassNotFoundException)this.myException;
      }
   }

   void handleMeToo() throws IOException {
      if (this.myException instanceof IOException) {
         throw (IOException)this.myException;
      }
   }
}
