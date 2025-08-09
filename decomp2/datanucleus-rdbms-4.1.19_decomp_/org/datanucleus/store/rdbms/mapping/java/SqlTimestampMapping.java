package org.datanucleus.store.rdbms.mapping.java;

import java.sql.Timestamp;

public class SqlTimestampMapping extends TemporalMapping {
   public Class getJavaType() {
      return Timestamp.class;
   }

   protected int getDefaultLengthAsString() {
      return 29;
   }
}
