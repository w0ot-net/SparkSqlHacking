package scala.collection.mutable;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class StringBuilder$ implements Serializable {
   public static final StringBuilder$ MODULE$ = new StringBuilder$();

   /** @deprecated */
   public StringBuilder newBuilder() {
      return new StringBuilder();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StringBuilder$.class);
   }

   private StringBuilder$() {
   }
}
