package org.apache.hadoop.hive.metastore.hbase;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.hbase.client.HTableInterface;

public interface HBaseConnection extends Configurable {
   void connect() throws IOException;

   void close() throws IOException;

   void beginTransaction() throws IOException;

   void commitTransaction() throws IOException;

   void rollbackTransaction() throws IOException;

   void flush(HTableInterface var1) throws IOException;

   void createHBaseTable(String var1, List var2) throws IOException;

   HTableInterface getHBaseTable(String var1) throws IOException;

   HTableInterface getHBaseTable(String var1, boolean var2) throws IOException;
}
