package org.apache.curator.shaded.com.google.common.collect;

import java.util.ListIterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class UnmodifiableListIterator extends UnmodifiableIterator implements ListIterator {
   protected UnmodifiableListIterator() {
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void add(@ParametricNullness Object e) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void set(@ParametricNullness Object e) {
      throw new UnsupportedOperationException();
   }
}
