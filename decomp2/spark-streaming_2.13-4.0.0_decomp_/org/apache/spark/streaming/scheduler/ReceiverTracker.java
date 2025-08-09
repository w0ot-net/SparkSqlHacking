package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SimpleFutureAction;
import org.apache.spark.SparkException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.scheduler.ExecutorCacheTaskLocation;
import org.apache.spark.scheduler.TaskLocation;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.ReceiverInputDStream;
import org.apache.spark.streaming.receiver.CleanupOldBlocks;
import org.apache.spark.streaming.receiver.Receiver;
import org.apache.spark.streaming.receiver.ReceiverSupervisorImpl;
import org.apache.spark.streaming.receiver.StopReceiver$;
import org.apache.spark.streaming.receiver.UpdateRateLimit;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import org.apache.spark.util.CallSite;
import org.apache.spark.util.SerializableConfiguration;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.HashMap;
import scala.concurrent.ExecutionContextExecutorService;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\ree!\u0002)R\u0001M[\u0006\u0002\u00035\u0001\u0005\u0003\u0005\u000b\u0011\u00026\t\u00119\u0004!\u0011!Q\u0001\n=DQA\u001d\u0001\u0005\u0002MDq\u0001\u001f\u0001C\u0002\u0013%\u0011\u0010C\u0004\u0002(\u0001\u0001\u000b\u0011\u0002>\t\u0013\u0005%\u0002A1A\u0005\n\u0005-\u0002\u0002CA\u001b\u0001\u0001\u0006I!!\f\t\u0013\u0005]\u0002A1A\u0005\n\u0005e\u0002\u0002CA!\u0001\u0001\u0006I!a\u000f\t\u0013\u0005\r\u0003A1A\u0005\n\u0005\u0015\u0003\u0002CA'\u0001\u0001\u0006I!a\u0012\b\u000f\u0005=\u0003\u0001#\u0001\u0002R\u00199\u0011Q\u000b\u0001\t\u0002\u0005]\u0003B\u0002:\u000e\t\u0003\ty&\u0002\u0004\u0002V5\u0001\u0011\u0011\r\u0005\n\u0003Sj!\u0019!C\u0001\u0003WB\u0001\"!\u001c\u000eA\u0003%\u0011\u0011\r\u0005\n\u0003_j!\u0019!C\u0001\u0003WB\u0001\"!\u001d\u000eA\u0003%\u0011\u0011\r\u0005\n\u0003gj!\u0019!C\u0001\u0003WB\u0001\"!\u001e\u000eA\u0003%\u0011\u0011\r\u0005\n\u0003oj!\u0019!C\u0001\u0003WB\u0001\"!\u001f\u000eA\u0003%\u0011\u0011\r\u0005\n\u0003w\u0002\u0001\u0019!C\u0005\u0003{B\u0011\"a!\u0001\u0001\u0004%I!!\"\t\u0011\u0005E\u0005\u0001)Q\u0005\u0003\u007fB\u0011\"a'\u0001\u0001\u0004%I!!(\t\u0013\u0005-\u0006\u00011A\u0005\n\u00055\u0006\u0002CAY\u0001\u0001\u0006K!a(\t\u0013\u0005M\u0006A1A\u0005\n\u0005U\u0006\u0002CA_\u0001\u0001\u0006I!a.\t\u0013\u0005}\u0006A1A\u0005\n\u0005\u0005\u0007\u0002CAl\u0001\u0001\u0006I!a1\t\u0013\u0005e\u0007A1A\u0005\n\u0005m\u0007\u0002CAz\u0001\u0001\u0006I!!8\t\u0013\u0005U\bA1A\u0005\n\u0005]\b\u0002\u0003B\f\u0001\u0001\u0006I!!?\t\u000f\te\u0001\u0001\"\u0001\u0003\u001c!9!Q\u0004\u0001\u0005\u0002\t}\u0001b\u0002B\u0013\u0001\u0011\u0005!q\u0005\u0005\b\u0005g\u0001A\u0011\u0001B\u001b\u0011\u001d\u00119\u0006\u0001C\u0001\u00053BqA!\u0019\u0001\t\u0003\u0011\u0019\u0007C\u0004\u0003j\u0001!\tAa\u001b\t\u000f\t=\u0004\u0001\"\u0001\u0003r!9!1\u000f\u0001\u0005\n\tU\u0004b\u0002BJ\u0001\u0011%!Q\u0013\u0005\b\u0005C\u0003A\u0011\u0001BR\u0011\u001d\u0011\u0019\f\u0001C\u0005\u0005kCqAa/\u0001\t\u0013\u0011i\fC\u0004\u0003F\u0002!IAa2\t\u000f\te\u0007\u0001\"\u0003\u0003\\\"9!1\u001d\u0001\u0005\u0002\t\u0015\bb\u0002Bt\u0001\u0011%!\u0011\u001e\u0005\b\u0005g\u0004A\u0011\u0002B\u000e\u0011\u001d\u0011)\u0010\u0001C\u0005\u00057AqAa>\u0001\t\u0013\u0011)\u000fC\u0004\u0003z\u0002!IA!:\t\u000f\tm\b\u0001\"\u0003\u0003f\u001a1!Q \u0001\u0005\u0005\u007fD!ba\u0002=\u0005\u000b\u0007I\u0011IB\u0005\u0011)\u0019\t\u0002\u0010B\u0001B\u0003%11\u0002\u0005\u0007er\"\taa\u0005\t\u0013\reAH1A\u0005\n\rm\u0001\u0002CB\u0014y\u0001\u0006Ia!\b\t\u0013\r%B\b1A\u0005\n\t\u0015\b\"CB\u0016y\u0001\u0007I\u0011BB\u0017\u0011\u001d\u0019\t\u0004\u0010Q!\n=Dqa!\u000e=\t\u0003\u001a9\u0004C\u0004\u0004@q\"\te!\u0011\t\u000f\r5C\b\"\u0003\u0004P!911\u000b\u001f\u0005\n\rU\u0003bBB8y\u0011\u0005#1\u0004\u0005\b\u0007cbD\u0011BB:\u0011\u001d\u00199\b\u0010C\u0005\u000579!b!\u001fR\u0003\u0003E\taUB>\r%\u0001\u0016+!A\t\u0002M\u001bi\b\u0003\u0004s\u001b\u0012\u00051q\u0010\u0005\n\u0007\u0003k\u0015\u0013!C\u0001\u0007\u0007\u0013qBU3dK&4XM\u001d+sC\u000e\\WM\u001d\u0006\u0003%N\u000b\u0011b]2iK\u0012,H.\u001a:\u000b\u0005Q+\u0016!C:ue\u0016\fW.\u001b8h\u0015\t1v+A\u0003ta\u0006\u00148N\u0003\u0002Y3\u00061\u0011\r]1dQ\u0016T\u0011AW\u0001\u0004_J<7c\u0001\u0001]EB\u0011Q\fY\u0007\u0002=*\tq,A\u0003tG\u0006d\u0017-\u0003\u0002b=\n1\u0011I\\=SK\u001a\u0004\"a\u00194\u000e\u0003\u0011T!!Z+\u0002\u0011%tG/\u001a:oC2L!a\u001a3\u0003\u000f1{wmZ5oO\u0006\u00191o]2\u0004\u0001A\u00111\u000e\\\u0007\u0002'&\u0011Qn\u0015\u0002\u0011'R\u0014X-Y7j]\u001e\u001cuN\u001c;fqR\f!c]6jaJ+7-Z5wKJd\u0015-\u001e8dQB\u0011Q\f]\u0005\u0003cz\u0013qAQ8pY\u0016\fg.\u0001\u0004=S:LGO\u0010\u000b\u0004iZ<\bCA;\u0001\u001b\u0005\t\u0006\"\u00025\u0004\u0001\u0004Q\u0007b\u00028\u0004!\u0003\u0005\ra\\\u0001\u0015e\u0016\u001cW-\u001b<fe&s\u0007/\u001e;TiJ,\u0017-\\:\u0016\u0003i\u00042!X>~\u0013\tahLA\u0003BeJ\f\u0017\u0010M\u0002\u007f\u0003\u001b\u0001Ra`A\u0003\u0003\u0013i!!!\u0001\u000b\u0007\u0005\r1+A\u0004egR\u0014X-Y7\n\t\u0005\u001d\u0011\u0011\u0001\u0002\u0015%\u0016\u001cW-\u001b<fe&s\u0007/\u001e;E'R\u0014X-Y7\u0011\t\u0005-\u0011Q\u0002\u0007\u0001\t-\ty\u0001AA\u0001\u0002\u0003\u0015\t!!\u0007\u0003\u0007}#\u0003(\u0003\u0003\u0002\u0014\u0005U\u0011aF4fiJ+7-Z5wKJLe\u000e];u'R\u0014X-Y7t\u0013\r\t9b\u0015\u0002\r\tN#(/Z1n\u000fJ\f\u0007\u000f[\t\u0005\u00037\t\t\u0003E\u0002^\u0003;I1!a\b_\u0005\u001dqu\u000e\u001e5j]\u001e\u00042!XA\u0012\u0013\r\t)C\u0018\u0002\u0004\u0003:L\u0018!\u0006:fG\u0016Lg/\u001a:J]B,Ho\u0015;sK\u0006l7\u000fI\u0001\u0017e\u0016\u001cW-\u001b<fe&s\u0007/\u001e;TiJ,\u0017-\\%egV\u0011\u0011Q\u0006\t\u0005;n\fy\u0003E\u0002^\u0003cI1!a\r_\u0005\rIe\u000e^\u0001\u0018e\u0016\u001cW-\u001b<fe&s\u0007/\u001e;TiJ,\u0017-\\%eg\u0002\nAC]3dK&4X\r\u001a\"m_\u000e\\GK]1dW\u0016\u0014XCAA\u001e!\r)\u0018QH\u0005\u0004\u0003\u007f\t&\u0001\u0006*fG\u0016Lg/\u001a3CY>\u001c7\u000e\u0016:bG.,'/A\u000bsK\u000e,\u0017N^3e\u00052|7m\u001b+sC\u000e\\WM\u001d\u0011\u0002\u00171L7\u000f^3oKJ\u0014Uo]\u000b\u0003\u0003\u000f\u00022!^A%\u0013\r\tY%\u0015\u0002\u0015'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014()^:\u0002\u00191L7\u000f^3oKJ\u0014Uo\u001d\u0011\u0002\u0019Q\u0013\u0018mY6feN#\u0018\r^3\u0011\u0007\u0005MS\"D\u0001\u0001\u00051!&/Y2lKJ\u001cF/\u0019;f'\ri\u0011\u0011\f\t\u0004;\u0006m\u0013bAA/=\nYQI\\;nKJ\fG/[8o)\t\t\t\u0006\u0005\u0003\u0002d\u0005\u0015T\"A\u0007\n\t\u0005\u001d\u00141\f\u0002\u0006-\u0006dW/Z\u0001\f\u0013:LG/[1mSj,G-\u0006\u0002\u0002b\u0005a\u0011J\\5uS\u0006d\u0017N_3eA\u000591\u000b^1si\u0016$\u0017\u0001C*uCJ$X\r\u001a\u0011\u0002\u0011M#x\u000e\u001d9j]\u001e\f\u0011b\u0015;paBLgn\u001a\u0011\u0002\u000fM#x\u000e\u001d9fI\u0006A1\u000b^8qa\u0016$\u0007%\u0001\u0007ue\u0006\u001c7.\u001a:Ti\u0006$X-\u0006\u0002\u0002\u0000A!\u0011\u0011QA3\u001d\r\t\u0019\u0006D\u0001\u0011iJ\f7m[3s'R\fG/Z0%KF$B!a\"\u0002\u000eB\u0019Q,!#\n\u0007\u0005-eL\u0001\u0003V]&$\b\"CAH3\u0005\u0005\t\u0019AA@\u0003\rAH%M\u0001\u000eiJ\f7m[3s'R\fG/\u001a\u0011)\u0007i\t)\nE\u0002^\u0003/K1!!'_\u0005!1x\u000e\\1uS2,\u0017\u0001C3oIB|\u0017N\u001c;\u0016\u0005\u0005}\u0005\u0003BAQ\u0003Ok!!a)\u000b\u0007\u0005\u0015V+A\u0002sa\u000eLA!!+\u0002$\nq!\u000b]2F]\u0012\u0004x.\u001b8u%\u00164\u0017\u0001D3oIB|\u0017N\u001c;`I\u0015\fH\u0003BAD\u0003_C\u0011\"a$\u001d\u0003\u0003\u0005\r!a(\u0002\u0013\u0015tG\r]8j]R\u0004\u0013\u0001E:dQ\u0016$W\u000f\\5oOB{G.[2z+\t\t9\fE\u0002v\u0003sK1!a/R\u0005a\u0011VmY3jm\u0016\u00148k\u00195fIVd\u0017N\\4Q_2L7-_\u0001\u0012g\u000eDW\rZ;mS:<\u0007k\u001c7jGf\u0004\u0013\u0001\u0006:fG\u0016Lg/\u001a:K_\n,\u00050\u001b;MCR\u001c\u0007.\u0006\u0002\u0002DB!\u0011QYAj\u001b\t\t9M\u0003\u0003\u0002J\u0006-\u0017AC2p]\u000e,(O]3oi*!\u0011QZAh\u0003\u0011)H/\u001b7\u000b\u0005\u0005E\u0017\u0001\u00026bm\u0006LA!!6\u0002H\nq1i\\;oi\u0012{wO\u001c'bi\u000eD\u0017!\u0006:fG\u0016Lg/\u001a:K_\n,\u00050\u001b;MCR\u001c\u0007\u000eI\u0001\u0016e\u0016\u001cW-\u001b<feR\u0013\u0018mY6j]\u001eLeNZ8t+\t\ti\u000e\u0005\u0005\u0002`\u0006%\u0018qFAw\u001b\t\t\tO\u0003\u0003\u0002d\u0006\u0015\u0018aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003Ot\u0016AC2pY2,7\r^5p]&!\u00111^Aq\u0005\u001dA\u0015m\u001d5NCB\u00042!^Ax\u0013\r\t\t0\u0015\u0002\u0015%\u0016\u001cW-\u001b<feR\u0013\u0018mY6j]\u001eLeNZ8\u0002-I,7-Z5wKJ$&/Y2lS:<\u0017J\u001c4pg\u0002\n!D]3dK&4XM\u001d)sK\u001a,'O]3e\u0019>\u001c\u0017\r^5p]N,\"!!?\u0011\u0011\u0005}\u0017\u0011^A\u0018\u0003w\u0004R!XA\u007f\u0005\u0003I1!a@_\u0005\u0019y\u0005\u000f^5p]B!!1\u0001B\t\u001d\u0011\u0011)A!\u0004\u0011\u0007\t\u001da,\u0004\u0002\u0003\n)\u0019!1B5\u0002\rq\u0012xn\u001c;?\u0013\r\u0011yAX\u0001\u0007!J,G-\u001a4\n\t\tM!Q\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\t=a,A\u000esK\u000e,\u0017N^3s!J,g-\u001a:sK\u0012dunY1uS>t7\u000fI\u0001\u0006gR\f'\u000f\u001e\u000b\u0003\u0003\u000f\u000bAa\u001d;paR!\u0011q\u0011B\u0011\u0011\u0019\u0011\u0019c\na\u0001_\u0006AqM]1dK\u001a,H.A\u000bbY2|7-\u0019;f\u00052|7m[:U_\n\u000bGo\u00195\u0015\t\u0005\u001d%\u0011\u0006\u0005\b\u0005WA\u0003\u0019\u0001B\u0017\u0003%\u0011\u0017\r^2i)&lW\rE\u0002l\u0005_I1A!\rT\u0005\u0011!\u0016.\\3\u0002!\u001d,GO\u00117pG.\u001cxJ\u001a\"bi\u000eDG\u0003\u0002B\u001c\u0005+\u0002\u0002Ba\u0001\u0003:\u0005=\"QH\u0005\u0005\u0005w\u0011)BA\u0002NCB\u0004bAa\u0010\u0003J\t=c\u0002\u0002B!\u0005\u000brAAa\u0002\u0003D%\tq,C\u0002\u0003Hy\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0003L\t5#aA*fc*\u0019!q\t0\u0011\u0007U\u0014\t&C\u0002\u0003TE\u0013\u0011CU3dK&4X\r\u001a\"m_\u000e\\\u0017J\u001c4p\u0011\u001d\u0011Y#\u000ba\u0001\u0005[\t\u0011dZ3u\u00052|7m[:PM\n\u000bGo\u00195B]\u0012\u001cFO]3b[R1!Q\bB.\u0005;BqAa\u000b+\u0001\u0004\u0011i\u0003C\u0004\u0003`)\u0002\r!a\f\u0002\u0011M$(/Z1n\u0013\u0012\f!d\u00197fC:,\bo\u00147e\u00052|7m[:B]\u0012\u0014\u0015\r^2iKN$B!a\"\u0003f!9!qM\u0016A\u0002\t5\u0012!E2mK\u0006tW\u000f\u001d+ie\u0016\u001c\b\u000eV5nK\u0006\u0011\u0012\r\u001c7pG\u0006$X\rZ#yK\u000e,Ho\u001c:t)\t\u0011i\u0007\u0005\u0005\u0003\u0004\te\u0012qFA~\u00031qW/\u001c*fG\u0016Lg/\u001a:t)\t\ty#\u0001\tsK\u001eL7\u000f^3s%\u0016\u001cW-\u001b<feRiqNa\u001e\u0003z\tu$\u0011\u0011BC\u0005\u0013CqAa\u0018/\u0001\u0004\ty\u0003C\u0004\u0003|9\u0002\rA!\u0001\u0002\u0007QL\b\u000fC\u0004\u0003\u00009\u0002\rA!\u0001\u0002\t!|7\u000f\u001e\u0005\b\u0005\u0007s\u0003\u0019\u0001B\u0001\u0003))\u00070Z2vi>\u0014\u0018\n\u001a\u0005\b\u0005\u000fs\u0003\u0019AAP\u0003A\u0011XmY3jm\u0016\u0014XI\u001c3q_&tG\u000fC\u0004\u0003\f:\u0002\rA!$\u0002\u001bM,g\u000eZ3s\u0003\u0012$'/Z:t!\u0011\t\tKa$\n\t\tE\u00151\u0015\u0002\u000b%B\u001c\u0017\t\u001a3sKN\u001c\u0018A\u00053fe\u0016<\u0017n\u001d;feJ+7-Z5wKJ$\u0002\"a\"\u0003\u0018\ne%Q\u0014\u0005\b\u0005?z\u0003\u0019AA\u0018\u0011\u001d\u0011Yj\fa\u0001\u0005\u0003\tq!\\3tg\u0006<W\rC\u0004\u0003 >\u0002\rA!\u0001\u0002\u000b\u0015\u0014(o\u001c:\u0002\u001dM,g\u000e\u001a*bi\u0016,\u0006\u000fZ1uKR1\u0011q\u0011BS\u0005SCqAa*1\u0001\u0004\ty#A\u0005tiJ,\u0017-\\+J\t\"9!1\u0016\u0019A\u0002\t5\u0016a\u00028foJ\u000bG/\u001a\t\u0004;\n=\u0016b\u0001BY=\n!Aj\u001c8h\u0003!\tG\r\u001a\"m_\u000e\\GcA8\u00038\"9!\u0011X\u0019A\u0002\t=\u0013!\u0005:fG\u0016Lg/\u001a3CY>\u001c7.\u00138g_\u0006Y!/\u001a9peR,%O]8s)!\t9Ia0\u0003B\n\r\u0007b\u0002B0e\u0001\u0007\u0011q\u0006\u0005\b\u00057\u0013\u0004\u0019\u0001B\u0001\u0011\u001d\u0011yJ\ra\u0001\u0005\u0003\t\u0001c]2iK\u0012,H.\u001a*fG\u0016Lg/\u001a:\u0015\t\t%'Q\u001b\t\u0007\u0005\u007f\u0011IEa3\u0011\t\t5'\u0011[\u0007\u0003\u0005\u001fT!AU+\n\t\tM'q\u001a\u0002\r)\u0006\u001c8\u000eT8dCRLwN\u001c\u0005\b\u0005/\u001c\u0004\u0019AA\u0018\u0003)\u0011XmY3jm\u0016\u0014\u0018\nZ\u0001!kB$\u0017\r^3SK\u000e,\u0017N^3s'\u000eDW\rZ;mK\u0012,\u00050Z2vi>\u00148\u000f\u0006\u0004\u0002\b\nu'q\u001c\u0005\b\u0005/$\u0004\u0019AA\u0018\u0011\u001d\u0011\t\u000f\u000ea\u0001\u0005\u0013\f!c]2iK\u0012,H.\u001a3M_\u000e\fG/[8og\u0006!\u0002.Y:V]\u0006dGn\\2bi\u0016$'\t\\8dWN,\u0012a\\\u0001\rO\u0016$X\t_3dkR|'o]\u000b\u0003\u0005W\u0004bAa\u0010\u0003J\t5\b\u0003\u0002Bg\u0005_LAA!=\u0003P\nIR\t_3dkR|'oQ1dQ\u0016$\u0016m]6M_\u000e\fG/[8o\u0003A\u0011XO\u001c#v[6L8\u000b]1sW*{'-A\bmCVt7\r\u001b*fG\u0016Lg/\u001a:t\u0003AI7\u000f\u0016:bG.,'o\u0015;beR,G-A\tjgR\u0013\u0018mY6feN#x\u000e\u001d9j]\u001e\f\u0001#[:Ue\u0006\u001c7.\u001a:Ti>\u0004\b/\u001a3\u0003/I+7-Z5wKJ$&/Y2lKJ,e\u000e\u001a9pS:$8\u0003\u0002\u001f]\u0007\u0003\u0001B!!)\u0004\u0004%!1QAAR\u0005U!\u0006N]3bIN\u000bg-\u001a*qG\u0016sG\r]8j]R\faA\u001d9d\u000b:4XCAB\u0006!\u0011\t\tk!\u0004\n\t\r=\u00111\u0015\u0002\u0007%B\u001cWI\u001c<\u0002\u000fI\u00048-\u00128wAQ!1QCB\f!\r\t\u0019\u0006\u0010\u0005\b\u0007\u000fy\u0004\u0019AB\u0006\u0003U9\u0018\r\u001c\"bi\u000eD\u0017N\\4UQJ,\u0017\r\u001a)p_2,\"a!\b\u0011\t\r}11E\u0007\u0003\u0007CQ1!!3_\u0013\u0011\u0019)c!\t\u0003?\u0015CXmY;uS>t7i\u001c8uKb$X\t_3dkR|'oU3sm&\u001cW-\u0001\fxC2\u0014\u0015\r^2iS:<G\u000b\u001b:fC\u0012\u0004vn\u001c7!\u0003\u0019\t7\r^5wK\u0006Q\u0011m\u0019;jm\u0016|F%Z9\u0015\t\u0005\u001d5q\u0006\u0005\t\u0003\u001f\u001b\u0015\u0011!a\u0001_\u00069\u0011m\u0019;jm\u0016\u0004\u0003f\u0001#\u0002\u0016\u00069!/Z2fSZ,WCAB\u001d!\u001di61HA\u0011\u0003\u000fK1a!\u0010_\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0017a\u0004:fG\u0016Lg/Z!oIJ+\u0007\u000f\\=\u0015\t\re21\t\u0005\b\u0007\u000b2\u0005\u0019AB$\u0003\u001d\u0019wN\u001c;fqR\u0004B!!)\u0004J%!11JAR\u00059\u0011\u0006oY\"bY2\u001cuN\u001c;fqR\f1dZ3u'R|'/\u001a3TG\",G-\u001e7fI\u0016CXmY;u_J\u001cH\u0003\u0002Be\u0007#BqAa6H\u0001\u0004\ty#A\u0007ti\u0006\u0014HOU3dK&4XM\u001d\u000b\u0007\u0003\u000f\u001b9f!\u001c\t\u000f\re\u0003\n1\u0001\u0004\\\u0005A!/Z2fSZ,'\u000f\r\u0003\u0004^\r%\u0004CBB0\u0007G\u001a9'\u0004\u0002\u0004b)\u00191\u0011L*\n\t\r\u00154\u0011\r\u0002\t%\u0016\u001cW-\u001b<feB!\u00111BB5\t1\u0019Yga\u0016\u0002\u0002\u0003\u0005)\u0011AA\r\u0005\ryFe\r\u0005\b\u0005CD\u0005\u0019\u0001Be\u0003\u0019ygn\u0015;pa\u0006\u0019rN\u001c*fG\u0016Lg/\u001a:K_\n4\u0015N\\5tQR!\u0011qQB;\u0011\u001d\u00119N\u0013a\u0001\u0003_\tQb\u001d;paJ+7-Z5wKJ\u001c\u0018a\u0004*fG\u0016Lg/\u001a:Ue\u0006\u001c7.\u001a:\u0011\u0005Ul5CA'])\t\u0019Y(A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0003\u0007\u000bS3a\\BDW\t\u0019I\t\u0005\u0003\u0004\f\u000eUUBABG\u0015\u0011\u0019yi!%\u0002\u0013Ut7\r[3dW\u0016$'bABJ=\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\r]5Q\u0012\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0007"
)
public class ReceiverTracker implements Logging {
   private volatile TrackerState$ TrackerState$module;
   public final StreamingContext org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc;
   private final boolean skipReceiverLaunch;
   private final ReceiverInputDStream[] receiverInputStreams;
   private final int[] receiverInputStreamIds;
   private final ReceivedBlockTracker receivedBlockTracker;
   private final StreamingListenerBus listenerBus;
   private volatile Enumeration.Value trackerState;
   private RpcEndpointRef endpoint;
   private final ReceiverSchedulingPolicy org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy;
   private final CountDownLatch org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch;
   private final HashMap org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos;
   private final HashMap org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static boolean $lessinit$greater$default$2() {
      return ReceiverTracker$.MODULE$.$lessinit$greater$default$2();
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

   public TrackerState$ TrackerState() {
      if (this.TrackerState$module == null) {
         this.TrackerState$lzycompute$1();
      }

      return this.TrackerState$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ReceiverInputDStream[] receiverInputStreams() {
      return this.receiverInputStreams;
   }

   private int[] receiverInputStreamIds() {
      return this.receiverInputStreamIds;
   }

   private ReceivedBlockTracker receivedBlockTracker() {
      return this.receivedBlockTracker;
   }

   private StreamingListenerBus listenerBus() {
      return this.listenerBus;
   }

   private Enumeration.Value trackerState() {
      return this.trackerState;
   }

   private void trackerState_$eq(final Enumeration.Value x$1) {
      this.trackerState = x$1;
   }

   private RpcEndpointRef endpoint() {
      return this.endpoint;
   }

   private void endpoint_$eq(final RpcEndpointRef x$1) {
      this.endpoint = x$1;
   }

   public ReceiverSchedulingPolicy org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy() {
      return this.org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy;
   }

   public CountDownLatch org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch() {
      return this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch;
   }

   public HashMap org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos() {
      return this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos;
   }

   public HashMap org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations() {
      return this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations;
   }

   public synchronized void start() {
      if (this.isTrackerStarted()) {
         throw new SparkException("ReceiverTracker already started");
      } else if (!.MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(this.receiverInputStreams()))) {
         this.endpoint_$eq(this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.env().rpcEnv().setupEndpoint("ReceiverTracker", new ReceiverTrackerEndpoint(this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.env().rpcEnv())));
         if (!this.skipReceiverLaunch) {
            this.launchReceivers();
         }

         this.logInfo((Function0)(() -> "ReceiverTracker started"));
         this.trackerState_$eq(this.TrackerState().Started());
      }
   }

   public synchronized void stop(final boolean graceful) {
      boolean isStarted = this.isTrackerStarted();
      this.trackerState_$eq(this.TrackerState().Stopping());
      if (isStarted) {
         if (!this.skipReceiverLaunch) {
            this.endpoint().askSync(StopAllReceivers$.MODULE$, scala.reflect.ClassTag..MODULE$.Boolean());
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch().await(10L, TimeUnit.SECONDS);
            if (graceful) {
               this.logInfo((Function0)(() -> "Waiting for receiver job to terminate gracefully"));
               this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch().await();
               this.logInfo((Function0)(() -> "Waited for receiver job to terminate gracefully"));
            }

            Seq receivers = (Seq)this.endpoint().askSync(AllReceiverIds$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(Seq.class));
            if (receivers.nonEmpty()) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not all of the receivers have deregistered, "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECEIVER_IDS..MODULE$, receivers)}))))));
            } else {
               this.logInfo((Function0)(() -> "All of the receivers have deregistered successfully"));
            }
         }

         this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.env().rpcEnv().stop(this.endpoint());
         this.endpoint_$eq((RpcEndpointRef)null);
      }

      this.receivedBlockTracker().stop();
      this.logInfo((Function0)(() -> "ReceiverTracker stopped"));
      this.trackerState_$eq(this.TrackerState().Stopped());
   }

   public void allocateBlocksToBatch(final Time batchTime) {
      if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(this.receiverInputStreams()))) {
         this.receivedBlockTracker().allocateBlocksToBatch(batchTime);
      }
   }

   public scala.collection.immutable.Map getBlocksOfBatch(final Time batchTime) {
      return this.receivedBlockTracker().getBlocksOfBatch(batchTime);
   }

   public Seq getBlocksOfBatchAndStream(final Time batchTime, final int streamId) {
      return this.receivedBlockTracker().getBlocksOfBatchAndStream(batchTime, streamId);
   }

   public void cleanupOldBlocksAndBatches(final Time cleanupThreshTime) {
      this.receivedBlockTracker().cleanupOldBatches(cleanupThreshTime, false);
      if (WriteAheadLogUtils$.MODULE$.enableReceiverLog(this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.conf())) {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cleanup old received batch data: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLEANUP_LOCAL_DIRS..MODULE$, cleanupThreshTime)}))))));
         synchronized(this){}

         try {
            if (this.isTrackerStarted()) {
               this.endpoint().send(new CleanupOldBlocks(cleanupThreshTime));
            }
         } catch (Throwable var4) {
            throw var4;
         }

      }
   }

   public synchronized scala.collection.immutable.Map allocatedExecutors() {
      return this.isTrackerStarted() ? (scala.collection.immutable.Map)((MapOps)this.endpoint().askSync(GetAllReceiverInfo$.MODULE$, scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class))).transform((x$2, v) -> $anonfun$allocatedExecutors$1(BoxesRunTime.unboxToInt(x$2), v)) : scala.Predef..MODULE$.Map().empty();
   }

   public int numReceivers() {
      return this.receiverInputStreams().length;
   }

   public boolean org$apache$spark$streaming$scheduler$ReceiverTracker$$registerReceiver(final int streamId, final String typ, final String host, final String executorId, final RpcEndpointRef receiverEndpoint, final RpcAddress senderAddress) {
      if (!.MODULE$.contains$extension(scala.Predef..MODULE$.intArrayOps(this.receiverInputStreamIds()), BoxesRunTime.boxToInteger(streamId))) {
         throw new SparkException("Register received for unexpected id " + streamId);
      } else if (!this.org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopping() && !this.org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopped()) {
         Option scheduledLocations = ((ReceiverTrackingInfo)this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().apply(BoxesRunTime.boxToInteger(streamId))).scheduledLocations();
         Seq acceptableExecutors = scheduledLocations.nonEmpty() ? (Seq)scheduledLocations.get() : this.scheduleReceiver(streamId);
         if (!isAcceptable$1(acceptableExecutors, executorId, host)) {
            return false;
         } else {
            String name = typ + "-" + streamId;
            ReceiverTrackingInfo receiverTrackingInfo = new ReceiverTrackingInfo(streamId, ReceiverState$.MODULE$.ACTIVE(), scala.None..MODULE$, new Some(new ExecutorCacheTaskLocation(host, executorId)), new Some(name), new Some(receiverEndpoint), ReceiverTrackingInfo$.MODULE$.apply$default$7());
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().put(BoxesRunTime.boxToInteger(streamId), receiverTrackingInfo);
            this.listenerBus().post(new StreamingListenerReceiverStarted(receiverTrackingInfo.toReceiverInfo()));
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Registered receiver for stream ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(streamId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"from ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ADDRESS..MODULE$, senderAddress)}))))));
            return true;
         }
      } else {
         return false;
      }
   }

   public void org$apache$spark$streaming$scheduler$ReceiverTracker$$deregisterReceiver(final int streamId, final String message, final String error) {
      long var10000;
      label45: {
         if (error != null) {
            label44: {
               String var7 = "";
               if (error == null) {
                  if (var7 == null) {
                     break label44;
                  }
               } else if (error.equals(var7)) {
                  break label44;
               }

               var10000 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.scheduler().clock().getTimeMillis();
               break label45;
            }
         }

         var10000 = -1L;
      }

      long lastErrorTime = var10000;
      ReceiverErrorInfo errorInfo = new ReceiverErrorInfo(message, error, lastErrorTime);
      Option var10 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().get(BoxesRunTime.boxToInteger(streamId));
      ReceiverTrackingInfo var21;
      if (var10 instanceof Some var11) {
         ReceiverTrackingInfo oldInfo = (ReceiverTrackingInfo)var11.value();
         Enumeration.Value x$1 = ReceiverState$.MODULE$.INACTIVE();
         Some x$2 = new Some(errorInfo);
         int x$3 = oldInfo.copy$default$1();
         Option x$4 = oldInfo.copy$default$3();
         Option x$5 = oldInfo.copy$default$4();
         Option x$6 = oldInfo.copy$default$5();
         Option x$7 = oldInfo.copy$default$6();
         var21 = oldInfo.copy(x$3, x$1, x$4, x$5, x$6, x$7, x$2);
      } else {
         if (!scala.None..MODULE$.equals(var10)) {
            throw new MatchError(var10);
         }

         this.logWarning((Function0)(() -> "No prior receiver info"));
         var21 = new ReceiverTrackingInfo(streamId, ReceiverState$.MODULE$.INACTIVE(), scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, new Some(errorInfo));
      }

      ReceiverTrackingInfo newReceiverTrackingInfo = var21;
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().update(BoxesRunTime.boxToInteger(streamId), newReceiverTrackingInfo);
      this.listenerBus().post(new StreamingListenerReceiverStopped(newReceiverTrackingInfo.toReceiverInfo()));
      String messageWithError = error != null && !error.isEmpty() ? message + " - " + error : String.valueOf(message);
      this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Deregistered receiver for stream ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(streamId))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, messageWithError)}))))));
   }

   public synchronized void sendRateUpdate(final int streamUID, final long newRate) {
      if (this.isTrackerStarted()) {
         this.endpoint().send(new UpdateReceiverRateLimit(streamUID, newRate));
      }
   }

   public boolean org$apache$spark$streaming$scheduler$ReceiverTracker$$addBlock(final ReceivedBlockInfo receivedBlockInfo) {
      return this.receivedBlockTracker().addBlock(receivedBlockInfo);
   }

   public void org$apache$spark$streaming$scheduler$ReceiverTracker$$reportError(final int streamId, final String message, final String error) {
      Option var6 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().get(BoxesRunTime.boxToInteger(streamId));
      ReceiverTrackingInfo var10000;
      if (var6 instanceof Some var7) {
         ReceiverTrackingInfo oldInfo = (ReceiverTrackingInfo)var7.value();
         ReceiverErrorInfo errorInfo = new ReceiverErrorInfo(message, error, BoxesRunTime.unboxToLong(oldInfo.errorInfo().map((x$4x) -> BoxesRunTime.boxToLong($anonfun$reportError$1(x$4x))).getOrElse((JFunction0.mcJ.sp)() -> -1L)));
         Some x$1 = new Some(errorInfo);
         int x$2 = oldInfo.copy$default$1();
         Enumeration.Value x$3 = oldInfo.copy$default$2();
         Option x$4 = oldInfo.copy$default$3();
         Option x$5 = oldInfo.copy$default$4();
         Option x$6 = oldInfo.copy$default$5();
         Option x$7 = oldInfo.copy$default$6();
         var10000 = oldInfo.copy(x$2, x$3, x$4, x$5, x$6, x$7, x$1);
      } else {
         if (!scala.None..MODULE$.equals(var6)) {
            throw new MatchError(var6);
         }

         this.logWarning((Function0)(() -> "No prior receiver info"));
         ReceiverErrorInfo errorInfo = new ReceiverErrorInfo(message, error, this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.scheduler().clock().getTimeMillis());
         var10000 = new ReceiverTrackingInfo(streamId, ReceiverState$.MODULE$.INACTIVE(), scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$, new Some(errorInfo));
      }

      ReceiverTrackingInfo newReceiverTrackingInfo = var10000;
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().update(BoxesRunTime.boxToInteger(streamId), newReceiverTrackingInfo);
      this.listenerBus().post(new StreamingListenerReceiverError(newReceiverTrackingInfo.toReceiverInfo()));
      MessageWithContext messageWithError = error != null && !error.isEmpty() ? this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " - ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message), new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, error)}))) : this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MESSAGE..MODULE$, message)})));
      this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error reported by receiver for stream ", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(streamId))}))).$plus(messageWithError)));
   }

   private Seq scheduleReceiver(final int receiverId) {
      Option preferredLocation = (Option)this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations().getOrElse(BoxesRunTime.boxToInteger(receiverId), () -> scala.None..MODULE$);
      Seq scheduledLocations = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy().rescheduleReceiver(receiverId, preferredLocation, this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos(), this.org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors());
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$updateReceiverScheduledExecutors(receiverId, scheduledLocations);
      return scheduledLocations;
   }

   public void org$apache$spark$streaming$scheduler$ReceiverTracker$$updateReceiverScheduledExecutors(final int receiverId, final Seq scheduledLocations) {
      Option var5 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().get(BoxesRunTime.boxToInteger(receiverId));
      ReceiverTrackingInfo var10000;
      if (var5 instanceof Some var6) {
         ReceiverTrackingInfo oldInfo = (ReceiverTrackingInfo)var6.value();
         Enumeration.Value x$1 = ReceiverState$.MODULE$.SCHEDULED();
         Some x$2 = new Some(scheduledLocations);
         int x$3 = oldInfo.copy$default$1();
         Option x$4 = oldInfo.copy$default$4();
         Option x$5 = oldInfo.copy$default$5();
         Option x$6 = oldInfo.copy$default$6();
         Option x$7 = oldInfo.copy$default$7();
         var10000 = oldInfo.copy(x$3, x$1, x$2, x$4, x$5, x$6, x$7);
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var10000 = new ReceiverTrackingInfo(receiverId, ReceiverState$.MODULE$.SCHEDULED(), new Some(scheduledLocations), scala.None..MODULE$, ReceiverTrackingInfo$.MODULE$.apply$default$5(), ReceiverTrackingInfo$.MODULE$.apply$default$6(), ReceiverTrackingInfo$.MODULE$.apply$default$7());
      }

      ReceiverTrackingInfo newReceiverTrackingInfo = var10000;
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().put(BoxesRunTime.boxToInteger(receiverId), newReceiverTrackingInfo);
   }

   public boolean hasUnallocatedBlocks() {
      return this.receivedBlockTracker().hasUnallocatedReceivedBlocks();
   }

   public Seq org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors() {
      if (this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sc().isLocal()) {
         BlockManagerId blockManagerId = this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().env().blockManager().blockManagerId();
         return new scala.collection.immutable..colon.colon(new ExecutorCacheTaskLocation(blockManagerId.host(), blockManagerId.executorId()), scala.collection.immutable.Nil..MODULE$);
      } else {
         return ((IterableOnceOps)((IterableOps)this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().env().blockManager().master().getMemoryStatus().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getExecutors$1(x0$1)))).map((x0$2) -> {
            if (x0$2 != null) {
               BlockManagerId blockManagerId = (BlockManagerId)x0$2._1();
               return new ExecutorCacheTaskLocation(blockManagerId.host(), blockManagerId.executorId());
            } else {
               throw new MatchError(x0$2);
            }
         })).toSeq();
      }
   }

   private void runDummySparkJob() {
      if (!this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().isLocal()) {
         org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().makeRDD(scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), 50), 50, scala.reflect.ClassTag..MODULE$.Int()).map((x) -> $anonfun$runDummySparkJob$1(BoxesRunTime.unboxToInt(x)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.Int(), scala.math.Ordering.Int..MODULE$).reduceByKey((JFunction2.mcIII.sp)(x$5, x$6) -> x$5 + x$6, 20).collect();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      scala.Predef..MODULE$.assert(this.org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors().nonEmpty());
   }

   private void launchReceivers() {
      Receiver[] receivers = (Receiver[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.receiverInputStreams()), (nis) -> {
         Receiver rcvr = nis.getReceiver();
         rcvr.setReceiverId(nis.id());
         return rcvr;
      }, scala.reflect.ClassTag..MODULE$.apply(Receiver.class));
      this.runDummySparkJob();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Starting ", " receivers"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RECEIVERS..MODULE$, BoxesRunTime.boxToInteger(receivers.length))})))));
      this.endpoint().send(new StartAllReceivers(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(receivers).toImmutableArraySeq()));
   }

   private boolean isTrackerStarted() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.trackerState();
         Enumeration.Value var1 = this.TrackerState().Started();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public boolean org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopping() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.trackerState();
         Enumeration.Value var1 = this.TrackerState().Stopping();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public boolean org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopped() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.trackerState();
         Enumeration.Value var1 = this.TrackerState().Stopped();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   private final void TrackerState$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TrackerState$module == null) {
            this.TrackerState$module = new TrackerState$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final int $anonfun$receiverInputStreamIds$1(final ReceiverInputDStream x$1) {
      return x$1.id();
   }

   // $FF: synthetic method
   public static final Option $anonfun$allocatedExecutors$1(final int x$2, final ReceiverTrackingInfo v) {
      return v.runningExecutor().map((x$3) -> x$3.executorId());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$registerReceiver$1(final String executorId$1, final String host$1, final TaskLocation x0$1) {
      if (x0$1 instanceof ExecutorCacheTaskLocation var5) {
         boolean var11;
         label53: {
            String var10 = var5.executorId();
            if (var10 == null) {
               if (executorId$1 == null) {
                  break label53;
               }
            } else if (var10.equals(executorId$1)) {
               break label53;
            }

            var11 = false;
            return var11;
         }

         var11 = true;
         return var11;
      } else if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var9;
         label55: {
            String var10000 = x0$1.host();
            if (var10000 == null) {
               if (host$1 == null) {
                  break label55;
               }
            } else if (var10000.equals(host$1)) {
               break label55;
            }

            var9 = false;
            return var9;
         }

         var9 = true;
         return var9;
      }
   }

   private static final boolean isAcceptable$1(final Seq acceptableExecutors$1, final String executorId$1, final String host$1) {
      return acceptableExecutors$1.exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$registerReceiver$1(executorId$1, host$1, x0$1)));
   }

   // $FF: synthetic method
   public static final long $anonfun$reportError$1(final ReceiverErrorInfo x$4) {
      return x$4.lastErrorTime();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getExecutors$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var5;
         label30: {
            BlockManagerId blockManagerId = (BlockManagerId)x0$1._1();
            String var10000 = blockManagerId.executorId();
            String var4 = org.apache.spark.SparkContext..MODULE$.DRIVER_IDENTIFIER();
            if (var10000 == null) {
               if (var4 != null) {
                  break label30;
               }
            } else if (!var10000.equals(var4)) {
               break label30;
            }

            var5 = false;
            return var5;
         }

         var5 = true;
         return var5;
      }
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$runDummySparkJob$1(final int x) {
      return new Tuple2.mcII.sp(x, 1);
   }

   public ReceiverTracker(final StreamingContext ssc, final boolean skipReceiverLaunch) {
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc = ssc;
      this.skipReceiverLaunch = skipReceiverLaunch;
      Logging.$init$(this);
      this.receiverInputStreams = ssc.graph().getReceiverInputStreams();
      this.receiverInputStreamIds = (int[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.receiverInputStreams()), (x$1) -> BoxesRunTime.boxToInteger($anonfun$receiverInputStreamIds$1(x$1)), scala.reflect.ClassTag..MODULE$.Int());
      this.receivedBlockTracker = new ReceivedBlockTracker(ssc.sparkContext().conf(), ssc.sparkContext().hadoopConfiguration(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.receiverInputStreamIds()).toImmutableArraySeq(), ssc.scheduler().clock(), ssc.isCheckpointPresent(), scala.Option..MODULE$.apply(ssc.checkpointDir()));
      this.listenerBus = ssc.scheduler().listenerBus();
      this.trackerState = this.TrackerState().Initialized();
      this.endpoint = null;
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy = new ReceiverSchedulingPolicy();
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch = new CountDownLatch(this.receiverInputStreams().length);
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos = new HashMap();
      this.org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class TrackerState$ extends Enumeration {
      private final Enumeration.Value Initialized = this.Value();
      private final Enumeration.Value Started = this.Value();
      private final Enumeration.Value Stopping = this.Value();
      private final Enumeration.Value Stopped = this.Value();

      public Enumeration.Value Initialized() {
         return this.Initialized;
      }

      public Enumeration.Value Started() {
         return this.Started;
      }

      public Enumeration.Value Stopping() {
         return this.Stopping;
      }

      public Enumeration.Value Stopped() {
         return this.Stopped;
      }
   }

   private class ReceiverTrackerEndpoint implements ThreadSafeRpcEndpoint {
      private final RpcEnv rpcEnv;
      private final ExecutionContextExecutorService org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool;
      private volatile boolean org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active;
      // $FF: synthetic field
      public final ReceiverTracker $outer;

      public final RpcEndpointRef self() {
         return RpcEndpoint.self$(this);
      }

      public void onError(final Throwable cause) {
         RpcEndpoint.onError$(this, cause);
      }

      public void onConnected(final RpcAddress remoteAddress) {
         RpcEndpoint.onConnected$(this, remoteAddress);
      }

      public void onDisconnected(final RpcAddress remoteAddress) {
         RpcEndpoint.onDisconnected$(this, remoteAddress);
      }

      public void onNetworkError(final Throwable cause, final RpcAddress remoteAddress) {
         RpcEndpoint.onNetworkError$(this, cause, remoteAddress);
      }

      public void onStart() {
         RpcEndpoint.onStart$(this);
      }

      public final void stop() {
         RpcEndpoint.stop$(this);
      }

      public RpcEnv rpcEnv() {
         return this.rpcEnv;
      }

      public ExecutionContextExecutorService org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool() {
         return this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool;
      }

      public boolean org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active() {
         return this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active;
      }

      private void active_$eq(final boolean x$1) {
         this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active = x$1;
      }

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final ReceiverTrackerEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof StartAllReceivers var5) {
                  Seq receivers = var5.receiver();
                  scala.collection.Map scheduledLocations = this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy().scheduleReceivers(receivers, this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors());
                  receivers.foreach((receiverx) -> {
                     $anonfun$applyOrElse$1(this, scheduledLocations, receiverx);
                     return BoxedUnit.UNIT;
                  });
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof RestartReceiver var8) {
                  Receiver receiver = var8.receiver();
                  Seq oldScheduledExecutors = this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$getStoredScheduledExecutors(receiver.streamId());
                  Seq var10000;
                  if (oldScheduledExecutors.nonEmpty()) {
                     var10000 = oldScheduledExecutors;
                  } else {
                     ReceiverTrackingInfo oldReceiverInfo = (ReceiverTrackingInfo)this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().apply(BoxesRunTime.boxToInteger(receiver.streamId()));
                     Enumeration.Value x$1 = ReceiverState$.MODULE$.INACTIVE();
                     None x$2 = scala.None..MODULE$;
                     int x$3 = oldReceiverInfo.copy$default$1();
                     Option x$4 = oldReceiverInfo.copy$default$4();
                     Option x$5 = oldReceiverInfo.copy$default$5();
                     Option x$6 = oldReceiverInfo.copy$default$6();
                     Option x$7 = oldReceiverInfo.copy$default$7();
                     ReceiverTrackingInfo newReceiverInfo = oldReceiverInfo.copy(x$3, x$1, x$2, x$4, x$5, x$6, x$7);
                     this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().update(BoxesRunTime.boxToInteger(receiver.streamId()), newReceiverInfo);
                     var10000 = this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$schedulingPolicy().rescheduleReceiver(receiver.streamId(), receiver.preferredLocation(), this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos(), this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors());
                  }

                  Seq scheduledLocations = var10000;
                  this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$startReceiver(receiver, scheduledLocations);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CleanupOldBlocks var21) {
                  ((IterableOnceOps)this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().values().flatMap((x$7x) -> x$7x.endpoint())).foreach((x$8) -> {
                     $anonfun$applyOrElse$3(var21, x$8);
                     return BoxedUnit.UNIT;
                  });
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof UpdateReceiverRateLimit var22) {
                  int streamUID = var22.streamUID();
                  long newRate = var22.newRate();
                  this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().get(BoxesRunTime.boxToInteger(streamUID)).foreach((info) -> {
                     $anonfun$applyOrElse$4(newRate, info);
                     return BoxedUnit.UNIT;
                  });
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof ReportError var26) {
                  int streamId = var26.streamId();
                  String message = var26.message();
                  String error = var26.error();
                  this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$reportError(streamId, message, error);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof StartAllReceivers) {
                  return true;
               } else if (x1 instanceof RestartReceiver) {
                  return true;
               } else if (x1 instanceof CleanupOldBlocks) {
                  return true;
               } else if (x1 instanceof UpdateReceiverRateLimit) {
                  return true;
               } else {
                  return x1 instanceof ReportError;
               }
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$1(final Object $this, final scala.collection.Map scheduledLocations$1, final Receiver receiver) {
               Seq executors = (Seq)scheduledLocations$1.apply(BoxesRunTime.boxToInteger(receiver.streamId()));
               $this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$updateReceiverScheduledExecutors(receiver.streamId(), executors);
               $this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverPreferredLocations().update(BoxesRunTime.boxToInteger(receiver.streamId()), receiver.preferredLocation());
               $this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$startReceiver(receiver, executors);
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$3(final CleanupOldBlocks x4$1, final RpcEndpointRef x$8) {
               x$8.send(x4$1);
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$5(final long newRate$1, final RpcEndpointRef eP) {
               eP.send(new UpdateRateLimit(newRate$1));
            }

            // $FF: synthetic method
            public static final void $anonfun$applyOrElse$4(final long newRate$1, final ReceiverTrackingInfo info) {
               info.endpoint().foreach((eP) -> {
                  $anonfun$applyOrElse$5(newRate$1, eP);
                  return BoxedUnit.UNIT;
               });
            }

            public {
               if (ReceiverTrackerEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = ReceiverTrackerEndpoint.this;
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
            private final ReceiverTrackerEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof RegisterReceiver var5) {
                  int streamId = var5.streamId();
                  String typ = var5.typ();
                  String host = var5.host();
                  String executorId = var5.executorId();
                  RpcEndpointRef receiverEndpoint = var5.receiverEndpoint();
                  boolean successful = this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$registerReceiver(streamId, typ, host, executorId, receiverEndpoint, this.context$1.senderAddress());
                  this.context$1.reply(BoxesRunTime.boxToBoolean(successful));
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof AddBlock var12) {
                  ReceivedBlockInfo receivedBlockInfo = var12.receivedBlockInfo();
                  if (WriteAheadLogUtils$.MODULE$.isBatchingEnabled(this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.conf(), true)) {
                     this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool().execute(() -> org.apache.spark.util.Utils..MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                           if (this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active()) {
                              this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$addBlock(receivedBlockInfo)));
                           } else {
                              this.context$1.sendFailure(new IllegalStateException("ReceiverTracker RpcEndpoint already shut down."));
                           }
                        }));
                     return BoxedUnit.UNIT;
                  } else {
                     this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$addBlock(receivedBlockInfo)));
                     return BoxedUnit.UNIT;
                  }
               } else if (x1 instanceof DeregisterReceiver var14) {
                  int streamId = var14.streamId();
                  String message = var14.msg();
                  String error = var14.error();
                  this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$deregisterReceiver(streamId, message, error);
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               } else if (AllReceiverIds$.MODULE$.equals(x1)) {
                  this.context$1.reply(((scala.collection.MapOps)this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().filter((x$9) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$8(x$9)))).keys().toSeq());
                  return BoxedUnit.UNIT;
               } else if (GetAllReceiverInfo$.MODULE$.equals(x1)) {
                  this.context$1.reply(this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().toMap(scala..less.colon.less..MODULE$.refl()));
                  return BoxedUnit.UNIT;
               } else if (!StopAllReceivers$.MODULE$.equals(x1)) {
                  return default.apply(x1);
               } else {
                  scala.Predef..MODULE$.assert(this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopping() || this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopped());
                  this.$outer.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$stopReceivers();
                  this.context$1.reply(BoxesRunTime.boxToBoolean(true));
                  return BoxedUnit.UNIT;
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof RegisterReceiver) {
                  return true;
               } else if (x1 instanceof AddBlock) {
                  return true;
               } else if (x1 instanceof DeregisterReceiver) {
                  return true;
               } else if (AllReceiverIds$.MODULE$.equals(x1)) {
                  return true;
               } else if (GetAllReceiverInfo$.MODULE$.equals(x1)) {
                  return true;
               } else {
                  return StopAllReceivers$.MODULE$.equals(x1);
               }
            }

            // $FF: synthetic method
            public static final boolean $anonfun$applyOrElse$8(final Tuple2 x$9) {
               boolean var2;
               label23: {
                  Enumeration.Value var10000 = ((ReceiverTrackingInfo)x$9._2()).state();
                  Enumeration.Value var1 = ReceiverState$.MODULE$.INACTIVE();
                  if (var10000 == null) {
                     if (var1 != null) {
                        break label23;
                     }
                  } else if (!var10000.equals(var1)) {
                     break label23;
                  }

                  var2 = false;
                  return var2;
               }

               var2 = true;
               return var2;
            }

            public {
               if (ReceiverTrackerEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = ReceiverTrackerEndpoint.this;
                  this.context$1 = context$1;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public Seq org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$getStoredScheduledExecutors(final int receiverId) {
         if (this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().contains(BoxesRunTime.boxToInteger(receiverId))) {
            Option scheduledLocations = ((ReceiverTrackingInfo)this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().apply(BoxesRunTime.boxToInteger(receiverId))).scheduledLocations();
            if (scheduledLocations.nonEmpty()) {
               Set executors = this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$getExecutors().toSet();
               return (Seq)((IterableOps)scheduledLocations.get()).filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getStoredScheduledExecutors$1(executors, x0$1)));
            } else {
               return scala.collection.immutable.Nil..MODULE$;
            }
         } else {
            return scala.collection.immutable.Nil..MODULE$;
         }
      }

      public void org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$startReceiver(final Receiver receiver, final Seq scheduledLocations) {
         int receiverId = receiver.streamId();
         if (!this.shouldStartReceiver$1()) {
            this.onReceiverJobFinish(receiverId);
         } else {
            Option checkpointDirOption = scala.Option..MODULE$.apply(this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.checkpointDir());
            SerializableConfiguration serializableHadoopConf = new SerializableConfiguration(this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().hadoopConfiguration());
            Function1 startReceiverFunc = (iterator) -> {
               $anonfun$startReceiver$1(serializableHadoopConf, checkpointDirOption, iterator);
               return BoxedUnit.UNIT;
            };
            RDD var10000;
            if (scheduledLocations.isEmpty()) {
               var10000 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sc().makeRDD(new scala.collection.immutable..colon.colon(receiver, scala.collection.immutable.Nil..MODULE$), 1, scala.reflect.ClassTag..MODULE$.apply(Receiver.class));
            } else {
               Seq preferredLocations = (Seq)((SeqOps)scheduledLocations.map((x$10) -> x$10.toString())).distinct();
               var10000 = this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sc().makeRDD(new scala.collection.immutable..colon.colon(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(receiver), preferredLocations), scala.collection.immutable.Nil..MODULE$), scala.reflect.ClassTag..MODULE$.apply(Receiver.class));
            }

            RDD receiverRDD = var10000;
            receiverRDD.setName("Receiver " + receiverId);
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().setJobDescription("Streaming job running receiver " + receiverId);
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().setCallSite((CallSite)scala.Option..MODULE$.apply(this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.getStartSite()).getOrElse(() -> org.apache.spark.util.Utils..MODULE$.getCallSite(org.apache.spark.util.Utils..MODULE$.getCallSite$default$1())));
            SimpleFutureAction future = this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$ssc.sparkContext().submitJob(receiverRDD, startReceiverFunc, (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{0})), (x$11, x$12) -> {
               $anonfun$startReceiver$4(BoxesRunTime.unboxToInt(x$11), x$12);
               return BoxedUnit.UNIT;
            }, (JFunction0.mcV.sp)() -> {
            });
            future.onComplete((x0$1) -> {
               $anonfun$startReceiver$6(this, receiverId, receiver, x0$1);
               return BoxedUnit.UNIT;
            }, org.apache.spark.util.ThreadUtils..MODULE$.sameThread());
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Receiver ", " started"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(receiver.streamId()))})))));
         }
      }

      public void onStop() {
         this.active_$eq(false);
         this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool().shutdown();
      }

      private void onReceiverJobFinish(final int receiverId) {
         this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverJobExitLatch().countDown();
         this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().remove(BoxesRunTime.boxToInteger(receiverId)).foreach((receiverTrackingInfo) -> {
            $anonfun$onReceiverJobFinish$1(this, receiverId, receiverTrackingInfo);
            return BoxedUnit.UNIT;
         });
      }

      public void org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$stopReceivers() {
         ((IterableOnceOps)this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().values().flatMap((x$13) -> x$13.endpoint())).foreach((x$14) -> {
            $anonfun$stopReceivers$2(x$14);
            return BoxedUnit.UNIT;
         });
         this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Sent stop signal to all "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " receivers"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RECEIVERS..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$receiverTrackingInfos().size()))}))))));
      }

      // $FF: synthetic method
      public ReceiverTracker org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$getStoredScheduledExecutors$1(final Set executors$1, final TaskLocation x0$1) {
         if (x0$1 instanceof ExecutorCacheTaskLocation var4) {
            return executors$1.apply(var4);
         } else if (x0$1 != null) {
            return true;
         } else {
            throw new MatchError(x0$1);
         }
      }

      private final boolean shouldStartReceiver$1() {
         return !this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopping() && !this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().org$apache$spark$streaming$scheduler$ReceiverTracker$$isTrackerStopped();
      }

      // $FF: synthetic method
      public static final void $anonfun$startReceiver$1(final SerializableConfiguration serializableHadoopConf$1, final Option checkpointDirOption$1, final Iterator iterator) {
         if (!iterator.hasNext()) {
            throw new SparkException("Could not start receiver as object not found.");
         } else if (org.apache.spark.TaskContext..MODULE$.get().attemptNumber() == 0) {
            Receiver receiver = (Receiver)iterator.next();
            scala.Predef..MODULE$.assert(!iterator.hasNext());
            ReceiverSupervisorImpl supervisor = new ReceiverSupervisorImpl(receiver, org.apache.spark.SparkEnv..MODULE$.get(), serializableHadoopConf$1.value(), checkpointDirOption$1);
            supervisor.start();
            supervisor.awaitTermination();
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$startReceiver$4(final int x$11, final BoxedUnit x$12) {
      }

      // $FF: synthetic method
      public static final void $anonfun$startReceiver$6(final ReceiverTrackerEndpoint $this, final int receiverId$1, final Receiver receiver$1, final Try x0$1) {
         if (x0$1 instanceof Success) {
            if (!$this.shouldStartReceiver$1()) {
               $this.onReceiverJobFinish(receiverId$1);
               BoxedUnit var10 = BoxedUnit.UNIT;
            } else {
               $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restarting Receiver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(receiverId$1))})))));
               $this.self().send(new RestartReceiver(receiver$1));
               BoxedUnit var9 = BoxedUnit.UNIT;
            }
         } else if (x0$1 instanceof Failure) {
            Failure var6 = (Failure)x0$1;
            Throwable e = var6.exception();
            if (!$this.shouldStartReceiver$1()) {
               $this.onReceiverJobFinish(receiverId$1);
               BoxedUnit var8 = BoxedUnit.UNIT;
            } else {
               $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logError((Function0)(() -> "Receiver has been stopped. Try to restart it."), e);
               $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Restarting Receiver ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STREAM_ID..MODULE$, BoxesRunTime.boxToInteger(receiverId$1))})))));
               $this.self().send(new RestartReceiver(receiver$1));
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$onReceiverJobFinish$1(final ReceiverTrackerEndpoint $this, final int receiverId$2, final ReceiverTrackingInfo receiverTrackingInfo) {
         label14: {
            Enumeration.Value var10000 = receiverTrackingInfo.state();
            Enumeration.Value var3 = ReceiverState$.MODULE$.ACTIVE();
            if (var10000 == null) {
               if (var3 == null) {
                  break label14;
               }
            } else if (var10000.equals(var3)) {
               break label14;
            }

            return;
         }

         $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Receiver ", " exited but didn't deregister"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RECEIVER_ID..MODULE$, BoxesRunTime.boxToInteger(receiverId$2))})))));
      }

      // $FF: synthetic method
      public static final void $anonfun$stopReceivers$2(final RpcEndpointRef x$14) {
         x$14.send(StopReceiver$.MODULE$);
      }

      public ReceiverTrackerEndpoint(final RpcEnv rpcEnv) {
         this.rpcEnv = rpcEnv;
         if (ReceiverTracker.this == null) {
            throw null;
         } else {
            this.$outer = ReceiverTracker.this;
            super();
            RpcEndpoint.$init$(this);
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$walBatchingThreadPool = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(org.apache.spark.util.ThreadUtils..MODULE$.newDaemonCachedThreadPool("wal-batching-thread-pool"));
            this.org$apache$spark$streaming$scheduler$ReceiverTracker$ReceiverTrackerEndpoint$$active = true;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
