package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.SparseVector.;
import org.apache.spark.ml.param.IntParam;
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
import org.apache.spark.mllib.linalg.Vectors$;
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
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rua\u0001\u0002\u001e<\u0001\u0019C\u0001B\u0016\u0001\u0003\u0006\u0004%\te\u0016\u0005\t]\u0002\u0011\t\u0011)A\u00051\"A\u0001\u000f\u0001B\u0001B\u0003%\u0011\u000f\u0003\u0004x\u0001\u0011\u0005Q\b\u001f\u0005\u0007o\u0002!\t!\u0010?\t\u000bu\u0004A\u0011\u0001@\t\u000f\u0005\u001d\u0001\u0001\"\u0001\u0002\n!9\u0011q\u0002\u0001\u0005B\u0005E\u0001bBA1\u0001\u0011\u0005\u00131\r\u0005\b\u0003o\u0002A\u0011IA=\u0011\u001d\t\t\n\u0001C\u0001\u0003'Cq!a)\u0001\t\u0003\t)\u000bC\u0004\u0002:\u0002!\t!a/\t\u000f\u0005}\u0006\u0001\"\u0011\u0002B\"9\u0011q\u001a\u0001\u0005B\u0005EwaBAlw!\u0005\u0011\u0011\u001c\u0004\u0007umB\t!a7\t\r]\fB\u0011AA}\r\u001d\tY0\u0005\u0001\u0012\u0003{D\u0011\"a@\u0014\u0005\u0003\u0005\u000b\u0011B&\t\r]\u001cB\u0011\u0001B\u0001\r\u0019\u0011Ia\u0005#\u0003\f!Q\u0011\u0011\u0013\f\u0003\u0016\u0004%\t!a%\t\u0015\t\u0005bC!E!\u0002\u0013\t)\n\u0003\u0006\u0002$Z\u0011)\u001a!C\u0001\u0003KC!Ba\t\u0017\u0005#\u0005\u000b\u0011BAT\u0011)\tIL\u0006BK\u0002\u0013\u0005\u00111\u0018\u0005\u000b\u0005K1\"\u0011#Q\u0001\n\u00055\u0006BB<\u0017\t\u0003\u00119\u0003C\u0005\u0002xY\t\t\u0011\"\u0001\u00034!I!1\b\f\u0012\u0002\u0013\u0005!Q\b\u0005\n\u0005#2\u0012\u0013!C\u0001\u0005'B\u0011Ba\u0016\u0017#\u0003%\tA!\u0017\t\u0013\tuc#!A\u0005B\t}\u0003\"\u0003B6-\u0005\u0005I\u0011\u0001B7\u0011%\u0011)HFA\u0001\n\u0003\u00119\bC\u0005\u0003~Y\t\t\u0011\"\u0011\u0003\u0000!I!Q\u0012\f\u0002\u0002\u0013\u0005!q\u0012\u0005\n\u000533\u0012\u0011!C!\u00057C\u0011Ba(\u0017\u0003\u0003%\tE!)\t\u0013\u0005=g#!A\u0005B\t\r\u0006\"\u0003BS-\u0005\u0005I\u0011\tBT\u000f%\u0011YkEA\u0001\u0012\u0013\u0011iKB\u0005\u0003\nM\t\t\u0011#\u0003\u00030\"1q\u000f\fC\u0001\u0005{C\u0011\"a4-\u0003\u0003%)Ea)\t\u0013\t}F&!A\u0005\u0002\n\u0005\u0007\"\u0003BeY\u0005\u0005I\u0011\u0011Bf\u0011\u001d\u0011in\u0005C)\u0005?4aAa;\u0012\t\t5\bBB<3\t\u0003\u0011)\u0010C\u0005\u0003zJ\u0012\r\u0011\"\u0003\u0003`!A!1 \u001a!\u0002\u0013\u0011\t\u0007C\u0004\u0003~J\"\tEa@\t\u000f\r\r\u0011\u0003\"\u0011\u0004\u0006!9!Q`\t\u0005B\r%\u0001\"CB\b#\u0005\u0005I\u0011BB\t\u0005!IEIR'pI\u0016d'B\u0001\u001f>\u0003\u001d1W-\u0019;ve\u0016T!AP \u0002\u00055d'B\u0001!B\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00115)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\t\u0006\u0019qN]4\u0004\u0001M!\u0001aR'Q!\rA\u0015jS\u0007\u0002{%\u0011!*\u0010\u0002\u0006\u001b>$W\r\u001c\t\u0003\u0019\u0002i\u0011a\u000f\t\u0003\u0019:K!aT\u001e\u0003\u000f%#eIQ1tKB\u0011\u0011\u000bV\u0007\u0002%*\u00111+P\u0001\u0005kRLG.\u0003\u0002V%\nQQ\nT,sSR\f'\r\\3\u0002\u0007ULG-F\u0001Y!\tI&M\u0004\u0002[AB\u00111LX\u0007\u00029*\u0011Q,R\u0001\u0007yI|w\u000e\u001e \u000b\u0003}\u000bQa]2bY\u0006L!!\u00190\u0002\rA\u0013X\rZ3g\u0013\t\u0019GM\u0001\u0004TiJLgn\u001a\u0006\u0003CzC3!\u00014m!\t9'.D\u0001i\u0015\tIw(\u0001\u0006b]:|G/\u0019;j_:L!a\u001b5\u0003\u000bMKgnY3\"\u00035\fQ!\r\u00185]A\nA!^5eA!\u001a!A\u001a7\u0002\u0011%$g-T8eK2\u0004\"A\u001d<\u000e\u0003MT!\u0001\u0010;\u000b\u0005U|\u0014!B7mY&\u0014\u0017B\u0001\u001et\u0003\u0019a\u0014N\\5u}Q\u00191*_>\t\u000bY#\u0001\u0019\u0001-)\u0007e4G\u000eC\u0003q\t\u0001\u0007\u0011\u000fF\u0001L\u0003-\u0019X\r^%oaV$8i\u001c7\u0015\u0007}\f\t!D\u0001\u0001\u0011\u0019\t\u0019A\u0002a\u00011\u0006)a/\u00197vK\"\u001aaA\u001a7\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0007}\fY\u0001\u0003\u0004\u0002\u0004\u001d\u0001\r\u0001\u0017\u0015\u0004\u000f\u0019d\u0017!\u0003;sC:\u001chm\u001c:n)\u0011\t\u0019\"!\u000e\u0011\t\u0005U\u0011q\u0006\b\u0005\u0003/\tIC\u0004\u0003\u0002\u001a\u0005\u0015b\u0002BA\u000e\u0003GqA!!\b\u0002\"9\u00191,a\b\n\u0003\u0011K!AQ\"\n\u0005\u0001\u000b\u0015bAA\u0014\u007f\u0005\u00191/\u001d7\n\t\u0005-\u0012QF\u0001\ba\u0006\u001c7.Y4f\u0015\r\t9cP\u0005\u0005\u0003c\t\u0019DA\u0005ECR\fgI]1nK*!\u00111FA\u0017\u0011\u001d\t9\u0004\u0003a\u0001\u0003s\tq\u0001Z1uCN,G\u000f\r\u0003\u0002<\u0005\u001d\u0003CBA\u001f\u0003\u007f\t\u0019%\u0004\u0002\u0002.%!\u0011\u0011IA\u0017\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0012\u0002H1\u0001A\u0001DA%\u0003k\t\t\u0011!A\u0003\u0002\u0005-#aA0%eE!\u0011QJA+!\u0011\ty%!\u0015\u000e\u0003yK1!a\u0015_\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!a\u0014\u0002X%\u0019\u0011\u0011\f0\u0003\u0007\u0005s\u0017\u0010\u000b\u0003\tM\u0006u\u0013EAA0\u0003\u0015\u0011d\u0006\r\u00181\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA3\u0003c\u0002B!a\u001a\u0002n5\u0011\u0011\u0011\u000e\u0006\u0005\u0003W\ni#A\u0003usB,7/\u0003\u0003\u0002p\u0005%$AC*ueV\u001cG\u000fV=qK\"9\u00111O\u0005A\u0002\u0005\u0015\u0014AB:dQ\u0016l\u0017\rK\u0002\nM2\fAaY8qsR\u00191*a\u001f\t\u000f\u0005u$\u00021\u0001\u0002\u0000\u0005)Q\r\u001f;sCB!\u0011\u0011QAD\u001b\t\t\u0019IC\u0002\u0002\u0006v\nQ\u0001]1sC6LA!!#\u0002\u0004\nA\u0001+\u0019:b[6\u000b\u0007\u000f\u000b\u0003\u000bM\u00065\u0015EAAH\u0003\u0015\td\u0006\u000e\u00182\u0003\rIGMZ\u000b\u0003\u0003+\u0003B!a&\u0002\u001e6\u0011\u0011\u0011\u0014\u0006\u0004\u00037k\u0014A\u00027j]\u0006dw-\u0003\u0003\u0002 \u0006e%A\u0002,fGR|'\u000f\u000b\u0003\fM\u0006u\u0013a\u00023pG\u001a\u0013X-]\u000b\u0003\u0003O\u0003b!a\u0014\u0002*\u00065\u0016bAAV=\n)\u0011I\u001d:bsB!\u0011qJAX\u0013\r\t\tL\u0018\u0002\u0005\u0019>tw\r\u000b\u0003\rM\u0006U\u0016EAA\\\u0003\u0015\u0019d\u0006\r\u00181\u0003\u001dqW/\u001c#pGN,\"!!,)\t51\u0017QW\u0001\u0006oJLG/Z\u000b\u0003\u0003\u0007\u00042!UAc\u0013\r\t9M\u0015\u0002\t\u001b2;&/\u001b;fe\"\"aBZAfC\t\ti-A\u00032]Yr\u0003'\u0001\u0005u_N#(/\u001b8h)\u0005A\u0006\u0006B\bg\u0003kC3\u0001\u00014m\u0003!IEIR'pI\u0016d\u0007C\u0001'\u0012'\u001d\t\u0012Q\\Ar\u0003S\u0004B!a\u0014\u0002`&\u0019\u0011\u0011\u001d0\u0003\r\u0005s\u0017PU3g!\u0011\t\u0016Q]&\n\u0007\u0005\u001d(K\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004B!a;\u0002v6\u0011\u0011Q\u001e\u0006\u0005\u0003_\f\t0\u0001\u0002j_*\u0011\u00111_\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002x\u00065(\u0001D*fe&\fG.\u001b>bE2,GCAAm\u00059IEIR'pI\u0016dwK]5uKJ\u001c2aEAb\u0003!Ign\u001d;b]\u000e,G\u0003\u0002B\u0002\u0005\u000f\u00012A!\u0002\u0014\u001b\u0005\t\u0002BBA\u0000+\u0001\u00071J\u0001\u0003ECR\f7c\u0002\f\u0002^\n5!1\u0003\t\u0005\u0003\u001f\u0012y!C\u0002\u0003\u0012y\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0003\u0016\tua\u0002\u0002B\f\u00057q1a\u0017B\r\u0013\u0005y\u0016bAA\u0016=&!\u0011q\u001fB\u0010\u0015\r\tYCX\u0001\u0005S\u00124\u0007%\u0001\u0005e_\u000e4%/Z9!\u0003!qW/\u001c#pGN\u0004C\u0003\u0003B\u0015\u0005[\u0011yC!\r\u0011\u0007\t-b#D\u0001\u0014\u0011\u001d\t\t*\ba\u0001\u0003+Cq!a)\u001e\u0001\u0004\t9\u000bC\u0004\u0002:v\u0001\r!!,\u0015\u0011\t%\"Q\u0007B\u001c\u0005sA\u0011\"!%\u001f!\u0003\u0005\r!!&\t\u0013\u0005\rf\u0004%AA\u0002\u0005\u001d\u0006\"CA]=A\u0005\t\u0019AAW\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa\u0010+\t\u0005U%\u0011I\u0016\u0003\u0005\u0007\u0002BA!\u0012\u0003N5\u0011!q\t\u0006\u0005\u0005\u0013\u0012Y%A\u0005v]\u000eDWmY6fI*\u0011\u0011NX\u0005\u0005\u0005\u001f\u00129EA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0006\u0002\u0003V)\"\u0011q\u0015B!\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"Aa\u0017+\t\u00055&\u0011I\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u0005\u0004\u0003\u0002B2\u0005Sj!A!\u001a\u000b\t\t\u001d\u0014\u0011_\u0001\u0005Y\u0006tw-C\u0002d\u0005K\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"Aa\u001c\u0011\t\u0005=#\u0011O\u0005\u0004\u0005gr&aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA+\u0005sB\u0011Ba\u001f%\u0003\u0003\u0005\rAa\u001c\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011\t\t\u0005\u0004\u0003\u0004\n%\u0015QK\u0007\u0003\u0005\u000bS1Aa\"_\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005\u0017\u0013)I\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002BI\u0005/\u0003B!a\u0014\u0003\u0014&\u0019!Q\u00130\u0003\u000f\t{w\u000e\\3b]\"I!1\u0010\u0014\u0002\u0002\u0003\u0007\u0011QK\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003b\tu\u0005\"\u0003B>O\u0005\u0005\t\u0019\u0001B8\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B8)\t\u0011\t'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005#\u0013I\u000bC\u0005\u0003|)\n\t\u00111\u0001\u0002V\u0005!A)\u0019;b!\r\u0011Y\u0003L\n\u0006Y\tE\u0016\u0011\u001e\t\r\u0005g\u0013I,!&\u0002(\u00065&\u0011F\u0007\u0003\u0005kS1Aa._\u0003\u001d\u0011XO\u001c;j[\u0016LAAa/\u00036\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0015\u0005\t5\u0016!B1qa2LH\u0003\u0003B\u0015\u0005\u0007\u0014)Ma2\t\u000f\u0005Eu\u00061\u0001\u0002\u0016\"9\u00111U\u0018A\u0002\u0005\u001d\u0006bBA]_\u0001\u0007\u0011QV\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011iM!7\u0011\r\u0005=#q\u001aBj\u0013\r\u0011\tN\u0018\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0015\u0005=#Q[AK\u0003O\u000bi+C\u0002\u0003Xz\u0013a\u0001V;qY\u0016\u001c\u0004\"\u0003Bna\u0005\u0005\t\u0019\u0001B\u0015\u0003\rAH\u0005M\u0001\tg\u00064X-S7qYR!!\u0011\u001dBt!\u0011\tyEa9\n\u0007\t\u0015hL\u0001\u0003V]&$\bB\u0002Buc\u0001\u0007\u0001,\u0001\u0003qCRD'AD%E\r6{G-\u001a7SK\u0006$WM]\n\u0004e\t=\b\u0003B)\u0003r.K1Aa=S\u0005!iEJU3bI\u0016\u0014HC\u0001B|!\r\u0011)AM\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!\u0003\u0011aw.\u00193\u0015\u0007-\u001b\t\u0001\u0003\u0004\u0003jZ\u0002\r\u0001W\u0001\u0005e\u0016\fG-\u0006\u0002\u0003p\"\"qGZAf)\rY51\u0002\u0005\u0007\u0005SD\u0004\u0019\u0001-)\ta2\u00171Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007'\u0001BAa\u0019\u0004\u0016%!1q\u0003B3\u0005\u0019y%M[3di\"\"\u0011CZAfQ\u0011\u0001b-a3"
)
public class IDFModel extends Model implements IDFBase, MLWritable {
   private final String uid;
   private final org.apache.spark.mllib.feature.IDFModel idfModel;
   private IntParam minDocFreq;
   private Param outputCol;
   private Param inputCol;

   public static IDFModel load(final String path) {
      return IDFModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return IDFModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getMinDocFreq() {
      return IDFBase.getMinDocFreq$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return IDFBase.validateAndTransformSchema$(this, schema);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final IntParam minDocFreq() {
      return this.minDocFreq;
   }

   public final void org$apache$spark$ml$feature$IDFBase$_setter_$minDocFreq_$eq(final IntParam x$1) {
      this.minDocFreq = x$1;
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

   public IDFModel setInputCol(final String value) {
      return (IDFModel)this.set(this.inputCol(), value);
   }

   public IDFModel setOutputCol(final String value) {
      return (IDFModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Function1 func = (vector) -> {
         if (vector instanceof SparseVector var5) {
            Option var6 = .MODULE$.unapply(var5);
            if (!var6.isEmpty()) {
               int size = BoxesRunTime.unboxToInt(((Tuple3)var6.get())._1());
               int[] indices = (int[])((Tuple3)var6.get())._2();
               double[] values = (double[])((Tuple3)var6.get())._3();
               Tuple2 var11 = org.apache.spark.mllib.feature.IDFModel$.MODULE$.transformSparse(this.idfModel.idf(), indices, values);
               if (var11 != null) {
                  int[] newIndices = (int[])var11._1();
                  double[] newValues = (double[])var11._2();
                  Tuple2 var10 = new Tuple2(newIndices, newValues);
                  int[] newIndicesx = (int[])var10._1();
                  double[] newValuesx = (double[])var10._2();
                  return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, newIndicesx, newValuesx);
               }

               throw new MatchError(var11);
            }
         }

         if (vector instanceof DenseVector var16) {
            Option var17 = org.apache.spark.ml.linalg.DenseVector..MODULE$.unapply(var16);
            if (!var17.isEmpty()) {
               double[] values = (double[])var17.get();
               double[] newValues = org.apache.spark.mllib.feature.IDFModel$.MODULE$.transformDense(this.idfModel.idf(), values);
               return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(newValues);
            }
         }

         throw new UnsupportedOperationException("Only sparse and dense vectors are supported but got " + vector.getClass() + ".");
      };
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IDFModel.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IDFModel.class.getClassLoader());

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
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.outputCol()), this.idf().size());
      }

      return outputSchema;
   }

   public IDFModel copy(final ParamMap extra) {
      IDFModel copied = new IDFModel(this.uid(), this.idfModel);
      return (IDFModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public Vector idf() {
      return this.idfModel.idf().asML();
   }

   public long[] docFreq() {
      return this.idfModel.docFreq();
   }

   public long numDocs() {
      return this.idfModel.numDocs();
   }

   public MLWriter write() {
      return new IDFModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "IDFModel: uid=" + var10000 + ", numDocs=" + this.numDocs() + ", numFeatures=" + this.idf().size();
   }

   public IDFModel(final String uid, final org.apache.spark.mllib.feature.IDFModel idfModel) {
      this.uid = uid;
      this.idfModel = idfModel;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      IDFBase.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public IDFModel() {
      this("", (org.apache.spark.mllib.feature.IDFModel)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class IDFModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final IDFModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.idf(), this.instance.docFreq(), this.instance.numDocs());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(IDFModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.IDFModel.IDFModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.IDFModel.IDFModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public IDFModelWriter(final IDFModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector idf;
         private final long[] docFreq;
         private final long numDocs;
         // $FF: synthetic field
         public final IDFModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector idf() {
            return this.idf;
         }

         public long[] docFreq() {
            return this.docFreq;
         }

         public long numDocs() {
            return this.numDocs;
         }

         public Data copy(final Vector idf, final long[] docFreq, final long numDocs) {
            return this.org$apache$spark$ml$feature$IDFModel$IDFModelWriter$Data$$$outer().new Data(idf, docFreq, numDocs);
         }

         public Vector copy$default$1() {
            return this.idf();
         }

         public long[] copy$default$2() {
            return this.docFreq();
         }

         public long copy$default$3() {
            return this.numDocs();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.idf();
               }
               case 1 -> {
                  return this.docFreq();
               }
               case 2 -> {
                  return BoxesRunTime.boxToLong(this.numDocs());
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
                  return "idf";
               }
               case 1 -> {
                  return "docFreq";
               }
               case 2 -> {
                  return "numDocs";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.anyHash(this.idf()));
            var1 = Statics.mix(var1, Statics.anyHash(this.docFreq()));
            var1 = Statics.mix(var1, Statics.longHash(this.numDocs()));
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label58: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$IDFModel$IDFModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$IDFModel$IDFModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.numDocs() == var4.numDocs()) {
                        label48: {
                           Vector var10000 = this.idf();
                           Vector var5 = var4.idf();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label48;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label48;
                           }

                           if (this.docFreq() == var4.docFreq() && var4.canEqual(this)) {
                              break label58;
                           }
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
         public IDFModelWriter org$apache$spark$ml$feature$IDFModel$IDFModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector idf, final long[] docFreq, final long numDocs) {
            this.idf = idf;
            this.docFreq = docFreq;
            this.numDocs = numDocs;
            if (IDFModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = IDFModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final IDFModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector idf, final long[] docFreq, final long numDocs) {
            return this.$outer.new Data(idf, docFreq, numDocs);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.idf(), x$0.docFreq(), BoxesRunTime.boxToLong(x$0.numDocs()))));
         }

         public Data$() {
            if (IDFModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = IDFModelWriter.this;
               super();
            }
         }
      }
   }

   private static class IDFModelReader extends MLReader {
      private final String className = IDFModel.class.getName();

      private String className() {
         return this.className;
      }

      public IDFModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         IDFModel var10000;
         if (org.apache.spark.util.VersionUtils..MODULE$.majorVersion(metadata.sparkVersion()) >= 3) {
            Row var9 = (Row)data.select("idf", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"docFreq", "numDocs"}))).head();
            if (var9 == null) {
               throw new MatchError(var9);
            }

            Some var10 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var9);
            if (var10.isEmpty() || var10.get() == null || ((SeqOps)var10.get()).lengthCompare(3) != 0) {
               throw new MatchError(var9);
            }

            Object idf = ((SeqOps)var10.get()).apply(0);
            Object df = ((SeqOps)var10.get()).apply(1);
            Object numDocs = ((SeqOps)var10.get()).apply(2);
            if (!(idf instanceof Vector)) {
               throw new MatchError(var9);
            }

            Vector var14 = (Vector)idf;
            if (!(df instanceof Seq)) {
               throw new MatchError(var9);
            }

            Seq var15 = (Seq)df;
            if (!(numDocs instanceof Long)) {
               throw new MatchError(var9);
            }

            long var16 = BoxesRunTime.unboxToLong(numDocs);
            Tuple3 var8 = new Tuple3(var14, var15, BoxesRunTime.boxToLong(var16));
            Vector idf = (Vector)var8._1();
            Seq df = (Seq)var8._2();
            long numDocs = BoxesRunTime.unboxToLong(var8._3());
            var10000 = new IDFModel(metadata.uid(), new org.apache.spark.mllib.feature.IDFModel(Vectors$.MODULE$.fromML(idf), (long[])df.toArray(scala.reflect.ClassTag..MODULE$.Long()), numDocs));
         } else {
            Row var23 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (scala.collection.immutable.Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"idf"}))).select("idf", scala.collection.immutable.Nil..MODULE$).head();
            if (var23 == null) {
               throw new MatchError(var23);
            }

            Some var24 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var23);
            if (var24.isEmpty() || var24.get() == null || ((SeqOps)var24.get()).lengthCompare(1) != 0) {
               throw new MatchError(var23);
            }

            Object idf = ((SeqOps)var24.get()).apply(0);
            if (!(idf instanceof Vector)) {
               throw new MatchError(var23);
            }

            Vector var26 = (Vector)idf;
            var10000 = new IDFModel(metadata.uid(), new org.apache.spark.mllib.feature.IDFModel(Vectors$.MODULE$.fromML(var26), new long[var26.size()], 0L));
         }

         IDFModel model = var10000;
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public IDFModelReader() {
      }
   }
}
