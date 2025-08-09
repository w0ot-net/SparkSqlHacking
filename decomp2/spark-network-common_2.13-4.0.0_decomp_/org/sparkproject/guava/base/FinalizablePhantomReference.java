package org.sparkproject.guava.base;

import java.lang.ref.PhantomReference;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public abstract class FinalizablePhantomReference extends PhantomReference implements FinalizableReference {
   protected FinalizablePhantomReference(@CheckForNull Object referent, FinalizableReferenceQueue queue) {
      super(referent, queue.queue);
      queue.cleanUp();
   }
}
