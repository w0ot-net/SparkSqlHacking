package org.apache.hadoop.hive.metastore.hbase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaHBaseConnection implements HBaseConnection {
   private static final Logger LOG = LoggerFactory.getLogger(VanillaHBaseConnection.class.getName());
   protected HConnection conn;
   protected Map tables = new HashMap();
   protected Configuration conf;

   VanillaHBaseConnection() {
   }

   public void connect() throws IOException {
      if (this.conf == null) {
         throw new RuntimeException("Must call getConf before connect");
      } else {
         this.conn = HConnectionManager.createConnection(this.conf);
      }
   }

   public void close() throws IOException {
      for(HTableInterface htab : this.tables.values()) {
         htab.close();
      }

   }

   public void beginTransaction() throws IOException {
   }

   public void commitTransaction() throws IOException {
   }

   public void rollbackTransaction() throws IOException {
   }

   public void flush(HTableInterface htab) throws IOException {
      htab.flushCommits();
   }

   public void createHBaseTable(String tableName, List columnFamilies) throws IOException {
      HBaseAdmin admin = new HBaseAdmin(this.conn);
      LOG.info("Creating HBase table " + tableName);
      admin.createTable(this.buildDescriptor(tableName, columnFamilies));
      admin.close();
   }

   protected HTableDescriptor buildDescriptor(String tableName, List columnFamilies) throws IOException {
      HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));

      for(byte[] cf : columnFamilies) {
         tableDesc.addFamily(new HColumnDescriptor(cf));
      }

      return tableDesc;
   }

   public HTableInterface getHBaseTable(String tableName) throws IOException {
      return this.getHBaseTable(tableName, false);
   }

   public HTableInterface getHBaseTable(String tableName, boolean force) throws IOException {
      HTableInterface htab = (HTableInterface)this.tables.get(tableName);
      if (htab == null) {
         LOG.debug("Trying to connect to table " + tableName);

         try {
            htab = this.conn.getTable(tableName);
            if (force) {
               htab.get(new Get("nosuchkey".getBytes(HBaseUtils.ENCODING)));
            }
         } catch (IOException var5) {
            LOG.info("Caught exception when table was missing");
            return null;
         }

         htab.setAutoFlushTo(false);
         this.tables.put(tableName, htab);
      }

      return htab;
   }

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }
}
