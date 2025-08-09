package org.apache.avro.mapred;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;

public abstract class AvroCollector extends Configured {
   public abstract void collect(Object datum) throws IOException;
}
