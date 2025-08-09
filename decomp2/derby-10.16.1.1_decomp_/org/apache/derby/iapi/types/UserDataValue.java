package org.apache.derby.iapi.types;

import org.apache.derby.shared.common.error.StandardException;

public interface UserDataValue extends DataValueDescriptor {
   void setValue(Object var1) throws StandardException;
}
