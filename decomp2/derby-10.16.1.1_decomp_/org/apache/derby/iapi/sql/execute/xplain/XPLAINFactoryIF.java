package org.apache.derby.iapi.sql.execute.xplain;

import org.apache.derby.shared.common.error.StandardException;

public interface XPLAINFactoryIF {
   String MODULE = "org.apache.derby.iapi.sql.execute.xplain.XPLAINFactoryIF";

   XPLAINVisitor getXPLAINVisitor() throws StandardException;

   void freeResources();
}
