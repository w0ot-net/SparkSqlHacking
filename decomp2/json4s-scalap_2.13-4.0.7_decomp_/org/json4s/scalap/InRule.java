package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005M3AAB\u0004\u0001\u001d!Aa\u0003\u0001B\u0001B\u0003%q\u0003C\u00030\u0001\u0011\u0005\u0001\u0007C\u00034\u0001\u0011\u0005A\u0007C\u0003K\u0001\u0011\u00051\nC\u0003Q\u0001\u0011\u0005\u0011K\u0001\u0004J]J+H.\u001a\u0006\u0003\u0011%\taa]2bY\u0006\u0004(B\u0001\u0006\f\u0003\u0019Q7o\u001c85g*\tA\"A\u0002pe\u001e\u001c\u0001!F\u0003\u0010;\u001dRSf\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fAA];mKB1\u0001$G\u000e'S1j\u0011aB\u0005\u00035\u001d\u0011AAU;mKB\u0011A$\b\u0007\u0001\t\u0015q\u0002A1\u0001 \u0005\tIe.\u0005\u0002!GA\u0011\u0011#I\u0005\u0003EI\u0011qAT8uQ&tw\r\u0005\u0002\u0012I%\u0011QE\u0005\u0002\u0004\u0003:L\bC\u0001\u000f(\t\u0019A\u0003\u0001\"b\u0001?\t\u0019q*\u001e;\u0011\u0005qQCAB\u0016\u0001\t\u000b\u0007qDA\u0001B!\taR\u0006\u0002\u0004/\u0001\u0011\u0015\ra\b\u0002\u00021\u00061A(\u001b8jiz\"\"!\r\u001a\u0011\ra\u00011DJ\u0015-\u0011\u00151\"\u00011\u0001\u0018\u0003\u001di\u0017\r\u001d*vY\u0016,B!\u000e\u001d<}Q\u0011a\u0007\u0011\t\u00071eYrGO\u001f\u0011\u0005qAD!B\u001d\u0004\u0005\u0004y\"\u0001B(viJ\u0002\"\u0001H\u001e\u0005\u000bq\u001a!\u0019A\u0010\u0003\u0003\t\u0003\"\u0001\b \u0005\u000b}\u001a!\u0019A\u0010\u0003\u0003eCQ!Q\u0002A\u0002\t\u000b\u0011A\u001a\t\u0005#\r+\u0005*\u0003\u0002E%\tIa)\u001e8di&|g.\r\t\u00061\u00193\u0013\u0006L\u0005\u0003\u000f\u001e\u0011aAU3tk2$\b\u0003B\tD7%\u0003R\u0001\u0007$8uu\n1\"\u001e8bef|FEY1oOV\tA\n\u0005\u0004\u00193mYR\n\t\t\u0003#9K!a\u0014\n\u0003\tUs\u0017\u000e^\u0001\u0005I\u0005l\u0007/F\u0001S!\u0019A\u0012dG\u000e*Y\u0001"
)
public class InRule {
   private final Rule rule;

   public Rule mapRule(final Function1 f) {
      return this.rule.factory().rule((in) -> (Result)((Function1)f.apply(this.rule.apply(in))).apply(in));
   }

   public Rule unary_$bang() {
      return this.mapRule((x0$1) -> {
         Function1 var1;
         if (x0$1 instanceof Success) {
            var1 = (in) -> Failure$.MODULE$;
         } else {
            var1 = (in) -> new Success(in, BoxedUnit.UNIT);
         }

         return var1;
      });
   }

   public Rule $amp() {
      return this.mapRule((x0$1) -> {
         Function1 var1;
         if (x0$1 instanceof Success) {
            Success var3 = (Success)x0$1;
            Object a = var3.value();
            var1 = (in) -> new Success(in, a);
         } else if (Failure$.MODULE$.equals(x0$1)) {
            var1 = (in) -> Failure$.MODULE$;
         } else {
            if (!(x0$1 instanceof Error)) {
               throw new MatchError(x0$1);
            }

            Error var5 = (Error)x0$1;
            Object x = var5.error();
            var1 = (in) -> new Error(x);
         }

         return var1;
      });
   }

   public InRule(final Rule rule) {
      this.rule = rule;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
