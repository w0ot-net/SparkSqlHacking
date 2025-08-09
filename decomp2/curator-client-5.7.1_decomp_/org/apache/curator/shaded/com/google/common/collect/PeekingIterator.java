package org.apache.curator.shaded.com.google.common.collect;

import java.util.Iterator;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use Iterators.peekingIterator")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface PeekingIterator extends Iterator {
   @ParametricNullness
   Object peek();

   @ParametricNullness
   @CanIgnoreReturnValue
   Object next();

   void remove();
}
