package com.thoughtworks.paranamer;

import java.lang.reflect.AccessibleObject;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class CachingParanamer implements Paranamer {
   public static final String __PARANAMER_DATA = "v1.0 \ncom.thoughtworks.paranamer.CachingParanamer <init> com.thoughtworks.paranamer.Paranamer delegate \ncom.thoughtworks.paranamer.CachingParanamer lookupParameterNames java.lang.AccessibleObject methodOrConstructor \ncom.thoughtworks.paranamer.CachingParanamer lookupParameterNames java.lang.AccessibleObject, boolean methodOrCtor,throwExceptionIfMissing \n";
   private final Paranamer delegate;
   private final Map methodCache;

   protected Map makeMethodCache() {
      return Collections.synchronizedMap(new WeakHashMap());
   }

   public CachingParanamer() {
      this(new DefaultParanamer());
   }

   public CachingParanamer(Paranamer delegate) {
      this.methodCache = this.makeMethodCache();
      this.delegate = delegate;
   }

   public String[] lookupParameterNames(AccessibleObject methodOrConstructor) {
      return this.lookupParameterNames(methodOrConstructor, true);
   }

   public String[] lookupParameterNames(AccessibleObject methodOrCtor, boolean throwExceptionIfMissing) {
      String[] names = (String[])this.methodCache.get(methodOrCtor);
      if (names == null) {
         names = this.delegate.lookupParameterNames(methodOrCtor, throwExceptionIfMissing);
         this.methodCache.put(methodOrCtor, names);
      }

      return names;
   }

   public static class WithoutWeakReferences extends CachingParanamer {
      public static final String __PARANAMER_DATA = "<init> com.thoughtworks.paranamer.Paranamer delegate \n";

      public WithoutWeakReferences() {
      }

      public WithoutWeakReferences(Paranamer delegate) {
         super(delegate);
      }

      protected Map makeMethodCache() {
         return new ConcurrentHashMap();
      }
   }
}
