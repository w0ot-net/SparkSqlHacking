package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.hadoop.hive.serde2.typeinfo.DecimalTypeInfo;
import org.apache.hadoop.hive.serde2.typeinfo.HiveDecimalUtils;

public class WritableHiveDecimalObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableHiveDecimalObjectInspector {
   public WritableHiveDecimalObjectInspector() {
   }

   public WritableHiveDecimalObjectInspector(DecimalTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveDecimalWritable getPrimitiveWritableObject(Object o) {
      return o == null ? null : this.enforcePrecisionScale((HiveDecimalWritable)o);
   }

   public HiveDecimal getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.enforcePrecisionScale(((HiveDecimalWritable)o).getHiveDecimal());
   }

   public Object copyObject(Object o) {
      return o == null ? null : new HiveDecimalWritable((HiveDecimalWritable)o);
   }

   public Object set(Object o, byte[] bytes, int scale) {
      HiveDecimalWritable writable = (HiveDecimalWritable)this.create(bytes, scale);
      if (writable != null) {
         ((HiveDecimalWritable)o).set(writable);
         return o;
      } else {
         return null;
      }
   }

   public Object set(Object o, HiveDecimal t) {
      HiveDecimal dec = this.enforcePrecisionScale(t);
      if (dec != null) {
         ((HiveDecimalWritable)o).set(dec);
         return o;
      } else {
         return null;
      }
   }

   public Object set(Object o, HiveDecimalWritable t) {
      HiveDecimalWritable writable = this.enforcePrecisionScale(t);
      if (writable == null) {
         return null;
      } else {
         ((HiveDecimalWritable)o).set(writable);
         return o;
      }
   }

   public Object create(byte[] bytes, int scale) {
      return new HiveDecimalWritable(bytes, scale);
   }

   public Object create(HiveDecimal t) {
      return t == null ? null : new HiveDecimalWritable(t);
   }

   private HiveDecimal enforcePrecisionScale(HiveDecimal dec) {
      return HiveDecimalUtils.enforcePrecisionScale(dec, (DecimalTypeInfo)this.typeInfo);
   }

   private HiveDecimalWritable enforcePrecisionScale(HiveDecimalWritable writable) {
      return HiveDecimalUtils.enforcePrecisionScale(writable, (DecimalTypeInfo)this.typeInfo);
   }
}
