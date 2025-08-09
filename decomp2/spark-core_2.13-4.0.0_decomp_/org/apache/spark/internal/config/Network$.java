package org.apache.spark.internal.config;

import java.util.concurrent.TimeUnit;
import scala.runtime.BoxesRunTime;

public final class Network$ {
   public static final Network$ MODULE$ = new Network$();
   private static final ConfigEntry NETWORK_CRYPTO_SASL_FALLBACK = (new ConfigBuilder("spark.network.crypto.saslFallback")).version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(true));
   private static final ConfigEntry NETWORK_CRYPTO_ENABLED = (new ConfigBuilder("spark.network.crypto.enabled")).version("2.2.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry NETWORK_REMOTE_READ_NIO_BUFFER_CONVERSION = (new ConfigBuilder("spark.network.remoteReadNioBufferConversion")).version("2.4.0").booleanConf().createWithDefault(BoxesRunTime.boxToBoolean(false));
   private static final ConfigEntry NETWORK_TIMEOUT;
   private static final ConfigEntry NETWORK_TIMEOUT_INTERVAL;
   private static final OptionalConfigEntry RPC_ASK_TIMEOUT;
   private static final ConfigEntry RPC_CONNECT_THREADS;
   private static final ConfigEntry RPC_IO_NUM_CONNECTIONS_PER_PEER;
   private static final OptionalConfigEntry RPC_IO_THREADS;
   private static final OptionalConfigEntry RPC_LOOKUP_TIMEOUT;
   private static final ConfigEntry RPC_MESSAGE_MAX_SIZE;
   private static final OptionalConfigEntry RPC_NETTY_DISPATCHER_NUM_THREADS;

   static {
      NETWORK_TIMEOUT = (new ConfigBuilder("spark.network.timeout")).version("1.3.0").timeConf(TimeUnit.SECONDS).createWithDefaultString("120s");
      NETWORK_TIMEOUT_INTERVAL = (new ConfigBuilder("spark.network.timeoutInterval")).version("1.3.2").timeConf(TimeUnit.MILLISECONDS).createWithDefaultString(package$.MODULE$.STORAGE_BLOCKMANAGER_TIMEOUTINTERVAL().defaultValueString());
      RPC_ASK_TIMEOUT = (new ConfigBuilder("spark.rpc.askTimeout")).version("1.4.0").stringConf().createOptional();
      RPC_CONNECT_THREADS = (new ConfigBuilder("spark.rpc.connect.threads")).version("1.6.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(64));
      RPC_IO_NUM_CONNECTIONS_PER_PEER = (new ConfigBuilder("spark.rpc.io.numConnectionsPerPeer")).version("1.6.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(1));
      RPC_IO_THREADS = (new ConfigBuilder("spark.rpc.io.threads")).version("1.6.0").intConf().createOptional();
      RPC_LOOKUP_TIMEOUT = (new ConfigBuilder("spark.rpc.lookupTimeout")).version("1.4.0").stringConf().createOptional();
      RPC_MESSAGE_MAX_SIZE = (new ConfigBuilder("spark.rpc.message.maxSize")).version("2.0.0").intConf().createWithDefault(BoxesRunTime.boxToInteger(128));
      RPC_NETTY_DISPATCHER_NUM_THREADS = (new ConfigBuilder("spark.rpc.netty.dispatcher.numThreads")).version("1.6.0").intConf().createOptional();
   }

   public ConfigEntry NETWORK_CRYPTO_SASL_FALLBACK() {
      return NETWORK_CRYPTO_SASL_FALLBACK;
   }

   public ConfigEntry NETWORK_CRYPTO_ENABLED() {
      return NETWORK_CRYPTO_ENABLED;
   }

   public ConfigEntry NETWORK_REMOTE_READ_NIO_BUFFER_CONVERSION() {
      return NETWORK_REMOTE_READ_NIO_BUFFER_CONVERSION;
   }

   public ConfigEntry NETWORK_TIMEOUT() {
      return NETWORK_TIMEOUT;
   }

   public ConfigEntry NETWORK_TIMEOUT_INTERVAL() {
      return NETWORK_TIMEOUT_INTERVAL;
   }

   public OptionalConfigEntry RPC_ASK_TIMEOUT() {
      return RPC_ASK_TIMEOUT;
   }

   public ConfigEntry RPC_CONNECT_THREADS() {
      return RPC_CONNECT_THREADS;
   }

   public ConfigEntry RPC_IO_NUM_CONNECTIONS_PER_PEER() {
      return RPC_IO_NUM_CONNECTIONS_PER_PEER;
   }

   public OptionalConfigEntry RPC_IO_THREADS() {
      return RPC_IO_THREADS;
   }

   public OptionalConfigEntry RPC_LOOKUP_TIMEOUT() {
      return RPC_LOOKUP_TIMEOUT;
   }

   public ConfigEntry RPC_MESSAGE_MAX_SIZE() {
      return RPC_MESSAGE_MAX_SIZE;
   }

   public OptionalConfigEntry RPC_NETTY_DISPATCHER_NUM_THREADS() {
      return RPC_NETTY_DISPATCHER_NUM_THREADS;
   }

   private Network$() {
   }
}
