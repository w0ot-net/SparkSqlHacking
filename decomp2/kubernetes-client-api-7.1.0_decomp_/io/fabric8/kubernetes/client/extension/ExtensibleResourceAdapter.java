package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public abstract class ExtensibleResourceAdapter extends ResourceAdapter implements ExtensibleResource {
   protected ExtensibleResource resource;
   protected Client client;

   public abstract ExtensibleResourceAdapter newInstance();

   public ExtensibleResourceAdapter init(ExtensibleResource resource, Client client) {
      super.resource = resource;
      this.resource = resource;
      this.client = client;
      return this;
   }

   public ExtensibleResource lockResourceVersion(String resourceVersion) {
      return this.newInstance().init(this.resource.lockResourceVersion(resourceVersion), this.client);
   }

   public ExtensibleResource withResourceVersion(String resourceVersion) {
      return this.newInstance().init(this.resource.withResourceVersion(resourceVersion), this.client);
   }

   public ExtensibleResource fromServer() {
      return this.newInstance().init(this.resource.fromServer(), this.client);
   }

   public ExtensibleResource withGracePeriod(long gracePeriodSeconds) {
      return this.newInstance().init(this.resource.withGracePeriod(gracePeriodSeconds), this.client);
   }

   public ExtensibleResource withPropagationPolicy(DeletionPropagation propagationPolicy) {
      return this.newInstance().init(this.resource.withPropagationPolicy(propagationPolicy), this.client);
   }

   public ExtensibleResource withIndexers(Map indexers) {
      return this.newInstance().init(this.resource.withIndexers(indexers), this.client);
   }

   public ExtensibleResource dryRun(boolean isDryRun) {
      return this.newInstance().init(this.resource.dryRun(isDryRun), this.client);
   }

   public ExtensibleResource withLimit(Long limit) {
      return this.newInstance().init(this.resource.withLimit(limit), this.client);
   }

   public Client inWriteContext(Class clazz) {
      return this.resource.inWriteContext(clazz);
   }

   public ExtensibleResource lockResourceVersion() {
      return this.newInstance().init(this.resource.lockResourceVersion(), this.client);
   }

   public Object getItem() {
      return this.resource.getItem();
   }

   public ExtensibleResource fieldValidation(FieldValidateable.Validation fieldValidation) {
      return this.newInstance().init(this.resource.fieldValidation(fieldValidation), this.client);
   }

   public ExtensibleResource fieldManager(String manager) {
      return this.newInstance().init(this.resource.fieldManager(manager), this.client);
   }

   public ExtensibleResource forceConflicts() {
      return this.newInstance().init(this.resource.forceConflicts(), this.client);
   }

   public ExtensibleResource withTimeout(long timeout, TimeUnit unit) {
      return this.newInstance().init(this.resource.withTimeout(timeout, unit), this.client);
   }

   public ExtensibleResource withTimeoutInMillis(long timeoutInMillis) {
      return this.withTimeout(timeoutInMillis, TimeUnit.MILLISECONDS);
   }

   public ExtensibleResource unlock() {
      return this.newInstance().init(this.resource.unlock(), this.client);
   }

   public ExtensibleResource subresource(String subresource) {
      return this.newInstance().init(this.resource.subresource(subresource), this.client);
   }
}
