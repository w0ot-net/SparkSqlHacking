package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.AbstractIterable;
import scala.collection.AbstractIterator;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019%a\u0001DAD\u0003\u0013\u0003\n1!\u0001\u0002\u0018\u001a\u0005\u0001bBAV\u0001\u0011\u0005\u0011Q\u0016\u0005\f\u0003k\u0003\u0001\u0019!C\u0001\u0003#\u000b9\fC\u0006\u0002@\u0002\u0001\r\u0011\"\u0001\u0002\u0012\u0006\u0005g!CAd\u0001A\u0005\u0019\u0011EAe\u0011\u001d\tY\u000b\u0002C\u0001\u0003[Cq!a3\u0005\r\u0003\ti\rC\u0004\u0002\\\u0012!\t!!8\u0007\r\tM\u0007\u0001\u0011Bk\u0011)\u00119\u000e\u0003BK\u0002\u0013\u0005!\u0011\u001c\u0005\u000b\u0005KD!\u0011#Q\u0001\n\tm\u0007BCAf\u0011\tU\r\u0011\"\u0001\u0002N\"Q!Q\u0014\u0005\u0003\u0012\u0003\u0006I!a4\t\u000f\t\u0005\u0002\u0002\"\u0001\u0003h\"9\u00111\u001c\u0005\u0005B\u0005u\u0007\"\u0003B\u0019\u0011\u0005\u0005I\u0011\u0001Bx\u0011%\u00119\u0004CI\u0001\n\u0003\u0011)\u0010C\u0005\u00032\"\t\n\u0011\"\u0001\u0003.\"I!q\n\u0005\u0002\u0002\u0013\u0005#\u0011\u000b\u0005\n\u0005CB\u0011\u0011!C\u0001\u0003oC\u0011Ba\u0019\t\u0003\u0003%\tA!?\t\u0013\t=\u0004\"!A\u0005B\tE\u0004\"\u0003B@\u0011\u0005\u0005I\u0011\u0001B\u007f\u0011%\u0011)\tCA\u0001\n\u0003\u001a\t\u0001C\u0005\u0003\f\"\t\t\u0011\"\u0011\u0003\u000e\"I!q\u0012\u0005\u0002\u0002\u0013\u0005#\u0011\u0013\u0005\n\u0005'C\u0011\u0011!C!\u0007\u000b9\u0011b!\u0003\u0001\u0003\u0003E\taa\u0003\u0007\u0013\tM\u0007!!A\t\u0002\r5\u0001b\u0002B\u00119\u0011\u00051Q\u0005\u0005\n\u0005\u001fc\u0012\u0011!C#\u0005#C\u0011ba\n\u001d\u0003\u0003%\ti!\u000b\t\u0013\r=B$!A\u0005\u0002\u000eEbABAt\u0001\u0001\u000bI\u000f\u0003\u0006\u0003\f\u0005\u0012)\u001a!C\u0001\u0005\u001bA!Ba\b\"\u0005#\u0005\u000b\u0011\u0002B\b\u0011\u001d\u0011\t#\tC\u0001\u0005GAq!a3\"\t\u0003\u0011I\u0003C\u0005\u00032\u0005\n\t\u0011\"\u0001\u00034!I!qG\u0011\u0012\u0002\u0013\u0005!\u0011\b\u0005\n\u0005\u001f\n\u0013\u0011!C!\u0005#B\u0011B!\u0019\"\u0003\u0003%\t!a.\t\u0013\t\r\u0014%!A\u0005\u0002\t\u0015\u0004\"\u0003B8C\u0005\u0005I\u0011\tB9\u0011%\u0011y(IA\u0001\n\u0003\u0011\t\tC\u0005\u0003\u0006\u0006\n\t\u0011\"\u0011\u0003\b\"I!1R\u0011\u0002\u0002\u0013\u0005#Q\u0012\u0005\n\u0005\u001f\u000b\u0013\u0011!C!\u0005#C\u0011Ba%\"\u0003\u0003%\tE!&\b\u0013\r\r\u0003!!A\t\u0002\r\u0015c!CAt\u0001\u0005\u0005\t\u0012AB$\u0011\u001d\u0011\tC\rC\u0001\u0007\u001fB\u0011Ba$3\u0003\u0003%)E!%\t\u0013\r\u001d\"'!A\u0005\u0002\u000eE\u0003\"CB\u0018e\u0005\u0005I\u0011QB+\r\u0019\u0011I\n\u0001!\u0003\u001c\"Q\u00111Z\u001c\u0003\u0016\u0004%\t!!4\t\u0015\tuuG!E!\u0002\u0013\ty\r\u0003\u0006\u0003\f]\u0012)\u001a!C\u0001\u0005\u001bA!Ba\b8\u0005#\u0005\u000b\u0011\u0002B\b\u0011\u001d\u0011\tc\u000eC\u0001\u0005?C\u0011B!\r8\u0003\u0003%\tAa*\t\u0013\t]r'%A\u0005\u0002\t5\u0006\"\u0003BYoE\u0005I\u0011\u0001B\u001d\u0011%\u0011yeNA\u0001\n\u0003\u0012\t\u0006C\u0005\u0003b]\n\t\u0011\"\u0001\u00028\"I!1M\u001c\u0002\u0002\u0013\u0005!1\u0017\u0005\n\u0005_:\u0014\u0011!C!\u0005cB\u0011Ba 8\u0003\u0003%\tAa.\t\u0013\t\u0015u'!A\u0005B\tm\u0006\"\u0003BFo\u0005\u0005I\u0011\tBG\u0011%\u0011yiNA\u0001\n\u0003\u0012\t\nC\u0005\u0003\u0014^\n\t\u0011\"\u0011\u0003@\u001eI11\f\u0001\u0002\u0002#\u00051Q\f\u0004\n\u00053\u0003\u0011\u0011!E\u0001\u0007?BqA!\tK\t\u0003\u0019\u0019\u0007C\u0005\u0003\u0010*\u000b\t\u0011\"\u0012\u0003\u0012\"I1q\u0005&\u0002\u0002\u0013\u00055Q\r\u0005\n\u0007_Q\u0015\u0011!CA\u0007W:qaa\u001d\u0001\u0011\u0003\u0013IMB\u0004\u0003D\u0002A\tI!2\t\u000f\t\u0005\u0002\u000b\"\u0001\u0003H\"9\u00111\u001a)\u0005\u0002\t%\u0002\"\u0003B(!\u0006\u0005I\u0011\tB)\u0011%\u0011\t\u0007UA\u0001\n\u0003\t9\fC\u0005\u0003dA\u000b\t\u0011\"\u0001\u0003L\"I!q\u000e)\u0002\u0002\u0013\u0005#\u0011\u000f\u0005\n\u0005\u007f\u0002\u0016\u0011!C\u0001\u0005\u001fD\u0011Ba#Q\u0003\u0003%\tE!$\t\u0013\t=\u0005+!A\u0005B\tEeABB;\u0001\u0001\u00199\b\u0003\u0006\u0004zi\u0013\t\u0019!C\u0001\u0003\u001bD!ba\u001f[\u0005\u0003\u0007I\u0011AB?\u0011)\u0019\tI\u0017B\u0001B\u0003&\u0011q\u001a\u0005\u000b\u0007\u0007S&Q1A\u0005\u0002\r\u0015\u0005BCC\u001f5\n\u0005\t\u0015!\u0003\u0004\b\"9!\u0011\u0005.\u0005\u0002\u0015}\u0002\"CC#5\u0002\u0007I\u0011ABR\u0011%)9E\u0017a\u0001\n\u0003)I\u0005\u0003\u0005\u0006Ni\u0003\u000b\u0015BBS\u0011%!yB\u0017a\u0001\n\u0003\u0019\u0019\u000bC\u0005\u0006Pi\u0003\r\u0011\"\u0001\u0006R!AQQ\u000b.!B\u0013\u0019)\u000bC\u0004\u0006Xi#\t!a.\t\u000f\t-%\f\"\u0011\u0003\u000e\"9!q\u0012.\u0005B\tE\u0005b\u0002C55\u0012\u0015Q\u0011\f\u0005\b\u000b?\u0002A\u0011BC1\u000f\u001d)9\u0007\u0001E\u0001\u000bS2qa!#\u0001\u0011\u0003)Y\u0007C\u0004\u0003\"5$\t!\"\u001c\t\u000f\u0015=T\u000e\"\u0001\u0006r\u001911\u0011\u0012\u0001\u0001\u0007\u0017C\u0001B!\tq\t#\u00011q\u0014\u0005\u000e\u0007C\u0003\b\u0019!a\u0001\n\u0003\t\tja)\t\u001b\r\u001d\u0006\u000f1AA\u0002\u0013\u0005\u0011\u0011SBU\u0011-\u0019i\u000b\u001da\u0001\u0002\u0003\u0006Ka!*\t\u0017\r=\u0006O!A\u0001B\u0003&\u0011\u0011\u0018\u0005\f\u0007c\u0003(\u0011!A!B\u0013\u0019\u0019\f\u0003\u0005\u0004:B\u0004\u000b\u0015BB^\u0011!\u0019\u0019\r\u001dQ!\n\u0005e\u0006bBBca\u0012%\u0011Q\u0016\u0005\n\u0007\u000f\u0004(\u0019!C\u0007\u0007\u0013D\u0001ba4qA\u0003511\u001a\u0005\n\u0007#\u0004(\u0019!C\u0007\u0007'D\u0001b!7qA\u000351Q\u001b\u0005\n\u00077\u0004(\u0019!C\u0007\u0007;D\u0001ba9qA\u000351q\u001c\u0005\b\u0007K\u0004H\u0011ABC\u0011\u001d\u00199\u000f\u001dC!\u0003;Dqa!;q\t\u0003\n9\fC\u0004\u0004lB$I!a.\t\u000f\r5\b\u000f\"\u0005\u0004p\"91Q\u001f9\u0005\n\r]\bbBB~a\u0012\u00051Q \u0005\b\t+\u0001HQ\u0001C\f\u0011\u001d!\t\u0003\u001dC\u0001\tGAq\u0001b\nq\t\u0003!I\u0003C\u0004\u00054A$I!!,\t\u000f\u0011U\u0002\u000f\"\u0003\u00058!IAq\b9\u0012\u0002\u0013%A\u0011\t\u0005\b\t\u000b\u0002H\u0011\u0001C$\u0011\u001d!I\u0006\u001dC\u0001\t7Bq\u0001\"\u0017q\t\u0003!y\u0006C\u0004\u0005dA$\t\u0001\"\u001a\t\u000f\u0011-\u0004\u000f\"\u0001\u0005n!9A\u0011\u000f9\u0005\u0002\u0011M\u0004b\u0002C<a\u0012\u0005A\u0011\u0010\u0005\b\t{\u0002H\u0011\u0001C@\u0011\u001d!9\t\u001dC\u0001\t\u0013Cq\u0001b$q\t\u0003!\t\nC\u0004\u0005\u0016B$)\u0001b&\t\u000f\u0011m\u0005\u000f\"\u0001\u0005\u001e\"9A\u0011\u00159\u0005\u0002\u0011\r\u0006b\u0002CUa\u0012\u0015A1\u0016\u0005\b\tk\u0003H\u0011\u0001C\\\u0011\u001d!i\f\u001dC\u0001\t\u007fCq\u0001b1q\t\u0003\")\rC\u0004\u0005HB$\t\u0001\"2\t\u000f\u0011%\u0007\u000f\"\u0001\u00028\"9A1\u001a9\u0005\u0002\u00115\u0007b\u0002Cha\u0012\u0005AQ\u001a\u0005\b\t#\u0004H\u0011\tCj\u0011\u001d!I\u000f\u001dC!\tWDq\u0001\"=q\t\u0003\"\u0019\u0010C\u0004\u0005xB$\t\u0001\"2\t\u000f\u00155\u0001\u000f\"\u0011\u0006\u0010!9!q\u00129\u0005B\u0015%\u0002\u0002DC\u0016a\n\u0005\t\u0019!C\u0001\u0001\u0005]\u0006\u0002DC\u0017a\n\u0005\t\u0019!C\u0001\u0001\u0015=\u0002\u0002DC\u001aa\n\u0005\t\u0019!C\u0001\u0001\u0015U\u0002\u0002DC\u001ca\n\u0005\t\u0019!C\u0001\u0001\u0015e\u0002\"CCB\u0001\t\u0007I1ACC\u000b\u0019)y\t\u0001\u0001\u0004\b\"IQ\u0011\u0013\u0001C\u0002\u0013\rQ1\u0013\u0005\b\u000b3\u0003A\u0011ABC\u0011%)Y\n\u0001C\u0001\u0003#\u001b)\tC\u0004\u0006\u001e\u0002!)!b(\t\u000f\u0015\u0015\u0006\u0001\"\u0001\u0006(\"9Q\u0011\u0017\u0001\u0005\u0002\u0015M\u0006bBC]\u0001\u0011\u0005Q1X\u0004\b\u000b\u0017\u0004\u0001\u0012ACg\r\u001d)y\r\u0001E\u0001\u000b#D\u0001B!\t\u0002n\u0011\u0005Q1\u001b\u0005\t\u0007[\fi\u0007\"\u0011\u0006V\u001a1Q\u0011\u001c\u0001\u0001\u000b7D1ba!\u0002t\t\u0005\t\u0015!\u0003\u0002P\"A!\u0011EA:\t\u0003)i\u000eC\u0005\u0006d\u0002\u0011\r\u0011\"\u0004\u0006f\u001a9Q1\u001e\u0001\u0001\u0001\u00155\bbCBB\u0003w\u0012\t\u0011)A\u0005\u0007\u000fC\u0001B!\t\u0002|\u0011\u0005QQ\u001f\u0005\n\u000bw\fY\b)Q\u0005\u0007KC\u0001\"\"@\u0002|\u0011\u0005\u0011Q\u001c\u0005\t\t?\tY\b\"\u0001\u0006\u0000\n11kY8qKNTA!a#\u0002\u000e\u0006A\u0011N\u001c;fe:\fGN\u0003\u0003\u0002\u0010\u0006E\u0015a\u0002:fM2,7\r\u001e\u0006\u0003\u0003'\u000bQa]2bY\u0006\u001c\u0001aE\u0003\u0001\u00033\u000b\t\u000b\u0005\u0003\u0002\u001c\u0006uUBAAI\u0013\u0011\ty*!%\u0003\r\u0005s\u0017PU3g!\u0011\t\u0019+!+\u000e\u0005\u0005\u0015&\u0002BAT\u0003\u001b\u000b1!\u00199j\u0013\u0011\t9)!*\u0002\r\u0011Jg.\u001b;%)\t\ty\u000b\u0005\u0003\u0002\u001c\u0006E\u0016\u0002BAZ\u0003#\u0013A!\u00168ji\u0006Q1oY8qK\u000e{WO\u001c;\u0016\u0005\u0005e\u0006\u0003BAN\u0003wKA!!0\u0002\u0012\n\u0019\u0011J\u001c;\u0002\u001dM\u001cw\u000e]3D_VtGo\u0018\u0013fcR!\u0011qVAb\u0011%\t)mAA\u0001\u0002\u0004\tI,A\u0002yIE\u0012!BT1nK2{wn[;q'\r!\u0011\u0011T\u0001\u0007gfl'm\u001c7\u0016\u0005\u0005=\u0007\u0003BAi\u0003'l\u0011\u0001A\u0005\u0005\u0003+\f9N\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0005\u00033\fIIA\u0004Ts6\u0014w\u000e\\:\u0002\u0013%\u001c8+^2dKN\u001cXCAAp!\u0011\tY*!9\n\t\u0005\r\u0018\u0011\u0013\u0002\b\u0005>|G.Z1oS\u0015!\u0011e\u000e)\t\u0005=aun\\6va\u0006k'-[4v_V\u001c8#C\u0011\u0002\u001a\u0006-\u0018Q^Az!\r\t\t\u000e\u0002\t\u0005\u00037\u000by/\u0003\u0003\u0002r\u0006E%a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003k\u0014)A\u0004\u0003\u0002x\n\u0005a\u0002BA}\u0003\u007fl!!a?\u000b\t\u0005u\u0018QS\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005M\u0015\u0002\u0002B\u0002\u0003#\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0003\b\t%!\u0001D*fe&\fG.\u001b>bE2,'\u0002\u0002B\u0002\u0003#\u000b1!\\:h+\t\u0011y\u0001\u0005\u0003\u0003\u0012\tea\u0002\u0002B\n\u0005+\u0001B!!?\u0002\u0012&!!qCAI\u0003\u0019\u0001&/\u001a3fM&!!1\u0004B\u000f\u0005\u0019\u0019FO]5oO*!!qCAI\u0003\u0011i7o\u001a\u0011\u0002\rqJg.\u001b;?)\u0011\u0011)Ca\n\u0011\u0007\u0005E\u0017\u0005C\u0004\u0003\f\u0011\u0002\rAa\u0004\u0016\u0005\t-\u0002\u0003BAi\u0005[IAAa\f\u0002X\nAaj\\*z[\n|G.\u0001\u0003d_BLH\u0003\u0002B\u0013\u0005kA\u0011Ba\u0003'!\u0003\u0005\rAa\u0004\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!1\b\u0016\u0005\u0005\u001f\u0011id\u000b\u0002\u0003@A!!\u0011\tB&\u001b\t\u0011\u0019E\u0003\u0003\u0003F\t\u001d\u0013!C;oG\",7m[3e\u0015\u0011\u0011I%!%\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003N\t\r#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"Aa\u0015\u0011\t\tU#qL\u0007\u0003\u0005/RAA!\u0017\u0003\\\u0005!A.\u00198h\u0015\t\u0011i&\u0001\u0003kCZ\f\u0017\u0002\u0002B\u000e\u0005/\nA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003h\t5\u0004\u0003BAN\u0005SJAAa\u001b\u0002\u0012\n\u0019\u0011I\\=\t\u0013\u0005\u0015'&!AA\u0002\u0005e\u0016a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\tM\u0004C\u0002B;\u0005w\u00129'\u0004\u0002\u0003x)!!\u0011PAI\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005{\u00129H\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAp\u0005\u0007C\u0011\"!2-\u0003\u0003\u0005\rAa\u001a\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005'\u0012I\tC\u0005\u0002F6\n\t\u00111\u0001\u0002:\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002:\u0006AAo\\*ue&tw\r\u0006\u0002\u0003T\u00051Q-];bYN$B!a8\u0003\u0018\"I\u0011Q\u0019\u0019\u0002\u0002\u0003\u0007!q\r\u0002\u0013\u0019>|7.\u001e9J]\u0006\u001c7-Z:tS\ndWmE\u00058\u00033\u000bY/!<\u0002t\u000691/_7c_2\u0004CC\u0002BQ\u0005G\u0013)\u000bE\u0002\u0002R^Bq!a3=\u0001\u0004\ty\rC\u0004\u0003\fq\u0002\rAa\u0004\u0015\r\t\u0005&\u0011\u0016BV\u0011%\tY-\u0010I\u0001\u0002\u0004\ty\rC\u0005\u0003\fu\u0002\n\u00111\u0001\u0003\u0010U\u0011!q\u0016\u0016\u0005\u0003\u001f\u0014i$\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0015\t\t\u001d$Q\u0017\u0005\n\u0003\u000b\u0014\u0015\u0011!a\u0001\u0003s#B!a8\u0003:\"I\u0011Q\u0019#\u0002\u0002\u0003\u0007!q\r\u000b\u0005\u0005'\u0012i\fC\u0005\u0002F\u0016\u000b\t\u00111\u0001\u0002:R!\u0011q\u001cBa\u0011%\t)\rSA\u0001\u0002\u0004\u00119G\u0001\bM_>\\W\u000f\u001d(pi\u001a{WO\u001c3\u0014\u0013A\u000bI*a;\u0002n\u0006MHC\u0001Be!\r\t\t\u000e\u0015\u000b\u0005\u0005O\u0012i\rC\u0005\u0002FV\u000b\t\u00111\u0001\u0002:R!\u0011q\u001cBi\u0011%\t)mVA\u0001\u0002\u0004\u00119GA\bM_>\\W\u000f]*vG\u000e,W\rZ3e'%A\u0011\u0011TAv\u0003[\f\u00190A\u0005rk\u0006d\u0017NZ5feV\u0011!1\u001c\t\u0005\u0003#\u0014i.\u0003\u0003\u0003`\n\u0005(\u0001\u0002+sK\u0016LAAa9\u0002\n\n)AK]3fg\u0006Q\u0011/^1mS\u001aLWM\u001d\u0011\u0015\r\t%(1\u001eBw!\r\t\t\u000e\u0003\u0005\b\u0005/l\u0001\u0019\u0001Bn\u0011\u001d\tY-\u0004a\u0001\u0003\u001f$bA!;\u0003r\nM\b\"\u0003Bl\u001fA\u0005\t\u0019\u0001Bn\u0011%\tYm\u0004I\u0001\u0002\u0004\ty-\u0006\u0002\u0003x*\"!1\u001cB\u001f)\u0011\u00119Ga?\t\u0013\u0005\u0015G#!AA\u0002\u0005eF\u0003BAp\u0005\u007fD\u0011\"!2\u0017\u0003\u0003\u0005\rAa\u001a\u0015\t\tM31\u0001\u0005\n\u0003\u000b<\u0012\u0011!a\u0001\u0003s#B!a8\u0004\b!I\u0011Q\u0019\u000e\u0002\u0002\u0003\u0007!qM\u0001\u0010\u0019>|7.\u001e9Tk\u000e\u001cW-\u001a3fIB\u0019\u0011\u0011\u001b\u000f\u0014\u000bq\u0019yaa\u0007\u0011\u0015\rE1q\u0003Bn\u0003\u001f\u0014I/\u0004\u0002\u0004\u0014)!1QCAI\u0003\u001d\u0011XO\u001c;j[\u0016LAa!\u0007\u0004\u0014\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001a\u0011\t\ru11E\u0007\u0003\u0007?QAa!\t\u0003\\\u0005\u0011\u0011n\\\u0005\u0005\u0005\u000f\u0019y\u0002\u0006\u0002\u0004\f\u0005)\u0011\r\u001d9msR1!\u0011^B\u0016\u0007[AqAa6 \u0001\u0004\u0011Y\u000eC\u0004\u0002L~\u0001\r!a4\u0002\u000fUt\u0017\r\u001d9msR!11GB !\u0019\tYj!\u000e\u0004:%!1qGAI\u0005\u0019y\u0005\u000f^5p]BA\u00111TB\u001e\u00057\fy-\u0003\u0003\u0004>\u0005E%A\u0002+va2,'\u0007C\u0005\u0004B\u0001\n\t\u00111\u0001\u0003j\u0006\u0019\u0001\u0010\n\u0019\u0002\u001f1{wn[;q\u00036\u0014\u0017nZ;pkN\u00042!!53'\u0015\u00114\u0011JB\u000e!!\u0019\tba\u0013\u0003\u0010\t\u0015\u0012\u0002BB'\u0007'\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82)\t\u0019)\u0005\u0006\u0003\u0003&\rM\u0003b\u0002B\u0006k\u0001\u0007!q\u0002\u000b\u0005\u0007/\u001aI\u0006\u0005\u0004\u0002\u001c\u000eU\"q\u0002\u0005\n\u0007\u00032\u0014\u0011!a\u0001\u0005K\t!\u0003T8pWV\u0004\u0018J\\1dG\u0016\u001c8/\u001b2mKB\u0019\u0011\u0011\u001b&\u0014\u000b)\u001b\tga\u0007\u0011\u0015\rE1qCAh\u0005\u001f\u0011\t\u000b\u0006\u0002\u0004^Q1!\u0011UB4\u0007SBq!a3N\u0001\u0004\ty\rC\u0004\u0003\f5\u0003\rAa\u0004\u0015\t\r54\u0011\u000f\t\u0007\u00037\u001b)da\u001c\u0011\u0011\u0005m51HAh\u0005\u001fA\u0011b!\u0011O\u0003\u0003\u0005\rA!)\u0002\u001d1{wn[;q\u001d>$hi\\;oI\nQ1kY8qK\u0016sGO]=\u0014\u0007i\u000bI*A\u0002ts6\fqa]=n?\u0012*\u0017\u000f\u0006\u0003\u00020\u000e}\u0004\"CAc9\u0006\u0005\t\u0019AAh\u0003\u0011\u0019\u00180\u001c\u0011\u0002\u000b=<h.\u001a:\u0016\u0005\r\u001d\u0005cAAia\n)1kY8qKN9\u0001o!$\u0004\u0014\u000ee\u0005C\u0002B;\u0007\u001f\u000by-\u0003\u0003\u0004\u0012\n]$\u0001E!cgR\u0014\u0018m\u0019;Ji\u0016\u0014\u0018M\u00197f!\u0011\t\tn!&\n\t\r]\u0015\u0011\u0016\u0002\t'\u000e|\u0007/Z!qSB!\u0011\u0011[BN\u0013\u0011\u0019i*!+\u0003\u001d5+WNY3s'\u000e|\u0007/Z!qSR\u00111qQ\u0001\u0006K2,Wn]\u000b\u0003\u0007K\u00032!!5[\u0003%)G.Z7t?\u0012*\u0017\u000f\u0006\u0003\u00020\u000e-\u0006\"CAcg\u0006\u0005\t\u0019ABS\u0003\u0019)G.Z7tA\u0005a3oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000eJ*d_B,7\u000f\n\u0013oKN$\u0018N\\4mKZ,G\u000eI\u0001*g\u000e\fG.\u0019\u0013sK\u001adWm\u0019;%S:$XM\u001d8bY\u0012\u001a6m\u001c9fg\u0012\"\u0003.Y:ii\u0006\u0014G.\u001a\u0011\u0011\r\u0005m5QWBS\u0013\u0011\u00199,!%\u0003\u000b\u0005\u0013(/Y=\u0002\u0015\u0015dW-\\:DC\u000eDW\r\u0005\u0004\u0004>\u000e}\u0016q\u001a\b\u0005\u00037\u0013\t!\u0003\u0003\u0004B\n%!\u0001\u0002'jgR\f!bY1dQ\u0016$7+\u001b>f\u0003=1G.^:i\u000b2,Wn]\"bG\",\u0017\u0001\u0003%B'\"\u001b\u0016JW#\u0016\u0005\r-wBABg;\t\u0001\u00011A\u0005I\u0003NC5+\u0013.FA\u0005A\u0001*Q*I\u001b\u0006\u001b6*\u0006\u0002\u0004V>\u00111q[\u000f\u0002\u007f\u0006I\u0001*Q*I\u001b\u0006\u001b6\nI\u0001\t\u001b&su\fS!T\u0011V\u00111q\\\b\u0003\u0007Cl\u0012\u0001C\u0001\n\u001b&su\fS!T\u0011\u0002\n!b\u00197p]\u0016\u001c6m\u001c9f\u0003\u001dI7/R7qif\fAa]5{K\u0006QA-\u001b:fGR\u001c\u0016N_3\u0002\u0015\u0015tG/\u001a:F]R\u0014\u0018\u0010\u0006\u0003\u00020\u000eE\b\u0002CBz\u0003\u0013\u0001\ra!*\u0002\u0003\u0015\f1\"\u001a8uKJLe\u000eS1tQR!\u0011qVB}\u0011!\u0019\u00190a\u0003A\u0002\r\u0015\u0016!B3oi\u0016\u0014X\u0003BB\u0000\t\u0013!B\u0001\"\u0001\u0005\u00069!A1\u0001C\u0003\u0019\u0001A\u0001b!\u001f\u0002\u000e\u0001\u0007Aq\u0001\t\u0005\t\u0007!I\u0001\u0002\u0005\u0005\f\u00055!\u0019\u0001C\u0007\u0005\u0005!\u0016\u0003\u0002C\b\u0003\u001f\u0004B!a'\u0005\u0012%!A1CAI\u0005\u001dqu\u000e\u001e5j]\u001e\f1\"\u001a8uKJ\u0014UMZ8sKR1A\u0011\u0004C\u000e\t;qA\u0001b\u0001\u0005\u001c!A1\u0011PA\b\u0001\u0004\ty\r\u0003\u0005\u0005 \u0005=\u0001\u0019ABS\u0003\u0011qW\r\u001f;\u0002\u0017\u0015tG/\u001a:V]&\fX/\u001a\u000b\u0005\u0003_#)\u0003\u0003\u0005\u0004z\u0005E\u0001\u0019AAh\u0003))g\u000e^3s\u0013\u001atUm^\u000b\u0005\tW!y\u0003\u0006\u0003\u0005.\u0011E\u0002\u0003\u0002C\u0002\t_!\u0001\u0002b\u0003\u0002\u0014\t\u0007AQ\u0002\u0005\t\u0007s\n\u0019\u00021\u0001\u0005.\u0005Q1M]3bi\u0016D\u0015m\u001d5\u0002\u001d\u0015tG/\u001a:BY2Le\u000eS1tQR1\u0011q\u0016C\u001d\twA\u0001ba=\u0002\u0018\u0001\u00071Q\u0015\u0005\u000b\t{\t9\u0002%AA\u0002\u0005e\u0016!\u00018\u00021\u0015tG/\u001a:BY2Le\u000eS1tQ\u0012\"WMZ1vYR$#'\u0006\u0002\u0005D)\"\u0011\u0011\u0018B\u001f\u0003\u0019\u0011X\r[1tQR1\u0011q\u0016C%\t\u0017B\u0001b!\u001f\u0002\u001c\u0001\u0007\u0011q\u001a\u0005\t\t\u001b\nY\u00021\u0001\u0005P\u00059a.Z<oC6,\u0007\u0003BAi\t#JA\u0001b\u0015\u0005V\t!a*Y7f\u0013\u0011!9&!#\u0003\u000b9\u000bW.Z:\u0002\rUtG.\u001b8l)\u0011\ty\u000b\"\u0018\t\u0011\rM\u0018Q\u0004a\u0001\u0007K#B!a,\u0005b!A1\u0011PA\u0010\u0001\u0004\ty-\u0001\u0007m_>\\W\u000f]'pIVdW\r\u0006\u0003\u0002P\u0012\u001d\u0004\u0002\u0003C5\u0003C\u0001\r\u0001b\u0014\u0002\t9\fW.Z\u0001\fY>|7.\u001e9DY\u0006\u001c8\u000f\u0006\u0003\u0002P\u0012=\u0004\u0002\u0003C5\u0003G\u0001\r\u0001b\u0014\u0002\u0019\r|g\u000e^1j]Nt\u0015-\\3\u0015\t\u0005}GQ\u000f\u0005\t\tS\n)\u00031\u0001\u0005P\u00051An\\8lkB$B!a4\u0005|!AA\u0011NA\u0014\u0001\u0004!y%A\u0005m_>\\W\u000f]!mYR!A\u0011\u0011CC!\u0019\u0019i\fb!\u0002P&!!Q\u0010B\u0005\u0011!!I'!\u000bA\u0002\u0011=\u0013\u0001\u00057p_.,\b/\u00117m\u000b:$(/[3t)\u0011!Y\t\"$\u0011\r\ruF1QBS\u0011!!I'a\u000bA\u0002\u0011=\u0013a\u00067p_.,\b/\u00168tQ\u0006$wn^3e\u000b:$(/[3t)\u0011!Y\tb%\t\u0011\u0011%\u0014Q\u0006a\u0001\t\u001f\n\u0011\u0003\\8pWV\u00048+_7c_2,e\u000e\u001e:z)\u0011\u0019)\u000b\"'\t\u0011\re\u0014q\u0006a\u0001\u0003\u001f\f1\u0002\\8pWV\u0004XI\u001c;ssR!1Q\u0015CP\u0011!!I'!\rA\u0002\u0011=\u0013a\u00047p_.,\bOT3yi\u0016sGO]=\u0015\t\r\u0015FQ\u0015\u0005\t\tO\u000b\u0019\u00041\u0001\u0004&\u0006)QM\u001c;ss\u00069Bn\\8lkBt\u0015-\\3J]N\u000bW.Z*d_B,\u0017i\u001d\u000b\u0007\u0003\u001f$i\u000b\"-\t\u0011\u0011=\u0016Q\u0007a\u0001\u0003\u001f\f\u0001b\u001c:jO&t\u0017\r\u001c\u0005\t\tg\u000b)\u00041\u0001\u0005P\u0005i1m\\7qC:LwN\u001c(b[\u0016\f1\"[:TC6,7kY8qKR!\u0011q\u001cC]\u0011!!Y,a\u000eA\u0002\r\u001d\u0015!B8uQ\u0016\u0014\u0018AC5t'V\u00147kY8qKR!\u0011q\u001cCa\u0011!!Y,!\u000fA\u0002\r\u001d\u0015A\u0002;p\u0019&\u001cH/\u0006\u0002\u0004<\u000611o\u001c:uK\u0012\fAB\\3ti&tw\rT3wK2\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\t\u0003\u000bqB]3wKJ\u001cX-\u0013;fe\u0006$xN]\u0001\bM>\u0014X-Y2i+\u0011!)\u000eb9\u0015\t\u0005=Fq\u001b\u0005\t\t3\f)\u00051\u0001\u0005\\\u0006\t\u0001\u000f\u0005\u0005\u0002\u001c\u0012u\u0017q\u001aCq\u0013\u0011!y.!%\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003\u0002C\u0002\tG$\u0001\u0002\":\u0002F\t\u0007Aq\u001d\u0002\u0002+F!Aq\u0002B4\u0003%1\u0017\u000e\u001c;fe:{G\u000f\u0006\u0003\u0004\b\u00125\b\u0002\u0003Cm\u0003\u000f\u0002\r\u0001b<\u0011\u0011\u0005mEQ\\Ah\u0003?\faAZ5mi\u0016\u0014H\u0003BBD\tkD\u0001\u0002\"7\u0002J\u0001\u0007Aq^\u0001\be\u00164XM]:fQ1\tY\u0005b?\u0006\u0002\u0015\rQqAC\u0005!\u0011\tY\n\"@\n\t\u0011}\u0018\u0011\u0013\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u000b\u000b\tA$^:fA\u0001$x\u000eT5ti:\u0012XM^3sg\u0016\u0004\u0007%\u001b8ti\u0016\fG-A\u0003tS:\u001cW-\t\u0002\u0006\f\u00051!GL\u00191]A\n\u0011\"\u00193e'R\u0014\u0018N\\4\u0015\u0015\u0015EQ1CC\u000f\u000bC))C\u0004\u0003\u0005\u0004\u0015M\u0001\u0002CC\u000b\u0003\u001b\u0002\r!b\u0006\u0002\u0005M\u0014\u0007\u0003BB_\u000b3IA!b\u0007\u0003\n\ti1\u000b\u001e:j]\u001e\u0014U/\u001b7eKJD\u0001\"b\b\u0002N\u0001\u0007!qB\u0001\u0006gR\f'\u000f\u001e\u0005\t\u000bG\ti\u00051\u0001\u0003\u0010\u0005\u00191/\u001a9\t\u0011\u0015\u001d\u0012Q\na\u0001\u0005\u001f\t1!\u001a8e)\t\u0011y!A\u0016tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIM\u001bw\u000e]3tI\u0011rWm\u001d;j]\u001edWM^3m\u0003=\u001a8-\u00197bII,g\r\\3di\u0012Jg\u000e^3s]\u0006dGeU2pa\u0016\u001cH\u0005\n8fgRLgn\u001a7fm\u0016dw\fJ3r)\u0011\ty+\"\r\t\u0015\u0005\u0015\u00171KA\u0001\u0002\u0004\tI,\u0001\u0015tG\u0006d\u0017\r\n:fM2,7\r\u001e\u0013j]R,'O\\1mIM\u001bw\u000e]3tI\u0011B\u0017m\u001d5uC\ndW-\u0006\u0002\u00044\u0006a3oY1mC\u0012\u0012XM\u001a7fGR$\u0013N\u001c;fe:\fG\u000eJ*d_B,7\u000f\n\u0013iCNDG/\u00192mK~#S-\u001d\u000b\u0005\u0003_+Y\u0004\u0003\u0006\u0002F\u0006]\u0013\u0011!a\u0001\u0007g\u000baa\\<oKJ\u0004CCBBS\u000b\u0003*\u0019\u0005C\u0004\u0004z\u0001\u0004\r!a4\t\u000f\r\r\u0005\r1\u0001\u0004\b\u0006!A/Y5m\u0003!!\u0018-\u001b7`I\u0015\fH\u0003BAX\u000b\u0017B\u0011\"!2c\u0003\u0003\u0005\ra!*\u0002\u000bQ\f\u0017\u000e\u001c\u0011\u0002\u00119,\u0007\u0010^0%KF$B!a,\u0006T!I\u0011QY3\u0002\u0002\u0003\u00071QU\u0001\u0006]\u0016DH\u000fI\u0001\u0006I\u0016\u0004H\u000f\u001b\u000b\u0005\t\u001f*Y\u0006C\u0004\u0006^)\u0004\r!a8\u0002\t\u0019d\u0017\r^\u0001\u000e]\u0016<8kY8qK\u0016sGO]=\u0015\r\r\u0015V1MC3\u0011\u001d\u0019Ih\u001ba\u0001\u0003\u001fDqaa!l\u0001\u0004\u00199)A\u0003TG>\u0004X\rE\u0002\u0002R6\u001c2!\\AM)\t)I'\u0001\u0006v]\u0006\u0004\b\u000f\\=TKF$B!b\u001d\u0006\u0000A1\u00111TC;\u000bsJA!b\u001e\u0002\u0012\n!1k\\7f!\u0019\u0019i,b\u001f\u0002P&!QQ\u0010B\u0005\u0005\r\u0019V-\u001d\u0005\b\u000b\u0003{\u0007\u0019ABD\u0003\u0015!Wm\u00197t\u0003!\u00196m\u001c9f)\u0006<WCACD!\u0019)I)b#\u0004\b6\u0011\u0011QR\u0005\u0005\u000b\u001b\u000biI\u0001\u0005DY\u0006\u001c8\u000fV1h\u0005-iU-\u001c2feN\u001bw\u000e]3\u0002\u001d5+WNY3s'\u000e|\u0007/\u001a+bOV\u0011QQ\u0013\t\u0007\u000b\u0013+Y)b&\u0011\t\u0005E\u00171L\u0001\t]\u0016<8kY8qK\u0006\u0011b.Z<GS:$W*Z7cKJ\u001c6m\u001c9f\u00039qWm\u001e(fgR,GmU2pa\u0016$Baa\"\u0006\"\"AQ1UA2\u0001\u0004\u00199)A\u0003pkR,'/\u0001\u0007oK^\u001c6m\u001c9f/&$\b\u000e\u0006\u0003\u0004\b\u0016%\u0006\u0002CBQ\u0003K\u0002\r!b+\u0011\r\u0005mUQVAh\u0013\u0011)y+!%\u0003\u0015q\u0012X\r]3bi\u0016$g(A\boK^\u0004\u0016mY6bO\u0016\u001c6m\u001c9f)\u0011\u00199)\".\t\u0011\u0015]\u0016q\ra\u0001\u0003\u001f\f\u0001\u0002]6h\u00072\f7o]\u0001\u000fg\u000e|\u0007/\u001a+sC:\u001chm\u001c:n)\u0011)i,\"3\u0015\t\r\u001dUq\u0018\u0005\n\u000b\u0003\fI\u0007\"a\u0001\u000b\u0007\f!a\u001c9\u0011\r\u0005mUQYBD\u0013\u0011)9-!%\u0003\u0011q\u0012\u0017P\\1nKzB\u0001ba!\u0002j\u0001\u0007\u0011qZ\u0001\u000b\u000b6\u0004H/_*d_B,\u0007\u0003BAi\u0003[\u0012!\"R7qif\u001c6m\u001c9f'\u0011\tiga\"\u0015\u0005\u00155G\u0003BAX\u000b/D\u0001ba=\u0002r\u0001\u00071Q\u0015\u0002\u000b\u000bJ\u0014xN]*d_B,7\u0003BA:\u0007\u000f#B!b8\u0006bB!\u0011\u0011[A:\u0011!\u0019\u0019)a\u001eA\u0002\u0005=\u0017!D7bqJ+7-\u001e:tS>t7/\u0006\u0002\u0006h>\u0011Q\u0011^\u000f\u0003\u0007!\u0018QbU2pa\u0016LE/\u001a:bi>\u00148\u0003BA>\u000b_\u0004bA!\u001e\u0006r\u0006=\u0017\u0002BCz\u0005o\u0012\u0001#\u00112tiJ\f7\r^%uKJ\fGo\u001c:\u0015\t\u0015]X\u0011 \t\u0005\u0003#\fY\b\u0003\u0005\u0004\u0004\u0006}\u0004\u0019ABD\u0003\u0011)G.Z7\u0002\u000f!\f7OT3yiR\u0011\u0011q\u001a\t\u0005\r\u00071)!\u0004\u0002\u0002\n&!aqAAE\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3"
)
public interface Scopes extends scala.reflect.api.Scopes {
   LookupSucceeded$ LookupSucceeded();

   LookupAmbiguous$ LookupAmbiguous();

   LookupInaccessible$ LookupInaccessible();

   LookupNotFound$ LookupNotFound();

   Scope$ Scope();

   EmptyScope$ EmptyScope();

   void scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(final ClassTag x$1);

   void scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(final ClassTag x$1);

   int scopeCount();

   void scopeCount_$eq(final int x$1);

   // $FF: synthetic method
   static ScopeEntry scala$reflect$internal$Scopes$$newScopeEntry$(final Scopes $this, final Symbols.Symbol sym, final Scope owner) {
      return $this.scala$reflect$internal$Scopes$$newScopeEntry(sym, owner);
   }

   default ScopeEntry scala$reflect$internal$Scopes$$newScopeEntry(final Symbols.Symbol sym, final Scope owner) {
      ScopeEntry e = (SymbolTable)this.new ScopeEntry(sym, owner);
      e.next_$eq(owner.elems());
      owner.elems_$eq(e);
      return e;
   }

   ClassTag ScopeTag();

   ClassTag MemberScopeTag();

   // $FF: synthetic method
   static Scope newScope$(final Scopes $this) {
      return $this.newScope();
   }

   default Scope newScope() {
      return (SymbolTable)this.new Scope();
   }

   // $FF: synthetic method
   static Scope newFindMemberScope$(final Scopes $this) {
      return $this.newFindMemberScope();
   }

   default Scope newFindMemberScope() {
      return (SymbolTable)this.new Scope() {
         public List sorted() {
            List members = this.toList();
            if (members == null) {
               throw null;
            } else {
               Object var10000;
               if (members == .MODULE$) {
                  var10000 = .MODULE$;
               } else {
                  scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)members.head()).owner(), .MODULE$);
                  scala.collection.immutable..colon.colon map_t = map_h;

                  for(List map_rest = (List)members.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                     scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).owner(), .MODULE$);
                     map_t.next_$eq(map_nx);
                     map_t = map_nx;
                  }

                  Statics.releaseFence();
                  var10000 = map_h;
               }

               Object var14 = null;
               Object var15 = null;
               Object var16 = null;
               Object var17 = null;
               List owners = (List)((List)var10000).distinct();
               Map grouped = members.groupBy((x$6) -> x$6.owner());
               if (owners == null) {
                  throw null;
               } else {
                  List flatMap_rest = owners;
                  scala.collection.immutable..colon.colon flatMap_h = null;

                  for(scala.collection.immutable..colon.colon flatMap_t = null; flatMap_rest != .MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
                     Symbols.Symbol var13 = (Symbols.Symbol)flatMap_rest.head();

                     scala.collection.immutable..colon.colon flatMap_nx;
                     for(Iterator flatMap_it = $anonfun$sorted$3(grouped, var13).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                        flatMap_nx = new scala.collection.immutable..colon.colon(flatMap_it.next(), .MODULE$);
                        if (flatMap_t == null) {
                           flatMap_h = flatMap_nx;
                        } else {
                           flatMap_t.next_$eq(flatMap_nx);
                        }
                     }
                  }

                  if (flatMap_h == null) {
                     return .MODULE$;
                  } else {
                     Statics.releaseFence();
                     return flatMap_h;
                  }
               }
            }
         }

         // $FF: synthetic method
         public static final Symbols.Symbol $anonfun$sorted$1(final Symbols.Symbol x$5) {
            return x$5.owner();
         }

         // $FF: synthetic method
         public static final List $anonfun$sorted$3(final Map grouped$1, final Symbols.Symbol owner) {
            return ((List)grouped$1.apply(owner)).reverse();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static Scope newNestedScope$(final Scopes $this, final Scope outer) {
      return $this.newNestedScope(outer);
   }

   default Scope newNestedScope(final Scope outer) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var26 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var27 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var27;
      boolean var28 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      Tuple2 var34;
      if (var28) {
         Statistics var29 = ((SymbolTable)this).statistics();
         Statistics.Timer startTimer_tm = ((ScopeStats)((SymbolTable)this).statistics()).scopePopulationTime();
         if (var29 == null) {
            throw null;
         }

         Statistics startTimer_this = var29;
         MutableSettings.SettingsOps$ var30 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var31 = MutableSettings$.MODULE$;
         MutableSettings startTimer_enabled_SettingsOps_settings = startTimer_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var32 = startTimer_enabled_SettingsOps_settings;
         startTimer_enabled_SettingsOps_settings = null;
         MutableSettings startTimer_enabled_areStatisticsEnabled$extension_$this = var32;
         boolean var33 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(startTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         startTimer_enabled_areStatisticsEnabled$extension_$this = null;
         var34 = var33 && startTimer_tm != null ? startTimer_tm.start() : null;
         Object var17 = null;
         Object var18 = null;
      } else {
         var34 = null;
      }

      Tuple2 startTime = var34;
      Scope nested = this.newScope();
      nested.elems_$eq(outer.elems());
      nested.scala$reflect$internal$Scopes$$nestinglevel_$eq(outer.scala$reflect$internal$Scopes$$nestinglevel() + 1);
      if (outer.scala$reflect$internal$Scopes$$hashtable() != null) {
         nested.scala$reflect$internal$Scopes$$hashtable_$eq((ScopeEntry[])Arrays.copyOf(outer.scala$reflect$internal$Scopes$$hashtable(), outer.scala$reflect$internal$Scopes$$hashtable().length));
      }

      MutableSettings.SettingsOps$ var35 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var36 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var37 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var37;
      boolean var38 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      if (var38) {
         Statistics var39 = ((SymbolTable)this).statistics();
         Statistics.Timer stopTimer_tm = ((ScopeStats)((SymbolTable)this).statistics()).scopePopulationTime();
         if (var39 == null) {
            throw null;
         }

         Statistics stopTimer_this = var39;
         MutableSettings.SettingsOps$ var40 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var41 = MutableSettings$.MODULE$;
         MutableSettings stopTimer_enabled_SettingsOps_settings = stopTimer_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var42 = stopTimer_enabled_SettingsOps_settings;
         stopTimer_enabled_SettingsOps_settings = null;
         MutableSettings stopTimer_enabled_areStatisticsEnabled$extension_$this = var42;
         boolean var43 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(stopTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         stopTimer_enabled_areStatisticsEnabled$extension_$this = null;
         if (var43 && stopTimer_tm != null) {
            stopTimer_tm.stop(startTime);
         }
      }

      return nested;
   }

   // $FF: synthetic method
   static Scope newScopeWith$(final Scopes $this, final Seq elems) {
      return $this.newScopeWith(elems);
   }

   default Scope newScopeWith(final Seq elems) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var26 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var27 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var27;
      boolean var28 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      Tuple2 var34;
      if (var28) {
         Statistics var29 = ((SymbolTable)this).statistics();
         Statistics.Timer startTimer_tm = ((ScopeStats)((SymbolTable)this).statistics()).scopePopulationTime();
         if (var29 == null) {
            throw null;
         }

         Statistics startTimer_this = var29;
         MutableSettings.SettingsOps$ var30 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var31 = MutableSettings$.MODULE$;
         MutableSettings startTimer_enabled_SettingsOps_settings = startTimer_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var32 = startTimer_enabled_SettingsOps_settings;
         startTimer_enabled_SettingsOps_settings = null;
         MutableSettings startTimer_enabled_areStatisticsEnabled$extension_$this = var32;
         boolean var33 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(startTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         startTimer_enabled_areStatisticsEnabled$extension_$this = null;
         var34 = var33 && startTimer_tm != null ? startTimer_tm.start() : null;
         Object var17 = null;
         Object var18 = null;
      } else {
         var34 = null;
      }

      Tuple2 startTime = var34;
      Scope scope = this.newScope();
      elems.foreach((x$7) -> scope.enter(x$7));
      MutableSettings.SettingsOps$ var35 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var36 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
      MutableSettings var37 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings areStatisticsEnabled$extension_$this = var37;
      boolean var38 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
      areStatisticsEnabled$extension_$this = null;
      if (var38) {
         Statistics var39 = ((SymbolTable)this).statistics();
         Statistics.Timer stopTimer_tm = ((ScopeStats)((SymbolTable)this).statistics()).scopePopulationTime();
         if (var39 == null) {
            throw null;
         }

         Statistics stopTimer_this = var39;
         MutableSettings.SettingsOps$ var40 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var41 = MutableSettings$.MODULE$;
         MutableSettings stopTimer_enabled_SettingsOps_settings = stopTimer_this.scala$reflect$internal$util$Statistics$$settings;
         MutableSettings var42 = stopTimer_enabled_SettingsOps_settings;
         stopTimer_enabled_SettingsOps_settings = null;
         MutableSettings stopTimer_enabled_areStatisticsEnabled$extension_$this = var42;
         boolean var43 = StatisticsStatics.COLD_STATS_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(stopTimer_enabled_areStatisticsEnabled$extension_$this.YstatisticsEnabled().value());
         stopTimer_enabled_areStatisticsEnabled$extension_$this = null;
         if (var43 && stopTimer_tm != null) {
            stopTimer_tm.stop(startTime);
         }
      }

      return scope;
   }

   // $FF: synthetic method
   static Scope newPackageScope$(final Scopes $this, final Symbols.Symbol pkgClass) {
      return $this.newPackageScope(pkgClass);
   }

   default Scope newPackageScope(final Symbols.Symbol pkgClass) {
      return this.newScope();
   }

   // $FF: synthetic method
   static Scope scopeTransform$(final Scopes $this, final Symbols.Symbol owner, final Function0 op) {
      return $this.scopeTransform(owner, op);
   }

   default Scope scopeTransform(final Symbols.Symbol owner, final Function0 op) {
      return (Scope)op.apply();
   }

   private int maxRecursions() {
      return 1000;
   }

   static void $init$(final Scopes $this) {
      $this.scopeCount_$eq(0);
      ((SymbolTable)$this).perRunCaches().recordCache(() -> $this.scopeCount_$eq(0));
      $this.scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(scala.reflect.ClassTag..MODULE$.apply(Scope.class));
      $this.scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(scala.reflect.ClassTag..MODULE$.apply(Scope.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface NameLookup {
      Symbols.Symbol symbol();

      default boolean isSuccess() {
         return false;
      }

      // $FF: synthetic method
      Scopes scala$reflect$internal$Scopes$NameLookup$$$outer();

      static void $init$(final NameLookup $this) {
      }
   }

   public class LookupSucceeded implements NameLookup, Product, Serializable {
      private final Trees.Tree qualifier;
      private final Symbols.Symbol symbol;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Trees.Tree qualifier() {
         return this.qualifier;
      }

      public Symbols.Symbol symbol() {
         return this.symbol;
      }

      public boolean isSuccess() {
         return true;
      }

      public LookupSucceeded copy(final Trees.Tree qualifier, final Symbols.Symbol symbol) {
         return this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer().new LookupSucceeded(qualifier, symbol);
      }

      public Trees.Tree copy$default$1() {
         return this.qualifier();
      }

      public Symbols.Symbol copy$default$2() {
         return this.symbol();
      }

      public String productPrefix() {
         return "LookupSucceeded";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.qualifier();
            case 1:
               return this.symbol();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LookupSucceeded;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "qualifier";
            case 1:
               return "symbol";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof LookupSucceeded && ((LookupSucceeded)x$1).scala$reflect$internal$Scopes$LookupSucceeded$$$outer() == this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer()) {
               LookupSucceeded var2 = (LookupSucceeded)x$1;
               Trees.Tree var10000 = this.qualifier();
               Trees.Tree var3 = var2.qualifier();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               Symbols.Symbol var5 = this.symbol();
               Symbols.Symbol var4 = var2.symbol();
               if (var5 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var5.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$LookupSucceeded$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
         return this.scala$reflect$internal$Scopes$LookupSucceeded$$$outer();
      }

      public LookupSucceeded(final Trees.Tree qualifier, final Symbols.Symbol symbol) {
         this.qualifier = qualifier;
         this.symbol = symbol;
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupSucceeded$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "LookupSucceeded";
      }

      public LookupSucceeded apply(final Trees.Tree qualifier, final Symbols.Symbol symbol) {
         return this.$outer.new LookupSucceeded(qualifier, symbol);
      }

      public Option unapply(final LookupSucceeded x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.qualifier(), x$0.symbol())));
      }

      public LookupSucceeded$() {
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupAmbiguous implements NameLookup, Product, Serializable {
      private final String msg;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean isSuccess() {
         return Scopes.NameLookup.super.isSuccess();
      }

      public String msg() {
         return this.msg;
      }

      public Symbols.NoSymbol symbol() {
         return this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer().NoSymbol();
      }

      public LookupAmbiguous copy(final String msg) {
         return this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer().new LookupAmbiguous(msg);
      }

      public String copy$default$1() {
         return this.msg();
      }

      public String productPrefix() {
         return "LookupAmbiguous";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.msg();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LookupAmbiguous;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "msg";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof LookupAmbiguous && ((LookupAmbiguous)x$1).scala$reflect$internal$Scopes$LookupAmbiguous$$$outer() == this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer()) {
               LookupAmbiguous var2 = (LookupAmbiguous)x$1;
               String var10000 = this.msg();
               String var3 = var2.msg();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$LookupAmbiguous$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
         return this.scala$reflect$internal$Scopes$LookupAmbiguous$$$outer();
      }

      public LookupAmbiguous(final String msg) {
         this.msg = msg;
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupAmbiguous$ extends AbstractFunction1 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "LookupAmbiguous";
      }

      public LookupAmbiguous apply(final String msg) {
         return this.$outer.new LookupAmbiguous(msg);
      }

      public Option unapply(final LookupAmbiguous x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.msg()));
      }

      public LookupAmbiguous$() {
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupInaccessible implements NameLookup, Product, Serializable {
      private final Symbols.Symbol symbol;
      private final String msg;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean isSuccess() {
         return Scopes.NameLookup.super.isSuccess();
      }

      public Symbols.Symbol symbol() {
         return this.symbol;
      }

      public String msg() {
         return this.msg;
      }

      public LookupInaccessible copy(final Symbols.Symbol symbol, final String msg) {
         return this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer().new LookupInaccessible(symbol, msg);
      }

      public Symbols.Symbol copy$default$1() {
         return this.symbol();
      }

      public String copy$default$2() {
         return this.msg();
      }

      public String productPrefix() {
         return "LookupInaccessible";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.symbol();
            case 1:
               return this.msg();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LookupInaccessible;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "symbol";
            case 1:
               return "msg";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return scala.util.hashing.MurmurHash3..MODULE$.productHash(this, -889275714, false);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         if (this != x$1) {
            if (x$1 instanceof LookupInaccessible && ((LookupInaccessible)x$1).scala$reflect$internal$Scopes$LookupInaccessible$$$outer() == this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer()) {
               LookupInaccessible var2 = (LookupInaccessible)x$1;
               Symbols.Symbol var10000 = this.symbol();
               Symbols.Symbol var3 = var2.symbol();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               String var5 = this.msg();
               String var4 = var2.msg();
               if (var5 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var5.equals(var4)) {
                  return false;
               }

               if (var2.canEqual(this)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$LookupInaccessible$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
         return this.scala$reflect$internal$Scopes$LookupInaccessible$$$outer();
      }

      public LookupInaccessible(final Symbols.Symbol symbol, final String msg) {
         this.symbol = symbol;
         this.msg = msg;
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupInaccessible$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public final String toString() {
         return "LookupInaccessible";
      }

      public LookupInaccessible apply(final Symbols.Symbol symbol, final String msg) {
         return this.$outer.new LookupInaccessible(symbol, msg);
      }

      public Option unapply(final LookupInaccessible x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.symbol(), x$0.msg())));
      }

      public LookupInaccessible$() {
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class LookupNotFound$ implements NameLookup, Product, Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public boolean isSuccess() {
         return Scopes.NameLookup.super.isSuccess();
      }

      public Symbols.NoSymbol symbol() {
         return this.$outer.NoSymbol();
      }

      public String productPrefix() {
         return "LookupNotFound";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         return Statics.ioobe(x$1);
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof LookupNotFound$;
      }

      public int hashCode() {
         return -298416087;
      }

      public String toString() {
         return "LookupNotFound";
      }

      // $FF: synthetic method
      public Scopes scala$reflect$internal$Scopes$NameLookup$$$outer() {
         return this.$outer;
      }

      public LookupNotFound$() {
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
         }
      }
   }

   public class ScopeEntry {
      private Symbols.Symbol sym;
      private final Scope owner;
      private ScopeEntry tail;
      private ScopeEntry next;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Symbols.Symbol sym() {
         return this.sym;
      }

      public void sym_$eq(final Symbols.Symbol x$1) {
         this.sym = x$1;
      }

      public Scope owner() {
         return this.owner;
      }

      public ScopeEntry tail() {
         return this.tail;
      }

      public void tail_$eq(final ScopeEntry x$1) {
         this.tail = x$1;
      }

      public ScopeEntry next() {
         return this.next;
      }

      public void next_$eq(final ScopeEntry x$1) {
         this.next = x$1;
      }

      public int depth() {
         return this.owner().nestingLevel();
      }

      public int hashCode() {
         Names.Name var10000 = this.sym().name();
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.index();
         }
      }

      public String toString() {
         return (new StringBuilder(9)).append(this.sym()).append(" (depth=").append(this.depth()).append(")").toString();
      }

      public final Names.Name name(final boolean flat) {
         if (flat) {
            return this.sym().name();
         } else {
            Symbols.Symbol var10000 = this.sym();
            if (var10000 == null) {
               throw null;
            } else {
               return var10000._rawname();
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$ScopeEntry$$$outer() {
         return this.$outer;
      }

      public ScopeEntry(final Symbols.Symbol sym, final Scope owner) {
         this.sym = sym;
         this.owner = owner;
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
            this.tail = null;
            this.next = null;
         }
      }
   }

   public class Scope$ {
      public Some unapplySeq(final Scope decls) {
         return new Some(decls.toList());
      }
   }

   public class ScopeIterator extends AbstractIterator {
      private final Scope owner;
      private ScopeEntry elem;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public boolean hasNext() {
         if (this.elem != null) {
            Scope var10000 = this.elem.owner();
            Scope var1 = this.owner;
            if (var10000 == null) {
               if (var1 == null) {
                  return true;
               }
            } else if (var10000.equals(var1)) {
               return true;
            }
         }

         return false;
      }

      public Symbols.Symbol next() {
         if (this.hasNext()) {
            ScopeEntry res = this.elem;
            this.elem = this.elem.next();
            return res.sym();
         } else {
            throw new NoSuchElementException();
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$ScopeIterator$$$outer() {
         return this.$outer;
      }

      public ScopeIterator(final Scope owner) {
         this.owner = owner;
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
            this.elem = owner.elems();
         }
      }
   }

   public class Scope extends AbstractIterable implements scala.reflect.api.Scopes.MemberScopeApi {
      private ScopeEntry elems;
      private int scala$reflect$internal$Scopes$$nestinglevel;
      private ScopeEntry[] scala$reflect$internal$Scopes$$hashtable;
      private List elemsCache;
      private int cachedSize;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public ScopeEntry elems() {
         return this.elems;
      }

      public void elems_$eq(final ScopeEntry x$1) {
         this.elems = x$1;
      }

      public int scala$reflect$internal$Scopes$$nestinglevel() {
         return this.scala$reflect$internal$Scopes$$nestinglevel;
      }

      public void scala$reflect$internal$Scopes$$nestinglevel_$eq(final int x$1) {
         this.scala$reflect$internal$Scopes$$nestinglevel = x$1;
      }

      public ScopeEntry[] scala$reflect$internal$Scopes$$hashtable() {
         return this.scala$reflect$internal$Scopes$$hashtable;
      }

      public void scala$reflect$internal$Scopes$$hashtable_$eq(final ScopeEntry[] x$1) {
         this.scala$reflect$internal$Scopes$$hashtable = x$1;
      }

      private void flushElemsCache() {
         this.elemsCache = null;
         this.cachedSize = -1;
      }

      private final int HASHSIZE() {
         return 128;
      }

      private final int HASHMASK() {
         return 127;
      }

      private final int MIN_HASH() {
         return 8;
      }

      public Scope cloneScope() {
         return this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith(this.toList());
      }

      public boolean isEmpty() {
         return this.elems() == null;
      }

      public int size() {
         if (this.cachedSize < 0) {
            this.cachedSize = this.directSize();
         }

         return this.cachedSize;
      }

      private int directSize() {
         int s = 0;

         for(ScopeEntry e = this.elems(); e != null; e = e.next()) {
            ++s;
         }

         return s;
      }

      public void enterEntry(final ScopeEntry e) {
         this.flushElemsCache();
         if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
            this.enterInHash(e);
         } else if (this.size() >= 8) {
            this.createHash();
         }
      }

      private void enterInHash(final ScopeEntry e) {
         Names.Name var10000 = e.sym().name();
         if (var10000 == null) {
            throw null;
         } else {
            int i = var10000.index() & 127;
            e.tail_$eq(this.scala$reflect$internal$Scopes$$hashtable()[i]);
            this.scala$reflect$internal$Scopes$$hashtable()[i] = e;
         }
      }

      public Symbols.Symbol enter(final Symbols.Symbol sym) {
         this.enterEntry(this.scala$reflect$internal$Scopes$Scope$$$outer().scala$reflect$internal$Scopes$$newScopeEntry(sym, this));
         return sym;
      }

      public final Symbols.Symbol enterBefore(final Symbols.Symbol sym, final ScopeEntry next) {
         SymbolTable var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
         boolean assert_assertion = !this.equals(this.scala$reflect$internal$Scopes$Scope$$$outer().EmptyScope());
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError(sym);
            } else {
               assert_this = null;
               var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
               Names.Name var10001 = sym.name();
               if (var10001 == null) {
                  throw null;
               } else {
                  int var16 = var10001.index();
                  Names.Name var10002 = next.sym().name();
                  if (var10002 == null) {
                     throw null;
                  } else {
                     boolean require_requirement = var16 == var10002.index();
                     if (var10000 == null) {
                        throw null;
                     } else {
                        SymbolTable require_this = var10000;
                        if (!require_requirement) {
                           throw require_this.throwRequirementError($anonfun$enterBefore$2(sym, next));
                        } else {
                           require_this = null;
                           var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
                           boolean require_requirementx = !sym.equals(next.sym());
                           if (var10000 == null) {
                              throw null;
                           } else {
                              SymbolTable require_this = var10000;
                              if (!require_requirementx) {
                                 throw require_this.throwRequirementError($anonfun$enterBefore$3(sym, next));
                              } else {
                                 require_this = null;
                                 ScopeEntry newNext = this.scala$reflect$internal$Scopes$Scope$$$outer().new ScopeEntry(next.sym(), this);
                                 boolean hasHashTable = this.scala$reflect$internal$Scopes$$hashtable() != null;
                                 newNext.next_$eq(next.next());
                                 if (hasHashTable) {
                                    newNext.tail_$eq(next.tail());
                                 }

                                 next.sym_$eq(sym);
                                 next.next_$eq(newNext);
                                 if (hasHashTable) {
                                    next.tail_$eq(newNext);
                                 }

                                 this.flushElemsCache();
                                 return sym;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      public void enterUnique(final Symbols.Symbol sym) {
         boolean var6;
         SymbolTable var10000;
         label25: {
            label24: {
               var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
               Symbols.Symbol var10001 = this.lookup(sym.name());
               Symbols.NoSymbol var2 = this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol();
               if (var10001 == null) {
                  if (var2 == null) {
                     break label24;
                  }
               } else if (var10001.equals(var2)) {
                  break label24;
               }

               var6 = false;
               break label25;
            }

            var6 = true;
         }

         boolean assert_assertion = var6;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError($anonfun$enterUnique$1(this, sym));
            } else {
               assert_this = null;
               this.enter(sym);
            }
         }
      }

      public Symbols.Symbol enterIfNew(final Symbols.Symbol sym) {
         ScopeEntry existing = this.lookupEntry(sym.name());
         return existing == null ? this.enter(sym) : existing.sym();
      }

      private void createHash() {
         this.scala$reflect$internal$Scopes$$hashtable_$eq(new ScopeEntry[128]);
         this.enterAllInHash(this.elems(), 0);
      }

      private void enterAllInHash(final ScopeEntry e, final int n) {
         if (e != null) {
            if (n < 1000) {
               this.enterAllInHash(e.next(), n + 1);
               this.enterInHash(e);
            } else {
               List entries = .MODULE$;

               for(ScopeEntry ee = e; ee != null; ee = ee.next()) {
                  entries = entries.$colon$colon(ee);
               }

               entries.foreach((ex) -> {
                  $anonfun$enterAllInHash$1(this, ex);
                  return BoxedUnit.UNIT;
               });
            }
         }
      }

      private int enterAllInHash$default$2() {
         return 0;
      }

      public void rehash(final Symbols.Symbol sym, final Names.Name newname) {
         if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
            Names.Name var10000 = sym.name();
            if (var10000 == null) {
               throw null;
            } else {
               int index = var10000.index() & 127;
               ScopeEntry e1 = this.scala$reflect$internal$Scopes$$hashtable()[index];
               ScopeEntry e = null;
               if (e1 != null) {
                  label49: {
                     Symbols.Symbol var7 = e1.sym();
                     if (var7 != null) {
                        if (var7.equals(sym)) {
                           this.scala$reflect$internal$Scopes$$hashtable()[index] = e1.tail();
                           e = e1;
                           break label49;
                        }
                     }

                     for(; e1.tail() != null; e1 = e1.tail()) {
                        var7 = e1.tail().sym();
                        if (var7 != null) {
                           if (var7.equals(sym)) {
                              break;
                           }
                        }
                     }

                     if (e1.tail() != null) {
                        e = e1.tail();
                        e1.tail_$eq(e.tail());
                     }
                  }
               }

               if (e != null) {
                  if (newname == null) {
                     throw null;
                  } else {
                     int newindex = newname.index() & 127;
                     e.tail_$eq(this.scala$reflect$internal$Scopes$$hashtable()[newindex]);
                     this.scala$reflect$internal$Scopes$$hashtable()[newindex] = e;
                  }
               }
            }
         }
      }

      public void unlink(final ScopeEntry e) {
         label55: {
            label58: {
               ScopeEntry var10000 = this.elems();
               if (var10000 == null) {
                  if (e == null) {
                     break label58;
                  }
               } else if (var10000.equals(e)) {
                  break label58;
               }

               ScopeEntry e1 = this.elems();

               while(true) {
                  var10000 = e1.next();
                  if (var10000 == null) {
                     if (e == null) {
                        break;
                     }
                  } else if (var10000.equals(e)) {
                     break;
                  }

                  e1 = e1.next();
               }

               e1.next_$eq(e.next());
               break label55;
            }

            this.elems_$eq(e.next());
         }

         if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
            label59: {
               Names.Name var6 = e.sym().name();
               if (var6 == null) {
                  throw null;
               }

               int index = var6.index() & 127;
               ScopeEntry e1 = this.scala$reflect$internal$Scopes$$hashtable()[index];
               if (e1 != null) {
                  if (e1.equals(e)) {
                     this.scala$reflect$internal$Scopes$$hashtable()[index] = e.tail();
                     break label59;
                  }
               }

               while(true) {
                  ScopeEntry var7 = e1.tail();
                  if (var7 != null) {
                     if (var7.equals(e)) {
                        e1.tail_$eq(e.tail());
                        break;
                     }
                  }

                  e1 = e1.tail();
               }
            }
         }

         this.flushElemsCache();
      }

      public void unlink(final Symbols.Symbol sym) {
         for(ScopeEntry e = this.lookupEntry(sym.name()); e != null; e = this.lookupNextEntry(e)) {
            Symbols.Symbol var10000 = e.sym();
            if (var10000 == null) {
               if (sym != null) {
                  continue;
               }
            } else if (!var10000.equals(sym)) {
               continue;
            }

            this.unlink(e);
         }

      }

      public Symbols.Symbol lookupModule(final Names.Name name) {
         SymbolTable var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
         Iterator var10001 = this.lookupAll(name.toTermName());
         Function1 findSymbol_p = (x$2) -> BoxesRunTime.boxToBoolean($anonfun$lookupModule$1(x$2));
         Iterator findSymbol_xs = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable findSymbol_this = var10000;
            if (findSymbol_xs == null) {
               throw null;
            } else {
               Option var6 = findSymbol_xs.find(findSymbol_p);
               if (var6 == null) {
                  throw null;
               } else {
                  Option findSymbol_getOrElse_this = var6;
                  return (Symbols.Symbol)(findSymbol_getOrElse_this.isEmpty() ? findSymbol_this.NoSymbol() : findSymbol_getOrElse_this.get());
               }
            }
         }
      }

      public Symbols.Symbol lookupClass(final Names.Name name) {
         SymbolTable var10000 = this.scala$reflect$internal$Scopes$Scope$$$outer();
         Iterator var10001 = this.lookupAll(name.toTypeName());
         Function1 findSymbol_p = (x$3) -> BoxesRunTime.boxToBoolean($anonfun$lookupClass$1(x$3));
         Iterator findSymbol_xs = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            SymbolTable findSymbol_this = var10000;
            if (findSymbol_xs == null) {
               throw null;
            } else {
               Option var6 = findSymbol_xs.find(findSymbol_p);
               if (var6 == null) {
                  throw null;
               } else {
                  Option findSymbol_getOrElse_this = var6;
                  return (Symbols.Symbol)(findSymbol_getOrElse_this.isEmpty() ? findSymbol_this.NoSymbol() : findSymbol_getOrElse_this.get());
               }
            }
         }
      }

      public boolean containsName(final Names.Name name) {
         return this.lookupEntry(name) != null;
      }

      public Symbols.Symbol lookup(final Names.Name name) {
         ScopeEntry e = this.lookupEntry(name);
         if (e == null) {
            return this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol();
         } else if (this.lookupNextEntry(e) == null) {
            return e.sym();
         } else {
            List alts = this.lookupAll(name).toList();
            this.scala$reflect$internal$Scopes$Scope$$$outer().devWarning(() -> (new StringBuilder(41)).append("scope lookup of ").append(name).append(" found multiple symbols: ").append(alts_s$1(alts)).toString());
            return this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol().newOverloaded(this.scala$reflect$internal$Scopes$Scope$$$outer().NoPrefix(), alts);
         }
      }

      public Iterator lookupAll(final Names.Name name) {
         return new AbstractIterator(name) {
            private ScopeEntry e;
            // $FF: synthetic field
            private final Scope $outer;

            public boolean hasNext() {
               return this.e != null;
            }

            public Symbols.Symbol next() {
               Symbols.Symbol var10000;
               try {
                  var10000 = this.e.sym();
               } finally {
                  this.e = this.$outer.lookupNextEntry(this.e);
               }

               return var10000;
            }

            public {
               if (Scope.this == null) {
                  throw null;
               } else {
                  this.$outer = Scope.this;
                  this.e = Scope.this.lookupEntry(name$2);
               }
            }
         };
      }

      public Iterator lookupAllEntries(final Names.Name name) {
         return new AbstractIterator(name) {
            private ScopeEntry e;
            // $FF: synthetic field
            private final Scope $outer;

            public boolean hasNext() {
               return this.e != null;
            }

            public ScopeEntry next() {
               ScopeEntry var10000;
               try {
                  var10000 = this.e;
               } finally {
                  this.e = this.$outer.lookupNextEntry(this.e);
               }

               return var10000;
            }

            public {
               if (Scope.this == null) {
                  throw null;
               } else {
                  this.$outer = Scope.this;
                  this.e = Scope.this.lookupEntry(name$3);
               }
            }
         };
      }

      public Iterator lookupUnshadowedEntries(final Names.Name name) {
         ScopeEntry var2 = this.lookupEntry(name);
         if (var2 == null) {
            if (scala.package..MODULE$.Iterator() == null) {
               throw null;
            } else {
               return scala.collection.Iterator..scala$collection$Iterator$$_empty;
            }
         } else {
            return this.lookupAllEntries(name).filter((e1) -> BoxesRunTime.boxToBoolean($anonfun$lookupUnshadowedEntries$1(var2, e1)));
         }
      }

      public final ScopeEntry lookupSymbolEntry(final Symbols.Symbol sym) {
         for(ScopeEntry e = this.lookupEntry(sym.name()); e != null; e = this.lookupNextEntry(e)) {
            Symbols.Symbol var10000 = e.sym();
            if (var10000 != null) {
               if (var10000.equals(sym)) {
                  return e;
               }
            }
         }

         return null;
      }

      public ScopeEntry lookupEntry(final Names.Name name) {
         boolean flat = this.scala$reflect$internal$Scopes$Scope$$$outer().phase().flatClasses();
         ScopeEntry e;
         if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
            ScopeEntry[] var10000 = this.scala$reflect$internal$Scopes$$hashtable();
            if (name == null) {
               throw null;
            }

            for(e = var10000[name.index() & 127]; e != null && e.name(flat) != name; e = e.tail()) {
            }
         } else {
            for(e = this.elems(); e != null && e.name(flat) != name; e = e.next()) {
            }
         }

         return e;
      }

      public ScopeEntry lookupNextEntry(final ScopeEntry entry) {
         ScopeEntry e = entry;
         boolean flat = this.scala$reflect$internal$Scopes$Scope$$$outer().phase().flatClasses();
         Names.Name entryName = entry.name(flat);
         if (this.scala$reflect$internal$Scopes$$hashtable() != null) {
            while(true) {
               e = e.tail();
               if (e == null) {
                  break;
               }

               Names.Name var5 = e.name(flat);
               if (var5 == null) {
                  if (entryName == null) {
                     break;
                  }
               } else if (var5.equals(entryName)) {
                  break;
               }
            }
         } else {
            while(true) {
               e = e.next();
               if (e == null) {
                  break;
               }

               Names.Name var10000 = e.name(flat);
               if (var10000 == null) {
                  if (entryName == null) {
                     break;
                  }
               } else if (var10000.equals(entryName)) {
                  break;
               }
            }
         }

         return e;
      }

      public final Symbols.Symbol lookupNameInSameScopeAs(final Symbols.Symbol original, final Names.Name companionName) {
         ScopeEntry var3 = this.lookupSymbolEntry(original);
         if (var3 != null) {
            for(ScopeEntry e = this.lookupEntry(companionName); e != null; e = this.lookupNextEntry(e)) {
               if (e.owner() == var3.owner()) {
                  return e.sym();
               }
            }
         }

         return this.scala$reflect$internal$Scopes$Scope$$$outer().NoSymbol();
      }

      public boolean isSameScope(final Scope other) {
         return this.size() == other.size() && this.isSubScope(other) && other.isSubScope(this);
      }

      public boolean isSubScope(final Scope other) {
         return other.reverseIterator().forall((sym) -> BoxesRunTime.boxToBoolean($anonfun$isSubScope$1(this, sym)));
      }

      public List toList() {
         if (this.elemsCache == null) {
            List symbols = .MODULE$;
            int count = 0;

            for(ScopeEntry e = this.elems(); e != null; e = e.next()) {
               Scope var10000 = e.owner();
               if (var10000 == null) {
                  if (this != null) {
                     break;
                  }
               } else if (!var10000.equals(this)) {
                  break;
               }

               ++count;
               symbols = symbols.$colon$colon(e.sym());
            }

            this.elemsCache = symbols;
            this.cachedSize = count;
         }

         return this.elemsCache;
      }

      public List sorted() {
         return this.toList();
      }

      public int nestingLevel() {
         return this.scala$reflect$internal$Scopes$$nestinglevel();
      }

      public Iterator iterator() {
         return this.toList().iterator();
      }

      public Iterator reverseIterator() {
         return this.scala$reflect$internal$Scopes$Scope$$$outer().new ScopeIterator(this);
      }

      public void foreach(final Function1 p) {
         List var10000 = this.toList();
         if (var10000 == null) {
            throw null;
         } else {
            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               p.apply(foreach_these.head());
            }

         }
      }

      public Scope filterNot(final Function1 p) {
         List result = this.toList();
         if (result == null) {
            throw null;
         } else {
            boolean filterNot_filterCommon_isFlipped = true;
            List filterNot_filterCommon_noneIn$1_l = result;

            Object var10000;
            while(true) {
               if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                  var10000 = .MODULE$;
                  break;
               }

               Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
               List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
               if (BoxesRunTime.unboxToBoolean(p.apply(filterNot_filterCommon_noneIn$1_h)) != filterNot_filterCommon_isFlipped) {
                  List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10000 = filterNot_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                     if (BoxesRunTime.unboxToBoolean(p.apply(filterNot_filterCommon_noneIn$1_allIn$1_x)) == filterNot_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           if (BoxesRunTime.unboxToBoolean(p.apply(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head)) != filterNot_filterCommon_isFlipped) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var10000 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var27 = null;
                        Object var30 = null;
                        Object var33 = null;
                        Object var36 = null;
                        Object var39 = null;
                        Object var42 = null;
                        Object var45 = null;
                        Object var48 = null;
                        break;
                     }

                     filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var23 = null;
                  Object var25 = null;
                  Object var28 = null;
                  Object var31 = null;
                  Object var34 = null;
                  Object var37 = null;
                  Object var40 = null;
                  Object var43 = null;
                  Object var46 = null;
                  Object var49 = null;
                  break;
               }

               filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
            }

            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var24 = null;
            Object var26 = null;
            Object var29 = null;
            Object var32 = null;
            Object var35 = null;
            Object var38 = null;
            Object var41 = null;
            Object var44 = null;
            Object var47 = null;
            Object var50 = null;
            List filterNot_filterCommon_result = (List)var10000;
            Statics.releaseFence();
            var10000 = filterNot_filterCommon_result;
            filterNot_filterCommon_result = null;
            List filtered = (List)var10000;
            return result == filtered ? this : this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith(filtered);
         }
      }

      public Scope filter(final Function1 p) {
         List result = this.toList();
         if (result == null) {
            throw null;
         } else {
            boolean filter_filterCommon_isFlipped = false;
            List filter_filterCommon_noneIn$1_l = result;

            Object var10000;
            while(true) {
               if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                  var10000 = .MODULE$;
                  break;
               }

               Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
               List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
               if (BoxesRunTime.unboxToBoolean(p.apply(filter_filterCommon_noneIn$1_h)) != filter_filterCommon_isFlipped) {
                  List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var10000 = filter_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                     if (BoxesRunTime.unboxToBoolean(p.apply(filter_filterCommon_noneIn$1_allIn$1_x)) == filter_filterCommon_isFlipped) {
                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), .MODULE$);
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                        scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), .MODULE$);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           if (BoxesRunTime.unboxToBoolean(p.apply(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head)) != filter_filterCommon_isFlipped) {
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), .MODULE$);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.tail();
                              }

                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           }
                        }

                        if (!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.isEmpty()) {
                           filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy);
                        }

                        var10000 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var27 = null;
                        Object var30 = null;
                        Object var33 = null;
                        Object var36 = null;
                        Object var39 = null;
                        Object var42 = null;
                        Object var45 = null;
                        Object var48 = null;
                        break;
                     }

                     filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var23 = null;
                  Object var25 = null;
                  Object var28 = null;
                  Object var31 = null;
                  Object var34 = null;
                  Object var37 = null;
                  Object var40 = null;
                  Object var43 = null;
                  Object var46 = null;
                  Object var49 = null;
                  break;
               }

               filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
            }

            Object var20 = null;
            Object var21 = null;
            Object var22 = null;
            Object var24 = null;
            Object var26 = null;
            Object var29 = null;
            Object var32 = null;
            Object var35 = null;
            Object var38 = null;
            Object var41 = null;
            Object var44 = null;
            Object var47 = null;
            Object var50 = null;
            List filter_filterCommon_result = (List)var10000;
            Statics.releaseFence();
            var10000 = filter_filterCommon_result;
            filter_filterCommon_result = null;
            List filtered = (List)var10000;
            SymbolTable var52 = this.scala$reflect$internal$Scopes$Scope$$$outer();
            if (var52 == null) {
               throw null;
            } else {
               return Collections.sameLength$(var52, result, filtered) ? this : this.scala$reflect$internal$Scopes$Scope$$$outer().newScopeWith(filtered);
            }
         }
      }

      /** @deprecated */
      public List reverse() {
         return this.toList().reverse();
      }

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder sb, final String start, final String sep, final String end) {
         List var10000 = this.toList();
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            Object var15;
            if (map_this == .MODULE$) {
               var15 = .MODULE$;
            } else {
               scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_this.head()).defString(), .MODULE$);
               scala.collection.immutable..colon.colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                  scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).defString(), .MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               var15 = map_h;
            }

            Object var10 = null;
            Object var11 = null;
            Object var12 = null;
            Object var13 = null;
            Object var14 = null;
            return ((List)var15).addString(sb, start, sep, end);
         }
      }

      public String toString() {
         String mkString_end = "\n}";
         String mkString_sep = ";\n  ";
         String mkString_start = "Scope{\n  ";
         return IterableOnceOps.mkString$(this, mkString_start, mkString_sep, mkString_end);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$Scope$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final Symbols.Symbol $anonfun$enterBefore$1(final Symbols.Symbol sym$1) {
         return sym$1;
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$enterBefore$2(final Symbols.Symbol sym$1, final ScopeEntry next$1) {
         return new Tuple2(sym$1, next$1.sym());
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$enterBefore$3(final Symbols.Symbol sym$1, final ScopeEntry next$1) {
         return new Tuple2(sym$1, next$1.sym());
      }

      // $FF: synthetic method
      public static final Tuple2 $anonfun$enterUnique$1(final Scope $this, final Symbols.Symbol sym$2) {
         return new Tuple2(sym$2.fullLocationString(), $this.lookup(sym$2.name()).fullLocationString());
      }

      // $FF: synthetic method
      public static final void $anonfun$enterAllInHash$1(final Scope $this, final ScopeEntry e) {
         $this.enterInHash(e);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$lookupModule$1(final Symbols.Symbol x$2) {
         return x$2.isModule();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$lookupClass$1(final Symbols.Symbol x$3) {
         return x$3.isClass();
      }

      // $FF: synthetic method
      public static final String $anonfun$lookup$1(final Symbols.Symbol s) {
         return s.defString();
      }

      private static final String alts_s$1(final List alts$1) {
         Object var10000;
         if (alts$1 == .MODULE$) {
            var10000 = .MODULE$;
         } else {
            scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon(((Symbols.Symbol)alts$1.head()).defString(), .MODULE$);
            scala.collection.immutable..colon.colon map_t = map_h;

            for(List map_rest = (List)alts$1.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
               scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon(((Symbols.Symbol)map_rest.head()).defString(), .MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10000 = map_h;
         }

         Object var6 = null;
         Object var7 = null;
         Object var8 = null;
         Object var9 = null;
         String mkString_sep = " <and> ";
         return ((IterableOnceOps)var10000).mkString("", mkString_sep, "");
      }

      // $FF: synthetic method
      public static final boolean $anonfun$lookupUnshadowedEntries$1(final ScopeEntry x1$1, final ScopeEntry e1) {
         if (x1$1 != e1) {
            if (x1$1.depth() == e1.depth()) {
               Symbols.Symbol var10000 = x1$1.sym();
               Symbols.Symbol var2 = e1.sym();
               if (var10000 == null) {
                  if (var2 != null) {
                     return true;
                  }
               } else if (!var10000.equals(var2)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      private final boolean entryContainsSym$1(final ScopeEntry e, final Symbols.Symbol sym$3) {
         while(e != null) {
            Types.Type comparableInfo = sym$3.info().substThis(sym$3.owner(), e.sym().owner());
            if (e.sym().info().$eq$colon$eq(comparableInfo)) {
               return true;
            }

            e = this.lookupNextEntry(e);
         }

         return false;
      }

      private final boolean scopeContainsSym$1(final Symbols.Symbol sym) {
         return this.entryContainsSym$1(this.lookupEntry(sym.name()), sym);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isSubScope$1(final Scope $this, final Symbols.Symbol sym) {
         return $this.scopeContainsSym$1(sym);
      }

      // $FF: synthetic method
      public static final String $anonfun$addString$1(final Symbols.Symbol x$4) {
         return x$4.defString();
      }

      public Scope() {
         if (Scopes.this == null) {
            throw null;
         } else {
            this.$outer = Scopes.this;
            super();
            Scopes.this.scopeCount_$eq(Scopes.this.scopeCount() + 1);
            this.scala$reflect$internal$Scopes$$nestinglevel = 0;
            this.scala$reflect$internal$Scopes$$hashtable = null;
            this.elemsCache = null;
            this.cachedSize = -1;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class EmptyScope$ extends Scope {
      public void enterEntry(final ScopeEntry e) {
         throw this.scala$reflect$internal$Scopes$EmptyScope$$$outer().abort("EmptyScope.enter");
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$EmptyScope$$$outer() {
         return this.$outer;
      }
   }

   public class ErrorScope extends Scope {
      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Scopes$ErrorScope$$$outer() {
         return this.$outer;
      }

      public ErrorScope(final Symbols.Symbol owner) {
      }
   }
}
