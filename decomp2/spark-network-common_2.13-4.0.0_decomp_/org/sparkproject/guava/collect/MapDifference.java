package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
