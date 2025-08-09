package jodd.format;

import java.math.BigInteger;

public class PrintfFormat {
   protected int width;
   protected int precision;
   protected StringBuilder pre;
   protected StringBuilder post;
   protected boolean leadingZeroes;
   protected boolean showPlus;
   protected boolean alternate;
   protected boolean showSpace;
   protected boolean leftAlign;
   protected boolean groupDigits;
   protected char fmt;
   protected boolean countSignInLen;
   private static final BigInteger bgInt = new BigInteger("9223372036854775808");

   public PrintfFormat(String s) {
      this.init(s, 0);
   }

   protected PrintfFormat() {
   }

   protected PrintfFormat reinit(String s) {
      if (this.pre == null) {
         this.init(s, 0);
      } else {
         this.init(s, this.pre.length());
      }

      return this;
   }

   protected void init(String s, int i) {
      this.width = 0;
      this.precision = -1;
      this.pre = i == 0 ? new StringBuilder() : new StringBuilder(s.substring(0, i));
      this.post = new StringBuilder();
      this.leadingZeroes = false;
      this.showPlus = false;
      this.alternate = false;
      this.showSpace = false;
      this.leftAlign = false;
      this.countSignInLen = true;
      this.fmt = ' ';
      int length = s.length();

      while(i < length) {
         char c = s.charAt(i);
         if (c != '%') {
            this.pre.append(c);
            ++i;
         } else {
            if (i >= length - 1) {
               throw new IllegalArgumentException("Format string can not end with '%'.");
            }

            if (s.charAt(i + 1) != '%') {
               ++i;

               int parseState;
               label86:
               while(true) {
                  if (i >= length) {
                     parseState = 5;
                     break;
                  }

                  c = s.charAt(i);
                  switch (c) {
                     case ' ':
                        this.showSpace = true;
                        break;
                     case '#':
                        this.alternate = true;
                        break;
                     case '+':
                        this.showPlus = true;
                        break;
                     case ',':
                        this.groupDigits = true;
                        break;
                     case '-':
                        this.leftAlign = true;
                        break;
                     case '0':
                        this.leadingZeroes = true;
                        break;
                     case '~':
                        this.countSignInLen = false;
                        break;
                     default:
                        parseState = 2;
                        break label86;
                  }

                  ++i;
               }

               while(parseState == 2) {
                  if (i >= length) {
                     parseState = 5;
                     break;
                  }

                  c = s.charAt(i);
                  if (c < '0' || c > '9') {
                     if (s.charAt(i) == '.') {
                        parseState = 3;
                        this.precision = 0;
                        ++i;
                     } else {
                        parseState = 4;
                     }
                     break;
                  }

                  this.width = this.width * 10 + s.charAt(i) - 48;
                  ++i;
               }

               while(parseState == 3) {
                  if (i >= length) {
                     parseState = 5;
                     break;
                  }

                  c = s.charAt(i);
                  if (c < '0' || c > '9') {
                     parseState = 4;
                     break;
                  }

                  this.precision = this.precision * 10 + s.charAt(i) - 48;
                  ++i;
               }

               if (parseState == 4 && i < length) {
                  this.fmt = s.charAt(i);
                  ++i;
               }

               if (i < length) {
                  this.post.append(s.substring(i, length));
               }

               return;
            }

            this.pre.append('%');
            i += 2;
         }
      }

      throw new IllegalArgumentException("Format string requires '%'.");
   }

   protected String expFormat(double d) {
      StringBuilder f = new StringBuilder();
      int e = 0;
      double dd = d;
      double factor = (double)1.0F;
      if (d != (double)0.0F) {
         while(dd > (double)10.0F) {
            ++e;
            factor /= (double)10.0F;
            dd /= (double)10.0F;
         }

         while(dd < (double)1.0F) {
            --e;
            factor *= (double)10.0F;
            dd *= (double)10.0F;
         }
      }

      if ((this.fmt == 'g' || this.fmt == 'G') && e >= -4 && e < this.precision) {
         return this.fixedFormat(d);
      } else {
         d *= factor;
         f.append(this.fixedFormat(d));
         if (this.fmt != 'e' && this.fmt != 'g') {
            f.append('E');
         } else {
            f.append('e');
         }

         StringBuilder p = new StringBuilder("000");
         if (e >= 0) {
            f.append('+');
            p.append(e);
         } else {
            f.append('-');
            p.append(-e);
         }

         char[] data = new char[3];
         p.getChars(p.length() - 3, p.length(), data, 0);
         return f.append(data).toString();
      }
   }

   protected String fixedFormat(double d) {
      boolean removeTrailing = (this.fmt == 'G' || this.fmt == 'g') && !this.alternate;
      if (d > (double)Long.MAX_VALUE) {
         return this.expFormat(d);
      } else if (this.precision == 0) {
         return Long.toString(Math.round(d));
      } else {
         long whole = (long)d;
         double fr = d - (double)whole;
         if (!(fr >= (double)1.0F) && !(fr < (double)0.0F)) {
            double factor = (double)1.0F;
            StringBuilder leadingZeroesStr = new StringBuilder();

            for(int i = 1; i <= this.precision && factor <= (double)Long.MAX_VALUE; ++i) {
               factor *= (double)10.0F;
               leadingZeroesStr.append('0');
            }

            long l = Math.round(factor * fr);
            if ((double)l >= factor) {
               l = 0L;
               ++whole;
            }

            String z = leadingZeroesStr.toString() + l;
            z = '.' + z.substring(z.length() - this.precision, z.length());
            if (removeTrailing) {
               int t;
               for(t = z.length() - 1; t >= 0 && z.charAt(t) == '0'; --t) {
               }

               if (t >= 0 && z.charAt(t) == '.') {
                  --t;
               }

               z = z.substring(0, t + 1);
            }

            return whole + z;
         } else {
            return this.expFormat(d);
         }
      }
   }

   protected String pad(String value) {
      String spaces = repeat(' ', this.width - value.length());
      return this.leftAlign ? this.pre + value + spaces + this.post : this.pre + spaces + value + this.post;
   }

   protected static String repeat(char c, int n) {
      if (n <= 0) {
         return "";
      } else {
         char[] buffer = new char[n];

         for(int i = 0; i < n; ++i) {
            buffer[i] = c;
         }

         return new String(buffer);
      }
   }

   private String getAltPrefixFor(char fmt, String currentPrefix) {
      switch (fmt) {
         case 'B':
            return "0B";
         case 'X':
            return "0X";
         case 'b':
            return "0b";
         case 'x':
            return "0x";
         default:
            return currentPrefix;
      }
   }

   protected String sign(int s, String r) {
      String p = "";
      if (s < 0) {
         p = "-";
      } else if (s > 0) {
         if (this.showPlus) {
            p = "+";
         } else if (this.showSpace) {
            p = " ";
         }
      } else if (this.alternate) {
         if (this.fmt == 'o' && r.length() > 0 && r.charAt(0) != '0') {
            p = "0";
         } else {
            p = this.getAltPrefixFor(this.fmt, p);
         }
      }

      int w = 0;
      if (this.leadingZeroes) {
         w = this.width;
      } else if ((this.fmt == 'u' || this.fmt == 'd' || this.fmt == 'i' || this.fmt == 'x' || this.fmt == 'X' || this.fmt == 'o') && this.precision > 0) {
         w = this.precision;
      }

      return this.countSignInLen ? p + repeat('0', w - p.length() - r.length()) + r : p + repeat('0', w - r.length()) + r;
   }

   protected String groupDigits(String value, int size, char separator) {
      if (!this.groupDigits) {
         return value;
      } else {
         StringBuilder r = new StringBuilder(value.length() + 10);
         int ndx = 0;
         int len = value.length() - 1;

         for(int mod = len % size; ndx < len; ++ndx) {
            r.append(value.charAt(ndx));
            if (mod == 0) {
               r.append(separator);
               mod = size;
            }

            --mod;
         }

         r.append(value.charAt(ndx));
         return r.toString();
      }
   }

   public String form(char value) {
      switch (this.fmt) {
         case 'C':
            return this.alternate ? "\\u" + Integer.toHexString(value & '\uffff').toUpperCase() : this.pad(String.valueOf(value));
         case 'L':
         case 'X':
         case 'b':
         case 'd':
         case 'i':
         case 'l':
         case 'o':
         case 'u':
         case 'x':
            return this.form((short)value);
         case 'c':
            return this.alternate ? "\\u" + Integer.toHexString(value & '\uffff') : this.pad(String.valueOf(value));
         default:
            throw this.newIllegalArgumentException("cCdiuoxXblL");
      }
   }

   public String form(boolean value) {
      if (this.fmt == 'l') {
         return this.pad(value ? "true" : "false");
      } else if (this.fmt == 'L') {
         return this.pad(value ? "TRUE" : "FALSE");
      } else {
         throw this.newIllegalArgumentException("lL");
      }
   }

   public String form(double x) {
      if (this.precision < 0) {
         this.precision = 6;
      }

      int s = 1;
      if (x < (double)0.0F) {
         x = -x;
         s = -1;
      }

      String r;
      if (this.fmt == 'f') {
         r = this.fixedFormat(x);
      } else {
         if (this.fmt != 'e' && this.fmt != 'E' && this.fmt != 'g' && this.fmt != 'G') {
            throw this.newIllegalArgumentException("feEgG");
         }

         r = this.expFormat(x);
      }

      return this.pad(this.sign(s, r));
   }

   public String form(long x) {
      int s = 0;
      String r;
      switch (this.fmt) {
         case 'B':
         case 'b':
            r = Long.toBinaryString(x);
            r = this.groupDigits(r, 8, ' ');
            break;
         case 'L':
            r = x == 0L ? "FALSE" : "TRUE";
            break;
         case 'X':
            r = Long.toHexString(x).toUpperCase();
            r = this.groupDigits(r, 4, ' ');
            break;
         case 'c':
            return this.form((char)((int)x));
         case 'd':
            if (x < 0L) {
               r = Long.toString(x).substring(1);
               s = -1;
            } else {
               r = Long.toString(x);
               s = 1;
            }

            r = this.groupDigits(r, 3, ',');
            break;
         case 'i':
            int xx = (int)x;
            if (xx < 0) {
               r = Integer.toString(xx).substring(1);
               s = -1;
            } else {
               r = Integer.toString(xx);
               s = 1;
            }

            r = this.groupDigits(r, 3, ',');
            break;
         case 'l':
            r = x == 0L ? "false" : "true";
            break;
         case 'o':
            r = Long.toOctalString(x);
            break;
         case 'u':
            if (x < 0L) {
               long xl = x & Long.MAX_VALUE;
               r = Long.toString(xl);
               BigInteger bi = new BigInteger(r);
               r = bi.add(bgInt).toString();
            } else {
               r = Long.toString(x);
            }

            r = this.groupDigits(r, 3, ',');
            s = 1;
            break;
         case 'x':
            r = Long.toHexString(x);
            r = this.groupDigits(r, 4, ' ');
            break;
         default:
            throw new IllegalArgumentException("cdiuoxXbBlL");
      }

      return this.pad(this.sign(s, r));
   }

   public String form(int x) {
      int s = 0;
      String r;
      switch (this.fmt) {
         case 'B':
         case 'b':
            r = Integer.toBinaryString(x);
            r = this.groupDigits(r, 8, ' ');
            break;
         case 'L':
            r = x == 0 ? "FALSE" : "TRUE";
            break;
         case 'X':
            r = Integer.toHexString(x).toUpperCase();
            r = this.groupDigits(r, 4, ' ');
            break;
         case 'c':
            return this.form((char)x);
         case 'd':
         case 'i':
            if (x < 0) {
               r = Integer.toString(x).substring(1);
               s = -1;
            } else {
               r = Integer.toString(x);
               s = 1;
            }

            r = this.groupDigits(r, 3, ',');
            break;
         case 'l':
            r = x == 0 ? "false" : "true";
            break;
         case 'o':
            r = Integer.toOctalString(x);
            break;
         case 'u':
            long xl = (long)x & 4294967295L;
            r = Long.toString(xl);
            r = this.groupDigits(r, 3, ',');
            s = 1;
            break;
         case 'x':
            r = Integer.toHexString(x);
            r = this.groupDigits(r, 4, ' ');
            break;
         default:
            throw this.newIllegalArgumentException("cdiuoxXbBlL");
      }

      return this.pad(this.sign(s, r));
   }

   public String form(byte b) {
      return this.formInt(b, 255);
   }

   public String form(short s) {
      return this.formInt(s, 65535);
   }

   private String formInt(int value, int unsignedMask) {
      int s = 0;
      String r;
      switch (this.fmt) {
         case 'B':
         case 'b':
            r = Integer.toBinaryString(value & unsignedMask);
            r = this.groupDigits(r, 8, ' ');
            break;
         case 'L':
            r = value == 0 ? "FALSE" : "TRUE";
            break;
         case 'X':
            r = Integer.toHexString(value & unsignedMask).toUpperCase();
            r = this.groupDigits(r, 4, ' ');
            break;
         case 'c':
            return this.form((char)value);
         case 'd':
         case 'i':
            if (value < 0) {
               r = Integer.toString(value).substring(1);
               s = -1;
            } else {
               r = Integer.toString(value);
               s = 1;
            }

            r = this.groupDigits(r, 3, ',');
            break;
         case 'l':
            r = value == 0 ? "false" : "true";
            break;
         case 'o':
            r = Integer.toOctalString(value & unsignedMask);
            break;
         case 'u':
            int xl = value & unsignedMask;
            r = Integer.toString(xl);
            r = this.groupDigits(r, 3, ',');
            s = 1;
            break;
         case 'x':
            r = Integer.toHexString(value & unsignedMask);
            r = this.groupDigits(r, 4, ' ');
            break;
         default:
            throw this.newIllegalArgumentException("cdiuoxXblL");
      }

      return this.pad(this.sign(s, r));
   }

   public String form(Object object) {
      switch (this.fmt) {
         case 'p':
            return Integer.toString(System.identityHashCode(object));
         case 's':
            String s = object == null ? "null" : object.toString();
            if (this.precision >= 0 && this.precision < s.length()) {
               s = s.substring(0, this.precision);
            }

            return this.pad(s);
         default:
            if (object instanceof Number) {
               Number number = (Number)object;
               if (object instanceof Integer) {
                  return this.form(number.intValue());
               } else if (object instanceof Long) {
                  return this.form(number.longValue());
               } else if (object instanceof Double) {
                  return this.form(number.doubleValue());
               } else if (object instanceof Float) {
                  return this.form((double)number.floatValue());
               } else if (object instanceof Byte) {
                  return this.form(number.byteValue());
               } else {
                  return object instanceof Short ? this.form(number.shortValue()) : this.form(number.intValue());
               }
            } else if (object instanceof Character) {
               return this.form((Character)object);
            } else {
               throw this.newIllegalArgumentException("sp");
            }
      }
   }

   protected IllegalArgumentException newIllegalArgumentException(String allowedFormats) {
      return new IllegalArgumentException("Invalid format: '" + this.fmt + "' is not one of '" + allowedFormats + "'");
   }
}
