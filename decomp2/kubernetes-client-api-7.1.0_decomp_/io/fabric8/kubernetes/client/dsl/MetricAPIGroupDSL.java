package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface MetricAPIGroupDSL extends Client {
   PodMetricOperation pods();

   NodeMetricOperation nodes();
}
