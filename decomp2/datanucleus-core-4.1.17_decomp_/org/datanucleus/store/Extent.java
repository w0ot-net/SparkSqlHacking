package org.datanucleus.store;

import java.util.Iterator;
import org.datanucleus.ExecutionContext;
import org.datanucleus.FetchPlan;

public interface Extent {
   Class getCandidateClass();

   boolean hasSubclasses();

   ExecutionContext getExecutionContext();

   FetchPlan getFetchPlan();

   Iterator iterator();

   void closeAll();

   void close(Iterator var1);
}
