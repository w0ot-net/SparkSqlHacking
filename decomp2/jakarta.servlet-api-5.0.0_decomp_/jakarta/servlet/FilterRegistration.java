package jakarta.servlet;

import java.util.Collection;
import java.util.EnumSet;

public interface FilterRegistration extends Registration {
   void addMappingForServletNames(EnumSet var1, boolean var2, String... var3);

   Collection getServletNameMappings();

   void addMappingForUrlPatterns(EnumSet var1, boolean var2, String... var3);

   Collection getUrlPatternMappings();

   public interface Dynamic extends FilterRegistration, Registration.Dynamic {
   }
}
