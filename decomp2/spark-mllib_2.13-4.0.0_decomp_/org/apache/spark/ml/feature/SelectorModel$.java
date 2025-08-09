package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.NominalAttribute$;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.SparseVector.;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class SelectorModel$ implements Serializable {
   public static final SelectorModel$ MODULE$ = new SelectorModel$();

   public Dataset transform(final Dataset dataset, final int[] selectedFeatures, final StructType outputSchema, final String outputCol, final String featuresCol) {
      int newSize = selectedFeatures.length;
      Function1 func = (vector) -> {
         if (vector instanceof SparseVector var6) {
            Option var7 = .MODULE$.unapply(var6);
            if (!var7.isEmpty()) {
               int[] indices = (int[])((Tuple3)var7.get())._2();
               double[] values = (double[])((Tuple3)var7.get())._3();
               Tuple2 var11 = MODULE$.compressSparse(indices, values, selectedFeatures);
               if (var11 != null) {
                  int[] newIndices = (int[])var11._1();
                  double[] newValues = (double[])var11._2();
                  Tuple2 var10 = new Tuple2(newIndices, newValues);
                  int[] newIndicesx = (int[])var10._1();
                  double[] newValuesx = (double[])var10._2();
                  return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(newSize, newIndicesx, newValuesx);
               }

               throw new MatchError(var11);
            }
         }

         if (vector instanceof DenseVector var16) {
            Option var17 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var16);
            if (!var17.isEmpty()) {
               double[] values = (double[])var17.get();
               return org.apache.spark.ml.linalg.Vectors..MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(selectedFeatures), scala.Predef..MODULE$.wrapDoubleArray(values), scala.reflect.ClassTag..MODULE$.Double()));
            }
         }

         throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + vector.getClass() + ".");
      };
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(func, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn(outputCol, transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(featuresCol)}))), outputSchema.apply(outputCol).metadata());
   }

   public StructField prepOutputField(final StructType schema, final int[] selectedFeatures, final String outputCol, final String featuresCol, final boolean isNumericAttribute) {
      Set selector = scala.Predef..MODULE$.wrapIntArray(selectedFeatures).toSet();
      AttributeGroup origAttrGroup = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(schema, featuresCol));
      Attribute[] featureAttributes = origAttrGroup.attributes().nonEmpty() ? (Attribute[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(origAttrGroup.attributes().get()))), (x) -> BoxesRunTime.boxToBoolean($anonfun$prepOutputField$1(selector, x)))), (x$2) -> (Attribute)x$2._1(), scala.reflect.ClassTag..MODULE$.apply(Attribute.class)) : (isNumericAttribute ? (Attribute[])scala.Array..MODULE$.fill(selector.size(), () -> NumericAttribute$.MODULE$.defaultAttr(), scala.reflect.ClassTag..MODULE$.apply(Attribute.class)) : (Attribute[])scala.Array..MODULE$.fill(selector.size(), () -> NominalAttribute$.MODULE$.defaultAttr(), scala.reflect.ClassTag..MODULE$.apply(Attribute.class)));
      AttributeGroup newAttributeGroup = new AttributeGroup(outputCol, featureAttributes);
      return newAttributeGroup.toStructField();
   }

   public Tuple2 compressSparse(final int[] indices, final double[] values, final int[] selectedFeatures) {
      ArrayBuilder.ofDouble newValues = new ArrayBuilder.ofDouble();
      ArrayBuilder.ofInt newIndices = new ArrayBuilder.ofInt();
      int i = 0;
      int j = 0;

      while(i < indices.length && j < selectedFeatures.length) {
         int indicesIdx = indices[i];
         int filterIndicesIdx = selectedFeatures[j];
         if (indicesIdx == filterIndicesIdx) {
            newIndices.$plus$eq(BoxesRunTime.boxToInteger(j));
            newValues.$plus$eq(BoxesRunTime.boxToDouble(values[i]));
            ++j;
            ++i;
         } else if (indicesIdx > filterIndicesIdx) {
            ++j;
         } else {
            ++i;
         }
      }

      return new Tuple2(newIndices.result(), newValues.result());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SelectorModel$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepOutputField$1(final Set selector$1, final Tuple2 x) {
      return selector$1.contains(BoxesRunTime.boxToInteger(x._2$mcI$sp()));
   }

   private SelectorModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
