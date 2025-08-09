package scala.collection.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:Q!\u0003\u0006\t\u0002E1Qa\u0005\u0006\t\u0002QAQ!G\u0001\u0005\u0002iAqaG\u0001C\u0002\u0013\u0005A\u0004\u0003\u0004!\u0003\u0001\u0006I!\b\u0005\bC\u0005\u0011\r\u0011\"\u0001#\u0011\u0019y\u0013\u0001)A\u0005G!9\u0001'\u0001b\u0001\n\u0003\t\u0004B\u0002\u001c\u0002A\u0003%!'A\u000bGkR,(/\u001a+ie\u0016\fG\rU8pYR\u000b7o[:\u000b\u0005-a\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u00055q\u0011AC2pY2,7\r^5p]*\tq\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0011\u0005I\tQ\"\u0001\u0006\u0003+\u0019+H/\u001e:f)\"\u0014X-\u00193Q_>dG+Y:lgN\u0011\u0011!\u0006\t\u0003-]i\u0011AD\u0005\u000319\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0012\u0003!qW/\\\"pe\u0016\u001cX#A\u000f\u0011\u0005Yq\u0012BA\u0010\u000f\u0005\rIe\u000e^\u0001\n]Vl7i\u001c:fg\u0002\na\u0001^2pk:$X#A\u0012\u0011\u0005\u0011jS\"A\u0013\u000b\u0005\u0019:\u0013AB1u_6L7M\u0003\u0002)S\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005)Z\u0013\u0001B;uS2T\u0011\u0001L\u0001\u0005U\u00064\u0018-\u0003\u0002/K\tQ\u0011\t^8nS\u000eduN\\4\u0002\u000fQ\u001cw.\u001e8uA\u0005\tB-\u001a4bk2$H\u000b\u001b:fC\u0012\u0004vn\u001c7\u0016\u0003I\u0002\"a\r\u001b\u000e\u0003\u001dJ!!N\u0014\u0003\u001f\u0015CXmY;u_J\u001cVM\u001d<jG\u0016\f!\u0003Z3gCVdG\u000f\u00165sK\u0006$\u0007k\\8mA\u0001"
)
public final class FutureThreadPoolTasks {
   public static ExecutorService defaultThreadPool() {
      return FutureThreadPoolTasks$.MODULE$.defaultThreadPool();
   }

   public static AtomicLong tcount() {
      return FutureThreadPoolTasks$.MODULE$.tcount();
   }

   public static int numCores() {
      return FutureThreadPoolTasks$.MODULE$.numCores();
   }
}
