package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotCall;
import java.util.ListIterator;

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
