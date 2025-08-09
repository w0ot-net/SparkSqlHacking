package org.apache.spark.scheduler.cluster;

import jakarta.servlet.DispatcherType;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.spark.ExecutorAllocationManager;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.security.HadoopDelegationTokenManager;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.rpc.RpcTimeout;
import org.apache.spark.rpc.ThreadSafeRpcEndpoint;
import org.apache.spark.scheduler.ExecutorLossReason;
import org.apache.spark.scheduler.ExecutorProcessLost;
import org.apache.spark.scheduler.SchedulerBackend;
import org.apache.spark.scheduler.TaskSchedulerImpl;
import org.apache.spark.storage.BlockManagerMaster;
import org.apache.spark.ui.DelegatingServletContextHandler;
import org.apache.spark.ui.SparkUI;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnce;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\r]bA\u0002$H\u0003\u0003Y\u0015\u000b\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003X\u0011!Y\u0006A!A!\u0002\u0013a\u0006\"\u00021\u0001\t\u0003\t\u0007bB3\u0001\u0005\u0004%IA\u001a\u0005\u0007g\u0002\u0001\u000b\u0011B4\t\u000fQ\u0004!\u0019!C!k\"1A\u0010\u0001Q\u0001\nYDq! \u0001A\u0002\u0013Ea\u0010C\u0005\u0002\u0006\u0001\u0001\r\u0011\"\u0005\u0002\b!9\u00111\u0003\u0001!B\u0013y\b\"CA\u000b\u0001\t\u0007I\u0011CA\f\u0011!\t)\u000b\u0001Q\u0001\n\u0005e\u0001\"CAT\u0001\u0001\u0007I\u0011CAU\u0011%\t9\f\u0001a\u0001\n#\tI\f\u0003\u0005\u0002>\u0002\u0001\u000b\u0015BAV\u0011%\ty\f\u0001b\u0001\n\u0013\t\t\r\u0003\u0005\u0002D\u0002\u0001\u000b\u0011BAY\u0011%\t)\r\u0001b\u0001\n\u0017\t9\r\u0003\u0005\u0002P\u0002\u0001\u000b\u0011BAe\u0011%\t\t\u000e\u0001b\u0001\n\u0017\t\u0019\u000e\u0003\u0005\u0002`\u0002\u0001\u000b\u0011BAk\u0011%\t\t\u000f\u0001a\u0001\n#\t\u0019\u000fC\u0005\u0002\u0000\u0002\u0001\r\u0011\"\u0005\u0003\u0002!A!Q\u0001\u0001!B\u0013\t)\u000fC\u0005\u0003\b\u0001\u0001\r\u0011\"\u0003\u0003\n!I!1\u0003\u0001A\u0002\u0013%!Q\u0003\u0005\t\u00053\u0001\u0001\u0015)\u0003\u0003\f!I!1\u0004\u0001C\u0002\u0013%!Q\u0004\u0005\t\u0005W\u0001\u0001\u0015!\u0003\u0003 !A!Q\u0006\u0001C\u0002\u0013%Q\u000fC\u0004\u00030\u0001\u0001\u000b\u0011\u0002<\t\u0011\tE\u0002A1A\u0005\nyDqAa\r\u0001A\u0003%q\u0010\u0003\u0005\u00036\u0001\u0011\r\u0011\"\u0003\u007f\u0011\u001d\u00119\u0004\u0001Q\u0001\n}D\u0001B!\u000f\u0001\u0005\u0004%IA \u0005\b\u0005w\u0001\u0001\u0015!\u0003\u0000\u0011\u001d\u0011i\u0004\u0001C\t\u0005\u007fAqA!\u0012\u0001\t#\u00119\u0005C\u0004\u0003J\u0001!\tEa\u0012\t\u000f\t-\u0003\u0001\"\u0011\u0003N!9!\u0011\u000b\u0001\u0005B\tM\u0003\u0002\u0003B+\u0001\u0011\u0005qIa\u0016\t\u000f\tE\u0005\u0001\"\u0011\u0003\u0014\"9!1\u0015\u0001\u0005B\t\u0015\u0006b\u0002B_\u0001\u0011\u0005#q\u0018\u0005\b\u0005\u0003\u0004A\u0011\tBb\u0011!\u0011)\u000e\u0001C\u0001\u000f\n]\u0007b\u0002Bt\u0001\u0011\u0005#\u0011\u001e\u0005\t\u0005c\u0004A\u0011K%\u0003H!9!1\u001f\u0001\u0005R\tUhABB\u0005\u0001\u0011\u0019Y\u0001\u0003\u0004ai\u0011\u00051Q\u0002\u0005\b\u0003k\"D\u0011IB\t\r\u0019\ti\u0002\u0001\u0005\u0002 !Q\u0011qH\u001c\u0003\u0006\u0004%\t%!\u0011\t\u0015\u0005%sG!A!\u0002\u0013\t\u0019\u0005\u0003\u0004ao\u0011\u0005\u00111\n\u0005\t\u0003\u001f:D\u0011A$\u0002R!9\u0011qK\u001c\u0005B\u0005e\u0003bBA4o\u0011\u0005\u0013\u0011\u000e\u0005\b\u0003k:D\u0011IA<\u00111\t\u0019i\u000eB\u0001\u0002\u0003%\t\u0001AAC\u00119\u00199\u0002\u0001I\u0001\u0004\u0003\u0005I\u0011\u0002B*\u000739\u0001ba\bH\u0011\u0003Y5\u0011\u0005\u0004\b\r\u001eC\taSB\u0012\u0011\u0019\u0001'\t\"\u0001\u0004&!I1q\u0005\"C\u0002\u0013\u00051\u0011\u0006\u0005\t\u0007k\u0011\u0005\u0015!\u0003\u0004,\t!\u0012,\u0019:o'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012T!\u0001S%\u0002\u000f\rdWo\u001d;fe*\u0011!jS\u0001\ng\u000eDW\rZ;mKJT!\u0001T'\u0002\u000bM\u0004\u0018M]6\u000b\u00059{\u0015AB1qC\u000eDWMC\u0001Q\u0003\ry'oZ\n\u0003\u0001I\u0003\"a\u0015+\u000e\u0003\u001dK!!V$\u0003;\r{\u0017M]:f\u000fJ\f\u0017N\\3e'\u000eDW\rZ;mKJ\u0014\u0015mY6f]\u0012\u001c\u0001\u0001\u0005\u0002Y36\t\u0011*\u0003\u0002[\u0013\n\tB+Y:l'\u000eDW\rZ;mKJLU\u000e\u001d7\u0002\u0005M\u001c\u0007CA/_\u001b\u0005Y\u0015BA0L\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0019a\u0014N\\5u}Q\u0019!m\u00193\u0011\u0005M\u0003\u0001\"\u0002&\u0004\u0001\u00049\u0006\"B.\u0004\u0001\u0004a\u0016aB:u_B\u0004X\rZ\u000b\u0002OB\u0011\u0001.]\u0007\u0002S*\u0011!n[\u0001\u0007CR|W.[2\u000b\u00051l\u0017AC2p]\u000e,(O]3oi*\u0011an\\\u0001\u0005kRLGNC\u0001q\u0003\u0011Q\u0017M^1\n\u0005IL'!D!u_6L7MQ8pY\u0016\fg.\u0001\u0005ti>\u0004\b/\u001a3!\u0003Ii\u0017N\u001c*fO&\u001cH/\u001a:fIJ\u000bG/[8\u0016\u0003Y\u0004\"a\u001e>\u000e\u0003aT\u0011!_\u0001\u0006g\u000e\fG.Y\u0005\u0003wb\u0014a\u0001R8vE2,\u0017aE7j]J+w-[:uKJ,GMU1uS>\u0004\u0013A\u0006;pi\u0006dW\t\u001f9fGR,G-\u0012=fGV$xN]:\u0016\u0003}\u00042a^A\u0001\u0013\r\t\u0019\u0001\u001f\u0002\u0004\u0013:$\u0018A\u0007;pi\u0006dW\t\u001f9fGR,G-\u0012=fGV$xN]:`I\u0015\fH\u0003BA\u0005\u0003\u001f\u00012a^A\u0006\u0013\r\ti\u0001\u001f\u0002\u0005+:LG\u000f\u0003\u0005\u0002\u0012%\t\t\u00111\u0001\u0000\u0003\rAH%M\u0001\u0018i>$\u0018\r\\#ya\u0016\u001cG/\u001a3Fq\u0016\u001cW\u000f^8sg\u0002\nQ#_1s]N\u001b\u0007.\u001a3vY\u0016\u0014XI\u001c3q_&tG/\u0006\u0002\u0002\u001aA\u0019\u00111D\u001c\u000e\u0003\u0001\u0011Q#W1s]N\u001b\u0007.\u001a3vY\u0016\u0014XI\u001c3q_&tGoE\u00048\u0003C\t9#a\r\u0011\u0007]\f\u0019#C\u0002\u0002&a\u0014a!\u00118z%\u00164\u0007\u0003BA\u0015\u0003_i!!a\u000b\u000b\u0007\u000552*A\u0002sa\u000eLA!!\r\u0002,\t)B\u000b\u001b:fC\u0012\u001c\u0016MZ3Sa\u000e,e\u000e\u001a9pS:$\b\u0003BA\u001b\u0003wi!!a\u000e\u000b\u0007\u0005e2*\u0001\u0005j]R,'O\\1m\u0013\u0011\ti$a\u000e\u0003\u000f1{wmZ5oO\u00061!\u000f]2F]Z,\"!a\u0011\u0011\t\u0005%\u0012QI\u0005\u0005\u0003\u000f\nYC\u0001\u0004Sa\u000e,eN^\u0001\beB\u001cWI\u001c<!)\u0011\tI\"!\u0014\t\u000f\u0005}\"\b1\u0001\u0002D\u0005\u00012/[4oC2$%/\u001b<feN#x\u000e\u001d\u000b\u0005\u0003\u0013\t\u0019\u0006\u0003\u0004\u0002Vm\u0002\ra`\u0001\tKbLGoQ8eK\u00069!/Z2fSZ,WCAA.!\u001d9\u0018QLA1\u0003\u0013I1!a\u0018y\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007cA<\u0002d%\u0019\u0011Q\r=\u0003\u0007\u0005s\u00170A\bsK\u000e,\u0017N^3B]\u0012\u0014V\r\u001d7z)\u0011\tY&a\u001b\t\u000f\u00055T\b1\u0001\u0002p\u000591m\u001c8uKb$\b\u0003BA\u0015\u0003cJA!a\u001d\u0002,\tq!\u000b]2DC2d7i\u001c8uKb$\u0018AD8o\t&\u001c8m\u001c8oK\u000e$X\r\u001a\u000b\u0005\u0003\u0013\tI\bC\u0004\u0002|y\u0002\r!! \u0002\u001bI,Wn\u001c;f\u0003\u0012$'/Z:t!\u0011\tI#a \n\t\u0005\u0005\u00151\u0006\u0002\u000b%B\u001c\u0017\t\u001a3sKN\u001c\u0018!X8sO\u0012\n\u0007/Y2iK\u0012\u001a\b/\u0019:lIM\u001c\u0007.\u001a3vY\u0016\u0014He\u00197vgR,'\u000fJ-be:\u001c6\r[3ek2,'OQ1dW\u0016tG\r\n\u0013iC:$G.Z#yK\u000e,Ho\u001c:ESN\u001cwN\u001c8fGR,GM\u0012:p[\u0012\u0013\u0018N^3s)\u0019\tI!a\"\u0002\"\"9\u0011\u0011R A\u0002\u0005-\u0015AC3yK\u000e,Ho\u001c:JIB!\u0011QRAN\u001d\u0011\ty)a&\u0011\u0007\u0005E\u00050\u0004\u0002\u0002\u0014*\u0019\u0011Q\u0013,\u0002\rq\u0012xn\u001c;?\u0013\r\tI\n_\u0001\u0007!J,G-\u001a4\n\t\u0005u\u0015q\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005e\u0005\u0010C\u0004\u0002$~\u0002\r!! \u0002%\u0015DXmY;u_J\u0014\u0006oY!eIJ,7o]\u0001\u0017s\u0006\u0014hnU2iK\u0012,H.\u001a:F]\u0012\u0004x.\u001b8uA\u0005Q\u0011-\\#oIB|\u0017N\u001c;\u0016\u0005\u0005-\u0006#B<\u0002.\u0006E\u0016bAAXq\n1q\n\u001d;j_:\u0004B!!\u000b\u00024&!\u0011QWA\u0016\u00059\u0011\u0006oY#oIB|\u0017N\u001c;SK\u001a\fa\"Y7F]\u0012\u0004x.\u001b8u?\u0012*\u0017\u000f\u0006\u0003\u0002\n\u0005m\u0006\"CA\t\u001d\u0005\u0005\t\u0019AAV\u0003-\tW.\u00128ea>Lg\u000e\u001e\u0011\u00021e\f'O\\*dQ\u0016$W\u000f\\3s\u000b:$\u0007o\\5oiJ+g-\u0006\u0002\u00022\u0006I\u00120\u0019:o'\u000eDW\rZ;mKJ,e\u000e\u001a9pS:$(+\u001a4!\u0003)\t7o\u001b+j[\u0016|W\u000f^\u000b\u0003\u0003\u0013\u0004B!!\u000b\u0002L&!\u0011QZA\u0016\u0005)\u0011\u0006o\u0019+j[\u0016|W\u000f^\u0001\fCN\\G+[7f_V$\b%A\ntG\",G-\u001e7fe\u0016sG\r]8j]R,5)\u0006\u0002\u0002VB!\u0011q[An\u001b\t\tIN\u0003\u0002mq&!\u0011Q\\Am\u0005A)\u00050Z2vi&|gnQ8oi\u0016DH/\u0001\u000btG\",G-\u001e7fe\u0016sG\r]8j]R,5\tI\u0001\u0006CB\u0004\u0018\nZ\u000b\u0003\u0003K\u0004Ra^AW\u0003O\u0004B!!;\u0002|6\u0011\u00111\u001e\u0006\u0005\u0003[\fy/A\u0004sK\u000e|'\u000fZ:\u000b\t\u0005E\u00181_\u0001\u0004CBL'\u0002BA{\u0003o\fA!_1s]*\u0019\u0011\u0011`'\u0002\r!\fGm\\8q\u0013\u0011\ti0a;\u0003\u001b\u0005\u0003\b\u000f\\5dCRLwN\\%e\u0003%\t\u0007\u000f]%e?\u0012*\u0017\u000f\u0006\u0003\u0002\n\t\r\u0001\"CA\t/\u0005\u0005\t\u0019AAs\u0003\u0019\t\u0007\u000f]%eA\u0005I\u0011\r\u001e;f[B$\u0018\nZ\u000b\u0003\u0005\u0017\u0001Ra^AW\u0005\u001b\u0001B!!;\u0003\u0010%!!\u0011CAv\u0005Q\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8BiR,W\u000e\u001d;JI\u0006i\u0011\r\u001e;f[B$\u0018\nZ0%KF$B!!\u0003\u0003\u0018!I\u0011\u0011\u0003\u000e\u0002\u0002\u0003\u0007!1B\u0001\u000bCR$X-\u001c9u\u0013\u0012\u0004\u0013A\u00052m_\u000e\\W*\u00198bO\u0016\u0014X*Y:uKJ,\"Aa\b\u0011\t\t\u0005\"qE\u0007\u0003\u0005GQ1A!\nL\u0003\u001d\u0019Ho\u001c:bO\u0016LAA!\u000b\u0003$\t\u0011\"\t\\8dW6\u000bg.Y4fe6\u000b7\u000f^3s\u0003M\u0011Gn\\2l\u001b\u0006t\u0017mZ3s\u001b\u0006\u001cH/\u001a:!\u0003ai\u0017N\\'fe\u001e,'o\u001d+ie\u0016\u001c\bn\u001c7e%\u0006$\u0018n\\\u0001\u001a[&tW*\u001a:hKJ\u001cH\u000b\u001b:fg\"|G\u000e\u001a*bi&|\u0007%A\rnS:lUM]4feN\u001cF/\u0019;jGRC'/Z:i_2$\u0017AG7j]6+'oZ3sgN#\u0018\r^5d)\"\u0014Xm\u001d5pY\u0012\u0004\u0013aD7bq:+X.\u0012=fGV$xN]:\u0002!5\f\u0007PT;n\u000bb,7-\u001e;peN\u0004\u0013\u0001\u00048v[\u0016CXmY;u_J\u001c\u0018!\u00048v[\u0016CXmY;u_J\u001c\b%\u0001\u0006cS:$Gk\\-be:$b!!\u0003\u0003B\t\r\u0003bBAqM\u0001\u0007\u0011q\u001d\u0005\b\u0005\u000f1\u0003\u0019\u0001B\u0006\u00035\u0019H/\u0019:u\u0005&tG-\u001b8hgR\u0011\u0011\u0011B\u0001\u0005gR|\u0007/\u0001\u000bbaBd\u0017nY1uS>t\u0017\t\u001e;f[B$\u0018\n\u001a\u000b\u0003\u0005\u001f\u0002Ra^AW\u0003\u0017\u000bQ\"\u00199qY&\u001c\u0017\r^5p]&#GCAAF\u0003]\u0001(/\u001a9be\u0016\u0014V-];fgR,\u00050Z2vi>\u00148\u000f\u0006\u0003\u0003Z\tm\u0004\u0003\u0002B.\u0005krAA!\u0018\u0003r9!!q\fB8\u001d\u0011\u0011\tG!\u001c\u000f\t\t\r$1\u000e\b\u0005\u0005K\u0012IG\u0004\u0003\u0002\u0012\n\u001d\u0014\"\u0001)\n\u00059{\u0015B\u0001'N\u0013\tQ5*\u0003\u0002I\u0013&\u0019!1O$\u00029\r{\u0017M]:f\u000fJ\f\u0017N\\3e\u00072,8\u000f^3s\u001b\u0016\u001c8/Y4fg&!!q\u000fB=\u0005A\u0011V-];fgR,\u00050Z2vi>\u00148OC\u0002\u0003t\u001dCqA! ,\u0001\u0004\u0011y(A\u000esKN|WO]2f!J|g-\u001b7f)>$v\u000e^1m\u000bb,7m\u001d\t\b\u0003\u001b\u0013\tI!\"\u0000\u0013\u0011\u0011\u0019)a(\u0003\u00075\u000b\u0007\u000f\u0005\u0003\u0003\b\n5UB\u0001BE\u0015\r\u0011YiS\u0001\te\u0016\u001cx.\u001e:dK&!!q\u0012BE\u0005=\u0011Vm]8ve\u000e,\u0007K]8gS2,\u0017a\u00063p%\u0016\fX/Z:u)>$\u0018\r\\#yK\u000e,Ho\u001c:t)\u0011\u0011)J!)\u0011\r\u0005]'q\u0013BN\u0013\u0011\u0011I*!7\u0003\r\u0019+H/\u001e:f!\r9(QT\u0005\u0004\u0005?C(a\u0002\"p_2,\u0017M\u001c\u0005\b\u0005{b\u0003\u0019\u0001B@\u0003=!wnS5mY\u0016CXmY;u_J\u001cH\u0003\u0002BK\u0005OCqA!+.\u0001\u0004\u0011Y+A\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u001c\bC\u0002BW\u0005o\u000bYI\u0004\u0003\u00030\nMf\u0002BAI\u0005cK\u0011!_\u0005\u0004\u0005kC\u0018a\u00029bG.\fw-Z\u0005\u0005\u0005s\u0013YLA\u0002TKFT1A!.y\u0003u\u0019XO\u001a4jG&,g\u000e\u001e*fg>,(oY3t%\u0016<\u0017n\u001d;fe\u0016$GC\u0001BN\u0003u9W\r^*ik\u001a4G.\u001a)vg\"lUM]4fe2{7-\u0019;j_:\u001cHC\u0002Bc\u0005\u001b\u0014\t\u000e\u0005\u0004\u0003.\n]&q\u0019\t\u0005\u0005C\u0011I-\u0003\u0003\u0003L\n\r\"A\u0004\"m_\u000e\\W*\u00198bO\u0016\u0014\u0018\n\u001a\u0005\u0007\u0005\u001f|\u0003\u0019A@\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u0011\u0019\u0011\u0019n\fa\u0001\u007f\u0006\t\"/Z:pkJ\u001cW\r\u0015:pM&dW-\u00133\u0002\u001d\u0005$GmV3c+&3\u0015\u000e\u001c;feRA\u0011\u0011\u0002Bm\u0005;\u0014\u0019\u000fC\u0004\u0003\\B\u0002\r!a#\u0002\u0015\u0019LG\u000e^3s\u001d\u0006lW\rC\u0004\u0003`B\u0002\rA!9\u0002\u0019\u0019LG\u000e^3s!\u0006\u0014\u0018-\\:\u0011\u0011\u00055%\u0011QAF\u0003\u0017CqA!:1\u0001\u0004\tY)A\u0005qe>D\u0018PQ1tK\u0006!2M]3bi\u0016$%/\u001b<fe\u0016sG\r]8j]R$\"Aa;\u0011\t\u0005m!Q^\u0005\u0004\u0005_$&A\u0004#sSZ,'/\u00128ea>Lg\u000e^\u0001\u0006e\u0016\u001cX\r^\u0001\u0013GJ,\u0017\r^3U_.,g.T1oC\u001e,'\u000f\u0006\u0002\u0003xB)q/!,\u0003zB!!1`B\u0003\u001b\t\u0011iP\u0003\u0003\u0003\u0000\u000e\u0005\u0011\u0001C:fGV\u0014\u0018\u000e^=\u000b\u0007\r\r1*\u0001\u0004eKBdw._\u0005\u0005\u0007\u000f\u0011iP\u0001\u000fIC\u0012|w\u000e\u001d#fY\u0016<\u0017\r^5p]R{7.\u001a8NC:\fw-\u001a:\u0003%e\u000b'O\u001c#sSZ,'/\u00128ea>Lg\u000e^\n\u0004i\t-HCAB\b!\r\tY\u0002\u000e\u000b\u0005\u0003\u0013\u0019\u0019\u0002C\u0004\u0004\u0016Y\u0002\r!! \u0002\u0015I\u00048-\u00113ee\u0016\u001c8/A\ntkB,'\u000fJ1qa2L7-\u0019;j_:LE-\u0003\u0003\u0003R\rm\u0011bAB\u000f\u0013\n\u00012k\u00195fIVdWM\u001d\"bG.,g\u000eZ\u0001\u00153\u0006\u0014hnU2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3\u0011\u0005M\u00135c\u0001\"\u0002\"Q\u00111\u0011E\u0001\u000e\u000b:#\u0005kT%O)~s\u0015)T#\u0016\u0005\r-\u0002\u0003BB\u0017\u0007gi!aa\f\u000b\u0007\rEr.\u0001\u0003mC:<\u0017\u0002BAO\u0007_\ta\"\u0012(E!>Ke\nV0O\u00036+\u0005\u0005"
)
public abstract class YarnSchedulerBackend extends CoarseGrainedSchedulerBackend {
   private final TaskSchedulerImpl scheduler;
   private final SparkContext sc;
   private final AtomicBoolean org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped;
   private final double minRegisteredRatio;
   private int totalExpectedExecutors;
   private final YarnSchedulerEndpoint yarnSchedulerEndpoint;
   private Option amEndpoint;
   private final RpcEndpointRef yarnSchedulerEndpointRef;
   private final RpcTimeout org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$askTimeout;
   private final ExecutionContext org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$schedulerEndpointEC;
   private Option appId;
   private Option attemptId;
   private final BlockManagerMaster blockManagerMaster;
   private final double minMergersThresholdRatio;
   private final int minMergersStaticThreshold;
   private final int maxNumExecutors;
   private final int numExecutors;

   public static String ENDPOINT_NAME() {
      return YarnSchedulerBackend$.MODULE$.ENDPOINT_NAME();
   }

   // $FF: synthetic method
   private String super$applicationId() {
      return SchedulerBackend.applicationId$(this);
   }

   public AtomicBoolean org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped() {
      return this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped;
   }

   public double minRegisteredRatio() {
      return this.minRegisteredRatio;
   }

   public int totalExpectedExecutors() {
      return this.totalExpectedExecutors;
   }

   public void totalExpectedExecutors_$eq(final int x$1) {
      this.totalExpectedExecutors = x$1;
   }

   public YarnSchedulerEndpoint yarnSchedulerEndpoint() {
      return this.yarnSchedulerEndpoint;
   }

   public Option amEndpoint() {
      return this.amEndpoint;
   }

   public void amEndpoint_$eq(final Option x$1) {
      this.amEndpoint = x$1;
   }

   private RpcEndpointRef yarnSchedulerEndpointRef() {
      return this.yarnSchedulerEndpointRef;
   }

   public RpcTimeout org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$askTimeout() {
      return this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$askTimeout;
   }

   public ExecutionContext org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$schedulerEndpointEC() {
      return this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$schedulerEndpointEC;
   }

   public Option appId() {
      return this.appId;
   }

   public void appId_$eq(final Option x$1) {
      this.appId = x$1;
   }

   private Option attemptId() {
      return this.attemptId;
   }

   private void attemptId_$eq(final Option x$1) {
      this.attemptId = x$1;
   }

   private BlockManagerMaster blockManagerMaster() {
      return this.blockManagerMaster;
   }

   private double minMergersThresholdRatio() {
      return this.minMergersThresholdRatio;
   }

   private int minMergersStaticThreshold() {
      return this.minMergersStaticThreshold;
   }

   private int maxNumExecutors() {
      return this.maxNumExecutors;
   }

   private int numExecutors() {
      return this.numExecutors;
   }

   public void bindToYarn(final ApplicationId appId, final Option attemptId) {
      this.appId_$eq(new Some(appId));
      this.attemptId_$eq(attemptId);
   }

   public void startBindings() {
      .MODULE$.require(this.appId().isDefined(), () -> "application ID unset");
   }

   public void stop() {
      try {
         this.requestTotalExecutors(.MODULE$.Map().empty(), .MODULE$.Map().empty(), .MODULE$.Map().empty());
         super.stop();
      } finally {
         this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped().set(true);
      }

   }

   public Option applicationAttemptId() {
      return this.attemptId().map((x$1) -> Integer.toString(x$1.getAttemptId()));
   }

   public String applicationId() {
      return (String)this.appId().map((x$2) -> x$2.toString()).getOrElse(() -> {
         this.logWarning(() -> "Application ID is not initialized yet.");
         return this.super$applicationId();
      });
   }

   public CoarseGrainedClusterMessages.RequestExecutors prepareRequestExecutors(final Map resourceProfileToTotalExecs) {
      Set excludedNodes = this.scheduler.excludedNodes();
      Map filteredRPHostToLocalTaskCount = (Map)this.rpHostToLocalTaskCount().map((x0$1) -> {
         if (x0$1 != null) {
            int rpid = x0$1._1$mcI$sp();
            Map v = (Map)x0$1._2();
            return new Tuple2(BoxesRunTime.boxToInteger(rpid), v.filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$prepareRequestExecutors$2(excludedNodes, x0$2))));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new CoarseGrainedClusterMessages.RequestExecutors(resourceProfileToTotalExecs, this.numLocalityAwareTasksPerResourceProfileId(), filteredRPHostToLocalTaskCount, excludedNodes);
   }

   public Future doRequestTotalExecutors(final Map resourceProfileToTotalExecs) {
      return this.yarnSchedulerEndpointRef().ask(this.prepareRequestExecutors(resourceProfileToTotalExecs), scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public Future doKillExecutors(final Seq executorIds) {
      return this.yarnSchedulerEndpointRef().ask(new CoarseGrainedClusterMessages.KillExecutors(executorIds), scala.reflect.ClassTag..MODULE$.Boolean());
   }

   public boolean sufficientResourcesRegistered() {
      return (double)this.totalRegisteredExecutors().get() >= (double)this.totalExpectedExecutors() * this.minRegisteredRatio();
   }

   public Seq getShufflePushMergerLocations(final int numPartitions, final int resourceProfileId) {
      int maxExecutors = org.apache.spark.util.Utils..MODULE$.isDynamicAllocationEnabled(this.sc.getConf()) ? this.maxNumExecutors() : this.numExecutors();
      int tasksPerExecutor = this.sc.resourceProfileManager().resourceProfileFromId(resourceProfileId).maxTasksPerExecutor(this.sc.conf());
      int numMergersDesired = scala.math.package..MODULE$.min(scala.math.package..MODULE$.max(1, (int)scala.math.package..MODULE$.ceil((double)(numPartitions / tasksPerExecutor))), maxExecutors);
      int minMergersNeeded = scala.math.package..MODULE$.max(this.minMergersStaticThreshold(), (int)scala.math.package..MODULE$.floor((double)numMergersDesired * this.minMergersThresholdRatio()));
      Seq mergerLocations = this.blockManagerMaster().getShufflePushMergerLocations(numMergersDesired, this.scheduler.excludedNodes());
      if (mergerLocations.size() < numMergersDesired && mergerLocations.size() < minMergersNeeded) {
         return (Seq)scala.package..MODULE$.Seq().empty();
      } else {
         this.logDebug(() -> "The number of shuffle mergers desired " + numMergersDesired + " and available locations are " + mergerLocations.length());
         return mergerLocations;
      }
   }

   public void addWebUIFilter(final String filterName, final Map filterParams, final String proxyBase) {
      if (proxyBase != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(proxyBase))) {
         System.setProperty("spark.ui.proxyBase", proxyBase);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      boolean hasFilter = filterName != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(filterName)) && filterParams != null && filterParams.nonEmpty();
      if (hasFilter) {
         Seq allFilters = (Seq)(new scala.collection.immutable..colon.colon(filterName, scala.collection.immutable.Nil..MODULE$)).$plus$plus((IterableOnce)this.conf().get(org.apache.spark.internal.config.UI..MODULE$.UI_FILTERS()));
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Add WebUI Filter. ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UI_FILTER..MODULE$, filterName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UI_FILTER_PARAMS..MODULE$, filterParams)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UI_PROXY_BASE..MODULE$, proxyBase)}))))));
         this.scheduler.sc().ui().foreach((ui) -> {
            $anonfun$addWebUIFilter$2(this, filterParams, filterName, allFilters, ui);
            return BoxedUnit.UNIT;
         });
      }
   }

   public CoarseGrainedSchedulerBackend.DriverEndpoint createDriverEndpoint() {
      return new YarnDriverEndpoint();
   }

   public void reset() {
      super.reset();
      this.sc.executorAllocationManager().foreach((x$3) -> {
         $anonfun$reset$1(x$3);
         return BoxedUnit.UNIT;
      });
   }

   public Option createTokenManager() {
      return new Some(new HadoopDelegationTokenManager(this.sc.conf(), this.sc.hadoopConfiguration(), this.driverEndpoint()));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$prepareRequestExecutors$2(final Set excludedNodes$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String host = (String)x0$2._1();
         return !excludedNodes$1.contains(host);
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$addWebUIFilter$4(final String filterName$1, final Map filterParams$1, final DelegatingServletContextHandler h) {
      h.addFilter(filterName$1, filterName$1, filterParams$1);
      h.prependFilterMapping(filterName$1, "/*", EnumSet.allOf(DispatcherType.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$addWebUIFilter$2(final YarnSchedulerBackend $this, final Map filterParams$1, final String filterName$1, final Seq allFilters$1, final SparkUI ui) {
      synchronized(ui){}

      try {
         filterParams$1.foreach((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return $this.conf().set("spark." + filterName$1 + ".param." + k, v);
            } else {
               throw new MatchError(x0$1);
            }
         });
         $this.conf().set(org.apache.spark.internal.config.UI..MODULE$.UI_FILTERS(), allFilters$1);
         ui.getDelegatingHandlers().foreach((h) -> {
            $anonfun$addWebUIFilter$4(filterName$1, filterParams$1, h);
            return BoxedUnit.UNIT;
         });
      } catch (Throwable var7) {
         throw var7;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$reset$1(final ExecutorAllocationManager x$3) {
      x$3.reset();
   }

   public YarnSchedulerBackend(final TaskSchedulerImpl scheduler, final SparkContext sc) {
      super(scheduler, sc.env().rpcEnv());
      this.scheduler = scheduler;
      this.sc = sc;
      this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped = new AtomicBoolean(false);
      this.minRegisteredRatio = ((Option)this.conf().get(org.apache.spark.internal.config.package..MODULE$.SCHEDULER_MIN_REGISTERED_RESOURCES_RATIO())).isEmpty() ? 0.8 : super.minRegisteredRatio();
      this.totalExpectedExecutors = 0;
      this.yarnSchedulerEndpoint = new YarnSchedulerEndpoint(this.rpcEnv());
      this.amEndpoint = scala.None..MODULE$;
      this.yarnSchedulerEndpointRef = this.rpcEnv().setupEndpoint(YarnSchedulerBackend$.MODULE$.ENDPOINT_NAME(), this.yarnSchedulerEndpoint());
      this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$askTimeout = org.apache.spark.util.RpcUtils..MODULE$.askRpcTimeout(sc.conf());
      this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$schedulerEndpointEC = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(org.apache.spark.util.ThreadUtils..MODULE$.newDaemonSingleThreadExecutor("yarn-scheduler-endpoint"));
      this.appId = scala.None..MODULE$;
      this.attemptId = scala.None..MODULE$;
      this.blockManagerMaster = sc.env().blockManager().master();
      this.minMergersThresholdRatio = BoxesRunTime.unboxToDouble(this.conf().get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_MERGER_LOCATIONS_MIN_THRESHOLD_RATIO()));
      this.minMergersStaticThreshold = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package..MODULE$.SHUFFLE_MERGER_LOCATIONS_MIN_STATIC_THRESHOLD()));
      this.maxNumExecutors = BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package..MODULE$.DYN_ALLOCATION_MAX_EXECUTORS()));
      this.numExecutors = BoxesRunTime.unboxToInt(((Option)this.conf().get(org.apache.spark.internal.config.package..MODULE$.EXECUTOR_INSTANCES())).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class YarnDriverEndpoint extends CoarseGrainedSchedulerBackend.DriverEndpoint {
      public void onDisconnected(final RpcAddress rpcAddress) {
         this.addressToExecutorId().get(rpcAddress).foreach((executorId) -> {
            $anonfun$onDisconnected$1(this, rpcAddress, executorId);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      public YarnSchedulerBackend org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnDriverEndpoint$$$outer() {
         return (YarnSchedulerBackend)this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$onDisconnected$1(final YarnDriverEndpoint $this, final RpcAddress rpcAddress$1, final String executorId) {
         if (!$this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnDriverEndpoint$$$outer().org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped().get()) {
            if ($this.disableExecutor(executorId)) {
               $this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnDriverEndpoint$$$outer().yarnSchedulerEndpoint().org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$handleExecutorDisconnectedFromDriver(executorId, rpcAddress$1);
            }
         }
      }

      public YarnDriverEndpoint() {
         super(YarnSchedulerBackend.this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class YarnSchedulerEndpoint implements ThreadSafeRpcEndpoint, Logging {
      private final RpcEnv rpcEnv;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      public final YarnSchedulerBackend $outer;

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

      public void onStart() {
         RpcEndpoint.onStart$(this);
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

      public void org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$handleExecutorDisconnectedFromDriver(final String executorId, final RpcAddress executorRpcAddress) {
         Option var5 = this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint();
         Future var10000;
         if (var5 instanceof Some var6) {
            RpcEndpointRef am = (RpcEndpointRef)var6.value();
            CoarseGrainedClusterMessages.GetExecutorLossReason lossReasonRequest = new CoarseGrainedClusterMessages.GetExecutorLossReason(executorId);
            var10000 = am.ask(lossReasonRequest, this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$askTimeout(), scala.reflect.ClassTag..MODULE$.apply(ExecutorLossReason.class)).map((reason) -> new CoarseGrainedClusterMessages.RemoveExecutor(executorId, reason), org.apache.spark.util.ThreadUtils..MODULE$.sameThread()).recover(new Serializable(executorId, executorRpcAddress) {
               private static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final YarnSchedulerEndpoint $outer;
               private final String executorId$1;
               private final RpcAddress executorRpcAddress$1;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  if (x1 != null && scala.util.control.NonFatal..MODULE$.apply(x1)) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempted to get executor loss reason for executor id "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " at RPC address "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, this.executorId$1)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", but got no response. "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.executorRpcAddress$1)})))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Marking as agent lost."})))).log(scala.collection.immutable.Nil..MODULE$))), x1);
                     return new CoarseGrainedClusterMessages.RemoveExecutor(this.executorId$1, new ExecutorProcessLost(org.apache.spark.scheduler.ExecutorProcessLost..MODULE$.apply$default$1(), org.apache.spark.scheduler.ExecutorProcessLost..MODULE$.apply$default$2(), org.apache.spark.scheduler.ExecutorProcessLost..MODULE$.apply$default$3()));
                  } else {
                     return default.apply(x1);
                  }
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  return x1 != null && scala.util.control.NonFatal..MODULE$.apply(x1);
               }

               public {
                  if (YarnSchedulerEndpoint.this == null) {
                     throw null;
                  } else {
                     this.$outer = YarnSchedulerEndpoint.this;
                     this.executorId$1 = executorId$1;
                     this.executorRpcAddress$1 = executorRpcAddress$1;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return var0.lambdaDeserialize<invokedynamic>(var0);
               }
            }, org.apache.spark.util.ThreadUtils..MODULE$.sameThread());
         } else {
            if (!scala.None..MODULE$.equals(var5)) {
               throw new MatchError(var5);
            }

            this.logWarning((Function0)(() -> "Attempted to check for an executor loss reason before the AM has registered!"));
            var10000 = scala.concurrent.Future..MODULE$.successful(new CoarseGrainedClusterMessages.RemoveExecutor(executorId, new ExecutorProcessLost("AM is not yet registered.", org.apache.spark.scheduler.ExecutorProcessLost..MODULE$.apply$default$2(), org.apache.spark.scheduler.ExecutorProcessLost..MODULE$.apply$default$3())));
         }

         Future removeExecutorMessage = var10000;
         removeExecutorMessage.foreach((message) -> {
            $anonfun$org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$handleExecutorDisconnectedFromDriver$3(this, message);
            return BoxedUnit.UNIT;
         }, this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$schedulerEndpointEC());
      }

      public void signalDriverStop(final int exitCode) {
         Option var3 = this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint();
         if (var3 instanceof Some var4) {
            RpcEndpointRef am = (RpcEndpointRef)var4.value();
            am.send(new CoarseGrainedClusterMessages.Shutdown(exitCode));
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else if (scala.None..MODULE$.equals(var3)) {
            this.logWarning((Function0)(() -> "Attempted to send shutdown message before the AM has registered!"));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var3);
         }
      }

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final YarnSchedulerEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.RegisterClusterManager var5) {
                  RpcEndpointRef am = var5.am();
                  this.$outer.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ApplicationMaster registered as ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RPC_ENDPOINT_REF..MODULE$, am)})))));
                  this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint_$eq(scala.Option..MODULE$.apply(am));
                  this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().reset();
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.DecommissionExecutorsOnHost var7) {
                  String hostId = var7.host();
                  this.$outer.logDebug((Function0)(() -> "Requesting to decommission host " + hostId + ". Sending to driver"));
                  this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().driverEndpoint().send(var7);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.AddWebUIFilter var9) {
                  String filterName = var9.filterName();
                  Map filterParams = var9.filterParams();
                  String proxyBase = var9.proxyBase();
                  this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().addWebUIFilter(filterName, filterParams, proxyBase);
                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveExecutor var13) {
                  String executorId = var13.executorId();
                  ExecutorLossReason reason = var13.reason();
                  if (!this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$stopped().get()) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Requesting driver to remove executor "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " for reason ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, executorId), new MDC(org.apache.spark.internal.LogKeys.REASON..MODULE$, reason)}))))));
                     this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().driverEndpoint().send(var13);
                     return BoxedUnit.UNIT;
                  } else {
                     return BoxedUnit.UNIT;
                  }
               } else if (x1 instanceof CoarseGrainedClusterMessages.MiscellaneousProcessAdded var16) {
                  this.$outer.logDebug((Function0)(() -> "Sending the Spark AM info for yarn client mode"));
                  this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().driverEndpoint().send(var16);
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               if (x1 instanceof CoarseGrainedClusterMessages.RegisterClusterManager) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.DecommissionExecutorsOnHost) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.AddWebUIFilter) {
                  return true;
               } else if (x1 instanceof CoarseGrainedClusterMessages.RemoveExecutor) {
                  return true;
               } else {
                  return x1 instanceof CoarseGrainedClusterMessages.MiscellaneousProcessAdded;
               }
            }

            public {
               if (YarnSchedulerEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = YarnSchedulerEndpoint.this;
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
            private final YarnSchedulerEndpoint $outer;
            public final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof CoarseGrainedClusterMessages.RequestExecutors var7) {
                  Option var8 = this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint();
                  if (var8 instanceof Some var9) {
                     RpcEndpointRef am = (RpcEndpointRef)var9.value();
                     am.ask(var7, scala.reflect.ClassTag..MODULE$.Boolean()).andThen(new Serializable(var7) {
                        private static final long serialVersionUID = 0L;
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;
                        private final CoarseGrainedClusterMessages.RequestExecutors x2$1;

                        public final Object applyOrElse(final Try x1, final Function1 default) {
                           if (x1 instanceof Success var5) {
                              boolean b = BoxesRunTime.unboxToBoolean(var5.value());
                              this.$outer.context$1.reply(BoxesRunTime.boxToBoolean(b));
                              return BoxedUnit.UNIT;
                           } else {
                              if (x1 instanceof Failure var7) {
                                 Throwable var8 = var7.exception();
                                 if (var8 != null) {
                                    Option var9 = scala.util.control.NonFatal..MODULE$.unapply(var8);
                                    if (!var9.isEmpty()) {
                                       Throwable e = (Throwable)var9.get();
                                       this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$anonfun$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$anonfun$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Sending ", " to AM was unsuccessful"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.REQUEST_EXECUTORS..MODULE$, this.x2$1)})))), e);
                                       this.$outer.context$1.sendFailure(e);
                                       return BoxedUnit.UNIT;
                                    }
                                 }
                              }

                              return default.apply(x1);
                           }
                        }

                        public final boolean isDefinedAt(final Try x1) {
                           if (x1 instanceof Success) {
                              return true;
                           } else {
                              if (x1 instanceof Failure) {
                                 Failure var4 = (Failure)x1;
                                 Throwable var5 = var4.exception();
                                 if (var5 != null) {
                                    Option var6 = scala.util.control.NonFatal..MODULE$.unapply(var5);
                                    if (!var6.isEmpty()) {
                                       return true;
                                    }
                                 }
                              }

                              return false;
                           }
                        }

                        public {
                           if (<VAR_NAMELESS_ENCLOSURE> == null) {
                              throw null;
                           } else {
                              this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              this.x2$1 = x2$1;
                           }
                        }

                        // $FF: synthetic method
                        private static Object $deserializeLambda$(SerializedLambda var0) {
                           return var0.lambdaDeserialize<invokedynamic>(var0);
                        }
                     }, org.apache.spark.util.ThreadUtils..MODULE$.sameThread());
                     BoxedUnit var16 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var8)) {
                        throw new MatchError(var8);
                     }

                     this.$outer.logWarning((Function0)(() -> "Attempted to request executors before the AM has registered!"));
                     this.context$1.reply(BoxesRunTime.boxToBoolean(false));
                     BoxedUnit var17 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (x1 instanceof CoarseGrainedClusterMessages.KillExecutors var11) {
                  Option var12 = this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint();
                  if (var12 instanceof Some var13) {
                     RpcEndpointRef am = (RpcEndpointRef)var13.value();
                     am.ask(var11, scala.reflect.ClassTag..MODULE$.Boolean()).andThen(new Serializable(var11) {
                        private static final long serialVersionUID = 0L;
                        // $FF: synthetic field
                        private final <undefinedtype> $outer;
                        private final CoarseGrainedClusterMessages.KillExecutors x3$1;

                        public final Object applyOrElse(final Try x2, final Function1 default) {
                           if (x2 instanceof Success var5) {
                              boolean b = BoxesRunTime.unboxToBoolean(var5.value());
                              this.$outer.context$1.reply(BoxesRunTime.boxToBoolean(b));
                              return BoxedUnit.UNIT;
                           } else {
                              if (x2 instanceof Failure var7) {
                                 Throwable var8 = var7.exception();
                                 if (var8 != null) {
                                    Option var9 = scala.util.control.NonFatal..MODULE$.unapply(var8);
                                    if (!var9.isEmpty()) {
                                       Throwable e = (Throwable)var9.get();
                                       this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$anonfun$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$anonfun$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Sending ", " to AM was unsuccessful"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KILL_EXECUTORS..MODULE$, this.x3$1)})))), e);
                                       this.$outer.context$1.sendFailure(e);
                                       return BoxedUnit.UNIT;
                                    }
                                 }
                              }

                              return default.apply(x2);
                           }
                        }

                        public final boolean isDefinedAt(final Try x2) {
                           if (x2 instanceof Success) {
                              return true;
                           } else {
                              if (x2 instanceof Failure) {
                                 Failure var4 = (Failure)x2;
                                 Throwable var5 = var4.exception();
                                 if (var5 != null) {
                                    Option var6 = scala.util.control.NonFatal..MODULE$.unapply(var5);
                                    if (!var6.isEmpty()) {
                                       return true;
                                    }
                                 }
                              }

                              return false;
                           }
                        }

                        public {
                           if (<VAR_NAMELESS_ENCLOSURE> == null) {
                              throw null;
                           } else {
                              this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              this.x3$1 = x3$1;
                           }
                        }

                        // $FF: synthetic method
                        private static Object $deserializeLambda$(SerializedLambda var0) {
                           return var0.lambdaDeserialize<invokedynamic>(var0);
                        }
                     }, org.apache.spark.util.ThreadUtils..MODULE$.sameThread());
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  } else {
                     if (!scala.None..MODULE$.equals(var12)) {
                        throw new MatchError(var12);
                     }

                     this.$outer.logWarning((Function0)(() -> "Attempted to kill executors before the AM has registered!"));
                     this.context$1.reply(BoxesRunTime.boxToBoolean(false));
                     BoxedUnit var15 = BoxedUnit.UNIT;
                  }

                  return BoxedUnit.UNIT;
               } else if (org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveLastAllocatedExecutorId..MODULE$.equals(x1)) {
                  this.context$1.reply(BoxesRunTime.boxToInteger(this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().currentExecutorIdCounter()));
                  return BoxedUnit.UNIT;
               } else if (org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveDelegationTokens..MODULE$.equals(x1)) {
                  this.context$1.reply(this.$outer.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().currentDelegationTokens());
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
               } else if (org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveLastAllocatedExecutorId..MODULE$.equals(x1)) {
                  return true;
               } else {
                  return org.apache.spark.scheduler.cluster.CoarseGrainedClusterMessages.RetrieveDelegationTokens..MODULE$.equals(x1);
               }
            }

            // $FF: synthetic method
            public YarnSchedulerEndpoint org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$anonfun$$$outer() {
               return this.$outer;
            }

            public {
               if (YarnSchedulerEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = YarnSchedulerEndpoint.this;
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
         if (this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint().exists((x$4) -> BoxesRunTime.boxToBoolean($anonfun$onDisconnected$2(remoteAddress, x$4)))) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"ApplicationMaster has disassociated: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, remoteAddress)}))))));
            this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().amEndpoint_$eq(scala.None..MODULE$);
         }
      }

      // $FF: synthetic method
      public YarnSchedulerBackend org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$org$apache$spark$scheduler$cluster$YarnSchedulerBackend$$handleExecutorDisconnectedFromDriver$3(final YarnSchedulerEndpoint $this, final CoarseGrainedClusterMessages.RemoveExecutor message) {
         $this.org$apache$spark$scheduler$cluster$YarnSchedulerBackend$YarnSchedulerEndpoint$$$outer().driverEndpoint().send(message);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$onDisconnected$2(final RpcAddress remoteAddress$1, final RpcEndpointRef x$4) {
         boolean var3;
         label23: {
            RpcAddress var10000 = x$4.address();
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

      public YarnSchedulerEndpoint(final RpcEnv rpcEnv) {
         this.rpcEnv = rpcEnv;
         if (YarnSchedulerBackend.this == null) {
            throw null;
         } else {
            this.$outer = YarnSchedulerBackend.this;
            super();
            RpcEndpoint.$init$(this);
            Logging.$init$(this);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
