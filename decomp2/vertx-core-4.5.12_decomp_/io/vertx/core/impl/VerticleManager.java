package io.vertx.core.impl;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.ServiceHelper;
import io.vertx.core.Verticle;
import io.vertx.core.spi.VerticleFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class VerticleManager {
   private final VertxInternal vertx;
   private final DeploymentManager deploymentManager;
   private final LoaderManager loaderManager = new LoaderManager();
   private final Map verticleFactories = new ConcurrentHashMap();
   private final List defaultFactories = new ArrayList();

   public VerticleManager(VertxInternal vertx, DeploymentManager deploymentManager) {
      this.vertx = vertx;
      this.deploymentManager = deploymentManager;
      this.loadVerticleFactories();
   }

   private void loadVerticleFactories() {
      Collection<VerticleFactory> factories = ServiceHelper.loadFactories(VerticleFactory.class);
      factories.forEach(this::registerVerticleFactory);
      VerticleFactory defaultFactory = new JavaVerticleFactory();
      defaultFactory.init(this.vertx);
      this.defaultFactories.add(defaultFactory);
   }

   public void registerVerticleFactory(VerticleFactory factory) {
      String prefix = factory.prefix();
      if (prefix == null) {
         throw new IllegalArgumentException("factory.prefix() cannot be null");
      } else {
         List<VerticleFactory> facts = (List)this.verticleFactories.get(prefix);
         if (facts == null) {
            facts = new ArrayList();
            this.verticleFactories.put(prefix, facts);
         }

         if (facts.contains(factory)) {
            throw new IllegalArgumentException("Factory already registered");
         } else {
            facts.add(factory);
            facts.sort((fact1, fact2) -> fact1.order() - fact2.order());
            factory.init(this.vertx);
         }
      }
   }

   public void unregisterVerticleFactory(VerticleFactory factory) {
      String prefix = factory.prefix();
      if (prefix == null) {
         throw new IllegalArgumentException("factory.prefix() cannot be null");
      } else {
         List<VerticleFactory> facts = (List)this.verticleFactories.get(prefix);
         boolean removed = false;
         if (facts != null) {
            if (facts.remove(factory)) {
               removed = true;
            }

            if (facts.isEmpty()) {
               this.verticleFactories.remove(prefix);
            }
         }

         if (!removed) {
            throw new IllegalArgumentException("factory isn't registered");
         }
      }
   }

   public Set verticleFactories() {
      Set<VerticleFactory> facts = new HashSet();

      for(List list : this.verticleFactories.values()) {
         facts.addAll(list);
      }

      return facts;
   }

   private List resolveFactories(String identifier) {
      List<VerticleFactory> factoryList = null;
      int pos = identifier.indexOf(58);
      String lookup = null;
      if (pos != -1) {
         lookup = identifier.substring(0, pos);
      } else {
         pos = identifier.lastIndexOf(46);
         if (pos != -1) {
            lookup = getSuffix(pos, identifier);
         } else {
            factoryList = this.defaultFactories;
         }
      }

      if (factoryList == null) {
         factoryList = (List)this.verticleFactories.get(lookup);
         if (factoryList == null) {
            factoryList = this.defaultFactories;
         }
      }

      return factoryList;
   }

   private static String getSuffix(int pos, String str) {
      if (pos + 1 >= str.length()) {
         throw new IllegalArgumentException("Invalid name: " + str);
      } else {
         return str.substring(pos + 1);
      }
   }

   public Future deployVerticle(String identifier, DeploymentOptions options) {
      ContextInternal callingContext = this.vertx.getOrCreateContext();
      ClassLoader loader = options.getClassLoader();
      ClassLoaderHolder holder;
      if (loader == null) {
         holder = this.loaderManager.getClassLoader(options);
         loader = holder != null ? holder.loader : getCurrentClassLoader();
      } else {
         holder = null;
      }

      Future<Deployment> deployment = this.doDeployVerticle(identifier, options, callingContext, callingContext, loader);
      if (holder != null) {
         deployment.onComplete((ar) -> {
            if (ar.succeeded()) {
               Deployment result = (Deployment)ar.result();
               result.undeployHandler((v) -> this.loaderManager.release(holder));
            } else {
               throw new UnsupportedOperationException();
            }
         });
      }

      return deployment;
   }

   private Future doDeployVerticle(String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader cl) {
      List<VerticleFactory> verticleFactories = this.resolveFactories(identifier);
      Iterator<VerticleFactory> iter = verticleFactories.iterator();
      return this.doDeployVerticle(iter, (Throwable)null, identifier, options, parentContext, callingContext, cl);
   }

   private Future doDeployVerticle(Iterator iter, Throwable prevErr, String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader cl) {
      if (iter.hasNext()) {
         VerticleFactory verticleFactory = (VerticleFactory)iter.next();
         return this.doDeployVerticle(verticleFactory, identifier, options, parentContext, callingContext, cl).recover((err) -> this.doDeployVerticle(iter, err, identifier, options, parentContext, callingContext, cl));
      } else if (prevErr != null) {
         return callingContext.failedFuture(prevErr);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   private Future doDeployVerticle(VerticleFactory verticleFactory, String identifier, DeploymentOptions options, ContextInternal parentContext, ContextInternal callingContext, ClassLoader cl) {
      Promise<Callable<Verticle>> p = callingContext.promise();

      try {
         verticleFactory.createVerticle(identifier, cl, p);
      } catch (Exception e) {
         return Future.failedFuture((Throwable)e);
      }

      return p.future().compose((callable) -> this.deploymentManager.doDeploy((DeploymentOptions)options, (Function)((v) -> identifier), parentContext, callingContext, cl, (Callable)callable));
   }

   static ClassLoader getCurrentClassLoader() {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl == null) {
         cl = VerticleManager.class.getClassLoader();
      }

      return cl;
   }
}
