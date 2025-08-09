package com.univocity.parsers.common.input;

public abstract class LineSeparatorDetector implements InputAnalysisProcess {
   public void execute(char[] characters, int length) {
      char separator1 = 0;
      char separator2 = 0;

      for(int c = 0; c < length; ++c) {
         char ch = characters[c];
         if (ch != '\n' && ch != '\r') {
            if (separator1 != 0) {
               break;
            }
         } else {
            if (separator1 != 0) {
               separator2 = ch;
               break;
            }

            separator1 = ch;
         }
      }

      char lineSeparator1 = separator1;
      char lineSeparator2 = separator2;
      if (separator1 != 0) {
         if (separator1 == '\n') {
            lineSeparator1 = '\n';
            lineSeparator2 = 0;
         } else {
            lineSeparator1 = '\r';
            if (separator2 == '\n') {
               lineSeparator2 = '\n';
            } else {
               lineSeparator2 = 0;
            }
         }
      }

      this.apply(lineSeparator1, lineSeparator2);
   }

   protected abstract void apply(char var1, char var2);
}
