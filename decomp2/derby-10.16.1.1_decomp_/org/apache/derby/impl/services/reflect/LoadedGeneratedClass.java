package org.apache.derby.impl.services.reflect;

import java.lang.reflect.InvocationTargetException;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.services.loader.ClassInfo;
import org.apache.derby.iapi.services.loader.GeneratedByteCode;
import org.apache.derby.iapi.services.loader.GeneratedClass;
import org.apache.derby.shared.common.error.StandardException;

public abstract class LoadedGeneratedClass implements GeneratedClass {
   private final ClassInfo ci;
   private final int classLoaderVersion;

   public LoadedGeneratedClass(ClassFactory var1, Class var2) {
      this.ci = new ClassInfo(var2);
      this.classLoaderVersion = var1.getClassLoaderVersion();
   }

   public String getName() {
      return this.ci.getClassName();
   }

   public Object newInstance(Context var1) throws StandardException {
      Object var2;
      try {
         GeneratedByteCode var3 = (GeneratedByteCode)this.ci.getNewInstance();
         var3.initFromContext(var1);
         var3.setGC(this);
         var3.postConstructor();
         return var3;
      } catch (InstantiationException var4) {
         var2 = var4;
      } catch (IllegalAccessException var5) {
         var2 = var5;
      } catch (InvocationTargetException var6) {
         var2 = var6;
      } catch (NoSuchMethodException var7) {
         var2 = var7;
      } catch (LinkageError var8) {
         var2 = var8;
      }

      throw StandardException.newException("XBCM2.S", (Throwable)var2, new Object[]{this.getName()});
   }

   public final int getClassLoaderVersion() {
      return this.classLoaderVersion;
   }

   protected Class getJVMClass() {
      return this.ci.getClassObject();
   }
}
