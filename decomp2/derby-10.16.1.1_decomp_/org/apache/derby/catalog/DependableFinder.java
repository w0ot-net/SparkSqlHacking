package org.apache.derby.catalog;

import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.shared.common.error.StandardException;

public interface DependableFinder {
   Dependable getDependable(DataDictionary var1, UUID var2) throws StandardException;

   String getSQLObjectType();
}
