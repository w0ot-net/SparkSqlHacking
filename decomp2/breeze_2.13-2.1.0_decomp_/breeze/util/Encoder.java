package breeze.util;

import breeze.collection.mutable.SparseArray;
import breeze.linalg.Counter;
import breeze.linalg.Counter$;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.DenseVector$mcD$sp;
import breeze.linalg.SparseVector;
import breeze.linalg.SparseVector$;
import breeze.linalg.Tensor;
import breeze.linalg.Vector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.linalg.VectorBuilder$mcD$sp;
import breeze.math.Semiring$;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015ca\u0002\u0011\"!\u0003\r\tA\n\u0005\u0006]\u0001!\ta\f\u0005\bg\u0001\u0011\rQ\"\u00015\u0011\u0015!\u0005\u0001\"\u0001F\u0011\u0015y\u0005\u0001\"\u0002Q\u0011\u001d1\u0006!%A\u0005\u0006]CQA\u0019\u0001\u0005\u0006\rDQa\u001a\u0001\u0005\u0006!DQ\u0001\u001c\u0001\u0005\u00025Dq\u0001\u001f\u0001\u0012\u0002\u0013\u0005\u0011\u0010C\u0003|\u0001\u0011\u0005A\u0010\u0003\u0005\u0002\n\u0001\t\n\u0011\"\u0001z\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bA\u0001\"a\u0005\u0001#\u0003%\t!\u001f\u0005\b\u0003+\u0001A\u0011AA\f\u0011!\ti\u0002AI\u0001\n\u0003I\bbBA\u000b\u0001\u0011\u0005\u0011q\u0004\u0005\b\u0003W\u0001A\u0011AA\u0017\u0011\u001d\ti\u0005\u0001C\u0001\u0003\u001fBq!!\u001b\u0001\t\u0003\tY\u0007C\u0004\u0002\b\u0002!\t!!#\t\r1\u0004A\u0011AAH\u0011\u001d\t\u0019\f\u0001C\u0001\u0003kCa\u0001\u001c\u0001\u0005\u0002\u0005\rxaBAyC!\u0005\u00111\u001f\u0004\u0007A\u0005B\t!!>\t\u000f\u0005]\u0018\u0004\"\u0001\u0002z\"9\u00111`\r\u0005\u0002\u0005uhA\u0002B\u00073\u0011\u0011y\u0001C\u000549\t\u0015\r\u0011\"\u0001\u0003,!Q!q\u0006\u000f\u0003\u0002\u0003\u0006IA!\f\t\u000f\u0005]H\u0004\"\u0001\u00032\t9QI\\2pI\u0016\u0014(B\u0001\u0012$\u0003\u0011)H/\u001b7\u000b\u0003\u0011\naA\u0019:fKj,7\u0001A\u000b\u0003Om\u001a\"\u0001\u0001\u0015\u0011\u0005%bS\"\u0001\u0016\u000b\u0003-\nQa]2bY\u0006L!!\f\u0016\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t\u0001\u0007\u0005\u0002*c%\u0011!G\u000b\u0002\u0005+:LG/A\u0003j]\u0012,\u00070F\u00016!\r1t'O\u0007\u0002C%\u0011\u0001(\t\u0002\u0006\u0013:$W\r\u001f\t\u0003umb\u0001\u0001B\u0003=\u0001\t\u0007QHA\u0001U#\tq\u0014\t\u0005\u0002*\u007f%\u0011\u0001I\u000b\u0002\b\u001d>$\b.\u001b8h!\tI#)\u0003\u0002DU\t\u0019\u0011I\\=\u0002\u001d5\\7\u000b]1sg\u00164Vm\u0019;peR\ta\tE\u0002H\u00152k\u0011\u0001\u0013\u0006\u0003\u0013\u000e\na\u0001\\5oC2<\u0017BA&I\u00051\u0019\u0006/\u0019:tKZ+7\r^8s!\tIS*\u0003\u0002OU\t1Ai\\;cY\u0016\fQ\"\\6EK:\u001cXMV3di>\u0014HCA)U!\r9%\u000bT\u0005\u0003'\"\u00131\u0002R3og\u00164Vm\u0019;pe\"9Q\u000b\u0002I\u0001\u0002\u0004a\u0015a\u00023fM\u0006,H\u000e^\u0001\u0018[.$UM\\:f-\u0016\u001cGo\u001c:%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u0017\u0016\u0003\u0019f[\u0013A\u0017\t\u00037\u0002l\u0011\u0001\u0018\u0006\u0003;z\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005}S\u0013AC1o]>$\u0018\r^5p]&\u0011\u0011\r\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001C7l-\u0016\u001cGo\u001c:\u0015\u0003\u0011\u00042aR3M\u0013\t1\u0007J\u0001\u0004WK\u000e$xN]\u0001\t[.l\u0015\r\u001e:jqR\t\u0011\u000eE\u0002HU2K!a\u001b%\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e_\u0001\u0007I\u0016\u001cw\u000eZ3\u0015\u00079\f8\u000f\u0005\u0003H_fb\u0015B\u00019I\u0005\u001d\u0019u.\u001e8uKJDQA\u001d\u0005A\u0002\u0011\f\u0011A\u001e\u0005\bi\"\u0001\n\u00111\u0001v\u0003%YW-\u001a9[KJ|7\u000f\u0005\u0002*m&\u0011qO\u000b\u0002\b\u0005>|G.Z1o\u0003A!WmY8eK\u0012\"WMZ1vYR$#'F\u0001{U\t)\u0018,A\u0006f]\u000e|G-\u001a#f]N,G\u0003B)~\u0003\u000bAQA \u0006A\u0002}\f\u0011a\u0019\t\u0006\u000f\u0006\u0005\u0011\bT\u0005\u0004\u0003\u0007A%A\u0002+f]N|'\u000f\u0003\u0005\u0002\b)\u0001\n\u00111\u0001v\u0003AIwM\\8sK>+Ho\u00144J]\u0012,\u00070A\u000bf]\u000e|G-\u001a#f]N,G\u0005Z3gCVdG\u000f\n\u001a\u0002\u0019\u0015t7m\u001c3f'B\f'o]3\u0015\u000b\u0019\u000by!!\u0005\t\u000byd\u0001\u0019A@\t\u0011\u0005\u001dA\u0002%AA\u0002U\fa#\u001a8d_\u0012,7\u000b]1sg\u0016$C-\u001a4bk2$HEM\u0001\u0007K:\u001cw\u000eZ3\u0015\u000b\u0011\fI\"a\u0007\t\u000byt\u0001\u0019A@\t\u0011\u0005\u001da\u0002%AA\u0002U\f\u0001#\u001a8d_\u0012,G\u0005Z3gCVdG\u000f\n\u001a\u0015\u0007%\f\t\u0003\u0003\u0004\u007f!\u0001\u0007\u00111\u0005\t\u0007\u000f\u0006\u0005\u0011Q\u0005'\u0011\u000b%\n9#O\u001d\n\u0007\u0005%\"F\u0001\u0004UkBdWMM\u0001\b[.\f%O]1z+\u0011\ty#!\u000f\u0015\t\u0005E\u0012Q\b\t\u0006S\u0005M\u0012qG\u0005\u0004\u0003kQ#!B!se\u0006L\bc\u0001\u001e\u0002:\u00111\u00111H\tC\u0002u\u0012\u0011A\u0016\u0005\n\u0003\u007f\t\u0012\u0011!a\u0002\u0003\u0003\n!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t\u0019%!\u0013\u000285\u0011\u0011Q\t\u0006\u0004\u0003\u000fR\u0013a\u0002:fM2,7\r^\u0005\u0005\u0003\u0017\n)E\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003%1\u0017\u000e\u001c7BeJ\f\u00170\u0006\u0003\u0002R\u0005eC\u0003BA*\u0003C\"B!!\u0016\u0002\\A)\u0011&a\r\u0002XA\u0019!(!\u0017\u0005\r\u0005m\"C1\u0001>\u0011%\tiFEA\u0001\u0002\b\ty&\u0001\u0006fm&$WM\\2fII\u0002b!a\u0011\u0002J\u0005]\u0003bB+\u0013\t\u0003\u0007\u00111\r\t\u0006S\u0005\u0015\u0014qK\u0005\u0004\u0003OR#\u0001\u0003\u001fcs:\fW.\u001a \u0002\u001bQ\f'-\u001e7bi\u0016\f%O]1z+\u0011\ti'!\u001e\u0015\t\u0005=\u0014Q\u0010\u000b\u0005\u0003c\n9\bE\u0003*\u0003g\t\u0019\bE\u0002;\u0003k\"a!a\u000f\u0014\u0005\u0004i\u0004\"CA='\u0005\u0005\t9AA>\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003\u0007\nI%a\u001d\t\u000f\u0005}4\u00031\u0001\u0002\u0002\u0006\ta\r\u0005\u0004*\u0003\u0007K\u00141O\u0005\u0004\u0003\u000bS#!\u0003$v]\u000e$\u0018n\u001c82\u0003M!\u0018MY;mCR,G)\u001a8tKZ+7\r^8s)\r\t\u00161\u0012\u0005\b\u0003\u007f\"\u0002\u0019AAG!\u0015I\u00131Q\u001dM+\u0011\t\t*a+\u0015\t\u0005M\u0015Q\u0016\t\b\u0003+\u000b\u0019+OAU\u001d\u0011\t9*a(\u0011\u0007\u0005e%&\u0004\u0002\u0002\u001c*\u0019\u0011QT\u0013\u0002\rq\u0012xn\u001c;?\u0013\r\t\tKK\u0001\u0007!J,G-\u001a4\n\t\u0005\u0015\u0016q\u0015\u0002\u0004\u001b\u0006\u0004(bAAQUA\u0019!(a+\u0005\r\u0005mRC1\u0001>\u0011\u001d\ty+\u0006a\u0001\u0003c\u000bQ!\u0019:sCf\u0004R!KA\u001a\u0003S\u000bQ\"\\6Ta\u0006\u00148/Z!se\u0006LX\u0003BA\\\u0003\u0017$b!!/\u0002N\u0006M\u0007CBA^\u0003\u000b\fI-\u0004\u0002\u0002>*!\u0011qXAa\u0003\u001diW\u000f^1cY\u0016T1!a1$\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u000f\fiLA\u0006Ta\u0006\u00148/Z!se\u0006L\bc\u0001\u001e\u0002L\u00121\u00111\b\fC\u0002uB\u0011\"a4\u0017\u0003\u0003\u0005\u001d!!5\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0002D\u0005%\u0013\u0011\u001a\u0005\n\u0003+4\u0012\u0011!a\u0002\u0003/\f!\"\u001a<jI\u0016t7-\u001a\u00136!\u0019\tI.a8\u0002J6\u0011\u00111\u001c\u0006\u0004\u0003;\u001c\u0013aB:u_J\fw-Z\u0005\u0005\u0003C\fYN\u0001\u0003[KJ|W\u0003BAs\u0003W$B!a:\u0002nB9\u0011QSARs\u0005%\bc\u0001\u001e\u0002l\u00121\u00111H\fC\u0002uBq!a,\u0018\u0001\u0004\ty\u000f\u0005\u0004\u0002<\u0006\u0015\u0017\u0011^\u0001\b\u000b:\u001cw\u000eZ3s!\t1\u0014d\u0005\u0002\u001aQ\u00051A(\u001b8jiz\"\"!a=\u0002\u0013\u0019\u0014x.\\%oI\u0016DX\u0003BA\u0000\u0005\u000b!BA!\u0001\u0003\bA!a\u0007\u0001B\u0002!\rQ$Q\u0001\u0003\u0006ym\u0011\r!\u0010\u0005\b\u0005\u0013Y\u0002\u0019\u0001B\u0006\u0003\rIg\u000e\u001a\t\u0005m]\u0012\u0019AA\u0007TS6\u0004H.Z#oG>$WM]\u000b\u0005\u0005#\u00119b\u0005\u0004\u001dQ\tM!\u0011\u0004\t\u0005m\u0001\u0011)\u0002E\u0002;\u0005/!Q\u0001\u0010\u000fC\u0002u\u0002BAa\u0007\u0003&9!!Q\u0004B\u0011\u001d\u0011\tIJa\b\n\u0003-J1Aa\t+\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\n\u0003*\ta1+\u001a:jC2L'0\u00192mK*\u0019!1\u0005\u0016\u0016\u0005\t5\u0002\u0003\u0002\u001c8\u0005+\ta!\u001b8eKb\u0004C\u0003\u0002B\u001a\u0005o\u0001RA!\u000e\u001d\u0005+i\u0011!\u0007\u0005\u0007g}\u0001\rA!\f)\u000fq\u0011YD!\u0011\u0003DA\u0019\u0011F!\u0010\n\u0007\t}\"F\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t\u0011\u0001"
)
public interface Encoder {
   static Encoder fromIndex(final Index ind) {
      return Encoder$.MODULE$.fromIndex(ind);
   }

   Index index();

   default SparseVector mkSparseVector() {
      return SparseVector$.MODULE$.zeros$mDc$sp(this.index().size(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
   }

   default DenseVector mkDenseVector(final double default) {
      double[] array = new double[this.index().size()];
      Arrays.fill(array, default);
      return new DenseVector$mcD$sp(array);
   }

   default double mkDenseVector$default$1() {
      return (double)0.0F;
   }

   default Vector mkVector() {
      return this.mkSparseVector();
   }

   default DenseMatrix mkMatrix() {
      return DenseMatrix$.MODULE$.zeros$mDc$sp(this.index().size(), this.index().size(), .MODULE$.Double(), Zero$.MODULE$.DoubleZero());
   }

   default Counter decode(final Vector v, final boolean keepZeros) {
      Counter ctr = Counter$.MODULE$.apply(Zero$.MODULE$.DoubleZero());
      v.active().pairs().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$decode$1(check$ifrefutable$1))).foreach((Function1)((x$1) -> {
         $anonfun$decode$2(this, keepZeros, ctr, x$1);
         return BoxedUnit.UNIT;
      }));
      return ctr;
   }

   default DenseVector encodeDense(final Tensor c, final boolean ignoreOutOfIndex) {
      DenseVector vec = this.mkDenseVector(this.mkDenseVector$default$1());
      c.active().pairs().withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$encodeDense$1(check$ifrefutable$2))).foreach((Function1)((x$2) -> {
         $anonfun$encodeDense$2(this, ignoreOutOfIndex, vec, x$2);
         return BoxedUnit.UNIT;
      }));
      return vec;
   }

   default SparseVector encodeSparse(final Tensor c, final boolean ignoreOutOfIndex) {
      VectorBuilder vec = new VectorBuilder$mcD$sp(this.index().size(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), Semiring$.MODULE$.semiringD(), .MODULE$.Double());
      vec.reserve(c.activeSize());
      c.active().pairs().withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$encodeSparse$1(check$ifrefutable$3))).foreach((Function1)((x$3) -> {
         $anonfun$encodeSparse$2(this, ignoreOutOfIndex, vec, x$3);
         return BoxedUnit.UNIT;
      }));
      return vec.toSparseVector$mcD$sp();
   }

   default Vector encode(final Tensor c, final boolean ignoreOutOfIndex) {
      Vector vec = this.mkVector();
      c.active().pairs().withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$encode$1(check$ifrefutable$4))).foreach((Function1)((x$4) -> {
         $anonfun$encode$2(this, ignoreOutOfIndex, vec, x$4);
         return BoxedUnit.UNIT;
      }));
      return vec;
   }

   default DenseMatrix encode(final Tensor c) {
      DenseMatrix vec = this.mkMatrix();
      c.active().pairs().withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$encode$3(check$ifrefutable$5))).foreach((Function1)((x$5) -> {
         $anonfun$encode$4(this, vec, x$5);
         return BoxedUnit.UNIT;
      }));
      return vec;
   }

   default boolean encodeDense$default$2() {
      return false;
   }

   default boolean encodeSparse$default$2() {
      return false;
   }

   default boolean encode$default$2() {
      return false;
   }

   default Object mkArray(final ClassTag evidence$1) {
      return evidence$1.newArray(this.index().size());
   }

   default Object fillArray(final Function0 default, final ClassTag evidence$2) {
      return scala.Array..MODULE$.fill(this.index().size(), default, evidence$2);
   }

   default Object tabulateArray(final Function1 f, final ClassTag evidence$3) {
      Object arr = evidence$3.newArray(this.index().size());
      this.index().pairs().withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$tabulateArray$1(check$ifrefutable$6))).foreach((x$6) -> {
         $anonfun$tabulateArray$2(arr, f, x$6);
         return BoxedUnit.UNIT;
      });
      return arr;
   }

   default DenseVector tabulateDenseVector(final Function1 f) {
      return new DenseVector$mcD$sp((double[])this.tabulateArray(f, .MODULE$.Double()));
   }

   default Map decode(final Object array) {
      return (Map)scala.Predef..MODULE$.Map().empty().$plus$plus(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.genericArrayOps(array))), (x0$1) -> {
         if (x0$1 != null) {
            Object v = x0$1._1();
            int i = x0$1._2$mcI$sp();
            Tuple2 var2 = new Tuple2(this.index().get(i), v);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, .MODULE$.apply(Tuple2.class))));
   }

   default SparseArray mkSparseArray(final ClassTag evidence$4, final Zero evidence$5) {
      return new SparseArray(this.index().size(), evidence$4, evidence$5);
   }

   default Map decode(final SparseArray array) {
      return (Map)scala.Predef..MODULE$.Map().empty().$plus$plus(array.iterator().map((x0$1) -> {
         if (x0$1 != null) {
            int i = x0$1._1$mcI$sp();
            Object v = x0$1._2();
            Tuple2 var2 = new Tuple2(this.index().get(i), v);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   default boolean decode$default$2() {
      return false;
   }

   // $FF: synthetic method
   static boolean $anonfun$decode$1(final Tuple2 check$ifrefutable$1) {
      boolean var1;
      if (check$ifrefutable$1 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$decode$2(final Encoder $this, final boolean keepZeros$1, final Counter ctr$1, final Tuple2 x$1) {
      if (x$1 == null) {
         throw new MatchError(x$1);
      } else {
         int i = x$1._1$mcI$sp();
         double v = x$1._2$mcD$sp();
         if (!keepZeros$1 && v == (double)0.0F) {
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            ctr$1.update($this.index().get(i), BoxesRunTime.boxToDouble(v));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

      }
   }

   // $FF: synthetic method
   static boolean $anonfun$encodeDense$1(final Tuple2 check$ifrefutable$2) {
      boolean var1;
      if (check$ifrefutable$2 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$encodeDense$2(final Encoder $this, final boolean ignoreOutOfIndex$1, final DenseVector vec$1, final Tuple2 x$2) {
      if (x$2 != null) {
         Object k = x$2._1();
         double v = x$2._2$mcD$sp();
         int ki = $this.index().apply(k);
         if (ki < 0) {
            if (!ignoreOutOfIndex$1) {
               throw new RuntimeException((new StringBuilder(21)).append("Error, not in index: ").append(k).toString());
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            vec$1.update$mcD$sp(ki, v);
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$encodeSparse$1(final Tuple2 check$ifrefutable$3) {
      boolean var1;
      if (check$ifrefutable$3 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$encodeSparse$2(final Encoder $this, final boolean ignoreOutOfIndex$2, final VectorBuilder vec$2, final Tuple2 x$3) {
      if (x$3 != null) {
         Object k = x$3._1();
         double v = x$3._2$mcD$sp();
         int ki = $this.index().apply(k);
         if (ki < 0) {
            if (!ignoreOutOfIndex$2) {
               throw new RuntimeException((new StringBuilder(21)).append("Error, not in index: ").append(k).toString());
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            vec$2.add$mcD$sp(ki, v);
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$3);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$encode$1(final Tuple2 check$ifrefutable$4) {
      boolean var1;
      if (check$ifrefutable$4 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$encode$2(final Encoder $this, final boolean ignoreOutOfIndex$3, final Vector vec$3, final Tuple2 x$4) {
      if (x$4 != null) {
         Object k = x$4._1();
         double v = x$4._2$mcD$sp();
         int ki = $this.index().apply(k);
         if (ki < 0) {
            if (!ignoreOutOfIndex$3) {
               throw new RuntimeException((new StringBuilder(21)).append("Error, not in index: ").append(k).toString());
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            vec$3.update$mcID$sp(ki, v);
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

      } else {
         throw new MatchError(x$4);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$encode$3(final Tuple2 check$ifrefutable$5) {
      boolean var1;
      if (check$ifrefutable$5 != null) {
         Tuple2 var3 = (Tuple2)check$ifrefutable$5._1();
         if (var3 != null) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$encode$4(final Encoder $this, final DenseMatrix vec$4, final Tuple2 x$5) {
      if (x$5 != null) {
         Tuple2 var5 = (Tuple2)x$5._1();
         double v = x$5._2$mcD$sp();
         if (var5 != null) {
            Object k = var5._1();
            Object l = var5._2();
            int ki = $this.index().apply(k);
            int li = $this.index().apply(l);
            if (ki < 0) {
               throw new RuntimeException((new StringBuilder(21)).append("Error, not in index: ").append(k).toString());
            }

            if (li < 0) {
               throw new RuntimeException((new StringBuilder(21)).append("Error, not in index: ").append(k).toString());
            }

            vec$4.update$mcD$sp(ki, li, v);
            BoxedUnit var3 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x$5);
   }

   // $FF: synthetic method
   static boolean $anonfun$tabulateArray$1(final Tuple2 check$ifrefutable$6) {
      boolean var1;
      if (check$ifrefutable$6 != null) {
         var1 = true;
      } else {
         var1 = false;
      }

      return var1;
   }

   // $FF: synthetic method
   static void $anonfun$tabulateArray$2(final Object arr$1, final Function1 f$1, final Tuple2 x$6) {
      if (x$6 != null) {
         Object e = x$6._1();
         int i = x$6._2$mcI$sp();
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr$1, i, f$1.apply(e));
         BoxedUnit var3 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$6);
      }
   }

   static void $init$(final Encoder $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SimpleEncoder implements Encoder, Serializable {
      private static final long serialVersionUID = 1L;
      private final Index index;

      public SparseVector mkSparseVector() {
         return Encoder.super.mkSparseVector();
      }

      public final DenseVector mkDenseVector(final double default) {
         return Encoder.super.mkDenseVector(default);
      }

      public final double mkDenseVector$default$1() {
         return Encoder.super.mkDenseVector$default$1();
      }

      public final Vector mkVector() {
         return Encoder.super.mkVector();
      }

      public final DenseMatrix mkMatrix() {
         return Encoder.super.mkMatrix();
      }

      public Counter decode(final Vector v, final boolean keepZeros) {
         return Encoder.super.decode(v, keepZeros);
      }

      public boolean decode$default$2() {
         return Encoder.super.decode$default$2();
      }

      public DenseVector encodeDense(final Tensor c, final boolean ignoreOutOfIndex) {
         return Encoder.super.encodeDense(c, ignoreOutOfIndex);
      }

      public boolean encodeDense$default$2() {
         return Encoder.super.encodeDense$default$2();
      }

      public SparseVector encodeSparse(final Tensor c, final boolean ignoreOutOfIndex) {
         return Encoder.super.encodeSparse(c, ignoreOutOfIndex);
      }

      public boolean encodeSparse$default$2() {
         return Encoder.super.encodeSparse$default$2();
      }

      public Vector encode(final Tensor c, final boolean ignoreOutOfIndex) {
         return Encoder.super.encode(c, ignoreOutOfIndex);
      }

      public boolean encode$default$2() {
         return Encoder.super.encode$default$2();
      }

      public DenseMatrix encode(final Tensor c) {
         return Encoder.super.encode(c);
      }

      public Object mkArray(final ClassTag evidence$1) {
         return Encoder.super.mkArray(evidence$1);
      }

      public Object fillArray(final Function0 default, final ClassTag evidence$2) {
         return Encoder.super.fillArray(default, evidence$2);
      }

      public Object tabulateArray(final Function1 f, final ClassTag evidence$3) {
         return Encoder.super.tabulateArray(f, evidence$3);
      }

      public DenseVector tabulateDenseVector(final Function1 f) {
         return Encoder.super.tabulateDenseVector(f);
      }

      public Map decode(final Object array) {
         return Encoder.super.decode(array);
      }

      public SparseArray mkSparseArray(final ClassTag evidence$4, final Zero evidence$5) {
         return Encoder.super.mkSparseArray(evidence$4, evidence$5);
      }

      public Map decode(final SparseArray array) {
         return Encoder.super.decode(array);
      }

      public Index index() {
         return this.index;
      }

      public SimpleEncoder(final Index index) {
         this.index = index;
         Encoder.$init$(this);
      }
   }
}
