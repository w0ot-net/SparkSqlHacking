package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;

public class JavaHiveCharObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableHiveCharObjectInspector {
   public JavaHiveCharObjectInspector() {
   }

   public JavaHiveCharObjectInspector(CharTypeInfo typeInfo) {
      super(typeInfo);
   }

   public HiveChar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveChar value;
         if (o instanceof String) {
            value = new HiveChar((String)o, this.getMaxLength());
         } else {
            value = (HiveChar)o;
         }

         return BaseCharUtils.doesPrimitiveMatchTypeParams(value, (CharTypeInfo)this.typeInfo) ? value : this.getPrimitiveWithParams(value);
      }
   }

   public HiveCharWritable getPrimitiveWritableObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveChar var;
         if (o instanceof String) {
            var = new HiveChar((String)o, this.getMaxLength());
         } else {
            var = (HiveChar)o;
         }

         return this.getWritableWithParams(var);
      }
   }

   private HiveChar getPrimitiveWithParams(HiveChar val) {
      HiveChar hc = new HiveChar(val, this.getMaxLength());
      return hc;
   }

   private HiveCharWritable getWritableWithParams(HiveChar val) {
      HiveCharWritable hcw = new HiveCharWritable();
      hcw.set(val, this.getMaxLength());
      return hcw;
   }

   public Object set(Object o, HiveChar value) {
      return BaseCharUtils.doesPrimitiveMatchTypeParams(value, (CharTypeInfo)this.typeInfo) ? value : new HiveChar(value, this.getMaxLength());
   }

   public Object set(Object o, String value) {
      return new HiveChar(value, this.getMaxLength());
   }

   public Object create(HiveChar value) {
      HiveChar hc = new HiveChar(value, this.getMaxLength());
      return hc;
   }

   public int getMaxLength() {
      CharTypeInfo ti = (CharTypeInfo)this.typeInfo;
      return ti.getLength();
   }
}
