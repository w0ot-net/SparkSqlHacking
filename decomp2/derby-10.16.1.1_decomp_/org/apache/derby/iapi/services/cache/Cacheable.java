package org.apache.derby.iapi.services.cache;

import org.apache.derby.shared.common.error.StandardException;

public interface Cacheable {
   Cacheable setIdentity(Object var1) throws StandardException;

   Cacheable createIdentity(Object var1, Object var2) throws StandardException;

   void clearIdentity();

   Object getIdentity();

   boolean isDirty();

   void clean(boolean var1) throws StandardException;
}
