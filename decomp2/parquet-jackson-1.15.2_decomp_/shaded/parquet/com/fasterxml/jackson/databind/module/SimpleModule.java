package shaded.parquet.com.fasterxml.jackson.databind.module;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import shaded.parquet.com.fasterxml.jackson.core.Version;
import shaded.parquet.com.fasterxml.jackson.databind.JsonDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.JsonSerializer;
import shaded.parquet.com.fasterxml.jackson.databind.KeyDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.Module;
import shaded.parquet.com.fasterxml.jackson.databind.PropertyNamingStrategy;
import shaded.parquet.com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import shaded.parquet.com.fasterxml.jackson.databind.deser.ValueInstantiator;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.NamedType;
import shaded.parquet.com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

public class SimpleModule extends Module implements Serializable {
   private static final long serialVersionUID = 1L;
   private static final AtomicInteger MODULE_ID_SEQ = new AtomicInteger(1);
   protected final String _name;
   protected final Version _version;
   protected final boolean _hasExplicitName;
   protected SimpleSerializers _serializers;
   protected SimpleDeserializers _deserializers;
   protected SimpleSerializers _keySerializers;
   protected SimpleKeyDeserializers _keyDeserializers;
   protected SimpleAbstractTypeResolver _abstractTypes;
   protected SimpleValueInstantiators _valueInstantiators;
   protected BeanDeserializerModifier _deserializerModifier;
   protected BeanSerializerModifier _serializerModifier;
   protected HashMap _mixins;
   protected LinkedHashSet _subtypes;
   protected PropertyNamingStrategy _namingStrategy;

   public SimpleModule() {
      this._serializers = null;
      this._deserializers = null;
      this._keySerializers = null;
      this._keyDeserializers = null;
      this._abstractTypes = null;
      this._valueInstantiators = null;
      this._deserializerModifier = null;
      this._serializerModifier = null;
      this._mixins = null;
      this._subtypes = null;
      this._namingStrategy = null;
      this._name = this.getClass() == SimpleModule.class ? "SimpleModule-" + MODULE_ID_SEQ.getAndIncrement() : this.getClass().getName();
      this._version = Version.unknownVersion();
      this._hasExplicitName = false;
   }

   public SimpleModule(String name) {
      this(name, Version.unknownVersion());
   }

   public SimpleModule(Version version) {
      this(version.getArtifactId(), version);
   }

   public SimpleModule(String name, Version version) {
      this._serializers = null;
      this._deserializers = null;
      this._keySerializers = null;
      this._keyDeserializers = null;
      this._abstractTypes = null;
      this._valueInstantiators = null;
      this._deserializerModifier = null;
      this._serializerModifier = null;
      this._mixins = null;
      this._subtypes = null;
      this._namingStrategy = null;
      this._name = name;
      this._version = version;
      this._hasExplicitName = true;
   }

   public SimpleModule(String name, Version version, Map deserializers) {
      this(name, version, deserializers, (List)null);
   }

   public SimpleModule(String name, Version version, List serializers) {
      this(name, version, (Map)null, serializers);
   }

   public SimpleModule(String name, Version version, Map deserializers, List serializers) {
      this._serializers = null;
      this._deserializers = null;
      this._keySerializers = null;
      this._keyDeserializers = null;
      this._abstractTypes = null;
      this._valueInstantiators = null;
      this._deserializerModifier = null;
      this._serializerModifier = null;
      this._mixins = null;
      this._subtypes = null;
      this._namingStrategy = null;
      this._name = name;
      this._hasExplicitName = true;
      this._version = version;
      if (deserializers != null) {
         this._deserializers = new SimpleDeserializers(deserializers);
      }

      if (serializers != null) {
         this._serializers = new SimpleSerializers(serializers);
      }

   }

   public Object getTypeId() {
      if (this._hasExplicitName) {
         return this._name;
      } else {
         return this.getClass() == SimpleModule.class ? this._name : super.getTypeId();
      }
   }

   public void setSerializers(SimpleSerializers s) {
      this._serializers = s;
   }

   public void setDeserializers(SimpleDeserializers d) {
      this._deserializers = d;
   }

   public void setKeySerializers(SimpleSerializers ks) {
      this._keySerializers = ks;
   }

   public void setKeyDeserializers(SimpleKeyDeserializers kd) {
      this._keyDeserializers = kd;
   }

   public void setAbstractTypes(SimpleAbstractTypeResolver atr) {
      this._abstractTypes = atr;
   }

   public void setValueInstantiators(SimpleValueInstantiators svi) {
      this._valueInstantiators = svi;
   }

   public SimpleModule setDeserializerModifier(BeanDeserializerModifier mod) {
      this._deserializerModifier = mod;
      return this;
   }

   public SimpleModule setSerializerModifier(BeanSerializerModifier mod) {
      this._serializerModifier = mod;
      return this;
   }

   protected SimpleModule setNamingStrategy(PropertyNamingStrategy naming) {
      this._namingStrategy = naming;
      return this;
   }

   public SimpleModule addSerializer(JsonSerializer ser) {
      this._checkNotNull(ser, "serializer");
      if (this._serializers == null) {
         this._serializers = new SimpleSerializers();
      }

      this._serializers.addSerializer(ser);
      return this;
   }

   public SimpleModule addSerializer(Class type, JsonSerializer ser) {
      this._checkNotNull(type, "type to register serializer for");
      this._checkNotNull(ser, "serializer");
      if (this._serializers == null) {
         this._serializers = new SimpleSerializers();
      }

      this._serializers.addSerializer(type, ser);
      return this;
   }

   public SimpleModule addKeySerializer(Class type, JsonSerializer ser) {
      this._checkNotNull(type, "type to register key serializer for");
      this._checkNotNull(ser, "key serializer");
      if (this._keySerializers == null) {
         this._keySerializers = new SimpleSerializers();
      }

      this._keySerializers.addSerializer(type, ser);
      return this;
   }

   public SimpleModule addDeserializer(Class type, JsonDeserializer deser) {
      this._checkNotNull(type, "type to register deserializer for");
      this._checkNotNull(deser, "deserializer");
      if (this._deserializers == null) {
         this._deserializers = new SimpleDeserializers();
      }

      this._deserializers.addDeserializer(type, deser);
      return this;
   }

   public SimpleModule addKeyDeserializer(Class type, KeyDeserializer deser) {
      this._checkNotNull(type, "type to register key deserializer for");
      this._checkNotNull(deser, "key deserializer");
      if (this._keyDeserializers == null) {
         this._keyDeserializers = new SimpleKeyDeserializers();
      }

      this._keyDeserializers.addDeserializer(type, deser);
      return this;
   }

   public SimpleModule addAbstractTypeMapping(Class superType, Class subType) {
      this._checkNotNull(superType, "abstract type to map");
      this._checkNotNull(subType, "concrete type to map to");
      if (this._abstractTypes == null) {
         this._abstractTypes = new SimpleAbstractTypeResolver();
      }

      this._abstractTypes = this._abstractTypes.addMapping(superType, subType);
      return this;
   }

   public SimpleModule registerSubtypes(Class... subtypes) {
      if (this._subtypes == null) {
         this._subtypes = new LinkedHashSet();
      }

      for(Class subtype : subtypes) {
         this._checkNotNull(subtype, "subtype to register");
         this._subtypes.add(new NamedType(subtype));
      }

      return this;
   }

   public SimpleModule registerSubtypes(NamedType... subtypes) {
      if (this._subtypes == null) {
         this._subtypes = new LinkedHashSet();
      }

      for(NamedType subtype : subtypes) {
         this._checkNotNull(subtype, "subtype to register");
         this._subtypes.add(subtype);
      }

      return this;
   }

   public SimpleModule registerSubtypes(Collection subtypes) {
      if (this._subtypes == null) {
         this._subtypes = new LinkedHashSet();
      }

      for(Class subtype : subtypes) {
         this._checkNotNull(subtype, "subtype to register");
         this._subtypes.add(new NamedType(subtype));
      }

      return this;
   }

   public SimpleModule addValueInstantiator(Class beanType, ValueInstantiator inst) {
      this._checkNotNull(beanType, "class to register value instantiator for");
      this._checkNotNull(inst, "value instantiator");
      if (this._valueInstantiators == null) {
         this._valueInstantiators = new SimpleValueInstantiators();
      }

      this._valueInstantiators = this._valueInstantiators.addValueInstantiator(beanType, inst);
      return this;
   }

   public SimpleModule setMixInAnnotation(Class targetType, Class mixinClass) {
      this._checkNotNull(targetType, "target type");
      this._checkNotNull(mixinClass, "mixin class");
      if (this._mixins == null) {
         this._mixins = new HashMap();
      }

      this._mixins.put(targetType, mixinClass);
      return this;
   }

   public String getModuleName() {
      return this._name;
   }

   public void setupModule(Module.SetupContext context) {
      if (this._serializers != null) {
         context.addSerializers(this._serializers);
      }

      if (this._deserializers != null) {
         context.addDeserializers(this._deserializers);
      }

      if (this._keySerializers != null) {
         context.addKeySerializers(this._keySerializers);
      }

      if (this._keyDeserializers != null) {
         context.addKeyDeserializers(this._keyDeserializers);
      }

      if (this._abstractTypes != null) {
         context.addAbstractTypeResolver(this._abstractTypes);
      }

      if (this._valueInstantiators != null) {
         context.addValueInstantiators(this._valueInstantiators);
      }

      if (this._deserializerModifier != null) {
         context.addBeanDeserializerModifier(this._deserializerModifier);
      }

      if (this._serializerModifier != null) {
         context.addBeanSerializerModifier(this._serializerModifier);
      }

      if (this._subtypes != null && this._subtypes.size() > 0) {
         context.registerSubtypes((NamedType[])this._subtypes.toArray(new NamedType[this._subtypes.size()]));
      }

      if (this._namingStrategy != null) {
         context.setNamingStrategy(this._namingStrategy);
      }

      if (this._mixins != null) {
         for(Map.Entry entry : this._mixins.entrySet()) {
            context.setMixInAnnotations((Class)entry.getKey(), (Class)entry.getValue());
         }
      }

   }

   public Version version() {
      return this._version;
   }

   protected void _checkNotNull(Object thingy, String type) {
      if (thingy == null) {
         throw new IllegalArgumentException(String.format("Cannot pass `null` as %s", type));
      }
   }
}
