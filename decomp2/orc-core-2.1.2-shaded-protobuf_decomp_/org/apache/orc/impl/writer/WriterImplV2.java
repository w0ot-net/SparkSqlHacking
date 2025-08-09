package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.orc.OrcFile;
import org.apache.orc.impl.WriterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriterImplV2 extends WriterImpl {
   private static final Logger LOG = LoggerFactory.getLogger(WriterImplV2.class);

   public WriterImplV2(FileSystem fs, Path path, OrcFile.WriterOptions opts) throws IOException {
      super(fs, path, opts);
      LOG.warn("ORC files written in " + OrcFile.Version.UNSTABLE_PRE_2_0.getName() + " will not be readable by other versions of the software. It is only for developer testing.");
   }
}
