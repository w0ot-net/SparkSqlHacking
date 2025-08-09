package org.codehaus.commons.compiler.util.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.codehaus.commons.nullanalysis.Nullable;

public class ZipFileResourceFinder extends ResourceFinder {
   private final ZipFile zipFile;

   public ZipFileResourceFinder(ZipFile zipFile) {
      this.zipFile = zipFile;
   }

   public final String toString() {
      return "zip:" + this.zipFile.getName();
   }

   @Nullable
   public final Resource findResource(final String resourceName) {
      final ZipEntry ze = this.zipFile.getEntry(resourceName);
      return ze == null ? null : new LocatableResource() {
         public URL getLocation() throws IOException {
            return new URL("jar", (String)null, "file:" + ZipFileResourceFinder.this.zipFile.getName() + "!" + resourceName);
         }

         public InputStream open() throws IOException {
            return ZipFileResourceFinder.this.zipFile.getInputStream(ze);
         }

         public String getFileName() {
            return ZipFileResourceFinder.this.zipFile.getName() + ':' + resourceName;
         }

         public long lastModified() {
            long l = ze.getTime();
            return l == -1L ? 0L : l;
         }

         public String toString() {
            return this.getFileName();
         }
      };
   }
}
