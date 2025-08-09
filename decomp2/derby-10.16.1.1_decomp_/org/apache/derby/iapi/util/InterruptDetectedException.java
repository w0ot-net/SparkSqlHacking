package org.apache.derby.iapi.util;

import org.apache.derby.shared.common.error.StandardException;

public class InterruptDetectedException extends StandardException {
   public InterruptDetectedException() {
      super("intrp.U");
   }
}
