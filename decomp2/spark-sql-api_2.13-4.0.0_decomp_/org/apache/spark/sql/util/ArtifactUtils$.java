package org.apache.spark.sql.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import scala.Predef;
import scala.Predef.;

public final class ArtifactUtils$ {
   public static final ArtifactUtils$ MODULE$ = new ArtifactUtils$();

   public Path concatenatePaths(final Path basePath, final Path otherPath) {
      Path normalizedPath;
      Predef var10000;
      boolean var10001;
      label23: {
         label22: {
            .MODULE$.require(!otherPath.isAbsolute());
            Path concatenatedPath = Paths.get(basePath.toString() + "/" + otherPath.toString());
            normalizedPath = concatenatedPath.normalize();
            var10000 = .MODULE$;
            if (normalizedPath == null) {
               if (basePath == null) {
                  break label22;
               }
            } else if (normalizedPath.equals(basePath)) {
               break label22;
            }

            if (normalizedPath.startsWith(basePath + "/")) {
               var10001 = true;
               break label23;
            }
         }

         var10001 = false;
      }

      var10000.require(var10001);
      return normalizedPath;
   }

   public Path concatenatePaths(final Path basePath, final String otherPath) {
      return this.concatenatePaths(basePath, Paths.get(otherPath));
   }

   private ArtifactUtils$() {
   }
}
