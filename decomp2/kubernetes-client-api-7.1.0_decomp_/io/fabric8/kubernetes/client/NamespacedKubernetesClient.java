package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.FunctionCallable;

public interface NamespacedKubernetesClient extends KubernetesClient {
   NamespacedKubernetesClient inAnyNamespace();

   NamespacedKubernetesClient inNamespace(String var1);

   FunctionCallable withRequestConfig(RequestConfig var1);
}
