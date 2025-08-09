package org.datanucleus.store.query;

import java.util.Collection;
import java.util.Map;
import org.datanucleus.ExecutionContext;
import org.datanucleus.store.Extent;
import org.datanucleus.store.StoreManager;
import org.datanucleus.util.StringUtils;

public abstract class AbstractJavaQuery extends Query {
   private static final long serialVersionUID = 7429197167814283812L;
   protected transient Collection candidateCollection = null;
   protected String singleString = null;

   public AbstractJavaQuery(StoreManager storeMgr, ExecutionContext ec) {
      super(storeMgr, ec);
   }

   public void setCandidates(Extent pcs) {
      this.discardCompiled();
      this.assertIsModifiable();
      if (pcs != null) {
         this.setCandidateClass(pcs.getCandidateClass());
         this.setSubclasses(pcs.hasSubclasses());
      }

      this.candidateCollection = null;
   }

   public void setCandidates(Collection pcs) {
      this.discardCompiled();
      this.assertIsModifiable();
      this.candidateCollection = pcs;
   }

   protected void discardCompiled() {
      super.discardCompiled();
      this.singleString = null;
   }

   public abstract void compileGeneric(Map var1);

   protected long performDeletePersistentAll(Map parameters) {
      return this.candidateCollection != null && this.candidateCollection.isEmpty() ? 0L : super.performDeletePersistentAll(parameters);
   }

   public abstract String getSingleStringQuery();

   public String toString() {
      return this.getSingleStringQuery();
   }

   protected boolean evaluateInMemory() {
      return this.getBooleanExtensionProperty("datanucleus.query.evaluateInMemory", false);
   }

   protected String dereferenceFilter(String input) {
      if (this.subqueries == null) {
         return input;
      } else {
         String output = input;

         for(Map.Entry entry : this.subqueries.entrySet()) {
            Query.SubqueryDefinition subqueryDefinition = (Query.SubqueryDefinition)entry.getValue();
            AbstractJavaQuery subquery = (AbstractJavaQuery)subqueryDefinition.getQuery();
            output = StringUtils.replaceAll(output, (String)entry.getKey(), "(" + subquery.getSingleStringQuery() + ")");
         }

         return output;
      }
   }
}
