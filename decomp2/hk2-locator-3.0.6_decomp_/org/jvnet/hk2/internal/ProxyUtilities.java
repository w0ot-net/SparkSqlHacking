package org.jvnet.hk2.internal;

import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.glassfish.hk2.api.ActiveDescriptor;
import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.ProxyCtl;
import org.glassfish.hk2.api.ServiceLocator;

public class ProxyUtilities {
   private static final Object proxyCreationLock = new Object();
   private final HashMap superClassToDelegator = new HashMap();

   private Object secureCreate(final Class superclass, final Class[] interfaces, final MethodHandler callback, boolean useJDKProxy, ServiceLocator anchor) {
      final ClassLoader loader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            ClassLoader retVal = superclass.getClassLoader();
            if (retVal == null) {
               try {
                  retVal = ClassLoader.getSystemClassLoader();
               } catch (SecurityException se) {
                  throw new IllegalStateException("Insufficient privilege to get system classloader while looking for classloader of " + superclass.getName(), se);
               }
            }

            if (retVal == null) {
               throw new IllegalStateException("Could not find system classloader or classloader of " + superclass.getName());
            } else {
               return retVal;
            }
         }
      });
      final DelegatingClassLoader initDelegatingLoader;
      synchronized(this.superClassToDelegator) {
         initDelegatingLoader = (DelegatingClassLoader)this.superClassToDelegator.get(loader);
         if (initDelegatingLoader == null) {
            initDelegatingLoader = (DelegatingClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
               public DelegatingClassLoader run() {
                  return new DelegatingClassLoader(loader, new ClassLoader[]{ProxyFactory.class.getClassLoader(), ProxyCtl.class.getClassLoader()});
               }
            });
            this.superClassToDelegator.put(loader, initDelegatingLoader);
         }
      }

      return useJDKProxy ? AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            return Proxy.newProxyInstance(initDelegatingLoader, interfaces, new MethodInterceptorInvocationHandler(callback));
         }
      }) : AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            synchronized(ProxyUtilities.proxyCreationLock) {
               ProxyFactory.ClassLoaderProvider originalProvider = ProxyFactory.classLoaderProvider;
               ProxyFactory.classLoaderProvider = new ProxyFactory.ClassLoaderProvider() {
                  public ClassLoader get(ProxyFactory arg0) {
                     return initDelegatingLoader;
                  }
               };

               Object var6;
               try {
                  ProxyFactory proxyFactory = new ProxyFactory();
                  proxyFactory.setInterfaces(interfaces);
                  proxyFactory.setSuperclass(superclass);
                  Class<?> proxyClass = proxyFactory.createClass();

                  try {
                     T proxy = (T)proxyClass.newInstance();
                     ((ProxyObject)proxy).setHandler(callback);
                     var6 = proxy;
                  } catch (Exception e1) {
                     throw new RuntimeException(e1);
                  }
               } finally {
                  ProxyFactory.classLoaderProvider = originalProvider;
               }

               return var6;
            }
         }
      });
   }

   public Object generateProxy(Class requestedClass, ServiceLocatorImpl locator, ActiveDescriptor root, ServiceHandleImpl handle, Injectee injectee) {
      boolean isInterface = requestedClass == null ? false : requestedClass.isInterface();
      Class<?> proxyClass;
      Class<?>[] iFaces;
      if (isInterface) {
         proxyClass = requestedClass;
         iFaces = new Class[2];
         iFaces[0] = requestedClass;
         iFaces[1] = ProxyCtl.class;
      } else {
         proxyClass = Utilities.getFactoryAwareImplementationClass(root);
         iFaces = Utilities.getInterfacesForProxy(root.getContractTypes());
      }

      try {
         T proxy = (T)this.secureCreate(proxyClass, iFaces, new MethodInterceptorImpl(locator, root, handle, injectee), isInterface, locator);
         return proxy;
      } catch (Throwable th) {
         String var10002 = proxyClass.getName();
         Exception addMe = new IllegalArgumentException("While attempting to create a Proxy for " + var10002 + " in scope " + root.getScope() + " an error occured while creating the proxy");
         if (th instanceof MultiException) {
            MultiException me = (MultiException)th;
            me.addError(addMe);
            throw me;
         } else {
            MultiException me = new MultiException(th);
            me.addError(addMe);
            throw me;
         }
      }
   }

   public void releaseCache() {
      synchronized(this.superClassToDelegator) {
         this.superClassToDelegator.clear();
      }
   }
}
