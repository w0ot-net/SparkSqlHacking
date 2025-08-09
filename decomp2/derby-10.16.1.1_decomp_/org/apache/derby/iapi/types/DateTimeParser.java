package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

class DateTimeParser {
   private String str;
   private int len;
   private int fieldStart;
   private char currentSeparator;

   DateTimeParser(String var1) {
      this.str = var1;
      this.len = var1.length();
   }

   int parseInt(int var1, boolean var2, char[] var3, boolean var4) throws StandardException {
      int var5 = 0;

      int var7;
      for(var7 = 0; this.fieldStart < this.len; ++this.fieldStart) {
         char var6 = this.str.charAt(this.fieldStart);
         if (!Character.isDigit(var6)) {
            break;
         }

         if (var7 >= var1) {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }

         ++var7;
         var5 = var5 * 10 + Character.digit(var6, 10);
      }

      if (var2) {
         if (var7 == 0 && !var4) {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }
      } else if (var7 != var1) {
         throw StandardException.newException("22007.S.181", new Object[0]);
      }

      this.updateCurrentSeparator();
      if (var3 == null) {
         if (this.fieldStart < this.len) {
            ++this.fieldStart;
         }
      } else {
         int var8;
         for(var8 = 0; var8 < var3.length; ++var8) {
            if (var3[var8] != 0) {
               if (this.currentSeparator == var3[var8]) {
                  ++this.fieldStart;
                  break;
               }
            } else {
               int var9;
               for(var9 = this.fieldStart; var9 < this.len && this.str.charAt(var9) == ' '; ++var9) {
               }

               if (var9 == this.len) {
                  this.fieldStart = var9;
                  break;
               }
            }
         }

         if (var8 >= var3.length) {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }
      }

      if (var4) {
         for(int var10 = var7; var10 < var1; ++var10) {
            var5 *= 10;
         }
      }

      return var5;
   }

   int parseChoice(String[] var1) throws StandardException {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         String var3 = var1[var2];
         int var4 = var3.length();
         if (this.fieldStart + var4 <= this.len) {
            int var5;
            for(var5 = 0; var5 < var4 && var3.charAt(var5) == this.str.charAt(this.fieldStart + var5); ++var5) {
            }

            if (var5 == var4) {
               this.fieldStart += var4;
               this.updateCurrentSeparator();
               return var2;
            }
         }
      }

      throw StandardException.newException("22007.S.181", new Object[0]);
   }

   private void updateCurrentSeparator() {
      if (this.fieldStart >= this.len) {
         this.currentSeparator = 0;
      } else {
         this.currentSeparator = this.str.charAt(this.fieldStart);
         if (this.currentSeparator == ' ') {
            for(int var1 = this.fieldStart + 1; var1 < this.len; ++var1) {
               if (this.str.charAt(var1) != ' ') {
                  return;
               }
            }

            this.currentSeparator = 0;
            this.fieldStart = this.len;
         }
      }

   }

   void checkEnd() throws StandardException {
      while(this.fieldStart < this.len) {
         if (this.str.charAt(this.fieldStart) != ' ') {
            throw StandardException.newException("22007.S.181", new Object[0]);
         }

         ++this.fieldStart;
      }

      this.currentSeparator = 0;
   }

   char nextSeparator() {
      for(int var1 = this.fieldStart + 1; var1 < this.len; ++var1) {
         char var2 = this.str.charAt(var1);
         if (!Character.isLetterOrDigit(var2)) {
            return var2;
         }
      }

      return '\u0000';
   }

   char getCurrentSeparator() {
      return this.currentSeparator;
   }
}
