package cats.kernel;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Some;
import scala.collection.immutable.LazyList;
import scala.collection.immutable.LazyList.Deferrer.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003+\u0001\u0011\u00051\u0006C\u00030\u0001\u0011\u0005\u0001GA\fQCJ$\u0018.\u00197OKb$Hj\\<fe\n{WO\u001c3fI*\u0011QAB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003\u001d\tAaY1ug\u000e\u0001QC\u0001\u0006\u0018'\u0015\u00011\"\u0005\u0013(!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fMB\u0019!cE\u000b\u000e\u0003\u0011I!\u0001\u0006\u0003\u0003\u001fA\u000b'\u000f^5bYB\u0013XM^5pkN\u0004\"AF\f\r\u0001\u0011I\u0001\u0004\u0001Q\u0001\u0002\u0003\u0015\r!\u0007\u0002\u0002\u0003F\u0011!$\b\t\u0003\u0019mI!\u0001H\u0007\u0003\u000f9{G\u000f[5oOB\u0011ABH\u0005\u0003?5\u00111!\u00118zQ\t9\u0012\u0005\u0005\u0002\rE%\u00111%\u0004\u0002\fgB,7-[1mSj,G\rE\u0002\u0013KUI!A\n\u0003\u0003\u0017A\u000b'\u000f^5bY:+\u0007\u0010\u001e\t\u0004%!*\u0012BA\u0015\u0005\u00051aun^3s\u0005>,h\u000eZ3e\u0003\u0019!\u0013N\\5uIQ\tA\u0006\u0005\u0002\r[%\u0011a&\u0004\u0002\u0005+:LG/\u0001\tnK6\u0014WM]:Bg\u000e,g\u000eZ5oOV\t\u0011\u0007E\u00023oUi\u0011a\r\u0006\u0003iU\n\u0011\"[7nkR\f'\r\\3\u000b\u0005Yj\u0011AC2pY2,7\r^5p]&\u0011\u0001h\r\u0002\t\u0019\u0006T\u0018\u0010T5ti\u0002"
)
public interface PartialNextLowerBounded extends PartialPrevious, PartialNext, LowerBounded {
   // $FF: synthetic method
   static LazyList membersAscending$(final PartialNextLowerBounded $this) {
      return $this.membersAscending();
   }

   default LazyList membersAscending() {
      return .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> this.loop$2(this.minBound())), () -> this.minBound());
   }

   private LazyList loop$2(final Object a) {
      Option var3 = this.partialNext(a);
      LazyList var2;
      if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         Object aa = var4.value();
         var2 = .MODULE$.$hash$colon$colon$extension(scala.collection.immutable.LazyList..MODULE$.toDeferrer(() -> this.loop$2(aa)), () -> aa);
      } else {
         var2 = scala.collection.immutable.LazyList..MODULE$.empty();
      }

      return var2;
   }

   static void $init$(final PartialNextLowerBounded $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
