package scala.util.parsing.combinator.syntactical;

import java.lang.invoke.SerializedLambda;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.parsing.combinator.Parsers;
import scala.util.parsing.combinator.token.StdTokens;
import scala.util.parsing.combinator.token.Tokens;

@ScalaSignature(
   bytes = "\u0006\u0005a3q\u0001C\u0005\u0011\u0002\u0007\u0005A\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004B\u0003#\u0001\t\u00051\u0005C\u0004.\u0001\t\u0007I\u0011\u0003\u0018\t\u000b\u0015\u0003A1\u0001$\t\u000bQ\u0003A\u0011A+\t\u000bY\u0003A\u0011A+\t\u000b]\u0003A\u0011A+\u0003\u001fM#H\rV8lK:\u0004\u0016M]:feNT!AC\u0006\u0002\u0017MLh\u000e^1di&\u001c\u0017\r\u001c\u0006\u0003\u00195\t!bY8nE&t\u0017\r^8s\u0015\tqq\"A\u0004qCJ\u001c\u0018N\\4\u000b\u0005A\t\u0012\u0001B;uS2T\u0011AE\u0001\u0006g\u000e\fG.Y\u0002\u0001'\r\u0001Q#\u0007\t\u0003-]i\u0011!E\u0005\u00031E\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001c\u001b\u0005I\u0011B\u0001\u000f\n\u00051!vn[3o!\u0006\u00148/\u001a:t\u0003\u0019!\u0013N\\5uIQ\tq\u0004\u0005\u0002\u0017A%\u0011\u0011%\u0005\u0002\u0005+:LGO\u0001\u0004U_.,gn]\t\u0003I\u001d\u0002\"AF\u0013\n\u0005\u0019\n\"a\u0002(pi\"Lgn\u001a\t\u0003Q-j\u0011!\u000b\u0006\u0003U-\tQ\u0001^8lK:L!\u0001L\u0015\u0003\u0013M#H\rV8lK:\u001c\u0018\u0001D6fs^|'\u000fZ\"bG\",W#A\u0018\u0011\tA*tgP\u0007\u0002c)\u0011!gM\u0001\b[V$\u0018M\u00197f\u0015\t!\u0014#\u0001\u0006d_2dWm\u0019;j_:L!AN\u0019\u0003\u000f!\u000b7\u000f['baB\u0011\u0001(P\u0007\u0002s)\u0011!hO\u0001\u0005Y\u0006twMC\u0001=\u0003\u0011Q\u0017M^1\n\u0005yJ$AB*ue&tw\rE\u0002A\u0003^j\u0011\u0001A\u0005\u0003\u0005\u000e\u0013a\u0001U1sg\u0016\u0014\u0018B\u0001#\f\u0005\u001d\u0001\u0016M]:feN\fqa[3zo>\u0014H\r\u0006\u0002H%B\u0019\u0001)\u0011%\u0011\u0005%\u0003fB\u0001&O!\tY\u0015#D\u0001M\u0015\ti5#\u0001\u0004=e>|GOP\u0005\u0003\u001fF\ta\u0001\u0015:fI\u00164\u0017B\u0001 R\u0015\ty\u0015\u0003C\u0003T\t\u0001\u0007\u0001*A\u0003dQ\u0006\u00148/\u0001\u0006ok6,'/[2MSR,\u0012aR\u0001\ngR\u0014\u0018N\\4MSR\fQ!\u001b3f]R\u0004"
)
public interface StdTokenParsers extends TokenParsers {
   void scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq(final HashMap x$1);

   HashMap keywordCache();

   // $FF: synthetic method
   static Parsers.Parser keyword$(final StdTokenParsers $this, final String chars) {
      return $this.keyword(chars);
   }

   default Parsers.Parser keyword(final String chars) {
      return (Parsers.Parser)this.keywordCache().getOrElseUpdate(chars, () -> this.accept((StdTokens)this.lexical().new Keyword(chars)).$up$up((x$1) -> x$1.chars()));
   }

   // $FF: synthetic method
   static Parsers.Parser numericLit$(final StdTokenParsers $this) {
      return $this.numericLit();
   }

   default Parsers.Parser numericLit() {
      return this.elem("number", (x$2) -> BoxesRunTime.boxToBoolean($anonfun$numericLit$1(x$2))).$up$up((x$3) -> x$3.chars());
   }

   // $FF: synthetic method
   static Parsers.Parser stringLit$(final StdTokenParsers $this) {
      return $this.stringLit();
   }

   default Parsers.Parser stringLit() {
      return this.elem("string literal", (x$4) -> BoxesRunTime.boxToBoolean($anonfun$stringLit$1(x$4))).$up$up((x$5) -> x$5.chars());
   }

   // $FF: synthetic method
   static Parsers.Parser ident$(final StdTokenParsers $this) {
      return $this.ident();
   }

   default Parsers.Parser ident() {
      return this.elem("identifier", (x$6) -> BoxesRunTime.boxToBoolean($anonfun$ident$1(x$6))).$up$up((x$7) -> x$7.chars());
   }

   // $FF: synthetic method
   static boolean $anonfun$numericLit$1(final Tokens.Token x$2) {
      return x$2 instanceof StdTokens.NumericLit;
   }

   // $FF: synthetic method
   static boolean $anonfun$stringLit$1(final Tokens.Token x$4) {
      return x$4 instanceof StdTokens.StringLit;
   }

   // $FF: synthetic method
   static boolean $anonfun$ident$1(final Tokens.Token x$6) {
      return x$6 instanceof StdTokens.Identifier;
   }

   static void $init$(final StdTokenParsers $this) {
      $this.scala$util$parsing$combinator$syntactical$StdTokenParsers$_setter_$keywordCache_$eq((HashMap).MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
