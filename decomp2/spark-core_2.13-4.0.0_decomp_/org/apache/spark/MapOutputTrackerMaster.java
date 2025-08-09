package org.apache.spark;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.spark.broadcast.BroadcastManager;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.scheduler.MapStatus;
import org.apache.spark.scheduler.MergeStatus;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.roaringbitmap.RoaringBitmap;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.collection.concurrent.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.concurrent.Awaitable;
import scala.concurrent.ExecutionContextExecutor;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rmh!\u0002%J\u0001%{\u0005\u0002\u0003+\u0001\u0005\u0003\u0005\u000b\u0011\u0002,\t\u0013e\u0003!Q1A\u0005\u0002%S\u0006\u0002C1\u0001\u0005\u0003\u0005\u000b\u0011B.\t\u0013\t\u0004!Q1A\u0005\u0002%\u001b\u0007\u0002\u00036\u0001\u0005\u0003\u0005\u000b\u0011\u00023\t\u000b-\u0004A\u0011\u00017\t\u000fE\u0004!\u0019!C\u0005e\"1a\u000f\u0001Q\u0001\nMDqa\u001e\u0001C\u0002\u0013%1\r\u0003\u0004y\u0001\u0001\u0006I\u0001\u001a\u0005\bs\u0002\u0011\r\u0011\"\u0003d\u0011\u0019Q\b\u0001)A\u0005I\"91\u0010\u0001b\u0001\n\u0013\u0011\bB\u0002?\u0001A\u0003%1\u000fC\u0004~\u0001\t\u0007I\u0011\u0002:\t\ry\u0004\u0001\u0015!\u0003t\u0011!y\bA1A\u0005\n\u0005\u0005\u0001\u0002CA\u0005\u0001\u0001\u0006I!a\u0001\t\u0013\u0005-\u0001A1A\u0005\u0002\u00055\u0001\u0002CA\u0013\u0001\u0001\u0006I!a\u0004\t\u0011\u0005\u001d\u0002A1A\u0005\nIDq!!\u000b\u0001A\u0003%1\u000fC\u0005\u0002,\u0001\u0011\r\u0011\"\u0003\u0002.!A\u0011q\t\u0001!\u0002\u0013\ty\u0003\u0003\u0005\u0002J\u0001\u0011\r\u0011\"\u0003d\u0011\u001d\tY\u0005\u0001Q\u0001\n\u0011D\u0011\"!\u0014\u0001\u0005\u0004%I!a\u0014\t\u0011\u0005]\u0003\u0001)A\u0005\u0003#B\u0001\"!\u0017\u0001\u0005\u0004%IA\u001d\u0005\b\u00037\u0002\u0001\u0015!\u0003t\u0011\u001d\ti\u0006\u0001C\u0001\u0003?2a!a\u001b\u0001\t\u00055\u0004BB6!\t\u0003\t\t\tC\u0004\u0002\b\u0002\"I!!#\t\u000f\u0005\r\u0006\u0005\"\u0011\u0002&\"I\u0011q\u0015\u0001C\u0002\u0013%\u0011\u0011\u0016\u0005\t\u0003c\u0003\u0001\u0015!\u0003\u0002,\"9\u00111\u0017\u0001\u0005\u0002%\u0013\bbBA[\u0001\u0011\u0005\u0011q\u0017\u0005\b\u0003\u0007\u0004A\u0011AAc\u0011\u001d\t\u0019\u000f\u0001C\u0001\u0003KDq!!@\u0001\t\u0003\ty\u0010C\u0004\u0003\b\u0001!\tA!\u0003\t\u000f\t5\u0001\u0001\"\u0001\u0003\u0010!9!q\u0004\u0001\u0005\u0002\t\u0005\u0002b\u0002B$\u0001\u0011\u0005!\u0011\n\u0005\b\u0005'\u0002A\u0011\u0001B+\u0011%\u0011)\u0007AI\u0001\n\u0003\u00119\u0007C\u0004\u0003~\u0001!\tAa \t\u000f\t\r\u0005\u0001\"\u0001\u0003\u0006\"9!\u0011\u0012\u0001\u0005\u0002\t-\u0005b\u0002BQ\u0001\u0011\u0005!1\u0015\u0005\b\u0005S\u0003A\u0011\u0001BV\u0011\u001d\u0011y\u000b\u0001C\u0001\u0005cC\u0001B!.\u0001\t\u0003I%q\u0017\u0005\b\u0005w\u0003A\u0011\u0001B_\u0011\u001d\u0011)\r\u0001C\u0001\u0005\u000fDqA!7\u0001\t\u0003\u0011Y\u000eC\u0004\u0003h\u0002!\tA!;\t\u000f\r}\u0001\u0001\"\u0001\u0004\"!91\u0011\t\u0001\u0005\u0002\r\r\u0003bBB.\u0001\u0011\u00051Q\f\u0005\b\u0007\u007f\u0002A\u0011ABA\u0011\u001d\u0019I\t\u0001C\u0001\u0003KCqaa#\u0001\t\u0003\u0019i\tC\u0004\u0004\u0010\u0002!\te!%\t\u000f\rm\u0006\u0001\"\u0011\u0004>\"91q\u001a\u0001\u0005B\rE\u0007bBBh\u0001\u0011\u00053Q\u001c\u0005\b\u0007g\u0004A\u0011IB{\u0011\u001d\u0019I\u0010\u0001C!\u0003K\u0013a#T1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:NCN$XM\u001d\u0006\u0003\u0015.\u000bQa\u001d9be.T!\u0001T'\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005q\u0015aA8sON\u0011\u0001\u0001\u0015\t\u0003#Jk\u0011!S\u0005\u0003'&\u0013\u0001#T1q\u001fV$\b/\u001e;Ue\u0006\u001c7.\u001a:\u0002\t\r|gNZ\u0002\u0001!\t\tv+\u0003\u0002Y\u0013\nI1\u000b]1sW\u000e{gNZ\u0001\u0011EJ|\u0017\rZ2bgRl\u0015M\\1hKJ,\u0012a\u0017\t\u00039~k\u0011!\u0018\u0006\u0003=&\u000b\u0011B\u0019:pC\u0012\u001c\u0017m\u001d;\n\u0005\u0001l&\u0001\u0005\"s_\u0006$7-Y:u\u001b\u0006t\u0017mZ3s\u0003E\u0011'o\\1eG\u0006\u001cH/T1oC\u001e,'\u000fI\u0001\bSNdunY1m+\u0005!\u0007CA3i\u001b\u00051'\"A4\u0002\u000bM\u001c\u0017\r\\1\n\u0005%4'a\u0002\"p_2,\u0017M\\\u0001\tSNdunY1mA\u00051A(\u001b8jiz\"B!\u001c8paB\u0011\u0011\u000b\u0001\u0005\u0006)\u001a\u0001\rA\u0016\u0005\u00063\u001a\u0001\ra\u0017\u0005\u0006E\u001a\u0001\r\u0001Z\u0001\u0014[&t7+\u001b>f\r>\u0014(I]8bI\u000e\f7\u000f^\u000b\u0002gB\u0011Q\r^\u0005\u0003k\u001a\u00141!\u00138u\u0003Qi\u0017N\\*ju\u00164uN\u001d\"s_\u0006$7-Y:uA\u000512\u000f[;gM2,Gj\\2bY&$\u00180\u00128bE2,G-A\ftQV4g\r\\3M_\u000e\fG.\u001b;z\u000b:\f'\r\\3eA\u000592\u000f[;gM2,W*[4sCRLwN\\#oC\ndW\rZ\u0001\u0019g\",hM\u001a7f\u001b&<'/\u0019;j_:,e.\u00192mK\u0012\u0004\u0013AG*I+\u001a3E*R0Q%\u00163u,T!Q?RC%+R*I\u001f2#\u0015aG*I+\u001a3E*R0Q%\u00163u,T!Q?RC%+R*I\u001f2#\u0005%A\u000fT\u0011V3e\tT#`!J+ei\u0018*F\tV\u001bUi\u0018+I%\u0016\u001b\u0006j\u0014'E\u0003y\u0019\u0006*\u0016$G\u0019\u0016{\u0006KU#G?J+E)V\"F?RC%+R*I\u001f2#\u0005%\u0001\u000eS\u000b\u0012+6)\u0012*`!J+ei\u0018'P\u0007N{fIU!D)&{e*\u0006\u0002\u0002\u0004A\u0019Q-!\u0002\n\u0007\u0005\u001daM\u0001\u0004E_V\u0014G.Z\u0001\u001c%\u0016#UkQ#S?B\u0013VIR0M\u001f\u000e\u001bvL\u0012*B\u0007RKuJ\u0014\u0011\u0002\u001fMDWO\u001a4mKN#\u0018\r^;tKN,\"!a\u0004\u0011\u000f\u0005E\u00111D:\u0002 5\u0011\u00111\u0003\u0006\u0005\u0003+\t9\"\u0001\u0006d_:\u001cWO\u001d:f]RT1!!\u0007g\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003;\t\u0019BA\u0002NCB\u00042!UA\u0011\u0013\r\t\u0019#\u0013\u0002\u000e'\",hM\u001a7f'R\fG/^:\u0002!MDWO\u001a4mKN#\u0018\r^;tKN\u0004\u0013!E7bqJ\u00038-T3tg\u0006<WmU5{K\u0006\u0011R.\u0019=Sa\u000elUm]:bO\u0016\u001c\u0016N_3!\u0003yi\u0017\r](viB,H\u000f\u0016:bG.,'/T1ti\u0016\u0014X*Z:tC\u001e,7/\u0006\u0002\u00020A1\u0011\u0011GA\u001f\u0003\u0003j!!a\r\u000b\t\u0005U\u0011Q\u0007\u0006\u0005\u0003o\tI$\u0001\u0003vi&d'BAA\u001e\u0003\u0011Q\u0017M^1\n\t\u0005}\u00121\u0007\u0002\u0014\u0019&t7.\u001a3CY>\u001c7.\u001b8h#V,W/\u001a\t\u0004#\u0006\r\u0013bAA#\u0013\niR*\u00199PkR\u0004X\u000f\u001e+sC\u000e\\WM]'bgR,'/T3tg\u0006<W-A\u0010nCB|U\u000f\u001e9viR\u0013\u0018mY6fe6\u000b7\u000f^3s\u001b\u0016\u001c8/Y4fg\u0002\nq\u0003];tQ\n\u000b7/\u001a3TQV4g\r\\3F]\u0006\u0014G.\u001a3\u00021A,8\u000f\u001b\"bg\u0016$7\u000b[;gM2,WI\\1cY\u0016$\u0007%\u0001\u0006uQJ,\u0017\r\u001a9p_2,\"!!\u0015\u0011\t\u0005E\u00121K\u0005\u0005\u0003+\n\u0019D\u0001\nUQJ,\u0017\r\u001a)p_2,\u00050Z2vi>\u0014\u0018a\u0003;ie\u0016\fG\r]8pY\u0002\n1#\u0019<bS2\f'\r\\3Qe>\u001cWm]:peN\fA#\u0019<bS2\f'\r\\3Qe>\u001cWm]:peN\u0004\u0013\u0001\u00029pgR$B!!\u0019\u0002hA\u0019Q-a\u0019\n\u0007\u0005\u0015dM\u0001\u0003V]&$\bbBA5?\u0001\u0007\u0011\u0011I\u0001\b[\u0016\u001c8/Y4f\u0005-iUm]:bO\u0016dun\u001c9\u0014\u000b\u0001\ny'a\u001f\u0011\t\u0005E\u0014qO\u0007\u0003\u0003gRA!!\u001e\u0002:\u0005!A.\u00198h\u0013\u0011\tI(a\u001d\u0003\r=\u0013'.Z2u!\u0011\t\t(! \n\t\u0005}\u00141\u000f\u0002\t%Vtg.\u00192mKR\u0011\u00111\u0011\t\u0004\u0003\u000b\u0003S\"\u0001\u0001\u0002'!\fg\u000e\u001a7f'R\fG/^:NKN\u001c\u0018mZ3\u0015\u0011\u0005\u0005\u00141RAH\u0003?Ca!!$#\u0001\u0004\u0019\u0018!C:ik\u001a4G.Z%e\u0011\u001d\t\tJ\ta\u0001\u0003'\u000bqaY8oi\u0016DH\u000f\u0005\u0003\u0002\u0016\u0006mUBAAL\u0015\r\tI*S\u0001\u0004eB\u001c\u0017\u0002BAO\u0003/\u0013aB\u00159d\u0007\u0006dGnQ8oi\u0016DH\u000f\u0003\u0004\u0002\"\n\u0002\r\u0001Z\u0001\u0010]\u0016,G-T3sO\u0016|U\u000f\u001e9vi\u0006\u0019!/\u001e8\u0015\u0005\u0005\u0005\u0014A\u0003)pSN|g\u000eU5mYV\u0011\u00111\u0016\t\u0004#\u00065\u0016bAAX\u0013\n\u0019r)\u001a;NCB|U\u000f\u001e9vi6+7o]1hK\u0006Y\u0001k\\5t_:\u0004\u0016\u000e\u001c7!\u0003}9W\r\u001e(v[\u000e\u000b7\r[3e'\u0016\u0014\u0018.\u00197ju\u0016$'I]8bI\u000e\f7\u000f^\u0001\u0010e\u0016<\u0017n\u001d;feNCWO\u001a4mKRA\u0011\u0011MA]\u0003w\u000by\f\u0003\u0004\u0002\u000e\u001e\u0002\ra\u001d\u0005\u0007\u0003{;\u0003\u0019A:\u0002\u000f9,X.T1qg\"1\u0011\u0011Y\u0014A\u0002M\f!B\\;n%\u0016$WoY3t\u0003=)\b\u000fZ1uK6\u000b\u0007oT;uaV$H\u0003CA1\u0003\u000f\fI-a5\t\r\u00055\u0005\u00061\u0001t\u0011\u001d\tY\r\u000ba\u0001\u0003\u001b\fQ!\\1q\u0013\u0012\u00042!ZAh\u0013\r\t\tN\u001a\u0002\u0005\u0019>tw\rC\u0004\u0002V\"\u0002\r!a6\u0002\u0013\tl\u0017\t\u001a3sKN\u001c\b\u0003BAm\u0003?l!!a7\u000b\u0007\u0005u\u0017*A\u0004ti>\u0014\u0018mZ3\n\t\u0005\u0005\u00181\u001c\u0002\u000f\u00052|7m['b]\u0006<WM]%e\u0003E\u0011XmZ5ti\u0016\u0014X*\u00199PkR\u0004X\u000f\u001e\u000b\t\u0003C\n9/!;\u0002n\"1\u0011QR\u0015A\u0002MDa!a;*\u0001\u0004\u0019\u0018\u0001C7ba&sG-\u001a=\t\u000f\u0005=\u0018\u00061\u0001\u0002r\u000611\u000f^1ukN\u0004B!a=\u0002z6\u0011\u0011Q\u001f\u0006\u0004\u0003oL\u0015!C:dQ\u0016$W\u000f\\3s\u0013\u0011\tY0!>\u0003\u00135\u000b\u0007o\u0015;biV\u001c\u0018aE;oe\u0016<\u0017n\u001d;fe6\u000b\u0007oT;uaV$H\u0003CA1\u0005\u0003\u0011\u0019A!\u0002\t\r\u00055%\u00061\u0001t\u0011\u0019\tYO\u000ba\u0001g\"9\u0011Q\u001b\u0016A\u0002\u0005]\u0017AH;oe\u0016<\u0017n\u001d;fe\u0006cG.T1q\u0003:$W*\u001a:hK>+H\u000f];u)\u0011\t\tGa\u0003\t\r\u000555\u00061\u0001t\u0003M\u0011XmZ5ti\u0016\u0014X*\u001a:hKJ+7/\u001e7u)!\t\tG!\u0005\u0003\u0014\t]\u0001BBAGY\u0001\u00071\u000f\u0003\u0004\u0003\u00161\u0002\ra]\u0001\te\u0016$WoY3JI\"9\u0011q\u001e\u0017A\u0002\te\u0001\u0003BAz\u00057IAA!\b\u0002v\nYQ*\u001a:hKN#\u0018\r^;t\u0003Q\u0011XmZ5ti\u0016\u0014X*\u001a:hKJ+7/\u001e7ugR1\u0011\u0011\rB\u0012\u0005KAa!!$.\u0001\u0004\u0019\bb\u0002B\u0014[\u0001\u0007!\u0011F\u0001\tgR\fG/^:fgB1!1\u0006B\u001e\u0005\u0003rAA!\f\u000389!!q\u0006B\u001b\u001b\t\u0011\tDC\u0002\u00034U\u000ba\u0001\u0010:p_Rt\u0014\"A4\n\u0007\teb-A\u0004qC\u000e\\\u0017mZ3\n\t\tu\"q\b\u0002\u0004'\u0016\f(b\u0001B\u001dMB1QMa\u0011t\u00053I1A!\u0012g\u0005\u0019!V\u000f\u001d7fe\u0005\u0011#/Z4jgR,'o\u00155vM\u001adW\rU;tQ6+'oZ3s\u0019>\u001c\u0017\r^5p]N$b!!\u0019\u0003L\t5\u0003BBAG]\u0001\u00071\u000fC\u0004\u0003P9\u0002\rA!\u0015\u0002\u001dMDWO\u001a4mK6+'oZ3sgB1!1\u0006B\u001e\u0003/\fQ#\u001e8sK\u001eL7\u000f^3s\u001b\u0016\u0014x-\u001a*fgVdG\u000f\u0006\u0006\u0002b\t]#\u0011\fB.\u0005;Ba!!$0\u0001\u0004\u0019\bB\u0002B\u000b_\u0001\u00071\u000fC\u0004\u0002V>\u0002\r!a6\t\u0013\u0005-x\u0006%AA\u0002\t}\u0003\u0003B3\u0003bML1Aa\u0019g\u0005\u0019y\u0005\u000f^5p]\u0006yRO\u001c:fO&\u001cH/\u001a:NKJ<WMU3tk2$H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\t%$\u0006\u0002B0\u0005WZ#A!\u001c\u0011\t\t=$\u0011P\u0007\u0003\u0005cRAAa\u001d\u0003v\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005o2\u0017AC1o]>$\u0018\r^5p]&!!1\u0010B9\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0019k:\u0014XmZ5ti\u0016\u0014\u0018\t\u001c7NKJ<WMU3tk2$H\u0003BA1\u0005\u0003Ca!!$2\u0001\u0004\u0019\u0018!E;oe\u0016<\u0017n\u001d;feNCWO\u001a4mKR!\u0011\u0011\rBD\u0011\u0019\tiI\ra\u0001g\u0006\u0019\"/Z7pm\u0016|U\u000f\u001e9viN|e\u000eS8tiR!\u0011\u0011\rBG\u0011\u001d\u0011yi\ra\u0001\u0005#\u000bA\u0001[8tiB!!1\u0013BN\u001d\u0011\u0011)Ja&\u0011\u0007\t=b-C\u0002\u0003\u001a\u001a\fa\u0001\u0015:fI\u00164\u0017\u0002\u0002BO\u0005?\u0013aa\u0015;sS:<'b\u0001BMM\u00069\"/Z7pm\u0016|U\u000f\u001e9viN|e.\u0012=fGV$xN\u001d\u000b\u0005\u0003C\u0012)\u000bC\u0004\u0003(R\u0002\rA!%\u0002\r\u0015DXmY%e\u0003=\u0019wN\u001c;bS:\u001c8\u000b[;gM2,Gc\u00013\u0003.\"1\u0011QR\u001bA\u0002M\facZ3u\u001dVl\u0017I^1jY\u0006\u0014G.Z(viB,Ho\u001d\u000b\u0004g\nM\u0006BBAGm\u0001\u00071/A\u000ehKRtU/\\!wC&d\u0017M\u00197f\u001b\u0016\u0014x-\u001a*fgVdGo\u001d\u000b\u0004g\ne\u0006BBAGo\u0001\u00071/A\u000bgS:$W*[:tS:<\u0007+\u0019:uSRLwN\\:\u0015\t\t}&1\u0019\t\u0006K\n\u0005$\u0011\u0019\t\u0006\u0005W\u0011Yd\u001d\u0005\u0007\u0003\u001bC\u0004\u0019A:\u0002\u0019I\fgnZ3He>,\b/\u001a3\u0015\r\t%'\u0011\u001bBk!\u0019\u0011YCa\u000f\u0003LB!!1\u0006Bg\u0013\u0011\u0011yMa\u0010\u0003\u000bI\u000bgnZ3\t\u000f\tM\u0017\b1\u0001\u0003L\u0006)!/\u00198hK\"1!q[\u001dA\u0002M\fAa]5{K\u0006iQ-];bY2LH)\u001b<jI\u0016$bA!8\u0003`\n\r\bC\u0002B\u0016\u0005w\u0011\t\r\u0003\u0004\u0003bj\u0002\ra]\u0001\f]VlW\t\\3nK:$8\u000f\u0003\u0004\u0003fj\u0002\ra]\u0001\u000b]Vl')^2lKR\u001c\u0018!D4fiN#\u0018\r^5ti&\u001c7\u000f\u0006\u0003\u0003l\nE\bcA)\u0003n&\u0019!q^%\u0003'5\u000b\u0007oT;uaV$8\u000b^1uSN$\u0018nY:\t\u000f\tM8\b1\u0001\u0003v\u0006\u0019A-\u001a91\u0011\t]8\u0011AB\u000b\u00077\u0001\u0012\"\u0015B}\u0005{\u001c\u0019b!\u0007\n\u0007\tm\u0018JA\tTQV4g\r\\3EKB,g\u000eZ3oGf\u0004BAa@\u0004\u00021\u0001A\u0001DB\u0002\u0005c\f\t\u0011!A\u0003\u0002\r\u0015!aA0%cE!1qAB\u0007!\r)7\u0011B\u0005\u0004\u0007\u00171'a\u0002(pi\"Lgn\u001a\t\u0004K\u000e=\u0011bAB\tM\n\u0019\u0011I\\=\u0011\t\t}8Q\u0003\u0003\r\u0007/\u0011\t0!A\u0001\u0002\u000b\u00051Q\u0001\u0002\u0004?\u0012\u0012\u0004\u0003\u0002B\u0000\u00077!Ab!\b\u0003r\u0006\u0005\t\u0011!B\u0001\u0007\u000b\u00111a\u0018\u00134\u0003}9W\r\u001e)sK\u001a,'O]3e\u0019>\u001c\u0017\r^5p]N4uN]*ik\u001a4G.\u001a\u000b\u0007\u0007G\u0019)c!\u0010\u0011\r\t-\"1\bBI\u0011\u001d\u0011\u0019\u0010\u0010a\u0001\u0007O\u0001\u0004b!\u000b\u0004.\rM2\u0011\b\t\n#\ne81FB\u0019\u0007o\u0001BAa@\u0004.\u0011a1qFB\u0013\u0003\u0003\u0005\tQ!\u0001\u0004\u0006\t\u0019q\f\n\u001b\u0011\t\t}81\u0007\u0003\r\u0007k\u0019)#!A\u0001\u0002\u000b\u00051Q\u0001\u0002\u0004?\u0012*\u0004\u0003\u0002B\u0000\u0007s!Aba\u000f\u0004&\u0005\u0005\t\u0011!B\u0001\u0007\u000b\u00111a\u0018\u00137\u0011\u0019\u0019y\u0004\u0010a\u0001g\u0006Y\u0001/\u0019:uSRLwN\\%e\u0003y9W\r\u001e'pG\u0006$\u0018n\u001c8t/&$\b\u000eT1sO\u0016\u001cHoT;uaV$8\u000f\u0006\u0006\u0004F\r53qJB*\u0007/\u0002R!\u001aB1\u0007\u000f\u0002R!ZB%\u0003/L1aa\u0013g\u0005\u0015\t%O]1z\u0011\u0019\ti)\u0010a\u0001g\"11\u0011K\u001fA\u0002M\f\u0011B]3ek\u000e,'/\u00133\t\r\rUS\b1\u0001t\u0003-qW/\u001c*fIV\u001cWM]:\t\u000f\reS\b1\u0001\u0002\u0004\u0005\tbM]1di&|g\u000e\u00165sKNDw\u000e\u001c3\u0002\u001d\u001d,G/T1q\u0019>\u001c\u0017\r^5p]RA11EB0\u0007o\u001aY\bC\u0004\u0003tz\u0002\ra!\u00191\u0011\r\r4qMB7\u0007g\u0002\u0012\"\u0015B}\u0007K\u001aYg!\u001d\u0011\t\t}8q\r\u0003\r\u0007S\u001ay&!A\u0001\u0002\u000b\u00051Q\u0001\u0002\u0004?\u0012:\u0004\u0003\u0002B\u0000\u0007[\"Aba\u001c\u0004`\u0005\u0005\t\u0011!B\u0001\u0007\u000b\u00111a\u0018\u00139!\u0011\u0011ypa\u001d\u0005\u0019\rU4qLA\u0001\u0002\u0003\u0015\ta!\u0002\u0003\u0007}#\u0013\b\u0003\u0004\u0004zy\u0002\ra]\u0001\u000egR\f'\u000f^'ba&sG-\u001a=\t\r\rud\b1\u0001t\u0003-)g\u000eZ'ba&sG-\u001a=\u0002)\u001d,G/T1q\u001fV$\b/\u001e;M_\u000e\fG/[8o)\u0019\u0019\u0019i!\"\u0004\bB)QM!\u0019\u0002X\"1\u0011QR A\u0002MDq!a3@\u0001\u0004\ti-\u0001\bj]\u000e\u0014X-\\3oi\u0016\u0003xn\u00195\u0002\u0011\u001d,G/\u00129pG\",\"!!4\u0002/\u001d,G/T1q'&TXm\u001d\"z\u000bb,7-\u001e;pe&#G\u0003DBJ\u0007[\u001byk!-\u00044\u000e]\u0006C\u0002B\u0016\u0007+\u001bI*\u0003\u0003\u0004\u0018\n}\"\u0001C%uKJ\fGo\u001c:\u0011\u000f\u0015\u0014\u0019%a6\u0004\u001cB11QTBP\u0007Ck!!a\u0006\n\t\tu\u0012q\u0003\t\tK\u000e\r6qUAgg&\u00191Q\u00154\u0003\rQ+\b\u000f\\34!\u0011\tIn!+\n\t\r-\u00161\u001c\u0002\b\u00052|7m[%e\u0011\u0019\tiI\u0011a\u0001g\"11\u0011\u0010\"A\u0002MDaa! C\u0001\u0004\u0019\bBBB[\u0005\u0002\u00071/\u0001\bti\u0006\u0014H\u000fU1si&$\u0018n\u001c8\t\r\re&\t1\u0001t\u00031)g\u000e\u001a)beRLG/[8o\u0003\u001d:W\r\u001e)vg\"\u0014\u0015m]3e'\",hM\u001a7f\u001b\u0006\u00048+\u001b>fg\nKX\t_3dkR|'/\u00133\u0015\u0019\r}6QYBd\u0007\u0013\u001cYm!4\u0011\u0007E\u001b\t-C\u0002\u0004D&\u0013A#T1q'&TXm\u001d\"z\u000bb,7-\u001e;pe&#\u0007BBAG\u0007\u0002\u00071\u000f\u0003\u0004\u0004z\r\u0003\ra\u001d\u0005\u0007\u0007{\u001a\u0005\u0019A:\t\r\rU6\t1\u0001t\u0011\u0019\u0019Il\u0011a\u0001g\u0006Ir-\u001a;NCB\u001c\u0016N_3t\r>\u0014X*\u001a:hKJ+7/\u001e7u)\u0019\u0019\u0019n!7\u0004\\B1!1FBK\u0007+\u0004r!\u001aB\"\u0003/\u001c9\u000e\u0005\u0004\u0003,\tm2\u0011\u0015\u0005\u0007\u0003\u001b#\u0005\u0019A:\t\r\r}B\t1\u0001t)!\u0019\u0019na8\u0004b\u000e\r\bBBAG\u000b\u0002\u00071\u000f\u0003\u0004\u0004@\u0015\u0003\ra\u001d\u0005\b\u0007K,\u0005\u0019ABt\u00031\u0019\u0007.\u001e8l)J\f7m[3s!\u0011\u0019Ioa<\u000e\u0005\r-(bABw\u001b\u0006i!o\\1sS:<'-\u001b;nCBLAa!=\u0004l\ni!k\\1sS:<')\u001b;nCB\fQdZ3u'\",hM\u001a7f!V\u001c\b.T3sO\u0016\u0014Hj\\2bi&|gn\u001d\u000b\u0005\u0005#\u001a9\u0010\u0003\u0004\u0002\u000e\u001a\u0003\ra]\u0001\u0005gR|\u0007\u000f"
)
public class MapOutputTrackerMaster extends MapOutputTracker {
   public final SparkConf org$apache$spark$MapOutputTrackerMaster$$conf;
   private final BroadcastManager broadcastManager;
   private final boolean isLocal;
   private final int org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast;
   private final boolean shuffleLocalityEnabled;
   private final boolean shuffleMigrationEnabled;
   private final int SHUFFLE_PREF_MAP_THRESHOLD;
   private final int SHUFFLE_PREF_REDUCE_THRESHOLD;
   private final double REDUCER_PREF_LOCS_FRACTION;
   private final Map shuffleStatuses;
   private final int maxRpcMessageSize;
   private final LinkedBlockingQueue org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages;
   private final boolean pushBasedShuffleEnabled;
   private final ThreadPoolExecutor threadpool;
   private final int availableProcessors;
   private final GetMapOutputMessage org$apache$spark$MapOutputTrackerMaster$$PoisonPill;

   public BroadcastManager broadcastManager() {
      return this.broadcastManager;
   }

   public boolean isLocal() {
      return this.isLocal;
   }

   public int org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast() {
      return this.org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast;
   }

   private boolean shuffleLocalityEnabled() {
      return this.shuffleLocalityEnabled;
   }

   private boolean shuffleMigrationEnabled() {
      return this.shuffleMigrationEnabled;
   }

   private int SHUFFLE_PREF_MAP_THRESHOLD() {
      return this.SHUFFLE_PREF_MAP_THRESHOLD;
   }

   private int SHUFFLE_PREF_REDUCE_THRESHOLD() {
      return this.SHUFFLE_PREF_REDUCE_THRESHOLD;
   }

   private double REDUCER_PREF_LOCS_FRACTION() {
      return this.REDUCER_PREF_LOCS_FRACTION;
   }

   public Map shuffleStatuses() {
      return this.shuffleStatuses;
   }

   private int maxRpcMessageSize() {
      return this.maxRpcMessageSize;
   }

   public LinkedBlockingQueue org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages() {
      return this.org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages;
   }

   private boolean pushBasedShuffleEnabled() {
      return this.pushBasedShuffleEnabled;
   }

   private ThreadPoolExecutor threadpool() {
      return this.threadpool;
   }

   private int availableProcessors() {
      return this.availableProcessors;
   }

   public void post(final MapOutputTrackerMasterMessage message) {
      this.org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages().offer(message);
   }

   public GetMapOutputMessage org$apache$spark$MapOutputTrackerMaster$$PoisonPill() {
      return this.org$apache$spark$MapOutputTrackerMaster$$PoisonPill;
   }

   public int getNumCachedSerializedBroadcast() {
      return this.shuffleStatuses().valuesIterator().count((x$11) -> BoxesRunTime.boxToBoolean($anonfun$getNumCachedSerializedBroadcast$1(x$11)));
   }

   public void registerShuffle(final int shuffleId, final int numMaps, final int numReduces) {
      if (this.pushBasedShuffleEnabled()) {
         if (this.shuffleStatuses().put(BoxesRunTime.boxToInteger(shuffleId), new ShuffleStatus(numMaps, numReduces)).isDefined()) {
            throw new IllegalArgumentException("Shuffle ID " + shuffleId + " registered twice");
         }
      } else if (this.shuffleStatuses().put(BoxesRunTime.boxToInteger(shuffleId), new ShuffleStatus(numMaps, ShuffleStatus$.MODULE$.$lessinit$greater$default$2())).isDefined()) {
         throw new IllegalArgumentException("Shuffle ID " + shuffleId + " registered twice");
      }
   }

   public void updateMapOutput(final int shuffleId, final long mapId, final BlockManagerId bmAddress) {
      boolean var6 = false;
      Object var7 = null;
      Option var8 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var8 instanceof Some var9) {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var9.value();
         shuffleStatus.updateMapOutput(mapId, bmAddress);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else {
         if (.MODULE$.equals(var8)) {
            var6 = true;
            if (this.shuffleMigrationEnabled()) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to update map output for unknown shuffle "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))}))))));
               BoxedUnit var11 = BoxedUnit.UNIT;
               return;
            }
         }

         if (var6) {
            this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Asked to update map output for unknown shuffle ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.SHUFFLE_ID..MODULE$, BoxesRunTime.boxToInteger(shuffleId))})))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var8);
         }
      }
   }

   public void registerMapOutput(final int shuffleId, final int mapIndex, final MapStatus status) {
      ((ShuffleStatus)this.shuffleStatuses().apply(BoxesRunTime.boxToInteger(shuffleId))).addMapOutput(mapIndex, status);
   }

   public void unregisterMapOutput(final int shuffleId, final int mapIndex, final BlockManagerId bmAddress) {
      Option var5 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var5 instanceof Some var6) {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var6.value();
         shuffleStatus.removeMapOutput(mapIndex, bmAddress);
         this.incrementEpoch();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var5)) {
         throw new SparkException("unregisterMapOutput called for nonexistent shuffle ID");
      } else {
         throw new MatchError(var5);
      }
   }

   public void unregisterAllMapAndMergeOutput(final int shuffleId) {
      Option var3 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var3 instanceof Some var4) {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var4.value();
         shuffleStatus.removeOutputsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$unregisterAllMapAndMergeOutput$1(x)));
         shuffleStatus.removeMergeResultsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$unregisterAllMapAndMergeOutput$2(x)));
         shuffleStatus.removeShuffleMergerLocations();
         this.incrementEpoch();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         throw new SparkException("unregisterAllMapAndMergeOutput called for nonexistent shuffle ID " + shuffleId + ".");
      } else {
         throw new MatchError(var3);
      }
   }

   public void registerMergeResult(final int shuffleId, final int reduceId, final MergeStatus status) {
      ((ShuffleStatus)this.shuffleStatuses().apply(BoxesRunTime.boxToInteger(shuffleId))).addMergeResult(reduceId, status);
   }

   public void registerMergeResults(final int shuffleId, final Seq statuses) {
      statuses.foreach((x0$1) -> {
         $anonfun$registerMergeResults$1(this, shuffleId, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public void registerShufflePushMergerLocations(final int shuffleId, final Seq shuffleMergers) {
      ((ShuffleStatus)this.shuffleStatuses().apply(BoxesRunTime.boxToInteger(shuffleId))).registerShuffleMergerLocations(shuffleMergers);
   }

   public void unregisterMergeResult(final int shuffleId, final int reduceId, final BlockManagerId bmAddress, final Option mapIndex) {
      Option var6 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (!(var6 instanceof Some var7)) {
         if (.MODULE$.equals(var6)) {
            throw new SparkException("unregisterMergeResult called for nonexistent shuffle ID");
         } else {
            throw new MatchError(var6);
         }
      } else {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var7.value();
         MergeStatus mergeStatus = shuffleStatus.mergeStatuses()[reduceId];
         if (mergeStatus == null || !mapIndex.isEmpty() && !mergeStatus.tracker().contains(BoxesRunTime.unboxToInt(mapIndex.get()))) {
            BoxedUnit var10 = BoxedUnit.UNIT;
         } else {
            shuffleStatus.removeMergeResult(reduceId, bmAddress);
            this.incrementEpoch();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }
   }

   public Option unregisterMergeResult$default$4() {
      return .MODULE$;
   }

   public void unregisterAllMergeResult(final int shuffleId) {
      Option var3 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var3 instanceof Some var4) {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var4.value();
         shuffleStatus.removeMergeResultsByFilter((x) -> BoxesRunTime.boxToBoolean($anonfun$unregisterAllMergeResult$1(x)));
         this.incrementEpoch();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(var3)) {
         throw new SparkException("unregisterAllMergeResult called for nonexistent shuffle ID " + shuffleId + ".");
      } else {
         throw new MatchError(var3);
      }
   }

   public void unregisterShuffle(final int shuffleId) {
      this.shuffleStatuses().remove(BoxesRunTime.boxToInteger(shuffleId)).foreach((shuffleStatus) -> {
         $anonfun$unregisterShuffle$1(shuffleStatus);
         return BoxedUnit.UNIT;
      });
   }

   public void removeOutputsOnHost(final String host) {
      this.shuffleStatuses().valuesIterator().foreach((x$12) -> {
         $anonfun$removeOutputsOnHost$5(host, x$12);
         return BoxedUnit.UNIT;
      });
      this.incrementEpoch();
   }

   public void removeOutputsOnExecutor(final String execId) {
      this.shuffleStatuses().valuesIterator().foreach((x$13) -> {
         $anonfun$removeOutputsOnExecutor$4(execId, x$13);
         return BoxedUnit.UNIT;
      });
      this.incrementEpoch();
   }

   public boolean containsShuffle(final int shuffleId) {
      return this.shuffleStatuses().contains(BoxesRunTime.boxToInteger(shuffleId));
   }

   public int getNumAvailableOutputs(final int shuffleId) {
      return BoxesRunTime.unboxToInt(this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).map((x$14) -> BoxesRunTime.boxToInteger($anonfun$getNumAvailableOutputs$1(x$14))).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   public int getNumAvailableMergeResults(final int shuffleId) {
      return BoxesRunTime.unboxToInt(this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).map((x$15) -> BoxesRunTime.boxToInteger($anonfun$getNumAvailableMergeResults$1(x$15))).getOrElse((JFunction0.mcI.sp)() -> 0));
   }

   public Option findMissingPartitions(final int shuffleId) {
      return this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).map((x$16) -> x$16.findMissingPartitions());
   }

   public Seq rangeGrouped(final Range range, final int size) {
      int start = range.start();
      int step = range.step();
      int end = range.end();
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(start), end, size * step).map((i) -> $anonfun$rangeGrouped$1(size, step, BoxesRunTime.unboxToInt(i)));
   }

   public Seq equallyDivide(final int numElements, final int numBuckets) {
      int elementsPerBucket = numElements / numBuckets;
      int remaining = numElements % numBuckets;
      int splitPoint = (elementsPerBucket + 1) * remaining;
      return elementsPerBucket == 0 ? this.rangeGrouped(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), splitPoint), elementsPerBucket + 1) : (Seq)this.rangeGrouped(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), splitPoint), elementsPerBucket + 1).$plus$plus(this.rangeGrouped(scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(splitPoint), numElements), elementsPerBucket));
   }

   public MapOutputStatistics getStatistics(final ShuffleDependency dep) {
      return (MapOutputStatistics)((ShuffleStatus)this.shuffleStatuses().apply(BoxesRunTime.boxToInteger(dep.shuffleId()))).withMapStatuses((statuses) -> {
         long[] totalSizes = new long[dep.partitioner().numPartitions()];
         int parallelAggThreshold = BoxesRunTime.unboxToInt(this.org$apache$spark$MapOutputTrackerMaster$$conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAP_OUTPUT_PARALLEL_AGGREGATION_THRESHOLD()));
         int parallelism = (int)scala.math.package..MODULE$.min((long)this.availableProcessors(), (long)statuses.length * (long)totalSizes.length / (long)parallelAggThreshold + 1L);
         if (parallelism <= 1) {
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(statuses), (x$17) -> BoxesRunTime.boxToBoolean($anonfun$getStatistics$2(x$17)))), (s) -> {
               $anonfun$getStatistics$3(totalSizes, s);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            ThreadPoolExecutor threadPool = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(parallelism, "map-output-aggregate");

            try {
               ExecutionContextExecutor executionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutor(threadPool);
               Seq mapStatusSubmitTasks = (Seq)this.equallyDivide(totalSizes.length, parallelism).map((reduceIds) -> scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(statuses), (x$18) -> BoxesRunTime.boxToBoolean($anonfun$getStatistics$7(x$18)))), (s) -> {
                        $anonfun$getStatistics$8(reduceIds, totalSizes, s);
                        return BoxedUnit.UNIT;
                     }), executionContext));
               ThreadUtils$.MODULE$.awaitResult((Awaitable)scala.concurrent.Future..MODULE$.sequence(mapStatusSubmitTasks, scala.collection.BuildFrom..MODULE$.buildFromIterableOps(), executionContext), scala.concurrent.duration.Duration..MODULE$.Inf());
            } finally {
               threadPool.shutdown();
            }
         }

         return new MapOutputStatistics(dep.shuffleId(), totalSizes);
      });
   }

   public Seq getPreferredLocationsForShuffle(final ShuffleDependency dep, final int partitionId) {
      ShuffleStatus shuffleStatus = (ShuffleStatus)this.shuffleStatuses().get(BoxesRunTime.boxToInteger(dep.shuffleId())).orNull(scala..less.colon.less..MODULE$.refl());
      if (shuffleStatus != null) {
         Seq preferredLoc = (Seq)(this.pushBasedShuffleEnabled() ? (Seq)shuffleStatus.withMergeStatuses((statuses) -> {
            MergeStatus status = statuses[partitionId];
            int numMaps = dep.rdd().partitions().length;
            return (Seq)(status != null && (double)status.getNumMissingMapOutputs(numMaps) / (double)numMaps <= (double)1 - this.REDUCER_PREF_LOCS_FRACTION() ? new scala.collection.immutable..colon.colon(status.location().host(), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$);
         }) : scala.collection.immutable.Nil..MODULE$);
         if (preferredLoc.nonEmpty()) {
            return preferredLoc;
         } else if (this.shuffleLocalityEnabled() && dep.rdd().partitions().length < this.SHUFFLE_PREF_MAP_THRESHOLD() && dep.partitioner().numPartitions() < this.SHUFFLE_PREF_REDUCE_THRESHOLD()) {
            Option blockManagerIds = this.getLocationsWithLargestOutputs(dep.shuffleId(), partitionId, dep.partitioner().numPartitions(), this.REDUCER_PREF_LOCS_FRACTION());
            return (Seq)(blockManagerIds.nonEmpty() ? org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(blockManagerIds.get()), (x$19) -> x$19.host(), scala.reflect.ClassTag..MODULE$.apply(String.class))))).toImmutableArraySeq() : scala.collection.immutable.Nil..MODULE$);
         } else {
            return scala.collection.immutable.Nil..MODULE$;
         }
      } else {
         return scala.collection.immutable.Nil..MODULE$;
      }
   }

   public Option getLocationsWithLargestOutputs(final int shuffleId, final int reducerId, final int numReducers, final double fractionThreshold) {
      Object var6 = new Object();

      Object var10000;
      try {
         ShuffleStatus shuffleStatus = (ShuffleStatus)this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).orNull(scala..less.colon.less..MODULE$.refl());
         if (shuffleStatus != null) {
            shuffleStatus.withMapStatuses((statuses) -> {
               $anonfun$getLocationsWithLargestOutputs$1(reducerId, fractionThreshold, var6, statuses);
               return BoxedUnit.UNIT;
            });
         } else {
            BoxedUnit var10 = BoxedUnit.UNIT;
         }

         var10000 = .MODULE$;
      } catch (NonLocalReturnControl var9) {
         if (var9.key() != var6) {
            throw var9;
         }

         var10000 = (Option)var9.value();
      }

      return (Option)var10000;
   }

   public Seq getMapLocation(final ShuffleDependency dep, final int startMapIndex, final int endMapIndex) {
      if (!this.shuffleLocalityEnabled()) {
         return scala.collection.immutable.Nil..MODULE$;
      } else {
         ShuffleStatus shuffleStatus = (ShuffleStatus)this.shuffleStatuses().get(BoxesRunTime.boxToInteger(dep.shuffleId())).orNull(scala..less.colon.less..MODULE$.refl());
         return (Seq)(shuffleStatus != null ? (Seq)shuffleStatus.withMapStatuses((statuses) -> {
            if (startMapIndex < endMapIndex && startMapIndex >= 0 && endMapIndex <= statuses.length) {
               MapStatus[] statusesPicked = (MapStatus[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.slice$extension(scala.Predef..MODULE$.refArrayOps(statuses), startMapIndex, endMapIndex)), (x$20) -> BoxesRunTime.boxToBoolean($anonfun$getMapLocation$2(x$20)));
               return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.distinct$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(statusesPicked), (x$21) -> x$21.location().host(), scala.reflect.ClassTag..MODULE$.apply(String.class))))).toImmutableArraySeq();
            } else {
               return scala.collection.immutable.Nil..MODULE$;
            }
         }) : scala.collection.immutable.Nil..MODULE$);
      }
   }

   public Option getMapOutputLocation(final int shuffleId, final long mapId) {
      return this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).flatMap((shuffleStatus) -> shuffleStatus.getMapStatus(mapId).map((x$22) -> x$22.location()));
   }

   public void incrementEpoch() {
      synchronized(this.epochLock()){}

      try {
         this.epoch_$eq(this.epoch() + 1L);
         this.logDebug(() -> "Increasing epoch to " + this.epoch());
      } catch (Throwable var3) {
         throw var3;
      }

   }

   public long getEpoch() {
      synchronized(this.epochLock()){}

      long var3;
      try {
         var3 = this.epoch();
      } catch (Throwable var6) {
         throw var6;
      }

      return var3;
   }

   public Iterator getMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition) {
      MapSizesByExecutorId mapSizesByExecutorId = this.getPushBasedShuffleMapSizesByExecutorId(shuffleId, startMapIndex, endMapIndex, startPartition, endPartition);
      scala.Predef..MODULE$.assert(mapSizesByExecutorId.enableBatchFetch());
      return mapSizesByExecutorId.iter();
   }

   public MapSizesByExecutorId getPushBasedShuffleMapSizesByExecutorId(final int shuffleId, final int startMapIndex, final int endMapIndex, final int startPartition, final int endPartition) {
      this.logDebug(() -> "Fetching outputs for shuffle " + shuffleId);
      Option var7 = this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId));
      if (var7 instanceof Some var8) {
         ShuffleStatus shuffleStatus = (ShuffleStatus)var8.value();
         return (MapSizesByExecutorId)shuffleStatus.withMapStatuses((statuses) -> {
            int actualEndMapIndex = endMapIndex == Integer.MAX_VALUE ? statuses.length : endMapIndex;
            this.logDebug(() -> "Convert map statuses for shuffle " + shuffleId + ", mappers " + startMapIndex + "-" + actualEndMapIndex + ", partitions " + startPartition + "-" + endPartition);
            return MapOutputTracker$.MODULE$.convertMapStatuses(shuffleId, startPartition, endPartition, statuses, startMapIndex, actualEndMapIndex, MapOutputTracker$.MODULE$.convertMapStatuses$default$7());
         });
      } else if (.MODULE$.equals(var7)) {
         return new MapSizesByExecutorId(scala.package..MODULE$.Iterator().empty(), true);
      } else {
         throw new MatchError(var7);
      }
   }

   public Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId) {
      return scala.package..MODULE$.Seq().empty().iterator();
   }

   public Iterator getMapSizesForMergeResult(final int shuffleId, final int partitionId, final RoaringBitmap chunkTracker) {
      return scala.package..MODULE$.Seq().empty().iterator();
   }

   public Seq getShufflePushMergerLocations(final int shuffleId) {
      return (Seq)this.shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).map((x$23) -> x$23.getShufflePushMergerLocations()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty());
   }

   public void stop() {
      this.org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages().offer(this.org$apache$spark$MapOutputTrackerMaster$$PoisonPill());
      this.threadpool().shutdown();

      try {
         this.sendTracker(StopMapOutputTracker$.MODULE$);
      } catch (SparkException var2) {
         this.logError(() -> "Could not tell tracker we are stopping.", var2);
      }

      this.trackerEndpoint_$eq((RpcEndpointRef)null);
      this.shuffleStatuses().clear();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getNumCachedSerializedBroadcast$1(final ShuffleStatus x$11) {
      return x$11.hasCachedSerializedBroadcast();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unregisterAllMapAndMergeOutput$1(final BlockManagerId x) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unregisterAllMapAndMergeOutput$2(final BlockManagerId x) {
      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$registerMergeResults$1(final MapOutputTrackerMaster $this, final int shuffleId$5, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int reduceId = x0$1._1$mcI$sp();
         MergeStatus status = (MergeStatus)x0$1._2();
         $this.registerMergeResult(shuffleId$5, reduceId, status);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$unregisterAllMergeResult$1(final BlockManagerId x) {
      return true;
   }

   // $FF: synthetic method
   public static final void $anonfun$unregisterShuffle$1(final ShuffleStatus shuffleStatus) {
      shuffleStatus.invalidateSerializedMapOutputStatusCache();
      shuffleStatus.invalidateSerializedMergeOutputStatusCache();
   }

   // $FF: synthetic method
   public static final void $anonfun$removeOutputsOnHost$5(final String host$2, final ShuffleStatus x$12) {
      x$12.removeOutputsOnHost(host$2);
   }

   // $FF: synthetic method
   public static final void $anonfun$removeOutputsOnExecutor$4(final String execId$2, final ShuffleStatus x$13) {
      x$13.removeOutputsOnExecutor(execId$2);
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumAvailableOutputs$1(final ShuffleStatus x$14) {
      return x$14.numAvailableMapOutputs();
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumAvailableMergeResults$1(final ShuffleStatus x$15) {
      return x$15.numAvailableMergeResults();
   }

   // $FF: synthetic method
   public static final Range $anonfun$rangeGrouped$1(final int size$1, final int step$1, final int i) {
      return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(i), i + size$1 * step$1, step$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getStatistics$2(final MapStatus x$17) {
      return x$17 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$getStatistics$3(final long[] totalSizes$1, final MapStatus s) {
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.longArrayOps(totalSizes$1)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> totalSizes$1[i] += s.getSizeForBlock(i));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getStatistics$7(final MapStatus x$18) {
      return x$18 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$getStatistics$8(final Seq reduceIds$1, final long[] totalSizes$1, final MapStatus s) {
      reduceIds$1.foreach((JFunction1.mcVI.sp)(i) -> totalSizes$1[i] += s.getSizeForBlock(i));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getLocationsWithLargestOutputs$3(final LongRef totalOutputSize$1, final double fractionThreshold$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         long size = x0$1._2$mcJ$sp();
         return (double)size / (double)totalOutputSize$1.elem >= fractionThreshold$1;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getLocationsWithLargestOutputs$1(final int reducerId$1, final double fractionThreshold$1, final Object nonLocalReturnKey1$1, final MapStatus[] statuses) {
      if (scala.collection.ArrayOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.refArrayOps(statuses))) {
         HashMap locs = new HashMap();
         LongRef totalOutputSize = LongRef.create(0L);

         for(int mapIdx = 0; mapIdx < statuses.length; ++mapIdx) {
            MapStatus status = statuses[mapIdx];
            if (status != null) {
               long blockSize = status.getSizeForBlock(reducerId$1);
               if (blockSize > 0L) {
                  locs.update(status.location(), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(locs.getOrElse(status.location(), (JFunction0.mcJ.sp)() -> 0L)) + blockSize));
                  totalOutputSize.elem += blockSize;
               }
            }
         }

         HashMap topLocs = (HashMap)locs.filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getLocationsWithLargestOutputs$3(totalOutputSize, fractionThreshold$1, x0$1)));
         if (topLocs.nonEmpty()) {
            throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Some(topLocs.keys().toArray(scala.reflect.ClassTag..MODULE$.apply(BlockManagerId.class))));
         }
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getMapLocation$2(final MapStatus x$20) {
      return x$20 != null;
   }

   public MapOutputTrackerMaster(final SparkConf conf, final BroadcastManager broadcastManager, final boolean isLocal) {
      super(conf);
      this.org$apache$spark$MapOutputTrackerMaster$$conf = conf;
      this.broadcastManager = broadcastManager;
      this.isLocal = isLocal;
      this.org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast = (int)BoxesRunTime.unboxToLong(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST()));
      this.shuffleLocalityEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_REDUCE_LOCALITY_ENABLE()));
      this.shuffleMigrationEnabled = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.DECOMMISSION_ENABLED())) && BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_ENABLED())) && BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.STORAGE_DECOMMISSION_SHUFFLE_BLOCKS_ENABLED()));
      this.SHUFFLE_PREF_MAP_THRESHOLD = 1000;
      this.SHUFFLE_PREF_REDUCE_THRESHOLD = 1000;
      this.REDUCER_PREF_LOCS_FRACTION = 0.2;
      this.shuffleStatuses = scala.jdk.CollectionConverters..MODULE$.ConcurrentMapHasAsScala(new ConcurrentHashMap()).asScala();
      this.maxRpcMessageSize = RpcUtils$.MODULE$.maxMessageSizeBytes(conf);
      this.org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages = new LinkedBlockingQueue();
      this.pushBasedShuffleEnabled = Utils$.MODULE$.isPushBasedShuffleEnabled(conf, true, Utils$.MODULE$.isPushBasedShuffleEnabled$default$3());
      int numThreads = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAPOUTPUT_DISPATCHER_NUM_THREADS()));
      ThreadPoolExecutor pool = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(numThreads, "map-output-dispatcher");
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numThreads).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> pool.execute(this.new MessageLoop()));
      this.threadpool = pool;
      this.availableProcessors = Runtime.getRuntime().availableProcessors();
      if (this.org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast() > this.maxRpcMessageSize()) {
         MessageWithContext logEntry = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_MAPOUTPUT_MIN_SIZE_FOR_BROADCAST().key())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", " bytes) "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"must be <= spark.rpc.message.maxSize (", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MAX_SIZE..MODULE$, BoxesRunTime.boxToInteger(this.maxRpcMessageSize()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"bytes) to prevent sending an rpc message that is too large."})))).log(scala.collection.immutable.Nil..MODULE$));
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> logEntry));
         throw new IllegalArgumentException(logEntry.message());
      } else {
         this.org$apache$spark$MapOutputTrackerMaster$$PoisonPill = new GetMapOutputMessage(-99, (RpcCallContext)null);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class MessageLoop implements Runnable {
      // $FF: synthetic field
      public final MapOutputTrackerMaster $outer;

      private void handleStatusMessage(final int shuffleId, final RpcCallContext context, final boolean needMergeOutput) {
         String hostPort = context.senderAddress().hostPort();
         ShuffleStatus shuffleStatus = (ShuffleStatus)scala.Option..MODULE$.option2Iterable(this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId))).head();
         this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().logDebug(() -> "Handling request to send " + (needMergeOutput ? "map/merge" : "map") + " output locations for shuffle " + shuffleId + " to " + hostPort);
         if (needMergeOutput) {
            context.reply(shuffleStatus.serializedMapAndMergeStatus(this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().broadcastManager(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().isLocal(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$conf));
         } else {
            context.reply(shuffleStatus.serializedMapStatus(this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().broadcastManager(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().isLocal(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$minSizeForBroadcast(), this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$conf));
         }
      }

      public void run() {
         try {
            while(true) {
               try {
                  label48: {
                     MapOutputTrackerMasterMessage data = (MapOutputTrackerMasterMessage)this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages().take();
                     GetMapOutputMessage var4 = this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$PoisonPill();
                     if (data == null) {
                        if (var4 == null) {
                           break label48;
                        }
                     } else if (data.equals(var4)) {
                        break label48;
                     }

                     if (data instanceof GetMapOutputMessage var6) {
                        int shuffleId = var6.shuffleId();
                        RpcCallContext context = var6.context();
                        this.handleStatusMessage(shuffleId, context, false);
                        BoxedUnit var21 = BoxedUnit.UNIT;
                     } else if (data instanceof GetMapAndMergeOutputMessage var9) {
                        int shuffleId = var9.shuffleId();
                        RpcCallContext context = var9.context();
                        this.handleStatusMessage(shuffleId, context, true);
                        BoxedUnit var22 = BoxedUnit.UNIT;
                     } else {
                        if (!(data instanceof GetShufflePushMergersMessage)) {
                           throw new MatchError(data);
                        }

                        GetShufflePushMergersMessage var12 = (GetShufflePushMergersMessage)data;
                        int shuffleId = var12.shuffleId();
                        RpcCallContext context = var12.context();
                        this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().logDebug(() -> "Handling request to send shuffle push merger locations for shuffle " + shuffleId + " to " + context.senderAddress().hostPort());
                        context.reply(this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().shuffleStatuses().get(BoxesRunTime.boxToInteger(shuffleId)).map((x$10) -> x$10.getShufflePushMergerLocations()).getOrElse(() -> (Seq)scala.package..MODULE$.Seq().empty()));
                        BoxedUnit var23 = BoxedUnit.UNIT;
                     }
                     continue;
                  }

                  this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$mapOutputTrackerMasterMessages().offer(this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().org$apache$spark$MapOutputTrackerMaster$$PoisonPill());
                  return;
               } catch (Throwable var19) {
                  if (var19 == null || !scala.util.control.NonFatal..MODULE$.apply(var19)) {
                     throw var19;
                  }

                  this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.ERROR..MODULE$, var19.getMessage())})))), var19);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }
            }
         } catch (InterruptedException var20) {
         }
      }

      // $FF: synthetic method
      public MapOutputTrackerMaster org$apache$spark$MapOutputTrackerMaster$MessageLoop$$$outer() {
         return this.$outer;
      }

      public MessageLoop() {
         if (MapOutputTrackerMaster.this == null) {
            throw null;
         } else {
            this.$outer = MapOutputTrackerMaster.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
