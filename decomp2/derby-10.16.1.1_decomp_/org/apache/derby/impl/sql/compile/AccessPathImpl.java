package org.apache.derby.impl.sql.compile;

import org.apache.derby.iapi.sql.compile.AccessPath;
import org.apache.derby.iapi.sql.compile.CostEstimate;
import org.apache.derby.iapi.sql.compile.JoinStrategy;
import org.apache.derby.iapi.sql.compile.Optimizer;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class AccessPathImpl implements AccessPath {
   ConglomerateDescriptor cd = null;
   private CostEstimate costEstimate = null;
   boolean coveringIndexScan = false;
   boolean nonMatchingIndexScan = false;
   JoinStrategy joinStrategy = null;
   int lockMode;
   Optimizer optimizer;
   private String accessPathName = "";

   AccessPathImpl(Optimizer var1) {
      this.optimizer = var1;
   }

   public void setConglomerateDescriptor(ConglomerateDescriptor var1) {
      this.cd = var1;
   }

   public ConglomerateDescriptor getConglomerateDescriptor() {
      return this.cd;
   }

   public void setCostEstimate(CostEstimate var1) {
      if (this.costEstimate == null) {
         if (var1 != null) {
            this.costEstimate = var1.cloneMe();
         }
      } else if (var1 == null) {
         this.costEstimate = null;
      } else {
         this.costEstimate.setCost(var1);
      }

   }

   public CostEstimate getCostEstimate() {
      return this.costEstimate;
   }

   public void setCoveringIndexScan(boolean var1) {
      this.coveringIndexScan = var1;
   }

   public boolean getCoveringIndexScan() {
      return this.coveringIndexScan;
   }

   public void setNonMatchingIndexScan(boolean var1) {
      this.nonMatchingIndexScan = var1;
   }

   public boolean getNonMatchingIndexScan() {
      return this.nonMatchingIndexScan;
   }

   public void setJoinStrategy(JoinStrategy var1) {
      this.joinStrategy = var1;
   }

   public JoinStrategy getJoinStrategy() {
      return this.joinStrategy;
   }

   public void setLockMode(int var1) {
      this.lockMode = var1;
   }

   public int getLockMode() {
      return this.lockMode;
   }

   public void copy(AccessPath var1) {
      this.setConglomerateDescriptor(var1.getConglomerateDescriptor());
      this.setCostEstimate(var1.getCostEstimate());
      this.setCoveringIndexScan(var1.getCoveringIndexScan());
      this.setNonMatchingIndexScan(var1.getNonMatchingIndexScan());
      this.setJoinStrategy(var1.getJoinStrategy());
      this.setLockMode(var1.getLockMode());
   }

   public Optimizer getOptimizer() {
      return this.optimizer;
   }

   public String toString() {
      return "";
   }

   public void initializeAccessPathName(DataDictionary var1, TableDescriptor var2) throws StandardException {
      if (this.cd != null) {
         if (this.cd.isConstraint()) {
            ConstraintDescriptor var3 = var1.getConstraintDescriptor(var2, this.cd.getUUID());
            if (var3 == null) {
               throw StandardException.newException("42X94", new Object[]{"CONSTRAINT on TABLE", var2.getName()});
            }

            this.accessPathName = var3.getConstraintName();
         } else if (this.cd.isIndex()) {
            this.accessPathName = this.cd.getConglomerateName();
         } else {
            this.accessPathName = "";
         }

      }
   }
}
