package org.glassfish.hk2.internal;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.DescriptorVisibility;
import org.glassfish.hk2.api.DynamicConfigurationListener;
import org.glassfish.hk2.api.ErrorInformation;
import org.glassfish.hk2.api.ErrorService;
import org.glassfish.hk2.api.ErrorType;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.ImmediateController;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.Operation;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ValidationInformation;
import org.glassfish.hk2.api.ValidationService;
import org.glassfish.hk2.api.Validator;
import org.glassfish.hk2.api.Visibility;
import org.glassfish.hk2.utilities.ImmediateContext;

@Singleton
@Visibility(DescriptorVisibility.LOCAL)
public class ImmediateHelper implements DynamicConfigurationListener, Runnable, ValidationService, ErrorService, Validator, ImmediateController {
   private static final ThreadFactory THREAD_FACTORY = new ImmediateThreadFactory();
   private static final Executor DEFAULT_EXECUTOR;
   private final ServiceLocator locator;
   private final ImmediateContext immediateContext;
   private final HashSet tidsWithWork = new HashSet();
   private final Object queueLock = new Object();
   private boolean threadAvailable;
   private boolean outstandingJob;
   private boolean waitingForWork;
   private boolean firstTime = true;
   private ImmediateController.ImmediateServiceState currentState;
   private Executor currentExecutor;
   private long decayTime;

   @Inject
   private ImmediateHelper(ServiceLocator serviceLocator, ImmediateContext immediateContext) {
      this.currentState = ImmediateController.ImmediateServiceState.SUSPENDED;
      this.currentExecutor = DEFAULT_EXECUTOR;
      this.decayTime = 20000L;
      this.locator = serviceLocator;
      this.immediateContext = immediateContext;
   }

   private boolean hasWork() {
      long tid = Thread.currentThread().getId();
      boolean wasFirst = this.firstTime;
      this.firstTime = false;
      boolean retVal = this.tidsWithWork.contains(tid);
      this.tidsWithWork.remove(tid);
      if (!retVal && wasFirst) {
         List<ActiveDescriptor<?>> immediates = this.getImmediateServices();
         return !immediates.isEmpty();
      } else {
         return retVal;
      }
   }

   private void doWorkIfWeHaveSome() {
      if (this.hasWork()) {
         this.outstandingJob = true;
         if (!this.threadAvailable) {
            this.threadAvailable = true;
            this.currentExecutor.execute(this);
         } else if (this.waitingForWork) {
            this.queueLock.notify();
         }

      }
   }

   public void configurationChanged() {
      synchronized(this.queueLock) {
         if (!this.currentState.equals(ImmediateController.ImmediateServiceState.SUSPENDED)) {
            this.doWorkIfWeHaveSome();
         }
      }
   }

   public Filter getLookupFilter() {
      return this.immediateContext.getValidationFilter();
   }

   public Validator getValidator() {
      return this;
   }

   public void onFailure(ErrorInformation errorInformation) throws MultiException {
      if (!ErrorType.DYNAMIC_CONFIGURATION_FAILURE.equals(errorInformation.getErrorType())) {
         long tid = Thread.currentThread().getId();
         synchronized(this.queueLock) {
            this.tidsWithWork.remove(tid);
         }
      }
   }

   public boolean validate(ValidationInformation info) {
      if (info.getOperation().equals(Operation.BIND) || info.getOperation().equals(Operation.UNBIND)) {
         long tid = Thread.currentThread().getId();
         synchronized(this.queueLock) {
            this.tidsWithWork.add(tid);
         }
      }

      return true;
   }

   public void run() {
      while(true) {
         synchronized(this.queueLock) {
            long elapsedTime;
            for(long decayTime = this.decayTime; this.currentState.equals(ImmediateController.ImmediateServiceState.RUNNING) && !this.outstandingJob && decayTime > 0L; decayTime -= elapsedTime) {
               this.waitingForWork = true;
               long currentTime = System.currentTimeMillis();

               try {
                  this.queueLock.wait(decayTime);
               } catch (InterruptedException var9) {
                  this.threadAvailable = false;
                  this.waitingForWork = false;
                  return;
               }

               elapsedTime = System.currentTimeMillis() - currentTime;
            }

            this.waitingForWork = false;
            if (!this.outstandingJob || this.currentState.equals(ImmediateController.ImmediateServiceState.SUSPENDED)) {
               this.threadAvailable = false;
               return;
            }

            this.outstandingJob = false;
         }

         this.immediateContext.doWork();
      }
   }

   public Executor getExecutor() {
      synchronized(this.queueLock) {
         return this.currentExecutor;
      }
   }

   public void setExecutor(Executor executor) throws IllegalStateException {
      synchronized(this.queueLock) {
         if (this.currentState.equals(ImmediateController.ImmediateServiceState.RUNNING)) {
            throw new IllegalStateException("ImmediateSerivce attempt made to change executor while in RUNNING state");
         } else {
            this.currentExecutor = executor == null ? DEFAULT_EXECUTOR : executor;
         }
      }
   }

   public long getThreadInactivityTimeout() {
      synchronized(this.queueLock) {
         return this.decayTime;
      }
   }

   public void setThreadInactivityTimeout(long timeInMillis) throws IllegalStateException {
      synchronized(this.queueLock) {
         if (timeInMillis < 0L) {
            throw new IllegalArgumentException();
         } else {
            this.decayTime = timeInMillis;
         }
      }
   }

   public ImmediateController.ImmediateServiceState getImmediateState() {
      synchronized(this.queueLock) {
         return this.currentState;
      }
   }

   public void setImmediateState(ImmediateController.ImmediateServiceState state) {
      synchronized(this.queueLock) {
         if (state == null) {
            throw new IllegalArgumentException();
         } else if (state != this.currentState) {
            this.currentState = state;
            if (this.currentState.equals(ImmediateController.ImmediateServiceState.RUNNING)) {
               this.doWorkIfWeHaveSome();
            }

         }
      }
   }

   private List getImmediateServices() {
      List<ActiveDescriptor<?>> inScopeAndInThisLocator;
      try {
         inScopeAndInThisLocator = this.locator.getDescriptors(this.immediateContext.getValidationFilter());
      } catch (IllegalStateException var3) {
         inScopeAndInThisLocator = Collections.emptyList();
      }

      return inScopeAndInThisLocator;
   }

   static {
      DEFAULT_EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(true), THREAD_FACTORY);
   }

   private static class ImmediateThread extends Thread {
      private ImmediateThread(Runnable r) {
         super(r);
         this.setDaemon(true);
         String var10001 = this.getClass().getSimpleName();
         this.setName(var10001 + "-" + System.currentTimeMillis());
      }
   }

   private static class ImmediateThreadFactory implements ThreadFactory {
      public Thread newThread(Runnable runnable) {
         Thread activeThread = new ImmediateThread(runnable);
         return activeThread;
      }
   }
}
