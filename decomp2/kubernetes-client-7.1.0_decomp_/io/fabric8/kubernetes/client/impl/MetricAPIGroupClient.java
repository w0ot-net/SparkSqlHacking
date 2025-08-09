package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.MetricAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.internal.NodeMetricOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.PodMetricOperationsImpl;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class MetricAPIGroupClient extends ClientAdapter implements MetricAPIGroupDSL {
   public PodMetricOperationsImpl pods() {
      return new PodMetricOperationsImpl(this);
   }

   public NodeMetricOperationsImpl nodes() {
      return new NodeMetricOperationsImpl(this);
   }

   public MetricAPIGroupClient newInstance() {
      return new MetricAPIGroupClient();
   }
}
