package org.apache.spark.deploy.master;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.serializer.Serializer;
import org.rocksdb.BlockBasedTableConfig;
import org.rocksdb.BloomFilter;
import org.rocksdb.CompressionType;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b!B\t\u0013\u0001Ia\u0002\u0002C\u0014\u0001\u0005\u000b\u0007I\u0011A\u0015\t\u0011]\u0002!\u0011!Q\u0001\n)B\u0001\u0002\u000f\u0001\u0003\u0006\u0004%\t!\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005u!)\u0001\t\u0001C\u0001\u0003\"9Q\t\u0001b\u0001\n\u00131\u0005BB)\u0001A\u0003%q\tC\u0004S\u0001\t\u0007I\u0011B*\t\ri\u0003\u0001\u0015!\u0003U\u0011\u001dY\u0006A1A\u0005\nqCa\u0001\u0019\u0001!\u0002\u0013i\u0006bB1\u0001\u0005\u0004%IA\u0019\u0005\u0007M\u0002\u0001\u000b\u0011B2\t\u000b\u001d\u0004A\u0011\t5\t\u000b]\u0004A\u0011\t=\t\u000bi\u0004A\u0011I>\u00031I{7m[:E\u0005B+'o]5ti\u0016t7-Z#oO&tWM\u0003\u0002\u0014)\u00051Q.Y:uKJT!!\u0006\f\u0002\r\u0011,\u0007\u000f\\8z\u0015\t9\u0002$A\u0003ta\u0006\u00148N\u0003\u0002\u001a5\u00051\u0011\r]1dQ\u0016T\u0011aG\u0001\u0004_J<7c\u0001\u0001\u001eCA\u0011adH\u0007\u0002%%\u0011\u0001E\u0005\u0002\u0012!\u0016\u00148/[:uK:\u001cW-\u00128hS:,\u0007C\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0017\u0003!Ig\u000e^3s]\u0006d\u0017B\u0001\u0014$\u0005\u001daunZ4j]\u001e\f1\u0001Z5s\u0007\u0001)\u0012A\u000b\t\u0003WQr!\u0001\f\u001a\u0011\u00055\u0002T\"\u0001\u0018\u000b\u0005=B\u0013A\u0002\u001fs_>$hHC\u00012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0004'\u0001\u0004Qe\u0016$WMZ\u0005\u0003kY\u0012aa\u0015;sS:<'BA\u001a1\u0003\u0011!\u0017N\u001d\u0011\u0002\u0015M,'/[1mSj,'/F\u0001;!\tYT(D\u0001=\u0015\tAd#\u0003\u0002?y\tQ1+\u001a:jC2L'0\u001a:\u0002\u0017M,'/[1mSj,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\t\u001bE\t\u0005\u0002\u001f\u0001!)q%\u0002a\u0001U!)\u0001(\u0002a\u0001u\u0005!\u0001/\u0019;i+\u00059\u0005C\u0001%P\u001b\u0005I%B\u0001&L\u0003\u00111\u0017\u000e\\3\u000b\u00051k\u0015a\u00018j_*\ta*\u0001\u0003kCZ\f\u0017B\u0001)J\u0005\u0011\u0001\u0016\r\u001e5\u0002\u000bA\fG\u000f\u001b\u0011\u0002#Q\f'\r\\3G_Jl\u0017\r^\"p]\u001aLw-F\u0001U!\t)\u0006,D\u0001W\u0015\t9&$A\u0004s_\u000e\\7\u000f\u001a2\n\u0005e3&!\u0006\"m_\u000e\\')Y:fIR\u000b'\r\\3D_:4\u0017nZ\u0001\u0013i\u0006\u0014G.\u001a$pe6\fGoQ8oM&<\u0007%A\u0004paRLwN\\:\u0016\u0003u\u0003\"!\u00160\n\u0005}3&aB(qi&|gn]\u0001\t_B$\u0018n\u001c8tA\u0005\u0011AMY\u000b\u0002GB\u0011Q\u000bZ\u0005\u0003KZ\u0013qAU8dWN$%)A\u0002eE\u0002\nq\u0001]3sg&\u001cH\u000fF\u0002j[>\u0004\"A[6\u000e\u0003AJ!\u0001\u001c\u0019\u0003\tUs\u0017\u000e\u001e\u0005\u0006]:\u0001\rAK\u0001\u0005]\u0006lW\rC\u0003q\u001d\u0001\u0007\u0011/A\u0002pE*\u0004\"A];\u000e\u0003MT!\u0001^'\u0002\t1\fgnZ\u0005\u0003mN\u0014aa\u00142kK\u000e$\u0018!C;oa\u0016\u00148/[:u)\tI\u0017\u0010C\u0003o\u001f\u0001\u0007!&\u0001\u0003sK\u0006$Wc\u0001?\u0002\u0014Q\u0019Q0!\u000e\u0015\u0007y\f)\u0003E\u0003\u0000\u0003\u0013\tyA\u0004\u0003\u0002\u0002\u0005\u0015abA\u0017\u0002\u0004%\t\u0011'C\u0002\u0002\bA\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\f\u00055!aA*fc*\u0019\u0011q\u0001\u0019\u0011\t\u0005E\u00111\u0003\u0007\u0001\t\u001d\t)\u0002\u0005b\u0001\u0003/\u0011\u0011\u0001V\t\u0005\u00033\ty\u0002E\u0002k\u00037I1!!\b1\u0005\u001dqu\u000e\u001e5j]\u001e\u00042A[A\u0011\u0013\r\t\u0019\u0003\r\u0002\u0004\u0003:L\b\"CA\u0014!\u0005\u0005\t9AA\u0015\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003W\t\t$a\u0004\u000e\u0005\u00055\"bAA\u0018a\u00059!/\u001a4mK\u000e$\u0018\u0002BA\u001a\u0003[\u0011\u0001b\u00117bgN$\u0016m\u001a\u0005\u0006]B\u0001\rA\u000b"
)
public class RocksDBPersistenceEngine extends PersistenceEngine implements Logging {
   private final String dir;
   private final Serializer serializer;
   private final Path path;
   private final BlockBasedTableConfig tableFormatConfig;
   private final Options options;
   private final RocksDB db;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String dir() {
      return this.dir;
   }

   public Serializer serializer() {
      return this.serializer;
   }

   private Path path() {
      return this.path;
   }

   private BlockBasedTableConfig tableFormatConfig() {
      return this.tableFormatConfig;
   }

   private Options options() {
      return this.options;
   }

   private RocksDB db() {
      return this.db;
   }

   public void persist(final String name, final Object obj) {
      ByteBuffer serialized = this.serializer().newInstance().serialize(obj, .MODULE$.Object());
      if (serialized.hasArray()) {
         this.db().put(name.getBytes(StandardCharsets.UTF_8), serialized.array());
      } else {
         byte[] bytes = new byte[serialized.remaining()];
         serialized.get(bytes);
         this.db().put(name.getBytes(StandardCharsets.UTF_8), bytes);
      }
   }

   public void unpersist(final String name) {
      this.db().delete(name.getBytes(StandardCharsets.UTF_8));
   }

   public Seq read(final String name, final ClassTag evidence$1) {
      ArrayBuffer result = new ArrayBuffer();
      RocksIterator iter = this.db().newIterator();

      try {
         iter.seek(name.getBytes(StandardCharsets.UTF_8));

         while(iter.isValid() && (new String(iter.key())).startsWith(name)) {
            result.append(this.serializer().newInstance().deserialize(ByteBuffer.wrap(iter.value()), evidence$1));
            iter.next();
         }
      } finally {
         iter.close();
      }

      return result.toSeq();
   }

   // $FF: synthetic method
   private final Path liftedTree1$1() {
      Path var10000;
      try {
         var10000 = Files.createDirectories(Paths.get(this.dir()));
      } catch (Throwable var4) {
         if (!(var4 instanceof FileAlreadyExistsException) || !Files.isSymbolicLink(Paths.get(this.dir()))) {
            throw var4;
         }

         var10000 = Files.createDirectories(Paths.get(this.dir()).toRealPath());
      }

      return var10000;
   }

   public RocksDBPersistenceEngine(final String dir, final Serializer serializer) {
      this.dir = dir;
      this.serializer = serializer;
      Logging.$init$(this);
      RocksDB.loadLibrary();
      this.path = this.liftedTree1$1();
      this.tableFormatConfig = (new BlockBasedTableConfig()).setFilterPolicy(new BloomFilter((double)10.0F, false)).setEnableIndexCompression(false).setIndexBlockRestartInterval(8).setFormatVersion(5);
      this.options = (new Options()).setCreateIfMissing(true).setBottommostCompressionType(CompressionType.ZSTD_COMPRESSION).setCompressionType(CompressionType.LZ4_COMPRESSION).setTableFormatConfig(this.tableFormatConfig());
      this.db = RocksDB.open(this.options(), this.path().toString());
   }
}
