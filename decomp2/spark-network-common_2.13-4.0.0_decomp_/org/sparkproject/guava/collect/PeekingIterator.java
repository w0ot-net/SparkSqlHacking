package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Iterator;
import org.sparkproject.guava.annotations.GwtCompatible;

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
