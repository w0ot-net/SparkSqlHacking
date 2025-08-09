package org.apache.derby.impl.services.cache;

import org.apache.derby.shared.common.error.StandardException;

interface ReplacementPolicy {
   void insertEntry(CacheEntry var1) throws StandardException;

   void doShrink();

   int size();

   public interface Callback {
      void access();

      void free();
   }
}
