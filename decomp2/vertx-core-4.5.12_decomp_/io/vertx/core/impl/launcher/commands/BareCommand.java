package io.vertx.core.impl.launcher.commands;

import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.VertxException;
import io.vertx.core.VertxOptions;
import io.vertx.core.cli.annotations.DefaultValue;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Name;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.cli.annotations.Summary;
import io.vertx.core.eventbus.AddressHelper;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.impl.launcher.VertxLifecycleHooks;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.launcher.ExecutionContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Summary("Creates a bare instance of vert.x.")
@Description("This command launches a vert.x instance but do not deploy any verticles. It will receive a verticle if another node of the cluster dies.")
@Name("bare")
public class BareCommand extends ClasspathHandler {
   public static final String VERTX_OPTIONS_PROP_PREFIX = "vertx.options.";
   public static final String VERTX_EVENTBUS_PROP_PREFIX = "vertx.eventBus.options.";
   public static final String DEPLOYMENT_OPTIONS_PROP_PREFIX = "vertx.deployment.options.";
   public static final String METRICS_OPTIONS_PROP_PREFIX = "vertx.metrics.options.";
   protected Vertx vertx;
   protected int clusterPort;
   protected String clusterHost;
   protected int clusterPublicPort;
   protected String clusterPublicHost;
   protected int quorum;
   protected String haGroup;
   protected String vertxOptions;
   protected VertxOptions options;
   protected Runnable finalAction;
   private static final ThreadLocal configureFromSystemProperties = new ThreadLocal();

   @Option(
      longName = "quorum",
      argName = "q"
   )
   @Description("Used in conjunction with -ha this specifies the minimum number of nodes in the cluster for any HA deploymentIDs to be active. Defaults to 1.")
   @DefaultValue("-1")
   public void setQuorum(int quorum) {
      this.quorum = quorum;
   }

   @Option(
      longName = "hagroup",
      argName = "group"
   )
   @Description("used in conjunction with -ha this specifies the HA group this node will join. There can be multiple HA groups in a cluster. Nodes will only failover to other nodes in the same group. Defaults to '__DEFAULT__'.")
   @DefaultValue("__DEFAULT__")
   public void setHAGroup(String group) {
      this.haGroup = group;
   }

   @Option(
      longName = "cluster-port",
      argName = "port"
   )
   @Description("Port to use for cluster communication. Default is 0 which means choose a spare random port.")
   @DefaultValue("0")
   public void setClusterPort(int port) {
      this.clusterPort = port;
   }

   @Option(
      longName = "cluster-host",
      argName = "host"
   )
   @Description("host to bind to for cluster communication. If this is not specified vert.x will attempt to choose one from the available interfaces.")
   public void setClusterHost(String host) {
      this.clusterHost = host;
   }

   @Option(
      longName = "cluster-public-port",
      argName = "public-port"
   )
   @Description("Public port to use for cluster communication. Default is -1 which means same as cluster port.")
   @DefaultValue("-1")
   public void setClusterPublicPort(int port) {
      this.clusterPublicPort = port;
   }

   @Option(
      longName = "cluster-public-host",
      argName = "public-host"
   )
   @Description("Public host to bind to for cluster communication. If not specified, Vert.x will use the same as cluster host.")
   public void setClusterPublicHost(String host) {
      this.clusterPublicHost = host;
   }

   @Option(
      longName = "options",
      argName = "options"
   )
   @Description("Specifies the Vert.x options. It should reference either a JSON file which represents the options OR be a JSON string.")
   public void setVertxOptions(String vertxOptions) {
      if (vertxOptions != null) {
         this.vertxOptions = vertxOptions.trim().replaceAll("^\"|\"$", "").replaceAll("^'|'$", "");
      } else {
         this.vertxOptions = null;
      }

   }

   public boolean isClustered() {
      return true;
   }

   public boolean getHA() {
      return true;
   }

   public void run() {
      this.run((Runnable)null);
   }

   public void run(Runnable action) {
      this.finalAction = action;
      this.vertx = this.startVertx();
   }

   protected Vertx startVertx() {
      JsonObject optionsJson = this.getJsonFromFileOrString(this.vertxOptions, "options");
      EventBusOptions eventBusOptions = optionsJson == null ? this.getEventBusOptions() : this.getEventBusOptions(optionsJson.getJsonObject("eventBusOptions"));
      VertxBuilder builder = this.createVertxBuilder(optionsJson);
      this.options = builder.options();
      this.options.setEventBusOptions(eventBusOptions);
      this.beforeStartingVertx(this.options);
      configureFromSystemProperties.set(this.log);

      try {
         configureFromSystemProperties(this.options, "vertx.options.");
         if (this.options.getMetricsOptions() != null) {
            configureFromSystemProperties(this.options.getMetricsOptions(), "vertx.metrics.options.");
         }

         builder.init();
      } finally {
         configureFromSystemProperties.set((Object)null);
      }

      Vertx instance;
      if (this.isClustered()) {
         this.log.info("Starting clustering...");
         eventBusOptions = this.options.getEventBusOptions();
         if (!Objects.equals(eventBusOptions.getHost(), EventBusOptions.DEFAULT_CLUSTER_HOST)) {
            this.clusterHost = eventBusOptions.getHost();
         }

         if (eventBusOptions.getPort() != 0) {
            this.clusterPort = eventBusOptions.getPort();
         }

         if (!Objects.equals(eventBusOptions.getClusterPublicHost(), EventBusOptions.DEFAULT_CLUSTER_PUBLIC_HOST)) {
            this.clusterPublicHost = eventBusOptions.getClusterPublicHost();
         }

         if (eventBusOptions.getClusterPublicPort() != -1) {
            this.clusterPublicPort = eventBusOptions.getClusterPublicPort();
         }

         eventBusOptions.setHost(this.clusterHost).setPort(this.clusterPort).setClusterPublicHost(this.clusterPublicHost);
         if (this.clusterPublicPort != -1) {
            eventBusOptions.setClusterPublicPort(this.clusterPublicPort);
         }

         if (this.getHA()) {
            this.options.setHAEnabled(true);
            if (this.haGroup != null) {
               this.options.setHAGroup(this.haGroup);
            }

            if (this.quorum != -1) {
               this.options.setQuorumSize(this.quorum);
            }
         }

         CountDownLatch latch = new CountDownLatch(1);
         AtomicReference<AsyncResult<Vertx>> result = new AtomicReference();
         this.create(builder, (ar) -> {
            result.set(ar);
            latch.countDown();
         });

         try {
            if (!latch.await(2L, TimeUnit.MINUTES)) {
               this.log.error("Timed out in starting clustered Vert.x");
               return null;
            }
         } catch (InterruptedException var10) {
            this.log.error("Thread interrupted in startup");
            Thread.currentThread().interrupt();
            return null;
         }

         if (((AsyncResult)result.get()).failed()) {
            this.log.error("Failed to form cluster", ((AsyncResult)result.get()).cause());
            return null;
         }

         instance = (Vertx)((AsyncResult)result.get()).result();
      } else {
         instance = this.create(builder);
      }

      addShutdownHook(instance, this.log, this.finalAction);
      this.afterStartingVertx(instance);
      return instance;
   }

   protected JsonObject getJsonFromFileOrString(String jsonFileOrString, String argName) {
      JsonObject conf;
      if (jsonFileOrString != null) {
         try {
            Scanner scanner = (new Scanner(new File(jsonFileOrString), "UTF-8")).useDelimiter("\\A");
            Throwable var5 = null;

            Object var8;
            try {
               String sconf = scanner.next();

               try {
                  conf = new JsonObject(sconf);
                  return conf;
               } catch (DecodeException var22) {
                  this.log.error("Configuration file " + sconf + " does not contain a valid JSON object");
                  var8 = null;
               }
            } catch (Throwable var23) {
               var5 = var23;
               throw var23;
            } finally {
               if (scanner != null) {
                  if (var5 != null) {
                     try {
                        scanner.close();
                     } catch (Throwable var21) {
                        var5.addSuppressed(var21);
                     }
                  } else {
                     scanner.close();
                  }
               }

            }

            return (JsonObject)var8;
         } catch (FileNotFoundException var25) {
            try {
               conf = new JsonObject(jsonFileOrString);
            } catch (DecodeException e2) {
               this.log.error("The -" + argName + " argument does not point to an existing file or is not a valid JSON object", e2);
               return null;
            }
         }
      } else {
         conf = null;
      }

      return conf;
   }

   protected void afterStartingVertx(Vertx instance) {
      Object main = this.executionContext.main();
      if (main instanceof VertxLifecycleHooks) {
         ((VertxLifecycleHooks)main).afterStartingVertx(instance);
      }

   }

   protected void beforeStartingVertx(VertxOptions options) {
      Object main = this.executionContext.main();
      if (main instanceof VertxLifecycleHooks) {
         ((VertxLifecycleHooks)main).beforeStartingVertx(options);
      }

   }

   protected VertxBuilder createVertxBuilder(JsonObject config) {
      Object main = this.executionContext.main();
      if (main instanceof VertxLifecycleHooks) {
         return ((VertxLifecycleHooks)main).createVertxBuilder(config);
      } else {
         return config == null ? new VertxBuilder() : new VertxBuilder(config);
      }
   }

   protected EventBusOptions getEventBusOptions() {
      return this.getEventBusOptions((JsonObject)null);
   }

   protected EventBusOptions getEventBusOptions(JsonObject jsonObject) {
      EventBusOptions eventBusOptions = jsonObject == null ? new EventBusOptions() : new EventBusOptions(jsonObject);
      configureFromSystemProperties.set(this.log);

      try {
         configureFromSystemProperties(eventBusOptions, "vertx.eventBus.options.");
      } finally {
         configureFromSystemProperties.set((Object)null);
      }

      return eventBusOptions;
   }

   public static void configureFromSystemProperties(Object options, String prefix) {
      Logger log = (Logger)configureFromSystemProperties.get();
      if (log != null) {
         Properties props = System.getProperties();
         Enumeration<?> e = props.propertyNames();

         while(e.hasMoreElements()) {
            String propName = (String)e.nextElement();
            String propVal = props.getProperty(propName);
            if (propName.startsWith(prefix)) {
               String fieldName = propName.substring(prefix.length());
               Method setter = getSetter(fieldName, options.getClass());
               if (setter == null) {
                  log.warn("No such property to configure on options: " + options.getClass().getName() + "." + fieldName);
               } else {
                  Class<?> argType = setter.getParameterTypes()[0];

                  Object arg;
                  try {
                     if (argType.equals(String.class)) {
                        arg = propVal;
                     } else if (argType.equals(Integer.TYPE)) {
                        arg = Integer.valueOf(propVal);
                     } else if (argType.equals(Long.TYPE)) {
                        arg = Long.valueOf(propVal);
                     } else if (argType.equals(Boolean.TYPE)) {
                        arg = Boolean.valueOf(propVal);
                     } else {
                        if (!argType.isEnum()) {
                           log.warn("Invalid type for setter: " + argType);
                           continue;
                        }

                        arg = Enum.valueOf(argType, propVal);
                     }
                  } catch (IllegalArgumentException var13) {
                     log.warn("Invalid argtype:" + argType + " on options: " + options.getClass().getName() + "." + fieldName);
                     continue;
                  }

                  try {
                     setter.invoke(options, arg);
                  } catch (Exception ex) {
                     throw new VertxException("Failed to invoke setter: " + setter, ex);
                  }
               }
            }
         }

      }
   }

   private static Method getSetter(String fieldName, Class clazz) {
      Method[] meths = clazz.getDeclaredMethods();

      for(Method meth : meths) {
         if (("set" + fieldName).equalsIgnoreCase(meth.getName())) {
            return meth;
         }
      }

      meths = clazz.getMethods();

      for(Method meth : meths) {
         if (("set" + fieldName).equalsIgnoreCase(meth.getName())) {
            return meth;
         }
      }

      return null;
   }

   protected static void addShutdownHook(Vertx vertx, Logger log, Runnable action) {
      Runtime.getRuntime().addShutdownHook(new Thread(getTerminationRunnable(vertx, log, action)));
   }

   public static Runnable getTerminationRunnable(Vertx vertx, Logger log, Runnable action) {
      return () -> {
         CountDownLatch latch = new CountDownLatch(1);
         if (vertx != null) {
            vertx.close((ar) -> {
               if (!ar.succeeded()) {
                  log.error("Failure in stopping Vert.x", ar.cause());
               }

               latch.countDown();
            });

            try {
               if (!latch.await(2L, TimeUnit.MINUTES)) {
                  log.error("Timed out waiting to undeploy all");
               }

               if (action != null) {
                  action.run();
               }
            } catch (InterruptedException e) {
               throw new IllegalStateException(e);
            }
         }

      };
   }

   /** @deprecated */
   @Deprecated
   protected String getDefaultAddress() {
      return AddressHelper.defaultAddress();
   }

   public void setExecutionContext(ExecutionContext context) {
      this.executionContext = context;
   }

   public synchronized Vertx vertx() {
      return this.vertx;
   }
}
