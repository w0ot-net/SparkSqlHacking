package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

class SubqueryList extends QueryTreeNodeVector {
   SubqueryList(ContextManager var1) {
      super(SubqueryNode.class, var1);
   }

   void addSubqueryNode(SubqueryNode var1) throws StandardException {
      this.addElement(var1);
   }

   void optimize(DataDictionary var1, double var2) throws StandardException {
      for(SubqueryNode var5 : this) {
         var5.optimize(var1, var2);
      }

   }

   void modifyAccessPaths() throws StandardException {
      for(SubqueryNode var2 : this) {
         var2.modifyAccessPaths();
      }

   }

   boolean referencesTarget(String var1, boolean var2) throws StandardException {
      for(SubqueryNode var4 : this) {
         if (!var4.isMaterializable() && var4.getResultSet().referencesTarget(var1, var2)) {
            return true;
         }
      }

      return false;
   }

   public boolean referencesSessionSchema() throws StandardException {
      for(SubqueryNode var2 : this) {
         if (var2.referencesSessionSchema()) {
            return true;
         }
      }

      return false;
   }

   void setPointOfAttachment(int var1) throws StandardException {
      for(SubqueryNode var3 : this) {
         var3.setPointOfAttachment(var1);
      }

   }

   void decrementLevel(int var1) {
      for(SubqueryNode var3 : this) {
         var3.getResultSet().decrementLevel(var1);
      }

   }

   void markHavingSubqueries() {
      for(SubqueryNode var2 : this) {
         var2.setHavingSubquery(true);
      }

   }

   void markWhereSubqueries() {
      for(SubqueryNode var2 : this) {
         var2.setWhereSubquery(true);
      }

   }
}
