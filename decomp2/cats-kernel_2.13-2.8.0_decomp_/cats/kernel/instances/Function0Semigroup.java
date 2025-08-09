package cats.kernel.instances;

import cats.kernel.Semigroup;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0019\rQ\u0006C\u00030\u0001\u0011\u0005\u0003\u0007C\u00036\u0001\u0011\u0005cG\u0001\nGk:\u001cG/[8oaM+W.[4s_V\u0004(BA\u0004\t\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\n\u0015\u000511.\u001a:oK2T\u0011aC\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u00059q2c\u0001\u0001\u0010+A\u0011\u0001cE\u0007\u0002#)\t!#A\u0003tG\u0006d\u0017-\u0003\u0002\u0015#\t1\u0011I\\=SK\u001a\u00042AF\f\u001a\u001b\u0005A\u0011B\u0001\r\t\u0005%\u0019V-\\5he>,\b\u000fE\u0002\u00115qI!aG\t\u0003\u0013\u0019+hn\u0019;j_:\u0004\u0004CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"\u0001\u0005\u0012\n\u0005\r\n\"a\u0002(pi\"Lgn\u001a\t\u0003!\u0015J!AJ\t\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u0002SA\u0011\u0001CK\u0005\u0003WE\u0011A!\u00168ji\u0006\t\u0011)F\u0001/!\r1r\u0003H\u0001\bG>l'-\u001b8f)\rI\u0012g\r\u0005\u0006e\r\u0001\r!G\u0001\u0002q\")Ag\u0001a\u00013\u0005\t\u00110\u0001\td_6\u0014\u0017N\\3BY2|\u0005\u000f^5p]R\u0011qG\u000f\t\u0004!aJ\u0012BA\u001d\u0012\u0005\u0019y\u0005\u000f^5p]\")1\b\u0002a\u0001y\u0005\u0019aM\\:\u0011\u0007u*\u0015D\u0004\u0002?\u0007:\u0011qHQ\u0007\u0002\u0001*\u0011\u0011\tD\u0001\u0007yI|w\u000e\u001e \n\u0003II!\u0001R\t\u0002\u000fA\f7m[1hK&\u0011ai\u0012\u0002\r\u0013R,'/\u00192mK>s7-\u001a\u0006\u0003\tF\u0001"
)
public interface Function0Semigroup extends Semigroup {
   Semigroup A();

   // $FF: synthetic method
   static Function0 combine$(final Function0Semigroup $this, final Function0 x, final Function0 y) {
      return $this.combine(x, y);
   }

   default Function0 combine(final Function0 x, final Function0 y) {
      return new CombineFunction0(x, y, this.A());
   }

   // $FF: synthetic method
   static Option combineAllOption$(final Function0Semigroup $this, final IterableOnce fns) {
      return $this.combineAllOption(fns);
   }

   default Option combineAllOption(final IterableOnce fns) {
      return (Option)(fns.iterator().isEmpty() ? .MODULE$ : new Some((Function0)() -> this.A().combineAllOption(fns.iterator().map((x$3) -> x$3.apply())).get()));
   }

   static void $init$(final Function0Semigroup $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
