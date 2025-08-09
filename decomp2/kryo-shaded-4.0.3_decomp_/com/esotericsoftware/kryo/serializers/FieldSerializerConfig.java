package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.minlog.Log;

public class FieldSerializerConfig implements Cloneable {
   private boolean fieldsCanBeNull = true;
   private boolean setFieldsAsAccessible = true;
   private boolean ignoreSyntheticFields = true;
   private boolean fixedFieldTypes;
   private boolean useAsm;
   private boolean copyTransient = true;
   private boolean serializeTransient = false;
   private boolean optimizedGenerics = false;
   private FieldSerializer.CachedFieldNameStrategy cachedFieldNameStrategy;

   public FieldSerializerConfig() {
      this.cachedFieldNameStrategy = FieldSerializer.CachedFieldNameStrategy.DEFAULT;
      this.useAsm = !FieldSerializer.unsafeAvailable;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "useAsm: " + this.useAsm);
      }

   }

   protected FieldSerializerConfig clone() {
      try {
         return (FieldSerializerConfig)super.clone();
      } catch (CloneNotSupportedException e) {
         throw new RuntimeException(e);
      }
   }

   public void setFieldsCanBeNull(boolean fieldsCanBeNull) {
      this.fieldsCanBeNull = fieldsCanBeNull;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setFieldsCanBeNull: " + fieldsCanBeNull);
      }

   }

   public void setFieldsAsAccessible(boolean setFieldsAsAccessible) {
      this.setFieldsAsAccessible = setFieldsAsAccessible;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setFieldsAsAccessible: " + setFieldsAsAccessible);
      }

   }

   public void setIgnoreSyntheticFields(boolean ignoreSyntheticFields) {
      this.ignoreSyntheticFields = ignoreSyntheticFields;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setIgnoreSyntheticFields: " + ignoreSyntheticFields);
      }

   }

   public void setFixedFieldTypes(boolean fixedFieldTypes) {
      this.fixedFieldTypes = fixedFieldTypes;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setFixedFieldTypes: " + fixedFieldTypes);
      }

   }

   public void setUseAsm(boolean setUseAsm) {
      this.useAsm = setUseAsm;
      if (!this.useAsm && !FieldSerializer.unsafeAvailable) {
         this.useAsm = true;
         if (Log.TRACE) {
            Log.trace("kryo.FieldSerializerConfig", "sun.misc.Unsafe is unavailable, using ASM.");
         }
      }

      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setUseAsm: " + setUseAsm);
      }

   }

   public void setOptimizedGenerics(boolean setOptimizedGenerics) {
      this.optimizedGenerics = setOptimizedGenerics;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "setOptimizedGenerics: " + setOptimizedGenerics);
      }

   }

   public void setCopyTransient(boolean setCopyTransient) {
      this.copyTransient = setCopyTransient;
   }

   public void setSerializeTransient(boolean serializeTransient) {
      this.serializeTransient = serializeTransient;
   }

   public boolean isFieldsCanBeNull() {
      return this.fieldsCanBeNull;
   }

   public boolean isSetFieldsAsAccessible() {
      return this.setFieldsAsAccessible;
   }

   public boolean isIgnoreSyntheticFields() {
      return this.ignoreSyntheticFields;
   }

   public boolean isFixedFieldTypes() {
      return this.fixedFieldTypes;
   }

   public boolean isUseAsm() {
      return this.useAsm;
   }

   public boolean isOptimizedGenerics() {
      return this.optimizedGenerics;
   }

   public boolean isCopyTransient() {
      return this.copyTransient;
   }

   public boolean isSerializeTransient() {
      return this.serializeTransient;
   }

   public FieldSerializer.CachedFieldNameStrategy getCachedFieldNameStrategy() {
      return this.cachedFieldNameStrategy;
   }

   public void setCachedFieldNameStrategy(FieldSerializer.CachedFieldNameStrategy cachedFieldNameStrategy) {
      this.cachedFieldNameStrategy = cachedFieldNameStrategy;
      if (Log.TRACE) {
         Log.trace("kryo.FieldSerializerConfig", "CachedFieldNameStrategy: " + cachedFieldNameStrategy);
      }

   }
}
