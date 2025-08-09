package org.apache.curator.shaded.com.google.common.collect;

import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;

@ElementTypesAreNonnullByDefault
@GwtIncompatible
class ImmutableMapEntry extends ImmutableEntry {
   static ImmutableMapEntry[] createEntryArray(int size) {
      return new ImmutableMapEntry[size];
   }

   ImmutableMapEntry(Object key, Object value) {
      super(key, value);
      CollectPreconditions.checkEntryNotNull(key, value);
   }

   ImmutableMapEntry(ImmutableMapEntry contents) {
      super(contents.getKey(), contents.getValue());
   }

   @CheckForNull
   ImmutableMapEntry getNextInKeyBucket() {
      return null;
   }

   @CheckForNull
   ImmutableMapEntry getNextInValueBucket() {
      return null;
   }

   boolean isReusable() {
      return true;
   }

   static class NonTerminalImmutableMapEntry extends ImmutableMapEntry {
      @CheckForNull
      private final transient ImmutableMapEntry nextInKeyBucket;

      NonTerminalImmutableMapEntry(Object key, Object value, @CheckForNull ImmutableMapEntry nextInKeyBucket) {
         super(key, value);
         this.nextInKeyBucket = nextInKeyBucket;
      }

      @CheckForNull
      final ImmutableMapEntry getNextInKeyBucket() {
         return this.nextInKeyBucket;
      }

      final boolean isReusable() {
         return false;
      }
   }

   static final class NonTerminalImmutableBiMapEntry extends NonTerminalImmutableMapEntry {
      @CheckForNull
      private final transient ImmutableMapEntry nextInValueBucket;

      NonTerminalImmutableBiMapEntry(Object key, Object value, @CheckForNull ImmutableMapEntry nextInKeyBucket, @CheckForNull ImmutableMapEntry nextInValueBucket) {
         super(key, value, nextInKeyBucket);
         this.nextInValueBucket = nextInValueBucket;
      }

      @CheckForNull
      ImmutableMapEntry getNextInValueBucket() {
         return this.nextInValueBucket;
      }
   }
}
