package spire.std;

import algebra.ring.Field;
import scala.collection.Factory;
import scala.reflect.ScalaSignature;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005q3A!\u0002\u0004\u0001\u0017!Aa\b\u0001BC\u0002\u0013\rs\bC\u0005K\u0001\t\u0005\t\u0015!\u0003A\u0017\"AA\n\u0001B\u0001B\u0003-Q\nC\u0003Q\u0001\u0011\u0005\u0011K\u0001\bTKF4Vm\u0019;peN\u0003\u0018mY3\u000b\u0005\u001dA\u0011aA:uI*\t\u0011\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00071\u0019\u0002e\u0005\u0003\u0001\u001b1\u0012\u0004\u0003\u0002\b\u0010#}i\u0011AB\u0005\u0003!\u0019\u0011!bU3r\u00076{G-\u001e7f!\t\u00112\u0003\u0004\u0001\u0005\u000bQ\u0001!\u0019A\u000b\u0003\u0003\u0005\u000b\"A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u000f9{G\u000f[5oOB\u0011q#H\u0005\u0003=a\u00111!\u00118z!\t\u0011\u0002\u0005B\u0003\"\u0001\t\u0007!E\u0001\u0002T\u0003F\u0011ac\t\t\u0006I\u001d\n\u0012fH\u0007\u0002K)\u0011a\u0005G\u0001\u000bG>dG.Z2uS>t\u0017B\u0001\u0015&\u0005\u0019\u0019V-](qgB\u0011AEK\u0005\u0003W\u0015\u00121aU3r!\u0011i\u0003gH\t\u000e\u00039R!a\f\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011G\f\u0002\f-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u00024w9\u0011A'\u000f\b\u0003kaj\u0011A\u000e\u0006\u0003o)\ta\u0001\u0010:p_Rt\u0014\"A\r\n\u0005iB\u0012a\u00029bG.\fw-Z\u0005\u0003yu\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\u000f\r\u0002\rM\u001c\u0017\r\\1s+\u0005\u0001\u0005cA!H#9\u0011!I\u0012\b\u0003\u0007\u0016s!!\u000e#\n\u0003%I!a\f\u0005\n\u0005ir\u0013B\u0001%J\u0005\u00151\u0015.\u001a7e\u0015\tQd&A\u0004tG\u0006d\u0017M\u001d\u0011\n\u0005yz\u0011aA2cMB!AET\t \u0013\tyUEA\u0004GC\u000e$xN]=\u0002\rqJg.\u001b;?)\u0005\u0011FcA*U+B!a\u0002A\t \u0011\u0015qD\u0001q\u0001A\u0011\u0015aE\u0001q\u0001NQ\u0011\u0001qKW.\u0011\u0005]A\u0016BA-\u0019\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class SeqVectorSpace extends SeqCModule implements VectorSpace {
   private static final long serialVersionUID = 0L;

   public Field scalar$mcD$sp() {
      return VectorSpace.scalar$mcD$sp$(this);
   }

   public Field scalar$mcF$sp() {
      return VectorSpace.scalar$mcF$sp$(this);
   }

   public Field scalar$mcI$sp() {
      return VectorSpace.scalar$mcI$sp$(this);
   }

   public Field scalar$mcJ$sp() {
      return VectorSpace.scalar$mcJ$sp$(this);
   }

   public Object divr(final Object v, final Object f) {
      return VectorSpace.divr$(this, v, f);
   }

   public Object divr$mcD$sp(final Object v, final double f) {
      return VectorSpace.divr$mcD$sp$(this, v, f);
   }

   public Object divr$mcF$sp(final Object v, final float f) {
      return VectorSpace.divr$mcF$sp$(this, v, f);
   }

   public Object divr$mcI$sp(final Object v, final int f) {
      return VectorSpace.divr$mcI$sp$(this, v, f);
   }

   public Object divr$mcJ$sp(final Object v, final long f) {
      return VectorSpace.divr$mcJ$sp$(this, v, f);
   }

   public Field scalar() {
      return (Field)super.scalar();
   }

   public SeqVectorSpace(final Field scalar, final Factory cbf) {
      super(scalar, cbf);
      VectorSpace.$init$(this);
   }
}
