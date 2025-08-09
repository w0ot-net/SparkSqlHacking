package org.apache.spark.ml.attribute;

import java.io.Serializable;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import scala.runtime.ModuleSerializationProxy;

public final class Attribute$ implements AttributeFactory, Serializable {
   public static final Attribute$ MODULE$ = new Attribute$();

   static {
      AttributeFactory.$init$(MODULE$);
   }

   public Attribute decodeStructField(final StructField field, final boolean preserveName) {
      return AttributeFactory.decodeStructField$(this, field, preserveName);
   }

   public Attribute fromStructField(final StructField field) {
      return AttributeFactory.fromStructField$(this, field);
   }

   public Attribute fromMetadata(final Metadata metadata) {
      String attrType = metadata.contains(AttributeKeys$.MODULE$.TYPE()) ? metadata.getString(AttributeKeys$.MODULE$.TYPE()) : AttributeType$.MODULE$.Numeric().name();
      return this.getFactory(attrType).fromMetadata(metadata);
   }

   private AttributeFactory getFactory(final String attrType) {
      String var2 = AttributeType$.MODULE$.Numeric().name();
      if (attrType == null) {
         if (var2 == null) {
            return NumericAttribute$.MODULE$;
         }
      } else if (attrType.equals(var2)) {
         return NumericAttribute$.MODULE$;
      }

      String var3 = AttributeType$.MODULE$.Nominal().name();
      if (attrType == null) {
         if (var3 == null) {
            return NominalAttribute$.MODULE$;
         }
      } else if (attrType.equals(var3)) {
         return NominalAttribute$.MODULE$;
      }

      String var4 = AttributeType$.MODULE$.Binary().name();
      if (attrType == null) {
         if (var4 == null) {
            return BinaryAttribute$.MODULE$;
         }
      } else if (attrType.equals(var4)) {
         return BinaryAttribute$.MODULE$;
      }

      throw new IllegalArgumentException("Cannot recognize type " + attrType + ".");
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Attribute$.class);
   }

   private Attribute$() {
   }
}
