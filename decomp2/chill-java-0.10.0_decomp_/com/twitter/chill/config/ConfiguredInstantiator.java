package com.twitter.chill.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.chill.Base64;
import com.twitter.chill.KryoInstantiator;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ConfiguredInstantiator extends KryoInstantiator {
   protected final KryoInstantiator delegate;
   public static final String KEY = "com.twitter.chill.config.configuredinstantiator";
   private static CachedKryoInstantiator cachedKryoInstantiator = null;

   public ConfiguredInstantiator(Config var1) throws ConfigurationException {
      String var2 = var1.get("com.twitter.chill.config.configuredinstantiator");
      if (null == var2) {
         this.delegate = new KryoInstantiator();
      } else {
         String[] var3 = fastSplitKey(var2);
         if (var3 == null) {
            throw new ConfigurationException("Invalid Config Key: " + var1.get("com.twitter.chill.config.configuredinstantiator"));
         }

         Object var4 = null;

         try {
            var7 = reflect(Class.forName(var3[0], true, Thread.currentThread().getContextClassLoader()), var1);
         } catch (ClassNotFoundException var6) {
            throw new ConfigurationException("Could not find class for: " + var3[0], var6);
         }

         if (var3.length == 2) {
            this.delegate = fastDeserialize(var7.newKryo(), var3[1]);
            if (null == this.delegate) {
               throw new ConfigurationException("Null delegate from: " + var3[1]);
            }
         } else {
            this.delegate = var7;
         }
      }

   }

   public Kryo newKryo() {
      return this.delegate.newKryo();
   }

   public KryoInstantiator getDelegate() {
      return this.delegate;
   }

   public static void setReflect(Config var0, Class var1) {
      var0.set("com.twitter.chill.config.configuredinstantiator", var1.getName());
   }

   static KryoInstantiator reflect(Class var0, Config var1) throws ConfigurationException {
      try {
         try {
            return (KryoInstantiator)var0.getConstructor(Config.class).newInstance(var1);
         } catch (NoSuchMethodException var3) {
            return (KryoInstantiator)var0.newInstance();
         }
      } catch (InstantiationException var4) {
         throw new ConfigurationException(var4);
      } catch (IllegalAccessException var5) {
         throw new ConfigurationException(var5);
      } catch (InvocationTargetException var6) {
         throw new ConfigurationException(var6);
      }
   }

   public static void setSerialized(Config var0, KryoInstantiator var1) throws ConfigurationException {
      setSerialized(var0, KryoInstantiator.class, var1);
   }

   public static void setSerialized(Config var0, Class var1, KryoInstantiator var2) throws ConfigurationException {
      KryoInstantiator var3 = reflect(var1, var0);
      String var4 = serialize(var3.newKryo(), var2);
      KryoInstantiator var5 = deserialize(var3.newKryo(), var4);
      var5.newKryo();
      var0.set("com.twitter.chill.config.configuredinstantiator", var1.getName() + ":" + var4);
   }

   protected static KryoInstantiator deserialize(Kryo var0, String var1) throws ConfigurationException {
      try {
         return (KryoInstantiator)var0.readClassAndObject(new Input(Base64.decode(var1)));
      } catch (IOException var3) {
         throw new ConfigurationException("could not deserialize: " + var1, var3);
      }
   }

   protected static String serialize(Kryo var0, KryoInstantiator var1) {
      Output var2 = new Output(1024, 524288);
      var0.writeClassAndObject(var2, var1);
      return Base64.encodeBytes(var2.toBytes());
   }

   private static synchronized KryoInstantiator fastDeserialize(Kryo var0, String var1) throws ConfigurationException {
      if (cachedKryoInstantiator == null || !cachedKryoInstantiator.base64Value.equals(var1)) {
         cachedKryoInstantiator = new CachedKryoInstantiator(deserialize(var0, var1), var1);
      }

      return cachedKryoInstantiator.kryoInstantiator;
   }

   public static String[] fastSplitKey(String var0) {
      int var1 = var0.indexOf(58);
      if (-1 == var1) {
         return new String[]{var0};
      } else {
         int var2 = var0.indexOf(58, var1 + 1);
         return -1 != var2 ? null : new String[]{var0.substring(0, var1), var0.substring(var1 + 1)};
      }
   }

   private static class CachedKryoInstantiator {
      public final KryoInstantiator kryoInstantiator;
      public final String base64Value;

      public CachedKryoInstantiator(KryoInstantiator var1, String var2) {
         this.kryoInstantiator = var1;
         this.base64Value = var2;
      }
   }
}
