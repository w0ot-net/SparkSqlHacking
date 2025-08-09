package org.datanucleus.api.jdo;

import java.util.Iterator;
import javax.jdo.Extent;
import javax.jdo.FetchPlan;
import javax.jdo.PersistenceManager;

public class JDOExtent implements Extent {
   PersistenceManager pm;
   org.datanucleus.store.Extent extent;
   JDOFetchPlan fetchPlan = null;

   public JDOExtent(PersistenceManager pm, org.datanucleus.store.Extent extent) {
      this.pm = pm;
      this.extent = extent;
      this.fetchPlan = new JDOFetchPlan(extent.getFetchPlan());
   }

   public void close(Iterator iterator) {
      this.extent.close(iterator);
   }

   public void closeAll() {
      this.extent.closeAll();
   }

   public Class getCandidateClass() {
      return this.extent.getCandidateClass();
   }

   public boolean hasSubclasses() {
      return this.extent.hasSubclasses();
   }

   public FetchPlan getFetchPlan() {
      return this.fetchPlan;
   }

   public PersistenceManager getPersistenceManager() {
      return this.pm;
   }

   public org.datanucleus.store.Extent getExtent() {
      return this.extent;
   }

   public Iterator iterator() {
      return this.extent.iterator();
   }
}
