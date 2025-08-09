package com.esotericsoftware.kryo.util;

import com.esotericsoftware.kryo.ClassResolver;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Registration;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;

public class DefaultClassResolver implements ClassResolver {
   public static final byte NAME = -1;
   protected Kryo kryo;
   protected final IntMap idToRegistration = new IntMap();
   protected final ObjectMap classToRegistration = new ObjectMap();
   protected IdentityObjectIntMap classToNameId;
   protected IntMap nameIdToClass;
   protected ObjectMap nameToClass;
   protected int nextNameId;
   private int memoizedClassId = -1;
   private Registration memoizedClassIdValue;
   private Class memoizedClass;
   private Registration memoizedClassValue;

   public void setKryo(Kryo kryo) {
      this.kryo = kryo;
   }

   public Registration register(Registration registration) {
      if (registration == null) {
         throw new IllegalArgumentException("registration cannot be null.");
      } else {
         if (registration.getId() != -1) {
            if (Log.TRACE) {
               Log.trace("kryo", "Register class ID " + registration.getId() + ": " + Util.className(registration.getType()) + " (" + registration.getSerializer().getClass().getName() + ")");
            }

            this.idToRegistration.put(registration.getId(), registration);
         } else if (Log.TRACE) {
            Log.trace("kryo", "Register class name: " + Util.className(registration.getType()) + " (" + registration.getSerializer().getClass().getName() + ")");
         }

         this.classToRegistration.put(registration.getType(), registration);
         if (registration.getType().isPrimitive()) {
            this.classToRegistration.put(Util.getWrapperClass(registration.getType()), registration);
         }

         return registration;
      }
   }

   public Registration registerImplicit(Class type) {
      return this.register(new Registration(type, this.kryo.getDefaultSerializer(type), -1));
   }

   public Registration getRegistration(Class type) {
      if (type == this.memoizedClass) {
         return this.memoizedClassValue;
      } else {
         Registration registration = (Registration)this.classToRegistration.get(type);
         if (registration != null) {
            this.memoizedClass = type;
            this.memoizedClassValue = registration;
         }

         return registration;
      }
   }

   public Registration getRegistration(int classID) {
      return (Registration)this.idToRegistration.get(classID);
   }

   public Registration writeClass(Output output, Class type) {
      if (type != null) {
         Registration registration = this.kryo.getRegistration(type);
         if (registration.getId() == -1) {
            this.writeName(output, type, registration);
         } else {
            if (Log.TRACE) {
               Log.trace("kryo", "Write class " + registration.getId() + ": " + Util.className(type));
            }

            output.writeVarInt(registration.getId() + 2, true);
         }

         return registration;
      } else {
         if (Log.TRACE || Log.DEBUG && this.kryo.getDepth() == 1) {
            Util.log("Write", (Object)null);
         }

         output.writeVarInt(0, true);
         return null;
      }
   }

   protected void writeName(Output output, Class type, Registration registration) {
      output.writeVarInt(1, true);
      if (this.classToNameId != null) {
         int nameId = this.classToNameId.get(type, -1);
         if (nameId != -1) {
            if (Log.TRACE) {
               Log.trace("kryo", "Write class name reference " + nameId + ": " + Util.className(type));
            }

            output.writeVarInt(nameId, true);
            return;
         }
      }

      if (Log.TRACE) {
         Log.trace("kryo", "Write class name: " + Util.className(type));
      }

      int nameId = this.nextNameId++;
      if (this.classToNameId == null) {
         this.classToNameId = new IdentityObjectIntMap();
      }

      this.classToNameId.put(type, nameId);
      output.writeVarInt(nameId, true);
      output.writeString(type.getName());
   }

   public Registration readClass(Input input) {
      int classID = input.readVarInt(true);
      switch (classID) {
         case 0:
            if (Log.TRACE || Log.DEBUG && this.kryo.getDepth() == 1) {
               Util.log("Read", (Object)null);
            }

            return null;
         case 1:
            return this.readName(input);
         default:
            if (classID == this.memoizedClassId) {
               return this.memoizedClassIdValue;
            } else {
               Registration registration = (Registration)this.idToRegistration.get(classID - 2);
               if (registration == null) {
                  throw new KryoException("Encountered unregistered class ID: " + (classID - 2));
               } else {
                  if (Log.TRACE) {
                     Log.trace("kryo", "Read class " + (classID - 2) + ": " + Util.className(registration.getType()));
                  }

                  this.memoizedClassId = classID;
                  this.memoizedClassIdValue = registration;
                  return registration;
               }
            }
      }
   }

   protected Registration readName(Input input) {
      int nameId = input.readVarInt(true);
      if (this.nameIdToClass == null) {
         this.nameIdToClass = new IntMap();
      }

      Class type = (Class)this.nameIdToClass.get(nameId);
      if (type == null) {
         String className = input.readString();
         type = this.getTypeByName(className);
         if (type == null) {
            try {
               type = Class.forName(className, false, this.kryo.getClassLoader());
            } catch (ClassNotFoundException ex) {
               if (Log.WARN) {
                  Log.warn("kryo", "Unable to load class " + className + " with kryo's ClassLoader. Retrying with current..");
               }

               try {
                  type = Class.forName(className);
               } catch (ClassNotFoundException var7) {
                  throw new KryoException("Unable to find class: " + className, ex);
               }
            }

            if (this.nameToClass == null) {
               this.nameToClass = new ObjectMap();
            }

            this.nameToClass.put(className, type);
         }

         this.nameIdToClass.put(nameId, type);
         if (Log.TRACE) {
            Log.trace("kryo", "Read class name: " + className);
         }
      } else if (Log.TRACE) {
         Log.trace("kryo", "Read class name reference " + nameId + ": " + Util.className(type));
      }

      return this.kryo.getRegistration(type);
   }

   protected Class getTypeByName(String className) {
      return this.nameToClass != null ? (Class)this.nameToClass.get(className) : null;
   }

   public void reset() {
      if (!this.kryo.isRegistrationRequired()) {
         if (this.classToNameId != null) {
            this.classToNameId.clear(2048);
         }

         if (this.nameIdToClass != null) {
            this.nameIdToClass.clear();
         }

         this.nextNameId = 0;
      }

   }
}
