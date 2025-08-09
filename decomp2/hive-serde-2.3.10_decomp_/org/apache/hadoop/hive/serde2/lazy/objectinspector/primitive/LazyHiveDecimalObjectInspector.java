package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveDecimal;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveDecimalObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;

public class LazyHiveDecimalObjectInspector extends AbstractPrimitiveLazyObjectInspector implements HiveDecimalObjectInspector {
   protected LazyHiveDecimalObjectInspector(DecimalTypeInfo typeInfo) {
      super(typeInfo);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyHiveDecimal((LazyHiveDecimal)o);
   }

   public HiveDecimal getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else {
         DecimalTypeInfo decimalTypeInfo = (DecimalTypeInfo)this.typeInfo;
         HiveDecimalWritable decWritable = ((LazyHiveDecimal)o).getWritableObject();
         HiveDecimalWritable result = HiveDecimalWritable.enforcePrecisionScale(decWritable, decimalTypeInfo.getPrecision(), decimalTypeInfo.getScale());
         return result != null && result.isSet() ? result.getHiveDecimal() : null;
      }
   }
}
