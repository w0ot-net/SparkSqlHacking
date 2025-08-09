package org.datanucleus.store.query.cache;

import org.datanucleus.NucleusContext;
import org.datanucleus.util.WeakValueMap;

public class WeakQueryResultsCache extends AbstractQueryResultsCache {
   private static final long serialVersionUID = 5007468676679033002L;

   public WeakQueryResultsCache(NucleusContext ctx) {
      super(ctx);
      this.cache = new WeakValueMap();
   }
}
