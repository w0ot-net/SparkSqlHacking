package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0019\r\u0001\u0007C\u00033\u0001\u0011\u00053\u0007C\u00039\u0001\u0011\u0005\u0013H\u0001\nGk:\u001cG/[8ocM+W.[4s_V\u0004(BA\u0004\t\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\n\u0015\u000511.\u001a:oK2T\u0011aC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00079q\u0002fE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f\u001835\t\u0001\"\u0003\u0002\u0019\u0011\tI1+Z7jOJ|W\u000f\u001d\t\u0005!iar%\u0003\u0002\u001c#\tIa)\u001e8di&|g.\r\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0011E%\u00111%\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001R%\u0003\u0002'#\t\u0019\u0011I\\=\u0011\u0005uAC!B\u0015\u0001\u0005\u0004\u0001#!\u0001\"\u0002\r\u0011Jg.\u001b;%)\u0005a\u0003C\u0001\t.\u0013\tq\u0013C\u0001\u0003V]&$\u0018!\u0001\"\u0016\u0003E\u00022AF\f(\u0003\u001d\u0019w.\u001c2j]\u0016$2!\u0007\u001b7\u0011\u0015)4\u00011\u0001\u001a\u0003\u0005A\b\"B\u001c\u0004\u0001\u0004I\u0012!A=\u0002!\r|WNY5oK\u0006cGn\u00149uS>tGC\u0001\u001e>!\r\u00012(G\u0005\u0003yE\u0011aa\u00149uS>t\u0007\"\u0002 \u0005\u0001\u0004y\u0014a\u00014ogB\u0019\u0001\tS\r\u000f\u0005\u00053eB\u0001\"F\u001b\u0005\u0019%B\u0001#\r\u0003\u0019a$o\\8u}%\t!#\u0003\u0002H#\u00059\u0001/Y2lC\u001e,\u0017BA%K\u00051IE/\u001a:bE2,wJ\\2f\u0015\t9\u0015\u0003"
)
public interface Function1Semigroup extends Semigroup {
   Semigroup B();

   // $FF: synthetic method
   static Function1 combine$(final Function1Semigroup $this, final Function1 x, final Function1 y) {
      return $this.combine(x, y);
   }

   default Function1 combine(final Function1 x, final Function1 y) {
      return new CombineFunction1(x, y, this.B());
   }

   // $FF: synthetic method
   static Option combineAllOption$(final Function1Semigroup $this, final IterableOnce fns) {
      return $this.combineAllOption(fns);
   }

   default Option combineAllOption(final IterableOnce fns) {
      return (Option)(fns.iterator().isEmpty() ? .MODULE$ : new Some((Function1)(a) -> this.B().combineAllOption(fns.iterator().map((x$1) -> x$1.apply(a))).get()));
   }

   static void $init$(final Function1Semigroup $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
