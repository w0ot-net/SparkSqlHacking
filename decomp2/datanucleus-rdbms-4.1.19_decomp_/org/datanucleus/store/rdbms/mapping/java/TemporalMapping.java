package org.datanucleus.store.rdbms.mapping.java;

import org.datanucleus.ClassNameConstants;

public abstract class TemporalMapping extends SingleFieldMapping {
   public int getDefaultLength(int index) {
      return this.datastoreMappings != null && this.datastoreMappings.length > 0 && this.datastoreMappings[0].isStringBased() ? this.getDefaultLengthAsString() : super.getDefaultLength(index);
   }

   protected abstract int getDefaultLengthAsString();

   public String getJavaTypeForDatastoreMapping(int index) {
      return this.datastoreMappings != null && this.datastoreMappings.length > 0 && this.datastoreMappings[0].isStringBased() ? ClassNameConstants.JAVA_LANG_STRING : super.getJavaTypeForDatastoreMapping(index);
   }
}
