package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.api.model.APIGroup;
import io.fabric8.kubernetes.api.model.APIGroupList;
import io.fabric8.kubernetes.api.model.APIResourceList;
import io.fabric8.kubernetes.api.model.APIVersions;
import io.fabric8.kubernetes.api.model.RootPaths;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.NamespacedKubernetesClient;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.http.HttpClient;
import java.net.URL;

public abstract class ClientAdapter implements Client {
   private Client client;

   public Client getClient() {
      return this.client;
   }

   public void init(Client client) {
      this.client = client;
   }

   public HttpClient getHttpClient() {
      return this.client.getHttpClient();
   }

   public Config getConfiguration() {
      return this.client.getConfiguration();
   }

   public boolean supports(Class type) {
      return this.client.supports(type);
   }

   public boolean supports(String apiVersion, String kind) {
      return this.client.supports(apiVersion, kind);
   }

   public boolean hasApiGroup(String apiGroup, boolean exact) {
      return this.client.hasApiGroup(apiGroup, exact);
   }

   public Client adapt(Class type) {
      return this.client.adapt(type);
   }

   public URL getMasterUrl() {
      return this.client.getMasterUrl();
   }

   public String getApiVersion() {
      return this.client.getApiVersion();
   }

   public String getNamespace() {
      return this.client.getNamespace();
   }

   public RootPaths rootPaths() {
      return this.client.rootPaths();
   }

   public boolean supportsApiPath(String path) {
      return this.client.supportsApiPath(path);
   }

   public void close() {
      this.client.close();
   }

   public APIGroupList getApiGroups() {
      return this.client.getApiGroups();
   }

   public APIVersions getAPIVersions() {
      return this.client.getAPIVersions();
   }

   public APIGroup getApiGroup(String name) {
      return this.client.getApiGroup(name);
   }

   public APIResourceList getApiResources(String groupVersion) {
      return this.client.getApiResources(groupVersion);
   }

   public MixedOperation resources(Class resourceType, Class listClass, Class resourceClass) {
      return this.client.resources(resourceType, listClass, resourceClass);
   }

   public ClientAdapter inAnyNamespace() {
      C result = (C)this.newInstance();
      result.init(((NamespacedKubernetesClient)this.client.adapt(NamespacedKubernetesClient.class)).inAnyNamespace());
      return result;
   }

   public ClientAdapter inNamespace(String namespace) {
      C result = (C)this.newInstance();
      result.init(((NamespacedKubernetesClient)this.client.adapt(NamespacedKubernetesClient.class)).inNamespace(namespace));
      return result;
   }

   public Client newClient(RequestConfig requestConfig) {
      return this.client.newClient(requestConfig);
   }

   public abstract ClientAdapter newInstance();

   public String raw(String uri, String method, Object payload) {
      return this.client.raw(uri, method, payload);
   }
}
