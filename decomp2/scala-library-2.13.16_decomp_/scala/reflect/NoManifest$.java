package scala.reflect;

import scala.runtime.ModuleSerializationProxy;

public final class NoManifest$ implements OptManifest {
   public static final NoManifest$ MODULE$ = new NoManifest$();

   public String toString() {
      return "<?>";
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NoManifest$.class);
   }

   private NoManifest$() {
   }
}
