package io.fabric8.kubernetes.client.dsl;

public interface Updatable {
   /** @deprecated */
   @Deprecated
   Object replace();

   Object update();
}
