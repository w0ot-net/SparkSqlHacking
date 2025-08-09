package io.vertx.core.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.VertxException;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.core.spi.cluster.NodeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class HAManager {
   private static final Logger log = LoggerFactory.getLogger(HAManager.class);
   private static final long QUORUM_CHECK_PERIOD = 1000L;
   private final VertxInternal vertx;
   private final DeploymentManager deploymentManager;
   private final VerticleManager verticleFactoryManager;
   private final ClusterManager clusterManager;
   private final int quorumSize;
   private final String group;
   private final JsonObject haInfo;
   private final Map clusterMap;
   private final String nodeID;
   private final Queue toDeployOnQuorum = new ConcurrentLinkedQueue();
   private long quorumTimerID;
   private long checkQuorumTimerID = -1L;
   private volatile boolean attainedQuorum;
   private volatile FailoverCompleteHandler failoverCompleteHandler;
   private volatile boolean failDuringFailover;
   private volatile boolean stopped;
   private volatile boolean killed;

   public HAManager(VertxInternal vertx, DeploymentManager deploymentManager, VerticleManager verticleFactoryManager, ClusterManager clusterManager, Map clusterMap, int quorumSize, String group) {
      this.vertx = vertx;
      this.deploymentManager = deploymentManager;
      this.verticleFactoryManager = verticleFactoryManager;
      this.clusterManager = clusterManager;
      this.clusterMap = clusterMap;
      this.quorumSize = quorumSize;
      this.group = group;
      this.haInfo = (new JsonObject()).put("verticles", new JsonArray()).put("group", this.group);
      this.nodeID = clusterManager.getNodeId();
   }

   void init() {
      synchronized(this.haInfo) {
         this.clusterMap.put(this.nodeID, this.haInfo.encode());
      }

      this.clusterManager.nodeListener(new NodeListener() {
         public void nodeAdded(String nodeID) {
            HAManager.this.nodeAdded(nodeID);
         }

         public void nodeLeft(String leftNodeID) {
            HAManager.this.nodeLeft(leftNodeID);
         }
      });
      this.quorumTimerID = this.vertx.setPeriodic(1000L, (tid) -> this.checkHADeployments());
      synchronized(this) {
         this.checkQuorum();
      }
   }

   public void removeFromHA(String depID) {
      Deployment dep = this.deploymentManager.getDeployment(depID);
      if (dep != null && dep.deploymentOptions().isHa()) {
         synchronized(this.haInfo) {
            JsonArray haMods = this.haInfo.getJsonArray("verticles");
            Iterator<Object> iter = haMods.iterator();

            while(iter.hasNext()) {
               Object obj = iter.next();
               JsonObject mod = (JsonObject)obj;
               if (mod.getString("dep_id").equals(depID)) {
                  iter.remove();
               }
            }

            this.clusterMap.put(this.nodeID, this.haInfo.encode());
         }
      }
   }

   public void addDataToAHAInfo(String key, JsonObject value) {
      synchronized(this.haInfo) {
         this.haInfo.put(key, value);
         this.clusterMap.put(this.nodeID, this.haInfo.encode());
      }
   }

   public void deployVerticle(String verticleName, DeploymentOptions deploymentOptions, Handler doneHandler) {
      if (this.attainedQuorum) {
         this.doDeployVerticle(verticleName, deploymentOptions, doneHandler);
      } else {
         log.info("Quorum not attained. Deployment of verticle will be delayed until there's a quorum.");
         this.addToHADeployList(verticleName, deploymentOptions, doneHandler);
      }

   }

   public void stop() {
      if (!this.stopped) {
         if (this.clusterManager.isActive()) {
            this.clusterMap.remove(this.nodeID);
         }

         long timerID = this.checkQuorumTimerID;
         if (timerID >= 0L) {
            this.checkQuorumTimerID = -1L;
            this.vertx.cancelTimer(timerID);
         }

         this.vertx.cancelTimer(this.quorumTimerID);
         this.stopped = true;
      }

   }

   public void simulateKill() {
      if (!this.stopped) {
         this.killed = true;
         CountDownLatch latch = new CountDownLatch(1);
         Promise<Void> promise = Promise.promise();
         this.clusterManager.leave(promise);
         promise.future().onFailure((t) -> log.error("Failed to leave cluster", t)).onComplete((ar) -> latch.countDown());
         long timerID = this.checkQuorumTimerID;
         if (timerID >= 0L) {
            this.checkQuorumTimerID = -1L;
            this.vertx.cancelTimer(timerID);
         }

         this.vertx.cancelTimer(this.quorumTimerID);
         boolean interrupted = false;

         try {
            long remainingNanos = TimeUnit.MINUTES.toNanos(1L);
            long end = System.nanoTime() + remainingNanos;

            while(true) {
               try {
                  latch.await(remainingNanos, TimeUnit.NANOSECONDS);
                  break;
               } catch (InterruptedException var14) {
                  interrupted = true;
                  remainingNanos = end - System.nanoTime();
               }
            }
         } finally {
            if (interrupted) {
               Thread.currentThread().interrupt();
            }

         }

         this.stopped = true;
      }

   }

   public void setFailoverCompleteHandler(FailoverCompleteHandler failoverCompleteHandler) {
      this.failoverCompleteHandler = failoverCompleteHandler;
   }

   public boolean isKilled() {
      return this.killed;
   }

   public void failDuringFailover(boolean fail) {
      this.failDuringFailover = fail;
   }

   private void doDeployVerticle(String verticleName, DeploymentOptions deploymentOptions, Handler doneHandler) {
      Handler<AsyncResult<String>> wrappedHandler = (ar1) -> this.vertx.executeBlocking((fut) -> {
            if (ar1.succeeded()) {
               String deploymentID = (String)ar1.result();
               this.addToHA(deploymentID, verticleName, deploymentOptions);
               fut.complete(deploymentID);
            } else {
               fut.fail(ar1.cause());
            }

         }, false, (ar2) -> {
            if (doneHandler != null) {
               doneHandler.handle(ar2);
            } else if (ar2.failed()) {
               log.error("Failed to deploy verticle", ar2.cause());
            }

         });
      this.verticleFactoryManager.deployVerticle(verticleName, deploymentOptions).map(Deployment::deploymentID).onComplete(wrappedHandler);
   }

   private synchronized void nodeAdded(String nodeID) {
      this.addHaInfoIfLost();
      this.checkQuorumWhenAdded(nodeID, System.currentTimeMillis());
   }

   private synchronized void nodeLeft(String leftNodeID) {
      this.addHaInfoIfLost();
      this.checkQuorum();
      if (this.attainedQuorum) {
         String sclusterInfo = (String)this.clusterMap.get(leftNodeID);
         if (sclusterInfo != null) {
            JsonObject clusterInfo = new JsonObject(sclusterInfo);
            this.checkFailover(leftNodeID, clusterInfo);
         }

         List<String> nodes = this.clusterManager.getNodes();

         for(Map.Entry entry : this.clusterMap.entrySet()) {
            if (!leftNodeID.equals(entry.getKey()) && !nodes.contains(entry.getKey())) {
               JsonObject haInfo = new JsonObject((String)entry.getValue());
               this.checkFailover((String)entry.getKey(), haInfo);
            }
         }
      }

   }

   private void addHaInfoIfLost() {
      if (this.clusterManager.getNodes().contains(this.nodeID) && !this.clusterMap.containsKey(this.nodeID)) {
         synchronized(this.haInfo) {
            this.clusterMap.put(this.nodeID, this.haInfo.encode());
         }
      }

   }

   private synchronized void checkQuorumWhenAdded(String nodeID, long start) {
      if (!this.stopped) {
         if (this.clusterMap.containsKey(nodeID)) {
            this.checkQuorum();
         } else {
            this.checkQuorumTimerID = this.vertx.setTimer(200L, (tid) -> {
               this.checkQuorumTimerID = -1L;
               if (!this.stopped) {
                  this.vertx.executeBlockingInternal((fut) -> {
                     if (System.currentTimeMillis() - start > 10000L) {
                        log.warn("Timed out waiting for group information to appear");
                     } else {
                        ((VertxImpl)this.vertx).executeIsolated((v) -> this.checkQuorumWhenAdded(nodeID, start));
                     }

                     fut.complete();
                  }, (Handler)null);
               }

            });
         }
      }

   }

   private void checkQuorum() {
      if (this.quorumSize == 0) {
         this.attainedQuorum = true;
      } else {
         List<String> nodes = this.clusterManager.getNodes();
         int count = 0;

         for(String node : nodes) {
            String json = (String)this.clusterMap.get(node);
            if (json != null) {
               JsonObject clusterInfo = new JsonObject(json);
               String group = clusterInfo.getString("group");
               if (group.equals(this.group)) {
                  ++count;
               }
            } else if (!this.attainedQuorum) {
               this.checkQuorumWhenAdded(node, System.currentTimeMillis());
            }
         }

         boolean attained = count >= this.quorumSize;
         if (!this.attainedQuorum && attained) {
            log.info("A quorum has been obtained. Any deploymentIDs waiting on a quorum will now be deployed");
            this.attainedQuorum = true;
         } else if (this.attainedQuorum && !attained) {
            log.info("There is no longer a quorum. Any HA deploymentIDs will be undeployed until a quorum is re-attained");
            this.attainedQuorum = false;
         }
      }

   }

   private void addToHA(String deploymentID, String verticleName, DeploymentOptions deploymentOptions) {
      synchronized(this.haInfo) {
         JsonObject verticleConf = (new JsonObject()).put("dep_id", deploymentID);
         verticleConf.put("verticle_name", verticleName);
         verticleConf.put("options", deploymentOptions.toJson());
         JsonArray haMods = this.haInfo.getJsonArray("verticles");
         haMods.add(verticleConf);
         String encoded = this.haInfo.encode();
         this.clusterMap.put(this.nodeID, encoded);
      }
   }

   private void addToHADeployList(String verticleName, DeploymentOptions deploymentOptions, Handler doneHandler) {
      this.toDeployOnQuorum.add((Runnable)() -> ((VertxImpl)this.vertx).executeIsolated((v) -> this.deployVerticle(verticleName, deploymentOptions, doneHandler)));
   }

   private void checkHADeployments() {
      try {
         if (this.attainedQuorum) {
            this.deployHADeployments();
         } else {
            this.undeployHADeployments();
         }
      } catch (Throwable t) {
         log.error("Failed when checking HA deploymentIDs", t);
      }

   }

   private void undeployHADeployments() {
      for(String deploymentID : this.deploymentManager.deployments()) {
         Deployment dep = this.deploymentManager.getDeployment(deploymentID);
         if (dep != null && dep.deploymentOptions().isHa()) {
            ((VertxImpl)this.vertx).executeIsolated((v) -> this.deploymentManager.undeployVerticle(deploymentID).onComplete((result) -> {
                  if (result.succeeded()) {
                     log.info("Successfully undeployed HA deployment " + deploymentID + "-" + dep.verticleIdentifier() + " as there is no quorum");
                     this.addToHADeployList(dep.verticleIdentifier(), dep.deploymentOptions(), (result1) -> {
                        if (result1.succeeded()) {
                           log.info("Successfully redeployed verticle " + dep.verticleIdentifier() + " after quorum was re-attained");
                        } else {
                           log.error("Failed to redeploy verticle " + dep.verticleIdentifier() + " after quorum was re-attained", result1.cause());
                        }

                     });
                  } else {
                     log.error("Failed to undeploy deployment on lost quorum", result.cause());
                  }

               }));
         }
      }

   }

   private void deployHADeployments() {
      int size = this.toDeployOnQuorum.size();
      if (size != 0) {
         log.info("There are " + size + " HA deploymentIDs waiting on a quorum. These will now be deployed");

         Runnable task;
         while((task = (Runnable)this.toDeployOnQuorum.poll()) != null) {
            try {
               task.run();
            } catch (Throwable t) {
               log.error("Failed to run redeployment task", t);
            }
         }
      }

   }

   private void checkFailover(String failedNodeID, JsonObject theHAInfo) {
      try {
         JsonArray deployments = theHAInfo.getJsonArray("verticles");
         String group = theHAInfo.getString("group");
         String chosen = this.chooseHashedNode(group, failedNodeID.hashCode());
         if (chosen != null && chosen.equals(this.nodeID)) {
            if (deployments != null && deployments.size() != 0) {
               log.info("node" + this.nodeID + " says: Node " + failedNodeID + " has failed. This node will deploy " + deployments.size() + " deploymentIDs from that node.");

               for(Object obj : deployments) {
                  JsonObject app = (JsonObject)obj;
                  this.processFailover(app);
               }
            }

            this.clusterMap.remove(failedNodeID);
            this.runOnContextAndWait(() -> {
               if (this.failoverCompleteHandler != null) {
                  this.failoverCompleteHandler.handle(failedNodeID, theHAInfo, true);
               }

            });
         }
      } catch (Throwable t) {
         log.error("Failed to handle failover", t);
         this.runOnContextAndWait(() -> {
            if (this.failoverCompleteHandler != null) {
               this.failoverCompleteHandler.handle(failedNodeID, theHAInfo, false);
            }

         });
      }

   }

   private void runOnContextAndWait(Runnable runnable) {
      CountDownLatch latch = new CountDownLatch(1);
      this.vertx.runOnContext((v) -> {
         try {
            runnable.run();
         } finally {
            latch.countDown();
         }

      });

      try {
         latch.await(30L, TimeUnit.SECONDS);
      } catch (InterruptedException var4) {
      }

   }

   private void processFailover(JsonObject failedVerticle) {
      if (this.failDuringFailover) {
         throw new VertxException("Oops!");
      } else {
         String verticleName = failedVerticle.getString("verticle_name");
         CountDownLatch latch = new CountDownLatch(1);
         AtomicReference<Throwable> err = new AtomicReference();
         ((VertxImpl)this.vertx).executeIsolated((v) -> {
            JsonObject options = failedVerticle.getJsonObject("options");
            this.doDeployVerticle(verticleName, new DeploymentOptions(options), (result) -> {
               if (result.succeeded()) {
                  log.info("Successfully redeployed verticle " + verticleName + " after failover");
               } else {
                  log.error("Failed to redeploy verticle after failover", result.cause());
                  err.set(result.cause());
               }

               latch.countDown();
               Throwable t = (Throwable)err.get();
               if (t != null) {
                  throw new VertxException(t);
               }
            });
         });

         try {
            if (!latch.await(120L, TimeUnit.SECONDS)) {
               throw new VertxException("Timed out waiting for redeploy on failover");
            }
         } catch (InterruptedException e) {
            throw new IllegalStateException(e);
         }
      }
   }

   private String chooseHashedNode(String group, int hashCode) {
      List<String> nodes = this.clusterManager.getNodes();
      ArrayList<String> matchingMembers = new ArrayList();

      for(String node : nodes) {
         String sclusterInfo = (String)this.clusterMap.get(node);
         if (sclusterInfo != null) {
            JsonObject clusterInfo = new JsonObject(sclusterInfo);
            String memberGroup = clusterInfo.getString("group");
            if (group == null || group.equals(memberGroup)) {
               matchingMembers.add(node);
            }
         }
      }

      if (!matchingMembers.isEmpty()) {
         long absHash = (long)hashCode + 2147483647L;
         long lpos = absHash % (long)matchingMembers.size();
         return (String)matchingMembers.get((int)lpos);
      } else {
         return null;
      }
   }
}
