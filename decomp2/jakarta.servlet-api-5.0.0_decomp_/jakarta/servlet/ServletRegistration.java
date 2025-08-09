package jakarta.servlet;

import java.util.Collection;
import java.util.Set;

public interface ServletRegistration extends Registration {
   Set addMapping(String... var1);

   Collection getMappings();

   String getRunAsRole();

   public interface Dynamic extends ServletRegistration, Registration.Dynamic {
      void setLoadOnStartup(int var1);

      Set setServletSecurity(ServletSecurityElement var1);

      void setMultipartConfig(MultipartConfigElement var1);

      void setRunAsRole(String var1);
   }
}
