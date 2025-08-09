package org.apache.arrow.vector.holders;

import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.types.Types;

public class UnionHolder implements ValueHolder {
   public FieldReader reader;
   public int isSet;

   public Types.MinorType getMinorType() {
      return this.reader.getMinorType();
   }

   public boolean isSet() {
      return this.isSet == 1;
   }
}
