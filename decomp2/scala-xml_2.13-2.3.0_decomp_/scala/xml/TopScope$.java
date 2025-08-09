package scala.xml;

import scala.collection.mutable.StringBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class TopScope$ extends NamespaceBinding {
   public static final TopScope$ MODULE$ = new TopScope$();

   public String getURI(final String prefix1) {
      String var2 = XML$.MODULE$.xml();
      if (prefix1 == null) {
         if (var2 == null) {
            return XML$.MODULE$.namespace();
         }
      } else if (prefix1.equals(var2)) {
         return XML$.MODULE$.namespace();
      }

      return null;
   }

   public String getPrefix(final String uri1) {
      String var2 = XML$.MODULE$.namespace();
      if (uri1 == null) {
         if (var2 == null) {
            return XML$.MODULE$.xml();
         }
      } else if (uri1.equals(var2)) {
         return XML$.MODULE$.xml();
      }

      return null;
   }

   public String toString() {
      return "";
   }

   public String buildString(final NamespaceBinding stop) {
      return "";
   }

   public void buildString(final StringBuilder sb, final NamespaceBinding ignore) {
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TopScope$.class);
   }

   private TopScope$() {
      super((String)null, (String)null, (NamespaceBinding)null);
   }
}
