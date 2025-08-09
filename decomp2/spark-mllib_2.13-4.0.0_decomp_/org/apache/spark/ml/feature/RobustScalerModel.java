package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasRelativeError;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.util.MLUtils$;
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
import scala.Tuple2;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t5g\u0001B\u001c9\u0001\rC\u0001b\u0015\u0001\u0003\u0006\u0004%\t\u0005\u0016\u0005\tW\u0002\u0011\t\u0011)A\u0005+\"AQ\u000e\u0001BC\u0002\u0013\u0005a\u000e\u0003\u0005w\u0001\t\u0005\t\u0015!\u0003p\u0011!A\bA!b\u0001\n\u0003q\u0007\u0002\u0003>\u0001\u0005\u0003\u0005\u000b\u0011B8\t\rq\u0004A\u0011\u0001\u001e~\u0011\u001da\b\u0001\"\u0001;\u0003\u0013Aq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0016\u0001!\t!a\u0006\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e!9\u0011q\r\u0001\u0005B\u0005%\u0004bBA>\u0001\u0011\u0005\u0013Q\u0010\u0005\b\u0003\u001f\u0003A\u0011IAI\u0011\u001d\tI\n\u0001C!\u00037;q!!)9\u0011\u0003\t\u0019K\u0002\u00048q!\u0005\u0011Q\u0015\u0005\u0007yF!\t!a1\u0007\u000f\u0005\u0015\u0017\u0003A\t\u0002H\"I\u0011\u0011Z\n\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\u0007yN!\t!a3\u0007\r\u0005M7\u0003RAk\u0011!igC!f\u0001\n\u0003q\u0007\u0002\u0003<\u0017\u0005#\u0005\u000b\u0011B8\t\u0011a4\"Q3A\u0005\u00029D\u0001B\u001f\f\u0003\u0012\u0003\u0006Ia\u001c\u0005\u0007yZ!\t!a;\t\u0013\u0005md#!A\u0005\u0002\u0005U\b\"CA~-E\u0005I\u0011AA\u007f\u0011%\u0011\tBFI\u0001\n\u0003\ti\u0010C\u0005\u0003\u0014Y\t\t\u0011\"\u0011\u0003\u0016!I!\u0011\u0005\f\u0002\u0002\u0013\u0005!1\u0005\u0005\n\u0005W1\u0012\u0011!C\u0001\u0005[A\u0011Ba\r\u0017\u0003\u0003%\tE!\u000e\t\u0013\t\rc#!A\u0005\u0002\t\u0015\u0003\"\u0003B(-\u0005\u0005I\u0011\tB)\u0011%\u0011)FFA\u0001\n\u0003\u00129\u0006C\u0005\u0002\u001aZ\t\t\u0011\"\u0011\u0003Z!I!1\f\f\u0002\u0002\u0013\u0005#QL\u0004\n\u0005C\u001a\u0012\u0011!E\u0005\u0005G2\u0011\"a5\u0014\u0003\u0003EIA!\u001a\t\rqLC\u0011\u0001B:\u0011%\tI*KA\u0001\n\u000b\u0012I\u0006C\u0005\u0003v%\n\t\u0011\"!\u0003x!I!QP\u0015\u0002\u0002\u0013\u0005%q\u0010\u0005\b\u0005#\u001bB\u0011\u000bBJ\r\u0019\u0011y*\u0005\u0003\u0003\"\"1Ap\fC\u0001\u0005SC\u0011B!,0\u0005\u0004%IA!\u0006\t\u0011\t=v\u0006)A\u0005\u0005/AqA!-0\t\u0003\u0012\u0019\fC\u0004\u00038F!\tE!/\t\u000f\tE\u0016\u0003\"\u0011\u0003<\"I!qX\t\u0002\u0002\u0013%!\u0011\u0019\u0002\u0012%>\u0014Wo\u001d;TG\u0006dWM]'pI\u0016d'BA\u001d;\u0003\u001d1W-\u0019;ve\u0016T!a\u000f\u001f\u0002\u00055d'BA\u001f?\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0004)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0003\u0006\u0019qN]4\u0004\u0001M!\u0001\u0001\u0012&N!\r)e\tS\u0007\u0002u%\u0011qI\u000f\u0002\u0006\u001b>$W\r\u001c\t\u0003\u0013\u0002i\u0011\u0001\u000f\t\u0003\u0013.K!\u0001\u0014\u001d\u0003%I{'-^:u'\u000e\fG.\u001a:QCJ\fWn\u001d\t\u0003\u001dFk\u0011a\u0014\u0006\u0003!j\nA!\u001e;jY&\u0011!k\u0014\u0002\u000b\u001b2;&/\u001b;bE2,\u0017aA;jIV\tQ\u000b\u0005\u0002W?:\u0011q+\u0018\t\u00031nk\u0011!\u0017\u0006\u00035\n\u000ba\u0001\u0010:p_Rt$\"\u0001/\u0002\u000bM\u001c\u0017\r\\1\n\u0005y[\u0016A\u0002)sK\u0012,g-\u0003\u0002aC\n11\u000b\u001e:j]\u001eT!AX.)\u0007\u0005\u0019\u0017\u000e\u0005\u0002eO6\tQM\u0003\u0002gy\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005!,'!B*j]\u000e,\u0017%\u00016\u0002\u000bMr\u0003G\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005\rL\u0017!\u0002:b]\u001e,W#A8\u0011\u0005A\u001cX\"A9\u000b\u0005IT\u0014A\u00027j]\u0006dw-\u0003\u0002uc\n1a+Z2u_JD3aA2j\u0003\u0019\u0011\u0018M\\4fA!\u001aAaY5\u0002\r5,G-[1oQ\r)1-[\u0001\b[\u0016$\u0017.\u00198!Q\r11-[\u0001\u0007y%t\u0017\u000e\u001e \u0015\r!s\u0018\u0011AA\u0003\u0011\u0015\u0019v\u00011\u0001VQ\rq8-\u001b\u0005\u0006[\u001e\u0001\ra\u001c\u0015\u0005\u0003\u0003\u0019\u0017\u000eC\u0003y\u000f\u0001\u0007q\u000e\u000b\u0003\u0002\u0006\rLG#\u0001%\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0005\u0003\u001f\t\t\"D\u0001\u0001\u0011\u0019\t\u0019\"\u0003a\u0001+\u0006)a/\u00197vK\u0006a1/\u001a;PkR\u0004X\u000f^\"pYR!\u0011qBA\r\u0011\u0019\t\u0019B\u0003a\u0001+\u0006IAO]1og\u001a|'/\u001c\u000b\u0005\u0003?\t\t\u0005\u0005\u0003\u0002\"\u0005mb\u0002BA\u0012\u0003kqA!!\n\u000229!\u0011qEA\u0018\u001d\u0011\tI#!\f\u000f\u0007a\u000bY#C\u0001B\u0013\ty\u0004)\u0003\u0002>}%\u0019\u00111\u0007\u001f\u0002\u0007M\fH.\u0003\u0003\u00028\u0005e\u0012a\u00029bG.\fw-\u001a\u0006\u0004\u0003ga\u0014\u0002BA\u001f\u0003\u007f\u0011\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\t\u0005]\u0012\u0011\b\u0005\b\u0003\u0007Z\u0001\u0019AA#\u0003\u001d!\u0017\r^1tKR\u0004D!a\u0012\u0002TA1\u0011\u0011JA&\u0003\u001fj!!!\u000f\n\t\u00055\u0013\u0011\b\u0002\b\t\u0006$\u0018m]3u!\u0011\t\t&a\u0015\r\u0001\u0011a\u0011QKA!\u0003\u0003\u0005\tQ!\u0001\u0002X\t\u0019q\f\n\u001a\u0012\t\u0005e\u0013\u0011\r\t\u0005\u00037\ni&D\u0001\\\u0013\r\tyf\u0017\u0002\b\u001d>$\b.\u001b8h!\u0011\tY&a\u0019\n\u0007\u0005\u00154LA\u0002B]f\fq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003W\n9\b\u0005\u0003\u0002n\u0005MTBAA8\u0015\u0011\t\t(!\u000f\u0002\u000bQL\b/Z:\n\t\u0005U\u0014q\u000e\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA=\u0019\u0001\u0007\u00111N\u0001\u0007g\u000eDW-\\1\u0002\t\r|\u0007/\u001f\u000b\u0004\u0011\u0006}\u0004bBAA\u001b\u0001\u0007\u00111Q\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u000b\u000bY)\u0004\u0002\u0002\b*\u0019\u0011\u0011\u0012\u001e\u0002\u000bA\f'/Y7\n\t\u00055\u0015q\u0011\u0002\t!\u0006\u0014\u0018-\\'ba\u0006)qO]5uKV\u0011\u00111\u0013\t\u0004\u001d\u0006U\u0015bAAL\u001f\nAQ\nT,sSR,'/\u0001\u0005u_N#(/\u001b8h)\u0005)\u0006fA\bdS\"\u001a\u0001aY5\u0002#I{'-^:u'\u000e\fG.\u001a:N_\u0012,G\u000e\u0005\u0002J#M9\u0011#a*\u0002.\u0006M\u0006\u0003BA.\u0003SK1!a+\\\u0005\u0019\te.\u001f*fMB!a*a,I\u0013\r\t\tl\u0014\u0002\u000b\u001b2\u0013V-\u00193bE2,\u0007\u0003BA[\u0003\u007fk!!a.\u000b\t\u0005e\u00161X\u0001\u0003S>T!!!0\u0002\t)\fg/Y\u0005\u0005\u0003\u0003\f9L\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002$\n9\"k\u001c2vgR\u001c6-\u00197fe6{G-\u001a7Xe&$XM]\n\u0004'\u0005M\u0015\u0001C5ogR\fgnY3\u0015\t\u00055\u0017\u0011\u001b\t\u0004\u0003\u001f\u001cR\"A\t\t\r\u0005%W\u00031\u0001I\u0005\u0011!\u0015\r^1\u0014\u000fY\t9+a6\u0002^B!\u00111LAm\u0013\r\tYn\u0017\u0002\b!J|G-^2u!\u0011\ty.a:\u000f\t\u0005\u0005\u0018Q\u001d\b\u00041\u0006\r\u0018\"\u0001/\n\u0007\u0005]2,\u0003\u0003\u0002B\u0006%(bAA\u001c7R1\u0011Q^Ay\u0003g\u00042!a<\u0017\u001b\u0005\u0019\u0002\"B7\u001c\u0001\u0004y\u0007\"\u0002=\u001c\u0001\u0004yGCBAw\u0003o\fI\u0010C\u0004n9A\u0005\t\u0019A8\t\u000fad\u0002\u0013!a\u0001_\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0000U\ry'\u0011A\u0016\u0003\u0005\u0007\u0001BA!\u0002\u0003\u000e5\u0011!q\u0001\u0006\u0005\u0005\u0013\u0011Y!A\u0005v]\u000eDWmY6fI*\u0011amW\u0005\u0005\u0005\u001f\u00119AA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005/\u0001BA!\u0007\u0003 5\u0011!1\u0004\u0006\u0005\u0005;\tY,\u0001\u0003mC:<\u0017b\u00011\u0003\u001c\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011!Q\u0005\t\u0005\u00037\u00129#C\u0002\u0003*m\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u0019\u00030!I!\u0011G\u0011\u0002\u0002\u0003\u0007!QE\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t]\u0002C\u0002B\u001d\u0005\u007f\t\t'\u0004\u0002\u0003<)\u0019!QH.\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003B\tm\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BAa\u0012\u0003NA!\u00111\fB%\u0013\r\u0011Ye\u0017\u0002\b\u0005>|G.Z1o\u0011%\u0011\tdIA\u0001\u0002\u0004\t\t'\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B\f\u0005'B\u0011B!\r%\u0003\u0003\u0005\rA!\n\u0002\u0011!\f7\u000f[\"pI\u0016$\"A!\n\u0015\u0005\t]\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0003H\t}\u0003\"\u0003B\u0019O\u0005\u0005\t\u0019AA1\u0003\u0011!\u0015\r^1\u0011\u0007\u0005=\u0018fE\u0003*\u0005O\n\u0019\f\u0005\u0005\u0003j\t=tn\\Aw\u001b\t\u0011YGC\u0002\u0003nm\u000bqA];oi&lW-\u0003\u0003\u0003r\t-$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011!1M\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0003[\u0014IHa\u001f\t\u000b5d\u0003\u0019A8\t\u000bad\u0003\u0019A8\u0002\u000fUt\u0017\r\u001d9msR!!\u0011\u0011BG!\u0019\tYFa!\u0003\b&\u0019!QQ.\u0003\r=\u0003H/[8o!\u0019\tYF!#p_&\u0019!1R.\u0003\rQ+\b\u000f\\33\u0011%\u0011y)LA\u0001\u0002\u0004\ti/A\u0002yIA\n\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005+\u0013Y\n\u0005\u0003\u0002\\\t]\u0015b\u0001BM7\n!QK\\5u\u0011\u0019\u0011iJ\fa\u0001+\u0006!\u0001/\u0019;i\u0005]\u0011vNY;tiN\u001b\u0017\r\\3s\u001b>$W\r\u001c*fC\u0012,'oE\u00020\u0005G\u0003BA\u0014BS\u0011&\u0019!qU(\u0003\u00115c%+Z1eKJ$\"Aa+\u0011\u0007\u0005=w&A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004\u0011\nU\u0006B\u0002BOg\u0001\u0007Q+\u0001\u0003sK\u0006$WC\u0001BR)\rA%Q\u0018\u0005\u0007\u0005;+\u0004\u0019A+\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t\r\u0007\u0003\u0002B\r\u0005\u000bLAAa2\u0003\u001c\t1qJ\u00196fGRD3!E2jQ\r\u00012-\u001b"
)
public class RobustScalerModel extends Model implements RobustScalerParams, MLWritable {
   private final String uid;
   private final Vector range;
   private final Vector median;
   private DoubleParam lower;
   private DoubleParam upper;
   private BooleanParam withCentering;
   private BooleanParam withScaling;
   private DoubleParam relativeError;
   private Param outputCol;
   private Param inputCol;

   public static RobustScalerModel load(final String path) {
      return RobustScalerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return RobustScalerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getLower() {
      return RobustScalerParams.getLower$(this);
   }

   public double getUpper() {
      return RobustScalerParams.getUpper$(this);
   }

   public boolean getWithCentering() {
      return RobustScalerParams.getWithCentering$(this);
   }

   public boolean getWithScaling() {
      return RobustScalerParams.getWithScaling$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return RobustScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final double getRelativeError() {
      return HasRelativeError.getRelativeError$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public DoubleParam lower() {
      return this.lower;
   }

   public DoubleParam upper() {
      return this.upper;
   }

   public BooleanParam withCentering() {
      return this.withCentering;
   }

   public BooleanParam withScaling() {
      return this.withScaling;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$lower_$eq(final DoubleParam x$1) {
      this.lower = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$upper_$eq(final DoubleParam x$1) {
      this.upper = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withCentering_$eq(final BooleanParam x$1) {
      this.withCentering = x$1;
   }

   public void org$apache$spark$ml$feature$RobustScalerParams$_setter_$withScaling_$eq(final BooleanParam x$1) {
      this.withScaling = x$1;
   }

   public final DoubleParam relativeError() {
      return this.relativeError;
   }

   public final void org$apache$spark$ml$param$shared$HasRelativeError$_setter_$relativeError_$eq(final DoubleParam x$1) {
      this.relativeError = x$1;
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

   public Vector range() {
      return this.range;
   }

   public Vector median() {
      return this.median;
   }

   public RobustScalerModel setInputCol(final String value) {
      return (RobustScalerModel)this.set(this.inputCol(), value);
   }

   public RobustScalerModel setOutputCol(final String value) {
      return (RobustScalerModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      double[] shift = BoxesRunTime.unboxToBoolean(this.$(this.withCentering())) ? this.median().toArray() : .MODULE$.emptyDoubleArray();
      double[] scale = BoxesRunTime.unboxToBoolean(this.$(this.withScaling())) ? (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(this.range().toArray()), (JFunction1.mcDD.sp)(v) -> v == (double)0 ? (double)0.0F : (double)1.0F / v, scala.reflect.ClassTag..MODULE$.Double()) : .MODULE$.emptyDoubleArray();
      Function1 func = StandardScalerModel$.MODULE$.getTransformFunc(shift, scale, BoxesRunTime.unboxToBoolean(this.$(this.withCentering())), BoxesRunTime.unboxToBoolean(this.$(this.withScaling())));
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RobustScalerModel.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RobustScalerModel.class.getClassLoader());

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
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), this.median().size());
      }

      return outputSchema;
   }

   public RobustScalerModel copy(final ParamMap extra) {
      RobustScalerModel copied = new RobustScalerModel(this.uid(), this.range(), this.median());
      return (RobustScalerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new RobustScalerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "RobustScalerModel: uid=" + var10000 + ", numFeatures=" + this.median().size() + ", withCentering=" + this.$(this.withCentering()) + ", withScaling=" + this.$(this.withScaling());
   }

   public RobustScalerModel(final String uid, final Vector range, final Vector median) {
      this.uid = uid;
      this.range = range;
      this.median = median;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasRelativeError.$init$(this);
      RobustScalerParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public RobustScalerModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class RobustScalerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final RobustScalerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.range(), this.instance.median());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(RobustScalerModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.RobustScalerModel.RobustScalerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.RobustScalerModel.RobustScalerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public RobustScalerModelWriter(final RobustScalerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector range;
         private final Vector median;
         // $FF: synthetic field
         public final RobustScalerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector range() {
            return this.range;
         }

         public Vector median() {
            return this.median;
         }

         public Data copy(final Vector range, final Vector median) {
            return this.org$apache$spark$ml$feature$RobustScalerModel$RobustScalerModelWriter$Data$$$outer().new Data(range, median);
         }

         public Vector copy$default$1() {
            return this.range();
         }

         public Vector copy$default$2() {
            return this.median();
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
                  return this.range();
               }
               case 1 -> {
                  return this.median();
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
                  return "range";
               }
               case 1 -> {
                  return "median";
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
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$RobustScalerModel$RobustScalerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$RobustScalerModel$RobustScalerModelWriter$Data$$$outer()) {
                     label50: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.range();
                        Vector var5 = var4.range();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        var10000 = this.median();
                        Vector var6 = var4.median();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var6)) {
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
         public RobustScalerModelWriter org$apache$spark$ml$feature$RobustScalerModel$RobustScalerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector range, final Vector median) {
            this.range = range;
            this.median = median;
            if (RobustScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = RobustScalerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final RobustScalerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector range, final Vector median) {
            return this.$outer.new Data(range, median);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.range(), x$0.median())));
         }

         public Data$() {
            if (RobustScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = RobustScalerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class RobustScalerModelReader extends MLReader {
      private final String className = RobustScalerModel.class.getName();

      private String className() {
         return this.className;
      }

      public RobustScalerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Row var7 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"range", "median"}))).select("range", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"median"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(2) == 0) {
               Object range = ((SeqOps)var8.get()).apply(0);
               Object median = ((SeqOps)var8.get()).apply(1);
               if (range instanceof Vector) {
                  Vector var11 = (Vector)range;
                  if (median instanceof Vector) {
                     Vector var12 = (Vector)median;
                     Tuple2 var6 = new Tuple2(var11, var12);
                     Vector range = (Vector)var6._1();
                     Vector median = (Vector)var6._2();
                     RobustScalerModel model = new RobustScalerModel(metadata.uid(), range, median);
                     metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                     return model;
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public RobustScalerModelReader() {
      }
   }
}
