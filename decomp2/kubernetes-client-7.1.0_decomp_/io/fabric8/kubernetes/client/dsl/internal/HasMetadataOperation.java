package io.fabric8.kubernetes.client.dsl.internal;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;
import io.fabric8.kubernetes.api.builder.Visitor;
import io.fabric8.kubernetes.api.model.DeletionPropagation;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.autoscaling.v1.Scale;
import io.fabric8.kubernetes.api.model.autoscaling.v1.ScaleBuilder;
import io.fabric8.kubernetes.api.model.autoscaling.v1.ScaleFluent;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.dsl.Scalable;
import io.fabric8.kubernetes.client.dsl.base.PatchContext;
import io.fabric8.kubernetes.client.dsl.base.PatchType;
import io.fabric8.kubernetes.client.utils.KubernetesResourceUtil;
import io.fabric8.kubernetes.client.utils.Utils;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HasMetadataOperation extends BaseOperation {
   private static final Logger LOGGER = LoggerFactory.getLogger(HasMetadataOperation.class);
   public static final DeletionPropagation DEFAULT_PROPAGATION_POLICY;
   public static final long DEFAULT_GRACE_PERIOD_IN_SECONDS = -1L;
   private static final String PATCH_OPERATION = "patch";
   private static final String REPLACE_OPERATION = "replace";
   private static final String UPDATE_OPERATION = "update";

   public HasMetadataOperation(OperationContext ctx, Class type, Class listType) {
      super(ctx);
      this.type = type;
      this.listType = listType;
   }

   public HasMetadata edit(UnaryOperator function) {
      T item = (T)this.getItemOrRequireFromServer();
      T clone = (T)this.clone(item);
      return this.patch((PatchContext)null, clone, (HasMetadata)function.apply(item));
   }

   private HasMetadata clone(HasMetadata item) {
      return (HasMetadata)this.getKubernetesSerialization().clone(item);
   }

   public HasMetadata editStatus(UnaryOperator function) {
      T item = (T)this.getItemOrRequireFromServer();
      T clone = (T)this.clone(item);
      return this.statusSubresource().patch((PatchContext)null, clone, (HasMetadata)function.apply(item));
   }

   private HasMetadataOperation statusSubresource() {
      return this.newInstance(this.context.withSubresource("status"));
   }

   public HasMetadata accept(Consumer consumer) {
      T item = (T)this.getItemOrRequireFromServer();
      T clone = (T)this.clone(item);
      consumer.accept(item);
      return this.patch((PatchContext)null, clone, item);
   }

   public HasMetadata edit(Visitor... visitors) {
      T item = (T)this.getItemOrRequireFromServer();
      T clone = (T)this.clone(item);
      return this.patch((PatchContext)null, clone, (HasMetadata)((VisitableBuilder)this.context.getHandler(item).edit(item).accept(visitors)).build());
   }

   public HasMetadata replace() {
      return this.handleReplace(this.getItem());
   }

   public HasMetadata replaceStatus() {
      return this.statusSubresource().replace();
   }

   protected HasMetadata modifyItemForReplaceOrPatch(Supplier current, HasMetadata item) {
      return item;
   }

   public HasMetadata update() {
      return this.update(this.getItem());
   }

   public HasMetadata updateStatus() {
      return this.statusSubresource().update();
   }

   protected HasMetadata update(HasMetadata item) {
      String existingResourceVersion = KubernetesResourceUtil.getResourceVersion(item);

      try {
         if (existingResourceVersion == null) {
            T got = (T)this.requireFromServer();
            String resourceVersion = KubernetesResourceUtil.getResourceVersion(got);
            item = (T)this.clone(item);
            item.getMetadata().setResourceVersion(resourceVersion);
         }

         return this.handleUpdate(item);
      } catch (KubernetesClientException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("update"), e);
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(this.forOperationType("update"), e);
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("update"), e);
      }
   }

   protected HasMetadata handleReplace(HasMetadata item) {
      String fixedResourceVersion = this.getResourceVersion();
      Exception caught = null;
      int maxTries = 10;
      item = (T)this.clone(item);
      if (item.getMetadata() == null) {
         item.setMetadata(new ObjectMeta());
      }

      if (this.context.getSubresource() == null) {
         try {
            item = (T)this.modifyItemForReplaceOrPatch(this::requireFromServer, item);
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable(this.forOperationType("replace"), e);
         }
      }

      String existingResourceVersion = KubernetesResourceUtil.getResourceVersion(item);

      for(int i = 0; i < maxTries; ++i) {
         try {
            String resourceVersion;
            if (fixedResourceVersion != null) {
               resourceVersion = fixedResourceVersion;
            } else if (i == 0 && existingResourceVersion != null) {
               resourceVersion = existingResourceVersion;
            } else {
               T got = (T)this.requireFromServer();
               resourceVersion = KubernetesResourceUtil.getResourceVersion(got);
            }

            UnaryOperator<T> visitor = (resource) -> {
               try {
                  resource.getMetadata().setResourceVersion(resourceVersion);
                  return this.handleUpdate(resource);
               } catch (Exception e) {
                  throw KubernetesClientException.launderThrowable(this.forOperationType("replace"), e);
               }
            };
            return (HasMetadata)visitor.apply(item);
         } catch (KubernetesClientException e) {
            caught = e;
            if (e.getCode() != 409 || fixedResourceVersion != null) {
               throw KubernetesClientException.launderThrowable(this.forOperationType("replace"), caught);
            }

            if (i < maxTries - 1) {
               try {
                  TimeUnit.SECONDS.sleep(1L);
               } catch (InterruptedException var9) {
                  Thread.currentThread().interrupt();
               }
            }
         } catch (Exception e) {
            caught = e;
         }
      }

      throw KubernetesClientException.launderThrowable(this.forOperationType("replace"), caught);
   }

   protected HasMetadata patch(PatchContext context, HasMetadata base, HasMetadata item) {
      if ((context == null || context.getPatchType() == PatchType.JSON) && base == null) {
         if (base == null) {
            base = (T)this.requireFromServer();
         }

         T current = base;

         try {
            item = (T)this.modifyItemForReplaceOrPatch(() -> current, item);
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable(this.forOperationType("patch"), e);
         }
      }

      UnaryOperator<T> visitor = (resource) -> {
         try {
            return this.handlePatch(context, base, resource);
         } catch (Exception e) {
            throw KubernetesClientException.launderThrowable(this.forOperationType("patch"), e);
         }
      };
      return (HasMetadata)visitor.apply(item);
   }

   public HasMetadata patchStatus() {
      return this.statusSubresource().patch(PatchContext.of(PatchType.JSON_MERGE), (HasMetadata)null, this.getNonNullItem());
   }

   public HasMetadata patch() {
      return this.patch((PatchContext)null, (HasMetadata)null, this.getNonNullItem());
   }

   public HasMetadata patch(PatchContext patchContext) {
      return this.patch(patchContext, (HasMetadata)null, this.getNonNullItem());
   }

   public HasMetadata patchStatus(HasMetadata item) {
      return this.statusSubresource().patch(PatchContext.of(PatchType.JSON_MERGE), this.getItem(), this.clone(item));
   }

   public HasMetadata patch(PatchContext patchContext, HasMetadata item) {
      return this.patch(patchContext, this.getItem(), this.clone(item));
   }

   public HasMetadata patch(PatchContext patchContext, String patch) {
      try {
         T got = (T)this.getItemOrRequireFromServer();
         return (HasMetadata)this.handlePatch(patchContext, got, this.getKubernetesSerialization().convertToJson(patch), this.getType());
      } catch (InterruptedException interruptedException) {
         Thread.currentThread().interrupt();
         throw KubernetesClientException.launderThrowable(this.forOperationType("patch"), interruptedException);
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(this.forOperationType("patch"), e);
      }
   }

   public HasMetadataOperation newInstance(OperationContext context) {
      return new HasMetadataOperation(context, this.type, this.listType);
   }

   public HasMetadata scale(int count) {
      this.scale(((ScaleBuilder)((ScaleFluent.SpecNested)((ScaleBuilder)((ScaleFluent.MetadataNested)(new ScaleBuilder(this.scale())).editOrNewMetadata().withResourceVersion((String)null)).endMetadata()).editOrNewSpec().withReplicas(count)).endSpec()).build());
      if (this.context.getTimeout() > 0L) {
         this.waitUntilScaled(count);
      }

      return this.get();
   }

   public HasMetadata scale(int count, boolean wait) {
      Scalable<T> scalable = this;
      if (wait) {
         scalable = this.withTimeoutInMillis(this.getRequestConfig().getScaleTimeout());
      }

      return (HasMetadata)scalable.scale(count);
   }

   public Scale scale(Scale scaleParam) {
      return (Scale)this.handleScale(scaleParam, Scale.class);
   }

   protected void waitUntilScaled(int count) {
      AtomicReference<Integer> replicasRef = new AtomicReference(0);
      String name = this.checkName(this.getItem());
      String namespace = this.checkNamespace(this.getItem());
      CompletableFuture<Void> completion = new CompletableFuture();
      Utils.scheduleWithVariableRate(completion, this.getOperationContext().getExecutor(), () -> {
         try {
            Scale scale = this.scale();
            int statusReplicas = (Integer)Optional.ofNullable(scale.getStatus().getReplicas()).orElse(0);
            int specReplicas = (Integer)Optional.ofNullable(scale.getSpec().getReplicas()).orElse(0);
            if (count == statusReplicas && count == specReplicas) {
               completion.complete((Object)null);
            } else if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("Only {}/{} replicas scheduled for {}: {} in namespace: {} seconds so waiting...", new Object[]{specReplicas, count, this.getKind(), this.getName(), namespace});
            }
         } catch (KubernetesClientException e) {
            completion.completeExceptionally(e);
         }

      }, 0L, () -> 1L, TimeUnit.SECONDS);
      if (!Utils.waitUntilReady(completion, this.context.getTimeout(), this.context.getTimeoutUnit())) {
         completion.complete((Object)null);
         throw new KubernetesClientException(String.format("%s/%s pod(s) ready for %s: %s in namespace: %s  after waiting for %s seconds so giving up", replicasRef.get(), count, this.getType().getSimpleName(), name, namespace, this.context.getTimeoutUnit().toSeconds(this.context.getTimeout())));
      }
   }

   static {
      DEFAULT_PROPAGATION_POLICY = DeletionPropagation.BACKGROUND;
   }
}
