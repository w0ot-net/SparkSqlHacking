package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.api.model.ListOptions;

public interface Listable {
   Object list();

   /** @deprecated */
   @Deprecated
   Object list(Integer var1, String var2);

   Object list(ListOptions var1);
}
