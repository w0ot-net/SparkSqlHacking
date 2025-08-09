package org.apache.derby.iapi.store.access;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface ScanInfo {
   Properties getAllScanInfo(Properties var1) throws StandardException;
}
