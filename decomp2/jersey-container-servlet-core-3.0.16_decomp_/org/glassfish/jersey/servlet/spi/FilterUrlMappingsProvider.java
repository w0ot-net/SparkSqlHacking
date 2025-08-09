package org.glassfish.jersey.servlet.spi;

import jakarta.servlet.FilterConfig;
import java.util.List;

public interface FilterUrlMappingsProvider {
   List getFilterUrlMappings(FilterConfig var1);
}
