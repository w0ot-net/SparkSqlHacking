package spire.std;

import algebra.ring.Field;
import scala.collection.Factory;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import spire.algebra.InnerProductSpace;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005}3A!\u0002\u0004\u0001\u0017!Aa\b\u0001B\u0002B\u0003-q\b\u0003\u0005J\u0001\t\u0005\t\u0015a\u0003K\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u0015\u0019\u0006\u0001\"\u0001U\u0005Q\u0019V-]%o]\u0016\u0014\bK]8ek\u000e$8\u000b]1dK*\u0011q\u0001C\u0001\u0004gR$'\"A\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0019Ab\u0005\u0011\u0014\t\u0001iAF\r\t\u0005\u001d=\tr$D\u0001\u0007\u0013\t\u0001bA\u0001\bTKF4Vm\u0019;peN\u0003\u0018mY3\u0011\u0005I\u0019B\u0002\u0001\u0003\u0006)\u0001\u0011\r!\u0006\u0002\u0002\u0003F\u0011a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\b\u001d>$\b.\u001b8h!\t9R$\u0003\u0002\u001f1\t\u0019\u0011I\\=\u0011\u0005I\u0001C!B\u0011\u0001\u0005\u0004\u0011#AA*B#\t12\u0005E\u0003%OEIs$D\u0001&\u0015\t1\u0003$\u0001\u0006d_2dWm\u0019;j_:L!\u0001K\u0013\u0003\rM+\u0017o\u00149t!\t!#&\u0003\u0002,K\t\u00191+Z9\u0011\t5\u0002t$E\u0007\u0002])\u0011q\u0006C\u0001\bC2<WM\u0019:b\u0013\t\tdFA\tJ]:,'\u000f\u0015:pIV\u001cGo\u00159bG\u0016\u0004\"aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c\u000b\u0003\u0019a$o\\8u}%\t\u0011$\u0003\u0002;1\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001f>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQ\u0004$\u0001\u0006fm&$WM\\2fIE\u00022\u0001\u0011$\u0012\u001d\t\tUI\u0004\u0002C\t:\u0011QgQ\u0005\u0002\u0013%\u0011q\u0006C\u0005\u0003u9J!a\u0012%\u0003\u000b\u0019KW\r\u001c3\u000b\u0005ir\u0013aA2cMB!AeS\t \u0013\taUEA\u0004GC\u000e$xN]=\u0002\rqJg.\u001b;?)\u0005yEc\u0001)R%B!a\u0002A\t \u0011\u0015q4\u0001q\u0001@\u0011\u0015I5\u0001q\u0001K\u0003\r!w\u000e\u001e\u000b\u0004#U;\u0006\"\u0002,\u0005\u0001\u0004y\u0012!\u0001=\t\u000ba#\u0001\u0019A\u0010\u0002\u0003eDC\u0001\u0001.^=B\u0011qcW\u0005\u00039b\u0011\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0001\u0001"
)
public class SeqInnerProductSpace extends SeqVectorSpace implements InnerProductSpace {
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

   public Object dot(final SeqOps x, final SeqOps y) {
      return this.loop$1(x.iterator(), y.iterator(), this.scalar().zero());
   }

   private final Object loop$1(final Iterator xi, final Iterator yi, final Object acc) {
      while(xi.hasNext() && yi.hasNext()) {
         acc = this.scalar().plus(acc, this.scalar().times(xi.next(), yi.next()));
         yi = yi;
         xi = xi;
      }

      return acc;
   }

   public SeqInnerProductSpace(final Field evidence$1, final Factory cbf) {
      super(evidence$1, cbf);
      InnerProductSpace.$init$(this);
   }
}
