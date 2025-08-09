package org.apache.curator.shaded.com.google.common.collect;

import java.util.function.BiConsumer;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.LazyInit;
import org.apache.curator.shaded.com.google.j2objc.annotations.RetainedWith;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
final class SingletonImmutableBiMap extends ImmutableBiMap {
   final transient Object singleKey;
   final transient Object singleValue;
   @CheckForNull
   private final transient ImmutableBiMap inverse;
   @LazyInit
   @CheckForNull
   @RetainedWith
   private transient ImmutableBiMap lazyInverse;

   SingletonImmutableBiMap(Object singleKey, Object singleValue) {
      CollectPreconditions.checkEntryNotNull(singleKey, singleValue);
      this.singleKey = singleKey;
      this.singleValue = singleValue;
      this.inverse = null;
   }

   private SingletonImmutableBiMap(Object singleKey, Object singleValue, ImmutableBiMap inverse) {
      this.singleKey = singleKey;
      this.singleValue = singleValue;
      this.inverse = inverse;
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return this.singleKey.equals(key) ? this.singleValue : null;
   }

   public int size() {
      return 1;
   }

   public void forEach(BiConsumer action) {
      ((BiConsumer)Preconditions.checkNotNull(action)).accept(this.singleKey, this.singleValue);
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.singleKey.equals(key);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.singleValue.equals(value);
   }

   boolean isPartialView() {
      return false;
   }

   ImmutableSet createEntrySet() {
      return ImmutableSet.of(Maps.immutableEntry(this.singleKey, this.singleValue));
   }

   ImmutableSet createKeySet() {
      return ImmutableSet.of(this.singleKey);
   }

   public ImmutableBiMap inverse() {
      if (this.inverse != null) {
         return this.inverse;
      } else {
         ImmutableBiMap<V, K> result = this.lazyInverse;
         return result == null ? (this.lazyInverse = new SingletonImmutableBiMap(this.singleValue, this.singleKey, this)) : result;
      }
   }
}
