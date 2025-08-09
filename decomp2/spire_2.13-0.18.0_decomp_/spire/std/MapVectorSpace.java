package spire.std;

import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.ObjectRef;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005i3A!\u0002\u0004\u0001\u0017!AA\b\u0001BC\u0002\u0013\rS\b\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003?\u0011\u0015I\u0005\u0001\"\u0001K\u0011\u0015q\u0005\u0001\"\u0011P\u00059i\u0015\r\u001d,fGR|'o\u00159bG\u0016T!a\u0002\u0005\u0002\u0007M$HMC\u0001\n\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)2\u0001D\n!'\u0011\u0001QBI\u001a\u0011\t9y\u0011cH\u0007\u0002\r%\u0011\u0001C\u0002\u0002\b\u001b\u0006\u00048I\u00158h!\t\u00112\u0003\u0004\u0001\u0005\u000bQ\u0001!\u0019A\u000b\u0003\u0003-\u000b\"A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u000f9{G\u000f[5oOB\u0011q#H\u0005\u0003=a\u00111!\u00118z!\t\u0011\u0002\u0005B\u0003\"\u0001\t\u0007QCA\u0001W!\u0011\u0019c\u0005K\u0010\u000e\u0003\u0011R!!\n\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011q\u0005\n\u0002\f-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0003*aEybB\u0001\u0016/!\tY\u0003$D\u0001-\u0015\ti#\"\u0001\u0004=e>|GOP\u0005\u0003_a\ta\u0001\u0015:fI\u00164\u0017BA\u00193\u0005\ri\u0015\r\u001d\u0006\u0003_a\u0001\"\u0001N\u001d\u000f\u0005U:dBA\u00167\u0013\u0005I\u0012B\u0001\u001d\u0019\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005aB\u0012AB:dC2\f'/F\u0001?!\ryTi\b\b\u0003\u0001\u0012s!!Q\"\u000f\u0005-\u0012\u0015\"A\u0005\n\u0005\u0015B\u0011B\u0001\u001d%\u0013\t1uIA\u0003GS\u0016dGM\u0003\u00029I\u000591oY1mCJ\u0004\u0013A\u0002\u001fj]&$h\bF\u0001L)\taU\n\u0005\u0003\u000f\u0001Ey\u0002\"\u0002\u001f\u0004\u0001\bq\u0014!\u0002;j[\u0016\u001cHc\u0001\u0015Q%\")\u0011\u000b\u0002a\u0001Q\u0005\t\u0001\u0010C\u0003T\t\u0001\u0007\u0001&A\u0001zQ\u0011\u0001Q\u000bW-\u0011\u0005]1\u0016BA,\u0019\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0001\u0001"
)
public class MapVectorSpace extends MapCRng implements VectorSpace {
   private static final long serialVersionUID = 0L;
   private final Field scalar;

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
      return this.scalar;
   }

   public Map times(final Map x, final Map y) {
      ObjectRef xx = ObjectRef.create(x);
      Map yy = y;
      if (x.size() < y.size()) {
         xx.elem = y;
         yy = x;
      }

      return (Map)yy.foldLeft(this.zero(), (z, kv) -> (Map)((Map)xx.elem).get(kv._1()).map((u) -> (Map)z.updated(kv._1(), this.scalar().times(u, kv._2()))).getOrElse(() -> z));
   }

   public MapVectorSpace(final Field scalar) {
      super(scalar);
      this.scalar = scalar;
      VectorSpace.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
