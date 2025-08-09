package org.apache.curator.shaded.com.google.common.collect;

import java.util.concurrent.ConcurrentMap;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingConcurrentMap extends ForwardingMap implements ConcurrentMap {
   protected ForwardingConcurrentMap() {
   }

   protected abstract ConcurrentMap delegate();

   @CheckForNull
   @CanIgnoreReturnValue
   public Object putIfAbsent(Object key, Object value) {
      return this.delegate().putIfAbsent(key, value);
   }

   @CanIgnoreReturnValue
   public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      return this.delegate().remove(key, value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object replace(Object key, Object value) {
      return this.delegate().replace(key, value);
   }

   @CanIgnoreReturnValue
   public boolean replace(Object key, Object oldValue, Object newValue) {
      return this.delegate().replace(key, oldValue, newValue);
   }
}
