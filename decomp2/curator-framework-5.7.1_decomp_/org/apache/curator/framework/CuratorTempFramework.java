package org.apache.curator.framework;

import java.io.Closeable;
import org.apache.curator.framework.api.TempGetDataBuilder;
import org.apache.curator.framework.api.transaction.CuratorTransaction;

public interface CuratorTempFramework extends Closeable {
   void close();

   CuratorTransaction inTransaction() throws Exception;

   TempGetDataBuilder getData() throws Exception;
}
