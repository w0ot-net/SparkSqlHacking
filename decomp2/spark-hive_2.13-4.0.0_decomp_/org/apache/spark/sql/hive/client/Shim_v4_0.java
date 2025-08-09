package org.apache.spark.sql.hive.client;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.metastore.api.EnvironmentContext;
import org.apache.hadoop.hive.ql.exec.Utilities;
import org.apache.hadoop.hive.ql.io.AcidUtils;
import org.apache.hadoop.hive.ql.io.AcidUtils.Operation;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.Partition;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.DynamicPartitionCtx;
import org.apache.spark.util.Utils.;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg!\u0002\u000b\u0016\u0001U\t\u0003\"\u0002\u0014\u0001\t\u0003A\u0003\u0002\u0003\u0016\u0001\u0011\u000b\u0007I\u0011B\u0016\t\u0011\r\u0003\u0001R1A\u0005\n\u0011C\u0001\"\u0013\u0001\t\u0006\u0004%IA\u0013\u0005\t+\u0002A)\u0019!C)-\"A!\f\u0001EC\u0002\u0013%1\f\u0003\u0005c\u0001!\u0015\r\u0011\"\u0003\\\u0011!\u0019\u0007\u0001#b\u0001\n\u0013Y\u0006\u0002\u00033\u0001\u0011\u000b\u0007I\u0011B.\t\u0011\u0015\u0004\u0001R1A\u0005\nmC\u0001B\u001a\u0001\t\u0006\u0004%Ia\u0017\u0005\tO\u0002A)\u0019!C\u00057\")\u0001\u000e\u0001C!S\"9\u0011\u0011\u0001\u0001\u0005B\u0005\r\u0001bBA\u0014\u0001\u0011\u0005\u0013\u0011\u0006\u0005\b\u0003\u000b\u0002A\u0011IA$\u0011\u001d\t9\b\u0001C!\u0003sBq!a&\u0001\t\u0003\nI\nC\u0004\u00024\u0002!\t%!.\u0003\u0013MC\u0017.\\0wi}\u0003$B\u0001\f\u0018\u0003\u0019\u0019G.[3oi*\u0011\u0001$G\u0001\u0005Q&4XM\u0003\u0002\u001b7\u0005\u00191/\u001d7\u000b\u0005qi\u0012!B:qCJ\\'B\u0001\u0010 \u0003\u0019\t\u0007/Y2iK*\t\u0001%A\u0002pe\u001e\u001c\"\u0001\u0001\u0012\u0011\u0005\r\"S\"A\u000b\n\u0005\u0015*\"!C*iS6|foM02\u0003\u0019a\u0014N\\5u}\r\u0001A#A\u0015\u0011\u0005\r\u0002\u0011!E2mCjTHj\\1e\r&dW\rV=qKV\tA\u0006\r\u0002.oA\u0019afM\u001b\u000e\u0003=R!\u0001M\u0019\u0002\t1\fgn\u001a\u0006\u0002e\u0005!!.\u0019<b\u0013\t!tFA\u0003DY\u0006\u001c8\u000f\u0005\u00027o1\u0001A!\u0003\u001d\u0003\u0003\u0003\u0005\tQ!\u0001:\u0005\ty\u0004'\u0005\u0002;\u0001B\u00111HP\u0007\u0002y)\tQ(A\u0003tG\u0006d\u0017-\u0003\u0002@y\t9aj\u001c;iS:<\u0007C\u0001\u0018B\u0013\t\u0011uF\u0001\u0004PE*,7\r^\u0001\u0013G2\f'P\u001f'pC\u0012$\u0016M\u00197f\t\u0016\u001c8-F\u0001Fa\t1\u0005\nE\u0002/g\u001d\u0003\"A\u000e%\u0005\u0013a\u001a\u0011\u0011!A\u0001\u0006\u0003I\u0014!F2mCjT\b+\u0019:uSRLwN\u001c#fi\u0006LGn]\u000b\u0002\u0017B\u0019Aj\u0015\u001e\u000f\u00055\u000b\u0006C\u0001(=\u001b\u0005y%B\u0001)(\u0003\u0019a$o\\8u}%\u0011!\u000bP\u0001\u0007!J,G-\u001a4\n\u0005Q\"&B\u0001*=\u0003!9\u0018\u000e\u001c3dCJ$W#A,\u0011\u00051C\u0016BA-U\u0005\u0019\u0019FO]5oO\u0006\u0001\u0012\r\u001c;feR\u000b'\r\\3NKRDw\u000eZ\u000b\u00029B\u0011Q\fY\u0007\u0002=*\u0011qlL\u0001\be\u00164G.Z2u\u0013\t\tgL\u0001\u0004NKRDw\u000eZ\u0001\u0010Y>\fG\rV1cY\u0016lU\r\u001e5pI\u0006\u0019\u0012\r\u001a3QCJ$\u0018\u000e^5p]NlU\r\u001e5pI\u0006)\u0012\r\u001c;feB\u000b'\u000f^5uS>t7/T3uQ>$\u0017a\u00057pC\u0012\u0004\u0016M\u001d;ji&|g.T3uQ>$\u0017a\u00077pC\u0012$\u0015P\\1nS\u000e\u0004\u0016M\u001d;ji&|gn]'fi\"|G-A\u000bsK:\fW.\u001a)beRLG/[8o\u001b\u0016$\bn\u001c3\u0002\u0015\u0005dG/\u001a:UC\ndW\r\u0006\u0003k[f\\\bCA\u001el\u0013\taGH\u0001\u0003V]&$\b\"\u0002\r\u000e\u0001\u0004q\u0007CA8x\u001b\u0005\u0001(BA9s\u0003!iW\r^1eCR\f'BA:u\u0003\t\tHN\u0003\u0002\u0019k*\u0011a/H\u0001\u0007Q\u0006$wn\u001c9\n\u0005a\u0004(\u0001\u0002%jm\u0016DQA_\u0007A\u0002]\u000b\u0011\u0002^1cY\u0016t\u0015-\\3\t\u000bql\u0001\u0019A?\u0002\u000bQ\f'\r\\3\u0011\u0005=t\u0018BA@q\u0005\u0015!\u0016M\u00197f\u0003%aw.\u00193UC\ndW\rF\u0006k\u0003\u000b\t9!a\u0006\u0002\u001a\u0005\r\u0002\"\u0002\r\u000f\u0001\u0004q\u0007bBA\u0005\u001d\u0001\u0007\u00111B\u0001\tY>\fG\rU1uQB!\u0011QBA\n\u001b\t\tyAC\u0002\u0002\u0012U\f!AZ:\n\t\u0005U\u0011q\u0002\u0002\u0005!\u0006$\b\u000eC\u0003{\u001d\u0001\u0007q\u000bC\u0004\u0002\u001c9\u0001\r!!\b\u0002\u000fI,\u0007\u000f\\1dKB\u00191(a\b\n\u0007\u0005\u0005BHA\u0004C_>dW-\u00198\t\u000f\u0005\u0015b\u00021\u0001\u0002\u001e\u0005Q\u0011n]*sG2{7-\u00197\u0002\u001f\u0005dG/\u001a:QCJ$\u0018\u000e^5p]N$rA[A\u0016\u0003[\ty\u0003C\u0003\u0019\u001f\u0001\u0007a\u000eC\u0003{\u001f\u0001\u0007q\u000bC\u0004\u00022=\u0001\r!a\r\u0002\u00119,w\u000fU1siN\u0004b!!\u000e\u0002<\u0005}RBAA\u001c\u0015\r\tI$M\u0001\u0005kRLG.\u0003\u0003\u0002>\u0005]\"\u0001\u0002'jgR\u00042a\\A!\u0013\r\t\u0019\u0005\u001d\u0002\n!\u0006\u0014H/\u001b;j_:\f\u0001c\u0019:fCR,\u0007+\u0019:uSRLwN\\:\u0015\u0013)\fI%a\u0013\u0002N\u0005M\u0004\"\u0002\r\u0011\u0001\u0004q\u0007\"\u0002?\u0011\u0001\u0004i\bbBA(!\u0001\u0007\u0011\u0011K\u0001\u0006a\u0006\u0014Ho\u001d\t\u0007\u0003'\ni&a\u0019\u000f\t\u0005U\u0013\u0011\f\b\u0004\u001d\u0006]\u0013\"A\u001f\n\u0007\u0005mC(A\u0004qC\u000e\\\u0017mZ3\n\t\u0005}\u0013\u0011\r\u0002\u0004'\u0016\f(bAA.yA!\u0011QMA8\u001b\t\t9G\u0003\u0003\u0002j\u0005-\u0014aB2bi\u0006dwn\u001a\u0006\u0004\u0003[J\u0012\u0001C2bi\u0006d\u0017p\u001d;\n\t\u0005E\u0014q\r\u0002\u0016\u0007\u0006$\u0018\r\\8h)\u0006\u0014G.\u001a)beRLG/[8o\u0011\u001d\t)\b\u0005a\u0001\u0003;\ta\"[4o_J,\u0017JZ#ySN$8/A\u0007m_\u0006$\u0007+\u0019:uSRLwN\u001c\u000b\u0012U\u0006m\u0014QPA@\u0003\u0003\u000bY)!$\u0002\u0012\u0006U\u0005\"\u0002\r\u0012\u0001\u0004q\u0007bBA\u0005#\u0001\u0007\u00111\u0002\u0005\u0006uF\u0001\ra\u0016\u0005\b\u0003\u0007\u000b\u0002\u0019AAC\u0003!\u0001\u0018M\u001d;Ta\u0016\u001c\u0007CBA\u001b\u0003\u000f;v+\u0003\u0003\u0002\n\u0006]\"aA'ba\"9\u00111D\tA\u0002\u0005u\u0001bBAH#\u0001\u0007\u0011QD\u0001\u0012S:DWM]5u)\u0006\u0014G.Z*qK\u000e\u001c\bbBAJ#\u0001\u0007\u0011QD\u0001\u0016SN\u001c6.Z<fIN#xN]3BgN+(\rZ5s\u0011\u001d\t)#\u0005a\u0001\u0003;\tQ\u0003\\8bI\u0012Kh.Y7jGB\u000b'\u000f^5uS>t7\u000fF\bk\u00037\u000bi*a(\u0002\"\u0006\r\u0016QUAX\u0011\u0015A\"\u00031\u0001o\u0011\u001d\tIA\u0005a\u0001\u0003\u0017AQA\u001f\nA\u0002]Cq!a!\u0013\u0001\u0004\t)\tC\u0004\u0002\u001cI\u0001\r!!\b\t\u000f\u0005\u001d&\u00031\u0001\u0002*\u0006)a.^7E!B\u00191(a+\n\u0007\u00055FHA\u0002J]RDa!!-\u0013\u0001\u0004i\u0018!\u00035jm\u0016$\u0016M\u00197f\u0003=\u0011XM\\1nKB\u000b'\u000f^5uS>tG#\u00036\u00028\u0006e\u00161XA`\u0011\u0015A2\u00031\u0001o\u0011\u0015a8\u00031\u0001~\u0011\u001d\til\u0005a\u0001\u0003\u000b\u000b1b\u001c7e!\u0006\u0014Ho\u00159fG\"9\u0011\u0011Y\nA\u0002\u0005}\u0012a\u00028foB\u000b'\u000f\u001e"
)
public class Shim_v4_0 extends Shim_v3_1 {
   private Class clazzLoadFileType;
   private Class clazzLoadTableDesc;
   private Class clazzPartitionDetails;
   private String wildcard;
   private Method alterTableMethod;
   private Method loadTableMethod;
   private Method addPartitionsMethod;
   private Method alterPartitionsMethod;
   private Method loadPartitionMethod;
   private Method loadDynamicPartitionsMethod;
   private Method renamePartitionMethod;
   private volatile int bitmap$0;

   private Class clazzLoadFileType$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.clazzLoadFileType = this.getClass().getClassLoader().loadClass("org.apache.hadoop.hive.ql.plan.LoadTableDesc$LoadFileType");
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.clazzLoadFileType;
   }

   private Class clazzLoadFileType() {
      return (this.bitmap$0 & 1) == 0 ? this.clazzLoadFileType$lzycompute() : this.clazzLoadFileType;
   }

   private Class clazzLoadTableDesc$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.clazzLoadTableDesc = this.getClass().getClassLoader().loadClass("org.apache.hadoop.hive.ql.plan.LoadTableDesc");
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.clazzLoadTableDesc;
   }

   private Class clazzLoadTableDesc() {
      return (this.bitmap$0 & 2) == 0 ? this.clazzLoadTableDesc$lzycompute() : this.clazzLoadTableDesc;
   }

   private Class clazzPartitionDetails$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.clazzPartitionDetails = .MODULE$.classForName("org.apache.hadoop.hive.ql.exec.Utilities$PartitionDesc", .MODULE$.classForName$default$2(), .MODULE$.classForName$default$3());
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.clazzPartitionDetails;
   }

   private Class clazzPartitionDetails() {
      return (this.bitmap$0 & 4) == 0 ? this.clazzPartitionDetails$lzycompute() : this.clazzPartitionDetails;
   }

   private String wildcard$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            this.wildcard = "%";
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.wildcard;
   }

   public String wildcard() {
      return (this.bitmap$0 & 8) == 0 ? this.wildcard$lzycompute() : this.wildcard;
   }

   private Method alterTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.alterTableMethod = this.findMethod(Hive.class, "alterTable", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{String.class, Table.class, EnvironmentContext.class, Boolean.TYPE})));
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterTableMethod;
   }

   private Method alterTableMethod() {
      return (this.bitmap$0 & 16) == 0 ? this.alterTableMethod$lzycompute() : this.alterTableMethod;
   }

   private Method loadTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.loadTableMethod = this.findMethod(Hive.class, "loadTable", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, this.clazzLoadFileType(), Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Long.class, Integer.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadTableMethod;
   }

   private Method loadTableMethod() {
      return (this.bitmap$0 & 32) == 0 ? this.loadTableMethod$lzycompute() : this.loadTableMethod;
   }

   private Method addPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.addPartitionsMethod = this.findMethod(Hive.class, "addPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{List.class, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.addPartitionsMethod;
   }

   private Method addPartitionsMethod() {
      return (this.bitmap$0 & 64) == 0 ? this.addPartitionsMethod$lzycompute() : this.addPartitionsMethod;
   }

   private Method alterPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.alterPartitionsMethod = this.findMethod(Hive.class, "alterPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{String.class, List.class, EnvironmentContext.class, Boolean.TYPE})));
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.alterPartitionsMethod;
   }

   private Method alterPartitionsMethod() {
      return (this.bitmap$0 & 128) == 0 ? this.alterPartitionsMethod$lzycompute() : this.alterPartitionsMethod;
   }

   private Method loadPartitionMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.loadPartitionMethod = this.findMethod(Hive.class, "loadPartition", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, Table.class, Map.class, this.clazzLoadFileType(), Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Long.class, Integer.TYPE, Boolean.TYPE, Boolean.TYPE})));
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadPartitionMethod;
   }

   private Method loadPartitionMethod() {
      return (this.bitmap$0 & 256) == 0 ? this.loadPartitionMethod$lzycompute() : this.loadPartitionMethod;
   }

   private Method loadDynamicPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.loadDynamicPartitionsMethod = this.findMethod(Hive.class, "loadDynamicPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{this.clazzLoadTableDesc(), Integer.TYPE, Boolean.TYPE, Long.TYPE, Integer.TYPE, Boolean.TYPE, AcidUtils.Operation.class, Map.class})));
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadDynamicPartitionsMethod;
   }

   private Method loadDynamicPartitionsMethod() {
      return (this.bitmap$0 & 512) == 0 ? this.loadDynamicPartitionsMethod$lzycompute() : this.loadDynamicPartitionsMethod;
   }

   private Method renamePartitionMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            this.renamePartitionMethod = this.findMethod(Hive.class, "renamePartition", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Table.class, Map.class, Partition.class, Long.TYPE})));
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.renamePartitionMethod;
   }

   private Method renamePartitionMethod() {
      return (this.bitmap$0 & 1024) == 0 ? this.renamePartitionMethod$lzycompute() : this.renamePartitionMethod;
   }

   public void alterTable(final Hive hive, final String tableName, final Table table) {
      this.recordHiveCall();
      boolean transactional = false;
      Method var10000 = this.alterTableMethod();
      Object[] var10002 = new Object[]{tableName, table, null, null};
      this.environmentContextInAlterTable();
      var10002[2] = null;
      var10002[3] = scala.Predef..MODULE$.boolean2Boolean(transactional);
      var10000.invoke(hive, var10002);
   }

   public void loadTable(final Hive hive, final Path loadPath, final String tableName, final boolean replace, final boolean isSrcLocal) {
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$18) -> BoxesRunTime.boxToBoolean($anonfun$loadTable$3(x$18))) : scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$19) -> BoxesRunTime.boxToBoolean($anonfun$loadTable$4(x$19)));
      scala.Predef..MODULE$.assert(loadFileType.isDefined());
      this.recordHiveCall();
      boolean resetStatistics = false;
      boolean isDirectInsert = false;
      this.loadTableMethod().invoke(hive, loadPath, tableName, loadFileType.get(), scala.Predef..MODULE$.boolean2Boolean(isSrcLocal), this.isSkewedStoreAsSubdir(), this.isAcidIUDoperation(), BoxesRunTime.boxToBoolean(resetStatistics), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), scala.Predef..MODULE$.boolean2Boolean(replace), scala.Predef..MODULE$.boolean2Boolean(isDirectInsert));
   }

   public void alterPartitions(final Hive hive, final String tableName, final List newParts) {
      this.recordHiveCall();
      Boolean transactional = scala.Predef..MODULE$.boolean2Boolean(false);
      Method var10000 = this.alterPartitionsMethod();
      Object[] var10002 = new Object[]{tableName, newParts, null, null};
      this.environmentContextInAlterTable();
      var10002[2] = null;
      var10002[3] = transactional;
      var10000.invoke(hive, var10002);
   }

   public void createPartitions(final Hive hive, final Table table, final Seq parts, final boolean ignoreIfExists) {
      List partitions = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava((scala.collection.Seq)parts.map((x$20) -> HiveClientImpl$.MODULE$.toHivePartition(x$20, table).getTPartition())).asJava();
      this.recordHiveCall();
      boolean needResults = false;
      this.addPartitionsMethod().invoke(hive, partitions, BoxesRunTime.boxToBoolean(ignoreIfExists), scala.Predef..MODULE$.boolean2Boolean(needResults));
   }

   public void loadPartition(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSkewedStoreAsSubdir, final boolean isSrcLocal) {
      this.recordHiveCall();
      Table table = hive.getTable(tableName);
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$21) -> BoxesRunTime.boxToBoolean($anonfun$loadPartition$3(x$21))) : scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$22) -> BoxesRunTime.boxToBoolean($anonfun$loadPartition$4(x$22)));
      scala.Predef..MODULE$.assert(loadFileType.isDefined());
      Boolean inheritLocation = scala.Predef..MODULE$.boolean2Boolean(false);
      Boolean isDirectInsert = scala.Predef..MODULE$.boolean2Boolean(false);
      this.recordHiveCall();
      this.loadPartitionMethod().invoke(hive, loadPath, table, partSpec, loadFileType.get(), scala.Predef..MODULE$.boolean2Boolean(inheritTableSpecs), inheritLocation, scala.Predef..MODULE$.boolean2Boolean(isSkewedStoreAsSubdir), scala.Predef..MODULE$.boolean2Boolean(isSrcLocal), this.isAcid(), this.hasFollowingStatsTask(), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), scala.Predef..MODULE$.boolean2Boolean(replace), isDirectInsert);
   }

   public void loadDynamicPartitions(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final int numDP, final Table hiveTable) {
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$23) -> BoxesRunTime.boxToBoolean($anonfun$loadDynamicPartitions$3(x$23))) : scala.collection.ArrayOps..MODULE$.find$extension(scala.Predef..MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$24) -> BoxesRunTime.boxToBoolean($anonfun$loadDynamicPartitions$4(x$24)));
      scala.Predef..MODULE$.assert(loadFileType.isDefined());
      Boolean useAppendForLoad = scala.Predef..MODULE$.boolean2Boolean(false);
      Object loadTableDesc = this.clazzLoadTableDesc().getConstructor(Path.class, Table.class, Boolean.TYPE, Boolean.TYPE, Map.class).newInstance(loadPath, hiveTable, scala.Predef..MODULE$.boolean2Boolean(replace), useAppendForLoad, partSpec);
      DynamicPartitionCtx ctx = new DynamicPartitionCtx();
      ctx.setRootPath(loadPath);
      ctx.setNumDPCols(numDP);
      ctx.setPartSpec(partSpec);
      Method fullDPSpecs = Utilities.class.getMethod("getFullDPSpecs", Configuration.class, DynamicPartitionCtx.class, Map.class);
      this.recordHiveCall();
      Boolean resetPartitionStats = scala.Predef..MODULE$.boolean2Boolean(false);
      this.loadDynamicPartitionsMethod().invoke(hive, loadTableDesc, this.listBucketingLevel(), this.isAcid(), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), resetPartitionStats, Operation.NOT_ACID, fullDPSpecs.invoke((Object)null, hive.getConf(), ctx, null));
   }

   public void renamePartition(final Hive hive, final Table table, final Map oldPartSpec, final Partition newPart) {
      this.recordHiveCall();
      this.renamePartitionMethod().invoke(hive, table, oldPartSpec, newPart, this.writeIdInLoadTableOrPartition());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadTable$3(final Object x$18) {
      return x$18.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadTable$4(final Object x$19) {
      return x$19.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadPartition$3(final Object x$21) {
      return x$21.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadPartition$4(final Object x$22) {
      return x$22.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadDynamicPartitions$3(final Object x$23) {
      return x$23.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadDynamicPartitions$4(final Object x$24) {
      return x$24.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
