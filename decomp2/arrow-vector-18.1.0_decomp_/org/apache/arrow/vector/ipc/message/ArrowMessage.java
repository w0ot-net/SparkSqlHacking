package org.apache.arrow.vector.ipc.message;

public interface ArrowMessage extends FBSerializable, AutoCloseable {
   long computeBodyLength();

   Object accepts(ArrowMessageVisitor var1);

   byte getMessageType();

   public interface ArrowMessageVisitor {
      Object visit(ArrowDictionaryBatch var1);

      Object visit(ArrowRecordBatch var1);
   }
}
