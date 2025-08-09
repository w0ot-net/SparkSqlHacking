package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.Success$;
import org.apache.spark.TaskCommitDenied;
import org.apache.spark.TaskEndReason;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.rpc.RpcAddress;
import org.apache.spark.rpc.RpcCallContext;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.rpc.RpcEnv;
import org.apache.spark.util.RpcUtils$;
import org.apache.spark.util.ThreadUtils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.concurrent.Awaitable;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Null;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t=h!\u0002'N\u0001=+\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011\u00023\t\u0011!\u0004!\u0011!Q\u0001\n%DQ\u0001\u001c\u0001\u0005\u00025DqA\u001d\u0001A\u0002\u0013\u00051\u000fC\u0004~\u0001\u0001\u0007I\u0011\u0001@\t\u000f\u0005%\u0001\u0001)Q\u0005i\u001a1\u00111\u0002\u0001E\u0003\u001bA!\"!\f\b\u0005+\u0007I\u0011AA\u0018\u0011)\t9d\u0002B\tB\u0003%\u0011\u0011\u0007\u0005\u000b\u0003s9!Q3A\u0005\u0002\u0005=\u0002BCA\u001e\u000f\tE\t\u0015!\u0003\u00022!1An\u0002C\u0001\u0003{A\u0011\"a\u0012\b\u0003\u0003%\t!!\u0013\t\u0013\u0005=s!%A\u0005\u0002\u0005E\u0003\"CA4\u000fE\u0005I\u0011AA)\u0011%\tIgBA\u0001\n\u0003\nY\u0007C\u0005\u0002~\u001d\t\t\u0011\"\u0001\u00020!I\u0011qP\u0004\u0002\u0002\u0013\u0005\u0011\u0011\u0011\u0005\n\u0003\u0017;\u0011\u0011!C!\u0003\u001bC\u0011\"a'\b\u0003\u0003%\t!!(\t\u0013\u0005\u0005v!!A\u0005B\u0005\r\u0006\"CAT\u000f\u0005\u0005I\u0011IAU\u0011%\tYkBA\u0001\n\u0003\ni\u000bC\u0005\u00020\u001e\t\t\u0011\"\u0011\u00022\u001eI\u0011Q\u0017\u0001\u0002\u0002#%\u0011q\u0017\u0004\n\u0003\u0017\u0001\u0011\u0011!E\u0005\u0003sCa\u0001\u001c\u000e\u0005\u0002\u0005E\u0007\"CAV5\u0005\u0005IQIAW\u0011%\t\u0019NGA\u0001\n\u0003\u000b)\u000eC\u0005\u0002\\j\t\t\u0011\"!\u0002^\u001a1\u00111\u001e\u0001E\u0003[D!\"a< \u0005+\u0007I\u0011AA\u0018\u0011)\t\tp\bB\tB\u0003%\u0011\u0011\u0007\u0005\u0007Y~!\t!a=\t\u0013\u0005exD1A\u0005\u0002\u0005m\b\u0002\u0003B\u0002?\u0001\u0006I!!@\t\u0013\t\u0015qD1A\u0005\u0002\t\u001d\u0001\u0002\u0003B\u000e?\u0001\u0006IA!\u0003\t\u0013\u0005\u001ds$!A\u0005\u0002\tu\u0001\"CA(?E\u0005I\u0011AA)\u0011%\tIgHA\u0001\n\u0003\nY\u0007C\u0005\u0002~}\t\t\u0011\"\u0001\u00020!I\u0011qP\u0010\u0002\u0002\u0013\u0005!\u0011\u0005\u0005\n\u0003\u0017{\u0012\u0011!C!\u0003\u001bC\u0011\"a' \u0003\u0003%\tA!\n\t\u0013\u0005\u0005v$!A\u0005B\t%\u0002\"CAT?\u0005\u0005I\u0011IAU\u0011%\tYkHA\u0001\n\u0003\ni\u000bC\u0005\u00020~\t\t\u0011\"\u0011\u0003.\u001dI!\u0011\u0007\u0001\u0002\u0002#%!1\u0007\u0004\n\u0003W\u0004\u0011\u0011!E\u0005\u0005kAa\u0001\\\u001a\u0005\u0002\tu\u0002\"CAVg\u0005\u0005IQIAW\u0011%\t\u0019nMA\u0001\n\u0003\u0013y\u0004C\u0005\u0002\\N\n\t\u0011\"!\u0003D!I!\u0011\n\u0001C\u0002\u0013%!1\n\u0005\t\u0005\u001f\u0002\u0001\u0015!\u0003\u0003N!9!\u0011\u000b\u0001\u0005\u0002\tM\u0003b\u0002B+\u0001\u0011\u0005!q\u000b\u0005\t\u0005O\u0002A\u0011A'\u0003j!A!\u0011\u000f\u0001\u0005\u00025\u0013\u0019\b\u0003\u0005\u0003x\u0001!\t!\u0014B=\u0011\u001d\u0011i\t\u0001C\u0001\u0005\u001fC\u0001B!%\u0001\t\u0003i%1\u0013\u0005\b\u0005;\u0003A\u0011\u0002BP\u000f!\u0011i+\u0014E\u0001\u001f\n=fa\u0002'N\u0011\u0003y%\u0011\u0017\u0005\u0007Y\u000e#\tAa-\u0007\u000f\tU6\tA(\u00038\"Q!qX#\u0003\u0006\u0004%\tE!1\t\u0015\t%WI!A!\u0002\u0013\u0011\u0019\rC\u0005\u0003L\u0016\u0013\t\u0011)A\u0005]\"1A.\u0012C\u0001\u0005\u001bDqAa6F\t\u0003\u0012I\u000eC\u0004\u0003b\u0016#\tEa9\u0003/=+H\u000f];u\u0007>lW.\u001b;D_>\u0014H-\u001b8bi>\u0014(B\u0001(P\u0003%\u00198\r[3ek2,'O\u0003\u0002Q#\u0006)1\u000f]1sW*\u0011!kU\u0001\u0007CB\f7\r[3\u000b\u0003Q\u000b1a\u001c:h'\r\u0001a\u000b\u0018\t\u0003/jk\u0011\u0001\u0017\u0006\u00023\u0006)1oY1mC&\u00111\f\u0017\u0002\u0007\u0003:L(+\u001a4\u0011\u0005u\u0003W\"\u00010\u000b\u0005}{\u0015\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0005t&a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005\u00154W\"A(\n\u0005\u001d|%!C*qCJ\\7i\u001c8g\u0003!I7\u000f\u0012:jm\u0016\u0014\bCA,k\u0013\tY\u0007LA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\rq\u0007/\u001d\t\u0003_\u0002i\u0011!\u0014\u0005\u0006E\u000e\u0001\r\u0001\u001a\u0005\u0006Q\u000e\u0001\r![\u0001\u000fG>|'\u000fZ5oCR|'OU3g+\u0005!\bcA,vo&\u0011a\u000f\u0017\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005a\\X\"A=\u000b\u0005i|\u0015a\u0001:qG&\u0011A0\u001f\u0002\u000f%B\u001cWI\u001c3q_&tGOU3g\u0003I\u0019wn\u001c:eS:\fGo\u001c:SK\u001a|F%Z9\u0015\u0007}\f)\u0001E\u0002X\u0003\u0003I1!a\u0001Y\u0005\u0011)f.\u001b;\t\u0011\u0005\u001dQ!!AA\u0002Q\f1\u0001\u001f\u00132\u0003=\u0019wn\u001c:eS:\fGo\u001c:SK\u001a\u0004#A\u0004+bg.LE-\u001a8uS\u001aLWM]\n\u0007\u000fY\u000by!!\u0006\u0011\u0007]\u000b\t\"C\u0002\u0002\u0014a\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\u0018\u0005\u001db\u0002BA\r\u0003GqA!a\u0007\u0002\"5\u0011\u0011Q\u0004\u0006\u0004\u0003?\u0019\u0017A\u0002\u001fs_>$h(C\u0001Z\u0013\r\t)\u0003W\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tI#a\u000b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0007\u0005\u0015\u0002,\u0001\u0007ti\u0006<W-\u0011;uK6\u0004H/\u0006\u0002\u00022A\u0019q+a\r\n\u0007\u0005U\u0002LA\u0002J]R\fQb\u001d;bO\u0016\fE\u000f^3naR\u0004\u0013a\u0003;bg.\fE\u000f^3naR\fA\u0002^1tW\u0006#H/Z7qi\u0002\"b!a\u0010\u0002D\u0005\u0015\u0003cAA!\u000f5\t\u0001\u0001C\u0004\u0002.1\u0001\r!!\r\t\u000f\u0005eB\u00021\u0001\u00022\u0005!1m\u001c9z)\u0019\ty$a\u0013\u0002N!I\u0011QF\u0007\u0011\u0002\u0003\u0007\u0011\u0011\u0007\u0005\n\u0003si\u0001\u0013!a\u0001\u0003c\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002T)\"\u0011\u0011GA+W\t\t9\u0006\u0005\u0003\u0002Z\u0005\rTBAA.\u0015\u0011\ti&a\u0018\u0002\u0013Ut7\r[3dW\u0016$'bAA11\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005\u0015\u00141\f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0004\u0003BA8\u0003sj!!!\u001d\u000b\t\u0005M\u0014QO\u0001\u0005Y\u0006twM\u0003\u0002\u0002x\u0005!!.\u0019<b\u0013\u0011\tY(!\u001d\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a!\u0002\nB\u0019q+!\"\n\u0007\u0005\u001d\u0005LA\u0002B]fD\u0011\"a\u0002\u0013\u0003\u0003\u0005\r!!\r\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a$\u0011\r\u0005E\u0015qSAB\u001b\t\t\u0019JC\u0002\u0002\u0016b\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\tI*a%\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004S\u0006}\u0005\"CA\u0004)\u0005\u0005\t\u0019AAB\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u00055\u0014Q\u0015\u0005\n\u0003\u000f)\u0012\u0011!a\u0001\u0003c\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003c\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003[\na!Z9vC2\u001cHcA5\u00024\"I\u0011q\u0001\r\u0002\u0002\u0003\u0007\u00111Q\u0001\u000f)\u0006\u001c8.\u00133f]RLg-[3s!\r\t\tEG\n\u00065\u0005m\u0016q\u0019\t\u000b\u0003{\u000b\u0019-!\r\u00022\u0005}RBAA`\u0015\r\t\t\rW\u0001\beVtG/[7f\u0013\u0011\t)-a0\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0005\u0003\u0002J\u0006=WBAAf\u0015\u0011\ti-!\u001e\u0002\u0005%|\u0017\u0002BA\u0015\u0003\u0017$\"!a.\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\r\u0005}\u0012q[Am\u0011\u001d\ti#\ba\u0001\u0003cAq!!\u000f\u001e\u0001\u0004\t\t$A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005}\u0017q\u001d\t\u0005/V\f\t\u000fE\u0004X\u0003G\f\t$!\r\n\u0007\u0005\u0015\bL\u0001\u0004UkBdWM\r\u0005\n\u0003St\u0012\u0011!a\u0001\u0003\u007f\t1\u0001\u001f\u00131\u0005)\u0019F/Y4f'R\fG/Z\n\u0007?Y\u000by!!\u0006\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u00039qW/\u001c)beRLG/[8og\u0002\"B!!>\u0002xB\u0019\u0011\u0011I\u0010\t\u000f\u0005=(\u00051\u0001\u00022\u0005!\u0012-\u001e;i_JL'0\u001a3D_6l\u0017\u000e\u001e;feN,\"!!@\u0011\u000b]\u000by0a\u0010\n\u0007\t\u0005\u0001LA\u0003BeJ\f\u00170A\u000bbkRDwN]5{K\u0012\u001cu.\\7jiR,'o\u001d\u0011\u0002\u0011\u0019\f\u0017\u000e\\;sKN,\"A!\u0003\u0011\u0011\t-!\u0011CA\u0019\u0005+i!A!\u0004\u000b\t\t=\u00111S\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0011\u0019B!\u0004\u0003\u00075\u000b\u0007\u000f\u0005\u0004\u0003\f\t]\u0011qH\u0005\u0005\u00053\u0011iAA\u0002TKR\f\u0011BZ1jYV\u0014Xm\u001d\u0011\u0015\t\u0005U(q\u0004\u0005\n\u0003_<\u0003\u0013!a\u0001\u0003c!B!a!\u0003$!I\u0011qA\u0016\u0002\u0002\u0003\u0007\u0011\u0011\u0007\u000b\u0004S\n\u001d\u0002\"CA\u0004[\u0005\u0005\t\u0019AAB)\u0011\tiGa\u000b\t\u0013\u0005\u001da&!AA\u0002\u0005EBcA5\u00030!I\u0011qA\u0019\u0002\u0002\u0003\u0007\u00111Q\u0001\u000b'R\fw-Z*uCR,\u0007cAA!gM)1Ga\u000e\u0002HBA\u0011Q\u0018B\u001d\u0003c\t)0\u0003\u0003\u0003<\u0005}&!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocQ\u0011!1\u0007\u000b\u0005\u0003k\u0014\t\u0005C\u0004\u0002pZ\u0002\r!!\r\u0015\t\t\u0015#q\t\t\u0005/V\f\t\u0004C\u0005\u0002j^\n\t\u00111\u0001\u0002v\u0006Y1\u000f^1hKN#\u0018\r^3t+\t\u0011i\u0005\u0005\u0005\u0003\f\tE\u0011\u0011GA{\u00031\u0019H/Y4f'R\fG/Z:!\u0003\u001dI7/R7qif,\u0012![\u0001\nG\u0006t7i\\7nSR$\u0012\"\u001bB-\u0005;\u0012yFa\u0019\t\u000f\tm3\b1\u0001\u00022\u0005)1\u000f^1hK\"9\u0011QF\u001eA\u0002\u0005E\u0002b\u0002B1w\u0001\u0007\u0011\u0011G\u0001\na\u0006\u0014H/\u001b;j_:DqA!\u001a<\u0001\u0004\t\t$A\u0007biR,W\u000e\u001d;Ok6\u0014WM]\u0001\u000bgR\fw-Z*uCJ$H#B@\u0003l\t5\u0004b\u0002B.y\u0001\u0007\u0011\u0011\u0007\u0005\b\u0005_b\u0004\u0019AA\u0019\u00039i\u0017\r\u001f)beRLG/[8o\u0013\u0012\f\u0001b\u001d;bO\u0016,e\u000e\u001a\u000b\u0004\u007f\nU\u0004b\u0002B.{\u0001\u0007\u0011\u0011G\u0001\u000ei\u0006\u001c8nQ8na2,G/\u001a3\u0015\u0017}\u0014YH! \u0003\u0000\t\u0005%1\u0011\u0005\b\u00057r\u0004\u0019AA\u0019\u0011\u001d\tiC\u0010a\u0001\u0003cAqA!\u0019?\u0001\u0004\t\t\u0004C\u0004\u0003fy\u0002\r!!\r\t\u000f\t\u0015e\b1\u0001\u0003\b\u00061!/Z1t_:\u00042!\u001aBE\u0013\r\u0011Yi\u0014\u0002\u000e)\u0006\u001c8.\u00128e%\u0016\f7o\u001c8\u0002\tM$x\u000e\u001d\u000b\u0002\u007f\u0006Y\u0002.\u00198eY\u0016\f5o\u001b)fe6L7o]5p]R{7i\\7nSR$\u0012\"\u001bBK\u0005/\u0013IJa'\t\u000f\tm\u0003\t1\u0001\u00022!9\u0011Q\u0006!A\u0002\u0005E\u0002b\u0002B1\u0001\u0002\u0007\u0011\u0011\u0007\u0005\b\u0005K\u0002\u0005\u0019AA\u0019\u00035\tG\u000f^3naR4\u0015-\u001b7fIRI\u0011N!)\u0003&\n\u001d&\u0011\u0016\u0005\b\u0005G\u000b\u0005\u0019AA{\u0003)\u0019H/Y4f'R\fG/\u001a\u0005\b\u0003[\t\u0005\u0019AA\u0019\u0011\u001d\u0011\t'\u0011a\u0001\u0003cAqAa+B\u0001\u0004\t\t$A\u0004biR,W\u000e\u001d;\u0002/=+H\u000f];u\u0007>lW.\u001b;D_>\u0014H-\u001b8bi>\u0014\bCA8D'\t\u0019e\u000b\u0006\u0002\u00030\nyr*\u001e;qkR\u001cu.\\7ji\u000e{wN\u001d3j]\u0006$xN]#oIB|\u0017N\u001c;\u0014\u000b\u00153&\u0011\u0018/\u0011\u0007a\u0014Y,C\u0002\u0003>f\u00141B\u00159d\u000b:$\u0007o\\5oi\u00061!\u000f]2F]Z,\"Aa1\u0011\u0007a\u0014)-C\u0002\u0003Hf\u0014aA\u00159d\u000b:4\u0018a\u0002:qG\u0016sg\u000fI\u0001\u0018_V$\b/\u001e;D_6l\u0017\u000e^\"p_J$\u0017N\\1u_J$bAa4\u0003T\nU\u0007c\u0001Bi\u000b6\t1\tC\u0004\u0003@&\u0003\rAa1\t\r\t-\u0017\n1\u0001o\u0003\u001d\u0011XmY3jm\u0016,\"Aa7\u0011\r]\u0013i.a!\u0000\u0013\r\u0011y\u000e\u0017\u0002\u0010!\u0006\u0014H/[1m\rVt7\r^5p]\u0006y!/Z2fSZ,\u0017I\u001c3SKBd\u0017\u0010\u0006\u0003\u0003\\\n\u0015\bb\u0002Bt\u0017\u0002\u0007!\u0011^\u0001\bG>tG/\u001a=u!\rA(1^\u0005\u0004\u0005[L(A\u0004*qG\u000e\u000bG\u000e\\\"p]R,\u0007\u0010\u001e"
)
public class OutputCommitCoordinator implements Logging {
   private volatile TaskIdentifier$ TaskIdentifier$module;
   private volatile StageState$ StageState$module;
   private final SparkConf conf;
   private final boolean isDriver;
   private Option coordinatorRef;
   private final Map stageStates;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private TaskIdentifier$ TaskIdentifier() {
      if (this.TaskIdentifier$module == null) {
         this.TaskIdentifier$lzycompute$1();
      }

      return this.TaskIdentifier$module;
   }

   private StageState$ StageState() {
      if (this.StageState$module == null) {
         this.StageState$lzycompute$1();
      }

      return this.StageState$module;
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Option coordinatorRef() {
      return this.coordinatorRef;
   }

   public void coordinatorRef_$eq(final Option x$1) {
      this.coordinatorRef = x$1;
   }

   private Map stageStates() {
      return this.stageStates;
   }

   public boolean isEmpty() {
      return this.stageStates().isEmpty();
   }

   public boolean canCommit(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      AskPermissionToCommitOutput msg = new AskPermissionToCommitOutput(stage, stageAttempt, partition, attemptNumber);
      Option var7 = this.coordinatorRef();
      if (var7 instanceof Some var8) {
         RpcEndpointRef endpointRef = (RpcEndpointRef)var8.value();
         return BoxesRunTime.unboxToBoolean(ThreadUtils$.MODULE$.awaitResult((Awaitable)endpointRef.ask(msg, .MODULE$.Boolean()), RpcUtils$.MODULE$.askRpcTimeout(this.conf).duration()));
      } else if (scala.None..MODULE$.equals(var7)) {
         this.logError((Function0)(() -> "canCommit called after coordinator was stopped (is SparkEnv shutdown in progress)?"));
         return false;
      } else {
         throw new MatchError(var7);
      }
   }

   public synchronized void stageStart(final int stage, final int maxPartitionId) {
      Option var4 = this.stageStates().get(BoxesRunTime.boxToInteger(stage));
      if (var4 instanceof Some var5) {
         StageState state = (StageState)var5.value();
         scala.Predef..MODULE$.require(state.authorizedCommitters().length == maxPartitionId + 1);
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reusing state from previous attempt of stage ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage))})))));
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         this.stageStates().update(BoxesRunTime.boxToInteger(stage), new StageState(maxPartitionId + 1));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void stageEnd(final int stage) {
      synchronized(this){}

      try {
         this.stageStates().remove(BoxesRunTime.boxToInteger(stage));
      } catch (Throwable var4) {
         throw var4;
      }

   }

   public synchronized void taskCompleted(final int stage, final int stageAttempt, final int partition, final int attemptNumber, final TaskEndReason reason) {
      Object var7 = new Object();

      try {
         StageState stageState = (StageState)this.stageStates().getOrElse(BoxesRunTime.boxToInteger(stage), () -> {
            this.logDebug((Function0)(() -> "Ignoring task completion for completed stage"));
            throw new NonLocalReturnControl.mcV.sp(var7, BoxedUnit.UNIT);
         });
         if (Success$.MODULE$.equals(reason)) {
            BoxedUnit var17 = BoxedUnit.UNIT;
         } else if (reason instanceof TaskCommitDenied) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task was denied committing, stage: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(stageAttempt))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"partition: ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_ID..MODULE$, BoxesRunTime.boxToInteger(partition))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"attempt: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(attemptNumber))}))))));
            BoxedUnit var16 = BoxedUnit.UNIT;
         } else {
            label51: {
               TaskIdentifier taskId = new TaskIdentifier(stageAttempt, attemptNumber);
               ((Growable)stageState.failures().getOrElseUpdate(BoxesRunTime.boxToInteger(partition), () -> (Set)scala.collection.mutable.Set..MODULE$.apply(scala.collection.immutable.Nil..MODULE$))).$plus$eq(taskId);
               TaskIdentifier var10000 = stageState.authorizedCommitters()[partition];
               if (var10000 == null) {
                  if (taskId == null) {
                     break label51;
                  }
               } else if (var10000.equals(taskId)) {
                  break label51;
               }

               BoxedUnit var14 = BoxedUnit.UNIT;
               return;
            }

            this.logDebug((Function0)(() -> "Authorized committer (attemptNumber=" + attemptNumber + ", stage=" + stage + ", partition=" + partition + ") failed; clearing lock"));
            stageState.authorizedCommitters()[partition] = null;
            BoxedUnit var15 = BoxedUnit.UNIT;
         }
      } catch (NonLocalReturnControl var13) {
         if (var13.key() != var7) {
            throw var13;
         }

         var13.value$mcV$sp();
      }

   }

   public synchronized void stop() {
      if (this.isDriver) {
         this.coordinatorRef().foreach((x$1) -> {
            $anonfun$stop$1(x$1);
            return BoxedUnit.UNIT;
         });
         this.coordinatorRef_$eq(scala.None..MODULE$);
         this.stageStates().clear();
      }
   }

   public synchronized boolean handleAskPermissionToCommit(final int stage, final int stageAttempt, final int partition, final int attemptNumber) {
      boolean var6 = false;
      Some var7 = null;
      Option var8 = this.stageStates().get(BoxesRunTime.boxToInteger(stage));
      if (var8 instanceof Some) {
         var6 = true;
         var7 = (Some)var8;
         StageState state = (StageState)var7.value();
         if (this.attemptFailed(state, stageAttempt, partition, attemptNumber)) {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Commit denied for stage=", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ID..MODULE$, BoxesRunTime.boxToInteger(stage))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", partition="})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.STAGE_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToInteger(stageAttempt))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": task attempt "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PARTITION_ID..MODULE$, BoxesRunTime.boxToInteger(partition))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " already marked as failed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ATTEMPT..MODULE$, BoxesRunTime.boxToInteger(attemptNumber))}))))));
            return false;
         }
      }

      if (var6) {
         StageState state = (StageState)var7.value();
         TaskIdentifier existing = state.authorizedCommitters()[partition];
         if (existing == null) {
            this.logDebug((Function0)(() -> "Commit allowed for stage=" + stage + "." + stageAttempt + ", partition=" + partition + ", task attempt " + attemptNumber));
            state.authorizedCommitters()[partition] = new TaskIdentifier(stageAttempt, attemptNumber);
            return true;
         } else {
            this.logDebug((Function0)(() -> "Commit denied for stage=" + stage + "." + stageAttempt + ", partition=" + partition + ": already committed by " + existing));
            return false;
         }
      } else if (scala.None..MODULE$.equals(var8)) {
         this.logDebug((Function0)(() -> "Commit denied for stage=" + stage + "." + stageAttempt + ", partition=" + partition + ": stage already marked as completed."));
         return false;
      } else {
         throw new MatchError(var8);
      }
   }

   private synchronized boolean attemptFailed(final StageState stageState, final int stageAttempt, final int partition, final int attempt) {
      TaskIdentifier failInfo = new TaskIdentifier(stageAttempt, attempt);
      return stageState.failures().get(BoxesRunTime.boxToInteger(partition)).exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$attemptFailed$1(failInfo, x$2)));
   }

   private final void TaskIdentifier$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TaskIdentifier$module == null) {
            this.TaskIdentifier$module = new TaskIdentifier$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void StageState$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StageState$module == null) {
            this.StageState$module = new StageState$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$stop$1(final RpcEndpointRef x$1) {
      x$1.send(StopCoordinator$.MODULE$);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$attemptFailed$1(final TaskIdentifier failInfo$1, final Set x$2) {
      return x$2.contains(failInfo$1);
   }

   public OutputCommitCoordinator(final SparkConf conf, final boolean isDriver) {
      this.conf = conf;
      this.isDriver = isDriver;
      Logging.$init$(this);
      this.coordinatorRef = scala.None..MODULE$;
      this.stageStates = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class TaskIdentifier implements Product, Serializable {
      private final int stageAttempt;
      private final int taskAttempt;
      // $FF: synthetic field
      public final OutputCommitCoordinator $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int stageAttempt() {
         return this.stageAttempt;
      }

      public int taskAttempt() {
         return this.taskAttempt;
      }

      public TaskIdentifier copy(final int stageAttempt, final int taskAttempt) {
         return this.org$apache$spark$scheduler$OutputCommitCoordinator$TaskIdentifier$$$outer().new TaskIdentifier(stageAttempt, taskAttempt);
      }

      public int copy$default$1() {
         return this.stageAttempt();
      }

      public int copy$default$2() {
         return this.taskAttempt();
      }

      public String productPrefix() {
         return "TaskIdentifier";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.stageAttempt());
            }
            case 1 -> {
               return BoxesRunTime.boxToInteger(this.taskAttempt());
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
         return x$1 instanceof TaskIdentifier;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "stageAttempt";
            }
            case 1 -> {
               return "taskAttempt";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.stageAttempt());
         var1 = Statics.mix(var1, this.taskAttempt());
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label43: {
               if (x$1 instanceof TaskIdentifier && ((TaskIdentifier)x$1).org$apache$spark$scheduler$OutputCommitCoordinator$TaskIdentifier$$$outer() == this.org$apache$spark$scheduler$OutputCommitCoordinator$TaskIdentifier$$$outer()) {
                  TaskIdentifier var4 = (TaskIdentifier)x$1;
                  if (this.stageAttempt() == var4.stageAttempt() && this.taskAttempt() == var4.taskAttempt() && var4.canEqual(this)) {
                     break label43;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public OutputCommitCoordinator org$apache$spark$scheduler$OutputCommitCoordinator$TaskIdentifier$$$outer() {
         return this.$outer;
      }

      public TaskIdentifier(final int stageAttempt, final int taskAttempt) {
         this.stageAttempt = stageAttempt;
         this.taskAttempt = taskAttempt;
         if (OutputCommitCoordinator.this == null) {
            throw null;
         } else {
            this.$outer = OutputCommitCoordinator.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class TaskIdentifier$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final OutputCommitCoordinator $outer;

      public final String toString() {
         return "TaskIdentifier";
      }

      public TaskIdentifier apply(final int stageAttempt, final int taskAttempt) {
         return this.$outer.new TaskIdentifier(stageAttempt, taskAttempt);
      }

      public Option unapply(final TaskIdentifier x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2.mcII.sp(x$0.stageAttempt(), x$0.taskAttempt())));
      }

      public TaskIdentifier$() {
         if (OutputCommitCoordinator.this == null) {
            throw null;
         } else {
            this.$outer = OutputCommitCoordinator.this;
            super();
         }
      }
   }

   private class StageState implements Product, Serializable {
      private final int numPartitions;
      private final TaskIdentifier[] authorizedCommitters;
      private final Map failures;
      // $FF: synthetic field
      public final OutputCommitCoordinator $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int numPartitions() {
         return this.numPartitions;
      }

      public TaskIdentifier[] authorizedCommitters() {
         return this.authorizedCommitters;
      }

      public Map failures() {
         return this.failures;
      }

      public StageState copy(final int numPartitions) {
         return this.org$apache$spark$scheduler$OutputCommitCoordinator$StageState$$$outer().new StageState(numPartitions);
      }

      public int copy$default$1() {
         return this.numPartitions();
      }

      public String productPrefix() {
         return "StageState";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToInteger(this.numPartitions());
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
         return x$1 instanceof StageState;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "numPartitions";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.numPartitions());
         return Statics.finalizeHash(var1, 1);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label41: {
               if (x$1 instanceof StageState && ((StageState)x$1).org$apache$spark$scheduler$OutputCommitCoordinator$StageState$$$outer() == this.org$apache$spark$scheduler$OutputCommitCoordinator$StageState$$$outer()) {
                  StageState var4 = (StageState)x$1;
                  if (this.numPartitions() == var4.numPartitions() && var4.canEqual(this)) {
                     break label41;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      // $FF: synthetic method
      public OutputCommitCoordinator org$apache$spark$scheduler$OutputCommitCoordinator$StageState$$$outer() {
         return this.$outer;
      }

      public StageState(final int numPartitions) {
         this.numPartitions = numPartitions;
         if (OutputCommitCoordinator.this == null) {
            throw null;
         } else {
            this.$outer = OutputCommitCoordinator.this;
            super();
            Product.$init$(this);
            this.authorizedCommitters = (TaskIdentifier[])scala.Array..MODULE$.fill(numPartitions, () -> null, .MODULE$.apply(TaskIdentifier.class));
            this.failures = (Map)scala.collection.mutable.Map..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class StageState$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final OutputCommitCoordinator $outer;

      public final String toString() {
         return "StageState";
      }

      public StageState apply(final int numPartitions) {
         return this.$outer.new StageState(numPartitions);
      }

      public Option unapply(final StageState x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(BoxesRunTime.boxToInteger(x$0.numPartitions())));
      }

      public StageState$() {
         if (OutputCommitCoordinator.this == null) {
            throw null;
         } else {
            this.$outer = OutputCommitCoordinator.this;
            super();
         }
      }
   }

   public static class OutputCommitCoordinatorEndpoint implements RpcEndpoint, Logging {
      private final RpcEnv rpcEnv;
      public final OutputCommitCoordinator org$apache$spark$scheduler$OutputCommitCoordinator$OutputCommitCoordinatorEndpoint$$outputCommitCoordinator;
      private transient Logger org$apache$spark$internal$Logging$$log_;

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

      public void onDisconnected(final RpcAddress remoteAddress) {
         RpcEndpoint.onDisconnected$(this, remoteAddress);
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

      public PartialFunction receive() {
         return new Serializable() {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final OutputCommitCoordinatorEndpoint $outer;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (StopCoordinator$.MODULE$.equals(x1)) {
                  this.$outer.logInfo((Function0)(() -> "OutputCommitCoordinator stopped!"));
                  this.$outer.stop();
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               return StopCoordinator$.MODULE$.equals(x1);
            }

            public {
               if (OutputCommitCoordinatorEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = OutputCommitCoordinatorEndpoint.this;
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
      }

      public PartialFunction receiveAndReply(final RpcCallContext context) {
         return new Serializable(context) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final OutputCommitCoordinatorEndpoint $outer;
            private final RpcCallContext context$1;

            public final Object applyOrElse(final Object x1, final Function1 default) {
               if (x1 instanceof AskPermissionToCommitOutput var5) {
                  int stage = var5.stage();
                  int stageAttempt = var5.stageAttempt();
                  int partition = var5.partition();
                  int attemptNumber = var5.attemptNumber();
                  this.context$1.reply(BoxesRunTime.boxToBoolean(this.$outer.org$apache$spark$scheduler$OutputCommitCoordinator$OutputCommitCoordinatorEndpoint$$outputCommitCoordinator.handleAskPermissionToCommit(stage, stageAttempt, partition, attemptNumber)));
                  return BoxedUnit.UNIT;
               } else {
                  return default.apply(x1);
               }
            }

            public final boolean isDefinedAt(final Object x1) {
               return x1 instanceof AskPermissionToCommitOutput;
            }

            public {
               if (OutputCommitCoordinatorEndpoint.this == null) {
                  throw null;
               } else {
                  this.$outer = OutputCommitCoordinatorEndpoint.this;
                  this.context$1 = context$1;
               }
            }
         };
      }

      public OutputCommitCoordinatorEndpoint(final RpcEnv rpcEnv, final OutputCommitCoordinator outputCommitCoordinator) {
         this.rpcEnv = rpcEnv;
         this.org$apache$spark$scheduler$OutputCommitCoordinator$OutputCommitCoordinatorEndpoint$$outputCommitCoordinator = outputCommitCoordinator;
         RpcEndpoint.$init$(this);
         Logging.$init$(this);
         this.logDebug((Function0)(() -> "init"));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
