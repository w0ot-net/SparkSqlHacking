package org.jvnet.hk2.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.TwoPhaseTransactionData;

public class TwoPhaseTransactionDataImpl implements TwoPhaseTransactionData {
   private final List added = new LinkedList();
   private final List removed = new LinkedList();

   public List getAllAddedDescriptors() {
      return Collections.unmodifiableList(new ArrayList(this.added));
   }

   public List getAllRemovedDescriptors() {
      return Collections.unmodifiableList(new ArrayList(this.removed));
   }

   void toAdd(ActiveDescriptor addMe) {
      this.added.add(addMe);
   }

   void toRemove(ActiveDescriptor removeMe) {
      this.removed.add(removeMe);
   }

   public String toString() {
      return "TwoPhaseTransactionalDataImpl(" + System.identityHashCode(this) + ")";
   }
}
