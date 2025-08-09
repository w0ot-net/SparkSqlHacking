package org.apache.avro.mapreduce;

import java.io.IOException;

public interface Syncable {
   long sync() throws IOException;
}
