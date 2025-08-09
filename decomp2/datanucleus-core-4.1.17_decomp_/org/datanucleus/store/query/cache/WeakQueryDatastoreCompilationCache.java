package org.datanucleus.store.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.WeakValueMap;

public class WeakQueryDatastoreCompilationCache extends AbstractQueryDatastoreCompilationCache {
   public WeakQueryDatastoreCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new WeakValueMap();
   }
}
