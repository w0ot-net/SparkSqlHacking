package spire.std;

import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import spire.algebra.InnerProductSpace;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005e3A\u0001B\u0003\u0001\u0015!I1\b\u0001B\u0002B\u0003-AH\u0012\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u0002!\tA\u0014\u0002\u0015\u001b\u0006\u0004\u0018J\u001c8feB\u0013x\u000eZ;diN\u0003\u0018mY3\u000b\u0005\u00199\u0011aA:uI*\t\u0001\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0007-\u0011rd\u0005\u0003\u0001\u0019\u0005\u0012\u0004\u0003B\u0007\u000f!yi\u0011!B\u0005\u0003\u001f\u0015\u0011a\"T1q-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0002\u0012%1\u0001A!B\n\u0001\u0005\u0004!\"!A&\u0012\u0005UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"a\u0002(pi\"Lgn\u001a\t\u0003-qI!!H\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0012?\u0011)\u0001\u0005\u0001b\u0001)\t\ta\u000b\u0005\u0003#K\u001drR\"A\u0012\u000b\u0005\u0011:\u0011aB1mO\u0016\u0014'/Y\u0005\u0003M\r\u0012\u0011#\u00138oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f!\u0011As\u0006\u0005\u0010\u000f\u0005%j\u0003C\u0001\u0016\u0018\u001b\u0005Y#B\u0001\u0017\n\u0003\u0019a$o\\8u}%\u0011afF\u0001\u0007!J,G-\u001a4\n\u0005A\n$aA'ba*\u0011af\u0006\t\u0003gar!\u0001\u000e\u001c\u000f\u0005)*\u0014\"\u0001\r\n\u0005]:\u0012a\u00029bG.\fw-Z\u0005\u0003si\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aN\f\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002>\u0007zq!A\u0010\"\u000f\u0005}\neB\u0001\u0016A\u0013\u0005A\u0011B\u0001\u0013\b\u0013\t94%\u0003\u0002E\u000b\n)a)[3mI*\u0011qgI\u0005\u0003\u000f:\taa]2bY\u0006\u0014\u0018A\u0002\u001fj]&$h\bF\u0001K)\tYE\n\u0005\u0003\u000e\u0001Aq\u0002\"B\u001e\u0003\u0001\ba\u0014a\u00013piR\u0019adT)\t\u000bA\u001b\u0001\u0019A\u0014\u0002\u0003aDQAU\u0002A\u0002\u001d\n\u0011!\u001f\u0015\u0005\u0001Q;\u0006\f\u0005\u0002\u0017+&\u0011ak\u0006\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001"
)
public class MapInnerProductSpace extends MapVectorSpace implements InnerProductSpace {
   private static final long serialVersionUID = 0L;

   public double dot$mcD$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcD$sp$(this, v, w);
   }

   public float dot$mcF$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcF$sp$(this, v, w);
   }

   public int dot$mcI$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcI$sp$(this, v, w);
   }

   public long dot$mcJ$sp(final Object v, final Object w) {
      return InnerProductSpace.dot$mcJ$sp$(this, v, w);
   }

   public NormedVectorSpace normed(final NRoot ev) {
      return InnerProductSpace.normed$(this, ev);
   }

   public NormedVectorSpace normed$mcD$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcD$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcF$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcF$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcI$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcI$sp$(this, ev);
   }

   public NormedVectorSpace normed$mcJ$sp(final NRoot ev) {
      return InnerProductSpace.normed$mcJ$sp$(this, ev);
   }

   public Object dot(final Map x, final Map y) {
      return this.times(x, y).foldLeft(this.scalar().zero(), (a, b) -> this.scalar().plus(a, b._2()));
   }

   public MapInnerProductSpace(final Field evidence$1) {
      super(evidence$1);
      InnerProductSpace.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
