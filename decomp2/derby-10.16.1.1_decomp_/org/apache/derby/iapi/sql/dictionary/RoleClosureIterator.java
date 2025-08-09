package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.shared.common.error.StandardException;

public interface RoleClosureIterator {
   String next() throws StandardException;
}
