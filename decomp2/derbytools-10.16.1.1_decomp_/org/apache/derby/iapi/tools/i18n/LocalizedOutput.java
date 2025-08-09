package org.apache.derby.iapi.tools.i18n;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class LocalizedOutput extends PrintWriter {
   private OutputStream out;

   public LocalizedOutput(OutputStream var1) {
      super(new OutputStreamWriter(var1), true);
      this.out = var1;
   }

   LocalizedOutput(OutputStream var1, String var2) throws UnsupportedEncodingException {
      super(new OutputStreamWriter(var1, var2), true);
      this.out = var1;
   }

   public boolean isStandardOutput() {
      return this.out == System.out;
   }

   public void close() {
      if (!this.isStandardOutput()) {
         super.close();
      }

   }
}
