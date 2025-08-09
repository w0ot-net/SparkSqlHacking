package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface Visitor {
   Visitable visit(Visitable var1) throws StandardException;

   boolean visitChildrenFirst(Visitable var1);

   boolean stopTraversal();

   boolean skipChildren(Visitable var1) throws StandardException;
}
