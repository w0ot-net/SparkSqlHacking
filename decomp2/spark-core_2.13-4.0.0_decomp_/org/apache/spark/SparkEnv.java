package org.apache.spark;

import java.io.File;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.api.python.PythonWorker;
import org.apache.spark.api.python.PythonWorkerFactory;
import org.apache.spark.api.python.PythonWorkerFactory$;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.memory.UnifiedMemoryManager$;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.scheduler.OutputCommitCoordinator;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.shuffle.ShuffleManager;
import org.apache.spark.shuffle.ShuffleManager$;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.cache.CacheBuilder;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.Iterator;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0015h\u0001\u00026l\u0001ID\u0011b \u0001\u0003\u0006\u0004%\t!!\u0001\t\u0015\u0005e\u0001A!A!\u0002\u0013\t\u0019\u0001C\u0006\u0002\u001c\u0001\u0011)\u0019!C\u0001W\u0006u\u0001BCA\u0016\u0001\t\u0005\t\u0015!\u0003\u0002 !Q\u0011Q\u0006\u0001\u0003\u0006\u0004%\t!a\f\t\u0015\u0005m\u0002A!A!\u0002\u0013\t\t\u0004\u0003\u0006\u0002>\u0001\u0011)\u0019!C\u0001\u0003_A!\"a\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA\u0019\u0011)\t\t\u0005\u0001BC\u0002\u0013\u0005\u00111\t\u0005\u000b\u0003\u0017\u0002!\u0011!Q\u0001\n\u0005\u0015\u0003BCA'\u0001\t\u0015\r\u0011\"\u0001\u0002P!Q\u0011\u0011\f\u0001\u0003\u0002\u0003\u0006I!!\u0015\t\u0015\u0005m\u0003A!b\u0001\n\u0003\ti\u0006\u0003\u0006\u0002l\u0001\u0011\t\u0011)A\u0005\u0003?B!\"!\u001c\u0001\u0005\u000b\u0007I\u0011AA8\u0011)\ti\b\u0001B\u0001B\u0003%\u0011\u0011\u000f\u0005\u000b\u0003\u007f\u0002!Q1A\u0005\u0002\u0005\u0005\u0005BCAE\u0001\t\u0005\t\u0015!\u0003\u0002\u0004\"Q\u00111\u0012\u0001\u0003\u0006\u0004%\t!!$\t\u0015\u0005m\u0005A!A!\u0002\u0013\ty\t\u0003\u0006\u0002\u001e\u0002\u0011)\u0019!C\u0001\u0003?C!\"!,\u0001\u0005\u0003\u0005\u000b\u0011BAQ\u0011)\ty\u000b\u0001BC\u0002\u0013\u0005\u0011\u0011\u0017\u0005\u000b\u0003s\u0003!\u0011!Q\u0001\n\u0005M\u0006bBA^\u0001\u0011\u0005\u0011Q\u0018\u0005\f\u00033\u0004\u0001\u0019!a\u0001\n\u0013\tY\u000eC\u0006\u0002j\u0002\u0001\r\u00111A\u0005\n\u0005-\bbCA|\u0001\u0001\u0007\t\u0011)Q\u0005\u0003;DqA!\u0001\u0001\t\u0003\tY\u000eC\u0006\u0003\u0004\u0001\u0001\r\u00111A\u0005\n\t\u0015\u0001b\u0003B\n\u0001\u0001\u0007\t\u0019!C\u0005\u0005+A1B!\u0007\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0003\b!9!1\u0004\u0001\u0005\u0002\t\u0015\u0001B\u0003B\u000f\u0001\u0001\u0007I\u0011A6\u0003 !Q!q\u0005\u0001A\u0002\u0013\u00051N!\u000b\t\u0011\t5\u0002\u0001)Q\u0005\u0005C1aA!\r\u0001\t\nM\u0002B\u0003B'K\tU\r\u0011\"\u0001\u0002\u0002!Q!qJ\u0013\u0003\u0012\u0003\u0006I!a\u0001\t\u0015\tESE!f\u0001\n\u0003\t\t\u0001\u0003\u0006\u0003T\u0015\u0012\t\u0012)A\u0005\u0003\u0007A!B!\u0016&\u0005+\u0007I\u0011AA\u0001\u0011)\u00119&\nB\tB\u0003%\u00111\u0001\u0005\u000b\u00053*#Q3A\u0005\u0002\tm\u0003B\u0003B2K\tE\t\u0015!\u0003\u0003^!9\u00111X\u0013\u0005\u0002\t\u0015\u0004\"\u0003B:K\u0005\u0005I\u0011\u0001B;\u0011%\u0011y(JI\u0001\n\u0003\u0011\t\tC\u0005\u0003\u0018\u0016\n\n\u0011\"\u0001\u0003\u0002\"I!\u0011T\u0013\u0012\u0002\u0013\u0005!\u0011\u0011\u0005\n\u00057+\u0013\u0013!C\u0001\u0005;C\u0011B!)&\u0003\u0003%\tEa)\t\u0013\tMV%!A\u0005\u0002\tU\u0006\"\u0003B_K\u0005\u0005I\u0011\u0001B`\u0011%\u0011I-JA\u0001\n\u0003\u0012Y\rC\u0005\u0003Z\u0016\n\t\u0011\"\u0001\u0003\\\"I!q\\\u0013\u0002\u0002\u0013\u0005#\u0011\u001d\u0005\n\u0005K,\u0013\u0011!C!\u0005OD\u0011B!;&\u0003\u0003%\tEa;\t\u0013\t5X%!A\u0005B\t=x!\u0003Bz\u0001\u0005\u0005\t\u0012\u0002B{\r%\u0011\t\u0004AA\u0001\u0012\u0013\u00119\u0010C\u0004\u0002<z\"\taa\u0004\t\u0013\t%h(!A\u0005F\t-\b\"CB\t}\u0005\u0005I\u0011QB\n\u0011%\u0019iBPA\u0001\n\u0003\u001by\u0002C\u0005\u00042\u0001\u0011\r\u0011\"\u0003\u00044!A1\u0011\u000b\u0001!\u0002\u0013\u0019)\u0004\u0003\u0006\u0004T\u0001\u0011\r\u0011\"\u0001l\u0007+B\u0001ba\u001a\u0001A\u0003%1q\u000b\u0005\u000b\u0007S\u0002\u0001\u0019!C\u0001W\u000e-\u0004BCB8\u0001\u0001\u0007I\u0011A6\u0004r!A1Q\u000f\u0001!B\u0013\u0019i\u0007\u0003\u0006\u0004x\u0001\u0001\r\u0011\"\u0001l\u0007sB!b!#\u0001\u0001\u0004%\ta[BF\u0011!\u0019y\t\u0001Q!\n\rm\u0004\u0002CBI\u0001\u0011\u00051na%\t\u0011\rU\u0005\u0001\"\u0001l\u0007/C\u0001b!&\u0001\t\u0003Y7\u0011\u0018\u0005\t\u0007+\u0003A\u0011A6\u0004D\"A1Q\u001a\u0001\u0005\u0002-\u001cy\r\u0003\u0005\u0004N\u0002!\ta[Bo\u0011!\u00199\u000f\u0001C\u0001W\u000e%\b\u0002CBt\u0001\u0011\u00051n!>\t\u0011\r}\b\u0001\"\u0001l\u0007'C\u0001\u0002\"\u0001\u0001\t\u0003YG1A\u0004\b\t+Y\u0007\u0012\u0001C\f\r\u0019Q7\u000e#\u0001\u0005\u001a!9\u00111\u0018-\u0005\u0002\u0011m\u0001b\u0003C\u000f1\u0002\u0007\t\u0019!C\u0005\t?A1\u0002\"\tY\u0001\u0004\u0005\r\u0011\"\u0003\u0005$!YAq\u0005-A\u0002\u0003\u0005\u000b\u0015BA`\u0011)!Y\u0003\u0017b\u0001\n\u0003Y'1\u0015\u0005\t\t[A\u0006\u0015!\u0003\u0003&\"QAq\u0006-C\u0002\u0013\u00051Na)\t\u0011\u0011E\u0002\f)A\u0005\u0005KCq\u0001b\rY\t\u0003!)\u0004C\u0004\u0005<a#\t\u0001b\b\t\u0011\u0011u\u0002\f\"\u0001l\t\u007fA!\u0002b\u0017Y#\u0003%\ta\u001bC/\u0011!!\t\u0007\u0017C\u0001W\u0012\r\u0004b\u0002CD1\u0012%A\u0011\u0012\u0005\n\tKC\u0016\u0013!C\u0005\tOC\u0011\u0002b+Y#\u0003%I\u0001\"\u0018\t\u0011\u00115\u0006\f\"\u0001l\t_\u0013\u0001b\u00159be.,eN\u001e\u0006\u0003Y6\fQa\u001d9be.T!A\\8\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0018aA8sO\u000e\u00011c\u0001\u0001tsB\u0011Ao^\u0007\u0002k*\ta/A\u0003tG\u0006d\u0017-\u0003\u0002yk\n1\u0011I\\=SK\u001a\u0004\"A_?\u000e\u0003mT!\u0001`6\u0002\u0011%tG/\u001a:oC2L!A`>\u0003\u000f1{wmZ5oO\u0006QQ\r_3dkR|'/\u00133\u0016\u0005\u0005\r\u0001\u0003BA\u0003\u0003'qA!a\u0002\u0002\u0010A\u0019\u0011\u0011B;\u000e\u0005\u0005-!bAA\u0007c\u00061AH]8pizJ1!!\u0005v\u0003\u0019\u0001&/\u001a3fM&!\u0011QCA\f\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011C;\u0002\u0017\u0015DXmY;u_JLE\rI\u0001\u0007eB\u001cWI\u001c<\u0016\u0005\u0005}\u0001\u0003BA\u0011\u0003Oi!!a\t\u000b\u0007\u0005\u00152.A\u0002sa\u000eLA!!\u000b\u0002$\t1!\u000b]2F]Z\fqA\u001d9d\u000b:4\b%\u0001\u0006tKJL\u0017\r\\5{KJ,\"!!\r\u0011\t\u0005M\u0012qG\u0007\u0003\u0003kQ1!!\fl\u0013\u0011\tI$!\u000e\u0003\u0015M+'/[1mSj,'/A\u0006tKJL\u0017\r\\5{KJ\u0004\u0013!E2m_N,(/Z*fe&\fG.\u001b>fe\u0006\u00112\r\\8tkJ,7+\u001a:jC2L'0\u001a:!\u0003E\u0019XM]5bY&TXM]'b]\u0006<WM]\u000b\u0003\u0003\u000b\u0002B!a\r\u0002H%!\u0011\u0011JA\u001b\u0005E\u0019VM]5bY&TXM]'b]\u0006<WM]\u0001\u0013g\u0016\u0014\u0018.\u00197ju\u0016\u0014X*\u00198bO\u0016\u0014\b%\u0001\tnCB|U\u000f\u001e9viR\u0013\u0018mY6feV\u0011\u0011\u0011\u000b\t\u0005\u0003'\n)&D\u0001l\u0013\r\t9f\u001b\u0002\u0011\u001b\u0006\u0004x*\u001e;qkR$&/Y2lKJ\f\u0011#\\1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:!\u0003A\u0011'o\\1eG\u0006\u001cH/T1oC\u001e,'/\u0006\u0002\u0002`A!\u0011\u0011MA4\u001b\t\t\u0019GC\u0002\u0002f-\f\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\t\u0005%\u00141\r\u0002\u0011\u0005J|\u0017\rZ2bgRl\u0015M\\1hKJ\f\u0011C\u0019:pC\u0012\u001c\u0017m\u001d;NC:\fw-\u001a:!\u00031\u0011Gn\\2l\u001b\u0006t\u0017mZ3s+\t\t\t\b\u0005\u0003\u0002t\u0005eTBAA;\u0015\r\t9h[\u0001\bgR|'/Y4f\u0013\u0011\tY(!\u001e\u0003\u0019\tcwnY6NC:\fw-\u001a:\u0002\u001b\tdwnY6NC:\fw-\u001a:!\u0003=\u0019XmY;sSRLX*\u00198bO\u0016\u0014XCAAB!\u0011\t\u0019&!\"\n\u0007\u0005\u001d5NA\bTK\u000e,(/\u001b;z\u001b\u0006t\u0017mZ3s\u0003A\u0019XmY;sSRLX*\u00198bO\u0016\u0014\b%A\u0007nKR\u0014\u0018nY:TsN$X-\\\u000b\u0003\u0003\u001f\u0003B!!%\u0002\u00186\u0011\u00111\u0013\u0006\u0004\u0003+[\u0017aB7fiJL7m]\u0005\u0005\u00033\u000b\u0019JA\u0007NKR\u0014\u0018nY:TsN$X-\\\u0001\u000f[\u0016$(/[2t'f\u001cH/Z7!\u0003]yW\u000f\u001e9vi\u000e{W.\\5u\u0007>|'\u000fZ5oCR|'/\u0006\u0002\u0002\"B!\u00111UAU\u001b\t\t)KC\u0002\u0002(.\f\u0011b]2iK\u0012,H.\u001a:\n\t\u0005-\u0016Q\u0015\u0002\u0018\u001fV$\b/\u001e;D_6l\u0017\u000e^\"p_J$\u0017N\\1u_J\f\u0001d\\;uaV$8i\\7nSR\u001cun\u001c:eS:\fGo\u001c:!\u0003\u0011\u0019wN\u001c4\u0016\u0005\u0005M\u0006\u0003BA*\u0003kK1!a.l\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0003d_:4\u0007%\u0001\u0004=S:LGO\u0010\u000b\u001b\u0003\u007f\u000b\t-a1\u0002F\u0006\u001d\u0017\u0011ZAf\u0003\u001b\fy-!5\u0002T\u0006U\u0017q\u001b\t\u0004\u0003'\u0002\u0001BB@\u001a\u0001\u0004\t\u0019\u0001C\u0004\u0002\u001ce\u0001\r!a\b\t\u000f\u00055\u0012\u00041\u0001\u00022!9\u0011QH\rA\u0002\u0005E\u0002bBA!3\u0001\u0007\u0011Q\t\u0005\b\u0003\u001bJ\u0002\u0019AA)\u0011\u001d\tY&\u0007a\u0001\u0003?Bq!!\u001c\u001a\u0001\u0004\t\t\bC\u0004\u0002\u0000e\u0001\r!a!\t\u000f\u0005-\u0015\u00041\u0001\u0002\u0010\"9\u0011QT\rA\u0002\u0005\u0005\u0006bBAX3\u0001\u0007\u00111W\u0001\u0010?NDWO\u001a4mK6\u000bg.Y4feV\u0011\u0011Q\u001c\t\u0005\u0003?\f)/\u0004\u0002\u0002b*\u0019\u00111]6\u0002\u000fMDWO\u001a4mK&!\u0011q]Aq\u00059\u0019\u0006.\u001e4gY\u0016l\u0015M\\1hKJ\f1cX:ik\u001a4G.Z'b]\u0006<WM]0%KF$B!!<\u0002tB\u0019A/a<\n\u0007\u0005EXO\u0001\u0003V]&$\b\"CA{7\u0005\u0005\t\u0019AAo\u0003\rAH%M\u0001\u0011?NDWO\u001a4mK6\u000bg.Y4fe\u0002B3\u0001HA~!\r!\u0018Q`\u0005\u0004\u0003\u007f,(\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u001dMDWO\u001a4mK6\u000bg.Y4fe\u0006qq,\\3n_JLX*\u00198bO\u0016\u0014XC\u0001B\u0004!\u0011\u0011IAa\u0004\u000e\u0005\t-!b\u0001B\u0007W\u00061Q.Z7pefLAA!\u0005\u0003\f\tiQ*Z7pefl\u0015M\\1hKJ\f!cX7f[>\u0014\u00180T1oC\u001e,'o\u0018\u0013fcR!\u0011Q\u001eB\f\u0011%\t)pHA\u0001\u0002\u0004\u00119!A\b`[\u0016lwN]=NC:\fw-\u001a:!\u00035iW-\\8ss6\u000bg.Y4fe\u0006I\u0011n]*u_B\u0004X\rZ\u000b\u0003\u0005C\u00012\u0001\u001eB\u0012\u0013\r\u0011)#\u001e\u0002\b\u0005>|G.Z1o\u00035I7o\u0015;paB,Gm\u0018\u0013fcR!\u0011Q\u001eB\u0016\u0011%\t)pIA\u0001\u0002\u0004\u0011\t#\u0001\u0006jgN#x\u000e\u001d9fI\u0002B3\u0001JA~\u0005A\u0001\u0016\u0010\u001e5p]^{'o[3sg.+\u0017p\u0005\u0004&g\nU\"1\b\t\u0004i\n]\u0012b\u0001B\u001dk\n9\u0001K]8ek\u000e$\b\u0003\u0002B\u001f\u0005\u000frAAa\u0010\u0003D9!\u0011\u0011\u0002B!\u0013\u00051\u0018b\u0001B#k\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002B%\u0005\u0017\u0012AbU3sS\u0006d\u0017N_1cY\u0016T1A!\u0012v\u0003)\u0001\u0018\u0010\u001e5p]\u0016CXmY\u0001\faf$\bn\u001c8Fq\u0016\u001c\u0007%\u0001\u0007x_J\\WM]'pIVdW-A\u0007x_J\\WM]'pIVdW\rI\u0001\rI\u0006,Wn\u001c8N_\u0012,H.Z\u0001\u000eI\u0006,Wn\u001c8N_\u0012,H.\u001a\u0011\u0002\u000f\u0015tgOV1sgV\u0011!Q\f\t\t\u0003\u000b\u0011y&a\u0001\u0002\u0004%!!\u0011MA\f\u0005\ri\u0015\r]\u0001\tK:4h+\u0019:tAQQ!q\rB6\u0005[\u0012yG!\u001d\u0011\u0007\t%T%D\u0001\u0001\u0011\u001d\u0011iE\fa\u0001\u0003\u0007AqA!\u0015/\u0001\u0004\t\u0019\u0001C\u0004\u0003V9\u0002\r!a\u0001\t\u000f\tec\u00061\u0001\u0003^\u0005!1m\u001c9z))\u00119Ga\u001e\u0003z\tm$Q\u0010\u0005\n\u0005\u001bz\u0003\u0013!a\u0001\u0003\u0007A\u0011B!\u00150!\u0003\u0005\r!a\u0001\t\u0013\tUs\u0006%AA\u0002\u0005\r\u0001\"\u0003B-_A\u0005\t\u0019\u0001B/\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"Aa!+\t\u0005\r!QQ\u0016\u0003\u0005\u000f\u0003BA!#\u0003\u00146\u0011!1\u0012\u0006\u0005\u0005\u001b\u0013y)A\u0005v]\u000eDWmY6fI*\u0019!\u0011S;\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003\u0016\n-%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012\u0014AD2paf$C-\u001a4bk2$HeM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\u0011yJ\u000b\u0003\u0003^\t\u0015\u0015!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0003&B!!q\u0015BY\u001b\t\u0011IK\u0003\u0003\u0003,\n5\u0016\u0001\u00027b]\u001eT!Aa,\u0002\t)\fg/Y\u0005\u0005\u0003+\u0011I+\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u00038B\u0019AO!/\n\u0007\tmVOA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003B\n\u001d\u0007c\u0001;\u0003D&\u0019!QY;\u0003\u0007\u0005s\u0017\u0010C\u0005\u0002vZ\n\t\u00111\u0001\u00038\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003NB1!q\u001aBk\u0005\u0003l!A!5\u000b\u0007\tMW/\u0001\u0006d_2dWm\u0019;j_:LAAa6\u0003R\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\u0011\tC!8\t\u0013\u0005U\b(!AA\u0002\t\u0005\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BA!*\u0003d\"I\u0011Q_\u001d\u0002\u0002\u0003\u0007!qW\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!qW\u0001\ti>\u001cFO]5oOR\u0011!QU\u0001\u0007KF,\u0018\r\\:\u0015\t\t\u0005\"\u0011\u001f\u0005\n\u0003kd\u0014\u0011!a\u0001\u0005\u0003\f\u0001\u0003U=uQ>twk\u001c:lKJ\u001c8*Z=\u0011\u0007\t%dhE\u0003?\u0005s\u001c)\u0001\u0005\b\u0003|\u000e\u0005\u00111AA\u0002\u0003\u0007\u0011iFa\u001a\u000e\u0005\tu(b\u0001B\u0000k\u00069!/\u001e8uS6,\u0017\u0002BB\u0002\u0005{\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\u00199a!\u0004\u000e\u0005\r%!\u0002BB\u0006\u0005[\u000b!![8\n\t\t%3\u0011\u0002\u000b\u0003\u0005k\fQ!\u00199qYf$\"Ba\u001a\u0004\u0016\r]1\u0011DB\u000e\u0011\u001d\u0011i%\u0011a\u0001\u0003\u0007AqA!\u0015B\u0001\u0004\t\u0019\u0001C\u0004\u0003V\u0005\u0003\r!a\u0001\t\u000f\te\u0013\t1\u0001\u0003^\u00059QO\\1qa2LH\u0003BB\u0011\u0007[\u0001R\u0001^B\u0012\u0007OI1a!\nv\u0005\u0019y\u0005\u000f^5p]BYAo!\u000b\u0002\u0004\u0005\r\u00111\u0001B/\u0013\r\u0019Y#\u001e\u0002\u0007)V\u0004H.\u001a\u001b\t\u0013\r=\")!AA\u0002\t\u001d\u0014a\u0001=%a\u0005i\u0001/\u001f;i_:<vN]6feN,\"a!\u000e\u0011\u0011\r]2Q\bB4\u0007\u0003j!a!\u000f\u000b\t\rm\"\u0011[\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0019yd!\u000f\u0003\u000f!\u000b7\u000f['baB!11IB'\u001b\t\u0019)E\u0003\u0003\u0004H\r%\u0013A\u00029zi\"|gNC\u0002\u0004L-\f1!\u00199j\u0013\u0011\u0019ye!\u0012\u0003'AKH\u000f[8o/>\u00148.\u001a:GC\u000e$xN]=\u0002\u001dALH\u000f[8o/>\u00148.\u001a:tA\u0005\t\u0002.\u00193p_BTuNY'fi\u0006$\u0017\r^1\u0016\u0005\r]\u0003cBB-\u0007G\n\u0019a]\u0007\u0003\u00077RAa!\u0018\u0004`\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\t\r\u0005$QV\u0001\u0005kRLG.\u0003\u0003\u0004f\rm#!D\"p]\u000e,(O]3oi6\u000b\u0007/\u0001\niC\u0012|w\u000e\u001d&pE6+G/\u00193bi\u0006\u0004\u0013\u0001\u00043sSZ,'\u000fV7q\t&\u0014XCAB7!\u0015!81EA\u0002\u0003A!'/\u001b<feRk\u0007\u000fR5s?\u0012*\u0017\u000f\u0006\u0003\u0002n\u000eM\u0004\"CA{\u0011\u0006\u0005\t\u0019AB7\u00035!'/\u001b<feRk\u0007\u000fR5sA\u0005yQ\r_3dkR|'OQ1dW\u0016tG-\u0006\u0002\u0004|A)Aoa\t\u0004~A!1qPBC\u001b\t\u0019\tIC\u0002\u0004\u0004.\f\u0001\"\u001a=fGV$xN]\u0005\u0005\u0007\u000f\u001b\tIA\bFq\u0016\u001cW\u000f^8s\u0005\u0006\u001c7.\u001a8e\u0003M)\u00070Z2vi>\u0014()Y2lK:$w\fJ3r)\u0011\tio!$\t\u0013\u0005U8*!AA\u0002\rm\u0014\u0001E3yK\u000e,Ho\u001c:CC\u000e\\WM\u001c3!\u0003\u0011\u0019Ho\u001c9\u0015\u0005\u00055\u0018AE2sK\u0006$X\rU=uQ>twk\u001c:lKJ$Bb!'\u0004.\u000e=6\u0011WBZ\u0007k\u0003r\u0001^BN\u0007?\u001b)+C\u0002\u0004\u001eV\u0014a\u0001V;qY\u0016\u0014\u0004\u0003BB\"\u0007CKAaa)\u0004F\ta\u0001+\u001f;i_:<vN]6feB)Aoa\t\u0004(B!!qUBU\u0013\u0011\u0019YK!+\u0003\u001bA\u0013xnY3tg\"\u000bg\u000e\u001a7f\u0011\u001d\u0011iE\u0014a\u0001\u0003\u0007AqA!\u0015O\u0001\u0004\t\u0019\u0001C\u0004\u0003V9\u0003\r!a\u0001\t\u000f\tec\n1\u0001\u0003^!91q\u0017(A\u0002\t\u0005\u0012!C;tK\u0012\u000bW-\\8o))\u0019Ija/\u0004>\u000e}6\u0011\u0019\u0005\b\u0005\u001bz\u0005\u0019AA\u0002\u0011\u001d\u0011\tf\u0014a\u0001\u0003\u0007AqA!\u0017P\u0001\u0004\u0011i\u0006C\u0004\u00048>\u0003\rA!\t\u0015\u0015\re5QYBd\u0007\u0013\u001cY\rC\u0004\u0003NA\u0003\r!a\u0001\t\u000f\tE\u0003\u000b1\u0001\u0002\u0004!9!Q\u000b)A\u0002\u0005\r\u0001b\u0002B-!\u0002\u0007!QL\u0001\u0014I\u0016\u001cHO]8z!f$\bn\u001c8X_J\\WM\u001d\u000b\r\u0003[\u001c\tna5\u0004V\u000e]7\u0011\u001c\u0005\b\u0005\u001b\n\u0006\u0019AA\u0002\u0011\u001d\u0011\t&\u0015a\u0001\u0003\u0007AqA!\u0016R\u0001\u0004\t\u0019\u0001C\u0004\u0003ZE\u0003\rA!\u0018\t\u000f\rm\u0017\u000b1\u0001\u0004 \u00061qo\u001c:lKJ$\"\"!<\u0004`\u000e\u000581]Bs\u0011\u001d\u0011iE\u0015a\u0001\u0003\u0007AqA!\u0015S\u0001\u0004\t\u0019\u0001C\u0004\u0003ZI\u0003\rA!\u0018\t\u000f\rm'\u000b1\u0001\u0004 \u0006\u0019\"/\u001a7fCN,\u0007+\u001f;i_:<vN]6feRa\u0011Q^Bv\u0007[\u001cyo!=\u0004t\"9!QJ*A\u0002\u0005\r\u0001b\u0002B)'\u0002\u0007\u00111\u0001\u0005\b\u0005+\u001a\u0006\u0019AA\u0002\u0011\u001d\u0011If\u0015a\u0001\u0005;Bqaa7T\u0001\u0004\u0019y\n\u0006\u0006\u0002n\u000e]8\u0011`B~\u0007{DqA!\u0014U\u0001\u0004\t\u0019\u0001C\u0004\u0003RQ\u0003\r!a\u0001\t\u000f\teC\u000b1\u0001\u0003^!911\u001c+A\u0002\r}\u0015\u0001G5oSRL\u0017\r\\5{KNCWO\u001a4mK6\u000bg.Y4fe\u00069\u0012N\\5uS\u0006d\u0017N_3NK6|'/_'b]\u0006<WM\u001d\u000b\u0005\u0003[$)\u0001C\u0004\u0005\bY\u0003\rAa.\u0002\u001d9,X.V:bE2,7i\u001c:fg\"\u001a\u0001\u0001b\u0003\u0011\t\u00115A\u0011C\u0007\u0003\t\u001fQ1A!%l\u0013\u0011!\u0019\u0002b\u0004\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0011M\u0003\u0018M]6F]Z\u00042!a\u0015Y'\rA6/\u001f\u000b\u0003\t/\t1!\u001a8w+\t\ty,A\u0004f]Z|F%Z9\u0015\t\u00055HQ\u0005\u0005\n\u0003k\\\u0016\u0011!a\u0001\u0003\u007f\u000bA!\u001a8wA!\u001aA,a?\u0002!\u0011\u0014\u0018N^3s'f\u001cH/Z7OC6,\u0017!\u00053sSZ,'oU=ti\u0016lg*Y7fA\u0005\u0011R\r_3dkR|'oU=ti\u0016lg*Y7f\u0003M)\u00070Z2vi>\u00148+_:uK6t\u0015-\\3!\u0003\r\u0019X\r\u001e\u000b\u0005\u0003[$9\u0004C\u0004\u0005:\u0005\u0004\r!a0\u0002\u0003\u0015\f1aZ3u\u0003=\u0019'/Z1uK\u0012\u0013\u0018N^3s\u000b:4H\u0003DA`\t\u0003\"\u0019\u0005b\u0012\u0005R\u0011U\u0003bBAXG\u0002\u0007\u00111\u0017\u0005\b\t\u000b\u001a\u0007\u0019\u0001B\u0011\u0003\u001dI7\u000fT8dC2Dq\u0001\"\u0013d\u0001\u0004!Y%A\u0006mSN$XM\\3s\u0005V\u001c\b\u0003BAR\t\u001bJA\u0001b\u0014\u0002&\nyA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8\u000fC\u0004\u0005T\r\u0004\rAa.\u0002\u00119,XnQ8sKND\u0011\u0002b\u0016d!\u0003\u0005\r\u0001\"\u0017\u000275|7m[(viB,HoQ8n[&$8i\\8sI&t\u0017\r^8s!\u0015!81EAQ\u0003e\u0019'/Z1uK\u0012\u0013\u0018N^3s\u000b:4H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0011}#\u0006\u0002C-\u0005\u000b\u000b\u0011c\u0019:fCR,W\t_3dkR|'/\u00128w)A\ty\f\"\u001a\u0005h\u0011%DQ\u000eC9\tg\")\tC\u0004\u00020\u0016\u0004\r!a-\t\r},\u0007\u0019AA\u0002\u0011\u001d!Y'\u001aa\u0001\u0003\u0007\t1BY5oI\u0006#GM]3tg\"9AqN3A\u0002\u0005\r\u0011\u0001\u00035pgRt\u0017-\\3\t\u000f\u0011MS\r1\u0001\u00038\"9AQO3A\u0002\u0011]\u0014aD5p\u000b:\u001c'/\u001f9uS>t7*Z=\u0011\u000bQ\u001c\u0019\u0003\"\u001f\u0011\u000bQ$Y\bb \n\u0007\u0011uTOA\u0003BeJ\f\u0017\u0010E\u0002u\t\u0003K1\u0001b!v\u0005\u0011\u0011\u0015\u0010^3\t\u000f\u0011\u0015S\r1\u0001\u0003\"\u000511M]3bi\u0016$b#a0\u0005\f\u00125Eq\u0012CI\t+#Y\n\"(\u0005 \u0012\u0005F1\u0015\u0005\b\u0003_3\u0007\u0019AAZ\u0011\u0019yh\r1\u0001\u0002\u0004!9A1\u000e4A\u0002\u0005\r\u0001b\u0002CJM\u0002\u0007\u00111A\u0001\u0011C\u00124XM\u001d;jg\u0016\fE\r\u001a:fgNDq\u0001b&g\u0001\u0004!I*\u0001\u0003q_J$\b#\u0002;\u0004$\t]\u0006b\u0002C#M\u0002\u0007!\u0011\u0005\u0005\b\t\u000f1\u0007\u0019\u0001B\\\u0011\u001d!)H\u001aa\u0001\toB\u0011\u0002\"\u0013g!\u0003\u0005\r\u0001b\u0013\t\u0013\u0011]c\r%AA\u0002\u0011e\u0013\u0001E2sK\u0006$X\r\n3fM\u0006,H\u000e\u001e\u0013:+\t!IK\u000b\u0003\u0005L\t\u0015\u0015!E2sK\u0006$X\r\n3fM\u0006,H\u000e\u001e\u00132a\u0005\u0011RM\u001c<je>tW.\u001a8u\t\u0016$\u0018-\u001b7t)A!\t\fb/\u0005>\u0012=G1\u001bCm\t;$\t\u000f\u0005\u0005\u0002\u0006\t}\u00131\u0001CZ!\u0019\u0011i\u0004\".\u0005:&!Aq\u0017B&\u0005\r\u0019V-\u001d\t\bi\u000em\u00151AA\u0002\u0011\u001d\ty+\u001ba\u0001\u0003gCq\u0001b0j\u0001\u0004!\t-\u0001\u0006iC\u0012|w\u000e]\"p]\u001a\u0004B\u0001b1\u0005L6\u0011AQ\u0019\u0006\u0005\u0003_#9MC\u0002\u0005J6\fa\u0001[1e_>\u0004\u0018\u0002\u0002Cg\t\u000b\u0014QbQ8oM&<WO]1uS>t\u0007b\u0002CiS\u0002\u0007\u00111A\u0001\u000fg\u000eDW\rZ;mS:<Wj\u001c3f\u0011\u001d!).\u001ba\u0001\t/\f\u0011\"\u00193eK\u0012T\u0015M]:\u0011\r\tuBQWA\u0002\u0011\u001d!Y.\u001ba\u0001\t/\f!\"\u00193eK\u00124\u0015\u000e\\3t\u0011\u001d!y.\u001ba\u0001\t/\fQ\"\u00193eK\u0012\f%o\u00195jm\u0016\u001c\bb\u0002CrS\u0002\u0007!QL\u0001\u0012[\u0016$(/[2t!J|\u0007/\u001a:uS\u0016\u001c\b"
)
public class SparkEnv implements Logging {
   private volatile PythonWorkersKey$ PythonWorkersKey$module;
   private final String executorId;
   private final RpcEnv rpcEnv;
   private final Serializer serializer;
   private final Serializer closureSerializer;
   private final SerializerManager serializerManager;
   private final MapOutputTracker mapOutputTracker;
   private final BroadcastManager broadcastManager;
   private final BlockManager blockManager;
   private final SecurityManager securityManager;
   private final MetricsSystem metricsSystem;
   private final OutputCommitCoordinator outputCommitCoordinator;
   private final SparkConf conf;
   private volatile ShuffleManager _shuffleManager;
   private MemoryManager _memoryManager;
   private volatile boolean isStopped;
   private final HashMap pythonWorkers;
   private final ConcurrentMap hadoopJobMetadata;
   private Option driverTmpDir;
   private Option executorBackend;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static SparkEnv get() {
      return SparkEnv$.MODULE$.get();
   }

   public static void set(final SparkEnv e) {
      SparkEnv$.MODULE$.set(e);
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

   private PythonWorkersKey$ PythonWorkersKey() {
      if (this.PythonWorkersKey$module == null) {
         this.PythonWorkersKey$lzycompute$1();
      }

      return this.PythonWorkersKey$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public String executorId() {
      return this.executorId;
   }

   public RpcEnv rpcEnv() {
      return this.rpcEnv;
   }

   public Serializer serializer() {
      return this.serializer;
   }

   public Serializer closureSerializer() {
      return this.closureSerializer;
   }

   public SerializerManager serializerManager() {
      return this.serializerManager;
   }

   public MapOutputTracker mapOutputTracker() {
      return this.mapOutputTracker;
   }

   public BroadcastManager broadcastManager() {
      return this.broadcastManager;
   }

   public BlockManager blockManager() {
      return this.blockManager;
   }

   public SecurityManager securityManager() {
      return this.securityManager;
   }

   public MetricsSystem metricsSystem() {
      return this.metricsSystem;
   }

   public OutputCommitCoordinator outputCommitCoordinator() {
      return this.outputCommitCoordinator;
   }

   public SparkConf conf() {
      return this.conf;
   }

   private ShuffleManager _shuffleManager() {
      return this._shuffleManager;
   }

   private void _shuffleManager_$eq(final ShuffleManager x$1) {
      this._shuffleManager = x$1;
   }

   public ShuffleManager shuffleManager() {
      return this._shuffleManager();
   }

   private MemoryManager _memoryManager() {
      return this._memoryManager;
   }

   private void _memoryManager_$eq(final MemoryManager x$1) {
      this._memoryManager = x$1;
   }

   public MemoryManager memoryManager() {
      return this._memoryManager();
   }

   public boolean isStopped() {
      return this.isStopped;
   }

   public void isStopped_$eq(final boolean x$1) {
      this.isStopped = x$1;
   }

   private HashMap pythonWorkers() {
      return this.pythonWorkers;
   }

   public ConcurrentMap hadoopJobMetadata() {
      return this.hadoopJobMetadata;
   }

   public Option driverTmpDir() {
      return this.driverTmpDir;
   }

   public void driverTmpDir_$eq(final Option x$1) {
      this.driverTmpDir = x$1;
   }

   public Option executorBackend() {
      return this.executorBackend;
   }

   public void executorBackend_$eq(final Option x$1) {
      this.executorBackend = x$1;
   }

   public void stop() {
      if (!this.isStopped()) {
         this.isStopped_$eq(true);
         this.pythonWorkers().values().foreach((x$3) -> {
            $anonfun$stop$1(x$3);
            return BoxedUnit.UNIT;
         });
         this.mapOutputTracker().stop();
         if (this.shuffleManager() != null) {
            this.shuffleManager().stop();
         }

         this.broadcastManager().stop();
         this.blockManager().stop();
         this.blockManager().master().stop();
         this.metricsSystem().stop();
         this.outputCommitCoordinator().stop();
         this.rpcEnv().shutdown();
         this.rpcEnv().awaitTermination();
         Option var2 = this.driverTmpDir();
         if (var2 instanceof Some) {
            Some var3 = (Some)var2;
            String path = (String)var3.value();

            try {
               Utils$.MODULE$.deleteRecursively(new File(path));
               BoxedUnit var8 = BoxedUnit.UNIT;
            } catch (Exception var6) {
               this.logWarning((LogEntry).MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Exception while deleting Spark temp dir: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path)}))))), var6);
               BoxedUnit var7 = BoxedUnit.UNIT;
            }

         } else if (scala.None..MODULE$.equals(var2)) {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var2);
         }
      }
   }

   public synchronized Tuple2 createPythonWorker(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars, final boolean useDaemon) {
      PythonWorkersKey key = new PythonWorkersKey(pythonExec, workerModule, daemonModule, envVars);
      PythonWorkerFactory workerFactory = (PythonWorkerFactory)this.pythonWorkers().getOrElseUpdate(key, () -> new PythonWorkerFactory(pythonExec, workerModule, daemonModule, envVars, useDaemon));
      if (workerFactory.useDaemonEnabled() != useDaemon) {
         throw org.apache.spark.SparkException..MODULE$.internalError("PythonWorkerFactory is already created with useDaemon = " + workerFactory.useDaemonEnabled() + ", but now is requested with useDaemon = " + useDaemon + ". This is not allowed to change after the PythonWorkerFactory is created given the same key: " + key + ".");
      } else {
         return workerFactory.create();
      }
   }

   public Tuple2 createPythonWorker(final String pythonExec, final String workerModule, final scala.collection.immutable.Map envVars, final boolean useDaemon) {
      return this.createPythonWorker(pythonExec, workerModule, PythonWorkerFactory$.MODULE$.defaultDaemonModule(), envVars, useDaemon);
   }

   public Tuple2 createPythonWorker(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars) {
      boolean useDaemon = BoxesRunTime.unboxToBoolean(this.conf().get(Python$.MODULE$.PYTHON_USE_DAEMON()));
      return this.createPythonWorker(pythonExec, workerModule, daemonModule, envVars, useDaemon);
   }

   public synchronized void destroyPythonWorker(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars, final PythonWorker worker) {
      PythonWorkersKey key = new PythonWorkersKey(pythonExec, workerModule, daemonModule, envVars);
      this.pythonWorkers().get(key).foreach((x$4) -> {
         $anonfun$destroyPythonWorker$1(worker, x$4);
         return BoxedUnit.UNIT;
      });
   }

   public void destroyPythonWorker(final String pythonExec, final String workerModule, final scala.collection.immutable.Map envVars, final PythonWorker worker) {
      this.destroyPythonWorker(pythonExec, workerModule, PythonWorkerFactory$.MODULE$.defaultDaemonModule(), envVars, worker);
   }

   public synchronized void releasePythonWorker(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars, final PythonWorker worker) {
      PythonWorkersKey key = new PythonWorkersKey(pythonExec, workerModule, daemonModule, envVars);
      this.pythonWorkers().get(key).foreach((x$5) -> {
         $anonfun$releasePythonWorker$1(worker, x$5);
         return BoxedUnit.UNIT;
      });
   }

   public void releasePythonWorker(final String pythonExec, final String workerModule, final scala.collection.immutable.Map envVars, final PythonWorker worker) {
      this.releasePythonWorker(pythonExec, workerModule, PythonWorkerFactory$.MODULE$.defaultDaemonModule(), envVars, worker);
   }

   public void initializeShuffleManager() {
      boolean var2;
      ShuffleManager$ var10001;
      SparkConf var10002;
      label21: {
         label20: {
            Preconditions.checkState(this._shuffleManager() == null, "Shuffle manager already initialized to %s", this._shuffleManager());
            var10001 = ShuffleManager$.MODULE$;
            var10002 = this.conf();
            String var10003 = this.executorId();
            String var1 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (var10003 == null) {
               if (var1 == null) {
                  break label20;
               }
            } else if (var10003.equals(var1)) {
               break label20;
            }

            var2 = false;
            break label21;
         }

         var2 = true;
      }

      this._shuffleManager_$eq(var10001.create(var10002, var2));
   }

   public void initializeMemoryManager(final int numUsableCores) {
      Preconditions.checkState(this.memoryManager() == null, "Memory manager already initialized to %s", this._memoryManager());
      this._memoryManager_$eq(UnifiedMemoryManager$.MODULE$.apply(this.conf(), numUsableCores));
   }

   private final void PythonWorkersKey$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PythonWorkersKey$module == null) {
            this.PythonWorkersKey$module = new PythonWorkersKey$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final PythonWorkerFactory x$3) {
      x$3.stop();
   }

   // $FF: synthetic method
   public static final void $anonfun$destroyPythonWorker$1(final PythonWorker worker$1, final PythonWorkerFactory x$4) {
      x$4.stopWorker(worker$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$releasePythonWorker$1(final PythonWorker worker$2, final PythonWorkerFactory x$5) {
      x$5.releaseWorker(worker$2);
   }

   public SparkEnv(final String executorId, final RpcEnv rpcEnv, final Serializer serializer, final Serializer closureSerializer, final SerializerManager serializerManager, final MapOutputTracker mapOutputTracker, final BroadcastManager broadcastManager, final BlockManager blockManager, final SecurityManager securityManager, final MetricsSystem metricsSystem, final OutputCommitCoordinator outputCommitCoordinator, final SparkConf conf) {
      this.executorId = executorId;
      this.rpcEnv = rpcEnv;
      this.serializer = serializer;
      this.closureSerializer = closureSerializer;
      this.serializerManager = serializerManager;
      this.mapOutputTracker = mapOutputTracker;
      this.broadcastManager = broadcastManager;
      this.blockManager = blockManager;
      this.securityManager = securityManager;
      this.metricsSystem = metricsSystem;
      this.outputCommitCoordinator = outputCommitCoordinator;
      this.conf = conf;
      Logging.$init$(this);
      this.isStopped = false;
      this.pythonWorkers = (HashMap)scala.collection.mutable.HashMap..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.hadoopJobMetadata = CacheBuilder.newBuilder().maximumSize(1000L).softValues().build().asMap();
      this.driverTmpDir = scala.None..MODULE$;
      this.executorBackend = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class PythonWorkersKey implements Product, Serializable {
      private final String pythonExec;
      private final String workerModule;
      private final String daemonModule;
      private final scala.collection.immutable.Map envVars;
      // $FF: synthetic field
      public final SparkEnv $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String pythonExec() {
         return this.pythonExec;
      }

      public String workerModule() {
         return this.workerModule;
      }

      public String daemonModule() {
         return this.daemonModule;
      }

      public scala.collection.immutable.Map envVars() {
         return this.envVars;
      }

      public PythonWorkersKey copy(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars) {
         return this.org$apache$spark$SparkEnv$PythonWorkersKey$$$outer().new PythonWorkersKey(pythonExec, workerModule, daemonModule, envVars);
      }

      public String copy$default$1() {
         return this.pythonExec();
      }

      public String copy$default$2() {
         return this.workerModule();
      }

      public String copy$default$3() {
         return this.daemonModule();
      }

      public scala.collection.immutable.Map copy$default$4() {
         return this.envVars();
      }

      public String productPrefix() {
         return "PythonWorkersKey";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.pythonExec();
            }
            case 1 -> {
               return this.workerModule();
            }
            case 2 -> {
               return this.daemonModule();
            }
            case 3 -> {
               return this.envVars();
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
         return x$1 instanceof PythonWorkersKey;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "pythonExec";
            }
            case 1 -> {
               return "workerModule";
            }
            case 2 -> {
               return "daemonModule";
            }
            case 3 -> {
               return "envVars";
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
         boolean var12;
         if (this != x$1) {
            label76: {
               if (x$1 instanceof PythonWorkersKey && ((PythonWorkersKey)x$1).org$apache$spark$SparkEnv$PythonWorkersKey$$$outer() == this.org$apache$spark$SparkEnv$PythonWorkersKey$$$outer()) {
                  label66: {
                     PythonWorkersKey var4 = (PythonWorkersKey)x$1;
                     String var10000 = this.pythonExec();
                     String var5 = var4.pythonExec();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label66;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label66;
                     }

                     var10000 = this.workerModule();
                     String var6 = var4.workerModule();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label66;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label66;
                     }

                     var10000 = this.daemonModule();
                     String var7 = var4.daemonModule();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label66;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label66;
                     }

                     scala.collection.immutable.Map var11 = this.envVars();
                     scala.collection.immutable.Map var8 = var4.envVars();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label66;
                        }
                     } else if (!var11.equals(var8)) {
                        break label66;
                     }

                     if (var4.canEqual(this)) {
                        break label76;
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      // $FF: synthetic method
      public SparkEnv org$apache$spark$SparkEnv$PythonWorkersKey$$$outer() {
         return this.$outer;
      }

      public PythonWorkersKey(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars) {
         this.pythonExec = pythonExec;
         this.workerModule = workerModule;
         this.daemonModule = daemonModule;
         this.envVars = envVars;
         if (SparkEnv.this == null) {
            throw null;
         } else {
            this.$outer = SparkEnv.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class PythonWorkersKey$ extends AbstractFunction4 implements Serializable {
      // $FF: synthetic field
      private final SparkEnv $outer;

      public final String toString() {
         return "PythonWorkersKey";
      }

      public PythonWorkersKey apply(final String pythonExec, final String workerModule, final String daemonModule, final scala.collection.immutable.Map envVars) {
         return this.$outer.new PythonWorkersKey(pythonExec, workerModule, daemonModule, envVars);
      }

      public Option unapply(final PythonWorkersKey x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(x$0.pythonExec(), x$0.workerModule(), x$0.daemonModule(), x$0.envVars())));
      }

      public PythonWorkersKey$() {
         if (SparkEnv.this == null) {
            throw null;
         } else {
            this.$outer = SparkEnv.this;
            super();
         }
      }
   }
}
