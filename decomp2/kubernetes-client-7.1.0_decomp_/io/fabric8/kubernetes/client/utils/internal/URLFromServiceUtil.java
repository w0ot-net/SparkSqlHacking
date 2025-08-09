package io.fabric8.kubernetes.client.utils.internal;

import io.fabric8.kubernetes.api.model.IntOrString;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1.HTTPIngressPath;
import io.fabric8.kubernetes.api.model.networking.v1.HTTPIngressRuleValue;
import io.fabric8.kubernetes.api.model.networking.v1.IngressBackend;
import io.fabric8.kubernetes.api.model.networking.v1.IngressRule;
import io.fabric8.kubernetes.api.model.networking.v1.IngressSpec;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.URLUtils;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class URLFromServiceUtil {
   public static final Logger logger = LoggerFactory.getLogger(URLFromServiceUtil.class);
   public static final String DEFAULT_PROTO = "tcp";
   private static final String HOST_SUFFIX = "_SERVICE_HOST";
   private static final String PORT_SUFFIX = "_SERVICE_PORT";
   private static final String PROTO_SUFFIX = "_TCP_PROTO";

   private URLFromServiceUtil() {
   }

   public static String resolveHostFromEnvVarOrSystemProperty(String serviceName) {
      return getEnvVarOrSystemProperty(toServiceHostEnvironmentVariable(serviceName), "");
   }

   private static String getEnvVarOrSystemProperty(String envVarName, String defaultValue) {
      String answer = null;

      try {
         answer = System.getenv(envVarName);
      } catch (Exception e) {
         logger.warn("Failed to look up environment variable $" + envVarName + ". " + e, e);
      }

      if (answer == null || answer.isEmpty()) {
         answer = System.getProperty(envVarName, defaultValue);
      }

      return !answer.isEmpty() ? answer : defaultValue;
   }

   public static String resolveProtocolFromEnvVarOrSystemProperty(String serviceName, String servicePort) {
      return getEnvVarOrSystemProperty(toEnvVariable(serviceName + "_SERVICE_PORT_" + servicePort + "_TCP_PROTO"), "tcp");
   }

   public static String resolvePortFromEnvVarOrSystemProperty(String serviceName, String portName) {
      String envVarName = toServicePortEnvironmentVariable(serviceName, portName);
      return getEnvVarOrSystemProperty(envVarName, "");
   }

   public static String toServicePortEnvironmentVariable(String serviceName, String portName) {
      String name = serviceName + "_SERVICE_PORT" + (!portName.isEmpty() ? "_" + portName : "");
      return toEnvVariable(name);
   }

   private static String toServiceHostEnvironmentVariable(String serviceName) {
      return toEnvVariable(serviceName + "_SERVICE_HOST");
   }

   public static String toEnvVariable(String serviceName) {
      return serviceName.toUpperCase(Locale.ROOT).replaceAll("-", "_");
   }

   public static String getURLFromExtensionsV1beta1IngressList(List ingressList, String namespace, String serviceName, ServicePort port) {
      for(Ingress item : ingressList) {
         String ns = KubernetesResourceUtil.getNamespace(item);
         if (Objects.equals(ns, namespace) && item.getSpec() != null) {
            String url = getURLFromIngressSpec(item.getSpec(), serviceName, port);
            if (url != null) {
               return url;
            }
         }
      }

      return null;
   }

   public static String getURLFromNetworkingV1IngressList(List ingressList, String namespace, String serviceName, ServicePort port) {
      for(io.fabric8.kubernetes.api.model.networking.v1.Ingress item : ingressList) {
         String ns = KubernetesResourceUtil.getNamespace(item);
         if (Objects.equals(ns, namespace) && item.getSpec() != null) {
            String url = getURLFromNetworkV1IngressSpec(item.getSpec(), serviceName, port);
            if (url != null) {
               return url;
            }
         }
      }

      return null;
   }

   public static String getURLFromNetworkV1IngressSpec(IngressSpec spec, String serviceName, ServicePort port) {
      List<IngressRule> ingressRules = spec.getRules();
      if (ingressRules != null && !ingressRules.isEmpty()) {
         for(IngressRule rule : ingressRules) {
            HTTPIngressRuleValue http = rule.getHttp();
            if (http != null && http.getPaths() != null) {
               return getURLFromNetworkV1IngressRules(http.getPaths(), spec, serviceName, port, rule);
            }
         }
      }

      return null;
   }

   public static String getURLFromNetworkV1IngressRules(List paths, IngressSpec spec, String serviceName, ServicePort port, IngressRule rule) {
      for(HTTPIngressPath path : paths) {
         IngressBackend backend = path.getBackend();
         if (backend != null) {
            String backendServiceName = backend.getService().getName();
            if (serviceName.equals(backendServiceName) && portsMatch(port, new IntOrString(backend.getService().getPort().getNumber()))) {
               return getURLFromIngressBackend(spec.getTls() != null && !spec.getTls().isEmpty(), path.getPath(), rule.getHost());
            }
         }
      }

      return null;
   }

   public static String getURLFromIngressSpec(io.fabric8.kubernetes.api.model.extensions.IngressSpec spec, String serviceName, ServicePort port) {
      List<io.fabric8.kubernetes.api.model.extensions.IngressRule> ingressRules = spec.getRules();
      if (ingressRules != null && !ingressRules.isEmpty()) {
         for(io.fabric8.kubernetes.api.model.extensions.IngressRule rule : ingressRules) {
            io.fabric8.kubernetes.api.model.extensions.HTTPIngressRuleValue http = rule.getHttp();
            if (http != null && http.getPaths() != null) {
               return getURLFromIngressRules(http.getPaths(), spec, serviceName, port, rule);
            }
         }
      }

      return null;
   }

   public static String getURLFromIngressRules(List paths, io.fabric8.kubernetes.api.model.extensions.IngressSpec spec, String serviceName, ServicePort port, io.fabric8.kubernetes.api.model.extensions.IngressRule rule) {
      for(io.fabric8.kubernetes.api.model.extensions.HTTPIngressPath path : paths) {
         io.fabric8.kubernetes.api.model.extensions.IngressBackend backend = path.getBackend();
         if (backend != null) {
            String backendServiceName = backend.getServiceName();
            if (serviceName.equals(backendServiceName) && portsMatch(port, backend.getServicePort())) {
               return getURLFromIngressBackend(spec.getTls() != null && !spec.getTls().isEmpty(), path.getPath(), rule.getHost());
            }
         }
      }

      return null;
   }

   private static String getURLFromIngressBackend(boolean tlsProvided, String pathPostFix, String host) {
      if (tlsProvided) {
         return getURLFromTLSHost(host, pathPostFix);
      } else if (host != null && !host.isEmpty()) {
         pathPostFix = fixPathPostFixIfEmpty(pathPostFix);
         String[] var10000 = new String[]{host, pathPostFix};
         return "http://" + URLUtils.pathJoin(var10000);
      } else {
         return null;
      }
   }

   public static String getURLFromTLSHost(String host, String pathPostFix) {
      if (!host.isEmpty()) {
         pathPostFix = fixPathPostFixIfEmpty(pathPostFix);
         String[] var10000 = new String[]{host, pathPostFix};
         return "https://" + URLUtils.pathJoin(var10000);
      } else {
         return null;
      }
   }

   private static String fixPathPostFixIfEmpty(String pathPostFix) {
      return pathPostFix.isEmpty() ? "/" : pathPostFix;
   }

   private static boolean portsMatch(ServicePort servicePort, IntOrString intOrString) {
      if (intOrString != null) {
         Integer port = servicePort.getPort();
         Integer intVal = intOrString.getIntVal();
         String strVal = intOrString.getStrVal();
         if (intVal != null) {
            if (port != null) {
               return port == intVal;
            }
         } else if (strVal != null) {
            return strVal.equals(servicePort.getName());
         }
      }

      return false;
   }

   public static ServicePort getServicePortByName(Service service, String portName) {
      if (portName.isEmpty()) {
         return (ServicePort)service.getSpec().getPorts().iterator().next();
      } else {
         for(ServicePort servicePort : service.getSpec().getPorts()) {
            if (Objects.equals(servicePort.getName(), portName)) {
               return servicePort;
            }
         }

         return null;
      }
   }
}
