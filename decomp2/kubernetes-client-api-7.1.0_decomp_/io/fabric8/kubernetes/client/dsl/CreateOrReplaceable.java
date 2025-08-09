package io.fabric8.kubernetes.client.dsl;

public interface CreateOrReplaceable extends Replaceable {
   /** @deprecated */
   @Deprecated
   Object createOrReplace();

   Object create();
}
