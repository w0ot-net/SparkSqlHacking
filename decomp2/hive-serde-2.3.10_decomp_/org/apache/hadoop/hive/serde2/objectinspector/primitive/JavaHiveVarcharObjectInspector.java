package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;

public class JavaHiveVarcharObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableHiveVarcharObjectInspector {
   public JavaHiveVarcharObjectInspector() {
   }

   public JavaHiveVarcharObjectInspector(VarcharTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveVarchar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveVarchar value;
         if (o instanceof String) {
            value = new HiveVarchar((String)o, this.getMaxLength());
         } else {
            value = (HiveVarchar)o;
         }

         return BaseCharUtils.doesPrimitiveMatchTypeParams(value, (VarcharTypeInfo)this.typeInfo) ? value : this.getPrimitiveWithParams(value);
      }
   }

   public HiveVarcharWritable getPrimitiveWritableObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveVarchar var;
         if (o instanceof String) {
            var = new HiveVarchar((String)o, this.getMaxLength());
         } else {
            var = (HiveVarchar)o;
         }

         return this.getWritableWithParams(var);
      }
   }

   public Object set(Object o, HiveVarchar value) {
      return BaseCharUtils.doesPrimitiveMatchTypeParams(value, (VarcharTypeInfo)this.typeInfo) ? value : new HiveVarchar(value, this.getMaxLength());
   }

   public Object set(Object o, String value) {
      return new HiveVarchar(value, this.getMaxLength());
   }

   public Object create(HiveVarchar value) {
      return new HiveVarchar(value, this.getMaxLength());
   }

   public int getMaxLength() {
      VarcharTypeInfo ti = (VarcharTypeInfo)this.typeInfo;
      return ti.getLength();
   }

   private HiveVarchar getPrimitiveWithParams(HiveVarchar val) {
      return new HiveVarchar(val, this.getMaxLength());
   }

   private HiveVarcharWritable getWritableWithParams(HiveVarchar val) {
      HiveVarcharWritable newValue = new HiveVarcharWritable();
      newValue.set(val, this.getMaxLength());
      return newValue;
   }
}
