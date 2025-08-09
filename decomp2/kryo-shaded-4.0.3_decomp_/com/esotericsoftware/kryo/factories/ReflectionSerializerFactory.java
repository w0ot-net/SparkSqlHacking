package com.esotericsoftware.kryo.factories;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.util.Util;

public class ReflectionSerializerFactory implements SerializerFactory {
   private final Class serializerClass;

   public ReflectionSerializerFactory(Class serializerClass) {
      this.serializerClass = serializerClass;
   }

   public Serializer makeSerializer(Kryo kryo, Class type) {
      return makeSerializer(kryo, this.serializerClass, type);
   }

   public static Serializer makeSerializer(Kryo kryo, Class serializerClass, Class type) {
      try {
         try {
            return (Serializer)serializerClass.getConstructor(Kryo.class, Class.class).newInstance(kryo, type);
         } catch (NoSuchMethodException var8) {
            try {
               return (Serializer)serializerClass.getConstructor(Kryo.class).newInstance(kryo);
            } catch (NoSuchMethodException var7) {
               try {
                  return (Serializer)serializerClass.getConstructor(Class.class).newInstance(type);
               } catch (NoSuchMethodException var6) {
                  return (Serializer)serializerClass.newInstance();
               }
            }
         }
      } catch (Exception ex) {
         throw new IllegalArgumentException("Unable to create serializer \"" + serializerClass.getName() + "\" for class: " + Util.className(type), ex);
      }
   }
}
