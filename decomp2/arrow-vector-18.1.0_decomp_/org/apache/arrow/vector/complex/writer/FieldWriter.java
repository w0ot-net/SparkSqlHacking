package org.apache.arrow.vector.complex.writer;

public interface FieldWriter extends BaseWriter.StructWriter, BaseWriter.ListWriter, BaseWriter.MapWriter, BaseWriter.ScalarWriter {
   void allocate();

   void clear();
}
