package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.extensions.IngressList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.ServiceToURLProvider;
import io.fabric8.kubernetes.client.ServiceToURLProvider.ServiceToUrlImplPriority;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.utils.internal.URLFromServiceUtil;

public class URLFromIngressImpl implements ServiceToURLProvider {
   public String getURL(Service service, String portName, String namespace, KubernetesClient client) {
      ServicePort port = URLFromServiceUtil.getServicePortByName(service, portName);
      String serviceName = service.getMetadata().getName();
      if (port == null) {
         throw new RuntimeException("Couldn't find port: " + portName + " for service " + service.getMetadata().getName());
      } else {
         if (client.supports(Ingress.class)) {
            IngressList ingresses = (IngressList)((NonNamespaceOperation)client.extensions().ingresses().inNamespace(namespace)).list();
            if (ingresses != null && !ingresses.getItems().isEmpty()) {
               return URLFromServiceUtil.getURLFromExtensionsV1beta1IngressList(ingresses.getItems(), namespace, serviceName, port);
            }
         } else if (client.supports(io.fabric8.kubernetes.api.model.networking.v1.Ingress.class)) {
            io.fabric8.kubernetes.api.model.networking.v1.IngressList ingresses = (io.fabric8.kubernetes.api.model.networking.v1.IngressList)((NonNamespaceOperation)client.network().v1().ingresses().inNamespace(namespace)).list();
            if (ingresses != null && !ingresses.getItems().isEmpty()) {
               return URLFromServiceUtil.getURLFromNetworkingV1IngressList(ingresses.getItems(), namespace, serviceName, port);
            }
         }

         return null;
      }
   }

   public int getPriority() {
      return ServiceToUrlImplPriority.FIRST.getValue();
   }
}
