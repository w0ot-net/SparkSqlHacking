package org.apache.derby.iapi.store.access;

import java.io.InputStream;
import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public interface FileResource {
   String JAR_DIRECTORY_NAME = "jar";

   long add(String var1, InputStream var2) throws StandardException;

   void remove(String var1, long var2) throws StandardException;

   void removeJarDir(String var1) throws StandardException;

   long replace(String var1, long var2, InputStream var4) throws StandardException;

   StorageFile getAsFile(String var1, long var2);

   char getSeparatorChar();
}
