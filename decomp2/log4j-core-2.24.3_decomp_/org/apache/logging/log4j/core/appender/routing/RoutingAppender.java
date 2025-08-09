package org.apache.logging.log4j.core.appender.routing;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.script.Bindings;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LifeCycle2;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.AppenderControl;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.script.AbstractScript;
import org.apache.logging.log4j.core.script.ScriptManager;
import org.apache.logging.log4j.core.script.ScriptRef;
import org.apache.logging.log4j.core.util.Booleans;

@Plugin(
   name = "Routing",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class RoutingAppender extends AbstractAppender {
   public static final String STATIC_VARIABLES_KEY = "staticVariables";
   private static final String DEFAULT_KEY = "ROUTING_APPENDER_DEFAULT";
   private final Routes routes;
   private Route defaultRoute;
   private final Configuration configuration;
   private final ConcurrentMap createdAppenders;
   private final Map createdAppendersUnmodifiableView;
   private final ConcurrentMap referencedAppenders;
   private final RewritePolicy rewritePolicy;
   private final PurgePolicy purgePolicy;
   private final AbstractScript defaultRouteScript;
   private final ConcurrentMap scriptStaticVariables;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   private RoutingAppender(final String name, final Filter filter, final boolean ignoreExceptions, final Routes routes, final RewritePolicy rewritePolicy, final Configuration configuration, final PurgePolicy purgePolicy, final AbstractScript defaultRouteScript, final Property[] properties) {
      super(name, filter, (Layout)null, ignoreExceptions, properties);
      this.createdAppenders = new ConcurrentHashMap();
      this.createdAppendersUnmodifiableView = Collections.unmodifiableMap(this.createdAppenders);
      this.referencedAppenders = new ConcurrentHashMap();
      this.scriptStaticVariables = new ConcurrentHashMap();
      this.routes = routes;
      this.configuration = configuration;
      this.rewritePolicy = rewritePolicy;
      this.purgePolicy = purgePolicy;
      if (this.purgePolicy != null) {
         this.purgePolicy.initialize(this);
      }

      this.defaultRouteScript = defaultRouteScript;
      Route defRoute = null;

      for(Route route : routes.getRoutes()) {
         if (route.getKey() == null) {
            if (defRoute == null) {
               defRoute = route;
            } else {
               this.error("Multiple default routes. Route " + route.toString() + " will be ignored");
            }
         }
      }

      this.defaultRoute = defRoute;
   }

   public void start() {
      if (this.defaultRouteScript != null) {
         if (this.configuration == null) {
            this.error("No Configuration defined for RoutingAppender; required for Script element.");
         } else {
            ScriptManager scriptManager = this.configuration.getScriptManager();
            Bindings bindings = scriptManager.createBindings(this.defaultRouteScript);
            bindings.put("staticVariables", this.scriptStaticVariables);
            Object object = scriptManager.execute(this.defaultRouteScript.getName(), bindings);
            Route route = this.routes.getRoute(Objects.toString(object, (String)null));
            if (route != null) {
               this.defaultRoute = route;
            }
         }
      }

      for(Route route : this.routes.getRoutes()) {
         if (route.getAppenderRef() != null) {
            Appender appender = this.configuration.getAppender(route.getAppenderRef());
            if (appender != null) {
               String key = route == this.defaultRoute ? "ROUTING_APPENDER_DEFAULT" : route.getKey();
               this.referencedAppenders.put(key, new ReferencedRouteAppenderControl(appender));
            } else {
               this.error("Appender " + route.getAppenderRef() + " cannot be located. Route ignored");
            }
         }
      }

      super.start();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();
      super.stop(timeout, timeUnit, false);

      for(Map.Entry entry : this.createdAppenders.entrySet()) {
         Appender appender = ((CreatedRouteAppenderControl)entry.getValue()).getAppender();
         if (appender instanceof LifeCycle2) {
            ((LifeCycle2)appender).stop(timeout, timeUnit);
         } else {
            appender.stop();
         }
      }

      this.setStopped();
      return true;
   }

   public void append(LogEvent event) {
      if (this.rewritePolicy != null) {
         event = this.rewritePolicy.rewrite(event);
      }

      String pattern = this.routes.getPattern(event, this.scriptStaticVariables);
      String key = pattern != null ? this.configuration.getStrSubstitutor().replace(event, pattern) : (this.defaultRoute.getKey() != null ? this.defaultRoute.getKey() : "ROUTING_APPENDER_DEFAULT");
      RouteAppenderControl control = this.getControl(key, event);
      if (control != null) {
         try {
            control.callAppender(event);
         } finally {
            control.release();
         }
      }

      this.updatePurgePolicy(key, event);
   }

   private void updatePurgePolicy(final String key, final LogEvent event) {
      if (this.purgePolicy != null && !this.referencedAppenders.containsKey(key)) {
         this.purgePolicy.update(key, event);
      }

   }

   private synchronized RouteAppenderControl getControl(final String key, final LogEvent event) {
      RouteAppenderControl control = this.getAppender(key);
      if (control != null) {
         control.checkout();
         return control;
      } else {
         Route route = null;

         for(Route r : this.routes.getRoutes()) {
            if (r.getAppenderRef() == null && key.equals(r.getKey())) {
               route = r;
               break;
            }
         }

         if (route == null) {
            route = this.defaultRoute;
            control = this.getAppender("ROUTING_APPENDER_DEFAULT");
            if (control != null) {
               control.checkout();
               return control;
            }
         }

         if (route != null) {
            Appender app = this.createAppender(route, event);
            if (app == null) {
               return null;
            }

            CreatedRouteAppenderControl created = new CreatedRouteAppenderControl(app);
            control = created;
            this.createdAppenders.put(key, created);
         }

         if (control != null) {
            control.checkout();
         }

         return control;
      }
   }

   private RouteAppenderControl getAppender(final String key) {
      RouteAppenderControl result = (RouteAppenderControl)this.referencedAppenders.get(key);
      return result == null ? (RouteAppenderControl)this.createdAppenders.get(key) : result;
   }

   private Appender createAppender(final Route route, final LogEvent event) {
      Node routeNode = route.getNode();

      for(Node node : routeNode.getChildren()) {
         if (node.getType().getElementName().equals("appender")) {
            Node appNode = new Node(node);
            this.configuration.createConfiguration(appNode, event);
            if (appNode.getObject() instanceof Appender) {
               Appender app = (Appender)appNode.getObject();
               app.start();
               return app;
            }

            this.error("Unable to create Appender of type " + node.getName());
            return null;
         }
      }

      this.error("No Appender was configured for route " + route.getKey());
      return null;
   }

   public Map getAppenders() {
      return this.createdAppendersUnmodifiableView;
   }

   public void deleteAppender(final String key) {
      LOGGER.debug("Deleting route with {} key ", key);
      CreatedRouteAppenderControl control = (CreatedRouteAppenderControl)this.createdAppenders.remove(key);
      if (null != control) {
         LOGGER.debug("Stopping route with {} key", key);
         synchronized(this) {
            control.pendingDeletion = true;
         }

         control.tryStopAppender();
      } else if (this.referencedAppenders.containsKey(key)) {
         LOGGER.debug("Route {} using an appender reference may not be removed because the appender may be used outside of the RoutingAppender", key);
      } else {
         LOGGER.debug("Route with {} key already deleted", key);
      }

   }

   /** @deprecated */
   @Deprecated
   public static RoutingAppender createAppender(final String name, final String ignore, final Routes routes, final Configuration config, final RewritePolicy rewritePolicy, final PurgePolicy purgePolicy, final Filter filter) {
      boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
      if (name == null) {
         LOGGER.error("No name provided for RoutingAppender");
         return null;
      } else if (routes == null) {
         LOGGER.error("No routes defined for RoutingAppender");
         return null;
      } else {
         return new RoutingAppender(name, filter, ignoreExceptions, routes, rewritePolicy, config, purgePolicy, (AbstractScript)null, (Property[])null);
      }
   }

   public Route getDefaultRoute() {
      return this.defaultRoute;
   }

   public AbstractScript getDefaultRouteScript() {
      return this.defaultRouteScript;
   }

   public PurgePolicy getPurgePolicy() {
      return this.purgePolicy;
   }

   public RewritePolicy getRewritePolicy() {
      return this.rewritePolicy;
   }

   public Routes getRoutes() {
      return this.routes;
   }

   public Configuration getConfiguration() {
      return this.configuration;
   }

   public ConcurrentMap getScriptStaticVariables() {
      return this.scriptStaticVariables;
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginElement("Script")
      private AbstractScript defaultRouteScript;
      @PluginElement("Routes")
      private Routes routes;
      @PluginElement("RewritePolicy")
      private RewritePolicy rewritePolicy;
      @PluginElement("PurgePolicy")
      private PurgePolicy purgePolicy;

      public RoutingAppender build() {
         String name = this.getName();
         if (name == null) {
            RoutingAppender.LOGGER.error("No name defined for this RoutingAppender");
            return null;
         } else if (this.routes == null) {
            RoutingAppender.LOGGER.error("No routes defined for RoutingAppender {}", name);
            return null;
         } else {
            if (this.defaultRouteScript != null) {
               if (this.getConfiguration().getScriptManager() == null) {
                  RoutingAppender.LOGGER.error("Script support is not enabled");
                  return null;
               }

               if (!(this.defaultRouteScript instanceof ScriptRef) && !this.getConfiguration().getScriptManager().addScript(this.defaultRouteScript)) {
                  return null;
               }
            }

            return new RoutingAppender(name, this.getFilter(), this.isIgnoreExceptions(), this.routes, this.rewritePolicy, this.getConfiguration(), this.purgePolicy, this.defaultRouteScript, this.getPropertyArray());
         }
      }

      public Routes getRoutes() {
         return this.routes;
      }

      public AbstractScript getDefaultRouteScript() {
         return this.defaultRouteScript;
      }

      public RewritePolicy getRewritePolicy() {
         return this.rewritePolicy;
      }

      public PurgePolicy getPurgePolicy() {
         return this.purgePolicy;
      }

      public Builder withRoutes(final Routes routes) {
         this.routes = routes;
         return (Builder)this.asBuilder();
      }

      public Builder withDefaultRouteScript(final AbstractScript defaultRouteScript) {
         this.defaultRouteScript = defaultRouteScript;
         return (Builder)this.asBuilder();
      }

      public Builder withRewritePolicy(final RewritePolicy rewritePolicy) {
         this.rewritePolicy = rewritePolicy;
         return (Builder)this.asBuilder();
      }

      public void withPurgePolicy(final PurgePolicy purgePolicy) {
         this.purgePolicy = purgePolicy;
      }
   }

   private abstract static class RouteAppenderControl extends AppenderControl {
      RouteAppenderControl(final Appender appender) {
         super(appender, (Level)null, (Filter)null);
      }

      abstract void checkout();

      abstract void release();
   }

   private static final class CreatedRouteAppenderControl extends RouteAppenderControl {
      private volatile boolean pendingDeletion;
      private final AtomicInteger depth = new AtomicInteger();

      CreatedRouteAppenderControl(final Appender appender) {
         super(appender);
      }

      void checkout() {
         if (this.pendingDeletion) {
            LOGGER.warn("CreatedRouteAppenderControl.checkout invoked on a RouteAppenderControl that is pending deletion");
         }

         this.depth.incrementAndGet();
      }

      void release() {
         this.depth.decrementAndGet();
         this.tryStopAppender();
      }

      void tryStopAppender() {
         if (this.pendingDeletion && this.depth.compareAndSet(0, -100000)) {
            Appender appender = this.getAppender();
            LOGGER.debug("Stopping appender {}", appender);
            appender.stop();
         }

      }
   }

   private static final class ReferencedRouteAppenderControl extends RouteAppenderControl {
      ReferencedRouteAppenderControl(final Appender appender) {
         super(appender);
      }

      void checkout() {
      }

      void release() {
      }
   }
}
