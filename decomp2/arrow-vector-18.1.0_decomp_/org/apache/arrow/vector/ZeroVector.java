package org.apache.arrow.vector;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public final class ZeroVector extends NullVector {
   public static final ZeroVector INSTANCE = new ZeroVector();
   private final TransferPair defaultPair = new TransferPair() {
      public void transfer() {
      }

      public void splitAndTransfer(int startIndex, int length) {
      }

      public ValueVector getTo() {
         return ZeroVector.this;
      }

      public void copyValueSafe(int from, int to) {
      }
   };

   public ZeroVector(String name) {
      super(name);
   }

   public ZeroVector(String name, FieldType fieldType) {
      super(name, fieldType);
   }

   public ZeroVector(Field field) {
      super(field);
   }

   /** @deprecated */
   @Deprecated
   public ZeroVector() {
   }

   public int getValueCount() {
      return 0;
   }

   public void setValueCount(int valueCount) {
   }

   public int getNullCount() {
      return 0;
   }

   public boolean isNull(int index) {
      throw new IndexOutOfBoundsException();
   }

   public int hashCode(int index) {
      return 0;
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      return 0;
   }

   public int getValueCapacity() {
      return 0;
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.defaultPair;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.defaultPair;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return this.defaultPair;
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return this.defaultPair;
   }
}
