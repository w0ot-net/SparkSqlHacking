package org.apache.curator.shaded.com.google.common.collect;

import java.util.Iterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotCall;

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
