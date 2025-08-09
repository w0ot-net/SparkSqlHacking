package org.apache.derby.iapi.store.raw;

import java.util.Properties;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface StreamContainerHandle {
   int TEMPORARY_SEGMENT = -1;

   ContainerKey getId();

   void getContainerProperties(Properties var1) throws StandardException;

   boolean fetchNext(DataValueDescriptor[] var1) throws StandardException;

   void close();

   void removeContainer() throws StandardException;
}
