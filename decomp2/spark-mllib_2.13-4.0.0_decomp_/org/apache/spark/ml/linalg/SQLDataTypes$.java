package org.apache.spark.ml.linalg;

import org.apache.spark.sql.types.DataType;

public final class SQLDataTypes$ {
   public static final SQLDataTypes$ MODULE$ = new SQLDataTypes$();
   private static final DataType VectorType = new VectorUDT();
   private static final DataType MatrixType = new MatrixUDT();

   public DataType VectorType() {
      return VectorType;
   }

   public DataType MatrixType() {
      return MatrixType;
   }

   private SQLDataTypes$() {
   }
}
