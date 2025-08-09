package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.generic.AbstractGenericUDAFResolver;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.catalog.InvalidUDFClassException;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.internal.SparkUDFExpressionBuilder;
import org.apache.spark.util.Utils.;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;

public final class HiveUDFExpressionBuilder$ extends SparkUDFExpressionBuilder {
   public static final HiveUDFExpressionBuilder$ MODULE$ = new HiveUDFExpressionBuilder$();

   // $FF: synthetic method
   private Expression super$makeExpression(final String name, final Class clazz, final Seq input) {
      return super.makeExpression(name, clazz, input);
   }

   public Expression makeExpression(final String name, final Class clazz, final Seq input) {
      return (Expression).MODULE$.withContextClassLoader(clazz.getClassLoader(), () -> {
         Expression var10000;
         try {
            var10000 = MODULE$.super$makeExpression(name, clazz, input);
         } catch (Throwable var7) {
            if (!(var7 instanceof InvalidUDFClassException)) {
               if (var7 != null && scala.util.control.NonFatal..MODULE$.apply(var7)) {
                  throw var7;
               }

               throw var7;
            }

            var10000 = MODULE$.makeHiveFunctionExpression(name, clazz, input);
         }

         return var10000;
      });
   }

   private Expression makeHiveFunctionExpression(final String name, final Class clazz, final Seq input) {
      Option udfExpr = scala.None..MODULE$;

      try {
         if (UDF.class.isAssignableFrom(clazz)) {
            udfExpr = new Some(new HiveSimpleUDF(name, new HiveShim.HiveFunctionWrapper(clazz.getName(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3()), input));
            ((Expression)udfExpr.get()).dataType();
         } else if (GenericUDF.class.isAssignableFrom(clazz)) {
            udfExpr = new Some(new HiveGenericUDF(name, new HiveShim.HiveFunctionWrapper(clazz.getName(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3()), input));
            ((Expression)udfExpr.get()).dataType();
         } else if (AbstractGenericUDAFResolver.class.isAssignableFrom(clazz)) {
            udfExpr = new Some(new HiveUDAFFunction(name, new HiveShim.HiveFunctionWrapper(clazz.getName(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3()), input, HiveUDAFFunction$.MODULE$.apply$default$4(), HiveUDAFFunction$.MODULE$.apply$default$5(), HiveUDAFFunction$.MODULE$.apply$default$6()));
            ((Expression)udfExpr.get()).dataType();
         } else if (UDAF.class.isAssignableFrom(clazz)) {
            udfExpr = new Some(new HiveUDAFFunction(name, new HiveShim.HiveFunctionWrapper(clazz.getName(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3()), input, true, HiveUDAFFunction$.MODULE$.apply$default$5(), HiveUDAFFunction$.MODULE$.apply$default$6()));
            ((Expression)udfExpr.get()).dataType();
         } else if (GenericUDTF.class.isAssignableFrom(clazz)) {
            udfExpr = new Some(new HiveGenericUDTF(name, new HiveShim.HiveFunctionWrapper(clazz.getName(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$2(), HiveShim.HiveFunctionWrapper$.MODULE$.$lessinit$greater$default$3()), input));
            ((HiveGenericUDTF)udfExpr.get()).elementSchema();
         } else {
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      } catch (Throwable var14) {
         if (var14 != null && scala.util.control.NonFatal..MODULE$.apply(var14)) {
            Throwable var10000;
            if (var14 instanceof InvocationTargetException) {
               InvocationTargetException var12 = (InvocationTargetException)var14;
               var10000 = var12.getCause();
            } else {
               var10000 = var14;
            }

            Throwable e = var10000;
            AnalysisException analysisException = new AnalysisException("_LEGACY_ERROR_TEMP_3084", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("clazz"), clazz.getCanonicalName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("e"), e.toString())}))));
            analysisException.setStackTrace(e.getStackTrace());
            throw analysisException;
         }

         throw var14;
      }

      return (Expression)udfExpr.getOrElse(() -> {
         throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.invalidUDFClassError(clazz.getCanonicalName());
      });
   }

   private HiveUDFExpressionBuilder$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
