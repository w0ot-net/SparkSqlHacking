package org.apache.arrow.vector.extension;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.types.pojo.Field;

public class OpaqueVector extends ExtensionTypeVector implements ValueIterableVector {
   private final Field field;

   public OpaqueVector(Field field, BufferAllocator allocator, FieldVector underlyingVector) {
      super((Field)field, allocator, underlyingVector);
      this.field = field;
   }

   public Field getField() {
      return this.field;
   }

   public Object getObject(int index) {
      return ((FieldVector)this.getUnderlyingVector()).getObject(index);
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      return ((FieldVector)this.getUnderlyingVector()).hashCode(index, hasher);
   }
}
