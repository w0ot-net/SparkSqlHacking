package org.apache.spark.ml.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.Attribute$;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.attribute.BinaryAttribute;
import org.apache.spark.ml.attribute.NominalAttribute;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.UnresolvedAttribute$;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;

public final class MetadataUtils$ {
   public static final MetadataUtils$ MODULE$ = new MetadataUtils$();

   public Option getNumClasses(final StructField labelSchema) {
      Attribute var4 = Attribute$.MODULE$.fromStructField(labelSchema);
      if (var4 instanceof BinaryAttribute) {
         return new Some(BoxesRunTime.boxToInteger(2));
      } else if (var4 instanceof NominalAttribute) {
         NominalAttribute var5 = (NominalAttribute)var4;
         return var5.getNumValues();
      } else if (var4 instanceof NumericAttribute ? true : UnresolvedAttribute$.MODULE$.equals(var4)) {
         return .MODULE$;
      } else {
         throw new MatchError(var4);
      }
   }

   public Option getNumFeatures(final StructField vectorSchema) {
      DataType var10000 = vectorSchema.dataType();
      VectorUDT var2 = new VectorUDT();
      if (var10000 == null) {
         if (var2 != null) {
            return .MODULE$;
         }
      } else if (!var10000.equals(var2)) {
         return .MODULE$;
      }

      AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(vectorSchema);
      int size = group.size();
      if (size >= 0) {
         return new Some(BoxesRunTime.boxToInteger(size));
      } else {
         return .MODULE$;
      }
   }

   public Map getCategoricalFeatures(final StructField featuresSchema) {
      AttributeGroup metadata = AttributeGroup$.MODULE$.fromStructField(featuresSchema);
      return (Map)(metadata.attributes().isEmpty() ? scala.collection.immutable.HashMap..MODULE$.empty() : scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(metadata.attributes().get()))), (x0$1) -> {
         if (x0$1 != null) {
            Attribute attr = (Attribute)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            if (attr == null) {
               return scala.package..MODULE$.Iterator().apply(scala.collection.immutable.Nil..MODULE$);
            } else if (attr instanceof NumericAttribute ? true : UnresolvedAttribute$.MODULE$.equals(attr)) {
               return scala.package..MODULE$.Iterator().apply(scala.collection.immutable.Nil..MODULE$);
            } else if (attr instanceof BinaryAttribute) {
               return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(idx)), BoxesRunTime.boxToInteger(2))})));
            } else if (attr instanceof NominalAttribute) {
               NominalAttribute var9 = (NominalAttribute)attr;
               Option var10 = var9.getNumValues();
               if (var10 instanceof Some) {
                  Some var11 = (Some)var10;
                  int numValues = BoxesRunTime.unboxToInt(var11.value());
                  if (true) {
                     return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(idx)), BoxesRunTime.boxToInteger(numValues))})));
                  }
               }

               if (.MODULE$.equals(var10)) {
                  throw new IllegalArgumentException("Feature " + idx + " is marked as Nominal (categorical), but it does not have the number of values specified.");
               } else {
                  throw new MatchError(var10);
               }
            } else {
               throw new MatchError(attr);
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl()));
   }

   public int[] getFeatureIndicesFromNames(final StructField col, final String[] names) {
      scala.Predef..MODULE$.require(col.dataType() instanceof VectorUDT, () -> "getFeatureIndicesFromNames expected column " + col + " to be Vector type, but it was type " + col.dataType() + " instead.");
      AttributeGroup inputAttr = AttributeGroup$.MODULE$.fromStructField(col);
      return (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])names), (name) -> BoxesRunTime.boxToInteger($anonfun$getFeatureIndicesFromNames$2(inputAttr, col, name)), scala.reflect.ClassTag..MODULE$.Int());
   }

   // $FF: synthetic method
   public static final int $anonfun$getFeatureIndicesFromNames$2(final AttributeGroup inputAttr$1, final StructField col$1, final String name) {
      scala.Predef..MODULE$.require(inputAttr$1.hasAttr(name), () -> "getFeatureIndicesFromNames found no feature with name " + name + " in column " + col$1 + ".");
      return BoxesRunTime.unboxToInt(inputAttr$1.getAttr(name).index().get());
   }

   private MetadataUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
