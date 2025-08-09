package org.apache.derby.iapi.sql.compile;

import org.apache.derby.shared.common.error.StandardException;

public interface Visitable {
   Visitable accept(Visitor var1) throws StandardException;

   void addTag(String var1);

   boolean taggedWith(String var1);
}
