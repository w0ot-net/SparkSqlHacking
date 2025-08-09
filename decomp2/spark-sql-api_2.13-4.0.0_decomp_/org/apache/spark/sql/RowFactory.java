package org.apache.spark.sql;

import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.expressions.GenericRow;

@Stable
public class RowFactory {
   public static Row create(Object... values) {
      return new GenericRow(values);
   }
}
