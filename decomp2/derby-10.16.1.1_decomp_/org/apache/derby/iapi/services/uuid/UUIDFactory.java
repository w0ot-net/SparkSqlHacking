package org.apache.derby.iapi.services.uuid;

import org.apache.derby.catalog.UUID;

public interface UUIDFactory {
   UUID createUUID();

   UUID recreateUUID(String var1);
}
