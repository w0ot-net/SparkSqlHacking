package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.math.MutableVectorField;
import breeze.optimize.linear.ConjugateGradient;
import breeze.util.Implicits$;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple12;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IndexedSeqOps;
import scala.collection.Iterator;
import scala.collection.immutable.IndexedSeq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction12;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u001da\u0001\u0002<x\u0001qD!\"a\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA!\u0011)\t9\u0005\u0001B\u0001B\u0003%\u0011\u0011\n\u0005\u000b\u0003\u001f\u0002!\u0011!Q\u0001\n\u0005%\u0003BCA)\u0001\t\u0005\t\u0015!\u0003\u0002B!Q\u00111\u000b\u0001\u0003\u0002\u0003\u0006Y!!\u0016\t\u0015\u0005\u0005\u0004A!A!\u0002\u0017\t\u0019\u0007C\u0004\u0002\u0002\u0002!\t!a!\t\u000f\u0005U\u0005\u0001\"\u0001\u0002\u0018\u001a1\u0011\u0011\u0015\u0001A\u0003GC!\"a1\n\u0005+\u0007I\u0011AAc\u0011)\t9-\u0003B\tB\u0003%\u0011\u0011\t\u0005\u000b\u0003\u0013L!Q3A\u0005\u0002\u0005-\u0007BCAg\u0013\tE\t\u0015!\u0003\u0002J!Q\u0011qZ\u0005\u0003\u0016\u0004%\t!a3\t\u0015\u0005E\u0017B!E!\u0002\u0013\tI\u0005\u0003\u0006\u0002T&\u0011)\u001a!C\u0001\u0003+D!\"a6\n\u0005#\u0005\u000b\u0011BA\t\u0011)\tI.\u0003BK\u0002\u0013\u0005\u00111\u001a\u0005\u000b\u00037L!\u0011#Q\u0001\n\u0005%\u0003BCAo\u0013\tU\r\u0011\"\u0001\u0002V\"Q\u0011q\\\u0005\u0003\u0012\u0003\u0006I!!\u0005\t\u0015\u0005\u0005\u0018B!f\u0001\n\u0003\t\u0019\u000f\u0003\u0006\u0002f&\u0011\t\u0012)A\u0005\u0003[A!\"a:\n\u0005+\u0007I\u0011AAf\u0011)\tI/\u0003B\tB\u0003%\u0011\u0011\n\u0005\u000b\u0003WL!Q3A\u0005\u0002\u0005U\u0007BCAw\u0013\tE\t\u0015!\u0003\u0002\u0012!Q\u0011q^\u0005\u0003\u0016\u0004%\t!!=\t\u0015\u0005e\u0018B!E!\u0002\u0013\t\u0019\u0010\u0003\u0006\u0002|&\u0011)\u001a!C\u0001\u0003cD!\"!@\n\u0005#\u0005\u000b\u0011BAz\u0011)\ty0\u0003BK\u0002\u0013\u0005!\u0011\u0001\u0005\u000b\u0005\u0017K!\u0011#Q\u0001\n\t\r\u0001bBAA\u0013\u0011\u0005!Q\u0012\u0005\b\u0005SKA\u0011AAy\u0011%\u0011\t#CA\u0001\n\u0003\u0011Y\u000bC\u0005\u0003*%\t\n\u0011\"\u0001\u0003F\"I!\u0011I\u0005\u0012\u0002\u0013\u0005!\u0011\u001a\u0005\n\u0005\u001bL\u0011\u0013!C\u0001\u0005\u0013D\u0011Ba4\n#\u0003%\tA!5\t\u0013\tU\u0017\"%A\u0005\u0002\t%\u0007\"\u0003Bl\u0013E\u0005I\u0011\u0001Bi\u0011%\u0011I.CI\u0001\n\u0003\u0011Y\u000eC\u0005\u0003`&\t\n\u0011\"\u0001\u0003J\"I!\u0011]\u0005\u0012\u0002\u0013\u0005!\u0011\u001b\u0005\n\u0005GL\u0011\u0013!C\u0001\u0005KD\u0011B!;\n#\u0003%\tA!:\t\u0013\t-\u0018\"%A\u0005\u0002\t5\b\"\u0003B\"\u0013\u0005\u0005I\u0011\tB#\u0011%\u00119&CA\u0001\n\u0003\t)\rC\u0005\u0003Z%\t\t\u0011\"\u0001\u0003r\"I!\u0011M\u0005\u0002\u0002\u0013\u0005#1\r\u0005\n\u0005cJ\u0011\u0011!C\u0001\u0005kD\u0011Ba\u001e\n\u0003\u0003%\tE!?\t\u0013\tu\u0014\"!A\u0005B\t}\u0004\"\u0003BA\u0013\u0005\u0005I\u0011\tBB\u0011%\u0011))CA\u0001\n\u0003\u0012ipB\u0005\u0004\u0002\u0001\t\t\u0011#\u0001\u0004\u0004\u0019I\u0011\u0011\u0015\u0001\u0002\u0002#\u00051Q\u0001\u0005\b\u0003\u0003[D\u0011AB\u000f\u0011%\u0011\tiOA\u0001\n\u000b\u0012\u0019\tC\u0005\u0004 m\n\t\u0011\"!\u0004\"!I11H\u001e\u0002\u0002\u0013\u00055Q\b\u0005\b\u0007\u001f\u0002A\u0011BB)\u0011%\u00199\u0006\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004Z\u0001\u0001\u000b\u0011BA%\u0011%\u0019Y\u0006\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004^\u0001\u0001\u000b\u0011BA%\u0011%\u0019y\u0006\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004b\u0001\u0001\u000b\u0011BA%\u0011%\u0019\u0019\u0007\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004f\u0001\u0001\u000b\u0011BA%\u0011%\u00199\u0007\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004j\u0001\u0001\u000b\u0011BA%\u0011%\u0019Y\u0007\u0001b\u0001\n\u0013\tY\r\u0003\u0005\u0004n\u0001\u0001\u000b\u0011BA%\u0011\u001d\u0019y\u0007\u0001C\u0001\u0007c2aAa\u0002\u0001\u0001\n%\u0001B\u0003B\u0006\u001d\nU\r\u0011\"\u0001\u0003\u000e!Q!Q\u0003(\u0003\u0012\u0003\u0006IAa\u0004\t\u0015\t]aJ!f\u0001\n\u0003\u0011i\u0001\u0003\u0006\u0003\u001a9\u0013\t\u0012)A\u0005\u0005\u001fAq!!!O\t\u0003\u0011Y\u0002C\u0005\u0003\"9\u000b\t\u0011\"\u0001\u0003$!I!\u0011\u0006(\u0012\u0002\u0013\u0005!1\u0006\u0005\n\u0005\u0003r\u0015\u0013!C\u0001\u0005WA\u0011Ba\u0011O\u0003\u0003%\tE!\u0012\t\u0013\t]c*!A\u0005\u0002\u0005\u0015\u0007\"\u0003B-\u001d\u0006\u0005I\u0011\u0001B.\u0011%\u0011\tGTA\u0001\n\u0003\u0012\u0019\u0007C\u0005\u0003r9\u000b\t\u0011\"\u0001\u0003t!I!q\u000f(\u0002\u0002\u0013\u0005#\u0011\u0010\u0005\n\u0005{r\u0015\u0011!C!\u0005\u007fB\u0011B!!O\u0003\u0003%\tEa!\t\u0013\t\u0015e*!A\u0005B\t\u001du!CB>\u0001\u0005\u0005\t\u0012AB?\r%\u00119\u0001AA\u0001\u0012\u0003\u0019y\bC\u0004\u0002\u0002\u0006$\taa\"\t\u0013\t\u0005\u0015-!A\u0005F\t\r\u0005\"CB\u0010C\u0006\u0005I\u0011QBE\u0011%\u0019y)YI\u0001\n\u0003\u0011Y\u0003C\u0005\u0004\u0012\u0006\f\n\u0011\"\u0001\u0003,!I11H1\u0002\u0002\u0013\u000551\u0013\u0005\n\u0007?\u000b\u0017\u0013!C\u0001\u0005WA\u0011b!)b#\u0003%\tAa\u000b\t\u000f\r\r\u0006\u0001\"\u0005\u0004&\"91\u0011\u0017\u0001\u0005\u0012\rM\u0006bBB]\u0001\u0011%11\u0018\u0005\b\u0007\u000b\u0004A\u0011CBd\u000f%\u0019In^A\u0001\u0012\u0003\u0019YN\u0002\u0005wo\u0006\u0005\t\u0012ABo\u0011\u001d\t\ti\u001cC\u0001\u0007?D\u0011ba(p#\u0003%\ta!9\t\u0013\r\u0005v.%A\u0005\u0002\r\u001d\b\"CBw_F\u0005I\u0011ABx\u0011%\u0019)p\\I\u0001\n\u0003\u00199\u0010C\u0005\u0004~>\f\t\u0011\"\u0003\u0004\u0000\nABK];oG\u0006$X\r\u001a(foR|g.T5oS6L'0\u001a:\u000b\u0005aL\u0018\u0001C8qi&l\u0017N_3\u000b\u0003i\faA\u0019:fKj,7\u0001A\u000b\u0006{\u0006U\u0011qF\n\u0007\u0001y\fI!a\r\u0011\u0007}\f)!\u0004\u0002\u0002\u0002)\u0011\u00111A\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003\u000f\t\tA\u0001\u0004B]f\u0014VM\u001a\t\t\u0003\u0017\ti!!\u0005\u0002(5\tq/C\u0002\u0002\u0010]\u0014\u0011\"T5oS6L'0\u001a:\u0011\t\u0005M\u0011Q\u0003\u0007\u0001\t\u001d\t9\u0002\u0001b\u0001\u00033\u0011\u0011\u0001V\t\u0005\u00037\t\t\u0003E\u0002\u0000\u0003;IA!a\b\u0002\u0002\t9aj\u001c;iS:<\u0007cA@\u0002$%!\u0011QEA\u0001\u0005\r\te.\u001f\t\t\u0003\u0017\tI#!\u0005\u0002.%\u0019\u00111F<\u0003'M+7m\u001c8e\u001fJ$WM\u001d$v]\u000e$\u0018n\u001c8\u0011\t\u0005M\u0011q\u0006\u0003\b\u0003c\u0001!\u0019AA\r\u0005\u0005A\u0005\u0003BA\u001b\u0003wi!!a\u000e\u000b\u0007\u0005e\u00120\u0001\u0003vi&d\u0017\u0002BA\u001f\u0003o\u00111cU3sS\u0006d\u0017N_1cY\u0016dunZ4j]\u001e\fQ\"\\1y\u0013R,'/\u0019;j_:\u001c\bcA@\u0002D%!\u0011QIA\u0001\u0005\rIe\u000e^\u0001\ni>dWM]1oG\u0016\u00042a`A&\u0013\u0011\ti%!\u0001\u0003\r\u0011{WO\u00197f\u0003Aa'GU3hk2\f'/\u001b>bi&|g.A\u0001n\u0003\u0015\u0019\b/Y2f!!\t9&!\u0018\u0002\u0012\u0005%SBAA-\u0015\r\tY&_\u0001\u0005[\u0006$\b.\u0003\u0003\u0002`\u0005e#AE'vi\u0006\u0014G.\u001a,fGR|'OR5fY\u0012\fA!\\;miBQ\u0011QMA;\u0003[\t\t\"!\u0005\u000f\t\u0005\u001d\u0014\u0011O\u0007\u0003\u0003SRA!a\u001b\u0002n\u0005Iq\u000e]3sCR|'o\u001d\u0006\u0004\u0003_J\u0018A\u00027j]\u0006dw-\u0003\u0003\u0002t\u0005%\u0014aC(q\u001bVdW*\u0019;sSbLA!a\u001e\u0002z\t)\u0011*\u001c9me%!\u00111PA?\u0005\u0015)f)\u001e8d\u0015\r\ty(_\u0001\bO\u0016tWM]5d\u0003\u0019a\u0014N\\5u}QQ\u0011QQAG\u0003\u001f\u000b\t*a%\u0015\r\u0005\u001d\u0015\u0011RAF!\u001d\tY\u0001AA\t\u0003[Aq!a\u0015\b\u0001\b\t)\u0006C\u0004\u0002b\u001d\u0001\u001d!a\u0019\t\u0013\u0005}r\u0001%AA\u0002\u0005\u0005\u0003\"CA$\u000fA\u0005\t\u0019AA%\u0011%\tye\u0002I\u0001\u0002\u0004\tI\u0005C\u0005\u0002R\u001d\u0001\n\u00111\u0001\u0002B\u0005AQ.\u001b8j[&TX\r\u0006\u0004\u0002\u0012\u0005e\u0015Q\u0014\u0005\b\u00037C\u0001\u0019AA\u0014\u0003\u00051\u0007bBAP\u0011\u0001\u0007\u0011\u0011C\u0001\bS:LG/[1m\u0005\u0015\u0019F/\u0019;f'\u0019Ia0!*\u0002,B\u0019q0a*\n\t\u0005%\u0016\u0011\u0001\u0002\b!J|G-^2u!\u0011\ti+!0\u000f\t\u0005=\u0016\u0011\u0018\b\u0005\u0003c\u000b9,\u0004\u0002\u00024*\u0019\u0011QW>\u0002\rq\u0012xn\u001c;?\u0013\t\t\u0019!\u0003\u0003\u0002<\u0006\u0005\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003\u007f\u000b\tM\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002<\u0006\u0005\u0011\u0001B5uKJ,\"!!\u0011\u0002\u000b%$XM\u001d\u0011\u0002\u0019%t\u0017\u000e^5bY\u001esuN]7\u0016\u0005\u0005%\u0013!D5oSRL\u0017\r\\$O_Jl\u0007%A\u0003eK2$\u0018-\u0001\u0004eK2$\u0018\rI\u0001\u0002qV\u0011\u0011\u0011C\u0001\u0003q\u0002\nAA\u001a<bY\u0006)aM^1mA\u0005!qM]1e\u0003\u00159'/\u00193!\u0003\u0005AWCAA\u0017\u0003\tA\u0007%A\u0004bI*4e/\u00197\u0002\u0011\u0005$'N\u0012<bY\u0002\nq!\u00193k\u000fJ\fG-\u0001\u0005bI*<%/\u00193!\u0003\u0011\u0019Ho\u001c9\u0016\u0005\u0005M\bcA@\u0002v&!\u0011q_A\u0001\u0005\u001d\u0011un\u001c7fC:\fQa\u001d;pa\u0002\na!Y2dKB$\u0018aB1dG\u0016\u0004H\u000fI\u0001\bQ&\u001cHo\u001c:z+\t\u0011\u0019\u0001E\u0002\u0003\u00069k\u0011\u0001\u0001\u0002\b\u0011&\u001cHo\u001c:z'\u0019qe0!*\u0002,\u00069Q.Z7Ti\u0016\u0004XC\u0001B\b!\u0019\tiK!\u0005\u0002\u0012%!!1CAa\u0005)Ie\u000eZ3yK\u0012\u001cV-]\u0001\t[\u0016l7\u000b^3qA\u0005aQ.Z7He\u0006$G)\u001a7uC\u0006iQ.Z7He\u0006$G)\u001a7uC\u0002\"bAa\u0001\u0003\u001e\t}\u0001\"\u0003B\u0006'B\u0005\t\u0019\u0001B\b\u0011%\u00119b\u0015I\u0001\u0002\u0004\u0011y!\u0001\u0003d_BLHC\u0002B\u0002\u0005K\u00119\u0003C\u0005\u0003\fQ\u0003\n\u00111\u0001\u0003\u0010!I!q\u0003+\u0011\u0002\u0003\u0007!qB\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011iC\u000b\u0003\u0003\u0010\t=2F\u0001B\u0019!\u0011\u0011\u0019D!\u0010\u000e\u0005\tU\"\u0002\u0002B\u001c\u0005s\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\tm\u0012\u0011A\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002B \u0005k\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001B$!\u0011\u0011IEa\u0015\u000e\u0005\t-#\u0002\u0002B'\u0005\u001f\nA\u0001\\1oO*\u0011!\u0011K\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003V\t-#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0005\"Q\f\u0005\n\u0005?J\u0016\u0011!a\u0001\u0003\u0003\n1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B3!\u0019\u00119G!\u001c\u0002\"5\u0011!\u0011\u000e\u0006\u0005\u0005W\n\t!\u0001\u0006d_2dWm\u0019;j_:LAAa\u001c\u0003j\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019P!\u001e\t\u0013\t}3,!AA\u0002\u0005\u0005\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BAa\u0012\u0003|!I!q\f/\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011I\u0001\ti>\u001cFO]5oOR\u0011!qI\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005M(\u0011\u0012\u0005\n\u0005?z\u0016\u0011!a\u0001\u0003C\t\u0001\u0002[5ti>\u0014\u0018\u0010\t\u000b\u001b\u0005\u001f\u0013\tJa%\u0003\u0016\n]%\u0011\u0014BN\u0005;\u0013yJ!)\u0003$\n\u0015&q\u0015\t\u0004\u0005\u000bI\u0001bBAbE\u0001\u0007\u0011\u0011\t\u0005\b\u0003\u0013\u0014\u0003\u0019AA%\u0011\u001d\tyM\ta\u0001\u0003\u0013Bq!a5#\u0001\u0004\t\t\u0002C\u0004\u0002Z\n\u0002\r!!\u0013\t\u000f\u0005u'\u00051\u0001\u0002\u0012!9\u0011\u0011\u001d\u0012A\u0002\u00055\u0002bBAtE\u0001\u0007\u0011\u0011\n\u0005\b\u0003W\u0014\u0003\u0019AA\t\u0011\u001d\tyO\ta\u0001\u0003gDq!a?#\u0001\u0004\t\u0019\u0010C\u0004\u0002\u0000\n\u0002\rAa\u0001\u0002\u0013\r|gN^3sO\u0016$GC\u0007BH\u0005[\u0013yK!-\u00034\nU&q\u0017B]\u0005w\u0013iLa0\u0003B\n\r\u0007\"CAbIA\u0005\t\u0019AA!\u0011%\tI\r\nI\u0001\u0002\u0004\tI\u0005C\u0005\u0002P\u0012\u0002\n\u00111\u0001\u0002J!I\u00111\u001b\u0013\u0011\u0002\u0003\u0007\u0011\u0011\u0003\u0005\n\u00033$\u0003\u0013!a\u0001\u0003\u0013B\u0011\"!8%!\u0003\u0005\r!!\u0005\t\u0013\u0005\u0005H\u0005%AA\u0002\u00055\u0002\"CAtIA\u0005\t\u0019AA%\u0011%\tY\u000f\nI\u0001\u0002\u0004\t\t\u0002C\u0005\u0002p\u0012\u0002\n\u00111\u0001\u0002t\"I\u00111 \u0013\u0011\u0002\u0003\u0007\u00111\u001f\u0005\n\u0003\u007f$\u0003\u0013!a\u0001\u0005\u0007)\"Aa2+\t\u0005\u0005#qF\u000b\u0003\u0005\u0017TC!!\u0013\u00030\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0005'TC!!\u0005\u00030\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014AD2paf$C-\u001a4bk2$HEN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+\t\u0011iN\u000b\u0003\u0002.\t=\u0012AD2paf$C-\u001a4bk2$H\u0005O\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u0013:\u0003=\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE\u0002TC\u0001BtU\u0011\t\u0019Pa\f\u0002\u001f\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cE\nqbY8qs\u0012\"WMZ1vYR$\u0013GM\u000b\u0003\u0005_TCAa\u0001\u00030Q!\u0011\u0011\u0005Bz\u0011%\u0011yfMA\u0001\u0002\u0004\t\t\u0005\u0006\u0003\u0002t\n]\b\"\u0003B0k\u0005\u0005\t\u0019AA\u0011)\u0011\u00119Ea?\t\u0013\t}c'!AA\u0002\u0005\u0005C\u0003BAz\u0005\u007fD\u0011Ba\u0018:\u0003\u0003\u0005\r!!\t\u0002\u000bM#\u0018\r^3\u0011\u0007\t\u00151hE\u0003<\u0007\u000f\u0019\u0019\u0002\u0005\u0010\u0004\n\r=\u0011\u0011IA%\u0003\u0013\n\t\"!\u0013\u0002\u0012\u00055\u0012\u0011JA\t\u0003g\f\u0019Pa\u0001\u0003\u00106\u001111\u0002\u0006\u0005\u0007\u001b\t\t!A\u0004sk:$\u0018.\\3\n\t\rE11\u0002\u0002\u0013\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f$\u0007\u0005\u0003\u0004\u0016\rmQBAB\f\u0015\u0011\u0019IBa\u0014\u0002\u0005%|\u0017\u0002BA`\u0007/!\"aa\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u00155\t=51EB\u0013\u0007O\u0019Ica\u000b\u0004.\r=2\u0011GB\u001a\u0007k\u00199d!\u000f\t\u000f\u0005\rg\b1\u0001\u0002B!9\u0011\u0011\u001a A\u0002\u0005%\u0003bBAh}\u0001\u0007\u0011\u0011\n\u0005\b\u0003't\u0004\u0019AA\t\u0011\u001d\tIN\u0010a\u0001\u0003\u0013Bq!!8?\u0001\u0004\t\t\u0002C\u0004\u0002bz\u0002\r!!\f\t\u000f\u0005\u001dh\b1\u0001\u0002J!9\u00111\u001e A\u0002\u0005E\u0001bBAx}\u0001\u0007\u00111\u001f\u0005\b\u0003wt\u0004\u0019AAz\u0011\u001d\tyP\u0010a\u0001\u0005\u0007\tq!\u001e8baBd\u0017\u0010\u0006\u0003\u0004@\r-\u0003#B@\u0004B\r\u0015\u0013\u0002BB\"\u0003\u0003\u0011aa\u00149uS>t\u0007cG@\u0004H\u0005\u0005\u0013\u0011JA%\u0003#\tI%!\u0005\u0002.\u0005%\u0013\u0011CAz\u0003g\u0014\u0019!\u0003\u0003\u0004J\u0005\u0005!a\u0002+va2,\u0017G\r\u0005\n\u0007\u001bz\u0014\u0011!a\u0001\u0005\u001f\u000b1\u0001\u001f\u00131\u00031Ig.\u001b;jC2\u001cF/\u0019;f)\u0019\u0011yia\u0015\u0004V!9\u00111\u0014!A\u0002\u0005\u001d\u0002bBAP\u0001\u0002\u0007\u0011\u0011C\u0001\u0005KR\f\u0007'A\u0003fi\u0006\u0004\u0004%\u0001\u0003fi\u0006\f\u0014!B3uCF\u0002\u0013\u0001B3uCJ\nQ!\u001a;be\u0001\naa]5h[\u0006\f\u0014aB:jO6\f\u0017\u0007I\u0001\u0007g&<W.\u0019\u001a\u0002\u000fMLw-\\13A\u000511/[4nCN\nqa]5h[\u0006\u001c\u0004%\u0001\u0006ji\u0016\u0014\u0018\r^5p]N$baa\u001d\u0004x\re\u0004CBAW\u0007k\u0012y)\u0003\u0003\u0003p\u0005\u0005\u0007bBAN\u001b\u0002\u0007\u0011q\u0005\u0005\b\u0003?k\u0005\u0019AA\t\u0003\u001dA\u0015n\u001d;pef\u00042A!\u0002b'\u0015\t7\u0011QB\n!)\u0019Iaa!\u0003\u0010\t=!1A\u0005\u0005\u0007\u000b\u001bYAA\tBEN$(/Y2u\rVt7\r^5p]J\"\"a! \u0015\r\t\r11RBG\u0011%\u0011Y\u0001\u001aI\u0001\u0002\u0004\u0011y\u0001C\u0005\u0003\u0018\u0011\u0004\n\u00111\u0001\u0003\u0010\u0005y\u0011\r\u001d9ms\u0012\"WMZ1vYR$\u0013'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00133)\u0011\u0019)j!(\u0011\u000b}\u001c\tea&\u0011\u000f}\u001cIJa\u0004\u0003\u0010%!11TA\u0001\u0005\u0019!V\u000f\u001d7fe!I1QJ4\u0002\u0002\u0003\u0007!1A\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133\u00039Ig.\u001b;jC2D\u0015n\u001d;pef$bAa\u0001\u0004(\u000e=\u0006bBANU\u0002\u00071\u0011\u0016\t\u0007\u0003\u0017\u0019Y+!\u0005\n\u0007\r5vO\u0001\u0007ES\u001a4g)\u001e8di&|g\u000eC\u0004\u0002T*\u0004\r!!\u0005\u0002-\rDwn\\:f\t\u0016\u001c8-\u001a8u\t&\u0014Xm\u0019;j_:$B!!\u0005\u00046\"91qW6A\u0002\t=\u0015!B:uCR,\u0017\u0001E2p[B,H/\u001a#jC\u001e\u001c6-\u00197f)\u0019\tIe!0\u0004B\"91q\u00187A\u0002\u0005E\u0011\u0001\u00039sKZ\u001cF/\u001a9\t\u000f\r\rG\u000e1\u0001\u0002\u0012\u0005a\u0001O]3w\u000fJ\fGm\u0015;fa\u0006iQ\u000f\u001d3bi\u0016D\u0015n\u001d;pef$\"Ba\u0001\u0004J\u000e57\u0011[Bk\u0011\u001d\u0019Y-\u001ca\u0001\u0003#\tAA\\3x1\"91qZ7A\u0002\u0005E\u0011a\u00028fo\u001e\u0013\u0018\r\u001a\u0005\b\u0007'l\u0007\u0019AA%\u0003\u0019qWm\u001e,bY\"91q[7A\u0002\t=\u0015\u0001C8mIN#\u0018\r^3\u00021Q\u0013XO\\2bi\u0016$g*Z<u_:l\u0015N\\5nSj,'\u000fE\u0002\u0002\f=\u001cBa\u001c@\u0004\u0014Q\u001111\\\u000b\u0007\u0005\u000b\u001c\u0019o!:\u0005\u000f\u0005]\u0011O1\u0001\u0002\u001a\u00119\u0011\u0011G9C\u0002\u0005eQC\u0002Be\u0007S\u001cY\u000fB\u0004\u0002\u0018I\u0014\r!!\u0007\u0005\u000f\u0005E\"O1\u0001\u0002\u001a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*bA!3\u0004r\u000eMHaBA\fg\n\u0007\u0011\u0011\u0004\u0003\b\u0003c\u0019(\u0019AA\r\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU1!QYB}\u0007w$q!a\u0006u\u0005\u0004\tI\u0002B\u0004\u00022Q\u0014\r!!\u0007\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0011\u0005\u0001\u0003\u0002B%\t\u0007IA\u0001\"\u0002\u0003L\t1qJ\u00196fGR\u0004"
)
public class TruncatedNewtonMinimizer implements Minimizer, SerializableLogging {
   private volatile State$ State$module;
   private volatile History$ History$module;
   public final int breeze$optimize$TruncatedNewtonMinimizer$$maxIterations;
   public final double breeze$optimize$TruncatedNewtonMinimizer$$tolerance;
   private final double l2Regularization;
   private final int m;
   public final MutableVectorField breeze$optimize$TruncatedNewtonMinimizer$$space;
   private final UFunc.UImpl2 mult;
   private final double eta0;
   private final double eta1;
   private final double eta2;
   private final double sigma1;
   private final double sigma2;
   private final double sigma3;
   private transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   public static int $lessinit$greater$default$4() {
      return TruncatedNewtonMinimizer$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return TruncatedNewtonMinimizer$.MODULE$.$lessinit$greater$default$3();
   }

   public static double $lessinit$greater$default$2() {
      return TruncatedNewtonMinimizer$.MODULE$.$lessinit$greater$default$2();
   }

   public static int $lessinit$greater$default$1() {
      return TruncatedNewtonMinimizer$.MODULE$.$lessinit$greater$default$1();
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public History$ History() {
      if (this.History$module == null) {
         this.History$lzycompute$1();
      }

      return this.History$module;
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return this.breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      this.breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public Object minimize(final SecondOrderFunction f, final Object initial) {
      return ((State)Implicits$.MODULE$.scEnrichIterator(Implicits$.MODULE$.scEnrichIterator(this.iterations(f, initial)).takeUpToWhere((x$1) -> BoxesRunTime.boxToBoolean($anonfun$minimize$1(x$1)))).last()).x();
   }

   private State initialState(final SecondOrderFunction f, final Object initial) {
      Tuple3 var5 = f.calculate2(initial);
      if (var5 != null) {
         double v = BoxesRunTime.unboxToDouble(var5._1());
         Object grad = var5._2();
         Object h = var5._3();
         Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(v), grad, h);
         double v = BoxesRunTime.unboxToDouble(var3._1());
         Object grad = var3._2();
         Object h = var3._3();
         Object adjgrad = ((NumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(grad)).$plus(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(initial)).$times(BoxesRunTime.boxToDouble(this.l2Regularization), this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulVS_M()), this.breeze$optimize$TruncatedNewtonMinimizer$$space.addVV());
         double initDelta = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(adjgrad, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()));
         double adjfval = v + (double)0.5F * this.l2Regularization * BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(initial)).dot(initial, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
         boolean f_too_small = adjfval < -1.0E32;
         return new State(0, initDelta, initDelta, initial, v, grad, h, adjfval, adjgrad, f_too_small, true, this.initialHistory(f, initial));
      } else {
         throw new MatchError(var5);
      }
   }

   private double eta0() {
      return this.eta0;
   }

   private double eta1() {
      return this.eta1;
   }

   private double eta2() {
      return this.eta2;
   }

   private double sigma1() {
      return this.sigma1;
   }

   private double sigma2() {
      return this.sigma2;
   }

   private double sigma3() {
      return this.sigma3;
   }

   public Iterator iterations(final SecondOrderFunction f, final Object initial) {
      return .MODULE$.Iterator().iterate(this.initialState(f, initial), (state) -> {
         double x$1 = state.delta();
         double x$2 = 0.1 * BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(state.adjGrad(), this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()));
         int x$3 = 400;
         double x$4 = this.l2Regularization;
         ConjugateGradient cg = new ConjugateGradient(x$1, 400, x$4, x$2, this.breeze$optimize$TruncatedNewtonMinimizer$$space, this.mult);
         Object initStep = this.chooseDescentDirection(state);
         Tuple2 var15 = cg.minimizeAndReturnResidual(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(state.adjGrad())).unary_$minus(this.breeze$optimize$TruncatedNewtonMinimizer$$space.neg()), state.h(), initStep);
         if (var15 == null) {
            throw new MatchError(var15);
         } else {
            Object step = var15._1();
            Object residual = var15._2();
            Tuple2 var4 = new Tuple2(step, residual);
            Object stepx = var4._1();
            Object residualx = var4._2();
            Object x_new = ((NumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(state.x())).$plus(stepx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.addVV());
            double gs = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(state.adjGrad())).dot(stepx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
            double predictedReduction = (double)-0.5F * (gs - BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(stepx)).dot(residualx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV())));
            Tuple3 var26 = f.calculate2(x_new);
            if (var26 != null) {
               double newv = BoxesRunTime.unboxToDouble(var26._1());
               Object newg = var26._2();
               Object newh = var26._3();
               Tuple3 var3 = new Tuple3(BoxesRunTime.boxToDouble(newv), newg, newh);
               double newvx = BoxesRunTime.unboxToDouble(var3._1());
               Object newgx = var3._2();
               Object newhx = var3._3();
               Object adjNewG = ((NumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(newgx)).$plus(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(x_new)).$times(BoxesRunTime.boxToDouble(this.l2Regularization), this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulVS_M()), this.breeze$optimize$TruncatedNewtonMinimizer$$space.addVV());
               double adjNewV = newvx + (double)0.5F * this.l2Regularization * BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(x_new)).dot(x_new, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
               double actualReduction = state.adjFval() - adjNewV;
               double stepNorm = BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(stepx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()));
               double newDelta = state.iter() == 1 ? scala.runtime.RichDouble..MODULE$.min$extension(scala.Predef..MODULE$.doubleWrapper(state.delta()), stepNorm * (double)3) : state.delta();
               double alpha = -actualReduction <= gs ? this.sigma3() : scala.runtime.RichDouble..MODULE$.max$extension(scala.Predef..MODULE$.doubleWrapper(this.sigma1()), (double)-0.5F * (gs / (-actualReduction - gs)));
               newDelta = actualReduction < this.eta0() * predictedReduction ? scala.math.package..MODULE$.min(scala.math.package..MODULE$.max(alpha, this.sigma1()) * stepNorm, this.sigma2() * newDelta) : (actualReduction < this.eta1() * predictedReduction ? scala.math.package..MODULE$.max(this.sigma1() * newDelta, scala.math.package..MODULE$.min(alpha * stepNorm, this.sigma2() * newDelta)) : (actualReduction < this.eta2() * predictedReduction ? scala.math.package..MODULE$.max(this.sigma1() * newDelta, scala.math.package..MODULE$.min(alpha * stepNorm, this.sigma3() * newDelta)) : scala.math.package..MODULE$.max(newDelta, scala.math.package..MODULE$.min((double)10 * stepNorm, this.sigma3() * newDelta))));
               State var10000;
               if (actualReduction > this.eta0() * predictedReduction) {
                  this.logger().info(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Accept %d d=%.2E newv=%.4E newG=%.4E resNorm=%.2E pred=%.2E actual=%.2E"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(state.iter()), BoxesRunTime.boxToDouble(state.delta()), BoxesRunTime.boxToDouble(adjNewV), norm$.MODULE$.apply(adjNewG, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()), norm$.MODULE$.apply(residualx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()), BoxesRunTime.boxToDouble(predictedReduction), BoxesRunTime.boxToDouble(actualReduction)})));
                  boolean stop_cond = adjNewV < -1.0E32 || scala.math.package..MODULE$.abs(actualReduction) <= scala.math.package..MODULE$.abs(adjNewV) * 1.0E-12 && scala.math.package..MODULE$.abs(predictedReduction) <= scala.math.package..MODULE$.abs(adjNewV) * 1.0E-12;
                  History newHistory = this.updateHistory(x_new, adjNewG, adjNewV, state);
                  int this_iter = state.accept() ? state.iter() + 1 : state.iter();
                  var10000 = this.new State(this_iter, state.initialGNorm(), newDelta, x_new, newvx, newgx, newhx, adjNewV, adjNewG, stop_cond, true, newHistory);
               } else {
                  int this_iter = state.accept() ? state.iter() + 1 : state.iter();
                  boolean stop_cond = state.adjFval() < -1.0E32 || scala.math.package..MODULE$.abs(actualReduction) <= scala.math.package..MODULE$.abs(state.adjFval()) * 1.0E-12 && scala.math.package..MODULE$.abs(predictedReduction) <= scala.math.package..MODULE$.abs(state.adjFval()) * 1.0E-12;
                  this.logger().info(() -> scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("Reject %d d=%.2f resNorm=%.2f pred=%.2f actual=%.2f"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(state.iter()), BoxesRunTime.boxToDouble(state.delta()), norm$.MODULE$.apply(residualx, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()), BoxesRunTime.boxToDouble(predictedReduction), BoxesRunTime.boxToDouble(actualReduction)})));
                  boolean x$8 = false;
                  double x$9 = state.copy$default$2();
                  Object x$10 = state.copy$default$4();
                  double x$11 = state.copy$default$5();
                  Object x$12 = state.copy$default$6();
                  Object x$13 = state.copy$default$7();
                  double x$14 = state.copy$default$8();
                  Object x$15 = state.copy$default$9();
                  History x$16 = state.copy$default$12();
                  var10000 = state.copy(this_iter, x$9, newDelta, x$10, x$11, x$12, x$13, x$14, x$15, stop_cond, false, x$16);
               }

               return var10000;
            } else {
               throw new MatchError(var26);
            }
         }
      });
   }

   public History initialHistory(final DiffFunction f, final Object x) {
      return new History(this.History().$lessinit$greater$default$1(), this.History().$lessinit$greater$default$2());
   }

   public Object chooseDescentDirection(final State state) {
      Object grad = state.adjGrad();
      IndexedSeq memStep = state.history().memStep();
      IndexedSeq memGradDelta = state.history().memGradDelta();
      double diag = memStep.size() > 0 ? this.computeDiagScale(memStep.head(), memGradDelta.head()) : (double)1.0F / BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(grad, this.breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl()));
      Object dir = this.breeze$optimize$TruncatedNewtonMinimizer$$space.copy().apply(grad);
      double[] as = new double[this.m];
      double[] rho = new double[this.m];
      scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(memStep.length() - 1), 0).by(-1).foreach((i) -> $anonfun$chooseDescentDirection$1(this, rho, memStep, memGradDelta, as, dir, BoxesRunTime.unboxToInt(i)));
      ((NumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(dir)).$times$eq(BoxesRunTime.boxToDouble(diag), this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulIntoVS());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), memStep.length()).foreach((i) -> $anonfun$chooseDescentDirection$2(this, memGradDelta, dir, rho, memStep, as, BoxesRunTime.unboxToInt(i)));
      ((NumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(dir)).$times$eq(BoxesRunTime.boxToDouble((double)-1.0F), this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulIntoVS());
      return dir;
   }

   private double computeDiagScale(final Object prevStep, final Object prevGradStep) {
      double sy = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(prevStep)).dot(prevGradStep, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
      double yy = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(prevGradStep)).dot(prevGradStep, this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
      if (!(sy < (double)0) && !Double.isNaN(sy)) {
         return sy / yy;
      } else {
         throw new NaNHistory();
      }
   }

   public History updateHistory(final Object newX, final Object newGrad, final double newVal, final State oldState) {
      Object gradDelta = ((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(newGrad)).$minus$colon$minus(oldState.adjGrad(), this.breeze$optimize$TruncatedNewtonMinimizer$$space.subVV());
      Object step = ((ImmutableNumericOps)this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(newX)).$minus(oldState.x(), this.breeze$optimize$TruncatedNewtonMinimizer$$space.subVV());
      IndexedSeq memStep = (IndexedSeq)((IndexedSeqOps)oldState.history().memStep().$plus$colon(step)).take(this.m);
      IndexedSeq memGradDelta = (IndexedSeq)((IndexedSeqOps)oldState.history().memGradDelta().$plus$colon(gradDelta)).take(this.m);
      return new History(memStep, memGradDelta);
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void History$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.History$module == null) {
            this.History$module = new History$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$minimize$1(final State x$1) {
      return x$1.converged();
   }

   // $FF: synthetic method
   public static final Object $anonfun$chooseDescentDirection$1(final TruncatedNewtonMinimizer $this, final double[] rho$1, final IndexedSeq memStep$1, final IndexedSeq memGradDelta$1, final double[] as$1, final Object dir$1, final int i) {
      rho$1[i] = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(memStep$1.apply(i))).dot(memGradDelta$1.apply(i), $this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV()));
      as$1[i] = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(memStep$1.apply(i))).dot(dir$1, $this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV())) / rho$1[i];
      if (Double.isNaN(as$1[i])) {
         throw new NaNHistory();
      } else {
         return ((NumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(dir$1)).$minus$eq(((ImmutableNumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(memGradDelta$1.apply(i))).$times(BoxesRunTime.boxToDouble(as$1[i]), $this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulVS_M()), $this.breeze$optimize$TruncatedNewtonMinimizer$$space.subIntoVV());
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$chooseDescentDirection$2(final TruncatedNewtonMinimizer $this, final IndexedSeq memGradDelta$1, final Object dir$1, final double[] rho$1, final IndexedSeq memStep$1, final double[] as$1, final int i) {
      double beta = BoxesRunTime.unboxToDouble(((ImmutableNumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(memGradDelta$1.apply(i))).dot(dir$1, $this.breeze$optimize$TruncatedNewtonMinimizer$$space.dotVV())) / rho$1[i];
      return ((NumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(dir$1)).$plus$eq(((ImmutableNumericOps)$this.breeze$optimize$TruncatedNewtonMinimizer$$space.hasOps().apply(memStep$1.apply(i))).$times(BoxesRunTime.boxToDouble(as$1[i] - beta), $this.breeze$optimize$TruncatedNewtonMinimizer$$space.mulVS_M()), $this.breeze$optimize$TruncatedNewtonMinimizer$$space.addIntoVV());
   }

   public TruncatedNewtonMinimizer(final int maxIterations, final double tolerance, final double l2Regularization, final int m, final MutableVectorField space, final UFunc.UImpl2 mult) {
      this.breeze$optimize$TruncatedNewtonMinimizer$$maxIterations = maxIterations;
      this.breeze$optimize$TruncatedNewtonMinimizer$$tolerance = tolerance;
      this.l2Regularization = l2Regularization;
      this.m = m;
      this.breeze$optimize$TruncatedNewtonMinimizer$$space = space;
      this.mult = mult;
      SerializableLogging.$init$(this);
      this.eta0 = 1.0E-4;
      this.eta1 = (double)0.25F;
      this.eta2 = (double)0.75F;
      this.sigma1 = (double)0.25F;
      this.sigma2 = (double)0.5F;
      this.sigma3 = (double)4.0F;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class State implements Product, Serializable {
      private final int iter;
      private final double initialGNorm;
      private final double delta;
      private final Object x;
      private final double fval;
      private final Object grad;
      private final Object h;
      private final double adjFval;
      private final Object adjGrad;
      private final boolean stop;
      private final boolean accept;
      private final History history;
      // $FF: synthetic field
      public final TruncatedNewtonMinimizer $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public int iter() {
         return this.iter;
      }

      public double initialGNorm() {
         return this.initialGNorm;
      }

      public double delta() {
         return this.delta;
      }

      public Object x() {
         return this.x;
      }

      public double fval() {
         return this.fval;
      }

      public Object grad() {
         return this.grad;
      }

      public Object h() {
         return this.h;
      }

      public double adjFval() {
         return this.adjFval;
      }

      public Object adjGrad() {
         return this.adjGrad;
      }

      public boolean stop() {
         return this.stop;
      }

      public boolean accept() {
         return this.accept;
      }

      public History history() {
         return this.history;
      }

      public boolean converged() {
         return this.iter() >= this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer().breeze$optimize$TruncatedNewtonMinimizer$$maxIterations && this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer().breeze$optimize$TruncatedNewtonMinimizer$$maxIterations > 0 && this.accept() || BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(this.adjGrad(), this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer().breeze$optimize$TruncatedNewtonMinimizer$$space.normImpl())) <= this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer().breeze$optimize$TruncatedNewtonMinimizer$$tolerance * this.initialGNorm() || this.stop();
      }

      public State copy(final int iter, final double initialGNorm, final double delta, final Object x, final double fval, final Object grad, final Object h, final double adjFval, final Object adjGrad, final boolean stop, final boolean accept, final History history) {
         return this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer().new State(iter, initialGNorm, delta, x, fval, grad, h, adjFval, adjGrad, stop, accept, history);
      }

      public int copy$default$1() {
         return this.iter();
      }

      public boolean copy$default$10() {
         return this.stop();
      }

      public boolean copy$default$11() {
         return this.accept();
      }

      public History copy$default$12() {
         return this.history();
      }

      public double copy$default$2() {
         return this.initialGNorm();
      }

      public double copy$default$3() {
         return this.delta();
      }

      public Object copy$default$4() {
         return this.x();
      }

      public double copy$default$5() {
         return this.fval();
      }

      public Object copy$default$6() {
         return this.grad();
      }

      public Object copy$default$7() {
         return this.h();
      }

      public double copy$default$8() {
         return this.adjFval();
      }

      public Object copy$default$9() {
         return this.adjGrad();
      }

      public String productPrefix() {
         return "State";
      }

      public int productArity() {
         return 12;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = BoxesRunTime.boxToInteger(this.iter());
               break;
            case 1:
               var10000 = BoxesRunTime.boxToDouble(this.initialGNorm());
               break;
            case 2:
               var10000 = BoxesRunTime.boxToDouble(this.delta());
               break;
            case 3:
               var10000 = this.x();
               break;
            case 4:
               var10000 = BoxesRunTime.boxToDouble(this.fval());
               break;
            case 5:
               var10000 = this.grad();
               break;
            case 6:
               var10000 = this.h();
               break;
            case 7:
               var10000 = BoxesRunTime.boxToDouble(this.adjFval());
               break;
            case 8:
               var10000 = this.adjGrad();
               break;
            case 9:
               var10000 = BoxesRunTime.boxToBoolean(this.stop());
               break;
            case 10:
               var10000 = BoxesRunTime.boxToBoolean(this.accept());
               break;
            case 11:
               var10000 = this.history();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof State;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "iter";
               break;
            case 1:
               var10000 = "initialGNorm";
               break;
            case 2:
               var10000 = "delta";
               break;
            case 3:
               var10000 = "x";
               break;
            case 4:
               var10000 = "fval";
               break;
            case 5:
               var10000 = "grad";
               break;
            case 6:
               var10000 = "h";
               break;
            case 7:
               var10000 = "adjFval";
               break;
            case 8:
               var10000 = "adjGrad";
               break;
            case 9:
               var10000 = "stop";
               break;
            case 10:
               var10000 = "accept";
               break;
            case 11:
               var10000 = "history";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, this.iter());
         var1 = Statics.mix(var1, Statics.doubleHash(this.initialGNorm()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.delta()));
         var1 = Statics.mix(var1, Statics.anyHash(this.x()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.fval()));
         var1 = Statics.mix(var1, Statics.anyHash(this.grad()));
         var1 = Statics.mix(var1, Statics.anyHash(this.h()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.adjFval()));
         var1 = Statics.mix(var1, Statics.anyHash(this.adjGrad()));
         var1 = Statics.mix(var1, this.stop() ? 1231 : 1237);
         var1 = Statics.mix(var1, this.accept() ? 1231 : 1237);
         var1 = Statics.mix(var1, Statics.anyHash(this.history()));
         return Statics.finalizeHash(var1, 12);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label80: {
               boolean var2;
               if (x$1 instanceof State && ((State)x$1).breeze$optimize$TruncatedNewtonMinimizer$State$$$outer() == this.breeze$optimize$TruncatedNewtonMinimizer$State$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label57: {
                     State var4 = (State)x$1;
                     if (this.iter() == var4.iter() && this.initialGNorm() == var4.initialGNorm() && this.delta() == var4.delta() && this.fval() == var4.fval() && this.adjFval() == var4.adjFval() && this.stop() == var4.stop() && this.accept() == var4.accept() && BoxesRunTime.equals(this.x(), var4.x()) && BoxesRunTime.equals(this.grad(), var4.grad()) && BoxesRunTime.equals(this.h(), var4.h()) && BoxesRunTime.equals(this.adjGrad(), var4.adjGrad())) {
                        label45: {
                           History var10000 = this.history();
                           History var5 = var4.history();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label45;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label45;
                           }

                           if (var4.canEqual(this)) {
                              var7 = true;
                              break label57;
                           }
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label80;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      // $FF: synthetic method
      public TruncatedNewtonMinimizer breeze$optimize$TruncatedNewtonMinimizer$State$$$outer() {
         return this.$outer;
      }

      public State(final int iter, final double initialGNorm, final double delta, final Object x, final double fval, final Object grad, final Object h, final double adjFval, final Object adjGrad, final boolean stop, final boolean accept, final History history) {
         this.iter = iter;
         this.initialGNorm = initialGNorm;
         this.delta = delta;
         this.x = x;
         this.fval = fval;
         this.grad = grad;
         this.h = h;
         this.adjFval = adjFval;
         this.adjGrad = adjGrad;
         this.stop = stop;
         this.accept = accept;
         this.history = history;
         if (TruncatedNewtonMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = TruncatedNewtonMinimizer.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class State$ extends AbstractFunction12 implements Serializable {
      // $FF: synthetic field
      private final TruncatedNewtonMinimizer $outer;

      public final String toString() {
         return "State";
      }

      public State apply(final int iter, final double initialGNorm, final double delta, final Object x, final double fval, final Object grad, final Object h, final double adjFval, final Object adjGrad, final boolean stop, final boolean accept, final History history) {
         return this.$outer.new State(iter, initialGNorm, delta, x, fval, grad, h, adjFval, adjGrad, stop, accept, history);
      }

      public Option unapply(final State x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple12(BoxesRunTime.boxToInteger(x$0.iter()), BoxesRunTime.boxToDouble(x$0.initialGNorm()), BoxesRunTime.boxToDouble(x$0.delta()), x$0.x(), BoxesRunTime.boxToDouble(x$0.fval()), x$0.grad(), x$0.h(), BoxesRunTime.boxToDouble(x$0.adjFval()), x$0.adjGrad(), BoxesRunTime.boxToBoolean(x$0.stop()), BoxesRunTime.boxToBoolean(x$0.accept()), x$0.history())));
      }

      public State$() {
         if (TruncatedNewtonMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = TruncatedNewtonMinimizer.this;
            super();
         }
      }
   }

   public class History implements Product, Serializable {
      private final IndexedSeq memStep;
      private final IndexedSeq memGradDelta;
      // $FF: synthetic field
      public final TruncatedNewtonMinimizer $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public IndexedSeq memStep() {
         return this.memStep;
      }

      public IndexedSeq memGradDelta() {
         return this.memGradDelta;
      }

      public History copy(final IndexedSeq memStep, final IndexedSeq memGradDelta) {
         return this.breeze$optimize$TruncatedNewtonMinimizer$History$$$outer().new History(memStep, memGradDelta);
      }

      public IndexedSeq copy$default$1() {
         return this.memStep();
      }

      public IndexedSeq copy$default$2() {
         return this.memGradDelta();
      }

      public String productPrefix() {
         return "History";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.memStep();
               break;
            case 1:
               var10000 = this.memGradDelta();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof History;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "memStep";
               break;
            case 1:
               var10000 = "memGradDelta";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var9;
         if (this != x$1) {
            label68: {
               boolean var2;
               if (x$1 instanceof History && ((History)x$1).breeze$optimize$TruncatedNewtonMinimizer$History$$$outer() == this.breeze$optimize$TruncatedNewtonMinimizer$History$$$outer()) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label44: {
                     label58: {
                        History var4 = (History)x$1;
                        IndexedSeq var10000 = this.memStep();
                        IndexedSeq var5 = var4.memStep();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label58;
                        }

                        var10000 = this.memGradDelta();
                        IndexedSeq var6 = var4.memGradDelta();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label58;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label44;
                        }
                     }

                     var9 = false;
                  }

                  if (var9) {
                     break label68;
                  }
               }

               var9 = false;
               return var9;
            }
         }

         var9 = true;
         return var9;
      }

      // $FF: synthetic method
      public TruncatedNewtonMinimizer breeze$optimize$TruncatedNewtonMinimizer$History$$$outer() {
         return this.$outer;
      }

      public History(final IndexedSeq memStep, final IndexedSeq memGradDelta) {
         this.memStep = memStep;
         this.memGradDelta = memGradDelta;
         if (TruncatedNewtonMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = TruncatedNewtonMinimizer.this;
            super();
            Product.$init$(this);
         }
      }
   }

   public class History$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final TruncatedNewtonMinimizer $outer;

      public IndexedSeq $lessinit$greater$default$1() {
         return (IndexedSeq).MODULE$.IndexedSeq().empty();
      }

      public IndexedSeq $lessinit$greater$default$2() {
         return (IndexedSeq).MODULE$.IndexedSeq().empty();
      }

      public final String toString() {
         return "History";
      }

      public History apply(final IndexedSeq memStep, final IndexedSeq memGradDelta) {
         return this.$outer.new History(memStep, memGradDelta);
      }

      public IndexedSeq apply$default$1() {
         return (IndexedSeq).MODULE$.IndexedSeq().empty();
      }

      public IndexedSeq apply$default$2() {
         return (IndexedSeq).MODULE$.IndexedSeq().empty();
      }

      public Option unapply(final History x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.memStep(), x$0.memGradDelta())));
      }

      public History$() {
         if (TruncatedNewtonMinimizer.this == null) {
            throw null;
         } else {
            this.$outer = TruncatedNewtonMinimizer.this;
            super();
         }
      }
   }
}
