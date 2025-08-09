package org.datanucleus.store.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.SoftValueMap;

public class SoftQueryResultsCache extends AbstractQueryResultsCache {
   private static final long serialVersionUID = -2353653447203789565L;

   public SoftQueryResultsCache(NucleusContext ctx) {
      super(ctx);
      this.cache = new SoftValueMap();
   }
}
