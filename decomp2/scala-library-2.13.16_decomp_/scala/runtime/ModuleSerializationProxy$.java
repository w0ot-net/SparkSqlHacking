package scala.runtime;

import java.io.Serializable;
import java.security.AccessController;
import java.security.PrivilegedActionException;

public final class ModuleSerializationProxy$ implements Serializable {
   public static final ModuleSerializationProxy$ MODULE$ = new ModuleSerializationProxy$();
   private static final ClassValueCompat scala$runtime$ModuleSerializationProxy$$instances = new ClassValueCompat() {
      public Object getModule(final Class cls) {
         return AccessController.doPrivileged(() -> cls.getField("MODULE$").get((Object)null));
      }

      public Object computeValue(final Class cls) {
         try {
            return this.getModule(cls);
         } catch (PrivilegedActionException var3) {
            return ModuleSerializationProxy$.MODULE$.scala$runtime$ModuleSerializationProxy$$rethrowRuntime(var3.getCause());
         }
      }
   };

   public ClassValueCompat scala$runtime$ModuleSerializationProxy$$instances() {
      return scala$runtime$ModuleSerializationProxy$$instances;
   }

   public Object scala$runtime$ModuleSerializationProxy$$rethrowRuntime(final Throwable e) {
      if (e instanceof RuntimeException) {
         throw (RuntimeException)e;
      } else {
         throw new RuntimeException(e);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ModuleSerializationProxy$.class);
   }

   private ModuleSerializationProxy$() {
   }
}
