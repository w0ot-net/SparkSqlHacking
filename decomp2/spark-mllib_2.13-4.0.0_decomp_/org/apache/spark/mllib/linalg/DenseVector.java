package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.json4s.JObject;
import org.json4s.JValue;
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

@SQLUserDefinedType(
   udt = VectorUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg\u0001\u0002\u000e\u001c\u0001\u0019B\u0001\"\r\u0001\u0003\u0006\u0004%\tA\r\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005g!)A\t\u0001C\u0001\u000b\")!\n\u0001C!\u0017\")\u0001\u000b\u0001C!#\")Q\f\u0001C!e!1q\f\u0001C!?\u0001DQa\u001a\u0001\u0005B!DQ\u0001\u001c\u0001\u0005B5DQ!\u001d\u0001\u0005BIDQa\u001f\u0001\u0005BqDQ! \u0001\u0005B-Ca!a\u0001\u0001\t\u0003Z\u0005\u0002CA\u0004\u0001\u0011\u00053$!\u0003\t\r\u0005U\u0001\u0001\"\u0011L\u0011\u001d\ti\u0002\u0001C!\u0003?Aq!a\n\u0001\t\u0003\nI\u0003\u0003\u0005\u0002>\u0001!\teHA \u0011!\tI\u0006\u0001C!?\u0005}raBA=7!\u0005\u00111\u0010\u0004\u00075mA\t!! \t\r\u0011+B\u0011AAH\u0011\u001d\t\t*\u0006C\u0001\u0003'Cq!!*\u0016\t\u0003\t9\u000bC\u0005\u00020V\t\t\u0011\"\u0003\u00022\nYA)\u001a8tKZ+7\r^8s\u0015\taR$\u0001\u0004mS:\fGn\u001a\u0006\u0003=}\tQ!\u001c7mS\nT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\r\u0001q%\f\t\u0003Q-j\u0011!\u000b\u0006\u0002U\u0005)1oY1mC&\u0011A&\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u00059zS\"A\u000e\n\u0005AZ\"A\u0002,fGR|'/\u0001\u0004wC2,Xm]\u000b\u0002gA\u0019\u0001\u0006\u000e\u001c\n\u0005UJ#!B!se\u0006L\bC\u0001\u00158\u0013\tA\u0014F\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0003i\u0002\u0005CA\u001e?\u001b\u0005a$BA\u001f \u0003)\tgN\\8uCRLwN\\\u0005\u0003\u007fq\u0012QaU5oG\u0016\f\u0013!Q\u0001\u0006c9\u0002d\u0006M\u0001\bm\u0006dW/Z:!Q\r\u0011!\bQ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0019;\u0005C\u0001\u0018\u0001\u0011\u0015\t4\u00011\u00014Q\r9%\b\u0011\u0015\u0004\u0007i\u0002\u0015\u0001B:ju\u0016,\u0012\u0001\u0014\t\u0003Q5K!AT\u0015\u0003\u0007%sG\u000fK\u0002\u0005u\u0001\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002%B\u00111K\u0017\b\u0003)b\u0003\"!V\u0015\u000e\u0003YS!aV\u0013\u0002\rq\u0012xn\u001c;?\u0013\tI\u0016&\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-*\u0003\u001d!x.\u0011:sCfD3A\u0002\u001eA\u0003!\t7O\u0011:fKj,W#A1\u0011\u0007\t4g'D\u0001d\u0015\taBMC\u0001f\u0003\u0019\u0011'/Z3{K&\u0011\u0001gY\u0001\u0006CB\u0004H.\u001f\u000b\u0003m%DQA\u001b\u0005A\u00021\u000b\u0011!\u001b\u0015\u0004\u0011i\u0002\u0015\u0001B2paf,\u0012A\u0012\u0015\u0004\u0013iz\u0017%\u00019\u0002\u000bEr\u0013G\f\u0019\u0002\r\u0015\fX/\u00197t)\t\u0019h\u000f\u0005\u0002)i&\u0011Q/\u000b\u0002\b\u0005>|G.Z1o\u0011\u00159(\u00021\u0001y\u0003\u0015yG\u000f[3s!\tA\u00130\u0003\u0002{S\t\u0019\u0011I\\=\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012\u0001T\u0001\u000b]Vl\u0017i\u0019;jm\u0016\u001c\bf\u0001\u0007;\u007f\u0006\u0012\u0011\u0011A\u0001\u0006c9\"d\u0006M\u0001\f]Vlgj\u001c8{KJ|7\u000fK\u0002\u000eu}\f\u0001\u0003^8Ta\u0006\u00148/Z,ji\"\u001c\u0016N_3\u0015\t\u0005-\u0011\u0011\u0003\t\u0004]\u00055\u0011bAA\b7\ta1\u000b]1sg\u00164Vm\u0019;pe\"1\u00111\u0003\bA\u00021\u000b1A\u001c8{\u0003\u0019\t'oZ7bq\"\"qBOA\rC\t\tY\"A\u00032]Ur\u0003'\u0001\u0004u_*\u001bxN\\\u000b\u0002%\"\"\u0001COA\u0012C\t\t)#A\u00032]Yr\u0003'\u0001\u0003bg6cUCAA\u0016!\u0011\ti#!\u000e\u000e\u0005\u0005=\"b\u0001\u000f\u00022)\u0019\u00111G\u0010\u0002\u00055d\u0017b\u0001\u000e\u00020!\"\u0011COA\u001dC\t\tY$A\u00033]Ar\u0003'\u0001\u0005ji\u0016\u0014\u0018\r^8s+\t\t\t\u0005\u0005\u0004\u0002D\u00055\u00131\u000b\b\u0005\u0003\u000b\nIED\u0002V\u0003\u000fJ\u0011AK\u0005\u0004\u0003\u0017J\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003\u001f\n\tF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\r\tY%\u000b\t\u0006Q\u0005UCJN\u0005\u0004\u0003/J#A\u0002+va2,''\u0001\bbGRLg/Z%uKJ\fGo\u001c:)\u0007\u0001Q\u0004\tK\u0004\u0001\u0003?\ny'!\u001d\u0011\t\u0005\u0005\u00141N\u0007\u0003\u0003GRA!!\u001a\u0002h\u0005)A/\u001f9fg*\u0019\u0011\u0011N\u0010\u0002\u0007M\fH.\u0003\u0003\u0002n\u0005\r$AE*R\u0019V\u001bXM\u001d#fM&tW\r\u001a+za\u0016\f1!\u001e3uG\t\t\u0019\bE\u0002/\u0003kJ1!a\u001e\u001c\u0005%1Vm\u0019;peV#E+A\u0006EK:\u001cXMV3di>\u0014\bC\u0001\u0018\u0016'\u0011)r%a \u0011\t\u0005\u0005\u00151R\u0007\u0003\u0003\u0007SA!!\"\u0002\b\u0006\u0011\u0011n\u001c\u0006\u0003\u0003\u0013\u000bAA[1wC&!\u0011QRAB\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\tY(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005U\u00151\u0014\t\u0005Q\u0005]5'C\u0002\u0002\u001a&\u0012aa\u00149uS>t\u0007BBAO/\u0001\u0007a)\u0001\u0002em\"\"qCOAQC\t\t\u0019+A\u00032]Mr\u0003'\u0001\u0004ge>lW\n\u0014\u000b\u0004\r\u0006%\u0006bBAV1\u0001\u0007\u00111F\u0001\u0002m\"\"\u0001DOA\u001d\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\f\u0005\u0003\u00026\u0006mVBAA\\\u0015\u0011\tI,a\"\u0002\t1\fgnZ\u0005\u0005\u0003{\u000b9L\u0001\u0004PE*,7\r\u001e\u0015\u0005+i\n\t\u000b\u000b\u0003\u0015u\u0005\u0005\u0006"
)
public class DenseVector implements Vector {
   private final double[] values;

   public static DenseVector fromML(final org.apache.spark.ml.linalg.DenseVector v) {
      return DenseVector$.MODULE$.fromML(v);
   }

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

   public double dot(final Vector v) {
      return Vector.dot$(this, v);
   }

   public Iterator nonZeroIterator() {
      return Vector.nonZeroIterator$(this);
   }

   public double sparsity() {
      return Vector.sparsity$(this);
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

   public String toJson() {
      JObject jValue = org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(1)), (x) -> $anonfun$toJson$1(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.values()).toImmutableArraySeq()), (x) -> $anonfun$toJson$2(BoxesRunTime.unboxToInt(x)), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$4(BoxesRunTime.unboxToDouble(x))));
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public org.apache.spark.ml.linalg.DenseVector asML() {
      return new org.apache.spark.ml.linalg.DenseVector(this.values());
   }

   public Iterator iterator() {
      double[] localValues = this.values();
      return scala.package..MODULE$.Iterator().tabulate(this.size(), (i) -> $anonfun$iterator$2(localValues, BoxesRunTime.unboxToInt(i)));
   }

   public Iterator activeIterator() {
      return this.iterator();
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$1(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$2(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$4(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
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
