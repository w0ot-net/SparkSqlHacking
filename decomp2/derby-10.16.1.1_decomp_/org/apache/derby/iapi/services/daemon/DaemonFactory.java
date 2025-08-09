package org.apache.derby.iapi.services.daemon;

import org.apache.derby.shared.common.error.StandardException;

public interface DaemonFactory {
   DaemonService createNewDaemon(String var1) throws StandardException;
}
