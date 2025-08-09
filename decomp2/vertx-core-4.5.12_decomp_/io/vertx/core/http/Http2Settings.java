package io.vertx.core.http;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.json.annotations.JsonGen;
import io.vertx.core.impl.Arguments;
import io.vertx.core.json.JsonObject;
import java.util.HashMap;
import java.util.Map;

@DataObject
@JsonGen(
   publicConverter = false
)
public class Http2Settings {
   public static final long DEFAULT_HEADER_TABLE_SIZE = 4096L;
   public static final boolean DEFAULT_ENABLE_PUSH = true;
   public static final long DEFAULT_MAX_CONCURRENT_STREAMS = 4294967295L;
   public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
   public static final int DEFAULT_MAX_FRAME_SIZE = 16384;
   public static final int DEFAULT_MAX_HEADER_LIST_SIZE = 8192;
   public static final Map DEFAULT_EXTRA_SETTINGS = null;
   private long headerTableSize;
   private boolean pushEnabled;
   private long maxConcurrentStreams;
   private int initialWindowSize;
   private int maxFrameSize;
   private long maxHeaderListSize;
   private Map extraSettings;

   public Http2Settings() {
      this.headerTableSize = 4096L;
      this.pushEnabled = true;
      this.maxConcurrentStreams = 4294967295L;
      this.initialWindowSize = 65535;
      this.maxFrameSize = 16384;
      this.maxHeaderListSize = 8192L;
      this.extraSettings = DEFAULT_EXTRA_SETTINGS;
   }

   public Http2Settings(JsonObject json) {
      this();
      Http2SettingsConverter.fromJson(json, this);
   }

   public Http2Settings(Http2Settings other) {
      this.headerTableSize = other.headerTableSize;
      this.pushEnabled = other.pushEnabled;
      this.maxConcurrentStreams = other.maxConcurrentStreams;
      this.initialWindowSize = other.initialWindowSize;
      this.maxFrameSize = other.maxFrameSize;
      this.maxHeaderListSize = other.maxHeaderListSize;
      this.extraSettings = other.extraSettings != null ? new HashMap(other.extraSettings) : null;
   }

   public long getHeaderTableSize() {
      return this.headerTableSize;
   }

   public Http2Settings setHeaderTableSize(long headerTableSize) {
      Arguments.require(headerTableSize >= 0L, "headerTableSize must be >= 0");
      Arguments.require(headerTableSize <= 4294967295L, "headerTableSize must be <= 4294967295");
      this.headerTableSize = headerTableSize;
      return this;
   }

   public boolean isPushEnabled() {
      return this.pushEnabled;
   }

   public Http2Settings setPushEnabled(boolean pushEnabled) {
      this.pushEnabled = pushEnabled;
      return this;
   }

   public long getMaxConcurrentStreams() {
      return this.maxConcurrentStreams;
   }

   public Http2Settings setMaxConcurrentStreams(long maxConcurrentStreams) {
      Arguments.require(maxConcurrentStreams >= 0L, "maxConcurrentStreams must be >= 0");
      Arguments.require(maxConcurrentStreams <= 4294967295L, "maxConcurrentStreams must be < 4294967295");
      this.maxConcurrentStreams = maxConcurrentStreams;
      return this;
   }

   public int getInitialWindowSize() {
      return this.initialWindowSize;
   }

   public Http2Settings setInitialWindowSize(int initialWindowSize) {
      Arguments.require(initialWindowSize >= 0, "initialWindowSize must be >= 0");
      this.initialWindowSize = initialWindowSize;
      return this;
   }

   public int getMaxFrameSize() {
      return this.maxFrameSize;
   }

   public Http2Settings setMaxFrameSize(int maxFrameSize) {
      Arguments.require(maxFrameSize >= 16384, "maxFrameSize must be >= 16384");
      Arguments.require(maxFrameSize <= 16777215, "maxFrameSize must be <= 16777215");
      this.maxFrameSize = maxFrameSize;
      return this;
   }

   public long getMaxHeaderListSize() {
      return this.maxHeaderListSize;
   }

   public Http2Settings setMaxHeaderListSize(long maxHeaderListSize) {
      Arguments.require(maxHeaderListSize >= 0L, "maxHeaderListSize must be >= 0");
      Arguments.require(maxHeaderListSize >= 0L, "maxHeaderListSize must be >= 0");
      this.maxHeaderListSize = maxHeaderListSize;
      return this;
   }

   @GenIgnore
   public Map getExtraSettings() {
      return this.extraSettings;
   }

   @GenIgnore
   public Http2Settings setExtraSettings(Map settings) {
      this.extraSettings = settings;
      return this;
   }

   public Long get(int id) {
      switch (id) {
         case 1:
            return this.headerTableSize;
         case 2:
            return this.pushEnabled ? 1L : 0L;
         case 3:
            return this.maxConcurrentStreams;
         case 4:
            return (long)this.initialWindowSize;
         case 5:
            return (long)this.maxFrameSize;
         case 6:
            return this.maxHeaderListSize;
         default:
            return this.extraSettings != null ? (Long)this.extraSettings.get(id) : null;
      }
   }

   public Http2Settings set(int id, long value) {
      Arguments.require(id >= 0 && id <= 65535, "Setting id must me an unsigned 16-bit value");
      Arguments.require(value >= 0L && value <= 4294967295L, "Setting value must me an unsigned 32-bit value");
      switch (id) {
         case 1:
            this.setHeaderTableSize(value);
            break;
         case 2:
            Arguments.require(value == 0L || value == 1L, "enablePush must be 0 or 1");
            this.setPushEnabled(value == 1L);
            break;
         case 3:
            this.setMaxConcurrentStreams(value);
            break;
         case 4:
            this.setInitialWindowSize((int)value);
            break;
         case 5:
            this.setMaxFrameSize((int)value);
            break;
         case 6:
            Arguments.require(value <= 2147483647L, "maxHeaderListSize must be <= 2147483647");
            this.setMaxHeaderListSize((long)((int)value));
            break;
         default:
            if (this.extraSettings == null) {
               this.extraSettings = new HashMap();
            }

            this.extraSettings.put(id, value);
      }

      return this;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         Http2Settings that = (Http2Settings)o;
         if (this.headerTableSize != that.headerTableSize) {
            return false;
         } else if (this.pushEnabled != that.pushEnabled) {
            return false;
         } else if (this.maxConcurrentStreams != that.maxConcurrentStreams) {
            return false;
         } else if (this.initialWindowSize != that.initialWindowSize) {
            return false;
         } else if (this.maxFrameSize != that.maxFrameSize) {
            return false;
         } else {
            return this.maxHeaderListSize == that.maxHeaderListSize;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = (int)(this.headerTableSize ^ this.headerTableSize >>> 32);
      result = 31 * result + (this.pushEnabled ? 1 : 0);
      result = 31 * result + (int)(this.maxConcurrentStreams ^ this.maxConcurrentStreams >>> 32);
      result = 31 * result + this.initialWindowSize;
      result = 31 * result + this.maxFrameSize;
      result = 31 * result + (int)(this.maxHeaderListSize ^ this.maxHeaderListSize >>> 32);
      return result;
   }

   public String toString() {
      return this.toJson().encode();
   }

   public JsonObject toJson() {
      JsonObject json = new JsonObject();
      Http2SettingsConverter.toJson(this, json);
      return json;
   }
}
