package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface ASTVisitor extends Visitor {
   int AFTER_PARSE = 0;
   int AFTER_BIND = 1;
   int AFTER_OPTIMIZE = 2;

   void initializeVisitor() throws StandardException;

   void teardownVisitor() throws StandardException;

   void begin(String var1, int var2) throws StandardException;

   void end(int var1) throws StandardException;
}
