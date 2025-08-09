package org.apache.arrow.vector.complex.impl;

import java.nio.ByteBuffer;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.LargeVarBinaryVector;
import org.apache.arrow.vector.holders.LargeVarBinaryHolder;
import org.apache.arrow.vector.holders.NullableLargeVarBinaryHolder;
import org.apache.arrow.vector.types.pojo.Field;

public class LargeVarBinaryWriterImpl extends AbstractFieldWriter {
   final LargeVarBinaryVector vector;

   public LargeVarBinaryWriterImpl(LargeVarBinaryVector vector) {
      this.vector = vector;
   }

   public Field getField() {
      return this.vector.getField();
   }

   public int getValueCapacity() {
      return this.vector.getValueCapacity();
   }

   public void allocate() {
      this.vector.allocateNew();
   }

   public void close() {
      this.vector.close();
   }

   public void clear() {
      this.vector.clear();
   }

   protected int idx() {
      return super.idx();
   }

   public void write(LargeVarBinaryHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void write(NullableLargeVarBinaryHolder h) {
      this.vector.setSafe(this.idx(), h);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeLargeVarBinary(long start, long end, ArrowBuf buffer) {
      this.vector.setSafe(this.idx(), 1, start, end, buffer);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeNull() {
      this.vector.setNull(this.idx());
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeLargeVarBinary(byte[] value) {
      this.vector.setSafe(this.idx(), (byte[])value);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeLargeVarBinary(byte[] value, int offset, int length) {
      this.vector.setSafe(this.idx(), value, offset, length);
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeLargeVarBinary(ByteBuffer value) {
      this.vector.setSafe(this.idx(), value, 0, value.remaining());
      this.vector.setValueCount(this.idx() + 1);
   }

   public void writeLargeVarBinary(ByteBuffer value, int offset, int length) {
      this.vector.setSafe(this.idx(), value, offset, length);
      this.vector.setValueCount(this.idx() + 1);
   }
}
