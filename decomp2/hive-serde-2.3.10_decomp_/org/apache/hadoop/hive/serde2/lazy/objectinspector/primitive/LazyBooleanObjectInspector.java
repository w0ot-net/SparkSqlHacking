package org.apache.hadoop.hive.serde2.lazy.objectinspector.primitive;

import org.apache.hadoop.hive.serde2.lazy.LazyBoolean;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.BooleanObjectInspector;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfoFactory;
import org.apache.hadoop.io.BooleanWritable;

public class LazyBooleanObjectInspector extends AbstractPrimitiveLazyObjectInspector implements BooleanObjectInspector {
   private boolean extendedLiteral = false;

   LazyBooleanObjectInspector() {
      super(TypeInfoFactory.booleanTypeInfo);
   }

   public boolean get(Object o) {
      return ((BooleanWritable)this.getPrimitiveWritableObject(o)).get();
   }

   public Object copyObject(Object o) {
      return o == null ? null : new LazyBoolean((LazyBoolean)o);
   }

   public Object getPrimitiveJavaObject(Object o) {
      return o == null ? null : this.get(o);
   }

   public boolean isExtendedLiteral() {
      return this.extendedLiteral;
   }

   public void setExtendedLiteral(boolean extendedLiteral) {
      this.extendedLiteral = extendedLiteral;
   }
}
