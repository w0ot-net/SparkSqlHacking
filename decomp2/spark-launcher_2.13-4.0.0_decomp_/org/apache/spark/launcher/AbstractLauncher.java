package org.apache.spark.launcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractLauncher {
   final SparkSubmitCommandBuilder builder = new SparkSubmitCommandBuilder();

   AbstractLauncher() {
   }

   public AbstractLauncher setPropertiesFile(String path) {
      CommandBuilderUtils.checkNotNull(path, "path");
      this.builder.setPropertiesFile(path);
      return this.self();
   }

   public AbstractLauncher setConf(String key, String value) {
      CommandBuilderUtils.checkNotNull(key, "key");
      CommandBuilderUtils.checkNotNull(value, "value");
      CommandBuilderUtils.checkArgument(key.startsWith("spark."), "'key' must start with 'spark.'");
      this.builder.conf.put(key, value);
      return this.self();
   }

   public AbstractLauncher setAppName(String appName) {
      CommandBuilderUtils.checkNotNull(appName, "appName");
      this.builder.appName = appName;
      return this.self();
   }

   public AbstractLauncher setMaster(String master) {
      CommandBuilderUtils.checkNotNull(master, "master");
      this.builder.master = master;
      return this.self();
   }

   public AbstractLauncher setRemote(String remote) {
      CommandBuilderUtils.checkNotNull(remote, "remote");
      this.builder.remote = remote;
      return this.self();
   }

   public AbstractLauncher setDeployMode(String mode) {
      CommandBuilderUtils.checkNotNull(mode, "mode");
      this.builder.deployMode = mode;
      return this.self();
   }

   public AbstractLauncher setAppResource(String resource) {
      CommandBuilderUtils.checkNotNull(resource, "resource");
      this.builder.appResource = resource;
      return this.self();
   }

   public AbstractLauncher setMainClass(String mainClass) {
      CommandBuilderUtils.checkNotNull(mainClass, "mainClass");
      this.builder.mainClass = mainClass;
      return this.self();
   }

   public AbstractLauncher addSparkArg(String arg) {
      SparkSubmitOptionParser validator = new ArgumentValidator(false);
      validator.parse(Arrays.asList(arg));
      this.builder.userArgs.add(arg);
      return this.self();
   }

   public AbstractLauncher addSparkArg(String name, String value) {
      SparkSubmitOptionParser validator = new ArgumentValidator(true);
      Objects.requireNonNull(validator);
      if ("--master".equals(name)) {
         this.setMaster(value);
      } else {
         Objects.requireNonNull(validator);
         if ("--remote".equals(name)) {
            this.setRemote(value);
         } else {
            Objects.requireNonNull(validator);
            if ("--properties-file".equals(name)) {
               this.setPropertiesFile(value);
            } else {
               Objects.requireNonNull(validator);
               if ("--conf".equals(name)) {
                  String[] vals = value.split("=", 2);
                  this.setConf(vals[0], vals[1]);
               } else {
                  Objects.requireNonNull(validator);
                  if ("--class".equals(name)) {
                     this.setMainClass(value);
                  } else {
                     Objects.requireNonNull(validator);
                     if ("--jars".equals(name)) {
                        this.builder.jars.clear();

                        for(String jar : value.split(",")) {
                           this.addJar(jar);
                        }
                     } else {
                        Objects.requireNonNull(validator);
                        if ("--files".equals(name)) {
                           this.builder.files.clear();

                           for(String file : value.split(",")) {
                              this.addFile(file);
                           }
                        } else {
                           Objects.requireNonNull(validator);
                           if ("--py-files".equals(name)) {
                              this.builder.pyFiles.clear();

                              for(String file : value.split(",")) {
                                 this.addPyFile(file);
                              }
                           } else {
                              validator.parse(Arrays.asList(name, value));
                              this.builder.userArgs.add(name);
                              this.builder.userArgs.add(value);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return this.self();
   }

   public AbstractLauncher addAppArgs(String... args) {
      for(String arg : args) {
         CommandBuilderUtils.checkNotNull(arg, "arg");
         this.builder.appArgs.add(arg);
      }

      return this.self();
   }

   public AbstractLauncher addJar(String jar) {
      CommandBuilderUtils.checkNotNull(jar, "jar");
      this.builder.jars.add(jar);
      return this.self();
   }

   public AbstractLauncher addFile(String file) {
      CommandBuilderUtils.checkNotNull(file, "file");
      this.builder.files.add(file);
      return this.self();
   }

   public AbstractLauncher addPyFile(String file) {
      CommandBuilderUtils.checkNotNull(file, "file");
      this.builder.pyFiles.add(file);
      return this.self();
   }

   public AbstractLauncher setVerbose(boolean verbose) {
      this.builder.verbose = verbose;
      return this.self();
   }

   public abstract SparkAppHandle startApplication(SparkAppHandle.Listener... var1) throws IOException;

   abstract AbstractLauncher self();

   private static class ArgumentValidator extends SparkSubmitOptionParser {
      private final boolean hasValue;

      ArgumentValidator(boolean hasValue) {
         this.hasValue = hasValue;
      }

      protected boolean handle(String opt, String value) {
         if (value == null && this.hasValue) {
            throw new IllegalArgumentException(String.format("'%s' expects a value.", opt));
         } else {
            return true;
         }
      }

      protected boolean handleUnknown(String opt) {
         return true;
      }

      protected void handleExtraArgs(List extra) {
      }
   }
}
