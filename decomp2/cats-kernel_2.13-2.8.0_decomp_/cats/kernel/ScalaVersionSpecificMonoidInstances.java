package cats.kernel;

import cats.kernel.instances.stream.package$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0005\u0006\u007f\u0001!\u0019\u0001\u0011\u0005\u0006\u0011\u0002!\u0019!\u0013\u0002$'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2N_:|\u0017\u000eZ%ogR\fgnY3t\u0015\t9\u0001\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0013\u0005!1-\u0019;t'\t\u00011\u0002\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tA\u0003\u0005\u0002\r+%\u0011a#\u0004\u0002\u0005+:LG/A\rdCR\u001c8*\u001a:oK2luN\\8jI\u001a{'o\u0015;sK\u0006lWCA\r-+\u0005Q\u0002cA\u000e\u001d=5\ta!\u0003\u0002\u001e\r\t1Qj\u001c8pS\u0012\u00042aH\u0014+\u001d\t\u0001SE\u0004\u0002\"I5\t!E\u0003\u0002$%\u00051AH]8pizJ\u0011AD\u0005\u0003M5\tq\u0001]1dW\u0006<W-\u0003\u0002)S\t11\u000b\u001e:fC6T!AJ\u0007\u0011\u0005-bC\u0002\u0001\u0003\u0006[\t\u0011\rA\f\u0002\u0002\u0003F\u0011qF\r\t\u0003\u0019AJ!!M\u0007\u0003\u000f9{G\u000f[5oOB\u0011AbM\u0005\u0003i5\u00111!\u00118zQ\u0019\u0011a'\u000f\u001e={A\u0011AbN\u0005\u0003q5\u0011!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013aO\u0001 +N,\u0007eY1ug.+'O\\3m\u001b>tw.\u001b3G_Jd\u0015M_=MSN$\u0018!B:j]\u000e,\u0017%\u0001 \u0002\u000bMr\u0003G\f\u0019\u00027\r\fGo]&fe:,G.T8o_&$gi\u001c:MCjLH*[:u+\t\tu)F\u0001C!\rYBd\u0011\t\u0004?\u00113\u0015BA#*\u0005!a\u0015M_=MSN$\bCA\u0016H\t\u0015i3A1\u0001/\u0003m\u0019\u0017\r^:LKJtW\r\\'p]>LGMR8s\u0003J\u0014\u0018-_*fcV\u0011!*V\u000b\u0002\u0017B\u00191\u0004\b'\u0011\u00075\u0013F+D\u0001O\u0015\ty\u0005+A\u0005j[6,H/\u00192mK*\u0011\u0011+D\u0001\u000bG>dG.Z2uS>t\u0017BA*O\u0005!\t%O]1z'\u0016\f\bCA\u0016V\t\u0015iCA1\u0001/\u0001"
)
public interface ScalaVersionSpecificMonoidInstances {
   // $FF: synthetic method
   static Monoid catsKernelMonoidForStream$(final ScalaVersionSpecificMonoidInstances $this) {
      return $this.catsKernelMonoidForStream();
   }

   /** @deprecated */
   default Monoid catsKernelMonoidForStream() {
      return package$.MODULE$.catsKernelStdMonoidForStream();
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForLazyList$(final ScalaVersionSpecificMonoidInstances $this) {
      return $this.catsKernelMonoidForLazyList();
   }

   default Monoid catsKernelMonoidForLazyList() {
      return cats.kernel.instances.lazyList.package$.MODULE$.catsKernelStdMonoidForLazyList();
   }

   // $FF: synthetic method
   static Monoid catsKernelMonoidForArraySeq$(final ScalaVersionSpecificMonoidInstances $this) {
      return $this.catsKernelMonoidForArraySeq();
   }

   default Monoid catsKernelMonoidForArraySeq() {
      return cats.kernel.instances.arraySeq.package$.MODULE$.catsKernelStdMonoidForArraySeq();
   }

   static void $init$(final ScalaVersionSpecificMonoidInstances $this) {
   }
}
