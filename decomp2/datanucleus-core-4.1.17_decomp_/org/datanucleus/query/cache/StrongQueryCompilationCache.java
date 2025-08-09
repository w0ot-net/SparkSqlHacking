package org.datanucleus.query.cache;

import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.NucleusContext;

public class StrongQueryCompilationCache extends AbstractQueryCompilationCache implements QueryCompilationCache {
   public StrongQueryCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new ConcurrentHashMap();
   }
}
