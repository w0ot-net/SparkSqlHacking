package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.attribute.Attribute;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.NumericAttribute;
import org.apache.spark.ml.attribute.NumericAttribute$;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
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
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.collection.OpenHashMap;
import org.apache.spark.util.collection.Utils.;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
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
import scala.runtime.LongRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r=a\u0001\u0002\u001d:\u0001\u0011C\u0001\u0002\u0016\u0001\u0003\u0006\u0004%\t%\u0016\u0005\tY\u0002\u0011\t\u0011)A\u0005-\"Aa\u000e\u0001BC\u0002\u0013\u0005q\u000e\u0003\u0005v\u0001\t\u0005\t\u0015!\u0003q\u0011\u00159\b\u0001\"\u0001y\u0011\u00199\b\u0001\"\u0001<{\")q\u000f\u0001C\u0001}\"9\u00111\u0001\u0001\u0005\u0002\u0005\u0015\u0001bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u0003/\u0001A\u0011AA\r\u0011\u001d\t)\u0003\u0001C\u0001\u0003OA\u0011\"a\u000e\u0001\u0001\u0004%I!!\u000f\t\u0013\u0005e\u0003\u00011A\u0005\n\u0005m\u0003\u0002CA4\u0001\u0001\u0006K!a\u000f\t\u000f\u0005%\u0004\u0001\"\u0011\u0002l!9\u0011Q\u0017\u0001\u0005B\u0005]\u0006bBAf\u0001\u0011\u0005\u0013Q\u001a\u0005\b\u0003C\u0004A\u0011IAr\u0011\u001d\t\t\u0010\u0001C!\u0003g<q!!@:\u0011\u0003\tyP\u0002\u00049s!\u0005!\u0011\u0001\u0005\u0007oV!\tAa\b\u0007\u000f\t\u0005R\u0003A\u000b\u0003$!I!QE\f\u0003\u0002\u0003\u0006I!\u0013\u0005\u0007o^!\tAa\n\u0007\r\t=r\u0003\u0012B\u0019\u0011%q'D!f\u0001\n\u0003\u00119\u0005C\u0005v5\tE\t\u0015!\u0003\u0003J!1qO\u0007C\u0001\u0005\u001fB\u0011\"a3\u001b\u0003\u0003%\tAa\u0016\t\u0013\tm#$%A\u0005\u0002\tu\u0003\"\u0003B95\u0005\u0005I\u0011\tB:\u0011%\u0011yHGA\u0001\n\u0003\u0011\t\tC\u0005\u0003\u0004j\t\t\u0011\"\u0001\u0003\u0006\"I!\u0011\u0012\u000e\u0002\u0002\u0013\u0005#1\u0012\u0005\n\u00053S\u0012\u0011!C\u0001\u00057C\u0011Ba(\u001b\u0003\u0003%\tE!)\t\u0013\t\u0015&$!A\u0005B\t\u001d\u0006\"CAy5\u0005\u0005I\u0011\tBU\u0011%\u0011YKGA\u0001\n\u0003\u0012ikB\u0005\u00032^\t\t\u0011#\u0003\u00034\u001aI!qF\f\u0002\u0002#%!Q\u0017\u0005\u0007o*\"\tAa1\t\u0013\u0005E(&!A\u0005F\t%\u0006\"\u0003BcU\u0005\u0005I\u0011\u0011Bd\u0011%\u0011YMKA\u0001\n\u0003\u0013i\rC\u0004\u0003V^!\tFa6\u0007\r\tuW\u0003\u0002Bp\u0011\u00199\b\u0007\"\u0001\u0003h\"I!1\u001e\u0019C\u0002\u0013%!1\u000f\u0005\t\u0005[\u0004\u0004\u0015!\u0003\u0003v!9!q\u001e\u0019\u0005B\tE\bb\u0002B{+\u0011\u0005#q\u001f\u0005\b\u0005_,B\u0011\tB~\u0011%\u0019\t!FA\u0001\n\u0013\u0019\u0019A\u0001\u000bD_VtGOV3di>\u0014\u0018N_3s\u001b>$W\r\u001c\u0006\u0003um\nqAZ3biV\u0014XM\u0003\u0002={\u0005\u0011Q\u000e\u001c\u0006\u0003}}\nQa\u001d9be.T!\u0001Q!\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0011\u0015aA8sO\u000e\u00011\u0003\u0002\u0001F\u0017:\u00032AR$J\u001b\u0005Y\u0014B\u0001%<\u0005\u0015iu\u000eZ3m!\tQ\u0005!D\u0001:!\tQE*\u0003\u0002Ns\t)2i\\;oiZ+7\r^8sSj,'\u000fU1sC6\u001c\bCA(S\u001b\u0005\u0001&BA)<\u0003\u0011)H/\u001b7\n\u0005M\u0003&AC'M/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003Y\u0003\"a\u00161\u000f\u0005as\u0006CA-]\u001b\u0005Q&BA.D\u0003\u0019a$o\\8u})\tQ,A\u0003tG\u0006d\u0017-\u0003\u0002`9\u00061\u0001K]3eK\u001aL!!\u00192\u0003\rM#(/\u001b8h\u0015\tyF\fK\u0002\u0002I*\u0004\"!\u001a5\u000e\u0003\u0019T!aZ\u001f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002jM\n)1+\u001b8dK\u0006\n1.A\u00032]Ur\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002eU\u0006Qao\\2bEVd\u0017M]=\u0016\u0003A\u00042!\u001d:W\u001b\u0005a\u0016BA:]\u0005\u0015\t%O]1zQ\r\u0019AM[\u0001\fm>\u001c\u0017MY;mCJL\b\u0005K\u0002\u0005I*\fa\u0001P5oSRtDcA%zw\")A+\u0002a\u0001-\"\u001a\u0011\u0010\u001a6\t\u000b9,\u0001\u0019\u00019)\u0007m$'\u000eF\u0001J)\tIu\u0010C\u0003o\u000f\u0001\u0007\u0001\u000fK\u0002\bI*\f1b]3u\u0013:\u0004X\u000f^\"pYR!\u0011qAA\u0005\u001b\u0005\u0001\u0001BBA\u0006\u0011\u0001\u0007a+A\u0003wC2,X\rK\u0002\tI*\fAb]3u\u001fV$\b/\u001e;D_2$B!a\u0002\u0002\u0014!1\u00111B\u0005A\u0002YC3!\u00033k\u0003!\u0019X\r^'j]R3E\u0003BA\u0004\u00037Aq!a\u0003\u000b\u0001\u0004\ti\u0002E\u0002r\u0003?I1!!\t]\u0005\u0019!u.\u001e2mK\"\u001a!\u0002\u001a6\u0002\u0013M,GOQ5oCJLH\u0003BA\u0004\u0003SAq!a\u0003\f\u0001\u0004\tY\u0003E\u0002r\u0003[I1!a\f]\u0005\u001d\u0011un\u001c7fC:DCa\u00033\u00024\u0005\u0012\u0011QG\u0001\u0006e9\u0002d\u0006M\u0001\u000eEJ|\u0017\rZ2bgR$\u0015n\u0019;\u0016\u0005\u0005m\u0002#B9\u0002>\u0005\u0005\u0013bAA 9\n1q\n\u001d;j_:\u0004b!a\u0011\u0002J\u00055SBAA#\u0015\r\t9%P\u0001\nEJ|\u0017\rZ2bgRLA!a\u0013\u0002F\tI!I]8bI\u000e\f7\u000f\u001e\t\u0007/\u0006=c+a\u0015\n\u0007\u0005E#MA\u0002NCB\u00042!]A+\u0013\r\t9\u0006\u0018\u0002\u0004\u0013:$\u0018!\u00052s_\u0006$7-Y:u\t&\u001cGo\u0018\u0013fcR!\u0011QLA2!\r\t\u0018qL\u0005\u0004\u0003Cb&\u0001B+oSRD\u0011\"!\u001a\u000e\u0003\u0003\u0005\r!a\u000f\u0002\u0007a$\u0013'\u0001\bce>\fGmY1ti\u0012K7\r\u001e\u0011\u0002\u0013Q\u0014\u0018M\\:g_JlG\u0003BA7\u0003\u001f\u0003B!a\u001c\u0002\n:!\u0011\u0011OAB\u001d\u0011\t\u0019(a \u000f\t\u0005U\u0014Q\u0010\b\u0005\u0003o\nYHD\u0002Z\u0003sJ\u0011AQ\u0005\u0003\u0001\u0006K!AP \n\u0007\u0005\u0005U(A\u0002tc2LA!!\"\u0002\b\u00069\u0001/Y2lC\u001e,'bAAA{%!\u00111RAG\u0005%!\u0015\r^1Ge\u0006lWM\u0003\u0003\u0002\u0006\u0006\u001d\u0005bBAI\u001f\u0001\u0007\u00111S\u0001\bI\u0006$\u0018m]3ua\u0011\t)*!)\u0011\r\u0005]\u0015\u0011TAO\u001b\t\t9)\u0003\u0003\u0002\u001c\u0006\u001d%a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003?\u000b\t\u000b\u0004\u0001\u0005\u0019\u0005\r\u0016qRA\u0001\u0002\u0003\u0015\t!!*\u0003\u0007}##'\u0005\u0003\u0002(\u00065\u0006cA9\u0002*&\u0019\u00111\u0016/\u0003\u000f9{G\u000f[5oOB\u0019\u0011/a,\n\u0007\u0005EFLA\u0002B]fDCa\u00043\u00024\u0005yAO]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0003\u0002:\u0006\u0015\u0007\u0003BA^\u0003\u0003l!!!0\u000b\t\u0005}\u0016qQ\u0001\u0006if\u0004Xm]\u0005\u0005\u0003\u0007\fiL\u0001\u0006TiJ,8\r\u001e+za\u0016Dq!a2\u0011\u0001\u0004\tI,\u0001\u0004tG\",W.\u0019\u0015\u0004!\u0011T\u0017\u0001B2paf$2!SAh\u0011\u001d\t\t.\u0005a\u0001\u0003'\fQ!\u001a=ue\u0006\u0004B!!6\u0002\\6\u0011\u0011q\u001b\u0006\u0004\u00033\\\u0014!\u00029be\u0006l\u0017\u0002BAo\u0003/\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004#\u0011T\u0017!B<sSR,WCAAs!\ry\u0015q]\u0005\u0004\u0003S\u0004&\u0001C'M/JLG/\u001a:)\tI!\u0017Q^\u0011\u0003\u0003_\fQ!\r\u00187]A\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002-\"\"1\u0003ZA|C\t\tI0A\u00034]Ar\u0003\u0007K\u0002\u0001I*\fAcQ8v]R4Vm\u0019;pe&TXM]'pI\u0016d\u0007C\u0001&\u0016'\u001d)\"1\u0001B\u0005\u0005\u001f\u00012!\u001dB\u0003\u0013\r\u00119\u0001\u0018\u0002\u0007\u0003:L(+\u001a4\u0011\t=\u0013Y!S\u0005\u0004\u0005\u001b\u0001&AC'M%\u0016\fG-\u00192mKB!!\u0011\u0003B\u000e\u001b\t\u0011\u0019B\u0003\u0003\u0003\u0016\t]\u0011AA5p\u0015\t\u0011I\"\u0001\u0003kCZ\f\u0017\u0002\u0002B\u000f\u0005'\u0011AbU3sS\u0006d\u0017N_1cY\u0016$\"!a@\u00035\r{WO\u001c;WK\u000e$xN]5{KJlu\u000eZ3m/JLG/\u001a:\u0014\u0007]\t)/\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011IC!\f\u0011\u0007\t-r#D\u0001\u0016\u0011\u0019\u0011)#\u0007a\u0001\u0013\n!A)\u0019;b'\u001dQ\"1\u0001B\u001a\u0005s\u00012!\u001dB\u001b\u0013\r\u00119\u0004\u0018\u0002\b!J|G-^2u!\u0011\u0011YDa\u0011\u000f\t\tu\"\u0011\t\b\u00043\n}\u0012\"A/\n\u0007\u0005\u0015E,\u0003\u0003\u0003\u001e\t\u0015#bAAC9V\u0011!\u0011\n\t\u0006\u0005w\u0011YEV\u0005\u0005\u0005\u001b\u0012)EA\u0002TKF$BA!\u0015\u0003VA\u0019!1\u000b\u000e\u000e\u0003]AaA\\\u000fA\u0002\t%C\u0003\u0002B)\u00053B\u0001B\u001c\u0010\u0011\u0002\u0003\u0007!\u0011J\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011yF\u000b\u0003\u0003J\t\u00054F\u0001B2!\u0011\u0011)G!\u001c\u000e\u0005\t\u001d$\u0002\u0002B5\u0005W\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u001dd\u0016\u0002\u0002B8\u0005O\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!Q\u000f\t\u0005\u0005o\u0012i(\u0004\u0002\u0003z)!!1\u0010B\f\u0003\u0011a\u0017M\\4\n\u0007\u0005\u0014I(\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002T\u0005q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAW\u0005\u000fC\u0011\"!\u001a#\u0003\u0003\u0005\r!a\u0015\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!$\u0011\r\t=%QSAW\u001b\t\u0011\tJC\u0002\u0003\u0014r\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u00119J!%\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003W\u0011i\nC\u0005\u0002f\u0011\n\t\u00111\u0001\u0002.\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0011)Ha)\t\u0013\u0005\u0015T%!AA\u0002\u0005M\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005MCC\u0001B;\u0003\u0019)\u0017/^1mgR!\u00111\u0006BX\u0011%\t)\u0007KA\u0001\u0002\u0004\ti+\u0001\u0003ECR\f\u0007c\u0001B*UM)!Fa.\u0003\u0010AA!\u0011\u0018B`\u0005\u0013\u0012\t&\u0004\u0002\u0003<*\u0019!Q\u0018/\u0002\u000fI,h\u000e^5nK&!!\u0011\u0019B^\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\u000b\u0003\u0005g\u000bQ!\u00199qYf$BA!\u0015\u0003J\"1a.\fa\u0001\u0005\u0013\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003P\nE\u0007#B9\u0002>\t%\u0003\"\u0003Bj]\u0005\u0005\t\u0019\u0001B)\u0003\rAH\u0005M\u0001\tg\u00064X-S7qYR!\u0011Q\fBm\u0011\u0019\u0011Yn\fa\u0001-\u0006!\u0001/\u0019;i\u0005i\u0019u.\u001e8u-\u0016\u001cGo\u001c:ju\u0016\u0014Xj\u001c3fYJ+\u0017\rZ3s'\r\u0001$\u0011\u001d\t\u0005\u001f\n\r\u0018*C\u0002\u0003fB\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u000b\u0003\u0005S\u00042Aa\u000b1\u0003%\u0019G.Y:t\u001d\u0006lW-\u0001\u0006dY\u0006\u001c8OT1nK\u0002\nA\u0001\\8bIR\u0019\u0011Ja=\t\r\tmG\u00071\u0001W\u0003\u0011\u0011X-\u00193\u0016\u0005\t\u0005\b\u0006B\u001be\u0003[$2!\u0013B\u007f\u0011\u0019\u0011YN\u000ea\u0001-\"\"a\u0007ZAw\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019)\u0001\u0005\u0003\u0003x\r\u001d\u0011\u0002BB\u0005\u0005s\u0012aa\u00142kK\u000e$\b\u0006B\u000be\u0003[DC\u0001\u00063\u0002n\u0002"
)
public class CountVectorizerModel extends Model implements CountVectorizerParams, MLWritable {
   private final String uid;
   private final String[] vocabulary;
   private Option broadcastDict;
   private IntParam vocabSize;
   private DoubleParam minDF;
   private DoubleParam maxDF;
   private DoubleParam minTF;
   private BooleanParam binary;
   private Param outputCol;
   private Param inputCol;

   public static CountVectorizerModel load(final String path) {
      return CountVectorizerModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return CountVectorizerModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getVocabSize() {
      return CountVectorizerParams.getVocabSize$(this);
   }

   public double getMinDF() {
      return CountVectorizerParams.getMinDF$(this);
   }

   public double getMaxDF() {
      return CountVectorizerParams.getMaxDF$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return CountVectorizerParams.validateAndTransformSchema$(this, schema);
   }

   public double getMinTF() {
      return CountVectorizerParams.getMinTF$(this);
   }

   public boolean getBinary() {
      return CountVectorizerParams.getBinary$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public IntParam vocabSize() {
      return this.vocabSize;
   }

   public DoubleParam minDF() {
      return this.minDF;
   }

   public DoubleParam maxDF() {
      return this.maxDF;
   }

   public DoubleParam minTF() {
      return this.minTF;
   }

   public BooleanParam binary() {
      return this.binary;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$vocabSize_$eq(final IntParam x$1) {
      this.vocabSize = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minDF_$eq(final DoubleParam x$1) {
      this.minDF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$maxDF_$eq(final DoubleParam x$1) {
      this.maxDF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$minTF_$eq(final DoubleParam x$1) {
      this.minTF = x$1;
   }

   public void org$apache$spark$ml$feature$CountVectorizerParams$_setter_$binary_$eq(final BooleanParam x$1) {
      this.binary = x$1;
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

   public String[] vocabulary() {
      return this.vocabulary;
   }

   public CountVectorizerModel setInputCol(final String value) {
      return (CountVectorizerModel)this.set(this.inputCol(), value);
   }

   public CountVectorizerModel setOutputCol(final String value) {
      return (CountVectorizerModel)this.set(this.outputCol(), value);
   }

   public CountVectorizerModel setMinTF(final double value) {
      return (CountVectorizerModel)this.set(this.minTF(), BoxesRunTime.boxToDouble(value));
   }

   public CountVectorizerModel setBinary(final boolean value) {
      return (CountVectorizerModel)this.set(this.binary(), BoxesRunTime.boxToBoolean(value));
   }

   private Option broadcastDict() {
      return this.broadcastDict;
   }

   private void broadcastDict_$eq(final Option x$1) {
      this.broadcastDict = x$1;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      if (this.broadcastDict().isEmpty()) {
         Map dict = .MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapRefArray((Object[])this.vocabulary()));
         this.broadcastDict_$eq(new Some(dataset.sparkSession().sparkContext().broadcast(dict, scala.reflect.ClassTag..MODULE$.apply(Map.class))));
      }

      Broadcast dictBr = (Broadcast)this.broadcastDict().get();
      double minTf = BoxesRunTime.unboxToDouble(this.$(this.minTF()));
      boolean isBinary = BoxesRunTime.unboxToBoolean(this.$(this.binary()));
      functions var10000 = org.apache.spark.sql.functions..MODULE$;
      Function1 var10001 = (document) -> {
         OpenHashMap termCounts = new OpenHashMap.mcD.sp(scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Double());
         LongRef tokenCount = LongRef.create(0L);
         document.foreach((term) -> {
            $anonfun$transform$2(dictBr, termCounts, tokenCount, term);
            return BoxedUnit.UNIT;
         });
         double effectiveMinTF = minTf >= (double)1.0F ? minTf : (double)tokenCount.elem * minTf;
         Seq effectiveCounts = isBinary ? ((IterableOnceOps)((IterableOps)termCounts.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$transform$5(effectiveMinTF, x$6)))).map((p) -> new Tuple2.mcID.sp(p._1$mcI$sp(), (double)1.0F))).toSeq() : ((IterableOnceOps)termCounts.filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$transform$7(effectiveMinTF, x$7)))).toSeq();
         return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(((IterableOnceOps)dictBr.value()).size(), effectiveCounts);
      };
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(CountVectorizerModel.class.getClassLoader());

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
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(CountVectorizerModel.class.getClassLoader());

      final class $typecreator2$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
         }

         public $typecreator2$1() {
         }
      }

      UserDefinedFunction vectorizer = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
      return dataset.withColumn((String)this.$(this.outputCol()), vectorizer.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.outputCol())))) {
         Attribute[] attrs = (Attribute[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.vocabulary()), (x$8) -> new NumericAttribute(NumericAttribute$.MODULE$.$lessinit$greater$default$1(), NumericAttribute$.MODULE$.$lessinit$greater$default$2(), NumericAttribute$.MODULE$.$lessinit$greater$default$3(), NumericAttribute$.MODULE$.$lessinit$greater$default$4(), NumericAttribute$.MODULE$.$lessinit$greater$default$5(), NumericAttribute$.MODULE$.$lessinit$greater$default$6()), scala.reflect.ClassTag..MODULE$.apply(Attribute.class));
         StructField field = (new AttributeGroup((String)this.$(this.outputCol()), attrs)).toStructField();
         outputSchema = SchemaUtils$.MODULE$.updateField(outputSchema, field, SchemaUtils$.MODULE$.updateField$default$3());
      }

      return outputSchema;
   }

   public CountVectorizerModel copy(final ParamMap extra) {
      CountVectorizerModel copied = (CountVectorizerModel)(new CountVectorizerModel(this.uid(), this.vocabulary())).setParent(this.parent());
      return (CountVectorizerModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new CountVectorizerModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "CountVectorizerModel: uid=" + var10000 + ", vocabularySize=" + this.vocabulary().length;
   }

   // $FF: synthetic method
   public static final void $anonfun$transform$2(final Broadcast dictBr$1, final OpenHashMap termCounts$1, final LongRef tokenCount$1, final String term) {
      Option var5 = ((MapOps)dictBr$1.value()).get(term);
      if (var5 instanceof Some var6) {
         int index = BoxesRunTime.unboxToInt(var6.value());
         BoxesRunTime.boxToDouble(termCounts$1.changeValue$mcD$sp(BoxesRunTime.boxToInteger(index), (JFunction0.mcD.sp)() -> (double)1.0F, (JFunction1.mcDD.sp)(x$5) -> x$5 + (double)1.0F));
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      ++tokenCount$1.elem;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$5(final double effectiveMinTF$1, final Tuple2 x$6) {
      return x$6._2$mcD$sp() >= effectiveMinTF$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$transform$7(final double effectiveMinTF$1, final Tuple2 x$7) {
      return x$7._2$mcD$sp() >= effectiveMinTF$1;
   }

   public CountVectorizerModel(final String uid, final String[] vocabulary) {
      this.uid = uid;
      this.vocabulary = vocabulary;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      CountVectorizerParams.$init$(this);
      MLWritable.$init$(this);
      this.broadcastDict = scala.None..MODULE$;
      Statics.releaseFence();
   }

   public CountVectorizerModel() {
      this("", (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public CountVectorizerModel(final String[] vocabulary) {
      this(Identifiable$.MODULE$.randomUID("cntVecModel"), vocabulary);
      this.set(this.vocabSize(), BoxesRunTime.boxToInteger(vocabulary.length));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class CountVectorizerModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final CountVectorizerModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.vocabulary()).toImmutableArraySeq());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(CountVectorizerModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.CountVectorizerModel.CountVectorizerModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.CountVectorizerModel.CountVectorizerModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public CountVectorizerModelWriter(final CountVectorizerModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Seq vocabulary;
         // $FF: synthetic field
         public final CountVectorizerModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Seq vocabulary() {
            return this.vocabulary;
         }

         public Data copy(final Seq vocabulary) {
            return this.org$apache$spark$ml$feature$CountVectorizerModel$CountVectorizerModelWriter$Data$$$outer().new Data(vocabulary);
         }

         public Seq copy$default$1() {
            return this.vocabulary();
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
                  return this.vocabulary();
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
                  return "vocabulary";
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
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$CountVectorizerModel$CountVectorizerModelWriter$Data$$$outer() == this.org$apache$spark$ml$feature$CountVectorizerModel$CountVectorizerModelWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Seq var10000 = this.vocabulary();
                        Seq var5 = var4.vocabulary();
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
         public CountVectorizerModelWriter org$apache$spark$ml$feature$CountVectorizerModel$CountVectorizerModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Seq vocabulary) {
            this.vocabulary = vocabulary;
            if (CountVectorizerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = CountVectorizerModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final CountVectorizerModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Seq vocabulary) {
            return this.$outer.new Data(vocabulary);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.vocabulary()));
         }

         public Data$() {
            if (CountVectorizerModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = CountVectorizerModelWriter.this;
               super();
            }
         }
      }
   }

   private static class CountVectorizerModelReader extends MLReader {
      private final String className = CountVectorizerModel.class.getName();

      private String className() {
         return this.className;
      }

      public CountVectorizerModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("vocabulary", scala.collection.immutable.Nil..MODULE$).head();
         String[] vocabulary = (String[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
         CountVectorizerModel model = new CountVectorizerModel(metadata.uid(), vocabulary);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public CountVectorizerModelReader() {
      }
   }
}
