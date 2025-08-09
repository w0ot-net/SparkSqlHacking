package org.sparkproject.jetty.http;

import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.StringUtil;

public class HttpField {
   private static final String __zeroquality = "q=0";
   private final HttpHeader _header;
   private final String _name;
   private final String _value;
   private int hash;

   public HttpField(HttpHeader header, String name, String value) {
      this.hash = 0;
      this._header = header;
      if (this._header != null && name == null) {
         this._name = this._header.asString();
      } else {
         this._name = (String)Objects.requireNonNull(name, "name");
      }

      this._value = value;
   }

   public HttpField(HttpHeader header, String value) {
      this(header, header.asString(), value);
   }

   public HttpField(HttpHeader header, HttpHeaderValue value) {
      this(header, header.asString(), value.asString());
   }

   public HttpField(String name, String value) {
      this((HttpHeader)HttpHeader.CACHE.get(name), name, value);
   }

   public static String getValueParameters(String value, Map parameters) {
      if (value == null) {
         return null;
      } else {
         int i = value.indexOf(59);
         if (i < 0) {
            return value;
         } else if (parameters == null) {
            return value.substring(0, i).trim();
         } else {
            StringTokenizer tok1 = new QuotedStringTokenizer(value.substring(i), ";", false, true);

            while(tok1.hasMoreTokens()) {
               String token = tok1.nextToken();
               StringTokenizer tok2 = new QuotedStringTokenizer(token, "= ");
               if (tok2.hasMoreTokens()) {
                  String paramName = tok2.nextToken();
                  String paramVal = null;
                  if (tok2.hasMoreTokens()) {
                     paramVal = tok2.nextToken();
                  }

                  parameters.put(paramName, paramVal);
               }
            }

            return value.substring(0, i).trim();
         }
      }
   }

   public static String stripParameters(String value) {
      if (value == null) {
         return null;
      } else {
         int i = value.indexOf(59);
         return i < 0 ? value : value.substring(0, i).trim();
      }
   }

   public static String valueParameters(String value, Map parameters) {
      return getValueParameters(value, parameters);
   }

   public boolean contains(String search) {
      if (search == null) {
         return this._value == null;
      } else if (search.isEmpty()) {
         return false;
      } else if (this._value == null) {
         return false;
      } else if (search.equalsIgnoreCase(this._value)) {
         return true;
      } else {
         int state = 0;
         int match = 0;
         int param = 0;

         for(int i = 0; i < this._value.length(); ++i) {
            char c = StringUtil.asciiToLowerCase(this._value.charAt(i));
            switch (state) {
               case 0:
                  switch (c) {
                     case '\t':
                     case ' ':
                     case ',':
                        continue;
                     case '"':
                        match = 0;
                        state = 2;
                        continue;
                     case ';':
                        param = -1;
                        match = -1;
                        state = 5;
                        continue;
                     default:
                        match = c == StringUtil.asciiToLowerCase(search.charAt(0)) ? 1 : -1;
                        state = 1;
                        continue;
                  }
               case 1:
                  switch (c) {
                     case ',':
                        if (match == search.length()) {
                           return true;
                        }

                        state = 0;
                        continue;
                     case ';':
                        param = match >= 0 ? 0 : -1;
                        state = 5;
                        continue;
                     default:
                        if (match > 0) {
                           if (match < search.length()) {
                              match = c == StringUtil.asciiToLowerCase(search.charAt(match)) ? match + 1 : -1;
                           } else if (c != ' ' && c != '\t') {
                              match = -1;
                           }
                        }
                        continue;
                  }
               case 2:
                  switch (c) {
                     case '"':
                        state = 4;
                        continue;
                     case '\\':
                        state = 3;
                        continue;
                     default:
                        if (match >= 0) {
                           if (match < search.length()) {
                              match = c == StringUtil.asciiToLowerCase(search.charAt(match)) ? match + 1 : -1;
                           } else {
                              match = -1;
                           }
                        }
                        continue;
                  }
               case 3:
                  if (match >= 0) {
                     if (match < search.length()) {
                        match = c == StringUtil.asciiToLowerCase(search.charAt(match)) ? match + 1 : -1;
                     } else {
                        match = -1;
                     }
                  }

                  state = 2;
                  break;
               case 4:
                  switch (c) {
                     case '\t':
                     case ' ':
                        continue;
                     case ',':
                        if (match == search.length()) {
                           return true;
                        }

                        state = 0;
                        continue;
                     case ';':
                        state = 5;
                        continue;
                     default:
                        match = -1;
                        continue;
                  }
               case 5:
                  switch (c) {
                     case '\t':
                     case ' ':
                        continue;
                     case ',':
                        if (param != "q=0".length() && match == search.length()) {
                           return true;
                        }

                        param = 0;
                        state = 0;
                        continue;
                     default:
                        if (param >= 0) {
                           if (param < "q=0".length()) {
                              param = c == "q=0".charAt(param) ? param + 1 : -1;
                           } else if (c != '0' && c != '.') {
                              param = -1;
                           }
                        }
                        continue;
                  }
               default:
                  throw new IllegalStateException();
            }
         }

         return param != "q=0".length() && match == search.length();
      }
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof HttpField)) {
         return false;
      } else {
         HttpField field = (HttpField)o;
         if (this._header != field.getHeader()) {
            return false;
         } else {
            return !this._name.equalsIgnoreCase(field.getName()) ? false : Objects.equals(this._value, field.getValue());
         }
      }
   }

   public HttpHeader getHeader() {
      return this._header;
   }

   public int getIntValue() {
      return Integer.parseInt(this._value);
   }

   public long getLongValue() {
      return Long.parseLong(this._value);
   }

   public String getLowerCaseName() {
      return this._header != null ? this._header.lowerCaseName() : StringUtil.asciiToLowerCase(this._name);
   }

   public String getName() {
      return this._name;
   }

   public String getValue() {
      return this._value;
   }

   public String[] getValues() {
      if (this._value == null) {
         return null;
      } else {
         QuotedCSV list = new QuotedCSV(false, new String[]{this._value});
         return (String[])list.getValues().toArray(new String[list.size()]);
      }
   }

   public int hashCode() {
      int vhc = Objects.hashCode(this._value);
      return this._header == null ? vhc ^ this.nameHashCode() : vhc ^ this._header.hashCode();
   }

   public boolean isSameName(HttpField field) {
      if (field == null) {
         return false;
      } else if (field == this) {
         return true;
      } else {
         return this._header != null && this._header == field.getHeader() ? true : this._name.equalsIgnoreCase(field.getName());
      }
   }

   public boolean is(String name) {
      return this._name.equalsIgnoreCase(name);
   }

   private int nameHashCode() {
      int h = this.hash;
      int len = this._name.length();
      if (h == 0 && len > 0) {
         for(int i = 0; i < len; ++i) {
            char c = this._name.charAt(i);
            if (c >= 'a' && c <= 'z') {
               c = (char)(c - 32);
            }

            h = 31 * h + c;
         }

         this.hash = h;
      }

      return h;
   }

   public String toString() {
      String v = this.getValue();
      return this.getName() + ": " + (v == null ? "" : v);
   }

   public static class IntValueHttpField extends HttpField {
      private final int _int;

      public IntValueHttpField(HttpHeader header, String name, String value, int intValue) {
         super(header, name, value);
         this._int = intValue;
      }

      public IntValueHttpField(HttpHeader header, String name, String value) {
         this(header, name, value, Integer.parseInt(value));
      }

      public IntValueHttpField(HttpHeader header, String name, int intValue) {
         this(header, name, Integer.toString(intValue), intValue);
      }

      public IntValueHttpField(HttpHeader header, int value) {
         this(header, header.asString(), value);
      }

      public int getIntValue() {
         return this._int;
      }

      public long getLongValue() {
         return (long)this._int;
      }
   }

   public static class LongValueHttpField extends HttpField {
      private final long _long;

      public LongValueHttpField(HttpHeader header, String name, String value, long longValue) {
         super(header, name, value);
         this._long = longValue;
      }

      public LongValueHttpField(HttpHeader header, String name, String value) {
         this(header, name, value, Long.parseLong(value));
      }

      public LongValueHttpField(HttpHeader header, String name, long value) {
         this(header, name, Long.toString(value), value);
      }

      public LongValueHttpField(HttpHeader header, long value) {
         this(header, header.asString(), value);
      }

      public int getIntValue() {
         return (int)this._long;
      }

      public long getLongValue() {
         return this._long;
      }
   }
}
