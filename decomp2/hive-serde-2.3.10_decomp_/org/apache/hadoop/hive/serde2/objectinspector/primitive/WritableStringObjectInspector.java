package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.Text;

public class WritableStringObjectInspector extends AbstractPrimitiveWritableObjectInspector implements SettableStringObjectInspector {
   WritableStringObjectInspector() {
      super(TypeInfoFactory.stringTypeInfo);
   }

   public Object copyObject(Object o) {
      return o == null ? null : new Text((Text)o);
   }

   public Text getPrimitiveWritableObject(Object o) {
      return o == null ? null : (Text)o;
   }

   public String getPrimitiveJavaObject(Object o) {
      return o == null ? null : ((Text)o).toString();
   }

   public Object create(Text value) {
      Text r = new Text();
      if (value != null) {
         r.set(value);
      }

      return r;
   }

   public Object create(String value) {
      Text r = new Text();
      if (value != null) {
         r.set(value);
      }

      return r;
   }

   public Object set(Object o, Text value) {
      Text r = (Text)o;
      if (value != null) {
         r.set(value);
      }

      return o;
   }

   public Object set(Object o, String value) {
      Text r = (Text)o;
      if (value != null) {
         r.set(value);
      }

      return o;
   }
}
