package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.DoubleAccumulator;
import org.apache.spark.util.random.XORShiftRandom;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.DoubleRef;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\tEh\u0001B\u001f?\u0001%C\u0001B\u0019\u0001\u0003\u0002\u0004%Ia\u0019\u0005\tO\u0002\u0011\t\u0019!C\u0005Q\"Aa\u000e\u0001B\u0001B\u0003&A\r\u0003\u0005p\u0001\t\u0005\r\u0011\"\u0003d\u0011!\u0001\bA!a\u0001\n\u0013\t\b\u0002C:\u0001\u0005\u0003\u0005\u000b\u0015\u00023\t\u0011Q\u0004!\u00111A\u0005\nUD\u0001B \u0001\u0003\u0002\u0004%Ia \u0005\n\u0003\u0007\u0001!\u0011!Q!\nYD\u0011\"!\u0002\u0001\u0005\u0003\u0007I\u0011B2\t\u0015\u0005\u001d\u0001A!a\u0001\n\u0013\tI\u0001C\u0005\u0002\u000e\u0001\u0011\t\u0011)Q\u0005I\"Q\u0011q\u0002\u0001\u0003\u0002\u0004%I!!\u0005\t\u0015\u0005e\u0001A!a\u0001\n\u0013\tY\u0002\u0003\u0006\u0002 \u0001\u0011\t\u0011)Q\u0005\u0003'A!\"!\t\u0001\u0005\u0003\u0007I\u0011BA\u0012\u0011)\tY\u0003\u0001BA\u0002\u0013%\u0011Q\u0006\u0005\u000b\u0003c\u0001!\u0011!Q!\n\u0005\u0015\u0002\"CA\u001a\u0001\t\u0005\r\u0011\"\u0003v\u0011)\t)\u0004\u0001BA\u0002\u0013%\u0011q\u0007\u0005\n\u0003w\u0001!\u0011!Q!\nYDq!!\u0010\u0001\t\u0013\ty\u0004C\u0004\u0002>\u0001!I!a\u0015\t\u000f\u0005u\u0002\u0001\"\u0001\u0002t!1\u0011q\u000f\u0001\u0005\u0002\rDq!a \u0001\t\u0003\t\t\t\u0003\u0004\u0002\n\u0002!\ta\u0019\u0005\b\u0003\u001b\u0003A\u0011AAH\u0011\u0019\t)\n\u0001C\u0001k\"9\u0011\u0011\u0014\u0001\u0005\u0002\u0005m\u0005BBAQ\u0001\u0011\u00051\rC\u0004\u0002&\u0002!\t!a*\t\u000f\u00055\u0006\u0001\"\u0001\u0002\u0012!9\u0011\u0011\u0017\u0001\u0005\u0002\u0005M\u0006bBA]\u0001\u0011\u0005\u00111\u0005\u0005\b\u0003{\u0003A\u0011AA`\u0011\u0019\t)\r\u0001C\u0001k\"9\u0011Q\u001a\u0001\u0005\u0002\u0005=\u0007\"CAk\u0001\u0001\u0007I\u0011BAl\u0011%\t)\u000f\u0001a\u0001\n\u0013\t9\u000f\u0003\u0005\u0002l\u0002\u0001\u000b\u0015BAm\u0011\u001d\ti\u000f\u0001C\u0001\u0003_Dq!a>\u0001\t\u0003\tI\u0010\u0003\u0005\u0003\u001a\u0001!\tA\u0011B\u000e\u0011!\u0011I\u0005\u0001C\u0001\u0005\n-\u0003b\u0002B+\u0001\u0011%!q\u000b\u0005\b\u0005K\u0002A\u0011\u0002B4\u0011!\u0011i\u0007\u0001C\u0001}\t=ta\u0002B@}!\u0005!\u0011\u0011\u0004\u0007{yB\tAa!\t\u000f\u0005u\"\u0007\"\u0001\u0003\u0014\"I!Q\u0013\u001aC\u0002\u0013\u0005!q\u0013\u0005\t\u0005K\u0013\u0004\u0015!\u0003\u0003\u001a\"I!\u0011\u0016\u001aC\u0002\u0013\u0005!q\u0013\u0005\t\u0005[\u0013\u0004\u0015!\u0003\u0003\u001a\"9!\u0011\u0017\u001a\u0005\u0002\tM\u0006b\u0002BYe\u0011\u0005!Q\u0019\u0005\b\u0005c\u0013D\u0011\u0001Bi\u0011!\u0011YN\rC\u0001\u0005\nu\u0007\"\u0003Bre\u0005\u0005I\u0011\u0002Bs\u0005\u0019YU*Z1og*\u0011q\bQ\u0001\u000bG2,8\u000f^3sS:<'BA!C\u0003\u0015iG\u000e\\5c\u0015\t\u0019E)A\u0003ta\u0006\u00148N\u0003\u0002F\r\u00061\u0011\r]1dQ\u0016T\u0011aR\u0001\u0004_J<7\u0001A\n\u0005\u0001)\u0003F\f\u0005\u0002L\u001d6\tAJC\u0001N\u0003\u0015\u00198-\u00197b\u0013\tyEJ\u0001\u0004B]f\u0014VM\u001a\t\u0003#fs!AU,\u000f\u0005M3V\"\u0001+\u000b\u0005UC\u0015A\u0002\u001fs_>$h(C\u0001N\u0013\tAF*A\u0004qC\u000e\\\u0017mZ3\n\u0005i[&\u0001D*fe&\fG.\u001b>bE2,'B\u0001-M!\ti\u0006-D\u0001_\u0015\ty&)\u0001\u0005j]R,'O\\1m\u0013\t\tgLA\u0004M_\u001e<\u0017N\\4\u0002\u0003-,\u0012\u0001\u001a\t\u0003\u0017\u0016L!A\u001a'\u0003\u0007%sG/A\u0003l?\u0012*\u0017\u000f\u0006\u0002jYB\u00111J[\u0005\u0003W2\u0013A!\u00168ji\"9QNAA\u0001\u0002\u0004!\u0017a\u0001=%c\u0005\u00111\u000eI\u0001\u000e[\u0006D\u0018\n^3sCRLwN\\:\u0002#5\f\u00070\u0013;fe\u0006$\u0018n\u001c8t?\u0012*\u0017\u000f\u0006\u0002je\"9Q.BA\u0001\u0002\u0004!\u0017AD7bq&#XM]1uS>t7\u000fI\u0001\u0013S:LG/[1mSj\fG/[8o\u001b>$W-F\u0001w!\t98P\u0004\u0002ysB\u00111\u000bT\u0005\u0003u2\u000ba\u0001\u0015:fI\u00164\u0017B\u0001?~\u0005\u0019\u0019FO]5oO*\u0011!\u0010T\u0001\u0017S:LG/[1mSj\fG/[8o\u001b>$Wm\u0018\u0013fcR\u0019\u0011.!\u0001\t\u000f5D\u0011\u0011!a\u0001m\u0006\u0019\u0012N\\5uS\u0006d\u0017N_1uS>tWj\u001c3fA\u0005\u0019\u0012N\\5uS\u0006d\u0017N_1uS>t7\u000b^3qg\u00069\u0012N\\5uS\u0006d\u0017N_1uS>t7\u000b^3qg~#S-\u001d\u000b\u0004S\u0006-\u0001bB7\f\u0003\u0003\u0005\r\u0001Z\u0001\u0015S:LG/[1mSj\fG/[8o'R,\u0007o\u001d\u0011\u0002\u000f\u0015\u00048/\u001b7p]V\u0011\u00111\u0003\t\u0004\u0017\u0006U\u0011bAA\f\u0019\n1Ai\\;cY\u0016\f1\"\u001a9tS2|gn\u0018\u0013fcR\u0019\u0011.!\b\t\u00115t\u0011\u0011!a\u0001\u0003'\t\u0001\"\u001a9tS2|g\u000eI\u0001\u0005g\u0016,G-\u0006\u0002\u0002&A\u00191*a\n\n\u0007\u0005%BJ\u0001\u0003M_:<\u0017\u0001C:fK\u0012|F%Z9\u0015\u0007%\fy\u0003\u0003\u0005n#\u0005\u0005\t\u0019AA\u0013\u0003\u0015\u0019X-\u001a3!\u0003=!\u0017n\u001d;b]\u000e,W*Z1tkJ,\u0017a\u00053jgR\fgnY3NK\u0006\u001cXO]3`I\u0015\fHcA5\u0002:!9Q\u000eFA\u0001\u0002\u00041\u0018\u0001\u00053jgR\fgnY3NK\u0006\u001cXO]3!\u0003\u0019a\u0014N\\5u}Q\u0001\u0012\u0011IA#\u0003\u000f\nI%a\u0013\u0002N\u0005=\u0013\u0011\u000b\t\u0004\u0003\u0007\u0002Q\"\u0001 \t\u000b\t4\u0002\u0019\u00013\t\u000b=4\u0002\u0019\u00013\t\u000bQ4\u0002\u0019\u0001<\t\r\u0005\u0015a\u00031\u0001e\u0011\u001d\tyA\u0006a\u0001\u0003'Aq!!\t\u0017\u0001\u0004\t)\u0003\u0003\u0004\u00024Y\u0001\rA\u001e\u000b\u000f\u0003\u0003\n)&a\u0016\u0002Z\u0005m\u0013QLA0\u0011\u0015\u0011w\u00031\u0001e\u0011\u0015yw\u00031\u0001e\u0011\u0015!x\u00031\u0001w\u0011\u0019\t)a\u0006a\u0001I\"9\u0011qB\fA\u0002\u0005M\u0001bBA\u0011/\u0001\u0007\u0011Q\u0005\u0015\u0006/\u0005\r\u0014q\u000e\t\u0005\u0003K\nY'\u0004\u0002\u0002h)\u0019\u0011\u0011\u000e\"\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002n\u0005\u001d$!B*j]\u000e,\u0017EAA9\u0003\u0015\u0001d\u0006\u000f\u00181)\t\t\t\u0005K\u0003\u0019\u0003G\ny'\u0001\u0003hKR\\\u0005&B\r\u0002d\u0005m\u0014EAA?\u0003\u0015\td\u0006\u000e\u00181\u0003\u0011\u0019X\r^&\u0015\t\u0005\r\u0015QQ\u0007\u0002\u0001!)!M\u0007a\u0001I\"*!$a\u0019\u0002p\u0005\u0001r-\u001a;NCbLE/\u001a:bi&|gn\u001d\u0015\u00067\u0005\r\u00141P\u0001\u0011g\u0016$X*\u0019=Ji\u0016\u0014\u0018\r^5p]N$B!a!\u0002\u0012\")q\u000e\ba\u0001I\"*A$a\u0019\u0002p\u0005)r-\u001a;J]&$\u0018.\u00197ju\u0006$\u0018n\u001c8N_\u0012,\u0007&B\u000f\u0002d\u0005m\u0014!F:fi&s\u0017\u000e^5bY&T\u0018\r^5p]6{G-\u001a\u000b\u0005\u0003\u0007\u000bi\nC\u0003u=\u0001\u0007a\u000fK\u0003\u001f\u0003G\ny'\u0001\fhKRLe.\u001b;jC2L'0\u0019;j_:\u001cF/\u001a9tQ\u0015y\u00121MA>\u0003Y\u0019X\r^%oSRL\u0017\r\\5{CRLwN\\*uKB\u001cH\u0003BAB\u0003SCa!!\u0002!\u0001\u0004!\u0007&\u0002\u0011\u0002d\u0005=\u0014AC4fi\u0016\u00038/\u001b7p]\"*\u0011%a\u0019\u0002|\u0005Q1/\u001a;FaNLGn\u001c8\u0015\t\u0005\r\u0015Q\u0017\u0005\b\u0003\u001f\u0011\u0003\u0019AA\nQ\u0015\u0011\u00131MA8\u0003\u001d9W\r^*fK\u0012DSaIA2\u0003w\nqa]3u'\u0016,G\r\u0006\u0003\u0002\u0004\u0006\u0005\u0007bBA\u0011I\u0001\u0007\u0011Q\u0005\u0015\u0006I\u0005\r\u00141P\u0001\u0013O\u0016$H)[:uC:\u001cW-T3bgV\u0014X\rK\u0003&\u0003G\nI-\t\u0002\u0002L\u0006)!G\f\u001b/a\u0005\u00112/\u001a;ESN$\u0018M\\2f\u001b\u0016\f7/\u001e:f)\u0011\t\u0019)!5\t\r\u0005Mb\u00051\u0001wQ\u00151\u00131MAe\u00031Ig.\u001b;jC2lu\u000eZ3m+\t\tI\u000eE\u0003L\u00037\fy.C\u0002\u0002^2\u0013aa\u00149uS>t\u0007\u0003BA\"\u0003CL1!a9?\u0005-YU*Z1og6{G-\u001a7\u0002!%t\u0017\u000e^5bY6{G-\u001a7`I\u0015\fHcA5\u0002j\"AQ\u000eKA\u0001\u0002\u0004\tI.A\u0007j]&$\u0018.\u00197N_\u0012,G\u000eI\u0001\u0010g\u0016$\u0018J\\5uS\u0006dWj\u001c3fYR!\u00111QAy\u0011\u001d\t\u0019P\u000ba\u0001\u0003?\fQ!\\8eK2DSAKA2\u0003w\n1A];o)\u0011\ty.a?\t\u000f\u0005u8\u00061\u0001\u0002\u0000\u0006!A-\u0019;b!\u0019\u0011\tAa\u0002\u0003\f5\u0011!1\u0001\u0006\u0004\u0005\u000b\u0011\u0015a\u0001:eI&!!\u0011\u0002B\u0002\u0005\r\u0011F\t\u0012\t\u0005\u0005\u001b\u0011\u0019\"\u0004\u0002\u0003\u0010)\u0019!\u0011\u0003!\u0002\r1Lg.\u00197h\u0013\u0011\u0011)Ba\u0004\u0003\rY+7\r^8sQ\u0015Y\u00131MA8\u00035\u0011XO\\,ji\"<V-[4iiRA\u0011q\u001cB\u000f\u0005S\u0011\u0019\u0004C\u0004\u0003 1\u0002\rA!\t\u0002\u0013%t7\u000f^1oG\u0016\u001c\bC\u0002B\u0001\u0005\u000f\u0011\u0019\u0003E\u0004L\u0005K\u0011Y!a\u0005\n\u0007\t\u001dBJ\u0001\u0004UkBdWM\r\u0005\b\u0005Wa\u0003\u0019\u0001B\u0017\u0003EA\u0017M\u001c3mKB+'o]5ti\u0016t7-\u001a\t\u0004\u0017\n=\u0012b\u0001B\u0019\u0019\n9!i\\8mK\u0006t\u0007b\u0002B\u001bY\u0001\u0007!qG\u0001\u0006S:\u001cHO\u001d\t\u0006\u0017\u0006m'\u0011\b\t\u0005\u0005w\u0011)%\u0004\u0002\u0003>)!!q\bB!\u0003\u0011)H/\u001b7\u000b\u0007\t\r#)\u0001\u0002nY&!!q\tB\u001f\u0005=Ien\u001d;sk6,g\u000e^1uS>t\u0017AC5oSRL\u0017\r\\5{KR!!Q\nB*!\u0015Y%q\nB\u0006\u0013\r\u0011\t\u0006\u0014\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0003{l\u0003\u0019AA\u0000\u0003Y\u0011XO\\!mO>\u0014\u0018\u000e\u001e5n/&$\bnV3jO\"$HCBAp\u00053\u0012\u0019\u0007C\u0004\u0002~:\u0002\rAa\u0017\u0011\r\t\u0005!q\u0001B/!\u0011\t\u0019Ea\u0018\n\u0007\t\u0005dH\u0001\bWK\u000e$xN],ji\"tuN]7\t\u000f\tUb\u00061\u0001\u00038\u0005Q\u0011N\\5u%\u0006tGm\\7\u0015\t\t%$1\u000e\t\u0006\u0017\n=#Q\f\u0005\b\u0003{|\u0003\u0019\u0001B.\u0003IIg.\u001b;L\u001b\u0016\fgn\u001d)be\u0006dG.\u001a7\u0015\r\t%$\u0011\u000fB:\u0011\u001d\ti\u0010\ra\u0001\u00057BqA!\u001e1\u0001\u0004\u00119(A\feSN$\u0018M\\2f\u001b\u0016\f7/\u001e:f\u0013:\u001cH/\u00198dKB!\u00111\tB=\u0013\r\u0011YH\u0010\u0002\u0010\t&\u001cH/\u00198dK6+\u0017m];sK\"*\u0001!a\u0019\u0002p\u000511*T3b]N\u00042!a\u00113'\u0011\u0011$J!\"\u0011\t\t\u001d%\u0011S\u0007\u0003\u0005\u0013SAAa#\u0003\u000e\u0006\u0011\u0011n\u001c\u0006\u0003\u0005\u001f\u000bAA[1wC&\u0019!L!#\u0015\u0005\t\u0005\u0015A\u0002*B\u001d\u0012{U*\u0006\u0002\u0003\u001aB!!1\u0014BQ\u001b\t\u0011iJ\u0003\u0003\u0003 \n5\u0015\u0001\u00027b]\u001eL1\u0001 BOQ\u0015!\u00141MA8\u0003\u001d\u0011\u0016I\u0014#P\u001b\u0002BS!NA2\u0003_\n\u0001cS0N\u000b\u0006s5k\u0018)B%\u0006cE*\u0012')\u000bY\n\u0019'a\u001c\u0002#-{V*R!O'~\u0003\u0016IU!M\u0019\u0016c\u0005\u0005K\u00038\u0003G\ny'A\u0003ue\u0006Lg\u000e\u0006\u0007\u0002`\nU&q\u0017B]\u0005w\u0013i\fC\u0004\u0002~b\u0002\r!a@\t\u000b\tD\u0004\u0019\u00013\t\u000b=D\u0004\u0019\u00013\t\u000bQD\u0004\u0019\u0001<\t\u000f\u0005\u0005\u0002\b1\u0001\u0002&!*\u0001(a\u0019\u0003B\u0006\u0012!1Y\u0001\u0006e9\nd\u0006\r\u000b\u000b\u0003?\u00149M!3\u0003L\n5\u0007bBA\u007fs\u0001\u0007\u0011q \u0005\u0006Ef\u0002\r\u0001\u001a\u0005\u0006_f\u0002\r\u0001\u001a\u0005\u0006if\u0002\rA\u001e\u0015\u0006s\u0005\r$\u0011\u0019\u000b\t\u0003?\u0014\u0019N!6\u0003X\"9\u0011Q \u001eA\u0002\u0005}\b\"\u00022;\u0001\u0004!\u0007\"B8;\u0001\u0004!\u0007&\u0002\u001e\u0002d\u0005=\u0014\u0001\u0005<bY&$\u0017\r^3J]&$Xj\u001c3f)\u0011\u0011iCa8\t\r\t\u00058\b1\u0001w\u0003!Ig.\u001b;N_\u0012,\u0017\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001Bt!\u0011\u0011YJ!;\n\t\t-(Q\u0014\u0002\u0007\u001f\nTWm\u0019;)\u000bI\n\u0019'a\u001c)\u000bE\n\u0019'a\u001c"
)
public class KMeans implements Serializable, Logging {
   private int k;
   private int maxIterations;
   private String initializationMode;
   private int initializationSteps;
   private double epsilon;
   private long seed;
   private String distanceMeasure;
   private Option initialModel;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static KMeansModel train(final RDD data, final int k, final int maxIterations) {
      return KMeans$.MODULE$.train(data, k, maxIterations);
   }

   public static KMeansModel train(final RDD data, final int k, final int maxIterations, final String initializationMode) {
      return KMeans$.MODULE$.train(data, k, maxIterations, initializationMode);
   }

   public static KMeansModel train(final RDD data, final int k, final int maxIterations, final String initializationMode, final long seed) {
      return KMeans$.MODULE$.train(data, k, maxIterations, initializationMode, seed);
   }

   public static String K_MEANS_PARALLEL() {
      return KMeans$.MODULE$.K_MEANS_PARALLEL();
   }

   public static String RANDOM() {
      return KMeans$.MODULE$.RANDOM();
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

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private int maxIterations() {
      return this.maxIterations;
   }

   private void maxIterations_$eq(final int x$1) {
      this.maxIterations = x$1;
   }

   private String initializationMode() {
      return this.initializationMode;
   }

   private void initializationMode_$eq(final String x$1) {
      this.initializationMode = x$1;
   }

   private int initializationSteps() {
      return this.initializationSteps;
   }

   private void initializationSteps_$eq(final int x$1) {
      this.initializationSteps = x$1;
   }

   private double epsilon() {
      return this.epsilon;
   }

   private void epsilon_$eq(final double x$1) {
      this.epsilon = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private String distanceMeasure() {
      return this.distanceMeasure;
   }

   private void distanceMeasure_$eq(final String x$1) {
      this.distanceMeasure = x$1;
   }

   public int getK() {
      return this.k();
   }

   public KMeans setK(final int k) {
      .MODULE$.require(k > 0, () -> "Number of clusters must be positive but got " + k);
      this.k_$eq(k);
      return this;
   }

   public int getMaxIterations() {
      return this.maxIterations();
   }

   public KMeans setMaxIterations(final int maxIterations) {
      .MODULE$.require(maxIterations >= 0, () -> "Maximum of iterations must be nonnegative but got " + maxIterations);
      this.maxIterations_$eq(maxIterations);
      return this;
   }

   public String getInitializationMode() {
      return this.initializationMode();
   }

   public KMeans setInitializationMode(final String initializationMode) {
      KMeans$.MODULE$.validateInitMode(initializationMode);
      this.initializationMode_$eq(initializationMode);
      return this;
   }

   public int getInitializationSteps() {
      return this.initializationSteps();
   }

   public KMeans setInitializationSteps(final int initializationSteps) {
      .MODULE$.require(initializationSteps > 0, () -> "Number of initialization steps must be positive but got " + initializationSteps);
      this.initializationSteps_$eq(initializationSteps);
      return this;
   }

   public double getEpsilon() {
      return this.epsilon();
   }

   public KMeans setEpsilon(final double epsilon) {
      .MODULE$.require(epsilon >= (double)0, () -> "Distance threshold must be nonnegative but got " + epsilon);
      this.epsilon_$eq(epsilon);
      return this;
   }

   public long getSeed() {
      return this.seed();
   }

   public KMeans setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public String getDistanceMeasure() {
      return this.distanceMeasure();
   }

   public KMeans setDistanceMeasure(final String distanceMeasure) {
      DistanceMeasure$.MODULE$.validateDistanceMeasure(distanceMeasure);
      this.distanceMeasure_$eq(distanceMeasure);
      return this;
   }

   private Option initialModel() {
      return this.initialModel;
   }

   private void initialModel_$eq(final Option x$1) {
      this.initialModel = x$1;
   }

   public KMeans setInitialModel(final KMeansModel model) {
      .MODULE$.require(model.k() == this.k(), () -> "mismatched cluster count");
      this.initialModel_$eq(new Some(model));
      return this;
   }

   public KMeansModel run(final RDD data) {
      RDD instances;
      boolean var5;
      label17: {
         label16: {
            instances = data.map((point) -> new Tuple2(point, BoxesRunTime.boxToDouble((double)1.0F)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            StorageLevel var10000 = data.getStorageLevel();
            StorageLevel var4 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (var10000.equals(var4)) {
               break label16;
            }

            var5 = false;
            break label17;
         }

         var5 = true;
      }

      boolean handlePersistence = var5;
      return this.runWithWeight(instances, handlePersistence, scala.None..MODULE$);
   }

   public KMeansModel runWithWeight(final RDD instances, final boolean handlePersistence, final Option instr) {
      RDD norms = instances.map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$runWithWeight$1(x0$1)), scala.reflect.ClassTag..MODULE$.Double());
      RDD vectors = instances.zip(norms, scala.reflect.ClassTag..MODULE$.Double()).map((x0$2) -> {
         if (x0$2 != null) {
            Tuple2 var3 = (Tuple2)x0$2._1();
            double norm = x0$2._2$mcD$sp();
            if (var3 != null) {
               Vector v = (Vector)var3._1();
               double w = var3._2$mcD$sp();
               return new VectorWithNorm(v, norm, w);
            }
         }

         throw new MatchError(x0$2);
      }, scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
      if (handlePersistence) {
         vectors.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      } else {
         norms.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      }

      KMeansModel model = this.runAlgorithmWithWeight(vectors, instr);
      if (handlePersistence) {
         vectors.unpersist(vectors.unpersist$default$1());
      } else {
         norms.unpersist(norms.unpersist$default$1());
      }

      return model;
   }

   public Vector[] initialize(final RDD data) {
      VectorWithNorm[] var11;
      label29: {
         RDD dataWithNorms;
         label32: {
            dataWithNorms = data.map((x$1) -> new VectorWithNorm(x$1), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
            String var5 = this.initializationMode();
            String var10000 = KMeans$.MODULE$.RANDOM();
            if (var10000 == null) {
               if (var5 == null) {
                  break label32;
               }
            } else if (var10000.equals(var5)) {
               break label32;
            }

            var10000 = KMeans$.MODULE$.K_MEANS_PARALLEL();
            if (var10000 == null) {
               if (var5 != null) {
                  throw new MatchError(var5);
               }
            } else if (!var10000.equals(var5)) {
               throw new MatchError(var5);
            }

            DistanceMeasure distanceMeasureInstance = DistanceMeasure$.MODULE$.decodeFromString(this.distanceMeasure());
            dataWithNorms.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
            VectorWithNorm[] centers = this.initKMeansParallel(dataWithNorms, distanceMeasureInstance);
            dataWithNorms.unpersist(dataWithNorms.unpersist$default$1());
            var11 = centers;
            break label29;
         }

         var11 = this.initRandom(dataWithNorms);
      }

      VectorWithNorm[] centers = var11;
      return (Vector[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(centers), (x$2) -> x$2.vector(), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
   }

   private KMeansModel runAlgorithmWithWeight(final RDD data, final Option instr) {
      SparkContext sc = data.sparkContext();
      long initStartTime = System.currentTimeMillis();
      DistanceMeasure distanceMeasureInstance = DistanceMeasure$.MODULE$.decodeFromString(this.distanceMeasure());
      Option var9 = this.initialModel();
      VectorWithNorm[] var10000;
      if (var9 instanceof Some var10) {
         KMeansModel kMeansCenters = (KMeansModel)var10.value();
         var10000 = (VectorWithNorm[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(kMeansCenters.clusterCenters()), (x$3) -> new VectorWithNorm(x$3), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
      } else {
         label58: {
            if (!scala.None..MODULE$.equals(var9)) {
               throw new MatchError(var9);
            }

            label52: {
               String var30 = this.initializationMode();
               String var12 = KMeans$.MODULE$.RANDOM();
               if (var30 == null) {
                  if (var12 == null) {
                     break label52;
                  }
               } else if (var30.equals(var12)) {
                  break label52;
               }

               var10000 = this.initKMeansParallel(data, distanceMeasureInstance);
               break label58;
            }

            var10000 = this.initRandom(data);
         }
      }

      VectorWithNorm[] centers = var10000;
      int numFeatures = ((VectorWithNorm)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(centers))).vector().size();
      long initTimeMs = System.currentTimeMillis() - initStartTime;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initialization with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INIT_MODE..MODULE$, this.initializationMode())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" took ", " ms."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(initTimeMs))}))))));
      BooleanRef converged = BooleanRef.create(false);
      DoubleRef cost = DoubleRef.create((double)0.0F);
      IntRef iteration = IntRef.create(0);
      long iterationStartTime = System.currentTimeMillis();
      instr.foreach((x$4) -> {
         $anonfun$runAlgorithmWithWeight$3(numFeatures, x$4);
         return BoxedUnit.UNIT;
      });
      boolean shouldComputeStats = DistanceMeasure$.MODULE$.shouldComputeStatistics(centers.length);

      for(boolean shouldComputeStatsLocally = DistanceMeasure$.MODULE$.shouldComputeStatisticsLocally(centers.length, numFeatures); iteration.elem < this.maxIterations() && !converged.elem; ++iteration.elem) {
         Broadcast bcCenters = sc.broadcast(centers, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VectorWithNorm.class)));
         Option stats = (Option)(shouldComputeStats ? (shouldComputeStatsLocally ? new Some(distanceMeasureInstance.computeStatistics(centers)) : new Some(distanceMeasureInstance.computeStatisticsDistributedly(sc, bcCenters))) : scala.None..MODULE$);
         Broadcast bcStats = sc.broadcast(stats, scala.reflect.ClassTag..MODULE$.apply(Option.class));
         DoubleAccumulator costAccum = sc.doubleAccumulator();
         scala.collection.Map collected = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(data.mapPartitions((points) -> {
            VectorWithNorm[] centers = (VectorWithNorm[])bcCenters.value();
            Option stats = (Option)bcStats.value();
            int dims = ((VectorWithNorm)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(centers))).vector().size();
            Vector[] sums = (Vector[])scala.Array..MODULE$.fill(centers.length, () -> Vectors$.MODULE$.zeros(dims), scala.reflect.ClassTag..MODULE$.apply(Vector.class));
            double[] clusterWeightSum = (double[])scala.Array..MODULE$.ofDim(centers.length, scala.reflect.ClassTag..MODULE$.Double());
            points.foreach((point) -> {
               $anonfun$runAlgorithmWithWeight$6(distanceMeasureInstance, centers, stats, costAccum, sums, clusterWeightSum, point);
               return BoxedUnit.UNIT;
            });
            return scala.package..MODULE$.Iterator().tabulate(centers.length, (j) -> $anonfun$runAlgorithmWithWeight$7(sums, clusterWeightSum, BoxesRunTime.unboxToInt(j))).filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$runAlgorithmWithWeight$8(x$6)));
         }, data.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).reduceByKey((sumweight1, sumweight2) -> {
            BLAS$.MODULE$.axpy((double)1.0F, (Vector)sumweight2._1(), (Vector)sumweight1._1());
            return new Tuple2(sumweight1._1(), BoxesRunTime.boxToDouble(sumweight1._2$mcD$sp() + sumweight2._2$mcD$sp()));
         }), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.math.Ordering.Int..MODULE$).collectAsMap();
         if (iteration.elem == 0) {
            instr.foreach((x$7) -> {
               $anonfun$runAlgorithmWithWeight$10(costAccum, x$7);
               return BoxedUnit.UNIT;
            });
            instr.foreach((x$8) -> {
               $anonfun$runAlgorithmWithWeight$11(collected, x$8);
               return BoxedUnit.UNIT;
            });
         }

         bcCenters.destroy();
         bcStats.destroy();
         converged.elem = true;
         collected.foreach((x0$1) -> {
            $anonfun$runAlgorithmWithWeight$13(this, distanceMeasureInstance, converged, centers, x0$1);
            return BoxedUnit.UNIT;
         });
         cost.elem = .MODULE$.Double2double(costAccum.value());
         instr.foreach((x$10) -> {
            $anonfun$runAlgorithmWithWeight$14(iteration, cost, x$10);
            return BoxedUnit.UNIT;
         });
      }

      long iterationTimeMs = System.currentTimeMillis() - iterationStartTime;
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Iterations took ", " ms"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(iterationTimeMs))})))));
      if (iteration.elem == this.maxIterations()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"KMeans reached the max number of"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" iterations: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(this.maxIterations()))}))))));
      } else {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"KMeans converged in ", " iterations."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ITERATIONS..MODULE$, BoxesRunTime.boxToInteger(iteration.elem))})))));
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The cost is ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COST..MODULE$, BoxesRunTime.boxToDouble(cost.elem))})))));
      return new KMeansModel((Vector[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(centers), (x$11) -> x$11.vector(), scala.reflect.ClassTag..MODULE$.apply(Vector.class)), this.distanceMeasure(), cost.elem, iteration.elem);
   }

   private VectorWithNorm[] initRandom(final RDD data) {
      return (VectorWithNorm[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.distinct$extension(.MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(data.takeSample(false, this.k(), (long)(new XORShiftRandom(this.seed())).nextInt())), (x$12) -> x$12.vector(), scala.reflect.ClassTag..MODULE$.apply(Vector.class))))), (x$13) -> new VectorWithNorm(x$13), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
   }

   public VectorWithNorm[] initKMeansParallel(final RDD data, final DistanceMeasure distanceMeasureInstance) {
      RDD costs = data.map((x$14) -> BoxesRunTime.boxToDouble($anonfun$initKMeansParallel$1(x$14)), scala.reflect.ClassTag..MODULE$.Double());
      int seed = (new XORShiftRandom(this.seed())).nextInt();
      VectorWithNorm[] sample = (VectorWithNorm[])data.takeSample(false, 1, (long)seed);
      .MODULE$.require(scala.collection.ArrayOps..MODULE$.nonEmpty$extension(.MODULE$.refArrayOps(sample)), () -> "No samples available from " + data);
      ArrayBuffer centers = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      VectorWithNorm[] newCenters = (VectorWithNorm[])(new VectorWithNorm[]{((VectorWithNorm)scala.collection.ArrayOps..MODULE$.head$extension(.MODULE$.refArrayOps(sample))).toDense()});
      centers.$plus$plus$eq(.MODULE$.wrapRefArray(newCenters));
      IntRef step = IntRef.create(0);

      ArrayBuffer bcNewCentersList;
      for(bcNewCentersList = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$); step.elem < this.initializationSteps(); ++step.elem) {
         Broadcast bcNewCenters = data.context().broadcast(newCenters, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VectorWithNorm.class)));
         bcNewCentersList.$plus$eq(bcNewCenters);
         RDD preCosts = costs;
         costs = data.zip(costs, scala.reflect.ClassTag..MODULE$.Double()).map((x0$1) -> BoxesRunTime.boxToDouble($anonfun$initKMeansParallel$3(distanceMeasureInstance, bcNewCenters, x0$1)), scala.reflect.ClassTag..MODULE$.Double()).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
         double sumCosts = org.apache.spark.rdd.RDD..MODULE$.doubleRDDToDoubleRDDFunctions(costs).sum();
         bcNewCenters.unpersist();
         preCosts.unpersist(preCosts.unpersist$default$1());
         RDD qual$1 = data.zip(costs, scala.reflect.ClassTag..MODULE$.Double());
         Function2 x$1 = (index, pointCosts) -> $anonfun$initKMeansParallel$4(this, seed, step, sumCosts, BoxesRunTime.unboxToInt(index), pointCosts);
         boolean x$2 = qual$1.mapPartitionsWithIndex$default$2();
         VectorWithNorm[] chosen = (VectorWithNorm[])qual$1.mapPartitionsWithIndex(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class)).collect();
         newCenters = (VectorWithNorm[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(chosen), (x$16) -> x$16.toDense(), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
         centers.$plus$plus$eq(.MODULE$.wrapRefArray(newCenters));
      }

      boolean x$3 = costs.unpersist$default$1();
      costs.unpersist(x$3);
      bcNewCentersList.foreach((x$17) -> {
         $anonfun$initKMeansParallel$8(x$17);
         return BoxedUnit.UNIT;
      });
      VectorWithNorm[] distinctCenters = (VectorWithNorm[])((IterableOnceOps)((StrictOptimizedIterableOps)((SeqOps)centers.map((x$18) -> x$18.vector())).distinct()).map((x$19) -> new VectorWithNorm(x$19))).toArray(scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
      if (distinctCenters.length <= this.k()) {
         return distinctCenters;
      } else {
         Broadcast bcCenters = data.context().broadcast(distinctCenters, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(VectorWithNorm.class)));
         scala.collection.Map countMap = data.map((x$20) -> BoxesRunTime.boxToInteger($anonfun$initKMeansParallel$11(distanceMeasureInstance, bcCenters, x$20)), scala.reflect.ClassTag..MODULE$.Int()).countByValue(scala.math.Ordering.Int..MODULE$);
         bcCenters.destroy();
         double[] myWeights = (double[])scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps(distinctCenters)).map((JFunction1.mcDI.sp)(x$21) -> (double)BoxesRunTime.unboxToLong(countMap.getOrElse(BoxesRunTime.boxToInteger(x$21), (JFunction0.mcJ.sp)() -> 0L))).toArray(scala.reflect.ClassTag..MODULE$.Double());
         return LocalKMeans$.MODULE$.kMeansPlusPlus(0, distinctCenters, myWeights, this.k(), 30);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$runWithWeight$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Vector v = (Vector)x0$1._1();
         return Vectors$.MODULE$.norm(v, (double)2.0F);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$3(final int numFeatures$1, final Instrumentation x$4) {
      x$4.logNumFeatures((long)numFeatures$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$6(final DistanceMeasure distanceMeasureInstance$1, final VectorWithNorm[] centers$1, final Option stats$1, final DoubleAccumulator costAccum$1, final Vector[] sums$1, final double[] clusterWeightSum$1, final VectorWithNorm point) {
      Tuple2 var9 = distanceMeasureInstance$1.findClosest(centers$1, stats$1, point);
      if (var9 != null) {
         int bestCenter = var9._1$mcI$sp();
         double cost = var9._2$mcD$sp();
         Tuple2.mcID.sp var8 = new Tuple2.mcID.sp(bestCenter, cost);
         int bestCenter = ((Tuple2)var8)._1$mcI$sp();
         double cost = ((Tuple2)var8)._2$mcD$sp();
         costAccum$1.add(cost * point.weight());
         distanceMeasureInstance$1.updateClusterSum(point, sums$1[bestCenter]);
         clusterWeightSum$1[bestCenter] += point.weight();
      } else {
         throw new MatchError(var9);
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runAlgorithmWithWeight$7(final Vector[] sums$1, final double[] clusterWeightSum$1, final int j) {
      return new Tuple2(BoxesRunTime.boxToInteger(j), new Tuple2(sums$1[j], BoxesRunTime.boxToDouble(clusterWeightSum$1[j])));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$runAlgorithmWithWeight$8(final Tuple2 x$6) {
      return ((Tuple2)x$6._2())._2$mcD$sp() > (double)0;
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$10(final DoubleAccumulator costAccum$1, final Instrumentation x$7) {
      x$7.logNumExamples(costAccum$1.count());
   }

   // $FF: synthetic method
   public static final double $anonfun$runAlgorithmWithWeight$12(final Tuple2 x$9) {
      return x$9._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$11(final scala.collection.Map collected$1, final Instrumentation x$8) {
      x$8.logSumOfWeights(BoxesRunTime.unboxToDouble(((IterableOnceOps)collected$1.values().map((x$9) -> BoxesRunTime.boxToDouble($anonfun$runAlgorithmWithWeight$12(x$9)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)));
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$13(final KMeans $this, final DistanceMeasure distanceMeasureInstance$1, final BooleanRef converged$1, final VectorWithNorm[] centers$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int j = x0$1._1$mcI$sp();
         Tuple2 var8 = (Tuple2)x0$1._2();
         if (var8 != null) {
            Vector sum = (Vector)var8._1();
            double weightSum = var8._2$mcD$sp();
            VectorWithNorm newCenter = distanceMeasureInstance$1.centroid(sum, weightSum);
            if (converged$1.elem && !distanceMeasureInstance$1.isCenterConverged(centers$2[j], newCenter, $this.epsilon())) {
               converged$1.elem = false;
            }

            centers$2[j] = newCenter;
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$runAlgorithmWithWeight$14(final IntRef iteration$1, final DoubleRef cost$1, final Instrumentation x$10) {
      x$10.logNamedValue("Cost@iter=" + iteration$1.elem, String.valueOf(BoxesRunTime.boxToDouble(cost$1.elem)));
   }

   // $FF: synthetic method
   public static final double $anonfun$initKMeansParallel$1(final VectorWithNorm x$14) {
      return Double.POSITIVE_INFINITY;
   }

   // $FF: synthetic method
   public static final double $anonfun$initKMeansParallel$3(final DistanceMeasure distanceMeasureInstance$2, final Broadcast bcNewCenters$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         VectorWithNorm point = (VectorWithNorm)x0$1._1();
         double cost = x0$1._2$mcD$sp();
         return scala.math.package..MODULE$.min(distanceMeasureInstance$2.pointCost((VectorWithNorm[])bcNewCenters$1.value(), point), cost);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$initKMeansParallel$5(final KMeans $this, final XORShiftRandom rand$1, final double sumCosts$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         double c = x0$2._2$mcD$sp();
         return rand$1.nextDouble() < (double)2.0F * c * (double)$this.k() / sumCosts$1;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$initKMeansParallel$4(final KMeans $this, final int seed$1, final IntRef step$1, final double sumCosts$1, final int index, final Iterator pointCosts) {
      XORShiftRandom rand = new XORShiftRandom((long)(seed$1 ^ step$1.elem << 16 ^ index));
      return pointCosts.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$initKMeansParallel$5($this, rand, sumCosts$1, x0$2))).map((x$15) -> (VectorWithNorm)x$15._1());
   }

   // $FF: synthetic method
   public static final void $anonfun$initKMeansParallel$8(final Broadcast x$17) {
      x$17.destroy();
   }

   // $FF: synthetic method
   public static final int $anonfun$initKMeansParallel$11(final DistanceMeasure distanceMeasureInstance$2, final Broadcast bcCenters$2, final VectorWithNorm x$20) {
      return distanceMeasureInstance$2.findClosest((VectorWithNorm[])bcCenters$2.value(), x$20)._1$mcI$sp();
   }

   private KMeans(final int k, final int maxIterations, final String initializationMode, final int initializationSteps, final double epsilon, final long seed, final String distanceMeasure) {
      this.k = k;
      this.maxIterations = maxIterations;
      this.initializationMode = initializationMode;
      this.initializationSteps = initializationSteps;
      this.epsilon = epsilon;
      this.seed = seed;
      this.distanceMeasure = distanceMeasure;
      super();
      Logging.$init$(this);
      this.initialModel = scala.None..MODULE$;
   }

   private KMeans(final int k, final int maxIterations, final String initializationMode, final int initializationSteps, final double epsilon, final long seed) {
      this(k, maxIterations, initializationMode, initializationSteps, epsilon, seed, DistanceMeasure$.MODULE$.EUCLIDEAN());
   }

   public KMeans() {
      this(2, 20, KMeans$.MODULE$.K_MEANS_PARALLEL(), 2, 1.0E-4, org.apache.spark.util.Utils..MODULE$.random().nextLong(), DistanceMeasure$.MODULE$.EUCLIDEAN());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
