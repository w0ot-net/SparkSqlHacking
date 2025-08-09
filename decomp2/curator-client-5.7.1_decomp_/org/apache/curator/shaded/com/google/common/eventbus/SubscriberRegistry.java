package org.apache.curator.shaded.com.google.common.eventbus;

import java.lang.reflect.Method;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.MoreObjects;
import org.apache.curator.shaded.com.google.common.base.Objects;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Throwables;
import org.apache.curator.shaded.com.google.common.cache.CacheBuilder;
import org.apache.curator.shaded.com.google.common.cache.CacheLoader;
import org.apache.curator.shaded.com.google.common.cache.LoadingCache;
import org.apache.curator.shaded.com.google.common.collect.HashMultimap;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.ImmutableSet;
import org.apache.curator.shaded.com.google.common.collect.Iterators;
import org.apache.curator.shaded.com.google.common.collect.Lists;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.collect.Multimap;
import org.apache.curator.shaded.com.google.common.primitives.Primitives;
import org.apache.curator.shaded.com.google.common.reflect.TypeToken;
import org.apache.curator.shaded.com.google.common.util.concurrent.UncheckedExecutionException;
import org.apache.curator.shaded.com.google.j2objc.annotations.Weak;

@ElementTypesAreNonnullByDefault
final class SubscriberRegistry {
   private final ConcurrentMap subscribers = Maps.newConcurrentMap();
   @Weak
   private final EventBus bus;
   private static final LoadingCache subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader() {
      public ImmutableList load(Class concreteClass) throws Exception {
         return SubscriberRegistry.getAnnotatedMethodsNotCached(concreteClass);
      }
   });
   private static final LoadingCache flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader() {
      public ImmutableSet load(Class concreteClass) {
         return ImmutableSet.copyOf((Collection)TypeToken.of(concreteClass).getTypes().rawTypes());
      }
   });

   SubscriberRegistry(EventBus bus) {
      this.bus = (EventBus)Preconditions.checkNotNull(bus);
   }

   void register(Object listener) {
      Multimap<Class<?>, Subscriber> listenerMethods = this.findAllSubscribers(listener);

      for(Map.Entry entry : listenerMethods.asMap().entrySet()) {
         Class<?> eventType = (Class)entry.getKey();
         Collection<Subscriber> eventMethodsInListener = (Collection)entry.getValue();
         CopyOnWriteArraySet<Subscriber> eventSubscribers = (CopyOnWriteArraySet)this.subscribers.get(eventType);
         if (eventSubscribers == null) {
            CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet();
            eventSubscribers = (CopyOnWriteArraySet)MoreObjects.firstNonNull((CopyOnWriteArraySet)this.subscribers.putIfAbsent(eventType, newSet), newSet);
         }

         eventSubscribers.addAll(eventMethodsInListener);
      }

   }

   void unregister(Object listener) {
      Multimap<Class<?>, Subscriber> listenerMethods = this.findAllSubscribers(listener);

      for(Map.Entry entry : listenerMethods.asMap().entrySet()) {
         Class<?> eventType = (Class)entry.getKey();
         Collection<Subscriber> listenerMethodsForType = (Collection)entry.getValue();
         CopyOnWriteArraySet<Subscriber> currentSubscribers = (CopyOnWriteArraySet)this.subscribers.get(eventType);
         if (currentSubscribers == null || !currentSubscribers.removeAll(listenerMethodsForType)) {
            throw new IllegalArgumentException("missing event subscriber for an annotated method. Is " + listener + " registered?");
         }
      }

   }

   @VisibleForTesting
   Set getSubscribersForTesting(Class eventType) {
      return (Set)MoreObjects.firstNonNull((AbstractCollection)this.subscribers.get(eventType), ImmutableSet.of());
   }

   Iterator getSubscribers(Object event) {
      ImmutableSet<Class<?>> eventTypes = flattenHierarchy(event.getClass());
      List<Iterator<Subscriber>> subscriberIterators = Lists.newArrayListWithCapacity(eventTypes.size());

      for(Class eventType : eventTypes) {
         CopyOnWriteArraySet<Subscriber> eventSubscribers = (CopyOnWriteArraySet)this.subscribers.get(eventType);
         if (eventSubscribers != null) {
            subscriberIterators.add(eventSubscribers.iterator());
         }
      }

      return Iterators.concat(subscriberIterators.iterator());
   }

   private Multimap findAllSubscribers(Object listener) {
      Multimap<Class<?>, Subscriber> methodsInListener = HashMultimap.create();
      Class<?> clazz = listener.getClass();

      for(Method method : getAnnotatedMethods(clazz)) {
         Class<?>[] parameterTypes = method.getParameterTypes();
         Class<?> eventType = parameterTypes[0];
         methodsInListener.put(eventType, Subscriber.create(this.bus, listener, method));
      }

      return methodsInListener;
   }

   private static ImmutableList getAnnotatedMethods(Class clazz) {
      try {
         return (ImmutableList)subscriberMethodsCache.getUnchecked(clazz);
      } catch (UncheckedExecutionException e) {
         Throwables.throwIfUnchecked(e.getCause());
         throw e;
      }
   }

   private static ImmutableList getAnnotatedMethodsNotCached(Class clazz) {
      Set<? extends Class<?>> supertypes = TypeToken.of(clazz).getTypes().rawTypes();
      Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();

      for(Class supertype : supertypes) {
         for(Method method : supertype.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class) && !method.isSynthetic()) {
               Class<?>[] parameterTypes = method.getParameterTypes();
               Preconditions.checkArgument(parameterTypes.length == 1, "Method %s has @Subscribe annotation but has %s parameters. Subscriber methods must have exactly 1 parameter.", method, (int)parameterTypes.length);
               Preconditions.checkArgument(!parameterTypes[0].isPrimitive(), "@Subscribe method %s's parameter is %s. Subscriber methods cannot accept primitives. Consider changing the parameter to %s.", method, parameterTypes[0].getName(), Primitives.wrap(parameterTypes[0]).getSimpleName());
               MethodIdentifier ident = new MethodIdentifier(method);
               if (!identifiers.containsKey(ident)) {
                  identifiers.put(ident, method);
               }
            }
         }
      }

      return ImmutableList.copyOf(identifiers.values());
   }

   @VisibleForTesting
   static ImmutableSet flattenHierarchy(Class concreteClass) {
      try {
         return (ImmutableSet)flattenHierarchyCache.getUnchecked(concreteClass);
      } catch (UncheckedExecutionException e) {
         throw Throwables.propagate(e.getCause());
      }
   }

   private static final class MethodIdentifier {
      private final String name;
      private final List parameterTypes;

      MethodIdentifier(Method method) {
         this.name = method.getName();
         this.parameterTypes = Arrays.asList(method.getParameterTypes());
      }

      public int hashCode() {
         return Objects.hashCode(this.name, this.parameterTypes);
      }

      public boolean equals(@CheckForNull Object o) {
         if (!(o instanceof MethodIdentifier)) {
            return false;
         } else {
            MethodIdentifier ident = (MethodIdentifier)o;
            return this.name.equals(ident.name) && this.parameterTypes.equals(ident.parameterTypes);
         }
      }
   }
}
