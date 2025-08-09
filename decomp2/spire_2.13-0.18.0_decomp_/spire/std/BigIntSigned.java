package spire.std;

import algebra.ring.Signed;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003/\u0001\u0011\u0005q\u0006C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005s\u0007C\u0003>\u0001\u0011\u0005cH\u0001\u0007CS\u001eLe\u000e^*jO:,GM\u0003\u0002\b\u0011\u0005\u00191\u000f\u001e3\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0003\u0001\u0019IQ\u0003CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\rE\u0002\u0014?\tr!\u0001\u0006\u000f\u000f\u0005UQbB\u0001\f\u001a\u001b\u00059\"B\u0001\r\u000b\u0003\u0019a$o\\8u}%\t\u0011\"\u0003\u0002\u001c\u0011\u00059\u0011\r\\4fEJ\f\u0017BA\u000f\u001f\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u0007\u0005\n\u0005\u0001\n#AB*jO:,GM\u0003\u0002\u001e=A\u00111e\n\b\u0003I\u0019r!AF\u0013\n\u0003=I!!\b\b\n\u0005!J#A\u0002\"jO&sGO\u0003\u0002\u001e\u001dA\u00111\u0006L\u0007\u0002\r%\u0011QF\u0002\u0002\f\u0005&<\u0017J\u001c;Pe\u0012,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002aA\u0011Q\"M\u0005\u0003e9\u0011A!\u00168ji\u0006)qN\u001d3feV\tQ\u0007\u0005\u0002,\u0001\u000511/[4ok6$\"\u0001O\u001e\u0011\u00055I\u0014B\u0001\u001e\u000f\u0005\rIe\u000e\u001e\u0005\u0006y\r\u0001\rAI\u0001\u0002C\u0006\u0019\u0011MY:\u0015\u0005\tz\u0004\"\u0002\u001f\u0005\u0001\u0004\u0011\u0003"
)
public interface BigIntSigned extends Signed, BigIntOrder {
   // $FF: synthetic method
   static BigIntSigned order$(final BigIntSigned $this) {
      return $this.order();
   }

   default BigIntSigned order() {
      return this;
   }

   // $FF: synthetic method
   static int signum$(final BigIntSigned $this, final BigInt a) {
      return $this.signum(a);
   }

   default int signum(final BigInt a) {
      return a.signum();
   }

   // $FF: synthetic method
   static BigInt abs$(final BigIntSigned $this, final BigInt a) {
      return $this.abs(a);
   }

   default BigInt abs(final BigInt a) {
      return a.abs();
   }

   static void $init$(final BigIntSigned $this) {
   }
}
