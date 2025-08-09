package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.StructVector;

public class NullableCaseSensitiveStructWriter extends NullableStructWriter {
   public NullableCaseSensitiveStructWriter(StructVector container) {
      super(container);
   }

   protected String handleCase(String input) {
      return input;
   }

   protected NullableStructWriterFactory getNullableStructWriterFactory() {
      return NullableStructWriterFactory.getNullableCaseSensitiveStructWriterFactoryInstance();
   }
}
