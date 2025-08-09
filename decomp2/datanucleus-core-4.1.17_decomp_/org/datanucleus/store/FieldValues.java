package org.datanucleus.store;

import org.datanucleus.FetchPlan;
import org.datanucleus.state.ObjectProvider;

public interface FieldValues {
   void fetchFields(ObjectProvider var1);

   void fetchNonLoadedFields(ObjectProvider var1);

   FetchPlan getFetchPlanForLoading();
}
