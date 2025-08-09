package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Verticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class DeploymentManager {
   private static final Logger log = LoggerFactory.getLogger(DeploymentManager.class);
   private final VertxImpl vertx;
   private final Map deployments = new ConcurrentHashMap();

   public DeploymentManager(VertxImpl vertx) {
      this.vertx = vertx;
   }

   private String generateDeploymentID() {
      return UUID.randomUUID().toString();
   }

   public Future deployVerticle(Callable verticleSupplier, DeploymentOptions options) {
      if (options.getInstances() < 1) {
         throw new IllegalArgumentException("Can't specify < 1 instances to deploy");
      } else {
         options.checkIsolationNotDefined();
         ContextInternal currentContext = this.vertx.getOrCreateContext();
         ClassLoader cl = options.getClassLoader();
         if (cl == null) {
            cl = Thread.currentThread().getContextClassLoader();
            if (cl == null) {
               cl = this.getClass().getClassLoader();
            }
         }

         return this.doDeploy((DeploymentOptions)options, (Function)((v) -> "java:" + v.getClass().getName()), currentContext, currentContext, cl, (Callable)verticleSupplier).map(Deployment::deploymentID);
      }
   }

   public Future undeployVerticle(String deploymentID) {
      Deployment deployment = (Deployment)this.deployments.get(deploymentID);
      Context currentContext = this.vertx.getOrCreateContext();
      return deployment == null ? ((ContextInternal)currentContext).failedFuture((Throwable)(new IllegalStateException("Unknown deployment"))) : deployment.doUndeploy(this.vertx.getOrCreateContext());
   }

   public Set deployments() {
      return Collections.unmodifiableSet(this.deployments.keySet());
   }

   public Deployment getDeployment(String deploymentID) {
      return (Deployment)this.deployments.get(deploymentID);
   }

   public Future undeployAll() {
      Set<String> deploymentIDs = new HashSet();

      for(Map.Entry entry : this.deployments.entrySet()) {
         if (!((Deployment)entry.getValue()).isChild()) {
            deploymentIDs.add(entry.getKey());
         }
      }

      List<Future<?>> completionList = new ArrayList();
      if (deploymentIDs.isEmpty()) {
         return this.vertx.getOrCreateContext().succeededFuture();
      } else {
         for(String deploymentID : deploymentIDs) {
            Promise<Void> promise = Promise.promise();
            completionList.add(promise.future());
            this.undeployVerticle(deploymentID).onComplete((ar) -> {
               if (ar.failed()) {
                  log.error("Undeploy failed", ar.cause());
               }

               promise.handle(ar);
            });
         }

         Promise<Void> promise = this.vertx.getOrCreateContext().promise();
         Future.join(completionList).mapEmpty().onComplete(promise);
         return promise.future();
      }
   }

   private void reportFailure(Throwable t, Context context, Handler completionHandler) {
      if (completionHandler != null) {
         this.reportResult(context, completionHandler, Future.failedFuture(t));
      } else {
         log.error(t.getMessage(), t);
      }

   }

   private void reportResult(Context context, Handler completionHandler, AsyncResult result) {
      context.runOnContext((v) -> {
         try {
            completionHandler.handle(result);
         } catch (Throwable t) {
            log.error("Failure in calling handler", t);
            throw t;
         }
      });
   }

   Future doDeploy(DeploymentOptions options, Function identifierProvider, ContextInternal parentContext, ContextInternal callingContext, ClassLoader tccl, Callable verticleSupplier) {
      int nbInstances = options.getInstances();
      Set<Verticle> verticles = Collections.newSetFromMap(new IdentityHashMap());

      for(int i = 0; i < nbInstances; ++i) {
         Verticle verticle;
         try {
            verticle = (Verticle)verticleSupplier.call();
         } catch (Exception e) {
            return Future.failedFuture((Throwable)e);
         }

         if (verticle == null) {
            return Future.failedFuture("Supplied verticle is null");
         }

         verticles.add(verticle);
      }

      if (verticles.size() != nbInstances) {
         return Future.failedFuture("Same verticle supplied more than once");
      } else {
         Verticle[] verticlesArray = (Verticle[])verticles.toArray(new Verticle[0]);
         return this.doDeploy((String)identifierProvider.apply(verticlesArray[0]), options, parentContext, callingContext, tccl, verticlesArray);
      }
   }

   private Future doDeploy(String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader tccl, Verticle... verticles) {
      Promise<Deployment> promise = callingContext.promise();
      Deployment parent = parentContext.getDeployment();
      String deploymentID = this.generateDeploymentID();
      AtomicInteger deployCount = new AtomicInteger();
      AtomicBoolean failureReported = new AtomicBoolean();
      WorkerPool workerPool = null;
      ThreadingModel mode = options.getThreadingModel();
      if (mode == null) {
         mode = ThreadingModel.EVENT_LOOP;
      }

      if (mode != ThreadingModel.VIRTUAL_THREAD) {
         if (options.getWorkerPoolName() != null) {
            workerPool = this.vertx.createSharedWorkerPool(options.getWorkerPoolName(), options.getWorkerPoolSize(), options.getMaxWorkerExecuteTime(), options.getMaxWorkerExecuteTimeUnit());
         }
      } else if (!this.vertx.isVirtualThreadAvailable()) {
         return callingContext.failedFuture("This Java runtime does not support virtual threads");
      }

      DeploymentImpl deployment = new DeploymentImpl(parent, workerPool, deploymentID, identifier, options);

      for(Verticle verticle : verticles) {
         CloseFuture closeFuture = new CloseFuture(log);
         ContextImpl context;
         switch (mode) {
            case WORKER:
               context = this.vertx.createWorkerContext(deployment, closeFuture, workerPool, tccl);
               break;
            case VIRTUAL_THREAD:
               context = this.vertx.createVirtualThreadContext(deployment, closeFuture, tccl);
               break;
            default:
               context = this.vertx.createEventLoopContext(deployment, closeFuture, workerPool, tccl);
         }

         VerticleHolder holder = new VerticleHolder(verticle, context);
         deployment.addVerticle(holder);
         context.runOnContext((v) -> {
            try {
               verticle.init(this.vertx, context);
               Promise<Void> startPromise = context.promise();
               Future<Void> startFuture = startPromise.future();
               verticle.start(startPromise);
               startFuture.onComplete((ar) -> {
                  if (ar.succeeded()) {
                     if (parent != null) {
                        if (!parent.addChild(deployment)) {
                           deployment.doUndeploy(this.vertx.getOrCreateContext()).onComplete((ar2) -> promise.fail("Verticle deployment failed.Could not be added as child of parent verticle"));
                           return;
                        }

                        deployment.child = true;
                     }

                     this.deployments.put(deploymentID, deployment);
                     if (deployCount.incrementAndGet() == verticles.length) {
                        promise.complete(deployment);
                     }
                  } else if (failureReported.compareAndSet(false, true)) {
                     deployment.rollback(callingContext, promise, context, holder, ar.cause());
                  }

               });
            } catch (Throwable t) {
               if (failureReported.compareAndSet(false, true)) {
                  deployment.rollback(callingContext, promise, context, holder, t);
               }
            }

         });
      }

      return promise.future();
   }

   static class VerticleHolder {
      final Verticle verticle;
      final ContextImpl context;

      VerticleHolder(Verticle verticle, ContextImpl context) {
         this.verticle = verticle;
         this.context = context;
      }

      void close(Handler completionHandler) {
         this.context.close().onComplete(completionHandler);
      }
   }

   private class DeploymentImpl implements Deployment {
      private static final int ST_DEPLOYED = 0;
      private static final int ST_UNDEPLOYING = 1;
      private static final int ST_UNDEPLOYED = 2;
      private final Deployment parent;
      private final String deploymentID;
      private final JsonObject conf;
      private final String verticleIdentifier;
      private final List verticles;
      private final Set children;
      private final WorkerPool workerPool;
      private final DeploymentOptions options;
      private Handler undeployHandler;
      private int status;
      private volatile boolean child;

      private DeploymentImpl(Deployment parent, WorkerPool workerPool, String deploymentID, String verticleIdentifier, DeploymentOptions options) {
         this.verticles = new CopyOnWriteArrayList();
         this.children = new ConcurrentHashSet();
         this.status = 0;
         this.parent = parent;
         this.deploymentID = deploymentID;
         this.conf = options.getConfig() != null ? options.getConfig().copy() : new JsonObject();
         this.verticleIdentifier = verticleIdentifier;
         this.options = options;
         this.workerPool = workerPool;
      }

      public void addVerticle(VerticleHolder holder) {
         this.verticles.add(holder);
      }

      private synchronized void rollback(ContextInternal callingContext, Handler completionHandler, ContextImpl context, VerticleHolder closeFuture, Throwable cause) {
         if (this.status == 0) {
            this.status = 1;
            this.doUndeployChildren(callingContext).onComplete((childrenResult) -> {
               if (this.workerPool != null) {
                  this.workerPool.close();
               }

               Handler<Void> handler;
               synchronized(this) {
                  this.status = 2;
                  handler = this.undeployHandler;
                  this.undeployHandler = null;
               }

               if (handler != null) {
                  try {
                     handler.handle((Object)null);
                  } catch (Exception e) {
                     context.reportException(e);
                  }
               }

               if (childrenResult.failed()) {
                  DeploymentManager.this.reportFailure(cause, callingContext, completionHandler);
               } else {
                  closeFuture.close((closeHookAsyncResult) -> DeploymentManager.this.reportFailure(cause, callingContext, completionHandler));
               }

            });
         }

      }

      private synchronized Future doUndeployChildren(ContextInternal undeployingContext) {
         if (this.children.isEmpty()) {
            return Future.succeededFuture();
         } else {
            List<Future<?>> undeployFutures = new ArrayList();

            for(Deployment childDeployment : new HashSet(this.children)) {
               Promise<Void> p = Promise.promise();
               undeployFutures.add(p.future());
               childDeployment.doUndeploy(undeployingContext).onComplete((ar) -> {
                  this.children.remove(childDeployment);
                  p.handle(ar);
               });
            }

            return Future.all(undeployFutures).mapEmpty();
         }
      }

      public synchronized Future doUndeploy(ContextInternal undeployingContext) {
         if (this.status == 2) {
            return Future.failedFuture((Throwable)(new IllegalStateException("Already undeployed")));
         } else if (!this.children.isEmpty()) {
            this.status = 1;
            return this.doUndeployChildren(undeployingContext).compose((v) -> this.doUndeploy(undeployingContext));
         } else {
            this.status = 2;
            List<Future<?>> undeployFutures = new ArrayList();
            if (this.parent != null) {
               this.parent.removeChild(this);
            }

            for(VerticleHolder verticleHolder : this.verticles) {
               ContextImpl context = verticleHolder.context;
               Promise<?> p = Promise.promise();
               undeployFutures.add(p.future());
               context.runOnContext((v) -> {
                  Promise<Void> stopPromise = undeployingContext.promise();
                  Future<Void> stopFuture = stopPromise.future();
                  stopFuture.onComplete((ar) -> {
                     DeploymentManager.this.deployments.remove(this.deploymentID);
                     verticleHolder.close((ar2) -> {
                        if (ar2.failed()) {
                           DeploymentManager.log.error("Failed to run close hook", ar2.cause());
                        }

                        if (ar.succeeded()) {
                           p.complete();
                        } else if (ar.failed()) {
                           p.fail(ar.cause());
                        }

                     });
                  });

                  try {
                     verticleHolder.verticle.stop(stopPromise);
                  } catch (Throwable t) {
                     if (!stopPromise.tryFail(t)) {
                        undeployingContext.reportException(t);
                     }
                  }

               });
            }

            Promise<Void> resolvingPromise = undeployingContext.promise();
            Future.all(undeployFutures).mapEmpty().onComplete(resolvingPromise);
            Future<Void> fut = resolvingPromise.future();
            if (this.workerPool != null) {
               fut = fut.andThen((ar) -> this.workerPool.close());
            }

            Handler<Void> handler = this.undeployHandler;
            if (handler != null) {
               this.undeployHandler = null;
               return fut.compose((v) -> {
                  handler.handle((Object)null);
                  return Future.succeededFuture();
               }, (v) -> {
                  handler.handle((Object)null);
                  return Future.succeededFuture();
               });
            } else {
               return fut;
            }
         }
      }

      public String verticleIdentifier() {
         return this.verticleIdentifier;
      }

      public DeploymentOptions deploymentOptions() {
         return this.options;
      }

      public JsonObject config() {
         return this.conf;
      }

      public synchronized boolean addChild(Deployment deployment) {
         if (this.status == 0) {
            this.children.add(deployment);
            return true;
         } else {
            return false;
         }
      }

      public void removeChild(Deployment deployment) {
         this.children.remove(deployment);
      }

      public Set getContexts() {
         Set<Context> contexts = new HashSet();

         for(VerticleHolder holder : this.verticles) {
            contexts.add(holder.context);
         }

         return contexts;
      }

      public Set getVerticles() {
         Set<Verticle> verts = new HashSet();

         for(VerticleHolder holder : this.verticles) {
            verts.add(holder.verticle);
         }

         return verts;
      }

      public void undeployHandler(Handler handler) {
         synchronized(this) {
            if (this.status != 2) {
               this.undeployHandler = handler;
               return;
            }
         }

         handler.handle((Object)null);
      }

      public boolean isChild() {
         return this.child;
      }

      public String deploymentID() {
         return this.deploymentID;
      }
   }
}
