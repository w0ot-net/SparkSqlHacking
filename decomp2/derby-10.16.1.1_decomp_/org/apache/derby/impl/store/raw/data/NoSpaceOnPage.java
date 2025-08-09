package org.apache.derby.impl.store.raw.data;

import org.apache.derby.shared.common.error.StandardException;

class NoSpaceOnPage extends StandardException {
   private final boolean onOverflowPage;

   protected NoSpaceOnPage(boolean var1) {
      super("nospc.U");
      this.onOverflowPage = var1;
   }

   protected boolean onOverflowPage() {
      return this.onOverflowPage;
   }
}
