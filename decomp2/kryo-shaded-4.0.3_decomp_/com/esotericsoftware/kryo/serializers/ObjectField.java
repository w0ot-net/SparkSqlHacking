package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.FieldAccess;

class ObjectField extends FieldSerializer.CachedField {
   public Class[] generics;
   final FieldSerializer fieldSerializer;
   final Class type;
   final Kryo kryo;

   ObjectField(FieldSerializer fieldSerializer) {
      this.fieldSerializer = fieldSerializer;
      this.kryo = fieldSerializer.kryo;
      this.type = fieldSerializer.type;
   }

   public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
      return this.field.get(object);
   }

   public void setField(Object object, Object value) throws IllegalArgumentException, IllegalAccessException {
      this.field.set(object, value);
   }

   public void write(Output output, Object object) {
      try {
         if (Log.TRACE) {
            Log.trace("kryo", "Write field: " + this + " (" + object.getClass().getName() + ") pos=" + output.position());
         }

         Object value = this.getField(object);
         Serializer serializer = this.serializer;
         if (this.valueClass == null) {
            if (value == null) {
               this.kryo.writeClass(output, (Class)null);
               return;
            }

            Registration registration = this.kryo.writeClass(output, value.getClass());
            if (serializer == null) {
               serializer = registration.getSerializer();
            }

            serializer.setGenerics(this.kryo, this.generics);
            this.kryo.writeObject(output, value, serializer);
         } else {
            if (serializer == null) {
               this.serializer = serializer = this.kryo.getSerializer(this.valueClass);
            }

            serializer.setGenerics(this.kryo, this.generics);
            if (this.canBeNull) {
               this.kryo.writeObjectOrNull(output, value, serializer);
            } else {
               if (value == null) {
                  throw new KryoException("Field value is null but canBeNull is false: " + this + " (" + object.getClass().getName() + ")");
               }

               this.kryo.writeObject(output, value, serializer);
            }
         }

      } catch (IllegalAccessException ex) {
         throw new KryoException("Error accessing field: " + this + " (" + object.getClass().getName() + ")", ex);
      } catch (KryoException ex) {
         ex.addTrace(this + " (" + object.getClass().getName() + ")");
         throw ex;
      } catch (RuntimeException runtimeEx) {
         KryoException ex = new KryoException(runtimeEx);
         ex.addTrace(this + " (" + object.getClass().getName() + ")");
         throw ex;
      } finally {
         ;
      }
   }

   public void read(Input input, Object object) {
      try {
         if (Log.TRACE) {
            Log.trace("kryo", "Read field: " + this + " (" + this.type.getName() + ") pos=" + input.position());
         }

         Class concreteType = this.valueClass;
         Serializer serializer = this.serializer;
         Object value;
         if (concreteType == null) {
            Registration registration = this.kryo.readClass(input);
            if (registration == null) {
               value = null;
            } else {
               if (serializer == null) {
                  serializer = registration.getSerializer();
               }

               serializer.setGenerics(this.kryo, this.generics);
               value = this.kryo.readObject(input, registration.getType(), serializer);
            }
         } else {
            if (serializer == null) {
               this.serializer = serializer = this.kryo.getSerializer(this.valueClass);
            }

            serializer.setGenerics(this.kryo, this.generics);
            if (this.canBeNull) {
               value = this.kryo.readObjectOrNull(input, concreteType, serializer);
            } else {
               value = this.kryo.readObject(input, concreteType, serializer);
            }
         }

         this.setField(object, value);
      } catch (IllegalAccessException ex) {
         throw new KryoException("Error accessing field: " + this + " (" + this.type.getName() + ")", ex);
      } catch (KryoException ex) {
         ex.addTrace(this + " (" + this.type.getName() + ")");
         throw ex;
      } catch (RuntimeException runtimeEx) {
         KryoException ex = new KryoException(runtimeEx);
         ex.addTrace(this + " (" + this.type.getName() + ")");
         throw ex;
      } finally {
         ;
      }
   }

   public void copy(Object original, Object copy) {
      try {
         if (this.accessIndex != -1) {
            FieldAccess access = (FieldAccess)this.fieldSerializer.access;
            access.set(copy, this.accessIndex, this.kryo.copy(access.get(original, this.accessIndex)));
         } else {
            this.setField(copy, this.kryo.copy(this.getField(original)));
         }

      } catch (IllegalAccessException ex) {
         throw new KryoException("Error accessing field: " + this + " (" + this.type.getName() + ")", ex);
      } catch (KryoException ex) {
         ex.addTrace(this + " (" + this.type.getName() + ")");
         throw ex;
      } catch (RuntimeException runtimeEx) {
         KryoException ex = new KryoException(runtimeEx);
         ex.addTrace(this + " (" + this.type.getName() + ")");
         throw ex;
      }
   }

   static final class ObjectIntField extends ObjectField {
      public ObjectIntField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getInt(object);
      }

      public void write(Output output, Object object) {
         try {
            if (this.varIntsEnabled) {
               output.writeInt(this.field.getInt(object), false);
            } else {
               output.writeInt(this.field.getInt(object));
            }

         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            if (this.varIntsEnabled) {
               this.field.setInt(object, input.readInt(false));
            } else {
               this.field.setInt(object, input.readInt());
            }

         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setInt(copy, this.field.getInt(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectFloatField extends ObjectField {
      public ObjectFloatField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getFloat(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeFloat(this.field.getFloat(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setFloat(object, input.readFloat());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setFloat(copy, this.field.getFloat(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectShortField extends ObjectField {
      public ObjectShortField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getShort(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeShort(this.field.getShort(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setShort(object, input.readShort());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setShort(copy, this.field.getShort(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectByteField extends ObjectField {
      public ObjectByteField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getByte(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeByte(this.field.getByte(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setByte(object, input.readByte());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setByte(copy, this.field.getByte(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectBooleanField extends ObjectField {
      public ObjectBooleanField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getBoolean(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeBoolean(this.field.getBoolean(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setBoolean(object, input.readBoolean());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setBoolean(copy, this.field.getBoolean(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectCharField extends ObjectField {
      public ObjectCharField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getChar(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeChar(this.field.getChar(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setChar(object, input.readChar());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setChar(copy, this.field.getChar(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectLongField extends ObjectField {
      public ObjectLongField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getLong(object);
      }

      public void write(Output output, Object object) {
         try {
            if (this.varIntsEnabled) {
               output.writeLong(this.field.getLong(object), false);
            } else {
               output.writeLong(this.field.getLong(object));
            }

         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            if (this.varIntsEnabled) {
               this.field.setLong(object, input.readLong(false));
            } else {
               this.field.setLong(object, input.readLong());
            }

         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setLong(copy, this.field.getLong(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }

   static final class ObjectDoubleField extends ObjectField {
      public ObjectDoubleField(FieldSerializer fieldSerializer) {
         super(fieldSerializer);
      }

      public Object getField(Object object) throws IllegalArgumentException, IllegalAccessException {
         return this.field.getDouble(object);
      }

      public void write(Output output, Object object) {
         try {
            output.writeDouble(this.field.getDouble(object));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void read(Input input, Object object) {
         try {
            this.field.setDouble(object, input.readDouble());
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }

      public void copy(Object original, Object copy) {
         try {
            this.field.setDouble(copy, this.field.getDouble(original));
         } catch (Exception e) {
            KryoException ex = new KryoException(e);
            ex.addTrace(this + " (" + this.type.getName() + ")");
            throw ex;
         }
      }
   }
}
