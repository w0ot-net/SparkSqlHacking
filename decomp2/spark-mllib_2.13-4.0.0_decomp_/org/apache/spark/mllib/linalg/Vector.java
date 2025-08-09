package org.apache.spark.mllib.linalg;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.apache.spark.util.ArrayImplicits.;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction2;

@SQLUserDefinedType(
   udt = VectorUDT.class
)
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ufa\u0002\u000e\u001c!\u0003\r\tC\n\u0005\u0006s\u0001!\tA\u000f\u0005\u0006}\u00011\ta\u0010\u0005\u0006\u0019\u00021\t!\u0014\u0005\u0006+\u0002!\tE\u0016\u0005\u0006?\u0002!\t\u0005\u0019\u0005\u0007C\u00021\ta\b2\t\u000b%\u0004A\u0011\u00016\t\u000bA\u0004A\u0011A9\t\rU\u0004A\u0011A\u0010w\u0011\u0015a\b\u0001\"\u0001~\u0011!\t)\u0001\u0001C\u0001?\u0005\u001d\u0001BBA\u0006\u0001\u0019\u0005q\b\u0003\u0004\u0002\u0014\u00011\ta\u0010\u0005\b\u0003/\u0001A\u0011AA\r\u0011!\t\u0019\u0003\u0001D\u00017\u0005\u0015\u0002bBA\u0016\u0001\u0011\u0005\u0011Q\u0006\u0005\u0007\u0003o\u0001A\u0011A9\t\r\u0005m\u0002A\"\u0001@\u0011\u001d\t\u0019\u0005\u0001D\u0001\u0003\u000bBq!!\u0017\u0001\r\u0003\tY\u0006C\u0004\u0002p\u0001!\t!!\u001d\t\u0011\u0005u\u0004\u0001\"\u0001 \u0003\u007fB\u0001\"!$\u0001\r\u0003y\u0012q\u0010\u0005\t\u0003\u001f\u0003A\u0011A\u0010\u0002\u0000!A\u0011\u0011\u0013\u0001\u0005\u0002}\t\u0019J\u0001\u0004WK\u000e$xN\u001d\u0006\u00039u\ta\u0001\\5oC2<'B\u0001\u0010 \u0003\u0015iG\u000e\\5c\u0015\t\u0001\u0013%A\u0003ta\u0006\u00148N\u0003\u0002#G\u00051\u0011\r]1dQ\u0016T\u0011\u0001J\u0001\u0004_J<7\u0001A\n\u0004\u0001\u001dj\u0003C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#AB!osJ+g\r\u0005\u0002/m9\u0011q\u0006\u000e\b\u0003aMj\u0011!\r\u0006\u0003e\u0015\na\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005UJ\u0013a\u00029bG.\fw-Z\u0005\u0003oa\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!N\u0015\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0004C\u0001\u0015=\u0013\ti\u0014F\u0001\u0003V]&$\u0018\u0001B:ju\u0016,\u0012\u0001\u0011\t\u0003Q\u0005K!AQ\u0015\u0003\u0007%sG\u000fK\u0002\u0003\t*\u0003\"!\u0012%\u000e\u0003\u0019S!aR\u0010\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002J\r\n)1+\u001b8dK\u0006\n1*A\u00032]Ar\u0003'A\u0004u_\u0006\u0013(/Y=\u0016\u00039\u00032\u0001K(R\u0013\t\u0001\u0016FA\u0003BeJ\f\u0017\u0010\u0005\u0002)%&\u00111+\u000b\u0002\u0007\t>,(\r\\3)\u0007\r!%*\u0001\u0004fcV\fGn\u001d\u000b\u0003/j\u0003\"\u0001\u000b-\n\u0005eK#a\u0002\"p_2,\u0017M\u001c\u0005\u00067\u0012\u0001\r\u0001X\u0001\u0006_RDWM\u001d\t\u0003QuK!AX\u0015\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f)\u0005\u0001\u0015\u0001C1t\u0005J,WM_3\u0016\u0003\r\u00042\u0001\u001a5R\u001b\u0005)'B\u0001\u000fg\u0015\u00059\u0017A\u00022sK\u0016TX-\u0003\u0002\u001bK\u0006)\u0011\r\u001d9msR\u0011\u0011k\u001b\u0005\u0006Y\u001e\u0001\r\u0001Q\u0001\u0002S\"\u001aq\u0001\u00128\"\u0003=\fQ!\r\u00182]A\nAaY8qsV\t!\u000f\u0005\u0002t\u00015\t1\u0004K\u0002\t\t:\fqAZ8sK\u0006\u001c\u0007\u000e\u0006\u0002<o\")\u00010\u0003a\u0001s\u0006\ta\rE\u0003)u\u0002\u000b6(\u0003\u0002|S\tIa)\u001e8di&|gNM\u0001\u000eM>\u0014X-Y2i\u0003\u000e$\u0018N^3\u0015\u0005mr\b\"\u0002=\u000b\u0001\u0004I\b\u0006\u0002\u0006E\u0003\u0003\t#!a\u0001\u0002\u000bErcG\f\u0019\u0002\u001d\u0019|'/Z1dQ:{gNW3s_R\u00191(!\u0003\t\u000ba\\\u0001\u0019A=\u0002\u00159,X.Q2uSZ,7\u000f\u000b\u0003\r\t\u0006=\u0011EAA\t\u0003\u0015\td\u0006\u000e\u00181\u0003-qW/\u001c(p]j,'o\\:)\t5!\u0015qB\u0001\ti>\u001c\u0006/\u0019:tKV\u0011\u00111\u0004\t\u0004g\u0006u\u0011bAA\u00107\ta1\u000b]1sg\u00164Vm\u0019;pe\"\"a\u0002RA\b\u0003A!xn\u00159beN,w+\u001b;i'&TX\r\u0006\u0003\u0002\u001c\u0005\u001d\u0002BBA\u0015\u001f\u0001\u0007\u0001)A\u0002o]j\fq\u0001^8EK:\u001cX-\u0006\u0002\u00020A\u00191/!\r\n\u0007\u0005M2DA\u0006EK:\u001cXMV3di>\u0014\b\u0006\u0002\tE\u0003\u001f\t!bY8naJ,7o]3eQ\u0011\tB)a\u0004\u0002\r\u0005\u0014x-\\1yQ\u0011\u0011B)a\u0010\"\u0005\u0005\u0005\u0013!B\u0019/k9\u0002\u0014A\u0002;p\u0015N|g.\u0006\u0002\u0002HA!\u0011\u0011JA)\u001d\u0011\tY%!\u0014\u0011\u0005AJ\u0013bAA(S\u00051\u0001K]3eK\u001aLA!a\u0015\u0002V\t11\u000b\u001e:j]\u001eT1!a\u0014*Q\u0011\u0019B)!\u0001\u0002\t\u0005\u001cX\nT\u000b\u0003\u0003;\u0002B!a\u0018\u0002h5\u0011\u0011\u0011\r\u0006\u00049\u0005\r$bAA3?\u0005\u0011Q\u000e\\\u0005\u00045\u0005\u0005\u0004\u0006\u0002\u000bE\u0003W\n#!!\u001c\u0002\u000bIr\u0003G\f\u0019\u0002\u0007\u0011|G\u000fF\u0002R\u0003gBa!!\u001e\u0016\u0001\u0004\u0011\u0018!\u0001<)\tU!\u0015\u0011P\u0011\u0003\u0003w\nQa\r\u00181]A\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0003\u0003\u0003RALAB\u0003\u000fK1!!\"9\u0005!IE/\u001a:bi>\u0014\b#\u0002\u0015\u0002\n\u0002\u000b\u0016bAAFS\t1A+\u001e9mKJ\na\"Y2uSZ,\u0017\n^3sCR|'/A\bo_:TVM]8Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\b/\u0019:tSRLH#A)*\u000b\u0001\t\t$!\b)\u000f\u0001\tI*!+\u0002,B!\u00111TAS\u001b\t\tiJ\u0003\u0003\u0002 \u0006\u0005\u0016!\u0002;za\u0016\u001c(bAAR?\u0005\u00191/\u001d7\n\t\u0005\u001d\u0016Q\u0014\u0002\u0013'FcUk]3s\t\u00164\u0017N\\3e)f\u0004X-A\u0002vIR\u001c#!!,\u0011\u0007M\fy+C\u0002\u00022n\u0011\u0011BV3di>\u0014X\u000b\u0012+)\u0007\u0001!%\n"
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
                     return Vectors$.MODULE$.equals(.MODULE$.SparkArrayOps(var9.indices()).toImmutableArraySeq(), var9.values(), .MODULE$.SparkArrayOps(var10.indices()).toImmutableArraySeq(), var10.values());
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
                     return Vectors$.MODULE$.equals(.MODULE$.SparkArrayOps(var13.indices()).toImmutableArraySeq(), var13.values(), scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), var14.size()), var14.values());
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
                     return Vectors$.MODULE$.equals(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), var17.size()), var17.values(), .MODULE$.SparkArrayOps(var18.indices()).toImmutableArraySeq(), var18.values());
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
      int nnz = this.numNonzeros();
      return (Vector)((double)1.5F * ((double)nnz + (double)1.0F) < (double)this.size() ? this.toSparseWithSize(nnz) : this.toDense());
   }

   int argmax();

   String toJson();

   org.apache.spark.ml.linalg.Vector asML();

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
   static double sparsity$(final Vector $this) {
      return $this.sparsity();
   }

   default double sparsity() {
      return (double)1.0F - (double)this.numNonzeros() / (double)this.size();
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
