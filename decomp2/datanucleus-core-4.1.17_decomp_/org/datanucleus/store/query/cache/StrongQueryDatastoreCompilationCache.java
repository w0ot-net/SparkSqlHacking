package org.datanucleus.store.query.cache;

import java.util.HashMap;
import org.datanucleus.NucleusContext;

public class StrongQueryDatastoreCompilationCache extends AbstractQueryDatastoreCompilationCache {
   public StrongQueryDatastoreCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new HashMap();
   }
}
