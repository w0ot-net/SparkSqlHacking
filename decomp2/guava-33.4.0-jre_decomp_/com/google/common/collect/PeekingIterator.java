package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Iterator;

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
