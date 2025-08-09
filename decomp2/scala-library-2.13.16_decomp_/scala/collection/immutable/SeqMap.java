package scala.collection.immutable;

import java.io.Serializable;
import java.util.NoSuchElementException;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.LazyZip2;
import scala.collection.MapFactory;
import scala.collection.MapFactoryDefaults;
import scala.collection.MapView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011=daB5k!\u0003\r\t!\u001d\u0005\b\u0003_\u0001A\u0011AA\u0019\u0011\u001d\tI\u0004\u0001C!\u0003w9q!a\u0011k\u0011\u0003\t)E\u0002\u0004jU\"\u0005\u0011q\t\u0005\b\u0003\u0013\"A\u0011AA&\u0011\u001d\ti\u0005\u0002C\u0001\u0003\u001fBq!!\u0018\u0005\t\u0003\ty\u0006C\u0004\u0002~\u0011!\t!a \b\u000f\u0005mE\u0001#\u0003\u0002\u001e\u001a9\u0011\u0011\u0015\u0003\t\n\u0005\r\u0006bBA%\u0015\u0011\u0005\u0011Q\u0017\u0005\b\u0003oSA\u0011IA]\u0011\u001d\t\tM\u0003C!\u0003sCq!a1\u000b\t\u0003\n)\rC\u0004\u0002L*!\t%!4\t\u000f\u0005]'\u0002\"\u0001\u0002Z\"9\u00111\u001d\u0006\u0005B\u0005\u0015\bbBA~\u0015\u0011\u0005\u0011Q \u0005\b\u0005\u000fQA\u0011\u0001B\u0005\u0011\u001d\u0011IB\u0003C\u0001\u00057A\u0011Ba\b\u000b\u0003\u0003%IA!\t\u0007\u000f\t}BA\u00016\u0003B!Q!q\n\f\u0003\u0002\u0003\u0006IAa\u0012\t\u0015\tEcC!A!\u0002\u0013\u0011Y\u0005C\u0004\u0002JY!\tAa\u0015\t\u000f\u0005]f\u0003\"\u0011\u0002:\"9\u0011\u0011\u0019\f\u0005B\u0005e\u0006bBAb-\u0011\u0005#1\f\u0005\b\u0003\u00174B\u0011\tB0\u0011\u001d\t9N\u0006C\u0001\u0005GBq!a9\u0017\t\u0003\u0012I\u0007C\u0004\u0002|Z!\tA!\u001f\t\u000f\t\u001da\u0003\"\u0001\u0003\u0000!9!\u0011\u0004\f\u0005\u0002\t5\u0005b\u0002BI-\u0011\u0005#1\u0013\u0005\b\u0005O3B\u0011\tBU\r\u001d\u0011Y\f\u0002\u0002k\u0005{C!Ba\u0014&\u0005\u0003\u0005\u000b\u0011\u0002Bb\u0011)\u0011\t&\nB\u0001B\u0003%!q\u0019\u0005\u000b\u0005\u0017,#\u0011!Q\u0001\n\t\r\u0007B\u0003BgK\t\u0005\t\u0015!\u0003\u0003H\"9\u0011\u0011J\u0013\u0005\u0002\t=\u0007bBA\\K\u0011\u0005\u0013\u0011\u0018\u0005\b\u0003\u0003,C\u0011IA]\u0011\u001d\t\u0019-\nC!\u00057Dq!a3&\t\u0003\u0012y\u000eC\u0004\u0002X\u0016\"\tAa9\t\u000f\u0005\rX\u0005\"\u0011\u0003j\"9\u00111`\u0013\u0005\u0002\te\bb\u0002B\u0004K\u0011\u0005!q \u0005\b\u00053)C\u0011AB\u0007\u0011\u001d\u0011\t*\nC!\u0007#AqAa*&\t\u0003\u001aiBB\u0004\u0004,\u0011\u0001!n!\f\t\u0015\t=cG!A!\u0002\u0013\u0019\u0019\u0004\u0003\u0006\u0003RY\u0012\t\u0011)A\u0005\u0007oA!Ba37\u0005\u0003\u0005\u000b\u0011BB\u001a\u0011)\u0011iM\u000eB\u0001B\u0003%1q\u0007\u0005\u000b\u0007w1$\u0011!Q\u0001\n\rM\u0002BCB\u001fm\t\u0005\t\u0015!\u0003\u00048!9\u0011\u0011\n\u001c\u0005\u0002\r}\u0002bBA\\m\u0011\u0005\u0013\u0011\u0018\u0005\b\u0003\u00034D\u0011IA]\u0011\u001d\t\u0019M\u000eC!\u0007\u001fBq!a37\t\u0003\u001a\u0019\u0006C\u0004\u0002XZ\"\taa\u0016\t\u000f\u0005\rh\u0007\"\u0011\u0004^!9\u00111 \u001c\u0005\u0002\r5\u0004b\u0002B\u0004m\u0011\u000511\u000f\u0005\b\u000531D\u0011ABA\u0011\u001d\u0011\tJ\u000eC!\u0007\u000bCqAa*7\t\u0003\u001a\tJB\u0004\u0004 \u0012\u0011!n!)\t\u0015\t=\u0013J!A!\u0002\u0013\u00199\u000b\u0003\u0006\u0003R%\u0013\t\u0011)A\u0005\u0007WC!Ba3J\u0005\u0003\u0005\u000b\u0011BBT\u0011)\u0011i-\u0013B\u0001B\u0003%11\u0016\u0005\u000b\u0007wI%\u0011!Q\u0001\n\r\u001d\u0006BCB\u001f\u0013\n\u0005\t\u0015!\u0003\u0004,\"Q1qV%\u0003\u0002\u0003\u0006Iaa*\t\u0015\rE\u0016J!A!\u0002\u0013\u0019Y\u000bC\u0004\u0002J%#\taa-\t\u000f\u0005]\u0016\n\"\u0011\u0002:\"9\u0011\u0011Y%\u0005B\u0005e\u0006bBAb\u0013\u0012\u00053q\u0019\u0005\b\u0003\u0017LE\u0011IBf\u0011\u001d\t9.\u0013C\u0001\u0007\u001fDq!a9J\t\u0003\u001a)\u000eC\u0004\u0002|&#\ta!:\t\u000f\t\u001d\u0011\n\"\u0001\u0004l\"9!\u0011D%\u0005\u0002\re\bb\u0002BI\u0013\u0012\u00053Q \u0005\b\u0005OKE\u0011\tC\u0005\u0011!!)\"\u0013C\u0001\t\u0011]aA\u0002C\u0017\t\u0019!y\u0003C\u0004\u0002J}#\t\u0001\"\u0012\t\u0011\u0011%s\f)Q\u0005\t\u0007B\u0001\u0002b\u0013`A\u0003&\u0011q\u001a\u0005\f\t\u001bz\u0006\u0019!A!B\u0013!y\u0005C\u0004\u0005V}#\t%!\r\t\u000f\u0011]s\f\"\u0011\u0005Z!9A1L0\u0005\u0002\u0011u\u0003b\u0002C3?\u0012\u0005Cq\r\u0005\n\u0005?!\u0011\u0011!C\u0005\u0005C\u0011aaU3r\u001b\u0006\u0004(BA6m\u0003%IW.\\;uC\ndWM\u0003\u0002n]\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003=\fQa]2bY\u0006\u001c\u0001!\u0006\u0003s{\u0006=1#\u0003\u0001to\u0006M\u0011\u0011DA\u0012!\t!X/D\u0001o\u0013\t1hN\u0001\u0004B]f\u0014VM\u001a\t\u0006qf\\\u0018QB\u0007\u0002U&\u0011!P\u001b\u0002\u0004\u001b\u0006\u0004\bC\u0001?~\u0019\u0001!QA \u0001C\u0002}\u0014\u0011aS\t\u0005\u0003\u0003\t9\u0001E\u0002u\u0003\u0007I1!!\u0002o\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001^A\u0005\u0013\r\tYA\u001c\u0002\u0004\u0003:L\bc\u0001?\u0002\u0010\u00119\u0011\u0011\u0003\u0001\u0005\u0006\u0004y(!\u0001,\u0011\u000f\u0005U\u0011qC>\u0002\u000e5\tA.\u0003\u0002jYBQ\u00010a\u0007|\u0003\u001b\ty\"!\t\n\u0007\u0005u!N\u0001\u0004NCB|\u0005o\u001d\t\u0003q\u0002\u0001R\u0001\u001f\u0001|\u0003\u001b\u00012\"!\u0006\u0002&m\fi!a\b\u0002*%\u0019\u0011q\u00057\u0003%5\u000b\u0007OR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0004q\u0006-\u0012bAA\u0017U\nA\u0011\n^3sC\ndW-\u0001\u0004%S:LG\u000f\n\u000b\u0003\u0003g\u00012\u0001^A\u001b\u0013\r\t9D\u001c\u0002\u0005+:LG/\u0001\u0006nCB4\u0015m\u0019;pef,\"!!\u0010\u0011\r\u0005U\u0011qHA\u0010\u0013\r\t\t\u0005\u001c\u0002\u000b\u001b\u0006\u0004h)Y2u_JL\u0018AB*fc6\u000b\u0007\u000f\u0005\u0002y\tM!Aa]A\u001f\u0003\u0019a\u0014N\\5u}Q\u0011\u0011QI\u0001\u0006K6\u0004H/_\u000b\u0007\u0003#\n9&a\u0017\u0016\u0005\u0005M\u0003C\u0002=\u0001\u0003+\nI\u0006E\u0002}\u0003/\"QA \u0004C\u0002}\u00042\u0001`A.\t\u0019\t\tB\u0002b\u0001\u007f\u0006!aM]8n+\u0019\t\t'a\u001a\u0002lQ!\u00111MA7!\u0019A\b!!\u001a\u0002jA\u0019A0a\u001a\u0005\u000by<!\u0019A@\u0011\u0007q\fY\u0007\u0002\u0004\u0002\u0012\u001d\u0011\ra \u0005\b\u0003_:\u0001\u0019AA9\u0003\tIG\u000f\u0005\u0004\u0002\u0016\u0005M\u0014qO\u0005\u0004\u0003kb'\u0001D%uKJ\f'\r\\3P]\u000e,\u0007c\u0002;\u0002z\u0005\u0015\u0014\u0011N\u0005\u0004\u0003wr'A\u0002+va2,''\u0001\u0006oK^\u0014U/\u001b7eKJ,b!!!\u0002\u0014\u0006]UCAAB!!\t))a#\u0002\u0010\u0006eUBAAD\u0015\r\tI\t\\\u0001\b[V$\u0018M\u00197f\u0013\u0011\ti)a\"\u0003\u000f\t+\u0018\u000e\u001c3feB9A/!\u001f\u0002\u0012\u0006U\u0005c\u0001?\u0002\u0014\u0012)a\u0010\u0003b\u0001\u007fB\u0019A0a&\u0005\r\u0005E\u0001B1\u0001\u0000!\u0019A\b!!%\u0002\u0016\u0006YQ)\u001c9usN+\u0017/T1q!\r\tyJC\u0007\u0002\t\tYQ)\u001c9usN+\u0017/T1q'\u0019Q1/!*\u0002(B1\u0001\u0010AA\u0004\u0003\u0003\u0001B!!+\u00020:\u0019A/a+\n\u0007\u00055f.A\u0004qC\u000e\\\u0017mZ3\n\t\u0005E\u00161\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003[sGCAAO\u0003\u0011\u0019\u0018N_3\u0016\u0005\u0005m\u0006c\u0001;\u0002>&\u0019\u0011q\u00188\u0003\u0007%sG/A\u0005l]><hnU5{K\u0006)\u0011\r\u001d9msR!\u0011\u0011AAd\u0011\u001d\tIM\u0004a\u0001\u0003\u000f\t1a[3z\u0003!\u0019wN\u001c;bS:\u001cH\u0003BAh\u0003+\u00042\u0001^Ai\u0013\r\t\u0019N\u001c\u0002\b\u0005>|G.Z1o\u0011\u001d\tIm\u0004a\u0001\u0003\u000f\t1aZ3u)\u0011\tY.!9\u0011\u000bQ\fi.!\u0001\n\u0007\u0005}gN\u0001\u0004PaRLwN\u001c\u0005\b\u0003\u0013\u0004\u0002\u0019AA\u0004\u0003%9W\r^(s\u000b2\u001cX-\u0006\u0003\u0002h\u0006-HCBAu\u0003_\f\t\u0010E\u0002}\u0003W$a!!<\u0012\u0005\u0004y(A\u0001,2\u0011\u001d\tI-\u0005a\u0001\u0003\u000fA\u0001\"a=\u0012\t\u0003\u0007\u0011Q_\u0001\bI\u00164\u0017-\u001e7u!\u0015!\u0018q_Au\u0013\r\tIP\u001c\u0002\ty\tLh.Y7f}\u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002\u0000B1\u0011Q\u0003B\u0001\u0005\u000bI1Aa\u0001m\u0005!IE/\u001a:bi>\u0014\bc\u0002;\u0002z\u0005\u001d\u0011\u0011A\u0001\bkB$\u0017\r^3e+\u0011\u0011YA!\u0005\u0015\r\t5!1\u0003B\u000b!\u0019A\b!a\u0002\u0003\u0010A\u0019AP!\u0005\u0005\r\u000558C1\u0001\u0000\u0011\u001d\tIm\u0005a\u0001\u0003\u000fAqAa\u0006\u0014\u0001\u0004\u0011y!A\u0003wC2,X-A\u0004sK6|g/\u001a3\u0015\t\u0005\u0015&Q\u0004\u0005\b\u0003\u0013$\u0002\u0019AA\u0004\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\u0019\u0003\u0005\u0003\u0003&\t=RB\u0001B\u0014\u0015\u0011\u0011ICa\u000b\u0002\t1\fgn\u001a\u0006\u0003\u0005[\tAA[1wC&!!\u0011\u0007B\u0014\u0005\u0019y%M[3di\":!B!\u000e\u0003\u0018\tm\u0002c\u0001;\u00038%\u0019!\u0011\b8\u0003!M+'/[1m-\u0016\u00148/[8o+&#e$A\u0002)\u000f%\u0011)Da\u0006\u0003<\t91+Z9NCB\fTC\u0002B\"\u0005\u0013\u0012ie\u0005\u0004\u0017g\n\u0015\u0013q\u0015\t\u0007q\u0002\u00119Ea\u0013\u0011\u0007q\u0014I\u0005B\u0003\u007f-\t\u0007q\u0010E\u0002}\u0005\u001b\"q!!\u0005\u0017\t\u000b\u0007q0\u0001\u0003lKf\f\u0014A\u0002<bYV,\u0017\u0007\u0006\u0004\u0003V\t]#\u0011\f\t\b\u0003?3\"q\tB&\u0011\u001d\u0011y%\u0007a\u0001\u0005\u000fBqA!\u0015\u001a\u0001\u0004\u0011Y\u0005\u0006\u0003\u0003L\tu\u0003bBAe9\u0001\u0007!q\t\u000b\u0005\u0003\u001f\u0014\t\u0007C\u0004\u0002Jv\u0001\rAa\u0012\u0015\t\t\u0015$q\r\t\u0006i\u0006u'1\n\u0005\b\u0003\u0013t\u0002\u0019\u0001B$+\u0011\u0011YGa\u001c\u0015\r\t5$1\u000fB;!\ra(q\u000e\u0003\b\u0003[|\"\u0019\u0001B9#\u0011\u0011Y%a\u0002\t\u000f\u0005%w\u00041\u0001\u0003H!A\u00111_\u0010\u0005\u0002\u0004\u00119\bE\u0003u\u0003o\u0014i'\u0006\u0002\u0003|A1\u0011Q\u0003B\u0001\u0005{\u0002r\u0001^A=\u0005\u000f\u0012Y%\u0006\u0003\u0003\u0002\n\u001dEC\u0002BB\u0005\u0013\u0013Y\t\u0005\u0004y\u0001\t\u001d#Q\u0011\t\u0004y\n\u001dEaBAwC\t\u0007!\u0011\u000f\u0005\b\u0003\u0013\f\u0003\u0019\u0001B$\u0011\u001d\u00119\"\ta\u0001\u0005\u000b#BA!\u0012\u0003\u0010\"9\u0011\u0011\u001a\u0012A\u0002\t\u001d\u0013a\u00024pe\u0016\f7\r[\u000b\u0005\u0005+\u0013\u0019\u000b\u0006\u0003\u00024\t]\u0005b\u0002BMG\u0001\u0007!1T\u0001\u0002MB9AO!(\u0003~\t\u0005\u0016b\u0001BP]\nIa)\u001e8di&|g.\r\t\u0004y\n\rFA\u0002BSG\t\u0007qPA\u0001V\u000311wN]3bG\",e\u000e\u001e:z+\u0011\u0011YKa.\u0015\t\u0005M\"Q\u0016\u0005\b\u00053#\u0003\u0019\u0001BX!%!(\u0011\u0017B$\u0005\u0017\u0012),C\u0002\u00034:\u0014\u0011BR;oGRLwN\u001c\u001a\u0011\u0007q\u00149\f\u0002\u0004\u0003&\u0012\u0012\ra \u0015\b-\tU\"q\u0003B\u001e\u0005\u001d\u0019V-]'baJ*bAa0\u0003F\n%7CB\u0013t\u0005\u0003\f9\u000b\u0005\u0004y\u0001\t\r'q\u0019\t\u0004y\n\u0015G!\u0002@&\u0005\u0004y\bc\u0001?\u0003J\u00129\u0011\u0011C\u0013\u0005\u0006\u0004y\u0018\u0001B6fsJ\naA^1mk\u0016\u0014DC\u0003Bi\u0005'\u0014)Na6\u0003ZB9\u0011qT\u0013\u0003D\n\u001d\u0007b\u0002B(U\u0001\u0007!1\u0019\u0005\b\u0005#R\u0003\u0019\u0001Bd\u0011\u001d\u0011YM\u000ba\u0001\u0005\u0007DqA!4+\u0001\u0004\u00119\r\u0006\u0003\u0003H\nu\u0007bBAe[\u0001\u0007!1\u0019\u000b\u0005\u0003\u001f\u0014\t\u000fC\u0004\u0002J:\u0002\rAa1\u0015\t\t\u0015(q\u001d\t\u0006i\u0006u'q\u0019\u0005\b\u0003\u0013|\u0003\u0019\u0001Bb+\u0011\u0011YOa<\u0015\r\t5(1\u001fB{!\ra(q\u001e\u0003\b\u0003[\u0004$\u0019\u0001By#\u0011\u00119-a\u0002\t\u000f\u0005%\u0007\u00071\u0001\u0003D\"A\u00111\u001f\u0019\u0005\u0002\u0004\u00119\u0010E\u0003u\u0003o\u0014i/\u0006\u0002\u0003|B1\u0011Q\u0003B\u0001\u0005{\u0004r\u0001^A=\u0005\u0007\u00149-\u0006\u0003\u0004\u0002\r\u001dACBB\u0002\u0007\u0013\u0019Y\u0001\u0005\u0004y\u0001\t\r7Q\u0001\t\u0004y\u000e\u001dAaBAwe\t\u0007!\u0011\u001f\u0005\b\u0003\u0013\u0014\u0004\u0019\u0001Bb\u0011\u001d\u00119B\ra\u0001\u0007\u000b!BA!1\u0004\u0010!9\u0011\u0011Z\u001aA\u0002\t\rW\u0003BB\n\u00077!B!a\r\u0004\u0016!9!\u0011\u0014\u001bA\u0002\r]\u0001c\u0002;\u0003\u001e\nu8\u0011\u0004\t\u0004y\u000emAA\u0002BSi\t\u0007q0\u0006\u0003\u0004 \r\u001dB\u0003BA\u001a\u0007CAqA!'6\u0001\u0004\u0019\u0019\u0003E\u0005u\u0005c\u0013\u0019Ma2\u0004&A\u0019Apa\n\u0005\r\t\u0015VG1\u0001\u0000Q\u001d)#Q\u0007B\f\u0005w\u0011qaU3r\u001b\u0006\u00048'\u0006\u0004\u00040\rU2\u0011H\n\u0007mM\u001c\t$a*\u0011\ra\u000411GB\u001c!\ra8Q\u0007\u0003\u0006}Z\u0012\ra \t\u0004y\u000eeBaBA\tm\u0011\u0015\ra`\u0001\u0005W\u0016L8'\u0001\u0004wC2,Xm\r\u000b\u000f\u0007\u0003\u001a\u0019e!\u0012\u0004H\r%31JB'!\u001d\tyJNB\u001a\u0007oAqAa\u0014>\u0001\u0004\u0019\u0019\u0004C\u0004\u0003Ru\u0002\raa\u000e\t\u000f\t-W\b1\u0001\u00044!9!QZ\u001fA\u0002\r]\u0002bBB\u001e{\u0001\u000711\u0007\u0005\b\u0007{i\u0004\u0019AB\u001c)\u0011\u00199d!\u0015\t\u000f\u0005%\u0007\t1\u0001\u00044Q!\u0011qZB+\u0011\u001d\tI-\u0011a\u0001\u0007g!Ba!\u0017\u0004\\A)A/!8\u00048!9\u0011\u0011\u001a\"A\u0002\rMR\u0003BB0\u0007G\"ba!\u0019\u0004h\r%\u0004c\u0001?\u0004d\u00119\u0011Q^\"C\u0002\r\u0015\u0014\u0003BB\u001c\u0003\u000fAq!!3D\u0001\u0004\u0019\u0019\u0004\u0003\u0005\u0002t\u000e#\t\u0019AB6!\u0015!\u0018q_B1+\t\u0019y\u0007\u0005\u0004\u0002\u0016\t\u00051\u0011\u000f\t\bi\u0006e41GB\u001c+\u0011\u0019)ha\u001f\u0015\r\r]4QPB@!\u0019A\baa\r\u0004zA\u0019Apa\u001f\u0005\u000f\u00055XI1\u0001\u0004f!9\u0011\u0011Z#A\u0002\rM\u0002b\u0002B\f\u000b\u0002\u00071\u0011\u0010\u000b\u0005\u0007c\u0019\u0019\tC\u0004\u0002J\u001a\u0003\raa\r\u0016\t\r\u001d5q\u0012\u000b\u0005\u0003g\u0019I\tC\u0004\u0003\u001a\u001e\u0003\raa#\u0011\u000fQ\u0014ij!\u001d\u0004\u000eB\u0019Apa$\u0005\r\t\u0015vI1\u0001\u0000+\u0011\u0019\u0019ja'\u0015\t\u0005M2Q\u0013\u0005\b\u00053C\u0005\u0019ABL!%!(\u0011WB\u001a\u0007o\u0019I\nE\u0002}\u00077#aA!*I\u0005\u0004y\bf\u0002\u001c\u00036\t]!1\b\u0002\b'\u0016\fX*\u001995+\u0019\u0019\u0019k!+\u0004.N1\u0011j]BS\u0003O\u0003b\u0001\u001f\u0001\u0004(\u000e-\u0006c\u0001?\u0004*\u0012)a0\u0013b\u0001\u007fB\u0019Ap!,\u0005\u000f\u0005E\u0011\n\"b\u0001\u007f\u0006!1.Z=5\u0003\u00191\u0018\r\\;fiQ\u00112QWB\\\u0007s\u001bYl!0\u0004@\u000e\u000571YBc!\u001d\ty*SBT\u0007WCqAa\u0014S\u0001\u0004\u00199\u000bC\u0004\u0003RI\u0003\raa+\t\u000f\t-'\u000b1\u0001\u0004(\"9!Q\u001a*A\u0002\r-\u0006bBB\u001e%\u0002\u00071q\u0015\u0005\b\u0007{\u0011\u0006\u0019ABV\u0011\u001d\u0019yK\u0015a\u0001\u0007OCqa!-S\u0001\u0004\u0019Y\u000b\u0006\u0003\u0004,\u000e%\u0007bBAe+\u0002\u00071q\u0015\u000b\u0005\u0003\u001f\u001ci\rC\u0004\u0002JZ\u0003\raa*\u0015\t\rE71\u001b\t\u0006i\u0006u71\u0016\u0005\b\u0003\u0013<\u0006\u0019ABT+\u0011\u00199na7\u0015\r\re7q\\Bq!\ra81\u001c\u0003\b\u0003[D&\u0019ABo#\u0011\u0019Y+a\u0002\t\u000f\u0005%\u0007\f1\u0001\u0004(\"A\u00111\u001f-\u0005\u0002\u0004\u0019\u0019\u000fE\u0003u\u0003o\u001cI.\u0006\u0002\u0004hB1\u0011Q\u0003B\u0001\u0007S\u0004r\u0001^A=\u0007O\u001bY+\u0006\u0003\u0004n\u000eMHCBBx\u0007k\u001c9\u0010\u0005\u0004y\u0001\r\u001d6\u0011\u001f\t\u0004y\u000eMHaBAw5\n\u00071Q\u001c\u0005\b\u0003\u0013T\u0006\u0019ABT\u0011\u001d\u00119B\u0017a\u0001\u0007c$Ba!*\u0004|\"9\u0011\u0011Z.A\u0002\r\u001dV\u0003BB\u0000\t\u000f!B!a\r\u0005\u0002!9!\u0011\u0014/A\u0002\u0011\r\u0001c\u0002;\u0003\u001e\u000e%HQ\u0001\t\u0004y\u0012\u001dAA\u0002BS9\n\u0007q0\u0006\u0003\u0005\f\u0011MA\u0003BA\u001a\t\u001bAqA!'^\u0001\u0004!y\u0001E\u0005u\u0005c\u001b9ka+\u0005\u0012A\u0019A\u0010b\u0005\u0005\r\t\u0015VL1\u0001\u0000\u0003\u001d\u0011W/\u001b7e)>,B\u0001\"\u0007\u0005(Q!A1\u0004C\u000f\u001d\raHQ\u0004\u0005\b\t?q\u0006\u0019\u0001C\u0011\u0003\u001d\u0011W/\u001b7eKJ\u0004\u0002\"!\"\u0002\f\u0012\rB\u0011\u0006\t\bi\u0006e4q\u0015C\u0013!\raHq\u0005\u0003\b\u0003[t&\u0019ABo!\u0019A\baa*\u0005&!:\u0011J!\u000e\u0003\u0018\tm\"!E*fc6\u000b\u0007OQ;jY\u0012,'/S7qYV1A\u0011\u0007C\u001f\t\u0003\u001aBaX:\u00054AA\u0011Q\u0011C\u001b\ts!\u0019%\u0003\u0003\u00058\u0005\u001d%a\u0004*fkN\f'\r\\3Ck&dG-\u001a:\u0011\u000fQ\fI\bb\u000f\u0005@A\u0019A\u0010\"\u0010\u0005\u000by|&\u0019A@\u0011\u0007q$\t\u0005\u0002\u0004\u0002\u0012}\u0013\ra \t\u0007q\u0002!Y\u0004b\u0010\u0015\u0005\u0011\u001d\u0003cBAP?\u0012mBqH\u0001\u0006K2,Wn]\u0001\u001bg^LGo\u00195fIR{g+Z2u_Jl\u0015\r\u001d\"vS2$WM]\u0001\u0011m\u0016\u001cGo\u001c:NCB\u0014U/\u001b7eKJ\u0004r\u0001\u001fC)\tw!y$C\u0002\u0005T)\u0014\u0001CV3di>\u0014X*\u00199Ck&dG-\u001a:\u0002\u000b\rdW-\u0019:\u0002\rI,7/\u001e7u)\t!\u0019%\u0001\u0004bI\u0012|e.\u001a\u000b\u0005\t?\"\t'D\u0001`\u0011\u001d!\u0019G\u001aa\u0001\ts\tA!\u001a7f[\u00061\u0011\r\u001a3BY2$B\u0001b\u0018\u0005j!9A1N4A\u0002\u00115\u0014A\u0001=t!\u0019\t)\"a\u001d\u0005:\u0001"
)
public interface SeqMap extends Map, scala.collection.SeqMap {
   static Builder newBuilder() {
      SeqMap$ var10000 = SeqMap$.MODULE$;
      return new SeqMapBuilderImpl();
   }

   static SeqMap from(final IterableOnce it) {
      return SeqMap$.MODULE$.from(it);
   }

   default MapFactory mapFactory() {
      return SeqMap$.MODULE$;
   }

   static void $init$(final SeqMap $this) {
   }

   private static class EmptySeqMap$ implements SeqMap, Serializable {
      public static final EmptySeqMap$ MODULE$ = new EmptySeqMap$();
      private static final long serialVersionUID = 3L;

      static {
         EmptySeqMap$ var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
         var10000 = MODULE$;
      }

      public MapFactory mapFactory() {
         return SeqMap.super.mapFactory();
      }

      public String stringPrefix() {
         return scala.collection.SeqMap.stringPrefix$(this);
      }

      public final Map toMap(final $less$colon$less ev) {
         return Map.toMap$(this, ev);
      }

      public Map withDefault(final Function1 d) {
         return Map.withDefault$(this, d);
      }

      public Map withDefaultValue(final Object d) {
         return Map.withDefaultValue$(this, d);
      }

      public final MapOps $minus(final Object key) {
         return MapOps.$minus$(this, key);
      }

      /** @deprecated */
      public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
         return MapOps.$minus$(this, key1, key2, keys);
      }

      public MapOps removedAll(final IterableOnce keys) {
         return MapOps.removedAll$(this, keys);
      }

      public final MapOps $minus$minus(final IterableOnce keys) {
         return MapOps.$minus$minus$(this, keys);
      }

      public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
         return MapOps.updatedWith$(this, key, remappingFunction);
      }

      public MapOps $plus(final Tuple2 kv) {
         return MapOps.$plus$(this, kv);
      }

      public MapOps transform(final Function2 f) {
         return MapOps.transform$(this, f);
      }

      public Set keySet() {
         return MapOps.keySet$(this);
      }

      public boolean canEqual(final Object that) {
         return scala.collection.Map.canEqual$(this, that);
      }

      public boolean equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public int hashCode() {
         return scala.collection.Map.hashCode$(this);
      }

      public String toString() {
         return scala.collection.Map.toString$(this);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return MapFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return MapFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return MapFactoryDefaults.empty$(this);
      }

      public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
         return MapFactoryDefaults.withFilter$(this, p);
      }

      public MapView view() {
         return scala.collection.MapOps.view$(this);
      }

      public Stepper keyStepper(final StepperShape shape) {
         return scala.collection.MapOps.keyStepper$(this, shape);
      }

      public Stepper valueStepper(final StepperShape shape) {
         return scala.collection.MapOps.valueStepper$(this, shape);
      }

      public final IterableOps mapFromIterable(final scala.collection.Iterable it) {
         return scala.collection.MapOps.mapFromIterable$(this, it);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return scala.collection.MapOps.applyOrElse$(this, x, default);
      }

      public scala.collection.Iterable keys() {
         return scala.collection.MapOps.keys$(this);
      }

      public scala.collection.Iterable values() {
         return scala.collection.MapOps.values$(this);
      }

      public Iterator keysIterator() {
         return scala.collection.MapOps.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return scala.collection.MapOps.valuesIterator$(this);
      }

      public void foreachEntry(final Function2 f) {
         scala.collection.MapOps.foreachEntry$(this, f);
      }

      /** @deprecated */
      public MapView filterKeys(final Function1 p) {
         return scala.collection.MapOps.filterKeys$(this, p);
      }

      /** @deprecated */
      public MapView mapValues(final Function1 f) {
         return scala.collection.MapOps.mapValues$(this, f);
      }

      public Object default(final Object key) throws NoSuchElementException {
         return scala.collection.MapOps.default$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return scala.collection.MapOps.isDefinedAt$(this, key);
      }

      public IterableOps map(final Function1 f) {
         return scala.collection.MapOps.map$(this, f);
      }

      public IterableOps collect(final PartialFunction pf) {
         return scala.collection.MapOps.collect$(this, pf);
      }

      public IterableOps flatMap(final Function1 f) {
         return scala.collection.MapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return scala.collection.MapOps.concat$(this, suffix);
      }

      public IterableOps $plus$plus(final IterableOnce xs) {
         return scala.collection.MapOps.$plus$plus$(this, xs);
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         return scala.collection.MapOps.addString$(this, sb, start, sep, end);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public IterableOps $plus$plus$colon(final IterableOnce that) {
         return scala.collection.MapOps.$plus$plus$colon$(this, that);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, (Function1)k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public IterableFactory iterableFactory() {
         return Iterable.iterableFactory$(this);
      }

      /** @deprecated */
      public final scala.collection.Iterable toIterable() {
         return scala.collection.Iterable.toIterable$(this);
      }

      public final scala.collection.Iterable coll() {
         return scala.collection.Iterable.coll$(this);
      }

      /** @deprecated */
      public scala.collection.Iterable seq() {
         return scala.collection.Iterable.seq$(this);
      }

      public String className() {
         return scala.collection.Iterable.className$(this);
      }

      public final String collectionClassName() {
         return scala.collection.Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final scala.collection.Iterable that) {
         return scala.collection.Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final scala.collection.Iterable toTraversable() {
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

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final scala.collection.Iterable that) {
         return IterableOps.sizeCompare$(this, that);
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

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
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

      public Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
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

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
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

      public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
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

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
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

      public boolean isEmpty() {
         return IterableOnceOps.isEmpty$(this);
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

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
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

      public scala.collection.Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int size() {
         return 0;
      }

      public int knownSize() {
         return 0;
      }

      public Nothing$ apply(final Object key) {
         throw new NoSuchElementException((new java.lang.StringBuilder(15)).append("key not found: ").append(key).toString());
      }

      public boolean contains(final Object key) {
         return false;
      }

      public Option get(final Object key) {
         return None$.MODULE$;
      }

      public Object getOrElse(final Object key, final Function0 default) {
         return default.apply();
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      }

      public SeqMap updated(final Object key, final Object value) {
         return new SeqMap1(key, value);
      }

      public SeqMap removed(final Object key) {
         return this;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptySeqMap$.class);
      }

      public EmptySeqMap$() {
      }
   }

   public static final class SeqMap1 implements SeqMap, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object key1;
      private final Object value1;

      public MapFactory mapFactory() {
         return SeqMap.super.mapFactory();
      }

      public String stringPrefix() {
         return scala.collection.SeqMap.stringPrefix$(this);
      }

      public final Map toMap(final $less$colon$less ev) {
         return Map.toMap$(this, ev);
      }

      public Map withDefault(final Function1 d) {
         return Map.withDefault$(this, d);
      }

      public Map withDefaultValue(final Object d) {
         return Map.withDefaultValue$(this, d);
      }

      public final MapOps $minus(final Object key) {
         return MapOps.$minus$(this, key);
      }

      /** @deprecated */
      public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
         return MapOps.$minus$(this, key1, key2, keys);
      }

      public MapOps removedAll(final IterableOnce keys) {
         return MapOps.removedAll$(this, keys);
      }

      public final MapOps $minus$minus(final IterableOnce keys) {
         return MapOps.$minus$minus$(this, keys);
      }

      public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
         return MapOps.updatedWith$(this, key, remappingFunction);
      }

      public MapOps $plus(final Tuple2 kv) {
         return MapOps.$plus$(this, kv);
      }

      public MapOps transform(final Function2 f) {
         return MapOps.transform$(this, f);
      }

      public Set keySet() {
         return MapOps.keySet$(this);
      }

      public boolean canEqual(final Object that) {
         return scala.collection.Map.canEqual$(this, that);
      }

      public boolean equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public int hashCode() {
         return scala.collection.Map.hashCode$(this);
      }

      public String toString() {
         return scala.collection.Map.toString$(this);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return MapFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return MapFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return MapFactoryDefaults.empty$(this);
      }

      public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
         return MapFactoryDefaults.withFilter$(this, p);
      }

      public MapView view() {
         return scala.collection.MapOps.view$(this);
      }

      public Stepper keyStepper(final StepperShape shape) {
         return scala.collection.MapOps.keyStepper$(this, shape);
      }

      public Stepper valueStepper(final StepperShape shape) {
         return scala.collection.MapOps.valueStepper$(this, shape);
      }

      public final IterableOps mapFromIterable(final scala.collection.Iterable it) {
         return scala.collection.MapOps.mapFromIterable$(this, it);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return scala.collection.MapOps.applyOrElse$(this, x, default);
      }

      public scala.collection.Iterable keys() {
         return scala.collection.MapOps.keys$(this);
      }

      public scala.collection.Iterable values() {
         return scala.collection.MapOps.values$(this);
      }

      public Iterator keysIterator() {
         return scala.collection.MapOps.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return scala.collection.MapOps.valuesIterator$(this);
      }

      /** @deprecated */
      public MapView filterKeys(final Function1 p) {
         return scala.collection.MapOps.filterKeys$(this, p);
      }

      /** @deprecated */
      public MapView mapValues(final Function1 f) {
         return scala.collection.MapOps.mapValues$(this, f);
      }

      public Object default(final Object key) throws NoSuchElementException {
         return scala.collection.MapOps.default$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return scala.collection.MapOps.isDefinedAt$(this, key);
      }

      public IterableOps map(final Function1 f) {
         return scala.collection.MapOps.map$(this, f);
      }

      public IterableOps collect(final PartialFunction pf) {
         return scala.collection.MapOps.collect$(this, pf);
      }

      public IterableOps flatMap(final Function1 f) {
         return scala.collection.MapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return scala.collection.MapOps.concat$(this, suffix);
      }

      public IterableOps $plus$plus(final IterableOnce xs) {
         return scala.collection.MapOps.$plus$plus$(this, xs);
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         return scala.collection.MapOps.addString$(this, sb, start, sep, end);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public IterableOps $plus$plus$colon(final IterableOnce that) {
         return scala.collection.MapOps.$plus$plus$colon$(this, that);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, (Function1)k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public IterableFactory iterableFactory() {
         return Iterable.iterableFactory$(this);
      }

      /** @deprecated */
      public final scala.collection.Iterable toIterable() {
         return scala.collection.Iterable.toIterable$(this);
      }

      public final scala.collection.Iterable coll() {
         return scala.collection.Iterable.coll$(this);
      }

      /** @deprecated */
      public scala.collection.Iterable seq() {
         return scala.collection.Iterable.seq$(this);
      }

      public String className() {
         return scala.collection.Iterable.className$(this);
      }

      public final String collectionClassName() {
         return scala.collection.Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final scala.collection.Iterable that) {
         return scala.collection.Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final scala.collection.Iterable toTraversable() {
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

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final scala.collection.Iterable that) {
         return IterableOps.sizeCompare$(this, that);
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

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
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

      public Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
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

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
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

      public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
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

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
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

      public boolean isEmpty() {
         return IterableOnceOps.isEmpty$(this);
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

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
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

      public scala.collection.Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int size() {
         return 1;
      }

      public int knownSize() {
         return 1;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else {
            throw new NoSuchElementException((new java.lang.StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.key1);
      }

      public Option get(final Object key) {
         return (Option)(BoxesRunTime.equals(key, this.key1) ? new Some(this.value1) : None$.MODULE$);
      }

      public Object getOrElse(final Object key, final Function0 default) {
         return BoxesRunTime.equals(key, this.key1) ? this.value1 : default.apply();
      }

      public Iterator iterator() {
         Iterator$ var10000 = Iterator$.MODULE$;
         Object single_a = new Tuple2(this.key1, this.value1);
         return new AbstractIterator(single_a) {
            private boolean consumed;
            private final Object a$1;

            public boolean hasNext() {
               return !this.consumed;
            }

            public Object next() {
               if (this.consumed) {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty.next();
               } else {
                  this.consumed = true;
                  return this.a$1;
               }
            }

            public Iterator sliceIterator(final int from, final int until) {
               if (!this.consumed && from <= 0 && until != 0) {
                  return this;
               } else {
                  Iterator$ var10000 = Iterator$.MODULE$;
                  return Iterator$.scala$collection$Iterator$$_empty;
               }
            }

            public {
               this.a$1 = a$1;
               this.consumed = false;
            }
         };
      }

      public SeqMap updated(final Object key, final Object value) {
         return (SeqMap)(BoxesRunTime.equals(key, this.key1) ? new SeqMap1(this.key1, value) : new SeqMap2(this.key1, this.value1, key, value));
      }

      public SeqMap removed(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            SeqMap$ var10000 = SeqMap$.MODULE$;
            return SeqMap.EmptySeqMap$.MODULE$;
         } else {
            return this;
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.key1, this.value1));
      }

      public void foreachEntry(final Function2 f) {
         f.apply(this.key1, this.value1);
      }

      public SeqMap1(final Object key1, final Object value1) {
         this.key1 = key1;
         this.value1 = value1;
      }
   }

   public static final class SeqMap2 implements SeqMap, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object key1;
      private final Object value1;
      private final Object key2;
      private final Object value2;

      public MapFactory mapFactory() {
         return SeqMap.super.mapFactory();
      }

      public String stringPrefix() {
         return scala.collection.SeqMap.stringPrefix$(this);
      }

      public final Map toMap(final $less$colon$less ev) {
         return Map.toMap$(this, ev);
      }

      public Map withDefault(final Function1 d) {
         return Map.withDefault$(this, d);
      }

      public Map withDefaultValue(final Object d) {
         return Map.withDefaultValue$(this, d);
      }

      public final MapOps $minus(final Object key) {
         return MapOps.$minus$(this, key);
      }

      /** @deprecated */
      public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
         return MapOps.$minus$(this, key1, key2, keys);
      }

      public MapOps removedAll(final IterableOnce keys) {
         return MapOps.removedAll$(this, keys);
      }

      public final MapOps $minus$minus(final IterableOnce keys) {
         return MapOps.$minus$minus$(this, keys);
      }

      public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
         return MapOps.updatedWith$(this, key, remappingFunction);
      }

      public MapOps $plus(final Tuple2 kv) {
         return MapOps.$plus$(this, kv);
      }

      public MapOps transform(final Function2 f) {
         return MapOps.transform$(this, f);
      }

      public Set keySet() {
         return MapOps.keySet$(this);
      }

      public boolean canEqual(final Object that) {
         return scala.collection.Map.canEqual$(this, that);
      }

      public boolean equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public int hashCode() {
         return scala.collection.Map.hashCode$(this);
      }

      public String toString() {
         return scala.collection.Map.toString$(this);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return MapFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return MapFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return MapFactoryDefaults.empty$(this);
      }

      public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
         return MapFactoryDefaults.withFilter$(this, p);
      }

      public MapView view() {
         return scala.collection.MapOps.view$(this);
      }

      public Stepper keyStepper(final StepperShape shape) {
         return scala.collection.MapOps.keyStepper$(this, shape);
      }

      public Stepper valueStepper(final StepperShape shape) {
         return scala.collection.MapOps.valueStepper$(this, shape);
      }

      public final IterableOps mapFromIterable(final scala.collection.Iterable it) {
         return scala.collection.MapOps.mapFromIterable$(this, it);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return scala.collection.MapOps.applyOrElse$(this, x, default);
      }

      public scala.collection.Iterable keys() {
         return scala.collection.MapOps.keys$(this);
      }

      public scala.collection.Iterable values() {
         return scala.collection.MapOps.values$(this);
      }

      public Iterator keysIterator() {
         return scala.collection.MapOps.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return scala.collection.MapOps.valuesIterator$(this);
      }

      /** @deprecated */
      public MapView filterKeys(final Function1 p) {
         return scala.collection.MapOps.filterKeys$(this, p);
      }

      /** @deprecated */
      public MapView mapValues(final Function1 f) {
         return scala.collection.MapOps.mapValues$(this, f);
      }

      public Object default(final Object key) throws NoSuchElementException {
         return scala.collection.MapOps.default$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return scala.collection.MapOps.isDefinedAt$(this, key);
      }

      public IterableOps map(final Function1 f) {
         return scala.collection.MapOps.map$(this, f);
      }

      public IterableOps collect(final PartialFunction pf) {
         return scala.collection.MapOps.collect$(this, pf);
      }

      public IterableOps flatMap(final Function1 f) {
         return scala.collection.MapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return scala.collection.MapOps.concat$(this, suffix);
      }

      public IterableOps $plus$plus(final IterableOnce xs) {
         return scala.collection.MapOps.$plus$plus$(this, xs);
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         return scala.collection.MapOps.addString$(this, sb, start, sep, end);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public IterableOps $plus$plus$colon(final IterableOnce that) {
         return scala.collection.MapOps.$plus$plus$colon$(this, that);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, (Function1)k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public IterableFactory iterableFactory() {
         return Iterable.iterableFactory$(this);
      }

      /** @deprecated */
      public final scala.collection.Iterable toIterable() {
         return scala.collection.Iterable.toIterable$(this);
      }

      public final scala.collection.Iterable coll() {
         return scala.collection.Iterable.coll$(this);
      }

      /** @deprecated */
      public scala.collection.Iterable seq() {
         return scala.collection.Iterable.seq$(this);
      }

      public String className() {
         return scala.collection.Iterable.className$(this);
      }

      public final String collectionClassName() {
         return scala.collection.Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final scala.collection.Iterable that) {
         return scala.collection.Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final scala.collection.Iterable toTraversable() {
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

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final scala.collection.Iterable that) {
         return IterableOps.sizeCompare$(this, that);
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

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
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

      public Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
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

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
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

      public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
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

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
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

      public boolean isEmpty() {
         return IterableOnceOps.isEmpty$(this);
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

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
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

      public scala.collection.Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int size() {
         return 2;
      }

      public int knownSize() {
         return 2;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return this.value2;
         } else {
            throw new NoSuchElementException((new java.lang.StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.key1) || BoxesRunTime.equals(key, this.key2);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new Some(this.value1);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.key2) ? new Some(this.value2) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else {
            return BoxesRunTime.equals(key, this.key2) ? this.value2 : default.apply();
         }
      }

      public Iterator iterator() {
         Tuple2 var1 = new Tuple2(this.key1, this.value1);
         Tuple2 var2 = new Tuple2(this.key2, this.value2);
         List $colon$colon_this = Nil$.MODULE$;
         $colon$colon var10000 = new $colon$colon(var2, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var1, $colon$colon_this);
         $colon$colon_this = null;
         return ((List)var10000).iterator();
      }

      public SeqMap updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap2(this.key1, value, this.key2, this.value2);
         } else {
            return (SeqMap)(BoxesRunTime.equals(key, this.key2) ? new SeqMap2(this.key1, this.value1, this.key2, value) : new SeqMap3(this.key1, this.value1, this.key2, this.value2, key, value));
         }
      }

      public SeqMap removed(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap1(this.key2, this.value2);
         } else {
            return (SeqMap)(BoxesRunTime.equals(key, this.key2) ? new SeqMap1(this.key1, this.value1) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.key1, this.value1));
         f.apply(new Tuple2(this.key2, this.value2));
      }

      public void foreachEntry(final Function2 f) {
         f.apply(this.key1, this.value1);
         f.apply(this.key2, this.value2);
      }

      public SeqMap2(final Object key1, final Object value1, final Object key2, final Object value2) {
         this.key1 = key1;
         this.value1 = value1;
         this.key2 = key2;
         this.value2 = value2;
      }
   }

   public static class SeqMap3 implements SeqMap, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object key1;
      private final Object value1;
      private final Object key2;
      private final Object value2;
      private final Object key3;
      private final Object value3;

      public MapFactory mapFactory() {
         return SeqMap.super.mapFactory();
      }

      public String stringPrefix() {
         return scala.collection.SeqMap.stringPrefix$(this);
      }

      public final Map toMap(final $less$colon$less ev) {
         return Map.toMap$(this, ev);
      }

      public Map withDefault(final Function1 d) {
         return Map.withDefault$(this, d);
      }

      public Map withDefaultValue(final Object d) {
         return Map.withDefaultValue$(this, d);
      }

      public final MapOps $minus(final Object key) {
         return MapOps.$minus$(this, key);
      }

      /** @deprecated */
      public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
         return MapOps.$minus$(this, key1, key2, keys);
      }

      public MapOps removedAll(final IterableOnce keys) {
         return MapOps.removedAll$(this, keys);
      }

      public final MapOps $minus$minus(final IterableOnce keys) {
         return MapOps.$minus$minus$(this, keys);
      }

      public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
         return MapOps.updatedWith$(this, key, remappingFunction);
      }

      public MapOps $plus(final Tuple2 kv) {
         return MapOps.$plus$(this, kv);
      }

      public MapOps transform(final Function2 f) {
         return MapOps.transform$(this, f);
      }

      public Set keySet() {
         return MapOps.keySet$(this);
      }

      public boolean canEqual(final Object that) {
         return scala.collection.Map.canEqual$(this, that);
      }

      public boolean equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public int hashCode() {
         return scala.collection.Map.hashCode$(this);
      }

      public String toString() {
         return scala.collection.Map.toString$(this);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return MapFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return MapFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return MapFactoryDefaults.empty$(this);
      }

      public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
         return MapFactoryDefaults.withFilter$(this, p);
      }

      public MapView view() {
         return scala.collection.MapOps.view$(this);
      }

      public Stepper keyStepper(final StepperShape shape) {
         return scala.collection.MapOps.keyStepper$(this, shape);
      }

      public Stepper valueStepper(final StepperShape shape) {
         return scala.collection.MapOps.valueStepper$(this, shape);
      }

      public final IterableOps mapFromIterable(final scala.collection.Iterable it) {
         return scala.collection.MapOps.mapFromIterable$(this, it);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return scala.collection.MapOps.applyOrElse$(this, x, default);
      }

      public scala.collection.Iterable keys() {
         return scala.collection.MapOps.keys$(this);
      }

      public scala.collection.Iterable values() {
         return scala.collection.MapOps.values$(this);
      }

      public Iterator keysIterator() {
         return scala.collection.MapOps.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return scala.collection.MapOps.valuesIterator$(this);
      }

      /** @deprecated */
      public MapView filterKeys(final Function1 p) {
         return scala.collection.MapOps.filterKeys$(this, p);
      }

      /** @deprecated */
      public MapView mapValues(final Function1 f) {
         return scala.collection.MapOps.mapValues$(this, f);
      }

      public Object default(final Object key) throws NoSuchElementException {
         return scala.collection.MapOps.default$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return scala.collection.MapOps.isDefinedAt$(this, key);
      }

      public IterableOps map(final Function1 f) {
         return scala.collection.MapOps.map$(this, f);
      }

      public IterableOps collect(final PartialFunction pf) {
         return scala.collection.MapOps.collect$(this, pf);
      }

      public IterableOps flatMap(final Function1 f) {
         return scala.collection.MapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return scala.collection.MapOps.concat$(this, suffix);
      }

      public IterableOps $plus$plus(final IterableOnce xs) {
         return scala.collection.MapOps.$plus$plus$(this, xs);
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         return scala.collection.MapOps.addString$(this, sb, start, sep, end);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public IterableOps $plus$plus$colon(final IterableOnce that) {
         return scala.collection.MapOps.$plus$plus$colon$(this, that);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, (Function1)k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public IterableFactory iterableFactory() {
         return Iterable.iterableFactory$(this);
      }

      /** @deprecated */
      public final scala.collection.Iterable toIterable() {
         return scala.collection.Iterable.toIterable$(this);
      }

      public final scala.collection.Iterable coll() {
         return scala.collection.Iterable.coll$(this);
      }

      /** @deprecated */
      public scala.collection.Iterable seq() {
         return scala.collection.Iterable.seq$(this);
      }

      public String className() {
         return scala.collection.Iterable.className$(this);
      }

      public final String collectionClassName() {
         return scala.collection.Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final scala.collection.Iterable that) {
         return scala.collection.Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final scala.collection.Iterable toTraversable() {
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

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final scala.collection.Iterable that) {
         return IterableOps.sizeCompare$(this, that);
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

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
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

      public Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
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

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
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

      public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
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

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
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

      public boolean isEmpty() {
         return IterableOnceOps.isEmpty$(this);
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

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
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

      public scala.collection.Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int size() {
         return 3;
      }

      public int knownSize() {
         return 3;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return this.value2;
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return this.value3;
         } else {
            throw new NoSuchElementException((new java.lang.StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.key1) || BoxesRunTime.equals(key, this.key2) || BoxesRunTime.equals(key, this.key3);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new Some(this.value1);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new Some(this.value2);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.key3) ? new Some(this.value3) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return this.value2;
         } else {
            return BoxesRunTime.equals(key, this.key3) ? this.value3 : default.apply();
         }
      }

      public Iterator iterator() {
         Tuple2 var1 = new Tuple2(this.key1, this.value1);
         Tuple2 var2 = new Tuple2(this.key2, this.value2);
         Tuple2 var3 = new Tuple2(this.key3, this.value3);
         List $colon$colon_this = Nil$.MODULE$;
         $colon$colon var10000 = new $colon$colon(var3, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var2, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var1, $colon$colon_this);
         $colon$colon_this = null;
         return ((List)var10000).iterator();
      }

      public SeqMap updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap3(this.key1, value, this.key2, this.value2, this.key3, this.value3);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new SeqMap3(this.key1, this.value1, this.key2, value, this.key3, this.value3);
         } else {
            return (SeqMap)(BoxesRunTime.equals(key, this.key3) ? new SeqMap3(this.key1, this.value1, this.key2, this.value2, this.key3, value) : new SeqMap4(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3, key, value));
         }
      }

      public SeqMap removed(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap2(this.key2, this.value2, this.key3, this.value3);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new SeqMap2(this.key1, this.value1, this.key3, this.value3);
         } else {
            return (SeqMap)(BoxesRunTime.equals(key, this.key3) ? new SeqMap2(this.key1, this.value1, this.key2, this.value2) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.key1, this.value1));
         f.apply(new Tuple2(this.key2, this.value2));
         f.apply(new Tuple2(this.key3, this.value3));
      }

      public void foreachEntry(final Function2 f) {
         f.apply(this.key1, this.value1);
         f.apply(this.key2, this.value2);
         f.apply(this.key3, this.value3);
      }

      public SeqMap3(final Object key1, final Object value1, final Object key2, final Object value2, final Object key3, final Object value3) {
         this.key1 = key1;
         this.value1 = value1;
         this.key2 = key2;
         this.value2 = value2;
         this.key3 = key3;
         this.value3 = value3;
      }
   }

   public static final class SeqMap4 implements SeqMap, Serializable {
      private static final long serialVersionUID = 3L;
      private final Object key1;
      private final Object value1;
      private final Object key2;
      private final Object value2;
      private final Object key3;
      private final Object value3;
      private final Object key4;
      private final Object value4;

      public MapFactory mapFactory() {
         return SeqMap.super.mapFactory();
      }

      public String stringPrefix() {
         return scala.collection.SeqMap.stringPrefix$(this);
      }

      public final Map toMap(final $less$colon$less ev) {
         return Map.toMap$(this, ev);
      }

      public Map withDefault(final Function1 d) {
         return Map.withDefault$(this, d);
      }

      public Map withDefaultValue(final Object d) {
         return Map.withDefaultValue$(this, d);
      }

      public final MapOps $minus(final Object key) {
         return MapOps.$minus$(this, key);
      }

      /** @deprecated */
      public MapOps $minus(final Object key1, final Object key2, final Seq keys) {
         return MapOps.$minus$(this, key1, key2, keys);
      }

      public MapOps removedAll(final IterableOnce keys) {
         return MapOps.removedAll$(this, keys);
      }

      public final MapOps $minus$minus(final IterableOnce keys) {
         return MapOps.$minus$minus$(this, keys);
      }

      public MapOps updatedWith(final Object key, final Function1 remappingFunction) {
         return MapOps.updatedWith$(this, key, remappingFunction);
      }

      public MapOps $plus(final Tuple2 kv) {
         return MapOps.$plus$(this, kv);
      }

      public MapOps transform(final Function2 f) {
         return MapOps.transform$(this, f);
      }

      public Set keySet() {
         return MapOps.keySet$(this);
      }

      public boolean canEqual(final Object that) {
         return scala.collection.Map.canEqual$(this, that);
      }

      public boolean equals(final Object o) {
         return scala.collection.Map.equals$(this, o);
      }

      public int hashCode() {
         return scala.collection.Map.hashCode$(this);
      }

      public String toString() {
         return scala.collection.Map.toString$(this);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return MapFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return MapFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return MapFactoryDefaults.empty$(this);
      }

      public scala.collection.MapOps.WithFilter withFilter(final Function1 p) {
         return MapFactoryDefaults.withFilter$(this, p);
      }

      public MapView view() {
         return scala.collection.MapOps.view$(this);
      }

      public Stepper keyStepper(final StepperShape shape) {
         return scala.collection.MapOps.keyStepper$(this, shape);
      }

      public Stepper valueStepper(final StepperShape shape) {
         return scala.collection.MapOps.valueStepper$(this, shape);
      }

      public final IterableOps mapFromIterable(final scala.collection.Iterable it) {
         return scala.collection.MapOps.mapFromIterable$(this, it);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return scala.collection.MapOps.applyOrElse$(this, x, default);
      }

      public scala.collection.Iterable keys() {
         return scala.collection.MapOps.keys$(this);
      }

      public scala.collection.Iterable values() {
         return scala.collection.MapOps.values$(this);
      }

      public Iterator keysIterator() {
         return scala.collection.MapOps.keysIterator$(this);
      }

      public Iterator valuesIterator() {
         return scala.collection.MapOps.valuesIterator$(this);
      }

      /** @deprecated */
      public MapView filterKeys(final Function1 p) {
         return scala.collection.MapOps.filterKeys$(this, p);
      }

      /** @deprecated */
      public MapView mapValues(final Function1 f) {
         return scala.collection.MapOps.mapValues$(this, f);
      }

      public Object default(final Object key) throws NoSuchElementException {
         return scala.collection.MapOps.default$(this, key);
      }

      public boolean isDefinedAt(final Object key) {
         return scala.collection.MapOps.isDefinedAt$(this, key);
      }

      public IterableOps map(final Function1 f) {
         return scala.collection.MapOps.map$(this, f);
      }

      public IterableOps collect(final PartialFunction pf) {
         return scala.collection.MapOps.collect$(this, pf);
      }

      public IterableOps flatMap(final Function1 f) {
         return scala.collection.MapOps.flatMap$(this, f);
      }

      public IterableOps concat(final IterableOnce suffix) {
         return scala.collection.MapOps.concat$(this, suffix);
      }

      public IterableOps $plus$plus(final IterableOnce xs) {
         return scala.collection.MapOps.$plus$plus$(this, xs);
      }

      public StringBuilder addString(final StringBuilder sb, final String start, final String sep, final String end) {
         return scala.collection.MapOps.addString$(this, sb, start, sep, end);
      }

      /** @deprecated */
      public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
         return scala.collection.MapOps.$plus$(this, elem1, elem2, elems);
      }

      /** @deprecated */
      public IterableOps $plus$plus$colon(final IterableOnce that) {
         return scala.collection.MapOps.$plus$plus$colon$(this, that);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, (Function1)k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, (PartialFunction)k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

      public boolean apply$mcZD$sp(final double v1) {
         return Function1.apply$mcZD$sp$(this, v1);
      }

      public double apply$mcDD$sp(final double v1) {
         return Function1.apply$mcDD$sp$(this, v1);
      }

      public float apply$mcFD$sp(final double v1) {
         return Function1.apply$mcFD$sp$(this, v1);
      }

      public int apply$mcID$sp(final double v1) {
         return Function1.apply$mcID$sp$(this, v1);
      }

      public long apply$mcJD$sp(final double v1) {
         return Function1.apply$mcJD$sp$(this, v1);
      }

      public void apply$mcVD$sp(final double v1) {
         Function1.apply$mcVD$sp$(this, v1);
      }

      public boolean apply$mcZF$sp(final float v1) {
         return Function1.apply$mcZF$sp$(this, v1);
      }

      public double apply$mcDF$sp(final float v1) {
         return Function1.apply$mcDF$sp$(this, v1);
      }

      public float apply$mcFF$sp(final float v1) {
         return Function1.apply$mcFF$sp$(this, v1);
      }

      public int apply$mcIF$sp(final float v1) {
         return Function1.apply$mcIF$sp$(this, v1);
      }

      public long apply$mcJF$sp(final float v1) {
         return Function1.apply$mcJF$sp$(this, v1);
      }

      public void apply$mcVF$sp(final float v1) {
         Function1.apply$mcVF$sp$(this, v1);
      }

      public boolean apply$mcZI$sp(final int v1) {
         return Function1.apply$mcZI$sp$(this, v1);
      }

      public double apply$mcDI$sp(final int v1) {
         return Function1.apply$mcDI$sp$(this, v1);
      }

      public float apply$mcFI$sp(final int v1) {
         return Function1.apply$mcFI$sp$(this, v1);
      }

      public int apply$mcII$sp(final int v1) {
         return Function1.apply$mcII$sp$(this, v1);
      }

      public long apply$mcJI$sp(final int v1) {
         return Function1.apply$mcJI$sp$(this, v1);
      }

      public void apply$mcVI$sp(final int v1) {
         Function1.apply$mcVI$sp$(this, v1);
      }

      public boolean apply$mcZJ$sp(final long v1) {
         return Function1.apply$mcZJ$sp$(this, v1);
      }

      public double apply$mcDJ$sp(final long v1) {
         return Function1.apply$mcDJ$sp$(this, v1);
      }

      public float apply$mcFJ$sp(final long v1) {
         return Function1.apply$mcFJ$sp$(this, v1);
      }

      public int apply$mcIJ$sp(final long v1) {
         return Function1.apply$mcIJ$sp$(this, v1);
      }

      public long apply$mcJJ$sp(final long v1) {
         return Function1.apply$mcJJ$sp$(this, v1);
      }

      public void apply$mcVJ$sp(final long v1) {
         Function1.apply$mcVJ$sp$(this, v1);
      }

      public Function1 compose(final Function1 g) {
         return Function1.compose$(this, g);
      }

      public IterableFactory iterableFactory() {
         return Iterable.iterableFactory$(this);
      }

      /** @deprecated */
      public final scala.collection.Iterable toIterable() {
         return scala.collection.Iterable.toIterable$(this);
      }

      public final scala.collection.Iterable coll() {
         return scala.collection.Iterable.coll$(this);
      }

      /** @deprecated */
      public scala.collection.Iterable seq() {
         return scala.collection.Iterable.seq$(this);
      }

      public String className() {
         return scala.collection.Iterable.className$(this);
      }

      public final String collectionClassName() {
         return scala.collection.Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final scala.collection.Iterable that) {
         return scala.collection.Iterable.lazyZip$(this, that);
      }

      /** @deprecated */
      public final scala.collection.Iterable toTraversable() {
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

      public int sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public int sizeCompare(final scala.collection.Iterable that) {
         return IterableOps.sizeCompare$(this, that);
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

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object take(final int n) {
         return IterableOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IterableOps.takeRight$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object drop(final int n) {
         return IterableOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IterableOps.dropRight$(this, n);
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

      public Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
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

      public Object map(final Function1 f) {
         return IterableOps.map$(this, f);
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

      public Object concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
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

      public Object zipAll(final scala.collection.Iterable that, final Object thisElem, final Object thatElem) {
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

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public Object $plus$plus$colon(final IterableOnce that) {
         return IterableOps.$plus$plus$colon$(this, that);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
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

      public boolean isEmpty() {
         return IterableOnceOps.isEmpty$(this);
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

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
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

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
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

      public scala.collection.Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int size() {
         return 4;
      }

      public int knownSize() {
         return 4;
      }

      public Object apply(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return this.value2;
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return this.value3;
         } else if (BoxesRunTime.equals(key, this.key4)) {
            return this.value4;
         } else {
            throw new NoSuchElementException((new java.lang.StringBuilder(15)).append("key not found: ").append(key).toString());
         }
      }

      public boolean contains(final Object key) {
         return BoxesRunTime.equals(key, this.key1) || BoxesRunTime.equals(key, this.key2) || BoxesRunTime.equals(key, this.key3) || BoxesRunTime.equals(key, this.key4);
      }

      public Option get(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new Some(this.value1);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new Some(this.value2);
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return new Some(this.value3);
         } else {
            return (Option)(BoxesRunTime.equals(key, this.key4) ? new Some(this.value4) : None$.MODULE$);
         }
      }

      public Object getOrElse(final Object key, final Function0 default) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return this.value1;
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return this.value2;
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return this.value3;
         } else {
            return BoxesRunTime.equals(key, this.key4) ? this.value4 : default.apply();
         }
      }

      public Iterator iterator() {
         Tuple2 var1 = new Tuple2(this.key1, this.value1);
         Tuple2 var2 = new Tuple2(this.key2, this.value2);
         Tuple2 var3 = new Tuple2(this.key3, this.value3);
         Tuple2 var4 = new Tuple2(this.key4, this.value4);
         List $colon$colon_this = Nil$.MODULE$;
         $colon$colon var10000 = new $colon$colon(var4, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var3, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var2, $colon$colon_this);
         $colon$colon_this = null;
         List $colon$colon_this = var10000;
         var10000 = new $colon$colon(var1, $colon$colon_this);
         $colon$colon_this = null;
         return ((List)var10000).iterator();
      }

      public SeqMap updated(final Object key, final Object value) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap4(this.key1, value, this.key2, this.value2, this.key3, this.value3, this.key4, this.value4);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new SeqMap4(this.key1, this.value1, this.key2, value, this.key3, this.value3, this.key4, this.value4);
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return new SeqMap4(this.key1, this.value1, this.key2, this.value2, this.key3, value, this.key4, this.value4);
         } else if (BoxesRunTime.equals(key, this.key4)) {
            return new SeqMap4(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3, this.key4, value);
         } else {
            Vector$ var10000 = Vector$.MODULE$;
            ArraySeq apply_elems = ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{this.key1, this.key2, this.key3, this.key4, key});
            Vector$ apply_this = var10000;
            Vector var13 = apply_this.from(apply_elems);
            Object var9 = null;
            apply_elems = null;
            Vector fields = var13;
            HashMap$ var14 = HashMap$.MODULE$;
            ArraySeq apply_elems = ScalaRunTime$.MODULE$.wrapRefArray(new Tuple2[]{new Tuple2(this.key1, new Tuple2(0, this.value1)), new Tuple2(this.key2, new Tuple2(1, this.value2)), new Tuple2(this.key3, new Tuple2(2, this.value3)), new Tuple2(this.key4, new Tuple2(3, this.value4)), new Tuple2(key, new Tuple2(4, value))});
            HashMap$ apply_this = var14;
            HashMap var15 = apply_this.from(apply_elems);
            Object var11 = null;
            apply_elems = null;
            Map underlying = var15;
            return new VectorMap(fields, underlying);
         }
      }

      public SeqMap removed(final Object key) {
         if (BoxesRunTime.equals(key, this.key1)) {
            return new SeqMap3(this.key2, this.value2, this.key3, this.value3, this.key4, this.value4);
         } else if (BoxesRunTime.equals(key, this.key2)) {
            return new SeqMap3(this.key1, this.value1, this.key3, this.value3, this.key4, this.value4);
         } else if (BoxesRunTime.equals(key, this.key3)) {
            return new SeqMap3(this.key1, this.value1, this.key2, this.value2, this.key4, this.value4);
         } else {
            return (SeqMap)(BoxesRunTime.equals(key, this.key4) ? new SeqMap3(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3) : this);
         }
      }

      public void foreach(final Function1 f) {
         f.apply(new Tuple2(this.key1, this.value1));
         f.apply(new Tuple2(this.key2, this.value2));
         f.apply(new Tuple2(this.key3, this.value3));
         f.apply(new Tuple2(this.key4, this.value4));
      }

      public void foreachEntry(final Function2 f) {
         f.apply(this.key1, this.value1);
         f.apply(this.key2, this.value2);
         f.apply(this.key3, this.value3);
         f.apply(this.key4, this.value4);
      }

      public Builder buildTo(final Builder builder) {
         return (Builder)builder.addOne(new Tuple2(this.key1, this.value1)).addOne(new Tuple2(this.key2, this.value2)).addOne(new Tuple2(this.key3, this.value3)).addOne(new Tuple2(this.key4, this.value4));
      }

      public SeqMap4(final Object key1, final Object value1, final Object key2, final Object value2, final Object key3, final Object value3, final Object key4, final Object value4) {
         this.key1 = key1;
         this.value1 = value1;
         this.key2 = key2;
         this.value2 = value2;
         this.key3 = key3;
         this.value3 = value3;
         this.key4 = key4;
         this.value4 = value4;
      }
   }

   private static final class SeqMapBuilderImpl implements ReusableBuilder {
      private SeqMap elems;
      private boolean switchedToVectorMapBuilder;
      private VectorMapBuilder vectorMapBuilder;

      public void sizeHint(final int size) {
         Builder.sizeHint$(this, size);
      }

      public final void sizeHint(final IterableOnce coll, final int delta) {
         Builder.sizeHint$(this, coll, delta);
      }

      public final int sizeHint$default$2() {
         return Builder.sizeHint$default$2$(this);
      }

      public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
         Builder.sizeHintBounded$(this, size, boundingColl);
      }

      public Builder mapResult(final Function1 f) {
         return Builder.mapResult$(this, f);
      }

      public final Growable $plus$eq(final Object elem) {
         return Growable.$plus$eq$(this, elem);
      }

      /** @deprecated */
      public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
         return Growable.$plus$eq$(this, elem1, elem2, elems);
      }

      public final Growable $plus$plus$eq(final IterableOnce elems) {
         return Growable.$plus$plus$eq$(this, elems);
      }

      public int knownSize() {
         return Growable.knownSize$(this);
      }

      public void clear() {
         SeqMap$ var10001 = SeqMap$.MODULE$;
         this.elems = SeqMap.EmptySeqMap$.MODULE$;
         if (this.vectorMapBuilder != null) {
            this.vectorMapBuilder.clear();
         }

         this.switchedToVectorMapBuilder = false;
      }

      public SeqMap result() {
         return (SeqMap)(this.switchedToVectorMapBuilder ? this.vectorMapBuilder.result() : this.elems);
      }

      public SeqMapBuilderImpl addOne(final Tuple2 elem) {
         if (this.switchedToVectorMapBuilder) {
            this.vectorMapBuilder.addOne(elem);
         } else if (this.elems.size() < 4) {
            this.elems = (SeqMap)this.elems.$plus(elem);
         } else if (this.elems.contains(elem._1())) {
            this.elems = (SeqMap)this.elems.$plus(elem);
         } else {
            this.switchedToVectorMapBuilder = true;
            if (this.vectorMapBuilder == null) {
               this.vectorMapBuilder = new VectorMapBuilder();
            }

            ((SeqMap4)this.elems).buildTo(this.vectorMapBuilder);
            this.vectorMapBuilder.addOne(elem);
         }

         return this;
      }

      public SeqMapBuilderImpl addAll(final IterableOnce xs) {
         if (this.switchedToVectorMapBuilder) {
            VectorMapBuilder var10000 = this.vectorMapBuilder;
            if (var10000 == null) {
               throw null;
            } else {
               Growable.addAll$(var10000, xs);
               return this;
            }
         } else {
            return (SeqMapBuilderImpl)Growable.addAll$(this, xs);
         }
      }

      public SeqMapBuilderImpl() {
         SeqMap$ var10001 = SeqMap$.MODULE$;
         this.elems = SeqMap.EmptySeqMap$.MODULE$;
         this.switchedToVectorMapBuilder = false;
      }
   }
}
