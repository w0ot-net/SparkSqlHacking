package io.vertx.core.file;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.json.JsonObject;

@DataObject
@JsonGen(
   publicConverter = false
)
public class OpenOptions {
   public static final String DEFAULT_PERMS = null;
   public static final boolean DEFAULT_READ = true;
   public static final boolean DEFAULT_WRITE = true;
   public static final boolean DEFAULT_CREATE = true;
   public static final boolean DEFAULT_CREATENEW = false;
   public static final boolean DEFAULT_DSYNC = false;
   public static final boolean DEFAULT_SYNC = false;
   public static final boolean DEFAULT_DELETEONCLOSE = false;
   public static final boolean DEFAULT_TRUNCATEEXISTING = false;
   public static final boolean DEFAULT_SPARSE = false;
   public static final boolean DEFAULT_APPEND = false;
   private String perms;
   private boolean read;
   private boolean write;
   private boolean create;
   private boolean createNew;
   private boolean dsync;
   private boolean sync;
   private boolean deleteOnClose;
   private boolean truncateExisting;
   private boolean sparse;
   private boolean append;

   public OpenOptions() {
      this.perms = DEFAULT_PERMS;
      this.read = true;
      this.write = true;
      this.create = true;
      this.createNew = false;
      this.dsync = false;
      this.sync = false;
      this.deleteOnClose = false;
      this.truncateExisting = false;
      this.sparse = false;
      this.append = false;
   }

   public OpenOptions(OpenOptions other) {
      this.perms = DEFAULT_PERMS;
      this.read = true;
      this.write = true;
      this.create = true;
      this.createNew = false;
      this.dsync = false;
      this.sync = false;
      this.deleteOnClose = false;
      this.truncateExisting = false;
      this.sparse = false;
      this.append = false;
      this.perms = other.perms;
      this.read = other.read;
      this.write = other.write;
      this.create = other.create;
      this.createNew = other.createNew;
      this.dsync = other.dsync;
      this.sync = other.sync;
      this.deleteOnClose = other.deleteOnClose;
      this.truncateExisting = other.truncateExisting;
      this.sparse = other.sparse;
      this.append = other.append;
   }

   public OpenOptions(JsonObject json) {
      this();
      OpenOptionsConverter.fromJson(json, this);
   }

   public String getPerms() {
      return this.perms;
   }

   public OpenOptions setPerms(String perms) {
      this.perms = perms;
      return this;
   }

   public boolean isRead() {
      return this.read;
   }

   public OpenOptions setRead(boolean read) {
      this.read = read;
      return this;
   }

   public boolean isWrite() {
      return this.write;
   }

   public OpenOptions setWrite(boolean write) {
      this.write = write;
      return this;
   }

   public boolean isCreate() {
      return this.create;
   }

   public OpenOptions setCreate(boolean create) {
      this.create = create;
      return this;
   }

   public boolean isCreateNew() {
      return this.createNew;
   }

   public OpenOptions setCreateNew(boolean createNew) {
      this.createNew = createNew;
      return this;
   }

   public boolean isDeleteOnClose() {
      return this.deleteOnClose;
   }

   public OpenOptions setDeleteOnClose(boolean deleteOnClose) {
      this.deleteOnClose = deleteOnClose;
      return this;
   }

   public boolean isTruncateExisting() {
      return this.truncateExisting;
   }

   public OpenOptions setTruncateExisting(boolean truncateExisting) {
      this.truncateExisting = truncateExisting;
      return this;
   }

   public boolean isSparse() {
      return this.sparse;
   }

   public OpenOptions setSparse(boolean sparse) {
      this.sparse = sparse;
      return this;
   }

   public boolean isSync() {
      return this.sync;
   }

   public OpenOptions setSync(boolean sync) {
      this.sync = sync;
      return this;
   }

   public boolean isDsync() {
      return this.dsync;
   }

   public OpenOptions setDsync(boolean dsync) {
      this.dsync = dsync;
      return this;
   }

   public boolean isAppend() {
      return this.append;
   }

   public OpenOptions setAppend(boolean append) {
      this.append = append;
      return this;
   }
}
