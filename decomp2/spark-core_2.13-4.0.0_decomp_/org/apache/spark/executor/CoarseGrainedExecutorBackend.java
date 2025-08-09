package org.apache.spark.executor;

import io.netty.util.internal.PlatformDependent;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkException;
import org.apache.spark.TaskState$;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.util.NettyUtils;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.IsolatedThreadSafeRpcEndpoint;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.ExecutorLossMessage$;
import org.apache.spark.scheduler.ExecutorLossReason;
import org.apache.spark.scheduler.TaskDescription;
import org.apache.spark.scheduler.TaskDescription$;
import org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages;
import org.apache.spark.util.ChildFirstURLClassLoader;
import org.apache.spark.util.MutableURLClassLoader;
import org.apache.spark.util.SerializableBuffer;
import org.apache.spark.util.SignalUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Function4;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple9;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction9;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011mc!B6m\u00019$\bBCA\f\u0001\t\u0015\r\u0011\"\u0011\u0002\u001c!Q\u00111\u0005\u0001\u0003\u0002\u0003\u0006I!!\b\t\u0015\u0005\u0015\u0002A!A!\u0002\u0013\t9\u0003\u0003\u0006\u0002>\u0001\u0011\t\u0011)A\u0005\u0003OA!\"a\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA\u0014\u0011)\t\t\u0005\u0001B\u0001B\u0003%\u0011q\u0005\u0005\u000b\u0003\u0007\u0002!\u0011!Q\u0001\n\u0005\u0015\u0003BCA&\u0001\t\u0005\t\u0015!\u0003\u0002N!Q\u0011Q\u000b\u0001\u0003\u0002\u0003\u0006I!a\u0016\t\u0015\u0005u\u0003A!A!\u0002\u0013\ty\u0006C\u0004\u0002l\u0001!\t!!\u001c\t\u0015\u0005\r\u0005A1A\u0005\u00029\f)\t\u0003\u0005\u0002 \u0002\u0001\u000b\u0011BAD\u0011!i\u0007\u00011A\u0005\u0002\u0005\u0005\u0006\"CAU\u0001\u0001\u0007I\u0011AAV\u0011!\t9\f\u0001Q!\n\u0005\r\u0006\"CA]\u0001\u0001\u0007I\u0011AA^\u0011%\t)\r\u0001a\u0001\n\u0003\t9\r\u0003\u0005\u0002L\u0002\u0001\u000b\u0015BA_\u0011%\t)\u000e\u0001a\u0001\n\u0013\t9\u000eC\u0005\u0002p\u0002\u0001\r\u0011\"\u0003\u0002r\"A\u0011Q\u001f\u0001!B\u0013\tI\u000eC\u0005\u0002x\u0002\u0001\r\u0011\"\u0003\u0002z\"I!\u0011\u0001\u0001A\u0002\u0013%!1\u0001\u0005\t\u0005\u000f\u0001\u0001\u0015)\u0003\u0002|\"I!\u0011\u0002\u0001C\u0002\u0013%!1\u0002\u0005\t\u0005'\u0001\u0001\u0015!\u0003\u0003\u000e!9!Q\u0003\u0001\u0005B\t]\u0001b\u0002B\r\u0001\u0011%!1\u0004\u0005\b\u0005O\u0001A\u0011\u0001B\u0015\u0011\u001d\u0011\t\u0004\u0001C\u0001\u0005gAqAa\u0015\u0001\t\u0003\u0011)\u0006C\u0004\u0003Z\u0001!\tA!\u0016\t\u000f\tm\u0003\u0001\"\u0001\u0003^!9!1\u000e\u0001\u0005B\t5\u0004b\u0002B>\u0001\u0011\u0005#Q\u0010\u0005\b\u0005\u0013\u0003A\u0011\tBF\u0011\u001d\u00119\n\u0001C!\u00053CqAa5\u0001\t#\u0011)\u000eC\u0005\u0003n\u0002\t\n\u0011\"\u0005\u0003p\"I1Q\u0001\u0001\u0012\u0002\u0013E1q\u0001\u0005\b\u0007\u0017\u0001A\u0011\u0002B\f\u000f!\u0019i\u0001\u001cE\u0001]\u000e=aaB6m\u0011\u0003q7\u0011\u0003\u0005\b\u0003WbC\u0011AB\n\u000f\u001d\u0019)\u0002\fEA\u0007/1qaa\u0007-\u0011\u0003\u001bi\u0002C\u0004\u0002l=\"\taa\u000b\t\u0013\r5r&!A\u0005B\r=\u0002\"CB\u001e_\u0005\u0005I\u0011AB\u001f\u0011%\u0019ydLA\u0001\n\u0003\u0019\t\u0005C\u0005\u0004F=\n\t\u0011\"\u0011\u0004H!I1\u0011K\u0018\u0002\u0002\u0013\u000511\u000b\u0005\n\u0007/z\u0013\u0011!C!\u00073B\u0011ba\u00170\u0003\u0003%\te!\u0018\t\u0013\r}s&!A\u0005\n\r\u0005dABB5Y\u0001\u001bY\u0007\u0003\u0006\u0002&e\u0012)\u001a!C\u0001\u0007[B!ba\u001c:\u0005#\u0005\u000b\u0011BA\u0014\u0011)\ti$\u000fBK\u0002\u0013\u00051Q\u000e\u0005\u000b\u0007cJ$\u0011#Q\u0001\n\u0005\u001d\u0002BCA s\tU\r\u0011\"\u0001\u0004n!Q11O\u001d\u0003\u0012\u0003\u0006I!a\n\t\u0015\u0005\u0005\u0013H!f\u0001\n\u0003\u0019i\u0007\u0003\u0006\u0004ve\u0012\t\u0012)A\u0005\u0003OA!\"a\u0011:\u0005+\u0007I\u0011AB\u001f\u0011)\u00199(\u000fB\tB\u0003%\u0011Q\t\u0005\u000b\u0007sJ$Q3A\u0005\u0002\r5\u0004BCB>s\tE\t\u0015!\u0003\u0002(!Q1QP\u001d\u0003\u0016\u0004%\taa \t\u0015\r\u0005\u0015H!E!\u0002\u0013\t9\u0006\u0003\u0006\u0002Ve\u0012)\u001a!C\u0001\u0007\u007fB!ba!:\u0005#\u0005\u000b\u0011BA,\u0011)\u0019))\u000fBK\u0002\u0013\u00051Q\b\u0005\u000b\u0007\u000fK$\u0011#Q\u0001\n\u0005\u0015\u0003bBA6s\u0011\u00051\u0011\u0012\u0005\n\u0007?K\u0014\u0011!C\u0001\u0007CC\u0011b!.:#\u0003%\taa.\t\u0013\rm\u0016(%A\u0005\u0002\r]\u0006\"CB_sE\u0005I\u0011AB\\\u0011%\u0019y,OI\u0001\n\u0003\u00199\fC\u0005\u0004Bf\n\n\u0011\"\u0001\u0004D\"I1qY\u001d\u0012\u0002\u0013\u00051q\u0017\u0005\n\u0007\u0013L\u0014\u0013!C\u0001\u0007\u0017D\u0011ba4:#\u0003%\taa3\t\u0013\rE\u0017(%A\u0005\u0002\r\r\u0007\"CB\u0017s\u0005\u0005I\u0011IB\u0018\u0011%\u0019Y$OA\u0001\n\u0003\u0019i\u0004C\u0005\u0004@e\n\t\u0011\"\u0001\u0004T\"I1QI\u001d\u0002\u0002\u0013\u00053q\t\u0005\n\u0007#J\u0014\u0011!C\u0001\u0007/D\u0011ba7:\u0003\u0003%\te!8\t\u0013\r]\u0013(!A\u0005B\re\u0003\"CB.s\u0005\u0005I\u0011IB/\u0011%\u0019\t/OA\u0001\n\u0003\u001a\u0019oB\u0005\u0004h2\n\t\u0011#\u0001\u0004j\u001aI1\u0011\u000e\u0017\u0002\u0002#\u000511\u001e\u0005\b\u0003W\nG\u0011\u0001C\u0002\u0011%\u0019Y&YA\u0001\n\u000b\u001ai\u0006C\u0005\u0005\u0006\u0005\f\t\u0011\"!\u0005\b!IA1D1\u0002\u0002\u0013\u0005EQ\u0004\u0005\n\u0007?\n\u0017\u0011!C\u0005\u0007CBq\u0001b\u000b-\t\u0003!i\u0003C\u0004\u0005:1\"\t\u0001b\u000f\t\u000f\u0011-C\u0006\"\u0001\u0005N!9AQ\u000b\u0017\u0005\n\u0011]#\u0001H\"pCJ\u001cXm\u0012:bS:,G-\u0012=fGV$xN\u001d\"bG.,g\u000e\u001a\u0006\u0003[:\f\u0001\"\u001a=fGV$xN\u001d\u0006\u0003_B\fQa\u001d9be.T!!\u001d:\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0018aA8sON9\u0001!^>\u0002\u0004\u0005-\u0001C\u0001<z\u001b\u00059(\"\u0001=\u0002\u000bM\u001c\u0017\r\\1\n\u0005i<(AB!osJ+g\r\u0005\u0002}\u007f6\tQP\u0003\u0002\u007f]\u0006\u0019!\u000f]2\n\u0007\u0005\u0005QPA\u000fJg>d\u0017\r^3e)\"\u0014X-\u00193TC\u001a,'\u000b]2F]\u0012\u0004x.\u001b8u!\u0011\t)!a\u0002\u000e\u00031L1!!\u0003m\u0005=)\u00050Z2vi>\u0014()Y2lK:$\u0007\u0003BA\u0007\u0003'i!!a\u0004\u000b\u0007\u0005Ea.\u0001\u0005j]R,'O\\1m\u0013\u0011\t)\"a\u0004\u0003\u000f1{wmZ5oO\u00061!\u000f]2F]Z\u001c\u0001!\u0006\u0002\u0002\u001eA\u0019A0a\b\n\u0007\u0005\u0005RP\u0001\u0004Sa\u000e,eN^\u0001\beB\u001cWI\u001c<!\u0003%!'/\u001b<feV\u0013H\u000e\u0005\u0003\u0002*\u0005]b\u0002BA\u0016\u0003g\u00012!!\fx\u001b\t\tyC\u0003\u0003\u00022\u0005e\u0011A\u0002\u001fs_>$h(C\u0002\u00026]\fa\u0001\u0015:fI\u00164\u0017\u0002BA\u001d\u0003w\u0011aa\u0015;sS:<'bAA\u001bo\u0006QQ\r_3dkR|'/\u00133\u0002\u0017\tLg\u000eZ!eIJ,7o]\u0001\tQ>\u001cHO\\1nK\u0006)1m\u001c:fgB\u0019a/a\u0012\n\u0007\u0005%sOA\u0002J]R\f1!\u001a8w!\u0011\ty%!\u0015\u000e\u00039L1!a\u0015o\u0005!\u0019\u0006/\u0019:l\u000b:4\u0018\u0001\u0005:fg>,(oY3t\r&dWm\u00149u!\u00151\u0018\u0011LA\u0014\u0013\r\tYf\u001e\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u001fI,7o\\;sG\u0016\u0004&o\u001c4jY\u0016\u0004B!!\u0019\u0002h5\u0011\u00111\r\u0006\u0004\u0003Kr\u0017\u0001\u0003:fg>,(oY3\n\t\u0005%\u00141\r\u0002\u0010%\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\u00061A(\u001b8jiz\"B#a\u001c\u0002r\u0005M\u0014QOA<\u0003s\nY(! \u0002\u0000\u0005\u0005\u0005cAA\u0003\u0001!9\u0011qC\u0006A\u0002\u0005u\u0001bBA\u0013\u0017\u0001\u0007\u0011q\u0005\u0005\b\u0003{Y\u0001\u0019AA\u0014\u0011\u001d\tyd\u0003a\u0001\u0003OAq!!\u0011\f\u0001\u0004\t9\u0003C\u0004\u0002D-\u0001\r!!\u0012\t\u000f\u0005-3\u00021\u0001\u0002N!9\u0011QK\u0006A\u0002\u0005]\u0003bBA/\u0017\u0001\u0007\u0011qL\u0001\tgR|\u0007\u000f]5oOV\u0011\u0011q\u0011\t\u0005\u0003\u0013\u000bY*\u0004\u0002\u0002\f*!\u0011QRAH\u0003\u0019\tGo\\7jG*!\u0011\u0011SAJ\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0003+\u000b9*\u0001\u0003vi&d'BAAM\u0003\u0011Q\u0017M^1\n\t\u0005u\u00151\u0012\u0002\u000e\u0003R|W.[2C_>dW-\u00198\u0002\u0013M$x\u000e\u001d9j]\u001e\u0004SCAAR!\u0011\t)!!*\n\u0007\u0005\u001dFN\u0001\u0005Fq\u0016\u001cW\u000f^8s\u00031)\u00070Z2vi>\u0014x\fJ3r)\u0011\ti+a-\u0011\u0007Y\fy+C\u0002\u00022^\u0014A!\u00168ji\"I\u0011QW\b\u0002\u0002\u0003\u0007\u00111U\u0001\u0004q\u0012\n\u0014!C3yK\u000e,Ho\u001c:!\u0003\u0019!'/\u001b<feV\u0011\u0011Q\u0018\t\u0006m\u0006e\u0013q\u0018\t\u0004y\u0006\u0005\u0017bAAb{\nq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017A\u00033sSZ,'o\u0018\u0013fcR!\u0011QVAe\u0011%\t)LEA\u0001\u0002\u0004\ti,A\u0004ee&4XM\u001d\u0011)\u0007M\ty\rE\u0002w\u0003#L1!a5x\u0005!1x\u000e\\1uS2,\u0017AC0sKN|WO]2fgV\u0011\u0011\u0011\u001c\t\t\u00037\f)/a\n\u0002j6\u0011\u0011Q\u001c\u0006\u0005\u0003?\f\t/A\u0005j[6,H/\u00192mK*\u0019\u00111]<\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002h\u0006u'aA'baB!\u0011\u0011MAv\u0013\u0011\ti/a\u0019\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u001d}\u0013Xm]8ve\u000e,7o\u0018\u0013fcR!\u0011QVAz\u0011%\t),FA\u0001\u0002\u0004\tI.A\u0006`e\u0016\u001cx.\u001e:dKN\u0004\u0013A\u00043fG>lW.[:tS>tW\rZ\u000b\u0003\u0003w\u00042A^A\u007f\u0013\r\typ\u001e\u0002\b\u0005>|G.Z1o\u0003I!WmY8n[&\u001c8/[8oK\u0012|F%Z9\u0015\t\u00055&Q\u0001\u0005\n\u0003kC\u0012\u0011!a\u0001\u0003w\fq\u0002Z3d_6l\u0017n]:j_:,G\rI\u0001\u0013Y\u0006\u001cH\u000fV1tW\u001aKg.[:i)&lW-\u0006\u0002\u0003\u000eA!\u0011\u0011\u0012B\b\u0013\u0011\u0011\t\"a#\u0003\u0015\u0005#x.\\5d\u0019>tw-A\nmCN$H+Y:l\r&t\u0017n\u001d5US6,\u0007%A\u0004p]N#\u0018M\u001d;\u0015\u0005\u00055\u0016!E2sK\u0006$Xm\u00117bgNdu.\u00193feR\u0011!Q\u0004\t\u0005\u0005?\u0011\u0019#\u0004\u0002\u0003\")\u0019\u0011Q\u00138\n\t\t\u0015\"\u0011\u0005\u0002\u0016\u001bV$\u0018M\u00197f+Jc5\t\\1tg2{\u0017\rZ3s\u0003Q\u0001\u0018M]:f\u001fJ4\u0015N\u001c3SKN|WO]2fgR!!1\u0006B\u0018!!\tIC!\f\u0002(\u0005%\u0018\u0002BAt\u0003wAq!!\u0016\u001f\u0001\u0004\t9&\u0001\thKR,6/\u001a:DY\u0006\u001c8\u000fU1uQV\u0011!Q\u0007\t\u0007\u0005o\u0011\tEa\u0012\u000f\t\te\"Q\b\b\u0005\u0003[\u0011Y$C\u0001y\u0013\r\u0011yd^\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0011\u0019E!\u0012\u0003\u0007M+\u0017OC\u0002\u0003@]\u0004BA!\u0013\u0003P5\u0011!1\n\u0006\u0005\u0005\u001b\n9*A\u0002oKRLAA!\u0015\u0003L\t\u0019QK\u0015'\u0002\u001d\u0015DHO]1di2{w-\u0016:mgV\u0011!q\u000b\t\t\u0003S\u0011i#a\n\u0002(\u0005\tR\r\u001f;sC\u000e$\u0018\t\u001e;sS\n,H/Z:\u0002?9|G/\u001b4z\tJLg/\u001a:BE>,H\u000fU;tQ\u000e{W\u000e\u001d7fi&|g\u000e\u0006\u0005\u0002.\n}#1\rB4\u0011\u001d\u0011\tG\ta\u0001\u0003\u000b\n\u0011b\u001d5vM\u001adW-\u00133\t\u000f\t\u0015$\u00051\u0001\u0002F\u0005q1\u000f[;gM2,W*\u001a:hK&#\u0007b\u0002B5E\u0001\u0007\u0011QI\u0001\t[\u0006\u0004\u0018J\u001c3fq\u00069!/Z2fSZ,WC\u0001B8!\u001d1(\u0011\u000fB;\u0003[K1Aa\u001dx\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007c\u0001<\u0003x%\u0019!\u0011P<\u0003\u0007\u0005s\u00170A\bsK\u000e,\u0017N^3B]\u0012\u0014V\r\u001d7z)\u0011\u0011yGa \t\u000f\t\u0005E\u00051\u0001\u0003\u0004\u000691m\u001c8uKb$\bc\u0001?\u0003\u0006&\u0019!qQ?\u0003\u001dI\u00038mQ1mY\u000e{g\u000e^3yi\u0006qqN\u001c#jg\u000e|gN\\3di\u0016$G\u0003BAW\u0005\u001bCqAa$&\u0001\u0004\u0011\t*A\u0007sK6|G/Z!eIJ,7o\u001d\t\u0004y\nM\u0015b\u0001BK{\nQ!\u000b]2BI\u0012\u0014Xm]:\u0002\u0019M$\u0018\r^;t+B$\u0017\r^3\u0015\u0011\u00055&1\u0014BS\u0005\u0007DqA!('\u0001\u0004\u0011y*\u0001\u0004uCN\\\u0017\n\u001a\t\u0004m\n\u0005\u0016b\u0001BRo\n!Aj\u001c8h\u0011\u001d\u00119K\na\u0001\u0005S\u000bQa\u001d;bi\u0016\u0004BAa+\u0003>:!!Q\u0016B]\u001d\u0011\u0011yKa.\u000f\t\tE&Q\u0017\b\u0005\u0003[\u0011\u0019,C\u0001t\u0013\t\t(/\u0003\u0002pa&\u0019!1\u00188\u0002\u0013Q\u000b7o[*uCR,\u0017\u0002\u0002B`\u0005\u0003\u0014\u0011\u0002V1tWN#\u0018\r^3\u000b\u0007\tmf\u000eC\u0004\u0003F\u001a\u0002\rAa2\u0002\t\u0011\fG/\u0019\t\u0005\u0005\u0013\u0014y-\u0004\u0002\u0003L*!!QZAL\u0003\rq\u0017n\\\u0005\u0005\u0005#\u0014YM\u0001\u0006CsR,')\u001e4gKJ\fA\"\u001a=ji\u0016CXmY;u_J$\"\"!,\u0003X\nm'q\u001cBu\u0011\u001d\u0011In\na\u0001\u0003\u000b\nAaY8eK\"9!Q\\\u0014A\u0002\u0005\u001d\u0012A\u0002:fCN|g\u000eC\u0005\u0003b\u001e\u0002\n\u00111\u0001\u0003d\u0006IA\u000f\u001b:po\u0006\u0014G.\u001a\t\u0005\u0005o\u0011)/\u0003\u0003\u0003h\n\u0015#!\u0003+ie><\u0018M\u00197f\u0011%\u0011Yo\nI\u0001\u0002\u0004\tY0\u0001\u0007o_RLg-\u001f#sSZ,'/\u0001\ffq&$X\t_3dkR|'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\t\u0011\tP\u000b\u0003\u0003d\nM8F\u0001B{!\u0011\u00119p!\u0001\u000e\u0005\te(\u0002\u0002B~\u0005{\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t}x/\u0001\u0006b]:|G/\u0019;j_:LAaa\u0001\u0003z\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002-\u0015D\u0018\u000e^#yK\u000e,Ho\u001c:%I\u00164\u0017-\u001e7uIQ*\"a!\u0003+\t\u0005m(1_\u0001\u0011I\u0016\u001cw.\\7jgNLwN\\*fY\u001a\fAdQ8beN,wI]1j]\u0016$W\t_3dkR|'OQ1dW\u0016tG\rE\u0002\u0002\u00061\u001aB\u0001L;\u0002\fQ\u00111qB\u0001\u0013%\u0016<\u0017n\u001d;fe\u0016$W\t_3dkR|'\u000fE\u0002\u0004\u001a=j\u0011\u0001\f\u0002\u0013%\u0016<\u0017n\u001d;fe\u0016$W\t_3dkR|'o\u0005\u00040k\u000e}1Q\u0005\t\u0004m\u000e\u0005\u0012bAB\u0012o\n9\u0001K]8ek\u000e$\b\u0003\u0002B\u001c\u0007OIAa!\u000b\u0003F\ta1+\u001a:jC2L'0\u00192mKR\u00111qC\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\rE\u0002\u0003BB\u001a\u0007si!a!\u000e\u000b\t\r]\u0012qS\u0001\u0005Y\u0006tw-\u0003\u0003\u0002:\rU\u0012\u0001\u00049s_\u0012,8\r^!sSRLXCAA#\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\u001e\u0004D!I\u0011QW\u001a\u0002\u0002\u0003\u0007\u0011QI\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u00111\u0011\n\t\u0007\u0007\u0017\u001aiE!\u001e\u000e\u0005\u0005\u0005\u0018\u0002BB(\u0003C\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111`B+\u0011%\t),NA\u0001\u0002\u0004\u0011)(\u0001\u0005iCND7i\u001c3f)\t\t)%\u0001\u0005u_N#(/\u001b8h)\t\u0019\t$\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004dA!11GB3\u0013\u0011\u00199g!\u000e\u0003\r=\u0013'.Z2u\u0005%\t%oZ;nK:$8o\u0005\u0004:k\u000e}1QE\u000b\u0003\u0003O\t!\u0002\u001a:jm\u0016\u0014XK\u001d7!\u0003-)\u00070Z2vi>\u0014\u0018\n\u001a\u0011\u0002\u0019\tLg\u000eZ!eIJ,7o\u001d\u0011\u0002\u0013!|7\u000f\u001e8b[\u0016\u0004\u0013AB2pe\u0016\u001c\b%A\u0003baBLE-\u0001\u0004baBLE\rI\u0001\no>\u00148.\u001a:Ve2,\"!a\u0016\u0002\u0015]|'o[3s+Jd\u0007%A\tsKN|WO]2fg\u001aKG.Z(qi\u0002\n\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z%e\u0003I\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\n\u001a\u0011\u0015)\r-5QRBH\u0007#\u001b\u0019j!&\u0004\u0018\u000ee51TBO!\r\u0019I\"\u000f\u0005\b\u0003Ka\u0005\u0019AA\u0014\u0011\u001d\ti\u0004\u0014a\u0001\u0003OAq!a\u0010M\u0001\u0004\t9\u0003C\u0004\u0002B1\u0003\r!a\n\t\u000f\u0005\rC\n1\u0001\u0002F!91\u0011\u0010'A\u0002\u0005\u001d\u0002bBB?\u0019\u0002\u0007\u0011q\u000b\u0005\b\u0003+b\u0005\u0019AA,\u0011\u001d\u0019)\t\u0014a\u0001\u0003\u000b\nAaY8qsR!21RBR\u0007K\u001b9k!+\u0004,\u000e56qVBY\u0007gC\u0011\"!\nN!\u0003\u0005\r!a\n\t\u0013\u0005uR\n%AA\u0002\u0005\u001d\u0002\"CA \u001bB\u0005\t\u0019AA\u0014\u0011%\t\t%\u0014I\u0001\u0002\u0004\t9\u0003C\u0005\u0002D5\u0003\n\u00111\u0001\u0002F!I1\u0011P'\u0011\u0002\u0003\u0007\u0011q\u0005\u0005\n\u0007{j\u0005\u0013!a\u0001\u0003/B\u0011\"!\u0016N!\u0003\u0005\r!a\u0016\t\u0013\r\u0015U\n%AA\u0002\u0005\u0015\u0013AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0007sSC!a\n\u0003t\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"a!2+\t\u0005\u0015#1_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]*\"a!4+\t\u0005]#1_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00139\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIe\"BA!\u001e\u0004V\"I\u0011QW-\u0002\u0002\u0003\u0007\u0011Q\t\u000b\u0005\u0003w\u001cI\u000eC\u0005\u00026n\u000b\t\u00111\u0001\u0003v\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\u0019\tda8\t\u0013\u0005UF,!AA\u0002\u0005\u0015\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002|\u000e\u0015\b\"CA[?\u0006\u0005\t\u0019\u0001B;\u0003%\t%oZ;nK:$8\u000fE\u0002\u0004\u001a\u0005\u001cR!YBw\u0007s\u0004\u0002da<\u0004v\u0006\u001d\u0012qEA\u0014\u0003O\t)%a\n\u0002X\u0005]\u0013QIBF\u001b\t\u0019\tPC\u0002\u0004t^\fqA];oi&lW-\u0003\u0003\u0004x\u000eE(!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8osA!11 C\u0001\u001b\t\u0019iP\u0003\u0003\u0004\u0000\u0006]\u0015AA5p\u0013\u0011\u0019Ic!@\u0015\u0005\r%\u0018!B1qa2LH\u0003FBF\t\u0013!Y\u0001\"\u0004\u0005\u0010\u0011EA1\u0003C\u000b\t/!I\u0002C\u0004\u0002&\u0011\u0004\r!a\n\t\u000f\u0005uB\r1\u0001\u0002(!9\u0011q\b3A\u0002\u0005\u001d\u0002bBA!I\u0002\u0007\u0011q\u0005\u0005\b\u0003\u0007\"\u0007\u0019AA#\u0011\u001d\u0019I\b\u001aa\u0001\u0003OAqa! e\u0001\u0004\t9\u0006C\u0004\u0002V\u0011\u0004\r!a\u0016\t\u000f\r\u0015E\r1\u0001\u0002F\u00059QO\\1qa2LH\u0003\u0002C\u0010\tO\u0001RA^A-\tC\u0001RC\u001eC\u0012\u0003O\t9#a\n\u0002(\u0005\u0015\u0013qEA,\u0003/\n)%C\u0002\u0005&]\u0014a\u0001V;qY\u0016L\u0004\"\u0003C\u0015K\u0006\u0005\t\u0019ABF\u0003\rAH\u0005M\u0001\u0005[\u0006Lg\u000e\u0006\u0003\u0002.\u0012=\u0002b\u0002C\u0019O\u0002\u0007A1G\u0001\u0005CJ<7\u000fE\u0003w\tk\t9#C\u0002\u00058]\u0014Q!\u0011:sCf\f1A];o)\u0019\ti\u000b\"\u0010\u0005B!9Aq\b5A\u0002\r-\u0015!C1sOVlWM\u001c;t\u0011\u001d!\u0019\u0005\u001ba\u0001\t\u000b\nqBY1dW\u0016tGm\u0011:fCR,gI\u001c\t\u000em\u0012\u001d\u0013QDBF\u0003\u001b\ny&a\u001c\n\u0007\u0011%sOA\u0005Gk:\u001cG/[8oi\u0005q\u0001/\u0019:tK\u0006\u0013x-^7f]R\u001cHCBBF\t\u001f\"\t\u0006C\u0004\u00052%\u0004\r\u0001b\r\t\u000f\u0011M\u0013\u000e1\u0001\u0002(\u0005\t2\r\\1tg:\u000bW.\u001a$pe\u0016sGO]=\u0002#A\u0014\u0018N\u001c;Vg\u0006<W-\u00118e\u000bbLG\u000f\u0006\u0003\u0002.\u0012e\u0003b\u0002C*U\u0002\u0007\u0011q\u0005"
)
public class CoarseGrainedExecutorBackend implements IsolatedThreadSafeRpcEndpoint, ExecutorBackend, Logging {
   private final RpcEnv rpcEnv;
   private final String driverUrl;
   public final String org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId;
   public final String org$apache$spark$executor$CoarseGrainedExecutorBackend$$hostname;
   private final int cores;
   public final SparkEnv org$apache$spark$executor$CoarseGrainedExecutorBackend$$env;
   private final Option resourcesFileOpt;
   private final ResourceProfile resourceProfile;
   private final AtomicBoolean stopping;
   private Executor executor;
   private volatile Option driver;
   private Map org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources;
   private boolean org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned;
   private final AtomicLong org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Arguments parseArguments(final String[] args, final String classNameForEntry) {
      return CoarseGrainedExecutorBackend$.MODULE$.parseArguments(args, classNameForEntry);
   }

   public static void run(final Arguments arguments, final Function4 backendCreateFn) {
      CoarseGrainedExecutorBackend$.MODULE$.run(arguments, backendCreateFn);
   }

   public static void main(final String[] args) {
      CoarseGrainedExecutorBackend$.MODULE$.main(args);
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

   public final int threadCount() {
      return IsolatedThreadSafeRpcEndpoint.threadCount$(this);
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

   public AtomicBoolean stopping() {
      return this.stopping;
   }

   public Executor executor() {
      return this.executor;
   }

   public void executor_$eq(final Executor x$1) {
      this.executor = x$1;
   }

   public Option driver() {
      return this.driver;
   }

   public void driver_$eq(final Option x$1) {
      this.driver = x$1;
   }

   public Map org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources() {
      return this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources;
   }

   private void _resources_$eq(final Map x$1) {
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources = x$1;
   }

   public boolean org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned() {
      return this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned;
   }

   public void org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned_$eq(final boolean x$1) {
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned = x$1;
   }

   public AtomicLong org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime() {
      return this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime;
   }

   public void onStart() {
      if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED()))) {
         String signal = (String)this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_DECOMMISSION_SIGNAL());
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registering SIG", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" handler to trigger decommissioning."})))).log(scala.collection.immutable.Nil..MODULE$))));
         SignalUtils$.MODULE$.register(signal, this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to register SIG", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SIGNAL..MODULE$, signal)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"handler - disabling executor decommission feature."})))).log(scala.collection.immutable.Nil..MODULE$)), SignalUtils$.MODULE$.register$default$3(), (JFunction0.mcZ.sp)() -> BoxesRunTime.unboxToBoolean(this.self().askSync(CoarseGrainedClusterMessages.ExecutorDecommissionSigReceived$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean())));
      }

      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Connecting to driver: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.URL..MODULE$, this.driverUrl)})))));

      try {
         SecurityManager securityManager = new SecurityManager(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf(), SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
         SparkConf x$1 = this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf();
         String x$2 = "shuffle";
         Some x$3 = new Some(securityManager.getRpcSSLOptions());
         int x$4 = SparkTransportConf$.MODULE$.fromSparkConf$default$3();
         Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
         TransportConf shuffleClientTransportConf = SparkTransportConf$.MODULE$.fromSparkConf(x$1, "shuffle", x$4, x$5, x$3);
         if (NettyUtils.preferDirectBufs(shuffleClientTransportConf) && PlatformDependent.maxDirectMemory() < BoxesRunTime.unboxToLong(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM()))) {
            String var10002 = org.apache.spark.internal.config.package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM().key();
            throw new SparkException("Netty direct memory should at least be bigger than '" + var10002 + "', but got " + PlatformDependent.maxDirectMemory() + " bytes < " + this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.MAX_REMOTE_BLOCK_SIZE_FETCH_TO_MEM()));
         }

         this._resources_$eq(this.parseOrFindResources(this.resourcesFileOpt));
      } catch (Throwable var13) {
         if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13)) {
            throw var13;
         }

         this.exitExecutor(1, "Unable to create executor due to " + var13.getMessage(), var13, this.exitExecutor$default$4());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.rpcEnv().asyncSetupEndpointRefByURI(this.driverUrl).flatMap((ref) -> {
         this.driver_$eq(new Some(ref));
         this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.executorBackend_$eq(scala.Option..MODULE$.apply(this));
         return ref.ask(new CoarseGrainedClusterMessages.RegisterExecutor(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId, this.self(), this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$hostname, this.cores, this.extractLogUrls(), this.extractAttributes(), this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources(), this.resourceProfile.id()), scala.reflect.ClassTag..MODULE$.Boolean());
      }, ThreadUtils$.MODULE$.sameThread()).onComplete((x0$1) -> {
         $anonfun$onStart$5(this, x0$1);
         return BoxedUnit.UNIT;
      }, ThreadUtils$.MODULE$.sameThread());
   }

   private MutableURLClassLoader createClassLoader() {
      ClassLoader currentLoader = Utils$.MODULE$.getContextOrSparkClassLoader();
      URL[] urls = (URL[])this.getUserClassPath().toArray(scala.reflect.ClassTag..MODULE$.apply(URL.class));
      return (MutableURLClassLoader)(BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_USER_CLASS_PATH_FIRST())) ? new ChildFirstURLClassLoader(urls, currentLoader) : new MutableURLClassLoader(urls, currentLoader));
   }

   public Map parseOrFindResources(final Option resourcesFileOpt) {
      MutableURLClassLoader urlClassLoader = this.createClassLoader();
      this.logDebug((Function0)(() -> "Resource profile id is: " + this.resourceProfile.id()));
      return (Map)Utils$.MODULE$.withContextClassLoader(urlClassLoader, () -> {
         Map resources = ResourceUtils$.MODULE$.getOrDiscoverAllResourcesForResourceProfile(resourcesFileOpt, org.apache.spark.internal.config.package$.MODULE$.SPARK_EXECUTOR_PREFIX(), this.resourceProfile, this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf());
         ResourceUtils$.MODULE$.logResourceInfo(org.apache.spark.internal.config.package$.MODULE$.SPARK_EXECUTOR_PREFIX(), resources);
         return resources;
      });
   }

   public Seq getUserClassPath() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public Map extractLogUrls() {
      String prefix = "SPARK_LOG_URL_";
      return (Map)((MapOps)scala.sys.package..MODULE$.env().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$extractLogUrls$1(prefix, x0$1)))).map((e) -> new Tuple2(((String)e._1()).substring(prefix.length()).toLowerCase(Locale.ROOT), e._2()));
   }

   public Map extractAttributes() {
      String prefix = "SPARK_EXECUTOR_ATTRIBUTE_";
      return (Map)((MapOps)scala.sys.package..MODULE$.env().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$extractAttributes$1(prefix, x0$1)))).map((e) -> new Tuple2(((String)e._1()).substring(prefix.length()).toUpperCase(Locale.ROOT), e._2()));
   }

   public void notifyDriverAboutPushCompletion(final int shuffleId, final int shuffleMergeId, final int mapIndex) {
      CoarseGrainedClusterMessages.ShufflePushCompletion msg = new CoarseGrainedClusterMessages.ShufflePushCompletion(shuffleId, shuffleMergeId, mapIndex);
      this.driver().foreach((x$1) -> {
         $anonfun$notifyDriverAboutPushCompletion$1(msg, x$1);
         return BoxedUnit.UNIT;
      });
   }

   public PartialFunction receive() {
      return new Serializable() {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final CoarseGrainedExecutorBackend $outer;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (CoarseGrainedExecutorBackend.RegisteredExecutor$.MODULE$.equals(x1)) {
               this.$outer.logInfo((Function0)(() -> "Successfully registered with driver"));

               BoxedUnit var29;
               try {
                  CoarseGrainedExecutorBackend var30 = this.$outer;
                  String x$1 = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId;
                  String x$2 = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$hostname;
                  SparkEnv x$3 = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env;
                  Seq x$4 = this.$outer.getUserClassPath();
                  boolean x$5 = false;
                  Map x$6 = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources();
                  Thread.UncaughtExceptionHandler x$7 = Executor$.MODULE$.$lessinit$greater$default$6();
                  var30.executor_$eq(new Executor(x$1, x$2, x$3, x$4, false, x$7, x$6));
                  ((RpcEndpointRef)this.$outer.driver().get()).send(new CoarseGrainedClusterMessages.LaunchedExecutor(this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId));
                  var29 = BoxedUnit.UNIT;
               } catch (Throwable var28) {
                  if (var28 == null || !scala.util.control.NonFatal..MODULE$.apply(var28)) {
                     throw var28;
                  }

                  this.$outer.exitExecutor(1, "Unable to create executor due to " + var28.getMessage(), var28, this.$outer.exitExecutor$default$4());
                  var29 = BoxedUnit.UNIT;
                  var29 = BoxedUnit.UNIT;
               }

               return var29;
            } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateExecutorLogLevel) {
               CoarseGrainedClusterMessages.UpdateExecutorLogLevel var16 = (CoarseGrainedClusterMessages.UpdateExecutorLogLevel)x1;
               String newLogLevel = var16.logLevel();
               Utils$.MODULE$.setLogLevelIfNeeded(newLogLevel);
               return BoxedUnit.UNIT;
            } else if (x1 instanceof CoarseGrainedClusterMessages.LaunchTask) {
               CoarseGrainedClusterMessages.LaunchTask var18 = (CoarseGrainedClusterMessages.LaunchTask)x1;
               SerializableBuffer data = var18.data();
               if (this.$outer.executor() == null) {
                  this.$outer.exitExecutor(1, "Received LaunchTask command but executor was null", this.$outer.exitExecutor$default$3(), this.$outer.exitExecutor$default$4());
                  return BoxedUnit.UNIT;
               } else {
                  TaskDescription taskDesc = TaskDescription$.MODULE$.decode(data.value());
                  this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Got assigned task ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(taskDesc.taskId()))})))));
                  this.$outer.executor().launchTask(this.$outer, taskDesc);
                  return BoxedUnit.UNIT;
               }
            } else if (x1 instanceof CoarseGrainedClusterMessages.KillTask) {
               CoarseGrainedClusterMessages.KillTask var21 = (CoarseGrainedClusterMessages.KillTask)x1;
               long taskId = var21.taskId();
               boolean interruptThread = var21.interruptThread();
               String reason = var21.reason();
               if (this.$outer.executor() == null) {
                  this.$outer.exitExecutor(1, "Received KillTask command but executor was null", this.$outer.exitExecutor$default$3(), this.$outer.exitExecutor$default$4());
                  return BoxedUnit.UNIT;
               } else {
                  this.$outer.executor().killTask(taskId, interruptThread, reason);
                  return BoxedUnit.UNIT;
               }
            } else if (CoarseGrainedClusterMessages.StopExecutor$.MODULE$.equals(x1)) {
               this.$outer.stopping().set(true);
               this.$outer.logInfo((Function0)(() -> "Driver commanded a shutdown"));
               this.$outer.self().send(CoarseGrainedClusterMessages.Shutdown$.MODULE$);
               return BoxedUnit.UNIT;
            } else if (CoarseGrainedClusterMessages.Shutdown$.MODULE$.equals(x1)) {
               this.$outer.stopping().set(true);
               (new Thread() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void run() {
                     if (this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$anonfun$$$outer().executor() == null) {
                        System.exit(1);
                     } else {
                        this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$anonfun$$$outer().executor().stop();
                     }
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               }).start();
               return BoxedUnit.UNIT;
            } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens) {
               CoarseGrainedClusterMessages.UpdateDelegationTokens var26 = (CoarseGrainedClusterMessages.UpdateDelegationTokens)x1;
               byte[] tokenBytes = var26.tokens();
               this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received tokens of ", " bytes"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, BoxesRunTime.boxToInteger(tokenBytes.length))})))));
               SparkHadoopUtil$.MODULE$.get().addDelegationTokens(tokenBytes, this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf());
               return BoxedUnit.UNIT;
            } else if (CoarseGrainedClusterMessages.DecommissionExecutor$.MODULE$.equals(x1)) {
               this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissionSelf();
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (CoarseGrainedExecutorBackend.RegisteredExecutor$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateExecutorLogLevel) {
               return true;
            } else if (x1 instanceof CoarseGrainedClusterMessages.LaunchTask) {
               return true;
            } else if (x1 instanceof CoarseGrainedClusterMessages.KillTask) {
               return true;
            } else if (CoarseGrainedClusterMessages.StopExecutor$.MODULE$.equals(x1)) {
               return true;
            } else if (CoarseGrainedClusterMessages.Shutdown$.MODULE$.equals(x1)) {
               return true;
            } else if (x1 instanceof CoarseGrainedClusterMessages.UpdateDelegationTokens) {
               return true;
            } else {
               return CoarseGrainedClusterMessages.DecommissionExecutor$.MODULE$.equals(x1);
            }
         }

         // $FF: synthetic method
         public CoarseGrainedExecutorBackend org$apache$spark$executor$CoarseGrainedExecutorBackend$$anonfun$$$outer() {
            return this.$outer;
         }

         public {
            if (CoarseGrainedExecutorBackend.this == null) {
               throw null;
            } else {
               this.$outer = CoarseGrainedExecutorBackend.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public PartialFunction receiveAndReply(final RpcCallContext context) {
      return new Serializable(context) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final CoarseGrainedExecutorBackend $outer;
         private final RpcCallContext context$1;

         public final Object applyOrElse(final Object x1, final Function1 default) {
            if (CoarseGrainedClusterMessages.ExecutorDecommissionSigReceived$.MODULE$.equals(x1)) {
               BooleanRef driverNotified = BooleanRef.create(false);

               try {
                  this.$outer.driver().foreach((driverRef) -> {
                     $anonfun$applyOrElse$5(this, driverNotified, driverRef);
                     return BoxedUnit.UNIT;
                  });
               } catch (Exception var10) {
                  if (driverNotified.elem) {
                     this.$outer.logError((Function0)(() -> "Fail to decommission self (but driver has been notified)."), var10);
                  } else {
                     this.$outer.logError((Function0)(() -> "Fail to tell driver that we are starting decommissioning"), var10);
                  }

                  this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned_$eq(false);
               }

               this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned()));
               return BoxedUnit.UNIT;
            } else if (x1 instanceof CoarseGrainedClusterMessages.TaskThreadDump) {
               CoarseGrainedClusterMessages.TaskThreadDump var7 = (CoarseGrainedClusterMessages.TaskThreadDump)x1;
               long taskId = var7.taskId();
               this.context$1.reply(this.$outer.executor().getTaskThreadDump(taskId));
               return BoxedUnit.UNIT;
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Object x1) {
            if (CoarseGrainedClusterMessages.ExecutorDecommissionSigReceived$.MODULE$.equals(x1)) {
               return true;
            } else {
               return x1 instanceof CoarseGrainedClusterMessages.TaskThreadDump;
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$applyOrElse$5(final Object $this, final BooleanRef driverNotified$1, final RpcEndpointRef driverRef) {
            driverNotified$1.elem = BoxesRunTime.unboxToBoolean(driverRef.askSync(new CoarseGrainedClusterMessages.ExecutorDecommissioning($this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId), scala.reflect.ClassTag..MODULE$.Boolean()));
            if (driverNotified$1.elem) {
               $this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissionSelf();
            }
         }

         public {
            if (CoarseGrainedExecutorBackend.this == null) {
               throw null;
            } else {
               this.$outer = CoarseGrainedExecutorBackend.this;
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
      if (this.stopping().get()) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Driver from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, remoteAddress)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" disconnected during shutdown"})))).log(scala.collection.immutable.Nil..MODULE$))));
      } else if (this.driver().exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$onDisconnected$2(remoteAddress, x$2)))) {
         this.exitExecutor(1, "Driver " + remoteAddress + " disassociated! Shutting down.", (Throwable)null, false);
      } else {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"An unknown (", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REMOTE_ADDRESS..MODULE$, remoteAddress)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"driver disconnected."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }
   }

   public void statusUpdate(final long taskId, final Enumeration.Value state, final ByteBuffer data) {
      TaskDescription taskDescription = ((Executor.TaskRunner)this.executor().runningTasks().get(BoxesRunTime.boxToLong(taskId))).taskDescription();
      Map resources = taskDescription.resources();
      int cpus = taskDescription.cpus();
      CoarseGrainedClusterMessages.StatusUpdate msg = CoarseGrainedClusterMessages.StatusUpdate$.MODULE$.apply(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId, taskId, state, data, cpus, resources);
      if (TaskState$.MODULE$.isFinished(state)) {
         this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime().set(System.nanoTime());
      }

      Option var10 = this.driver();
      if (var10 instanceof Some var11) {
         RpcEndpointRef driverRef = (RpcEndpointRef)var11.value();
         driverRef.send(msg);
         BoxedUnit var13 = BoxedUnit.UNIT;
      } else if (scala.None..MODULE$.equals(var10)) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Drop ", " because has not yet connected to driver"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, msg)})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var10);
      }
   }

   public void exitExecutor(final int code, final String reason, final Throwable throwable, final boolean notifyDriver) {
      if (this.stopping().compareAndSet(false, true)) {
         MessageWithContext message = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor self-exiting due to : ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)})));
         if (throwable != null) {
            this.logError(.MODULE$.from(() -> message), throwable);
         } else if (code == 0) {
            this.logInfo(.MODULE$.from(() -> message));
         } else {
            this.logError(.MODULE$.from(() -> message));
         }

         if (notifyDriver && this.driver().nonEmpty()) {
            ((RpcEndpointRef)this.driver().get()).send(new CoarseGrainedClusterMessages.RemoveExecutor(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId, new ExecutorLossReason(reason)));
         }

         this.self().send(CoarseGrainedClusterMessages.Shutdown$.MODULE$);
      } else {
         this.logInfo((Function0)(() -> "Skip exiting executor since it's been already asked to exit before."));
      }
   }

   public Throwable exitExecutor$default$3() {
      return null;
   }

   public boolean exitExecutor$default$4() {
      return true;
   }

   public void org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissionSelf() {
      if (!BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED()))) {
         this.logWarning((Function0)(() -> "Receive decommission request, but decommission feature is disabled."));
      } else if (this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned()) {
         this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Executor ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"already started decommissioning."})))).log(scala.collection.immutable.Nil..MODULE$))));
      } else {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommission executor ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId)})))));

         try {
            this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned_$eq(true);
            boolean migrationEnabled = BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_ENABLED())) && (BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED())) || BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED())));
            if (migrationEnabled) {
               this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.blockManager().decommissionBlockManager();
            } else if (BoxesRunTime.unboxToBoolean(this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_ENABLED()))) {
               this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Storage decommissioning attempted but neither "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " or "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " is enabled "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_RDD_BLOCKS_ENABLED().key())}))))));
            }

            if (this.executor() != null) {
               this.executor().decommission();
            }

            Thread shutdownThread = new Thread(migrationEnabled) {
               // $FF: synthetic field
               private final CoarseGrainedExecutorBackend $outer;
               private final boolean migrationEnabled$1;

               public void run() {
                  int sleep_time = 1000;
                  int initialSleepMillis = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.conf().getInt("spark.test.executor.decommission.initial.sleep.millis", sleep_time);
                  if (initialSleepMillis > 0) {
                     Thread.sleep((long)initialSleepMillis);
                  }

                  while(true) {
                     this.$outer.logInfo((Function0)(() -> "Checking to see if we can shutdown."));
                     if (this.$outer.executor() != null && this.$outer.executor().numRunningTasks() != 0) {
                        this.$outer.logInfo(.MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Blocked from shutdown by"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" ", " running tasks"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_TASKS..MODULE$, BoxesRunTime.boxToInteger(this.$outer.executor().numRunningTasks()))}))))));
                     } else if (this.migrationEnabled$1) {
                        this.$outer.logInfo((Function0)(() -> "No running tasks, checking migrations"));
                        Tuple2 var5 = this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env.blockManager().lastMigrationInfo();
                        if (var5 == null) {
                           throw new MatchError(var5);
                        }

                        long migrationTime = var5._1$mcJ$sp();
                        boolean allBlocksMigrated = var5._2$mcZ$sp();
                        Tuple2.mcJZ.sp var4 = new Tuple2.mcJZ.sp(migrationTime, allBlocksMigrated);
                        long migrationTimex = ((Tuple2)var4)._1$mcJ$sp();
                        boolean allBlocksMigrated = ((Tuple2)var4)._2$mcZ$sp();
                        if (allBlocksMigrated && migrationTimex > this.$outer.org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime().get()) {
                           this.$outer.logInfo((Function0)(() -> "No running tasks, all blocks migrated, stopping."));
                           int x$1 = 0;
                           String x$2 = ExecutorLossMessage$.MODULE$.decommissionFinished();
                           boolean x$3 = true;
                           Throwable x$4 = this.$outer.exitExecutor$default$3();
                           this.$outer.exitExecutor(0, x$2, x$4, true);
                        } else {
                           this.$outer.logInfo((Function0)(() -> "All blocks not yet migrated."));
                        }
                     } else {
                        this.$outer.logInfo((Function0)(() -> "No running tasks, no block migration configured, stopping."));
                        int x$5 = 0;
                        String x$6 = ExecutorLossMessage$.MODULE$.decommissionFinished();
                        boolean x$7 = true;
                        Throwable x$8 = this.$outer.exitExecutor$default$3();
                        this.$outer.exitExecutor(0, x$6, x$8, true);
                     }

                     Thread.sleep((long)sleep_time);
                  }
               }

               public {
                  if (CoarseGrainedExecutorBackend.this == null) {
                     throw null;
                  } else {
                     this.$outer = CoarseGrainedExecutorBackend.this;
                     this.migrationEnabled$1 = migrationEnabled$1;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return Class.lambdaDeserialize<invokedynamic>(var0);
               }
            };
            shutdownThread.setDaemon(true);
            shutdownThread.start();
            this.logInfo((Function0)(() -> "Will exit when finished decommissioning"));
         } catch (Exception var4) {
            this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned_$eq(false);
            this.logError((Function0)(() -> "Unexpected error while decommissioning self"), var4);
         }

      }
   }

   // $FF: synthetic method
   public static final void $anonfun$onStart$5(final CoarseGrainedExecutorBackend $this, final Try x0$1) {
      if (x0$1 instanceof Success) {
         $this.self().send(CoarseGrainedExecutorBackend.RegisteredExecutor$.MODULE$);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (x0$1 instanceof Failure) {
         Failure var4 = (Failure)x0$1;
         Throwable e = var4.exception();
         $this.exitExecutor(1, "Cannot register with driver: " + $this.driverUrl, e, false);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractLogUrls$1(final String prefix$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(prefix$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$extractAttributes$1(final String prefix$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(prefix$2);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$notifyDriverAboutPushCompletion$1(final CoarseGrainedClusterMessages.ShufflePushCompletion msg$1, final RpcEndpointRef x$1) {
      x$1.send(msg$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$onDisconnected$2(final RpcAddress remoteAddress$1, final RpcEndpointRef x$2) {
      boolean var3;
      label23: {
         RpcAddress var10000 = x$2.address();
         if (var10000 == null) {
            if (remoteAddress$1 == null) {
               break label23;
            }
         } else if (var10000.equals(remoteAddress$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public CoarseGrainedExecutorBackend(final RpcEnv rpcEnv, final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final SparkEnv env, final Option resourcesFileOpt, final ResourceProfile resourceProfile) {
      this.rpcEnv = rpcEnv;
      this.driverUrl = driverUrl;
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$executorId = executorId;
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$hostname = hostname;
      this.cores = cores;
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$env = env;
      this.resourcesFileOpt = resourcesFileOpt;
      this.resourceProfile = resourceProfile;
      RpcEndpoint.$init$(this);
      IsolatedThreadSafeRpcEndpoint.$init$(this);
      Logging.$init$(this);
      this.stopping = new AtomicBoolean(false);
      this.executor = null;
      this.driver = scala.None..MODULE$;
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$_resources = scala.Predef..MODULE$.Map().empty();
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$decommissioned = false;
      this.org$apache$spark$executor$CoarseGrainedExecutorBackend$$lastTaskFinishTime = new AtomicLong(System.nanoTime());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class RegisteredExecutor$ implements Product, Serializable {
      public static final RegisteredExecutor$ MODULE$ = new RegisteredExecutor$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "RegisteredExecutor";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof RegisteredExecutor$;
      }

      public int hashCode() {
         return 280073621;
      }

      public String toString() {
         return "RegisteredExecutor";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(RegisteredExecutor$.class);
      }
   }

   public static class Arguments implements Product, Serializable {
      private final String driverUrl;
      private final String executorId;
      private final String bindAddress;
      private final String hostname;
      private final int cores;
      private final String appId;
      private final Option workerUrl;
      private final Option resourcesFileOpt;
      private final int resourceProfileId;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String driverUrl() {
         return this.driverUrl;
      }

      public String executorId() {
         return this.executorId;
      }

      public String bindAddress() {
         return this.bindAddress;
      }

      public String hostname() {
         return this.hostname;
      }

      public int cores() {
         return this.cores;
      }

      public String appId() {
         return this.appId;
      }

      public Option workerUrl() {
         return this.workerUrl;
      }

      public Option resourcesFileOpt() {
         return this.resourcesFileOpt;
      }

      public int resourceProfileId() {
         return this.resourceProfileId;
      }

      public Arguments copy(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId) {
         return new Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId);
      }

      public String copy$default$1() {
         return this.driverUrl();
      }

      public String copy$default$2() {
         return this.executorId();
      }

      public String copy$default$3() {
         return this.bindAddress();
      }

      public String copy$default$4() {
         return this.hostname();
      }

      public int copy$default$5() {
         return this.cores();
      }

      public String copy$default$6() {
         return this.appId();
      }

      public Option copy$default$7() {
         return this.workerUrl();
      }

      public Option copy$default$8() {
         return this.resourcesFileOpt();
      }

      public int copy$default$9() {
         return this.resourceProfileId();
      }

      public String productPrefix() {
         return "Arguments";
      }

      public int productArity() {
         return 9;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.driverUrl();
            }
            case 1 -> {
               return this.executorId();
            }
            case 2 -> {
               return this.bindAddress();
            }
            case 3 -> {
               return this.hostname();
            }
            case 4 -> {
               return BoxesRunTime.boxToInteger(this.cores());
            }
            case 5 -> {
               return this.appId();
            }
            case 6 -> {
               return this.workerUrl();
            }
            case 7 -> {
               return this.resourcesFileOpt();
            }
            case 8 -> {
               return BoxesRunTime.boxToInteger(this.resourceProfileId());
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
         return x$1 instanceof Arguments;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "driverUrl";
            }
            case 1 -> {
               return "executorId";
            }
            case 2 -> {
               return "bindAddress";
            }
            case 3 -> {
               return "hostname";
            }
            case 4 -> {
               return "cores";
            }
            case 5 -> {
               return "appId";
            }
            case 6 -> {
               return "workerUrl";
            }
            case 7 -> {
               return "resourcesFileOpt";
            }
            case 8 -> {
               return "resourceProfileId";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.driverUrl()));
         var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.bindAddress()));
         var1 = Statics.mix(var1, Statics.anyHash(this.hostname()));
         var1 = Statics.mix(var1, this.cores());
         var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
         var1 = Statics.mix(var1, Statics.anyHash(this.workerUrl()));
         var1 = Statics.mix(var1, Statics.anyHash(this.resourcesFileOpt()));
         var1 = Statics.mix(var1, this.resourceProfileId());
         return Statics.finalizeHash(var1, 9);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var18;
         if (this != x$1) {
            label103: {
               if (x$1 instanceof Arguments) {
                  Arguments var4 = (Arguments)x$1;
                  if (this.cores() == var4.cores() && this.resourceProfileId() == var4.resourceProfileId()) {
                     label96: {
                        String var10000 = this.driverUrl();
                        String var5 = var4.driverUrl();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label96;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label96;
                        }

                        var10000 = this.executorId();
                        String var6 = var4.executorId();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label96;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label96;
                        }

                        var10000 = this.bindAddress();
                        String var7 = var4.bindAddress();
                        if (var10000 == null) {
                           if (var7 != null) {
                              break label96;
                           }
                        } else if (!var10000.equals(var7)) {
                           break label96;
                        }

                        var10000 = this.hostname();
                        String var8 = var4.hostname();
                        if (var10000 == null) {
                           if (var8 != null) {
                              break label96;
                           }
                        } else if (!var10000.equals(var8)) {
                           break label96;
                        }

                        var10000 = this.appId();
                        String var9 = var4.appId();
                        if (var10000 == null) {
                           if (var9 != null) {
                              break label96;
                           }
                        } else if (!var10000.equals(var9)) {
                           break label96;
                        }

                        Option var16 = this.workerUrl();
                        Option var10 = var4.workerUrl();
                        if (var16 == null) {
                           if (var10 != null) {
                              break label96;
                           }
                        } else if (!var16.equals(var10)) {
                           break label96;
                        }

                        var16 = this.resourcesFileOpt();
                        Option var11 = var4.resourcesFileOpt();
                        if (var16 == null) {
                           if (var11 != null) {
                              break label96;
                           }
                        } else if (!var16.equals(var11)) {
                           break label96;
                        }

                        if (var4.canEqual(this)) {
                           break label103;
                        }
                     }
                  }
               }

               var18 = false;
               return var18;
            }
         }

         var18 = true;
         return var18;
      }

      public Arguments(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId) {
         this.driverUrl = driverUrl;
         this.executorId = executorId;
         this.bindAddress = bindAddress;
         this.hostname = hostname;
         this.cores = cores;
         this.appId = appId;
         this.workerUrl = workerUrl;
         this.resourcesFileOpt = resourcesFileOpt;
         this.resourceProfileId = resourceProfileId;
         Product.$init$(this);
      }
   }

   public static class Arguments$ extends AbstractFunction9 implements Serializable {
      public static final Arguments$ MODULE$ = new Arguments$();

      public final String toString() {
         return "Arguments";
      }

      public Arguments apply(final String driverUrl, final String executorId, final String bindAddress, final String hostname, final int cores, final String appId, final Option workerUrl, final Option resourcesFileOpt, final int resourceProfileId) {
         return new Arguments(driverUrl, executorId, bindAddress, hostname, cores, appId, workerUrl, resourcesFileOpt, resourceProfileId);
      }

      public Option unapply(final Arguments x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple9(x$0.driverUrl(), x$0.executorId(), x$0.bindAddress(), x$0.hostname(), BoxesRunTime.boxToInteger(x$0.cores()), x$0.appId(), x$0.workerUrl(), x$0.resourcesFileOpt(), BoxesRunTime.boxToInteger(x$0.resourceProfileId()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Arguments$.class);
      }
   }
}
