package org.apache.spark.deploy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.spark.api.r.RUtils$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogKeys.CONFIG.;
import org.apache.spark.util.RedirectThread;
import org.apache.spark.util.RedirectThread$;
import org.slf4j.Logger;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Files;
import scala.Function0;
import scala.Some;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class RPackageUtils$ implements Logging {
   public static final RPackageUtils$ MODULE$ = new RPackageUtils$();
   private static final MDC hasRPackageMDC;
   private static final Seq baseInstallCmd;
   private static final MessageWithContext RJarDoc;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      hasRPackageMDC = new MDC(.MODULE$, "Spark-HasRPackage");
      baseInstallCmd = new scala.collection.immutable..colon.colon("R", new scala.collection.immutable..colon.colon("CMD", new scala.collection.immutable..colon.colon("INSTALL", new scala.collection.immutable..colon.colon("-l", scala.collection.immutable.Nil..MODULE$))));
      RJarDoc = MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"In order for Spark to build R packages that are parts of Spark Packages, there are a few\n      |requirements. The R source code must be shipped in a jar, with additional Java/Scala\n      |classes. The jar must be in the following format:\n      |  1- The Manifest (META-INF/MANIFEST.mf) must contain the key-value: ", ": true\n      |  2- The standard R package layout must be preserved under R/pkg/ inside the jar. More\n      |  information on the standard R package layout can be found in:\n      |  http://cran.r-project.org/doc/contrib/Leisch-CreatingPackages.pdf\n      |  An example layout is given below. After running `jar tf $JAR_FILE | sort`:\n      |\n      |META-INF/MANIFEST.MF\n      |R/\n      |R/pkg/\n      |R/pkg/DESCRIPTION\n      |R/pkg/NAMESPACE\n      |R/pkg/R/\n      |R/pkg/R/myRcode.R\n      |org/\n      |org/apache/\n      |..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{MODULE$.hasRPackageMDC()}))).stripMargin();
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

   private final String hasRPackage() {
      return "Spark-HasRPackage";
   }

   private final MDC hasRPackageMDC() {
      return hasRPackageMDC;
   }

   private final Seq baseInstallCmd() {
      return baseInstallCmd;
   }

   private final String RJarEntries() {
      return "R/pkg";
   }

   public final MessageWithContext RJarDoc() {
      return RJarDoc;
   }

   private void print(final LogEntry msg, final PrintStream printStream, final Level level, final Throwable e) {
      if (printStream != null) {
         printStream.println(msg.message());
         if (e != null) {
            e.printStackTrace(printStream);
         }
      } else {
         label53: {
            Level var10000 = Level.INFO;
            if (var10000 == null) {
               if (level == null) {
                  break label53;
               }
            } else if (var10000.equals(level)) {
               break label53;
            }

            label54: {
               var10000 = Level.WARNING;
               if (var10000 == null) {
                  if (level == null) {
                     break label54;
                  }
               } else if (var10000.equals(level)) {
                  break label54;
               }

               label35: {
                  var10000 = Level.SEVERE;
                  if (var10000 == null) {
                     if (level == null) {
                        break label35;
                     }
                  } else if (var10000.equals(level)) {
                     break label35;
                  }

                  this.logDebug(msg);
                  BoxedUnit var12 = BoxedUnit.UNIT;
                  return;
               }

               this.logError(msg, e);
               BoxedUnit var13 = BoxedUnit.UNIT;
               return;
            }

            this.logWarning(msg);
            BoxedUnit var14 = BoxedUnit.UNIT;
            return;
         }

         this.logInfo(msg);
         BoxedUnit var15 = BoxedUnit.UNIT;
      }
   }

   private Level print$default$3() {
      return Level.FINE;
   }

   private Throwable print$default$4() {
      return null;
   }

   public boolean checkManifestForR(final JarFile jar) {
      if (jar.getManifest() == null) {
         return false;
      } else {
         boolean var4;
         label31: {
            Attributes manifest = jar.getManifest().getMainAttributes();
            if (manifest.getValue("Spark-HasRPackage") != null) {
               String var10000 = manifest.getValue("Spark-HasRPackage").trim();
               String var3 = "true";
               if (var10000 == null) {
                  if (var3 == null) {
                     break label31;
                  }
               } else if (var10000.equals(var3)) {
                  break label31;
               }
            }

            var4 = false;
            return var4;
         }

         var4 = true;
         return var4;
      }
   }

   private boolean rPackageBuilder(final File dir, final PrintStream printStream, final boolean verbose, final String libDir) {
      String pathToPkg = (new scala.collection.immutable..colon.colon(dir, new scala.collection.immutable..colon.colon("R", new scala.collection.immutable..colon.colon("pkg", scala.collection.immutable.Nil..MODULE$)))).mkString(File.separator);
      Seq installCmd = (Seq)this.baseInstallCmd().$plus$plus(new scala.collection.immutable..colon.colon(libDir, new scala.collection.immutable..colon.colon(pathToPkg, scala.collection.immutable.Nil..MODULE$)));
      if (verbose) {
         this.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Building R package with the command: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COMMAND..MODULE$, installCmd)})))), printStream, this.print$default$3(), this.print$default$4());
      }

      boolean var10000;
      try {
         ProcessBuilder builder = new ProcessBuilder(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(installCmd).asJava());
         builder.redirectErrorStream(true);
         Map env = builder.environment();
         Seq rPackageDir = RUtils$.MODULE$.sparkRPackagePath(true);
         env.put("SPARKR_PACKAGE_DIR", rPackageDir.mkString(","));
         env.put("R_PROFILE_USER", (new scala.collection.immutable..colon.colon((String)rPackageDir.apply(0), new scala.collection.immutable..colon.colon("SparkR", new scala.collection.immutable..colon.colon("profile", new scala.collection.immutable..colon.colon("general.R", scala.collection.immutable.Nil..MODULE$))))).mkString(File.separator));
         Process process = builder.start();
         (new RedirectThread(process.getInputStream(), printStream, "redirect R packaging", RedirectThread$.MODULE$.$lessinit$greater$default$4())).start();
         var10000 = process.waitFor() == 0;
      } catch (Throwable var12) {
         this.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to build R package."})))).log(scala.collection.immutable.Nil..MODULE$)), printStream, Level.SEVERE, var12);
         var10000 = false;
      }

      return var10000;
   }

   private File extractRFolder(final JarFile jar, final PrintStream printStream, final boolean verbose) {
      File tempDir = org.apache.spark.util.Utils$.MODULE$.createTempDir((String)null, org.apache.spark.util.Utils$.MODULE$.createTempDir$default$2());
      Enumeration jarEntries = jar.entries();

      while(jarEntries.hasMoreElements()) {
         JarEntry entry = (JarEntry)jarEntries.nextElement();
         int entryRIndex = entry.getName().indexOf("R/pkg");
         if (entryRIndex > -1) {
            String entryPath = entry.getName().substring(entryRIndex);
            if (entry.isDirectory()) {
               File dir = new File(tempDir, entryPath);
               if (verbose) {
                  this.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Creating directory: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, dir)})))), printStream, this.print$default$3(), this.print$default$4());
               }

               BoxesRunTime.boxToBoolean(dir.mkdirs());
            } else {
               InputStream inStream = jar.getInputStream(entry);
               File outPath = new File(tempDir, entryPath);
               Files.createParentDirs(outPath);
               FileOutputStream outStream = new FileOutputStream(outPath);
               if (verbose) {
                  this.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Extracting ", " to ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.JAR_ENTRY..MODULE$, entry), new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, outPath)})))), printStream, this.print$default$3(), this.print$default$4());
               }

               BoxesRunTime.boxToLong(org.apache.spark.util.Utils$.MODULE$.copyStream(inStream, outStream, true, org.apache.spark.util.Utils$.MODULE$.copyStream$default$4()));
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

      return tempDir;
   }

   public void checkAndBuildRPackage(final String jars, final PrintStream printStream, final boolean verbose) {
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])jars.split(",")), (jarPath) -> {
         $anonfun$checkAndBuildRPackage$1(printStream, verbose, jarPath);
         return BoxedUnit.UNIT;
      });
   }

   public PrintStream checkAndBuildRPackage$default$2() {
      return null;
   }

   public boolean checkAndBuildRPackage$default$3() {
      return false;
   }

   private Set listFilesRecursively(final File dir, final Seq excludePatterns) {
      if (!dir.exists()) {
         return scala.Predef..MODULE$.Set().empty();
      } else if (dir.isDirectory()) {
         File[] subDir = dir.listFiles(new FilenameFilter(excludePatterns) {
            private final Seq excludePatterns$1;

            public boolean accept(final File dir, final String name) {
               return !BoxesRunTime.unboxToBoolean(((IterableOnceOps)this.excludePatterns$1.map((x$1) -> BoxesRunTime.boxToBoolean($anonfun$accept$1(name, x$1)))).reduce((x$1, x$2) -> BoxesRunTime.boxToBoolean($anonfun$accept$2(BoxesRunTime.unboxToBoolean(x$1), BoxesRunTime.unboxToBoolean(x$2)))));
            }

            // $FF: synthetic method
            public static final boolean $anonfun$accept$1(final String name$1, final CharSequence x$1) {
               return name$1.contains(x$1);
            }

            // $FF: synthetic method
            public static final boolean $anonfun$accept$2(final boolean x$1, final boolean x$2) {
               return x$1 || x$2;
            }

            public {
               this.excludePatterns$1 = excludePatterns$1;
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         });
         return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])subDir), (x$3) -> MODULE$.listFilesRecursively(x$3, excludePatterns), scala.reflect.ClassTag..MODULE$.apply(File.class))).toSet();
      } else {
         return (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new File[]{dir})));
      }
   }

   public File zipRLibraries(final File dir, final String name) {
      Set filesToBundle = this.listFilesRecursively(dir, new scala.collection.immutable..colon.colon(".zip", scala.collection.immutable.Nil..MODULE$));
      File zipFile = new File(dir, name);
      if (!zipFile.delete()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, zipFile.getPath())})))));
      }

      ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile, false));

      try {
         filesToBundle.foreach((file) -> {
            $anonfun$zipRLibraries$2(dir, zipOutputStream, file);
            return BoxedUnit.UNIT;
         });
      } finally {
         zipOutputStream.close();
      }

      return zipFile;
   }

   // $FF: synthetic method
   public static final void $anonfun$checkAndBuildRPackage$1(final PrintStream printStream$1, final boolean verbose$1, final String jarPath) {
      File file = new File(org.apache.spark.util.Utils$.MODULE$.resolveURI(jarPath));
      if (file.exists()) {
         JarFile jar = new JarFile(file);
         org.apache.spark.util.Utils$.MODULE$.tryWithSafeFinally((JFunction0.mcV.sp)() -> {
            if (MODULE$.checkManifestForR(jar)) {
               MODULE$.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " contains R source code. Now installing package."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), printStream$1, Level.INFO, MODULE$.print$default$4());
               File rSource = MODULE$.extractRFolder(jar, printStream$1, verbose$1);
               if (RUtils$.MODULE$.rPackages().isEmpty()) {
                  RUtils$.MODULE$.rPackages_$eq(new Some(org.apache.spark.util.Utils$.MODULE$.createTempDir().getAbsolutePath()));
               }

               try {
                  if (!MODULE$.rPackageBuilder(rSource, printStream$1, verbose$1, (String)RUtils$.MODULE$.rPackages().get())) {
                     MODULE$.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ERROR: Failed to build R package in ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), printStream$1, MODULE$.print$default$3(), MODULE$.print$default$4());
                     MODULE$.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.RJarDoc()), printStream$1, MODULE$.print$default$3(), MODULE$.print$default$4());
                  }
               } finally {
                  if (!rSource.delete()) {
                     MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, rSource.getPath())})))));
                  }

               }

            } else if (verbose$1) {
               MODULE$.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " doesn't contain R source code, skipping..."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), printStream$1, MODULE$.print$default$3(), MODULE$.print$default$4());
            }
         }, (JFunction0.mcV.sp)() -> jar.close());
      } else {
         MODULE$.print(org.apache.spark.internal.LogEntry..MODULE$.from(() -> MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"WARN: ", " resolved as dependency, but not found."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, file)})))), printStream$1, Level.WARNING, MODULE$.print$default$4());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$zipRLibraries$2(final File dir$2, final ZipOutputStream zipOutputStream$1, final File file) {
      String relPath = file.toURI().toString().replaceFirst(scala.collection.StringOps..MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(dir$2.toURI().toString()), "/"), "");
      FileInputStream fis = new FileInputStream(file);
      ZipEntry zipEntry = new ZipEntry(relPath);
      zipOutputStream$1.putNextEntry(zipEntry);
      ByteStreams.copy(fis, zipOutputStream$1);
      zipOutputStream$1.closeEntry();
      fis.close();
   }

   private RPackageUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
