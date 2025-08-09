package io.vertx.core.impl.launcher.commands;

import io.vertx.core.AsyncResult;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.cli.annotations.Description;
import io.vertx.core.cli.annotations.Option;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.spi.launcher.DefaultCommand;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class ClasspathHandler extends DefaultCommand {
   protected static final String PATH_SEP = System.getProperty("path.separator");
   protected final Logger log = LoggerFactory.getLogger(this.getClass());
   protected List classpath;
   protected Object manager;
   private ClassLoader classloader;

   @Option(
      shortName = "cp",
      longName = "classpath",
      argName = "classpath"
   )
   @Description("Provides an extra classpath to be used for the verticle deployment.")
   public void setClasspath(String classpath) {
      if (classpath != null && !classpath.isEmpty()) {
         this.classpath = Arrays.asList(classpath.split(PATH_SEP));
         this.classloader = this.createClassloader();
      } else {
         this.classloader = ClasspathHandler.class.getClassLoader();
         this.classpath = Collections.emptyList();
      }

   }

   protected synchronized ClassLoader createClassloader() {
      URL[] urls = (URL[])this.classpath.stream().map((path) -> {
         File file = new File(path);

         try {
            return file.toURI().toURL();
         } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
         }
      }).toArray((x$0) -> new URL[x$0]);
      return new URLClassLoader(urls, this.getClass().getClassLoader());
   }

   protected synchronized Object newInstance() {
      try {
         this.classloader = this.classpath != null && !this.classpath.isEmpty() ? this.createClassloader() : ClasspathHandler.class.getClassLoader();
         Class<?> clazz = this.classloader.loadClass("io.vertx.core.impl.launcher.commands.VertxIsolatedDeployer");
         return clazz.getDeclaredConstructor().newInstance();
      } catch (Exception e) {
         this.log.error("Failed to load or instantiate the isolated deployer", e);
         throw new IllegalStateException(e);
      }
   }

   protected synchronized Vertx create(VertxBuilder builder) {
      ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

      try {
         Thread.currentThread().setContextClassLoader(this.classloader != null ? this.classloader : this.getClass().getClassLoader());
         Vertx var3 = builder.vertx();
         return var3;
      } catch (Exception e) {
         this.log.error("Failed to create the vert.x instance", e);
      } finally {
         Thread.currentThread().setContextClassLoader(originalClassLoader);
      }

      return null;
   }

   protected synchronized void create(VertxBuilder builder, Handler resultHandler) {
      ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

      try {
         Thread.currentThread().setContextClassLoader(this.classloader != null ? this.classloader : this.getClass().getClassLoader());
         builder.clusteredVertx(resultHandler);
      } catch (Exception e) {
         this.log.error("Failed to create the vert.x instance", e);
      } finally {
         Thread.currentThread().setContextClassLoader(originalClassLoader);
      }

   }

   public synchronized void deploy(String verticle, Vertx vertx, DeploymentOptions options, Handler completionHandler) {
      if (this.manager == null) {
         this.manager = this.newInstance();
      }

      ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();

      try {
         Thread.currentThread().setContextClassLoader(this.classloader);
         Method method = this.manager.getClass().getMethod("deploy", String.class, Vertx.class, DeploymentOptions.class, Handler.class);
         if (this.executionContext.get("Default-Verticle-Factory") != null && verticle.indexOf(58) == -1) {
            verticle = this.executionContext.get("Default-Verticle-Factory") + ":" + verticle;
         }

         method.invoke(this.manager, verticle, vertx, options, completionHandler);
      } catch (InvocationTargetException e) {
         this.log.error("Failed to deploy verticle " + verticle, e.getCause());
      } catch (Exception e) {
         this.log.error("Failed to deploy verticle " + verticle, e);
      } finally {
         Thread.currentThread().setContextClassLoader(originalClassLoader);
      }

   }
}
