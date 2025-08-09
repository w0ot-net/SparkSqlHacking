package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Stream$;
import scala.collection.immutable.Vector;
import scala.collection.immutable.Vector$;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Buffer$;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0005f!\u00031b!\u0003\r\tA\u001aDK\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u0015\t\bA\"\u0001s\u0011\u001d\tY\u0002\u0001D\u0001\u0003;Aq!!\u000e\u0001\r\u0003\t9\u0004C\u0004\u0002>\u00011\t!a\u0010\t\u000f\u0005-\u0003A\"\u0001\u0002N!9\u0011\u0011\u000b\u0001\u0007\u0002\u0005M\u0003bBA,\u0001\u0019\u0005\u0011\u0011\f\u0005\b\u0003;\u0002a\u0011AA0\u0011\u001d\tI\u0007\u0001D\u0001\u0003WBq!a\u001f\u0001\r\u0003\ti\bC\u0004\u0002\u0014\u00021\t!!&\t\u000f\u0005\u001d\u0006A\"\u0001\u0002*\"9\u0011Q\u0018\u0001\u0007\u0002\u0005}\u0006bBAo\u0001\u0019\u0005\u0011q\u001c\u0005\b\u0003K\u0004A\u0011AAt\u0011\u001d\tY\u000f\u0001D\u0001\u0003[Dq!a?\u0001\t\u0003\ti\u0010C\u0004\u0003\u0014\u0001!\t!!@\t\u000f\tU\u0001\u0001\"\u0001\u0003\u0018!9!1\u0005\u0001\u0005\u0002\t\u0015\u0002b\u0002B\u0015\u0001\u0011\u0005!1\u0006\u0005\b\u0005_\u0001A\u0011\u0001B\u0019\u0011\u001d\u0011)\u0004\u0001C\u0001\u0005oAqA!\u0011\u0001\t\u0013\u0011\u0019\u0005C\u0004\u0003h\u0001!IA!\u001b\t\u000f\t}\u0004\u0001\"\u0001\u0003\u0002\"9!\u0011\u0013\u0001\u0005\u0002\tM\u0005b\u0002BR\u0001\u0011\u0015!Q\u0015\u0005\b\u0005\u0007\u0004AQ\u0001Bc\u0011\u001d\u0011i\u000e\u0001C\u0001\u0005?DqA!=\u0001\t\u0003\u0011\u0019\u0010C\u0004\u0003\u0000\u0002!\ta!\u0001\t\u000f\r=\u0001\u0001\"\u0001\u0004\u0012!91Q\u0004\u0001\u0005\u000e\r}\u0001bBB\u001c\u0001\u0011\u00051\u0011\b\u0005\b\u0007\u000b\u0002A\u0011AB$\u0011\u001d\u0019)\u0006\u0001C\u0007\u0007/Bqa!\u001a\u0001\t\u001b\u00199\u0007C\u0004\u0004\b\u0002!\ta!#\t\u000f\r]\u0005\u0001\"\u0001\u0002~\"91\u0011\u0014\u0001\u0005\u0002\u0005u\bbBBT\u0001\u0011\u00051\u0011\u0016\u0005\b\u0007W\u0003AQABW\u0011\u001d\u0019i\r\u0001C\u0001\u0007\u001fDqa!4\u0001\t\u0003\u0019Y\u000fC\u0004\u0004N\u0002!\taa?\t\u000f\u00115\u0001\u0001\"\u0001\u0005\u0010!9Aq\u0005\u0001\u0005\u0002\u0011%\u0002b\u0002C\u001b\u0001\u0011\u0005Aq\u0007\u0005\b\t\u0013\u0002A\u0011\u0001C&\u0011\u001d!9\u0006\u0001C\u0001\t3Bq\u0001\"\u001a\u0001\t\u0003!9\u0007C\u0004\u0005t\u0001!\t\u0001\"\u001e\u0007\r\u0011\u001d\u0005\u0001\u0002CE\u0011)!)k\u000eB\u0001B\u0003%Aq\u0015\u0005\u000b\u0003o:$\u0011!Q\u0001\n\u0011u\u0006B\u0003C`o\t\u0005\t\u0015!\u0003\u0005B\"9A1Y\u001c\u0005\u0002\u0011\u0015\u0007\"\u0003Cio\u0001\u0007I\u0011\u0001Cj\u0011%!)n\u000ea\u0001\n\u0003!9\u000e\u0003\u0005\u0005^^\u0002\u000b\u0015\u0002CO\u0011%!yn\u000ea\u0001\n\u0003!\t\u000fC\u0005\u0005d^\u0002\r\u0011\"\u0001\u0005f\"AA\u0011^\u001c!B\u0013!\t\u000bC\u0005\u0004\u001a^\u0002\r\u0011\"\u0001\u0002~\"IA1^\u001cA\u0002\u0013\u0005AQ\u001e\u0005\t\tc<\u0004\u0015)\u0003\u00020!9A1_\u001c\u0005\u0002\u0011U\bb\u0002C}o\u0011\u0005A1\u001b\u0005\b\tw<D\u0011\u0001C\u007f\u0011\u001d)9\u0001\u0001C\u0001\u000b\u0013Aq!b\u0007\u0001\t\u0003)i\u0002C\u0004\u00060\u0001!\t!\"\r\t\u000f\u0015\r\u0003\u0001\"\u0001\u0006F!9Q1\u000b\u0001\u0005\u0002\u0015U\u0003bBC;\u0001\u0011\u0005Qq\u000f\u0005\b\u000b\u0017\u0003AQACG\u0011\u001d)Y\t\u0001C\u0003\u000b3Cq!b#\u0001\t\u000b)y\nC\u0004\u0006$\u0002!\t!\"*\t\u000f\u0015\r\u0006\u0001\"\u0002\u0006:\"9Q1\u0015\u0001\u0005\u0006\u0015\r\u0007bBCf\u0001\u0011\u0005QQ\u001a\u0005\b\u000bC\u0004AQACr\u0011\u001d)y\u000f\u0001C\u0001\u000bcDq!b@\u0001\t\u00031\t\u0001C\u0004\u0007\n\u0001!\tAb\u0003\t\u000f\u00195\u0002\u0001\"\u0001\u00070!9aQ\b\u0001\u0005\u0002\u0019}\u0002b\u0002D$\u0001\u0011\u0005a\u0011\n\u0005\b\r\u001f\u0002AQ\u0001D)\u0011\u001d1\t\u0007\u0001C\u0003\rGBqAb\u001c\u0001\t\u00031\t\bC\u0004\u0007\f\u0002!\tB\"$\u0003\u001f%#XM]1cY\u0016|enY3PaNT!AY2\u0002\u0015\r|G\u000e\\3di&|gNC\u0001e\u0003\u0015\u00198-\u00197b\u0007\u0001)baZA\no\u0006\u00052C\u0001\u0001i!\tI'.D\u0001d\u0013\tY7MA\u0002B]f\fa\u0001J5oSR$C#\u00018\u0011\u0005%|\u0017B\u00019d\u0005\u0011)f.\u001b;\u0002\u0011M\u001c\u0017M\u001c'fMR,2a]A\u0002)\r!\u0018q\u0003\u000b\u0004k\u0006\u001d\u0001\u0003\u0002<x\u0003\u0003a\u0001\u0001\u0002\u0004y\u0001\u0011\u0015\r!\u001f\u0002\u0003\u0007\u000e+\"A\u001f@\u0012\u0005mD\u0007CA5}\u0013\ti8MA\u0004O_RD\u0017N\\4\u0005\u000b}<(\u0019\u0001>\u0003\t}#C%\r\t\u0004m\u0006\rAABA\u0003\u0005\t\u0007!PA\u0001C\u0011\u001d\tIA\u0001a\u0001\u0003\u0017\t!a\u001c9\u0011\u0013%\fi!!\u0001\u0002\u0012\u0005\u0005\u0011bAA\bG\nIa)\u001e8di&|gN\r\t\u0004m\u0006MAaBA\u000b\u0001\u0011\u0015\rA\u001f\u0002\u0002\u0003\"9\u0011\u0011\u0004\u0002A\u0002\u0005\u0005\u0011!\u0001>\u0002\r\u0019LG\u000e^3s)\u0011\ty\"!\n\u0011\u0007Y\f\t\u0003B\u0004\u0002$\u0001!)\u0019\u0001>\u0003\u0003\rCq!a\n\u0004\u0001\u0004\tI#A\u0001q!\u001dI\u00171FA\t\u0003_I1!!\fd\u0005%1UO\\2uS>t\u0017\u0007E\u0002j\u0003cI1!a\rd\u0005\u001d\u0011un\u001c7fC:\f\u0011BZ5mi\u0016\u0014hj\u001c;\u0015\t\u0005}\u0011\u0011\b\u0005\b\u0003w!\u0001\u0019AA\u0015\u0003\u0011\u0001(/\u001a3\u0002\tQ\f7.\u001a\u000b\u0005\u0003?\t\t\u0005C\u0004\u0002D\u0015\u0001\r!!\u0012\u0002\u00039\u00042![A$\u0013\r\tIe\u0019\u0002\u0004\u0013:$\u0018!\u0003;bW\u0016<\u0006.\u001b7f)\u0011\ty\"a\u0014\t\u000f\u0005\u001db\u00011\u0001\u0002*\u0005!AM]8q)\u0011\ty\"!\u0016\t\u000f\u0005\rs\u00011\u0001\u0002F\u0005IAM]8q/\"LG.\u001a\u000b\u0005\u0003?\tY\u0006C\u0004\u0002(!\u0001\r!!\u000b\u0002\u000bMd\u0017nY3\u0015\r\u0005}\u0011\u0011MA3\u0011\u001d\t\u0019'\u0003a\u0001\u0003\u000b\nAA\u001a:p[\"9\u0011qM\u0005A\u0002\u0005\u0015\u0013!B;oi&d\u0017aA7baV!\u0011QNA:)\u0011\ty'!\u001e\u0011\tY<\u0018\u0011\u000f\t\u0004m\u0006MDABA\u0003\u0015\t\u0007!\u0010C\u0004\u0002x)\u0001\r!!\u001f\u0002\u0003\u0019\u0004r![A\u0016\u0003#\t\t(A\u0004gY\u0006$X*\u00199\u0016\t\u0005}\u0014Q\u0011\u000b\u0005\u0003\u0003\u000b9\t\u0005\u0003wo\u0006\r\u0005c\u0001<\u0002\u0006\u00121\u0011QA\u0006C\u0002iDq!a\u001e\f\u0001\u0004\tI\tE\u0004j\u0003W\t\t\"a#\u0011\r\u00055\u0015qRAB\u001b\u0005\t\u0017bAAIC\na\u0011\n^3sC\ndWm\u00148dK\u00069a\r\\1ui\u0016tW\u0003BAL\u0003;#B!!'\u0002 B!ao^AN!\r1\u0018Q\u0014\u0003\u0007\u0003\u000ba!\u0019\u0001>\t\u000f\u0005\u0005F\u0002q\u0001\u0002$\u0006Q\u0011m]%uKJ\f'\r\\3\u0011\u000f%\fY#!\u0005\u0002&B1\u0011QRAH\u00037\u000bqaY8mY\u0016\u001cG/\u0006\u0003\u0002,\u0006EF\u0003BAW\u0003g\u0003BA^<\u00020B\u0019a/!-\u0005\r\u0005\u0015QB1\u0001{\u0011\u001d\t),\u0004a\u0001\u0003o\u000b!\u0001\u001d4\u0011\u000f%\fI,!\u0005\u00020&\u0019\u00111X2\u0003\u001fA\u000b'\u000f^5bY\u001a+hn\u0019;j_:\fAB_5q/&$\b.\u00138eKb,\"!!1\u0011\tY<\u00181\u0019\t\bS\u0006\u0015\u0017\u0011ZA#\u0013\r\t9m\u0019\u0002\u0007)V\u0004H.\u001a\u001a+\t\u0005E\u00111Z\u0016\u0003\u0003\u001b\u0004B!a4\u0002Z6\u0011\u0011\u0011\u001b\u0006\u0005\u0003'\f).A\u0005v]\u000eDWmY6fI*\u0019\u0011q[2\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\\\u0006E'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!1\u000f]1o)\u0011\t\t/a9\u0011\u000f%\f)-a\b\u0002 !9\u0011qE\bA\u0002\u0005%\u0012aB:qY&$\u0018\t\u001e\u000b\u0005\u0003C\fI\u000fC\u0004\u0002DA\u0001\r!!\u0012\u0002\u000fQ\f\u0007/R1dQV!\u0011q^A|)\u0011\ty\"!=\t\u000f\u0005]\u0014\u00031\u0001\u0002tB9\u0011.a\u000b\u0002\u0012\u0005U\bc\u0001<\u0002x\u00121\u0011\u0011`\tC\u0002i\u0014\u0011!V\u0001\u0010Q\u0006\u001cH)\u001a4j]&$XmU5{KV\u0011\u0011q\u0006\u0015\f%\t\u0005!q\u0001B\u0005\u0005\u001b\u0011y\u0001E\u0002j\u0005\u0007I1A!\u0002d\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0011Y!A4DQ\u0016\u001c7\u000e\t\u0018l]><hnU5{K\u0002Jgn\u001d;fC\u0012\u0004sN\u001a\u0011/Q\u0006\u001cH)\u001a4j]&$XmU5{K\u00022wN\u001d\u0011n_J,\u0007%Y2uS>t\u0017M\u00197fA%tgm\u001c:nCRLwN\u001c\u0011)g\u0016,\u0007e]2bY\u0006$wn\u0019\u0011g_J\u0004C-\u001a;bS2\u001c\u0018&A\u0003tS:\u001cW-\t\u0002\u0003\u0012\u00051!GL\u00194]A\n!#[:Ue\u00064XM]:bE2,\u0017iZ1j]\u00069am\u001c:fC\u000eDW\u0003\u0002B\r\u0005C!2A\u001cB\u000e\u0011\u001d\t9\b\u0006a\u0001\u0005;\u0001r![A\u0016\u0003#\u0011y\u0002E\u0002w\u0005C!a!!?\u0015\u0005\u0004Q\u0018A\u00024pe\u0006dG\u000e\u0006\u0003\u00020\t\u001d\u0002bBA\u0014+\u0001\u0007\u0011\u0011F\u0001\u0007KbL7\u000f^:\u0015\t\u0005=\"Q\u0006\u0005\b\u0003O1\u0002\u0019AA\u0015\u0003\u0015\u0019w.\u001e8u)\u0011\t)Ea\r\t\u000f\u0005\u001dr\u00031\u0001\u0002*\u0005!a-\u001b8e)\u0011\u0011IDa\u0010\u0011\u000b%\u0014Y$!\u0005\n\u0007\tu2M\u0001\u0004PaRLwN\u001c\u0005\b\u0003OA\u0002\u0019AA\u0015\u0003\u00151w\u000e\u001c3m+\u0019\u0011)Ea\u0016\u0003JQQ!q\tB&\u0005;\u0012\tGa\u0019\u0011\u0007Y\u0014I\u0005\u0002\u0004\u0002\u0006e\u0011\rA\u001f\u0005\b\u0005\u001bJ\u0002\u0019\u0001B(\u0003\r\u0019X-\u001d\t\u0007\u0003\u001b\u0013\tF!\u0016\n\u0007\tM\u0013M\u0001\u0006J]\u0012,\u00070\u001a3TKF\u00042A\u001eB,\t\u001d\u0011I&\u0007b\u0001\u00057\u0012\u0011\u0001W\t\u0004\u0003#A\u0007b\u0002B03\u0001\u0007\u0011QI\u0001\u0006gR\f'\u000f\u001e\u0005\b\u00033I\u0002\u0019\u0001B$\u0011\u001d\tI!\u0007a\u0001\u0005K\u0002\u0012\"[A\u0007\u0005\u000f\u0012)Fa\u0012\u0002\u000b\u0019|G\u000e\u001a:\u0016\r\t-$Q\u000fB8)\u0019\u0011iGa\u001e\u0003|A\u0019aOa\u001c\u0005\u000f\u0005\u0015!D1\u0001\u0003rE\u0019!1\u000f5\u0011\u0007Y\u0014)\bB\u0004\u0003Zi\u0011\rAa\u0017\t\u000f\t5#\u00041\u0001\u0003zA1\u0011Q\u0012B)\u0005gBq!!\u0003\u001b\u0001\u0004\u0011i\bE\u0005j\u0003\u001b\u0011\u0019H!\u001c\u0003n\u0005Aam\u001c7e\u0019\u00164G/\u0006\u0003\u0003\u0004\n%E\u0003\u0002BC\u0005\u001f#BAa\"\u0003\fB\u0019aO!#\u0005\r\u0005\u00151D1\u0001{\u0011\u001d\tIa\u0007a\u0001\u0005\u001b\u0003\u0012\"[A\u0007\u0005\u000f\u000b\tBa\"\t\u000f\u0005e1\u00041\u0001\u0003\b\u0006Iam\u001c7e%&<\u0007\u000e^\u000b\u0005\u0005+\u0013Y\n\u0006\u0003\u0003\u0018\n\u0005F\u0003\u0002BM\u0005;\u00032A\u001eBN\t\u0019\t)\u0001\bb\u0001u\"9\u0011\u0011\u0002\u000fA\u0002\t}\u0005#C5\u0002\u000e\u0005E!\u0011\u0014BM\u0011\u001d\tI\u0002\ba\u0001\u00053\u000b!\u0002\n3jm\u0012\u001aw\u000e\\8o+\u0011\u00119K!,\u0015\t\t%&1\u0017\u000b\u0005\u0005W\u0013y\u000bE\u0002w\u0005[#a!!\u0002\u001e\u0005\u0004Q\bbBA\u0005;\u0001\u0007!\u0011\u0017\t\nS\u00065!1VA\t\u0005WCq!!\u0007\u001e\u0001\u0004\u0011Y\u000bK\u0006\u001e\u0005\u0003\u00119Aa.\u0003\u000e\t=\u0011E\u0001B]\u0003i)6/\u001a\u0011g_2$G*\u001a4uA%t7\u000f^3bI\u0002zg\rI\u0018;Q\ri\"Q\u0018\t\u0004S\n}\u0016b\u0001BaG\n1\u0011N\u001c7j]\u0016\fQ\u0002J2pY>tGEY:mCNDW\u0003\u0002Bd\u0005\u001b$BA!3\u0003TR!!1\u001aBh!\r1(Q\u001a\u0003\u0007\u0003\u000bq\"\u0019\u0001>\t\u000f\u0005%a\u00041\u0001\u0003RBI\u0011.!\u0004\u0002\u0012\t-'1\u001a\u0005\b\u00033q\u0002\u0019\u0001BfQ-q\"\u0011\u0001B\u0004\u0005/\u0014iAa\u0004\"\u0005\te\u0017aG+tK\u00022w\u000e\u001c3SS\u001eDG\u000fI5ogR,\u0017\r\u001a\u0011pM\u0002RD\fK\u0002\u001f\u0005{\u000bAAZ8mIV!!\u0011\u001dBt)\u0011\u0011\u0019Oa<\u0015\t\t\u0015(1\u001e\t\u0004m\n\u001dHa\u0002Bu?\t\u0007!1\f\u0002\u0003\u0003FBq!!\u0003 \u0001\u0004\u0011i\u000fE\u0005j\u0003\u001b\u0011)O!:\u0003f\"9\u0011\u0011D\u0010A\u0002\t\u0015\u0018A\u0002:fIV\u001cW-\u0006\u0003\u0003v\neH\u0003\u0002B|\u0005w\u00042A\u001eB}\t\u001d\t)\u0001\tb\u0001\u00057Bq!!\u0003!\u0001\u0004\u0011i\u0010E\u0005j\u0003\u001b\u00119Pa>\u0003x\u0006a!/\u001a3vG\u0016|\u0005\u000f^5p]V!11AB\u0005)\u0011\u0019)aa\u0003\u0011\u000b%\u0014Yda\u0002\u0011\u0007Y\u001cI\u0001B\u0004\u0002\u0006\u0005\u0012\rAa\u0017\t\u000f\u0005%\u0011\u00051\u0001\u0004\u000eAI\u0011.!\u0004\u0004\b\r\u001d1qA\u0001\u000be\u0016$WoY3MK\u001a$X\u0003BB\n\u0007/!Ba!\u0006\u0004\u001aA\u0019aoa\u0006\u0005\u000f\u0005\u0015!E1\u0001\u0003\\!9\u0011\u0011\u0002\u0012A\u0002\rm\u0001#C5\u0002\u000e\rU\u0011\u0011CB\u000b\u0003I\u0011X\rZ;dK2+g\r^%uKJ\fGo\u001c:\u0016\t\r\u00052q\u0005\u000b\u0005\u0007G\u0019i\u0003\u0006\u0003\u0004&\r%\u0002c\u0001<\u0004(\u00119\u0011QA\u0012C\u0002\tm\u0003bBA\u0005G\u0001\u000711\u0006\t\nS\u000651QEA\t\u0007KA\u0001ba\f$\t\u0003\u00071\u0011G\u0001\b_:,U\u000e\u001d;z!\u0015I71GB\u0013\u0013\r\u0019)d\u0019\u0002\ty\tLh.Y7f}\u0005Y!/\u001a3vG\u0016\u0014\u0016n\u001a5u+\u0011\u0019Yda\u0010\u0015\t\ru2\u0011\t\t\u0004m\u000e}BaBA\u0003I\t\u0007!1\f\u0005\b\u0003\u0013!\u0003\u0019AB\"!%I\u0017QBA\t\u0007{\u0019i$\u0001\tsK\u0012,8-\u001a'fMR|\u0005\u000f^5p]V!1\u0011JB()\u0011\u0019Ye!\u0015\u0011\u000b%\u0014Yd!\u0014\u0011\u0007Y\u001cy\u0005B\u0004\u0002\u0006\u0015\u0012\rAa\u0017\t\u000f\u0005%Q\u00051\u0001\u0004TAI\u0011.!\u0004\u0004N\u0005E1QJ\u0001\u0019e\u0016$WoY3MK\u001a$x\n\u001d;j_:LE/\u001a:bi>\u0014X\u0003BB-\u0007?\"Baa\u0017\u0004bA)\u0011Na\u000f\u0004^A\u0019aoa\u0018\u0005\u000f\u0005\u0015aE1\u0001\u0003\\!9\u0011\u0011\u0002\u0014A\u0002\r\r\u0004#C5\u0002\u000e\ru\u0013\u0011CB/\u0003Q\u0011X\rZ;dK>\u0003H/[8o\u0013R,'/\u0019;peV11\u0011NB<\u0007c\"Baa\u001b\u0004~Q!1QNB=!\u0015I'1HB8!\r18\u0011\u000f\u0003\b\u0003\u000b9#\u0019AB:#\r\u0019)\b\u001b\t\u0004m\u000e]Da\u0002B-O\t\u0007!1\f\u0005\b\u0003\u00139\u0003\u0019AB>!%I\u0017QBB8\u0007k\u001ay\u0007C\u0004\u0004\u0000\u001d\u0002\ra!!\u0002\u0005%$\bCBAG\u0007\u0007\u001b)(C\u0002\u0004\u0006\u0006\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\u0012e\u0016$WoY3SS\u001eDGo\u00149uS>tW\u0003BBF\u0007##Ba!$\u0004\u0014B)\u0011Na\u000f\u0004\u0010B\u0019ao!%\u0005\u000f\u0005\u0015\u0001F1\u0001\u0003\\!9\u0011\u0011\u0002\u0015A\u0002\rU\u0005#C5\u0002\u000e\u0005E1qRBH\u0003\u001dI7/R7qif\f\u0001B\\8o\u000b6\u0004H/\u001f\u0015\fU\ru%qABR\u0005\u001b\u0011y\u0001E\u0002j\u0007?K1a!)d\u0005Q!W\r\u001d:fG\u0006$X\rZ(wKJ\u0014\u0018\u000eZ5oO\u0006\u00121QU\u0001:]>tW)\u001c9us\u0002J7\u000f\t3fM&tW\r\u001a\u0011bg\u0002\n\u0013n]#naRL8\bI8wKJ\u0014\u0018\u000eZ3!SN,U\u000e\u001d;zA%t7\u000f^3bI\u0006!1/\u001b>f+\t\t)%\u0001\u0007d_BLHk\u001c\"vM\u001a,'/\u0006\u0003\u00040\u000e\rGc\u00018\u00042\"911\u0017\u0017A\u0002\rU\u0016\u0001\u00023fgR\u0004baa.\u0004>\u000e\u0005WBAB]\u0015\r\u0019Y,Y\u0001\b[V$\u0018M\u00197f\u0013\u0011\u0019yl!/\u0003\r\t+hMZ3s!\r181\u0019\u0003\b\u0003\u000ba#\u0019\u0001B.Q-a#\u0011\u0001B\u0004\u0007\u000f\u0014iAa\u0004\"\u0005\r%\u0017aG+tK\u0002\u0002G-Z:uA-ZS\bI2pY2\u0004\u0007%\u001b8ti\u0016\fG\rK\u0002-\u0005{\u000b1bY8qsR{\u0017I\u001d:bsV!1\u0011[Bp)\u0011\t)ea5\t\u000f\rUW\u00061\u0001\u0004X\u0006\u0011\u0001p\u001d\t\u0006S\u000ee7Q\\\u0005\u0004\u00077\u001c'!B!se\u0006L\bc\u0001<\u0004`\u00129\u0011QA\u0017C\u0002\tm\u0003fC\u0017\u0004\u001e\n\u001d11\u001dB\u0007\u0007O\f#a!:\u0002}QC\u0017n\u001d\u0011tQ>,H\u000e\u001a\u0011bY^\f\u0017p\u001d\u0011g_J<\u0018M\u001d3!i>\u0004C\u000f[3!g5\n'o\u001a\u0011wKJ\u001c\u0018n\u001c8!_\u001a\u0004C\u000f[5tA5,G\u000f[8eC\t\u0019I/\u0001\u00043]E\u001ad\u0006N\u000b\u0005\u0007[\u001c)\u0010\u0006\u0004\u0002F\r=8q\u001f\u0005\b\u0007+t\u0003\u0019ABy!\u0015I7\u0011\\Bz!\r18Q\u001f\u0003\b\u0003\u000bq#\u0019\u0001B.\u0011\u001d\u0011yF\fa\u0001\u0003\u000bB3BLBO\u0005\u000f\u0019\u0019O!\u0004\u0004hV!1Q C\u0003)!\t)ea@\u0005\b\u0011%\u0001bBBk_\u0001\u0007A\u0011\u0001\t\u0006S\u000eeG1\u0001\t\u0004m\u0012\u0015AaBA\u0003_\t\u0007!1\f\u0005\b\u0005?z\u0003\u0019AA#\u0011\u001d!Ya\fa\u0001\u0003\u000b\n1\u0001\\3o\u0003\r\u0019X/\\\u000b\u0005\t#!)\u0002\u0006\u0003\u0005\u0014\u0011]\u0001c\u0001<\u0005\u0016\u00119\u0011Q\u0001\u0019C\u0002\tm\u0003b\u0002C\ra\u0001\u000fA1D\u0001\u0004]Vl\u0007C\u0002C\u000f\tG!\u0019\"\u0004\u0002\u0005 )\u0019A\u0011E2\u0002\t5\fG\u000f[\u0005\u0005\tK!yBA\u0004Ok6,'/[2\u0002\u000fA\u0014x\u000eZ;diV!A1\u0006C\u0018)\u0011!i\u0003\"\r\u0011\u0007Y$y\u0003B\u0004\u0002\u0006E\u0012\rAa\u0017\t\u000f\u0011e\u0011\u0007q\u0001\u00054A1AQ\u0004C\u0012\t[\t1!\\5o+\u0011!I\u0004b\u0012\u0015\t\u0005EA1\b\u0005\b\t{\u0011\u00049\u0001C \u0003\ry'\u000f\u001a\t\u0007\t;!\t\u0005\"\u0012\n\t\u0011\rCq\u0004\u0002\t\u001fJ$WM]5oOB\u0019a\u000fb\u0012\u0005\u000f\u0005\u0015!G1\u0001\u0003\\\u0005IQ.\u001b8PaRLwN\\\u000b\u0005\t\u001b\")\u0006\u0006\u0003\u0003:\u0011=\u0003b\u0002C\u001fg\u0001\u000fA\u0011\u000b\t\u0007\t;!\t\u0005b\u0015\u0011\u0007Y$)\u0006B\u0004\u0002\u0006M\u0012\rAa\u0017\u0002\u00075\f\u00070\u0006\u0003\u0005\\\u0011\rD\u0003BA\t\t;Bq\u0001\"\u00105\u0001\b!y\u0006\u0005\u0004\u0005\u001e\u0011\u0005C\u0011\r\t\u0004m\u0012\rDaBA\u0003i\t\u0007!1L\u0001\n[\u0006Dx\n\u001d;j_:,B\u0001\"\u001b\u0005rQ!!\u0011\bC6\u0011\u001d!i$\u000ea\u0002\t[\u0002b\u0001\"\b\u0005B\u0011=\u0004c\u0001<\u0005r\u00119\u0011QA\u001bC\u0002\tm\u0013!B7bq\nKX\u0003\u0002C<\t\u0003#B\u0001\"\u001f\u0005\u0004R!\u0011\u0011\u0003C>\u0011\u001d!iD\u000ea\u0002\t{\u0002b\u0001\"\b\u0005B\u0011}\u0004c\u0001<\u0005\u0002\u00121\u0011Q\u0001\u001cC\u0002iDq!a\u001e7\u0001\u0004!)\tE\u0004j\u0003W\t\t\u0002b \u0003\u00135\u000b\u00070[7ju\u0016$WC\u0002CF\t?#\u0019kE\u00028\t\u001b\u0003\"\u0002b$\u0005\u0016\u0012eEQ\u0014CM\u001b\t!\tJC\u0002\u0005\u0014\u000e\fqA];oi&lW-\u0003\u0003\u0005\u0018\u0012E%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeA9A1T\u001c\u0005\u001e\u0012\u0005V\"\u0001\u0001\u0011\u0007Y$y\n\u0002\u0004\u0003Z]\u0012\rA\u001f\t\u0004m\u0012\rFABA\u0003o\t\u0007!0\u0001\u0006eKN\u001c'/\u001b9u_J\u0004B\u0001\"+\u00058:!A1\u0016CZ!\r!ikY\u0007\u0003\t_S1\u0001\"-f\u0003\u0019a$o\\8u}%\u0019AQW2\u0002\rA\u0013X\rZ3g\u0013\u0011!I\fb/\u0003\rM#(/\u001b8h\u0015\r!)l\u0019\t\bS\u0006-BQ\u0014CQ\u0003\r\u0019W\u000e\u001d\t\nS\u00065A\u0011\u0015CQ\u0003_\ta\u0001P5oSRtD\u0003\u0002Cd\t\u001f$B\u0001\"3\u0005NR!A\u0011\u0014Cf\u0011\u001d!yl\u000fa\u0001\t\u0003Dq!a\u001e<\u0001\u0004!i\fC\u0004\u0005&n\u0002\r\u0001b*\u0002\u000f5\f\u00070\u00127f[V\u0011AQT\u0001\f[\u0006DX\t\\3n?\u0012*\u0017\u000fF\u0002o\t3D\u0011\u0002b7>\u0003\u0003\u0005\r\u0001\"(\u0002\u0007a$\u0013'\u0001\u0005nCb,E.Z7!\u0003\u0011i\u0017\r\u001f$\u0016\u0005\u0011\u0005\u0016\u0001C7bq\u001a{F%Z9\u0015\u00079$9\u000fC\u0005\u0005\\\u0002\u000b\t\u00111\u0001\u0005\"\u0006)Q.\u0019=GA\u0005aan\u001c8F[B$\u0018p\u0018\u0013fcR\u0019a\u000eb<\t\u0013\u0011m7)!AA\u0002\u0005=\u0012!\u00038p]\u0016k\u0007\u000f^=!\u0003!!xn\u00149uS>tWC\u0001C|!\u0015I'1\bCO\u0003\u0019\u0011Xm];mi\u0006)\u0011\r\u001d9msR1A\u0011\u0014C\u0000\u000b\u0007Aq!\"\u0001H\u0001\u0004!I*A\u0001n\u0011\u001d))a\u0012a\u0001\t;\u000b\u0011!Y\u0001\f[\u0006D()_(qi&|g.\u0006\u0003\u0006\f\u0015UA\u0003BC\u0007\u000b/!BA!\u000f\u0006\u0010!9AQ\b%A\u0004\u0015E\u0001C\u0002C\u000f\t\u0003*\u0019\u0002E\u0002w\u000b+!a!!\u0002I\u0005\u0004Q\bbBA<\u0011\u0002\u0007Q\u0011\u0004\t\bS\u0006-\u0012\u0011CC\n\u0003\u0015i\u0017N\u001c\"z+\u0011)y\"\"\u000b\u0015\t\u0015\u0005R1\u0006\u000b\u0005\u0003#)\u0019\u0003C\u0004\u0005>%\u0003\u001d!\"\n\u0011\r\u0011uA\u0011IC\u0014!\r1X\u0011\u0006\u0003\u0007\u0003\u000bI%\u0019\u0001>\t\u000f\u0005]\u0014\n1\u0001\u0006.A9\u0011.a\u000b\u0002\u0012\u0015\u001d\u0012aC7j]\nKx\n\u001d;j_:,B!b\r\u0006>Q!QQGC )\u0011\u0011I$b\u000e\t\u000f\u0011u\"\nq\u0001\u0006:A1AQ\u0004C!\u000bw\u00012A^C\u001f\t\u0019\t)A\u0013b\u0001u\"9\u0011q\u000f&A\u0002\u0015\u0005\u0003cB5\u0002,\u0005EQ1H\u0001\rG>dG.Z2u\r&\u00148\u000f^\u000b\u0005\u000b\u000f*i\u0005\u0006\u0003\u0006J\u0015=\u0003#B5\u0003<\u0015-\u0003c\u0001<\u0006N\u00111\u0011QA&C\u0002iDq!!.L\u0001\u0004)\t\u0006E\u0004j\u0003s\u000b\t\"b\u0013\u0002\u0013\u0005<wM]3hCR,W\u0003BC,\u000b;\"B!\"\u0017\u0006lQ1Q1LC0\u000bK\u00022A^C/\t\u0019\t)\u0001\u0014b\u0001u\"9Q\u0011\r'A\u0002\u0015\r\u0014!B:fc>\u0004\b#C5\u0002\u000e\u0015m\u0013\u0011CC.\u0011\u001d)9\u0007\u0014a\u0001\u000bS\naaY8nE>\u0004\b#C5\u0002\u000e\u0015mS1LC.\u0011!\tI\u0002\u0014CA\u0002\u00155\u0004#B5\u00044\u0015m\u0003f\u0003'\u0003\u0002\t\u001dQ\u0011\u000fB\u0007\u0005\u001f\t#!b\u001d\u0002g\u001a{'\u000fI:fcV,g\u000e^5bY\u0002\u001aw\u000e\u001c7fGRLwN\\:-AA\u0014XMZ3sA\u00014w\u000e\u001c3MK\u001a$\bF_\u0015)g\u0016\fx\u000e]\u0015a]\u00012uN\u001d\u0011qCJ\fG\u000e\\3mA\r|G\u000e\\3di&|gn\u001d\u0017!kN,\u0007\u0005\u0019)be&#XM]1cY\u0016d\u0015n[3$C\u001e<'/Z4bi\u0016\u0004g&A\u0006d_J\u0014Xm\u001d9p]\u0012\u001cX\u0003BC=\u000b\u0007#B!b\u001f\u0006\u0006R!\u0011qFC?\u0011\u001d\t9#\u0014a\u0001\u000b\u007f\u0002\u0012\"[A\u0007\u0003#)\t)a\f\u0011\u0007Y,\u0019\t\u0002\u0004\u0002\u00065\u0013\rA\u001f\u0005\b\u000b\u000fk\u0005\u0019ACE\u0003\u0011!\b.\u0019;\u0011\r\u00055\u0015qRCA\u0003!i7n\u0015;sS:<G\u0003\u0003CT\u000b\u001f+\t*\"&\t\u000f\t}c\n1\u0001\u0005(\"9Q1\u0013(A\u0002\u0011\u001d\u0016aA:fa\"9Qq\u0013(A\u0002\u0011\u001d\u0016aA3oIR!AqUCN\u0011\u001d)\u0019j\u0014a\u0001\tOC3a\u0014B_+\t!9\u000bK\u0002Q\u0005{\u000b\u0011\"\u00193e'R\u0014\u0018N\\4\u0015\u0015\u0015\u001dV\u0011VCZ\u000bk+9LD\u0002w\u000bSCq!b+R\u0001\u0004)i+A\u0001c!\u0011\u00199,b,\n\t\u0015E6\u0011\u0018\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000f\t}\u0013\u000b1\u0001\u0005(\"9Q1S)A\u0002\u0011\u001d\u0006bBCL#\u0002\u0007Aq\u0015\u000b\u0007\u000bw+i,b0\u000f\u0007Y,i\fC\u0004\u0006,J\u0003\r!\",\t\u000f\u0015M%\u000b1\u0001\u0005(\"\u001a!K!0\u0015\t\u0015\u0015Wq\u0019\b\u0004m\u0016\u001d\u0007bBCV'\u0002\u0007QQ\u0016\u0015\u0004'\nu\u0016A\u0001;p+\u0011)y-b5\u0015\t\u0015EWq\u001b\t\u0004m\u0016MGABCk)\n\u0007!P\u0001\u0002Dc!9Q\u0011\u001c+A\u0002\u0015m\u0017a\u00024bGR|'/\u001f\t\t\u0003\u001b+i.!\u0005\u0006R&\u0019Qq\\1\u0003\u000f\u0019\u000b7\r^8ss\u0006QAo\\%uKJ\fGo\u001c:\u0016\u0005\u0015\u0015\bCBAG\u0007\u0007\u000b\t\u0002K\u0006V\u0005\u0003\u00119!\";\u0003\u000e\t=\u0011EACv\u0003\u0011*6/\u001a\u0011/SR,'/\u0019;pe\u0002Jgn\u001d;fC\u0012\u0004sN\u001a\u0011/i>LE/\u001a:bi>\u0014\bfA+\u0003>\u00061Ao\u001c'jgR,\"!b=\u0011\r\u0015UX1`A\t\u001b\t)9PC\u0002\u0006z\u0006\f\u0011\"[7nkR\f'\r\\3\n\t\u0015uXq\u001f\u0002\u0005\u0019&\u001cH/\u0001\u0005u_Z+7\r^8s+\t1\u0019\u0001\u0005\u0004\u0006v\u001a\u0015\u0011\u0011C\u0005\u0005\r\u000f)9P\u0001\u0004WK\u000e$xN]\u0001\u0006i>l\u0015\r]\u000b\u0007\r\u001b19B\"\b\u0015\t\u0019=a\u0011\u0005\t\t\u000bk4\tB\"\u0006\u0007\u001c%!a1CC|\u0005\ri\u0015\r\u001d\t\u0004m\u001a]AA\u0002D\r1\n\u0007!PA\u0001L!\r1hQ\u0004\u0003\u0007\r?A&\u0019\u0001>\u0003\u0003YCqAb\tY\u0001\b1)#\u0001\u0002fmB9\u0011Nb\n\u0002\u0012\u0019-\u0012b\u0001D\u0015G\n\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\bS\u0006\u0015gQ\u0003D\u000e\u0003\u0015!xnU3u+\u00111\tDb\u000f\u0016\u0005\u0019M\u0002CBC{\rk1I$\u0003\u0003\u00078\u0015](aA*fiB\u0019aOb\u000f\u0005\u000f\u0005\u0015\u0011L1\u0001\u0003\\\u0005)Ao\\*fcV\u0011a\u0011\t\t\u0007\u000bk4\u0019%!\u0005\n\t\u0019\u0015Sq\u001f\u0002\u0004'\u0016\f\u0018\u0001\u0004;p\u0013:$W\r_3e'\u0016\fXC\u0001D&!\u0019))P\"\u0014\u0002\u0012%!!1KC|\u0003!!xn\u0015;sK\u0006lWC\u0001D*!\u0019))P\"\u0016\u0002\u0012%!aqKC|\u0005\u0019\u0019FO]3b[\"ZAL!\u0001\u0003\b\u0019m#Q\u0002B\bC\t1i&\u0001\u0014Vg\u0016\u0004c\u0006^8)\u0019\u0006T\u0018\u0010T5ti&\u0002\u0013N\\:uK\u0006$\u0007e\u001c4!]Q|7\u000b\u001e:fC6D3\u0001\u0018B_\u0003!!xNQ;gM\u0016\u0014X\u0003\u0002D3\rW*\"Ab\u001a\u0011\r\r]6Q\u0018D5!\r1h1\u000e\u0003\b\u0003\u000bi&\u0019\u0001B.Q\ri&QX\u0001\bi>\f%O]1z+\u00111\u0019H\"\u001f\u0015\t\u0019Ud1\u0010\t\u0006S\u000eegq\u000f\t\u0004m\u001aeDaBA\u0003=\n\u0007!1\f\u0005\n\r{r\u0016\u0011!a\u0002\r\u007f\n!\"\u001a<jI\u0016t7-\u001a\u00133!\u00191\tIb\"\u0007x5\u0011a1\u0011\u0006\u0004\r\u000b\u001b\u0017a\u0002:fM2,7\r^\u0005\u0005\r\u00133\u0019I\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003!\u0011XM^3sg\u0016$WC\u0001DH!\u0019\tiI\"%\u0002\u0012%\u0019a1S1\u0003\u0011%#XM]1cY\u0016\u0014bAb&\u0007\u001c\u001a}eA\u0002DM\u0001\u00011)J\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0005\u0002\u000e\u0002\t\tB\"(\u0002 A\u0011ao\u001e\t\u0007\u0003\u001b\u000by)!\u0005"
)
public interface IterableOnceOps {
   Object scanLeft(final Object z, final Function2 op);

   Object filter(final Function1 p);

   Object filterNot(final Function1 pred);

   Object take(final int n);

   Object takeWhile(final Function1 p);

   Object drop(final int n);

   Object dropWhile(final Function1 p);

   Object slice(final int from, final int until);

   Object map(final Function1 f);

   Object flatMap(final Function1 f);

   Object flatten(final Function1 asIterable);

   Object collect(final PartialFunction pf);

   Object zipWithIndex();

   Tuple2 span(final Function1 p);

   // $FF: synthetic method
   static Tuple2 splitAt$(final IterableOnceOps $this, final int n) {
      return $this.splitAt(n);
   }

   default Tuple2 splitAt(final int n) {
      class Spanner$1 extends AbstractFunction1 {
         private int i;
         private final int n$1;

         public int i() {
            return this.i;
         }

         public void i_$eq(final int x$1) {
            this.i = x$1;
         }

         public boolean apply(final Object a) {
            if (this.i() < this.n$1) {
               this.i_$eq(this.i() + 1);
               return true;
            } else {
               return false;
            }
         }

         public Spanner$1(final int n$1) {
            this.n$1 = n$1;
            this.i = 0;
         }
      }

      Spanner$1 spanner = new Spanner$1(n);
      return this.span(spanner);
   }

   Object tapEach(final Function1 f);

   // $FF: synthetic method
   static boolean hasDefiniteSize$(final IterableOnceOps $this) {
      return $this.hasDefiniteSize();
   }

   /** @deprecated */
   default boolean hasDefiniteSize() {
      return true;
   }

   // $FF: synthetic method
   static boolean isTraversableAgain$(final IterableOnceOps $this) {
      return $this.isTraversableAgain();
   }

   default boolean isTraversableAgain() {
      return false;
   }

   // $FF: synthetic method
   static void foreach$(final IterableOnceOps $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      Iterator it = ((IterableOnce)this).iterator();

      while(it.hasNext()) {
         f.apply(it.next());
      }

   }

   // $FF: synthetic method
   static boolean forall$(final IterableOnceOps $this, final Function1 p) {
      return $this.forall(p);
   }

   default boolean forall(final Function1 p) {
      boolean res = true;

      for(Iterator it = ((IterableOnce)this).iterator(); res && it.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
      }

      return res;
   }

   // $FF: synthetic method
   static boolean exists$(final IterableOnceOps $this, final Function1 p) {
      return $this.exists(p);
   }

   default boolean exists(final Function1 p) {
      boolean res = false;

      for(Iterator it = ((IterableOnce)this).iterator(); !res && it.hasNext(); res = BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
      }

      return res;
   }

   // $FF: synthetic method
   static int count$(final IterableOnceOps $this, final Function1 p) {
      return $this.count(p);
   }

   default int count(final Function1 p) {
      int res = 0;
      Iterator it = ((IterableOnce)this).iterator();

      while(it.hasNext()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(it.next()))) {
            ++res;
         }
      }

      return res;
   }

   // $FF: synthetic method
   static Option find$(final IterableOnceOps $this, final Function1 p) {
      return $this.find(p);
   }

   default Option find(final Function1 p) {
      Iterator it = ((IterableOnce)this).iterator();

      while(it.hasNext()) {
         Object a = it.next();
         if (BoxesRunTime.unboxToBoolean(p.apply(a))) {
            return new Some(a);
         }
      }

      return None$.MODULE$;
   }

   private Object foldl(final IndexedSeq seq, final int start, final Object z, final Function2 op) {
      int var10001 = seq.length();
      Object loop$1_acc = z;
      int loop$1_end = var10001;

      int var10000;
      for(int loop$1_at = start; loop$1_at != loop$1_end; loop$1_at = var10000) {
         var10000 = loop$1_at + 1;
         loop$1_acc = op.apply(loop$1_acc, seq.apply(loop$1_at));
         loop$1_end = loop$1_end;
      }

      return loop$1_acc;
   }

   private Object foldr(final IndexedSeq seq, final Function2 op) {
      int var10000 = seq.length() - 1;
      Object loop$2_acc = seq.apply(seq.length() - 1);

      for(int loop$2_at = var10000; loop$2_at != 0; loop$2_at = var10000) {
         var10000 = loop$2_at - 1;
         loop$2_acc = op.apply(seq.apply(loop$2_at - 1), loop$2_acc);
      }

      return loop$2_acc;
   }

   // $FF: synthetic method
   static Object foldLeft$(final IterableOnceOps $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      if (this instanceof IndexedSeq) {
         IndexedSeq var3 = (IndexedSeq)this;
         int var10001 = var3.length();
         Object foldl_loop$1_acc = z;
         int foldl_loop$1_end = var10001;

         int var10000;
         for(int foldl_loop$1_at = 0; foldl_loop$1_at != foldl_loop$1_end; foldl_loop$1_at = var10000) {
            var10000 = foldl_loop$1_at + 1;
            foldl_loop$1_acc = op.apply(foldl_loop$1_acc, var3.apply(foldl_loop$1_at));
            foldl_loop$1_end = foldl_loop$1_end;
         }

         return foldl_loop$1_acc;
      } else {
         Object result = z;

         for(Iterator it = ((IterableOnce)this).iterator(); it.hasNext(); result = op.apply(result, it.next())) {
         }

         return result;
      }
   }

   // $FF: synthetic method
   static Object foldRight$(final IterableOnceOps $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      return this.reversed().foldLeft(z, (b, a) -> op.apply(a, b));
   }

   // $FF: synthetic method
   static Object $div$colon$(final IterableOnceOps $this, final Object z, final Function2 op) {
      return $this.$div$colon(z, op);
   }

   /** @deprecated */
   default Object $div$colon(final Object z, final Function2 op) {
      return this.foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object $colon$bslash$(final IterableOnceOps $this, final Object z, final Function2 op) {
      return $this.$colon$bslash(z, op);
   }

   /** @deprecated */
   default Object $colon$bslash(final Object z, final Function2 op) {
      return this.foldRight(z, op);
   }

   // $FF: synthetic method
   static Object fold$(final IterableOnceOps $this, final Object z, final Function2 op) {
      return $this.fold(z, op);
   }

   default Object fold(final Object z, final Function2 op) {
      return this.foldLeft(z, op);
   }

   // $FF: synthetic method
   static Object reduce$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduce(op);
   }

   default Object reduce(final Function2 op) {
      return this.reduceLeft(op);
   }

   // $FF: synthetic method
   static Option reduceOption$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduceOption(op);
   }

   default Option reduceOption(final Function2 op) {
      return this.reduceLeftOption(op);
   }

   // $FF: synthetic method
   static Object reduceLeft$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduceLeft(op);
   }

   default Object reduceLeft(final Function2 op) {
      if (this instanceof IndexedSeq) {
         IndexedSeq var2 = (IndexedSeq)this;
         if (var2.length() > 0) {
            Object foldl_z = var2.apply(0);
            int var10001 = var2.length();
            Object foldl_loop$1_acc = foldl_z;
            int foldl_loop$1_end = var10001;

            int var10000;
            for(int foldl_loop$1_at = 1; foldl_loop$1_at != foldl_loop$1_end; foldl_loop$1_at = var10000) {
               var10000 = foldl_loop$1_at + 1;
               foldl_loop$1_acc = op.apply(foldl_loop$1_acc, var2.apply(foldl_loop$1_at));
               foldl_loop$1_end = foldl_loop$1_end;
            }

            return foldl_loop$1_acc;
         }
      }

      if (((IterableOnce)this).knownSize() == 0) {
         throw new UnsupportedOperationException("empty.reduceLeft");
      } else {
         Iterator reduceLeftIterator_it = ((IterableOnce)this).iterator();
         if (!reduceLeftIterator_it.hasNext()) {
            throw new UnsupportedOperationException("empty.reduceLeft");
         } else {
            Object reduceLeftIterator_acc;
            for(reduceLeftIterator_acc = reduceLeftIterator_it.next(); reduceLeftIterator_it.hasNext(); reduceLeftIterator_acc = op.apply(reduceLeftIterator_acc, reduceLeftIterator_it.next())) {
            }

            return reduceLeftIterator_acc;
         }
      }
   }

   private Object reduceLeftIterator(final Function0 onEmpty, final Function2 op) {
      Iterator it = ((IterableOnce)this).iterator();
      if (!it.hasNext()) {
         return onEmpty.apply();
      } else {
         Object acc;
         for(acc = it.next(); it.hasNext(); acc = op.apply(acc, it.next())) {
         }

         return acc;
      }
   }

   // $FF: synthetic method
   static Object reduceRight$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduceRight(op);
   }

   default Object reduceRight(final Function2 op) {
      if (this instanceof IndexedSeq) {
         IndexedSeq var2 = (IndexedSeq)this;
         if (var2.length() > 0) {
            int var10000 = var2.length() - 1;
            Object foldr_loop$2_acc = var2.apply(var2.length() - 1);

            for(int foldr_loop$2_at = var10000; foldr_loop$2_at != 0; foldr_loop$2_at = var10000) {
               var10000 = foldr_loop$2_at - 1;
               foldr_loop$2_acc = op.apply(var2.apply(foldr_loop$2_at - 1), foldr_loop$2_acc);
            }

            return foldr_loop$2_acc;
         }
      }

      if (((IterableOnce)this).knownSize() == 0) {
         throw new UnsupportedOperationException("empty.reduceRight");
      } else {
         return this.reversed().reduceLeft((x, y) -> op.apply(y, x));
      }
   }

   // $FF: synthetic method
   static Option reduceLeftOption$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduceLeftOption(op);
   }

   default Option reduceLeftOption(final Function2 op) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceLeftOptionIterator_reduceOptionIterator_it = ((IterableOnce)this).iterator();
            if (!reduceLeftOptionIterator_reduceOptionIterator_it.hasNext()) {
               return None$.MODULE$;
            }

            Object reduceLeftOptionIterator_reduceOptionIterator_acc;
            for(reduceLeftOptionIterator_reduceOptionIterator_acc = reduceLeftOptionIterator_reduceOptionIterator_it.next(); reduceLeftOptionIterator_reduceOptionIterator_it.hasNext(); reduceLeftOptionIterator_reduceOptionIterator_acc = op.apply(reduceLeftOptionIterator_reduceOptionIterator_acc, reduceLeftOptionIterator_reduceOptionIterator_it.next())) {
            }

            return new Some(reduceLeftOptionIterator_reduceOptionIterator_acc);
         case 0:
            return None$.MODULE$;
         default:
            return new Some(this.reduceLeft(op));
      }
   }

   private Option reduceLeftOptionIterator(final Function2 op) {
      Iterator reduceOptionIterator_it = ((IterableOnce)this).iterator();
      if (!reduceOptionIterator_it.hasNext()) {
         return None$.MODULE$;
      } else {
         Object reduceOptionIterator_acc;
         for(reduceOptionIterator_acc = reduceOptionIterator_it.next(); reduceOptionIterator_it.hasNext(); reduceOptionIterator_acc = op.apply(reduceOptionIterator_acc, reduceOptionIterator_it.next())) {
         }

         return new Some(reduceOptionIterator_acc);
      }
   }

   private Option reduceOptionIterator(final Iterator it, final Function2 op) {
      if (!it.hasNext()) {
         return None$.MODULE$;
      } else {
         Object acc;
         for(acc = it.next(); it.hasNext(); acc = op.apply(acc, it.next())) {
         }

         return new Some(acc);
      }
   }

   // $FF: synthetic method
   static Option reduceRightOption$(final IterableOnceOps $this, final Function2 op) {
      return $this.reduceRightOption(op);
   }

   default Option reduceRightOption(final Function2 op) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceOptionIterator_it = this.reversed().iterator();
            if (!reduceOptionIterator_it.hasNext()) {
               return None$.MODULE$;
            }

            Object reduceOptionIterator_acc;
            Object var4;
            for(reduceOptionIterator_acc = reduceOptionIterator_it.next(); reduceOptionIterator_it.hasNext(); reduceOptionIterator_acc = op.apply(var4, reduceOptionIterator_acc)) {
               var4 = reduceOptionIterator_it.next();
            }

            return new Some(reduceOptionIterator_acc);
         case 0:
            return None$.MODULE$;
         default:
            return new Some(this.reduceRight(op));
      }
   }

   // $FF: synthetic method
   static boolean isEmpty$(final IterableOnceOps $this) {
      return $this.isEmpty();
   }

   default boolean isEmpty() {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            if (!((IterableOnce)this).iterator().hasNext()) {
               return true;
            }

            return false;
         case 0:
            return true;
         default:
            return false;
      }
   }

   // $FF: synthetic method
   static boolean nonEmpty$(final IterableOnceOps $this) {
      return $this.nonEmpty();
   }

   default boolean nonEmpty() {
      return !this.isEmpty();
   }

   // $FF: synthetic method
   static int size$(final IterableOnceOps $this) {
      return $this.size();
   }

   default int size() {
      if (((IterableOnce)this).knownSize() >= 0) {
         return ((IterableOnce)this).knownSize();
      } else {
         Iterator it = ((IterableOnce)this).iterator();
         int len = 0;

         while(it.hasNext()) {
            ++len;
            it.next();
         }

         return len;
      }
   }

   // $FF: synthetic method
   static void copyToBuffer$(final IterableOnceOps $this, final Buffer dest) {
      $this.copyToBuffer(dest);
   }

   /** @deprecated */
   default void copyToBuffer(final Buffer dest) {
      IterableOnce $plus$plus$eq_elems = (IterableOnce)this;
      if (dest == null) {
         throw null;
      } else {
         dest.addAll($plus$plus$eq_elems);
      }
   }

   // $FF: synthetic method
   static int copyToArray$(final IterableOnceOps $this, final Object xs) {
      return $this.copyToArray(xs);
   }

   default int copyToArray(final Object xs) {
      return this.copyToArray(xs, 0, Integer.MAX_VALUE);
   }

   // $FF: synthetic method
   static int copyToArray$(final IterableOnceOps $this, final Object xs, final int start) {
      return $this.copyToArray(xs, start);
   }

   default int copyToArray(final Object xs, final int start) {
      return this.copyToArray(xs, start, Integer.MAX_VALUE);
   }

   // $FF: synthetic method
   static int copyToArray$(final IterableOnceOps $this, final Object xs, final int start, final int len) {
      return $this.copyToArray(xs, start, len);
   }

   default int copyToArray(final Object xs, final int start, final int len) {
      Iterator it = ((IterableOnce)this).iterator();
      int i = start;
      scala.math.package$ var10001 = scala.math.package$.MODULE$;
      int min_y = Array.getLength(xs) - start;

      for(int end = start + Math.min(len, min_y); i < end && it.hasNext(); ++i) {
         ScalaRunTime$.MODULE$.array_update(xs, i, it.next());
      }

      return i - start;
   }

   // $FF: synthetic method
   static Object sum$(final IterableOnceOps $this, final Numeric num) {
      return $this.sum(num);
   }

   default Object sum(final Numeric num) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            return this.foldLeft(num.zero(), (x, y) -> num.plus(x, y));
         case 0:
            return num.zero();
         default:
            return this.reduce((x, y) -> num.plus(x, y));
      }
   }

   // $FF: synthetic method
   static Object product$(final IterableOnceOps $this, final Numeric num) {
      return $this.product(num);
   }

   default Object product(final Numeric num) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            return this.foldLeft(num.one(), (x, y) -> num.times(x, y));
         case 0:
            return num.one();
         default:
            return this.reduce((x, y) -> num.times(x, y));
      }
   }

   // $FF: synthetic method
   static Object min$(final IterableOnceOps $this, final Ordering ord) {
      return $this.min(ord);
   }

   default Object min(final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceLeftIterator_it = ((IterableOnce)this).iterator();
            if (!reduceLeftIterator_it.hasNext()) {
               throw new UnsupportedOperationException("empty.min");
            }

            Object reduceLeftIterator_acc;
            Object var4;
            for(reduceLeftIterator_acc = reduceLeftIterator_it.next(); reduceLeftIterator_it.hasNext(); reduceLeftIterator_acc = ord.min(reduceLeftIterator_acc, var4)) {
               var4 = reduceLeftIterator_it.next();
            }

            return reduceLeftIterator_acc;
         case 0:
            throw new UnsupportedOperationException("empty.min");
         default:
            return this.reduceLeft((x, y) -> ord.min(x, y));
      }
   }

   // $FF: synthetic method
   static Option minOption$(final IterableOnceOps $this, final Ordering ord) {
      return $this.minOption(ord);
   }

   default Option minOption(final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceLeftOptionIterator_reduceOptionIterator_it = ((IterableOnce)this).iterator();
            if (!reduceLeftOptionIterator_reduceOptionIterator_it.hasNext()) {
               return None$.MODULE$;
            }

            Object reduceLeftOptionIterator_reduceOptionIterator_acc;
            Object var4;
            for(reduceLeftOptionIterator_reduceOptionIterator_acc = reduceLeftOptionIterator_reduceOptionIterator_it.next(); reduceLeftOptionIterator_reduceOptionIterator_it.hasNext(); reduceLeftOptionIterator_reduceOptionIterator_acc = ord.min(reduceLeftOptionIterator_reduceOptionIterator_acc, var4)) {
               var4 = reduceLeftOptionIterator_reduceOptionIterator_it.next();
            }

            return new Some(reduceLeftOptionIterator_reduceOptionIterator_acc);
         case 0:
            return None$.MODULE$;
         default:
            return new Some(this.reduceLeft((x, y) -> ord.min(x, y)));
      }
   }

   // $FF: synthetic method
   static Object max$(final IterableOnceOps $this, final Ordering ord) {
      return $this.max(ord);
   }

   default Object max(final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceLeftIterator_it = ((IterableOnce)this).iterator();
            if (!reduceLeftIterator_it.hasNext()) {
               throw new UnsupportedOperationException("empty.max");
            }

            Object reduceLeftIterator_acc;
            Object var4;
            for(reduceLeftIterator_acc = reduceLeftIterator_it.next(); reduceLeftIterator_it.hasNext(); reduceLeftIterator_acc = ord.max(reduceLeftIterator_acc, var4)) {
               var4 = reduceLeftIterator_it.next();
            }

            return reduceLeftIterator_acc;
         case 0:
            throw new UnsupportedOperationException("empty.max");
         default:
            return this.reduceLeft((x, y) -> ord.max(x, y));
      }
   }

   // $FF: synthetic method
   static Option maxOption$(final IterableOnceOps $this, final Ordering ord) {
      return $this.maxOption(ord);
   }

   default Option maxOption(final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case -1:
            Iterator reduceLeftOptionIterator_reduceOptionIterator_it = ((IterableOnce)this).iterator();
            if (!reduceLeftOptionIterator_reduceOptionIterator_it.hasNext()) {
               return None$.MODULE$;
            }

            Object reduceLeftOptionIterator_reduceOptionIterator_acc;
            Object var4;
            for(reduceLeftOptionIterator_reduceOptionIterator_acc = reduceLeftOptionIterator_reduceOptionIterator_it.next(); reduceLeftOptionIterator_reduceOptionIterator_it.hasNext(); reduceLeftOptionIterator_reduceOptionIterator_acc = ord.max(reduceLeftOptionIterator_reduceOptionIterator_acc, var4)) {
               var4 = reduceLeftOptionIterator_reduceOptionIterator_it.next();
            }

            return new Some(reduceLeftOptionIterator_reduceOptionIterator_acc);
         case 0:
            return None$.MODULE$;
         default:
            return new Some(this.reduceLeft((x, y) -> ord.max(x, y)));
      }
   }

   // $FF: synthetic method
   static Object maxBy$(final IterableOnceOps $this, final Function1 f, final Ordering ord) {
      return $this.maxBy(f, ord);
   }

   default Object maxBy(final Function1 f, final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case 0:
            throw new UnsupportedOperationException("empty.maxBy");
         default:
            return ((Maximized)this.foldLeft(new Maximized("maxBy", f, (x, y) -> BoxesRunTime.boxToBoolean($anonfun$maxBy$1(ord, x, y))), (m, a) -> m.apply(m, a))).result();
      }
   }

   // $FF: synthetic method
   static Option maxByOption$(final IterableOnceOps $this, final Function1 f, final Ordering ord) {
      return $this.maxByOption(f, ord);
   }

   default Option maxByOption(final Function1 f, final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case 0:
            return None$.MODULE$;
         default:
            return ((Maximized)this.foldLeft(new Maximized("maxBy", f, (x, y) -> BoxesRunTime.boxToBoolean($anonfun$maxByOption$1(ord, x, y))), (m, a) -> m.apply(m, a))).toOption();
      }
   }

   // $FF: synthetic method
   static Object minBy$(final IterableOnceOps $this, final Function1 f, final Ordering ord) {
      return $this.minBy(f, ord);
   }

   default Object minBy(final Function1 f, final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case 0:
            throw new UnsupportedOperationException("empty.minBy");
         default:
            return ((Maximized)this.foldLeft(new Maximized("minBy", f, (x, y) -> BoxesRunTime.boxToBoolean($anonfun$minBy$1(ord, x, y))), (m, a) -> m.apply(m, a))).result();
      }
   }

   // $FF: synthetic method
   static Option minByOption$(final IterableOnceOps $this, final Function1 f, final Ordering ord) {
      return $this.minByOption(f, ord);
   }

   default Option minByOption(final Function1 f, final Ordering ord) {
      switch (((IterableOnce)this).knownSize()) {
         case 0:
            return None$.MODULE$;
         default:
            return ((Maximized)this.foldLeft(new Maximized("minBy", f, (x, y) -> BoxesRunTime.boxToBoolean($anonfun$minByOption$1(ord, x, y))), (m, a) -> m.apply(m, a))).toOption();
      }
   }

   // $FF: synthetic method
   static Option collectFirst$(final IterableOnceOps $this, final PartialFunction pf) {
      return $this.collectFirst(pf);
   }

   default Option collectFirst(final PartialFunction pf) {
      Function1 sentinel = new AbstractFunction1() {
         public AbstractFunction1 apply(final Object a) {
            return this;
         }
      };
      Iterator it = ((IterableOnce)this).iterator();

      while(it.hasNext()) {
         Object x = pf.applyOrElse(it.next(), sentinel);
         if (x != sentinel) {
            return new Some(x);
         }
      }

      return None$.MODULE$;
   }

   // $FF: synthetic method
   static Object aggregate$(final IterableOnceOps $this, final Function0 z, final Function2 seqop, final Function2 combop) {
      return $this.aggregate(z, seqop, combop);
   }

   /** @deprecated */
   default Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return this.foldLeft(z.apply(), seqop);
   }

   // $FF: synthetic method
   static boolean corresponds$(final IterableOnceOps $this, final IterableOnce that, final Function2 p) {
      return $this.corresponds(that, p);
   }

   default boolean corresponds(final IterableOnce that, final Function2 p) {
      Iterator a = ((IterableOnce)this).iterator();
      Iterator b = that.iterator();

      while(a.hasNext() && b.hasNext()) {
         if (!BoxesRunTime.unboxToBoolean(p.apply(a.next(), b.next()))) {
            return false;
         }
      }

      return a.hasNext() == b.hasNext();
   }

   // $FF: synthetic method
   static String mkString$(final IterableOnceOps $this, final String start, final String sep, final String end) {
      return $this.mkString(start, sep, end);
   }

   default String mkString(final String start, final String sep, final String end) {
      return ((IterableOnce)this).knownSize() == 0 ? (new StringBuilder(0)).append(start).append(end).toString() : this.addString(new scala.collection.mutable.StringBuilder(), start, sep, end).result();
   }

   // $FF: synthetic method
   static String mkString$(final IterableOnceOps $this, final String sep) {
      return $this.mkString(sep);
   }

   default String mkString(final String sep) {
      return this.mkString("", sep, "");
   }

   // $FF: synthetic method
   static String mkString$(final IterableOnceOps $this) {
      return $this.mkString();
   }

   default String mkString() {
      String mkString_sep = "";
      return this.mkString("", mkString_sep, "");
   }

   // $FF: synthetic method
   static scala.collection.mutable.StringBuilder addString$(final IterableOnceOps $this, final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
      return $this.addString(b, start, sep, end);
   }

   default scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String start, final String sep, final String end) {
      StringBuilder jsb = b.underlying();
      if (start.length() != 0) {
         jsb.append(start);
      }

      Iterator it = ((IterableOnce)this).iterator();
      if (it.hasNext()) {
         jsb.append(it.next());

         while(it.hasNext()) {
            jsb.append(sep);
            jsb.append(it.next());
         }
      }

      if (end.length() != 0) {
         jsb.append(end);
      }

      return b;
   }

   // $FF: synthetic method
   static scala.collection.mutable.StringBuilder addString$(final IterableOnceOps $this, final scala.collection.mutable.StringBuilder b, final String sep) {
      return $this.addString(b, sep);
   }

   default scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b, final String sep) {
      return this.addString(b, "", sep, "");
   }

   // $FF: synthetic method
   static scala.collection.mutable.StringBuilder addString$(final IterableOnceOps $this, final scala.collection.mutable.StringBuilder b) {
      return $this.addString(b);
   }

   default scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder b) {
      String addString_sep = "";
      return this.addString(b, "", addString_sep, "");
   }

   // $FF: synthetic method
   static Object to$(final IterableOnceOps $this, final Factory factory) {
      return $this.to(factory);
   }

   default Object to(final Factory factory) {
      return factory.fromSpecific((IterableOnce)this);
   }

   // $FF: synthetic method
   static Iterator toIterator$(final IterableOnceOps $this) {
      return $this.toIterator();
   }

   /** @deprecated */
   default Iterator toIterator() {
      return ((IterableOnce)this).iterator();
   }

   // $FF: synthetic method
   static List toList$(final IterableOnceOps $this) {
      return $this.toList();
   }

   default List toList() {
      List$ var10000 = List$.MODULE$;
      IterableOnce from_coll = (IterableOnce)this;
      return Nil$.MODULE$.prependedAll(from_coll);
   }

   // $FF: synthetic method
   static Vector toVector$(final IterableOnceOps $this) {
      return $this.toVector();
   }

   default Vector toVector() {
      return Vector$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static scala.collection.immutable.Map toMap$(final IterableOnceOps $this, final $less$colon$less ev) {
      return $this.toMap(ev);
   }

   default scala.collection.immutable.Map toMap(final $less$colon$less ev) {
      return scala.collection.immutable.Map$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static scala.collection.immutable.Set toSet$(final IterableOnceOps $this) {
      return $this.toSet();
   }

   default scala.collection.immutable.Set toSet() {
      return scala.collection.immutable.Set$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static scala.collection.immutable.Seq toSeq$(final IterableOnceOps $this) {
      return $this.toSeq();
   }

   default scala.collection.immutable.Seq toSeq() {
      return scala.collection.immutable.Seq$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static scala.collection.immutable.IndexedSeq toIndexedSeq$(final IterableOnceOps $this) {
      return $this.toIndexedSeq();
   }

   default scala.collection.immutable.IndexedSeq toIndexedSeq() {
      return scala.collection.immutable.IndexedSeq$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static Stream toStream$(final IterableOnceOps $this) {
      return $this.toStream();
   }

   /** @deprecated */
   default Stream toStream() {
      IterableFactory$ var10001 = IterableFactory$.MODULE$;
      IterableFactory toFactory_factory = Stream$.MODULE$;
      IterableFactory.ToFactory var3 = new IterableFactory.ToFactory(toFactory_factory);
      toFactory_factory = null;
      return (Stream)this.to(var3);
   }

   // $FF: synthetic method
   static Buffer toBuffer$(final IterableOnceOps $this) {
      return $this.toBuffer();
   }

   default Buffer toBuffer() {
      return (Buffer)Buffer$.MODULE$.from((IterableOnce)this);
   }

   // $FF: synthetic method
   static Object toArray$(final IterableOnceOps $this, final ClassTag evidence$2) {
      return $this.toArray(evidence$2);
   }

   default Object toArray(final ClassTag evidence$2) {
      if (((IterableOnce)this).knownSize() >= 0) {
         Object destination = evidence$2.newArray(((IterableOnce)this).knownSize());
         this.copyToArray(destination, 0);
         return destination;
      } else {
         Object var14;
         label116: {
            label120: {
               ArrayBuilder$ var10000 = ArrayBuilder$.MODULE$;
               Class var3 = evidence$2.runtimeClass();
               Class var5 = Byte.TYPE;
               if (var5 == null) {
                  if (var3 == null) {
                     break label120;
                  }
               } else if (var5.equals(var3)) {
                  break label120;
               }

               label121: {
                  var5 = Short.TYPE;
                  if (var5 == null) {
                     if (var3 == null) {
                        break label121;
                     }
                  } else if (var5.equals(var3)) {
                     break label121;
                  }

                  label122: {
                     var5 = Character.TYPE;
                     if (var5 == null) {
                        if (var3 == null) {
                           break label122;
                        }
                     } else if (var5.equals(var3)) {
                        break label122;
                     }

                     label123: {
                        var5 = Integer.TYPE;
                        if (var5 == null) {
                           if (var3 == null) {
                              break label123;
                           }
                        } else if (var5.equals(var3)) {
                           break label123;
                        }

                        label124: {
                           var5 = Long.TYPE;
                           if (var5 == null) {
                              if (var3 == null) {
                                 break label124;
                              }
                           } else if (var5.equals(var3)) {
                              break label124;
                           }

                           label125: {
                              var5 = Float.TYPE;
                              if (var5 == null) {
                                 if (var3 == null) {
                                    break label125;
                                 }
                              } else if (var5.equals(var3)) {
                                 break label125;
                              }

                              label126: {
                                 var5 = Double.TYPE;
                                 if (var5 == null) {
                                    if (var3 == null) {
                                       break label126;
                                    }
                                 } else if (var5.equals(var3)) {
                                    break label126;
                                 }

                                 label127: {
                                    var5 = Boolean.TYPE;
                                    if (var5 == null) {
                                       if (var3 == null) {
                                          break label127;
                                       }
                                    } else if (var5.equals(var3)) {
                                       break label127;
                                    }

                                    label59: {
                                       var5 = Void.TYPE;
                                       if (var5 == null) {
                                          if (var3 == null) {
                                             break label59;
                                          }
                                       } else if (var5.equals(var3)) {
                                          break label59;
                                       }

                                       var14 = new ArrayBuilder.ofRef(evidence$2);
                                       break label116;
                                    }

                                    var14 = new ArrayBuilder.ofUnit();
                                    break label116;
                                 }

                                 var14 = new ArrayBuilder.ofBoolean();
                                 break label116;
                              }

                              var14 = new ArrayBuilder.ofDouble();
                              break label116;
                           }

                           var14 = new ArrayBuilder.ofFloat();
                           break label116;
                        }

                        var14 = new ArrayBuilder.ofLong();
                        break label116;
                     }

                     var14 = new ArrayBuilder.ofInt();
                     break label116;
                  }

                  var14 = new ArrayBuilder.ofChar();
                  break label116;
               }

               var14 = new ArrayBuilder.ofShort();
               break label116;
            }

            var14 = new ArrayBuilder.ofByte();
         }

         Object var4 = null;
         return ((ArrayBuilder)var14).addAll((IterableOnce)this).result();
      }
   }

   // $FF: synthetic method
   static Iterable reversed$(final IterableOnceOps $this) {
      return $this.reversed();
   }

   default Iterable reversed() {
      List xs = Nil$.MODULE$;

      Object var3;
      for(Iterator it = ((IterableOnce)this).iterator(); it.hasNext(); xs = xs.$colon$colon(var3)) {
         var3 = it.next();
      }

      return xs;
   }

   private Object loop$1(final int at, final int end, final Object acc, final Function2 op$1, final IndexedSeq seq$1) {
      while(at != end) {
         int var10000 = at + 1;
         acc = op$1.apply(acc, seq$1.apply(at));
         end = end;
         at = var10000;
      }

      return acc;
   }

   private Object loop$2(final int at, final Object acc, final Function2 op$2, final IndexedSeq seq$2) {
      while(at != 0) {
         int var10000 = at - 1;
         acc = op$2.apply(seq$2.apply(at - 1), acc);
         at = var10000;
      }

      return acc;
   }

   // $FF: synthetic method
   static Nothing$ $anonfun$reduceLeft$1() {
      throw new UnsupportedOperationException("empty.reduceLeft");
   }

   // $FF: synthetic method
   static Object $anonfun$reduceRightOption$1(final Function2 op$5, final Object x, final Object y) {
      return op$5.apply(y, x);
   }

   // $FF: synthetic method
   static Nothing$ $anonfun$min$1() {
      throw new UnsupportedOperationException("empty.min");
   }

   // $FF: synthetic method
   static Object $anonfun$min$2(final Ordering ord$1, final Object x, final Object y) {
      return ord$1.min(x, y);
   }

   // $FF: synthetic method
   static Object $anonfun$minOption$1(final Ordering ord$2, final Object x, final Object y) {
      return ord$2.min(x, y);
   }

   // $FF: synthetic method
   static Nothing$ $anonfun$max$1() {
      throw new UnsupportedOperationException("empty.max");
   }

   // $FF: synthetic method
   static Object $anonfun$max$2(final Ordering ord$3, final Object x, final Object y) {
      return ord$3.max(x, y);
   }

   // $FF: synthetic method
   static Object $anonfun$maxOption$1(final Ordering ord$4, final Object x, final Object y) {
      return ord$4.max(x, y);
   }

   // $FF: synthetic method
   static boolean $anonfun$maxBy$1(final Ordering ord$5, final Object x, final Object y) {
      return ord$5.gt(x, y);
   }

   // $FF: synthetic method
   static boolean $anonfun$maxByOption$1(final Ordering ord$6, final Object x, final Object y) {
      return ord$6.gt(x, y);
   }

   // $FF: synthetic method
   static boolean $anonfun$minBy$1(final Ordering ord$7, final Object x, final Object y) {
      return ord$7.lt(x, y);
   }

   // $FF: synthetic method
   static boolean $anonfun$minByOption$1(final Ordering ord$8, final Object x, final Object y) {
      return ord$8.lt(x, y);
   }

   static void $init$(final IterableOnceOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class Maximized extends AbstractFunction2 {
      private final String descriptor;
      private final Function1 f;
      private final Function2 cmp;
      private Object maxElem;
      private Object maxF;
      private boolean nonEmpty;
      // $FF: synthetic field
      public final IterableOnceOps $outer;

      public Object maxElem() {
         return this.maxElem;
      }

      public void maxElem_$eq(final Object x$1) {
         this.maxElem = x$1;
      }

      public Object maxF() {
         return this.maxF;
      }

      public void maxF_$eq(final Object x$1) {
         this.maxF = x$1;
      }

      public boolean nonEmpty() {
         return this.nonEmpty;
      }

      public void nonEmpty_$eq(final boolean x$1) {
         this.nonEmpty = x$1;
      }

      public Option toOption() {
         return (Option)(this.nonEmpty() ? new Some(this.maxElem()) : None$.MODULE$);
      }

      public Object result() {
         if (this.nonEmpty()) {
            return this.maxElem();
         } else {
            throw new UnsupportedOperationException((new StringBuilder(6)).append("empty.").append(this.descriptor).toString());
         }
      }

      public Maximized apply(final Maximized m, final Object a) {
         if (m.nonEmpty()) {
            Object fa = this.f.apply(a);
            if (BoxesRunTime.unboxToBoolean(this.cmp.apply(fa, this.maxF()))) {
               this.maxF_$eq(fa);
               this.maxElem_$eq(a);
            }

            return m;
         } else {
            m.nonEmpty_$eq(true);
            m.maxElem_$eq(a);
            m.maxF_$eq(this.f.apply(a));
            return m;
         }
      }

      // $FF: synthetic method
      public IterableOnceOps scala$collection$IterableOnceOps$Maximized$$$outer() {
         return this.$outer;
      }

      public Maximized(final String descriptor, final Function1 f, final Function2 cmp) {
         this.descriptor = descriptor;
         this.f = f;
         this.cmp = cmp;
         if (IterableOnceOps.this == null) {
            throw null;
         } else {
            this.$outer = IterableOnceOps.this;
            super();
            this.maxElem = null;
            this.maxF = null;
            this.nonEmpty = false;
         }
      }
   }
}
