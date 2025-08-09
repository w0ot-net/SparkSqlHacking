package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use Maps.difference")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface MapDifference {
   boolean areEqual();

   Map entriesOnlyOnLeft();

   Map entriesOnlyOnRight();

   Map entriesInCommon();

   Map entriesDiffering();

   boolean equals(@CheckForNull Object object);

   int hashCode();

   @DoNotMock("Use Maps.difference")
   public interface ValueDifference {
      @ParametricNullness
      Object leftValue();

      @ParametricNullness
      Object rightValue();

      boolean equals(@CheckForNull Object other);

      int hashCode();
   }
}
