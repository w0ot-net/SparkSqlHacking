package io.vertx.core.file;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;
import java.io.File;

@DataObject
@JsonGen(
   publicConverter = false
)
public class FileSystemOptions {
   public static final boolean DEFAULT_FILE_CACHING_ENABLED = !Boolean.getBoolean("vertx.disableFileCaching");
   public static final boolean DEFAULT_CLASS_PATH_RESOLVING_ENABLED = !Boolean.getBoolean("vertx.disableFileCPResolving");
   private static final String TMPDIR = System.getProperty("java.io.tmpdir", ".");
   private static final String DEFAULT_CACHE_DIR_BASE = "vertx-cache";
   public static final String DEFAULT_FILE_CACHING_DIR;
   private boolean classPathResolvingEnabled;
   private boolean fileCachingEnabled;
   private String fileCacheDir;

   public FileSystemOptions() {
      this.classPathResolvingEnabled = DEFAULT_CLASS_PATH_RESOLVING_ENABLED;
      this.fileCachingEnabled = DEFAULT_FILE_CACHING_ENABLED;
      this.fileCacheDir = DEFAULT_FILE_CACHING_DIR;
   }

   public FileSystemOptions(FileSystemOptions other) {
      this.classPathResolvingEnabled = DEFAULT_CLASS_PATH_RESOLVING_ENABLED;
      this.fileCachingEnabled = DEFAULT_FILE_CACHING_ENABLED;
      this.fileCacheDir = DEFAULT_FILE_CACHING_DIR;
      this.classPathResolvingEnabled = other.isClassPathResolvingEnabled();
      this.fileCachingEnabled = other.isFileCachingEnabled();
      this.fileCacheDir = other.getFileCacheDir();
   }

   public FileSystemOptions(JsonObject json) {
      this();
      FileSystemOptionsConverter.fromJson(json, this);
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      FileSystemOptionsConverter.toJson(this, json);
      return json;
   }

   public boolean isClassPathResolvingEnabled() {
      return this.classPathResolvingEnabled;
   }

   public FileSystemOptions setClassPathResolvingEnabled(boolean classPathResolvingEnabled) {
      this.classPathResolvingEnabled = classPathResolvingEnabled;
      return this;
   }

   public boolean isFileCachingEnabled() {
      return this.fileCachingEnabled;
   }

   public FileSystemOptions setFileCachingEnabled(boolean fileCachingEnabled) {
      this.fileCachingEnabled = fileCachingEnabled;
      return this;
   }

   public String getFileCacheDir() {
      return this.fileCacheDir;
   }

   public FileSystemOptions setFileCacheDir(String fileCacheDir) {
      this.fileCacheDir = fileCacheDir;
      return this;
   }

   public String toString() {
      return "FileSystemOptions{classPathResolvingEnabled=" + this.classPathResolvingEnabled + ", fileCachingEnabled=" + this.fileCachingEnabled + ", fileCacheDir=" + this.fileCacheDir + '}';
   }

   static {
      DEFAULT_FILE_CACHING_DIR = System.getProperty("vertx.cacheDirBase", TMPDIR + File.separator + "vertx-cache");
   }
}
