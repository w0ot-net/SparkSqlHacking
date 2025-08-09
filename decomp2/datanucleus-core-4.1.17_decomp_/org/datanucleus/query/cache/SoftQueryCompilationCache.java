package org.datanucleus.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.SoftValueMap;

public class SoftQueryCompilationCache extends AbstractQueryCompilationCache implements QueryCompilationCache {
   public SoftQueryCompilationCache(NucleusContext nucleusCtx) {
      this.cache = new SoftValueMap();
   }
}
