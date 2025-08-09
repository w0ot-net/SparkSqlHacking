package org.sparkproject.jetty.servlet;

import [Lorg.sparkproject.jetty.servlet.FilterHolder;;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.pathmap.MappedResource;
import org.sparkproject.jetty.http.pathmap.MatchedPath;
import org.sparkproject.jetty.http.pathmap.MatchedResource;
import org.sparkproject.jetty.http.pathmap.PathMappings;
import org.sparkproject.jetty.http.pathmap.PathSpec;
import org.sparkproject.jetty.http.pathmap.ServletPathSpec;
import org.sparkproject.jetty.security.IdentityService;
import org.sparkproject.jetty.security.SecurityHandler;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.ServletPathMapping;
import org.sparkproject.jetty.server.ServletRequestHttpWrapper;
import org.sparkproject.jetty.server.ServletResponseHttpWrapper;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ScopedHandler;
import org.sparkproject.jetty.util.ArrayUtil;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject("Servlet Handler")
public class ServletHandler extends ScopedHandler {
   private static final Logger LOG = LoggerFactory.getLogger(ServletHandler.class);
   private final AutoLock _lock = new AutoLock();
   private ServletContextHandler _contextHandler;
   private ServletContext _servletContext;
   private final List _filters = new ArrayList();
   private final List _filterMappings = new ArrayList();
   private int _matchBeforeIndex = -1;
   private int _matchAfterIndex = -1;
   private boolean _filterChainsCached = true;
   private int _maxFilterChainsCacheSize = 1024;
   private boolean _startWithUnavailable = false;
   private boolean _ensureDefaultServlet = true;
   private IdentityService _identityService;
   private boolean _allowDuplicateMappings = false;
   private final List _servlets = new ArrayList();
   private final List _servletMappings = new ArrayList();
   private final Map _filterNameMap = new HashMap();
   private List _filterPathMappings;
   private MultiMap _filterNameMappings;
   private List _wildFilterNameMappings;
   private final List _durable = new ArrayList();
   private final Map _servletNameMap = new HashMap();
   private PathMappings _servletPathMap;
   private final List _listeners = new ArrayList();
   private boolean _initialized = false;
   protected final ConcurrentMap[] _chainCache = new ConcurrentMap[31];

   AutoLock lock() {
      return this._lock.lock();
   }

   private void updateAndSet(Collection target, Collection values) {
      this.updateBeans(target, values);
      target.clear();
      target.addAll(values);
   }

   public boolean isDumpable(Object o) {
      return !(o instanceof BaseHolder) && !(o instanceof FilterMapping) && !(o instanceof ServletMapping);
   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{DumpableCollection.from("listeners " + String.valueOf(this), (Collection)this._listeners), DumpableCollection.from("filters " + String.valueOf(this), (Collection)this._filters), DumpableCollection.from("filterMappings " + String.valueOf(this), (Collection)this._filterMappings), DumpableCollection.from("servlets " + String.valueOf(this), (Collection)this._servlets), DumpableCollection.from("servletMappings " + String.valueOf(this), (Collection)this._servletMappings), DumpableCollection.from("durable " + String.valueOf(this), (Collection)this._durable)});
   }

   protected void doStart() throws Exception {
      try (AutoLock ignored = this.lock()) {
         ContextHandler.Context context = ContextHandler.getCurrentContext();
         this._servletContext = (ServletContext)(context == null ? new ContextHandler.StaticContext() : context);
         this._contextHandler = (ServletContextHandler)(context == null ? null : context.getContextHandler());
         if (this._contextHandler != null) {
            SecurityHandler securityHandler = (SecurityHandler)this._contextHandler.getChildHandlerByClass(SecurityHandler.class);
            if (securityHandler != null) {
               this._identityService = securityHandler.getIdentityService();
            }
         }

         this._durable.clear();
         this._durable.addAll(Arrays.asList(this.getFilters()));
         this._durable.addAll(Arrays.asList(this.getServlets()));
         this._durable.addAll(Arrays.asList(this.getListeners()));
         this.updateNameMappings();
         this.updateMappings();
         if (this.getServletMapping("/") == null && this.isEnsureDefaultServlet()) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Adding Default404Servlet to {}", this);
            }

            this.addServletWithMapping(Default404Servlet.class, "/");
            this.updateMappings();
            this.getServletMapping("/").setFromDefaultDescriptor(true);
         }

         if (this.isFilterChainsCached()) {
            this._chainCache[1] = new ConcurrentHashMap();
            this._chainCache[2] = new ConcurrentHashMap();
            this._chainCache[4] = new ConcurrentHashMap();
            this._chainCache[8] = new ConcurrentHashMap();
            this._chainCache[16] = new ConcurrentHashMap();
         }

         if (this._contextHandler == null) {
            this.initialize();
         }

         super.doStart();
      }

   }

   public boolean isEnsureDefaultServlet() {
      return this._ensureDefaultServlet;
   }

   public void setEnsureDefaultServlet(boolean ensureDefaultServlet) {
      this._ensureDefaultServlet = ensureDefaultServlet;
   }

   protected void start(LifeCycle l) throws Exception {
      if (!(l instanceof Holder)) {
         super.start(l);
      }

   }

   protected void stop(LifeCycle l) throws Exception {
      if (!(l instanceof Holder)) {
         super.stop(l);
      }

   }

   protected void doStop() throws Exception {
      try (AutoLock ignored = this.lock()) {
         super.doStop();
         List<FilterHolder> filterHolders = new ArrayList();
         int i = this._filters.size();

         while(i-- > 0) {
            FilterHolder filter = (FilterHolder)this._filters.get(i);

            try {
               filter.stop();
            } catch (Exception e) {
               LOG.warn("Unable to stop filter {}", filter, e);
            }

            if (this._durable.contains(filter)) {
               filterHolders.add(filter);
            }
         }

         this.updateBeans(this._filters, filterHolders);
         this._filters.clear();
         this._filters.addAll(filterHolders);
         List<ServletHolder> servletHolders = new ArrayList();
         int i = this._servlets.size();

         while(i-- > 0) {
            ServletHolder servlet = (ServletHolder)this._servlets.get(i);

            try {
               servlet.stop();
            } catch (Exception e) {
               LOG.warn("Unable to stop servlet {}", servlet, e);
            }

            if (this._durable.contains(servlet)) {
               servletHolders.add(servlet);
            }
         }

         this.updateBeans(this._servlets, servletHolders);
         this._servlets.clear();
         this._servlets.addAll(servletHolders);
         this.updateNameMappings();
         this.updateAndSet(this._servletMappings, (Collection)this._servletMappings.stream().filter((m) -> this._servletNameMap.containsKey(m.getServletName())).collect(Collectors.toList()));
         this.updateAndSet(this._filterMappings, (Collection)this._filterMappings.stream().filter((m) -> this._filterNameMap.containsKey(m.getFilterName())).collect(Collectors.toList()));
         this.updateMappings();
         if (this._contextHandler != null) {
            this._contextHandler.contextDestroyed();
         }

         List<ListenerHolder> listenerHolders = new ArrayList();
         int i = this._listeners.size();

         while(i-- > 0) {
            ListenerHolder listener = (ListenerHolder)this._listeners.get(i);

            try {
               listener.stop();
            } catch (Exception e) {
               LOG.warn("Unable to stop listener {}", listener, e);
            }

            if (this._durable.contains(listener)) {
               listenerHolders.add(listener);
            }
         }

         this.updateBeans(this._listeners, listenerHolders);
         this._listeners.clear();
         this._listeners.addAll(listenerHolders);
         this._matchAfterIndex = this._filterMappings.size() == 0 ? -1 : this._filterMappings.size() - 1;
         this._matchBeforeIndex = -1;
         this._durable.clear();
         this._filterPathMappings = null;
         this._filterNameMappings = null;
         this._servletPathMap = null;
         this._initialized = false;
      }

   }

   protected IdentityService getIdentityService() {
      return this._identityService;
   }

   @ManagedAttribute(
      value = "filters",
      readonly = true
   )
   public FilterMapping[] getFilterMappings() {
      return (FilterMapping[])this._filterMappings.toArray(new FilterMapping[0]);
   }

   @ManagedAttribute(
      value = "filters",
      readonly = true
   )
   public FilterHolder[] getFilters() {
      return (FilterHolder[])this._filters.toArray(new FilterHolder[0]);
   }

   /** @deprecated */
   @Deprecated
   public MappedResource getHolderEntry(String target) {
      if (target.startsWith("/")) {
         MatchedResource<MappedServlet> matchedResource = this.getMatchedServlet(target);
         return new MappedResource(matchedResource.getPathSpec(), (MappedServlet)matchedResource.getResource());
      } else {
         return null;
      }
   }

   public ServletContext getServletContext() {
      return this._servletContext;
   }

   public ServletContextHandler getServletContextHandler() {
      return this._contextHandler;
   }

   @ManagedAttribute(
      value = "mappings of servlets",
      readonly = true
   )
   public ServletMapping[] getServletMappings() {
      return (ServletMapping[])this._servletMappings.toArray(new ServletMapping[0]);
   }

   public ServletMapping getServletMapping(String pathSpec) {
      if (pathSpec == null) {
         return null;
      } else {
         ServletMapping mapping = null;

         for(int i = 0; i < this._servletMappings.size() && mapping == null; ++i) {
            ServletMapping m = (ServletMapping)this._servletMappings.get(i);
            if (m.getPathSpecs() != null) {
               for(String p : m.getPathSpecs()) {
                  if (pathSpec.equals(p)) {
                     mapping = m;
                     break;
                  }
               }
            }
         }

         return mapping;
      }
   }

   @ManagedAttribute(
      value = "servlets",
      readonly = true
   )
   public ServletHolder[] getServlets() {
      return (ServletHolder[])this._servlets.toArray(new ServletHolder[0]);
   }

   public List getServlets(Class clazz) {
      List<ServletHolder> holders = null;

      for(ServletHolder holder : this._servlets) {
         Class<? extends Servlet> held = holder.getHeldClass();
         if (held == null && holder.getClassName() != null && holder.getClassName().equals(clazz.getName()) || held != null && clazz.isAssignableFrom(holder.getHeldClass())) {
            if (holders == null) {
               holders = new ArrayList();
            }

            holders.add(holder);
         }
      }

      return holders == null ? Collections.emptyList() : holders;
   }

   public ServletHolder getServlet(String name) {
      MappedServlet mapped = (MappedServlet)this._servletNameMap.get(name);
      return mapped != null ? mapped.getServletHolder() : null;
   }

   public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      ServletPathMapping old_servlet_path_mapping = baseRequest.getServletPathMapping();
      ServletHolder servletHolder = null;
      UserIdentity.Scope oldScope = null;
      MatchedResource<MappedServlet> matched = this.getMatchedServlet(target);
      if (matched != null) {
         MappedServlet mappedServlet = (MappedServlet)matched.getResource();
         servletHolder = mappedServlet.getServletHolder();
         ServletPathMapping servletPathMapping = mappedServlet.getServletPathMapping(target, matched.getMatchedPath());
         if (servletPathMapping != null) {
            baseRequest.setServletPathMapping(servletPathMapping);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("servlet {}|{}|{}|{} -> {}", new Object[]{baseRequest.getContextPath(), baseRequest.getServletPath(), baseRequest.getPathInfo(), baseRequest.getHttpServletMapping(), servletHolder});
      }

      try {
         oldScope = baseRequest.getUserIdentityScope();
         baseRequest.setUserIdentityScope(servletHolder);
         this.nextScope(target, baseRequest, request, response);
      } finally {
         if (oldScope != null) {
            baseRequest.setUserIdentityScope(oldScope);
         }

         baseRequest.setServletPathMapping(old_servlet_path_mapping);
      }

   }

   public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      ServletHolder servletHolder = (ServletHolder)baseRequest.getUserIdentityScope();
      FilterChain chain = null;
      if (servletHolder != null && this._filterMappings.size() > 0) {
         chain = this.getFilterChain(baseRequest, target.startsWith("/") ? target : null, servletHolder);
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("chain={}", chain);
      }

      try {
         if (servletHolder == null) {
            this.notFound(baseRequest, request, response);
         } else {
            ServletRequest req = request;
            if (request instanceof ServletRequestHttpWrapper) {
               req = ((ServletRequestHttpWrapper)request).getRequest();
            }

            ServletResponse res = response;
            if (response instanceof ServletResponseHttpWrapper) {
               res = ((ServletResponseHttpWrapper)response).getResponse();
            }

            servletHolder.prepare(baseRequest, req, res);
            if (chain != null) {
               chain.doFilter(req, res);
            } else {
               servletHolder.handle(baseRequest, req, res);
            }
         }
      } finally {
         if (servletHolder != null) {
            baseRequest.setHandled(true);
         }

      }

   }

   public MatchedResource getMatchedServlet(String target) {
      if (target.startsWith("/")) {
         return this._servletPathMap == null ? null : this._servletPathMap.getMatched(target);
      } else {
         MappedServlet holder = (MappedServlet)this._servletNameMap.get(target);
         return holder == null ? null : new MatchedResource(holder, (PathSpec)null, MatchedPath.EMPTY);
      }
   }

   /** @deprecated */
   @Deprecated
   public MappedServlet getMappedServlet(String target) {
      MatchedResource<MappedServlet> matchedResource = this.getMatchedServlet(target);
      return (MappedServlet)matchedResource.getResource();
   }

   protected FilterChain getFilterChain(Request baseRequest, String pathInContext, ServletHolder servletHolder) {
      Objects.requireNonNull(servletHolder);
      String key = pathInContext == null ? servletHolder.getName() : pathInContext;
      int dispatch = FilterMapping.dispatch(baseRequest.getDispatcherType());
      if (this._filterChainsCached) {
         FilterChain chain = (FilterChain)this._chainCache[dispatch].get(key);
         if (chain != null) {
            return chain;
         }
      }

      FilterChain chain = null;
      if (this._filterNameMappings != null && !this._filterNameMappings.isEmpty()) {
         if (this._wildFilterNameMappings != null) {
            for(FilterMapping mapping : this._wildFilterNameMappings) {
               chain = this.newFilterChain(mapping.getFilterHolder(), (FilterChain)(chain == null ? new ChainEnd(servletHolder) : chain));
            }
         }

         List<FilterMapping> nameMappings = (List)this._filterNameMappings.get(servletHolder.getName());
         if (nameMappings != null) {
            for(FilterMapping mapping : nameMappings) {
               if (mapping.appliesTo(dispatch)) {
                  chain = this.newFilterChain(mapping.getFilterHolder(), (FilterChain)(chain == null ? new ChainEnd(servletHolder) : chain));
               }
            }
         }
      }

      if (pathInContext != null && this._filterPathMappings != null) {
         for(FilterMapping mapping : this._filterPathMappings) {
            if (mapping.appliesTo(pathInContext, dispatch)) {
               chain = this.newFilterChain(mapping.getFilterHolder(), (FilterChain)(chain == null ? new ChainEnd(servletHolder) : chain));
            }
         }
      }

      if (this._filterChainsCached) {
         Map<String, FilterChain> cache = this._chainCache[dispatch];
         if (this._maxFilterChainsCacheSize > 0 && cache.size() >= this._maxFilterChainsCacheSize) {
            LOG.debug("{} flushed filter chain cache for {}", this, baseRequest.getDispatcherType());
            cache.clear();
         }

         chain = (FilterChain)(chain == null ? new ChainEnd(servletHolder) : chain);
         LOG.debug("{} cached filter chain for {}: {}", new Object[]{this, baseRequest.getDispatcherType(), chain});
         cache.put(key, chain);
      }

      return chain;
   }

   protected FilterChain newFilterChain(FilterHolder filterHolder, FilterChain chain) {
      return new Chain(filterHolder, chain);
   }

   protected void invalidateChainsCache() {
      if (this._chainCache[1] != null) {
         this._chainCache[1].clear();
         this._chainCache[2].clear();
         this._chainCache[4].clear();
         this._chainCache[8].clear();
         this._chainCache[16].clear();
      }

   }

   public boolean isAvailable() {
      if (!this.isStarted()) {
         return false;
      } else {
         ServletHolder[] holders = this.getServlets();

         for(ServletHolder holder : holders) {
            if (holder != null && !holder.isAvailable()) {
               return false;
            }
         }

         return true;
      }
   }

   public void setStartWithUnavailable(boolean start) {
      this._startWithUnavailable = start;
   }

   public boolean isAllowDuplicateMappings() {
      return this._allowDuplicateMappings;
   }

   public void setAllowDuplicateMappings(boolean allowDuplicateMappings) {
      this._allowDuplicateMappings = allowDuplicateMappings;
   }

   public boolean isStartWithUnavailable() {
      return this._startWithUnavailable;
   }

   public void initialize() throws Exception {
      MultiException mx = new MultiException();
      Consumer<BaseHolder<?>> c = (h) -> {
         try {
            if (!h.isStarted()) {
               h.start();
               h.initialize();
            }
         } catch (Throwable e) {
            LOG.debug("Unable to start {}", h, e);
            mx.add(e);
         }

      };
      this._listeners.forEach(c);
      if (this._contextHandler != null) {
         this._contextHandler.contextInitialized();
      }

      this._initialized = true;
      Stream.concat(this._filters.stream(), this._servlets.stream().sorted()).forEach(c);
      mx.ifExceptionThrow();
   }

   public boolean isInitialized() {
      return this._initialized;
   }

   protected void initializeHolders(Collection holders) {
      for(BaseHolder holder : holders) {
         holder.setServletHandler(this);
         if (this.isInitialized()) {
            try {
               if (!holder.isStarted()) {
                  holder.start();
                  holder.initialize();
               }
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         }
      }

   }

   public boolean isFilterChainsCached() {
      return this._filterChainsCached;
   }

   public void addListener(ListenerHolder listener) {
      if (listener != null) {
         this.setListeners((ListenerHolder[])ArrayUtil.addToArray(this.getListeners(), listener, ListenerHolder.class));
      }

   }

   public ListenerHolder[] getListeners() {
      return (ListenerHolder[])this._listeners.toArray(new ListenerHolder[0]);
   }

   public void setListeners(ListenerHolder[] holders) {
      List<ListenerHolder> listeners = holders == null ? Collections.emptyList() : Arrays.asList(holders);
      this.initializeHolders(listeners);
      this.updateBeans(this._listeners, listeners);
      this._listeners.clear();
      this._listeners.addAll(listeners);
   }

   public ListenerHolder newListenerHolder(Source source) {
      return new ListenerHolder(source);
   }

   public ServletHolder newServletHolder(Source source) {
      return new ServletHolder(source);
   }

   public ServletHolder addServletWithMapping(String className, String pathSpec) {
      ServletHolder holder = this.newServletHolder(Source.EMBEDDED);
      holder.setClassName(className);
      this.addServletWithMapping(holder, pathSpec);
      return holder;
   }

   public ServletHolder addServletWithMapping(Class servlet, String pathSpec) {
      ServletHolder holder = this.newServletHolder(Source.EMBEDDED);
      holder.setHeldClass(servlet);
      this.addServletWithMapping(holder, pathSpec);
      return holder;
   }

   public void addServletWithMapping(ServletHolder servlet, String pathSpec) {
      Objects.requireNonNull(servlet);
      ServletHolder[] holders = this.getServlets();

      try {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsServletHolder(servlet)) {
               this.setServlets((ServletHolder[])ArrayUtil.addToArray(holders, servlet, ServletHolder.class));
            }
         }

         ServletMapping mapping = new ServletMapping();
         mapping.setServletName(servlet.getName());
         mapping.setPathSpec(pathSpec);
         this.setServletMappings((ServletMapping[])ArrayUtil.addToArray(this.getServletMappings(), mapping, ServletMapping.class));
      } catch (RuntimeException e) {
         this.setServlets(holders);
         throw e;
      }
   }

   public void addServlet(ServletHolder holder) {
      if (holder != null) {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsServletHolder(holder)) {
               this.setServlets((ServletHolder[])ArrayUtil.addToArray(this.getServlets(), holder, ServletHolder.class));
            }
         }

      }
   }

   public void addServletMapping(ServletMapping mapping) {
      this.setServletMappings((ServletMapping[])ArrayUtil.addToArray(this.getServletMappings(), mapping, ServletMapping.class));
   }

   public Set setServletSecurity(ServletRegistration.Dynamic registration, ServletSecurityElement servletSecurityElement) {
      return this._contextHandler != null ? this._contextHandler.setServletSecurity(registration, servletSecurityElement) : Collections.emptySet();
   }

   public FilterHolder newFilterHolder(Source source) {
      return new FilterHolder(source);
   }

   public FilterHolder getFilter(String name) {
      return (FilterHolder)this._filterNameMap.get(name);
   }

   public FilterHolder addFilterWithMapping(Class filter, String pathSpec, EnumSet dispatches) {
      FilterHolder holder = this.newFilterHolder(Source.EMBEDDED);
      holder.setHeldClass(filter);
      this.addFilterWithMapping(holder, pathSpec, dispatches);
      return holder;
   }

   public FilterHolder addFilterWithMapping(String className, String pathSpec, EnumSet dispatches) {
      FilterHolder holder = this.newFilterHolder(Source.EMBEDDED);
      holder.setClassName(className);
      this.addFilterWithMapping(holder, pathSpec, dispatches);
      return holder;
   }

   public void addFilterWithMapping(FilterHolder holder, String pathSpec, EnumSet dispatches) {
      Objects.requireNonNull(holder);
      FilterHolder[] holders = this.getFilters();

      try {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsFilterHolder(holder)) {
               this.setFilters((FilterHolder[])ArrayUtil.addToArray(holders, holder, FilterHolder.class));
            }
         }

         FilterMapping mapping = new FilterMapping();
         mapping.setFilterName(holder.getName());
         mapping.setPathSpec(pathSpec);
         mapping.setDispatcherTypes(dispatches);
         this.addFilterMapping(mapping);
      } catch (Throwable e) {
         this.setFilters(holders);
         throw e;
      }
   }

   public FilterHolder addFilterWithMapping(Class filter, String pathSpec, int dispatches) {
      FilterHolder holder = this.newFilterHolder(Source.EMBEDDED);
      holder.setHeldClass(filter);
      this.addFilterWithMapping(holder, pathSpec, dispatches);
      return holder;
   }

   public FilterHolder addFilterWithMapping(String className, String pathSpec, int dispatches) {
      FilterHolder holder = this.newFilterHolder(Source.EMBEDDED);
      holder.setClassName(className);
      this.addFilterWithMapping(holder, pathSpec, dispatches);
      return holder;
   }

   public void addFilterWithMapping(FilterHolder holder, String pathSpec, int dispatches) {
      Objects.requireNonNull(holder);
      FilterHolder[] holders = this.getFilters();
      if (holders != null) {
         holders = (FilterHolder[])((FilterHolder;)holders).clone();
      }

      try {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsFilterHolder(holder)) {
               this.setFilters((FilterHolder[])ArrayUtil.addToArray(holders, holder, FilterHolder.class));
            }
         }

         FilterMapping mapping = new FilterMapping();
         mapping.setFilterName(holder.getName());
         mapping.setPathSpec(pathSpec);
         mapping.setDispatches(dispatches);
         this.addFilterMapping(mapping);
      } catch (Throwable e) {
         this.setFilters(holders);
         throw e;
      }
   }

   public void addFilter(FilterHolder filter, FilterMapping filterMapping) {
      if (filter != null) {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsFilterHolder(filter)) {
               this.setFilters((FilterHolder[])ArrayUtil.addToArray(this.getFilters(), filter, FilterHolder.class));
            }
         }
      }

      if (filterMapping != null) {
         this.addFilterMapping(filterMapping);
      }

   }

   public void addFilter(FilterHolder filter) {
      if (filter != null) {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsFilterHolder(filter)) {
               this.setFilters((FilterHolder[])ArrayUtil.addToArray(this.getFilters(), filter, FilterHolder.class));
            }
         }

      }
   }

   public void prependFilter(FilterHolder filter) {
      if (filter != null) {
         try (AutoLock ignored = this.lock()) {
            if (!this.containsFilterHolder(filter)) {
               this.setFilters((FilterHolder[])ArrayUtil.prependToArray(filter, this.getFilters(), FilterHolder.class));
            }
         }

      }
   }

   public void addFilterMapping(FilterMapping mapping) {
      if (mapping != null) {
         try (AutoLock ignored = this.lock()) {
            Source source = mapping.getFilterHolder() == null ? null : mapping.getFilterHolder().getSource();
            if (this._filterMappings.isEmpty()) {
               this._filterMappings.add(mapping);
               if (source == Source.JAVAX_API) {
                  this._matchAfterIndex = 0;
               }
            } else if (Source.JAVAX_API == source) {
               this._filterMappings.add(mapping);
               if (this._matchAfterIndex < 0) {
                  this._matchAfterIndex = this.getFilterMappings().length - 1;
               }
            } else if (this._matchAfterIndex < 0) {
               this._filterMappings.add(mapping);
            } else {
               this._filterMappings.add(this._matchAfterIndex++, mapping);
            }

            this.addBean(mapping);
            if (this.isRunning()) {
               this.updateMappings();
            }

            this.invalidateChainsCache();
         }

      }
   }

   public void prependFilterMapping(FilterMapping mapping) {
      if (mapping != null) {
         try (AutoLock ignored = this.lock()) {
            Source source = mapping.getFilterHolder() == null ? null : mapping.getFilterHolder().getSource();
            if (this._filterMappings.isEmpty()) {
               this._filterMappings.add(mapping);
               if (Source.JAVAX_API == source) {
                  this._matchBeforeIndex = 0;
               }
            } else {
               if (Source.JAVAX_API == source) {
                  if (this._matchBeforeIndex < 0) {
                     this._matchBeforeIndex = 0;
                     this._filterMappings.add(0, mapping);
                  } else {
                     this._filterMappings.add(1 + this._matchBeforeIndex++, mapping);
                  }
               } else {
                  this._filterMappings.add(0, mapping);
               }

               if (this._matchAfterIndex >= 0) {
                  ++this._matchAfterIndex;
               }
            }

            this.addBean(mapping);
            if (this.isRunning()) {
               this.updateMappings();
            }

            this.invalidateChainsCache();
         }

      }
   }

   public void removeFilterHolder(FilterHolder holder) {
      if (holder != null) {
         try (AutoLock ignored = this.lock()) {
            FilterHolder[] holders = (FilterHolder[])Arrays.stream(this.getFilters()).filter((h) -> h != holder).toArray((x$0) -> new FilterHolder[x$0]);
            this.setFilters(holders);
         }

      }
   }

   public void removeFilterMapping(FilterMapping mapping) {
      if (mapping != null) {
         try (AutoLock ignored = this.lock()) {
            FilterMapping[] mappings = (FilterMapping[])Arrays.stream(this.getFilterMappings()).filter((m) -> m != mapping).toArray((x$0) -> new FilterMapping[x$0]);
            this.setFilterMappings(mappings);
         }

      }
   }

   protected void updateNameMappings() {
      try (AutoLock ignored = this.lock()) {
         this._filterNameMap.clear();

         for(FilterHolder filter : this._filters) {
            this._filterNameMap.put(filter.getName(), filter);
            filter.setServletHandler(this);
         }

         this._servletNameMap.clear();

         for(ServletHolder servlet : this._servlets) {
            this._servletNameMap.put(servlet.getName(), new MappedServlet((PathSpec)null, servlet));
            servlet.setServletHandler(this);
         }
      }

   }

   protected PathSpec asPathSpec(String pathSpec) {
      return new ServletPathSpec(pathSpec);
   }

   protected void updateMappings() {
      try (AutoLock ignored = this.lock()) {
         this._filterPathMappings = new ArrayList();
         this._filterNameMappings = new MultiMap();

         for(FilterMapping filtermapping : this._filterMappings) {
            FilterHolder filterHolder = (FilterHolder)this._filterNameMap.get(filtermapping.getFilterName());
            if (filterHolder == null) {
               throw new IllegalStateException("No filter named " + filtermapping.getFilterName());
            }

            filtermapping.setFilterHolder(filterHolder);
            if (filtermapping.getPathSpecs() != null) {
               this._filterPathMappings.add(filtermapping);
            }

            if (filtermapping.getServletNames() != null) {
               String[] names = filtermapping.getServletNames();

               for(String name : names) {
                  if (name != null) {
                     this._filterNameMappings.add(name, filtermapping);
                  }
               }
            }
         }

         for(Map.Entry entry : this._filterNameMappings.entrySet()) {
            Collections.reverse((List)entry.getValue());
         }

         Collections.reverse(this._filterPathMappings);
         this._wildFilterNameMappings = (List)this._filterNameMappings.get("*");
         if (this._wildFilterNameMappings != null) {
            Collections.reverse(this._wildFilterNameMappings);
         }

         PathMappings<MappedServlet> pm = new PathMappings();
         HashMap<String, List<ServletMapping>> sms = new HashMap();

         for(ServletMapping servletMapping : this._servletMappings) {
            String[] pathSpecs = servletMapping.getPathSpecs();
            if (pathSpecs != null) {
               for(String pathSpec : pathSpecs) {
                  List<ServletMapping> mappings = (List)sms.computeIfAbsent(pathSpec, (k) -> new ArrayList());
                  mappings.add(servletMapping);
               }
            }
         }

         for(String pathSpec : sms.keySet()) {
            List<ServletMapping> mappings = (List)sms.get(pathSpec);
            ServletMapping finalMapping = null;

            for(ServletMapping mapping : mappings) {
               ServletHolder servletHolder = this.getServlet(mapping.getServletName());
               if (servletHolder == null) {
                  throw new IllegalStateException("No such servlet: " + mapping.getServletName());
               }

               if (servletHolder.isEnabled()) {
                  if (finalMapping == null) {
                     finalMapping = mapping;
                  } else if (finalMapping.isFromDefaultDescriptor()) {
                     finalMapping = mapping;
                  } else if (this.isAllowDuplicateMappings()) {
                     LOG.warn("Multiple servlets map to path {}: {} and {}, choosing {}", new Object[]{pathSpec, finalMapping.getServletName(), mapping.getServletName(), mapping});
                     finalMapping = mapping;
                  } else if (!mapping.isFromDefaultDescriptor()) {
                     ServletHolder finalMappedServlet = this.getServlet(finalMapping.getServletName());
                     throw new IllegalStateException("Multiple servlets map to path " + pathSpec + ": " + finalMappedServlet.getName() + "[mapped:" + String.valueOf(finalMapping.getSource()) + "]," + mapping.getServletName() + "[mapped:" + String.valueOf(mapping.getSource()) + "]");
                  }
               }
            }

            if (finalMapping == null) {
               throw new IllegalStateException("No acceptable servlet mappings for " + pathSpec);
            }

            if (LOG.isDebugEnabled()) {
               LOG.debug("Path={}[{}] mapped to servlet={}[{}]", new Object[]{pathSpec, finalMapping.getSource(), finalMapping.getServletName(), this.getServlet(finalMapping.getServletName()).getSource()});
            }

            PathSpec ps = this.asPathSpec(pathSpec);
            MappedServlet mappedServlet = new MappedServlet(ps, this.getServlet(finalMapping.getServletName()));
            pm.put((PathSpec)ps, mappedServlet);
         }

         this._servletPathMap = pm;
         int i = this._chainCache.length;

         while(i-- > 0) {
            if (this._chainCache[i] != null) {
               this._chainCache[i].clear();
            }
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("filterNameMap={} pathFilters={} servletFilterMap={} servletPathMap={} servletNameMap={}", new Object[]{this._filterNameMap, this._filterPathMappings, this._filterNameMappings, this._servletPathMap, this._servletNameMap});
         }
      }

   }

   protected void notFound(Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Not Found {}", request.getRequestURI());
      }

      if (this.getHandler() != null) {
         this.nextHandle(baseRequest.getPathInContext(), baseRequest, request, response);
      }

   }

   protected boolean containsFilterHolder(FilterHolder holder) {
      try (AutoLock ignored = this.lock()) {
         return this._filters.contains(holder);
      }
   }

   protected boolean containsServletHolder(ServletHolder holder) {
      try (AutoLock ignored = this.lock()) {
         return this._servlets.contains(holder);
      }
   }

   public void setFilterChainsCached(boolean filterChainsCached) {
      this._filterChainsCached = filterChainsCached;
   }

   public void setFilterMappings(FilterMapping[] filterMappings) {
      try (AutoLock ignored = this.lock()) {
         List<FilterMapping> mappings = filterMappings == null ? Collections.emptyList() : Arrays.asList(filterMappings);
         this.updateAndSet(this._filterMappings, mappings);
         if (this.isRunning()) {
            this.updateMappings();
         }

         this.invalidateChainsCache();
      }

   }

   public void setFilters(FilterHolder[] holders) {
      try (AutoLock ignored = this.lock()) {
         List<FilterHolder> filters = holders == null ? Collections.emptyList() : Arrays.asList(holders);
         this.initializeHolders(filters);
         this.updateAndSet(this._filters, filters);
         this.updateNameMappings();
         this.invalidateChainsCache();
      }

   }

   public void setServletMappings(ServletMapping[] servletMappings) {
      List<ServletMapping> mappings = servletMappings == null ? Collections.emptyList() : Arrays.asList(servletMappings);
      this.updateAndSet(this._servletMappings, mappings);
      if (this.isRunning()) {
         this.updateMappings();
      }

      this.invalidateChainsCache();
   }

   public void setServlets(ServletHolder[] holders) {
      try (AutoLock ignored = this.lock()) {
         List<ServletHolder> servlets = holders == null ? Collections.emptyList() : Arrays.asList(holders);
         this.initializeHolders(servlets);
         this.updateAndSet(this._servlets, servlets);
         this.updateNameMappings();
         this.invalidateChainsCache();
      }

   }

   public int getMaxFilterChainsCacheSize() {
      return this._maxFilterChainsCacheSize;
   }

   public void setMaxFilterChainsCacheSize(int maxFilterChainsCacheSize) {
      this._maxFilterChainsCacheSize = maxFilterChainsCacheSize;
   }

   void destroyServlet(Servlet servlet) {
      if (this._contextHandler != null) {
         this._contextHandler.destroyServlet(servlet);
      }

   }

   void destroyFilter(Filter filter) {
      if (this._contextHandler != null) {
         this._contextHandler.destroyFilter(filter);
      }

   }

   void destroyListener(EventListener listener) {
      if (this._contextHandler != null) {
         this._contextHandler.destroyListener(listener);
      }

   }

   public static class MappedServlet {
      private final PathSpec _pathSpec;
      private final ServletHolder _servletHolder;
      private final ServletPathMapping _servletPathMapping;

      MappedServlet(PathSpec pathSpec, ServletHolder servletHolder) {
         this._pathSpec = pathSpec;
         this._servletHolder = servletHolder;
         if (pathSpec instanceof ServletPathSpec) {
            switch (pathSpec.getGroup()) {
               case EXACT:
                  this._servletPathMapping = new ServletPathMapping(this._pathSpec, this._servletHolder.getName(), this._pathSpec.getDeclaration());
                  break;
               case ROOT:
                  this._servletPathMapping = new ServletPathMapping(this._pathSpec, this._servletHolder.getName(), "/");
                  break;
               default:
                  this._servletPathMapping = null;
            }
         } else {
            this._servletPathMapping = null;
         }

      }

      public PathSpec getPathSpec() {
         return this._pathSpec;
      }

      public ServletHolder getServletHolder() {
         return this._servletHolder;
      }

      public ServletPathMapping getServletPathMapping(String pathInContext, MatchedPath matchedPath) {
         if (this._servletPathMapping != null) {
            return this._servletPathMapping;
         } else {
            return this._pathSpec != null ? new ServletPathMapping(this._pathSpec, this._servletHolder.getName(), pathInContext, matchedPath) : null;
         }
      }

      public String toString() {
         return String.format("MappedServlet%x{%s->%s}", this.hashCode(), this._pathSpec == null ? null : this._pathSpec.getDeclaration(), this._servletHolder);
      }
   }

   public static class Default404Servlet extends HttpServlet {
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.sendError(404);
      }
   }

   static class Chain implements FilterChain {
      private final FilterHolder _filterHolder;
      private final FilterChain _filterChain;

      Chain(FilterHolder filter, FilterChain chain) {
         this._filterHolder = filter;
         this._filterChain = chain;
      }

      public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
         this._filterHolder.doFilter(request, response, this._filterChain);
      }

      public String toString() {
         return String.format("Chain@%x(%s)->%s", this.hashCode(), this._filterHolder, this._filterChain);
      }
   }

   static class ChainEnd implements FilterChain {
      private final ServletHolder _servletHolder;

      ChainEnd(ServletHolder holder) {
         Objects.requireNonNull(holder);
         this._servletHolder = holder;
      }

      public ServletHolder getServletHolder() {
         return this._servletHolder;
      }

      public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
         Request baseRequest = Request.getBaseRequest(request);
         Objects.requireNonNull(baseRequest);
         this._servletHolder.handle(baseRequest, request, response);
      }

      public String toString() {
         return String.format("ChainEnd@%x(%s)", this.hashCode(), this._servletHolder);
      }
   }
}
