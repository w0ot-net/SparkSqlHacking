package org.codehaus.commons.compiler.util;

public abstract class LineAndColumnTracker {
   public static final int DEFAULT_TAB_WIDTH = 8;

   public abstract void setTabWidth(int var1);

   public abstract void consume(char var1);

   public abstract int getLineNumber();

   public abstract void setLineNumber(int var1);

   public abstract int getColumnNumber();

   public abstract void setColumnNumber(int var1);

   public abstract void reset();

   public static LineAndColumnTracker create() {
      return new LineAndColumnTracker() {
         private int tabWidth = 8;
         private int line = 1;
         private int column = 1;
         private boolean crPending;

         public void consume(char c) {
            if (this.crPending) {
               this.crPending = false;
               if (c == '\n') {
                  return;
               }
            }

            switch (c) {
               case '\t':
                  this.column = this.column - (this.column - 1) % this.tabWidth + this.tabWidth;
                  break;
               case '\n':
               case '\u0085':
               case '\u2028':
               case '\u2029':
                  ++this.line;
                  this.column = 1;
                  break;
               case '\r':
                  this.crPending = true;
                  ++this.line;
                  this.column = 1;
                  break;
               default:
                  ++this.column;
            }

         }

         public int getLineNumber() {
            return this.line;
         }

         public void setLineNumber(int lineNumber) {
            this.line = lineNumber;
         }

         public int getColumnNumber() {
            return this.column;
         }

         public void setColumnNumber(int columnNumber) {
            this.column = columnNumber;
         }

         public void setTabWidth(int tabWidth) {
            this.tabWidth = tabWidth;
         }

         public void reset() {
            this.line = this.column = 1;
            this.crPending = false;
         }
      };
   }
}
