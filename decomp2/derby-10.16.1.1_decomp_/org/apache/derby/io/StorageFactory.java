package org.apache.derby.io;

import java.io.IOException;

public interface StorageFactory {
   int VERSION_NUMBER = 1;

   void init(String var1, String var2, String var3, String var4) throws IOException;

   void shutdown();

   String getCanonicalName() throws IOException;

   StorageFile newStorageFile(String var1);

   StorageFile newStorageFile(String var1, String var2);

   StorageFile newStorageFile(StorageFile var1, String var2);

   char getSeparator();

   StorageFile getTempDir();

   boolean isFast();

   boolean isReadOnlyDatabase();

   boolean supportsRandomAccess();

   int getStorageFactoryVersion();

   StorageFile createTemporaryFile(String var1, String var2) throws IOException;

   void setCanonicalName(String var1);
}
