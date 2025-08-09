package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassNameConstants;

public class SerialisedMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return Object.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_IO_SERIALIZABLE;
   }
}
