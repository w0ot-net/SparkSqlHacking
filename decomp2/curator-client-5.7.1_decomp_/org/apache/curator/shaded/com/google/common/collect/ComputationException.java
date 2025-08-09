package org.apache.curator.shaded.com.google.common.collect;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;

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
