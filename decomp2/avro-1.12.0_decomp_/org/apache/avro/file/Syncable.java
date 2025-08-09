package org.apache.avro.file;

import java.io.IOException;

public interface Syncable {
   void sync() throws IOException;
}
