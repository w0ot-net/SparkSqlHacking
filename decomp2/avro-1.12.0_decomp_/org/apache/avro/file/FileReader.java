package org.apache.avro.file;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import org.apache.avro.Schema;

public interface FileReader extends Iterator, Iterable, Closeable {
   Schema getSchema();

   Object next(Object reuse) throws IOException;

   void sync(long position) throws IOException;

   boolean pastSync(long position) throws IOException;

   long tell() throws IOException;
}
