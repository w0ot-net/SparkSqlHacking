package org.apache.spark.ml.linalg;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function0;
import scala.Function1;
import scala.Function2;
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
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ee\u0001\u0002\u000f\u001e\u0001!B\u0001b\r\u0001\u0003\u0006\u0004%\t\u0005\u000e\u0005\tq\u0001\u0011\t\u0011)A\u0005k!A\u0011\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005H\u0001\t\u0005\t\u0015!\u0003<\u0011!I\u0005A!b\u0001\n\u0003Q\u0005\u0002\u0003)\u0001\u0005\u0003\u0005\u000b\u0011B&\t\u000bI\u0003A\u0011A*\t\u000bm\u0003A\u0011\t/\t\u000b!\u0004A\u0011\t&\t\u000b%\u0004A\u0011\t6\t\r-\u0004A\u0011I\u0011m\u0011\u0015\u0019\b\u0001\"\u0011u\u0011\u00159\b\u0001\"\u0011y\u0011\u001d\t\u0019\u0001\u0001C!\u0003\u000bAa!a\u0002\u0001\t\u0003\"\u0004BBA\u0005\u0001\u0011\u0005C\u0007\u0003\u0005\u0002\f\u0001!\t%HA\u0007\u0011\u0019\t\u0019\u0002\u0001C!i!A\u0011Q\u0003\u0001\u0005\u0002\u0005\n9\u0002\u0003\u0006\u0002\"\u0001\t\n\u0011\"\u0001\"\u0003GA\u0001\"a\u000e\u0001\t\u0003\n\u0013\u0011\b\u0005\t\u0003'\u0002A\u0011I\u0011\u0002:\u001d9\u0011qK\u000f\t\u0002\u0005ecA\u0002\u000f\u001e\u0011\u0003\tY\u0006\u0003\u0004S1\u0011\u0005\u0011Q\u000e\u0005\b\u0003_BB\u0011AA9\u0011%\t)\tGA\u0001\n\u0013\t9I\u0001\u0007Ta\u0006\u00148/\u001a,fGR|'O\u0003\u0002\u001f?\u00051A.\u001b8bY\u001eT!\u0001I\u0011\u0002\u00055d'B\u0001\u0012$\u0003\u0015\u0019\b/\u0019:l\u0015\t!S%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002M\u0005\u0019qN]4\u0004\u0001M\u0019\u0001!K\u0018\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u0001\u0014'D\u0001\u001e\u0013\t\u0011TD\u0001\u0004WK\u000e$xN]\u0001\u0005g&TX-F\u00016!\tQc'\u0003\u00028W\t\u0019\u0011J\u001c;\u0002\u000bML'0\u001a\u0011\u0002\u000f%tG-[2fgV\t1\bE\u0002+yUJ!!P\u0016\u0003\u000b\u0005\u0013(/Y=)\u0007\ryT\t\u0005\u0002A\u00076\t\u0011I\u0003\u0002CC\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0011\u000b%!B*j]\u000e,\u0017%\u0001$\u0002\u000bIr\u0003G\f\u0019\u0002\u0011%tG-[2fg\u0002B3\u0001B F\u0003\u00191\u0018\r\\;fgV\t1\nE\u0002+y1\u0003\"AK'\n\u00059[#A\u0002#pk\ndW\rK\u0002\u0006\u007f\u0015\u000bqA^1mk\u0016\u001c\b\u0005K\u0002\u0007\u007f\u0015\u000ba\u0001P5oSRtD\u0003\u0002+V-b\u0003\"\u0001\r\u0001\t\u000bM:\u0001\u0019A\u001b\t\u000be:\u0001\u0019A\u001e)\u0007Y{T\tC\u0003J\u000f\u0001\u00071\nK\u0002Y\u007f\u0015C3aB F\u0003!!xn\u0015;sS:<G#A/\u0011\u0005y+gBA0d!\t\u00017&D\u0001b\u0015\t\u0011w%\u0001\u0004=e>|GOP\u0005\u0003I.\na\u0001\u0015:fI\u00164\u0017B\u00014h\u0005\u0019\u0019FO]5oO*\u0011AmK\u0001\bi>\f%O]1z\u0003\u0011\u0019w\u000e]=\u0016\u0003Q\u000b\u0001\"Y:Ce\u0016,'0Z\u000b\u0002[B\u0019aN\u001d'\u000e\u0003=T!A\b9\u000b\u0003E\faA\u0019:fKj,\u0017B\u0001\u001ap\u0003\u0015\t\u0007\u000f\u001d7z)\taU\u000fC\u0003w\u0019\u0001\u0007Q'A\u0001j\u0003\u0019)\u0017/^1mgR\u0011\u0011\u0010 \t\u0003UiL!a_\u0016\u0003\u000f\t{w\u000e\\3b]\")Q0\u0004a\u0001}\u0006)q\u000e\u001e5feB\u0011!f`\u0005\u0004\u0003\u0003Y#aA!os\u0006A\u0001.Y:i\u0007>$W\rF\u00016\u0003)qW/\\!di&4Xm]\u0001\f]Vlgj\u001c8{KJ|7/\u0001\tu_N\u0003\u0018M]:f/&$\bnU5{KR\u0019A+a\u0004\t\r\u0005E\u0011\u00031\u00016\u0003\rqgN_\u0001\u0007CJ<W.\u0019=\u0002\u000bMd\u0017nY3\u0015\u000bQ\u000bI\"!\b\t\r\u0005m1\u00031\u0001<\u0003=\u0019X\r\\3di\u0016$\u0017J\u001c3jG\u0016\u001c\b\u0002CA\u0010'A\u0005\t\u0019A=\u0002\rM|'\u000f^3e\u0003=\u0019H.[2fI\u0011,g-Y;mi\u0012\u0012TCAA\u0013U\rI\u0018qE\u0016\u0003\u0003S\u0001B!a\u000b\u000245\u0011\u0011Q\u0006\u0006\u0005\u0003_\t\t$A\u0005v]\u000eDWmY6fI*\u0011!iK\u0005\u0005\u0003k\tiCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003w\u0001b!!\u0010\u0002H\u00055c\u0002BA \u0003\u0007r1\u0001YA!\u0013\u0005a\u0013bAA#W\u00059\u0001/Y2lC\u001e,\u0017\u0002BA%\u0003\u0017\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003\u000bZ\u0003#\u0002\u0016\u0002PUb\u0015bAA)W\t1A+\u001e9mKJ\na\"Y2uSZ,\u0017\n^3sCR|'\u000fK\u0002\u0001\u007f\u0015\u000bAb\u00159beN,g+Z2u_J\u0004\"\u0001\r\r\u0014\taI\u0013Q\f\t\u0005\u0003?\nI'\u0004\u0002\u0002b)!\u00111MA3\u0003\tIwN\u0003\u0002\u0002h\u0005!!.\u0019<b\u0013\u0011\tY'!\u0019\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005e\u0013aB;oCB\u0004H.\u001f\u000b\u0005\u0003g\ny\bE\u0003+\u0003k\nI(C\u0002\u0002x-\u0012aa\u00149uS>t\u0007C\u0002\u0016\u0002|UZ4*C\u0002\u0002~-\u0012a\u0001V;qY\u0016\u001c\u0004BBAA5\u0001\u0007A+\u0001\u0002tm\"\u001a!dP#\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005%\u0005\u0003BAF\u0003#k!!!$\u000b\t\u0005=\u0015QM\u0001\u0005Y\u0006tw-\u0003\u0003\u0002\u0014\u00065%AB(cU\u0016\u001cG\u000fK\u0002\u0019\u007f\u0015C3aF F\u0001"
)
public class SparseVector implements Vector {
   private final int size;
   private final int[] indices;
   private final double[] values;

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

   public Vector compressedWithNNZ(final int nnz) {
      return Vector.compressedWithNNZ$(this, nnz);
   }

   public double dot(final Vector v) {
      return Vector.dot$(this, v);
   }

   public Iterator nonZeroIterator() {
      return Vector.nonZeroIterator$(this);
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

   public SparseVector slice(final int[] selectedIndices, final boolean sorted) {
      int[] localIndices = this.indices();
      double[] localValues = this.values();
      int ns = selectedIndices.length;
      ArrayBuilder indexBuff = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Int());
      ArrayBuilder valueBuff = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      if (sorted) {
         int nk = localIndices.length;
         int k = 0;

         for(int s = 0; k < nk && s < ns; ++k) {
            int i = localIndices[k];
            double v = localValues[k];
            if (v != (double)0) {
               while(s < ns && selectedIndices[s] < i) {
                  ++s;
               }

               if (s < ns && selectedIndices[s] == i) {
                  indexBuff.$plus$eq(BoxesRunTime.boxToInteger(s));
                  valueBuff.$plus$eq(BoxesRunTime.boxToDouble(v));
                  ++s;
               }
            }
         }
      } else {
         for(int s = 0; s < ns; ++s) {
            int j = Arrays.binarySearch(localIndices, selectedIndices[s]);
            if (j >= 0) {
               double v = localValues[j];
               if (v != (double)0) {
                  indexBuff.$plus$eq(BoxesRunTime.boxToInteger(s));
                  valueBuff.$plus$eq(BoxesRunTime.boxToDouble(v));
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            } else {
               BoxedUnit var18 = BoxedUnit.UNIT;
            }
         }
      }

      return new SparseVector(ns, (int[])indexBuff.result(), (double[])valueBuff.result());
   }

   public boolean slice$default$2() {
      return false;
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
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.intArrayOps(indices))) {
         .MODULE$.require(indices[0] >= 0, () -> {
            int[] var10000 = this.indices();
            return "Found negative index: " + var10000[0] + ".";
         });
      }

      IntRef prev = IntRef.create(-1);
      scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.intArrayOps(indices), (JFunction1.mcVI.sp)(i) -> {
         .MODULE$.require(prev.elem < i, () -> "Index " + i + " follows " + prev.elem + " and is not strictly increasing");
         prev.elem = i;
      });
      .MODULE$.require(prev.elem < size, () -> {
         int var10000 = prev.elem;
         return "Index " + var10000 + " out of bounds for vector of size " + this.size();
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
