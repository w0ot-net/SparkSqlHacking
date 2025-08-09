package scala.reflect;

import java.lang.reflect.AccessibleObject;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   /** @deprecated */
   private static final ClassManifestFactory$ ClassManifest;

   static {
      ClassManifest = ClassManifestFactory$.MODULE$;
   }

   /** @deprecated */
   public ClassManifestFactory$ ClassManifest() {
      return ClassManifest;
   }

   public ClassTag classTag(final ClassTag ctag) {
      return ctag;
   }

   public AccessibleObject ensureAccessible(final AccessibleObject m) {
      if (!m.isAccessible()) {
         try {
            m.setAccessible(true);
         } catch (SecurityException var2) {
         }
      }

      return m;
   }

   private package$() {
   }
}
