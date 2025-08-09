package org.apache.arrow.vector.complex.impl;

import org.apache.arrow.vector.complex.MapVector;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.types.Types;

public class UnionMapReader extends UnionListReader {
   private String keyName = "key";
   private String valueName = "value";

   public UnionMapReader(MapVector vector) {
      super(vector);
   }

   public void setKeyValueNames(String key, String value) {
      this.keyName = key;
      this.valueName = value;
   }

   public FieldReader key() {
      return this.reader().reader(this.keyName);
   }

   public FieldReader value() {
      return this.reader().reader(this.valueName);
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.MAP;
   }
}
