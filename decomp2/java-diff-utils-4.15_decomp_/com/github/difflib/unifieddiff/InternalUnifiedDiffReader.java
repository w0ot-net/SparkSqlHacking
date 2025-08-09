package com.github.difflib.unifieddiff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

class InternalUnifiedDiffReader extends BufferedReader {
   private String lastLine;

   public InternalUnifiedDiffReader(Reader reader) {
      super(reader);
   }

   public String readLine() throws IOException {
      this.lastLine = super.readLine();
      return this.lastLine();
   }

   String lastLine() {
      return this.lastLine;
   }
}
