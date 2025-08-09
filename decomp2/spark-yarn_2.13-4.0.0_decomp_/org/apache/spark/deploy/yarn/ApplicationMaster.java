package org.apache.spark.deploy.yarn;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import org.apache.hadoop.yarn.api.records.URL;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.ApplicationAttemptNotFoundException;
import org.apache.hadoop.yarn.util.Records;
import org.apache.spark.SecurityManager;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.SparkException;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.deploy.SparkHadoopUtil.;
import org.apache.spark.deploy.yarn.config.package$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.MiscellaneousProcessDetails;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.scheduler.cluster.YarnSchedulerBackend$;
import org.apache.spark.util.CallerContext;
import org.apache.spark.util.ChildFirstURLClassLoader;
import org.apache.spark.util.MutableURLClassLoader;
import org.apache.spark.util.YarnContainerInfoHelper$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.concurrent.Promise;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011=da\u0002?~\u0001\u0005\r\u0011q\u0002\u0005\u000b\u0003S\u0001!\u0011!Q\u0001\n\u00055\u0002BCA\u001b\u0001\t\u0005\t\u0015!\u0003\u00028!Q\u0011q\b\u0001\u0003\u0002\u0003\u0006I!!\u0011\t\u000f\u0005M\u0003\u0001\"\u0001\u0002V!9\u0011q\f\u0001\u0005\n\u0005\u0005\u0004\"CA@\u0001\t\u0007I\u0011BAA\u0011!\t\u0019\n\u0001Q\u0001\n\u0005\r\u0005\"CAK\u0001\t\u0007I\u0011BAL\u0011!\ty\n\u0001Q\u0001\n\u0005e\u0005BCAQ\u0001!\u0015\r\u0011\"\u0003\u0002$\"I\u00111\u0016\u0001A\u0002\u0013%\u0011Q\u0016\u0005\n\u0003\u0003\u0004\u0001\u0019!C\u0005\u0003\u0007D\u0001\"a4\u0001A\u0003&\u0011q\u0016\u0005\n\u0003#\u0004!\u0019!C\u0005\u0003'D\u0001\"!9\u0001A\u0003%\u0011Q\u001b\u0005\n\u0003G\u0004!\u0019!C\u0005\u0003KD\u0001\"!<\u0001A\u0003%\u0011q\u001d\u0005\n\u0003_\u0004!\u0019!C\u0005\u0003cD\u0001\"!?\u0001A\u0003%\u00111\u001f\u0005\n\u0003w\u0004\u0001\u0019!C\u0005\u0003cD\u0011\"!@\u0001\u0001\u0004%I!a@\t\u0011\t\r\u0001\u0001)Q\u0005\u0003gD\u0011B!\u0004\u0001\u0001\u0004%I!a&\t\u0013\t=\u0001\u00011A\u0005\n\tE\u0001\u0002\u0003B\u000b\u0001\u0001\u0006K!!'\t\u0013\te\u0001\u00011A\u0005\n\u0005]\u0005\"\u0003B\u000e\u0001\u0001\u0007I\u0011\u0002B\u000f\u0011!\u0011\t\u0003\u0001Q!\n\u0005e\u0005\"\u0003B\u0013\u0001\u0001\u0007I\u0011\u0002B\u0014\u0011%\u0011y\u0003\u0001a\u0001\n\u0013\u0011\t\u0004\u0003\u0005\u00036\u0001\u0001\u000b\u0015\u0002B\u0015\u0011%\u0011I\u0004\u0001a\u0001\n\u0013\u0011Y\u0004C\u0005\u0003>\u0001\u0001\r\u0011\"\u0003\u0003@!A!1\t\u0001!B\u0013\tI\bC\u0006\u0003H\u0001\u0001\r\u00111A\u0005\n\t%\u0003b\u0003B.\u0001\u0001\u0007\t\u0019!C\u0005\u0005;B1B!\u0019\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0003L!Y!Q\r\u0001A\u0002\u0003\u0007I\u0011\u0002B%\u0011-\u00119\u0007\u0001a\u0001\u0002\u0004%IA!\u001b\t\u0017\t5\u0004\u00011A\u0001B\u0003&!1\n\u0005\f\u0005c\u0002\u0001\u0019!a\u0001\n\u0013\u0011\u0019\bC\u0006\u0003|\u0001\u0001\r\u00111A\u0005\n\tu\u0004b\u0003BA\u0001\u0001\u0007\t\u0011)Q\u0005\u0005kB\u0011B!\"\u0001\u0001\u0004%I!a&\t\u0013\t\u001d\u0005\u00011A\u0005\n\t%\u0005\u0002\u0003BG\u0001\u0001\u0006K!!'\t\u0013\tE\u0005A1A\u0005\n\tM\u0005\u0002\u0003BN\u0001\u0001\u0006IA!&\t\u0013\tu\u0005A1A\u0005\n\t}\u0005\u0002\u0003BT\u0001\u0001\u0006IA!)\t\u0013\t%\u0006A1A\u0005\n\t}\u0005\u0002\u0003BV\u0001\u0001\u0006IA!)\t\u0013\t5\u0006\u00011A\u0005\n\t}\u0005\"\u0003BX\u0001\u0001\u0007I\u0011\u0002BY\u0011!\u0011)\f\u0001Q!\n\t\u0005\u0006\"\u0003B\\\u0001\t\u0007I\u0011\u0002B]\u0011!\u0011i\r\u0001Q\u0001\n\tm\u0006b\u0002Bh\u0001\u0011%!\u0011\u001b\u0005\b\u0005?\u0004AQ\u0001Bq\u0011\u001d\u0011\u0019\u000f\u0001C\u0001\u0005KDqa!\u0004\u0001\t\u0003\u0019y\u0001C\u0004\u0004\u0014\u0001!)a!\u0006\t\u000f\r]\u0001\u0001\"\u0002\u0004\u001a!I11\u0005\u0001\u0012\u0002\u0013\u00151Q\u0005\u0005\b\u0007w\u0001AQAB\u001f\u0011%\u0019I\u0005AI\u0001\n\u000b\u0019)\u0003C\u0004\u0004L\u0001!Ia!\u0014\t\u000f\rM\u0003\u0001\"\u0003\u0004V!91q\u000b\u0001\u0005\n\re\u0003bBB9\u0001\u0011%11\u000f\u0005\b\u0007\u0013\u0003A\u0011BB+\u0011\u001d\u0019Y\t\u0001C\u0005\u0007+Bqa!$\u0001\t\u0013\u0019)\u0006C\u0004\u0004\u0010\u0002!Ia!%\t\u000f\tu\u0007\u0001\"\u0003\u0004\u0014\"91Q\u0013\u0001\u0005\n\r]\u0005bBBK\u0001\u0011%1Q\u0014\u0005\b\u0007S\u0003A\u0011BBV\u0011\u001d\u00199\f\u0001C\u0005\u0007#Cqa!/\u0001\t\u0013\u0019)F\u0002\u0004\u0004<\u0002!1Q\u0018\u0005\u000b\u0007\u0007\u000b&Q1A\u0005B\r\u0015\u0007BCBd#\n\u0005\t\u0015!\u0003\u0003l\"Q1qV)\u0003\u0002\u0003\u0006Ia!\u001f\t\u000f\u0005M\u0013\u000b\"\u0001\u0004J\"I11[)A\u0002\u0013%\u0011q\u0013\u0005\n\u0007+\f\u0006\u0019!C\u0005\u0007/D\u0001ba7RA\u0003&\u0011\u0011\u0014\u0005\n\u0003w\f\u0006\u0019!C\u0005\u0003cD\u0011\"!@R\u0001\u0004%Iaa8\t\u0011\t\r\u0011\u000b)Q\u0005\u0003gD\u0011b!:R\u0005\u0004%I!a&\t\u0011\r\u001d\u0018\u000b)A\u0005\u00033Cqa!;R\t\u0003\u001a)\u0006C\u0004\u0004lF#\te!<\t\u000f\rm\u0018\u000b\"\u0011\u0004~\"9A\u0011B)\u0005B\u0011-qa\u0002C\f{\"\u0005A\u0011\u0004\u0004\u0007yvD\t\u0001b\u0007\t\u000f\u0005M3\r\"\u0001\u0005\u001e!IAqD2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\tC\u0019\u0007\u0015!\u0003\u0002t\"IA1E2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\tK\u0019\u0007\u0015!\u0003\u0002t\"IAqE2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\tS\u0019\u0007\u0015!\u0003\u0002t\"IA1F2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\t[\u0019\u0007\u0015!\u0003\u0002t\"IAqF2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\tc\u0019\u0007\u0015!\u0003\u0002t\"IA1G2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\tk\u0019\u0007\u0015!\u0003\u0002t\"IAqG2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\ts\u0019\u0007\u0015!\u0003\u0002t\"IA1H2C\u0002\u0013%\u0011\u0011\u001f\u0005\t\t{\u0019\u0007\u0015!\u0003\u0002t\"YAqH2A\u0002\u0003\u0007I\u0011\u0002C!\u0011-!\u0019e\u0019a\u0001\u0002\u0004%I\u0001\"\u0012\t\u0017\u0011%3\r1A\u0001B\u0003&\u0011q\u000b\u0005\b\t\u0017\u001aG\u0011\u0001C'\u0011%\u0019Ye\u0019C\u0001\u0003\u0007!9\u0006C\u0005\u0005\\\r$\t!a\u0001\u0005^!IAqL2\u0005\u0002\u0005\rA\u0011\r\u0002\u0012\u0003B\u0004H.[2bi&|g.T1ti\u0016\u0014(B\u0001@\u0000\u0003\u0011I\u0018M\u001d8\u000b\t\u0005\u0005\u00111A\u0001\u0007I\u0016\u0004Hn\\=\u000b\t\u0005\u0015\u0011qA\u0001\u0006gB\f'o\u001b\u0006\u0005\u0003\u0013\tY!\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0003\u0003\u001b\t1a\u001c:h'\u0015\u0001\u0011\u0011CA\u000f!\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!BAA\f\u0003\u0015\u00198-\u00197b\u0013\u0011\tY\"!\u0006\u0003\r\u0005s\u0017PU3g!\u0011\ty\"!\n\u000e\u0005\u0005\u0005\"\u0002BA\u0012\u0003\u0007\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\u0003O\t\tCA\u0004M_\u001e<\u0017N\\4\u0002\t\u0005\u0014xm]\u0002\u0001!\u0011\ty#!\r\u000e\u0003uL1!a\r~\u0005i\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8NCN$XM]!sOVlWM\u001c;t\u0003%\u0019\b/\u0019:l\u0007>tg\r\u0005\u0003\u0002:\u0005mRBAA\u0002\u0013\u0011\ti$a\u0001\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001C=be:\u001cuN\u001c4\u0011\t\u0005\r\u0013qJ\u0007\u0003\u0003\u000bRA!a\u0012\u0002J\u0005!1m\u001c8g\u0015\rq\u00181\n\u0006\u0005\u0003\u001b\n9!\u0001\u0004iC\u0012|w\u000e]\u0005\u0005\u0003#\n)EA\tZCJt7i\u001c8gS\u001e,(/\u0019;j_:\fa\u0001P5oSRtD\u0003CA,\u00033\nY&!\u0018\u0011\u0007\u0005=\u0002\u0001C\u0004\u0002*\u0011\u0001\r!!\f\t\u000f\u0005UB\u00011\u0001\u00028!9\u0011q\b\u0003A\u0002\u0005\u0005\u0013AD3yiJ\f7\r\u001e'pOV\u0013Hn]\u000b\u0003\u0003G\u0002\u0002\"!\u001a\u0002t\u0005e\u0014\u0011\u0010\b\u0005\u0003O\ny\u0007\u0005\u0003\u0002j\u0005UQBAA6\u0015\u0011\ti'a\u000b\u0002\rq\u0012xn\u001c;?\u0013\u0011\t\t(!\u0006\u0002\rA\u0013X\rZ3g\u0013\u0011\t)(a\u001e\u0003\u00075\u000b\u0007O\u0003\u0003\u0002r\u0005U\u0001\u0003BA3\u0003wJA!! \u0002x\t11\u000b\u001e:j]\u001e\fA\"\u00199q\u0003R$X-\u001c9u\u0013\u0012,\"!a!\u0011\t\u0005\u0015\u0015qR\u0007\u0003\u0003\u000fSA!!#\u0002\f\u00069!/Z2pe\u0012\u001c(\u0002BAG\u0003\u0013\n1!\u00199j\u0013\u0011\t\t*a\"\u0003)\u0005\u0003\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f^%e\u00035\t\u0007\u000f]!ui\u0016l\u0007\u000f^%eA\u0005i\u0011n]\"mkN$XM]'pI\u0016,\"!!'\u0011\t\u0005M\u00111T\u0005\u0005\u0003;\u000b)BA\u0004C_>dW-\u00198\u0002\u001d%\u001c8\t\\;ti\u0016\u0014Xj\u001c3fA\u0005Y1/Z2ve&$\u00180T4s+\t\t)\u000b\u0005\u0003\u0002:\u0005\u001d\u0016\u0002BAU\u0003\u0007\u0011qbU3dkJLG/_'b]\u0006<WM]\u0001\u000e[\u0016$(/[2t'f\u001cH/Z7\u0016\u0005\u0005=\u0006CBA\n\u0003c\u000b),\u0003\u0003\u00024\u0006U!AB(qi&|g\u000e\u0005\u0003\u00028\u0006uVBAA]\u0015\u0011\tY,a\u0001\u0002\u000f5,GO]5dg&!\u0011qXA]\u00055iU\r\u001e:jGN\u001c\u0016p\u001d;f[\u0006\tR.\u001a;sS\u000e\u001c8+_:uK6|F%Z9\u0015\t\u0005\u0015\u00171\u001a\t\u0005\u0003'\t9-\u0003\u0003\u0002J\u0006U!\u0001B+oSRD\u0011\"!4\r\u0003\u0003\u0005\r!a,\u0002\u0007a$\u0013'\u0001\bnKR\u0014\u0018nY:TsN$X-\u001c\u0011\u0002\u001fU\u001cXM]\"mCN\u001cHj\\1eKJ,\"!!6\u0011\t\u0005]\u0017Q\\\u0007\u0003\u00033TA!a7\u0002\u0004\u0005!Q\u000f^5m\u0013\u0011\ty.!7\u0003+5+H/\u00192mKV\u0013Fj\u00117bgNdu.\u00193fe\u0006\u0001Ro]3s\u00072\f7o\u001d'pC\u0012,'\u000fI\u0001\u0007G2LWM\u001c;\u0016\u0005\u0005\u001d\b\u0003BA\u0018\u0003SL1!a;~\u00051I\u0016M\u001d8S\u001b\u000ec\u0017.\u001a8u\u0003\u001d\u0019G.[3oi\u0002\na#\\1y\u001dVlW\t_3dkR|'OR1jYV\u0014Xm]\u000b\u0003\u0003g\u0004B!a\u0005\u0002v&!\u0011q_A\u000b\u0005\rIe\u000e^\u0001\u0018[\u0006Dh*^7Fq\u0016\u001cW\u000f^8s\r\u0006LG.\u001e:fg\u0002\n\u0001\"\u001a=ji\u000e{G-Z\u0001\rKbLGoQ8eK~#S-\u001d\u000b\u0005\u0003\u000b\u0014\t\u0001C\u0005\u0002NV\t\t\u00111\u0001\u0002t\u0006IQ\r_5u\u0007>$W\r\t\u0015\u0004-\t\u001d\u0001\u0003BA\n\u0005\u0013IAAa\u0003\u0002\u0016\tAao\u001c7bi&dW-\u0001\u0007v]J,w-[:uKJ,G-\u0001\tv]J,w-[:uKJ,Gm\u0018\u0013fcR!\u0011Q\u0019B\n\u0011%\ti\rGA\u0001\u0002\u0004\tI*A\u0007v]J,w-[:uKJ,G\r\t\u0015\u00043\t\u001d\u0011\u0001\u00034j]&\u001c\b.\u001a3\u0002\u0019\u0019Lg.[:iK\u0012|F%Z9\u0015\t\u0005\u0015'q\u0004\u0005\n\u0003\u001b\\\u0012\u0011!a\u0001\u00033\u000b\u0011BZ5oSNDW\r\u001a\u0011)\u0007q\u00119!A\u0006gS:\fGn\u0015;biV\u001cXC\u0001B\u0015!\u0011\t)Ia\u000b\n\t\t5\u0012q\u0011\u0002\u0017\r&t\u0017\r\\!qa2L7-\u0019;j_:\u001cF/\u0019;vg\u0006ya-\u001b8bYN#\u0018\r^;t?\u0012*\u0017\u000f\u0006\u0003\u0002F\nM\u0002\"CAg=\u0005\u0005\t\u0019\u0001B\u0015\u000311\u0017N\\1m'R\fG/^:!Q\ry\"qA\u0001\tM&t\u0017\r\\'tOV\u0011\u0011\u0011P\u0001\rM&t\u0017\r\\'tO~#S-\u001d\u000b\u0005\u0003\u000b\u0014\t\u0005C\u0005\u0002N\u0006\n\t\u00111\u0001\u0002z\u0005Ia-\u001b8bY6\u001bx\r\t\u0015\u0004E\t\u001d\u0011aD;tKJ\u001cE.Y:t)\"\u0014X-\u00193\u0016\u0005\t-\u0003\u0003\u0002B'\u0005/j!Aa\u0014\u000b\t\tE#1K\u0001\u0005Y\u0006twM\u0003\u0002\u0003V\u0005!!.\u0019<b\u0013\u0011\u0011IFa\u0014\u0003\rQC'/Z1e\u0003M)8/\u001a:DY\u0006\u001c8\u000f\u00165sK\u0006$w\fJ3r)\u0011\t)Ma\u0018\t\u0013\u00055G%!AA\u0002\t-\u0013\u0001E;tKJ\u001cE.Y:t)\"\u0014X-\u00193!Q\r)#qA\u0001\u000fe\u0016\u0004xN\u001d;feRC'/Z1e\u0003I\u0011X\r]8si\u0016\u0014H\u000b\u001b:fC\u0012|F%Z9\u0015\t\u0005\u0015'1\u000e\u0005\n\u0003\u001b<\u0013\u0011!a\u0001\u0005\u0017\nqB]3q_J$XM\u001d+ie\u0016\fG\r\t\u0015\u0004Q\t\u001d\u0011!C1mY>\u001c\u0017\r^8s+\t\u0011)\b\u0005\u0003\u00020\t]\u0014b\u0001B={\ni\u0011,\u0019:o\u00032dwnY1u_J\fQ\"\u00197m_\u000e\fGo\u001c:`I\u0015\fH\u0003BAc\u0005\u007fB\u0011\"!4+\u0003\u0003\u0005\rA!\u001e\u0002\u0015\u0005dGn\\2bi>\u0014\b\u0005K\u0002,\u0005\u000f\t!B]3hSN$XM]3e\u00039\u0011XmZ5ti\u0016\u0014X\rZ0%KF$B!!2\u0003\f\"I\u0011QZ\u0017\u0002\u0002\u0003\u0007\u0011\u0011T\u0001\fe\u0016<\u0017n\u001d;fe\u0016$\u0007\u0005K\u0002/\u0005\u000f\tQ\"\u00197m_\u000e\fGo\u001c:M_\u000e\\WC\u0001BK!\u0011\u0011iEa&\n\t\te%q\n\u0002\u0007\u001f\nTWm\u0019;\u0002\u001d\u0005dGn\\2bi>\u0014Hj\\2lA\u0005\t\u0002.Z1si\n,\u0017\r^%oi\u0016\u0014h/\u00197\u0016\u0005\t\u0005\u0006\u0003BA\n\u0005GKAA!*\u0002\u0016\t!Aj\u001c8h\u0003IAW-\u0019:uE\u0016\fG/\u00138uKJ4\u0018\r\u001c\u0011\u00023%t\u0017\u000e^5bY\u0006cGn\\2bi&|g.\u00138uKJ4\u0018\r\\\u0001\u001bS:LG/[1m\u00032dwnY1uS>t\u0017J\u001c;feZ\fG\u000eI\u0001\u0017]\u0016DH/\u00117m_\u000e\fG/[8o\u0013:$XM\u001d<bY\u0006Qb.\u001a=u\u00032dwnY1uS>t\u0017J\u001c;feZ\fGn\u0018\u0013fcR!\u0011Q\u0019BZ\u0011%\tiMNA\u0001\u0002\u0004\u0011\t+A\foKb$\u0018\t\u001c7pG\u0006$\u0018n\u001c8J]R,'O^1mA\u0005\u00192\u000f]1sW\u000e{g\u000e^3yiB\u0013x.\\5tKV\u0011!1\u0018\t\u0007\u0005{\u0013\u0019Ma2\u000e\u0005\t}&\u0002\u0002Ba\u0003+\t!bY8oGV\u0014(/\u001a8u\u0013\u0011\u0011)Ma0\u0003\u000fA\u0013x.\\5tKB!\u0011\u0011\bBe\u0013\u0011\u0011Y-a\u0001\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002)M\u0004\u0018M]6D_:$X\r\u001f;Qe>l\u0017n]3!\u0003U\u0001(/\u001a9be\u0016dunY1m%\u0016\u001cx.\u001e:dKN$BAa5\u0003\\BA\u0011QMA:\u0003s\u0012)\u000e\u0005\u0003\u0002\u0006\n]\u0017\u0002\u0002Bm\u0003\u000f\u0013Q\u0002T8dC2\u0014Vm]8ve\u000e,\u0007b\u0002Bou\u0001\u0007\u0011qG\u0001\u000eI&\u001cHoQ1dQ\u0016\u001cuN\u001c4\u0002\u0007I,h\u000e\u0006\u0002\u0002t\u0006a!/\u001e8V]6\fg.Y4fIRQ\u0011Q\u0019Bt\u0005o\u0014Ip!\u0003\t\u000f\t%H\b1\u0001\u0003l\u0006a1\r\\5f]R\u0014\u0006oY#omB!!Q\u001eBz\u001b\t\u0011yO\u0003\u0003\u0003r\u0006\r\u0011a\u0001:qG&!!Q\u001fBx\u0005\u0019\u0011\u0006oY#om\"9\u0011q\u0010\u001fA\u0002\u0005\r\u0005b\u0002B~y\u0001\u0007!Q`\u0001\u000bgR\fw-\u001b8h\t&\u0014\b\u0003\u0002B\u0000\u0007\u000bi!a!\u0001\u000b\t\r\r\u00111J\u0001\u0003MNLAaa\u0002\u0004\u0002\t!\u0001+\u0019;i\u0011\u001d\u0019Y\u0001\u0010a\u0001\u0003o\t1cY1dQ\u0016$'+Z:pkJ\u001cWm]\"p]\u001a\fQb\u001d;paVsW.\u00198bO\u0016$G\u0003BAc\u0007#AqAa?>\u0001\u0004\u0011i0A\u000bhKR$UMZ1vYR4\u0015N\\1m'R\fG/^:\u0015\u0005\t%\u0012AC;oe\u0016<\u0017n\u001d;feR1\u0011QYB\u000e\u0007?Aqa!\b@\u0001\u0004\u0011I#\u0001\u0004ti\u0006$Xo\u001d\u0005\n\u0007Cy\u0004\u0013!a\u0001\u0003s\n1\u0002Z5bO:|7\u000f^5dg\u0006!RO\u001c:fO&\u001cH/\u001a:%I\u00164\u0017-\u001e7uII*\"aa\n+\t\u0005e4\u0011F\u0016\u0003\u0007W\u0001Ba!\f\u000485\u00111q\u0006\u0006\u0005\u0007c\u0019\u0019$A\u0005v]\u000eDWmY6fI*!1QGA\u000b\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007s\u0019yCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\faAZ5oSNDG\u0003CAc\u0007\u007f\u0019\te!\u0012\t\u000f\ru\u0011\t1\u0001\u0003*!911I!A\u0002\u0005M\u0018\u0001B2pI\u0016D\u0011ba\u0012B!\u0003\u0005\r!!\u001f\u0002\u00075\u001cx-\u0001\tgS:L7\u000f\u001b\u0013eK\u001a\fW\u000f\u001c;%g\u000592\u000f]1sW\u000e{g\u000e^3yi&s\u0017\u000e^5bY&TX\r\u001a\u000b\u0005\u0003\u000b\u001cy\u0005C\u0004\u0004R\r\u0003\rAa2\u0002\u0005M\u001c\u0017\u0001\u0004:fgVlW\r\u0012:jm\u0016\u0014HCAAc\u0003)\u0011XmZ5ti\u0016\u0014\u0018)\u0014\u000b\r\u0003\u000b\u001cYfa\u0018\u0004d\r\u001d4Q\u000e\u0005\b\u0007;*\u0005\u0019AA=\u0003\u0011Awn\u001d;\t\u000f\r\u0005T\t1\u0001\u0002t\u0006!\u0001o\u001c:u\u0011\u001d\u0019)'\u0012a\u0001\u0003o\t!bX:qCJ\\7i\u001c8g\u0011\u001d\u0019I'\u0012a\u0001\u0007W\n\u0011\"^5BI\u0012\u0014Xm]:\u0011\r\u0005M\u0011\u0011WA=\u0011\u001d\u0019y'\u0012a\u0001\u0003\u0007\u000b!\"\u00199q\u0003R$X-\u001c9u\u0003=\u0019'/Z1uK\u0006cGn\\2bi>\u0014H\u0003DAc\u0007k\u001ayh!!\u0004\u0006\u000e\u001d\u0005bBB<\r\u0002\u00071\u0011P\u0001\nIJLg/\u001a:SK\u001a\u0004BA!<\u0004|%!1Q\u0010Bx\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001aDqa!\u001aG\u0001\u0004\t9\u0004C\u0004\u0004\u0004\u001a\u0003\rAa;\u0002\rI\u00048-\u00128w\u0011\u001d\tyH\u0012a\u0001\u0003\u0007CqA!8G\u0001\u0004\t9$A\u0005sk:$%/\u001b<fe\u0006\u0019\"/\u001e8Fq\u0016\u001cW\u000f^8s\u0019\u0006,hn\u00195fe\u0006!\u0012\r\u001c7pG\u0006$\u0018n\u001c8UQJ,\u0017\rZ%na2\fA\u0003\\1v]\u000eD'+\u001a9peR,'\u000f\u00165sK\u0006$GC\u0001B&)\t\t9$A\tdY\u0016\fg.\u001e9Ti\u0006<\u0017N\\4ESJ$B!!2\u0004\u001a\"911\u0014'A\u0002\tu\u0018AD:uC\u001eLgn\u001a#jeB\u000bG\u000f\u001b\u000b\u0007\u0003\u000b\u001cyja*\t\u000f\r\rQ\n1\u0001\u0004\"B!!q`BR\u0013\u0011\u0019)k!\u0001\u0003\u0015\u0019KG.Z*zgR,W\u000eC\u0004\u0004\u001c6\u0003\rA!@\u0002\u001b\u0005$G-Q7Ja\u001aKG\u000e^3s)\u0019\t)m!,\u00044\"91q\u0016(A\u0002\rE\u0016A\u00023sSZ,'\u000f\u0005\u0004\u0002\u0014\u0005E6\u0011\u0010\u0005\b\u0007ks\u0005\u0019AA=\u0003%\u0001(o\u001c=z\u0005\u0006\u001cX-\u0001\u000bti\u0006\u0014H/V:fe\u0006\u0003\b\u000f\\5dCRLwN\\\u0001\u0017e\u0016\u001cX\r^!mY>\u001c\u0017\r^8s\u0013:$XM\u001d<bY\nQ\u0011)T#oIB|\u0017N\u001c;\u0014\u000fE\u000b\tba0\u0002\u001eA!!Q^Ba\u0013\u0011\u0019\u0019Ma<\u0003\u0017I\u00038-\u00128ea>Lg\u000e^\u000b\u0003\u0005W\fqA\u001d9d\u000b:4\b\u0005\u0006\u0004\u0004L\u000e=7\u0011\u001b\t\u0004\u0007\u001b\fV\"\u0001\u0001\t\u000f\r\rU\u000b1\u0001\u0003l\"91qV+A\u0002\re\u0014\u0001C:ikR$wn\u001e8\u0002\u0019MDW\u000f\u001e3po:|F%Z9\u0015\t\u0005\u00157\u0011\u001c\u0005\n\u0003\u001b<\u0016\u0011!a\u0001\u00033\u000b\u0011b\u001d5vi\u0012|wO\u001c\u0011)\u0007a\u00139\u0001\u0006\u0003\u0002F\u000e\u0005\b\"CAg5\u0006\u0005\t\u0019AAzQ\rY&qA\u0001\"G2LWM\u001c;N_\u0012,GK]3bi\u0012K7oY8o]\u0016\u001cG/Q:GC&dW\rZ\u0001#G2LWM\u001c;N_\u0012,GK]3bi\u0012K7oY8o]\u0016\u001cG/Q:GC&dW\r\u001a\u0011\u0002\u000f=t7\u000b^1si\u00069!/Z2fSZ,WCABx!!\t\u0019b!=\u0004v\u0006\u0015\u0017\u0002BBz\u0003+\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0005\u0003'\u001990\u0003\u0003\u0004z\u0006U!aA!os\u0006y!/Z2fSZ,\u0017I\u001c3SKBd\u0017\u0010\u0006\u0003\u0004p\u000e}\bb\u0002C\u0001A\u0002\u0007A1A\u0001\bG>tG/\u001a=u!\u0011\u0011i\u000f\"\u0002\n\t\u0011\u001d!q\u001e\u0002\u000f%B\u001c7)\u00197m\u0007>tG/\u001a=u\u00039yg\u000eR5tG>tg.Z2uK\u0012$B!!2\u0005\u000e!9AqB1A\u0002\u0011E\u0011!\u0004:f[>$X-\u00113ee\u0016\u001c8\u000f\u0005\u0003\u0003n\u0012M\u0011\u0002\u0002C\u000b\u0005_\u0014!B\u00159d\u0003\u0012$'/Z:t\u0003E\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8NCN$XM\u001d\t\u0004\u0003_\u00197#B2\u0002\u0012\u0005uAC\u0001C\r\u00031)\u0005,\u0013+`'V\u001b5)R*T\u00035)\u0005,\u0013+`'V\u001b5)R*TA\u00059R\tW%U?Vs5)Q+H\u0011R{V\tW\"F!RKuJT\u0001\u0019\u000bbKEkX+O\u0007\u0006+v\t\u0013+`\u000bb\u001bU\t\u0015+J\u001f:\u0003\u0013AG#Y\u0013R{V*\u0011-`\u000bb+5)\u0016+P%~3\u0015)\u0013'V%\u0016\u001b\u0016aG#Y\u0013R{V*\u0011-`\u000bb+5)\u0016+P%~3\u0015)\u0013'V%\u0016\u001b\u0006%A\u000bF1&#vLU#Q\u001fJ#VIU0G\u0003&cUKU#\u0002-\u0015C\u0016\nV0S\u000bB{%\u000bV#S?\u001a\u000b\u0015\nT+S\u000b\u0002\n!#\u0012-J)~\u001b6i\u0018(P)~Ke*\u0013+F\t\u0006\u0019R\tW%U?N\u001buLT(U?&s\u0015\nV#EA\u0005IR\tW%U?\u0016C6)\u0012)U\u0013>su,V*F%~\u001bE*Q*T\u0003i)\u0005,\u0013+`\u000bb\u001bU\t\u0015+J\u001f:{VkU#S?\u000ec\u0015iU*!\u0003))\u0005,\u0013+`\u000b\u0006\u0013F*W\u0001\f\u000bbKEkX#B%2K\u0006%A\tF1&#v\fR%T\u0007>se*R\"U\u000b\u0012\u000b!#\u0012-J)~#\u0015jU\"P\u001d:+5\tV#EA\u00051Q.Y:uKJ,\"!a\u0016\u0002\u00155\f7\u000f^3s?\u0012*\u0017\u000f\u0006\u0003\u0002F\u0012\u001d\u0003\"CAgm\u0006\u0005\t\u0019AA,\u0003\u001di\u0017m\u001d;fe\u0002\nA!\\1j]R!\u0011Q\u0019C(\u0011\u001d\tI\u0003\u001fa\u0001\t#\u0002b!a\u0005\u0005T\u0005e\u0014\u0002\u0002C+\u0003+\u0011Q!\u0011:sCf$B!!2\u0005Z!91\u0011K=A\u0002\t\u001d\u0017\u0001D4fi\u0006#H/Z7qi&#GCAAB\u0003]9W\r\u001e%jgR|'/_*feZ,'/\u00113ee\u0016\u001c8\u000f\u0006\u0006\u0002z\u0011\rDQ\rC4\tWBq!!\u000e|\u0001\u0004\t9\u0004C\u0004\u0002@m\u0004\r!!\u0011\t\u000f\u0011%4\u00101\u0001\u0002z\u0005)\u0011\r\u001d9JI\"9AQN>A\u0002\u0005e\u0014!C1ui\u0016l\u0007\u000f^%e\u0001"
)
public class ApplicationMaster implements Logging {
   private SecurityManager securityMgr;
   public final ApplicationMasterArguments org$apache$spark$deploy$yarn$ApplicationMaster$$args;
   public final SparkConf org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf;
   private final YarnConfiguration yarnConf;
   private final ApplicationAttemptId org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId;
   private final boolean org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode;
   private Option metricsSystem;
   private final MutableURLClassLoader org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader;
   private final YarnRMClient client;
   private final int maxNumExecutorFailures;
   private volatile int exitCode;
   private volatile boolean unregistered;
   private volatile boolean finished;
   private volatile FinalApplicationStatus finalStatus;
   private volatile String finalMsg;
   private volatile Thread userClassThread;
   private volatile Thread reporterThread;
   private volatile YarnAllocator org$apache$spark$deploy$yarn$ApplicationMaster$$allocator;
   private volatile boolean registered;
   private final Object allocatorLock;
   private final long heartbeatInterval;
   private final long initialAllocationInterval;
   private long nextAllocationInterval;
   private final Promise org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public static void main(final String[] args) {
      ApplicationMaster$.MODULE$.main(args);
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

   public scala.collection.immutable.Map org$apache$spark$deploy$yarn$ApplicationMaster$$extractLogUrls() {
      return (scala.collection.immutable.Map)YarnContainerInfoHelper$.MODULE$.getLogUrls(.MODULE$.newConfiguration(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf), scala.None..MODULE$).getOrElse(() -> (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$));
   }

   public ApplicationAttemptId org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId;
   }

   public boolean org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode;
   }

   private SecurityManager securityMgr$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.securityMgr = new SecurityManager(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$2(), org.apache.spark.SecurityManager..MODULE$.$lessinit$greater$default$3());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.securityMgr;
   }

   private SecurityManager securityMgr() {
      return !this.bitmap$0 ? this.securityMgr$lzycompute() : this.securityMgr;
   }

   private Option metricsSystem() {
      return this.metricsSystem;
   }

   private void metricsSystem_$eq(final Option x$1) {
      this.metricsSystem = x$1;
   }

   public MutableURLClassLoader org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader;
   }

   private YarnRMClient client() {
      return this.client;
   }

   private int maxNumExecutorFailures() {
      return this.maxNumExecutorFailures;
   }

   private int exitCode() {
      return this.exitCode;
   }

   private void exitCode_$eq(final int x$1) {
      this.exitCode = x$1;
   }

   private boolean unregistered() {
      return this.unregistered;
   }

   private void unregistered_$eq(final boolean x$1) {
      this.unregistered = x$1;
   }

   private boolean finished() {
      return this.finished;
   }

   private void finished_$eq(final boolean x$1) {
      this.finished = x$1;
   }

   private FinalApplicationStatus finalStatus() {
      return this.finalStatus;
   }

   private void finalStatus_$eq(final FinalApplicationStatus x$1) {
      this.finalStatus = x$1;
   }

   private String finalMsg() {
      return this.finalMsg;
   }

   private void finalMsg_$eq(final String x$1) {
      this.finalMsg = x$1;
   }

   private Thread userClassThread() {
      return this.userClassThread;
   }

   private void userClassThread_$eq(final Thread x$1) {
      this.userClassThread = x$1;
   }

   private Thread reporterThread() {
      return this.reporterThread;
   }

   private void reporterThread_$eq(final Thread x$1) {
      this.reporterThread = x$1;
   }

   public YarnAllocator org$apache$spark$deploy$yarn$ApplicationMaster$$allocator() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator;
   }

   private void allocator_$eq(final YarnAllocator x$1) {
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator = x$1;
   }

   private boolean registered() {
      return this.registered;
   }

   private void registered_$eq(final boolean x$1) {
      this.registered = x$1;
   }

   private Object allocatorLock() {
      return this.allocatorLock;
   }

   private long heartbeatInterval() {
      return this.heartbeatInterval;
   }

   private long initialAllocationInterval() {
      return this.initialAllocationInterval;
   }

   private long nextAllocationInterval() {
      return this.nextAllocationInterval;
   }

   private void nextAllocationInterval_$eq(final long x$1) {
      this.nextAllocationInterval = x$1;
   }

   public Promise org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise;
   }

   private scala.collection.immutable.Map prepareLocalResources(final SparkConf distCacheConf) {
      this.logInfo((Function0)(() -> "Preparing Local resources"));
      HashMap resources = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      Seq distFiles = (Seq)distCacheConf.get(package$.MODULE$.CACHED_FILES());
      Seq fileSizes = (Seq)distCacheConf.get(package$.MODULE$.CACHED_FILES_SIZES());
      Seq timeStamps = (Seq)distCacheConf.get(package$.MODULE$.CACHED_FILES_TIMESTAMPS());
      Seq visibilities = (Seq)distCacheConf.get(package$.MODULE$.CACHED_FILES_VISIBILITIES());
      Seq resTypes = (Seq)distCacheConf.get(package$.MODULE$.CACHED_FILES_TYPES());
      distFiles.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
         LocalResourceType resType = LocalResourceType.valueOf((String)resTypes.apply(i));
         setupDistributedCache$1((String)distFiles.apply(i), resType, timeStamps.apply(i).toString(), fileSizes.apply(i).toString(), (String)visibilities.apply(i), resources);
      });
      ((Option)distCacheConf.get(package$.MODULE$.CACHED_CONF_ARCHIVE())).foreach((path) -> {
         $anonfun$prepareLocalResources$4(this, resources, path);
         return BoxedUnit.UNIT;
      });
      return resources.toMap(scala..less.colon.less..MODULE$.refl());
   }

   public final int run() {
      try {
         Object var15;
         if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode()) {
            if (System.getProperty(org.apache.spark.internal.config.UI..MODULE$.UI_PORT().key()) == null) {
               System.setProperty(org.apache.spark.internal.config.UI..MODULE$.UI_PORT().key(), "0");
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            System.setProperty("spark.master", "yarn");
            System.setProperty(org.apache.spark.internal.config.package..MODULE$.SUBMIT_DEPLOY_MODE().key(), "cluster");
            System.setProperty("spark.yarn.app.id", this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId().getApplicationId().toString());
            var15 = scala.Option..MODULE$.apply(Integer.toString(this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId().getAttemptId()));
         } else {
            var15 = scala.None..MODULE$;
         }

         Option attemptID = (Option)var15;
         (new CallerContext("APPMASTER", (Option)this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(org.apache.spark.internal.config.package..MODULE$.APP_CALLER_CONTEXT()), scala.Option..MODULE$.apply(this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId().getApplicationId().toString()), attemptID, org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$5(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$6(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$7(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$8(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$9())).setCurrentContext();
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ApplicationAttemptId: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_ATTEMPT_ID..MODULE$, this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId())})))));
         Path stagingDirPath = new Path(System.getenv("SPARK_YARN_STAGING_DIR"));
         FileSystem stagingDirFs = stagingDirPath.getFileSystem(this.yarnConf);
         int priority = org.apache.spark.util.ShutdownHookManager..MODULE$.SPARK_CONTEXT_SHUTDOWN_PRIORITY() - 1;
         org.apache.spark.util.ShutdownHookManager..MODULE$.addShutdownHook(priority, (JFunction0.mcV.sp)() -> {
            try {
               int maxAppAttempts = this.client().getMaxRegAttempts(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, this.yarnConf);
               boolean isLastAttempt = this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId().getAttemptId() >= maxAppAttempts;
               if (!this.finished()) {
                  this.finish(this.finalStatus(), ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EARLY(), "Shutdown hook called before final status was reported.");
               }

               if (!this.unregistered()) {
                  if (isLastAttempt) {
                     this.cleanupStagingDir(stagingDirFs, stagingDirPath);
                     this.unregister(this.finalStatus(), this.finalMsg());
                  } else {
                     FinalApplicationStatus var10000 = this.finalStatus();
                     FinalApplicationStatus var5 = FinalApplicationStatus.SUCCEEDED;
                     if (var10000 == null) {
                        if (var5 != null) {
                           return;
                        }
                     } else if (!var10000.equals(var5)) {
                        return;
                     }

                     this.unregister(this.finalStatus(), this.finalMsg());
                     this.cleanupStagingDir(stagingDirFs, stagingDirPath);
                  }
               }
            } catch (Throwable var7) {
               this.logWarning((Function0)(() -> "Ignoring Exception while stopping ApplicationMaster from shutdown hook"), var7);
            }

         });
         if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode()) {
            this.runDriver();
         } else {
            this.runExecutorLauncher();
         }
      } catch (Exception var13) {
         this.logError((Function0)(() -> "Uncaught exception: "), var13);
         this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION(), "Uncaught exception: " + StringUtils.stringifyException(var13));
      } finally {
         try {
            this.metricsSystem().foreach((ms) -> {
               $anonfun$run$5(ms);
               return BoxedUnit.UNIT;
            });
         } catch (Exception var12) {
            this.logWarning((Function0)(() -> "Exception during stopping of the metric system: "), var12);
         }

      }

      return this.exitCode();
   }

   public void runUnmanaged(final RpcEnv clientRpcEnv, final ApplicationAttemptId appAttemptId, final Path stagingDir, final SparkConf cachedResourcesConf) {
      try {
         (new CallerContext("APPMASTER", (Option)this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(org.apache.spark.internal.config.package..MODULE$.APP_CALLER_CONTEXT()), scala.Option..MODULE$.apply(appAttemptId.getApplicationId().toString()), scala.None..MODULE$, org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$5(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$6(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$7(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$8(), org.apache.spark.util.CallerContext..MODULE$.$lessinit$greater$default$9())).setCurrentContext();
         RpcEndpointRef driverRef = clientRpcEnv.setupEndpointRef(org.apache.spark.rpc.RpcAddress..MODULE$.apply((String)this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_HOST_ADDRESS()), BoxesRunTime.unboxToInt(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT()))), YarnSchedulerBackend$.MODULE$.ENDPOINT_NAME());
         this.registerAM(org.apache.spark.util.Utils..MODULE$.localHostNameForURI(), -1, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.getOption("spark.driver.appUIAddress"), appAttemptId);
         String encodedAppId = URLEncoder.encode(appAttemptId.getApplicationId().toString(), "UTF-8");
         this.addAmIpFilter(new Some(driverRef), "/proxy/" + encodedAppId);
         this.createAllocator(driverRef, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, clientRpcEnv, appAttemptId, cachedResourcesConf);
         this.reporterThread().join();
      } catch (Exception var15) {
         this.logError((Function0)(() -> "Uncaught exception: "), var15);
         this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_UNCAUGHT_EXCEPTION(), "Uncaught exception: " + StringUtils.stringifyException(var15));
         if (!this.unregistered()) {
            this.cleanupStagingDir(stagingDir);
            this.unregister(this.finalStatus(), this.finalMsg());
         }
      } finally {
         try {
            this.metricsSystem().foreach((ms) -> {
               $anonfun$runUnmanaged$2(ms);
               return BoxedUnit.UNIT;
            });
         } catch (Exception var14) {
            this.logWarning((Function0)(() -> "Exception during stopping of the metric system: "), var14);
         }

      }

   }

   public void stopUnmanaged(final Path stagingDir) {
      if (!this.finished()) {
         this.finish(FinalApplicationStatus.SUCCEEDED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS(), this.finish$default$3());
      }

      if (!this.unregistered()) {
         this.cleanupStagingDir(stagingDir);
         this.unregister(this.finalStatus(), this.finalMsg());
      }
   }

   public final FinalApplicationStatus getDefaultFinalStatus() {
      return this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode() ? FinalApplicationStatus.FAILED : FinalApplicationStatus.UNDEFINED;
   }

   public final synchronized void unregister(final FinalApplicationStatus status, final String diagnostics) {
      if (this.registered() && !this.unregistered()) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unregistering ApplicationMaster with ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, status)}))).$plus((MessageWithContext)scala.Option..MODULE$.apply(diagnostics).map((msg) -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" (diag message: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, msg)})))).getOrElse(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$)))));
         this.unregistered_$eq(true);
         this.client().unregister(status, (String)scala.Option..MODULE$.apply(diagnostics).getOrElse(() -> ""));
      }
   }

   public final String unregister$default$2() {
      return null;
   }

   public final synchronized void finish(final FinalApplicationStatus status, final int code, final String msg) {
      if (!this.finished()) {
         boolean inShutdown = org.apache.spark.util.ShutdownHookManager..MODULE$.inShutdown();
         if (!this.registered() && this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode()) {
            this.finalStatus_$eq(FinalApplicationStatus.FAILED);
            this.exitCode_$eq(ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED());
         } else {
            this.exitCode_$eq(code);
            this.finalStatus_$eq(status);
         }

         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Final app status: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.APP_STATE..MODULE$, this.finalStatus())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exitCode: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(this.exitCode()))})))).$plus((MessageWithContext)scala.Option..MODULE$.apply(msg).map((msgx) -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{", (reason: ", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, msgx)})))).getOrElse(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{""})))).log(scala.collection.immutable.Nil..MODULE$)))));
         this.finalMsg_$eq(org.apache.commons.lang3.StringUtils.abbreviate(msg, (int)BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.AM_FINAL_MSG_LIMIT()))));
         this.finished_$eq(true);
         if (!inShutdown) {
            label39: {
               Thread var10000 = Thread.currentThread();
               Thread var5 = this.reporterThread();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label39;
                  }
               } else if (var10000.equals(var5)) {
                  break label39;
               }

               if (this.reporterThread() != null) {
                  this.logDebug((Function0)(() -> "shutting down reporter thread"));
                  this.reporterThread().interrupt();
               }
            }
         }

         if (!inShutdown) {
            Thread var7 = Thread.currentThread();
            Thread var6 = this.userClassThread();
            if (var7 == null) {
               if (var6 == null) {
                  return;
               }
            } else if (var7.equals(var6)) {
               return;
            }

            if (this.userClassThread() != null) {
               this.logDebug((Function0)(() -> "shutting down user thread"));
               this.userClassThread().interrupt();
               return;
            }
         }

      }
   }

   public final String finish$default$3() {
      return null;
   }

   public void org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextInitialized(final SparkContext sc) {
      synchronized(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise()){}

      try {
         this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().success(sc);
         this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().wait();
      } catch (Throwable var4) {
         throw var4;
      }

   }

   private void resumeDriver() {
      synchronized(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise()){}

      try {
         this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().notify();
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private void registerAM(final String host, final int port, final SparkConf _sparkConf, final Option uiAddress, final ApplicationAttemptId appAttempt) {
      String appId = appAttempt.getApplicationId().toString();
      String attemptId = Integer.toString(appAttempt.getAttemptId());
      String historyAddress = ApplicationMaster$.MODULE$.getHistoryServerAddress(_sparkConf, this.yarnConf, appId, attemptId);
      this.client().register(host, port, this.yarnConf, _sparkConf, uiAddress, historyAddress);
      this.registered_$eq(true);
   }

   private void createAllocator(final RpcEndpointRef driverRef, final SparkConf _sparkConf, final RpcEnv rpcEnv, final ApplicationAttemptId appAttemptId, final SparkConf distCacheConf) {
      if (!this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode()) {
         byte[] tokens = (byte[])driverRef.askSync(org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveDelegationTokens..MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Byte.TYPE)));
         if (tokens != null) {
            .MODULE$.get().addDelegationTokens(tokens, _sparkConf);
         }
      }

      String appId = appAttemptId.getApplicationId().toString();
      String driverUrl = org.apache.spark.rpc.RpcEndpointAddress..MODULE$.apply(driverRef.address().host(), driverRef.address().port(), org.apache.spark.scheduler.cluster.CoarseGrainedSchedulerBackend..MODULE$.ENDPOINT_NAME()).toString();
      scala.collection.immutable.Map localResources = this.prepareLocalResources(distCacheConf);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> {
         int executorMemory = (int)BoxesRunTime.unboxToLong(_sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_MEMORY()));
         int executorCores = BoxesRunTime.unboxToInt(_sparkConf.get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_CORES()));
         ExecutorRunnable dummyRunner = new ExecutorRunnable(scala.None..MODULE$, this.yarnConf, _sparkConf, driverUrl, "<executorId>", "<hostname>", executorMemory, executorCores, appId, this.securityMgr(), localResources, org.apache.spark.resource.ResourceProfile..MODULE$.DEFAULT_RESOURCE_PROFILE_ID());
         return dummyRunner.launchContextDebugInfo();
      }));
      this.allocator_$eq(this.client().createAllocator(this.yarnConf, _sparkConf, appAttemptId, driverUrl, driverRef, this.securityMgr(), localResources));
      rpcEnv.setupEndpoint("YarnAM", new AMEndpoint(rpcEnv, driverRef));
      if (BoxesRunTime.unboxToBoolean(_sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initializing service data for shuffle service using name '"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "'"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_SERVICE_NAME..MODULE$, _sparkConf.get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_SERVICE_NAME()))}))))));
      }

      this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().allocateResources();
      MetricsSystem ms = org.apache.spark.metrics.MetricsSystem..MODULE$.createMetricsSystem(org.apache.spark.metrics.MetricsSystemInstances..MODULE$.APPLICATION_MASTER(), this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf);
      String prefix = (String)((Option)_sparkConf.get(package$.MODULE$.YARN_METRICS_NAMESPACE())).getOrElse(() -> appId);
      ms.registerSource(new ApplicationMasterSource(prefix, this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator()));
      ms.start(false);
      this.metricsSystem_$eq(new Some(ms));
      this.reporterThread_$eq(this.launchReporterThread());
   }

   private void runDriver() {
      this.addAmIpFilter(scala.None..MODULE$, System.getenv("APPLICATION_WEB_PROXY_BASE"));
      this.userClassThread_$eq(this.startUserApplication());
      this.logInfo((Function0)(() -> "Waiting for spark context initialization..."));
      long totalWaitTime = BoxesRunTime.unboxToLong(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.AM_MAX_WAIT_TIME()));

      try {
         SparkContext sc = (SparkContext)org.apache.spark.util.ThreadUtils..MODULE$.awaitResult(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().future(), scala.concurrent.duration.Duration..MODULE$.apply(totalWaitTime, TimeUnit.MILLISECONDS));
         if (sc == null) {
            throw new IllegalStateException("User did not initialize spark context!");
         }

         RpcEnv rpcEnv = sc.env().rpcEnv();
         SparkConf userConf = sc.getConf();
         String host = (String)userConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_HOST_ADDRESS());
         int port = BoxesRunTime.unboxToInt(userConf.get(org.apache.spark.internal.config.package..MODULE$.DRIVER_PORT()));
         this.registerAM(host, port, userConf, sc.ui().map((x$4) -> x$4.webUrl()), this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId());
         RpcEndpointRef driverRef = rpcEnv.setupEndpointRef(org.apache.spark.rpc.RpcAddress..MODULE$.apply(host, port), YarnSchedulerBackend$.MODULE$.ENDPOINT_NAME());
         this.createAllocator(driverRef, userConf, rpcEnv, this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId(), this.distCacheConf());
         this.resumeDriver();
         this.userClassThread().join();
      } catch (Throwable var16) {
         if (var16 instanceof SparkException var12) {
            if (var12.getCause() instanceof TimeoutException) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"SparkContext did not initialize after waiting for "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " ms. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TIMEOUT..MODULE$, BoxesRunTime.boxToLong(totalWaitTime))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Please check earlier log output for errors. Failing the application."})))).log(scala.collection.immutable.Nil..MODULE$))));
               this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SC_NOT_INITED(), "Timed out waiting for SparkContext.");
               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }
         }

         throw var16;
      } finally {
         this.resumeDriver();
      }

   }

   private void runExecutorLauncher() {
      String hostname = org.apache.spark.util.Utils..MODULE$.localHostNameForURI();
      int amCores = BoxesRunTime.unboxToInt(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.AM_CORES()));
      RpcEnv rpcEnv = org.apache.spark.rpc.RpcEnv..MODULE$.create("sparkYarnAM", hostname, hostname, -1, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, this.securityMgr(), amCores, true);
      this.registerAM(hostname, -1, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, (Option)this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.DRIVER_APP_UI_ADDRESS()), this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId());
      Tuple2 var6 = org.apache.spark.util.Utils..MODULE$.parseHostPort((String)this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.userArgs().apply(0));
      if (var6 != null) {
         String driverHost = (String)var6._1();
         int driverPort = var6._2$mcI$sp();
         Tuple2 var5 = new Tuple2(driverHost, BoxesRunTime.boxToInteger(driverPort));
         String driverHost = (String)var5._1();
         int driverPort = var5._2$mcI$sp();
         RpcEndpointRef driverRef = rpcEnv.setupEndpointRef(org.apache.spark.rpc.RpcAddress..MODULE$.apply(driverHost, driverPort), YarnSchedulerBackend$.MODULE$.ENDPOINT_NAME());
         this.addAmIpFilter(new Some(driverRef), System.getenv("APPLICATION_WEB_PROXY_BASE"));
         this.createAllocator(driverRef, this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf, rpcEnv, this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId(), this.distCacheConf());
         this.reporterThread().join();
      } else {
         throw new MatchError(var6);
      }
   }

   public void org$apache$spark$deploy$yarn$ApplicationMaster$$allocationThreadImpl() {
      int reporterMaxFailures = BoxesRunTime.unboxToInt(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.MAX_REPORTER_THREAD_FAILURES()));
      IntRef failureCount = IntRef.create(0);

      while(!this.finished()) {
         try {
            if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().getNumExecutorsFailed() >= this.maxNumExecutorFailures()) {
               this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES(), "Max number of executor failures (" + this.maxNumExecutorFailures() + ") reached");
            } else if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().isAllNodeExcluded()) {
               this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_MAX_EXECUTOR_FAILURES(), "Due to executor failures all available nodes are excluded");
            } else {
               this.logDebug((Function0)(() -> "Sending progress"));
               this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().allocateResources();
            }

            failureCount.elem = 0;
         } catch (InterruptedException var23) {
         } catch (ApplicationAttemptNotFoundException var24) {
            ++failureCount.elem;
            this.logError((Function0)(() -> "Exception from Reporter thread."), var24);
            this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE(), var24.getMessage());
         } catch (Throwable var25) {
            ++failureCount.elem;
            if (!scala.util.control.NonFatal..MODULE$.apply(var25)) {
               this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE(), "Fatal exception: " + StringUtils.stringifyException(var25));
            } else if (failureCount.elem >= reporterMaxFailures) {
               this.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_REPORTER_FAILURE(), "Exception was thrown " + failureCount.elem + " time(s) from Reporter thread.");
            } else {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reporter thread fails ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FAILURES..MODULE$, BoxesRunTime.boxToInteger(failureCount.elem))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"time(s) in a row."})))).log(scala.collection.immutable.Nil..MODULE$))), var25);
            }
         }

         try {
            int numPendingAllocate = this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().getNumContainersPendingAllocate();
            long sleepStartNs = 0L;
            LongRef sleepInterval = LongRef.create(200L);
            synchronized(this.allocatorLock()){}

            try {
               long var10001;
               if (numPendingAllocate <= 0 && this.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().getNumPendingLossReasonRequests() <= 0) {
                  this.nextAllocationInterval_$eq(this.initialAllocationInterval());
                  var10001 = this.heartbeatInterval();
               } else {
                  long currentAllocationInterval = scala.math.package..MODULE$.min(this.heartbeatInterval(), this.nextAllocationInterval());
                  this.nextAllocationInterval_$eq(currentAllocationInterval * 2L);
                  var10001 = currentAllocationInterval;
               }

               sleepInterval.elem = var10001;
               sleepStartNs = System.nanoTime();
               this.allocatorLock().wait(sleepInterval.elem);
            } catch (Throwable var26) {
               throw var26;
            }

            long sleepDuration = System.nanoTime() - sleepStartNs;
            if (sleepDuration < TimeUnit.MILLISECONDS.toNanos(sleepInterval.elem)) {
               this.logDebug((Function0)(() -> "Number of pending allocations is " + numPendingAllocate + ". Slept for " + sleepDuration + "/" + sleepInterval.elem + " ms."));
               long toSleep = scala.math.package..MODULE$.max(0L, this.initialAllocationInterval() - sleepDuration);
               if (toSleep > 0L) {
                  this.logDebug((Function0)(() -> "Going back to sleep for " + toSleep + " ms"));
                  Thread.sleep(toSleep);
               }
            } else {
               this.logDebug((Function0)(() -> "Number of pending allocations is " + numPendingAllocate + ". Slept for " + sleepDuration + "/" + sleepInterval.elem + "."));
            }
         } catch (InterruptedException var27) {
         }
      }

   }

   private Thread launchReporterThread() {
      Thread t = new Thread() {
         // $FF: synthetic field
         private final ApplicationMaster $outer;

         public void run() {
            try {
               this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$$allocationThreadImpl();
            } finally {
               this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().stop();
            }

         }

         public {
            if (ApplicationMaster.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMaster.this;
            }
         }
      };
      t.setDaemon(true);
      t.setName("Reporter");
      t.start();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Started progress reporter thread with "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(heartbeat: ", ", initial allocation: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HEARTBEAT_INTERVAL..MODULE$, BoxesRunTime.boxToLong(this.heartbeatInterval()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") intervals"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INITIAL_HEARTBEAT_INTERVAL..MODULE$, BoxesRunTime.boxToLong(this.initialAllocationInterval()))}))))));
      return t;
   }

   private SparkConf distCacheConf() {
      SparkConf distCacheConf = new SparkConf(false);
      if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.distCacheConf() != null) {
         org.apache.spark.util.Utils..MODULE$.getPropertiesFromFile(this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.distCacheConf()).foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return distCacheConf.set(k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
      }

      return distCacheConf;
   }

   private void cleanupStagingDir(final Path stagingDirPath) {
      FileSystem stagingDirFs = stagingDirPath.getFileSystem(this.yarnConf);
      this.cleanupStagingDir(stagingDirFs, stagingDirPath);
   }

   private void cleanupStagingDir(final FileSystem fs, final Path stagingDirPath) {
      try {
         boolean preserveFiles = BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.PRESERVE_STAGING_FILES()));
         if (!preserveFiles) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deleting staging directory ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, stagingDirPath)})))));
            fs.delete(stagingDirPath, true);
         }
      } catch (IOException var5) {
         this.logError((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to cleanup staging dir ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, stagingDirPath)})))), var5);
      }

   }

   private void addAmIpFilter(final Option driver, final String proxyBase) {
      String amFilter = AmIpFilter.class.getName();
      scala.collection.immutable.Map params = this.client().getAmIpFilterParams(this.yarnConf, proxyBase);
      if (driver instanceof Some var7) {
         RpcEndpointRef d = (RpcEndpointRef)var7.value();
         d.send(new CoarseGrainedClusterMessages.AddWebUIFilter(amFilter, params, proxyBase));
         BoxedUnit var9 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(driver)) {
         System.setProperty(org.apache.spark.internal.config.UI..MODULE$.UI_FILTERS().key(), amFilter);
         params.foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return System.setProperty("spark." + amFilter + ".param." + k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(driver);
      }
   }

   private Thread startUserApplication() {
      this.logInfo((Function0)(() -> "Starting the user application in a separate Thread"));
      ObjectRef userArgs = ObjectRef.create(this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.userArgs());
      if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryPyFile() != null && this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryPyFile().endsWith(".py")) {
         userArgs.elem = (Seq)(new scala.collection.immutable..colon.colon(this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryPyFile(), new scala.collection.immutable..colon.colon("", scala.collection.immutable.Nil..MODULE$))).$plus$plus((Seq)userArgs.elem);
      }

      if (this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryRFile() != null && !this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryRFile().endsWith(".R") && this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.primaryRFile().endsWith(".r")) {
      }

      Method mainMethod = this.org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader().loadClass(this.org$apache$spark$deploy$yarn$ApplicationMaster$$args.userClass()).getMethod("main", String[].class);
      Thread userThread = new Thread(mainMethod, userArgs) {
         // $FF: synthetic field
         private final ApplicationMaster $outer;
         private final Method mainMethod$1;
         private final ObjectRef userArgs$1;

         public void run() {
            try {
               if (!Modifier.isStatic(this.mainMethod$1.getModifiers())) {
                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Could not find static main method in object "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$$args.userClass())}))))));
                  this.$outer.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS(), this.$outer.finish$default$3());
               } else {
                  this.mainMethod$1.invoke((Object)null, ((Seq)this.userArgs$1.elem).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
                  this.$outer.finish(FinalApplicationStatus.SUCCEEDED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS(), this.$outer.finish$default$3());
                  this.$outer.logDebug((Function0)(() -> "Done running user class"));
               }
            } catch (InvocationTargetException var11) {
               Throwable var3 = var11.getCause();
               if (var3 instanceof InterruptedException) {
                  BoxedUnit var14 = BoxedUnit.UNIT;
               } else if (var3 instanceof SparkUserAppException) {
                  SparkUserAppException var4 = (SparkUserAppException)var3;
                  int exitCode = var4.exitCode();
                  MessageWithContext msg = this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"User application exited with status "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(exitCode))}))));
                  this.$outer.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg));
                  this.$outer.finish(FinalApplicationStatus.FAILED, exitCode, msg.message());
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  if (var3 == null) {
                     throw new MatchError(var3);
                  }

                  this.$outer.logError((Function0)(() -> "User class threw exception: "), var3);
                  this.$outer.finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_EXCEPTION_USER_CLASS(), "User class threw exception: " + StringUtils.stringifyException(var3));
                  BoxedUnit var13 = BoxedUnit.UNIT;
               }

               this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().tryFailure(var11.getCause());
            } finally {
               this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise().trySuccess((Object)null);
            }

         }

         public {
            if (ApplicationMaster.this == null) {
               throw null;
            } else {
               this.$outer = ApplicationMaster.this;
               this.mainMethod$1 = mainMethod$1;
               this.userArgs$1 = userArgs$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      userThread.setContextClassLoader(this.org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader());
      userThread.setName("Driver");
      userThread.start();
      return userThread;
   }

   public void org$apache$spark$deploy$yarn$ApplicationMaster$$resetAllocatorInterval() {
      synchronized(this.allocatorLock()){}

      try {
         this.nextAllocationInterval_$eq(this.initialAllocationInterval());
         this.allocatorLock().notifyAll();
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private static final void setupDistributedCache$1(final String file, final LocalResourceType rtype, final String timestamp, final String size, final String vis, final HashMap resources$1) {
      URI uri = new URI(file);
      LocalResource amJarRsrc = (LocalResource)Records.newRecord(LocalResource.class);
      amJarRsrc.setType(rtype);
      amJarRsrc.setVisibility(LocalResourceVisibility.valueOf(vis));
      amJarRsrc.setResource(URL.fromURI(uri));
      amJarRsrc.setTimestamp(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(timestamp)));
      amJarRsrc.setSize(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(size)));
      String fileName = (String)scala.Option..MODULE$.apply(uri.getFragment()).getOrElse(() -> (new Path(uri)).getName());
      resources$1.update(fileName, amJarRsrc);
   }

   // $FF: synthetic method
   public static final void $anonfun$prepareLocalResources$4(final ApplicationMaster $this, final HashMap resources$1, final String path) {
      URI uri = new URI(path);
      FileSystem fs = FileSystem.get(uri, $this.yarnConf);
      FileStatus status = fs.getFileStatus(new Path(uri));
      URI destUri = new URI(uri.getScheme(), uri.getRawSchemeSpecificPart(), Client$.MODULE$.LOCALIZED_CONF_DIR());
      setupDistributedCache$1(destUri.toString(), LocalResourceType.ARCHIVE, Long.toString(status.getModificationTime()), Long.toString(status.getLen()), LocalResourceVisibility.PRIVATE.name(), resources$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$run$5(final MetricsSystem ms) {
      ms.report();
      ms.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$runUnmanaged$2(final MetricsSystem ms) {
      ms.report();
      ms.stop();
   }

   public ApplicationMaster(final ApplicationMasterArguments args, final SparkConf sparkConf, final YarnConfiguration yarnConf) {
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$args = args;
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf = sparkConf;
      this.yarnConf = yarnConf;
      Logging.$init$(this);
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$appAttemptId = System.getenv(Environment.CONTAINER_ID.name()) != null ? YarnSparkHadoopUtil$.MODULE$.getContainerId().getApplicationAttemptId() : null;
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode = args.userClass() != null;
      this.metricsSystem = scala.None..MODULE$;
      java.net.URL[] urls = Client$.MODULE$.getUserClasspathUrls(sparkConf, this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode());
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$userClassLoader = (MutableURLClassLoader)(this.org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode() ? (Client$.MODULE$.isUserClassPathFirst(sparkConf, true) ? new ChildFirstURLClassLoader(urls, org.apache.spark.util.Utils..MODULE$.getContextOrSparkClassLoader()) : new MutableURLClassLoader(urls, org.apache.spark.util.Utils..MODULE$.getContextOrSparkClassLoader())) : new MutableURLClassLoader(urls, org.apache.spark.util.Utils..MODULE$.getContextOrSparkClassLoader()));
      this.client = new YarnRMClient();
      this.maxNumExecutorFailures = org.apache.spark.deploy.ExecutorFailureTracker..MODULE$.maxNumExecutorFailures(sparkConf);
      this.exitCode = 0;
      this.unregistered = false;
      this.finished = false;
      this.finalStatus = this.getDefaultFinalStatus();
      this.finalMsg = "";
      this.registered = false;
      this.allocatorLock = new Object();
      int expiryInterval = yarnConf.getInt("yarn.am.liveness-monitor.expiry-interval-ms", 120000);
      this.heartbeatInterval = scala.math.package..MODULE$.max(0L, scala.math.package..MODULE$.min((long)(expiryInterval / 2), BoxesRunTime.unboxToLong(sparkConf.get(package$.MODULE$.RM_HEARTBEAT_INTERVAL()))));
      this.initialAllocationInterval = scala.math.package..MODULE$.min(this.heartbeatInterval(), BoxesRunTime.unboxToLong(sparkConf.get(package$.MODULE$.INITIAL_HEARTBEAT_INTERVAL())));
      this.nextAllocationInterval = this.initialAllocationInterval();
      this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkContextPromise = scala.concurrent.Promise..MODULE$.apply();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class AMEndpoint implements RpcEndpoint, Logging {
      private final RpcEnv rpcEnv;
      private final RpcEndpointRef driver;
      private volatile boolean org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown;
      private volatile int org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode;
      private final boolean clientModeTreatDisconnectAsFailed;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      public final ApplicationMaster $outer;

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

      public final RpcEndpointRef self() {
         return RpcEndpoint.self$(this);
      }

      public void onError(final Throwable cause) {
         RpcEndpoint.onError$(this, cause);
      }

      public void onConnected(final RpcAddress remoteAddress) {
         RpcEndpoint.onConnected$(this, remoteAddress);
      }

      public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
         RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
      }

      public void onStop() {
         RpcEndpoint.onStop$(this);
      }

      public final void stop() {
         RpcEndpoint.stop$(this);
      }

      public Logger org$apache$spark$internal$Logging$$log_() {
         return this.org$apache$spark$internal$Logging$$log_;
      }

      public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
         this.org$apache$spark$internal$Logging$$log_ = x$1;
      }

      public RpcEnv rpcEnv() {
         return this.rpcEnv;
      }

      private boolean shutdown() {
         return this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown;
      }

      public void org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown_$eq(final boolean x$1) {
         this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown = x$1;
      }

      private int exitCode() {
         return this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode;
      }

      public void org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode_$eq(final int x$1) {
         this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode = x$1;
      }

      private boolean clientModeTreatDisconnectAsFailed() {
         return this.clientModeTreatDisconnectAsFailed;
      }

      public void onStart() {
         this.driver.send(new CoarseGrainedClusterMessages.RegisterClusterManager(this.self()));
         if (!this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode() && !BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.YARN_UNMANAGED_AM()))) {
            String hostPort = YarnContainerInfoHelper$.MODULE$.getNodeManagerHttpAddress(scala.None..MODULE$);
            String yarnAMID = "yarn-am";
            MiscellaneousProcessDetails info = new MiscellaneousProcessDetails(hostPort, BoxesRunTime.unboxToInt(this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.AM_CORES())), this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$extractLogUrls());
            this.driver.send(new CoarseGrainedClusterMessages.MiscellaneousProcessAdded(System.currentTimeMillis(), yarnAMID, info));
         }
      }

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final AMEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens var5) {
                  byte[] tokens = var5.tokens();
                  .MODULE$.get().addDelegationTokens(tokens, this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.Shutdown var7) {
                  int code = var7.exitCode();
                  this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode_$eq(code);
                  this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown_$eq(true);
                  this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$allocator().setShutdown(true);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens) {
                  return true;
               } else {
                  return x1 instanceof CoarseGrainedClusterMessages.Shutdown;
               }
            }

            public {
               if (AMEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = AMEndpoint.this;
               }
            }
         };
      }

      public PartialFunction receiveAndReply(final RpcCallContext context) {
         return new Serializable(context) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final AMEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.RequestExecutors var8) {
                  Option var9 = scala.Option..MODULE$.apply(this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$allocator());
                  if (var9 instanceof Some var10) {
                     YarnAllocator a = (YarnAllocator)var10.value();
                     if (a.requestTotalExecutorsWithPreferredLocalities(var8.resourceProfileToTotalExecs(), var8.numLocalityAwareTasksPerResourceProfileId(), var8.hostToLocalTaskCount(), var8.excludedNodes())) {
                        this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$resetAllocatorInterval();
                     }

                     this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                     BoxedUnit var25 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var9)) {
                        throw new MatchError(var9);
                     }

                     this.$outer.logWarning((Function0)(() -> "Container allocator is not ready to request executors yet."));
                     this.context$1.reply(BoxesRunTime.boxToBoolean(false));
                     BoxedUnit var26 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillExecutors var12) {
                  Seq executorIds = var12.executorIds();
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver requested to kill executor(s) "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, executorIds.mkString(", "))}))))));
                  Option var14 = scala.Option..MODULE$.apply(this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$allocator());
                  if (var14 instanceof Some var15) {
                     YarnAllocator a = (YarnAllocator)var15.value();
                     executorIds.foreach((executorId) -> {
                        $anonfun$applyOrElse$3(a, executorId);
                        return BoxedUnit.UNIT;
                     });
                     BoxedUnit var23 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var14)) {
                        throw new MatchError(var14);
                     }

                     this.$outer.logWarning((Function0)(() -> "Container allocator is not ready to kill executors yet."));
                     BoxedUnit var24 = BoxedUnit.UNIT;
                  }

                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.GetExecutorLossReason var17) {
                  String eid = var17.executorId();
                  Option var19 = scala.Option..MODULE$.apply(this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$allocator());
                  if (var19 instanceof Some var20) {
                     YarnAllocator a = (YarnAllocator)var20.value();
                     a.enqueueGetLossReasonRequest(eid, this.context$1);
                     this.$outer.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$resetAllocatorInterval();
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var19)) {
                        throw new MatchError(var19);
                     }

                     this.$outer.logWarning((Function0)(() -> "Container allocator is not ready to find executor loss reasons yet."));
                     BoxedUnit var22 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof CoarseGrainedClusterMessages.RequestExecutors) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillExecutors) {
                  return true;
               } else {
                  return x1 instanceof CoarseGrainedClusterMessages.GetExecutorLossReason;
               }
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$3(final YarnAllocator a$1, final String executorId) {
               a$1.killExecutor(executorId);
            }

            public {
               if (AMEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = AMEndpoint.this;
                  this.context$1 = context$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public void onDisconnected(final RpcAddress remoteAddress) {
         if (!this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$isClusterMode() && !BoxesRunTime.unboxToBoolean(this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.YARN_UNMANAGED_AM()))) {
            if (!this.shutdown() && this.clientModeTreatDisconnectAsFailed()) {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Application Master lost connection with driver! Shutting down. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)}))))));
               this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish(FinalApplicationStatus.FAILED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_DISCONNECTED(), this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish$default$3());
            } else if (this.exitCode() == 0) {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver terminated or disconnected! Shutting down. "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)}))))));
               this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish(FinalApplicationStatus.SUCCEEDED, ApplicationMaster$.MODULE$.org$apache$spark$deploy$yarn$ApplicationMaster$$EXIT_SUCCESS(), this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish$default$3());
            } else {
               this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver terminated with exit code ", "! "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXIT_CODE..MODULE$, BoxesRunTime.boxToInteger(this.exitCode()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Shutting down. ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)}))))));
               this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish(FinalApplicationStatus.FAILED, this.exitCode(), this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer().finish$default$3());
            }
         }
      }

      // $FF: synthetic method
      public ApplicationMaster org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$$outer() {
         return this.$outer;
      }

      public AMEndpoint(final RpcEnv rpcEnv, final RpcEndpointRef driver) {
         this.rpcEnv = rpcEnv;
         this.driver = driver;
         if (ApplicationMaster.this == null) {
            throw null;
         } else {
            this.$outer = ApplicationMaster.this;
            super();
            RpcEndpoint.$init$(this);
            Logging.$init$(this);
            this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$shutdown = false;
            this.org$apache$spark$deploy$yarn$ApplicationMaster$AMEndpoint$$exitCode = 0;
            this.clientModeTreatDisconnectAsFailed = BoxesRunTime.unboxToBoolean(ApplicationMaster.this.org$apache$spark$deploy$yarn$ApplicationMaster$$sparkConf.get(package$.MODULE$.AM_CLIENT_MODE_TREAT_DISCONNECT_AS_FAILED()));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
