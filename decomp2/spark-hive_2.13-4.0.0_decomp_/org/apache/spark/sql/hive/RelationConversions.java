package org.apache.spark.sql.hive;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.util.Locale;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.HiveTableRelation;
import org.apache.spark.sql.catalyst.plans.logical.InsertIntoDir;
import org.apache.spark.sql.catalyst.plans.logical.InsertIntoStatement;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.rules.Rule;
import org.apache.spark.sql.execution.command.DDLUtils;
import org.apache.spark.sql.execution.command.InsertIntoDataSourceDirCommand;
import org.apache.spark.sql.execution.command.DDLUtils.;
import org.apache.spark.sql.execution.datasources.HadoopFsRelation;
import org.apache.spark.sql.execution.datasources.InsertIntoHadoopFsRelationCommand;
import org.apache.spark.sql.execution.datasources.LogicalRelation;
import org.apache.spark.sql.hive.execution.InsertIntoHiveTable;
import org.apache.spark.sql.hive.execution.InsertIntoHiveTable$;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g\u0001B\u0010!\u0001.B\u0001B\u0014\u0001\u0003\u0016\u0004%\ta\u0014\u0005\t)\u0002\u0011\t\u0012)A\u0005!\")Q\u000b\u0001C\u0001-\")\u0011\f\u0001C\u00055\")\u0011\f\u0001C\u0005M\")\u0011\f\u0001C\u0005Y\")!\u000f\u0001C\u0005g\"9Q\u0010\u0001b\u0001\n\u0013q\bbBA\u0003\u0001\u0001\u0006Ia \u0005\b\u0003\u000f\u0001A\u0011IA\u0005\u0011!\ty\u0001\u0001C\u0001A\u0005E\u0001\u0002CA\u000b\u0001\u0011\u0005\u0001%a\u0006\t\u0013\u0005-\u0002!!A\u0005\u0002\u00055\u0002\"CA\u0019\u0001E\u0005I\u0011AA\u001a\u0011%\tI\u0005AA\u0001\n\u0003\nY\u0005C\u0005\u0002\\\u0001\t\t\u0011\"\u0001\u0002^!I\u0011Q\r\u0001\u0002\u0002\u0013\u0005\u0011q\r\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003kB\u0011\"a!\u0001\u0003\u0003%\t!!\"\t\u0013\u0005%\u0005!!A\u0005B\u0005-\u0005\"CAH\u0001\u0005\u0005I\u0011IAI\u0011%\t\u0019\nAA\u0001\n\u0003\n)\nC\u0005\u0002\u0018\u0002\t\t\u0011\"\u0011\u0002\u001a\u001eI\u0011Q\u0014\u0011\u0002\u0002#\u0005\u0011q\u0014\u0004\t?\u0001\n\t\u0011#\u0001\u0002\"\"1Q+\u0007C\u0001\u0003sC\u0011\"a%\u001a\u0003\u0003%)%!&\t\u0013\u0005\u001d\u0011$!A\u0005\u0002\u0006m\u0006\"CA`3\u0005\u0005I\u0011QAa\u0011%\ti-GA\u0001\n\u0013\tyMA\nSK2\fG/[8o\u0007>tg/\u001a:tS>t7O\u0003\u0002\"E\u0005!\u0001.\u001b<f\u0015\t\u0019C%A\u0002tc2T!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\u0002\u0001'\u0011\u0001A\u0006\u0010\"\u0011\u00075\u0012D'D\u0001/\u0015\ty\u0003'A\u0003sk2,7O\u0003\u00022E\u0005A1-\u0019;bYf\u001cH/\u0003\u00024]\t!!+\u001e7f!\t)$(D\u00017\u0015\t9\u0004(A\u0004m_\u001eL7-\u00197\u000b\u0005e\u0002\u0014!\u00029mC:\u001c\u0018BA\u001e7\u0005-aunZ5dC2\u0004F.\u00198\u0011\u0005u\u0002U\"\u0001 \u000b\u0003}\nQa]2bY\u0006L!!\u0011 \u0003\u000fA\u0013x\u000eZ;diB\u00111i\u0013\b\u0003\t&s!!\u0012%\u000e\u0003\u0019S!a\u0012\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005y\u0014B\u0001&?\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001T'\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005)s\u0014AD:fgNLwN\\\"bi\u0006dwnZ\u000b\u0002!B\u0011\u0011KU\u0007\u0002A%\u00111\u000b\t\u0002\u0013\u0011&4XmU3tg&|gnQ1uC2|w-A\btKN\u001c\u0018n\u001c8DCR\fGn\\4!\u0003\u0019a\u0014N\\5u}Q\u0011q\u000b\u0017\t\u0003#\u0002AQAT\u0002A\u0002A\u000bQ\"[:D_:4XM\u001d;jE2,GCA._!\tiD,\u0003\u0002^}\t9!i\\8mK\u0006t\u0007\"B0\u0005\u0001\u0004\u0001\u0017\u0001\u0003:fY\u0006$\u0018n\u001c8\u0011\u0005\u0005$W\"\u00012\u000b\u0005\r\u0004\u0014aB2bi\u0006dwnZ\u0005\u0003K\n\u0014\u0011\u0003S5wKR\u000b'\r\\3SK2\fG/[8o)\tYv\rC\u0003i\u000b\u0001\u0007\u0011.A\u0005uC\ndW-T3uCB\u0011\u0011M[\u0005\u0003W\n\u0014AbQ1uC2|w\rV1cY\u0016$\"aW7\t\u000b94\u0001\u0019A8\u0002\u000fM$xN]1hKB\u0011\u0011\r]\u0005\u0003c\n\u0014AcQ1uC2|wm\u0015;pe\u0006<WMR8s[\u0006$\u0018aD2p]Z,'\u000f\u001e)s_ZLG-\u001a:\u0015\u0005Qd\bCA;z\u001d\t1x\u000f\u0005\u0002F}%\u0011\u0001PP\u0001\u0007!J,G-\u001a4\n\u0005i\\(AB*ue&twM\u0003\u0002y}!)an\u0002a\u0001_\u0006\u0001R.\u001a;bgR|'/Z\"bi\u0006dwnZ\u000b\u0002\u007fB\u0019\u0011+!\u0001\n\u0007\u0005\r\u0001E\u0001\u000bISZ,W*\u001a;bgR|'/Z\"bi\u0006dwnZ\u0001\u0012[\u0016$\u0018m\u001d;pe\u0016\u001c\u0015\r^1m_\u001e\u0004\u0013!B1qa2LHc\u0001\u001b\u0002\f!1\u0011Q\u0002\u0006A\u0002Q\nA\u0001\u001d7b]\u0006\tCm\\\"p]Z,'\u000f\u001e%jm\u0016$\u0016M\u00197f%\u0016d\u0017\r^5p]\u001a{'OU3bIR\u00191,a\u0005\t\u000b}[\u0001\u0019\u00011\u0002?\r|gN^3si\"Kg/\u001a+bE2,'+\u001a7bi&|gNR8s%\u0016\fG\r\u0006\u0003\u0002\u001a\u0005%\u0002\u0003BA\u000e\u0003Ki!!!\b\u000b\t\u0005}\u0011\u0011E\u0001\fI\u0006$\u0018m]8ve\u000e,7OC\u0002\u0002$\t\n\u0011\"\u001a=fGV$\u0018n\u001c8\n\t\u0005\u001d\u0012Q\u0004\u0002\u0010\u0019><\u0017nY1m%\u0016d\u0017\r^5p]\")q\f\u0004a\u0001A\u0006!1m\u001c9z)\r9\u0016q\u0006\u0005\b\u001d6\u0001\n\u00111\u0001Q\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u000e+\u0007A\u000b9d\u000b\u0002\u0002:A!\u00111HA#\u001b\t\tiD\u0003\u0003\u0002@\u0005\u0005\u0013!C;oG\",7m[3e\u0015\r\t\u0019EP\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA$\u0003{\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\n\t\u0005\u0003\u001f\nI&\u0004\u0002\u0002R)!\u00111KA+\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0013\u0001\u00026bm\u0006L1A_A)\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\ty\u0006E\u0002>\u0003CJ1!a\u0019?\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tI'a\u001c\u0011\u0007u\nY'C\u0002\u0002ny\u00121!\u00118z\u0011%\t\t(EA\u0001\u0002\u0004\ty&A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003o\u0002b!!\u001f\u0002\u0000\u0005%TBAA>\u0015\r\tiHP\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAA\u0003w\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u00191,a\"\t\u0013\u0005E4#!AA\u0002\u0005%\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0014\u0002\u000e\"I\u0011\u0011\u000f\u000b\u0002\u0002\u0003\u0007\u0011qL\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011qL\u0001\ti>\u001cFO]5oOR\u0011\u0011QJ\u0001\u0007KF,\u0018\r\\:\u0015\u0007m\u000bY\nC\u0005\u0002r]\t\t\u00111\u0001\u0002j\u0005\u0019\"+\u001a7bi&|gnQ8om\u0016\u00148/[8ogB\u0011\u0011+G\n\u00063\u0005\r\u0016q\u0016\t\u0007\u0003K\u000bY\u000bU,\u000e\u0005\u0005\u001d&bAAU}\u00059!/\u001e8uS6,\u0017\u0002BAW\u0003O\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\t\t,a.\u000e\u0005\u0005M&\u0002BA[\u0003+\n!![8\n\u00071\u000b\u0019\f\u0006\u0002\u0002 R\u0019q+!0\t\u000b9c\u0002\u0019\u0001)\u0002\u000fUt\u0017\r\u001d9msR!\u00111YAe!\u0011i\u0014Q\u0019)\n\u0007\u0005\u001dgH\u0001\u0004PaRLwN\u001c\u0005\t\u0003\u0017l\u0012\u0011!a\u0001/\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005E\u0007\u0003BA(\u0003'LA!!6\u0002R\t1qJ\u00196fGR\u0004"
)
public class RelationConversions extends Rule implements Product, Serializable {
   private final HiveSessionCatalog sessionCatalog;
   private final HiveMetastoreCatalog org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog;

   public static Option unapply(final RelationConversions x$0) {
      return RelationConversions$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return RelationConversions$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RelationConversions$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public HiveSessionCatalog sessionCatalog() {
      return this.sessionCatalog;
   }

   public boolean org$apache$spark$sql$hive$RelationConversions$$isConvertible(final HiveTableRelation relation) {
      return this.org$apache$spark$sql$hive$RelationConversions$$isConvertible(relation.tableMeta());
   }

   public boolean org$apache$spark$sql$hive$RelationConversions$$isConvertible(final CatalogTable tableMeta) {
      return this.org$apache$spark$sql$hive$RelationConversions$$isConvertible(tableMeta.storage());
   }

   public boolean org$apache$spark$sql$hive$RelationConversions$$isConvertible(final CatalogStorageFormat storage) {
      String serde = ((String)storage.serde().getOrElse(() -> "")).toLowerCase(Locale.ROOT);
      return serde.contains("parquet") && BoxesRunTime.unboxToBoolean(this.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_PARQUET())) || serde.contains("orc") && BoxesRunTime.unboxToBoolean(this.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_ORC()));
   }

   public String org$apache$spark$sql$hive$RelationConversions$$convertProvider(final CatalogStorageFormat storage) {
      String serde = ((String)storage.serde().getOrElse(() -> "")).toLowerCase(Locale.ROOT);
      return serde.contains("parquet") ? "parquet" : "orc";
   }

   public HiveMetastoreCatalog org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog() {
      return this.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog;
   }

   public LogicalPlan apply(final LogicalPlan plan) {
      return plan.resolveOperators(new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final RelationConversions $outer;

         public final Object applyOrElse(final LogicalPlan x1, final Function1 default) {
            if (x1 instanceof InsertIntoStatement var6) {
               LogicalPlan r = var6.table();
               Map partition = var6.partitionSpec();
               Seq cols = var6.userSpecifiedCols();
               LogicalPlan query = var6.query();
               boolean overwrite = var6.overwrite();
               boolean ifPartitionNotExists = var6.ifPartitionNotExists();
               boolean byName = var6.byName();
               if (r instanceof HiveTableRelation var14) {
                  if (query.resolved() && .MODULE$.isHiveTable(var14.tableMeta()) && (var14.isPartitioned() && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_INSERTING_PARTITIONED_TABLE())) || !var14.isPartitioned() && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_INSERTING_UNPARTITIONED_TABLE()))) && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(var14)) {
                     return new InsertIntoStatement(this.$outer.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog().convert(var14, true), partition, cols, query, overwrite, ifPartitionNotExists, byName);
                  }
               }
            }

            if (x1 instanceof HiveTableRelation var15) {
               if (this.$outer.doConvertHiveTableRelationForRead(var15)) {
                  return this.$outer.convertHiveTableRelationForRead(var15);
               }
            }

            if (x1 instanceof InsertIntoHiveTable var16) {
               CatalogTable tableDesc = var16.table();
               LogicalPlan query = var16.query();
               boolean overwrite = var16.overwrite();
               boolean ifPartitionNotExists = var16.ifPartitionNotExists();
               if (query.resolved() && .MODULE$.isHiveTable(tableDesc) && tableDesc.partitionColumnNames().isEmpty() && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(tableDesc) && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_CTAS())) && var16.getTagValue(InsertIntoHiveTable$.MODULE$.BY_CTAS()).isDefined()) {
                  DDLUtils var10000 = .MODULE$;
                  StructType x$1 = query.schema();
                  TableIdentifier x$2 = tableDesc.copy$default$1();
                  CatalogTableType x$3 = tableDesc.copy$default$2();
                  CatalogStorageFormat x$4 = tableDesc.copy$default$3();
                  Option x$5 = tableDesc.copy$default$5();
                  Seq x$6 = tableDesc.copy$default$6();
                  Option x$7 = tableDesc.copy$default$7();
                  String x$8 = tableDesc.copy$default$8();
                  long x$9 = tableDesc.copy$default$9();
                  long x$10 = tableDesc.copy$default$10();
                  String x$11 = tableDesc.copy$default$11();
                  Map x$12 = tableDesc.copy$default$12();
                  Option x$13 = tableDesc.copy$default$13();
                  Option x$14 = tableDesc.copy$default$14();
                  Option x$15 = tableDesc.copy$default$15();
                  Option x$16 = tableDesc.copy$default$16();
                  Seq x$17 = tableDesc.copy$default$17();
                  boolean x$18 = tableDesc.copy$default$18();
                  boolean x$19 = tableDesc.copy$default$19();
                  Map x$20 = tableDesc.copy$default$20();
                  Option x$21 = tableDesc.copy$default$21();
                  var10000.checkTableColumns(tableDesc.copy(x$2, x$3, x$4, x$1, x$5, x$6, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21));
                  HiveTableRelation hiveTable = .MODULE$.readHiveTable(tableDesc);
                  LogicalRelation var46 = this.$outer.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog().convert(hiveTable, true);
                  if (var46 != null) {
                     Option var47 = org.apache.spark.sql.execution.datasources.LogicalRelationWithTable..MODULE$.unapply(var46);
                     if (!var47.isEmpty()) {
                        BaseRelation t = (BaseRelation)((Tuple2)var47.get())._1();
                        if (t instanceof HadoopFsRelation) {
                           HadoopFsRelation var49 = (HadoopFsRelation)t;
                           return new InsertIntoHadoopFsRelationCommand((Path)var49.location().rootPaths().head(), scala.Predef..MODULE$.Map().empty(), ifPartitionNotExists, (Seq)scala.package..MODULE$.Seq().empty(), var49.bucketSpec(), var49.fileFormat(), var49.options(), query, overwrite ? SaveMode.Overwrite : SaveMode.Append, new Some(tableDesc), new Some(var49.location()), (Seq)query.output().map((x$4x) -> x$4x.name()));
                        }
                     }
                  }

                  throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.tableIdentifierNotConvertedToHadoopFsRelationError(tableDesc.identifier());
               }
            }

            if (x1 instanceof InsertIntoDir var50) {
               CatalogStorageFormat storage = var50.storage();
               Option provider = var50.provider();
               LogicalPlan query = var50.child();
               boolean overwrite = var50.overwrite();
               if (query.resolved() && .MODULE$.isHiveTable(provider) && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(storage) && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_INSERT_DIR()))) {
                  Path outputPath = new Path((URI)storage.locationUri().get());
                  if (overwrite) {
                     .MODULE$.verifyNotReadPath(query, outputPath, .MODULE$.verifyNotReadPath$default$3());
                  }

                  return new InsertIntoDataSourceDirCommand(this.$outer.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog().convertStorageFormat(storage), this.$outer.org$apache$spark$sql$hive$RelationConversions$$convertProvider(storage), query, overwrite);
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final LogicalPlan x1) {
            if (x1 instanceof InsertIntoStatement var4) {
               LogicalPlan r = var4.table();
               LogicalPlan query = var4.query();
               if (r instanceof HiveTableRelation var7) {
                  if (query.resolved() && .MODULE$.isHiveTable(var7.tableMeta()) && (var7.isPartitioned() && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_INSERTING_PARTITIONED_TABLE())) || !var7.isPartitioned() && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_INSERTING_UNPARTITIONED_TABLE()))) && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(var7)) {
                     return true;
                  }
               }
            }

            if (x1 instanceof HiveTableRelation var8) {
               if (this.$outer.doConvertHiveTableRelationForRead(var8)) {
                  return true;
               }
            }

            if (x1 instanceof InsertIntoHiveTable var9) {
               CatalogTable tableDesc = var9.table();
               LogicalPlan query = var9.query();
               if (query.resolved() && .MODULE$.isHiveTable(tableDesc) && tableDesc.partitionColumnNames().isEmpty() && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(tableDesc) && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_CTAS())) && var9.getTagValue(InsertIntoHiveTable$.MODULE$.BY_CTAS()).isDefined()) {
                  return true;
               }
            }

            if (x1 instanceof InsertIntoDir var12) {
               CatalogStorageFormat storage = var12.storage();
               Option provider = var12.provider();
               LogicalPlan query = var12.child();
               if (query.resolved() && .MODULE$.isHiveTable(provider) && this.$outer.org$apache$spark$sql$hive$RelationConversions$$isConvertible(storage) && BoxesRunTime.unboxToBoolean(this.$outer.conf().getConf(HiveUtils$.MODULE$.CONVERT_METASTORE_INSERT_DIR()))) {
                  return true;
               }
            }

            return false;
         }

         public {
            if (RelationConversions.this == null) {
               throw null;
            } else {
               this.$outer = RelationConversions.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      });
   }

   public boolean doConvertHiveTableRelationForRead(final HiveTableRelation relation) {
      return .MODULE$.isHiveTable(relation.tableMeta()) && this.org$apache$spark$sql$hive$RelationConversions$$isConvertible(relation);
   }

   public LogicalRelation convertHiveTableRelationForRead(final HiveTableRelation relation) {
      return this.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog().convert(relation, false);
   }

   public RelationConversions copy(final HiveSessionCatalog sessionCatalog) {
      return new RelationConversions(sessionCatalog);
   }

   public HiveSessionCatalog copy$default$1() {
      return this.sessionCatalog();
   }

   public String productPrefix() {
      return "RelationConversions";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.sessionCatalog();
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
      return x$1 instanceof RelationConversions;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "sessionCatalog";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof RelationConversions) {
               label40: {
                  RelationConversions var4 = (RelationConversions)x$1;
                  HiveSessionCatalog var10000 = this.sessionCatalog();
                  HiveSessionCatalog var5 = var4.sessionCatalog();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public RelationConversions(final HiveSessionCatalog sessionCatalog) {
      this.sessionCatalog = sessionCatalog;
      Product.$init$(this);
      this.org$apache$spark$sql$hive$RelationConversions$$metastoreCatalog = sessionCatalog.metastoreCatalog();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
