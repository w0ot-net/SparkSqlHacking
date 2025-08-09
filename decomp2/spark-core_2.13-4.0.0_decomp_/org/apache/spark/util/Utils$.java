package org.apache.spark.util;

import io.netty.channel.unix.Errors;
import jakarta.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.io.StringWriter;
import java.lang.Thread.State;
import java.lang.invoke.SerializedLambda;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.PlatformManagedObject;
import java.lang.management.ThreadInfo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.BindException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.SplittableCompressionCodec;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.RunJar;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.spark.ReadOnlySparkConf;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Streaming$;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.internal.config.UI$;
import org.apache.spark.internal.config.Worker$;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.security.GroupMappingServiceProvider;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.SerializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.status.api.v1.StackTrace;
import org.apache.spark.status.api.v1.ThreadStackTrace;
import org.apache.spark.util.io.ChunkedByteBufferOutputStream;
import org.slf4j.Logger;
import org.sparkproject.guava.cache.CacheBuilder;
import org.sparkproject.guava.cache.CacheLoader;
import org.sparkproject.guava.cache.LoadingCache;
import org.sparkproject.guava.collect.Interner;
import org.sparkproject.guava.collect.Interners;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.net.InetAddresses;
import org.sparkproject.jetty.util.MultiException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.NotImplementedError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LinearSeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StringOps.;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LazyBoolean;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;
import scala.util.control.ControlThrowable;
import scala.util.matching.Regex;

public final class Utils$ implements SparkClassUtils, SparkEnvUtils, SparkErrorUtils, SparkFileUtils, SparkSerDeUtils, SparkStreamUtils, SparkStringUtils {
   public static final Utils$ MODULE$ = new Utils$();
   private static InetAddress localIpAddress;
   private static Tuple2 getLogContext;
   private static boolean isG1GC;
   private static final SparkUncaughtExceptionHandler sparkUncaughtExceptionHandler;
   private static volatile String cachedLocalDir;
   private static final int DEFAULT_DRIVER_MEM_MB;
   private static final int MAX_DIR_CREATION_ATTEMPTS;
   private static volatile String[] localRootDirs;
   private static final String LOCAL_SCHEME;
   private static final Interner weakStringInterner;
   private static final Regex PATTERN_FOR_COMMAND_LINE_ARG;
   private static final int COPY_BUFFER_LEN;
   private static final ThreadLocal copyBuffer;
   private static Option customHostname;
   private static final ConcurrentHashMap hostPortParseResults;
   private static final long[] siByteSizes;
   private static final String[] siByteSuffixes;
   private static final String TRY_WITH_CALLER_STACKTRACE_FULL_STACKTRACE;
   private static final Regex SPARK_CORE_CLASS_REGEX;
   private static final Regex SPARK_SQL_CLASS_REGEX;
   private static LoadingCache compressedLogFileLengthCache;
   private static final boolean isWindows;
   private static final boolean isMac;
   private static final boolean isJavaVersionAtLeast21;
   private static final boolean isMacOnAppleSilicon;
   private static final boolean preferIPv6;
   private static final Regex windowsDrive;
   private static final Set EMPTY_USER_GROUPS;
   private static final String BACKUP_STANDALONE_MASTER_PREFIX;
   private static final String REDACTION_REPLACEMENT_TEXT;
   private static final Regex fullWidthRegex;
   private static Random random;
   private static transient Logger org$apache$spark$internal$Logging$$log_;
   private static volatile byte bitmap$0;

   static {
      Logging.$init$(MODULE$);
      SparkClassUtils.$init$(MODULE$);
      SparkEnvUtils.$init$(MODULE$);
      SparkErrorUtils.$init$(MODULE$);
      SparkFileUtils.$init$(MODULE$);
      SparkSerDeUtils.$init$(MODULE$);
      SparkStreamUtils.$init$(MODULE$);
      SparkStringUtils.$init$(MODULE$);
      sparkUncaughtExceptionHandler = new SparkUncaughtExceptionHandler(SparkUncaughtExceptionHandler$.MODULE$.$lessinit$greater$default$1());
      cachedLocalDir = "";
      DEFAULT_DRIVER_MEM_MB = 1024;
      MAX_DIR_CREATION_ATTEMPTS = 10;
      localRootDirs = null;
      LOCAL_SCHEME = "local";
      weakStringInterner = Interners.newWeakInterner();
      PATTERN_FOR_COMMAND_LINE_ARG = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("-D(.+?)=(.+)"));
      COPY_BUFFER_LEN = 1024;
      copyBuffer = ThreadLocal.withInitial(() -> new byte[MODULE$.COPY_BUFFER_LEN()]);
      customHostname = scala.sys.package..MODULE$.env().get("SPARK_LOCAL_HOSTNAME");
      hostPortParseResults = new ConcurrentHashMap();
      siByteSizes = new long[]{1152921504606846976L, 1125899906842624L, 1099511627776L, 1073741824L, 1048576L, 1024L, 1L};
      siByteSuffixes = (String[])((Object[])(new String[]{"EiB", "PiB", "TiB", "GiB", "MiB", "KiB", "B"}));
      TRY_WITH_CALLER_STACKTRACE_FULL_STACKTRACE = "Full stacktrace of original doTryWithCallerStacktrace caller";
      SPARK_CORE_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^org\\.apache\\.spark(\\.api\\.java)?(\\.util)?(\\.rdd)?(\\.broadcast)?\\.[A-Z]"));
      SPARK_SQL_CLASS_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^org\\.apache\\.spark\\.sql.*"));
      compressedLogFileLengthCache = null;
      isWindows = SystemUtils.IS_OS_WINDOWS;
      isMac = SystemUtils.IS_OS_MAC_OSX;
      isJavaVersionAtLeast21 = SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_21);
      isMacOnAppleSilicon = SystemUtils.IS_OS_MAC_OSX && SystemUtils.OS_ARCH.equals("aarch64");
      preferIPv6 = "true".equals(System.getProperty("java.net.preferIPv6Addresses"));
      windowsDrive = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("([a-zA-Z])"));
      EMPTY_USER_GROUPS = scala.Predef..MODULE$.Set().empty();
      BACKUP_STANDALONE_MASTER_PREFIX = "Current state is not alive";
      REDACTION_REPLACEMENT_TEXT = "*********(redacted)";
      fullWidthRegex = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("[ᄀ-ᅟ⺀-\ua4cf가-힣豈-\ufaff︐-︙︰-\ufe6f\uff00-｠￠-￦]"));
   }

   public Seq stringToSeq(final String str) {
      return SparkStringUtils.stringToSeq$(this, str);
   }

   public long copyStream(final InputStream in, final OutputStream out, final boolean closeStreams, final boolean transferToEnabled) {
      return SparkStreamUtils.copyStream$(this, in, out, closeStreams, transferToEnabled);
   }

   public boolean copyStream$default$3() {
      return SparkStreamUtils.copyStream$default$3$(this);
   }

   public boolean copyStream$default$4() {
      return SparkStreamUtils.copyStream$default$4$(this);
   }

   public void copyFileStreamNIO(final FileChannel input, final WritableByteChannel output, final long startPosition, final long bytesToCopy) {
      SparkStreamUtils.copyFileStreamNIO$(this, input, output, startPosition, bytesToCopy);
   }

   public byte[] serialize(final Object o) {
      return SparkSerDeUtils.serialize$(this, o);
   }

   public Object deserialize(final byte[] bytes) {
      return SparkSerDeUtils.deserialize$(this, bytes);
   }

   public Object deserialize(final byte[] bytes, final ClassLoader loader) {
      return SparkSerDeUtils.deserialize$(this, bytes, loader);
   }

   public URI resolveURI(final String path) {
      return SparkFileUtils.resolveURI$(this, path);
   }

   public File[] recursiveList(final File f) {
      return SparkFileUtils.recursiveList$(this, f);
   }

   public boolean createDirectory(final File dir) {
      return SparkFileUtils.createDirectory$(this, dir);
   }

   public File createDirectory(final String root, final String namePrefix) {
      return SparkFileUtils.createDirectory$(this, root, namePrefix);
   }

   public String createDirectory$default$2() {
      return SparkFileUtils.createDirectory$default$2$(this);
   }

   public File createTempDir() {
      return SparkFileUtils.createTempDir$(this);
   }

   public Object tryOrIOException(final Function0 block) {
      return SparkErrorUtils.tryOrIOException$(this, block);
   }

   public Object tryWithResource(final Function0 createResource, final Function1 f) {
      return SparkErrorUtils.tryWithResource$(this, createResource, f);
   }

   public Object tryInitializeResource(final Function0 createResource, final Function1 initialize) {
      return SparkErrorUtils.tryInitializeResource$(this, createResource, initialize);
   }

   public Object tryWithSafeFinally(final Function0 block, final Function0 finallyBlock) {
      return SparkErrorUtils.tryWithSafeFinally$(this, block, finallyBlock);
   }

   public String stackTraceToString(final Throwable t) {
      return SparkErrorUtils.stackTraceToString$(this, t);
   }

   public boolean isTesting() {
      return SparkEnvUtils.isTesting$(this);
   }

   public ClassLoader getSparkClassLoader() {
      return SparkClassUtils.getSparkClassLoader$(this);
   }

   public ClassLoader getContextOrSparkClassLoader() {
      return SparkClassUtils.getContextOrSparkClassLoader$(this);
   }

   public Class classForName(final String className, final boolean initialize, final boolean noSparkClassLoader) {
      return SparkClassUtils.classForName$(this, className, initialize, noSparkClassLoader);
   }

   public boolean classForName$default$2() {
      return SparkClassUtils.classForName$default$2$(this);
   }

   public boolean classForName$default$3() {
      return SparkClassUtils.classForName$default$3$(this);
   }

   public boolean classIsLoadable(final String clazz) {
      return SparkClassUtils.classIsLoadable$(this, clazz);
   }

   public boolean classIsLoadableAndAssignableFrom(final String clazz, final Class targetClass) {
      return SparkClassUtils.classIsLoadableAndAssignableFrom$(this, clazz, targetClass);
   }

   public String getFormattedClassName(final Object obj) {
      return SparkClassUtils.getFormattedClassName$(this, obj);
   }

   public String getSimpleName(final Class cls) {
      return SparkClassUtils.getSimpleName$(this, cls);
   }

   public final String stripDollars(final String s) {
      return SparkClassUtils.stripDollars$(this, s);
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

   public Random random() {
      return random;
   }

   public void org$apache$spark$util$SparkClassUtils$_setter_$random_$eq(final Random x$1) {
      random = x$1;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private SparkUncaughtExceptionHandler sparkUncaughtExceptionHandler() {
      return sparkUncaughtExceptionHandler;
   }

   private String cachedLocalDir() {
      return cachedLocalDir;
   }

   private void cachedLocalDir_$eq(final String x$1) {
      cachedLocalDir = x$1;
   }

   public int DEFAULT_DRIVER_MEM_MB() {
      return DEFAULT_DRIVER_MEM_MB;
   }

   public int MAX_DIR_CREATION_ATTEMPTS() {
      return MAX_DIR_CREATION_ATTEMPTS;
   }

   private String[] localRootDirs() {
      return localRootDirs;
   }

   private void localRootDirs_$eq(final String[] x$1) {
      localRootDirs = x$1;
   }

   public String LOCAL_SCHEME() {
      return LOCAL_SCHEME;
   }

   private Interner weakStringInterner() {
      return weakStringInterner;
   }

   private Regex PATTERN_FOR_COMMAND_LINE_ARG() {
      return PATTERN_FOR_COMMAND_LINE_ARG;
   }

   private int COPY_BUFFER_LEN() {
      return COPY_BUFFER_LEN;
   }

   private ThreadLocal copyBuffer() {
      return copyBuffer;
   }

   public long deserializeLongValue(final byte[] bytes) {
      long result = (long)bytes[7] & 255L;
      result += ((long)bytes[6] & 255L) << 8;
      result += ((long)bytes[5] & 255L) << 16;
      result += ((long)bytes[4] & 255L) << 24;
      result += ((long)bytes[3] & 255L) << 32;
      result += ((long)bytes[2] & 255L) << 40;
      result += ((long)bytes[1] & 255L) << 48;
      return result + (((long)bytes[0] & 255L) << 56);
   }

   public void serializeViaNestedStream(final OutputStream os, final SerializerInstance ser, final Function1 f) {
      SerializationStream osWrapper = ser.serializeStream(new OutputStream(os) {
         private final OutputStream os$1;

         public void write(final int b) {
            this.os$1.write(b);
         }

         public void write(final byte[] b, final int off, final int len) {
            this.os$1.write(b, off, len);
         }

         public {
            this.os$1 = os$1;
         }
      });

      try {
         f.apply(osWrapper);
      } finally {
         osWrapper.close();
      }

   }

   public void deserializeViaNestedStream(final InputStream is, final SerializerInstance ser, final Function1 f) {
      DeserializationStream isWrapper = ser.deserializeStream(new InputStream(is) {
         private final InputStream is$1;

         public int read() {
            return this.is$1.read();
         }

         public int read(final byte[] b, final int off, final int len) {
            return this.is$1.read(b, off, len);
         }

         public {
            this.is$1 = is$1;
         }
      });

      try {
         f.apply(isWrapper);
      } finally {
         isWrapper.close();
      }

   }

   public String weakIntern(final String s) {
      return (String)this.weakStringInterner().intern(s);
   }

   public Object withContextClassLoader(final ClassLoader ctxClassLoader, final Function0 fn) {
      ClassLoader oldClassLoader = Thread.currentThread().getContextClassLoader();

      Object var10000;
      try {
         Thread.currentThread().setContextClassLoader(ctxClassLoader);
         var10000 = fn.apply();
      } finally {
         Thread.currentThread().setContextClassLoader(oldClassLoader);
      }

      return var10000;
   }

   private void writeByteBufferImpl(final ByteBuffer bb, final Function3 writer) {
      if (bb.hasArray()) {
         writer.apply(bb.array(), BoxesRunTime.boxToInteger(bb.arrayOffset() + bb.position()), BoxesRunTime.boxToInteger(bb.remaining()));
      } else {
         byte[] buffer = (byte[])this.copyBuffer().get();
         int originalPosition = bb.position();

         for(int bytesToCopy = Math.min(bb.remaining(), this.COPY_BUFFER_LEN()); bytesToCopy > 0; bytesToCopy = Math.min(bb.remaining(), this.COPY_BUFFER_LEN())) {
            bb.get(buffer, 0, bytesToCopy);
            writer.apply(buffer, BoxesRunTime.boxToInteger(0), BoxesRunTime.boxToInteger(bytesToCopy));
         }

         bb.position(originalPosition);
      }
   }

   public void writeByteBuffer(final ByteBuffer bb, final DataOutput out) {
      this.writeByteBufferImpl(bb, (x$1, x$2, x$3) -> {
         $anonfun$writeByteBuffer$1(out, x$1, BoxesRunTime.unboxToInt(x$2), BoxesRunTime.unboxToInt(x$3));
         return BoxedUnit.UNIT;
      });
   }

   public void writeByteBuffer(final ByteBuffer bb, final OutputStream out) {
      this.writeByteBufferImpl(bb, (x$1, x$2, x$3) -> {
         $anonfun$writeByteBuffer$2(out, x$1, BoxesRunTime.unboxToInt(x$2), BoxesRunTime.unboxToInt(x$3));
         return BoxedUnit.UNIT;
      });
   }

   public boolean chmod700(final File file) {
      return file.setReadable(false, false) && file.setReadable(true, true) && file.setWritable(false, false) && file.setWritable(true, true) && file.setExecutable(false, false) && file.setExecutable(true, true);
   }

   public File createTempDir(final String root, final String namePrefix) {
      File dir = this.createDirectory(root, namePrefix);
      ShutdownHookManager$.MODULE$.registerShutdownDeleteDir(dir);
      return dir;
   }

   public String createTempDir$default$1() {
      return System.getProperty("java.io.tmpdir");
   }

   public String createTempDir$default$2() {
      return "spark";
   }

   public InputStream copyStreamUpTo(final InputStream in, final long maxSize) {
      LongRef count = LongRef.create(0L);
      ChunkedByteBufferOutputStream out = new ChunkedByteBufferOutputStream(65536, (x$1) -> $anonfun$copyStreamUpTo$1(BoxesRunTime.unboxToInt(x$1)));
      boolean fullyCopied = BoxesRunTime.unboxToBoolean(this.tryWithSafeFinally((JFunction0.mcZ.sp)() -> {
         long bufSize = Math.min(8192L, maxSize);
         byte[] buf = new byte[(int)bufSize];
         int n = 0;

         while(n != -1 && count.elem < maxSize) {
            n = in.read(buf, 0, (int)Math.min(maxSize - count.elem, bufSize));
            if (n != -1) {
               out.write(buf, 0, n);
               count.elem += (long)n;
            }
         }

         return count.elem < maxSize;
      }, (JFunction0.mcV.sp)() -> {
         try {
            if (count.elem < maxSize) {
               in.close();
            }
         } finally {
            out.close();
         }

      }));
      return (InputStream)(fullyCopied ? out.toChunkedByteBuffer().toInputStream(true) : new SequenceInputStream(out.toChunkedByteBuffer().toInputStream(true), in));
   }

   public String encodeFileNameToURIRawPath(final String fileName) {
      scala.Predef..MODULE$.require(!fileName.contains("/") && !fileName.contains("\\"));
      return this.encodeRelativeUnixPathToURIRawPath(fileName);
   }

   public String encodeRelativeUnixPathToURIRawPath(final String path) {
      scala.Predef..MODULE$.require(!path.startsWith("/") && !path.contains("\\"));
      return (new URI("file", (String)null, "localhost", -1, "/" + path, (String)null, (String)null)).getRawPath().substring(1);
   }

   public String decodeFileNameInURI(final URI uri) {
      String rawPath = uri.getRawPath();
      String rawFileName = (String)scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.refArrayOps((Object[])rawPath.split("/")));
      return (new URI("file:///" + rawFileName)).getPath().substring(1);
   }

   public File fetchFile(final String url, final File targetDir, final SparkConf conf, final Configuration hadoopConf, final long timestamp, final boolean useCache, final boolean shouldUntar) {
      String fileName = this.decodeFileNameInURI(new URI(url));
      File targetFile = new File(targetDir, fileName);
      boolean fetchCacheEnabled = conf.getBoolean("spark.files.useFetchCache", true);
      if (useCache && fetchCacheEnabled) {
         int var10000 = url.hashCode();
         String cachedFileName = "" + var10000 + timestamp + "_cache";
         var10000 = url.hashCode();
         String lockFileName = "" + var10000 + timestamp + "_lock";
         if (this.cachedLocalDir().isEmpty()) {
            synchronized(this){}

            try {
               if (this.cachedLocalDir().isEmpty()) {
                  this.cachedLocalDir_$eq(this.getLocalDir(conf));
               }
            } catch (Throwable var26) {
               throw var26;
            }
         }

         File localDir = new File(this.cachedLocalDir());
         File lockFile = new File(localDir, lockFileName);
         FileChannel lockFileChannel = (new RandomAccessFile(lockFile, "rw")).getChannel();
         FileLock lock = lockFileChannel.lock();
         File cachedFile = new File(localDir, cachedFileName);

         try {
            if (!cachedFile.exists()) {
               this.doFetchFile(url, localDir, cachedFileName, conf, hadoopConf);
            } else {
               BoxedUnit var28 = BoxedUnit.UNIT;
            }
         } finally {
            lock.release();
            lockFileChannel.close();
         }

         this.copyFile(url, cachedFile, targetFile, conf.getBoolean("spark.files.overwrite", false), this.copyFile$default$5());
         BoxedUnit var29 = BoxedUnit.UNIT;
      } else {
         this.doFetchFile(url, targetDir, fileName, conf, hadoopConf);
      }

      if (shouldUntar) {
         if (!fileName.endsWith(".tar.gz") && !fileName.endsWith(".tgz")) {
            if (fileName.endsWith(".tar")) {
               this.logWarning((Function0)(() -> "Untarring behavior will be deprecated at spark.files and SparkContext.addFile. Consider using spark.archives or SparkContext.addArchive instead."));
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Untarring ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, fileName)})))));
               this.executeAndGetOutput(new scala.collection.immutable..colon.colon("tar", new scala.collection.immutable..colon.colon("-xf", new scala.collection.immutable..colon.colon(fileName, scala.collection.immutable.Nil..MODULE$))), targetDir, this.executeAndGetOutput$default$3(), this.executeAndGetOutput$default$4());
            } else {
               BoxedUnit var30 = BoxedUnit.UNIT;
            }
         } else {
            this.logWarning((Function0)(() -> "Untarring behavior will be deprecated at spark.files and SparkContext.addFile. Consider using spark.archives or SparkContext.addArchive instead."));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Untarring ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, fileName)})))));
            this.executeAndGetOutput(new scala.collection.immutable..colon.colon("tar", new scala.collection.immutable..colon.colon("-xzf", new scala.collection.immutable..colon.colon(fileName, scala.collection.immutable.Nil..MODULE$))), targetDir, this.executeAndGetOutput$default$3(), this.executeAndGetOutput$default$4());
         }
      } else {
         BoxedUnit var31 = BoxedUnit.UNIT;
      }

      FileUtil.chmod(targetFile.getAbsolutePath(), "a+x");
      if (this.isWindows()) {
         BoxesRunTime.boxToInteger(FileUtil.chmod(targetFile.getAbsolutePath(), "u+r"));
      } else {
         BoxedUnit var32 = BoxedUnit.UNIT;
      }

      return targetFile;
   }

   public boolean fetchFile$default$7() {
      return true;
   }

   public void unpack(final File source, final File dest) {
      if (!source.exists()) {
         throw new FileNotFoundException(source.getAbsolutePath());
      } else {
         String lowerSrc = StringUtils.toLowerCase(source.getName());
         if (lowerSrc.endsWith(".jar")) {
            RunJar.unJar(source, dest, RunJar.MATCH_ANY);
         } else if (lowerSrc.endsWith(".zip")) {
            FileUtil.unZip(source, dest);
         } else if (!lowerSrc.endsWith(".tar.gz") && !lowerSrc.endsWith(".tgz")) {
            if (lowerSrc.endsWith(".tar")) {
               this.unTarUsingJava(source, dest);
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot unpack ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, source)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"just copying it to ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME2..MODULE$, dest)}))))));
               this.copyRecursive(source, dest);
            }
         } else {
            FileUtil.unTar(source, dest);
         }
      }
   }

   private void unTarUsingJava(final File source, final File dest) {
      if (!dest.mkdirs() && !dest.isDirectory()) {
         throw new IOException("Mkdirs failed to create " + dest);
      } else {
         try {
            Method mth = FileUtil.class.getDeclaredMethod("unTarUsingJava", File.class, File.class, Boolean.TYPE);
            mth.setAccessible(true);
            mth.invoke((Object)null, source, dest, Boolean.FALSE);
         } catch (Throwable var8) {
            if (var8 instanceof InvocationTargetException) {
               InvocationTargetException var7 = (InvocationTargetException)var8;
               if (var7.getCause() != null) {
                  throw var7.getCause();
               }
            }

            throw var8;
         }
      }
   }

   public Tuple2 timeTakenMs(final Function0 body) {
      long startTime = System.nanoTime();
      Object result = body.apply();
      long endTime = System.nanoTime();
      return new Tuple2(result, BoxesRunTime.boxToLong(scala.math.package..MODULE$.max(TimeUnit.NANOSECONDS.toMillis(endTime - startTime), 0L)));
   }

   private void downloadFile(final String url, final InputStream in, final File destFile, final boolean fileOverwrite) {
      File tempFile = File.createTempFile("fetchFileTemp", (String)null, new File(destFile.getParentFile().getAbsolutePath()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Fetching ", " to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URL..MODULE$, url), new MDC(org.apache.spark.internal.LogKeys.FILE_ABSOLUTE_PATH..MODULE$, tempFile)})))));

      try {
         FileOutputStream out = new FileOutputStream(tempFile);
         this.copyStream(in, out, true, this.copyStream$default$4());
         this.copyFile(url, tempFile, destFile, fileOverwrite, true);
      } finally {
         if (tempFile.exists()) {
            tempFile.delete();
         }

      }

   }

   private void copyFile(final String url, final File sourceFile, final File destFile, final boolean fileOverwrite, final boolean removeSourceFile) {
      if (destFile.exists()) {
         if (this.filesEqualRecursive(sourceFile, destFile)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " has been previously"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOURCE_PATH..MODULE$, sourceFile.getAbsolutePath())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" copied to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, destFile.getAbsolutePath())}))))));
            return;
         }

         if (!fileOverwrite) {
            throw new SparkException("File " + destFile + " exists and does not match contents of " + url);
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"File ", " exists and does not match contents of"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, destFile)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ", replacing it with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URL..MODULE$, url), new MDC(org.apache.spark.internal.LogKeys.URL2..MODULE$, url)}))))));
         if (!destFile.delete()) {
            throw new SparkException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Failed to delete %s while attempting to overwrite it with %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{destFile.getAbsolutePath(), sourceFile.getAbsolutePath()})));
         }
      }

      if (removeSourceFile) {
         Files.move(sourceFile.toPath(), destFile.toPath());
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Copying ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SOURCE_PATH..MODULE$, sourceFile.getAbsolutePath())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DESTINATION_PATH..MODULE$, destFile.getAbsolutePath())}))))));
         this.copyRecursive(sourceFile, destFile);
      }
   }

   private boolean copyFile$default$5() {
      return false;
   }

   private boolean filesEqualRecursive(final File file1, final File file2) {
      if (file1.isDirectory() && file2.isDirectory()) {
         File[] subfiles1 = file1.listFiles();
         File[] subfiles2 = file2.listFiles();
         return scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])subfiles1)) != scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])subfiles2)) ? false : scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])subfiles1), (x$1) -> x$1.getName(), scala.math.Ordering.String..MODULE$)), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps((Object[])subfiles2), (x$2) -> x$2.getName(), scala.math.Ordering.String..MODULE$)))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$filesEqualRecursive$3(x0$1)));
      } else {
         return file1.isFile() && file2.isFile() ? org.sparkproject.guava.io.Files.equal(file1, file2) : false;
      }
   }

   private void copyRecursive(final File source, final File dest) {
      if (source.isDirectory()) {
         if (!dest.mkdir()) {
            throw new IOException("Failed to create directory " + dest.getPath());
         } else {
            File[] subfiles = source.listFiles();
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])subfiles), (f) -> {
               $anonfun$copyRecursive$1(dest, f);
               return BoxedUnit.UNIT;
            });
         }
      } else {
         Files.copy(source.toPath(), dest.toPath());
      }
   }

   public File doFetchFile(final String url, final File targetDir, final String filename, final SparkConf conf, final Configuration hadoopConf) {
      File targetFile;
      boolean fileOverwrite;
      label66: {
         targetFile = new File(targetDir, filename);
         URI uri = new URI(url);
         fileOverwrite = conf.getBoolean("spark.files.overwrite", false);
         String var9 = (String)scala.Option..MODULE$.apply(uri.getScheme()).getOrElse(() -> "file");
         switch (var9 == null ? 0 : var9.hashCode()) {
            case 101730:
               if ("ftp".equals(var9)) {
                  break label66;
               }
               break;
            case 3143036:
               if ("file".equals(var9)) {
                  File sourceFile = uri.isAbsolute() ? new File(uri) : new File(uri.getPath());
                  this.copyFile(url, sourceFile, targetFile, fileOverwrite, this.copyFile$default$5());
                  return targetFile;
               }
               break;
            case 3213448:
               if ("http".equals(var9)) {
                  break label66;
               }
               break;
            case 99617003:
               if ("https".equals(var9)) {
                  break label66;
               }
               break;
            case 109638365:
               if ("spark".equals(var9)) {
                  if (SparkEnv$.MODULE$.get() == null) {
                     throw new IllegalStateException("Cannot retrieve files with 'spark' scheme without an active SparkEnv.");
                  }

                  ReadableByteChannel source = SparkEnv$.MODULE$.get().rpcEnv().openChannel(url);
                  InputStream is = Channels.newInputStream(source);
                  this.downloadFile(url, is, targetFile, fileOverwrite);
                  return targetFile;
               }
         }

         FileSystem fs = this.getHadoopFileSystem(uri, hadoopConf);
         Path path = new Path(uri);
         this.fetchHcfsFile(path, targetDir, fs, conf, hadoopConf, fileOverwrite, new Some(filename));
         return targetFile;
      }

      URLConnection uc = (new URI(url)).toURL().openConnection();
      int timeoutMs = (int)conf.getTimeAsSeconds("spark.files.fetchTimeout", "60s") * 1000;
      uc.setConnectTimeout(timeoutMs);
      uc.setReadTimeout(timeoutMs);
      uc.connect();
      InputStream in = uc.getInputStream();
      this.downloadFile(url, in, targetFile, fileOverwrite);
      return targetFile;
   }

   public void fetchHcfsFile(final Path path, final File targetDir, final FileSystem fs, final SparkConf conf, final Configuration hadoopConf, final boolean fileOverwrite, final Option filename) {
      if (!targetDir.exists() && !targetDir.mkdir()) {
         throw new IOException("Failed to create directory " + targetDir.getPath());
      } else {
         File dest = new File(targetDir, (String)filename.getOrElse(() -> path.getName()));
         if (fs.getFileStatus(path).isFile()) {
            FSDataInputStream in = fs.open(path);

            try {
               this.downloadFile(path.toString(), in, dest, fileOverwrite);
            } finally {
               in.close();
            }

         } else {
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])fs.listStatus(path)), (fileStatus) -> {
               $anonfun$fetchHcfsFile$2(dest, fs, conf, hadoopConf, fileOverwrite, fileStatus);
               return BoxedUnit.UNIT;
            });
         }
      }
   }

   public Option fetchHcfsFile$default$7() {
      return scala.None..MODULE$;
   }

   public void validateURL(final URI uri) throws MalformedURLException {
      String var2 = (String)scala.Option..MODULE$.apply(uri.getScheme()).getOrElse(() -> "file");
      switch (var2 == null ? 0 : var2.hashCode()) {
         case 101730:
            if (!"ftp".equals(var2)) {
               return;
            }
            break;
         case 3213448:
            if (!"http".equals(var2)) {
               return;
            }
            break;
         case 99617003:
            if (!"https".equals(var2)) {
               return;
            }
            break;
         default:
            return;
      }

      try {
         uri.toURL();
      } catch (MalformedURLException var5) {
         MalformedURLException ex = new MalformedURLException("URI (" + uri.toString() + ") is not a valid URL.");
         ex.initCause(var5);
         throw ex;
      }

   }

   public String getLocalDir(final SparkConf conf) {
      String[] localRootDirs = this.getOrCreateLocalRootDirs(conf);
      if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])localRootDirs))) {
         String[] configuredLocalDirs = this.getConfiguredLocalDirs(conf);
         ArraySeq.ofRef var10002 = scala.Predef..MODULE$.wrapRefArray((Object[])configuredLocalDirs);
         throw new IOException("Failed to get a temp directory under [" + var10002.mkString(",") + "].");
      } else {
         return localRootDirs[scala.util.Random..MODULE$.nextInt(localRootDirs.length)];
      }
   }

   public boolean isRunningInYarnContainer(final SparkConf conf) {
      return conf.getenv("CONTAINER_ID") != null;
   }

   public boolean isInRunningSparkTask() {
      return TaskContext$.MODULE$.get() != null;
   }

   public String[] getOrCreateLocalRootDirs(final SparkConf conf) {
      if (this.localRootDirs() == null) {
         synchronized(this){}

         try {
            if (this.localRootDirs() == null) {
               this.localRootDirs_$eq(this.getOrCreateLocalRootDirsImpl(conf));
            }
         } catch (Throwable var4) {
            throw var4;
         }
      }

      return this.localRootDirs();
   }

   public String[] getConfiguredLocalDirs(final SparkConf conf) {
      if (this.isRunningInYarnContainer(conf)) {
         return (String[])this.randomizeInPlace(this.getYarnLocalDirs(conf).split(","), this.randomizeInPlace$default$2());
      } else if (conf.getenv("SPARK_EXECUTOR_DIRS") != null) {
         return conf.getenv("SPARK_EXECUTOR_DIRS").split(File.pathSeparator);
      } else {
         return conf.getenv("SPARK_LOCAL_DIRS") != null ? conf.getenv("SPARK_LOCAL_DIRS").split(",") : conf.get("spark.local.dir", System.getProperty("java.io.tmpdir")).split(",");
      }
   }

   private String[] getOrCreateLocalRootDirsImpl(final SparkConf conf) {
      String[] configuredLocalDirs = this.getConfiguredLocalDirs(conf);
      String[] uris = (String[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])configuredLocalDirs), (root) -> BoxesRunTime.boxToBoolean($anonfun$getOrCreateLocalRootDirsImpl$1(root)));
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps((Object[])uris))) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The configured local directories are not expected to be URIs; "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"however, got suspicious values ["})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "]. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URIS..MODULE$, scala.Predef..MODULE$.wrapRefArray((Object[])uris).mkString(", "))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please check your configured local directories."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return (String[])scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])configuredLocalDirs), (root) -> {
         Object var10000;
         try {
            File rootDir = new File(root);
            if (!rootDir.exists() && !rootDir.mkdirs()) {
               MODULE$.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create dir in ", ". Ignoring this directory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, root)})))));
               var10000 = scala.None..MODULE$;
            } else {
               File dir = MODULE$.createTempDir(root, MODULE$.createTempDir$default$2());
               MODULE$.chmod700(dir);
               var10000 = new Some(dir.getAbsolutePath());
            }
         } catch (IOException var4) {
            MODULE$.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create local root dir in ", ". Ignoring this directory."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, root)})))));
            var10000 = scala.None..MODULE$;
         }

         return (Option)var10000;
      }, scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   private String getYarnLocalDirs(final SparkConf conf) {
      String localDirs = (String)scala.Option..MODULE$.apply(conf.getenv("LOCAL_DIRS")).getOrElse(() -> "");
      if (localDirs.isEmpty()) {
         throw new Exception("Yarn Local dirs can't be empty");
      } else {
         return localDirs;
      }
   }

   public void clearLocalRootDirs() {
      this.localRootDirs_$eq((String[])null);
   }

   public Seq randomize(final IterableOnce seq, final ClassTag evidence$1) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.randomizeInPlace(seq.iterator().toArray(evidence$1), this.randomizeInPlace$default$2())).toImmutableArraySeq();
   }

   public Object randomizeInPlace(final Object arr, final Random rand) {
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(scala.runtime.ScalaRunTime..MODULE$.array_length(arr) - 1), 1).by(-1).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         int j = rand.nextInt(i + 1);
         Object tmp = scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, j);
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, j, scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, i));
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, i, tmp);
      });
      return arr;
   }

   public Random randomizeInPlace$default$2() {
      return new Random();
   }

   private InetAddress localIpAddress$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            localIpAddress = this.findLocalInetAddress();
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return localIpAddress;
   }

   private InetAddress localIpAddress() {
      return (byte)(bitmap$0 & 1) == 0 ? this.localIpAddress$lzycompute() : localIpAddress;
   }

   private InetAddress findLocalInetAddress() {
      Object var1 = new Object();

      InetAddress var10000;
      try {
         String defaultIpOverride = System.getenv("SPARK_LOCAL_IP");
         if (defaultIpOverride != null) {
            var10000 = InetAddress.getByName(defaultIpOverride);
         } else {
            InetAddress address = InetAddress.getLocalHost();
            if (address.isLoopbackAddress()) {
               Seq activeNetworkIFs = scala.jdk.CollectionConverters..MODULE$.EnumerationHasAsScala(NetworkInterface.getNetworkInterfaces()).asScala().toSeq();
               Seq reOrderedNetworkIFs = this.isWindows() ? activeNetworkIFs : (Seq)activeNetworkIFs.reverse();
               reOrderedNetworkIFs.foreach((ni) -> {
                  $anonfun$findLocalInetAddress$1(address, var1, ni);
                  return BoxedUnit.UNIT;
               });
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Your hostname, ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, InetAddress.getLocalHost().getHostName())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"resolves to a loopback address: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, address.getHostAddress())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but we couldn't find any external IP address!"})))).log(scala.collection.immutable.Nil..MODULE$))));
               this.logWarning((Function0)(() -> "Set SPARK_LOCAL_IP if you need to bind to another address"));
            }

            var10000 = address;
         }
      } catch (NonLocalReturnControl var7) {
         if (var7.key() != var1) {
            throw var7;
         }

         var10000 = (InetAddress)var7.value();
      }

      return var10000;
   }

   private Option customHostname() {
      return customHostname;
   }

   private void customHostname_$eq(final Option x$1) {
      customHostname = x$1;
   }

   public void setCustomHostname(final String hostname) {
      this.checkHost(hostname);
      this.customHostname_$eq(new Some(hostname));
   }

   public String localCanonicalHostName() {
      return this.addBracketsIfNeeded((String)this.customHostname().getOrElse(() -> MODULE$.localIpAddress().getCanonicalHostName()));
   }

   public String localHostName() {
      return this.addBracketsIfNeeded((String)this.customHostname().getOrElse(() -> MODULE$.localIpAddress().getHostAddress()));
   }

   public String localHostNameForURI() {
      return this.addBracketsIfNeeded((String)this.customHostname().getOrElse(() -> InetAddresses.toUriString(MODULE$.localIpAddress())));
   }

   public String addBracketsIfNeeded(final String addr) {
      return addr.contains(":") && !addr.contains("[") ? "[" + addr + "]" : addr;
   }

   public String normalizeIpIfNeeded(final String host) {
      Regex addressRe = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^\\[{0,1}([0-9:]+?:[0-9]*)\\]{0,1}$"));
      if (host != null) {
         Option var5 = addressRe.unapplySeq(host);
         if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(1) == 0) {
            String unbracketed = (String)((LinearSeqOps)var5.get()).apply(0);
            return this.addBracketsIfNeeded(InetAddresses.toAddrString(InetAddresses.forString(unbracketed)));
         }
      }

      return host;
   }

   public void checkHost(final String host) {
      if (host != null && host.split(":").length > 2) {
         scala.Predef..MODULE$.assert(host.startsWith("[") && host.endsWith("]"), () -> "Expected hostname or IPv6 IP enclosed in [] but got " + host);
      } else {
         scala.Predef..MODULE$.assert(host != null && host.indexOf(58) == -1, () -> "Expected hostname or IP but got " + host);
      }
   }

   public void checkHostPort(final String hostPort) {
      if (hostPort != null && hostPort.split(":").length > 2) {
         scala.Predef..MODULE$.assert(hostPort != null && hostPort.indexOf("]:") != -1, () -> "Expected host and port but got " + hostPort);
      } else {
         scala.Predef..MODULE$.assert(hostPort != null && hostPort.indexOf(58) != -1, () -> "Expected host and port but got " + hostPort);
      }
   }

   private ConcurrentHashMap hostPortParseResults() {
      return hostPortParseResults;
   }

   public Tuple2 parseHostPort(final String hostPort) {
      Tuple2 cached = (Tuple2)this.hostPortParseResults().get(hostPort);
      if (cached != null) {
         return cached;
      } else {
         if (hostPort != null && hostPort.split(":").length > 2) {
            int index = hostPort.lastIndexOf("]:");
            if (-1 == index) {
               return this.setDefaultPortValue$1(hostPort);
            }

            String port = hostPort.substring(index + 2).trim();
            Tuple2 retval = new Tuple2(hostPort.substring(0, index + 1).trim(), port.isEmpty() ? BoxesRunTime.boxToInteger(0) : BoxesRunTime.boxToInteger(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(port))));
            this.hostPortParseResults().putIfAbsent(hostPort, retval);
         } else {
            int index = hostPort.lastIndexOf(58);
            if (-1 == index) {
               return this.setDefaultPortValue$1(hostPort);
            }

            String port = hostPort.substring(index + 1).trim();
            Tuple2 retval = new Tuple2(hostPort.substring(0, index).trim(), port.isEmpty() ? BoxesRunTime.boxToInteger(0) : BoxesRunTime.boxToInteger(.MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(port))));
            this.hostPortParseResults().putIfAbsent(hostPort, retval);
         }

         return (Tuple2)this.hostPortParseResults().get(hostPort);
      }
   }

   public String getUsedTimeNs(final long startTimeNs) {
      long var10000 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTimeNs);
      return var10000 + " ms";
   }

   public void deleteRecursively(final File file) {
      SparkFileUtils.deleteRecursively$(this, file);
      if (file != null) {
         ShutdownHookManager$.MODULE$.removeShutdownDeleteDir(file);
      }
   }

   public boolean doesDirectoryContainAnyNewFiles(final File dir, final long cutoff) {
      if (!dir.isDirectory()) {
         throw new IllegalArgumentException(dir + " is not a directory!");
      } else {
         File[] filesAndDirs = dir.listFiles();
         long cutoffTimeInMillis = System.currentTimeMillis() - cutoff * 1000L;
         return scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])filesAndDirs), (x$4) -> BoxesRunTime.boxToBoolean($anonfun$doesDirectoryContainAnyNewFiles$1(cutoffTimeInMillis, x$4))) || scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])filesAndDirs), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$doesDirectoryContainAnyNewFiles$2(x$5)))), (subdir) -> BoxesRunTime.boxToBoolean($anonfun$doesDirectoryContainAnyNewFiles$3(cutoff, subdir)));
      }
   }

   public long timeStringAsMs(final String str) {
      return JavaUtils.timeStringAsMs(str);
   }

   public long timeStringAsSeconds(final String str) {
      return JavaUtils.timeStringAsSec(str);
   }

   public long byteStringAsBytes(final String str) {
      return JavaUtils.byteStringAsBytes(str);
   }

   public long byteStringAsKb(final String str) {
      return JavaUtils.byteStringAsKb(str);
   }

   public long byteStringAsMb(final String str) {
      return JavaUtils.byteStringAsMb(str);
   }

   public long byteStringAsGb(final String str) {
      return JavaUtils.byteStringAsGb(str);
   }

   public int memoryStringToMb(final String str) {
      return (int)(JavaUtils.byteStringAsBytes(str) / 1024L / 1024L);
   }

   public String bytesToString(final long size) {
      int i;
      for(i = 0; i < siByteSizes.length - 1 && size < 2L * siByteSizes[i]; ++i) {
      }

      return .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.1f %s"), Locale.US, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble((double)size / (double)siByteSizes[i]), siByteSuffixes[i]}));
   }

   public String bytesToString(final BigInt size) {
      long EiB = 1152921504606846976L;
      if (size.isValidLong()) {
         return this.bytesToString(size.toLong());
      } else if (size.$less(scala.package..MODULE$.BigInt().apply(2048L).$times(scala.math.BigInt..MODULE$.long2bigInt(EiB)))) {
         return .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.1f EiB"), Locale.US, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{scala.package..MODULE$.BigDecimal().apply(size).$div(scala.math.BigDecimal..MODULE$.long2bigDecimal(EiB))}));
      } else {
         BigDecimal var10000 = scala.package..MODULE$.BigDecimal().apply(size, new MathContext(3, RoundingMode.HALF_UP));
         return var10000.toString() + " B";
      }
   }

   public String msDurationToString(final long ms) {
      int second = 1000;
      int minute = 60 * second;
      int hour = 60 * minute;
      Locale locale = Locale.US;
      if (ms < (long)second) {
         return .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%d ms"), locale, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(ms)}));
      } else if (ms < (long)minute) {
         return .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.1f s"), locale, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat((float)ms / (float)second)}));
      } else {
         return ms < (long)hour ? .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.1f m"), locale, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat((float)ms / (float)minute)})) : .MODULE$.formatLocal$extension(scala.Predef..MODULE$.augmentString("%.2f h"), locale, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToFloat((float)ms / (float)hour)}));
      }
   }

   public String megabytesToString(final long megabytes) {
      return this.bytesToString(megabytes * 1024L * 1024L);
   }

   public Process executeCommand(final Seq command, final File workingDir, final scala.collection.Map extraEnvironment, final boolean redirectStderr) {
      ProcessBuilder builder = (new ProcessBuilder((String[])command.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)))).directory(workingDir);
      Map environment = builder.environment();
      extraEnvironment.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$executeCommand$1(check$ifrefutable$1))).foreach((x$6) -> {
         if (x$6 != null) {
            String key = (String)x$6._1();
            String value = (String)x$6._2();
            return (String)environment.put(key, value);
         } else {
            throw new MatchError(x$6);
         }
      });
      Process process = builder.start();
      if (redirectStderr) {
         String threadName = "redirect stderr for command " + command.apply(0);
         this.processStreamByLine(threadName, process.getErrorStream(), (s) -> {
            $anonfun$executeCommand$4(this, s);
            return BoxedUnit.UNIT;
         });
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return process;
   }

   public File executeCommand$default$2() {
      return new File(".");
   }

   public scala.collection.Map executeCommand$default$3() {
      return (scala.collection.Map)scala.collection.Map..MODULE$.empty();
   }

   public boolean executeCommand$default$4() {
      return true;
   }

   public String executeAndGetOutput(final Seq command, final File workingDir, final scala.collection.Map extraEnvironment, final boolean redirectStderr) {
      Process process = this.executeCommand(command, workingDir, extraEnvironment, redirectStderr);
      StringBuilder output = new StringBuilder();
      String threadName = "read stdout for " + command.apply(0);
      Thread stdoutThread = this.processStreamByLine(threadName, process.getInputStream(), (s) -> {
         $anonfun$executeAndGetOutput$1(output, s);
         return BoxedUnit.UNIT;
      });
      int exitCode = process.waitFor();
      stdoutThread.join();
      if (exitCode != 0) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Process ", " exited with code "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, command)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitCode)), new MDC(org.apache.spark.internal.LogKeys.COMMAND_OUTPUT..MODULE$, output)}))))));
         throw new SparkException("Process " + command + " exited with code " + exitCode);
      } else {
         return output.toString();
      }
   }

   public File executeAndGetOutput$default$2() {
      return new File(".");
   }

   public scala.collection.Map executeAndGetOutput$default$3() {
      return (scala.collection.Map)scala.collection.Map..MODULE$.empty();
   }

   public boolean executeAndGetOutput$default$4() {
      return true;
   }

   public Thread processStreamByLine(final String threadName, final InputStream inputStream, final Function1 processLine) {
      Thread t = new Thread(threadName, inputStream, processLine) {
         private final InputStream inputStream$1;
         private final Function1 processLine$1;

         public void run() {
            scala.io.Source..MODULE$.fromInputStream(this.inputStream$1, scala.io.Codec..MODULE$.fallbackSystemCodec()).getLines().foreach((line) -> {
               $anonfun$run$1(this, line);
               return BoxedUnit.UNIT;
            });
         }

         // $FF: synthetic method
         public static final void $anonfun$run$1(final Object $this, final String line) {
            $this.processLine$1.apply(line);
         }

         public {
            this.inputStream$1 = inputStream$1;
            this.processLine$1 = processLine$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      t.setDaemon(true);
      t.start();
      return t;
   }

   public void tryOrExit(final Function0 block) {
      try {
         block.apply$mcV$sp();
      } catch (ControlThrowable var4) {
         throw var4;
      } catch (Throwable var5) {
         this.sparkUncaughtExceptionHandler().uncaughtException(var5);
      }

   }

   public void tryOrStopSparkContext(final SparkContext sc, final Function0 block) {
      try {
         block.apply$mcV$sp();
      } catch (ControlThrowable var6) {
         throw var6;
      } catch (Throwable var7) {
         String currentThreadName = Thread.currentThread().getName();
         if (sc != null) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"uncaught error in thread ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_NAME..MODULE$, currentThreadName)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"stopping SparkContext"})))).log(scala.collection.immutable.Nil..MODULE$))), var7);
            sc.stopInNewThread();
         }

         if (!scala.util.control.NonFatal..MODULE$.apply(var7)) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"throw uncaught fatal error in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_NAME..MODULE$, currentThreadName)})))), var7);
            throw var7;
         }
      }

   }

   public void tryLogNonFatalError(final Function0 block) {
      try {
         block.apply$mcV$sp();
      } catch (Throwable var6) {
         if (var6 == null || !scala.util.control.NonFatal..MODULE$.apply(var6)) {
            throw var6;
         }

         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught exception in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_NAME..MODULE$, Thread.currentThread().getName())})))), var6);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public Object tryWithSafeFinallyAndFailureCallbacks(final Function0 block, final Function0 catchBlock, final Function0 finallyBlock) {
      Throwable originalThrowable = null;
      boolean var21 = false;

      Object var10000;
      try {
         var21 = true;
         var10000 = block.apply();
         var21 = false;
      } catch (Throwable var25) {
         originalThrowable = var25;

         try {
            this.logError((Function0)(() -> "Aborting task"), originalThrowable);
            if (TaskContext$.MODULE$.get() != null) {
               TaskContext$.MODULE$.get().markTaskFailed(originalThrowable);
            }

            catchBlock.apply$mcV$sp();
         } catch (Throwable var24) {
            if (var25 == null) {
               if (var24 == null) {
                  throw var25;
               }
            } else if (var25.equals(var24)) {
               throw var25;
            }

            var25.addSuppressed(var24);
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Suppressing exception in catch: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var24.getMessage())})))), var24);
         }

         throw var25;
      } finally {
         if (var21) {
            try {
               finallyBlock.apply$mcV$sp();
            } catch (Throwable var23) {
               label145: {
                  if (var23 != null && originalThrowable != null) {
                     if (originalThrowable == null) {
                        if (var23 != null) {
                           break label145;
                        }
                     } else if (!originalThrowable.equals(var23)) {
                        break label145;
                     }
                  }

                  throw var23;
               }

               originalThrowable.addSuppressed(var23);
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Suppressing exception in finally: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var22.getMessage())})))), var23);
               throw originalThrowable;
            }
         }
      }

      Object var6 = var10000;

      try {
         finallyBlock.apply$mcV$sp();
         return var6;
      } catch (Throwable var22) {
         label134: {
            if (var22 != null && originalThrowable != null) {
               if (originalThrowable == null) {
                  if (var22 != null) {
                     break label134;
                  }
               } else if (!originalThrowable.equals(var22)) {
                  break label134;
               }
            }

            throw var22;
         }

         originalThrowable.addSuppressed(var22);
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Suppressing exception in finally: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var22.getMessage())})))), var22);
         throw originalThrowable;
      }
   }

   public void tryWithSafeFinallyAndFailureCallbacks$default$2(final Function0 block) {
   }

   public void tryWithSafeFinallyAndFailureCallbacks$default$3(final Function0 block) {
   }

   public String TRY_WITH_CALLER_STACKTRACE_FULL_STACKTRACE() {
      return TRY_WITH_CALLER_STACKTRACE_FULL_STACKTRACE;
   }

   public Try doTryWithCallerStacktrace(final Function0 f) {
      Try t = scala.util.Try..MODULE$.apply(f);
      if (t instanceof Failure var5) {
         Throwable ex = var5.exception();
         StackTraceElement[] origStackTrace = ex.getStackTrace();
         StackTraceElement[] currentStackTrace = Thread.currentThread().getStackTrace();
         int commonSuffixLen = ((Tuple2[])scala.collection.ArrayOps..MODULE$.takeWhile$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps((Object[])origStackTrace))), scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.reverse$extension(scala.Predef..MODULE$.refArrayOps((Object[])currentStackTrace))))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$doTryWithCallerStacktrace$1(x0$1)))).length;
         Utils.OriginalTryStackTraceException origEx = (Utils.OriginalTryStackTraceException)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])ex.getSuppressed()), (e) -> BoxesRunTime.boxToBoolean($anonfun$doTryWithCallerStacktrace$2(e))).getOrElse(() -> {
            Utils.OriginalTryStackTraceException fullEx = new Utils.OriginalTryStackTraceException();
            fullEx.setStackTrace(origStackTrace);
            ex.addSuppressed(fullEx);
            return fullEx;
         });
         origEx.doTryWithCallerStacktraceDepth_$eq(scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])origStackTrace)) - commonSuffixLen);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!(t instanceof Success)) {
            throw new MatchError(t);
         }

         BoxedUnit var11 = BoxedUnit.UNIT;
      }

      return t;
   }

   public Object getTryWithCallerStacktrace(final Try t) {
      if (t instanceof Failure var4) {
         Throwable ex = var4.exception();
         Utils.OriginalTryStackTraceException originalStacktraceEx = (Utils.OriginalTryStackTraceException)scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps((Object[])ex.getSuppressed()), (e) -> BoxesRunTime.boxToBoolean($anonfun$getTryWithCallerStacktrace$1(e))).getOrElse(() -> {
            throw ex;
         });
         StackTraceElement[] belowStacktrace = (StackTraceElement[])scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.refArrayOps((Object[])originalStacktraceEx.getStackTrace()), originalStacktraceEx.doTryWithCallerStacktraceDepth());
         ex.setStackTrace((StackTraceElement[])scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])belowStacktrace), scala.collection.ArrayOps..MODULE$.drop$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()), 1), scala.reflect.ClassTag..MODULE$.apply(StackTraceElement.class)));
         throw ex;
      } else if (t instanceof Success var8) {
         Object s = var8.value();
         return s;
      } else {
         throw new MatchError(t);
      }
   }

   private Regex SPARK_CORE_CLASS_REGEX() {
      return SPARK_CORE_CLASS_REGEX;
   }

   private Regex SPARK_SQL_CLASS_REGEX() {
      return SPARK_SQL_CLASS_REGEX;
   }

   private boolean sparkInternalExclusionFunction(final String className) {
      String SCALA_CORE_CLASS_PREFIX = "scala";
      boolean isSparkClass = this.SPARK_CORE_CLASS_REGEX().findFirstIn(className).isDefined() || this.SPARK_SQL_CLASS_REGEX().findFirstIn(className).isDefined();
      boolean isScalaClass = className.startsWith(SCALA_CORE_CLASS_PREFIX);
      return isSparkClass || isScalaClass;
   }

   public CallSite getCallSite(final Function1 skipClass) {
      ArrayBuffer callStack;
      int callStackDepth;
      String var11;
      label17: {
         label16: {
            ObjectRef lastSparkMethod = ObjectRef.create("<unknown>");
            ObjectRef firstUserFile = ObjectRef.create("<unknown>");
            IntRef firstUserLine = IntRef.create(0);
            BooleanRef insideSpark = BooleanRef.create(true);
            callStack = (ArrayBuffer)(new ArrayBuffer()).$colon$plus("<unknown>");
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])Thread.currentThread().getStackTrace()), (ste) -> {
               if (ste != null && ste.getMethodName() != null && !ste.getMethodName().contains("getStackTrace")) {
                  if (insideSpark.elem) {
                     if (!BoxesRunTime.unboxToBoolean(skipClass.apply(ste.getClassName()))) {
                        if (ste.getFileName() != null) {
                           firstUserFile.elem = ste.getFileName();
                           if (ste.getLineNumber() >= 0) {
                              firstUserLine.elem = ste.getLineNumber();
                           }
                        }

                        callStack.$plus$eq(ste.toString());
                        insideSpark.elem = false;
                        return BoxedUnit.UNIT;
                     } else {
                        String var8;
                        label33: {
                           label32: {
                              var8 = ste.getMethodName();
                              String var7 = "<init>";
                              if (var8 == null) {
                                 if (var7 == null) {
                                    break label32;
                                 }
                              } else if (var8.equals(var7)) {
                                 break label32;
                              }

                              var8 = ste.getMethodName();
                              break label33;
                           }

                           var8 = ste.getClassName().substring(ste.getClassName().lastIndexOf(46) + 1);
                        }

                        lastSparkMethod.elem = var8;
                        callStack.update(0, ste.toString());
                        return BoxedUnit.UNIT;
                     }
                  } else {
                     return callStack.$plus$eq(ste.toString());
                  }
               } else {
                  return BoxedUnit.UNIT;
               }
            });
            callStackDepth = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getProperty("spark.callstack.depth", "20")));
            var11 = (String)firstUserFile.elem;
            String var9 = "HiveSessionImpl.java";
            if (var11 == null) {
               if (var9 == null) {
                  break label16;
               }
            } else if (var11.equals(var9)) {
               break label16;
            }

            var11 = (String)lastSparkMethod.elem + " at " + (String)firstUserFile.elem + ":" + firstUserLine.elem;
            break label17;
         }

         var11 = "Spark JDBC Server Query";
      }

      String shortForm = var11;
      String longForm = ((IterableOnceOps)callStack.take(callStackDepth)).mkString("\n");
      return new CallSite(shortForm, longForm);
   }

   public Function1 getCallSite$default$1() {
      return (className) -> BoxesRunTime.boxToBoolean($anonfun$getCallSite$default$1$1(className));
   }

   private LoadingCache compressedLogFileLengthCache() {
      return compressedLogFileLengthCache;
   }

   private void compressedLogFileLengthCache_$eq(final LoadingCache x$1) {
      compressedLogFileLengthCache = x$1;
   }

   private synchronized LoadingCache getCompressedLogFileLengthCache(final SparkConf sparkConf) {
      if (this.compressedLogFileLengthCache() == null) {
         int compressedLogFileLengthCacheSize = BoxesRunTime.unboxToInt(sparkConf.get(Worker$.MODULE$.UNCOMPRESSED_LOG_FILE_LENGTH_CACHE_SIZE_CONF()));
         this.compressedLogFileLengthCache_$eq(CacheBuilder.newBuilder().maximumSize((long)compressedLogFileLengthCacheSize).build(new CacheLoader() {
            public Long load(final String path) {
               return scala.Predef..MODULE$.long2Long(Utils$.MODULE$.org$apache$spark$util$Utils$$getCompressedFileLength(new File(path)));
            }
         }));
      }

      return this.compressedLogFileLengthCache();
   }

   public long getFileLength(final File file, final SparkConf workConf) {
      return file.getName().endsWith(".gz") ? scala.Predef..MODULE$.Long2long((Long)this.getCompressedLogFileLengthCache(workConf).get(file.getAbsolutePath())) : file.length();
   }

   public long org$apache$spark$util$Utils$$getCompressedFileLength(final File file) {
      GZIPInputStream gzInputStream = null;

      long var10000;
      try {
         long fileSize = 0L;
         gzInputStream = new GZIPInputStream(new FileInputStream(file));
         int bufSize = 1024;
         byte[] buf = new byte[bufSize];

         for(int numBytes = ByteStreams.read(gzInputStream, buf, 0, bufSize); numBytes > 0; numBytes = ByteStreams.read(gzInputStream, buf, 0, bufSize)) {
            fileSize += (long)numBytes;
         }

         var10000 = fileSize;
      } catch (Throwable var12) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot get file length of ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), var12);
         throw var12;
      } finally {
         if (gzInputStream != null) {
            gzInputStream.close();
         }

      }

      return var10000;
   }

   public String offsetBytes(final String path, final long length, final long start, final long end) {
      File file = new File(path);
      long effectiveEnd = scala.math.package..MODULE$.min(length, end);
      long effectiveStart = scala.math.package..MODULE$.max(0L, start);
      byte[] buff = new byte[(int)(effectiveEnd - effectiveStart)];
      InputStream stream = (InputStream)(path.endsWith(".gz") ? new GZIPInputStream(new FileInputStream(file)) : new FileInputStream(file));

      try {
         ByteStreams.skipFully(stream, effectiveStart);
         ByteStreams.readFully(stream, buff);
      } finally {
         stream.close();
      }

      return scala.io.Source..MODULE$.fromBytes(buff, scala.io.Codec..MODULE$.fallbackSystemCodec()).mkString();
   }

   public String offsetBytes(final Seq files, final Seq fileLengths, final long start, final long end) {
      scala.Predef..MODULE$.assert(files.length() == fileLengths.length());
      long startIndex = scala.math.package..MODULE$.max(start, 0L);
      long endIndex = scala.math.package..MODULE$.min(end, BoxesRunTime.unboxToLong(fileLengths.sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
      scala.collection.immutable.Map fileToLength = org.apache.spark.util.collection.Utils$.MODULE$.toMap(files, fileLengths);
      this.logDebug((Function0)(() -> "Log files: \n" + fileToLength.mkString("\n")));
      StringBuffer stringBuffer = new StringBuffer((int)(endIndex - startIndex));
      LongRef sum = LongRef.create(0L);
      ((IterableOnceOps)files.zip(fileLengths)).foreach((x0$1) -> {
         $anonfun$offsetBytes$2(sum, fileToLength, endIndex, startIndex, stringBuffer, x0$1);
         return BoxedUnit.UNIT;
      });
      return stringBuffer.toString();
   }

   public Object clone(final Object value, final SerializerInstance serializer, final ClassTag evidence$2) {
      return serializer.deserialize(serializer.serialize(value, evidence$2), evidence$2);
   }

   private boolean isSpace(final char c) {
      return " \t\r\n".indexOf(c) != -1;
   }

   public Seq splitCommandString(final String s) {
      ArrayBuffer buf = new ArrayBuffer();
      boolean inWord = false;
      boolean inSingleQuote = false;
      boolean inDoubleQuote = false;
      StringBuilder curWord = new StringBuilder();

      for(int i = 0; i < s.length(); ++i) {
         char nextChar = s.charAt(i);
         if (inDoubleQuote) {
            if (nextChar == '"') {
               inDoubleQuote = false;
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (nextChar == '\\') {
               if (i < s.length() - 1) {
                  curWord.append(s.charAt(i + 1));
                  ++i;
               }

               BoxedUnit var9 = BoxedUnit.UNIT;
            } else {
               curWord.append(nextChar);
            }
         } else if (inSingleQuote) {
            if (nextChar == '\'') {
               inSingleQuote = false;
               BoxedUnit var10 = BoxedUnit.UNIT;
            } else {
               curWord.append(nextChar);
            }
         } else {
            if (nextChar == '"') {
               inWord = true;
               inDoubleQuote = true;
            } else if (nextChar == '\'') {
               inWord = true;
               inSingleQuote = true;
            } else if (!this.isSpace(nextChar)) {
               curWord.append(nextChar);
               inWord = true;
            } else if (inWord && this.isSpace(nextChar)) {
               endWord$1(buf, curWord);
               inWord = false;
            }

            BoxedUnit var11 = BoxedUnit.UNIT;
         }
      }

      if (inWord || inDoubleQuote || inSingleQuote) {
         endWord$1(buf, curWord);
      }

      return buf.toSeq();
   }

   public int nonNegativeMod(final int x, final int mod) {
      int rawMod = x % mod;
      return rawMod + (rawMod < 0 ? mod : 0);
   }

   public int nonNegativeHash(final Object obj) {
      if (obj == null) {
         return 0;
      } else {
         int hash = obj.hashCode();
         int hashAbs = Integer.MIN_VALUE != hash ? scala.math.package..MODULE$.abs(hash) : 0;
         return hashAbs;
      }
   }

   public scala.collection.Map getSystemProperties() {
      return ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(System.getProperties().stringPropertyNames()).asScala().map((key) -> new Tuple2(key, System.getProperty(key)))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public void times(final int numIters, final Function0 f) {
      for(int i = 0; i < numIters; ++i) {
         f.apply$mcV$sp();
      }

   }

   public long timeIt(final int numIters, final Function0 f, final Option prepare) {
      if (prepare.isEmpty()) {
         long startNs = System.nanoTime();
         this.times(numIters, f);
         return System.nanoTime() - startNs;
      } else {
         int i = 0;

         long sum;
         for(sum = 0L; i < numIters; ++i) {
            ((Function0)prepare.get()).apply$mcV$sp();
            long startNs = System.nanoTime();
            f.apply$mcV$sp();
            sum += System.nanoTime() - startNs;
         }

         return sum;
      }
   }

   public Option timeIt$default$3(final int numIters) {
      return scala.None..MODULE$;
   }

   public long getIteratorSize(final Iterator iterator) {
      if (iterator.knownSize() >= 0) {
         return (long)iterator.knownSize();
      } else {
         long count = 0L;

         while(iterator.hasNext()) {
            ++count;
            iterator.next();
         }

         return count;
      }
   }

   public Iterator getIteratorZipWithIndex(final Iterator iter, final long startIndex) {
      return new Iterator(startIndex, iter) {
         private long index;
         private final Iterator iter$1;

         /** @deprecated */
         public final boolean hasDefiniteSize() {
            return Iterator.hasDefiniteSize$(this);
         }

         public final Iterator iterator() {
            return Iterator.iterator$(this);
         }

         public Option nextOption() {
            return Iterator.nextOption$(this);
         }

         public boolean contains(final Object elem) {
            return Iterator.contains$(this, elem);
         }

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
         }

         public Iterator padTo(final int len, final Object elem) {
            return Iterator.padTo$(this, len, elem);
         }

         public Tuple2 partition(final Function1 p) {
            return Iterator.partition$(this, p);
         }

         public Iterator.GroupedIterator grouped(final int size) {
            return Iterator.grouped$(this, size);
         }

         public Iterator.GroupedIterator sliding(final int size, final int step) {
            return Iterator.sliding$(this, size, step);
         }

         public int sliding$default$2() {
            return Iterator.sliding$default$2$(this);
         }

         public Iterator scanLeft(final Object z, final Function2 op) {
            return Iterator.scanLeft$(this, z, op);
         }

         /** @deprecated */
         public Iterator scanRight(final Object z, final Function2 op) {
            return Iterator.scanRight$(this, z, op);
         }

         public int indexWhere(final Function1 p, final int from) {
            return Iterator.indexWhere$(this, p, from);
         }

         public int indexWhere$default$2() {
            return Iterator.indexWhere$default$2$(this);
         }

         public int indexOf(final Object elem) {
            return Iterator.indexOf$(this, elem);
         }

         public int indexOf(final Object elem, final int from) {
            return Iterator.indexOf$(this, elem, from);
         }

         public final int length() {
            return Iterator.length$(this);
         }

         public boolean isEmpty() {
            return Iterator.isEmpty$(this);
         }

         public Iterator filter(final Function1 p) {
            return Iterator.filter$(this, p);
         }

         public Iterator filterNot(final Function1 p) {
            return Iterator.filterNot$(this, p);
         }

         public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
            return Iterator.filterImpl$(this, p, isFlipped);
         }

         public Iterator withFilter(final Function1 p) {
            return Iterator.withFilter$(this, p);
         }

         public Iterator collect(final PartialFunction pf) {
            return Iterator.collect$(this, pf);
         }

         public Iterator distinct() {
            return Iterator.distinct$(this);
         }

         public Iterator distinctBy(final Function1 f) {
            return Iterator.distinctBy$(this, f);
         }

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
         }

         public Iterator flatMap(final Function1 f) {
            return Iterator.flatMap$(this, f);
         }

         public Iterator flatten(final Function1 ev) {
            return Iterator.flatten$(this, ev);
         }

         public Iterator concat(final Function0 xs) {
            return Iterator.concat$(this, xs);
         }

         public final Iterator $plus$plus(final Function0 xs) {
            return Iterator.$plus$plus$(this, xs);
         }

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
         }

         public Iterator sliceIterator(final int from, final int until) {
            return Iterator.sliceIterator$(this, from, until);
         }

         public Iterator zip(final IterableOnce that) {
            return Iterator.zip$(this, that);
         }

         public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
            return Iterator.zipAll$(this, that, thisElem, thatElem);
         }

         public Iterator zipWithIndex() {
            return Iterator.zipWithIndex$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return Iterator.sameElements$(this, that);
         }

         public Tuple2 duplicate() {
            return Iterator.duplicate$(this);
         }

         public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
            return Iterator.patch$(this, from, patchElems, replaced);
         }

         public Iterator tapEach(final Function1 f) {
            return Iterator.tapEach$(this, f);
         }

         public String toString() {
            return Iterator.toString$(this);
         }

         /** @deprecated */
         public Iterator seq() {
            return Iterator.seq$(this);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOnceOps.splitAt$(this, n);
         }

         public boolean isTraversableAgain() {
            return IterableOnceOps.isTraversableAgain$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         public int size() {
            return IterableOnceOps.size$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         public Object to(final Factory factory) {
            return IterableOnceOps.to$(this, factory);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private long index() {
            return this.index;
         }

         private void index_$eq(final long x$1) {
            this.index = x$1;
         }

         public boolean hasNext() {
            return this.iter$1.hasNext();
         }

         public Tuple2 next() {
            this.index_$eq(this.index() + 1L);
            return new Tuple2(this.iter$1.next(), BoxesRunTime.boxToLong(this.index()));
         }

         public {
            this.iter$1 = iter$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            scala.Predef..MODULE$.require(startIndex$2 >= 0L, () -> "startIndex should be >= 0.");
            this.index = startIndex$2 - 1L;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void symlink(final File src, final File dst) {
      if (!src.isAbsolute()) {
         throw new IOException("Source must be absolute");
      } else if (dst.isAbsolute()) {
         throw new IOException("Destination must be relative");
      } else {
         Files.createSymbolicLink(dst.toPath(), src.toPath());
      }
   }

   public FileSystem getHadoopFileSystem(final URI path, final Configuration conf) {
      return FileSystem.get(path, conf);
   }

   public FileSystem getHadoopFileSystem(final String path, final Configuration conf) {
      return this.getHadoopFileSystem(new URI(path), conf);
   }

   public boolean isWindows() {
      return isWindows;
   }

   public boolean isMac() {
      return isMac;
   }

   public boolean isJavaVersionAtLeast21() {
      return isJavaVersionAtLeast21;
   }

   public boolean isMacOnAppleSilicon() {
      return isMacOnAppleSilicon;
   }

   public boolean preferIPv6() {
      return preferIPv6;
   }

   public Regex windowsDrive() {
      return windowsDrive;
   }

   public Option terminateProcess(final Process process, final long timeoutMs) {
      process.destroy();
      if (process.waitFor(timeoutMs, TimeUnit.MILLISECONDS)) {
         return scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(process.exitValue()));
      } else {
         try {
            process.destroyForcibly();
         } catch (Throwable var8) {
            if (var8 == null || !scala.util.control.NonFatal..MODULE$.apply(var8)) {
               throw var8;
            }

            this.logWarning((Function0)(() -> "Exception when attempting to kill process"), var8);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (process.waitFor(timeoutMs, TimeUnit.MILLISECONDS)) {
            return scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(process.exitValue()));
         } else {
            this.logWarning((Function0)(() -> "Timed out waiting to forcibly kill process"));
            return scala.None..MODULE$;
         }
      }
   }

   public Option getStderr(final Process process, final long timeoutMs) {
      boolean terminated = process.waitFor(timeoutMs, TimeUnit.MILLISECONDS);
      return (Option)(terminated ? new Some(scala.io.Source..MODULE$.fromInputStream(process.getErrorStream(), scala.io.Codec..MODULE$.fallbackSystemCodec()).getLines().mkString("\n")) : scala.None..MODULE$);
   }

   public Object logUncaughtExceptions(final Function0 f) {
      try {
         return f.apply();
      } catch (ControlThrowable var4) {
         throw var4;
      } catch (Throwable var5) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught exception in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_NAME..MODULE$, Thread.currentThread().getName())})))), var5);
         throw var5;
      }
   }

   public Try tryLog(final Function0 f) {
      Object var10000;
      try {
         Object res = f.apply();
         var10000 = new Success(res);
      } catch (ControlThrowable var5) {
         throw var5;
      } catch (Throwable var6) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Uncaught exception in thread ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THREAD_NAME..MODULE$, Thread.currentThread().getName())})))), var6);
         var10000 = new Failure(var6);
      }

      return (Try)var10000;
   }

   public boolean isFatalError(final Throwable e) {
      boolean var10000;
      if (e != null) {
         Option var5 = scala.util.control.NonFatal..MODULE$.unapply(e);
         if (!var5.isEmpty()) {
            var10000 = true;
            return !var10000;
         }
      }

      var10000 = e instanceof InterruptedException ? true : (e instanceof NotImplementedError ? true : (e instanceof ControlThrowable ? true : e instanceof LinkageError));
      return !var10000;
   }

   public String resolveURIs(final String paths) {
      return paths != null && !paths.trim().isEmpty() ? scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])paths.split(",")), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$resolveURIs$1(x$7)))), (p) -> MODULE$.resolveURI(p), scala.reflect.ClassTag..MODULE$.apply(URI.class))).mkString(",") : "";
   }

   public String[] nonLocalPaths(final String paths, final boolean testWindows) {
      boolean windows = this.isWindows() || testWindows;
      return paths != null && !paths.trim().isEmpty() ? (String[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])paths.split(",")), (p) -> BoxesRunTime.boxToBoolean($anonfun$nonLocalPaths$1(windows, p))) : (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public boolean nonLocalPaths$default$2() {
      return false;
   }

   public String loadDefaultSparkProperties(final SparkConf conf, final String filePath) {
      String path = (String)scala.Option..MODULE$.apply(filePath).getOrElse(() -> MODULE$.getDefaultPropertiesFile(MODULE$.getDefaultPropertiesFile$default$1()));
      scala.Option..MODULE$.apply(path).foreach((confFile) -> {
         $anonfun$loadDefaultSparkProperties$2(conf, confFile);
         return BoxedUnit.UNIT;
      });
      return path;
   }

   public String loadDefaultSparkProperties$default$2() {
      return null;
   }

   public String trimExceptCRLF(final String str) {
      Function1 nonSpaceOrNaturalLineDelimiter = (ch) -> BoxesRunTime.boxToBoolean($anonfun$trimExceptCRLF$1(BoxesRunTime.unboxToChar(ch)));
      String qual$1 = scala.Predef..MODULE$.augmentString(str);
      int x$2 = .MODULE$.indexWhere$default$2$extension(qual$1);
      int firstPos = .MODULE$.indexWhere$extension(qual$1, nonSpaceOrNaturalLineDelimiter, x$2);
      String qual$2 = scala.Predef..MODULE$.augmentString(str);
      int x$4 = .MODULE$.lastIndexWhere$default$2$extension(qual$2);
      int lastPos = .MODULE$.lastIndexWhere$extension(qual$2, nonSpaceOrNaturalLineDelimiter, x$4);
      return firstPos >= 0 && lastPos >= 0 ? str.substring(firstPos, lastPos + 1) : "";
   }

   public scala.collection.Map getPropertiesFromFile(final String filename) {
      File file = new File(filename);
      scala.Predef..MODULE$.require(file.exists(), () -> "Properties file " + file + " does not exist");
      scala.Predef..MODULE$.require(file.isFile(), () -> "Properties file " + file + " is not a normal file");
      InputStreamReader inReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);

      try {
         Properties properties = new Properties();
         properties.load(inReader);
         ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(properties.stringPropertyNames()).asScala().map((k) -> new Tuple2(k, MODULE$.trimExceptCRLF(properties.getProperty(k))))).toMap(scala..less.colon.less..MODULE$.refl());
      } catch (IOException var9) {
         throw new SparkException("Failed when loading Spark properties from " + filename, var9);
      } finally {
         inReader.close();
      }

      return inReader;
   }

   public String getDefaultPropertiesFile(final scala.collection.Map env) {
      return (String)env.get("SPARK_CONF_DIR").orElse(() -> env.get("SPARK_HOME").map((t) -> t + File.separator + "conf")).map((t) -> new File(t + File.separator + "spark-defaults.conf")).filter((x$8) -> BoxesRunTime.boxToBoolean($anonfun$getDefaultPropertiesFile$4(x$8))).map((x$9) -> x$9.getAbsolutePath()).orNull(scala..less.colon.less..MODULE$.refl());
   }

   public scala.collection.Map getDefaultPropertiesFile$default$1() {
      return scala.sys.package..MODULE$.env();
   }

   public String exceptionString(final Throwable e) {
      if (e == null) {
         return "";
      } else {
         StringWriter stringWriter = new StringWriter();
         e.printStackTrace(new PrintWriter(stringWriter));
         return stringWriter.toString();
      }
   }

   private Utils.Lock Lock(final LockInfo lock) {
      return new Utils.Lock(lock);
   }

   public ThreadStackTrace[] getThreadDump() {
      ThreadInfo[] threadInfos = (ThreadInfo[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])ManagementFactory.getThreadMXBean().dumpAllThreads(true, true)), (x$10) -> BoxesRunTime.boxToBoolean($anonfun$getThreadDump$1(x$10)));
      return (ThreadStackTrace[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.sortWith$extension(scala.Predef..MODULE$.refArrayOps((Object[])threadInfos), (x0$1, x1$1) -> BoxesRunTime.boxToBoolean($anonfun$getThreadDump$2(x0$1, x1$1)))), (threadInfo) -> MODULE$.threadInfoToThreadStackTrace(threadInfo), scala.reflect.ClassTag..MODULE$.apply(ThreadStackTrace.class));
   }

   public String[] getHeapHistogram() {
      String pid = String.valueOf(ProcessHandle.current().pid());
      String jmap = System.getProperty("java.home") + "/bin/jmap";
      ProcessBuilder builder = new ProcessBuilder(new String[]{jmap, "-histo:live", pid});
      Process p = builder.start();
      ArrayBuffer rows = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
      this.tryWithResource(() -> new BufferedReader(new InputStreamReader(p.getInputStream())), (r) -> {
         $anonfun$getHeapHistogram$2(rows, r);
         return BoxedUnit.UNIT;
      });
      return (String[])rows.toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public Option getThreadDumpForThread(final long threadId) {
      if (threadId <= 0L) {
         return scala.None..MODULE$;
      } else {
         Option threadInfo = scala.Option..MODULE$.apply(ManagementFactory.getThreadMXBean().getThreadInfo(threadId, Integer.MAX_VALUE));
         return threadInfo.map((threadInfox) -> MODULE$.threadInfoToThreadStackTrace(threadInfox));
      }
   }

   private ThreadStackTrace threadInfoToThreadStackTrace(final ThreadInfo threadInfo) {
      Thread.State threadState = threadInfo.getThreadState();
      scala.collection.immutable.Map monitors = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])threadInfo.getLockedMonitors()), (m) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(m.getLockedStackDepth())), m.toString()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      StackTrace stackTrace = new StackTrace(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])threadInfo.getStackTrace()))), (x0$1) -> {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            StackTraceElement frame = (StackTraceElement)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            String locked = idx == 0 && threadInfo.getLockInfo() != null ? (State.BLOCKED.equals(threadState) ? "\t-  blocked on " + threadInfo.getLockInfo() + "\n" : ((State.WAITING.equals(threadState) ? true : State.TIMED_WAITING.equals(threadState)) ? "\t-  waiting on " + threadInfo.getLockInfo() + "\n" : "")) : "";
            String locking = (String)monitors.get(BoxesRunTime.boxToInteger(idx)).map((mi) -> "\t-  locked " + mi + "\n").getOrElse(() -> "");
            return frame.toString() + "\n" + locked + locking;
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq());
      String[] synchronizers = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])threadInfo.getLockedSynchronizers()), (x$11) -> x$11.toString(), scala.reflect.ClassTag..MODULE$.apply(String.class));
      Seq monitorStrs = monitors.values().toSeq();
      return new ThreadStackTrace(threadInfo.getThreadId(), threadInfo.getThreadName(), threadState, stackTrace, (Option)(threadInfo.getLockOwnerId() < 0L ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToLong(threadInfo.getLockOwnerId()))), (String)scala.Option..MODULE$.apply(threadInfo.getLockInfo()).map((x$12) -> MODULE$.Lock(x$12).lockString()).getOrElse(() -> ""), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])synchronizers), monitorStrs, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(synchronizers).toImmutableArraySeq(), monitorStrs, scala.Option..MODULE$.apply(threadInfo.getLockName()), scala.Option..MODULE$.apply(threadInfo.getLockOwnerName()), threadInfo.isSuspended(), threadInfo.isInNative(), threadInfo.isDaemon(), threadInfo.getPriority());
   }

   public Seq sparkJavaOpts(final SparkConf conf, final Function1 filterKey) {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])conf.getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$sparkJavaOpts$1(filterKey, x0$1)))), (x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return "-D" + k + "=" + v;
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).toImmutableArraySeq();
   }

   public Function1 sparkJavaOpts$default$2() {
      return (x$13) -> BoxesRunTime.boxToBoolean($anonfun$sparkJavaOpts$default$2$1(x$13));
   }

   public int portMaxRetries(final SparkConf conf) {
      Option maxRetries = conf.getOption("spark.port.maxRetries").map((x$14) -> BoxesRunTime.boxToInteger($anonfun$portMaxRetries$1(x$14)));
      return conf.contains((ConfigEntry)Tests$.MODULE$.IS_TESTING()) ? BoxesRunTime.unboxToInt(maxRetries.getOrElse((JFunction0.mcI.sp)() -> 100)) : BoxesRunTime.unboxToInt(maxRetries.getOrElse((JFunction0.mcI.sp)() -> 16));
   }

   public int userPort(final int base, final int offset) {
      return (base + offset - 1024) % 'ﰀ' + 1024;
   }

   public Tuple2 startServiceOnPort(final int startPort, final Function1 startService, final SparkConf conf, final String serviceName) {
      return this.startServiceOnPort(startPort, startService, this.portMaxRetries(conf), serviceName);
   }

   public Tuple2 startServiceOnPort(final int startPort, final Function1 startService, final int maxRetries, final String serviceName) {
      Object var5 = new Object();

      try {
         scala.Predef..MODULE$.require(startPort == 0 || 1024 <= startPort && startPort < 65536, () -> "startPort should be between 1024 and 65535 (inclusive), or 0 for a random free port.");
         String serviceString = serviceName.isEmpty() ? "" : " '" + serviceName + "'";
         scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(0), maxRetries).foreach$mVc$sp((JFunction1.mcVI.sp)(offset) -> {
            int tryPort = startPort == 0 ? startPort : MODULE$.userPort(startPort, offset);

            try {
               Tuple2 var10 = (Tuple2)startService.apply(BoxesRunTime.boxToInteger(tryPort));
               if (var10 != null) {
                  Object service = var10._1();
                  int port = var10._2$mcI$sp();
                  Tuple2 var9 = new Tuple2(service, BoxesRunTime.boxToInteger(port));
                  Object servicex = var9._1();
                  int portx = var9._2$mcI$sp();
                  MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Successfully started service", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceString)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" on port ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(portx))}))))));
                  throw new NonLocalReturnControl(var5, new Tuple2(servicex, BoxesRunTime.boxToInteger(portx)));
               } else {
                  throw new MatchError(var10);
               }
            } catch (Throwable var20) {
               if (var20 instanceof Exception var17) {
                  if (MODULE$.isBindCollision(var17)) {
                     if (offset >= maxRetries) {
                        String exceptionMessage = startPort == 0 ? var17.getMessage() + ": Service" + serviceString + " failed after " + maxRetries + " retries (on a random free port)! Consider explicitly setting the appropriate binding address for the service" + serviceString + " (for example " + org.apache.spark.internal.config.package$.MODULE$.DRIVER_BIND_ADDRESS().key() + " for SparkDriver) to the correct binding address." : var17.getMessage() + ": Service" + serviceString + " failed after " + maxRetries + " retries (starting from " + startPort + ")! Consider explicitly setting the appropriate port for the service" + serviceString + " (for example spark.ui.port for SparkUI) to an available port or increasing spark.port.maxRetries.";
                        BindException exception = new BindException(exceptionMessage);
                        exception.setStackTrace(var17.getStackTrace());
                        throw exception;
                     }

                     if (startPort == 0) {
                        MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Service", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceString)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"could not bind on a random free port. "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"You may check whether configuring an appropriate binding address."})))).log(scala.collection.immutable.Nil..MODULE$))));
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                     } else {
                        MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Service", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceString)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"could not bind on port ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT..MODULE$, BoxesRunTime.boxToInteger(tryPort))})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempting port ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PORT2..MODULE$, BoxesRunTime.boxToInteger(tryPort + 1))}))))));
                        BoxedUnit var21 = BoxedUnit.UNIT;
                     }

                     return;
                  }
               }

               throw var20;
            }
         });
         throw new SparkException("Failed to start service" + serviceString + " on port " + startPort);
      } catch (NonLocalReturnControl var8) {
         if (var8.key() == var5) {
            return (Tuple2)var8.value();
         } else {
            throw var8;
         }
      }
   }

   public String startServiceOnPort$default$4() {
      return "";
   }

   public boolean isBindCollision(final Throwable exception) {
      while(true) {
         if (exception instanceof BindException var5) {
            if (var5.getMessage() != null) {
               return true;
            }

            exception = var5.getCause();
         } else {
            if (exception instanceof MultiException var6) {
               return scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(var6.getThrowables()).asScala().exists((exceptionx) -> BoxesRunTime.boxToBoolean($anonfun$isBindCollision$1(exceptionx)));
            }

            if (exception instanceof Errors.NativeIoException var7) {
               if (var7.getMessage() != null && var7.getMessage().startsWith("bind() failed: ")) {
                  return true;
               }

               exception = var7.getCause();
            } else if (exception instanceof IOException var8) {
               if (var8.getMessage() != null && var8.getMessage().startsWith("Failed to bind to address")) {
                  return true;
               }

               exception = var8.getCause();
            } else {
               if (!(exception instanceof Exception)) {
                  return false;
               }

               Exception var9 = (Exception)exception;
               exception = var9.getCause();
            }
         }
      }
   }

   public void setLogLevel(final Level l) {
      Tuple2 var4 = this.getLogContext();
      if (var4 != null) {
         LoggerContext ctx = (LoggerContext)var4._1();
         LoggerConfig loggerConfig = (LoggerConfig)var4._2();
         Tuple2 var3 = new Tuple2(ctx, loggerConfig);
         LoggerContext ctx = (LoggerContext)var3._1();
         LoggerConfig loggerConfig = (LoggerConfig)var3._2();
         loggerConfig.setLevel(l);
         ctx.updateLoggers();
         org.apache.spark.internal.Logging..MODULE$.sparkShellThresholdLevel_$eq((Level)null);
      } else {
         throw new MatchError(var4);
      }
   }

   public void setLogLevelIfNeeded(final String newLogLevel) {
      label14: {
         String var2 = this.getLogLevel();
         if (newLogLevel == null) {
            if (var2 != null) {
               break label14;
            }
         } else if (!newLogLevel.equals(var2)) {
            break label14;
         }

         return;
      }

      this.setLogLevel(Level.toLevel(newLogLevel));
   }

   private Tuple2 getLogContext$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            LoggerContext ctx = (LoggerContext)LogManager.getContext(false);
            getLogContext = new Tuple2(ctx, ctx.getConfiguration().getLoggerConfig(""));
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return getLogContext;
   }

   private Tuple2 getLogContext() {
      return (byte)(bitmap$0 & 2) == 0 ? this.getLogContext$lzycompute() : getLogContext;
   }

   public String getLogLevel() {
      Tuple2 var3 = this.getLogContext();
      if (var3 != null) {
         LoggerConfig loggerConfig = (LoggerConfig)var3._2();
         return loggerConfig.getLevel().name();
      } else {
         throw new MatchError(var3);
      }
   }

   public String libraryPathEnvName() {
      if (this.isWindows()) {
         return "PATH";
      } else {
         return this.isMac() ? "DYLD_LIBRARY_PATH" : "LD_LIBRARY_PATH";
      }
   }

   public String libraryPathEnvPrefix(final Seq libraryPaths) {
      String libraryPathScriptVar = this.isWindows() ? "%" + this.libraryPathEnvName() + "%" : "$" + this.libraryPathEnvName();
      String libraryPath = ((IterableOnceOps)libraryPaths.$colon$plus(libraryPathScriptVar)).mkString("\"", File.pathSeparator, "\"");
      String ampersand = this.isWindows() ? " &" : "";
      return this.libraryPathEnvName() + "=" + libraryPath + ampersand;
   }

   public String getSparkOrYarnConfig(final SparkConf conf, final String key, final String default) {
      if (conf.contains(key)) {
         return conf.get(key, default);
      } else {
         String var10000 = conf.get("spark.master", (String)null);
         String var4 = "yarn";
         if (var10000 == null) {
            if (var4 == null) {
               return (new YarnConfiguration(SparkHadoopUtil$.MODULE$.get().newConfiguration(conf))).get(key, default);
            }
         } else if (var10000.equals(var4)) {
            return (new YarnConfiguration(SparkHadoopUtil$.MODULE$.get().newConfiguration(conf))).get(key, default);
         }

         return default;
      }
   }

   public Tuple2 extractHostPortFromSparkUrl(final String sparkUrl) throws SparkException {
      try {
         URI uri = new URI(sparkUrl);
         String host = uri.getHost();
         int port = uri.getPort();
         String var10000 = uri.getScheme();
         String var5 = "spark";
         if (var10000 == null) {
            if (var5 != null) {
               throw new SparkException("Invalid master URL: " + sparkUrl);
            }
         } else if (!var10000.equals(var5)) {
            throw new SparkException("Invalid master URL: " + sparkUrl);
         }

         if (host != null && port >= 0 && (uri.getPath() == null || uri.getPath().isEmpty()) && uri.getFragment() == null && uri.getQuery() == null && uri.getUserInfo() == null) {
            return new Tuple2(host, BoxesRunTime.boxToInteger(port));
         } else {
            throw new SparkException("Invalid master URL: " + sparkUrl);
         }
      } catch (URISyntaxException var7) {
         throw new SparkException("Invalid master URL: " + sparkUrl, var7);
      }
   }

   public String getCurrentUserName() {
      return (String)scala.Option..MODULE$.apply(System.getenv("SPARK_USER")).getOrElse(() -> UserGroupInformation.getCurrentUser().getShortUserName());
   }

   public Set EMPTY_USER_GROUPS() {
      return EMPTY_USER_GROUPS;
   }

   public Set getCurrentUserGroups(final SparkConf sparkConf, final String username) {
      String groupProviderClassName = (String)sparkConf.get(UI$.MODULE$.USER_GROUPS_MAPPING());
      String var4 = "";
      if (groupProviderClassName == null) {
         if (var4 == null) {
            return this.EMPTY_USER_GROUPS();
         }
      } else if (groupProviderClassName.equals(var4)) {
         return this.EMPTY_USER_GROUPS();
      }

      try {
         GroupMappingServiceProvider groupMappingServiceProvider = (GroupMappingServiceProvider)this.classForName(groupProviderClassName, this.classForName$default$2(), this.classForName$default$3()).getConstructor().newInstance();
         Set currentUserGroups = groupMappingServiceProvider.getGroups(username);
         return currentUserGroups;
      } catch (Exception var8) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error getting groups for user=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.USER_NAME..MODULE$, username)})))), var8);
         return this.EMPTY_USER_GROUPS();
      }
   }

   public String[] parseStandaloneMasterUrls(final String masterUrls) {
      return (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.stripPrefix$extension(scala.Predef..MODULE$.augmentString(masterUrls), "spark://").split(",")), (x$17) -> "spark://" + x$17, scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public String BACKUP_STANDALONE_MASTER_PREFIX() {
      return BACKUP_STANDALONE_MASTER_PREFIX;
   }

   public boolean responseFromBackup(final String msg) {
      return msg.startsWith(this.BACKUP_STANDALONE_MASTER_PREFIX());
   }

   public Object withDummyCallSite(final SparkContext sc, final Function0 body) {
      String oldShortCallSite = sc.getLocalProperty(CallSite$.MODULE$.SHORT_FORM());
      String oldLongCallSite = sc.getLocalProperty(CallSite$.MODULE$.LONG_FORM());

      Object var10000;
      try {
         sc.setLocalProperty(CallSite$.MODULE$.SHORT_FORM(), "");
         sc.setLocalProperty(CallSite$.MODULE$.LONG_FORM(), "");
         var10000 = body.apply();
      } finally {
         sc.setLocalProperty(CallSite$.MODULE$.SHORT_FORM(), oldShortCallSite);
         sc.setLocalProperty(CallSite$.MODULE$.LONG_FORM(), oldLongCallSite);
      }

      return var10000;
   }

   public boolean isInDirectory(final File parent, final File child) {
      while(true) {
         if (child != null && parent != null) {
            if (child.exists() && parent.exists() && parent.isDirectory()) {
               if (parent.equals(child)) {
                  return true;
               }

               child = child.getParentFile();
               parent = parent;
               continue;
            }

            return false;
         }

         return false;
      }
   }

   public boolean isLocalMaster(final ReadOnlySparkConf conf) {
      boolean var10000;
      label18: {
         String master = conf.get("spark.master", "");
         String var3 = "local";
         if (master == null) {
            if (var3 == null) {
               break label18;
            }
         } else if (master.equals(var3)) {
            break label18;
         }

         if (!master.startsWith("local[")) {
            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public boolean isPushBasedShuffleEnabled(final SparkConf conf, final boolean isDriver, final boolean checkSerializer) {
      boolean pushBasedShuffleEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.PUSH_BASED_SHUFFLE_ENABLED()));
      if (!pushBasedShuffleEnabled) {
         return false;
      } else {
         LazyBoolean serializerIsSupported$lzy;
         boolean isTesting;
         boolean var11;
         label46: {
            label45: {
               serializerIsSupported$lzy = new LazyBoolean();
               isTesting = BoxesRunTime.unboxToBoolean(((Option)conf.get((ConfigEntry)Tests$.MODULE$.IS_TESTING())).getOrElse((JFunction0.mcZ.sp)() -> false));
               if (BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
                  String var10000 = conf.get("spark.master", (String)null);
                  String var9 = "yarn";
                  if (var10000 == null) {
                     if (var9 == null) {
                        break label45;
                     }
                  } else if (var10000.equals(var9)) {
                     break label45;
                  }
               }

               var11 = false;
               break label46;
            }

            var11 = true;
         }

         boolean isShuffleServiceAndYarn = var11;
         boolean ioEncryptionDisabled = !BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.IO_ENCRYPTION_ENABLED()));
         boolean canDoPushBasedShuffle = (isShuffleServiceAndYarn || isTesting) && ioEncryptionDisabled && serializerIsSupported$1(serializerIsSupported$lzy, checkSerializer, conf, isDriver);
         if (!canDoPushBasedShuffle) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Push-based shuffle can only be enabled when the application is submitted "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to run in YARN mode, with external shuffle service enabled, IO encryption "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"disabled, and relocation of serialized objects supported."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         return canDoPushBasedShuffle;
      }
   }

   public boolean isPushBasedShuffleEnabled$default$3() {
      return true;
   }

   public Object instantiateSerializerOrShuffleManager(final String className, final SparkConf conf, final boolean isDriver) {
      Class cls = this.classForName(className, this.classForName$default$2(), this.classForName$default$3());

      Object var10000;
      try {
         var10000 = cls.getConstructor(SparkConf.class, Boolean.TYPE).newInstance(conf, isDriver);
      } catch (NoSuchMethodException var6) {
         try {
            var10000 = cls.getConstructor(SparkConf.class).newInstance(conf);
         } catch (NoSuchMethodException var5) {
            var10000 = cls.getConstructor().newInstance();
         }
      }

      return var10000;
   }

   public Object instantiateSerializerFromConf(final ConfigEntry propertyName, final SparkConf conf, final boolean isDriver) {
      return this.instantiateSerializerOrShuffleManager((String)conf.get(propertyName), conf, isDriver);
   }

   public boolean isDynamicAllocationEnabled(final ReadOnlySparkConf conf) {
      boolean dynamicAllocationEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_ENABLED()));
      return dynamicAllocationEnabled && (!this.isLocalMaster(conf) || BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_TESTING())));
   }

   public boolean isStreamingDynamicAllocationEnabled(final SparkConf conf) {
      boolean streamingDynamicAllocationEnabled = BoxesRunTime.unboxToBoolean(conf.get(Streaming$.MODULE$.STREAMING_DYN_ALLOCATION_ENABLED()));
      return streamingDynamicAllocationEnabled && (!this.isLocalMaster(conf) || BoxesRunTime.unboxToBoolean(conf.get(Streaming$.MODULE$.STREAMING_DYN_ALLOCATION_TESTING())));
   }

   public int getDynamicAllocationInitialExecutors(final SparkConf conf) {
      if (BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_INITIAL_EXECUTORS())) < BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS()))) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " less than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_INITIAL_EXECUTORS().key())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is invalid, ignoring its setting, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS().key())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"please update your configs."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      if (BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_INSTANCES())).getOrElse((JFunction0.mcI.sp)() -> 0)) < BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS()))) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " less than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_INSTANCES().key())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is invalid, ignoring its setting, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS().key())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"please update your configs."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      int initialExecutors = BoxesRunTime.unboxToInt(scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS())), BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_INITIAL_EXECUTORS())), BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_INSTANCES())).getOrElse((JFunction0.mcI.sp)() -> 0))})).max(scala.math.Ordering.Int..MODULE$));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using initial executors = ", ", max of "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS..MODULE$, BoxesRunTime.boxToInteger(initialExecutors))}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ","})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_INITIAL_EXECUTORS().key())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " and"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DYN_ALLOCATION_MIN_EXECUTORS().key())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG3..MODULE$, org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_INSTANCES().key())}))))));
      return initialExecutors;
   }

   public File tempFileWith(final File path) {
      String var10002 = path.getAbsolutePath();
      return new File(var10002 + "." + UUID.randomUUID());
   }

   public String getProcessName() {
      return ManagementFactory.getRuntimeMXBean().getName();
   }

   public void initDaemon(final Logger log) {
      log.info("Started daemon with process name: " + this.getProcessName());
      SignalUtils$.MODULE$.registerLogger(log);
   }

   public void resetStructuredLogging() {
      if (System.getProperty(org.apache.spark.internal.config.package$.MODULE$.STRUCTURED_LOGGING_ENABLED().key(), "false").equals("false")) {
         org.apache.spark.internal.Logging..MODULE$.disableStructuredLogging();
      } else {
         org.apache.spark.internal.Logging..MODULE$.enableStructuredLogging();
      }
   }

   public void resetStructuredLogging(final SparkConf sparkConf) {
      if (BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.STRUCTURED_LOGGING_ENABLED()))) {
         org.apache.spark.internal.Logging..MODULE$.enableStructuredLogging();
      } else {
         org.apache.spark.internal.Logging..MODULE$.disableStructuredLogging();
      }
   }

   public Seq getUserJars(final SparkConf conf) {
      return (Seq)((IterableOps)conf.get(org.apache.spark.internal.config.package$.MODULE$.JARS())).filter((x$20) -> BoxesRunTime.boxToBoolean($anonfun$getUserJars$1(x$20)));
   }

   public Seq getLocalUserJarsForShell(final SparkConf conf) {
      Option localJars = conf.getOption("spark.repl.local.jars");
      return (Seq)scala.Option..MODULE$.option2Iterable(localJars.map((x$21) -> x$21.split(",")).map((x$22) -> (String[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])x$22), (x$23) -> BoxesRunTime.boxToBoolean($anonfun$getLocalUserJarsForShell$3(x$23))))).toSeq().flatten((xs) -> scala.Predef..MODULE$.wrapRefArray((Object[])xs));
   }

   public String REDACTION_REPLACEMENT_TEXT() {
      return REDACTION_REPLACEMENT_TEXT;
   }

   public scala.collection.Seq redact(final SparkConf conf, final scala.collection.Seq kvs) {
      Regex redactionPattern = (Regex)conf.get(org.apache.spark.internal.config.package$.MODULE$.SECRET_REDACTION_PATTERN());
      return this.redact(redactionPattern, kvs);
   }

   public scala.collection.Seq redact(final Option regex, final scala.collection.Seq kvs) {
      if (scala.None..MODULE$.equals(regex)) {
         return kvs;
      } else if (regex instanceof Some) {
         Some var5 = (Some)regex;
         Regex r = (Regex)var5.value();
         return this.redact(r, kvs);
      } else {
         throw new MatchError(regex);
      }
   }

   public String redact(final Option regex, final String text) {
      if (scala.None..MODULE$.equals(regex)) {
         return text;
      } else if (regex instanceof Some) {
         Some var5 = (Some)regex;
         Regex r = (Regex)var5.value();
         return text != null && !text.isEmpty() ? r.replaceAllIn(text, this.REDACTION_REPLACEMENT_TEXT()) : text;
      } else {
         throw new MatchError(regex);
      }
   }

   private scala.collection.Seq redact(final Regex redactionPattern, final scala.collection.Seq kvs) {
      return (scala.collection.Seq)kvs.map((x0$1) -> {
         if (x0$1 != null) {
            Object key = x0$1._1();
            Object value = x0$1._2();
            if (key instanceof String) {
               String var6 = (String)key;
               if (value instanceof String) {
                  String var7 = (String)value;
                  return (Tuple2)redactionPattern.findFirstIn(var6).orElse(() -> redactionPattern.findFirstIn(var7)).map((x$24) -> new Tuple2(var6, MODULE$.REDACTION_REPLACEMENT_TEXT())).getOrElse(() -> new Tuple2(var6, var7));
               }
            }
         }

         if (x0$1 != null) {
            Object key = x0$1._1();
            Object value = x0$1._2();
            if (value instanceof String) {
               String var10 = (String)value;
               return (Tuple2)redactionPattern.findFirstIn(var10).map((x$25) -> new Tuple2(key, MODULE$.REDACTION_REPLACEMENT_TEXT())).getOrElse(() -> new Tuple2(key, var10));
            }
         }

         if (x0$1 != null) {
            Object key = x0$1._1();
            Object value = x0$1._2();
            return new Tuple2(key, value);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public scala.collection.Seq redact(final scala.collection.Map kvs) {
      Regex redactionPattern = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString((String)kvs.getOrElse(org.apache.spark.internal.config.package$.MODULE$.SECRET_REDACTION_PATTERN().key(), () -> org.apache.spark.internal.config.package$.MODULE$.SECRET_REDACTION_PATTERN().defaultValueString())));
      return this.redact((Regex)redactionPattern, (scala.collection.Seq)scala.Predef..MODULE$.wrapRefArray(kvs.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))));
   }

   public Seq redactCommandLineArgs(final SparkConf conf, final Seq commands) {
      Regex redactionPattern = (Regex)conf.get(org.apache.spark.internal.config.package$.MODULE$.SECRET_REDACTION_PATTERN());
      return (Seq)commands.map((x0$1) -> {
         if (x0$1 != null) {
            Option var5 = MODULE$.PATTERN_FOR_COMMAND_LINE_ARG().unapplySeq(x0$1);
            if (!var5.isEmpty() && var5.get() != null && ((List)var5.get()).lengthCompare(2) == 0) {
               String key = (String)((LinearSeqOps)var5.get()).apply(0);
               String value = (String)((LinearSeqOps)var5.get()).apply(1);
               Tuple2 var9 = (Tuple2)MODULE$.redact((Regex)redactionPattern, (scala.collection.Seq)(new scala.collection.immutable..colon.colon(new Tuple2(key, value), scala.collection.immutable.Nil..MODULE$))).head();
               if (var9 != null) {
                  String newValue = (String)var9._2();
                  return "-D" + key + "=" + newValue;
               }

               throw new MatchError(var9);
            }
         }

         return x0$1;
      });
   }

   public Seq loadExtensions(final Class extClass, final Seq classes, final SparkConf conf) {
      return (Seq)classes.flatMap((name) -> {
         Object var10000;
         try {
            Class klass = MODULE$.classForName(name, MODULE$.classForName$default$2(), MODULE$.classForName$default$3());
            scala.Predef..MODULE$.require(extClass.isAssignableFrom(klass), () -> name + " is not a subclass of " + extClass.getName() + ".");
            Try var7 = scala.util.Try..MODULE$.apply(() -> klass.getConstructor(SparkConf.class));
            if (var7 instanceof Success var8) {
               Constructor ctor = (Constructor)var8.value();
               var10000 = ctor.newInstance(conf);
            } else {
               if (!(var7 instanceof Failure)) {
                  throw new MatchError(var7);
               }

               var10000 = klass.getConstructor().newInstance();
            }

            Object ext = var10000;
            var10000 = new Some(ext);
         } catch (NoSuchMethodException var13) {
            throw new SparkException(name + " did not have a zero-argument constructor or a single-argument constructor that accepts SparkConf. Note: if the class is defined inside of another Scala class, then its constructors may accept an implicit parameter that references the enclosing class; in this case, you must define the class as a top-level class in order to prevent this extra parameter from breaking Spark's ability to find a valid constructor.");
         } catch (InvocationTargetException var14) {
            Throwable var11 = var14.getCause();
            if (!(var11 instanceof UnsupportedOperationException)) {
               if (var11 == null) {
                  throw var14;
               }

               throw var11;
            }

            UnsupportedOperationException var12 = (UnsupportedOperationException)var11;
            MODULE$.logDebug((Function0)(() -> "Extension " + name + " not being initialized."), var12);
            MODULE$.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Extension ", " not being initialized."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)})))));
            var10000 = scala.None..MODULE$;
         }

         return (IterableOnce)var10000;
      });
   }

   public String checkAndGetK8sMasterUrl(final String rawMasterURL) {
      scala.Predef..MODULE$.require(rawMasterURL.startsWith("k8s://"), () -> "Kubernetes master URL must start with k8s://.");
      String masterWithoutK8sPrefix = rawMasterURL.substring("k8s://".length());
      if (!masterWithoutK8sPrefix.contains("://")) {
         String resolvedURL = "https://" + masterWithoutK8sPrefix;
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No scheme specified for kubernetes master URL, so defaulting to https."})))).log(scala.collection.immutable.Nil..MODULE$).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" Resolved URL is ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URL..MODULE$, resolvedURL)}))))));
         return "k8s://" + resolvedURL;
      } else {
         String var10000;
         label33: {
            String masterScheme = (new URI(masterWithoutK8sPrefix)).getScheme();
            boolean var7 = false;
            Some var8 = null;
            Option var9 = scala.Option..MODULE$.apply(masterScheme).map((x$26) -> x$26.toLowerCase(Locale.ROOT));
            if (var9 instanceof Some) {
               var7 = true;
               var8 = (Some)var9;
               String var10 = (String)var8.value();
               if ("https".equals(var10)) {
                  var10000 = masterWithoutK8sPrefix;
                  break label33;
               }
            }

            if (!var7) {
               throw new IllegalArgumentException("Invalid Kubernetes master scheme: " + masterScheme + " found in URL: " + masterWithoutK8sPrefix);
            }

            String var11 = (String)var8.value();
            if (!"http".equals(var11)) {
               throw new IllegalArgumentException("Invalid Kubernetes master scheme: " + masterScheme + " found in URL: " + masterWithoutK8sPrefix);
            }

            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Kubernetes master URL uses HTTP instead of HTTPS."})))).log(scala.collection.immutable.Nil..MODULE$)));
            var10000 = masterWithoutK8sPrefix;
         }

         String resolvedURL = var10000;
         return "k8s://" + resolvedURL;
      }
   }

   public String substituteAppNExecIds(final String opt, final String appId, final String execId) {
      return opt.replace("{{APP_ID}}", appId).replace("{{EXECUTOR_ID}}", execId);
   }

   public String substituteAppId(final String opt, final String appId) {
      return opt.replace("{{APP_ID}}", appId);
   }

   public String createSecret(final SparkConf conf) {
      int bits = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.AUTH_SECRET_BIT_LENGTH()));
      SecureRandom rnd = new SecureRandom();
      byte[] secretBytes = new byte[bits / 8];
      rnd.nextBytes(secretBytes);
      return Hex.encodeHexString(secretBytes);
   }

   private Regex fullWidthRegex() {
      return fullWidthRegex;
   }

   public int stringHalfWidth(final String str) {
      return str == null ? 0 : str.length() + this.fullWidthRegex().findAllIn(str).size();
   }

   public String sanitizeDirName(final String str) {
      return str.replaceAll("[ :/]", "-").replaceAll("[.${}'\"]", "_").toLowerCase(Locale.ROOT);
   }

   public String nameForAppAndAttempt(final String appId, final Option appAttemptId) {
      String base = this.sanitizeDirName(appId);
      return appAttemptId.isDefined() ? base + "_" + this.sanitizeDirName((String)appAttemptId.get()) : base;
   }

   public boolean isClientMode(final SparkConf conf) {
      return "client".equals(conf.get("spark.submit.deployMode", "client"));
   }

   public boolean isLocalUri(final String uri) {
      return uri.startsWith(this.LOCAL_SCHEME() + ":");
   }

   public UriBuilder getUriBuilder(final URI uri) {
      return UriBuilder.fromUri(uri);
   }

   public UriBuilder getUriBuilder(final String uri) {
      return UriBuilder.fromUri(uri);
   }

   public boolean isFileSplittable(final Path path, final CompressionCodecFactory codecFactory) {
      CompressionCodec codec = codecFactory.getCodec(path);
      return codec == null || codec instanceof SplittableCompressionCodec;
   }

   public Properties cloneProperties(final Properties props) {
      if (props == null) {
         return props;
      } else {
         Properties resultProps = new Properties();
         resultProps.putAll((Properties)props.clone());
         return resultProps;
      }
   }

   public String buildLocationMetadata(final Seq paths, final int stopAppendingThreshold) {
      StringBuilder metadata = new StringBuilder("(" + paths.length() + " paths)[");

      int index;
      for(index = 0; index < paths.length() && metadata.length() < stopAppendingThreshold; ++index) {
         if (index > 0) {
            metadata.append(", ");
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         metadata.append(((Path)paths.apply(index)).toString());
      }

      if (paths.length() > index) {
         if (index > 0) {
            metadata.append(", ");
         } else {
            BoxedUnit var5 = BoxedUnit.UNIT;
         }

         metadata.append("...");
      } else {
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      metadata.append("]");
      return metadata.toString();
   }

   public int executorOffHeapMemorySizeAsMb(final SparkConf sparkConf) {
      int sizeInMB = this.memoryStringToMb(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_SIZE()).toString());
      return (int)this.checkOffHeapEnabled(sparkConf, (long)sizeInMB);
   }

   public long checkOffHeapEnabled(final SparkConf sparkConf, final long offHeapSize) {
      if (BoxesRunTime.unboxToBoolean(sparkConf.get(org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_ENABLED()))) {
         scala.Predef..MODULE$.require(offHeapSize > 0L, () -> {
            String var10000 = org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_SIZE().key();
            return var10000 + " must be > 0 when " + org.apache.spark.internal.config.package$.MODULE$.MEMORY_OFFHEAP_ENABLED().key() + " == true";
         });
         return offHeapSize;
      } else {
         return 0L;
      }
   }

   public MessageWithContext createFailedToGetTokenMessage(final String serviceName, final Throwable e) {
      return this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to get token from service ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"due to ", ". If ", " is not used, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, e), new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceName)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"set spark.security.credentials.", ".enabled to false."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SERVICE_NAME..MODULE$, serviceName)}))));
   }

   public Seq unzipFilesFromFile(final FileSystem fs, final Path dfsZipFile, final File localDir) {
      ArrayBuffer files = new ArrayBuffer();
      ZipInputStream in = new ZipInputStream(fs.open(dfsZipFile));
      OutputStream out = null;

      try {
         for(ZipEntry entry = in.getNextEntry(); entry != null; entry = in.getNextEntry()) {
            if (!entry.isDirectory()) {
               String fileName = localDir.toPath().resolve(entry.getName()).getFileName().toString();
               File outFile = new File(localDir, fileName);
               files.$plus$eq(outFile);
               out = new FileOutputStream(outFile);
               IOUtils.copy(in, out);
               out.close();
               in.closeEntry();
            }
         }

         in.close();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unzipped from ", "\\n\\t", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dfsZipFile), new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, files.mkString("\n\t"))})))));
      } finally {
         IOUtils.closeQuietly(in);
         IOUtils.closeQuietly(out);
      }

      return files.toSeq();
   }

   public Closeable createResourceUninterruptiblyIfInTaskThread(final Function0 createResource) {
      return (Closeable)scala.Option..MODULE$.apply(TaskContext$.MODULE$.get()).map((x$27) -> x$27.createResourceUninterruptibly(createResource)).getOrElse(createResource);
   }

   public long median(final long[] sizes, final boolean alreadySorted) {
      int len = sizes.length;
      long[] sortedSize = alreadySorted ? sizes : (long[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.longArrayOps(sizes), scala.math.Ordering.Long..MODULE$);
      switch (len) {
         default -> {
            return len % 2 == 0 ? scala.math.package..MODULE$.max((sortedSize[len / 2] + sortedSize[len / 2 - 1]) / 2L, 1L) : scala.math.package..MODULE$.max(sortedSize[len / 2], 1L);
         }
      }
   }

   public boolean checkCommandAvailable(final String command) {
      Try attempt = this.isWindows() ? scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.sys.process.Process..MODULE$.apply(new scala.collection.immutable..colon.colon("cmd.exe", new scala.collection.immutable..colon.colon("/C", new scala.collection.immutable..colon.colon("where " + command, scala.collection.immutable.Nil..MODULE$)))).run(scala.sys.process.ProcessLogger..MODULE$.apply((x$28) -> {
            $anonfun$checkCommandAvailable$2(x$28);
            return BoxedUnit.UNIT;
         })).exitValue()) : scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.sys.process.Process..MODULE$.apply(new scala.collection.immutable..colon.colon("sh", new scala.collection.immutable..colon.colon("-c", new scala.collection.immutable..colon.colon("command -v " + command, scala.collection.immutable.Nil..MODULE$)))).run(scala.sys.process.ProcessLogger..MODULE$.apply((x$29) -> {
            $anonfun$checkCommandAvailable$4(x$29);
            return BoxedUnit.UNIT;
         })).exitValue());
      return attempt.isSuccess() && BoxesRunTime.unboxToInt(attempt.get()) == 0;
   }

   private boolean isG1GC$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            isG1GC = BoxesRunTime.unboxToBoolean(scala.util.Try..MODULE$.apply((JFunction0.mcZ.sp)() -> {
               Class clazz = MODULE$.classForName("com.sun.management.HotSpotDiagnosticMXBean", MODULE$.classForName$default$2(), MODULE$.classForName$default$3());
               Class vmOptionClazz = MODULE$.classForName("com.sun.management.VMOption", MODULE$.classForName$default$2(), MODULE$.classForName$default$3());
               PlatformManagedObject hotSpotDiagnosticMXBean = ManagementFactory.getPlatformMXBean(clazz);
               Method vmOptionMethod = clazz.getMethod("getVMOption", String.class);
               Method valueMethod = vmOptionClazz.getMethod("getValue");
               Object useG1GCObject = vmOptionMethod.invoke(hotSpotDiagnosticMXBean, "UseG1GC");
               String useG1GC = (String)valueMethod.invoke(useG1GCObject);
               return "true".equals(useG1GC);
            }).getOrElse((JFunction0.mcZ.sp)() -> false));
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return isG1GC;
   }

   public boolean isG1GC() {
      return (byte)(bitmap$0 & 4) == 0 ? this.isG1GC$lzycompute() : isG1GC;
   }

   // $FF: synthetic method
   public static final void $anonfun$writeByteBuffer$1(final DataOutput out$1, final byte[] x$1, final int x$2, final int x$3) {
      out$1.write(x$1, x$2, x$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$writeByteBuffer$2(final OutputStream out$2, final byte[] x$1, final int x$2, final int x$3) {
      out$2.write(x$1, x$2, x$3);
   }

   // $FF: synthetic method
   public static final ByteBuffer $anonfun$copyStreamUpTo$1(final int x$1) {
      return ByteBuffer.allocate(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filesEqualRecursive$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         File f1 = (File)x0$1._1();
         File f2 = (File)x0$1._2();
         return MODULE$.filesEqualRecursive(f1, f2);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$copyRecursive$1(final File dest$2, final File f) {
      MODULE$.copyRecursive(f, new File(dest$2, f.getName()));
   }

   // $FF: synthetic method
   public static final void $anonfun$fetchHcfsFile$2(final File dest$3, final FileSystem fs$1, final SparkConf conf$1, final Configuration hadoopConf$1, final boolean fileOverwrite$1, final FileStatus fileStatus) {
      MODULE$.fetchHcfsFile(fileStatus.getPath(), dest$3, fs$1, conf$1, hadoopConf$1, fileOverwrite$1, MODULE$.fetchHcfsFile$default$7());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getOrCreateLocalRootDirsImpl$1(final String root) {
      return BoxesRunTime.unboxToBoolean(scala.util.Try..MODULE$.apply((JFunction0.mcZ.sp)() -> (new URI(root)).getScheme() != null).getOrElse((JFunction0.mcZ.sp)() -> false));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findLocalInetAddress$2(final InetAddress addr) {
      return addr.isLinkLocalAddress() || addr.isLoopbackAddress();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findLocalInetAddress$3(final InetAddress x$3) {
      return x$3 instanceof Inet4Address;
   }

   // $FF: synthetic method
   public static final void $anonfun$findLocalInetAddress$1(final InetAddress address$1, final Object nonLocalReturnKey1$1, final NetworkInterface ni) {
      Seq addresses = scala.jdk.CollectionConverters..MODULE$.EnumerationHasAsScala(ni.getInetAddresses()).asScala().filterNot((addrx) -> BoxesRunTime.boxToBoolean($anonfun$findLocalInetAddress$2(addrx))).toSeq();
      if (addresses.nonEmpty()) {
         InetAddress addr = (InetAddress)addresses.find((x$3) -> BoxesRunTime.boxToBoolean($anonfun$findLocalInetAddress$3(x$3))).getOrElse(() -> (InetAddress)addresses.head());
         InetAddress strippedAddress = InetAddress.getByAddress(addr.getAddress());
         MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Your hostname, ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, InetAddress.getLocalHost().getHostName())}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"resolves to a loopback address: ", "; "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, address$1.getHostAddress())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"using ", " instead (on interface "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT2..MODULE$, strippedAddress.getHostAddress())})))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NETWORK_IF..MODULE$, ni.getName())}))))));
         MODULE$.logWarning((Function0)(() -> "Set SPARK_LOCAL_IP if you need to bind to another address"));
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, strippedAddress);
      }
   }

   private final Tuple2 setDefaultPortValue$1(final String hostPort$2) {
      Tuple2 retval = new Tuple2(hostPort$2, BoxesRunTime.boxToInteger(0));
      this.hostPortParseResults().put(hostPort$2, retval);
      return retval;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doesDirectoryContainAnyNewFiles$1(final long cutoffTimeInMillis$1, final File x$4) {
      return x$4.lastModified() > cutoffTimeInMillis$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doesDirectoryContainAnyNewFiles$2(final File x$5) {
      return x$5.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doesDirectoryContainAnyNewFiles$3(final long cutoff$1, final File subdir) {
      return MODULE$.doesDirectoryContainAnyNewFiles(subdir, cutoff$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$executeCommand$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   private final void log$1(final String s) {
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LINE..MODULE$, s)})))));
   }

   // $FF: synthetic method
   public static final void $anonfun$executeCommand$4(final Utils$ $this, final String s) {
      $this.log$1(s);
   }

   private static final void appendToOutput$1(final String s, final StringBuilder output$1) {
      output$1.append(s).append("\n");
   }

   // $FF: synthetic method
   public static final void $anonfun$executeAndGetOutput$1(final StringBuilder output$1, final String s) {
      appendToOutput$1(s, output$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doTryWithCallerStacktrace$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var10000;
         label30: {
            StackTraceElement exElem = (StackTraceElement)x0$1._1();
            StackTraceElement currentElem = (StackTraceElement)x0$1._2();
            if (exElem == null) {
               if (currentElem == null) {
                  break label30;
               }
            } else if (exElem.equals(currentElem)) {
               break label30;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$doTryWithCallerStacktrace$2(final Throwable e) {
      return e instanceof Utils.OriginalTryStackTraceException;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getTryWithCallerStacktrace$1(final Throwable e) {
      return e instanceof Utils.OriginalTryStackTraceException;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCallSite$default$1$1(final String className) {
      return MODULE$.sparkInternalExclusionFunction(className);
   }

   // $FF: synthetic method
   public static final void $anonfun$offsetBytes$2(final LongRef sum$1, final scala.collection.immutable.Map fileToLength$1, final long endIndex$1, final long startIndex$1, final StringBuffer stringBuffer$1, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         File file = (File)x0$1._1();
         long fileLength = x0$1._2$mcJ$sp();
         long startIndexOfFile = sum$1.elem;
         long endIndexOfFile = sum$1.elem + BoxesRunTime.unboxToLong(fileToLength$1.apply(file));
         MODULE$.logDebug((Function0)(() -> "Processing file " + file + ", with start index = " + startIndexOfFile + ", end index = " + endIndex$1));
         if (startIndex$1 <= startIndexOfFile && endIndex$1 >= endIndexOfFile) {
            stringBuffer$1.append(MODULE$.offsetBytes(file.getAbsolutePath(), fileLength, 0L, BoxesRunTime.unboxToLong(fileToLength$1.apply(file))));
         } else if (startIndex$1 > startIndexOfFile && startIndex$1 < endIndexOfFile) {
            long effectiveStartIndex = startIndex$1 - startIndexOfFile;
            long effectiveEndIndex = scala.math.package..MODULE$.min(endIndex$1 - startIndexOfFile, BoxesRunTime.unboxToLong(fileToLength$1.apply(file)));
            stringBuffer$1.append(MODULE$.offsetBytes(file.getAbsolutePath(), fileLength, effectiveStartIndex, effectiveEndIndex));
         } else if (endIndex$1 > startIndexOfFile && endIndex$1 < endIndexOfFile) {
            long effectiveStartIndex = scala.math.package..MODULE$.max(startIndex$1 - startIndexOfFile, 0L);
            long effectiveEndIndex = endIndex$1 - startIndexOfFile;
            stringBuffer$1.append(MODULE$.offsetBytes(file.getAbsolutePath(), fileLength, effectiveStartIndex, effectiveEndIndex));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         sum$1.elem += BoxesRunTime.unboxToLong(fileToLength$1.apply(file));
         MODULE$.logDebug((Function0)(() -> "After processing file " + file + ", string built is " + stringBuffer$1.toString()));
         BoxedUnit var25 = BoxedUnit.UNIT;
      }
   }

   private static final void endWord$1(final ArrayBuffer buf$1, final StringBuilder curWord$1) {
      buf$1.$plus$eq(curWord$1.toString());
      curWord$1.clear();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$resolveURIs$1(final String x$7) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$7.trim()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$nonLocalPaths$1(final boolean windows$1, final String p) {
      URI uri = MODULE$.resolveURI(p);
      String var5 = (String)scala.Option..MODULE$.apply(uri.getScheme()).getOrElse(() -> "file");
      if (var5 != null) {
         Option var6 = MODULE$.windowsDrive().unapplySeq(var5);
         if (!var6.isEmpty() && var6.get() != null && ((List)var6.get()).lengthCompare(1) == 0 && windows$1) {
            return false;
         }
      }

      return !("local".equals(var5) ? true : "file".equals(var5));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadDefaultSparkProperties$3(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith("spark.");
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$loadDefaultSparkProperties$2(final SparkConf conf$2, final String confFile) {
      ((IterableOnceOps)MODULE$.getPropertiesFromFile(confFile).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$loadDefaultSparkProperties$3(x0$1)))).foreach((x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            conf$2.setIfMissing(k, v);
            return (String)scala.sys.package..MODULE$.props().getOrElseUpdate(k, () -> v);
         } else {
            throw new MatchError(x0$2);
         }
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$trimExceptCRLF$1(final char ch) {
      return ch > ' ' || ch == '\r' || ch == '\n';
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getDefaultPropertiesFile$4(final File x$8) {
      return x$8.isFile();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getThreadDump$1(final ThreadInfo x$10) {
      return x$10 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getThreadDump$2(final ThreadInfo x0$1, final ThreadInfo x1$1) {
      Tuple2 var3 = new Tuple2(x0$1, x1$1);
      if (var3 != null) {
         ThreadInfo threadTrace1 = (ThreadInfo)var3._1();
         ThreadInfo threadTrace2 = (ThreadInfo)var3._2();
         int v1 = threadTrace1.getThreadName().contains("Executor task launch") ? 1 : 0;
         int v2 = threadTrace2.getThreadName().contains("Executor task launch") ? 1 : 0;
         if (v1 == v2) {
            String name1 = threadTrace1.getThreadName().toLowerCase(Locale.ROOT);
            String name2 = threadTrace2.getThreadName().toLowerCase(Locale.ROOT);
            int nameCmpRes = name1.compareTo(name2);
            if (nameCmpRes == 0) {
               return threadTrace1.getThreadId() < threadTrace2.getThreadId();
            } else {
               return nameCmpRes < 0;
            }
         } else {
            return v1 > v2;
         }
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getHeapHistogram$2(final ArrayBuffer rows$1, final BufferedReader r) {
      for(String line = ""; line != null; line = r.readLine()) {
         if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(line))) {
            rows$1.$plus$eq(line);
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$sparkJavaOpts$1(final Function1 filterKey$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return BoxesRunTime.unboxToBoolean(filterKey$1.apply(k));
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$sparkJavaOpts$default$2$1(final String x$13) {
      return true;
   }

   // $FF: synthetic method
   public static final int $anonfun$portMaxRetries$1(final String x$14) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$14));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isBindCollision$1(final Throwable exception) {
      return MODULE$.isBindCollision(exception);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isPushBasedShuffleEnabled$3(final Serializer x$19) {
      return x$19 != null;
   }

   // $FF: synthetic method
   private static final boolean serializerIsSupported$lzycompute$1(final LazyBoolean serializerIsSupported$lzy$1, final boolean checkSerializer$1, final SparkConf conf$3, final boolean isDriver$1) {
      synchronized(serializerIsSupported$lzy$1){}

      boolean var5;
      try {
         var5 = serializerIsSupported$lzy$1.initialized() ? serializerIsSupported$lzy$1.value() : serializerIsSupported$lzy$1.initialize(checkSerializer$1 ? ((Serializer)scala.Option..MODULE$.apply(SparkEnv$.MODULE$.get()).map((x$18) -> x$18.serializer()).filter((x$19) -> BoxesRunTime.boxToBoolean($anonfun$isPushBasedShuffleEnabled$3(x$19))).getOrElse(() -> (Serializer)MODULE$.instantiateSerializerFromConf(org.apache.spark.internal.config.package$.MODULE$.SERIALIZER(), conf$3, isDriver$1))).supportsRelocationOfSerializedObjects() : true);
      } catch (Throwable var7) {
         throw var7;
      }

      return var5;
   }

   private static final boolean serializerIsSupported$1(final LazyBoolean serializerIsSupported$lzy$1, final boolean checkSerializer$1, final SparkConf conf$3, final boolean isDriver$1) {
      return serializerIsSupported$lzy$1.initialized() ? serializerIsSupported$lzy$1.value() : serializerIsSupported$lzycompute$1(serializerIsSupported$lzy$1, checkSerializer$1, conf$3, isDriver$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getUserJars$1(final String x$20) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$20));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocalUserJarsForShell$3(final String x$23) {
      return .MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(x$23));
   }

   // $FF: synthetic method
   public static final void $anonfun$checkCommandAvailable$2(final String x$28) {
   }

   // $FF: synthetic method
   public static final void $anonfun$checkCommandAvailable$4(final String x$29) {
   }

   private Utils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
