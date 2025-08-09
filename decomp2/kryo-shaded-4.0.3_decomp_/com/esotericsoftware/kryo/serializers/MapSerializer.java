package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class MapSerializer extends Serializer {
   private Class keyClass;
   private Class valueClass;
   private Serializer keySerializer;
   private Serializer valueSerializer;
   private boolean keysCanBeNull = true;
   private boolean valuesCanBeNull = true;
   private Class keyGenericType;
   private Class valueGenericType;

   public void setKeysCanBeNull(boolean keysCanBeNull) {
      this.keysCanBeNull = keysCanBeNull;
   }

   public void setKeyClass(Class keyClass, Serializer keySerializer) {
      this.keyClass = keyClass;
      this.keySerializer = keySerializer;
   }

   public void setValueClass(Class valueClass, Serializer valueSerializer) {
      this.valueClass = valueClass;
      this.valueSerializer = valueSerializer;
   }

   public void setValuesCanBeNull(boolean valuesCanBeNull) {
      this.valuesCanBeNull = valuesCanBeNull;
   }

   public void setGenerics(Kryo kryo, Class[] generics) {
      this.keyGenericType = null;
      this.valueGenericType = null;
      if (generics != null && generics.length > 0) {
         if (generics[0] != null && kryo.isFinal(generics[0])) {
            this.keyGenericType = generics[0];
         }

         if (generics.length > 1 && generics[1] != null && kryo.isFinal(generics[1])) {
            this.valueGenericType = generics[1];
         }
      }

   }

   public void write(Kryo kryo, Output output, Map map) {
      int length = map.size();
      output.writeInt(length, true);
      Serializer keySerializer = this.keySerializer;
      if (this.keyGenericType != null) {
         if (keySerializer == null) {
            keySerializer = kryo.getSerializer(this.keyGenericType);
         }

         this.keyGenericType = null;
      }

      Serializer valueSerializer = this.valueSerializer;
      if (this.valueGenericType != null) {
         if (valueSerializer == null) {
            valueSerializer = kryo.getSerializer(this.valueGenericType);
         }

         this.valueGenericType = null;
      }

      for(Map.Entry entry : map.entrySet()) {
         if (keySerializer != null) {
            if (this.keysCanBeNull) {
               kryo.writeObjectOrNull(output, entry.getKey(), keySerializer);
            } else {
               kryo.writeObject(output, entry.getKey(), keySerializer);
            }
         } else {
            kryo.writeClassAndObject(output, entry.getKey());
         }

         if (valueSerializer != null) {
            if (this.valuesCanBeNull) {
               kryo.writeObjectOrNull(output, entry.getValue(), valueSerializer);
            } else {
               kryo.writeObject(output, entry.getValue(), valueSerializer);
            }
         } else {
            kryo.writeClassAndObject(output, entry.getValue());
         }
      }

   }

   protected Map create(Kryo kryo, Input input, Class type) {
      return (Map)kryo.newInstance(type);
   }

   public Map read(Kryo kryo, Input input, Class type) {
      Map map = this.create(kryo, input, type);
      int length = input.readInt(true);
      Class keyClass = this.keyClass;
      Class valueClass = this.valueClass;
      Serializer keySerializer = this.keySerializer;
      if (this.keyGenericType != null) {
         keyClass = this.keyGenericType;
         if (keySerializer == null) {
            keySerializer = kryo.getSerializer(keyClass);
         }

         this.keyGenericType = null;
      }

      Serializer valueSerializer = this.valueSerializer;
      if (this.valueGenericType != null) {
         valueClass = this.valueGenericType;
         if (valueSerializer == null) {
            valueSerializer = kryo.getSerializer(valueClass);
         }

         this.valueGenericType = null;
      }

      kryo.reference(map);

      for(int i = 0; i < length; ++i) {
         Object key;
         if (keySerializer != null) {
            if (this.keysCanBeNull) {
               key = kryo.readObjectOrNull(input, keyClass, keySerializer);
            } else {
               key = kryo.readObject(input, keyClass, keySerializer);
            }
         } else {
            key = kryo.readClassAndObject(input);
         }

         Object value;
         if (valueSerializer != null) {
            if (this.valuesCanBeNull) {
               value = kryo.readObjectOrNull(input, valueClass, valueSerializer);
            } else {
               value = kryo.readObject(input, valueClass, valueSerializer);
            }
         } else {
            value = kryo.readClassAndObject(input);
         }

         map.put(key, value);
      }

      return map;
   }

   protected Map createCopy(Kryo kryo, Map original) {
      return (Map)kryo.newInstance(original.getClass());
   }

   public Map copy(Kryo kryo, Map original) {
      Map copy = this.createCopy(kryo, original);

      for(Map.Entry entry : original.entrySet()) {
         copy.put(kryo.copy(entry.getKey()), kryo.copy(entry.getValue()));
      }

      return copy;
   }

   @Retention(RetentionPolicy.RUNTIME)
   @Target({ElementType.FIELD})
   public @interface BindMap {
      Class keySerializer() default Serializer.class;

      Class valueSerializer() default Serializer.class;

      Class keyClass() default Object.class;

      Class valueClass() default Object.class;

      boolean keysCanBeNull() default true;

      boolean valuesCanBeNull() default true;
   }
}
