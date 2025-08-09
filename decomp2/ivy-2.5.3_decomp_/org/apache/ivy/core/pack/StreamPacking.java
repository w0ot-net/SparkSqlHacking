package org.apache.ivy.core.pack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;

public abstract class StreamPacking extends ArchivePacking {
   public abstract InputStream unpack(InputStream var1) throws IOException;

   public void unpack(InputStream packed, File dest) throws IOException {
      FileUtil.copy((InputStream)this.unpack(packed), (File)dest, (CopyProgressListener)null);
   }
}
