package spire.algebra;

import algebra.ring.Field;
import java.lang.invoke.SerializedLambda;
import scala.collection.Factory;
import scala.collection.immutable.Vector;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.std.SeqCoordinateSpace;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ufaB\t\u0013!\u0003\r\ta\u0006\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\ta\u0012\u0005\u0006\u0017\u00021\t\u0001\u0014\u0005\u0006#\u00021\tA\u0015\u0005\u0006)\u0002!\t!\u0016\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00065\u0002!\ta\u0017\u0005\u0006;\u0002!\tA\u0018\u0005\u0006M\u0002!\taZ\u0004\u0006WJA\t\u0001\u001c\u0004\u0006#IA\t!\u001c\u0005\u0006s.!\tA\u001f\u0005\u0006w.!)\u0001 \u0005\b\u0003;YA\u0011AA\u0010\u0011\u001d\t)h\u0003C\u0001\u0003oB\u0011\"!,\f\u0003\u0003%I!a,\u0003\u001f\r{wN\u001d3j]\u0006$Xm\u00159bG\u0016T!a\u0005\u000b\u0002\u000f\u0005dw-\u001a2sC*\tQ#A\u0003ta&\u0014Xm\u0001\u0001\u0016\u0007a)CfE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u00111!\u00118z!\u0011\u0001\u0013eI\u0016\u000e\u0003II!A\t\n\u0003#%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW\r\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u00049#!\u0001,\u0012\u0005!J\u0002C\u0001\u000e*\u0013\tQ3DA\u0004O_RD\u0017N\\4\u0011\u0005\u0011bC!C\u0017\u0001A\u0003\u0005\tQ1\u0001(\u0005\u00051\u0005\u0006\u0002\u00170eq\u0002\"A\u0007\u0019\n\u0005EZ\"aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI\u001a5mUr!A\u0007\u001b\n\u0005UZ\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u00138wqq!\u0001O\u001e\u000e\u0003eR!A\u000f\f\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0012'B\u0012>}\u0001{dB\u0001\u000e?\u0013\ty4$\u0001\u0004E_V\u0014G.Z\u0019\u0005I]ZD$\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0007B\u0011!\u0004R\u0005\u0003\u000bn\u0011A!\u00168ji\u0006QA-[7f]NLwN\\:\u0016\u0003!\u0003\"AG%\n\u0005)[\"aA%oi\u0006)1m\\8sIR\u00191&T(\t\u000b9\u001b\u0001\u0019A\u0012\u0002\u0003YDQ\u0001U\u0002A\u0002!\u000b\u0011![\u0001\u0005CbL7\u000f\u0006\u0002$'\")\u0001\u000b\u0002a\u0001\u0011\u0006\u0011q\f\u001f\u000b\u0003WYCQAT\u0003A\u0002\r\n!aX=\u0015\u0005-J\u0006\"\u0002(\u0007\u0001\u0004\u0019\u0013AA0{)\tYC\fC\u0003O\u000f\u0001\u00071%A\u0003cCNL7/F\u0001`!\r\u00017m\t\b\u0003o\u0005L!AY\u000e\u0002\u000fA\f7m[1hK&\u0011A-\u001a\u0002\u0007-\u0016\u001cGo\u001c:\u000b\u0005\t\\\u0012a\u00013piR\u00191\u0006[5\t\u000b9K\u0001\u0019A\u0012\t\u000b)L\u0001\u0019A\u0012\u0002\u0003]\fqbQ8pe\u0012Lg.\u0019;f'B\f7-\u001a\t\u0003A-\u00192a\u00038r!\tQr.\u0003\u0002q7\t1\u0011I\\=SK\u001a\u0004\"A]<\u000e\u0003MT!\u0001^;\u0002\u0005%|'\"\u0001<\u0002\t)\fg/Y\u0005\u0003qN\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u00017\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u000bu\f\t!!\u0002\u0015\u0007y\f\t\u0002E\u0003!\u0001}\f\u0019\u0001E\u0002%\u0003\u0003!QAJ\u0007C\u0002\u001d\u00022\u0001JA\u0003\t%iS\u0002)A\u0001\u0002\u000b\u0007q\u0005K\u0004\u0002\u0006=\nI!!\u00042\r\r\u001aD'a\u00036c\u0011!sg\u000f\u000f2\r\rjd(a\u0004@c\u0011!sg\u000f\u000f\t\r\u0005MQ\u0002q\u0001\u007f\u0003\u00051\u0006fA\u0007\u0002\u0018A\u0019!$!\u0007\n\u0007\u0005m1D\u0001\u0004j]2Lg.Z\u0001\u0004g\u0016\fXCBA\u0011\u0003g\tI\u0004\u0006\u0003\u0002$\u0005MDCBA\u0013\u00033\nI\u0007\u0005\u0005\u0002(\u00055\u0012\u0011GA\u001c\u001b\t\tICC\u0002\u0002,Q\t1a\u001d;e\u0013\u0011\ty#!\u000b\u0003%M+\u0017oQ8pe\u0012Lg.\u0019;f'B\f7-\u001a\t\u0004I\u0005MBABA\u001b\u001d\t\u0007qEA\u0001B!\u0015!\u0013\u0011HA\u0019\t\u001d\tYD\u0004b\u0001\u0003{\u0011!aQ\"\u0016\t\u0005}\u0012qJ\t\u0004Q\u0005\u0005\u0003CCA\"\u0003\u0013\ni%!\u0015\u0002X5\u0011\u0011Q\t\u0006\u0004\u0003\u000fZ\u0012AC2pY2,7\r^5p]&!\u00111JA#\u0005\u0019\u0019V-](qgB\u0019A%a\u0014\u0005\u000f\u0005U\u0012\u0011\bb\u0001OA!\u00111IA*\u0013\u0011\t)&!\u0012\u0003\u0007M+\u0017\u000fE\u0003%\u0003s\ti\u0005C\u0005\u0002\\9\t\t\u0011q\u0001\u0002^\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005}\u00131MA\u0019\u001d\r\u0001\u0013\u0011M\u0005\u0003EJIA!!\u001a\u0002h\t)a)[3mI*\u0011!M\u0005\u0005\b\u0003Wr\u00019AA7\u0003\u0011\u0019'M\u001a\u0019\u0011\u0011\u0005\r\u0013qNA\u0019\u0003oIA!!\u001d\u0002F\t9a)Y2u_JL\b\"\u0002$\u000f\u0001\u0004A\u0015!B1se\u0006LX\u0003BA=\u0003\u000f#B!a\u001f\u0002,R1\u0011QPAJ\u00033\u0003b\u0001\t\u0001\u0002\u0000\u0005\u0015\u0005#\u0002\u000e\u0002\u0002\u0006\u0015\u0015bAAB7\t)\u0011I\u001d:bsB\u0019A%a\"\u0005\u0015\u0005Ur\u0002)A\u0001\u0002\u000b\u0007q\u0005K\u0004\u0002\b>\nY)a$2\r\r\u001aD'!$6c\u0011!sg\u000f\u000f2\r\rjd(!%@c\u0011!sg\u000f\u000f\t\u0013\u0005Uu\"!AA\u0004\u0005]\u0015AC3wS\u0012,gnY3%eA1\u0011qLA2\u0003\u000bC\u0011\"a'\u0010\u0003\u0003\u0005\u001d!!(\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0002 \u0006\u0015\u0016Q\u0011\b\u0005\u0003C\u000b\u0019+D\u0001\u0015\u0013\t\u0011G#\u0003\u0003\u0002(\u0006%&\u0001C\"mCN\u001cH+Y4\u000b\u0005\t$\u0002\"\u0002$\u0010\u0001\u0004A\u0015\u0001D<sSR,'+\u001a9mC\u000e,GCAAY!\u0011\t\u0019,!/\u000e\u0005\u0005U&bAA\\k\u0006!A.\u00198h\u0013\u0011\tY,!.\u0003\r=\u0013'.Z2u\u0001"
)
public interface CoordinateSpace extends InnerProductSpace {
   static CoordinateSpace array(final int dimensions, final Field evidence$2, final ClassTag evidence$3) {
      return CoordinateSpace$.MODULE$.array(dimensions, evidence$2, evidence$3);
   }

   static SeqCoordinateSpace seq(final int dimensions, final Field evidence$1, final Factory cbf0) {
      return CoordinateSpace$.MODULE$.seq(dimensions, evidence$1, cbf0);
   }

   static CoordinateSpace apply(final CoordinateSpace V) {
      return CoordinateSpace$.MODULE$.apply(V);
   }

   int dimensions();

   Object coord(final Object v, final int i);

   Object axis(final int i);

   // $FF: synthetic method
   static Object _x$(final CoordinateSpace $this, final Object v) {
      return $this._x(v);
   }

   default Object _x(final Object v) {
      return this.coord(v, 0);
   }

   // $FF: synthetic method
   static Object _y$(final CoordinateSpace $this, final Object v) {
      return $this._y(v);
   }

   default Object _y(final Object v) {
      return this.coord(v, 1);
   }

   // $FF: synthetic method
   static Object _z$(final CoordinateSpace $this, final Object v) {
      return $this._z(v);
   }

   default Object _z(final Object v) {
      return this.coord(v, 2);
   }

   // $FF: synthetic method
   static Vector basis$(final CoordinateSpace $this) {
      return $this.basis();
   }

   default Vector basis() {
      return (Vector).MODULE$.Vector().tabulate(this.dimensions(), (i) -> $anonfun$basis$1(this, BoxesRunTime.unboxToInt(i)));
   }

   // $FF: synthetic method
   static Object dot$(final CoordinateSpace $this, final Object v, final Object w) {
      return $this.dot(v, w);
   }

   default Object dot(final Object v, final Object w) {
      return this.loop$1(this.scalar().zero(), 0, v, w);
   }

   // $FF: synthetic method
   static double coord$mcD$sp$(final CoordinateSpace $this, final Object v, final int i) {
      return $this.coord$mcD$sp(v, i);
   }

   default double coord$mcD$sp(final Object v, final int i) {
      return BoxesRunTime.unboxToDouble(this.coord(v, i));
   }

   // $FF: synthetic method
   static float coord$mcF$sp$(final CoordinateSpace $this, final Object v, final int i) {
      return $this.coord$mcF$sp(v, i);
   }

   default float coord$mcF$sp(final Object v, final int i) {
      return BoxesRunTime.unboxToFloat(this.coord(v, i));
   }

   // $FF: synthetic method
   static double _x$mcD$sp$(final CoordinateSpace $this, final Object v) {
      return $this._x$mcD$sp(v);
   }

   default double _x$mcD$sp(final Object v) {
      return BoxesRunTime.unboxToDouble(this._x(v));
   }

   // $FF: synthetic method
   static float _x$mcF$sp$(final CoordinateSpace $this, final Object v) {
      return $this._x$mcF$sp(v);
   }

   default float _x$mcF$sp(final Object v) {
      return BoxesRunTime.unboxToFloat(this._x(v));
   }

   // $FF: synthetic method
   static double _y$mcD$sp$(final CoordinateSpace $this, final Object v) {
      return $this._y$mcD$sp(v);
   }

   default double _y$mcD$sp(final Object v) {
      return BoxesRunTime.unboxToDouble(this._y(v));
   }

   // $FF: synthetic method
   static float _y$mcF$sp$(final CoordinateSpace $this, final Object v) {
      return $this._y$mcF$sp(v);
   }

   default float _y$mcF$sp(final Object v) {
      return BoxesRunTime.unboxToFloat(this._y(v));
   }

   // $FF: synthetic method
   static double _z$mcD$sp$(final CoordinateSpace $this, final Object v) {
      return $this._z$mcD$sp(v);
   }

   default double _z$mcD$sp(final Object v) {
      return BoxesRunTime.unboxToDouble(this._z(v));
   }

   // $FF: synthetic method
   static float _z$mcF$sp$(final CoordinateSpace $this, final Object v) {
      return $this._z$mcF$sp(v);
   }

   default float _z$mcF$sp(final Object v) {
      return BoxesRunTime.unboxToFloat(this._z(v));
   }

   // $FF: synthetic method
   static double dot$mcD$sp$(final CoordinateSpace $this, final Object v, final Object w) {
      return $this.dot$mcD$sp(v, w);
   }

   default double dot$mcD$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToDouble(this.dot(v, w));
   }

   // $FF: synthetic method
   static float dot$mcF$sp$(final CoordinateSpace $this, final Object v, final Object w) {
      return $this.dot$mcF$sp(v, w);
   }

   default float dot$mcF$sp(final Object v, final Object w) {
      return BoxesRunTime.unboxToFloat(this.dot(v, w));
   }

   // $FF: synthetic method
   static Object $anonfun$basis$1(final CoordinateSpace $this, final int i) {
      return $this.axis(i);
   }

   private Object loop$1(final Object sum, final int i, final Object v$1, final Object w$1) {
      while(i < this.dimensions()) {
         Object var10000 = this.scalar().plus(sum, this.scalar().times(this.coord(v$1, i), this.coord(w$1, i)));
         ++i;
         sum = var10000;
      }

      return sum;
   }

   static void $init$(final CoordinateSpace $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
