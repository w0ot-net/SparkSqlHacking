package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000fI\u0002!\u0019!C\u0002g\t9b)\u001b8ji\u0016$UO]1uS>t\u0017J\\:uC:\u001cWm\u001d\u0006\u0003\r\u001d\t\u0011\"\u001b8ti\u0006t7-Z:\u000b\u0005!I\u0011AB6fe:,GNC\u0001\u000b\u0003\u0011\u0019\u0017\r^:\u0004\u0001M\u0011\u0001!\u0004\t\u0003\u001dEi\u0011a\u0004\u0006\u0002!\u0005)1oY1mC&\u0011!c\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005)\u0002C\u0001\b\u0017\u0013\t9rB\u0001\u0003V]&$\u0018aI2biN\\UM\u001d8fYN#Hm\u0014:eKJ4uN\u001d$j]&$X\rR;sCRLwN\\\u000b\u00025I)1$H\u0015-_\u0019!A\u0004\u0001\u0001\u001b\u00051a$/\u001a4j]\u0016lWM\u001c;?!\rqr$I\u0007\u0002\u000f%\u0011\u0001e\u0002\u0002\u0006\u001fJ$WM\u001d\t\u0003E\u001dj\u0011a\t\u0006\u0003I\u0015\n\u0001\u0002Z;sCRLwN\u001c\u0006\u0003M=\t!bY8oGV\u0014(/\u001a8u\u0013\tA3E\u0001\bGS:LG/\u001a#ve\u0006$\u0018n\u001c8\u0011\u0007yQ\u0013%\u0003\u0002,\u000f\t!\u0001*Y:i!\rqR&I\u0005\u0003]\u001d\u0011A\u0002T8xKJ\u0014u.\u001e8eK\u0012\u00042A\b\u0019\"\u0013\t\ttA\u0001\u0007VaB,'OQ8v]\u0012,G-A\u0012dCR\u001c8*\u001a:oK2\u001cF\u000fZ$s_V\u0004hi\u001c:GS:LG/\u001a#ve\u0006$\u0018n\u001c8\u0016\u0003Q\u00022AH\u001b\"\u0013\t1tA\u0001\tD_6lW\u000f^1uSZ,wI]8va\u0002"
)
public interface FiniteDurationInstances {
   void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdOrderForFiniteDuration_$eq(final Order x$1);

   void cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdGroupForFiniteDuration_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForFiniteDuration();

   CommutativeGroup catsKernelStdGroupForFiniteDuration();

   static void $init$(final FiniteDurationInstances $this) {
      $this.cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdOrderForFiniteDuration_$eq(new FiniteDurationOrder());
      $this.cats$kernel$instances$FiniteDurationInstances$_setter_$catsKernelStdGroupForFiniteDuration_$eq(new FiniteDurationGroup());
   }
}
