package org.apache.logging.log4j.core.appender.nosql;

import java.io.Closeable;

public interface NoSqlConnection extends Closeable {
   NoSqlObject createObject();

   NoSqlObject[] createList(int length);

   void insertObject(NoSqlObject object);

   void close();

   boolean isClosed();
}
