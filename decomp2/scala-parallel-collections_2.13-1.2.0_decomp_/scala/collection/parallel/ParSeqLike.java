package scala.collection.parallel;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.generic.AtomicIndexFlag;
import scala.collection.generic.DefaultSignalling;
import scala.collection.generic.DelegatedSignalling;
import scala.collection.generic.Signalling;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.parallel.immutable.Repetition;
import scala.collection.parallel.mutable.ParArray$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;
import scala.util.hashing.MurmurHash3.;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d\u0005dACAA\u0003\u0007\u0003\n1!\u0001\u0002\u0012\"9!\u0011\u0004\u0001\u0005\u0002\tm\u0001b\u0002B\u0012\u0001\u0019\u0005!Q\u0005\u0005\b\u0005[\u0001a\u0011\u0001B\u0018\u0011\u001d\u0011)\u0004\u0001C!\u0005oAqA!\u000f\u0001\t\u0003\u0012Y\u0004C\u0004\u0003H\u0001!\tA!\u0013\u0006\u0011\t=\u0003\u0001)A\t\u0005#B\u0011Ba\u001b\u0001\r#\t\u0019I!\u001c\t\u000f\tU\u0004\u0001\"\u0011\u0003x!9!q\u0010\u0001\u0005\u0006\t\u0015ba\u0002BA\u0001\u0005E!1\u0011\u0005\u000b\u0005\u0017[!\u0011!Q\u0001\n\t\u001d\u0002B\u0003BG\u0017\t\u0015\r\u0011\"\u0001\u0003&!Q!qR\u0006\u0003\u0002\u0003\u0006IAa\n\t\u000f\tE5\u0002\"\u0001\u0003\u0014\"I!QT\u0006A\u0002\u0013%!Q\u0005\u0005\n\u0005?[\u0001\u0019!C\u0005\u0005CC\u0001Ba*\fA\u0003&!q\u0005\u0005\b\u0005S[A\u0011\u0001BV\u0011\u001d\u0011ik\u0003C\u0001\u0005_CqA!-\f\t\u0003\u0011\u0019\fC\u0004\u00036.!)A!\n\t\u000f\t]6\u0002\"\u0001\u0003:\"9!1X\u0006\u0005\u0002\tu\u0006b\u0002Be\u0017\u0011\u0005!1\u001a\u0005\b\u0005/\\A\u0011\tBm\u0011\u001d\u0011Y\u000f\u0001C\u0001\u0005[DqAa=\u0001\t\u0003\u0011)\u0010C\u0004\u0004\u0006\u0001!\taa\u0002\t\u000f\r-\u0001\u0001\"\u0001\u0004\u000e!911\u0002\u0001\u0005\u0002\ru\u0001bBB\u0015\u0001\u0011\u000511\u0006\u0005\b\u0007S\u0001A\u0011AB\u0018\u0011\u001d\u0019)\u0004\u0001C\u0001\u0007oAqa!\u000e\u0001\t\u0003\u0019\t\u0005C\u0004\u0004N\u0001!\taa\u0014\t\u000f\r5\u0003\u0001\"\u0001\u0004T!91\u0011\f\u0001\u0005\u0002\rm\u0003bBB/\u0001\u0011\u00051q\f\u0005\b\u0007c\u0002A\u0011AB:\u0011%\u0019i\tAI\u0001\n\u0003\u0019y\tC\u0004\u0004\u0018\u0002!\te!'\t\u000f\r\u001d\u0006\u0001\"\u0001\u0004*\"91q\u0015\u0001\u0005\u0002\rU\u0006bBBc\u0001\u0011\u00051q\u0019\u0005\b\u0007\u000b\u0004A\u0011ABn\u0011\u001d\u0019i\u000f\u0001C\u0005\u0007_Dq\u0001\"\u0002\u0001\t\u0003!9\u0001C\u0004\u0005\u0016\u0001!\t\u0001b\u0006\t\u000f\u0011\u001d\u0002\u0001\"\u0001\u0005*!9Aq\u0007\u0001\u0005\u0002\u0011e\u0002b\u0002C\u001c\u0001\u0011\u0005Aq\t\u0005\b\t+\u0002A\u0011\u0001C,\u0011\u001d!9\u0007\u0001C!\tSBq\u0001\"\"\u0001\t\u0003!9\tC\u0004\u0005\u001e\u0002!\t\u0001b(\t\u000f\u0011u\u0005\u0001\"\u0001\u0005,\"9Aq\u0017\u0001\u0005\u0002\u0011e\u0006b\u0002Cc\u0001\u0011\u000511\f\u0005\b\u0005/\u0004A\u0011\tCd\u0011\u001d!9\u000e\u0001C!\t3D\u0001\u0002b7\u0001A\u0013EAQ\u001c\u0004\n\tW\u0004\u0001\u0013aI\t\t[D\u0011\"b\u0001@\u0005\u00046\tB!\u001c\u0007\u0013\u0015\u0015\u0001\u0001%A\u0012\u0012\u0015\u001da\u0001CC\u000e\u0001\u0001\u0006\t\"\"\b\t\u0015\u0015\u0015\"I!A!\u0002\u0013\u0011Y\u0010\u0003\u0006\u0004\u0004\t\u0013\t\u0011)A\u0005\u0005OA!\"b\u0001C\u0005\u000b\u0007K\u0011CC\u0014\u0011))YC\u0011B\u0001B\u0003%Q\u0011\u0006\u0005\b\u0005#\u0013E\u0011AC\u0017\u0011%))D\u0011a\u0001\n\u0003)9\u0004C\u0005\u0006:\t\u0003\r\u0011\"\u0001\u0006<!AQq\b\"!B\u0013)\t\u0003C\u0004\u0006J\t#\t!b\u0013\t\u0011\u0015]#\t)C\t\u000b3BqAa/C\t\u0003*y\u0006C\u0004\u0006f\t#\t%b\u001a\t\u000f\u0015-$\t\"\u0011\u0003,\u001aAQQ\u000e\u0001!\u0002#)y\u0007\u0003\u0006\u0006&A\u0013\t\u0011)A\u0005\u0005wD!ba\u0001Q\u0005\u0003\u0005\u000b\u0011\u0002B\u0014\u0011))\u0019\u0001\u0015BCB\u0013EQq\u0005\u0005\u000b\u000bW\u0001&\u0011!Q\u0001\n\u0015%\u0002b\u0002BI!\u0012\u0005QQ\u000f\u0005\n\u000bk\u0001\u0006\u0019!C\u0001\u0005KA\u0011\"\"\u000fQ\u0001\u0004%\t!\" \t\u0011\u0015}\u0002\u000b)Q\u0005\u0005OAq!\"\u0013Q\t\u0003)\u0019\t\u0003\u0005\u0006XA\u0003K\u0011CCE\u0011\u001d\u0011Y\f\u0015C!\u000b\u001bCq!\"\u001aQ\t\u0003*\u0019\nC\u0004\u0006lA#\tEa+\u0007\u0011\u0015]\u0005\u0001)A\t\u000b3C!\"\"\n_\u0005\u0003\u0005\u000b\u0011\u0002B~\u0011))yJ\u0018B\u0001B\u0003%!q\u0005\u0005\u000b\u000b\u0007q&Q1Q\u0005\u0012\u0015\u001d\u0002BCC\u0016=\n\u0005\t\u0015!\u0003\u0006*!9!\u0011\u00130\u0005\u0002\u0015\u0005\u0006\"CC\u001b=\u0002\u0007I\u0011\u0001B\u0013\u0011%)ID\u0018a\u0001\n\u0003)I\u000b\u0003\u0005\u0006@y\u0003\u000b\u0015\u0002B\u0014\u0011\u001d)IE\u0018C\u0001\u000b_C\u0001\"b\u0016_A\u0013EQ1\u0017\u0005\b\u0005wsF\u0011IC\\\u0011\u001d))G\u0018C!\u000b{Cq!b\u001b_\t\u0003\u0012YK\u0002\u0005\u0006B\u0002\u0001\u000b\u0011CCb\u0011))i\u000e\u001cB\u0001B\u0003%Qq\u001c\u0005\u000b\u000b\u0007a'Q1Q\u0005\u0012\u0015\u001d\u0002BCC\u0016Y\n\u0005\t\u0015!\u0003\u0006*!9!\u0011\u00137\u0005\u0002\u0015\u0015\b\"CC\u001bY\u0002\u0007I\u0011ACv\u0011%)I\u0004\u001ca\u0001\n\u0003)i\u000f\u0003\u0005\u0006@1\u0004\u000b\u0015BCe\u0011\u001d)I\u0005\u001cC\u0001\u000bgD\u0001\"b\u0016mA\u0013EQ\u0011 \u0005\b\u000bKbG\u0011IC\u007f\r!1\t\u0001\u0001Q\u0001\u0012\u0019\r\u0001BCB7o\n\u0005\t\u0015!\u0003\u0007\u0016!QaqC<\u0003\u0002\u0003\u0006IA\"\u0007\t\u0015\u0015\rqO!b!\n#)9\u0003\u0003\u0006\u0006,]\u0014\t\u0011)A\u0005\u000bSAqA!%x\t\u00031Y\u0002C\u0005\u00066]\u0004\r\u0011\"\u0001\u0007$!IQ\u0011H<A\u0002\u0013\u0005aQ\u0005\u0005\t\u000b\u007f9\b\u0015)\u0003\u0007\n!9Q\u0011J<\u0005\u0002\u0019-\u0002\u0002CC,o\u0002&\tB\"\r\t\u000f\u0015\u0015t\u000f\"\u0011\u00076\u0019Aa\u0011\b\u0001!\u0002#1Y\u0004C\u0006\u0006\u0004\u0005\u001d!Q1Q\u0005\u0012\u0015\u001d\u0002bCC\u0016\u0003\u000f\u0011\t\u0011)A\u0005\u000bSA1Bb\u0012\u0002\b\t\u0015\r\u0011\"\u0001\u0007J!YaqJA\u0004\u0005\u0003\u0005\u000b\u0011\u0002D&\u0011!\u0011\t*a\u0002\u0005\u0002\u0019E\u0003BCC\u001b\u0003\u000f\u0001\r\u0011\"\u0001\u0003,\"QQ\u0011HA\u0004\u0001\u0004%\tAb\u0016\t\u0013\u0015}\u0012q\u0001Q!\n\tu\u0002\u0002CC%\u0003\u000f!\tA\"\u0018\t\u0013\u0015]\u0013q\u0001Q\u0005\u0012\u0019\r\u0004\u0002\u0003B^\u0003\u000f!\tEb\u001a\t\u0011\u0015\u0015\u0014q\u0001C!\r[B\u0001\"b\u001b\u0002\b\u0011\u0005#1\u0016\u0004\t\rc\u0002\u0001\u0015!\u0005\u0007t!YQqTA\u0012\u0005\u0003\u0005\u000b\u0011\u0002B\u0014\u0011-\u0019\u0019\"a\t\u0003\u0002\u0003\u0006IAb\u001f\t\u0017\u0019]\u00111\u0005B\u0001B\u0003%aQ\u0011\u0005\f\u000b\u0007\t\u0019C!b!\n#)9\u0003C\u0006\u0006,\u0005\r\"\u0011!Q\u0001\n\u0015%\u0002\u0002\u0003BI\u0003G!\tAb#\t\u0015\u0015U\u00121\u0005a\u0001\n\u00031)\n\u0003\u0006\u0006:\u0005\r\u0002\u0019!C\u0001\r/C\u0011\"b\u0010\u0002$\u0001\u0006KA\"\u001f\t\u0011\u0015%\u00131\u0005C\u0001\r;C\u0011\"b\u0016\u0002$\u0001&\tBb)\t\u0011\tm\u00161\u0005C!\rOC\u0001\"\"\u001a\u0002$\u0011\u0005cQ\u0016\u0005\t\u000bW\n\u0019\u0003\"\u0011\u0003,\u001aAa\u0011\u0017\u0001!\u0002#1\u0019\fC\u0006\u0005d\u0005\u0005#\u0011!Q\u0001\n\t\u001d\u0002b\u0003Df\u0003\u0003\u0012\t\u0011)A\u0005\r\u001bD1\"b\u0001\u0002B\t\u0015\r\u0015\"\u0005\u0006(!YQ1FA!\u0005\u0003\u0005\u000b\u0011BC\u0015\u0011-19%!\u0011\u0003\u0006\u0004%\tAb4\t\u0017\u0019=\u0013\u0011\tB\u0001B\u0003%a\u0011\u001b\u0005\t\u0005#\u000b\t\u0005\"\u0001\u0007T\"QQQGA!\u0001\u0004%\tA\"8\t\u0015\u0015e\u0012\u0011\ta\u0001\n\u00031Y\u000fC\u0005\u0006@\u0005\u0005\u0003\u0015)\u0003\u0007`\"AQ\u0011JA!\t\u00031\t\u0010C\u0005\u0006X\u0005\u0005\u0003\u0015\"\u0005\u0007x\"A!1XA!\t\u00032Y\u0010\u0003\u0005\u0006f\u0005\u0005C\u0011\tD\u0000\r!9\u0019\u0001\u0001Q\u0001\u0012\u001d\u0015\u0001bCD\t\u0003?\u0012\t\u0011)A\u0005\u000f'A1\"b\u0001\u0002`\t\u0015\r\u0015\"\u0005\u0006(!YQ1FA0\u0005\u0003\u0005\u000b\u0011BC\u0015\u0011-19%a\u0018\u0003\u0006\u0004%\ta\"\u0006\t\u0017\u0019=\u0013q\fB\u0001B\u0003%qq\u0003\u0005\t\u0005#\u000by\u0006\"\u0001\b\u001a!QQQGA0\u0001\u0004%\tAa+\t\u0015\u0015e\u0012q\fa\u0001\n\u00039\t\u0003C\u0005\u0006@\u0005}\u0003\u0015)\u0003\u0003>!AQ\u0011JA0\t\u000399\u0003C\u0005\u0006X\u0005}\u0003\u0015\"\u0005\b,!A!1XA0\t\u0003:y\u0003\u0003\u0005\u0006f\u0005}C\u0011ID\u001b\u0011!)Y'a\u0018\u0005B\t-\u0006BDD\u001d\u0001A\u0005\u0019\u0011!A\u0005\n\u001dmrq\t\u0005\u000f\u000f\u0013\u0002\u0001\u0013aA\u0001\u0002\u0013%q1JD0\u0005)\u0001\u0016M]*fc2K7.\u001a\u0006\u0005\u0003\u000b\u000b9)\u0001\u0005qCJ\fG\u000e\\3m\u0015\u0011\tI)a#\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0002\u0002\u000e\u0006)1oY1mC\u000e\u0001QCCAJ\u0003S\u000bi,a5\u0002^N9\u0001!!&\u0002\u001e\nM\u0001\u0003BAL\u00033k!!a#\n\t\u0005m\u00151\u0012\u0002\u0007\u0003:L(+\u001a4\u0011\u0019\u0005}\u0015\u0011UAS\u0003w\u000b\t.a7\u000e\u0005\u0005\r\u0015\u0002BAR\u0003\u0007\u0013q\u0002U1s\u0013R,'/\u00192mK2K7.\u001a\t\u0005\u0003O\u000bI\u000b\u0004\u0001\u0005\u0011\u0005-\u0006\u0001\"b\u0001\u0003[\u0013\u0011\u0001V\t\u0005\u0003_\u000b)\f\u0005\u0003\u0002\u0018\u0006E\u0016\u0002BAZ\u0003\u0017\u0013qAT8uQ&tw\r\u0005\u0003\u0002\u0018\u0006]\u0016\u0002BA]\u0003\u0017\u00131!\u00118z!\u0011\t9+!0\u0005\u0011\u0005}\u0006\u0001\"b\u0001\u0003\u0003\u0014!aQ\"\u0016\t\u0005\r\u0017QZ\t\u0005\u0003_\u000b)\r\u0005\u0004\u0002 \u0006\u001d\u00171Z\u0005\u0005\u0003\u0013\f\u0019I\u0001\u0004QCJ\u001cV-\u001d\t\u0005\u0003O\u000bi\r\u0002\u0005\u0002P\u0006u&\u0019AAW\u0005\u0005A\u0006\u0003BAT\u0003'$\u0001\"!6\u0001\t\u000b\u0007\u0011q\u001b\u0002\u0005%\u0016\u0004(/\u0005\u0003\u00020\u0006e\u0007CBAP\u0003\u000f\f)\u000b\u0005\u0003\u0002(\u0006uG\u0001CAp\u0001\u0011\u0015\r!!9\u0003\u0015M+\u0017/^3oi&\fG.\u0005\u0003\u00020\u0006\r(CBAs\u0003S\f\tP\u0002\u0004\u0002h\u0002\u0001\u00111\u001d\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0007\u0003W\fi/!*\u000e\u0005\u0005\u001d\u0015\u0002BAx\u0003\u000f\u00131aU3r!)\tY/a=\u0002&\u0006]\u00181\\\u0005\u0005\u0003k\f9I\u0001\u0004TKF|\u0005o\u001d\t\u0005\u0003s\u0014iA\u0004\u0003\u0002|\n%a\u0002BA\u007f\u0005\u000fqA!a@\u0003\u00065\u0011!\u0011\u0001\u0006\u0005\u0005\u0007\ty)\u0001\u0004=e>|GOP\u0005\u0003\u0003\u001bKA!!#\u0002\f&!!1BAD\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\u0004\u0003\u0012\tI\u0011I\\=D_:\u001cHO\u001d\u0006\u0005\u0005\u0017\t9\t\u0005\u0003\u0002\u0018\nU\u0011\u0002\u0002B\f\u0003\u0017\u0013a!R9vC2\u001c\u0018A\u0002\u0013j]&$H\u0005\u0006\u0002\u0003\u001eA!\u0011q\u0013B\u0010\u0013\u0011\u0011\t#a#\u0003\tUs\u0017\u000e^\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0005\t\u001d\u0002\u0003BAL\u0005SIAAa\u000b\u0002\f\n\u0019\u0011J\u001c;\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005\u0015&\u0011\u0007\u0005\b\u0005g\u0019\u0001\u0019\u0001B\u0014\u0003\u0015Ig\u000eZ3y\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B\u0014\u0003\u0019)\u0017/^1mgR!!Q\bB\"!\u0011\t9Ja\u0010\n\t\t\u0005\u00131\u0012\u0002\b\u0005>|G.Z1o\u0011\u001d\u0011)%\u0002a\u0001\u0003k\u000bA\u0001\u001e5bi\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003>\t-\u0003b\u0002B'\r\u0001\u0007\u0011QW\u0001\u0006_RDWM\u001d\u0002\u0011'V\u0004XM\u001d)be&#XM]1u_J\u0004b!a(\u0003T\t]\u0013\u0002\u0002B+\u0003\u0007\u0013\u0001#\u0013;fe\u0006\u0014G.Z*qY&$H/\u001a:+\t\u0005\u0015&\u0011L\u0016\u0003\u00057\u0002BA!\u0018\u0003h5\u0011!q\f\u0006\u0005\u0005C\u0012\u0019'A\u0005v]\u000eDWmY6fI*!!QMAF\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0005S\u0012yFA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001b\u001d9mSR$XM]\u000b\u0003\u0005_\u0002b!a(\u0003r\u0005\u0015\u0016\u0002\u0002B:\u0003\u0007\u00131bU3r'Bd\u0017\u000e\u001e;fe\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0003zA1\u0011q\u0014B>\u0003KKAA! \u0002\u0004\ny\u0001K]3dSN,7\u000b\u001d7jiR,'/\u0001\u0003tSj,'\u0001C#mK6,g\u000e^:\u0014\u000f-\t)Ja\u001c\u0003\u0006B1\u00111\u001eBD\u0003KKAA!#\u0002\b\n\u0001\")\u001e4gKJ,G-\u0013;fe\u0006$xN]\u0001\u0006gR\f'\u000f^\u0001\u0004K:$\u0017\u0001B3oI\u0002\na\u0001P5oSRtDC\u0002BK\u00053\u0013Y\nE\u0002\u0003\u0018.i\u0011\u0001\u0001\u0005\b\u0005\u0017{\u0001\u0019\u0001B\u0014\u0011\u001d\u0011ii\u0004a\u0001\u0005O\t\u0011![\u0001\u0006S~#S-\u001d\u000b\u0005\u0005;\u0011\u0019\u000bC\u0005\u0003&F\t\t\u00111\u0001\u0003(\u0005\u0019\u0001\u0010J\u0019\u0002\u0005%\u0004\u0013a\u00025bg:+\u0007\u0010^\u000b\u0003\u0005{\tAA\\3yiR\u0011\u0011QU\u0001\u0005Q\u0016\fG-\u0006\u0002\u0002&\u0006I!/Z7bS:LgnZ\u0001\u0004IV\u0004XC\u0001BK\u0003\u0015\u0019\b\u000f\\5u+\t\u0011y\f\u0005\u0004\u0003B\n\u001d'qN\u0007\u0003\u0005\u0007TAA!2\u0002\b\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003_\u0014\u0019-\u0001\u0004qgBd\u0017\u000e\u001e\u000b\u0005\u0005\u007f\u0013i\rC\u0004\u0003Pf\u0001\rA!5\u0002\u000bML'0Z:\u0011\r\u0005]%1\u001bB\u0014\u0013\u0011\u0011).a#\u0003\u0015q\u0012X\r]3bi\u0016$g(\u0001\u0005u_N#(/\u001b8h)\t\u0011Y\u000e\u0005\u0003\u0003^\n\u001dXB\u0001Bp\u0015\u0011\u0011\tOa9\u0002\t1\fgn\u001a\u0006\u0003\u0005K\fAA[1wC&!!\u0011\u001eBp\u0005\u0019\u0019FO]5oO\u0006Y\u0011n\u001d#fM&tW\rZ!u)\u0011\u0011iDa<\t\u000f\tE8\u00041\u0001\u0003(\u0005\u0019\u0011\u000e\u001a=\u0002\u001bM,w-\\3oi2+gn\u001a;i)\u0019\u00119Ca>\u0004\u0002!9!\u0011 \u000fA\u0002\tm\u0018!\u00019\u0011\u0011\u0005]%Q`AS\u0005{IAAa@\u0002\f\nIa)\u001e8di&|g.\r\u0005\b\u0007\u0007a\u0002\u0019\u0001B\u0014\u0003\u00111'o\\7\u0002\u0019A\u0014XMZ5y\u0019\u0016tw\r\u001e5\u0015\t\t\u001d2\u0011\u0002\u0005\b\u0005sl\u0002\u0019\u0001B~\u0003\u001dIg\u000eZ3y\u001f\u001a,Baa\u0004\u0004\u0018Q!!qEB\t\u0011\u001d\u0019\u0019B\ba\u0001\u0007+\tA!\u001a7f[B!\u0011qUB\f\t\u001d\u0019IB\bb\u0001\u00077\u0011\u0011AQ\t\u0005\u0003K\u000b),\u0006\u0003\u0004 \r\u0015BC\u0002B\u0014\u0007C\u00199\u0003C\u0004\u0004\u0014}\u0001\raa\t\u0011\t\u0005\u001d6Q\u0005\u0003\b\u00073y\"\u0019AB\u000e\u0011\u001d\u0019\u0019a\ba\u0001\u0005O\t!\"\u001b8eKb<\u0006.\u001a:f)\u0011\u00119c!\f\t\u000f\te\b\u00051\u0001\u0003|R1!qEB\u0019\u0007gAqA!?\"\u0001\u0004\u0011Y\u0010C\u0004\u0004\u0004\u0005\u0002\rAa\n\u0002\u00171\f7\u000f^%oI\u0016DxJZ\u000b\u0005\u0007s\u0019y\u0004\u0006\u0003\u0003(\rm\u0002bBB\nE\u0001\u00071Q\b\t\u0005\u0003O\u001by\u0004B\u0004\u0004\u001a\t\u0012\raa\u0007\u0016\t\r\r3\u0011\n\u000b\u0007\u0005O\u0019)ea\u0013\t\u000f\rM1\u00051\u0001\u0004HA!\u0011qUB%\t\u001d\u0019Ib\tb\u0001\u00077AqA!$$\u0001\u0004\u00119#\u0001\bmCN$\u0018J\u001c3fq^CWM]3\u0015\t\t\u001d2\u0011\u000b\u0005\b\u0005s$\u0003\u0019\u0001B~)\u0019\u00119c!\u0016\u0004X!9!\u0011`\u0013A\u0002\tm\bb\u0002BGK\u0001\u0007!qE\u0001\be\u00164XM]:f+\t\t\t.\u0001\u0006sKZ,'o]3NCB,Ba!\u0019\u0004hQ!11MB6!\u0019\t9+!0\u0004fA!\u0011qUB4\t\u001d\u0019Ig\nb\u0001\u0003[\u0013\u0011a\u0015\u0005\b\u0007[:\u0003\u0019AB8\u0003\u00051\u0007\u0003CAL\u0005{\f)k!\u001a\u0002\u0015M$\u0018M\u001d;t/&$\b.\u0006\u0003\u0004v\r\u001dEC\u0002B\u001f\u0007o\u001aI\tC\u0004\u0003F!\u0002\ra!\u001f\u0011\r\rm4qPBC\u001d\u0011\t9j! \n\t\t-\u00111R\u0005\u0005\u0007\u0003\u001b\u0019I\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cWM\u0003\u0003\u0003\f\u0005-\u0005\u0003BAT\u0007\u000f#qa!\u001b)\u0005\u0004\u0019Y\u0002C\u0005\u0004\f\"\u0002\n\u00111\u0001\u0003(\u00051qN\u001a4tKR\fAc\u001d;beR\u001cx+\u001b;iI\u0011,g-Y;mi\u0012\u0012T\u0003BBI\u0007++\"aa%+\t\t\u001d\"\u0011\f\u0003\b\u0007SJ#\u0019AB\u000e\u00031\u0019\u0018-\\3FY\u0016lWM\u001c;t+\u0011\u0019Yja)\u0015\t\tu2Q\u0014\u0005\b\u0005\u000bR\u0003\u0019ABP!\u0019\u0019Yha \u0004\"B!\u0011qUBR\t\u001d\u0019)K\u000bb\u0001\u00077\u0011\u0011!V\u0001\tK:$7oV5uQV!11VBZ)\u0011\u0011id!,\t\u000f\t\u00153\u00061\u0001\u00040B1\u0011qTAd\u0007c\u0003B!a*\u00044\u001291\u0011N\u0016C\u0002\rmQ\u0003BB\\\u0007\u0007$BA!\u0010\u0004:\"9!Q\t\u0017A\u0002\rm\u0006CBB>\u0007{\u001b\t-\u0003\u0003\u0004@\u000e\r%\u0001C%uKJ\f'\r\\3\u0011\t\u0005\u001d61\u0019\u0003\b\u0007Sb#\u0019AB\u000e\u0003\u0015\u0001\u0018\r^2i+\u0011\u0019Ima4\u0015\u0011\r-7\u0011[Bj\u0007/\u0004b!a*\u0002>\u000e5\u0007\u0003BAT\u0007\u001f$qa!*.\u0005\u0004\u0019Y\u0002C\u0004\u0004\u00045\u0002\rAa\n\t\u000f\r\u0015W\u00061\u0001\u0004VB1\u00111^Aw\u0007\u001bDqa!7.\u0001\u0004\u00119#\u0001\u0005sKBd\u0017mY3e+\u0011\u0019ina9\u0015\u0011\r}7Q]Bt\u0007W\u0004b!a*\u0002>\u000e\u0005\b\u0003BAT\u0007G$qa!*/\u0005\u0004\u0019Y\u0002C\u0004\u0004\u00049\u0002\rAa\n\t\u000f\r\u0015g\u00061\u0001\u0004jB1\u0011qTAd\u0007CDqa!7/\u0001\u0004\u00119#\u0001\tqCR\u001c\u0007nX:fcV,g\u000e^5bYV!1\u0011_B|)!\u0019\u0019p!?\u0004~\u0012\u0005\u0001CBAT\u0003{\u001b)\u0010\u0005\u0003\u0002(\u000e]HaBBS_\t\u000711\u0004\u0005\b\u0007w|\u0003\u0019\u0001B\u0014\u0003\u001d1'o\\7be\u001eDqa!20\u0001\u0004\u0019y\u0010\u0005\u0004\u0002l\u000658Q\u001f\u0005\b\t\u0007y\u0003\u0019\u0001B\u0014\u0003\u0005\u0011\u0018aB;qI\u0006$X\rZ\u000b\u0005\t\u0013!y\u0001\u0006\u0004\u0005\f\u0011EA1\u0003\t\u0007\u0003O\u000bi\f\"\u0004\u0011\t\u0005\u001dFq\u0002\u0003\b\u0007K\u0003$\u0019AB\u000e\u0011\u001d\u0011\u0019\u0004\ra\u0001\u0005OAqaa\u00051\u0001\u0004!i!A\u0006%a2,8\u000fJ2pY>tWC\u0002C\r\t?!\u0019\u0003\u0006\u0003\u0005\u001c\u0011\u0005\u0002CBAT\u0003{#i\u0002\u0005\u0003\u0002(\u0012}AaBBSc\t\u000711\u0004\u0005\b\u0007'\t\u0004\u0019\u0001C\u000f\t\u001d!)#\rb\u0001\u0003[\u0013A\u0001\u00165bi\u0006YAeY8m_:$\u0003\u000f\\;t+\u0019!Y\u0003\"\r\u00056Q!AQ\u0006C\u001a!\u0019\t9+!0\u00050A!\u0011q\u0015C\u0019\t\u001d\u0019)K\rb\u0001\u00077Aqaa\u00053\u0001\u0004!y\u0003B\u0004\u0005&I\u0012\r!!,\u0002\u000bUt\u0017n\u001c8\u0016\t\u0011mB\u0011\t\u000b\u0005\t{!\u0019\u0005\u0005\u0004\u0002(\u0006uFq\b\t\u0005\u0003O#\t\u0005B\u0004\u0004\u001aM\u0012\raa\u0007\t\u000f\t\u00153\u00071\u0001\u0005FA1\u0011qTAd\t\u007f)B\u0001\"\u0013\u0005PQ!A1\nC)!\u0019\t9+!0\u0005NA!\u0011q\u0015C(\t\u001d\u0019I\u0002\u000eb\u0001\u00077AqA!\u00125\u0001\u0004!\u0019\u0006\u0005\u0004\u0002l\u00065HQJ\u0001\u0006a\u0006$Gk\\\u000b\u0005\t3\"y\u0006\u0006\u0004\u0005\\\u0011\u0005DQ\r\t\u0007\u0003O\u000bi\f\"\u0018\u0011\t\u0005\u001dFq\f\u0003\b\u0007K+$\u0019AB\u000e\u0011\u001d!\u0019'\u000ea\u0001\u0005O\t1\u0001\\3o\u0011\u001d\u0019\u0019\"\u000ea\u0001\t;\n1A_5q+\u0019!Y\u0007b\u001e\u0005|Q!AQ\u000eC?!\u0019\t9+!0\u0005pAA\u0011q\u0013C9\tk\"I(\u0003\u0003\u0005t\u0005-%A\u0002+va2,'\u0007\u0005\u0003\u0002(\u0012]DaBBSm\t\u000711\u0004\t\u0005\u0003O#Y\bB\u0004\u0004jY\u0012\r!!,\t\u000f\t\u0015c\u00071\u0001\u0005\u0000A1\u0011q\u0014CA\tsJA\u0001b!\u0002\u0004\nY\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f\u0003-\u0019wN\u001d:fgB|g\u000eZ:\u0016\t\u0011%Eq\u0013\u000b\u0005\t\u0017#I\n\u0006\u0003\u0003>\u00115\u0005b\u0002B}o\u0001\u0007Aq\u0012\t\u000b\u0003/#\t*!*\u0005\u0016\nu\u0012\u0002\u0002CJ\u0003\u0017\u0013\u0011BR;oGRLwN\u001c\u001a\u0011\t\u0005\u001dFq\u0013\u0003\b\u0007S:$\u0019AAW\u0011\u001d\u0011)e\u000ea\u0001\t7\u0003b!a(\u0002H\u0012U\u0015\u0001\u00023jM\u001a,B\u0001\")\u0005*R!\u0011\u0011\u001bCR\u0011\u001d\u0011)\u0005\u000fa\u0001\tK\u0003b!a(\u0002H\u0012\u001d\u0006\u0003BAT\tS#qa!*9\u0005\u0004\u0019Y\"\u0006\u0003\u0005.\u0012UF\u0003BAi\t_CqA!\u0012:\u0001\u0004!\t\f\u0005\u0004\u0002l\u00065H1\u0017\t\u0005\u0003O#)\fB\u0004\u0004&f\u0012\raa\u0007\u0002\u0013%tG/\u001a:tK\u000e$X\u0003\u0002C^\t\u0007$B!!5\u0005>\"9!Q\t\u001eA\u0002\u0011}\u0006CBAv\u0003[$\t\r\u0005\u0003\u0002(\u0012\rGaBBSu\t\u000711D\u0001\tI&\u001cH/\u001b8diR\u0011A\u0011\u001a\t\u0005\t\u0017$\u0019N\u0004\u0003\u0005N\u0012=\u0007\u0003BA\u0000\u0003\u0017KA\u0001\"5\u0002\f\u00061\u0001K]3eK\u001aLAA!;\u0005V*!A\u0011[AF\u0003\u0015!xnU3r+\t\tI.\u0001\u0003e_^tG\u0003\u0002B8\t?DqA!??\u0001\u0004!\t\u000f\r\u0003\u0005d\u0012\u001d\bCBAP\u0005'\")\u000f\u0005\u0003\u0002(\u0012\u001dH\u0001\u0004Cu\t?\f\t\u0011!A\u0003\u0002\u00055&\u0001\u0003\u0013r[\u0006\u00148\u000eJ\u0019\u0003%A\u000b'oU3r\u0019&\\W-Q2dKN\u001cxN]\u000b\u0007\t_$I\u0010b@\u0014\u000b}\n)\n\"=\u0011\u0011\t]E1\u001fC|\t{LA\u0001\">\u0002\"\nA\u0011iY2fgN|'\u000f\u0005\u0003\u0002(\u0012eHa\u0002C~\u007f\t\u0007\u0011Q\u0016\u0002\u0002%B!\u0011q\u0015C\u0000\t\u001d)\ta\u0010b\u0001\u0003[\u0013!\u0001\u00169\u0002\u0007ALGOA\u000bQCJ\u001cV-\u001d'jW\u0016$&/\u00198tM>\u0014X.\u001a:\u0016\r\u0015%QqBC\n'\u001d\t\u0015QSC\u0006\u000b+\u0001rAa&@\u000b\u001b)\t\u0002\u0005\u0003\u0002(\u0016=Aa\u0002C~\u0003\n\u0007\u0011Q\u0016\t\u0005\u0003O+\u0019\u0002B\u0004\u0006\u0002\u0005\u0013\r!!,\u0011\u0011\t]UqCC\u0007\u000b#IA!\"\u0007\u0002\"\nYAK]1og\u001a|'/\\3s\u00055\u0019VmZ7f]RdUM\\4uQN)!)!&\u0006 A9!qS \u0006\"\u0015\r\u0002\u0003CAL\tc\u00129C!\u0010\u0011\u0007\t]%)\u0001\u0003qe\u0016$WCAC\u0015!\u0019\tyJ!\u001d\u0003X\u0005!\u0001/\u001b;!)!)\u0019#b\f\u00062\u0015M\u0002bBC\u0013\u000f\u0002\u0007!1 \u0005\b\u0007\u00079\u0005\u0019\u0001B\u0014\u0011\u001d)\u0019a\u0012a\u0001\u000bS\taA]3tk2$XCAC\u0011\u0003)\u0011Xm];mi~#S-\u001d\u000b\u0005\u0005;)i\u0004C\u0005\u0003&&\u000b\t\u00111\u0001\u0006\"\u00059!/Z:vYR\u0004\u0003f\u0001&\u0006DA!\u0011qSC#\u0013\u0011)9%a#\u0003\u0011Y|G.\u0019;jY\u0016\fA\u0001\\3bMR!!QDC'\u0011\u001d)ye\u0013a\u0001\u000b#\nA\u0001\u001d:fmB1\u0011qSC*\u000bCIA!\"\u0016\u0002\f\n1q\n\u001d;j_:\f!B\\3x'V\u0014G/Y:l)\u0011\ty+b\u0017\t\u000f\teH\n1\u0001\u0006^A\u0019!qS\u0004\u0016\u0005\u0015\u0005\u0004C\u0002Ba\u0005\u000f,\u0019\u0007\u0005\u0005\u0003\u0018\u0012MX\u0011EC\u0012\u0003\u0015iWM]4f)\u0011\u0011i\"\"\u001b\t\u000f\t\u0015c\n1\u0001\u0006$\u00059\"/Z9vSJ,7o\u0015;sS\u000e$8\u000b\u001d7jiR,'o\u001d\u0002\u000b\u0013:$W\r_,iKJ,7#\u0002)\u0002\u0016\u0016E\u0004c\u0002BL\u007f\t\u001dR1\u000f\t\u0004\u0005/\u0003F\u0003CC:\u000bo*I(b\u001f\t\u000f\u0015\u0015R\u000b1\u0001\u0003|\"911A+A\u0002\t\u001d\u0002bBC\u0002+\u0002\u0007Q\u0011\u0006\u000b\u0005\u0005;)y\bC\u0005\u0003&^\u000b\t\u00111\u0001\u0003(!\u001a\u0001,b\u0011\u0015\t\tuQQ\u0011\u0005\b\u000b\u001fJ\u0006\u0019ACD!\u0019\t9*b\u0015\u0003(Q!\u0011qVCF\u0011\u001d\u0011IP\u0017a\u0001\u000b;*\"!b$\u0011\r\t\u0005'qYCI!!\u00119\nb=\u0003(\u0015MD\u0003\u0002B\u000f\u000b+CqA!\u0012]\u0001\u0004)\u0019H\u0001\bMCN$\u0018J\u001c3fq^CWM]3\u0014\u000by\u000b)*b'\u0011\u000f\t]uHa\n\u0006\u001eB\u0019!q\u00130\u0002\u0007A|7\u000f\u0006\u0005\u0006\u001e\u0016\rVQUCT\u0011\u001d))c\u0019a\u0001\u0005wDq!b(d\u0001\u0004\u00119\u0003C\u0004\u0006\u0004\r\u0004\r!\"\u000b\u0015\t\tuQ1\u0016\u0005\n\u0005K+\u0017\u0011!a\u0001\u0005OA3AZC\")\u0011\u0011i\"\"-\t\u000f\u0015=s\r1\u0001\u0006\bR!\u0011qVC[\u0011\u001d\u0011I\u0010\u001ba\u0001\u000b;*\"!\"/\u0011\r\t\u0005'qYC^!!\u00119\nb=\u0003(\u0015uE\u0003\u0002B\u000f\u000b\u007fCqA!\u0012k\u0001\u0004)iJA\u0004SKZ,'o]3\u0016\r\u0015\u0015W\u0011[Ck'\u0015a\u0017QSCd!\u001d\u00119*QCe\u000b7\u0004\u0002\"a(\u0006L\u0016=W1[\u0005\u0005\u000b\u001b\f\u0019I\u0001\u0005D_6\u0014\u0017N\\3s!\u0011\t9+\"5\u0005\u000f\r\u0015FN1\u0001\u0004\u001cA!\u0011qUCk\t\u001d)9\u000e\u001cb\u0001\u000b3\u0014A\u0001\u00165jgF!\u0011\u0011[A[!\u001d\u00119\n\\Ch\u000b'\f1a\u00192g!\u0019\t9*\"9\u0006J&!Q1]AF\u0005%1UO\\2uS>t\u0007\u0007\u0006\u0004\u0006\\\u0016\u001dX\u0011\u001e\u0005\b\u000b;\u0004\b\u0019ACp\u0011\u001d)\u0019\u0001\u001da\u0001\u000bS)\"!\"3\u0015\t\tuQq\u001e\u0005\n\u0005K\u0013\u0018\u0011!a\u0001\u000b\u0013D3a]C\")\u0011\u0011i\"\">\t\u000f\u0015=C\u000f1\u0001\u0006xB1\u0011qSC*\u000b\u0013$B!b7\u0006|\"9!\u0011`;A\u0002\u0015uC\u0003\u0002B\u000f\u000b\u007fDqA!\u0012w\u0001\u0004)YN\u0001\u0006SKZ,'o]3NCB,bA\"\u0002\u0007\u000e\u0019E1#B<\u0002\u0016\u001a\u001d\u0001c\u0002BL\u0003\u001a%a1\u0003\t\t\u0003?+YMb\u0003\u0007\u0010A!\u0011q\u0015D\u0007\t\u001d\u0019Ig\u001eb\u0001\u0003[\u0003B!a*\u0007\u0012\u00119AQE<C\u0002\u00055\u0006c\u0002BLo\u001a-aq\u0002\t\t\u0003/\u0013i0!*\u0007\f\u0005\u0019\u0001O\u00194\u0011\r\u0005]U\u0011\u001dD\u0005)!1\u0019B\"\b\u0007 \u0019\u0005\u0002bBB7y\u0002\u0007aQ\u0003\u0005\b\r/a\b\u0019\u0001D\r\u0011\u001d)\u0019\u0001 a\u0001\u000bS)\"A\"\u0003\u0015\t\tuaq\u0005\u0005\n\u0005Ks\u0018\u0011!a\u0001\r\u0013A3a`C\")\u0011\u0011iB\"\f\t\u0011\u0015=\u0013\u0011\u0001a\u0001\r_\u0001b!a&\u0006T\u0019%A\u0003\u0002D\n\rgA\u0001B!?\u0002\u0004\u0001\u0007QQ\f\u000b\u0005\u0005;19\u0004\u0003\u0005\u0003F\u0005\u0015\u0001\u0019\u0001D\n\u00051\u0019\u0016-\\3FY\u0016lWM\u001c;t+\u00111iD\"\u0012\u0014\r\u0005\u001d\u0011Q\u0013D !\u001d\u00119j\u0010B\u001f\r\u0003\u0002bAa&\u0002\b\u0019\r\u0003\u0003BAT\r\u000b\"\u0001b!*\u0002\b\t\u000711D\u0001\t_RDWM\u001d9jiV\u0011a1\n\t\u0007\u0003?\u0013\tH\"\u0014+\t\u0019\r#\u0011L\u0001\n_RDWM\u001d9ji\u0002\"bA\"\u0011\u0007T\u0019U\u0003\u0002CC\u0002\u0003#\u0001\r!\"\u000b\t\u0011\u0019\u001d\u0013\u0011\u0003a\u0001\r\u0017\"BA!\b\u0007Z!Q!QUA\u000b\u0003\u0003\u0005\rA!\u0010)\t\u0005]Q1\t\u000b\u0005\u0005;1y\u0006\u0003\u0005\u0006P\u0005e\u0001\u0019\u0001D1!\u0019\t9*b\u0015\u0003>Q!\u0011q\u0016D3\u0011!\u0011I0a\u0007A\u0002\u0015uSC\u0001D5!\u0019\u0011\tMa2\u0007lAA!q\u0013Cz\u0005{1\t\u0005\u0006\u0003\u0003\u001e\u0019=\u0004\u0002\u0003B#\u0003?\u0001\rA\"\u0011\u0003\u000fU\u0003H-\u0019;fIV1aQ\u000fD?\r\u0003\u001bb!a\t\u0002\u0016\u001a]\u0004c\u0002BL\u0003\u001aed1\u0011\t\t\u0003?+YMb\u001f\u0007\u0000A!\u0011q\u0015D?\t!\u0019)+a\tC\u0002\rm\u0001\u0003BAT\r\u0003#\u0001\u0002\"\n\u0002$\t\u0007\u0011Q\u0016\t\t\u0005/\u000b\u0019Cb\u001f\u0007\u0000AA\u0011q\u0014DD\rw2y(\u0003\u0003\u0007\n\u0006\r%aD\"p[\nLg.\u001a:GC\u000e$xN]=\u0015\u0015\u0019\reQ\u0012DH\r#3\u0019\n\u0003\u0005\u0006 \u0006=\u0002\u0019\u0001B\u0014\u0011!\u0019\u0019\"a\fA\u0002\u0019m\u0004\u0002\u0003D\f\u0003_\u0001\rA\"\"\t\u0011\u0015\r\u0011q\u0006a\u0001\u000bS)\"A\"\u001f\u0015\t\tua\u0011\u0014\u0005\u000b\u0005K\u000b\u0019$!AA\u0002\u0019e\u0004\u0006BA\u001b\u000b\u0007\"BA!\b\u0007 \"AQqJA\u001c\u0001\u00041\t\u000b\u0005\u0004\u0002\u0018\u0016Mc\u0011\u0010\u000b\u0005\u0003_3)\u000b\u0003\u0005\u0003z\u0006e\u0002\u0019AC/+\t1I\u000b\u0005\u0004\u0003B\n\u001dg1\u0016\t\t\u0005/#\u0019P\"\u001f\u0007\u0004R!!Q\u0004DX\u0011!\u0011)%!\u0010A\u0002\u0019\r%!\u0004)beN+\u0017\u000fT5lKjK\u0007/\u0006\u0005\u00076\u001a}f1\u0019Dd'\u0019\t\t%!&\u00078B9!qS!\u0007:\u001a%\u0007\u0003CAP\u000b\u00174YL\"2\u0011\u0011\u0005]E\u0011\u000fD_\r\u0003\u0004B!a*\u0007@\u0012A1QUA!\u0005\u0004\u0019Y\u0002\u0005\u0003\u0002(\u001a\rG\u0001CB5\u0003\u0003\u0012\r!!,\u0011\t\u0005\u001dfq\u0019\u0003\t\tK\t\tE1\u0001\u0002.BQ!qSA!\r{3\tM\"2\u0002\u0005\r4\u0007\u0003CAP\r\u000f3YL\"2\u0016\u0005\u0019E\u0007CBAP\u0005c2\t\r\u0006\u0006\u0007J\u001aUgq\u001bDm\r7D\u0001\u0002b\u0019\u0002P\u0001\u0007!q\u0005\u0005\t\r\u0017\fy\u00051\u0001\u0007N\"AQ1AA(\u0001\u0004)I\u0003\u0003\u0005\u0007H\u0005=\u0003\u0019\u0001Di+\t1y\u000e\u0005\u0003\u0007b\u001a\rXBAA!\u0013\u00111)Ob:\u0003\rI+7/\u001e7u\u0013\u00111I/a!\u0003\tQ\u000b7o\u001b\u000b\u0005\u0005;1i\u000f\u0003\u0006\u0003&\u0006M\u0013\u0011!a\u0001\r?DC!!\u0016\u0006DQ!!Q\u0004Dz\u0011!)y%a\u0016A\u0002\u0019U\bCBAL\u000b'2y\u000e\u0006\u0003\u00020\u001ae\b\u0002\u0003B}\u00033\u0002\r!\"\u0018\u0016\u0005\u0019u\bC\u0002Ba\u0005\u000f4I\r\u0006\u0003\u0003\u001e\u001d\u0005\u0001\u0002\u0003B#\u0003;\u0002\rA\"3\u0003\u0017\r{'O]3ta>tGm]\u000b\u0005\u000f\u000f9ya\u0005\u0004\u0002`\u0005Uu\u0011\u0002\t\b\u0005/{$QHD\u0006!\u0019\u00119*a\u0018\b\u000eA!\u0011qUD\b\t!\u0019I'a\u0018C\u0002\u00055\u0016\u0001B2peJ\u0004\"\"a&\u0005\u0012\u0006\u0015vQ\u0002B\u001f+\t99\u0002\u0005\u0004\u0002 \nEtQ\u0002\u000b\t\u000f\u00179Yb\"\b\b !Aq\u0011CA6\u0001\u00049\u0019\u0002\u0003\u0005\u0006\u0004\u0005-\u0004\u0019AC\u0015\u0011!19%a\u001bA\u0002\u001d]A\u0003\u0002B\u000f\u000fGA!B!*\u0002p\u0005\u0005\t\u0019\u0001B\u001fQ\u0011\t\t(b\u0011\u0015\t\tuq\u0011\u0006\u0005\t\u000b\u001f\n\u0019\b1\u0001\u0007bQ!\u0011qVD\u0017\u0011!\u0011I0!\u001eA\u0002\u0015uSCAD\u0019!\u0019\u0011\tMa2\b4AA!q\u0013Cz\u0005{9Y\u0001\u0006\u0003\u0003\u001e\u001d]\u0002\u0002\u0003B#\u0003s\u0002\rab\u0003\u0002%M,\b/\u001a:%g\u0006lW-\u00127f[\u0016tGo]\u000b\u0005\u000f{9)\u0005\u0006\u0003\u0003>\u001d}\u0002\u0002\u0003B#\u0003{\u0002\ra\"\u0011\u0011\r\rm4qPD\"!\u0011\t9k\"\u0012\u0005\u0011\r\u0015\u0016Q\u0010b\u0001\u00077IAaa&\u0002\"\u0006I1/\u001e9fe\u0012R\u0018\u000e]\u000b\u0007\u000f\u001b:)f\"\u0017\u0015\t\u001d=s1\f\t\u0007\u0003O\u000bil\"\u0015\u0011\u0011\u0005]E\u0011OD*\u000f/\u0002B!a*\bV\u0011A1QUA@\u0005\u0004\u0019Y\u0002\u0005\u0003\u0002(\u001eeC\u0001CB5\u0003\u007f\u0012\r!!,\t\u0011\t\u0015\u0013q\u0010a\u0001\u000f;\u0002b!a(\u0005\u0002\u001e]\u0013\u0002\u0002C4\u0003C\u0003"
)
public interface ParSeqLike extends ParIterableLike, Equals {
   // $FF: synthetic method
   boolean scala$collection$parallel$ParSeqLike$$super$sameElements(final IterableOnce that);

   // $FF: synthetic method
   ParSeq scala$collection$parallel$ParSeqLike$$super$zip(final ParIterable that);

   int length();

   Object apply(final int index);

   // $FF: synthetic method
   static int hashCode$(final ParSeqLike $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      return .MODULE$.orderedHash(this, "ParSeq".hashCode());
   }

   // $FF: synthetic method
   static boolean equals$(final ParSeqLike $this, final Object that) {
      return $this.equals(that);
   }

   default boolean equals(final Object that) {
      if (!(that instanceof ParSeq)) {
         return false;
      } else {
         ParSeq var4 = (ParSeq)that;
         return var4 == this || var4.canEqual(this) && this.sameElements(var4);
      }
   }

   // $FF: synthetic method
   static boolean canEqual$(final ParSeqLike $this, final Object other) {
      return $this.canEqual(other);
   }

   default boolean canEqual(final Object other) {
      return true;
   }

   SeqSplitter splitter();

   // $FF: synthetic method
   static PreciseSplitter iterator$(final ParSeqLike $this) {
      return $this.iterator();
   }

   default PreciseSplitter iterator() {
      return this.splitter();
   }

   // $FF: synthetic method
   static int size$(final ParSeqLike $this) {
      return $this.size();
   }

   default int size() {
      return this.length();
   }

   // $FF: synthetic method
   static boolean isDefinedAt$(final ParSeqLike $this, final int idx) {
      return $this.isDefinedAt(idx);
   }

   default boolean isDefinedAt(final int idx) {
      return idx >= 0 && idx < this.length();
   }

   // $FF: synthetic method
   static int segmentLength$(final ParSeqLike $this, final Function1 p, final int from) {
      return $this.segmentLength(p, from);
   }

   default int segmentLength(final Function1 p, final int from) {
      if (from >= this.length()) {
         return 0;
      } else {
         int realfrom = from < 0 ? 0 : from;
         DefaultSignalling ctx = new AtomicIndexFlag() {
            private AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

            public int indexFlag() {
               return AtomicIndexFlag.indexFlag$(this);
            }

            public void setIndexFlag(final int f) {
               AtomicIndexFlag.setIndexFlag$(this, f);
            }

            public void setIndexFlagIfGreater(final int f) {
               AtomicIndexFlag.setIndexFlagIfGreater$(this, f);
            }

            public void setIndexFlagIfLesser(final int f) {
               AtomicIndexFlag.setIndexFlagIfLesser$(this, f);
            }

            public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
               return this.scala$collection$generic$AtomicIndexFlag$$intflag;
            }

            public final void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(final AtomicInteger x$1) {
               this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
            }

            public {
               AtomicIndexFlag.$init$(this);
               Statics.releaseFence();
            }
         };
         ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MAX_VALUE);
         return ((Tuple2)this.tasksupport().executeAndWaitResult(new SegmentLength(p, 0, (SeqSplitter)this.delegatedSignalling2ops((DelegatedSignalling)this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{realfrom, this.length() - realfrom})).apply(1)).assign(ctx))))._1$mcI$sp();
      }
   }

   // $FF: synthetic method
   static int prefixLength$(final ParSeqLike $this, final Function1 p) {
      return $this.prefixLength(p);
   }

   default int prefixLength(final Function1 p) {
      return this.segmentLength(p, 0);
   }

   // $FF: synthetic method
   static int indexOf$(final ParSeqLike $this, final Object elem) {
      return $this.indexOf(elem);
   }

   default int indexOf(final Object elem) {
      return this.indexOf(elem, 0);
   }

   // $FF: synthetic method
   static int indexOf$(final ParSeqLike $this, final Object elem, final int from) {
      return $this.indexOf(elem, from);
   }

   default int indexOf(final Object elem, final int from) {
      return this.indexWhere((x$4) -> BoxesRunTime.boxToBoolean($anonfun$indexOf$1(elem, x$4)), from);
   }

   // $FF: synthetic method
   static int indexWhere$(final ParSeqLike $this, final Function1 p) {
      return $this.indexWhere(p);
   }

   default int indexWhere(final Function1 p) {
      return this.indexWhere(p, 0);
   }

   // $FF: synthetic method
   static int indexWhere$(final ParSeqLike $this, final Function1 p, final int from) {
      return $this.indexWhere(p, from);
   }

   default int indexWhere(final Function1 p, final int from) {
      if (from >= this.length()) {
         return -1;
      } else {
         int realfrom = from < 0 ? 0 : from;
         DefaultSignalling ctx = new AtomicIndexFlag() {
            private AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

            public int indexFlag() {
               return AtomicIndexFlag.indexFlag$(this);
            }

            public void setIndexFlag(final int f) {
               AtomicIndexFlag.setIndexFlag$(this, f);
            }

            public void setIndexFlagIfGreater(final int f) {
               AtomicIndexFlag.setIndexFlagIfGreater$(this, f);
            }

            public void setIndexFlagIfLesser(final int f) {
               AtomicIndexFlag.setIndexFlagIfLesser$(this, f);
            }

            public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
               return this.scala$collection$generic$AtomicIndexFlag$$intflag;
            }

            public final void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(final AtomicInteger x$1) {
               this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
            }

            public {
               AtomicIndexFlag.$init$(this);
               Statics.releaseFence();
            }
         };
         ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MAX_VALUE);
         return BoxesRunTime.unboxToInt(this.tasksupport().executeAndWaitResult(new IndexWhere(p, realfrom, (SeqSplitter)this.delegatedSignalling2ops((DelegatedSignalling)this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{realfrom, this.length() - realfrom})).apply(1)).assign(ctx))));
      }
   }

   // $FF: synthetic method
   static int lastIndexOf$(final ParSeqLike $this, final Object elem) {
      return $this.lastIndexOf(elem);
   }

   default int lastIndexOf(final Object elem) {
      return this.lastIndexWhere((x$5) -> BoxesRunTime.boxToBoolean($anonfun$lastIndexOf$1(elem, x$5)));
   }

   // $FF: synthetic method
   static int lastIndexOf$(final ParSeqLike $this, final Object elem, final int end) {
      return $this.lastIndexOf(elem, end);
   }

   default int lastIndexOf(final Object elem, final int end) {
      return this.lastIndexWhere((x$6) -> BoxesRunTime.boxToBoolean($anonfun$lastIndexOf$2(elem, x$6)), end);
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final ParSeqLike $this, final Function1 p) {
      return $this.lastIndexWhere(p);
   }

   default int lastIndexWhere(final Function1 p) {
      return this.lastIndexWhere(p, this.length() - 1);
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final ParSeqLike $this, final Function1 p, final int end) {
      return $this.lastIndexWhere(p, end);
   }

   default int lastIndexWhere(final Function1 p, final int end) {
      if (end < 0) {
         return -1;
      } else {
         int until = end >= this.length() ? this.length() : end + 1;
         DefaultSignalling ctx = new AtomicIndexFlag() {
            private AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag;

            public int indexFlag() {
               return AtomicIndexFlag.indexFlag$(this);
            }

            public void setIndexFlag(final int f) {
               AtomicIndexFlag.setIndexFlag$(this, f);
            }

            public void setIndexFlagIfGreater(final int f) {
               AtomicIndexFlag.setIndexFlagIfGreater$(this, f);
            }

            public void setIndexFlagIfLesser(final int f) {
               AtomicIndexFlag.setIndexFlagIfLesser$(this, f);
            }

            public AtomicInteger scala$collection$generic$AtomicIndexFlag$$intflag() {
               return this.scala$collection$generic$AtomicIndexFlag$$intflag;
            }

            public final void scala$collection$generic$AtomicIndexFlag$_setter_$scala$collection$generic$AtomicIndexFlag$$intflag_$eq(final AtomicInteger x$1) {
               this.scala$collection$generic$AtomicIndexFlag$$intflag = x$1;
            }

            public {
               AtomicIndexFlag.$init$(this);
               Statics.releaseFence();
            }
         };
         ((AtomicIndexFlag)ctx).setIndexFlag(Integer.MIN_VALUE);
         return BoxesRunTime.unboxToInt(this.tasksupport().executeAndWaitResult(new LastIndexWhere(p, 0, (SeqSplitter)this.delegatedSignalling2ops((DelegatedSignalling)this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{until, this.length() - until})).apply(0)).assign(ctx))));
      }
   }

   // $FF: synthetic method
   static ParSeq reverse$(final ParSeqLike $this) {
      return $this.reverse();
   }

   default ParSeq reverse() {
      return (ParSeq)this.tasksupport().executeAndWaitResult(this.task2ops(new Reverse(() -> this.newCombiner(), this.splitter())).mapResult((x$7) -> (ParSeq)x$7.resultWithTaskSupport()));
   }

   // $FF: synthetic method
   static ParSeq reverseMap$(final ParSeqLike $this, final Function1 f) {
      return $this.reverseMap(f);
   }

   default ParSeq reverseMap(final Function1 f) {
      return (ParSeq)this.tasksupport().executeAndWaitResult(this.task2ops(new ReverseMap(f, () -> this.companion().newCombiner(), this.splitter())).mapResult((x$8) -> (ParSeq)x$8.resultWithTaskSupport()));
   }

   // $FF: synthetic method
   static boolean startsWith$(final ParSeqLike $this, final IterableOnce that, final int offset) {
      return $this.startsWith(that, offset);
   }

   default boolean startsWith(final IterableOnce that, final int offset) {
      if (that instanceof ParSeq) {
         ParSeq var5 = (ParSeq)that;
         if (offset >= 0 && offset < this.length()) {
            if (var5.isEmpty()) {
               return true;
            } else if (var5.length() > this.length() - offset) {
               return false;
            } else {
               DefaultSignalling ctx = new DefaultSignalling() {
               };
               return BoxesRunTime.unboxToBoolean(this.tasksupport().executeAndWaitResult(new SameElements((SeqSplitter)this.delegatedSignalling2ops((DelegatedSignalling)this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{offset, var5.length()})).apply(1)).assign(ctx), var5.splitter())));
            }
         } else {
            return offset == this.length() && var5.isEmpty();
         }
      } else {
         return ((SeqOps)this.seq()).startsWith(that, offset);
      }
   }

   // $FF: synthetic method
   static int startsWith$default$2$(final ParSeqLike $this) {
      return $this.startsWith$default$2();
   }

   default int startsWith$default$2() {
      return 0;
   }

   // $FF: synthetic method
   static boolean sameElements$(final ParSeqLike $this, final IterableOnce that) {
      return $this.sameElements(that);
   }

   default boolean sameElements(final IterableOnce that) {
      if (!(that instanceof ParSeq)) {
         return this.scala$collection$parallel$ParSeqLike$$super$sameElements(that);
      } else {
         ParSeq var4 = (ParSeq)that;
         DefaultSignalling ctx = new DefaultSignalling() {
         };
         return this.length() == var4.length() && BoxesRunTime.unboxToBoolean(this.tasksupport().executeAndWaitResult(new SameElements((SeqSplitter)this.delegatedSignalling2ops(this.splitter()).assign(ctx), var4.splitter())));
      }
   }

   // $FF: synthetic method
   static boolean endsWith$(final ParSeqLike $this, final ParSeq that) {
      return $this.endsWith(that);
   }

   default boolean endsWith(final ParSeq that) {
      if (that.length() == 0) {
         return true;
      } else if (that.length() > this.length()) {
         return false;
      } else {
         DefaultSignalling ctx = new DefaultSignalling() {
         };
         int tlen = that.length();
         return BoxesRunTime.unboxToBoolean(this.tasksupport().executeAndWaitResult(new SameElements((SeqSplitter)this.delegatedSignalling2ops((DelegatedSignalling)this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{this.length() - tlen, tlen})).apply(1)).assign(ctx), that.splitter())));
      }
   }

   // $FF: synthetic method
   static boolean endsWith$(final ParSeqLike $this, final Iterable that) {
      return $this.endsWith(that);
   }

   default boolean endsWith(final Iterable that) {
      return ((SeqOps)this.seq()).endsWith(that);
   }

   // $FF: synthetic method
   static ParSeq patch$(final ParSeqLike $this, final int from, final Seq patch, final int replaced) {
      return $this.patch(from, patch, replaced);
   }

   default ParSeq patch(final int from, final Seq patch, final int replaced) {
      return this.patch_sequential(from, patch, replaced);
   }

   // $FF: synthetic method
   static ParSeq patch$(final ParSeqLike $this, final int from, final ParSeq patch, final int replaced) {
      return $this.patch(from, patch, replaced);
   }

   default ParSeq patch(final int from, final ParSeq patch, final int replaced) {
      int realreplaced = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(replaced), this.length() - from);
      if (this.size() - realreplaced + patch.size() > package$.MODULE$.MIN_FOR_COPY()) {
         ParSeq that = ParallelCollectionImplicits$.MODULE$.traversable2ops(patch).asParSeq();
         scala.collection.immutable.Seq pits = this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{from, replaced, this.length() - from - realreplaced}));
         CombinerFactory cfactory = this.combinerFactory(() -> this.companion().newCombiner());
         ParIterableLike.Copy copystart = new ParIterableLike.Copy(cfactory, (IterableSplitter)pits.apply(0));
         ParIterableLike.NonDivisible copymiddle = this.wrap(() -> {
            ParIterableLike.Copy tsk = that.new Copy(cfactory, that.splitter());
            return (Combiner)this.tasksupport().executeAndWaitResult(tsk);
         });
         ParIterableLike.Copy copyend = new ParIterableLike.Copy(cfactory, (IterableSplitter)pits.apply(2));
         return (ParSeq)this.tasksupport().executeAndWaitResult(this.task2ops(this.task2ops(this.task2ops(copystart).parallel(copymiddle, (x$9, x$10) -> x$9.combine(x$10))).parallel(copyend, (x$11, x$12) -> x$11.combine(x$12))).mapResult((x$13) -> (ParSeq)x$13.resultWithTaskSupport()));
      } else {
         return this.patch_sequential(from, (Seq)patch.seq(), replaced);
      }
   }

   private ParSeq patch_sequential(final int fromarg, final Seq patch, final int r) {
      int from = scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(0), fromarg);
      Combiner b = this.companion().newBuilder();
      int repl = scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(r), this.length() - from)), 0);
      scala.collection.immutable.Seq pits = this.splitter().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{from, repl, this.length() - from - repl}));
      b.$plus$plus$eq((IterableOnce)pits.apply(0));
      b.$plus$plus$eq(patch);
      b.$plus$plus$eq((IterableOnce)pits.apply(2));
      return (ParSeq)package$.MODULE$.setTaskSupport(b.result(), this.tasksupport());
   }

   // $FF: synthetic method
   static ParSeq updated$(final ParSeqLike $this, final int index, final Object elem) {
      return $this.updated(index, elem);
   }

   default ParSeq updated(final int index, final Object elem) {
      return (ParSeq)this.tasksupport().executeAndWaitResult(this.task2ops(new Updated(index, elem, this.combinerFactory(() -> this.companion().newCombiner()), this.splitter())).mapResult((x$14) -> (ParSeq)x$14.resultWithTaskSupport()));
   }

   // $FF: synthetic method
   static ParSeq $plus$colon$(final ParSeqLike $this, final Object elem) {
      return $this.$plus$colon(elem);
   }

   default ParSeq $plus$colon(final Object elem) {
      return this.patch(0, (ParSeq)((ParSeq)ParArray$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{elem}))), 0);
   }

   // $FF: synthetic method
   static ParSeq $colon$plus$(final ParSeqLike $this, final Object elem) {
      return $this.$colon$plus(elem);
   }

   default ParSeq $colon$plus(final Object elem) {
      return this.patch(this.length(), (ParSeq)((ParSeq)ParArray$.MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{elem}))), 0);
   }

   // $FF: synthetic method
   static ParSeq union$(final ParSeqLike $this, final ParSeq that) {
      return $this.union(that);
   }

   default ParSeq union(final ParSeq that) {
      return (ParSeq)this.$plus$plus(that);
   }

   // $FF: synthetic method
   static ParSeq union$(final ParSeqLike $this, final Seq that) {
      return $this.union(that);
   }

   default ParSeq union(final Seq that) {
      return (ParSeq)this.$plus$plus(that);
   }

   // $FF: synthetic method
   static ParSeq padTo$(final ParSeqLike $this, final int len, final Object elem) {
      return $this.padTo(len, elem);
   }

   default ParSeq padTo(final int len, final Object elem) {
      return this.length() < len ? this.patch(this.length(), (ParSeq)(new Repetition(elem, len - this.length())), 0) : this.patch(this.length(), (ParSeq)((ParSeq)ParSeq$.MODULE$.newBuilder().result()), 0);
   }

   // $FF: synthetic method
   static ParSeq zip$(final ParSeqLike $this, final ParIterable that) {
      return $this.zip(that);
   }

   default ParSeq zip(final ParIterable that) {
      if (that instanceof ParSeq) {
         ParSeq var4 = (ParSeq)that;
         return (ParSeq)this.tasksupport().executeAndWaitResult(this.task2ops(new ParSeqLikeZip(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.length()), var4.length()), this.combinerFactory(() -> this.companion().newCombiner()), this.splitter(), var4.splitter())).mapResult((x$15) -> (ParSeq)x$15.resultWithTaskSupport()));
      } else {
         return this.scala$collection$parallel$ParSeqLike$$super$zip(that);
      }
   }

   // $FF: synthetic method
   static boolean corresponds$(final ParSeqLike $this, final ParSeq that, final Function2 p) {
      return $this.corresponds(that, p);
   }

   default boolean corresponds(final ParSeq that, final Function2 p) {
      DefaultSignalling ctx = new DefaultSignalling() {
      };
      return this.length() == that.length() && BoxesRunTime.unboxToBoolean(this.tasksupport().executeAndWaitResult(new Corresponds(p, (SeqSplitter)this.delegatedSignalling2ops(this.splitter()).assign(ctx), that.splitter())));
   }

   // $FF: synthetic method
   static ParSeq diff$(final ParSeqLike $this, final ParSeq that) {
      return $this.diff(that);
   }

   default ParSeq diff(final ParSeq that) {
      return this.diff((Seq)that.seq());
   }

   // $FF: synthetic method
   static ParSeq diff$(final ParSeqLike $this, final Seq that) {
      return $this.diff(that);
   }

   default ParSeq diff(final Seq that) {
      return (ParSeq)this.sequentially((x$16) -> (Seq)x$16.diff(that));
   }

   // $FF: synthetic method
   static ParSeq intersect$(final ParSeqLike $this, final Seq that) {
      return $this.intersect(that);
   }

   default ParSeq intersect(final Seq that) {
      return (ParSeq)this.sequentially((x$17) -> (Seq)x$17.intersect(that));
   }

   // $FF: synthetic method
   static ParSeq distinct$(final ParSeqLike $this) {
      return $this.distinct();
   }

   default ParSeq distinct() {
      return (ParSeq)this.sequentially((x$18) -> (Seq)x$18.distinct());
   }

   // $FF: synthetic method
   static String toString$(final ParSeqLike $this) {
      return $this.toString();
   }

   default String toString() {
      return this.seq().mkString((new StringBuilder(1)).append(this.stringPrefix()).append("(").toString(), ", ", ")");
   }

   // $FF: synthetic method
   static ParSeq toSeq$(final ParSeqLike $this) {
      return $this.toSeq();
   }

   default ParSeq toSeq() {
      return (ParSeq)this;
   }

   // $FF: synthetic method
   static SeqSplitter down$(final ParSeqLike $this, final IterableSplitter p) {
      return $this.down(p);
   }

   default SeqSplitter down(final IterableSplitter p) {
      return (SeqSplitter)p;
   }

   // $FF: synthetic method
   static boolean $anonfun$indexOf$1(final Object elem$1, final Object x$4) {
      return BoxesRunTime.equals(elem$1, x$4);
   }

   // $FF: synthetic method
   static boolean $anonfun$lastIndexOf$1(final Object elem$2, final Object x$5) {
      return BoxesRunTime.equals(elem$2, x$5);
   }

   // $FF: synthetic method
   static boolean $anonfun$lastIndexOf$2(final Object elem$3, final Object x$6) {
      return BoxesRunTime.equals(elem$3, x$6);
   }

   static void $init$(final ParSeqLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class Elements implements SeqSplitter, BufferedIterator {
      public final int scala$collection$parallel$ParSeqLike$Elements$$start;
      private final int end;
      private int scala$collection$parallel$ParSeqLike$Elements$$i;
      private Signalling signalDelegate;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      public Option headOption() {
         return BufferedIterator.headOption$(this);
      }

      public BufferedIterator buffered() {
         return BufferedIterator.buffered$(this);
      }

      public scala.collection.immutable.Seq splitWithSignalling() {
         return SeqSplitter.splitWithSignalling$(this);
      }

      public scala.collection.immutable.Seq psplitWithSignalling(final scala.collection.immutable.Seq sizes) {
         return SeqSplitter.psplitWithSignalling$(this, sizes);
      }

      public SeqSplitter.RemainsIteratorTaken newTaken(final int until) {
         return SeqSplitter.newTaken$(this, until);
      }

      public SeqSplitter take(final int n) {
         return SeqSplitter.take$(this, n);
      }

      public SeqSplitter slice(final int from1, final int until1) {
         return SeqSplitter.slice$(this, from1, until1);
      }

      public SeqSplitter map(final Function1 f) {
         return SeqSplitter.map$(this, f);
      }

      public SeqSplitter.RemainsIteratorAppended appendParSeq(final SeqSplitter that) {
         return SeqSplitter.appendParSeq$(this, that);
      }

      public SeqSplitter zipParSeq(final SeqSplitter that) {
         return SeqSplitter.zipParSeq$(this, that);
      }

      public SeqSplitter.RemainsIteratorZippedAll zipAllParSeq(final SeqSplitter that, final Object thisElem, final Object thatElem) {
         return SeqSplitter.zipAllParSeq$(this, that, thisElem, thatElem);
      }

      public SeqSplitter reverse() {
         return SeqSplitter.reverse$(this);
      }

      public SeqSplitter.Patched patchParSeq(final int from, final SeqSplitter patchElems, final int replaced) {
         return SeqSplitter.patchParSeq$(this, from, patchElems, replaced);
      }

      public int prefixLength(final Function1 pred) {
         return AugmentedSeqIterator.prefixLength$(this, pred);
      }

      public int indexWhere(final Function1 pred) {
         return AugmentedSeqIterator.indexWhere$(this, pred);
      }

      public int lastIndexWhere(final Function1 pred) {
         return AugmentedSeqIterator.lastIndexWhere$(this, pred);
      }

      public boolean corresponds(final Function2 corr, final Iterator that) {
         return AugmentedSeqIterator.corresponds$(this, corr, that);
      }

      public Combiner reverse2combiner(final Combiner cb) {
         return AugmentedSeqIterator.reverse2combiner$(this, cb);
      }

      public Combiner reverseMap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedSeqIterator.reverseMap2combiner$(this, f, cb);
      }

      public Combiner updated2combiner(final int index, final Object elem, final Combiner cb) {
         return AugmentedSeqIterator.updated2combiner$(this, index, elem, cb);
      }

      public boolean shouldSplitFurther(final ParIterable coll, final int parallelismLevel) {
         return IterableSplitter.shouldSplitFurther$(this, coll, parallelismLevel);
      }

      public String buildString(final Function1 closure) {
         return IterableSplitter.buildString$(this, closure);
      }

      public String debugInformation() {
         return IterableSplitter.debugInformation$(this);
      }

      public IterableSplitter.Taken newSliceInternal(final IterableSplitter.Taken it, final int from1) {
         return IterableSplitter.newSliceInternal$(this, it, from1);
      }

      public IterableSplitter drop(final int n) {
         return IterableSplitter.drop$(this, n);
      }

      public IterableSplitter.Appended appendParIterable(final IterableSplitter that) {
         return IterableSplitter.appendParIterable$(this, that);
      }

      public boolean isAborted() {
         return DelegatedSignalling.isAborted$(this);
      }

      public void abort() {
         DelegatedSignalling.abort$(this);
      }

      public int indexFlag() {
         return DelegatedSignalling.indexFlag$(this);
      }

      public void setIndexFlag(final int f) {
         DelegatedSignalling.setIndexFlag$(this, f);
      }

      public void setIndexFlagIfGreater(final int f) {
         DelegatedSignalling.setIndexFlagIfGreater$(this, f);
      }

      public void setIndexFlagIfLesser(final int f) {
         DelegatedSignalling.setIndexFlagIfLesser$(this, f);
      }

      public int tag() {
         return DelegatedSignalling.tag$(this);
      }

      public int count(final Function1 p) {
         return AugmentedIterableIterator.count$(this, p);
      }

      public Object reduce(final Function2 op) {
         return AugmentedIterableIterator.reduce$(this, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return AugmentedIterableIterator.fold$(this, z, op);
      }

      public Object sum(final Numeric num) {
         return AugmentedIterableIterator.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return AugmentedIterableIterator.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return AugmentedIterableIterator.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return AugmentedIterableIterator.max$(this, ord);
      }

      public Object reduceLeft(final int howmany, final Function2 op) {
         return AugmentedIterableIterator.reduceLeft$(this, howmany, op);
      }

      public Combiner map2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.map2combiner$(this, f, cb);
      }

      public Combiner collect2combiner(final PartialFunction pf, final Combiner cb) {
         return AugmentedIterableIterator.collect2combiner$(this, pf, cb);
      }

      public Combiner flatmap2combiner(final Function1 f, final Combiner cb) {
         return AugmentedIterableIterator.flatmap2combiner$(this, f, cb);
      }

      public Builder copy2builder(final Builder b) {
         return AugmentedIterableIterator.copy2builder$(this, b);
      }

      public Combiner filter2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filter2combiner$(this, pred, cb);
      }

      public Combiner filterNot2combiner(final Function1 pred, final Combiner cb) {
         return AugmentedIterableIterator.filterNot2combiner$(this, pred, cb);
      }

      public Tuple2 partition2combiners(final Function1 pred, final Combiner btrue, final Combiner bfalse) {
         return AugmentedIterableIterator.partition2combiners$(this, pred, btrue, bfalse);
      }

      public Combiner take2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.take2combiner$(this, n, cb);
      }

      public Combiner drop2combiner(final int n, final Combiner cb) {
         return AugmentedIterableIterator.drop2combiner$(this, n, cb);
      }

      public Combiner slice2combiner(final int from, final int until, final Combiner cb) {
         return AugmentedIterableIterator.slice2combiner$(this, from, until, cb);
      }

      public Tuple2 splitAt2combiners(final int at, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.splitAt2combiners$(this, at, before, after);
      }

      public Tuple2 takeWhile2combiner(final Function1 p, final Combiner cb) {
         return AugmentedIterableIterator.takeWhile2combiner$(this, p, cb);
      }

      public Tuple2 span2combiners(final Function1 p, final Combiner before, final Combiner after) {
         return AugmentedIterableIterator.span2combiners$(this, p, before, after);
      }

      public void scanToArray(final Object z, final Function2 op, final Object array, final int from) {
         AugmentedIterableIterator.scanToArray$(this, z, op, array, from);
      }

      public Combiner scanToCombiner(final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, startValue, op, cb);
      }

      public Combiner scanToCombiner(final int howmany, final Object startValue, final Function2 op, final Combiner cb) {
         return AugmentedIterableIterator.scanToCombiner$(this, howmany, startValue, op, cb);
      }

      public Combiner zip2combiner(final RemainsIterator otherpit, final Combiner cb) {
         return AugmentedIterableIterator.zip2combiner$(this, otherpit, cb);
      }

      public Combiner zipAll2combiner(final RemainsIterator that, final Object thiselem, final Object thatelem, final Combiner cb) {
         return AugmentedIterableIterator.zipAll2combiner$(this, that, thiselem, thatelem, cb);
      }

      public boolean isRemainingCheap() {
         return RemainsIterator.isRemainingCheap$(this);
      }

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
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

      public int size() {
         return IterableOnceOps.size$(this);
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

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
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

      public scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
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

      public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public scala.collection.immutable.Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
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

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      public Signalling signalDelegate() {
         return this.signalDelegate;
      }

      public void signalDelegate_$eq(final Signalling x$1) {
         this.signalDelegate = x$1;
      }

      public int end() {
         return this.end;
      }

      public int scala$collection$parallel$ParSeqLike$Elements$$i() {
         return this.scala$collection$parallel$ParSeqLike$Elements$$i;
      }

      private void i_$eq(final int x$1) {
         this.scala$collection$parallel$ParSeqLike$Elements$$i = x$1;
      }

      public boolean hasNext() {
         return this.scala$collection$parallel$ParSeqLike$Elements$$i() < this.end();
      }

      public Object next() {
         if (this.scala$collection$parallel$ParSeqLike$Elements$$i() < this.end()) {
            Object x = this.scala$collection$parallel$ParSeqLike$Elements$$$outer().apply(this.scala$collection$parallel$ParSeqLike$Elements$$i());
            this.i_$eq(this.scala$collection$parallel$ParSeqLike$Elements$$i() + 1);
            return x;
         } else {
            return scala.collection.Iterator..MODULE$.empty().next();
         }
      }

      public Object head() {
         return this.scala$collection$parallel$ParSeqLike$Elements$$$outer().apply(this.scala$collection$parallel$ParSeqLike$Elements$$i());
      }

      public final int remaining() {
         return this.end() - this.scala$collection$parallel$ParSeqLike$Elements$$i();
      }

      public Elements dup() {
         return new Elements() {
         };
      }

      public scala.collection.immutable.Seq split() {
         return this.psplit(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{this.remaining() / 2, this.remaining() - this.remaining() / 2}));
      }

      public scala.collection.immutable.Seq psplit(final scala.collection.immutable.Seq sizes) {
         scala.collection.immutable.Seq incr = (scala.collection.immutable.Seq)sizes.scanLeft(BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(x$1, x$2) -> x$1 + x$2);
         return (scala.collection.immutable.Seq)((IterableOps)((IterableOps)incr.init()).zip((IterableOnce)incr.tail())).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$psplit$2(check$ifrefutable$1))).map((x$3) -> {
            if (x$3 != null) {
               int from = x$3._1$mcI$sp();
               int until = x$3._2$mcI$sp();
               return this.new Elements(from, until) {
               };
            } else {
               throw new MatchError(x$3);
            }
         });
      }

      public String toString() {
         return (new StringBuilder(12)).append("Elements(").append(this.scala$collection$parallel$ParSeqLike$Elements$$start).append(", ").append(this.end()).append(")").toString();
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$Elements$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$psplit$2(final Tuple2 check$ifrefutable$1) {
         return check$ifrefutable$1 != null;
      }

      public Elements(final int start, final int end) {
         this.scala$collection$parallel$ParSeqLike$Elements$$start = start;
         this.end = end;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            RemainsIterator.$init$(this);
            AugmentedIterableIterator.$init$(this);
            DelegatedSignalling.$init$(this);
            IterableSplitter.$init$(this);
            AugmentedSeqIterator.$init$(this);
            SeqSplitter.$init$(this);
            BufferedIterator.$init$(this);
            this.scala$collection$parallel$ParSeqLike$Elements$$i = start;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class SegmentLength implements ParSeqLikeAccessor {
      private final Function1 pred;
      private final int from;
      private final SeqSplitter pit;
      private volatile Tuple2 result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public Tuple2 result() {
         return this.result;
      }

      public void result_$eq(final Tuple2 x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (this.from < this.pit().indexFlag()) {
            int itsize = this.pit().remaining();
            int seglen = this.pit().prefixLength(this.pred);
            this.result_$eq((Tuple2)(new Tuple2.mcIZ.sp(seglen, itsize == seglen)));
            if (!this.result()._2$mcZ$sp()) {
               this.pit().setIndexFlagIfLesser(this.from);
            }
         } else {
            this.result_$eq((Tuple2)(new Tuple2.mcIZ.sp(0, false)));
         }
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         scala.collection.immutable.Seq pits = this.pit().splitWithSignalling();
         return (scala.collection.immutable.Seq)((IterableOps)pits.zip((IterableOnce)pits.scanLeft(BoxesRunTime.boxToInteger(0), (x$19, x$20) -> BoxesRunTime.boxToInteger($anonfun$split$1(BoxesRunTime.unboxToInt(x$19), x$20))))).withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$split$2(check$ifrefutable$2))).map((x$21) -> {
            if (x$21 != null) {
               SeqSplitter p = (SeqSplitter)x$21._1();
               int untilp = x$21._2$mcI$sp();
               return this.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer().new SegmentLength(this.pred, this.from + untilp, p);
            } else {
               throw new MatchError(x$21);
            }
         });
      }

      public void merge(final SegmentLength that) {
         if (this.result()._2$mcZ$sp()) {
            this.result_$eq((Tuple2)(new Tuple2.mcIZ.sp(this.result()._1$mcI$sp() + that.result()._1$mcI$sp(), that.result()._2$mcZ$sp())));
         }
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$SegmentLength$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$SegmentLength$$$outer();
      }

      // $FF: synthetic method
      public static final int $anonfun$split$1(final int x$19, final SeqSplitter x$20) {
         return x$19 + x$20.remaining();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$2(final Tuple2 check$ifrefutable$2) {
         return check$ifrefutable$2 != null;
      }

      public SegmentLength(final Function1 pred, final int from, final SeqSplitter pit) {
         this.pred = pred;
         this.from = from;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = null;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class IndexWhere implements ParSeqLikeAccessor {
      private final Function1 pred;
      private final int from;
      private final SeqSplitter pit;
      private volatile int result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public int result() {
         return this.result;
      }

      public void result_$eq(final int x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (this.from < this.pit().indexFlag()) {
            int r = this.pit().indexWhere(this.pred);
            if (r != -1) {
               this.result_$eq(this.from + r);
               this.pit().setIndexFlagIfLesser(this.from);
            }
         }
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         scala.collection.immutable.Seq pits = this.pit().splitWithSignalling();
         return (scala.collection.immutable.Seq)((IterableOps)pits.zip((IterableOnce)pits.scanLeft(BoxesRunTime.boxToInteger(this.from), (x$22, x$23) -> BoxesRunTime.boxToInteger($anonfun$split$4(BoxesRunTime.unboxToInt(x$22), x$23))))).withFilter((check$ifrefutable$3) -> BoxesRunTime.boxToBoolean($anonfun$split$5(check$ifrefutable$3))).map((x$24) -> {
            if (x$24 != null) {
               SeqSplitter p = (SeqSplitter)x$24._1();
               int untilp = x$24._2$mcI$sp();
               return this.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer().new IndexWhere(this.pred, untilp, p);
            } else {
               throw new MatchError(x$24);
            }
         });
      }

      public void merge(final IndexWhere that) {
         this.result_$eq(this.result() == -1 ? that.result() : (that.result() != -1 ? scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.result()), that.result()) : this.result()));
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$IndexWhere$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$IndexWhere$$$outer();
      }

      // $FF: synthetic method
      public static final int $anonfun$split$4(final int x$22, final SeqSplitter x$23) {
         return x$22 + x$23.remaining();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$5(final Tuple2 check$ifrefutable$3) {
         return check$ifrefutable$3 != null;
      }

      public IndexWhere(final Function1 pred, final int from, final SeqSplitter pit) {
         this.pred = pred;
         this.from = from;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = -1;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class LastIndexWhere implements ParSeqLikeAccessor {
      private final Function1 pred;
      private final int pos;
      private final SeqSplitter pit;
      private volatile int result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public int result() {
         return this.result;
      }

      public void result_$eq(final int x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (this.pos > this.pit().indexFlag()) {
            int r = this.pit().lastIndexWhere(this.pred);
            if (r != -1) {
               this.result_$eq(this.pos + r);
               this.pit().setIndexFlagIfGreater(this.pos);
            }
         }
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         scala.collection.immutable.Seq pits = this.pit().splitWithSignalling();
         return (scala.collection.immutable.Seq)((IterableOps)pits.zip((IterableOnce)pits.scanLeft(BoxesRunTime.boxToInteger(this.pos), (x$25, x$26) -> BoxesRunTime.boxToInteger($anonfun$split$7(BoxesRunTime.unboxToInt(x$25), x$26))))).withFilter((check$ifrefutable$4) -> BoxesRunTime.boxToBoolean($anonfun$split$8(check$ifrefutable$4))).map((x$27) -> {
            if (x$27 != null) {
               SeqSplitter p = (SeqSplitter)x$27._1();
               int untilp = x$27._2$mcI$sp();
               return this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer().new LastIndexWhere(this.pred, untilp, p);
            } else {
               throw new MatchError(x$27);
            }
         });
      }

      public void merge(final LastIndexWhere that) {
         this.result_$eq(this.result() == -1 ? that.result() : (that.result() != -1 ? scala.runtime.RichInt..MODULE$.max$extension(scala.Predef..MODULE$.intWrapper(this.result()), that.result()) : this.result()));
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$LastIndexWhere$$$outer();
      }

      // $FF: synthetic method
      public static final int $anonfun$split$7(final int x$25, final SeqSplitter x$26) {
         return x$25 + x$26.remaining();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$8(final Tuple2 check$ifrefutable$4) {
         return check$ifrefutable$4 != null;
      }

      public LastIndexWhere(final Function1 pred, final int pos, final SeqSplitter pit) {
         this.pred = pred;
         this.pos = pos;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = -1;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Reverse implements ParSeqLikeTransformer {
      private final Function0 cbf;
      private final SeqSplitter pit;
      private volatile Combiner result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public scala.collection.immutable.Seq split() {
         return ParIterableLike.Accessor.split$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public boolean requiresStrictSplitters() {
         return ParIterableLike.StrictSplitterCheckTask.requiresStrictSplitters$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public Combiner result() {
         return this.result;
      }

      public void result_$eq(final Combiner x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         this.result_$eq(this.pit().reverse2combiner(this.scala$collection$parallel$ParSeqLike$Reverse$$$outer().reuse(prev, (Combiner)this.cbf.apply())));
      }

      public Reverse newSubtask(final IterableSplitter p) {
         return this.scala$collection$parallel$ParSeqLike$Reverse$$$outer().new Reverse(this.cbf, this.scala$collection$parallel$ParSeqLike$Reverse$$$outer().down(p));
      }

      public void merge(final Reverse that) {
         this.result_$eq(that.result().combine(this.result()));
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$Reverse$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Reverse$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Reverse$$$outer();
      }

      public Reverse(final Function0 cbf, final SeqSplitter pit) {
         this.cbf = cbf;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = null;
         }
      }
   }

   public class ReverseMap implements ParSeqLikeTransformer {
      private final Function1 f;
      private final Function0 pbf;
      private final SeqSplitter pit;
      private volatile Combiner result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public scala.collection.immutable.Seq split() {
         return ParIterableLike.Accessor.split$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public boolean requiresStrictSplitters() {
         return ParIterableLike.StrictSplitterCheckTask.requiresStrictSplitters$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public Combiner result() {
         return this.result;
      }

      public void result_$eq(final Combiner x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         this.result_$eq(this.pit().reverseMap2combiner(this.f, (Combiner)this.pbf.apply()));
      }

      public ReverseMap newSubtask(final IterableSplitter p) {
         return this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer().new ReverseMap(this.f, this.pbf, this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer().down(p));
      }

      public void merge(final ReverseMap that) {
         this.result_$eq(that.result().combine(this.result()));
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$ReverseMap$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$ReverseMap$$$outer();
      }

      public ReverseMap(final Function1 f, final Function0 pbf, final SeqSplitter pit) {
         this.f = f;
         this.pbf = pbf;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = null;
         }
      }
   }

   public class SameElements implements ParSeqLikeAccessor {
      private final SeqSplitter pit;
      private final SeqSplitter otherpit;
      private volatile boolean result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public SeqSplitter otherpit() {
         return this.otherpit;
      }

      public boolean result() {
         return this.result;
      }

      public void result_$eq(final boolean x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (!this.pit().isAborted()) {
            this.result_$eq(this.pit().sameElements(this.otherpit()));
            if (!this.result()) {
               this.pit().abort();
            }
         }
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         int fp = this.pit().remaining() / 2;
         int sp = this.pit().remaining() - fp;
         return (scala.collection.immutable.Seq)((IterableOps)this.pit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp})).zip(this.otherpit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp})))).withFilter((check$ifrefutable$5) -> BoxesRunTime.boxToBoolean($anonfun$split$10(check$ifrefutable$5))).map((x$28) -> {
            if (x$28 != null) {
               SeqSplitter p = (SeqSplitter)x$28._1();
               SeqSplitter op = (SeqSplitter)x$28._2();
               return this.scala$collection$parallel$ParSeqLike$SameElements$$$outer().new SameElements(p, op);
            } else {
               throw new MatchError(x$28);
            }
         });
      }

      public void merge(final SameElements that) {
         this.result_$eq(this.result() && that.result());
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$SameElements$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$SameElements$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$SameElements$$$outer();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$10(final Tuple2 check$ifrefutable$5) {
         return check$ifrefutable$5 != null;
      }

      public SameElements(final SeqSplitter pit, final SeqSplitter otherpit) {
         this.pit = pit;
         this.otherpit = otherpit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = true;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class Updated implements ParSeqLikeTransformer {
      private final int pos;
      private final Object elem;
      private final CombinerFactory pbf;
      private final SeqSplitter pit;
      private volatile Combiner result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public Combiner result() {
         return this.result;
      }

      public void result_$eq(final Combiner x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         this.result_$eq(this.pit().updated2combiner(this.pos, this.elem, this.pbf.apply()));
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         scala.collection.immutable.Seq pits = this.pit().splitWithSignalling();
         return (scala.collection.immutable.Seq)((IterableOps)pits.zip((IterableOnce)pits.scanLeft(BoxesRunTime.boxToInteger(0), (x$29, x$30) -> BoxesRunTime.boxToInteger($anonfun$split$12(BoxesRunTime.unboxToInt(x$29), x$30))))).withFilter((check$ifrefutable$6) -> BoxesRunTime.boxToBoolean($anonfun$split$13(check$ifrefutable$6))).map((x$31) -> {
            if (x$31 != null) {
               SeqSplitter p = (SeqSplitter)x$31._1();
               int untilp = x$31._2$mcI$sp();
               return this.scala$collection$parallel$ParSeqLike$Updated$$$outer().new Updated(this.pos - untilp, this.elem, this.pbf, p);
            } else {
               throw new MatchError(x$31);
            }
         });
      }

      public void merge(final Updated that) {
         this.result_$eq(this.result().combine(that.result()));
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$Updated$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Updated$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Updated$$$outer();
      }

      // $FF: synthetic method
      public static final int $anonfun$split$12(final int x$29, final SeqSplitter x$30) {
         return x$29 + x$30.remaining();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$13(final Tuple2 check$ifrefutable$6) {
         return check$ifrefutable$6 != null;
      }

      public Updated(final int pos, final Object elem, final CombinerFactory pbf, final SeqSplitter pit) {
         this.pos = pos;
         this.elem = elem;
         this.pbf = pbf;
         this.pit = pit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = null;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ParSeqLikeZip implements ParSeqLikeTransformer {
      private final int len;
      private final CombinerFactory cf;
      private final SeqSplitter pit;
      private final SeqSplitter otherpit;
      private volatile Combiner result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public boolean requiresStrictSplitters() {
         return ParIterableLike.StrictSplitterCheckTask.requiresStrictSplitters$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public SeqSplitter otherpit() {
         return this.otherpit;
      }

      public Combiner result() {
         return this.result;
      }

      public void result_$eq(final Combiner x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         this.result_$eq(this.pit().zip2combiner(this.otherpit(), this.cf.apply()));
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         int fp = this.len / 2;
         int sp = this.len - this.len / 2;
         scala.collection.immutable.Seq pits = this.pit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp}));
         scala.collection.immutable.Seq opits = this.otherpit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp}));
         return new scala.collection.immutable..colon.colon(this.scala$collection$parallel$ParSeqLike$ParSeqLikeZip$$$outer().new ParSeqLikeZip(fp, this.cf, (SeqSplitter)pits.apply(0), (SeqSplitter)opits.apply(0)), new scala.collection.immutable..colon.colon(this.scala$collection$parallel$ParSeqLike$ParSeqLikeZip$$$outer().new ParSeqLikeZip(sp, this.cf, (SeqSplitter)pits.apply(1), (SeqSplitter)opits.apply(1)), scala.collection.immutable.Nil..MODULE$));
      }

      public void merge(final ParSeqLikeZip that) {
         this.result_$eq(this.result().combine(that.result()));
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$ParSeqLikeZip$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$ParSeqLikeZip$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$ParSeqLikeZip$$$outer();
      }

      public ParSeqLikeZip(final int len, final CombinerFactory cf, final SeqSplitter pit, final SeqSplitter otherpit) {
         this.len = len;
         this.cf = cf;
         this.pit = pit;
         this.otherpit = otherpit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = null;
         }
      }
   }

   public class Corresponds implements ParSeqLikeAccessor {
      private final Function2 corr;
      private final SeqSplitter pit;
      private final SeqSplitter otherpit;
      private volatile boolean result;
      private volatile Throwable throwable;
      // $FF: synthetic field
      public final ParSeqLike $outer;

      // $FF: synthetic method
      public String scala$collection$parallel$ParIterableLike$Accessor$$super$toString() {
         return super.toString();
      }

      public boolean shouldSplitFurther() {
         return ParIterableLike.Accessor.shouldSplitFurther$(this);
      }

      public void signalAbort() {
         ParIterableLike.Accessor.signalAbort$(this);
      }

      public String toString() {
         return ParIterableLike.Accessor.toString$(this);
      }

      public Object repr() {
         return Task.repr$(this);
      }

      public void forwardThrowable() {
         Task.forwardThrowable$(this);
      }

      public void tryLeaf(final Option lastres) {
         Task.tryLeaf$(this, lastres);
      }

      public void tryMerge(final Object t) {
         Task.tryMerge$(this, t);
      }

      public void mergeThrowables(final Task that) {
         Task.mergeThrowables$(this, that);
      }

      public Throwable throwable() {
         return this.throwable;
      }

      public void throwable_$eq(final Throwable x$1) {
         this.throwable = x$1;
      }

      public SeqSplitter pit() {
         return this.pit;
      }

      public SeqSplitter otherpit() {
         return this.otherpit;
      }

      public boolean result() {
         return this.result;
      }

      public void result_$eq(final boolean x$1) {
         this.result = x$1;
      }

      public void leaf(final Option prev) {
         if (!this.pit().isAborted()) {
            this.result_$eq(this.pit().corresponds(this.corr, this.otherpit()));
            if (!this.result()) {
               this.pit().abort();
            }
         }
      }

      public Nothing newSubtask(final IterableSplitter p) {
         throw new UnsupportedOperationException();
      }

      public scala.collection.immutable.Seq split() {
         int fp = this.pit().remaining() / 2;
         int sp = this.pit().remaining() - fp;
         return (scala.collection.immutable.Seq)((IterableOps)this.pit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp})).zip(this.otherpit().psplitWithSignalling(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{fp, sp})))).withFilter((check$ifrefutable$7) -> BoxesRunTime.boxToBoolean($anonfun$split$15(check$ifrefutable$7))).map((x$32) -> {
            if (x$32 != null) {
               SeqSplitter p = (SeqSplitter)x$32._1();
               SeqSplitter op = (SeqSplitter)x$32._2();
               return this.scala$collection$parallel$ParSeqLike$Corresponds$$$outer().new Corresponds(this.corr, p, op);
            } else {
               throw new MatchError(x$32);
            }
         });
      }

      public void merge(final Corresponds that) {
         this.result_$eq(this.result() && that.result());
      }

      public boolean requiresStrictSplitters() {
         return true;
      }

      // $FF: synthetic method
      public ParSeqLike scala$collection$parallel$ParSeqLike$Corresponds$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$Accessor$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Corresponds$$$outer();
      }

      // $FF: synthetic method
      public ParIterableLike scala$collection$parallel$ParIterableLike$StrictSplitterCheckTask$$$outer() {
         return this.scala$collection$parallel$ParSeqLike$Corresponds$$$outer();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$split$15(final Tuple2 check$ifrefutable$7) {
         return check$ifrefutable$7 != null;
      }

      public Corresponds(final Function2 corr, final SeqSplitter pit, final SeqSplitter otherpit) {
         this.corr = corr;
         this.pit = pit;
         this.otherpit = otherpit;
         if (ParSeqLike.this == null) {
            throw null;
         } else {
            this.$outer = ParSeqLike.this;
            super();
            Task.$init$(this);
            ParIterableLike.StrictSplitterCheckTask.$init$(this);
            ParIterableLike.Accessor.$init$(this);
            this.result = true;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface ParSeqLikeAccessor extends ParIterableLike.Accessor {
      SeqSplitter pit();
   }

   public interface ParSeqLikeTransformer extends ParSeqLikeAccessor, ParIterableLike.Transformer {
   }
}
