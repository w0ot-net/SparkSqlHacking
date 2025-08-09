package org.jvnet.hk2.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.IterableProvider;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.Unqualified;
import org.glassfish.hk2.utilities.InjecteeImpl;
import org.glassfish.hk2.utilities.NamedImpl;
import org.glassfish.hk2.utilities.reflection.Pretty;
import org.glassfish.hk2.utilities.reflection.ReflectionHelper;

public class IterableProviderImpl implements IterableProvider {
   private final ServiceLocatorImpl locator;
   private final Type requiredType;
   private final Set requiredQualifiers;
   private final Unqualified unqualified;
   private final Injectee originalInjectee;
   private final boolean isIterable;

   IterableProviderImpl(ServiceLocatorImpl locator, Type requiredType, Set requiredQualifiers, Unqualified unqualified, Injectee originalInjectee, boolean isIterable) {
      this.locator = locator;
      this.requiredType = requiredType;
      this.requiredQualifiers = Collections.unmodifiableSet(requiredQualifiers);
      this.unqualified = unqualified;
      this.originalInjectee = originalInjectee;
      this.isIterable = isIterable;
   }

   private void justInTime() {
      InjecteeImpl injectee = new InjecteeImpl(this.originalInjectee);
      injectee.setRequiredType(this.requiredType);
      injectee.setRequiredQualifiers(this.requiredQualifiers);
      if (this.unqualified != null) {
         injectee.setUnqualified(this.unqualified);
      }

      this.locator.getInjecteeDescriptor(injectee);
   }

   public Object get() {
      this.justInTime();
      return this.locator.getUnqualifiedService(this.requiredType, this.unqualified, this.isIterable, (Annotation[])this.requiredQualifiers.toArray(new Annotation[this.requiredQualifiers.size()]));
   }

   public ServiceHandle getHandle() {
      this.justInTime();
      return this.locator.getUnqualifiedServiceHandle(this.requiredType, this.unqualified, this.isIterable, (Annotation[])this.requiredQualifiers.toArray(new Annotation[this.requiredQualifiers.size()]));
   }

   public Iterator iterator() {
      this.justInTime();
      List<ServiceHandle<T>> handles = (List)ReflectionHelper.cast(this.locator.getAllUnqualifiedServiceHandles(this.requiredType, this.unqualified, this.isIterable, (Annotation[])this.requiredQualifiers.toArray(new Annotation[this.requiredQualifiers.size()])));
      return new MyIterator(handles);
   }

   public int getSize() {
      this.justInTime();
      return this.locator.getAllUnqualifiedServiceHandles(this.requiredType, this.unqualified, this.isIterable, (Annotation[])this.requiredQualifiers.toArray(new Annotation[this.requiredQualifiers.size()])).size();
   }

   public IterableProvider named(String name) {
      return this.qualifiedWith(new NamedImpl(name));
   }

   public IterableProvider ofType(Type type) {
      return new IterableProviderImpl(this.locator, type, this.requiredQualifiers, this.unqualified, this.originalInjectee, this.isIterable);
   }

   public IterableProvider qualifiedWith(Annotation... qualifiers) {
      HashSet<Annotation> moreAnnotations = new HashSet(this.requiredQualifiers);

      for(Annotation qualifier : qualifiers) {
         moreAnnotations.add(qualifier);
      }

      return new IterableProviderImpl(this.locator, this.requiredType, moreAnnotations, this.unqualified, this.originalInjectee, this.isIterable);
   }

   public Iterable handleIterator() {
      this.justInTime();
      List<ServiceHandle<T>> handles = (List)ReflectionHelper.cast(this.locator.getAllServiceHandles(this.requiredType, (Annotation[])this.requiredQualifiers.toArray(new Annotation[this.requiredQualifiers.size()])));
      return new HandleIterable(handles);
   }

   public String toString() {
      String var10000 = Pretty.type(this.requiredType);
      return "IterableProviderImpl(" + var10000 + "," + Pretty.collection(this.requiredQualifiers) + "," + System.identityHashCode(this) + ")";
   }

   private static class MyIterator implements Iterator {
      private final LinkedList handles;

      private MyIterator(List handles) {
         this.handles = new LinkedList(handles);
      }

      public boolean hasNext() {
         return !this.handles.isEmpty();
      }

      public Object next() {
         if (this.handles.isEmpty()) {
            throw new NoSuchElementException();
         } else {
            ServiceHandle<U> nextHandle = (ServiceHandle)this.handles.removeFirst();
            return nextHandle.getService();
         }
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   private static class HandleIterable implements Iterable {
      private final List handles;

      private HandleIterable(List handles) {
         this.handles = new LinkedList(handles);
      }

      public Iterator iterator() {
         return new MyHandleIterator(this.handles);
      }
   }

   private static class MyHandleIterator implements Iterator {
      private final LinkedList handles;

      private MyHandleIterator(List handles) {
         this.handles = new LinkedList(handles);
      }

      public boolean hasNext() {
         return !this.handles.isEmpty();
      }

      public ServiceHandle next() {
         return (ServiceHandle)this.handles.removeFirst();
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
