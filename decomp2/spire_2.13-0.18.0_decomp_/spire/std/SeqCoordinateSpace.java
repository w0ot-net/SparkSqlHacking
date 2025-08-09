package spire.std;

import algebra.ring.Field;
import scala.collection.Factory;
import scala.collection.SeqOps;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import spire.algebra.CoordinateSpace;

@ScalaSignature(
   bytes = "\u0006\u0005I4A!\u0003\u0006\u0001\u001f!A!\t\u0001BC\u0002\u0013\u00051\t\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003E\u0011!A\u0005AaA!\u0002\u0017I\u0005\u0002C*\u0001\u0005\u0003\u0005\u000b1\u0002+\t\u000b]\u0003A\u0011\u0001-\t\u000by\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011I3\t\u000b%\u0004A\u0011\u00016\u0003%M+\u0017oQ8pe\u0012Lg.\u0019;f'B\f7-\u001a\u0006\u0003\u00171\t1a\u001d;e\u0015\u0005i\u0011!B:qSJ,7\u0001A\u000b\u0004!]!3\u0003\u0002\u0001\u0012aY\u0002BAE\n\u0016G5\t!\"\u0003\u0002\u0015\u0015\t!2+Z9J]:,'\u000f\u0015:pIV\u001cGo\u00159bG\u0016\u0004\"AF\f\r\u0001\u0011)\u0001\u0004\u0001b\u00013\t\t\u0011)\u0005\u0002\u001bAA\u00111DH\u0007\u00029)\tQ$A\u0003tG\u0006d\u0017-\u0003\u0002 9\t9aj\u001c;iS:<\u0007CA\u000e\"\u0013\t\u0011CDA\u0002B]f\u0004\"A\u0006\u0013\u0005\u000b\u0015\u0002!\u0019\u0001\u0014\u0003\u0005M\u000b\u0015C\u0001\u000e(!\u0015A3&F\u0017$\u001b\u0005I#B\u0001\u0016\u001d\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Y%\u0012aaU3r\u001fB\u001c\bC\u0001\u0015/\u0013\ty\u0013FA\u0002TKF\u0004B!\r\u001b$+5\t!G\u0003\u00024\u0019\u00059\u0011\r\\4fEJ\f\u0017BA\u001b3\u0005=\u0019un\u001c:eS:\fG/Z*qC\u000e,\u0007CA\u001c@\u001d\tATH\u0004\u0002:y5\t!H\u0003\u0002<\u001d\u00051AH]8pizJ\u0011!H\u0005\u0003}q\tq\u0001]1dW\u0006<W-\u0003\u0002A\u0003\na1+\u001a:jC2L'0\u00192mK*\u0011a\bH\u0001\u000bI&lWM\\:j_:\u001cX#\u0001#\u0011\u0005m)\u0015B\u0001$\u001d\u0005\rIe\u000e^\u0001\fI&lWM\\:j_:\u001c\b%\u0001\u0006fm&$WM\\2fII\u00022A\u0013)\u0016\u001d\tYuJ\u0004\u0002M\u001d:\u0011\u0011(T\u0005\u0002\u001b%\u00111\u0007D\u0005\u0003}IJ!!\u0015*\u0003\u000b\u0019KW\r\u001c3\u000b\u0005y\u0012\u0014aA2cMB!\u0001&V\u000b$\u0013\t1\u0016FA\u0004GC\u000e$xN]=\u0002\rqJg.\u001b;?)\tIV\fF\u0002[7r\u0003BA\u0005\u0001\u0016G!)\u0001*\u0002a\u0002\u0013\")1+\u0002a\u0002)\")!)\u0002a\u0001\t\u0006)1m\\8sIR\u0019Q\u0003\u00192\t\u000b\u00054\u0001\u0019A\u0012\u0002\u0003YDQa\u0019\u0004A\u0002\u0011\u000b\u0011![\u0001\u0004I>$HcA\u000bgO\")\u0011m\u0002a\u0001G!)\u0001n\u0002a\u0001G\u0005\tq/\u0001\u0003bq&\u001cHCA\u0012l\u0011\u0015\u0019\u0007\u00021\u0001EQ\u0011\u0001Q\u000e]9\u0011\u0005mq\u0017BA8\u001d\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class SeqCoordinateSpace extends SeqInnerProductSpace implements CoordinateSpace {
   private static final long serialVersionUID = 0L;
   private final int dimensions;
   private final Factory cbf;

   public double coord$mcD$sp(final Object v, final int i) {
      return CoordinateSpace.coord$mcD$sp$(this, v, i);
   }

   public float coord$mcF$sp(final Object v, final int i) {
      return CoordinateSpace.coord$mcF$sp$(this, v, i);
   }

   public Object _x(final Object v) {
      return CoordinateSpace._x$(this, v);
   }

   public double _x$mcD$sp(final Object v) {
      return CoordinateSpace._x$mcD$sp$(this, v);
   }

   public float _x$mcF$sp(final Object v) {
      return CoordinateSpace._x$mcF$sp$(this, v);
   }

   public Object _y(final Object v) {
      return CoordinateSpace._y$(this, v);
   }

   public double _y$mcD$sp(final Object v) {
      return CoordinateSpace._y$mcD$sp$(this, v);
   }

   public float _y$mcF$sp(final Object v) {
      return CoordinateSpace._y$mcF$sp$(this, v);
   }

   public Object _z(final Object v) {
      return CoordinateSpace._z$(this, v);
   }

   public double _z$mcD$sp(final Object v) {
      return CoordinateSpace._z$mcD$sp$(this, v);
   }

   public float _z$mcF$sp(final Object v) {
      return CoordinateSpace._z$mcF$sp$(this, v);
   }

   public Vector basis() {
      return CoordinateSpace.basis$(this);
   }

   public double dot$mcD$sp(final Object v, final Object w) {
      return CoordinateSpace.dot$mcD$sp$(this, v, w);
   }

   public float dot$mcF$sp(final Object v, final Object w) {
      return CoordinateSpace.dot$mcF$sp$(this, v, w);
   }

   public int dimensions() {
      return this.dimensions;
   }

   public Object coord(final SeqOps v, final int i) {
      return v.apply(i);
   }

   public Object dot(final SeqOps v, final SeqOps w) {
      return super.dot(v, w);
   }

   public SeqOps axis(final int i) {
      Builder b = this.cbf.newBuilder();
      return this.loop$2(0, i, b);
   }

   private final SeqOps loop$2(final int j, final int i$1, final Builder b$1) {
      while(i$1 < this.dimensions()) {
         b$1.$plus$eq(i$1 == j ? this.scalar().one() : this.scalar().zero());
         ++j;
      }

      return (SeqOps)b$1.result();
   }

   public SeqCoordinateSpace(final int dimensions, final Field evidence$2, final Factory cbf) {
      super(evidence$2, cbf);
      this.dimensions = dimensions;
      this.cbf = cbf;
      CoordinateSpace.$init$(this);
   }
}
