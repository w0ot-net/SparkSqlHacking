package jakarta.activation;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

class SecuritySupport {
   private SecuritySupport() {
   }

   public static ClassLoader getContextClassLoader() {
      return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {
         public ClassLoader run() {
            ClassLoader cl = null;

            try {
               cl = Thread.currentThread().getContextClassLoader();
            } catch (SecurityException var3) {
            }

            return cl;
         }
      });
   }

   public static InputStream getResourceAsStream(final Class c, final String name) throws IOException {
      try {
         return (InputStream)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public InputStream run() throws IOException {
               return c.getResourceAsStream(name);
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getException();
      }
   }

   public static URL[] getResources(final ClassLoader cl, final String name) {
      return (URL[])AccessController.doPrivileged(new PrivilegedAction() {
         public URL[] run() {
            URL[] ret = null;

            try {
               List<URL> v = new ArrayList();
               Enumeration<URL> e = cl.getResources(name);

               while(e != null && e.hasMoreElements()) {
                  URL url = (URL)e.nextElement();
                  if (url != null) {
                     v.add(url);
                  }
               }

               if (v.size() > 0) {
                  ret = new URL[v.size()];
                  ret = (URL[])v.toArray(ret);
               }
            } catch (IOException var5) {
            } catch (SecurityException var6) {
            }

            return ret;
         }
      });
   }

   public static URL[] getSystemResources(final String name) {
      return (URL[])AccessController.doPrivileged(new PrivilegedAction() {
         public URL[] run() {
            URL[] ret = null;

            try {
               List<URL> v = new ArrayList();
               Enumeration<URL> e = ClassLoader.getSystemResources(name);

               while(e != null && e.hasMoreElements()) {
                  URL url = (URL)e.nextElement();
                  if (url != null) {
                     v.add(url);
                  }
               }

               if (v.size() > 0) {
                  ret = new URL[v.size()];
                  ret = (URL[])v.toArray(ret);
               }
            } catch (IOException var5) {
            } catch (SecurityException var6) {
            }

            return ret;
         }
      });
   }

   public static InputStream openStream(final URL url) throws IOException {
      try {
         return (InputStream)AccessController.doPrivileged(new PrivilegedExceptionAction() {
            public InputStream run() throws IOException {
               return url.openStream();
            }
         });
      } catch (PrivilegedActionException e) {
         throw (IOException)e.getException();
      }
   }
}
