package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.writer.BaseWriter;
import org.apache.arrow.vector.complex.writer.BigIntWriter;
import org.apache.arrow.vector.complex.writer.BitWriter;
import org.apache.arrow.vector.complex.writer.Float4Writer;
import org.apache.arrow.vector.complex.writer.Float8Writer;
import org.apache.arrow.vector.complex.writer.IntWriter;
import org.apache.arrow.vector.complex.writer.VarBinaryWriter;
import org.apache.arrow.vector.complex.writer.VarCharWriter;

public class StructOrListWriterImpl implements BaseWriter.StructOrListWriter {
   public final BaseWriter.StructWriter struct;
   public final BaseWriter.ListWriter list;

   public StructOrListWriterImpl(BaseWriter.StructWriter writer) {
      this.struct = writer;
      this.list = null;
   }

   public StructOrListWriterImpl(BaseWriter.ListWriter writer) {
      this.struct = null;
      this.list = writer;
   }

   public void start() {
      if (this.struct != null) {
         this.struct.start();
      } else {
         this.list.startList();
      }

   }

   public void end() {
      if (this.struct != null) {
         this.struct.end();
      } else {
         this.list.endList();
      }

   }

   public BaseWriter.StructOrListWriter struct(String name) {
      assert this.struct != null;

      return new StructOrListWriterImpl(this.struct.struct(name));
   }

   /** @deprecated */
   @Deprecated
   public BaseWriter.StructOrListWriter listoftstruct(String name) {
      return this.listOfStruct(name);
   }

   public BaseWriter.StructOrListWriter listOfStruct(String name) {
      assert this.list != null;

      return new StructOrListWriterImpl(this.list.struct());
   }

   public BaseWriter.StructOrListWriter list(String name) {
      assert this.struct != null;

      return new StructOrListWriterImpl(this.struct.list(name));
   }

   public boolean isStructWriter() {
      return this.struct != null;
   }

   public boolean isListWriter() {
      return this.list != null;
   }

   public VarCharWriter varChar(String name) {
      return this.struct != null ? this.struct.varChar(name) : this.list.varChar();
   }

   public IntWriter integer(String name) {
      return this.struct != null ? this.struct.integer(name) : this.list.integer();
   }

   public BigIntWriter bigInt(String name) {
      return this.struct != null ? this.struct.bigInt(name) : this.list.bigInt();
   }

   public Float4Writer float4(String name) {
      return this.struct != null ? this.struct.float4(name) : this.list.float4();
   }

   public Float8Writer float8(String name) {
      return this.struct != null ? this.struct.float8(name) : this.list.float8();
   }

   public BitWriter bit(String name) {
      return this.struct != null ? this.struct.bit(name) : this.list.bit();
   }

   public VarBinaryWriter binary(String name) {
      return this.struct != null ? this.struct.varBinary(name) : this.list.varBinary();
   }
}
