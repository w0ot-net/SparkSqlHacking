package jodd.datetime.format;

import jodd.datetime.DateTimeStamp;
import jodd.datetime.JDateTime;
import jodd.util.CharUtil;

public abstract class AbstractFormatter implements JdtFormatter {
   protected char[][] patterns;
   protected char escapeChar = '\'';

   protected void preparePatterns(String[] spat) {
      this.patterns = new char[spat.length][];

      for(int i = 0; i < spat.length; ++i) {
         this.patterns[i] = spat[i].toCharArray();
      }

   }

   protected int findPattern(char[] format, int i) {
      int frmtc_len = format.length;
      int lastn = -1;
      int maxLen = 0;

      for(int n = 0; n < this.patterns.length; ++n) {
         char[] curr = this.patterns[n];
         if (i <= frmtc_len - curr.length) {
            boolean match = true;

            for(int delta = 0; delta < curr.length; ++delta) {
               if (curr[delta] != format[i + delta]) {
                  match = false;
                  break;
               }
            }

            if (match && this.patterns[n].length > maxLen) {
               lastn = n;
               maxLen = this.patterns[n].length;
            }
         }
      }

      return lastn;
   }

   protected abstract String convertPattern(int var1, JDateTime var2);

   public String convert(JDateTime jdt, String format) {
      char[] fmtc = format.toCharArray();
      int fmtc_len = fmtc.length;
      StringBuilder result = new StringBuilder(fmtc_len);
      int i = 0;

      while(i < fmtc_len) {
         if (fmtc[i] == this.escapeChar) {
            int end;
            for(end = i + 1; end < fmtc_len; ++end) {
               if (fmtc[end] == this.escapeChar) {
                  if (end + 1 < fmtc_len) {
                     ++end;
                     if (fmtc[end] != this.escapeChar) {
                        break;
                     }

                     result.append(this.escapeChar);
                  }
               } else {
                  result.append(fmtc[end]);
               }
            }

            i = end;
         } else {
            int n = this.findPattern(fmtc, i);
            if (n != -1) {
               result.append(this.convertPattern(n, jdt));
               i += this.patterns[n].length;
            } else {
               result.append(fmtc[i]);
               ++i;
            }
         }
      }

      return result.toString();
   }

   protected abstract void parseValue(int var1, String var2, DateTimeStamp var3);

   public DateTimeStamp parse(String value, String format) {
      char[] valueChars = value.toCharArray();
      char[] formatChars = format.toCharArray();
      int i = 0;
      int j = 0;
      int valueLen = valueChars.length;
      int formatLen = formatChars.length;
      boolean useSeparators = true;
      if (valueLen == formatLen) {
         useSeparators = false;

         for(char valueChar : valueChars) {
            if (!CharUtil.isDigit(valueChar)) {
               useSeparators = true;
               break;
            }
         }
      }

      DateTimeStamp time = new DateTimeStamp();
      StringBuilder sb = new StringBuilder();

      do {
         int n = this.findPattern(formatChars, i);
         if (n == -1) {
            if (!useSeparators) {
               throw new IllegalArgumentException("Invalid value: " + value);
            }

            if (formatChars[i] == valueChars[j]) {
               ++j;
            }

            ++i;
         } else {
            int patternLen = this.patterns[n].length;
            i += patternLen;
            sb.setLength(0);
            if (!useSeparators) {
               for(int k = 0; k < patternLen; ++k) {
                  sb.append(valueChars[j++]);
               }
            } else {
               char next = '\uffff';
               if (i < formatLen) {
                  next = formatChars[i];
               }

               for(; j < valueLen && valueChars[j] != next; ++j) {
                  char scj = valueChars[j];
                  if (scj != ' ' && scj != '\t') {
                     sb.append(valueChars[j]);
                  }
               }
            }

            this.parseValue(n, sb.toString(), time);
         }
      } while(i != formatLen && j != valueLen);

      return time;
   }

   protected String print2(int value) {
      if (value < 0) {
         throw new IllegalArgumentException("Value must be positive: " + value);
      } else if (value < 10) {
         return '0' + Integer.toString(value);
      } else if (value < 100) {
         return Integer.toString(value);
      } else {
         throw new IllegalArgumentException("Value too big: " + value);
      }
   }

   protected String print3(int value) {
      if (value < 0) {
         throw new IllegalArgumentException("Value must be positive: " + value);
      } else if (value < 10) {
         return "00" + Integer.toString(value);
      } else if (value < 100) {
         return '0' + Integer.toString(value);
      } else if (value < 1000) {
         return Integer.toString(value);
      } else {
         throw new IllegalArgumentException("Value too big: " + value);
      }
   }

   protected String printPad4(int value) {
      char[] result = new char[4];
      int count = 0;
      if (value < 0) {
         result[count++] = '-';
         value = -value;
      }

      String str = Integer.toString(value);
      if (value < 10) {
         result[count++] = '0';
         result[count++] = '0';
         result[count++] = '0';
         result[count++] = str.charAt(0);
      } else if (value < 100) {
         result[count++] = '0';
         result[count++] = '0';
         result[count++] = str.charAt(0);
         result[count++] = str.charAt(1);
      } else {
         if (value >= 1000) {
            if (count > 0) {
               return '-' + str;
            }

            return str;
         }

         result[count++] = '0';
         result[count++] = str.charAt(0);
         result[count++] = str.charAt(1);
         result[count++] = str.charAt(2);
      }

      return new String(result, 0, count);
   }
}
