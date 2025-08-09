package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.FileUtils;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.hive.ql.plan.FileSinkDesc;
import org.apache.hadoop.hive.ql.plan.TableDesc;
import org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe;
import org.apache.spark.SparkException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.plans.logical.CTEInChildren;
import org.apache.spark.sql.catalyst.plans.logical.Command;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.Statistics;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.catalyst.trees.UnaryLike;
import org.apache.spark.sql.execution.SparkPlan;
import org.apache.spark.sql.execution.command.DataWritingCommand;
import org.apache.spark.sql.execution.datasources.BasicWriteJobStatsTracker;
import org.apache.spark.sql.execution.datasources.FileFormat;
import org.apache.spark.sql.hive.client.HiveClientImpl$;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015a\u0001\u0002\u0012$\u0001BB\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\t3\u0002\u0011\t\u0012)A\u0005-\"A!\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005c\u0001\tE\t\u0015!\u0003]\u0011!\u0019\u0007A!f\u0001\n\u0003!\u0007\u0002C3\u0001\u0005#\u0005\u000b\u0011B\u0019\t\u0011\u0019\u0004!Q3A\u0005\u0002UC\u0001b\u001a\u0001\u0003\u0012\u0003\u0006IA\u0016\u0005\tQ\u0002\u0011)\u001a!C\u0001S\"AQ\u000f\u0001B\tB\u0003%!\u000eC\u0003w\u0001\u0011\u0005q\u000fC\u0003\u007f\u0001\u0011\u0005s\u0010C\u0004\u0002*\u0001!\t&a\u000b\t\u0013\u0005E\u0002!!A\u0005\u0002\u0005M\u0002\"CA \u0001E\u0005I\u0011AA!\u0011%\t9\u0006AI\u0001\n\u0003\tI\u0006C\u0005\u0002^\u0001\t\n\u0011\"\u0001\u0002`!I\u00111\r\u0001\u0012\u0002\u0013\u0005\u0011\u0011\t\u0005\n\u0003K\u0002\u0011\u0013!C\u0001\u0003OB\u0011\"a\u001b\u0001\u0003\u0003%\t%!\u001c\t\u0013\u0005u\u0004!!A\u0005\u0002\u0005}\u0004\"CAD\u0001\u0005\u0005I\u0011AAE\u0011%\t)\nAA\u0001\n\u0003\n9\nC\u0005\u0002&\u0002\t\t\u0011\"\u0001\u0002(\"I\u00111\u0016\u0001\u0002\u0002\u0013\u0005\u0013Q\u0016\u0005\n\u0003c\u0003\u0011\u0011!C!\u0003g;\u0011\"a.$\u0003\u0003E\t!!/\u0007\u0011\t\u001a\u0013\u0011!E\u0001\u0003wCaA\u001e\u000f\u0005\u0002\u0005M\u0007\"CAk9\u0005\u0005IQIAl\u0011%\tI\u000eHA\u0001\n\u0003\u000bY\u000eC\u0005\u0002hr\t\t\u0011\"!\u0002j\"I\u00111 \u000f\u0002\u0002\u0013%\u0011Q \u0002\u0019\u0013:\u001cXM\u001d;J]R|\u0007*\u001b<f\t&\u00148i\\7nC:$'B\u0001\u0013&\u0003%)\u00070Z2vi&|gN\u0003\u0002'O\u0005!\u0001.\u001b<f\u0015\tA\u0013&A\u0002tc2T!AK\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u00051j\u0013AB1qC\u000eDWMC\u0001/\u0003\ry'oZ\u0002\u0001'\u0019\u0001\u0011gO C\u0011B\u0011!'O\u0007\u0002g)\u0011A'N\u0001\bY><\u0017nY1m\u0015\t1t'A\u0003qY\u0006t7O\u0003\u00029O\u0005A1-\u0019;bYf\u001cH/\u0003\u0002;g\tYAj\\4jG\u0006d\u0007\u000b\\1o!\taT(D\u0001$\u0013\tq4E\u0001\bTCZ,\u0017i\u001d%jm\u00164\u0015\u000e\\3\u0011\u0005q\u0002\u0015BA!$\u0005E1\u0016g\u0016:ji\u0016\u001c\b*\u001b<f+RLGn\u001d\t\u0003\u0007\u001ak\u0011\u0001\u0012\u0006\u0002\u000b\u0006)1oY1mC&\u0011q\t\u0012\u0002\b!J|G-^2u!\tI\u0015K\u0004\u0002K\u001f:\u00111JT\u0007\u0002\u0019*\u0011QjL\u0001\u0007yI|w\u000e\u001e \n\u0003\u0015K!\u0001\u0015#\u0002\u000fA\f7m[1hK&\u0011!k\u0015\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003!\u0012\u000bq![:M_\u000e\fG.F\u0001W!\t\u0019u+\u0003\u0002Y\t\n9!i\\8mK\u0006t\u0017\u0001C5t\u0019>\u001c\u0017\r\u001c\u0011\u0002\u000fM$xN]1hKV\tA\f\u0005\u0002^A6\taL\u0003\u0002`o\u000591-\u0019;bY><\u0017BA1_\u0005Q\u0019\u0015\r^1m_\u001e\u001cFo\u001c:bO\u00164uN]7bi\u0006A1\u000f^8sC\u001e,\u0007%A\u0003rk\u0016\u0014\u00180F\u00012\u0003\u0019\tX/\u001a:zA\u0005IqN^3soJLG/Z\u0001\u000b_Z,'o\u001e:ji\u0016\u0004\u0013!E8viB,HoQ8mk6tg*Y7fgV\t!\u000eE\u0002JW6L!\u0001\\*\u0003\u0007M+\u0017\u000f\u0005\u0002oe:\u0011q\u000e\u001d\t\u0003\u0017\u0012K!!\u001d#\u0002\rA\u0013X\rZ3g\u0013\t\u0019HO\u0001\u0004TiJLgn\u001a\u0006\u0003c\u0012\u000b!c\\;uaV$8i\u001c7v[:t\u0015-\\3tA\u00051A(\u001b8jiz\"b\u0001_={wrl\bC\u0001\u001f\u0001\u0011\u0015!6\u00021\u0001W\u0011\u0015Q6\u00021\u0001]\u0011\u0015\u00197\u00021\u00012\u0011\u001517\u00021\u0001W\u0011\u0015A7\u00021\u0001k\u0003\r\u0011XO\u001c\u000b\u0007\u0003\u0003\tY!a\u0007\u0011\t%[\u00171\u0001\t\u0005\u0003\u000b\t9!D\u0001(\u0013\r\tIa\n\u0002\u0004%><\bbBA\u0007\u0019\u0001\u0007\u0011qB\u0001\rgB\f'o[*fgNLwN\u001c\t\u0005\u0003#\t9\"\u0004\u0002\u0002\u0014)\u0019\u0011QC\u0014\u0002\u000f\rd\u0017m]:jG&!\u0011\u0011DA\n\u00051\u0019\u0006/\u0019:l'\u0016\u001c8/[8o\u0011\u001d\ti\u0002\u0004a\u0001\u0003?\tQa\u00195jY\u0012\u0004B!!\t\u0002&5\u0011\u00111\u0005\u0006\u0003I\u001dJA!a\n\u0002$\tI1\u000b]1sWBc\u0017M\\\u0001\u0015o&$\bNT3x\u0007\"LG\u000eZ%oi\u0016\u0014h.\u00197\u0015\u0007a\fi\u0003\u0003\u0004\u000205\u0001\r!M\u0001\t]\u0016<8\t[5mI\u0006!1m\u001c9z)-A\u0018QGA\u001c\u0003s\tY$!\u0010\t\u000fQs\u0001\u0013!a\u0001-\"9!L\u0004I\u0001\u0002\u0004a\u0006bB2\u000f!\u0003\u0005\r!\r\u0005\bM:\u0001\n\u00111\u0001W\u0011\u001dAg\u0002%AA\u0002)\fabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002D)\u001aa+!\u0012,\u0005\u0005\u001d\u0003\u0003BA%\u0003'j!!a\u0013\u000b\t\u00055\u0013qJ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0015E\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003+\nYEA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0002\\)\u001aA,!\u0012\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011\u0011\u0011\r\u0016\u0004c\u0005\u0015\u0013AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\tIGK\u0002k\u0003\u000b\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA8!\u0011\t\t(a\u001f\u000e\u0005\u0005M$\u0002BA;\u0003o\nA\u0001\\1oO*\u0011\u0011\u0011P\u0001\u0005U\u00064\u0018-C\u0002t\u0003g\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!!\u0011\u0007\r\u000b\u0019)C\u0002\u0002\u0006\u0012\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a#\u0002\u0012B\u00191)!$\n\u0007\u0005=EIA\u0002B]fD\u0011\"a%\u0017\u0003\u0003\u0005\r!!!\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\n\u0005\u0004\u0002\u001c\u0006\u0005\u00161R\u0007\u0003\u0003;S1!a(E\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003G\u000biJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGc\u0001,\u0002*\"I\u00111\u0013\r\u0002\u0002\u0003\u0007\u00111R\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002p\u0005=\u0006\"CAJ3\u0005\u0005\t\u0019AAA\u0003\u0019)\u0017/^1mgR\u0019a+!.\t\u0013\u0005M%$!AA\u0002\u0005-\u0015\u0001G%og\u0016\u0014H/\u00138u_\"Kg/\u001a#je\u000e{W.\\1oIB\u0011A\bH\n\u00069\u0005u\u0016\u0011\u001a\t\u000b\u0003\u007f\u000b)M\u0016/2-*DXBAAa\u0015\r\t\u0019\rR\u0001\beVtG/[7f\u0013\u0011\t9-!1\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0005\u0003\u0002L\u0006EWBAAg\u0015\u0011\ty-a\u001e\u0002\u0005%|\u0017b\u0001*\u0002NR\u0011\u0011\u0011X\u0001\ti>\u001cFO]5oOR\u0011\u0011qN\u0001\u0006CB\u0004H.\u001f\u000b\fq\u0006u\u0017q\\Aq\u0003G\f)\u000fC\u0003U?\u0001\u0007a\u000bC\u0003[?\u0001\u0007A\fC\u0003d?\u0001\u0007\u0011\u0007C\u0003g?\u0001\u0007a\u000bC\u0003i?\u0001\u0007!.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0018q\u001f\t\u0006\u0007\u00065\u0018\u0011_\u0005\u0004\u0003_$%AB(qi&|g\u000e\u0005\u0005D\u0003g4F,\r,k\u0013\r\t)\u0010\u0012\u0002\u0007)V\u0004H.Z\u001b\t\u0011\u0005e\b%!AA\u0002a\f1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ty\u0010\u0005\u0003\u0002r\t\u0005\u0011\u0002\u0002B\u0002\u0003g\u0012aa\u00142kK\u000e$\b"
)
public class InsertIntoHiveDirCommand extends LogicalPlan implements SaveAsHiveFile, V1WritesHiveUtils, Serializable {
   private final boolean isLocal;
   private final CatalogStorageFormat storage;
   private final LogicalPlan query;
   private final boolean overwrite;
   private final Seq outputColumnNames;
   private Map metrics;
   private transient Seq children;
   private Seq nodePatterns;
   private volatile boolean bitmap$0;
   private transient volatile boolean bitmap$trans$0;

   public static Option unapply(final InsertIntoHiveDirCommand x$0) {
      return InsertIntoHiveDirCommand$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return InsertIntoHiveDirCommand$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return InsertIntoHiveDirCommand$.MODULE$.curried();
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

   public Set saveAsHiveFile(final org.apache.spark.sql.classic.SparkSession sparkSession, final SparkPlan plan, final Configuration hadoopConf, final FileFormat fileFormat, final String outputLocation, final Map customPartitionLocations, final Seq partitionAttributes, final Option bucketSpec, final Map options) {
      return SaveAsHiveFile.saveAsHiveFile$(this, sparkSession, plan, hadoopConf, fileFormat, outputLocation, customPartitionLocations, partitionAttributes, bucketSpec, options);
   }

   public Map saveAsHiveFile$default$6() {
      return SaveAsHiveFile.saveAsHiveFile$default$6$(this);
   }

   public Seq saveAsHiveFile$default$7() {
      return SaveAsHiveFile.saveAsHiveFile$default$7$(this);
   }

   public Option saveAsHiveFile$default$8() {
      return SaveAsHiveFile.saveAsHiveFile$default$8$(this);
   }

   public Map saveAsHiveFile$default$9() {
      return SaveAsHiveFile.saveAsHiveFile$default$9$(this);
   }

   public final LogicalPlan child() {
      return DataWritingCommand.child$(this);
   }

   public Seq outputColumns() {
      return DataWritingCommand.outputColumns$(this);
   }

   public BasicWriteJobStatsTracker basicWriteJobStatsTracker(final Configuration hadoopConf) {
      return DataWritingCommand.basicWriteJobStatsTracker$(this, hadoopConf);
   }

   public LogicalPlan withCTEDefs(final Seq cteDefs) {
      return CTEInChildren.withCTEDefs$(this, cteDefs);
   }

   public final TreeNode mapChildren(final Function1 f) {
      return UnaryLike.mapChildren$(this, f);
   }

   public final TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
      return UnaryLike.withNewChildrenInternal$(this, newChildren);
   }

   public Seq output() {
      return Command.output$(this);
   }

   public AttributeSet producedAttributes() {
      return Command.producedAttributes$(this);
   }

   public Statistics stats() {
      return Command.stats$(this);
   }

   private Map metrics$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.metrics = DataWritingCommand.metrics$(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.metrics;
   }

   public Map metrics() {
      return !this.bitmap$0 ? this.metrics$lzycompute() : this.metrics;
   }

   private Seq children$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.children = UnaryLike.children$(this);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.children;
   }

   public final Seq children() {
      return !this.bitmap$trans$0 ? this.children$lzycompute() : this.children;
   }

   public final Seq nodePatterns() {
      return this.nodePatterns;
   }

   public final void org$apache$spark$sql$catalyst$plans$logical$Command$_setter_$nodePatterns_$eq(final Seq x$1) {
      this.nodePatterns = x$1;
   }

   public boolean isLocal() {
      return this.isLocal;
   }

   public CatalogStorageFormat storage() {
      return this.storage;
   }

   public LogicalPlan query() {
      return this.query;
   }

   public boolean overwrite() {
      return this.overwrite;
   }

   public Seq outputColumnNames() {
      return this.outputColumnNames;
   }

   public Seq run(final org.apache.spark.sql.classic.SparkSession sparkSession, final SparkPlan child) {
      .MODULE$.assert(this.storage().locationUri().nonEmpty());
      org.apache.spark.sql.util.SchemaUtils..MODULE$.checkColumnNameDuplication(this.outputColumnNames(), sparkSession.sessionState().conf().caseSensitiveAnalysis());
      TableIdentifier x$1 = org.apache.spark.sql.catalyst.TableIdentifier..MODULE$.apply(((URI)this.storage().locationUri().get()).toString(), new Some("default"));
      Some x$2 = new Some(org.apache.spark.sql.execution.command.DDLUtils..MODULE$.HIVE_PROVIDER());
      CatalogTableType x$3 = org.apache.spark.sql.catalyst.catalog.CatalogTableType..MODULE$.VIEW();
      CatalogStorageFormat x$4 = this.storage();
      StructType x$5 = org.apache.spark.sql.catalyst.expressions.package..MODULE$.AttributeSeq(this.outputColumns()).toStructType();
      Seq x$6 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$6();
      Option x$7 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$7();
      String x$8 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$8();
      long x$9 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$9();
      long x$10 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$10();
      String x$11 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$11();
      Map x$12 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$12();
      Option x$13 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$13();
      Option x$14 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$14();
      Option x$15 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$15();
      Option x$16 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$16();
      Seq x$17 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$17();
      boolean x$18 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$18();
      boolean x$19 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$19();
      Map x$20 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$20();
      Option x$21 = org.apache.spark.sql.catalyst.catalog.CatalogTable..MODULE$.apply$default$21();
      CatalogTable table = new CatalogTable(x$1, x$3, x$4, x$5, x$2, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21);
      org.apache.spark.sql.execution.command.DDLUtils..MODULE$.checkTableColumns(table);
      Table hiveTable = HiveClientImpl$.MODULE$.toHiveTable(table, HiveClientImpl$.MODULE$.toHiveTable$default$2());
      hiveTable.getMetadata().put("serialization.lib", this.storage().serde().getOrElse(() -> LazySimpleSerDe.class.getName()));
      TableDesc tableDesc = new TableDesc(hiveTable.getInputFormatClass(), hiveTable.getOutputFormatClass(), hiveTable.getMetadata());
      Configuration hadoopConf = sparkSession.sessionState().newHadoopConf();
      Path targetPath = new Path((URI)this.storage().locationUri().get());
      Path qualifiedPath = FileUtils.makeQualified(targetPath, hadoopConf);
      Tuple2 var10000;
      if (this.isLocal()) {
         LocalFileSystem localFileSystem = FileSystem.getLocal(hadoopConf);
         var10000 = new Tuple2(localFileSystem.makeQualified(targetPath), localFileSystem);
      } else {
         FileSystem dfs = qualifiedPath.getFileSystem(hadoopConf);
         var10000 = new Tuple2(qualifiedPath, dfs);
      }

      Tuple2 var34 = var10000;
      if (var34 != null) {
         Path writeToPath = (Path)var34._1();
         FileSystem fs = (FileSystem)var34._2();
         if (writeToPath != null && fs != null) {
            Tuple2 var33 = new Tuple2(writeToPath, fs);
            Path writeToPath = (Path)var33._1();
            FileSystem fs = (FileSystem)var33._2();
            if (!fs.exists(writeToPath)) {
               BoxesRunTime.boxToBoolean(fs.mkdirs(writeToPath));
            } else {
               BoxedUnit var53 = BoxedUnit.UNIT;
            }

            HiveTempPath hiveTempPath = new HiveTempPath(sparkSession, hadoopConf, qualifiedPath);
            Path tmpPath = hiveTempPath.externalTempPath();
            FileSinkDesc fileSinkConf = new FileSinkDesc(tmpPath, tableDesc, false);
            this.setupHadoopConfForCompression(fileSinkConf, hadoopConf, sparkSession);
            hiveTempPath.createTmpPath();

            try {
               this.saveAsHiveFile(sparkSession, child, hadoopConf, new HiveFileFormat(fileSinkConf), tmpPath.toString(), this.saveAsHiveFile$default$6(), this.saveAsHiveFile$default$7(), this.saveAsHiveFile$default$8(), this.saveAsHiveFile$default$9());
               if (this.overwrite() && fs.exists(writeToPath)) {
                  scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])fs.listStatus(writeToPath)), (existFile) -> {
                     $anonfun$run$2(hiveTempPath, fs, existFile);
                     return BoxedUnit.UNIT;
                  });
               }

               FileSystem dfs = tmpPath.getFileSystem(hadoopConf);
               scala.collection.ArrayOps..MODULE$.foreach$extension(.MODULE$.refArrayOps((Object[])dfs.listStatus(tmpPath)), (tmpFile) -> {
                  if (this.isLocal()) {
                     dfs.copyToLocalFile(tmpFile.getPath(), writeToPath);
                     return BoxedUnit.UNIT;
                  } else {
                     return BoxesRunTime.boxToBoolean(dfs.rename(tmpFile.getPath(), writeToPath));
                  }
               });
            } catch (Throwable var51) {
               throw new SparkException("Failed inserting overwrite directory " + this.storage().locationUri().get(), var51);
            } finally {
               hiveTempPath.deleteTmpPath();
            }

            return (Seq)scala.package..MODULE$.Seq().empty();
         }
      }

      throw new MatchError(var34);
   }

   public InsertIntoHiveDirCommand withNewChildInternal(final LogicalPlan newChild) {
      boolean x$2 = this.copy$default$1();
      CatalogStorageFormat x$3 = this.copy$default$2();
      boolean x$4 = this.copy$default$4();
      Seq x$5 = this.copy$default$5();
      return this.copy(x$2, x$3, newChild, x$4, x$5);
   }

   public InsertIntoHiveDirCommand copy(final boolean isLocal, final CatalogStorageFormat storage, final LogicalPlan query, final boolean overwrite, final Seq outputColumnNames) {
      return new InsertIntoHiveDirCommand(isLocal, storage, query, overwrite, outputColumnNames);
   }

   public boolean copy$default$1() {
      return this.isLocal();
   }

   public CatalogStorageFormat copy$default$2() {
      return this.storage();
   }

   public LogicalPlan copy$default$3() {
      return this.query();
   }

   public boolean copy$default$4() {
      return this.overwrite();
   }

   public Seq copy$default$5() {
      return this.outputColumnNames();
   }

   public String productPrefix() {
      return "InsertIntoHiveDirCommand";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.isLocal());
         }
         case 1 -> {
            return this.storage();
         }
         case 2 -> {
            return this.query();
         }
         case 3 -> {
            return BoxesRunTime.boxToBoolean(this.overwrite());
         }
         case 4 -> {
            return this.outputColumnNames();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof InsertIntoHiveDirCommand;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "isLocal";
         }
         case 1 -> {
            return "storage";
         }
         case 2 -> {
            return "query";
         }
         case 3 -> {
            return "overwrite";
         }
         case 4 -> {
            return "outputColumnNames";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof InsertIntoHiveDirCommand) {
               InsertIntoHiveDirCommand var4 = (InsertIntoHiveDirCommand)x$1;
               if (this.isLocal() == var4.isLocal() && this.overwrite() == var4.overwrite()) {
                  label64: {
                     CatalogStorageFormat var10000 = this.storage();
                     CatalogStorageFormat var5 = var4.storage();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label64;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label64;
                     }

                     LogicalPlan var8 = this.query();
                     LogicalPlan var6 = var4.query();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label64;
                        }
                     } else if (!var8.equals(var6)) {
                        break label64;
                     }

                     Seq var9 = this.outputColumnNames();
                     Seq var7 = var4.outputColumnNames();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label64;
                        }
                     } else if (!var9.equals(var7)) {
                        break label64;
                     }

                     if (var4.canEqual(this)) {
                        break label71;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   // $FF: synthetic method
   public static final void $anonfun$run$2(final HiveTempPath hiveTempPath$1, final FileSystem fs$1, final FileStatus existFile) {
      hiveTempPath$1.deleteIfNotStagingDir(existFile.getPath(), fs$1);
   }

   public InsertIntoHiveDirCommand(final boolean isLocal, final CatalogStorageFormat storage, final LogicalPlan query, final boolean overwrite, final Seq outputColumnNames) {
      this.isLocal = isLocal;
      this.storage = storage;
      this.query = query;
      this.overwrite = overwrite;
      this.outputColumnNames = outputColumnNames;
      Command.$init$(this);
      UnaryLike.$init$(this);
      CTEInChildren.$init$(this);
      DataWritingCommand.$init$(this);
      SaveAsHiveFile.$init$(this);
      V1WritesHiveUtils.$init$(this);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
