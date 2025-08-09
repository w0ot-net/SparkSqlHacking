package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.Axis;
import breeze.linalg.BitVector;
import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseMatrix$;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.TensorLike;
import breeze.linalg.support.CanCollapseAxis;
import breeze.linalg.support.CanIterateAxis;
import breeze.linalg.support.CanTraverseAxis;
import breeze.linalg.support.CanZipMapKeyValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r5ea\u0002\u0011\"!\u0003\r\t\u0001\u000b\u0005\u0006\u000b\u0002!\tA\u0012\u0005\b\u0015\u0002\u0011\r\u0011b\u0001L\u0011\u001d\u0001\u0007A1A\u0005\u0004\u0005Dq\u0001\u001b\u0001C\u0002\u0013\r\u0011\u000eC\u0003q\u0001\u0011\r\u0011\u000fC\u0004\u0002L\u0001!\u0019!!\u0014\t\u000f\u0005\u001d\u0004\u0001b\u0001\u0002j!9\u0011\u0011\u0013\u0001\u0005\u0004\u0005M\u0005bBAi\u0001\u0011\r\u00111\u001b\u0005\b\u0003G\u0004A1AAs\u0011\u001d\ty\u0010\u0001C\u0002\u0005\u0003AqAa\u0005\u0001\t\u0007\u0011)\u0002C\u0004\u0003$\u0001!\u0019A!\n\t\u000f\t]\u0002\u0001b\u0001\u0003:\u00191!q\t\u0001\u0001\u0005\u0013B!Ba(\u0010\u0005\u0007\u0005\u000b1\u0002BQ\u0011\u001d\u0011\u0019k\u0004C\u0001\u0005KCqAa,\u0010\t\u0003\u0011\t\fC\u0004\u0003<>!\tA!0\t\u000f\tE\u0007\u0001b\u0001\u0003T\"I!q\u001d\u0001C\u0002\u0013\r!\u0011\u001e\u0005\n\u0005[\u0004!\u0019!C\u0002\u0005_D\u0011Ba=\u0001\u0005\u0004%\u0019A!>\u0007\r\te\b\u0001\u0001B~\u0011)\u0019\u0019\u0004\u0007B\u0002B\u0003-1Q\u0007\u0005\b\u0005GCB\u0011AB\u001c\u0011\u001d\u0011y\u000b\u0007C\u0001\u0007\u007fAqa!\u0012\u0019\t\u0003\u001a9\u0005C\u0004\u0003<b!\ta!\u0016\t\u000f\ru\u0003\u0001b\u0001\u0004`!911\u000f\u0001\u0005\u0004\rU$A\u0004#f]N,W*\u0019;sSb|\u0005o\u001d\u0006\u0003E\r\n\u0011b\u001c9fe\u0006$xN]:\u000b\u0005\u0011*\u0013A\u00027j]\u0006dwMC\u0001'\u0003\u0019\u0011'/Z3{K\u000e\u00011#\u0003\u0001*_M2\u0014\bP C!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001'M\u0007\u0002C%\u0011!'\t\u0002\n\u001b\u0006$(/\u001b=PaN\u0004\"\u0001\r\u001b\n\u0005U\n#A\u0006#f]N,W*\u0019;sSb,\u0005\u0010]1oI\u0016$w\n]:\u0011\u0005A:\u0014B\u0001\u001d\"\u0005e!UM\\:f\u001b\u0006$(/\u001b=`\u0007>l\u0007/\u0019:jg>tw\n]:\u0011\u0005AR\u0014BA\u001e\"\u0005I!UM\\:f\u001b\u0006$(/\u001b=Nk2$x\n]:\u0011\u0005Aj\u0014B\u0001 \"\u0005Y!UM\\:f\u001b\u0006$(/\u001b=Nk2$\u0018\u000e\u001d7z\u001fB\u001c\bC\u0001\u0019A\u0013\t\t\u0015EA\u0010EK:\u001cX-T1ue&Dx\n]:`\r2|\u0017\r^*qK\u000eL\u0017\r\\5{K\u0012\u0004\"\u0001M\"\n\u0005\u0011\u000b#A\u0005#f]N,W*\u0019;sSb|6+\u001a;PaN\fa\u0001J5oSR$C#A$\u0011\u0005)B\u0015BA%,\u0005\u0011)f.\u001b;\u0002\u000fM,G/\u0014,`\tV\tA\n\u0005\u0003N!ZkfB\u0001\u0019O\u0013\ty\u0015%A\u0003PaN+G/\u0003\u0002R%\na\u0011J\u001c)mC\u000e,\u0017*\u001c9me%\u00111\u000b\u0016\u0002\u0006+\u001a+hn\u0019\u0006\u0003+\u0016\nqaZ3oKJL7\rE\u0002X1jk\u0011aI\u0005\u00033\u000e\u00121\u0002R3og\u0016l\u0015\r\u001e:jqB\u0011!fW\u0005\u00039.\u0012a\u0001R8vE2,\u0007cA,_5&\u0011ql\t\u0002\f\t\u0016t7/\u001a,fGR|'/A\u0004tKRlek\u0018$\u0016\u0003\t\u0004B!\u0014)dOB\u0019q\u000b\u00173\u0011\u0005)*\u0017B\u00014,\u0005\u00151En\\1u!\r9f\fZ\u0001\bg\u0016$XJV0J+\u0005Q\u0007\u0003B'QW>\u00042a\u0016-m!\tQS.\u0003\u0002oW\t\u0019\u0011J\u001c;\u0011\u0007]sF.A\u0007dC:l\u0015\r\u001d*poN|F)T\u000b\u0005er\fy\u0002F\u0004t\u0003K\t)$!\u0012\u0011\u0017Q<\u00180a\u0003\u0002\u001a\u0005m\u00111E\u0007\u0002k*\u0011aoI\u0001\bgV\u0004\bo\u001c:u\u0013\tAXOA\bDC:\u001cu\u000e\u001c7baN,\u0017\t_5t!\r9\u0006L\u001f\t\u0003wrd\u0001\u0001B\u0003~\u000b\t\u0007aPA\u0001W#\ry\u0018Q\u0001\t\u0004U\u0005\u0005\u0011bAA\u0002W\t9aj\u001c;iS:<\u0007c\u0001\u0016\u0002\b%\u0019\u0011\u0011B\u0016\u0003\u0007\u0005s\u0017P\u0004\u0003\u0002\u000e\u0005MabA,\u0002\u0010%\u0019\u0011\u0011C\u0012\u0002\t\u0005C\u0018n]\u0005\u0005\u0003+\t9\"\u0001\u0002`a)\u0019\u0011\u0011C\u0012\u0011\u0007]s&\u0010\u0005\u0003X=\u0006u\u0001cA>\u0002 \u00111\u0011\u0011E\u0003C\u0002y\u0014\u0011A\u0015\t\u0005/b\u000bi\u0002C\u0005\u0002(\u0015\t\t\u0011q\u0001\u0002*\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005-\u0012\u0011GA\u000f\u001b\t\tiCC\u0002\u00020-\nqA]3gY\u0016\u001cG/\u0003\u0003\u00024\u00055\"\u0001C\"mCN\u001cH+Y4\t\u0013\u0005]R!!AA\u0004\u0005e\u0012AC3wS\u0012,gnY3%eA1\u00111HA!\u0003;i!!!\u0010\u000b\u0007\u0005}R%A\u0004ti>\u0014\u0018mZ3\n\t\u0005\r\u0013Q\b\u0002\u00055\u0016\u0014x\u000eC\u0004\u0002H\u0015\u0001\u001d!!\u0013\u0002\u000f%l\u0007\u000f\\*fiB1Q\nUA\u000e\u00037\tQ\u0003[1oI\"|G\u000eZ\"b]6\u000b\u0007OU8xg~#U*\u0006\u0003\u0002P\u0005\rTCAA)!)\t\u0019&!\u0017\u0002`\u0005-\u0011Q\r\b\u0004i\u0006U\u0013bAA,k\u0006y1)\u00198D_2d\u0017\r]:f\u0003bL7/\u0003\u0003\u0002\\\u0005u#\u0001\u0003%b]\u0012Du\u000e\u001c3\u000b\u0007\u0005]S\u000f\u0005\u0003X1\u0006\u0005\u0004cA>\u0002d\u0011)QP\u0002b\u0001}B!qKXA1\u0003Y\u0019\u0017M\\'baJ{wo\u001d\"jiZ+7\r^8s?\u0012kU\u0003BA6\u0003g\"b!!\u001c\u0002\u0006\u0006-\u0005\u0003\u0004;x\u0003_\nY!!\u001e\u0002x\u0005u\u0004\u0003B,Y\u0003c\u00022a_A:\t\u0015ixA1\u0001\u007f!\u00119f,!\u001d\u0011\u0007]\u000bI(C\u0002\u0002|\r\u0012\u0011BQ5u-\u0016\u001cGo\u001c:\u0011\t]C\u0016q\u0010\t\u0004U\u0005\u0005\u0015bAABW\t9!i\\8mK\u0006t\u0007\"CAD\u000f\u0005\u0005\t9AAE\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003W\t\t$!\u001d\t\u0013\u00055u!!AA\u0004\u0005=\u0015AC3wS\u0012,gnY3%iA1\u00111HA!\u0003c\nQbY1o\u001b\u0006\u00048i\u001c7t?\u0012kUCBAK\u0003;\u000bY\f\u0006\u0005\u0002\u0018\u0006\u0005\u0017qYAg!1!x/!'\u0002 \u0006U\u0016qWA`!\u00119\u0006,a'\u0011\u0007m\fi\nB\u0003~\u0011\t\u0007aP\u0004\u0003\u0002\"\u0006Ef\u0002BAR\u0003\u001fqA!!*\u00020:!\u0011qUAW\u001b\t\tIKC\u0002\u0002,\u001e\na\u0001\u0010:p_Rt\u0014\"\u0001\u0014\n\u0005\u0011*\u0013\u0002BAZ\u0003/\t!aX\u0019\u0011\t]s\u00161\u0014\t\u0005/z\u000bI\fE\u0002|\u0003w#a!!0\t\u0005\u0004q(a\u0001*fgB!q\u000bWA]\u0011%\t\u0019\rCA\u0001\u0002\b\t)-\u0001\u0006fm&$WM\\2fIU\u0002b!a\u000b\u00022\u0005e\u0006\"CAe\u0011\u0005\u0005\t9AAf\u0003))g/\u001b3f]\u000e,GE\u000e\t\u0007\u0003w\t\t%!/\t\u000f\u0005\u001d\u0003\u0002q\u0001\u0002PB1Q\nUA\\\u0003o\u000bQ\u0003[1oI\"|G\u000eZ\"b]6\u000b\u0007oQ8mg~#U*\u0006\u0003\u0002V\u0006uWCAAl!)\t\u0019&!\u0017\u0002Z\u0006}\u0017\u0011\u001d\t\u0005/b\u000bY\u000eE\u0002|\u0003;$Q!`\u0005C\u0002ytA!!\u0004\u00022B!qKXAn\u0003Y\u0019\u0017M\\'ba\u000e{Gn\u001d\"jiZ+7\r^8s?\u0012kU\u0003BAt\u0003_$b!!;\u0002t\u0006e\b\u0003\u0004;x\u0003W\fy.!=\u0002x\u0005u\u0004\u0003B,Y\u0003[\u00042a_Ax\t\u0015i(B1\u0001\u007f!\u00119f,!<\t\u0013\u0005U(\"!AA\u0004\u0005]\u0018AC3wS\u0012,gnY3%oA1\u00111FA\u0019\u0003[D\u0011\"a?\u000b\u0003\u0003\u0005\u001d!!@\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\b\u0005\u0004\u0002<\u0005\u0005\u0013Q^\u0001\u0013G\u0006tGK]1wKJ\u001cXmQ8mg~#U*\u0006\u0003\u0003\u0004\t=QC\u0001B\u0003!%!(q\u0001B\u0006\u0003\u0017\u0011\t\"C\u0002\u0003\nU\u0014qbQ1o)J\fg/\u001a:tK\u0006C\u0018n\u001d\t\u0005/b\u0013i\u0001E\u0002|\u0005\u001f!Q!`\u0006C\u0002y\u0004Ba\u00160\u0003\u000e\u0005\u00112-\u00198Ue\u00064XM]:f%><8o\u0018#N+\u0011\u00119Ba\b\u0016\u0005\te\u0001#\u0003;\u0003\b\tm\u0011q\u001cB\u0011!\u00119\u0006L!\b\u0011\u0007m\u0014y\u0002B\u0003~\u0019\t\u0007a\u0010\u0005\u0003X=\nu\u0011!E2b]&#XM]1uK\u000e{Gn]0E\u001bV!!q\u0005B\u001a+\t\u0011I\u0003E\u0005u\u0005W\u0011y#a\u0003\u00036%\u0019!QF;\u0003\u001d\r\u000bg.\u0013;fe\u0006$X-\u0011=jgB!q\u000b\u0017B\u0019!\rY(1\u0007\u0003\u0006{6\u0011\rA \t\u0005/z\u0013\t$A\tdC:LE/\u001a:bi\u0016\u0014vn^:`\t6+BAa\u000f\u0003DU\u0011!Q\b\t\ni\n-\"qHAp\u0005\u000b\u0002Ba\u0016-\u0003BA\u00191Pa\u0011\u0005\u000but!\u0019\u0001@\u0011\t]s&\u0011\t\u0002\u001b\u0007\u0006t',\u001b9NCB4\u0016\r\\;fg\u0012+gn]3NCR\u0014\u0018\u000e_\u000b\u0007\u0005\u0017\u00129Fa$\u0014\t=I#Q\n\t\fi\n=#1\u000bB+\u0005\u001b\u0013i*C\u0002\u0003RU\u0014qbQ1o5&\u0004X*\u00199WC2,Xm\u001d\t\u0005/b\u0013)\u0006E\u0002|\u0005/\"\u0011\"`\b!\u0002\u0003\u0005)\u0019\u0001@)\u0019\t]#1\fB1\u0005_\u0012IHa!\u0011\u0007)\u0012i&C\u0002\u0003`-\u00121b\u001d9fG&\fG.\u001b>fIFJ1Ea\u0019\u0003f\t%$q\r\b\u0004U\t\u0015\u0014b\u0001B4W\u00051Ai\\;cY\u0016\fd\u0001\nB6\u0005[bc\u0002BAT\u0005[J\u0011\u0001L\u0019\nG\tE$1\u000fB<\u0005kr1A\u000bB:\u0013\r\u0011)hK\u0001\u0004\u0013:$\u0018G\u0002\u0013\u0003l\t5D&M\u0005$\u0005w\u0012iH!!\u0003\u00009\u0019!F! \n\u0007\t}4&A\u0003GY>\fG/\r\u0004%\u0005W\u0012i\u0007L\u0019\nG\t\u0015%q\u0011BF\u0005\u0013s1A\u000bBD\u0013\r\u0011IiK\u0001\u0005\u0019>tw-\r\u0004%\u0005W\u0012i\u0007\f\t\u0004w\n=EA\u0003BI\u001f\u0001\u0006\t\u0011!b\u0001}\n\u0011!K\u0016\u0015\t\u0005\u001f\u0013YF!&\u0003\u001aFJ1E!\u001d\u0003t\t]%QO\u0019\u0007I\t-$Q\u000e\u00172\u0013\r\u0012\u0019G!\u001a\u0003\u001c\n\u001d\u0014G\u0002\u0013\u0003l\t5D\u0006\u0005\u0003X1\n5\u0015AC3wS\u0012,gnY3%sA1\u00111FA\u0019\u0005\u001b\u000ba\u0001P5oSRtDC\u0001BT)\u0011\u0011IK!,\u0011\u000f\t-vB!\u0016\u0003\u000e6\t\u0001\u0001C\u0004\u0003 F\u0001\u001dA!)\u0002\r\r\u0014X-\u0019;f)\u0019\u0011iJa-\u00038\"1!Q\u0017\nA\u00021\fAA]8xg\"1!\u0011\u0018\nA\u00021\fAaY8mg\u0006\u0019Q.\u00199\u0015\u0011\tu%q\u0018Bb\u0005\u000fDqA!1\u0014\u0001\u0004\u0011\u0019&\u0001\u0003ge>l\u0007b\u0002Bc'\u0001\u0007!1K\u0001\u0006MJ|WN\r\u0005\b\u0005\u0013\u001c\u0002\u0019\u0001Bf\u0003\t1g\u000eE\u0005+\u0005\u001b\u0014)F!\u0016\u0003\u000e&\u0019!qZ\u0016\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0014!\u0003>ja6\u000b\u0007o\u0018#N+\u0019\u0011)Na7\u0003`R!!q\u001bBq!\u001d\u0011Yk\u0004Bm\u0005;\u00042a\u001fBn\t\u0015iHC1\u0001\u007f!\rY(q\u001c\u0003\u0007\u0003C!\"\u0019\u0001@\t\u0013\t\rH#!AA\u0004\t\u0015\u0018aC3wS\u0012,gnY3%cA\u0002b!a\u000b\u00022\tu\u0017\u0001\u0005>ja6\u000b\u0007o\u0018#N?\u0012{WO\u00197f+\t\u0011Y\u000fE\u0003\u0003,>Q&,A\b{SBl\u0015\r]0E\u001b~3En\\1u+\t\u0011\t\u0010E\u0003\u0003,>!G-A\u0007{SBl\u0015\r]0E\u001b~Ke\u000e^\u000b\u0003\u0005o\u0004RAa+\u0010Y2\u0014QdQ1o5&\u0004X*\u00199LKf4\u0016\r\\;fg\u0012+gn]3NCR\u0014\u0018\u000e_\u000b\u0007\u0005{\u001cIa!\n\u0014\taI#q \t\u000ei\u000e\u00051QAB\u000f\u0007\u000f\u0019\u0019c!\r\n\u0007\r\rQO\u0001\nDC:T\u0016\u000e]'ba.+\u0017PV1mk\u0016\u001c\b\u0003B,Y\u0007\u000f\u00012a_B\u0005\t%i\b\u0004)A\u0001\u0002\u000b\u0007a\u0010\u000b\u0007\u0004\n\tm3QBB\t\u0007+\u0019I\"M\u0005$\u0005G\u0012)ga\u0004\u0003hE2AEa\u001b\u0003n1\n\u0014b\tB9\u0005g\u001a\u0019B!\u001e2\r\u0011\u0012YG!\u001c-c%\u0019#1\u0010B?\u0007/\u0011y(\r\u0004%\u0005W\u0012i\u0007L\u0019\nG\t\u0015%qQB\u000e\u0005\u0013\u000bd\u0001\nB6\u0005[b\u0003#\u0002\u0016\u0004 1d\u0017bAB\u0011W\t1A+\u001e9mKJ\u00022a_B\u0013\t)\u0011\t\n\u0007Q\u0001\u0002\u0003\u0015\rA \u0015\t\u0007K\u0011Yf!\u000b\u0004.EJ1E!\u001d\u0003t\r-\"QO\u0019\u0007I\t-$Q\u000e\u00172\u0013\r\u0012\u0019G!\u001a\u00040\t\u001d\u0014G\u0002\u0013\u0003l\t5D\u0006\u0005\u0003X1\u000e\r\u0012aC3wS\u0012,gnY3%cE\u0002b!a\u000b\u00022\r\rBCAB\u001d)\u0011\u0019Yd!\u0010\u0011\u000f\t-\u0006da\u0002\u0004$!911\u0007\u000eA\u0004\rUBCBB\u0019\u0007\u0003\u001a\u0019\u0005\u0003\u0004\u00036n\u0001\r\u0001\u001c\u0005\u0007\u0005s[\u0002\u0019\u00017\u0002\u00135\f\u0007/Q2uSZ,G\u0003CB\u0019\u0007\u0013\u001aYe!\u0014\t\u000f\t\u0005G\u00041\u0001\u0004\u0006!9!Q\u0019\u000fA\u0002\r\u0015\u0001b\u0002Be9\u0001\u00071q\n\t\fU\rE3QDB\u0004\u0007\u000f\u0019\u0019#C\u0002\u0004T-\u0012\u0011BR;oGRLwN\\\u001a\u0015\u0011\rE2qKB-\u00077BqA!1\u001e\u0001\u0004\u0019)\u0001C\u0004\u0003Fv\u0001\ra!\u0002\t\u000f\t%W\u00041\u0001\u0004P\u0005Y!0\u001b9NCB\\ek\u0018#N+\u0019\u0019\tga\u001a\u0004lQ!11MB7!\u001d\u0011Y\u000bGB3\u0007S\u00022a_B4\t\u0015ihD1\u0001\u007f!\rY81\u000e\u0003\u0007\u0003Cq\"\u0019\u0001@\t\u0013\r=d$!AA\u0004\rE\u0014aC3wS\u0012,gnY3%cI\u0002b!a\u000b\u00022\r%\u0014!C2b]\u0012KWn\u0018#N+\u0011\u00199h!#\u0016\u0005\re\u0004\u0003CB>\u0007\u0003\u001b)i!\b\u000f\u0007]\u001bi(C\u0002\u0004\u0000\r\n1\u0001Z5n\u0013\r\u0019\u0019I\u0015\u0002\u0005\u00136\u0004H\u000e\u0005\u0003X1\u000e\u001d\u0005cA>\u0004\n\u0012111R\u0010C\u0002y\u0014\u0011!\u0012"
)
public interface DenseMatrixOps extends DenseMatrix_ComparisonOps, DenseMatrixMultiplyOps, DenseMatrixOps_FloatSpecialized {
   void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_D_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_F_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_I_$eq(final UFunc.InPlaceImpl2 x$1);

   void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Double_$eq(final CanZipMapValuesDenseMatrix x$1);

   void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Float_$eq(final CanZipMapValuesDenseMatrix x$1);

   void breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Int_$eq(final CanZipMapValuesDenseMatrix x$1);

   UFunc.InPlaceImpl2 setMV_D();

   UFunc.InPlaceImpl2 setMV_F();

   UFunc.InPlaceImpl2 setMV_I();

   // $FF: synthetic method
   static CanCollapseAxis canMapRows_DM$(final DenseMatrixOps $this, final ClassTag evidence$1, final Zero evidence$2, final UFunc.InPlaceImpl2 implSet) {
      return $this.canMapRows_DM(evidence$1, evidence$2, implSet);
   }

   default CanCollapseAxis canMapRows_DM(final ClassTag evidence$1, final Zero evidence$2, final UFunc.InPlaceImpl2 implSet) {
      return new CanCollapseAxis(evidence$1, evidence$2, implSet) {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;
         private final ClassTag evidence$1$1;
         private final Zero evidence$2$1;
         private final UFunc.InPlaceImpl2 implSet$1;

         public DenseMatrix apply(final DenseMatrix from, final Axis._0$ axis, final Function1 f) {
            DenseMatrix result = null;
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               DenseVector col = (DenseVector)f.apply(from.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
               if (result == null) {
                  result = DenseMatrix$.MODULE$.zeros(col.length(), from.cols(), this.evidence$1$1, this.evidence$2$1);
               }

               this.implSet$1.apply(result.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()), col);
            }

            return result == null ? DenseMatrix$.MODULE$.zeros(0, from.cols(), this.evidence$1$1, this.evidence$2$1) : result;
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
               this.evidence$1$1 = evidence$1$1;
               this.evidence$2$1 = evidence$2$1;
               this.implSet$1 = implSet$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis.HandHold handholdCanMapRows_DM$(final DenseMatrixOps $this) {
      return $this.handholdCanMapRows_DM();
   }

   default CanCollapseAxis.HandHold handholdCanMapRows_DM() {
      return new CanCollapseAxis.HandHold();
   }

   // $FF: synthetic method
   static CanCollapseAxis canMapRowsBitVector_DM$(final DenseMatrixOps $this, final ClassTag evidence$3, final Zero evidence$4) {
      return $this.canMapRowsBitVector_DM(evidence$3, evidence$4);
   }

   default CanCollapseAxis canMapRowsBitVector_DM(final ClassTag evidence$3, final Zero evidence$4) {
      return new CanCollapseAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public DenseMatrix apply(final DenseMatrix from, final Axis._0$ axis, final Function1 f) {
            DenseMatrix result = null;
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               BitVector col = (BitVector)f.apply(from.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
               if (result == null) {
                  result = DenseMatrix$.MODULE$.zeros(col.length(), from.cols(), scala.reflect.ClassTag..MODULE$.Boolean(), Zero$.MODULE$.BooleanZero());
               }

               ((NumericOps)result.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol())).$colon$eq(col, this.$outer.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_OpSet_V_V_InPlace()));
            }

            return result == null ? DenseMatrix$.MODULE$.zeros(0, from.cols(), scala.reflect.ClassTag..MODULE$.Boolean(), Zero$.MODULE$.BooleanZero()) : result;
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis canMapCols_DM$(final DenseMatrixOps $this, final ClassTag evidence$5, final Zero evidence$6, final UFunc.InPlaceImpl2 implSet) {
      return $this.canMapCols_DM(evidence$5, evidence$6, implSet);
   }

   default CanCollapseAxis canMapCols_DM(final ClassTag evidence$5, final Zero evidence$6, final UFunc.InPlaceImpl2 implSet) {
      return new CanCollapseAxis(evidence$5, evidence$6, implSet) {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;
         private final ClassTag evidence$5$1;
         private final Zero evidence$6$1;
         private final UFunc.InPlaceImpl2 implSet$2;

         public DenseMatrix apply(final DenseMatrix from, final Axis._1$ axis, final Function1 f) {
            DenseMatrix result = null;
            DenseMatrix t = (DenseMatrix)from.t(HasOps$.MODULE$.canTranspose_DM());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               DenseVector row = (DenseVector)f.apply(t.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
               if (result == null) {
                  Object data = this.evidence$5$1.newArray(from.rows() * row.length());
                  result = DenseMatrix$.MODULE$.create(from.rows(), row.length(), data, this.evidence$6$1);
               }

               this.implSet$2.apply(((TensorLike)result.t(HasOps$.MODULE$.canTranspose_DM())).apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()), row);
            }

            DenseMatrix var10000;
            if (result != null) {
               var10000 = result;
            } else {
               Object data = this.evidence$5$1.newArray(0);
               result = DenseMatrix$.MODULE$.create(from.rows(), 0, data, this.evidence$6$1);
               var10000 = result;
            }

            return var10000;
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
               this.evidence$5$1 = evidence$5$1;
               this.evidence$6$1 = evidence$6$1;
               this.implSet$2 = implSet$2;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanCollapseAxis.HandHold handholdCanMapCols_DM$(final DenseMatrixOps $this) {
      return $this.handholdCanMapCols_DM();
   }

   default CanCollapseAxis.HandHold handholdCanMapCols_DM() {
      return new CanCollapseAxis.HandHold();
   }

   // $FF: synthetic method
   static CanCollapseAxis canMapColsBitVector_DM$(final DenseMatrixOps $this, final ClassTag evidence$7, final Zero evidence$8) {
      return $this.canMapColsBitVector_DM(evidence$7, evidence$8);
   }

   default CanCollapseAxis canMapColsBitVector_DM(final ClassTag evidence$7, final Zero evidence$8) {
      return new CanCollapseAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public DenseMatrix apply(final DenseMatrix from, final Axis._1$ axis, final Function1 f) {
            DenseMatrix result = null;
            DenseMatrix t = (DenseMatrix)from.t(HasOps$.MODULE$.canTranspose_DM());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               BitVector row = (BitVector)f.apply(t.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
               if (result == null) {
                  boolean[] data = new boolean[from.rows() * row.length()];
                  result = DenseMatrix$.MODULE$.create(from.rows(), row.length(), data, Zero$.MODULE$.BooleanZero());
               }

               ((NumericOps)((TensorLike)result.t(HasOps$.MODULE$.canTranspose_DM())).apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol())).$colon$eq(row, this.$outer.castUpdateOps_V_V(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), HasOps$.MODULE$.impl_OpSet_V_V_InPlace()));
            }

            DenseMatrix var10000;
            if (result != null) {
               var10000 = result;
            } else {
               boolean[] data = new boolean[0];
               result = DenseMatrix$.MODULE$.create(from.rows(), 0, data, Zero$.MODULE$.BooleanZero());
               var10000 = result;
            }

            return var10000;
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseAxis canTraverseCols_DM$(final DenseMatrixOps $this) {
      return $this.canTraverseCols_DM();
   }

   default CanTraverseAxis canTraverseCols_DM() {
      return new CanTraverseAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public void apply(final DenseMatrix from, final Axis._0$ axis, final Function1 f) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.cols(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               f.apply(from.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
            }

         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanTraverseAxis canTraverseRows_DM$(final DenseMatrixOps $this) {
      return $this.canTraverseRows_DM();
   }

   default CanTraverseAxis canTraverseRows_DM() {
      return new CanTraverseAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public void apply(final DenseMatrix from, final Axis._1$ axis, final Function1 f) {
            DenseMatrix t = (DenseMatrix)from.t(HasOps$.MODULE$.canTranspose_DM());
            int index$macro$2 = 0;

            for(int limit$macro$4 = from.rows(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               f.apply(t.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(index$macro$2), this.$outer.canSliceCol()));
            }

         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static CanIterateAxis canIterateCols_DM$(final DenseMatrixOps $this) {
      return $this.canIterateCols_DM();
   }

   default CanIterateAxis canIterateCols_DM() {
      return new CanIterateAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public Iterator apply(final DenseMatrix from, final Axis._0$ axis) {
            return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.cols()).iterator().map((x$1) -> $anonfun$apply$1(this, from, BoxesRunTime.unboxToInt(x$1)));
         }

         // $FF: synthetic method
         public static final DenseVector $anonfun$apply$1(final Object $this, final DenseMatrix from$1, final int x$1) {
            return (DenseVector)from$1.apply(.MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(x$1), $this.$outer.canSliceCol());
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanIterateAxis canIterateRows_DM$(final DenseMatrixOps $this) {
      return $this.canIterateRows_DM();
   }

   default CanIterateAxis canIterateRows_DM() {
      return new CanIterateAxis() {
         // $FF: synthetic field
         private final DenseMatrixOps $outer;

         public Iterator apply(final DenseMatrix from, final Axis._1$ axis) {
            return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), from.rows()).iterator().map((x$2) -> $anonfun$apply$2(this, from, BoxesRunTime.unboxToInt(x$2)));
         }

         // $FF: synthetic method
         public static final DenseVector $anonfun$apply$2(final Object $this, final DenseMatrix from$2, final int x$2) {
            return (DenseVector)((ImmutableNumericOps)from$2.apply(BoxesRunTime.boxToInteger(x$2), .MODULE$.$colon$colon(), $this.$outer.canSliceRow())).t(HasOps$.MODULE$.canUntranspose());
         }

         public {
            if (DenseMatrixOps.this == null) {
               throw null;
            } else {
               this.$outer = DenseMatrixOps.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static CanZipMapValuesDenseMatrix zipMap_DM$(final DenseMatrixOps $this, final ClassTag evidence$10) {
      return $this.zipMap_DM(evidence$10);
   }

   default CanZipMapValuesDenseMatrix zipMap_DM(final ClassTag evidence$10) {
      return new CanZipMapValuesDenseMatrix(evidence$10);
   }

   CanZipMapValuesDenseMatrix zipMap_DM_Double();

   CanZipMapValuesDenseMatrix zipMap_DM_Float();

   CanZipMapValuesDenseMatrix zipMap_DM_Int();

   // $FF: synthetic method
   static CanZipMapKeyValuesDenseMatrix zipMapKV_DM$(final DenseMatrixOps $this, final ClassTag evidence$12) {
      return $this.zipMapKV_DM(evidence$12);
   }

   default CanZipMapKeyValuesDenseMatrix zipMapKV_DM(final ClassTag evidence$12) {
      return new CanZipMapKeyValuesDenseMatrix(evidence$12);
   }

   // $FF: synthetic method
   static UFunc.UImpl canDim_DM$(final DenseMatrixOps $this) {
      return $this.canDim_DM();
   }

   default UFunc.UImpl canDim_DM() {
      return new UFunc.UImpl() {
         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public Tuple2 apply(final DenseMatrix v) {
            return new Tuple2.mcII.sp(v.rows(), v.cols());
         }
      };
   }

   static void $init$(final DenseMatrixOps $this) {
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_D_$eq(new DenseMatrix_SetOps$SetDMDVOp$mcD$sp($this));
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_F_$eq(new DenseMatrix_SetOps$SetDMDVOp$mcF$sp($this));
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$setMV_I_$eq(new DenseMatrix_SetOps$SetDMDVOp$mcI$sp($this));
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Double_$eq(new DenseMatrixOps$CanZipMapValuesDenseMatrix$mcDD$sp($this, scala.reflect.ClassTag..MODULE$.Double()));
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Float_$eq($this.new CanZipMapValuesDenseMatrix(scala.reflect.ClassTag..MODULE$.Float()));
      $this.breeze$linalg$operators$DenseMatrixOps$_setter_$zipMap_DM_Int_$eq(new DenseMatrixOps$CanZipMapValuesDenseMatrix$mcII$sp($this, scala.reflect.ClassTag..MODULE$.Int()));
   }

   public class CanZipMapValuesDenseMatrix implements CanZipMapValues {
      public final ClassTag breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$$evidence$9;
      // $FF: synthetic field
      public final DenseMatrixOps $outer;

      public Object map$mcDD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDD$sp$(this, from, from2, fn);
      }

      public Object map$mcFD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFD$sp$(this, from, from2, fn);
      }

      public Object map$mcID$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcID$sp$(this, from, from2, fn);
      }

      public Object map$mcJD$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJD$sp$(this, from, from2, fn);
      }

      public Object map$mcDF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDF$sp$(this, from, from2, fn);
      }

      public Object map$mcFF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFF$sp$(this, from, from2, fn);
      }

      public Object map$mcIF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIF$sp$(this, from, from2, fn);
      }

      public Object map$mcJF$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJF$sp$(this, from, from2, fn);
      }

      public Object map$mcDI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDI$sp$(this, from, from2, fn);
      }

      public Object map$mcFI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFI$sp$(this, from, from2, fn);
      }

      public Object map$mcII$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcII$sp$(this, from, from2, fn);
      }

      public Object map$mcJI$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJI$sp$(this, from, from2, fn);
      }

      public Object map$mcDJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcDJ$sp$(this, from, from2, fn);
      }

      public Object map$mcFJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcFJ$sp$(this, from, from2, fn);
      }

      public Object map$mcIJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcIJ$sp$(this, from, from2, fn);
      }

      public Object map$mcJJ$sp(final Object from, final Object from2, final Function2 fn) {
         return CanZipMapValues.map$mcJJ$sp$(this, from, from2, fn);
      }

      public DenseMatrix create(final int rows, final int cols) {
         return DenseMatrix$.MODULE$.create(rows, cols, this.breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$$evidence$9.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.create$default$6());
      }

      public DenseMatrix map(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         int left$macro$1 = from.rows();
         int right$macro$2 = from2.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector row dimensions must match!: ").append("from.rows == from2.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = from.cols();
            int right$macro$4 = from2.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector col dimensions must match!: ").append("from.cols == from2.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               DenseMatrix result = this.create(from.rows(), from.cols());
               int index$macro$11 = 0;

               for(int limit$macro$13 = from.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = from.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ((j, i) -> result.update(i, j, fn.apply(from.apply(i, j), from2.apply(i, j)))).apply$mcVII$sp(index$macro$11, index$macro$6);
                  }
               }

               return result;
            }
         }
      }

      public DenseMatrix create$mcD$sp(final int rows, final int cols) {
         return this.create(rows, cols);
      }

      public DenseMatrix create$mcI$sp(final int rows, final int cols) {
         return this.create(rows, cols);
      }

      public DenseMatrix map$mcDD$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcID$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDF$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcIF$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDI$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcII$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcIJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function2 fn) {
         return this.map(from, from2, fn);
      }

      // $FF: synthetic method
      public DenseMatrixOps breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$$$outer() {
         return this.$outer;
      }

      public CanZipMapValuesDenseMatrix(final ClassTag evidence$9) {
         this.breeze$linalg$operators$DenseMatrixOps$CanZipMapValuesDenseMatrix$$evidence$9 = evidence$9;
         if (DenseMatrixOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class CanZipMapKeyValuesDenseMatrix implements CanZipMapKeyValues {
      public final ClassTag breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$$evidence$11;
      // $FF: synthetic field
      public final DenseMatrixOps $outer;

      public DenseMatrix create(final int rows, final int cols) {
         return DenseMatrix$.MODULE$.create(rows, cols, this.breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$$evidence$11.newArray(rows * cols), 0, rows, DenseMatrix$.MODULE$.create$default$6());
      }

      public DenseMatrix mapActive(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         int left$macro$1 = from.rows();
         int right$macro$2 = from2.rows();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector row dimensions must match!: ").append("from.rows == from2.rows (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int left$macro$3 = from.cols();
            int right$macro$4 = from2.cols();
            if (left$macro$3 != right$macro$4) {
               throw new IllegalArgumentException((new StringBuilder(85)).append("requirement failed: Vector col dimensions must match!: ").append("from.cols == from2.cols (").append(left$macro$3).append(" ").append("!=").append(" ").append(right$macro$4).append(")").toString());
            } else {
               DenseMatrix result = this.create(from.rows(), from.cols());
               int index$macro$11 = 0;

               for(int limit$macro$13 = from.cols(); index$macro$11 < limit$macro$13; ++index$macro$11) {
                  int index$macro$6 = 0;

                  for(int limit$macro$8 = from.rows(); index$macro$6 < limit$macro$8; ++index$macro$6) {
                     ((j, i) -> result.update(i, j, fn.apply(new Tuple2.mcII.sp(i, j), from.apply(i, j), from2.apply(i, j)))).apply$mcVII$sp(index$macro$11, index$macro$6);
                  }
               }

               return result;
            }
         }
      }

      public DenseMatrix create$mcD$sp(final int rows, final int cols) {
         return this.create(rows, cols);
      }

      public DenseMatrix create$mcI$sp(final int rows, final int cols) {
         return this.create(rows, cols);
      }

      public DenseMatrix mapActive$mcDD$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcID$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcDF$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcIF$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcDI$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcII$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcDJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix mapActive$mcIJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.mapActive(from, from2, fn);
      }

      public DenseMatrix map$mcDD$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcID$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDF$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcIF$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDI$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcII$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcDJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      public DenseMatrix map$mcIJ$sp(final DenseMatrix from, final DenseMatrix from2, final Function3 fn) {
         return this.map(from, from2, fn);
      }

      // $FF: synthetic method
      public DenseMatrixOps breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$$$outer() {
         return this.$outer;
      }

      public CanZipMapKeyValuesDenseMatrix(final ClassTag evidence$11) {
         this.breeze$linalg$operators$DenseMatrixOps$CanZipMapKeyValuesDenseMatrix$$evidence$11 = evidence$11;
         if (DenseMatrixOps.this == null) {
            throw null;
         } else {
            this.$outer = DenseMatrixOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
