package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.util.LRUMap;
import com.fasterxml.jackson.databind.util.LookupCache;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.concurrent.atomic.AtomicReference;

public final class SerializerCache {
   public static final int DEFAULT_MAX_CACHE_SIZE = 4000;
   /** @deprecated */
   @Deprecated
   public static final int DEFAULT_MAX_CACHED = 4000;
   private final LookupCache _sharedMap;
   private final AtomicReference _readOnlyMap;

   public SerializerCache() {
      this(4000);
   }

   public SerializerCache(int maxCached) {
      this._readOnlyMap = new AtomicReference();
      int initial = Math.min(64, maxCached >> 2);
      this._sharedMap = new LRUMap(initial, maxCached);
   }

   public SerializerCache(LookupCache cache) {
      this._readOnlyMap = new AtomicReference();
      this._sharedMap = cache;
   }

   public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
      ReadOnlyClassToSerializerMap m = (ReadOnlyClassToSerializerMap)this._readOnlyMap.get();
      return m != null ? m : this._makeReadOnlyLookupMap();
   }

   private final synchronized ReadOnlyClassToSerializerMap _makeReadOnlyLookupMap() {
      ReadOnlyClassToSerializerMap m = (ReadOnlyClassToSerializerMap)this._readOnlyMap.get();
      if (m == null) {
         m = ReadOnlyClassToSerializerMap.from(this._sharedMap);
         this._readOnlyMap.set(m);
      }

      return m;
   }

   public synchronized int size() {
      return this._sharedMap.size();
   }

   public JsonSerializer untypedValueSerializer(Class type) {
      synchronized(this) {
         return (JsonSerializer)this._sharedMap.get(new TypeKey(type, false));
      }
   }

   public JsonSerializer untypedValueSerializer(JavaType type) {
      synchronized(this) {
         return (JsonSerializer)this._sharedMap.get(new TypeKey(type, false));
      }
   }

   public JsonSerializer typedValueSerializer(JavaType type) {
      synchronized(this) {
         return (JsonSerializer)this._sharedMap.get(new TypeKey(type, true));
      }
   }

   public JsonSerializer typedValueSerializer(Class cls) {
      synchronized(this) {
         return (JsonSerializer)this._sharedMap.get(new TypeKey(cls, true));
      }
   }

   public void addTypedSerializer(JavaType type, JsonSerializer ser) {
      synchronized(this) {
         if (this._sharedMap.put(new TypeKey(type, true), ser) == null) {
            this._readOnlyMap.set((Object)null);
         }

      }
   }

   public void addTypedSerializer(Class cls, JsonSerializer ser) {
      synchronized(this) {
         if (this._sharedMap.put(new TypeKey(cls, true), ser) == null) {
            this._readOnlyMap.set((Object)null);
         }

      }
   }

   public void addAndResolveNonTypedSerializer(Class type, JsonSerializer ser, SerializerProvider provider) throws JsonMappingException {
      synchronized(this) {
         if (this._sharedMap.put(new TypeKey(type, false), ser) == null) {
            this._readOnlyMap.set((Object)null);
         }

         if (ser instanceof ResolvableSerializer) {
            ((ResolvableSerializer)ser).resolve(provider);
         }

      }
   }

   public void addAndResolveNonTypedSerializer(JavaType type, JsonSerializer ser, SerializerProvider provider) throws JsonMappingException {
      synchronized(this) {
         if (this._sharedMap.put(new TypeKey(type, false), ser) == null) {
            this._readOnlyMap.set((Object)null);
         }

         if (ser instanceof ResolvableSerializer) {
            ((ResolvableSerializer)ser).resolve(provider);
         }

      }
   }

   public void addAndResolveNonTypedSerializer(Class rawType, JavaType fullType, JsonSerializer ser, SerializerProvider provider) throws JsonMappingException {
      synchronized(this) {
         Object ob1 = this._sharedMap.put(new TypeKey(rawType, false), ser);
         Object ob2 = this._sharedMap.put(new TypeKey(fullType, false), ser);
         if (ob1 == null || ob2 == null) {
            this._readOnlyMap.set((Object)null);
         }

         if (ser instanceof ResolvableSerializer) {
            ((ResolvableSerializer)ser).resolve(provider);
         }

      }
   }

   public synchronized void flush() {
      this._sharedMap.clear();
      this._readOnlyMap.set((Object)null);
   }
}
