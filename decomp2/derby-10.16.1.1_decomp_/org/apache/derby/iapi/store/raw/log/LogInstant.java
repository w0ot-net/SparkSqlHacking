package org.apache.derby.iapi.store.raw.log;

import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.store.access.DatabaseInstant;

public interface LogInstant extends Formatable, DatabaseInstant {
   long INVALID_LOG_INSTANT = 0L;
}
