package cats.kernel.instances;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0011\ra\u0004C\u00036\u0001\u0011\ra\u0007C\u0003D\u0001\u0011\rAIA\bPaRLwN\\%ogR\fgnY3t\u0015\t9\u0001\"A\u0005j]N$\u0018M\\2fg*\u0011\u0011BC\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003-\tAaY1ug\u000e\u00011c\u0001\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"!\u0006\f\u000e\u0003\u0019I!a\u0006\u0004\u0003!=\u0003H/[8o\u0013:\u001cH/\u00198dKN\u0004\u0014A\u0002\u0013j]&$H\u0005F\u0001\u001b!\ty1$\u0003\u0002\u001d!\t!QK\\5u\u0003m\u0019\u0017\r^:LKJtW\r\\*uI>\u0013H-\u001a:G_J|\u0005\u000f^5p]V\u0011q$\u000b\u000b\u0003AI\u00022!\t\u0012%\u001b\u0005A\u0011BA\u0012\t\u0005\u0015y%\u000fZ3s!\ryQeJ\u0005\u0003MA\u0011aa\u00149uS>t\u0007C\u0001\u0015*\u0019\u0001!QA\u000b\u0002C\u0002-\u0012\u0011!Q\t\u0003Y=\u0002\"aD\u0017\n\u00059\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001fAJ!!\r\t\u0003\u0007\u0005s\u0017\u0010C\u00044\u0005\u0005\u0005\t9\u0001\u001b\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002\"E\u001d\nqeY1ug.+'O\\3m'R$7i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3G_J|\u0005\u000f^5p]V\u0011q'\u0010\u000b\u0003qy\u00022!I\u001d<\u0013\tQ\u0004BA\tD_6lW\u000f^1uSZ,Wj\u001c8pS\u0012\u00042aD\u0013=!\tAS\bB\u0003+\u0007\t\u00071\u0006C\u0004@\u0007\u0005\u0005\t9\u0001!\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002\"\u0003rJ!A\u0011\u0005\u0003)\r{W.\\;uCRLg/Z*f[&<'o\\;q\u0003q\u0019\u0017\r^:LKJtW\r\\*uI6{gn\\5e\r>\u0014x\n\u001d;j_:,\"!R&\u0015\u0005\u0019c\u0005cA\u0011H\u0013&\u0011\u0001\n\u0003\u0002\u0007\u001b>tw.\u001b3\u0011\u0007=)#\n\u0005\u0002)\u0017\u0012)!\u0006\u0002b\u0001W!9Q\nBA\u0001\u0002\bq\u0015AC3wS\u0012,gnY3%gA\u0019\u0011e\u0014&\n\u0005AC!!C*f[&<'o\\;q\u0001"
)
public interface OptionInstances extends OptionInstances0 {
   // $FF: synthetic method
   static Order catsKernelStdOrderForOption$(final OptionInstances $this, final Order evidence$1) {
      return $this.catsKernelStdOrderForOption(evidence$1);
   }

   default Order catsKernelStdOrderForOption(final Order evidence$1) {
      return new OptionOrder(evidence$1);
   }

   // $FF: synthetic method
   static CommutativeMonoid catsKernelStdCommutativeMonoidForOption$(final OptionInstances $this, final CommutativeSemigroup evidence$2) {
      return $this.catsKernelStdCommutativeMonoidForOption(evidence$2);
   }

   default CommutativeMonoid catsKernelStdCommutativeMonoidForOption(final CommutativeSemigroup evidence$2) {
      return new OptionCommutativeMonoid(evidence$2);
   }

   // $FF: synthetic method
   static Monoid catsKernelStdMonoidForOption$(final OptionInstances $this, final Semigroup evidence$3) {
      return $this.catsKernelStdMonoidForOption(evidence$3);
   }

   default Monoid catsKernelStdMonoidForOption(final Semigroup evidence$3) {
      return new OptionMonoid(evidence$3);
   }

   static void $init$(final OptionInstances $this) {
   }
}
