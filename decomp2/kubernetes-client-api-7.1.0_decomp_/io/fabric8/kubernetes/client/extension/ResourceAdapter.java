package io.fabric8.kubernetes.client.extension;

import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.ListOptions;
import io.fabric8.kubernetes.api.model.autoscaling.v1.Scale;
import io.fabric8.kubernetes.client.GracePeriodConfigurable;
import io.fabric8.kubernetes.client.PropagationPolicyConfigurable;
import io.fabric8.kubernetes.client.ResourceNotFoundException;
import io.fabric8.kubernetes.client.Watch;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.EditReplacePatchable;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import io.fabric8.kubernetes.client.dsl.Gettable;
import io.fabric8.kubernetes.client.dsl.Informable;
import io.fabric8.kubernetes.client.dsl.NonDeletingOperation;
import io.fabric8.kubernetes.client.dsl.ReplaceDeletable;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.ServerSideApplicable;
import io.fabric8.kubernetes.client.dsl.Watchable;
import io.fabric8.kubernetes.client.dsl.WritableOperation;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ResourceAdapter implements Resource {
   Resource resource;

   public ResourceAdapter() {
   }

   public ResourceAdapter(Resource resource) {
      this.resource = resource;
   }

   public Resource getResource() {
      return this.resource;
   }

   public List delete() {
      return this.resource.delete();
   }

   public boolean isReady() {
      return this.resource.isReady();
   }

   public Object get() {
      return this.resource.get();
   }

   public ReplaceDeletable lockResourceVersion(String resourceVersion) {
      return this.resource.lockResourceVersion(resourceVersion);
   }

   public WritableOperation dryRun() {
      return (WritableOperation)this.resource.dryRun();
   }

   public Watchable withResourceVersion(String resourceVersion) {
      return this.resource.withResourceVersion(resourceVersion);
   }

   public Gettable fromServer() {
      return this.resource.fromServer();
   }

   public Object replaceStatus() {
      return this.resource.replaceStatus();
   }

   public Object create() {
      return this.resource.create();
   }

   public PropagationPolicyConfigurable withGracePeriod(long gracePeriodSeconds) {
      return (PropagationPolicyConfigurable)this.resource.withGracePeriod(gracePeriodSeconds);
   }

   public Object createOrReplace() {
      return this.resource.createOrReplace();
   }

   public Object editStatus(UnaryOperator function) {
      return this.resource.editStatus(function);
   }

   public Object require() throws ResourceNotFoundException {
      return this.resource.require();
   }

   public GracePeriodConfigurable withPropagationPolicy(DeletionPropagation propagationPolicy) {
      return (GracePeriodConfigurable)this.resource.withPropagationPolicy(propagationPolicy);
   }

   public Watch watch(Watcher watcher) {
      return this.resource.watch(watcher);
   }

   /** @deprecated */
   @Deprecated
   public Object patch(Object item) {
      return this.resource.patch(item);
   }

   public Object patchStatus(Object item) {
      return this.resource.patchStatus(item);
   }

   public Object edit(UnaryOperator function) {
      return this.resource.edit(function);
   }

   public Object waitUntilReady(long amount, TimeUnit timeUnit) {
      return this.resource.waitUntilReady(amount, timeUnit);
   }

   public Watch watch(ListOptions options, Watcher watcher) {
      return this.resource.watch(options, watcher);
   }

   public Object waitUntilCondition(Predicate condition, long amount, TimeUnit timeUnit) {
      return this.resource.waitUntilCondition(condition, amount, timeUnit);
   }

   public Informable withIndexers(Map indexers) {
      return this.resource.withIndexers(indexers);
   }

   public WritableOperation dryRun(boolean isDryRun) {
      return (WritableOperation)this.resource.dryRun(isDryRun);
   }

   public Object edit(Visitor... visitors) {
      return this.resource.edit(visitors);
   }

   public Object patch(PatchContext patchContext, Object item) {
      return this.resource.patch(patchContext, item);
   }

   public Informable withLimit(Long limit) {
      return this.resource.withLimit(limit);
   }

   public Object edit(Class visitorType, Visitor visitor) {
      return this.resource.edit(visitorType, visitor);
   }

   public Watch watch(String resourceVersion, Watcher watcher) {
      return this.resource.watch(resourceVersion, watcher);
   }

   public Object accept(Consumer function) {
      return this.resource.accept(function);
   }

   public SharedIndexInformer inform() {
      return this.resource.inform();
   }

   public Object patch(String patch) {
      return this.resource.patch(patch);
   }

   public Object patch(PatchContext patchContext, String patch) {
      return this.resource.patch(patchContext, patch);
   }

   public SharedIndexInformer inform(ResourceEventHandler handler) {
      return this.resource.inform(handler);
   }

   public SharedIndexInformer inform(ResourceEventHandler handler, long resync) {
      return this.resource.inform(handler, resync);
   }

   public SharedIndexInformer runnableInformer(long resync) {
      return this.resource.runnableInformer(resync);
   }

   public CompletableFuture informOnCondition(Predicate condition) {
      return this.resource.informOnCondition(condition);
   }

   public Object replace() {
      return this.resource.replace();
   }

   public Object create(Object item) {
      return this.resource.create(item);
   }

   public Object replace(Object item) {
      return this.resource.replace(item);
   }

   public Object createOrReplace(Object item) {
      return this.resource.createOrReplace(item);
   }

   public Object replaceStatus(Object item) {
      return this.resource.replaceStatus(item);
   }

   public List delete(Object item) {
      return this.resource.delete(item);
   }

   public Object updateStatus(Object item) {
      return this.resource.updateStatus(item);
   }

   public ReplaceDeletable lockResourceVersion() {
      return this.resource.lockResourceVersion();
   }

   public Object patchStatus() {
      return this.resource.patchStatus();
   }

   public Object patch() {
      return this.resource.patch();
   }

   public Object patch(PatchContext patchContext) {
      return this.resource.patch(patchContext);
   }

   public NonDeletingOperation fieldValidation(FieldValidateable.Validation fieldValidation) {
      return (NonDeletingOperation)this.resource.fieldValidation(fieldValidation);
   }

   public ServerSideApplicable fieldManager(String manager) {
      return this.resource.fieldManager(manager);
   }

   public ServerSideApplicable forceConflicts() {
      return this.resource.forceConflicts();
   }

   public Object serverSideApply() {
      return this.resource.serverSideApply();
   }

   public Object item() {
      return this.resource.item();
   }

   public Deletable withTimeout(long timeout, TimeUnit unit) {
      return this.resource.withTimeout(timeout, unit);
   }

   public Deletable withTimeoutInMillis(long timeoutInMillis) {
      return this.withTimeout(timeoutInMillis, TimeUnit.MILLISECONDS);
   }

   public Object update() {
      return this.resource.update();
   }

   public Object updateStatus() {
      return this.resource.updateStatus();
   }

   public Scale scale() {
      return this.resource.scale();
   }

   public Object scale(int count) {
      return this.resource.scale(count);
   }

   public Object scale(int count, boolean wait) {
      return this.resource.scale(count, wait);
   }

   public Scale scale(Scale scale) {
      return this.resource.scale(scale);
   }

   public Object createOr(Function conflictAction) {
      return this.resource.createOr(conflictAction);
   }

   public NonDeletingOperation unlock() {
      return this.resource.unlock();
   }

   public EditReplacePatchable subresource(String subresource) {
      return this.resource.subresource(subresource);
   }
}
