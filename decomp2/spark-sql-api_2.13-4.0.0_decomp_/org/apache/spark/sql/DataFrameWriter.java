package org.apache.spark.sql;

import java.util.Locale;
import java.util.Properties;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap;
import org.apache.spark.sql.catalyst.util.CaseInsensitiveMap$;
import org.apache.spark.sql.errors.CompilationErrors$;
import scala.Option;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\tmd!B\u001c9\u0003\u0003\t\u0005\"B%\u0001\t\u0003Q\u0005\"\u0002-\u0001\t\u0003I\u0006\"\u0002-\u0001\t\u0003\u0001\u0007\"B7\u0001\t\u0003q\u0007\"B9\u0001\t\u0003\u0011\b\"B9\u0001\t\u00039\b\"B9\u0001\t\u0003i\bBB9\u0001\t\u0003\t9\u0001C\u0004\u0002\u0014\u0001!\t!!\u0006\t\u000f\u0005M\u0001\u0001\"\u0001\u0002&!9\u0011q\u0007\u0001\u0005\u0002\u0005e\u0002bBA*\u0001\u0011\u0005\u0011Q\u000b\u0005\b\u0003S\u0002A\u0011AA6\u0011\u001d\t\u0019\b\u0001C\u0001\u0003kBq!! \u0001\r\u0003\ty\bC\u0004\u0002~\u00011\t!a#\t\u000f\u00055\u0005A\"\u0001\u0002\u0010\"9\u0011Q\u0013\u0001\u0007\u0002\u0005]\u0005bBAN\u0001\u0011\u0005\u0011Q\u0014\u0005\b\u0003c\u0003A\u0011AAZ\u0011\u001d\t9\f\u0001C\u0001\u0003sCq!!0\u0001\t\u0003\ty\fC\u0004\u0002D\u0002!\t!!2\t\u000f\u0005%\u0007\u0001\"\u0001\u0002L\"9\u0011q\u001a\u0001\u0005\u0002\u0005E\u0007bBAk\u0001\u0011E\u0011q\u001b\u0005\b\u00033\u0004A\u0011CAn\u0011\u001d\t\t\u000f\u0001C\t\u0003GDq!a:\u0001\t#\tI\u000fC\u0004\u0002n\u0002!\t\"a#\t\u0011A\u0004\u0001\u0019!C\t\u0003_D\u0011\"!=\u0001\u0001\u0004%\t\"a=\t\u000f\u0005e\b\u0001)Q\u0005E\"I\u00111 \u0001A\u0002\u0013E\u0011Q \u0005\n\u0003\u007f\u0004\u0001\u0019!C\t\u0005\u0003AqA!\u0002\u0001A\u0003&Q\fC\u0005\u0003\b\u0001\u0001\r\u0011\"\u0005\u0003\n!I!\u0011\u0004\u0001A\u0002\u0013E!1\u0004\u0005\t\u0005?\u0001\u0001\u0015)\u0003\u0003\f!I!\u0011\u0005\u0001A\u0002\u0013E!1\u0005\u0005\n\u0005{\u0001\u0001\u0019!C\t\u0005\u007fA\u0001Ba\u0011\u0001A\u0003&!Q\u0005\u0005\n\u0005\u000b\u0002\u0001\u0019!C\t\u0005GA\u0011Ba\u0012\u0001\u0001\u0004%\tB!\u0013\t\u0011\t5\u0003\u0001)Q\u0005\u0005KA\u0011\"!\u0017\u0001\u0001\u0004%\tBa\u0014\t\u0013\tM\u0003\u00011A\u0005\u0012\tU\u0003\u0002\u0003B-\u0001\u0001\u0006KA!\u0015\t\u0013\tm\u0003\u00011A\u0005\u0012\t\r\u0002\"\u0003B/\u0001\u0001\u0007I\u0011\u0003B0\u0011!\u0011\u0019\u0007\u0001Q!\n\t\u0015\u0002\"\u0003B3\u0001\u0001\u0007I\u0011\u0003B\u0012\u0011%\u00119\u0007\u0001a\u0001\n#\u0011I\u0007\u0003\u0005\u0003n\u0001\u0001\u000b\u0015\u0002B\u0013\u0005=!\u0015\r^1Ge\u0006lWm\u0016:ji\u0016\u0014(BA\u001d;\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003wq\nQa\u001d9be.T!!\u0010 \u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0014aA8sO\u000e\u0001QC\u0001\"P'\t\u00011\t\u0005\u0002E\u000f6\tQIC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAUI\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u00032\u0001\u0014\u0001N\u001b\u0005A\u0004C\u0001(P\u0019\u0001!Q\u0001\u0015\u0001C\u0002E\u0013\u0011\u0001V\t\u0003%V\u0003\"\u0001R*\n\u0005Q+%a\u0002(pi\"Lgn\u001a\t\u0003\tZK!aV#\u0003\u0007\u0005s\u00170\u0001\u0003n_\u0012,GC\u0001.\\\u001b\u0005\u0001\u0001\"\u0002/\u0003\u0001\u0004i\u0016\u0001C:bm\u0016lu\u000eZ3\u0011\u00051s\u0016BA09\u0005!\u0019\u0016M^3N_\u0012,GC\u0001.b\u0011\u0015a6\u00011\u0001c!\t\u0019'N\u0004\u0002eQB\u0011Q-R\u0007\u0002M*\u0011q\rQ\u0001\u0007yI|w\u000e\u001e \n\u0005%,\u0015A\u0002)sK\u0012,g-\u0003\u0002lY\n11\u000b\u001e:j]\u001eT!![#\u0002\r\u0019|'/\\1u)\tQv\u000eC\u0003q\t\u0001\u0007!-\u0001\u0004t_V\u00148-Z\u0001\u0007_B$\u0018n\u001c8\u0015\u0007i\u001bX\u000fC\u0003u\u000b\u0001\u0007!-A\u0002lKfDQA^\u0003A\u0002\t\fQA^1mk\u0016$2A\u0017=z\u0011\u0015!h\u00011\u0001c\u0011\u00151h\u00011\u0001{!\t!50\u0003\u0002}\u000b\n9!i\\8mK\u0006tGc\u0001.\u007f\u007f\")Ao\u0002a\u0001E\"1ao\u0002a\u0001\u0003\u0003\u00012\u0001RA\u0002\u0013\r\t)!\u0012\u0002\u0005\u0019>tw\rF\u0003[\u0003\u0013\tY\u0001C\u0003u\u0011\u0001\u0007!\r\u0003\u0004w\u0011\u0001\u0007\u0011Q\u0002\t\u0004\t\u0006=\u0011bAA\t\u000b\n1Ai\\;cY\u0016\fqa\u001c9uS>t7\u000fF\u0002[\u0003/Aq!a\u0005\n\u0001\u0004\tI\u0002\u0005\u0004\u0002\u001c\u0005\u0005\"MY\u0007\u0003\u0003;Q1!a\bF\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003G\tiBA\u0002NCB$2AWA\u0014\u0011\u001d\t\u0019B\u0003a\u0001\u0003S\u0001b!a\u000b\u00026\t\u0014WBAA\u0017\u0015\u0011\ty#!\r\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0003g\tAA[1wC&!\u00111EA\u0017\u0003-\u0001\u0018M\u001d;ji&|gNQ=\u0015\u0007i\u000bY\u0004C\u0004\u0002>-\u0001\r!a\u0010\u0002\u0011\r|GNT1nKN\u0004B\u0001RA!E&\u0019\u00111I#\u0003\u0015q\u0012X\r]3bi\u0016$g\bK\u0002\f\u0003\u000f\u0002B!!\u0013\u0002P5\u0011\u00111\n\u0006\u0004\u0003\u001b*\u0015AC1o]>$\u0018\r^5p]&!\u0011\u0011KA&\u0005\u001d1\u0018M]1sON\f\u0001BY;dW\u0016$()\u001f\u000b\b5\u0006]\u0013\u0011MA3\u0011\u001d\tI\u0006\u0004a\u0001\u00037\n!B\\;n\u0005V\u001c7.\u001a;t!\r!\u0015QL\u0005\u0004\u0003?*%aA%oi\"1\u00111\r\u0007A\u0002\t\fqaY8m\u001d\u0006lW\rC\u0004\u0002>1\u0001\r!a\u0010)\u00071\t9%\u0001\u0004t_J$()\u001f\u000b\u00065\u00065\u0014q\u000e\u0005\u0007\u0003Gj\u0001\u0019\u00012\t\u000f\u0005uR\u00021\u0001\u0002@!\u001aQ\"a\u0012\u0002\u0013\rdWo\u001d;fe\nKH#\u0002.\u0002x\u0005e\u0004BBA2\u001d\u0001\u0007!\rC\u0004\u0002>9\u0001\r!a\u0010)\u00079\t9%\u0001\u0003tCZ,G\u0003BAA\u0003\u000f\u00032\u0001RAB\u0013\r\t))\u0012\u0002\u0005+:LG\u000f\u0003\u0004\u0002\n>\u0001\rAY\u0001\u0005a\u0006$\b\u000e\u0006\u0002\u0002\u0002\u0006Q\u0011N\\:feRLe\u000e^8\u0015\t\u0005\u0005\u0015\u0011\u0013\u0005\u0007\u0003'\u000b\u0002\u0019\u00012\u0002\u0013Q\f'\r\\3OC6,\u0017aC:bm\u0016\f5\u000fV1cY\u0016$B!!!\u0002\u001a\"1\u00111\u0013\nA\u0002\t\fAA\u001b3cGRA\u0011\u0011QAP\u0003G\u000b9\u000b\u0003\u0004\u0002\"N\u0001\rAY\u0001\u0004kJd\u0007BBAS'\u0001\u0007!-A\u0003uC\ndW\rC\u0004\u0002*N\u0001\r!a+\u0002)\r|gN\\3di&|g\u000e\u0015:pa\u0016\u0014H/[3t!\u0011\tY#!,\n\t\u0005=\u0016Q\u0006\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\u0018\u0001\u00026t_:$B!!!\u00026\"1\u0011\u0011\u0012\u000bA\u0002\t\fq\u0001]1scV,G\u000f\u0006\u0003\u0002\u0002\u0006m\u0006BBAE+\u0001\u0007!-A\u0002pe\u000e$B!!!\u0002B\"1\u0011\u0011\u0012\fA\u0002\t\fA\u0001^3yiR!\u0011\u0011QAd\u0011\u0019\tIi\u0006a\u0001E\u0006\u00191m\u001d<\u0015\t\u0005\u0005\u0015Q\u001a\u0005\u0007\u0003\u0013C\u0002\u0019\u00012\u0002\u0007alG\u000e\u0006\u0003\u0002\u0002\u0006M\u0007BBAE3\u0001\u0007!-\u0001\u0006jg\n+8m[3uK\u0012$\u0012A_\u0001\u0012CN\u001cXM\u001d;O_R\u0014UoY6fi\u0016$G\u0003BAA\u0003;Da!a8\u001c\u0001\u0004\u0011\u0017!C8qKJ\fG/[8o\u0003Q\t7o]3si:{G\u000fU1si&$\u0018n\u001c8fIR!\u0011\u0011QAs\u0011\u0019\ty\u000e\ba\u0001E\u0006\u0011\u0012m]:feRtu\u000e^\"mkN$XM]3e)\u0011\t\t)a;\t\r\u0005}W\u00041\u0001c\u0003Q1\u0018\r\\5eCR,\u0007+\u0019:uSRLwN\\5oOV\t!-\u0001\u0006t_V\u00148-Z0%KF$B!!!\u0002v\"A\u0011q\u001f\u0011\u0002\u0002\u0003\u0007!-A\u0002yIE\nqa]8ve\u000e,\u0007%A\u0004dkJlw\u000eZ3\u0016\u0003u\u000b1bY;s[>$Wm\u0018\u0013fcR!\u0011\u0011\u0011B\u0002\u0011!\t9pIA\u0001\u0002\u0004i\u0016\u0001C2ve6|G-\u001a\u0011\u0002\u0019\u0015DHO]1PaRLwN\\:\u0016\u0005\t-\u0001#\u0002B\u0007\u0005+\u0011WB\u0001B\b\u0015\u0011\tyC!\u0005\u000b\u0007\tM\u0001(\u0001\u0005dCR\fG._:u\u0013\u0011\u00119Ba\u0004\u0003%\r\u000b7/Z%og\u0016t7/\u001b;jm\u0016l\u0015\r]\u0001\u0011Kb$(/Y(qi&|gn]0%KF$B!!!\u0003\u001e!I\u0011q\u001f\u0014\u0002\u0002\u0003\u0007!1B\u0001\u000eKb$(/Y(qi&|gn\u001d\u0011\u0002'A\f'\u000f^5uS>t\u0017N\\4D_2,XN\\:\u0016\u0005\t\u0015\u0002#\u0002#\u0003(\t-\u0012b\u0001B\u0015\u000b\n1q\n\u001d;j_:\u0004RA!\f\u00038\ttAAa\f\u000349\u0019QM!\r\n\u0003\u0019K1A!\u000eF\u0003\u001d\u0001\u0018mY6bO\u0016LAA!\u000f\u0003<\t\u00191+Z9\u000b\u0007\tUR)A\fqCJ$\u0018\u000e^5p]&twmQ8mk6t7o\u0018\u0013fcR!\u0011\u0011\u0011B!\u0011%\t90KA\u0001\u0002\u0004\u0011)#\u0001\u000bqCJ$\u0018\u000e^5p]&twmQ8mk6t7\u000fI\u0001\u0012EV\u001c7.\u001a;D_2,XN\u001c(b[\u0016\u001c\u0018!\u00062vG.,GoQ8mk6tg*Y7fg~#S-\u001d\u000b\u0005\u0003\u0003\u0013Y\u0005C\u0005\u0002x2\n\t\u00111\u0001\u0003&\u0005\u0011\"-^2lKR\u001cu\u000e\\;n]:\u000bW.Z:!+\t\u0011\t\u0006E\u0003E\u0005O\tY&\u0001\bok6\u0014UoY6fiN|F%Z9\u0015\t\u0005\u0005%q\u000b\u0005\n\u0003o|\u0013\u0011!a\u0001\u0005#\n1B\\;n\u0005V\u001c7.\u001a;tA\u0005y1o\u001c:u\u0007>dW/\u001c8OC6,7/A\nt_J$8i\u001c7v[:t\u0015-\\3t?\u0012*\u0017\u000f\u0006\u0003\u0002\u0002\n\u0005\u0004\"CA|e\u0005\u0005\t\u0019\u0001B\u0013\u0003A\u0019xN\u001d;D_2,XN\u001c(b[\u0016\u001c\b%A\tdYV\u001cH/\u001a:j]\u001e\u001cu\u000e\\;n]N\fQc\u00197vgR,'/\u001b8h\u0007>dW/\u001c8t?\u0012*\u0017\u000f\u0006\u0003\u0002\u0002\n-\u0004\"CA|k\u0005\u0005\t\u0019\u0001B\u0013\u0003I\u0019G.^:uKJLgnZ\"pYVlgn\u001d\u0011)\u0007\u0001\u0011\t\b\u0005\u0003\u0003t\t]TB\u0001B;\u0015\r\tiEO\u0005\u0005\u0005s\u0012)H\u0001\u0004Ti\u0006\u0014G.\u001a"
)
public abstract class DataFrameWriter {
   private String source = "";
   private SaveMode curmode;
   private CaseInsensitiveMap extraOptions;
   private Option partitioningColumns;
   private Option bucketColumnNames;
   private Option numBuckets;
   private Option sortColumnNames;
   private Option clusteringColumns;

   public DataFrameWriter partitionBy(final String... colNames) {
      return this.partitionBy((Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public DataFrameWriter bucketBy(final int numBuckets, final String colName, final String... colNames) {
      return this.bucketBy(numBuckets, colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public DataFrameWriter sortBy(final String colName, final String... colNames) {
      return this.sortBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public DataFrameWriter clusterBy(final String colName, final String... colNames) {
      return this.clusterBy(colName, (Seq).MODULE$.wrapRefArray((Object[])colNames));
   }

   public DataFrameWriter mode(final SaveMode saveMode) {
      this.curmode_$eq(saveMode);
      return this;
   }

   public DataFrameWriter mode(final String saveMode) {
      String var3 = saveMode.toLowerCase(Locale.ROOT);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case -1411068134:
            if ("append".equals(var3)) {
               return this.mode(SaveMode.Append);
            }
            break;
         case -1190396462:
            if ("ignore".equals(var3)) {
               return this.mode(SaveMode.Ignore);
            }
            break;
         case -745078901:
            if ("overwrite".equals(var3)) {
               return this.mode(SaveMode.Overwrite);
            }
            break;
         case 96784904:
            if ("error".equals(var3)) {
               return this.mode(SaveMode.ErrorIfExists);
            }
            break;
         case 691225025:
            if ("errorifexists".equals(var3)) {
               return this.mode(SaveMode.ErrorIfExists);
            }
            break;
         case 1544803905:
            if ("default".equals(var3)) {
               return this.mode(SaveMode.ErrorIfExists);
            }
      }

      throw CompilationErrors$.MODULE$.invalidSaveModeError(saveMode);
   }

   public DataFrameWriter format(final String source) {
      this.source_$eq(source);
      return this;
   }

   public DataFrameWriter option(final String key, final String value) {
      this.extraOptions_$eq(this.extraOptions().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(key), value)));
      return this;
   }

   public DataFrameWriter option(final String key, final boolean value) {
      return this.option(key, Boolean.toString(value));
   }

   public DataFrameWriter option(final String key, final long value) {
      return this.option(key, Long.toString(value));
   }

   public DataFrameWriter option(final String key, final double value) {
      return this.option(key, Double.toString(value));
   }

   public DataFrameWriter options(final Map options) {
      this.extraOptions_$eq(this.extraOptions().$plus$plus(options));
      return this;
   }

   public DataFrameWriter options(final java.util.Map options) {
      this.options((Map)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(options).asScala());
      return this;
   }

   public DataFrameWriter partitionBy(final Seq colNames) {
      this.partitioningColumns_$eq(scala.Option..MODULE$.apply(colNames));
      this.validatePartitioning();
      return this;
   }

   public DataFrameWriter bucketBy(final int numBuckets, final String colName, final Seq colNames) {
      this.numBuckets_$eq(scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(numBuckets)));
      this.bucketColumnNames_$eq(scala.Option..MODULE$.apply(colNames.$plus$colon(colName)));
      this.validatePartitioning();
      return this;
   }

   public DataFrameWriter sortBy(final String colName, final Seq colNames) {
      this.sortColumnNames_$eq(scala.Option..MODULE$.apply(colNames.$plus$colon(colName)));
      return this;
   }

   public DataFrameWriter clusterBy(final String colName, final Seq colNames) {
      this.clusteringColumns_$eq(scala.Option..MODULE$.apply(colNames.$plus$colon(colName)));
      this.validatePartitioning();
      return this;
   }

   public abstract void save(final String path);

   public abstract void save();

   public abstract void insertInto(final String tableName);

   public abstract void saveAsTable(final String tableName);

   public void jdbc(final String url, final String table, final Properties connectionProperties) {
      this.assertNotPartitioned("jdbc");
      this.assertNotBucketed("jdbc");
      this.assertNotClustered("jdbc");
      this.extraOptions_$eq(this.extraOptions().$plus$plus(scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(connectionProperties).asScala()));
      this.extraOptions_$eq(this.extraOptions().$plus$plus(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("url"), url), new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("dbtable"), table), scala.collection.immutable.Nil..MODULE$))));
      this.format("jdbc").save();
   }

   public void json(final String path) {
      this.format("json").save(path);
   }

   public void parquet(final String path) {
      this.format("parquet").save(path);
   }

   public void orc(final String path) {
      this.format("orc").save(path);
   }

   public void text(final String path) {
      this.format("text").save(path);
   }

   public void csv(final String path) {
      this.format("csv").save(path);
   }

   public void xml(final String path) {
      this.format("xml").save(path);
   }

   public boolean isBucketed() {
      if (this.sortColumnNames().isDefined() && this.numBuckets().isEmpty()) {
         throw CompilationErrors$.MODULE$.sortByWithoutBucketingError();
      } else {
         return this.numBuckets().isDefined();
      }
   }

   public void assertNotBucketed(final String operation) {
      if (this.isBucketed()) {
         if (this.sortColumnNames().isEmpty()) {
            throw CompilationErrors$.MODULE$.bucketByUnsupportedByOperationError(operation);
         } else {
            throw CompilationErrors$.MODULE$.bucketByAndSortByUnsupportedByOperationError(operation);
         }
      }
   }

   public void assertNotPartitioned(final String operation) {
      if (this.partitioningColumns().isDefined()) {
         throw CompilationErrors$.MODULE$.operationNotSupportPartitioningError(operation);
      }
   }

   public void assertNotClustered(final String operation) {
      if (this.clusteringColumns().isDefined()) {
         throw CompilationErrors$.MODULE$.operationNotSupportClusteringError(operation);
      }
   }

   public void validatePartitioning() {
      if (this.clusteringColumns().nonEmpty()) {
         if (this.partitioningColumns().nonEmpty()) {
            throw CompilationErrors$.MODULE$.clusterByWithPartitionedBy();
         } else if (this.isBucketed()) {
            throw CompilationErrors$.MODULE$.clusterByWithBucketing();
         }
      }
   }

   public String source() {
      return this.source;
   }

   public void source_$eq(final String x$1) {
      this.source = x$1;
   }

   public SaveMode curmode() {
      return this.curmode;
   }

   public void curmode_$eq(final SaveMode x$1) {
      this.curmode = x$1;
   }

   public CaseInsensitiveMap extraOptions() {
      return this.extraOptions;
   }

   public void extraOptions_$eq(final CaseInsensitiveMap x$1) {
      this.extraOptions = x$1;
   }

   public Option partitioningColumns() {
      return this.partitioningColumns;
   }

   public void partitioningColumns_$eq(final Option x$1) {
      this.partitioningColumns = x$1;
   }

   public Option bucketColumnNames() {
      return this.bucketColumnNames;
   }

   public void bucketColumnNames_$eq(final Option x$1) {
      this.bucketColumnNames = x$1;
   }

   public Option numBuckets() {
      return this.numBuckets;
   }

   public void numBuckets_$eq(final Option x$1) {
      this.numBuckets = x$1;
   }

   public Option sortColumnNames() {
      return this.sortColumnNames;
   }

   public void sortColumnNames_$eq(final Option x$1) {
      this.sortColumnNames = x$1;
   }

   public Option clusteringColumns() {
      return this.clusteringColumns;
   }

   public void clusteringColumns_$eq(final Option x$1) {
      this.clusteringColumns = x$1;
   }

   public DataFrameWriter() {
      this.curmode = SaveMode.ErrorIfExists;
      this.extraOptions = CaseInsensitiveMap$.MODULE$.apply(scala.collection.immutable.Map..MODULE$.empty());
      this.partitioningColumns = scala.None..MODULE$;
      this.bucketColumnNames = scala.None..MODULE$;
      this.numBuckets = scala.None..MODULE$;
      this.sortColumnNames = scala.None..MODULE$;
      this.clusteringColumns = scala.None..MODULE$;
   }
}
