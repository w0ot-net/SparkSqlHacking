package org.apache.spark.sql.catalyst.parser;

public final class DataTypeParser$ extends AbstractParser {
   public static final DataTypeParser$ MODULE$ = new DataTypeParser$();

   public DataTypeAstBuilder astBuilder() {
      return new DataTypeAstBuilder();
   }

   private DataTypeParser$() {
   }
}
