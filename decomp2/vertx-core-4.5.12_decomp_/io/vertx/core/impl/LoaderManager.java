package io.vertx.core.impl;

import io.vertx.core.DeploymentOptions;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LoaderManager {
   private final Map classLoaders = new HashMap();

   ClassLoaderHolder getClassLoader(DeploymentOptions options) {
      String isolationGroup = options.getIsolationGroup();
      if (isolationGroup == null) {
         return null;
      } else {
         synchronized(this) {
            ClassLoaderHolder holder = (ClassLoaderHolder)this.classLoaders.get(isolationGroup);
            if (holder == null) {
               ClassLoader current = VerticleManager.getCurrentClassLoader();
               if (!(current instanceof URLClassLoader)) {
                  throw new IllegalStateException("Current classloader must be URLClassLoader");
               }

               holder = new ClassLoaderHolder(isolationGroup, buildLoader((URLClassLoader)current, options));
               this.classLoaders.put(isolationGroup, holder);
            }

            ++holder.refCount;
            return holder;
         }
      }
   }

   void release(ClassLoaderHolder holder) {
      synchronized(this) {
         if (--holder.refCount == 0) {
            this.classLoaders.remove(holder.group);
            if (holder.loader instanceof Closeable) {
               try {
                  ((Closeable)holder.loader).close();
               } catch (IOException var5) {
               }
            }
         }

      }
   }

   private static ClassLoader buildLoader(URLClassLoader parent, DeploymentOptions options) {
      List<URL> urls = new ArrayList();
      List<String> extraClasspath = options.getExtraClasspath();
      if (extraClasspath != null) {
         for(String pathElement : extraClasspath) {
            File file = new File(pathElement);

            try {
               URL url = file.toURI().toURL();
               urls.add(url);
            } catch (MalformedURLException e) {
               throw new IllegalStateException(e);
            }
         }
      }

      urls.addAll(Arrays.asList(parent.getURLs()));
      return new IsolatingClassLoader((URL[])urls.toArray(new URL[0]), parent, options.getIsolatedClasses());
   }
}
