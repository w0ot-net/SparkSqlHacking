package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.LazyList.Deferrer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001GA\u000eQCJ$\u0018.\u00197Qe\u00164\u0018n\\;t+B\u0004XM\u001d\"pk:$W\r\u001a\u0006\u0003\u000b\u0019\taa[3s]\u0016d'\"A\u0004\u0002\t\r\fGo]\u0002\u0001+\tQqcE\u0003\u0001\u0017E!s\u0005\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VM\u001a\t\u0004%M)R\"\u0001\u0003\n\u0005Q!!a\u0004)beRL\u0017\r\u001c)sKZLw.^:\u0011\u0005Y9B\u0002\u0001\u0003\n1\u0001\u0001\u000b\u0011!AC\u0002e\u0011\u0011!Q\t\u00035u\u0001\"\u0001D\u000e\n\u0005qi!a\u0002(pi\"Lgn\u001a\t\u0003\u0019yI!aH\u0007\u0003\u0007\u0005s\u0017\u0010\u000b\u0002\u0018CA\u0011ABI\u0005\u0003G5\u00111b\u001d9fG&\fG.\u001b>fIB\u0019!#J\u000b\n\u0005\u0019\"!a\u0003)beRL\u0017\r\u001c(fqR\u00042A\u0005\u0015\u0016\u0013\tICA\u0001\u0007VaB,'OQ8v]\u0012,G-\u0001\u0004%S:LG\u000f\n\u000b\u0002YA\u0011A\"L\u0005\u0003]5\u0011A!\u00168ji\u0006\tR.Z7cKJ\u001cH)Z:dK:$\u0017N\\4\u0016\u0003E\u00022AM\u001c\u0016\u001b\u0005\u0019$B\u0001\u001b6\u0003%IW.\\;uC\ndWM\u0003\u00027\u001b\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005a\u001a$\u0001\u0003'bufd\u0015n\u001d;"
)
public interface PartialPreviousUpperBounded extends PartialPrevious, PartialNext, UpperBounded {
   // $FF: synthetic method
   static LazyList membersDescending$(final PartialPreviousUpperBounded $this) {
      return $this.membersDescending();
   }

   default LazyList membersDescending() {
      return .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> this.loop$1(this.maxBound())), () -> this.maxBound());
   }

   private LazyList loop$1(final Object a) {
      Option var3 = this.partialPrevious(a);
      LazyList var2;
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Object aa = var4.value();
         var2 = .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> this.loop$1(aa)), () -> aa);
      } else {
         var2 = scala.collection.immutable.LazyList..MODULE$.empty();
      }

      return var2;
   }

   static void $init$(final PartialPreviousUpperBounded $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
