package jodd.util.cl;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import jodd.util.SystemUtil;

public class ExtendedURLClassLoader extends URLClassLoader {
   protected ClassLoader parentClassLoader;
   protected String[] systemPackages;
   protected String[] loaderPackages;
   protected boolean parentFirst;

   public ExtendedURLClassLoader(URL[] classpath, ClassLoader parent) {
      this(classpath, parent, true);
   }

   public ExtendedURLClassLoader(URL[] classpath, ClassLoader parent, boolean parentFirst) {
      super(classpath, parent);
      this.parentFirst = parentFirst;
      if (parent == null) {
         parent = getSystemClassLoader();
      }

      this.parentClassLoader = parent;
      this.systemPackages = new String[0];
      this.loaderPackages = new String[0];
      this.addSystemPackage(SystemUtil.getJrePackages());
   }

   public void setParentFirst(boolean parentFirst) {
      this.parentFirst = parentFirst;
   }

   public void addSystemPackage(String... packages) {
      this.systemPackages = this.joinPackages(this.systemPackages, packages);
   }

   public void addLoaderPackage(String... packages) {
      this.loaderPackages = this.joinPackages(this.loaderPackages, packages);
   }

   protected String[] joinPackages(String[] dest, String[] src) {
      int len = dest.length;
      String[] result = new String[len + src.length];
      System.arraycopy(dest, 0, result, 0, len);

      for(int i = 0; i < src.length; ++i) {
         String pck = src[i];
         pck = pck + (pck.endsWith(".") ? "" : ".");
         result[len + i] = pck;
      }

      return result;
   }

   protected boolean isInPackageList(String name, String[] packages) {
      for(String pck : packages) {
         if (name.startsWith(pck)) {
            return true;
         }
      }

      return false;
   }

   protected boolean isParentFirst(String resourceName) {
      boolean useParentFirst = this.parentFirst;
      if (this.isInPackageList(resourceName, this.systemPackages)) {
         useParentFirst = true;
      }

      if (this.isInPackageList(resourceName, this.loaderPackages)) {
         useParentFirst = false;
      }

      return useParentFirst;
   }

   protected synchronized Class loadClass(String className, boolean resolve) throws ClassNotFoundException {
      Class<?> c = this.findLoadedClass(className);
      if (c != null) {
         if (resolve) {
            this.resolveClass(c);
         }

         return c;
      } else {
         boolean loadUsingParentFirst = this.isParentFirst(className);
         if (loadUsingParentFirst) {
            try {
               c = this.parentClassLoader.loadClass(className);
            } catch (ClassNotFoundException var7) {
            }

            if (c == null) {
               c = this.findClass(className);
            }
         } else {
            try {
               c = this.findClass(className);
            } catch (ClassNotFoundException var6) {
            }

            if (c == null) {
               c = this.parentClassLoader.loadClass(className);
            }
         }

         if (resolve) {
            this.resolveClass(c);
         }

         return c;
      }
   }

   public URL getResource(String resourceName) {
      boolean loadUsingParentFirst = this.isParentFirst(resourceName);
      URL url;
      if (loadUsingParentFirst) {
         url = this.parentClassLoader.getResource(resourceName);
         if (url == null) {
            url = this.findResource(resourceName);
         }
      } else {
         url = this.findResource(resourceName);
         if (url == null) {
            url = this.parentClassLoader.getResource(resourceName);
         }
      }

      return url;
   }

   public Enumeration getResources(String resourceName) throws IOException {
      final List<URL> urls = new ArrayList();
      Enumeration<URL> localUrls = this.findResources(resourceName);
      Enumeration<URL> parentUrls = this.parentClassLoader.getResources(resourceName);
      boolean loadUsingParentFirst = this.isParentFirst(resourceName);
      if (loadUsingParentFirst) {
         while(parentUrls.hasMoreElements()) {
            urls.add(parentUrls.nextElement());
         }

         while(localUrls.hasMoreElements()) {
            urls.add(localUrls.nextElement());
         }

         return new Enumeration() {
            Iterator iterator = urls.iterator();

            public boolean hasMoreElements() {
               return this.iterator.hasNext();
            }

            public URL nextElement() {
               return (URL)this.iterator.next();
            }
         };
      } else {
         while(localUrls.hasMoreElements()) {
            urls.add(localUrls.nextElement());
         }

         while(parentUrls.hasMoreElements()) {
            urls.add(parentUrls.nextElement());
         }

         return new Enumeration() {
            Iterator iterator = urls.iterator();

            public boolean hasMoreElements() {
               return this.iterator.hasNext();
            }

            public URL nextElement() {
               return (URL)this.iterator.next();
            }
         };
      }
   }
}
