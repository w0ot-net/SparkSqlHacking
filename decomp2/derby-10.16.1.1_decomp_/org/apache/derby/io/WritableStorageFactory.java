package org.apache.derby.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.SyncFailedException;

public interface WritableStorageFactory extends StorageFactory {
   void sync(OutputStream var1, boolean var2) throws IOException, SyncFailedException;

   boolean supportsWriteSync();
}
