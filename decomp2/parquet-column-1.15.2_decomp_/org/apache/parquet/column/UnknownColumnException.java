package org.apache.parquet.column;

import org.apache.parquet.ParquetRuntimeException;

public class UnknownColumnException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;
   private final ColumnDescriptor descriptor;

   public UnknownColumnException(ColumnDescriptor descriptor) {
      super("Column not found: " + descriptor.toString());
      this.descriptor = descriptor;
   }

   public ColumnDescriptor getDescriptor() {
      return this.descriptor;
   }
}
