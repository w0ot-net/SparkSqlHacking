package org.apache.hadoop.hive.shims;

import java.io.IOException;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.HarFileSystem;
import org.apache.hadoop.fs.Path;

public class HiveHarFileSystem extends HarFileSystem {
   public BlockLocation[] getFileBlockLocations(FileStatus file, long start, long len) throws IOException {
      String[] hosts = new String[]{"DUMMY_HOST"};
      return new BlockLocation[]{new BlockLocation((String[])null, hosts, 0L, file.getLen())};
   }

   public ContentSummary getContentSummary(Path f) throws IOException {
      FileStatus status = this.getFileStatus(f);
      if (!status.isDir()) {
         return new ContentSummary(status.getLen(), 1L, 0L);
      } else {
         long[] summary = new long[]{0L, 0L, 1L};

         for(FileStatus s : this.listStatus(f)) {
            ContentSummary c = s.isDir() ? this.getContentSummary(s.getPath()) : new ContentSummary(s.getLen(), 1L, 0L);
            summary[0] += c.getLength();
            summary[1] += c.getFileCount();
            summary[2] += c.getDirectoryCount();
         }

         return new ContentSummary(summary[0], summary[1], summary[2]);
      }
   }
}
