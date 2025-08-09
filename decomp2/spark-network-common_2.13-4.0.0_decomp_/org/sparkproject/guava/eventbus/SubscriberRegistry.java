package org.sparkproject.guava.eventbus;

import com.google.j2objc.annotations.Weak;
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
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.base.Function;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import org.sparkproject.guava.collect.HashMultimap;
import org.sparkproject.guava.collect.ImmutableList;
import org.sparkproject.guava.collect.ImmutableSet;
import org.sparkproject.guava.collect.Iterators;
import org.sparkproject.guava.collect.Lists;
import org.sparkproject.guava.collect.Maps;
import org.sparkproject.guava.collect.Multimap;
import org.sparkproject.guava.primitives.Primitives;
import org.sparkproject.guava.reflect.TypeToken;
import org.sparkproject.guava.util.concurrent.UncheckedExecutionException;

@ElementTypesAreNonnullByDefault
final class SubscriberRegistry {
   private final ConcurrentMap subscribers = Maps.newConcurrentMap();
   @Weak
   private final EventBus bus;
   private static final LoadingCache subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(CacheLoader.from(SubscriberRegistry::getAnnotatedMethodsNotCached));
   private static final LoadingCache flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(CacheLoader.from((Function)((concreteClass) -> ImmutableSet.copyOf((Collection)TypeToken.of(concreteClass).getTypes().rawTypes()))));

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
         if (e.getCause() instanceof IllegalArgumentException) {
            throw new IllegalArgumentException(e.getCause().getMessage(), e.getCause());
         } else {
            throw e;
         }
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
      return (ImmutableSet)flattenHierarchyCache.getUnchecked(concreteClass);
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
