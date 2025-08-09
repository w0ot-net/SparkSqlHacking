package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPFloat extends CPConstantNumber {
   public CPFloat(Float value, int globalIndex) {
      super((byte)4, value, globalIndex);
   }

   public String toString() {
      return "Float: " + this.getValue();
   }

   protected void writeBody(DataOutputStream dos) throws IOException {
      dos.writeFloat(this.getNumber().floatValue());
   }
}
