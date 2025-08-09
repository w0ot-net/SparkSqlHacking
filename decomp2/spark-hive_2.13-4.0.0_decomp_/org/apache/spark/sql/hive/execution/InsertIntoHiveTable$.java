package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.trees.TreeNodeTag;
import org.apache.spark.sql.classic.SparkSession.;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import scala.Option;
import scala.Some;
import scala.Tuple11;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class InsertIntoHiveTable$ implements V1WritesHiveUtils, Serializable {
   public static final InsertIntoHiveTable$ MODULE$ = new InsertIntoHiveTable$();
   private static final TreeNodeTag BY_CTAS;

   static {
      V1WritesHiveUtils.$init$(MODULE$);
      BY_CTAS = new TreeNodeTag("by_ctas");
   }

   public Map getPartitionSpec(final Map partition) {
      return V1WritesHiveUtils.getPartitionSpec$(this, partition);
   }

   public Seq getDynamicPartitionColumns(final CatalogTable table, final Map partition, final LogicalPlan query) {
      return V1WritesHiveUtils.getDynamicPartitionColumns$(this, table, partition, query);
   }

   public Map getOptionsWithHiveBucketWrite(final Option bucketSpec) {
      return V1WritesHiveUtils.getOptionsWithHiveBucketWrite$(this, bucketSpec);
   }

   public void setupHadoopConfForCompression(final FileSinkDesc fileSinkConf, final Configuration hadoopConf, final SparkSession sparkSession) {
      V1WritesHiveUtils.setupHadoopConfForCompression$(this, fileSinkConf, hadoopConf, sparkSession);
   }

   public TreeNodeTag BY_CTAS() {
      return BY_CTAS;
   }

   public InsertIntoHiveTable apply(final CatalogTable table, final Map partition, final LogicalPlan query, final boolean overwrite, final boolean ifPartitionNotExists, final Seq outputColumnNames) {
      org.apache.spark.sql.classic.SparkSession sparkSession = (org.apache.spark.sql.classic.SparkSession).MODULE$.getActiveSession().orNull(scala..less.colon.less..MODULE$.refl());
      Table hiveQlTable = HiveClientImpl$.MODULE$.toHiveTable(table, HiveClientImpl$.MODULE$.toHiveTable$default$2());
      TableDesc tableDesc = new TableDesc(hiveQlTable.getInputFormatClass(), hiveQlTable.getOutputFormatClass(), hiveQlTable.getMetadata());
      Configuration hadoopConf = sparkSession.sessionState().newHadoopConf();
      Path tableLocation = hiveQlTable.getDataLocation();
      HiveTempPath hiveTempPath = new HiveTempPath(sparkSession, hadoopConf, tableLocation);
      FileSinkDesc fileSinkConf = new FileSinkDesc(hiveTempPath.externalTempPath(), tableDesc, false);
      this.setupHadoopConfForCompression(fileSinkConf, hadoopConf, sparkSession);
      FileFormat fileFormat = new HiveFileFormat(fileSinkConf);
      Seq partitionColumns = this.getDynamicPartitionColumns(table, partition, query);
      Option bucketSpec = table.bucketSpec();
      Map options = this.getOptionsWithHiveBucketWrite(bucketSpec);
      return new InsertIntoHiveTable(table, partition, query, overwrite, ifPartitionNotExists, outputColumnNames, partitionColumns, bucketSpec, options, fileFormat, hiveTempPath);
   }

   public InsertIntoHiveTable apply(final CatalogTable table, final Map partition, final LogicalPlan query, final boolean overwrite, final boolean ifPartitionNotExists, final Seq outputColumnNames, final Seq partitionColumns, final Option bucketSpec, final Map options, final FileFormat fileFormat, final HiveTempPath hiveTmpPath) {
      return new InsertIntoHiveTable(table, partition, query, overwrite, ifPartitionNotExists, outputColumnNames, partitionColumns, bucketSpec, options, fileFormat, hiveTmpPath);
   }

   public Option unapply(final InsertIntoHiveTable x$0) {
      return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple11(x$0.table(), x$0.partition(), x$0.query(), BoxesRunTime.boxToBoolean(x$0.overwrite()), BoxesRunTime.boxToBoolean(x$0.ifPartitionNotExists()), x$0.outputColumnNames(), x$0.partitionColumns(), x$0.bucketSpec(), x$0.options(), x$0.fileFormat(), x$0.hiveTmpPath())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InsertIntoHiveTable$.class);
   }

   private InsertIntoHiveTable$() {
   }
}
