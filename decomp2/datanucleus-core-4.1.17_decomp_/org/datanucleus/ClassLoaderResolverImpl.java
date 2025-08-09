package org.datanucleus;

import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.datanucleus.exceptions.ClassNotResolvedException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.WeakValueMap;

public class ClassLoaderResolverImpl implements ClassLoaderResolver {
   protected final ClassLoader contextLoader;
   protected int contextLoaderHashCode = 0;
   protected ClassLoader runtimeLoader;
   protected int runtimeLoaderHashCode = 0;
   protected ClassLoader userRegisteredLoader;
   protected int userRegisteredLoaderHashCode = 0;
   protected Map loadedClasses = Collections.synchronizedMap(new WeakValueMap());
   protected Map unloadedClasses = Collections.synchronizedMap(new WeakValueMap());
   protected Map resources = Collections.synchronizedMap(new WeakValueMap());
   ThreadLocal primary = new ThreadLocal();

   public ClassLoaderResolverImpl(ClassLoader ctxLoader) {
      this.contextLoader = ctxLoader;
      if (this.contextLoader != null) {
         this.contextLoaderHashCode = this.contextLoader.hashCode();
      }

   }

   public ClassLoaderResolverImpl() {
      this.contextLoader = null;
   }

   public Class classForName(String name, ClassLoader primary) {
      if (name == null) {
         String msg = Localiser.msg("001000");
         throw new ClassNotResolvedException(msg);
      } else if (name.equals(ClassNameConstants.BYTE)) {
         return Byte.TYPE;
      } else if (name.equals(ClassNameConstants.CHAR)) {
         return Character.TYPE;
      } else if (name.equals(ClassNameConstants.INT)) {
         return Integer.TYPE;
      } else if (name.equals(ClassNameConstants.LONG)) {
         return Long.TYPE;
      } else if (name.equals(ClassNameConstants.DOUBLE)) {
         return Double.TYPE;
      } else if (name.equals(ClassNameConstants.FLOAT)) {
         return Float.TYPE;
      } else if (name.equals(ClassNameConstants.SHORT)) {
         return Short.TYPE;
      } else if (name.equals(ClassNameConstants.BOOLEAN)) {
         return Boolean.TYPE;
      } else if (name.equals(ClassNameConstants.JAVA_LANG_STRING)) {
         return String.class;
      } else {
         ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
         String cacheKey = this.newCacheKey(name, primary, threadClassLoader);
         Class cls = (Class)this.loadedClasses.get(cacheKey);
         if (cls != null) {
            return cls;
         } else {
            cls = (Class)this.unloadedClasses.get(cacheKey);
            if (cls != null) {
               return cls;
            } else {
               cls = this.classOrNull(name, primary);
               if (cls == null && this.primary.get() != null) {
                  cls = this.classOrNull(name, (ClassLoader)this.primary.get());
               }

               if (cls == null) {
                  cls = this.classOrNull(name, threadClassLoader);
               }

               if (cls == null) {
                  cls = this.classOrNull(name, this.contextLoader);
               }

               if (cls == null && this.runtimeLoader != null) {
                  cls = this.classOrNull(name, this.runtimeLoader);
               }

               if (cls == null && this.userRegisteredLoader != null) {
                  cls = this.classOrNull(name, this.userRegisteredLoader);
               }

               if (cls == null) {
                  throw new ClassNotResolvedException(Localiser.msg("001000", name));
               } else {
                  this.unloadedClasses.put(cacheKey, cls);
                  return cls;
               }
            }
         }
      }
   }

   private Class classForNameWithInitialize(String name, ClassLoader primary) {
      if (name == null) {
         throw new ClassNotResolvedException(Localiser.msg("001000"));
      } else if (name.equals(ClassNameConstants.BYTE)) {
         return Byte.TYPE;
      } else if (name.equals(ClassNameConstants.CHAR)) {
         return Character.TYPE;
      } else if (name.equals(ClassNameConstants.INT)) {
         return Integer.TYPE;
      } else if (name.equals(ClassNameConstants.LONG)) {
         return Long.TYPE;
      } else if (name.equals(ClassNameConstants.DOUBLE)) {
         return Double.TYPE;
      } else if (name.equals(ClassNameConstants.FLOAT)) {
         return Float.TYPE;
      } else if (name.equals(ClassNameConstants.SHORT)) {
         return Short.TYPE;
      } else if (name.equals(ClassNameConstants.BOOLEAN)) {
         return Boolean.TYPE;
      } else if (name.equals(ClassNameConstants.JAVA_LANG_STRING)) {
         return String.class;
      } else {
         ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
         String cacheKey = this.newCacheKey(name, primary, threadClassLoader);
         Class cls = (Class)this.loadedClasses.get(cacheKey);
         if (cls != null) {
            return cls;
         } else {
            cls = this.ClassOrNullWithInitialize(name, primary);
            if (cls == null && this.primary.get() != null) {
               cls = this.ClassOrNullWithInitialize(name, (ClassLoader)this.primary.get());
            }

            if (cls == null) {
               cls = this.ClassOrNullWithInitialize(name, threadClassLoader);
            }

            if (cls == null) {
               cls = this.ClassOrNullWithInitialize(name, this.contextLoader);
            }

            if (cls == null && this.runtimeLoader != null) {
               cls = this.ClassOrNullWithInitialize(name, this.runtimeLoader);
            }

            if (cls == null && this.userRegisteredLoader != null) {
               cls = this.ClassOrNullWithInitialize(name, this.userRegisteredLoader);
            }

            if (cls == null) {
               throw new ClassNotResolvedException(Localiser.msg("001000", name));
            } else {
               this.loadedClasses.put(cacheKey, cls);
               return cls;
            }
         }
      }
   }

   private String newCacheKey(String prefix, ClassLoader primary, ClassLoader contextClassLoader) {
      int h = 3;
      if (primary != null) {
         h ^= primary.hashCode();
      }

      if (contextClassLoader != null) {
         h ^= contextClassLoader.hashCode();
      }

      h ^= this.contextLoaderHashCode;
      h ^= this.runtimeLoaderHashCode;
      h ^= this.userRegisteredLoaderHashCode;
      return prefix + h;
   }

   public Class classForName(String name, ClassLoader primary, boolean initialize) {
      return initialize ? this.classForNameWithInitialize(name, primary) : this.classForName(name, primary);
   }

   public Class classForName(String name) {
      return this.classForName(name, (ClassLoader)null);
   }

   public Class classForName(String name, boolean initialize) {
      return this.classForName(name, (ClassLoader)null, initialize);
   }

   public boolean isAssignableFrom(String class_name_1, String class_name_2) {
      if (class_name_1 != null && class_name_2 != null) {
         if (class_name_1.equals(class_name_2)) {
            return true;
         } else {
            Class class_1 = this.classForName(class_name_1);
            Class class_2 = this.classForName(class_name_2);
            return class_1.isAssignableFrom(class_2);
         }
      } else {
         return false;
      }
   }

   public boolean isAssignableFrom(String class_name_1, Class class_2) {
      if (class_name_1 != null && class_2 != null) {
         if (class_name_1.equals(class_2.getName())) {
            return true;
         } else {
            try {
               Class class_1 = null;
               if (class_2.getClassLoader() != null) {
                  class_1 = class_2.getClassLoader().loadClass(class_name_1);
               } else {
                  class_1 = Class.forName(class_name_1);
               }

               return class_1.isAssignableFrom(class_2);
            } catch (Exception var4) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public boolean isAssignableFrom(Class class_1, String class_name_2) {
      if (class_1 != null && class_name_2 != null) {
         if (class_1.getName().equals(class_name_2)) {
            return true;
         } else {
            try {
               Class class_2 = null;
               if (class_1.getClassLoader() != null) {
                  class_2 = class_1.getClassLoader().loadClass(class_name_2);
               } else {
                  class_2 = Class.forName(class_name_2);
               }

               return class_1.isAssignableFrom(class_2);
            } catch (Exception var4) {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private Class classOrNull(String name, ClassLoader loader) {
      try {
         return loader == null ? null : Class.forName(name, false, loader);
      } catch (ClassNotFoundException var4) {
      } catch (NoClassDefFoundError var5) {
      }

      return null;
   }

   private Class ClassOrNullWithInitialize(String name, ClassLoader loader) {
      try {
         return loader == null ? null : Class.forName(name, true, loader);
      } catch (ClassNotFoundException var4) {
         return null;
      } catch (NoClassDefFoundError var5) {
         return null;
      }
   }

   public void setRuntimeClassLoader(ClassLoader loader) {
      this.runtimeLoader = loader;
      if (this.runtimeLoader == null) {
         this.runtimeLoaderHashCode = 0;
      } else {
         this.runtimeLoaderHashCode = loader.hashCode();
      }

   }

   public void registerUserClassLoader(ClassLoader loader) {
      this.userRegisteredLoader = loader;
      if (this.userRegisteredLoader == null) {
         this.userRegisteredLoaderHashCode = 0;
      } else {
         this.userRegisteredLoaderHashCode = loader.hashCode();
      }

   }

   public Enumeration getResources(final String resourceName, final ClassLoader primary) throws IOException {
      final List list = new ArrayList();
      final ClassLoader userClassLoader = (ClassLoader)this.primary.get();
      final ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
      AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            try {
               String name = resourceName;
               if (name.startsWith("/")) {
                  name = name.substring(1);
               }

               if (primary != null) {
                  Enumeration primaryResourceEnum = primary.getResources(name);

                  while(primaryResourceEnum.hasMoreElements()) {
                     list.add(primaryResourceEnum.nextElement());
                  }
               }

               if (userClassLoader != null) {
                  Enumeration primaryResourceEnum = userClassLoader.getResources(name);

                  while(primaryResourceEnum.hasMoreElements()) {
                     list.add(primaryResourceEnum.nextElement());
                  }
               }

               if (threadClassLoader != null) {
                  Enumeration resourceEnum = threadClassLoader.getResources(name);

                  while(resourceEnum.hasMoreElements()) {
                     list.add(resourceEnum.nextElement());
                  }
               }

               if (ClassLoaderResolverImpl.this.contextLoader != null) {
                  Enumeration pmResourceEnum = ClassLoaderResolverImpl.this.contextLoader.getResources(name);

                  while(pmResourceEnum.hasMoreElements()) {
                     list.add(pmResourceEnum.nextElement());
                  }
               }

               if (ClassLoaderResolverImpl.this.runtimeLoader != null) {
                  Enumeration loaderResourceEnum = ClassLoaderResolverImpl.this.runtimeLoader.getResources(name);

                  while(loaderResourceEnum.hasMoreElements()) {
                     list.add(loaderResourceEnum.nextElement());
                  }
               }

               if (ClassLoaderResolverImpl.this.userRegisteredLoader != null) {
                  Enumeration loaderResourceEnum = ClassLoaderResolverImpl.this.userRegisteredLoader.getResources(name);

                  while(loaderResourceEnum.hasMoreElements()) {
                     list.add(loaderResourceEnum.nextElement());
                  }
               }

               return null;
            } catch (IOException ex) {
               throw new NucleusException(ex.getMessage(), ex);
            }
         }
      });
      return Collections.enumeration(new LinkedHashSet(list));
   }

   public URL getResource(final String resourceName, final ClassLoader primary) {
      final ClassLoader userClassLoader = (ClassLoader)this.primary.get();
      URL url = (URL)AccessController.doPrivileged(new PrivilegedAction() {
         public Object run() {
            String resName = resourceName;
            URL url = (URL)ClassLoaderResolverImpl.this.resources.get(resName);
            if (url != null) {
               return url;
            } else {
               if (resName.startsWith("/")) {
                  resName = resName.substring(1);
               }

               if (primary != null) {
                  url = primary.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               if (userClassLoader != null) {
                  url = userClassLoader.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
               if (threadClassLoader != null) {
                  url = threadClassLoader.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               if (ClassLoaderResolverImpl.this.contextLoader != null) {
                  url = ClassLoaderResolverImpl.this.contextLoader.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               if (ClassLoaderResolverImpl.this.runtimeLoader != null) {
                  url = ClassLoaderResolverImpl.this.runtimeLoader.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               if (ClassLoaderResolverImpl.this.userRegisteredLoader != null) {
                  url = ClassLoaderResolverImpl.this.userRegisteredLoader.getResource(resName);
                  if (url != null) {
                     ClassLoaderResolverImpl.this.resources.put(resName, url);
                     return url;
                  }
               }

               return null;
            }
         }
      });
      return url;
   }

   public void setPrimary(ClassLoader primary) {
      this.primary.set(primary);
   }

   public void unsetPrimary() {
      this.primary.set((Object)null);
   }

   public String toString() {
      return "ClassLoaderResolver: primary=" + this.primary + " contextLoader=" + this.contextLoader + " runtimeLoader=" + this.runtimeLoader + " registeredLoader=" + this.userRegisteredLoader;
   }
}
