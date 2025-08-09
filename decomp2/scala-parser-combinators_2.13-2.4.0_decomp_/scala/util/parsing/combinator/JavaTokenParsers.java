package scala.util.parsing.combinator;

import java.lang.invoke.SerializedLambda;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y2qa\u0002\u0005\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001b\u0001\u0011\u00051\u0004C\u0003 \u0001\u0011\u0005\u0001\u0005C\u00033\u0001\u0011\u0005\u0001\u0005C\u00034\u0001\u0011\u0005\u0001\u0005C\u00035\u0001\u0011\u0005\u0001\u0005C\u00036\u0001\u0011\u0005\u0001E\u0001\tKCZ\fGk\\6f]B\u000b'o]3sg*\u0011\u0011BC\u0001\u000bG>l'-\u001b8bi>\u0014(BA\u0006\r\u0003\u001d\u0001\u0018M]:j]\u001eT!!\u0004\b\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001f\u0005)1oY1mC\u000e\u00011c\u0001\u0001\u0013-A\u00111\u0003F\u0007\u0002\u001d%\u0011QC\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]AR\"\u0001\u0005\n\u0005eA!\u0001\u0004*fO\u0016D\b+\u0019:tKJ\u001c\u0018A\u0002\u0013j]&$H\u0005F\u0001\u001d!\t\u0019R$\u0003\u0002\u001f\u001d\t!QK\\5u\u0003\u0015IG-\u001a8u+\u0005\t\u0003c\u0001\u0012$O5\t\u0001!\u0003\u0002%K\t1\u0001+\u0019:tKJL!A\n\u0005\u0003\u000fA\u000b'o]3sgB\u0011\u0001f\f\b\u0003S5\u0002\"A\u000b\b\u000e\u0003-R!\u0001\f\t\u0002\rq\u0012xn\u001c;?\u0013\tqc\"\u0001\u0004Qe\u0016$WMZ\u0005\u0003aE\u0012aa\u0015;sS:<'B\u0001\u0018\u000f\u0003-9\bn\u001c7f\u001dVl'-\u001a:\u0002\u001b\u0011,7-[7bY:+XNY3s\u00035\u0019HO]5oO2KG/\u001a:bY\u0006\u0019b\r\\8bi&tw\rU8j]RtU/\u001c2fe\u0002"
)
public interface JavaTokenParsers extends RegexParsers {
   // $FF: synthetic method
   static Parsers.Parser ident$(final JavaTokenParsers $this) {
      return $this.ident();
   }

   default Parsers.Parser ident() {
      return this.literal("").$tilde$greater(() -> this.rep1(() -> this.acceptIf((x$1) -> BoxesRunTime.boxToBoolean($anonfun$ident$3(BoxesRunTime.unboxToChar(x$1))), (x$1) -> $anonfun$ident$4(BoxesRunTime.unboxToChar(x$1))), () -> this.elem("identifier part", (x$2) -> BoxesRunTime.boxToBoolean($anonfun$ident$6(BoxesRunTime.unboxToChar(x$2)))))).$up$up((x$3) -> x$3.mkString());
   }

   // $FF: synthetic method
   static Parsers.Parser wholeNumber$(final JavaTokenParsers $this) {
      return $this.wholeNumber();
   }

   default Parsers.Parser wholeNumber() {
      return this.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("-?\\d+")));
   }

   // $FF: synthetic method
   static Parsers.Parser decimalNumber$(final JavaTokenParsers $this) {
      return $this.decimalNumber();
   }

   default Parsers.Parser decimalNumber() {
      return this.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("(\\d+(\\.\\d*)?|\\d*\\.\\d+)")));
   }

   // $FF: synthetic method
   static Parsers.Parser stringLiteral$(final JavaTokenParsers $this) {
      return $this.stringLiteral();
   }

   default Parsers.Parser stringLiteral() {
      return this.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\"([^\"\\x00-\\x1F\\x7F\\\\]|\\\\[\\\\'\"bfnrt]|\\\\u[a-fA-F0-9]{4})*\"")));
   }

   // $FF: synthetic method
   static Parsers.Parser floatingPointNumber$(final JavaTokenParsers $this) {
      return $this.floatingPointNumber();
   }

   default Parsers.Parser floatingPointNumber() {
      return this.regex(.MODULE$.r$extension(scala.Predef..MODULE$.augmentString("-?(\\d+(\\.\\d*)?|\\d*\\.\\d+)([eE][+-]?\\d+)?[fFdD]?")));
   }

   // $FF: synthetic method
   static boolean $anonfun$ident$3(final char x$1) {
      return Character.isJavaIdentifierStart(x$1);
   }

   // $FF: synthetic method
   static String $anonfun$ident$4(final char x$1) {
      return (new StringBuilder(32)).append("identifier expected but '").append(x$1).append("' found").toString();
   }

   // $FF: synthetic method
   static boolean $anonfun$ident$6(final char x$2) {
      return Character.isJavaIdentifierPart(x$2);
   }

   static void $init$(final JavaTokenParsers $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
