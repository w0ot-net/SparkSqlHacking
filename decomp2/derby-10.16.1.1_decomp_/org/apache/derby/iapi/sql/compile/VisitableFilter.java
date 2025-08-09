package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface VisitableFilter {
   boolean accept(Visitable var1) throws StandardException;
}
