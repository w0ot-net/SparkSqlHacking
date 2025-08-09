package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotCall;
import java.util.Iterator;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class UnmodifiableIterator implements Iterator {
   protected UnmodifiableIterator() {
   }

   /** @deprecated */
   @Deprecated
   @DoNotCall("Always throws UnsupportedOperationException")
   public final void remove() {
      throw new UnsupportedOperationException();
   }
}
