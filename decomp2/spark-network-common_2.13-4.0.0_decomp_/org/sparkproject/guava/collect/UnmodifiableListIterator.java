package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotCall;
import java.util.ListIterator;
import org.sparkproject.guava.annotations.GwtCompatible;

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
