package io.airlift.compress.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface HadoopStreams {
   String getDefaultFileExtension();

   List getHadoopCodecName();

   HadoopInputStream createInputStream(InputStream in) throws IOException;

   HadoopOutputStream createOutputStream(OutputStream out) throws IOException;
}
