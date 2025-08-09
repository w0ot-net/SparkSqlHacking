package org.apache.derby.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.derby.shared.common.error.StandardException;

public interface StorageFile {
   int NO_FILE_LOCK_SUPPORT = 0;
   int EXCLUSIVE_FILE_LOCK = 1;
   int EXCLUSIVE_FILE_LOCK_NOT_AVAILABLE = 2;

   String[] list();

   boolean canWrite();

   boolean exists();

   boolean isDirectory();

   boolean delete();

   boolean deleteAll();

   String getPath();

   String getCanonicalPath() throws IOException;

   String getName();

   boolean createNewFile() throws IOException;

   boolean renameTo(StorageFile var1);

   boolean mkdir();

   boolean mkdirs();

   StorageFile getParentDir();

   boolean setReadOnly();

   OutputStream getOutputStream() throws FileNotFoundException;

   OutputStream getOutputStream(boolean var1) throws FileNotFoundException;

   InputStream getInputStream() throws FileNotFoundException;

   int getExclusiveFileLock() throws StandardException;

   void releaseExclusiveFileLock();

   StorageRandomAccessFile getRandomAccessFile(String var1) throws FileNotFoundException;

   void limitAccessToOwner() throws IOException;
}
