package org.datanucleus.store.query.cache;

import java.util.HashMap;
import org.datanucleus.NucleusContext;

public class StrongQueryResultsCache extends AbstractQueryResultsCache {
   private static final long serialVersionUID = -8309204044669474063L;

   public StrongQueryResultsCache(NucleusContext ctx) {
      super(ctx);
      this.cache = new HashMap();
   }
}
