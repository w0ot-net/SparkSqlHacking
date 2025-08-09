package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;
import org.apache.hadoop.io.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WritableHiveVarcharObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableHiveVarcharObjectInspector {
   private static final Logger LOG = LoggerFactory.getLogger(WritableHiveVarcharObjectInspector.class);

   public WritableHiveVarcharObjectInspector() {
   }

   public WritableHiveVarcharObjectInspector(VarcharTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveVarchar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         return new HiveVarchar(str, ((VarcharTypeInfo)this.typeInfo).getLength());
      } else {
         HiveVarcharWritable writable = (HiveVarcharWritable)o;
         return this.doesWritableMatchTypeParams(writable) ? writable.getHiveVarchar() : this.getPrimitiveWithParams(writable);
      }
   }

   public HiveVarcharWritable getPrimitiveWritableObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         HiveVarcharWritable hcw = new HiveVarcharWritable();
         hcw.set(str, ((VarcharTypeInfo)this.typeInfo).getLength());
         return hcw;
      } else {
         HiveVarcharWritable writable = (HiveVarcharWritable)o;
         return this.doesWritableMatchTypeParams((HiveVarcharWritable)o) ? writable : this.getWritableWithParams(writable);
      }
   }

   private HiveVarchar getPrimitiveWithParams(HiveVarcharWritable val) {
      HiveVarchar hv = new HiveVarchar();
      hv.setValue(val.getHiveVarchar(), this.getMaxLength());
      return hv;
   }

   private HiveVarcharWritable getWritableWithParams(HiveVarcharWritable val) {
      HiveVarcharWritable newValue = new HiveVarcharWritable();
      newValue.set(val, this.getMaxLength());
      return newValue;
   }

   private boolean doesWritableMatchTypeParams(HiveVarcharWritable writable) {
      return BaseCharUtils.doesWritableMatchTypeParams(writable, (VarcharTypeInfo)this.typeInfo);
   }

   public Object copyObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         HiveVarcharWritable hcw = new HiveVarcharWritable();
         hcw.set(str, ((VarcharTypeInfo)this.typeInfo).getLength());
         return hcw;
      } else {
         HiveVarcharWritable writable = (HiveVarcharWritable)o;
         return this.doesWritableMatchTypeParams((HiveVarcharWritable)o) ? new HiveVarcharWritable(writable) : this.getWritableWithParams(writable);
      }
   }

   public Object set(Object o, HiveVarchar value) {
      if (value == null) {
         return null;
      } else {
         HiveVarcharWritable writable = (HiveVarcharWritable)o;
         writable.set(value, this.getMaxLength());
         return o;
      }
   }

   public Object set(Object o, String value) {
      if (value == null) {
         return null;
      } else {
         HiveVarcharWritable writable = (HiveVarcharWritable)o;
         writable.set(value, this.getMaxLength());
         return o;
      }
   }

   public Object create(HiveVarchar value) {
      HiveVarcharWritable ret = new HiveVarcharWritable();
      ret.set(value, this.getMaxLength());
      return ret;
   }

   public int getMaxLength() {
      return ((VarcharTypeInfo)this.typeInfo).getLength();
   }
}
