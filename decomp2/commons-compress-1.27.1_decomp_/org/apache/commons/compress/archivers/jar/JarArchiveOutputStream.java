package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.zip.JarMarker;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class JarArchiveOutputStream extends ZipArchiveOutputStream {
   private boolean jarMarkerAdded;

   public JarArchiveOutputStream(OutputStream out) {
      super(out);
   }

   public JarArchiveOutputStream(OutputStream out, String encoding) {
      super(out);
      this.setEncoding(encoding);
   }

   public void putArchiveEntry(ZipArchiveEntry entry) throws IOException {
      if (!this.jarMarkerAdded) {
         entry.addAsFirstExtraField(JarMarker.getInstance());
         this.jarMarkerAdded = true;
      }

      super.putArchiveEntry(entry);
   }
}
