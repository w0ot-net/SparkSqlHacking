package cats.kernel;

import cats.kernel.instances.function.package$;
import scala.concurrent.ExecutionContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i4\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006'\u0001!\t!\u0006\u0005\u00063\u0001!\u0019A\u0007\u0005\u0006c\u0001!\u0019A\r\u0005\u0006\u0001\u0002!\u0019!\u0011\u0005\u0006/\u0002!\u0019\u0001\u0017\u0005\u0006M\u0002!\u0019a\u001a\u0002\u0013'\u0016l\u0017n\u001a:pkBLen\u001d;b]\u000e,7O\u0003\u0002\n\u0015\u000511.\u001a:oK2T\u0011aC\u0001\u0005G\u0006$8o\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002-A\u0011abF\u0005\u00031=\u0011A!\u00168ji\u0006y2-\u0019;t\u0017\u0016\u0014h.\u001a7TK6LwM]8va\u001a{'OR;oGRLwN\u001c\u0019\u0016\u0005m)CC\u0001\u000f/!\rib\u0004I\u0007\u0002\u0011%\u0011q\u0004\u0003\u0002\n'\u0016l\u0017n\u001a:pkB\u00042AD\u0011$\u0013\t\u0011sBA\u0005Gk:\u001cG/[8oaA\u0011A%\n\u0007\u0001\t\u00151#A1\u0001(\u0005\u0005\t\u0015C\u0001\u0015,!\tq\u0011&\u0003\u0002+\u001f\t9aj\u001c;iS:<\u0007C\u0001\b-\u0013\tisBA\u0002B]fDqa\f\u0002\u0002\u0002\u0003\u000f\u0001'A\u0006fm&$WM\\2fIM\u0002\u0004cA\u000f\u001fG\u0005y2-\u0019;t\u0017\u0016\u0014h.\u001a7TK6LwM]8va\u001a{'OR;oGRLwN\\\u0019\u0016\u0007MJ4\b\u0006\u00025{A\u0019QDH\u001b\u0011\t91\u0004HO\u0005\u0003o=\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005\u0011JD!\u0002\u0014\u0004\u0005\u00049\u0003C\u0001\u0013<\t\u0015a4A1\u0001(\u0005\u0005\u0011\u0005b\u0002 \u0004\u0003\u0003\u0005\u001daP\u0001\fKZLG-\u001a8dK\u0012\u001a\u0014\u0007E\u0002\u001e=i\nAdY1ug.+'O\\3m'\u0016l\u0017n\u001a:pkB4uN]#ji\",'/F\u0002C#N#\"a\u0011+\u0011\u0007uqB\t\u0005\u0003F\u001bB\u0013fB\u0001$L\u001d\t9%*D\u0001I\u0015\tIE#\u0001\u0004=e>|GOP\u0005\u0002!%\u0011AjD\u0001\ba\u0006\u001c7.Y4f\u0013\tquJ\u0001\u0004FSRDWM\u001d\u0006\u0003\u0019>\u0001\"\u0001J)\u0005\u000b\u0019\"!\u0019A\u0014\u0011\u0005\u0011\u001aF!\u0002\u001f\u0005\u0005\u00049\u0003bB+\u0005\u0003\u0003\u0005\u001dAV\u0001\fKZLG-\u001a8dK\u0012\u001a$\u0007E\u0002\u001e=I\u000b\u0011dY1ug.+'O\\3m'\u0016l\u0017n\u001a:pkB4uN\u001d+ssV\u0011\u0011L\u0019\u000b\u00035\u000e\u00042!\b\u0010\\!\rav,Y\u0007\u0002;*\u0011alD\u0001\u0005kRLG.\u0003\u0002a;\n\u0019AK]=\u0011\u0005\u0011\u0012G!\u0002\u0014\u0006\u0005\u00049\u0003b\u00023\u0006\u0003\u0003\u0005\u001d!Z\u0001\fKZLG-\u001a8dK\u0012\u001a4\u0007E\u0002\u001e=\u0005\fAdY1ug.+'O\\3m'\u0016l\u0017n\u001a:pkB4uN\u001d$viV\u0014X-\u0006\u0002icR\u0019\u0011N];\u0011\u0007uq\"\u000eE\u0002l]Bl\u0011\u0001\u001c\u0006\u0003[>\t!bY8oGV\u0014(/\u001a8u\u0013\tyGN\u0001\u0004GkR,(/\u001a\t\u0003IE$QA\n\u0004C\u0002\u001dBQa\u001d\u0004A\u0004Q\f\u0011!\u0011\t\u0004;y\u0001\b\"\u0002<\u0007\u0001\b9\u0018AA3d!\tY\u00070\u0003\u0002zY\n\u0001R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e"
)
public interface SemigroupInstances {
   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForFunction0$(final SemigroupInstances $this, final Semigroup evidence$30) {
      return $this.catsKernelSemigroupForFunction0(evidence$30);
   }

   default Semigroup catsKernelSemigroupForFunction0(final Semigroup evidence$30) {
      return package$.MODULE$.catsKernelSemigroupForFunction0(evidence$30);
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForFunction1$(final SemigroupInstances $this, final Semigroup evidence$31) {
      return $this.catsKernelSemigroupForFunction1(evidence$31);
   }

   default Semigroup catsKernelSemigroupForFunction1(final Semigroup evidence$31) {
      return package$.MODULE$.catsKernelSemigroupForFunction1(evidence$31);
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForEither$(final SemigroupInstances $this, final Semigroup evidence$32) {
      return $this.catsKernelSemigroupForEither(evidence$32);
   }

   default Semigroup catsKernelSemigroupForEither(final Semigroup evidence$32) {
      return cats.kernel.instances.either.package$.MODULE$.catsDataSemigroupForEither(evidence$32);
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForTry$(final SemigroupInstances $this, final Semigroup evidence$33) {
      return $this.catsKernelSemigroupForTry(evidence$33);
   }

   default Semigroup catsKernelSemigroupForTry(final Semigroup evidence$33) {
      return new TrySemigroup(Semigroup$.MODULE$.apply(evidence$33));
   }

   // $FF: synthetic method
   static Semigroup catsKernelSemigroupForFuture$(final SemigroupInstances $this, final Semigroup A, final ExecutionContext ec) {
      return $this.catsKernelSemigroupForFuture(A, ec);
   }

   default Semigroup catsKernelSemigroupForFuture(final Semigroup A, final ExecutionContext ec) {
      return new FutureSemigroup(A, ec);
   }

   static void $init$(final SemigroupInstances $this) {
   }
}
