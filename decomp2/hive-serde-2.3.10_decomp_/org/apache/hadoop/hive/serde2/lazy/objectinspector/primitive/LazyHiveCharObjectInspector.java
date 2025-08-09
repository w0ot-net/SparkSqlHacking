package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.common.type.HiveChar;
import org.apache.hadoop.hive.serde2.io.HiveCharWritable;
import org.apache.hadoop.hive.serde2.lazy.LazyHiveChar;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.HiveCharObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.BaseCharUtils;
import org.apache.hadoop.hive.serde2.typeinfo.CharTypeInfo;

public class LazyHiveCharObjectInspector extends AbstractPrimitiveLazyObjectInspector implements HiveCharObjectInspector {
   private boolean escaped;
   private byte escapeChar;

   public LazyHiveCharObjectInspector() {
   }

   public LazyHiveCharObjectInspector(CharTypeInfo typeInfo) {
      this(typeInfo, false, (byte)0);
   }

   public LazyHiveCharObjectInspector(CharTypeInfo typeInfo, boolean escaped, byte escapeChar) {
      super(typeInfo);
      this.escaped = escaped;
      this.escapeChar = escapeChar;
   }

   public Object copyObject(Object o) {
      if (o == null) {
         return null;
      } else {
         LazyHiveChar ret = new LazyHiveChar(this);
         ret.setValue((LazyHiveChar)o);
         return ret;
      }
   }

   public HiveChar getPrimitiveJavaObject(Object o) {
      if (o == null) {
         return null;
      } else {
         HiveChar ret = ((HiveCharWritable)((LazyHiveChar)o).getWritableObject()).getHiveChar();
         if (!BaseCharUtils.doesPrimitiveMatchTypeParams(ret, (CharTypeInfo)this.typeInfo)) {
            HiveChar newValue = new HiveChar(ret, ((CharTypeInfo)this.typeInfo).getLength());
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
