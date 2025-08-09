package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]ba\u0002\b\u0010!\u0003\r\t\u0001\b\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u000b\u0002!\t\u0001\u0014\u0005\u0006+\u0002!\tA\u0016\u0005\u0006=\u0002!\ta\u0018\u0005\bq\u0002\t\n\u0011\"\u0001z\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAq!a\t\u0001\t\u0003\t)cB\u0004\u0002*=A\t!a\u000b\u0007\r9y\u0001\u0012AA\u0018\u0011\u001d\t\u0019\u0004\u0004C\u0001\u0003k\u0011\u0001c\u00159be.\u0004\u0016M]:feV#\u0018\u000e\\:\u000b\u0005A\t\u0012\u0001B;uS2T!AE\n\u0002\u0011\r\fG/\u00197zgRT!\u0001F\u000b\u0002\u0007M\fHN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u000f\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0005\u0005\u0002\u001fM%\u0011qe\b\u0002\u0005+:LG/A\tv]\u0016\u001c8-\u00199f'Fc5\u000b\u001e:j]\u001e$\"AK\u001b\u0011\u0005-\u0012dB\u0001\u00171!\tis$D\u0001/\u0015\ty3$\u0001\u0004=e>|GOP\u0005\u0003c}\ta\u0001\u0015:fI\u00164\u0017BA\u001a5\u0005\u0019\u0019FO]5oO*\u0011\u0011g\b\u0005\u0006m\t\u0001\rAK\u0001\u0002E\u000611o\\;sG\u0016$\"AK\u001d\t\u000bi\u001a\u0001\u0019A\u001e\u0002\u0007\r$\b\u0010\u0005\u0002=\u00076\tQH\u0003\u0002?\u007f\u00059!/\u001e8uS6,'B\u0001!B\u0003\t1HG\u0003\u0002C3\u0005)\u0011M\u001c;me&\u0011A)\u0010\u0002\u0012!\u0006\u00148/\u001a:Sk2,7i\u001c8uKb$\u0018AB:ue&tw\r\u0006\u0002+\u000f\")\u0001\n\u0002a\u0001\u0013\u0006)Ao\\6f]B\u0011AHS\u0005\u0003\u0017v\u0012Q\u0001V8lK:$\"AK'\t\u000b9+\u0001\u0019A(\u0002\t9|G-\u001a\t\u0003!Nk\u0011!\u0015\u0006\u0003%v\nA\u0001\u001e:fK&\u0011A+\u0015\u0002\r)\u0016\u0014X.\u001b8bY:{G-Z\u0001\ta>\u001c\u0018\u000e^5p]R\u0011q+\u0018\t\u00031nk\u0011!\u0017\u0006\u00035F\tQ\u0001\u001e:fKNL!\u0001X-\u0003\r=\u0013\u0018nZ5o\u0011\u0015Ae\u00011\u0001J\u0003)9\u0018\u000e\u001e5Pe&<\u0017N\\\u000b\u0003A\u0012$2!\u0019:t)\t\u0011W\u000e\u0005\u0002dI2\u0001A!B3\b\u0005\u00041'!\u0001+\u0012\u0005\u001dT\u0007C\u0001\u0010i\u0013\tIwDA\u0004O_RD\u0017N\\4\u0011\u0005yY\u0017B\u00017 \u0005\r\te.\u001f\u0005\u0007]\u001e!\t\u0019A8\u0002\u0003\u0019\u00042A\b9c\u0013\t\txD\u0001\u0005=Eft\u0017-\\3?\u0011\u0015Qt\u00011\u0001<\u0011\u001d!x\u0001%AA\u0002U\fqa]9m)\u0016DH\u000fE\u0002\u001fm*J!a^\u0010\u0003\r=\u0003H/[8o\u0003Q9\u0018\u000e\u001e5Pe&<\u0017N\u001c\u0013eK\u001a\fW\u000f\u001c;%eU\u0019!0a\u0003\u0016\u0003mT#!\u001e?,\u0003u\u00042A`A\u0004\u001b\u0005y(\u0002BA\u0001\u0003\u0007\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0015q$\u0001\u0006b]:|G/\u0019;j_:L1!!\u0003\u0000\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006K\"\u0011\rAZ\u0001\u0010a>\u001c\u0018\u000e^5p]\u0006sG\rV3yiRYq+!\u0005\u0002\u0016\u0005e\u00111DA\u0010\u0011\u0019\t\u0019\"\u0003a\u0001\u0013\u0006Q1\u000f^1siR{7.\u001a8\t\r\u0005]\u0011\u00021\u0001J\u0003%\u0019Ho\u001c9U_.,g\u000eC\u0003u\u0013\u0001\u0007!\u0006\u0003\u0004\u0002\u001e%\u0001\r!^\u0001\u000b_\nTWm\u0019;UsB,\u0007BBA\u0011\u0013\u0001\u0007Q/\u0001\u0006pE*,7\r\u001e(b[\u0016\fqaY8n[\u0006tG\rF\u0002+\u0003OAQA\u000f\u0006A\u0002m\n\u0001c\u00159be.\u0004\u0016M]:feV#\u0018\u000e\\:\u0011\u0007\u00055B\"D\u0001\u0010'\u0011aQ$!\r\u0011\u0007\u00055\u0002!\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003W\u0001"
)
public interface SparkParserUtils {
   // $FF: synthetic method
   static String unescapeSQLString$(final SparkParserUtils $this, final String b) {
      return $this.unescapeSQLString(b);
   }

   default String unescapeSQLString(final String b) {
      char firstChar = b.charAt(0);
      boolean isRawString = firstChar == 'r' || firstChar == 'R';
      if (isRawString) {
         return b.substring(2, b.length() - 1);
      } else if (b.indexOf(92) == -1) {
         return b.substring(1, b.length() - 1);
      } else {
         StringBuilder sb = new StringBuilder(b.length());
         int i = 1;
         int length = b.length() - 1;

         while(i < length) {
            char c = b.charAt(i);
            if (c == '\\' && i + 1 != length) {
               ++i;
               char cAfterBackslash = b.charAt(i);
               if (cAfterBackslash == 'u' && i + 1 + 4 <= length && allCharsAreHex$1(b, i + 1, 4)) {
                  sb.append((char)Integer.parseInt(b, i + 1, i + 1 + 4, 16));
                  i += 5;
               } else if (cAfterBackslash == 'U' && i + 1 + 8 <= length && allCharsAreHex$1(b, i + 1, 8)) {
                  long codePoint = Long.parseLong(b, i + 1, i + 1 + 8, 16);
                  if (codePoint < 65536L) {
                     sb.append((char)((int)(codePoint & 65535L)));
                  } else {
                     long highSurrogate = (codePoint - 65536L) / 1024L + 55296L;
                     long lowSurrogate = (codePoint - 65536L) % 1024L + 56320L;
                     sb.append((char)((int)highSurrogate));
                     sb.append((char)((int)lowSurrogate));
                  }

                  i += 9;
               } else if (i + 3 <= length && isThreeDigitOctalEscape$1(b, i)) {
                  sb.append((char)Integer.parseInt(b, i, i + 3, 8));
                  i += 3;
               } else {
                  appendEscapedChar$1(cAfterBackslash, sb);
                  ++i;
               }
            } else {
               sb.append(c);
               ++i;
            }
         }

         return sb.toString();
      }
   }

   // $FF: synthetic method
   static String source$(final SparkParserUtils $this, final ParserRuleContext ctx) {
      return $this.source(ctx);
   }

   default String source(final ParserRuleContext ctx) {
      CharStream stream = ctx.getStart().getInputStream();
      return stream.getText(Interval.of(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex()));
   }

   // $FF: synthetic method
   static String string$(final SparkParserUtils $this, final Token token) {
      return $this.string(token);
   }

   default String string(final Token token) {
      return this.unescapeSQLString(token.getText());
   }

   // $FF: synthetic method
   static String string$(final SparkParserUtils $this, final TerminalNode node) {
      return $this.string(node);
   }

   default String string(final TerminalNode node) {
      return this.unescapeSQLString(node.getText());
   }

   // $FF: synthetic method
   static Origin position$(final SparkParserUtils $this, final Token token) {
      return $this.position(token);
   }

   default Origin position(final Token token) {
      Option opt = .MODULE$.apply(token);
      return new Origin(opt.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$position$1(x$1))), opt.map((x$2) -> BoxesRunTime.boxToInteger($anonfun$position$2(x$2))), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
   }

   // $FF: synthetic method
   static Object withOrigin$(final SparkParserUtils $this, final ParserRuleContext ctx, final Option sqlText, final Function0 f) {
      return $this.withOrigin(ctx, sqlText, f);
   }

   default Object withOrigin(final ParserRuleContext ctx, final Option sqlText, final Function0 f) {
      Origin current = CurrentOrigin$.MODULE$.get();
      Option text = sqlText.orElse(() -> current.sqlText());
      if (text.isEmpty()) {
         CurrentOrigin$.MODULE$.set(this.position(ctx.getStart()));
      } else {
         CurrentOrigin$.MODULE$.set(this.positionAndText(ctx.getStart(), ctx.getStop(), (String)text.get(), current.objectType(), current.objectName()));
      }

      Object var10000;
      try {
         var10000 = f.apply();
      } finally {
         CurrentOrigin$.MODULE$.set(current);
      }

      return var10000;
   }

   // $FF: synthetic method
   static Option withOrigin$default$2$(final SparkParserUtils $this) {
      return $this.withOrigin$default$2();
   }

   default Option withOrigin$default$2() {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   static Origin positionAndText$(final SparkParserUtils $this, final Token startToken, final Token stopToken, final String sqlText, final Option objectType, final Option objectName) {
      return $this.positionAndText(startToken, stopToken, sqlText, objectType, objectName);
   }

   default Origin positionAndText(final Token startToken, final Token stopToken, final String sqlText, final Option objectType, final Option objectName) {
      Option startOpt = .MODULE$.apply(startToken);
      Option stopOpt = .MODULE$.apply(stopToken);
      return new Origin(startOpt.map((x$3) -> BoxesRunTime.boxToInteger($anonfun$positionAndText$1(x$3))), startOpt.map((x$4) -> BoxesRunTime.boxToInteger($anonfun$positionAndText$2(x$4))), startOpt.map((x$5) -> BoxesRunTime.boxToInteger($anonfun$positionAndText$3(x$5))), stopOpt.map((x$6) -> BoxesRunTime.boxToInteger($anonfun$positionAndText$4(x$6))), new Some(sqlText), objectType, objectName, Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
   }

   // $FF: synthetic method
   static String command$(final SparkParserUtils $this, final ParserRuleContext ctx) {
      return $this.command(ctx);
   }

   default String command(final ParserRuleContext ctx) {
      CharStream stream = ctx.getStart().getInputStream();
      return stream.getText(Interval.of(0, stream.size() - 1));
   }

   private static void appendEscapedChar$1(final char n, final StringBuilder sb) {
      switch (n) {
         case '%':
            sb.append("\\%");
            return;
         case '0':
            sb.append('\u0000');
            return;
         case 'Z':
            sb.append('\u001a');
            return;
         case '_':
            sb.append("\\_");
            return;
         case 'b':
            sb.append('\b');
            return;
         case 'n':
            sb.append('\n');
            return;
         case 'r':
            sb.append('\r');
            return;
         case 't':
            sb.append('\t');
            return;
         default:
            sb.append(n);
      }
   }

   private static boolean allCharsAreHex$1(final String s, final int start, final int length) {
      int end = start + length;

      for(int i = start; i < end; ++i) {
         char c = s.charAt(i);
         boolean cIsHex = c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
         if (!cIsHex) {
            return false;
         }
      }

      return true;
   }

   private static boolean isThreeDigitOctalEscape$1(final String s, final int start) {
      char firstChar = s.charAt(start);
      char secondChar = s.charAt(start + 1);
      char thirdChar = s.charAt(start + 2);
      return (firstChar == '0' || firstChar == '1') && secondChar >= '0' && secondChar <= '7' && thirdChar >= '0' && thirdChar <= '7';
   }

   // $FF: synthetic method
   static int $anonfun$position$1(final Token x$1) {
      return x$1.getLine();
   }

   // $FF: synthetic method
   static int $anonfun$position$2(final Token x$2) {
      return x$2.getCharPositionInLine();
   }

   // $FF: synthetic method
   static int $anonfun$positionAndText$1(final Token x$3) {
      return x$3.getLine();
   }

   // $FF: synthetic method
   static int $anonfun$positionAndText$2(final Token x$4) {
      return x$4.getCharPositionInLine();
   }

   // $FF: synthetic method
   static int $anonfun$positionAndText$3(final Token x$5) {
      return x$5.getStartIndex();
   }

   // $FF: synthetic method
   static int $anonfun$positionAndText$4(final Token x$6) {
      return x$6.getStopIndex();
   }

   static void $init$(final SparkParserUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
