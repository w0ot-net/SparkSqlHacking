package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.generic.CommonErrors$;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.ArraySeq$;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Range;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011=gaB<y!\u0003\r\t! \u0005\b\u0003[\u0001A\u0011AA\u0018\u0011\u001d\t9\u0004\u0001C!\u0003sAq!!\u0010\u0001\t\u0003\ny\u0004C\u0004\u0002V\u0001!\t%a\u0016\t\u000f\u0005\u001d\u0004\u0001\"\u0011\u0002j!9\u0011Q\u000f\u0001\u0005B\u0005e\u0002bBA<\u0001\u0011\u0005\u0013\u0011\u0010\u0005\b\u0003\u000b\u0003A\u0011IAD\u0011\u001d\tY\t\u0001C!\u0003\u001bCq!!%\u0001\t\u0003\n\u0019\nC\u0004\u0002\u0018\u0002!\t%!'\t\u000f\u0005\u001d\u0006\u0001\"\u0001\u0002*\"9A\u0011\u0015\u0001\u0005\u0002\u0011\r\u0006b\u0002CY\u0001\u0011\u0005A1\u0017\u0005\b\t\u000f\u0002A\u0011\tCa\u0011!!i\r\u0001Q\u0005R\r}saBA^q\"\u0005\u0011Q\u0018\u0004\u0007obD\t!a0\t\u000f\u0005E'\u0003\"\u0001\u0002T\u00161\u0011Q\u001b\n\u0005\u0003/4a!!>\u0013\u0001\u0005]\bB\u0003B\u0003+\t\u0005\t\u0015!\u0003\u0003\b!9\u0011\u0011[\u000b\u0005\u0002\t-\u0001b\u0002B\t+\u0011\u0005!1\u0003\u0005\b\u00053)B\u0011\u0001B\u000e\u0011\u001d\u0011i\"\u0006C\u0001\u0005?AqAa\n\u0016\t\u0003\u0012Y\u0002C\u0004\u0003*U!\tEa\u000b\u0007\r\t}\"\u0003\u0001B!\u0011)\u0011)!\bB\u0001B\u0003%!1\f\u0005\u000b\u0003\u001bj\"\u0011!Q\u0001\n\tu\u0003bBAi;\u0011\u0005!q\f\u0005\b\u0005#iB\u0011\u0001B4\u0011\u001d\u0011I\"\bC\u0001\u000571aA!\u001c\u0013\u0001\t=\u0004B\u0003B\u0003G\t\u0005\t\u0015!\u0003\u0003~!Q\u0011QM\u0012\u0003\u0002\u0003\u0006IAa\u001e\t\u000f\u0005E7\u0005\"\u0001\u0003\u0000!9!\u0011C\u0012\u0005\u0002\t\u001d\u0005b\u0002B\rG\u0011\u0005!1\u0004\u0004\u0007\u0005\u001b\u0013\u0002Aa$\t\u0015\u0005\u0015\u0014F!A!\u0002\u0013\u00119\n\u0003\u0006\u0003\u0006%\u0012\t\u0011)A\u0005\u0005;Cq!!5*\t\u0003\u0011y\nC\u0004\u0003\u0012%\"\tAa*\t\u000f\te\u0011\u0006\"\u0001\u0003\u001c\u00191!Q\u0016\n\u0001\u0005_C!B!00\u0005\u0003\u0005\u000b\u0011\u0002B`\u0011)\t)l\fB\u0001B\u0003%!q\u0018\u0005\b\u0003#|C\u0011\u0001Ba\u0011\u001d\u0011\tb\fC\u0001\u0005\u0013DqA!\u00070\t\u0003\u0011YB\u0002\u0004\u0003PJ\u0001!\u0011\u001b\u0005\u000b\u0005\u000b)$\u0011!Q\u0001\n\tm\u0007bBAik\u0011\u0005!Q\u001c\u0005\b\u0005#)D\u0011\u0001Br\u0011\u001d\u0011I\"\u000eC\u0001\u00057AqA!\b6\t\u0003\u0011I\u000fC\u0004\u0003(U\"\tEa\u0007\t\u000f\t%R\u0007\"\u0011\u0003,\u00191!q\u001e\n\u0001\u0005cD!B!\u0002>\u0005\u0003\u0005\u000b\u0011\u0002B\u0000\u0011)\ti(\u0010B\u0001B\u0003%\u0011q\u0010\u0005\b\u0003#lD\u0011AB\u0001\u0011\u001d\u0011\t\"\u0010C\u0001\u0007\u0013AqA!\u0007>\t\u0003\u0011YB\u0002\u0004\u0004\u0010I\u00011\u0011\u0003\u0005\u000b\u0005\u000b\u0019%\u0011!Q\u0001\n\r}\u0001BCA?\u0007\n\u0005\t\u0015!\u0003\u0002\u0000!9\u0011\u0011[\"\u0005\u0002\r\u0005\u0002\u0002CB\u0015\u0007\u0002\u0006I!a \t\u000f\te1\t\"\u0001\u0003\u001c!9!\u0011C\"\u0005\u0002\r-bABBB%\u0001\u0019)\t\u0003\u0006\u0003\u0006)\u0013\t\u0011)A\u0005\u0007'C!\"! K\u0005\u0003\u0005\u000b\u0011BA@\u0011\u001d\t\tN\u0013C\u0001\u0007+CqA!\u0007K\t\u0003\u0011Y\u0002C\u0004\u0003\u0012)#\ta!(\t\u000f\u0005\u0015%\n\"\u0011\u0004.\u001a111\u0017\n\u0001\u0007kC!B!\u0002R\u0005\u0003\u0005\u000b\u0011BBb\u0011)\ti(\u0015B\u0001B\u0003%\u0011q\u0010\u0005\b\u0003#\fF\u0011ABc\u0011!\u0019i-\u0015Q\u0001\n\u0005}\u0004b\u0002B\r#\u0012\u0005!1\u0004\u0005\b\u0005#\tF\u0011ABh\r\u0019\u0019\tO\u0005\u0001\u0004d\"Q!Q\u0001-\u0003\u0002\u0003\u0006Ka!<\t\u0015\r5\u0007L!A!\u0002\u0013\ty\b\u0003\u0006\u0004pb\u0013\t\u0011)A\u0005\u0007cDq!!5Y\t\u0013\u0019i\u0010C\u0004\u0002Rb#\t\u0001b\u0002\u0007\u0011\u00115\u0001\f)A\u0005\t\u001fAq!!5_\t\u0003!\t\u0002\u0003\u0006\u0005\u0018yC)\u0019)C\u0005\t3AqA!\u0005_\t\u0003!i\u0002C\u0004\u0003\u001ay#\tAa\u0007\t\u000f\tua\f\"\u0001\u0005\"!9!q\u00050\u0005B\tm\u0001b\u0002B\u0015=\u0012\u0005#1\u0006\u0005\b\tKqF\u0011\tC\u0014\u0011\u001d\t)H\u0018C!\twAq\u0001\"\u0010_\t#\"y\u0004C\u0004\u0005Hy#\t\u0005\"\u0013\t\u0011\u0011m\u0003\f)Q\u0005\u0005[A!\u0002\"\u001aY\u0011\u000b\u0007K\u0011\u0002C4\u0011!!y\u0007\u0017Q\u0005\n\u0011E\u0004b\u0002B\t1\u0012\u0005A1\u000f\u0005\b\u00053AF\u0011\u0001B\u000e\u0011\u001d\u0011i\u0002\u0017C\u0001\tCAqAa\nY\t\u0003\u0012Y\u0002C\u0004\u0003*a#\tEa\u000b\t\u000f\u0011\u0015\u0002\f\"\u0011\u0005x!9\u0011Q\u000f-\u0005B\u0011m\u0002b\u0002C\u001f1\u0012ECq\b\u0005\b\t\u000fBF\u0011\tCB\u0011%!\tJEA\u0001\n\u0013!\u0019JA\u0004TKF4\u0016.Z<\u000b\u0005eT\u0018AC2pY2,7\r^5p]*\t10A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007y\f\u0019b\u0005\u0004\u0001\u007f\u0006\u001d\u00111\u0006\t\u0005\u0003\u0003\t\u0019!D\u0001{\u0013\r\t)A\u001f\u0002\u0007\u0003:L(+\u001a4\u0011\u0015\u0005%\u00111BA\b\u0003K\tY#D\u0001y\u0013\r\ti\u0001\u001f\u0002\u0007'\u0016\fx\n]:\u0011\t\u0005E\u00111\u0003\u0007\u0001\t!\t)\u0002\u0001CC\u0002\u0005]!!A!\u0012\t\u0005e\u0011q\u0004\t\u0005\u0003\u0003\tY\"C\u0002\u0002\u001ei\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u0002\u0005\u0005\u0012bAA\u0012u\n\u0019\u0011I\\=\u0011\t\u0005%\u0011qE\u0005\u0004\u0003SA(\u0001\u0002,jK^\u0004b!!\u0003\u0002(\u0005=\u0011A\u0002\u0013j]&$H\u0005\u0006\u0002\u00022A!\u0011\u0011AA\u001a\u0013\r\t)D\u001f\u0002\u0005+:LG/\u0001\u0003wS\u0016<XCAA\u001e!\u0015\tI\u0001AA\b\u0003\ri\u0017\r]\u000b\u0005\u0003\u0003\n9\u0005\u0006\u0003\u0002D\u0005-\u0003#BA\u0005\u0001\u0005\u0015\u0003\u0003BA\t\u0003\u000f\"q!!\u0013\u0004\u0005\u0004\t9BA\u0001C\u0011\u001d\tie\u0001a\u0001\u0003\u001f\n\u0011A\u001a\t\t\u0003\u0003\t\t&a\u0004\u0002F%\u0019\u00111\u000b>\u0003\u0013\u0019+hn\u0019;j_:\f\u0014\u0001C1qa\u0016tG-\u001a3\u0016\t\u0005e\u0013q\f\u000b\u0005\u00037\n\u0019\u0007E\u0003\u0002\n\u0001\ti\u0006\u0005\u0003\u0002\u0012\u0005}CaBA%\t\t\u0007\u0011\u0011M\t\u0005\u0003\u001f\ty\u0002C\u0004\u0002f\u0011\u0001\r!!\u0018\u0002\t\u0015dW-\\\u0001\naJ,\u0007/\u001a8eK\u0012,B!a\u001b\u0002rQ!\u0011QNA:!\u0015\tI\u0001AA8!\u0011\t\t\"!\u001d\u0005\u000f\u0005%SA1\u0001\u0002b!9\u0011QM\u0003A\u0002\u0005=\u0014a\u0002:fm\u0016\u00148/Z\u0001\u0005i\u0006\\W\r\u0006\u0003\u0002<\u0005m\u0004bBA?\u000f\u0001\u0007\u0011qP\u0001\u0002]B!\u0011\u0011AAA\u0013\r\t\u0019I\u001f\u0002\u0004\u0013:$\u0018\u0001\u00023s_B$B!a\u000f\u0002\n\"9\u0011Q\u0010\u0005A\u0002\u0005}\u0014!\u0003;bW\u0016\u0014\u0016n\u001a5u)\u0011\tY$a$\t\u000f\u0005u\u0014\u00021\u0001\u0002\u0000\u0005IAM]8q%&<\u0007\u000e\u001e\u000b\u0005\u0003w\t)\nC\u0004\u0002~)\u0001\r!a \u0002\u000fQ\f\u0007/R1dQV!\u00111TAR)\u0011\tY$!(\t\u000f\u000553\u00021\u0001\u0002 BA\u0011\u0011AA)\u0003\u001f\t\t\u000b\u0005\u0003\u0002\u0012\u0005\rFaBAS\u0017\t\u0007\u0011q\u0003\u0002\u0002+\u000611m\u001c8dCR,B!a+\u00022R!\u0011QVAZ!\u0015\tI\u0001AAX!\u0011\t\t\"!-\u0005\u000f\u0005%CB1\u0001\u0002b!9\u0011Q\u0017\u0007A\u0002\u0005]\u0016AB:vM\u001aL\u0007\u0010E\u0003\u0002:R\tyKD\u0002\u0002\nE\tqaU3r-&,w\u000fE\u0002\u0002\nI\u0019BAE@\u0002BB!\u00111YAg\u001b\t\t)M\u0003\u0003\u0002H\u0006%\u0017AA5p\u0015\t\tY-\u0001\u0003kCZ\f\u0017\u0002BAh\u0003\u000b\u0014AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtDCAA_\u0005)\u0019v.\\3TKF|\u0005o]\u000b\u0005\u00033\fy\u000e\r\u0003\u0002\\\u0006E\bCCA\u0005\u0003\u0017\ti.!9\u0002pB!\u0011\u0011CAp\t!\t)\u0002\u0006CC\u0002\u0005]\u0001\u0003BAr\u0003StA!!\u0003\u0002f&\u0019\u0011q\u001d=\u0002\u000fA\f7m[1hK&!\u00111^Aw\u0005%\te._\"p]N$(OC\u0002\u0002hb\u0004B!!\u0005\u0002r\u0012Y\u00111\u001f\u000b\u0002\u0002\u0003\u0005)\u0011AA\f\u0005\ryF%\r\u0002\u0003\u0013\u0012,B!!?\u0003\u0004M\u0019Q#a?\u0011\r\u0005%\u0011Q B\u0001\u0013\r\ty\u0010\u001f\u0002\u0010\u0003\n\u001cHO]1diN+\u0017OV5foB!\u0011\u0011\u0003B\u0002\t!\t)\"\u0006CC\u0002\u0005]\u0011AC;oI\u0016\u0014H._5oOB)!\u0011\u0002\u000b\u0003\u00025\t!\u0003\u0006\u0003\u0003\u000e\t=\u0001#\u0002B\u0005+\t\u0005\u0001b\u0002B\u0003/\u0001\u0007!qA\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0005\u0003\u0011)\u0002C\u0004\u0003\u0018a\u0001\r!a \u0002\u0007%$\u00070\u0001\u0004mK:<G\u000f[\u000b\u0003\u0003\u007f\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0005C\u0001b!!\u0003\u0003$\t\u0005\u0011b\u0001B\u0013q\nA\u0011\n^3sCR|'/A\u0005l]><hnU5{K\u00069\u0011n]#naRLXC\u0001B\u0017!\u0011\t\tAa\f\n\u0007\tE\"PA\u0004C_>dW-\u00198)\u000fU\u0011)Da\u000f\u0003>A!\u0011\u0011\u0001B\u001c\u0013\r\u0011ID\u001f\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012a\u0001\u0002\u0004\u001b\u0006\u0004XC\u0002B\"\u0005'\u00129fE\u0003\u001e\u0005\u000b\u0012I\u0006\u0005\u0005\u0003H\t5#\u0011\u000bB+\u001d\u0011\tIA!\u0013\n\u0007\t-\u00030\u0001\u0003WS\u0016<\u0018\u0002\u0002B \u0005\u001fR1Aa\u0013y!\u0011\t\tBa\u0015\u0005\u0011\u0005UQ\u0004\"b\u0001\u0003/\u0001B!!\u0005\u0003X\u0011A\u0011\u0011J\u000f\u0005\u0006\u0004\t9\u0002E\u0003\u0002\n\u0001\u0011)\u0006E\u0003\u0003\nQ\u0011\t\u0006\u0005\u0005\u0002\u0002\u0005E#\u0011\u000bB+)\u0019\u0011\tGa\u0019\u0003fA9!\u0011B\u000f\u0003R\tU\u0003b\u0002B\u0003A\u0001\u0007!1\f\u0005\b\u0003\u001b\u0002\u0003\u0019\u0001B/)\u0011\u0011)F!\u001b\t\u000f\t]\u0011\u00051\u0001\u0002\u0000!:QD!\u000e\u0003<\tu\"\u0001C!qa\u0016tG-\u001a3\u0016\t\tE$\u0011P\n\u0006G\tM$1\u0010\t\u0007\u0005\u000f\u0012)Ha\u001e\n\t\t5$q\n\t\u0005\u0003#\u0011I\b\u0002\u0005\u0002\u0016\r\")\u0019AA\f!\u0015\tI\u0001\u0001B<!\u0015\u0011I\u0001\u0006B<)\u0019\u0011\tIa!\u0003\u0006B)!\u0011B\u0012\u0003x!9!Q\u0001\u0014A\u0002\tu\u0004bBA3M\u0001\u0007!q\u000f\u000b\u0005\u0005o\u0012I\tC\u0004\u0003\u0018\u001d\u0002\r!a )\u000f\r\u0012)Da\u000f\u0003>\tI\u0001K]3qK:$W\rZ\u000b\u0005\u0005#\u0013IjE\u0003*\u0005'\u0013Y\n\u0005\u0004\u0003H\tU%qS\u0005\u0005\u0005\u001b\u0013y\u0005\u0005\u0003\u0002\u0012\teE\u0001CA\u000bS\u0011\u0015\r!a\u0006\u0011\u000b\u0005%\u0001Aa&\u0011\u000b\t%ACa&\u0015\r\t\u0005&1\u0015BS!\u0015\u0011I!\u000bBL\u0011\u001d\t)\u0007\fa\u0001\u0005/CqA!\u0002-\u0001\u0004\u0011i\n\u0006\u0003\u0003\u0018\n%\u0006b\u0002B\f[\u0001\u0007\u0011q\u0010\u0015\bS\tU\"1\bB\u001f\u0005\u0019\u0019uN\\2biV!!\u0011\u0017B]'\u0015y#1\u0017B^!\u0019\u00119E!.\u00038&!!Q\u0016B(!\u0011\t\tB!/\u0005\u000f\u0005UqF1\u0001\u0002\u0018A)\u0011\u0011\u0002\u0001\u00038\u00061\u0001O]3gSb\u0004RA!\u0003\u0015\u0005o#bAa1\u0003F\n\u001d\u0007#\u0002B\u0005_\t]\u0006b\u0002B_e\u0001\u0007!q\u0018\u0005\b\u0003k\u0013\u0004\u0019\u0001B`)\u0011\u00119La3\t\u000f\t]1\u00071\u0001\u0002\u0000!:qF!\u000e\u0003<\tu\"a\u0002*fm\u0016\u00148/Z\u000b\u0005\u0005'\u0014InE\u00026\u0005+\u0004b!!\u0003\u0002~\n]\u0007\u0003BA\t\u00053$q!!\u00066\u0005\u0004\t9\u0002E\u0003\u0003\nQ\u00119\u000e\u0006\u0003\u0003`\n\u0005\b#\u0002B\u0005k\t]\u0007b\u0002B\u0003o\u0001\u0007!1\u001c\u000b\u0005\u0005/\u0014)\u000fC\u0004\u0003hb\u0002\r!a \u0002\u0003%,\"Aa;\u0011\r\u0005%!1\u0005BlQ\u001d)$Q\u0007B\u001e\u0005{\u0011A\u0001V1lKV!!1\u001fB~'\u0015i$Q\u001fB\u007f!\u0019\u00119Ea>\u0003z&!!q\u001eB(!\u0011\t\tBa?\u0005\u0011\u0005UQ\b\"b\u0001\u0003/\u0001R!!\u0003\u0001\u0005s\u0004RA!\u0003\u0015\u0005s$baa\u0001\u0004\u0006\r\u001d\u0001#\u0002B\u0005{\te\bb\u0002B\u0003\u0001\u0002\u0007!q \u0005\b\u0003{\u0002\u0005\u0019AA@)\u0011\u0011Ipa\u0003\t\u000f\t]\u0011\t1\u0001\u0002\u0000!:QH!\u000e\u0003<\tu\"!\u0003+bW\u0016\u0014\u0016n\u001a5u+\u0011\u0019\u0019ba\u0007\u0014\u000b\r\u001b)b!\b\u0011\r\t\u001d3qCB\r\u0013\u0011\u0019yAa\u0014\u0011\t\u0005E11\u0004\u0003\t\u0003+\u0019EQ1\u0001\u0002\u0018A)\u0011\u0011\u0002\u0001\u0004\u001aA)!\u0011\u0002\u000b\u0004\u001aQ111EB\u0013\u0007O\u0001RA!\u0003D\u00073AqA!\u0002G\u0001\u0004\u0019y\u0002C\u0004\u0002~\u0019\u0003\r!a \u0002\u000b\u0011,G\u000e^1\u0015\t\re1Q\u0006\u0005\b\u0005OL\u0005\u0019AA@Q\u0015I5\u0011GB\"!\u0019\t\taa\r\u00048%\u00191Q\u0007>\u0003\rQD'o\\<t!\u0011\u0019Id!\u0010\u000f\t\u0005\u000511H\u0005\u0004\u0003OT\u0018\u0002BB \u0007\u0003\u0012\u0011$\u00138eKb|U\u000f^(g\u0005>,h\u000eZ:Fq\u000e,\u0007\u000f^5p]*\u0019\u0011q\u001d>2\u000fy\u0019)ea\u0017\u0004\u0000A!1qIB+\u001d\u0011\u0019Ie!\u0015\u0011\u0007\r-#0\u0004\u0002\u0004N)\u00191q\n?\u0002\rq\u0012xn\u001c;?\u0013\r\u0019\u0019F_\u0001\u0007!J,G-\u001a4\n\t\r]3\u0011\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\rM#0M\u0005$\u0007;\u001a)g!\u001e\u0004hU!1qLB1+\t\u0019)\u0005B\u0004\u0004dq\u0014\ra!\u001c\u0003\u0003QKAaa\u001a\u0004j\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIER1aa\u001b{\u0003\u0019!\bN]8xgF!\u0011\u0011DB8!\u0011\u0019Id!\u001d\n\t\rM4\u0011\t\u0002\n)\"\u0014xn^1cY\u0016\f\u0014bIB<\u0007s\u001aYha\u001b\u000f\t\u0005\u00051\u0011P\u0005\u0004\u0007WR\u0018G\u0002\u0012\u0002\u0002i\u001ciHA\u0003tG\u0006d\u0017-M\u0002'\u0007oAsa\u0011B\u001b\u0005w\u0011iD\u0001\u0003Ee>\u0004X\u0003BBD\u0007\u001f\u001bRASBE\u0007#\u0003bAa\u0012\u0004\f\u000e5\u0015\u0002BBB\u0005\u001f\u0002B!!\u0005\u0004\u0010\u00129\u0011Q\u0003&C\u0002\u0005]\u0001#BA\u0005\u0001\r5\u0005#\u0002B\u0005)\r5ECBBL\u00073\u001bY\nE\u0003\u0003\n)\u001bi\tC\u0004\u0003\u00065\u0003\raa%\t\u000f\u0005uT\n1\u0001\u0002\u0000Q!1QRBP\u0011\u001d\u00119o\u0014a\u0001\u0003\u007fBSaTB\u0019\u0007G\u000btAHB#\u0007K\u001bY+M\u0005$\u0007;\u001a)ga*\u0004hEJ1ea\u001e\u0004z\r%61N\u0019\u0007E\u0005\u0005!p! 2\u0007\u0019\u001a9\u0004\u0006\u0003\u0004\u0012\u000e=\u0006bBA?!\u0002\u0007\u0011q\u0010\u0015\b\u0015\nU\"1\bB\u001f\u0005%!%o\u001c9SS\u001eDG/\u0006\u0003\u00048\u000e}6#B)\u0004:\u000e\u0005\u0007C\u0002B$\u0007w\u001bi,\u0003\u0003\u00044\n=\u0003\u0003BA\t\u0007\u007f#q!!\u0006R\u0005\u0004\t9\u0002E\u0003\u0002\n\u0001\u0019i\fE\u0003\u0003\nQ\u0019i\f\u0006\u0004\u0004H\u000e%71\u001a\t\u0006\u0005\u0013\t6Q\u0018\u0005\b\u0005\u000b!\u0006\u0019ABb\u0011\u001d\ti\b\u0016a\u0001\u0003\u007f\n1\u0001\\3o)\u0011\u0019il!5\t\u000f\t\u001dx\u000b1\u0001\u0002\u0000!*qk!\r\u0004VF:ad!\u0012\u0004X\u000eu\u0017'C\u0012\u0004^\r\u00154\u0011\\B4c%\u00193qOB=\u00077\u001cY'\r\u0004#\u0003\u0003Q8QP\u0019\u0004M\r]\u0002fB)\u00036\tm\"Q\b\u0002\u0007'>\u0014H/\u001a3\u0016\r\r\u001581^B}'\u0011Avpa:\u0011\u000b\u0005%\u0001a!;\u0011\t\u0005E11\u001e\u0003\b\u0003+A&\u0019AA\f!\u0015\u0011I\u0001FBu\u0003\ry'\u000f\u001a\t\u0007\u0007s\u0019\u0019pa>\n\t\rU8\u0011\t\u0002\t\u001fJ$WM]5oOB!\u0011\u0011CB}\t\u001d\tI\u0005\u0017b\u0001\u0007w\fBa!;\u0002 QA1q C\u0001\t\u0007!)\u0001E\u0004\u0003\na\u001bIoa>\t\u000f\t\u0015A\f1\u0001\u0004n\"91Q\u001a/A\u0002\u0005}\u0004bBBx9\u0002\u00071\u0011\u001f\u000b\u0007\u0007\u007f$I\u0001b\u0003\t\u000f\t\u0015Q\f1\u0001\u0004n\"91q^/A\u0002\rE(!\u0004*fm\u0016\u00148/Z*peR,Gm\u0005\u0003_\u007f\u000e\u001dHC\u0001C\n!\r!)BX\u0007\u00021\u0006IqL]3wKJ\u001cX\rZ\u000b\u0003\t7\u0001R!!/6\u0007S$Ba!;\u0005 !9!q]1A\u0002\u0005}TC\u0001C\u0012!\u0019\tIAa\t\u0004j\u0006\u0011Ao\\\u000b\u0005\tS!i\u0003\u0006\u0003\u0005,\u0011E\u0002\u0003BA\t\t[!q\u0001b\fg\u0005\u0004\t9B\u0001\u0002Dc!9A1\u00074A\u0002\u0011U\u0012a\u00024bGR|'/\u001f\t\t\u0003\u0013!9d!;\u0005,%\u0019A\u0011\b=\u0003\u000f\u0019\u000b7\r^8ssV\u00111q]\u0001\te\u00164XM]:fIV\u0011A\u0011\t\t\u0007\u0003\u0013!\u0019e!;\n\u0007\u0011\u0015\u0003P\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003\u0019\u0019xN\u001d;fIV!A1\nC+)\u0011\u00199\u000f\"\u0014\t\u000f\u0011=\u0013\u000eq\u0001\u0005R\u0005!qN\u001d32!\u0019\u0019Ida=\u0005TA!\u0011\u0011\u0003C+\t\u001d!9&\u001bb\u0001\u0007w\u0014!AQ\u0019)\u000fy\u0013)Da\u000f\u0003>\u0005IQM^1mk\u0006$X\r\u001a\u0015\u0004U\u0012}\u0003\u0003BA\u0001\tCJ1\u0001b\u0019{\u0005!1x\u000e\\1uS2,\u0017aB0t_J$X\rZ\u000b\u0003\tS\u0002b!!\u0003\u0005l\r%\u0018b\u0001C7q\n\u00191+Z9\u0002\u000b\u0015dW-\\:\u0016\u0005\r5H\u0003BBu\tkBqAa:n\u0001\u0004\ty(\u0006\u0003\u0005z\u0011uD\u0003\u0002C>\t\u007f\u0002B!!\u0005\u0005~\u00119Aq\u0006:C\u0002\u0005]\u0001b\u0002C\u001ae\u0002\u0007A\u0011\u0011\t\t\u0003\u0013!9d!;\u0005|U!AQ\u0011CG)\u0011\u00199\u000fb\"\t\u000f\u0011=S\u000fq\u0001\u0005\nB11\u0011HBz\t\u0017\u0003B!!\u0005\u0005\u000e\u00129AqK;C\u0002\rm\bf\u0002-\u00036\tm\"QH\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\t+\u0003B\u0001b&\u0005\u001e6\u0011A\u0011\u0014\u0006\u0005\t7\u000bI-\u0001\u0003mC:<\u0017\u0002\u0002CP\t3\u0013aa\u00142kK\u000e$\u0018aC1qa\u0016tG-\u001a3BY2,B\u0001\"*\u0005,R!Aq\u0015CW!\u0015\tI\u0001\u0001CU!\u0011\t\t\u0002b+\u0005\u000f\u0005%SB1\u0001\u0002b!9\u0011QW\u0007A\u0002\u0011=\u0006#BA])\u0011%\u0016\u0001\u00049sKB,g\u000eZ3e\u00032dW\u0003\u0002C[\tw#B\u0001b.\u0005>B)\u0011\u0011\u0002\u0001\u0005:B!\u0011\u0011\u0003C^\t\u001d\tIE\u0004b\u0001\u0003CBqA!0\u000f\u0001\u0004!y\fE\u0003\u0002:R!I,\u0006\u0003\u0005D\u0012-G\u0003BA\u001e\t\u000bDqaa<\u0010\u0001\b!9\r\u0005\u0004\u0004:\rMH\u0011\u001a\t\u0005\u0003#!Y\rB\u0004\u0002J=\u0011\r!!\u0019\u0002\u0019M$(/\u001b8h!J,g-\u001b="
)
public interface SeqView extends SeqOps, View {
   default SeqView view() {
      return this;
   }

   default SeqView map(final Function1 f) {
      return new Map(this, f);
   }

   default SeqView appended(final Object elem) {
      return new Appended(this, elem);
   }

   default SeqView prepended(final Object elem) {
      return new Prepended(elem, this);
   }

   default SeqView reverse() {
      return new Reverse(this);
   }

   default SeqView take(final int n) {
      return new Take(this, n);
   }

   default SeqView drop(final int n) {
      return new Drop(this, n);
   }

   default SeqView takeRight(final int n) {
      return new TakeRight(this, n);
   }

   default SeqView dropRight(final int n) {
      return new DropRight(this, n);
   }

   default SeqView tapEach(final Function1 f) {
      return new Map(this, (a) -> {
         f.apply(a);
         return a;
      });
   }

   default SeqView concat(final SeqOps suffix) {
      return new Concat(this, suffix);
   }

   default SeqView appendedAll(final SeqOps suffix) {
      return new Concat(this, suffix);
   }

   default SeqView prependedAll(final SeqOps prefix) {
      return new Concat(prefix, this);
   }

   default SeqView sorted(final Ordering ord) {
      return new Sorted(this, ord);
   }

   default String stringPrefix() {
      return "SeqView";
   }

   static void $init$(final SeqView $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Id extends AbstractSeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;

      public Object apply(final int idx) {
         return this.underlying.apply(idx);
      }

      public int length() {
         return this.underlying.length();
      }

      public Iterator iterator() {
         return this.underlying.iterator();
      }

      public int knownSize() {
         return this.underlying.knownSize();
      }

      public boolean isEmpty() {
         return this.underlying.isEmpty();
      }

      public Id(final SeqOps underlying) {
         this.underlying = underlying;
      }
   }

   public static class Map extends View.Map implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final Function1 f;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public Object apply(final int idx) {
         return this.f.apply(this.underlying.apply(idx));
      }

      public int length() {
         return this.underlying.length();
      }

      public Map(final SeqOps underlying, final Function1 f) {
         super(underlying, f);
         this.underlying = underlying;
         this.f = f;
      }
   }

   public static class Appended extends View.Appended implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final Object elem;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public Object apply(final int idx) {
         return idx == this.underlying.length() ? this.elem : this.underlying.apply(idx);
      }

      public int length() {
         return this.underlying.length() + 1;
      }

      public Appended(final SeqOps underlying, final Object elem) {
         super(underlying, elem);
         this.underlying = underlying;
         this.elem = elem;
      }
   }

   public static class Prepended extends View.Prepended implements SeqView {
      private static final long serialVersionUID = 3L;
      private final Object elem;
      private final SeqOps underlying;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public Object apply(final int idx) {
         return idx == 0 ? this.elem : this.underlying.apply(idx - 1);
      }

      public int length() {
         return this.underlying.length() + 1;
      }

      public Prepended(final Object elem, final SeqOps underlying) {
         super(elem, underlying);
         this.elem = elem;
         this.underlying = underlying;
      }
   }

   public static class Concat extends View.Concat implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps prefix;
      private final SeqOps suffix;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public Object apply(final int idx) {
         int l = this.prefix.length();
         return idx < l ? this.prefix.apply(idx) : this.suffix.apply(idx - l);
      }

      public int length() {
         return this.prefix.length() + this.suffix.length();
      }

      public Concat(final SeqOps prefix, final SeqOps suffix) {
         super(prefix, suffix);
         this.prefix = prefix;
         this.suffix = suffix;
      }
   }

   public static class Reverse extends AbstractSeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;

      public Object apply(final int i) {
         return this.underlying.apply(this.length() - 1 - i);
      }

      public int length() {
         SeqOps var10000 = this.underlying;
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.length();
         }
      }

      public Iterator iterator() {
         return this.underlying.reverseIterator();
      }

      public int knownSize() {
         return this.underlying.knownSize();
      }

      public boolean isEmpty() {
         return this.underlying.isEmpty();
      }

      public Reverse(final SeqOps underlying) {
         this.underlying = underlying;
      }
   }

   public static class Take extends View.Take implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final int n;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public Object apply(final int idx) {
         if (idx < this.n) {
            return this.underlying.apply(idx);
         } else if (this.underlying.knownSize() >= 0) {
            throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.knownSize() - 1);
         } else {
            throw CommonErrors$.MODULE$.indexOutOfBounds(idx);
         }
      }

      public int length() {
         RichInt$ var10000 = RichInt$.MODULE$;
         int var1 = this.underlying.length();
         int min$extension_that = this.normN();
         scala.math.package$ var3 = scala.math.package$.MODULE$;
         return Math.min(var1, min$extension_that);
      }

      public Take(final SeqOps underlying, final int n) {
         super(underlying, n);
         this.underlying = underlying;
         this.n = n;
      }
   }

   public static class TakeRight extends View.TakeRight implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final int delta;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public int length() {
         SeqOps var10000 = this.underlying;
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.length() - this.delta;
         }
      }

      public Object apply(final int i) throws IndexOutOfBoundsException {
         return this.underlying.apply(i + this.delta);
      }

      public TakeRight(final SeqOps underlying, final int n) {
         super(underlying, n);
         this.underlying = underlying;
         RichInt$ var10001 = RichInt$.MODULE$;
         if (underlying == null) {
            throw null;
         } else {
            int var6 = underlying.length();
            RichInt$ var10002 = RichInt$.MODULE$;
            int max$extension_that = 0;
            scala.math.package$ var8 = scala.math.package$.MODULE$;
            int var3 = var6 - Math.max(n, max$extension_that);
            int max$extension_that = 0;
            scala.math.package$ var7 = scala.math.package$.MODULE$;
            this.delta = Math.max(var3, max$extension_that);
         }
      }
   }

   public static class Drop extends View.Drop implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final int n;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public int length() {
         RichInt$ var10000 = RichInt$.MODULE$;
         SeqOps var3 = this.underlying;
         if (var3 == null) {
            throw null;
         } else {
            int var1 = var3.length() - this.normN();
            int max$extension_that = 0;
            scala.math.package$ var4 = scala.math.package$.MODULE$;
            return Math.max(var1, max$extension_that);
         }
      }

      public Object apply(final int i) throws IndexOutOfBoundsException {
         return this.underlying.apply(i + this.normN());
      }

      public SeqView drop(final int n) {
         return new Drop(this.underlying, this.n + n);
      }

      public Drop(final SeqOps underlying, final int n) {
         super(underlying, n);
         this.underlying = underlying;
         this.n = n;
      }
   }

   public static class DropRight extends View.DropRight implements SeqView {
      private static final long serialVersionUID = 3L;
      private final SeqOps underlying;
      private final int len;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView reverse() {
         return SeqView.super.reverse();
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public SeqView sorted(final Ordering ord) {
         return SeqView.super.sorted(ord);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      public int length() {
         return this.len;
      }

      public Object apply(final int i) throws IndexOutOfBoundsException {
         return this.underlying.apply(i);
      }

      public DropRight(final SeqOps underlying, final int n) {
         super(underlying, n);
         this.underlying = underlying;
         RichInt$ var10001 = RichInt$.MODULE$;
         if (underlying == null) {
            throw null;
         } else {
            int var6 = underlying.length();
            RichInt$ var10002 = RichInt$.MODULE$;
            int max$extension_that = 0;
            scala.math.package$ var8 = scala.math.package$.MODULE$;
            int var3 = var6 - Math.max(n, max$extension_that);
            int max$extension_that = 0;
            scala.math.package$ var7 = scala.math.package$.MODULE$;
            this.len = Math.max(var3, max$extension_that);
         }
      }
   }

   public static class Sorted implements SeqView {
      private static final long serialVersionUID = 3L;
      private Seq scala$collection$SeqView$Sorted$$_sorted;
      private SeqOps underlying;
      public final int scala$collection$SeqView$Sorted$$len;
      public final Ordering scala$collection$SeqView$Sorted$$ord;
      private volatile boolean evaluated;
      private volatile boolean bitmap$0;

      public SeqView view() {
         return SeqView.super.view();
      }

      public SeqView map(final Function1 f) {
         return SeqView.super.map(f);
      }

      public SeqView appended(final Object elem) {
         return SeqView.super.appended(elem);
      }

      public SeqView prepended(final Object elem) {
         return SeqView.super.prepended(elem);
      }

      public SeqView take(final int n) {
         return SeqView.super.take(n);
      }

      public SeqView drop(final int n) {
         return SeqView.super.drop(n);
      }

      public SeqView takeRight(final int n) {
         return SeqView.super.takeRight(n);
      }

      public SeqView dropRight(final int n) {
         return SeqView.super.dropRight(n);
      }

      public SeqView tapEach(final Function1 f) {
         return SeqView.super.tapEach(f);
      }

      public SeqView concat(final SeqOps suffix) {
         return SeqView.super.concat(suffix);
      }

      public SeqView appendedAll(final SeqOps suffix) {
         return SeqView.super.appendedAll(suffix);
      }

      public SeqView prependedAll(final SeqOps prefix) {
         return SeqView.super.prependedAll(prefix);
      }

      public String stringPrefix() {
         return SeqView.super.stringPrefix();
      }

      public IterableFactory iterableFactory() {
         return View.iterableFactory$(this);
      }

      public View empty() {
         return View.empty$(this);
      }

      public String toString() {
         return View.toString$(this);
      }

      /** @deprecated */
      public IndexedSeq force() {
         return View.force$(this);
      }

      /** @deprecated */
      public final Iterable toIterable() {
         return Iterable.toIterable$(this);
      }

      public final Iterable coll() {
         return Iterable.coll$(this);
      }

      /** @deprecated */
      public Iterable seq() {
         return Iterable.seq$(this);
      }

      public String className() {
         return Iterable.className$(this);
      }

      public final String collectionClassName() {
         return Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final Iterable that) {
         return Iterable.lazyZip$(this, that);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return IterableFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return IterableFactoryDefaults.newSpecificBuilder$(this);
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public Iterator reverseIterator() {
         return SeqOps.reverseIterator$(this);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public int lengthCompare(final int len) {
         return SeqOps.lengthCompare$(this, len);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public int lengthCompare(final Iterable that) {
         return SeqOps.lengthCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public scala.collection.mutable.Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return SeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return SeqOps.search$(this, elem, from, to, ord);
      }

      /** @deprecated */
      public final Iterable toTraversable() {
         return IterableOps.toTraversable$(this);
      }

      public boolean isTraversableAgain() {
         return IterableOps.isTraversableAgain$(this);
      }

      /** @deprecated */
      public final Object repr() {
         return IterableOps.repr$(this);
      }

      /** @deprecated */
      public IterableFactory companion() {
         return IterableOps.companion$(this);
      }

      public Object head() {
         return IterableOps.head$(this);
      }

      public Option headOption() {
         return IterableOps.headOption$(this);
      }

      public Object last() {
         return IterableOps.last$(this);
      }

      public Option lastOption() {
         return IterableOps.lastOption$(this);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      /** @deprecated */
      public View view(final int from, final int until) {
         return IterableOps.view$(this, from, until);
      }

      public Object transpose(final Function1 asIterable) {
         return IterableOps.transpose$(this, asIterable);
      }

      public Object filter(final Function1 pred) {
         return IterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return IterableOps.filterNot$(this, pred);
      }

      public scala.collection.WithFilter withFilter(final Function1 p) {
         return IterableOps.withFilter$(this, p);
      }

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object dropWhile(final Function1 p) {
         return IterableOps.dropWhile$(this, p);
      }

      public Iterator grouped(final int size) {
         return IterableOps.grouped$(this, size);
      }

      public Iterator sliding(final int size) {
         return IterableOps.sliding$(this, size);
      }

      public Iterator sliding(final int size, final int step) {
         return IterableOps.sliding$(this, size, step);
      }

      public Object tail() {
         return IterableOps.tail$(this);
      }

      public Object init() {
         return IterableOps.init$(this);
      }

      public Object slice(final int from, final int until) {
         return IterableOps.slice$(this, from, until);
      }

      public scala.collection.immutable.Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
         return IterableOps.groupMapReduce$(this, key, f, reduce);
      }

      public Object scan(final Object z, final Function2 op) {
         return IterableOps.scan$(this, z, op);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return IterableOps.scanLeft$(this, z, op);
      }

      public Object scanRight(final Object z, final Function2 op) {
         return IterableOps.scanRight$(this, z, op);
      }

      public Object flatMap(final Function1 f) {
         return IterableOps.flatMap$(this, f);
      }

      public Object flatten(final Function1 asIterable) {
         return IterableOps.flatten$(this, asIterable);
      }

      public Object collect(final PartialFunction pf) {
         return IterableOps.collect$(this, pf);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return IterableOps.partitionMap$(this, f);
      }

      public final Object $plus$plus(final IterableOnce suffix) {
         return IterableOps.$plus$plus$(this, suffix);
      }

      public Object zip(final IterableOnce that) {
         return IterableOps.zip$(this, that);
      }

      public Object zipWithIndex() {
         return IterableOps.zipWithIndex$(this);
      }

      public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
         return IterableOps.zipAll$(this, that, thisElem, thatElem);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return IterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return IterableOps.unzip3$(this, asTriple);
      }

      public Iterator tails() {
         return IterableOps.tails$(this);
      }

      public Iterator inits() {
         return IterableOps.inits$(this);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Object max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public scala.collection.immutable.Map toMap(final $less$colon$less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public scala.collection.immutable.Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public scala.collection.immutable.Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public scala.collection.immutable.IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      private Seq _sorted$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               int len = this.scala$collection$SeqView$Sorted$$len;
               Object var10001;
               if (len == 0) {
                  var10001 = Nil$.MODULE$;
               } else if (len == 1) {
                  List$ var8 = scala.package$.MODULE$.List();
                  ArraySeq apply_elems = ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{this.underlying.head()});
                  if (var8 == null) {
                     throw null;
                  }

                  var10001 = Nil$.MODULE$.prependedAll(apply_elems);
                  apply_elems = null;
               } else {
                  Object[] arr = new Object[len];
                  this.underlying.copyToArray(arr);
                  Arrays.sort(arr, this.scala$collection$SeqView$Sorted$$ord);
                  var10001 = ArraySeq$.MODULE$.unsafeWrapArray(arr);
               }

               scala.collection.immutable.AbstractSeq res = (scala.collection.immutable.AbstractSeq)var10001;
               this.evaluated = true;
               this.underlying = null;
               this.scala$collection$SeqView$Sorted$$_sorted = res;
               this.bitmap$0 = true;
            }
         } catch (Throwable var6) {
            throw var6;
         }

         return this.scala$collection$SeqView$Sorted$$_sorted;
      }

      public Seq scala$collection$SeqView$Sorted$$_sorted() {
         return !this.bitmap$0 ? this._sorted$lzycompute() : this.scala$collection$SeqView$Sorted$$_sorted;
      }

      public SeqOps scala$collection$SeqView$Sorted$$elems() {
         SeqOps orig = this.underlying;
         return (SeqOps)(this.evaluated ? this.scala$collection$SeqView$Sorted$$_sorted() : orig);
      }

      public Object apply(final int i) {
         return this.scala$collection$SeqView$Sorted$$_sorted().apply(i);
      }

      public int length() {
         return this.scala$collection$SeqView$Sorted$$len;
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator var2 = Iterator$.scala$collection$Iterator$$_empty;
         Function0 $plus$plus_xs = () -> this.scala$collection$SeqView$Sorted$$_sorted().iterator();
         if (var2 == null) {
            throw null;
         } else {
            return var2.concat($plus$plus_xs);
         }
      }

      public int knownSize() {
         return this.scala$collection$SeqView$Sorted$$len;
      }

      public boolean isEmpty() {
         return this.scala$collection$SeqView$Sorted$$len == 0;
      }

      public Object to(final Factory factory) {
         return this.scala$collection$SeqView$Sorted$$_sorted().to(factory);
      }

      public SeqView reverse() {
         return new ReverseSorted();
      }

      public Iterable reversed() {
         return new ReverseSorted();
      }

      public SeqView sorted(final Ordering ord1) {
         Ordering var2 = this.scala$collection$SeqView$Sorted$$ord;
         if (ord1 == null) {
            if (var2 == null) {
               return this;
            }
         } else if (ord1.equals(var2)) {
            return this;
         }

         if (ord1.isReverseOf(this.scala$collection$SeqView$Sorted$$ord)) {
            return this.reverse();
         } else {
            return new Sorted(this.scala$collection$SeqView$Sorted$$elems(), this.scala$collection$SeqView$Sorted$$len, ord1);
         }
      }

      public Sorted(final SeqOps underlying, final int len, final Ordering ord) {
         this.underlying = underlying;
         this.scala$collection$SeqView$Sorted$$len = len;
         this.scala$collection$SeqView$Sorted$$ord = ord;
         super();
         this.evaluated = false;
      }

      public Sorted(final SeqOps underlying, final Ordering ord) {
         this(underlying, underlying.length(), ord);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      private class ReverseSorted implements SeqView {
         private static final long serialVersionUID = 3L;
         private Reverse _reversed;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         public final Sorted $outer;

         public SeqView view() {
            return SeqView.super.view();
         }

         public SeqView map(final Function1 f) {
            return SeqView.super.map(f);
         }

         public SeqView appended(final Object elem) {
            return SeqView.super.appended(elem);
         }

         public SeqView prepended(final Object elem) {
            return SeqView.super.prepended(elem);
         }

         public SeqView take(final int n) {
            return SeqView.super.take(n);
         }

         public SeqView drop(final int n) {
            return SeqView.super.drop(n);
         }

         public SeqView takeRight(final int n) {
            return SeqView.super.takeRight(n);
         }

         public SeqView dropRight(final int n) {
            return SeqView.super.dropRight(n);
         }

         public SeqView tapEach(final Function1 f) {
            return SeqView.super.tapEach(f);
         }

         public SeqView concat(final SeqOps suffix) {
            return SeqView.super.concat(suffix);
         }

         public SeqView appendedAll(final SeqOps suffix) {
            return SeqView.super.appendedAll(suffix);
         }

         public SeqView prependedAll(final SeqOps prefix) {
            return SeqView.super.prependedAll(prefix);
         }

         public String stringPrefix() {
            return SeqView.super.stringPrefix();
         }

         public IterableFactory iterableFactory() {
            return View.iterableFactory$(this);
         }

         public View empty() {
            return View.empty$(this);
         }

         public String toString() {
            return View.toString$(this);
         }

         /** @deprecated */
         public IndexedSeq force() {
            return View.force$(this);
         }

         /** @deprecated */
         public final Iterable toIterable() {
            return Iterable.toIterable$(this);
         }

         public final Iterable coll() {
            return Iterable.coll$(this);
         }

         /** @deprecated */
         public Iterable seq() {
            return Iterable.seq$(this);
         }

         public String className() {
            return Iterable.className$(this);
         }

         public final String collectionClassName() {
            return Iterable.collectionClassName$(this);
         }

         public LazyZip2 lazyZip(final Iterable that) {
            return Iterable.lazyZip$(this, that);
         }

         public IterableOps fromSpecific(final IterableOnce coll) {
            return IterableFactoryDefaults.fromSpecific$(this, coll);
         }

         public Builder newSpecificBuilder() {
            return IterableFactoryDefaults.newSpecificBuilder$(this);
         }

         // $FF: synthetic method
         public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
            return IterableOps.concat$(this, suffix);
         }

         // $FF: synthetic method
         public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
            return IterableOps.sizeCompare$(this, otherSize);
         }

         // $FF: synthetic method
         public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
            return IterableOps.sizeCompare$(this, that);
         }

         public final Object $plus$colon(final Object elem) {
            return SeqOps.$plus$colon$(this, elem);
         }

         public final Object $colon$plus(final Object elem) {
            return SeqOps.$colon$plus$(this, elem);
         }

         public Object prependedAll(final IterableOnce prefix) {
            return SeqOps.prependedAll$(this, prefix);
         }

         public final Object $plus$plus$colon(final IterableOnce prefix) {
            return SeqOps.$plus$plus$colon$(this, prefix);
         }

         public Object appendedAll(final IterableOnce suffix) {
            return SeqOps.appendedAll$(this, suffix);
         }

         public final Object $colon$plus$plus(final IterableOnce suffix) {
            return SeqOps.$colon$plus$plus$(this, suffix);
         }

         public final Object concat(final IterableOnce suffix) {
            return SeqOps.concat$(this, suffix);
         }

         /** @deprecated */
         public final Object union(final Seq that) {
            return SeqOps.union$(this, that);
         }

         public final int size() {
            return SeqOps.size$(this);
         }

         public Object distinct() {
            return SeqOps.distinct$(this);
         }

         public Object distinctBy(final Function1 f) {
            return SeqOps.distinctBy$(this, f);
         }

         public Iterator reverseIterator() {
            return SeqOps.reverseIterator$(this);
         }

         public boolean startsWith(final IterableOnce that, final int offset) {
            return SeqOps.startsWith$(this, that, offset);
         }

         public int startsWith$default$2() {
            return SeqOps.startsWith$default$2$(this);
         }

         public boolean endsWith(final Iterable that) {
            return SeqOps.endsWith$(this, that);
         }

         public boolean isDefinedAt(final int idx) {
            return SeqOps.isDefinedAt$(this, idx);
         }

         public Object padTo(final int len, final Object elem) {
            return SeqOps.padTo$(this, len, elem);
         }

         public final int segmentLength(final Function1 p) {
            return SeqOps.segmentLength$(this, p);
         }

         public int segmentLength(final Function1 p, final int from) {
            return SeqOps.segmentLength$(this, p, from);
         }

         /** @deprecated */
         public final int prefixLength(final Function1 p) {
            return SeqOps.prefixLength$(this, p);
         }

         public int indexWhere(final Function1 p, final int from) {
            return SeqOps.indexWhere$(this, p, from);
         }

         public int indexWhere(final Function1 p) {
            return SeqOps.indexWhere$(this, p);
         }

         public int indexOf(final Object elem, final int from) {
            return SeqOps.indexOf$(this, elem, from);
         }

         public int indexOf(final Object elem) {
            return SeqOps.indexOf$(this, elem);
         }

         public int lastIndexOf(final Object elem, final int end) {
            return SeqOps.lastIndexOf$(this, elem, end);
         }

         public int lastIndexOf$default$2() {
            return SeqOps.lastIndexOf$default$2$(this);
         }

         public int lastIndexWhere(final Function1 p, final int end) {
            return SeqOps.lastIndexWhere$(this, p, end);
         }

         public int lastIndexWhere(final Function1 p) {
            return SeqOps.lastIndexWhere$(this, p);
         }

         public int indexOfSlice(final Seq that, final int from) {
            return SeqOps.indexOfSlice$(this, that, from);
         }

         public int indexOfSlice(final Seq that) {
            return SeqOps.indexOfSlice$(this, that);
         }

         public int lastIndexOfSlice(final Seq that, final int end) {
            return SeqOps.lastIndexOfSlice$(this, that, end);
         }

         public int lastIndexOfSlice(final Seq that) {
            return SeqOps.lastIndexOfSlice$(this, that);
         }

         public Option findLast(final Function1 p) {
            return SeqOps.findLast$(this, p);
         }

         public boolean containsSlice(final Seq that) {
            return SeqOps.containsSlice$(this, that);
         }

         public boolean contains(final Object elem) {
            return SeqOps.contains$(this, elem);
         }

         /** @deprecated */
         public Object reverseMap(final Function1 f) {
            return SeqOps.reverseMap$(this, f);
         }

         public Iterator permutations() {
            return SeqOps.permutations$(this);
         }

         public Iterator combinations(final int n) {
            return SeqOps.combinations$(this, n);
         }

         public Object sortWith(final Function2 lt) {
            return SeqOps.sortWith$(this, lt);
         }

         public Object sortBy(final Function1 f, final Ordering ord) {
            return SeqOps.sortBy$(this, f, ord);
         }

         public Range indices() {
            return SeqOps.indices$(this);
         }

         public final int sizeCompare(final int otherSize) {
            return SeqOps.sizeCompare$(this, otherSize);
         }

         public int lengthCompare(final int len) {
            return SeqOps.lengthCompare$(this, len);
         }

         public final int sizeCompare(final Iterable that) {
            return SeqOps.sizeCompare$(this, that);
         }

         public int lengthCompare(final Iterable that) {
            return SeqOps.lengthCompare$(this, that);
         }

         public final IterableOps lengthIs() {
            return SeqOps.lengthIs$(this);
         }

         public boolean sameElements(final IterableOnce that) {
            return SeqOps.sameElements$(this, that);
         }

         public boolean corresponds(final Seq that, final Function2 p) {
            return SeqOps.corresponds$(this, that, p);
         }

         public Object diff(final Seq that) {
            return SeqOps.diff$(this, that);
         }

         public Object intersect(final Seq that) {
            return SeqOps.intersect$(this, that);
         }

         public Object patch(final int from, final IterableOnce other, final int replaced) {
            return SeqOps.patch$(this, from, other, replaced);
         }

         public Object updated(final int index, final Object elem) {
            return SeqOps.updated$(this, index, elem);
         }

         public scala.collection.mutable.Map occCounts(final Seq sq) {
            return SeqOps.occCounts$(this, sq);
         }

         public Searching.SearchResult search(final Object elem, final Ordering ord) {
            return SeqOps.search$(this, elem, ord);
         }

         public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
            return SeqOps.search$(this, elem, from, to, ord);
         }

         /** @deprecated */
         public final Iterable toTraversable() {
            return IterableOps.toTraversable$(this);
         }

         public boolean isTraversableAgain() {
            return IterableOps.isTraversableAgain$(this);
         }

         /** @deprecated */
         public final Object repr() {
            return IterableOps.repr$(this);
         }

         /** @deprecated */
         public IterableFactory companion() {
            return IterableOps.companion$(this);
         }

         public Object head() {
            return IterableOps.head$(this);
         }

         public Option headOption() {
            return IterableOps.headOption$(this);
         }

         public Object last() {
            return IterableOps.last$(this);
         }

         public Option lastOption() {
            return IterableOps.lastOption$(this);
         }

         public final IterableOps sizeIs() {
            return IterableOps.sizeIs$(this);
         }

         /** @deprecated */
         public View view(final int from, final int until) {
            return IterableOps.view$(this, from, until);
         }

         public Object transpose(final Function1 asIterable) {
            return IterableOps.transpose$(this, asIterable);
         }

         public Object filter(final Function1 pred) {
            return IterableOps.filter$(this, pred);
         }

         public Object filterNot(final Function1 pred) {
            return IterableOps.filterNot$(this, pred);
         }

         public scala.collection.WithFilter withFilter(final Function1 p) {
            return IterableOps.withFilter$(this, p);
         }

         public Tuple2 partition(final Function1 p) {
            return IterableOps.partition$(this, p);
         }

         public Tuple2 splitAt(final int n) {
            return IterableOps.splitAt$(this, n);
         }

         public Object takeWhile(final Function1 p) {
            return IterableOps.takeWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return IterableOps.span$(this, p);
         }

         public Object dropWhile(final Function1 p) {
            return IterableOps.dropWhile$(this, p);
         }

         public Iterator grouped(final int size) {
            return IterableOps.grouped$(this, size);
         }

         public Iterator sliding(final int size) {
            return IterableOps.sliding$(this, size);
         }

         public Iterator sliding(final int size, final int step) {
            return IterableOps.sliding$(this, size, step);
         }

         public Object tail() {
            return IterableOps.tail$(this);
         }

         public Object init() {
            return IterableOps.init$(this);
         }

         public Object slice(final int from, final int until) {
            return IterableOps.slice$(this, from, until);
         }

         public scala.collection.immutable.Map groupBy(final Function1 f) {
            return IterableOps.groupBy$(this, f);
         }

         public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
            return IterableOps.groupMap$(this, key, f);
         }

         public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
            return IterableOps.groupMapReduce$(this, key, f, reduce);
         }

         public Object scan(final Object z, final Function2 op) {
            return IterableOps.scan$(this, z, op);
         }

         public Object scanLeft(final Object z, final Function2 op) {
            return IterableOps.scanLeft$(this, z, op);
         }

         public Object scanRight(final Object z, final Function2 op) {
            return IterableOps.scanRight$(this, z, op);
         }

         public Object flatMap(final Function1 f) {
            return IterableOps.flatMap$(this, f);
         }

         public Object flatten(final Function1 asIterable) {
            return IterableOps.flatten$(this, asIterable);
         }

         public Object collect(final PartialFunction pf) {
            return IterableOps.collect$(this, pf);
         }

         public Tuple2 partitionMap(final Function1 f) {
            return IterableOps.partitionMap$(this, f);
         }

         public final Object $plus$plus(final IterableOnce suffix) {
            return IterableOps.$plus$plus$(this, suffix);
         }

         public Object zip(final IterableOnce that) {
            return IterableOps.zip$(this, that);
         }

         public Object zipWithIndex() {
            return IterableOps.zipWithIndex$(this);
         }

         public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
            return IterableOps.zipAll$(this, that, thisElem, thatElem);
         }

         public Tuple2 unzip(final Function1 asPair) {
            return IterableOps.unzip$(this, asPair);
         }

         public Tuple3 unzip3(final Function1 asTriple) {
            return IterableOps.unzip3$(this, asTriple);
         }

         public Iterator tails() {
            return IterableOps.tails$(this);
         }

         public Iterator inits() {
            return IterableOps.inits$(this);
         }

         /** @deprecated */
         public boolean hasDefiniteSize() {
            return IterableOnceOps.hasDefiniteSize$(this);
         }

         public void foreach(final Function1 f) {
            IterableOnceOps.foreach$(this, f);
         }

         public boolean forall(final Function1 p) {
            return IterableOnceOps.forall$(this, p);
         }

         public boolean exists(final Function1 p) {
            return IterableOnceOps.exists$(this, p);
         }

         public int count(final Function1 p) {
            return IterableOnceOps.count$(this, p);
         }

         public Option find(final Function1 p) {
            return IterableOnceOps.find$(this, p);
         }

         public Object foldLeft(final Object z, final Function2 op) {
            return IterableOnceOps.foldLeft$(this, z, op);
         }

         public Object foldRight(final Object z, final Function2 op) {
            return IterableOnceOps.foldRight$(this, z, op);
         }

         /** @deprecated */
         public final Object $div$colon(final Object z, final Function2 op) {
            return IterableOnceOps.$div$colon$(this, z, op);
         }

         /** @deprecated */
         public final Object $colon$bslash(final Object z, final Function2 op) {
            return IterableOnceOps.$colon$bslash$(this, z, op);
         }

         public Object fold(final Object z, final Function2 op) {
            return IterableOnceOps.fold$(this, z, op);
         }

         public Object reduce(final Function2 op) {
            return IterableOnceOps.reduce$(this, op);
         }

         public Option reduceOption(final Function2 op) {
            return IterableOnceOps.reduceOption$(this, op);
         }

         public Object reduceLeft(final Function2 op) {
            return IterableOnceOps.reduceLeft$(this, op);
         }

         public Object reduceRight(final Function2 op) {
            return IterableOnceOps.reduceRight$(this, op);
         }

         public Option reduceLeftOption(final Function2 op) {
            return IterableOnceOps.reduceLeftOption$(this, op);
         }

         public Option reduceRightOption(final Function2 op) {
            return IterableOnceOps.reduceRightOption$(this, op);
         }

         public boolean nonEmpty() {
            return IterableOnceOps.nonEmpty$(this);
         }

         /** @deprecated */
         public final void copyToBuffer(final Buffer dest) {
            IterableOnceOps.copyToBuffer$(this, dest);
         }

         public int copyToArray(final Object xs) {
            return IterableOnceOps.copyToArray$(this, xs);
         }

         public int copyToArray(final Object xs, final int start) {
            return IterableOnceOps.copyToArray$(this, xs, start);
         }

         public int copyToArray(final Object xs, final int start, final int len) {
            return IterableOnceOps.copyToArray$(this, xs, start, len);
         }

         public Object sum(final Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final Numeric num) {
            return IterableOnceOps.product$(this, num);
         }

         public Object min(final Ordering ord) {
            return IterableOnceOps.min$(this, ord);
         }

         public Option minOption(final Ordering ord) {
            return IterableOnceOps.minOption$(this, ord);
         }

         public Object max(final Ordering ord) {
            return IterableOnceOps.max$(this, ord);
         }

         public Option maxOption(final Ordering ord) {
            return IterableOnceOps.maxOption$(this, ord);
         }

         public Object maxBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxBy$(this, f, ord);
         }

         public Option maxByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.maxByOption$(this, f, ord);
         }

         public Object minBy(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minBy$(this, f, ord);
         }

         public Option minByOption(final Function1 f, final Ordering ord) {
            return IterableOnceOps.minByOption$(this, f, ord);
         }

         public Option collectFirst(final PartialFunction pf) {
            return IterableOnceOps.collectFirst$(this, pf);
         }

         /** @deprecated */
         public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
            return IterableOnceOps.aggregate$(this, z, seqop, combop);
         }

         public boolean corresponds(final IterableOnce that, final Function2 p) {
            return IterableOnceOps.corresponds$(this, that, p);
         }

         public final String mkString(final String start, final String sep, final String end) {
            return IterableOnceOps.mkString$(this, start, sep, end);
         }

         public final String mkString(final String sep) {
            return IterableOnceOps.mkString$(this, sep);
         }

         public final String mkString() {
            return IterableOnceOps.mkString$(this);
         }

         public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
            return IterableOnceOps.addString$(this, b, start, sep, end);
         }

         public final StringBuilder addString(final StringBuilder b, final String sep) {
            return IterableOnceOps.addString$(this, b, sep);
         }

         public final StringBuilder addString(final StringBuilder b) {
            return IterableOnceOps.addString$(this, b);
         }

         /** @deprecated */
         public final Iterator toIterator() {
            return IterableOnceOps.toIterator$(this);
         }

         public List toList() {
            return IterableOnceOps.toList$(this);
         }

         public Vector toVector() {
            return IterableOnceOps.toVector$(this);
         }

         public scala.collection.immutable.Map toMap(final $less$colon$less ev) {
            return IterableOnceOps.toMap$(this, ev);
         }

         public scala.collection.immutable.Set toSet() {
            return IterableOnceOps.toSet$(this);
         }

         public scala.collection.immutable.Seq toSeq() {
            return IterableOnceOps.toSeq$(this);
         }

         public scala.collection.immutable.IndexedSeq toIndexedSeq() {
            return IterableOnceOps.toIndexedSeq$(this);
         }

         /** @deprecated */
         public final Stream toStream() {
            return IterableOnceOps.toStream$(this);
         }

         public final Buffer toBuffer() {
            return IterableOnceOps.toBuffer$(this);
         }

         public Object toArray(final ClassTag evidence$2) {
            return IterableOnceOps.toArray$(this, evidence$2);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         private Reverse _reversed$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this._reversed = new Reverse(this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$_sorted());
                  this.bitmap$0 = true;
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this._reversed;
         }

         private Reverse _reversed() {
            return !this.bitmap$0 ? this._reversed$lzycompute() : this._reversed;
         }

         public Object apply(final int i) {
            return this._reversed().apply(i);
         }

         public int length() {
            return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$len;
         }

         public Iterator iterator() {
            Iterator$ var10000 = Iterator$.MODULE$;
            Iterator var2 = Iterator$.scala$collection$Iterator$$_empty;
            Function0 $plus$plus_xs = () -> this._reversed().iterator();
            if (var2 == null) {
               throw null;
            } else {
               return var2.concat($plus$plus_xs);
            }
         }

         public int knownSize() {
            return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$len;
         }

         public boolean isEmpty() {
            return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$len == 0;
         }

         public Object to(final Factory factory) {
            return this._reversed().to(factory);
         }

         public SeqView reverse() {
            return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer();
         }

         public Iterable reversed() {
            return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer();
         }

         public SeqView sorted(final Ordering ord1) {
            Ordering var2 = this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$ord;
            if (ord1 == null) {
               if (var2 == null) {
                  return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer();
               }
            } else if (ord1.equals(var2)) {
               return this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer();
            }

            if (ord1.isReverseOf(this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$ord)) {
               return this;
            } else {
               return new Sorted(this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$elems(), this.scala$collection$SeqView$Sorted$ReverseSorted$$$outer().scala$collection$SeqView$Sorted$$len, ord1);
            }
         }

         // $FF: synthetic method
         public Sorted scala$collection$SeqView$Sorted$ReverseSorted$$$outer() {
            return this.$outer;
         }

         public ReverseSorted() {
            if (Sorted.this == null) {
               throw null;
            } else {
               this.$outer = Sorted.this;
               super();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      }
   }
}
