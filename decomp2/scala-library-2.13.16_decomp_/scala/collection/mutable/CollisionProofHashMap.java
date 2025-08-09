package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.SortedMapFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.View;
import scala.collection.generic.DefaultSerializationProxy;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.RichInt$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005!%eaBA'\u0003\u001f\u0012\u0011Q\f\u0005\u000b\u0003_\u0003!\u0011!Q\u0001\n\u0005E\u0006BCA\\\u0001\t\u0005\t\u0015!\u0003\u0002:\"q\u0011q\u0018\u0001\u0005\u0002\u0003\u0015)\u0011!Q\u0001\f\u0005\u0005\u0007bBAm\u0001\u0011\u0005\u00111\u001c\u0005\t\u0003O\u0004\u0001\u0015\"\u0004\u0002j\"9\u0011\u0011\u001c\u0001\u0005\u0002\u0005MX\u0001CA}\u0001\u0001\u0006I!a?\u0006\u0011\r\u0015\u0001\u0001)A\u0005\u000b[Ba\"b\u001c\u0001\t\u0003\u0005)\u0011!A!B\u0013)\t\b\u0003\b\u0006z\u0001!\t\u0011!B\u0001\u0002\u0003\u0006K!!-\t\u001d\u0015m\u0004\u0001\"A\u0001\u0006\u0003\u0005\t\u0015)\u0003\u00022\"9QQ\u0010\u0001\u0005B\tU\u0005BDC@\u0001\u0011\u0005\tQ!A\u0001B\u00135Q\u0011\u0011\u0005\u000f\u000b\u0013\u0003A\u0011!A\u0003\u0002\u0003\u0005KQBCF\u0011\u001d\u0011i\r\u0001C)\u000b#Cq!b+\u0001\t#*i\u000bC\u0004\u0003>\u0001!\t%b-\t\u000f\u0015U\u0006\u0001\"\u0011\u00068\"9Q1\u0018\u0001\u0005\u0002\u0015u\u0006bBCd\u0001\u0011\u0005S\u0011\u001a\u0005\b\u000b3\u0004A\u0011ICn\u0011!)\u0019\u0010\u0001Q\u0005\n\u0015U\bbBC\u007f\u0001\u0011\u0005Sq \u0005\b\r\u0007\u0001A\u0011\tD\u0003\u0011\u001d1Y\u0001\u0001C!\r\u001bAqAb\u0005\u0001\t\u00031)\u0002\u0003\u0005\u0007\u001c\u0001\u0001K\u0011\u0002D\u000f\u001191y\u0003\u0001C\u0001\u0002\u000b\u0005\t\u0011)C\u0005\rcA\u0001Bb\u0010\u0001A\u0013%a\u0011\t\u0005\b\r\u0017\u0002A\u0011\tD'\u0011!1\u0019\u0006\u0001Q\u0005\n\u0019Uc\u0001\u0003D-\u0001\u0001\u000eIAb\u0017\t\u000f\u0005e\u0007\u0005\"\u0001\u0007h!Aa1\u000e\u0011!\u000e#1i\u0007\u0003\u0005\u0007l\u0001\u0002k\u0011\u0003D9\u0011!19\b\tQ!\n\u0005E\u0006\u0002CB\u0001A\u0001\u0006K!b\u001e\t\u0011\u0019e\u0004\u0005)A\u0005\u0003cCq!b\t!\t\u0003\u00199\u0005C\u0004\u0005\u000e\u0001\"\tAb\u001f\t\u000f\u0019u\u0004\u0001\"\u0011\u0007\u0000!9aq\u0011\u0001\u0005B\u0019%\u0005B\u0004DG\u0001\u0011\u0005\tQ!A\u0001B\u0013%aq\u0012\u0005\t\r+\u0003\u0001\u0015\"\u0003\u0007\u0018\"AaQ\u0014\u0001!\n\u00131y\n\u0003\b\u00072\u0002!\t\u0011!B\u0001\u0002\u0003&IAb-\t\u001d\u0019E\u0006\u0001\"A\u0001\u0006\u0003\u0005\t\u0015\"\u0003\u0007@\"Aa\u0011\u001a\u0001!\n\u00131Y\r\u0003\b\u0007R\u0002!\t\u0011!B\u0001\u0002\u0003&IAb5\t\u000f\u0019]\u0007\u0001\"\u0011\u0007Z\"9a1\u001c\u0001\u0005B\u0019u\u0007b\u0002Dq\u0001\u0011\u0005a1\u001d\u0005\b\rO\u0004A\u0011\tBK\u0011\u001d1I\u000f\u0001C!\u0007\u000fBqa!0\u0001\t\u00032Y\u000fC\u0004\u0004V\u0002!\tEb>\t\u0011\u0015}\u0003\u0001)C\t\u000f\u0007A\u0001b\"\u0002\u0001A\u0013Esq\u0001\u0005\b\u000f\u0013\u0001A\u0011ID\u0006\u0011\u001d9)\u0002\u0001C\u0001\u000f/Aqab\u0010\u0001\t\u00039\t\u0005C\u0004\b`\u0001!\ta\"\u0019\t\u000f\u001d\r\u0005\u0001\"\u0011\b\u0006\"9qq\u0013\u0001\u0005F\u001de\u0005bBDV\u0001\u0011\u0005sQ\u0016\u0005\b\u000fW\u0003A\u0011IDi\u0011!9)\u0010\u0001Q\u0005\n\u001d]\b\u0002CD\u007f\u0001\u0001&Iab@\t\u0011\t\u0015\b\u0001)C\u0005\u0011\u000bA\u0001B!:\u0001A\u0013%\u0001\u0012\u0003\u0005\t\u00117\u0001\u0001\u0015\"\u0004\t\u001e!A\u00012\u0007\u0001!\n\u001bA)\u0004\u0003\u0005\tB\u0001\u0001K\u0011\u0002E\"\u0011!AI\u0005\u0001Q\u0005\n!-\u0003\u0002\u0003E+\u0001\u0001&I\u0001c\u0016\t\u0011!}\u0003\u0001)C\u0005\u0011CB\u0001\u0002#\u001b\u0001A\u0013%\u00012\u000e\u0005\t\u0011g\u0002\u0001\u0015\"\u0003\tv!9\u0001r\u0010\u0001\u0005\u0002!\u0005u\u0001CA\u0000\u0003\u001fB\tA!\u0001\u0007\u0011\u00055\u0013q\nE\u0001\u0005\u0007Aq!!7R\t\u0003\u0011Y\u0001C\u0006\u0003\u000eE\u0013\r\u0011\"\u0002\u0002T\t=\u0001\u0002\u0003B\f#\u0002\u0006iA!\u0005\t\u000f\te\u0011\u000b\"\u0001\u0003\u001c!9!QH)\u0005\u0002\t}\u0002b\u0002B*#\u0012\u0005!Q\u000b\u0005\b\u0005'\nF\u0011\u0001B9\u0011\u001d\u0011y)\u0015C\u0003\u0005#CqAa%R\t\u000b\u0011)J\u0002\u0004\u0003\u0018F3!\u0011\u0014\u0005\u000b\u0005k[&Q1A\u0005\u0002\tU\u0005B\u0003B\\7\n\u0005\t\u0015!\u0003\u00022\"Q\u0011qW.\u0003\u0006\u0004%\tA!%\t\u0015\te6L!A!\u0002\u0013\tI\f\u0003\u0006\u0002bn\u0013)\u0019!C\u0001\u0005wC!Ba0\\\u0005\u0003\u0005\u000b\u0011\u0002B_\u0011\u001d\tIn\u0017C\u0001\u0005\u0003DqA!4\\\t\u0003\u0011y\rC\u0004\u0003Tm#\tA!6\t\u000f\t\u0015\u0018\u000b\"\u0003\u0003h\"9!Q])\u0005\n\u0011=\u0005\"\u0003CV#\n\u0007IQ\u0002CW\u0011!!\u0019,\u0015Q\u0001\u000e\u0011=faBB\u0007#\u0006\u00052q\u0002\u0005\b\u00033LG\u0011AB\t\r\u0019\tI0\u0015\u0002\u0004\u0016!Q!\u0011`6\u0003\u0002\u0004%\ta!\u0007\t\u0015\r}1N!a\u0001\n\u0003\u0019\t\u0003\u0003\u0006\u0004.-\u0014\t\u0011)Q\u0005\u00077A!B!@l\u0005\u0003\u0007I\u0011\u0001BK\u0011)\u0019yc\u001bBA\u0002\u0013\u00051\u0011\u0007\u0005\u000b\u0007kY'\u0011!Q!\n\u0005E\u0006B\u0003BqW\n\u0005\r\u0011\"\u0001\u00048!Q1QH6\u0003\u0002\u0004%\taa\u0010\t\u0015\r\r3N!A!B\u0013\u0019I\u0004\u0003\u0006\u0004F-\u0014\t\u0019!C\u0001\u0007\u000fB!ba\u0014l\u0005\u0003\u0007I\u0011AB)\u0011)\u0019)f\u001bB\u0001B\u0003&1\u0011\n\u0005\u000b\u0007/Z'\u00111A\u0005\u0002\re\u0003BCB/W\n\u0005\r\u0011\"\u0001\u0004`!Q11M6\u0003\u0002\u0003\u0006Kaa\u0017\t\u0015\r\u00154N!a\u0001\n\u0003\u0019I\u0006\u0003\u0006\u0004h-\u0014\t\u0019!C\u0001\u0007SB!b!\u001cl\u0005\u0003\u0005\u000b\u0015BB.\u0011)\u0019yg\u001bBA\u0002\u0013\u00051\u0011\f\u0005\u000b\u0007cZ'\u00111A\u0005\u0002\rM\u0004BCB<W\n\u0005\t\u0015)\u0003\u0004\\!9\u0011\u0011\\6\u0005\u0002\re\u0004bBBEW\u0012\u000531\u0012\u0005\b\u0007;[G\u0011ABP\u0011\u001d\u0019il\u001bC\u0001\u0007\u007fCqa!6l\t\u0003\u00199\u000eC\u0004\u0004h.$\ta!;\t\u000f\u0011U\u0016\u000b\"\u0003\u00058\"9AQ[)\u0005\n\u0011]\u0007b\u0002Cu#\u0012%A1\u001e\u0004\u0007\tw\ff\u0001\"@\t\u0017\u0015E\u0011Q\u0003B\u0001B\u0003%Qq\u0001\u0005\f\u0005_\f)B!A!\u0002\u0017)\u0019\u0002\u0003\u0005\u0002Z\u0006UA\u0011AC\f\u0011%)\t#!\u0006!B\u0013)9\u0001\u0003\u0005\u0006$\u0005UA\u0011AB$\u0011!!i!!\u0006\u0005\u0002\u0015\u0015bABB\u0003#\u001a\u00199\u0001C\u0006\u0003z\u0006\r\"\u00111A\u0005\u0002\rU\bbCB\u0010\u0003G\u0011\t\u0019!C\u0001\u0007wD1b!\f\u0002$\t\u0005\t\u0015)\u0003\u0004x\"Y!Q`A\u0012\u0005\u0003\u0007I\u0011\u0001BK\u0011-\u0019y#a\t\u0003\u0002\u0004%\taa@\t\u0017\rU\u00121\u0005B\u0001B\u0003&\u0011\u0011\u0017\u0005\f\u0005C\f\u0019C!a\u0001\n\u0003!\u0019\u0001C\u0006\u0004>\u0005\r\"\u00111A\u0005\u0002\u0011%\u0001bCB\"\u0003G\u0011\t\u0011)Q\u0005\t\u000bA1\u0002\"\u0004\u0002$\t\u0005\r\u0011\"\u0001\u0005\u0010!YA1CA\u0012\u0005\u0003\u0007I\u0011\u0001C\u000b\u0011-!I\"a\t\u0003\u0002\u0003\u0006K\u0001\"\u0005\t\u0011\u0005e\u00171\u0005C\u0001\t7A\u0001b!#\u0002$\u0011\u0005CQ\u0005\u0005\n\tk\t\u0019\u0003)C\u0005\toA\u0001b!(\u0002$\u0011\u0005A\u0011\t\u0005\t\u0007{\u000b\u0019\u0003\"\u0001\u0005P!A1Q[A\u0012\t\u0003!y\u0006\u0003\u0005\u0004h\u0006\rB\u0011\u0001C7\u0011%)y&UA\u0001\n\u0013)\tGA\u000bD_2d\u0017n]5p]B\u0013xn\u001c4ICNDW*\u00199\u000b\t\u0005E\u00131K\u0001\b[V$\u0018M\u00197f\u0015\u0011\t)&a\u0016\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0002\u0002Z\u0005)1oY1mC\u000e\u0001QCBA0\u0003[\n\u0019iE\u0005\u0001\u0003C\n9)!&\u0002*BA\u00111MA3\u0003S\n\t)\u0004\u0002\u0002P%!\u0011qMA(\u0005-\t%m\u001d;sC\u000e$X*\u00199\u0011\t\u0005-\u0014Q\u000e\u0007\u0001\t\u001d\ty\u0007\u0001b\u0001\u0003c\u0012\u0011aS\t\u0005\u0003g\nY\b\u0005\u0003\u0002v\u0005]TBAA,\u0013\u0011\tI(a\u0016\u0003\u000f9{G\u000f[5oOB!\u0011QOA?\u0013\u0011\ty(a\u0016\u0003\u0007\u0005s\u0017\u0010\u0005\u0003\u0002l\u0005\rEaBAC\u0001\t\u0007\u0011\u0011\u000f\u0002\u0002-Ba\u00111MAE\u0003S\n\t)!$\u0002\u0014&!\u00111RA(\u0005\u0019i\u0015\r](qgB!\u00111MAH\u0013\u0011\t\t*a\u0014\u0003\u00075\u000b\u0007\u000fE\u0004\u0002d\u0001\tI'!!\u0011\u0015\u0005]\u0015\u0011TAO\u0003G\u000b\u0019*\u0004\u0002\u0002T%!\u00111TA*\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t!!\t)(a(\u0002j\u0005\u0005\u0015\u0002BAQ\u0003/\u0012a\u0001V;qY\u0016\u0014\u0004\u0003BA2\u0003KKA!a*\u0002P\tA\u0011\n^3sC\ndW\r\u0005\u0007\u0002\u0018\u0006-\u0016\u0011NAA\u0003\u001b\u000b\u0019*\u0003\u0003\u0002.\u0006M#!F*ue&\u001cGo\u00149uS6L'0\u001a3NCB|\u0005o]\u0001\u0010S:LG/[1m\u0007\u0006\u0004\u0018mY5usB!\u0011QOAZ\u0013\u0011\t),a\u0016\u0003\u0007%sG/\u0001\u0006m_\u0006$g)Y2u_J\u0004B!!\u001e\u0002<&!\u0011QXA,\u0005\u0019!u.\u001e2mK\u0006A4oY1mC\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013nkR\f'\r\\3%\u0007>dG.[:j_:\u0004&o\\8g\u0011\u0006\u001c\b.T1qI\u0011z'\u000fZ3sS:<\u0007CBAb\u0003'\fIG\u0004\u0003\u0002F\u0006=g\u0002BAd\u0003\u001bl!!!3\u000b\t\u0005-\u00171L\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005e\u0013\u0002BAi\u0003/\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002V\u0006]'\u0001C(sI\u0016\u0014\u0018N\\4\u000b\t\u0005E\u0017qK\u0001\u0007y%t\u0017\u000e\u001e \u0015\r\u0005u\u00171]As)\u0011\t\u0019*a8\t\u000f\u0005\u0005H\u0001q\u0001\u0002B\u0006AqN\u001d3fe&tw\rC\u0004\u00020\u0012\u0001\r!!-\t\u000f\u0005]F\u00011\u0001\u0002:\u0006\u00012o\u001c:uK\u0012l\u0015\r\u001d$bGR|'/_\u000b\u0003\u0003W\u0004b!a&\u0002n\u0006E\u0018\u0002BAx\u0003'\u0012\u0001cU8si\u0016$W*\u00199GC\u000e$xN]=\u0011\u0007\u0005\r\u0004\u0001\u0006\u0002\u0002vR!\u00111SA|\u0011\u001d\t\tO\u0002a\u0002\u0003\u0003\u0014aA\u0015\"O_\u0012,\u0007cBA\u007fW\u0006%\u0014\u0011\u0011\b\u0004\u0003G\u0002\u0016!F\"pY2L7/[8o!J|wN\u001a%bg\"l\u0015\r\u001d\t\u0004\u0003G\n6#B)\u0003\u0006\u0005-\b\u0003BA;\u0005\u000fIAA!\u0003\u0002X\t1\u0011I\\=SK\u001a$\"A!\u0001\u0002\r=\u0014H-T:h+\t\u0011\tb\u0004\u0002\u0003\u0014\u0005\u0012!QC\u0001\u000269{\u0007%[7qY&\u001c\u0017\u000e\u001e\u0011Pe\u0012,'/\u001b8h7\u0012Z8JM?^A\u0019|WO\u001c3!i>\u0004#-^5mI\u0002\n\u0007eQ8mY&\u001c\u0018n\u001c8Qe>|g\rS1tQ6\u000b\u0007o\u0017\u0013|\u0017JjH\u0006\t\u0013|-JjXL\f\u0011Z_V\u0004S.Y=!o\u0006tG\u000f\t;pAU\u00048-Y:uAQ|\u0007%\u0019\u0011NCB\\Fe_&~Y\u0001\"3PV?^A\u0019L'o\u001d;!Ef\u00043-\u00197mS:<\u0007\u0005Y;og>\u0014H/\u001a3a]\u00059qN\u001d3Ng\u001e\u0004\u0013\u0001\u00024s_6,bA!\b\u0003&\t%B\u0003\u0002B\u0010\u0005c!BA!\t\u0003,A9\u00111\r\u0001\u0003$\t\u001d\u0002\u0003BA6\u0005K!q!a\u001cV\u0005\u0004\t\t\b\u0005\u0003\u0002l\t%BaBAC+\n\u0007\u0011\u0011\u000f\u0005\n\u0005[)\u0016\u0011!a\u0002\u0005_\t!\"\u001a<jI\u0016t7-\u001a\u00132!\u0019\t\u0019-a5\u0003$!9!1G+A\u0002\tU\u0012AA5u!\u0019\t9Ja\u000e\u0003<%!!\u0011HA*\u00051IE/\u001a:bE2,wJ\\2f!!\t)(a(\u0003$\t\u001d\u0012!B3naRLXC\u0002B!\u0005\u000f\u0012Y\u0005\u0006\u0003\u0003D\t5\u0003cBA2\u0001\t\u0015#\u0011\n\t\u0005\u0003W\u00129\u0005B\u0004\u0002pY\u0013\r!!\u001d\u0011\t\u0005-$1\n\u0003\b\u0003\u000b3&\u0019AA9\u0011%\u0011yEVA\u0001\u0002\b\u0011\t&\u0001\u0006fm&$WM\\2fII\u0002b!a1\u0002T\n\u0015\u0013A\u00038fo\n+\u0018\u000e\u001c3feV1!q\u000bB2\u0005O\"BA!\u0017\u0003lAA\u00111\rB.\u0005?\u0012I'\u0003\u0003\u0003^\u0005=#a\u0002\"vS2$WM\u001d\t\t\u0003k\nyJ!\u0019\u0003fA!\u00111\u000eB2\t\u001d\tyg\u0016b\u0001\u0003c\u0002B!a\u001b\u0003h\u00119\u0011QQ,C\u0002\u0005E\u0004cBA2\u0001\t\u0005$Q\r\u0005\n\u0005[:\u0016\u0011!a\u0002\u0005_\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\t\u0019-a5\u0003bU1!1\u000fB?\u0005\u0003#bA!\u001e\u0003\f\n5E\u0003\u0002B<\u0005\u000b\u0003\u0002\"a\u0019\u0003\\\te$1\u0011\t\t\u0003k\nyJa\u001f\u0003\u0000A!\u00111\u000eB?\t\u001d\ty\u0007\u0017b\u0001\u0003c\u0002B!a\u001b\u0003\u0002\u00129\u0011Q\u0011-C\u0002\u0005E\u0004cBA2\u0001\tm$q\u0010\u0005\n\u0005\u000fC\u0016\u0011!a\u0002\u0005\u0013\u000b!\"\u001a<jI\u0016t7-\u001a\u00135!\u0019\t\u0019-a5\u0003|!9\u0011q\u0016-A\u0002\u0005E\u0006bBA\\1\u0002\u0007\u0011\u0011X\u0001\u0012I\u00164\u0017-\u001e7u\u0019>\fGMR1di>\u0014XCAA]\u0003Y!WMZ1vYRLe.\u001b;jC2\u001c\u0015\r]1dSRLXCAAY\u0005Y!Um]3sS\u0006d\u0017N_1uS>tg)Y2u_JLXC\u0002BN\u0005O\u0013YkE\u0004\\\u0005\u000b\u0011iJa,\u0011\u0011\u0005]%q\u0014BR\u0005[KAA!)\u0002T\t9a)Y2u_JL\b\u0003CA;\u0003?\u0013)K!+\u0011\t\u0005-$q\u0015\u0003\b\u0003_Z&\u0019AA9!\u0011\tYGa+\u0005\u000f\u0005\u00155L1\u0001\u0002rA9\u00111\r\u0001\u0003&\n%\u0006\u0003BAb\u0005cKAAa-\u0002X\na1+\u001a:jC2L'0\u00192mK\u0006YA/\u00192mK2+gn\u001a;i\u00031!\u0018M\u00197f\u0019\u0016tw\r\u001e5!\u0003-aw.\u00193GC\u000e$xN\u001d\u0011\u0016\u0005\tu\u0006CBAb\u0003'\u0014)+A\u0005pe\u0012,'/\u001b8hAQA!1\u0019Bd\u0005\u0013\u0014Y\rE\u0004\u0003Fn\u0013)K!+\u000e\u0003ECqA!.c\u0001\u0004\t\t\fC\u0004\u00028\n\u0004\r!!/\t\u000f\u0005\u0005(\r1\u0001\u0003>\u0006aaM]8n'B,7-\u001b4jGR!!Q\u0016Bi\u0011\u001d\u0011\u0019d\u0019a\u0001\u0005'\u0004b!a&\u00038\t\rVC\u0001Bl!!\t\u0019Ga\u0017\u0003$\n5\u0006fB.\u0003\\\n\u0005(1\u001d\t\u0005\u0003k\u0012i.\u0003\u0003\u0003`\u0006]#\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0011aB2p[B\f'/Z\u000b\u0007\u0005S\u0014)\u0010\" \u0015\u0011\t-(q\u001fB~\u0005\u007f$B!!-\u0003n\"9!q^3A\u0004\tE\u0018aA8sIB1\u00111YAj\u0005g\u0004B!a\u001b\u0003v\u00129\u0011qN3C\u0002\u0005E\u0004b\u0002B}K\u0002\u0007!1_\u0001\u0004W\u0016L\bb\u0002B\u007fK\u0002\u0007\u0011\u0011W\u0001\u0005Q\u0006\u001c\b\u000eC\u0004\u0004\u0002\u0015\u0004\raa\u0001\u0002\t9|G-\u001a\t\t\u0005\u000b\f\u0019Ca=\u0005|\t1A\n\u0014(pI\u0016,ba!\u0003\u0004z\u0012\u001d1\u0003BA\u0012\u0007\u0017\u00012A!2j\u0005\u0011qu\u000eZ3\u0014\u0007%\u0014)\u0001\u0006\u0002\u0004\f%\"\u0011.a\tl+\u0019\u00199b!\b\u0004<M\u00191na\u0003\u0016\u0005\rm\u0001\u0003BA6\u0007;!q!a\u001cl\u0005\u0004\t\t(A\u0004lKf|F%Z9\u0015\t\r\r2\u0011\u0006\t\u0005\u0003k\u001a)#\u0003\u0003\u0004(\u0005]#\u0001B+oSRD\u0011ba\u000bn\u0003\u0003\u0005\raa\u0007\u0002\u0007a$\u0013'\u0001\u0003lKf\u0004\u0013\u0001\u00035bg\"|F%Z9\u0015\t\r\r21\u0007\u0005\n\u0007W\u0001\u0018\u0011!a\u0001\u0003c\u000bQ\u0001[1tQ\u0002*\"a!\u000f\u0011\t\u0005-41\b\u0003\b\u0003\u000b['\u0019AA9\u0003%1\u0018\r\\;f?\u0012*\u0017\u000f\u0006\u0003\u0004$\r\u0005\u0003\"CB\u0016g\u0006\u0005\t\u0019AB\u001d\u0003\u00191\u0018\r\\;fA\u0005\u0019!/\u001a3\u0016\u0005\r%\u0003\u0003BA;\u0007\u0017JAa!\u0014\u0002X\t9!i\\8mK\u0006t\u0017a\u0002:fI~#S-\u001d\u000b\u0005\u0007G\u0019\u0019\u0006C\u0005\u0004,Y\f\t\u00111\u0001\u0004J\u0005!!/\u001a3!\u0003\u0011aWM\u001a;\u0016\u0005\rm\u0003c\u0002BcW\u000em1\u0011H\u0001\tY\u00164Go\u0018\u0013fcR!11EB1\u0011%\u0019Y#_A\u0001\u0002\u0004\u0019Y&A\u0003mK\u001a$\b%A\u0003sS\u001eDG/A\u0005sS\u001eDGo\u0018\u0013fcR!11EB6\u0011%\u0019Y\u0003`A\u0001\u0002\u0004\u0019Y&\u0001\u0004sS\u001eDG\u000fI\u0001\u0007a\u0006\u0014XM\u001c;\u0002\u0015A\f'/\u001a8u?\u0012*\u0017\u000f\u0006\u0003\u0004$\rU\u0004\"CB\u0016\u007f\u0006\u0005\t\u0019AB.\u0003\u001d\u0001\u0018M]3oi\u0002\"\u0002ca\u0017\u0004|\ru4qPBA\u0007\u0007\u001b)ia\"\t\u0011\te\u00181\u0001a\u0001\u00077A\u0001B!@\u0002\u0004\u0001\u0007\u0011\u0011\u0017\u0005\t\u0005C\f\u0019\u00011\u0001\u0004:!A1QIA\u0002\u0001\u0004\u0019I\u0005\u0003\u0005\u0004X\u0005\r\u0001\u0019AB.\u0011!\u0019)'a\u0001A\u0002\rm\u0003\u0002CB8\u0003\u0007\u0001\raa\u0017\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"a!$\u0011\t\r=5q\u0013\b\u0005\u0007#\u001b\u0019\n\u0005\u0003\u0002H\u0006]\u0013\u0002BBK\u0003/\na\u0001\u0015:fI\u00164\u0017\u0002BBM\u00077\u0013aa\u0015;sS:<'\u0002BBK\u0003/\nqaZ3u\u001d>$W\r\u0006\u0004\u0004\"\u000e\u001d61\u0016\u000b\u0005\u00077\u001a\u0019\u000b\u0003\u0005\u0003p\u0006\u001d\u00019ABS!\u0019\t\u0019-a5\u0004\u001c!A1\u0011VA\u0004\u0001\u0004\u0019Y\"A\u0001l\u0011!\u0019i+a\u0002A\u0002\u0005E\u0016!\u00015)\t\u0005\u001d1\u0011\u0017\t\u0005\u0007g\u001bI,\u0004\u0002\u00046*!1qWA,\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0007w\u001b)LA\u0004uC&d'/Z2\u0002\u000f\u0019|'/Z1dQV!1\u0011YBi)\u0011\u0019\u0019ca1\t\u0011\r\u0015\u0017\u0011\u0002a\u0001\u0007\u000f\f\u0011A\u001a\t\t\u0003k\u001aIm!4\u0004P&!11ZA,\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0005\u0002v\u0005}51DB\u001d!\u0011\tYg!5\u0005\u0011\rM\u0017\u0011\u0002b\u0001\u0003c\u0012\u0011!V\u0001\rM>\u0014X-Y2i\u000b:$(/_\u000b\u0005\u00073\u001c)\u000f\u0006\u0003\u0004$\rm\u0007\u0002CBc\u0003\u0017\u0001\ra!8\u0011\u0015\u0005U4q\\B\u000e\u0007s\u0019\u0019/\u0003\u0003\u0004b\u0006]#!\u0003$v]\u000e$\u0018n\u001c83!\u0011\tYg!:\u0005\u0011\rM\u00171\u0002b\u0001\u0003c\n1BZ8sK\u0006\u001c\u0007NT8eKV!11^Bz)\u0011\u0019\u0019c!<\t\u0011\r\u0015\u0017Q\u0002a\u0001\u0007_\u0004\u0002\"!\u001e\u0004J\u000em3\u0011\u001f\t\u0005\u0003W\u001a\u0019\u0010\u0002\u0005\u0004T\u00065!\u0019AA9+\t\u00199\u0010\u0005\u0003\u0002l\reH\u0001CA8\u0003G\u0011\r!!\u001d\u0015\t\r\r2Q \u0005\u000b\u0007W\t9#!AA\u0002\r]H\u0003BB\u0012\t\u0003A!ba\u000b\u0002.\u0005\u0005\t\u0019AAY+\t!)\u0001\u0005\u0003\u0002l\u0011\u001dA\u0001CAC\u0003G\u0011\r!!\u001d\u0015\t\r\rB1\u0002\u0005\u000b\u0007W\t\u0019$!AA\u0002\u0011\u0015\u0011\u0001\u00028fqR,\"\u0001\"\u0005\u0011\u0011\t\u0015\u00171EB|\t\u000b\t\u0001B\\3yi~#S-\u001d\u000b\u0005\u0007G!9\u0002\u0003\u0006\u0004,\u0005e\u0012\u0011!a\u0001\t#\tQA\\3yi\u0002\"\"\u0002\"\u0005\u0005\u001e\u0011}A\u0011\u0005C\u0012\u0011!\u0011I0!\u0010A\u0002\r]\b\u0002\u0003B\u007f\u0003{\u0001\r!!-\t\u0011\t\u0005\u0018Q\ba\u0001\t\u000bA\u0001\u0002\"\u0004\u0002>\u0001\u0007A\u0011\u0003\u000b\u0003\tO\u0001B\u0001\"\u000b\u000545\u0011A1\u0006\u0006\u0005\t[!y#\u0001\u0003mC:<'B\u0001C\u0019\u0003\u0011Q\u0017M^1\n\t\reE1F\u0001\u0003KF$ba!\u0013\u0005:\u0011u\u0002\u0002\u0003C\u001e\u0003\u0003\u0002\r!a\u001f\u0002\u0003\u0005D\u0001\u0002b\u0010\u0002B\u0001\u0007\u00111P\u0001\u0002ER1A1\tC%\t\u0017\"B\u0001\"\u0005\u0005F!A!q^A\"\u0001\b!9\u0005\u0005\u0004\u0002D\u0006M7q\u001f\u0005\t\u0007S\u000b\u0019\u00051\u0001\u0004x\"A1QVA\"\u0001\u0004\t\t\f\u000b\u0003\u0002D\rEV\u0003\u0002C)\t7\"Baa\t\u0005T!A1QYA#\u0001\u0004!)\u0006\u0005\u0005\u0002v\r%Gq\u000bC-!!\t)(a(\u0004x\u0012\u0015\u0001\u0003BA6\t7\"\u0001ba5\u0002F\t\u0007\u0011\u0011\u000f\u0015\u0005\u0003\u000b\u001a\t,\u0006\u0003\u0005b\u0011%D\u0003BB\u0012\tGB\u0001b!2\u0002H\u0001\u0007AQ\r\t\u000b\u0003k\u001ayna>\u0005\u0006\u0011\u001d\u0004\u0003BA6\tS\"\u0001ba5\u0002H\t\u0007\u0011\u0011\u000f\u0015\u0005\u0003\u000f\u001a\t,\u0006\u0003\u0005p\u0011]D\u0003BB\u0012\tcB\u0001b!2\u0002J\u0001\u0007A1\u000f\t\t\u0003k\u001aI\r\"\u0005\u0005vA!\u00111\u000eC<\t!\u0019\u0019.!\u0013C\u0002\u0005E\u0004\u0006BA%\u0007c\u0003B!a\u001b\u0005~\u00119\u0011QQ3C\u0002\u0005E\u0004fA3\u0005\u0002B!11\u0017CB\u0013\u0011!)i!.\u0003\rUtWo]3eQ\r)G\u0011\u0012\t\u0005\u0003k\"Y)\u0003\u0003\u0005\u000e\u0006]#AB5oY&tW-\u0006\u0004\u0005\u0012\u0012mEq\u0015\u000b\t\t'#i\nb(\u0005\"R!\u0011\u0011\u0017CK\u0011\u001d\u0011yO\u001aa\u0002\t/\u0003b!a1\u0002T\u0012e\u0005\u0003BA6\t7#q!a\u001cg\u0005\u0004\t\t\bC\u0004\u0003z\u001a\u0004\r\u0001\"'\t\u000f\tuh\r1\u0001\u00022\"91\u0011\u00014A\u0002\u0011\r\u0006c\u0002BcW\u0012eEQ\u0015\t\u0005\u0003W\"9\u000bB\u0004\u0002\u0006\u001a\u0014\r!!\u001d)\u0007\u0019$I)\u0001\tue\u0016,\u0017NZ=UQJ,7\u000f[8mIV\u0011AqV\b\u0003\tck\u0012\u0001C\u0001\u0012iJ,W-\u001b4z)\"\u0014Xm\u001d5pY\u0012\u0004\u0013\u0001\u00027fC\u001a,b\u0001\"/\u0005@\u0012\u0015G\u0003\u0004C^\t\u0013$Y\r\"4\u0005P\u0012E\u0007c\u0002BcW\u0012uF1\u0019\t\u0005\u0003W\"y\f\u0002\u0005\u0005B\u0006=!\u0019AA9\u0005\u0005\t\u0005\u0003BA6\t\u000b$\u0001\u0002b2\u0002\u0010\t\u0007\u0011\u0011\u000f\u0002\u0002\u0005\"A!\u0011`A\b\u0001\u0004!i\f\u0003\u0005\u0003~\u0006=\u0001\u0019AAY\u0011!\u0011\t/a\u0004A\u0002\u0011\r\u0007\u0002CB#\u0003\u001f\u0001\ra!\u0013\t\u0011\r=\u0014q\u0002a\u0001\twCC!a\u0004\u0005\n\u0006qQ.\u001b8O_\u0012,gj\u001c8Ok2dWC\u0002Cm\t?$\u0019\u000f\u0006\u0003\u0005\\\u0012\u0015\bc\u0002BcW\u0012uG\u0011\u001d\t\u0005\u0003W\"y\u000e\u0002\u0005\u0005B\u0006E!\u0019AA9!\u0011\tY\u0007b9\u0005\u0011\u0011\u001d\u0017\u0011\u0003b\u0001\u0003cB\u0001b!\u0001\u0002\u0012\u0001\u0007A1\u001c\u0015\u0005\u0003#\u0019\t,A\u0005tk\u000e\u001cWm]:peV1AQ\u001eCz\to$B\u0001b<\u0005zB9!QY6\u0005r\u0012U\b\u0003BA6\tg$\u0001\u0002\"1\u0002\u0014\t\u0007\u0011\u0011\u000f\t\u0005\u0003W\"9\u0010\u0002\u0005\u0005H\u0006M!\u0019AA9\u0011!\u0019\t!a\u0005A\u0002\u0011=(a\u0004*C\u001d>$Wm]%uKJ\fGo\u001c:\u0016\r\u0011}X1BC\b'\u0011\t)\"\"\u0001\u0011\r\u0005]U1AC\u0004\u0013\u0011))!a\u0015\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\bc\u0002BcW\u0016%QQ\u0002\t\u0005\u0003W*Y\u0001\u0002\u0005\u0005B\u0006U!\u0019AA9!\u0011\tY'b\u0004\u0005\u0011\u0011\u001d\u0017Q\u0003b\u0001\u0003c\nA\u0001\u001e:fKB1\u00111YAj\u000b\u0013AC!!\u0007\u0005\u0002R!Q\u0011DC\u0010)\u0011)Y\"\"\b\u0011\u0011\t\u0015\u0017QCC\u0005\u000b\u001bA\u0001Ba<\u0002\u001c\u0001\u000fQ1\u0003\u0005\t\u000b#\tY\u00021\u0001\u0006\b\u0005Aa.\u001a=u\u001d>$W-A\u0004iCNtU\r\u001f;\u0015\u0005\u0015\u001d\u0001FBA\u0011\u000bS))\u0004\u0005\u0004\u0002v\u0015-RqF\u0005\u0005\u000b[\t9F\u0001\u0004uQJ|wo\u001d\t\u0005\u0003\u0007,\t$\u0003\u0003\u00064\u0005]'A\u0006(p'V\u001c\u0007.\u00127f[\u0016tG/\u0012=dKB$\u0018n\u001c82\u000fy\u0019i)b\u000e\u0006^EJ1%\"\u000f\u0006B\u0015MS1I\u000b\u0005\u000bw)i$\u0006\u0002\u0004\u000e\u0012AQqHA.\u0005\u0004)IEA\u0001U\u0013\u0011)\u0019%\"\u0012\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132\u0015\u0011)9%a\u0016\u0002\rQD'o\\<t#\u0011\t\u0019(b\u0013\u0011\t\u00155Sq\n\b\u0005\u0003k\ny-\u0003\u0003\u0006R\u0005]'!\u0003+ie><\u0018M\u00197fc%\u0019SQKC,\u000b3*9E\u0004\u0003\u0002v\u0015]\u0013\u0002BC$\u0003/\ntAIA;\u0003/*YFA\u0003tG\u0006d\u0017-M\u0002'\u000b_\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!b\u0019\u0011\t\u0011%RQM\u0005\u0005\u000bO\"YC\u0001\u0004PE*,7\r\u001e\u0015\b#\nm'\u0011\u001dBrQ\u001d\u0001&1\u001cBq\u0005G\u0004\u0002\"!@\u0002$\u0005%\u0014\u0011Q\u00016g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,GeQ8mY&\u001c\u0018n\u001c8Qe>|g\rS1tQ6\u000b\u0007\u000f\n\u0013uC\ndW\r\u0005\u0004\u0002v\u0015MTqO\u0005\u0005\u000bk\n9FA\u0003BeJ\f\u0017\u0010E\u0002\u0002~&\f\u0011h]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\r{G\u000e\\5tS>t\u0007K]8pM\"\u000b7\u000f['ba\u0012\"C\u000f\u001b:fg\"|G\u000eZ\u0001<g\u000e\fG.\u0019\u0013d_2dWm\u0019;j_:$S.\u001e;bE2,GeQ8mY&\u001c\u0018n\u001c8Qe>|g\rS1tQ6\u000b\u0007\u000f\n\u0013d_:$XM\u001c;TSj,\u0017\u0001B:ju\u0016\f1h]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\r{G\u000e\\5tS>t\u0007K]8pM\"\u000b7\u000f['ba\u0012\"3m\\7qkR,\u0007*Y:i)\u0011\t\t,b!\t\u000f\u0015\u0015U\u00021\u0001\u0002j\u0005\tq\u000eK\u0002\u000e\t\u0013\u000bQg]2bY\u0006$3m\u001c7mK\u000e$\u0018n\u001c8%[V$\u0018M\u00197fI\r{G\u000e\\5tS>t\u0007K]8pM\"\u000b7\u000f['ba\u0012\"\u0013N\u001c3fqR!\u0011\u0011WCG\u0011\u001d\u0011iP\u0004a\u0001\u0003cC3A\u0004CE)\u0011)\u0019*b)+\t\u0005MUQS\u0016\u0003\u000b/\u0003B!\"'\u0006 6\u0011Q1\u0014\u0006\u0005\u000b;\u001b),A\u0005v]\u000eDWmY6fI&!Q\u0011UCN\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0005\b\u000bK{\u0001\u0019ACT\u0003\u0011\u0019w\u000e\u001c7+\t\u0015%VQ\u0013\t\u0007\u0003/\u00139$!(\u0002%9,wo\u00159fG&4\u0017n\u0019\"vS2$WM]\u000b\u0003\u000b_SC!\"-\u0006\u0016BA\u00111\rB.\u0003;\u000b\u0019*\u0006\u0002\u0002\u0014\u0006A1m\u001c8uC&t7\u000f\u0006\u0003\u0004J\u0015e\u0006b\u0002B}%\u0001\u0007\u0011\u0011N\u0001\u0004O\u0016$H\u0003BC`\u000b\u000b\u0004b!!\u001e\u0006B\u0006\u0005\u0015\u0002BCb\u0003/\u0012aa\u00149uS>t\u0007b\u0002B}'\u0001\u0007\u0011\u0011N\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0003\u0003+Y\rC\u0004\u0003zR\u0001\r!!\u001b)\u000bQ)I#b42\u000fy\u0019i)\"5\u0006XFJ1%\"\u000f\u0006B\u0015MW1I\u0019\nG\u0015USqKCk\u000b\u000f\ntAIA;\u0003/*Y&M\u0002'\u000b_\t\u0011bZ3u\u001fJ,En]3\u0016\t\u0015uW\u0011\u001d\u000b\u0007\u000b?,9/\";\u0011\t\u0005-T\u0011\u001d\u0003\b\u000bG,\"\u0019ACs\u0005\t1\u0016'\u0005\u0003\u0002\u0002\u0006m\u0004b\u0002B}+\u0001\u0007\u0011\u0011\u000e\u0005\t\u000bW,B\u00111\u0001\u0006n\u00069A-\u001a4bk2$\bCBA;\u000b_,y.\u0003\u0003\u0006r\u0006]#\u0001\u0003\u001fcs:\fW.\u001a \u0002\u0011\u0019Lg\u000e\u001a(pI\u0016$B!b\u001e\u0006x\"9Q\u0011 \fA\u0002\u0005%\u0014\u0001B3mK6D3A\u0006CE\u0003!\u0019\u0018N_3IS:$H\u0003BB\u0012\r\u0003Aq!\" \u0018\u0001\u0004\t\t,\u0001\u0004va\u0012\fG/\u001a\u000b\u0007\u0007G19A\"\u0003\t\u000f\te\b\u00041\u0001\u0002j!9!\u0011\u001d\rA\u0002\u0005\u0005\u0015a\u00019viR1Qq\u0018D\b\r#AqA!?\u001a\u0001\u0004\tI\u0007C\u0004\u0003bf\u0001\r!!!\u0002\r\u0005$Gm\u00148f)\u001119B\"\u0007\u000e\u0003\u0001Aq!\"?\u001b\u0001\u0004\ti*\u0001\u0003qkR\u0004D\u0003\u0003D\u0010\rK19C\"\u000b\u0011\r\u0005Ud\u0011EAA\u0013\u00111\u0019#a\u0016\u0003\tM{W.\u001a\u0005\b\u0005s\\\u0002\u0019AA5\u0011\u001d\u0011\to\u0007a\u0001\u0003\u0003CqAb\u000b\u001c\u0001\u0004\u0019I%\u0001\u0004hKR|E\u000e\u001a\u0015\u00047\u0011%\u0015\u0001N:dC2\fGeY8mY\u0016\u001cG/[8oI5,H/\u00192mK\u0012\u001au\u000e\u001c7jg&|g\u000e\u0015:p_\u001aD\u0015m\u001d5NCB$C\u0005];uaQaaq\u0004D\u001a\rk19D\"\u000f\u0007<!9!\u0011 \u000fA\u0002\u0005%\u0004b\u0002Bq9\u0001\u0007\u0011\u0011\u0011\u0005\b\rWa\u0002\u0019AB%\u0011\u001d\u0011i\u0010\ba\u0001\u0003cCqA\"\u0010\u001d\u0001\u0004\t\t,A\u0002jIb\fq\u0001\u001e:fK&4\u0017\u0010\u0006\u0004\u0004$\u0019\rc\u0011\n\u0005\b\r\u000bj\u0002\u0019\u0001D$\u0003\ryG\u000e\u001a\t\u0004\r/A\u0001b\u0002D\u001f;\u0001\u0007\u0011\u0011W\u0001\u0007C\u0012$\u0017\t\u001c7\u0015\t\u0019]aq\n\u0005\b\r#r\u0002\u0019ACU\u0003\tA8/A\u0004sK6|g/\u001a\u0019\u0015\t\u0005mdq\u000b\u0005\b\u000bs|\u0002\u0019AA5\u0005-i\u0015\r]%uKJ\fGo\u001c:\u0016\t\u0019uc1M\n\u0004A\u0019}\u0003CBAL\u000b\u00071\t\u0007\u0005\u0003\u0002l\u0019\rDa\u0002D3A\t\u0007\u0011\u0011\u000f\u0002\u0002%R\u0011a\u0011\u000e\t\u0006\r/\u0001c\u0011M\u0001\bKb$(/Y2u)\u00111\tGb\u001c\t\u000f\r\u0005!\u00051\u0001\u0007HQ!a\u0011\rD:\u0011\u001d\u0019\ta\ta\u0001\rk\u00022Ab\u0006\b\u0003\u0005I\u0017a\u00017f]R\u0011a\u0011M\u0001\rW\u0016L8/\u0013;fe\u0006$xN]\u000b\u0003\r\u0003\u0003b!a&\u0007\u0004\u0006%\u0014\u0002\u0002DC\u0003'\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tSR,'/\u0019;peV\u0011a1\u0012\t\u0007\u0003/3\u0019)!(\u0002sM\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\rJ\"pY2L7/[8o!J|wN\u001a%bg\"l\u0015\r\u001d\u0013%OJ|w\u000fV1cY\u0016$Baa\t\u0007\u0012\"9a1S\u0016A\u0002\u0005E\u0016A\u00028fo2,g.\u0001\u0007sK\u0006dGn\\2UC\ndW\r\u0006\u0003\u0004$\u0019e\u0005b\u0002DJY\u0001\u0007\u0011\u0011\u0017\u0015\u0004Y\u0011%\u0015aC:qY&$()^2lKR$\"ba\t\u0007\"\u001a\rfq\u0015DV\u0011\u001d)\t\"\fa\u0001\u000boBqA\"*.\u0001\u0004\t\t,A\u0005m_^\u0014UoY6fi\"9a\u0011V\u0017A\u0002\u0005E\u0016A\u00035jO\"\u0014UoY6fi\"9aQV\u0017A\u0002\u0005E\u0016\u0001B7bg.D3!\fCE\u0003m\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013D_2d\u0017n]5p]B\u0013xn\u001c4ICNDW*\u00199%IM\u0004H.\u001b;Ck\u000e\\W\r\u001e\u000b\u000b\u0007G1)L\"/\u0007<\u001au\u0006b\u0002D\\]\u0001\u0007aqI\u0001\u0005Y&\u001cH\u000fC\u0004\u0007&:\u0002\r!!-\t\u000f\u0019%f\u00061\u0001\u00022\"9aQ\u0016\u0018A\u0002\u0005EFCCB\u0012\r\u00034\u0019M\"2\u0007H\"9Q\u0011C\u0018A\u0002\u0019U\u0004b\u0002DS_\u0001\u0007\u0011\u0011\u0017\u0005\b\rS{\u0003\u0019AAY\u0011\u001d1ik\fa\u0001\u0003c\u000bA\u0002^1cY\u0016\u001c\u0016N_3G_J$B!!-\u0007N\"9aq\u001a\u0019A\u0002\u0005E\u0016\u0001C2ba\u0006\u001c\u0017\u000e^=\u0002yM\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\rJ\"pY2L7/[8o!J|wN\u001a%bg\"l\u0015\r\u001d\u0013%]\u0016<H\u000b\u001b:fg\"|G\u000e\u001a\u000b\u0005\u0003c3)\u000eC\u0004\u0006~E\u0002\r!!-\u0002\u000b\rdW-\u0019:\u0015\u0005\r\r\u0012A\u0002:f[>4X\r\u0006\u0003\u0006@\u001a}\u0007b\u0002B}g\u0001\u0007\u0011\u0011N\u0001\fgV\u0014GO]1di>sW\r\u0006\u0003\u0007\u0018\u0019\u0015\bbBC}i\u0001\u0007\u0011\u0011N\u0001\nW:|wO\\*ju\u0016\fq![:F[B$\u00180\u0006\u0003\u0007n\u001aUH\u0003BB\u0012\r_Dqa!28\u0001\u00041\t\u0010\u0005\u0005\u0002v\r%\u0017Q\u0014Dz!\u0011\tYG\">\u0005\u000f\rMwG1\u0001\u0002rU!a\u0011`D\u0001)\u0011\u0019\u0019Cb?\t\u000f\r\u0015\u0007\b1\u0001\u0007~BQ\u0011QOBp\u0003S\n\tIb@\u0011\t\u0005-t\u0011\u0001\u0003\b\u0007'D$\u0019AA9)\t\u0011)!A\u0005dY\u0006\u001c8OT1nKV\u0011AqE\u0001\u0010O\u0016$xJ]#mg\u0016,\u0006\u000fZ1uKR1\u0011\u0011QD\u0007\u000f\u001fAqA!?<\u0001\u0004\tI\u0007\u0003\u0005\b\u0012m\"\t\u0019AD\n\u00031!WMZ1vYR4\u0016\r\\;f!\u0019\t)(b<\u0002\u0002\u0006\u0019Q.\u00199\u0016\r\u001deq\u0011ED\u0014)\u00119Yb\"\u000f\u0015\t\u001duq1\u0006\t\b\u0003G\u0002qqDD\u0013!\u0011\tYg\"\t\u0005\u000f\u001d\rBH1\u0001\u0002r\t\u00111J\r\t\u0005\u0003W:9\u0003B\u0004\b*q\u0012\r!!\u001d\u0003\u0005Y\u0013\u0004bBAqy\u0001\u000fqQ\u0006\t\u0007\u0003\u0007\f\u0019nb\b)\u0011\u001d-r\u0011GD\u001c\u0005'\u0001Baa-\b4%!qQGB[\u0005AIW\u000e\u001d7jG&$hj\u001c;G_VtG-A\u0002ng\u001eDqa!2=\u0001\u00049Y\u0004\u0005\u0005\u0002v\r%\u0017QTD\u001f!!\t)(a(\b \u001d\u0015\u0012a\u00024mCRl\u0015\r]\u000b\u0007\u000f\u0007:Yeb\u0014\u0015\t\u001d\u0015sq\u000b\u000b\u0005\u000f\u000f:\t\u0006E\u0004\u0002d\u00019Ie\"\u0014\u0011\t\u0005-t1\n\u0003\b\u000fGi$\u0019AA9!\u0011\tYgb\u0014\u0005\u000f\u001d%RH1\u0001\u0002r!9\u0011\u0011]\u001fA\u0004\u001dM\u0003CBAb\u0003'<I\u0005\u000b\u0005\bR\u001dErq\u0007B\n\u0011\u001d\u0019)-\u0010a\u0001\u000f3\u0002\u0002\"!\u001e\u0004J\u0006uu1\f\t\u0007\u0003/\u00139d\"\u0018\u0011\u0011\u0005U\u0014qTD%\u000f\u001b\nqaY8mY\u0016\u001cG/\u0006\u0004\bd\u001d-tq\u000e\u000b\u0005\u000fK:9\b\u0006\u0003\bh\u001dE\u0004cBA2\u0001\u001d%tQ\u000e\t\u0005\u0003W:Y\u0007B\u0004\b$y\u0012\r!!\u001d\u0011\t\u0005-tq\u000e\u0003\b\u000fSq$\u0019AA9\u0011\u001d\t\tO\u0010a\u0002\u000fg\u0002b!a1\u0002T\u001e%\u0004\u0006CD9\u000fc99Da\u0005\t\u000f\u001ded\b1\u0001\b|\u0005\u0011\u0001O\u001a\t\t\u0003k:i(!(\b\u0002&!qqPA,\u0005=\u0001\u0016M\u001d;jC24UO\\2uS>t\u0007\u0003CA;\u0003?;Ig\"\u001c\u0002\r\r|gnY1u+\u001199i\"$\u0015\t\u001d%uq\u0012\t\b\u0003G\u0002\u0011\u0011NDF!\u0011\tYg\"$\u0005\u000f\u001d%rH1\u0001\u0006f\"9q\u0011S A\u0002\u001dM\u0015AB:vM\u001aL\u0007\u0010\u0005\u0004\u0002\u0018\n]rQ\u0013\t\t\u0003k\ny*!\u001b\b\f\u0006QA\u0005\u001d7vg\u0012\u0002H.^:\u0016\t\u001dmu\u0011\u0015\u000b\u0005\u000f;;\u0019\u000bE\u0004\u0002d\u0001\tIgb(\u0011\t\u0005-t\u0011\u0015\u0003\b\u000fS\u0001%\u0019ACs\u0011\u001d1\t\u0006\u0011a\u0001\u000fK\u0003b!a&\u00038\u001d\u001d\u0006\u0003CA;\u0003?\u000bIgb()\u0007\u0001#I)A\u0003%a2,8/\u0006\u0003\b0\u001eUF\u0003BDY\u000fo\u0003r!a\u0019\u0001\u0003S:\u0019\f\u0005\u0003\u0002l\u001dUFaBCr\u0003\n\u0007QQ\u001d\u0005\b\u000fs\u000b\u0005\u0019AD^\u0003\tYg\u000f\u0005\u0005\u0002v\u0005}\u0015\u0011NDZQ-\tuqXDc\u000f\u000f<Ym\"4\u0011\t\u0005Ut\u0011Y\u0005\u0005\u000f\u0007\f9F\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\bJ\u0006q4i\u001c8tS\u0012,'\u000f\t:fcVL'/\u001b8hA\u0005t\u0007%[7nkR\f'\r\\3!\u001b\u0006\u0004\be\u001c:!M\u0006dG\u000e\t2bG.\u0004Co\u001c\u0011NCBt3m\u001c8dCR\fQa]5oG\u0016\f#ab4\u0002\rIr\u0013g\r\u00181+\u00119\u0019n\"7\u0015\u0011\u001dUw1\\Dq\u000fK\u0004r!a\u0019\u0001\u0003S:9\u000e\u0005\u0003\u0002l\u001deGaBCr\u0005\n\u0007QQ\u001d\u0005\b\u000f;\u0014\u0005\u0019ADp\u0003\u0015)G.Z72!!\t)(a(\u0002j\u001d]\u0007bBDr\u0005\u0002\u0007qq\\\u0001\u0006K2,WN\r\u0005\b\u000fO\u0014\u0005\u0019ADu\u0003\u0015)G.Z7t!\u0019\t)hb;\b`&!qQ^A,\u0005)a$/\u001a9fCR,GM\u0010\u0015\f\u0005\u001e}vQYDy\u000f\u0017<i-\t\u0002\bt\u0006)Uk]3!W-\u0002s/\u001b;iA\u0005t\u0007%\u001a=qY&\u001c\u0017\u000e\u001e\u0011d_2dWm\u0019;j_:\u0004\u0013M]4v[\u0016tG\u000fI5ogR,\u0017\r\u001a\u0011pM\u0002Z\u0003e^5uQ\u00022\u0018M]1sON\fQ![:SK\u0012$Ba!\u0013\bz\"91\u0011A\"A\u0002\u0019U\u0004fA\"\u0005\n\u00069\u0011n\u001d\"mC\u000e\\G\u0003BB%\u0011\u0003Aqa!\u0001E\u0001\u00041)\bK\u0002E\t\u0013#\u0002\"!-\t\b!%\u00012\u0002\u0005\b\u0005s,\u0005\u0019AA5\u0011\u001d\u0011i0\u0012a\u0001\u0003cCqa!\u0001F\u0001\u000419\u0005K\u0002F\t\u0003C3!\u0012CE)!\t\t\fc\u0005\t\u0016!]\u0001b\u0002B}\r\u0002\u0007\u0011\u0011\u000e\u0005\b\u0005{4\u0005\u0019AAY\u0011\u001d\u0019\tA\u0012a\u0001\rkB3A\u0012CE\u0003IIgn]3si&sGo\\#ySN$\u0018N\\4\u0015\u001d\r%\u0003r\u0004E\u0012\u0011OAI\u0003c\u000b\t.!9\u0001\u0012E$A\u0002\u0019U\u0014!B0s_>$\bb\u0002E\u0013\u000f\u0002\u0007\u0011\u0011W\u0001\u0007EV\u001c7.\u001a;\t\u000f\tex\t1\u0001\u0002j!9!Q`$A\u0002\u0005E\u0006b\u0002Bq\u000f\u0002\u0007\u0011\u0011\u0011\u0005\b\u0011_9\u0005\u0019\u0001D;\u0003\u0005A\bfA$\u00042\u00061\u0011N\\:feR$Bb!\u0013\t8!e\u00022\bE\u001f\u0011\u007fAq!\"\u0005I\u0001\u00041)\bC\u0004\t&!\u0003\r!!-\t\u000f\te\b\n1\u0001\u0002j!9!Q %A\u0002\u0005E\u0006b\u0002Bq\u0011\u0002\u0007\u0011\u0011Q\u0001\u000fM&D\u0018I\u001a;fe&s7/\u001a:u)\u00191)\b#\u0012\tH!9\u0001\u0012E%A\u0002\u0019U\u0004bBB\u0001\u0013\u0002\u0007aQO\u0001\u0007I\u0016dW\r^3\u0015\u0015\u0005m\u0004R\nE(\u0011#B\u0019\u0006C\u0004\t\")\u0003\rA\"\u001e\t\u000f!\u0015\"\n1\u0001\u00022\"9!\u0011 &A\u0002\u0005%\u0004b\u0002B\u007f\u0015\u0002\u0007\u0011\u0011W\u0001\u000fM&D\u0018I\u001a;fe\u0012+G.\u001a;f)!1)\b#\u0017\t\\!u\u0003b\u0002E\u0011\u0017\u0002\u0007aQ\u000f\u0005\b\u0007\u0003Y\u0005\u0019\u0001D;\u0011\u001d\u0019yg\u0013a\u0001\rk\n!B]8uCR,G*\u001a4u)\u00191)\bc\u0019\tf!9\u0001\u0012\u0005'A\u0002\u0019U\u0004b\u0002E\u0018\u0019\u0002\u0007aQ\u000f\u0015\u0004\u0019\u0012%\u0015a\u0003:pi\u0006$XMU5hQR$bA\"\u001e\tn!=\u0004b\u0002E\u0011\u001b\u0002\u0007aQ\u000f\u0005\b\u0011_i\u0005\u0019\u0001D;Q\riE\u0011R\u0001\u000biJ\fgn\u001d9mC:$H\u0003\u0003D;\u0011oBI\b# \t\u000f!\u0005b\n1\u0001\u0007v!9\u00012\u0010(A\u0002\u0019U\u0014A\u0001;p\u0011\u001d\u0011IB\u0014a\u0001\rk\n\u0011B\u001a:p[:{G-Z:\u0015\r\u0019U\u00042\u0011ED\u0011\u001d1\tf\u0014a\u0001\u0011\u000b\u0003b!a&\u0007\u0004\u0016]\u0004bBC?\u001f\u0002\u0007\u0011\u0011\u0017"
)
public final class CollisionProofHashMap extends AbstractMap implements StrictOptimizedMapOps {
   private final double loadFactor;
   public final Ordering scala$collection$mutable$CollisionProofHashMap$$ordering;
   public Node[] scala$collection$mutable$CollisionProofHashMap$$table;
   public int scala$collection$mutable$CollisionProofHashMap$$threshold;
   public int scala$collection$mutable$CollisionProofHashMap$$contentSize;

   public static int defaultInitialCapacity() {
      CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
      return 16;
   }

   public static double defaultLoadFactor() {
      CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
      return (double)0.75F;
   }

   public static Builder newBuilder(final int initialCapacity, final double loadFactor, final Ordering evidence$4) {
      CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
      return new GrowableBuilder(initialCapacity, loadFactor, evidence$4) {
         public void sizeHint(final int size) {
            ((CollisionProofHashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public static Builder newBuilder(final Ordering evidence$3) {
      return CollisionProofHashMap$.MODULE$.newBuilder(evidence$3);
   }

   public static CollisionProofHashMap from(final IterableOnce it, final Ordering evidence$1) {
      return CollisionProofHashMap$.MODULE$.from(it, evidence$1);
   }

   public IterableOps map(final Function1 f) {
      return StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return StrictOptimizedMapOps.collect$(this, pf);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   private final SortedMapFactory sortedMapFactory() {
      return CollisionProofHashMap$.MODULE$;
   }

   public int size() {
      return this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
   }

   public final int scala$collection$mutable$CollisionProofHashMap$$computeHash(final Object o) {
      int h = o == null ? 0 : o.hashCode();
      return h ^ h >>> 16;
   }

   public final int scala$collection$mutable$CollisionProofHashMap$$index(final int hash) {
      return hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
   }

   public CollisionProofHashMap fromSpecific(final IterableOnce coll) {
      return CollisionProofHashMap$.MODULE$.from(coll, this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   public Builder newSpecificBuilder() {
      return CollisionProofHashMap$.MODULE$.newBuilder(this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   public CollisionProofHashMap empty() {
      return new CollisionProofHashMap(this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   public boolean contains(final Object key) {
      int findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int findNode_hash = findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      Node var3 = this.scala$collection$mutable$CollisionProofHashMap$$table[findNode_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1];
      Object var10000 = var3 == null ? null : (var3 instanceof LLNode ? ((LLNode)var3).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering) : ((RBNode)var3).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering));
      var3 = null;
      return var10000 != null;
   }

   public Option get(final Object key) {
      int findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int findNode_hash = findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      Node var4 = this.scala$collection$mutable$CollisionProofHashMap$$table[findNode_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1];
      Some var10000 = (Some)(var4 == null ? null : (var4 instanceof LLNode ? ((LLNode)var4).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering) : ((RBNode)var4).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering)));
      var4 = null;
      Object var2 = var10000;
      if (var2 == null) {
         return None$.MODULE$;
      } else {
         var10000 = new Some;
         Object var10002;
         if (var2 instanceof LLNode) {
            var10002 = ((LLNode)var2).value();
         } else {
            if (!(var2 instanceof RBNode)) {
               throw new MatchError(var2);
            }

            var10002 = ((RBNode)var2).value();
         }

         var10000.<init>(var10002);
         return var10000;
      }
   }

   public Object apply(final Object key) throws NoSuchElementException {
      int findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int findNode_hash = findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      Node var4 = this.scala$collection$mutable$CollisionProofHashMap$$table[findNode_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1];
      Object var10000 = var4 == null ? null : (var4 instanceof LLNode ? ((LLNode)var4).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering) : ((RBNode)var4).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering));
      var4 = null;
      Object var2 = var10000;
      if (var2 == null) {
         return scala.collection.MapOps.default$(this, key);
      } else if (var2 instanceof LLNode) {
         return ((LLNode)var2).value();
      } else if (var2 instanceof RBNode) {
         return ((RBNode)var2).value();
      } else {
         throw new MatchError(var2);
      }
   }

   public Object getOrElse(final Object key, final Function0 default) {
      int findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int findNode_hash = findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ findNode_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      Node var5 = this.scala$collection$mutable$CollisionProofHashMap$$table[findNode_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1];
      Object var10000 = var5 == null ? null : (var5 instanceof LLNode ? ((LLNode)var5).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering) : ((RBNode)var5).getNode(key, findNode_hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering));
      var5 = null;
      Node nd = (Node)var10000;
      if (nd == null) {
         return default.apply();
      } else {
         return nd instanceof LLNode ? ((LLNode)nd).value() : ((RBNode)nd).value();
      }
   }

   private Node findNode(final Object elem) {
      int scala$collection$mutable$CollisionProofHashMap$$computeHash_h = elem == null ? 0 : elem.hashCode();
      int hash = scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      Node var3 = this.scala$collection$mutable$CollisionProofHashMap$$table[hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1];
      if (var3 == null) {
         return null;
      } else {
         return (Node)(var3 instanceof LLNode ? ((LLNode)var3).getNode(elem, hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering) : ((RBNode)var3).getNode(elem, hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering));
      }
   }

   public void sizeHint(final int size) {
      int target = this.tableSizeFor((int)((double)(size + 1) / this.loadFactor));
      if (target > this.scala$collection$mutable$CollisionProofHashMap$$table.length) {
         if (size == 0) {
            this.scala$collection$mutable$CollisionProofHashMap$$table = new Node[target];
            this.scala$collection$mutable$CollisionProofHashMap$$threshold = this.scala$collection$mutable$CollisionProofHashMap$$newThreshold(this.scala$collection$mutable$CollisionProofHashMap$$table.length);
         } else {
            this.scala$collection$mutable$CollisionProofHashMap$$growTable(target);
         }
      }
   }

   public void update(final Object key, final Object value) {
      boolean put0_getOld = false;
      if (this.scala$collection$mutable$CollisionProofHashMap$$contentSize + 1 >= this.scala$collection$mutable$CollisionProofHashMap$$threshold) {
         this.scala$collection$mutable$CollisionProofHashMap$$growTable(this.scala$collection$mutable$CollisionProofHashMap$$table.length * 2);
      }

      int put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int put0_hash = put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int put0_idx = put0_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      this.scala$collection$mutable$CollisionProofHashMap$$put0(key, value, put0_getOld, put0_hash, put0_idx);
   }

   public Option put(final Object key, final Object value) {
      boolean put0_getOld = true;
      if (this.scala$collection$mutable$CollisionProofHashMap$$contentSize + 1 >= this.scala$collection$mutable$CollisionProofHashMap$$threshold) {
         this.scala$collection$mutable$CollisionProofHashMap$$growTable(this.scala$collection$mutable$CollisionProofHashMap$$table.length * 2);
      }

      int put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int put0_hash = put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int put0_idx = put0_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      Some var3 = this.scala$collection$mutable$CollisionProofHashMap$$put0(key, value, put0_getOld, put0_hash, put0_idx);
      return (Option)(var3 == null ? None$.MODULE$ : var3);
   }

   public CollisionProofHashMap addOne(final Tuple2 elem) {
      Object var10000 = elem._1();
      Object var10001 = elem._2();
      boolean put0_getOld = false;
      Object put0_value = var10001;
      Object put0_key = var10000;
      if (this.scala$collection$mutable$CollisionProofHashMap$$contentSize + 1 >= this.scala$collection$mutable$CollisionProofHashMap$$threshold) {
         this.scala$collection$mutable$CollisionProofHashMap$$growTable(this.scala$collection$mutable$CollisionProofHashMap$$table.length * 2);
      }

      int put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h = put0_key == null ? 0 : put0_key.hashCode();
      int put0_hash = put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ put0_scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int put0_idx = put0_hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      this.scala$collection$mutable$CollisionProofHashMap$$put0(put0_key, put0_value, put0_getOld, put0_hash, put0_idx);
      return this;
   }

   private Some put0(final Object key, final Object value, final boolean getOld) {
      if (this.scala$collection$mutable$CollisionProofHashMap$$contentSize + 1 >= this.scala$collection$mutable$CollisionProofHashMap$$threshold) {
         this.scala$collection$mutable$CollisionProofHashMap$$growTable(this.scala$collection$mutable$CollisionProofHashMap$$table.length * 2);
      }

      int scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int hash = scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int idx = hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      return this.scala$collection$mutable$CollisionProofHashMap$$put0(key, value, getOld, hash, idx);
   }

   public Some scala$collection$mutable$CollisionProofHashMap$$put0(final Object key, final Object value, final boolean getOld, final int hash, final int idx) {
      Node var7 = this.scala$collection$mutable$CollisionProofHashMap$$table[idx];
      boolean var10000;
      if (var7 instanceof RBNode) {
         RBNode var8 = (RBNode)var7;
         var10000 = this.insert(var8, idx, key, hash, value);
      } else {
         LLNode old = (LLNode)var7;
         if (old == null) {
            this.scala$collection$mutable$CollisionProofHashMap$$table[idx] = new LLNode(key, hash, value, (LLNode)null);
         } else {
            int remaining = 8;
            LLNode prev = null;

            for(LLNode n = old; n != null && n.hash() <= hash && remaining > 0; --remaining) {
               if (n.hash() == hash && BoxesRunTime.equals(key, n.key())) {
                  Object old = n.value();
                  n.value_$eq(value);
                  if (getOld) {
                     return new Some(old);
                  }

                  return null;
               }

               prev = n;
               n = n.next();
            }

            if (remaining == 0) {
               this.treeify(old, idx);
               return this.scala$collection$mutable$CollisionProofHashMap$$put0(key, value, getOld, hash, idx);
            }

            if (prev == null) {
               this.scala$collection$mutable$CollisionProofHashMap$$table[idx] = new LLNode(key, hash, value, old);
            } else {
               prev.next_$eq(new LLNode(key, hash, value, prev.next()));
            }
         }

         var10000 = true;
      }

      boolean res = var10000;
      if (res) {
         ++this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
      }

      return res ? new Some((Object)null) : null;
   }

   private void treeify(final LLNode old, final int idx) {
      Node[] var10000 = this.scala$collection$mutable$CollisionProofHashMap$$table;
      CollisionProofHashMap$ var10002 = CollisionProofHashMap$.MODULE$;
      var10002 = (CollisionProofHashMap$)old.key();
      int var10003 = old.hash();
      Object var10004 = old.value();
      boolean scala$collection$mutable$CollisionProofHashMap$$leaf_red = false;
      Object scala$collection$mutable$CollisionProofHashMap$$leaf_value = var10004;
      int scala$collection$mutable$CollisionProofHashMap$$leaf_hash = var10003;
      Object scala$collection$mutable$CollisionProofHashMap$$leaf_key = var10002;
      RBNode var12 = new RBNode(scala$collection$mutable$CollisionProofHashMap$$leaf_key, scala$collection$mutable$CollisionProofHashMap$$leaf_hash, scala$collection$mutable$CollisionProofHashMap$$leaf_value, scala$collection$mutable$CollisionProofHashMap$$leaf_red, (RBNode)null, (RBNode)null, (RBNode)null);
      scala$collection$mutable$CollisionProofHashMap$$leaf_key = null;
      scala$collection$mutable$CollisionProofHashMap$$leaf_value = null;
      var10000[idx] = var12;

      for(LLNode n = old.next(); n != null; n = n.next()) {
         RBNode root = (RBNode)this.scala$collection$mutable$CollisionProofHashMap$$table[idx];
         this.insertIntoExisting(root, idx, n.key(), n.hash(), n.value(), root);
      }

   }

   public CollisionProofHashMap addAll(final IterableOnce xs) {
      int sizeHint_delta = this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
      Builder.sizeHint$(this, xs, sizeHint_delta);
      return (CollisionProofHashMap)Growable.addAll$(this, xs);
   }

   private Object remove0(final Object elem) {
      int scala$collection$mutable$CollisionProofHashMap$$computeHash_h = elem == null ? 0 : elem.hashCode();
      int hash = scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int idx = hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      boolean var4 = false;
      LLNode var5 = null;
      Node var6 = this.scala$collection$mutable$CollisionProofHashMap$$table[idx];
      if (var6 == null) {
         return Statics.pfMarker;
      } else if (var6 instanceof RBNode) {
         RBNode var7 = (RBNode)var6;
         Object v = this.delete(var7, idx, elem, hash);
         if (v != Statics.pfMarker) {
            --this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
         }

         return v;
      } else {
         if (var6 instanceof LLNode) {
            var4 = true;
            var5 = (LLNode)var6;
            if (var5.hash() == hash && BoxesRunTime.equals(var5.key(), elem)) {
               this.scala$collection$mutable$CollisionProofHashMap$$table[idx] = var5.next();
               --this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
               return var5.value();
            }
         }

         if (!var4) {
            throw new MatchError(var6);
         } else {
            LLNode prev = var5;

            for(LLNode next = var5.next(); next != null && next.hash() <= hash; next = next.next()) {
               if (next.hash() == hash && BoxesRunTime.equals(next.key(), elem)) {
                  prev.next_$eq(next.next());
                  --this.scala$collection$mutable$CollisionProofHashMap$$contentSize;
                  return next.value();
               }

               prev = next;
            }

            return Statics.pfMarker;
         }
      }
   }

   public Iterator keysIterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapIterator() {
            public Object extract(final LLNode node) {
               return node.key();
            }

            public Object extract(final RBNode node) {
               return node.key();
            }
         };
      }
   }

   public Iterator iterator() {
      if (this.isEmpty()) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new MapIterator() {
            public Tuple2 extract(final LLNode node) {
               return new Tuple2(node.key(), node.value());
            }

            public Tuple2 extract(final RBNode node) {
               return new Tuple2(node.key(), node.value());
            }
         };
      }
   }

   public void scala$collection$mutable$CollisionProofHashMap$$growTable(final int newlen) {
      int oldlen = this.scala$collection$mutable$CollisionProofHashMap$$table.length;
      this.scala$collection$mutable$CollisionProofHashMap$$table = (Node[])Arrays.copyOf(this.scala$collection$mutable$CollisionProofHashMap$$table, newlen);

      for(this.scala$collection$mutable$CollisionProofHashMap$$threshold = this.scala$collection$mutable$CollisionProofHashMap$$newThreshold(this.scala$collection$mutable$CollisionProofHashMap$$table.length); oldlen < newlen; oldlen *= 2) {
         for(int i = 0; i < oldlen; ++i) {
            Node old = this.scala$collection$mutable$CollisionProofHashMap$$table[i];
            if (old != null) {
               int splitBucket_highBucket = i + oldlen;
               if (old instanceof LLNode) {
                  LLNode var6 = (LLNode)old;
                  this.scala$collection$mutable$CollisionProofHashMap$$splitBucket(var6, i, splitBucket_highBucket, oldlen);
               } else {
                  if (!(old instanceof RBNode)) {
                     throw new MatchError(old);
                  }

                  RBNode var7 = (RBNode)old;
                  this.scala$collection$mutable$CollisionProofHashMap$$splitBucket(var7, i, splitBucket_highBucket, oldlen);
               }

               Object var8 = null;
               Object var9 = null;
            }
         }
      }

   }

   private void reallocTable(final int newlen) {
      this.scala$collection$mutable$CollisionProofHashMap$$table = new Node[newlen];
      this.scala$collection$mutable$CollisionProofHashMap$$threshold = this.scala$collection$mutable$CollisionProofHashMap$$newThreshold(this.scala$collection$mutable$CollisionProofHashMap$$table.length);
   }

   private void splitBucket(final Node tree, final int lowBucket, final int highBucket, final int mask) {
      if (tree instanceof LLNode) {
         LLNode var5 = (LLNode)tree;
         this.scala$collection$mutable$CollisionProofHashMap$$splitBucket(var5, lowBucket, highBucket, mask);
      } else if (tree instanceof RBNode) {
         RBNode var6 = (RBNode)tree;
         this.scala$collection$mutable$CollisionProofHashMap$$splitBucket(var6, lowBucket, highBucket, mask);
      } else {
         throw new MatchError(tree);
      }
   }

   public void scala$collection$mutable$CollisionProofHashMap$$splitBucket(final LLNode list, final int lowBucket, final int highBucket, final int mask) {
      LLNode preLow = new LLNode((Object)null, 0, (Object)null, (LLNode)null);
      LLNode preHigh = new LLNode((Object)null, 0, (Object)null, (LLNode)null);
      LLNode lastLow = preLow;
      LLNode lastHigh = preHigh;

      LLNode next;
      for(LLNode n = list; n != null; n = next) {
         next = n.next();
         if ((n.hash() & mask) == 0) {
            lastLow.next_$eq(n);
            lastLow = n;
         } else {
            lastHigh.next_$eq(n);
            lastHigh = n;
         }
      }

      lastLow.next_$eq((LLNode)null);
      if (list != preLow.next()) {
         this.scala$collection$mutable$CollisionProofHashMap$$table[lowBucket] = preLow.next();
      }

      if (preHigh.next() != null) {
         this.scala$collection$mutable$CollisionProofHashMap$$table[highBucket] = preHigh.next();
         lastHigh.next_$eq((LLNode)null);
      }
   }

   public void scala$collection$mutable$CollisionProofHashMap$$splitBucket(final RBNode tree, final int lowBucket, final int highBucket, final int mask) {
      int create_e = 0;
      IntRef lowCount = new IntRef(create_e);
      int create_e = 0;
      IntRef highCount = new IntRef(create_e);
      Function1 foreachNode_f = (n) -> {
         $anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$1(mask, highCount, lowCount, n);
         return BoxedUnit.UNIT;
      };
      if (tree == null) {
         throw null;
      } else {
         RBNode foreachNode_this = tree;

         while(true) {
            if (foreachNode_this.left() != null) {
               foreachNode_this.left().foreachNode(foreachNode_f);
            }

            if ((foreachNode_this.hash() & mask) != 0) {
               ++highCount.elem;
            } else {
               ++lowCount.elem;
            }

            if (foreachNode_this.right() == null) {
               Object var11 = null;
               foreachNode_f = null;
               if (highCount.elem != 0) {
                  if (lowCount.elem == 0) {
                     this.scala$collection$mutable$CollisionProofHashMap$$table[lowBucket] = null;
                     this.scala$collection$mutable$CollisionProofHashMap$$table[highBucket] = tree;
                     return;
                  }

                  this.scala$collection$mutable$CollisionProofHashMap$$table[lowBucket] = this.fromNodes((new RBNodesIterator(tree, this.scala$collection$mutable$CollisionProofHashMap$$ordering)).filterImpl((n) -> BoxesRunTime.boxToBoolean($anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$2(mask, n)), false), lowCount.elem);
                  this.scala$collection$mutable$CollisionProofHashMap$$table[highBucket] = this.fromNodes((new RBNodesIterator(tree, this.scala$collection$mutable$CollisionProofHashMap$$ordering)).filterImpl((n) -> BoxesRunTime.boxToBoolean($anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$3(mask, n)), false), highCount.elem);
                  return;
               }

               return;
            }

            RBNode var10000 = foreachNode_this.right();
            foreachNode_f = foreachNode_f;
            foreachNode_this = var10000;
         }
      }
   }

   private int tableSizeFor(final int capacity) {
      RichInt$ var10000 = RichInt$.MODULE$;
      var10000 = RichInt$.MODULE$;
      int var2 = capacity - 1;
      int max$extension_that = 4;
      scala.math.package$ var7 = scala.math.package$.MODULE$;
      int var3 = Integer.highestOneBit(Math.max(var2, max$extension_that)) * 2;
      int min$extension_that = 1073741824;
      var7 = scala.math.package$.MODULE$;
      return Math.min(var3, min$extension_that);
   }

   public int scala$collection$mutable$CollisionProofHashMap$$newThreshold(final int size) {
      return (int)((double)size * this.loadFactor);
   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$CollisionProofHashMap$$table, (Object)null);
      this.scala$collection$mutable$CollisionProofHashMap$$contentSize = 0;
   }

   public Option remove(final Object key) {
      Object v = this.remove0(key);
      return (Option)(v == Statics.pfMarker ? None$.MODULE$ : new Some(v));
   }

   public CollisionProofHashMap subtractOne(final Object elem) {
      this.remove0(elem);
      return this;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void foreach(final Function1 f) {
      int len = this.scala$collection$mutable$CollisionProofHashMap$$table.length;

      for(int i = 0; i < len; ++i) {
         Node n = this.scala$collection$mutable$CollisionProofHashMap$$table[i];
         if (n != null) {
            if (!(n instanceof LLNode)) {
               if (!(n instanceof RBNode)) {
                  throw new MatchError(n);
               }

               RBNode foreach_this = (RBNode)n;

               while(true) {
                  if (foreach_this.left() != null) {
                     foreach_this.left().foreach(f);
                  }

                  f.apply(new Tuple2(foreach_this.key(), foreach_this.value()));
                  if (foreach_this.right() == null) {
                     Object var8 = null;
                     break;
                  }

                  foreach_this = foreach_this.right();
               }
            } else {
               LLNode foreach_this = (LLNode)n;

               while(true) {
                  f.apply(new Tuple2(foreach_this.key(), foreach_this.value()));
                  if (foreach_this.next() == null) {
                     Object var7 = null;
                     break;
                  }

                  foreach_this = foreach_this.next();
               }
            }
         }
      }

   }

   public void foreachEntry(final Function2 f) {
      int len = this.scala$collection$mutable$CollisionProofHashMap$$table.length;

      for(int i = 0; i < len; ++i) {
         Node n = this.scala$collection$mutable$CollisionProofHashMap$$table[i];
         if (n != null) {
            if (!(n instanceof LLNode)) {
               if (!(n instanceof RBNode)) {
                  throw new MatchError(n);
               }

               RBNode foreachEntry_this = (RBNode)n;

               while(true) {
                  if (foreachEntry_this.left() != null) {
                     foreachEntry_this.left().foreachEntry(f);
                  }

                  f.apply(foreachEntry_this.key(), foreachEntry_this.value());
                  if (foreachEntry_this.right() == null) {
                     Object var8 = null;
                     break;
                  }

                  foreachEntry_this = foreachEntry_this.right();
               }
            } else {
               LLNode foreachEntry_this = (LLNode)n;

               while(true) {
                  f.apply(foreachEntry_this.key(), foreachEntry_this.value());
                  if (foreachEntry_this.next() == null) {
                     Object var7 = null;
                     break;
                  }

                  foreachEntry_this = foreachEntry_this.next();
               }
            }
         }
      }

   }

   public Object writeReplace() {
      return new DefaultSerializationProxy(new DeserializationFactory(this.scala$collection$mutable$CollisionProofHashMap$$table.length, this.loadFactor, this.scala$collection$mutable$CollisionProofHashMap$$ordering), this);
   }

   public String className() {
      return "CollisionProofHashMap";
   }

   public Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      int scala$collection$mutable$CollisionProofHashMap$$computeHash_h = key == null ? 0 : key.hashCode();
      int hash = scala$collection$mutable$CollisionProofHashMap$$computeHash_h ^ scala$collection$mutable$CollisionProofHashMap$$computeHash_h >>> 16;
      int idx = hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      Node var5 = this.scala$collection$mutable$CollisionProofHashMap$$table[idx];
      if (var5 != null) {
         if (var5 instanceof LLNode) {
            LLNode nd = ((LLNode)var5).getNode(key, hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering);
            if (nd != null) {
               return nd.value();
            }
         } else {
            RBNode nd = ((RBNode)var5).getNode(key, hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering);
            if (nd != null) {
               return nd.value();
            }
         }
      }

      Node[] table0 = this.scala$collection$mutable$CollisionProofHashMap$$table;
      Object var9 = defaultValue.apply();
      if (this.scala$collection$mutable$CollisionProofHashMap$$contentSize + 1 >= this.scala$collection$mutable$CollisionProofHashMap$$threshold) {
         this.scala$collection$mutable$CollisionProofHashMap$$growTable(this.scala$collection$mutable$CollisionProofHashMap$$table.length * 2);
      }

      int newIdx = table0 == this.scala$collection$mutable$CollisionProofHashMap$$table ? idx : hash & this.scala$collection$mutable$CollisionProofHashMap$$table.length - 1;
      this.scala$collection$mutable$CollisionProofHashMap$$put0(key, var9, false, hash, newIdx);
      return var9;
   }

   public CollisionProofHashMap map(final Function1 f, final Ordering ordering) {
      return CollisionProofHashMap$.MODULE$.from(new View.Map(this, f), ordering);
   }

   public CollisionProofHashMap flatMap(final Function1 f, final Ordering ordering) {
      return CollisionProofHashMap$.MODULE$.from(new View.FlatMap(this, f), ordering);
   }

   public CollisionProofHashMap collect(final PartialFunction pf, final Ordering ordering) {
      return CollisionProofHashMap$.MODULE$.from(new View.Collect(this, pf), ordering);
   }

   public CollisionProofHashMap concat(final IterableOnce suffix) {
      CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
      Object var10001;
      if (suffix instanceof Iterable) {
         Iterable var2 = (Iterable)suffix;
         var10001 = new View.Concat(this, var2);
      } else {
         var10001 = this.iterator().concat(() -> suffix.iterator());
      }

      return var10000.from((IterableOnce)var10001, this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   public final CollisionProofHashMap $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   /** @deprecated */
   public CollisionProofHashMap $plus(final Tuple2 kv) {
      return CollisionProofHashMap$.MODULE$.from(new View.Appended(this, kv), this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   /** @deprecated */
   public CollisionProofHashMap $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return CollisionProofHashMap$.MODULE$.from(new View.Concat(new View.Appended(new View.Appended(this, elem1), elem2), elems), this.scala$collection$mutable$CollisionProofHashMap$$ordering);
   }

   private boolean isRed(final RBNode node) {
      return node != null && node.red();
   }

   private boolean isBlack(final RBNode node) {
      return node == null || !node.red();
   }

   private int compare(final Object key, final int hash, final LLNode node) {
      int i = hash - node.hash();
      return i != 0 ? i : this.scala$collection$mutable$CollisionProofHashMap$$ordering.compare(key, node.key());
   }

   private int compare(final Object key, final int hash, final RBNode node) {
      return this.scala$collection$mutable$CollisionProofHashMap$$ordering.compare(key, node.key());
   }

   private final boolean insertIntoExisting(final RBNode _root, final int bucket, final Object key, final int hash, final Object value, final RBNode x) {
      while(true) {
         int cmp = this.scala$collection$mutable$CollisionProofHashMap$$ordering.compare(key, x.key());
         if (cmp == 0) {
            x.value_$eq(value);
            return false;
         }

         RBNode next = cmp < 0 ? x.left() : x.right();
         if (next == null) {
            CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
            boolean scala$collection$mutable$CollisionProofHashMap$$leaf_red = true;
            RBNode z = new RBNode(key, hash, value, scala$collection$mutable$CollisionProofHashMap$$leaf_red, (RBNode)null, (RBNode)null, x);
            if (cmp < 0) {
               x.left_$eq(z);
            } else {
               x.right_$eq(z);
            }

            this.scala$collection$mutable$CollisionProofHashMap$$table[bucket] = this.fixAfterInsert(_root, z);
            return true;
         }

         x = next;
         value = value;
         hash = hash;
         key = key;
         bucket = bucket;
         _root = _root;
      }
   }

   private final boolean insert(final RBNode tree, final int bucket, final Object key, final int hash, final Object value) {
      if (tree == null) {
         CollisionProofHashMap$ var10002 = CollisionProofHashMap$.MODULE$;
         boolean scala$collection$mutable$CollisionProofHashMap$$leaf_red = false;
         this.scala$collection$mutable$CollisionProofHashMap$$table[bucket] = new RBNode(key, hash, value, scala$collection$mutable$CollisionProofHashMap$$leaf_red, (RBNode)null, (RBNode)null, (RBNode)null);
         return true;
      } else {
         return this.insertIntoExisting(tree, bucket, key, hash, value, tree);
      }
   }

   private RBNode fixAfterInsert(final RBNode _root, final RBNode node) {
      RBNode root = _root;
      RBNode z = node;

      while(true) {
         RBNode isRed_node = z.parent();
         boolean var10000 = isRed_node != null && isRed_node.red();
         Object var22 = null;
         if (!var10000) {
            root.red_$eq(false);
            return root;
         }

         if (z.parent() == z.parent().parent().left()) {
            RBNode y = z.parent().parent().right();
            if (y != null && y.red()) {
               z.parent().red_$eq(false);
               y.red_$eq(false);
               z.parent().parent().red_$eq(true);
               z = z.parent().parent();
            } else {
               if (z == z.parent().right()) {
                  z = z.parent();
                  RBNode rotateLeft_root = root;
                  RBNode rotateLeft_y = z.right();
                  z.right_$eq(rotateLeft_y.left());
                  RBNode rotateLeft_xp = z.parent();
                  if (rotateLeft_y.left() != null) {
                     rotateLeft_y.left().parent_$eq(z);
                  }

                  rotateLeft_y.parent_$eq(rotateLeft_xp);
                  if (rotateLeft_xp == null) {
                     rotateLeft_root = rotateLeft_y;
                  } else if (z == rotateLeft_xp.left()) {
                     rotateLeft_xp.left_$eq(rotateLeft_y);
                  } else {
                     rotateLeft_xp.right_$eq(rotateLeft_y);
                  }

                  rotateLeft_y.left_$eq(z);
                  z.parent_$eq(rotateLeft_y);
                  RBNode var39 = rotateLeft_root;
                  rotateLeft_root = null;
                  Object var24 = null;
                  Object var25 = null;
                  root = var39;
               }

               z.parent().red_$eq(false);
               z.parent().parent().red_$eq(true);
               RBNode rotateRight_x = z.parent().parent();
               RBNode rotateRight_root = root;
               RBNode rotateRight_y = rotateRight_x.left();
               rotateRight_x.left_$eq(rotateRight_y.right());
               RBNode rotateRight_xp = rotateRight_x.parent();
               if (rotateRight_y.right() != null) {
                  rotateRight_y.right().parent_$eq(rotateRight_x);
               }

               rotateRight_y.parent_$eq(rotateRight_xp);
               if (rotateRight_xp == null) {
                  rotateRight_root = rotateRight_y;
               } else if (rotateRight_x == rotateRight_xp.right()) {
                  rotateRight_xp.right_$eq(rotateRight_y);
               } else {
                  rotateRight_xp.left_$eq(rotateRight_y);
               }

               rotateRight_y.right_$eq(rotateRight_x);
               rotateRight_x.parent_$eq(rotateRight_y);
               RBNode var40 = rotateRight_root;
               Object var26 = null;
               rotateRight_root = null;
               Object var28 = null;
               Object var29 = null;
               root = var40;
            }
         } else {
            RBNode y = z.parent().parent().left();
            if (y != null && y.red()) {
               z.parent().red_$eq(false);
               y.red_$eq(false);
               z.parent().parent().red_$eq(true);
               z = z.parent().parent();
            } else {
               if (z == z.parent().left()) {
                  z = z.parent();
                  RBNode rotateRight_root = root;
                  RBNode rotateRight_y = z.left();
                  z.left_$eq(rotateRight_y.right());
                  RBNode rotateRight_xp = z.parent();
                  if (rotateRight_y.right() != null) {
                     rotateRight_y.right().parent_$eq(z);
                  }

                  rotateRight_y.parent_$eq(rotateRight_xp);
                  if (rotateRight_xp == null) {
                     rotateRight_root = rotateRight_y;
                  } else if (z == rotateRight_xp.right()) {
                     rotateRight_xp.right_$eq(rotateRight_y);
                  } else {
                     rotateRight_xp.left_$eq(rotateRight_y);
                  }

                  rotateRight_y.right_$eq(z);
                  z.parent_$eq(rotateRight_y);
                  RBNode var37 = rotateRight_root;
                  rotateRight_root = null;
                  Object var31 = null;
                  Object var32 = null;
                  root = var37;
               }

               z.parent().red_$eq(false);
               z.parent().parent().red_$eq(true);
               RBNode rotateLeft_x = z.parent().parent();
               RBNode rotateLeft_root = root;
               RBNode rotateLeft_y = rotateLeft_x.right();
               rotateLeft_x.right_$eq(rotateLeft_y.left());
               RBNode rotateLeft_xp = rotateLeft_x.parent();
               if (rotateLeft_y.left() != null) {
                  rotateLeft_y.left().parent_$eq(rotateLeft_x);
               }

               rotateLeft_y.parent_$eq(rotateLeft_xp);
               if (rotateLeft_xp == null) {
                  rotateLeft_root = rotateLeft_y;
               } else if (rotateLeft_x == rotateLeft_xp.left()) {
                  rotateLeft_xp.left_$eq(rotateLeft_y);
               } else {
                  rotateLeft_xp.right_$eq(rotateLeft_y);
               }

               rotateLeft_y.left_$eq(rotateLeft_x);
               rotateLeft_x.parent_$eq(rotateLeft_y);
               RBNode var38 = rotateLeft_root;
               Object var33 = null;
               rotateLeft_root = null;
               Object var35 = null;
               Object var36 = null;
               root = var38;
            }
         }
      }
   }

   private Object delete(final RBNode _root, final int bucket, final Object key, final int hash) {
      RBNode root = _root;
      RBNode z = _root.getNode(key, hash, this.scala$collection$mutable$CollisionProofHashMap$$ordering);
      if (z != null) {
         Object oldValue = z.value();
         boolean yIsRed = z.red();
         RBNode x;
         RBNode xParent;
         if (z.left() == null) {
            x = z.right();
            root = this.transplant(_root, z, z.right());
            xParent = z.parent();
         } else if (z.right() == null) {
            x = z.left();
            root = this.transplant(_root, z, z.left());
            xParent = z.parent();
         } else {
            RBNode y = CollisionProofHashMap$.MODULE$.scala$collection$mutable$CollisionProofHashMap$$minNodeNonNull(z.right());
            yIsRed = y.red();
            x = y.right();
            if (y.parent() == z) {
               xParent = y;
            } else {
               xParent = y.parent();
               root = this.transplant(_root, y, y.right());
               y.right_$eq(z.right());
               y.right().parent_$eq(y);
            }

            root = this.transplant(root, z, y);
            y.left_$eq(z.left());
            y.left().parent_$eq(y);
            y.red_$eq(z.red());
         }

         if (!yIsRed) {
            root = this.fixAfterDelete(root, x, xParent);
         }

         if (root != _root) {
            this.scala$collection$mutable$CollisionProofHashMap$$table[bucket] = root;
         }

         return oldValue;
      } else {
         return Statics.pfMarker;
      }
   }

   private RBNode fixAfterDelete(final RBNode _root, final RBNode node, final RBNode parent) {
      RBNode root = _root;
      RBNode x = node;

      for(RBNode xParent = parent; x != root && (x == null || !x.red()); xParent = x.parent()) {
         if (x == xParent.left()) {
            RBNode w = xParent.right();
            if (w.red()) {
               w.red_$eq(false);
               xParent.red_$eq(true);
               RBNode rotateLeft_root = root;
               RBNode rotateLeft_y = xParent.right();
               xParent.right_$eq(rotateLeft_y.left());
               RBNode rotateLeft_xp = xParent.parent();
               if (rotateLeft_y.left() != null) {
                  rotateLeft_y.left().parent_$eq(xParent);
               }

               rotateLeft_y.parent_$eq(rotateLeft_xp);
               if (rotateLeft_xp == null) {
                  rotateLeft_root = rotateLeft_y;
               } else if (xParent == rotateLeft_xp.left()) {
                  rotateLeft_xp.left_$eq(rotateLeft_y);
               } else {
                  rotateLeft_xp.right_$eq(rotateLeft_y);
               }

               rotateLeft_y.left_$eq(xParent);
               xParent.parent_$eq(rotateLeft_y);
               RBNode var10000 = rotateLeft_root;
               rotateLeft_root = null;
               Object var34 = null;
               Object var35 = null;
               root = var10000;
               w = xParent.right();
            }

            RBNode isBlack_node = w.left();
            boolean var57 = isBlack_node == null || !isBlack_node.red();
            Object var36 = null;
            if (var57) {
               RBNode isBlack_node = w.right();
               var57 = isBlack_node == null || !isBlack_node.red();
               Object var37 = null;
               if (var57) {
                  w.red_$eq(true);
                  x = xParent;
                  continue;
               }
            }

            RBNode isBlack_node = w.right();
            var57 = isBlack_node == null || !isBlack_node.red();
            Object var38 = null;
            if (var57) {
               w.left().red_$eq(false);
               w.red_$eq(true);
               RBNode rotateRight_root = root;
               RBNode rotateRight_y = w.left();
               w.left_$eq(rotateRight_y.right());
               RBNode rotateRight_xp = w.parent();
               if (rotateRight_y.right() != null) {
                  rotateRight_y.right().parent_$eq(w);
               }

               rotateRight_y.parent_$eq(rotateRight_xp);
               if (rotateRight_xp == null) {
                  rotateRight_root = rotateRight_y;
               } else if (w == rotateRight_xp.right()) {
                  rotateRight_xp.right_$eq(rotateRight_y);
               } else {
                  rotateRight_xp.left_$eq(rotateRight_y);
               }

               rotateRight_y.right_$eq(w);
               w.parent_$eq(rotateRight_y);
               RBNode var60 = rotateRight_root;
               rotateRight_root = null;
               Object var40 = null;
               Object var41 = null;
               root = var60;
               w = xParent.right();
            }

            w.red_$eq(xParent.red());
            xParent.red_$eq(false);
            w.right().red_$eq(false);
            RBNode rotateLeft_root = root;
            RBNode rotateLeft_y = xParent.right();
            xParent.right_$eq(rotateLeft_y.left());
            RBNode rotateLeft_xp = xParent.parent();
            if (rotateLeft_y.left() != null) {
               rotateLeft_y.left().parent_$eq(xParent);
            }

            rotateLeft_y.parent_$eq(rotateLeft_xp);
            if (rotateLeft_xp == null) {
               rotateLeft_root = rotateLeft_y;
            } else if (xParent == rotateLeft_xp.left()) {
               rotateLeft_xp.left_$eq(rotateLeft_y);
            } else {
               rotateLeft_xp.right_$eq(rotateLeft_y);
            }

            rotateLeft_y.left_$eq(xParent);
            xParent.parent_$eq(rotateLeft_y);
            RBNode var61 = rotateLeft_root;
            rotateLeft_root = null;
            Object var43 = null;
            Object var44 = null;
            root = var61;
            x = root;
         } else {
            RBNode w = xParent.left();
            if (w.red()) {
               w.red_$eq(false);
               xParent.red_$eq(true);
               RBNode rotateRight_root = root;
               RBNode rotateRight_y = xParent.left();
               xParent.left_$eq(rotateRight_y.right());
               RBNode rotateRight_xp = xParent.parent();
               if (rotateRight_y.right() != null) {
                  rotateRight_y.right().parent_$eq(xParent);
               }

               rotateRight_y.parent_$eq(rotateRight_xp);
               if (rotateRight_xp == null) {
                  rotateRight_root = rotateRight_y;
               } else if (xParent == rotateRight_xp.right()) {
                  rotateRight_xp.right_$eq(rotateRight_y);
               } else {
                  rotateRight_xp.left_$eq(rotateRight_y);
               }

               rotateRight_y.right_$eq(xParent);
               xParent.parent_$eq(rotateRight_y);
               RBNode var62 = rotateRight_root;
               rotateRight_root = null;
               Object var46 = null;
               Object var47 = null;
               root = var62;
               w = xParent.left();
            }

            RBNode isBlack_node = w.right();
            boolean var63 = isBlack_node == null || !isBlack_node.red();
            Object var48 = null;
            if (var63) {
               RBNode isBlack_node = w.left();
               var63 = isBlack_node == null || !isBlack_node.red();
               Object var49 = null;
               if (var63) {
                  w.red_$eq(true);
                  x = xParent;
                  continue;
               }
            }

            RBNode isBlack_node = w.left();
            var63 = isBlack_node == null || !isBlack_node.red();
            Object var50 = null;
            if (var63) {
               w.right().red_$eq(false);
               w.red_$eq(true);
               RBNode rotateLeft_root = root;
               RBNode rotateLeft_y = w.right();
               w.right_$eq(rotateLeft_y.left());
               RBNode rotateLeft_xp = w.parent();
               if (rotateLeft_y.left() != null) {
                  rotateLeft_y.left().parent_$eq(w);
               }

               rotateLeft_y.parent_$eq(rotateLeft_xp);
               if (rotateLeft_xp == null) {
                  rotateLeft_root = rotateLeft_y;
               } else if (w == rotateLeft_xp.left()) {
                  rotateLeft_xp.left_$eq(rotateLeft_y);
               } else {
                  rotateLeft_xp.right_$eq(rotateLeft_y);
               }

               rotateLeft_y.left_$eq(w);
               w.parent_$eq(rotateLeft_y);
               RBNode var66 = rotateLeft_root;
               rotateLeft_root = null;
               Object var52 = null;
               Object var53 = null;
               root = var66;
               w = xParent.left();
            }

            w.red_$eq(xParent.red());
            xParent.red_$eq(false);
            w.left().red_$eq(false);
            RBNode rotateRight_root = root;
            RBNode rotateRight_y = xParent.left();
            xParent.left_$eq(rotateRight_y.right());
            RBNode rotateRight_xp = xParent.parent();
            if (rotateRight_y.right() != null) {
               rotateRight_y.right().parent_$eq(xParent);
            }

            rotateRight_y.parent_$eq(rotateRight_xp);
            if (rotateRight_xp == null) {
               rotateRight_root = rotateRight_y;
            } else if (xParent == rotateRight_xp.right()) {
               rotateRight_xp.right_$eq(rotateRight_y);
            } else {
               rotateRight_xp.left_$eq(rotateRight_y);
            }

            rotateRight_y.right_$eq(xParent);
            xParent.parent_$eq(rotateRight_y);
            RBNode var67 = rotateRight_root;
            rotateRight_root = null;
            Object var55 = null;
            Object var56 = null;
            root = var67;
            x = root;
         }
      }

      if (x != null) {
         x.red_$eq(false);
      }

      return root;
   }

   private RBNode rotateLeft(final RBNode _root, final RBNode x) {
      RBNode root = _root;
      RBNode y = x.right();
      x.right_$eq(y.left());
      RBNode xp = x.parent();
      if (y.left() != null) {
         y.left().parent_$eq(x);
      }

      y.parent_$eq(xp);
      if (xp == null) {
         root = y;
      } else if (x == xp.left()) {
         xp.left_$eq(y);
      } else {
         xp.right_$eq(y);
      }

      y.left_$eq(x);
      x.parent_$eq(y);
      return root;
   }

   private RBNode rotateRight(final RBNode _root, final RBNode x) {
      RBNode root = _root;
      RBNode y = x.left();
      x.left_$eq(y.right());
      RBNode xp = x.parent();
      if (y.right() != null) {
         y.right().parent_$eq(x);
      }

      y.parent_$eq(xp);
      if (xp == null) {
         root = y;
      } else if (x == xp.right()) {
         xp.right_$eq(y);
      } else {
         xp.left_$eq(y);
      }

      y.right_$eq(x);
      x.parent_$eq(y);
      return root;
   }

   private RBNode transplant(final RBNode _root, final RBNode to, final RBNode from) {
      RBNode root = _root;
      if (to.parent() == null) {
         root = from;
      } else if (to == to.parent().left()) {
         to.parent().left_$eq(from);
      } else {
         to.parent().right_$eq(from);
      }

      if (from != null) {
         from.parent_$eq(to.parent());
      }

      return root;
   }

   public RBNode fromNodes(final Iterator xs, final int size) {
      int maxUsedDepth = 32 - Integer.numberOfLeadingZeros(size);
      return f$1(1, size, xs, maxUsedDepth);
   }

   // $FF: synthetic method
   public static final void $anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$1(final int mask$1, final IntRef highCount$1, final IntRef lowCount$1, final RBNode n) {
      if ((n.hash() & mask$1) != 0) {
         ++highCount$1.elem;
      } else {
         ++lowCount$1.elem;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$2(final int mask$1, final RBNode n) {
      return (n.hash() & mask$1) == 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$scala$collection$mutable$CollisionProofHashMap$$splitBucket$3(final int mask$1, final RBNode n) {
      return (n.hash() & mask$1) != 0;
   }

   private static final RBNode f$1(final int level, final int size, final Iterator xs$1, final int maxUsedDepth$1) {
      switch (size) {
         case 0:
            return null;
         case 1:
            Node nn = (Node)xs$1.next();
            Object var10000;
            int var10001;
            Object var10002;
            if (nn instanceof LLNode) {
               LLNode var5 = (LLNode)nn;
               var10000 = var5.key();
               var10001 = var5.hash();
               var10002 = var5.value();
            } else {
               if (!(nn instanceof RBNode)) {
                  throw new MatchError(nn);
               }

               RBNode var6 = (RBNode)nn;
               var10000 = var6.key();
               var10001 = var6.hash();
               var10002 = var6.value();
            }

            Object var16 = var10002;
            int var15 = var10001;
            Object var14 = var10000;
            return new RBNode(var14, var15, var16, level == maxUsedDepth$1 && level != 1, (RBNode)null, (RBNode)null, (RBNode)null);
         default:
            int leftSize = (size - 1) / 2;
            RBNode left = f$1(level + 1, leftSize, xs$1, maxUsedDepth$1);
            Node nn = (Node)xs$1.next();
            RBNode right = f$1(level + 1, size - 1 - leftSize, xs$1, maxUsedDepth$1);
            Object var20;
            int var21;
            Object var22;
            if (nn instanceof LLNode) {
               LLNode var11 = (LLNode)nn;
               var20 = var11.key();
               var21 = var11.hash();
               var22 = var11.value();
            } else {
               if (!(nn instanceof RBNode)) {
                  throw new MatchError(nn);
               }

               RBNode var12 = (RBNode)nn;
               var20 = var12.key();
               var21 = var12.hash();
               var22 = var12.value();
            }

            Object var19 = var22;
            int var18 = var21;
            Object var17 = var20;
            RBNode n = new RBNode(var17, var18, var19, false, left, right, (RBNode)null);
            if (left != null) {
               left.parent_$eq(n);
            }

            right.parent_$eq(n);
            return n;
      }
   }

   public CollisionProofHashMap(final int initialCapacity, final double loadFactor, final Ordering ordering) {
      this.loadFactor = loadFactor;
      this.scala$collection$mutable$CollisionProofHashMap$$ordering = ordering;
      this.scala$collection$mutable$CollisionProofHashMap$$table = new Node[this.tableSizeFor(initialCapacity)];
      this.scala$collection$mutable$CollisionProofHashMap$$threshold = this.scala$collection$mutable$CollisionProofHashMap$$newThreshold(this.scala$collection$mutable$CollisionProofHashMap$$table.length);
      this.scala$collection$mutable$CollisionProofHashMap$$contentSize = 0;
   }

   public CollisionProofHashMap(final Ordering ordering) {
      CollisionProofHashMap$ var10001 = CollisionProofHashMap$.MODULE$;
      CollisionProofHashMap$ var10002 = CollisionProofHashMap$.MODULE$;
      this(16, (double)0.75F, ordering);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private abstract class MapIterator extends AbstractIterator {
      private int i;
      private Node node;
      private final int len;
      // $FF: synthetic field
      public final CollisionProofHashMap $outer;

      public abstract Object extract(final LLNode node);

      public abstract Object extract(final RBNode node);

      public boolean hasNext() {
         if (this.node != null) {
            return true;
         } else {
            while(this.i < this.len) {
               Node n = this.scala$collection$mutable$CollisionProofHashMap$MapIterator$$$outer().scala$collection$mutable$CollisionProofHashMap$$table[this.i];
               ++this.i;
               if (n != null) {
                  if (n instanceof RBNode) {
                     RBNode var2 = (RBNode)n;
                     this.node = CollisionProofHashMap$.MODULE$.scala$collection$mutable$CollisionProofHashMap$$minNodeNonNull(var2);
                     return true;
                  }

                  if (n instanceof LLNode) {
                     LLNode var3 = (LLNode)n;
                     this.node = var3;
                     return true;
                  }

                  throw new MatchError(n);
               }
            }

            return false;
         }
      }

      public Object next() {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         } else {
            Node var1 = this.node;
            if (var1 instanceof RBNode) {
               RBNode var2 = (RBNode)var1;
               Object r = this.extract(var2);
               this.node = CollisionProofHashMap$.MODULE$.scala$collection$mutable$CollisionProofHashMap$$successor(var2);
               return r;
            } else if (var1 instanceof LLNode) {
               LLNode var4 = (LLNode)var1;
               Object r = this.extract(var4);
               this.node = var4.next();
               return r;
            } else {
               throw new MatchError(var1);
            }
         }
      }

      // $FF: synthetic method
      public CollisionProofHashMap scala$collection$mutable$CollisionProofHashMap$MapIterator$$$outer() {
         return this.$outer;
      }

      public MapIterator() {
         if (CollisionProofHashMap.this == null) {
            throw null;
         } else {
            this.$outer = CollisionProofHashMap.this;
            super();
            this.i = 0;
            this.node = null;
            this.len = CollisionProofHashMap.this.scala$collection$mutable$CollisionProofHashMap$$table.length;
         }
      }
   }

   private static final class DeserializationFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final int tableLength;
      private final double loadFactor;
      private final Ordering ordering;

      public int tableLength() {
         return this.tableLength;
      }

      public double loadFactor() {
         return this.loadFactor;
      }

      public Ordering ordering() {
         return this.ordering;
      }

      public CollisionProofHashMap fromSpecific(final IterableOnce it) {
         return (new CollisionProofHashMap(this.tableLength(), this.loadFactor(), this.ordering())).addAll(it);
      }

      public Builder newBuilder() {
         CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
         int var5 = this.tableLength();
         double var10001 = this.loadFactor();
         Ordering newBuilder_evidence$4 = this.ordering();
         double newBuilder_loadFactor = var10001;
         int newBuilder_initialCapacity = var5;
         return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor, newBuilder_evidence$4) {
            public void sizeHint(final int size) {
               ((CollisionProofHashMap)this.elems()).sizeHint(size);
            }
         };
      }

      public DeserializationFactory(final int tableLength, final double loadFactor, final Ordering ordering) {
         this.tableLength = tableLength;
         this.loadFactor = loadFactor;
         this.ordering = ordering;
      }
   }

   public abstract static class Node {
   }

   public static final class RBNode extends Node {
      private Object key;
      private int hash;
      private Object value;
      private boolean red;
      private RBNode left;
      private RBNode right;
      private RBNode parent;

      public Object key() {
         return this.key;
      }

      public void key_$eq(final Object x$1) {
         this.key = x$1;
      }

      public int hash() {
         return this.hash;
      }

      public void hash_$eq(final int x$1) {
         this.hash = x$1;
      }

      public Object value() {
         return this.value;
      }

      public void value_$eq(final Object x$1) {
         this.value = x$1;
      }

      public boolean red() {
         return this.red;
      }

      public void red_$eq(final boolean x$1) {
         this.red = x$1;
      }

      public RBNode left() {
         return this.left;
      }

      public void left_$eq(final RBNode x$1) {
         this.left = x$1;
      }

      public RBNode right() {
         return this.right;
      }

      public void right_$eq(final RBNode x$1) {
         this.right = x$1;
      }

      public RBNode parent() {
         return this.parent;
      }

      public void parent_$eq(final RBNode x$1) {
         this.parent = x$1;
      }

      public String toString() {
         return (new java.lang.StringBuilder(18)).append("RBNode(").append(this.key()).append(", ").append(this.hash()).append(", ").append(this.value()).append(", ").append(this.red()).append(", ").append(this.left()).append(", ").append(this.right()).append(")").toString();
      }

      public RBNode getNode(final Object k, final int h, final Ordering ord) {
         while(true) {
            CollisionProofHashMap$ var10000 = CollisionProofHashMap$.MODULE$;
            int cmp = ord.compare(k, this.key());
            if (cmp < 0) {
               if (this.left() == null) {
                  return null;
               }

               RBNode var6 = this.left();
               ord = ord;
               h = h;
               k = k;
               this = var6;
            } else {
               if (cmp > 0) {
                  if (this.right() != null) {
                     RBNode var5 = this.right();
                     ord = ord;
                     h = h;
                     k = k;
                     this = var5;
                     continue;
                  }

                  return null;
               }

               return this;
            }
         }
      }

      public void foreach(final Function1 f) {
         while(true) {
            if (this.left() != null) {
               this.left().foreach(f);
            }

            f.apply(new Tuple2(this.key(), this.value()));
            if (this.right() == null) {
               return;
            }

            RBNode var10000 = this.right();
            f = f;
            this = var10000;
         }
      }

      public void foreachEntry(final Function2 f) {
         while(true) {
            if (this.left() != null) {
               this.left().foreachEntry(f);
            }

            f.apply(this.key(), this.value());
            if (this.right() == null) {
               return;
            }

            RBNode var10000 = this.right();
            f = f;
            this = var10000;
         }
      }

      public void foreachNode(final Function1 f) {
         while(true) {
            if (this.left() != null) {
               this.left().foreachNode(f);
            }

            f.apply(this);
            if (this.right() == null) {
               return;
            }

            RBNode var10000 = this.right();
            f = f;
            this = var10000;
         }
      }

      public RBNode(final Object key, final int hash, final Object value, final boolean red, final RBNode left, final RBNode right, final RBNode parent) {
         this.key = key;
         this.hash = hash;
         this.value = value;
         this.red = red;
         this.left = left;
         this.right = right;
         this.parent = parent;
         super();
      }
   }

   private static final class RBNodesIterator extends AbstractIterator {
      private RBNode nextNode;

      public boolean hasNext() {
         return this.nextNode != null;
      }

      public RBNode next() throws NoSuchElementException {
         RBNode var1 = this.nextNode;
         if (var1 == null) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return (RBNode)Iterator$.scala$collection$Iterator$$_empty.next();
         } else {
            this.nextNode = CollisionProofHashMap$.MODULE$.scala$collection$mutable$CollisionProofHashMap$$successor(var1);
            return var1;
         }
      }

      public RBNodesIterator(final RBNode tree, final Ordering ord) {
         this.nextNode = tree == null ? null : CollisionProofHashMap$.MODULE$.scala$collection$mutable$CollisionProofHashMap$$minNodeNonNull(tree);
      }
   }

   private static final class LLNode extends Node {
      private Object key;
      private int hash;
      private Object value;
      private LLNode next;

      public Object key() {
         return this.key;
      }

      public void key_$eq(final Object x$1) {
         this.key = x$1;
      }

      public int hash() {
         return this.hash;
      }

      public void hash_$eq(final int x$1) {
         this.hash = x$1;
      }

      public Object value() {
         return this.value;
      }

      public void value_$eq(final Object x$1) {
         this.value = x$1;
      }

      public LLNode next() {
         return this.next;
      }

      public void next_$eq(final LLNode x$1) {
         this.next = x$1;
      }

      public String toString() {
         return (new java.lang.StringBuilder(16)).append("LLNode(").append(this.key()).append(", ").append(this.value()).append(", ").append(this.hash()).append(") -> ").append(this.next()).toString();
      }

      private boolean eq(final Object a, final Object b) {
         if (a == null) {
            return b == null;
         } else {
            return a.equals(b);
         }
      }

      public LLNode getNode(final Object k, final int h, final Ordering ord) {
         while(h != this.hash() || !this.eq(k, this.key())) {
            if (this.next() == null || this.hash() > h) {
               return null;
            }

            LLNode var10000 = this.next();
            ord = ord;
            h = h;
            k = k;
            this = var10000;
         }

         return this;
      }

      public void foreach(final Function1 f) {
         while(true) {
            f.apply(new Tuple2(this.key(), this.value()));
            if (this.next() == null) {
               return;
            }

            LLNode var10000 = this.next();
            f = f;
            this = var10000;
         }
      }

      public void foreachEntry(final Function2 f) {
         while(true) {
            f.apply(this.key(), this.value());
            if (this.next() == null) {
               return;
            }

            LLNode var10000 = this.next();
            f = f;
            this = var10000;
         }
      }

      public void foreachNode(final Function1 f) {
         while(true) {
            f.apply(this);
            if (this.next() == null) {
               return;
            }

            LLNode var10000 = this.next();
            f = f;
            this = var10000;
         }
      }

      public LLNode(final Object key, final int hash, final Object value, final LLNode next) {
         this.key = key;
         this.hash = hash;
         this.value = value;
         this.next = next;
         super();
      }
   }
}
