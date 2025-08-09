package cats.kernel.instances;

import cats.kernel.BoundedSemilattice;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f\u001d\u0002!\u0019!C\u0002Q\tiQK\\5u\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\n\u0003\u00015\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0016!\tqa#\u0003\u0002\u0018\u001f\t!QK\\5u\u0003e\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_J,f.\u001b;\u0016\u0003i\u0011BaG\u000f\"I\u0019!A\u0004\u0001\u0001\u001b\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rqr$F\u0007\u0002\u000f%\u0011\u0001e\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0004=\t*\u0012BA\u0012\b\u0005\u0011A\u0015m\u001d5\u0011\u0007y)S#\u0003\u0002'\u000f\t\t\"i\\;oI\u0016$WI\\;nKJ\f'\r\\3\u00027\r\fGo]&fe:,Gn\u0015;e\u00032<WM\u0019:b\r>\u0014XK\\5u+\u0005I#c\u0001\u0016,]\u0019!A\u0004\u0001\u0001*!\rqB&F\u0005\u0003[\u001d\u0011!CQ8v]\u0012,GmU3nS2\fG\u000f^5dKB\u0019adL\u000b\n\u0005A:!\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;qQ\t\u0001!\u0007\u0005\u00024u9\u0011Ag\u000e\b\u0003=UJ!AN\u0004\u0002\r\r|W\u000e]1u\u0013\tA\u0014(\u0001\u000btG\u0006d\u0017MV3sg&|gn\u00159fG&4\u0017n\u0019\u0006\u0003m\u001dI!a\u000f\u001f\u0003eM,\b\u000f\u001d:fgN,f.^:fI&k\u0007o\u001c:u/\u0006\u0014h.\u001b8h\r>\u00148kY1mCZ+'o]5p]N\u0003XmY5gS\u000eT!\u0001O\u001d"
)
public interface UnitInstances {
   void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdOrderForUnit_$eq(final Order x$1);

   void cats$kernel$instances$UnitInstances$_setter_$catsKernelStdAlgebraForUnit_$eq(final BoundedSemilattice x$1);

   Order catsKernelStdOrderForUnit();

   BoundedSemilattice catsKernelStdAlgebraForUnit();

   static void $init$(final UnitInstances $this) {
      $this.cats$kernel$instances$UnitInstances$_setter_$catsKernelStdOrderForUnit_$eq(new UnitOrder());
      $this.cats$kernel$instances$UnitInstances$_setter_$catsKernelStdAlgebraForUnit_$eq(new UnitAlgebra());
   }
}
