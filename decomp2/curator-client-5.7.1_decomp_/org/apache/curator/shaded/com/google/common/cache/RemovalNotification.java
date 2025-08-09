package org.apache.curator.shaded.com.google.common.cache;

import java.util.AbstractMap;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public final class RemovalNotification extends AbstractMap.SimpleImmutableEntry {
   private final RemovalCause cause;
   private static final long serialVersionUID = 0L;

   public static RemovalNotification create(@CheckForNull Object key, @CheckForNull Object value, RemovalCause cause) {
      return new RemovalNotification(key, value, cause);
   }

   private RemovalNotification(@CheckForNull Object key, @CheckForNull Object value, RemovalCause cause) {
      super(key, value);
      this.cause = (RemovalCause)Preconditions.checkNotNull(cause);
   }

   public RemovalCause getCause() {
      return this.cause;
   }

   public boolean wasEvicted() {
      return this.cause.wasEvicted();
   }
}
