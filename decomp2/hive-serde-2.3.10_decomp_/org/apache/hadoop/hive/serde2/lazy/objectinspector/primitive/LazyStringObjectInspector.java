package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyString;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.StringObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;

public class LazyStringObjectInspector extends AbstractPrimitiveLazyObjectInspector implements StringObjectInspector {
   private boolean escaped;
   private byte escapeChar;

   protected LazyStringObjectInspector() {
   }

   LazyStringObjectInspector(boolean escaped, byte escapeChar) {
      super(TypeInfoFactory.stringTypeInfo);
      this.escaped = escaped;
      this.escapeChar = escapeChar;
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyString((LazyString)o);
   }

   public Text getPrimitiveWritableObject(Object o) {
      return o == null ? null : (Text)((LazyString)o).getWritableObject();
   }

   public String getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((Text)((LazyString)o).getWritableObject()).toString();
   }

   public boolean isEscaped() {
      return this.escaped;
   }

   public byte getEscapeChar() {
      return this.escapeChar;
   }
}
