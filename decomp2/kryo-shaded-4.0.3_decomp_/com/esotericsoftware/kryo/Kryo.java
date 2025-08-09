package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.factories.PseudoSerializerFactory;
import com.esotericsoftware.kryo.factories.ReflectionSerializerFactory;
import com.esotericsoftware.kryo.factories.SerializerFactory;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.ClosureSerializer;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers;
import com.esotericsoftware.kryo.serializers.DefaultSerializers;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.FieldSerializerConfig;
import com.esotericsoftware.kryo.serializers.GenericsResolver;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.esotericsoftware.kryo.serializers.OptionalSerializers;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializerConfig;
import com.esotericsoftware.kryo.serializers.TimeSerializers;
import com.esotericsoftware.kryo.util.DefaultClassResolver;
import com.esotericsoftware.kryo.util.DefaultStreamFactory;
import com.esotericsoftware.kryo.util.IdentityMap;
import com.esotericsoftware.kryo.util.IntArray;
import com.esotericsoftware.kryo.util.MapReferenceResolver;
import com.esotericsoftware.kryo.util.ObjectMap;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.reflectasm.ConstructorAccess;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.strategy.InstantiatorStrategy;

public class Kryo {
   public static final byte NULL = 0;
   public static final byte NOT_NULL = 1;
   private static final int REF = -1;
   private static final int NO_REF = -2;
   private SerializerFactory defaultSerializer;
   private final ArrayList defaultSerializers;
   private final int lowPriorityDefaultSerializerCount;
   private final ClassResolver classResolver;
   private int nextRegisterID;
   private ClassLoader classLoader;
   private InstantiatorStrategy strategy;
   private boolean registrationRequired;
   private boolean warnUnregisteredClasses;
   private int depth;
   private int maxDepth;
   private boolean autoReset;
   private volatile Thread thread;
   private ObjectMap context;
   private ObjectMap graphContext;
   private ReferenceResolver referenceResolver;
   private final IntArray readReferenceIds;
   private boolean references;
   private boolean copyReferences;
   private Object readObject;
   private int copyDepth;
   private boolean copyShallow;
   private IdentityMap originalToCopy;
   private Object needsCopyReference;
   private GenericsResolver genericsResolver;
   private FieldSerializerConfig fieldSerializerConfig;
   private TaggedFieldSerializerConfig taggedFieldSerializerConfig;
   private StreamFactory streamFactory;

   public Kryo() {
      this(new DefaultClassResolver(), new MapReferenceResolver(), new DefaultStreamFactory());
   }

   public Kryo(ReferenceResolver referenceResolver) {
      this(new DefaultClassResolver(), referenceResolver, new DefaultStreamFactory());
   }

   public Kryo(ClassResolver classResolver, ReferenceResolver referenceResolver) {
      this(classResolver, referenceResolver, new DefaultStreamFactory());
   }

   public Kryo(ClassResolver classResolver, ReferenceResolver referenceResolver, StreamFactory streamFactory) {
      this.defaultSerializer = new ReflectionSerializerFactory(FieldSerializer.class);
      this.defaultSerializers = new ArrayList(33);
      this.classLoader = this.getClass().getClassLoader();
      this.strategy = new DefaultInstantiatorStrategy();
      this.maxDepth = Integer.MAX_VALUE;
      this.autoReset = true;
      this.readReferenceIds = new IntArray(0);
      this.copyReferences = true;
      this.genericsResolver = new GenericsResolver();
      this.fieldSerializerConfig = new FieldSerializerConfig();
      this.taggedFieldSerializerConfig = new TaggedFieldSerializerConfig();
      if (classResolver == null) {
         throw new IllegalArgumentException("classResolver cannot be null.");
      } else {
         this.classResolver = classResolver;
         classResolver.setKryo(this);
         this.streamFactory = streamFactory;
         streamFactory.setKryo(this);
         this.referenceResolver = referenceResolver;
         if (referenceResolver != null) {
            referenceResolver.setKryo(this);
            this.references = true;
         }

         this.addDefaultSerializer(byte[].class, DefaultArraySerializers.ByteArraySerializer.class);
         this.addDefaultSerializer(char[].class, DefaultArraySerializers.CharArraySerializer.class);
         this.addDefaultSerializer(short[].class, DefaultArraySerializers.ShortArraySerializer.class);
         this.addDefaultSerializer(int[].class, DefaultArraySerializers.IntArraySerializer.class);
         this.addDefaultSerializer(long[].class, DefaultArraySerializers.LongArraySerializer.class);
         this.addDefaultSerializer(float[].class, DefaultArraySerializers.FloatArraySerializer.class);
         this.addDefaultSerializer(double[].class, DefaultArraySerializers.DoubleArraySerializer.class);
         this.addDefaultSerializer(boolean[].class, DefaultArraySerializers.BooleanArraySerializer.class);
         this.addDefaultSerializer(String[].class, DefaultArraySerializers.StringArraySerializer.class);
         this.addDefaultSerializer(Object[].class, DefaultArraySerializers.ObjectArraySerializer.class);
         this.addDefaultSerializer(KryoSerializable.class, DefaultSerializers.KryoSerializableSerializer.class);
         this.addDefaultSerializer(BigInteger.class, DefaultSerializers.BigIntegerSerializer.class);
         this.addDefaultSerializer(BigDecimal.class, DefaultSerializers.BigDecimalSerializer.class);
         this.addDefaultSerializer(Class.class, DefaultSerializers.ClassSerializer.class);
         this.addDefaultSerializer(Date.class, DefaultSerializers.DateSerializer.class);
         this.addDefaultSerializer(Enum.class, DefaultSerializers.EnumSerializer.class);
         this.addDefaultSerializer(EnumSet.class, DefaultSerializers.EnumSetSerializer.class);
         this.addDefaultSerializer(Currency.class, DefaultSerializers.CurrencySerializer.class);
         this.addDefaultSerializer(StringBuffer.class, DefaultSerializers.StringBufferSerializer.class);
         this.addDefaultSerializer(StringBuilder.class, DefaultSerializers.StringBuilderSerializer.class);
         this.addDefaultSerializer(Collections.EMPTY_LIST.getClass(), DefaultSerializers.CollectionsEmptyListSerializer.class);
         this.addDefaultSerializer(Collections.EMPTY_MAP.getClass(), DefaultSerializers.CollectionsEmptyMapSerializer.class);
         this.addDefaultSerializer(Collections.EMPTY_SET.getClass(), DefaultSerializers.CollectionsEmptySetSerializer.class);
         this.addDefaultSerializer(Collections.singletonList((Object)null).getClass(), DefaultSerializers.CollectionsSingletonListSerializer.class);
         this.addDefaultSerializer(Collections.singletonMap((Object)null, (Object)null).getClass(), DefaultSerializers.CollectionsSingletonMapSerializer.class);
         this.addDefaultSerializer(Collections.singleton((Object)null).getClass(), DefaultSerializers.CollectionsSingletonSetSerializer.class);
         this.addDefaultSerializer(TreeSet.class, DefaultSerializers.TreeSetSerializer.class);
         this.addDefaultSerializer(Collection.class, CollectionSerializer.class);
         this.addDefaultSerializer(TreeMap.class, DefaultSerializers.TreeMapSerializer.class);
         this.addDefaultSerializer(Map.class, MapSerializer.class);
         this.addDefaultSerializer(TimeZone.class, DefaultSerializers.TimeZoneSerializer.class);
         this.addDefaultSerializer(Calendar.class, DefaultSerializers.CalendarSerializer.class);
         this.addDefaultSerializer(Locale.class, DefaultSerializers.LocaleSerializer.class);
         this.addDefaultSerializer(Charset.class, DefaultSerializers.CharsetSerializer.class);
         this.addDefaultSerializer(URL.class, DefaultSerializers.URLSerializer.class);
         OptionalSerializers.addDefaultSerializers(this);
         TimeSerializers.addDefaultSerializers(this);
         this.lowPriorityDefaultSerializerCount = this.defaultSerializers.size();
         this.register(Integer.TYPE, new DefaultSerializers.IntSerializer());
         this.register(String.class, new DefaultSerializers.StringSerializer());
         this.register(Float.TYPE, new DefaultSerializers.FloatSerializer());
         this.register(Boolean.TYPE, new DefaultSerializers.BooleanSerializer());
         this.register(Byte.TYPE, new DefaultSerializers.ByteSerializer());
         this.register(Character.TYPE, new DefaultSerializers.CharSerializer());
         this.register(Short.TYPE, new DefaultSerializers.ShortSerializer());
         this.register(Long.TYPE, new DefaultSerializers.LongSerializer());
         this.register(Double.TYPE, new DefaultSerializers.DoubleSerializer());
         this.register(Void.TYPE, new DefaultSerializers.VoidSerializer());
      }
   }

   public void setDefaultSerializer(SerializerFactory serializer) {
      if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.defaultSerializer = serializer;
      }
   }

   public void setDefaultSerializer(Class serializer) {
      if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.defaultSerializer = new ReflectionSerializerFactory(serializer);
      }
   }

   public void addDefaultSerializer(Class type, Serializer serializer) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         DefaultSerializerEntry entry = new DefaultSerializerEntry(type, new PseudoSerializerFactory(serializer));
         this.defaultSerializers.add(this.defaultSerializers.size() - this.lowPriorityDefaultSerializerCount, entry);
      }
   }

   public void addDefaultSerializer(Class type, SerializerFactory serializerFactory) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (serializerFactory == null) {
         throw new IllegalArgumentException("serializerFactory cannot be null.");
      } else {
         DefaultSerializerEntry entry = new DefaultSerializerEntry(type, serializerFactory);
         this.defaultSerializers.add(this.defaultSerializers.size() - this.lowPriorityDefaultSerializerCount, entry);
      }
   }

   public void addDefaultSerializer(Class type, Class serializerClass) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (serializerClass == null) {
         throw new IllegalArgumentException("serializerClass cannot be null.");
      } else {
         DefaultSerializerEntry entry = new DefaultSerializerEntry(type, new ReflectionSerializerFactory(serializerClass));
         this.defaultSerializers.add(this.defaultSerializers.size() - this.lowPriorityDefaultSerializerCount, entry);
      }
   }

   public Serializer getDefaultSerializer(Class type) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         Serializer serializerForAnnotation = this.getDefaultSerializerForAnnotatedType(type);
         if (serializerForAnnotation != null) {
            return serializerForAnnotation;
         } else {
            int i = 0;

            for(int n = this.defaultSerializers.size(); i < n; ++i) {
               DefaultSerializerEntry entry = (DefaultSerializerEntry)this.defaultSerializers.get(i);
               if (entry.type.isAssignableFrom(type)) {
                  Serializer defaultSerializer = entry.serializerFactory.makeSerializer(this, type);
                  return defaultSerializer;
               }
            }

            return this.newDefaultSerializer(type);
         }
      }
   }

   protected Serializer getDefaultSerializerForAnnotatedType(Class type) {
      if (type.isAnnotationPresent(DefaultSerializer.class)) {
         DefaultSerializer defaultSerializerAnnotation = (DefaultSerializer)type.getAnnotation(DefaultSerializer.class);
         return ReflectionSerializerFactory.makeSerializer(this, defaultSerializerAnnotation.value(), type);
      } else {
         return null;
      }
   }

   protected Serializer newDefaultSerializer(Class type) {
      return this.defaultSerializer.makeSerializer(this, type);
   }

   public Registration register(Class type) {
      Registration registration = this.classResolver.getRegistration(type);
      return registration != null ? registration : this.register(type, this.getDefaultSerializer(type));
   }

   public Registration register(Class type, int id) {
      Registration registration = this.classResolver.getRegistration(type);
      return registration != null ? registration : this.register(type, this.getDefaultSerializer(type), id);
   }

   public Registration register(Class type, Serializer serializer) {
      Registration registration = this.classResolver.getRegistration(type);
      if (registration != null) {
         registration.setSerializer(serializer);
         return registration;
      } else {
         return this.classResolver.register(new Registration(type, serializer, this.getNextRegistrationId()));
      }
   }

   public Registration register(Class type, Serializer serializer, int id) {
      if (id < 0) {
         throw new IllegalArgumentException("id must be >= 0: " + id);
      } else {
         return this.register(new Registration(type, serializer, id));
      }
   }

   public Registration register(Registration registration) {
      int id = registration.getId();
      if (id < 0) {
         throw new IllegalArgumentException("id must be > 0: " + id);
      } else {
         Registration existing = this.getRegistration(registration.getId());
         if (Log.DEBUG && existing != null && existing.getType() != registration.getType()) {
            Log.debug("An existing registration with a different type already uses ID: " + registration.getId() + "\nExisting registration: " + existing + "\nis now overwritten with: " + registration);
         }

         return this.classResolver.register(registration);
      }
   }

   public int getNextRegistrationId() {
      while(this.nextRegisterID != -2) {
         if (this.classResolver.getRegistration(this.nextRegisterID) == null) {
            return this.nextRegisterID;
         }

         ++this.nextRegisterID;
      }

      throw new KryoException("No registration IDs are available.");
   }

   public Registration getRegistration(Class type) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         Registration registration = this.classResolver.getRegistration(type);
         if (registration == null) {
            if (Proxy.isProxyClass(type)) {
               registration = this.getRegistration(InvocationHandler.class);
            } else if (!type.isEnum() && Enum.class.isAssignableFrom(type) && !Enum.class.equals(type)) {
               registration = this.getRegistration(type.getEnclosingClass());
            } else if (EnumSet.class.isAssignableFrom(type)) {
               registration = this.classResolver.getRegistration(EnumSet.class);
            } else if (this.isClosure(type)) {
               registration = this.classResolver.getRegistration(ClosureSerializer.Closure.class);
            }

            if (registration == null) {
               if (this.registrationRequired) {
                  throw new IllegalArgumentException(this.unregisteredClassMessage(type));
               }

               if (this.warnUnregisteredClasses) {
                  Log.warn(this.unregisteredClassMessage(type));
               }

               registration = this.classResolver.registerImplicit(type);
            }
         }

         return registration;
      }
   }

   protected String unregisteredClassMessage(Class type) {
      return "Class is not registered: " + Util.className(type) + "\nNote: To register this class use: kryo.register(" + Util.className(type) + ".class);";
   }

   public Registration getRegistration(int classID) {
      return this.classResolver.getRegistration(classID);
   }

   public Serializer getSerializer(Class type) {
      return this.getRegistration(type).getSerializer();
   }

   public Registration writeClass(Output output, Class type) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else {
         Registration var3;
         try {
            var3 = this.classResolver.writeClass(output, type);
         } finally {
            if (this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

         return var3;
      }
   }

   public void writeObject(Output output, Object object) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else if (object == null) {
         throw new IllegalArgumentException("object cannot be null.");
      } else {
         this.beginObject();

         try {
            if (!this.references || !this.writeReferenceOrNull(output, object, false)) {
               if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                  Util.log("Write", object);
               }

               this.getRegistration(object.getClass()).getSerializer().write(this, output, object);
               return;
            }

            this.getRegistration(object.getClass()).getSerializer().setGenerics(this, (Class[])null);
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

      }
   }

   public void writeObject(Output output, Object object, Serializer serializer) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else if (object == null) {
         throw new IllegalArgumentException("object cannot be null.");
      } else if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.beginObject();

         try {
            if (!this.references || !this.writeReferenceOrNull(output, object, false)) {
               if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                  Util.log("Write", object);
               }

               serializer.write(this, output, object);
               return;
            }

            serializer.setGenerics(this, (Class[])null);
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

      }
   }

   public void writeObjectOrNull(Output output, Object object, Class type) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else {
         this.beginObject();

         try {
            Serializer serializer = this.getRegistration(type).getSerializer();
            if (this.references) {
               if (this.writeReferenceOrNull(output, object, true)) {
                  serializer.setGenerics(this, (Class[])null);
                  return;
               }
            } else if (!serializer.getAcceptsNull()) {
               if (object == null) {
                  if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                     Util.log("Write", object);
                  }

                  output.writeByte((byte)0);
                  return;
               }

               output.writeByte((byte)1);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Write", object);
            }

            serializer.write(this, output, object);
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public void writeObjectOrNull(Output output, Object object, Serializer serializer) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.beginObject();

         try {
            if (this.references) {
               if (this.writeReferenceOrNull(output, object, true)) {
                  serializer.setGenerics(this, (Class[])null);
                  return;
               }
            } else if (!serializer.getAcceptsNull()) {
               if (object == null) {
                  if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                     Util.log("Write", (Object)null);
                  }

                  output.writeByte((byte)0);
                  return;
               }

               output.writeByte((byte)1);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Write", object);
            }

            serializer.write(this, output, object);
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public void writeClassAndObject(Output output, Object object) {
      if (output == null) {
         throw new IllegalArgumentException("output cannot be null.");
      } else {
         this.beginObject();

         try {
            if (object != null) {
               Registration registration = this.writeClass(output, object.getClass());
               if (this.references && this.writeReferenceOrNull(output, object, false)) {
                  registration.getSerializer().setGenerics(this, (Class[])null);
                  return;
               }

               if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                  Util.log("Write", object);
               }

               registration.getSerializer().write(this, output, object);
               return;
            }

            this.writeClass(output, (Class)null);
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

      }
   }

   boolean writeReferenceOrNull(Output output, Object object, boolean mayBeNull) {
      if (object != null) {
         if (!this.referenceResolver.useReferences(object.getClass())) {
            if (mayBeNull) {
               output.writeVarInt(1, true);
            }

            return false;
         } else {
            int id = this.referenceResolver.getWrittenId(object);
            if (id != -1) {
               if (Log.DEBUG) {
                  Log.debug("kryo", "Write object reference " + id + ": " + Util.string(object));
               }

               output.writeVarInt(id + 2, true);
               return true;
            } else {
               id = this.referenceResolver.addWrittenObject(object);
               output.writeVarInt(1, true);
               if (Log.TRACE) {
                  Log.trace("kryo", "Write initial object reference " + id + ": " + Util.string(object));
               }

               return false;
            }
         }
      } else {
         if (Log.TRACE || Log.DEBUG && this.depth == 1) {
            Util.log("Write", (Object)null);
         }

         output.writeVarInt(0, true);
         return true;
      }
   }

   public Registration readClass(Input input) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else {
         Registration var2;
         try {
            var2 = this.classResolver.readClass(input);
         } finally {
            if (this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

         return var2;
      }
   }

   public Object readObject(Input input, Class type) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         this.beginObject();

         try {
            T object;
            if (this.references) {
               int stackSize = this.readReferenceOrNull(input, type, false);
               if (stackSize == -1) {
                  Object var5 = this.readObject;
                  return var5;
               }

               object = (T)this.getRegistration(type).getSerializer().read(this, input, type);
               if (stackSize == this.readReferenceIds.size) {
                  this.reference(object);
               }
            } else {
               object = (T)this.getRegistration(type).getSerializer().read(this, input, type);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Read", object);
            }

            Object var9 = object;
            return var9;
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public Object readObject(Input input, Class type, Serializer serializer) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.beginObject();

         try {
            T object;
            if (this.references) {
               int stackSize = this.readReferenceOrNull(input, type, false);
               if (stackSize == -1) {
                  Object var6 = this.readObject;
                  return var6;
               }

               object = (T)serializer.read(this, input, type);
               if (stackSize == this.readReferenceIds.size) {
                  this.reference(object);
               }
            } else {
               object = (T)serializer.read(this, input, type);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Read", object);
            }

            Object var10 = object;
            return var10;
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public Object readObjectOrNull(Input input, Class type) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         this.beginObject();

         try {
            T object;
            if (this.references) {
               int stackSize = this.readReferenceOrNull(input, type, true);
               if (stackSize == -1) {
                  Object var5 = this.readObject;
                  return var5;
               }

               object = (T)this.getRegistration(type).getSerializer().read(this, input, type);
               if (stackSize == this.readReferenceIds.size) {
                  this.reference(object);
               }
            } else {
               Serializer serializer = this.getRegistration(type).getSerializer();
               if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                  if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                     Util.log("Read", (Object)null);
                  }

                  Object var11 = null;
                  return var11;
               }

               object = (T)serializer.read(this, input, type);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Read", object);
            }

            Object var10 = object;
            return var10;
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public Object readObjectOrNull(Input input, Class type, Serializer serializer) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (serializer == null) {
         throw new IllegalArgumentException("serializer cannot be null.");
      } else {
         this.beginObject();

         try {
            T object;
            if (this.references) {
               int stackSize = this.readReferenceOrNull(input, type, true);
               if (stackSize == -1) {
                  Object var6 = this.readObject;
                  return var6;
               }

               object = (T)serializer.read(this, input, type);
               if (stackSize == this.readReferenceIds.size) {
                  this.reference(object);
               }
            } else {
               if (!serializer.getAcceptsNull() && input.readByte() == 0) {
                  if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                     Util.log("Read", (Object)null);
                  }

                  Object var11 = null;
                  return var11;
               }

               object = (T)serializer.read(this, input, type);
            }

            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Read", object);
            }

            Object var10 = object;
            return var10;
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }
      }
   }

   public Object readClassAndObject(Input input) {
      if (input == null) {
         throw new IllegalArgumentException("input cannot be null.");
      } else {
         this.beginObject();

         Object var3;
         try {
            Registration registration = this.readClass(input);
            if (registration != null) {
               Class type = registration.getType();
               Object object;
               if (this.references) {
                  registration.getSerializer().setGenerics(this, (Class[])null);
                  int stackSize = this.readReferenceOrNull(input, type, false);
                  if (stackSize == -1) {
                     Object var6 = this.readObject;
                     return var6;
                  }

                  object = registration.getSerializer().read(this, input, type);
                  if (stackSize == this.readReferenceIds.size) {
                     this.reference(object);
                  }
               } else {
                  object = registration.getSerializer().read(this, input, type);
               }

               if (Log.TRACE || Log.DEBUG && this.depth == 1) {
                  Util.log("Read", object);
               }

               Object var11 = object;
               return var11;
            }

            var3 = null;
         } finally {
            if (--this.depth == 0 && this.autoReset) {
               this.reset();
            }

         }

         return var3;
      }
   }

   int readReferenceOrNull(Input input, Class type, boolean mayBeNull) {
      if (type.isPrimitive()) {
         type = Util.getWrapperClass(type);
      }

      boolean referencesSupported = this.referenceResolver.useReferences(type);
      int id;
      if (mayBeNull) {
         id = input.readVarInt(true);
         if (id == 0) {
            if (Log.TRACE || Log.DEBUG && this.depth == 1) {
               Util.log("Read", (Object)null);
            }

            this.readObject = null;
            return -1;
         }

         if (!referencesSupported) {
            this.readReferenceIds.add(-2);
            return this.readReferenceIds.size;
         }
      } else {
         if (!referencesSupported) {
            this.readReferenceIds.add(-2);
            return this.readReferenceIds.size;
         }

         id = input.readVarInt(true);
      }

      if (id == 1) {
         id = this.referenceResolver.nextReadId(type);
         if (Log.TRACE) {
            Log.trace("kryo", "Read initial object reference " + id + ": " + Util.className(type));
         }

         this.readReferenceIds.add(id);
         return this.readReferenceIds.size;
      } else {
         id -= 2;
         this.readObject = this.referenceResolver.getReadObject(type, id);
         if (Log.DEBUG) {
            Log.debug("kryo", "Read object reference " + id + ": " + Util.string(this.readObject));
         }

         return -1;
      }
   }

   public void reference(Object object) {
      if (this.copyDepth > 0) {
         if (this.needsCopyReference != null) {
            if (object == null) {
               throw new IllegalArgumentException("object cannot be null.");
            }

            this.originalToCopy.put(this.needsCopyReference, object);
            this.needsCopyReference = null;
         }
      } else if (this.references && object != null) {
         int id = this.readReferenceIds.pop();
         if (id != -2) {
            this.referenceResolver.setReadObject(id, object);
         }
      }

   }

   public void reset() {
      this.depth = 0;
      if (this.graphContext != null) {
         this.graphContext.clear();
      }

      this.classResolver.reset();
      if (this.references) {
         this.referenceResolver.reset();
         this.readObject = null;
      }

      this.copyDepth = 0;
      if (this.originalToCopy != null) {
         this.originalToCopy.clear(2048);
      }

      if (Log.TRACE) {
         Log.trace("kryo", "Object graph complete.");
      }

   }

   public Object copy(Object object) {
      if (object == null) {
         return null;
      } else if (this.copyShallow) {
         return object;
      } else {
         ++this.copyDepth;

         Object copy;
         try {
            if (this.originalToCopy == null) {
               this.originalToCopy = new IdentityMap();
            }

            Object existingCopy = this.originalToCopy.get(object);
            if (existingCopy == null) {
               if (this.copyReferences) {
                  this.needsCopyReference = object;
               }

               if (object instanceof KryoCopyable) {
                  copy = ((KryoCopyable)object).copy(this);
               } else {
                  copy = this.getSerializer(object.getClass()).copy(this, object);
               }

               if (this.needsCopyReference != null) {
                  this.reference(copy);
               }

               if (Log.TRACE || Log.DEBUG && this.copyDepth == 1) {
                  Util.log("Copy", copy);
               }

               Object var4 = copy;
               return var4;
            }

            copy = existingCopy;
         } finally {
            if (--this.copyDepth == 0) {
               this.reset();
            }

         }

         return copy;
      }
   }

   public Object copy(Object object, Serializer serializer) {
      if (object == null) {
         return null;
      } else if (this.copyShallow) {
         return object;
      } else {
         ++this.copyDepth;

         Object copy;
         try {
            if (this.originalToCopy == null) {
               this.originalToCopy = new IdentityMap();
            }

            Object existingCopy = this.originalToCopy.get(object);
            if (existingCopy == null) {
               if (this.copyReferences) {
                  this.needsCopyReference = object;
               }

               if (object instanceof KryoCopyable) {
                  copy = ((KryoCopyable)object).copy(this);
               } else {
                  copy = serializer.copy(this, object);
               }

               if (this.needsCopyReference != null) {
                  this.reference(copy);
               }

               if (Log.TRACE || Log.DEBUG && this.copyDepth == 1) {
                  Util.log("Copy", copy);
               }

               Object var5 = copy;
               return var5;
            }

            copy = existingCopy;
         } finally {
            if (--this.copyDepth == 0) {
               this.reset();
            }

         }

         return copy;
      }
   }

   public Object copyShallow(Object object) {
      if (object == null) {
         return null;
      } else {
         ++this.copyDepth;
         this.copyShallow = true;

         Object copy;
         try {
            if (this.originalToCopy == null) {
               this.originalToCopy = new IdentityMap();
            }

            Object existingCopy = this.originalToCopy.get(object);
            if (existingCopy == null) {
               if (this.copyReferences) {
                  this.needsCopyReference = object;
               }

               if (object instanceof KryoCopyable) {
                  copy = ((KryoCopyable)object).copy(this);
               } else {
                  copy = this.getSerializer(object.getClass()).copy(this, object);
               }

               if (this.needsCopyReference != null) {
                  this.reference(copy);
               }

               if (Log.TRACE || Log.DEBUG && this.copyDepth == 1) {
                  Util.log("Shallow copy", copy);
               }

               Object var4 = copy;
               return var4;
            }

            copy = existingCopy;
         } finally {
            this.copyShallow = false;
            if (--this.copyDepth == 0) {
               this.reset();
            }

         }

         return copy;
      }
   }

   public Object copyShallow(Object object, Serializer serializer) {
      if (object == null) {
         return null;
      } else {
         ++this.copyDepth;
         this.copyShallow = true;

         Object copy;
         try {
            if (this.originalToCopy == null) {
               this.originalToCopy = new IdentityMap();
            }

            Object existingCopy = this.originalToCopy.get(object);
            if (existingCopy == null) {
               if (this.copyReferences) {
                  this.needsCopyReference = object;
               }

               if (object instanceof KryoCopyable) {
                  copy = ((KryoCopyable)object).copy(this);
               } else {
                  copy = serializer.copy(this, object);
               }

               if (this.needsCopyReference != null) {
                  this.reference(copy);
               }

               if (Log.TRACE || Log.DEBUG && this.copyDepth == 1) {
                  Util.log("Shallow copy", copy);
               }

               Object var5 = copy;
               return var5;
            }

            copy = existingCopy;
         } finally {
            this.copyShallow = false;
            if (--this.copyDepth == 0) {
               this.reset();
            }

         }

         return copy;
      }
   }

   private void beginObject() {
      if (Log.DEBUG) {
         if (this.depth == 0) {
            this.thread = Thread.currentThread();
         } else if (this.thread != Thread.currentThread()) {
            throw new ConcurrentModificationException("Kryo must not be accessed concurrently by multiple threads.");
         }
      }

      if (this.depth == this.maxDepth) {
         throw new KryoException("Max depth exceeded: " + this.depth);
      } else {
         ++this.depth;
      }
   }

   public ClassResolver getClassResolver() {
      return this.classResolver;
   }

   public ReferenceResolver getReferenceResolver() {
      return this.referenceResolver;
   }

   public void setClassLoader(ClassLoader classLoader) {
      if (classLoader == null) {
         throw new IllegalArgumentException("classLoader cannot be null.");
      } else {
         this.classLoader = classLoader;
      }
   }

   public ClassLoader getClassLoader() {
      return this.classLoader;
   }

   public void setRegistrationRequired(boolean registrationRequired) {
      this.registrationRequired = registrationRequired;
      if (Log.TRACE) {
         Log.trace("kryo", "Registration required: " + registrationRequired);
      }

   }

   public boolean isRegistrationRequired() {
      return this.registrationRequired;
   }

   public void setWarnUnregisteredClasses(boolean warnUnregisteredClasses) {
      this.warnUnregisteredClasses = warnUnregisteredClasses;
      if (Log.TRACE) {
         Log.trace("kryo", "Warn unregistered classes: " + warnUnregisteredClasses);
      }

   }

   public boolean isWarnUnregisteredClasses() {
      return this.warnUnregisteredClasses;
   }

   public boolean setReferences(boolean references) {
      if (references == this.references) {
         return references;
      } else {
         this.references = references;
         if (references && this.referenceResolver == null) {
            this.referenceResolver = new MapReferenceResolver();
         }

         if (Log.TRACE) {
            Log.trace("kryo", "References: " + references);
         }

         return !references;
      }
   }

   public void setCopyReferences(boolean copyReferences) {
      this.copyReferences = copyReferences;
   }

   public FieldSerializerConfig getFieldSerializerConfig() {
      return this.fieldSerializerConfig;
   }

   public TaggedFieldSerializerConfig getTaggedFieldSerializerConfig() {
      return this.taggedFieldSerializerConfig;
   }

   public void setReferenceResolver(ReferenceResolver referenceResolver) {
      if (referenceResolver == null) {
         throw new IllegalArgumentException("referenceResolver cannot be null.");
      } else {
         this.references = true;
         this.referenceResolver = referenceResolver;
         if (Log.TRACE) {
            Log.trace("kryo", "Reference resolver: " + referenceResolver.getClass().getName());
         }

      }
   }

   public boolean getReferences() {
      return this.references;
   }

   public void setInstantiatorStrategy(InstantiatorStrategy strategy) {
      this.strategy = strategy;
   }

   public InstantiatorStrategy getInstantiatorStrategy() {
      return this.strategy;
   }

   protected ObjectInstantiator newInstantiator(Class type) {
      return this.strategy.newInstantiatorOf(type);
   }

   public Object newInstance(Class type) {
      Registration registration = this.getRegistration(type);
      ObjectInstantiator instantiator = registration.getInstantiator();
      if (instantiator == null) {
         instantiator = this.newInstantiator(type);
         registration.setInstantiator(instantiator);
      }

      return instantiator.newInstance();
   }

   public ObjectMap getContext() {
      if (this.context == null) {
         this.context = new ObjectMap();
      }

      return this.context;
   }

   public ObjectMap getGraphContext() {
      if (this.graphContext == null) {
         this.graphContext = new ObjectMap();
      }

      return this.graphContext;
   }

   public int getDepth() {
      return this.depth;
   }

   public IdentityMap getOriginalToCopyMap() {
      return this.originalToCopy;
   }

   public void setAutoReset(boolean autoReset) {
      this.autoReset = autoReset;
   }

   public void setMaxDepth(int maxDepth) {
      if (maxDepth <= 0) {
         throw new IllegalArgumentException("maxDepth must be > 0.");
      } else {
         this.maxDepth = maxDepth;
      }
   }

   public boolean isFinal(Class type) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         return type.isArray() ? Modifier.isFinal(Util.getElementClass(type).getModifiers()) : Modifier.isFinal(type.getModifiers());
      }
   }

   protected boolean isClosure(Class type) {
      if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         return type.getName().indexOf(47) >= 0;
      }
   }

   public GenericsResolver getGenericsResolver() {
      return this.genericsResolver;
   }

   public StreamFactory getStreamFactory() {
      return this.streamFactory;
   }

   public void setStreamFactory(StreamFactory streamFactory) {
      this.streamFactory = streamFactory;
   }

   /** @deprecated */
   @Deprecated
   public void setAsmEnabled(boolean flag) {
      this.fieldSerializerConfig.setUseAsm(flag);
   }

   /** @deprecated */
   @Deprecated
   public boolean getAsmEnabled() {
      return this.fieldSerializerConfig.isUseAsm();
   }

   static final class DefaultSerializerEntry {
      final Class type;
      final SerializerFactory serializerFactory;

      DefaultSerializerEntry(Class type, SerializerFactory serializerFactory) {
         this.type = type;
         this.serializerFactory = serializerFactory;
      }
   }

   public static class DefaultInstantiatorStrategy implements InstantiatorStrategy {
      private InstantiatorStrategy fallbackStrategy;

      public DefaultInstantiatorStrategy() {
      }

      public DefaultInstantiatorStrategy(InstantiatorStrategy fallbackStrategy) {
         this.fallbackStrategy = fallbackStrategy;
      }

      public void setFallbackInstantiatorStrategy(InstantiatorStrategy fallbackStrategy) {
         this.fallbackStrategy = fallbackStrategy;
      }

      public InstantiatorStrategy getFallbackInstantiatorStrategy() {
         return this.fallbackStrategy;
      }

      public ObjectInstantiator newInstantiatorOf(final Class type) {
         if (!Util.IS_ANDROID) {
            Class enclosingType = type.getEnclosingClass();
            boolean isNonStaticMemberClass = enclosingType != null && type.isMemberClass() && !Modifier.isStatic(type.getModifiers());
            if (!isNonStaticMemberClass) {
               try {
                  final ConstructorAccess access = ConstructorAccess.get(type);
                  return new ObjectInstantiator() {
                     public Object newInstance() {
                        try {
                           return access.newInstance();
                        } catch (Exception ex) {
                           throw new KryoException("Error constructing instance of class: " + Util.className(type), ex);
                        }
                     }
                  };
               } catch (Exception var7) {
               }
            }
         }

         try {
            final Constructor ctor;
            try {
               ctor = type.getConstructor((Class[])null);
            } catch (Exception var5) {
               ctor = type.getDeclaredConstructor((Class[])null);
               ctor.setAccessible(true);
            }

            return new ObjectInstantiator() {
               public Object newInstance() {
                  try {
                     return ctor.newInstance();
                  } catch (Exception ex) {
                     throw new KryoException("Error constructing instance of class: " + Util.className(type), ex);
                  }
               }
            };
         } catch (Exception var6) {
            if (this.fallbackStrategy == null) {
               if (type.isMemberClass() && !Modifier.isStatic(type.getModifiers())) {
                  throw new KryoException("Class cannot be created (non-static member class): " + Util.className(type));
               } else {
                  StringBuilder errorMessageSb = new StringBuilder("Class cannot be created (missing no-arg constructor): " + Util.className(type));
                  if (type.getSimpleName().equals("")) {
                     errorMessageSb.append("\n\tThis is an anonymous class, which is not serializable by default in Kryo. Possible solutions: ").append("1. Remove uses of anonymous classes, including double brace initialization, from the containing ").append("class. This is the safest solution, as anonymous classes don't have predictable names for serialization.").append("\n\t2. Register a FieldSerializer for the containing class and call ").append("FieldSerializer#setIgnoreSyntheticFields(false) on it. This is not safe but may be sufficient temporarily. ").append("Use at your own risk.");
                  }

                  throw new KryoException(errorMessageSb.toString());
               }
            } else {
               return this.fallbackStrategy.newInstantiatorOf(type);
            }
         }
      }
   }
}
