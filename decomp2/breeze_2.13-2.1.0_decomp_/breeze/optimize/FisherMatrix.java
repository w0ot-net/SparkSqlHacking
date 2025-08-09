package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.math.MutableInnerProductVectorSpace;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005}3A!\u0003\u0006\u0001\u001f!Aq\u0003\u0001B\u0001B\u0003%\u0001\u0004\u0003\u00050\u0001\t\u0005\t\u0015a\u00031\u0011\u0015I\u0004\u0001\"\u0001;\u0011\u0015\u0001\u0005\u0001\"\u0001B\u000f\u0015!%\u0002#\u0001F\r\u0015I!\u0002#\u0001G\u0011\u0015Id\u0001\"\u0001H\u0011\u0015Ae\u0001b\u0001J\u000511\u0015n\u001d5fe6\u000bGO]5y\u0015\tYA\"\u0001\u0005paRLW.\u001b>f\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005A13C\u0001\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fM\u0006)qM]1egB\u0019\u0011$\t\u0013\u000f\u0005iybBA\u000e\u001f\u001b\u0005a\"BA\u000f\u000f\u0003\u0019a$o\\8u}%\tA#\u0003\u0002!'\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u0012$\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0006\u0003AM\u0001\"!\n\u0014\r\u0001\u0011)q\u0005\u0001b\u0001Q\t\tA+\u0005\u0002*YA\u0011!CK\u0005\u0003WM\u0011qAT8uQ&tw\r\u0005\u0002\u0013[%\u0011af\u0005\u0002\u0004\u0003:L\u0018A\u0001<t!\u0011\tD\u0007\n\u001c\u000e\u0003IR!a\r\u0007\u0002\t5\fG\u000f[\u0005\u0003kI\u0012a$T;uC\ndW-\u00138oKJ\u0004&o\u001c3vGR4Vm\u0019;peN\u0003\u0018mY3\u0011\u0005I9\u0014B\u0001\u001d\u0014\u0005\u0019!u.\u001e2mK\u00061A(\u001b8jiz\"\"aO \u0015\u0005qr\u0004cA\u001f\u0001I5\t!\u0002C\u00030\u0007\u0001\u000f\u0001\u0007C\u0003\u0018\u0007\u0001\u0007\u0001$\u0001\u0004%i&lWm\u001d\u000b\u0003I\tCQa\u0011\u0003A\u0002\u0011\n\u0011\u0001^\u0001\r\r&\u001c\b.\u001a:NCR\u0014\u0018\u000e\u001f\t\u0003{\u0019\u0019\"AB\t\u0015\u0003\u0015\u000bq\u0001\u001d:pIV\u001cG/F\u0002K9v+\u0012a\u0013\t\u0006\u0019RS6l\u0017\b\u0003\u001bJk\u0011A\u0014\u0006\u0003\u001fB\u000b\u0011b\u001c9fe\u0006$xN]:\u000b\u0005Ec\u0011A\u00027j]\u0006dw-\u0003\u0002T\u001d\u0006Yq\n]'vY6\u000bGO]5y\u0013\t)fKA\u0003J[Bd''\u0003\u0002X1\n)QKR;oG*\u0011\u0011\fD\u0001\bO\u0016tWM]5d!\ri\u0004a\u0017\t\u0003Kq#Qa\n\u0005C\u0002!\"QA\u0018\u0005C\u0002!\u0012\u0011!\u0013"
)
public class FisherMatrix {
   private final IndexedSeq grads;
   private final MutableInnerProductVectorSpace vs;

   public static UFunc.UImpl2 product() {
      return FisherMatrix$.MODULE$.product();
   }

   public Object $times(final Object t) {
      return ((NumericOps)this.vs.hasOps().apply(this.grads.view().map((g) -> ((ImmutableNumericOps)this.vs.hasOps().apply(g)).$times(((ImmutableNumericOps)this.vs.hasOps().apply(g)).dot(t, this.vs.dotVV()), this.vs.mulVS_M())).reduceLeft((x$6, x$7) -> ((NumericOps)this.vs.hasOps().apply(x$6)).$plus$eq(x$7, this.vs.addIntoVV())))).$div$eq(BoxesRunTime.boxToDouble((double)this.grads.length()), this.vs.divIntoVS());
   }

   public FisherMatrix(final IndexedSeq grads, final MutableInnerProductVectorSpace vs) {
      this.grads = grads;
      this.vs = vs;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
