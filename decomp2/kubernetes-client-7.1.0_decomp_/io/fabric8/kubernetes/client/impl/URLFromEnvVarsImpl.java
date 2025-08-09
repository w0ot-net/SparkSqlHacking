package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.ServiceToURLProvider;
import io.fabric8.kubernetes.client.ServiceToURLProvider.ServiceToUrlImplPriority;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.internal.URLFromServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLFromEnvVarsImpl implements ServiceToURLProvider {
   public static final Logger logger = LoggerFactory.getLogger(URLFromEnvVarsImpl.class);
   public static final String ANNOTATION_EXPOSE_URL = "fabric8.io/exposeUrl";

   public String getURL(Service service, String portName, String namespace, KubernetesClient client) {
      String serviceHost = URLFromServiceUtil.resolveHostFromEnvVarOrSystemProperty(service.getMetadata().getName());
      String servicePort = URLFromServiceUtil.resolvePortFromEnvVarOrSystemProperty(service.getMetadata().getName(), "");
      String serviceProtocol = URLFromServiceUtil.resolveProtocolFromEnvVarOrSystemProperty(((ServicePort)service.getSpec().getPorts().iterator().next()).getProtocol(), "");
      if (!serviceHost.isEmpty() && !servicePort.isEmpty() && !serviceProtocol.isEmpty()) {
         return serviceProtocol + "://" + serviceHost + ":" + servicePort;
      } else {
         String answer = (String)KubernetesResourceUtil.getOrCreateAnnotations(service).get("fabric8.io/exposeUrl");
         return answer != null && !answer.isEmpty() ? answer : null;
      }
   }

   public int getPriority() {
      return ServiceToUrlImplPriority.THIRD.getValue();
   }
}
