package org.datanucleus.store.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.SoftValueMap;

public class SoftQueryDatastoreCompilationCache extends AbstractQueryDatastoreCompilationCache {
   public SoftQueryDatastoreCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new SoftValueMap();
   }
}
