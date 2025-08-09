package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.PrimitiveObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.HiveDecimalUtils;
import org.apache.hadoop.hive.serde2.typeinfo.PrimitiveTypeInfo;

public abstract class AbstractPrimitiveObjectInspector implements PrimitiveObjectInspector {
   protected PrimitiveTypeInfo typeInfo;

   protected AbstractPrimitiveObjectInspector() {
   }

   protected AbstractPrimitiveObjectInspector(PrimitiveTypeInfo typeInfo) {
      this.typeInfo = typeInfo;
   }

   public Class getJavaPrimitiveClass() {
      return this.typeInfo.getPrimitiveJavaClass();
   }

   public PrimitiveObjectInspector.PrimitiveCategory getPrimitiveCategory() {
      return this.typeInfo.getPrimitiveCategory();
   }

   public Class getPrimitiveWritableClass() {
      return this.typeInfo.getPrimitiveWritableClass();
   }

   public ObjectInspector.Category getCategory() {
      return ObjectInspector.Category.PRIMITIVE;
   }

   public String getTypeName() {
      return this.typeInfo.getTypeName();
   }

   public PrimitiveTypeInfo getTypeInfo() {
      return this.typeInfo;
   }

   public int precision() {
      return HiveDecimalUtils.getPrecisionForType(this.typeInfo);
   }

   public int scale() {
      return HiveDecimalUtils.getScaleForType(this.typeInfo);
   }
}
