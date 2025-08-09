package org.apache.spark.sql.hive.execution;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.TableIdentifier;
import org.apache.spark.sql.catalyst.catalog.CatalogStorageFormat;
import org.apache.spark.sql.catalyst.catalog.CatalogTable;
import org.apache.spark.sql.catalyst.catalog.CatalogTableType;
import org.apache.spark.sql.catalyst.catalog.SessionCatalog;
import org.apache.spark.sql.catalyst.expressions.AttributeSet;
import org.apache.spark.sql.catalyst.plans.logical.CTEInChildren;
import org.apache.spark.sql.catalyst.plans.logical.Command;
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan;
import org.apache.spark.sql.catalyst.plans.logical.Statistics;
import org.apache.spark.sql.catalyst.plans.logical.WithCTE;
import org.apache.spark.sql.catalyst.trees.LeafLike;
import org.apache.spark.sql.catalyst.trees.TreeNode;
import org.apache.spark.sql.execution.QueryExecution;
import org.apache.spark.sql.execution.command.DataWritingCommand;
import org.apache.spark.sql.execution.command.LeafRunnableCommand;
import org.apache.spark.sql.execution.command.RunnableCommand;
import org.apache.spark.sql.internal.SessionState;
import org.apache.spark.sql.types.StructType;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Predef;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t-b\u0001\u0002\u0013&\u0001JB\u0001\"\u0017\u0001\u0003\u0016\u0004%\tA\u0017\u0005\tC\u0002\u0011\t\u0012)A\u00057\"A!\r\u0001BK\u0002\u0013\u00051\r\u0003\u0005e\u0001\tE\t\u0015!\u00034\u0011!)\u0007A!f\u0001\n\u00031\u0007\u0002\u0003:\u0001\u0005#\u0005\u000b\u0011B4\t\u0011M\u0004!Q3A\u0005\u0002QD\u0001\"\u001f\u0001\u0003\u0012\u0003\u0006I!\u001e\u0005\u0006u\u0002!\ta\u001f\u0005\b\u0003\u000b\u0001A\u0011IA\u0004\u0011%\tY\u0001\u0001b\u0001\n#\ti\u0001\u0003\u0005\u0002\u0018\u0001\u0001\u000b\u0011BA\b\u0011\u001d\tI\u0002\u0001C!\u00037Aq!a\f\u0001\t\u0013\t\t\u0004C\u0004\u0002F\u0001!\t%a\u0012\t\u000f\u0005M\u0003\u0001\"\u0011\u0002V!I\u00111\r\u0001\u0002\u0002\u0013\u0005\u0011Q\r\u0005\n\u0003_\u0002\u0011\u0013!C\u0001\u0003cB\u0011\"a\"\u0001#\u0003%\t!!#\t\u0013\u00055\u0005!%A\u0005\u0002\u0005=\u0005\"CAJ\u0001E\u0005I\u0011AAK\u0011%\tI\nAA\u0001\n\u0003\nY\nC\u0005\u0002,\u0002\t\t\u0011\"\u0001\u0002.\"I\u0011q\u0016\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0017\u0005\n\u0003{\u0003\u0011\u0011!C!\u0003\u007fC\u0011\"!4\u0001\u0003\u0003%\t!a4\t\u0013\u0005M\u0007!!A\u0005B\u0005U\u0007\"CAm\u0001\u0005\u0005I\u0011IAn\u000f%\ty.JA\u0001\u0012\u0003\t\tO\u0002\u0005%K\u0005\u0005\t\u0012AAr\u0011\u0019Qh\u0004\"\u0001\u0002|\"I\u0011Q \u0010\u0002\u0002\u0013\u0015\u0013q \u0005\n\u0005\u0003q\u0012\u0011!CA\u0005\u0007A\u0011B!\u0004\u001f\u0003\u0003%\tIa\u0004\t\u0013\t\u0005b$!A\u0005\n\t\r\"AH\"sK\u0006$X\rS5wKR\u000b'\r\\3BgN+G.Z2u\u0007>lW.\u00198e\u0015\t1s%A\u0005fq\u0016\u001cW\u000f^5p]*\u0011\u0001&K\u0001\u0005Q&4XM\u0003\u0002+W\u0005\u00191/\u001d7\u000b\u00051j\u0013!B:qCJ\\'B\u0001\u00180\u0003\u0019\t\u0007/Y2iK*\t\u0001'A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001gu\"u)\u0014\t\u0003imj\u0011!\u000e\u0006\u0003m]\nq\u0001\\8hS\u000e\fGN\u0003\u00029s\u0005)\u0001\u000f\\1og*\u0011!(K\u0001\tG\u0006$\u0018\r\\=ti&\u0011A(\u000e\u0002\f\u0019><\u0017nY1m!2\fg\u000e\u0005\u0002?\u00056\tqH\u0003\u0002A\u0003\u000691m\\7nC:$'B\u0001\u0014*\u0013\t\u0019uHA\nMK\u00064'+\u001e8oC\ndWmQ8n[\u0006tG\r\u0005\u00025\u000b&\u0011a)\u000e\u0002\u000e\u0007R+\u0015J\\\"iS2$'/\u001a8\u0011\u0005![U\"A%\u000b\u0003)\u000bQa]2bY\u0006L!\u0001T%\u0003\u000fA\u0013x\u000eZ;diB\u0011aJ\u0016\b\u0003\u001fRs!\u0001U*\u000e\u0003ES!AU\u0019\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0015BA+J\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0016-\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005UK\u0015!\u0003;bE2,G)Z:d+\u0005Y\u0006C\u0001/`\u001b\u0005i&B\u00010:\u0003\u001d\u0019\u0017\r^1m_\u001eL!\u0001Y/\u0003\u0019\r\u000bG/\u00197pOR\u000b'\r\\3\u0002\u0015Q\f'\r\\3EKN\u001c\u0007%A\u0003rk\u0016\u0014\u00180F\u00014\u0003\u0019\tX/\u001a:zA\u0005\tr.\u001e;qkR\u001cu\u000e\\;n]:\u000bW.Z:\u0016\u0003\u001d\u00042A\u00145k\u0013\tI\u0007LA\u0002TKF\u0004\"a[8\u000f\u00051l\u0007C\u0001)J\u0013\tq\u0017*\u0001\u0004Qe\u0016$WMZ\u0005\u0003aF\u0014aa\u0015;sS:<'B\u00018J\u0003IyW\u000f\u001e9vi\u000e{G.^7o\u001d\u0006lWm\u001d\u0011\u0002\t5|G-Z\u000b\u0002kB\u0011ao^\u0007\u0002S%\u0011\u00010\u000b\u0002\t'\u00064X-T8eK\u0006)Qn\u001c3fA\u00051A(\u001b8jiz\"r\u0001 @\u0000\u0003\u0003\t\u0019\u0001\u0005\u0002~\u00015\tQ\u0005C\u0003Z\u0013\u0001\u00071\fC\u0003c\u0013\u0001\u00071\u0007C\u0003f\u0013\u0001\u0007q\rC\u0003t\u0013\u0001\u0007Q/A\u0007j]:,'o\u00115jY\u0012\u0014XM\\\u000b\u0003\u0003\u0013\u00012A\u001454\u0003=!\u0018M\u00197f\u0013\u0012,g\u000e^5gS\u0016\u0014XCAA\b!\u0011\t\t\"a\u0005\u000e\u0003eJ1!!\u0006:\u0005=!\u0016M\u00197f\u0013\u0012,g\u000e^5gS\u0016\u0014\u0018\u0001\u0005;bE2,\u0017\nZ3oi&4\u0017.\u001a:!\u0003\r\u0011XO\u001c\u000b\u0005\u0003;\t)\u0003\u0005\u0003OQ\u0006}\u0001c\u0001<\u0002\"%\u0019\u00111E\u0015\u0003\u0007I{w\u000fC\u0004\u0002(5\u0001\r!!\u000b\u0002\u0019M\u0004\u0018M]6TKN\u001c\u0018n\u001c8\u0011\u0007Y\fY#C\u0002\u0002.%\u0012Ab\u00159be.\u001cVm]:j_:\f\u0011cZ3u/JLG/\u001b8h\u0007>lW.\u00198e)\u0019\t\u0019$!\u000f\u0002<A\u0019a(!\u000e\n\u0007\u0005]rH\u0001\nECR\fwK]5uS:<7i\\7nC:$\u0007\"B-\u000f\u0001\u0004Y\u0006bBA\u001f\u001d\u0001\u0007\u0011qH\u0001\fi\u0006\u0014G.Z#ySN$8\u000fE\u0002I\u0003\u0003J1!a\u0011J\u0005\u001d\u0011un\u001c7fC:\f\u0011\"\u0019:h'R\u0014\u0018N\\4\u0015\u0007)\fI\u0005C\u0004\u0002L=\u0001\r!!\u0014\u0002\u00135\f\u0007PR5fY\u0012\u001c\bc\u0001%\u0002P%\u0019\u0011\u0011K%\u0003\u0007%sG/A\u0006xSRD7\tV#EK\u001a\u001cHcA\u001a\u0002X!9\u0011\u0011\f\tA\u0002\u0005m\u0013aB2uK\u0012+gm\u001d\t\u0005\u001d\"\fi\u0006E\u00025\u0003?J1!!\u00196\u00059\u0019E+\u0012*fY\u0006$\u0018n\u001c8EK\u001a\fAaY8qsRIA0a\u001a\u0002j\u0005-\u0014Q\u000e\u0005\b3F\u0001\n\u00111\u0001\\\u0011\u001d\u0011\u0017\u0003%AA\u0002MBq!Z\t\u0011\u0002\u0003\u0007q\rC\u0004t#A\u0005\t\u0019A;\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u000f\u0016\u00047\u0006U4FAA<!\u0011\tI(a!\u000e\u0005\u0005m$\u0002BA?\u0003\u007f\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u0005\u0015*\u0001\u0006b]:|G/\u0019;j_:LA!!\"\u0002|\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0012\u0016\u0004g\u0005U\u0014AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003#S3aZA;\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ*\"!a&+\u0007U\f)(A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003;\u0003B!a(\u0002*6\u0011\u0011\u0011\u0015\u0006\u0005\u0003G\u000b)+\u0001\u0003mC:<'BAAT\u0003\u0011Q\u0017M^1\n\u0007A\f\t+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002N\u0005q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAZ\u0003s\u00032\u0001SA[\u0013\r\t9,\u0013\u0002\u0004\u0003:L\b\"CA^1\u0005\u0005\t\u0019AA'\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0019\t\u0007\u0003\u0007\fI-a-\u000e\u0005\u0005\u0015'bAAd\u0013\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005-\u0017Q\u0019\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002@\u0005E\u0007\"CA^5\u0005\u0005\t\u0019AAZ\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005u\u0015q\u001b\u0005\n\u0003w[\u0012\u0011!a\u0001\u0003\u001b\na!Z9vC2\u001cH\u0003BA \u0003;D\u0011\"a/\u001d\u0003\u0003\u0005\r!a-\u0002=\r\u0013X-\u0019;f\u0011&4X\rV1cY\u0016\f5oU3mK\u000e$8i\\7nC:$\u0007CA?\u001f'\u0015q\u0012Q]Ay!%\t9/!<\\g\u001d,H0\u0004\u0002\u0002j*\u0019\u00111^%\u0002\u000fI,h\u000e^5nK&!\u0011q^Au\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000e\t\u0005\u0003g\fI0\u0004\u0002\u0002v*!\u0011q_AS\u0003\tIw.C\u0002X\u0003k$\"!!9\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!(\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0013q\u0014)Aa\u0002\u0003\n\t-\u0001\"B-\"\u0001\u0004Y\u0006\"\u00022\"\u0001\u0004\u0019\u0004\"B3\"\u0001\u00049\u0007\"B:\"\u0001\u0004)\u0018aB;oCB\u0004H.\u001f\u000b\u0005\u0005#\u0011i\u0002E\u0003I\u0005'\u00119\"C\u0002\u0003\u0016%\u0013aa\u00149uS>t\u0007c\u0002%\u0003\u001am\u001bt-^\u0005\u0004\u00057I%A\u0002+va2,G\u0007\u0003\u0005\u0003 \t\n\t\u00111\u0001}\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005K\u0001B!a(\u0003(%!!\u0011FAQ\u0005\u0019y%M[3di\u0002"
)
public class CreateHiveTableAsSelectCommand extends LogicalPlan implements LeafRunnableCommand, CTEInChildren, Serializable {
   private final CatalogTable tableDesc;
   private final LogicalPlan query;
   private final Seq outputColumnNames;
   private final SaveMode mode;
   private final TableIdentifier tableIdentifier;
   private Map metrics;
   private Seq nodePatterns;
   private volatile boolean bitmap$0;

   public static Option unapply(final CreateHiveTableAsSelectCommand x$0) {
      return CreateHiveTableAsSelectCommand$.MODULE$.unapply(x$0);
   }

   public static Function1 tupled() {
      return CreateHiveTableAsSelectCommand$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return CreateHiveTableAsSelectCommand$.MODULE$.curried();
   }

   public final Seq children() {
      return LeafLike.children$(this);
   }

   public final TreeNode mapChildren(final Function1 f) {
      return LeafLike.mapChildren$(this, f);
   }

   public TreeNode withNewChildrenInternal(final IndexedSeq newChildren) {
      return LeafLike.withNewChildrenInternal$(this, newChildren);
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
            this.metrics = RunnableCommand.metrics$(this);
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

   public final Seq nodePatterns() {
      return this.nodePatterns;
   }

   public final void org$apache$spark$sql$catalyst$plans$logical$Command$_setter_$nodePatterns_$eq(final Seq x$1) {
      this.nodePatterns = x$1;
   }

   public CatalogTable tableDesc() {
      return this.tableDesc;
   }

   public LogicalPlan query() {
      return this.query;
   }

   public Seq outputColumnNames() {
      return this.outputColumnNames;
   }

   public SaveMode mode() {
      return this.mode;
   }

   public Seq innerChildren() {
      LogicalPlan var1 = this.query();
      return .MODULE$.$colon$colon(var1);
   }

   public TableIdentifier tableIdentifier() {
      return this.tableIdentifier;
   }

   public Seq run(final SparkSession sparkSession) {
      SessionCatalog catalog = sparkSession.sessionState().catalog();
      boolean tableExists = catalog.tableExists(this.tableIdentifier());
      if (tableExists) {
         boolean var50;
         Predef var10000;
         label59: {
            label58: {
               var10000 = scala.Predef..MODULE$;
               SaveMode var10001 = this.mode();
               SaveMode var5 = SaveMode.Overwrite;
               if (var10001 == null) {
                  if (var5 != null) {
                     break label58;
                  }
               } else if (!var10001.equals(var5)) {
                  break label58;
               }

               var50 = false;
               break label59;
            }

            var50 = true;
         }

         var10000.assert(var50, () -> "Expect the table " + this.tableIdentifier() + " has been dropped when the save mode is Overwrite");
         SaveMode var48 = this.mode();
         SaveMode var6 = SaveMode.ErrorIfExists;
         if (var48 == null) {
            if (var6 == null) {
               throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.tableIdentifierExistsError(this.tableIdentifier());
            }
         } else if (var48.equals(var6)) {
            throw org.apache.spark.sql.errors.QueryCompilationErrors..MODULE$.tableIdentifierExistsError(this.tableIdentifier());
         }

         label49: {
            var48 = this.mode();
            SaveMode var7 = SaveMode.Ignore;
            if (var48 == null) {
               if (var7 != null) {
                  break label49;
               }
            } else if (!var48.equals(var7)) {
               break label49;
            }

            return (Seq)scala.package..MODULE$.Seq().empty();
         }

         DataWritingCommand command = this.getWritingCommand(this.tableDesc(), true);
         SessionState qual$1 = sparkSession.sessionState();
         Enumeration.Value x$2 = qual$1.executePlan$default$2();
         QueryExecution qe = qual$1.executePlan((LogicalPlan)command, x$2);
         qe.assertCommandExecuted();
      } else {
         this.tableDesc().storage().locationUri().foreach((p) -> {
            $anonfun$run$2(this, sparkSession, p);
            return BoxedUnit.UNIT;
         });
         Seq outputColumns = org.apache.spark.sql.execution.command.DataWritingCommand..MODULE$.logicalPlanOutputWithNames(this.query(), this.outputColumnNames());
         StructType tableSchema = org.apache.spark.sql.catalyst.util.CharVarcharUtils..MODULE$.getRawSchema(org.apache.spark.sql.catalyst.expressions.package..MODULE$.AttributeSeq(outputColumns).toStructType(), sparkSession.sessionState().conf());
         scala.Predef..MODULE$.assert(this.tableDesc().schema().isEmpty());
         TableIdentifier x$4 = this.tableDesc().copy$default$1();
         CatalogTableType x$5 = this.tableDesc().copy$default$2();
         CatalogStorageFormat x$6 = this.tableDesc().copy$default$3();
         Option x$7 = this.tableDesc().copy$default$5();
         Seq x$8 = this.tableDesc().copy$default$6();
         Option x$9 = this.tableDesc().copy$default$7();
         String x$10 = this.tableDesc().copy$default$8();
         long x$11 = this.tableDesc().copy$default$9();
         long x$12 = this.tableDesc().copy$default$10();
         String x$13 = this.tableDesc().copy$default$11();
         Map x$14 = this.tableDesc().copy$default$12();
         Option x$15 = this.tableDesc().copy$default$13();
         Option x$16 = this.tableDesc().copy$default$14();
         Option x$17 = this.tableDesc().copy$default$15();
         Option x$18 = this.tableDesc().copy$default$16();
         Seq x$19 = this.tableDesc().copy$default$17();
         boolean x$20 = this.tableDesc().copy$default$18();
         boolean x$21 = this.tableDesc().copy$default$19();
         Map x$22 = this.tableDesc().copy$default$20();
         Option x$23 = this.tableDesc().copy$default$21();
         catalog.createTable(this.tableDesc().copy(x$4, x$5, x$6, tableSchema, x$7, x$8, x$9, x$10, x$11, x$12, x$13, x$14, x$15, x$16, x$17, x$18, x$19, x$20, x$21, x$22, x$23), false, catalog.createTable$default$3());

         try {
            CatalogTable createdTableMeta = catalog.getTableMetadata(this.tableDesc().identifier());
            DataWritingCommand command = this.getWritingCommand(createdTableMeta, false);
            SessionState qual$2 = sparkSession.sessionState();
            Enumeration.Value x$25 = qual$2.executePlan$default$2();
            QueryExecution qe = qual$2.executePlan((LogicalPlan)command, x$25);
            qe.assertCommandExecuted();
         } catch (Throwable var47) {
            if (var47 != null && scala.util.control.NonFatal..MODULE$.apply(var47)) {
               catalog.dropTable(this.tableIdentifier(), true, false);
               throw var47;
            }

            throw var47;
         }
      }

      return (Seq)scala.package..MODULE$.Seq().empty();
   }

   private DataWritingCommand getWritingCommand(final CatalogTable tableDesc, final boolean tableExists) {
      Map partition = ((IterableOnceOps)tableDesc.partitionColumnNames().map((x$1) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(x$1), scala.None..MODULE$))).toMap(scala..less.colon.less..MODULE$.refl());
      InsertIntoHiveTable insertHive = InsertIntoHiveTable$.MODULE$.apply(tableDesc, partition, this.query(), false, false, this.outputColumnNames());
      insertHive.setTagValue(InsertIntoHiveTable$.MODULE$.BY_CTAS(), BoxedUnit.UNIT);
      return insertHive;
   }

   public String argString(final int maxFields) {
      String var10000 = this.tableDesc().database();
      return "[Database: " + var10000 + ", TableName: " + this.tableDesc().identifier().table() + "]";
   }

   public LogicalPlan withCTEDefs(final Seq cteDefs) {
      WithCTE x$1 = new WithCTE(this.query(), cteDefs);
      CatalogTable x$2 = this.copy$default$1();
      Seq x$3 = this.copy$default$3();
      SaveMode x$4 = this.copy$default$4();
      return this.copy(x$2, x$1, x$3, x$4);
   }

   public CreateHiveTableAsSelectCommand copy(final CatalogTable tableDesc, final LogicalPlan query, final Seq outputColumnNames, final SaveMode mode) {
      return new CreateHiveTableAsSelectCommand(tableDesc, query, outputColumnNames, mode);
   }

   public CatalogTable copy$default$1() {
      return this.tableDesc();
   }

   public LogicalPlan copy$default$2() {
      return this.query();
   }

   public Seq copy$default$3() {
      return this.outputColumnNames();
   }

   public SaveMode copy$default$4() {
      return this.mode();
   }

   public String productPrefix() {
      return "CreateHiveTableAsSelectCommand";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.tableDesc();
         }
         case 1 -> {
            return this.query();
         }
         case 2 -> {
            return this.outputColumnNames();
         }
         case 3 -> {
            return this.mode();
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
      return x$1 instanceof CreateHiveTableAsSelectCommand;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "tableDesc";
         }
         case 1 -> {
            return "query";
         }
         case 2 -> {
            return "outputColumnNames";
         }
         case 3 -> {
            return "mode";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label71: {
            if (x$1 instanceof CreateHiveTableAsSelectCommand) {
               label64: {
                  CreateHiveTableAsSelectCommand var4 = (CreateHiveTableAsSelectCommand)x$1;
                  CatalogTable var10000 = this.tableDesc();
                  CatalogTable var5 = var4.tableDesc();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label64;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label64;
                  }

                  LogicalPlan var9 = this.query();
                  LogicalPlan var6 = var4.query();
                  if (var9 == null) {
                     if (var6 != null) {
                        break label64;
                     }
                  } else if (!var9.equals(var6)) {
                     break label64;
                  }

                  Seq var10 = this.outputColumnNames();
                  Seq var7 = var4.outputColumnNames();
                  if (var10 == null) {
                     if (var7 != null) {
                        break label64;
                     }
                  } else if (!var10.equals(var7)) {
                     break label64;
                  }

                  SaveMode var11 = this.mode();
                  SaveMode var8 = var4.mode();
                  if (var11 == null) {
                     if (var8 != null) {
                        break label64;
                     }
                  } else if (!var11.equals(var8)) {
                     break label64;
                  }

                  if (var4.canEqual(this)) {
                     break label71;
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   // $FF: synthetic method
   public static final void $anonfun$run$2(final CreateHiveTableAsSelectCommand $this, final SparkSession sparkSession$1, final URI p) {
      org.apache.spark.sql.execution.command.DataWritingCommand..MODULE$.assertEmptyRootPath(p, $this.mode(), sparkSession$1.sessionState().newHadoopConf());
   }

   public CreateHiveTableAsSelectCommand(final CatalogTable tableDesc, final LogicalPlan query, final Seq outputColumnNames, final SaveMode mode) {
      this.tableDesc = tableDesc;
      this.query = query;
      this.outputColumnNames = outputColumnNames;
      this.mode = mode;
      Command.$init$(this);
      RunnableCommand.$init$(this);
      LeafLike.$init$(this);
      CTEInChildren.$init$(this);
      scala.Predef..MODULE$.assert(query.resolved());
      this.tableIdentifier = tableDesc.identifier();
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
