package org.datanucleus.store.rdbms.mapping.java;

import java.sql.Time;

public class SqlTimeMapping extends TemporalMapping {
   public Class getJavaType() {
      return Time.class;
   }

   protected int getDefaultLengthAsString() {
      return 8;
   }
}
