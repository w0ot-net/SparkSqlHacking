package org.apache.derby.impl.services.bytecode;

interface BCExpr {
   short vm_void = -1;
   short vm_byte = 0;
   short vm_short = 1;
   short vm_int = 2;
   short vm_long = 3;
   short vm_float = 4;
   short vm_double = 5;
   short vm_char = 6;
   short vm_reference = 7;
}
