package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.MetricOperation;
import io.fabric8.kubernetes.client.utils.URLUtils;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MetricOperationsImpl extends OperationSupport implements MetricOperation {
   public static final String METRIC_ENDPOINT_URL = "apis/metrics.k8s.io/v1beta1/";
   private final Class apiTypeListClass;
   private final Class apiTypeClass;

   public MetricOperationsImpl(OperationContext operationContext, Class apiTypeClass, Class apiTypeListClass) {
      super(operationContext);
      this.apiTypeClass = apiTypeClass;
      this.apiTypeListClass = apiTypeListClass;
   }

   public Object metrics() {
      try {
         return this.handleMetric(this.getMetricEndpointUrl(), this.apiTypeListClass);
      } catch (IOException exception) {
         throw KubernetesClientException.launderThrowable(exception);
      } catch (InterruptedException interruptedException) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(interruptedException);
      }
   }

   public Object metric() {
      try {
         return this.handleMetric(this.getMetricEndpointUrl(), this.apiTypeClass);
      } catch (IOException exception) {
         throw KubernetesClientException.launderThrowable(exception);
      } catch (InterruptedException interruptedException) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(interruptedException);
      }
   }

   public Object metrics(Map labelsMap) {
      Map<String, String> labels = new HashMap();
      labelsMap.forEach((k, v) -> labels.put(k, v.toString()));
      return this.withLabels(labels).metrics();
   }

   protected String getMetricEndpointUrlWithPlural(String plural) {
      String result = URLUtils.join(new String[]{this.config.getMasterUrl(), "apis/metrics.k8s.io/v1beta1/"});
      if (this.isResourceNamespaced() && this.namespace != null) {
         result = result + "namespaces/" + this.namespace + "/";
      }

      result = result + plural;
      if (this.context.getName() != null) {
         result = result + "/" + this.context.getName();
      }

      if (Utils.isNotNullOrEmpty(this.context.getLabels())) {
         result = this.getUrlWithLabels(result, this.context.getLabels());
      }

      return result;
   }

   private String getMetricEndpointUrl() {
      return this.getMetricEndpointUrlWithPlural(this.context.getPlural());
   }

   private String getUrlWithLabels(String baseUrl, Map labels) {
      URLUtils.URLBuilder httpUrlBuilder = new URLUtils.URLBuilder(baseUrl);
      StringBuilder sb = new StringBuilder();

      for(Map.Entry entry : labels.entrySet()) {
         sb.append((String)entry.getKey()).append("=").append((String)entry.getValue()).append(",");
      }

      httpUrlBuilder.addQueryParameter("labelSelector", sb.substring(0, sb.toString().length() - 1));
      return httpUrlBuilder.toString();
   }
}
