package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;
import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.KubernetesClientTimeoutException;
import io.fabric8.kubernetes.client.dsl.CreateOrReplaceable;
import io.fabric8.kubernetes.client.dsl.DeletableWithOptions;
import io.fabric8.kubernetes.client.dsl.FieldValidateable;
import io.fabric8.kubernetes.client.dsl.Gettable;
import io.fabric8.kubernetes.client.dsl.ListVisitFromServerGetDeleteRecreateWaitApplicable;
import io.fabric8.kubernetes.client.dsl.ListVisitFromServerWritable;
import io.fabric8.kubernetes.client.dsl.NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable;
import io.fabric8.kubernetes.client.dsl.NamespaceableResource;
import io.fabric8.kubernetes.client.dsl.Replaceable;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.ServerSideApplicable;
import io.fabric8.kubernetes.client.dsl.Updatable;
import io.fabric8.kubernetes.client.dsl.Waitable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl implements NamespaceListVisitFromServerGetDeleteRecreateWaitApplicable, Waitable {
   protected static final String EXPRESSION = "expression";
   private OperationContext context;

   public NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl(OperationContext context) {
      this.context = context;
   }

   public NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl(Client client, Object item) {
      this(HasMetadataOperationsImpl.defaultContext(client).withItem(item));
   }

   public List waitUntilReady(long amount, TimeUnit timeUnit) {
      return this.waitUntilCondition((resource) -> Objects.nonNull(resource) && this.context.getConfig().getReadiness().isReady(resource), amount, timeUnit);
   }

   List getItems() {
      Object item = this.context.getItem();
      return (List)this.asHasMetadata(item).stream().map((meta) -> acceptVisitors(meta, Collections.emptyList(), this.context)).collect(Collectors.toList());
   }

   public List items() {
      return this.getItems();
   }

   public Stream resources() {
      return this.getItems().stream().map(this::getResource);
   }

   public List getResources() {
      return (List)this.resources().collect(Collectors.toList());
   }

   NamespaceableResource getResource(HasMetadata meta) {
      return ((KubernetesClient)this.context.clientInWriteContext(KubernetesClient.class)).resource(meta);
   }

   public List waitUntilCondition(Predicate condition, long amount, TimeUnit timeUnit) {
      List<? extends Resource<HasMetadata>> operations = this.getResources();
      if (operations.isEmpty()) {
         return Collections.emptyList();
      } else {
         List<HasMetadata> items = (List)operations.stream().map(Resource::item).collect(Collectors.toList());
         List<CompletableFuture<List<HasMetadata>>> futures = new ArrayList(items.size());

         for(Resource impl : operations) {
            CompletableFuture<List<HasMetadata>> futureCondition = impl.informOnCondition((l) -> l.isEmpty() ? condition.test((Object)null) : condition.test((HasMetadata)l.get(0)));
            futures.add(futureCondition);
         }

         List<HasMetadata> results = new ArrayList();
         List<HasMetadata> itemsWithConditionNotMatched = new ArrayList();
         long finish = System.nanoTime() + timeUnit.toNanos(amount);

         try {
            for(int i = 0; i < items.size(); ++i) {
               HasMetadata meta = (HasMetadata)items.get(i);
               CompletableFuture<List<HasMetadata>> future = (CompletableFuture)futures.get(i);

               try {
                  results.add((HasMetadata)future.thenApply((l) -> l.isEmpty() ? null : (HasMetadata)l.get(0)).get(Math.max(0L, finish - System.nanoTime()), TimeUnit.NANOSECONDS));
               } catch (TimeoutException var28) {
                  itemsWithConditionNotMatched.add(meta);
               } catch (ExecutionException e) {
                  throw KubernetesClientException.launderThrowable(e.getCause());
               } catch (InterruptedException e) {
                  Thread.currentThread().interrupt();
                  throw KubernetesClientException.launderThrowable(e);
               } finally {
                  future.cancel(true);
               }
            }
         } finally {
            futures.forEach((f) -> f.cancel(true));
         }

         if (!itemsWithConditionNotMatched.isEmpty()) {
            throw new KubernetesClientTimeoutException(itemsWithConditionNotMatched, amount, timeUnit);
         } else {
            return results;
         }
      }
   }

   public ListVisitFromServerWritable dryRun(boolean isDryRun) {
      return this.newInstance(this.context.withDryRun(isDryRun));
   }

   public ListVisitFromServerWritable fieldValidation(FieldValidateable.Validation fieldValidation) {
      return this.newInstance(this.context.withFieldValidation(fieldValidation));
   }

   public List createOrReplace() {
      List<? extends Resource<HasMetadata>> operations = this.getResources();
      return (List)operations.stream().map(CreateOrReplaceable::createOrReplace).filter(Objects::nonNull).collect(Collectors.toList());
   }

   public List delete() {
      List<StatusDetails> deleted = (List)this.resources().flatMap((r) -> r.delete().stream()).collect(Collectors.toList());
      BaseOperation.waitForDelete(deleted, this.context, this);
      return deleted;
   }

   public List get() {
      return this.performOperation(Gettable::get);
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable inNamespace(String explicitNamespace) {
      if (explicitNamespace == null) {
         throw new KubernetesClientException("namespace cannot be null");
      } else {
         return this.newInstance(this.context.withNamespace(explicitNamespace));
      }
   }

   public Gettable fromServer() {
      return this;
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable accept(Visitor... visitors) {
      return this.newInstance(this.context.withItem(this.getItems().stream().map((i) -> acceptVisitors(i, Arrays.asList(visitors), this.context)).collect(Collectors.toList())));
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable withGracePeriod(long gracePeriodSeconds) {
      return this.newInstance(this.context.withGracePeriodSeconds(gracePeriodSeconds));
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable withPropagationPolicy(DeletionPropagation propagationPolicy) {
      return this.newInstance(this.context.withPropagationPolicy(propagationPolicy));
   }

   protected List asHasMetadata(Object item) {
      List<HasMetadata> result = new ArrayList();
      if (item instanceof KubernetesResourceList) {
         result.addAll(((KubernetesResourceList)item).getItems());
      } else if (item instanceof HasMetadata) {
         result.add((HasMetadata)item);
      } else if (item instanceof Collection) {
         for(Object o : (Collection)item) {
            if (o != null) {
               result.add((HasMetadata)o);
            }
         }
      } else if (item != null) {
         throw new IllegalArgumentException("Could not convert item to a list of HasMetadata");
      }

      return result;
   }

   public NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl newInstance(OperationContext context) {
      return new NamespaceVisitFromServerGetWatchDeleteRecreateWaitApplicableListImpl(context);
   }

   static HasMetadata acceptVisitors(HasMetadata item, List visitors, OperationContext context) {
      if (!visitors.isEmpty()) {
         VisitableBuilder<HasMetadata, ?> builder = context.getHandler(item).edit(item);

         for(Visitor v : visitors) {
            builder.accept(new Visitor[]{v});
         }

         item = (HasMetadata)builder.build();
      }

      return item;
   }

   public List create() {
      return this.performOperation(CreateOrReplaceable::create);
   }

   public ListVisitFromServerWritable dryRun() {
      return this.dryRun(true);
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable inAnyNamespace() {
      return this.newInstance(this.context.withNamespace((String)null));
   }

   public List replace() {
      return this.performOperation(Updatable::replace);
   }

   private List performOperation(Function operation) {
      return (List)this.getResources().stream().map(operation).collect(Collectors.toList());
   }

   public List replaceStatus() {
      return this.performOperation(Replaceable::replaceStatus);
   }

   public DeletableWithOptions withTimeout(long timeout, TimeUnit unit) {
      return this.newInstance(this.context.withTimeout(timeout, unit));
   }

   public DeletableWithOptions withTimeoutInMillis(long timeoutInMillis) {
      return this.withTimeout(timeoutInMillis, TimeUnit.MILLISECONDS);
   }

   public List updateStatus() {
      return this.performOperation(Replaceable::updateStatus);
   }

   public List update() {
      return this.performOperation(Updatable::update);
   }

   public List serverSideApply() {
      return this.performOperation(ServerSideApplicable::serverSideApply);
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable fieldManager(String manager) {
      return this.newInstance(this.context.withFieldManager(manager));
   }

   public ListVisitFromServerGetDeleteRecreateWaitApplicable forceConflicts() {
      return this.newInstance(this.context.withForceConflicts());
   }
}
