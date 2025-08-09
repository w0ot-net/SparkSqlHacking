package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
class Subscriber {
   @Weak
   private EventBus bus;
   @VisibleForTesting
   final Object target;
   private final Method method;
   private final Executor executor;

   static Subscriber create(EventBus bus, Object listener, Method method) {
      return (Subscriber)(isDeclaredThreadSafe(method) ? new Subscriber(bus, listener, method) : new SynchronizedSubscriber(bus, listener, method));
   }

   private Subscriber(EventBus bus, Object target, Method method) {
      this.bus = bus;
      this.target = Preconditions.checkNotNull(target);
      this.method = method;
      method.setAccessible(true);
      this.executor = bus.executor();
   }

   final void dispatchEvent(Object event) {
      this.executor.execute(() -> {
         try {
            this.invokeSubscriberMethod(event);
         } catch (InvocationTargetException e) {
            this.bus.handleSubscriberException(e.getCause(), this.context(event));
         }

      });
   }

   @VisibleForTesting
   void invokeSubscriberMethod(Object event) throws InvocationTargetException {
      try {
         this.method.invoke(this.target, Preconditions.checkNotNull(event));
      } catch (IllegalArgumentException e) {
         throw new Error("Method rejected target/argument: " + event, e);
      } catch (IllegalAccessException e) {
         throw new Error("Method became inaccessible: " + event, e);
      } catch (InvocationTargetException e) {
         if (e.getCause() instanceof Error) {
            throw (Error)e.getCause();
         } else {
            throw e;
         }
      }
   }

   private SubscriberExceptionContext context(Object event) {
      return new SubscriberExceptionContext(this.bus, event, this.target, this.method);
   }

   public final int hashCode() {
      return (31 + this.method.hashCode()) * 31 + System.identityHashCode(this.target);
   }

   public final boolean equals(@CheckForNull Object obj) {
      if (!(obj instanceof Subscriber)) {
         return false;
      } else {
         Subscriber that = (Subscriber)obj;
         return this.target == that.target && this.method.equals(that.method);
      }
   }

   private static boolean isDeclaredThreadSafe(Method method) {
      return method.getAnnotation(AllowConcurrentEvents.class) != null;
   }

   @VisibleForTesting
   static final class SynchronizedSubscriber extends Subscriber {
      private SynchronizedSubscriber(EventBus bus, Object target, Method method) {
         super(bus, target, method, null);
      }

      void invokeSubscriberMethod(Object event) throws InvocationTargetException {
         synchronized(this) {
            super.invokeSubscriberMethod(event);
         }
      }
   }
}
