package org.bouncycastle.math.ec;

public abstract class AbstractECLookupTable implements ECLookupTable {
   public ECPoint lookupVar(int var1) {
      return this.lookup(var1);
   }
}
