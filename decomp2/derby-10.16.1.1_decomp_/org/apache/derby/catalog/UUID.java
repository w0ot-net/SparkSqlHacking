package org.apache.derby.catalog;

import java.io.Externalizable;

public interface UUID extends Externalizable {
   String NULL = "NULL";
   int UUID_BYTE_LENGTH = 16;

   String toANSIidentifier();

   UUID cloneMe();
}
