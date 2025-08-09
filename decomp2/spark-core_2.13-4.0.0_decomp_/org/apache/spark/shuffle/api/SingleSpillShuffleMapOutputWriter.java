package org.apache.spark.shuffle.api;

import java.io.File;
import java.io.IOException;
import org.apache.spark.annotation.Private;

@Private
public interface SingleSpillShuffleMapOutputWriter {
   void transferMapSpillFile(File var1, long[] var2, long[] var3) throws IOException;
}
