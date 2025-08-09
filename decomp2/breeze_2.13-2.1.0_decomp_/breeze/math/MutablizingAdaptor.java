package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import breeze.util.Isomorphism;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005!%bACAV\u0003[\u0003\n1!\u0001\u00028\"9\u0011q\u0019\u0001\u0005\u0002\u0005%GaBAi\u0001\t\u0005\u00111\u001b\u0005\n\u0003C\u0004!\u0019!D\u0001\u0003GD\u0011Ba\u0001\u0001\u0005\u00045\u0019A!\u0002\t\u0013\tm\u0001A1A\u0005\u0004\tu\u0001b\u0002B\u0016\u0001\u0011\r!Q\u0006\u0005\b\u0005g\u0001a\u0011\u0001B\u001b\u0011\u001d\u0011Y\u0004\u0001D\u0001\u0005{9\u0001Ba\u0011\u0002.\"\u0005!Q\t\u0004\t\u0003W\u000bi\u000b#\u0001\u0003J!9!1\n\u0006\u0005\u0002\t5\u0003b\u0002B(\u0015\u0011\u0005!\u0011\u000b\u0005\b\u0005\u001fRA\u0011\u0001B9\u0011\u001d\u0011yE\u0003C\u0001\u0005\u001fCqAa\u0014\u000b\t\u0003\u00119\u000eC\u0004\u0003P)!\taa\u0001\u0007\r\r=\"\u0002QB\u0019\u0011)\t\t/\u0005BK\u0002\u0013\u000511\u000e\u0005\u000b\u0007_\n\"\u0011#Q\u0001\n\r5\u0004b\u0002B&#\u0011\u00051\u0011O\u0003\u0007\u0003#\f\u0002a!\u0012\t\u0013\t\r\u0011C1A\u0005\u0004\re\u0004\u0002CBA#\u0001\u0006Iaa\u001f\t\u000f\tM\u0012\u0003\"\u0001\u0004\u0004\"9!1H\t\u0005\u0002\r\u001d\u0005\"CBF#\u0005\u0005I\u0011ABG\u0011%\u0019I+EI\u0001\n\u0003\u0019Y\u000bC\u0005\u0004PF\t\t\u0011\"\u0011\u0004R\"I11]\t\u0002\u0002\u0013\u00051Q\u001d\u0005\n\u0007[\f\u0012\u0011!C\u0001\u0007_D\u0011b!>\u0012\u0003\u0003%\tea>\t\u0013\u0011\u0015\u0011#!A\u0005\u0002\u0011\u001d\u0001\"\u0003C\t#\u0005\u0005I\u0011\tC\n\u0011%!9\"EA\u0001\n\u0003\"I\u0002C\u0005\u0005\u001cE\t\t\u0011\"\u0011\u0005\u001e!IAqD\t\u0002\u0002\u0013\u0005C\u0011E\u0004\n\tKQ\u0011\u0011!E\u0001\tO1\u0011ba\f\u000b\u0003\u0003E\t\u0001\"\u000b\t\u000f\t-c\u0005\"\u0001\u00056!IA1\u0004\u0014\u0002\u0002\u0013\u0015CQ\u0004\u0005\n\to1\u0013\u0011!CA\tsA\u0011\u0002\"\u0016'\u0003\u0003%\t\tb\u0016\t\u0013\u0011md%!A\u0005\n\u0011udA\u0002CC\u0015\u0001#9\t\u0003\u0006\u0002b2\u0012)\u001a!C\u0001\t+C!ba\u001c-\u0005#\u0005\u000b\u0011\u0002CL\u0011\u001d\u0011Y\u0005\fC\u0001\t3+a!!5-\u0001\u0011}\u0005b\u0002B\u001aY\u0011\u0005Qq\u0001\u0005\b\u0005waC\u0011AC\b\u0011%\u0011\u0019\u0001\fb\u0001\n\u0007)\u0019\u0002\u0003\u0005\u0004\u00022\u0002\u000b\u0011BC\u000b\u0011%\u0019Y\tLA\u0001\n\u0003)9\u0002C\u0005\u0004*2\n\n\u0011\"\u0001\u0006*!I1q\u001a\u0017\u0002\u0002\u0013\u00053\u0011\u001b\u0005\n\u0007Gd\u0013\u0011!C\u0001\u0007KD\u0011b!<-\u0003\u0003%\t!b\r\t\u0013\rUH&!A\u0005B\r]\b\"\u0003C\u0003Y\u0005\u0005I\u0011AC\u001c\u0011%!\t\u0002LA\u0001\n\u0003*Y\u0004C\u0005\u0005\u00181\n\t\u0011\"\u0011\u0005\u001a!IA1\u0004\u0017\u0002\u0002\u0013\u0005CQ\u0004\u0005\n\t?a\u0013\u0011!C!\u000b\u007f9\u0011\"b\u0011\u000b\u0003\u0003E\t!\"\u0012\u0007\u0013\u0011\u0015%\"!A\t\u0002\u0015\u001d\u0003b\u0002B&\u0003\u0012\u0005Q\u0011\n\u0005\n\t7\t\u0015\u0011!C#\t;A\u0011\u0002b\u000eB\u0003\u0003%\t)b\u0013\t\u0013\u0011U\u0013)!A\u0005\u0002\u0016u\u0003\"\u0003C>\u0003\u0006\u0005I\u0011\u0002C?\r\u0019)\tH\u0003!\u0006t!Q\u0011\u0011]$\u0003\u0016\u0004%\t!\"!\t\u0015\r=tI!E!\u0002\u0013)\u0019\tC\u0004\u0003L\u001d#\t!\"\"\u0006\r\u0005Ew\tACF\u0011\u001d\u0011\u0019d\u0012C\u0001\u000b\u001bCqAa\u000fH\t\u0003))\nC\u0005\u0003\u0004\u001d\u0013\r\u0011b\u0001\u0006\u001a\"A1\u0011Q$!\u0002\u0013)Y\nC\u0005\u0004\f\u001e\u000b\t\u0011\"\u0001\u0006\u001e\"I1\u0011V$\u0012\u0002\u0013\u0005Qq\u0016\u0005\n\u0007\u001f<\u0015\u0011!C!\u0007#D\u0011ba9H\u0003\u0003%\ta!:\t\u0013\r5x)!A\u0005\u0002\u0015e\u0006\"CB{\u000f\u0006\u0005I\u0011IB|\u0011%!)aRA\u0001\n\u0003)i\fC\u0005\u0005\u0012\u001d\u000b\t\u0011\"\u0011\u0006B\"IAqC$\u0002\u0002\u0013\u0005C\u0011\u0004\u0005\n\t79\u0015\u0011!C!\t;A\u0011\u0002b\bH\u0003\u0003%\t%\"2\b\u0013\u0015%'\"!A\t\u0002\u0015-g!CC9\u0015\u0005\u0005\t\u0012ACg\u0011\u001d\u0011Y\u0005\u0018C\u0001\u000b\u001fD\u0011\u0002b\u0007]\u0003\u0003%)\u0005\"\b\t\u0013\u0011]B,!A\u0005\u0002\u0016E\u0007\"\u0003C+9\u0006\u0005I\u0011QCr\u0011%!Y\bXA\u0001\n\u0013!iH\u0002\u0004\u0006x*\u0001U\u0011 \u0005\u000b\u0003C\u0014'Q3A\u0005\u0002\u0019\u001d\u0001BCB8E\nE\t\u0015!\u0003\u0007\n!Q!Q\u00162\u0003\u0002\u0003\u0006YAb\u0003\t\u0015\t\u0005'M!A!\u0002\u00171i\u0001\u0003\u0006\u0003L\n\u0014\t\u0011)A\u0006\r\u001fAqAa\u0013c\t\u00031\t\"\u0002\u0004\u0002R\n\u0004aq\u0004\u0005\b\u0005g\u0011G\u0011\u0001D\u0011\u0011\u001d\u0011YD\u0019C\u0001\rSA\u0011Ba\u0001c\u0005\u0004%\u0019A\"\f\t\u0011\r\u0005%\r)A\u0005\r_A\u0011ba#c\u0003\u0003%\tA\"\r\t\u0013\r%&-%A\u0005\u0002\u0019E\u0003\"CBhE\u0006\u0005I\u0011IBi\u0011%\u0019\u0019OYA\u0001\n\u0003\u0019)\u000fC\u0005\u0004n\n\f\t\u0011\"\u0001\u0007\\!I1Q\u001f2\u0002\u0002\u0013\u00053q\u001f\u0005\n\t\u000b\u0011\u0017\u0011!C\u0001\r?B\u0011\u0002\"\u0005c\u0003\u0003%\tEb\u0019\t\u0013\u0011]!-!A\u0005B\u0011e\u0001\"\u0003C\u000eE\u0006\u0005I\u0011\tC\u000f\u0011%!yBYA\u0001\n\u000329gB\u0005\u0007l)\t\t\u0011#\u0001\u0007n\u0019IQq\u001f\u0006\u0002\u0002#\u0005aq\u000e\u0005\b\u0005\u0017RH\u0011\u0001D9\u0011%!YB_A\u0001\n\u000b\"i\u0002C\u0005\u00058i\f\t\u0011\"!\u0007t!IAQ\u000b>\u0002\u0002\u0013\u0005e1\u0013\u0005\n\twR\u0018\u0011!C\u0005\t{2a\u0001\")\u000b\u0001\u0012\r\u0006b\u0003C\\\u0003\u0003\u0011\t\u001a!C\u0001\tsC1\u0002b/\u0002\u0002\t\u0005\r\u0011\"\u0001\u0005>\"YA\u0011YA\u0001\u0005#\u0005\u000b\u0015\u0002CY\u0011!\u0011Y%!\u0001\u0005\u0002\u0011\r\u0007\u0002\u0003Cd\u0003\u0003!\t\u0001\"3\t\u0011\u0011-\u0017\u0011\u0001C\u0001\t\u001bD!ba#\u0002\u0002\u0005\u0005I\u0011\u0001Cr\u0011)\u0019I+!\u0001\u0012\u0002\u0013\u0005Aq\u001e\u0005\u000b\u0007\u001f\f\t!!A\u0005B\rE\u0007BCBr\u0003\u0003\t\t\u0011\"\u0001\u0004f\"Q1Q^A\u0001\u0003\u0003%\t\u0001b>\t\u0015\rU\u0018\u0011AA\u0001\n\u0003\u001a9\u0010\u0003\u0006\u0005\u0006\u0005\u0005\u0011\u0011!C\u0001\twD!\u0002\"\u0005\u0002\u0002\u0005\u0005I\u0011\tC\u0000\u0011)!9\"!\u0001\u0002\u0002\u0013\u0005C\u0011\u0004\u0005\u000b\t7\t\t!!A\u0005B\u0011u\u0001B\u0003C\u0010\u0003\u0003\t\t\u0011\"\u0011\u0006\u0004\u001dIaq\u0015\u0006\u0002\u0002#\u0005a\u0011\u0016\u0004\n\tCS\u0011\u0011!E\u0001\rWC\u0001Ba\u0013\u0002(\u0011\u0005aQ\u0016\u0005\u000b\t7\t9#!A\u0005F\u0011u\u0001B\u0003C\u001c\u0003O\t\t\u0011\"!\u00070\"QAQKA\u0014\u0003\u0003%\tIb/\t\u0015\u0011m\u0014qEA\u0001\n\u0013!iH\u0002\u0004\u0007J*\u0001e1\u001a\u0005\f\u0003C\f\u0019D!f\u0001\n\u00031I\u000eC\u0006\u0004p\u0005M\"\u0011#Q\u0001\n\u0019m\u0007b\u0003BW\u0003g\u0011\t\u0011)A\u0006\r;D1B!1\u00024\t\u0005\t\u0015a\u0003\u0007`\"Y!1ZA\u001a\u0005\u0003\u0005\u000b1\u0002Dq\u0011!\u0011Y%a\r\u0005\u0002\u0019\rXaBAi\u0003g\u0001a\u0011\u001f\u0005\t\u0005g\t\u0019\u0004\"\u0001\u0007t\"A!1HA\u001a\t\u00031Y\u0010\u0003\u0006\u0003\u0004\u0005M\"\u0019!C\u0002\r\u007fD\u0011b!!\u00024\u0001\u0006Ia\"\u0001\t\u0015\r-\u00151GA\u0001\n\u00039\u0019\u0001\u0003\u0006\u0004*\u0006M\u0012\u0013!C\u0001\u000fGA!ba4\u00024\u0005\u0005I\u0011IBi\u0011)\u0019\u0019/a\r\u0002\u0002\u0013\u00051Q\u001d\u0005\u000b\u0007[\f\u0019$!A\u0005\u0002\u001d5\u0002BCB{\u0003g\t\t\u0011\"\u0011\u0004x\"QAQAA\u001a\u0003\u0003%\ta\"\r\t\u0015\u0011E\u00111GA\u0001\n\u0003:)\u0004\u0003\u0006\u0005\u0018\u0005M\u0012\u0011!C!\t3A!\u0002b\u0007\u00024\u0005\u0005I\u0011\tC\u000f\u0011)!y\"a\r\u0002\u0002\u0013\u0005s\u0011H\u0004\n\u000f{Q\u0011\u0011!E\u0001\u000f\u007f1\u0011B\"3\u000b\u0003\u0003E\ta\"\u0011\t\u0011\t-\u00131\rC\u0001\u000f\u0007B!\u0002b\u0007\u0002d\u0005\u0005IQ\tC\u000f\u0011)!9$a\u0019\u0002\u0002\u0013\u0005uQ\t\u0005\u000b\t+\n\u0019'!A\u0005\u0002\u001e\u0015\u0004B\u0003C>\u0003G\n\t\u0011\"\u0003\u0005~\u00191q\u0011\u0010\u0006A\u000fwB1\"!9\u0002p\tU\r\u0011\"\u0001\b\n\"Y1qNA8\u0005#\u0005\u000b\u0011BDF\u0011-\u0011i+a\u001c\u0003\u0002\u0003\u0006Ya\"$\t\u0017\t\u0005\u0017q\u000eB\u0001B\u0003-qq\u0012\u0005\f\u0005\u0017\fyG!A!\u0002\u00179\t\n\u0003\u0005\u0003L\u0005=D\u0011ADJ\u000b\u001d\t\t.a\u001c\u0001\u000fCC\u0001Ba\r\u0002p\u0011\u0005q1\u0015\u0005\t\u0005w\ty\u0007\"\u0001\b,\"Q!1AA8\u0005\u0004%\u0019ab,\t\u0013\r\u0005\u0015q\u000eQ\u0001\n\u001dE\u0006BCBF\u0003_\n\t\u0011\"\u0001\b4\"Q1\u0011VA8#\u0003%\tab5\t\u0015\r=\u0017qNA\u0001\n\u0003\u001a\t\u000e\u0003\u0006\u0004d\u0006=\u0014\u0011!C\u0001\u0007KD!b!<\u0002p\u0005\u0005I\u0011ADo\u0011)\u0019)0a\u001c\u0002\u0002\u0013\u00053q\u001f\u0005\u000b\t\u000b\ty'!A\u0005\u0002\u001d\u0005\bB\u0003C\t\u0003_\n\t\u0011\"\u0011\bf\"QAqCA8\u0003\u0003%\t\u0005\"\u0007\t\u0015\u0011m\u0011qNA\u0001\n\u0003\"i\u0002\u0003\u0006\u0005 \u0005=\u0014\u0011!C!\u000fS<\u0011b\"<\u000b\u0003\u0003E\tab<\u0007\u0013\u001de$\"!A\t\u0002\u001dE\b\u0002\u0003B&\u0003?#\tab=\t\u0015\u0011m\u0011qTA\u0001\n\u000b\"i\u0002\u0003\u0006\u00058\u0005}\u0015\u0011!CA\u000fkD!\u0002\"\u0016\u0002 \u0006\u0005I\u0011\u0011E\u000b\u0011)!Y(a(\u0002\u0002\u0013%AQ\u0010\u0002\u0013\u001bV$\u0018M\u00197ju&tw-\u00113baR|'O\u0003\u0003\u00020\u0006E\u0016\u0001B7bi\"T!!a-\u0002\r\t\u0014X-\u001a>f\u0007\u0001)\"\"!/\u0002j\n%\u0011\u0011`A\u0000'\r\u0001\u00111\u0018\t\u0005\u0003{\u000b\u0019-\u0004\u0002\u0002@*\u0011\u0011\u0011Y\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003\u000b\fyL\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0005\u0005-\u0007\u0003BA_\u0003\u001bLA!a4\u0002@\n!QK\\5u\u0005\u001d9&/\u00199qKJ\fB!!6\u0002\\B!\u0011QXAl\u0013\u0011\tI.a0\u0003\u000f9{G\u000f[5oOB!\u0011QXAo\u0013\u0011\ty.a0\u0003\u0007\u0005s\u00170\u0001\u0006v]\u0012,'\u000f\\=j]\u001e,\"!!:\u0011\u0011\u0005\u001d\u0018\u0011^A|\u0003{d\u0001\u0001\u0002\u0005\u0002l\u0002!)\u0019AAw\u0005\t16+\u0006\u0004\u0002T\u0006=\u00181\u001f\u0003\t\u0003c\fIO1\u0001\u0002T\n!q\f\n\u00132\t!\t)0!;C\u0002\u0005M'\u0001B0%II\u0002B!a:\u0002z\u00129\u00111 \u0001C\u0002\u0005M'!\u0001,\u0011\t\u0005\u001d\u0018q \u0003\b\u0005\u0003\u0001!\u0019AAj\u0005\u0005\u0019\u0016AC7vi\u000646\u000f]1dKV\u0011!q\u0001\t\t\u0003O\u0014IAa\u0006\u0002~\u00129!1\u0002\u0001C\u0002\t5!aA'W'V1\u00111\u001bB\b\u0005'!\u0001B!\u0005\u0003\n\t\u0007\u00111\u001b\u0002\u0005?\u0012\"3\u0007\u0002\u0005\u0003\u0016\t%!\u0019AAj\u0005\u0011yF\u0005\n\u001b\u0011\u0007\te!!D\u0001\u0001\u0003-I7o\\7peBD\u0017n]7\u0016\u0005\t}\u0001\u0003\u0003B\u0011\u0005O\t9Pa\u0006\u000e\u0005\t\r\"\u0002\u0002B\u0013\u0003c\u000bA!\u001e;jY&!!\u0011\u0006B\u0012\u0005-I5o\\7peBD\u0017n]7\u0002\u001f5,H/\u0019,Ta\u0006\u001cW-\u00133f]R$BAa\u0002\u00030!9!\u0011\u0007\u0004A\u0002\t]\u0011aB<sCB\u0004XM]\u0001\u0005oJ\f\u0007\u000f\u0006\u0003\u0003\u0018\t]\u0002b\u0002B\u001d\u000f\u0001\u0007\u0011q_\u0001\u0002m\u00061QO\\<sCB$B!a>\u0003@!9!\u0011\t\u0005A\u0002\t]\u0011!A<\u0002%5+H/\u00192mSjLgnZ!eCB$xN\u001d\t\u0004\u0005\u000fRQBAAW'\rQ\u00111X\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u0015\u0013!D3ogV\u0014X-T;uC\ndW-\u0006\u0004\u0003T\t\u0015$\u0011\u000e\u000b\u0005\u0005+\u0012Y\u0007E\u0006\u0003H\u0001\u00119F!\u0018\u0003d\t\u001d\u0004\u0003\u0002B$\u00053JAAa\u0017\u0002.\nYa+Z2u_J\u001c\u0006/Y2f!\u0011\u00119Ea\u0018\n\t\t\u0005\u0014Q\u0016\u0002\u0013\u001bV$\u0018M\u00197f-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0003\u0002h\n\u0015DaBA~\u0019\t\u0007\u00111\u001b\t\u0005\u0003O\u0014I\u0007B\u0004\u0003\u00021\u0011\r!a5\t\u000f\t5D\u00021\u0001\u0003p\u0005\u0011ao\u001d\t\t\u0005\u000f\u0012IFa\u0019\u0003hU1!1\u000fBC\u0005\u0013#BA!\u001e\u0003\fBY!q\t\u0001\u0003x\tu$1\u0011BD!\u0011\u00119E!\u001f\n\t\tm\u0014Q\u0016\u0002\u0018\u0013:tWM\u001d)s_\u0012,8\r\u001e,fGR|'o\u00159bG\u0016\u0004BAa\u0012\u0003\u0000%!!\u0011QAW\u0005yiU\u000f^1cY\u0016LeN\\3s!J|G-^2u-\u0016\u001cGo\u001c:Ta\u0006\u001cW\r\u0005\u0003\u0002h\n\u0015EaBA~\u001b\t\u0007\u00111\u001b\t\u0005\u0003O\u0014I\tB\u0004\u0003\u00025\u0011\r!a5\t\u000f\t5T\u00021\u0001\u0003\u000eBA!q\tB=\u0005\u0007\u00139)\u0006\u0004\u0003\u0012\n\u0015&\u0011\u0016\u000b\u0005\u0005'\u0013\u0019\u000e\u0006\u0005\u0003\u0016\n-&q\u0018Be!-\u00119\u0005\u0001BL\u0005;\u0013\u0019Ka*\u0011\t\t\u001d#\u0011T\u0005\u0005\u00057\u000biKA\u0006WK\u000e$xN\u001d$jK2$\u0007\u0003\u0002B$\u0005?KAA!)\u0002.\n\u0011R*\u001e;bE2,g+Z2u_J4\u0015.\u001a7e!\u0011\t9O!*\u0005\u000f\u0005mhB1\u0001\u0002TB!\u0011q\u001dBU\t\u001d\u0011\tA\u0004b\u0001\u0003'DqA!,\u000f\u0001\b\u0011y+\u0001\u0006dC:LE/\u001a:bi\u0016\u0004\u0002B!-\u0003<\n\r&qU\u0007\u0003\u0005gSAA!.\u00038\u000691/\u001e9q_J$(\u0002\u0002B]\u0003c\u000ba\u0001\\5oC2<\u0017\u0002\u0002B_\u0005g\u0013\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t\u0011\u001d\u0011\tM\u0004a\u0002\u0005\u0007\faaY1o\u001b\u0006\u0004\b\u0003\u0004BY\u0005\u000b\u0014\u0019Ka*\u0003(\n\r\u0016\u0002\u0002Bd\u0005g\u0013AbQ1o\u001b\u0006\u0004h+\u00197vKNDqAa3\u000f\u0001\b\u0011i-A\u0005dC:T\u0016\u000e]'baBa!\u0011\u0017Bh\u0005G\u00139Ka*\u0003$&!!\u0011\u001bBZ\u0005=\u0019\u0015M\u001c.ja6\u000b\u0007OV1mk\u0016\u001c\bb\u0002B7\u001d\u0001\u0007!Q\u001b\t\t\u0005\u000f\u0012IJa)\u0003(V1!\u0011\u001cBw\u0005c$BAa7\u0003\u0000RA!Q\u001cBz\u0005o\u0014Y\u0010E\u0006\u0003H\u0001\u0011yN!:\u0003l\n=\b\u0003\u0002B$\u0005CLAAa9\u0002.\nQa+Z2u_J\u0014\u0016N\\4\u0011\t\t\u001d#q]\u0005\u0005\u0005S\fiKA\tNkR\f'\r\\3WK\u000e$xN\u001d*j]\u001e\u0004B!a:\u0003n\u00129\u00111`\bC\u0002\u0005M\u0007\u0003BAt\u0005c$qA!\u0001\u0010\u0005\u0004\t\u0019\u000eC\u0004\u0003.>\u0001\u001dA!>\u0011\u0011\tE&1\u0018Bv\u0005_DqA!1\u0010\u0001\b\u0011I\u0010\u0005\u0007\u00032\n\u0015'1\u001eBx\u0005_\u0014Y\u000fC\u0004\u0003L>\u0001\u001dA!@\u0011\u0019\tE&q\u001aBv\u0005_\u0014yOa;\t\u000f\t5t\u00021\u0001\u0004\u0002AA!q\tBq\u0005W\u0014y/\u0006\u0004\u0004\u0006\re1Q\u0004\u000b\u0005\u0007\u000f\u0019Y\u0003\u0006\u0005\u0004\n\r}11EB\u0014!-\u00119\u0005AB\u0006\u0007#\u00199ba\u0007\u0011\t\t\u001d3QB\u0005\u0005\u0007\u001f\tiKA\bD_>\u0014H-\u001b8bi\u00164\u0015.\u001a7e!\u0011\u00119ea\u0005\n\t\rU\u0011Q\u0016\u0002\u0017\u001bV$\u0018M\u00197f\u0007>|'\u000fZ5oCR,g)[3mIB!\u0011q]B\r\t\u001d\tY\u0010\u0005b\u0001\u0003'\u0004B!a:\u0004\u001e\u00119!\u0011\u0001\tC\u0002\u0005M\u0007b\u0002BW!\u0001\u000f1\u0011\u0005\t\t\u0005c\u0013Yla\u0006\u0004\u001c!9!\u0011\u0019\tA\u0004\r\u0015\u0002\u0003\u0004BY\u0005\u000b\u001c9ba\u0007\u0004\u001c\r]\u0001b\u0002Bf!\u0001\u000f1\u0011\u0006\t\r\u0005c\u0013yma\u0006\u0004\u001c\rm1q\u0003\u0005\b\u0005[\u0002\u0002\u0019AB\u0017!!\u00119e!\u0004\u0004\u0018\rm!aD%eK:$\u0018\u000e^=Xe\u0006\u0004\b/\u001a:\u0016\u0011\rM2\u0011HB$\u0007\u0017\u001a\u0012\"EA^\u0007k\u0019iea\u0015\u0011\u0017\t\u001d\u0003aa\u000e\u00048\r\u00153\u0011\n\t\u0005\u0003O\u001cI\u0004B\u0004\u0002lF\u0011\raa\u000f\u0016\r\u0005M7QHB!\t!\u0019yd!\u000fC\u0002\u0005M'\u0001B0%IU\"\u0001ba\u0011\u0004:\t\u0007\u00111\u001b\u0002\u0005?\u0012\"c\u0007\u0005\u0003\u0002h\u000e\u001dCaBA~#\t\u0007\u00111\u001b\t\u0005\u0003O\u001cY\u0005B\u0004\u0003\u0002E\u0011\r!a5\u0011\t\u0005u6qJ\u0005\u0005\u0007#\nyLA\u0004Qe>$Wo\u0019;\u0011\t\rU3Q\r\b\u0005\u0007/\u001a\tG\u0004\u0003\u0004Z\r}SBAB.\u0015\u0011\u0019i&!.\u0002\rq\u0012xn\u001c;?\u0013\t\t\t-\u0003\u0003\u0004d\u0005}\u0016a\u00029bG.\fw-Z\u0005\u0005\u0007O\u001aIG\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0004d\u0005}VCAB7!!\t9o!\u000f\u0004F\r%\u0013aC;oI\u0016\u0014H._5oO\u0002\"Baa\u001d\u0004xAI1QO\t\u00048\r\u00153\u0011J\u0007\u0002\u0015!9\u0011\u0011\u001d\u000bA\u0002\r5TCAB>!!\t9o!\u000f\u0004~\r%\u0003cAB@+5\t\u0011#A\u0006nkR\fgk\u001d9bG\u0016\u0004C\u0003BB?\u0007\u000bCqA!\u000f\u0019\u0001\u0004\u0019)\u0005\u0006\u0003\u0004F\r%\u0005b\u0002B\u001d3\u0001\u00071QP\u0001\u0005G>\u0004\u00180\u0006\u0005\u0004\u0010\u000eU5qTBR)\u0011\u0019\tj!*\u0011\u0013\rU\u0014ca%\u0004\u001e\u000e\u0005\u0006\u0003BAt\u0007+#q!a;\u001b\u0005\u0004\u00199*\u0006\u0004\u0002T\u000ee51\u0014\u0003\t\u0007\u007f\u0019)J1\u0001\u0002T\u0012A11IBK\u0005\u0004\t\u0019\u000e\u0005\u0003\u0002h\u000e}EaBA~5\t\u0007\u00111\u001b\t\u0005\u0003O\u001c\u0019\u000bB\u0004\u0003\u0002i\u0011\r!a5\t\u0013\u0005\u0005(\u0004%AA\u0002\r\u001d\u0006\u0003CAt\u0007+\u001bij!)\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cUA1QVBb\u0007\u0017\u001ci-\u0006\u0002\u00040*\"1QNBYW\t\u0019\u0019\f\u0005\u0003\u00046\u000e}VBAB\\\u0015\u0011\u0019Ila/\u0002\u0013Ut7\r[3dW\u0016$'\u0002BB_\u0003\u007f\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0019\tma.\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0004\u0002ln\u0011\ra!2\u0016\r\u0005M7qYBe\t!\u0019yda1C\u0002\u0005MG\u0001CB\"\u0007\u0007\u0014\r!a5\u0005\u000f\u0005m8D1\u0001\u0002T\u00129!\u0011A\u000eC\u0002\u0005M\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0004TB!1Q[Bp\u001b\t\u00199N\u0003\u0003\u0004Z\u000em\u0017\u0001\u00027b]\u001eT!a!8\u0002\t)\fg/Y\u0005\u0005\u0007C\u001c9N\u0001\u0004TiJLgnZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0007O\u0004B!!0\u0004j&!11^A`\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tYn!=\t\u0013\rMh$!AA\u0002\r\u001d\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0004zB111 C\u0001\u00037l!a!@\u000b\t\r}\u0018qX\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002C\u0002\u0007{\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!A\u0011\u0002C\b!\u0011\ti\fb\u0003\n\t\u00115\u0011q\u0018\u0002\b\u0005>|G.Z1o\u0011%\u0019\u0019\u0010IA\u0001\u0002\u0004\tY.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BBj\t+A\u0011ba=\"\u0003\u0003\u0005\raa:\u0002\u0011!\f7\u000f[\"pI\u0016$\"aa:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"aa5\u0002\r\u0015\fX/\u00197t)\u0011!I\u0001b\t\t\u0013\rMH%!AA\u0002\u0005m\u0017aD%eK:$\u0018\u000e^=Xe\u0006\u0004\b/\u001a:\u0011\u0007\rUdeE\u0003'\u0003w#Y\u0003\u0005\u0003\u0005.\u0011MRB\u0001C\u0018\u0015\u0011!\tda7\u0002\u0005%|\u0017\u0002BB4\t_!\"\u0001b\n\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0011\u0011mB\u0011\tC&\t\u001f\"B\u0001\"\u0010\u0005RAI1QO\t\u0005@\u0011%CQ\n\t\u0005\u0003O$\t\u0005B\u0004\u0002l&\u0012\r\u0001b\u0011\u0016\r\u0005MGQ\tC$\t!\u0019y\u0004\"\u0011C\u0002\u0005MG\u0001CB\"\t\u0003\u0012\r!a5\u0011\t\u0005\u001dH1\n\u0003\b\u0003wL#\u0019AAj!\u0011\t9\u000fb\u0014\u0005\u000f\t\u0005\u0011F1\u0001\u0002T\"9\u0011\u0011]\u0015A\u0002\u0011M\u0003\u0003CAt\t\u0003\"I\u0005\"\u0014\u0002\u000fUt\u0017\r\u001d9msVAA\u0011\fC2\t[\"\t\b\u0006\u0003\u0005\\\u0011M\u0004CBA_\t;\"\t'\u0003\u0003\u0005`\u0005}&AB(qi&|g\u000e\u0005\u0005\u0002h\u0012\rD1\u000eC8\t\u001d\tYO\u000bb\u0001\tK*b!a5\u0005h\u0011%D\u0001CB \tG\u0012\r!a5\u0005\u0011\r\rC1\rb\u0001\u0003'\u0004B!a:\u0005n\u00119\u00111 \u0016C\u0002\u0005M\u0007\u0003BAt\tc\"qA!\u0001+\u0005\u0004\t\u0019\u000eC\u0005\u0005v)\n\t\u00111\u0001\u0005x\u0005\u0019\u0001\u0010\n\u0019\u0011\u0013\rU\u0014\u0003\"\u001f\u0005l\u0011=\u0004\u0003BAt\tG\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001b \u0011\t\rUG\u0011Q\u0005\u0005\t\u0007\u001b9N\u0001\u0004PE*,7\r\u001e\u0002\u0013-\u0016\u001cGo\u001c:Ta\u0006\u001cW-\u00113baR|'/\u0006\u0004\u0005\n\u0012=E1S\n\nY\u0005mF1RB'\u0007'\u00022Ba\u0012\u0001\u0005/\u0012i\u0006\"$\u0005\u0012B!\u0011q\u001dCH\t\u001d\tY\u0010\fb\u0001\u0003'\u0004B!a:\u0005\u0014\u00129!\u0011\u0001\u0017C\u0002\u0005MWC\u0001CL!!\u00119E!\u0017\u0005\u000e\u0012EE\u0003\u0002CN\t;\u0003ra!\u001e-\t\u001b#\t\nC\u0004\u0002b>\u0002\r\u0001b&\u0011\r\rU\u0014\u0011\u0001CG\u0005\r\u0011VMZ\u000b\u0005\tK#\u0019l\u0005\u0006\u0002\u0002\u0005mFqUB'\u0007'\u0002b\u0001\"+\u0005,\u0012=VB\u0001B\\\u0013\u0011!iKa.\u0003\u00159+X.\u001a:jG>\u00038\u000f\u0005\u0004\u0004v\u0005\u0005A\u0011\u0017\t\u0005\u0003O$\u0019\f\u0002\u0005\u00056\u0006\u0005!\u0019AAj\u0005\u0005!\u0016!\u0002<bYV,WC\u0001CY\u0003%1\u0018\r\\;f?\u0012*\u0017\u000f\u0006\u0003\u0002L\u0012}\u0006BCBz\u0003\u000b\t\t\u00111\u0001\u00052\u00061a/\u00197vK\u0002\"B\u0001b,\u0005F\"AAqWA\u0005\u0001\u0004!\t,\u0001\u0003sKB\u0014XC\u0001CX\u0003\ri\u0017\r]\u000b\u0005\t\u001f$)\u000e\u0006\u0003\u0005R\u0012e\u0007CBB;\u0003\u0003!\u0019\u000e\u0005\u0003\u0002h\u0012UG\u0001\u0003Cl\u0003\u001b\u0011\r!a5\u0003\u0003UC\u0001\u0002b7\u0002\u000e\u0001\u0007AQ\\\u0001\u0002MBA\u0011Q\u0018Cp\tc#\u0019.\u0003\u0003\u0005b\u0006}&!\u0003$v]\u000e$\u0018n\u001c82+\u0011!)\u000fb;\u0015\t\u0011\u001dHQ\u001e\t\u0007\u0007k\n\t\u0001\";\u0011\t\u0005\u001dH1\u001e\u0003\t\tk\u000byA1\u0001\u0002T\"QAqWA\b!\u0003\u0005\r\u0001\";\u0016\t\u0011EHQ_\u000b\u0003\tgTC\u0001\"-\u00042\u0012AAQWA\t\u0005\u0004\t\u0019\u000e\u0006\u0003\u0002\\\u0012e\bBCBz\u0003/\t\t\u00111\u0001\u0004hR!A\u0011\u0002C\u007f\u0011)\u0019\u00190a\u0007\u0002\u0002\u0003\u0007\u00111\u001c\u000b\u0005\u0007',\t\u0001\u0003\u0006\u0004t\u0006u\u0011\u0011!a\u0001\u0007O$B\u0001\"\u0003\u0006\u0006!Q11_A\u0012\u0003\u0003\u0005\r!a7\u0015\t\u0015%QQ\u0002\t\u0004\u000b\u0017\u0001T\"\u0001\u0017\t\u000f\te\u0012\u00071\u0001\u0005\u000eR!AQRC\t\u0011\u001d\u0011\tE\ra\u0001\u000b\u0013)\"!\"\u0006\u0011\u0011\t\u001d#qLC\u0005\t#+b!\"\u0007\u0006 \u0015\rB\u0003BC\u000e\u000bK\u0001ra!\u001e-\u000b;)\t\u0003\u0005\u0003\u0002h\u0016}AaBA~k\t\u0007\u00111\u001b\t\u0005\u0003O,\u0019\u0003B\u0004\u0003\u0002U\u0012\r!a5\t\u0013\u0005\u0005X\u0007%AA\u0002\u0015\u001d\u0002\u0003\u0003B$\u00053*i\"\"\t\u0016\r\u0015-RqFC\u0019+\t)iC\u000b\u0003\u0005\u0018\u000eEFaBA~m\t\u0007\u00111\u001b\u0003\b\u0005\u00031$\u0019AAj)\u0011\tY.\"\u000e\t\u0013\rM\u0018(!AA\u0002\r\u001dH\u0003\u0002C\u0005\u000bsA\u0011ba=<\u0003\u0003\u0005\r!a7\u0015\t\rMWQ\b\u0005\n\u0007gd\u0014\u0011!a\u0001\u0007O$B\u0001\"\u0003\u0006B!I11_ \u0002\u0002\u0003\u0007\u00111\\\u0001\u0013-\u0016\u001cGo\u001c:Ta\u0006\u001cW-\u00113baR|'\u000fE\u0002\u0004v\u0005\u001bR!QA^\tW!\"!\"\u0012\u0016\r\u00155S1KC,)\u0011)y%\"\u0017\u0011\u000f\rUD&\"\u0015\u0006VA!\u0011q]C*\t\u001d\tY\u0010\u0012b\u0001\u0003'\u0004B!a:\u0006X\u00119!\u0011\u0001#C\u0002\u0005M\u0007bBAq\t\u0002\u0007Q1\f\t\t\u0005\u000f\u0012I&\"\u0015\u0006VU1QqLC4\u000bW\"B!\"\u0019\u0006nA1\u0011Q\u0018C/\u000bG\u0002\u0002Ba\u0012\u0003Z\u0015\u0015T\u0011\u000e\t\u0005\u0003O,9\u0007B\u0004\u0002|\u0016\u0013\r!a5\u0011\t\u0005\u001dX1\u000e\u0003\b\u0005\u0003)%\u0019AAj\u0011%!)(RA\u0001\u0002\u0004)y\u0007E\u0004\u0004v1*)'\"\u001b\u00031%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW-\u00113baR|'/\u0006\u0004\u0006v\u0015mTqP\n\n\u000f\u0006mVqOB'\u0007'\u00022Ba\u0012\u0001\u0005o\u0012i(\"\u001f\u0006~A!\u0011q]C>\t\u001d\tYp\u0012b\u0001\u0003'\u0004B!a:\u0006\u0000\u00119!\u0011A$C\u0002\u0005MWCACB!!\u00119E!\u001f\u0006z\u0015uD\u0003BCD\u000b\u0013\u0003ra!\u001eH\u000bs*i\bC\u0004\u0002b*\u0003\r!b!\u0011\r\rU\u0014\u0011AC=)\u0011)y)b%\u0011\u0007\u0015E5*D\u0001H\u0011\u001d\u0011I\u0004\u0014a\u0001\u000bs\"B!\"\u001f\u0006\u0018\"9!\u0011I'A\u0002\u0015=UCACN!!\u00119Ea \u0006\u0010\u0016uTCBCP\u000bK+I\u000b\u0006\u0003\u0006\"\u0016-\u0006cBB;\u000f\u0016\rVq\u0015\t\u0005\u0003O,)\u000bB\u0004\u0002|B\u0013\r!a5\u0011\t\u0005\u001dX\u0011\u0016\u0003\b\u0005\u0003\u0001&\u0019AAj\u0011%\t\t\u000f\u0015I\u0001\u0002\u0004)i\u000b\u0005\u0005\u0003H\teT1UCT+\u0019)\t,\".\u00068V\u0011Q1\u0017\u0016\u0005\u000b\u0007\u001b\t\fB\u0004\u0002|F\u0013\r!a5\u0005\u000f\t\u0005\u0011K1\u0001\u0002TR!\u00111\\C^\u0011%\u0019\u0019\u0010VA\u0001\u0002\u0004\u00199\u000f\u0006\u0003\u0005\n\u0015}\u0006\"CBz-\u0006\u0005\t\u0019AAn)\u0011\u0019\u0019.b1\t\u0013\rMx+!AA\u0002\r\u001dH\u0003\u0002C\u0005\u000b\u000fD\u0011ba=[\u0003\u0003\u0005\r!a7\u00021%sg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW-\u00113baR|'\u000fE\u0002\u0004vq\u001bR\u0001XA^\tW!\"!b3\u0016\r\u0015MW\u0011\\Co)\u0011)).b8\u0011\u000f\rUt)b6\u0006\\B!\u0011q]Cm\t\u001d\tYp\u0018b\u0001\u0003'\u0004B!a:\u0006^\u00129!\u0011A0C\u0002\u0005M\u0007bBAq?\u0002\u0007Q\u0011\u001d\t\t\u0005\u000f\u0012I(b6\u0006\\V1QQ]Cw\u000bc$B!b:\u0006tB1\u0011Q\u0018C/\u000bS\u0004\u0002Ba\u0012\u0003z\u0015-Xq\u001e\t\u0005\u0003O,i\u000fB\u0004\u0002|\u0002\u0014\r!a5\u0011\t\u0005\u001dX\u0011\u001f\u0003\b\u0005\u0003\u0001'\u0019AAj\u0011%!)\bYA\u0001\u0002\u0004))\u0010E\u0004\u0004v\u001d+Y/b<\u0003%Y+7\r^8s\r&,G\u000eZ!eCB$xN]\u000b\u0007\u000bw4\tA\"\u0002\u0014\u0013\t\fY,\"@\u0004N\rM\u0003c\u0003B$\u0001\t]%QTC\u0000\r\u0007\u0001B!a:\u0007\u0002\u00119\u00111 2C\u0002\u0005M\u0007\u0003BAt\r\u000b!qA!\u0001c\u0005\u0004\t\u0019.\u0006\u0002\u0007\nAA!q\tBM\u000b\u007f4\u0019\u0001\u0005\u0005\u00032\nmVq D\u0002!1\u0011\tL!2\u0006\u0000\u001a\ra1AC\u0000!1\u0011\tLa4\u0006\u0000\u001a\ra1AC\u0000)\u00111\u0019B\"\b\u0015\u0011\u0019Uaq\u0003D\r\r7\u0001ra!\u001ec\u000b\u007f4\u0019\u0001C\u0004\u0003.\"\u0004\u001dAb\u0003\t\u000f\t\u0005\u0007\u000eq\u0001\u0007\u000e!9!1\u001a5A\u0004\u0019=\u0001bBAqQ\u0002\u0007a\u0011\u0002\t\u0007\u0007k\n\t!b@\u0015\t\u0019\rbq\u0005\t\u0004\rKIW\"\u00012\t\u000f\te\"\u000e1\u0001\u0006\u0000R!Qq D\u0016\u0011\u001d\u0011\te\u001ba\u0001\rG)\"Ab\f\u0011\u0011\t\u001d#q\u0014D\u0012\r\u0007)bAb\r\u0007<\u0019}B\u0003\u0002D\u001b\r\u001b\"\u0002Bb\u000e\u0007B\u0019\u0015c\u0011\n\t\b\u0007k\u0012g\u0011\bD\u001f!\u0011\t9Ob\u000f\u0005\u000f\u0005mhN1\u0001\u0002TB!\u0011q\u001dD \t\u001d\u0011\tA\u001cb\u0001\u0003'DqA!,o\u0001\b1\u0019\u0005\u0005\u0005\u00032\nmf\u0011\bD\u001f\u0011\u001d\u0011\tM\u001ca\u0002\r\u000f\u0002BB!-\u0003F\u001aebQ\bD\u001f\rsAqAa3o\u0001\b1Y\u0005\u0005\u0007\u00032\n=g\u0011\bD\u001f\r{1I\u0004C\u0005\u0002b:\u0004\n\u00111\u0001\u0007PAA!q\tBM\rs1i$\u0006\u0004\u0007T\u0019]c\u0011L\u000b\u0003\r+RCA\"\u0003\u00042\u00129\u00111`8C\u0002\u0005MGa\u0002B\u0001_\n\u0007\u00111\u001b\u000b\u0005\u000374i\u0006C\u0005\u0004tJ\f\t\u00111\u0001\u0004hR!A\u0011\u0002D1\u0011%\u0019\u0019\u0010^A\u0001\u0002\u0004\tY\u000e\u0006\u0003\u0004T\u001a\u0015\u0004\"CBzk\u0006\u0005\t\u0019ABt)\u0011!IA\"\u001b\t\u0013\rM\b0!AA\u0002\u0005m\u0017A\u0005,fGR|'OR5fY\u0012\fE-\u00199u_J\u00042a!\u001e{'\u0015Q\u00181\u0018C\u0016)\t1i'\u0006\u0004\u0007v\u0019ud\u0011\u0011\u000b\u0005\ro2y\t\u0006\u0005\u0007z\u0019\req\u0011DF!\u001d\u0019)H\u0019D>\r\u007f\u0002B!a:\u0007~\u00119\u00111`?C\u0002\u0005M\u0007\u0003BAt\r\u0003#qA!\u0001~\u0005\u0004\t\u0019\u000eC\u0004\u0003.v\u0004\u001dA\"\"\u0011\u0011\tE&1\u0018D>\r\u007fBqA!1~\u0001\b1I\t\u0005\u0007\u00032\n\u0015g1\u0010D@\r\u007f2Y\bC\u0004\u0003Lv\u0004\u001dA\"$\u0011\u0019\tE&q\u001aD>\r\u007f2yHb\u001f\t\u000f\u0005\u0005X\u00101\u0001\u0007\u0012BA!q\tBM\rw2y(\u0006\u0004\u0007\u0016\u001aue\u0011\u0015\u000b\u0005\r/3\u0019\u000b\u0005\u0004\u0002>\u0012uc\u0011\u0014\t\t\u0005\u000f\u0012IJb'\u0007 B!\u0011q\u001dDO\t\u001d\tYP b\u0001\u0003'\u0004B!a:\u0007\"\u00129!\u0011\u0001@C\u0002\u0005M\u0007\"\u0003C;}\u0006\u0005\t\u0019\u0001DS!\u001d\u0019)H\u0019DN\r?\u000b1AU3g!\u0011\u0019)(a\n\u0014\r\u0005\u001d\u00121\u0018C\u0016)\t1I+\u0006\u0003\u00072\u001a]F\u0003\u0002DZ\rs\u0003ba!\u001e\u0002\u0002\u0019U\u0006\u0003BAt\ro#\u0001\u0002\".\u0002.\t\u0007\u00111\u001b\u0005\t\to\u000bi\u00031\u0001\u00076V!aQ\u0018Db)\u00111yL\"2\u0011\r\u0005uFQ\fDa!\u0011\t9Ob1\u0005\u0011\u0011U\u0016q\u0006b\u0001\u0003'D!\u0002\"\u001e\u00020\u0005\u0005\t\u0019\u0001Dd!\u0019\u0019)(!\u0001\u0007B\n\tb+Z2u_J\u0014\u0016N\\4BI\u0006\u0004Ho\u001c:\u0016\r\u00195g1\u001bDl')\t\u0019$a/\u0007P\u000e531\u000b\t\f\u0005\u000f\u0002!q\u001cBs\r#4)\u000e\u0005\u0003\u0002h\u001aMG\u0001CA~\u0003g\u0011\r!a5\u0011\t\u0005\u001dhq\u001b\u0003\t\u0005\u0003\t\u0019D1\u0001\u0002TV\u0011a1\u001c\t\t\u0005\u000f\u0012\tO\"5\u0007VBA!\u0011\u0017B^\r#4)\u000e\u0005\u0007\u00032\n\u0015g\u0011\u001bDk\r+4\t\u000e\u0005\u0007\u00032\n=g\u0011\u001bDk\r+4\t\u000e\u0006\u0003\u0007f\u001a=H\u0003\u0003Dt\rS4YO\"<\u0011\u0011\rU\u00141\u0007Di\r+D\u0001B!,\u0002@\u0001\u000faQ\u001c\u0005\t\u0005\u0003\fy\u0004q\u0001\u0007`\"A!1ZA \u0001\b1\t\u000f\u0003\u0005\u0002b\u0006}\u0002\u0019\u0001Dn!\u0019\u0019)(!\u0001\u0007RR!aQ\u001fD}!\u0011190!\u0011\u000e\u0005\u0005M\u0002\u0002\u0003B\u001d\u0003\u0007\u0002\rA\"5\u0015\t\u0019EgQ \u0005\t\u0005\u0003\n)\u00051\u0001\u0007vV\u0011q\u0011\u0001\t\t\u0005\u000f\u00129O\">\u0007VV1qQAD\u0007\u000f#!Bab\u0002\b QAq\u0011BD\n\u000f/9Y\u0002\u0005\u0005\u0004v\u0005Mr1BD\b!\u0011\t9o\"\u0004\u0005\u0011\u0005m\u00181\nb\u0001\u0003'\u0004B!a:\b\u0012\u0011A!\u0011AA&\u0005\u0004\t\u0019\u000e\u0003\u0005\u0003.\u0006-\u00039AD\u000b!!\u0011\tLa/\b\f\u001d=\u0001\u0002\u0003Ba\u0003\u0017\u0002\u001da\"\u0007\u0011\u0019\tE&QYD\u0006\u000f\u001f9yab\u0003\t\u0011\t-\u00171\na\u0002\u000f;\u0001BB!-\u0003P\u001e-qqBD\b\u000f\u0017A!\"!9\u0002LA\u0005\t\u0019AD\u0011!!\u00119E!9\b\f\u001d=QCBD\u0013\u000fS9Y#\u0006\u0002\b()\"a1\\BY\t!\tY0!\u0014C\u0002\u0005MG\u0001\u0003B\u0001\u0003\u001b\u0012\r!a5\u0015\t\u0005mwq\u0006\u0005\u000b\u0007g\f\u0019&!AA\u0002\r\u001dH\u0003\u0002C\u0005\u000fgA!ba=\u0002X\u0005\u0005\t\u0019AAn)\u0011\u0019\u0019nb\u000e\t\u0015\rM\u0018\u0011LA\u0001\u0002\u0004\u00199\u000f\u0006\u0003\u0005\n\u001dm\u0002BCBz\u0003?\n\t\u00111\u0001\u0002\\\u0006\tb+Z2u_J\u0014\u0016N\\4BI\u0006\u0004Ho\u001c:\u0011\t\rU\u00141M\n\u0007\u0003G\nY\fb\u000b\u0015\u0005\u001d}RCBD$\u000f\u001f:\u0019\u0006\u0006\u0003\bJ\u001d\u0005D\u0003CD&\u000f+:If\"\u0018\u0011\u0011\rU\u00141GD'\u000f#\u0002B!a:\bP\u0011A\u00111`A5\u0005\u0004\t\u0019\u000e\u0005\u0003\u0002h\u001eMC\u0001\u0003B\u0001\u0003S\u0012\r!a5\t\u0011\t5\u0016\u0011\u000ea\u0002\u000f/\u0002\u0002B!-\u0003<\u001e5s\u0011\u000b\u0005\t\u0005\u0003\fI\u0007q\u0001\b\\Aa!\u0011\u0017Bc\u000f\u001b:\tf\"\u0015\bN!A!1ZA5\u0001\b9y\u0006\u0005\u0007\u00032\n=wQJD)\u000f#:i\u0005\u0003\u0005\u0002b\u0006%\u0004\u0019AD2!!\u00119E!9\bN\u001dESCBD4\u000f_:\u0019\b\u0006\u0003\bj\u001dU\u0004CBA_\t;:Y\u0007\u0005\u0005\u0003H\t\u0005xQND9!\u0011\t9ob\u001c\u0005\u0011\u0005m\u00181\u000eb\u0001\u0003'\u0004B!a:\bt\u0011A!\u0011AA6\u0005\u0004\t\u0019\u000e\u0003\u0006\u0005v\u0005-\u0014\u0011!a\u0001\u000fo\u0002\u0002b!\u001e\u00024\u001d5t\u0011\u000f\u0002\u0017\u0007>|'\u000fZ5oCR,g)[3mI\u0006#\u0017\r\u001d;peV1qQPDB\u000f\u000f\u001b\"\"a\u001c\u0002<\u001e}4QJB*!-\u00119\u0005AB\u0006\u0007#9\ti\"\"\u0011\t\u0005\u001dx1\u0011\u0003\t\u0003w\fyG1\u0001\u0002TB!\u0011q]DD\t!\u0011\t!a\u001cC\u0002\u0005MWCADF!!\u00119e!\u0004\b\u0002\u001e\u0015\u0005\u0003\u0003BY\u0005w;\ti\"\"\u0011\u0019\tE&QYDA\u000f\u000b;)i\"!\u0011\u0019\tE&qZDA\u000f\u000b;)i\"!\u0015\t\u001dUuq\u0014\u000b\t\u000f/;Ijb'\b\u001eBA1QOA8\u000f\u0003;)\t\u0003\u0005\u0003.\u0006m\u00049ADG\u0011!\u0011\t-a\u001fA\u0004\u001d=\u0005\u0002\u0003Bf\u0003w\u0002\u001da\"%\t\u0011\u0005\u0005\u00181\u0010a\u0001\u000f\u0017\u0003ba!\u001e\u0002\u0002\u001d\u0005E\u0003BDS\u000fS\u0003Bab*\u0002~5\u0011\u0011q\u000e\u0005\t\u0005s\ty\b1\u0001\b\u0002R!q\u0011QDW\u0011!\u0011\t%!!A\u0002\u001d\u0015VCADY!!\u00119ea\u0005\b&\u001e\u0015UCBD[\u000f{;\t\r\u0006\u0003\b8\u001e=G\u0003CD]\u000f\u0007<9mb3\u0011\u0011\rU\u0014qND^\u000f\u007f\u0003B!a:\b>\u0012A\u00111`AD\u0005\u0004\t\u0019\u000e\u0005\u0003\u0002h\u001e\u0005G\u0001\u0003B\u0001\u0003\u000f\u0013\r!a5\t\u0011\t5\u0016q\u0011a\u0002\u000f\u000b\u0004\u0002B!-\u0003<\u001emvq\u0018\u0005\t\u0005\u0003\f9\tq\u0001\bJBa!\u0011\u0017Bc\u000fw;ylb0\b<\"A!1ZAD\u0001\b9i\r\u0005\u0007\u00032\n=w1XD`\u000f\u007f;Y\f\u0003\u0006\u0002b\u0006\u001d\u0005\u0013!a\u0001\u000f#\u0004\u0002Ba\u0012\u0004\u000e\u001dmvqX\u000b\u0007\u000f+<Inb7\u0016\u0005\u001d]'\u0006BDF\u0007c#\u0001\"a?\u0002\n\n\u0007\u00111\u001b\u0003\t\u0005\u0003\tII1\u0001\u0002TR!\u00111\\Dp\u0011)\u0019\u00190a$\u0002\u0002\u0003\u00071q\u001d\u000b\u0005\t\u00139\u0019\u000f\u0003\u0006\u0004t\u0006M\u0015\u0011!a\u0001\u00037$Baa5\bh\"Q11_AK\u0003\u0003\u0005\raa:\u0015\t\u0011%q1\u001e\u0005\u000b\u0007g\fY*!AA\u0002\u0005m\u0017AF\"p_J$\u0017N\\1uK\u001aKW\r\u001c3BI\u0006\u0004Ho\u001c:\u0011\t\rU\u0014qT\n\u0007\u0003?\u000bY\fb\u000b\u0015\u0005\u001d=XCBD|\u000f\u007fD\u0019\u0001\u0006\u0003\bz\"EA\u0003CD~\u0011\u000bAI\u0001#\u0004\u0011\u0011\rU\u0014qND\u007f\u0011\u0003\u0001B!a:\b\u0000\u0012A\u00111`AS\u0005\u0004\t\u0019\u000e\u0005\u0003\u0002h\"\rA\u0001\u0003B\u0001\u0003K\u0013\r!a5\t\u0011\t5\u0016Q\u0015a\u0002\u0011\u000f\u0001\u0002B!-\u0003<\u001eu\b\u0012\u0001\u0005\t\u0005\u0003\f)\u000bq\u0001\t\fAa!\u0011\u0017Bc\u000f{D\t\u0001#\u0001\b~\"A!1ZAS\u0001\bAy\u0001\u0005\u0007\u00032\n=wQ E\u0001\u0011\u00039i\u0010\u0003\u0005\u0002b\u0006\u0015\u0006\u0019\u0001E\n!!\u00119e!\u0004\b~\"\u0005QC\u0002E\f\u0011?A\u0019\u0003\u0006\u0003\t\u001a!\u0015\u0002CBA_\t;BY\u0002\u0005\u0005\u0003H\r5\u0001R\u0004E\u0011!\u0011\t9\u000fc\b\u0005\u0011\u0005m\u0018q\u0015b\u0001\u0003'\u0004B!a:\t$\u0011A!\u0011AAT\u0005\u0004\t\u0019\u000e\u0003\u0006\u0005v\u0005\u001d\u0016\u0011!a\u0001\u0011O\u0001\u0002b!\u001e\u0002p!u\u0001\u0012\u0005"
)
public interface MutablizingAdaptor {
   static MutablizingAdaptor ensureMutable(final CoordinateField vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return MutablizingAdaptor$.MODULE$.ensureMutable(vs, canIterate, canMap, canZipMap);
   }

   static MutablizingAdaptor ensureMutable(final VectorRing vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return MutablizingAdaptor$.MODULE$.ensureMutable(vs, canIterate, canMap, canZipMap);
   }

   static MutablizingAdaptor ensureMutable(final VectorField vs, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
      return MutablizingAdaptor$.MODULE$.ensureMutable(vs, canIterate, canMap, canZipMap);
   }

   static MutablizingAdaptor ensureMutable(final InnerProductVectorSpace vs) {
      return MutablizingAdaptor$.MODULE$.ensureMutable(vs);
   }

   static MutablizingAdaptor ensureMutable(final VectorSpace vs) {
      return MutablizingAdaptor$.MODULE$.ensureMutable(vs);
   }

   void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1);

   Object underlying();

   Object mutaVspace();

   Isomorphism isomorphism();

   default Object mutaVSpaceIdent(final Object wrapper) {
      return this.mutaVspace();
   }

   Object wrap(final Object v);

   Object unwrap(final Object w);

   static void $init$(final MutablizingAdaptor $this) {
      $this.breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(new Isomorphism() {
         // $FF: synthetic field
         private final MutablizingAdaptor $outer;

         public Isomorphism reverse() {
            return Isomorphism.reverse$(this);
         }

         public Object forward(final Object value) {
            return this.$outer.wrap(value);
         }

         public Object backward(final Object u) {
            return this.$outer.unwrap(u);
         }

         public {
            if (MutablizingAdaptor.this == null) {
               throw null;
            } else {
               this.$outer = MutablizingAdaptor.this;
               Isomorphism.$init$(this);
            }
         }
      });
   }

   public static class IdentityWrapper implements MutablizingAdaptor, Product, Serializable {
      private final Object underlying;
      private final Object mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public Object underlying() {
         return this.underlying;
      }

      public Object mutaVspace() {
         return this.mutaVspace;
      }

      public Object wrap(final Object v) {
         return v;
      }

      public Object unwrap(final Object v) {
         return v;
      }

      public IdentityWrapper copy(final Object underlying) {
         return new IdentityWrapper(underlying);
      }

      public Object copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "IdentityWrapper";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof IdentityWrapper;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof IdentityWrapper) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  IdentityWrapper var4 = (IdentityWrapper)x$1;
                  if (BoxesRunTime.equals(this.underlying(), var4.underlying()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public IdentityWrapper(final Object underlying) {
         this.underlying = underlying;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = underlying;
         Statics.releaseFence();
      }
   }

   public static class IdentityWrapper$ implements Serializable {
      public static final IdentityWrapper$ MODULE$ = new IdentityWrapper$();

      public final String toString() {
         return "IdentityWrapper";
      }

      public IdentityWrapper apply(final Object underlying) {
         return new IdentityWrapper(underlying);
      }

      public Option unapply(final IdentityWrapper x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(IdentityWrapper$.class);
      }
   }

   public static class VectorSpaceAdaptor implements MutablizingAdaptor, Product, Serializable {
      private final VectorSpace underlying;
      private final MutableVectorSpace mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public VectorSpace underlying() {
         return this.underlying;
      }

      public Ref wrap(final Object v) {
         return new Ref(v);
      }

      public Object unwrap(final Ref w) {
         return w.value();
      }

      public MutableVectorSpace mutaVspace() {
         return this.mutaVspace;
      }

      public VectorSpaceAdaptor copy(final VectorSpace underlying) {
         return new VectorSpaceAdaptor(underlying);
      }

      public VectorSpace copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "VectorSpaceAdaptor";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof VectorSpaceAdaptor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof VectorSpaceAdaptor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        VectorSpaceAdaptor var4 = (VectorSpaceAdaptor)x$1;
                        VectorSpace var10000 = this.underlying();
                        VectorSpace var5 = var4.underlying();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public VectorSpaceAdaptor(final VectorSpace underlying) {
         this.underlying = underlying;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = new MutableVectorSpace() {
            private final VectorSpace u;
            private final Function1 hasOps;
            // $FF: synthetic field
            private final VectorSpaceAdaptor $outer;

            public UFunc.InPlaceImpl2 mulIntoVS_M() {
               return MutableModule.mulIntoVS_M$(this);
            }

            public UFunc.UImpl2 mulVS_M() {
               return Module.mulVS_M$(this);
            }

            private VectorSpace u() {
               return this.u;
            }

            public Field scalars() {
               return this.$outer.underlying().scalars();
            }

            public Function1 hasOps() {
               return this.hasOps;
            }

            public CanCreateZerosLike zeroLike() {
               return new CanCreateZerosLike() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Ref apply(final Ref from) {
                     CanCreateZerosLike var2 = this.$outer.breeze$math$MutablizingAdaptor$VectorSpaceAdaptor$$anon$$$outer().underlying().zeroLike();
                     return from.map((fromx) -> var2.apply(fromx));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanCopy copy() {
               return new CanCopy() {
                  public Ref apply(final Ref from) {
                     return from;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdate(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$1;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Object b) {
                     a.value_$eq(this.op$1.apply(a.value(), b));
                  }

                  public {
                     this.op$1 = op$1;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdateV(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$2;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(this.op$2.apply(a.value(), b.value()));
                  }

                  public {
                     this.op$2 = op$2;
                  }
               };
            }

            private UFunc.UImpl2 liftOp(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$3;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Object b) {
                     return a.map((x$1) -> this.op$3.apply(x$1, b));
                  }

                  public {
                     this.op$3 = op$3;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.UImpl2 liftOpV(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$4;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Ref b) {
                     return a.map((x$2) -> this.op$4.apply(x$2, b.value()));
                  }

                  public {
                     this.op$4 = op$4;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.InPlaceImpl2 mulIntoVS() {
               return this.liftUpdate(this.u().mulVS());
            }

            public UFunc.InPlaceImpl2 divIntoVS() {
               return this.liftUpdate(this.u().divVS());
            }

            public UFunc.InPlaceImpl2 addIntoVV() {
               return this.liftUpdateV(this.u().addVV());
            }

            public UFunc.InPlaceImpl2 subIntoVV() {
               return this.liftUpdateV(this.u().subVV());
            }

            public UFunc.InPlaceImpl2 setIntoVV() {
               return new UFunc.InPlaceImpl2() {
                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(b.value());
                  }
               };
            }

            public UFunc.InPlaceImpl3 scaleAddVV() {
               return new UFunc.InPlaceImpl3() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void apply(final Ref y, final Object a, final Ref x) {
                     y.$plus$eq(x.$times(a, this.$outer.mulVS_M()), this.$outer.addIntoVV());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 mulVS() {
               return this.liftOp(this.u().mulVS());
            }

            public UFunc.UImpl2 divVS() {
               return this.liftOp(this.u().divVS());
            }

            public UFunc.UImpl2 addVV() {
               return this.liftOpV(this.u().addVV());
            }

            public UFunc.UImpl2 subVV() {
               return this.liftOpV(this.u().subVV());
            }

            public boolean close(final Ref a, final Ref b, final double tolerance) {
               return this.u().close(a.value(), b.value(), tolerance);
            }

            // $FF: synthetic method
            public VectorSpaceAdaptor breeze$math$MutablizingAdaptor$VectorSpaceAdaptor$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (VectorSpaceAdaptor.this == null) {
                  throw null;
               } else {
                  this.$outer = VectorSpaceAdaptor.this;
                  Module.$init$(this);
                  MutableModule.$init$(this);
                  this.u = VectorSpaceAdaptor.this.underlying();
                  this.hasOps = (x) -> (Ref)scala.Predef..MODULE$.identity(x);
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         Statics.releaseFence();
      }
   }

   public static class VectorSpaceAdaptor$ implements Serializable {
      public static final VectorSpaceAdaptor$ MODULE$ = new VectorSpaceAdaptor$();

      public final String toString() {
         return "VectorSpaceAdaptor";
      }

      public VectorSpaceAdaptor apply(final VectorSpace underlying) {
         return new VectorSpaceAdaptor(underlying);
      }

      public Option unapply(final VectorSpaceAdaptor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(VectorSpaceAdaptor$.class);
      }
   }

   public static class InnerProductSpaceAdaptor implements MutablizingAdaptor, Product, Serializable {
      private final InnerProductVectorSpace underlying;
      private final MutableInnerProductVectorSpace mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public InnerProductVectorSpace underlying() {
         return this.underlying;
      }

      public Ref wrap(final Object v) {
         return new Ref(v);
      }

      public Object unwrap(final Ref w) {
         return w.value();
      }

      public MutableInnerProductVectorSpace mutaVspace() {
         return this.mutaVspace;
      }

      public InnerProductSpaceAdaptor copy(final InnerProductVectorSpace underlying) {
         return new InnerProductSpaceAdaptor(underlying);
      }

      public InnerProductVectorSpace copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "InnerProductSpaceAdaptor";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof InnerProductSpaceAdaptor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof InnerProductSpaceAdaptor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        InnerProductSpaceAdaptor var4 = (InnerProductSpaceAdaptor)x$1;
                        InnerProductVectorSpace var10000 = this.underlying();
                        InnerProductVectorSpace var5 = var4.underlying();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public InnerProductSpaceAdaptor(final InnerProductVectorSpace underlying) {
         this.underlying = underlying;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = new MutableInnerProductVectorSpace() {
            private final InnerProductVectorSpace breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u;
            private final Function1 hasOps;
            // $FF: synthetic field
            private final InnerProductSpaceAdaptor $outer;

            public UFunc.UImpl normImpl() {
               return InnerProductModule.normImpl$(this);
            }

            public UFunc.UImpl scalarNorm() {
               return NormedModule.scalarNorm$(this);
            }

            public UFunc.InPlaceImpl2 mulIntoVS_M() {
               return MutableModule.mulIntoVS_M$(this);
            }

            public UFunc.UImpl2 mulVS_M() {
               return Module.mulVS_M$(this);
            }

            public InnerProductVectorSpace breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u() {
               return this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u;
            }

            public Field scalars() {
               return this.$outer.underlying().scalars();
            }

            public Function1 hasOps() {
               return this.hasOps;
            }

            public CanCreateZerosLike zeroLike() {
               return new CanCreateZerosLike() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Ref apply(final Ref from) {
                     CanCreateZerosLike var2 = this.$outer.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$$outer().underlying().zeroLike();
                     return from.map((fromx) -> var2.apply(fromx));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanCopy copy() {
               return new CanCopy() {
                  public Ref apply(final Ref from) {
                     return from;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdate(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$5;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Object b) {
                     a.value_$eq(this.op$5.apply(a.value(), b));
                  }

                  public {
                     this.op$5 = op$5;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdateV(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$6;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(this.op$6.apply(a.value(), b.value()));
                  }

                  public {
                     this.op$6 = op$6;
                  }
               };
            }

            private UFunc.UImpl2 liftOp(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$7;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Object b) {
                     return a.map((x$3) -> this.op$7.apply(x$3, b));
                  }

                  public {
                     this.op$7 = op$7;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.UImpl2 liftOpV(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$8;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Ref b) {
                     return a.map((x$4) -> this.op$8.apply(x$4, b.value()));
                  }

                  public {
                     this.op$8 = op$8;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.InPlaceImpl2 mulIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().mulVS());
            }

            public UFunc.InPlaceImpl2 divIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().divVS());
            }

            public UFunc.InPlaceImpl2 addIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().addVV());
            }

            public UFunc.InPlaceImpl2 subIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().subVV());
            }

            public UFunc.InPlaceImpl2 setIntoVV() {
               return new UFunc.InPlaceImpl2() {
                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(b.value());
                  }
               };
            }

            public UFunc.InPlaceImpl3 scaleAddVV() {
               return new UFunc.InPlaceImpl3() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void apply(final Ref y, final Object a, final Ref x) {
                     y.$plus$eq(x.$times(a, this.$outer.mulVS_M()), this.$outer.addIntoVV());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 mulVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().mulVS());
            }

            public UFunc.UImpl2 divVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().divVS());
            }

            public UFunc.UImpl2 addVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().addVV());
            }

            public UFunc.UImpl2 subVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().subVV());
            }

            public boolean close(final Ref a, final Ref b, final double tolerance) {
               return this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().close(a.value(), b.value(), tolerance);
            }

            public UFunc.UImpl2 dotVV() {
               return new UFunc.UImpl2() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Object apply(final Ref a, final Ref b) {
                     return this.$outer.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u().dotVV().apply(a.value(), b.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            // $FF: synthetic method
            public InnerProductSpaceAdaptor breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (InnerProductSpaceAdaptor.this == null) {
                  throw null;
               } else {
                  this.$outer = InnerProductSpaceAdaptor.this;
                  Module.$init$(this);
                  MutableModule.$init$(this);
                  NormedModule.$init$(this);
                  InnerProductModule.$init$(this);
                  this.breeze$math$MutablizingAdaptor$InnerProductSpaceAdaptor$$anon$$u = InnerProductSpaceAdaptor.this.underlying();
                  this.hasOps = (x) -> (Ref)scala.Predef..MODULE$.identity(x);
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         Statics.releaseFence();
      }
   }

   public static class InnerProductSpaceAdaptor$ implements Serializable {
      public static final InnerProductSpaceAdaptor$ MODULE$ = new InnerProductSpaceAdaptor$();

      public final String toString() {
         return "InnerProductSpaceAdaptor";
      }

      public InnerProductSpaceAdaptor apply(final InnerProductVectorSpace underlying) {
         return new InnerProductSpaceAdaptor(underlying);
      }

      public Option unapply(final InnerProductSpaceAdaptor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(InnerProductSpaceAdaptor$.class);
      }
   }

   public static class VectorFieldAdaptor implements MutablizingAdaptor, Product, Serializable {
      private final VectorField underlying;
      public final CanTraverseValues breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canIterate;
      public final CanMapValues breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canMap;
      public final CanZipMapValues breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canZipMap;
      private final MutableVectorField mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public VectorField underlying() {
         return this.underlying;
      }

      public Ref wrap(final Object v) {
         return new Ref(v);
      }

      public Object unwrap(final Ref w) {
         return w.value();
      }

      public MutableVectorField mutaVspace() {
         return this.mutaVspace;
      }

      public VectorFieldAdaptor copy(final VectorField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new VectorFieldAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public VectorField copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "VectorFieldAdaptor";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof VectorFieldAdaptor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof VectorFieldAdaptor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        VectorFieldAdaptor var4 = (VectorFieldAdaptor)x$1;
                        VectorField var10000 = this.underlying();
                        VectorField var5 = var4.underlying();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public VectorFieldAdaptor(final VectorField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         this.underlying = underlying;
         this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canIterate = canIterate;
         this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canMap = canMap;
         this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canZipMap = canZipMap;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = new MutableVectorField() {
            private final VectorField breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u;
            private final Function1 hasOps;
            // $FF: synthetic field
            private final VectorFieldAdaptor $outer;

            public UFunc.InPlaceImpl2 mulIntoVS_M() {
               return MutableModule.mulIntoVS_M$(this);
            }

            public UFunc.UImpl normImpl() {
               return InnerProductModule.normImpl$(this);
            }

            public UFunc.UImpl scalarNorm() {
               return NormedModule.scalarNorm$(this);
            }

            public UFunc.UImpl2 mulVS_M() {
               return Module.mulVS_M$(this);
            }

            public VectorField breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u() {
               return this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u;
            }

            public Field scalars() {
               return this.$outer.underlying().scalars();
            }

            public Function1 hasOps() {
               return this.hasOps;
            }

            public CanCreateZerosLike zeroLike() {
               return new CanCreateZerosLike() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Ref apply(final Ref from) {
                     CanCreateZerosLike var2 = this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().underlying().zeroLike();
                     return from.map((fromx) -> var2.apply(fromx));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanCopy copy() {
               return new CanCopy() {
                  public Ref apply(final Ref from) {
                     return from;
                  }
               };
            }

            private CanTraverseValues iterateValues() {
               return new CanTraverseValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object foldLeft(final Object from, final Object b, final Function2 fn) {
                     return CanTraverseValues.foldLeft$(this, from, b, fn);
                  }

                  public CanTraverseValues.ValuesVisitor traverse(final Ref from, final CanTraverseValues.ValuesVisitor fn) {
                     from.map((x$5) -> this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canIterate.traverse(x$5, fn));
                     return fn;
                  }

                  public boolean isTraversableAgain(final Ref from) {
                     return this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canIterate.isTraversableAgain(from.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        CanTraverseValues.$init$(this);
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private CanMapValues mapValues() {
               return new CanMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object map$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDD$sp$(this, from, fn);
                  }

                  public Object map$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDF$sp$(this, from, fn);
                  }

                  public Object map$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDI$sp$(this, from, fn);
                  }

                  public Object map$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDJ$sp$(this, from, fn);
                  }

                  public Object map$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFD$sp$(this, from, fn);
                  }

                  public Object map$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFF$sp$(this, from, fn);
                  }

                  public Object map$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFI$sp$(this, from, fn);
                  }

                  public Object map$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFJ$sp$(this, from, fn);
                  }

                  public Object map$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcID$sp$(this, from, fn);
                  }

                  public Object map$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIF$sp$(this, from, fn);
                  }

                  public Object map$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcII$sp$(this, from, fn);
                  }

                  public Object map$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIJ$sp$(this, from, fn);
                  }

                  public Object map$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJD$sp$(this, from, fn);
                  }

                  public Object map$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJF$sp$(this, from, fn);
                  }

                  public Object map$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJI$sp$(this, from, fn);
                  }

                  public Object map$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcID$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcII$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
                  }

                  public Ref map(final Ref from, final Function1 fn) {
                     return from.map((x$6) -> this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canMap.map(x$6, fn));
                  }

                  public Ref mapActive(final Ref from, final Function1 fn) {
                     return from.map((x$7) -> this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canMap.mapActive(x$7, fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return Class.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private CanZipMapValues zipMapValues() {
               return new CanZipMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref map(final Ref from, final Ref from2, final Function2 fn) {
                     return from.map((x$8) -> this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$canZipMap.map(x$8, from2.value(), fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdate(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$9;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Object b) {
                     a.value_$eq(this.op$9.apply(a.value(), b));
                  }

                  public {
                     this.op$9 = op$9;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdateV(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$10;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(this.op$10.apply(a.value(), b.value()));
                  }

                  public {
                     this.op$10 = op$10;
                  }
               };
            }

            private UFunc.UImpl2 liftOp(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$11;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Object b) {
                     return a.map((x$9) -> this.op$11.apply(x$9, b));
                  }

                  public {
                     this.op$11 = op$11;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.UImpl2 liftOpV(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$12;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Ref b) {
                     return a.map((x$10) -> this.op$12.apply(x$10, b.value()));
                  }

                  public {
                     this.op$12 = op$12;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.InPlaceImpl2 mulIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().mulVS());
            }

            public UFunc.InPlaceImpl2 divIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().divVS());
            }

            public UFunc.InPlaceImpl2 addIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().addVV());
            }

            public UFunc.InPlaceImpl2 subIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().subVV());
            }

            public UFunc.InPlaceImpl2 setIntoVV() {
               return new UFunc.InPlaceImpl2() {
                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(b.value());
                  }
               };
            }

            public UFunc.UImpl2 mulVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().mulVV());
            }

            public UFunc.InPlaceImpl2 mulIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().mulVV());
            }

            public UFunc.UImpl2 divVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().divVV());
            }

            public UFunc.InPlaceImpl2 divIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().divVV());
            }

            public UFunc.InPlaceImpl3 scaleAddVV() {
               return new UFunc.InPlaceImpl3() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void apply(final Ref y, final Object a, final Ref x) {
                     y.$plus$eq(x.$times(a, this.$outer.mulVS_M()), this.$outer.addIntoVV());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 mulVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().mulVS());
            }

            public UFunc.UImpl2 divVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().divVS());
            }

            public UFunc.UImpl2 addVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().addVV());
            }

            public UFunc.UImpl2 subVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().subVV());
            }

            public boolean close(final Ref a, final Ref b, final double tolerance) {
               return this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().close(a.value(), b.value(), tolerance);
            }

            public UFunc.UImpl neg() {
               return new UFunc.UImpl() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref apply(final Ref a) {
                     UFunc.UImpl var2 = this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().neg();
                     return a.map((v) -> var2.apply(v));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.UImpl2 dotVV() {
               return new UFunc.UImpl2() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Object apply(final Ref a, final Ref b) {
                     return this.$outer.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u().dotVV().apply(a.value(), b.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            // $FF: synthetic method
            public VectorFieldAdaptor breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (VectorFieldAdaptor.this == null) {
                  throw null;
               } else {
                  this.$outer = VectorFieldAdaptor.this;
                  Module.$init$(this);
                  NormedModule.$init$(this);
                  InnerProductModule.$init$(this);
                  MutableModule.$init$(this);
                  this.breeze$math$MutablizingAdaptor$VectorFieldAdaptor$$anon$$u = VectorFieldAdaptor.this.underlying();
                  this.hasOps = (x) -> (Ref)scala.Predef..MODULE$.identity(x);
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         Statics.releaseFence();
      }
   }

   public static class VectorFieldAdaptor$ implements Serializable {
      public static final VectorFieldAdaptor$ MODULE$ = new VectorFieldAdaptor$();

      public final String toString() {
         return "VectorFieldAdaptor";
      }

      public VectorFieldAdaptor apply(final VectorField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new VectorFieldAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public Option unapply(final VectorFieldAdaptor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(VectorFieldAdaptor$.class);
      }
   }

   public static class Ref implements NumericOps, Product, Serializable {
      private Object value;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public final Object $plus(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$plus$(this, b, op);
      }

      public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$eq$(this, b, op);
      }

      public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$plus$eq$(this, b, op);
      }

      public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$times$eq$(this, b, op);
      }

      public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$plus$eq$(this, b, op);
      }

      public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$times$eq$(this, b, op);
      }

      public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$minus$eq$(this, b, op);
      }

      public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$percent$eq$(this, b, op);
      }

      public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$percent$eq$(this, b, op);
      }

      public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$minus$eq$(this, b, op);
      }

      public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$div$eq$(this, b, op);
      }

      public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$eq$(this, b, op);
      }

      public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$div$eq$(this, b, op);
      }

      public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$less$(this, b, op);
      }

      public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$less$colon$eq$(this, b, op);
      }

      public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$greater$(this, b, op);
      }

      public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
         return NumericOps.$greater$colon$eq$(this, b, op);
      }

      public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$amp$eq$(this, b, op);
      }

      public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$bar$eq$(this, b, op);
      }

      public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$colon$up$up$eq$(this, b, op);
      }

      public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$amp$eq$(this, b, op);
      }

      public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$bar$eq$(this, b, op);
      }

      public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
         return NumericOps.$up$up$eq$(this, b, op);
      }

      public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
      }

      public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$colon$times$(this, b, op);
      }

      public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
      }

      public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
      }

      public final Object unary_$minus(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$minus$(this, op);
      }

      public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
      }

      public final Object $minus(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$minus$(this, b, op);
      }

      public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
      }

      public final Object $percent(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$percent$(this, b, op);
      }

      public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$colon$div$(this, b, op);
      }

      public final Object $div(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$div$(this, b, op);
      }

      public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$colon$up$(this, b, op);
      }

      public final Object dot(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.dot$(this, b, op);
      }

      public final Object unary_$bang(final UFunc.UImpl op) {
         return ImmutableNumericOps.unary_$bang$(this, op);
      }

      public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
      }

      public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
      }

      public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
      }

      public final Object $amp(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$amp$(this, b, op);
      }

      public final Object $bar(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bar$(this, b, op);
      }

      public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$up$up$(this, b, op);
      }

      public final Object $times(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$times$(this, b, op);
      }

      public final Object t(final CanTranspose op) {
         return ImmutableNumericOps.t$(this, op);
      }

      public Object $bslash(final Object b, final UFunc.UImpl2 op) {
         return ImmutableNumericOps.$bslash$(this, b, op);
      }

      public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
         return ImmutableNumericOps.t$(this, a, b, op, canSlice);
      }

      public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
         return ImmutableNumericOps.t$(this, a, op, canSlice);
      }

      public Object value() {
         return this.value;
      }

      public void value_$eq(final Object x$1) {
         this.value = x$1;
      }

      public Ref repr() {
         return this;
      }

      public Ref map(final Function1 f) {
         return new Ref(f.apply(this.value()));
      }

      public Ref copy(final Object value) {
         return new Ref(value);
      }

      public Object copy$default$1() {
         return this.value();
      }

      public String productPrefix() {
         return "Ref";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.value();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Ref;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "value";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof Ref) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  Ref var4 = (Ref)x$1;
                  if (BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Ref(final Object value) {
         this.value = value;
         super();
         ImmutableNumericOps.$init$(this);
         NumericOps.$init$(this);
         Product.$init$(this);
      }
   }

   public static class Ref$ implements Serializable {
      public static final Ref$ MODULE$ = new Ref$();

      public final String toString() {
         return "Ref";
      }

      public Ref apply(final Object value) {
         return new Ref(value);
      }

      public Option unapply(final Ref x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.value()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Ref$.class);
      }
   }

   public static class VectorRingAdaptor implements MutablizingAdaptor, Product, Serializable {
      private final VectorRing underlying;
      public final CanTraverseValues breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canIterate;
      public final CanMapValues breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canMap;
      public final CanZipMapValues breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canZipMap;
      private final MutableVectorRing mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public VectorRing underlying() {
         return this.underlying;
      }

      public Ref wrap(final Object v) {
         return new Ref(v);
      }

      public Object unwrap(final Ref w) {
         return w.value();
      }

      public MutableVectorRing mutaVspace() {
         return this.mutaVspace;
      }

      public VectorRingAdaptor copy(final VectorRing underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new VectorRingAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public VectorRing copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "VectorRingAdaptor";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof VectorRingAdaptor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof VectorRingAdaptor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        VectorRingAdaptor var4 = (VectorRingAdaptor)x$1;
                        VectorRing var10000 = this.underlying();
                        VectorRing var5 = var4.underlying();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public VectorRingAdaptor(final VectorRing underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         this.underlying = underlying;
         this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canIterate = canIterate;
         this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canMap = canMap;
         this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canZipMap = canZipMap;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = new MutableVectorRing() {
            private final VectorRing breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u;
            private final Function1 hasOps;
            // $FF: synthetic field
            private final VectorRingAdaptor $outer;

            public UFunc.InPlaceImpl2 mulIntoVS_M() {
               return MutableModule.mulIntoVS_M$(this);
            }

            public UFunc.UImpl normImpl() {
               return InnerProductModule.normImpl$(this);
            }

            public UFunc.UImpl scalarNorm() {
               return NormedModule.scalarNorm$(this);
            }

            public UFunc.UImpl2 mulVS_M() {
               return Module.mulVS_M$(this);
            }

            public VectorRing breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u() {
               return this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u;
            }

            public Ring scalars() {
               return this.$outer.underlying().scalars();
            }

            public Function1 hasOps() {
               return this.hasOps;
            }

            public CanCreateZerosLike zeroLike() {
               return new CanCreateZerosLike() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Ref apply(final Ref from) {
                     CanCreateZerosLike var2 = this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().underlying().zeroLike();
                     return from.map((fromx) -> var2.apply(fromx));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanCopy copy() {
               return new CanCopy() {
                  public Ref apply(final Ref from) {
                     return from;
                  }
               };
            }

            private CanTraverseValues iterateValues() {
               return new CanTraverseValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object foldLeft(final Object from, final Object b, final Function2 fn) {
                     return CanTraverseValues.foldLeft$(this, from, b, fn);
                  }

                  public CanTraverseValues.ValuesVisitor traverse(final Ref from, final CanTraverseValues.ValuesVisitor fn) {
                     from.map((x$11) -> this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canIterate.traverse(x$11, fn));
                     return fn;
                  }

                  public boolean isTraversableAgain(final Ref from) {
                     return this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canIterate.isTraversableAgain(from.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        CanTraverseValues.$init$(this);
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private CanMapValues mapValues() {
               return new CanMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object map$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDD$sp$(this, from, fn);
                  }

                  public Object map$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDF$sp$(this, from, fn);
                  }

                  public Object map$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDI$sp$(this, from, fn);
                  }

                  public Object map$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDJ$sp$(this, from, fn);
                  }

                  public Object map$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFD$sp$(this, from, fn);
                  }

                  public Object map$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFF$sp$(this, from, fn);
                  }

                  public Object map$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFI$sp$(this, from, fn);
                  }

                  public Object map$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFJ$sp$(this, from, fn);
                  }

                  public Object map$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcID$sp$(this, from, fn);
                  }

                  public Object map$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIF$sp$(this, from, fn);
                  }

                  public Object map$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcII$sp$(this, from, fn);
                  }

                  public Object map$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIJ$sp$(this, from, fn);
                  }

                  public Object map$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJD$sp$(this, from, fn);
                  }

                  public Object map$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJF$sp$(this, from, fn);
                  }

                  public Object map$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJI$sp$(this, from, fn);
                  }

                  public Object map$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcID$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcII$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
                  }

                  public Ref map(final Ref from, final Function1 fn) {
                     return from.map((x$12) -> this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canMap.map(x$12, fn));
                  }

                  public Ref mapActive(final Ref from, final Function1 fn) {
                     return from.map((x$13) -> this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canMap.mapActive(x$13, fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return Class.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private CanZipMapValues zipMapValues() {
               return new CanZipMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref map(final Ref from, final Ref from2, final Function2 fn) {
                     return from.map((x$14) -> this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$VectorRingAdaptor$$canZipMap.map(x$14, from2.value(), fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdate(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$13;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Object b) {
                     a.value_$eq(this.op$13.apply(a.value(), b));
                  }

                  public {
                     this.op$13 = op$13;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdateV(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$14;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(this.op$14.apply(a.value(), b.value()));
                  }

                  public {
                     this.op$14 = op$14;
                  }
               };
            }

            private UFunc.UImpl2 liftOp(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$15;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Object b) {
                     return a.map((x$15) -> this.op$15.apply(x$15, b));
                  }

                  public {
                     this.op$15 = op$15;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.UImpl2 liftOpV(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$16;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Ref b) {
                     return a.map((x$16) -> this.op$16.apply(x$16, b.value()));
                  }

                  public {
                     this.op$16 = op$16;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.InPlaceImpl2 mulIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().mulVS());
            }

            public UFunc.InPlaceImpl2 addIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().addVV());
            }

            public UFunc.InPlaceImpl2 subIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().subVV());
            }

            public UFunc.InPlaceImpl2 setIntoVV() {
               return new UFunc.InPlaceImpl2() {
                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(b.value());
                  }
               };
            }

            public UFunc.UImpl2 mulVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().mulVV());
            }

            public UFunc.InPlaceImpl2 mulIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().mulVV());
            }

            public UFunc.InPlaceImpl3 scaleAddVV() {
               return new UFunc.InPlaceImpl3() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void apply(final Ref y, final Object a, final Ref x) {
                     y.$plus$eq(x.$times(a, this.$outer.mulVS_M()), this.$outer.addIntoVV());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 mulVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().mulVS());
            }

            public UFunc.UImpl2 addVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().addVV());
            }

            public UFunc.UImpl2 subVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().subVV());
            }

            public boolean close(final Ref a, final Ref b, final double tolerance) {
               return this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().close(a.value(), b.value(), tolerance);
            }

            public UFunc.UImpl neg() {
               return new UFunc.UImpl() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref apply(final Ref a) {
                     UFunc.UImpl var2 = this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().neg();
                     return a.map((v) -> var2.apply(v));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.UImpl2 dotVV() {
               return new UFunc.UImpl2() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Object apply(final Ref a, final Ref b) {
                     return this.$outer.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u().dotVV().apply(a.value(), b.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            // $FF: synthetic method
            public VectorRingAdaptor breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (VectorRingAdaptor.this == null) {
                  throw null;
               } else {
                  this.$outer = VectorRingAdaptor.this;
                  Module.$init$(this);
                  NormedModule.$init$(this);
                  InnerProductModule.$init$(this);
                  MutableModule.$init$(this);
                  this.breeze$math$MutablizingAdaptor$VectorRingAdaptor$$anon$$u = VectorRingAdaptor.this.underlying();
                  this.hasOps = (x) -> (Ref)scala.Predef..MODULE$.identity(x);
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         Statics.releaseFence();
      }
   }

   public static class VectorRingAdaptor$ implements Serializable {
      public static final VectorRingAdaptor$ MODULE$ = new VectorRingAdaptor$();

      public final String toString() {
         return "VectorRingAdaptor";
      }

      public VectorRingAdaptor apply(final VectorRing underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new VectorRingAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public Option unapply(final VectorRingAdaptor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(VectorRingAdaptor$.class);
      }
   }

   public static class CoordinateFieldAdaptor implements MutablizingAdaptor, Product, Serializable {
      private final CoordinateField underlying;
      public final CanTraverseValues breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canIterate;
      public final CanMapValues breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canMap;
      public final CanZipMapValues breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canZipMap;
      private final MutableCoordinateField mutaVspace;
      private Isomorphism isomorphism;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object mutaVSpaceIdent(final Object wrapper) {
         return MutablizingAdaptor.super.mutaVSpaceIdent(wrapper);
      }

      public Isomorphism isomorphism() {
         return this.isomorphism;
      }

      public void breeze$math$MutablizingAdaptor$_setter_$isomorphism_$eq(final Isomorphism x$1) {
         this.isomorphism = x$1;
      }

      public CoordinateField underlying() {
         return this.underlying;
      }

      public Ref wrap(final Object v) {
         return new Ref(v);
      }

      public Object unwrap(final Ref w) {
         return w.value();
      }

      public MutableCoordinateField mutaVspace() {
         return this.mutaVspace;
      }

      public CoordinateFieldAdaptor copy(final CoordinateField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new CoordinateFieldAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public CoordinateField copy$default$1() {
         return this.underlying();
      }

      public String productPrefix() {
         return "CoordinateFieldAdaptor";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.underlying();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof CoordinateFieldAdaptor;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "underlying";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var7;
         if (this != x$1) {
            label53: {
               boolean var2;
               if (x$1 instanceof CoordinateFieldAdaptor) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  label36: {
                     label35: {
                        CoordinateFieldAdaptor var4 = (CoordinateFieldAdaptor)x$1;
                        CoordinateField var10000 = this.underlying();
                        CoordinateField var5 = var4.underlying();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label35;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label35;
                        }

                        if (var4.canEqual(this)) {
                           var7 = true;
                           break label36;
                        }
                     }

                     var7 = false;
                  }

                  if (var7) {
                     break label53;
                  }
               }

               var7 = false;
               return var7;
            }
         }

         var7 = true;
         return var7;
      }

      public CoordinateFieldAdaptor(final CoordinateField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         this.underlying = underlying;
         this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canIterate = canIterate;
         this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canMap = canMap;
         this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canZipMap = canZipMap;
         MutablizingAdaptor.$init$(this);
         Product.$init$(this);
         this.mutaVspace = new MutableCoordinateField() {
            private final CoordinateField breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u;
            private final Function1 hasOps;
            // $FF: synthetic field
            private final CoordinateFieldAdaptor $outer;

            public UFunc.InPlaceImpl2 mulIntoVS_M() {
               return MutableModule.mulIntoVS_M$(this);
            }

            public UFunc.UImpl normImpl() {
               return InnerProductModule.normImpl$(this);
            }

            public UFunc.UImpl scalarNorm() {
               return NormedModule.scalarNorm$(this);
            }

            public UFunc.UImpl2 mulVS_M() {
               return Module.mulVS_M$(this);
            }

            public CoordinateField breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u() {
               return this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u;
            }

            public Field scalars() {
               return this.$outer.underlying().scalars();
            }

            public Function1 hasOps() {
               return this.hasOps;
            }

            public CanCreateZerosLike zeroLike() {
               return new CanCreateZerosLike() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Ref apply(final Ref from) {
                     CanCreateZerosLike var2 = this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().underlying().zeroLike();
                     return from.map((fromx) -> var2.apply(fromx));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanCopy copy() {
               return new CanCopy() {
                  public Ref apply(final Ref from) {
                     return from;
                  }
               };
            }

            public CanTraverseValues iterateValues() {
               return new CanTraverseValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object foldLeft(final Object from, final Object b, final Function2 fn) {
                     return CanTraverseValues.foldLeft$(this, from, b, fn);
                  }

                  public CanTraverseValues.ValuesVisitor traverse(final Ref from, final CanTraverseValues.ValuesVisitor fn) {
                     from.map((x$17) -> this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canIterate.traverse(x$17, fn));
                     return fn;
                  }

                  public boolean isTraversableAgain(final Ref from) {
                     return this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canIterate.isTraversableAgain(from.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        CanTraverseValues.$init$(this);
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public CanMapValues mapValues() {
               return new CanMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Object map$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDD$sp$(this, from, fn);
                  }

                  public Object map$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDF$sp$(this, from, fn);
                  }

                  public Object map$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDI$sp$(this, from, fn);
                  }

                  public Object map$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcDJ$sp$(this, from, fn);
                  }

                  public Object map$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFD$sp$(this, from, fn);
                  }

                  public Object map$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFF$sp$(this, from, fn);
                  }

                  public Object map$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFI$sp$(this, from, fn);
                  }

                  public Object map$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcFJ$sp$(this, from, fn);
                  }

                  public Object map$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcID$sp$(this, from, fn);
                  }

                  public Object map$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIF$sp$(this, from, fn);
                  }

                  public Object map$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcII$sp$(this, from, fn);
                  }

                  public Object map$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcIJ$sp$(this, from, fn);
                  }

                  public Object map$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJD$sp$(this, from, fn);
                  }

                  public Object map$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJF$sp$(this, from, fn);
                  }

                  public Object map$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJI$sp$(this, from, fn);
                  }

                  public Object map$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.map$mcJJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcDJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcDJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcFJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcFJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcID$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcID$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcII$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcII$sp$(this, from, fn);
                  }

                  public Object mapActive$mcIJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcIJ$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJD$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJD$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJF$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJF$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJI$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJI$sp$(this, from, fn);
                  }

                  public Object mapActive$mcJJ$sp(final Object from, final Function1 fn) {
                     return CanMapValues.mapActive$mcJJ$sp$(this, from, fn);
                  }

                  public Ref map(final Ref from, final Function1 fn) {
                     return from.map((x$18) -> this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canMap.map(x$18, fn));
                  }

                  public Ref mapActive(final Ref from, final Function1 fn) {
                     return from.map((x$19) -> this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canMap.mapActive(x$19, fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return Class.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public ScalarOf scalarOf() {
               return ScalarOf$.MODULE$.dummy();
            }

            public CanZipMapValues zipMapValues() {
               return new CanZipMapValues() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref map(final Ref from, final Ref from2, final Function2 fn) {
                     return from.map((x$20) -> this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$canZipMap.map(x$20, from2.value(), fn));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdate(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$17;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Object b) {
                     a.value_$eq(this.op$17.apply(a.value(), b));
                  }

                  public {
                     this.op$17 = op$17;
                  }
               };
            }

            private UFunc.InPlaceImpl2 liftUpdateV(final UFunc.UImpl2 op) {
               return new UFunc.InPlaceImpl2(op) {
                  private final UFunc.UImpl2 op$18;

                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(this.op$18.apply(a.value(), b.value()));
                  }

                  public {
                     this.op$18 = op$18;
                  }
               };
            }

            private UFunc.UImpl2 liftOp(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$19;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Object b) {
                     return a.map((x$21) -> this.op$19.apply(x$21, b));
                  }

                  public {
                     this.op$19 = op$19;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            private UFunc.UImpl2 liftOpV(final UFunc.UImpl2 op) {
               return new UFunc.UImpl2(op) {
                  private final UFunc.UImpl2 op$20;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Ref apply(final Ref a, final Ref b) {
                     return a.map((x$22) -> this.op$20.apply(x$22, b.value()));
                  }

                  public {
                     this.op$20 = op$20;
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.InPlaceImpl2 mulIntoVS() {
               return this.liftUpdate(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().mulVS());
            }

            public UFunc.InPlaceImpl2 addIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().addVV());
            }

            public UFunc.InPlaceImpl2 subIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().subVV());
            }

            public UFunc.InPlaceImpl2 setIntoVV() {
               return new UFunc.InPlaceImpl2() {
                  public void apply$mcD$sp(final Object v, final double v2) {
                     UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
                  }

                  public void apply$mcF$sp(final Object v, final float v2) {
                     UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
                  }

                  public void apply$mcI$sp(final Object v, final int v2) {
                     UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
                  }

                  public void apply(final Ref a, final Ref b) {
                     a.value_$eq(b.value());
                  }
               };
            }

            public UFunc.UImpl2 mulVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().mulVV());
            }

            public UFunc.InPlaceImpl2 mulIntoVV() {
               return this.liftUpdateV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().mulVV());
            }

            public UFunc.InPlaceImpl3 scaleAddVV() {
               return new UFunc.InPlaceImpl3() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void apply(final Ref y, final Object a, final Ref x) {
                     y.$plus$eq(x.$times(a, this.$outer.mulVS_M()), this.$outer.addIntoVV());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 mulVS() {
               return this.liftOp(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().mulVS());
            }

            public UFunc.UImpl2 addVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().addVV());
            }

            public UFunc.UImpl2 subVV() {
               return this.liftOpV(this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().subVV());
            }

            public boolean close(final Ref a, final Ref b, final double tolerance) {
               return this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().close(a.value(), b.value(), tolerance);
            }

            public UFunc.UImpl neg() {
               return new UFunc.UImpl() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

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

                  public Ref apply(final Ref a) {
                     UFunc.UImpl var2 = this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().neg();
                     return a.map((v) -> var2.apply(v));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }

                  // $FF: synthetic method
                  private static Object $deserializeLambda$(SerializedLambda var0) {
                     return var0.lambdaDeserialize<invokedynamic>(var0);
                  }
               };
            }

            public UFunc.UImpl2 dotVV() {
               return new UFunc.UImpl2() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public Object apply(final Ref a, final Ref b) {
                     return this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u().dotVV().apply(a.value(), b.value());
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.UImpl2 normImpl2() {
               return new UFunc.UImpl2() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public double apply$mcDDD$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
                  }

                  public float apply$mcDDF$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
                  }

                  public int apply$mcDDI$sp(final double v, final double v2) {
                     return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
                  }

                  public double apply$mcDFD$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
                  }

                  public float apply$mcDFF$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
                  }

                  public int apply$mcDFI$sp(final double v, final float v2) {
                     return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
                  }

                  public double apply$mcDID$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
                  }

                  public float apply$mcDIF$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
                  }

                  public int apply$mcDII$sp(final double v, final int v2) {
                     return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
                  }

                  public double apply$mcFDD$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
                  }

                  public float apply$mcFDF$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
                  }

                  public int apply$mcFDI$sp(final float v, final double v2) {
                     return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
                  }

                  public double apply$mcFFD$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
                  }

                  public float apply$mcFFF$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
                  }

                  public int apply$mcFFI$sp(final float v, final float v2) {
                     return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
                  }

                  public double apply$mcFID$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
                  }

                  public float apply$mcFIF$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
                  }

                  public int apply$mcFII$sp(final float v, final int v2) {
                     return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
                  }

                  public double apply$mcIDD$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
                  }

                  public float apply$mcIDF$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
                  }

                  public int apply$mcIDI$sp(final int v, final double v2) {
                     return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
                  }

                  public double apply$mcIFD$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
                  }

                  public float apply$mcIFF$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
                  }

                  public int apply$mcIFI$sp(final int v, final float v2) {
                     return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
                  }

                  public double apply$mcIID$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
                  }

                  public float apply$mcIIF$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
                  }

                  public int apply$mcIII$sp(final int v, final int v2) {
                     return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
                  }

                  public double apply(final Ref v, final double v2) {
                     return BoxesRunTime.unboxToDouble(this.$outer.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer().underlying().normImpl2().apply(v.value(), BoxesRunTime.boxToDouble(v2)));
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               };
            }

            public UFunc.InPlaceImpl2 divIntoVV() {
               return this.liftUpdateV(this.$outer.underlying().divVV());
            }

            public UFunc.UImpl2 divVV() {
               return this.liftOpV(this.$outer.underlying().divVV());
            }

            public UFunc.InPlaceImpl2 divIntoVS() {
               return this.liftUpdate(this.$outer.underlying().divVS());
            }

            public UFunc.UImpl2 divVS() {
               return this.liftOp(this.$outer.underlying().divVS());
            }

            // $FF: synthetic method
            public CoordinateFieldAdaptor breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (CoordinateFieldAdaptor.this == null) {
                  throw null;
               } else {
                  this.$outer = CoordinateFieldAdaptor.this;
                  Module.$init$(this);
                  NormedModule.$init$(this);
                  InnerProductModule.$init$(this);
                  MutableModule.$init$(this);
                  this.breeze$math$MutablizingAdaptor$CoordinateFieldAdaptor$$anon$$u = CoordinateFieldAdaptor.this.underlying();
                  this.hasOps = (x) -> (Ref)scala.Predef..MODULE$.identity(x);
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return var0.lambdaDeserialize<invokedynamic>(var0);
            }
         };
         Statics.releaseFence();
      }
   }

   public static class CoordinateFieldAdaptor$ implements Serializable {
      public static final CoordinateFieldAdaptor$ MODULE$ = new CoordinateFieldAdaptor$();

      public final String toString() {
         return "CoordinateFieldAdaptor";
      }

      public CoordinateFieldAdaptor apply(final CoordinateField underlying, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap) {
         return new CoordinateFieldAdaptor(underlying, canIterate, canMap, canZipMap);
      }

      public Option unapply(final CoordinateFieldAdaptor x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.underlying()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(CoordinateFieldAdaptor$.class);
      }
   }
}
