package scala.collection.mutable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AnyStepper$;
import scala.collection.ArrayOps;
import scala.collection.ArrayOps$;
import scala.collection.ArrayOps$ArrayIterator$mcB$sp;
import scala.collection.ArrayOps$ArrayIterator$mcC$sp;
import scala.collection.ArrayOps$ArrayIterator$mcD$sp;
import scala.collection.ArrayOps$ArrayIterator$mcF$sp;
import scala.collection.ArrayOps$ArrayIterator$mcI$sp;
import scala.collection.ArrayOps$ArrayIterator$mcJ$sp;
import scala.collection.ArrayOps$ArrayIterator$mcS$sp;
import scala.collection.ArrayOps$ArrayIterator$mcV$sp;
import scala.collection.ArrayOps$ArrayIterator$mcZ$sp;
import scala.collection.ClassTagIterableFactory;
import scala.collection.DoubleStepper;
import scala.collection.EvidenceIterableFactory;
import scala.collection.Factory;
import scala.collection.IndexedSeqView;
import scala.collection.IntStepper;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.Iterator;
import scala.collection.LongStepper;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.View;
import scala.collection.convert.impl.BoxedBooleanArrayStepper;
import scala.collection.convert.impl.DoubleArrayStepper;
import scala.collection.convert.impl.IntArrayStepper;
import scala.collection.convert.impl.LongArrayStepper;
import scala.collection.convert.impl.ObjectArrayStepper;
import scala.collection.convert.impl.WidenedByteArrayStepper;
import scala.collection.convert.impl.WidenedCharArrayStepper;
import scala.collection.convert.impl.WidenedFloatArrayStepper;
import scala.collection.convert.impl.WidenedShortArrayStepper;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ManifestFactory;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime$;
import scala.util.Sorting$;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019Ud\u0001CA\u0015\u0003W\t\t#!\u000f\t\u000f\u00055\u0005\u0001\"\u0001\u0002\u0010\"9\u0011\u0011\u0013\u0001\u0005B\u0005M\u0005bBAN\u0001\u0011E\u0013Q\u0014\u0005\b\u0003S\u0003A\u0011KAV\u0011\u001d\t\u0019\f\u0001C!\u0003kCq!a.\u0001\r\u0003\tI\fC\u0004\u0002P\u00021\t!!5\t\u000f\u0005]\bA\"\u0001\u0002z\"9!\u0011\u0002\u0001\u0007B\t-\u0001\u0002\u0003B$\u0001\u0001&\tF!\u0013\t\u000f\tm\u0003\u0001\"\u0011\u0002\u0010\"9!Q\f\u0001\u0005B\t}\u0003b\u0002B=\u0001\u0011\u0005#1\u0010\u0005\b\u0005\u000f\u0003A\u0011\tBE\u0011\u001d\u0011Y\n\u0001C!\u0005;;\u0001B\"\u001d\u0002,!\u0005!\u0011\u0019\u0004\t\u0003S\tY\u0003#\u0001\u00032\"9\u0011QR\t\u0005\u0002\t}\u0006\"\u0003Bb#\t\u0007I\u0011AAJ\u0011!\u0011)-\u0005Q\u0001\n\u0005U\u0005\u0002\u0003Bd#\u0001\u0006IA!3\t\u000f\u0005M\u0016\u0003\"\u0001\u00048!91qI\t\u0005\u0002\r%\u0003bBB2#\u0011\u00051Q\r\u0005\b\u0007o\nB\u0011AB=\r\u0019\u0011i-\u0005\u0002\u0003P\"Q\u0011q\u001f\u000e\u0003\u0006\u0004%\tAa7\t\u0015\t}'D!A!\u0002\u0013\u0011i\u000eC\u0004\u0002\u000ej!\tA!9\t\u000f\u0005]&\u0004\"\u0001\u0003h\"9!1\u001e\u000e\u0005\u0002\t5\bb\u0002Bx5\u0011\u0005!\u0011\u001f\u0005\b\u0003\u001fTB\u0011\u0001B{\u0011\u001d\u0011YP\u0007C!\u0005{DqA!\u001f\u001b\t\u0003\u0012y\u0010C\u0004\u0004\u0006i!\tea\u0002\t\u000f\t%!\u0004\"\u0011\u0004\u0010\u001911\u0011R\t\u0003\u0007\u0017C!\"a>'\u0005\u000b\u0007I\u0011ABK\u0011)\u0011yN\nB\u0001B\u0003%1q\u0013\u0005\b\u0003\u001b3C\u0011ABM\u0011\u001d\t9L\nC\u0001\u0007?CqAa;'\t\u0003\u0011i\u000fC\u0004\u0003p\u001a\"\taa,\t\u000f\u0005=g\u0005\"\u0001\u00044\"9!1 \u0014\u0005B\tu\bb\u0002B=M\u0011\u00053\u0011\u0018\u0005\b\u0007\u000b1C\u0011IB_\u0011\u001d\u0011IA\nC!\u0007\u00034aaa8\u0012\u0005\r\u0005\bBCA|e\t\u0015\r\u0011\"\u0001\u0004l\"Q!q\u001c\u001a\u0003\u0002\u0003\u0006Ia!<\t\u000f\u00055%\u0007\"\u0001\u0004p\"9\u0011q\u0017\u001a\u0005\u0002\rU\bb\u0002Bve\u0011\u0005!Q\u001e\u0005\b\u0005_\u0014D\u0011AB\u007f\u0011\u001d\tyM\rC\u0001\t\u0003AqAa?3\t\u0003\u0012i\u0010C\u0004\u0003zI\"\t\u0005b\u0002\t\u000f\r\u0015!\u0007\"\u0011\u0005\f!9!\u0011\u0002\u001a\u0005B\u0011=aA\u0002C\u0017#\t!y\u0003\u0003\u0006\u0002xz\u0012)\u0019!C\u0001\tsA!Ba8?\u0005\u0003\u0005\u000b\u0011\u0002C\u001e\u0011\u001d\tiI\u0010C\u0001\t{Aq!a.?\t\u0003!\u0019\u0005C\u0004\u0003lz\"\tA!<\t\u000f\t=h\b\"\u0001\u0005L!9\u0011q\u001a \u0005\u0002\u0011=\u0003b\u0002B~}\u0011\u0005#Q \u0005\b\u0005srD\u0011\tC+\u0011\u001d\u0019)A\u0010C!\t3BqA!\u0003?\t\u0003\"i\u0006C\u0004\u0005zy\"\t\u0005b\u001f\u0007\r\u0011\r\u0016C\u0001CS\u0011)\t9p\u0013BC\u0002\u0013\u0005A\u0011\u0016\u0005\u000b\u0005?\\%\u0011!Q\u0001\n\u0011-\u0006bBAG\u0017\u0012\u0005AQ\u0016\u0005\b\u0003o[E\u0011\u0001CZ\u0011\u001d\u0011Yo\u0013C\u0001\u0005[DqAa<L\t\u0003!Y\fC\u0004\u0002P.#\t\u0001b0\t\u000f\tm8\n\"\u0011\u0003~\"9!\u0011P&\u0005B\u0011\u0015\u0007bBB\u0003\u0017\u0012\u0005C\u0011\u001a\u0005\b\u0005\u0013YE\u0011\tCg\r\u0019!Y/\u0005\u0002\u0005n\"Q\u0011q_,\u0003\u0006\u0004%\t\u0001b>\t\u0015\t}wK!A!\u0002\u0013!I\u0010C\u0004\u0002\u000e^#\t\u0001b?\t\u000f\u0005]v\u000b\"\u0001\u0006\u0002!9!1^,\u0005\u0002\t5\bb\u0002Bx/\u0012\u0005Q\u0011\u0002\u0005\b\u0003\u001f<F\u0011AC\u0007\u0011\u001d\u0011Yp\u0016C!\u0005{DqA!\u001fX\t\u0003*\u0019\u0002C\u0004\u0004\u0006]#\t%b\u0006\t\u000f\t%q\u000b\"\u0011\u0006\u001c\u00191Q\u0011H\t\u0003\u000bwA!\"a>d\u0005\u000b\u0007I\u0011AC#\u0011)\u0011yn\u0019B\u0001B\u0003%Qq\t\u0005\b\u0003\u001b\u001bG\u0011AC%\u0011\u001d\t9l\u0019C\u0001\u000b\u001fBqAa;d\t\u0003\u0011i\u000fC\u0004\u0003p\u000e$\t!b\u0016\t\u000f\u0005=7\r\"\u0001\u0006\\!9!1`2\u0005B\tu\bb\u0002B=G\u0012\u0005S\u0011\r\u0005\b\u0007\u000b\u0019G\u0011IC3\u0011\u001d\u0011Ia\u0019C!\u000bS2a!b\"\u0012\u0005\u0015%\u0005BCA|_\n\u0015\r\u0011\"\u0001\u0006\u0014\"Q!q\\8\u0003\u0002\u0003\u0006I!\"&\t\u000f\u00055u\u000e\"\u0001\u0006\u0018\"9\u0011qW8\u0005\u0002\u0015u\u0005b\u0002Bv_\u0012\u0005!Q\u001e\u0005\b\u0005_|G\u0011ACS\u0011\u001d\tym\u001cC\u0001\u000bSCqAa?p\t\u0003\u0012i\u0010C\u0004\u0003z=$\t%b,\t\u000f\r\u0015q\u000e\"\u0011\u00064\"9!\u0011B8\u0005B\u0015]fA\u0002BX#\t1I\u0003\u0003\u0006\u0002xn\u0014)\u0019!C\u0001\r[A!Ba8|\u0005\u0003\u0005\u000b\u0011\u0002D\u0018\u0011\u001d\tii\u001fC\u0001\rcAq!a.|\t\u000319\u0004C\u0004\u0003ln$\tA!<\t\u000f\t=8\u0010\"\u0001\u0007@!9\u0011qZ>\u0005\u0002\u0019\r\u0003b\u0002B~w\u0012\u0005#Q \u0005\b\u0005sZH\u0011\tD%\u0011\u001d\u0019)a\u001fC!\r\u001bBqA!\u0003|\t\u00032\tF\u0002\u0004\u0006VF\u0011Qq\u001b\u0005\f\u0003o\fyA!b\u0001\n\u0003)Y\u000eC\u0006\u0003`\u0006=!\u0011!Q\u0001\n\u0015u\u0007\u0002CAG\u0003\u001f!\t!b8\t\u0011\u0005]\u0016q\u0002C\u0001\u000bKD\u0001Ba;\u0002\u0010\u0011\u0005!Q\u001e\u0005\t\u0005_\fy\u0001\"\u0001\u0006n\"A\u0011qZA\b\t\u0003)\t\u0010\u0003\u0005\u0003|\u0006=A\u0011\tB\u007f\u0011!\u0011I(a\u0004\u0005B\u0015]\b\u0002CB\u0003\u0003\u001f!\t%b?\t\u0011\t%\u0011q\u0002C!\u000b\u007fD\u0011B\"\b\u0012\u0003\u0003%IAb\b\u0003\u0011\u0005\u0013(/Y=TKFTA!!\f\u00020\u00059Q.\u001e;bE2,'\u0002BA\u0019\u0003g\t!bY8mY\u0016\u001cG/[8o\u0015\t\t)$A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\t\u0005m\u0012\u0011J\n\f\u0001\u0005u\u0012QLA2\u0003[\n)\b\u0005\u0004\u0002@\u0005\u0005\u0013QI\u0007\u0003\u0003WIA!a\u0011\u0002,\tY\u0011IY:ue\u0006\u001cGoU3r!\u0011\t9%!\u0013\r\u0001\u00119\u00111\n\u0001C\u0002\u00055#!\u0001+\u0012\t\u0005=\u0013q\u000b\t\u0005\u0003#\n\u0019&\u0004\u0002\u00024%!\u0011QKA\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!!\u0015\u0002Z%!\u00111LA\u001a\u0005\r\te.\u001f\t\u0007\u0003\u007f\ty&!\u0012\n\t\u0005\u0005\u00141\u0006\u0002\u000b\u0013:$W\r_3e'\u0016\f\bCCA \u0003K\n)%!\u001b\u0002l%!\u0011qMA\u0016\u00055Ie\u000eZ3yK\u0012\u001cV-](qgB\u0019\u0011q\b\u0001\u0011\u000b\u0005}\u0002!!\u0012\u0011\u0015\u0005=\u0014\u0011OA#\u0003S\nY'\u0004\u0002\u00020%!\u00111OA\u0018\u0005U\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9PaN\u0004B!a\u001e\u0002\b:!\u0011\u0011PAB\u001d\u0011\tY(!!\u000e\u0005\u0005u$\u0002BA@\u0003o\ta\u0001\u0010:p_Rt\u0014BAA\u001b\u0013\u0011\t))a\r\u0002\u000fA\f7m[1hK&!\u0011\u0011RAF\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\u0011\t))a\r\u0002\rqJg.\u001b;?)\t\tY'A\bji\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z+\t\t)\n\u0005\u0004\u0002p\u0005]\u0015\u0011N\u0005\u0005\u00033\u000byC\u0001\u0006TKF4\u0015m\u0019;pef\fAB\u001a:p[N\u0003XmY5gS\u000e$B!a\u001b\u0002 \"9\u0011\u0011U\u0002A\u0002\u0005\r\u0016\u0001B2pY2\u0004b!a\u001c\u0002&\u0006\u0015\u0013\u0002BAT\u0003_\u0011A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!C\\3x'B,7-\u001b4jG\n+\u0018\u000e\u001c3feV\u0011\u0011Q\u0016\t\t\u0003\u007f\ty+!\u0012\u0002l%!\u0011\u0011WA\u0016\u0005\u001d\u0011U/\u001b7eKJ\fQ!Z7qif,\"!a\u001b\u0002\u000f\u0015dW-\u001c+bOV\u0011\u00111\u0018\u0019\u0005\u0003{\u000bY\r\u0005\u0004\u0002@\u0006\u0015\u0017\u0011Z\u0007\u0003\u0003\u0003TA!a1\u00024\u00059!/\u001a4mK\u000e$\u0018\u0002BAd\u0003\u0003\u0014\u0001b\u00117bgN$\u0016m\u001a\t\u0005\u0003\u000f\nY\rB\u0006\u0002N\u001a\t\t\u0011!A\u0003\u0002\u00055#aA0%c\u00051Q\u000f\u001d3bi\u0016$b!a5\u0002Z\u0006M\b\u0003BA)\u0003+LA!a6\u00024\t!QK\\5u\u0011\u001d\tYn\u0002a\u0001\u0003;\fQ!\u001b8eKb\u0004B!!\u0015\u0002`&!\u0011\u0011]A\u001a\u0005\rIe\u000e\u001e\u0015\t\u00033\f)/a;\u0002pB!\u0011\u0011KAt\u0013\u0011\tI/a\r\u0003\u001d\u0011,\u0007O]3dCR,GMT1nK\u0006\u0012\u0011Q^\u0001\u0004S\u0012D\u0018EAAy\u0003\u0019\u0011d&M\u001a/a!9\u0011Q_\u0004A\u0002\u0005\u0015\u0013\u0001B3mK6\fQ!\u0019:sCf,\"!a?1\t\u0005u(Q\u0001\t\u0007\u0003#\nyPa\u0001\n\t\t\u0005\u00111\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0003\u000f\u0012)\u0001B\u0006\u0003\b!\t\t\u0011!A\u0003\u0002\u00055#aA0%e\u000591\u000f^3qa\u0016\u0014X\u0003\u0002B\u0007\u0005/!BAa\u0004\u0003>I1!\u0011\u0003B\u000b\u0005W1aAa\u0005\u0001\u0001\t=!\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004\u0003BA$\u0005/!qA!\u0007\n\u0005\u0004\u0011YBA\u0001T#\u0011\tyE!\b1\t\t}!q\u0005\t\u0007\u0003_\u0012\tC!\n\n\t\t\r\u0012q\u0006\u0002\b'R,\u0007\u000f]3s!\u0011\t9Ea\n\u0005\u0019\t%\"qCA\u0001\u0002\u0003\u0015\t!!\u0014\u0003\u0007}#3\u0007\u0005\u0003\u0003.\t]b\u0002\u0002B\u0018\u0005gqA!!\u001f\u00032%!\u0011\u0011GA\u001a\u0013\u0011\u0011)$a\f\u0002\u000fM#X\r\u001d9fe&!!\u0011\bB\u001e\u00059)eMZ5dS\u0016tGo\u00159mSRTAA!\u000e\u00020!9!qH\u0005A\u0004\t\u0005\u0013!B:iCB,\u0007\u0003CA8\u0005\u0007\n)E!\u0006\n\t\t\u0015\u0013q\u0006\u0002\r'R,\u0007\u000f]3s'\"\f\u0007/Z\u0001\nG2\f7o\u001d(b[\u0016,\"Aa\u0013\u0011\t\t5#qK\u0007\u0003\u0005\u001fRAA!\u0015\u0003T\u0005!A.\u00198h\u0015\t\u0011)&\u0001\u0003kCZ\f\u0017\u0002\u0002B-\u0005\u001f\u0012aa\u0015;sS:<\u0017!B2m_:,\u0017aC2paf$v.\u0011:sCf,BA!\u0019\u0003lQA\u0011Q\u001cB2\u0005c\u0012)\bC\u0004\u0003f1\u0001\rAa\u001a\u0002\u0005a\u001c\bCBA)\u0003\u007f\u0014I\u0007\u0005\u0003\u0002H\t-Da\u0002B7\u0019\t\u0007!q\u000e\u0002\u0002\u0005F!\u0011QIA,\u0011\u001d\u0011\u0019\b\u0004a\u0001\u0003;\fQa\u001d;beRDqAa\u001e\r\u0001\u0004\ti.A\u0002mK:\fa!Z9vC2\u001cH\u0003\u0002B?\u0005\u0007\u0003B!!\u0015\u0003\u0000%!!\u0011QA\u001a\u0005\u001d\u0011un\u001c7fC:DqA!\"\u000e\u0001\u0004\t9&A\u0003pi\",'/\u0001\u0004t_J$X\rZ\u000b\u0005\u0005\u0017\u0013I\n\u0006\u0003\u0002l\t5\u0005b\u0002BH\u001d\u0001\u000f!\u0011S\u0001\u0004_J$\u0007CBA<\u0005'\u00139*\u0003\u0003\u0003\u0016\u0006-%\u0001C(sI\u0016\u0014\u0018N\\4\u0011\t\u0005\u001d#\u0011\u0014\u0003\b\u0005[r!\u0019\u0001B8\u0003-\u0019xN\u001d;J]Bc\u0017mY3\u0016\t\t}%1\u0016\u000b\u0003\u0005C#BAa)\u0003&6\t\u0001\u0001C\u0004\u0003\u0010>\u0001\u001dAa*\u0011\r\u0005]$1\u0013BU!\u0011\t9Ea+\u0005\u000f\t5tB1\u0001\u0003p%b\u0001a\u001f\u0014?_\u000e\\uK\u0007\u001a\u0002\u0010\tIqN\u001a\"p_2,\u0017M\\\n\u0006#\tM&\u0011\u0018\t\u0005\u0003#\u0012),\u0003\u0003\u00038\u0006M\"AB!osJ+g\r\u0005\u0004\u0002p\tm\u0016\u0011N\u0005\u0005\u0005{\u000byCA\u0011TiJL7\r^(qi&l\u0017N_3e\u00072\f7o\u001d+bON+\u0017OR1di>\u0014\u0018\u0010\u0006\u0002\u0003BB\u0019\u0011qH\t\u0002\u0011UtG/Y4hK\u0012\f\u0011\"\u001e8uC\u001e<W\r\u001a\u0011\u0002\u001b\u0015k\u0007\u000f^=BeJ\f\u0017pU3r!\u0015\u0011YM\u0007BZ\u001b\u0005\t\"!B8g%\u00164W\u0003\u0002Bi\u0005/\u001c2A\u0007Bj!\u0015\ty\u0004\u0001Bk!\u0011\t9Ea6\u0005\u000f\u0005-#D1\u0001\u0003ZF!\u0011q\nBZ+\t\u0011i\u000e\u0005\u0004\u0002R\u0005}(Q[\u0001\u0007CJ\u0014\u0018-\u001f\u0011\u0015\t\t\r(Q\u001d\t\u0006\u0005\u0017T\"Q\u001b\u0005\b\u0003ol\u0002\u0019\u0001Bo+\t\u0011I\u000f\u0005\u0004\u0002@\u0006\u0015'Q[\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0005\u0005u\u0017!B1qa2LH\u0003\u0002Bk\u0005gDq!a7!\u0001\u0004\ti\u000e\u0006\u0004\u0002T\n](\u0011 \u0005\b\u00037\f\u0003\u0019AAo\u0011\u001d\t)0\ta\u0001\u0005+\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003;$BA! \u0004\u0002!911A\u0012A\u0002\u0005]\u0013\u0001\u0002;iCR\f\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0007\u0013\u0001b!a\u001c\u0004\f\tU\u0017\u0002BB\u0007\u0003_\u0011\u0001\"\u0013;fe\u0006$xN]\u000b\u0005\u0007#\u0019I\u0002\u0006\u0003\u0004\u0014\r\u001d\"CBB\u000b\u0007/\u0011YC\u0002\u0004\u0003\u0014i\u000111\u0003\t\u0005\u0003\u000f\u001aI\u0002B\u0004\u0003\u001a\u0015\u0012\raa\u0007\u0012\t\u0005=3Q\u0004\u0019\u0005\u0007?\u0019\u0019\u0003\u0005\u0004\u0002p\t\u00052\u0011\u0005\t\u0005\u0003\u000f\u001a\u0019\u0003\u0002\u0007\u0004&\re\u0011\u0011!A\u0001\u0006\u0003\tiEA\u0002`IQBqAa\u0010&\u0001\b\u0019I\u0003\u0005\u0005\u0002p\t\r#Q[B\fQ\u001dQ2QFB\u001a\u0007k\u0001B!!\u0015\u00040%!1\u0011GA\u001a\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004+\u0011\u0019Ida\u0010\u0015\t\rm2\u0011\t\t\u0006\u0003\u007f\u00011Q\b\t\u0005\u0003\u000f\u001ay\u0004B\u0004\u0002LY\u0011\r!!\u0014\t\u0013\r\rc#!AA\u0004\r\u0015\u0013AC3wS\u0012,gnY3%cA1\u0011qXAc\u0007{\tAA\u001a:p[V!11JB*)\u0011\u0019ie!\u0018\u0015\t\r=3q\u000b\t\u0006\u0003\u007f\u00011\u0011\u000b\t\u0005\u0003\u000f\u001a\u0019\u0006B\u0004\u0004V]\u0011\r!!\u0014\u0003\u0003\u0005C\u0011b!\u0017\u0018\u0003\u0003\u0005\u001daa\u0017\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u0002@\u0006\u00157\u0011\u000b\u0005\b\u0007?:\u0002\u0019AB1\u0003\tIG\u000f\u0005\u0004\u0002p\u0005\u00156\u0011K\u0001\u000b]\u0016<()^5mI\u0016\u0014X\u0003BB4\u0007[\"Ba!\u001b\u0004rAA\u0011qHAX\u0007W\u001ay\u0007\u0005\u0003\u0002H\r5DaBB+1\t\u0007\u0011Q\n\t\u0006\u0003\u007f\u000111\u000e\u0005\n\u0007gB\u0012\u0011!a\u0002\u0007k\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\ty,!2\u0004l\u0005!Q.Y6f+\u0011\u0019Yh!!\u0015\t\ru41\u0011\t\u0006\u0003\u007f\u00011q\u0010\t\u0005\u0003\u000f\u001a\t\tB\u0004\u0002Le\u0011\r!!\u0014\t\u000f\r\u0015\u0015\u00041\u0001\u0004\b\u0006\t\u0001\u0010\u0005\u0004\u0002R\u0005}8q\u0010\u0002\u0007_\u001a\u0014\u0015\u0010^3\u0014\u0007\u0019\u001ai\tE\u0003\u0002@\u0001\u0019y\t\u0005\u0003\u0002R\rE\u0015\u0002BBJ\u0003g\u0011AAQ=uKV\u00111q\u0013\t\u0007\u0003#\nypa$\u0015\t\rm5Q\u0014\t\u0004\u0005\u00174\u0003bBA|S\u0001\u00071qS\u000b\u0003\u0007CsAaa)\u0004*:!\u0011qXBS\u0013\u0011\u00199+!1\u0002\u0011\rc\u0017m]:UC\u001eLAaa+\u0004.\u0006!!)\u001f;f\u0015\u0011\u00199+!1\u0015\t\r=5\u0011\u0017\u0005\b\u00037d\u0003\u0019AAo)\u0019\t\u0019n!.\u00048\"9\u00111\\\u0017A\u0002\u0005u\u0007bBA{[\u0001\u00071q\u0012\u000b\u0005\u0005{\u001aY\fC\u0004\u0004\u0004=\u0002\r!a\u0016\u0016\u0005\r}\u0006CBA8\u0007\u0017\u0019y)\u0006\u0003\u0004D\u000e-G\u0003BBc\u00073\u0014baa2\u0004J\n-bA\u0002B\nM\u0001\u0019)\r\u0005\u0003\u0002H\r-Ga\u0002B\rc\t\u00071QZ\t\u0005\u0003\u001f\u001ay\r\r\u0003\u0004R\u000eU\u0007CBA8\u0005C\u0019\u0019\u000e\u0005\u0003\u0002H\rUG\u0001DBl\u0007\u0017\f\t\u0011!A\u0003\u0002\u00055#aA0%k!9!qH\u0019A\u0004\rm\u0007\u0003CA8\u0005\u0007\u001ayi!3)\u000f\u0019\u001aica\r\u00046\t9qNZ*i_J$8c\u0001\u001a\u0004dB)\u0011q\b\u0001\u0004fB!\u0011\u0011KBt\u0013\u0011\u0019I/a\r\u0003\u000bMCwN\u001d;\u0016\u0005\r5\bCBA)\u0003\u007f\u001c)\u000f\u0006\u0003\u0004r\u000eM\bc\u0001Bfe!9\u0011q_\u001bA\u0002\r5XCAB|\u001d\u0011\u0019\u0019k!?\n\t\rm8QV\u0001\u0006'\"|'\u000f\u001e\u000b\u0005\u0007K\u001cy\u0010C\u0004\u0002\\b\u0002\r!!8\u0015\r\u0005MG1\u0001C\u0003\u0011\u001d\tY.\u000fa\u0001\u0003;Dq!!>:\u0001\u0004\u0019)\u000f\u0006\u0003\u0003~\u0011%\u0001bBB\u0002w\u0001\u0007\u0011qK\u000b\u0003\t\u001b\u0001b!a\u001c\u0004\f\r\u0015X\u0003\u0002C\t\t3!B\u0001b\u0005\u0005(I1AQ\u0003C\f\u0005W1aAa\u00053\u0001\u0011M\u0001\u0003BA$\t3!qA!\u0007>\u0005\u0004!Y\"\u0005\u0003\u0002P\u0011u\u0001\u0007\u0002C\u0010\tG\u0001b!a\u001c\u0003\"\u0011\u0005\u0002\u0003BA$\tG!A\u0002\"\n\u0005\u001a\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00137\u0011\u001d\u0011y$\u0010a\u0002\tS\u0001\u0002\"a\u001c\u0003D\r\u0015Hq\u0003\u0015\be\r521GB\u001b\u0005\u0019ygm\u00115beN\u0019a\b\"\r\u0011\u000b\u0005}\u0002\u0001b\r\u0011\t\u0005ECQG\u0005\u0005\to\t\u0019D\u0001\u0003DQ\u0006\u0014XC\u0001C\u001e!\u0019\t\t&a@\u00054Q!Aq\bC!!\r\u0011YM\u0010\u0005\b\u0003o\f\u0005\u0019\u0001C\u001e+\t!)E\u0004\u0003\u0004$\u0012\u001d\u0013\u0002\u0002C%\u0007[\u000bAa\u00115beR!A1\u0007C'\u0011\u001d\tY\u000e\u0012a\u0001\u0003;$b!a5\u0005R\u0011M\u0003bBAn\u000b\u0002\u0007\u0011Q\u001c\u0005\b\u0003k,\u0005\u0019\u0001C\u001a)\u0011\u0011i\bb\u0016\t\u000f\r\rq\t1\u0001\u0002XU\u0011A1\f\t\u0007\u0003_\u001aY\u0001b\r\u0016\t\u0011}Cq\r\u000b\u0005\tC\")H\u0005\u0004\u0005d\u0011\u0015$1\u0006\u0004\u0007\u0005'q\u0004\u0001\"\u0019\u0011\t\u0005\u001dCq\r\u0003\b\u00053I%\u0019\u0001C5#\u0011\ty\u0005b\u001b1\t\u00115D\u0011\u000f\t\u0007\u0003_\u0012\t\u0003b\u001c\u0011\t\u0005\u001dC\u0011\u000f\u0003\r\tg\"9'!A\u0001\u0002\u000b\u0005\u0011Q\n\u0002\u0004?\u0012:\u0004b\u0002B \u0013\u0002\u000fAq\u000f\t\t\u0003_\u0012\u0019\u0005b\r\u0005f\u0005I\u0011\r\u001a3TiJLgn\u001a\u000b\u000b\t{\"y\b\"#\u0005\u001a\u0012ue\u0002BA$\t\u007fBq\u0001\"!K\u0001\u0004!\u0019)\u0001\u0002tEB!\u0011q\bCC\u0013\u0011!9)a\u000b\u0003\u001bM#(/\u001b8h\u0005VLG\u000eZ3s\u0011\u001d\u0011\u0019H\u0013a\u0001\t\u0017\u0003B\u0001\"$\u0005\u0016:!Aq\u0012CI!\u0011\tY(a\r\n\t\u0011M\u00151G\u0001\u0007!J,G-\u001a4\n\t\teCq\u0013\u0006\u0005\t'\u000b\u0019\u0004C\u0004\u0005\u001c*\u0003\r\u0001b#\u0002\u0007M,\u0007\u000fC\u0004\u0005 *\u0003\r\u0001b#\u0002\u0007\u0015tG\rK\u0004?\u0007[\u0019\u0019d!\u000e\u0003\u000b=4\u0017J\u001c;\u0014\u0007-#9\u000bE\u0003\u0002@\u0001\ti.\u0006\u0002\u0005,B1\u0011\u0011KA\u0000\u0003;$B\u0001b,\u00052B\u0019!1Z&\t\u000f\u0005]h\n1\u0001\u0005,V\u0011AQ\u0017\b\u0005\u0007G#9,\u0003\u0003\u0005:\u000e5\u0016aA%oiR!\u0011Q\u001cC_\u0011\u001d\tY.\u0015a\u0001\u0003;$b!a5\u0005B\u0012\r\u0007bBAn%\u0002\u0007\u0011Q\u001c\u0005\b\u0003k\u0014\u0006\u0019AAo)\u0011\u0011i\bb2\t\u000f\r\rA\u000b1\u0001\u0002XU\u0011A1\u001a\t\u0007\u0003_\u001aY!!8\u0016\t\u0011=Gq\u001b\u000b\u0005\t#$)O\u0005\u0004\u0005T\u0012U'1\u0006\u0004\u0007\u0005'Y\u0005\u0001\"5\u0011\t\u0005\u001dCq\u001b\u0003\b\u000531&\u0019\u0001Cm#\u0011\ty\u0005b71\t\u0011uG\u0011\u001d\t\u0007\u0003_\u0012\t\u0003b8\u0011\t\u0005\u001dC\u0011\u001d\u0003\r\tG$9.!A\u0001\u0002\u000b\u0005\u0011Q\n\u0002\u0004?\u0012B\u0004b\u0002B -\u0002\u000fAq\u001d\t\t\u0003_\u0012\u0019%!8\u0005V\":1j!\f\u00044\rU\"AB8g\u0019>twmE\u0002X\t_\u0004R!a\u0010\u0001\tc\u0004B!!\u0015\u0005t&!AQ_A\u001a\u0005\u0011auN\\4\u0016\u0005\u0011e\bCBA)\u0003\u007f$\t\u0010\u0006\u0003\u0005~\u0012}\bc\u0001Bf/\"9\u0011q\u001f.A\u0002\u0011eXCAC\u0002\u001d\u0011\u0019\u0019+\"\u0002\n\t\u0015\u001d1QV\u0001\u0005\u0019>tw\r\u0006\u0003\u0005r\u0016-\u0001bBAn;\u0002\u0007\u0011Q\u001c\u000b\u0007\u0003',y!\"\u0005\t\u000f\u0005mg\f1\u0001\u0002^\"9\u0011Q\u001f0A\u0002\u0011EH\u0003\u0002B?\u000b+Aqaa\u0001a\u0001\u0004\t9&\u0006\u0002\u0006\u001aA1\u0011qNB\u0006\tc,B!\"\b\u0006&Q!QqDC\u001a%\u0019)\t#b\t\u0003,\u00191!1C,\u0001\u000b?\u0001B!a\u0012\u0006&\u00119!\u0011\u00042C\u0002\u0015\u001d\u0012\u0003BA(\u000bS\u0001D!b\u000b\u00060A1\u0011q\u000eB\u0011\u000b[\u0001B!a\u0012\u00060\u0011aQ\u0011GC\u0013\u0003\u0003\u0005\tQ!\u0001\u0002N\t\u0019q\fJ\u001d\t\u000f\t}\"\rq\u0001\u00066AA\u0011q\u000eB\"\tc,\u0019\u0003K\u0004X\u0007[\u0019\u0019d!\u000e\u0003\u000f=4g\t\\8biN\u00191-\"\u0010\u0011\u000b\u0005}\u0002!b\u0010\u0011\t\u0005ES\u0011I\u0005\u0005\u000b\u0007\n\u0019DA\u0003GY>\fG/\u0006\u0002\u0006HA1\u0011\u0011KA\u0000\u000b\u007f!B!b\u0013\u0006NA\u0019!1Z2\t\u000f\u0005]h\r1\u0001\u0006HU\u0011Q\u0011\u000b\b\u0005\u0007G+\u0019&\u0003\u0003\u0006V\r5\u0016!\u0002$m_\u0006$H\u0003BC \u000b3Bq!a7j\u0001\u0004\ti\u000e\u0006\u0004\u0002T\u0016uSq\f\u0005\b\u00037T\u0007\u0019AAo\u0011\u001d\t)P\u001ba\u0001\u000b\u007f!BA! \u0006d!911\u00017A\u0002\u0005]SCAC4!\u0019\tyga\u0003\u0006@U!Q1NC:)\u0011)i'\"!\u0013\r\u0015=T\u0011\u000fB\u0016\r\u0019\u0011\u0019b\u0019\u0001\u0006nA!\u0011qIC:\t\u001d\u0011IB\u001cb\u0001\u000bk\nB!a\u0014\u0006xA\"Q\u0011PC?!\u0019\tyG!\t\u0006|A!\u0011qIC?\t1)y(b\u001d\u0002\u0002\u0003\u0005)\u0011AA'\u0005\u0011yF%\r\u0019\t\u000f\t}b\u000eq\u0001\u0006\u0004BA\u0011q\u000eB\"\u000b\u007f)\t\bK\u0004d\u0007[\u0019\u0019d!\u000e\u0003\u0011=4Gi\\;cY\u0016\u001c2a\\CF!\u0015\ty\u0004ACG!\u0011\t\t&b$\n\t\u0015E\u00151\u0007\u0002\u0007\t>,(\r\\3\u0016\u0005\u0015U\u0005CBA)\u0003\u007f,i\t\u0006\u0003\u0006\u001a\u0016m\u0005c\u0001Bf_\"9\u0011q\u001f:A\u0002\u0015UUCACP\u001d\u0011\u0019\u0019+\")\n\t\u0015\r6QV\u0001\u0007\t>,(\r\\3\u0015\t\u00155Uq\u0015\u0005\b\u00037,\b\u0019AAo)\u0019\t\u0019.b+\u0006.\"9\u00111\u001c<A\u0002\u0005u\u0007bBA{m\u0002\u0007QQ\u0012\u000b\u0005\u0005{*\t\fC\u0004\u0004\u0004a\u0004\r!a\u0016\u0016\u0005\u0015U\u0006CBA8\u0007\u0017)i)\u0006\u0003\u0006:\u0016\u0005G\u0003BC^\u000b\u001f\u0014b!\"0\u0006@\n-bA\u0002B\n_\u0002)Y\f\u0005\u0003\u0002H\u0015\u0005Ga\u0002B\ru\n\u0007Q1Y\t\u0005\u0003\u001f*)\r\r\u0003\u0006H\u0016-\u0007CBA8\u0005C)I\r\u0005\u0003\u0002H\u0015-G\u0001DCg\u000b\u0003\f\t\u0011!A\u0003\u0002\u00055#\u0001B0%cEBqAa\u0010{\u0001\b)\t\u000e\u0005\u0005\u0002p\t\rSQRC`Q\u001dy7QFB\u001a\u0007k\u0011aa\u001c4V]&$8\u0003BA\b\u000b3\u0004R!a\u0010\u0001\u0003',\"!\"8\u0011\r\u0005E\u0013q`Aj)\u0011)\t/b9\u0011\t\t-\u0017q\u0002\u0005\t\u0003o\f)\u00021\u0001\u0006^V\u0011Qq\u001d\b\u0005\u0007G+I/\u0003\u0003\u0006l\u000e5\u0016\u0001B+oSR$B!a5\u0006p\"A\u00111\\A\u000e\u0001\u0004\ti\u000e\u0006\u0004\u0002T\u0016MXQ\u001f\u0005\t\u00037\fi\u00021\u0001\u0002^\"A\u0011Q_A\u000f\u0001\u0004\t\u0019\u000e\u0006\u0003\u0003~\u0015e\b\u0002CB\u0002\u0003C\u0001\r!a\u0016\u0016\u0005\u0015u\bCBA8\u0007\u0017\t\u0019.\u0006\u0003\u0007\u0002\u0019%A\u0003\u0002D\u0002\r/\u0011bA\"\u0002\u0007\b\t-ba\u0002B\n\u0003\u001f\u0001a1\u0001\t\u0005\u0003\u000f2I\u0001\u0002\u0005\u0003\u001a\u0005\u0015\"\u0019\u0001D\u0006#\u0011\tyE\"\u00041\t\u0019=a1\u0003\t\u0007\u0003_\u0012\tC\"\u0005\u0011\t\u0005\u001dc1\u0003\u0003\r\r+1I!!A\u0001\u0002\u000b\u0005\u0011Q\n\u0002\u0005?\u0012\n4\u0007\u0003\u0005\u0003@\u0005\u0015\u00029\u0001D\r!!\tyGa\u0011\u0002T\u001a\u001d\u0001\u0006CA\b\u0007[\u0019\u0019d!\u000e\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0019\u0005\u0002\u0003\u0002B'\rGIAA\"\n\u0003P\t1qJ\u00196fGRDs!EB\u0017\u0007g\u0019)dE\u0002|\rW\u0001R!a\u0010\u0001\u0005{*\"Ab\f\u0011\r\u0005E\u0013q B?)\u00111\u0019D\"\u000e\u0011\u0007\t-7\u0010C\u0004\u0002xz\u0004\rAb\f\u0016\u0005\u0019eb\u0002BBR\rwIAA\"\u0010\u0004.\u00069!i\\8mK\u0006tG\u0003\u0002B?\r\u0003B\u0001\"a7\u0002\u0004\u0001\u0007\u0011Q\u001c\u000b\u0007\u0003'4)Eb\u0012\t\u0011\u0005m\u0017Q\u0001a\u0001\u0003;D\u0001\"!>\u0002\u0006\u0001\u0007!Q\u0010\u000b\u0005\u0005{2Y\u0005\u0003\u0005\u0004\u0004\u0005%\u0001\u0019AA,+\t1y\u0005\u0005\u0004\u0002p\r-!QP\u000b\u0005\r'2Y\u0006\u0006\u0003\u0007V\u0019%$C\u0002D,\r3\u0012YC\u0002\u0004\u0003\u0014m\u0004aQ\u000b\t\u0005\u0003\u000f2Y\u0006\u0002\u0005\u0003\u001a\u00055!\u0019\u0001D/#\u0011\tyEb\u00181\t\u0019\u0005dQ\r\t\u0007\u0003_\u0012\tCb\u0019\u0011\t\u0005\u001dcQ\r\u0003\r\rO2Y&!A\u0001\u0002\u000b\u0005\u0011Q\n\u0002\u0005?\u0012\n$\u0007\u0003\u0005\u0003@\u00055\u00019\u0001D6!!\tyGa\u0011\u0003~\u0019e\u0003fB>\u0004.\rM2Q\u0007\u0015\b\u0001\r521GB\u001b\u0003!\t%O]1z'\u0016\f\bf\u0002\t\u0004.\rM2Q\u0007"
)
public abstract class ArraySeq extends AbstractSeq implements IndexedSeq, StrictOptimizedSeqOps, Serializable {
   private static final long serialVersionUID = 3L;

   public static ArraySeq make(final Object x) {
      return ArraySeq$.MODULE$.make(x);
   }

   public static Builder newBuilder(final ClassTag evidence$3) {
      return ArraySeq$.MODULE$.newBuilder(evidence$3);
   }

   public static ArraySeq from(final IterableOnce it, final ClassTag evidence$2) {
      return ArraySeq$.MODULE$.from(it, evidence$2);
   }

   public static SeqFactory untagged() {
      return ArraySeq$.MODULE$.untagged();
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f, final ClassTag evidence$35) {
      Builder tabulate_b = ArraySeq$.MODULE$.newBuilder(evidence$35);
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem, final ClassTag evidence$34) {
      Builder fill_b = ArraySeq$.MODULE$.newBuilder(evidence$34);
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      ArraySeq$ var10000 = ArraySeq$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      ArraySeq$ tabulate_this = ArraySeq$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
            Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
               Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = tabulate_this.newBuilder(evidence$33);
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
                  Object var40 = null;
                  Object var42 = null;
                  Object tabulate_$plus$eq_elem = var10000;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var43 = (scala.collection.SeqOps)tabulate_b.result();
               Object var37 = null;
               Object var39 = null;
               tabulate_evidence$9 = null;
               Object tabulate_$plus$eq_elem = var43;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var44 = (scala.collection.SeqOps)tabulate_b.result();
            Object var33 = null;
            Object var35 = null;
            tabulate_evidence$9 = null;
            Object tabulate_$plus$eq_elem = var44;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var45 = (scala.collection.SeqOps)tabulate_b.result();
         Object var29 = null;
         Object var31 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var45;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      ArraySeq$ tabulate_this = ArraySeq$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
            Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = tabulate_this.newBuilder(evidence$32);
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
               Object var31 = null;
               Object var33 = null;
               Object tabulate_$plus$eq_elem = var10000;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var34 = (scala.collection.SeqOps)tabulate_b.result();
            Object var28 = null;
            Object var30 = null;
            tabulate_evidence$9 = null;
            Object tabulate_$plus$eq_elem = var34;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
         Object var24 = null;
         Object var26 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var35;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      ArraySeq$ tabulate_this = ArraySeq$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = tabulate_this.newBuilder(evidence$31);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
            Object var22 = null;
            Object var24 = null;
            Object tabulate_$plus$eq_elem = var10000;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var25 = (scala.collection.SeqOps)tabulate_b.result();
         Object var19 = null;
         Object var21 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var25;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      ArraySeq$ tabulate_this = ArraySeq$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = tabulate_this.newBuilder((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = tabulate_this.newBuilder(evidence$30);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var10000 = (scala.collection.SeqOps)tabulate_b.result();
         Object var13 = null;
         Object var15 = null;
         Object tabulate_$plus$eq_elem = var10000;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      ArraySeq$ fill_this = ArraySeq$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
            Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
               Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = fill_this.newBuilder(evidence$29);
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
                  Object var40 = null;
                  Object var42 = null;
                  Object fill_$plus$eq_elem = var10000;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var43 = (scala.collection.SeqOps)fill_b.result();
               Object var37 = null;
               Object var39 = null;
               fill_evidence$8 = null;
               Object fill_$plus$eq_elem = var43;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var44 = (scala.collection.SeqOps)fill_b.result();
            Object var33 = null;
            Object var35 = null;
            fill_evidence$8 = null;
            Object fill_$plus$eq_elem = var44;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var45 = (scala.collection.SeqOps)fill_b.result();
         Object var29 = null;
         Object var31 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var45;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      ArraySeq$ fill_this = ArraySeq$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
            Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = fill_this.newBuilder(evidence$28);
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
               Object var31 = null;
               Object var33 = null;
               Object fill_$plus$eq_elem = var10000;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var34 = (scala.collection.SeqOps)fill_b.result();
            Object var28 = null;
            Object var30 = null;
            fill_evidence$8 = null;
            Object fill_$plus$eq_elem = var34;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
         Object var24 = null;
         Object var26 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var35;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      ArraySeq$ fill_this = ArraySeq$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = fill_this.newBuilder(evidence$27);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
            Object var22 = null;
            Object var24 = null;
            Object fill_$plus$eq_elem = var10000;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var25 = (scala.collection.SeqOps)fill_b.result();
         Object var19 = null;
         Object var21 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var25;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      ArraySeq$ fill_this = ArraySeq$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = fill_this.newBuilder((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = fill_this.newBuilder(evidence$26);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var10000 = (scala.collection.SeqOps)fill_b.result();
         Object var13 = null;
         Object var15 = null;
         Object fill_$plus$eq_elem = var10000;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      return ClassTagIterableFactory.range$(ArraySeq$.MODULE$, start, end, step, evidence$24, evidence$25);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      return ClassTagIterableFactory.range$(ArraySeq$.MODULE$, start, end, evidence$22, evidence$23);
   }

   public static Factory evidenceIterableFactory(final Object evidence$13) {
      return EvidenceIterableFactory.evidenceIterableFactory$(ArraySeq$.MODULE$, evidence$13);
   }

   public static Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      ArraySeq$ unfold_this = ArraySeq$.MODULE$;
      IterableOnce from_it = new View.Unfold(init, f);
      return unfold_this.from(from_it, (ClassTag)evidence$11);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      ArraySeq$ iterate_this = ArraySeq$.MODULE$;
      IterableOnce from_it = new View.Iterate(start, len, f);
      return iterate_this.from(from_it, (ClassTag)evidence$10);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object prepended(final Object elem) {
      return StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.intersect$(this, that);
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

   public IndexedSeqOps mapInPlace(final Function1 f) {
      return IndexedSeqOps.mapInPlace$(this, f);
   }

   public IndexedSeqOps sortInPlaceWith(final Function2 lt) {
      return IndexedSeqOps.sortInPlaceWith$(this, lt);
   }

   public IndexedSeqOps sortInPlaceBy(final Function1 f, final Ordering ord) {
      return IndexedSeqOps.sortInPlaceBy$(this, f, ord);
   }

   public String stringPrefix() {
      return scala.collection.IndexedSeq.stringPrefix$(this);
   }

   public Iterator iterator() {
      return scala.collection.IndexedSeqOps.iterator$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object reverse() {
      return scala.collection.IndexedSeqOps.reverse$(this);
   }

   public Object slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public Object head() {
      return scala.collection.IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return scala.collection.IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public SeqFactory iterableFactory() {
      return ArraySeq$.MODULE$.untagged();
   }

   public ArraySeq fromSpecific(final IterableOnce coll) {
      Object var17;
      label113: {
         label115: {
            ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = this.elemTag();
            Class var4 = make_evidence$1.runtimeClass();
            Class var8 = Byte.TYPE;
            if (var8 == null) {
               if (var4 == null) {
                  break label115;
               }
            } else if (var8.equals(var4)) {
               break label115;
            }

            label116: {
               var8 = Short.TYPE;
               if (var8 == null) {
                  if (var4 == null) {
                     break label116;
                  }
               } else if (var8.equals(var4)) {
                  break label116;
               }

               label117: {
                  var8 = Character.TYPE;
                  if (var8 == null) {
                     if (var4 == null) {
                        break label117;
                     }
                  } else if (var8.equals(var4)) {
                     break label117;
                  }

                  label118: {
                     var8 = Integer.TYPE;
                     if (var8 == null) {
                        if (var4 == null) {
                           break label118;
                        }
                     } else if (var8.equals(var4)) {
                        break label118;
                     }

                     label119: {
                        var8 = Long.TYPE;
                        if (var8 == null) {
                           if (var4 == null) {
                              break label119;
                           }
                        } else if (var8.equals(var4)) {
                           break label119;
                        }

                        label120: {
                           var8 = Float.TYPE;
                           if (var8 == null) {
                              if (var4 == null) {
                                 break label120;
                              }
                           } else if (var8.equals(var4)) {
                              break label120;
                           }

                           label121: {
                              var8 = Double.TYPE;
                              if (var8 == null) {
                                 if (var4 == null) {
                                    break label121;
                                 }
                              } else if (var8.equals(var4)) {
                                 break label121;
                              }

                              label122: {
                                 var8 = Boolean.TYPE;
                                 if (var8 == null) {
                                    if (var4 == null) {
                                       break label122;
                                    }
                                 } else if (var8.equals(var4)) {
                                    break label122;
                                 }

                                 label56: {
                                    var8 = Void.TYPE;
                                    if (var8 == null) {
                                       if (var4 == null) {
                                          break label56;
                                       }
                                    } else if (var8.equals(var4)) {
                                       break label56;
                                    }

                                    var17 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label113;
                                 }

                                 var17 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var17 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var17 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var17 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var17 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var17 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var17 = new ArrayBuilder.ofChar();
               break label113;
            }

            var17 = new ArrayBuilder.ofShort();
            break label113;
         }

         var17 = new ArrayBuilder.ofByte();
      }

      Object var6 = null;
      Object var7 = null;
      ArrayBuilder b = (ArrayBuilder)var17;
      int sizeHint_delta = 0;
      Builder.sizeHint$(b, coll, sizeHint_delta);
      b.addAll(coll);
      return ArraySeq$.MODULE$.make(b.result());
   }

   public Builder newSpecificBuilder() {
      return ArraySeq$.MODULE$.newBuilder(this.elemTag());
   }

   public ArraySeq empty() {
      return ArraySeq$.MODULE$.empty(this.elemTag());
   }

   public abstract ClassTag elemTag();

   public abstract void update(final int index, final Object elem);

   public abstract Object array();

   public abstract Stepper stepper(final StepperShape shape);

   public String className() {
      return "ArraySeq";
   }

   public ArraySeq clone() {
      return ArraySeq$.MODULE$.make(ScalaRunTime$.MODULE$.array_clone(this.array()));
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var7 = this.length();
      int elemsToCopyToArray_destLen = Array.getLength(xs);
      int elemsToCopyToArray_srcLen = var7;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
      if (copied > 0) {
         Array$.MODULE$.copy(this.array(), 0, xs, start, copied);
      }

      return copied;
   }

   public boolean equals(final Object other) {
      if (other instanceof ArraySeq) {
         ArraySeq var2 = (ArraySeq)other;
         if (Array.getLength(this.array()) != Array.getLength(var2.array())) {
            return false;
         }
      }

      return scala.collection.Seq.equals$(this, other);
   }

   public ArraySeq sorted(final Ordering ord) {
      return ArraySeq$.MODULE$.make(ArrayOps$.MODULE$.sorted$extension(this.array(), ord));
   }

   public ArraySeq sortInPlace(final Ordering ord) {
      if (this.length() > 1) {
         Sorting$ var10000 = Sorting$.MODULE$;
         Object stableSort_a = this.array();
         var10000.stableSort(stableSort_a, 0, Array.getLength(stableSort_a), ord);
      }

      return this;
   }

   public static final class ofRef extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final Object[] array;

      public Object[] array() {
         return this.array;
      }

      public ClassTag elemTag() {
         return ClassTag$.MODULE$.apply(this.array().getClass().getComponentType());
      }

      public int length() {
         return this.array().length;
      }

      public Object apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final Object elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofRef) {
            ofRef var2 = (ofRef)that;
            return Array$.MODULE$.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps.ArrayIterator(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         return (Stepper)(shape.shape() == StepperShape$.MODULE$.ReferenceShape() ? new ObjectArrayStepper(this.array(), 0, this.array().length) : shape.parUnbox(new ObjectArrayStepper(this.array(), 0, this.array().length)));
      }

      public ofRef(final Object[] array) {
         this.array = array;
      }
   }

   public static final class ofByte extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final byte[] array;

      public byte[] array() {
         return this.array;
      }

      public ManifestFactory.ByteManifest elemTag() {
         return ClassTag$.MODULE$.Byte();
      }

      public int length() {
         return this.array().length;
      }

      public byte apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final byte elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mBc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofByte) {
            ofByte var2 = (ofByte)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcB$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            IntStepper ofParIntStepper_st = new WidenedByteArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParIntStepper_st) {
            };
            ofParIntStepper_st = null;
         } else {
            var4 = new WidenedByteArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public ofByte(final byte[] array) {
         this.array = array;
      }
   }

   public static final class ofShort extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final short[] array;

      public short[] array() {
         return this.array;
      }

      public ManifestFactory.ShortManifest elemTag() {
         return ClassTag$.MODULE$.Short();
      }

      public int length() {
         return this.array().length;
      }

      public short apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final short elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mSc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofShort) {
            ofShort var2 = (ofShort)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcS$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            IntStepper ofParIntStepper_st = new WidenedShortArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParIntStepper_st) {
            };
            ofParIntStepper_st = null;
         } else {
            var4 = new WidenedShortArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public ofShort(final short[] array) {
         this.array = array;
      }
   }

   public static final class ofChar extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final char[] array;

      public char[] array() {
         return this.array;
      }

      public ManifestFactory.CharManifest elemTag() {
         return ClassTag$.MODULE$.Char();
      }

      public int length() {
         return this.array().length;
      }

      public char apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final char elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mCc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofChar) {
            ofChar var2 = (ofChar)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcC$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            IntStepper ofParIntStepper_st = new WidenedCharArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParIntStepper_st) {
            };
            ofParIntStepper_st = null;
         } else {
            var4 = new WidenedCharArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         java.lang.StringBuilder jsb = sb.underlying();
         if (start.length() != 0) {
            jsb.append(start);
         }

         int len = this.array().length;
         if (len != 0) {
            if (sep.isEmpty()) {
               jsb.append(this.array());
            } else {
               jsb.ensureCapacity(jsb.length() + len + end.length() + (len - 1) * sep.length());
               jsb.append(this.array()[0]);

               for(int i = 1; i < len; ++i) {
                  jsb.append(sep);
                  jsb.append(this.array()[i]);
               }
            }
         }

         if (end.length() != 0) {
            jsb.append(end);
         }

         return sb;
      }

      public ofChar(final char[] array) {
         this.array = array;
      }
   }

   public static final class ofInt extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final int[] array;

      public int[] array() {
         return this.array;
      }

      public ManifestFactory.IntManifest elemTag() {
         return ClassTag$.MODULE$.Int();
      }

      public int length() {
         return this.array().length;
      }

      public int apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final int elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mIc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofInt) {
            ofInt var2 = (ofInt)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcI$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            IntStepper ofParIntStepper_st = new IntArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParIntStepper_st) {
            };
            ofParIntStepper_st = null;
         } else {
            var4 = new IntArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public int apply$mcII$sp(final int index) {
         return this.array()[index];
      }

      public ofInt(final int[] array) {
         this.array = array;
      }
   }

   public static final class ofLong extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final long[] array;

      public long[] array() {
         return this.array;
      }

      public ManifestFactory.LongManifest elemTag() {
         return ClassTag$.MODULE$.Long();
      }

      public int length() {
         return this.array().length;
      }

      public long apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final long elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mJc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofLong) {
            ofLong var2 = (ofLong)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcJ$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            LongStepper ofParLongStepper_st = new LongArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParLongStepper_st) {
            };
            ofParLongStepper_st = null;
         } else {
            var4 = new LongArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public long apply$mcJI$sp(final int index) {
         return this.array()[index];
      }

      public ofLong(final long[] array) {
         this.array = array;
      }
   }

   public static final class ofFloat extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final float[] array;

      public float[] array() {
         return this.array;
      }

      public ManifestFactory.FloatManifest elemTag() {
         return ClassTag$.MODULE$.Float();
      }

      public int length() {
         return this.array().length;
      }

      public float apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final float elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mFc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofFloat) {
            ofFloat var2 = (ofFloat)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcF$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            DoubleStepper ofParDoubleStepper_st = new WidenedFloatArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParDoubleStepper_st) {
            };
            ofParDoubleStepper_st = null;
         } else {
            var4 = new WidenedFloatArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public float apply$mcFI$sp(final int index) {
         return this.array()[index];
      }

      public ofFloat(final float[] array) {
         this.array = array;
      }
   }

   public static final class ofDouble extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final double[] array;

      public double[] array() {
         return this.array;
      }

      public ManifestFactory.DoubleManifest elemTag() {
         return ClassTag$.MODULE$.Double();
      }

      public int length() {
         return this.array().length;
      }

      public double apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final double elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mDc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofDouble) {
            ofDouble var2 = (ofDouble)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcD$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         Object var4;
         if (shape.shape() == StepperShape$.MODULE$.ReferenceShape()) {
            AnyStepper$ var10000 = AnyStepper$.MODULE$;
            DoubleStepper ofParDoubleStepper_st = new DoubleArrayStepper(this.array(), 0, this.array().length);
            var4 = new Stepper.EfficientSplit(ofParDoubleStepper_st) {
            };
            ofParDoubleStepper_st = null;
         } else {
            var4 = new DoubleArrayStepper(this.array(), 0, this.array().length);
         }

         return (Stepper)var4;
      }

      public double apply$mcDI$sp(final int index) {
         return this.array()[index];
      }

      public ofDouble(final double[] array) {
         this.array = array;
      }
   }

   public static final class ofBoolean extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final boolean[] array;

      public boolean[] array() {
         return this.array;
      }

      public ManifestFactory.BooleanManifest elemTag() {
         return ClassTag$.MODULE$.Boolean();
      }

      public int length() {
         return this.array().length;
      }

      public boolean apply(final int index) {
         return this.array()[index];
      }

      public void update(final int index, final boolean elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mZc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofBoolean) {
            ofBoolean var2 = (ofBoolean)that;
            return Arrays.equals(this.array(), var2.array());
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcZ$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         return new BoxedBooleanArrayStepper(this.array(), 0, this.array().length);
      }

      public boolean apply$mcZI$sp(final int index) {
         return this.array()[index];
      }

      public ofBoolean(final boolean[] array) {
         this.array = array;
      }
   }

   public static final class ofUnit extends ArraySeq {
      private static final long serialVersionUID = 3L;
      private final BoxedUnit[] array;

      public BoxedUnit[] array() {
         return this.array;
      }

      public ManifestFactory.UnitManifest elemTag() {
         return ClassTag$.MODULE$.Unit();
      }

      public int length() {
         return this.array().length;
      }

      public void apply(final int index) {
         BoxedUnit var10000 = this.array()[index];
      }

      public void update(final int index, final BoxedUnit elem) {
         this.array()[index] = elem;
      }

      public int hashCode() {
         return MurmurHash3$.MODULE$.arraySeqHash$mVc$sp(this.array());
      }

      public boolean equals(final Object that) {
         if (that instanceof ofUnit) {
            ofUnit var2 = (ofUnit)that;
            return this.array().length == var2.array().length;
         } else {
            return super.equals(that);
         }
      }

      public Iterator iterator() {
         return new ArrayOps$ArrayIterator$mcV$sp(this.array());
      }

      public Stepper stepper(final StepperShape shape) {
         return new ObjectArrayStepper(this.array(), 0, this.array().length);
      }

      public void apply$mcVI$sp(final int index) {
         BoxedUnit var10000 = this.array()[index];
      }

      public ofUnit(final BoxedUnit[] array) {
         this.array = array;
      }
   }
}
