package org.datanucleus.store.rdbms.mapping.java;

import java.sql.Date;

public class SqlDateMapping extends TemporalMapping {
   public Class getJavaType() {
      return Date.class;
   }

   protected int getDefaultLengthAsString() {
      return 10;
   }
}
