package org.sparkproject.jetty.http;

public abstract class QuotedCSVParser {
   protected final boolean _keepQuotes;

   public QuotedCSVParser(boolean keepQuotes) {
      this._keepQuotes = keepQuotes;
   }

   public static String unquote(String s) {
      int l = s.length();
      if (s != null && l != 0) {
         int i;
         for(i = 0; i < l; ++i) {
            char c = s.charAt(i);
            if (c == '"') {
               break;
            }
         }

         if (i == l) {
            return s;
         } else {
            boolean quoted = true;
            boolean sloshed = false;
            StringBuffer buffer = new StringBuffer();
            buffer.append(s, 0, i);
            ++i;

            for(; i < l; ++i) {
               char c = s.charAt(i);
               if (quoted) {
                  if (sloshed) {
                     buffer.append(c);
                     sloshed = false;
                  } else if (c == '"') {
                     quoted = false;
                  } else if (c == '\\') {
                     sloshed = true;
                  } else {
                     buffer.append(c);
                  }
               } else if (c == '"') {
                  quoted = true;
               } else {
                  buffer.append(c);
               }
            }

            return buffer.toString();
         }
      } else {
         return s;
      }
   }

   public void addValue(String value) {
      if (value != null) {
         StringBuffer buffer = new StringBuffer();
         int l = value.length();
         State state = QuotedCSVParser.State.VALUE;
         boolean quoted = false;
         boolean sloshed = false;
         int nwsLength = 0;
         int lastLength = 0;
         int valueLength = -1;
         int paramName = -1;
         int paramValue = -1;

         for(int i = 0; i <= l; ++i) {
            char c = i == l ? 0 : value.charAt(i);
            if (quoted && c != 0) {
               if (sloshed) {
                  sloshed = false;
               } else {
                  switch (c) {
                     case '"':
                        quoted = false;
                        if (!this._keepQuotes) {
                           continue;
                        }
                        break;
                     case '\\':
                        sloshed = true;
                        if (!this._keepQuotes) {
                           continue;
                        }
                  }
               }

               buffer.append(c);
               nwsLength = buffer.length();
            } else {
               switch (c) {
                  case '\u0000':
                  case ',':
                     if (nwsLength > 0) {
                        buffer.setLength(nwsLength);
                        switch (state.ordinal()) {
                           case 0:
                              this.parsedValue(buffer);
                              valueLength = buffer.length();
                              break;
                           case 1:
                           case 2:
                              this.parsedParam(buffer, valueLength, paramName, paramValue);
                              break;
                           default:
                              throw new IllegalStateException(state.toString());
                        }

                        this.parsedValueAndParams(buffer);
                     }

                     buffer.setLength(0);
                     lastLength = 0;
                     nwsLength = 0;
                     paramValue = -1;
                     paramName = -1;
                     valueLength = -1;
                     state = QuotedCSVParser.State.VALUE;
                     break;
                  case '\t':
                  case ' ':
                     if (buffer.length() > lastLength) {
                        buffer.append(c);
                     }
                     break;
                  case '"':
                     quoted = true;
                     if (this._keepQuotes) {
                        if (state == QuotedCSVParser.State.PARAM_VALUE && paramValue < 0) {
                           paramValue = nwsLength;
                        }

                        buffer.append(c);
                     } else if (state == QuotedCSVParser.State.PARAM_VALUE && paramValue < 0) {
                        paramValue = nwsLength;
                     }

                     nwsLength = buffer.length();
                     break;
                  case ';':
                     buffer.setLength(nwsLength);
                     if (state == QuotedCSVParser.State.VALUE) {
                        this.parsedValue(buffer);
                        valueLength = buffer.length();
                     } else {
                        this.parsedParam(buffer, valueLength, paramName, paramValue);
                     }

                     nwsLength = buffer.length();
                     paramValue = -1;
                     paramName = -1;
                     buffer.append(c);
                     ++nwsLength;
                     lastLength = nwsLength;
                     state = QuotedCSVParser.State.PARAM_NAME;
                     break;
                  case '=':
                     switch (state.ordinal()) {
                        case 0:
                           paramName = 0;
                           buffer.setLength(nwsLength);
                           String param = buffer.toString();
                           buffer.setLength(0);
                           this.parsedValue(buffer);
                           valueLength = buffer.length();
                           buffer.append(param);
                           buffer.append(c);
                           ++nwsLength;
                           lastLength = nwsLength;
                           state = QuotedCSVParser.State.PARAM_VALUE;
                           continue;
                        case 1:
                           buffer.setLength(nwsLength);
                           buffer.append(c);
                           ++nwsLength;
                           lastLength = nwsLength;
                           state = QuotedCSVParser.State.PARAM_VALUE;
                           continue;
                        case 2:
                           if (paramValue < 0) {
                              paramValue = nwsLength;
                           }

                           buffer.append(c);
                           nwsLength = buffer.length();
                           continue;
                        default:
                           throw new IllegalStateException(state.toString());
                     }
                  default:
                     switch (state.ordinal()) {
                        case 0:
                           buffer.append(c);
                           nwsLength = buffer.length();
                           break;
                        case 1:
                           if (paramName < 0) {
                              paramName = nwsLength;
                           }

                           buffer.append(c);
                           nwsLength = buffer.length();
                           break;
                        case 2:
                           if (paramValue < 0) {
                              paramValue = nwsLength;
                           }

                           buffer.append(c);
                           nwsLength = buffer.length();
                           break;
                        default:
                           throw new IllegalStateException(state.toString());
                     }
               }
            }
         }

      }
   }

   protected void parsedValueAndParams(StringBuffer buffer) {
   }

   protected void parsedValue(StringBuffer buffer) {
   }

   protected void parsedParam(StringBuffer buffer, int valueLength, int paramName, int paramValue) {
   }

   private static enum State {
      VALUE,
      PARAM_NAME,
      PARAM_VALUE;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{VALUE, PARAM_NAME, PARAM_VALUE};
      }
   }
}
