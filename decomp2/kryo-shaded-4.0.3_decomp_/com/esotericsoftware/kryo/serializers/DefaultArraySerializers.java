package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;

public class DefaultArraySerializers {
   public static class ByteArraySerializer extends Serializer {
      public ByteArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, byte[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeBytes(object);
         }
      }

      public byte[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readBytes(length - 1);
      }

      public byte[] copy(Kryo kryo, byte[] original) {
         byte[] copy = new byte[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class IntArraySerializer extends Serializer {
      public IntArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, int[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeInts(object, false);
         }
      }

      public int[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readInts(length - 1, false);
      }

      public int[] copy(Kryo kryo, int[] original) {
         int[] copy = new int[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class FloatArraySerializer extends Serializer {
      public FloatArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, float[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeFloats(object);
         }
      }

      public float[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readFloats(length - 1);
      }

      public float[] copy(Kryo kryo, float[] original) {
         float[] copy = new float[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class LongArraySerializer extends Serializer {
      public LongArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, long[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeLongs(object, false);
         }
      }

      public long[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readLongs(length - 1, false);
      }

      public long[] copy(Kryo kryo, long[] original) {
         long[] copy = new long[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class ShortArraySerializer extends Serializer {
      public ShortArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, short[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeShorts(object);
         }
      }

      public short[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readShorts(length - 1);
      }

      public short[] copy(Kryo kryo, short[] original) {
         short[] copy = new short[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class CharArraySerializer extends Serializer {
      public CharArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, char[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeChars(object);
         }
      }

      public char[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readChars(length - 1);
      }

      public char[] copy(Kryo kryo, char[] original) {
         char[] copy = new char[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class DoubleArraySerializer extends Serializer {
      public DoubleArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, double[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            output.writeDoubles(object);
         }
      }

      public double[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         return length == 0 ? null : input.readDoubles(length - 1);
      }

      public double[] copy(Kryo kryo, double[] original) {
         double[] copy = new double[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class BooleanArraySerializer extends Serializer {
      public BooleanArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, boolean[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            int i = 0;

            for(int n = object.length; i < n; ++i) {
               output.writeBoolean(object[i]);
            }

         }
      }

      public boolean[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         if (length == 0) {
            return null;
         } else {
            --length;
            boolean[] array = new boolean[length];

            for(int i = 0; i < length; ++i) {
               array[i] = input.readBoolean();
            }

            return array;
         }
      }

      public boolean[] copy(Kryo kryo, boolean[] original) {
         boolean[] copy = new boolean[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class StringArraySerializer extends Serializer {
      public StringArraySerializer() {
         this.setAcceptsNull(true);
      }

      public void write(Kryo kryo, Output output, String[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            if (kryo.getReferences() && kryo.getReferenceResolver().useReferences(String.class)) {
               Serializer serializer = kryo.getSerializer(String.class);
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  kryo.writeObjectOrNull(output, object[i], (Serializer)serializer);
               }
            } else {
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  output.writeString(object[i]);
               }
            }

         }
      }

      public String[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         if (length == 0) {
            return null;
         } else {
            --length;
            String[] array = new String[length];
            if (kryo.getReferences() && kryo.getReferenceResolver().useReferences(String.class)) {
               Serializer serializer = kryo.getSerializer(String.class);

               for(int i = 0; i < length; ++i) {
                  array[i] = (String)kryo.readObjectOrNull(input, String.class, serializer);
               }
            } else {
               for(int i = 0; i < length; ++i) {
                  array[i] = input.readString();
               }
            }

            return array;
         }
      }

      public String[] copy(Kryo kryo, String[] original) {
         String[] copy = new String[original.length];
         System.arraycopy(original, 0, copy, 0, copy.length);
         return copy;
      }
   }

   public static class ObjectArraySerializer extends Serializer {
      private boolean elementsAreSameType;
      private boolean elementsCanBeNull = true;
      private Class[] generics;
      private final Class type;

      public ObjectArraySerializer(Kryo kryo, Class type) {
         this.setAcceptsNull(true);
         this.type = type;
         Class componentType = type.getComponentType();
         boolean isFinal = 0 != (componentType.getModifiers() & 16);
         if (isFinal) {
            this.setElementsAreSameType(true);
         }

      }

      public void write(Kryo kryo, Output output, Object[] object) {
         if (object == null) {
            output.writeVarInt(0, true);
         } else {
            output.writeVarInt(object.length + 1, true);
            Class elementClass = object.getClass().getComponentType();
            if (!this.elementsAreSameType && !Modifier.isFinal(elementClass.getModifiers())) {
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  if (object[i] != null) {
                     Serializer serializer = kryo.getSerializer(object[i].getClass());
                     serializer.setGenerics(kryo, this.generics);
                  }

                  kryo.writeClassAndObject(output, object[i]);
               }
            } else {
               Serializer elementSerializer = kryo.getSerializer(elementClass);
               elementSerializer.setGenerics(kryo, this.generics);
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  if (this.elementsCanBeNull) {
                     kryo.writeObjectOrNull(output, object[i], elementSerializer);
                  } else {
                     kryo.writeObject(output, object[i], elementSerializer);
                  }
               }
            }

         }
      }

      public Object[] read(Kryo kryo, Input input, Class type) {
         int length = input.readVarInt(true);
         if (length == 0) {
            return null;
         } else {
            Object[] object = Array.newInstance(type.getComponentType(), length - 1);
            kryo.reference(object);
            Class elementClass = object.getClass().getComponentType();
            if (!this.elementsAreSameType && !Modifier.isFinal(elementClass.getModifiers())) {
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  Registration registration = kryo.readClass(input);
                  if (registration != null) {
                     registration.getSerializer().setGenerics(kryo, this.generics);
                     object[i] = kryo.readObject(input, registration.getType(), registration.getSerializer());
                  } else {
                     object[i] = null;
                  }
               }
            } else {
               Serializer elementSerializer = kryo.getSerializer(elementClass);
               elementSerializer.setGenerics(kryo, this.generics);
               int i = 0;

               for(int n = object.length; i < n; ++i) {
                  if (this.elementsCanBeNull) {
                     object[i] = kryo.readObjectOrNull(input, elementClass, elementSerializer);
                  } else {
                     object[i] = kryo.readObject(input, elementClass, elementSerializer);
                  }
               }
            }

            return object;
         }
      }

      public Object[] copy(Kryo kryo, Object[] original) {
         Object[] copy = Array.newInstance(original.getClass().getComponentType(), original.length);
         int i = 0;

         for(int n = original.length; i < n; ++i) {
            copy[i] = kryo.copy(original[i]);
         }

         return copy;
      }

      public void setElementsCanBeNull(boolean elementsCanBeNull) {
         this.elementsCanBeNull = elementsCanBeNull;
      }

      public void setElementsAreSameType(boolean elementsAreSameType) {
         this.elementsAreSameType = elementsAreSameType;
      }

      public void setGenerics(Kryo kryo, Class[] generics) {
         if (Log.TRACE) {
            Log.trace("kryo", "setting generics for ObjectArraySerializer");
         }

         this.generics = generics;
      }
   }
}
