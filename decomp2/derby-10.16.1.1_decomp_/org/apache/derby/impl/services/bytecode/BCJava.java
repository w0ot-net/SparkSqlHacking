package org.apache.derby.impl.services.bytecode;

import java.util.Properties;
import org.apache.derby.iapi.services.cache.CacheFactory;
import org.apache.derby.iapi.services.cache.CacheManager;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.cache.CacheableFactory;
import org.apache.derby.iapi.services.classfile.ClassHolder;
import org.apache.derby.iapi.services.compiler.ClassBuilder;
import org.apache.derby.iapi.services.compiler.JavaFactory;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.monitor.ModuleControl;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.shared.common.error.StandardException;

public class BCJava implements JavaFactory, CacheableFactory, ModuleControl {
   private CacheManager vmTypeIdCache;

   public void boot(boolean var1, Properties var2) throws StandardException {
      CacheFactory var3 = (CacheFactory)startSystemModule("org.apache.derby.iapi.services.cache.CacheFactory");
      this.vmTypeIdCache = var3.newCacheManager(this, "VMTypeIdCache", 64, 256);
   }

   public void stop() {
   }

   public ClassBuilder newClassBuilder(ClassFactory var1, String var2, int var3, String var4, String var5) {
      return new BCClass(var1, var2, var3, var4, var5, this);
   }

   public Cacheable newCacheable(CacheManager var1) {
      return new VMTypeIdCacheable();
   }

   Type type(String var1) {
      try {
         VMTypeIdCacheable var3 = (VMTypeIdCacheable)this.vmTypeIdCache.find(var1);
         Type var5 = (Type)var3.descriptor();
         this.vmTypeIdCache.release(var3);
         return var5;
      } catch (StandardException var4) {
         Type var2 = new Type(var1, ClassHolder.convertToInternalDescriptor(var1));
         return var2;
      }
   }

   String vmType(BCMethodDescriptor var1) {
      String var2;
      try {
         VMTypeIdCacheable var3 = (VMTypeIdCacheable)this.vmTypeIdCache.find(var1);
         var2 = var3.descriptor().toString();
         this.vmTypeIdCache.release(var3);
      } catch (StandardException var4) {
         var2 = var1.buildMethodDescriptor();
      }

      return var2;
   }

   static short vmTypeId(String var0) {
      char var1 = var0.charAt(0);
      switch (var1) {
         case 'B':
            return 0;
         case 'C':
            return 6;
         case 'D':
            return 5;
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'W':
         case 'X':
         case 'Y':
         default:
            return -1;
         case 'F':
            return 4;
         case 'I':
            return 2;
         case 'J':
            return 3;
         case 'L':
            return 7;
         case 'S':
            return 1;
         case 'V':
            return -1;
         case 'Z':
            return 2;
         case '[':
            return 7;
      }
   }

   private static Object startSystemModule(String var0) throws StandardException {
      return Monitor.startSystemModule(var0);
   }
}
