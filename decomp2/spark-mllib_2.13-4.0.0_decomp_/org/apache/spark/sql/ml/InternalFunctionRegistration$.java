package org.apache.spark.sql.ml;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.stat.SummaryBuilderImpl;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.expressions.SparkUserDefinedFunction;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.functions.;
import scala.Function1;
import scala.Option;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeTags;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

public final class InternalFunctionRegistration$ {
   public static final InternalFunctionRegistration$ MODULE$ = new InternalFunctionRegistration$();
   private static final UserDefinedFunction vectorToArrayUdf;
   private static final UserDefinedFunction vectorToArrayFloatUdf;
   private static final UserDefinedFunction arrayToVectorUdf;

   static {
      functions var10000 = .MODULE$;
      Function1 var10001 = (vec) -> {
         if (vec instanceof Vector var3) {
            return var3.toArray();
         } else if (vec instanceof org.apache.spark.mllib.linalg.Vector var4) {
            return var4.toArray();
         } else {
            String var10002 = vec == null ? "null" : vec.getClass().getName();
            throw new IllegalArgumentException("function vector_to_array requires a non-null input argument and input type must be `org.apache.spark.ml.linalg.Vector` or `org.apache.spark.mllib.linalg.Vector`, but got " + var10002 + ".");
         }
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());
      vectorToArrayUdf = var10000.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new InternalFunctionRegistration$$typecreator1$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any()).asNonNullable();
      var10000 = .MODULE$;
      var10001 = (vec) -> {
         if (vec instanceof SparseVector var3) {
            float[] data = new float[var3.size()];
            var3.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> data[index] = (float)value);
            return data;
         } else if (vec instanceof Vector var5) {
            return (float[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(var5.toArray()), (JFunction1.mcFD.sp)(x$1) -> (float)x$1, scala.reflect.ClassTag..MODULE$.Float());
         } else if (vec instanceof org.apache.spark.mllib.linalg.SparseVector var6) {
            float[] data = new float[var6.size()];
            var6.foreachNonZero((JFunction2.mcVID.sp)(index, value) -> data[index] = (float)value);
            return data;
         } else if (vec instanceof org.apache.spark.mllib.linalg.Vector var8) {
            return (float[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(var8.toArray()), (JFunction1.mcFD.sp)(x$2) -> (float)x$2, scala.reflect.ClassTag..MODULE$.Float());
         } else {
            String var10002 = vec == null ? "null" : vec.getClass().getName();
            throw new IllegalArgumentException("function vector_to_array requires a non-null input argument and input type must be `org.apache.spark.ml.linalg.Vector` or `org.apache.spark.mllib.linalg.Vector`, but got " + var10002 + ".");
         }
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());
      vectorToArrayFloatUdf = var10000.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new InternalFunctionRegistration$$typecreator2$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Any()).asNonNullable();
      MODULE$.registerFunction("vector_to_array", (x0$1) -> {
         if (x0$1 != null) {
            SeqOps var3 = scala.package..MODULE$.Seq().unapplySeq(x0$1);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var3) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 2) == 0) {
               Expression input = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 0);
               Expression var5 = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 1);
               if (var5 != null) {
                  Option var6 = org.apache.spark.sql.catalyst.expressions.StringLiteral..MODULE$.unapply(var5);
                  if (!var6.isEmpty()) {
                     String var7 = (String)var6.get();
                     if ("float64".equals(var7)) {
                        return MODULE$.invokeUdf(MODULE$.vectorToArrayUdf(), input);
                     }
                  }
               }
            }
         }

         if (x0$1 != null) {
            SeqOps var8 = scala.package..MODULE$.Seq().unapplySeq(x0$1);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var8) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 2) == 0) {
               Expression input = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 0);
               Expression var10 = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var8), 1);
               if (var10 != null) {
                  Option var11 = org.apache.spark.sql.catalyst.expressions.StringLiteral..MODULE$.unapply(var10);
                  if (!var11.isEmpty()) {
                     String var12 = (String)var11.get();
                     if ("float32".equals(var12)) {
                        return MODULE$.invokeUdf(MODULE$.vectorToArrayFloatUdf(), input);
                     }
                  }
               }
            }
         }

         if (x0$1 != null) {
            SeqOps var13 = scala.package..MODULE$.Seq().unapplySeq(x0$1);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 2) == 0) {
               Expression invalid = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1);
               if (invalid != null) {
                  Option var15 = org.apache.spark.sql.catalyst.expressions.StringLiteral..MODULE$.unapply(invalid);
                  if (!var15.isEmpty()) {
                     throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.invalidParameter("DTYPE", "vector_to_array", "dtype", invalid);
                  }
               }
            }
         }

         if (x0$1 != null) {
            SeqOps var16 = scala.package..MODULE$.Seq().unapplySeq(x0$1);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var16) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 2) == 0) {
               Expression invalid = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 1);
               throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.invalidStringParameter("vector_to_array", "dtype", invalid);
            }
         }

         throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError("vector_to_array", scala.Predef..MODULE$.wrapString("2"), x0$1.size(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$4(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$5(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$6());
      });
      var10000 = .MODULE$;
      var10001 = (array) -> org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])array.toArray(scala.reflect.ClassTag..MODULE$.Double()));
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());
      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new InternalFunctionRegistration$$typecreator3$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());
      arrayToVectorUdf = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new InternalFunctionRegistration$$typecreator4$1()));
      MODULE$.registerFunction("array_to_vector", (x0$2) -> {
         if (x0$2 != null) {
            SeqOps var3 = scala.package..MODULE$.Seq().unapplySeq(x0$2);
            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var3) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 1) == 0) {
               Expression input = (Expression)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var3), 0);
               return MODULE$.invokeUdf(MODULE$.arrayToVectorUdf(), input);
            }
         }

         throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError("array_to_vector", scala.Predef..MODULE$.wrapString("1"), x0$2.size(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$4(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$5(), org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.wrongNumArgsError$default$6());
      });
      org.apache.spark.sql.catalyst.analysis.FunctionRegistry..MODULE$.registerInternalExpression("aggregate_metrics", org.apache.spark.sql.catalyst.analysis.FunctionRegistry..MODULE$.registerInternalExpression$default$2(), scala.reflect.ClassTag..MODULE$.apply(SummaryBuilderImpl.MetricsAggregate.class));
   }

   public void apply() {
   }

   private Expression invokeUdf(final UserDefinedFunction udf, final Expression e) {
      return org.apache.spark.sql.classic.UserDefinedFunctionUtils..MODULE$.toScalaUDF((SparkUserDefinedFunction)udf, scala.collection.immutable.Nil..MODULE$.$colon$colon(e));
   }

   private void registerFunction(final String name, final Function1 builder) {
      org.apache.spark.sql.catalyst.analysis.FunctionRegistry..MODULE$.internal().createOrReplaceTempFunction(name, builder, "internal");
   }

   private UserDefinedFunction vectorToArrayUdf() {
      return vectorToArrayUdf;
   }

   private UserDefinedFunction vectorToArrayFloatUdf() {
      return vectorToArrayFloatUdf;
   }

   private UserDefinedFunction arrayToVectorUdf() {
      return arrayToVectorUdf;
   }

   private InternalFunctionRegistration$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
