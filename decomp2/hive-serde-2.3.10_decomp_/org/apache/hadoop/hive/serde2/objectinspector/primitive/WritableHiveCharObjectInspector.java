package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;
import org.apache.hadoop.io.Text;

public class WritableHiveCharObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableHiveCharObjectInspector {
   public WritableHiveCharObjectInspector() {
   }

   public WritableHiveCharObjectInspector(CharTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveChar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         return new HiveChar(str, ((CharTypeInfo)this.typeInfo).getLength());
      } else {
         HiveCharWritable writable = (HiveCharWritable)o;
         return this.doesWritableMatchTypeParams(writable) ? writable.getHiveChar() : this.getPrimitiveWithParams(writable);
      }
   }

   public HiveCharWritable getPrimitiveWritableObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         HiveCharWritable hcw = new HiveCharWritable();
         hcw.set(str, ((CharTypeInfo)this.typeInfo).getLength());
         return hcw;
      } else {
         HiveCharWritable writable = (HiveCharWritable)o;
         return this.doesWritableMatchTypeParams((HiveCharWritable)o) ? writable : this.getWritableWithParams(writable);
      }
   }

   private HiveChar getPrimitiveWithParams(HiveCharWritable val) {
      HiveChar hv = new HiveChar();
      hv.setValue(val.getHiveChar(), this.getMaxLength());
      return hv;
   }

   private HiveCharWritable getWritableWithParams(HiveCharWritable val) {
      HiveCharWritable newValue = new HiveCharWritable();
      newValue.set(val, this.getMaxLength());
      return newValue;
   }

   private boolean doesWritableMatchTypeParams(HiveCharWritable writable) {
      return BaseCharUtils.doesWritableMatchTypeParams(writable, (CharTypeInfo)this.typeInfo);
   }

   public Object copyObject(Object o) {
      if (o == null) {
         return null;
      } else if (o instanceof Text) {
         String str = ((Text)o).toString();
         HiveCharWritable hcw = new HiveCharWritable();
         hcw.set(str, ((CharTypeInfo)this.typeInfo).getLength());
         return hcw;
      } else {
         HiveCharWritable writable = (HiveCharWritable)o;
         return this.doesWritableMatchTypeParams((HiveCharWritable)o) ? new HiveCharWritable(writable) : this.getWritableWithParams(writable);
      }
   }

   public Object set(Object o, HiveChar value) {
      if (value == null) {
         return null;
      } else {
         HiveCharWritable writable = (HiveCharWritable)o;
         writable.set(value, this.getMaxLength());
         return o;
      }
   }

   public Object set(Object o, String value) {
      if (value == null) {
         return null;
      } else {
         HiveCharWritable writable = (HiveCharWritable)o;
         writable.set(value, this.getMaxLength());
         return o;
      }
   }

   public Object create(HiveChar value) {
      HiveCharWritable ret = new HiveCharWritable();
      ret.set(value, this.getMaxLength());
      return ret;
   }

   public int getMaxLength() {
      return ((CharTypeInfo)this.typeInfo).getLength();
   }
}
