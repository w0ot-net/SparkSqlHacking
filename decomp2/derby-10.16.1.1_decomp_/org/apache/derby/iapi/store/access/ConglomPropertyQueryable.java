package org.apache.derby.iapi.store.access;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface ConglomPropertyQueryable {
   void getTableProperties(Properties var1) throws StandardException;

   Properties getInternalTablePropertySet(Properties var1) throws StandardException;
}
