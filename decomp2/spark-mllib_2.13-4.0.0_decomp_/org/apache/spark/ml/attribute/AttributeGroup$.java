package org.apache.spark.ml.attribute;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import scala.Predef;
import scala.collection.ArrayOps.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class AttributeGroup$ implements Serializable {
   public static final AttributeGroup$ MODULE$ = new AttributeGroup$();

   public AttributeGroup fromMetadata(final Metadata metadata, final String name) {
      if (metadata.contains(AttributeKeys$.MODULE$.ATTRIBUTES())) {
         int numAttrs = (int)metadata.getLong(AttributeKeys$.MODULE$.NUM_ATTRIBUTES());
         Attribute[] attributes = new Attribute[numAttrs];
         Metadata attrMetadata = metadata.getMetadata(AttributeKeys$.MODULE$.ATTRIBUTES());
         if (attrMetadata.contains(AttributeType$.MODULE$.Numeric().name())) {
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])attrMetadata.getMetadataArray(AttributeType$.MODULE$.Numeric().name())), (metadatax) -> NumericAttribute$.MODULE$.fromMetadata(metadatax), scala.reflect.ClassTag..MODULE$.apply(NumericAttribute.class))), (attr) -> {
               $anonfun$fromMetadata$2(attributes, attr);
               return BoxedUnit.UNIT;
            });
         }

         if (attrMetadata.contains(AttributeType$.MODULE$.Nominal().name())) {
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])attrMetadata.getMetadataArray(AttributeType$.MODULE$.Nominal().name())), (metadatax) -> NominalAttribute$.MODULE$.fromMetadata(metadatax), scala.reflect.ClassTag..MODULE$.apply(NominalAttribute.class))), (attr) -> {
               $anonfun$fromMetadata$4(attributes, attr);
               return BoxedUnit.UNIT;
            });
         }

         if (attrMetadata.contains(AttributeType$.MODULE$.Binary().name())) {
            .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])attrMetadata.getMetadataArray(AttributeType$.MODULE$.Binary().name())), (metadatax) -> BinaryAttribute$.MODULE$.fromMetadata(metadatax), scala.reflect.ClassTag..MODULE$.apply(BinaryAttribute.class))), (attr) -> {
               $anonfun$fromMetadata$6(attributes, attr);
               return BoxedUnit.UNIT;
            });
         }

         for(int i = 0; i < numAttrs; ++i) {
            if (attributes[i] == null) {
               attributes[i] = NumericAttribute$.MODULE$.defaultAttr();
            }
         }

         return new AttributeGroup(name, attributes);
      } else {
         return metadata.contains(AttributeKeys$.MODULE$.NUM_ATTRIBUTES()) ? new AttributeGroup(name, (int)metadata.getLong(AttributeKeys$.MODULE$.NUM_ATTRIBUTES())) : new AttributeGroup(name);
      }
   }

   public AttributeGroup fromStructField(final StructField field) {
      boolean var3;
      Predef var10000;
      label21: {
         label20: {
            var10000 = scala.Predef..MODULE$;
            DataType var10001 = field.dataType();
            VectorUDT var2 = new VectorUDT();
            if (var10001 == null) {
               if (var2 == null) {
                  break label20;
               }
            } else if (var10001.equals(var2)) {
               break label20;
            }

            var3 = false;
            break label21;
         }

         var3 = true;
      }

      var10000.require(var3);
      return field.metadata().contains(AttributeKeys$.MODULE$.ML_ATTR()) ? this.fromMetadata(field.metadata().getMetadata(AttributeKeys$.MODULE$.ML_ATTR()), field.name()) : new AttributeGroup(field.name());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AttributeGroup$.class);
   }

   // $FF: synthetic method
   public static final void $anonfun$fromMetadata$2(final Attribute[] attributes$1, final NumericAttribute attr) {
      attributes$1[BoxesRunTime.unboxToInt(attr.index().get())] = attr;
   }

   // $FF: synthetic method
   public static final void $anonfun$fromMetadata$4(final Attribute[] attributes$1, final NominalAttribute attr) {
      attributes$1[BoxesRunTime.unboxToInt(attr.index().get())] = attr;
   }

   // $FF: synthetic method
   public static final void $anonfun$fromMetadata$6(final Attribute[] attributes$1, final BinaryAttribute attr) {
      attributes$1[BoxesRunTime.unboxToInt(attr.index().get())] = attr;
   }

   private AttributeGroup$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
