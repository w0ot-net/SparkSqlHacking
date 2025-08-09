package org.datanucleus.store;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.store.query.Query;
import org.datanucleus.store.query.QueryResult;

public class DefaultCandidateExtent extends AbstractExtent {
   private FetchPlan fetchPlan = null;
   private Query query;
   protected Map queryResultsByIterator = new HashMap();

   public DefaultCandidateExtent(ExecutionContext ec, Class cls, boolean subclasses, AbstractClassMetaData cmd) {
      super(ec, cls, subclasses, cmd);
      this.query = ec.getStoreManager().getQueryManager().newQuery("JDOQL", ec, (Object)null);
      this.fetchPlan = this.query.getFetchPlan();
      this.query.setCandidateClass(cls);
      this.query.setSubclasses(subclasses);
   }

   public Iterator iterator() {
      Object results = this.query.execute();
      Iterator iter = null;
      if (results instanceof QueryResult) {
         QueryResult qr = (QueryResult)results;
         iter = qr.iterator();
         this.queryResultsByIterator.put(iter, qr);
      } else {
         iter = ((Collection)results).iterator();
      }

      return iter;
   }

   public boolean hasSubclasses() {
      return this.subclasses;
   }

   public ExecutionContext getExecutionContext() {
      return this.ec;
   }

   public FetchPlan getFetchPlan() {
      return this.fetchPlan;
   }

   public void closeAll() {
      this.queryResultsByIterator.clear();
      this.query.closeAll();
      this.fetchPlan.clearGroups().addGroup("default");
   }

   public void close(Iterator iterator) {
      QueryResult qr = (QueryResult)this.queryResultsByIterator.remove(iterator);
      if (qr != null) {
         this.query.close(qr);
      }

   }
}
