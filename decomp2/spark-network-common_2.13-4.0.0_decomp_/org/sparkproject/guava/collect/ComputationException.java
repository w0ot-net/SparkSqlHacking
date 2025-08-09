package org.sparkproject.guava.collect;

import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

/** @deprecated */
@Deprecated
@ElementTypesAreNonnullByDefault
@GwtCompatible
public class ComputationException extends RuntimeException {
   private static final long serialVersionUID = 0L;

   public ComputationException(@CheckForNull Throwable cause) {
      super(cause);
   }
}
