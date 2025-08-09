package io.fabric8.kubernetes.client.dsl;

import java.util.Map;

public interface MetricOperation {
   String METRIC_ENDPOINT_URL = "apis/metrics.k8s.io/v1beta1/";

   MetricOperation withName(String var1);

   MetricOperation withLabels(Map var1);

   Object metrics();

   Object metric();

   Object metrics(Map var1);
}
