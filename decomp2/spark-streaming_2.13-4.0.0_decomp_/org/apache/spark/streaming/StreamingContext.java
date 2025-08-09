package org.apache.spark.streaming;

import java.io.NotSerializableException;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.input.FixedLengthBinaryInputFormat;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDDOperationScope.;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.dstream.DStream$;
import org.apache.spark.streaming.dstream.FileInputDStream;
import org.apache.spark.streaming.dstream.FileInputDStream$;
import org.apache.spark.streaming.dstream.InputDStream;
import org.apache.spark.streaming.dstream.PluggableInputDStream;
import org.apache.spark.streaming.dstream.QueueInputDStream;
import org.apache.spark.streaming.dstream.RawInputDStream;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import org.apache.spark.streaming.dstream.SocketInputDStream;
import org.apache.spark.streaming.dstream.SocketReceiver$;
import org.apache.spark.streaming.dstream.TransformedDStream;
import org.apache.spark.streaming.dstream.UnionDStream;
import org.apache.spark.streaming.receiver.Receiver;
import org.apache.spark.streaming.scheduler.ExecutorAllocationManager$;
import org.apache.spark.streaming.scheduler.JobScheduler;
import org.apache.spark.streaming.scheduler.StreamingListener;
import org.apache.spark.streaming.scheduler.StreamingListenerStreamingStarted;
import org.apache.spark.streaming.ui.StreamingJobProgressListener;
import org.apache.spark.streaming.ui.StreamingTab;
import org.apache.spark.ui.SparkUI;
import org.apache.spark.util.CallSite;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Queue;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0005f\u0001\u00029r\u0001iD!\"a\u0004\u0001\u0005\u0003\u0005\u000b\u0011BA\t\u0011)\tI\u0002\u0001B\u0001B\u0003%\u00111\u0004\u0005\u000b\u0003G\u0001!\u0011!Q\u0001\n\u0005\u0015\u0002\u0002CA\u0016\u0001\u0011\u0005\u0011/!\f\t\u000f\u0005-\u0002\u0001\"\u0001\u00028!9\u00111\u0006\u0001\u0005\u0002\u0005\u0005\u0003bBA\u0016\u0001\u0011\u0005\u0011q\n\u0005\b\u0003W\u0001A\u0011AAN\u0011\u001d\tY\u0003\u0001C\u0001\u0003gCq!a\u000b\u0001\t\u0003\t9\f\u0003\u0006\u0002>\u0002\u0011\r\u0011\"\u0001r\u0003\u007fC\u0001\"a2\u0001A\u0003%\u0011\u0011\u0019\u0005\u000b\u0003\u0013\u0004!\u0019!C\u0001c\u0006-\u0007\u0002CAg\u0001\u0001\u0006I!!\u0005\t\u0015\u0005\u0015\u0003A1A\u0005\u0002E\fy\r\u0003\u0005\u0002R\u0002\u0001\u000b\u0011BA$\u0011)\t\u0019\u000e\u0001b\u0001\n\u0003\t\u0018Q\u001b\u0005\t\u0003;\u0004\u0001\u0015!\u0003\u0002X\"Q\u0011q\u001c\u0001C\u0002\u0013\u0005\u0011/!9\t\u0011\u0005%\b\u0001)A\u0005\u0003GD\u0011\"a;\u0001\u0005\u0004%I!!<\t\u0011\t\u001d\u0001\u0001)A\u0005\u0003_D!B!\u0003\u0001\u0001\u0004%\t!\u001dB\u0006\u0011)\u0011i\u0001\u0001a\u0001\n\u0003\t(q\u0002\u0005\t\u00057\u0001\u0001\u0015)\u0003\u0002V!Q!Q\u0004\u0001C\u0002\u0013\u0005\u0011Oa\b\t\u0011\t\u0005\u0002\u0001)A\u0005\u0003KA!Ba\t\u0001\u0005\u0004%\t!\u001dB\u0013\u0011!\u0011\t\u0004\u0001Q\u0001\n\t\u001d\u0002B\u0003B\u001a\u0001\t\u0007I\u0011A9\u00036!A!Q\b\u0001!\u0002\u0013\u00119\u0004\u0003\u0006\u0003@\u0001\u0011\r\u0011\"\u0001r\u0005\u0003B\u0001Ba\u0014\u0001A\u0003%!1\t\u0005\u000b\u0005#\u0002!\u0019!C\u0001c\nM\u0003\u0002\u0003B1\u0001\u0001\u0006IA!\u0016\t\u0013\t\r\u0004A1A\u0005\n\t\u0015\u0004\u0002\u0003B7\u0001\u0001\u0006IAa\u001a\t\u0013\t=\u0004\u00011A\u0005\n\tE\u0004\"\u0003B=\u0001\u0001\u0007I\u0011\u0002B>\u0011!\u0011y\b\u0001Q!\n\tM\u0004\"\u0003BA\u0001\t\u0007I\u0011\u0002BB\u0011!\u0011)\n\u0001Q\u0001\n\t\u0015\u0005B\u0003BL\u0001\t\u0007I\u0011A9\u0003\u001a\"A!Q\u0015\u0001!\u0002\u0013\u0011Y\n\u0003\u0005\u0003(\u0002!\t!\u001dBU\u0011-\u0011Y\u000b\u0001a\u0001\u0002\u0004%IA!,\t\u0017\t=\u0006\u00011AA\u0002\u0013%!\u0011\u0017\u0005\u000b\u0005k\u0003\u0001\u0019!A!B\u0013Y\bbBA\u001e\u0001\u0011\u0005\u00111\u001a\u0005\b\u0005o\u0003A\u0011\u0001B]\u0011\u001d\u0011y\f\u0001C\u0001\u0005\u0003D\u0001Ba2\u0001\t\u0003\t\u0018q\u0018\u0005\t\u0005\u0013\u0004A\u0011A9\u0003L\"A!Q\u001a\u0001\u0005\u0002E\u0014y\r\u0003\u0005\u0003X\u0002!\t!\u001dBm\u0011!\u0011i\u0010\u0001C\u0001c\n}\bbBB\t\u0001\u0011\u000511\u0003\u0005\b\u0007\u0013\u0002A\u0011AB&\u0011%\u00199\u0007AI\u0001\n\u0003\u0019I\u0007C\u0004\u0004\u0000\u0001!\ta!!\t\u000f\rU\u0006\u0001\"\u0001\u00048\"I1q\u001a\u0001\u0012\u0002\u0013\u00051\u0011\u001b\u0005\b\u0007+\u0004A\u0011ABl\u0011\u001d\u0019)\u000e\u0001C\u0001\t;Aqa!6\u0001\t\u0003!\t\u0007C\u0004\u0005\u0016\u0002!\t\u0001b&\t\u000f\u0011\u0005\u0006\u0001\"\u0001\u0005$\"9A\u0011\u0018\u0001\u0005\u0002\u0011m\u0006\"\u0003Cw\u0001E\u0005I\u0011\u0001Cx\u0011\u001d!I\f\u0001C\u0001\toDq!\"\u0006\u0001\t\u0003)9\u0002C\u0004\u00060\u0001!\t!\"\r\t\u000f\u0015m\u0004\u0001\"\u0001\u0006~!9Q\u0011\u0012\u0001\u0005\u0002\u0015-\u0005bBCH\u0001\u0011%Q\u0011\u0013\u0005\b\u000b'\u0003A\u0011ACK\u0011\u001d)\u0019\u000b\u0001C\u0001\u000b#Cq!\"*\u0001\t\u0003)\t\nC\u0004\u0006(\u0002!\t!\"+\t\u000f\u0015U\u0006\u0001\"\u0001\u00068\"IQQ\u0018\u0001\u0012\u0002\u0013\u0005A\u0011\u001f\u0005\b\u000bk\u0003A\u0011AC`\u0011\u001d)9\r\u0001C\u0005\u000b#Cq!\"3\u0001\t\u0013)\t\nC\u0004\u0006L\u0002!I!\"%\b\u000f\u0015\u0005\u0018\u000f#\u0001\u0006d\u001a1\u0001/\u001dE\u0001\u000bKDq!a\u000bX\t\u0003)9\u000fC\u0005\u0006j^\u0013\r\u0011\"\u0003\u0006l\"AQ\u0011`,!\u0002\u0013)i\u000fC\u0005\u0006|^\u0013\r\u0011\"\u0003\u0006~\"AQq`,!\u0002\u0013\u0011\t\u000eC\u0005\u0007\u0002]\u0013\r\u0011\"\u0003\u0007\u0004!AaqA,!\u0002\u00131)\u0001C\u0004\u0007\n]#I!\"%\t\u000f\u0019-q\u000b\"\u0003\u0007\u000e!9a1C,\u0005\u0002\u0019U\u0001b\u0002D\r/\u0012\u0005a1\u0004\u0005\b\r39F\u0011\u0001D\u0014\u0011%1)dVI\u0001\n\u000319\u0004C\u0005\u0007<]\u000b\n\u0011\"\u0001\u0005r\"9aQH,\u0005\u0002\u0019}\u0002\"\u0003D%/F\u0005I\u0011\u0001D\u001c\u0011%1YeVI\u0001\n\u0003!\t\u0010C\u0004\u0007N]#\tAb\u0014\t\u0011\u0019\u0015t\u000b\"\u0001r\rOB\u0001B\"\u001aX\t\u0003\th1\u000e\u0005\t\ro:F\u0011A9\u0007z!Ia1R,\u0012\u0002\u0013\u0005aQ\u0012\u0005\n\r#;\u0016\u0013!C\u0001\r'C\u0011Bb&X#\u0003%\tA\"'\u0003!M#(/Z1nS:<7i\u001c8uKb$(B\u0001:t\u0003%\u0019HO]3b[&twM\u0003\u0002uk\u0006)1\u000f]1sW*\u0011ao^\u0001\u0007CB\f7\r[3\u000b\u0003a\f1a\u001c:h\u0007\u0001\u0019B\u0001A>\u0002\u0004A\u0011Ap`\u0007\u0002{*\ta0A\u0003tG\u0006d\u0017-C\u0002\u0002\u0002u\u0014a!\u00118z%\u00164\u0007\u0003BA\u0003\u0003\u0017i!!a\u0002\u000b\u0007\u0005%1/\u0001\u0005j]R,'O\\1m\u0013\u0011\ti!a\u0002\u0003\u000f1{wmZ5oO\u0006\u0019ql]2\u0011\t\u0005M\u0011QC\u0007\u0002g&\u0019\u0011qC:\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\u0007}\u001b\u0007\u000f\u0005\u0003\u0002\u001e\u0005}Q\"A9\n\u0007\u0005\u0005\u0012O\u0001\u0006DQ\u0016\u001c7\u000e]8j]R\f\u0011b\u00182bi\u000eDG)\u001e:\u0011\t\u0005u\u0011qE\u0005\u0004\u0003S\t(\u0001\u0003#ve\u0006$\u0018n\u001c8\u0002\rqJg.\u001b;?)!\ty#!\r\u00024\u0005U\u0002cAA\u000f\u0001!9\u0011q\u0002\u0003A\u0002\u0005E\u0001bBA\r\t\u0001\u0007\u00111\u0004\u0005\b\u0003G!\u0001\u0019AA\u0013)\u0019\ty#!\u000f\u0002>!9\u00111H\u0003A\u0002\u0005E\u0011\u0001D:qCJ\\7i\u001c8uKb$\bbBA \u000b\u0001\u0007\u0011QE\u0001\u000eE\u0006$8\r\u001b#ve\u0006$\u0018n\u001c8\u0015\r\u0005=\u00121IA'\u0011\u001d\t)E\u0002a\u0001\u0003\u000f\nAaY8oMB!\u00111CA%\u0013\r\tYe\u001d\u0002\n'B\f'o[\"p]\u001aDq!a\u0010\u0007\u0001\u0004\t)\u0003\u0006\b\u00020\u0005E\u00131NA8\u0003c\n)(a#\t\u000f\u0005Ms\u00011\u0001\u0002V\u00051Q.Y:uKJ\u0004B!a\u0016\u0002f9!\u0011\u0011LA1!\r\tY&`\u0007\u0003\u0003;R1!a\u0018z\u0003\u0019a$o\\8u}%\u0019\u00111M?\u0002\rA\u0013X\rZ3g\u0013\u0011\t9'!\u001b\u0003\rM#(/\u001b8h\u0015\r\t\u0019' \u0005\b\u0003[:\u0001\u0019AA+\u0003\u001d\t\u0007\u000f\u001d(b[\u0016Dq!a\u0010\b\u0001\u0004\t)\u0003C\u0005\u0002t\u001d\u0001\n\u00111\u0001\u0002V\u0005I1\u000f]1sW\"{W.\u001a\u0005\n\u0003o:\u0001\u0013!a\u0001\u0003s\nAA[1sgB1\u00111PAC\u0003+rA!! \u0002\u0002:!\u00111LA@\u0013\u0005q\u0018bAAB{\u00069\u0001/Y2lC\u001e,\u0017\u0002BAD\u0003\u0013\u00131aU3r\u0015\r\t\u0019) \u0005\n\u0003\u001b;\u0001\u0013!a\u0001\u0003\u001f\u000b1\"\u001a8wSJ|g.\\3oiBA\u0011\u0011SAL\u0003+\n)&\u0004\u0002\u0002\u0014*\u0019\u0011QS?\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u001a\u0006M%aA'baR1\u0011qFAO\u0003CCq!a(\t\u0001\u0004\t)&\u0001\u0003qCRD\u0007bBAR\u0011\u0001\u0007\u0011QU\u0001\u000bQ\u0006$wn\u001c9D_:4\u0007\u0003BAT\u0003_k!!!+\u000b\t\u0005\u0015\u00131\u0016\u0006\u0004\u0003[+\u0018A\u00025bI>|\u0007/\u0003\u0003\u00022\u0006%&!D\"p]\u001aLw-\u001e:bi&|g\u000e\u0006\u0003\u00020\u0005U\u0006bBAP\u0013\u0001\u0007\u0011Q\u000b\u000b\u0007\u0003_\tI,a/\t\u000f\u0005}%\u00021\u0001\u0002V!9\u00111\b\u0006A\u0002\u0005E\u0011aE5t\u0007\",7m\u001b9pS:$\bK]3tK:$XCAAa!\ra\u00181Y\u0005\u0004\u0003\u000bl(a\u0002\"p_2,\u0017M\\\u0001\u0015SN\u001c\u0005.Z2la>Lg\u000e\u001e)sKN,g\u000e\u001e\u0011\u0002\u0005M\u001cWCAA\t\u0003\r\u00198\rI\u000b\u0003\u0003\u000f\nQaY8oM\u0002\n1!\u001a8w+\t\t9\u000e\u0005\u0003\u0002\u0014\u0005e\u0017bAAng\nA1\u000b]1sW\u0016sg/\u0001\u0003f]Z\u0004\u0013!B4sCBDWCAAr!\u0011\ti\"!:\n\u0007\u0005\u001d\u0018O\u0001\u0007E'R\u0014X-Y7He\u0006\u0004\b.\u0001\u0004he\u0006\u0004\b\u000eI\u0001\u0012]\u0016DH/\u00138qkR\u001cFO]3b[&#WCAAx!\u0011\t\tPa\u0001\u000e\u0005\u0005M(\u0002BA{\u0003o\fa!\u0019;p[&\u001c'\u0002BA}\u0003w\f!bY8oGV\u0014(/\u001a8u\u0015\u0011\ti0a@\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0005\u0003\tAA[1wC&!!QAAz\u00055\tEo\\7jG&sG/Z4fe\u0006\u0011b.\u001a=u\u0013:\u0004X\u000f^*ue\u0016\fW.\u00133!\u00035\u0019\u0007.Z2la>Lg\u000e\u001e#jeV\u0011\u0011QK\u0001\u0012G\",7m\u001b9pS:$H)\u001b:`I\u0015\fH\u0003\u0002B\t\u0005/\u00012\u0001 B\n\u0013\r\u0011)\" \u0002\u0005+:LG\u000fC\u0005\u0003\u001aa\t\t\u00111\u0001\u0002V\u0005\u0019\u0001\u0010J\u0019\u0002\u001d\rDWmY6q_&tG\u000fR5sA\u0005\u00112\r[3dWB|\u0017N\u001c;EkJ\fG/[8o+\t\t)#A\ndQ\u0016\u001c7\u000e]8j]R$UO]1uS>t\u0007%A\u0005tG\",G-\u001e7feV\u0011!q\u0005\t\u0005\u0005S\u0011i#\u0004\u0002\u0003,)\u0019!1E9\n\t\t=\"1\u0006\u0002\r\u0015>\u00147k\u00195fIVdWM]\u0001\u000bg\u000eDW\rZ;mKJ\u0004\u0013AB<bSR,'/\u0006\u0002\u00038A!\u0011Q\u0004B\u001d\u0013\r\u0011Y$\u001d\u0002\u000e\u0007>tG/\u001a=u/\u0006LG/\u001a:\u0002\u000f]\f\u0017\u000e^3sA\u0005\u0001\u0002O]8he\u0016\u001c8\u000fT5ti\u0016tWM]\u000b\u0003\u0005\u0007\u0002BA!\u0012\u0003L5\u0011!q\t\u0006\u0004\u0005\u0013\n\u0018AA;j\u0013\u0011\u0011iEa\u0012\u00039M#(/Z1nS:<'j\u001c2Qe><'/Z:t\u0019&\u001cH/\u001a8fe\u0006\t\u0002O]8he\u0016\u001c8\u000fT5ti\u0016tWM\u001d\u0011\u0002\u000bULG+\u00192\u0016\u0005\tU\u0003#\u0002?\u0003X\tm\u0013b\u0001B-{\n1q\n\u001d;j_:\u0004BA!\u0012\u0003^%!!q\fB$\u00051\u0019FO]3b[&tw\rV1c\u0003\u0019)\u0018\u000eV1cA\u0005y1\u000f\u001e:fC6LgnZ*pkJ\u001cW-\u0006\u0002\u0003hA!\u0011Q\u0004B5\u0013\r\u0011Y'\u001d\u0002\u0010'R\u0014X-Y7j]\u001e\u001cv.\u001e:dK\u0006\u00012\u000f\u001e:fC6LgnZ*pkJ\u001cW\rI\u0001\u0006gR\fG/Z\u000b\u0003\u0005g\u0002B!!\b\u0003v%\u0019!qO9\u0003+M#(/Z1nS:<7i\u001c8uKb$8\u000b^1uK\u0006I1\u000f^1uK~#S-\u001d\u000b\u0005\u0005#\u0011i\bC\u0005\u0003\u001a\u001d\n\t\u00111\u0001\u0003t\u000511\u000f^1uK\u0002\n\u0011b\u001d;beR\u001c\u0016\u000e^3\u0016\u0005\t\u0015\u0005CBAy\u0005\u000f\u0013Y)\u0003\u0003\u0003\n\u0006M(aD!u_6L7MU3gKJ,gnY3\u0011\t\t5%\u0011S\u0007\u0003\u0005\u001fS1!!@t\u0013\u0011\u0011\u0019Ja$\u0003\u0011\r\u000bG\u000e\\*ji\u0016\f!b\u001d;beR\u001c\u0016\u000e^3!\u0003=\u0019\u0018M^3e!J|\u0007/\u001a:uS\u0016\u001cXC\u0001BN!\u0019\t\tPa\"\u0003\u001eB!!q\u0014BQ\u001b\t\tY0\u0003\u0003\u0003$\u0006m(A\u0003)s_B,'\u000f^5fg\u0006\u00012/\u0019<fIB\u0013x\u000e]3si&,7\u000fI\u0001\rO\u0016$8\u000b^1siNKG/\u001a\u000b\u0003\u0005\u0017\u000bqb\u001d5vi\u0012|wO\u001c%p_.\u0014VMZ\u000b\u0002w\u0006\u00192\u000f[;uI><h\u000eS8pWJ+gm\u0018\u0013fcR!!\u0011\u0003BZ\u0011!\u0011IbLA\u0001\u0002\u0004Y\u0018\u0001E:ikR$wn\u001e8I_>\\'+\u001a4!\u0003!\u0011X-\\3nE\u0016\u0014H\u0003\u0002B\t\u0005wCqA!03\u0001\u0004\t)#\u0001\u0005ekJ\fG/[8o\u0003)\u0019\u0007.Z2la>Lg\u000e\u001e\u000b\u0005\u0005#\u0011\u0019\rC\u0004\u0003FN\u0002\r!!\u0016\u0002\u0013\u0011L'/Z2u_JL\u0018AF5t\u0007\",7m\u001b9pS:$\u0018N\\4F]\u0006\u0014G.\u001a3\u0002#%t\u0017\u000e^5bY\u000eCWmY6q_&tG/\u0006\u0002\u0002\u001c\u0005\u0019r-\u001a;OK^Le\u000e];u'R\u0014X-Y7JIR\u0011!\u0011\u001b\t\u0004y\nM\u0017b\u0001Bk{\n\u0019\u0011J\u001c;\u0002\u0013]LG\u000f[*d_B,W\u0003\u0002Bn\u0005C$BA!8\u0003tB!!q\u001cBq\u0019\u0001!qAa98\u0005\u0004\u0011)OA\u0001V#\u0011\u00119O!<\u0011\u0007q\u0014I/C\u0002\u0003lv\u0014qAT8uQ&tw\rE\u0002}\u0005_L1A!=~\u0005\r\te.\u001f\u0005\t\u0005k<D\u00111\u0001\u0003x\u0006!!m\u001c3z!\u0015a(\u0011 Bo\u0013\r\u0011Y0 \u0002\ty\tLh.Y7f}\u0005qq/\u001b;i\u001d\u0006lW\rZ*d_B,W\u0003BB\u0001\u0007\u000f!Baa\u0001\u0004\u000eQ!1QAB\u0005!\u0011\u0011yna\u0002\u0005\u000f\t\r\bH1\u0001\u0003f\"A!Q\u001f\u001d\u0005\u0002\u0004\u0019Y\u0001E\u0003}\u0005s\u001c)\u0001C\u0004\u0004\u0010a\u0002\r!!\u0016\u0002\t9\fW.Z\u0001\u000fe\u0016\u001cW-\u001b<feN#(/Z1n+\u0011\u0019)ba\n\u0015\t\r]11\b\u000b\u0005\u00073\u0019Y\u0003\u0005\u0004\u0004\u001c\r\u00052QE\u0007\u0003\u0007;Q1aa\br\u0003\u001d!7\u000f\u001e:fC6LAaa\t\u0004\u001e\t!\"+Z2fSZ,'/\u00138qkR$5\u000b\u001e:fC6\u0004BAa8\u0004(\u001191\u0011F\u001dC\u0002\t\u0015(!\u0001+\t\u0013\r5\u0012(!AA\u0004\r=\u0012AC3wS\u0012,gnY3%cA11\u0011GB\u001c\u0007Ki!aa\r\u000b\u0007\rUR0A\u0004sK\u001adWm\u0019;\n\t\re21\u0007\u0002\t\u00072\f7o\u001d+bO\"91QH\u001dA\u0002\r}\u0012\u0001\u0003:fG\u0016Lg/\u001a:\u0011\r\r\u00053QIB\u0013\u001b\t\u0019\u0019EC\u0002\u0004>ELAaa\u0012\u0004D\tA!+Z2fSZ,'/\u0001\tt_\u000e\\W\r\u001e+fqR\u001cFO]3b[RA1QJB(\u0007'\u001a9\u0006\u0005\u0004\u0004\u001c\r\u0005\u0012Q\u000b\u0005\b\u0007#R\u0004\u0019AA+\u0003!Awn\u001d;oC6,\u0007bBB+u\u0001\u0007!\u0011[\u0001\u0005a>\u0014H\u000fC\u0005\u0004Zi\u0002\n\u00111\u0001\u0004\\\u0005a1\u000f^8sC\u001e,G*\u001a<fYB!1QLB2\u001b\t\u0019yFC\u0002\u0004bM\fqa\u001d;pe\u0006<W-\u0003\u0003\u0004f\r}#\u0001D*u_J\fw-\u001a'fm\u0016d\u0017AG:pG.,G\u000fV3yiN#(/Z1nI\u0011,g-Y;mi\u0012\u001aTCAB6U\u0011\u0019Yf!\u001c,\u0005\r=\u0004\u0003BB9\u0007wj!aa\u001d\u000b\t\rU4qO\u0001\nk:\u001c\u0007.Z2lK\u0012T1a!\u001f~\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007{\u001a\u0019HA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb]8dW\u0016$8\u000b\u001e:fC6,Baa!\u0004\fRQ1QQBJ\u0007+\u001b9ja-\u0015\t\r\u001d5Q\u0012\t\u0007\u00077\u0019\tc!#\u0011\t\t}71\u0012\u0003\b\u0007Sa$\u0019\u0001Bs\u0011%\u0019y\tPA\u0001\u0002\b\u0019\t*\u0001\u0006fm&$WM\\2fII\u0002ba!\r\u00048\r%\u0005bBB)y\u0001\u0007\u0011Q\u000b\u0005\b\u0007+b\u0004\u0019\u0001Bi\u0011\u001d\u0019I\n\u0010a\u0001\u00077\u000b\u0011bY8om\u0016\u0014H/\u001a:\u0011\u000fq\u001cij!)\u0004.&\u00191qT?\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003BBR\u0007Sk!a!*\u000b\t\r\u001d\u0016q`\u0001\u0003S>LAaa+\u0004&\nY\u0011J\u001c9viN#(/Z1n!\u0019\tYha,\u0004\n&!1\u0011WAE\u0005!IE/\u001a:bi>\u0014\bbBB-y\u0001\u000711L\u0001\u0010e\u0006<8k\\2lKR\u001cFO]3b[V!1\u0011XBa)!\u0019Yl!3\u0004L\u000e5G\u0003BB_\u0007\u0007\u0004baa\u0007\u0004\"\r}\u0006\u0003\u0002Bp\u0007\u0003$qa!\u000b>\u0005\u0004\u0011)\u000fC\u0005\u0004Fv\n\t\u0011q\u0001\u0004H\u0006QQM^5eK:\u001cW\rJ\u001a\u0011\r\rE2qGB`\u0011\u001d\u0019\t&\u0010a\u0001\u0003+Bqa!\u0016>\u0001\u0004\u0011\t\u000eC\u0005\u0004Zu\u0002\n\u00111\u0001\u0004\\\u0005I\"/Y<T_\u000e\\W\r^*ue\u0016\fW\u000e\n3fM\u0006,H\u000e\u001e\u00134+\u0011\u0019Iga5\u0005\u000f\r%bH1\u0001\u0003f\u0006Qa-\u001b7f'R\u0014X-Y7\u0016\u0011\re71^By\t\u0013!Baa7\u0005\u001cQA1Q\\B{\u0007w$\t\u0001\u0005\u0004\u0004\u001c\r}71]\u0005\u0005\u0007C\u001ciB\u0001\u0007J]B,H\u000fR*ue\u0016\fW\u000eE\u0004}\u0007K\u001cIoa<\n\u0007\r\u001dXP\u0001\u0004UkBdWM\r\t\u0005\u0005?\u001cY\u000fB\u0004\u0004n~\u0012\rA!:\u0003\u0003-\u0003BAa8\u0004r\u0012911_ C\u0002\t\u0015(!\u0001,\t\u0013\r]x(!AA\u0004\re\u0018AC3wS\u0012,gnY3%iA11\u0011GB\u001c\u0007SD\u0011b!@@\u0003\u0003\u0005\u001daa@\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u00042\r]2q\u001e\u0005\n\t\u0007y\u0014\u0011!a\u0002\t\u000b\t!\"\u001a<jI\u0016t7-\u001a\u00137!\u0019\u0019\tda\u000e\u0005\bA!!q\u001cC\u0005\t\u001d!Ya\u0010b\u0001\t\u001b\u0011\u0011AR\t\u0005\u0005O$y\u0001\u0005\u0005\u0005\u0012\u0011]1\u0011^Bx\u001b\t!\u0019B\u0003\u0003\u0005\u0016\u0005-\u0016!C7baJ,G-^2f\u0013\u0011!I\u0002b\u0005\u0003\u0017%s\u0007/\u001e;G_Jl\u0017\r\u001e\u0005\b\u0005\u000b|\u0004\u0019AA++!!y\u0002\"\u000b\u0005.\u0011\rC\u0003\u0003C\u0011\t\u0013\"Y\u0005\"\u0018\u0015\u0011\u0011\rBq\u0006C\u001b\tw\u0001baa\u0007\u0004`\u0012\u0015\u0002c\u0002?\u0004f\u0012\u001dB1\u0006\t\u0005\u0005?$I\u0003B\u0004\u0004n\u0002\u0013\rA!:\u0011\t\t}GQ\u0006\u0003\b\u0007g\u0004%\u0019\u0001Bs\u0011%!\t\u0004QA\u0001\u0002\b!\u0019$\u0001\u0006fm&$WM\\2fI]\u0002ba!\r\u00048\u0011\u001d\u0002\"\u0003C\u001c\u0001\u0006\u0005\t9\u0001C\u001d\u0003))g/\u001b3f]\u000e,G\u0005\u000f\t\u0007\u0007c\u00199\u0004b\u000b\t\u0013\u0011u\u0002)!AA\u0004\u0011}\u0012AC3wS\u0012,gnY3%sA11\u0011GB\u001c\t\u0003\u0002BAa8\u0005D\u00119A1\u0002!C\u0002\u0011\u0015\u0013\u0003\u0002Bt\t\u000f\u0002\u0002\u0002\"\u0005\u0005\u0018\u0011\u001dB1\u0006\u0005\b\u0005\u000b\u0004\u0005\u0019AA+\u0011\u001d!i\u0005\u0011a\u0001\t\u001f\naAZ5mi\u0016\u0014\bc\u0002?\u0004\u001e\u0012E\u0013\u0011\u0019\t\u0005\t'\"I&\u0004\u0002\u0005V)!AqKAV\u0003\t17/\u0003\u0003\u0005\\\u0011U#\u0001\u0002)bi\"Dq\u0001b\u0018A\u0001\u0004\t\t-\u0001\u0007oK^4\u0015\u000e\\3t\u001f:d\u00170\u0006\u0005\u0005d\u00115D\u0011\u000fCD))!)\u0007\"$\u0005\u0010\u0012EE1\u0013\u000b\t\tO\"\u0019\b\"\u001f\u0005\u0000A111DBp\tS\u0002r\u0001`Bs\tW\"y\u0007\u0005\u0003\u0003`\u00125DaBBw\u0003\n\u0007!Q\u001d\t\u0005\u0005?$\t\bB\u0004\u0004t\u0006\u0013\rA!:\t\u0013\u0011U\u0014)!AA\u0004\u0011]\u0014aC3wS\u0012,gnY3%cA\u0002ba!\r\u00048\u0011-\u0004\"\u0003C>\u0003\u0006\u0005\t9\u0001C?\u0003-)g/\u001b3f]\u000e,G%M\u0019\u0011\r\rE2q\u0007C8\u0011%!\t)QA\u0001\u0002\b!\u0019)A\u0006fm&$WM\\2fIE\u0012\u0004CBB\u0019\u0007o!)\t\u0005\u0003\u0003`\u0012\u001dEa\u0002C\u0006\u0003\n\u0007A\u0011R\t\u0005\u0005O$Y\t\u0005\u0005\u0005\u0012\u0011]A1\u000eC8\u0011\u001d\u0011)-\u0011a\u0001\u0003+Bq\u0001\"\u0014B\u0001\u0004!y\u0005C\u0004\u0005`\u0005\u0003\r!!1\t\u000f\u0005\u0015\u0013\t1\u0001\u0002&\u0006qA/\u001a=u\r&dWm\u0015;sK\u0006lG\u0003\u0002CM\t?\u0003baa\u0007\u0005\u001c\u0006U\u0013\u0002\u0002CO\u0007;\u0011q\u0001R*ue\u0016\fW\u000eC\u0004\u0003F\n\u0003\r!!\u0016\u0002'\tLg.\u0019:z%\u0016\u001cwN\u001d3t'R\u0014X-Y7\u0015\r\u0011\u0015F1\u0017C[!\u0019\u0019Y\u0002b'\u0005(B)A\u0010\"+\u0005.&\u0019A1V?\u0003\u000b\u0005\u0013(/Y=\u0011\u0007q$y+C\u0002\u00052v\u0014AAQ=uK\"9!QY\"A\u0002\u0005U\u0003b\u0002C\\\u0007\u0002\u0007!\u0011[\u0001\re\u0016\u001cwN\u001d3MK:<G\u000f[\u0001\fcV,W/Z*ue\u0016\fW.\u0006\u0003\u0005>\u0012\u0015GC\u0002C`\t\u001b$I\u000f\u0006\u0003\u0005B\u0012\u001d\u0007CBB\u000e\u0007?$\u0019\r\u0005\u0003\u0003`\u0012\u0015GaBB\u0015\t\n\u0007!Q\u001d\u0005\n\t\u0013$\u0015\u0011!a\u0002\t\u0017\f1\"\u001a<jI\u0016t7-\u001a\u00132gA11\u0011GB\u001c\t\u0007Dq\u0001b4E\u0001\u0004!\t.A\u0003rk\u0016,X\r\u0005\u0004\u0005T\u0012eGQ\\\u0007\u0003\t+TA\u0001b6\u0002\u0014\u00069Q.\u001e;bE2,\u0017\u0002\u0002Cn\t+\u0014Q!U;fk\u0016\u0004b\u0001b8\u0005f\u0012\rWB\u0001Cq\u0015\r!\u0019o]\u0001\u0004e\u0012$\u0017\u0002\u0002Ct\tC\u00141A\u0015#E\u0011%!Y\u000f\u0012I\u0001\u0002\u0004\t\t-\u0001\u0006p]\u0016\fE/\u0011+j[\u0016\fQ#];fk\u0016\u001cFO]3b[\u0012\"WMZ1vYR$#'\u0006\u0003\u0005r\u0012UXC\u0001CzU\u0011\t\tm!\u001c\u0005\u000f\r%RI1\u0001\u0003fV!A\u0011`C\u0001)!!Y0\"\u0003\u0006\u0010\u0015EA\u0003\u0002C\u007f\u000b\u0007\u0001baa\u0007\u0004`\u0012}\b\u0003\u0002Bp\u000b\u0003!qa!\u000bG\u0005\u0004\u0011)\u000fC\u0005\u0006\u0006\u0019\u000b\t\u0011q\u0001\u0006\b\u0005YQM^5eK:\u001cW\rJ\u00195!\u0019\u0019\tda\u000e\u0005\u0000\"9Aq\u001a$A\u0002\u0015-\u0001C\u0002Cj\t3,i\u0001\u0005\u0004\u0005`\u0012\u0015Hq \u0005\b\tW4\u0005\u0019AAa\u0011\u001d)\u0019B\u0012a\u0001\u000b\u001b\t!\u0002Z3gCVdGO\u0015#E\u0003\u0015)h.[8o+\u0011)I\"\"\t\u0015\t\u0015mQ\u0011\u0006\u000b\u0005\u000b;)\u0019\u0003\u0005\u0004\u0004\u001c\u0011mUq\u0004\t\u0005\u0005?,\t\u0003B\u0004\u0004*\u001d\u0013\rA!:\t\u0013\u0015\u0015r)!AA\u0004\u0015\u001d\u0012aC3wS\u0012,gnY3%cU\u0002ba!\r\u00048\u0015}\u0001bBC\u0016\u000f\u0002\u0007QQF\u0001\bgR\u0014X-Y7t!\u0019\tY(!\"\u0006\u001e\u0005IAO]1og\u001a|'/\\\u000b\u0005\u000bg)Y\u0004\u0006\u0004\u00066\u0015\rSQ\u000b\u000b\u0005\u000bo)i\u0004\u0005\u0004\u0004\u001c\u0011mU\u0011\b\t\u0005\u0005?,Y\u0004B\u0004\u0004*!\u0013\rA!:\t\u0013\u0015}\u0002*!AA\u0004\u0015\u0005\u0013aC3wS\u0012,gnY3%cY\u0002ba!\r\u00048\u0015e\u0002bBC#\u0011\u0002\u0007QqI\u0001\tIN$(/Z1ngB1\u00111PAC\u000b\u0013\u0002D!b\u0013\u0006PA111\u0004CN\u000b\u001b\u0002BAa8\u0006P\u0011aQ\u0011KC*\u0003\u0003\u0005\tQ!\u0001\u0003f\n\u0019q\fJ\u0019\t\u000f\u0015\u0015\u0003\n1\u0001\u0006H!9Qq\u000b%A\u0002\u0015e\u0013!\u0004;sC:\u001chm\u001c:n\rVt7\rE\u0005}\u000b7*y&b\u001c\u0006z%\u0019QQL?\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004CBA>\u0003\u000b+\t\u0007\r\u0003\u0006d\u0015\u001d\u0004C\u0002Cp\tK,)\u0007\u0005\u0003\u0003`\u0016\u001dD\u0001DC5\u000bW\n\t\u0011!A\u0003\u0002\t\u0015(aA0%e!9Qq\u000b%A\u0002\u00155\u0004#\u0003?\u0006\\\u0015}SqNC;!\u0011\ti\"\"\u001d\n\u0007\u0015M\u0014O\u0001\u0003US6,\u0007C\u0002Cp\tK,9\b\u0005\u0003\u0003`\u0016m\u0002C\u0002Cp\tK,I$\u0001\u000bbI\u0012\u001cFO]3b[&tw\rT5ti\u0016tWM\u001d\u000b\u0005\u0005#)y\bC\u0004\u0006\u0002&\u0003\r!b!\u0002#M$(/Z1nS:<G*[:uK:,'\u000f\u0005\u0003\u0003*\u0015\u0015\u0015\u0002BCD\u0005W\u0011\u0011c\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s\u0003]\u0011X-\\8wKN#(/Z1nS:<G*[:uK:,'\u000f\u0006\u0003\u0003\u0012\u00155\u0005bBCA\u0015\u0002\u0007Q1Q\u0001\tm\u0006d\u0017\u000eZ1uKR\u0011!\u0011C\u0001\tO\u0016$8\u000b^1uKR\u0011!1\u000f\u0015\u0004\u0019\u0016e\u0005\u0003BCN\u000b?k!!\"(\u000b\u0007\re4/\u0003\u0003\u0006\"\u0016u%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!B:uCJ$\u0018\u0001E1xC&$H+\u001a:nS:\fG/[8o\u0003e\tw/Y5u)\u0016\u0014X.\u001b8bi&|gn\u0014:US6,w.\u001e;\u0015\t\u0005\u0005W1\u0016\u0005\b\u000b[{\u0005\u0019ACX\u0003\u001d!\u0018.\\3pkR\u00042\u0001`CY\u0013\r)\u0019, \u0002\u0005\u0019>tw-\u0001\u0003ti>\u0004H\u0003\u0002B\t\u000bsC\u0011\"b/Q!\u0003\u0005\r!!1\u0002!M$x\u000e]*qCJ\\7i\u001c8uKb$\u0018AD:u_B$C-\u001a4bk2$H%\r\u000b\u0007\u0005#)\t-b1\t\u000f\u0015m&\u000b1\u0001\u0002B\"9QQ\u0019*A\u0002\u0005\u0005\u0017AD:u_B<%/Y2fMVdG._\u0001\u000fgR|\u0007o\u00148TQV$Hm\\<o\u0003a\u0011XmZ5ti\u0016\u0014\bK]8he\u0016\u001c8\u000fT5ti\u0016tWM]\u0001\u001bk:\u0014XmZ5ti\u0016\u0014\bK]8he\u0016\u001c8\u000fT5ti\u0016tWM\u001d\u0015\f\u0001\u0015=WQ[Cl\u000b7,i\u000eE\u0002}\u000b#L1!b5~\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t)I.A\u001cE'R\u0014X-Y7!SN\u0004C-\u001a9sK\u000e\fG/\u001a3/A5KwM]1uK\u0002\"x\u000eI*ueV\u001cG/\u001e:fI\u0002\u001aFO]3b[&twML\u0001\u0006g&t7-Z\u0011\u0003\u000b?\f1b\u00159be.\u00043G\f\u001b/a\u0005\u00012\u000b\u001e:fC6LgnZ\"p]R,\u0007\u0010\u001e\t\u0004\u0003;96\u0003B,|\u0003\u0007!\"!b9\u0002\u001f\u0005\u001bE+\u0013,B)&{ej\u0018'P\u0007.+\"!\"<\u0011\t\u0015=XQ_\u0007\u0003\u000bcTA!b=\u0002\u0000\u0006!A.\u00198h\u0013\u0011)90\"=\u0003\r=\u0013'.Z2u\u0003A\t5\tV%W\u0003RKuJT0M\u001f\u000e[\u0005%\u0001\fT\u0011V#FiT,O?\"{ujS0Q%&{%+\u0013+Z+\t\u0011\t.A\fT\u0011V#FiT,O?\"{ujS0Q%&{%+\u0013+ZA\u0005i\u0011m\u0019;jm\u0016\u001cuN\u001c;fqR,\"A\"\u0002\u0011\r\u0005E(qQA\u0018\u00039\t7\r^5wK\u000e{g\u000e^3yi\u0002\nA$Y:tKJ$hj\\(uQ\u0016\u00148i\u001c8uKb$\u0018j]!di&4X-\u0001\ttKR\f5\r^5wK\u000e{g\u000e^3yiR!!\u0011\u0003D\b\u0011\u001d1\t\u0002\u0019a\u0001\u0003_\t1a]:d\u0003%9W\r^!di&4X\r\u0006\u0002\u0007\u0018A)APa\u0016\u00020\u0005\tr-\u001a;BGRLg/Z(s\u0007J,\u0017\r^3\u0015\t\u0005=bQ\u0004\u0005\b\r?\u0011\u0007\u0019\u0001D\u0011\u00031\u0019'/Z1uS:<g)\u001e8d!\u0015ah1EA\u0018\u0013\r1)# \u0002\n\rVt7\r^5p]B\"\"\"a\f\u0007*\u00195bq\u0006D\u0019\u0011\u001d1Yc\u0019a\u0001\u0003+\nab\u00195fG.\u0004x.\u001b8u!\u0006$\b\u000eC\u0004\u0007 \r\u0004\rA\"\t\t\u0013\u0005\r6\r%AA\u0002\u0005\u0015\u0006\"\u0003D\u001aGB\u0005\t\u0019AAa\u00035\u0019'/Z1uK>sWI\u001d:pe\u0006Yr-\u001a;BGRLg/Z(s\u0007J,\u0017\r^3%I\u00164\u0017-\u001e7uIM*\"A\"\u000f+\t\u0005\u00156QN\u0001\u001cO\u0016$\u0018i\u0019;jm\u0016|%o\u0011:fCR,G\u0005Z3gCVdG\u000f\n\u001b\u0002\u0017\u001d,Go\u0014:De\u0016\fG/\u001a\u000b\u000b\u0003_1\tEb\u0011\u0007F\u0019\u001d\u0003b\u0002D\u0016M\u0002\u0007\u0011Q\u000b\u0005\b\r?1\u0007\u0019\u0001D\u0011\u0011%\t\u0019K\u001aI\u0001\u0002\u0004\t)\u000bC\u0005\u00074\u0019\u0004\n\u00111\u0001\u0002B\u0006)r-\u001a;Pe\u000e\u0013X-\u0019;fI\u0011,g-Y;mi\u0012\u001a\u0014!F4fi>\u00138I]3bi\u0016$C-\u001a4bk2$H\u0005N\u0001\u000bU\u0006\u0014xJZ\"mCN\u001cH\u0003\u0002D)\r'\u0002R\u0001 B,\u0003+BqA\"\u0016j\u0001\u000419&A\u0002dYN\u0004DA\"\u0017\u0007bA1\u0011q\u000bD.\r?JAA\"\u0018\u0002j\t)1\t\\1tgB!!q\u001cD1\t11\u0019Gb\u0015\u0002\u0002\u0003\u0005)\u0011\u0001Bs\u0005\ryFeM\u0001\u0016GJ,\u0017\r^3OK^\u001c\u0006/\u0019:l\u0007>tG/\u001a=u)\u0011\t\tB\"\u001b\t\u000f\u0005\u0015#\u000e1\u0001\u0002HQa\u0011\u0011\u0003D7\r_2\tHb\u001d\u0007v!9\u00111K6A\u0002\u0005U\u0003bBA7W\u0002\u0007\u0011Q\u000b\u0005\b\u0003gZ\u0007\u0019AA+\u0011\u001d\t9h\u001ba\u0001\u0003sBq!!$l\u0001\u0004\ty)A\u0007sI\u0012$vNR5mK:\u000bW.Z\u000b\u0005\rw2I\t\u0006\u0005\u0002V\u0019ud\u0011\u0011DC\u0011\u001d1y\b\u001ca\u0001\u0003+\na\u0001\u001d:fM&D\bb\u0002DBY\u0002\u0007\u0011QK\u0001\u0007gV4g-\u001b=\t\u000f\u0019\u001dE\u000e1\u0001\u0006p\u0005!A/[7f\t\u001d\u0019I\u0003\u001cb\u0001\u0005K\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\"TC\u0001DHU\u0011\t)f!\u001c\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00136+\t1)J\u000b\u0003\u0002z\r5\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'\u0006\u0002\u0007\u001c*\"\u0011qRB7Q-9VqZCk\u000b/,Y.\"8)\u0017Y+y-\"6\u0006X\u0016mWQ\u001c"
)
public class StreamingContext implements Logging {
   private final SparkContext _sc;
   private final Checkpoint _cp;
   private final boolean isCheckpointPresent;
   private final SparkContext sc;
   private final SparkConf conf;
   private final SparkEnv env;
   private final DStreamGraph graph;
   private final AtomicInteger nextInputStreamId;
   private String checkpointDir;
   private final Duration checkpointDuration;
   private final JobScheduler scheduler;
   private final ContextWaiter waiter;
   private final StreamingJobProgressListener progressListener;
   private final Option uiTab;
   private final StreamingSource streamingSource;
   private StreamingContextState state;
   private final AtomicReference startSite;
   private final AtomicReference savedProperties;
   private Object shutdownHookRef;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map $lessinit$greater$default$6() {
      return StreamingContext$.MODULE$.$lessinit$greater$default$6();
   }

   public static Seq $lessinit$greater$default$5() {
      return StreamingContext$.MODULE$.$lessinit$greater$default$5();
   }

   public static String $lessinit$greater$default$4() {
      return StreamingContext$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option jarOfClass(final Class cls) {
      return StreamingContext$.MODULE$.jarOfClass(cls);
   }

   public static boolean getOrCreate$default$4() {
      return StreamingContext$.MODULE$.getOrCreate$default$4();
   }

   public static Configuration getOrCreate$default$3() {
      return StreamingContext$.MODULE$.getOrCreate$default$3();
   }

   public static StreamingContext getOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      return StreamingContext$.MODULE$.getOrCreate(checkpointPath, creatingFunc, hadoopConf, createOnError);
   }

   public static boolean getActiveOrCreate$default$4() {
      return StreamingContext$.MODULE$.getActiveOrCreate$default$4();
   }

   public static Configuration getActiveOrCreate$default$3() {
      return StreamingContext$.MODULE$.getActiveOrCreate$default$3();
   }

   public static StreamingContext getActiveOrCreate(final String checkpointPath, final Function0 creatingFunc, final Configuration hadoopConf, final boolean createOnError) {
      return StreamingContext$.MODULE$.getActiveOrCreate(checkpointPath, creatingFunc, hadoopConf, createOnError);
   }

   public static StreamingContext getActiveOrCreate(final Function0 creatingFunc) {
      return StreamingContext$.MODULE$.getActiveOrCreate(creatingFunc);
   }

   public static Option getActive() {
      return StreamingContext$.MODULE$.getActive();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public boolean isCheckpointPresent() {
      return this.isCheckpointPresent;
   }

   public SparkContext sc() {
      return this.sc;
   }

   public SparkConf conf() {
      return this.conf;
   }

   public SparkEnv env() {
      return this.env;
   }

   public DStreamGraph graph() {
      return this.graph;
   }

   private AtomicInteger nextInputStreamId() {
      return this.nextInputStreamId;
   }

   public String checkpointDir() {
      return this.checkpointDir;
   }

   public void checkpointDir_$eq(final String x$1) {
      this.checkpointDir = x$1;
   }

   public Duration checkpointDuration() {
      return this.checkpointDuration;
   }

   public JobScheduler scheduler() {
      return this.scheduler;
   }

   public ContextWaiter waiter() {
      return this.waiter;
   }

   public StreamingJobProgressListener progressListener() {
      return this.progressListener;
   }

   public Option uiTab() {
      return this.uiTab;
   }

   private StreamingSource streamingSource() {
      return this.streamingSource;
   }

   private StreamingContextState state() {
      return this.state;
   }

   private void state_$eq(final StreamingContextState x$1) {
      this.state = x$1;
   }

   private AtomicReference startSite() {
      return this.startSite;
   }

   public AtomicReference savedProperties() {
      return this.savedProperties;
   }

   public CallSite getStartSite() {
      return (CallSite)this.startSite().get();
   }

   private Object shutdownHookRef() {
      return this.shutdownHookRef;
   }

   private void shutdownHookRef_$eq(final Object x$1) {
      this.shutdownHookRef = x$1;
   }

   public SparkContext sparkContext() {
      return this.sc();
   }

   public void remember(final Duration duration) {
      this.graph().remember(duration);
   }

   public void checkpoint(final String directory) {
      if (directory != null) {
         Path path = new Path(directory);
         FileSystem fs = path.getFileSystem(this.sparkContext().hadoopConfiguration());
         fs.mkdirs(path);
         String fullPath = fs.getFileStatus(path).getPath().toString();
         this.sc().setCheckpointDir(fullPath);
         this.checkpointDir_$eq(fullPath);
      } else {
         this.checkpointDir_$eq((String)null);
      }
   }

   public boolean isCheckpointingEnabled() {
      return this.checkpointDir() != null;
   }

   public Checkpoint initialCheckpoint() {
      return this.isCheckpointPresent() ? this._cp : null;
   }

   public int getNewInputStreamId() {
      return this.nextInputStreamId().getAndIncrement();
   }

   public Object withScope(final Function0 body) {
      return this.sparkContext().withScope(body);
   }

   public Object withNamedScope(final String name, final Function0 body) {
      return .MODULE$.withScope(this.sc(), name, false, false, body);
   }

   public ReceiverInputDStream receiverStream(final Receiver receiver, final ClassTag evidence$1) {
      return (ReceiverInputDStream)this.withNamedScope("receiver stream", () -> new PluggableInputDStream(this, receiver, evidence$1));
   }

   public ReceiverInputDStream socketTextStream(final String hostname, final int port, final StorageLevel storageLevel) {
      return (ReceiverInputDStream)this.withNamedScope("socket text stream", () -> this.socketStream(hostname, port, (inputStream) -> SocketReceiver$.MODULE$.bytesToLines(inputStream), storageLevel, scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public StorageLevel socketTextStream$default$3() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER_2();
   }

   public ReceiverInputDStream socketStream(final String hostname, final int port, final Function1 converter, final StorageLevel storageLevel, final ClassTag evidence$2) {
      return new SocketInputDStream(this, hostname, port, converter, storageLevel, evidence$2);
   }

   public ReceiverInputDStream rawSocketStream(final String hostname, final int port, final StorageLevel storageLevel, final ClassTag evidence$3) {
      return (ReceiverInputDStream)this.withNamedScope("raw socket stream", () -> new RawInputDStream(this, hostname, port, storageLevel, evidence$3));
   }

   public StorageLevel rawSocketStream$default$3() {
      return org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK_SER_2();
   }

   public InputDStream fileStream(final String directory, final ClassTag evidence$4, final ClassTag evidence$5, final ClassTag evidence$6) {
      return new FileInputDStream(this, directory, FileInputDStream$.MODULE$.$lessinit$greater$default$3(), FileInputDStream$.MODULE$.$lessinit$greater$default$4(), FileInputDStream$.MODULE$.$lessinit$greater$default$5(), evidence$4, evidence$5, evidence$6);
   }

   public InputDStream fileStream(final String directory, final Function1 filter, final boolean newFilesOnly, final ClassTag evidence$7, final ClassTag evidence$8, final ClassTag evidence$9) {
      return new FileInputDStream(this, directory, filter, newFilesOnly, FileInputDStream$.MODULE$.$lessinit$greater$default$5(), evidence$7, evidence$8, evidence$9);
   }

   public InputDStream fileStream(final String directory, final Function1 filter, final boolean newFilesOnly, final Configuration conf, final ClassTag evidence$10, final ClassTag evidence$11, final ClassTag evidence$12) {
      return new FileInputDStream(this, directory, filter, newFilesOnly, scala.Option..MODULE$.apply(conf), evidence$10, evidence$11, evidence$12);
   }

   public DStream textFileStream(final String directory) {
      return (DStream)this.withNamedScope("text file stream", () -> this.fileStream(directory, scala.reflect.ClassTag..MODULE$.apply(LongWritable.class), scala.reflect.ClassTag..MODULE$.apply(Text.class), scala.reflect.ClassTag..MODULE$.apply(TextInputFormat.class)).map((x$2) -> ((Text)x$2._2()).toString(), scala.reflect.ClassTag..MODULE$.apply(String.class)));
   }

   public DStream binaryRecordsStream(final String directory, final int recordLength) {
      return (DStream)this.withNamedScope("binary records stream", () -> {
         Configuration conf = this._sc.hadoopConfiguration();
         conf.setInt(org.apache.spark.input.FixedLengthBinaryInputFormat..MODULE$.RECORD_LENGTH_PROPERTY(), recordLength);
         InputDStream br = this.fileStream(directory, (path) -> BoxesRunTime.boxToBoolean($anonfun$binaryRecordsStream$2(path)), true, conf, scala.reflect.ClassTag..MODULE$.apply(LongWritable.class), scala.reflect.ClassTag..MODULE$.apply(BytesWritable.class), scala.reflect.ClassTag..MODULE$.apply(FixedLengthBinaryInputFormat.class));
         return br.map((x0$1) -> {
            if (x0$1 != null) {
               BytesWritable v = (BytesWritable)x0$1._2();
               byte[] bytes = v.copyBytes();
               scala.Predef..MODULE$.require(bytes.length == recordLength, () -> "Byte array does not have correct length. " + bytes.length + " did not equal recordLength: " + recordLength);
               return bytes;
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
      });
   }

   public InputDStream queueStream(final Queue queue, final boolean oneAtATime, final ClassTag evidence$13) {
      return this.queueStream(queue, oneAtATime, this.sc().makeRDD((Seq)scala.package..MODULE$.Seq().empty(), 1, evidence$13), evidence$13);
   }

   public InputDStream queueStream(final Queue queue, final boolean oneAtATime, final RDD defaultRDD, final ClassTag evidence$14) {
      return new QueueInputDStream(this, queue, oneAtATime, defaultRDD, evidence$14);
   }

   public boolean queueStream$default$2() {
      return true;
   }

   public DStream union(final Seq streams, final ClassTag evidence$15) {
      return (DStream)this.withScope(() -> new UnionDStream((DStream[])streams.toArray(scala.reflect.ClassTag..MODULE$.apply(DStream.class)), evidence$15));
   }

   public DStream transform(final Seq dstreams, final Function2 transformFunc, final ClassTag evidence$16) {
      return (DStream)this.withScope(() -> {
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new TransformedDStream(dstreams, (Function2)qual$1.clean(transformFunc, x$2), evidence$16);
      });
   }

   public void addStreamingListener(final StreamingListener streamingListener) {
      this.scheduler().listenerBus().addListener(streamingListener);
   }

   public void removeStreamingListener(final StreamingListener streamingListener) {
      this.scheduler().listenerBus().removeListener(streamingListener);
   }

   private void validate() {
      scala.Predef..MODULE$.assert(this.graph() != null, () -> "Graph is null");
      this.graph().validate();
      scala.Predef..MODULE$.require(!this.isCheckpointingEnabled() || this.checkpointDuration() != null, () -> "Checkpoint directory has been set, but the graph checkpointing interval has not been set. Please use StreamingContext.checkpoint() to set the interval.");
      if (this.isCheckpointingEnabled()) {
         Checkpoint checkpoint = new Checkpoint(this, new Time(0L));

         try {
            Checkpoint$.MODULE$.serialize(checkpoint, this.conf());
         } catch (NotSerializableException var3) {
            NotSerializableException var10002 = org.apache.spark.serializer.SerializationDebugger..MODULE$.improveException(checkpoint, var3);
            throw new NotSerializableException("DStream checkpointing has been enabled but the DStreams with their functions are not serializable\n" + var10002.getMessage());
         }
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      if (org.apache.spark.util.Utils..MODULE$.isDynamicAllocationEnabled(this.sc().conf()) || ExecutorAllocationManager$.MODULE$.isDynamicAllocationEnabled(this.conf())) {
         this.logWarning((Function0)(() -> "Dynamic Allocation is enabled for this application. Enabling Dynamic allocation for Spark Streaming applications can cause data loss if Write Ahead Log is not enabled for non-replayable sources. See the programming guide for details on how to enable the Write Ahead Log."));
      }
   }

   @DeveloperApi
   public synchronized StreamingContextState getState() {
      return this.state();
   }

   public synchronized void start() {
      StreamingContextState var2 = this.state();
      if (StreamingContextState.INITIALIZED.equals(var2)) {
         this.startSite().set(DStream$.MODULE$.getCreationSite());
         synchronized(StreamingContext$.MODULE$.org$apache$spark$streaming$StreamingContext$$ACTIVATION_LOCK()){}

         try {
            StreamingContext$.MODULE$.org$apache$spark$streaming$StreamingContext$$assertNoOtherContextIsActive();
            this.liftedTree1$1();
            StreamingContext$.MODULE$.org$apache$spark$streaming$StreamingContext$$setActiveContext(this);
         } catch (Throwable var5) {
            throw var5;
         }

         this.logDebug((Function0)(() -> "Adding shutdown hook"));
         this.shutdownHookRef_$eq(org.apache.spark.util.ShutdownHookManager..MODULE$.addShutdownHook(StreamingContext$.MODULE$.org$apache$spark$streaming$StreamingContext$$SHUTDOWN_HOOK_PRIORITY(), (JFunction0.mcV.sp)() -> this.stopOnShutdown()));
         scala.Predef..MODULE$.assert(this.env().metricsSystem() != null);
         this.env().metricsSystem().registerSource(this.streamingSource());
         this.uiTab().foreach((x$3) -> {
            $anonfun$start$5(x$3);
            return BoxedUnit.UNIT;
         });
         this.logInfo((Function0)(() -> "StreamingContext started"));
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (StreamingContextState.ACTIVE.equals(var2)) {
         this.logWarning((Function0)(() -> "StreamingContext has already been started"));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (StreamingContextState.STOPPED.equals(var2)) {
         throw new IllegalStateException("StreamingContext has already been stopped");
      } else {
         throw new MatchError(var2);
      }
   }

   public void awaitTermination() {
      this.waiter().waitForStopOrError(this.waiter().waitForStopOrError$default$1());
   }

   public boolean awaitTerminationOrTimeout(final long timeout) {
      return this.waiter().waitForStopOrError(timeout);
   }

   public synchronized void stop(final boolean stopSparkContext) {
      this.stop(stopSparkContext, false);
   }

   public void stop(final boolean stopSparkContext, final boolean stopGracefully) {
      Object shutdownHookRefToRemove = null;
      if (BoxesRunTime.unboxToBoolean(org.apache.spark.scheduler.LiveListenerBus..MODULE$.withinListenerThread().value())) {
         throw new SparkException("Cannot stop StreamingContext within listener bus thread.");
      } else {
         synchronized(this){}

         try {
            StreamingContextState var6 = this.state();
            if (StreamingContextState.INITIALIZED.equals(var6)) {
               this.logWarning((Function0)(() -> "StreamingContext has not been started yet"));
               this.state_$eq(StreamingContextState.STOPPED);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else if (StreamingContextState.STOPPED.equals(var6)) {
               this.logWarning((Function0)(() -> "StreamingContext has already been stopped"));
               this.state_$eq(StreamingContextState.STOPPED);
               BoxedUnit var9 = BoxedUnit.UNIT;
            } else {
               if (!StreamingContextState.ACTIVE.equals(var6)) {
                  throw new MatchError(var6);
               }

               org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.scheduler().stop(stopGracefully));
               org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.env().metricsSystem().removeSource(this.streamingSource()));
               org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.uiTab().foreach((x$4) -> {
                     $anonfun$stop$6(x$4);
                     return BoxedUnit.UNIT;
                  }));
               org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.unregisterProgressListener());
               StreamingContext$.MODULE$.org$apache$spark$streaming$StreamingContext$$setActiveContext((StreamingContext)null);
               org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> this.waiter().notifyStop());
               if (this.shutdownHookRef() != null) {
                  shutdownHookRefToRemove = this.shutdownHookRef();
                  this.shutdownHookRef_$eq((Object)null);
               }

               this.logInfo((Function0)(() -> "StreamingContext stopped successfully"));
               this.state_$eq(StreamingContextState.STOPPED);
               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         } catch (Throwable var8) {
            throw var8;
         }

         if (shutdownHookRefToRemove != null) {
            BoxesRunTime.boxToBoolean(org.apache.spark.util.ShutdownHookManager..MODULE$.removeShutdownHook(shutdownHookRefToRemove));
         } else {
            BoxedUnit var11 = BoxedUnit.UNIT;
         }

         if (stopSparkContext) {
            this.sc().stop();
         }
      }
   }

   public boolean stop$default$1() {
      return this.conf().getBoolean("spark.streaming.stopSparkContextByDefault", true);
   }

   private void stopOnShutdown() {
      boolean stopGracefully = BoxesRunTime.unboxToBoolean(this.conf().get(StreamingConf$.MODULE$.STOP_GRACEFULLY_ON_SHUTDOWN()));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Invoking stop(stopGracefully="})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") from shutdown hook"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, BoxesRunTime.boxToBoolean(stopGracefully))}))))));
      this.stop(false, stopGracefully);
   }

   private void registerProgressListener() {
      this.addStreamingListener(this.progressListener());
      this.sc().addSparkListener(this.progressListener());
      this.sc().ui().foreach((x$5) -> {
         $anonfun$registerProgressListener$1(this, x$5);
         return BoxedUnit.UNIT;
      });
   }

   private void unregisterProgressListener() {
      this.removeStreamingListener(this.progressListener());
      this.sc().removeSparkListener(this.progressListener());
      this.sc().ui().foreach((x$6) -> {
         $anonfun$unregisterProgressListener$1(x$6);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$new$3(final StreamingContext $this, final String directory) {
      $this.checkpoint(directory);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$binaryRecordsStream$2(final Path path) {
      return FileInputDStream$.MODULE$.defaultFilter(path);
   }

   // $FF: synthetic method
   private final void liftedTree1$1() {
      try {
         this.validate();
         this.registerProgressListener();
         org.apache.spark.util.ThreadUtils..MODULE$.runInNewThread("streaming-start", org.apache.spark.util.ThreadUtils..MODULE$.runInNewThread$default$2(), (JFunction0.mcV.sp)() -> {
            this.sparkContext().setCallSite((CallSite)this.startSite().get());
            this.sparkContext().clearJobGroup();
            this.sparkContext().setLocalProperty(org.apache.spark.SparkContext..MODULE$.SPARK_JOB_INTERRUPT_ON_CANCEL(), "false");
            this.savedProperties().set(org.apache.spark.util.Utils..MODULE$.cloneProperties((Properties)this.sparkContext().localProperties().get()));
            this.scheduler().start();
         });
         this.state_$eq(StreamingContextState.ACTIVE);
         this.scheduler().listenerBus().post(new StreamingListenerStreamingStarted(System.currentTimeMillis()));
      } catch (Throwable var5) {
         if (var5 != null && scala.util.control.NonFatal..MODULE$.apply(var5)) {
            this.logError((Function0)(() -> "Error starting the context, marking it as stopped"), var5);
            this.scheduler().stop(false);
            this.state_$eq(StreamingContextState.STOPPED);
            throw var5;
         } else {
            throw var5;
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$start$5(final StreamingTab x$3) {
      x$3.attach();
   }

   // $FF: synthetic method
   public static final void $anonfun$stop$6(final StreamingTab x$4) {
      x$4.detach();
   }

   // $FF: synthetic method
   public static final void $anonfun$registerProgressListener$1(final StreamingContext $this, final SparkUI x$5) {
      x$5.setStreamingJobProgressListener($this.progressListener());
   }

   // $FF: synthetic method
   public static final void $anonfun$unregisterProgressListener$1(final SparkUI x$6) {
      x$6.clearStreamingJobProgressListener();
   }

   public StreamingContext(final SparkContext _sc, final Checkpoint _cp, final Duration _batchDur) {
      this._sc = _sc;
      this._cp = _cp;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(_sc != null || _cp != null, () -> "Spark Streaming cannot be initialized with both SparkContext and checkpoint as null");
      this.isCheckpointPresent = _cp != null;
      SparkContext var10001;
      if (_sc != null) {
         var10001 = _sc;
      } else {
         if (!this.isCheckpointPresent()) {
            throw new SparkException("Cannot create StreamingContext without a SparkContext");
         }

         var10001 = org.apache.spark.SparkContext..MODULE$.getOrCreate(_cp.createSparkConf());
      }

      label71: {
         label70: {
            this.sc = var10001;
            String var10000 = this.sc().conf().get("spark.master");
            String var5 = "local";
            if (var10000 == null) {
               if (var5 == null) {
                  break label70;
               }
            } else if (var10000.equals(var5)) {
               break label70;
            }

            var10000 = this.sc().conf().get("spark.master");
            String var6 = "local[1]";
            if (var10000 == null) {
               if (var6 != null) {
                  break label71;
               }
            } else if (!var10000.equals(var6)) {
               break label71;
            }
         }

         this.logWarning((Function0)(() -> "spark.master should be set as local[n], n > 1 in local mode if you have receivers to get data, otherwise Spark jobs will not get resources to process the received data."));
      }

      this.conf = this.sc().conf();
      this.env = this.sc().env();
      DStreamGraph var12;
      if (this.isCheckpointPresent()) {
         _cp.graph().setContext(this);
         _cp.graph().restoreCheckpointData();
         var12 = _cp.graph();
      } else {
         scala.Predef..MODULE$.require(_batchDur != null, () -> "Batch duration for StreamingContext cannot be null");
         DStreamGraph newGraph = new DStreamGraph();
         newGraph.setBatchDuration(_batchDur);
         var12 = newGraph;
      }

      this.graph = var12;
      this.nextInputStreamId = new AtomicInteger(0);
      String var13;
      if (this.isCheckpointPresent()) {
         this.sc().setCheckpointDir(_cp.checkpointDir());
         var13 = _cp.checkpointDir();
      } else {
         var13 = null;
      }

      this.checkpointDir = var13;
      this.checkpointDuration = this.isCheckpointPresent() ? _cp.checkpointDuration() : this.graph().batchDuration();
      this.scheduler = new JobScheduler(this);
      this.waiter = new ContextWaiter();
      this.progressListener = new StreamingJobProgressListener(this);
      Option var8 = this.sparkContext().ui();
      Object var14;
      if (var8 instanceof Some var9) {
         SparkUI ui = (SparkUI)var9.value();
         var14 = new Some(new StreamingTab(this, ui));
      } else {
         if (!scala.None..MODULE$.equals(var8)) {
            throw new MatchError(var8);
         }

         var14 = scala.None..MODULE$;
      }

      this.uiTab = (Option)var14;
      this.streamingSource = new StreamingSource(this);
      this.state = StreamingContextState.INITIALIZED;
      this.startSite = new AtomicReference((Object)null);
      this.savedProperties = new AtomicReference(new Properties());
      this.conf().getOption("spark.streaming.checkpoint.directory").foreach((directory) -> {
         $anonfun$new$3(this, directory);
         return BoxedUnit.UNIT;
      });
   }

   public StreamingContext(final SparkContext sparkContext, final Duration batchDuration) {
      this(sparkContext, (Checkpoint)null, batchDuration);
   }

   public StreamingContext(final SparkConf conf, final Duration batchDuration) {
      this(StreamingContext$.MODULE$.createNewSparkContext(conf), (Checkpoint)null, batchDuration);
   }

   public StreamingContext(final String master, final String appName, final Duration batchDuration, final String sparkHome, final Seq jars, final Map environment) {
      this(StreamingContext$.MODULE$.createNewSparkContext(master, appName, sparkHome, jars, environment), (Checkpoint)null, batchDuration);
   }

   public StreamingContext(final String path, final Configuration hadoopConf) {
      this((SparkContext)null, (Checkpoint)CheckpointReader$.MODULE$.read(path, new SparkConf(), hadoopConf, CheckpointReader$.MODULE$.read$default$4()).orNull(scala..less.colon.less..MODULE$.refl()), (Duration)null);
   }

   public StreamingContext(final String path) {
      this(path, org.apache.spark.deploy.SparkHadoopUtil..MODULE$.get().conf());
   }

   public StreamingContext(final String path, final SparkContext sparkContext) {
      this(sparkContext, (Checkpoint)CheckpointReader$.MODULE$.read(path, sparkContext.conf(), sparkContext.hadoopConfiguration(), CheckpointReader$.MODULE$.read$default$4()).orNull(scala..less.colon.less..MODULE$.refl()), (Duration)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
