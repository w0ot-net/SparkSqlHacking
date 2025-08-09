package shaded.parquet.com.fasterxml.jackson.databind.deser.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationConfig;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.MapperFeature;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyName;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;

public final class PropertyBasedCreator {
   protected final int _propertyCount;
   protected final ValueInstantiator _valueInstantiator;
   protected final HashMap _propertyLookup;
   protected final SettableBeanProperty[] _allProperties;

   protected PropertyBasedCreator(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps, boolean caseInsensitive, boolean addAliases) {
      this._valueInstantiator = valueInstantiator;
      if (caseInsensitive) {
         this._propertyLookup = PropertyBasedCreator.CaseInsensitiveMap.construct(ctxt.getConfig().getLocale());
      } else {
         this._propertyLookup = new HashMap();
      }

      int len = creatorProps.length;
      this._propertyCount = len;
      this._allProperties = new SettableBeanProperty[len];
      if (addAliases) {
         DeserializationConfig config = ctxt.getConfig();

         for(SettableBeanProperty prop : creatorProps) {
            if (!prop.isIgnorable()) {
               List<PropertyName> aliases = prop.findAliases(config);
               if (!aliases.isEmpty()) {
                  for(PropertyName pn : aliases) {
                     this._propertyLookup.put(pn.getSimpleName(), prop);
                  }
               }
            }
         }
      }

      for(int i = 0; i < len; ++i) {
         SettableBeanProperty prop = creatorProps[i];
         this._allProperties[i] = prop;
         if (!prop.isIgnorable()) {
            this._propertyLookup.put(prop.getName(), prop);
         }
      }

   }

   public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps, BeanPropertyMap allProperties) throws JsonMappingException {
      int len = srcCreatorProps.length;
      SettableBeanProperty[] creatorProps = new SettableBeanProperty[len];

      for(int i = 0; i < len; ++i) {
         SettableBeanProperty prop = srcCreatorProps[i];
         if (!prop.hasValueDeserializer() && !prop.isInjectionOnly()) {
            prop = prop.withValueDeserializer(ctxt.findContextualValueDeserializer(prop.getType(), prop));
         }

         creatorProps[i] = prop;
      }

      return new PropertyBasedCreator(ctxt, valueInstantiator, creatorProps, allProperties.isCaseInsensitive(), true);
   }

   public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps, boolean caseInsensitive) throws JsonMappingException {
      int len = srcCreatorProps.length;
      SettableBeanProperty[] creatorProps = new SettableBeanProperty[len];

      for(int i = 0; i < len; ++i) {
         SettableBeanProperty prop = srcCreatorProps[i];
         if (!prop.hasValueDeserializer()) {
            prop = prop.withValueDeserializer(ctxt.findContextualValueDeserializer(prop.getType(), prop));
         }

         creatorProps[i] = prop;
      }

      return new PropertyBasedCreator(ctxt, valueInstantiator, creatorProps, caseInsensitive, false);
   }

   /** @deprecated */
   @Deprecated
   public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcCreatorProps) throws JsonMappingException {
      return construct(ctxt, valueInstantiator, srcCreatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
   }

   public Collection properties() {
      return this._propertyLookup.values();
   }

   public SettableBeanProperty findCreatorProperty(String name) {
      return (SettableBeanProperty)this._propertyLookup.get(name);
   }

   public SettableBeanProperty findCreatorProperty(int propertyIndex) {
      for(SettableBeanProperty prop : this._propertyLookup.values()) {
         if (prop.getPropertyIndex() == propertyIndex) {
            return prop;
         }
      }

      return null;
   }

   public PropertyValueBuffer startBuilding(JsonParser p, DeserializationContext ctxt, ObjectIdReader oir) {
      return new PropertyValueBuffer(p, ctxt, this._propertyCount, oir, (SettableAnyProperty)null);
   }

   public PropertyValueBuffer startBuildingWithAnySetter(JsonParser p, DeserializationContext ctxt, ObjectIdReader oir, SettableAnyProperty anySetter) {
      return new PropertyValueBuffer(p, ctxt, this._propertyCount, oir, anySetter);
   }

   public Object build(DeserializationContext ctxt, PropertyValueBuffer buffer) throws IOException {
      Object bean = this._valueInstantiator.createFromObjectWith(ctxt, this._allProperties, buffer);
      if (bean != null) {
         bean = buffer.handleIdValue(ctxt, bean);

         for(PropertyValue pv = buffer.buffered(); pv != null; pv = pv.next) {
            pv.assign(bean);
         }
      }

      return bean;
   }

   static class CaseInsensitiveMap extends HashMap {
      private static final long serialVersionUID = 1L;
      protected final Locale _locale;

      /** @deprecated */
      @Deprecated
      public CaseInsensitiveMap() {
         this(Locale.getDefault());
      }

      public CaseInsensitiveMap(Locale l) {
         this._locale = l;
      }

      public static CaseInsensitiveMap construct(Locale l) {
         return new CaseInsensitiveMap(l);
      }

      public SettableBeanProperty get(Object key0) {
         return (SettableBeanProperty)super.get(((String)key0).toLowerCase(this._locale));
      }

      public SettableBeanProperty put(String key, SettableBeanProperty value) {
         key = key.toLowerCase(this._locale);
         return (SettableBeanProperty)super.put(key, value);
      }
   }
}
