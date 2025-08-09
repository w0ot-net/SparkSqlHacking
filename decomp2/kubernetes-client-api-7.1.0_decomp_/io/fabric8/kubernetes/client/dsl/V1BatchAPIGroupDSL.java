package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1BatchAPIGroupDSL extends Client {
   MixedOperation jobs();

   MixedOperation cronjobs();
}
