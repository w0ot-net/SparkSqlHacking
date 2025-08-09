package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import scala.MatchError;
import scala.Tuple2;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Ordered;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.ObjectRef;
import scala.runtime.RichLong;
import scala.runtime.Statics;
import spire.macros.ArithmeticOverflowException;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011}c\u0001CA-\u00037\n\t#!\u001a\t\u000f\u0005]\u0005\u0001\"\u0001\u0002\u001a\"9\u00111\u0014\u0001\u0007\u0002\u0005u\u0005bBAS\u0001\u0019\u0005\u0011Q\u0014\u0005\b\u0003O\u0003a\u0011AAU\u0011\u001d\t\u0019\f\u0001D\t\u0003SCq!!.\u0001\r\u0003\t9\fC\u0004\u0002@\u00021\t!!+\t\u000f\u0005\u0005\u0007A\"\u0005\u0002*\"9\u00111\u0019\u0001\u0007\u0002\u0005]\u0006bBAc\u0001\u0019\u0005\u0011\u0011\u0016\u0005\b\u0003\u000f\u0004a\u0011AAU\u0011\u001d\tI\r\u0001C\u0001\u0003oCq!a3\u0001\t\u0003\ti\rC\u0004\u0002V\u0002!\t!a6\t\u000f\u0005}\u0007\u0001\"\u0011\u0002b\"9\u00111\u001f\u0001\u0005\u0002\u0005U\bbBA|\u0001\u0011\u0005\u0011Q\u001f\u0005\b\u0003s\u0004a\u0011AA{\u0011\u001d\tY\u0010\u0001D\u0001\u0003\u001bDq!!@\u0001\r\u0003\tI\u000bC\u0004\u0002\u0000\u00021\t!!+\t\u000f\t\u0005\u0001A\"\u0001\u0002v\"9!1\u0001\u0001\u0007\u0002\t\u0015\u0001b\u0002B\u0006\u0001\u0019\u0005!Q\u0002\u0005\b\u0005#\u0001a\u0011\u0001B\n\u0011\u001d\u00119\u0002\u0001D\u0001\u00053AqA!\b\u0001\t\u0003\u0011y\u0002C\u0004\u0003$\u0001!\tA!\n\t\u000f\t%\u0002\u0001\"\u0001\u0003,!9!q\u0006\u0001\u0005\u0002\tE\u0002b\u0002B\u001e\u0001\u0011\u0005!Q\b\u0005\b\u0005\u0003\u0002A\u0011\u0001B\"\u0011\u001d\u00119\u0005\u0001C\u0001\u0005\u0013BqA!\u0015\u0001\t\u0003\u0011\u0019\u0006C\u0004\u0003\\\u0001!\tA!\u0018\t\u000f\tm\u0003\u0001\"\u0001\u0003x!9!1\u0011\u0001\u0007\u0002\u0005u\u0005b\u0002BC\u0001\u0011\u0015!q\u0011\u0005\b\u0005\u001f\u0003A\u0011\tBI\u0011\u001d\u0011I\n\u0001C!\u00057CqAa)\u0001\r\u0003\t)\u0010C\u0004\u0003&\u00021\t!!>\t\u000f\t\u001d\u0006A\"\u0001\u0002v\"9!\u0011\u0016\u0001\u0005\u0002\t-\u0006b\u0002BY\u0001\u0019\u0005!1\u0017\u0005\b\u0005s\u0003A\u0011\u0001B^\u0011\u001d\u0011)\u000e\u0001D\u0001\u0003\u001bDqAa6\u0001\t\u0003\u0011I\u000eC\u0004\u0003^\u0002!\tAa8\t\u000f\t\r\b\u0001\"\u0001\u0002v\"9!Q\u001d\u0001\u0005\u0002\u0005U\bb\u0002Bt\u0001\u0011\u0005!\u0011\u001e\u0005\b\u0005[\u0004A\u0011\u0001Bx\u0011\u001d\u0011)\u0010\u0001C!\u0005o<\u0001\u0002\"\u0018\u0002\\!\u000511\u0005\u0004\t\u00033\nY\u0006#\u0001\u0004\b!9\u0011q\u0013\u001d\u0005\u0002\r\u0005\u0002\"CB\u0013q\t\u0007I\u0011BB\u0014\u0011!\u0019I\u0004\u000fQ\u0001\n\r%\u0002\"CB\u001eq\t\u0007I\u0011BB\u0014\u0011!\u0019i\u0004\u000fQ\u0001\n\r%\u0002\"CB q\t\u0007I\u0011AA{\u0011!\u0019\t\u0005\u000fQ\u0001\n\u0005M\u0005\"CB\"q\t\u0007I\u0011AA{\u0011!\u0019)\u0005\u000fQ\u0001\n\u0005M\u0005bCB$q\t\u0007I\u0011AA.\u0005\u000fC\u0001b!\u00139A\u0003%!\u0011\u0012\u0005\f\u0007\u0017B$\u0019!C\u0001\u00037\u00129\t\u0003\u0005\u0004Na\u0002\u000b\u0011\u0002BE\u0011-\u0019y\u0005\u000fb\u0001\n\u0003\tYFa\"\t\u0011\rE\u0003\b)A\u0005\u0005\u0013C1ba\u00159\u0005\u0004%\t!a\u0017\u0003\b\"A1Q\u000b\u001d!\u0002\u0013\u0011I\tC\u0005\u0004Xa\"\t!a\u0017\u0004Z!91\u0011\u000e\u001d\u0005\u0002\r-\u0004bBB5q\u0011\u00051\u0011\u000f\u0005\n\u0007oBD\u0011AA.\u0007sBqa!\u001b9\t\u0003\u0019Y\tC\u0004\u0004ja\"\u0019a!%\t\u000f\r%\u0004\bb\u0001\u0004\u0018\"91\u0011\u000e\u001d\u0005\u0004\rm\u0005bBB5q\u0011\r1q\u0014\u0005\b\u0007SBD1ABR\u0011\u001d\u0019I\u0007\u000fC\u0002\u0007OCqa!\u001b9\t\u0003\u0019Y\u000bC\u0004\u0004ja\"\u0019a!1\t\u000f\r%\u0004\bb\u0001\u0004F\u001a11q\u001a\u001d\u0007\u0007#D!ba\u0019Y\u0005\u000b\u0007I\u0011AA\\\u0011)\u00199\u000e\u0017B\u0001B\u0003%\u0011\u0011\u0018\u0005\u000b\u0007OB&Q1A\u0005\u0002\u0005]\u0006BCBm1\n\u0005\t\u0015!\u0003\u0002:\"9\u0011q\u0013-\u0005\u0002\rm\u0007bBAN1\u0012\u0005\u0011Q\u0014\u0005\b\u0003KCF\u0011AAO\u0011\u001d\t)\f\u0017C\u0001\u0003oCq!a*Y\t\u0003\tI\u000bC\u0004\u00024b#\t!!+\t\u000f\u0005\r\u0007\f\"\u0001\u00028\"9\u0011q\u0018-\u0005\u0002\u0005%\u0006bBAa1\u0012\u0005\u0011\u0011\u0016\u0005\b\u0003sDF\u0011AA{\u0011\u001d\tY\u0010\u0017C!\u0003\u001bDq!a2Y\t\u0003\nI\u000bC\u0004\u0002~b#\t%!+\t\u000f\u0005}\b\f\"\u0011\u0002*\"91Q\u001d-\u0005B\u0005%\u0006bBBt1\u0012\u0005\u0013\u0011\u0016\u0005\b\u0007SDF\u0011IAU\u0011\u001d\u0019Y\u000f\u0017C!\u0003SCq!!2Y\t\u0003\nI\u000bC\u0004\u0003\u0004b#\t%!(\t\u000f\r5\b\f\"\u0011\u0004p\"9!\u0011\u0001-\u0005B\u0005U\bb\u0002B\u00021\u0012\u00051\u0011\u001f\u0005\b\u0005\u0017AF\u0011AB{\u0011\u001d\u0011\t\u0002\u0017C\u0001\u0007sDqAa\u0006Y\t\u0003\u0019i\u0010C\u0004\u0003$b#\t!!>\t\u000f\t\u0015\u0006\f\"\u0001\u0002v\"9!q\u0015-\u0005\u0002\u0005U\bb\u0002BY1\u0012\u0005A\u0011\u0001\u0005\b\u0005+DF\u0011AAg\u0011\u001d!)\u0001\u0017C\u0001\t\u000fAq!!3Y\t\u0003\n9\fC\u0004\u0003vb#\t\u0005b\u0003\t\u000f\u0011=\u0001\f\"\u0011\u0005\u0012!9A1\u0003-\u0005B\u0011U\u0001b\u0002C\u0012q\u0011%AQ\u0005\u0004\u0007\u0007\u000bAd\u0001b\u000e\t\u0017\r\r\u0014Q\u0001BC\u0002\u0013\u0005\u0011Q\u0014\u0005\f\u0007/\f)A!A!\u0002\u0013\ty\nC\u0006\u0004h\u0005\u0015!Q1A\u0005\u0002\u0005u\u0005bCBm\u0003\u000b\u0011\t\u0011)A\u0005\u0003?C\u0001\"a&\u0002\u0006\u0011\u0005A\u0011\b\u0005\t\u00037\u000b)\u0001\"\u0001\u0002\u001e\"A\u0011QUA\u0003\t\u0003\ti\n\u0003\u0005\u00026\u0006\u0015A\u0011AA\\\u0011!\t9+!\u0002\u0005\u0002\u0005%\u0006\u0002CAZ\u0003\u000b!\t!!+\t\u0011\u0005\r\u0017Q\u0001C\u0001\u0003oC\u0001\"a0\u0002\u0006\u0011\u0005\u0011\u0011\u0016\u0005\t\u0003\u0003\f)\u0001\"\u0001\u0002*\"A\u0011\u0011`A\u0003\t\u0003\t)\u0010\u0003\u0005\u0002|\u0006\u0015A\u0011IAg\u0011!\t9-!\u0002\u0005B\u0005%\u0006\u0002CA\u007f\u0003\u000b!\t%!+\t\u0011\u0005}\u0018Q\u0001C!\u0003SC\u0001b!:\u0002\u0006\u0011\u0005\u0013\u0011\u0016\u0005\t\u0007O\f)\u0001\"\u0011\u0002*\"A1\u0011^A\u0003\t\u0003\nI\u000b\u0003\u0005\u0004l\u0006\u0015A\u0011IAU\u0011!\t)-!\u0002\u0005B\u0005%\u0006\u0002\u0003BB\u0003\u000b!\t%!(\t\u0011\r5\u0018Q\u0001C!\u0007_D\u0001B!\u0001\u0002\u0006\u0011\u0005\u0013Q\u001f\u0005\t\u0005\u0007\t)\u0001\"\u0001\u0005@!A!1BA\u0003\t\u0003!\u0019\u0005\u0003\u0005\u0003\u0012\u0005\u0015A\u0011\u0001C$\u0011!\u00119\"!\u0002\u0005\u0002\u0011-\u0003\u0002\u0003BR\u0003\u000b!\t!!>\t\u0011\t\u0015\u0016Q\u0001C\u0001\u0003kD\u0001Ba*\u0002\u0006\u0011\u0005\u0011Q\u001f\u0005\t\u0005c\u000b)\u0001\"\u0001\u0005P!A!Q[A\u0003\t\u0003\ti\r\u0003\u0005\u0005\u0006\u0005\u0015A\u0011\u0001C*\u0011!\u0011)0!\u0002\u0005B\u0011]\u0003\u0002\u0003C\b\u0003\u000b!\t\u0005\"\u0005\t\u0011\u0011M\u0011Q\u0001C!\t+Aq\u0001b\u000b9\t\u0013!i\u0003C\u0005\u00056a\n\t\u0011\"\u0003\u0002b\nA!+\u0019;j_:\fGN\u0003\u0003\u0002^\u0005}\u0013\u0001B7bi\"T!!!\u0019\u0002\u000bM\u0004\u0018N]3\u0004\u0001M9\u0001!a\u001a\u0002v\u0005m\u0004\u0003BA5\u0003cj!!a\u001b\u000b\t\u0005u\u0013Q\u000e\u0006\u0003\u0003_\nQa]2bY\u0006LA!a\u001d\u0002l\tY1kY1mC:+XNY3s!\u0011\tI'a\u001e\n\t\u0005e\u00141\u000e\u0002\u0018'\u000e\fG.\u0019(v[\u0016\u0014\u0018nY\"p]Z,'o]5p]N\u0004b!! \u0002\u000e\u0006Me\u0002BA@\u0003\u0013sA!!!\u0002\b6\u0011\u00111\u0011\u0006\u0005\u0003\u000b\u000b\u0019'\u0001\u0004=e>|GOP\u0005\u0003\u0003_JA!a#\u0002n\u00059\u0001/Y2lC\u001e,\u0017\u0002BAH\u0003#\u0013qa\u0014:eKJ,GM\u0003\u0003\u0002\f\u00065\u0004cAAK\u00015\u0011\u00111L\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005M\u0015!\u00038v[\u0016\u0014\u0018\r^8s+\t\ty\n\u0005\u0003\u0002\u0016\u0006\u0005\u0016\u0002BAR\u00037\u0012\u0001bU1gK2{gnZ\u0001\fI\u0016tw.\\5oCR|'/\u0001\u000bok6,'/\u0019;pe&\u001bh+\u00197jI2{gnZ\u000b\u0003\u0003W\u0003B!!,\u000206\u0011\u0011QN\u0005\u0005\u0003c\u000biGA\u0004C_>dW-\u00198\u0002/9,X.\u001a:bi>\u0014\u0018IY:JgZ\u000bG.\u001b3M_:<\u0017a\u00048v[\u0016\u0014\u0018\r^8s\u0003NduN\\4\u0016\u0005\u0005e\u0006\u0003BAW\u0003wKA!!0\u0002n\t!Aj\u001c8h\u0003Y!WM\\8nS:\fGo\u001c:JgZ\u000bG.\u001b3M_:<\u0017!\u00073f]>l\u0017N\\1u_J\f%m]%t-\u0006d\u0017\u000e\u001a'p]\u001e\f\u0011\u0003Z3o_6Lg.\u0019;pe\u0006\u001bHj\u001c8h\u0003-I7OV1mS\u0012duN\\4\u0002\u000f%\u001cx\u000b[8mK\u0006IAn\u001c8h-\u0006dW/Z\u0001\tS:$h+\u00197vKV\u0011\u0011q\u001a\t\u0005\u0003[\u000b\t.\u0003\u0003\u0002T\u00065$aA%oi\u0006Qa\r\\8biZ\u000bG.^3\u0016\u0005\u0005e\u0007\u0003BAW\u00037LA!!8\u0002n\t)a\t\\8bi\u0006QQO\u001c3fe2L\u0018N\\4\u0015\u0005\u0005\r\b\u0003BAs\u0003_l!!a:\u000b\t\u0005%\u00181^\u0001\u0005Y\u0006twM\u0003\u0002\u0002n\u0006!!.\u0019<b\u0013\u0011\t\t0a:\u0003\r=\u0013'.Z2u\u0003\r\t'm]\u000b\u0003\u0003'\u000bq!\u001b8wKJ\u001cX-\u0001\u0006sK\u000eL\u0007O]8dC2\faa]5h]Vl\u0017AB5t5\u0016\u0014x.A\u0003jg>sW-\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/A\u0003%a2,8\u000f\u0006\u0003\u0002\u0014\n\u001d\u0001b\u0002B\u0005/\u0001\u0007\u00111S\u0001\u0004e\"\u001c\u0018A\u0002\u0013nS:,8\u000f\u0006\u0003\u0002\u0014\n=\u0001b\u0002B\u00051\u0001\u0007\u00111S\u0001\u0007IQLW.Z:\u0015\t\u0005M%Q\u0003\u0005\b\u0005\u0013I\u0002\u0019AAJ\u0003\u0011!C-\u001b<\u0015\t\u0005M%1\u0004\u0005\b\u0005\u0013Q\u0002\u0019AAJ\u0003!!\u0003/\u001a:dK:$H\u0003BAJ\u0005CAqA!\u0003\u001c\u0001\u0004\t\u0019*A\u0003ucV|G\u000f\u0006\u0003\u0002\u0014\n\u001d\u0002b\u0002B\u00059\u0001\u0007\u00111S\u0001\u0005i6|G\r\u0006\u0003\u0002\u0014\n5\u0002b\u0002B\u0005;\u0001\u0007\u00111S\u0001\tiF,x\u000e^7pIR!!1\u0007B\u001d!!\tiK!\u000e\u0002\u0014\u0006M\u0015\u0002\u0002B\u001c\u0003[\u0012a\u0001V;qY\u0016\u0014\u0004b\u0002B\u0005=\u0001\u0007\u00111S\u0001\u0004Y\u000elG\u0003BAJ\u0005\u007fAqA!\u0003 \u0001\u0004\t\u0019*A\u0002hG\u0012$B!a%\u0003F!9!\u0011\u0002\u0011A\u0002\u0005M\u0015A\u0002;p%\u0016\fG.\u0006\u0002\u0003LA!\u0011Q\u0013B'\u0013\u0011\u0011y%a\u0017\u0003\tI+\u0017\r\\\u0001\fi>\fEnZ3ce\u0006L7-\u0006\u0002\u0003VA!\u0011Q\u0013B,\u0013\u0011\u0011I&a\u0017\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0017\u0001\u0004;p\u0005&<G)Z2j[\u0006dGC\u0002B0\u0005K\u0012I\u0007\u0005\u0003\u0002~\t\u0005\u0014\u0002\u0002B2\u0003#\u0013!BQ5h\t\u0016\u001c\u0017.\\1m\u0011\u001d\u00119g\ta\u0001\u0003\u001f\fQa]2bY\u0016DqAa\u001b$\u0001\u0004\u0011i'\u0001\u0003n_\u0012,\u0007\u0003\u0002B8\u0005gj!A!\u001d\u000b\t\u0005u\u00131^\u0005\u0005\u0005k\u0012\tH\u0001\u0007S_VtG-\u001b8h\u001b>$W\r\u0006\u0003\u0003`\te\u0004b\u0002B>I\u0001\u0007!QP\u0001\u0003[\u000e\u0004BAa\u001c\u0003\u0000%!!\u0011\u0011B9\u0005-i\u0015\r\u001e5D_:$X\r\u001f;\u0002\u0015Q|7+\u00194f\u0019>tw-\u0001\u0005u_\nKw-\u00138u+\t\u0011I\t\u0005\u0003\u0002~\t-\u0015\u0002\u0002BG\u0003#\u0013aAQ5h\u0013:$\u0018AC:i_J$h+\u00197vKV\u0011!1\u0013\t\u0005\u0003[\u0013)*\u0003\u0003\u0003\u0018\u00065$!B*i_J$\u0018!\u00032zi\u00164\u0016\r\\;f+\t\u0011i\n\u0005\u0003\u0002.\n}\u0015\u0002\u0002BQ\u0003[\u0012AAQ=uK\u0006)a\r\\8pe\u0006!1-Z5m\u0003\u0015\u0011x.\u001e8e\u0003\u001d\u0011x.\u001e8e)>$B!a%\u0003.\"9!q\u0016\u0017A\u0002\u0005}\u0015!\u00023f]>l\u0017a\u00019poR!\u00111\u0013B[\u0011\u001d\u00119,\fa\u0001\u0003\u001f\f1!\u001a=q\u0003\u0011\u0019\u0018n\u001a8\u0016\u0005\tu\u0006\u0003\u0002B`\u0005\u001ftAA!1\u0003L:!!1\u0019Bd\u001d\u0011\t\tI!2\n\u0005\u0005\u0005\u0014\u0002\u0002Be\u0003?\nq!\u00197hK\n\u0014\u0018-\u0003\u0003\u0002\f\n5'\u0002\u0002Be\u0003?JAA!5\u0003T\n!1+[4o\u0015\u0011\tYI!4\u0002\u0019\r|W\u000e]1sKR{wJ\\3\u0002\u00075Lg\u000e\u0006\u0003\u0002\u0014\nm\u0007b\u0002B\u0005a\u0001\u0007\u00111S\u0001\u0004[\u0006DH\u0003BAJ\u0005CDqA!\u00032\u0001\u0004\t\u0019*\u0001\u0006mS6LG\u000fV8J]R\f1\u0002\\5nSR$v\u000eT8oO\u00069A.[7jiR{G\u0003BAJ\u0005WDqA!85\u0001\u0004\ty*\u0001\nmS6LG\u000fR3o_6Lg.\u0019;peR{G\u0003BAJ\u0005cDqAa=6\u0001\u0004\ty*A\u0003mS6LG/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003W\u0013I\u0010C\u0004\u0003|Z\u0002\rA!@\u0002\tQD\u0017\r\u001e\t\u0005\u0003[\u0013y0\u0003\u0003\u0004\u0002\u00055$aA!os&\"\u0001!!\u0002Y\u0005-\u0011\u0015n\u001a*bi&|g.\u00197\u0014\u000fa\u001aIaa\u0004\u0004\u0016A!\u0011QVB\u0006\u0013\u0011\u0019i!!\u001c\u0003\r\u0005s\u0017PU3g!\u0011\t)j!\u0005\n\t\rM\u00111\f\u0002\u0012%\u0006$\u0018n\u001c8bY&s7\u000f^1oG\u0016\u001c\b\u0003BB\f\u0007;i!a!\u0007\u000b\t\rm\u00111^\u0001\u0003S>LAaa\b\u0004\u001a\ta1+\u001a:jC2L'0\u00192mKR\u001111\u0005\t\u0004\u0003+C\u0014A\u0004*bi&|g.\u00197TiJLgnZ\u000b\u0003\u0007S\u0001Baa\u000b\u000465\u00111Q\u0006\u0006\u0005\u0007_\u0019\t$\u0001\u0005nCR\u001c\u0007.\u001b8h\u0015\u0011\u0019\u0019$!\u001c\u0002\tU$\u0018\u000e\\\u0005\u0005\u0007o\u0019iCA\u0003SK\u001e,\u00070A\bSCRLwN\\1m'R\u0014\u0018N\\4!\u00035Ie\u000e^3hKJ\u001cFO]5oO\u0006q\u0011J\u001c;fO\u0016\u00148\u000b\u001e:j]\u001e\u0004\u0013\u0001\u0002>fe>\fQA_3s_\u0002\n1a\u001c8f\u0003\u0011yg.\u001a\u0011\u0002\u000fQ;xnM\u0019nc\u0005AAk^84c5\f\u0004%A\u0004Uo>\u001c\u0014'\u001c\u0019\u0002\u0011Q;xnM\u0019na\u0001\nq\u0001V<pmMj\u0017'\u0001\u0005Uo>44'\\\u0019!\u0003\u001d!vo\u001c\u001c4[B\n\u0001\u0002V<pmMj\u0007\u0007I\u0001\ti>$u.\u001e2mKR111LB1\u0007K\u0002B!!,\u0004^%!1qLA7\u0005\u0019!u.\u001e2mK\"911\r&A\u0002\u0005}\u0015!\u00018\t\u000f\r\u001d$\n1\u0001\u0002 \u0006\tA-A\u0003baBd\u0017\u0010\u0006\u0004\u0002\u0014\u000e54q\u000e\u0005\b\u0007GZ\u0005\u0019\u0001BE\u0011\u001d\u00199g\u0013a\u0001\u0005\u0013#b!a%\u0004t\rU\u0004bBB2\u0019\u0002\u0007\u0011\u0011\u0018\u0005\b\u0007Ob\u0005\u0019AA]\u00031\u0011W/\u001b7e/&$\b\u000eR5w))\t\u0019ja\u001f\u0004\u0000\r\r5q\u0011\u0005\b\u0007{j\u0005\u0019AA]\u0003\rqW/\u001c\u0005\b\u0007\u0003k\u0005\u0019AA]\u0003\u0011qwm\u00193\t\u000f\r\u0015U\n1\u0001\u0002:\u0006\u0011!\u000f\u001a\u0005\b\u0007\u0013k\u0005\u0019AA]\u0003\u0011aG-\u001a8\u0015\r\u0005M5QRBH\u0011\u001d\u0019\u0019G\u0014a\u0001\u0003?Cqaa\u001aO\u0001\u0004\ty\n\u0006\u0003\u0002\u0014\u000eM\u0005bBBK\u001f\u0002\u0007\u0011qZ\u0001\u0002qR!\u00111SBM\u0011\u001d\u0019)\n\u0015a\u0001\u0003s#B!a%\u0004\u001e\"91QS)A\u0002\t%E\u0003BAJ\u0007CCqa!&S\u0001\u0004\tI\u000e\u0006\u0003\u0002\u0014\u000e\u0015\u0006bBBK'\u0002\u000711\f\u000b\u0005\u0003'\u001bI\u000bC\u0004\u0004\u0016R\u0003\rAa\u0018\u0015\t\u0005M5Q\u0016\u0005\b\u0007_+\u0006\u0019ABY\u0003\u0005\u0011\b\u0003BBZ\u0007wsAa!.\u00048B!\u0011\u0011QA7\u0013\u0011\u0019I,!\u001c\u0002\rA\u0013X\rZ3g\u0013\u0011\u0019ila0\u0003\rM#(/\u001b8h\u0015\u0011\u0019I,!\u001c\u0015\t\u0005M51\u0019\u0005\b\u0007G2\u0006\u0019AAP)\u0011\t\u0019ja2\t\u000f\rUu\u000b1\u0001\u0004JB!\u0011QSBf\u0013\u0011\u0019i-a\u0017\u0003\r9+XNY3s\u00051auN\\4SCRLwN\\1m'\u0015A\u00161SBj!\u0011\tih!6\n\t\r}\u0011\u0011S\u0001\u0003]\u0002\n!\u0001\u001a\u0011\u0015\r\ru7\u0011]Br!\r\u0019y\u000eW\u0007\u0002q!911M/A\u0002\u0005e\u0006bBB4;\u0002\u0007\u0011\u0011X\u0001\fSN4\u0016\r\\5e\u0007\"\f'/A\u0006jgZ\u000bG.\u001b3CsR,\u0017\u0001D5t-\u0006d\u0017\u000eZ*i_J$\u0018AC5t-\u0006d\u0017\u000eZ%oi\u0006YAm\\;cY\u00164\u0016\r\\;f+\t\u0019Y\u0006\u0006\u0003\u0002\u0014\u000eM\bbBBXg\u0002\u0007\u00111\u0013\u000b\u0005\u0003'\u001b9\u0010C\u0004\u00040R\u0004\r!a%\u0015\t\u0005M51 \u0005\b\u0007_+\b\u0019AAJ)\u0011\t\u0019ja@\t\u000f\r=f\u000f1\u0001\u0002\u0014R!\u00111\u0013C\u0002\u0011\u001d\u00119L\u001fa\u0001\u0003\u001f\fqaY8na\u0006\u0014X\r\u0006\u0003\u0002P\u0012%\u0001bBBXy\u0002\u0007\u00111\u0013\u000b\u0005\u0003W#i\u0001C\u0004\u0003|z\u0004\rA!@\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a4\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"a!-)\u000fa#I\u0002b\b\u0005\"A!\u0011Q\u0016C\u000e\u0013\u0011!i\"!\u001c\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g$\u0001\u0001\u0002\u00191|gn\u001a*bi&|g.\u00197\u0015\r\ruGq\u0005C\u0015\u0011!\u0019\u0019'a\u0001A\u0002\u0005e\u0006\u0002CB4\u0003\u0007\u0001\r!!/\u0002\u0017\tLwMU1uS>t\u0017\r\u001c\u000b\u0007\t_!\t\u0004b\r\u0011\t\r}\u0017Q\u0001\u0005\t\u0007G\n)\u00061\u0001\u0002 \"A1qMA+\u0001\u0004\ty*\u0001\u0007xe&$XMU3qY\u0006\u001cWm\u0005\u0004\u0002\u0006\u0005M51\u001b\u000b\u0007\t_!Y\u0004\"\u0010\t\u0011\r\r\u0014q\u0002a\u0001\u0003?C\u0001ba\u001a\u0002\u0010\u0001\u0007\u0011q\u0014\u000b\u0005\u0003'#\t\u0005\u0003\u0005\u00040\u0006m\u0002\u0019AAJ)\u0011\t\u0019\n\"\u0012\t\u0011\r=\u0016Q\ba\u0001\u0003'#B!a%\u0005J!A1qVA \u0001\u0004\t\u0019\n\u0006\u0003\u0002\u0014\u00125\u0003\u0002CBX\u0003\u0003\u0002\r!a%\u0015\t\u0005ME\u0011\u000b\u0005\t\u0005o\u000bI\u00051\u0001\u0002PR!\u0011q\u001aC+\u0011!\u0019y+!\u0014A\u0002\u0005ME\u0003BAV\t3B\u0001Ba?\u0002P\u0001\u0007!Q \u0015\t\u0003\u000b!I\u0002b\b\u0005\"\u0005A!+\u0019;j_:\fG\u000e"
)
public abstract class Rational extends ScalaNumber implements ScalaNumericConversions, Ordered {
   public static Rational apply(final Number x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final SafeLong n) {
      return Rational$.MODULE$.apply(n);
   }

   public static Rational apply(final String r) {
      return Rational$.MODULE$.apply(r);
   }

   public static Rational apply(final BigDecimal x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final double x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final float x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final BigInt x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final long x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final int x) {
      return Rational$.MODULE$.apply(x);
   }

   public static Rational apply(final SafeLong n, final SafeLong d) {
      return Rational$.MODULE$.apply(n, d);
   }

   public static Rational apply(final long n, final long d) {
      return Rational$.MODULE$.apply(n, d);
   }

   public static Rational apply(final BigInt n, final BigInt d) {
      return Rational$.MODULE$.apply(n, d);
   }

   public static Rational one() {
      return Rational$.MODULE$.one();
   }

   public static Rational zero() {
      return Rational$.MODULE$.zero();
   }

   public static NumberTag RationalTag() {
      return Rational$.MODULE$.RationalTag();
   }

   public static Field RationalAlgebra() {
      return Rational$.MODULE$.RationalAlgebra();
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

   public abstract SafeLong numerator();

   public abstract SafeLong denominator();

   public abstract boolean numeratorIsValidLong();

   public abstract boolean numeratorAbsIsValidLong();

   public abstract long numeratorAsLong();

   public abstract boolean denominatorIsValidLong();

   public abstract boolean denominatorAbsIsValidLong();

   public abstract long denominatorAsLong();

   public abstract boolean isValidLong();

   public abstract boolean isWhole();

   public long longValue() {
      return this.toBigInt().longValue();
   }

   public int intValue() {
      return (int)this.longValue();
   }

   public float floatValue() {
      return (float)this.doubleValue();
   }

   public Object underlying() {
      return this;
   }

   public Rational abs() {
      return this.signum() < 0 ? this.unary_$minus() : this;
   }

   public Rational inverse() {
      return this.reciprocal();
   }

   public abstract Rational reciprocal();

   public abstract int signum();

   public abstract boolean isZero();

   public abstract boolean isOne();

   public abstract Rational unary_$minus();

   public abstract Rational $plus(final Rational rhs);

   public abstract Rational $minus(final Rational rhs);

   public abstract Rational $times(final Rational rhs);

   public abstract Rational $div(final Rational rhs);

   public Rational $percent(final Rational rhs) {
      return Rational$.MODULE$.zero();
   }

   public Rational tquot(final Rational rhs) {
      return Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(this.$div(rhs).toBigInt()), SafeLong$.MODULE$.one());
   }

   public Rational tmod(final Rational rhs) {
      return this.$minus(this.tquot(rhs).$times(rhs));
   }

   public Tuple2 tquotmod(final Rational rhs) {
      Rational q = this.tquot(rhs);
      return new Tuple2(q, this.$minus(q.$times(rhs)));
   }

   public Rational lcm(final Rational rhs) {
      return !this.isZero() && !rhs.isZero() ? this.$div(this.gcd(rhs)).$times(rhs) : Rational$.MODULE$.zero();
   }

   public Rational gcd(final Rational rhs) {
      Rational var10000;
      if (this.isZero()) {
         var10000 = rhs.abs();
      } else if (rhs.isZero()) {
         var10000 = this.abs();
      } else if (this.isOne() && rhs.isOne()) {
         var10000 = this;
      } else {
         LongRef newNumAsLong = LongRef.create(0L);
         ObjectRef newNumAsSafeLong = ObjectRef.create((SafeLong).MODULE$.empty());
         if (this.numeratorAbsIsValidLong() && rhs.numeratorAbsIsValidLong()) {
            newNumAsLong.elem = package$.MODULE$.gcd(this.numeratorAsLong(), rhs.numeratorAsLong());
         } else {
            SafeLong newNum = this.numerator().gcd(rhs.numerator());
            if (newNum.isValidLong()) {
               newNumAsLong.elem = newNum.toLong();
            } else {
               newNumAsSafeLong.elem = (SafeLong).MODULE$.apply(newNum);
            }
         }

         if (this.denominatorAbsIsValidLong() && rhs.denominatorAbsIsValidLong()) {
            long ld = this.denominatorAsLong();
            long rd = rhs.denominatorAsLong();
            long dengcd = package$.MODULE$.gcd(ld, rd);
            long tmp = ld / dengcd;
            var10000 = checked$attempt$macro$1$1(tmp, rd, newNumAsSafeLong, newNumAsLong);
         } else {
            SafeLong newDenAsSafeLong = this.denominator().lcm(rhs.denominator());
            SafeLong var15 = (SafeLong)newNumAsSafeLong.elem;
            SafeLong var16 = (SafeLong).MODULE$.unapply(var15);
            Rational var2;
            if (!.MODULE$.isEmpty$extension(var16)) {
               SafeLong sl = (SafeLong).MODULE$.get$extension(var16);
               var2 = Rational$.MODULE$.apply(sl, newDenAsSafeLong);
            } else {
               var2 = Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(newNumAsLong.elem), newDenAsSafeLong);
            }

            var10000 = var2;
         }
      }

      return var10000;
   }

   public Real toReal() {
      return Real$.MODULE$.apply(this);
   }

   public Algebraic toAlgebraic() {
      return Algebraic$.MODULE$.apply(this);
   }

   public BigDecimal toBigDecimal(final int scale, final RoundingMode mode) {
      java.math.BigDecimal n = new java.math.BigDecimal(this.numerator().toBigInteger());
      java.math.BigDecimal d = new java.math.BigDecimal(this.denominator().toBigInteger());
      return scala.package..MODULE$.BigDecimal().apply(n.divide(d, scale, mode));
   }

   public BigDecimal toBigDecimal(final MathContext mc) {
      java.math.BigDecimal n = new java.math.BigDecimal(this.numerator().toBigInteger());
      java.math.BigDecimal d = new java.math.BigDecimal(this.denominator().toBigInteger());
      return scala.package..MODULE$.BigDecimal().apply(n.divide(d, mc));
   }

   public abstract SafeLong toSafeLong();

   public final BigInt toBigInt() {
      return this.toSafeLong().toBigInt();
   }

   public short shortValue() {
      return (short)((int)this.longValue());
   }

   public byte byteValue() {
      return (byte)((int)this.longValue());
   }

   public abstract Rational floor();

   public abstract Rational ceil();

   public abstract Rational round();

   public Rational roundTo(final SafeLong denom) {
      return this.$times(Rational$.MODULE$.apply(denom)).round().$div(Rational$.MODULE$.apply(denom));
   }

   public abstract Rational pow(final int exp);

   public Signed.Sign sign() {
      return spire.algebra.package$.MODULE$.Sign().apply(this.signum());
   }

   public abstract int compareToOne();

   public Rational min(final Rational rhs) {
      return this.compare(rhs) < 0 ? this : rhs;
   }

   public Rational max(final Rational rhs) {
      return this.compare(rhs) > 0 ? this : rhs;
   }

   public Rational limitToInt() {
      return this.signum() < 0 ? this.unary_$minus().limitTo(SafeLong$.MODULE$.apply(Rational$.MODULE$.Two31m0())).unary_$minus() : this.limitTo(SafeLong$.MODULE$.apply(Rational$.MODULE$.Two31m1()));
   }

   public Rational limitToLong() {
      return this.signum() < 0 ? this.unary_$minus().limitTo(SafeLong$.MODULE$.apply(Rational$.MODULE$.Two63m0())).unary_$minus() : this.limitTo(SafeLong$.MODULE$.apply(Rational$.MODULE$.Two63m1()));
   }

   public Rational limitTo(final SafeLong max) {
      Rational var10000;
      if (this.signum() < 0) {
         var10000 = this.unary_$minus().limitTo(max).unary_$minus();
      } else {
         scala.Predef..MODULE$.require(max.signum() > 0, () -> "Limit must be a positive integer.");
         SafeLong floor = SafeLong$.MODULE$.apply(this.toBigInt());
         if (floor.$greater$eq(max)) {
            var10000 = Rational$.MODULE$.apply(max);
         } else if (floor.$greater$eq(max.$greater$greater(1))) {
            var10000 = Rational$.MODULE$.apply(floor.toLong());
         } else if (this.compareToOne() < 0) {
            var10000 = this.limitDenominatorTo(max);
         } else {
            BigInt floor = this.toBigInt();
            var10000 = SafeLong$.MODULE$.apply(floor).$greater$eq(max) ? Rational$.MODULE$.apply(max) : (SafeLong$.MODULE$.apply(floor).$greater$eq(max.$greater$greater(1)) ? Rational$.MODULE$.apply(floor.toLong()) : (this.compareToOne() < 0 ? this.limitDenominatorTo(max) : this.limitDenominatorTo(max.$times(this.denominator()).$div(this.numerator()))));
         }
      }

      return var10000;
   }

   public Rational limitDenominatorTo(final SafeLong limit) {
      scala.Predef..MODULE$.require(limit.signum() > 0, () -> "Cannot limit denominator to non-positive number.");
      Signed.Sign var3 = this.sign();
      Rational var2;
      if (algebra.ring.Signed.Zero..MODULE$.equals(var3)) {
         var2 = this;
      } else if (algebra.ring.Signed.Positive..MODULE$.equals(var3)) {
         var2 = this.closest$1(Rational$.MODULE$.apply(this.toBigInt()), new LongRational(1L, 0L), (SafeLong).MODULE$.empty(), (SafeLong).MODULE$.empty(), limit);
      } else {
         if (!algebra.ring.Signed.Negative..MODULE$.equals(var3)) {
            throw new MatchError(var3);
         }

         var2 = this.closest$1(new LongRational(-1L, 0L), Rational$.MODULE$.apply(this.toBigInt()), (SafeLong).MODULE$.empty(), (SafeLong).MODULE$.empty(), limit);
      }

      return var2;
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Real) {
         Real var4 = (Real)that;
         var2 = BoxesRunTime.equalsNumNum(this, var4.toRational());
      } else if (that instanceof Algebraic) {
         Algebraic var5 = (Algebraic)that;
         var2 = BoxesRunTime.equalsNumNum(var5, this);
      } else if (that instanceof BigInt) {
         boolean var20;
         label135: {
            label134: {
               BigInt var6 = (BigInt)that;
               if (this.isWhole()) {
                  BigInt var10000 = this.toBigInt();
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label134;
                     }
                  } else if (var10000.equals(var6)) {
                     break label134;
                  }
               }

               var20 = false;
               break label135;
            }

            var20 = true;
         }

         var2 = var20;
      } else if (that instanceof BigDecimal) {
         BigDecimal var8 = (BigDecimal)that;

         boolean var21;
         try {
            label123: {
               label122: {
                  BigDecimal var22 = this.toBigDecimal(var8.mc());
                  if (var22 == null) {
                     if (var8 == null) {
                        break label122;
                     }
                  } else if (var22.equals(var8)) {
                     break label122;
                  }

                  var21 = false;
                  break label123;
               }

               var21 = true;
            }
         } catch (ArithmeticException var19) {
            var21 = false;
         }

         var2 = var21;
      } else if (that instanceof SafeLong) {
         SafeLong var11 = (SafeLong)that;
         var2 = BoxesRunTime.equalsNumNum(SafeLong$.MODULE$.apply(this.toBigInt()), var11);
      } else if (that instanceof Number) {
         boolean var24;
         label113: {
            label112: {
               Number var12 = (Number)that;
               Number var23 = Number$.MODULE$.apply(this);
               if (var23 == null) {
                  if (var12 == null) {
                     break label112;
                  }
               } else if (var23.equals(var12)) {
                  break label112;
               }

               var24 = false;
               break label113;
            }

            var24 = true;
         }

         var2 = var24;
      } else if (that instanceof Natural) {
         Natural var14 = (Natural)that;
         var2 = this.isWhole() && BoxesRunTime.equalsNumNum(this, Rational$.MODULE$.apply(var14.toBigInt()));
      } else if (that instanceof Complex) {
         Complex var15 = (Complex)that;
         var2 = BoxesRunTime.equalsNumNum(var15, this);
      } else if (that instanceof Quaternion) {
         Quaternion var16 = (Quaternion)that;
         var2 = BoxesRunTime.equalsNumNum(var16, this);
      } else if (that instanceof Long) {
         long var17 = BoxesRunTime.unboxToLong(that);
         var2 = this.isValidLong() && this.toLong() == var17;
      } else {
         var2 = this.unifiedPrimitiveEquals(that);
      }

      return var2;
   }

   private static final Rational checked$fallback$macro$2$1(final long tmp$1, final long rd$1, final ObjectRef newNumAsSafeLong$1, final LongRef newNumAsLong$1) {
      SafeLong newDenAsSafeLong = SafeLong$.MODULE$.apply(tmp$1).$times(rd$1);
      return .MODULE$.isEmpty$extension((SafeLong)newNumAsSafeLong$1.elem) ? Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(newNumAsLong$1.elem), newDenAsSafeLong) : Rational$.MODULE$.apply((SafeLong).MODULE$.get$extension((SafeLong)newNumAsSafeLong$1.elem), newDenAsSafeLong);
   }

   private static final Rational checked$attempt$macro$1$1(final long tmp$1, final long rd$1, final ObjectRef newNumAsSafeLong$1, final LongRef newNumAsLong$1) {
      long z$macro$3 = tmp$1 * rd$1;
      if (tmp$1 == 0L || rd$1 == z$macro$3 / tmp$1 && (tmp$1 != -1L || rd$1 != Long.MIN_VALUE)) {
         return .MODULE$.isEmpty$extension((SafeLong)newNumAsSafeLong$1.elem) ? Rational$.MODULE$.apply(newNumAsLong$1.elem, z$macro$3) : Rational$.MODULE$.apply((SafeLong).MODULE$.get$extension((SafeLong)newNumAsSafeLong$1.elem), SafeLong$.MODULE$.apply(z$macro$3));
      } else {
         return checked$fallback$macro$2$1(tmp$1, rd$1, newNumAsSafeLong$1, newNumAsLong$1);
      }
   }

   private static final SafeLong nextK$1(final SafeLong curr) {
      return .MODULE$.isEmpty$extension(curr) ? (SafeLong).MODULE$.apply(SafeLong$.MODULE$.apply(2)) : (SafeLong).MODULE$.apply(((SafeLong).MODULE$.get$extension(curr)).$times(2L));
   }

   private final Rational closest$1(final Rational l, final Rational u, final SafeLong lk, final SafeLong rk, final SafeLong limit$1) {
      while(true) {
         Rational mediant;
         label58: {
            Tuple2.mcZZ.sp var9 = new Tuple2.mcZZ.sp(.MODULE$.nonEmpty$extension(lk), .MODULE$.nonEmpty$extension(rk));
            if (var9 != null) {
               boolean var10 = ((Tuple2)var9)._1$mcZ$sp();
               boolean var11 = ((Tuple2)var9)._2$mcZ$sp();
               if (var10 && !var11) {
                  mediant = Rational$.MODULE$.apply(((SafeLong).MODULE$.get$extension(lk)).$times(l.numerator()).$plus(u.numerator()), ((SafeLong).MODULE$.get$extension(lk)).$times(l.denominator()).$plus(u.denominator()));
                  break label58;
               }
            }

            if (var9 != null) {
               boolean var12 = ((Tuple2)var9)._1$mcZ$sp();
               boolean var13 = ((Tuple2)var9)._2$mcZ$sp();
               if (!var12 && var13) {
                  mediant = Rational$.MODULE$.apply(l.numerator().$plus(((SafeLong).MODULE$.get$extension(rk)).$times(u.numerator())), l.denominator().$plus(((SafeLong).MODULE$.get$extension(rk)).$times(u.denominator())));
                  break label58;
               }
            }

            mediant = Rational$.MODULE$.apply(l.numerator().$plus(u.numerator()), l.denominator().$plus(u.denominator()));
         }

         Rational var10000;
         if (mediant.denominator().$greater(limit$1)) {
            if (.MODULE$.nonEmpty$extension(lk) || .MODULE$.nonEmpty$extension(rk)) {
               lk = rk = (SafeLong).MODULE$.empty();
               u = u;
               l = l;
               continue;
            }

            var10000 = this.$minus(l).abs().$greater(u.$minus(this).abs()) ? u : l;
         } else {
            if (!BoxesRunTime.equalsNumNum(mediant, this)) {
               if (mediant.$less(this)) {
                  SafeLong var14 = (SafeLong).MODULE$.empty();
                  rk = nextK$1(rk);
                  lk = var14;
                  u = u;
                  l = mediant;
                  continue;
               }

               SafeLong var10002 = nextK$1(lk);
               rk = (SafeLong).MODULE$.empty();
               lk = var10002;
               u = mediant;
               l = l;
               continue;
            }

            var10000 = mediant;
         }

         return var10000;
      }
   }

   public Rational() {
      ScalaNumericAnyConversions.$init$(this);
      Ordered.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static final class LongRational extends Rational {
      private static final long serialVersionUID = 0L;
      private final long n;
      private final long d;

      public long n() {
         return this.n;
      }

      public long d() {
         return this.d;
      }

      public SafeLong numerator() {
         return SafeLong$.MODULE$.apply(this.n());
      }

      public SafeLong denominator() {
         return SafeLong$.MODULE$.apply(this.d());
      }

      public long numeratorAsLong() {
         return this.n();
      }

      public boolean numeratorIsValidLong() {
         return true;
      }

      public boolean numeratorAbsIsValidLong() {
         return this.n() != Long.MIN_VALUE;
      }

      public long denominatorAsLong() {
         return this.d();
      }

      public boolean denominatorIsValidLong() {
         return true;
      }

      public boolean denominatorAbsIsValidLong() {
         return this.d() != Long.MIN_VALUE;
      }

      public Rational reciprocal() {
         if (this.n() == 0L) {
            throw new ArithmeticException("reciprocal called on 0/1");
         } else {
            return (Rational)(this.n() > 0L ? Rational$.MODULE$.spire$math$Rational$$longRational(this.d(), this.n()) : (this.n() != Long.MIN_VALUE && this.d() != Long.MIN_VALUE ? Rational$.MODULE$.spire$math$Rational$$longRational(-this.d(), -this.n()) : Rational$.MODULE$.spire$math$Rational$$bigRational(SafeLong$.MODULE$.apply(this.d()).unary_$minus(), SafeLong$.MODULE$.apply(this.n()).unary_$minus())));
         }
      }

      public int signum() {
         return Long.signum(this.n());
      }

      public boolean isWhole() {
         return this.d() == 1L;
      }

      public boolean isZero() {
         return this.n() == 0L;
      }

      public boolean isOne() {
         return this.d() == 1L && this.n() == 1L;
      }

      public boolean isValidChar() {
         return this.isWhole() && scala.runtime.RichLong..MODULE$.isValidChar$extension(this.n());
      }

      public boolean isValidByte() {
         return this.isWhole() && scala.runtime.RichLong..MODULE$.isValidByte$extension(this.n());
      }

      public boolean isValidShort() {
         return this.isWhole() && scala.runtime.RichLong..MODULE$.isValidShort$extension(this.n());
      }

      public boolean isValidInt() {
         return this.isWhole() && scala.runtime.RichLong..MODULE$.isValidInt$extension(this.n());
      }

      public boolean isValidLong() {
         return this.isWhole();
      }

      public SafeLong toSafeLong() {
         return SafeLong$.MODULE$.apply(this.n() / this.d());
      }

      public double doubleValue() {
         return Rational$.MODULE$.toDouble(SafeLong$.MODULE$.apply(this.n()), SafeLong$.MODULE$.apply(this.d()));
      }

      public Rational unary_$minus() {
         return (Rational)(this.n() == Long.MIN_VALUE ? Rational$.MODULE$.spire$math$Rational$$bigRational(SafeLong$.MODULE$.safe64(), SafeLong$.MODULE$.apply(this.d())) : Rational$.MODULE$.spire$math$Rational$$longRational(-this.n(), this.d()));
      }

      public Rational $plus(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var6 = (LongRational)r;
            long dgcd = package$.MODULE$.gcd(this.d(), var6.d());
            Rational var10000;
            if (dgcd == 1L) {
               try {
                  var10000 = this.checked$attempt$macro$1$3(var6);
               } catch (ArithmeticException var34) {
                  return Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(this.n()).$times(var6.d()).$plus(SafeLong$.MODULE$.apply(var6.n()).$times(this.d())), SafeLong$.MODULE$.apply(this.d()).$times(var6.d()));
               }
            } else {
               long lden = this.d() / dgcd;
               long rden = var6.d() / dgcd;

               try {
                  var10000 = this.checked$attempt$macro$15$1(rden, var6, lden, dgcd);
               } catch (ArithmeticException var35) {
                  SafeLong num = SafeLong$.MODULE$.apply(this.n()).$times(rden).$plus(SafeLong$.MODULE$.apply(var6.n()).$times(lden));
                  long ngcd = package$.MODULE$.gcd(dgcd, num.$percent(dgcd).toLong());
                  Rational result = ngcd == 1L ? Rational$.MODULE$.apply(num, SafeLong$.MODULE$.apply(lden).$times(var6.d())) : Rational$.MODULE$.apply(num.$div(ngcd), SafeLong$.MODULE$.apply(lden).$times(var6.d() / ngcd));
                  return result;
               }
            }

            var2 = var10000;
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var17 = (BigRational)r;
            long dgcd = package$.MODULE$.gcd(this.d(), var17.d().$percent(this.d()).toLong());
            Rational var36;
            if (dgcd == 1L) {
               SafeLong num = var17.d().$times(this.n()).$plus(var17.n().$times(this.d()));
               SafeLong den = var17.d().$times(this.d());
               var36 = Rational$.MODULE$.apply(num, den);
            } else {
               long lden = this.d() / dgcd;
               SafeLong rden = var17.d().$div(dgcd);
               SafeLong num = rden.$times(this.n()).$plus(var17.n().$times(lden));
               long ngcd;
               if (num instanceof SafeLongLong) {
                  SafeLongLong var29 = (SafeLongLong)num;
                  long x = var29.x();
                  ngcd = package$.MODULE$.gcd(x, dgcd);
               } else {
                  if (!(num instanceof SafeLongBigInteger)) {
                     throw new MatchError(num);
                  }

                  SafeLongBigInteger var32 = (SafeLongBigInteger)num;
                  BigInteger x = var32.x();
                  ngcd = package$.MODULE$.gcd(dgcd, x.mod(BigInteger.valueOf(dgcd)).longValue());
               }

               var36 = ngcd == 1L ? Rational$.MODULE$.apply(num, SafeLong$.MODULE$.apply(lden).$times(var17.d())) : Rational$.MODULE$.apply(num.$div(ngcd), var17.d().$div(ngcd).$times(lden));
            }

            var2 = var36;
         }

         return var2;
      }

      public Rational $minus(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var6 = (LongRational)r;
            long dgcd = package$.MODULE$.gcd(this.d(), var6.d());
            Rational var10000;
            if (dgcd == 1L) {
               try {
                  var10000 = this.checked$attempt$macro$1$4(var6);
               } catch (ArithmeticException var33) {
                  return Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(this.n()).$times(var6.d()).$minus(SafeLong$.MODULE$.apply(var6.n()).$times(this.d())), SafeLong$.MODULE$.apply(this.d()).$times(var6.d()));
               }
            } else {
               long lden = this.d() / dgcd;
               long rden = var6.d() / dgcd;

               try {
                  var10000 = this.checked$attempt$macro$15$2(rden, var6, lden, dgcd);
               } catch (ArithmeticException var34) {
                  SafeLong num = SafeLong$.MODULE$.apply(this.n()).$times(rden).$minus(SafeLong$.MODULE$.apply(var6.n()).$times(lden));
                  long ngcd = package$.MODULE$.gcd(dgcd, num.$percent(dgcd).toLong());
                  var10000 = ngcd == 1L ? Rational$.MODULE$.apply(num, SafeLong$.MODULE$.apply(lden).$times(var6.d())) : Rational$.MODULE$.apply(num.$div(ngcd), SafeLong$.MODULE$.apply(lden).$times(var6.d() / ngcd));
               }
            }

            var2 = var10000;
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var16 = (BigRational)r;
            long dgcd = package$.MODULE$.gcd(this.d(), var16.d().$percent(this.d()).toLong());
            Rational var35;
            if (dgcd == 1L) {
               SafeLong num = var16.d().$times(this.n()).$minus(var16.n().$times(this.d()));
               SafeLong den = var16.d().$times(this.d());
               var35 = Rational$.MODULE$.apply(num, den);
            } else {
               long lden = this.d() / dgcd;
               SafeLong rden = var16.d().$div(dgcd);
               SafeLong num = rden.$times(this.n()).$minus(var16.n().$times(lden));
               long ngcd;
               if (num instanceof SafeLongLong) {
                  SafeLongLong var28 = (SafeLongLong)num;
                  long x = var28.x();
                  ngcd = package$.MODULE$.gcd(x, dgcd);
               } else {
                  if (!(num instanceof SafeLongBigInteger)) {
                     throw new MatchError(num);
                  }

                  SafeLongBigInteger var31 = (SafeLongBigInteger)num;
                  BigInteger x = var31.x();
                  ngcd = package$.MODULE$.gcd(dgcd, x.mod(BigInteger.valueOf(dgcd)).longValue());
               }

               var35 = ngcd == 1L ? Rational$.MODULE$.apply(num, SafeLong$.MODULE$.apply(lden).$times(var16.d())) : Rational$.MODULE$.apply(num.$div(ngcd), var16.d().$div(ngcd).$times(lden));
            }

            var2 = var35;
         }

         return var2;
      }

      public Rational $times(final Rational r) {
         Object var10000;
         if (this.n() == 0L) {
            var10000 = Rational$.MODULE$.zero();
         } else {
            Object var2;
            if (r instanceof LongRational) {
               LongRational var4 = (LongRational)r;
               long a = package$.MODULE$.gcd(this.n(), var4.d());
               long b = package$.MODULE$.gcd(this.d(), var4.n());
               long n1 = this.n() / a;
               long n2 = var4.n() / b;
               long d1 = this.d() / b;
               long d2 = var4.d() / a;

               try {
                  var10000 = checked$attempt$macro$1$5(n1, n2, d1, d2);
               } catch (ArithmeticException var22) {
                  var10000 = Rational$.MODULE$.spire$math$Rational$$bigRational(SafeLong$.MODULE$.apply(n1).$times(n2), SafeLong$.MODULE$.apply(d1).$times(d2));
               }

               var2 = var10000;
            } else {
               if (!(r instanceof BigRational)) {
                  throw new MatchError(r);
               }

               BigRational var17 = (BigRational)r;
               long a = package$.MODULE$.gcd(this.n(), var17.d().$percent(this.n()).toLong());
               long b = package$.MODULE$.gcd(this.d(), var17.n().$percent(this.d()).toLong());
               var2 = Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(this.n() / a).$times(var17.n().$div(b)), SafeLong$.MODULE$.apply(this.d() / b).$times(var17.d().$div(a)));
            }

            var10000 = var2;
         }

         return (Rational)var10000;
      }

      public Rational $div(final Rational r) {
         Object var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            if (var4.n() == 0L) {
               throw new ArithmeticException("divide (/) by 0");
            }

            if (this.n() == 0L) {
               return this;
            }

            long a = package$.MODULE$.gcd(this.n(), var4.n());
            long b = package$.MODULE$.gcd(this.d(), var4.d());
            long n1 = this.n() / a;
            long n2 = var4.n() / a;
            LongRef d1 = LongRef.create(this.d() / b);
            LongRef d2 = LongRef.create(var4.d() / b);
            if (n2 < 0L) {
               d1.elem = -d1.elem;
               d2.elem = -d2.elem;
            }

            Object var10000;
            try {
               var10000 = checked$attempt$macro$1$6(n1, d2, d1, n2);
            } catch (ArithmeticException var22) {
               var10000 = Rational$.MODULE$.spire$math$Rational$$bigRational(SafeLong$.MODULE$.apply(n1).$times(d2.elem), SafeLong$.MODULE$.apply(d1.elem).$times(n2));
            }

            var2 = var10000;
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var15 = (BigRational)r;
            if (this.n() == 0L) {
               return this;
            }

            long a = package$.MODULE$.gcd(this.n(), var15.n().$percent(this.n()).toLong());
            long b = package$.MODULE$.gcd(this.d(), var15.d().$percent(this.d()).toLong());
            SafeLong num = SafeLong$.MODULE$.apply(this.n() / a).$times(var15.d().$div(b));
            SafeLong den = SafeLong$.MODULE$.apply(this.d() / b).$times(var15.n().$div(a));
            var2 = den.signum() < 0 ? Rational$.MODULE$.apply(num.unary_$minus(), den.unary_$minus()) : Rational$.MODULE$.apply(num, den);
         }

         return (Rational)var2;
      }

      public Rational floor() {
         return (Rational)(this.d() == 1L ? this : (this.n() >= 0L ? Rational$.MODULE$.apply(this.n() / this.d(), 1L) : Rational$.MODULE$.apply(this.n() / this.d() - 1L, 1L)));
      }

      public Rational ceil() {
         return (Rational)(this.d() == 1L ? this : (this.n() >= 0L ? Rational$.MODULE$.apply(this.n() / this.d() + 1L, 1L) : Rational$.MODULE$.apply(this.n() / this.d(), 1L)));
      }

      public Rational round() {
         Rational var10000;
         if (this.n() >= 0L) {
            long m = this.n() % this.d();
            var10000 = m >= this.d() - m ? Rational$.MODULE$.apply(this.n() / this.d() + 1L) : Rational$.MODULE$.apply(this.n() / this.d());
         } else {
            long m = -(this.n() % this.d());
            var10000 = m >= this.d() - m ? Rational$.MODULE$.apply(this.n() / this.d() - 1L) : Rational$.MODULE$.apply(this.n() / this.d());
         }

         return var10000;
      }

      public Rational pow(final int exp) {
         return exp == 0 ? Rational$.MODULE$.one() : (exp < 0 ? this.reciprocal().pow(-exp) : Rational$.MODULE$.apply(SafeLong$.MODULE$.apply(this.n()).pow(exp), SafeLong$.MODULE$.apply(this.d()).pow(exp)));
      }

      public int compareToOne() {
         return (new RichLong(scala.Predef..MODULE$.longWrapper(this.n()))).compare(BoxesRunTime.boxToLong(this.d()));
      }

      public int compare(final Rational r) {
         int var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;

            int var10000;
            try {
               var10000 = this.checked$attempt$macro$1$7(var4);
            } catch (ArithmeticException var10) {
               long dgcd = package$.MODULE$.gcd(this.d(), var4.d());
               var10000 = dgcd == 1L ? SafeLong$.MODULE$.apply(this.n()).$times(var4.d()).compare(SafeLong$.MODULE$.apply(var4.n()).$times(this.d())) : SafeLong$.MODULE$.apply(this.n()).$times(var4.d() / dgcd).compare(SafeLong$.MODULE$.apply(var4.n()).$times(this.d() / dgcd));
            }

            var2 = var10000;
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var7 = (BigRational)r;
            long dgcd = package$.MODULE$.gcd(this.d(), var7.d().$percent(this.d()).toLong());
            var2 = dgcd == 1L ? SafeLong$.MODULE$.apply(this.n()).$times(var7.d()).compare(var7.n().$times(this.d())) : SafeLong$.MODULE$.apply(this.n()).$times(var7.d().$div(dgcd)).compare(var7.n().$times(this.d() / dgcd));
         }

         return var2;
      }

      public long longValue() {
         return this.d() == 1L ? this.n() : this.n() / this.d();
      }

      public boolean equals(final Object that) {
         boolean var2;
         if (that instanceof LongRational) {
            LongRational var4 = (LongRational)that;
            var2 = this.n() == var4.n() && this.d() == var4.d();
         } else {
            var2 = super.equals(that);
         }

         return var2;
      }

      public int hashCode() {
         return this.d() == 1L ? this.unifiedPrimitiveHashcode() : 29 * (37 * Statics.longHash(this.n()) + Statics.longHash(this.d()));
      }

      public String toString() {
         return this.isWhole() ? Long.toString(this.n()) : (new StringBuilder(1)).append(this.n()).append("/").append(this.d()).toString();
      }

      private static final Rational checked$fallback$macro$2$3() {
         throw new ArithmeticOverflowException();
      }

      private final Rational checked$attempt$macro$1$3(final LongRational x2$1) {
         Rational$ var10000 = Rational$.MODULE$;
         long x$macro$7 = this.n();
         long y$macro$8 = x2$1.d();
         long z$macro$6 = x$macro$7 * y$macro$8;
         if (x$macro$7 == 0L || y$macro$8 == z$macro$6 / x$macro$7 && (x$macro$7 != -1L || y$macro$8 != Long.MIN_VALUE)) {
            long x$macro$10 = x2$1.n();
            long y$macro$11 = this.d();
            long z$macro$9 = x$macro$10 * y$macro$11;
            if (x$macro$10 == 0L || y$macro$11 == z$macro$9 / x$macro$10 && (x$macro$10 != -1L || y$macro$11 != Long.MIN_VALUE)) {
               long z$macro$3 = z$macro$6 + z$macro$9;
               if ((~(z$macro$6 ^ z$macro$9) & (z$macro$6 ^ z$macro$3)) < 0L) {
                  return checked$fallback$macro$2$3();
               } else {
                  long x$macro$13 = this.d();
                  long y$macro$14 = x2$1.d();
                  long z$macro$12 = x$macro$13 * y$macro$14;
                  return x$macro$13 != 0L && (y$macro$14 != z$macro$12 / x$macro$13 || x$macro$13 == -1L && y$macro$14 == Long.MIN_VALUE) ? checked$fallback$macro$2$3() : var10000.apply(z$macro$3, z$macro$12);
               }
            } else {
               return checked$fallback$macro$2$3();
            }
         } else {
            return checked$fallback$macro$2$3();
         }
      }

      private static final Rational checked$fallback$macro$16$1() {
         throw new ArithmeticOverflowException();
      }

      private final Rational checked$attempt$macro$15$1(final long rden$1, final LongRational x2$1, final long lden$2, final long dgcd$1) {
         long x$macro$21 = this.n();
         long z$macro$20 = x$macro$21 * rden$1;
         if (x$macro$21 == 0L || rden$1 == z$macro$20 / x$macro$21 && (x$macro$21 != -1L || rden$1 != Long.MIN_VALUE)) {
            long x$macro$23 = x2$1.n();
            long z$macro$22 = x$macro$23 * lden$2;
            if (x$macro$23 == 0L || lden$2 == z$macro$22 / x$macro$23 && (x$macro$23 != -1L || lden$2 != Long.MIN_VALUE)) {
               long z$macro$17 = z$macro$20 + z$macro$22;
               if ((~(z$macro$20 ^ z$macro$22) & (z$macro$20 ^ z$macro$17)) < 0L) {
                  return checked$fallback$macro$16$1();
               } else {
                  long ngcd = package$.MODULE$.gcd(z$macro$17, dgcd$1);
                  Rational var30;
                  if (ngcd == 1L) {
                     Rational$ var10000 = Rational$.MODULE$;
                     long y$macro$25 = x2$1.d();
                     long z$macro$24 = lden$2 * y$macro$25;
                     if (lden$2 != 0L && (y$macro$25 != z$macro$24 / lden$2 || lden$2 == -1L && y$macro$25 == Long.MIN_VALUE)) {
                        return checked$fallback$macro$16$1();
                     }

                     var30 = var10000.apply(z$macro$17, z$macro$24);
                  } else {
                     var30 = Rational$.MODULE$.buildWithDiv(z$macro$17, ngcd, x2$1.d(), lden$2);
                  }

                  return var30;
               }
            } else {
               return checked$fallback$macro$16$1();
            }
         } else {
            return checked$fallback$macro$16$1();
         }
      }

      private static final Rational checked$fallback$macro$2$4() {
         throw new ArithmeticOverflowException();
      }

      private final Rational checked$attempt$macro$1$4(final LongRational x2$2) {
         Rational$ var10000 = Rational$.MODULE$;
         long x$macro$7 = this.n();
         long y$macro$8 = x2$2.d();
         long z$macro$6 = x$macro$7 * y$macro$8;
         if (x$macro$7 == 0L || y$macro$8 == z$macro$6 / x$macro$7 && (x$macro$7 != -1L || y$macro$8 != Long.MIN_VALUE)) {
            long x$macro$10 = x2$2.n();
            long y$macro$11 = this.d();
            long z$macro$9 = x$macro$10 * y$macro$11;
            if (x$macro$10 == 0L || y$macro$11 == z$macro$9 / x$macro$10 && (x$macro$10 != -1L || y$macro$11 != Long.MIN_VALUE)) {
               long z$macro$3 = z$macro$6 - z$macro$9;
               if (((z$macro$6 ^ z$macro$9) & (z$macro$6 ^ z$macro$3)) < 0L) {
                  return checked$fallback$macro$2$4();
               } else {
                  long x$macro$13 = this.d();
                  long y$macro$14 = x2$2.d();
                  long z$macro$12 = x$macro$13 * y$macro$14;
                  return x$macro$13 != 0L && (y$macro$14 != z$macro$12 / x$macro$13 || x$macro$13 == -1L && y$macro$14 == Long.MIN_VALUE) ? checked$fallback$macro$2$4() : var10000.apply(z$macro$3, z$macro$12);
               }
            } else {
               return checked$fallback$macro$2$4();
            }
         } else {
            return checked$fallback$macro$2$4();
         }
      }

      private static final Rational checked$fallback$macro$16$2() {
         throw new ArithmeticOverflowException();
      }

      private final Rational checked$attempt$macro$15$2(final long rden$2, final LongRational x2$2, final long lden$3, final long dgcd$2) {
         long x$macro$21 = this.n();
         long z$macro$20 = x$macro$21 * rden$2;
         if (x$macro$21 == 0L || rden$2 == z$macro$20 / x$macro$21 && (x$macro$21 != -1L || rden$2 != Long.MIN_VALUE)) {
            long x$macro$23 = x2$2.n();
            long z$macro$22 = x$macro$23 * lden$3;
            if (x$macro$23 == 0L || lden$3 == z$macro$22 / x$macro$23 && (x$macro$23 != -1L || lden$3 != Long.MIN_VALUE)) {
               long z$macro$17 = z$macro$20 - z$macro$22;
               if (((z$macro$20 ^ z$macro$22) & (z$macro$20 ^ z$macro$17)) < 0L) {
                  return checked$fallback$macro$16$2();
               } else {
                  long ngcd = package$.MODULE$.gcd(z$macro$17, dgcd$2);
                  Rational var30;
                  if (ngcd == 1L) {
                     Rational$ var10000 = Rational$.MODULE$;
                     long y$macro$25 = x2$2.d();
                     long z$macro$24 = lden$3 * y$macro$25;
                     if (lden$3 != 0L && (y$macro$25 != z$macro$24 / lden$3 || lden$3 == -1L && y$macro$25 == Long.MIN_VALUE)) {
                        return checked$fallback$macro$16$2();
                     }

                     var30 = var10000.apply(z$macro$17, z$macro$24);
                  } else {
                     var30 = Rational$.MODULE$.buildWithDiv(z$macro$17, ngcd, x2$2.d(), lden$3);
                  }

                  return var30;
               }
            } else {
               return checked$fallback$macro$16$2();
            }
         } else {
            return checked$fallback$macro$16$2();
         }
      }

      private static final LongRational checked$fallback$macro$2$5() {
         throw new ArithmeticOverflowException();
      }

      private static final LongRational checked$attempt$macro$1$5(final long n1$1, final long n2$1, final long d1$1, final long d2$1) {
         Rational$ var10000 = Rational$.MODULE$;
         long z$macro$3 = n1$1 * n2$1;
         if (n1$1 == 0L || n2$1 == z$macro$3 / n1$1 && (n1$1 != -1L || n2$1 != Long.MIN_VALUE)) {
            long z$macro$4 = d1$1 * d2$1;
            return d1$1 != 0L && (d2$1 != z$macro$4 / d1$1 || d1$1 == -1L && d2$1 == Long.MIN_VALUE) ? checked$fallback$macro$2$5() : var10000.spire$math$Rational$$longRational(z$macro$3, z$macro$4);
         } else {
            return checked$fallback$macro$2$5();
         }
      }

      private static final LongRational checked$fallback$macro$2$6() {
         throw new ArithmeticOverflowException();
      }

      private static final LongRational checked$attempt$macro$1$6(final long n1$2, final LongRef d2$2, final LongRef d1$2, final long n2$2) {
         Rational$ var10000 = Rational$.MODULE$;
         long z$macro$3 = n1$2 * d2$2.elem;
         if (n1$2 == 0L || d2$2.elem == z$macro$3 / n1$2 && (n1$2 != -1L || d2$2.elem != Long.MIN_VALUE)) {
            long z$macro$4 = d1$2.elem * n2$2;
            return d1$2.elem != 0L && (n2$2 != z$macro$4 / d1$2.elem || d1$2.elem == -1L && n2$2 == Long.MIN_VALUE) ? checked$fallback$macro$2$6() : var10000.spire$math$Rational$$longRational(z$macro$3, z$macro$4);
         } else {
            return checked$fallback$macro$2$6();
         }
      }

      private static final int checked$fallback$macro$2$7() {
         throw new ArithmeticOverflowException();
      }

      private final int checked$attempt$macro$1$7(final LongRational x2$3) {
         Order var10000 = (Order)spire.std.package.long$.MODULE$.LongAlgebra();
         long x$macro$4 = this.n();
         long y$macro$5 = x2$3.d();
         long z$macro$3 = x$macro$4 * y$macro$5;
         if (x$macro$4 == 0L || y$macro$5 == z$macro$3 / x$macro$4 && (x$macro$4 != -1L || y$macro$5 != Long.MIN_VALUE)) {
            long x$macro$7 = x2$3.n();
            long y$macro$8 = this.d();
            long z$macro$6 = x$macro$7 * y$macro$8;
            return x$macro$7 != 0L && (y$macro$8 != z$macro$6 / x$macro$7 || x$macro$7 == -1L && y$macro$8 == Long.MIN_VALUE) ? checked$fallback$macro$2$7() : var10000.compare$mcJ$sp(z$macro$3, z$macro$6);
         } else {
            return checked$fallback$macro$2$7();
         }
      }

      public LongRational(final long n, final long d) {
         this.n = n;
         this.d = d;
      }
   }

   private static final class BigRational extends Rational {
      private static final long serialVersionUID = 0L;
      private final SafeLong n;
      private final SafeLong d;

      public SafeLong n() {
         return this.n;
      }

      public SafeLong d() {
         return this.d;
      }

      public SafeLong numerator() {
         return this.n();
      }

      public SafeLong denominator() {
         return this.d();
      }

      public long numeratorAsLong() {
         return this.n().toLong();
      }

      public boolean numeratorIsValidLong() {
         return this.n().isValidLong();
      }

      public boolean numeratorAbsIsValidLong() {
         return this.n().isValidLong() && this.n().toLong() != Long.MIN_VALUE;
      }

      public long denominatorAsLong() {
         return this.d().toLong();
      }

      public boolean denominatorIsValidLong() {
         return this.d().isValidLong();
      }

      public boolean denominatorAbsIsValidLong() {
         return this.d().isValidLong() && this.d().toLong() != Long.MIN_VALUE;
      }

      public Rational reciprocal() {
         return (Rational)(this.signum() < 0 ? Rational$.MODULE$.apply(this.d().unary_$minus(), this.n().unary_$minus()) : Rational$.MODULE$.spire$math$Rational$$bigRational(this.d(), this.n()));
      }

      public int signum() {
         return this.n().signum();
      }

      public boolean isWhole() {
         return this.d().isOne();
      }

      public boolean isZero() {
         return false;
      }

      public boolean isOne() {
         return false;
      }

      public boolean isValidChar() {
         return false;
      }

      public boolean isValidByte() {
         return false;
      }

      public boolean isValidShort() {
         return false;
      }

      public boolean isValidInt() {
         return false;
      }

      public boolean isValidLong() {
         return false;
      }

      public SafeLong toSafeLong() {
         return this.n().$div(this.d());
      }

      public double doubleValue() {
         return Rational$.MODULE$.toDouble(this.n(), this.d());
      }

      public Rational unary_$minus() {
         return Rational$.MODULE$.apply(this.n().unary_$minus(), this.d());
      }

      public Rational $plus(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            var2 = var4.$plus(this);
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var5 = (BigRational)r;
            SafeLong dgcd = this.d().gcd(var5.d());
            Rational var10000;
            if (dgcd.isOne()) {
               var10000 = Rational$.MODULE$.apply(var5.d().$times(this.n()).$plus(var5.n().$times(this.d())), var5.d().$times(this.d()));
            } else {
               SafeLong lden = this.d().$div(dgcd);
               SafeLong rden = var5.d().$div(dgcd);
               SafeLong num = rden.$times(this.n()).$plus(var5.n().$times(lden));
               SafeLong ngcd = num.gcd(dgcd);
               var10000 = ngcd.isOne() ? Rational$.MODULE$.apply(num, lden.$times(var5.d())) : Rational$.MODULE$.apply(num.$div(ngcd), var5.d().$div(ngcd).$times(lden));
            }

            var2 = var10000;
         }

         return var2;
      }

      public Rational $minus(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            var2 = var4.unary_$minus().$plus(this);
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var5 = (BigRational)r;
            SafeLong dgcd = this.d().gcd(var5.d());
            Rational var10000;
            if (dgcd.isOne()) {
               var10000 = Rational$.MODULE$.apply(var5.d().$times(this.n()).$minus(var5.n().$times(this.d())), var5.d().$times(this.d()));
            } else {
               SafeLong lden = this.d().$div(dgcd);
               SafeLong rden = var5.d().$div(dgcd);
               SafeLong num = rden.$times(this.n()).$minus(var5.n().$times(lden));
               SafeLong ngcd = num.gcd(dgcd);
               var10000 = ngcd.isOne() ? Rational$.MODULE$.apply(num, lden.$times(var5.d())) : Rational$.MODULE$.apply(num.$div(ngcd), var5.d().$div(ngcd).$times(lden));
            }

            var2 = var10000;
         }

         return var2;
      }

      public Rational $times(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            var2 = var4.$times(this);
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var5 = (BigRational)r;
            SafeLong a = this.n().gcd(var5.d());
            SafeLong b = this.d().gcd(var5.n());
            var2 = Rational$.MODULE$.apply(this.n().$div(a).$times(var5.n().$div(b)), this.d().$div(b).$times(var5.d().$div(a)));
         }

         return var2;
      }

      public Rational $div(final Rational r) {
         Rational var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            var2 = var4.inverse().$times(this);
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var5 = (BigRational)r;
            SafeLong a = this.n().gcd(var5.n());
            SafeLong b = this.d().gcd(var5.d());
            SafeLong num = this.n().$div(a).$times(var5.d().$div(b));
            SafeLong den = this.d().$div(b).$times(var5.n().$div(a));
            var2 = den.signum() < 0 ? Rational$.MODULE$.apply(num.unary_$minus(), den.unary_$minus()) : Rational$.MODULE$.apply(num, den);
         }

         return var2;
      }

      public Rational floor() {
         return (Rational)(this.isWhole() ? this : (this.n().signum() >= 0 ? Rational$.MODULE$.apply(this.n().$div(this.d()), SafeLong$.MODULE$.one()) : Rational$.MODULE$.apply(this.n().$div(this.d()).$minus(1L), SafeLong$.MODULE$.one())));
      }

      public Rational ceil() {
         return (Rational)(this.isWhole() ? this : (this.n().signum() >= 0 ? Rational$.MODULE$.apply(this.n().$div(this.d()).$plus(1L), SafeLong$.MODULE$.one()) : Rational$.MODULE$.apply(this.n().$div(this.d()), SafeLong$.MODULE$.one())));
      }

      public Rational round() {
         Rational var10000;
         if (this.n().signum() >= 0) {
            SafeLong m = this.n().$percent(this.d());
            var10000 = m.$greater$eq(this.d().$minus(m)) ? Rational$.MODULE$.apply(this.n().$div(this.d()).$plus(1L)) : Rational$.MODULE$.apply(this.n().$div(this.d()));
         } else {
            SafeLong m = this.n().$percent(this.d()).unary_$minus();
            var10000 = m.$greater$eq(this.d().$minus(m)) ? Rational$.MODULE$.apply(this.n().$div(this.d()).$minus(1L)) : Rational$.MODULE$.apply(this.n().$div(this.d()));
         }

         return var10000;
      }

      public Rational pow(final int exp) {
         return exp == 0 ? Rational$.MODULE$.one() : (exp < 0 ? Rational$.MODULE$.apply(this.d().pow(-exp), this.n().pow(-exp)) : Rational$.MODULE$.apply(this.n().pow(exp), this.d().pow(exp)));
      }

      public int compareToOne() {
         return this.n().compare(this.d());
      }

      public int compare(final Rational r) {
         int var2;
         if (r instanceof LongRational) {
            LongRational var4 = (LongRational)r;
            long dgcd = package$.MODULE$.gcd(var4.d(), this.d().$percent(var4.d()).toLong());
            var2 = dgcd == 1L ? this.n().$times(var4.d()).compare(SafeLong$.MODULE$.apply(var4.n()).$times(this.d())) : this.n().$times(var4.d() / dgcd).compare(SafeLong$.MODULE$.apply(var4.n()).$times(this.d().$div(dgcd)));
         } else {
            if (!(r instanceof BigRational)) {
               throw new MatchError(r);
            }

            BigRational var7 = (BigRational)r;
            SafeLong dgcd = this.d().gcd(var7.d());
            var2 = dgcd.isOne() ? this.n().$times(var7.d()).compare(var7.n().$times(this.d())) : var7.d().$div(dgcd).$times(this.n()).compare(this.d().$div(dgcd).$times(var7.n()));
         }

         return var2;
      }

      public boolean equals(final Object that) {
         boolean var2;
         if (that instanceof BigRational) {
            BigRational var4 = (BigRational)that;
            var2 = BoxesRunTime.equalsNumNum(this.n(), var4.n()) && BoxesRunTime.equalsNumNum(this.d(), var4.d());
         } else {
            var2 = super.equals(that);
         }

         return var2;
      }

      public int hashCode() {
         return 29 * (37 * Statics.anyHash(this.n()) + Statics.anyHash(this.d()));
      }

      public String toString() {
         return this.isWhole() ? this.n().toString() : (new StringBuilder(1)).append(this.n()).append("/").append(this.d()).toString();
      }

      public BigRational(final SafeLong n, final SafeLong d) {
         this.n = n;
         this.d = d;
      }
   }
}
