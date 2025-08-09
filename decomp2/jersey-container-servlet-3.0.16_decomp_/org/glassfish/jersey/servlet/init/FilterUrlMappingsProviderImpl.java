package org.glassfish.jersey.servlet.init;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.FilterRegistration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.glassfish.jersey.servlet.spi.FilterUrlMappingsProvider;

public class FilterUrlMappingsProviderImpl implements FilterUrlMappingsProvider {
   public List getFilterUrlMappings(FilterConfig filterConfig) {
      FilterRegistration filterRegistration = filterConfig.getServletContext().getFilterRegistration(filterConfig.getFilterName());
      Collection<String> urlPatternMappings = filterRegistration.getUrlPatternMappings();
      List<String> result = new ArrayList();

      for(String pattern : urlPatternMappings) {
         result.add(pattern.endsWith("*") ? pattern.substring(0, pattern.length() - 1) : pattern);
      }

      return result;
   }
}
