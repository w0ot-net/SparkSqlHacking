package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassNameConstants;

public class NumberMapping extends SingleFieldMapping {
   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_MATH_BIGDECIMAL;
   }

   public Class getJavaType() {
      return Number.class;
   }
}
