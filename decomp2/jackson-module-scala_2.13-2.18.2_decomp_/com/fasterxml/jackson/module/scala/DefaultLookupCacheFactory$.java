package com.fasterxml.jackson.module.scala;

import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;

public final class DefaultLookupCacheFactory$ implements LookupCacheFactory {
   public static final DefaultLookupCacheFactory$ MODULE$ = new DefaultLookupCacheFactory$();

   public LookupCache createLookupCache(final int initialEntries, final int maxEntries) {
      return new LRUMap(initialEntries, maxEntries);
   }

   private DefaultLookupCacheFactory$() {
   }
}
