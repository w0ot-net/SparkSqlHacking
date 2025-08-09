package org.apache.derby.iapi.services.locks;

public interface C_LockFactory {
   int WAIT_FOREVER = -1;
   int TIMED_WAIT = -2;
   int NO_WAIT = 0;
}
