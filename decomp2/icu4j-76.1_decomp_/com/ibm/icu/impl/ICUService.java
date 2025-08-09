package com.ibm.icu.impl;

import com.ibm.icu.util.ULocale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class ICUService extends ICUNotifier {
   protected final String name;
   private static final boolean DEBUG = ICUDebug.enabled("service");
   private final ICURWLock factoryLock = new ICURWLock();
   private final List factories = new ArrayList();
   private int defaultSize = 0;
   private Map cache;
   private Map idcache;
   private LocaleRef dnref;

   public ICUService() {
      this.name = "";
   }

   public ICUService(String name) {
      this.name = name;
   }

   public Object get(String descriptor) {
      return this.getKey(this.createKey(descriptor), (String[])null);
   }

   public Object get(String descriptor, String[] actualReturn) {
      if (descriptor == null) {
         throw new NullPointerException("descriptor must not be null");
      } else {
         return this.getKey(this.createKey(descriptor), actualReturn);
      }
   }

   public Object getKey(Key key) {
      return this.getKey(key, (String[])null);
   }

   public Object getKey(Key key, String[] actualReturn) {
      return this.getKey(key, actualReturn, (Factory)null);
   }

   public Object getKey(Key key, String[] actualReturn, Factory factory) {
      if (this.factories.size() == 0) {
         return this.handleDefault(key, actualReturn);
      } else {
         if (DEBUG) {
            System.out.println("Service: " + this.name + " key: " + key.canonicalID());
         }

         CacheEntry result = null;
         if (key != null) {
            try {
               this.factoryLock.acquireRead();
               Map<String, CacheEntry> cache = this.cache;
               if (cache == null) {
                  if (DEBUG) {
                     System.out.println("Service " + this.name + " cache was empty");
                  }

                  cache = new ConcurrentHashMap();
               }

               String currentDescriptor = null;
               ArrayList<String> cacheDescriptorList = null;
               boolean putInCache = false;
               int NDebug = 0;
               int startIndex = 0;
               int limit = this.factories.size();
               boolean cacheResult = true;
               if (factory != null) {
                  for(int i = 0; i < limit; ++i) {
                     if (factory == this.factories.get(i)) {
                        startIndex = i + 1;
                        break;
                     }
                  }

                  if (startIndex == 0) {
                     throw new IllegalStateException("Factory " + factory + "not registered with service: " + this);
                  }

                  cacheResult = false;
               }

               label287:
               do {
                  currentDescriptor = key.currentDescriptor();
                  if (DEBUG) {
                     System.out.println(this.name + "[" + NDebug++ + "] looking for: " + currentDescriptor);
                  }

                  result = (CacheEntry)cache.get(currentDescriptor);
                  if (result != null) {
                     if (DEBUG) {
                        System.out.println(this.name + " found with descriptor: " + currentDescriptor);
                     }
                     break;
                  }

                  if (DEBUG) {
                     System.out.println("did not find: " + currentDescriptor + " in cache");
                  }

                  putInCache = cacheResult;
                  int index = startIndex;

                  while(index < limit) {
                     Factory f = (Factory)this.factories.get(index++);
                     if (DEBUG) {
                        System.out.println("trying factory[" + (index - 1) + "] " + f.toString());
                     }

                     Object service = f.create(key, this);
                     if (service != null) {
                        result = new CacheEntry(currentDescriptor, service);
                        if (DEBUG) {
                           System.out.println(this.name + " factory supported: " + currentDescriptor + ", caching");
                        }
                        break label287;
                     }

                     if (DEBUG) {
                        System.out.println("factory did not support: " + currentDescriptor);
                     }
                  }

                  if (cacheDescriptorList == null) {
                     cacheDescriptorList = new ArrayList(5);
                  }

                  cacheDescriptorList.add(currentDescriptor);
               } while(key.fallback());

               if (result != null) {
                  if (putInCache) {
                     if (DEBUG) {
                        System.out.println("caching '" + result.actualDescriptor + "'");
                     }

                     cache.put(result.actualDescriptor, result);
                     if (cacheDescriptorList != null) {
                        for(String desc : cacheDescriptorList) {
                           if (DEBUG) {
                              System.out.println(this.name + " adding descriptor: '" + desc + "' for actual: '" + result.actualDescriptor + "'");
                           }

                           cache.put(desc, result);
                        }
                     }

                     this.cache = cache;
                  }

                  if (actualReturn != null) {
                     if (result.actualDescriptor.indexOf("/") == 0) {
                        actualReturn[0] = result.actualDescriptor.substring(1);
                     } else {
                        actualReturn[0] = result.actualDescriptor;
                     }
                  }

                  if (DEBUG) {
                     System.out.println("found in service: " + this.name);
                  }

                  Object var23 = result.service;
                  return var23;
               }
            } finally {
               this.factoryLock.releaseRead();
            }
         }

         if (DEBUG) {
            System.out.println("not found in service: " + this.name);
         }

         return this.handleDefault(key, actualReturn);
      }
   }

   protected Object handleDefault(Key key, String[] actualIDReturn) {
      return null;
   }

   public Set getVisibleIDs() {
      return this.getVisibleIDs((String)null);
   }

   public Set getVisibleIDs(String matchID) {
      Set<String> result = this.getVisibleIDMap().keySet();
      Key fallbackKey = this.createKey(matchID);
      if (fallbackKey != null) {
         Set<String> temp = new HashSet(result.size());

         for(String id : result) {
            if (fallbackKey.isFallbackOf(id)) {
               temp.add(id);
            }
         }

         result = temp;
      }

      return result;
   }

   private Map getVisibleIDMap() {
      synchronized(this) {
         if (this.idcache == null) {
            Map var5;
            try {
               this.factoryLock.acquireRead();
               Map<String, Factory> mutableMap = new HashMap();
               ListIterator<Factory> lIter = this.factories.listIterator(this.factories.size());

               while(lIter.hasPrevious()) {
                  Factory f = (Factory)lIter.previous();
                  f.updateVisibleIDs(mutableMap);
               }

               Map<String, Factory> result = Collections.unmodifiableMap(mutableMap);
               this.idcache = result;
               var5 = result;
            } finally {
               this.factoryLock.releaseRead();
            }

            return var5;
         } else {
            return this.idcache;
         }
      }
   }

   public String getDisplayName(String id) {
      return this.getDisplayName(id, ULocale.getDefault(ULocale.Category.DISPLAY));
   }

   public String getDisplayName(String id, ULocale locale) {
      Map<String, Factory> m = this.getVisibleIDMap();
      Factory f = (Factory)m.get(id);
      if (f != null) {
         return f.getDisplayName(id, locale);
      } else {
         Key key = this.createKey(id);

         while(key.fallback()) {
            f = (Factory)m.get(key.currentID());
            if (f != null) {
               return f.getDisplayName(id, locale);
            }
         }

         return null;
      }
   }

   public SortedMap getDisplayNames() {
      ULocale locale = ULocale.getDefault(ULocale.Category.DISPLAY);
      return this.getDisplayNames(locale, (Comparator)null, (String)null);
   }

   public SortedMap getDisplayNames(ULocale locale) {
      return this.getDisplayNames(locale, (Comparator)null, (String)null);
   }

   public SortedMap getDisplayNames(ULocale locale, Comparator com) {
      return this.getDisplayNames(locale, com, (String)null);
   }

   public SortedMap getDisplayNames(ULocale locale, String matchID) {
      return this.getDisplayNames(locale, (Comparator)null, matchID);
   }

   public SortedMap getDisplayNames(ULocale locale, Comparator com, String matchID) {
      SortedMap<String, String> dncache = null;
      LocaleRef ref = this.dnref;
      if (ref != null) {
         dncache = ref.get(locale, com);
      }

      while(dncache == null) {
         synchronized(this) {
            if (ref != this.dnref && this.dnref != null) {
               ref = this.dnref;
               dncache = ref.get(locale, com);
            } else {
               dncache = new TreeMap(com);
               Map<String, Factory> m = this.getVisibleIDMap();

               for(Map.Entry e : m.entrySet()) {
                  String id = (String)e.getKey();
                  Factory f = (Factory)e.getValue();
                  dncache.put(f.getDisplayName(id, locale), id);
               }

               dncache = Collections.unmodifiableSortedMap(dncache);
               this.dnref = new LocaleRef(dncache, locale, com);
            }
         }
      }

      Key matchKey = this.createKey(matchID);
      if (matchKey == null) {
         return dncache;
      } else {
         SortedMap<String, String> result = new TreeMap(dncache);
         Iterator<Map.Entry<String, String>> iter = result.entrySet().iterator();

         while(iter.hasNext()) {
            Map.Entry<String, String> e = (Map.Entry)iter.next();
            if (!matchKey.isFallbackOf((String)e.getValue())) {
               iter.remove();
            }
         }

         return result;
      }
   }

   public final List factories() {
      ArrayList var1;
      try {
         this.factoryLock.acquireRead();
         var1 = new ArrayList(this.factories);
      } finally {
         this.factoryLock.releaseRead();
      }

      return var1;
   }

   public Factory registerObject(Object obj, String id) {
      return this.registerObject(obj, id, true);
   }

   public Factory registerObject(Object obj, String id, boolean visible) {
      String canonicalID = this.createKey(id).canonicalID();
      return this.registerFactory(new SimpleFactory(obj, canonicalID, visible));
   }

   public final Factory registerFactory(Factory factory) {
      if (factory == null) {
         throw new NullPointerException();
      } else {
         try {
            this.factoryLock.acquireWrite();
            this.factories.add(0, factory);
            this.clearCaches();
         } finally {
            this.factoryLock.releaseWrite();
         }

         this.notifyChanged();
         return factory;
      }
   }

   public final boolean unregisterFactory(Factory factory) {
      if (factory == null) {
         throw new NullPointerException();
      } else {
         boolean result = false;

         try {
            this.factoryLock.acquireWrite();
            if (this.factories.remove(factory)) {
               result = true;
               this.clearCaches();
            }
         } finally {
            this.factoryLock.releaseWrite();
         }

         if (result) {
            this.notifyChanged();
         }

         return result;
      }
   }

   public final void reset() {
      try {
         this.factoryLock.acquireWrite();
         this.reInitializeFactories();
         this.clearCaches();
      } finally {
         this.factoryLock.releaseWrite();
      }

      this.notifyChanged();
   }

   protected void reInitializeFactories() {
      this.factories.clear();
   }

   public boolean isDefault() {
      return this.factories.size() == this.defaultSize;
   }

   protected void markDefault() {
      this.defaultSize = this.factories.size();
   }

   public Key createKey(String id) {
      return id == null ? null : new Key(id);
   }

   protected void clearCaches() {
      this.cache = null;
      this.idcache = null;
      this.dnref = null;
   }

   protected void clearServiceCache() {
      this.cache = null;
   }

   protected boolean acceptsListener(EventListener l) {
      return l instanceof ServiceListener;
   }

   protected void notifyListener(EventListener l) {
      ((ServiceListener)l).serviceChanged(this);
   }

   public String stats() {
      ICURWLock.Stats stats = this.factoryLock.resetStats();
      return stats != null ? stats.toString() : "no stats";
   }

   public String getName() {
      return this.name;
   }

   public String toString() {
      return super.toString() + "{" + this.name + "}";
   }

   public static class Key {
      private final String id;

      public Key(String id) {
         this.id = id;
      }

      public final String id() {
         return this.id;
      }

      public String canonicalID() {
         return this.id;
      }

      public String currentID() {
         return this.canonicalID();
      }

      public String currentDescriptor() {
         return "/" + this.currentID();
      }

      public boolean fallback() {
         return false;
      }

      public boolean isFallbackOf(String idToCheck) {
         return this.canonicalID().equals(idToCheck);
      }
   }

   public static class SimpleFactory implements Factory {
      protected Object instance;
      protected String id;
      protected boolean visible;

      public SimpleFactory(Object instance, String id) {
         this(instance, id, true);
      }

      public SimpleFactory(Object instance, String id, boolean visible) {
         if (instance != null && id != null) {
            this.instance = instance;
            this.id = id;
            this.visible = visible;
         } else {
            throw new IllegalArgumentException("Instance or id is null");
         }
      }

      public Object create(Key key, ICUService service) {
         return this.id.equals(key.currentID()) ? this.instance : null;
      }

      public void updateVisibleIDs(Map result) {
         if (this.visible) {
            result.put(this.id, this);
         } else {
            result.remove(this.id);
         }

      }

      public String getDisplayName(String identifier, ULocale locale) {
         return this.visible && this.id.equals(identifier) ? identifier : null;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder(super.toString());
         buf.append(", id: ");
         buf.append(this.id);
         buf.append(", visible: ");
         buf.append(this.visible);
         return buf.toString();
      }
   }

   private static final class CacheEntry {
      final String actualDescriptor;
      final Object service;

      CacheEntry(String actualDescriptor, Object service) {
         this.actualDescriptor = actualDescriptor;
         this.service = service;
      }
   }

   private static class LocaleRef {
      private final ULocale locale;
      private SortedMap dnCache;
      private Comparator com;

      LocaleRef(SortedMap dnCache, ULocale locale, Comparator com) {
         this.locale = locale;
         this.com = com;
         this.dnCache = dnCache;
      }

      SortedMap get(ULocale loc, Comparator comp) {
         SortedMap<String, String> m = this.dnCache;
         return m == null || !this.locale.equals(loc) || this.com != comp && (this.com == null || !this.com.equals(comp)) ? null : m;
      }
   }

   public interface Factory {
      Object create(Key var1, ICUService var2);

      void updateVisibleIDs(Map var1);

      String getDisplayName(String var1, ULocale var2);
   }

   public interface ServiceListener extends EventListener {
      void serviceChanged(ICUService var1);
   }
}
