package org.apache.spark;

import java.io.Closeable;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import java.util.Stack;
import javax.annotation.concurrent.GuardedBy;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.shuffle.FetchFailedException;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.TaskCompletionListener;
import org.apache.spark.util.TaskCompletionListenerException;
import org.apache.spark.util.TaskFailureListener;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\rmf!\u0002)R\u0001E;\u0006\u0002\u00032\u0001\u0005\u000b\u0007I\u0011\t3\t\u0011-\u0004!\u0011!Q\u0001\n\u0015D\u0001\u0002\u001c\u0001\u0003\u0006\u0004%\t\u0005\u001a\u0005\t[\u0002\u0011\t\u0011)A\u0005K\"Aa\u000e\u0001BC\u0002\u0013\u0005C\r\u0003\u0005p\u0001\t\u0005\t\u0015!\u0003f\u0011!\u0001\bA!b\u0001\n\u0003\n\b\u0002C;\u0001\u0005\u0003\u0005\u000b\u0011\u0002:\t\u0011Y\u0004!Q1A\u0005B\u0011D\u0001b\u001e\u0001\u0003\u0002\u0003\u0006I!\u001a\u0005\tq\u0002\u0011)\u0019!C!I\"A\u0011\u0010\u0001B\u0001B\u0003%Q\r\u0003\u0005{\u0001\t\u0015\r\u0011\"\u0011|\u0011%\t)\u0001\u0001B\u0001B\u0003%A\u0010\u0003\u0006\u0002\b\u0001\u0011\t\u0011)A\u0005\u0003\u0013A!\"!\u0007\u0001\u0005\u000b\u0007I\u0011BA\u000e\u0011)\tI\u0003\u0001B\u0001B\u0003%\u0011Q\u0004\u0005\u000b\u0003g\u0001!Q1A\u0005B\u0005U\u0002BCA\"\u0001\t\u0005\t\u0015!\u0003\u00028!I\u0011Q\t\u0001\u0003\u0006\u0004%\t\u0005\u001a\u0005\n\u0003\u000f\u0002!\u0011!Q\u0001\n\u0015D!\"!\u0013\u0001\u0005\u000b\u0007I\u0011IA&\u0011)\t)\b\u0001B\u0001B\u0003%\u0011Q\n\u0005\b\u0003o\u0002A\u0011AA=\u0011%\t)\n\u0001b\u0001\n\u0013\t9\n\u0003\u0005\u0002*\u0002\u0001\u000b\u0011BAM\u0011%\ti\u000b\u0001b\u0001\n\u0013\ty\u000b\u0003\u0005\u0002:\u0002\u0001\u000b\u0011BAY\u0011%\ti\f\u0001a\u0001\n\u0013\ty\fC\u0005\u0002T\u0002\u0001\r\u0011\"\u0003\u0002V\"A\u0011\u0011\u001d\u0001!B\u0013\t\t\rC\u0005\u0002n\u0002\u0001\r\u0011\"\u0003\u0002p\"I\u00111\u001f\u0001A\u0002\u0013%\u0011Q\u001f\u0005\t\u0003s\u0004\u0001\u0015)\u0003\u0002r\"q\u0011Q \u0001\u0005\u0002\u0003\u0015\t\u00111A\u0005\n\u0005}\b\"\u0003B\u0005\u0001\u0001\u0007I\u0011\u0002B\u0006\u0011-\u0011y\u0001\u0001B\u0001\u0002\u0003\u0006KA!\u0001\t\u0013\tE\u0001\u00011A\u0005\n\tM\u0001\"\u0003B\u000e\u0001\u0001\u0007I\u0011\u0002B\u000f\u0011!\u0011\t\u0003\u0001Q!\n\tU\u0001\"\u0003B\u0012\u0001\u0001\u0007I\u0011\u0002B\n\u0011%\u0011)\u0003\u0001a\u0001\n\u0013\u00119\u0003\u0003\u0005\u0003,\u0001\u0001\u000b\u0015\u0002B\u000b\u0011%\u0011i\u0003\u0001a\u0001\n\u0013\u0011y\u0003C\u0005\u0003F\u0001\u0001\r\u0011\"\u0003\u0003H!A!1\n\u0001!B\u0013\u0011\t\u0004C\u0005\u0003N\u0001\u0001\r\u0011\"\u0003\u0003P!I!q\f\u0001A\u0002\u0013%!\u0011\r\u0005\t\u0005K\u0002\u0001\u0015)\u0003\u0003R!9!\u0011\u000e\u0001\u0005B\t-\u0004b\u0002B:\u0001\u0011\u0005#Q\u000f\u0005\b\u0005s\u0002A\u0011\tB>\u0011!\u0011\t\t\u0001C!#\n\r\u0005\u0002\u0003BE\u0001\u0011\u0005\u0013Ka#\t\u000f\t=\u0005\u0001\"\u0003\u0003\u0012\"9!Q\u0013\u0001\u0005\n\t]\u0005b\u0002BN\u0001\u0011%!Q\u0014\u0005\t\u0005\u001f\u0004A\u0011I)\u0003R\"A!q\u001b\u0001\u0005BE\u0013I\u000e\u0003\u0005\u0003\\\u0002!\t%\u0015Bo\u0011\u001d\u0011y\u000e\u0001C!\u0005CDqAa@\u0001\t\u0003\u0012\t\u000fC\u0004\u0004\u0002\u0001!\tE!9\t\u000f\r\r\u0001\u0001\"\u0011\u0004\u0006!911\u0002\u0001\u0005B\r5\u0001\u0002CB\u0013\u0001\u0011\u0005\u0013ka\n\t\u0011\r\u0005\u0003\u0001\"\u0011R\u0007\u0007B\u0001ba\u0012\u0001\t\u0003\n&q\n\u0005\t\u0007\u0013\u0002A\u0011I)\u0004L!91Q\n\u0001\u0005B\t\u0005\bbBB(\u0001\u0011\u00053\u0011\u000b\u0005\b\u00073\u0002A\u0011AB.\u000f)\u0019Y(UA\u0001\u0012\u0003\t6Q\u0010\u0004\n!F\u000b\t\u0011#\u0001R\u0007\u007fBq!a\u001eK\t\u0003\u0019i\tC\u0005\u0004\u0010*\u000b\n\u0011\"\u0001\u0004\u0012\"I1Q\u0015&\u0012\u0002\u0013\u00051q\u0015\u0005\n\u0007WS\u0015\u0013!C\u0001\u0007[C\u0011b!-K\u0003\u0003%Iaa-\u0003\u001fQ\u000b7o[\"p]R,\u0007\u0010^%na2T!AU*\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q+\u0016AB1qC\u000eDWMC\u0001W\u0003\ry'oZ\n\u0004\u0001ac\u0006CA-[\u001b\u0005\t\u0016BA.R\u0005-!\u0016m]6D_:$X\r\u001f;\u0011\u0005u\u0003W\"\u00010\u000b\u0005}\u000b\u0016\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0005t&a\u0002'pO\u001eLgnZ\u0001\bgR\fw-Z%e\u0007\u0001)\u0012!\u001a\t\u0003M&l\u0011a\u001a\u0006\u0002Q\u0006)1oY1mC&\u0011!n\u001a\u0002\u0004\u0013:$\u0018\u0001C:uC\u001e,\u0017\n\u001a\u0011\u0002%M$\u0018mZ3BiR,W\u000e\u001d;Ok6\u0014WM]\u0001\u0014gR\fw-Z!ui\u0016l\u0007\u000f\u001e(v[\n,'\u000fI\u0001\fa\u0006\u0014H/\u001b;j_:LE-\u0001\u0007qCJ$\u0018\u000e^5p]&#\u0007%A\u0007uCN\\\u0017\t\u001e;f[B$\u0018\nZ\u000b\u0002eB\u0011am]\u0005\u0003i\u001e\u0014A\u0001T8oO\u0006qA/Y:l\u0003R$X-\u001c9u\u0013\u0012\u0004\u0013!D1ui\u0016l\u0007\u000f\u001e(v[\n,'/\u0001\bbiR,W\u000e\u001d;Ok6\u0014WM\u001d\u0011\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u00039qW/\u001c)beRLG/[8og\u0002\n\u0011\u0003^1tW6+Wn\u001c:z\u001b\u0006t\u0017mZ3s+\u0005a\bcA?\u0002\u00025\taP\u0003\u0002\u0000#\u00061Q.Z7pefL1!a\u0001\u007f\u0005E!\u0016m]6NK6|'/_'b]\u0006<WM]\u0001\u0013i\u0006\u001c8.T3n_JLX*\u00198bO\u0016\u0014\b%A\bm_\u000e\fG\u000e\u0015:pa\u0016\u0014H/[3t!\u0011\tY!!\u0006\u000e\u0005\u00055!\u0002BA\b\u0003#\tA!\u001e;jY*\u0011\u00111C\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002\u0018\u00055!A\u0003)s_B,'\u000f^5fg\u0006iQ.\u001a;sS\u000e\u001c8+_:uK6,\"!!\b\u0011\t\u0005}\u0011QE\u0007\u0003\u0003CQ1!a\tR\u0003\u001diW\r\u001e:jGNLA!a\n\u0002\"\tiQ*\u001a;sS\u000e\u001c8+_:uK6\fa\"\\3ue&\u001c7oU=ti\u0016l\u0007\u0005K\u0002\u0012\u0003[\u00012AZA\u0018\u0013\r\t\td\u001a\u0002\niJ\fgn]5f]R\f1\u0002^1tW6+GO]5dgV\u0011\u0011q\u0007\t\u0005\u0003s\ty$\u0004\u0002\u0002<)\u0019\u0011QH)\u0002\u0011\u0015DXmY;u_JLA!!\u0011\u0002<\tYA+Y:l\u001b\u0016$(/[2t\u00031!\u0018m]6NKR\u0014\u0018nY:!\u0003\u0011\u0019\u0007/^:\u0002\u000b\r\u0004Xo\u001d\u0011\u0002\u0013I,7o\\;sG\u0016\u001cXCAA'!!\ty%!\u0018\u0002d\u0005%d\u0002BA)\u00033\u00022!a\u0015h\u001b\t\t)FC\u0002\u0002X\r\fa\u0001\u0010:p_Rt\u0014bAA.O\u00061\u0001K]3eK\u001aLA!a\u0018\u0002b\t\u0019Q*\u00199\u000b\u0007\u0005ms\r\u0005\u0003\u0002P\u0005\u0015\u0014\u0002BA4\u0003C\u0012aa\u0015;sS:<\u0007\u0003BA6\u0003cj!!!\u001c\u000b\u0007\u0005=\u0014+\u0001\u0005sKN|WO]2f\u0013\u0011\t\u0019(!\u001c\u0003'I+7o\\;sG\u0016LeNZ8s[\u0006$\u0018n\u001c8\u0002\u0015I,7o\\;sG\u0016\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u001b\u0003w\ni(a \u0002\u0002\u0006\r\u0015QQAD\u0003\u0013\u000bY)!$\u0002\u0010\u0006E\u00151\u0013\t\u00033\u0002AQA\u0019\rA\u0002\u0015DQ\u0001\u001c\rA\u0002\u0015DQA\u001c\rA\u0002\u0015DQ\u0001\u001d\rA\u0002IDQA\u001e\rA\u0002\u0015DQ\u0001\u001f\rA\u0002\u0015DQA\u001f\rA\u0002qDq!a\u0002\u0019\u0001\u0004\tI\u0001C\u0004\u0002\u001aa\u0001\r!!\b\t\u0013\u0005M\u0002\u0004%AA\u0002\u0005]\u0002\u0002CA#1A\u0005\t\u0019A3\t\u0013\u0005%\u0003\u0004%AA\u0002\u00055\u0013aE8o\u0007>l\u0007\u000f\\3uK\u000e\u000bG\u000e\u001c2bG.\u001cXCAAM!\u0019\tY!a'\u0002 &!\u0011QTA\u0007\u0005\u0015\u0019F/Y2l!\u0011\t\t+!*\u000e\u0005\u0005\r&bAA\b#&!\u0011qUAR\u0005Y!\u0016m]6D_6\u0004H.\u001a;j_:d\u0015n\u001d;f]\u0016\u0014\u0018\u0001F8o\u0007>l\u0007\u000f\\3uK\u000e\u000bG\u000e\u001c2bG.\u001c\b\u0005K\u0002\u001b\u0003[\t!c\u001c8GC&dWO]3DC2d'-Y2lgV\u0011\u0011\u0011\u0017\t\u0007\u0003\u0017\tY*a-\u0011\t\u0005\u0005\u0016QW\u0005\u0005\u0003o\u000b\u0019KA\nUCN\\g)Y5mkJ,G*[:uK:,'/A\np]\u001a\u000b\u0017\u000e\\;sK\u000e\u000bG\u000e\u001c2bG.\u001c\b\u0005K\u0002\u001d\u0003[\t\u0001\u0004\\5ti\u0016tWM]%om>\u001c\u0017\r^5p]RC'/Z1e+\t\t\t\rE\u0003g\u0003\u0007\f9-C\u0002\u0002F\u001e\u0014aa\u00149uS>t\u0007\u0003BAe\u0003\u001fl!!a3\u000b\t\u00055\u0017\u0011C\u0001\u0005Y\u0006tw-\u0003\u0003\u0002R\u0006-'A\u0002+ie\u0016\fG-\u0001\u000fmSN$XM\\3s\u0013:4xnY1uS>tG\u000b\u001b:fC\u0012|F%Z9\u0015\t\u0005]\u0017Q\u001c\t\u0004M\u0006e\u0017bAAnO\n!QK\\5u\u0011%\tyNHA\u0001\u0002\u0004\t\t-A\u0002yIE\n\u0011\u0004\\5ti\u0016tWM]%om>\u001c\u0017\r^5p]RC'/Z1eA!\u001aq$!\f)\u0007}\t9\u000fE\u0002g\u0003SL1!a;h\u0005!1x\u000e\\1uS2,\u0017A\u0004:fCN|g.\u00134LS2dW\rZ\u000b\u0003\u0003c\u0004RAZAb\u0003G\n!C]3bg>t\u0017JZ&jY2,Gm\u0018\u0013fcR!\u0011q[A|\u0011%\ty.IA\u0001\u0002\u0004\t\t0A\bsK\u0006\u001cxN\\%g\u0017&dG.\u001a3!Q\r\u0011\u0013q]\u0001:_J<G%\u00199bG\",Ge\u001d9be.$C+Y:l\u0007>tG/\u001a=u\u00136\u0004H\u000e\n\u0013qK:$\u0017N\\4J]R,'O];qiJ+\u0017/^3tiV\u0011!\u0011\u0001\t\u0006M\u0006\r'1\u0001\t\bM\n\u0015\u0011\u0011YA2\u0013\r\u00119a\u001a\u0002\u0007)V\u0004H.\u001a\u001a\u00027A,g\u000eZ5oO&sG/\u001a:skB$(+Z9vKN$x\fJ3r)\u0011\t9N!\u0004\t\u0013\u0005}G%!AA\u0002\t\u0005\u0011AO8sO\u0012\n\u0007/Y2iK\u0012\u001a\b/\u0019:lIQ\u000b7o[\"p]R,\u0007\u0010^%na2$C\u0005]3oI&tw-\u00138uKJ\u0014X\u000f\u001d;SKF,Xm\u001d;!\u00039y\u0016N\u001c;feJ,\b\u000f^5cY\u0016,\"A!\u0006\u0011\u0007\u0019\u00149\"C\u0002\u0003\u001a\u001d\u0014qAQ8pY\u0016\fg.\u0001\n`S:$XM\u001d:vaRL'\r\\3`I\u0015\fH\u0003BAl\u0005?A\u0011\"a8(\u0003\u0003\u0005\rA!\u0006\u0002\u001f}Kg\u000e^3seV\u0004H/\u001b2mK\u0002\n\u0011bY8na2,G/\u001a3\u0002\u001b\r|W\u000e\u001d7fi\u0016$w\fJ3r)\u0011\t9N!\u000b\t\u0013\u0005}'&!AA\u0002\tU\u0011AC2p[BdW\r^3eA\u0005ya-Y5mkJ,7)Y;tK>\u0003H/\u0006\u0002\u00032A)a-a1\u00034A!!Q\u0007B \u001d\u0011\u00119Da\u000f\u000f\t\u0005M#\u0011H\u0005\u0002Q&\u0019!QH4\u0002\u000fA\f7m[1hK&!!\u0011\tB\"\u0005%!\u0006N]8xC\ndWMC\u0002\u0003>\u001d\f1CZ1jYV\u0014XmQ1vg\u0016|\u0005\u000f^0%KF$B!a6\u0003J!I\u0011q\\\u0017\u0002\u0002\u0003\u0007!\u0011G\u0001\u0011M\u0006LG.\u001e:f\u0007\u0006,8/Z(qi\u0002\nQc\u00184fi\u000eDg)Y5mK\u0012,\u0005pY3qi&|g.\u0006\u0002\u0003RA)a-a1\u0003TA!!Q\u000bB.\u001b\t\u00119FC\u0002\u0003ZE\u000bqa\u001d5vM\u001adW-\u0003\u0003\u0003^\t]#\u0001\u0006$fi\u000eDg)Y5mK\u0012,\u0005pY3qi&|g.A\r`M\u0016$8\r\u001b$bS2,G-\u0012=dKB$\u0018n\u001c8`I\u0015\fH\u0003BAl\u0005GB\u0011\"a81\u0003\u0003\u0005\rA!\u0015\u0002-}3W\r^2i\r\u0006LG.\u001a3Fq\u000e,\u0007\u000f^5p]\u0002B3!MAt\u0003e\tG\r\u001a+bg.\u001cu.\u001c9mKRLwN\u001c'jgR,g.\u001a:\u0015\t\t5$qN\u0007\u0002\u0001!9!\u0011\u000f\u001aA\u0002\u0005}\u0015\u0001\u00037jgR,g.\u001a:\u0002-\u0005$G\rV1tW\u001a\u000b\u0017\u000e\\;sK2K7\u000f^3oKJ$BA!\u001c\u0003x!9!\u0011O\u001aA\u0002\u0005M\u0016!\u0004:fg>,(oY3t\u00156\u000b\u0007\u000f\u0006\u0002\u0003~AA\u00111\u0002B@\u0003G\nI'\u0003\u0003\u0002`\u00055\u0011AD7be.$\u0016m]6GC&dW\r\u001a\u000b\u0005\u0003/\u0014)\tC\u0004\u0003\bV\u0002\rAa\r\u0002\u000b\u0015\u0014(o\u001c:\u0002#5\f'o\u001b+bg.\u001cu.\u001c9mKR,G\r\u0006\u0003\u0002X\n5\u0005b\u0002BDm\u0001\u0007!\u0011G\u0001\u001eS:4xn[3UCN\\7i\\7qY\u0016$\u0018n\u001c8MSN$XM\\3sgR!\u0011q\u001bBJ\u0011\u001d\u00119i\u000ea\u0001\u0005c\t!$\u001b8w_.,G+Y:l\r\u0006LG.\u001e:f\u0019&\u001cH/\u001a8feN$B!a6\u0003\u001a\"9!q\u0011\u001dA\u0002\tM\u0012aD5om>\\W\rT5ti\u0016tWM]:\u0016\t\t}%\u0011\u0017\u000b\t\u0005C\u0013\u0019M!3\u0003NR!\u0011q\u001bBR\u0011\u001d\u0011)+\u000fa\u0001\u0005O\u000b\u0001bY1mY\n\f7m\u001b\t\bM\n%&QVAl\u0013\r\u0011Yk\u001a\u0002\n\rVt7\r^5p]F\u0002BAa,\u000322\u0001Aa\u0002BZs\t\u0007!Q\u0017\u0002\u0002)F!!q\u0017B_!\r1'\u0011X\u0005\u0004\u0005w;'a\u0002(pi\"Lgn\u001a\t\u0004M\n}\u0016b\u0001BaO\n\u0019\u0011I\\=\t\u000f\t\u0015\u0017\b1\u0001\u0003H\u0006IA.[:uK:,'o\u001d\t\u0007\u0003\u0017\tYJ!,\t\u000f\t-\u0017\b1\u0001\u0002d\u0005!a.Y7f\u0011\u001d\u00119)\u000fa\u0001\u0005c\tq\"\\1sW&sG/\u001a:skB$X\r\u001a\u000b\u0005\u0003/\u0014\u0019\u000eC\u0004\u0003Vj\u0002\r!a\u0019\u0002\rI,\u0017m]8o\u0003UY\u0017\u000e\u001c7UCN\\\u0017JZ%oi\u0016\u0014(/\u001e9uK\u0012$\"!a6\u0002\u001b\u001d,GoS5mYJ+\u0017m]8o)\t\t\t0A\u0006jg\u000e{W\u000e\u001d7fi\u0016$GC\u0001B\u000bQ\u001di$Q\u001dB}\u0005w\u0004BAa:\u0003v6\u0011!\u0011\u001e\u0006\u0005\u0005W\u0014i/\u0001\u0006d_:\u001cWO\u001d:f]RTAAa<\u0003r\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005\tM\u0018!\u00026bm\u0006D\u0018\u0002\u0002B|\u0005S\u0014\u0011bR;be\u0012,GMQ=\u0002\u000bY\fG.^3\"\u0005\tu\u0018\u0001\u0002;iSN\f\u0001\"[:GC&dW\rZ\u0001\u000eSNLe\u000e^3seV\u0004H/\u001a3\u0002!\u001d,G\u000fT8dC2\u0004&o\u001c9feRLH\u0003BA2\u0007\u000fAqa!\u0003A\u0001\u0004\t\u0019'A\u0002lKf\f\u0011cZ3u\u001b\u0016$(/[2t'>,(oY3t)\u0011\u0019ya!\t\u0011\r\tU2\u0011CB\u000b\u0013\u0011\u0019\u0019Ba\u0011\u0003\u0007M+\u0017\u000f\u0005\u0003\u0004\u0018\ruQBAB\r\u0015\u0011\u0019Y\"!\t\u0002\rM|WO]2f\u0013\u0011\u0019yb!\u0007\u0003\rM{WO]2f\u0011\u001d\u0019\u0019#\u0011a\u0001\u0003G\n!b]8ve\u000e,g*Y7f\u0003M\u0011XmZ5ti\u0016\u0014\u0018iY2v[Vd\u0017\r^8s)\u0011\t9n!\u000b\t\u000f\r-\"\t1\u0001\u0004.\u0005\t\u0011\r\r\u0004\u00040\r]2Q\b\t\t\u0003C\u001b\td!\u000e\u0004<%!11GAR\u00055\t5mY;nk2\fGo\u001c:WeA!!qVB\u001c\t1\u0019Id!\u000b\u0002\u0002\u0003\u0005)\u0011\u0001B[\u0005\ryF%\r\t\u0005\u0005_\u001bi\u0004\u0002\u0007\u0004@\r%\u0012\u0011!A\u0001\u0006\u0003\u0011)LA\u0002`II\nab]3u\r\u0016$8\r\u001b$bS2,G\r\u0006\u0003\u0002X\u000e\u0015\u0003bBB$\u0007\u0002\u0007!1K\u0001\fM\u0016$8\r\u001b$bS2,G-\u0001\nhKRdunY1m!J|\u0007/\u001a:uS\u0016\u001cXCAA\u0005\u00035Ig\u000e^3seV\u0004H/\u001b2mK\u0006\u0001\u0002/\u001a8eS:<\u0017J\u001c;feJ,\b\u000f\u001e\u000b\u0007\u0003/\u001c\u0019fa\u0016\t\u000f\rUs\t1\u0001\u0002B\u0006\tB\u000f\u001b:fC\u0012$v.\u00138uKJ\u0014X\u000f\u001d;\t\u000f\tUw\t1\u0001\u0002d\u0005i2M]3bi\u0016\u0014Vm]8ve\u000e,WK\\5oi\u0016\u0014(/\u001e9uS\nd\u00170\u0006\u0003\u0004^\r\u0005D\u0003BB0\u0007c\u0002BAa,\u0004b\u00119!1\u0017%C\u0002\r\r\u0014\u0003\u0002B\\\u0007K\u0002Baa\u001a\u0004n5\u00111\u0011\u000e\u0006\u0005\u0007W\n\t\"\u0001\u0002j_&!1qNB5\u0005%\u0019En\\:fC\ndW\r\u0003\u0005\u0004t!#\t\u0019AB;\u0003=\u0011Xm]8ve\u000e,')^5mI\u0016\u0014\b#\u00024\u0004x\r}\u0013bAB=O\nAAHY=oC6,g(A\bUCN\\7i\u001c8uKb$\u0018*\u001c9m!\tI&jE\u0003K\u0007\u0003\u001b9\tE\u0002g\u0007\u0007K1a!\"h\u0005\u0019\te.\u001f*fMB!1qMBE\u0013\u0011\u0019Yi!\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\ru\u0014\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013\u0007M\u000b\u0003\u0007'SC!a\u000e\u0004\u0016.\u00121q\u0013\t\u0005\u00073\u001b\t+\u0004\u0002\u0004\u001c*!1QTBP\u0003%)hn\u00195fG.,GMC\u0002\u0003p\u001eLAaa)\u0004\u001c\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132cU\u00111\u0011\u0016\u0016\u0004K\u000eU\u0015\u0001\b\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013GM\u000b\u0003\u0007_SC!!\u0014\u0004\u0016\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u00111Q\u0017\t\u0005\u0003\u0013\u001c9,\u0003\u0003\u0004:\u0006-'AB(cU\u0016\u001cG\u000f"
)
public class TaskContextImpl extends TaskContext implements Logging {
   private final int stageId;
   private final int stageAttemptNumber;
   private final int partitionId;
   private final long taskAttemptId;
   private final int attemptNumber;
   private final int numPartitions;
   private final TaskMemoryManager taskMemoryManager;
   private final Properties localProperties;
   private final transient MetricsSystem metricsSystem;
   private final TaskMetrics taskMetrics;
   private final int cpus;
   private final Map resources;
   private final transient Stack onCompleteCallbacks;
   private final transient Stack onFailureCallbacks;
   private transient volatile Option listenerInvocationThread;
   private volatile Option reasonIfKilled;
   private Option org$apache$spark$TaskContextImpl$$pendingInterruptRequest;
   private boolean _interruptible;
   private boolean completed;
   private Option failureCauseOpt;
   private volatile Option _fetchFailedException;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map $lessinit$greater$default$12() {
      return TaskContextImpl$.MODULE$.$lessinit$greater$default$12();
   }

   public static int $lessinit$greater$default$11() {
      return TaskContextImpl$.MODULE$.$lessinit$greater$default$11();
   }

   public static TaskMetrics $lessinit$greater$default$10() {
      return TaskContextImpl$.MODULE$.$lessinit$greater$default$10();
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

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptNumber() {
      return this.stageAttemptNumber;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public long taskAttemptId() {
      return this.taskAttemptId;
   }

   public int attemptNumber() {
      return this.attemptNumber;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public TaskMemoryManager taskMemoryManager() {
      return this.taskMemoryManager;
   }

   private MetricsSystem metricsSystem() {
      return this.metricsSystem;
   }

   public TaskMetrics taskMetrics() {
      return this.taskMetrics;
   }

   public int cpus() {
      return this.cpus;
   }

   public Map resources() {
      return this.resources;
   }

   private Stack onCompleteCallbacks() {
      return this.onCompleteCallbacks;
   }

   private Stack onFailureCallbacks() {
      return this.onFailureCallbacks;
   }

   private Option listenerInvocationThread() {
      return this.listenerInvocationThread;
   }

   private void listenerInvocationThread_$eq(final Option x$1) {
      this.listenerInvocationThread = x$1;
   }

   private Option reasonIfKilled() {
      return this.reasonIfKilled;
   }

   private void reasonIfKilled_$eq(final Option x$1) {
      this.reasonIfKilled = x$1;
   }

   public Option org$apache$spark$TaskContextImpl$$pendingInterruptRequest() {
      return this.org$apache$spark$TaskContextImpl$$pendingInterruptRequest;
   }

   private void pendingInterruptRequest_$eq(final Option x$1) {
      this.org$apache$spark$TaskContextImpl$$pendingInterruptRequest = x$1;
   }

   private boolean _interruptible() {
      return this._interruptible;
   }

   private void _interruptible_$eq(final boolean x$1) {
      this._interruptible = x$1;
   }

   private boolean completed() {
      return this.completed;
   }

   private void completed_$eq(final boolean x$1) {
      this.completed = x$1;
   }

   private Option failureCauseOpt() {
      return this.failureCauseOpt;
   }

   private void failureCauseOpt_$eq(final Option x$1) {
      this.failureCauseOpt = x$1;
   }

   private Option _fetchFailedException() {
      return this._fetchFailedException;
   }

   private void _fetchFailedException_$eq(final Option x$1) {
      this._fetchFailedException = x$1;
   }

   public TaskContextImpl addTaskCompletionListener(final TaskCompletionListener listener) {
      synchronized(this){}

      boolean var4;
      try {
         this.onCompleteCallbacks().push(listener);
         var4 = this.completed();
      } catch (Throwable var6) {
         throw var6;
      }

      if (var4) {
         this.invokeTaskCompletionListeners(.MODULE$);
      }

      return this;
   }

   public TaskContextImpl addTaskFailureListener(final TaskFailureListener listener) {
      synchronized(this){}

      Option var3;
      try {
         this.onFailureCallbacks().push(listener);
         var3 = this.failureCauseOpt();
      } catch (Throwable var5) {
         throw var5;
      }

      var3.foreach((error) -> {
         $anonfun$addTaskFailureListener$1(this, error);
         return BoxedUnit.UNIT;
      });
      return this;
   }

   public java.util.Map resourcesJMap() {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsJava(this.resources()).asJava();
   }

   public void markTaskFailed(final Throwable error) {
      synchronized(this){}

      label39: {
         try {
            if (!this.failureCauseOpt().isDefined()) {
               this.failureCauseOpt_$eq(new Some(error));
               break label39;
            }
         } catch (Throwable var4) {
            throw var4;
         }

         return;
      }

      this.invokeTaskFailureListeners(error);
   }

   public void markTaskCompleted(final Option error) {
      synchronized(this){}

      label39: {
         try {
            if (!this.completed()) {
               this.completed_$eq(true);
               break label39;
            }
         } catch (Throwable var4) {
            throw var4;
         }

         return;
      }

      this.invokeTaskCompletionListeners(error);
   }

   private void invokeTaskCompletionListeners(final Option error) {
      this.invokeListeners(this.onCompleteCallbacks(), "TaskCompletionListener", error, (x$1) -> {
         $anonfun$invokeTaskCompletionListeners$1(this, x$1);
         return BoxedUnit.UNIT;
      });
   }

   private void invokeTaskFailureListeners(final Throwable error) {
      this.invokeListeners(this.onFailureCallbacks(), "TaskFailureListener", scala.Option..MODULE$.apply(error), (x$2) -> {
         $anonfun$invokeTaskFailureListeners$1(this, error, x$2);
         return BoxedUnit.UNIT;
      });
   }

   private void invokeListeners(final Stack listeners, final String name, final Option error, final Function1 callback) {
      synchronized(this){}

      try {
         if (this.listenerInvocationThread().nonEmpty()) {
            return;
         }

         this.listenerInvocationThread_$eq(new Some(Thread.currentThread()));
      } catch (Throwable var42) {
         throw var42;
      }

      ArrayBuffer listenerExceptions = new ArrayBuffer(2);
      None var7 = .MODULE$;

      while(true) {
         Option var43 = this.getNextListenerOrDeregisterThread$1(listeners);
         if (!var43.nonEmpty()) {
            if (listenerExceptions.nonEmpty()) {
               TaskCompletionListenerException exception = new TaskCompletionListenerException(((IterableOnceOps)listenerExceptions.map((x$3) -> x$3.getMessage())).toSeq(), error);
               listenerExceptions.foreach((x$1) -> {
                  $anonfun$invokeListeners$3(exception, x$1);
                  return BoxedUnit.UNIT;
               });
               throw exception;
            }

            return;
         }

         Object listener = var43.get();

         try {
            callback.apply(listener);
         } catch (Throwable var41) {
            Throwable e = var41;

            try {
               this.listenerInvocationThread_$eq(.MODULE$);
               this.markTaskFailed(e);
            } catch (Throwable var38) {
               e.addSuppressed(var38);
            } finally {
               synchronized(this){}

               try {
                  if (this.listenerInvocationThread().isEmpty()) {
                     this.listenerInvocationThread_$eq(new Some(Thread.currentThread()));
                  }
               } catch (Throwable var39) {
                  throw var39;
               }

            }

            listenerExceptions.$plus$eq(var41);
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error in ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.LISTENER..MODULE$, name)})))), var41);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   public void markInterrupted(final String reason) {
      this.reasonIfKilled_$eq(new Some(reason));
   }

   public void killTaskIfInterrupted() {
      Option reason = this.reasonIfKilled();
      if (reason.isDefined()) {
         throw new TaskKilledException((String)reason.get());
      }
   }

   public Option getKillReason() {
      return this.reasonIfKilled();
   }

   @GuardedBy("this")
   public synchronized boolean isCompleted() {
      return this.completed();
   }

   public synchronized boolean isFailed() {
      return this.failureCauseOpt().isDefined();
   }

   public boolean isInterrupted() {
      return this.reasonIfKilled().isDefined();
   }

   public String getLocalProperty(final String key) {
      return this.localProperties.getProperty(key);
   }

   public Seq getMetricsSources(final String sourceName) {
      return this.metricsSystem().getSourcesByName(sourceName);
   }

   public void registerAccumulator(final AccumulatorV2 a) {
      this.taskMetrics().registerAccumulator(a);
   }

   public void setFetchFailed(final FetchFailedException fetchFailed) {
      this._fetchFailedException_$eq(scala.Option..MODULE$.apply(fetchFailed));
   }

   public Option fetchFailed() {
      return this._fetchFailedException();
   }

   public Properties getLocalProperties() {
      return this.localProperties;
   }

   public boolean interruptible() {
      synchronized(TaskContext$.MODULE$){}

      boolean var2;
      try {
         var2 = this._interruptible();
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   public void pendingInterrupt(final Option threadToInterrupt, final String reason) {
      synchronized(TaskContext$.MODULE$){}

      try {
         this.pendingInterruptRequest_$eq(new Some(new Tuple2(threadToInterrupt, reason)));
      } catch (Throwable var5) {
         throw var5;
      }

   }

   public Closeable createResourceUninterruptibly(final Function0 resourceBuilder) {
      synchronized(TaskContext$.MODULE$){}

      try {
         this.interruptIfRequired$1();
         this._interruptible_$eq(false);
      } catch (Throwable var24) {
         throw var24;
      }

      Closeable var10000;
      try {
         Closeable resource = (Closeable)resourceBuilder.apply();
         this.addTaskCompletionListener((Function1)((x$5) -> {
            $anonfun$createResourceUninterruptibly$3(resource, x$5);
            return BoxedUnit.UNIT;
         }));
         var10000 = resource;
      } finally {
         synchronized(TaskContext$.MODULE$){}

         try {
            this._interruptible_$eq(true);
            this.interruptIfRequired$1();
         } catch (Throwable var22) {
            throw var22;
         }

      }

      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$addTaskFailureListener$1(final TaskContextImpl $this, final Throwable error) {
      $this.invokeTaskFailureListeners(error);
   }

   // $FF: synthetic method
   public static final void $anonfun$invokeTaskCompletionListeners$1(final TaskContextImpl $this, final TaskCompletionListener x$1) {
      x$1.onTaskCompletion($this);
   }

   // $FF: synthetic method
   public static final void $anonfun$invokeTaskFailureListeners$1(final TaskContextImpl $this, final Throwable error$1, final TaskFailureListener x$2) {
      x$2.onTaskFailure($this, error$1);
   }

   private final synchronized Option getNextListenerOrDeregisterThread$1(final Stack listeners$1) {
      if (listeners$1.empty()) {
         this.listenerInvocationThread_$eq(.MODULE$);
         return .MODULE$;
      } else {
         return new Some(listeners$1.pop());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$invokeListeners$3(final TaskCompletionListenerException exception$1, final Throwable x$1) {
      exception$1.addSuppressed(x$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$createResourceUninterruptibly$2(final Thread x$4) {
      x$4.interrupt();
   }

   // $FF: synthetic method
   public static final void $anonfun$createResourceUninterruptibly$1(final TaskContextImpl $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Option threadToInterrupt = (Option)x0$1._1();
         String reason = (String)x0$1._2();
         $this.markInterrupted(reason);
         threadToInterrupt.foreach((x$4) -> {
            $anonfun$createResourceUninterruptibly$2(x$4);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final void interruptIfRequired$1() {
      this.org$apache$spark$TaskContextImpl$$pendingInterruptRequest().foreach((x0$1) -> {
         $anonfun$createResourceUninterruptibly$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
      this.killTaskIfInterrupted();
   }

   // $FF: synthetic method
   public static final void $anonfun$createResourceUninterruptibly$3(final Closeable resource$1, final TaskContext x$5) {
      resource$1.close();
   }

   public TaskContextImpl(final int stageId, final int stageAttemptNumber, final int partitionId, final long taskAttemptId, final int attemptNumber, final int numPartitions, final TaskMemoryManager taskMemoryManager, final Properties localProperties, final MetricsSystem metricsSystem, final TaskMetrics taskMetrics, final int cpus, final Map resources) {
      this.stageId = stageId;
      this.stageAttemptNumber = stageAttemptNumber;
      this.partitionId = partitionId;
      this.taskAttemptId = taskAttemptId;
      this.attemptNumber = attemptNumber;
      this.numPartitions = numPartitions;
      this.taskMemoryManager = taskMemoryManager;
      this.localProperties = localProperties;
      this.metricsSystem = metricsSystem;
      this.taskMetrics = taskMetrics;
      this.cpus = cpus;
      this.resources = resources;
      Logging.$init$(this);
      this.onCompleteCallbacks = new Stack();
      this.onFailureCallbacks = new Stack();
      this.listenerInvocationThread = .MODULE$;
      this.reasonIfKilled = .MODULE$;
      this.org$apache$spark$TaskContextImpl$$pendingInterruptRequest = .MODULE$;
      this._interruptible = true;
      this.completed = false;
      this.failureCauseOpt = .MODULE$;
      this._fetchFailedException = .MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
