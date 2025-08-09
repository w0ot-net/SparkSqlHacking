package io.fabric8.kubernetes.client.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.fabric8.kubernetes.client.http.BasicBuilder;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.http.Interceptor;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BackwardsCompatibilityInterceptor implements Interceptor {
   public static final String JSON = "application/json";
   public static final String NAME = "BACKWARDS";
   private static final int API_GROUP = 1;
   private static final int API_VERSION = 2;
   private static final int PATH = 3;
   private static final String PATCH = "patch";
   private static final String NAME_REGEX = "[a-z0-9\\-\\.]+";
   private static final Pattern URL_PATTERN = Pattern.compile("[^ ]+/apis/([a-z0-9\\-\\.]+)/([a-z0-9\\-\\.]+)/([a-z0-9\\-\\.]+)[^ ]*");
   private static final Pattern NAMESPACED_URL_PATTERN = Pattern.compile("[^ ]+/apis/([a-z0-9\\-\\.]+)/([a-z0-9\\-\\.]+)/namespaces/[a-z0-9\\-\\.]+/([a-z0-9\\-\\.]+)[^ ]*");
   private static final Map notFoundTransformations = new HashMap();
   private static final Map badRequestTransformations = new HashMap();
   private static final Map openshiftOAPITransformations = new HashMap();
   private static final Map responseCodeToTransformations = new HashMap();

   public CompletableFuture afterFailure(HttpRequest.Builder builder, HttpResponse response, Interceptor.RequestTags tags) {
      ResourceKey target = this.findNewTarget(builder, response);
      if (target == null) {
         return CompletableFuture.completedFuture(false);
      } else {
         HttpRequest request = response.request();
         if (request.bodyString() != null && !request.method().equalsIgnoreCase("patch")) {
            JsonNode object = (JsonNode)Serialization.unmarshal(request.bodyString(), JsonNode.class);
            if (object.get("apiVersion") != null) {
               ((ObjectNode)object).put("apiVersion", target.group + "/" + target.version);
               switch (request.method()) {
                  case "POST":
                     builder.post("application/json", Serialization.asJson(object));
                     break;
                  case "PUT":
                     builder.put("application/json", Serialization.asJson(object));
                     break;
                  case "DELETE":
                     builder.delete("application/json", Serialization.asJson(object));
                     break;
                  default:
                     return CompletableFuture.completedFuture(false);
               }
            }
         }

         return CompletableFuture.completedFuture(true);
      }
   }

   public ResourceKey findNewTarget(BasicBuilder basicBuilder, HttpResponse response) {
      HttpRequest request = response.request();
      if (isDeprecatedOpenshiftOapiRequest(request)) {
         return handleOpenshiftOapiRequests(basicBuilder, request, response);
      } else {
         if (!response.isSuccessful() && responseCodeToTransformations.keySet().contains(response.code())) {
            String url = request.uri().toString();
            Matcher matcher = getMatcher(url);
            ResourceKey key = getKey(matcher);
            ResourceKey target = (ResourceKey)((Map)responseCodeToTransformations.get(response.code())).get(key);
            if (target != null) {
               String newUrl = (new StringBuilder(url)).replace(matcher.start(2), matcher.end(2), target.version).replace(matcher.start(1), matcher.end(1), target.group).toString();
               basicBuilder.uri(URI.create(newUrl));
               return target;
            }
         }

         return null;
      }
   }

   public CompletableFuture afterFailure(BasicBuilder basicBuilder, HttpResponse response, Interceptor.RequestTags tags) {
      return CompletableFuture.completedFuture(this.findNewTarget(basicBuilder, response) != null);
   }

   private static Matcher getMatcher(String url) {
      Matcher m = NAMESPACED_URL_PATTERN.matcher(url);
      if (m.matches()) {
         return m;
      } else {
         m = URL_PATTERN.matcher(url);
         return m.matches() ? m : null;
      }
   }

   private static ResourceKey getKey(Matcher m) {
      return m != null ? new ResourceKey((String)null, m.group(3), m.group(1), m.group(2)) : null;
   }

   private static ResourceKey handleOpenshiftOapiRequests(BasicBuilder builder, HttpRequest request, HttpResponse response) {
      if (!response.isSuccessful() && response.code() == 404) {
         String requestUrl = request.uri().toString();
         String[] parts = requestUrl.split("/");
         String resourcePath = parts[parts.length - 1];
         ResourceKey target = (ResourceKey)openshiftOAPITransformations.get(resourcePath);
         if (target != null) {
            requestUrl = requestUrl.replace("/oapi", "/apis/" + target.getGroup());
            builder.uri(URI.create(requestUrl));
            return target;
         }
      }

      return null;
   }

   private static boolean isDeprecatedOpenshiftOapiRequest(HttpRequest request) {
      return request.uri().toString().contains("oapi");
   }

   static {
      notFoundTransformations.put(new ResourceKey("Deployment", "deployments", "apps", "v1"), new ResourceKey("Deployment", "deployments", "extensions", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("StatefulSet", "statefulsets", "apps", "v1"), new ResourceKey("StatefulSet", "statefulsets", "apps", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("DaemonSets", "daemonsets", "apps", "v1"), new ResourceKey("DaemonSet", "daemonsets", "extensions", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("ReplicaSets", "replicasets", "apps", "v1"), new ResourceKey("ReplicaSet", "replicasets", "extensions", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("NetworkPolicy", "networkpolicies", "networking.k8s.io", "v1"), new ResourceKey("NetworkPolicy", "networkpolicies", "extensions", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("StorageClass", "storageclasses", "storage.k8s.io", "v1"), new ResourceKey("StorageClass", "storageclasses", "extensions", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("RoleBinding", "rolebindings", "rbac.authorization.k8s.io", "v1"), new ResourceKey("RoleBinding", "rolebindings", "rbac.authorization.k8s.io", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("Role", "roles", "rbac.authorization.k8s.io", "v1"), new ResourceKey("Role", "roles", "rbac.authorization.k8s.io", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("ClusterRoleBinding", "clusterrolebindings", "rbac.authorization.k8s.io", "v1"), new ResourceKey("ClusterRoleBinding", "clusterrolebindings", "rbac.authorization.k8s.io", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("ClusterRole", "clusterroles", "rbac.authorization.k8s.io", "v1"), new ResourceKey("ClusterRole", "clusterroles", "rbac.authorization.k8s.io", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("CronJob", "cronjobs", "batch", "v1"), new ResourceKey("CronJob", "cronjobs", "batch", "v1beta1"));
      notFoundTransformations.put(new ResourceKey("CronJob", "cronjobs", "batch", "v1beta1"), new ResourceKey("CronJob", "cronjobs", "batch", "v2alpha1"));
      notFoundTransformations.put(new ResourceKey("Template", "template", "", "v1"), new ResourceKey("Template", "template", "template.openshift.io", "v1"));
      badRequestTransformations.put(new ResourceKey("Deployment", "deployments", "apps", "v1beta1"), new ResourceKey("Deployment", "deployments", "extensions", "v1beta1"));
      responseCodeToTransformations.put(400, badRequestTransformations);
      responseCodeToTransformations.put(404, notFoundTransformations);
      openshiftOAPITransformations.put("routes", new ResourceKey("Route", "routes", "route.openshift.io", "v1"));
      openshiftOAPITransformations.put("templates", new ResourceKey("Template", "templates", "template.openshift.io", "v1"));
      openshiftOAPITransformations.put("buildconfigs", new ResourceKey("BuildConfig", "buildconfigs", "build.openshift.io", "v1"));
      openshiftOAPITransformations.put("deploymentconfigs", new ResourceKey("DeploymentConfig", "deploymentconfigs", "apps.openshift.io", "v1"));
      openshiftOAPITransformations.put("imagestreams", new ResourceKey("ImageStream", "imagestreams", "image.openshift.io", "v1"));
      openshiftOAPITransformations.put("imagestreamtags", new ResourceKey("ImageStream", "imagestreamtags", "image.openshift.io", "v1"));
      openshiftOAPITransformations.put("securitycontextconstraints", new ResourceKey("SecurityContextConstraints", "securitycontextconstraints", "security.openshift.io", "v1"));
   }

   private static class ResourceKey {
      private final String kind;
      private final String path;
      private final String group;
      private final String version;

      public ResourceKey(String kind, String path, String group, String version) {
         this.kind = kind;
         this.path = path;
         this.group = group;
         this.version = version;
      }

      public String getKind() {
         return this.kind;
      }

      public String getPath() {
         return this.path;
      }

      public String getGroup() {
         return this.group;
      }

      public String getVersion() {
         return this.version;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            ResourceKey key = (ResourceKey)o;
            return Objects.equals(this.path, key.path) && Objects.equals(this.group, key.group) && Objects.equals(this.version, key.version);
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.path, this.group, this.version});
      }
   }
}
