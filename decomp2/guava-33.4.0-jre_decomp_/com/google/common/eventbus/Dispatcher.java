package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ElementTypesAreNonnullByDefault
abstract class Dispatcher {
   static Dispatcher perThreadDispatchQueue() {
      return new PerThreadQueuedDispatcher();
   }

   static Dispatcher legacyAsync() {
      return new LegacyAsyncDispatcher();
   }

   static Dispatcher immediate() {
      return Dispatcher.ImmediateDispatcher.INSTANCE;
   }

   abstract void dispatch(Object event, Iterator subscribers);

   private static final class PerThreadQueuedDispatcher extends Dispatcher {
      private final ThreadLocal queue;
      private final ThreadLocal dispatching;

      private PerThreadQueuedDispatcher() {
         this.queue = new ThreadLocal() {
            protected Queue initialValue() {
               return Queues.newArrayDeque();
            }
         };
         this.dispatching = new ThreadLocal() {
            protected Boolean initialValue() {
               return false;
            }
         };
      }

      void dispatch(Object event, Iterator subscribers) {
         Preconditions.checkNotNull(event);
         Preconditions.checkNotNull(subscribers);
         Queue<Event> queueForThread = (Queue)Objects.requireNonNull((Queue)this.queue.get());
         queueForThread.offer(new Event(event, subscribers));
         if (!(Boolean)this.dispatching.get()) {
            this.dispatching.set(true);

            Event nextEvent;
            try {
               while((nextEvent = (Event)queueForThread.poll()) != null) {
                  while(nextEvent.subscribers.hasNext()) {
                     ((Subscriber)nextEvent.subscribers.next()).dispatchEvent(nextEvent.event);
                  }
               }
            } finally {
               this.dispatching.remove();
               this.queue.remove();
            }
         }

      }

      private static final class Event {
         private final Object event;
         private final Iterator subscribers;

         private Event(Object event, Iterator subscribers) {
            this.event = event;
            this.subscribers = subscribers;
         }
      }
   }

   private static final class LegacyAsyncDispatcher extends Dispatcher {
      private final ConcurrentLinkedQueue queue;

      private LegacyAsyncDispatcher() {
         this.queue = Queues.newConcurrentLinkedQueue();
      }

      void dispatch(Object event, Iterator subscribers) {
         Preconditions.checkNotNull(event);

         while(subscribers.hasNext()) {
            this.queue.add(new EventWithSubscriber(event, (Subscriber)subscribers.next()));
         }

         EventWithSubscriber e;
         while((e = (EventWithSubscriber)this.queue.poll()) != null) {
            e.subscriber.dispatchEvent(e.event);
         }

      }

      private static final class EventWithSubscriber {
         private final Object event;
         private final Subscriber subscriber;

         private EventWithSubscriber(Object event, Subscriber subscriber) {
            this.event = event;
            this.subscriber = subscriber;
         }
      }
   }

   private static final class ImmediateDispatcher extends Dispatcher {
      private static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

      void dispatch(Object event, Iterator subscribers) {
         Preconditions.checkNotNull(event);

         while(subscribers.hasNext()) {
            ((Subscriber)subscribers.next()).dispatchEvent(event);
         }

      }
   }
}
