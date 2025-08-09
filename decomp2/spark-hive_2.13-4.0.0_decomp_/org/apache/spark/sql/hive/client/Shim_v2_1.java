package org.apache.spark.sql.hive.client;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.ql.io.AcidUtils;
import org.apache.hadoop.hive.ql.io.AcidUtils.Operation;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.Table;
import scala.reflect.ScalaSignature;
import scala.runtime.Null;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c!\u0002\b\u0010\u0001=Y\u0002\"\u0002\u0011\u0001\t\u0003\u0011\u0003\u0002\u0003\u0013\u0001\u0011\u000b\u0007I\u0011C\u0013\t\u00119\u0002\u0001R1A\u0005\u0012=B\u0001B\u000e\u0001\t\u0006\u0004%Ia\u000e\u0005\t}\u0001A)\u0019!C\u0005o!Aq\b\u0001EC\u0002\u0013%q\u0007\u0003\u0005A\u0001!\u0015\r\u0011\"\u00038\u0011!\t\u0005\u0001#b\u0001\n\u00139\u0004\"\u0002\"\u0001\t\u0003\u001a\u0005\"\u0002>\u0001\t\u0003Z\bbBA\u0002\u0001\u0011\u0005\u0013Q\u0001\u0005\b\u0003K\u0001A\u0011IA\u0014\u0011\u001d\t\t\u0004\u0001C!\u0003g\u0011\u0011b\u00155j[~3(gX\u0019\u000b\u0005A\t\u0012AB2mS\u0016tGO\u0003\u0002\u0013'\u0005!\u0001.\u001b<f\u0015\t!R#A\u0002tc2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\n\u0003\u0001q\u0001\"!\b\u0010\u000e\u0003=I!aH\b\u0003\u0013MC\u0017.\\0we}\u0003\u0014A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\r\u0002\"!\b\u0001\u0002+!\f7OR8mY><\u0018N\\4Ti\u0006$8\u000fV1tWV\ta\u0005\u0005\u0002(Y5\t\u0001F\u0003\u0002*U\u0005!A.\u00198h\u0015\u0005Y\u0013\u0001\u00026bm\u0006L!!\f\u0015\u0003\u000f\t{w\u000e\\3b]\u0006qRM\u001c<je>tW.\u001a8u\u0007>tG/\u001a=u\u0013:\fE\u000e^3s)\u0006\u0014G.Z\u000b\u0002aA\u0011\u0011\u0007N\u0007\u0002e)\t1'A\u0003tG\u0006d\u0017-\u0003\u00026e\t!a*\u001e7m\u0003Maw.\u00193QCJ$\u0018\u000e^5p]6+G\u000f[8e+\u0005A\u0004CA\u001d=\u001b\u0005Q$BA\u001e)\u0003\u001d\u0011XM\u001a7fGRL!!\u0010\u001e\u0003\r5+G\u000f[8e\u0003=aw.\u00193UC\ndW-T3uQ>$\u0017a\u00077pC\u0012$\u0015P\\1nS\u000e\u0004\u0016M\u001d;ji&|gn]'fi\"|G-\u0001\tbYR,'\u000fV1cY\u0016lU\r\u001e5pI\u0006)\u0012\r\u001c;feB\u000b'\u000f^5uS>t7/T3uQ>$\u0017!\u00047pC\u0012\u0004\u0016M\u001d;ji&|g\u000eF\u0005E\u000fN[\u0006\u000e\u001d;wqB\u0011\u0011'R\u0005\u0003\rJ\u0012A!\u00168ji\")!#\u0003a\u0001\u0011B\u0011\u0011*U\u0007\u0002\u0015*\u00111\nT\u0001\t[\u0016$\u0018\rZ1uC*\u0011QJT\u0001\u0003c2T!AE(\u000b\u0005A;\u0012A\u00025bI>|\u0007/\u0003\u0002S\u0015\n!\u0001*\u001b<f\u0011\u0015!\u0016\u00021\u0001V\u0003!aw.\u00193QCRD\u0007C\u0001,Z\u001b\u00059&B\u0001-P\u0003\t17/\u0003\u0002[/\n!\u0001+\u0019;i\u0011\u0015a\u0016\u00021\u0001^\u0003%!\u0018M\u00197f\u001d\u0006lW\r\u0005\u0002_K:\u0011ql\u0019\t\u0003AJj\u0011!\u0019\u0006\u0003E\u0006\na\u0001\u0010:p_Rt\u0014B\u000133\u0003\u0019\u0001&/\u001a3fM&\u0011am\u001a\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0011\u0014\u0004\"B5\n\u0001\u0004Q\u0017\u0001\u00039beR\u001c\u0006/Z2\u0011\t-tW,X\u0007\u0002Y*\u0011QNK\u0001\u0005kRLG.\u0003\u0002pY\n\u0019Q*\u00199\t\u000bEL\u0001\u0019\u0001:\u0002\u000fI,\u0007\u000f\\1dKB\u0011\u0011g]\u0005\u0003[IBQ!^\u0005A\u0002I\f\u0011#\u001b8iKJLG\u000fV1cY\u0016\u001c\u0006/Z2t\u0011\u00159\u0018\u00021\u0001s\u0003UI7oU6fo\u0016$7\u000b^8sK\u0006\u001b8+\u001e2eSJDQ!_\u0005A\u0002I\f!\"[:Te\u000edunY1m\u0003%aw.\u00193UC\ndW\rF\u0004Eyvtx0!\u0001\t\u000bIQ\u0001\u0019\u0001%\t\u000bQS\u0001\u0019A+\t\u000bqS\u0001\u0019A/\t\u000bET\u0001\u0019\u0001:\t\u000beT\u0001\u0019\u0001:\u0002+1|\u0017\r\u001a#z]\u0006l\u0017n\u0019)beRLG/[8ogRyA)a\u0002\u0002\n\u0005-\u0011QBA\b\u0003#\tY\u0002C\u0003\u0013\u0017\u0001\u0007\u0001\nC\u0003U\u0017\u0001\u0007Q\u000bC\u0003]\u0017\u0001\u0007Q\fC\u0003j\u0017\u0001\u0007!\u000eC\u0003r\u0017\u0001\u0007!\u000fC\u0004\u0002\u0014-\u0001\r!!\u0006\u0002\u000b9,X\u000e\u0012)\u0011\u0007E\n9\"C\u0002\u0002\u001aI\u00121!\u00138u\u0011\u001d\tib\u0003a\u0001\u0003?\t\u0011\u0002[5wKR\u000b'\r\\3\u0011\u0007%\u000b\t#C\u0002\u0002$)\u0013Q\u0001V1cY\u0016\f!\"\u00197uKJ$\u0016M\u00197f)\u001d!\u0015\u0011FA\u0016\u0003[AQA\u0005\u0007A\u0002!CQ\u0001\u0018\u0007A\u0002uCq!a\f\r\u0001\u0004\ty\"A\u0003uC\ndW-A\bbYR,'\u000fU1si&$\u0018n\u001c8t)\u001d!\u0015QGA\u001c\u0003sAQAE\u0007A\u0002!CQ\u0001X\u0007A\u0002uCq!a\u000f\u000e\u0001\u0004\ti$\u0001\u0005oK^\u0004\u0016M\u001d;t!\u0015Y\u0017qHA\"\u0013\r\t\t\u0005\u001c\u0002\u0005\u0019&\u001cH\u000fE\u0002J\u0003\u000bJ1!a\u0012K\u0005%\u0001\u0016M\u001d;ji&|g\u000e"
)
public class Shim_v2_1 extends Shim_v2_0 {
   private Boolean hasFollowingStatsTask;
   private Null environmentContextInAlterTable;
   private Method loadPartitionMethod;
   private Method loadTableMethod;
   private Method loadDynamicPartitionsMethod;
   private Method alterTableMethod;
   private Method alterPartitionsMethod;
   private volatile byte bitmap$0;

   private Boolean hasFollowingStatsTask$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.hasFollowingStatsTask = Boolean.FALSE;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hasFollowingStatsTask;
   }

   public Boolean hasFollowingStatsTask() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.hasFollowingStatsTask$lzycompute() : this.hasFollowingStatsTask;
   }

   private Null environmentContextInAlterTable$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.environmentContextInAlterTable = null;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.environmentContextInAlterTable;
   }

   public Null environmentContextInAlterTable() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.environmentContextInAlterTable$lzycompute() : this.environmentContextInAlterTable;
   }

   private Method loadPartitionMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.loadPartitionMethod = this.findMethod(Hive.class, "loadPartition", .MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Map.class, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadPartitionMethod;
   }

   private Method loadPartitionMethod() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.loadPartitionMethod$lzycompute() : this.loadPartitionMethod;
   }

   private Method loadTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.loadTableMethod = this.findMethod(Hive.class, "loadTable", .MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadTableMethod;
   }

   private Method loadTableMethod() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.loadTableMethod$lzycompute() : this.loadTableMethod;
   }

   private Method loadDynamicPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.loadDynamicPartitionsMethod = this.findMethod(Hive.class, "loadDynamicPartitions", .MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Map.class, Boolean.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Long.TYPE, Boolean.TYPE, AcidUtils.Operation.class})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadDynamicPartitionsMethod;
   }

   private Method loadDynamicPartitionsMethod() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.loadDynamicPartitionsMethod$lzycompute() : this.loadDynamicPartitionsMethod;
   }

   private Method alterTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.alterTableMethod = this.findMethod(Hive.class, "alterTable", .MODULE$.wrapRefArray((Object[])(new Class[]{String.class, Table.class, EnvironmentContext.class})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterTableMethod;
   }

   private Method alterTableMethod() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.alterTableMethod$lzycompute() : this.alterTableMethod;
   }

   private Method alterPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 64) == 0) {
            this.alterPartitionsMethod = this.findMethod(Hive.class, "alterPartitions", .MODULE$.wrapRefArray((Object[])(new Class[]{String.class, List.class, EnvironmentContext.class})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterPartitionsMethod;
   }

   private Method alterPartitionsMethod() {
      return (byte)(this.bitmap$0 & 64) == 0 ? this.alterPartitionsMethod$lzycompute() : this.alterPartitionsMethod;
   }

   public void loadPartition(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSkewedStoreAsSubdir, final boolean isSrcLocal) {
      this.recordHiveCall();
      this.loadPartitionMethod().invoke(hive, loadPath, tableName, partSpec, scala.Predef..MODULE$.boolean2Boolean(replace), scala.Predef..MODULE$.boolean2Boolean(inheritTableSpecs), scala.Predef..MODULE$.boolean2Boolean(isSkewedStoreAsSubdir), scala.Predef..MODULE$.boolean2Boolean(isSrcLocal), this.isAcid(), this.hasFollowingStatsTask());
   }

   public void loadTable(final Hive hive, final Path loadPath, final String tableName, final boolean replace, final boolean isSrcLocal) {
      this.recordHiveCall();
      this.loadTableMethod().invoke(hive, loadPath, tableName, scala.Predef..MODULE$.boolean2Boolean(replace), scala.Predef..MODULE$.boolean2Boolean(isSrcLocal), this.isSkewedStoreAsSubdir(), this.isAcid(), this.hasFollowingStatsTask());
   }

   public void loadDynamicPartitions(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final int numDP, final Table hiveTable) {
      this.recordHiveCall();
      boolean listBucketingEnabled = hiveTable.isStoredAsSubDirectories();
      this.loadDynamicPartitionsMethod().invoke(hive, loadPath, tableName, partSpec, scala.Predef..MODULE$.boolean2Boolean(replace), scala.Predef..MODULE$.int2Integer(numDP), scala.Predef..MODULE$.boolean2Boolean(listBucketingEnabled), this.isAcid(), this.txnIdInLoadDynamicPartitions(), this.hasFollowingStatsTask(), Operation.NOT_ACID);
   }

   public void alterTable(final Hive hive, final String tableName, final Table table) {
      this.recordHiveCall();
      Method var10000 = this.alterTableMethod();
      Object[] var10002 = new Object[]{tableName, table, null};
      this.environmentContextInAlterTable();
      var10002[2] = null;
      var10000.invoke(hive, var10002);
   }

   public void alterPartitions(final Hive hive, final String tableName, final List newParts) {
      this.recordHiveCall();
      Method var10000 = this.alterPartitionsMethod();
      Object[] var10002 = new Object[]{tableName, newParts, null};
      this.environmentContextInAlterTable();
      var10002[2] = null;
      var10000.invoke(hive, var10002);
   }
}
