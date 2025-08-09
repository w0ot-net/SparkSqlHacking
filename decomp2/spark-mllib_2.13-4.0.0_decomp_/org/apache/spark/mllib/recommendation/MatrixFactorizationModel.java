package org.apache.spark.mllib.recommendation;

import com.clearspring.analytics.stream.cardinality.HyperLogLogPlus;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.SQLImplicits;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import org.json4s.Formats;
import org.json4s.JValue;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple1;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t5e\u0001\u0002\u0014(\u0001IB\u0001\"\u0015\u0001\u0003\u0006\u0004%\tA\u0015\u0005\t?\u0002\u0011\t\u0011)A\u0005'\"A\u0011\r\u0001BC\u0002\u0013\u0005!\r\u0003\u0005t\u0001\t\u0005\t\u0015!\u0003d\u0011!)\bA!b\u0001\n\u0003\u0011\u0007\u0002C<\u0001\u0005\u0003\u0005\u000b\u0011B2\t\u000be\u0004A\u0011\u0001>\t\u000f\u0005%\u0001\u0001\"\u0003\u0002\f!9\u00111\u0006\u0001\u0005\u0002\u00055\u0002\u0002CA\u001d\u0001\u0001&I!a\u000f\t\u000f\u0005-\u0002\u0001\"\u0001\u0002N!9\u00111\u0006\u0001\u0005\u0002\u0005}\u0003bBAG\u0001\u0011\u0005\u0011q\u0012\u0005\b\u0003?\u0003A\u0011AAQ\u0011\u001d\tI\u000b\u0001C!\u0003WCq!a1\u0001\t\u0003\t)\rC\u0004\u0002T\u0002!\t!!6\b\u000f\u0005uw\u0005#\u0001\u0002`\u001a1ae\nE\u0001\u0003CDa!_\n\u0005\u0002\u0005M\bbBA{'\u0011%\u0011q\u001f\u0005\b\u0005\u000f\u0019B\u0011\u0002B\u0005\u0011\u001d\u0011Yb\u0005C\u0005\u0005;A\u0011Ba\u000b\u0014#\u0003%IA!\f\t\u000f\t\u00053\u0003\"\u0011\u0003D\u001dA!1J\n\t\u0002\u001d\u0012iE\u0002\u0005\u0003RMA\ta\nB*\u0011\u0019I8\u0004\"\u0001\u0003V!I!qK\u000eC\u0002\u0013%!\u0011\f\u0005\t\u0005?Z\u0002\u0015!\u0003\u0003\\!Q!\u0011M\u000eC\u0002\u0013\u0005qE!\u0017\t\u0011\t\r4\u0004)A\u0005\u00057Bq!!+\u001c\t\u0003\u0011)\u0007C\u0004\u0003Bm!\tA!\u001c\t\u000f\tM4\u0004\"\u0003\u0003v!9!\u0011P\u000e\u0005\n\tm\u0004\"\u0003B@'\u0005\u0005I\u0011\u0002BA\u0005ai\u0015\r\u001e:jq\u001a\u000b7\r^8sSj\fG/[8o\u001b>$W\r\u001c\u0006\u0003Q%\naB]3d_6lWM\u001c3bi&|gN\u0003\u0002+W\u0005)Q\u000e\u001c7jE*\u0011A&L\u0001\u0006gB\f'o\u001b\u0006\u0003]=\na!\u00199bG\",'\"\u0001\u0019\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001\u0019\u0014hP&\u0011\u0005Q:T\"A\u001b\u000b\u0003Y\nQa]2bY\u0006L!\u0001O\u001b\u0003\r\u0005s\u0017PU3g!\tQT(D\u0001<\u0015\ta\u0014&\u0001\u0003vi&d\u0017B\u0001 <\u0005!\u0019\u0016M^3bE2,\u0007C\u0001!I\u001d\t\teI\u0004\u0002C\u000b6\t1I\u0003\u0002Ec\u00051AH]8pizJ\u0011AN\u0005\u0003\u000fV\nq\u0001]1dW\u0006<W-\u0003\u0002J\u0015\na1+\u001a:jC2L'0\u00192mK*\u0011q)\u000e\t\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001d.\n\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003!6\u0013q\u0001T8hO&tw-\u0001\u0003sC:\\W#A*\u0011\u0005Q\"\u0016BA+6\u0005\rIe\u000e\u001e\u0015\u0004\u0003]k\u0006C\u0001-\\\u001b\u0005I&B\u0001.,\u0003)\tgN\\8uCRLwN\\\u0005\u00039f\u0013QaU5oG\u0016\f\u0013AX\u0001\u0006a9Bd\u0006M\u0001\u0006e\u0006t7\u000e\t\u0015\u0004\u0005]k\u0016\u0001D;tKJ4U-\u0019;ve\u0016\u001cX#A2\u0011\u0007\u0011<\u0017.D\u0001f\u0015\t17&A\u0002sI\u0012L!\u0001[3\u0003\u0007I#E\t\u0005\u00035UNc\u0017BA66\u0005\u0019!V\u000f\u001d7feA\u0019A'\\8\n\u00059,$!B!se\u0006L\bC\u0001\u001bq\u0013\t\tXG\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007]k\u0016!D;tKJ4U-\u0019;ve\u0016\u001c\b\u0005K\u0002\u0005/v\u000bq\u0002\u001d:pIV\u001cGOR3biV\u0014Xm\u001d\u0015\u0004\u000b]k\u0016\u0001\u00059s_\u0012,8\r\u001e$fCR,(/Z:!Q\r1q+X\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bmlx0a\u0001\u0011\u0005q\u0004Q\"A\u0014\t\u000bE;\u0001\u0019A*)\u0007u<V\fC\u0003b\u000f\u0001\u00071\rK\u0002\u0000/vCQ!^\u0004A\u0002\rDC!a\u0001X;\"\u001aqaV/\u0002!Y\fG.\u001b3bi\u00164U-\u0019;ve\u0016\u001cHCBA\u0007\u0003'\t9\u0003E\u00025\u0003\u001fI1!!\u00056\u0005\u0011)f.\u001b;\t\u000f\u0005U\u0001\u00021\u0001\u0002\u0018\u0005!a.Y7f!\u0011\tI\"!\t\u000f\t\u0005m\u0011Q\u0004\t\u0003\u0005VJ1!a\b6\u0003\u0019\u0001&/\u001a3fM&!\u00111EA\u0013\u0005\u0019\u0019FO]5oO*\u0019\u0011qD\u001b\t\r\u0005%\u0002\u00021\u0001d\u0003!1W-\u0019;ve\u0016\u001c\u0018a\u00029sK\u0012L7\r\u001e\u000b\u0006_\u0006=\u00121\u0007\u0005\u0007\u0003cI\u0001\u0019A*\u0002\tU\u001cXM\u001d\u0005\u0007\u0003kI\u0001\u0019A*\u0002\u000fA\u0014x\u000eZ;di\"\u001a\u0011bV/\u0002=\r|WO\u001c;BaB\u0014x\u000e\u001f#jgRLgn\u0019;Vg\u0016\u0014\bK]8ek\u000e$H\u0003BA\u001f\u0003\u000b\u0002b\u0001\u000e6\u0002@\u0005}\u0002c\u0001\u001b\u0002B%\u0019\u00111I\u001b\u0003\t1{gn\u001a\u0005\b\u0003\u000fR\u0001\u0019AA%\u00035)8/\u001a:t!J|G-^2ugB!AmZA&!\u0011!$nU*\u0015\t\u0005=\u0013q\u000b\t\u0005I\u001e\f\t\u0006E\u0002}\u0003'J1!!\u0016(\u0005\u0019\u0011\u0016\r^5oO\"9\u0011qI\u0006A\u0002\u0005%\u0003\u0006B\u0006X\u00037\n#!!\u0018\u0002\u000bAr\u0013H\f\u0019\u0015\t\u0005\u0005\u0014\u0011\u000f\t\u0007\u0003G\ni'!\u0015\u000e\u0005\u0005\u0015$\u0002BA4\u0003S\nAA[1wC*\u0019\u00111N\u0016\u0002\u0007\u0005\u0004\u0018.\u0003\u0003\u0002p\u0005\u0015$a\u0002&bm\u0006\u0014F\t\u0012\u0005\b\u0003\u000fb\u0001\u0019AA:!!\t\u0019'!\u001e\u0002z\u0005e\u0014\u0002BA<\u0003K\u00121BS1wCB\u000b\u0017N\u001d*E\tB!\u00111PAB\u001b\t\tiH\u0003\u0003\u0002\u0000\u0005\u0005\u0015\u0001\u00027b]\u001eT!!a\u001a\n\t\u0005\u0015\u0015Q\u0010\u0002\b\u0013:$XmZ3sQ\u0011aq+!#\"\u0005\u0005-\u0015!B\u0019/e9\u0002\u0014!\u0005:fG>lW.\u001a8e!J|G-^2ugR1\u0011\u0011SAJ\u0003+\u0003B\u0001N7\u0002R!1\u0011\u0011G\u0007A\u0002MCa!a&\u000e\u0001\u0004\u0019\u0016a\u00018v[\"\"QbVANC\t\ti*A\u00032]Er\u0003'\u0001\bsK\u000e|W.\\3oIV\u001bXM]:\u0015\r\u0005E\u00151UAS\u0011\u0019\t)D\u0004a\u0001'\"1\u0011q\u0013\bA\u0002MCCAD,\u0002\u001c\u0006!1/\u0019<f)\u0019\ti!!,\u0002:\"9\u0011qV\bA\u0002\u0005E\u0016AA:d!\u0011\t\u0019,!.\u000e\u0003-J1!a.,\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0011\u001d\tYl\u0004a\u0001\u0003/\tA\u0001]1uQ\"\"qbVA`C\t\t\t-A\u00032]Mr\u0003'A\rsK\u000e|W.\\3oIB\u0013x\u000eZ;diN4uN]+tKJ\u001cH\u0003BAd\u0003\u0017\u0004B\u0001Z4\u0002JB)AG[*\u0002\u0012\"1\u0011q\u0013\tA\u0002MCC\u0001E,\u0002P\u0006\u0012\u0011\u0011[\u0001\u0006c9\"d\u0006M\u0001\u001ae\u0016\u001cw.\\7f]\u0012,6/\u001a:t\r>\u0014\bK]8ek\u000e$8\u000f\u0006\u0003\u0002H\u0006]\u0007BBAL#\u0001\u00071\u000b\u000b\u0003\u0012/\u0006=\u0007f\u0001\u0001X;\u0006AR*\u0019;sSb4\u0015m\u0019;pe&T\u0018\r^5p]6{G-\u001a7\u0011\u0005q\u001c2CB\n4\u0003G\fI\u000f\u0005\u0003;\u0003K\\\u0018bAAtw\t1Aj\\1eKJ\u0004B!a;\u0002r6\u0011\u0011Q\u001e\u0006\u0005\u0003_\f\t)\u0001\u0002j_&\u0019\u0011*!<\u0015\u0005\u0005}\u0017!\u0003:fG>lW.\u001a8e)!\tI0!@\u0003\u0002\t\u0015\u0001\u0003\u0002\u001bn\u0003w\u0004B\u0001\u000e6T_\"1\u0011q`\u000bA\u00021\f1C]3d_6lWM\u001c3U_\u001a+\u0017\r^;sKNDaAa\u0001\u0016\u0001\u0004\u0019\u0017!\u0006:fG>lW.\u001a8eC\ndWMR3biV\u0014Xm\u001d\u0005\u0007\u0003/+\u0002\u0019A*\u0002\u001fI,7m\\7nK:$gi\u001c:BY2$\"Ba\u0003\u0003\u0010\tE!Q\u0003B\r!\u0011!wM!\u0004\u0011\u000bQR7+!?\t\u000bE3\u0002\u0019A*\t\r\tMa\u00031\u0001d\u0003-\u0019(o\u0019$fCR,(/Z:\t\r\t]a\u00031\u0001d\u0003-!7\u000f\u001e$fCR,(/Z:\t\r\u0005]e\u00031\u0001T\u0003!\u0011Gn\\2lS\u001aLHC\u0002B\u0010\u0005K\u00119\u0003\u0005\u0003eO\n\u0005\u0002#\u0002\u001bk\u0005Ga\u0007c\u0001\u001bn'\"1\u0011\u0011F\fA\u0002\rD\u0001B!\u000b\u0018!\u0003\u0005\raU\u0001\nE2|7m[*ju\u0016\f!C\u00197pG.Lg-\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!q\u0006\u0016\u0004'\nE2F\u0001B\u001a!\u0011\u0011)D!\u0010\u000e\u0005\t]\"\u0002\u0002B\u001d\u0005w\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005i+\u0014\u0002\u0002B \u0005o\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u0011aw.\u00193\u0015\u000bm\u0014)Ea\u0012\t\u000f\u0005=\u0016\u00041\u0001\u00022\"9\u00111X\rA\u0002\u0005]\u0001\u0006B\rX\u0003\u007f\u000bAbU1wK2{\u0017\r\u001a,2?B\u00022Aa\u0014\u001c\u001b\u0005\u0019\"\u0001D*bm\u0016du.\u00193Wc}\u00034CA\u000e4)\t\u0011i%A\tuQ&\u001chi\u001c:nCR4VM]:j_:,\"Aa\u0017\u0011\t\u0005m$QL\u0005\u0005\u0003G\ti(\u0001\nuQ&\u001chi\u001c:nCR4VM]:j_:\u0004\u0013!\u0004;iSN\u001cE.Y:t\u001d\u0006lW-\u0001\buQ&\u001c8\t\\1tg:\u000bW.\u001a\u0011\u0015\r\u00055!q\rB6\u0011\u0019\u0011I'\ta\u0001w\u0006)Qn\u001c3fY\"9\u00111X\u0011A\u0002\u0005]A#B>\u0003p\tE\u0004bBAXE\u0001\u0007\u0011\u0011\u0017\u0005\b\u0003w\u0013\u0003\u0019AA\f\u0003!)8/\u001a:QCRDG\u0003BA\f\u0005oBq!a/$\u0001\u0004\t9\"A\u0006qe>$Wo\u0019;QCRDG\u0003BA\f\u0005{Bq!a/%\u0001\u0004\t9\"\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003\u0004B!\u00111\u0010BC\u0013\u0011\u00119)! \u0003\r=\u0013'.Z2uQ\u0011\u0019r+a0)\tI9\u0016q\u0018"
)
public class MatrixFactorizationModel implements Saveable, Serializable, Logging {
   private final int rank;
   private final RDD userFeatures;
   private final RDD productFeatures;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static MatrixFactorizationModel load(final SparkContext sc, final String path) {
      return MatrixFactorizationModel$.MODULE$.load(sc, path);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public int rank() {
      return this.rank;
   }

   public RDD userFeatures() {
      return this.userFeatures;
   }

   public RDD productFeatures() {
      return this.productFeatures;
   }

   private void validateFeatures(final String name, final RDD features) {
      .MODULE$.require(((double[])((Tuple2)features.first())._2()).length == this.rank(), () -> name + " feature dimension does not match the rank " + this.rank() + ".");
      if (features.partitioner().isEmpty()) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " factor does not have a partitioner. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FEATURE_NAME..MODULE$, name)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Prediction on individual records could be slow."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      label23: {
         StorageLevel var10000 = features.getStorageLevel();
         StorageLevel var3 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var3 == null) {
               break label23;
            }
         } else if (var10000.equals(var3)) {
            break label23;
         }

         return;
      }

      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " factor is not cached. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FEATURE_NAME..MODULE$, name)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Prediction could be slow."})))).log(scala.collection.immutable.Nil..MODULE$))));
   }

   public double predict(final int user, final int product) {
      Seq userFeatureSeq = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.userFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).lookup(BoxesRunTime.boxToInteger(user));
      .MODULE$.require(userFeatureSeq.nonEmpty(), () -> "userId: " + user + " not found in the model");
      Seq productFeatureSeq = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.productFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).lookup(BoxesRunTime.boxToInteger(product));
      .MODULE$.require(productFeatureSeq.nonEmpty(), () -> "productId: " + product + " not found in the model");
      double[] userVector = (double[])userFeatureSeq.head();
      double[] productVector = (double[])productFeatureSeq.head();
      return BLAS$.MODULE$.nativeBLAS().ddot(this.rank(), userVector, 1, productVector, 1);
   }

   private Tuple2 countApproxDistinctUserProduct(final RDD usersProducts) {
      HyperLogLogPlus zeroCounterUser = new HyperLogLogPlus(4, 0);
      HyperLogLogPlus zeroCounterProduct = new HyperLogLogPlus(4, 0);
      Tuple2 aggregated = (Tuple2)usersProducts.aggregate(new Tuple2(zeroCounterUser, zeroCounterProduct), (hllTuple, v) -> {
         ((HyperLogLogPlus)hllTuple._1()).offer(BoxesRunTime.boxToInteger(v._1$mcI$sp()));
         ((HyperLogLogPlus)hllTuple._2()).offer(BoxesRunTime.boxToInteger(v._2$mcI$sp()));
         return hllTuple;
      }, (h1, h2) -> {
         ((HyperLogLogPlus)h1._1()).addAll((HyperLogLogPlus)h2._1());
         ((HyperLogLogPlus)h1._2()).addAll((HyperLogLogPlus)h2._2());
         return h1;
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      return new Tuple2.mcJJ.sp(((HyperLogLogPlus)aggregated._1()).cardinality(), ((HyperLogLogPlus)aggregated._2()).cardinality());
   }

   public RDD predict(final RDD usersProducts) {
      Tuple2 var4 = this.countApproxDistinctUserProduct(usersProducts);
      if (var4 != null) {
         long usersCount = var4._1$mcJ$sp();
         long productsCount = var4._2$mcJ$sp();
         Tuple2.mcJJ.sp var3 = new Tuple2.mcJJ.sp(usersCount, productsCount);
         long usersCount = ((Tuple2)var3)._1$mcJ$sp();
         long productsCount = ((Tuple2)var3)._2$mcJ$sp();
         if (usersCount < productsCount) {
            RDD users = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.userFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).join(usersProducts).map((x0$1) -> {
               if (x0$1 != null) {
                  int user = x0$1._1$mcI$sp();
                  Tuple2 var4 = (Tuple2)x0$1._2();
                  if (var4 != null) {
                     double[] uFeatures = (double[])var4._1();
                     int product = var4._2$mcI$sp();
                     return new Tuple2(BoxesRunTime.boxToInteger(product), new Tuple2(BoxesRunTime.boxToInteger(user), uFeatures));
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(users, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).join(this.productFeatures()).map((x0$2) -> {
               if (x0$2 != null) {
                  int product = x0$2._1$mcI$sp();
                  Tuple2 var4 = (Tuple2)x0$2._2();
                  if (var4 != null) {
                     Tuple2 var5 = (Tuple2)var4._1();
                     double[] pFeatures = (double[])var4._2();
                     if (var5 != null) {
                        int user = var5._1$mcI$sp();
                        double[] uFeatures = (double[])var5._2();
                        return new Rating(user, product, BLAS$.MODULE$.nativeBLAS().ddot(uFeatures.length, uFeatures, 1, pFeatures, 1));
                     }
                  }
               }

               throw new MatchError(x0$2);
            }, scala.reflect.ClassTag..MODULE$.apply(Rating.class));
         } else {
            RDD products = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.productFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).join(usersProducts.map((x$2) -> x$2.swap$mcII$sp(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).map((x0$3) -> {
               if (x0$3 != null) {
                  int product = x0$3._1$mcI$sp();
                  Tuple2 var4 = (Tuple2)x0$3._2();
                  if (var4 != null) {
                     double[] pFeatures = (double[])var4._1();
                     int user = var4._2$mcI$sp();
                     return new Tuple2(BoxesRunTime.boxToInteger(user), new Tuple2(BoxesRunTime.boxToInteger(product), pFeatures));
                  }
               }

               throw new MatchError(x0$3);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            return org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(products, scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).join(this.userFeatures()).map((x0$4) -> {
               if (x0$4 != null) {
                  int user = x0$4._1$mcI$sp();
                  Tuple2 var4 = (Tuple2)x0$4._2();
                  if (var4 != null) {
                     Tuple2 var5 = (Tuple2)var4._1();
                     double[] uFeatures = (double[])var4._2();
                     if (var5 != null) {
                        int product = var5._1$mcI$sp();
                        double[] pFeatures = (double[])var5._2();
                        return new Rating(user, product, BLAS$.MODULE$.nativeBLAS().ddot(uFeatures.length, uFeatures, 1, pFeatures, 1));
                     }
                  }
               }

               throw new MatchError(x0$4);
            }, scala.reflect.ClassTag..MODULE$.apply(Rating.class));
         }
      } else {
         throw new MatchError(var4);
      }
   }

   public JavaRDD predict(final JavaPairRDD usersProducts) {
      return this.predict(usersProducts.rdd()).toJavaRDD();
   }

   public Rating[] recommendProducts(final int user, final int num) {
      Seq userFeatureSeq = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.userFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).lookup(BoxesRunTime.boxToInteger(user));
      .MODULE$.require(userFeatureSeq.nonEmpty(), () -> "userId: " + user + " not found in the model");
      return (Rating[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])MatrixFactorizationModel$.MODULE$.org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommend((double[])userFeatureSeq.head(), this.productFeatures(), num)), (t) -> new Rating(user, t._1$mcI$sp(), t._2$mcD$sp()), scala.reflect.ClassTag..MODULE$.apply(Rating.class));
   }

   public Rating[] recommendUsers(final int product, final int num) {
      Seq productFeatureSeq = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.productFeatures(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)), scala.math.Ordering.Int..MODULE$).lookup(BoxesRunTime.boxToInteger(product));
      .MODULE$.require(productFeatureSeq.nonEmpty(), () -> "productId: " + product + " not found in the model");
      return (Rating[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])MatrixFactorizationModel$.MODULE$.org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommend((double[])productFeatureSeq.head(), this.userFeatures(), num)), (t) -> new Rating(t._1$mcI$sp(), product, t._2$mcD$sp()), scala.reflect.ClassTag..MODULE$.apply(Rating.class));
   }

   public void save(final SparkContext sc, final String path) {
      MatrixFactorizationModel.SaveLoadV1_0$.MODULE$.save(this, path);
   }

   public RDD recommendProductsForUsers(final int num) {
      return MatrixFactorizationModel$.MODULE$.org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommendForAll(this.rank(), this.userFeatures(), this.productFeatures(), num).map((x0$1) -> {
         if (x0$1 != null) {
            int user = x0$1._1$mcI$sp();
            Tuple2[] top = (Tuple2[])x0$1._2();
            Rating[] ratings = (Rating[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])top), (x0$2) -> {
               if (x0$2 != null) {
                  int product = x0$2._1$mcI$sp();
                  double rating = x0$2._2$mcD$sp();
                  return new Rating(user, product, rating);
               } else {
                  throw new MatchError(x0$2);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Rating.class));
            return new Tuple2(BoxesRunTime.boxToInteger(user), ratings);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public RDD recommendUsersForProducts(final int num) {
      return MatrixFactorizationModel$.MODULE$.org$apache$spark$mllib$recommendation$MatrixFactorizationModel$$recommendForAll(this.rank(), this.productFeatures(), this.userFeatures(), num).map((x0$1) -> {
         if (x0$1 != null) {
            int product = x0$1._1$mcI$sp();
            Tuple2[] top = (Tuple2[])x0$1._2();
            Rating[] ratings = (Rating[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])top), (x0$2) -> {
               if (x0$2 != null) {
                  int user = x0$2._1$mcI$sp();
                  double rating = x0$2._2$mcD$sp();
                  return new Rating(user, product, rating);
               } else {
                  throw new MatchError(x0$2);
               }
            }, scala.reflect.ClassTag..MODULE$.apply(Rating.class));
            return new Tuple2(BoxesRunTime.boxToInteger(product), ratings);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public MatrixFactorizationModel(final int rank, final RDD userFeatures, final RDD productFeatures) {
      this.rank = rank;
      this.userFeatures = userFeatures;
      this.productFeatures = productFeatures;
      Logging.$init$(this);
      .MODULE$.require(rank > 0);
      this.validateFeatures("User", userFeatures);
      this.validateFeatures("Product", productFeatures);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();
      private static final String thisFormatVersion = "1.0";
      private static final String thisClassName = "org.apache.spark.mllib.recommendation.MatrixFactorizationModel";

      private String thisFormatVersion() {
         return thisFormatVersion;
      }

      public String thisClassName() {
         return thisClassName;
      }

      public void save(final MatrixFactorizationModel model, final String path) {
         SparkContext sc = model.userFeatures().sparkContext();
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("rank"), BoxesRunTime.boxToInteger(model.rank())), (x) -> $anonfun$save$4(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         SQLImplicits var10000 = spark.implicits();
         RDD var13 = model.userFeatures();
         SQLImplicits var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator11$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
            }

            public $typecreator11$1() {
            }
         }

         var10000.rddToDatasetHolder(var13, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator11$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"id", "features"}))).write().parquet(this.userPath(path));
         var10000 = spark.implicits();
         var13 = model.productFeatures();
         var10002 = spark.implicits();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator21$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Int").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Double").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
            }

            public $typecreator21$1() {
            }
         }

         var10000.rddToDatasetHolder(var13, var10002.newProductEncoder(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator21$1()))).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"id", "features"}))).write().parquet(this.productPath(path));
      }

      public MatrixFactorizationModel load(final SparkContext sc, final String path) {
         Formats formats = org.json4s.DefaultFormats..MODULE$;
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         Tuple3 var7 = Loader$.MODULE$.loadMetadata(sc, path);
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            String formatVersion;
            JValue metadata;
            Predef var10000;
            boolean var10001;
            label36: {
               label35: {
                  String className = (String)var7._1();
                  String formatVersion = (String)var7._2();
                  JValue metadata = (JValue)var7._3();
                  Tuple3 var6 = new Tuple3(className, formatVersion, metadata);
                  String className = (String)var6._1();
                  formatVersion = (String)var6._2();
                  metadata = (JValue)var6._3();
                  var10000 = .MODULE$;
                  String var14 = this.thisClassName();
                  if (className == null) {
                     if (var14 == null) {
                        break label35;
                     }
                  } else if (className.equals(var14)) {
                     break label35;
                  }

                  var10001 = false;
                  break label36;
               }

               var10001 = true;
            }

            label28: {
               label27: {
                  var10000.assert(var10001);
                  var10000 = .MODULE$;
                  String var15 = this.thisFormatVersion();
                  if (formatVersion == null) {
                     if (var15 == null) {
                        break label27;
                     }
                  } else if (formatVersion.equals(var15)) {
                     break label27;
                  }

                  var10001 = false;
                  break label28;
               }

               var10001 = true;
            }

            var10000.assert(var10001);
            int rank = BoxesRunTime.unboxToInt(org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(metadata), "rank")), formats, scala.reflect.ManifestFactory..MODULE$.Int()));
            RDD userFeatures = spark.read().parquet(this.userPath(path)).rdd().map((x0$1) -> {
               if (x0$1 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                     Object id = ((SeqOps)var3.get()).apply(0);
                     Object features = ((SeqOps)var3.get()).apply(1);
                     if (id instanceof Integer) {
                        int var6 = BoxesRunTime.unboxToInt(id);
                        if (features instanceof scala.collection.Seq) {
                           scala.collection.Seq var7 = (scala.collection.Seq)features;
                           return new Tuple2(BoxesRunTime.boxToInteger(var6), var7.toArray(scala.reflect.ClassTag..MODULE$.Double()));
                        }
                     }
                  }
               }

               throw new MatchError(x0$1);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            RDD productFeatures = spark.read().parquet(this.productPath(path)).rdd().map((x0$2) -> {
               if (x0$2 != null) {
                  Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$2);
                  if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(2) == 0) {
                     Object id = ((SeqOps)var3.get()).apply(0);
                     Object features = ((SeqOps)var3.get()).apply(1);
                     if (id instanceof Integer) {
                        int var6 = BoxesRunTime.unboxToInt(id);
                        if (features instanceof scala.collection.Seq) {
                           scala.collection.Seq var7 = (scala.collection.Seq)features;
                           return new Tuple2(BoxesRunTime.boxToInteger(var6), var7.toArray(scala.reflect.ClassTag..MODULE$.Double()));
                        }
                     }
                  }
               }

               throw new MatchError(x0$2);
            }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            return new MatrixFactorizationModel(rank, userFeatures, productFeatures);
         }
      }

      private String userPath(final String path) {
         return (new Path(Loader$.MODULE$.dataPath(path), "user")).toUri().toString();
      }

      private String productPath(final String path) {
         return (new Path(Loader$.MODULE$.dataPath(path), "product")).toUri().toString();
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$4(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
