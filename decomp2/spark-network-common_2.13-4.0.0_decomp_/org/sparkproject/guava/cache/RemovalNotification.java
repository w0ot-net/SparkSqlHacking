package org.sparkproject.guava.cache;

import java.util.AbstractMap;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

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
