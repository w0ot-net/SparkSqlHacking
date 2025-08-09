package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.RequestConfigBuilder;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import io.fabric8.kubernetes.client.http.HttpClient;
import io.fabric8.kubernetes.client.impl.BaseClient;
import io.fabric8.kubernetes.client.impl.ResourceHandler;
import io.fabric8.kubernetes.client.utils.ApiVersionUtil;
import io.fabric8.kubernetes.client.utils.Utils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class OperationContext {
   protected Object item;
   protected String resourceVersion;
   protected String plural;
   protected String apiGroupName;
   protected String apiGroupVersion;
   protected String namespace;
   protected boolean defaultNamespace;
   protected String name;
   protected String subresource;
   protected boolean dryRun;
   protected FieldValidateable.Validation fieldValidation;
   protected String fieldManager;
   protected Boolean forceConflicts;
   protected long gracePeriodSeconds;
   protected DeletionPropagation propagationPolicy;
   protected Map labels;
   protected Map labelsNot;
   protected Map labelsIn;
   protected Map labelsNotIn;
   protected Map fields;
   protected Map fieldsNot;
   protected String selectorAsString;
   protected Client client;
   protected RequestConfig requestConfig;
   private long timeout;
   private TimeUnit timeoutUnit;
   private static final BiFunction asKeyValuePair = (key, value) -> value != null ? key + "=" + value : key;
   private static final BiFunction asNotEqualToValues = (key, values) -> Utils.isNotNull(values) ? (String)Arrays.stream(values).map((v) -> key + "!=" + v).collect(Collectors.joining(",")) : "!" + key;
   private static final BiFunction asInSet = getEntryProcessorFor("in");
   private static final BiFunction asNotInSet = getEntryProcessorFor("notin");

   public OperationContext() {
      this.defaultNamespace = true;
      this.gracePeriodSeconds = -1L;
      this.timeoutUnit = TimeUnit.MILLISECONDS;
   }

   public OperationContext(OperationContext other) {
      this(other.client, other.plural, other.namespace, other.name, other.subresource, other.apiGroupName, other.apiGroupVersion, other.item, other.labels, other.labelsNot, other.labelsIn, other.labelsNotIn, other.fields, other.fieldsNot, other.resourceVersion, other.gracePeriodSeconds, other.propagationPolicy, other.dryRun, other.selectorAsString, other.defaultNamespace, other.fieldValidation, other.fieldManager, other.forceConflicts, other.timeout, other.timeoutUnit, other.requestConfig);
   }

   public OperationContext(Client client, String plural, String namespace, String name, String subresource, String apiGroupName, String apiGroupVersion, Object item, Map labels, Map labelsNot, Map labelsIn, Map labelsNotIn, Map fields, Map fieldsNot, String resourceVersion, long gracePeriodSeconds, DeletionPropagation propagationPolicy, boolean dryRun, String selectorAsString, boolean defaultNamespace, FieldValidateable.Validation fieldValidation, String fieldManager, Boolean forceConflicts, long timeout, TimeUnit timeoutUnit, RequestConfig requestConfig) {
      this.defaultNamespace = true;
      this.gracePeriodSeconds = -1L;
      this.timeoutUnit = TimeUnit.MILLISECONDS;
      this.client = client;
      this.item = item;
      this.plural = plural;
      this.setNamespace(namespace, defaultNamespace);
      this.name = name;
      this.subresource = subresource;
      this.setApiGroupName(apiGroupName);
      this.setApiGroupVersion(apiGroupVersion);
      this.setLabels(labels);
      this.setLabelsNot(labelsNot);
      this.setLabelsIn(labelsIn);
      this.setLabelsNotIn(labelsNotIn);
      this.setFields(fields);
      this.setFieldsNot(fieldsNot);
      this.resourceVersion = resourceVersion;
      this.gracePeriodSeconds = gracePeriodSeconds;
      this.propagationPolicy = propagationPolicy;
      this.dryRun = dryRun;
      this.selectorAsString = selectorAsString;
      this.fieldValidation = fieldValidation;
      this.fieldManager = fieldManager;
      this.forceConflicts = forceConflicts;
      this.timeout = timeout;
      this.timeoutUnit = timeoutUnit;
      this.requestConfig = requestConfig == null ? null : (new RequestConfigBuilder(requestConfig)).build();
   }

   private void setFieldsNot(Map fieldsNot) {
      this.fieldsNot = (Map)Utils.getNonNullOrElse(fieldsNot, new HashMap());
   }

   private void setFields(Map fields) {
      this.fields = (Map)Utils.getNonNullOrElse(fields, new HashMap());
   }

   private void setLabelsNotIn(Map labelsNotIn) {
      this.labelsNotIn = (Map)Utils.getNonNullOrElse(labelsNotIn, new HashMap());
   }

   private void setLabelsIn(Map labelsIn) {
      this.labelsIn = (Map)Utils.getNonNullOrElse(labelsIn, new HashMap());
   }

   private void setLabelsNot(Map labelsNot) {
      this.labelsNot = (Map)Utils.getNonNullOrElse(labelsNot, new HashMap());
   }

   private void setLabels(Map labels) {
      this.labels = (Map)Utils.getNonNullOrElse(labels, new HashMap());
   }

   private void setApiGroupVersion(String apiGroupVersion) {
      this.apiGroupVersion = ApiVersionUtil.apiVersion(this.item, apiGroupVersion);
   }

   private void setApiGroupName(String apiGroupName) {
      this.apiGroupName = ApiVersionUtil.apiGroup(this.item, apiGroupName);
   }

   private void setNamespace(String namespace, boolean defaultNamespace) {
      Config config = this.getConfig();
      if (defaultNamespace && !Utils.isNotNullOrEmpty(namespace)) {
         if (config != null) {
            this.namespace = config.getNamespace();
            this.defaultNamespace = config.isDefaultNamespace();
         }
      } else {
         this.namespace = namespace;
         this.defaultNamespace = defaultNamespace;
      }

   }

   public Client getClient() {
      return this.client;
   }

   public HttpClient getHttpClient() {
      return this.client == null ? null : this.client.getHttpClient();
   }

   public Config getConfig() {
      return this.client == null ? null : this.client.getConfiguration();
   }

   public RequestConfig getRequestConfig() {
      return this.requestConfig;
   }

   public String getPlural() {
      return this.plural;
   }

   public String getNamespace() {
      return this.namespace;
   }

   public boolean isDefaultNamespace() {
      return this.defaultNamespace;
   }

   public String getName() {
      return this.name;
   }

   public String getSubresource() {
      return this.subresource;
   }

   public String getApiGroupName() {
      return this.apiGroupName;
   }

   public String getApiGroupVersion() {
      return this.apiGroupVersion;
   }

   public Object getItem() {
      return this.item;
   }

   public Map getLabels() {
      return this.labels;
   }

   public Map getLabelsNot() {
      return this.labelsNot;
   }

   public Map getLabelsIn() {
      return this.labelsIn;
   }

   public Map getLabelsNotIn() {
      return this.labelsNotIn;
   }

   public Map getFields() {
      return this.fields;
   }

   public Map getFieldsNot() {
      return this.fieldsNot;
   }

   public String getResourceVersion() {
      return this.resourceVersion;
   }

   public long getGracePeriodSeconds() {
      return this.gracePeriodSeconds;
   }

   public DeletionPropagation getPropagationPolicy() {
      return this.propagationPolicy;
   }

   public boolean getDryRun() {
      return this.dryRun;
   }

   public long getTimeout() {
      return this.timeout;
   }

   public TimeUnit getTimeoutUnit() {
      return this.timeoutUnit;
   }

   public String getLabelQueryParam() {
      if (Utils.isNotNullOrEmpty(this.selectorAsString)) {
         return this.selectorAsString;
      } else {
         StringBuilder sb = new StringBuilder(101);
         this.appendEntriesAsParam(sb, this.getLabels(), asKeyValuePair);
         this.appendEntriesAsParam(sb, this.getLabelsNot(), asNotEqualToValues);
         this.appendEntriesAsParam(sb, this.getLabelsIn(), asInSet);
         this.appendEntriesAsParam(sb, this.getLabelsNotIn(), asNotInSet);
         return sb.length() > 0 ? sb.toString() : null;
      }
   }

   private static BiFunction getEntryProcessorFor(String operator) {
      return (key, values) -> key + " " + operator + " (" + String.join(",", values) + ")";
   }

   private void appendEntriesAsParam(StringBuilder sb, Map entries, BiFunction entryProcessor) {
      if (entries != null && !entries.isEmpty()) {
         if (sb.length() > 0) {
            sb.append(",");
         }

         sb.append((String)entries.entrySet().stream().map((entry) -> (String)entryProcessor.apply((String)entry.getKey(), entry.getValue())).collect(Collectors.joining(",")));
      }

   }

   public String getFieldQueryParam() {
      StringBuilder sb = new StringBuilder(101);
      if (Utils.isNotNullOrEmpty(this.getName())) {
         sb.append("metadata.name").append("=").append(this.getName());
      }

      this.appendEntriesAsParam(sb, this.getFields(), asKeyValuePair);
      this.appendEntriesAsParam(sb, this.getFieldsNot(), asNotEqualToValues);
      return sb.length() > 0 ? sb.toString() : null;
   }

   public OperationContext copy() {
      return new OperationContext(this);
   }

   public OperationContext withClient(Client client) {
      if (this.client == client) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.client = client;
         return context;
      }
   }

   public OperationContext withPlural(String plural) {
      if (Objects.equals(this.plural, plural)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.plural = plural;
         return context;
      }
   }

   public OperationContext withNamespace(String namespace) {
      if (Objects.equals(this.namespace, namespace) && !this.defaultNamespace) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setNamespace(namespace, false);
         return context;
      }
   }

   public OperationContext withName(String name) {
      if (Objects.equals(this.name, name)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.name = name;
         return context;
      }
   }

   public OperationContext withSubresource(String subresource) {
      if (Objects.equals(this.subresource, subresource)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.subresource = subresource;
         return context;
      }
   }

   public OperationContext withApiGroupName(String apiGroupName) {
      if (Objects.equals(this.apiGroupName, apiGroupName)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setApiGroupName(apiGroupName);
         return context;
      }
   }

   public OperationContext withApiGroupVersion(String apiGroupVersion) {
      if (Objects.equals(this.apiGroupVersion, apiGroupVersion)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setApiGroupVersion(apiGroupVersion);
         return context;
      }
   }

   public OperationContext withItem(Object item) {
      if (this.item == item) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.item = item;
         return context;
      }
   }

   public OperationContext withLabels(Map labels) {
      if (Objects.equals(this.labels, labels)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setLabels(labels);
         return context;
      }
   }

   public OperationContext withLabelsIn(Map labelsIn) {
      if (Objects.equals(this.labelsIn, labelsIn)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setLabelsIn(labelsIn);
         return context;
      }
   }

   public OperationContext withLabelsNot(Map labelsNot) {
      if (Objects.equals(this.labelsNot, labelsNot)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setLabelsNot(labelsNot);
         return context;
      }
   }

   public OperationContext withLabelsNotIn(Map labelsNotIn) {
      if (Objects.equals(this.labelsNotIn, labelsNotIn)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setLabelsNotIn(labelsNotIn);
         return context;
      }
   }

   public OperationContext withFields(Map fields) {
      if (Objects.equals(this.fields, fields)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setFields(fields);
         return context;
      }
   }

   public OperationContext withFieldsNot(Map fieldsNot) {
      if (Objects.equals(this.fieldsNot, fieldsNot)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.setFieldsNot(fieldsNot);
         return context;
      }
   }

   public OperationContext withResourceVersion(String resourceVersion) {
      if (Objects.equals(this.resourceVersion, resourceVersion)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.resourceVersion = resourceVersion;
         return context;
      }
   }

   public OperationContext withGracePeriodSeconds(long gracePeriodSeconds) {
      if (this.gracePeriodSeconds == gracePeriodSeconds) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.gracePeriodSeconds = gracePeriodSeconds;
         return context;
      }
   }

   public OperationContext withPropagationPolicy(DeletionPropagation propagationPolicy) {
      if (this.propagationPolicy == propagationPolicy) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.propagationPolicy = propagationPolicy;
         return context;
      }
   }

   public OperationContext withDryRun(boolean dryRun) {
      if (this.dryRun == dryRun) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.dryRun = dryRun;
         return context;
      }
   }

   public OperationContext withLabelSelector(String selectorAsString) {
      if (Objects.equals(this.selectorAsString, selectorAsString)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.selectorAsString = selectorAsString;
         return context;
      }
   }

   ResourceHandler getHandler(HasMetadata item) {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getHandlers().get(item, this.getClient());
   }

   public Client clientInWriteContext(Class clazz) {
      OperationContext newContext = HasMetadataOperationsImpl.defaultContext(this.client).withDryRun(this.getDryRun()).withGracePeriodSeconds(this.getGracePeriodSeconds()).withPropagationPolicy(this.getPropagationPolicy()).withFieldValidation(this.fieldValidation).withFieldManager(this.fieldManager);
      if (Boolean.TRUE.equals(this.forceConflicts)) {
         newContext = newContext.withForceConflicts();
      }

      if (!Objects.equals(this.getNamespace(), newContext.getNamespace()) || this.isDefaultNamespace() ^ newContext.isDefaultNamespace()) {
         newContext = newContext.withNamespace(this.getNamespace());
      }

      return ((BaseClient)this.getClient().adapt(BaseClient.class)).newClient(newContext, clazz);
   }

   public Executor getExecutor() {
      return ((BaseClient)this.getClient().adapt(BaseClient.class)).getExecutor();
   }

   public OperationContext withFieldValidation(FieldValidateable.Validation fieldValidation) {
      if (this.fieldValidation == fieldValidation) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.fieldValidation = fieldValidation;
         return context;
      }
   }

   public OperationContext withFieldManager(String fieldManager) {
      if (Objects.equals(fieldManager, this.fieldManager)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.fieldManager = fieldManager;
         return context;
      }
   }

   public OperationContext withForceConflicts() {
      if (Boolean.TRUE.equals(this.forceConflicts)) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.forceConflicts = true;
         return context;
      }
   }

   public OperationContext withTimeout(long timeout, TimeUnit timeUnit) {
      OperationContext context = new OperationContext(this);
      context.timeout = timeout;
      context.timeoutUnit = timeUnit == null ? TimeUnit.MILLISECONDS : timeUnit;
      return context;
   }

   public OperationContext withRequestConfig(RequestConfig requestConfig) {
      if (requestConfig == this.requestConfig) {
         return this;
      } else {
         OperationContext context = new OperationContext(this);
         context.requestConfig = requestConfig;
         return context;
      }
   }
}
