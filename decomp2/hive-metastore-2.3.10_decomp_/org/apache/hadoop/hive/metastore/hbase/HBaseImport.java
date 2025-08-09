package org.apache.hadoop.hive.metastore.hbase;

import com.google.common.annotations.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.metastore.Deadline;
import org.apache.hadoop.hive.metastore.ObjectStore;
import org.apache.hadoop.hive.metastore.RawStore;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.Index;
import org.apache.hadoop.hive.metastore.api.InvalidObjectException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.NoSuchObjectException;
import org.apache.hadoop.hive.metastore.api.Partition;
import org.apache.hadoop.hive.metastore.api.Role;
import org.apache.hadoop.hive.metastore.api.SQLForeignKey;
import org.apache.hadoop.hive.metastore.api.SQLPrimaryKey;
import org.apache.hadoop.hive.metastore.api.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseImport {
   private static final Logger LOG = LoggerFactory.getLogger(HBaseImport.class.getName());
   private ThreadLocal rdbmsStore = new ThreadLocal() {
      protected RawStore initialValue() {
         if (HBaseImport.this.rdbmsConf == null) {
            throw new RuntimeException("order violation, need to set rdbms conf first");
         } else {
            RawStore os = new ObjectStore();
            os.setConf(HBaseImport.this.rdbmsConf);
            return os;
         }
      }
   };
   private ThreadLocal hbaseStore = new ThreadLocal() {
      protected RawStore initialValue() {
         if (HBaseImport.this.hbaseConf == null) {
            throw new RuntimeException("order violation, need to set hbase conf first");
         } else {
            RawStore hs = new HBaseStore();
            hs.setConf(HBaseImport.this.hbaseConf);
            return hs;
         }
      }
   };
   private Configuration rdbmsConf;
   private Configuration hbaseConf;
   private List dbs;
   private BlockingQueue partitionedTables;
   private BlockingQueue tableNameQueue;
   private BlockingQueue indexNameQueue;
   private BlockingQueue partQueue;
   private boolean writingToQueue;
   private boolean readersFinished;
   private boolean doKerberos;
   private boolean doAll;
   private List rolesToImport;
   private List dbsToImport;
   private List tablesToImport;
   private List functionsToImport;
   private int parallel;
   private int batchSize;

   public static int main(String[] args) {
      try {
         HBaseImport tool = new HBaseImport();
         int rv = tool.init(args);
         if (rv != 0) {
            return rv;
         } else {
            tool.run();
            return 0;
         }
      } catch (Exception e) {
         System.err.println("Caught exception " + e.getClass().getName() + " with message <" + e.getMessage() + ">");
         return 1;
      }
   }

   private HBaseImport() {
   }

   @VisibleForTesting
   public HBaseImport(String... args) throws ParseException {
      this.init(args);
   }

   private int init(String... args) throws ParseException {
      Options options = new Options();
      this.doAll = this.doKerberos = false;
      this.parallel = 1;
      this.batchSize = 1000;
      OptionBuilder.withLongOpt("all");
      OptionBuilder.withDescription("Import the full metastore");
      options.addOption(OptionBuilder.create('a'));
      OptionBuilder.withLongOpt("batchsize");
      OptionBuilder.withDescription("Number of partitions to read and write in a batch, defaults to 1000");
      OptionBuilder.hasArg();
      options.addOption(OptionBuilder.create('b'));
      OptionBuilder.withLongOpt("database");
      OptionBuilder.withDescription("Import a single database");
      OptionBuilder.hasArgs();
      options.addOption(OptionBuilder.create('d'));
      OptionBuilder.withLongOpt("help");
      OptionBuilder.withDescription("You're looking at it");
      options.addOption(OptionBuilder.create('h'));
      OptionBuilder.withLongOpt("function");
      OptionBuilder.withDescription("Import a single function");
      OptionBuilder.hasArgs();
      options.addOption(OptionBuilder.create('f'));
      OptionBuilder.withLongOpt("kerberos");
      OptionBuilder.withDescription("Import all kerberos related objects (master key, tokens)");
      options.addOption(OptionBuilder.create('k'));
      OptionBuilder.withLongOpt("parallel");
      OptionBuilder.withDescription("Parallel factor for loading (only applied to tables and partitions), defaults to 1");
      OptionBuilder.hasArg();
      options.addOption(OptionBuilder.create('p'));
      OptionBuilder.withLongOpt("role");
      OptionBuilder.withDescription("Import a single role");
      OptionBuilder.hasArgs();
      options.addOption(OptionBuilder.create('r'));
      OptionBuilder.withLongOpt("tables");
      OptionBuilder.withDescription("Import a single tables");
      OptionBuilder.hasArgs();
      options.addOption(OptionBuilder.create('t'));
      CommandLine cli = (new GnuParser()).parse(options, args);
      if (cli.hasOption('h')) {
         this.printHelp(options);
         return 1;
      } else {
         boolean hasCmd = false;
         if (cli.hasOption('a')) {
            hasCmd = true;
            this.doAll = true;
         }

         if (cli.hasOption('b')) {
            this.batchSize = Integer.parseInt(cli.getOptionValue('b'));
         }

         if (cli.hasOption('d')) {
            hasCmd = true;
            this.dbsToImport = Arrays.asList(cli.getOptionValues('d'));
         }

         if (cli.hasOption('f')) {
            hasCmd = true;
            this.functionsToImport = Arrays.asList(cli.getOptionValues('f'));
         }

         if (cli.hasOption('p')) {
            this.parallel = Integer.parseInt(cli.getOptionValue('p'));
         }

         if (cli.hasOption('r')) {
            hasCmd = true;
            this.rolesToImport = Arrays.asList(cli.getOptionValues('r'));
         }

         if (cli.hasOption('k')) {
            this.doKerberos = true;
         }

         if (cli.hasOption('t')) {
            hasCmd = true;
            this.tablesToImport = Arrays.asList(cli.getOptionValues('t'));
         }

         if (!hasCmd) {
            this.printHelp(options);
            return 1;
         } else {
            this.dbs = new ArrayList();
            this.partitionedTables = new LinkedBlockingQueue();
            this.tableNameQueue = new LinkedBlockingQueue();
            this.indexNameQueue = new LinkedBlockingQueue();
            this.partQueue = new ArrayBlockingQueue(this.parallel * 2);
            return 0;
         }
      }
   }

   private void printHelp(Options options) {
      (new HelpFormatter()).printHelp("hbaseschematool", options);
   }

   @VisibleForTesting
   void run() throws MetaException, InstantiationException, IllegalAccessException, NoSuchObjectException, InvalidObjectException, InterruptedException {
      this.init();
      if (this.doAll || this.rolesToImport != null) {
         this.copyRoles();
      }

      if (this.doAll || this.dbsToImport != null) {
         this.copyDbs();
      }

      if (this.doAll || this.dbsToImport != null || this.tablesToImport != null) {
         this.copyTables();
         this.copyPartitions();
         this.copyIndexes();
      }

      if (this.doAll || this.dbsToImport != null || this.functionsToImport != null) {
         this.copyFunctions();
      }

      if (this.doAll || this.doKerberos) {
         this.copyKerberos();
      }

   }

   private void init() throws MetaException, IllegalAccessException, InstantiationException {
      if (this.rdbmsConf == null) {
         this.rdbmsConf = new HiveConf();
         this.hbaseConf = new HiveConf();
         HiveConf.setVar(this.hbaseConf, ConfVars.METASTORE_RAW_STORE_IMPL, HBaseStore.class.getName());
         HiveConf.setBoolVar(this.hbaseConf, ConfVars.METASTORE_FASTPATH, true);
         ((RawStore)this.rdbmsStore.get()).setConf(this.rdbmsConf);
         ((RawStore)this.hbaseStore.get()).setConf(this.hbaseConf);
      }
   }

   private void copyRoles() throws NoSuchObjectException, InvalidObjectException, MetaException {
      this.screen("Copying roles");

      for(String roleName : this.doAll ? ((RawStore)this.rdbmsStore.get()).listRoleNames() : this.rolesToImport) {
         Role role = ((RawStore)this.rdbmsStore.get()).getRole(roleName);
         this.screen("Copying role " + roleName);
         ((RawStore)this.hbaseStore.get()).addRole(roleName, role.getOwnerName());
      }

   }

   private void copyDbs() throws MetaException, NoSuchObjectException, InvalidObjectException {
      this.screen("Copying databases");

      for(String dbName : this.doAll ? ((RawStore)this.rdbmsStore.get()).getAllDatabases() : this.dbsToImport) {
         Database db = ((RawStore)this.rdbmsStore.get()).getDatabase(dbName);
         this.dbs.add(db);
         this.screen("Copying database " + dbName);
         ((RawStore)this.hbaseStore.get()).createDatabase(db);
      }

   }

   private void copyTables() throws MetaException, InvalidObjectException, InterruptedException {
      this.screen("Copying tables");
      Thread[] copiers = new Thread[this.parallel];
      this.writingToQueue = true;

      for(int i = 0; i < this.parallel; ++i) {
         copiers[i] = new TableCopier();
         copiers[i].start();
      }

      for(Database db : this.dbs) {
         this.screen("Coyping tables in database " + db.getName());

         for(String tableName : ((RawStore)this.rdbmsStore.get()).getAllTables(db.getName())) {
            this.tableNameQueue.put(new String[]{db.getName(), tableName});
         }
      }

      if (this.tablesToImport != null) {
         for(String compoundTableName : this.tablesToImport) {
            String[] tn = compoundTableName.split("\\.");
            if (tn.length != 2) {
               this.error(compoundTableName + " not in proper form.  Must be in form dbname.tablename.  Ignoring this table and continuing.");
            } else {
               this.tableNameQueue.put(new String[]{tn[0], tn[1]});
            }
         }
      }

      this.writingToQueue = false;

      for(Thread copier : copiers) {
         copier.join();
      }

   }

   private void copyIndexes() throws MetaException, InvalidObjectException, InterruptedException {
      this.screen("Copying indexes");
      Thread[] copiers = new Thread[this.parallel];
      this.writingToQueue = true;

      for(int i = 0; i < this.parallel; ++i) {
         copiers[i] = new IndexCopier();
         copiers[i].start();
      }

      for(Database db : this.dbs) {
         this.screen("Coyping indexes in database " + db.getName());

         for(String tableName : ((RawStore)this.rdbmsStore.get()).getAllTables(db.getName())) {
            for(Index index : ((RawStore)this.rdbmsStore.get()).getIndexes(db.getName(), tableName, -1)) {
               this.indexNameQueue.put(new String[]{db.getName(), tableName, index.getIndexName()});
            }
         }
      }

      if (this.tablesToImport != null) {
         for(String compoundTableName : this.tablesToImport) {
            String[] tn = compoundTableName.split("\\.");
            if (tn.length != 2) {
               this.error(compoundTableName + " not in proper form.  Must be in form dbname.tablename.  Ignoring this table and continuing.");
            } else {
               for(Index index : ((RawStore)this.rdbmsStore.get()).getIndexes(tn[0], tn[1], -1)) {
                  this.indexNameQueue.put(new String[]{tn[0], tn[1], index.getIndexName()});
               }
            }
         }
      }

      this.writingToQueue = false;

      for(Thread copier : copiers) {
         copier.join();
      }

   }

   private void copyPartitions() throws MetaException, NoSuchObjectException, InvalidObjectException, InterruptedException {
      this.screen("Copying partitions");
      this.readersFinished = false;
      Thread[] readers = new Thread[this.parallel];
      Thread[] writers = new Thread[this.parallel];

      for(int i = 0; i < this.parallel; ++i) {
         readers[i] = new PartitionReader();
         readers[i].start();
         writers[i] = new PartitionWriter();
         writers[i].start();
      }

      for(Thread reader : readers) {
         reader.join();
      }

      this.readersFinished = true;

      for(Thread writer : writers) {
         writer.join();
      }

   }

   private void copyFunctions() throws MetaException, NoSuchObjectException, InvalidObjectException {
      this.screen("Copying functions");

      for(Database db : this.dbs) {
         this.screen("Copying functions in database " + db.getName());

         for(String funcName : ((RawStore)this.rdbmsStore.get()).getFunctions(db.getName(), "*")) {
            this.copyOneFunction(db.getName(), funcName);
         }
      }

      if (this.functionsToImport != null) {
         for(String compoundFuncName : this.functionsToImport) {
            String[] fn = compoundFuncName.split("\\.");
            if (fn.length != 2) {
               this.error(compoundFuncName + " not in proper form.  Must be in form dbname.funcname.  Ignoring this function and continuing.");
            } else {
               this.copyOneFunction(fn[0], fn[1]);
            }
         }
      }

   }

   private void copyOneFunction(String dbName, String funcName) throws MetaException, InvalidObjectException {
      Function func = ((RawStore)this.rdbmsStore.get()).getFunction(dbName, funcName);
      this.screen("Copying function " + dbName + "." + funcName);
      ((RawStore)this.hbaseStore.get()).createFunction(func);
   }

   private void copyKerberos() throws MetaException {
      this.screen("Copying kerberos related items");

      for(String tokenId : ((RawStore)this.rdbmsStore.get()).getAllTokenIdentifiers()) {
         String token = ((RawStore)this.rdbmsStore.get()).getToken(tokenId);
         ((RawStore)this.hbaseStore.get()).addToken(tokenId, token);
      }

      for(String masterKey : ((RawStore)this.rdbmsStore.get()).getMasterKeys()) {
         ((RawStore)this.hbaseStore.get()).addMasterKey(masterKey);
      }

   }

   private void screen(String msg) {
      LOG.info(msg);
      System.out.println(msg);
   }

   private void error(String msg) {
      LOG.error(msg);
      System.err.println("ERROR:  " + msg);
   }

   @VisibleForTesting
   void setConnections(RawStore rdbms, RawStore hbase) {
      this.rdbmsStore.set(rdbms);
      this.hbaseStore.set(hbase);
      this.rdbmsConf = rdbms.getConf();
      this.hbaseConf = hbase.getConf();
   }

   private class TableCopier extends Thread {
      private TableCopier() {
      }

      public void run() {
         while(HBaseImport.this.writingToQueue || HBaseImport.this.tableNameQueue.size() > 0) {
            try {
               String[] name = (String[])HBaseImport.this.tableNameQueue.poll(1L, TimeUnit.SECONDS);
               if (name != null) {
                  Table table = ((RawStore)HBaseImport.this.rdbmsStore.get()).getTable(name[0], name[1]);
                  if (table.getPartitionKeys() != null && table.getPartitionKeys().size() > 0) {
                     HBaseImport.this.partitionedTables.put(table);
                  }

                  HBaseImport.this.screen("Copying table " + name[0] + "." + name[1]);
                  ((RawStore)HBaseImport.this.hbaseStore.get()).createTable(table);
                  List<SQLPrimaryKey> pk = ((RawStore)HBaseImport.this.rdbmsStore.get()).getPrimaryKeys(table.getDbName(), table.getTableName());
                  if (pk != null && pk.size() > 0) {
                     HBaseImport.LOG.debug("Found primary keys, adding them");
                     ((RawStore)HBaseImport.this.hbaseStore.get()).addPrimaryKeys(pk);
                  }

                  List<SQLForeignKey> fks = ((RawStore)HBaseImport.this.rdbmsStore.get()).getForeignKeys((String)null, (String)null, table.getDbName(), table.getTableName());
                  if (fks != null && fks.size() > 0) {
                     HBaseImport.LOG.debug("Found foreign keys, adding them");
                     ((RawStore)HBaseImport.this.hbaseStore.get()).addForeignKeys(fks);
                  }
               }
            } catch (MetaException | InvalidObjectException | InterruptedException e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   private class IndexCopier extends Thread {
      private IndexCopier() {
      }

      public void run() {
         while(HBaseImport.this.writingToQueue || HBaseImport.this.indexNameQueue.size() > 0) {
            try {
               String[] name = (String[])HBaseImport.this.indexNameQueue.poll(1L, TimeUnit.SECONDS);
               if (name != null) {
                  Index index = ((RawStore)HBaseImport.this.rdbmsStore.get()).getIndex(name[0], name[1], name[2]);
                  HBaseImport.this.screen("Copying index " + name[0] + "." + name[1] + "." + name[2]);
                  ((RawStore)HBaseImport.this.hbaseStore.get()).addIndex(index);
               }
            } catch (MetaException | InvalidObjectException | InterruptedException e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   private class PartitionReader extends Thread {
      private PartitionReader() {
      }

      public void run() {
         label32:
         while(true) {
            if (HBaseImport.this.partitionedTables.size() > 0) {
               try {
                  Table table = (Table)HBaseImport.this.partitionedTables.poll(1L, TimeUnit.SECONDS);
                  if (table == null) {
                     continue;
                  }

                  HBaseImport.this.screen("Fetching partitions for table " + table.getDbName() + "." + table.getTableName());
                  List<String> partNames = ((RawStore)HBaseImport.this.rdbmsStore.get()).listPartitionNames(table.getDbName(), table.getTableName(), (short)-1);
                  if (partNames.size() <= HBaseImport.this.batchSize) {
                     HBaseImport.LOG.debug("Adding all partition names to queue for " + table.getDbName() + "." + table.getTableName());
                     HBaseImport.this.partQueue.put(new PartQueueEntry(table.getDbName(), table.getTableName(), partNames));
                     continue;
                  }

                  int goUntil = partNames.size() % HBaseImport.this.batchSize == 0 ? partNames.size() / HBaseImport.this.batchSize : partNames.size() / HBaseImport.this.batchSize + 1;
                  int i = 0;

                  while(true) {
                     if (i >= goUntil) {
                        continue label32;
                     }

                     int start = i * HBaseImport.this.batchSize;
                     int end = Math.min((i + 1) * HBaseImport.this.batchSize, partNames.size());
                     HBaseImport.LOG.debug("Adding partitions " + start + " to " + end + " for " + table.getDbName() + "." + table.getTableName());
                     HBaseImport.this.partQueue.put(new PartQueueEntry(table.getDbName(), table.getTableName(), partNames.subList(start, end)));
                     ++i;
                  }
               } catch (MetaException | InterruptedException e) {
                  throw new RuntimeException(e);
               }
            }

            return;
         }
      }
   }

   private class PartitionWriter extends Thread {
      private PartitionWriter() {
      }

      public void run() {
         Deadline.registerIfNot(1000000L);

         while(!HBaseImport.this.readersFinished || HBaseImport.this.partQueue.size() > 0) {
            try {
               PartQueueEntry entry = (PartQueueEntry)HBaseImport.this.partQueue.poll(1L, TimeUnit.SECONDS);
               if (entry != null) {
                  HBaseImport.LOG.info("Writing partitions " + entry.dbName + "." + entry.tableName + "." + StringUtils.join(entry.partNames, ':'));
                  Deadline.startTimer("hbaseimport");
                  List<Partition> parts = ((RawStore)HBaseImport.this.rdbmsStore.get()).getPartitionsByNames(entry.dbName, entry.tableName, entry.partNames);
                  ((RawStore)HBaseImport.this.hbaseStore.get()).addPartitions(entry.dbName, entry.tableName, parts);
                  Deadline.stopTimer();
               }
            } catch (MetaException | InvalidObjectException | NoSuchObjectException | InterruptedException e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   private static class PartQueueEntry {
      final String dbName;
      final String tableName;
      final List partNames;

      PartQueueEntry(String d, String t, List p) {
         this.dbName = d;
         this.tableName = t;
         this.partNames = p;
      }
   }
}
