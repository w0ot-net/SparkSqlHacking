package breeze.linalg;

import breeze.linalg.support.CanMapKeyValuePairs;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.TensorActive;
import breeze.linalg.support.TensorKeys;
import breeze.linalg.support.TensorKeys$;
import breeze.linalg.support.TensorPairs;
import breeze.linalg.support.TensorPairs$;
import breeze.linalg.support.TensorValues;
import breeze.linalg.support.TensorValues$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala..less.colon.less.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;

@ScalaSignature(
   bytes = "\u0006\u0005\tmca\u0002\f\u0018!\u0003\r\t\u0001\b\u0005\u0006A\u0002!\t!\u0019\u0005\u0006K\u00021\tA\u001a\u0005\u0006S\u00021\tA\u001b\u0005\u0006]\u00021\ta\u001c\u0005\u0006g\u00021\ta\u001c\u0005\u0006i\u0002!\t!\u001e\u0005\u0006y\u0002!\t! \u0005\b\u0003\u0007\u0001A\u0011AA\u0003\u0011\u001d\ti\u0001\u0001C\u0001\u0003\u001fAa!\u001a\u0001\u0005\u0002\u0005]\u0001BB3\u0001\t\u0003\t9\u0004\u0003\u0004f\u0001\u0011\u0005\u0011q\r\u0005\b\u0003\u001b\u0003A\u0011AAH\u0011\u001d\t)\f\u0001C\u0001\u0003oCq!!4\u0001\t\u0003\ty\rC\u0004\u0003\u0002\u0001!\tAa\u0001\t\u000f\te\u0001\u0001\"\u0001\u0003\u001c!9!1\u0006\u0001\u0005\u0002\t5\u0002b\u0002B\u001d\u0001\u0011\u0005!1\b\u0005\b\u0005\u000f\u0002A\u0011\u0001B%\u0011\u001d\u00119\u0005\u0001C\u0001\u0005+\u0012!\u0002V3og>\u0014H*[6f\u0015\tA\u0012$\u0001\u0004mS:\fGn\u001a\u0006\u00025\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0003\u001eU\tS6\u0003\u0002\u0001\u001fIY\u0003\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007\u0003B\u0013'Q\u0005k\u0011aF\u0005\u0003O]\u00111\"U;bg&$VM\\:peB\u0011\u0011F\u000b\u0007\u0001\t%Y\u0003\u0001)A\u0001\u0002\u000b\u0007AFA\u0001L#\ti\u0003\u0007\u0005\u0002 ]%\u0011q\u0006\t\u0002\b\u001d>$\b.\u001b8h!\ty\u0012'\u0003\u00023A\t\u0019\u0011I\\=)\u0007)\"t\u0007\u0005\u0002 k%\u0011a\u0007\t\u0002\fgB,7-[1mSj,G-M\u0003$qeZ$H\u0004\u0002 s%\u0011!\bI\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013=\u0001\u0006r!!\u0010!\u000e\u0003yR!aP\u000e\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0003CA\u0015C\t%\u0019\u0005\u0001)A\u0001\u0002\u000b\u0007AFA\u0001WQ\u0019\u0011E'\u0012&M#F*1ER$J\u0011:\u0011qdR\u0005\u0003\u0011\u0002\na\u0001R8vE2,\u0017\u0007\u0002\u0013=\u0001\u0006\nTa\t\u001d:\u0017j\nD\u0001\n\u001fACE*1%\u0014(Q\u001f:\u0011qDT\u0005\u0003\u001f\u0002\nQA\u00127pCR\fD\u0001\n\u001fACE*1EU*V):\u0011qdU\u0005\u0003)\u0002\nA\u0001T8oOF\"A\u0005\u0010!\"!\r)s+W\u0005\u00031^\u0011!BT;nKJL7m\u00149t!\tI#\f\u0002\u0004\\\u0001\u0011\u0015\r\u0001\u0018\u0002\u0005)\"L7/\u0005\u0002.;B!QE\u0018\u0015B\u0013\tyvC\u0001\u0004UK:\u001cxN]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0004\"aH2\n\u0005\u0011\u0004#\u0001B+oSR\fQ!\u00199qYf$\"!Q4\t\u000b!\u0014\u0001\u0019\u0001\u0015\u0002\u0003%\fa!\u001e9eCR,Gc\u00012lY\")\u0001n\u0001a\u0001Q!)Qn\u0001a\u0001\u0003\u0006\ta/\u0001\u0003tSj,W#\u00019\u0011\u0005}\t\u0018B\u0001:!\u0005\rIe\u000e^\u0001\u000bC\u000e$\u0018N^3TSj,\u0017\u0001B6fsN,\u0012A\u001e\t\u0006ojD\u0013)W\u0007\u0002q*\u0011\u0011pF\u0001\bgV\u0004\bo\u001c:u\u0013\tY\bP\u0001\u0006UK:\u001cxN]&fsN\faA^1mk\u0016\u001cX#\u0001@\u0011\u000b]|\b&Q-\n\u0007\u0005\u0005\u0001P\u0001\u0007UK:\u001cxN\u001d,bYV,7/A\u0003qC&\u00148/\u0006\u0002\u0002\bA1q/!\u0003)\u0003fK1!a\u0003y\u0005-!VM\\:peB\u000b\u0017N]:\u0002\r\u0005\u001cG/\u001b<f+\t\t\t\u0002\u0005\u0004x\u0003'A\u0013)W\u0005\u0004\u0003+A(\u0001\u0004+f]N|'/Q2uSZ,WCBA\r\u0003_\ty\u0002\u0006\u0003\u0002\u001c\u0005MB\u0003BA\u000f\u0003G\u00012!KA\u0010\t\u0019\t\tC\u0003b\u0001Y\t1!+Z:vYRDq!!\n\u000b\u0001\b\t9#\u0001\u0005dC:\u001cF.[2f!!9\u0018\u0011F-\u0002.\u0005u\u0011bAA\u0016q\nA1)\u00198TY&\u001cW\rE\u0002*\u0003_!a!!\r\u000b\u0005\u0004a#!B*mS\u000e,\u0007bBA\u001b\u0015\u0001\u0007\u0011QF\u0001\u0006g2L7-Z\u000b\u0005\u0003s\ty\u0004\u0006\u0006\u0002<\u0005M\u0013qKA.\u0003?\"B!!\u0010\u0002BA\u0019\u0011&a\u0010\u0005\r\u0005\u00052B1\u0001-\u0011\u001d\t)c\u0003a\u0002\u0003\u0007\u0002\u0002b^A\u00153\u0006\u0015\u0013Q\b\t\u0006\u0003\u000f\ni\u0005\u000b\b\u0004y\u0005%\u0013bAA&A\u00059\u0001/Y2lC\u001e,\u0017\u0002BA(\u0003#\u00121aU3r\u0015\r\tY\u0005\t\u0005\u0007\u0003+Z\u0001\u0019\u0001\u0015\u0002\u0003\u0005Da!!\u0017\f\u0001\u0004A\u0013!\u00012\t\r\u0005u3\u00021\u0001)\u0003\u0005\u0019\u0007bBA\u001b\u0017\u0001\u0007\u0011\u0011\r\t\u0005?\u0005\r\u0004&C\u0002\u0002f\u0001\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?+!\tI'a\u001f\u0002\u0002\u0006=DCBA6\u0003\u000b\u000bI\t\u0006\u0003\u0002n\u0005E\u0004cA\u0015\u0002p\u00111\u0011\u0011\u0005\u0007C\u00021Bq!!\n\r\u0001\b\t\u0019\b\u0005\u0006x\u0003kJ\u0016\u0011PA@\u0003[J1!a\u001ey\u0005%\u0019\u0015M\\*mS\u000e,'\u0007E\u0002*\u0003w\"a!! \r\u0005\u0004a#AB*mS\u000e,\u0017\u0007E\u0002*\u0003\u0003#a!a!\r\u0005\u0004a#AB*mS\u000e,'\u0007C\u0004\u0002\b2\u0001\r!!\u001f\u0002\rMd\u0017nY32\u0011\u001d\tY\t\u0004a\u0001\u0003\u007f\naa\u001d7jG\u0016\u0014\u0014\u0001C7baB\u000b\u0017N]:\u0016\r\u0005E\u0015qUAL)\u0011\t\u0019*a+\u0015\t\u0005U\u00151\u0014\t\u0004S\u0005]EABAM\u001b\t\u0007AF\u0001\u0003UQ\u0006$\bbBAO\u001b\u0001\u000f\u0011qT\u0001\u0003E\u001a\u0004\"b^AQ3\"\n\u0015QUAK\u0013\r\t\u0019\u000b\u001f\u0002\u0014\u0007\u0006tW*\u00199LKf4\u0016\r\\;f!\u0006L'o\u001d\t\u0004S\u0005\u001dFABAU\u001b\t\u0007AFA\u0001P\u0011\u001d\ti+\u0004a\u0001\u0003_\u000b\u0011A\u001a\t\b?\u0005E\u0006&QAS\u0013\r\t\u0019\f\t\u0002\n\rVt7\r^5p]J\na\"\\1q\u0003\u000e$\u0018N^3QC&\u00148/\u0006\u0004\u0002:\u0006\u001d\u0017q\u0018\u000b\u0005\u0003w\u000bI\r\u0006\u0003\u0002>\u0006\u0005\u0007cA\u0015\u0002@\u00121\u0011\u0011\u0014\bC\u00021Bq!!(\u000f\u0001\b\t\u0019\r\u0005\u0006x\u0003CK\u0006&QAc\u0003{\u00032!KAd\t\u0019\tIK\u0004b\u0001Y!9\u0011Q\u0016\bA\u0002\u0005-\u0007cB\u0010\u00022\"\n\u0015QY\u0001\n[\u0006\u0004h+\u00197vKN,b!!5\u0002x\u0006]G\u0003BAj\u0003s$B!!6\u0002ZB\u0019\u0011&a6\u0005\r\u0005euB1\u0001-\u0011\u001d\tij\u0004a\u0002\u00037\u0004\"b^Ao\u0003C\f\u0015Q_Ak\u0013\r\ty\u000e\u001f\u0002\r\u0007\u0006tW*\u00199WC2,Xm\u001d\u0016\u00043\u0006\r8FAAs!\u0011\t9/!=\u000e\u0005\u0005%(\u0002BAv\u0003[\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=\b%\u0001\u0006b]:|G/\u0019;j_:LA!a=\u0002j\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0011\u0007%\n9\u0010\u0002\u0004\u0002*>\u0011\r\u0001\f\u0005\b\u0003[{\u0001\u0019AA~!\u0019y\u0012Q`!\u0002v&\u0019\u0011q \u0011\u0003\u0013\u0019+hn\u0019;j_:\f\u0014aD7ba\u0006\u001bG/\u001b<f-\u0006dW/Z:\u0016\r\t\u0015!1\u0003B\u0006)\u0011\u00119A!\u0006\u0015\t\t%!Q\u0002\t\u0004S\t-AABAM!\t\u0007A\u0006C\u0004\u0002\u001eB\u0001\u001dAa\u0004\u0011\u0015]\fi.!9B\u0005#\u0011I\u0001E\u0002*\u0005'!a!!+\u0011\u0005\u0004a\u0003bBAW!\u0001\u0007!q\u0003\t\u0007?\u0005u\u0018I!\u0005\u0002\u0015\u0019|'/Z1dQ.+\u00170\u0006\u0003\u0003\u001e\t\u001dBc\u00012\u0003 !9!\u0011E\tA\u0002\t\r\u0012A\u00014o!\u0019y\u0012Q \u0015\u0003&A\u0019\u0011Fa\n\u0005\r\t%\u0012C1\u0001-\u0005\u0005)\u0016a\u00034pe\u0016\f7\r\u001b)bSJ,BAa\f\u00038Q\u0019!M!\r\t\u000f\t\u0005\"\u00031\u0001\u00034A9q$!-)\u0003\nU\u0002cA\u0015\u00038\u00111!\u0011\u0006\nC\u00021\nABZ8sK\u0006\u001c\u0007NV1mk\u0016,BA!\u0010\u0003FQ\u0019!Ma\u0010\t\u000f\t\u00052\u00031\u0001\u0003BA1q$!@B\u0005\u0007\u00022!\u000bB#\t\u0019\u0011Ic\u0005b\u0001Y\u00051am\u001c:bY2$BAa\u0013\u0003RA\u0019qD!\u0014\n\u0007\t=\u0003EA\u0004C_>dW-\u00198\t\u000f\t\u0005B\u00031\u0001\u0003TA9q$!-)\u0003\n-C\u0003\u0002B&\u0005/BqA!\t\u0016\u0001\u0004\u0011I\u0006\u0005\u0004 \u0003{\f%1\n"
)
public interface TensorLike extends QuasiTensor, NumericOps {
   Object apply(final Object i);

   void update(final Object i, final Object v);

   int size();

   int activeSize();

   // $FF: synthetic method
   static TensorKeys keys$(final TensorLike $this) {
      return $this.keys();
   }

   default TensorKeys keys() {
      return new TensorKeys(this.repr(), false, TensorKeys$.MODULE$.$lessinit$greater$default$3(), .MODULE$.refl());
   }

   // $FF: synthetic method
   static TensorValues values$(final TensorLike $this) {
      return $this.values();
   }

   default TensorValues values() {
      return new TensorValues(this.repr(), false, TensorValues$.MODULE$.$lessinit$greater$default$3(), .MODULE$.refl());
   }

   // $FF: synthetic method
   static TensorPairs pairs$(final TensorLike $this) {
      return $this.pairs();
   }

   default TensorPairs pairs() {
      return new TensorPairs(this.repr(), false, TensorPairs$.MODULE$.$lessinit$greater$default$3(), .MODULE$.refl());
   }

   // $FF: synthetic method
   static TensorActive active$(final TensorLike $this) {
      return $this.active();
   }

   default TensorActive active() {
      return new TensorActive(this.repr(), .MODULE$.refl());
   }

   // $FF: synthetic method
   static Object apply$(final TensorLike $this, final Object slice, final CanSlice canSlice) {
      return $this.apply(slice, canSlice);
   }

   default Object apply(final Object slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), slice);
   }

   // $FF: synthetic method
   static Object apply$(final TensorLike $this, final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return $this.apply(a, b, c, slice, canSlice);
   }

   default Object apply(final Object a, final Object b, final Object c, final Seq slice, final CanSlice canSlice) {
      return canSlice.apply(this.repr(), ((SeqOps)((SeqOps)slice.$plus$colon(c)).$plus$colon(b)).$plus$colon(a));
   }

   // $FF: synthetic method
   static Object apply$(final TensorLike $this, final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return $this.apply(slice1, slice2, canSlice);
   }

   default Object apply(final Object slice1, final Object slice2, final CanSlice2 canSlice) {
      return canSlice.apply(this.repr(), slice1, slice2);
   }

   // $FF: synthetic method
   static Object mapPairs$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs(f, bf);
   }

   default Object mapPairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActivePairs$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs(f, bf);
   }

   default Object mapActivePairs(final Function2 f, final CanMapKeyValuePairs bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapValues$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues(f, bf);
   }

   default Object mapValues(final Function1 f, final CanMapValues bf) {
      return bf.map(this.repr(), f);
   }

   // $FF: synthetic method
   static Object mapActiveValues$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues(f, bf);
   }

   default Object mapActiveValues(final Function1 f, final CanMapValues bf) {
      return bf.mapActive(this.repr(), f);
   }

   // $FF: synthetic method
   static void foreachKey$(final TensorLike $this, final Function1 fn) {
      $this.foreachKey(fn);
   }

   default void foreachKey(final Function1 fn) {
      this.keysIterator().foreach(fn);
   }

   // $FF: synthetic method
   static void foreachPair$(final TensorLike $this, final Function2 fn) {
      $this.foreachPair(fn);
   }

   default void foreachPair(final Function2 fn) {
      this.foreachKey((k) -> fn.apply(k, this.apply(k)));
   }

   // $FF: synthetic method
   static void foreachValue$(final TensorLike $this, final Function1 fn) {
      $this.foreachValue(fn);
   }

   default void foreachValue(final Function1 fn) {
      this.foreachKey((k) -> fn.apply(this.apply(k)));
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike $this, final Function2 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function2 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachPair((k, v) -> {
            $anonfun$forall$1(fn, var2, k, v);
            return BoxedUnit.UNIT;
         });
         var10000 = true;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean forall$(final TensorLike $this, final Function1 fn) {
      return $this.forall(fn);
   }

   default boolean forall(final Function1 fn) {
      Object var2 = new Object();

      boolean var10000;
      try {
         this.foreachValue((v) -> {
            $anonfun$forall$2(fn, var2, v);
            return BoxedUnit.UNIT;
         });
         var10000 = true;
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = var4.value$mcZ$sp();
      }

      return var10000;
   }

   // $FF: synthetic method
   static double apply$mcID$sp$(final TensorLike $this, final int i) {
      return $this.apply$mcID$sp(i);
   }

   default double apply$mcID$sp(final int i) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static float apply$mcIF$sp$(final TensorLike $this, final int i) {
      return $this.apply$mcIF$sp(i);
   }

   default float apply$mcIF$sp(final int i) {
      return BoxesRunTime.unboxToFloat(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static int apply$mcII$sp$(final TensorLike $this, final int i) {
      return $this.apply$mcII$sp(i);
   }

   default int apply$mcII$sp(final int i) {
      return BoxesRunTime.unboxToInt(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static long apply$mcIJ$sp$(final TensorLike $this, final int i) {
      return $this.apply$mcIJ$sp(i);
   }

   default long apply$mcIJ$sp(final int i) {
      return BoxesRunTime.unboxToLong(this.apply(BoxesRunTime.boxToInteger(i)));
   }

   // $FF: synthetic method
   static void update$mcID$sp$(final TensorLike $this, final int i, final double v) {
      $this.update$mcID$sp(i, v);
   }

   default void update$mcID$sp(final int i, final double v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToDouble(v));
   }

   // $FF: synthetic method
   static void update$mcIF$sp$(final TensorLike $this, final int i, final float v) {
      $this.update$mcIF$sp(i, v);
   }

   default void update$mcIF$sp(final int i, final float v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToFloat(v));
   }

   // $FF: synthetic method
   static void update$mcII$sp$(final TensorLike $this, final int i, final int v) {
      $this.update$mcII$sp(i, v);
   }

   default void update$mcII$sp(final int i, final int v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToInteger(v));
   }

   // $FF: synthetic method
   static void update$mcIJ$sp$(final TensorLike $this, final int i, final long v) {
      $this.update$mcIJ$sp(i, v);
   }

   default void update$mcIJ$sp(final int i, final long v) {
      this.update(BoxesRunTime.boxToInteger(i), BoxesRunTime.boxToLong(v));
   }

   // $FF: synthetic method
   static Object apply$mcI$sp$(final TensorLike $this, final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return $this.apply$mcI$sp(a, b, c, slice, canSlice);
   }

   default Object apply$mcI$sp(final int a, final int b, final int c, final Seq slice, final CanSlice canSlice) {
      return this.apply(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), BoxesRunTime.boxToInteger(c), slice, canSlice);
   }

   // $FF: synthetic method
   static Object mapPairs$mcID$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcID$sp(f, bf);
   }

   default Object mapPairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcIF$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcIF$sp(f, bf);
   }

   default Object mapPairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcII$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcII$sp(f, bf);
   }

   default Object mapPairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapPairs$mcIJ$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapPairs$mcIJ$sp(f, bf);
   }

   default Object mapPairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapPairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcID$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcID$sp(f, bf);
   }

   default Object mapActivePairs$mcID$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcIF$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcIF$sp(f, bf);
   }

   default Object mapActivePairs$mcIF$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcII$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcII$sp(f, bf);
   }

   default Object mapActivePairs$mcII$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapActivePairs$mcIJ$sp$(final TensorLike $this, final Function2 f, final CanMapKeyValuePairs bf) {
      return $this.mapActivePairs$mcIJ$sp(f, bf);
   }

   default Object mapActivePairs$mcIJ$sp(final Function2 f, final CanMapKeyValuePairs bf) {
      return this.mapActivePairs(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcD$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcD$sp(f, bf);
   }

   default Object mapValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return this.mapValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcF$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcF$sp(f, bf);
   }

   default Object mapValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return this.mapValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcI$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcI$sp(f, bf);
   }

   default Object mapValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return this.mapValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapValues$mcJ$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapValues$mcJ$sp(f, bf);
   }

   default Object mapValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return this.mapValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcD$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcD$sp(f, bf);
   }

   default Object mapActiveValues$mcD$sp(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcF$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcF$sp(f, bf);
   }

   default Object mapActiveValues$mcF$sp(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcI$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcI$sp(f, bf);
   }

   default Object mapActiveValues$mcI$sp(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues(f, bf);
   }

   // $FF: synthetic method
   static Object mapActiveValues$mcJ$sp$(final TensorLike $this, final Function1 f, final CanMapValues bf) {
      return $this.mapActiveValues$mcJ$sp(f, bf);
   }

   default Object mapActiveValues$mcJ$sp(final Function1 f, final CanMapValues bf) {
      return this.mapActiveValues(f, bf);
   }

   // $FF: synthetic method
   static void foreachKey$mcI$sp$(final TensorLike $this, final Function1 fn) {
      $this.foreachKey$mcI$sp(fn);
   }

   default void foreachKey$mcI$sp(final Function1 fn) {
      this.foreachKey(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcID$sp$(final TensorLike $this, final Function2 fn) {
      $this.foreachPair$mcID$sp(fn);
   }

   default void foreachPair$mcID$sp(final Function2 fn) {
      this.foreachPair(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcIF$sp$(final TensorLike $this, final Function2 fn) {
      $this.foreachPair$mcIF$sp(fn);
   }

   default void foreachPair$mcIF$sp(final Function2 fn) {
      this.foreachPair(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcII$sp$(final TensorLike $this, final Function2 fn) {
      $this.foreachPair$mcII$sp(fn);
   }

   default void foreachPair$mcII$sp(final Function2 fn) {
      this.foreachPair(fn);
   }

   // $FF: synthetic method
   static void foreachPair$mcIJ$sp$(final TensorLike $this, final Function2 fn) {
      $this.foreachPair$mcIJ$sp(fn);
   }

   default void foreachPair$mcIJ$sp(final Function2 fn) {
      this.foreachPair(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcD$sp$(final TensorLike $this, final Function1 fn) {
      $this.foreachValue$mcD$sp(fn);
   }

   default void foreachValue$mcD$sp(final Function1 fn) {
      this.foreachValue(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcF$sp$(final TensorLike $this, final Function1 fn) {
      $this.foreachValue$mcF$sp(fn);
   }

   default void foreachValue$mcF$sp(final Function1 fn) {
      this.foreachValue(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcI$sp$(final TensorLike $this, final Function1 fn) {
      $this.foreachValue$mcI$sp(fn);
   }

   default void foreachValue$mcI$sp(final Function1 fn) {
      this.foreachValue(fn);
   }

   // $FF: synthetic method
   static void foreachValue$mcJ$sp$(final TensorLike $this, final Function1 fn) {
      $this.foreachValue$mcJ$sp(fn);
   }

   default void foreachValue$mcJ$sp(final Function1 fn) {
      this.foreachValue(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcID$sp$(final TensorLike $this, final Function2 fn) {
      return $this.forall$mcID$sp(fn);
   }

   default boolean forall$mcID$sp(final Function2 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcIF$sp$(final TensorLike $this, final Function2 fn) {
      return $this.forall$mcIF$sp(fn);
   }

   default boolean forall$mcIF$sp(final Function2 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcII$sp$(final TensorLike $this, final Function2 fn) {
      return $this.forall$mcII$sp(fn);
   }

   default boolean forall$mcII$sp(final Function2 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcIJ$sp$(final TensorLike $this, final Function2 fn) {
      return $this.forall$mcIJ$sp(fn);
   }

   default boolean forall$mcIJ$sp(final Function2 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcD$sp$(final TensorLike $this, final Function1 fn) {
      return $this.forall$mcD$sp(fn);
   }

   default boolean forall$mcD$sp(final Function1 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcF$sp$(final TensorLike $this, final Function1 fn) {
      return $this.forall$mcF$sp(fn);
   }

   default boolean forall$mcF$sp(final Function1 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcI$sp$(final TensorLike $this, final Function1 fn) {
      return $this.forall$mcI$sp(fn);
   }

   default boolean forall$mcI$sp(final Function1 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static boolean forall$mcJ$sp$(final TensorLike $this, final Function1 fn) {
      return $this.forall$mcJ$sp(fn);
   }

   default boolean forall$mcJ$sp(final Function1 fn) {
      return this.forall(fn);
   }

   // $FF: synthetic method
   static void $anonfun$forall$1(final Function2 fn$3, final Object nonLocalReturnKey1$1, final Object k, final Object v) {
      if (!BoxesRunTime.unboxToBoolean(fn$3.apply(k, v))) {
         throw new NonLocalReturnControl.mcZ.sp(nonLocalReturnKey1$1, false);
      }
   }

   // $FF: synthetic method
   static void $anonfun$forall$2(final Function1 fn$4, final Object nonLocalReturnKey2$1, final Object v) {
      if (!BoxesRunTime.unboxToBoolean(fn$4.apply(v))) {
         throw new NonLocalReturnControl.mcZ.sp(nonLocalReturnKey2$1, false);
      }
   }

   static void $init$(final TensorLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
