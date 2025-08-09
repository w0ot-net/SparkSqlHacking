package scala.reflect.io;

import java.io.IOException;
import java.net.URL;
import scala.Option;
import scala.Some;
import scala.math.package;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt;
import scala.runtime.RichInt.;
import scala.sys.SystemProperties;

public final class ZipArchive$ {
   public static final ZipArchive$ MODULE$ = new ZipArchive$();
   private static final boolean closeZipFile = Boolean.getBoolean("scala.classpath.closeZip");
   private static final int zipFilePoolCapacity;

   static {
      RichInt var10000 = .MODULE$;
      int var1 = Runtime.getRuntime().availableProcessors();
      int max$extension_that = 4;
      package var7 = scala.math.package..MODULE$;
      int var0 = Math.max(var1, max$extension_that);
      scala.sys.package var8 = scala.sys.package..MODULE$;
      Option var9 = (new SystemProperties()).get("scala.classpath.zipFilePool.capacity");
      if (var9 == null) {
         throw null;
      } else {
         Option map_this = var9;
         Object var10 = map_this.isEmpty() ? scala.None..MODULE$ : new Some($anonfun$zipFilePoolCapacity$1((String)map_this.get()));
         Object var5 = null;
         Option getOrElse_this = (Option)var10;
         var10 = getOrElse_this.isEmpty() ? var0 : getOrElse_this.get();
         getOrElse_this = null;
         zipFilePoolCapacity = BoxesRunTime.unboxToInt(var10);
      }
   }

   public boolean closeZipFile() {
      return closeZipFile;
   }

   public int zipFilePoolCapacity() {
      return zipFilePoolCapacity;
   }

   public final String RootEntry() {
      return "/";
   }

   public FileZipArchive fromFile(final File file) {
      return this.fromFile(file.jfile());
   }

   public FileZipArchive fromFile(final java.io.File file) {
      try {
         return new FileZipArchive(file);
      } catch (IOException var2) {
         return null;
      }
   }

   public URLZipArchive fromURL(final URL url) {
      return new URLZipArchive(url);
   }

   public AbstractFile fromManifestURL(final URL url) {
      return new ManifestResources(url);
   }

   public String scala$reflect$io$ZipArchive$$dirName(final String path) {
      return this.splitPath(path, true);
   }

   public String scala$reflect$io$ZipArchive$$baseName(final String path) {
      return this.splitPath(path, false);
   }

   private String splitPath(final String path0, final boolean front) {
      String path = path0.charAt(path0.length() - 1) == '/' ? path0.substring(0, path0.length() - 1) : path0;
      int idx = path.lastIndexOf(47);
      if (idx < 0) {
         return front ? "/" : path;
      } else {
         return front ? path.substring(0, idx + 1) : path.substring(idx + 1);
      }
   }

   /** @deprecated */
   public String pathToDotted(final String path) {
      if ("/".equals(path)) {
         return "";
      } else {
         boolean slashEnd = path.endsWith("/");
         int len = path.length() - (slashEnd ? 1 : 0);
         char[] result = new char[len];

         for(int i = 0; i < len; ++i) {
            char var6 = path.charAt(i);
            result[i] = var6 == '/' ? 46 : var6;
         }

         return new String(result);
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$zipFilePoolCapacity$1(final String x$1) {
      return Integer.parseInt(x$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$zipFilePoolCapacity$2(final int default$1) {
      return default$1;
   }

   private ZipArchive$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$zipFilePoolCapacity$1$adapted(final String x$1) {
      return BoxesRunTime.boxToInteger($anonfun$zipFilePoolCapacity$1(x$1));
   }
}
