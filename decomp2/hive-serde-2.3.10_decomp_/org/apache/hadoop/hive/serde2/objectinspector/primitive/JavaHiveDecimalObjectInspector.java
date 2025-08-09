package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import java.math.BigInteger;
import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.HiveDecimalUtils;

public class JavaHiveDecimalObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableHiveDecimalObjectInspector {
   public JavaHiveDecimalObjectInspector() {
   }

   public JavaHiveDecimalObjectInspector(DecimalTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveDecimalWritable getPrimitiveWritableObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof String) {
         HiveDecimal dec = this.enforcePrecisionScale(HiveDecimal.create((String)o));
         return dec == null ? null : new HiveDecimalWritable(dec);
      } else {
         HiveDecimal dec = this.enforcePrecisionScale((HiveDecimal)o);
         return dec == null ? null : new HiveDecimalWritable(dec);
      }
   }

   public HiveDecimal getPrimitiveJavaObject(Object o) {
      return this.enforcePrecisionScale((HiveDecimal)o);
   }

   public Object set(Object o, byte[] bytes, int scale) {
      return this.enforcePrecisionScale(HiveDecimal.create(new BigInteger(bytes), scale));
   }

   public Object set(Object o, HiveDecimal t) {
      return this.enforcePrecisionScale(t);
   }

   public Object set(Object o, HiveDecimalWritable t) {
      return t == null ? null : this.enforcePrecisionScale(t.getHiveDecimal());
   }

   public Object create(byte[] bytes, int scale) {
      return HiveDecimal.create(new BigInteger(bytes), scale);
   }

   public Object create(HiveDecimal t) {
      return t;
   }

   private HiveDecimal enforcePrecisionScale(HiveDecimal dec) {
      return HiveDecimalUtils.enforcePrecisionScale(dec, (DecimalTypeInfo)this.typeInfo);
   }
}
