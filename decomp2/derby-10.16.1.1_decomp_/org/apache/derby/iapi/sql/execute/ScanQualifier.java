package org.apache.derby.iapi.sql.execute;

import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.types.DataValueDescriptor;

public interface ScanQualifier extends Qualifier {
   void setQualifier(int var1, DataValueDescriptor var2, int var3, boolean var4, boolean var5, boolean var6);
}
