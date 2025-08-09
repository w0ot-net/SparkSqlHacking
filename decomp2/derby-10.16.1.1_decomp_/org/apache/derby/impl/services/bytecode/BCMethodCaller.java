package org.apache.derby.impl.services.bytecode;

class BCMethodCaller extends BCLocalField {
   final short opcode;

   BCMethodCaller(short var1, Type var2, int var3) {
      super(var2, var3);
      this.opcode = var1;
   }
}
