package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;

public class WritableConstantHiveDecimalObjectInspector extends WritableHiveDecimalObjectInspector implements ConstantObjectInspector {
   private HiveDecimalWritable value;

   protected WritableConstantHiveDecimalObjectInspector() {
   }

   WritableConstantHiveDecimalObjectInspector(DecimalTypeInfo typeInfo, HiveDecimalWritable value) {
      super(typeInfo);
      this.value = value;
   }

   public HiveDecimalWritable getWritableConstantValue() {
      DecimalTypeInfo decTypeInfo = (DecimalTypeInfo)this.typeInfo;
      HiveDecimalWritable result = new HiveDecimalWritable(this.value);
      result.mutateEnforcePrecisionScale(decTypeInfo.precision(), decTypeInfo.scale());
      return !result.isSet() ? null : result;
   }

   public int precision() {
      return this.value == null ? super.precision() : this.value.precision();
   }

   public int scale() {
      return this.value == null ? super.scale() : this.value.getHiveDecimal().scale();
   }
}
