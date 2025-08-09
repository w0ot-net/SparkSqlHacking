package org.apache.spark.internal.config;

import org.apache.spark.network.util.ByteUnit;
import scala.collection.immutable.Nil.;
import scala.runtime.BoxesRunTime;

public final class Kryo$ {
   public static final Kryo$ MODULE$ = new Kryo$();
   private static final ConfigEntry KRYO_REGISTRATION_REQUIRED = (new ConfigBuilder("spark.kryo.registrationRequired")).version("1.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry KRYO_USER_REGISTRATORS;
   private static final ConfigEntry KRYO_CLASSES_TO_REGISTER;
   private static final ConfigEntry KRYO_USE_UNSAFE;
   private static final ConfigEntry KRYO_USE_POOL;
   private static final ConfigEntry KRYO_REFERENCE_TRACKING;
   private static final ConfigEntry KRYO_SERIALIZER_BUFFER_SIZE;
   private static final ConfigEntry KRYO_SERIALIZER_MAX_BUFFER_SIZE;

   static {
      KRYO_USER_REGISTRATORS = (new ConfigBuilder("spark.kryo.registrator")).version("0.5.0").stringConf().toSequence().createWithDefault(.MODULE$);
      KRYO_CLASSES_TO_REGISTER = (new ConfigBuilder("spark.kryo.classesToRegister")).version("1.2.0").stringConf().toSequence().createWithDefault(.MODULE$);
      KRYO_USE_UNSAFE = (new ConfigBuilder("spark.kryo.unsafe")).version("2.1.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KRYO_USE_POOL = (new ConfigBuilder("spark.kryo.pool")).version("3.0.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KRYO_REFERENCE_TRACKING = (new ConfigBuilder("spark.kryo.referenceTracking")).version("0.8.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
      KRYO_SERIALIZER_BUFFER_SIZE = (new ConfigBuilder("spark.kryoserializer.buffer")).version("1.4.0").bytesConf(ByteUnit.KiB).createWithDefaultString("64k");
      KRYO_SERIALIZER_MAX_BUFFER_SIZE = (new ConfigBuilder("spark.kryoserializer.buffer.max")).version("1.4.0").bytesConf(ByteUnit.MiB).createWithDefaultString("64m");
   }

   public ConfigEntry KRYO_REGISTRATION_REQUIRED() {
      return KRYO_REGISTRATION_REQUIRED;
   }

   public ConfigEntry KRYO_USER_REGISTRATORS() {
      return KRYO_USER_REGISTRATORS;
   }

   public ConfigEntry KRYO_CLASSES_TO_REGISTER() {
      return KRYO_CLASSES_TO_REGISTER;
   }

   public ConfigEntry KRYO_USE_UNSAFE() {
      return KRYO_USE_UNSAFE;
   }

   public ConfigEntry KRYO_USE_POOL() {
      return KRYO_USE_POOL;
   }

   public ConfigEntry KRYO_REFERENCE_TRACKING() {
      return KRYO_REFERENCE_TRACKING;
   }

   public ConfigEntry KRYO_SERIALIZER_BUFFER_SIZE() {
      return KRYO_SERIALIZER_BUFFER_SIZE;
   }

   public ConfigEntry KRYO_SERIALIZER_MAX_BUFFER_SIZE() {
      return KRYO_SERIALIZER_MAX_BUFFER_SIZE;
   }

   private Kryo$() {
   }
}
