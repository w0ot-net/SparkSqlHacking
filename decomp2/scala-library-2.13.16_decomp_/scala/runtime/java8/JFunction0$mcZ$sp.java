package scala.runtime.java8;

import java.io.Serializable;
import scala.Function0;
import scala.reflect.ScalaSignature;

@FunctionalInterface
@ScalaSignature(
   bytes = "\u0006\u0005a2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019\u0005\u0011\u0006C\u0003.\u0001\u0011\u0005cFA\tK\rVt7\r^5p]B\"Sn\u0019.%gBT!AB\u0004\u0002\u000b)\fg/\u0019\u001d\u000b\u0005!I\u0011a\u0002:v]RLW.\u001a\u0006\u0002\u0015\u0005)1oY1mC\u000e\u00011\u0003\u0002\u0001\u000e#]\u0001\"AD\b\u000e\u0003%I!\u0001E\u0005\u0003\r\u0005s\u0017PU3g!\rq!\u0003F\u0005\u0003'%\u0011\u0011BR;oGRLwN\u001c\u0019\u0011\u00059)\u0012B\u0001\f\n\u0005\r\te.\u001f\t\u00031\u0001r!!\u0007\u0010\u000f\u0005iiR\"A\u000e\u000b\u0005qY\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\ty\u0012\"A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0005\u0012#\u0001D*fe&\fG.\u001b>bE2,'BA\u0010\n\u0003\u0019!\u0013N\\5uIQ\tQ\u0005\u0005\u0002\u000fM%\u0011q%\u0003\u0002\u0005+:LG/\u0001\u0007baBd\u0017\u0010J7d5\u0012\u001a\b\u000fF\u0001+!\tq1&\u0003\u0002-\u0013\t9!i\\8mK\u0006t\u0017!B1qa2LH#\u0001\u000b)\u0005\u0001\u0001\u0004CA\u00197\u001b\u0005\u0011$BA\u001a5\u0003\u0011a\u0017M\\4\u000b\u0003U\nAA[1wC&\u0011qG\r\u0002\u0014\rVt7\r^5p]\u0006d\u0017J\u001c;fe\u001a\f7-\u001a"
)
public interface JFunction0$mcZ$sp extends Function0, Serializable {
   boolean apply$mcZ$sp();

   // $FF: synthetic method
   static Object apply$(final JFunction0$mcZ$sp $this) {
      return $this.apply();
   }

   default Object apply() {
      return this.apply$mcZ$sp();
   }

   static void $init$(final JFunction0$mcZ$sp $this) {
   }
}
