package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public class NativeCache extends NativeObject {
   public NativeCache(long capacity) {
      super(NativeCache.CacheJNI.NewLRUCache(capacity));
   }

   public void delete() {
      this.assertAllocated();
      NativeCache.CacheJNI.delete(this.self);
      this.self = 0L;
   }

   @JniClass(
      name = "leveldb::Cache",
      flags = {ClassFlag.CPP}
   )
   private static class CacheJNI {
      @JniMethod(
         cast = "leveldb::Cache *",
         accessor = "leveldb::NewLRUCache"
      )
      public static final native long NewLRUCache(@JniArg(cast = "size_t") long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }
}
