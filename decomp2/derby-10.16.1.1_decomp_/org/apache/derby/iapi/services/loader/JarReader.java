package org.apache.derby.iapi.services.loader;

import org.apache.derby.io.StorageFile;
import org.apache.derby.shared.common.error.StandardException;

public interface JarReader {
   StorageFile getJarFile(String var1, String var2) throws StandardException;
}
