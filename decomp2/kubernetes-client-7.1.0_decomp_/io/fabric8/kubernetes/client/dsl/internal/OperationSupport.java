package io.fabric8.kubernetes.client.dsl.internal;

import com.fasterxml.jackson.core.type.TypeReference;
import io.fabric8.kubernetes.api.model.DeleteOptions;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Preconditions;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.StatusBuilder;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.RequestConfigBuilder;
import io.fabric8.kubernetes.client.dsl.FieldValidateable.Validation;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.http.HttpResponse;
import io.fabric8.kubernetes.client.impl.BaseClient;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;
import io.fabric8.kubernetes.client.utils.URLUtils;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationSupport {
   private static final String FIELD_MANAGER_PARAM = "?fieldManager=";
   public static final String JSON = "application/json";
   public static final String JSON_PATCH = "application/json-patch+json";
   public static final String STRATEGIC_MERGE_JSON_PATCH = "application/strategic-merge-patch+json";
   public static final String JSON_MERGE_PATCH = "application/merge-patch+json";
   private static final Logger LOG = LoggerFactory.getLogger(OperationSupport.class);
   private static final String CLIENT_STATUS_FLAG = "CLIENT_STATUS_FLAG";
   protected OperationContext context;
   protected final HttpClient httpClient;
   protected final Config config;
   protected final String resourceT;
   protected String namespace;
   protected String name;
   protected String subresource;
   protected String apiGroupName;
   protected String apiGroupVersion;
   protected boolean dryRun;

   public OperationSupport(Client client) {
      this((new OperationContext()).withClient(client));
   }

   public OperationSupport(OperationContext ctx) {
      this.context = ctx;
      this.httpClient = ctx.getHttpClient();
      this.config = ctx.getConfig();
      this.resourceT = ctx.getPlural();
      this.namespace = ctx.getNamespace();
      this.name = ctx.getName();
      this.subresource = ctx.getSubresource();
      this.apiGroupName = ctx.getApiGroupName();
      this.dryRun = ctx.getDryRun();
      if (Utils.isNotNullOrEmpty(ctx.getApiGroupVersion())) {
         this.apiGroupVersion = ctx.getApiGroupVersion();
      } else if (ctx.getConfig() != null && Utils.isNotNullOrEmpty(ctx.getConfig().getApiVersion())) {
         this.apiGroupVersion = ctx.getConfig().getApiVersion();
      } else {
         this.apiGroupVersion = "v1";
      }

   }

   public String getAPIGroupName() {
      return this.apiGroupName;
   }

   public String getAPIGroupVersion() {
      return this.apiGroupVersion;
   }

   public String getResourceT() {
      return this.resourceT;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public String getName() {
      return this.name;
   }

   public boolean isResourceNamespaced() {
      return true;
   }

   protected List getRootUrlParts() {
      ArrayList<String> result = new ArrayList();
      result.add(this.config.getMasterUrl());
      if (!Utils.isNullOrEmpty(this.apiGroupName)) {
         result.add("apis");
         result.add(this.apiGroupName);
         result.add(this.apiGroupVersion);
      } else {
         result.add("api");
         result.add(this.apiGroupVersion);
      }

      return result;
   }

   protected URL getNamespacedUrl(String namespace, String type) throws MalformedURLException {
      List<String> parts = this.getRootUrlParts();
      this.addNamespacedUrlPathParts(parts, namespace, type);
      URL requestUrl = new URL(URLUtils.join((String[])parts.toArray(new String[parts.size()])));
      return requestUrl;
   }

   public URL getNamespacedUrl(String namespace) throws MalformedURLException {
      return this.getNamespacedUrl(namespace, this.resourceT);
   }

   protected void addNamespacedUrlPathParts(List parts, String namespace, String type) {
      if (this.isResourceNamespaced() && Utils.isNotNullOrEmpty(namespace)) {
         parts.add("namespaces");
         parts.add(namespace);
      }

      if (Utils.isNotNullOrEmpty(type)) {
         parts.add(type);
      }

   }

   public URL getNamespacedUrl() throws MalformedURLException {
      return this.getNamespacedUrl(this.getNamespace());
   }

   public URL getResourceUrl(String namespace, String name, String... subresources) throws MalformedURLException {
      String subresourcePath = URLUtils.pathJoin(subresources);
      if (name == null) {
         if (Utils.isNotNullOrEmpty(subresourcePath)) {
            throw new KubernetesClientException("name not specified for an operation requiring one.");
         } else {
            return this.getNamespacedUrl(namespace);
         }
      } else {
         String path = name;
         if (Utils.isNotNullOrEmpty(subresourcePath)) {
            path = URLUtils.pathJoin(new String[]{name, subresourcePath});
         }

         return new URL(URLUtils.join(new String[]{this.getNamespacedUrl(namespace).toString(), path}));
      }
   }

   public URL getResourceUrl(String namespace, String name) throws MalformedURLException {
      return this.getResourceUrl(namespace, name, this.subresource);
   }

   public URL getResourceUrl() throws MalformedURLException {
      return this.getResourceUrl(this.namespace, this.name, this.subresource);
   }

   public URL getResourceURLForWriteOperation(URL resourceURL) throws MalformedURLException {
      if (this.dryRun) {
         resourceURL = new URL(URLUtils.join(new String[]{resourceURL.toString(), "?dryRun=All"}));
      }

      if (this.context.fieldValidation != null) {
         resourceURL = new URL(URLUtils.join(new String[]{resourceURL.toString(), "?fieldValidation=" + this.context.fieldValidation.parameterValue()}));
      }

      return resourceURL;
   }

   public URL getResourceURLForPatchOperation(URL resourceUrl, PatchContext patchContext) throws MalformedURLException {
      if (patchContext == null) {
         return resourceUrl;
      } else {
         String url = resourceUrl.toString();
         Boolean forceConflicts = patchContext.getForce();
         if (forceConflicts == null) {
            forceConflicts = this.context.forceConflicts;
         }

         if (forceConflicts != null) {
            url = URLUtils.join(new String[]{url, "?force=" + forceConflicts});
         }

         if (patchContext.getDryRun() != null && !patchContext.getDryRun().isEmpty() || this.dryRun) {
            url = URLUtils.join(new String[]{url, "?dryRun=All"});
         }

         String fieldManager = patchContext.getFieldManager();
         if (fieldManager == null) {
            fieldManager = this.context.fieldManager;
         }

         if (fieldManager == null && patchContext.getPatchType() == PatchType.SERVER_SIDE_APPLY) {
            fieldManager = "fabric8";
         }

         if (fieldManager != null) {
            url = URLUtils.join(new String[]{url, "?fieldManager=" + fieldManager});
         }

         String fieldValidation = patchContext.getFieldValidation();
         if (fieldValidation == null && this.context.fieldValidation != null) {
            fieldValidation = this.context.fieldValidation.parameterValue();
         }

         if (fieldValidation != null) {
            url = URLUtils.join(new String[]{url, "?fieldValidation=" + fieldValidation});
         }

         return new URL(url);
      }
   }

   protected Object correctNamespace(Object item) {
      if (this.isResourceNamespaced() && !this.context.isDefaultNamespace() && item instanceof HasMetadata) {
         String itemNs = KubernetesResourceUtil.getNamespace((HasMetadata)item);
         if (Utils.isNotNullOrEmpty(this.namespace) && Utils.isNotNullOrEmpty(itemNs) && !this.namespace.equals(itemNs)) {
            item = (T)this.getKubernetesSerialization().clone(item);
            KubernetesResourceUtil.setNamespace((HasMetadata)item, this.namespace);
         }

         return item;
      } else {
         return item;
      }
   }

   protected String checkNamespace(Object item) {
      if (!this.isResourceNamespaced()) {
         return null;
      } else {
         String operationNs = this.getNamespace();
         String itemNs = item instanceof HasMetadata ? KubernetesResourceUtil.getNamespace((HasMetadata)item) : null;
         if (Utils.isNullOrEmpty(operationNs) && Utils.isNullOrEmpty(itemNs)) {
            if (this.context.isDefaultNamespace()) {
               throw new KubernetesClientException("namespace not specified for an operation requiring one and no default was found in the Config.");
            } else {
               throw new KubernetesClientException("namespace not specified for an operation requiring one.");
            }
         } else {
            return Utils.isNullOrEmpty(itemNs) || !Utils.isNullOrEmpty(operationNs) && !this.context.isDefaultNamespace() ? operationNs : itemNs;
         }
      }
   }

   protected String checkName(Object item) {
      String operationName = this.getName();
      ObjectMeta metadata = item instanceof HasMetadata ? ((HasMetadata)item).getMetadata() : null;
      String itemName = metadata != null ? metadata.getName() : null;
      if (Utils.isNullOrEmpty(operationName) && Utils.isNullOrEmpty(itemName)) {
         return null;
      } else if (Utils.isNullOrEmpty(itemName)) {
         return operationName;
      } else if (Utils.isNullOrEmpty(operationName)) {
         return itemName;
      } else if (Objects.equals(itemName, operationName)) {
         return itemName;
      } else {
         throw new KubernetesClientException("Name mismatch. Item name:" + itemName + ". Operation name:" + operationName + ".");
      }
   }

   protected Object handleMetric(String resourceUrl, Class type) throws InterruptedException, IOException {
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().uri(resourceUrl);
      return this.handleResponse(requestBuilder, type);
   }

   protected KubernetesResource handleDelete(URL requestUrl, long gracePeriodSeconds, DeletionPropagation propagationPolicy, String resourceVersion) throws InterruptedException, IOException {
      DeleteOptions deleteOptions = new DeleteOptions();
      if (gracePeriodSeconds >= 0L) {
         deleteOptions.setGracePeriodSeconds(gracePeriodSeconds);
      }

      if (resourceVersion != null) {
         deleteOptions.setPreconditions(new Preconditions(resourceVersion, (String)null));
      }

      if (propagationPolicy != null) {
         deleteOptions.setPropagationPolicy(propagationPolicy.toString());
      }

      if (this.dryRun) {
         deleteOptions.setDryRun(Collections.singletonList("All"));
      }

      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().delete("application/json", this.getKubernetesSerialization().asJson(deleteOptions)).url(requestUrl);
      return (KubernetesResource)this.handleResponse(requestBuilder, KubernetesResource.class);
   }

   protected Object handleCreate(Object resource, Class outputType) throws InterruptedException, IOException {
      resource = (I)this.correctNamespace(resource);
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().post("application/json", this.getKubernetesSerialization().asJson(resource)).url(this.getResourceURLForWriteOperation(this.getResourceUrl(this.checkNamespace(resource), (String)null)));
      return this.handleResponse(requestBuilder, outputType);
   }

   protected Object handleUpdate(Object updated, Class type) throws IOException {
      updated = (T)this.correctNamespace(updated);
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().put("application/json", this.getKubernetesSerialization().asJson(updated)).url(this.getResourceURLForWriteOperation(this.getResourceUrl(this.checkNamespace(updated), this.checkName(updated))));
      return this.handleResponse(requestBuilder, type);
   }

   protected Object handlePatch(PatchContext patchContext, Object current, Object updated, Class type) throws InterruptedException, IOException {
      String patchForUpdate;
      if (current != null && (patchContext == null || patchContext.getPatchType() == PatchType.JSON)) {
         if (current instanceof HasMetadata) {
            ObjectMeta meta = ((HasMetadata)current).getMetadata();
            if (meta != null) {
               meta.setResourceVersion((String)null);
            }
         }

         patchForUpdate = PatchUtils.jsonDiff(current, updated, false, this.getKubernetesSerialization());
         if (patchContext == null) {
            patchContext = (new PatchContext.Builder()).withPatchType(PatchType.JSON).build();
         }
      } else {
         patchForUpdate = this.getKubernetesSerialization().asJson(updated);
         current = updated;
      }

      return this.handlePatch(patchContext, current, patchForUpdate, type);
   }

   protected Object handlePatch(PatchContext patchContext, Object current, String patchForUpdate, Class type) throws InterruptedException, IOException {
      String bodyContentType = this.getContentTypeFromPatchContextOrDefault(patchContext);
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().patch(bodyContentType, patchForUpdate).url(this.getResourceURLForPatchOperation(this.getResourceUrl(this.checkNamespace(current), this.checkName(current)), patchContext));
      return this.handleResponse(requestBuilder, type);
   }

   protected Object handleScale(String resourceUrl, Object scale, Class scaleType) throws InterruptedException, IOException {
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().uri(resourceUrl + "/scale");
      if (scale != null) {
         requestBuilder.put("application/json", this.getKubernetesSerialization().asJson(scale));
      }

      return this.handleResponse(requestBuilder, scaleType);
   }

   protected Status handleDeploymentRollback(String resourceUrl, DeploymentRollback deploymentRollback) throws InterruptedException, IOException {
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().uri(resourceUrl + "/rollback").post("application/json", this.getKubernetesSerialization().asJson(deploymentRollback));
      return (Status)this.handleResponse(requestBuilder, Status.class);
   }

   protected Object handleGet(URL resourceUrl, Class type) throws IOException {
      HttpRequest.Builder requestBuilder = this.httpClient.newHttpRequestBuilder().url(resourceUrl);
      return this.handleResponse(requestBuilder, type);
   }

   protected Object handleRawGet(URL resourceUrl, Class type) throws IOException {
      return this.handleRaw(type, resourceUrl.toString(), "GET", (Object)null);
   }

   HttpRequest.Builder withRequestTimeout(HttpRequest.Builder builder) {
      return builder.timeout((long)this.getRequestConfig().getRequestTimeout(), TimeUnit.MILLISECONDS);
   }

   protected Object waitForResult(CompletableFuture future) throws IOException {
      try {
         return future.get();
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         InterruptedIOException ie = new InterruptedIOException();
         ie.initCause(e);
         throw ie;
      } catch (ExecutionException e) {
         Throwable t = e;
         if (e.getCause() != null) {
            t = e.getCause();
         }

         if (t instanceof IOException) {
            throw new IOException(t.getMessage(), t);
         } else if (t instanceof KubernetesClientException) {
            throw ((KubernetesClientException)t).copyAsCause();
         } else {
            throw new KubernetesClientException(t.getMessage(), t);
         }
      }
   }

   protected Object handleResponse(HttpRequest.Builder requestBuilder, final Class type) throws IOException {
      return this.waitForResult(this.handleResponse(this.httpClient, this.withRequestTimeout(requestBuilder), new TypeReference() {
         public Type getType() {
            return type;
         }
      }));
   }

   protected CompletableFuture handleResponse(HttpClient client, HttpRequest.Builder requestBuilder, TypeReference type) {
      VersionUsageUtils.log(this.resourceT, this.apiGroupVersion);
      HttpRequest request = requestBuilder.build();
      return client.sendAsync(request, byte[].class).thenApply((response) -> {
         try {
            this.assertResponseCode(request, response);
            return type != null && type.getType() != null ? this.getKubernetesSerialization().unmarshal(new ByteArrayInputStream((byte[])response.body()), type) : null;
         } catch (KubernetesClientException e) {
            throw e;
         } catch (Exception e) {
            throw requestException(request, e);
         }
      });
   }

   protected void assertResponseCode(HttpRequest request, HttpResponse response) {
      List<String> warnings = response.headers("Warning");
      if (warnings != null && !warnings.isEmpty()) {
         if (this.context.fieldValidation == Validation.WARN) {
            LOG.warn("Recieved warning(s) from request {}: {}", request.uri(), warnings);
         } else {
            LOG.debug("Recieved warning(s) from request {}: {}", request.uri(), warnings);
         }
      }

      if (!response.isSuccessful()) {
         throw requestFailure(request, createStatus(response, this.getKubernetesSerialization()));
      }
   }

   public static Status createStatus(HttpResponse response, KubernetesSerialization kubernetesSerialization) {
      String statusMessage = "";
      int statusCode = response != null ? response.code() : 0;
      if (response == null) {
         statusMessage = "No response";
      } else {
         try {
            String bodyString = response.bodyString();
            if (Utils.isNotNullOrEmpty(bodyString)) {
               Status status = (Status)kubernetesSerialization.unmarshal(bodyString, Status.class);
               if (status != null) {
                  if (status.getCode() == null) {
                     status = ((StatusBuilder)(new StatusBuilder(status)).withCode(statusCode)).build();
                  }

                  return status;
               }
            }
         } catch (RuntimeException | IOException e) {
            LOG.debug("Exception convertion response to Status", e);
         }

         if (response.message() != null) {
            statusMessage = response.message();
         }
      }

      return createStatus(statusCode, statusMessage);
   }

   public static Status createStatus(int statusCode, String message) {
      Status status = ((StatusBuilder)((StatusBuilder)(new StatusBuilder()).withCode(statusCode)).withMessage(message)).build();
      status.getAdditionalProperties().put("CLIENT_STATUS_FLAG", "true");
      return status;
   }

   public static KubernetesClientException requestFailure(HttpRequest request, Status status) {
      return requestFailure(request, status, (String)null);
   }

   public static KubernetesClientException requestFailure(HttpRequest request, Status status, String message) {
      StringBuilder sb = new StringBuilder();
      if (message != null && !message.isEmpty()) {
         sb.append(message).append(". ");
      }

      sb.append("Failure executing: ").append(request.method()).append(" at: ").append(request.uri()).append(".");
      if (status.getMessage() != null && !status.getMessage().isEmpty()) {
         sb.append(" Message: ").append(status.getMessage()).append(".");
      }

      if (!status.getAdditionalProperties().containsKey("CLIENT_STATUS_FLAG")) {
         sb.append(" Received status: ").append(status).append(".");
      }

      return new KubernetesClientException(sb.toString(), (Throwable)null, status.getCode(), status, request);
   }

   public static KubernetesClientException requestException(HttpRequest request, Throwable e, String message) {
      StringBuilder sb = new StringBuilder();
      if (message != null && !message.isEmpty()) {
         sb.append(message).append(". ");
      }

      sb.append("Error executing: ").append(request.method()).append(" at: ").append(request.uri()).append(". Cause: ").append(e.getMessage());
      return new KubernetesClientException(sb.toString(), e, -1, (Status)null, request);
   }

   public static KubernetesClientException requestException(HttpRequest request, Exception e) {
      return requestException(request, e, (String)null);
   }

   public Config getConfig() {
      return this.config;
   }

   public OperationContext getOperationContext() {
      return this.context;
   }

   public RequestConfig getRequestConfig() {
      RequestConfig result = this.context.getRequestConfig();
      if (result == null && this.config != null) {
         result = this.config.getRequestConfig();
      }

      return (new RequestConfigBuilder(result)).build();
   }

   private String getContentTypeFromPatchContextOrDefault(PatchContext patchContext) {
      return patchContext != null && patchContext.getPatchType() != null ? patchContext.getPatchType().getContentType() : "application/strategic-merge-patch+json";
   }

   public Object restCall(Class result, String... path) {
      try {
         URL requestUrl = new URL(this.config.getMasterUrl());
         String url = requestUrl.toString();
         if (path != null && path.length > 0) {
            url = URLUtils.join(new String[]{url, URLUtils.pathJoin(path)});
         }

         HttpRequest.Builder req = this.httpClient.newHttpRequestBuilder().uri(url);
         return this.handleResponse(req, result);
      } catch (KubernetesClientException e) {
         if (e.getCode() != 404) {
            throw e;
         } else {
            return null;
         }
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public Object handleRaw(Class result, String uri, String method, Object payload) {
      try {
         if (uri.startsWith("/")) {
            String var10000 = this.config.getMasterUrl();
            uri = var10000 + uri.substring(1, uri.length());
         }

         String body = null;
         if (payload instanceof String) {
            body = (String)payload;
         } else if (payload != null) {
            body = this.getKubernetesSerialization().asJson(payload);
         }

         HttpRequest request = this.withRequestTimeout(this.httpClient.newHttpRequestBuilder().uri(uri).method(method, "application/json", body)).build();
         HttpResponse<R1> response = (HttpResponse)this.waitForResult(this.httpClient.sendAsync(request, result));
         this.assertResponseCode(request, response);
         return response.body();
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public KubernetesSerialization getKubernetesSerialization() {
      return ((BaseClient)this.context.getClient().adapt(BaseClient.class)).getKubernetesSerialization();
   }
}
