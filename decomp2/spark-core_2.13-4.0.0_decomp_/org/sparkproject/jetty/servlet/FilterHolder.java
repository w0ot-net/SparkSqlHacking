package org.sparkproject.jetty.servlet;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.thread.AutoLock;

public class FilterHolder extends Holder {
   private static final Logger LOG = LoggerFactory.getLogger(FilterHolder.class);
   private transient Filter _filter;
   private transient Config _config;
   private transient FilterRegistration.Dynamic _registration;

   public FilterHolder() {
      this(Source.EMBEDDED);
   }

   public FilterHolder(Source source) {
      super(source);
   }

   public FilterHolder(Class filter) {
      this(Source.EMBEDDED);
      this.setHeldClass(filter);
   }

   public FilterHolder(Filter filter) {
      this(Source.EMBEDDED);
      this.setFilter(filter);
   }

   public void doStart() throws Exception {
      super.doStart();
      if (!Filter.class.isAssignableFrom(this.getHeldClass())) {
         String msg = String.valueOf(this.getHeldClass()) + " is not a jakarta.servlet.Filter";
         this.doStop();
         throw new IllegalStateException(msg);
      }
   }

   public void initialize() throws Exception {
      try (AutoLock l = this.lock()) {
         if (this._filter != null) {
            return;
         }

         super.initialize();
         this._filter = (Filter)this.getInstance();
         if (this._filter == null) {
            try {
               this._filter = this.createInstance();
            } catch (ServletException ex) {
               Throwable cause = ex.getRootCause();
               if (cause instanceof InstantiationException) {
                  throw (InstantiationException)cause;
               }

               if (cause instanceof IllegalAccessException) {
                  throw (IllegalAccessException)cause;
               }

               throw ex;
            }
         }

         this._filter = (Filter)this.wrap(this._filter, WrapFunction.class, WrapFunction::wrapFilter);
         this._config = new Config();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Filter.init {}", this._filter);
         }

         this._filter.init(this._config);
      }

   }

   protected Filter createInstance() throws Exception {
      try (AutoLock l = this.lock()) {
         Filter filter = (Filter)super.createInstance();
         if (filter == null) {
            ServletContext context = this.getServletContext();
            if (context != null) {
               filter = context.createFilter(this.getHeldClass());
            }
         }

         return filter;
      }
   }

   public void doStop() throws Exception {
      super.doStop();
      this._config = null;
      if (this._filter != null) {
         try {
            this.destroyInstance(this._filter);
         } finally {
            this._filter = null;
         }
      }

   }

   public void destroyInstance(Object o) {
      if (o != null) {
         Filter filter = (Filter)o;
         this.getServletHandler().destroyFilter((Filter)this.unwrap(filter));
         filter.destroy();
      }
   }

   public void setFilter(Filter filter) {
      this.setInstance(filter);
   }

   public Filter getFilter() {
      return this._filter;
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      if (!this.isAsyncSupported() && request.isAsyncSupported()) {
         Request baseRequest = Request.getBaseRequest(request);
         Objects.requireNonNull(baseRequest);

         try {
            baseRequest.setAsyncSupported(false, this);
            this.getFilter().doFilter(request, response, chain);
         } finally {
            baseRequest.setAsyncSupported(true, (Object)null);
         }
      } else {
         this.getFilter().doFilter(request, response, chain);
      }

   }

   public void dump(Appendable out, String indent) throws IOException {
      if (this.getInitParameters().isEmpty()) {
         Dumpable.dumpObjects(out, indent, this, this._filter == null ? this.getHeldClass() : this._filter);
      } else {
         Dumpable.dumpObjects(out, indent, this, this._filter == null ? this.getHeldClass() : this._filter, new DumpableCollection("initParams", this.getInitParameters().entrySet()));
      }

   }

   public String toString() {
      return String.format("%s==%s@%x{inst=%b,async=%b,src=%s}", this.getName(), this.getClassName(), this.hashCode(), this._filter != null, this.isAsyncSupported(), this.getSource());
   }

   public FilterRegistration.Dynamic getRegistration() {
      if (this._registration == null) {
         this._registration = new Registration();
      }

      return this._registration;
   }

   protected class Registration extends Holder.HolderRegistration implements FilterRegistration.Dynamic {
      public void addMappingForServletNames(EnumSet dispatcherTypes, boolean isMatchAfter, String... servletNames) {
         FilterHolder.this.illegalStateIfContextStarted();
         FilterMapping mapping = new FilterMapping();
         mapping.setFilterHolder(FilterHolder.this);
         mapping.setServletNames(servletNames);
         mapping.setDispatcherTypes(dispatcherTypes);
         if (isMatchAfter) {
            FilterHolder.this.getServletHandler().addFilterMapping(mapping);
         } else {
            FilterHolder.this.getServletHandler().prependFilterMapping(mapping);
         }

      }

      public void addMappingForUrlPatterns(EnumSet dispatcherTypes, boolean isMatchAfter, String... urlPatterns) {
         FilterHolder.this.illegalStateIfContextStarted();
         FilterMapping mapping = new FilterMapping();
         mapping.setFilterHolder(FilterHolder.this);
         mapping.setPathSpecs(urlPatterns);
         mapping.setDispatcherTypes(dispatcherTypes);
         if (isMatchAfter) {
            FilterHolder.this.getServletHandler().addFilterMapping(mapping);
         } else {
            FilterHolder.this.getServletHandler().prependFilterMapping(mapping);
         }

      }

      public Collection getServletNameMappings() {
         FilterMapping[] mappings = FilterHolder.this.getServletHandler().getFilterMappings();
         List<String> names = new ArrayList();

         for(FilterMapping mapping : mappings) {
            if (mapping.getFilterHolder() == FilterHolder.this) {
               String[] servlets = mapping.getServletNames();
               if (servlets != null && servlets.length > 0) {
                  names.addAll(Arrays.asList(servlets));
               }
            }
         }

         return names;
      }

      public Collection getUrlPatternMappings() {
         FilterMapping[] mappings = FilterHolder.this.getServletHandler().getFilterMappings();
         List<String> patterns = new ArrayList();

         for(FilterMapping mapping : mappings) {
            if (mapping.getFilterHolder() == FilterHolder.this) {
               String[] specs = mapping.getPathSpecs();
               patterns.addAll(TypeUtil.asList(specs));
            }
         }

         return patterns;
      }
   }

   class Config extends Holder.HolderConfig implements FilterConfig {
      public String getFilterName() {
         return FilterHolder.this.getName();
      }
   }

   public static class Wrapper implements Filter, BaseHolder.Wrapped {
      private final Filter _filter;

      public Wrapper(Filter filter) {
         this._filter = (Filter)Objects.requireNonNull(filter, "Filter cannot be null");
      }

      public Filter getWrapped() {
         return this._filter;
      }

      public void init(FilterConfig filterConfig) throws ServletException {
         this._filter.init(filterConfig);
      }

      public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         this._filter.doFilter(request, response, chain);
      }

      public void destroy() {
         this._filter.destroy();
      }

      public String toString() {
         return String.format("%s:%s", this.getClass().getSimpleName(), this._filter.toString());
      }
   }

   public interface WrapFunction {
      Filter wrapFilter(Filter var1);
   }
}
