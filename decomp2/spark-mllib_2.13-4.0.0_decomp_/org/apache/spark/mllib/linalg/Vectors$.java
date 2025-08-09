package org.apache.spark.mllib.linalg;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.mllib.util.NumericParser$;
import org.json4s.Formats;
import org.json4s.JValue;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction1;

public final class Vectors$ {
   public static final Vectors$ MODULE$ = new Vectors$();
   private static final int MAX_HASH_NNZ = 128;

   public Vector dense(final double firstValue, final double... otherValues) {
      return this.dense(firstValue, (Seq).MODULE$.wrapDoubleArray(otherValues));
   }

   public Vector dense(final double firstValue, final Seq otherValues) {
      return new DenseVector((double[])((IterableOnceOps)otherValues.$plus$colon(BoxesRunTime.boxToDouble(firstValue))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
   }

   public Vector dense(final double[] values) {
      return new DenseVector(values);
   }

   public Vector sparse(final int size, final int[] indices, final double[] values) {
      return new SparseVector(size, indices, values);
   }

   public Vector sparse(final int size, final Seq elements) {
      Tuple2 var5 = ((IterableOps)elements.sortBy((x$2) -> BoxesRunTime.boxToInteger($anonfun$sparse$1(x$2)), scala.math.Ordering.Int..MODULE$)).unzip(scala.Predef..MODULE$.$conforms());
      if (var5 != null) {
         Seq indices = (Seq)var5._1();
         Seq values = (Seq)var5._2();
         Tuple2 var4 = new Tuple2(indices, values);
         Seq indices = (Seq)var4._1();
         Seq values = (Seq)var4._2();
         IntRef prev = IntRef.create(-1);
         indices.foreach((JFunction1.mcVI.sp)(i) -> {
            scala.Predef..MODULE$.require(prev.elem < i, () -> "Found duplicate indices: " + i + ".");
            prev.elem = i;
         });
         scala.Predef..MODULE$.require(prev.elem < size, () -> "You may not write an element to index " + prev.elem + " because the declared size of your vector is " + size);
         return new SparseVector(size, (int[])indices.toArray(scala.reflect.ClassTag..MODULE$.Int()), (double[])values.toArray(scala.reflect.ClassTag..MODULE$.Double()));
      } else {
         throw new MatchError(var5);
      }
   }

   public Vector sparse(final int size, final Iterable elements) {
      return this.sparse(size, ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.IterableHasAsScala(elements).asScala().map((x0$1) -> {
         if (x0$1 != null) {
            Integer i = (Integer)x0$1._1();
            Double x = (Double)x0$1._2();
            return new Tuple2.mcID.sp(i, x);
         } else {
            throw new MatchError(x0$1);
         }
      })).toSeq());
   }

   public Vector zeros(final int size) {
      return new DenseVector(new double[size]);
   }

   public Vector parse(final String s) {
      return this.parseNumeric(NumericParser$.MODULE$.parse(s));
   }

   public Vector fromJson(final String json) {
      Formats formats = org.json4s.DefaultFormats..MODULE$;
      JValue jValue = org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      int var4 = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "type")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
      switch (var4) {
         case 0:
            int size = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "size")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            int[] indices = (int[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "indices")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Int(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Int());
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return this.sparse(size, indices, values);
         case 1:
            double[] values = (double[])((IterableOnceOps)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "values")), formats, scala.reflect.ManifestFactory..MODULE$.classType(Seq.class, scala.reflect.ManifestFactory..MODULE$.Double(), scala.collection.immutable.Nil..MODULE$))).toArray(scala.reflect.ClassTag..MODULE$.Double());
            return this.dense(values);
         default:
            throw new IllegalArgumentException("Cannot parse " + json + " into a vector.");
      }
   }

   public Vector parseNumeric(final Object any) {
      if (any instanceof double[] var4) {
         return this.dense(var4);
      } else {
         if (any instanceof Seq var5) {
            SeqOps var6 = scala.package..MODULE$.Seq().unapplySeq(var5);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var6) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var6)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var6), 3) == 0) {
               Object size = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var6), 0);
               Object indices = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var6), 1);
               Object values = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var6), 2);
               if (size instanceof Double) {
                  double var10 = BoxesRunTime.unboxToDouble(size);
                  if (indices instanceof double[]) {
                     double[] var12 = (double[])indices;
                     if (values instanceof double[]) {
                        double[] var13 = (double[])values;
                        return this.sparse((int)var10, (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(var12), (JFunction1.mcID.sp)(x$4) -> (int)x$4, scala.reflect.ClassTag..MODULE$.Int()), var13);
                     }
                  }
               }
            }
         }

         throw new SparkException("Cannot parse " + any + ".");
      }
   }

   public Vector fromBreeze(final breeze.linalg.Vector breezeVector) {
      if (breezeVector instanceof breeze.linalg.DenseVector var4) {
         return var4.offset() == 0 && var4.stride() == 1 && var4.length() == var4.data$mcD$sp().length ? new DenseVector(var4.data$mcD$sp()) : new DenseVector(var4.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double()));
      } else if (breezeVector instanceof breeze.linalg.SparseVector var5) {
         return var5.index().length == var5.used() ? new SparseVector(var5.length(), var5.index(), var5.data$mcD$sp()) : new SparseVector(var5.length(), (int[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.intArrayOps(var5.index()), 0, var5.used()), (double[])scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(var5.data$mcD$sp()), 0, var5.used()));
      } else if (breezeVector != null) {
         throw scala.sys.package..MODULE$.error("Unsupported Breeze vector type: " + breezeVector.getClass().getName());
      } else {
         throw new MatchError(breezeVector);
      }
   }

   public double norm(final Vector vector, final double p) {
      double[] var10000;
      label85: {
         scala.Predef..MODULE$.require(p >= (double)1.0F, () -> "To compute the p-norm of the vector, we require that you specify a p>=1. You specified p=" + p + ".");
         if (vector instanceof DenseVector var7) {
            Option var8 = DenseVector$.MODULE$.unapply(var7);
            if (!var8.isEmpty()) {
               double[] vs = (double[])var8.get();
               var10000 = vs;
               break label85;
            }
         }

         if (!(vector instanceof SparseVector)) {
            throw new IllegalArgumentException("Do not support vector type " + vector.getClass());
         }

         SparseVector var10 = (SparseVector)vector;
         Option var11 = SparseVector$.MODULE$.unapply(var10);
         if (var11.isEmpty()) {
            throw new IllegalArgumentException("Do not support vector type " + vector.getClass());
         }

         double[] vs = (double[])((Tuple3)var11.get())._3();
         var10000 = vs;
      }

      double[] values = var10000;
      int size = values.length;
      if (p == (double)1) {
         double sum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            sum += scala.math.package..MODULE$.abs(values[i]);
         }

         return sum;
      } else if (p == (double)2) {
         double sum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            sum += values[i] * values[i];
         }

         return scala.math.package..MODULE$.sqrt(sum);
      } else if (p == Double.POSITIVE_INFINITY) {
         double max = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            double value = scala.math.package..MODULE$.abs(values[i]);
            if (value > max) {
               max = value;
            }
         }

         return max;
      } else {
         double sum = (double)0.0F;

         for(int i = 0; i < size; ++i) {
            sum += scala.math.package..MODULE$.pow(scala.math.package..MODULE$.abs(values[i]), p);
         }

         return scala.math.package..MODULE$.pow(sum, (double)1.0F / p);
      }
   }

   public double sqdist(final Vector v1, final Vector v2) {
      scala.Predef..MODULE$.require(v1.size() == v2.size(), () -> {
         int var10000 = v1.size();
         return "Vector dimensions do not match: Dim(v1)=" + var10000 + " and Dim(v2)=" + v2.size() + ".";
      });
      double squaredDistance = (double)0.0F;
      Tuple2 var6 = new Tuple2(v1, v2);
      if (var6 != null) {
         Vector v1 = (Vector)var6._1();
         Vector v2 = (Vector)var6._2();
         if (v1 instanceof SparseVector) {
            SparseVector var9 = (SparseVector)v1;
            if (v2 instanceof SparseVector) {
               SparseVector var10 = (SparseVector)v2;
               double[] v1Values = var9.values();
               int[] v1Indices = var9.indices();
               double[] v2Values = var10.values();
               int[] v2Indices = var10.indices();
               int nnzv1 = v1Indices.length;
               int nnzv2 = v2Indices.length;
               int kv1 = 0;

               double score;
               for(int kv2 = 0; kv1 < nnzv1 || kv2 < nnzv2; squaredDistance += score * score) {
                  score = (double)0.0F;
                  if (kv2 < nnzv2 && (kv1 >= nnzv1 || v1Indices[kv1] >= v2Indices[kv2])) {
                     if (kv1 < nnzv1 && (kv2 >= nnzv2 || v2Indices[kv2] >= v1Indices[kv1])) {
                        score = v1Values[kv1] - v2Values[kv2];
                        ++kv1;
                        ++kv2;
                     } else {
                        score = v2Values[kv2];
                        ++kv2;
                     }
                  } else {
                     score = v1Values[kv1];
                     ++kv1;
                  }
               }

               BoxedUnit var44 = BoxedUnit.UNIT;
               return squaredDistance;
            }
         }
      }

      if (var6 != null) {
         Vector v1 = (Vector)var6._1();
         Vector v2 = (Vector)var6._2();
         if (v1 instanceof SparseVector) {
            SparseVector var23 = (SparseVector)v1;
            if (v2 instanceof DenseVector) {
               DenseVector var24 = (DenseVector)v2;
               squaredDistance = this.sqdist(var23, var24);
               BoxedUnit var43 = BoxedUnit.UNIT;
               return squaredDistance;
            }
         }
      }

      if (var6 != null) {
         Vector v1 = (Vector)var6._1();
         Vector v2 = (Vector)var6._2();
         if (v1 instanceof DenseVector) {
            DenseVector var27 = (DenseVector)v1;
            if (v2 instanceof SparseVector) {
               SparseVector var28 = (SparseVector)v2;
               squaredDistance = this.sqdist(var28, var27);
               BoxedUnit var42 = BoxedUnit.UNIT;
               return squaredDistance;
            }
         }
      }

      if (var6 != null) {
         Vector var29 = (Vector)var6._1();
         Vector var30 = (Vector)var6._2();
         if (var29 instanceof DenseVector) {
            DenseVector var31 = (DenseVector)var29;
            Option var32 = DenseVector$.MODULE$.unapply(var31);
            if (!var32.isEmpty()) {
               double[] vv1 = (double[])var32.get();
               if (var30 instanceof DenseVector) {
                  DenseVector var34 = (DenseVector)var30;
                  Option var35 = DenseVector$.MODULE$.unapply(var34);
                  if (!var35.isEmpty()) {
                     double[] vv2 = (double[])var35.get();
                     int kv = 0;

                     for(int sz = vv1.length; kv < sz; ++kv) {
                        double score = vv1[kv] - vv2[kv];
                        squaredDistance += score * score;
                     }

                     BoxedUnit var10000 = BoxedUnit.UNIT;
                     return squaredDistance;
                  }
               }
            }
         }
      }

      Class var10002 = v1.getClass();
      throw new IllegalArgumentException("Do not support vector type " + var10002 + " and " + v2.getClass());
   }

   public double sqdist(final SparseVector v1, final DenseVector v2) {
      int kv1 = 0;
      int kv2 = 0;
      int[] indices = v1.indices();
      double squaredDistance = (double)0.0F;
      int nnzv1 = indices.length;
      int nnzv2 = v2.size();

      for(int iv1 = nnzv1 > 0 ? indices[kv1] : -1; kv2 < nnzv2; ++kv2) {
         double score = (double)0.0F;
         if (kv2 != iv1) {
            score = v2.apply(kv2);
         } else {
            score = v1.values()[kv1] - v2.apply(kv2);
            if (kv1 < nnzv1 - 1) {
               ++kv1;
               iv1 = indices[kv1];
            }
         }

         squaredDistance += score * score;
      }

      return squaredDistance;
   }

   public boolean equals(final IndexedSeq v1Indices, final double[] v1Values, final IndexedSeq v2Indices, final double[] v2Values) {
      int v1Size = v1Values.length;
      int v2Size = v2Values.length;
      int k1 = 0;
      int k2 = 0;

      boolean allEqual;
      for(allEqual = true; allEqual; ++k2) {
         while(k1 < v1Size && v1Values[k1] == (double)0) {
            ++k1;
         }

         while(k2 < v2Size && v2Values[k2] == (double)0) {
            ++k2;
         }

         if (k1 >= v1Size || k2 >= v2Size) {
            return k1 >= v1Size && k2 >= v2Size;
         }

         allEqual = BoxesRunTime.unboxToInt(v1Indices.apply(k1)) == BoxesRunTime.unboxToInt(v2Indices.apply(k2)) && v1Values[k1] == v2Values[k2];
         ++k1;
      }

      return allEqual;
   }

   public int MAX_HASH_NNZ() {
      return MAX_HASH_NNZ;
   }

   public Vector fromML(final org.apache.spark.ml.linalg.Vector v) {
      if (v instanceof org.apache.spark.ml.linalg.DenseVector var4) {
         return DenseVector$.MODULE$.fromML(var4);
      } else if (v instanceof org.apache.spark.ml.linalg.SparseVector var5) {
         return SparseVector$.MODULE$.fromML(var5);
      } else {
         throw new MatchError(v);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$sparse$1(final Tuple2 x$2) {
      return x$2._1$mcI$sp();
   }

   private Vectors$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
