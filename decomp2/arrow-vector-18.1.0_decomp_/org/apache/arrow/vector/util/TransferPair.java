package org.apache.arrow.vector.util;

import org.apache.arrow.vector.ValueVector;

public interface TransferPair {
   void transfer();

   void splitAndTransfer(int var1, int var2);

   ValueVector getTo();

   void copyValueSafe(int var1, int var2);
}
