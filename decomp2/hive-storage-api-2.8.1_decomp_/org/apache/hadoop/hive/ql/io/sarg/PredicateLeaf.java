package org.apache.hadoop.hive.ql.io.sarg;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;

public interface PredicateLeaf {
   Operator getOperator();

   Type getType();

   String getColumnName();

   Object getLiteral();

   List getLiteralList();

   int getId();

   public static enum Operator {
      EQUALS,
      NULL_SAFE_EQUALS,
      LESS_THAN,
      LESS_THAN_EQUALS,
      IN,
      BETWEEN,
      IS_NULL;
   }

   public static enum Type {
      LONG(Long.class),
      FLOAT(Double.class),
      STRING(String.class),
      DATE(Date.class),
      DECIMAL(HiveDecimalWritable.class),
      TIMESTAMP(Timestamp.class),
      BOOLEAN(Boolean.class);

      private final Class cls;

      private Type(Class cls) {
         this.cls = cls;
      }

      public Class getValueClass() {
         return this.cls;
      }
   }
}
