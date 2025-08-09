package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.ServiceToURLProvider;
import io.fabric8.kubernetes.client.ServiceToURLProvider.ServiceToUrlImplPriority;
import io.fabric8.kubernetes.client.utils.internal.URLFromServiceUtil;

public class URLFromClusterIPImpl implements ServiceToURLProvider {
   public int getPriority() {
      return ServiceToUrlImplPriority.FIFTH.getValue();
   }

   public String getURL(Service service, String portName, String namespace, KubernetesClient client) {
      ServicePort port = URLFromServiceUtil.getServicePortByName(service, portName);
      if (port != null && service.getSpec().getType().equals("ClusterIP")) {
         String var10000 = port.getProtocol().toLowerCase();
         return var10000 + "://" + service.getSpec().getClusterIP() + ":" + port.getPort();
      } else {
         return null;
      }
   }
}
