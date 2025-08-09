package org.apache.hadoop.hive.serde2.typeinfo;

import java.io.Serializable;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorUtils;

public class PrimitiveTypeInfo extends TypeInfo implements Serializable {
   private static final long serialVersionUID = 1L;
   protected String typeName;

   public PrimitiveTypeInfo() {
   }

   PrimitiveTypeInfo(String typeName) {
      this.typeName = typeName;
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.PRIMITIVE;
   }

   public PrimitiveObjectInspector.PrimitiveCategory getPrimitiveCategory() {
      return this.getPrimitiveTypeEntry().primitiveCategory;
   }

   public Class getPrimitiveWritableClass() {
      return this.getPrimitiveTypeEntry().primitiveWritableClass;
   }

   public Class getPrimitiveJavaClass() {
      return this.getPrimitiveTypeEntry().primitiveJavaClass;
   }

   public void setTypeName(String typeName) {
      this.typeName = typeName;
   }

   public String getTypeName() {
      return this.typeName;
   }

   public PrimitiveObjectInspectorUtils.PrimitiveTypeEntry getPrimitiveTypeEntry() {
      return PrimitiveObjectInspectorUtils.getTypeEntryFromTypeName(this.typeName);
   }

   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         PrimitiveTypeInfo pti = (PrimitiveTypeInfo)other;
         return this.typeName.equals(pti.typeName);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.typeName.hashCode();
   }

   public String toString() {
      return this.typeName;
   }
}
