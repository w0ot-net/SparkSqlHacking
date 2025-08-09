package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@SQLUserDefinedType(
   udt = VectorUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h\u0001\u0002\u0010 \u0001)B\u0001\"\u000e\u0001\u0003\u0006\u0004%\tE\u000e\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005o!AQ\t\u0001BC\u0002\u0013\u0005a\t\u0003\u0005L\u0001\t\u0005\t\u0015!\u0003H\u0011!i\u0005A!b\u0001\n\u0003q\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011B(\t\u000bY\u0003A\u0011A,\t\u000b\u0001\u0004A\u0011I1\t\u000b5\u0004A\u0011\t(\t\u000b=\u0004A\u0011\t9\t\rQ\u0004A\u0011I\u0012v\u0011\u0015a\b\u0001\"\u0011~\u0011\u001d\t\t\u0001\u0001C!\u0003\u0007Aq!!\u0006\u0001\t\u0003\n9\u0002\u0003\u0004\u0002\u001a\u0001!\tE\u000e\u0005\u0007\u0003C\u0001A\u0011\t\u001c\t\u0011\u0005\u0015\u0002\u0001\"\u0011 \u0003OAa!!\f\u0001\t\u00032\u0004\u0002CA\u001b\u0001\u0011\u00051%a\u000e\t\u000f\u0005u\u0002\u0001\"\u0011\u0002@!9\u0011q\t\u0001\u0005B\u0005%\u0003\u0002CA/\u0001\u0011\u00053%a\u0018\t\u0011\u0005e\u0004\u0001\"\u0011$\u0003?:q!!' \u0011\u0003\tYJ\u0002\u0004\u001f?!\u0005\u0011Q\u0014\u0005\u0007-f!\t!a,\t\u000f\u0005E\u0016\u0004\"\u0001\u00024\"9\u00111Z\r\u0005\u0002\u00055\u0007\"CAk3\u0005\u0005I\u0011BAl\u00051\u0019\u0006/\u0019:tKZ+7\r^8s\u0015\t\u0001\u0013%\u0001\u0004mS:\fGn\u001a\u0006\u0003E\r\nQ!\u001c7mS\nT!\u0001J\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0019:\u0013AB1qC\u000eDWMC\u0001)\u0003\ry'oZ\u0002\u0001'\r\u00011&\r\t\u0003Y=j\u0011!\f\u0006\u0002]\u0005)1oY1mC&\u0011\u0001'\f\u0002\u0007\u0003:L(+\u001a4\u0011\u0005I\u001aT\"A\u0010\n\u0005Qz\"A\u0002,fGR|'/\u0001\u0003tSj,W#A\u001c\u0011\u00051B\u0014BA\u001d.\u0005\rIe\u000e\u001e\u0015\u0004\u0003m\n\u0005C\u0001\u001f@\u001b\u0005i$B\u0001 $\u0003)\tgN\\8uCRLwN\\\u0005\u0003\u0001v\u0012QaU5oG\u0016\f\u0013AQ\u0001\u0006c9\u0002d\u0006M\u0001\u0006g&TX\r\t\u0015\u0004\u0005m\n\u0015aB5oI&\u001cWm]\u000b\u0002\u000fB\u0019A\u0006S\u001c\n\u0005%k#!B!se\u0006L\bfA\u0002<\u0003\u0006A\u0011N\u001c3jG\u0016\u001c\b\u0005K\u0002\u0005w\u0005\u000baA^1mk\u0016\u001cX#A(\u0011\u00071B\u0005\u000b\u0005\u0002-#&\u0011!+\f\u0002\u0007\t>,(\r\\3)\u0007\u0015Y\u0014)A\u0004wC2,Xm\u001d\u0011)\u0007\u0019Y\u0014)\u0001\u0004=S:LGO\u0010\u000b\u00051f[V\f\u0005\u00023\u0001!)Qg\u0002a\u0001o!\u001a\u0011lO!\t\u000b\u0015;\u0001\u0019A$)\u0007m[\u0014\tC\u0003N\u000f\u0001\u0007q\nK\u0002^w\u0005C3aB\u001eB\u0003!!xn\u0015;sS:<G#\u00012\u0011\u0005\rTgB\u00013i!\t)W&D\u0001g\u0015\t9\u0017&\u0001\u0004=e>|GOP\u0005\u0003S6\na\u0001\u0015:fI\u00164\u0017BA6m\u0005\u0019\u0019FO]5oO*\u0011\u0011.L\u0001\bi>\f%O]1zQ\rI1(Q\u0001\u0005G>\u0004\u00180F\u0001YQ\rQ1H]\u0011\u0002g\u0006)\u0011GL\u0019/a\u0005A\u0011m\u001d\"sK\u0016TX-F\u0001w!\r98\u0010U\u0007\u0002q*\u0011\u0001%\u001f\u0006\u0002u\u00061!M]3fu\u0016L!\u0001\u000e=\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005As\b\"B@\r\u0001\u00049\u0014!A5\u0002\r\u0015\fX/\u00197t)\u0011\t)!a\u0003\u0011\u00071\n9!C\u0002\u0002\n5\u0012qAQ8pY\u0016\fg\u000eC\u0004\u0002\u000e5\u0001\r!a\u0004\u0002\u000b=$\b.\u001a:\u0011\u00071\n\t\"C\u0002\u0002\u00145\u00121!\u00118z\u0003!A\u0017m\u001d5D_\u0012,G#A\u001c\u0002\u00159,X.Q2uSZ,7\u000f\u000b\u0003\u0010w\u0005u\u0011EAA\u0010\u0003\u0015\td\u0006\u000e\u00181\u0003-qW/\u001c(p]j,'o\\:)\tAY\u0014QD\u0001\u0011i>\u001c\u0006/\u0019:tK^KG\u000f[*ju\u0016$2\u0001WA\u0015\u0011\u0019\tY#\u0005a\u0001o\u0005\u0019aN\u001c>\u0002\r\u0005\u0014x-\\1yQ\u0011\u00112(!\r\"\u0005\u0005M\u0012!B\u0019/k9\u0002\u0014!B:mS\u000e,Gc\u0001-\u0002:!1\u00111H\nA\u0002\u001d\u000bqb]3mK\u000e$X\rZ%oI&\u001cWm]\u0001\u0007i>T5o\u001c8\u0016\u0003\tDC\u0001F\u001e\u0002D\u0005\u0012\u0011QI\u0001\u0006c92d\u0006M\u0001\u0005CNlE*\u0006\u0002\u0002LA!\u0011QJA+\u001b\t\tyEC\u0002!\u0003#R1!a\u0015$\u0003\tiG.C\u0002\u001f\u0003\u001fBC!F\u001e\u0002Z\u0005\u0012\u00111L\u0001\u0006e9\u0002d\u0006M\u0001\tSR,'/\u0019;peV\u0011\u0011\u0011\r\t\u0007\u0003G\ni'a\u001d\u000f\t\u0005\u0015\u0014\u0011\u000e\b\u0004K\u0006\u001d\u0014\"\u0001\u0018\n\u0007\u0005-T&A\u0004qC\u000e\\\u0017mZ3\n\t\u0005=\u0014\u0011\u000f\u0002\t\u0013R,'/\u0019;pe*\u0019\u00111N\u0017\u0011\u000b1\n)h\u000e)\n\u0007\u0005]TF\u0001\u0004UkBdWMM\u0001\u000fC\u000e$\u0018N^3Ji\u0016\u0014\u0018\r^8sQ\r\u00011(\u0011\u0015\b\u0001\u0005}\u0014qRAI!\u0011\t\t)a#\u000e\u0005\u0005\r%\u0002BAC\u0003\u000f\u000bQ\u0001^=qKNT1!!#$\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u0003\u001b\u000b\u0019I\u0001\nT#2+6/\u001a:EK\u001aLg.\u001a3UsB,\u0017aA;ei\u000e\u0012\u00111\u0013\t\u0004e\u0005U\u0015bAAL?\tIa+Z2u_J,F\tV\u0001\r'B\f'o]3WK\u000e$xN\u001d\t\u0003ee\u0019B!G\u0016\u0002 B!\u0011\u0011UAV\u001b\t\t\u0019K\u0003\u0003\u0002&\u0006\u001d\u0016AA5p\u0015\t\tI+\u0001\u0003kCZ\f\u0017\u0002BAW\u0003G\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!a'\u0002\u000fUt\u0017\r\u001d9msR!\u0011QWAa!\u0015a\u0013qWA^\u0013\r\tI,\f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\r1\nilN$P\u0013\r\ty,\f\u0002\u0007)V\u0004H.Z\u001a\t\r\u0005\r7\u00041\u0001Y\u0003\t\u0019h\u000f\u000b\u0003\u001cw\u0005\u001d\u0017EAAe\u0003\u0015\tdf\r\u00181\u0003\u00191'o\\7N\u0019R\u0019\u0001,a4\t\u000f\u0005EG\u00041\u0001\u0002L\u0005\ta\u000f\u000b\u0003\u001dw\u0005e\u0013\u0001D<sSR,'+\u001a9mC\u000e,GCAAm!\u0011\tY.!9\u000e\u0005\u0005u'\u0002BAp\u0003O\u000bA\u0001\\1oO&!\u00111]Ao\u0005\u0019y%M[3di\"\"\u0011dOAdQ\u0011A2(a2"
)
public class SparseVector implements Vector {
   private final int size;
   private final int[] indices;
   private final double[] values;

   public static SparseVector fromML(final org.apache.spark.ml.linalg.SparseVector v) {
      return SparseVector$.MODULE$.fromML(v);
   }

   public static Option unapply(final SparseVector sv) {
      return SparseVector$.MODULE$.unapply(sv);
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

   public int size() {
      return this.size;
   }

   public int[] indices() {
      return this.indices;
   }

   public double[] values() {
      return this.values;
   }

   public String toString() {
      int var10000 = this.size();
      return "(" + var10000 + "," + .MODULE$.wrapIntArray(this.indices()).mkString("[", ",", "]") + "," + .MODULE$.wrapDoubleArray(this.values()).mkString("[", ",", "]") + ")";
   }

   public double[] toArray() {
      double[] data = new double[this.size()];
      int i = 0;

      for(int nnz = this.indices().length; i < nnz; ++i) {
         data[this.indices()[i]] = this.values()[i];
      }

      return data;
   }

   public SparseVector copy() {
      return new SparseVector(this.size(), (int[])this.indices().clone(), (double[])this.values().clone());
   }

   public breeze.linalg.Vector asBreeze() {
      return new breeze.linalg.SparseVector.mcD.sp(this.indices(), this.values(), this.size(), breeze.storage.Zero..MODULE$.DoubleZero());
   }

   public double apply(final int i) {
      if (i >= 0 && i < this.size()) {
         int j = Arrays.binarySearch(this.indices(), i);
         return j < 0 ? (double)0.0F : this.values()[j];
      } else {
         throw new IndexOutOfBoundsException("Index " + i + " out of bounds [0, " + this.size() + ")");
      }
   }

   public boolean equals(final Object other) {
      return Vector.equals$(this, other);
   }

   public int hashCode() {
      int result = 31 + this.size();
      int end = this.values().length;
      int k = 0;

      for(int nnz = 0; k < end && nnz < Vectors$.MODULE$.MAX_HASH_NNZ(); ++k) {
         double v = this.values()[k];
         if (v != (double)0.0F) {
            int i = this.indices()[k];
            result = 31 * result + i;
            long bits = Double.doubleToLongBits(v);
            result = 31 * result + (int)(bits ^ bits >>> 32);
            ++nnz;
         }
      }

      return result;
   }

   public int numActives() {
      return this.values().length;
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
      if (nnz == this.numActives()) {
         return this;
      } else {
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
   }

   public int argmax() {
      if (this.size() == 0) {
         return -1;
      } else if (this.numActives() == 0) {
         return 0;
      } else {
         int maxIdx = this.indices()[0];
         double maxValue = this.values()[0];
         int maxJ = 0;
         int j = 1;

         int na;
         for(na = this.numActives(); j < na; ++j) {
            double v = this.values()[j];
            if (v > maxValue) {
               maxValue = v;
               maxIdx = this.indices()[j];
               maxJ = j;
            }
         }

         if (maxValue <= (double)0.0F && na < this.size()) {
            if (maxValue == (double)0.0F) {
               if (maxJ < maxIdx) {
                  int k;
                  for(k = 0; k < maxJ && this.indices()[k] == k; ++k) {
                  }

                  maxIdx = k;
               }
            } else {
               int k;
               for(k = 0; k < na && this.indices()[k] == k; ++k) {
               }

               maxIdx = k;
            }
         }

         return maxIdx;
      }
   }

   public SparseVector slice(final int[] selectedIndices) {
      IntRef currentIdx = IntRef.create(0);
      Tuple2 var5 = scala.collection.ArrayOps..MODULE$.unzip$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.flatMap$extension(.MODULE$.intArrayOps(selectedIndices), (origIdx) -> $anonfun$slice$1(this, currentIdx, BoxesRunTime.unboxToInt(origIdx)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))), .MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Double());
      if (var5 != null) {
         int[] sliceInds = (int[])var5._1();
         double[] sliceVals = (double[])var5._2();
         Tuple2 var4 = new Tuple2(sliceInds, sliceVals);
         int[] sliceInds = (int[])var4._1();
         double[] sliceVals = (double[])var4._2();
         return new SparseVector(selectedIndices.length, sliceInds, sliceVals);
      } else {
         throw new MatchError(var5);
      }
   }

   public String toJson() {
      JObject jValue = org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("type"), BoxesRunTime.boxToInteger(0)), (x) -> $anonfun$toJson$5(BoxesRunTime.unboxToInt(x))), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("size"), BoxesRunTime.boxToInteger(this.size())), (x) -> $anonfun$toJson$6(BoxesRunTime.unboxToInt(x)), (x) -> $anonfun$toJson$7(BoxesRunTime.unboxToInt(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("indices"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.indices()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$9(BoxesRunTime.unboxToInt(x)))))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("values"), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.values()).toImmutableArraySeq()), (s) -> org.json4s.JsonDSL..MODULE$.seq2jvalue(s, (x) -> $anonfun$toJson$11(BoxesRunTime.unboxToDouble(x)))));
      return org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
   }

   public org.apache.spark.ml.linalg.SparseVector asML() {
      return new org.apache.spark.ml.linalg.SparseVector(this.size(), this.indices(), this.values());
   }

   public Iterator iterator() {
      int localSize = this.size();
      int localNumActives = this.numActives();
      int[] localIndices = this.indices();
      double[] localValues = this.values();
      return new Iterator(localIndices, localSize, localNumActives, localValues) {
         private int i;
         private int j;
         private int k;
         private final int[] localIndices$1;
         private final int localSize$1;
         private final int localNumActives$1;
         private final double[] localValues$2;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public scala.collection.immutable.Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private int i() {
            return this.i;
         }

         private void i_$eq(final int x$1) {
            this.i = x$1;
         }

         private int j() {
            return this.j;
         }

         private void j_$eq(final int x$1) {
            this.j = x$1;
         }

         private int k() {
            return this.k;
         }

         private void k_$eq(final int x$1) {
            this.k = x$1;
         }

         public boolean hasNext() {
            return this.i() < this.localSize$1;
         }

         public Tuple2 next() {
            double var10000;
            if (this.i() == this.k()) {
               this.j_$eq(this.j() + 1);
               this.k_$eq(this.j() < this.localNumActives$1 ? this.localIndices$1[this.j()] : -1);
               var10000 = this.localValues$2[this.j() - 1];
            } else {
               var10000 = (double)0.0F;
            }

            double v = var10000;
            this.i_$eq(this.i() + 1);
            return new Tuple2.mcID.sp(this.i() - 1, v);
         }

         public {
            this.localIndices$1 = localIndices$1;
            this.localSize$1 = localSize$1;
            this.localNumActives$1 = localNumActives$1;
            this.localValues$2 = localValues$2;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.i = 0;
            this.j = 0;
            this.k = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.headOption$extension(.MODULE$.intArrayOps(localIndices$1)).getOrElse((JFunction0.mcI.sp)() -> -1));
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Iterator activeIterator() {
      int[] localIndices = this.indices();
      double[] localValues = this.values();
      return scala.package..MODULE$.Iterator().tabulate(this.numActives(), (j) -> $anonfun$activeIterator$1(localIndices, localValues, BoxesRunTime.unboxToInt(j)));
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$slice$1(final SparseVector $this, final IntRef currentIdx$1, final int origIdx) {
      int iIdx = Arrays.binarySearch($this.indices(), origIdx);
      Iterator i_v = iIdx >= 0 ? scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2.mcID.sp(currentIdx$1.elem, $this.values()[iIdx])}))) : scala.package..MODULE$.Iterator().apply(scala.collection.immutable.Nil..MODULE$);
      ++currentIdx$1.elem;
      return i_v;
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$5(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$6(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$7(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$9(final int x) {
      return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
   }

   // $FF: synthetic method
   public static final JValue $anonfun$toJson$11(final double x) {
      return org.json4s.JsonDSL..MODULE$.double2jvalue(x);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$activeIterator$1(final int[] localIndices$2, final double[] localValues$3, final int j) {
      return new Tuple2.mcID.sp(localIndices$2[j], localValues$3[j]);
   }

   public SparseVector(final int size, final int[] indices, final double[] values) {
      this.size = size;
      this.indices = indices;
      this.values = values;
      Vector.$init$(this);
      .MODULE$.require(size >= 0, () -> "The size of the requested sparse vector must be no less than 0.");
      .MODULE$.require(indices.length == values.length, () -> {
         int var10000 = this.indices().length;
         return "Sparse vectors require that the dimension of the indices match the dimension of the values. You provided " + var10000 + " indices and  " + this.values().length + " values.";
      });
      .MODULE$.require(indices.length <= size, () -> {
         int var10000 = this.indices().length;
         return "You provided " + var10000 + " indices and values, which exceeds the specified vector size " + this.size() + ".";
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
