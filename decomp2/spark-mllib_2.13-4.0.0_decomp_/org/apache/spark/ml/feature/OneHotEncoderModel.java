package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructType;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tuh\u0001B\u001d;\u0001\u0015C\u0001\"\u0016\u0001\u0003\u0006\u0004%\tE\u0016\u0005\t[\u0002\u0011\t\u0011)A\u0005/\"Aq\u000e\u0001BC\u0002\u0013\u0005\u0001\u000f\u0003\u0005z\u0001\t\u0005\t\u0015!\u0003r\u0011\u0019Y\b\u0001\"\u0001=y\"91\u0010\u0001C\u0001y\u0005\r\u0001BBA\u0003\u0001\u0011%\u0001\u000fC\u0004\u0002\b\u0001!I!!\u0003\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e!9\u0011q\u0005\u0001\u0005\u0002\u0005%\u0002bBA\u0018\u0001\u0011\u0005\u0011\u0011\u0007\u0005\b\u0003w\u0001A\u0011AA\u001f\u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000bBq!!\u0015\u0001\t\u0003\t\u0019\u0006C\u0004\u0002Z\u0001!\t%a\u0017\t\u000f\u0005=\u0004\u0001\"\u0003\u0002r!9\u0011Q\u000f\u0001\u0005B\u0005]\u0004bBA_\u0001\u0011\u0005\u0013q\u0018\u0005\b\u0003'\u0004A\u0011IAk\u0011\u001d\ty\u000e\u0001C!\u0003C<q!a:;\u0011\u0003\tIO\u0002\u0004:u!\u0005\u00111\u001e\u0005\u0007wZ!\tA!\u0003\u0007\u000f\t-a\u0003\u0001\f\u0003\u000e!I!q\u0002\r\u0003\u0002\u0003\u0006IA\u0013\u0005\u0007wb!\tA!\u0005\u0007\r\te\u0001\u0004\u0012B\u000e\u0011!y7D!f\u0001\n\u0003\u0001\b\u0002C=\u001c\u0005#\u0005\u000b\u0011B9\t\rm\\B\u0011\u0001B\u0019\u0011%\tilGA\u0001\n\u0003\u0011I\u0004C\u0005\u0003>m\t\n\u0011\"\u0001\u0003@!I!1K\u000e\u0002\u0002\u0013\u0005#Q\u000b\u0005\n\u0005CZ\u0012\u0011!C\u0001\u0005GB\u0011B!\u001a\u001c\u0003\u0003%\tAa\u001a\t\u0013\t54$!A\u0005B\t=\u0004\"\u0003B?7\u0005\u0005I\u0011\u0001B@\u0011%\u0011\u0019iGA\u0001\n\u0003\u0012)\tC\u0005\u0003\nn\t\t\u0011\"\u0011\u0003\f\"I\u0011q\\\u000e\u0002\u0002\u0013\u0005#Q\u0012\u0005\n\u0005\u001f[\u0012\u0011!C!\u0005#;\u0011B!&\u0019\u0003\u0003EIAa&\u0007\u0013\te\u0001$!A\t\n\te\u0005BB>,\t\u0003\u00119\u000bC\u0005\u0002`.\n\t\u0011\"\u0012\u0003\u000e\"I!\u0011V\u0016\u0002\u0002\u0013\u0005%1\u0016\u0005\n\u0005_[\u0013\u0011!CA\u0005cCqA!0\u0019\t#\u0012yL\u0002\u0004\u0003LZ!!Q\u001a\u0005\u0007wF\"\tA!6\t\u0013\te\u0017G1A\u0005\n\tU\u0003\u0002\u0003Bnc\u0001\u0006IAa\u0016\t\u000f\tu\u0017\u0007\"\u0011\u0003`\"9!1\u001d\f\u0005B\t\u0015\bb\u0002Bo-\u0011\u0005#\u0011\u001e\u0005\n\u0005_4\u0012\u0011!C\u0005\u0005c\u0014!c\u00148f\u0011>$XI\\2pI\u0016\u0014Xj\u001c3fY*\u00111\bP\u0001\bM\u0016\fG/\u001e:f\u0015\tid(\u0001\u0002nY*\u0011q\bQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0003\n\u000ba!\u00199bG\",'\"A\"\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u00011Ej\u0014\t\u0004\u000f\"SU\"\u0001\u001f\n\u0005%c$!B'pI\u0016d\u0007CA&\u0001\u001b\u0005Q\u0004CA&N\u0013\tq%HA\tP]\u0016Du\u000e^#oG>$WM\u001d\"bg\u0016\u0004\"\u0001U*\u000e\u0003ES!A\u0015\u001f\u0002\tU$\u0018\u000e\\\u0005\u0003)F\u0013!\"\u0014'Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002/B\u0011\u0001,\u0019\b\u00033~\u0003\"AW/\u000e\u0003mS!\u0001\u0018#\u0002\rq\u0012xn\u001c;?\u0015\u0005q\u0016!B:dC2\f\u0017B\u00011^\u0003\u0019\u0001&/\u001a3fM&\u0011!m\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001l\u0006fA\u0001fWB\u0011a-[\u0007\u0002O*\u0011\u0001NP\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00016h\u0005\u0015\u0019\u0016N\\2fC\u0005a\u0017!B\u001a/a9\u0002\u0014\u0001B;jI\u0002B3AA3l\u00035\u0019\u0017\r^3h_JL8+\u001b>fgV\t\u0011\u000fE\u0002sgVl\u0011!X\u0005\u0003iv\u0013Q!\u0011:sCf\u0004\"A\u001d<\n\u0005]l&aA%oi\"\u001a1!Z6\u0002\u001d\r\fG/Z4pef\u001c\u0016N_3tA!\u001aA!Z6\u0002\rqJg.\u001b;?)\rQUp \u0005\u0006+\u0016\u0001\ra\u0016\u0015\u0004{\u0016\\\u0007\"B8\u0006\u0001\u0004\t\bfA@fWR\t!*\u0001\rhKR\u001cuN\u001c4jO\u0016$7)\u0019;fO>\u0014\u0018pU5{KN\fq!\u001a8d_\u0012,'/\u0006\u0002\u0002\fA!\u0011QBA\f\u001b\t\tyA\u0003\u0003\u0002\u0012\u0005M\u0011aC3yaJ,7o]5p]NT1!!\u0006?\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u00033\tyAA\nVg\u0016\u0014H)\u001a4j]\u0016$g)\u001e8di&|g.A\u0006tKRLe\u000e];u\u0007>dG\u0003BA\u0010\u0003Ci\u0011\u0001\u0001\u0005\u0007\u0003GI\u0001\u0019A,\u0002\u000bY\fG.^3)\u0007%)7.\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0003\u0002 \u0005-\u0002BBA\u0012\u0015\u0001\u0007q\u000bK\u0002\u000bK.\fAb]3u\u0013:\u0004X\u000f^\"pYN$B!a\b\u00024!9\u0011QG\u0006A\u0002\u0005]\u0012A\u0002<bYV,7\u000fE\u0002sg^C3aC3l\u00035\u0019X\r^(viB,HoQ8mgR!\u0011qDA \u0011\u001d\t)\u0004\u0004a\u0001\u0003oA3\u0001D3l\u0003-\u0019X\r\u001e#s_Bd\u0015m\u001d;\u0015\t\u0005}\u0011q\t\u0005\b\u0003Gi\u0001\u0019AA%!\r\u0011\u00181J\u0005\u0004\u0003\u001bj&a\u0002\"p_2,\u0017M\u001c\u0015\u0004\u001b\u0015\\\u0017\u0001E:fi\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5e)\u0011\ty\"!\u0016\t\r\u0005\rb\u00021\u0001XQ\rqQm[\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u0011QLA5!\u0011\ty&!\u001a\u000e\u0005\u0005\u0005$\u0002BA2\u0003'\tQ\u0001^=qKNLA!a\u001a\u0002b\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005-t\u00021\u0001\u0002^\u000511o\u00195f[\u0006D3aD3l\u0003E1XM]5gs:+Xn\u00144WC2,Xm\u001d\u000b\u0005\u0003;\n\u0019\bC\u0004\u0002lA\u0001\r!!\u0018\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA=\u0003/\u0003B!a\u001f\u0002\u0012:!\u0011QPAG\u001d\u0011\ty(a#\u000f\t\u0005\u0005\u0015\u0011\u0012\b\u0005\u0003\u0007\u000b9ID\u0002[\u0003\u000bK\u0011aQ\u0005\u0003\u0003\nK!a\u0010!\n\u0007\u0005Ua(\u0003\u0003\u0002\u0010\u0006M\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003'\u000b)JA\u0005ECR\fgI]1nK*!\u0011qRA\n\u0011\u001d\tI*\u0005a\u0001\u00037\u000bq\u0001Z1uCN,G\u000f\r\u0003\u0002\u001e\u0006%\u0006CBAP\u0003C\u000b)+\u0004\u0002\u0002\u0014%!\u00111UA\n\u0005\u001d!\u0015\r^1tKR\u0004B!a*\u0002*2\u0001A\u0001DAV\u0003/\u000b\t\u0011!A\u0003\u0002\u00055&aA0%eE!\u0011qVA[!\r\u0011\u0018\u0011W\u0005\u0004\u0003gk&a\u0002(pi\"Lgn\u001a\t\u0004e\u0006]\u0016bAA];\n\u0019\u0011I\\=)\u0007E)7.\u0001\u0003d_BLHc\u0001&\u0002B\"9\u00111\u0019\nA\u0002\u0005\u0015\u0017!B3yiJ\f\u0007\u0003BAd\u0003\u001bl!!!3\u000b\u0007\u0005-G(A\u0003qCJ\fW.\u0003\u0003\u0002P\u0006%'\u0001\u0003)be\u0006lW*\u00199)\u0007I)7.A\u0003xe&$X-\u0006\u0002\u0002XB\u0019\u0001+!7\n\u0007\u0005m\u0017K\u0001\u0005N\u0019^\u0013\u0018\u000e^3sQ\r\u0019Rm[\u0001\ti>\u001cFO]5oOR\tq\u000bK\u0002\u0015K.D3\u0001A3l\u0003Iye.\u001a%pi\u0016s7m\u001c3fe6{G-\u001a7\u0011\u0005-32c\u0002\f\u0002n\u0006M\u0018\u0011 \t\u0004e\u0006=\u0018bAAy;\n1\u0011I\\=SK\u001a\u0004B\u0001UA{\u0015&\u0019\u0011q_)\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002|\n\u0015QBAA\u007f\u0015\u0011\tyP!\u0001\u0002\u0005%|'B\u0001B\u0002\u0003\u0011Q\u0017M^1\n\t\t\u001d\u0011Q \u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003S\u0014\u0001d\u00148f\u0011>$XI\\2pI\u0016\u0014Xj\u001c3fY^\u0013\u0018\u000e^3s'\rA\u0012q[\u0001\tS:\u001cH/\u00198dKR!!1\u0003B\f!\r\u0011)\u0002G\u0007\u0002-!1!q\u0002\u000eA\u0002)\u0013A\u0001R1uCN91$!<\u0003\u001e\t\r\u0002c\u0001:\u0003 %\u0019!\u0011E/\u0003\u000fA\u0013x\u000eZ;diB!!Q\u0005B\u0017\u001d\u0011\u00119Ca\u000b\u000f\u0007i\u0013I#C\u0001_\u0013\r\ty)X\u0005\u0005\u0005\u000f\u0011yCC\u0002\u0002\u0010v#BAa\r\u00038A\u0019!QG\u000e\u000e\u0003aAQa\u001c\u0010A\u0002E$BAa\r\u0003<!9qn\bI\u0001\u0002\u0004\t\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0005\u0003R3!\u001dB\"W\t\u0011)\u0005\u0005\u0003\u0003H\t=SB\u0001B%\u0015\u0011\u0011YE!\u0014\u0002\u0013Ut7\r[3dW\u0016$'B\u00015^\u0013\u0011\u0011\tF!\u0013\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005/\u0002BA!\u0017\u0003`5\u0011!1\f\u0006\u0005\u0005;\u0012\t!\u0001\u0003mC:<\u0017b\u00012\u0003\\\u0005a\u0001O]8ek\u000e$\u0018I]5usV\tQ/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005U&\u0011\u000e\u0005\t\u0005W\u001a\u0013\u0011!a\u0001k\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!\u001d\u0011\r\tM$\u0011PA[\u001b\t\u0011)HC\u0002\u0003xu\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011YH!\u001e\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u0013\u0012\t\tC\u0005\u0003l\u0015\n\t\u00111\u0001\u00026\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u00119Fa\"\t\u0011\t-d%!AA\u0002U\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002kR\u0011!qK\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005%#1\u0013\u0005\n\u0005WJ\u0013\u0011!a\u0001\u0003k\u000bA\u0001R1uCB\u0019!QG\u0016\u0014\u000b-\u0012Y*!?\u0011\u000f\tu%1U9\u000345\u0011!q\u0014\u0006\u0004\u0005Ck\u0016a\u0002:v]RLW.Z\u0005\u0005\u0005K\u0013yJA\tBEN$(/Y2u\rVt7\r^5p]F\"\"Aa&\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\tM\"Q\u0016\u0005\u0006_:\u0002\r!]\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\u0019L!/\u0011\tI\u0014),]\u0005\u0004\u0005ok&AB(qi&|g\u000eC\u0005\u0003<>\n\t\u00111\u0001\u00034\u0005\u0019\u0001\u0010\n\u0019\u0002\u0011M\fg/Z%na2$BA!1\u0003HB\u0019!Oa1\n\u0007\t\u0015WL\u0001\u0003V]&$\bB\u0002Bea\u0001\u0007q+\u0001\u0003qCRD'\u0001G(oK\"{G/\u00128d_\u0012,'/T8eK2\u0014V-\u00193feN\u0019\u0011Ga4\u0011\tA\u0013\tNS\u0005\u0004\u0005'\f&\u0001C'M%\u0016\fG-\u001a:\u0015\u0005\t]\u0007c\u0001B\u000bc\u0005I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004\u0013\u0001\u00027pC\u0012$2A\u0013Bq\u0011\u0019\u0011I-\u000ea\u0001/\u0006!!/Z1e+\t\u0011y\rK\u00027K.$2A\u0013Bv\u0011\u0019\u0011Im\u000ea\u0001/\"\u001aq'Z6\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tM\b\u0003\u0002B-\u0005kLAAa>\u0003\\\t1qJ\u00196fGRD3AF3lQ\r)Rm\u001b"
)
public class OneHotEncoderModel extends Model implements OneHotEncoderBase, MLWritable {
   private final String uid;
   private final int[] categorySizes;
   private Param handleInvalid;
   private BooleanParam dropLast;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;

   public static OneHotEncoderModel load(final String path) {
      return OneHotEncoderModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return OneHotEncoderModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public boolean getDropLast() {
      return OneHotEncoderBase.getDropLast$(this);
   }

   public Tuple2 getInOutCols() {
      return OneHotEncoderBase.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean dropLast, final boolean keepInvalid) {
      return OneHotEncoderBase.validateAndTransformSchema$(this, schema, dropLast, keepInvalid);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public final BooleanParam dropLast() {
      return this.dropLast;
   }

   public void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$dropLast_$eq(final BooleanParam x$1) {
      this.dropLast = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public int[] categorySizes() {
      return this.categorySizes;
   }

   private int[] getConfigedCategorySizes() {
      boolean dropLast;
      boolean var4;
      label29: {
         label28: {
            dropLast = this.getDropLast();
            String var10000 = this.getHandleInvalid();
            String var3 = OneHotEncoder$.MODULE$.KEEP_INVALID();
            if (var10000 == null) {
               if (var3 == null) {
                  break label28;
               }
            } else if (var10000.equals(var3)) {
               break label28;
            }

            var4 = false;
            break label29;
         }

         var4 = true;
      }

      boolean keepInvalid = var4;
      if (!dropLast && keepInvalid) {
         return (int[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(this.categorySizes()), (JFunction1.mcII.sp)(x$6) -> x$6 + 1, scala.reflect.ClassTag..MODULE$.Int());
      } else {
         return dropLast && !keepInvalid ? (int[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(this.categorySizes()), (JFunction1.mcII.sp)(x$7) -> x$7 - 1, scala.reflect.ClassTag..MODULE$.Int()) : this.categorySizes();
      }
   }

   private UserDefinedFunction encoder() {
      boolean var7;
      label17: {
         label16: {
            String var10000 = this.getHandleInvalid();
            String var2 = OneHotEncoder$.MODULE$.KEEP_INVALID();
            if (var10000 == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (var10000.equals(var2)) {
               break label16;
            }

            var7 = false;
            break label17;
         }

         var7 = true;
      }

      boolean keepInvalid = var7;
      int[] configedSizes = this.getConfigedCategorySizes();
      int[] localCategorySizes = this.categorySizes();
      functions var8 = org.apache.spark.sql.functions..MODULE$;
      Function2 var10001 = (label, colIdx) -> $anonfun$encoder$1(localCategorySizes, keepInvalid, configedSizes, BoxesRunTime.unboxToDouble(label), BoxesRunTime.unboxToInt(colIdx));
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneHotEncoderModel.class.getClassLoader());

      final class $typecreator1$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
         }

         public $typecreator1$1() {
         }
      }

      return var8.udf(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int());
   }

   public OneHotEncoderModel setInputCol(final String value) {
      return (OneHotEncoderModel)this.set(this.inputCol(), value);
   }

   public OneHotEncoderModel setOutputCol(final String value) {
      return (OneHotEncoderModel)this.set(this.outputCol(), value);
   }

   public OneHotEncoderModel setInputCols(final String[] values) {
      return (OneHotEncoderModel)this.set(this.inputCols(), values);
   }

   public OneHotEncoderModel setOutputCols(final String[] values) {
      return (OneHotEncoderModel)this.set(this.outputCols(), values);
   }

   public OneHotEncoderModel setDropLast(final boolean value) {
      return (OneHotEncoderModel)this.set(this.dropLast(), BoxesRunTime.boxToBoolean(value));
   }

   public OneHotEncoderModel setHandleInvalid(final String value) {
      return (OneHotEncoderModel)this.set(this.handleInvalid(), value);
   }

   public StructType transformSchema(final StructType schema) {
      Tuple2 var4 = this.getInOutCols();
      if (var4 != null) {
         boolean var9;
         label25: {
            label24: {
               String[] inputColNames = (String[])var4._1();
               scala.Predef..MODULE$.require(inputColNames.length == this.categorySizes().length, () -> "The number of input columns " + inputColNames.length + " must be the same as the number of features " + this.categorySizes().length + " during fitting.");
               Object var10000 = this.$(this.handleInvalid());
               String var7 = OneHotEncoder$.MODULE$.KEEP_INVALID();
               if (var10000 == null) {
                  if (var7 == null) {
                     break label24;
                  }
               } else if (var10000.equals(var7)) {
                  break label24;
               }

               var9 = false;
               break label25;
            }

            var9 = true;
         }

         boolean keepInvalid = var9;
         StructType transformedSchema = this.validateAndTransformSchema(schema, BoxesRunTime.unboxToBoolean(this.$(this.dropLast())), keepInvalid);
         return this.verifyNumOfValues(transformedSchema);
      } else {
         throw new MatchError(var4);
      }
   }

   private StructType verifyNumOfValues(final StructType schema) {
      int[] configedSizes = this.getConfigedCategorySizes();
      Tuple2 var5 = this.getInOutCols();
      if (var5 != null) {
         String[] inputColNames = (String[])var5._1();
         String[] outputColNames = (String[])var5._2();
         Tuple2 var4 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var4._1();
         String[] outputColNames = (String[])var4._2();
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputColNames))), (x0$1) -> {
            $anonfun$verifyNumOfValues$1(inputColNames, schema, configedSizes, x0$1);
            return BoxedUnit.UNIT;
         });
         return schema;
      } else {
         throw new MatchError(var5);
      }
   }

   public Dataset transform(final Dataset dataset) {
      StructType transformedSchema;
      boolean var13;
      label22: {
         label21: {
            transformedSchema = this.transformSchema(dataset.schema(), true);
            Object var10000 = this.$(this.handleInvalid());
            String var5 = OneHotEncoder$.MODULE$.KEEP_INVALID();
            if (var10000 == null) {
               if (var5 == null) {
                  break label21;
               }
            } else if (var10000.equals(var5)) {
               break label21;
            }

            var13 = false;
            break label22;
         }

         var13 = true;
      }

      boolean keepInvalid = var13;
      Tuple2 var7 = this.getInOutCols();
      if (var7 != null) {
         String[] inputColNames = (String[])var7._1();
         String[] outputColNames = (String[])var7._2();
         Tuple2 var6 = new Tuple2(inputColNames, outputColNames);
         String[] inputColNames = (String[])var6._1();
         String[] outputColNames = (String[])var6._2();
         IndexedSeq encodedColumns = .MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputColNames)).map((idx) -> $anonfun$transform$1(this, inputColNames, outputColNames, transformedSchema, keepInvalid, BoxesRunTime.unboxToInt(idx)));
         return dataset.withColumns(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputColNames).toImmutableArraySeq(), encodedColumns);
      } else {
         throw new MatchError(var7);
      }
   }

   public OneHotEncoderModel copy(final ParamMap extra) {
      OneHotEncoderModel copied = new OneHotEncoderModel(this.uid(), this.categorySizes());
      return (OneHotEncoderModel)((Model)this.copyValues(copied, extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new OneHotEncoderModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "OneHotEncoderModel: uid=" + var10000 + ", dropLast=" + this.$(this.dropLast()) + ", handleInvalid=" + this.$(this.handleInvalid()) + this.get(this.inputCols()).map((c) -> ", numInputCols=" + c.length).getOrElse(() -> "") + this.get(this.outputCols()).map((c) -> ", numOutputCols=" + c.length).getOrElse(() -> "");
   }

   // $FF: synthetic method
   public static final Vector $anonfun$encoder$1(final int[] localCategorySizes$1, final boolean keepInvalid$2, final int[] configedSizes$1, final double label, final int colIdx) {
      int origCategorySize = localCategorySizes$1[colIdx];
      double var10000;
      if (label >= (double)0 && label < (double)origCategorySize) {
         var10000 = label;
      } else {
         if (!keepInvalid$2) {
            if (label < (double)0) {
               throw new SparkException("Negative value: " + label + ". Input can't be negative. To handle invalid values, set Param handleInvalid to " + OneHotEncoder$.MODULE$.KEEP_INVALID());
            }

            throw new SparkException("Unseen value: " + label + ". To handle unseen values, set Param handleInvalid to " + OneHotEncoder$.MODULE$.KEEP_INVALID() + ".");
         }

         var10000 = (double)origCategorySize;
      }

      double idx = var10000;
      int size = configedSizes$1[colIdx];
      return idx < (double)size ? org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, new int[]{(int)idx}, new double[]{(double)1.0F}) : org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(size, scala.Array..MODULE$.emptyIntArray(), scala.Array..MODULE$.emptyDoubleArray());
   }

   // $FF: synthetic method
   public static final void $anonfun$verifyNumOfValues$1(final String[] inputColNames$3, final StructType schema$2, final int[] configedSizes$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String outputColName = (String)x0$1._1();
         int idx = x0$1._2$mcI$sp();
         String inputColName = inputColNames$3[idx];
         AttributeGroup attrGroup = AttributeGroup$.MODULE$.fromStructField(schema$2.apply(outputColName));
         if (attrGroup.attributes().nonEmpty()) {
            int numCategories = configedSizes$2[idx];
            scala.Predef..MODULE$.require(attrGroup.size() == numCategories, () -> "OneHotEncoderModel expected " + numCategories + " categorical values for input column " + inputColName + ", but the input column had metadata specifying " + attrGroup.size() + " values.");
            BoxedUnit var11 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final Column $anonfun$transform$1(final OneHotEncoderModel $this, final String[] inputColNames$4, final String[] outputColNames$2, final StructType transformedSchema$2, final boolean keepInvalid$3, final int idx) {
      String inputColName = inputColNames$4[idx];
      String outputColName = outputColNames$2[idx];
      AttributeGroup outputAttrGroupFromSchema = AttributeGroup$.MODULE$.fromStructField(transformedSchema$2.apply(outputColName));
      Metadata metadata = outputAttrGroupFromSchema.size() < 0 ? OneHotEncoderCommon$.MODULE$.createAttrGroupForAttrNames(outputColName, $this.categorySizes()[idx], BoxesRunTime.unboxToBoolean($this.$($this.dropLast())), keepInvalid$3).toMetadata() : outputAttrGroupFromSchema.toMetadata();
      return $this.encoder().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col(inputColName).cast(org.apache.spark.sql.types.DoubleType..MODULE$), org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToInteger(idx))}))).as(outputColName, metadata);
   }

   public OneHotEncoderModel(final String uid, final int[] categorySizes) {
      this.uid = uid;
      this.categorySizes = categorySizes;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      OneHotEncoderBase.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public OneHotEncoderModel() {
      this("", scala.Array..MODULE$.emptyIntArray());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class OneHotEncoderModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final OneHotEncoderModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.categorySizes());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(OneHotEncoderModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.OneHotEncoderModel.OneHotEncoderModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.OneHotEncoderModel.OneHotEncoderModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public OneHotEncoderModelWriter(final OneHotEncoderModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final int[] categorySizes;
         // $FF: synthetic field
         public final OneHotEncoderModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int[] categorySizes() {
            return this.categorySizes;
         }

         public Data copy(final int[] categorySizes) {
            return this.org$apache$spark$ml$feature$OneHotEncoderModel$OneHotEncoderModelWriter$Data$$$outer().new Data(categorySizes);
         }

         public int[] copy$default$1() {
            return this.categorySizes();
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
                  return this.categorySizes();
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
                  return "categorySizes";
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
            boolean var10000;
            if (this != x$1) {
               label41: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$OneHotEncoderModel$OneHotEncoderModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$OneHotEncoderModel$OneHotEncoderModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.categorySizes() == var4.categorySizes() && var4.canEqual(this)) {
                        break label41;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public OneHotEncoderModelWriter org$apache$spark$ml$feature$OneHotEncoderModel$OneHotEncoderModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int[] categorySizes) {
            this.categorySizes = categorySizes;
            if (OneHotEncoderModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = OneHotEncoderModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final OneHotEncoderModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int[] categorySizes) {
            return this.$outer.new Data(categorySizes);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.categorySizes()));
         }

         public Data$() {
            if (OneHotEncoderModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = OneHotEncoderModelWriter.this;
               super();
            }
         }
      }
   }

   private static class OneHotEncoderModelReader extends MLReader {
      private final String className = OneHotEncoderModel.class.getName();

      private String className() {
         return this.className;
      }

      public OneHotEncoderModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("categorySizes", scala.collection.immutable.Nil..MODULE$).head();
         int[] categorySizes = (int[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Int());
         OneHotEncoderModel model = new OneHotEncoderModel(metadata.uid(), categorySizes);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public OneHotEncoderModelReader() {
      }
   }
}
