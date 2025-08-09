package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;

public class JavaStringObjectInspector extends AbstractPrimitiveJavaObjectInspector implements SettableStringObjectInspector {
   protected JavaStringObjectInspector() {
      super(TypeInfoFactory.stringTypeInfo);
   }

   public Text getPrimitiveWritableObject(Object o) {
      return o == null ? null : new Text(o.toString());
   }

   public String getPrimitiveJavaObject(Object o) {
      return o == null ? null : o.toString();
   }

   public Object create(Text value) {
      return value == null ? null : value.toString();
   }

   public Object set(Object o, Text value) {
      return value == null ? null : value.toString();
   }

   public Object create(String value) {
      return value;
   }

   public Object set(Object o, String value) {
      return value;
   }
}
