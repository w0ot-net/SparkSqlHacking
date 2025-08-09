package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface Parser {
   Visitable parseStatement(String var1, Object[] var2) throws StandardException;

   Visitable parseStatement(String var1) throws StandardException;

   Visitable parseSearchCondition(String var1) throws StandardException;

   String getSQLtext();
}
