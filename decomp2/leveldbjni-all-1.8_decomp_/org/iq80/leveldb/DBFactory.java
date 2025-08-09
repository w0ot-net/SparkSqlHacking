package org.iq80.leveldb;

import java.io.File;
import java.io.IOException;

public interface DBFactory {
   DB open(File var1, Options var2) throws IOException;

   void destroy(File var1, Options var2) throws IOException;

   void repair(File var1, Options var2) throws IOException;
}
