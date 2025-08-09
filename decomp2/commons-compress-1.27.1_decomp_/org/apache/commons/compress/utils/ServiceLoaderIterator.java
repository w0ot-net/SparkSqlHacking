package org.apache.commons.compress.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/** @deprecated */
@Deprecated
public class ServiceLoaderIterator implements Iterator {
   private Object nextServiceLoader;
   private final Class service;
   private final Iterator serviceLoaderIterator;

   public ServiceLoaderIterator(Class service) {
      this(service, ClassLoader.getSystemClassLoader());
   }

   public ServiceLoaderIterator(Class service, ClassLoader classLoader) {
      this.service = service;
      this.serviceLoaderIterator = ServiceLoader.load(service, classLoader).iterator();
   }

   public boolean hasNext() {
      while(this.nextServiceLoader == null) {
         try {
            if (!this.serviceLoaderIterator.hasNext()) {
               return false;
            }

            this.nextServiceLoader = this.serviceLoaderIterator.next();
         } catch (ServiceConfigurationError e) {
            if (!(e.getCause() instanceof SecurityException)) {
               throw e;
            }
         }
      }

      return true;
   }

   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException("No more elements for service " + this.service.getName());
      } else {
         E tempNext = (E)this.nextServiceLoader;
         this.nextServiceLoader = null;
         return tempNext;
      }
   }

   public void remove() {
      throw new UnsupportedOperationException("service=" + this.service.getName());
   }
}
