package org.apache.spark.executor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.util.ParentClassLoader;
import org.apache.xbean.asm9.ClassReader;
import org.apache.xbean.asm9.ClassWriter;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction0;
import scala.util.control.NonFatal.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug\u0001\u0002\u0010 \u0001!B\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\ty\u0001\u0011\t\u0011)A\u0005{!A\u0001\t\u0001B\u0001B\u0003%\u0011\t\u0003\u0005O\u0001\t\u0005\t\u0015!\u0003*\u0011!y\u0005A!A!\u0002\u0013\u0001\u0006\"\u0002+\u0001\t\u0003)\u0006bB/\u0001\u0005\u0004%\tA\u0018\u0005\u0007K\u0002\u0001\u000b\u0011B0\t\u000f\u0019\u0004!\u0019!C\u0001O\"1!\u000e\u0001Q\u0001\n!Dqa\u001b\u0001C\u0002\u0013\u0005A\u000e\u0003\u0004t\u0001\u0001\u0006I!\u001c\u0005\ti\u0002\u0001\r\u0011\"\u0001 k\"A\u0011\u0010\u0001a\u0001\n\u0003y\"\u0010C\u0004\u0002\u0002\u0001\u0001\u000b\u0015\u0002<\t\u0013\u0005\r\u0001A1A\u0005\n\u0005\u0015\u0001\u0002CA\r\u0001\u0001\u0006I!a\u0002\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e!9\u0011\u0011\u0006\u0001\u0005B\u0005-\u0002bBA\u001d\u0001\u0011\u0005\u00131\b\u0005\b\u0003\u007f\u0001A\u0011BA!\u0011\u001d\t)\u0005\u0001C!\u0003\u000fB\u0011\"!\u001b\u0001\u0005\u0004%I!a\u001b\t\u0011\u0005e\u0004\u0001)A\u0005\u0003[Bq!a\u001f\u0001\t\u0013\ti\bC\u0004\u0002\u0004\u0002!I!!\"\t\u000f\u0005\u0005\u0006\u0001\"\u0001\u0002$\"9\u0011q\u0017\u0001\u0005\u0002\u0005e\u0006bBAg\u0001\u0011\u0005\u0011q\u001a\u0002\u0014\u000bb,7-\u001e;pe\u000ec\u0017m]:M_\u0006$WM\u001d\u0006\u0003A\u0005\n\u0001\"\u001a=fGV$xN\u001d\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sO\u000e\u00011c\u0001\u0001*cA\u0011!fL\u0007\u0002W)\u0011A&L\u0001\u0005Y\u0006twMC\u0001/\u0003\u0011Q\u0017M^1\n\u0005AZ#aC\"mCN\u001cHj\\1eKJ\u0004\"AM\u001b\u000e\u0003MR!\u0001N\u0011\u0002\u0011%tG/\u001a:oC2L!AN\u001a\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g!\tI$(D\u0001\"\u0013\tY\u0014EA\u0005Ta\u0006\u00148nQ8oM\u0006\u0019QM\u001c<\u0011\u0005er\u0014BA \"\u0005!\u0019\u0006/\u0019:l\u000b:4\u0018\u0001C2mCN\u001cXK]5\u0011\u0005\t[eBA\"J!\t!u)D\u0001F\u0015\t1u%\u0001\u0004=e>|GO\u0010\u0006\u0002\u0011\u0006)1oY1mC&\u0011!jR\u0001\u0007!J,G-\u001a4\n\u00051k%AB*ue&twM\u0003\u0002K\u000f\u00061\u0001/\u0019:f]R\f!#^:fe\u000ec\u0017m]:QCRDg)\u001b:tiB\u0011\u0011KU\u0007\u0002\u000f&\u00111k\u0012\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q1a\u000bW-[7r\u0003\"a\u0016\u0001\u000e\u0003}AQa\u000e\u0004A\u0002aBQ\u0001\u0010\u0004A\u0002uBQ\u0001\u0011\u0004A\u0002\u0005CQA\u0014\u0004A\u0002%BQa\u0014\u0004A\u0002A\u000b1!\u001e:j+\u0005y\u0006C\u00011d\u001b\u0005\t'B\u00012.\u0003\rqW\r^\u0005\u0003I\u0006\u00141!\u0016*J\u0003\u0011)(/\u001b\u0011\u0002\u0013\u0011L'/Z2u_JLX#\u00015\u0011\u0005)J\u0017B\u0001',\u0003)!\u0017N]3di>\u0014\u0018\u0010I\u0001\ra\u0006\u0014XM\u001c;M_\u0006$WM]\u000b\u0002[B\u0011a.]\u0007\u0002_*\u0011\u0001/I\u0001\u0005kRLG.\u0003\u0002s_\n\t\u0002+\u0019:f]R\u001cE.Y:t\u0019>\fG-\u001a:\u0002\u001bA\f'/\u001a8u\u0019>\fG-\u001a:!\u0003yAG\u000f\u001e9Ve2\u001cuN\u001c8fGRLwN\u001c+j[\u0016|W\u000f^'jY2L7/F\u0001w!\t\tv/\u0003\u0002y\u000f\n\u0019\u0011J\u001c;\u0002E!$H\u000f]+sY\u000e{gN\\3di&|g\u000eV5nK>,H/T5mY&\u001cx\fJ3r)\tYh\u0010\u0005\u0002Ry&\u0011Qp\u0012\u0002\u0005+:LG\u000fC\u0004\u0000\u001d\u0005\u0005\t\u0019\u0001<\u0002\u0007a$\u0013'A\u0010iiR\u0004XK\u001d7D_:tWm\u0019;j_:$\u0016.\\3pkRl\u0015\u000e\u001c7jg\u0002\nqAZ3uG\"4e.\u0006\u0002\u0002\bA1\u0011+!\u0003B\u0003\u001bI1!a\u0003H\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0010\u0005UQBAA\t\u0015\r\t\u0019\"L\u0001\u0003S>LA!a\u0006\u0002\u0012\tY\u0011J\u001c9viN#(/Z1n\u0003!1W\r^2i\r:\u0004\u0013aC4fiJ+7o\\;sG\u0016$B!a\b\u0002&A\u0019\u0001-!\t\n\u0007\u0005\r\u0012MA\u0002V%2Ca!a\n\u0013\u0001\u0004\t\u0015\u0001\u00028b[\u0016\fAbZ3u%\u0016\u001cx.\u001e:dKN$B!!\f\u00028A1\u0011qFA\u001a\u0003?i!!!\r\u000b\u0005Al\u0013\u0002BA\u001b\u0003c\u00111\"\u00128v[\u0016\u0014\u0018\r^5p]\"1\u0011qE\nA\u0002\u0005\u000b1cZ3u%\u0016\u001cx.\u001e:dK\u0006\u001b8\u000b\u001e:fC6$B!!\u0004\u0002>!1\u0011q\u0005\u000bA\u0002\u0005\u000bqdZ3u\u00072\f7o\u001d*fg>,(oY3BgN#(/Z1n\u0019>\u001c\u0017\r\u001c7z)\u0011\ti!a\u0011\t\r\u0005\u001dR\u00031\u0001B\u0003%1\u0017N\u001c3DY\u0006\u001c8\u000f\u0006\u0003\u0002J\u0005\u001d\u0004\u0007BA&\u0003+\u0002RAQA'\u0003#J1!a\u0014N\u0005\u0015\u0019E.Y:t!\u0011\t\u0019&!\u0016\r\u0001\u0011Y\u0011q\u000b\f\u0002\u0002\u0003\u0005)\u0011AA-\u0005\ryF%M\t\u0005\u00037\n\t\u0007E\u0002R\u0003;J1!a\u0018H\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!UA2\u0013\r\t)g\u0012\u0002\u0004\u0003:L\bBBA\u0014-\u0001\u0007\u0011)\u0001\fT)J+\u0015)T0O\u001fR{fiT+O\t~\u0013ViR#Y+\t\ti\u0007\u0005\u0003\u0002p\u0005UTBAA9\u0015\u0011\t\u0019(!\r\u0002\u000bI,w-\u001a=\n\t\u0005]\u0014\u0011\u000f\u0002\b!\u0006$H/\u001a:o\u0003]\u0019FKU#B\u001b~su\nV0G\u001fVsEi\u0018*F\u000f\u0016C\u0006%A\u0012hKR\u001cE.Y:t\r&dW-\u00138qkR\u001cFO]3b[\u001a\u0013x.\\*qCJ\\'\u000bU\"\u0015\t\u00055\u0011q\u0010\u0005\u0007\u0003\u0003K\u0002\u0019A!\u0002\tA\fG\u000f[\u0001&O\u0016$8\t\\1tg\u001aKG.Z%oaV$8\u000b\u001e:fC64%o\\7GS2,7+_:uK6$B!a\"\u0002\u000eR!\u0011QBAE\u0011\u0019\tYI\u0007a\u0001\u0003\u0006y\u0001/\u0019;i\u0013:$\u0015N]3di>\u0014\u0018\u0010C\u0004\u0002\u0010j\u0001\r!!%\u0002\u0015\u0019LG.Z*zgR,W\u000e\u0005\u0003\u0002\u0014\u0006uUBAAK\u0015\u0011\t9*!'\u0002\u0005\u0019\u001c(bAANG\u00051\u0001.\u00193p_BLA!a(\u0002\u0016\nQa)\u001b7f'f\u001cH/Z7\u0002!\u0019Lg\u000eZ\"mCN\u001cHj\\2bY2LH\u0003BAS\u0003k\u0003R!UAT\u0003WK1!!+H\u0005\u0019y\u0005\u000f^5p]B\"\u0011QVAY!\u0015\u0011\u0015QJAX!\u0011\t\u0019&!-\u0005\u0017\u0005M6$!A\u0001\u0002\u000b\u0005\u0011\u0011\f\u0002\u0004?\u0012\u0012\u0004BBA\u00147\u0001\u0007\u0011)A\u000bsK\u0006$\u0017I\u001c3Ue\u0006t7OZ8s[\u000ec\u0017m]:\u0015\r\u0005m\u0016qYAe!\u0015\t\u0016QXAa\u0013\r\tyl\u0012\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004#\u0006\r\u0017bAAc\u000f\n!!)\u001f;f\u0011\u0019\t9\u0003\ba\u0001\u0003\"9\u00111\u001a\u000fA\u0002\u00055\u0011AA5o\u0003%)(\u000f\\#oG>$W\rF\u0002B\u0003#Da!a5\u001e\u0001\u0004\t\u0015aA:ue\u0002"
)
public class ExecutorClassLoader extends ClassLoader implements Logging {
   private final SparkEnv env;
   private final String classUri;
   private final boolean userClassPathFirst;
   private final URI uri;
   private final String directory;
   private final ParentClassLoader parentLoader;
   private int httpUrlConnectionTimeoutMillis;
   private final Function1 fetchFn;
   private final Pattern org$apache$spark$executor$ExecutorClassLoader$$STREAM_NOT_FOUND_REGEX;
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

   public URI uri() {
      return this.uri;
   }

   public String directory() {
      return this.directory;
   }

   public ParentClassLoader parentLoader() {
      return this.parentLoader;
   }

   public int httpUrlConnectionTimeoutMillis() {
      return this.httpUrlConnectionTimeoutMillis;
   }

   public void httpUrlConnectionTimeoutMillis_$eq(final int x$1) {
      this.httpUrlConnectionTimeoutMillis = x$1;
   }

   private Function1 fetchFn() {
      return this.fetchFn;
   }

   public URL getResource(final String name) {
      return this.parentLoader().getResource(name);
   }

   public Enumeration getResources(final String name) {
      return this.parentLoader().getResources(name);
   }

   public InputStream getResourceAsStream(final String name) {
      if (this.userClassPathFirst) {
         InputStream res = this.getClassResourceAsStreamLocally(name);
         return res != null ? res : this.parentLoader().getResourceAsStream(name);
      } else {
         InputStream res = this.parentLoader().getResourceAsStream(name);
         return res != null ? res : this.getClassResourceAsStreamLocally(name);
      }
   }

   private InputStream getClassResourceAsStreamLocally(final String name) {
      InputStream var10000;
      try {
         var10000 = name.endsWith(".class") ? (InputStream)this.fetchFn().apply(name) : null;
      } catch (ClassNotFoundException var2) {
         var10000 = null;
      }

      return var10000;
   }

   public Class findClass(final String name) {
      if (this.userClassPathFirst) {
         return (Class)this.findClassLocally(name).getOrElse(() -> this.parentLoader().loadClass(name));
      } else {
         Class var15;
         try {
            var15 = this.parentLoader().loadClass(name);
         } catch (ClassNotFoundException var14) {
            try {
               var10000 = this.findClassLocally(name);
            } catch (Throwable var13) {
               if (var13 instanceof RemoteClassLoaderError) {
                  RemoteClassLoaderError var8 = (RemoteClassLoaderError)var13;
                  throw var8;
               }

               if (var13 != null && .MODULE$.apply(var13)) {
                  throw new RemoteClassLoaderError(name, var13);
               }

               throw var13;
            }

            Option classOption = var10000;
            if (scala.None..MODULE$.equals(classOption)) {
               throw new ClassNotFoundException(name, var14);
            }

            if (!(classOption instanceof Some)) {
               throw new MatchError(classOption);
            }

            Some var11 = (Some)classOption;
            Class a = (Class)var11.value();
            var15 = a;
         }

         return var15;
      }
   }

   public Pattern org$apache$spark$executor$ExecutorClassLoader$$STREAM_NOT_FOUND_REGEX() {
      return this.org$apache$spark$executor$ExecutorClassLoader$$STREAM_NOT_FOUND_REGEX;
   }

   private InputStream getClassFileInputStreamFromSparkRPC(final String path) {
      RpcEnv var10000 = this.env.rpcEnv();
      String var10001 = this.classUri;
      ReadableByteChannel channel = var10000.openChannel(var10001 + "/" + this.urlEncode(path));
      return new FilterInputStream(channel, path) {
         // $FF: synthetic field
         private final ExecutorClassLoader $outer;
         private final String path$1;

         // $FF: synthetic method
         private int super$read() {
            return super.read();
         }

         // $FF: synthetic method
         private int super$read(final byte[] x$1, final int x$2, final int x$3) {
            return super.read(x$1, x$2, x$3);
         }

         public int read() {
            return this.toClassNotFound((JFunction0.mcI.sp)() -> this.super$read());
         }

         public int read(final byte[] b, final int offset, final int len) {
            return this.toClassNotFound((JFunction0.mcI.sp)() -> this.super$read(b, offset, len));
         }

         private int toClassNotFound(final Function0 fn) {
            try {
               return fn.apply$mcI$sp();
            } catch (Throwable var7) {
               if (var7 instanceof RuntimeException var5) {
                  if (var5.getMessage() != null && this.$outer.org$apache$spark$executor$ExecutorClassLoader$$STREAM_NOT_FOUND_REGEX().matcher(var5.getMessage()).matches()) {
                     throw new ClassNotFoundException(this.path$1, var5);
                  }
               }

               if (var7 != null && .MODULE$.apply(var7)) {
                  throw new RemoteClassLoaderError(this.path$1, var7);
               } else {
                  throw var7;
               }
            }
         }

         public {
            if (ExecutorClassLoader.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorClassLoader.this;
               this.path$1 = path$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   private InputStream getClassFileInputStreamFromFileSystem(final FileSystem fileSystem, final String pathInDirectory) {
      Path path = new Path(this.directory(), pathInDirectory);

      try {
         return fileSystem.open(path);
      } catch (FileNotFoundException var4) {
         throw new ClassNotFoundException("Class file not found at path " + path);
      }
   }

   public Option findClassLocally(final String name) {
      String pathInDirectory = name.replace('.', '/') + ".class";
      InputStream inputStream = null;
      boolean var15 = false;

      Object var10000;
      try {
         var15 = true;
         inputStream = (InputStream)this.fetchFn().apply(pathInDirectory);
         byte[] bytes = this.readAndTransformClass(name, inputStream);
         var10000 = new Some(this.defineClass(name, bytes, 0, bytes.length));
         var15 = false;
      } catch (ClassNotFoundException var18) {
         this.logDebug((Function0)(() -> "Did not load class " + name + " from REPL class server at " + this.uri()), var18);
         var10000 = scala.None..MODULE$;
         var15 = false;
      } catch (Exception var19) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to check existence of class ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, name)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on REPL class server at ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URI..MODULE$, this.uri())}))))), var19);
         if (!this.userClassPathFirst) {
            throw var19;
         }

         var10000 = scala.None..MODULE$;
         var15 = false;
      } finally {
         if (var15) {
            if (inputStream != null) {
               try {
                  inputStream.close();
               } catch (Exception var16) {
                  this.logError((Function0)(() -> "Exception while closing inputStream"), var16);
               }
            }

         }
      }

      Object var4 = var10000;
      if (inputStream != null) {
         try {
            inputStream.close();
         } catch (Exception var17) {
            this.logError((Function0)(() -> "Exception while closing inputStream"), var17);
         }
      }

      return (Option)var4;
   }

   public byte[] readAndTransformClass(final String name, final InputStream in) {
      if (name.startsWith("line") && name.endsWith("$iw$")) {
         ClassReader cr = new ClassReader(in);
         ClassWriter cw = new ClassWriter(3);
         ConstructorCleaner cleaner = new ConstructorCleaner(name, cw);
         cr.accept(cleaner, 0);
         return cw.toByteArray();
      } else {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         byte[] bytes = new byte[4096];
         boolean done = false;

         while(!done) {
            int num = in.read(bytes);
            if (num >= 0) {
               bos.write(bytes, 0, num);
            } else {
               done = true;
            }
         }

         return bos.toByteArray();
      }
   }

   public String urlEncode(final String str) {
      return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.StringOps..MODULE$.split$extension(scala.Predef..MODULE$.augmentString(str), '/')), (part) -> URLEncoder.encode(part, StandardCharsets.UTF_8.name()), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("/");
   }

   public ExecutorClassLoader(final SparkConf conf, final SparkEnv env, final String classUri, final ClassLoader parent, final boolean userClassPathFirst) {
      super((ClassLoader)null);
      this.env = env;
      this.classUri = classUri;
      this.userClassPathFirst = userClassPathFirst;
      Logging.$init$(this);
      this.uri = new URI(classUri);
      this.directory = this.uri().getPath();
      this.parentLoader = new ParentClassLoader(parent);
      this.httpUrlConnectionTimeoutMillis = -1;
      String var7 = this.uri().getScheme();
      Function1 var10001;
      switch (var7 == null ? 0 : var7.hashCode()) {
         case 109638365:
            if ("spark".equals(var7)) {
               var10001 = (path) -> this.getClassFileInputStreamFromSparkRPC(path);
               break;
            }
         default:
            FileSystem fileSystem = FileSystem.get(this.uri(), SparkHadoopUtil$.MODULE$.get().newConfiguration(conf));
            var10001 = (pathInDirectory) -> this.getClassFileInputStreamFromFileSystem(fileSystem, pathInDirectory);
      }

      this.fetchFn = var10001;
      this.org$apache$spark$executor$ExecutorClassLoader$$STREAM_NOT_FOUND_REGEX = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("Stream '.*' was not found.")).pattern();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
