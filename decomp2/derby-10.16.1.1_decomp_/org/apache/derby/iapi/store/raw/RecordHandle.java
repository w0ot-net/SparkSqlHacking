package org.apache.derby.iapi.store.raw;

import org.apache.derby.iapi.services.locks.Lockable;

public interface RecordHandle extends Lockable {
   int INVALID_RECORD_HANDLE = 0;
   int RESERVED1_RECORD_HANDLE = 1;
   int DEALLOCATE_PROTECTION_HANDLE = 2;
   int PREVIOUS_KEY_HANDLE = 3;
   int RESERVED4_RECORD_HANDLE = 4;
   int RESERVED5_RECORD_HANDLE = 5;
   int FIRST_RECORD_ID = 6;

   int getId();

   long getPageNumber();

   int getSlotNumberHint();

   ContainerKey getContainerId();

   Object getPageId();
}
