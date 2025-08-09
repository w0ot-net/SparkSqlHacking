package shaded.parquet.com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonFormat;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonInclude;
import shaded.parquet.com.fasterxml.jackson.annotation.JsonSetter;
import shaded.parquet.com.fasterxml.jackson.databind.introspect.VisibilityChecker;

public class ConfigOverrides implements Serializable {
   private static final long serialVersionUID = 1L;
   protected Map _overrides;
   protected JsonInclude.Value _defaultInclusion;
   protected JsonSetter.Value _defaultSetterInfo;
   protected VisibilityChecker _visibilityChecker;
   protected Boolean _defaultMergeable;
   protected Boolean _defaultLeniency;

   public ConfigOverrides() {
      this((Map)null, JsonInclude.Value.empty(), JsonSetter.Value.empty(), VisibilityChecker.Std.defaultInstance(), (Boolean)null, (Boolean)null);
   }

   protected ConfigOverrides(Map overrides, JsonInclude.Value defIncl, JsonSetter.Value defSetter, VisibilityChecker defVisibility, Boolean defMergeable, Boolean defLeniency) {
      this._overrides = overrides;
      this._defaultInclusion = defIncl;
      this._defaultSetterInfo = defSetter;
      this._visibilityChecker = defVisibility;
      this._defaultMergeable = defMergeable;
      this._defaultLeniency = defLeniency;
   }

   /** @deprecated */
   @Deprecated
   protected ConfigOverrides(Map overrides, JsonInclude.Value defIncl, JsonSetter.Value defSetter, VisibilityChecker defVisibility, Boolean defMergeable) {
      this(overrides, defIncl, defSetter, defVisibility, defMergeable, (Boolean)null);
   }

   public ConfigOverrides copy() {
      Map<Class<?>, MutableConfigOverride> newOverrides;
      if (this._overrides == null) {
         newOverrides = null;
      } else {
         newOverrides = this._newMap();

         for(Map.Entry entry : this._overrides.entrySet()) {
            newOverrides.put(entry.getKey(), ((MutableConfigOverride)entry.getValue()).copy());
         }
      }

      return new ConfigOverrides(newOverrides, this._defaultInclusion, this._defaultSetterInfo, this._visibilityChecker, this._defaultMergeable, this._defaultLeniency);
   }

   public ConfigOverride findOverride(Class type) {
      return this._overrides == null ? null : (ConfigOverride)this._overrides.get(type);
   }

   public MutableConfigOverride findOrCreateOverride(Class type) {
      if (this._overrides == null) {
         this._overrides = this._newMap();
      }

      MutableConfigOverride override = (MutableConfigOverride)this._overrides.get(type);
      if (override == null) {
         override = new MutableConfigOverride();
         this._overrides.put(type, override);
      }

      return override;
   }

   public JsonFormat.Value findFormatDefaults(Class type) {
      if (this._overrides != null) {
         ConfigOverride override = (ConfigOverride)this._overrides.get(type);
         if (override != null) {
            JsonFormat.Value format = override.getFormat();
            if (format != null) {
               if (!format.hasLenient()) {
                  return format.withLenient(this._defaultLeniency);
               }

               return format;
            }
         }
      }

      return this._defaultLeniency == null ? JsonFormat.Value.empty() : JsonFormat.Value.forLeniency(this._defaultLeniency);
   }

   public JsonInclude.Value getDefaultInclusion() {
      return this._defaultInclusion;
   }

   public JsonSetter.Value getDefaultSetterInfo() {
      return this._defaultSetterInfo;
   }

   public Boolean getDefaultMergeable() {
      return this._defaultMergeable;
   }

   public Boolean getDefaultLeniency() {
      return this._defaultLeniency;
   }

   public VisibilityChecker getDefaultVisibility() {
      return this._visibilityChecker;
   }

   public void setDefaultInclusion(JsonInclude.Value v) {
      this._defaultInclusion = v;
   }

   public void setDefaultSetterInfo(JsonSetter.Value v) {
      this._defaultSetterInfo = v;
   }

   public void setDefaultMergeable(Boolean v) {
      this._defaultMergeable = v;
   }

   public void setDefaultLeniency(Boolean v) {
      this._defaultLeniency = v;
   }

   public void setDefaultVisibility(VisibilityChecker v) {
      this._visibilityChecker = v;
   }

   protected Map _newMap() {
      return new HashMap();
   }
}
