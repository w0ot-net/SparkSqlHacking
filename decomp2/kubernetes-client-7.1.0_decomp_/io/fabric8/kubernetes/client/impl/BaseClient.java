package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.APIGroup;
import io.fabric8.kubernetes.api.model.APIGroupList;
import io.fabric8.kubernetes.api.model.APIResourceList;
import io.fabric8.kubernetes.api.model.APIVersions;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.RootPaths;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.VersionInfo;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.OperationSupport;
import io.fabric8.kubernetes.client.extension.ExtensionAdapter;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.Utils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseClient implements Client {
   public static final Logger logger = LoggerFactory.getLogger(BaseClient.class);
   public static final KubernetesClientBuilder.ExecutorSupplier DEFAULT_EXECUTOR_SUPPLIER = new KubernetesClientBuilder.ExecutorSupplier() {
      public Executor get() {
         return Executors.newCachedThreadPool(Utils.daemonThreadFactory(this));
      }

      public void onClose(Executor executor) {
         ((ExecutorService)executor).shutdownNow();
      }
   };
   public static final String APIS = "/apis";
   private static final String API = "/api";
   private URL masterUrl;
   private String apiVersion;
   private String namespace;
   private Predicate matchingGroupPredicate;
   private final Adapters adapters;
   private final Handlers handlers;
   protected Config config;
   protected HttpClient httpClient;
   private OperationSupport operationSupport;
   private KubernetesClientBuilder.ExecutorSupplier executorSupplier;
   private Executor executor;
   protected KubernetesSerialization kubernetesSerialization;
   private CompletableFuture closed;
   private Set closable;
   private OperationContext operationContext;

   BaseClient(BaseClient baseClient) {
      this.closed = baseClient.closed;
      this.config = baseClient.config;
      this.httpClient = baseClient.httpClient;
      this.adapters = baseClient.adapters;
      this.handlers = baseClient.handlers;
      this.matchingGroupPredicate = baseClient.matchingGroupPredicate;
      this.executorSupplier = baseClient.executorSupplier;
      this.executor = baseClient.executor;
      this.kubernetesSerialization = baseClient.kubernetesSerialization;
      this.closable = baseClient.closable;
      this.setDerivedFields();
      if (baseClient.operationContext != null) {
         this.operationContext(baseClient.operationContext);
      }

   }

   BaseClient(HttpClient httpClient, Config config, KubernetesClientBuilder.ExecutorSupplier executorSupplier, KubernetesSerialization kubernetesSerialization) {
      this.closable = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
      this.closed = new CompletableFuture();
      this.config = config;
      this.httpClient = httpClient;
      this.handlers = new Handlers();
      this.adapters = new Adapters(this.handlers);
      this.setDerivedFields();
      if (executorSupplier == null) {
         executorSupplier = DEFAULT_EXECUTOR_SUPPLIER;
      }

      this.executorSupplier = executorSupplier;
      this.executor = (Executor)executorSupplier.get();
      this.kubernetesSerialization = kubernetesSerialization;
   }

   protected void setDerivedFields() {
      this.namespace = this.config.getNamespace();
      this.apiVersion = this.config.getApiVersion();
      if (this.config.getMasterUrl() == null) {
         throw new KubernetesClientException("Unknown Kubernetes master URL - please set with the builder, or set with either system property \"kubernetes.master\" or environment variable \"" + Utils.convertSystemPropertyNameToEnvVar("kubernetes.master") + "\"");
      } else {
         try {
            this.masterUrl = new URL(this.config.getMasterUrl());
         } catch (MalformedURLException e) {
            throw KubernetesClientException.launderThrowable(e);
         }
      }
   }

   public void close() {
      if (this.closed.complete((Object)null) && logger.isDebugEnabled()) {
         logger.debug("The client and associated httpclient {} have been closed, the usage of this or any client using the httpclient will not work after this", this.httpClient.getClass().getName());
      }

      this.httpClient.close();
      List<AutoCloseable> toClose = null;
      synchronized(this.closable) {
         toClose = new ArrayList(this.closable);
         this.closable.clear();
      }

      toClose.forEach((c) -> {
         try {
            c.close();
         } catch (Exception e) {
            logger.warn("Error closing resource", e);
         }

      });
      if (this.executorSupplier != null) {
         this.executorSupplier.onClose(this.executor);
         this.executorSupplier = null;
      }

   }

   public CompletableFuture getClosed() {
      return this.closed;
   }

   public URL getMasterUrl() {
      return this.masterUrl;
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public void setMatchingGroupPredicate(Predicate unsupportedApiGroups) {
      this.matchingGroupPredicate = unsupportedApiGroups;
   }

   public boolean hasApiGroup(String apiGroup, boolean exact) {
      if (this.matchingGroupPredicate != null) {
         return this.matchingGroupPredicate.test(apiGroup);
      } else if (exact) {
         return this.getApiGroup(apiGroup) != null;
      } else {
         APIGroupList apiGroups = this.getApiGroups();
         return apiGroups == null ? false : apiGroups.getGroups().stream().anyMatch((g) -> g.getName().endsWith(apiGroup));
      }
   }

   public boolean supports(Class type) {
      String typeApiVersion = HasMetadata.getApiVersion(type);
      if (this.matchingGroupPredicate != null) {
         return this.matchingGroupPredicate.test(typeApiVersion);
      } else {
         String typeKind = HasMetadata.getKind(type);
         return !Utils.isNullOrEmpty(typeApiVersion) && !Utils.isNullOrEmpty(typeKind) ? this.supports(ApiVersionUtil.joinApiGroupAndVersion(HasMetadata.getGroup(type), HasMetadata.getVersion(type)), typeKind) : false;
      }
   }

   public boolean supports(String apiVersion, String kind) {
      Utils.checkNotNull(kind, "kind cannot be null");
      Utils.checkNotNull(apiVersion, "apiVersion cannot be null");
      APIResourceList apiResources = this.getApiResources(apiVersion);
      return apiResources == null ? false : apiResources.getResources().stream().anyMatch((r) -> kind.equals(r.getKind()));
   }

   public Client adapt(Class type) {
      if (type.isAssignableFrom(this.getClass())) {
         return this;
      } else {
         ExtensionAdapter<C> adapter = this.adapters.get(type);
         if (adapter == null) {
            throw new IllegalStateException("No adapter available for type:" + type);
         } else {
            return adapter.adapt(this);
         }
      }
   }

   public RootPaths rootPaths() {
      return (RootPaths)this.getOperationSupport().restCall(RootPaths.class);
   }

   public boolean supportsApiPath(String apiPath) {
      RootPaths rootPaths = this.rootPaths();
      if (rootPaths != null) {
         List<String> paths = rootPaths.getPaths();
         if (paths != null) {
            for(String path : paths) {
               if (path.equals(apiPath)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public APIGroupList getApiGroups() {
      return (APIGroupList)this.getOperationSupport().restCall(APIGroupList.class, "/apis");
   }

   public APIGroup getApiGroup(String name) {
      return (APIGroup)this.getOperationSupport().restCall(APIGroup.class, "/apis", name);
   }

   public APIVersions getAPIVersions() {
      return (APIVersions)this.getOperationSupport().restCall(APIVersions.class, "/api");
   }

   private OperationSupport getOperationSupport() {
      if (this.operationSupport == null) {
         this.operationSupport = new OperationSupport(this);
      }

      return this.operationSupport;
   }

   public APIResourceList getApiResources(String groupVersion) {
      return "v1".equals(groupVersion) ? (APIResourceList)this.getOperationSupport().restCall(APIResourceList.class, "api", "v1") : (APIResourceList)this.getOperationSupport().restCall(APIResourceList.class, "/apis", groupVersion);
   }

   protected VersionInfo getVersionInfo(String path) {
      return (VersionInfo)this.getOperationSupport().restCall(VersionInfo.class, path);
   }

   public MixedOperation resources(Class resourceType, Class listClass, Class resourceClass) {
      if (GenericKubernetesResource.class.equals(resourceType)) {
         throw new KubernetesClientException("resources cannot be called with a generic type");
      } else if (resourceType.isInterface()) {
         throw new IllegalArgumentException("resources cannot be called with an interface");
      } else {
         try {
            return this.handlers.getOperation(resourceType, listClass, this);
         } catch (Exception e) {
            if (resourceClass != null && !Resource.class.equals(resourceClass)) {
               throw KubernetesClientException.launderThrowable(e);
            } else {
               return this.newHasMetadataOperation(ResourceDefinitionContext.fromResourceType(resourceType), resourceType, listClass);
            }
         }
      }
   }

   public HasMetadataOperationsImpl newHasMetadataOperation(ResourceDefinitionContext rdContext, Class resourceType, Class listClass) {
      return new HasMetadataOperationsImpl(this, rdContext, resourceType, listClass);
   }

   public Config getConfiguration() {
      return this.config;
   }

   public HttpClient getHttpClient() {
      return this.httpClient;
   }

   public Adapters getAdapters() {
      return this.adapters;
   }

   public Handlers getHandlers() {
      return this.handlers;
   }

   public OperationContext getOperationContext() {
      return this.operationContext;
   }

   public BaseClient operationContext(OperationContext operationContext) {
      this.operationContext = operationContext;
      this.namespace = operationContext.getNamespace();
      return this;
   }

   abstract BaseClient copy();

   public Client newClient(OperationContext newContext, Class clazz) {
      BaseClient copy = this.copy();
      if (newContext.getRequestConfig() != null && newContext.getConfig().getRequestConfig() != newContext.getRequestConfig()) {
         copy.httpClient = copy.httpClient.newBuilder().tag(newContext.getRequestConfig()).build();
      }

      newContext = newContext.withClient(copy);
      return copy.operationContext(newContext).adapt(clazz);
   }

   public Executor getExecutor() {
      return this.executor;
   }

   public String raw(String uri) {
      try {
         return this.raw(uri, "GET", (Object)null);
      } catch (KubernetesClientException e) {
         if (e.getCode() != 404) {
            throw e;
         } else {
            return null;
         }
      }
   }

   public String raw(String uri, String method, Object payload) {
      return (String)this.getOperationSupport().handleRaw(String.class, uri, method, payload);
   }

   public KubernetesSerialization getKubernetesSerialization() {
      return this.kubernetesSerialization;
   }

   public void addToCloseable(AutoCloseable closeable) {
      synchronized(closeable) {
         if (this.closed.isDone()) {
            throw new KubernetesClientException("Client is already closed");
         } else {
            this.closable.add(closeable);
         }
      }
   }

   public void removeFromCloseable(AutoCloseable closeable) {
      this.closable.remove(closeable);
   }
}
