package org.apache.spark.sql.hive;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.analysis.Analyzer;
import org.apache.spark.sql.catalyst.analysis.EvalSubqueriesForTimeTravel;
import org.apache.spark.sql.catalyst.analysis.InvokeProcedures;
import org.apache.spark.sql.catalyst.analysis.ReplaceCharWithVarchar;
import org.apache.spark.sql.catalyst.analysis.ResolveDataSource;
import org.apache.spark.sql.catalyst.analysis.ResolveSessionCatalog;
import org.apache.spark.sql.catalyst.analysis.ResolveTranspose;
import org.apache.spark.sql.catalyst.catalog.ExternalCatalogWithListener;
import org.apache.spark.sql.classic.SparkSession;
import org.apache.spark.sql.execution.SparkPlanner;
import org.apache.spark.sql.execution.aggregate.ResolveEncodersInScalaAgg;
import org.apache.spark.sql.execution.analysis.DetectAmbiguousSelfJoin;
import org.apache.spark.sql.execution.command.CommandCheck;
import org.apache.spark.sql.execution.datasources.ApplyCharTypePadding;
import org.apache.spark.sql.execution.datasources.DataSourceAnalysis;
import org.apache.spark.sql.execution.datasources.FallBackFileSourceV2;
import org.apache.spark.sql.execution.datasources.FileResolver;
import org.apache.spark.sql.execution.datasources.FindDataSourceTable;
import org.apache.spark.sql.execution.datasources.PreReadCheck;
import org.apache.spark.sql.execution.datasources.PreWriteCheck;
import org.apache.spark.sql.execution.datasources.PreprocessTableCreation;
import org.apache.spark.sql.execution.datasources.PreprocessTableInsertion;
import org.apache.spark.sql.execution.datasources.QualifyLocationWithWarehouse;
import org.apache.spark.sql.execution.datasources.ResolveSQLOnFile;
import org.apache.spark.sql.execution.datasources.ViewSyncSchemaToMetaStore;
import org.apache.spark.sql.execution.datasources.v2.TableCapabilityCheck;
import org.apache.spark.sql.execution.streaming.ResolveWriteToStream;
import org.apache.spark.sql.hive.execution.PruneHiveTablePartitions;
import org.apache.spark.sql.internal.BaseSessionStateBuilder;
import org.apache.spark.sql.internal.SessionState;
import org.apache.spark.sql.internal.SessionState.;
import scala.Function2;
import scala.Option;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005}4A\u0001D\u0007\u00011!Iq\u0004\u0001B\u0001B\u0003%\u0001E\n\u0005\nO\u0001\u0011\t\u0011)A\u0005QEBQA\r\u0001\u0005\u0002MBQ\u0001\u000f\u0001\u0005\neB\u0001B\u0011\u0001\t\u0006\u0004%\tf\u0011\u0005\t{\u0001A)\u0019!C)\u000f\")1\n\u0001C)\u0019\")1\u000b\u0001C!)\")q\u000e\u0001C)a\")q\u000f\u0001C)q\"YQ\u0010\u0001I\u0001\u0004\u0003\u0005I\u0011\u0002@'\u0005]A\u0015N^3TKN\u001c\u0018n\u001c8Ti\u0006$XMQ;jY\u0012,'O\u0003\u0002\u000f\u001f\u0005!\u0001.\u001b<f\u0015\t\u0001\u0012#A\u0002tc2T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0004\u0005\u0002\u001b;5\t1D\u0003\u0002\u001d\u001f\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002\u001f7\t9\")Y:f'\u0016\u001c8/[8o'R\fG/\u001a\"vS2$WM]\u0001\bg\u0016\u001c8/[8o!\t\tC%D\u0001#\u0015\t\u0019s\"A\u0004dY\u0006\u001c8/[2\n\u0005\u0015\u0012#\u0001D*qCJ\\7+Z:tS>t\u0017BA\u0010\u001e\u0003-\u0001\u0018M]3oiN#\u0018\r^3\u0011\u0007%bc&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u0019y\u0005\u000f^5p]B\u0011!dL\u0005\u0003am\u0011AbU3tg&|gn\u0015;bi\u0016L!aJ\u000f\u0002\rqJg.\u001b;?)\r!dg\u000e\t\u0003k\u0001i\u0011!\u0004\u0005\u0006?\r\u0001\r\u0001\t\u0005\u0006O\r\u0001\r\u0001K\u0001\u0010Kb$XM\u001d8bY\u000e\u000bG/\u00197pOV\t!\b\u0005\u0002<\u00016\tAH\u0003\u0002>}\u000591-\u0019;bY><'BA \u0010\u0003!\u0019\u0017\r^1msN$\u0018BA!=\u0005m)\u0005\u0010^3s]\u0006d7)\u0019;bY><w+\u001b;i\u0019&\u001cH/\u001a8fe\u0006q!/Z:pkJ\u001cW\rT8bI\u0016\u0014X#\u0001#\u0011\u0005U*\u0015B\u0001$\u000e\u0005eA\u0015N^3TKN\u001c\u0018n\u001c8SKN|WO]2f\u0019>\fG-\u001a:\u0016\u0003!\u0003\"!N%\n\u0005)k!A\u0005%jm\u0016\u001cVm]:j_:\u001c\u0015\r^1m_\u001e\f\u0001\"\u00198bYfTXM]\u000b\u0002\u001bB\u0011a*U\u0007\u0002\u001f*\u0011\u0001KP\u0001\tC:\fG._:jg&\u0011!k\u0014\u0002\t\u0003:\fG.\u001f>fe\u0006a2-^:u_6,\u0015M\u001d7z'\u000e\fg\u000eU;tQ\u0012{wO\u001c*vY\u0016\u001cX#A+\u0011\u0007Ys\u0016M\u0004\u0002X9:\u0011\u0001lW\u0007\u00023*\u0011!lF\u0001\u0007yI|w\u000e\u001e \n\u0003-J!!\u0018\u0016\u0002\u000fA\f7m[1hK&\u0011q\f\u0019\u0002\u0004'\u0016\f(BA/+!\r\u0011WmZ\u0007\u0002G*\u0011AMP\u0001\u0006eVdWm]\u0005\u0003M\u000e\u0014AAU;mKB\u0011\u0001.\\\u0007\u0002S*\u0011!n[\u0001\bY><\u0017nY1m\u0015\tag(A\u0003qY\u0006t7/\u0003\u0002oS\nYAj\\4jG\u0006d\u0007\u000b\\1o\u0003\u001d\u0001H.\u00198oKJ,\u0012!\u001d\t\u0003eVl\u0011a\u001d\u0006\u0003i>\t\u0011\"\u001a=fGV$\u0018n\u001c8\n\u0005Y\u001c(\u0001D*qCJ\\\u0007\u000b\\1o]\u0016\u0014\u0018A\u00038fo\n+\u0018\u000e\u001c3feV\t\u0011\u0010\u0005\u0002{w6\t\u0001!\u0003\u0002};\tQa*Z<Ck&dG-\u001a:\u0002\u001bM,\b/\u001a:%g\u0016\u001c8/[8o+\u0005\u0001\u0003"
)
public class HiveSessionStateBuilder extends BaseSessionStateBuilder {
   private HiveSessionResourceLoader resourceLoader;
   private HiveSessionCatalog catalog;
   private volatile byte bitmap$0;

   // $FF: synthetic method
   public SparkSession org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session() {
      return super.session();
   }

   private ExternalCatalogWithListener externalCatalog() {
      return super.session().sharedState().externalCatalog();
   }

   private HiveSessionResourceLoader resourceLoader$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.resourceLoader = new HiveSessionResourceLoader(super.session(), () -> ((HiveExternalCatalog)this.externalCatalog().unwrapped()).client());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.resourceLoader;
   }

   public HiveSessionResourceLoader resourceLoader() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.resourceLoader$lzycompute() : this.resourceLoader;
   }

   private HiveSessionCatalog catalog$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            HiveSessionCatalog catalog = new HiveSessionCatalog(() -> this.externalCatalog(), () -> this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session().sharedState().globalTempViewManager(), new HiveMetastoreCatalog(super.session()), this.functionRegistry(), this.tableFunctionRegistry(), .MODULE$.newHadoopConf(super.session().sparkContext().hadoopConfiguration(), this.conf()), this.sqlParser(), this.resourceLoader(), HiveUDFExpressionBuilder$.MODULE$);
            super.parentState().foreach((x$1) -> {
               $anonfun$catalog$3(catalog, x$1);
               return BoxedUnit.UNIT;
            });
            this.catalog = catalog;
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.catalog;
   }

   public HiveSessionCatalog catalog() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.catalog$lzycompute() : this.catalog;
   }

   public Analyzer analyzer() {
      return new Analyzer() {
         private final Seq singlePassResolverExtensions;
         private final Seq singlePassMetadataResolverExtensions;
         private final Seq extendedResolutionRules;
         private final Seq postHocResolutionRules;
         private final Seq extendedCheckRules;

         public Seq singlePassResolverExtensions() {
            return this.singlePassResolverExtensions;
         }

         public Seq singlePassMetadataResolverExtensions() {
            return this.singlePassMetadataResolverExtensions;
         }

         public Seq extendedResolutionRules() {
            return this.extendedResolutionRules;
         }

         public Seq postHocResolutionRules() {
            return this.postHocResolutionRules;
         }

         public Seq extendedCheckRules() {
            return this.extendedCheckRules;
         }

         public {
            this.singlePassResolverExtensions = new scala.collection.immutable..colon.colon(new DataSourceWithHiveResolver(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session(), HiveSessionStateBuilder.this.catalog()), scala.collection.immutable.Nil..MODULE$);
            this.singlePassMetadataResolverExtensions = new scala.collection.immutable..colon.colon(new FileResolver(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session()), scala.collection.immutable.Nil..MODULE$);
            ResolveHiveSerdeTable var2 = new ResolveHiveSerdeTable(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            ResolveDataSource var3 = new ResolveDataSource(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            FindDataSourceTable var4 = new FindDataSourceTable(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            ResolveSQLOnFile var5 = new ResolveSQLOnFile(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            FallBackFileSourceV2 var6 = new FallBackFileSourceV2(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            ResolveEncodersInScalaAgg var7 = org.apache.spark.sql.execution.aggregate.ResolveEncodersInScalaAgg..MODULE$;
            ResolveSessionCatalog var8 = new ResolveSessionCatalog(this.catalogManager());
            ResolveWriteToStream var9 = org.apache.spark.sql.execution.streaming.ResolveWriteToStream..MODULE$;
            EvalSubqueriesForTimeTravel var10 = new EvalSubqueriesForTimeTravel();
            DetermineTableStats var11 = new DetermineTableStats(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            ResolveTranspose var12 = new ResolveTranspose(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            InvokeProcedures var13 = new InvokeProcedures(HiveSessionStateBuilder.this.org$apache$spark$sql$hive$HiveSessionStateBuilder$$super$session());
            this.extendedResolutionRules = (Seq)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)HiveSessionStateBuilder.this.customResolutionRules().$plus$colon(var13)).$plus$colon(var12)).$plus$colon(var11)).$plus$colon(var10)).$plus$colon(var9)).$plus$colon(var8)).$plus$colon(var7)).$plus$colon(var6)).$plus$colon(var5)).$plus$colon(var4)).$plus$colon(var3)).$plus$colon(var2);
            DetectAmbiguousSelfJoin var14 = org.apache.spark.sql.execution.analysis.DetectAmbiguousSelfJoin..MODULE$;
            RelationConversions var15 = new RelationConversions(HiveSessionStateBuilder.this.catalog());
            QualifyLocationWithWarehouse var16 = new QualifyLocationWithWarehouse(HiveSessionStateBuilder.this.catalog());
            PreprocessTableCreation var17 = new PreprocessTableCreation(HiveSessionStateBuilder.this.catalog());
            PreprocessTableInsertion var18 = org.apache.spark.sql.execution.datasources.PreprocessTableInsertion..MODULE$;
            DataSourceAnalysis var19 = org.apache.spark.sql.execution.datasources.DataSourceAnalysis..MODULE$;
            ApplyCharTypePadding var20 = org.apache.spark.sql.execution.datasources.ApplyCharTypePadding..MODULE$;
            HiveAnalysis$ var21 = HiveAnalysis$.MODULE$;
            ReplaceCharWithVarchar var22 = org.apache.spark.sql.catalyst.analysis.ReplaceCharWithVarchar..MODULE$;
            this.postHocResolutionRules = (Seq)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)((SeqOps)HiveSessionStateBuilder.this.customPostHocResolutionRules().$plus$colon(var22)).$plus$colon(var21)).$plus$colon(var20)).$plus$colon(var19)).$plus$colon(var18)).$plus$colon(var17)).$plus$colon(var16)).$plus$colon(var15)).$plus$colon(var14);
            PreWriteCheck var23 = org.apache.spark.sql.execution.datasources.PreWriteCheck..MODULE$;
            PreReadCheck var24 = org.apache.spark.sql.execution.datasources.PreReadCheck..MODULE$;
            TableCapabilityCheck var25 = org.apache.spark.sql.execution.datasources.v2.TableCapabilityCheck..MODULE$;
            CommandCheck var26 = org.apache.spark.sql.execution.command.CommandCheck..MODULE$;
            ViewSyncSchemaToMetaStore var27 = org.apache.spark.sql.execution.datasources.ViewSyncSchemaToMetaStore..MODULE$;
            this.extendedCheckRules = (Seq)((SeqOps)((SeqOps)((SeqOps)((SeqOps)HiveSessionStateBuilder.this.customCheckRules().$plus$colon(var27)).$plus$colon(var26)).$plus$colon(var25)).$plus$colon(var24)).$plus$colon(var23);
         }
      };
   }

   public Seq customEarlyScanPushDownRules() {
      return new scala.collection.immutable..colon.colon(new PruneHiveTablePartitions(super.session()), scala.collection.immutable.Nil..MODULE$);
   }

   public SparkPlanner planner() {
      return new HiveStrategies() {
         private final SparkSession sparkSession;
         private volatile HiveStrategies.HiveScripts$ HiveScripts$module;
         private volatile HiveStrategies.HiveTableScans$ HiveTableScans$module;
         // $FF: synthetic field
         private final HiveSessionStateBuilder $outer;

         public HiveStrategies.HiveScripts$ HiveScripts() {
            if (this.HiveScripts$module == null) {
               this.HiveScripts$lzycompute$1();
            }

            return this.HiveScripts$module;
         }

         public HiveStrategies.HiveTableScans$ HiveTableScans() {
            if (this.HiveTableScans$module == null) {
               this.HiveTableScans$lzycompute$1();
            }

            return this.HiveTableScans$module;
         }

         public SparkSession sparkSession() {
            return this.sparkSession;
         }

         public Seq extraPlanningStrategies() {
            return (Seq)((IterableOps)super.extraPlanningStrategies().$plus$plus(this.$outer.customPlanningStrategies())).$plus$plus(new scala.collection.immutable..colon.colon(this.HiveTableScans(), new scala.collection.immutable..colon.colon(this.HiveScripts(), scala.collection.immutable.Nil..MODULE$)));
         }

         private final void HiveScripts$lzycompute$1() {
            synchronized(this){}

            try {
               if (this.HiveScripts$module == null) {
                  this.HiveScripts$module = new HiveStrategies.HiveScripts$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         private final void HiveTableScans$lzycompute$1() {
            synchronized(this){}

            try {
               if (this.HiveTableScans$module == null) {
                  this.HiveTableScans$module = new HiveStrategies.HiveTableScans$();
               }
            } catch (Throwable var3) {
               throw var3;
            }

         }

         public {
            if (HiveSessionStateBuilder.this == null) {
               throw null;
            } else {
               this.$outer = HiveSessionStateBuilder.this;
               HiveStrategies.$init$(this);
               this.sparkSession = this.session();
            }
         }
      };
   }

   public Function2 newBuilder() {
      return (x$2, x$3) -> new HiveSessionStateBuilder(x$2, x$3);
   }

   // $FF: synthetic method
   public static final void $anonfun$catalog$3(final HiveSessionCatalog catalog$1, final SessionState x$1) {
      x$1.catalog().copyStateTo(catalog$1);
   }

   public HiveSessionStateBuilder(final SparkSession session, final Option parentState) {
      super(session, parentState);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
