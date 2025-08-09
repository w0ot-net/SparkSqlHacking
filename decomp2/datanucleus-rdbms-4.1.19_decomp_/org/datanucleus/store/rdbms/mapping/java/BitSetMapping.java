package org.datanucleus.store.rdbms.mapping.java;

import java.util.BitSet;
import org.datanucleus.ClassNameConstants;

public class BitSetMapping extends SingleFieldMapping {
   public Class getJavaType() {
      return BitSet.class;
   }

   public String getJavaTypeForDatastoreMapping(int index) {
      return ClassNameConstants.JAVA_IO_SERIALIZABLE;
   }
}
