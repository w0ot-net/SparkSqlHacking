package scala.reflect.io;

import java.lang.invoke.SerializedLambda;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import scala.Option;
import scala.collection.convert.AsScalaExtensions;
import scala.jdk.CollectionConverters.;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;

public final class RootPath$ {
   public static final RootPath$ MODULE$ = new RootPath$();
   private static FileSystemProvider jarFsProvider;
   private static volatile boolean bitmap$0;

   private FileSystemProvider jarFsProvider$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            Option var10000 = AsScalaExtensions.ListHasAsScala$(.MODULE$, FileSystemProvider.installedProviders()).asScala().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$jarFsProvider$1(x$1)));
            if (var10000 == null) {
               throw null;
            }

            Option getOrElse_this = var10000;
            if (getOrElse_this.isEmpty()) {
               throw new RuntimeException("No jar filesystem provider");
            }

            Object var5 = getOrElse_this.get();
            Object var4 = null;
            jarFsProvider = (FileSystemProvider)var5;
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return jarFsProvider;
   }

   private FileSystemProvider jarFsProvider() {
      return !bitmap$0 ? this.jarFsProvider$lzycompute() : jarFsProvider;
   }

   public RootPath apply(final java.nio.file.Path path, final boolean writable) {
      if (path.getFileName().toString().endsWith(".jar")) {
         HashMap env = new HashMap();
         if (!Files.exists(path.getParent(), new LinkOption[0])) {
            Files.createDirectories(path.getParent());
         }

         if (writable) {
            env.put("create", "true");
            if (Files.exists(path, new LinkOption[0])) {
               Files.delete(path);
            }
         }

         FileSystem zipfs = this.jarFsProvider().newFileSystem(path, env);
         return new RootPath(zipfs, path) {
            private final FileSystem zipfs$1;
            private final java.nio.file.Path path$1;

            public java.nio.file.Path root() {
               return (java.nio.file.Path)this.zipfs$1.getRootDirectories().iterator().next();
            }

            public void close() {
               this.zipfs$1.close();
            }

            public String toString() {
               return this.path$1.toString();
            }

            public {
               this.zipfs$1 = zipfs$1;
               this.path$1 = path$1;
            }
         };
      } else {
         return new RootPath(path) {
            private final java.nio.file.Path path$1;

            public java.nio.file.Path root() {
               return this.path$1;
            }

            public void close() {
            }

            public String toString() {
               return this.path$1.toString();
            }

            public {
               this.path$1 = path$1;
            }
         };
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$jarFsProvider$1(final FileSystemProvider x$1) {
      String var10000 = x$1.getScheme();
      String var1 = "jar";
      if (var10000 != null) {
         if (var10000.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$jarFsProvider$2() {
      throw new RuntimeException("No jar filesystem provider");
   }

   private RootPath$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
