package org.apache.curator.shaded.com.google.common.util.concurrent;

import java.lang.ref.WeakReference;
import java.time.Duration;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.common.base.Function;
import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Predicates;
import org.apache.curator.shaded.com.google.common.base.Stopwatch;
import org.apache.curator.shaded.com.google.common.collect.Collections2;
import org.apache.curator.shaded.com.google.common.collect.ImmutableCollection;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSetMultimap;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.MultimapBuilder;
import org.apache.curator.shaded.com.google.common.collect.Multimaps;
import org.apache.curator.shaded.com.google.common.collect.Multiset;
import org.apache.curator.shaded.com.google.common.collect.Ordering;
import org.apache.curator.shaded.com.google.common.collect.SetMultimap;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.concurrent.GuardedBy;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public final class ServiceManager implements ServiceManagerBridge {
   private static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
   private static final ListenerCallQueue.Event HEALTHY_EVENT = new ListenerCallQueue.Event() {
      public void call(Listener listener) {
         listener.healthy();
      }

      public String toString() {
         return "healthy()";
      }
   };
   private static final ListenerCallQueue.Event STOPPED_EVENT = new ListenerCallQueue.Event() {
      public void call(Listener listener) {
         listener.stopped();
      }

      public String toString() {
         return "stopped()";
      }
   };
   private final ServiceManagerState state;
   private final ImmutableList services;

   public ServiceManager(Iterable services) {
      ImmutableList<Service> copy = ImmutableList.copyOf(services);
      if (copy.isEmpty()) {
         logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", new EmptyServiceManagerWarning());
         copy = ImmutableList.of(new NoOpService());
      }

      this.state = new ServiceManagerState(copy);
      this.services = copy;
      WeakReference<ServiceManagerState> stateReference = new WeakReference(this.state);

      for(Service service : copy) {
         service.addListener(new ServiceListener(service, stateReference), MoreExecutors.directExecutor());
         Preconditions.checkArgument(service.state() == Service.State.NEW, "Can only manage NEW services, %s", (Object)service);
      }

      this.state.markReady();
   }

   public void addListener(Listener listener, Executor executor) {
      this.state.addListener(listener, executor);
   }

   @CanIgnoreReturnValue
   public ServiceManager startAsync() {
      for(Service service : this.services) {
         Preconditions.checkState(service.state() == Service.State.NEW, "Not all services are NEW, cannot start %s", (Object)this);
      }

      for(Service service : this.services) {
         try {
            this.state.tryStartTiming(service);
            service.startAsync();
         } catch (IllegalStateException e) {
            logger.log(Level.WARNING, "Unable to start Service " + service, e);
         }
      }

      return this;
   }

   public void awaitHealthy() {
      this.state.awaitHealthy();
   }

   public void awaitHealthy(Duration timeout) throws TimeoutException {
      this.awaitHealthy(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   public void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
      this.state.awaitHealthy(timeout, unit);
   }

   @CanIgnoreReturnValue
   public ServiceManager stopAsync() {
      for(Service service : this.services) {
         service.stopAsync();
      }

      return this;
   }

   public void awaitStopped() {
      this.state.awaitStopped();
   }

   public void awaitStopped(Duration timeout) throws TimeoutException {
      this.awaitStopped(Internal.toNanosSaturated(timeout), TimeUnit.NANOSECONDS);
   }

   public void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
      this.state.awaitStopped(timeout, unit);
   }

   public boolean isHealthy() {
      for(Service service : this.services) {
         if (!service.isRunning()) {
            return false;
         }
      }

      return true;
   }

   public ImmutableSetMultimap servicesByState() {
      return this.state.servicesByState();
   }

   public ImmutableMap startupTimes() {
      return this.state.startupTimes();
   }

   public ImmutableMap startupDurations() {
      return ImmutableMap.copyOf(Maps.transformValues((Map)this.startupTimes(), Duration::ofMillis));
   }

   public String toString() {
      return MoreObjects.toStringHelper(ServiceManager.class).add("services", Collections2.filter(this.services, Predicates.not(Predicates.instanceOf(NoOpService.class)))).toString();
   }

   public abstract static class Listener {
      public void healthy() {
      }

      public void stopped() {
      }

      public void failure(Service service) {
      }
   }

   private static final class ServiceManagerState {
      final Monitor monitor = new Monitor();
      @GuardedBy("monitor")
      final SetMultimap servicesByState = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
      @GuardedBy("monitor")
      final Multiset states;
      @GuardedBy("monitor")
      final Map startupTimers;
      @GuardedBy("monitor")
      boolean ready;
      @GuardedBy("monitor")
      boolean transitioned;
      final int numberOfServices;
      final Monitor.Guard awaitHealthGuard;
      final Monitor.Guard stoppedGuard;
      final ListenerCallQueue listeners;

      ServiceManagerState(ImmutableCollection services) {
         this.states = this.servicesByState.keys();
         this.startupTimers = Maps.newIdentityHashMap();
         this.awaitHealthGuard = new AwaitHealthGuard();
         this.stoppedGuard = new StoppedGuard();
         this.listeners = new ListenerCallQueue();
         this.numberOfServices = services.size();
         this.servicesByState.putAll(Service.State.NEW, services);
      }

      void tryStartTiming(Service service) {
         this.monitor.enter();

         try {
            Stopwatch stopwatch = (Stopwatch)this.startupTimers.get(service);
            if (stopwatch == null) {
               this.startupTimers.put(service, Stopwatch.createStarted());
            }
         } finally {
            this.monitor.leave();
         }

      }

      void markReady() {
         this.monitor.enter();

         try {
            if (this.transitioned) {
               List<Service> servicesInBadStates = Lists.newArrayList();

               for(Service service : this.servicesByState().values()) {
                  if (service.state() != Service.State.NEW) {
                     servicesInBadStates.add(service);
                  }
               }

               throw new IllegalArgumentException("Services started transitioning asynchronously before the ServiceManager was constructed: " + servicesInBadStates);
            }

            this.ready = true;
         } finally {
            this.monitor.leave();
         }

      }

      void addListener(Listener listener, Executor executor) {
         this.listeners.addListener(listener, executor);
      }

      void awaitHealthy() {
         this.monitor.enterWhenUninterruptibly(this.awaitHealthGuard);

         try {
            this.checkHealthy();
         } finally {
            this.monitor.leave();
         }

      }

      void awaitHealthy(long timeout, TimeUnit unit) throws TimeoutException {
         this.monitor.enter();

         try {
            if (!this.monitor.waitForUninterruptibly(this.awaitHealthGuard, timeout, unit)) {
               throw new TimeoutException("Timeout waiting for the services to become healthy. The following services have not started: " + Multimaps.filterKeys(this.servicesByState, Predicates.in(ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
            }

            this.checkHealthy();
         } finally {
            this.monitor.leave();
         }

      }

      void awaitStopped() {
         this.monitor.enterWhenUninterruptibly(this.stoppedGuard);
         this.monitor.leave();
      }

      void awaitStopped(long timeout, TimeUnit unit) throws TimeoutException {
         this.monitor.enter();

         try {
            if (!this.monitor.waitForUninterruptibly(this.stoppedGuard, timeout, unit)) {
               throw new TimeoutException("Timeout waiting for the services to stop. The following services have not stopped: " + Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
            }
         } finally {
            this.monitor.leave();
         }

      }

      ImmutableSetMultimap servicesByState() {
         ImmutableSetMultimap.Builder<Service.State, Service> builder = ImmutableSetMultimap.builder();
         this.monitor.enter();

         try {
            for(Map.Entry entry : this.servicesByState.entries()) {
               if (!(entry.getValue() instanceof NoOpService)) {
                  builder.put(entry);
               }
            }
         } finally {
            this.monitor.leave();
         }

         return builder.build();
      }

      ImmutableMap startupTimes() {
         this.monitor.enter();

         List<Map.Entry<Service, Long>> loadTimes;
         try {
            loadTimes = Lists.newArrayListWithCapacity(this.startupTimers.size());

            for(Map.Entry entry : this.startupTimers.entrySet()) {
               Service service = (Service)entry.getKey();
               Stopwatch stopwatch = (Stopwatch)entry.getValue();
               if (!stopwatch.isRunning() && !(service instanceof NoOpService)) {
                  loadTimes.add(Maps.immutableEntry(service, stopwatch.elapsed(TimeUnit.MILLISECONDS)));
               }
            }
         } finally {
            this.monitor.leave();
         }

         Collections.sort(loadTimes, Ordering.natural().onResultOf(new Function() {
            public Long apply(Map.Entry input) {
               return (Long)input.getValue();
            }
         }));
         return ImmutableMap.copyOf((Iterable)loadTimes);
      }

      void transitionService(final Service service, Service.State from, Service.State to) {
         Preconditions.checkNotNull(service);
         Preconditions.checkArgument(from != to);
         this.monitor.enter();

         try {
            this.transitioned = true;
            if (this.ready) {
               Preconditions.checkState(this.servicesByState.remove(from, service), "Service %s not at the expected location in the state map %s", service, from);
               Preconditions.checkState(this.servicesByState.put(to, service), "Service %s in the state map unexpectedly at %s", service, to);
               Stopwatch stopwatch = (Stopwatch)this.startupTimers.get(service);
               if (stopwatch == null) {
                  stopwatch = Stopwatch.createStarted();
                  this.startupTimers.put(service, stopwatch);
               }

               if (to.compareTo(Service.State.RUNNING) >= 0 && stopwatch.isRunning()) {
                  stopwatch.stop();
                  if (!(service instanceof NoOpService)) {
                     ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                  }
               }

               if (to == Service.State.FAILED) {
                  this.enqueueFailedEvent(service);
               }

               if (this.states.count(Service.State.RUNNING) == this.numberOfServices) {
                  this.enqueueHealthyEvent();
               } else if (this.states.count(Service.State.TERMINATED) + this.states.count(Service.State.FAILED) == this.numberOfServices) {
                  this.enqueueStoppedEvent();
                  return;
               }

               return;
            }
         } finally {
            this.monitor.leave();
            this.dispatchListenerEvents();
         }

      }

      void enqueueStoppedEvent() {
         this.listeners.enqueue(ServiceManager.STOPPED_EVENT);
      }

      void enqueueHealthyEvent() {
         this.listeners.enqueue(ServiceManager.HEALTHY_EVENT);
      }

      void enqueueFailedEvent(final Service service) {
         this.listeners.enqueue(new ListenerCallQueue.Event() {
            public void call(Listener listener) {
               listener.failure(service);
            }

            public String toString() {
               return "failed({service=" + service + "})";
            }
         });
      }

      void dispatchListenerEvents() {
         Preconditions.checkState(!this.monitor.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
         this.listeners.dispatch();
      }

      @GuardedBy("monitor")
      void checkHealthy() {
         if (this.states.count(Service.State.RUNNING) != this.numberOfServices) {
            IllegalStateException exception = new IllegalStateException("Expected to be healthy after starting. The following services are not running: " + Multimaps.filterKeys(this.servicesByState, Predicates.not(Predicates.equalTo(Service.State.RUNNING))));

            for(Service service : this.servicesByState.get(Service.State.FAILED)) {
               exception.addSuppressed(new FailedService(service));
            }

            throw exception;
         }
      }

      final class AwaitHealthGuard extends Monitor.Guard {
         AwaitHealthGuard() {
            super(ServiceManagerState.this.monitor);
         }

         @GuardedBy("ServiceManagerState.this.monitor")
         public boolean isSatisfied() {
            return ServiceManagerState.this.states.count(Service.State.RUNNING) == ServiceManagerState.this.numberOfServices || ServiceManagerState.this.states.contains(Service.State.STOPPING) || ServiceManagerState.this.states.contains(Service.State.TERMINATED) || ServiceManagerState.this.states.contains(Service.State.FAILED);
         }
      }

      final class StoppedGuard extends Monitor.Guard {
         StoppedGuard() {
            super(ServiceManagerState.this.monitor);
         }

         @GuardedBy("ServiceManagerState.this.monitor")
         public boolean isSatisfied() {
            return ServiceManagerState.this.states.count(Service.State.TERMINATED) + ServiceManagerState.this.states.count(Service.State.FAILED) == ServiceManagerState.this.numberOfServices;
         }
      }
   }

   private static final class ServiceListener extends Service.Listener {
      final Service service;
      final WeakReference state;

      ServiceListener(Service service, WeakReference state) {
         this.service = service;
         this.state = state;
      }

      public void starting() {
         ServiceManagerState state = (ServiceManagerState)this.state.get();
         if (state != null) {
            state.transitionService(this.service, Service.State.NEW, Service.State.STARTING);
            if (!(this.service instanceof NoOpService)) {
               ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.service);
            }
         }

      }

      public void running() {
         ServiceManagerState state = (ServiceManagerState)this.state.get();
         if (state != null) {
            state.transitionService(this.service, Service.State.STARTING, Service.State.RUNNING);
         }

      }

      public void stopping(Service.State from) {
         ServiceManagerState state = (ServiceManagerState)this.state.get();
         if (state != null) {
            state.transitionService(this.service, from, Service.State.STOPPING);
         }

      }

      public void terminated(Service.State from) {
         ServiceManagerState state = (ServiceManagerState)this.state.get();
         if (state != null) {
            if (!(this.service instanceof NoOpService)) {
               ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.service, from});
            }

            state.transitionService(this.service, from, Service.State.TERMINATED);
         }

      }

      public void failed(Service.State from, Throwable failure) {
         ServiceManagerState state = (ServiceManagerState)this.state.get();
         if (state != null) {
            boolean log = !(this.service instanceof NoOpService);
            log &= from != Service.State.STARTING;
            if (log) {
               ServiceManager.logger.log(Level.SEVERE, "Service " + this.service + " has failed in the " + from + " state.", failure);
            }

            state.transitionService(this.service, from, Service.State.FAILED);
         }

      }
   }

   private static final class NoOpService extends AbstractService {
      private NoOpService() {
      }

      protected void doStart() {
         this.notifyStarted();
      }

      protected void doStop() {
         this.notifyStopped();
      }
   }

   private static final class EmptyServiceManagerWarning extends Throwable {
      private EmptyServiceManagerWarning() {
      }
   }

   private static final class FailedService extends Throwable {
      FailedService(Service service) {
         super(service.toString(), service.failureCause(), false, false);
      }
   }
}
