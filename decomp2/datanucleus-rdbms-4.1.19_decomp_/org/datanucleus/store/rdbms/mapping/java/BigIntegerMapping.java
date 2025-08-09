package org.datanucleus.store.rdbms.mapping.java;

import java.math.BigInteger;

public class BigIntegerMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return BigInteger.class;
   }
}
