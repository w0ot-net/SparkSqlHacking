package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.NonNullableStructVector;

public class SingleCaseSensitiveStructWriter extends SingleStructWriter {
   public SingleCaseSensitiveStructWriter(NonNullableStructVector container) {
      super(container);
   }

   protected String handleCase(String input) {
      return input;
   }

   protected NullableStructWriterFactory getNullableStructWriterFactory() {
      return NullableStructWriterFactory.getNullableCaseSensitiveStructWriterFactoryInstance();
   }
}
