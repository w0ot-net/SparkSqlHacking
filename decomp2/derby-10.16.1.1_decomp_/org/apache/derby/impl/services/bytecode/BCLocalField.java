package org.apache.derby.impl.services.bytecode;

import org.apache.derby.iapi.services.compiler.LocalField;

class BCLocalField implements LocalField {
   final int cpi;
   final Type type;

   BCLocalField(Type var1, int var2) {
      this.cpi = var2;
      this.type = var1;
   }
}
