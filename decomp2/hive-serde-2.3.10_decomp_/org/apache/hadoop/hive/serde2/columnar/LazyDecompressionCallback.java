package org.apache.hadoop.hive.serde2.columnar;

import java.io.IOException;

public interface LazyDecompressionCallback {
   byte[] decompress() throws IOException;
}
