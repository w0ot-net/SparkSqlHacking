package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.util.MLReadable;
import org.apache.spark.ml.util.MLReader;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class StandardScalerModel$ implements MLReadable, Serializable {
   public static final StandardScalerModel$ MODULE$ = new StandardScalerModel$();

   static {
      MLReadable.$init$(MODULE$);
   }

   public MLReader read() {
      return new StandardScalerModel.StandardScalerModelReader();
   }

   public StandardScalerModel load(final String path) {
      return (StandardScalerModel)MLReadable.load$(this, path);
   }

   public double[] transformWithBoth(final double[] shift, final double[] scale, final double[] values) {
      for(int i = 0; i < values.length; ++i) {
         values[i] = (values[i] - shift[i]) * scale[i];
      }

      return values;
   }

   public double[] transformWithShift(final double[] shift, final double[] values) {
      for(int i = 0; i < values.length; ++i) {
         values[i] -= shift[i];
      }

      return values;
   }

   public double[] transformDenseWithScale(final double[] scale, final double[] values) {
      for(int i = 0; i < values.length; ++i) {
         values[i] *= scale[i];
      }

      return values;
   }

   public double[] transformSparseWithScale(final double[] scale, final int[] indices, final double[] values) {
      for(int i = 0; i < values.length; ++i) {
         values[i] *= scale[indices[i]];
      }

      return values;
   }

   public Function1 getTransformFunc(final double[] shift, final double[] scale, final boolean withShift, final boolean withScale) {
      Tuple2.mcZZ.sp var6 = new Tuple2.mcZZ.sp(withShift, withScale);
      if (var6 != null) {
         boolean var7 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var8 = ((Tuple2)var6)._2$mcZ$sp();
         if (var7 && var8) {
            return (vector) -> {
               double[] var10000;
               if (vector instanceof DenseVector var6) {
                  var10000 = (double[])var6.values().clone();
               } else {
                  if (vector == null) {
                     throw new MatchError(vector);
                  }

                  var10000 = vector.toArray();
               }

               double[] values = var10000;
               double[] newValues = MODULE$.transformWithBoth(shift, scale, values);
               return .MODULE$.dense(newValues);
            };
         }
      }

      if (var6 != null) {
         boolean var9 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var10 = ((Tuple2)var6)._2$mcZ$sp();
         if (var9 && !var10) {
            return (vector) -> {
               double[] var10000;
               if (vector instanceof DenseVector var5) {
                  var10000 = (double[])var5.values().clone();
               } else {
                  if (vector == null) {
                     throw new MatchError(vector);
                  }

                  var10000 = vector.toArray();
               }

               double[] values = var10000;
               double[] newValues = MODULE$.transformWithShift(shift, values);
               return .MODULE$.dense(newValues);
            };
         }
      }

      if (var6 != null) {
         boolean var11 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var12 = ((Tuple2)var6)._2$mcZ$sp();
         if (!var11 && var12) {
            return (vector) -> {
               if (vector instanceof DenseVector var4) {
                  Option var5 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var4);
                  if (!var5.isEmpty()) {
                     double[] values = (double[])var5.get();
                     double[] newValues = MODULE$.transformDenseWithScale(scale, (double[])(([D)values).clone());
                     return .MODULE$.dense(newValues);
                  }
               }

               if (vector instanceof SparseVector var8) {
                  Option var9 = org.apache.spark.ml.linalg.SparseVector..MODULE$.unapply(var8);
                  if (!var9.isEmpty()) {
                     int size = BoxesRunTime.unboxToInt(((Tuple3)var9.get())._1());
                     int[] indices = (int[])((Tuple3)var9.get())._2();
                     double[] values = (double[])((Tuple3)var9.get())._3();
                     double[] newValues = MODULE$.transformSparseWithScale(scale, indices, (double[])(([D)values).clone());
                     return .MODULE$.sparse(size, indices, newValues);
                  }
               }

               throw new IllegalArgumentException("Unknown vector type " + vector.getClass() + ".");
            };
         }
      }

      if (var6 != null) {
         boolean var13 = ((Tuple2)var6)._1$mcZ$sp();
         boolean var14 = ((Tuple2)var6)._2$mcZ$sp();
         if (!var13 && !var14) {
            return (vector) -> vector;
         }
      }

      throw new MatchError(var6);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StandardScalerModel$.class);
   }

   private StandardScalerModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
