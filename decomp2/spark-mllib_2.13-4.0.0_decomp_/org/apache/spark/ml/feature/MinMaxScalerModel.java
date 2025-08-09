package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
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
   bytes = "\u0006\u0005\r\ra\u0001B\u001d;\u0001\u0015C\u0001\"\u0016\u0001\u0003\u0006\u0004%\tE\u0016\u0005\t[\u0002\u0011\t\u0011)A\u0005/\"Aq\u000e\u0001BC\u0002\u0013\u0005\u0001\u000f\u0003\u0005{\u0001\t\u0005\t\u0015!\u0003r\u0011!a\bA!b\u0001\n\u0003\u0001\b\u0002\u0003@\u0001\u0005\u0003\u0005\u000b\u0011B9\t\u0011\u0005\u0005\u0001\u0001\"\u0001=\u0003\u0007A\u0001\"!\u0001\u0001\t\u0003a\u0014\u0011\u0003\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!a\n\u0001\t\u0003\tI\u0003C\u0004\u00028\u0001!\t!!\u000f\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B!9\u00111\u0012\u0001\u0005B\u00055\u0005bBAQ\u0001\u0011\u0005\u00131\u0015\u0005\b\u0003o\u0003A\u0011IA]\u0011\u001d\t9\r\u0001C!\u0003\u0013<q!a5;\u0011\u0003\t)N\u0002\u0004:u!\u0005\u0011q\u001b\u0005\b\u0003\u0003\u0019B\u0011AA{\r\u001d\t9p\u0005\u0001\u0014\u0003sD\u0011\"a?\u0016\u0005\u0003\u0005\u000b\u0011\u0002&\t\u000f\u0005\u0005Q\u0003\"\u0001\u0002~\u001a1!QA\u000bE\u0005\u000fA\u0001b\u001c\r\u0003\u0016\u0004%\t\u0001\u001d\u0005\tub\u0011\t\u0012)A\u0005c\"AA\u0010\u0007BK\u0002\u0013\u0005\u0001\u000f\u0003\u0005\u007f1\tE\t\u0015!\u0003r\u0011\u001d\t\t\u0001\u0007C\u0001\u0005;A\u0011\"!)\u0019\u0003\u0003%\tAa\n\t\u0013\t5\u0002$%A\u0005\u0002\t=\u0002\"\u0003B\"1E\u0005I\u0011\u0001B\u0018\u0011%\u0011)\u0005GA\u0001\n\u0003\u00129\u0005C\u0005\u0003Ta\t\t\u0011\"\u0001\u0003V!I!Q\f\r\u0002\u0002\u0013\u0005!q\f\u0005\n\u0005KB\u0012\u0011!C!\u0005OB\u0011B!\u001e\u0019\u0003\u0003%\tAa\u001e\t\u0013\t\u0005\u0005$!A\u0005B\t\r\u0005\"\u0003BD1\u0005\u0005I\u0011\tBE\u0011%\t9\rGA\u0001\n\u0003\u0012Y\tC\u0005\u0003\u000eb\t\t\u0011\"\u0011\u0003\u0010\u001eI!1S\u000b\u0002\u0002#%!Q\u0013\u0004\n\u0005\u000b)\u0012\u0011!E\u0005\u0005/Cq!!\u0001,\t\u0003\u0011)\u000bC\u0005\u0002H.\n\t\u0011\"\u0012\u0003\f\"I!qU\u0016\u0002\u0002\u0013\u0005%\u0011\u0016\u0005\n\u0005_[\u0013\u0011!CA\u0005cCqAa1\u0016\t#\u0012)M\u0002\u0004\u0003RN!!1\u001b\u0005\b\u0003\u0003\tD\u0011\u0001Bn\u0011%\u0011y.\rb\u0001\n\u0013\u00119\u0005\u0003\u0005\u0003bF\u0002\u000b\u0011\u0002B%\u0011\u001d\u0011\u0019/\rC!\u0005KDqA!;\u0014\t\u0003\u0012Y\u000fC\u0004\u0003dN!\tEa<\t\u0013\tU8#!A\u0005\n\t](!E'j]6\u000b\u0007pU2bY\u0016\u0014Xj\u001c3fY*\u00111\bP\u0001\bM\u0016\fG/\u001e:f\u0015\tid(\u0001\u0002nY*\u0011q\bQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0003\n\u000ba!\u00199bG\",'\"A\"\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011Ej\u0014\t\u0004\u000f\"SU\"\u0001\u001f\n\u0005%c$!B'pI\u0016d\u0007CA&\u0001\u001b\u0005Q\u0004CA&N\u0013\tq%H\u0001\nNS:l\u0015\r_*dC2,'\u000fU1sC6\u001c\bC\u0001)T\u001b\u0005\t&B\u0001*=\u0003\u0011)H/\u001b7\n\u0005Q\u000b&AC'M/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003]\u0003\"\u0001W1\u000f\u0005e{\u0006C\u0001.^\u001b\u0005Y&B\u0001/E\u0003\u0019a$o\\8u})\ta,A\u0003tG\u0006d\u0017-\u0003\u0002a;\u00061\u0001K]3eK\u001aL!AY2\u0003\rM#(/\u001b8h\u0015\t\u0001W\fK\u0002\u0002K.\u0004\"AZ5\u000e\u0003\u001dT!\u0001\u001b \u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002kO\n)1+\u001b8dK\u0006\nA.A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002fW\u0006YqN]5hS:\fG.T5o+\u0005\t\bC\u0001:v\u001b\u0005\u0019(B\u0001;=\u0003\u0019a\u0017N\\1mO&\u0011ao\u001d\u0002\u0007-\u0016\u001cGo\u001c:)\u0007\r)\u00070I\u0001z\u0003\u0015\u0011d\u0006\r\u00181\u00031y'/[4j]\u0006dW*\u001b8!Q\r!Q\r_\u0001\f_JLw-\u001b8bY6\u000b\u0007\u0010K\u0002\u0006Kb\fAb\u001c:jO&t\u0017\r\\'bq\u0002B3AB3y\u0003\u0019a\u0014N\\5u}Q9!*!\u0002\u0002\n\u00055\u0001\"B+\b\u0001\u00049\u0006\u0006BA\u0003K.DQa\\\u0004A\u0002EDC!!\u0003fq\")Ap\u0002a\u0001c\"\"\u0011QB3y)\u0005Q\u0015aC:fi&s\u0007/\u001e;D_2$B!a\u0006\u0002\u001a5\t\u0001\u0001\u0003\u0004\u0002\u001c%\u0001\raV\u0001\u0006m\u0006dW/\u001a\u0015\u0004\u0013\u0015\\\u0017\u0001D:fi>+H\u000f];u\u0007>dG\u0003BA\f\u0003GAa!a\u0007\u000b\u0001\u00049\u0006f\u0001\u0006fW\u000611/\u001a;NS:$B!a\u0006\u0002,!9\u00111D\u0006A\u0002\u00055\u0002\u0003BA\u0018\u0003ci\u0011!X\u0005\u0004\u0003gi&A\u0002#pk\ndW\rK\u0002\fK.\faa]3u\u001b\u0006DH\u0003BA\f\u0003wAq!a\u0007\r\u0001\u0004\ti\u0003K\u0002\rK.\f\u0011\u0002\u001e:b]N4wN]7\u0015\t\u0005\r\u0013Q\r\t\u0005\u0003\u000b\nyF\u0004\u0003\u0002H\u0005ec\u0002BA%\u0003+rA!a\u0013\u0002T9!\u0011QJA)\u001d\rQ\u0016qJ\u0005\u0002\u0007&\u0011\u0011IQ\u0005\u0003\u007f\u0001K1!a\u0016?\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u00037\ni&A\u0004qC\u000e\\\u0017mZ3\u000b\u0007\u0005]c(\u0003\u0003\u0002b\u0005\r$!\u0003#bi\u00064%/Y7f\u0015\u0011\tY&!\u0018\t\u000f\u0005\u001dT\u00021\u0001\u0002j\u00059A-\u0019;bg\u0016$\b\u0007BA6\u0003o\u0002b!!\u001c\u0002p\u0005MTBAA/\u0013\u0011\t\t(!\u0018\u0003\u000f\u0011\u000bG/Y:fiB!\u0011QOA<\u0019\u0001!A\"!\u001f\u0002f\u0005\u0005\t\u0011!B\u0001\u0003w\u00121a\u0018\u00133#\u0011\ti(a!\u0011\t\u0005=\u0012qP\u0005\u0004\u0003\u0003k&a\u0002(pi\"Lgn\u001a\t\u0005\u0003_\t))C\u0002\u0002\bv\u00131!\u00118zQ\riQ\r_\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011qRAN!\u0011\t\t*a&\u000e\u0005\u0005M%\u0002BAK\u0003;\nQ\u0001^=qKNLA!!'\u0002\u0014\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005ue\u00021\u0001\u0002\u0010\u000611o\u00195f[\u0006D3AD3l\u0003\u0011\u0019w\u000e]=\u0015\u0007)\u000b)\u000bC\u0004\u0002(>\u0001\r!!+\u0002\u000b\u0015DHO]1\u0011\t\u0005-\u0016\u0011W\u0007\u0003\u0003[S1!a,=\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t\u0019,!,\u0003\u0011A\u000b'/Y7NCBD3aD3l\u0003\u00159(/\u001b;f+\t\tY\fE\u0002Q\u0003{K1!a0R\u0005!iEj\u0016:ji\u0016\u0014\b\u0006\u0002\tf\u0003\u0007\f#!!2\u0002\u000bErcG\f\u0019\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\u0016\u0015\u0005#\u0015\fi-\t\u0002\u0002P\u0006)1G\f\u0019/a!\u001a\u0001!Z6\u0002#5Kg.T1y'\u000e\fG.\u001a:N_\u0012,G\u000e\u0005\u0002L'M91#!7\u0002`\u0006\u0015\b\u0003BA\u0018\u00037L1!!8^\u0005\u0019\te.\u001f*fMB!\u0001+!9K\u0013\r\t\u0019/\u0015\u0002\u000b\u001b2\u0013V-\u00193bE2,\u0007\u0003BAt\u0003cl!!!;\u000b\t\u0005-\u0018Q^\u0001\u0003S>T!!a<\u0002\t)\fg/Y\u0005\u0005\u0003g\fIO\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002V\n9R*\u001b8NCb\u001c6-\u00197fe6{G-\u001a7Xe&$XM]\n\u0004+\u0005m\u0016\u0001C5ogR\fgnY3\u0015\t\u0005}(1\u0001\t\u0004\u0005\u0003)R\"A\n\t\r\u0005mx\u00031\u0001K\u0005\u0011!\u0015\r^1\u0014\u000fa\tIN!\u0003\u0003\u0010A!\u0011q\u0006B\u0006\u0013\r\u0011i!\u0018\u0002\b!J|G-^2u!\u0011\u0011\tB!\u0007\u000f\t\tM!q\u0003\b\u00045\nU\u0011\"\u00010\n\u0007\u0005mS,\u0003\u0003\u0002t\nm!bAA.;R1!q\u0004B\u0012\u0005K\u00012A!\t\u0019\u001b\u0005)\u0002\"B8\u001e\u0001\u0004\t\b\"\u0002?\u001e\u0001\u0004\tHC\u0002B\u0010\u0005S\u0011Y\u0003C\u0004p=A\u0005\t\u0019A9\t\u000fqt\u0002\u0013!a\u0001c\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001B\u0019U\r\t(1G\u0016\u0003\u0005k\u0001BAa\u000e\u0003@5\u0011!\u0011\b\u0006\u0005\u0005w\u0011i$A\u0005v]\u000eDWmY6fI*\u0011\u0001.X\u0005\u0005\u0005\u0003\u0012IDA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u0013\u0002BAa\u0013\u0003R5\u0011!Q\n\u0006\u0005\u0005\u001f\ni/\u0001\u0003mC:<\u0017b\u00012\u0003N\u0005a\u0001O]8ek\u000e$\u0018I]5usV\u0011!q\u000b\t\u0005\u0003_\u0011I&C\u0002\u0003\\u\u00131!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a!\u0003b!I!1M\u0012\u0002\u0002\u0003\u0007!qK\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\t%\u0004C\u0002B6\u0005c\n\u0019)\u0004\u0002\u0003n)\u0019!qN/\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003t\t5$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\u001f\u0003\u0000A!\u0011q\u0006B>\u0013\r\u0011i(\u0018\u0002\b\u0005>|G.Z1o\u0011%\u0011\u0019'JA\u0001\u0002\u0004\t\u0019)\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002B%\u0005\u000bC\u0011Ba\u0019'\u0003\u0003\u0005\rAa\u0016\u0002\u0011!\f7\u000f[\"pI\u0016$\"Aa\u0016\u0015\u0005\t%\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0003z\tE\u0005\"\u0003B2S\u0005\u0005\t\u0019AAB\u0003\u0011!\u0015\r^1\u0011\u0007\t\u00052fE\u0003,\u00053\u000b)\u000f\u0005\u0005\u0003\u001c\n\u0005\u0016/\u001dB\u0010\u001b\t\u0011iJC\u0002\u0003 v\u000bqA];oi&lW-\u0003\u0003\u0003$\nu%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011!QS\u0001\u0006CB\u0004H.\u001f\u000b\u0007\u0005?\u0011YK!,\t\u000b=t\u0003\u0019A9\t\u000bqt\u0003\u0019A9\u0002\u000fUt\u0017\r\u001d9msR!!1\u0017B`!\u0019\tyC!.\u0003:&\u0019!qW/\u0003\r=\u0003H/[8o!\u0019\tyCa/rc&\u0019!QX/\u0003\rQ+\b\u000f\\33\u0011%\u0011\tmLA\u0001\u0002\u0004\u0011y\"A\u0002yIA\n\u0001b]1wK&k\u0007\u000f\u001c\u000b\u0005\u0005\u000f\u0014i\r\u0005\u0003\u00020\t%\u0017b\u0001Bf;\n!QK\\5u\u0011\u0019\u0011y\r\ra\u0001/\u0006!\u0001/\u0019;i\u0005]i\u0015N\\'bqN\u001b\u0017\r\\3s\u001b>$W\r\u001c*fC\u0012,'oE\u00022\u0005+\u0004B\u0001\u0015Bl\u0015&\u0019!\u0011\\)\u0003\u00115c%+Z1eKJ$\"A!8\u0011\u0007\t\u0005\u0011'A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0002\t1|\u0017\r\u001a\u000b\u0004\u0015\n\u001d\bB\u0002Bhk\u0001\u0007q+\u0001\u0003sK\u0006$WC\u0001BkQ\u00111T-a1\u0015\u0007)\u0013\t\u0010\u0003\u0004\u0003P^\u0002\ra\u0016\u0015\u0005o\u0015\f\u0019-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003zB!!1\nB~\u0013\u0011\u0011iP!\u0014\u0003\r=\u0013'.Z2uQ\u0011\u0019R-a1)\tI)\u00171\u0019"
)
public class MinMaxScalerModel extends Model implements MinMaxScalerParams, MLWritable {
   private final String uid;
   private final Vector originalMin;
   private final Vector originalMax;
   private DoubleParam min;
   private DoubleParam max;
   private Param outputCol;
   private Param inputCol;

   public static MinMaxScalerModel load(final String path) {
      return MinMaxScalerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return MinMaxScalerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getMin() {
      return MinMaxScalerParams.getMin$(this);
   }

   public double getMax() {
      return MinMaxScalerParams.getMax$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return MinMaxScalerParams.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public DoubleParam min() {
      return this.min;
   }

   public DoubleParam max() {
      return this.max;
   }

   public void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$min_$eq(final DoubleParam x$1) {
      this.min = x$1;
   }

   public void org$apache$spark$ml$feature$MinMaxScalerParams$_setter_$max_$eq(final DoubleParam x$1) {
      this.max = x$1;
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

   public Vector originalMin() {
      return this.originalMin;
   }

   public Vector originalMax() {
      return this.originalMax;
   }

   public MinMaxScalerModel setInputCol(final String value) {
      return (MinMaxScalerModel)this.set(this.inputCol(), value);
   }

   public MinMaxScalerModel setOutputCol(final String value) {
      return (MinMaxScalerModel)this.set(this.outputCol(), value);
   }

   public MinMaxScalerModel setMin(final double value) {
      return (MinMaxScalerModel)this.set(this.min(), BoxesRunTime.boxToDouble(value));
   }

   public MinMaxScalerModel setMax(final double value) {
      return (MinMaxScalerModel)this.set(this.max(), BoxesRunTime.boxToDouble(value));
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      int numFeatures = this.originalMax().size();
      double scale = BoxesRunTime.unboxToDouble(this.$(this.max())) - BoxesRunTime.unboxToDouble(this.$(this.min()));
      double minValue = BoxesRunTime.unboxToDouble(this.$(this.min()));
      double constantOutput = (BoxesRunTime.unboxToDouble(this.$(this.min())) + BoxesRunTime.unboxToDouble(this.$(this.max()))) / (double)2;
      double[] minArray = this.originalMin().toArray();
      double[] scaleArray = (double[]).MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> {
         double range = this.originalMax().apply(i) - this.originalMin().apply(i);
         return range != (double)0 ? scale / range : (double)0.0F;
      }, scala.reflect.ClassTag..MODULE$.Double());
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (vector) -> {
         scala.Predef..MODULE$.require(vector.size() == numFeatures, () -> "Number of features must be " + numFeatures + " but got " + vector.size());
         double[] values = vector.toArray();

         for(int i = 0; i < numFeatures; ++i) {
            if (!Double.isNaN(values[i])) {
               if (scaleArray[i] != (double)0) {
                  values[i] = (values[i] - minArray[i]) * scaleArray[i] + minValue;
               } else {
                  values[i] = constantOutput;
               }
            }
         }

         return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(values).compressed();
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MinMaxScalerModel.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MinMaxScalerModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction transformer = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), transformer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), this.originalMin().size());
      }

      return outputSchema;
   }

   public MinMaxScalerModel copy(final ParamMap extra) {
      MinMaxScalerModel copied = new MinMaxScalerModel(this.uid(), this.originalMin(), this.originalMax());
      return (MinMaxScalerModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new MinMaxScalerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "MinMaxScalerModel: uid=" + var10000 + ", numFeatures=" + this.originalMin().size() + ", min=" + this.$(this.min()) + ", max=" + this.$(this.max());
   }

   public MinMaxScalerModel(final String uid, final Vector originalMin, final Vector originalMax) {
      this.uid = uid;
      this.originalMin = originalMin;
      this.originalMax = originalMax;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      MinMaxScalerParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public MinMaxScalerModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), org.apache.spark.ml.linalg.Vectors..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class MinMaxScalerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final MinMaxScalerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.originalMin(), this.instance.originalMax());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(MinMaxScalerModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.MinMaxScalerModel.MinMaxScalerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.MinMaxScalerModel.MinMaxScalerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public MinMaxScalerModelWriter(final MinMaxScalerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector originalMin;
         private final Vector originalMax;
         // $FF: synthetic field
         public final MinMaxScalerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector originalMin() {
            return this.originalMin;
         }

         public Vector originalMax() {
            return this.originalMax;
         }

         public Data copy(final Vector originalMin, final Vector originalMax) {
            return this.org$apache$spark$ml$feature$MinMaxScalerModel$MinMaxScalerModelWriter$Data$$$outer().new Data(originalMin, originalMax);
         }

         public Vector copy$default$1() {
            return this.originalMin();
         }

         public Vector copy$default$2() {
            return this.originalMax();
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
                  return this.originalMin();
               }
               case 1 -> {
                  return this.originalMax();
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
                  return "originalMin";
               }
               case 1 -> {
                  return "originalMax";
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
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$MinMaxScalerModel$MinMaxScalerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$MinMaxScalerModel$MinMaxScalerModelWriter$Data$$$outer()) {
                     label50: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.originalMin();
                        Vector var5 = var4.originalMin();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label50;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label50;
                        }

                        var10000 = this.originalMax();
                        Vector var6 = var4.originalMax();
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
         public MinMaxScalerModelWriter org$apache$spark$ml$feature$MinMaxScalerModel$MinMaxScalerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector originalMin, final Vector originalMax) {
            this.originalMin = originalMin;
            this.originalMax = originalMax;
            if (MinMaxScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MinMaxScalerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final MinMaxScalerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector originalMin, final Vector originalMax) {
            return this.$outer.new Data(originalMin, originalMax);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.originalMin(), x$0.originalMax())));
         }

         public Data$() {
            if (MinMaxScalerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = MinMaxScalerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class MinMaxScalerModelReader extends MLReader {
      private final String className = MinMaxScalerModel.class.getName();

      private String className() {
         return this.className;
      }

      public MinMaxScalerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Row var7 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"originalMin", "originalMax"}))).select("originalMin", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"originalMax"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(2) == 0) {
               Object originalMin = ((SeqOps)var8.get()).apply(0);
               Object originalMax = ((SeqOps)var8.get()).apply(1);
               if (originalMin instanceof Vector) {
                  Vector var11 = (Vector)originalMin;
                  if (originalMax instanceof Vector) {
                     Vector var12 = (Vector)originalMax;
                     Tuple2 var6 = new Tuple2(var11, var12);
                     Vector originalMin = (Vector)var6._1();
                     Vector originalMax = (Vector)var6._2();
                     MinMaxScalerModel model = new MinMaxScalerModel(metadata.uid(), originalMin, originalMax);
                     metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                     return model;
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public MinMaxScalerModelReader() {
      }
   }
}
