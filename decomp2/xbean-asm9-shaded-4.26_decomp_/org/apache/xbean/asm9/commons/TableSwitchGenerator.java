package org.apache.xbean.asm9.commons;

import org.apache.xbean.asm9.Label;

public interface TableSwitchGenerator {
   void generateCase(int var1, Label var2);

   void generateDefault();
}
