package io.fabric8.kubernetes.client.dsl;

public interface Replaceable extends Updatable {
   /** @deprecated */
   @Deprecated
   Object replaceStatus();

   Object updateStatus();
}
