package org.apache.spark.sql.hive.orc;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.orc.OrcFile;
import org.apache.hadoop.hive.ql.io.orc.Reader;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.sql.errors.QueryExecutionErrors.;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;

public final class OrcFileOperator$ implements Logging {
   public static final OrcFileOperator$ MODULE$ = new OrcFileOperator$();
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
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

   public Option getFileReader(final String basePath, final Option config, final boolean ignoreCorruptFiles) {
      Configuration conf = (Configuration)config.getOrElse(() -> new Configuration());
      Path hdfsPath = new Path(basePath);
      FileSystem fs = hdfsPath.getFileSystem(conf);
      return this.listOrcFiles(basePath, conf).iterator().map((path) -> {
         Object var10000;
         try {
            var10000 = new Some(OrcFile.createReader(fs, path));
         } catch (IOException var5) {
            if (!ignoreCorruptFiles) {
               throw .MODULE$.cannotReadFooterForFileError(path, var5);
            }

            MODULE$.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped the footer in the corrupted file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)})))), var5);
            var10000 = scala.None..MODULE$;
         }

         Option reader = (Option)var10000;
         return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(path), reader);
      }).collectFirst(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            if (x1 != null) {
               Path path = (Path)x1._1();
               Option var6 = (Option)x1._2();
               if (var6 instanceof Some) {
                  Some var7 = (Some)var6;
                  Reader reader = (Reader)var7.value();
                  if (OrcFileOperator$.MODULE$.org$apache$spark$sql$hive$orc$OrcFileOperator$$isWithNonEmptySchema$1(path, reader)) {
                     return reader;
                  }
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            if (x1 != null) {
               Path path = (Path)x1._1();
               Option var5 = (Option)x1._2();
               if (var5 instanceof Some) {
                  Some var6 = (Some)var5;
                  Reader reader = (Reader)var6.value();
                  if (OrcFileOperator$.MODULE$.org$apache$spark$sql$hive$orc$OrcFileOperator$$isWithNonEmptySchema$1(path, reader)) {
                     return true;
                  }
               }
            }

            return false;
         }
      });
   }

   public Option getFileReader$default$2() {
      return scala.None..MODULE$;
   }

   public boolean getFileReader$default$3() {
      return false;
   }

   public Option readSchema(final Seq paths, final Option conf, final boolean ignoreCorruptFiles) {
      return paths.iterator().map((x$1) -> MODULE$.getFileReader(x$1, conf, ignoreCorruptFiles)).collectFirst(new Serializable(paths) {
         private static final long serialVersionUID = 0L;
         private final Seq paths$1;

         public final Object applyOrElse(final Option x1, final Function1 default) {
            if (x1 instanceof Some var5) {
               Reader reader = (Reader)var5.value();
               StructObjectInspector readerInspector = (StructObjectInspector)reader.getObjectInspector();
               String schema = readerInspector.getTypeName();
               OrcFileOperator$.MODULE$.logDebug((Function0)(() -> "Reading schema from file " + this.paths$1 + ", got Hive schema string: " + schema));
               return (StructType)org.apache.spark.sql.catalyst.parser.CatalystSqlParser..MODULE$.parseDataType(schema);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Option x1) {
            return x1 instanceof Some;
         }

         public {
            this.paths$1 = paths$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public Seq readOrcSchemasInParallel(final Seq partFiles, final Configuration conf, final boolean ignoreCorruptFiles) {
      return (Seq)org.apache.spark.util.ThreadUtils..MODULE$.parmap(partFiles, "readingOrcSchemas", 8, (currentFile) -> {
         String file = currentFile.getPath().toString();
         return MODULE$.getFileReader(file, new Some(conf), ignoreCorruptFiles).map((reader) -> {
            StructObjectInspector readerInspector = (StructObjectInspector)reader.getObjectInspector();
            String schema = readerInspector.getTypeName();
            MODULE$.logDebug((Function0)(() -> "Reading schema from file " + file + "., got Hive schema string: " + schema));
            return (StructType)org.apache.spark.sql.catalyst.parser.CatalystSqlParser..MODULE$.parseDataType(schema);
         });
      }).flatten(scala.Predef..MODULE$.$conforms());
   }

   public Option getObjectInspector(final String path, final Option conf) {
      return this.getFileReader(path, conf, this.getFileReader$default$3()).map((x$2) -> (StructObjectInspector)x$2.getObjectInspector());
   }

   public Seq listOrcFiles(final String pathStr, final Configuration conf) {
      Path origPath = new Path(pathStr);
      FileSystem fs = origPath.getFileSystem(conf);
      Seq paths = (Seq)((IterableOps)((IterableOps)((IterableOps)org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().listLeafStatuses(fs, origPath).filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$listOrcFiles$1(x$3)))).map((x$4) -> x$4.getPath())).filterNot((x$5) -> BoxesRunTime.boxToBoolean($anonfun$listOrcFiles$3(x$5)))).filterNot((x$6) -> BoxesRunTime.boxToBoolean($anonfun$listOrcFiles$4(x$6)));
      return paths;
   }

   public final boolean org$apache$spark$sql$hive$orc$OrcFileOperator$$isWithNonEmptySchema$1(final Path path, final Reader reader) {
      ObjectInspector var4 = reader.getObjectInspector();
      if (var4 instanceof StructObjectInspector var5) {
         if (var5.getAllStructFieldRefs().size() == 0) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ORC file ", " has empty schema, it probably contains no rows. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))).$plus(MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Trying to read another ORC file to figure out the schema."})))).log(scala.collection.immutable.Nil..MODULE$))));
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listOrcFiles$1(final FileStatus x$3) {
      return x$3.isDirectory();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listOrcFiles$3(final Path x$5) {
      return x$5.getName().startsWith("_");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$listOrcFiles$4(final Path x$6) {
      return x$6.getName().startsWith(".");
   }

   private OrcFileOperator$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
