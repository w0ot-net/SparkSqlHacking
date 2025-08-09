package org.apache.spark.ml.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.ArraySeq.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mca\u0002\r\u001a!\u0003\r\t\u0003\n\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u0006y\u00011\t!\u0010\u0005\u0006\u0015\u00021\ta\u0013\u0005\u0006'\u0002!\t\u0005\u0016\u0005\u0006;\u0002!\tE\u0018\u0005\u0007?\u00021\t!\b1\t\u000b\u001d\u0004A\u0011\u00015\t\u000b1\u0004A\u0011A7\t\rE\u0004A\u0011A\u000fs\u0011\u0015A\b\u0001\"\u0001z\u0011\u0019a\b\u0001\"\u0001\u001e{\")q\u0010\u0001D\u0001{!1\u00111\u0001\u0001\u0007\u0002uBq!a\u0002\u0001\t\u0003\tI\u0001\u0003\u0005\u0002\u0014\u00011\t!GA\u000b\u0011\u001d\tY\u0002\u0001C\u0001\u0003;Aa!a\n\u0001\t\u0003i\u0007\u0002CA\u0016\u0001\u0011\u00051$!\f\t\r\u0005E\u0002A\"\u0001>\u0011\u001d\t)\u0004\u0001C\u0001\u0003oA\u0001\"a\u0011\u0001\t\u0003i\u0012Q\t\u0005\t\u0003'\u0002a\u0011A\u000f\u0002F!A\u0011Q\u000b\u0001\u0005\u0002u\t)E\u0001\u0004WK\u000e$xN\u001d\u0006\u00035m\ta\u0001\\5oC2<'B\u0001\u000f\u001e\u0003\tiGN\u0003\u0002\u001f?\u0005)1\u000f]1sW*\u0011\u0001%I\u0001\u0007CB\f7\r[3\u000b\u0003\t\n1a\u001c:h\u0007\u0001\u00192\u0001A\u0013,!\t1\u0013&D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019\te.\u001f*fMB\u0011A\u0006\u000e\b\u0003[Ir!AL\u0019\u000e\u0003=R!\u0001M\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0013BA\u001a(\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u000e\u001c\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005M:\u0013A\u0002\u0013j]&$H\u0005F\u0001:!\t1#(\u0003\u0002<O\t!QK\\5u\u0003\u0011\u0019\u0018N_3\u0016\u0003y\u0002\"AJ \n\u0005\u0001;#aA%oi\"\u001a!A\u0011%\u0011\u0005\r3U\"\u0001#\u000b\u0005\u0015k\u0012AC1o]>$\u0018\r^5p]&\u0011q\t\u0012\u0002\u0006'&t7-Z\u0011\u0002\u0013\u0006)!G\f\u0019/a\u00059Ao\\!se\u0006LX#\u0001'\u0011\u0007\u0019ju*\u0003\u0002OO\t)\u0011I\u001d:bsB\u0011a\u0005U\u0005\u0003#\u001e\u0012a\u0001R8vE2,\u0007fA\u0002C\u0011\u00061Q-];bYN$\"!\u0016-\u0011\u0005\u00192\u0016BA,(\u0005\u001d\u0011un\u001c7fC:DQ!\u0017\u0003A\u0002i\u000bQa\u001c;iKJ\u0004\"AJ.\n\u0005q;#aA!os\u0006A\u0001.Y:i\u0007>$W\rF\u0001?\u0003!\t7O\u0011:fKj,W#A1\u0011\u0007\t4w*D\u0001d\u0015\tQBMC\u0001f\u0003\u0019\u0011'/Z3{K&\u0011\u0001dY\u0001\u0006CB\u0004H.\u001f\u000b\u0003\u001f&DQA[\u0004A\u0002y\n\u0011!\u001b\u0015\u0004\u000f\tC\u0015\u0001B2paf,\u0012A\u001c\t\u0003_\u0002i\u0011!\u0007\u0015\u0004\u0011\tC\u0015a\u00024pe\u0016\f7\r\u001b\u000b\u0003sMDQ\u0001^\u0005A\u0002U\f\u0011A\u001a\t\u0006MYtt*O\u0005\u0003o\u001e\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\u001b\u0019|'/Z1dQ\u0006\u001bG/\u001b<f)\tI$\u0010C\u0003u\u0015\u0001\u0007Q\u000fK\u0002\u000b\u0005\"\u000baBZ8sK\u0006\u001c\u0007NT8o5\u0016\u0014x\u000e\u0006\u0002:}\")Ao\u0003a\u0001k\u0006Qa.^7BGRLg/Z:)\u00071\u0011\u0005*A\u0006ok6tuN\u001c>fe>\u001c\bfA\u0007C\u0011\u0006AAo\\*qCJ\u001cX-\u0006\u0002\u0002\fA\u0019q.!\u0004\n\u0007\u0005=\u0011D\u0001\u0007Ta\u0006\u00148/\u001a,fGR|'\u000fK\u0002\u000f\u0005\"\u000b\u0001\u0003^8Ta\u0006\u00148/Z,ji\"\u001c\u0016N_3\u0015\t\u0005-\u0011q\u0003\u0005\u0007\u00033y\u0001\u0019\u0001 \u0002\u00079t'0A\u0004u_\u0012+gn]3\u0016\u0005\u0005}\u0001cA8\u0002\"%\u0019\u00111E\r\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0015\u0004!\tC\u0015AC2p[B\u0014Xm]:fI\"\u001a\u0011C\u0011%\u0002#\r|W\u000e\u001d:fgN,GmV5uQ:s%\fF\u0002o\u0003_Aa!!\u0007\u0013\u0001\u0004q\u0014AB1sO6\f\u0007\u0010K\u0002\u0014\u0005\"\u000b1\u0001Z8u)\ry\u0015\u0011\b\u0005\u0007\u0003w!\u0002\u0019\u00018\u0002\u0003YDC\u0001\u0006\"\u0002@\u0005\u0012\u0011\u0011I\u0001\u0006g9\u0002d\u0006M\u0001\tSR,'/\u0019;peV\u0011\u0011q\t\t\u0006Y\u0005%\u0013QJ\u0005\u0004\u0003\u00172$\u0001C%uKJ\fGo\u001c:\u0011\u000b\u0019\nyEP(\n\u0007\u0005EsE\u0001\u0004UkBdWMM\u0001\u000fC\u000e$\u0018N^3Ji\u0016\u0014\u0018\r^8s\u0003=qwN\u001c.fe>LE/\u001a:bi>\u0014\u0018&\u0002\u0001\u0002\"\u00055\u0001f\u0001\u0001C\u0011\u0002"
)
public interface Vector extends Serializable {
   int size();

   double[] toArray();

   // $FF: synthetic method
   static boolean equals$(final Vector $this, final Object other) {
      return $this.equals(other);
   }

   default boolean equals(final Object other) {
      if (other instanceof Vector var5) {
         if (this.size() != var5.size()) {
            return false;
         } else {
            Tuple2 var6 = new Tuple2(this, var5);
            if (var6 != null) {
               Vector s1 = (Vector)var6._1();
               Vector s2 = (Vector)var6._2();
               if (s1 instanceof SparseVector) {
                  SparseVector var9 = (SparseVector)s1;
                  if (s2 instanceof SparseVector) {
                     SparseVector var10 = (SparseVector)s2;
                     return Vectors$.MODULE$.equals(.MODULE$.unsafeWrapArray(var9.indices()), var9.values(), .MODULE$.unsafeWrapArray(var10.indices()), var10.values());
                  }
               }
            }

            if (var6 != null) {
               Vector s1 = (Vector)var6._1();
               Vector d1 = (Vector)var6._2();
               if (s1 instanceof SparseVector) {
                  SparseVector var13 = (SparseVector)s1;
                  if (d1 instanceof DenseVector) {
                     DenseVector var14 = (DenseVector)d1;
                     return Vectors$.MODULE$.equals(.MODULE$.unsafeWrapArray(var13.indices()), var13.values(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), var14.size()), var14.values());
                  }
               }
            }

            if (var6 != null) {
               Vector d1 = (Vector)var6._1();
               Vector s1 = (Vector)var6._2();
               if (d1 instanceof DenseVector) {
                  DenseVector var17 = (DenseVector)d1;
                  if (s1 instanceof SparseVector) {
                     SparseVector var18 = (SparseVector)s1;
                     return Vectors$.MODULE$.equals(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), var17.size()), var17.values(), .MODULE$.unsafeWrapArray(var18.indices()), var18.values());
                  }
               }
            }

            if (var6 != null) {
               return Arrays.equals(this.toArray(), var5.toArray());
            } else {
               throw new MatchError(var6);
            }
         }
      } else {
         return false;
      }
   }

   // $FF: synthetic method
   static int hashCode$(final Vector $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      Object var1 = new Object();

      int var10000;
      try {
         IntRef result = IntRef.create(31 + this.size());
         IntRef nnz = IntRef.create(0);
         this.foreachActive((JFunction2.mcVID.sp)(index, value) -> {
            if (nnz.elem < Vectors$.MODULE$.MAX_HASH_NNZ()) {
               if (value != (double)0) {
                  result.elem = 31 * result.elem + index;
                  long bits = Double.doubleToLongBits(value);
                  result.elem = 31 * result.elem + (int)(bits ^ bits >>> 32);
                  ++nnz.elem;
               }
            } else {
               throw new NonLocalReturnControl.mcI.sp(var1, result.elem);
            }
         });
         var10000 = result.elem;
      } catch (NonLocalReturnControl var5) {
         if (var5.key() != var1) {
            throw var5;
         }

         var10000 = var5.value$mcI$sp();
      }

      return var10000;
   }

   breeze.linalg.Vector asBreeze();

   // $FF: synthetic method
   static double apply$(final Vector $this, final int i) {
      return $this.apply(i);
   }

   default double apply(final int i) {
      return this.asBreeze().apply$mcID$sp(i);
   }

   // $FF: synthetic method
   static Vector copy$(final Vector $this) {
      return $this.copy();
   }

   default Vector copy() {
      throw new UnsupportedOperationException("copy is not implemented for " + this.getClass() + ".");
   }

   // $FF: synthetic method
   static void foreach$(final Vector $this, final Function2 f) {
      $this.foreach(f);
   }

   default void foreach(final Function2 f) {
      this.iterator().foreach((x0$1) -> {
         $anonfun$foreach$1(f, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void foreachActive$(final Vector $this, final Function2 f) {
      $this.foreachActive(f);
   }

   default void foreachActive(final Function2 f) {
      this.activeIterator().foreach((x0$1) -> {
         $anonfun$foreachActive$1(f, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static void foreachNonZero$(final Vector $this, final Function2 f) {
      $this.foreachNonZero(f);
   }

   default void foreachNonZero(final Function2 f) {
      this.nonZeroIterator().foreach((x0$1) -> {
         $anonfun$foreachNonZero$1(f, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   int numActives();

   int numNonzeros();

   // $FF: synthetic method
   static SparseVector toSparse$(final Vector $this) {
      return $this.toSparse();
   }

   default SparseVector toSparse() {
      return this.toSparseWithSize(this.numNonzeros());
   }

   SparseVector toSparseWithSize(final int nnz);

   // $FF: synthetic method
   static DenseVector toDense$(final Vector $this) {
      return $this.toDense();
   }

   default DenseVector toDense() {
      return new DenseVector(this.toArray());
   }

   // $FF: synthetic method
   static Vector compressed$(final Vector $this) {
      return $this.compressed();
   }

   default Vector compressed() {
      return this.compressedWithNNZ(this.numNonzeros());
   }

   // $FF: synthetic method
   static Vector compressedWithNNZ$(final Vector $this, final int nnz) {
      return $this.compressedWithNNZ(nnz);
   }

   default Vector compressedWithNNZ(final int nnz) {
      return (Vector)((double)1.5F * ((double)nnz + (double)1.0F) < (double)this.size() ? this.toSparseWithSize(nnz) : this.toDense());
   }

   int argmax();

   // $FF: synthetic method
   static double dot$(final Vector $this, final Vector v) {
      return $this.dot(v);
   }

   default double dot(final Vector v) {
      return BLAS$.MODULE$.dot(this, v);
   }

   // $FF: synthetic method
   static Iterator iterator$(final Vector $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return scala.package..MODULE$.Iterator().tabulate(this.size(), (i) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(i)));
   }

   Iterator activeIterator();

   // $FF: synthetic method
   static Iterator nonZeroIterator$(final Vector $this) {
      return $this.nonZeroIterator();
   }

   default Iterator nonZeroIterator() {
      return this.activeIterator().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$nonZeroIterator$1(x$1)));
   }

   // $FF: synthetic method
   static void $anonfun$foreach$1(final Function2 f$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int i = x0$1._1$mcI$sp();
         double v = x0$1._2$mcD$sp();
         f$1.apply$mcVID$sp(i, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   static void $anonfun$foreachActive$1(final Function2 f$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int i = x0$1._1$mcI$sp();
         double v = x0$1._2$mcD$sp();
         f$2.apply$mcVID$sp(i, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   static void $anonfun$foreachNonZero$1(final Function2 f$3, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int i = x0$1._1$mcI$sp();
         double v = x0$1._2$mcD$sp();
         f$3.apply$mcVID$sp(i, v);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$iterator$1(final Vector $this, final int i) {
      return new Tuple2.mcID.sp(i, $this.apply(i));
   }

   // $FF: synthetic method
   static boolean $anonfun$nonZeroIterator$1(final Tuple2 x$1) {
      return x$1._2$mcD$sp() != (double)0;
   }

   static void $init$(final Vector $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
