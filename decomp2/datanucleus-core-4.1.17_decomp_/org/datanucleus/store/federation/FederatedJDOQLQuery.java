package org.datanucleus.store.federation;

import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.StoreManager;
import org.datanucleus.store.query.AbstractJDOQLQuery;

public class FederatedJDOQLQuery extends AbstractJDOQLQuery {
   private static final long serialVersionUID = 740380628222349781L;

   public FederatedJDOQLQuery(StoreManager storeMgr, ExecutionContext ec) {
      super(storeMgr, ec);
   }

   public FederatedJDOQLQuery(StoreManager storeMgr, ExecutionContext ec, FederatedJDOQLQuery q) {
      super(storeMgr, ec, (AbstractJDOQLQuery)q);
   }

   public FederatedJDOQLQuery(StoreManager storeMgr, ExecutionContext ec, String query) {
      super(storeMgr, ec, query);
   }

   protected Object performExecute(Map parameters) {
      return null;
   }
}
