package org.apache.derby.iapi.store.access;

import org.apache.derby.iapi.services.io.Storable;
import org.apache.derby.iapi.types.DataValueDescriptor;

public interface StaticCompiledOpenConglomInfo extends Storable {
   DataValueDescriptor getConglom();
}
