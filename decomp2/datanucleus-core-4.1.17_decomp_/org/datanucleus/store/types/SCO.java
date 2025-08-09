package org.datanucleus.store.types;

import org.datanucleus.state.FetchPlanState;

public interface SCO {
   void initialise(Object var1);

   void initialise();

   void initialise(Object var1, Object var2);

   String getFieldName();

   Object getOwner();

   void unsetOwner();

   Object getValue();

   Object clone();

   Object detachCopy(FetchPlanState var1);

   void attachCopy(Object var1);
}
