package io.fabric8.kubernetes.client.dsl.internal;

import com.fasterxml.jackson.core.type.TypeReference;
import io.fabric8.kubernetes.api.builder.TypedVisitor;
import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.DefaultKubernetesResourceList;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.LabelSelector;
import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.ListOptionsBuilder;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.api.model.StatusDetailsBuilder;
import io.fabric8.kubernetes.api.model.autoscaling.v1.Scale;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollback;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.KubernetesClientTimeoutException;
import io.fabric8.kubernetes.client.OperationInfo;
import io.fabric8.kubernetes.client.ResourceNotFoundException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import io.fabric8.kubernetes.client.dsl.FilterNested;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonDeletingOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.Waitable;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import io.fabric8.kubernetes.client.extension.ExtensibleResource;
import io.fabric8.kubernetes.client.http.HttpRequest;
import io.fabric8.kubernetes.client.impl.BaseClient;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.impl.DefaultSharedIndexInformer;
import io.fabric8.kubernetes.client.informers.impl.ListerWatcher;
import io.fabric8.kubernetes.client.readiness.Readiness;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.URLUtils;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.client.utils.internal.CreateOrReplaceHelper;
import io.fabric8.kubernetes.client.utils.internal.WatcherToggle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseOperation extends CreateOnlyResourceOperation implements OperationInfo, MixedOperation, ExtensibleResource, ListerWatcher {
   static final Logger LOGGER = LoggerFactory.getLogger(BaseOperation.class);
   private static final String WATCH = "watch";
   private static final String READ_ONLY_UPDATE_EXCEPTION_MESSAGE = "Cannot update read-only resources";
   private static final String READ_ONLY_EDIT_EXCEPTION_MESSAGE = "Cannot edit read-only resources";
   private final HasMetadata item;
   private final String resourceVersion;
   private final long gracePeriodSeconds;
   private final DeletionPropagation propagationPolicy;
   protected String apiVersion;
   protected Class listType;
   private Map indexers;
   private Long limit;

   protected BaseOperation(OperationContext ctx) {
      super(ctx);
      this.item = (HasMetadata)ctx.getItem();
      this.resourceVersion = ctx.getResourceVersion();
      this.gracePeriodSeconds = ctx.getGracePeriodSeconds();
      this.propagationPolicy = ctx.getPropagationPolicy();
      this.apiVersion = ApiVersionUtil.joinApiGroupAndVersion(this.getAPIGroupName(), this.getAPIGroupVersion());
   }

   public BaseOperation newInstance(OperationContext context) {
      return new BaseOperation(context);
   }

   protected Resource newResource(OperationContext context) {
      return this.newInstance(context);
   }

   protected URL fetchListUrl(URL url, ListOptions listOptions) {
      return this.appendListOptionParams(url, listOptions);
   }

   public HasMetadata get() {
      try {
         return this.requireFromServer();
      } catch (KubernetesClientException e) {
         if (e.getCode() != 404) {
            throw e;
         } else {
            return null;
         }
      }
   }

   public HasMetadata require() {
      try {
         return this.requireFromServer();
      } catch (KubernetesClientException e) {
         throw new ResourceNotFoundException("Resource couldn't be fetched : " + e.getMessage(), e);
      }
   }

   public HasMetadata getItemOrRequireFromServer() {
      return this.item != null ? (HasMetadata)this.getKubernetesSerialization().clone(this.item) : this.requireFromServer();
   }

   protected HasMetadata requireFromServer() {
      if (Utils.isNullOrEmpty(this.getName())) {
         throw new KubernetesClientException("name not specified for an operation requiring one.");
      } else {
         try {
            URL requestUrl = this.getCompleteResourceUrl();
            return this.handleGet(requestUrl);
         } catch (IOException e) {
            throw KubernetesClientException.launderThrowable(this.forOperationType("get"), e);
         }
      }
   }

   public HasMetadata edit(UnaryOperator function) {
      throw new KubernetesClientException("Cannot edit read-only resources");
   }

   public HasMetadata editStatus(UnaryOperator function) {
      throw new KubernetesClientException("Cannot edit read-only resources");
   }

   public HasMetadata edit(Visitor... visitors) {
      throw new KubernetesClientException("Cannot edit read-only resources");
   }

   public HasMetadata edit(final Class visitorType, final Visitor visitor) {
      return this.edit(new TypedVisitor() {
         public Class getType() {
            return visitorType;
         }

         public void visit(Object item) {
            visitor.visit(item);
         }
      });
   }

   public HasMetadata accept(Consumer consumer) {
      throw new KubernetesClientException("Cannot edit read-only resources");
   }

   public Resource withName(String name) {
      if (name != null && name.length() != 0) {
         return this.newResource(this.context.withName(name));
      } else {
         throw new IllegalArgumentException("Name must be provided.");
      }
   }

   public ExtensibleResource lockResourceVersion(String resourceVersion) {
      if (resourceVersion == null) {
         throw new KubernetesClientException("resourceVersion cannot be null");
      } else {
         return this.newInstance(this.context.withResourceVersion(resourceVersion));
      }
   }

   public BaseOperation inNamespace(String namespace) {
      if (namespace == null) {
         throw new KubernetesClientException("namespace cannot be null");
      } else {
         return this.newInstance(this.context.withNamespace(namespace));
      }
   }

   public BaseOperation inAnyNamespace() {
      return this.newInstance(this.context.withNamespace((String)null));
   }

   public Resource load(InputStream is) {
      T unmarshal = (T)((HasMetadata)this.getKubernetesSerialization().unmarshal(is, this.type));
      unmarshal.setApiVersion(this.apiVersion);
      return this.resource(unmarshal);
   }

   public Resource load(URL url) {
      try {
         InputStream inputStream = url.openStream();

         Resource var3;
         try {
            var3 = this.load(inputStream);
         } catch (Throwable var6) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Throwable var5) {
                  var6.addSuppressed(var5);
               }
            }

            throw var6;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         return var3;
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public Resource load(File file) {
      try {
         FileInputStream fis = new FileInputStream(file);

         Resource var3;
         try {
            var3 = this.load((InputStream)fis);
         } catch (Throwable var6) {
            try {
               fis.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         fis.close();
         return var3;
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public Resource load(String path) {
      return this.load(new File(path));
   }

   public BaseOperation fromServer() {
      return this;
   }

   public final HasMetadata createOrReplace() {
      if (this.item == null) {
         throw new IllegalArgumentException("Nothing to create.");
      } else {
         R resource = (R)this.resource(this.item);
         Objects.requireNonNull(resource);
         UnaryOperator var10002 = resource::create;
         Objects.requireNonNull(resource);
         CreateOrReplaceHelper<T> createOrReplaceHelper = new CreateOrReplaceHelper(var10002, resource::replace, (m) -> (HasMetadata)resource.waitUntilCondition(Objects::nonNull, 1L, TimeUnit.SECONDS), (m) -> (HasMetadata)resource.fromServer().get(), this.getKubernetesSerialization());
         return createOrReplaceHelper.createOrReplace(this.item);
      }
   }

   public HasMetadata createOr(Function conflictAction) {
      try {
         return this.create();
      } catch (KubernetesClientException e) {
         if (e.getCode() == 409) {
            return (HasMetadata)conflictAction.apply(this);
         } else {
            throw e;
         }
      }
   }

   public ExtensibleResource unlock() {
      T current = (T)this.getItemOrRequireFromServer();
      if (current.getMetadata() != null) {
         current.getMetadata().setResourceVersion((String)null);
      }

      return this.newInstance(this.context.withItem(current));
   }

   public FilterWatchListDeletable withLabels(Map labels) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabels(labels)).endFilter();
   }

   public FilterWatchListDeletable withLabelSelector(LabelSelector selector) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabelSelector(selector)).endFilter();
   }

   public FilterWatchListDeletable withoutLabels(Map labels) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withoutLabels(labels)).endFilter();
   }

   public FilterWatchListDeletable withLabelIn(String key, String... values) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabelIn(key, values)).endFilter();
   }

   public FilterWatchListDeletable withLabelNotIn(String key, String... values) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabelNotIn(key, values)).endFilter();
   }

   public FilterWatchListDeletable withLabel(String key, String value) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabel(key, value)).endFilter();
   }

   public FilterWatchListDeletable withoutLabel(String key, String value) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withoutLabel(key, value)).endFilter();
   }

   public FilterWatchListDeletable withLabelSelector(String selectorAsString) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withLabelSelector(selectorAsString)).endFilter();
   }

   public FilterWatchListDeletable withFields(Map fields) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withFields(fields)).endFilter();
   }

   public FilterWatchListDeletable withField(String key, String value) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withField(key, value)).endFilter();
   }

   public FilterWatchListDeletable withInvolvedObject(ObjectReference objectReference) {
      return (FilterWatchListDeletable)(objectReference != null ? (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withInvolvedObject(objectReference)).endFilter() : this);
   }

   public FilterNested withNewFilter() {
      return new FilterNestedImpl(this);
   }

   public FilterWatchListDeletable withoutFields(Map fields) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withoutFields(fields)).endFilter();
   }

   public FilterWatchListDeletable withoutField(String key, String value) {
      return (FilterWatchListDeletable)((FilterNested)this.withNewFilter().withoutField(key, value)).endFilter();
   }

   public String getFieldQueryParam() {
      return this.context.getFieldQueryParam();
   }

   public KubernetesResourceList list() {
      return this.list(new ListOptions());
   }

   public KubernetesResourceList list(Integer limitVal, String continueVal) {
      return this.list(((ListOptionsBuilder)((ListOptionsBuilder)(new ListOptionsBuilder()).withLimit(limitVal.longValue())).withContinue(continueVal)).build());
   }

   public CompletableFuture submitList(ListOptions listOptions) {
      try {
         URL fetchListUrl = this.fetchListUrl(this.getNamespacedUrl(), this.defaultListOptions(listOptions, (Boolean)null));
         HttpRequest.Builder requestBuilder = this.withRequestTimeout(this.httpClient.newHttpRequestBuilder()).url(fetchListUrl);
         final Type refinedType = (Type)(this.listType.equals(DefaultKubernetesResourceList.class) ? this.getKubernetesSerialization().constructParametricType(this.listType, new Class[]{this.type}) : this.listType);
         TypeReference<L> listTypeReference = new TypeReference() {
            public Type getType() {
               return refinedType;
            }
         };
         CompletableFuture<L> futureAnswer = this.handleResponse(this.httpClient, requestBuilder, listTypeReference);
         return futureAnswer.thenApply(this::updateListItems);
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("list"), e);
      }
   }

   public KubernetesResourceList list(ListOptions listOptions) {
      try {
         return (KubernetesResourceList)this.waitForResult(this.submitList(listOptions));
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("list"), e);
      }
   }

   private ListOptions defaultListOptions(ListOptions options, Boolean watch) {
      options.setWatch(watch);
      String fieldQueryParam = this.context.getFieldQueryParam();
      if (fieldQueryParam != null) {
         options.setFieldSelector(fieldQueryParam);
      }

      String labelQueryParam = this.context.getLabelQueryParam();
      if (labelQueryParam != null) {
         options.setLabelSelector(labelQueryParam);
      }

      if (this.resourceVersion != null) {
         options.setResourceVersion(this.resourceVersion);
      }

      return options;
   }

   static void toStatusDetails(KubernetesResource obj, List details) {
      if (obj instanceof HasMetadata) {
         HasMetadata meta = (HasMetadata)obj;
         ObjectMeta metadata = meta.getMetadata();
         details.add(((StatusDetailsBuilder)((StatusDetailsBuilder)((StatusDetailsBuilder)((StatusDetailsBuilder)(new StatusDetailsBuilder()).withGroup(ApiVersionUtil.trimGroup(meta.getApiVersion()))).withKind(meta.getKind())).withName(metadata == null ? null : metadata.getName())).withUid(metadata == null ? null : metadata.getUid())).build());
      } else if (obj instanceof Status) {
         details.add(((Status)obj).getDetails());
      } else if (obj instanceof KubernetesResourceList) {
         List<HasMetadata> items = ((KubernetesResourceList)obj).getItems();
         if (items != null) {
            items.forEach((i) -> toStatusDetails(i, details));
         }
      }

   }

   public List delete() {
      List<StatusDetails> deleted = this.deleteAll();
      waitForDelete(deleted, this.context, this);
      return deleted;
   }

   static void waitForDelete(List deleted, OperationContext context, Waitable waitable) {
      if (context.getTimeout() > 0L) {
         Set<String> uids = (Set)deleted.stream().map(StatusDetails::getUid).collect(Collectors.toSet());
         waitable.waitUntilCondition((h) -> h == null || !uids.contains(h.getMetadata().getUid()), context.getTimeout(), context.getTimeoutUnit());
      }

   }

   protected List deleteAll() {
      if (!Utils.isNotNullOrEmpty(this.name) && !Utils.isNotNullOrEmpty(this.namespace) && this.isResourceNamespaced()) {
         Set<String> namespaces = (Set)this.list().getItems().stream().map((i) -> i.getMetadata().getNamespace()).collect(Collectors.toSet());
         return (List)namespaces.stream().flatMap((n) -> this.inNamespace(n).delete().stream()).collect(Collectors.toList());
      } else {
         try {
            URL resourceURLForWriteOperation = this.getResourceURLForWriteOperation(this.getResourceUrl());
            ListOptions options = new ListOptions();
            boolean useOptions = false;
            if (Utils.isNullOrEmpty(this.name)) {
               String fieldQueryParam = this.context.getFieldQueryParam();
               if (fieldQueryParam != null) {
                  options.setFieldSelector(fieldQueryParam);
                  useOptions = true;
               }

               String labelQueryParam = this.context.getLabelQueryParam();
               if (labelQueryParam != null) {
                  options.setLabelSelector(labelQueryParam);
                  useOptions = true;
               }
            }

            if (useOptions) {
               resourceURLForWriteOperation = this.appendListOptionParams(resourceURLForWriteOperation, options);
            }

            KubernetesResource result = this.handleDelete(resourceURLForWriteOperation, this.gracePeriodSeconds, this.propagationPolicy, this.resourceVersion);
            ArrayList<StatusDetails> details = new ArrayList();
            toStatusDetails(result, details);
            return details;
         } catch (Exception e) {
            RuntimeException re = KubernetesClientException.launderThrowable(this.forOperationType("delete"), e);
            if (re instanceof KubernetesClientException) {
               KubernetesClientException ke = (KubernetesClientException)re;
               if (Utils.isNotNullOrEmpty(this.name)) {
                  if (ke.getCode() == 404) {
                     return Collections.emptyList();
                  }
               } else if (ke.getCode() == 405) {
                  return (List)this.list().getItems().stream().flatMap((i) -> this.resource(i).delete().stream()).collect(Collectors.toList());
               }
            }

            throw re;
         }
      }
   }

   public boolean delete(List items) {
      if (items != null) {
         for(HasMetadata toDelete : items) {
            if (toDelete != null) {
               try {
                  this.resource(toDelete).delete();
               } catch (KubernetesClientException e) {
                  if (e.getCode() != 404) {
                     throw e;
                  }
               }
            }
         }
      }

      return true;
   }

   public HasMetadata patchStatus(HasMetadata item) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata patchStatus() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata patch() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata patch(PatchContext patchContext) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   protected HasMetadata getNonNullItem() {
      T result = (T)this.getItem();
      if (result == null) {
         throw new KubernetesClientException("item required");
      } else {
         return result;
      }
   }

   public Resource resource(HasMetadata item) {
      item = (T)((HasMetadata)this.correctNamespace(item));
      this.updateApiVersion(item);
      String itemNs = KubernetesResourceUtil.getNamespace(item);
      OperationContext ctx = this.context.withName(this.checkName(item)).withItem(item);
      if (Utils.isNotNullOrEmpty(itemNs)) {
         ctx = ctx.withNamespace(itemNs);
      }

      return this.newResource(ctx);
   }

   public BaseOperation withResourceVersion(String resourceVersion) {
      return this.newInstance(this.context.withResourceVersion(resourceVersion));
   }

   public Watch watch(Watcher watcher) {
      return this.watch(new ListOptions(), watcher);
   }

   public Watch watch(String resourceVersion, Watcher watcher) {
      return this.watch(((ListOptionsBuilder)(new ListOptionsBuilder()).withResourceVersion(resourceVersion)).build(), watcher);
   }

   public Watch watch(ListOptions options, Watcher watcher) {
      CompletableFuture<? extends Watch> startedFuture = this.submitWatch(options, watcher);
      Utils.waitUntilReadyOrFail(startedFuture, -1L, TimeUnit.SECONDS);
      return (Watch)startedFuture.join();
   }

   public CompletableFuture submitWatch(ListOptions options, Watcher watcher) {
      ListOptions optionsToUse = this.defaultListOptions(options, true);
      if (this.getConfig().isOnlyHttpWatches()) {
         return CompletableFuture.completedFuture(this.httpWatch(watcher, optionsToUse));
      } else {
         WatcherToggle<T> watcherToggle = new WatcherToggle(watcher, true);

         WatchConnectionManager<T, L> watch;
         try {
            watch = new WatchConnectionManager(this.httpClient, this, optionsToUse, watcherToggle, this.getRequestConfig().getWatchReconnectInterval(), this.getRequestConfig().getWatchReconnectLimit(), (long)this.getRequestConfig().getRequestTimeout());
         } catch (MalformedURLException e) {
            throw KubernetesClientException.launderThrowable(this.forOperationType("watch"), e);
         }

         return watch.getWebsocketFuture().handle((w, t) -> {
            if (t == null) {
               return watch;
            } else {
               AbstractWatchManager var13;
               try {
                  if (t instanceof CompletionException) {
                     t = t.getCause();
                  }

                  boolean httpWatch = false;
                  if (t instanceof KubernetesClientException) {
                     KubernetesClientException ke = (KubernetesClientException)t;
                     List<Integer> furtherProcessedCodes = Arrays.asList(200, 503);
                     if (furtherProcessedCodes.contains(ke.getCode())) {
                        LOGGER.debug("Websocket hanshake failed with code {}, but an httpwatch may be possible.  Use Config.onlyHttpWatches to disable websocket watches.", ke.getCode());
                        httpWatch = true;
                     }
                  } else {
                     LOGGER.debug("Failed to establish a websocket watch, will try regular http instead.  Use Config.onlyHttpWatches to disable websocket watches.", t);
                     httpWatch = true;
                  }

                  if (!httpWatch) {
                     throw KubernetesClientException.launderThrowable(t);
                  }

                  watcherToggle.disable();
                  var13 = this.httpWatch(watcher, optionsToUse);
               } finally {
                  watch.close();
               }

               return var13;
            }
         });
      }
   }

   private AbstractWatchManager httpWatch(Watcher watcher, ListOptions optionsToUse) {
      try {
         return new WatchHTTPManager(this.httpClient, this, optionsToUse, watcher, this.getRequestConfig().getWatchReconnectInterval(), this.getRequestConfig().getWatchReconnectLimit());
      } catch (MalformedURLException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("watch"), e);
      }
   }

   public HasMetadata replace() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata replaceStatus() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata patch(PatchContext patchContext, String patch) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata patch(PatchContext patchContext, HasMetadata item) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public boolean isResourceNamespaced() {
      return Utils.isResourceNamespaced(this.getType());
   }

   protected HasMetadata handleResponse(HttpRequest.Builder requestBuilder) throws InterruptedException, IOException {
      return (HasMetadata)this.handleResponse(requestBuilder, this.getType());
   }

   protected HasMetadata handleCreate(HasMetadata resource) throws InterruptedException, IOException {
      this.updateApiVersion(resource);
      return (HasMetadata)this.handleCreate(resource, this.getType());
   }

   protected HasMetadata handleUpdate(HasMetadata updated) throws InterruptedException, IOException {
      this.updateApiVersion(updated);
      return (HasMetadata)this.handleUpdate(updated, this.getType());
   }

   protected HasMetadata handlePatch(PatchContext context, HasMetadata current, HasMetadata updated) throws InterruptedException, IOException {
      this.updateApiVersion(updated);
      return (HasMetadata)this.handlePatch(context, current, updated, this.getType());
   }

   public Object handleScale(Object scaleParam, Class scaleType) {
      try {
         return this.handleScale(this.getCompleteResourceUrl().toString(), scaleParam, scaleType);
      } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(this.forOperationType("scale"), ie);
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("scale"), e);
      }
   }

   protected Status handleDeploymentRollback(DeploymentRollback deploymentRollback) {
      try {
         return this.handleDeploymentRollback(this.getCompleteResourceUrl().toString(), deploymentRollback);
      } catch (InterruptedException ie) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(this.forOperationType("rollback"), ie);
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("rollback"), e);
      }
   }

   protected HasMetadata handleGet(URL resourceUrl) throws IOException {
      T answer = (T)((HasMetadata)this.handleGet(resourceUrl, this.getType()));
      this.updateApiVersion(answer);
      return answer;
   }

   private URL getCompleteResourceUrl() throws MalformedURLException {
      URL requestUrl = this.getNamespacedUrl(this.checkNamespace(this.item));
      if (this.name != null) {
         requestUrl = new URL(URLUtils.join(new String[]{requestUrl.toString(), this.name}));
      }

      return requestUrl;
   }

   public HasMetadata item() {
      return this.getItem();
   }

   public final HasMetadata getItem() {
      return this.item;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public Long getGracePeriodSeconds() {
      return this.gracePeriodSeconds;
   }

   public DeletionPropagation getPropagationPolicy() {
      return this.propagationPolicy;
   }

   public Class getListType() {
      return this.listType;
   }

   public String getKind() {
      return this.type != null ? HasMetadata.getKind(this.type) : "Resource";
   }

   public String getGroup() {
      return this.getAPIGroupName();
   }

   public String getPlural() {
      return this.getResourceT();
   }

   public String getVersion() {
      return this.getAPIGroupVersion();
   }

   public String getOperationType() {
      return null;
   }

   public OperationInfo forOperationType(String type) {
      return new DefaultOperationInfo(this.getKind(), type, this.name, this.namespace, this.getGroup(), this.getPlural(), this.getVersion());
   }

   public ExtensibleResource withGracePeriod(long gracePeriodSeconds) {
      return this.newInstance(this.context.withGracePeriodSeconds(gracePeriodSeconds));
   }

   public ExtensibleResource withPropagationPolicy(DeletionPropagation propagationPolicy) {
      return this.newInstance(this.context.withPropagationPolicy(propagationPolicy));
   }

   protected Class getConfigType() {
      return Config.class;
   }

   protected KubernetesResourceList updateListItems(KubernetesResourceList list) {
      if (list != null && list.getItems() != null) {
         boolean updateApiVersion = Utils.isNotNullOrEmpty(this.apiVersion);
         boolean updateKind = GenericKubernetesResource.class.isAssignableFrom(this.getType());
         if (updateApiVersion || updateKind) {
            for(HasMetadata item : list.getItems()) {
               this.updateApiVersion(item);
               if (updateKind && item != null && item.getKind() == null) {
                  ((GenericKubernetesResource)item).setKind(this.getKind());
               }
            }
         }
      }

      return list;
   }

   protected void updateApiVersion(HasMetadata hasMetadata) {
      String version = this.apiVersion;
      if (hasMetadata != null && Utils.isNotNullOrEmpty(version)) {
         String current = hasMetadata.getApiVersion();
         if (current == null || "v1".equals(current) || current.indexOf(47) < 0 && version.indexOf(47) > 0) {
            hasMetadata.setApiVersion(version);
         }
      }

   }

   public Readiness getReadiness() {
      return this.config.getReadiness();
   }

   public final boolean isReady() {
      T item = (T)this.get();
      return item == null ? false : this.getReadiness().isReady(item);
   }

   public HasMetadata waitUntilReady(long amount, TimeUnit timeUnit) {
      return this.waitUntilCondition((resource) -> Objects.nonNull(resource) && this.getReadiness().isReady(resource), amount, timeUnit);
   }

   public HasMetadata waitUntilCondition(Predicate condition, long amount, TimeUnit timeUnit) {
      CompletableFuture<List<T>> futureCondition = this.informOnCondition((l) -> l.isEmpty() ? condition.test((Object)null) : l.stream().allMatch(condition));
      if (!Utils.waitUntilReady(futureCondition, amount, timeUnit)) {
         futureCondition.cancel(true);
         T i = (T)this.getItem();
         if (i != null) {
            throw new KubernetesClientTimeoutException(i, amount, timeUnit);
         } else {
            throw new KubernetesClientTimeoutException(this.getKind(), this.getName(), this.getNamespace(), amount, timeUnit);
         }
      } else {
         return (HasMetadata)futureCondition.thenApply((l) -> l.isEmpty() ? null : (HasMetadata)l.get(0)).getNow((Object)null);
      }
   }

   public CompletableFuture informOnCondition(Predicate condition) {
      CompletableFuture<List<T>> future = new CompletableFuture();
      final SharedIndexInformer<T> informer = this.createInformer(0L, Runnable::run);
      informer.initialState(Stream.empty());
      future.whenComplete((r, t) -> informer.stop());
      final Consumer<List<T>> test = (list) -> {
         try {
            if (condition.test(list)) {
               future.complete(list);
            }
         } catch (Exception e) {
            future.completeExceptionally(e);
         }

      };
      informer.addEventHandler(new ResourceEventHandler() {
         public void onAdd(HasMetadata obj) {
            test.accept(informer.getStore().list());
         }

         public void onDelete(HasMetadata obj, boolean deletedFinalStateUnknown) {
            test.accept(informer.getStore().list());
         }

         public void onUpdate(HasMetadata oldObj, HasMetadata newObj) {
            test.accept(informer.getStore().list());
         }

         public void onNothing() {
            test.accept(informer.getStore().list());
         }
      }).start().whenComplete((v, t) -> {
         if (t != null) {
            future.completeExceptionally(t);
         }

      });
      informer.stopped().whenComplete((v, t) -> {
         if (t != null) {
            future.completeExceptionally(t);
         } else {
            future.completeExceptionally(new KubernetesClientException("Informer was stopped"));
         }

      });
      return future;
   }

   public void setType(Class type) {
      this.type = type;
   }

   public void setListType(Class listType) {
      this.listType = listType;
   }

   public ExtensibleResource dryRun(boolean isDryRun) {
      return this.newInstance(this.context.withDryRun(isDryRun));
   }

   public ExtensibleResource fieldValidation(FieldValidateable.Validation fieldValidation) {
      return this.newInstance(this.context.withFieldValidation(fieldValidation));
   }

   public ExtensibleResource withIndexers(Map indexers) {
      BaseOperation<T, L, R> result = this.newInstance(this.context);
      result.indexers = indexers;
      result.limit = this.limit;
      return result;
   }

   public BaseOperation withLimit(Long limit) {
      BaseOperation<T, L, R> result = this.newInstance(this.context);
      result.indexers = this.indexers;
      result.limit = limit;
      return result;
   }

   public Long getLimit() {
      return this.limit;
   }

   public SharedIndexInformer inform(ResourceEventHandler handler, long resync) {
      SharedIndexInformer<T> result = this.runnableInformer(resync);
      if (handler != null) {
         result.addEventHandler(handler);
      }

      result.run();
      return result;
   }

   public SharedIndexInformer runnableInformer(long resync) {
      return this.createInformer(resync, this.context.getExecutor());
   }

   private DefaultSharedIndexInformer createInformer(long resync, Executor executor) {
      T i = (T)this.getItem();
      if (Utils.isNotNullOrEmpty(this.getName()) && i != null) {
         this.checkName(i);
      }

      DefaultSharedIndexInformer<T, L> informer = new DefaultSharedIndexInformer(this.getType(), this.withResourceVersion((String)null).withLimit(this.limit), resync, executor);
      if (this.indexers != null) {
         informer.addIndexers(this.indexers);
      }

      informer.started().whenComplete((ignored, throwable) -> {
         if (throwable == null) {
            BaseClient baseClient = (BaseClient)this.context.getClient().adapt(BaseClient.class);
            baseClient.addToCloseable(informer);
            informer.stopped().whenComplete((x, y) -> baseClient.removeFromCloseable(informer));
         }

      });
      return informer;
   }

   public URL appendListOptionParams(URL base, ListOptions listOptions) {
      if (listOptions == null) {
         return base;
      } else {
         URLUtils.URLBuilder urlBuilder = new URLUtils.URLBuilder(base);
         Map<String, ?> values = (Map)this.getKubernetesSerialization().convertValue(listOptions, TreeMap.class);
         values.remove("apiVersion");
         values.remove("kind");
         values.forEach((k, v) -> urlBuilder.addQueryParameter(k, v.toString()));
         return urlBuilder.build();
      }
   }

   public Client inWriteContext(Class clazz) {
      return this.context.clientInWriteContext(clazz);
   }

   public int getWatchReconnectInterval() {
      return this.config.getWatchReconnectInterval();
   }

   public Stream resources() {
      return this.list().getItems().stream().map(this::resource);
   }

   public HasMetadata createOrReplace(HasMetadata item) {
      return (HasMetadata)this.resource(item).createOrReplace();
   }

   public HasMetadata replace(HasMetadata item) {
      return (HasMetadata)this.resource(item).replace();
   }

   public HasMetadata replaceStatus(HasMetadata item) {
      return (HasMetadata)this.resource(item).replaceStatus();
   }

   public List delete(HasMetadata item) {
      return this.resource(item).delete();
   }

   public ExtensibleResource dryRun() {
      return this.dryRun(true);
   }

   public ExtensibleResource lockResourceVersion() {
      return this.lockResourceVersion(KubernetesResourceUtil.getResourceVersion(this.getNonNullItem()));
   }

   public HasMetadata updateStatus(HasMetadata item) {
      return (HasMetadata)this.resource(item).updateStatus();
   }

   public HasMetadata create() {
      return (HasMetadata)this.create(this.getNonNullItem());
   }

   public String getApiEndpointPath() {
      List<String> parts = this.getRootUrlParts();
      parts.remove(0);
      parts.remove(0);
      this.addNamespacedUrlPathParts(parts, this.namespace, this.resourceT);
      return (String)parts.stream().collect(Collectors.joining("/"));
   }

   public ExtensibleResource fieldManager(String manager) {
      return this.newInstance(this.context.withFieldManager(manager));
   }

   public ExtensibleResource forceConflicts() {
      return this.newInstance(this.context.withForceConflicts());
   }

   public HasMetadata serverSideApply() {
      return this.patch(PatchContext.of(PatchType.SERVER_SIDE_APPLY));
   }

   public ExtensibleResource withTimeout(long timeout, TimeUnit unit) {
      return this.newInstance(this.context.withTimeout(timeout, unit));
   }

   public HasMetadata updateStatus() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata update() {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata scale(int count) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public HasMetadata scale(int count, boolean wait) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public Scale scale(Scale scale) {
      throw new KubernetesClientException("Cannot update read-only resources");
   }

   public ExtensibleResource subresource(String subresource) {
      return this.newInstance(this.context.withSubresource(subresource));
   }
}
