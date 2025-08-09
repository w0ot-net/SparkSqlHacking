package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001B\f\u0019\u0001\rB\u0001B\f\u0001\u0003\u0006\u0004%\ta\f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005a!)\u0011\t\u0001C\u0001\u0005\")q\t\u0001C!\u0011\")A\n\u0001C!\u001b\")\u0011\f\u0001C!_!1!\f\u0001C!9mCQA\u0019\u0001\u0005B\rDQA\u001a\u0001\u0005B\u001dDQ\u0001\u001b\u0001\u0005B%DQA\u001d\u0001\u0005BMDQ\u0001\u001e\u0001\u0005B!CQ!\u001e\u0001\u0005B!CaA\u001e\u0001\u0005Ba9\b\"B?\u0001\t\u0003B\u0005B\u0002@\u0001\t\u0003br\u0010C\u0004\u0002\u001a\u0001!\t\u0005H@\b\u000f\u0005u\u0001\u0004#\u0001\u0002 \u00191q\u0003\u0007E\u0001\u0003CAa!Q\n\u0005\u0002\u0005M\u0002bBA\u001b'\u0011\u0005\u0011q\u0007\u0005\n\u0003\u000b\u001a\u0012\u0011!C\u0005\u0003\u000f\u00121\u0002R3og\u00164Vm\u0019;pe*\u0011\u0011DG\u0001\u0007Y&t\u0017\r\\4\u000b\u0005ma\u0012AA7m\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0011R\u0003CA\u0013)\u001b\u00051#\"A\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005%2#AB!osJ+g\r\u0005\u0002,Y5\t\u0001$\u0003\u0002.1\t1a+Z2u_J\faA^1mk\u0016\u001cX#\u0001\u0019\u0011\u0007\u0015\n4'\u0003\u00023M\t)\u0011I\u001d:bsB\u0011Q\u0005N\u0005\u0003k\u0019\u0012a\u0001R8vE2,\u0007fA\u00018{A\u0011\u0001hO\u0007\u0002s)\u0011!\bH\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001\u001f:\u0005\u0015\u0019\u0016N\\2fC\u0005q\u0014!\u0002\u001a/a9\u0002\u0014a\u0002<bYV,7\u000f\t\u0015\u0004\u0005]j\u0014A\u0002\u001fj]&$h\b\u0006\u0002D\tB\u00111\u0006\u0001\u0005\u0006]\r\u0001\r\u0001\r\u0015\u0004\t^j\u0004fA\u00028{\u0005!1/\u001b>f+\u0005I\u0005CA\u0013K\u0013\tYeEA\u0002J]R\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u001dB\u0011qJ\u0016\b\u0003!R\u0003\"!\u0015\u0014\u000e\u0003IS!a\u0015\u0012\u0002\rq\u0012xn\u001c;?\u0013\t)f%\u0001\u0004Qe\u0016$WMZ\u0005\u0003/b\u0013aa\u0015;sS:<'BA+'\u0003\u001d!x.\u0011:sCf\f\u0001\"Y:Ce\u0016,'0Z\u000b\u00029B\u0019Q,Y\u001a\u000e\u0003yS!!G0\u000b\u0003\u0001\faA\u0019:fKj,\u0017BA\u0017_\u0003\u0015\t\u0007\u000f\u001d7z)\t\u0019D\rC\u0003f\u0011\u0001\u0007\u0011*A\u0001j\u0003\u0011\u0019w\u000e]=\u0016\u0003\r\u000ba!Z9vC2\u001cHC\u00016n!\t)3.\u0003\u0002mM\t9!i\\8mK\u0006t\u0007\"\u00028\u000b\u0001\u0004y\u0017!B8uQ\u0016\u0014\bCA\u0013q\u0013\t\thEA\u0002B]f\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u0013\u0006Qa.^7BGRLg/Z:\u0002\u00179,XNT8ou\u0016\u0014xn]\u0001\u0011i>\u001c\u0006/\u0019:tK^KG\u000f[*ju\u0016$\"\u0001_>\u0011\u0005-J\u0018B\u0001>\u0019\u00051\u0019\u0006/\u0019:tKZ+7\r^8s\u0011\u0015ah\u00021\u0001J\u0003\rqgN_\u0001\u0007CJ<W.\u0019=\u0002\u0011%$XM]1u_J,\"!!\u0001\u0011\r\u0005\r\u0011QBA\n\u001d\u0011\t)!!\u0003\u000f\u0007E\u000b9!C\u0001(\u0013\r\tYAJ\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ty!!\u0005\u0003\u0011%#XM]1u_JT1!a\u0003'!\u0015)\u0013QC%4\u0013\r\t9B\n\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u001d\u0005\u001cG/\u001b<f\u0013R,'/\u0019;pe\"\u001a\u0001aN\u001f\u0002\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0003WM\u0019Ba\u0005\u0013\u0002$A!\u0011QEA\u0018\u001b\t\t9C\u0003\u0003\u0002*\u0005-\u0012AA5p\u0015\t\ti#\u0001\u0003kCZ\f\u0017\u0002BA\u0019\u0003O\u0011AbU3sS\u0006d\u0017N_1cY\u0016$\"!a\b\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011HA !\u0011)\u00131\b\u0019\n\u0007\u0005ubE\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003\u0003*\u0002\u0019A\"\u0002\u0005\u00114\bfA\u000b8{\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\n\t\u0005\u0003\u0017\n\t&\u0004\u0002\u0002N)!\u0011qJA\u0016\u0003\u0011a\u0017M\\4\n\t\u0005M\u0013Q\n\u0002\u0007\u001f\nTWm\u0019;)\u0007M9T\bK\u0002\u0013ou\u0002"
)
public class DenseVector implements Vector {
   private final double[] values;

   public static Option unapply(final DenseVector dv) {
      return DenseVector$.MODULE$.unapply(dv);
   }

   public void foreach(final Function2 f) {
      Vector.foreach$(this, f);
   }

   public void foreachActive(final Function2 f) {
      Vector.foreachActive$(this, f);
   }

   public void foreachNonZero(final Function2 f) {
      Vector.foreachNonZero$(this, f);
   }

   public SparseVector toSparse() {
      return Vector.toSparse$(this);
   }

   public DenseVector toDense() {
      return Vector.toDense$(this);
   }

   public Vector compressed() {
      return Vector.compressed$(this);
   }

   public Vector compressedWithNNZ(final int nnz) {
      return Vector.compressedWithNNZ$(this, nnz);
   }

   public double dot(final Vector v) {
      return Vector.dot$(this, v);
   }

   public Iterator nonZeroIterator() {
      return Vector.nonZeroIterator$(this);
   }

   public double[] values() {
      return this.values;
   }

   public int size() {
      return this.values().length;
   }

   public String toString() {
      return .MODULE$.wrapDoubleArray(this.values()).mkString("[", ",", "]");
   }

   public double[] toArray() {
      return this.values();
   }

   public breeze.linalg.Vector asBreeze() {
      return new breeze.linalg.DenseVector.mcD.sp(this.values());
   }

   public double apply(final int i) {
      return this.values()[i];
   }

   public DenseVector copy() {
      return new DenseVector((double[])this.values().clone());
   }

   public boolean equals(final Object other) {
      return Vector.equals$(this, other);
   }

   public int hashCode() {
      int result = 31 + this.size();
      int i = 0;
      int end = this.values().length;

      for(int nnz = 0; i < end && nnz < Vectors$.MODULE$.MAX_HASH_NNZ(); ++i) {
         double v = this.values()[i];
         if (v != (double)0.0F) {
            result = 31 * result + i;
            long bits = Double.doubleToLongBits(this.values()[i]);
            result = 31 * result + (int)(bits ^ bits >>> 32);
            ++nnz;
         }
      }

      return result;
   }

   public int numActives() {
      return this.size();
   }

   public int numNonzeros() {
      IntRef nnz = IntRef.create(0);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.doubleArrayOps(this.values()), (JFunction1.mcVD.sp)(v) -> {
         if (v != (double)0.0F) {
            ++nnz.elem;
         }
      });
      return nnz.elem;
   }

   public SparseVector toSparseWithSize(final int nnz) {
      int[] ii = new int[nnz];
      double[] vv = new double[nnz];
      IntRef k = IntRef.create(0);
      this.foreachNonZero((JFunction2.mcVID.sp)(i, v) -> {
         ii[k.elem] = i;
         vv[k.elem] = v;
         ++k.elem;
      });
      return new SparseVector(this.size(), ii, vv);
   }

   public int argmax() {
      if (this.size() == 0) {
         return -1;
      } else {
         int maxIdx = 0;
         double maxValue = this.values()[0];

         for(int i = 1; i < this.size(); ++i) {
            if (this.values()[i] > maxValue) {
               maxIdx = i;
               maxValue = this.values()[i];
            }
         }

         return maxIdx;
      }
   }

   public Iterator iterator() {
      double[] localValues = this.values();
      return scala.package..MODULE$.Iterator().tabulate(this.size(), (i) -> $anonfun$iterator$2(localValues, BoxesRunTime.unboxToInt(i)));
   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$iterator$2(final double[] localValues$1, final int i) {
      return new Tuple2.mcID.sp(i, localValues$1[i]);
   }

   public DenseVector(final double[] values) {
      this.values = values;
      Vector.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
