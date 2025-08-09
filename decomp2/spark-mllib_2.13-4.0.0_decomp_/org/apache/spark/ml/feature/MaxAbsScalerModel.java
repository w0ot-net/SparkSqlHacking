package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005g\u0001\u0002\u001a4\u0001yB\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\tM\u0002\u0011\t\u0011)A\u0005!\"A\u0001\u000e\u0001BC\u0002\u0013\u0005\u0011\u000e\u0003\u0005r\u0001\t\u0005\t\u0015!\u0003k\u0011\u0019\u0019\b\u0001\"\u00016i\"11\u000f\u0001C\u0001keDQA\u001f\u0001\u0005\u0002mDq!!\u0001\u0001\t\u0003\t\u0019\u0001C\u0004\u0002\n\u0001!\t%a\u0003\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u0011Q\u000e\u0001\u0005B\u0005=\u0004bBAB\u0001\u0011\u0005\u0013Q\u0011\u0005\b\u0003'\u0003A\u0011IAK\u000f\u001d\tyj\rE\u0001\u0003C3aAM\u001a\t\u0002\u0005\r\u0006BB:\u0010\t\u0003\t\tMB\u0004\u0002D>\u0001q\"!2\t\u0013\u0005\u001d\u0017C!A!\u0002\u0013\u0019\u0005BB:\u0012\t\u0003\tIM\u0002\u0004\u0002RF!\u00151\u001b\u0005\tQR\u0011)\u001a!C\u0001S\"A\u0011\u000f\u0006B\tB\u0003%!\u000e\u0003\u0004t)\u0011\u0005\u0011\u0011\u001e\u0005\n\u0003[\"\u0012\u0011!C\u0001\u0003cD\u0011\"!>\u0015#\u0003%\t!a>\t\u0013\t-A#!A\u0005B\t5\u0001\"\u0003B\r)\u0005\u0005I\u0011\u0001B\u000e\u0011%\u0011\u0019\u0003FA\u0001\n\u0003\u0011)\u0003C\u0005\u0003,Q\t\t\u0011\"\u0011\u0003.!I!1\b\u000b\u0002\u0002\u0013\u0005!Q\b\u0005\n\u0005\u000f\"\u0012\u0011!C!\u0005\u0013B\u0011B!\u0014\u0015\u0003\u0003%\tEa\u0014\t\u0013\u0005ME#!A\u0005B\tE\u0003\"\u0003B*)\u0005\u0005I\u0011\tB+\u000f%\u0011I&EA\u0001\u0012\u0013\u0011YFB\u0005\u0002RF\t\t\u0011#\u0003\u0003^!11\u000f\nC\u0001\u0005WB\u0011\"a%%\u0003\u0003%)E!\u0015\t\u0013\t5D%!A\u0005\u0002\n=\u0004\"\u0003B:I\u0005\u0005I\u0011\u0011B;\u0011\u001d\u0011\t)\u0005C)\u0005\u00073aAa$\u0010\t\tE\u0005BB:+\t\u0003\u0011I\nC\u0005\u0003\u001e*\u0012\r\u0011\"\u0003\u0003\u000e!A!q\u0014\u0016!\u0002\u0013\u0011y\u0001C\u0004\u0003\"*\"\tEa)\t\u000f\t\u001dv\u0002\"\u0011\u0003*\"9!\u0011U\b\u0005B\t5\u0006\"\u0003BZ\u001f\u0005\u0005I\u0011\u0002B[\u0005Ei\u0015\r_!cgN\u001b\u0017\r\\3s\u001b>$W\r\u001c\u0006\u0003iU\nqAZ3biV\u0014XM\u0003\u00027o\u0005\u0011Q\u000e\u001c\u0006\u0003qe\nQa\u001d9be.T!AO\u001e\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0014aA8sO\u000e\u00011\u0003\u0002\u0001@\u000b\"\u00032\u0001Q!D\u001b\u0005)\u0014B\u0001\"6\u0005\u0015iu\u000eZ3m!\t!\u0005!D\u00014!\t!e)\u0003\u0002Hg\t\u0011R*\u0019=BEN\u001c6-\u00197feB\u000b'/Y7t!\tIE*D\u0001K\u0015\tYU'\u0001\u0003vi&d\u0017BA'K\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#\u0001)\u0011\u0005ESfB\u0001*Y!\t\u0019f+D\u0001U\u0015\t)V(\u0001\u0004=e>|GO\u0010\u0006\u0002/\u0006)1oY1mC&\u0011\u0011LV\u0001\u0007!J,G-\u001a4\n\u0005mc&AB*ue&twM\u0003\u0002Z-\"\u001a\u0011A\u00183\u0011\u0005}\u0013W\"\u00011\u000b\u0005\u0005<\u0014AC1o]>$\u0018\r^5p]&\u00111\r\u0019\u0002\u0006'&t7-Z\u0011\u0002K\u0006)!G\f\u0019/a\u0005!Q/\u001b3!Q\r\u0011a\fZ\u0001\u0007[\u0006D\u0018IY:\u0016\u0003)\u0004\"a\u001b8\u000e\u00031T!!\\\u001b\u0002\r1Lg.\u00197h\u0013\tyGN\u0001\u0004WK\u000e$xN\u001d\u0015\u0004\u0007y#\u0017aB7bq\u0006\u00137\u000f\t\u0015\u0004\ty#\u0017A\u0002\u001fj]&$h\bF\u0002Dk^DQAT\u0003A\u0002AC3!\u001e0e\u0011\u0015AW\u00011\u0001kQ\r9h\f\u001a\u000b\u0002\u0007\u0006Y1/\u001a;J]B,HoQ8m)\taX0D\u0001\u0001\u0011\u0015qx\u00011\u0001Q\u0003\u00151\u0018\r\\;fQ\r9a\fZ\u0001\rg\u0016$x*\u001e;qkR\u001cu\u000e\u001c\u000b\u0004y\u0006\u0015\u0001\"\u0002@\t\u0001\u0004\u0001\u0006f\u0001\u0005_I\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003\u001b\ty\u0003\u0005\u0003\u0002\u0010\u0005%b\u0002BA\t\u0003GqA!a\u0005\u0002 9!\u0011QCA\u000f\u001d\u0011\t9\"a\u0007\u000f\u0007M\u000bI\"C\u0001=\u0013\tQ4(\u0003\u00029s%\u0019\u0011\u0011E\u001c\u0002\u0007M\fH.\u0003\u0003\u0002&\u0005\u001d\u0012a\u00029bG.\fw-\u001a\u0006\u0004\u0003C9\u0014\u0002BA\u0016\u0003[\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005\u0015\u0012q\u0005\u0005\b\u0003cI\u0001\u0019AA\u001a\u0003\u001d!\u0017\r^1tKR\u0004D!!\u000e\u0002BA1\u0011qGA\u001d\u0003{i!!a\n\n\t\u0005m\u0012q\u0005\u0002\b\t\u0006$\u0018m]3u!\u0011\ty$!\u0011\r\u0001\u0011a\u00111IA\u0018\u0003\u0003\u0005\tQ!\u0001\u0002F\t\u0019q\f\n\u001a\u0012\t\u0005\u001d\u0013q\n\t\u0005\u0003\u0013\nY%D\u0001W\u0013\r\tiE\u0016\u0002\b\u001d>$\b.\u001b8h!\u0011\tI%!\u0015\n\u0007\u0005McKA\u0002B]fD3!\u00030e\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA.\u0003O\u0002B!!\u0018\u0002d5\u0011\u0011q\f\u0006\u0005\u0003C\n9#A\u0003usB,7/\u0003\u0003\u0002f\u0005}#AC*ueV\u001cG\u000fV=qK\"9\u0011\u0011\u000e\u0006A\u0002\u0005m\u0013AB:dQ\u0016l\u0017\rK\u0002\u000b=\u0012\fAaY8qsR\u00191)!\u001d\t\u000f\u0005M4\u00021\u0001\u0002v\u0005)Q\r\u001f;sCB!\u0011qOA?\u001b\t\tIHC\u0002\u0002|U\nQ\u0001]1sC6LA!a \u0002z\tA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\f=\u0012\fQa\u001e:ji\u0016,\"!a\"\u0011\u0007%\u000bI)C\u0002\u0002\f*\u0013\u0001\"\u0014'Xe&$XM\u001d\u0015\u0005\u0019y\u000by)\t\u0002\u0002\u0012\u0006)\u0011G\f\u001c/a\u0005AAo\\*ue&tw\rF\u0001QQ\u0011ia,!'\"\u0005\u0005m\u0015!B\u001a/a9\u0002\u0004f\u0001\u0001_I\u0006\tR*\u0019=BEN\u001c6-\u00197fe6{G-\u001a7\u0011\u0005\u0011{1cB\b\u0002&\u0006-\u0016\u0011\u0017\t\u0005\u0003\u0013\n9+C\u0002\u0002*Z\u0013a!\u00118z%\u00164\u0007\u0003B%\u0002.\u000eK1!a,K\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0003g\u000bi,\u0004\u0002\u00026*!\u0011qWA]\u0003\tIwN\u0003\u0002\u0002<\u0006!!.\u0019<b\u0013\u0011\ty,!.\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\u0005&aF'bq\u0006\u00137oU2bY\u0016\u0014Xj\u001c3fY^\u0013\u0018\u000e^3s'\r\t\u0012qQ\u0001\tS:\u001cH/\u00198dKR!\u00111ZAh!\r\ti-E\u0007\u0002\u001f!1\u0011qY\nA\u0002\r\u0013A\u0001R1uCN9A#!*\u0002V\u0006m\u0007\u0003BA%\u0003/L1!!7W\u0005\u001d\u0001&o\u001c3vGR\u0004B!!8\u0002f:!\u0011q\\Ar\u001d\r\u0019\u0016\u0011]\u0005\u0002/&\u0019\u0011Q\u0005,\n\t\u0005}\u0016q\u001d\u0006\u0004\u0003K1F\u0003BAv\u0003_\u00042!!<\u0015\u001b\u0005\t\u0002\"\u00025\u0018\u0001\u0004QG\u0003BAv\u0003gDq\u0001\u001b\r\u0011\u0002\u0003\u0007!.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005e(f\u00016\u0002|.\u0012\u0011Q \t\u0005\u0003\u007f\u00149!\u0004\u0002\u0003\u0002)!!1\u0001B\u0003\u0003%)hn\u00195fG.,GM\u0003\u0002b-&!!\u0011\u0002B\u0001\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t=\u0001\u0003\u0002B\t\u0005/i!Aa\u0005\u000b\t\tU\u0011\u0011X\u0001\u0005Y\u0006tw-C\u0002\\\u0005'\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"A!\b\u0011\t\u0005%#qD\u0005\u0004\u0005C1&aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA(\u0005OA\u0011B!\u000b\u001d\u0003\u0003\u0005\rA!\b\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011y\u0003\u0005\u0004\u00032\t]\u0012qJ\u0007\u0003\u0005gQ1A!\u000eW\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005s\u0011\u0019D\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002B \u0005\u000b\u0002B!!\u0013\u0003B%\u0019!1\t,\u0003\u000f\t{w\u000e\\3b]\"I!\u0011\u0006\u0010\u0002\u0002\u0003\u0007\u0011qJ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003\u0010\t-\u0003\"\u0003B\u0015?\u0005\u0005\t\u0019\u0001B\u000f\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B\u000f)\t\u0011y!\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005\u007f\u00119\u0006C\u0005\u0003*\t\n\t\u00111\u0001\u0002P\u0005!A)\u0019;b!\r\ti\u000fJ\n\u0006I\t}\u0013\u0011\u0017\t\b\u0005C\u00129G[Av\u001b\t\u0011\u0019GC\u0002\u0003fY\u000bqA];oi&lW-\u0003\u0003\u0003j\t\r$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocQ\u0011!1L\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003W\u0014\t\bC\u0003iO\u0001\u0007!.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t]$Q\u0010\t\u0006\u0003\u0013\u0012IH[\u0005\u0004\u0005w2&AB(qi&|g\u000eC\u0005\u0003\u0000!\n\t\u00111\u0001\u0002l\u0006\u0019\u0001\u0010\n\u0019\u0002\u0011M\fg/Z%na2$BA!\"\u0003\fB!\u0011\u0011\nBD\u0013\r\u0011II\u0016\u0002\u0005+:LG\u000f\u0003\u0004\u0003\u000e&\u0002\r\u0001U\u0001\u0005a\u0006$\bNA\fNCb\f%m]*dC2,'/T8eK2\u0014V-\u00193feN\u0019!Fa%\u0011\t%\u0013)jQ\u0005\u0004\u0005/S%\u0001C'M%\u0016\fG-\u001a:\u0015\u0005\tm\u0005cAAgU\u0005I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004\u0013\u0001\u00027pC\u0012$2a\u0011BS\u0011\u0019\u0011iI\fa\u0001!\u0006!!/Z1e+\t\u0011\u0019\nK\u00020=\u0012$2a\u0011BX\u0011\u0019\u0011i\t\ra\u0001!\"\u001a\u0001G\u00183\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t]\u0006\u0003\u0002B\t\u0005sKAAa/\u0003\u0014\t1qJ\u00196fGRD3a\u00040eQ\rqa\f\u001a"
)
public class MaxAbsScalerModel extends Model implements MaxAbsScalerParams, MLWritable {
   private final String uid;
   private final Vector maxAbs;
   private Param outputCol;
   private Param inputCol;

   public static MaxAbsScalerModel load(final String path) {
      return MaxAbsScalerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MaxAbsScalerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return MaxAbsScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Vector maxAbs() {
      return this.maxAbs;
   }

   public MaxAbsScalerModel setInputCol(final String value) {
      return (MaxAbsScalerModel)this.set(this.inputCol(), value);
   }

   public MaxAbsScalerModel setOutputCol(final String value) {
      return (MaxAbsScalerModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      double[] scale = (double[]).MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.maxAbs().toArray()), (JFunction1.mcDD.sp)(v) -> v == (double)0 ? (double)1.0F : (double)1 / v, scala.reflect.ClassTag..MODULE$.Double());
      Function1 func = StandardScalerModel$.MODULE$.getTransformFunc((double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), scale, false, true);
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MaxAbsScalerModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MaxAbsScalerModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(func, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), this.maxAbs().size());
      }

      return outputSchema;
   }

   public MaxAbsScalerModel copy(final ParamMap extra) {
      MaxAbsScalerModel copied = new MaxAbsScalerModel(this.uid(), this.maxAbs());
      return (MaxAbsScalerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new MaxAbsScalerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "MaxAbsScalerModel: uid=" + var10000 + ", numFeatures=" + this.maxAbs().size();
   }

   public MaxAbsScalerModel(final String uid, final Vector maxAbs) {
      this.uid = uid;
      this.maxAbs = maxAbs;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MaxAbsScalerParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public MaxAbsScalerModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class MaxAbsScalerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final MaxAbsScalerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.maxAbs());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MaxAbsScalerModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.MaxAbsScalerModel.MaxAbsScalerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.MaxAbsScalerModel.MaxAbsScalerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public MaxAbsScalerModelWriter(final MaxAbsScalerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector maxAbs;
         // $FF: synthetic field
         public final MaxAbsScalerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector maxAbs() {
            return this.maxAbs;
         }

         public Data copy(final Vector maxAbs) {
            return this.org$apache$spark$ml$feature$MaxAbsScalerModel$MaxAbsScalerModelWriter$Data$$$outer().new Data(maxAbs);
         }

         public Vector copy$default$1() {
            return this.maxAbs();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.maxAbs();
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
                  return "maxAbs";
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
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$MaxAbsScalerModel$MaxAbsScalerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$MaxAbsScalerModel$MaxAbsScalerModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.maxAbs();
                        Vector var5 = var4.maxAbs();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
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

         // $FF: synthetic method
         public MaxAbsScalerModelWriter org$apache$spark$ml$feature$MaxAbsScalerModel$MaxAbsScalerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector maxAbs) {
            this.maxAbs = maxAbs;
            if (MaxAbsScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MaxAbsScalerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final MaxAbsScalerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector maxAbs) {
            return this.$outer.new Data(maxAbs);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.maxAbs()));
         }

         public Data$() {
            if (MaxAbsScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MaxAbsScalerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class MaxAbsScalerModelReader extends MLReader {
      private final String className = MaxAbsScalerModel.class.getName();

      private String className() {
         return this.className;
      }

      public MaxAbsScalerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row var6 = (Row)this.sparkSession().read().parquet(dataPath).select("maxAbs", scala.collection.immutable.Nil..MODULE$).head();
         if (var6 != null) {
            Some var7 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var6);
            if (!var7.isEmpty() && var7.get() != null && ((SeqOps)var7.get()).lengthCompare(1) == 0) {
               Object maxAbs = ((SeqOps)var7.get()).apply(0);
               if (maxAbs instanceof Vector) {
                  Vector var9 = (Vector)maxAbs;
                  MaxAbsScalerModel model = new MaxAbsScalerModel(metadata.uid(), var9);
                  metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                  return model;
               }
            }
         }

         throw new MatchError(var6);
      }

      public MaxAbsScalerModelReader() {
      }
   }
}
