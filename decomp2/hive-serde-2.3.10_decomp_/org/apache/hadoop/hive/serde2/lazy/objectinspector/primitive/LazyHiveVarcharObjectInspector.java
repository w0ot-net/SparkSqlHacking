package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveVarchar;
import org.apache.hadoop.hive.serde2.io.HiveVarcharWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveVarchar;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveVarcharObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.VarcharTypeInfo;

public class LazyHiveVarcharObjectInspector extends AbstractPrimitiveLazyObjectInspector implements HiveVarcharObjectInspector {
   private boolean escaped;
   private byte escapeChar;

   public LazyHiveVarcharObjectInspector() {
   }

   public LazyHiveVarcharObjectInspector(VarcharTypeInfo typeInfo) {
      this(typeInfo, false, (byte)0);
   }

   public LazyHiveVarcharObjectInspector(VarcharTypeInfo typeInfo, boolean escaped, byte escapeChar) {
      super(typeInfo);
      this.escaped = escaped;
      this.escapeChar = escapeChar;
   }

   public Object copyObject(Object o) {
      if (o == null) {
         return null;
      } else {
         LazyHiveVarchar ret = new LazyHiveVarchar(this);
         ret.setValue((LazyHiveVarchar)o);
         return ret;
      }
   }

   public HiveVarchar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveVarchar ret = ((HiveVarcharWritable)((LazyHiveVarchar)o).getWritableObject()).getHiveVarchar();
         if (!BaseCharUtils.doesPrimitiveMatchTypeParams(ret, (VarcharTypeInfo)this.typeInfo)) {
            HiveVarchar newValue = new HiveVarchar(ret, ((VarcharTypeInfo)this.typeInfo).getLength());
            return newValue;
         } else {
            return ret;
         }
      }
   }

   public boolean isEscaped() {
      return this.escaped;
   }

   public byte getEscapeChar() {
      return this.escapeChar;
   }

   public String toString() {
      return this.getTypeName();
   }
}
