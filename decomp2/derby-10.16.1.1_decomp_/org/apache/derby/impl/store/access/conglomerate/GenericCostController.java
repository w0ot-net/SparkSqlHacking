package org.apache.derby.impl.store.access.conglomerate;

import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.StoreCostController;
import org.apache.derby.shared.common.error.StandardException;

public abstract class GenericCostController extends GenericController implements StoreCostController {
   public double getFetchFromFullKeyCost(FormatableBitSet var1, int var2) throws StandardException {
      throw StandardException.newException("XSCH8.S", new Object[0]);
   }
}
