package org.datanucleus.store.rdbms.mapping.java;

import java.math.BigDecimal;

public class BigDecimalMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return BigDecimal.class;
   }
}
