package jakarta.ws.rs.ext;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.Variant;
import java.lang.reflect.ReflectPermission;
import java.net.URL;

public abstract class RuntimeDelegate {
   public static final String JAXRS_RUNTIME_DELEGATE_PROPERTY = "jakarta.ws.rs.ext.RuntimeDelegate";
   private static final Object RD_LOCK = new Object();
   private static ReflectPermission suppressAccessChecksPermission = new ReflectPermission("suppressAccessChecks");
   private static volatile RuntimeDelegate cachedDelegate;

   protected RuntimeDelegate() {
   }

   public static RuntimeDelegate getInstance() {
      RuntimeDelegate result = cachedDelegate;
      if (result == null) {
         synchronized(RD_LOCK) {
            result = cachedDelegate;
            if (result == null) {
               cachedDelegate = result = findDelegate();
            }
         }
      }

      return result;
   }

   private static RuntimeDelegate findDelegate() {
      try {
         Object delegate = FactoryFinder.find("jakarta.ws.rs.ext.RuntimeDelegate", RuntimeDelegate.class);
         if (!(delegate instanceof RuntimeDelegate)) {
            Class pClass = RuntimeDelegate.class;
            String classnameAsResource = pClass.getName().replace('.', '/') + ".class";
            ClassLoader loader = pClass.getClassLoader();
            if (loader == null) {
               loader = ClassLoader.getSystemClassLoader();
            }

            URL targetTypeURL = loader.getResource(classnameAsResource);
            throw new LinkageError("ClassCastException: attempting to cast" + delegate.getClass().getClassLoader().getResource(classnameAsResource) + " to " + targetTypeURL);
         } else {
            return (RuntimeDelegate)delegate;
         }
      } catch (Exception ex) {
         throw new RuntimeException(ex);
      }
   }

   public static void setInstance(RuntimeDelegate rd) {
      SecurityManager security = System.getSecurityManager();
      if (security != null) {
         security.checkPermission(suppressAccessChecksPermission);
      }

      synchronized(RD_LOCK) {
         cachedDelegate = rd;
      }
   }

   public abstract UriBuilder createUriBuilder();

   public abstract Response.ResponseBuilder createResponseBuilder();

   public abstract Variant.VariantListBuilder createVariantListBuilder();

   public abstract Object createEndpoint(Application var1, Class var2) throws IllegalArgumentException, UnsupportedOperationException;

   public abstract HeaderDelegate createHeaderDelegate(Class var1) throws IllegalArgumentException;

   public abstract Link.Builder createLinkBuilder();

   public interface HeaderDelegate {
      Object fromString(String var1);

      String toString(Object var1);
   }
}
