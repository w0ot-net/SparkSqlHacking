package spire.math;

import algebra.ring.EuclideanRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000553\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005\u0001B\u0003\u0005\u0006Y\u0001!\t!\f\u0005\u0006c\u00011\tA\r\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0002\u0015'\u000e\fG.Y%oi\u0016<'/\u00197Xe\u0006\u0004\b/\u001a:\u000b\u0005\u001dA\u0011\u0001B7bi\"T\u0011!C\u0001\u0006gBL'/Z\u000b\u0003\u0017i\u0019B\u0001\u0001\u0007\u0015OA\u0011QBE\u0007\u0002\u001d)\u0011q\u0002E\u0001\u0005Y\u0006twMC\u0001\u0012\u0003\u0011Q\u0017M^1\n\u0005Mq!AB(cU\u0016\u001cG\u000fE\u0002\u0016-ai\u0011AB\u0005\u0003/\u0019\u00111cU2bY\u0006tU/\\3sS\u000e<&/\u00199qKJ\u0004\"!\u0007\u000e\r\u0001\u0011)1\u0004\u0001b\u0001;\t\t\u0011i\u0001\u0001\u0012\u0005y!\u0003CA\u0010#\u001b\u0005\u0001#\"A\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\r\u0002#a\u0002(pi\"Lgn\u001a\t\u0003?\u0015J!A\n\u0011\u0003\u0007\u0005s\u0017\u0010E\u0002)Uai\u0011!\u000b\u0006\u0003\u000f\u0001J!aK\u0015\u0003\u0011%sG/Z4sC2\fa\u0001J5oSR$C#\u0001\u0018\u0011\u0005}y\u0013B\u0001\u0019!\u0005\u0011)f.\u001b;\u0002\u0013M$(/^2ukJ,W#A\u001a\u0011\u0007Q\u0002\u0005D\u0004\u00026{9\u0011ag\u000f\b\u0003oij\u0011\u0001\u000f\u0006\u0003sq\ta\u0001\u0010:p_Rt\u0014\"A\u0005\n\u0005qB\u0011aB1mO\u0016\u0014'/Y\u0005\u0003}}\nq\u0001]1dW\u0006<WM\u0003\u0002=\u0011%\u0011\u0011I\u0011\u0002\u000e\u000bV\u001cG.\u001b3fC:\u0014\u0016N\\4\u000b\u0005yz\u0014\u0001B9v_R$2\u0001G#H\u0011\u001515\u00011\u0001\u0019\u0003\u0005A\b\"\u0002%\u0004\u0001\u0004A\u0012!A=\u0002\u0007I,W\u000eF\u0002\u0019\u00172CQA\u0012\u0003A\u0002aAQ\u0001\u0013\u0003A\u0002a\u0001"
)
public interface ScalaIntegralWrapper extends ScalaNumericWrapper, scala.math.Integral {
   EuclideanRing structure();

   // $FF: synthetic method
   static Object quot$(final ScalaIntegralWrapper $this, final Object x, final Object y) {
      return $this.quot(x, y);
   }

   default Object quot(final Object x, final Object y) {
      return this.structure().equot(x, y);
   }

   // $FF: synthetic method
   static Object rem$(final ScalaIntegralWrapper $this, final Object x, final Object y) {
      return $this.rem(x, y);
   }

   default Object rem(final Object x, final Object y) {
      return this.structure().emod(x, y);
   }

   static void $init$(final ScalaIntegralWrapper $this) {
   }
}
