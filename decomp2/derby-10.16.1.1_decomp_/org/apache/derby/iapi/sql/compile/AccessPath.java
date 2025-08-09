package org.apache.derby.iapi.sql.compile;

import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface AccessPath {
   void setConglomerateDescriptor(ConglomerateDescriptor var1);

   ConglomerateDescriptor getConglomerateDescriptor();

   void setCostEstimate(CostEstimate var1);

   CostEstimate getCostEstimate();

   void setCoveringIndexScan(boolean var1);

   boolean getCoveringIndexScan();

   void setNonMatchingIndexScan(boolean var1);

   boolean getNonMatchingIndexScan();

   void setJoinStrategy(JoinStrategy var1);

   JoinStrategy getJoinStrategy();

   void setLockMode(int var1);

   int getLockMode();

   void copy(AccessPath var1);

   Optimizer getOptimizer();

   void initializeAccessPathName(DataDictionary var1, TableDescriptor var2) throws StandardException;
}
