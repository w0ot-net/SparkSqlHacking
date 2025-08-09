package org.glassfish.jaxb.runtime.v2.runtime.reflect.opt;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.Accessor;

final class Injector {
   private static final ReentrantReadWriteLock irwl = new ReentrantReadWriteLock();
   private static final Lock ir;
   private static final Lock iw;
   private static final Map injectors;
   private static final Logger logger;
   private final Map classes = new HashMap();
   private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
   private final Lock r;
   private final Lock w;
   private final ClassLoader parent;
   private final boolean loadable;
   private static Method defineClass;
   private static Method resolveClass;
   private static Method findLoadedClass;
   private static Object U;

   static Class inject(ClassLoader cl, String className, byte[] image) {
      Injector injector = get(cl);
      return injector != null ? injector.inject(className, image) : null;
   }

   static Class find(ClassLoader cl, String className) {
      Injector injector = get(cl);
      return injector != null ? injector.find(className) : null;
   }

   private static Injector get(ClassLoader cl) {
      Injector injector = null;
      ir.lock();

      WeakReference<Injector> wr;
      try {
         wr = (WeakReference)injectors.get(cl);
      } finally {
         ir.unlock();
      }

      if (wr != null) {
         injector = (Injector)wr.get();
      }

      if (injector == null) {
         try {
            wr = new WeakReference(injector = new Injector(cl));
            iw.lock();

            try {
               if (!injectors.containsKey(cl)) {
                  injectors.put(cl, wr);
               }
            } finally {
               iw.unlock();
            }
         } catch (SecurityException e) {
            logger.log(Level.FINE, "Unable to set up a back-door for the injector", e);
            return null;
         }
      }

      return injector;
   }

   private static Class classForNames(String... names) throws ClassNotFoundException {
      for(String name : names) {
         try {
            return Class.forName(name);
         } catch (ClassNotFoundException var6) {
         }
      }

      throw new ClassNotFoundException(String.format("No class found for supplied FQDNs %s", Arrays.toString(names)));
   }

   private static Method getMethod(Class c, String methodname, Class... params) {
      try {
         Method m = c.getDeclaredMethod(methodname, params);
         m.setAccessible(true);
         return m;
      } catch (NoSuchMethodException e) {
         throw new NoSuchMethodError(e.getMessage());
      }
   }

   private Injector(ClassLoader parent) {
      this.r = this.rwl.readLock();
      this.w = this.rwl.writeLock();
      this.parent = parent;

      assert parent != null;

      boolean loadableCheck = false;

      try {
         loadableCheck = parent.loadClass(Accessor.class.getName()) == Accessor.class;
      } catch (ClassNotFoundException var4) {
      }

      this.loadable = loadableCheck;
   }

   private Class inject(String className, byte[] image) {
      if (!this.loadable) {
         return null;
      } else {
         boolean wlocked = false;
         boolean rlocked = false;

         try {
            this.r.lock();
            rlocked = true;
            Class c = (Class)this.classes.get(className);
            this.r.unlock();
            rlocked = false;
            if (c == null && findLoadedClass != null) {
               try {
                  c = (Class)findLoadedClass.invoke(this.parent, className.replace('/', '.'));
               } catch (IllegalAccessException | IllegalArgumentException e) {
                  logger.log(Level.FINE, "Unable to find " + className, e);
               } catch (InvocationTargetException e) {
                  Throwable t = e.getTargetException();
                  logger.log(Level.FINE, "Unable to find " + className, t);
               }

               if (c != null) {
                  this.w.lock();
                  wlocked = true;
                  this.classes.put(className, c);
                  this.w.unlock();
                  wlocked = false;
                  Class e = c;
                  return e;
               }
            }

            if (c == null) {
               this.r.lock();
               rlocked = true;
               c = (Class)this.classes.get(className);
               this.r.unlock();
               rlocked = false;
               if (c == null) {
                  label231: {
                     label208: {
                        try {
                           if (resolveClass != null) {
                              c = (Class)defineClass.invoke(this.parent, className.replace('/', '.'), image, 0, image.length);
                              resolveClass.invoke(this.parent, c);
                           } else {
                              c = (Class)defineClass.invoke(U, className.replace('/', '.'), image, 0, image.length, this.parent, Injector.class.getProtectionDomain());
                           }
                        } catch (LinkageError | SecurityException | IllegalAccessException e) {
                           logger.log(Level.FINE, "Unable to inject " + className, e);
                           Object var26 = null;
                           return (Class)var26;
                        } catch (InvocationTargetException e) {
                           Throwable t = e.getTargetException();
                           if (t instanceof LinkageError) {
                              logger.log(Level.FINE, "duplicate class definition bug occured? Please report this : " + className, t);
                              break label208;
                           }

                           logger.log(Level.FINE, "Unable to inject " + className, t);
                           break label208;
                        }

                        this.w.lock();
                        wlocked = true;
                        if (!this.classes.containsKey(className)) {
                           this.classes.put(className, c);
                        }

                        this.w.unlock();
                        wlocked = false;
                        break label231;
                     }

                     Object var8 = null;
                     return (Class)var8;
                  }
               }
            }

            Class e = c;
            return e;
         } finally {
            if (rlocked) {
               this.r.unlock();
            }

            if (wlocked) {
               this.w.unlock();
            }

         }
      }
   }

   private Class find(String className) {
      this.r.lock();

      Class var2;
      try {
         var2 = (Class)this.classes.get(className);
      } finally {
         this.r.unlock();
      }

      return var2;
   }

   static {
      ir = irwl.readLock();
      iw = irwl.writeLock();
      injectors = new WeakHashMap();
      logger = Logger.getLogger(Injector.class.getName());

      try {
         Method[] m = (Method[])AccessController.doPrivileged(new PrivilegedAction() {
            public Method[] run() {
               return new Method[]{Injector.getMethod(ClassLoader.class, "defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE), Injector.getMethod(ClassLoader.class, "resolveClass", Class.class), Injector.getMethod(ClassLoader.class, "findLoadedClass", String.class)};
            }
         });
         defineClass = m[0];
         resolveClass = m[1];
         findLoadedClass = m[2];
      } catch (Throwable var3) {
         try {
            U = AccessController.doPrivileged(new PrivilegedExceptionAction() {
               public Object run() throws Exception {
                  Class u = Injector.classForNames("sun.misc.Unsafe", "jdk.internal.misc.Unsafe");
                  Field theUnsafe = u.getDeclaredField("theUnsafe");
                  theUnsafe.setAccessible(true);
                  return theUnsafe.get((Object)null);
               }
            });
            defineClass = (Method)AccessController.doPrivileged(new PrivilegedExceptionAction() {
               public Method run() throws Exception {
                  try {
                     return Injector.U.getClass().getMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE, ClassLoader.class, ProtectionDomain.class);
                  } catch (SecurityException | NoSuchMethodException ex) {
                     throw ex;
                  }
               }
            });
         } catch (PrivilegedActionException | SecurityException ex) {
            Logger.getLogger(Injector.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      }

   }
}
