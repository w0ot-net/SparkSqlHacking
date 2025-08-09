package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f)\u0002!\u0019!C\u0002W\tiAj\u001c8h\u0013:\u001cH/\u00198dKNT!AB\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\n\u0003\u00015\u0001\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0016!\tqa#\u0003\u0002\u0018\u001f\t!QK\\5u\u0003e\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_JduN\\4\u0016\u0003i\u0011BaG\u000f%O\u0019!A\u0004\u0001\u0001\u001b\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rqr$I\u0007\u0002\u000f%\u0011\u0001e\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0003\u001d\tJ!aI\b\u0003\t1{gn\u001a\t\u0004=\u0015\n\u0013B\u0001\u0014\b\u0005\u0011A\u0015m\u001d5\u0011\u0007yA\u0013%\u0003\u0002*\u000f\t\t\"i\\;oI\u0016$WI\\;nKJ\f'\r\\3\u00023\r\fGo]&fe:,Gn\u0015;e\u000fJ|W\u000f\u001d$pe2{gnZ\u000b\u0002YA\u0019a$L\u0011\n\u00059:!\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;q\u0001"
)
public interface LongInstances {
   void cats$kernel$instances$LongInstances$_setter_$catsKernelStdOrderForLong_$eq(final Order x$1);

   void cats$kernel$instances$LongInstances$_setter_$catsKernelStdGroupForLong_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForLong();

   CommutativeGroup catsKernelStdGroupForLong();

   static void $init$(final LongInstances $this) {
      $this.cats$kernel$instances$LongInstances$_setter_$catsKernelStdOrderForLong_$eq(new LongOrder());
      $this.cats$kernel$instances$LongInstances$_setter_$catsKernelStdGroupForLong_$eq(new LongGroup());
   }
}
