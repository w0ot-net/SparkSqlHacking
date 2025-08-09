package org.apache.spark.security;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.crypto.random.CryptoRandomFactory;
import org.apache.commons.crypto.stream.CryptoInputStream;
import org.apache.commons.crypto.stream.CryptoOutputStream;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.util.CryptoUtils;
import org.apache.spark.network.util.JavaUtils;
import org.slf4j.Logger;
import org.sparkproject.guava.io.ByteStreams;
import scala.Function0;
import scala.StringContext;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;

public final class CryptoStreamUtils$ implements Logging {
   public static final CryptoStreamUtils$ MODULE$ = new CryptoStreamUtils$();
   private static final int IV_LENGTH_IN_BYTES;
   private static final String SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      IV_LENGTH_IN_BYTES = 16;
      SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX = "spark.io.encryption.commons.config.";
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int IV_LENGTH_IN_BYTES() {
      return IV_LENGTH_IN_BYTES;
   }

   public String SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX() {
      return SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX;
   }

   public OutputStream createCryptoOutputStream(final OutputStream os, final SparkConf sparkConf, final byte[] key) {
      CryptoStreamUtils.CryptoParams params = new CryptoStreamUtils.CryptoParams(key, sparkConf);
      byte[] iv = this.createInitializationVector(params.conf());
      os.write(iv);
      return new CryptoOutputStream(params.transformation(), params.conf(), os, params.keySpec(), new IvParameterSpec(iv));
   }

   public WritableByteChannel createWritableChannel(final WritableByteChannel channel, final SparkConf sparkConf, final byte[] key) {
      CryptoStreamUtils.CryptoParams params = new CryptoStreamUtils.CryptoParams(key, sparkConf);
      byte[] iv = this.createInitializationVector(params.conf());
      CryptoStreamUtils.CryptoHelperChannel helper = new CryptoStreamUtils.CryptoHelperChannel(channel);
      helper.write(ByteBuffer.wrap(iv));
      return new CryptoOutputStream(params.transformation(), params.conf(), helper, params.keySpec(), new IvParameterSpec(iv));
   }

   public InputStream createCryptoInputStream(final InputStream is, final SparkConf sparkConf, final byte[] key) {
      byte[] iv = new byte[this.IV_LENGTH_IN_BYTES()];
      ByteStreams.readFully(is, iv);
      CryptoStreamUtils.CryptoParams params = new CryptoStreamUtils.CryptoParams(key, sparkConf);
      return new CryptoInputStream(params.transformation(), params.conf(), is, params.keySpec(), new IvParameterSpec(iv));
   }

   public ReadableByteChannel createReadableChannel(final ReadableByteChannel channel, final SparkConf sparkConf, final byte[] key) {
      byte[] iv = new byte[this.IV_LENGTH_IN_BYTES()];
      ByteBuffer buf = ByteBuffer.wrap(iv);
      JavaUtils.readFully(channel, buf);
      CryptoStreamUtils.CryptoParams params = new CryptoStreamUtils.CryptoParams(key, sparkConf);
      return new CryptoInputStream(params.transformation(), params.conf(), channel, params.keySpec(), new IvParameterSpec(iv));
   }

   public Properties toCryptoConf(final SparkConf conf) {
      return CryptoUtils.toCryptoConf(this.SPARK_IO_ENCRYPTION_COMMONS_CONFIG_PREFIX(), .MODULE$.MapHasAsJava(scala.Predef..MODULE$.wrapRefArray((Object[])conf.getAll()).toMap(scala..less.colon.less..MODULE$.refl())).asJava().entrySet());
   }

   public byte[] createKey(final SparkConf conf) {
      int keyLen = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.IO_ENCRYPTION_KEY_SIZE_BITS()));
      String ioKeyGenAlgorithm = (String)conf.get(package$.MODULE$.IO_ENCRYPTION_KEYGEN_ALGORITHM());
      KeyGenerator keyGen = KeyGenerator.getInstance(ioKeyGenAlgorithm);
      keyGen.init(keyLen);
      return keyGen.generateKey().getEncoded();
   }

   private byte[] createInitializationVector(final Properties properties) {
      byte[] iv = new byte[this.IV_LENGTH_IN_BYTES()];
      long initialIVStart = System.nanoTime();
      CryptoRandomFactory.getCryptoRandom(properties).nextBytes(iv);
      long initialIVFinish = System.nanoTime();
      long initialIVTime = TimeUnit.NANOSECONDS.toMillis(initialIVFinish - initialIVStart);
      if (initialIVTime > 2000L) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"It costs ", " milliseconds "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIME_UNITS..MODULE$, BoxesRunTime.boxToLong(initialIVTime))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to create the Initialization Vector used by CryptoStream"})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return iv;
   }

   private CryptoStreamUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
