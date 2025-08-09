package spire.math;

import java.math.BigInteger;
import scala.MatchError;
import scala.Tuple2;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Ordered;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.math.BigInt.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.math.prime.Factors;

@ScalaSignature(
   bytes = "\u0006\u0005\red!B7o\u0003C\u0019\bbBA\r\u0001\u0011\u0005\u00111\u0004\u0005\b\u0003;\u0001a\u0011AA\u0010\u0011\u001d\tI\u0003\u0001D\u0001\u0003?Aq!a\u000b\u0001\r\u0003\ti\u0003C\u0004\u00026\u0001!)!a\u000e\t\u000f\u0005u\u0002\u0001\"\u0002\u0002@!9\u00111\t\u0001\u0005\u0006\u0005\u0015\u0003bBA%\u0001\u0011\u0015\u00111\n\u0005\b\u0003\u001f\u0002AQAA)\u0011\u001d\t)\u0006\u0001C\u0003\u0003/Bq!a\u0017\u0001\t\u000b\ti\u0006C\u0004\u0002h\u0001!)!!\u001b\t\u000f\u00055\u0004\u0001\"\u0002\u0002p!9\u00111\u000f\u0001\u0005\u0006\u0005U\u0004bBA=\u0001\u0011\u0015\u00111\u0010\u0005\b\u0003\u007f\u0002AQAAA\u0011\u001d\t)\t\u0001C\u0003\u0003\u000fCq!a#\u0001\t\u0003\ti\tC\u0004\u0002\u0014\u0002!\t!!&\t\u000f\u0005U\u0002A\"\u0001\u0002\u001a\"9\u0011Q\b\u0001\u0007\u0002\u0005\r\u0006bBA\"\u0001\u0019\u0005\u0011q\u0015\u0005\b\u0003\u0013\u0002a\u0011AAV\u0011\u001d\ty\u0005\u0001D\u0001\u0003_Cq!a\u0017\u0001\r\u0003\t\u0019\fC\u0004\u0002h\u00011\t!a.\t\u000f\u00055\u0004A\"\u0001\u0002<\"9\u00111\u000f\u0001\u0007\u0002\u0005}\u0006bBA=\u0001\u0019\u0005\u00111\u0019\u0005\b\u0003\u007f\u0002a\u0011AAd\u0011\u001d\t)\t\u0001D\u0001\u0003\u0017Dq!!\u000e\u0001\t\u000b\ty\rC\u0004\u0002>\u0001!)!!7\t\u000f\u0005\r\u0003\u0001\"\u0002\u0002^\"9\u0011\u0011\n\u0001\u0005\u0006\u0005\u0005\bbBA(\u0001\u0011\u0015\u0011Q\u001d\u0005\b\u00037\u0002AQAAu\u0011\u001d\t9\u0007\u0001C\u0003\u0003[Dq!!\u001c\u0001\t\u000b\t\t\u0010C\u0004\u0002t\u0001!)!!>\t\u000f\u0005e\u0004\u0001\"\u0002\u0002z\"9\u0011q\u0010\u0001\u0005\u0006\u0005u\bbBAC\u0001\u0011\u0015!\u0011\u0001\u0005\t\u0003k\u0001a\u0011\u00018\u0003\u0006!A\u0011Q\b\u0001\u0007\u00029\u00149\u0002\u0003\u0005\u0002D\u00011\tA\u001cB\u000e\u0011!\tI\u0005\u0001D\u0001]\n}\u0001\u0002CA(\u0001\u0019\u0005aNa\t\t\u0011\u0005m\u0003A\"\u0001o\u0005OA\u0001\"a\u001a\u0001\r\u0003q'1\u0006\u0005\t\u0003[\u0002a\u0011\u00018\u00030!A\u00111\u000f\u0001\u0007\u00029\u0014\u0019\u0004\u0003\u0005\u0002z\u00011\tA\u001cB\u001c\u0011!\ty\b\u0001D\u0001]\nm\u0002\u0002CAC\u0001\u0019\u0005aNa\u0010\t\u000f\t\r\u0003\u0001\"\u0002\u0003F!9!\u0011\n\u0001\u0005\u0006\t-\u0003b\u0002B(\u0001\u0019\u0005!\u0011\u000b\u0005\b\u0005/\u0002a\u0011\u0001B-\u0011\u001d\u0011i\u0006\u0001C\u0003\u0005?BqA!\u001a\u0001\t\u000b\u00119\u0007C\u0004\u0003l\u0001!)A!\u001c\t\u000f\tU\u0004A\"\u0001\u0003x!9!\u0011\u0010\u0001\u0007\u0002\tm\u0004b\u0002B@\u0001\u0011\u0005!\u0011\u0011\u0005\b\u0005\u000b\u0003a\u0011\u0001B<\u0011\u001d\u00119\t\u0001D\u0001\u0003?AqA!#\u0001\r\u0003\u0011Y\tC\u0004\u0003\u001a\u0002!\tEa'\t\u000f\t\r\u0006\u0001\"\u0011\u0003&\"9!Q\u0016\u0001\u0005B\u00055\u0002b\u0002BX\u0001\u0011\u0015!\u0011\u0017\u0005\b\u0005g\u0003a\u0011\u0001B[\u0011!\u0011i\f\u0001D\u0001]\n}\u0006b\u0002Ba\u0001\u0011\u0005#1\u0019\u0005\b\u0005+\u0004AQAA\u0010\u0011\u001d\u00119\u000e\u0001D\u0001\u0003?AqA!7\u0001\r\u0003\ty\u0002C\u0004\u0003\\\u0002!\t!a\b\t\u000f\tu\u0007\u0001\"\u0001\u0003`\"9!Q\u001e\u0001\u0005\u0006\t=\bb\u0002B{\u0001\u0019\u0005\u0011QF\u0004\b\u0007\u0003q\u0007\u0012AB\u0002\r\u0019ig\u000e#\u0001\u0004\u0006!9\u0011\u0011\u0004+\u0005\u0002\r}\u0001\"CB\u0011)\n\u0007IQ\u0001B<\u0011!\u0019\u0019\u0003\u0016Q\u0001\u000e\u0005U\u0001\"CB\u0013)\n\u0007IQ\u0001B<\u0011!\u00199\u0003\u0016Q\u0001\u000e\u0005U\u0001\"CB\u0015)\n\u0007IQ\u0001B<\u0011!\u0019Y\u0003\u0016Q\u0001\u000e\u0005U\u0001\"CB\u0017)\n\u0007IQ\u0001B<\u0011!\u0019y\u0003\u0016Q\u0001\u000e\u0005U\u0001\"CB\u0019)\n\u0007IQ\u0001B<\u0011!\u0019\u0019\u0004\u0016Q\u0001\u000e\u0005U\u0001\"CB\u001b)\n\u0007IQ\u0001B<\u0011!\u00199\u0004\u0016Q\u0001\u000e\u0005U\u0001BCB\u001d)\n\u0007IQ\u00019\u0003@\"A11\b+!\u0002\u001b\u0011I\u0001\u0003\u0006\u0004>Q\u0013\r\u0011\"\u0002q\u0005oB\u0001ba\u0010UA\u00035\u0011Q\u0003\u0005\b\u0007\u0003\"F1AB\"\u0011\u001d\u0019\t\u0005\u0016C\u0002\u0007\u0013Bqa!\u0011U\t\u0007\u0019i\u0005\u0003\u0005\u0004BQ#\tA\\B)\u0011\u001d\u00199\u0006\u0016C\u0001\u00073Bqa!\u0019U\t\u0003\u0019\u0019\u0007C\u0005\u0004jQ\u000b\t\u0011\"\u0003\u0004l\tA1+\u00194f\u0019>twM\u0003\u0002pa\u0006!Q.\u0019;i\u0015\u0005\t\u0018!B:qSJ,7\u0001A\n\u0005\u0001Q\\h\u0010\u0005\u0002vs6\taO\u0003\u0002po*\t\u00010A\u0003tG\u0006d\u0017-\u0003\u0002{m\nY1kY1mC:+XNY3s!\t)H0\u0003\u0002~m\n92kY1mC:+X.\u001a:jG\u000e{gN^3sg&|gn\u001d\t\u0006\u007f\u0006=\u0011Q\u0003\b\u0005\u0003\u0003\tYA\u0004\u0003\u0002\u0004\u0005%QBAA\u0003\u0015\r\t9A]\u0001\u0007yI|w\u000e\u001e \n\u0003aL1!!\u0004x\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0005\u0002\u0014\t9qJ\u001d3fe\u0016$'bAA\u0007oB\u0019\u0011q\u0003\u0001\u000e\u00039\fa\u0001P5oSRtDCAA\u000b\u0003\u0019I7OW3s_V\u0011\u0011\u0011\u0005\t\u0005\u0003G\t)#D\u0001x\u0013\r\t9c\u001e\u0002\b\u0005>|G.Z1o\u0003\u0015I7o\u00148f\u0003\u0019\u0019\u0018n\u001a8v[V\u0011\u0011q\u0006\t\u0005\u0003G\t\t$C\u0002\u00024]\u00141!\u00138u\u0003\u0015!\u0003\u000f\\;t)\u0011\t)\"!\u000f\t\u000f\u0005mR\u00011\u0001\u0002\u0016\u0005\u0019!\u000f[:\u0002\r\u0011j\u0017N\\;t)\u0011\t)\"!\u0011\t\u000f\u0005mb\u00011\u0001\u0002\u0016\u00051A\u0005^5nKN$B!!\u0006\u0002H!9\u00111H\u0004A\u0002\u0005U\u0011\u0001\u0002\u0013eSZ$B!!\u0006\u0002N!9\u00111\b\u0005A\u0002\u0005U\u0011\u0001\u0003\u0013qKJ\u001cWM\u001c;\u0015\t\u0005U\u00111\u000b\u0005\b\u0003wI\u0001\u0019AA\u000b\u0003)!C-\u001b<%i&dG-\u001a\u000b\u0005\u0003+\tI\u0006C\u0004\u0002<)\u0001\r!!\u0006\u0002\u0019\u0011\"\u0017N\u001e\u0013qKJ\u001cWM\u001c;\u0015\t\u0005}\u0013Q\r\t\t\u0003G\t\t'!\u0006\u0002\u0016%\u0019\u00111M<\u0003\rQ+\b\u000f\\33\u0011\u001d\tYd\u0003a\u0001\u0003+\tQ!Z9v_R$B!!\u0006\u0002l!9\u00111\b\u0007A\u0002\u0005U\u0011\u0001B3n_\u0012$B!!\u0006\u0002r!9\u00111H\u0007A\u0002\u0005U\u0011\u0001C3rk>$Xn\u001c3\u0015\t\u0005}\u0013q\u000f\u0005\b\u0003wq\u0001\u0019AA\u000b\u0003\u0011!\u0013-\u001c9\u0015\t\u0005U\u0011Q\u0010\u0005\b\u0003wy\u0001\u0019AA\u000b\u0003\u0011!#-\u0019:\u0015\t\u0005U\u00111\u0011\u0005\b\u0003w\u0001\u0002\u0019AA\u000b\u0003\r!S\u000f\u001d\u000b\u0005\u0003+\tI\tC\u0004\u0002<E\u0001\r!!\u0006\u0002\u0013\u0011*\u0017\u000fJ3rI\u0015\fH\u0003BA\u0011\u0003\u001fCq!!%\u0013\u0001\u0004\t)\"\u0001\u0003uQ\u0006$\u0018a\u0003\u0013fc\u0012\u0012\u0017M\\4%KF$B!!\t\u0002\u0018\"9\u0011\u0011S\nA\u0002\u0005UA\u0003BA\u000b\u00037Cq!a\u000f\u0015\u0001\u0004\ti\n\u0005\u0003\u0002$\u0005}\u0015bAAQo\n!Aj\u001c8h)\u0011\t)\"!*\t\u000f\u0005mR\u00031\u0001\u0002\u001eR!\u0011QCAU\u0011\u001d\tYD\u0006a\u0001\u0003;#B!!\u0006\u0002.\"9\u00111H\fA\u0002\u0005uE\u0003BA\u000b\u0003cCq!a\u000f\u0019\u0001\u0004\ti\n\u0006\u0003\u0002`\u0005U\u0006bBA\u001e3\u0001\u0007\u0011Q\u0014\u000b\u0005\u0003+\tI\fC\u0004\u0002<i\u0001\r!!(\u0015\t\u0005U\u0011Q\u0018\u0005\b\u0003wY\u0002\u0019AAO)\u0011\ty&!1\t\u000f\u0005mB\u00041\u0001\u0002\u001eR!\u0011QCAc\u0011\u001d\tY$\ba\u0001\u0003;#B!!\u0006\u0002J\"9\u00111\b\u0010A\u0002\u0005uE\u0003BA\u000b\u0003\u001bDq!a\u000f \u0001\u0004\ti\n\u0006\u0003\u0002\u0016\u0005E\u0007bBA\u001eA\u0001\u0007\u00111\u001b\t\u0004\u007f\u0006U\u0017\u0002BAl\u0003'\u0011aAQ5h\u0013:$H\u0003BA\u000b\u00037Dq!a\u000f\"\u0001\u0004\t\u0019\u000e\u0006\u0003\u0002\u0016\u0005}\u0007bBA\u001eE\u0001\u0007\u00111\u001b\u000b\u0005\u0003+\t\u0019\u000fC\u0004\u0002<\r\u0002\r!a5\u0015\t\u0005U\u0011q\u001d\u0005\b\u0003w!\u0003\u0019AAj)\u0011\ty&a;\t\u000f\u0005mR\u00051\u0001\u0002TR!\u0011QCAx\u0011\u001d\tYD\na\u0001\u0003'$B!!\u0006\u0002t\"9\u00111H\u0014A\u0002\u0005MG\u0003BA0\u0003oDq!a\u000f)\u0001\u0004\t\u0019\u000e\u0006\u0003\u0002\u0016\u0005m\bbBA\u001eS\u0001\u0007\u00111\u001b\u000b\u0005\u0003+\ty\u0010C\u0004\u0002<)\u0002\r!a5\u0015\t\u0005U!1\u0001\u0005\b\u0003wY\u0003\u0019AAj)\u0011\t)Ba\u0002\t\u000f\u0005mB\u00061\u0001\u0003\nA!!1\u0002B\n\u001b\t\u0011iAC\u0002p\u0005\u001fQ!A!\u0005\u0002\t)\fg/Y\u0005\u0005\u0005+\u0011iA\u0001\u0006CS\u001eLe\u000e^3hKJ$B!!\u0006\u0003\u001a!9\u00111H\u0017A\u0002\t%A\u0003BA\u000b\u0005;Aq!a\u000f/\u0001\u0004\u0011I\u0001\u0006\u0003\u0002\u0016\t\u0005\u0002bBA\u001e_\u0001\u0007!\u0011\u0002\u000b\u0005\u0003+\u0011)\u0003C\u0004\u0002<A\u0002\rA!\u0003\u0015\t\u0005}#\u0011\u0006\u0005\b\u0003w\t\u0004\u0019\u0001B\u0005)\u0011\t)B!\f\t\u000f\u0005m\"\u00071\u0001\u0003\nQ!\u0011Q\u0003B\u0019\u0011\u001d\tYd\ra\u0001\u0005\u0013!B!a\u0018\u00036!9\u00111\b\u001bA\u0002\t%A\u0003BA\u000b\u0005sAq!a\u000f6\u0001\u0004\u0011I\u0001\u0006\u0003\u0002\u0016\tu\u0002bBA\u001em\u0001\u0007!\u0011\u0002\u000b\u0005\u0003+\u0011\t\u0005C\u0004\u0002<]\u0002\rA!\u0003\u0002\u00075Lg\u000e\u0006\u0003\u0002\u0016\t\u001d\u0003bBAIq\u0001\u0007\u0011QC\u0001\u0004[\u0006DH\u0003BA\u000b\u0005\u001bBq!!%:\u0001\u0004\t)\"\u0001\u0006%Y\u0016\u001c8\u000f\n7fgN$B!!\u0006\u0003T!9!Q\u000b\u001eA\u0002\u0005=\u0012!\u00018\u0002!\u0011:'/Z1uKJ$sM]3bi\u0016\u0014H\u0003BA\u000b\u00057BqA!\u0016<\u0001\u0004\ty#\u0001\u0007%i&lWm\u001d\u0013uS6,7\u000f\u0006\u0003\u0002\u0016\t\u0005\u0004b\u0002B2y\u0001\u0007\u0011qF\u0001\u0002W\u0006\u0019\u0001o\\<\u0015\t\u0005U!\u0011\u000e\u0005\b\u0005Gj\u0004\u0019AA\u0018\u0003\u0019iw\u000e\u001a)poR1\u0011Q\u0003B8\u0005cBqAa\u0019?\u0001\u0004\ty\u0003C\u0004\u0003ty\u0002\r!!\u0006\u0002\u00075|G-A\u0002bEN,\"!!\u0006\u0002\u0007\u001d\u001cG\r\u0006\u0003\u0002\u0016\tu\u0004bBAI\u0001\u0002\u0007\u0011QC\u0001\u0004Y\u000elG\u0003BA\u000b\u0005\u0007Cq!!%B\u0001\u0004\t)\"\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/A\u0006jgZ\u000bG.\u001b3M_:<\u0017aB4fi2{gnZ\u000b\u0003\u0005\u001b\u0003bAa$\u0003\u0016\u0006uUB\u0001BI\u0015\r\u0011\u0019\n]\u0001\u0005kRLG.\u0003\u0003\u0003\u0018\nE%aA(qi\u00061Ao\u001c\"zi\u0016,\"A!(\u0011\t\u0005\r\"qT\u0005\u0004\u0005C;(\u0001\u0002\"zi\u0016\fq\u0001^8TQ>\u0014H/\u0006\u0002\u0003(B!\u00111\u0005BU\u0013\r\u0011Yk\u001e\u0002\u0006'\"|'\u000f^\u0001\u0006i>Le\u000e^\u0001\ti>\u0014\u0015nZ%oiV\u0011\u00111[\u0001\ri>\u0014\u0015n\u001a#fG&l\u0017\r\\\u000b\u0003\u0005o\u00032a B]\u0013\u0011\u0011Y,a\u0005\u0003\u0015\tKw\rR3dS6\fG.\u0001\u0007u_\nKw-\u00138uK\u001e,'/\u0006\u0002\u0003\n\u0005AAo\\*ue&tw\r\u0006\u0002\u0003FB!!q\u0019Bh\u001d\u0011\u0011IMa3\u0011\u0007\u0005\rq/C\u0002\u0003N^\fa\u0001\u0015:fI\u00164\u0017\u0002\u0002Bi\u0005'\u0014aa\u0015;sS:<'b\u0001Bgo\u00069\u0011n],i_2,\u0017!B5t\u001f\u0012$\u0017AB5t\u000bZ,g.A\u0004jgB\u0013\u0018.\\3\u0002\r\u0019\f7\r^8s+\t\u0011\t\u000f\u0005\u0003\u0003d\n%XB\u0001Bs\u0015\r\u00119O\\\u0001\u0006aJLW.Z\u0005\u0005\u0005W\u0014)OA\u0004GC\u000e$xN]:\u0002\u001f%\u001c\bK]8cC\ndW\r\u0015:j[\u0016$B!!\t\u0003r\"9!1_)A\u0002\u0005=\u0012!C2feR\f\u0017N\u001c;z\u0003%\u0011\u0017\u000e\u001e'f]\u001e$\b.K\u0003\u0001\u0005s\u0014i0C\u0002\u0003|:\u0014!cU1gK2{gn\u001a\"jO&sG/Z4fe&\u0019!q 8\u0003\u0019M\u000bg-\u001a'p]\u001eduN\\4\u0002\u0011M\u000bg-\u001a'p]\u001e\u00042!a\u0006U'\u001d!6qAB\u0007\u0007'\u0001B!a\t\u0004\n%\u001911B<\u0003\r\u0005s\u0017PU3g!\u0011\t9ba\u0004\n\u0007\rEaNA\tTC\u001a,Gj\u001c8h\u0013:\u001cH/\u00198dKN\u0004Ba!\u0006\u0004\u001c5\u00111q\u0003\u0006\u0005\u00073\u0011y!\u0001\u0002j_&!1QDB\f\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\u0019\u0019!\u0001\u0005nS:,8o\u00148f\u0003%i\u0017N\\;t\u001f:,\u0007%\u0001\u0003{KJ|\u0017!\u0002>fe>\u0004\u0013aA8oK\u0006!qN\\3!\u0003\r!xo\\\u0001\u0005i^|\u0007%A\u0003uQJ,W-\u0001\u0004uQJ,W\rI\u0001\u0004i\u0016t\u0017\u0001\u0002;f]\u0002\nQAY5hmQ\naAY5hmQ\u0002\u0013AB:bM\u00164D'A\u0004tC\u001a,g\u0007\u000e\u0011\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005U1Q\t\u0005\b\u0007\u000f2\u0007\u0019AA\u0018\u0003\u0005AH\u0003BA\u000b\u0007\u0017Bqaa\u0012h\u0001\u0004\ti\n\u0006\u0003\u0002\u0016\r=\u0003bBB$Q\u0002\u0007\u00111\u001b\u000b\u0005\u0003+\u0019\u0019\u0006C\u0004\u0004V%\u0004\rA!2\u0002\u0003M\fq\u0001\\8oO\u001e\u001bG\r\u0006\u0004\u0002\u0016\rm3Q\f\u0005\b\u0007\u000fR\u0007\u0019AAO\u0011\u001d\u0019yF\u001ba\u0001\u0003;\u000b\u0011!_\u0001\t[&DX\rZ$dIR1\u0011QCB3\u0007OBqaa\u0012l\u0001\u0004\ti\nC\u0004\u0004`-\u0004\rA!\u0003\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r5\u0004\u0003BB8\u0007kj!a!\u001d\u000b\t\rM$qB\u0001\u0005Y\u0006tw-\u0003\u0003\u0004x\rE$AB(cU\u0016\u001cG\u000f"
)
public abstract class SafeLong extends ScalaNumber implements ScalaNumericConversions, Ordered {
   public static SafeLong mixedGcd(final long x, final BigInteger y) {
      return SafeLong$.MODULE$.mixedGcd(x, y);
   }

   public static SafeLong longGcd(final long x, final long y) {
      return SafeLong$.MODULE$.longGcd(x, y);
   }

   public static SafeLong apply(final BigInt x) {
      return SafeLong$.MODULE$.apply(x);
   }

   public static SafeLong apply(final long x) {
      return SafeLong$.MODULE$.apply(x);
   }

   public static SafeLong apply(final int x) {
      return SafeLong$.MODULE$.apply(x);
   }

   public static SafeLong ten() {
      return SafeLong$.MODULE$.ten();
   }

   public static SafeLong three() {
      return SafeLong$.MODULE$.three();
   }

   public static SafeLong two() {
      return SafeLong$.MODULE$.two();
   }

   public static SafeLong one() {
      return SafeLong$.MODULE$.one();
   }

   public static SafeLong zero() {
      return SafeLong$.MODULE$.zero();
   }

   public static SafeLong minusOne() {
      return SafeLong$.MODULE$.minusOne();
   }

   public static NumberTag SafeLongTag() {
      return SafeLong$.MODULE$.SafeLongTag();
   }

   public static SafeLongInstances.SafeLongIsReal$ SafeLongIsReal() {
      return SafeLong$.MODULE$.SafeLongIsReal();
   }

   public static SafeLongInstances.SafeLongAlgebra$ SafeLongAlgebra() {
      return SafeLong$.MODULE$.SafeLongAlgebra();
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidInt() {
      return ScalaNumericAnyConversions.isValidInt$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public abstract boolean isZero();

   public abstract boolean isOne();

   public abstract int signum();

   public final SafeLong $plus(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$plus(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$plus(n);
      }

      return var2;
   }

   public final SafeLong $minus(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$minus(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$minus(n);
      }

      return var2;
   }

   public final SafeLong $times(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$times(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$times(n);
      }

      return var2;
   }

   public final SafeLong $div(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$div(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$div(n);
      }

      return var2;
   }

   public final SafeLong $percent(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$percent(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$percent(n);
      }

      return var2;
   }

   public final SafeLong $div$tilde(final SafeLong rhs) {
      return this.$div(rhs);
   }

   public final Tuple2 $div$percent(final SafeLong rhs) {
      Tuple2 var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$div$percent(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$div$percent(n);
      }

      return var2;
   }

   public final SafeLong equot(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.equot(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.equot(n);
      }

      return var2;
   }

   public final SafeLong emod(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.emod(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.emod(n);
      }

      return var2;
   }

   public final Tuple2 equotmod(final SafeLong rhs) {
      Tuple2 var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.equotmod(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.equotmod(n);
      }

      return var2;
   }

   public final SafeLong $amp(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$amp(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$amp(n);
      }

      return var2;
   }

   public final SafeLong $bar(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$bar(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$bar(n);
      }

      return var2;
   }

   public final SafeLong $up(final SafeLong rhs) {
      SafeLong var2;
      if (rhs instanceof SafeLongLong) {
         SafeLongLong var4 = (SafeLongLong)rhs;
         long n = var4.x();
         var2 = this.$up(n);
      } else {
         if (!(rhs instanceof SafeLongBigInteger)) {
            throw new MatchError(rhs);
         }

         SafeLongBigInteger var7 = (SafeLongBigInteger)rhs;
         BigInteger n = var7.x();
         var2 = this.$up(n);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final SafeLong that) {
      return BoxesRunTime.equalsNumNum(this, that);
   }

   public boolean $eq$bang$eq(final SafeLong that) {
      return !this.$eq$eq$eq(that);
   }

   public abstract SafeLong $plus(final long rhs);

   public abstract SafeLong $minus(final long rhs);

   public abstract SafeLong $times(final long rhs);

   public abstract SafeLong $div(final long rhs);

   public abstract SafeLong $percent(final long rhs);

   public abstract Tuple2 $div$percent(final long rhs);

   public abstract SafeLong equot(final long rhs);

   public abstract SafeLong emod(final long rhs);

   public abstract Tuple2 equotmod(final long rhs);

   public abstract SafeLong $amp(final long rhs);

   public abstract SafeLong $bar(final long rhs);

   public abstract SafeLong $up(final long rhs);

   public final SafeLong $plus(final BigInt rhs) {
      return this.$plus(rhs.bigInteger());
   }

   public final SafeLong $minus(final BigInt rhs) {
      return this.$minus(rhs.bigInteger());
   }

   public final SafeLong $times(final BigInt rhs) {
      return this.$times(rhs.bigInteger());
   }

   public final SafeLong $div(final BigInt rhs) {
      return this.$div(rhs.bigInteger());
   }

   public final SafeLong $percent(final BigInt rhs) {
      return this.$percent(rhs.bigInteger());
   }

   public final Tuple2 $div$percent(final BigInt rhs) {
      return this.$div$percent(rhs.bigInteger());
   }

   public final SafeLong equot(final BigInt rhs) {
      return this.equot(rhs.bigInteger());
   }

   public final SafeLong emod(final BigInt rhs) {
      return this.emod(rhs.bigInteger());
   }

   public final Tuple2 equotmod(final BigInt rhs) {
      return this.equotmod(rhs.bigInteger());
   }

   public final SafeLong $amp(final BigInt rhs) {
      return this.$amp(rhs.bigInteger());
   }

   public final SafeLong $bar(final BigInt rhs) {
      return this.$bar(rhs.bigInteger());
   }

   public final SafeLong $up(final BigInt rhs) {
      return this.$up(rhs.bigInteger());
   }

   public abstract SafeLong $plus(final BigInteger rhs);

   public abstract SafeLong $minus(final BigInteger rhs);

   public abstract SafeLong $times(final BigInteger rhs);

   public abstract SafeLong $div(final BigInteger rhs);

   public abstract SafeLong $percent(final BigInteger rhs);

   public abstract Tuple2 $div$percent(final BigInteger rhs);

   public abstract SafeLong equot(final BigInteger rhs);

   public abstract SafeLong emod(final BigInteger rhs);

   public abstract Tuple2 equotmod(final BigInteger rhs);

   public abstract SafeLong $amp(final BigInteger rhs);

   public abstract SafeLong $bar(final BigInteger rhs);

   public abstract SafeLong $up(final BigInteger rhs);

   public final SafeLong min(final SafeLong that) {
      return this.$less(that) ? this : that;
   }

   public final SafeLong max(final SafeLong that) {
      return this.$greater(that) ? this : that;
   }

   public abstract SafeLong $less$less(final int n);

   public abstract SafeLong $greater$greater(final int n);

   public final SafeLong $times$times(final int k) {
      return this.pow(k);
   }

   public final SafeLong pow(final int k) {
      if (k < 0) {
         throw new IllegalArgumentException((new StringBuilder(19)).append("negative exponent: ").append(k).toString());
      } else {
         return this.loop$1(SafeLong$.MODULE$.one(), this, k);
      }
   }

   public final SafeLong modPow(final int k, final SafeLong mod) {
      if (k < 0) {
         throw new IllegalArgumentException((new StringBuilder(19)).append("negative exponent: ").append(k).toString());
      } else {
         return this.loop$2(SafeLong$.MODULE$.one().$percent(mod), this, k, mod);
      }
   }

   public abstract SafeLong abs();

   public abstract SafeLong gcd(final SafeLong that);

   public SafeLong lcm(final SafeLong that) {
      return !this.isZero() && !that.isZero() ? this.$div(this.gcd(that)).$times(that) : SafeLong$.MODULE$.zero();
   }

   public abstract SafeLong unary_$minus();

   public abstract boolean isValidLong();

   public abstract Long getLong();

   public byte toByte() {
      return (byte)((int)this.toLong());
   }

   public short toShort() {
      return (short)((int)this.toLong());
   }

   public int toInt() {
      return (int)this.toLong();
   }

   public final BigInt toBigInt() {
      return .MODULE$.javaBigInteger2bigInt(this.toBigInteger());
   }

   public abstract BigDecimal toBigDecimal();

   public abstract BigInteger toBigInteger();

   public String toString() {
      String var1;
      if (this instanceof SafeLongLong) {
         SafeLongLong var3 = (SafeLongLong)this;
         long n = var3.x();
         var1 = Long.toString(n);
      } else {
         if (!(this instanceof SafeLongBigInteger)) {
            throw new MatchError(this);
         }

         SafeLongBigInteger var6 = (SafeLongBigInteger)this;
         BigInteger n = var6.x();
         var1 = n.toString();
      }

      return var1;
   }

   public final boolean isWhole() {
      return true;
   }

   public abstract boolean isOdd();

   public abstract boolean isEven();

   public boolean isPrime() {
      return spire.math.prime.package$.MODULE$.isPrime(this);
   }

   public Factors factor() {
      return spire.math.prime.package$.MODULE$.factor(this);
   }

   public final boolean isProbablePrime(final int certainty) {
      return this.toBigInteger().isProbablePrime(certainty);
   }

   public abstract int bitLength();

   private final SafeLong loop$1(final SafeLong total, final SafeLong base, final int exp) {
      while(exp != 0) {
         if ((exp & 1) == 1) {
            SafeLong var10000 = total.$times(base);
            SafeLong var4 = base.$times(base);
            exp >>= 1;
            base = var4;
            total = var10000;
         } else {
            SafeLong var10001 = base.$times(base);
            exp >>= 1;
            base = var10001;
            total = total;
         }
      }

      return total;
   }

   private final SafeLong loop$2(final SafeLong total, final SafeLong base, final int k, final SafeLong mod) {
      while(k != 0) {
         if ((k & 1) == 1) {
            SafeLong var10000 = total.$times(base).$percent(mod);
            SafeLong var5 = base.$times(base).$percent(mod);
            int var6 = k >> 1;
            mod = mod;
            k = var6;
            base = var5;
            total = var10000;
         } else {
            SafeLong var10001 = base.$times(base).$percent(mod);
            int var10002 = k >> 1;
            mod = mod;
            k = var10002;
            base = var10001;
            total = total;
         }
      }

      return total;
   }

   public SafeLong() {
      ScalaNumericAnyConversions.$init$(this);
      Ordered.$init$(this);
   }
}
