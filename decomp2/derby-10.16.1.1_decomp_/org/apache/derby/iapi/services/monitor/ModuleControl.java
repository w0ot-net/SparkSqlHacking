package org.apache.derby.iapi.services.monitor;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface ModuleControl {
   void boot(boolean var1, Properties var2) throws StandardException;

   void stop();
}
