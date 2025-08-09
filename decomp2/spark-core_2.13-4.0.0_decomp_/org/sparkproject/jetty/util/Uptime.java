package org.sparkproject.jetty.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Uptime {
   public static final int NOIMPL = -1;
   private static final Uptime INSTANCE = new Uptime();
   private Impl impl;

   public static Uptime getInstance() {
      return INSTANCE;
   }

   private Uptime() {
      try {
         this.impl = new DefaultImpl();
      } catch (UnsupportedOperationException e) {
         System.err.printf("Defaulting Uptime to NOIMPL due to (%s) %s%n", e.getClass().getName(), e.getMessage());
         this.impl = null;
      }

   }

   public Impl getImpl() {
      return this.impl;
   }

   public void setImpl(Impl impl) {
      this.impl = impl;
   }

   public static long getUptime() {
      Uptime u = getInstance();
      return u != null && u.impl != null ? u.impl.getUptime() : -1L;
   }

   public static class DefaultImpl implements Impl {
      public Object mxBean;
      public Method uptimeMethod;

      public DefaultImpl() {
         ClassLoader cl = Thread.currentThread().getContextClassLoader();

         try {
            Class<?> mgmtFactory = Class.forName("java.lang.management.ManagementFactory", true, cl);
            Class<?> runtimeClass = Class.forName("java.lang.management.RuntimeMXBean", true, cl);
            Class<?>[] noParams = new Class[0];
            Method mxBeanMethod = mgmtFactory.getMethod("getRuntimeMXBean", noParams);
            if (mxBeanMethod == null) {
               throw new UnsupportedOperationException("method getRuntimeMXBean() not found");
            } else {
               this.mxBean = mxBeanMethod.invoke(mgmtFactory);
               if (this.mxBean == null) {
                  throw new UnsupportedOperationException("getRuntimeMXBean() method returned null");
               } else {
                  this.uptimeMethod = runtimeClass.getMethod("getUptime", noParams);
                  if (this.mxBean == null) {
                     throw new UnsupportedOperationException("method getUptime() not found");
                  }
               }
            }
         } catch (NoClassDefFoundError | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException e) {
            throw new UnsupportedOperationException("Implementation not available in this environment", e);
         }
      }

      public long getUptime() {
         try {
            return (Long)this.uptimeMethod.invoke(this.mxBean);
         } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException var2) {
            return -1L;
         }
      }
   }

   public interface Impl {
      long getUptime();
   }
}
