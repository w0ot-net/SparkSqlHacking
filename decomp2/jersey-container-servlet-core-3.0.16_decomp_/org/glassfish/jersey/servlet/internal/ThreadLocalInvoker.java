package org.glassfish.jersey.servlet.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ThreadLocalInvoker implements InvocationHandler {
   private ThreadLocal threadLocalInstance = new ThreadLocal();

   public void set(Object threadLocalInstance) {
      this.threadLocalInstance.set(threadLocalInstance);
   }

   public Object get() {
      return this.threadLocalInstance.get();
   }

   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if (this.threadLocalInstance.get() == null) {
         throw new IllegalStateException(LocalizationMessages.PERSISTENCE_UNIT_NOT_CONFIGURED(proxy.getClass()));
      } else {
         try {
            return method.invoke(this.threadLocalInstance.get(), args);
         } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
         } catch (InvocationTargetException ex) {
            throw ex.getTargetException();
         }
      }
   }
}
