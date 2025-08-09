package org.apache.derby.iapi.tools.i18n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class LocalizedInput extends BufferedReader {
   private InputStream in;

   public LocalizedInput(InputStream var1) {
      super(new InputStreamReader(var1));
      this.in = var1;
   }

   LocalizedInput(InputStream var1, String var2) throws UnsupportedEncodingException {
      super(new InputStreamReader(var1, var2));
      this.in = var1;
   }

   public boolean isStandardInput() {
      return this.in == System.in;
   }

   public void close() throws IOException {
      if (!this.isStandardInput()) {
         super.close();
      }

   }
}
