package org.apache.spark.util;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import org.apache.spark.internal.LogEntry$;
import org.apache.spark.internal.LogKeys;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.network.util.JavaUtils;
import scala.StringContext;
import scala.Predef.;
import scala.collection.mutable.Buffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005e4\u0001b\u0004\t\u0011\u0002\u0007\u0005!\u0003\u0007\u0005\u0006K\u0001!\ta\n\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006\u0005\u0002!\ta\u0011\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006\u001f\u0002!\tA\u0016\u0005\b7\u0002\t\n\u0011\"\u0001]\u0011\u00159\u0007\u0001\"\u0001i\u0011\u00159\u0007\u0001\"\u0001j\u0011\u001da\u0007!%A\u0005\u0002qCq!\u001c\u0001\u0012\u0002\u0013\u0005A\fC\u0003o\u0001\u0011\u0005qn\u0002\u0004s!!\u0005!c\u001d\u0004\u0007\u001fAA\tAE;\t\u000b]lA\u0011\u0001=\u0003\u001dM\u0003\u0018M]6GS2,W\u000b^5mg*\u0011\u0011CE\u0001\u0005kRLGN\u0003\u0002\u0014)\u0005)1\u000f]1sW*\u0011QCF\u0001\u0007CB\f7\r[3\u000b\u0003]\t1a\u001c:h'\r\u0001\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0005\t\u0012\u0012\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0011\n#a\u0002'pO\u001eLgnZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t\u0001\u0006\u0005\u0002\u001bS%\u0011!f\u0007\u0002\u0005+:LG/\u0001\u0006sKN|GN^3V%&#\"!L\u001b\u0011\u00059\u001aT\"A\u0018\u000b\u0005A\n\u0014a\u00018fi*\t!'\u0001\u0003kCZ\f\u0017B\u0001\u001b0\u0005\r)&+\u0013\u0005\u0006m\t\u0001\raN\u0001\u0005a\u0006$\b\u000e\u0005\u00029\u007f9\u0011\u0011(\u0010\t\u0003umi\u0011a\u000f\u0006\u0003y\u0019\na\u0001\u0010:p_Rt\u0014B\u0001 \u001c\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005yZ\u0012!\u0004:fGV\u00148/\u001b<f\u0019&\u001cH\u000f\u0006\u0002E\u001bB\u0019!$R$\n\u0005\u0019[\"!B!se\u0006L\bC\u0001%L\u001b\u0005I%B\u0001&2\u0003\tIw.\u0003\u0002M\u0013\n!a)\u001b7f\u0011\u0015q5\u00011\u0001H\u0003\u00051\u0017aD2sK\u0006$X\rR5sK\u000e$xN]=\u0015\u0005E#\u0006C\u0001\u000eS\u0013\t\u00196DA\u0004C_>dW-\u00198\t\u000bU#\u0001\u0019A$\u0002\u0007\u0011L'\u000fF\u0002H/fCQ\u0001W\u0003A\u0002]\nAA]8pi\"9!,\u0002I\u0001\u0002\u00049\u0014A\u00038b[\u0016\u0004&/\u001a4jq\u0006I2M]3bi\u0016$\u0015N]3di>\u0014\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005i&FA\u001c_W\u0005y\u0006C\u00011f\u001b\u0005\t'B\u00012d\u0003%)hn\u00195fG.,GM\u0003\u0002e7\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\u0019\f'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i1M]3bi\u0016$V-\u001c9ESJ$\u0012a\u0012\u000b\u0004\u000f*\\\u0007b\u0002-\t!\u0003\u0005\ra\u000e\u0005\b5\"\u0001\n\u00111\u00018\u0003]\u0019'/Z1uKR+W\u000e\u001d#je\u0012\"WMZ1vYR$\u0013'A\fde\u0016\fG/\u001a+f[B$\u0015N\u001d\u0013eK\u001a\fW\u000f\u001c;%e\u0005\tB-\u001a7fi\u0016\u0014VmY;sg&4X\r\\=\u0015\u0005!\u0002\b\"B9\f\u0001\u00049\u0015\u0001\u00024jY\u0016\fab\u00159be.4\u0015\u000e\\3Vi&d7\u000f\u0005\u0002u\u001b5\t\u0001cE\u0002\u000e3Y\u0004\"\u0001\u001e\u0001\u0002\rqJg.\u001b;?)\u0005\u0019\b"
)
public interface SparkFileUtils extends Logging {
   // $FF: synthetic method
   static URI resolveURI$(final SparkFileUtils $this, final String path) {
      return $this.resolveURI(path);
   }

   default URI resolveURI(final String path) {
      try {
         URI uri = new URI(path);
         if (uri.getScheme() != null) {
            return uri;
         }

         if (uri.getFragment() != null) {
            URI absoluteURI = (new File(uri.getPath())).getAbsoluteFile().toURI();
            return new URI(absoluteURI.getScheme(), absoluteURI.getHost(), absoluteURI.getPath(), uri.getFragment());
         }
      } catch (URISyntaxException var5) {
      }

      return (new File(path)).getCanonicalFile().toURI();
   }

   // $FF: synthetic method
   static File[] recursiveList$(final SparkFileUtils $this, final File f) {
      return $this.recursiveList(f);
   }

   default File[] recursiveList(final File f) {
      .MODULE$.require(f.isDirectory());
      Buffer result = .MODULE$.wrapRefArray((Object[])f.listFiles()).toBuffer();
      Buffer dirList = (Buffer)result.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$recursiveList$1(x$1)));

      while(dirList.nonEmpty()) {
         File curDir = (File)dirList.remove(0);
         File[] files = curDir.listFiles();
         result.$plus$plus$eq(.MODULE$.wrapRefArray((Object[])files));
         dirList.$plus$plus$eq(.MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.filter$extension(.MODULE$.refArrayOps((Object[])files), (x$2) -> BoxesRunTime.boxToBoolean($anonfun$recursiveList$2(x$2)))));
      }

      return (File[])result.toArray(scala.reflect.ClassTag..MODULE$.apply(File.class));
   }

   // $FF: synthetic method
   static boolean createDirectory$(final SparkFileUtils $this, final File dir) {
      return $this.createDirectory(dir);
   }

   default boolean createDirectory(final File dir) {
      boolean var10000;
      try {
         Files.createDirectories(dir.toPath());
         if (!dir.exists() || !dir.isDirectory()) {
            this.logError(LogEntry$.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.PATH$.MODULE$, dir)}))));
         }

         var10000 = dir.isDirectory();
      } catch (Exception var3) {
         this.logError(LogEntry$.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to create directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new MDC[]{new MDC(LogKeys.PATH$.MODULE$, dir)}))), var3);
         var10000 = false;
      }

      return var10000;
   }

   // $FF: synthetic method
   static File createDirectory$(final SparkFileUtils $this, final String root, final String namePrefix) {
      return $this.createDirectory(root, namePrefix);
   }

   default File createDirectory(final String root, final String namePrefix) {
      return JavaUtils.createDirectory(root, namePrefix);
   }

   // $FF: synthetic method
   static String createDirectory$default$2$(final SparkFileUtils $this) {
      return $this.createDirectory$default$2();
   }

   default String createDirectory$default$2() {
      return "spark";
   }

   // $FF: synthetic method
   static File createTempDir$(final SparkFileUtils $this) {
      return $this.createTempDir();
   }

   default File createTempDir() {
      return this.createTempDir(System.getProperty("java.io.tmpdir"), "spark");
   }

   // $FF: synthetic method
   static File createTempDir$(final SparkFileUtils $this, final String root, final String namePrefix) {
      return $this.createTempDir(root, namePrefix);
   }

   default File createTempDir(final String root, final String namePrefix) {
      return this.createDirectory(root, namePrefix);
   }

   // $FF: synthetic method
   static String createTempDir$default$1$(final SparkFileUtils $this) {
      return $this.createTempDir$default$1();
   }

   default String createTempDir$default$1() {
      return System.getProperty("java.io.tmpdir");
   }

   // $FF: synthetic method
   static String createTempDir$default$2$(final SparkFileUtils $this) {
      return $this.createTempDir$default$2();
   }

   default String createTempDir$default$2() {
      return "spark";
   }

   // $FF: synthetic method
   static void deleteRecursively$(final SparkFileUtils $this, final File file) {
      $this.deleteRecursively(file);
   }

   default void deleteRecursively(final File file) {
      JavaUtils.deleteRecursively(file);
   }

   // $FF: synthetic method
   static boolean $anonfun$recursiveList$1(final File x$1) {
      return x$1.isDirectory();
   }

   // $FF: synthetic method
   static boolean $anonfun$recursiveList$2(final File x$2) {
      return x$2.isDirectory();
   }

   static void $init$(final SparkFileUtils $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
