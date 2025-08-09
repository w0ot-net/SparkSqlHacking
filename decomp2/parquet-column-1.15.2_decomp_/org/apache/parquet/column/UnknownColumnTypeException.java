package org.apache.parquet.column;

import org.apache.parquet.ParquetRuntimeException;
import org.apache.parquet.schema.PrimitiveType;

public class UnknownColumnTypeException extends ParquetRuntimeException {
   private static final long serialVersionUID = 1L;
   private final PrimitiveType.PrimitiveTypeName type;

   public UnknownColumnTypeException(PrimitiveType.PrimitiveTypeName type) {
      super("Column type not found: " + type.toString());
      this.type = type;
   }

   public PrimitiveType.PrimitiveTypeName getType() {
      return this.type;
   }
}
