package spire.math;

import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import spire.algebra.CModule;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.std.ArraySupport$;
import spire.syntax.LiteralIntMultiplicativeSemigroupOps$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0015u!\u00020`\u0011\u0003!g!\u00024`\u0011\u00039\u0007\"B=\u0002\t\u0003Q\b\"B>\u0002\t\u0003a\bBB>\u0002\t\u0003)\t\u0003\u0003\u0004|\u0003\u0011\u0005Q1\t\u0005\b\u000bW\nA\u0011AC7\u0011\u001d)y)\u0001C\u0001\u000b#Cq!b,\u0002\t\u0003)\t\fC\u0004\u0006P\u0006!\t!\"5\t\u000f\u0015]\u0018\u0001b\u0001\u0006z\"9a1A\u0001\u0005\u0004\u0019\u0015\u0001b\u0002D\u0007\u0003\u0011\raq\u0002\u0005\b\r3\tA1\u0001D\u000e\u0011\u001d1\u0019#\u0001C\u0002\rKAqAb\u000f\u0002\t\u00071i\u0004\u0003\u0005|\u0003\u0005\u0005I\u0011\u0011D#\u0011%1y&AA\u0001\n\u00033\t\u0007C\u0005\u0007\u0004\u0006\t\t\u0011\"\u0003\u0005\"\u0019!am\u0018\"\u0000\u0011)\tyc\u0005BK\u0002\u0013\u0005\u0011\u0011\u0007\u0005\u000b\u0003K\u001a\"\u0011#Q\u0001\n\u0005M\u0002BCA4'\tU\r\u0011\"\u0001\u0002j!Q\u0011\u0011O\n\u0003\u0012\u0003\u0006I!a\u001b\t\re\u001cB\u0011AA:\u0011\u001d\tYh\u0005C\u0001\u0003{Bq!!\"\u0014\t\u0007\t9\tC\u0004\u0002\u0010N!\t!!%\t\u000f\u0005=6\u0003\"\u0001\u00022\"9\u0011\u0011X\n\u0005\u0002\u0005m\u0006bBAb'\u0011\u0005\u00111\u0018\u0005\b\u0003\u000b\u001cB\u0011AA^\u0011\u001d\t9m\u0005C\u0001\u0003\u0013Dq!a7\u0014\t\u0003\ti\u000eC\u0004\u0002fN!\t!a:\t\u000f\u0005}8\u0003\"\u0001\u0003\u0002!9!\u0011B\n\u0005\u0002\t-\u0001b\u0002B\n'\u0011\u0005!Q\u0003\u0005\b\u0005?\u0019B\u0011\u0001B\u0011\u0011\u001d\typ\u0005C\u0001\u0005WAqA!\u0003\u0014\t\u0003\u0011)\u0004C\u0004\u0003\u0014M!\tAa\u0010\t\u000f\t}1\u0003\"\u0001\u0003J!9!1K\n\u0005\u0002\tU\u0003b\u0002B<'\u0011\u0005!\u0011\u0010\u0005\b\u0005\u000f\u001bB\u0011\u0001BE\u0011\u001d\u0011Ij\u0005C\u0001\u00057CqA!*\u0014\t\u0003\u00119\u000bC\u0004\u0003\u001aN!\tA!3\t\u000f\tm7\u0003\"\u0001\u0003^\"9!1]\n\u0005\u0002\t\u0015\bb\u0002Bv'\u0011\u0005!Q\u001e\u0005\b\u0005g\u001cB\u0011\u0001B{\u0011\u001d\u0011yp\u0005C\u0005\u0007\u0003Aqaa\u0005\u0014\t\u0003\u0019)\u0002C\u0004\u00042M!\taa\r\t\u000f\rE2\u0003\"\u0001\u0004F!91\u0011G\n\u0005\u0002\r=\u0003bBB1'\u0011\u000511\r\u0005\b\u0007W\u001aB\u0011AB7\u0011\u001d\u0019ih\u0005C\u0001\u0007\u007fBqa!#\u0014\t\u0003\u0019Y\tC\u0004\u0004\u0016N!\taa&\t\u000f\r}5\u0003\"\u0001\u0004\"\"91QV\n\u0005\u0002\r=\u0006bBB['\u0011\u00051q\u0017\u0005\b\u0007{\u001bB\u0011AB`\u0011\u001d\u0019)m\u0005C\u0001\u0007\u000fDqaa4\u0014\t\u0003\u0019\t\u000eC\u0004\u0004XN!\ta!7\t\u000f\r\u00058\u0003\"\u0001\u0004d\"911^\n\u0005B\r5\bbBB{'\u0011\u00053q\u001f\u0005\b\u0007\u007f\u001cB\u0011\tC\u0001\u0011\u001d!Ia\u0005C!\t\u0017Aq\u0001b\u0005\u0014\t\u0003\ni\bC\u0004\u0005\u0016M!\t\u0005b\u0006\t\u000f\u0011}1\u0003\"\u0011\u0005\"!9AqF\n\u0005B\u0005m\u0006b\u0002C\u0019'\u0011\u0005\u00131\u0018\u0005\b\tg\u0019B\u0011\tC\u001b\u0011\u001d!9d\u0005C!\tsAq\u0001b\u0010\u0014\t\u0003!\t\u0005C\u0004\u0005PM!\t\u0001\"\u0015\t\u000f\u0011}3\u0003\"\u0011\u0005b!IA1O\n\u0002\u0002\u0013\u0005AQ\u000f\u0005\n\t\u001f\u001b\u0012\u0013!C\u0001\t#C\u0011\u0002\".\u0014#\u0003%\t\u0001b.\t\u0013\u0011%7#!A\u0005B\u0011-\u0007\"\u0003Ci'\u0005\u0005I\u0011AA?\u0011%!\u0019nEA\u0001\n\u0003!)\u000eC\u0005\u0005\\N\t\t\u0011\"\u0011\u0005^\"IA1^\n\u0002\u0002\u0013\u0005AQ\u001e\u0005\n\tc\u001c\u0012\u0011!C!\tg\f1AS3u\u0015\t\u0001\u0017-\u0001\u0003nCRD'\"\u00012\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011Q-A\u0007\u0002?\n\u0019!*\u001a;\u0014\t\u0005Ag.\u001d\t\u0003S2l\u0011A\u001b\u0006\u0002W\u0006)1oY1mC&\u0011QN\u001b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0015|\u0017B\u00019`\u00051QU\r^%ogR\fgnY3t!\t\u0011x/D\u0001t\u0015\t!X/\u0001\u0002j_*\ta/\u0001\u0003kCZ\f\u0017B\u0001=t\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\tA-A\u0003baBd\u00170F\u0002~\u000b\u000b!rA`C\t\u000b+)I\u0002\u0005\u0003f'\u0015\rQ\u0003BA\u0001\u0003o\u0019\u0012bEA\u0002\u0003\u001b\t\u0019\"!\u000b\u0011\t\u0005\u0015\u0011\u0011B\u0007\u0003\u0003\u000fQ!\u0001\u00196\n\t\u0005-\u0011q\u0001\u0002\f'\u000e\fG.\u0019(v[\n,'\u000f\u0005\u0003\u0002\u0006\u0005=\u0011\u0002BA\t\u0003\u000f\u0011qcU2bY\u0006tU/\\3sS\u000e\u001cuN\u001c<feNLwN\\:\u0011\t\u0005U\u0011Q\u0005\b\u0005\u0003/\t\tC\u0004\u0003\u0002\u001a\u0005}QBAA\u000e\u0015\r\tibY\u0001\u0007yI|w\u000e\u001e \n\u0003-L1!a\tk\u0003\u001d\u0001\u0018mY6bO\u0016L1\u0001_A\u0014\u0015\r\t\u0019C\u001b\t\u0004S\u0006-\u0012bAA\u0017U\n9\u0001K]8ek\u000e$\u0018\u0001\u0002:fC2,\"!a\r\u0011\t\u0005U\u0012q\u0007\u0007\u0001\t-\tId\u0005Q\u0001\u0002\u0003\u0015\r!a\u000f\u0003\u0003Q\u000bB!!\u0010\u0002DA\u0019\u0011.a\u0010\n\u0007\u0005\u0005#NA\u0004O_RD\u0017N\\4\u0011\u0007%\f)%C\u0002\u0002H)\u00141!\u00118zQ!\t9$a\u0013\u0002R\u0005m\u0003cA5\u0002N%\u0019\u0011q\n6\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0005M\u0013QKA-\u0003/r1![A+\u0013\r\t9F[\u0001\u0006\r2|\u0017\r^\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0002d\u0005\u0005dbA5\u0002`%\u0019\u0011\u0011\r6\u0002\r\u0011{WO\u00197fc\u0019!\u0013qCA\u0010W\u0006)!/Z1mA\u0005i\u0011N\u001c4j]&$Xm]5nC2,\"!a\u001b\u0011\u000b%\fi'a\r\n\u0007\u0005=$NA\u0003BeJ\f\u00170\u0001\bj]\u001aLg.\u001b;fg&l\u0017\r\u001c\u0011\u0015\r\u0005U\u0014qOA=!\u0011)7#a\r\t\u000f\u0005=\u0002\u00041\u0001\u00024!9\u0011q\r\rA\u0002\u0005-\u0014!\u00033j[\u0016t7/[8o+\t\ty\bE\u0002j\u0003\u0003K1!a!k\u0005\rIe\u000e^\u0001\rU\u0016$H)[7f]NLwN\\\u000b\u0003\u0003\u0013\u00032!ZAF\u0013\r\tii\u0018\u0002\u0007\u0015\u0016$H)[7\u0002\rMLwM\\;n)\u0011\ty(a%\t\u000f\u0005U5\u0004q\u0001\u0002\u0018\u0006\t!\u000f\u0005\u0004\u0002\u001a\u0006%\u00161\u0007\b\u0005\u00037\u000b)K\u0004\u0003\u0002\u001e\u0006\u0005f\u0002BA\r\u0003?K\u0011AY\u0005\u0004\u0003G\u000b\u0017aB1mO\u0016\u0014'/Y\u0005\u0005\u0003G\t9KC\u0002\u0002$\u0006LA!a+\u0002.\n11+[4oK\u0012TA!a\t\u0002(\u00069\u0011m\u001d+va2,WCAAZ!\u001dI\u0017QWA\u001a\u0003WJ1!a.k\u0005\u0019!V\u000f\u001d7fe\u00051\u0011n\u001d*fC2,\"!!0\u0011\u0007%\fy,C\u0002\u0002B*\u0014qAQ8pY\u0016\fg.\u0001\u0004jgj+'o\\\u0001\u0010SNLeNZ5oSR,7/[7bY\u0006\u0019Q-\u001d<\u0015\t\u0005-\u0017q\u001b\u000b\u0005\u0003{\u000bi\rC\u0004\u0002P\u0002\u0002\u001d!!5\u0002\u0003=\u0004b!!'\u0002T\u0006M\u0012\u0002BAk\u0003[\u0013!!R9\t\u000f\u0005e\u0007\u00051\u0001\u0002v\u0005\t!-\u0001\u0003oKF4H\u0003BAp\u0003G$B!!0\u0002b\"9\u0011qZ\u0011A\u0004\u0005E\u0007bBAmC\u0001\u0007\u0011QO\u0001\rk:\f'/_0%[&tWo\u001d\u000b\u0007\u0003k\nI/a=\t\u000f\u0005-(\u0005q\u0001\u0002n\u0006\ta\r\u0005\u0004\u0002\u001a\u0006=\u00181G\u0005\u0005\u0003c\fiKA\u0003GS\u0016dG\rC\u0004\u0002v\n\u0002\u001d!a>\u0002\u0003Y\u0004\u0002\"!?\u0002|\u0006-\u00141G\u0007\u0003\u0003OKA!!@\u0002(\nYa+Z2u_J\u001c\u0006/Y2f\u0003\u0015!\u0003\u000f\\;t)\u0011\u0011\u0019Aa\u0002\u0015\t\u0005U$Q\u0001\u0005\b\u0003W\u001c\u00039AAw\u0011\u001d\tIn\ta\u0001\u0003g\ta\u0001J7j]V\u001cH\u0003\u0002B\u0007\u0005#!B!!\u001e\u0003\u0010!9\u00111\u001e\u0013A\u0004\u00055\bbBAmI\u0001\u0007\u00111G\u0001\u0007IQLW.Z:\u0015\t\t]!Q\u0004\u000b\u0007\u0003k\u0012IBa\u0007\t\u000f\u0005-X\u0005q\u0001\u0002n\"9\u0011Q_\u0013A\u0004\u0005]\bbBAmK\u0001\u0007\u00111G\u0001\u0005I\u0011Lg\u000f\u0006\u0003\u0003$\t%BCBA;\u0005K\u00119\u0003C\u0004\u0002l\u001a\u0002\u001d!!<\t\u000f\u0005Uh\u0005q\u0001\u0002x\"9\u0011\u0011\u001c\u0014A\u0002\u0005MB\u0003\u0002B\u0017\u0005g!b!!\u001e\u00030\tE\u0002bBAvO\u0001\u000f\u0011Q\u001e\u0005\b\u0003k<\u00039AA|\u0011\u001d\tIn\na\u0001\u0003k\"BAa\u000e\u0003>Q1\u0011Q\u000fB\u001d\u0005wAq!a;)\u0001\b\ti\u000fC\u0004\u0002v\"\u0002\u001d!a>\t\u000f\u0005e\u0007\u00061\u0001\u0002vQ!!\u0011\tB$)\u0019\t)Ha\u0011\u0003F!9\u00111^\u0015A\u0004\u00055\bbBA{S\u0001\u000f\u0011q\u001f\u0005\b\u00033L\u0003\u0019AA;)\u0011\u0011YE!\u0015\u0015\r\u0005U$Q\nB(\u0011\u001d\tYO\u000ba\u0002\u0003[Dq!!>+\u0001\b\t9\u0010C\u0004\u0002Z*\u0002\r!!\u001e\u0002\u0015\u0011\"\u0017N\u001e\u0013uS2$W\r\u0006\u0003\u0003X\tUDCCA;\u00053\u0012IGa\u001b\u0003t!9!1L\u0016A\u0004\tu\u0013!A2\u0011\r\t}#QMA\u001a\u001b\t\u0011\tGC\u0002\u0003d)\fqA]3gY\u0016\u001cG/\u0003\u0003\u0003h\t\u0005$\u0001C\"mCN\u001cH+Y4\t\u000f\u0005-8\u0006q\u0001\u0002n\"9\u0011QS\u0016A\u0004\t5\u0004CBA}\u0005_\n\u0019$\u0003\u0003\u0003r\u0005\u001d&AB%t%\u0016\fG\u000eC\u0004\u0002v.\u0002\u001d!a>\t\u000f\u0005e7\u00061\u0001\u0002v\u0005AA\u0005]3sG\u0016tG\u000f\u0006\u0003\u0003|\t\u0015ECCA;\u0005{\u0012yH!!\u0003\u0004\"9!1\f\u0017A\u0004\tu\u0003bBAvY\u0001\u000f\u0011Q\u001e\u0005\b\u0003+c\u00039\u0001B7\u0011\u001d\t)\u0010\fa\u0002\u0003oDq!!7-\u0001\u0004\t)(\u0001\u0007%I&4H\u0005]3sG\u0016tG\u000f\u0006\u0003\u0003\f\n]EC\u0003BG\u0005\u001f\u0013\tJa%\u0003\u0016B9\u0011.!.\u0002v\u0005U\u0004b\u0002B.[\u0001\u000f!Q\f\u0005\b\u0003Wl\u00039AAw\u0011\u001d\t)*\fa\u0002\u0005[Bq!!>.\u0001\b\t9\u0010C\u0004\u0002Z6\u0002\r!!\u001e\u0002\u0019\u0011\"\u0018.\\3tIQLW.Z:\u0015\t\tu%1\u0015\u000b\u0007\u0003k\u0012yJ!)\t\u000f\u0005-h\u0006q\u0001\u0002n\"9\u0011Q\u001f\u0018A\u0004\u0005]\bbBAm]\u0001\u0007\u0011qP\u0001\u0006]J|w\u000e\u001e\u000b\u0005\u0005S\u0013)\r\u0006\u0007\u0002v\t-&Q\u0016B[\u0005s\u0013\u0019\rC\u0004\u0002l>\u0002\u001d!!<\t\u000f\u0005=w\u0006q\u0001\u00030B1\u0011\u0011\u0014BY\u0003gIAAa-\u0002.\n)qJ\u001d3fe\"9!qW\u0018A\u0004\u0005]\u0015!A:\t\u000f\tmv\u0006q\u0001\u0003>\u0006\tA\u000f\u0005\u0004\u0002z\n}\u00161G\u0005\u0005\u0005\u0003\f9K\u0001\u0003Ue&<\u0007bBA{_\u0001\u000f\u0011q\u001f\u0005\b\u0005\u000f|\u0003\u0019AA@\u0003\u0005YG\u0003\u0002Bf\u00053$b\"!\u001e\u0003N\n='\u0011\u001bBj\u0005+\u00149\u000eC\u0004\u0003\\A\u0002\u001dA!\u0018\t\u000f\u0005-\b\u0007q\u0001\u0002n\"9\u0011q\u001a\u0019A\u0004\t=\u0006b\u0002B\\a\u0001\u000f\u0011q\u0013\u0005\b\u0005w\u0003\u00049\u0001B_\u0011\u001d\t)\u0010\ra\u0002\u0003oDq!!71\u0001\u0004\t)(A\u0003gY>|'\u000f\u0006\u0004\u0002v\t}'\u0011\u001d\u0005\b\u00057\n\u00049\u0001B/\u0011\u001d\t)*\ra\u0002\u0005[\nAaY3jYR1\u0011Q\u000fBt\u0005SDqAa\u00173\u0001\b\u0011i\u0006C\u0004\u0002\u0016J\u0002\u001dA!\u001c\u0002\u000bI|WO\u001c3\u0015\r\u0005U$q\u001eBy\u0011\u001d\u0011Yf\ra\u0002\u0005;Bq!!&4\u0001\b\u0011i'A\u0002bEN$\"\"!\u001e\u0003x\ne(1 B\u007f\u0011\u001d\tY\u000f\u000ea\u0002\u0003[Dq!a45\u0001\b\u0011y\u000bC\u0004\u00038R\u0002\u001d!a&\t\u000f\u0005UH\u0007q\u0001\u0002x\u0006\t\u0002o\\<TG\u0006d\u0017M\u001d+p'\u000e\fG.\u0019:\u0015\r\r\r1QBB\b))\t\u0019d!\u0002\u0004\b\r%11\u0002\u0005\b\u0003W,\u00049AAw\u0011\u001d\ty-\u000ea\u0002\u0005_CqAa.6\u0001\b\t9\nC\u0004\u0003<V\u0002\u001dA!0\t\u000f\u0005eW\u00071\u0001\u00024!91\u0011C\u001bA\u0002\u0005M\u0012!A3\u0002\u001dA|woU2bY\u0006\u0014Hk\u001c&fiR!1qCB\u0017)9\t)h!\u0007\u0004\u001c\ru1qEB\u0015\u0007WAqAa\u00177\u0001\b\u0011i\u0006C\u0004\u0002lZ\u0002\u001d!!<\t\u000f\r}a\u0007q\u0001\u0004\"\u0005\tQ\u000e\u0005\u0005\u0002z\u000e\r\u00121NA\u001a\u0013\u0011\u0019)#a*\u0003\u000f\rku\u000eZ;mK\"9\u0011q\u001a\u001cA\u0004\t=\u0006b\u0002B\\m\u0001\u000f\u0011q\u0013\u0005\b\u0005w3\u00049\u0001B_\u0011\u001d\u0019yC\u000ea\u0001\u0003g\t\u0011!Y\u0001\u0004a><H\u0003BB\u001b\u0007\u0003\"B\"!\u001e\u00048\re21HB\u001f\u0007\u007fAq!a;8\u0001\b\ti\u000fC\u0004\u0002P^\u0002\u001dAa,\t\u000f\t]v\u0007q\u0001\u0002\u0018\"9!1X\u001cA\u0004\tu\u0006bBA{o\u0001\u000f\u0011q\u001f\u0005\b\u0007\u0007:\u0004\u0019AA\u001a\u0003\u0005\u0001H\u0003BB$\u0007\u001b\"b!!\u001e\u0004J\r-\u0003bBAvq\u0001\u000f\u0011Q\u001e\u0005\b\u0003kD\u00049AA|\u0011\u001d\u0019\u0019\u0005\u000fa\u0001\u0003\u007f\"Ba!\u0015\u0004`Qq\u0011QOB*\u0007+\u001a9f!\u0017\u0004\\\ru\u0003b\u0002B.s\u0001\u000f!Q\f\u0005\b\u0003WL\u00049AAw\u0011\u001d\u0019y\"\u000fa\u0002\u0007CAq!a4:\u0001\b\u0011y\u000bC\u0004\u00038f\u0002\u001d!a&\t\u000f\tm\u0016\bq\u0001\u0003>\"9\u0011\u0011\\\u001dA\u0002\u0005U\u0014a\u00017pORA\u0011QOB3\u0007O\u001aI\u0007C\u0004\u0002lj\u0002\u001d!!<\t\u000f\tm&\bq\u0001\u0003>\"9\u0011Q\u001f\u001eA\u0004\u0005]\u0018\u0001B:reR$\u0002\"!\u001e\u0004p\rE41\u0010\u0005\b\u0003W\\\u00049AAw\u0011\u001d\u0019\u0019h\u000fa\u0002\u0007k\n\u0011A\u001c\t\u0007\u0003s\u001c9(a\r\n\t\re\u0014q\u0015\u0002\u0006\u001dJ{w\u000e\u001e\u0005\b\u0003k\\\u00049AA|\u0003\u0011\t7m\\:\u0015\u0015\u0005U4\u0011QBB\u0007\u000b\u001b9\tC\u0004\u0002lr\u0002\u001d!!<\t\u000f\rMD\bq\u0001\u0004v!9!1\u0018\u001fA\u0004\tu\u0006bBA{y\u0001\u000f\u0011q_\u0001\u0005CNLg\u000e\u0006\u0006\u0002v\r55qRBI\u0007'Cq!a;>\u0001\b\ti\u000fC\u0004\u0004tu\u0002\u001da!\u001e\t\u000f\tmV\bq\u0001\u0003>\"9\u0011Q_\u001fA\u0004\u0005]\u0018\u0001B1uC:$\u0002\"!\u001e\u0004\u001a\u000em5Q\u0014\u0005\b\u0003Wt\u00049AAw\u0011\u001d\u0011YL\u0010a\u0002\u0005{Cq!!>?\u0001\b\t90A\u0003bi\u0006t'\u0007\u0006\u0003\u0004$\u000e-F\u0003CA;\u0007K\u001b9k!+\t\u000f\u0005-x\bq\u0001\u0002n\"9!1X A\u0004\tu\u0006bBA{\u007f\u0001\u000f\u0011q\u001f\u0005\b\u0007_y\u0004\u0019AA;\u0003\r)\u0007\u0010\u001d\u000b\u0007\u0003k\u001a\tla-\t\u000f\tm\u0006\tq\u0001\u0003>\"9\u0011Q\u001f!A\u0004\u0005]\u0018aA:j]R1\u0011QOB]\u0007wCqAa/B\u0001\b\u0011i\fC\u0004\u0002v\u0006\u0003\u001d!a>\u0002\tMLg\u000e\u001b\u000b\u0007\u0003k\u001a\tma1\t\u000f\tm&\tq\u0001\u0003>\"9\u0011Q\u001f\"A\u0004\u0005]\u0018aA2pgRA\u0011QOBe\u0007\u0017\u001ci\rC\u0004\u0002l\u000e\u0003\u001d!!<\t\u000f\tm6\tq\u0001\u0003>\"9\u0011Q_\"A\u0004\u0005]\u0018\u0001B2pg\"$b!!\u001e\u0004T\u000eU\u0007b\u0002B^\t\u0002\u000f!Q\u0018\u0005\b\u0003k$\u00059AA|\u0003\r!\u0018M\u001c\u000b\t\u0003k\u001aYn!8\u0004`\"9\u00111^#A\u0004\u00055\bb\u0002B^\u000b\u0002\u000f!Q\u0018\u0005\b\u0003k,\u00059AA|\u0003\u0011!\u0018M\u001c5\u0015\u0011\u0005U4Q]Bt\u0007SDq!a;G\u0001\b\ti\u000fC\u0004\u0003<\u001a\u0003\u001dA!0\t\u000f\u0005Uh\tq\u0001\u0002x\u0006Qa\r\\8biZ\u000bG.^3\u0016\u0005\r=\bcA5\u0004r&\u001911\u001f6\u0003\u000b\u0019cw.\u0019;\u0002\u0017\u0011|WO\u00197f-\u0006dW/Z\u000b\u0003\u0007s\u00042![B~\u0013\r\u0019iP\u001b\u0002\u0007\t>,(\r\\3\u0002\u0013\tLH/\u001a,bYV,WC\u0001C\u0002!\rIGQA\u0005\u0004\t\u000fQ'\u0001\u0002\"zi\u0016\f!b\u001d5peR4\u0016\r\\;f+\t!i\u0001E\u0002j\t\u001fI1\u0001\"\u0005k\u0005\u0015\u0019\u0006n\u001c:u\u0003!Ig\u000e\u001e,bYV,\u0017!\u00037p]\u001e4\u0016\r\\;f+\t!I\u0002E\u0002j\t7I1\u0001\"\bk\u0005\u0011auN\\4\u0002\u0015UtG-\u001a:ms&tw\r\u0006\u0002\u0005$A!AQ\u0005C\u0016\u001b\t!9CC\u0002\u0005*U\fA\u0001\\1oO&!AQ\u0006C\u0014\u0005\u0019y%M[3di\u00069\u0011n],i_2,\u0017AC5t-\u0006d\u0017\u000eZ%oi\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0000\u00051Q-];bYN$B!!0\u0005<!9AQH)A\u0002\u0005\r\u0013\u0001\u0002;iCR\f\u0011\u0002J3rI\u0015\fH%Z9\u0015\t\u0005uF1\t\u0005\b\t{\u0011\u0006\u0019\u0001C#a\u0011!9\u0005b\u0013\u0011\t\u0015\u001cB\u0011\n\t\u0005\u0003k!Y\u0005\u0002\u0007\u0005N\u0011\r\u0013\u0011!A\u0001\u0006\u0003\tYDA\u0002`IE\n1\u0002J3rI\t\fgn\u001a\u0013fcR!\u0011Q\u0018C*\u0011\u001d!id\u0015a\u0001\t+\u0002D\u0001b\u0016\u0005\\A!Qm\u0005C-!\u0011\t)\u0004b\u0017\u0005\u0019\u0011uC1KA\u0001\u0002\u0003\u0015\t!a\u000f\u0003\u0007}##'\u0001\u0005u_N#(/\u001b8h)\t!\u0019\u0007\u0005\u0003\u0005f\u00115d\u0002\u0002C4\tS\u00022!!\u0007k\u0013\r!YG[\u0001\u0007!J,G-\u001a4\n\t\u0011=D\u0011\u000f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0011-$.\u0001\u0003d_BLX\u0003\u0002C<\t{\"b\u0001\"\u001f\u0005\n\u0012-\u0005\u0003B3\u0014\tw\u0002B!!\u000e\u0005~\u0011Y\u0011\u0011H+!\u0002\u0003\u0005)\u0019AA\u001eQ!!i(a\u0013\u0005\u0002\u0012\u0015\u0015'C\u0012\u0002T\u0005UC1QA,c\u0019!\u0013qCA\u0010WFJ1%!\u0018\u0002`\u0011\u001d\u0015\u0011M\u0019\u0007I\u0005]\u0011qD6\t\u0013\u0005=R\u000b%AA\u0002\u0011m\u0004\"CA4+B\u0005\t\u0019\u0001CG!\u0015I\u0017Q\u000eC>\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B\u0001b%\u0005*V\u0011AQ\u0013\u0016\u0005\u0003g!9j\u000b\u0002\u0005\u001aB!A1\u0014CS\u001b\t!iJ\u0003\u0003\u0005 \u0012\u0005\u0016!C;oG\",7m[3e\u0015\r!\u0019K[\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002CT\t;\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t-\tID\u0016Q\u0001\u0002\u0003\u0015\r!a\u000f)\u0011\u0011%\u00161\nCW\tc\u000b\u0014bIA*\u0003+\"y+a\u00162\r\u0011\n9\"a\blc%\u0019\u0013QLA0\tg\u000b\t'\r\u0004%\u0003/\tyb[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011!I\f\"0\u0016\u0005\u0011m&\u0006BA6\t/#1\"!\u000fXA\u0003\u0005\tQ1\u0001\u0002<!BAQXA&\t\u0003$)-M\u0005$\u0003'\n)\u0006b1\u0002XE2A%a\u0006\u0002 -\f\u0014bIA/\u0003?\"9-!\u00192\r\u0011\n9\"a\bl\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011AQ\u001a\t\u0005\tK!y-\u0003\u0003\u0005p\u0011\u001d\u0012\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u0007\"9\u000eC\u0005\u0005Zj\u000b\t\u00111\u0001\u0002\u0000\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"\u0001b8\u0011\r\u0011\u0005Hq]A\"\u001b\t!\u0019OC\u0002\u0005f*\f!bY8mY\u0016\u001cG/[8o\u0013\u0011!I\u000fb9\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003{#y\u000fC\u0005\u0005Zr\u000b\t\u00111\u0001\u0002D\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011!i\r\">\t\u0013\u0011eW,!AA\u0002\u0005}\u0004fB\n\u0005z\u0012}X\u0011\u0001\t\u0004S\u0012m\u0018b\u0001C\u007fU\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0001A!\u0011QGC\u0003\t-\tId\u0001Q\u0001\u0002\u0003\u0015\r!a\u000f)\u0011\u0015\u0015\u00111JC\u0005\u000b\u001b\t\u0014bIA*\u0003+*Y!a\u00162\r\u0011\n9\"a\blc%\u0019\u0013QLA0\u000b\u001f\t\t'\r\u0004%\u0003/\tyb\u001b\u0005\b\u00057\u001a\u00019AC\n!\u0019\u0011yF!\u001a\u0006\u0004!9QqC\u0002A\u0004\u0005%\u0015!\u00013\t\u000f\t]6\u0001q\u0001\u0006\u001cA1\u0011\u0011TC\u000f\u000b\u0007IA!b\b\u0002.\nA1+Z7je&tw-\u0006\u0003\u0006$\u0015-B\u0003BC\u0013\u000b\u0003\"\u0002\"b\n\u00068\u0015mRQ\b\t\u0005KN)I\u0003\u0005\u0003\u00026\u0015-BaCA\u001d\t\u0001\u0006\t\u0011!b\u0001\u0003wA\u0003\"b\u000b\u0002L\u0015=R1G\u0019\nG\u0005M\u0013QKC\u0019\u0003/\nd\u0001JA\f\u0003?Y\u0017'C\u0012\u0002^\u0005}SQGA1c\u0019!\u0013qCA\u0010W\"9!1\f\u0003A\u0004\u0015e\u0002C\u0002B0\u0005K*I\u0003C\u0004\u0006\u0018\u0011\u0001\u001d!!#\t\u000f\t]F\u0001q\u0001\u0006@A1\u0011\u0011TC\u000f\u000bSAq!a\f\u0005\u0001\u0004)I#\u0006\u0003\u0006F\u00155CCBC$\u000bO*I\u0007\u0006\u0005\u0006J\u0015eSQLC0!\u0011)7#b\u0013\u0011\t\u0005URQ\n\u0003\f\u0003s)\u0001\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006N\u0005-S\u0011KC+c%\u0019\u00131KA+\u000b'\n9&\r\u0004%\u0003/\tyb[\u0019\nG\u0005u\u0013qLC,\u0003C\nd\u0001JA\f\u0003?Y\u0007b\u0002B.\u000b\u0001\u000fQ1\f\t\u0007\u0005?\u0012)'b\u0013\t\u000f\u0015]Q\u0001q\u0001\u0002\n\"9\u0011QS\u0003A\u0004\u0015\u0005\u0004CBAM\u000bG*Y%\u0003\u0003\u0006f\u00055&a\u0001*jO\"91qF\u0003A\u0002\u0015-\u0003b\u0002Bd\u000b\u0001\u0007\u0011qP\u0001\u0002QV!QqNC<)\u0011)\t(\"$\u0015\u0011\u0015MT1QCD\u000b\u0013\u0003B!Z\n\u0006vA!\u0011QGC<\t-\tID\u0002Q\u0001\u0002\u0003\u0015\r!a\u000f)\u0011\u0015]\u00141JC>\u000b\u007f\n\u0014bIA*\u0003+*i(a\u00162\r\u0011\n9\"a\blc%\u0019\u0013QLA0\u000b\u0003\u000b\t'\r\u0004%\u0003/\tyb\u001b\u0005\b\u000572\u00019ACC!\u0019\u0011yF!\u001a\u0006v!9Qq\u0003\u0004A\u0004\u0005%\u0005bBAK\r\u0001\u000fQ1\u0012\t\u0007\u00033+\u0019'\"\u001e\t\u000f\t\u001dg\u00011\u0001\u0002\u0000\u0005\u0019qN\\3\u0016\t\u0015MU\u0011\u0014\u000b\t\u000b++)+\"+\u0006,B!QmECL!\u0011\t)$\"'\u0005\u0017\u0005er\u0001)A\u0001\u0002\u000b\u0007\u00111\b\u0015\t\u000b3\u000bY%\"(\u0006\"FJ1%a\u0015\u0002V\u0015}\u0015qK\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0006$\u0006\u0005\u0014G\u0002\u0013\u0002\u0018\u0005}1\u000eC\u0004\u0003\\\u001d\u0001\u001d!b*\u0011\r\t}#QMCL\u0011\u001d)9b\u0002a\u0002\u0003\u0013Cq!!&\b\u0001\b)i\u000b\u0005\u0004\u0002\u001a\u0016\rTqS\u0001\u0005u\u0016\u0014x.\u0006\u0003\u00064\u0016eF\u0003CC[\u000b\u000b,I-b3\u0011\t\u0015\u001cRq\u0017\t\u0005\u0003k)I\fB\u0006\u0002:!\u0001\u000b\u0011!AC\u0002\u0005m\u0002\u0006CC]\u0003\u0017*i,\"12\u0013\r\n\u0019&!\u0016\u0006@\u0006]\u0013G\u0002\u0013\u0002\u0018\u0005}1.M\u0005$\u0003;\ny&b1\u0002bE2A%a\u0006\u0002 -DqAa\u0017\t\u0001\b)9\r\u0005\u0004\u0003`\t\u0015Tq\u0017\u0005\b\u000b/A\u00019AAE\u0011\u001d\u00119\f\u0003a\u0002\u000b\u001b\u0004b!!'\u0006\u001e\u0015]\u0016a\u00024s_6Le\u000e^\u000b\u0005\u000b',Y\u000e\u0006\u0003\u0006V\u0016UH\u0003CCl\u000bO,Y/\"<\u0011\t\u0015\u001cR\u0011\u001c\t\u0005\u0003k)Y\u000eB\u0006\u0002:%\u0001\u000b\u0011!AC\u0002\u0005m\u0002\u0006CCn\u0003\u0017*y.b92\u0013\r\n\u0019&!\u0016\u0006b\u0006]\u0013G\u0002\u0013\u0002\u0018\u0005}1.M\u0005$\u0003;\ny&\":\u0002bE2A%a\u0006\u0002 -DqAa\u0017\n\u0001\b)I\u000f\u0005\u0004\u0003`\t\u0015T\u0011\u001c\u0005\b\u000b/I\u00019AAE\u0011\u001d\t)*\u0003a\u0002\u000b_\u0004b!!'\u0006r\u0016e\u0017\u0002BCz\u0003[\u0013AAU5oO\"911O\u0005A\u0002\u0005}\u0014\u0001C5oiR{'*\u001a;\u0015\t\u0015mh\u0011\u0001\u000b\u0005\u000b{,y\u0010\u0005\u0003f'\re\bbBC\f\u0015\u0001\u000f\u0011\u0011\u0012\u0005\b\u0007gR\u0001\u0019AA@\u0003%awN\\4U_*+G\u000f\u0006\u0003\u0007\b\u0019-A\u0003BC\u007f\r\u0013Aq!b\u0006\f\u0001\b\tI\tC\u0004\u0004t-\u0001\r\u0001\"\u0007\u0002\u0015\u0019dw.\u0019;U_*+G\u000f\u0006\u0003\u0007\u0012\u0019]A\u0003\u0002D\n\r+\u0001B!Z\n\u0004p\"9Qq\u0003\u0007A\u0004\u0005%\u0005bBB:\u0019\u0001\u00071q^\u0001\fI>,(\r\\3U_*+G\u000f\u0006\u0003\u0007\u001e\u0019\u0005B\u0003BC\u007f\r?Aq!b\u0006\u000e\u0001\b\tI\tC\u0004\u0004t5\u0001\ra!?\u0002\u0017\tLw-\u00138u)>TU\r\u001e\u000b\u0005\rO1\u0019\u0004\u0006\u0003\u0007*\u0019E\u0002\u0003B3\u0014\rW\u0001B!!\u0002\u0007.%!aqFA\u0004\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\u001c\u0005\b\u000b/q\u00019AAE\u0011\u001d\u0019\u0019H\u0004a\u0001\rk\u0001B!!\u0002\u00078%!a\u0011HA\u0004\u0005\u0019\u0011\u0015nZ%oi\u0006y!-[4EK\u000eLW.\u00197U_*+G\u000f\u0006\u0003\u0007@\u0019\rC\u0003\u0002D\u0015\r\u0003Bq!b\u0006\u0010\u0001\b\tI\tC\u0004\u0004t=\u0001\rAb\u000b\u0016\t\u0019\u001dcQ\n\u000b\u0007\r\u00132IFb\u0017\u0011\t\u0015\u001cb1\n\t\u0005\u0003k1i\u0005B\u0006\u0002:A\u0001\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0003D'\u0003\u00172\tF\"\u00162\u0013\r\n\u0019&!\u0016\u0007T\u0005]\u0013G\u0002\u0013\u0002\u0018\u0005}1.M\u0005$\u0003;\nyFb\u0016\u0002bE2A%a\u0006\u0002 -Dq!a\f\u0011\u0001\u00041Y\u0005C\u0004\u0002hA\u0001\rA\"\u0018\u0011\u000b%\fiGb\u0013\u0002\u000fUt\u0017\r\u001d9msV!a1\rD8)\u00111)G\" \u0011\u000b%49Gb\u001b\n\u0007\u0019%$N\u0001\u0004PaRLwN\u001c\t\bS\u0006UfQ\u000eD>!\u0011\t)Db\u001c\u0005\u0017\u0005e\u0012\u0003)A\u0001\u0002\u000b\u0007\u00111\b\u0015\t\r_\nYEb\u001d\u0007xEJ1%a\u0015\u0002V\u0019U\u0014qK\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0007z\u0005\u0005\u0014G\u0002\u0013\u0002\u0018\u0005}1\u000eE\u0003j\u0003[2i\u0007C\u0005\u0007\u0000E\t\t\u00111\u0001\u0007\u0002\u0006\u0019\u0001\u0010\n\u0019\u0011\t\u0015\u001cbQN\u0001\roJLG/\u001a*fa2\f7-\u001a"
)
public class Jet extends ScalaNumber implements ScalaNumericConversions, Product {
   private static final long serialVersionUID = 0L;
   public final Object real;
   public final Object infinitesimal;

   public static Option unapply(final Jet x$0) {
      return Jet$.MODULE$.unapply(x$0);
   }

   public static Jet apply(final Object real, final Object infinitesimal) {
      return Jet$.MODULE$.apply(real, infinitesimal);
   }

   public static Jet bigDecimalToJet(final BigDecimal n, final JetDim d) {
      return Jet$.MODULE$.bigDecimalToJet(n, d);
   }

   public static Jet bigIntToJet(final BigInt n, final JetDim d) {
      return Jet$.MODULE$.bigIntToJet(n, d);
   }

   public static Jet doubleToJet(final double n, final JetDim d) {
      return Jet$.MODULE$.doubleToJet(n, d);
   }

   public static Jet floatToJet(final float n, final JetDim d) {
      return Jet$.MODULE$.floatToJet(n, d);
   }

   public static Jet longToJet(final long n, final JetDim d) {
      return Jet$.MODULE$.longToJet(n, d);
   }

   public static Jet intToJet(final int n, final JetDim d) {
      return Jet$.MODULE$.intToJet(n, d);
   }

   public static Jet fromInt(final int n, final ClassTag c, final JetDim d, final Ring r) {
      return Jet$.MODULE$.fromInt(n, c, d, r);
   }

   public static Jet zero(final ClassTag c, final JetDim d, final Semiring s) {
      return Jet$.MODULE$.zero(c, d, s);
   }

   public static Jet one(final ClassTag c, final JetDim d, final Rig r) {
      return Jet$.MODULE$.one(c, d, r);
   }

   public static Jet h(final int k, final ClassTag c, final JetDim d, final Rig r) {
      return Jet$.MODULE$.h(k, c, d, r);
   }

   public static Jet apply(final Object a, final int k, final ClassTag c, final JetDim d, final Rig r) {
      return Jet$.MODULE$.apply(a, k, c, d, r);
   }

   public static Jet apply(final Object real, final ClassTag c, final JetDim d, final Semiring s) {
      return Jet$.MODULE$.apply(real, c, d, s);
   }

   public static Jet apply(final ClassTag c, final JetDim d, final Semiring s) {
      return Jet$.MODULE$.apply(c, d, s);
   }

   public static Eq JetEq(final Eq evidence$1) {
      return Jet$.MODULE$.JetEq(evidence$1);
   }

   public static JetAlgebra JetAlgebra(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return Jet$.MODULE$.JetAlgebra(c, d, f, n, o, s, t);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
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

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public Object real() {
      return this.real;
   }

   public Object infinitesimal() {
      return this.infinitesimal;
   }

   public int dimension() {
      return .MODULE$.size$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()));
   }

   public JetDim jetDimension() {
      return new JetDim(this.dimension());
   }

   public int signum(final Signed r) {
      return r.signum(this.real());
   }

   public Tuple2 asTuple() {
      return new Tuple2(this.real(), this.infinitesimal());
   }

   public boolean isReal() {
      return .MODULE$.forall$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (n) -> BoxesRunTime.boxToBoolean($anonfun$isReal$1(n)));
   }

   public boolean isZero() {
      return package$.MODULE$.anyIsZero(this.real()) && this.isReal();
   }

   public boolean isInfinitesimal() {
      return package$.MODULE$.anyIsZero(this.real()) && !this.isReal();
   }

   public boolean eqv(final Jet b, final Eq o) {
      return o.eqv(this.real(), b.real()) && ArraySupport$.MODULE$.eqv(this.infinitesimal(), b.infinitesimal(), o);
   }

   public boolean neqv(final Jet b, final Eq o) {
      return !this.eqv(b, o);
   }

   public Jet unary_$minus(final Field f, final VectorSpace v) {
      return new Jet(f.negate(this.real()), v.negate(this.infinitesimal()));
   }

   public Jet $plus(final Object b, final Field f) {
      return new Jet(f.plus(this.real(), b), this.infinitesimal());
   }

   public Jet $minus(final Object b, final Field f) {
      return new Jet(f.minus(this.real(), b), this.infinitesimal());
   }

   public Jet $times(final Object b, final Field f, final VectorSpace v) {
      return new Jet(f.times(this.real(), b), v.timesr(this.infinitesimal(), b));
   }

   public Jet $div(final Object b, final Field f, final VectorSpace v) {
      return new Jet(f.div(this.real(), b), v.divr(this.infinitesimal(), b));
   }

   public Jet $plus(final Jet b, final Field f, final VectorSpace v) {
      return new Jet(f.plus(this.real(), b.real()), v.plus(this.infinitesimal(), b.infinitesimal()));
   }

   public Jet $minus(final Jet b, final Field f, final VectorSpace v) {
      return new Jet(f.minus(this.real(), b.real()), v.minus(this.infinitesimal(), b.infinitesimal()));
   }

   public Jet $times(final Jet b, final Field f, final VectorSpace v) {
      Object var10002 = f.times(this.real(), b.real());
      Object var4 = b.real();
      Object var10004 = v.timesl(var4, this.infinitesimal());
      Object var5 = this.real();
      return new Jet(var10002, v.plus(var10004, v.timesl(var5, b.infinitesimal())));
   }

   public Jet $div(final Jet b, final Field f, final VectorSpace v) {
      Object br_inv = f.div(f.one(), b.real());
      Object ar_div_br = f.times(this.real(), br_inv);
      return new Jet(ar_div_br, v.timesl(br_inv, v.minus(this.infinitesimal(), v.timesl(ar_div_br, b.infinitesimal()))));
   }

   public Jet $div$tilde(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div(b, f, v);
      return new Jet(r.floor(q.real()), .MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(q.infinitesimal()), (a) -> r.floor(a), c));
   }

   public Jet $percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$minus(this.$div$tilde(b, c, f, r, v).$times(b, f, v), f, v);
   }

   public Tuple2 $div$percent(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      Jet q = this.$div$tilde(b, c, f, r, v);
      return new Tuple2(q, this.$minus(q.$times(b, f, v), f, v));
   }

   public Jet $times$times(final int b, final Field f, final VectorSpace v) {
      return this.pow(b, f, v);
   }

   public Jet nroot(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow(f.reciprocal(f.fromInt(k)), f, o, s, t, v);
   }

   public Jet $times$times(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow(b, c, f, v, o, s, t);
   }

   public Jet floor(final ClassTag c, final IsReal r) {
      return new Jet(r.floor(this.real()), .MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (a) -> r.floor(a), c));
   }

   public Jet ceil(final ClassTag c, final IsReal r) {
      return new Jet(r.ceil(this.real()), .MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (a) -> r.ceil(a), c));
   }

   public Jet round(final ClassTag c, final IsReal r) {
      return new Jet(r.round(this.real()), .MODULE$.map$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), (a) -> r.round(a), c));
   }

   public Jet abs(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return o.lt(this.real(), f.zero()) ? new Jet(f.negate(this.real()), v.negate(this.infinitesimal())) : this;
   }

   public Object powScalarToScalar(final Object b, final Object e, final Field f, final Order o, final Signed s, final Trig t) {
      Object var10000;
      if (o.eqv(e, f.zero())) {
         var10000 = f.one();
      } else if (o.eqv(b, f.zero())) {
         if (o.lt(e, f.zero())) {
            throw new Exception("raising 0 to a negative power");
         }

         var10000 = f.zero();
      } else {
         var10000 = package$.MODULE$.exp(f.times(e, package$.MODULE$.log(b, t)), t);
      }

      return var10000;
   }

   public Jet powScalarToJet(final Object a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Jet var10000;
      if (this.isZero()) {
         var10000 = Jet$.MODULE$.one(c, this.jetDimension(), f);
      } else {
         Object tmp = this.powScalarToScalar(a, this.real(), f, o, s, t);
         Object var9 = f.times(package$.MODULE$.log(a, t), tmp);
         var10000 = new Jet(tmp, m.timesl(var9, this.infinitesimal()));
      }

      return var10000;
   }

   public Jet pow(final Object p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      Object tmp = f.times(p, this.powScalarToScalar(this.real(), f.minus(p, f.one()), f, o, s, t));
      return new Jet(this.powScalarToScalar(this.real(), p, f, o, s, t), v.timesl(tmp, this.infinitesimal()));
   }

   public Jet pow(final int p, final Field f, final VectorSpace v) {
      Object tmp = LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(spire.syntax.package.vectorSpace$.MODULE$.literalIntMultiplicativeSemigroupOps(p), f.pow(this.real(), p - 1), f);
      return new Jet(f.pow(this.real(), p), v.timesl(tmp, this.infinitesimal()));
   }

   public Jet pow(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      Jet var10000;
      if (b.isZero()) {
         var10000 = Jet$.MODULE$.one(c, this.jetDimension(), f);
      } else {
         Object tmp1 = this.powScalarToScalar(this.real(), b.real(), f, o, s, t);
         Object tmp2 = f.times(b.real(), this.powScalarToScalar(this.real(), f.minus(b.real(), f.one()), f, o, s, t));
         Object tmp3 = f.times(tmp1, package$.MODULE$.log(this.real(), t));
         var10000 = new Jet(tmp1, m.plus(m.timesl(tmp2, this.infinitesimal()), m.timesl(tmp3, b.infinitesimal())));
      }

      return var10000;
   }

   public Jet log(final Field f, final Trig t, final VectorSpace v) {
      Object var10002 = package$.MODULE$.log(this.real(), t);
      Object var4 = f.div(f.one(), this.real());
      return new Jet(var10002, v.timesl(var4, this.infinitesimal()));
   }

   public Jet sqrt(final Field f, final NRoot n, final VectorSpace v) {
      Object sa = n.sqrt(this.real());
      Object oneHalf = f.div(f.one(), f.plus(f.one(), f.one()));
      Object var6 = f.div(oneHalf, sa);
      return new Jet(sa, v.timesl(var6, this.infinitesimal()));
   }

   public Jet acos(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      Object tmp = f.div(f.negate(f.one()), package$.MODULE$.sqrt(f.minus(f.one(), f.times(this.real(), this.real())), n));
      return new Jet(package$.MODULE$.acos(this.real(), t), v.timesl(tmp, this.infinitesimal()));
   }

   public Jet asin(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      Object tmp = f.div(f.one(), package$.MODULE$.sqrt(f.minus(f.one(), f.times(this.real(), this.real())), n));
      return new Jet(package$.MODULE$.asin(this.real(), t), v.timesl(tmp, this.infinitesimal()));
   }

   public Jet atan(final Field f, final Trig t, final VectorSpace v) {
      Object tmp = f.div(f.one(), f.plus(f.one(), f.times(this.real(), this.real())));
      return new Jet(package$.MODULE$.atan(this.real(), t), v.timesl(tmp, this.infinitesimal()));
   }

   public Jet atan2(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      Object tmp = f.div(f.one(), f.plus(f.times(a.real(), a.real()), f.times(this.real(), this.real())));
      Object var10002 = package$.MODULE$.atan2(this.real(), a.real(), t);
      Object var6 = f.times(tmp, f.negate(this.real()));
      Object var10004 = v.timesl(var6, a.infinitesimal());
      Object var7 = f.times(tmp, a.real());
      return new Jet(var10002, v.plus(var10004, v.timesl(var7, this.infinitesimal())));
   }

   public Jet exp(final Trig t, final VectorSpace v) {
      Object ea = package$.MODULE$.exp(this.real(), t);
      return new Jet(ea, v.timesl(ea, this.infinitesimal()));
   }

   public Jet sin(final Trig t, final VectorSpace v) {
      Object var10002 = package$.MODULE$.sin(this.real(), t);
      Object var3 = package$.MODULE$.cos(this.real(), t);
      return new Jet(var10002, v.timesl(var3, this.infinitesimal()));
   }

   public Jet sinh(final Trig t, final VectorSpace v) {
      Object var10002 = package$.MODULE$.sinh(this.real(), t);
      Object var3 = package$.MODULE$.cosh(this.real(), t);
      return new Jet(var10002, v.timesl(var3, this.infinitesimal()));
   }

   public Jet cos(final Field f, final Trig t, final VectorSpace v) {
      Object var10002 = package$.MODULE$.cos(this.real(), t);
      Object var4 = f.negate(package$.MODULE$.sin(this.real(), t));
      return new Jet(var10002, v.timesl(var4, this.infinitesimal()));
   }

   public Jet cosh(final Trig t, final VectorSpace v) {
      Object var10002 = package$.MODULE$.cosh(this.real(), t);
      Object var3 = package$.MODULE$.sinh(this.real(), t);
      return new Jet(var10002, v.timesl(var3, this.infinitesimal()));
   }

   public Jet tan(final Field f, final Trig t, final VectorSpace v) {
      Object tan_a = package$.MODULE$.tan(this.real(), t);
      Object tmp = f.plus(f.one(), f.times(tan_a, tan_a));
      return new Jet(tan_a, v.timesl(tmp, this.infinitesimal()));
   }

   public Jet tanh(final Field f, final Trig t, final VectorSpace v) {
      Object tanh_a = package$.MODULE$.tanh(this.real(), t);
      Object tmp = f.minus(f.one(), f.times(tanh_a, tanh_a));
      return new Jet(tanh_a, v.timesl(tmp, this.infinitesimal()));
   }

   public float floatValue() {
      return (float)this.doubleValue();
   }

   public double doubleValue() {
      return package$.MODULE$.anyToDouble(this.real());
   }

   public byte byteValue() {
      return (byte)((int)this.longValue());
   }

   public short shortValue() {
      return (short)((int)this.longValue());
   }

   public int intValue() {
      return (int)this.longValue();
   }

   public long longValue() {
      return package$.MODULE$.anyToLong(this.real());
   }

   public Object underlying() {
      return this;
   }

   public boolean isWhole() {
      return package$.MODULE$.anyIsWhole(this.real()) && this.isReal();
   }

   public boolean isValidInt() {
      return package$.MODULE$.anyIsValidInt(this.real()) && this.isReal();
   }

   public int hashCode() {
      return this.isReal() ? Statics.anyHash(this.real()) : 13 * Statics.anyHash(this.real()) + BoxesRunTime.unboxToInt(.MODULE$.foldLeft$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), BoxesRunTime.boxToInteger(53), (x, y) -> BoxesRunTime.boxToInteger($anonfun$hashCode$1(BoxesRunTime.unboxToInt(x), y))));
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Jet) {
         Jet var4 = (Jet)that;
         var2 = this.$eq$eq$eq(var4);
      } else {
         var2 = this.isReal() && BoxesRunTime.equals(this.real(), that);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Jet that) {
      return BoxesRunTime.equals(this.real(), that.real()) && this.dimension() == that.dimension() && .MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.genericArrayOps(this.infinitesimal()), scala.Predef..MODULE$.genericWrapArray(that.infinitesimal()))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$$eq$eq$eq$1(x0$1)));
   }

   public boolean $eq$bang$eq(final Jet that) {
      return !this.$eq$eq$eq(that);
   }

   public String toString() {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(%s + [%s]h)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{this.real().toString(), scala.Predef..MODULE$.genericWrapArray(this.infinitesimal()).mkString(", ")}));
   }

   public Jet copy(final Object real, final Object infinitesimal) {
      return new Jet(real, infinitesimal);
   }

   public Object copy$default$1() {
      return this.real();
   }

   public Object copy$default$2() {
      return this.infinitesimal();
   }

   public String productPrefix() {
      return "Jet";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.real();
            break;
         case 1:
            var10000 = this.infinitesimal();
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
      return x$1 instanceof Jet;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "real";
            break;
         case 1:
            var10000 = "infinitesimal";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public double real$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.real());
   }

   public float real$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.real());
   }

   public double[] infinitesimal$mcD$sp() {
      return (double[])this.infinitesimal();
   }

   public float[] infinitesimal$mcF$sp() {
      return (float[])this.infinitesimal();
   }

   public int signum$mcD$sp(final Signed r) {
      return this.signum(r);
   }

   public int signum$mcF$sp(final Signed r) {
      return this.signum(r);
   }

   public Tuple2 asTuple$mcD$sp() {
      return this.asTuple();
   }

   public Tuple2 asTuple$mcF$sp() {
      return this.asTuple();
   }

   public boolean eqv$mcD$sp(final Jet b, final Eq o) {
      return this.eqv(b, o);
   }

   public boolean eqv$mcF$sp(final Jet b, final Eq o) {
      return this.eqv(b, o);
   }

   public boolean neqv$mcD$sp(final Jet b, final Eq o) {
      return this.neqv(b, o);
   }

   public boolean neqv$mcF$sp(final Jet b, final Eq o) {
      return this.neqv(b, o);
   }

   public Jet unary_$minus$mcD$sp(final Field f, final VectorSpace v) {
      return this.unary_$minus(f, v);
   }

   public Jet unary_$minus$mcF$sp(final Field f, final VectorSpace v) {
      return this.unary_$minus(f, v);
   }

   public Jet $plus$mcD$sp(final double b, final Field f) {
      return this.$plus(BoxesRunTime.boxToDouble(b), f);
   }

   public Jet $plus$mcF$sp(final float b, final Field f) {
      return this.$plus(BoxesRunTime.boxToFloat(b), f);
   }

   public Jet $minus$mcD$sp(final double b, final Field f) {
      return this.$minus(BoxesRunTime.boxToDouble(b), f);
   }

   public Jet $minus$mcF$sp(final float b, final Field f) {
      return this.$minus(BoxesRunTime.boxToFloat(b), f);
   }

   public Jet $times$mcD$sp(final double b, final Field f, final VectorSpace v) {
      return this.$times((Object)BoxesRunTime.boxToDouble(b), f, v);
   }

   public Jet $times$mcF$sp(final float b, final Field f, final VectorSpace v) {
      return this.$times((Object)BoxesRunTime.boxToFloat(b), f, v);
   }

   public Jet $div$mcD$sp(final double b, final Field f, final VectorSpace v) {
      return this.$div((Object)BoxesRunTime.boxToDouble(b), f, v);
   }

   public Jet $div$mcF$sp(final float b, final Field f, final VectorSpace v) {
      return this.$div((Object)BoxesRunTime.boxToFloat(b), f, v);
   }

   public Jet $plus$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$plus(b, f, v);
   }

   public Jet $plus$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$plus(b, f, v);
   }

   public Jet $minus$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$minus(b, f, v);
   }

   public Jet $minus$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$minus(b, f, v);
   }

   public Jet $times$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$times(b, f, v);
   }

   public Jet $times$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$times(b, f, v);
   }

   public Jet $div$mcD$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$div(b, f, v);
   }

   public Jet $div$mcF$sp(final Jet b, final Field f, final VectorSpace v) {
      return this.$div(b, f, v);
   }

   public Jet $div$tilde$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$tilde(b, c, f, r, v);
   }

   public Jet $div$tilde$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$tilde(b, c, f, r, v);
   }

   public Jet $percent$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$percent(b, c, f, r, v);
   }

   public Jet $percent$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$percent(b, c, f, r, v);
   }

   public Tuple2 $div$percent$mcD$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$percent(b, c, f, r, v);
   }

   public Tuple2 $div$percent$mcF$sp(final Jet b, final ClassTag c, final Field f, final IsReal r, final VectorSpace v) {
      return this.$div$percent(b, c, f, r, v);
   }

   public Jet $times$times$mcD$sp(final int b, final Field f, final VectorSpace v) {
      return this.$times$times(b, f, v);
   }

   public Jet $times$times$mcF$sp(final int b, final Field f, final VectorSpace v) {
      return this.$times$times(b, f, v);
   }

   public Jet nroot$mcD$sp(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.nroot(k, f, o, s, t, v);
   }

   public Jet nroot$mcF$sp(final int k, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.nroot(k, f, o, s, t, v);
   }

   public Jet $times$times$mcD$sp(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.$times$times(b, c, f, o, s, t, v);
   }

   public Jet $times$times$mcF$sp(final Jet b, final ClassTag c, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.$times$times(b, c, f, o, s, t, v);
   }

   public Jet floor$mcD$sp(final ClassTag c, final IsReal r) {
      return this.floor(c, r);
   }

   public Jet floor$mcF$sp(final ClassTag c, final IsReal r) {
      return this.floor(c, r);
   }

   public Jet ceil$mcD$sp(final ClassTag c, final IsReal r) {
      return this.ceil(c, r);
   }

   public Jet ceil$mcF$sp(final ClassTag c, final IsReal r) {
      return this.ceil(c, r);
   }

   public Jet round$mcD$sp(final ClassTag c, final IsReal r) {
      return this.round(c, r);
   }

   public Jet round$mcF$sp(final ClassTag c, final IsReal r) {
      return this.round(c, r);
   }

   public Jet abs$mcD$sp(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return this.abs(f, o, s, v);
   }

   public Jet abs$mcF$sp(final Field f, final Order o, final Signed s, final VectorSpace v) {
      return this.abs(f, o, s, v);
   }

   public double powScalarToScalar$mcD$sp(final double b, final double e, final Field f, final Order o, final Signed s, final Trig t) {
      return BoxesRunTime.unboxToDouble(this.powScalarToScalar(BoxesRunTime.boxToDouble(b), BoxesRunTime.boxToDouble(e), f, o, s, t));
   }

   public float powScalarToScalar$mcF$sp(final float b, final float e, final Field f, final Order o, final Signed s, final Trig t) {
      return BoxesRunTime.unboxToFloat(this.powScalarToScalar(BoxesRunTime.boxToFloat(b), BoxesRunTime.boxToFloat(e), f, o, s, t));
   }

   public Jet powScalarToJet$mcD$sp(final double a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.powScalarToJet(BoxesRunTime.boxToDouble(a), c, f, m, o, s, t);
   }

   public Jet powScalarToJet$mcF$sp(final float a, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.powScalarToJet(BoxesRunTime.boxToFloat(a), c, f, m, o, s, t);
   }

   public Jet pow$mcD$sp(final double p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow(BoxesRunTime.boxToDouble(p), f, o, s, t, v);
   }

   public Jet pow$mcF$sp(final float p, final Field f, final Order o, final Signed s, final Trig t, final VectorSpace v) {
      return this.pow(BoxesRunTime.boxToFloat(p), f, o, s, t, v);
   }

   public Jet pow$mcD$sp(final int p, final Field f, final VectorSpace v) {
      return this.pow(p, f, v);
   }

   public Jet pow$mcF$sp(final int p, final Field f, final VectorSpace v) {
      return this.pow(p, f, v);
   }

   public Jet pow$mcD$sp(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.pow(b, c, f, m, o, s, t);
   }

   public Jet pow$mcF$sp(final Jet b, final ClassTag c, final Field f, final CModule m, final Order o, final Signed s, final Trig t) {
      return this.pow(b, c, f, m, o, s, t);
   }

   public Jet log$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.log(f, t, v);
   }

   public Jet log$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.log(f, t, v);
   }

   public Jet sqrt$mcD$sp(final Field f, final NRoot n, final VectorSpace v) {
      return this.sqrt(f, n, v);
   }

   public Jet sqrt$mcF$sp(final Field f, final NRoot n, final VectorSpace v) {
      return this.sqrt(f, n, v);
   }

   public Jet acos$mcD$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.acos(f, n, t, v);
   }

   public Jet acos$mcF$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.acos(f, n, t, v);
   }

   public Jet asin$mcD$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.asin(f, n, t, v);
   }

   public Jet asin$mcF$sp(final Field f, final NRoot n, final Trig t, final VectorSpace v) {
      return this.asin(f, n, t, v);
   }

   public Jet atan$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.atan(f, t, v);
   }

   public Jet atan$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.atan(f, t, v);
   }

   public Jet atan2$mcD$sp(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      return this.atan2(a, f, t, v);
   }

   public Jet atan2$mcF$sp(final Jet a, final Field f, final Trig t, final VectorSpace v) {
      return this.atan2(a, f, t, v);
   }

   public Jet exp$mcD$sp(final Trig t, final VectorSpace v) {
      return this.exp(t, v);
   }

   public Jet exp$mcF$sp(final Trig t, final VectorSpace v) {
      return this.exp(t, v);
   }

   public Jet sin$mcD$sp(final Trig t, final VectorSpace v) {
      return this.sin(t, v);
   }

   public Jet sin$mcF$sp(final Trig t, final VectorSpace v) {
      return this.sin(t, v);
   }

   public Jet sinh$mcD$sp(final Trig t, final VectorSpace v) {
      return this.sinh(t, v);
   }

   public Jet sinh$mcF$sp(final Trig t, final VectorSpace v) {
      return this.sinh(t, v);
   }

   public Jet cos$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.cos(f, t, v);
   }

   public Jet cos$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.cos(f, t, v);
   }

   public Jet cosh$mcD$sp(final Trig t, final VectorSpace v) {
      return this.cosh(t, v);
   }

   public Jet cosh$mcF$sp(final Trig t, final VectorSpace v) {
      return this.cosh(t, v);
   }

   public Jet tan$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.tan(f, t, v);
   }

   public Jet tan$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.tan(f, t, v);
   }

   public Jet tanh$mcD$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.tanh(f, t, v);
   }

   public Jet tanh$mcF$sp(final Field f, final Trig t, final VectorSpace v) {
      return this.tanh(f, t, v);
   }

   public Jet copy$mDc$sp(final double real, final double[] infinitesimal) {
      return new Jet$mcD$sp(real, infinitesimal);
   }

   public Jet copy$mFc$sp(final float real, final float[] infinitesimal) {
      return new Jet$mcF$sp(real, infinitesimal);
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public float copy$default$1$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$1());
   }

   public double[] copy$default$2$mcD$sp() {
      return (double[])this.copy$default$2();
   }

   public float[] copy$default$2$mcF$sp() {
      return (float[])this.copy$default$2();
   }

   public boolean specInstance$() {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isReal$1(final Object n) {
      return package$.MODULE$.anyIsZero(n);
   }

   // $FF: synthetic method
   public static final int $anonfun$hashCode$1(final int x, final Object y) {
      return x + Statics.anyHash(y) * 19;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$eq$eq$eq$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Object x = x0$1._1();
         Object y = x0$1._2();
         boolean var1 = BoxesRunTime.equals(x, y);
         return var1;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public Jet(final Object real, final Object infinitesimal) {
      this.real = real;
      this.infinitesimal = infinitesimal;
      ScalaNumericAnyConversions.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
