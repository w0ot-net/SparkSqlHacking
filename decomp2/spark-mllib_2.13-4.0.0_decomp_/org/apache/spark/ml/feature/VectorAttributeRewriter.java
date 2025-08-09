package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t%e\u0001\u0002\u001b6\t\u0001C\u0001b\u0013\u0001\u0003\u0006\u0004%\t\u0005\u0014\u0005\t5\u0002\u0011\t\u0011)A\u0005\u001b\"A1\f\u0001BC\u0002\u0013\u0005A\n\u0003\u0005]\u0001\t\u0005\t\u0015!\u0003N\u0011!i\u0006A!b\u0001\n\u0003q\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011B0\t\u000b\r\u0004A\u0011\u00013\t\u000b\r\u0004A\u0011\u00016\t\u000b5\u0004A\u0011\t8\t\u000f\u0005\u001d\u0002\u0001\"\u0011\u0002*!9\u00111\b\u0001\u0005B\u0005u\u0002bBA(\u0001\u0011\u0005\u0013\u0011K\u0004\b\u00033*\u0004\u0012BA.\r\u0019!T\u0007#\u0003\u0002^!11M\u0004C\u0001\u0003wBq!! \u000f\t\u0003\ny\bC\u0004\u0002\b:!\t%!#\u0007\u000f\u0005=e\u0002\u0001\b\u0002\u0012\"I\u00111\u0013\n\u0003\u0002\u0003\u0006I!\u001a\u0005\u0007GJ!\t!!&\u0007\r\u0005u%\u0003RAP\u0011!YVC!f\u0001\n\u0003a\u0005\u0002\u0003/\u0016\u0005#\u0005\u000b\u0011B'\t\u0011u+\"Q3A\u0005\u0002yC\u0001BY\u000b\u0003\u0012\u0003\u0006Ia\u0018\u0005\u0007GV!\t!!.\t\u0013\u0005mR#!A\u0005\u0002\u0005}\u0006\"CAc+E\u0005I\u0011AAd\u0011%\ti.FI\u0001\n\u0003\ty\u000eC\u0005\u0002dV\t\t\u0011\"\u0011\u0002f\"I\u0011\u0011_\u000b\u0002\u0002\u0013\u0005\u00111\u001f\u0005\n\u0003w,\u0012\u0011!C\u0001\u0003{D\u0011Ba\u0001\u0016\u0003\u0003%\tE!\u0002\t\u0013\tMQ#!A\u0005\u0002\tU\u0001\"\u0003B\u0010+\u0005\u0005I\u0011\tB\u0011\u0011%\u0011)#FA\u0001\n\u0003\u00129\u0003C\u0005\u0003*U\t\t\u0011\"\u0011\u0003,!I!QF\u000b\u0002\u0002\u0013\u0005#qF\u0004\n\u0005g\u0011\u0012\u0011!E\u0005\u0005k1\u0011\"!(\u0013\u0003\u0003EIAa\u000e\t\r\rDC\u0011\u0001B#\u0011%\u0011I\u0003KA\u0001\n\u000b\u0012Y\u0003C\u0005\u0003H!\n\t\u0011\"!\u0003J!I!q\n\u0015\u0002\u0002\u0013\u0005%\u0011\u000b\u0005\b\u0005G\u0012B\u0011\u000bB3\r\u0019\u0011yG\u0004\u0003\u0003r!11M\fC\u0001\u0005gB\u0011Ba\u001e/\u0005\u0004%I!!:\t\u0011\ted\u0006)A\u0005\u0003ODq!a\"/\t\u0003\u0012Y\bC\u0005\u0003\u00009\t\t\u0011\"\u0003\u0003\u0002\n9b+Z2u_J\fE\u000f\u001e:jEV$XMU3xe&$XM\u001d\u0006\u0003m]\nqAZ3biV\u0014XM\u0003\u00029s\u0005\u0011Q\u000e\u001c\u0006\u0003um\nQa\u001d9be.T!\u0001P\u001f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0014aA8sO\u000e\u00011c\u0001\u0001B\u000bB\u0011!iQ\u0007\u0002o%\u0011Ai\u000e\u0002\f)J\fgn\u001d4pe6,'\u000f\u0005\u0002G\u00136\tqI\u0003\u0002Io\u0005!Q\u000f^5m\u0013\tQuI\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005i\u0005C\u0001(X\u001d\tyU\u000b\u0005\u0002Q'6\t\u0011K\u0003\u0002S\u007f\u00051AH]8pizR\u0011\u0001V\u0001\u0006g\u000e\fG.Y\u0005\u0003-N\u000ba\u0001\u0015:fI\u00164\u0017B\u0001-Z\u0005\u0019\u0019FO]5oO*\u0011akU\u0001\u0005k&$\u0007%A\u0005wK\u000e$xN]\"pY\u0006Qa/Z2u_J\u001cu\u000e\u001c\u0011\u0002#A\u0014XMZ5yKN$vNU3xe&$X-F\u0001`!\u0011q\u0005-T'\n\u0005\u0005L&aA'ba\u0006\u0011\u0002O]3gSb,7\u000fV8SK^\u0014\u0018\u000e^3!\u0003\u0019a\u0014N\\5u}Q!Qm\u001a5j!\t1\u0007!D\u00016\u0011\u0015Yu\u00011\u0001N\u0011\u0015Yv\u00011\u0001N\u0011\u0015iv\u00011\u0001`)\r)7\u000e\u001c\u0005\u00067\"\u0001\r!\u0014\u0005\u0006;\"\u0001\raX\u0001\niJ\fgn\u001d4pe6$2a\\A\u0001!\t\u0001XP\u0004\u0002ru:\u0011!\u000f\u001f\b\u0003g^t!\u0001\u001e<\u000f\u0005A+\u0018\"\u0001 \n\u0005qj\u0014B\u0001\u001e<\u0013\tI\u0018(A\u0002tc2L!a\u001f?\u0002\u000fA\f7m[1hK*\u0011\u00110O\u0005\u0003}~\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005md\bbBA\u0002\u0013\u0001\u0007\u0011QA\u0001\bI\u0006$\u0018m]3ua\u0011\t9!a\u0005\u0011\r\u0005%\u00111BA\b\u001b\u0005a\u0018bAA\u0007y\n9A)\u0019;bg\u0016$\b\u0003BA\t\u0003'a\u0001\u0001\u0002\u0007\u0002\u0016\u0005\u0005\u0011\u0011!A\u0001\u0006\u0003\t9BA\u0002`IU\nB!!\u0007\u0002\"A!\u00111DA\u000f\u001b\u0005\u0019\u0016bAA\u0010'\n9aj\u001c;iS:<\u0007\u0003BA\u000e\u0003GI1!!\nT\u0005\r\te._\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u00111FA\u001c!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019y\u0006)A/\u001f9fg&!\u0011QGA\u0018\u0005)\u0019FO];diRK\b/\u001a\u0005\b\u0003sQ\u0001\u0019AA\u0016\u0003\u0019\u00198\r[3nC\u0006!1m\u001c9z)\r)\u0017q\b\u0005\b\u0003\u0003Z\u0001\u0019AA\"\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t)%a\u0013\u000e\u0005\u0005\u001d#bAA%o\u0005)\u0001/\u0019:b[&!\u0011QJA$\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\u0018!B<sSR,WCAA*!\r1\u0015QK\u0005\u0004\u0003/:%\u0001C'M/JLG/\u001a:\u0002/Y+7\r^8s\u0003R$(/\u001b2vi\u0016\u0014Vm\u001e:ji\u0016\u0014\bC\u00014\u000f'\u001dq\u0011qLA3\u0003W\u0002B!a\u0007\u0002b%\u0019\u00111M*\u0003\r\u0005s\u0017PU3g!\u00111\u0015qM3\n\u0007\u0005%tI\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!!\u001c\u0002x5\u0011\u0011q\u000e\u0006\u0005\u0003c\n\u0019(\u0001\u0002j_*\u0011\u0011QO\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002z\u0005=$\u0001D*fe&\fG.\u001b>bE2,GCAA.\u0003\u0011\u0011X-\u00193\u0016\u0005\u0005\u0005\u0005\u0003\u0002$\u0002\u0004\u0016L1!!\"H\u0005!iEJU3bI\u0016\u0014\u0018\u0001\u00027pC\u0012$2!ZAF\u0011\u0019\ti)\u0005a\u0001\u001b\u0006!\u0001/\u0019;i\u0005u1Vm\u0019;pe\u0006#HO]5ckR,'+Z<sSR,'o\u0016:ji\u0016\u00148c\u0001\n\u0002T\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0002\u0018\u0006m\u0005cAAM%5\ta\u0002\u0003\u0004\u0002\u0014R\u0001\r!\u001a\u0002\u0005\t\u0006$\u0018mE\u0004\u0016\u0003?\n\t+a*\u0011\t\u0005m\u00111U\u0005\u0004\u0003K\u001b&a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003S\u000b\tL\u0004\u0003\u0002,\u0006=fb\u0001)\u0002.&\tA+\u0003\u0002|'&!\u0011\u0011PAZ\u0015\tY8\u000b\u0006\u0004\u00028\u0006m\u0016Q\u0018\t\u0004\u0003s+R\"\u0001\n\t\u000bmS\u0002\u0019A'\t\u000buS\u0002\u0019A0\u0015\r\u0005]\u0016\u0011YAb\u0011\u001dY6\u0004%AA\u00025Cq!X\u000e\u0011\u0002\u0003\u0007q,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005%'fA'\u0002L.\u0012\u0011Q\u001a\t\u0005\u0003\u001f\fI.\u0004\u0002\u0002R*!\u00111[Ak\u0003%)hn\u00195fG.,GMC\u0002\u0002XN\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tY.!5\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005\u0005(fA0\u0002L\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a:\u0011\t\u0005%\u0018q^\u0007\u0003\u0003WTA!!<\u0002t\u0005!A.\u00198h\u0013\rA\u00161^\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003k\u0004B!a\u0007\u0002x&\u0019\u0011\u0011`*\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005\u0012q \u0005\n\u0005\u0003\u0001\u0013\u0011!a\u0001\u0003k\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\u0004!\u0019\u0011IAa\u0004\u0002\"5\u0011!1\u0002\u0006\u0004\u0005\u001b\u0019\u0016AC2pY2,7\r^5p]&!!\u0011\u0003B\u0006\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t]!Q\u0004\t\u0005\u00037\u0011I\"C\u0002\u0003\u001cM\u0013qAQ8pY\u0016\fg\u000eC\u0005\u0003\u0002\t\n\t\u00111\u0001\u0002\"\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t9Oa\t\t\u0013\t\u00051%!AA\u0002\u0005U\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u001d\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0003\u0018\tE\u0002\"\u0003B\u0001M\u0005\u0005\t\u0019AA\u0011\u0003\u0011!\u0015\r^1\u0011\u0007\u0005e\u0006fE\u0003)\u0005s\tY\u0007\u0005\u0005\u0003<\t\u0005SjXA\\\u001b\t\u0011iDC\u0002\u0003@M\u000bqA];oi&lW-\u0003\u0003\u0003D\tu\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011!QG\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0003o\u0013YE!\u0014\t\u000bm[\u0003\u0019A'\t\u000bu[\u0003\u0019A0\u0002\u000fUt\u0017\r\u001d9msR!!1\u000bB0!\u0019\tYB!\u0016\u0003Z%\u0019!qK*\u0003\r=\u0003H/[8o!\u0019\tYBa\u0017N?&\u0019!QL*\u0003\rQ+\b\u000f\\33\u0011%\u0011\t\u0007LA\u0001\u0002\u0004\t9,A\u0002yIA\n\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005O\u0012i\u0007\u0005\u0003\u0002\u001c\t%\u0014b\u0001B6'\n!QK\\5u\u0011\u0019\ti)\fa\u0001\u001b\nib+Z2u_J\fE\u000f\u001e:jEV$XMU3xe&$XM\u001d*fC\u0012,'oE\u0002/\u0003\u0003#\"A!\u001e\u0011\u0007\u0005ee&A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0015\u0007\u0015\u0014i\b\u0003\u0004\u0002\u000eJ\u0002\r!T\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u0007\u0003B!!;\u0003\u0006&!!qQAv\u0005\u0019y%M[3di\u0002"
)
public class VectorAttributeRewriter extends Transformer implements MLWritable {
   private final String uid;
   private final String vectorCol;
   private final Map prefixesToRewrite;

   public static VectorAttributeRewriter load(final String path) {
      return VectorAttributeRewriter$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorAttributeRewriter$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String uid() {
      return this.uid;
   }

   public String vectorCol() {
      return this.vectorCol;
   }

   public Map prefixesToRewrite() {
      return this.prefixesToRewrite;
   }

   public Dataset transform(final Dataset dataset) {
      AttributeGroup group = AttributeGroup$.MODULE$.fromStructField(SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), this.vectorCol()));
      Attribute[] attrs = (Attribute[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(group.attributes().get()), (attr) -> {
         if (attr.name().isDefined()) {
            String name = (String)this.prefixesToRewrite().foldLeft(attr.name().get(), (x0$1, x1$1) -> {
               Tuple2 var3 = new Tuple2(x0$1, x1$1);
               if (var3 != null) {
                  String curName = (String)var3._1();
                  Tuple2 var5 = (Tuple2)var3._2();
                  if (var5 != null) {
                     String from = (String)var5._1();
                     String to = (String)var5._2();
                     return curName.replace(from, to);
                  }
               }

               throw new MatchError(var3);
            });
            return attr.withName(name);
         } else {
            return attr;
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Attribute.class));
      Metadata metadata = (new AttributeGroup(this.vectorCol(), attrs)).toMetadata();
      String vectorColFieldName = SchemaUtils$.MODULE$.getSchemaField(dataset.schema(), this.vectorCol()).name();
      Column[] otherCols = (Column[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])dataset.columns()), (x$5) -> BoxesRunTime.boxToBoolean($anonfun$transform$5(vectorColFieldName, x$5)))), (colName) -> dataset.col(colName), scala.reflect.ClassTag..MODULE$.apply(Column.class));
      Column rewrittenCol = dataset.col(this.vectorCol()).as(this.vectorCol(), metadata);
      return dataset.select(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])otherCols), rewrittenCol, scala.reflect.ClassTag..MODULE$.apply(Column.class))).toImmutableArraySeq());
   }

   public StructType transformSchema(final StructType schema) {
      return new StructType((StructField[]).MODULE$.$plus$plus$extension(scala.Predef..MODULE$.refArrayOps(.MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$3(this, x$6)))), .MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$transformSchema$4(this, x$7))), scala.reflect.ClassTag..MODULE$.apply(StructField.class)));
   }

   public VectorAttributeRewriter copy(final ParamMap extra) {
      return (VectorAttributeRewriter)this.defaultCopy(extra);
   }

   public MLWriter write() {
      return new VectorAttributeRewriterWriter(this);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$5(final String vectorColFieldName$1, final String x$5) {
      boolean var10000;
      label23: {
         if (x$5 == null) {
            if (vectorColFieldName$1 != null) {
               break label23;
            }
         } else if (!x$5.equals(vectorColFieldName$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$3(final VectorAttributeRewriter $this, final StructField x$6) {
      boolean var3;
      label23: {
         String var10000 = x$6.name();
         String var2 = $this.vectorCol();
         if (var10000 == null) {
            if (var2 != null) {
               break label23;
            }
         } else if (!var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transformSchema$4(final VectorAttributeRewriter $this, final StructField x$7) {
      boolean var3;
      label23: {
         String var10000 = x$7.name();
         String var2 = $this.vectorCol();
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public VectorAttributeRewriter(final String uid, final String vectorCol, final Map prefixesToRewrite) {
      this.uid = uid;
      this.vectorCol = vectorCol;
      this.prefixesToRewrite = prefixesToRewrite;
      MLWritable.$init$(this);
   }

   public VectorAttributeRewriter(final String vectorCol, final Map prefixesToRewrite) {
      this(Identifiable$.MODULE$.randomUID("vectorAttrRewriter"), vectorCol, prefixesToRewrite);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class VectorAttributeRewriterWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final VectorAttributeRewriter instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$2();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.vectorCol(), this.instance.prefixesToRewrite());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VectorAttributeRewriterWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.VectorAttributeRewriter.VectorAttributeRewriterWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.VectorAttributeRewriter.VectorAttributeRewriterWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$2() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public VectorAttributeRewriterWriter(final VectorAttributeRewriter instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final String vectorCol;
         private final Map prefixesToRewrite;
         // $FF: synthetic field
         public final VectorAttributeRewriterWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public String vectorCol() {
            return this.vectorCol;
         }

         public Map prefixesToRewrite() {
            return this.prefixesToRewrite;
         }

         public Data copy(final String vectorCol, final Map prefixesToRewrite) {
            return this.org$apache$spark$ml$feature$VectorAttributeRewriter$VectorAttributeRewriterWriter$Data$$$outer().new Data(vectorCol, prefixesToRewrite);
         }

         public String copy$default$1() {
            return this.vectorCol();
         }

         public Map copy$default$2() {
            return this.prefixesToRewrite();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 2;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.vectorCol();
               }
               case 1 -> {
                  return this.prefixesToRewrite();
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
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "vectorCol";
               }
               case 1 -> {
                  return "prefixesToRewrite";
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
            boolean var8;
            if (this != x$1) {
               label60: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$VectorAttributeRewriter$VectorAttributeRewriterWriter$Data$$$outer() == this.org$apache$spark$ml$feature$VectorAttributeRewriter$VectorAttributeRewriterWriter$Data$$$outer()) {
                     label50: {
                        Data var4 = (Data)x$1;
                        String var10000 = this.vectorCol();
                        String var5 = var4.vectorCol();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        Map var7 = this.prefixesToRewrite();
                        Map var6 = var4.prefixesToRewrite();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label50;
                           }
                        } else if (!var7.equals(var6)) {
                           break label50;
                        }

                        if (var4.canEqual(this)) {
                           break label60;
                        }
                     }
                  }

                  var8 = false;
                  return var8;
               }
            }

            var8 = true;
            return var8;
         }

         // $FF: synthetic method
         public VectorAttributeRewriterWriter org$apache$spark$ml$feature$VectorAttributeRewriter$VectorAttributeRewriterWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final String vectorCol, final Map prefixesToRewrite) {
            this.vectorCol = vectorCol;
            this.prefixesToRewrite = prefixesToRewrite;
            if (VectorAttributeRewriterWriter.this == null) {
               throw null;
            } else {
               this.$outer = VectorAttributeRewriterWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final VectorAttributeRewriterWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final String vectorCol, final Map prefixesToRewrite) {
            return this.$outer.new Data(vectorCol, prefixesToRewrite);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.vectorCol(), x$0.prefixesToRewrite())));
         }

         public Data$() {
            if (VectorAttributeRewriterWriter.this == null) {
               throw null;
            } else {
               this.$outer = VectorAttributeRewriterWriter.this;
               super();
            }
         }
      }
   }

   private static class VectorAttributeRewriterReader extends MLReader {
      private final String className = VectorAttributeRewriter.class.getName();

      private String className() {
         return this.className;
      }

      public VectorAttributeRewriter load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("vectorCol", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"prefixesToRewrite"}))).head();
         String vectorCol = data.getString(0);
         Map prefixesToRewrite = (Map)data.getAs(1);
         VectorAttributeRewriter rewriter = new VectorAttributeRewriter(metadata.uid(), vectorCol, prefixesToRewrite);
         metadata.getAndSetParams(rewriter, metadata.getAndSetParams$default$2());
         return rewriter;
      }

      public VectorAttributeRewriterReader() {
      }
   }
}
