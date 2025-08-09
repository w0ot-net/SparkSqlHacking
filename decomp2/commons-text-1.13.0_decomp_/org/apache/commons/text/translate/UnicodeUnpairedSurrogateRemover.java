package org.apache.commons.text.translate;

import java.io.IOException;
import java.io.Writer;

public class UnicodeUnpairedSurrogateRemover extends CodePointTranslator {
   public boolean translate(int codePoint, Writer writer) throws IOException {
      return codePoint >= 55296 && codePoint <= 57343;
   }
}
