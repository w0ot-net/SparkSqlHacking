package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.StructVector;

public class NullableStructWriterFactory {
   private final boolean caseSensitive;
   private static final NullableStructWriterFactory nullableStructWriterFactory = new NullableStructWriterFactory(false);
   private static final NullableStructWriterFactory nullableCaseSensitiveWriterFactory = new NullableStructWriterFactory(true);

   public NullableStructWriterFactory(boolean caseSensitive) {
      this.caseSensitive = caseSensitive;
   }

   public NullableStructWriter build(StructVector container) {
      return (NullableStructWriter)(this.caseSensitive ? new NullableCaseSensitiveStructWriter(container) : new NullableStructWriter(container));
   }

   public static NullableStructWriterFactory getNullableStructWriterFactoryInstance() {
      return nullableStructWriterFactory;
   }

   public static NullableStructWriterFactory getNullableCaseSensitiveStructWriterFactoryInstance() {
      return nullableCaseSensitiveWriterFactory;
   }
}
