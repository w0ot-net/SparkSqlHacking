package io.fabric8.kubernetes.client.extended.leaderelection;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.extended.leaderelection.resourcelock.LeaderElectionRecord;
import io.fabric8.kubernetes.client.extended.leaderelection.resourcelock.Lock;
import io.fabric8.kubernetes.client.utils.Utils;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderElector {
   private static final Logger LOGGER = LoggerFactory.getLogger(LeaderElector.class);
   protected static final Double JITTER_FACTOR = 1.2;
   private final KubernetesClient kubernetesClient;
   private final LeaderElectionConfig leaderElectionConfig;
   private final AtomicReference observedRecord = new AtomicReference();
   private final Executor executor;
   private boolean started;
   private boolean stopped;

   public LeaderElector(KubernetesClient kubernetesClient, LeaderElectionConfig leaderElectionConfig, Executor executor) {
      this.kubernetesClient = kubernetesClient;
      this.leaderElectionConfig = leaderElectionConfig;
      this.executor = executor;
   }

   public void run() {
      CompletableFuture<?> acquire = this.start();

      try {
         acquire.get();
      } catch (InterruptedException var3) {
         acquire.cancel(true);
         Thread.currentThread().interrupt();
      } catch (ExecutionException e) {
         LOGGER.error("Exception during leader election", e);
      }

   }

   public CompletableFuture start() {
      synchronized(this) {
         if (this.started || this.stopped) {
            throw new IllegalStateException("LeaderElector may only be used once, please create another instance");
         }

         this.started = true;
      }

      LOGGER.debug("Leader election started");
      CompletableFuture<Void> result = new CompletableFuture();
      CompletableFuture<?> acquireFuture = this.acquire();
      acquireFuture.whenComplete((v, t) -> {
         if (t == null) {
            CompletableFuture<?> renewFuture = this.renewWithTimeout();
            result.whenComplete((v1, t1) -> renewFuture.cancel(true));
            renewFuture.whenComplete((v1, t1) -> {
               this.stopLeading();
               if (t1 != null) {
                  result.completeExceptionally(t1);
               } else {
                  result.complete((Object)null);
               }

            });
         } else {
            if (!(t instanceof CancellationException)) {
               LOGGER.error("Exception during leader election", t);
            }

            this.stopLeading();
         }

      });
      result.whenComplete((v, t) -> acquireFuture.cancel(true));
      return result;
   }

   private synchronized void stopLeading() {
      this.stopped = true;
      LeaderElectionRecord current = (LeaderElectionRecord)this.observedRecord.get();
      if (current != null && this.isLeader(current)) {
         if (this.leaderElectionConfig.isReleaseOnCancel()) {
            try {
               if (this.release()) {
                  return;
               }
            } catch (KubernetesClientException e) {
               String lockDescription = this.leaderElectionConfig.getLock().describe();
               if (e.getCode() != 409) {
                  LOGGER.error("Exception occurred while releasing lock '{}' on cancel", lockDescription, e);
               } else {
                  LOGGER.debug("Leadership was likely already lost '{}'", lockDescription, e);
               }
            }
         }

         this.leaderElectionConfig.getLeaderCallbacks().onStopLeading();
      }
   }

   public synchronized boolean release() {
      LeaderElectionRecord current = this.leaderElectionConfig.getLock().get(this.kubernetesClient);
      if (current != null && this.isLeader(current)) {
         ZonedDateTime now = now();
         LeaderElectionRecord newLeaderElectionRecord = new LeaderElectionRecord("", Duration.ofSeconds(1L), now, now, current.getLeaderTransitions());
         this.leaderElectionConfig.getLock().update(this.kubernetesClient, newLeaderElectionRecord);
         this.updateObserved(newLeaderElectionRecord);
         return true;
      } else {
         return false;
      }
   }

   private CompletableFuture acquire() {
      String lockDescription = this.leaderElectionConfig.getLock().describe();
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("Attempting to acquire leader lease '{}'...", lockDescription);
      }

      return loop((completion) -> {
         try {
            if (this.tryAcquireOrRenew()) {
               completion.complete((Object)null);
               if (LOGGER.isDebugEnabled()) {
                  LOGGER.debug("Acquired lease '{}'", lockDescription);
               }
            } else if (LOGGER.isDebugEnabled()) {
               LOGGER.debug("Failed to acquire lease '{}' retrying...", lockDescription);
            }
         } catch (KubernetesClientException exception) {
            if (exception.getCode() == 409) {
               LOGGER.debug("Conflict while acquiring lock '{} retrying...'", lockDescription, exception);
            } else {
               LOGGER.warn("Exception occurred while acquiring lock '{} retrying...'", lockDescription, exception);
            }
         }

      }, () -> jitter(this.leaderElectionConfig.getRetryPeriod(), JITTER_FACTOR).toMillis(), this.executor);
   }

   private CompletableFuture renewWithTimeout() {
      String lockDescription = this.leaderElectionConfig.getLock().describe();
      LOGGER.debug("Attempting to renew leader lease '{}'...", lockDescription);
      AtomicLong renewBy = new AtomicLong(System.currentTimeMillis() + this.leaderElectionConfig.getRenewDeadline().toMillis());
      return loop((completion) -> {
         if (System.currentTimeMillis() > renewBy.get()) {
            LOGGER.debug("Renew deadline reached after {} seconds while renewing lock {}", this.leaderElectionConfig.getRenewDeadline().get(ChronoUnit.SECONDS), lockDescription);
            completion.complete((Object)null);
         } else {
            try {
               if (this.tryAcquireOrRenew()) {
                  renewBy.set(System.currentTimeMillis() + this.leaderElectionConfig.getRenewDeadline().toMillis());
               } else {
                  completion.complete((Object)null);
               }
            } catch (KubernetesClientException exception) {
               LOGGER.warn("Exception occurred while acquiring lock '{} retrying...'", lockDescription, exception);
            }

         }
      }, () -> this.leaderElectionConfig.getRetryPeriod().toMillis(), this.executor);
   }

   synchronized boolean tryAcquireOrRenew() {
      if (this.stopped) {
         return false;
      } else {
         Lock lock = this.leaderElectionConfig.getLock();
         ZonedDateTime now = now();
         LeaderElectionRecord oldLeaderElectionRecord = lock.get(this.kubernetesClient);
         if (oldLeaderElectionRecord == null) {
            LeaderElectionRecord newLeaderElectionRecord = new LeaderElectionRecord(lock.identity(), this.leaderElectionConfig.getLeaseDuration(), now, now, 0);
            lock.create(this.kubernetesClient, newLeaderElectionRecord);
            this.updateObserved(newLeaderElectionRecord);
            return true;
         } else {
            this.updateObserved(oldLeaderElectionRecord);
            boolean isLeader = this.isLeader(oldLeaderElectionRecord);
            if (!isLeader && !this.canBecomeLeader(oldLeaderElectionRecord)) {
               LOGGER.debug("Lock is held by {} and has not yet expired", oldLeaderElectionRecord.getHolderIdentity());
               return false;
            } else {
               LeaderElectionRecord newLeaderElectionRecord = new LeaderElectionRecord(lock.identity(), this.leaderElectionConfig.getLeaseDuration(), isLeader ? oldLeaderElectionRecord.getAcquireTime() : now, now, oldLeaderElectionRecord.getLeaderTransitions() + (isLeader ? 0 : 1));
               lock.update(this.kubernetesClient, newLeaderElectionRecord);
               this.updateObserved(newLeaderElectionRecord);
               return true;
            }
         }
      }
   }

   private void updateObserved(LeaderElectionRecord leaderElectionRecord) {
      LeaderElectionRecord current = (LeaderElectionRecord)this.observedRecord.getAndSet(leaderElectionRecord);
      if (!Objects.equals(leaderElectionRecord, current)) {
         String currentLeader = current == null ? null : current.getHolderIdentity();
         String newLeader = leaderElectionRecord.getHolderIdentity();
         if (!Objects.equals(newLeader, currentLeader)) {
            LOGGER.debug("Leader changed from {} to {}", currentLeader, newLeader);
            this.leaderElectionConfig.getLeaderCallbacks().onNewLeader(newLeader);
            if (Objects.equals(currentLeader, this.leaderElectionConfig.getLock().identity())) {
               this.leaderElectionConfig.getLeaderCallbacks().onStopLeading();
            } else if (Objects.equals(newLeader, this.leaderElectionConfig.getLock().identity())) {
               LOGGER.debug("Successfully Acquired leader lease '{}'", this.leaderElectionConfig.getLock().describe());
               this.leaderElectionConfig.getLeaderCallbacks().onStartLeading();
            }
         }
      }

   }

   protected final boolean isLeader(LeaderElectionRecord leaderElectionRecord) {
      return Objects.equals(this.leaderElectionConfig.getLock().identity(), leaderElectionRecord.getHolderIdentity());
   }

   protected final boolean canBecomeLeader(LeaderElectionRecord leaderElectionRecord) {
      return Utils.isNullOrEmpty(leaderElectionRecord.getHolderIdentity()) || now().isAfter(leaderElectionRecord.getRenewTime().plus(this.leaderElectionConfig.getLeaseDuration()));
   }

   protected static CompletableFuture loop(Consumer consumer, LongSupplier delaySupplier, Executor executor) {
      CompletableFuture<Void> completion = new CompletableFuture();
      Utils.scheduleWithVariableRate(completion, executor, () -> consumer.accept(completion), 0L, delaySupplier, TimeUnit.MILLISECONDS);
      return completion;
   }

   protected static ZonedDateTime now() {
      return ZonedDateTime.now(ZoneOffset.UTC);
   }

   protected static Duration jitter(Duration duration, double maxFactor) {
      maxFactor = maxFactor > (double)0.0F ? maxFactor : (double)1.0F;
      return duration.plusMillis(Double.valueOf((double)duration.toMillis() * Math.random() * maxFactor).longValue());
   }
}
