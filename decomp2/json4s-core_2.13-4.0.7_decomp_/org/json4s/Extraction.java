package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import org.json4s.JNull.;
import org.json4s.prefs.ExtractionNullStrategy;
import org.json4s.reflect.ClassDescriptor;
import org.json4s.reflect.ConstructorDescriptor;
import org.json4s.reflect.ConstructorParamDescriptor;
import org.json4s.reflect.Executable;
import org.json4s.reflect.PropertyDescriptor;
import org.json4s.reflect.ScalaType;
import org.json4s.reflect.ScalaType$;
import org.json4s.reflect.TypeInfo;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil;
import scala.collection.immutable.Set;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Seq;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.Manifest;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u001ds!B\u001a5\u0011\u0003Id!B\u001e5\u0011\u0003a\u0004\"B\"\u0002\t\u0003!\u0005\"B#\u0002\t\u00031\u0005\"\u00024\u0002\t\u00039\u0007\"B#\u0002\t\u0003\u0019\b\"\u0002@\u0002\t\u0003y\bbBA\u000e\u0003\u0011\u0005\u0011Q\u0004\u0005\u000b\u0003\u001f\n\u0001R1Q\u0005\n\u0005E\u0003bBA4\u0003\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003\u0003\u000bA\u0011AAB\u0011!\tY)\u0001Q\u0005\n\u00055\u0005bBAU\u0003\u0011\u0005\u00111\u0016\u0005\n\u0003s\u000b\u0011\u0013!C\u0001\u0003wCq!a5\u0002\t\u0003\t)\u000eC\u0005\u0002j\u0006\t\n\u0011\"\u0001\u0002l\"I\u0011q^\u0001\u0012\u0002\u0013\u0005\u00111\u001e\u0005\u0007\u000b\u0006!\t!!=\t\u0011\t\r\u0011\u0001)C\u0005\u0005\u000b1aAa\u0005\u0002\t\tU\u0001\u0002\u00032\u0014\u0005\u0003\u0005\u000b\u0011B2\t\u0015\t]1C!A!\u0002\u0013\ti\u0010\u0003\u0005V'\t\u0005\t\u0015a\u0003W\u0011\u0019\u00195\u0003\"\u0001\u0003\u001a!A!\u0011C\n!\u0002\u0013\ti\u0010\u0003\u0005\u0003(M\u0001K\u0011\u0002B\u0015\u0011!\u0011\u0019e\u0005Q\u0005\n\t\u0015\u0003b\u0002B*'\u0011\u0005!Q\u000b\u0004\u0007\u0005/\nAA!\u0017\t\u0011\td\"\u0011!Q\u0001\n\rD!Ba\u0017\u001d\u0005\u0003\u0005\u000b\u0011\u0002B/\u0011!)FD!A!\u0002\u00171\u0006BB\"\u001d\t\u0003\u0011\u0019gB\u0004\u0003pqAIA!\u001d\u0007\u000f\tUD\u0004#\u0003\u0003x!11I\tC\u0001\u0005sBqAa\u001f#\t\u0003\u0011i\b\u0003\u0005\u0003*r\u0001\u000b\u0015\u0002BV\u0011!\u0011i\u0003\bQ\u0005\n\tE\u0006\u0002\u0003BZ9\u0001&IA!.\t\u0011\teF\u0004)C\u0005\u0005wC\u0001Ba2\u001dA\u0013%!\u0011\u001a\u0005\t\u0005\u001fd\u0002\u0015\"\u0003\u0003R\"A!q\u001b\u000f!\n\u0013\u0011)\u0006\u0003\u0005\u0003Zr\u0001K\u0011\u0002Bn\u0011\u001d\u0011\u0019\u0006\bC\u0001\u0005+B\u0001B!;\u0002A\u0013%!1\u001e\u0005\t\u0005{\f\u0001\u0015\"\u0003\u0003\u0000\"A!Q`\u0001!\n\u0013\u0019I\u0001\u0003\u0005\u0004\u001e\u0005\u0001K\u0011BB\u0010\u0011!\u0019\u0019$\u0001Q\u0005\n\rU\u0012AC#yiJ\f7\r^5p]*\u0011QGN\u0001\u0007UN|g\u000eN:\u000b\u0003]\n1a\u001c:h\u0007\u0001\u0001\"AO\u0001\u000e\u0003Q\u0012!\"\u0012=ue\u0006\u001cG/[8o'\t\tQ\b\u0005\u0002?\u00036\tqHC\u0001A\u0003\u0015\u00198-\u00197b\u0013\t\u0011uH\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003e\nq!\u001a=ue\u0006\u001cG/\u0006\u0002H\u0017R\u0011\u0001*\u0019\u000b\u0004\u0013RK\u0006C\u0001&L\u0019\u0001!Q\u0001T\u0002C\u00025\u0013\u0011!Q\t\u0003\u001dF\u0003\"AP(\n\u0005A{$a\u0002(pi\"Lgn\u001a\t\u0003}IK!aU \u0003\u0007\u0005s\u0017\u0010C\u0003V\u0007\u0001\u000fa+A\u0004g_Jl\u0017\r^:\u0011\u0005i:\u0016B\u0001-5\u0005\u001d1uN]7biNDQAW\u0002A\u0004m\u000b!!\u001c4\u0011\u0007q{\u0016*D\u0001^\u0015\tqv(A\u0004sK\u001adWm\u0019;\n\u0005\u0001l&\u0001C'b]&4Wm\u001d;\t\u000b\t\u001c\u0001\u0019A2\u0002\t)\u001cxN\u001c\t\u0003u\u0011L!!\u001a\u001b\u0003\r)3\u0016\r\\;f\u0003))\u0007\u0010\u001e:bGR|\u0005\u000f^\u000b\u0003Q:$\"!\u001b:\u0015\u0007)|\u0007\u000fE\u0002?W6L!\u0001\\ \u0003\r=\u0003H/[8o!\tQe\u000eB\u0003M\t\t\u0007Q\nC\u0003V\t\u0001\u000fa\u000bC\u0003[\t\u0001\u000f\u0011\u000fE\u0002]?6DQA\u0019\u0003A\u0002\r$2\u0001\u001e<x)\t\tV\u000fC\u0003V\u000b\u0001\u000fa\u000bC\u0003c\u000b\u0001\u00071\rC\u0003y\u000b\u0001\u0007\u00110\u0001\u0004uCJ<W\r\u001e\t\u0003url\u0011a\u001f\u0006\u0003=RJ!!`>\u0003\u0011QK\b/Z%oM>\fA\u0003Z3d_6\u0004xn]3XSRD')^5mI\u0016\u0014X\u0003BA\u0001\u0003\u000f!b!a\u0001\u0002\u000e\u0005EA\u0003BA\u0003\u0003\u0017\u00012ASA\u0004\t\u0019\tIA\u0002b\u0001\u001b\n\tA\u000bC\u0003V\r\u0001\u000fa\u000b\u0003\u0004\u0002\u0010\u0019\u0001\r!U\u0001\u0002C\"9\u00111\u0003\u0004A\u0002\u0005U\u0011a\u00022vS2$WM\u001d\t\u0006u\u0005]\u0011QA\u0005\u0004\u00033!$A\u0003&t_:<&/\u001b;fe\u0006\u0001Bn\\1e\u0019\u0006T\u0018PV1m-\u0006dW/\u001a\u000b\t\u0003?\ty#!\r\u0002LA!\u0011\u0011EA\u0016\u001b\t\t\u0019C\u0003\u0003\u0002&\u0005\u001d\u0012\u0001\u00027b]\u001eT!!!\u000b\u0002\t)\fg/Y\u0005\u0005\u0003[\t\u0019C\u0001\u0004PE*,7\r\u001e\u0005\u0007\u0003\u001f9\u0001\u0019A)\t\u000f\u0005Mr\u00011\u0001\u00026\u0005!a.Y7f!\u0011\t9$!\u0012\u000f\t\u0005e\u0012\u0011\t\t\u0004\u0003wyTBAA\u001f\u0015\r\ty\u0004O\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005\rs(\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u000f\nIE\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u0007z\u0004BBA'\u000f\u0001\u0007\u0011+\u0001\u0007eK\u001a\fW\u000f\u001c;WC2,X-\u0001\u0007usB,7\u000fS1wK:\u000bg*\u0006\u0002\u0002TA1\u0011qGA+\u00033JA!a\u0016\u0002J\t\u00191+\u001a;1\t\u0005m\u00131\r\t\u0007\u0003o\ti&!\u0019\n\t\u0005}\u0013\u0011\n\u0002\u0006\u00072\f7o\u001d\t\u0004\u0015\u0006\rDACA3\u0011\u0005\u0005\t\u0011!B\u0001\u001b\n\u0019q\fJ\u0019\u00029%tG/\u001a:oC2$UmY8na>\u001cXmV5uQ\n+\u0018\u000e\u001c3feV!\u00111NA@)\u0019\ti'a\u001e\u0002zQ!\u0011qNA;!\rq\u0014\u0011O\u0005\u0004\u0003gz$\u0001B+oSRDQ!V\u0005A\u0004YCa!a\u0004\n\u0001\u0004\t\u0006bBA\n\u0013\u0001\u0007\u00111\u0010\t\u0006u\u0005]\u0011Q\u0010\t\u0004\u0015\u0006}DABA\u0005\u0013\t\u0007Q*A\u0005eK\u000e|W\u000e]8tKR!\u0011QQAE)\r\u0019\u0017q\u0011\u0005\u0006+*\u0001\u001dA\u0016\u0005\u0007\u0003\u001fQ\u0001\u0019A)\u0002\u001d]\u0014\u0018\u000e^3Qe&l\u0017\u000e^5wKR1\u0011qRAO\u0003?#B!!%\u0002\u001cB\"\u00111SAL!\u0015Q\u0014qCAK!\rQ\u0015q\u0013\u0003\u000b\u00033[\u0011\u0011!A\u0001\u0006\u0003i%\u0001B0%eMBQ!V\u0006A\u0004YCa!a\u0004\f\u0001\u0004\t\u0006bBA\n\u0017\u0001\u0007\u0011\u0011\u0015\u0019\u0005\u0003G\u000b9\u000bE\u0003;\u0003/\t)\u000bE\u0002K\u0003O#1\"!'\u0002 \u0006\u0005\t\u0011!B\u0001\u001b\u00069a\r\\1ui\u0016tG\u0003BAW\u0003o#B!a,\u00026BA\u0011qGAY\u0003k\t)$\u0003\u0003\u00024\u0006%#aA'ba\"9Q\u000b\u0004I\u0001\u0002\b1\u0006\"\u00022\r\u0001\u0004\u0019\u0017!\u00054mCR$XM\u001c\u0013eK\u001a\fW\u000f\u001c;%eQ!\u0011QXAiU\r1\u0016qX\u0016\u0003\u0003\u0003\u0004B!a1\u0002N6\u0011\u0011Q\u0019\u0006\u0005\u0003\u000f\fI-A\u0005v]\u000eDWmY6fI*\u0019\u00111Z \u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002P\u0006\u0015'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\")!-\u0004a\u0001G\u0006IQO\u001c4mCR$XM\u001c\u000b\bG\u0006]\u00171\\As\u0011\u001d\tIN\u0004a\u0001\u0003_\u000b1!\\1q\u0011%\tiN\u0004I\u0001\u0002\u0004\ty.\u0001\fvg\u0016\u0014\u0015n\u001a#fG&l\u0017\r\u001c$pe\u0012{WO\u00197f!\rq\u0014\u0011]\u0005\u0004\u0003G|$a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003Ot\u0001\u0013!a\u0001\u0003?\f\u0001#^:f\u0005&<\u0017J\u001c;G_JduN\\4\u0002'Utg\r\\1ui\u0016tG\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u00055(\u0006BAp\u0003\u007f\u000b1#\u001e8gY\u0006$H/\u001a8%I\u00164\u0017-\u001e7uIM\"b!a=\u0002x\u0006eHcA)\u0002v\")Q+\u0005a\u0002-\")!-\u0005a\u0001G\"9\u00111`\tA\u0002\u0005u\u0018!C:dC2\fG+\u001f9f!\rQ\u0018q`\u0005\u0004\u0005\u0003Y(!C*dC2\fG+\u001f9f\u0003m)\u0007\u0010\u001e:bGR$U\r^3di&twMT8o)\u0016\u0014X.\u001b8bYR1!q\u0001B\u0006\u0005\u001f!2!\u0015B\u0005\u0011\u0015)&\u0003q\u0001W\u0011\u0019\u0011iA\u0005a\u0001G\u00061!N^1mk\u0016DqA!\u0005\u0013\u0001\u0004\ti0A\u0004usB,\u0017I]4\u0003#\r{G\u000e\\3di&|gNQ;jY\u0012,'o\u0005\u0002\u0014{\u0005\u0019A\u000f]3\u0015\r\tm!1\u0005B\u0013)\u0011\u0011iB!\t\u0011\u0007\t}1#D\u0001\u0002\u0011\u0015)v\u0003q\u0001W\u0011\u0015\u0011w\u00031\u0001d\u0011\u001d\u00119b\u0006a\u0001\u0003{\fA\"\\6D_2dWm\u0019;j_:$2!\u0015B\u0016\u0011\u001d\u0011i#\u0007a\u0001\u0005_\t1bY8ogR\u0014Xo\u0019;peB1aH!\r\u00036EK1Aa\r@\u0005%1UO\\2uS>t\u0017\u0007\r\u0003\u00038\t}\u0002#\u0002 \u0003:\tu\u0012b\u0001B\u001e\u007f\t)\u0011I\u001d:bsB\u0019!Ja\u0010\u0005\u0017\t\u0005#1FA\u0001\u0002\u0003\u0015\t!\u0014\u0002\u0005?\u0012\u0012d'\u0001\u0007nWRK\b/\u001a3BeJ\f\u0017\u0010\u0006\u0003\u0002 \t\u001d\u0003bBA\b5\u0001\u0007!\u0011\n\u0019\u0005\u0005\u0017\u0012y\u0005E\u0003?\u0005s\u0011i\u0005E\u0002K\u0005\u001f\"1B!\u0015\u0003H\u0005\u0005\t\u0011!B\u0001\u001b\n!q\f\n\u001a9\u0003\u0019\u0011Xm];miV\t\u0011K\u0001\u000bDY\u0006\u001c8/\u00138ti\u0006t7-\u001a\"vS2$WM]\n\u00039u\nQ\u0001Z3tGJ\u00042A\u001fB0\u0013\r\u0011\tg\u001f\u0002\u0010\u00072\f7o\u001d#fg\u000e\u0014\u0018\u000e\u001d;peR1!Q\rB6\u0005[\"BAa\u001a\u0003jA\u0019!q\u0004\u000f\t\u000bU\u0003\u00039\u0001,\t\u000b\t\u0004\u0003\u0019A2\t\u000f\tm\u0003\u00051\u0001\u0003^\u0005AA+\u001f9f\u0011&tG\u000fE\u0002\u0003t\tj\u0011\u0001\b\u0002\t)f\u0004X\rS5oiN\u0011!%\u0010\u000b\u0003\u0005c\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003\u0000\t\u0015\u0006\u0003\u0002 l\u0005\u0003\u0003rA\u0010BB\u0003k\u00119)C\u0002\u0003\u0006~\u0012a\u0001V;qY\u0016\u0014\u0004C\u0002BE\u0005'\u0013IJ\u0004\u0003\u0003\f\n=e\u0002BA\u001e\u0005\u001bK\u0011\u0001Q\u0005\u0004\u0005#{\u0014a\u00029bG.\fw-Z\u0005\u0005\u0005+\u00139J\u0001\u0003MSN$(b\u0001BI\u007fA!!1\u0014BP\u001d\rQ$QT\u0005\u0004\u0005##\u0014\u0002\u0002BQ\u0005G\u0013aA\u0013$jK2$'b\u0001BIi!9!q\u0015\u0013A\u0002\t\u001d\u0015A\u00014t\u00031y6m\u001c8tiJ,8\r^8s!\rQ(QV\u0005\u0004\u0005_[(!F\"p]N$(/^2u_J$Um]2sSB$xN]\u000b\u0003\u0005W\u000b\u0011b]3u\r&,G\u000eZ:\u0015\u0007u\u00129\f\u0003\u0004\u0002\u0010\u001d\u0002\r!P\u0001\rEVLG\u000eZ\"u_J\f%o\u001a\u000b\u0006#\nu&q\u0018\u0005\u0006E\"\u0002\ra\u0019\u0005\b\u00057B\u0003\u0019\u0001Ba!\rQ(1Y\u0005\u0004\u0005\u000b\\(AG\"p]N$(/^2u_J\u0004\u0016M]1n\t\u0016\u001c8M]5qi>\u0014\u0018\u0001\u00062vS2$w\n\u001d;j_:\fGn\u0011;pe\u0006\u0013x\rF\u0003R\u0005\u0017\u0014i\rC\u0003cS\u0001\u00071\rC\u0004\u0003\\%\u0002\rA!1\u0002+\t,\u0018\u000e\u001c3NC:$\u0017\r^8ss\u000e#xN]!sOR)\u0011Ka5\u0003V\")!M\u000ba\u0001G\"9!1\f\u0016A\u0002\t\u0005\u0017aC5ogR\fg\u000e^5bi\u0016\fa\"\\6XSRDG+\u001f9f\u0011&tG\u000fF\u0004R\u0005;\u0014\tO!:\t\u000f\t}G\u00061\u0001\u00026\u0005AA/\u001f9f\u0011&tG\u000fC\u0004\u0003d2\u0002\rAa\"\u0002\r\u0019LW\r\u001c3t\u0011\u001d\u00119\u000f\fa\u0001\u0003{\f\u0001\u0002^=qK&sgm\\\u0001\rGV\u001cHo\\7Pe\u0016c7/\u001a\u000b\u0007\u0005[\u0014IPa?\u0015\t\t=(1\u001f\u000b\u0004#\nE\b\"B+/\u0001\b1\u0006b\u0002B{]\u0001\u0007!q_\u0001\u0006i\",hn\u001b\t\u0006}\tE2-\u0015\u0005\u0007q:\u0002\r!!@\t\u000b\tt\u0003\u0019A2\u0002\u000f\r|gN^3siR9\u0011k!\u0001\u0004\u0006\r\u001d\u0001bBB\u0002_\u0001\u0007\u0011QG\u0001\u0004W\u0016L\bB\u0002=0\u0001\u0004\ti\u0010C\u0003V_\u0001\u0007a\u000bF\u0005R\u0007\u0017\u0019iaa\u0004\u0004\u0012!)!\r\ra\u0001G\"1\u0001\u0010\ra\u0001\u0003{DQ!\u0016\u0019A\u0002YCqaa\u00051\u0001\u0004\u0019)\"A\u0004eK\u001a\fW\u000f\u001c;\u0011\tyZ7q\u0003\t\u0005}\re\u0011+C\u0002\u0004\u001c}\u0012\u0011BR;oGRLwN\u001c\u0019\u0002\u001f\u0019|'/\\1u)&lWm\u001d;b[B$ba!\t\u0004.\rE\u0002\u0003BB\u0012\u0007Si!a!\n\u000b\t\r\u001d\u0012qE\u0001\u0004gFd\u0017\u0002BB\u0016\u0007K\u0011\u0011\u0002V5nKN$\u0018-\u001c9\t\u000f\r=\u0012\u00071\u0001\u00026\u0005\t1\u000fC\u0003Vc\u0001\u0007a+\u0001\u0006g_Jl\u0017\r\u001e#bi\u0016$baa\u000e\u0004D\r\u0015\u0003\u0003BB\u001d\u0007\u007fi!aa\u000f\u000b\t\ru\u0012qE\u0001\u0005kRLG.\u0003\u0003\u0004B\rm\"\u0001\u0002#bi\u0016Dqaa\f3\u0001\u0004\t)\u0004C\u0003Ve\u0001\u0007a\u000b"
)
public final class Extraction {
   public static Object extract(final JValue json, final ScalaType scalaType, final Formats formats) {
      return Extraction$.MODULE$.extract(json, scalaType, formats);
   }

   public static boolean unflatten$default$3() {
      return Extraction$.MODULE$.unflatten$default$3();
   }

   public static boolean unflatten$default$2() {
      return Extraction$.MODULE$.unflatten$default$2();
   }

   public static JValue unflatten(final Map map, final boolean useBigDecimalForDouble, final boolean useBigIntForLong) {
      return Extraction$.MODULE$.unflatten(map, useBigDecimalForDouble, useBigIntForLong);
   }

   public static Formats flatten$default$2(final JValue json) {
      return Extraction$.MODULE$.flatten$default$2(json);
   }

   public static Map flatten(final JValue json, final Formats formats) {
      return Extraction$.MODULE$.flatten(json, formats);
   }

   public static JValue decompose(final Object a, final Formats formats) {
      return Extraction$.MODULE$.decompose(a, formats);
   }

   public static void internalDecomposeWithBuilder(final Object a, final JsonWriter builder, final Formats formats) {
      Extraction$.MODULE$.internalDecomposeWithBuilder(a, builder, formats);
   }

   public static Object loadLazyValValue(final Object a, final String name, final Object defaultValue) {
      return Extraction$.MODULE$.loadLazyValValue(a, name, defaultValue);
   }

   public static Object decomposeWithBuilder(final Object a, final JsonWriter builder, final Formats formats) {
      return Extraction$.MODULE$.decomposeWithBuilder(a, builder, formats);
   }

   public static Object extract(final JValue json, final TypeInfo target, final Formats formats) {
      return Extraction$.MODULE$.extract(json, target, formats);
   }

   public static Option extractOpt(final JValue json, final Formats formats, final Manifest mf) {
      return Extraction$.MODULE$.extractOpt(json, formats, mf);
   }

   public static Object extract(final JValue json, final Formats formats, final Manifest mf) {
      return Extraction$.MODULE$.extract(json, formats, mf);
   }

   private static class CollectionBuilder {
      private final JValue json;
      private final ScalaType tpe;
      private final Formats formats;
      private final ScalaType typeArg;

      private Object mkCollection(final Function1 constructor) {
         JValue var5 = this.json;
         Object array;
         if (var5 instanceof JArray) {
            JArray var6 = (JArray)var5;
            List arr = var6.arr();
            array = arr.flatMap((x0$1) -> {
               Object var2;
               label26: {
                  if (.MODULE$.equals(x0$1)) {
                     ExtractionNullStrategy var10000 = this.formats.extractionNullStrategy();
                     ExtractionNullStrategy.TreatAsAbsent$ var4 = ExtractionNullStrategy.TreatAsAbsent$.MODULE$;
                     if (var10000 == null) {
                        if (var4 == null) {
                           break label26;
                        }
                     } else if (var10000.equals(var4)) {
                        break label26;
                     }
                  }

                  var2 = new Some(Extraction$.MODULE$.org$json4s$Extraction$$extractDetectingNonTerminal(x0$1, this.typeArg, this.formats));
                  return (Option)var2;
               }

               var2 = scala.None..MODULE$;
               return (Option)var2;
            }).toArray(scala.reflect.ClassTag..MODULE$.Any());
         } else {
            boolean var3;
            if (org.json4s.JNothing..MODULE$.equals(var5)) {
               var3 = true;
            } else if (.MODULE$.equals(var5)) {
               var3 = true;
            } else {
               var3 = false;
            }

            if (!var3 || this.formats.strictArrayExtraction()) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(51)).append("Expected collection but got ").append(var5).append(" for root ").append(this.json).append(" and mapping ").append(this.tpe).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }

            array = scala.Array..MODULE$.apply(scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.AnyRef());
         }

         return constructor.apply(array);
      }

      private Object mkTypedArray(final Object a) {
         return ((Tuple2)scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.genericArrayOps(a), new Tuple2(Array.newInstance(this.typeArg.erasure(), scala.runtime.ScalaRunTime..MODULE$.array_length(a)), BoxesRunTime.boxToInteger(0)), (tuple, e) -> {
            Array.set(tuple._1(), tuple._2$mcI$sp(), e);
            return new Tuple2(tuple._1(), BoxesRunTime.boxToInteger(tuple._2$mcI$sp() + 1));
         }))._1();
      }

      public Object result() {
         LazyRef customRich$lzy = new LazyRef();
         PartialFunction custom = Formats$.MODULE$.customDeserializer(new Tuple2(this.tpe.typeInfo(), this.json), this.formats);
         Object var10000;
         if (custom.isDefinedAt(new Tuple2(this.tpe.typeInfo(), this.json))) {
            var10000 = custom.apply(new Tuple2(this.tpe.typeInfo(), this.json));
         } else if (this.customRich$1(customRich$lzy).isDefinedAt(new Tuple2(this.tpe, this.json))) {
            var10000 = this.customRich$1(customRich$lzy).apply(new Tuple2(this.tpe, this.json));
         } else {
            label97: {
               Class var8 = this.tpe.erasure();
               Class var3 = List.class;
               if (var8 == null) {
                  if (var3 == null) {
                     break label97;
                  }
               } else if (var8.equals(var3)) {
                  break label97;
               }

               label98: {
                  var8 = this.tpe.erasure();
                  Class var4 = Set.class;
                  if (var8 == null) {
                     if (var4 == null) {
                        break label98;
                     }
                  } else if (var8.equals(var4)) {
                     break label98;
                  }

                  label99: {
                     var8 = this.tpe.erasure();
                     Class var5 = scala.collection.mutable.Set.class;
                     if (var8 == null) {
                        if (var5 == null) {
                           break label99;
                        }
                     } else if (var8.equals(var5)) {
                        break label99;
                     }

                     label100: {
                        var8 = this.tpe.erasure();
                        Class var6 = Seq.class;
                        if (var8 == null) {
                           if (var6 == null) {
                              break label100;
                           }
                        } else if (var8.equals(var6)) {
                           break label100;
                        }

                        label101: {
                           var8 = this.tpe.erasure();
                           Class var7 = ArrayList.class;
                           if (var8 == null) {
                              if (var7 == null) {
                                 break label101;
                              }
                           } else if (var8.equals(var7)) {
                              break label101;
                           }

                           var10000 = this.tpe.erasure().isArray() ? this.mkCollection((a) -> this.mkTypedArray(a)) : this.mkCollection((array) -> Compat$.MODULE$.makeCollection(this.tpe.erasure(), array).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(28)).append("Expected collection but got ").append(this.tpe).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2())));
                           return var10000;
                        }

                        var10000 = this.mkCollection((a) -> new ArrayList(scala.collection.JavaConverters..MODULE$.asJavaCollectionConverter(scala.Predef..MODULE$.genericWrapArray(a).toList()).asJavaCollection()));
                        return var10000;
                     }

                     var10000 = this.mkCollection((a) -> (Seq)scala.collection.mutable.Seq..MODULE$.apply(scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(a)));
                     return var10000;
                  }

                  var10000 = this.mkCollection((a) -> (scala.collection.mutable.Set)scala.collection.mutable.Set..MODULE$.apply(scala.Predef..MODULE$.copyArrayToImmutableIndexedSeq(a)));
                  return var10000;
               }

               var10000 = this.mkCollection((x$11) -> scala.Predef..MODULE$.genericWrapArray(x$11).toSet());
               return var10000;
            }

            var10000 = this.mkCollection((x$10) -> scala.Predef..MODULE$.genericWrapArray(x$10).toList());
         }

         return var10000;
      }

      // $FF: synthetic method
      private final PartialFunction customRich$lzycompute$1(final LazyRef customRich$lzy$1) {
         synchronized(customRich$lzy$1){}

         PartialFunction var3;
         try {
            var3 = customRich$lzy$1.initialized() ? (PartialFunction)customRich$lzy$1.value() : (PartialFunction)customRich$lzy$1.initialize(Formats$.MODULE$.customRichDeserializer(new Tuple2(this.tpe, this.json), this.formats));
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      }

      private final PartialFunction customRich$1(final LazyRef customRich$lzy$1) {
         return customRich$lzy$1.initialized() ? (PartialFunction)customRich$lzy$1.value() : this.customRich$lzycompute$1(customRich$lzy$1);
      }

      public CollectionBuilder(final JValue json, final ScalaType tpe, final Formats formats) {
         this.json = json;
         this.tpe = tpe;
         this.formats = formats;
         this.typeArg = (ScalaType)tpe.typeArgs().head();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class ClassInstanceBuilder {
      private volatile TypeHint$ TypeHint$module;
      private final JValue json;
      public final ClassDescriptor org$json4s$Extraction$ClassInstanceBuilder$$descr;
      public final Formats org$json4s$Extraction$ClassInstanceBuilder$$formats;
      private ConstructorDescriptor _constructor;

      private TypeHint$ TypeHint() {
         if (this.TypeHint$module == null) {
            this.TypeHint$lzycompute$1();
         }

         return this.TypeHint$module;
      }

      private ConstructorDescriptor constructor() {
         if (this._constructor == null) {
            ConstructorDescriptor var10001;
            if (this.org$json4s$Extraction$ClassInstanceBuilder$$descr.constructors().size() == 1) {
               var10001 = (ConstructorDescriptor)this.org$json4s$Extraction$ClassInstanceBuilder$$descr.constructors().head();
            } else {
               JValue var3 = this.json;
               Object argNames;
               if (var3 instanceof JObject) {
                  JObject var4 = (JObject)var3;
                  List fs = var4.obj();
                  argNames = fs.map((x$13) -> (String)x$13._1());
               } else {
                  argNames = scala.package..MODULE$.Nil();
               }

               Option r = this.org$json4s$Extraction$ClassInstanceBuilder$$descr.bestMatching((List)argNames);
               var10001 = (ConstructorDescriptor)r.getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(26)).append("No constructor for type ").append(this.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure()).append(", ").append(this.json).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()));
            }

            this._constructor = var10001;
         }

         return this._constructor;
      }

      private Object setFields(final Object a) {
         JValue var3 = this.json;
         Object var2;
         if (var3 instanceof JObject) {
            JObject var4 = (JObject)var3;
            List fields = var4.obj();
            Buffer fieldsActuallySet = (Buffer)scala.collection.mutable.Buffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            scala.collection.immutable.Seq ctorArgs = (scala.collection.immutable.Seq)this.constructor().params().map((x$14) -> x$14.name());
            scala.collection.immutable.Seq fieldsToSet = (scala.collection.immutable.Seq)this.org$json4s$Extraction$ClassInstanceBuilder$$descr.properties().filterNot((f) -> BoxesRunTime.boxToBoolean($anonfun$setFields$2(ctorArgs, f)));
            PartialFunction idPf = new Serializable() {
               private static final long serialVersionUID = 0L;

               public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                  return x1;
               }

               public final boolean isDefinedAt(final Tuple2 x1) {
                  boolean var2 = true;
                  return var2;
               }
            };
            this.org$json4s$Extraction$ClassInstanceBuilder$$formats.fieldSerializer(a.getClass()).foreach((serializer) -> {
               $anonfun$setFields$3(this, fields, idPf, a, fieldsToSet, fieldsActuallySet, serializer);
               return BoxedUnit.UNIT;
            });
            if (this.org$json4s$Extraction$ClassInstanceBuilder$$formats.strictOptionParsing()) {
               scala.collection.immutable.Seq diff = (scala.collection.immutable.Seq)((SeqOps)((IterableOps)fieldsToSet.filter((x$17) -> BoxesRunTime.boxToBoolean($anonfun$setFields$13(x$17)))).map((x$18) -> x$18.name())).diff(fieldsActuallySet);
               if (diff.nonEmpty()) {
                  throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(36)).append("No value set for Option properties: ").append(diff.mkString(", ")).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
               }
            }

            var2 = a;
         } else {
            var2 = a;
         }

         return var2;
      }

      public Object org$json4s$Extraction$ClassInstanceBuilder$$buildCtorArg(final JValue json, final ConstructorParamDescriptor descr) {
         try {
            return descr.isOptional() ? this.buildOptionalCtorArg(json, descr) : this.buildMandatoryCtorArg(json, descr);
         } catch (MappingException var4) {
            throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(21)).append("No usable value for ").append(descr.name()).append("\n").append(var4.msg()).toString(), var4);
         }
      }

      private Object buildOptionalCtorArg(final JValue json, final ConstructorParamDescriptor descr) {
         LazyRef default$lzy = new LazyRef();
         boolean var6 = false;
         Object var7 = null;
         JValue var8 = org.json4s.MonadicJValue..MODULE$.$bslash$extension(package$.MODULE$.jvalue2monadic(json), descr.name());
         Object var3;
         if (org.json4s.JNothing..MODULE$.equals(var8)) {
            var6 = true;
            if (json instanceof JObject || !this.org$json4s$Extraction$ClassInstanceBuilder$$formats.strictOptionParsing()) {
               var3 = default$1(default$lzy, descr);
               return var3;
            }
         }

         if (var6) {
            throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(34)).append("No value set for Option property: ").append(descr.name()).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
         } else {
            Object var10000;
            try {
               var10000 = scala.Option..MODULE$.apply(Extraction$.MODULE$.extract(var8, descr.argType(), this.org$json4s$Extraction$ClassInstanceBuilder$$formats)).getOrElse(() -> default$1(default$lzy, descr));
            } catch (Throwable var11) {
               if (!(var11 instanceof MappingException) || this.org$json4s$Extraction$ClassInstanceBuilder$$formats.strictOptionParsing()) {
                  throw var11;
               }

               Object var4 = default$1(default$lzy, descr);
               var10000 = var4;
            }

            var3 = var10000;
            return var3;
         }
      }

      private Object buildMandatoryCtorArg(final JValue json, final ConstructorParamDescriptor descr) {
         LazyRef default$lzy = new LazyRef();
         JValue var5 = org.json4s.MonadicJValue..MODULE$.$bslash$extension(package$.MODULE$.jvalue2monadic(json), descr.name());
         Object var3;
         if (org.json4s.JNothing..MODULE$.equals(var5)) {
            var3 = default$2(default$lzy, descr).getOrElse(() -> Extraction$.MODULE$.extract(org.json4s.JNothing..MODULE$, (ScalaType)descr.argType(), (Formats)this.org$json4s$Extraction$ClassInstanceBuilder$$formats));
         } else {
            label29: {
               if (.MODULE$.equals(var5)) {
                  ExtractionNullStrategy var10000 = this.org$json4s$Extraction$ClassInstanceBuilder$$formats.extractionNullStrategy();
                  ExtractionNullStrategy.TreatAsAbsent$ var6 = ExtractionNullStrategy.TreatAsAbsent$.MODULE$;
                  if (var10000 == null) {
                     if (var6 == null) {
                        break label29;
                     }
                  } else if (var10000.equals(var6)) {
                     break label29;
                  }
               }

               var3 = scala.Option..MODULE$.apply(Extraction$.MODULE$.extract(var5, descr.argType(), this.org$json4s$Extraction$ClassInstanceBuilder$$formats)).getOrElse(() -> {
                  if (descr.defaultValue().isEmpty() && descr.argType().$less$colon$less(ScalaType$.MODULE$.apply(scala.reflect.Manifest..MODULE$.AnyVal()))) {
                     throw new MappingException("Null invalid value for a sub-type of AnyVal");
                  } else {
                     return default$2(default$lzy, descr).orNull(scala..less.colon.less..MODULE$.refl());
                  }
               });
               return var3;
            }

            var3 = default$2(default$lzy, descr).getOrElse(() -> {
               throw new MappingException("Expected value but got null");
            });
         }

         return var3;
      }

      private Object instantiate() {
         Executable jconstructor = this.constructor().constructor();
         JValue var7 = this.json;
         JValue var4;
         if (var7 instanceof JObject) {
            JObject var8 = (JObject)var7;
            List fields = var8.obj();
            var4 = (JValue)this.org$json4s$Extraction$ClassInstanceBuilder$$formats.fieldSerializer(this.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure().erasure()).map((serializer) -> {
               PartialFunction idPf = new Serializable() {
                  private static final long serialVersionUID = 0L;

                  public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
                     return x1;
                  }

                  public final boolean isDefinedAt(final Tuple2 x1) {
                     boolean var2 = true;
                     return var2;
                  }
               };
               return new JObject(fields.map((f) -> (Tuple2)serializer.deserializer().orElse(idPf).apply(f)));
            }).getOrElse(() -> this.json);
         } else {
            if (var7 == null) {
               throw new MatchError(var7);
            }

            var4 = var7;
         }

         JValue deserializedJson = var4;
         scala.collection.immutable.Seq paramsWithOriginalTypes = (scala.collection.immutable.Seq)this.constructor().params().zip(scala.Predef..MODULE$.wrapRefArray((Object[])jconstructor.getParameterTypes()));
         scala.collection.immutable.Seq args = (scala.collection.immutable.Seq)paramsWithOriginalTypes.collect(new Serializable(var4) {
            private static final long serialVersionUID = 0L;
            // $FF: synthetic field
            private final ClassInstanceBuilder $outer;
            private final JValue deserializedJson$1;

            public final Object applyOrElse(final Tuple2 x2, final Function1 default) {
               Object var3;
               if (x2 != null) {
                  Object var4;
                  label52: {
                     BigDecimal var12;
                     label56: {
                        ConstructorParamDescriptor param = (ConstructorParamDescriptor)x2._1();
                        Class clazz = (Class)x2._2();
                        Object rawArg = this.$outer.org$json4s$Extraction$ClassInstanceBuilder$$buildCtorArg(this.deserializedJson$1, param);
                        Tuple2 var9 = new Tuple2(rawArg, clazz);
                        if (var9 != null) {
                           Object arg = var9._1();
                           Class tt = (Class)var9._2();
                           if (arg instanceof BigDecimal) {
                              var12 = (BigDecimal)arg;
                              Class var13 = java.math.BigDecimal.class;
                              if (tt == null) {
                                 if (var13 == null) {
                                    break label56;
                                 }
                              } else if (tt.equals(var13)) {
                                 break label56;
                              }
                           }
                        }

                        BigInt var16;
                        label57: {
                           if (var9 != null) {
                              Object arg = var9._1();
                              Class tt = (Class)var9._2();
                              if (arg instanceof BigInt) {
                                 var16 = (BigInt)arg;
                                 Class var17 = BigInteger.class;
                                 if (tt == null) {
                                    if (var17 == null) {
                                       break label57;
                                    }
                                 } else if (tt.equals(var17)) {
                                    break label57;
                                 }
                              }
                           }

                           if (var9 == null) {
                              throw new MatchError(var9);
                           }

                           Object arg = var9._1();
                           var4 = arg;
                           break label52;
                        }

                        var4 = var16.bigInteger();
                        break label52;
                     }

                     var4 = var12.bigDecimal();
                  }

                  var3 = var4;
               } else {
                  var3 = default.apply(x2);
               }

               return var3;
            }

            public final boolean isDefinedAt(final Tuple2 x2) {
               boolean var2;
               if (x2 != null) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               return var2;
            }

            public {
               if (ClassInstanceBuilder.this == null) {
                  throw null;
               } else {
                  this.$outer = ClassInstanceBuilder.this;
                  this.deserializedJson$1 = deserializedJson$1;
               }
            }
         });

         try {
            Object var28;
            label82: {
               Class var10000 = jconstructor.getDeclaringClass();
               Class var13 = Object.class;
               if (var10000 == null) {
                  if (var13 != null) {
                     break label82;
                  }
               } else if (!var10000.equals(var13)) {
                  break label82;
               }

               Object var3;
               label75: {
                  if (deserializedJson instanceof JObject) {
                     JObject var15 = (JObject)deserializedJson;
                     List var16 = var15.obj();
                     if (var16 != null) {
                        Option var17 = this.TypeHint().unapply(var16);
                        if (!var17.isEmpty()) {
                           String t = (String)((Tuple2)var17.get())._1();
                           List fs = (List)((Tuple2)var17.get())._2();
                           var3 = this.mkWithTypeHint(t, fs, this.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure());
                           break label75;
                        }
                     }
                  }

                  if (deserializedJson == null) {
                     throw new MatchError(deserializedJson);
                  }

                  var3 = deserializedJson.values();
               }

               var28 = var3;
               return var28;
            }

            Object instance = jconstructor.invoke(this.org$json4s$Extraction$ClassInstanceBuilder$$descr.companion(), args);
            var28 = this.setFields(instance);
            return var28;
         } catch (Throwable var27) {
            boolean var2;
            if (var27 instanceof IllegalArgumentException) {
               var2 = true;
            } else if (var27 instanceof InstantiationException) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Some[] constructorParamTypes = (Some[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])jconstructor.getParameterTypes()), (paramType) -> new Some(paramType), scala.reflect.ClassTag..MODULE$.apply(Some.class));
               scala.collection.immutable.Seq argTypes = (scala.collection.immutable.Seq)args.map((arg) -> new Some(arg != null ? arg.getClass() : null));
               String[] argsTypeComparisonResult = (String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipAll$extension(scala.Predef..MODULE$.refArrayOps((Object[])constructorParamTypes), argTypes, scala.None..MODULE$, scala.None..MODULE$)), (x0$1) -> {
                  String var1;
                  if (x0$1 != null) {
                     Option var3 = (Option)x0$1._1();
                     Option var4 = (Option)x0$1._2();
                     if (scala.None..MODULE$.equals(var3) && var4 instanceof Some) {
                        Some var5 = (Some)var4;
                        Class argType = (Class)var5.value();
                        var1 = (new StringBuilder(11)).append("REDUNDANT(").append(argType.getName()).append(")").toString();
                        return var1;
                     }
                  }

                  if (x0$1 != null) {
                     Option var7 = (Option)x0$1._1();
                     Option var8 = (Option)x0$1._2();
                     if (var7 instanceof Some) {
                        Some var9 = (Some)var7;
                        Class constructorParamType = (Class)var9.value();
                        if (scala.None..MODULE$.equals(var8)) {
                           var1 = (new StringBuilder(9)).append("MISSING(").append(constructorParamType.getName()).append(")").toString();
                           return var1;
                        }
                     }
                  }

                  if (x0$1 != null) {
                     Option var11 = (Option)x0$1._1();
                     Option var12 = (Option)x0$1._2();
                     if (var11 instanceof Some) {
                        Some var13 = (Some)var11;
                        Class constructorParamType = (Class)var13.value();
                        if (var12 instanceof Some) {
                           Some var15 = (Some)var12;
                           Class argType = (Class)var15.value();
                           if (argType == null || constructorParamType.isAssignableFrom(argType)) {
                              var1 = "MATCH";
                              return var1;
                           }
                        }
                     }
                  }

                  if (x0$1 == null) {
                     throw new MatchError(x0$1);
                  } else {
                     Option var17 = (Option)x0$1._1();
                     Option var18 = (Option)x0$1._2();
                     if (!(var17 instanceof Some)) {
                        throw new MatchError(x0$1);
                     } else {
                        Some var19 = (Some)var17;
                        Class constructorParamType = (Class)var19.value();
                        if (!(var18 instanceof Some)) {
                           throw new MatchError(x0$1);
                        } else {
                           Some var21 = (Some)var18;
                           Class argType = (Class)var21.value();
                           var1 = (new StringBuilder(9)).append(argType.getName()).append("(").append(argType.getClassLoader()).append(") !<: ").append(constructorParamType.getName()).append("(").append(constructorParamType.getClassLoader()).append(")").toString();
                           return var1;
                        }
                     }
                  }
               }, scala.reflect.ClassTag..MODULE$.apply(String.class));
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(115)).append("Parsed JSON values do not match with class constructor\nargs=").append(args.mkString(",")).append("\narg types=").append(((IterableOnceOps)args.map((a) -> a != null ? a.getClass().getName() : "null")).mkString(",")).append("\nexecutable=").append(jconstructor).append("\ncause=").append(var27.getMessage()).append("\ntypes comparison result=").append(scala.Predef..MODULE$.wrapRefArray((Object[])argsTypeComparisonResult).mkString(",")).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            } else {
               throw var27;
            }
         }
      }

      private Object mkWithTypeHint(final String typeHint, final List fields, final ScalaType typeInfo) {
         JObject obj = new JObject(fields.filterNot((x$21) -> BoxesRunTime.boxToBoolean($anonfun$mkWithTypeHint$1(this, typeInfo, x$21))));
         PartialFunction deserializer = this.org$json4s$Extraction$ClassInstanceBuilder$$formats.typeHints().deserialize();
         Object var10000;
         if (!deserializer.isDefinedAt(new Tuple2(typeHint, obj))) {
            Class concreteClass = (Class)this.org$json4s$Extraction$ClassInstanceBuilder$$formats.typeHints().classFor(typeHint, typeInfo.erasure()).getOrElse(() -> org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(33)).append("Do not know how to deserialize '").append(typeHint).append("'").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2()));
            if (!typeInfo.erasure().isAssignableFrom(concreteClass)) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(72)).append("Type hint ").append(typeHint).append(" which refers to class ").append(concreteClass.getName()).append(" cannot be extracted as an instance of ").append(typeInfo.erasure().getName()).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }

            var10000 = Extraction$.MODULE$.extract(obj, (ScalaType)typeInfo.copy(concreteClass, typeInfo.copy$default$2(), typeInfo.copy$default$3()), (Formats)this.org$json4s$Extraction$ClassInstanceBuilder$$formats);
         } else {
            var10000 = deserializer.apply(new Tuple2(typeHint, obj));
         }

         return var10000;
      }

      public Object result() {
         boolean var2 = false;
         Object var3 = null;
         JValue var4 = this.json;
         if (.MODULE$.equals(var4)) {
            var2 = true;
            if (this.org$json4s$Extraction$ClassInstanceBuilder$$formats.strictOptionParsing() && this.org$json4s$Extraction$ClassInstanceBuilder$$descr.properties().exists((x$22) -> BoxesRunTime.boxToBoolean($anonfun$result$9(x$22)))) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(34)).append("No value set for Option property: ").append(((IterableOnceOps)((IterableOps)this.org$json4s$Extraction$ClassInstanceBuilder$$descr.properties().filter((x$23) -> BoxesRunTime.boxToBoolean($anonfun$result$10(x$23)))).map((x$24) -> x$24.name())).mkString(", ")).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }
         }

         Object var1;
         label58: {
            if (var2) {
               ExtractionNullStrategy var10000 = this.org$json4s$Extraction$ClassInstanceBuilder$$formats.extractionNullStrategy();
               ExtractionNullStrategy.Keep$ var5 = ExtractionNullStrategy.Keep$.MODULE$;
               if (var10000 == null) {
                  if (var5 == null) {
                     break label58;
                  }
               } else if (var10000.equals(var5)) {
                  break label58;
               }
            }

            if (var2) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(47)).append("Did not find value which can be converted into ").append(this.org$json4s$Extraction$ClassInstanceBuilder$$descr.fullName()).toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }

            if (var4 instanceof JObject) {
               JObject var6 = (JObject)var4;
               List var7 = var6.obj();
               if (var7 != null) {
                  Option var8 = this.TypeHint().unapply(var7);
                  if (!var8.isEmpty()) {
                     String t = (String)((Tuple2)var8.get())._1();
                     List fs = (List)((Tuple2)var8.get())._2();
                     var1 = this.mkWithTypeHint(t, fs, this.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure());
                     return var1;
                  }
               }
            }

            var1 = this.instantiate();
            return var1;
         }

         var1 = null;
         return var1;
      }

      private final void TypeHint$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.TypeHint$module == null) {
               this.TypeHint$module = new TypeHint$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      // $FF: synthetic method
      public static final boolean $anonfun$setFields$2(final scala.collection.immutable.Seq ctorArgs$1, final PropertyDescriptor f) {
         return ctorArgs$1.contains(f.name());
      }

      // $FF: synthetic method
      public static final boolean $anonfun$setFields$5(final Object a$2, final Tuple2 x0$1) {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            boolean var10000;
            label21: {
               label20: {
                  Class clazz = (Class)x0$1._1();
                  Class var5 = a$2.getClass();
                  if (clazz == null) {
                     if (var5 == null) {
                        break label20;
                     }
                  } else if (clazz.equals(var5)) {
                     break label20;
                  }

                  var10000 = false;
                  break label21;
               }

               var10000 = true;
            }

            boolean var2 = var10000;
            return var2;
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$setFields$10(final Set setOfDeserializableFields$1, final Tuple2 x0$2) {
         if (x0$2 != null) {
            String propName = (String)x0$2._1();
            if (propName != null && x0$2._2() != null && !setOfDeserializableFields$1.contains(propName)) {
               throw org.json4s.reflect.package$.MODULE$.fail((new StringBuilder(83)).append("Attempted to deserialize JField ").append(propName).append(" into undefined property on target ClassDescriptor.").toString(), org.json4s.reflect.package$.MODULE$.fail$default$2());
            }
         }

         BoxedUnit var2 = BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final void $anonfun$setFields$11(final ClassInstanceBuilder $this, final Map jsonSerializers$1, final FieldSerializer serializer$1, final Object a$2, final Buffer fieldsActuallySet$1, final PropertyDescriptor prop) {
         jsonSerializers$1.get(prop.name()).foreach((x0$3) -> {
            if (x0$3 != null) {
               JValue v = (JValue)x0$3._2();
               Object vv = Extraction$.MODULE$.extract(v, prop.returnType(), $this.org$json4s$Extraction$ClassInstanceBuilder$$formats);
               if (serializer$1.includeLazyVal()) {
                  Extraction$.MODULE$.loadLazyValValue(a$2, prop.name(), vv);
               } else {
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               }

               prop.set(a$2, vv);
               Buffer var6 = (Buffer)fieldsActuallySet$1.$plus$eq(prop.name());
               return var6;
            } else {
               throw new MatchError(x0$3);
            }
         });
      }

      // $FF: synthetic method
      public static final void $anonfun$setFields$3(final ClassInstanceBuilder $this, final List fields$1, final PartialFunction idPf$1, final Object a$2, final scala.collection.immutable.Seq fieldsToSet$1, final Buffer fieldsActuallySet$1, final FieldSerializer serializer) {
         Map jsonSerializers = fields$1.map((f) -> {
            Tuple2 var5 = (Tuple2)serializer.deserializer().orElse(idPf$1).apply(f);
            if (var5 != null) {
               Tuple2 var6 = org.json4s.JField..MODULE$.unapply(var5);
               if (!org.json4s.SomeValue..MODULE$.isEmpty$extension(var6)) {
                  String n = (String)var6._1();
                  JValue v = (JValue)var6._2();
                  Tuple2 var3 = new Tuple2(n, v);
                  String nx = (String)var3._1();
                  JValue vx = (JValue)var3._2();
                  return new Tuple2(nx, new Tuple2(nx, vx));
               }
            }

            throw new MatchError(var5);
         }).toMap(scala..less.colon.less..MODULE$.refl());
         if ($this.org$json4s$Extraction$ClassInstanceBuilder$$formats.strictFieldDeserialization()) {
            List renamedFields;
            label16: {
               Option maybeClassSerializer = $this.org$json4s$Extraction$ClassInstanceBuilder$$formats.fieldSerializers().find((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$setFields$5(a$2, x0$1)));
               if (maybeClassSerializer instanceof Some) {
                  Some var12 = (Some)maybeClassSerializer;
                  Tuple2 var13 = (Tuple2)var12.value();
                  if (var13 != null) {
                     FieldSerializer fieldSerializer = (FieldSerializer)var13._2();
                     renamedFields = fields$1.map((field) -> (Tuple2)scala.util.Try..MODULE$.apply(() -> (Tuple2)fieldSerializer.deserializer().apply(field)).getOrElse(() -> field));
                     break label16;
                  }
               }

               renamedFields = fields$1;
            }

            Set setOfDeserializableFields = ((IterableOnceOps)$this.org$json4s$Extraction$ClassInstanceBuilder$$descr.properties().map((x$16) -> x$16.name())).toSet();
            renamedFields.foreach((x0$2) -> {
               $anonfun$setFields$10(setOfDeserializableFields, x0$2);
               return BoxedUnit.UNIT;
            });
         }

         fieldsToSet$1.foreach((prop) -> {
            $anonfun$setFields$11($this, jsonSerializers, serializer, a$2, fieldsActuallySet$1, prop);
            return BoxedUnit.UNIT;
         });
      }

      // $FF: synthetic method
      public static final boolean $anonfun$setFields$13(final PropertyDescriptor x$17) {
         return x$17.returnType().isOption();
      }

      // $FF: synthetic method
      private static final Object default$lzycompute$1(final LazyRef default$lzy$1, final ConstructorParamDescriptor descr$1) {
         synchronized(default$lzy$1){}

         Object var3;
         try {
            var3 = default$lzy$1.initialized() ? default$lzy$1.value() : default$lzy$1.initialize(descr$1.defaultValue().map((x$19) -> x$19.apply()).getOrElse(() -> scala.None..MODULE$));
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      }

      private static final Object default$1(final LazyRef default$lzy$1, final ConstructorParamDescriptor descr$1) {
         return default$lzy$1.initialized() ? default$lzy$1.value() : default$lzycompute$1(default$lzy$1, descr$1);
      }

      // $FF: synthetic method
      private static final Option default$lzycompute$2(final LazyRef default$lzy$2, final ConstructorParamDescriptor descr$2) {
         synchronized(default$lzy$2){}

         Option var3;
         try {
            var3 = default$lzy$2.initialized() ? (Option)default$lzy$2.value() : (Option)default$lzy$2.initialize(descr$2.defaultValue().map((x$20) -> x$20.apply()));
         } catch (Throwable var5) {
            throw var5;
         }

         return var3;
      }

      private static final Option default$2(final LazyRef default$lzy$2, final ConstructorParamDescriptor descr$2) {
         return default$lzy$2.initialized() ? (Option)default$lzy$2.value() : default$lzycompute$2(default$lzy$2, descr$2);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$mkWithTypeHint$1(final ClassInstanceBuilder $this, final ScalaType typeInfo$1, final Tuple2 x$21) {
         return $this.org$json4s$Extraction$ClassInstanceBuilder$$formats.typeHints().isTypeHintField(x$21, typeInfo$1.erasure());
      }

      // $FF: synthetic method
      public static final boolean $anonfun$result$9(final PropertyDescriptor x$22) {
         return x$22.returnType().isOption();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$result$10(final PropertyDescriptor x$23) {
         return x$23.returnType().isOption();
      }

      public ClassInstanceBuilder(final JValue json, final ClassDescriptor descr, final Formats formats) {
         this.json = json;
         this.org$json4s$Extraction$ClassInstanceBuilder$$descr = descr;
         this.org$json4s$Extraction$ClassInstanceBuilder$$formats = formats;
         this._constructor = null;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class TypeHint$ {
         // $FF: synthetic field
         private final ClassInstanceBuilder $outer;

         public Option unapply(final List fs) {
            Object var10000;
            if (!this.$outer.org$json4s$Extraction$ClassInstanceBuilder$$formats.typeHints().shouldExtractHints(this.$outer.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure().erasure())) {
               var10000 = scala.None..MODULE$;
            } else {
               Object var2;
               label29: {
                  label33: {
                     Tuple2 var3 = fs.partition((x$12) -> BoxesRunTime.boxToBoolean($anonfun$unapply$1(this, x$12)));
                     if (var3 != null) {
                        List var4 = (List)var3._1();
                        Nil var8 = scala.package..MODULE$.Nil();
                        if (var8 == null) {
                           if (var4 == null) {
                              break label33;
                           }
                        } else if (var8.equals(var4)) {
                           break label33;
                        }
                     }

                     if (var3 == null) {
                        throw new MatchError(var3);
                     }

                     List t = (List)var3._1();
                     List f = (List)var3._2();
                     var2 = new Some(new Tuple2(((JValue)((Tuple2)t.head())._2()).values().toString(), f));
                     break label29;
                  }

                  var2 = scala.None..MODULE$;
               }

               var10000 = var2;
            }

            return (Option)var10000;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$unapply$1(final TypeHint$ $this, final Tuple2 x$12) {
            return $this.$outer.org$json4s$Extraction$ClassInstanceBuilder$$formats.typeHints().isTypeHintField(x$12, $this.$outer.org$json4s$Extraction$ClassInstanceBuilder$$descr.erasure().erasure());
         }

         public TypeHint$() {
            if (ClassInstanceBuilder.this == null) {
               throw null;
            } else {
               this.$outer = ClassInstanceBuilder.this;
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
