package io.vertx.core;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DataObject
@JsonGen(
   publicConverter = false
)
public class DeploymentOptions {
   public static final ThreadingModel DEFAULT_MODE;
   public static final boolean DEFAULT_WORKER = false;
   public static final boolean DEFAULT_HA = false;
   public static final int DEFAULT_INSTANCES = 1;
   private JsonObject config;
   private ThreadingModel threadingModel;
   private String isolationGroup;
   private boolean ha;
   private List extraClasspath;
   private int instances;
   private List isolatedClasses;
   private ClassLoader classLoader;
   private String workerPoolName;
   private int workerPoolSize;
   private long maxWorkerExecuteTime;
   private TimeUnit maxWorkerExecuteTimeUnit;

   public DeploymentOptions() {
      this.threadingModel = DEFAULT_MODE;
      this.config = null;
      this.isolationGroup = null;
      this.ha = false;
      this.instances = 1;
      this.workerPoolSize = 20;
      this.maxWorkerExecuteTime = VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME;
      this.maxWorkerExecuteTimeUnit = VertxOptions.DEFAULT_MAX_WORKER_EXECUTE_TIME_UNIT;
   }

   public DeploymentOptions(DeploymentOptions other) {
      this.config = other.getConfig() == null ? null : other.getConfig().copy();
      this.threadingModel = other.getThreadingModel();
      this.isolationGroup = other.getIsolationGroup();
      this.ha = other.isHa();
      this.extraClasspath = other.getExtraClasspath() == null ? null : new ArrayList(other.getExtraClasspath());
      this.instances = other.instances;
      this.isolatedClasses = other.getIsolatedClasses() == null ? null : new ArrayList(other.getIsolatedClasses());
      this.workerPoolName = other.workerPoolName;
      this.workerPoolSize = other.workerPoolSize;
      this.maxWorkerExecuteTime = other.maxWorkerExecuteTime;
      this.maxWorkerExecuteTimeUnit = other.maxWorkerExecuteTimeUnit;
   }

   public DeploymentOptions(JsonObject json) {
      this();
      DeploymentOptionsConverter.fromJson(json, this);
      this.isolationGroup = json.getString("isolationGroup");
      JsonArray arr = json.getJsonArray("extraClasspath");
      if (arr != null) {
         this.extraClasspath = arr.getList();
      }

      JsonArray arrIsolated = json.getJsonArray("isolatedClasses");
      if (arrIsolated != null) {
         this.isolatedClasses = arrIsolated.getList();
      }

   }

   public JsonObject getConfig() {
      return this.config;
   }

   public DeploymentOptions setConfig(JsonObject config) {
      this.config = config;
      return this;
   }

   public ThreadingModel getThreadingModel() {
      return this.threadingModel;
   }

   public DeploymentOptions setThreadingModel(ThreadingModel threadingModel) {
      this.threadingModel = threadingModel;
      return this;
   }

   /** @deprecated */
   @Deprecated
   public boolean isWorker() {
      return this.threadingModel == ThreadingModel.WORKER;
   }

   /** @deprecated */
   @Deprecated
   public DeploymentOptions setWorker(boolean worker) {
      this.threadingModel = worker ? ThreadingModel.WORKER : ThreadingModel.EVENT_LOOP;
      return this;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public String getIsolationGroup() {
      return this.isolationGroup;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public DeploymentOptions setIsolationGroup(String isolationGroup) {
      this.isolationGroup = isolationGroup;
      return this;
   }

   public boolean isHa() {
      return this.ha;
   }

   public DeploymentOptions setHa(boolean ha) {
      this.ha = ha;
      return this;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public List getExtraClasspath() {
      return this.extraClasspath;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public DeploymentOptions setExtraClasspath(List extraClasspath) {
      this.extraClasspath = extraClasspath;
      return this;
   }

   public int getInstances() {
      return this.instances;
   }

   public DeploymentOptions setInstances(int instances) {
      this.instances = instances;
      return this;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public List getIsolatedClasses() {
      return this.isolatedClasses;
   }

   /** @deprecated */
   @GenIgnore
   @Deprecated
   public DeploymentOptions setIsolatedClasses(List isolatedClasses) {
      this.isolatedClasses = isolatedClasses;
      return this;
   }

   public String getWorkerPoolName() {
      return this.workerPoolName;
   }

   public DeploymentOptions setWorkerPoolName(String workerPoolName) {
      this.workerPoolName = workerPoolName;
      return this;
   }

   public int getWorkerPoolSize() {
      return this.workerPoolSize;
   }

   public DeploymentOptions setWorkerPoolSize(int workerPoolSize) {
      if (workerPoolSize < 1) {
         throw new IllegalArgumentException("size must be > 0");
      } else {
         this.workerPoolSize = workerPoolSize;
         return this;
      }
   }

   public long getMaxWorkerExecuteTime() {
      return this.maxWorkerExecuteTime;
   }

   public DeploymentOptions setMaxWorkerExecuteTime(long maxWorkerExecuteTime) {
      if (maxWorkerExecuteTime < 1L) {
         throw new IllegalArgumentException("maxExecuteTime must be > 0");
      } else {
         this.maxWorkerExecuteTime = maxWorkerExecuteTime;
         return this;
      }
   }

   public TimeUnit getMaxWorkerExecuteTimeUnit() {
      return this.maxWorkerExecuteTimeUnit;
   }

   public DeploymentOptions setMaxWorkerExecuteTimeUnit(TimeUnit maxWorkerExecuteTimeUnit) {
      this.maxWorkerExecuteTimeUnit = maxWorkerExecuteTimeUnit;
      return this;
   }

   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   public DeploymentOptions setClassLoader(ClassLoader classLoader) {
      this.classLoader = classLoader;
      return this;
   }

   public void checkIsolationNotDefined() {
      if (this.getExtraClasspath() != null) {
         throw new IllegalArgumentException("Can't specify extraClasspath for already created verticle");
      } else if (this.getIsolationGroup() != null) {
         throw new IllegalArgumentException("Can't specify isolationGroup for already created verticle");
      } else if (this.getIsolatedClasses() != null) {
         throw new IllegalArgumentException("Can't specify isolatedClasses for already created verticle");
      }
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      if (this.extraClasspath != null) {
         json.put("extraClasspath", new JsonArray(this.extraClasspath));
      }

      if (this.isolatedClasses != null) {
         json.put("isolatedClasses", new JsonArray(this.isolatedClasses));
      }

      if (this.isolationGroup != null) {
         json.put("isolationGroup", this.isolationGroup);
      }

      DeploymentOptionsConverter.toJson(this, json);
      return json;
   }

   static {
      DEFAULT_MODE = ThreadingModel.EVENT_LOOP;
   }
}
