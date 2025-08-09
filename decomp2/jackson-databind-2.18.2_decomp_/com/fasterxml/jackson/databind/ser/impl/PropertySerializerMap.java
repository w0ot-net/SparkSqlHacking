package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Arrays;

public abstract class PropertySerializerMap {
   protected final boolean _resetWhenFull;

   protected PropertySerializerMap(boolean resetWhenFull) {
      this._resetWhenFull = resetWhenFull;
   }

   protected PropertySerializerMap(PropertySerializerMap base) {
      this._resetWhenFull = base._resetWhenFull;
   }

   public abstract JsonSerializer serializerFor(Class var1);

   public final SerializerAndMapResult findAndAddPrimarySerializer(Class type, SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findPrimaryPropertySerializer(type, property);
      return new SerializerAndMapResult(serializer, this.newWith(type, serializer));
   }

   public final SerializerAndMapResult findAndAddPrimarySerializer(JavaType type, SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findPrimaryPropertySerializer(type, property);
      return new SerializerAndMapResult(serializer, this.newWith(type.getRawClass(), serializer));
   }

   public final SerializerAndMapResult findAndAddSecondarySerializer(Class type, SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findContentValueSerializer(type, property);
      return new SerializerAndMapResult(serializer, this.newWith(type, serializer));
   }

   public final SerializerAndMapResult findAndAddSecondarySerializer(JavaType type, SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findContentValueSerializer(type, property);
      return new SerializerAndMapResult(serializer, this.newWith(type.getRawClass(), serializer));
   }

   public final SerializerAndMapResult findAndAddRootValueSerializer(Class type, SerializerProvider provider) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findTypedValueSerializer((Class)type, false, (BeanProperty)null);
      return new SerializerAndMapResult(serializer, this.newWith(type, serializer));
   }

   public final SerializerAndMapResult findAndAddRootValueSerializer(JavaType type, SerializerProvider provider) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findTypedValueSerializer((JavaType)type, false, (BeanProperty)null);
      return new SerializerAndMapResult(serializer, this.newWith(type.getRawClass(), serializer));
   }

   public final SerializerAndMapResult findAndAddKeySerializer(Class type, SerializerProvider provider, BeanProperty property) throws JsonMappingException {
      JsonSerializer<Object> serializer = provider.findKeySerializer(type, property);
      return new SerializerAndMapResult(serializer, this.newWith(type, serializer));
   }

   public final SerializerAndMapResult addSerializer(Class type, JsonSerializer serializer) {
      return new SerializerAndMapResult(serializer, this.newWith(type, serializer));
   }

   public final SerializerAndMapResult addSerializer(JavaType type, JsonSerializer serializer) {
      return new SerializerAndMapResult(serializer, this.newWith(type.getRawClass(), serializer));
   }

   public abstract PropertySerializerMap newWith(Class var1, JsonSerializer var2);

   public static PropertySerializerMap emptyForProperties() {
      return PropertySerializerMap.Empty.FOR_PROPERTIES;
   }

   public static PropertySerializerMap emptyForRootValues() {
      return PropertySerializerMap.Empty.FOR_ROOT_VALUES;
   }

   public static final class SerializerAndMapResult {
      public final JsonSerializer serializer;
      public final PropertySerializerMap map;

      public SerializerAndMapResult(JsonSerializer serializer, PropertySerializerMap map) {
         this.serializer = serializer;
         this.map = map;
      }
   }

   private static final class TypeAndSerializer {
      public final Class type;
      public final JsonSerializer serializer;

      public TypeAndSerializer(Class type, JsonSerializer serializer) {
         this.type = type;
         this.serializer = serializer;
      }
   }

   private static final class Empty extends PropertySerializerMap {
      public static final Empty FOR_PROPERTIES = new Empty(false);
      public static final Empty FOR_ROOT_VALUES = new Empty(true);

      protected Empty(boolean resetWhenFull) {
         super(resetWhenFull);
      }

      public JsonSerializer serializerFor(Class type) {
         return null;
      }

      public PropertySerializerMap newWith(Class type, JsonSerializer serializer) {
         return new Single(this, type, serializer);
      }
   }

   private static final class Single extends PropertySerializerMap {
      private final Class _type;
      private final JsonSerializer _serializer;

      public Single(PropertySerializerMap base, Class type, JsonSerializer serializer) {
         super(base);
         this._type = type;
         this._serializer = serializer;
      }

      public JsonSerializer serializerFor(Class type) {
         return type == this._type ? this._serializer : null;
      }

      public PropertySerializerMap newWith(Class type, JsonSerializer serializer) {
         return new Double(this, this._type, this._serializer, type, serializer);
      }
   }

   private static final class Double extends PropertySerializerMap {
      private final Class _type1;
      private final Class _type2;
      private final JsonSerializer _serializer1;
      private final JsonSerializer _serializer2;

      public Double(PropertySerializerMap base, Class type1, JsonSerializer serializer1, Class type2, JsonSerializer serializer2) {
         super(base);
         this._type1 = type1;
         this._serializer1 = serializer1;
         this._type2 = type2;
         this._serializer2 = serializer2;
      }

      public JsonSerializer serializerFor(Class type) {
         if (type == this._type1) {
            return this._serializer1;
         } else {
            return type == this._type2 ? this._serializer2 : null;
         }
      }

      public PropertySerializerMap newWith(Class type, JsonSerializer serializer) {
         TypeAndSerializer[] ts = new TypeAndSerializer[3];
         ts[0] = new TypeAndSerializer(this._type1, this._serializer1);
         ts[1] = new TypeAndSerializer(this._type2, this._serializer2);
         ts[2] = new TypeAndSerializer(type, serializer);
         return new Multi(this, ts);
      }
   }

   private static final class Multi extends PropertySerializerMap {
      private static final int MAX_ENTRIES = 8;
      private final TypeAndSerializer[] _entries;

      public Multi(PropertySerializerMap base, TypeAndSerializer[] entries) {
         super(base);
         this._entries = entries;
      }

      public JsonSerializer serializerFor(Class type) {
         TypeAndSerializer entry = this._entries[0];
         if (entry.type == type) {
            return entry.serializer;
         } else {
            entry = this._entries[1];
            if (entry.type == type) {
               return entry.serializer;
            } else {
               entry = this._entries[2];
               if (entry.type == type) {
                  return entry.serializer;
               } else {
                  switch (this._entries.length) {
                     case 8:
                        entry = this._entries[7];
                        if (entry.type == type) {
                           return entry.serializer;
                        }
                     case 7:
                        entry = this._entries[6];
                        if (entry.type == type) {
                           return entry.serializer;
                        }
                     case 6:
                        entry = this._entries[5];
                        if (entry.type == type) {
                           return entry.serializer;
                        }
                     case 5:
                        entry = this._entries[4];
                        if (entry.type == type) {
                           return entry.serializer;
                        }
                     case 4:
                        entry = this._entries[3];
                        if (entry.type == type) {
                           return entry.serializer;
                        }
                     default:
                        return null;
                  }
               }
            }
         }
      }

      public PropertySerializerMap newWith(Class type, JsonSerializer serializer) {
         int len = this._entries.length;
         if (len == 8) {
            return (PropertySerializerMap)(this._resetWhenFull ? new Single(this, type, serializer) : this);
         } else {
            TypeAndSerializer[] entries = (TypeAndSerializer[])Arrays.copyOf(this._entries, len + 1);
            entries[len] = new TypeAndSerializer(type, serializer);
            return new Multi(this, entries);
         }
      }
   }
}
