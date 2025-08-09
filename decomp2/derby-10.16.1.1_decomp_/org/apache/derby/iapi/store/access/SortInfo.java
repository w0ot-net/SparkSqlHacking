package org.apache.derby.iapi.store.access;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface SortInfo {
   Properties getAllSortInfo(Properties var1) throws StandardException;
}
