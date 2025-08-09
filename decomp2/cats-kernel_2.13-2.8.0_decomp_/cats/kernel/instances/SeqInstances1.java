package cats.kernel.instances;

import cats.kernel.Hash;
import cats.kernel.PartialOrder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Qa\u0003\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\u0019!\b\u0005\u0006s\u0001!\u0019A\u000f\u0002\u000e'\u0016\f\u0018J\\:uC:\u001cWm]\u0019\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t'\r\u0001AB\u0005\t\u0003\u001bAi\u0011A\u0004\u0006\u0002\u001f\u0005)1oY1mC&\u0011\u0011C\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005M!R\"A\u0003\n\u0005U)!!D*fc&s7\u000f^1oG\u0016\u001c('\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005I\u0002CA\u0007\u001b\u0013\tYbB\u0001\u0003V]&$\u0018aH2biN\\UM\u001d8fYN#H\rU1si&\fGn\u0014:eKJ4uN]*fcV\u0011a$\f\u000b\u0003?Y\u00022\u0001I\u0011$\u001b\u00059\u0011B\u0001\u0012\b\u00051\u0001\u0016M\u001d;jC2|%\u000fZ3s!\r!\u0013fK\u0007\u0002K)\u0011aeJ\u0001\nS6lW\u000f^1cY\u0016T!\u0001\u000b\b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002+K\t\u00191+Z9\u0011\u00051jC\u0002\u0001\u0003\u0006]\t\u0011\ra\f\u0002\u0002\u0003F\u0011\u0001g\r\t\u0003\u001bEJ!A\r\b\u0003\u000f9{G\u000f[5oOB\u0011Q\u0002N\u0005\u0003k9\u00111!\u00118z\u0011\u001d9$!!AA\u0004a\n!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u0001\u0013eK\u0001\u0018G\u0006$8oS3s]\u0016d7\u000b\u001e3ICNDgi\u001c:TKF,\"aO!\u0015\u0005q\u0012\u0005c\u0001\u0011>\u007f%\u0011ah\u0002\u0002\u0005\u0011\u0006\u001c\b\u000eE\u0002%S\u0001\u0003\"\u0001L!\u0005\u000b9\u001a!\u0019A\u0018\t\u000f\r\u001b\u0011\u0011!a\u0002\t\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\u0007\u0001j\u0004\t"
)
public interface SeqInstances1 extends SeqInstances2 {
   // $FF: synthetic method
   static PartialOrder catsKernelStdPartialOrderForSeq$(final SeqInstances1 $this, final PartialOrder evidence$2) {
      return $this.catsKernelStdPartialOrderForSeq(evidence$2);
   }

   default PartialOrder catsKernelStdPartialOrderForSeq(final PartialOrder evidence$2) {
      return new SeqPartialOrder(evidence$2);
   }

   // $FF: synthetic method
   static Hash catsKernelStdHashForSeq$(final SeqInstances1 $this, final Hash evidence$3) {
      return $this.catsKernelStdHashForSeq(evidence$3);
   }

   default Hash catsKernelStdHashForSeq(final Hash evidence$3) {
      return new SeqHash(evidence$3);
   }

   static void $init$(final SeqInstances1 $this) {
   }
}
