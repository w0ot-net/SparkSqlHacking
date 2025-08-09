package org.apache.derby.iapi.services.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.apache.derby.iapi.services.loader.ClassFactory;

class ApplicationObjectInputStream extends ObjectInputStream implements ErrorObjectInput {
   protected ClassFactory cf;
   protected ObjectStreamClass initialClass;

   ApplicationObjectInputStream(InputStream var1, ClassFactory var2) throws IOException {
      super(var1);
      this.cf = var2;
   }

   protected Class resolveClass(ObjectStreamClass var1) throws IOException, ClassNotFoundException {
      if (this.initialClass == null) {
         this.initialClass = var1;
      }

      if (this.cf != null) {
         return this.cf.loadApplicationClass(var1);
      } else {
         throw new ClassNotFoundException(var1.getName());
      }
   }

   public String getErrorInfo() {
      if (this.initialClass == null) {
         return "";
      } else {
         String var10000 = this.initialClass.getName();
         return var10000 + " (serialVersionUID=" + this.initialClass.getSerialVersionUID() + ")";
      }
   }

   public Exception getNestedException() {
      return null;
   }
}
