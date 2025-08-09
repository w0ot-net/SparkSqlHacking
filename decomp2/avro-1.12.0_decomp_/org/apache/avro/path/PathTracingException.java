package org.apache.avro.path;

import org.apache.avro.Schema;

public interface PathTracingException {
   void tracePath(PathElement step);

   Throwable summarize(Schema root);
}
