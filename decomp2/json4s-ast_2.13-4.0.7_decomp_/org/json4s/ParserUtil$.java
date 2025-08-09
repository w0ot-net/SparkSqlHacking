package org.json4s;

import java.io.StringReader;
import scala.math.BigDecimal;
import scala.package.;
import scala.runtime.BoxesRunTime;

public final class ParserUtil$ {
   public static final ParserUtil$ MODULE$ = new ParserUtil$();
   public static final char org$json4s$ParserUtil$$EOF = (char)-1;
   private static final BigDecimal BrokenDouble;

   static {
      BrokenDouble = .MODULE$.BigDecimal().apply("2.2250738585072012e-308");
   }

   public String quote(final String s, final boolean alwaysEscapeUnicode) {
      return ((StringBuilder)this.quote(s, new StringBuilder(), alwaysEscapeUnicode)).toString();
   }

   public Appendable quote(final String s, final Appendable appender, final boolean alwaysEscapeUnicode) {
      int i = 0;

      for(int l = s.length(); i < l; ++i) {
         char var6 = scala.collection.StringOps..MODULE$.apply$extension(scala.Predef..MODULE$.augmentString(s), i);
         switch (var6) {
            case '\b':
               appender.append("\\b");
               break;
            case '\t':
               appender.append("\\t");
               break;
            case '\n':
               appender.append("\\n");
               break;
            case '\f':
               appender.append("\\f");
               break;
            case '\r':
               appender.append("\\r");
               break;
            case '"':
               appender.append("\\\"");
               break;
            case '\\':
               appender.append("\\\\");
               break;
            default:
               boolean shouldEscape = alwaysEscapeUnicode ? var6 >= 128 : var6 >= 0 && var6 <= 31 || var6 >= 128 && var6 < 160 || var6 >= 8192 && var6 < 8448;
               if (shouldEscape) {
                  appender.append(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("\\u%04X"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(var6)})));
               } else {
                  appender.append(var6);
               }
         }
      }

      return appender;
   }

   public String unquote(final String string) {
      return this.unquote(new ParserUtil.Buffer(new StringReader(string), false));
   }

   public String unquote(final ParserUtil.Buffer buf) {
      buf.eofIsFailure_$eq(true);
      buf.mark();

      for(char c = buf.next(); c != '"'; c = buf.next()) {
         if (c == '\\') {
            String s = unquote0$1(buf, buf.substring());
            buf.eofIsFailure_$eq(false);
            return s;
         }
      }

      buf.eofIsFailure_$eq(false);
      return buf.substring();
   }

   public int defaultSegmentSize() {
      return 1000;
   }

   public double parseDouble(final String s) {
      BigDecimal d = .MODULE$.BigDecimal().apply(s);
      BigDecimal var3 = BrokenDouble;
      if (d == null) {
         if (var3 == null) {
            throw scala.sys.package..MODULE$.error("Error parsing 2.2250738585072012e-308");
         }
      } else if (d.equals(var3)) {
         throw scala.sys.package..MODULE$.error("Error parsing 2.2250738585072012e-308");
      }

      return d.doubleValue();
   }

   private static final String unquote0$1(final ParserUtil.Buffer buf, final String base) {
      StringBuilder s = new StringBuilder(base);

      for(char c = '\\'; c != '"'; c = buf.next()) {
         if (c == '\\') {
            char var4 = buf.next();
            switch (var4) {
               case '"':
                  s.append('"');
                  break;
               case '/':
                  s.append('/');
                  break;
               case '\\':
                  s.append('\\');
                  break;
               case 'b':
                  s.append('\b');
                  break;
               case 'f':
                  s.append('\f');
                  break;
               case 'n':
                  s.append('\n');
                  break;
               case 'r':
                  s.append('\r');
                  break;
               case 't':
                  s.append('\t');
                  break;
               case 'u':
                  char[] chars = new char[]{buf.next(), buf.next(), buf.next(), buf.next()};
                  int codePoint = Integer.parseInt(new String(chars), 16);
                  s.appendCodePoint(codePoint);
                  break;
               default:
                  s.append('\\');
            }
         } else {
            s.append(c);
         }
      }

      return s.toString();
   }

   private ParserUtil$() {
   }
}
