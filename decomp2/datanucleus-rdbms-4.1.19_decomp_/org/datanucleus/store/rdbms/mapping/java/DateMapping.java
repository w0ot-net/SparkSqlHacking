package org.datanucleus.store.rdbms.mapping.java;

import java.util.Date;

public class DateMapping extends TemporalMapping {
   public Class getJavaType() {
      return Date.class;
   }

   protected int getDefaultLengthAsString() {
      return 28;
   }
}
