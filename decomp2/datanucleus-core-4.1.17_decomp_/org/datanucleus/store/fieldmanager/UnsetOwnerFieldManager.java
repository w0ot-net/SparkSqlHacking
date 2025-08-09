package org.datanucleus.store.fieldmanager;

import org.datanucleus.store.types.SCO;

public class UnsetOwnerFieldManager extends AbstractFieldManager {
   public void storeObjectField(int fieldNumber, Object value) {
      if (value instanceof SCO) {
         ((SCO)value).unsetOwner();
      }

   }
}
