package org.datanucleus.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.WeakValueMap;

public class WeakQueryCompilationCache extends AbstractQueryCompilationCache implements QueryCompilationCache {
   public WeakQueryCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new WeakValueMap();
   }
}
