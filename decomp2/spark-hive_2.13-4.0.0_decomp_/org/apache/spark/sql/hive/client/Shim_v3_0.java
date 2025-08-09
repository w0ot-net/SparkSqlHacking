package org.apache.spark.sql.hive.client;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.io.AcidUtils;
import org.apache.hadoop.hive.ql.io.AcidUtils.Operation;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.Table;
import scala.Option;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c!B\u0007\u000f\u00019Q\u0002\"B\u0010\u0001\t\u0003\t\u0003\u0002C\u0012\u0001\u0011\u000b\u0007I\u0011\u0003\u0013\t\u00115\u0002\u0001R1A\u0005\u00129B\u0001B\r\u0001\t\u0006\u0004%\tb\r\u0005\to\u0001A)\u0019!C\tg!A\u0001\b\u0001EC\u0002\u0013%\u0011\b\u0003\u0005M\u0001!\u0015\r\u0011\"\u0003N\u0011!!\u0006\u0001#b\u0001\n\u0013i\u0005\u0002C+\u0001\u0011\u000b\u0007I\u0011B'\t\u000bY\u0003A\u0011I,\t\u000f\u0005u\u0001\u0001\"\u0011\u0002 !9\u00111\u0006\u0001\u0005B\u00055\"!C*iS6|foM01\u0015\ty\u0001#\u0001\u0004dY&,g\u000e\u001e\u0006\u0003#I\tA\u0001[5wK*\u00111\u0003F\u0001\u0004gFd'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0014\u0005\u0001Y\u0002C\u0001\u000f\u001e\u001b\u0005q\u0011B\u0001\u0010\u000f\u0005%\u0019\u0006.[7`mJz6'\u0001\u0004=S:LGOP\u0002\u0001)\u0005\u0011\u0003C\u0001\u000f\u0001\u0003II7/Q2jI&+Fi\u001c9fe\u0006$\u0018n\u001c8\u0016\u0003\u0015\u0002\"AJ\u0016\u000e\u0003\u001dR!\u0001K\u0015\u0002\t1\fgn\u001a\u0006\u0002U\u0005!!.\u0019<b\u0013\tasEA\u0004C_>dW-\u00198\u0002;]\u0014\u0018\u000e^3JI&sGj\\1e)\u0006\u0014G.Z(s!\u0006\u0014H/\u001b;j_:,\u0012a\f\t\u0003MAJ!!M\u0014\u0003\t1{gnZ\u0001\u001dgRlG/\u00133J]2{\u0017\r\u001a+bE2,wJ\u001d)beRLG/[8o+\u0005!\u0004C\u0001\u00146\u0013\t1tEA\u0004J]R,w-\u001a:\u0002%1L7\u000f\u001e\"vG.,G/\u001b8h\u0019\u00164X\r\\\u0001\u0012G2\f'P\u001f'pC\u00124\u0015\u000e\\3UsB,W#\u0001\u001e1\u0005m\u0002\u0005c\u0001\u0014=}%\u0011Qh\n\u0002\u0006\u00072\f7o\u001d\t\u0003\u007f\u0001c\u0001\u0001B\u0005B\r\u0005\u0005\t\u0011!B\u0001\u0005\n\u0011q\bM\t\u0003\u0007&\u0003\"\u0001R$\u000e\u0003\u0015S\u0011AR\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0011\u0016\u0013qAT8uQ&tw\r\u0005\u0002'\u0015&\u00111j\n\u0002\u0007\u001f\nTWm\u0019;\u0002'1|\u0017\r\u001a)beRLG/[8o\u001b\u0016$\bn\u001c3\u0016\u00039\u0003\"a\u0014*\u000e\u0003AS!!U\u0014\u0002\u000fI,g\r\\3di&\u00111\u000b\u0015\u0002\u0007\u001b\u0016$\bn\u001c3\u0002\u001f1|\u0017\r\u001a+bE2,W*\u001a;i_\u0012\f1\u0004\\8bI\u0012Kh.Y7jGB\u000b'\u000f^5uS>t7/T3uQ>$\u0017!\u00047pC\u0012\u0004\u0016M\u001d;ji&|g\u000eF\u0007Y7\u001e|G0!\u0003\u0002\u0012\u0005U\u0011\u0011\u0004\t\u0003\tfK!AW#\u0003\tUs\u0017\u000e\u001e\u0005\u0006#)\u0001\r\u0001\u0018\t\u0003;\u0016l\u0011A\u0018\u0006\u0003?\u0002\f\u0001\"\\3uC\u0012\fG/\u0019\u0006\u0003C\n\f!!\u001d7\u000b\u0005E\u0019'B\u00013\u0017\u0003\u0019A\u0017\rZ8pa&\u0011aM\u0018\u0002\u0005\u0011&4X\rC\u0003i\u0015\u0001\u0007\u0011.\u0001\u0005m_\u0006$\u0007+\u0019;i!\tQW.D\u0001l\u0015\ta7-\u0001\u0002gg&\u0011an\u001b\u0002\u0005!\u0006$\b\u000eC\u0003q\u0015\u0001\u0007\u0011/A\u0005uC\ndWMT1nKB\u0011!/\u001f\b\u0003g^\u0004\"\u0001^#\u000e\u0003UT!A\u001e\u0011\u0002\rq\u0012xn\u001c;?\u0013\tAX)\u0001\u0004Qe\u0016$WMZ\u0005\u0003un\u0014aa\u0015;sS:<'B\u0001=F\u0011\u0015i(\u00021\u0001\u007f\u0003!\u0001\u0018M\u001d;Ta\u0016\u001c\u0007#B@\u0002\u0006E\fXBAA\u0001\u0015\r\t\u0019!K\u0001\u0005kRLG.\u0003\u0003\u0002\b\u0005\u0005!aA'ba\"9\u00111\u0002\u0006A\u0002\u00055\u0011a\u0002:fa2\f7-\u001a\t\u0004\t\u0006=\u0011B\u0001\u0017F\u0011\u001d\t\u0019B\u0003a\u0001\u0003\u001b\t\u0011#\u001b8iKJLG\u000fV1cY\u0016\u001c\u0006/Z2t\u0011\u001d\t9B\u0003a\u0001\u0003\u001b\tQ#[:TW\u0016<X\rZ*u_J,\u0017i]*vE\u0012L'\u000fC\u0004\u0002\u001c)\u0001\r!!\u0004\u0002\u0015%\u001c8K]2M_\u000e\fG.A\u0005m_\u0006$G+\u00192mKRY\u0001,!\t\u0002$\u0005\u0015\u0012qEA\u0015\u0011\u0015\t2\u00021\u0001]\u0011\u0015A7\u00021\u0001j\u0011\u0015\u00018\u00021\u0001r\u0011\u001d\tYa\u0003a\u0001\u0003\u001bAq!a\u0007\f\u0001\u0004\ti!A\u000bm_\u0006$G)\u001f8b[&\u001c\u0007+\u0019:uSRLwN\\:\u0015\u001fa\u000by#!\r\u00024\u0005U\u0012qGA\u001d\u0003\u0007BQ!\u0005\u0007A\u0002qCQ\u0001\u001b\u0007A\u0002%DQ\u0001\u001d\u0007A\u0002EDQ! \u0007A\u0002yDq!a\u0003\r\u0001\u0004\ti\u0001C\u0004\u0002<1\u0001\r!!\u0010\u0002\u000b9,X\u000e\u0012)\u0011\u0007\u0011\u000by$C\u0002\u0002B\u0015\u00131!\u00138u\u0011\u001d\t)\u0005\u0004a\u0001\u0003\u000f\n\u0011\u0002[5wKR\u000b'\r\\3\u0011\u0007u\u000bI%C\u0002\u0002Ly\u0013Q\u0001V1cY\u0016\u0004"
)
public class Shim_v3_0 extends Shim_v2_3 {
   private Boolean isAcidIUDoperation;
   private Long writeIdInLoadTableOrPartition;
   private Integer stmtIdInLoadTableOrPartition;
   private Integer listBucketingLevel;
   private Class clazzLoadFileType;
   private Method loadPartitionMethod;
   private Method loadTableMethod;
   private Method loadDynamicPartitionsMethod;
   private volatile byte bitmap$0;

   private Boolean isAcidIUDoperation$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.isAcidIUDoperation = Boolean.FALSE;
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isAcidIUDoperation;
   }

   public Boolean isAcidIUDoperation() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.isAcidIUDoperation$lzycompute() : this.isAcidIUDoperation;
   }

   private Long writeIdInLoadTableOrPartition$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.writeIdInLoadTableOrPartition = .MODULE$.long2Long(0L);
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.writeIdInLoadTableOrPartition;
   }

   public Long writeIdInLoadTableOrPartition() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.writeIdInLoadTableOrPartition$lzycompute() : this.writeIdInLoadTableOrPartition;
   }

   private Integer stmtIdInLoadTableOrPartition$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.stmtIdInLoadTableOrPartition = .MODULE$.int2Integer(0);
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.stmtIdInLoadTableOrPartition;
   }

   public Integer stmtIdInLoadTableOrPartition() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.stmtIdInLoadTableOrPartition$lzycompute() : this.stmtIdInLoadTableOrPartition;
   }

   private Integer listBucketingLevel$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 8) == 0) {
            this.listBucketingLevel = .MODULE$.int2Integer(0);
            this.bitmap$0 = (byte)(this.bitmap$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.listBucketingLevel;
   }

   public Integer listBucketingLevel() {
      return (byte)(this.bitmap$0 & 8) == 0 ? this.listBucketingLevel$lzycompute() : this.listBucketingLevel;
   }

   private Class clazzLoadFileType$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 16) == 0) {
            this.clazzLoadFileType = this.getClass().getClassLoader().loadClass("org.apache.hadoop.hive.ql.plan.LoadTableDesc$LoadFileType");
            this.bitmap$0 = (byte)(this.bitmap$0 | 16);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.clazzLoadFileType;
   }

   private Class clazzLoadFileType() {
      return (byte)(this.bitmap$0 & 16) == 0 ? this.clazzLoadFileType$lzycompute() : this.clazzLoadFileType;
   }

   private Method loadPartitionMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 32) == 0) {
            this.loadPartitionMethod = this.findMethod(Hive.class, "loadPartition", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, Table.class, Map.class, this.clazzLoadFileType(), Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Long.class, Integer.TYPE, Boolean.TYPE})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 32);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadPartitionMethod;
   }

   private Method loadPartitionMethod() {
      return (byte)(this.bitmap$0 & 32) == 0 ? this.loadPartitionMethod$lzycompute() : this.loadPartitionMethod;
   }

   private Method loadTableMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 64) == 0) {
            this.loadTableMethod = this.findMethod(Hive.class, "loadTable", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, this.clazzLoadFileType(), Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Long.class, Integer.TYPE, Boolean.TYPE})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 64);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadTableMethod;
   }

   private Method loadTableMethod() {
      return (byte)(this.bitmap$0 & 64) == 0 ? this.loadTableMethod$lzycompute() : this.loadTableMethod;
   }

   private Method loadDynamicPartitionsMethod$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 128) == 0) {
            this.loadDynamicPartitionsMethod = this.findMethod(Hive.class, "loadDynamicPartitions", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Class[]{Path.class, String.class, Map.class, this.clazzLoadFileType(), Integer.TYPE, Integer.TYPE, Boolean.TYPE, Long.TYPE, Integer.TYPE, Boolean.TYPE, AcidUtils.Operation.class, Boolean.TYPE})));
            this.bitmap$0 = (byte)(this.bitmap$0 | 128);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.loadDynamicPartitionsMethod;
   }

   private Method loadDynamicPartitionsMethod() {
      return (byte)(this.bitmap$0 & 128) == 0 ? this.loadDynamicPartitionsMethod$lzycompute() : this.loadDynamicPartitionsMethod;
   }

   public void loadPartition(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final boolean inheritTableSpecs, final boolean isSkewedStoreAsSubdir, final boolean isSrcLocal) {
      this.recordHiveCall();
      Table table = hive.getTable(tableName);
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$12) -> BoxesRunTime.boxToBoolean($anonfun$loadPartition$1(x$12))) : scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$13) -> BoxesRunTime.boxToBoolean($anonfun$loadPartition$2(x$13)));
      .MODULE$.assert(loadFileType.isDefined());
      this.recordHiveCall();
      this.loadPartitionMethod().invoke(hive, loadPath, table, partSpec, loadFileType.get(), .MODULE$.boolean2Boolean(inheritTableSpecs), .MODULE$.boolean2Boolean(isSkewedStoreAsSubdir), .MODULE$.boolean2Boolean(isSrcLocal), this.isAcid(), this.hasFollowingStatsTask(), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), .MODULE$.boolean2Boolean(replace));
   }

   public void loadTable(final Hive hive, final Path loadPath, final String tableName, final boolean replace, final boolean isSrcLocal) {
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$14) -> BoxesRunTime.boxToBoolean($anonfun$loadTable$1(x$14))) : scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$15) -> BoxesRunTime.boxToBoolean($anonfun$loadTable$2(x$15)));
      .MODULE$.assert(loadFileType.isDefined());
      this.recordHiveCall();
      this.loadTableMethod().invoke(hive, loadPath, tableName, loadFileType.get(), .MODULE$.boolean2Boolean(isSrcLocal), this.isSkewedStoreAsSubdir(), this.isAcidIUDoperation(), this.hasFollowingStatsTask(), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), .MODULE$.boolean2Boolean(replace));
   }

   public void loadDynamicPartitions(final Hive hive, final Path loadPath, final String tableName, final Map partSpec, final boolean replace, final int numDP, final Table hiveTable) {
      Option loadFileType = replace ? scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$16) -> BoxesRunTime.boxToBoolean($anonfun$loadDynamicPartitions$1(x$16))) : scala.collection.ArrayOps..MODULE$.find$extension(.MODULE$.refArrayOps(this.clazzLoadFileType().getEnumConstants()), (x$17) -> BoxesRunTime.boxToBoolean($anonfun$loadDynamicPartitions$2(x$17)));
      .MODULE$.assert(loadFileType.isDefined());
      this.recordHiveCall();
      this.loadDynamicPartitionsMethod().invoke(hive, loadPath, tableName, partSpec, loadFileType.get(), .MODULE$.int2Integer(numDP), this.listBucketingLevel(), this.isAcid(), this.writeIdInLoadTableOrPartition(), this.stmtIdInLoadTableOrPartition(), this.hasFollowingStatsTask(), Operation.NOT_ACID, .MODULE$.boolean2Boolean(replace));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadPartition$1(final Object x$12) {
      return x$12.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadPartition$2(final Object x$13) {
      return x$13.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadTable$1(final Object x$14) {
      return x$14.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadTable$2(final Object x$15) {
      return x$15.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadDynamicPartitions$1(final Object x$16) {
      return x$16.toString().equalsIgnoreCase("REPLACE_ALL");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadDynamicPartitions$2(final Object x$17) {
      return x$17.toString().equalsIgnoreCase("KEEP_EXISTING");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
