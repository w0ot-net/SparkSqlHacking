package scala.reflect.internal.tpe;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.Clearable;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Definitions;
import scala.reflect.internal.Depth$;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.Variance;
import scala.reflect.internal.Variance$;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.ReusableInstance;
import scala.reflect.internal.util.ReusableInstance$;
import scala.reflect.internal.util.StatisticsStatics;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rEeA\u0003+V!\u0003\r\taV/\u0002D!)!\r\u0001C\u0001I\"A\u0001\u000e\u0001EC\u0002\u0013%\u0011\u000e\u0003\u0004\u0004$\u0001!\t!\u001b\u0004\u0005Y\u0002\u0001Q\u000eC\u0003w\t\u0011\u0005q/\u0002\u0003y\t\u0001I\b\"\u0003Bv\t\u0001\u0007I\u0011\u0001Bw\u0011%\u0011\u0019\u0010\u0002a\u0001\n\u0003\u0011)\u0010\u0003\u0005\u0003z\u0012\u0001\u000b\u0015\u0002Bx\u0011\u001d\u0011Y\u0010\u0002C\u0001\u0005{D\u0001ba\u0001\u0005\t\u0003I6Q\u0001\u0005\u0007\u0007\u0013!A\u0011\u00013\t\u000f\r-A\u0001\"\u0001\u0004\u000e!Q1Q\u0005\u0001\t\u0006\u0004%I!a@\t\u0015\r\u001d\u0002\u0001#b\u0001\n\u0013\tyP\u0002\u0004\u0002\\\u0001\u0001\u00111\r\u0005\u000b\u0003K\u0002\"\u0011!Q\u0001\n\u0005\u001d\u0004BCA8!\t\u0005\t\u0015!\u0003\u0002h!Q\u0011\u0011\u000f\t\u0003\u0002\u0003\u0006I!!\u001b\t\u0015\u0005M\u0004C!A!\u0002\u0013\tI\u0007\u0003\u0006\u0002vA\u0011\t\u0011)A\u0005\u0003oBaA\u001e\t\u0005\u0002\u0005u\u0004B\u0002<\u0011\t\u0003\tY\t\u0003\u0004w!\u0011\u0005\u0011\u0011\u0013\u0005\u0007mB!\t!!(\t\u0011\u0005}\u0005\u0003)Q\u0005\u0003CC\u0001\"a+\u0011A\u0003&\u0011\u0011\u0015\u0005\t\u0003[\u0003\u0002\u0015)\u0003\u0002j!A\u0011q\u0016\t!B\u0013\tI\u0007\u0003\u0005\u00022B\u0001\u000b\u0015BA<\u0011\u001d\t\u0019\f\u0005C\u0001\u0003kCq!a.\u0011\t\u0003\t)\fC\u0004\u0002:B!\t!a/\t\r\u0005u\u0006\u0003\"\u0001e\u0011\u0019\ty\f\u0005C\u0001I\"9\u0011\u0011\u0019\t\u0005\u0002\u0005\r\u0007\"CAg!E\u0005I\u0011AAh\u0011\u001d\t)\u000f\u0005C\u0001\u0003ODq!a;\u0011\t\u0003\ti\u000fC\u0005\u0002tB\t\n\u0011\"\u0001\u0002P\"9\u0011Q\u001f\t\u0005\u0002\u0005m\u0006bBA|!\u0011\u0005\u0011\u0011 \u0005\n\u0003{\u0004\u0002\u0019!C\u0001\u0003\u007fD\u0011B!\u0001\u0011\u0001\u0004%\tAa\u0001\t\u0011\t%\u0001\u0003)Q\u0005\u0003SBqAa\u0003\u0011\t\u0003\tY\fC\u0004\u0003\u000eA!\tAa\u0004\t\u000f\tE\u0001\u0003\"\u0011\u0003\u0014\u001dI1\u0011\u0006\u0001\u0002\u0002#\u000511\u0006\u0004\n\u00037\u0002\u0011\u0011!E\u0001\u0007[AaA\u001e\u001a\u0005\u0002\r=\u0002\"CB\u0019eE\u0005I\u0011AAh\u0011%\u0019\u0019\u0004\u0001b!\n\u0013\u0019)\u0004\u0003\u0005\u0004N\u0001\u0001K\u0011BB(\u0011\u001d\u0019\t\u0007\u0001C\u0001\u0007G:\u0001\"a\u0002V\u0011\u00039\u0016\u0011\u0002\u0004\b)VC\taVA\u0006\u0011\u00191\u0018\b\"\u0001\u0002\u000e\u00191\u0011qB\u001dC\u0003#A!\"!\f<\u0005+\u0007I\u0011AA\u0018\u0011)\t\tf\u000fB\tB\u0003%\u0011\u0011\u0007\u0005\u000b\u0003'Z$Q3A\u0005\u0002\u0005U\u0003B\u0003B\u0013w\tE\t\u0015!\u0003\u0002X!1ao\u000fC\u0001\u0005OA\u0011B!\r<\u0003\u0003%\tAa\r\t\u0013\t\u00153(%A\u0005\u0002\t\u001d\u0003\"\u0003B)wE\u0005I\u0011\u0001B*\u0011%\u0011ifOA\u0001\n\u0003\u0012y\u0006C\u0005\u0003bm\n\t\u0011\"\u0001\u0003d!I!1N\u001e\u0002\u0002\u0013\u0005!Q\u000e\u0005\n\u0005oZ\u0014\u0011!C!\u0005sB\u0011Ba!<\u0003\u0003%\tA!\"\t\u0013\t%5(!A\u0005B\t-\u0005\"\u0003BHw\u0005\u0005I\u0011\tBI\u0011%\u0011\tbOA\u0001\n\u0003\u0012\u0019\u0002C\u0005\u0003\u0014n\n\t\u0011\"\u0011\u0003\u0016\u001eI!\u0011T\u001d\u0002\u0002#\u0005!1\u0014\u0004\n\u0003\u001fI\u0014\u0011!E\u0001\u0005;CaA\u001e(\u0005\u0002\t%\u0006\"\u0003B\t\u001d\u0006\u0005IQ\tB\n\u0011%\u0011YKTA\u0001\n\u0003\u0013i\u000bC\u0005\u0003@:\u000b\t\u0011\"!\u0003B\"I!q\u001c(\u0002\u0002\u0013%!\u0011\u001d\u0002\u0010)f\u0004XmQ8ogR\u0014\u0018-\u001b8ug*\u0011akV\u0001\u0004iB,'B\u0001-Z\u0003!Ig\u000e^3s]\u0006d'B\u0001.\\\u0003\u001d\u0011XM\u001a7fGRT\u0011\u0001X\u0001\u0006g\u000e\fG.Y\n\u0003\u0001y\u0003\"a\u00181\u000e\u0003mK!!Y.\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A3\u0011\u0005}3\u0017BA4\\\u0005\u0011)f.\u001b;\u0002\u0011}+h\u000eZ8M_\u001e,\u0012A\u001b\t\u0003W\u0012i\u0011\u0001\u0001\u0002\b+:$w\u000eT8h'\r!aL\u001c\t\u0003_Rl\u0011\u0001\u001d\u0006\u0003cJ\fq!\\;uC\ndWM\u0003\u0002t7\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005U\u0004(!C\"mK\u0006\u0014\u0018M\u00197f\u0003\u0019a\u0014N\\5u}Q\t!NA\u0005V]\u0012|\u0007+Y5sgB!!0`A\u0001\u001d\ty60\u0003\u0002}7\u00069\u0001/Y2lC\u001e,\u0017B\u0001@\u0000\u0005\u0011a\u0015n\u001d;\u000b\u0005q\\\u0006cBA\u0002w\t%\u0018q\u0010\b\u0004\u0003\u000bAT\"A+\u0002\u001fQK\b/Z\"p]N$(/Y5oiN\u00042!!\u0002:'\tId\f\u0006\u0002\u0002\n\tAQK\u001c3p!\u0006L'/\u0006\u0004\u0002\u0014\u0005U\u0012\u0011L\n\u0007wy\u000b)\"a\u0007\u0011\u0007}\u000b9\"C\u0002\u0002\u001am\u0013q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002\u001e\u0005%bbAA\u0010w:!\u0011\u0011EA\u0014\u001b\t\t\u0019CC\u0002\u0002&\r\fa\u0001\u0010:p_Rt\u0014\"\u0001/\n\u0007\u0005-rP\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0002umV\u0011\u0011\u0011\u0007\t\u0005\u0003g\t)\u0004\u0004\u0001\u0005\u000f\u0005]2H1\u0001\u0002:\t9A+\u001f9f-\u0006\u0014\u0018\u0003BA\u001e\u0003\u0003\u00022aXA\u001f\u0013\r\tyd\u0017\u0002\b\u001d>$\b.\u001b8h!\u0011\t\u0019%a\u0013\u0011\t\u0005\u0015\u0013qI\u0007\u0002/&\u0019\u0011\u0011J,\u0003\u0017MKXNY8m)\u0006\u0014G.Z\u0005\u0005\u0003o\ti%C\u0002\u0002P]\u0013Q\u0001V=qKN\f1\u0001\u001e<!\u0003-!8i\u001c8tiJ\f\u0017N\u001c;\u0016\u0005\u0005]\u0003\u0003BA\u001a\u00033\"q!a\u0017<\u0005\u0004\tiF\u0001\bUsB,7i\u001c8tiJ\f\u0017N\u001c;\u0012\t\u0005m\u0012q\f\t\u0004\u0003C\u0002\u0002cAA\u0003\u0001M\u0011\u0001CX\u0001\u0004Y>\u0004\u0004\u0003\u0002>~\u0003S\u00022a[A6\u0013\u0011\ti'!\u0014\u0003\tQK\b/Z\u0001\u0004Q&\u0004\u0014A\u00028v[2|\u0007'\u0001\u0004ok6D\u0017\u000eM\u0001\u000fCZ|\u0017\u000eZ,jI\u0016t\u0017N\\41!\ry\u0016\u0011P\u0005\u0004\u0003wZ&a\u0002\"p_2,\u0017M\u001c\u000b\r\u0003\u007f\n\t)a!\u0002\u0006\u0006\u001d\u0015\u0011\u0012\t\u0003WBAq!!\u001a\u0017\u0001\u0004\t9\u0007C\u0004\u0002pY\u0001\r!a\u001a\t\u000f\u0005Ed\u00031\u0001\u0002j!9\u00111\u000f\fA\u0002\u0005%\u0004\"CA;-A\u0005\t\u0019AA<)\u0019\ty(!$\u0002\u0010\"9\u0011QM\fA\u0002\u0005\u001d\u0004bBA8/\u0001\u0007\u0011q\r\u000b\u0005\u0003\u007f\n\u0019\nC\u0004\u0002\u0016b\u0001\r!a&\u0002\r\t|WO\u001c3t!\rY\u0017\u0011T\u0005\u0005\u00037\u000biE\u0001\u0006UsB,'i\\;oIN$\"!a \u0002\u00111|'m\\;oIN\u0004b!a)\u0002*\u0006%TBAAS\u0015\r\t9K]\u0001\nS6lW\u000f^1cY\u0016L1A`AS\u0003!A\u0017NY8v]\u0012\u001c\u0018!\u00028v[2|\u0017!\u00028v[\"L\u0017!D1w_&$w+\u001b3f]&tw-\u0001\u0005m_\n{WO\u001c3t+\t\t9'\u0001\u0005iS\n{WO\u001c3t\u0003)\tgo\\5e/&$WM\\\u000b\u0003\u0003o\nAb\u001d;pa^KG-\u001a8j]\u001e\fqc\u001d;pa^KG-\u001a8j]\u001eLe\r\u0015:fG2,H-\u001a3\u0002\u0015\u0005$G\rT8C_VtG\rF\u0003f\u0003\u000b\fI\rC\u0004\u0002H\u0012\u0002\r!!\u001b\u0002\u0005Q\u0004\b\"CAfIA\u0005\t\u0019AA<\u00039I7OT;nKJL7MQ8v]\u0012\fA#\u00193e\u0019>\u0014u.\u001e8eI\u0011,g-Y;mi\u0012\u0012TCAAiU\u0011\t9(a5,\u0005\u0005U\u0007\u0003BAl\u0003Cl!!!7\u000b\t\u0005m\u0017Q\\\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a8\\\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003G\fINA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fQb\u00195fG.<\u0016\u000eZ3oS:<GcA3\u0002j\"9\u0011q\u0019\u0014A\u0002\u0005%\u0014AC1eI\"K'i\\;oIR)Q-a<\u0002r\"9\u0011qY\u0014A\u0002\u0005%\u0004\"CAfOA\u0005\t\u0019AA<\u0003Q\tG\r\u001a%j\u0005>,h\u000e\u001a\u0013eK\u001a\fW\u000f\u001c;%e\u0005\u0001\u0012N\\:u/&$\b.\u001b8C_VtGm]\u0001\u000fSN<\u0016\u000e\u001e5j]\n{WO\u001c3t)\u0011\t9(a?\t\u000f\u0005\u001d'\u00061\u0001\u0002j\u0005!\u0011N\\:u+\t\tI'\u0001\u0005j]N$x\fJ3r)\r)'Q\u0001\u0005\n\u0005\u000fa\u0013\u0011!a\u0001\u0003S\n1\u0001\u001f\u00132\u0003\u0015Ign\u001d;!\u0003%Ign\u001d;WC2LG-A\u0007dY>tW-\u00138uKJt\u0017\r\\\u000b\u0003\u0003\u007f\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0005+\u0001BAa\u0006\u0003\"5\u0011!\u0011\u0004\u0006\u0005\u00057\u0011i\"\u0001\u0003mC:<'B\u0001B\u0010\u0003\u0011Q\u0017M^1\n\t\t\r\"\u0011\u0004\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019Q\u001cuN\\:ue\u0006Lg\u000e\u001e\u0011\u0015\r\t%\"Q\u0006B\u0018!\u001d\u0011YcOA\u0019\u0003/j\u0011!\u000f\u0005\b\u0003[\u0001\u0005\u0019AA\u0019\u0011\u001d\t\u0019\u0006\u0011a\u0001\u0003/\nAaY8qsV1!Q\u0007B\u001e\u0005\u007f!bAa\u000e\u0003B\t\r\u0003c\u0002B\u0016w\te\"Q\b\t\u0005\u0003g\u0011Y\u0004B\u0004\u00028\u0005\u0013\r!!\u000f\u0011\t\u0005M\"q\b\u0003\b\u00037\n%\u0019AA/\u0011%\ti#\u0011I\u0001\u0002\u0004\u0011I\u0004C\u0005\u0002T\u0005\u0003\n\u00111\u0001\u0003>\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0002B%\u0005\u001b\u0012y%\u0006\u0002\u0003L)\"\u0011\u0011GAj\t\u001d\t9D\u0011b\u0001\u0003s!q!a\u0017C\u0005\u0004\ti&\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\r\tU#\u0011\fB.+\t\u00119F\u000b\u0003\u0002X\u0005MGaBA\u001c\u0007\n\u0007\u0011\u0011\b\u0003\b\u00037\u001a%\u0019AA/\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!QC\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0005K\u00022a\u0018B4\u0013\r\u0011Ig\u0017\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0005_\u0012)\bE\u0002`\u0005cJ1Aa\u001d\\\u0005\r\te.\u001f\u0005\n\u0005\u000f1\u0015\u0011!a\u0001\u0005K\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005w\u0002bA! \u0003\u0000\t=T\"\u0001:\n\u0007\t\u0005%O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA<\u0005\u000fC\u0011Ba\u0002I\u0003\u0003\u0005\rAa\u001c\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005+\u0011i\tC\u0005\u0003\b%\u000b\t\u00111\u0001\u0003f\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003f\u00051Q-];bYN$B!a\u001e\u0003\u0018\"I!q\u0001'\u0002\u0002\u0003\u0007!qN\u0001\t+:$w\u000eU1jeB\u0019!1\u0006(\u0014\t9s&q\u0014\t\u0005\u0005C\u00139+\u0004\u0002\u0003$*!!Q\u0015B\u000f\u0003\tIw.\u0003\u0003\u0002,\t\rFC\u0001BN\u0003\u0015\t\u0007\u000f\u001d7z+\u0019\u0011yK!.\u0003:R1!\u0011\u0017B^\u0005{\u0003rAa\u000b<\u0005g\u00139\f\u0005\u0003\u00024\tUFaBA\u001c#\n\u0007\u0011\u0011\b\t\u0005\u0003g\u0011I\fB\u0004\u0002\\E\u0013\r!!\u0018\t\u000f\u00055\u0012\u000b1\u0001\u00034\"9\u00111K)A\u0002\t]\u0016aB;oCB\u0004H._\u000b\u0007\u0005\u0007\u0014\u0019Na6\u0015\t\t\u0015'\u0011\u001c\t\u0006?\n\u001d'1Z\u0005\u0004\u0005\u0013\\&AB(qi&|g\u000eE\u0004`\u0005\u001b\u0014\tN!6\n\u0007\t=7L\u0001\u0004UkBdWM\r\t\u0005\u0003g\u0011\u0019\u000eB\u0004\u00028I\u0013\r!!\u000f\u0011\t\u0005M\"q\u001b\u0003\b\u00037\u0012&\u0019AA/\u0011%\u0011YNUA\u0001\u0002\u0004\u0011i.A\u0002yIA\u0002rAa\u000b<\u0005#\u0014).\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003dB!!q\u0003Bs\u0013\u0011\u00119O!\u0007\u0003\r=\u0013'.Z2u!\rY\u00171J\u0001\u0004Y><WC\u0001Bx!\r\u0011\tPB\u0007\u0002\t\u00059An\\4`I\u0015\fHcA3\u0003x\"I!q\u0001\u0005\u0002\u0002\u0003\u0007!q^\u0001\u0005Y><\u0007%\u0001\u0004v]\u0012|Gk\u001c\u000b\u0004K\n}\bbBB\u0001\u0015\u0001\u0007!q^\u0001\u0006Y&l\u0017\u000e^\u0001\u0007e\u0016\u001cwN\u001d3\u0015\u0007\u0015\u001c9\u0001C\u0004\u0002.-\u0001\rA!;\u0002\u000b\rdW-\u0019:\u0002\tUtGm\\\u000b\u0005\u0007\u001f\u0019\u0019\u0002\u0006\u0003\u0004\u0012\re\u0001\u0003BA\u001a\u0007'!qa!\u0006\u000e\u0005\u0004\u00199BA\u0001U#\u0011\tYDa\u001c\t\u0011\rmQ\u0002\"a\u0001\u0007;\tQA\u00197pG.\u0004RaXB\u0010\u0007#I1a!\t\\\u0005!a$-\u001f8b[\u0016t\u0014aB;oI>dunZ\u0001\u000f]VlWM]5d\u0019>\u0014u.\u001e8e\u00039qW/\\3sS\u000eD\u0015NQ8v]\u0012\fa\u0002V=qK\u000e{gn\u001d;sC&tG\u000f\u0005\u0002leM\u0011!G\u0018\u000b\u0003\u0007W\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*\u0014AG2p]R\f\u0017N\\:D_2dWm\u0019;pe&s7\u000f^1oG\u0016\u001cXCAB\u001c!\u0019\u0019Ida\u0010\u0004D5\u001111\b\u0006\u0004\u0007{9\u0016\u0001B;uS2LAa!\u0011\u0004<\t\u0001\"+Z;tC\ndW-\u00138ti\u0006t7-\u001a\t\u0004W\u000e\u0015\u0013\u0002BB$\u0007\u0013\u0012\u0011cQ8oi\u0006Lgn]\"pY2,7\r^8s\u0013\r\u0019Y%\u0016\u0002\t)f\u0004X-T1qg\u0006q1m\u001c8uC&t7oU=nE>dGCBA<\u0007#\u001a\u0019\u0006C\u0004\u0002HZ\u0002\r!!\u001b\t\u000f\rUc\u00071\u0001\u0004X\u0005\u00191/_7\u0011\u0007-\u001cI&\u0003\u0003\u0004\\\ru#AB*z[\n|G.C\u0002\u0004`]\u0013qaU=nE>d7/A\u0003t_24X\r\u0006\u0007\u0002x\r\u001541NB9\u0007\u0007\u001b9\tC\u0004\u0004h]\u0002\ra!\u001b\u0002\u000bQ4\u0018M]:\u0011\til(\u0011\u001e\u0005\b\u0007[:\u0004\u0019AB8\u0003\u001d!\b/\u0019:b[N\u0004BA_?\u0004X!911O\u001cA\u0002\rU\u0014aC4fiZ\u000b'/[1oG\u0016\u0004baa\u001e\u0004~\r]c\u0002BA#\u0007sJ1aa\u001fX\u0003!1\u0016M]5b]\u000e,\u0017\u0002BB@\u0007\u0003\u0013\u0011\"\u0012=ue\u0006\u001cGo\u001c:\u000b\u0007\rmt\u000bC\u0004\u0004\u0006^\u0002\r!a\u001e\u0002\u000bU\u0004\b/\u001a:\t\u000f\r%u\u00071\u0001\u0004\f\u0006)A-\u001a9uQB!\u0011QIBG\u0013\r\u0019yi\u0016\u0002\u0006\t\u0016\u0004H\u000f\u001b"
)
public interface TypeConstraints {
   TypeConstraint$ TypeConstraint();

   void scala$reflect$internal$tpe$TypeConstraints$_setter_$scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances_$eq(final ReusableInstance x$1);

   // $FF: synthetic method
   static UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog$(final TypeConstraints $this) {
      return $this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog();
   }

   default UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog() {
      return (SymbolTable)this.new UndoLog();
   }

   // $FF: synthetic method
   static UndoLog undoLog$(final TypeConstraints $this) {
      return $this.undoLog();
   }

   default UndoLog undoLog() {
      return this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog();
   }

   // $FF: synthetic method
   static Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$(final TypeConstraints $this) {
      return $this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound();
   }

   default Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound() {
      return ((Definitions)this).definitions().IntTpe();
   }

   // $FF: synthetic method
   static Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$(final TypeConstraints $this) {
      return $this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound();
   }

   default Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound() {
      return ((Types)this).intersectionType(new .colon.colon(((Definitions)this).definitions().ByteTpe(), new .colon.colon(((Definitions)this).definitions().CharTpe(), scala.collection.immutable.Nil..MODULE$)), ((Definitions)this).definitions().ScalaPackageClass());
   }

   ReusableInstance scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances();

   private boolean containsSymbol(final Types.Type tp, final Symbols.Symbol sym) {
      ReusableInstance var10000 = this.scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances();
      if (var10000 == null) {
         throw null;
      } else {
         ReusableInstance using_this = var10000;
         if (using_this.scala$reflect$internal$util$ReusableInstance$$cache == null) {
            TypeMaps.ContainsCollector var10 = (TypeMaps.ContainsCollector)using_this.scala$reflect$internal$util$ReusableInstance$$make.apply();
            return $anonfun$containsSymbol$1(sym, tp, var10);
         } else {
            int var11 = using_this.scala$reflect$internal$util$ReusableInstance$$taken;
            ArrayBuffer var10001 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
            if (var10001 == null) {
               throw null;
            } else {
               if (var11 == SeqOps.size$(var10001)) {
                  ArrayBuffer var12 = using_this.scala$reflect$internal$util$ReusableInstance$$cache;
                  Object using_$plus$eq_elem = using_this.scala$reflect$internal$util$ReusableInstance$$make.apply();
                  if (var12 == null) {
                     throw null;
                  }

                  var12.addOne(using_$plus$eq_elem);
                  using_$plus$eq_elem = null;
               }

               ++using_this.scala$reflect$internal$util$ReusableInstance$$taken;

               try {
                  TypeMaps.ContainsCollector var6 = (TypeMaps.ContainsCollector)using_this.scala$reflect$internal$util$ReusableInstance$$cache.apply(using_this.scala$reflect$internal$util$ReusableInstance$$taken - 1);
                  var11 = $anonfun$containsSymbol$1(sym, tp, var6);
               } finally {
                  --using_this.scala$reflect$internal$util$ReusableInstance$$taken;
               }

               return (boolean)var11;
            }
         }
      }
   }

   // $FF: synthetic method
   static boolean solve$(final TypeConstraints $this, final List tvars, final List tparams, final Variance.Extractor getVariance, final boolean upper, final int depth) {
      return $this.solve(tvars, tparams, getVariance, upper, depth);
   }

   default boolean solve(final List tvars, final List tparams, final Variance.Extractor getVariance, final boolean upper, final int depth) {
      SymbolTable var10000 = (SymbolTable)this;
      boolean assert_assertion = tvars.corresponds(tparams, (tvar, tparam) -> BoxesRunTime.boxToBoolean($anonfun$solve$1(tvar, tparam)));
      SymbolTable assert_this = var10000;
      if (!assert_assertion) {
         throw assert_this.throwAssertionError($anonfun$solve$2(tparams, tvars));
      } else {
         BitSet var21 = scala.collection.mutable.BitSet..MODULE$;
         BitSet areContravariant = new BitSet();
         Collections var22 = (Collections)this;
         int foreachWithIndex_index = 0;

         for(List foreachWithIndex_ys = tparams; !foreachWithIndex_ys.isEmpty(); ++foreachWithIndex_index) {
            Symbols.Symbol var14 = (Symbols.Symbol)foreachWithIndex_ys.head();
            if (Variance$.MODULE$.isContravariant$extension(getVariance.apply(var14))) {
               Object $anonfun$solve$4_$plus$eq_elem = foreachWithIndex_index;
               areContravariant.addOne($anonfun$solve$4_$plus$eq_elem);
               $anonfun$solve$4_$plus$eq_elem = null;
            }

            Object var20 = null;
            foreachWithIndex_ys = (List)foreachWithIndex_ys.tail();
         }

         Object var17 = null;
         var22 = (Collections)this;
         int foreachWithIndex_index = 0;

         for(List foreachWithIndex_ys = tvars; !foreachWithIndex_ys.isEmpty(); ++foreachWithIndex_index) {
            Types.TypeVar var15 = (Types.TypeVar)foreachWithIndex_ys.head();
            $anonfun$solve$8(this, areContravariant, upper, tvars, tparams, depth, var15, foreachWithIndex_index);
            foreachWithIndex_ys = (List)foreachWithIndex_ys.tail();
         }

         Object var18 = null;

         for(List forall_these = tvars; !forall_these.isEmpty(); forall_these = (List)forall_these.tail()) {
            if (!((Types.TypeVar)forall_these.head()).instWithinBounds()) {
               return false;
            }
         }

         return true;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$containsSymbol$1(final Symbols.Symbol sym$1, final Types.Type tp$2, final TypeMaps.ContainsCollector cc) {
      cc.reset(sym$1);
      return BoxesRunTime.unboxToBoolean(cc.collect(tp$2));
   }

   // $FF: synthetic method
   static boolean $anonfun$solve$1(final Types.TypeVar tvar, final Symbols.Symbol tparam) {
      return tvar.origin().typeSymbol() == tparam;
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$solve$3(final Types.TypeVar x$7) {
      return x$7.origin().typeSymbol();
   }

   // $FF: synthetic method
   static Tuple2 $anonfun$solve$2(final List tparams$1, final List tvars$1) {
      Tuple2 var10000 = new Tuple2;
      if (tvars$1 == null) {
         throw null;
      } else {
         Object var10003;
         if (tvars$1 == scala.collection.immutable.Nil..MODULE$) {
            var10003 = scala.collection.immutable.Nil..MODULE$;
         } else {
            .colon.colon map_h = new .colon.colon($anonfun$solve$3((Types.TypeVar)tvars$1.head()), scala.collection.immutable.Nil..MODULE$);
            .colon.colon map_t = map_h;

            for(List map_rest = (List)tvars$1.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
               .colon.colon map_nx = new .colon.colon($anonfun$solve$3((Types.TypeVar)map_rest.head()), scala.collection.immutable.Nil..MODULE$);
               map_t.next_$eq(map_nx);
               map_t = map_nx;
            }

            Statics.releaseFence();
            var10003 = map_h;
         }

         Object var6 = null;
         Object var7 = null;
         Object var8 = null;
         Object var9 = null;
         var10000.<init>(tparams$1, var10003);
         return var10000;
      }
   }

   // $FF: synthetic method
   static void $anonfun$solve$4(final Variance.Extractor getVariance$1, final BitSet areContravariant$1, final Symbols.Symbol tparam, final int ix) {
      if (Variance$.MODULE$.isContravariant$extension(getVariance$1.apply(tparam))) {
         Integer $plus$eq_elem = ix;
         if (areContravariant$1 == null) {
            throw null;
         } else {
            areContravariant$1.addOne($plus$eq_elem);
         }
      }
   }

   private static Types.Type toBound$1(final boolean hi, final Symbols.Symbol tparam) {
      return hi ? tparam.info().upperBound() : tparam.info().lowerBound();
   }

   private static boolean tvarIsBoundOf$1(final Symbols.Symbol tparamOther, final boolean up$1, final Symbols.Symbol tparam$1) {
      Types.Type var3 = (!up$1 ? tparamOther.info().upperBound() : tparamOther.info().lowerBound()).dealias();
      if (var3 instanceof Types.TypeRef) {
         Types.TypeRef var4 = (Types.TypeRef)var3;
         Symbols.Symbol var5 = var4.sym();
         List var6 = var4.args();
         if (tparam$1 == null) {
            if (var5 != null) {
               return false;
            }
         } else if (!tparam$1.equals(var5)) {
            return false;
         }

         if (scala.collection.immutable.Nil..MODULE$.equals(var6)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static void $anonfun$solve$5(final TypeConstraints $this, final Symbols.Symbol tparam$1, final Types.Type bound$1, final BooleanRef otherTypeVarBeingSolved$1, final BitSet areContravariant$1, final boolean up$1, final boolean upper$1, final List tvars$1, final List tparams$1, final int depth$1, final Types.TypeVar tvarOther, final int ix) {
      Symbols.Symbol tparamOther = tvarOther.origin().typeSymbol();
      if (tparamOther == tparam$1 || !$this.containsSymbol(bound$1, tparamOther)) {
         boolean var10000;
         label43: {
            Types.Type var13 = (!up$1 ? tparamOther.info().upperBound() : tparamOther.info().lowerBound()).dealias();
            if (var13 instanceof Types.TypeRef) {
               label41: {
                  Types.TypeRef var14 = (Types.TypeRef)var13;
                  Symbols.Symbol var15 = var14.sym();
                  List var16 = var14.args();
                  if (tparam$1 == null) {
                     if (var15 != null) {
                        break label41;
                     }
                  } else if (!tparam$1.equals(var15)) {
                     break label41;
                  }

                  if (scala.collection.immutable.Nil..MODULE$.equals(var16)) {
                     var10000 = true;
                     break label43;
                  }
               }
            }

            var10000 = false;
         }

         Object var18 = null;
         Object var19 = null;
         Object var20 = null;
         Object var21 = null;
         if (!var10000) {
            return;
         }
      }

      if (tvarOther.constr().inst() == null) {
         otherTypeVarBeingSolved$1.elem = true;
      }

      Integer apply_elem = ix;
      if (areContravariant$1 == null) {
         throw null;
      } else {
         boolean var10002 = areContravariant$1.contains(apply_elem);
         Object var22 = null;
         $this.solveOne$1(tvarOther, var10002, upper$1, tvars$1, areContravariant$1, tparams$1, depth$1);
      }
   }

   // $FF: synthetic method
   static void $anonfun$solve$6(final Symbols.Symbol tparam$1, final boolean up$1, final Types.TypeVar tvar$1, final Types.TypeVar tvarOther) {
      Symbols.Symbol tparamOther = tvarOther.origin().typeSymbol();
      if (tparamOther != tparam$1) {
         boolean var10000;
         label38: {
            Types.Type var5 = (!up$1 ? tparamOther.info().upperBound() : tparamOther.info().lowerBound()).dealias();
            if (var5 instanceof Types.TypeRef) {
               label36: {
                  Types.TypeRef var6 = (Types.TypeRef)var5;
                  Symbols.Symbol var7 = var6.sym();
                  List var8 = var6.args();
                  if (tparam$1 == null) {
                     if (var7 != null) {
                        break label36;
                     }
                  } else if (!tparam$1.equals(var7)) {
                     break label36;
                  }

                  if (scala.collection.immutable.Nil..MODULE$.equals(var8)) {
                     var10000 = true;
                     break label38;
                  }
               }
            }

            var10000 = false;
         }

         Object var9 = null;
         Object var10 = null;
         Object var11 = null;
         Object var12 = null;
         if (var10000) {
            if (up$1) {
               tvar$1.addHiBound(tvarOther, tvar$1.addHiBound$default$2());
               return;
            }

            tvar$1.addLoBound(tvarOther, tvar$1.addLoBound$default$2());
            return;
         }
      }

   }

   // $FF: synthetic method
   static boolean $anonfun$solve$7(final TypeConstraints $this, final Types.Type tp) {
      return ((Types)$this).isSingleType(tp);
   }

   private void solveOne$1(final Types.TypeVar tvar, final boolean isContravariant, final boolean upper$1, final List tvars$1, final BitSet areContravariant$1, final List tparams$1, final int depth$1) {
      Types.Type var10000 = tvar.constr().inst();
      Types.NoType$ var8 = ((Types)this).NoType();
      if (var10000 == null) {
         if (var8 != null) {
            return;
         }
      } else if (!var10000.equals(var8)) {
         return;
      }

      tvar.constr().inst_$eq((Types.Type)null);
      boolean up = isContravariant ? !upper$1 : upper$1;
      Symbols.Symbol tparam = tvar.origin().typeSymbol();
      Types.Type bound = up ? tparam.info().upperBound() : tparam.info().lowerBound();
      boolean var29 = false;
      Collections var44 = (Collections)this;
      int foreachWithIndex_index = 0;

      for(List foreachWithIndex_ys = tvars$1; !foreachWithIndex_ys.isEmpty(); ++foreachWithIndex_index) {
         label188: {
            Types.TypeVar var20 = (Types.TypeVar)foreachWithIndex_ys.head();
            Symbols.Symbol $anonfun$solve$5_tparamOther = var20.origin().typeSymbol();
            if ($anonfun$solve$5_tparamOther == tparam || !this.containsSymbol(bound, $anonfun$solve$5_tparamOther)) {
               label154: {
                  Types.Type var24 = (!up ? $anonfun$solve$5_tparamOther.info().upperBound() : $anonfun$solve$5_tparamOther.info().lowerBound()).dealias();
                  if (var24 instanceof Types.TypeRef) {
                     label152: {
                        Types.TypeRef var25 = (Types.TypeRef)var24;
                        Symbols.Symbol var26 = var25.sym();
                        List var27 = var25.args();
                        if (tparam == null) {
                           if (var26 != null) {
                              break label152;
                           }
                        } else if (!tparam.equals(var26)) {
                           break label152;
                        }

                        if (scala.collection.immutable.Nil..MODULE$.equals(var27)) {
                           var45 = true;
                           break label154;
                        }
                     }
                  }

                  var45 = false;
               }

               Object var34 = null;
               Object var36 = null;
               Object var38 = null;
               Object var40 = null;
               if (!var45) {
                  break label188;
               }
            }

            if (var20.constr().inst() == null) {
               var29 = true;
            }

            Integer $anonfun$solve$5_apply_elem = foreachWithIndex_index;
            if (areContravariant$1 == null) {
               throw null;
            }

            boolean var10002 = areContravariant$1.contains($anonfun$solve$5_apply_elem);
            Object var42 = null;
            this.solveOne$1(var20, var10002, upper$1, tvars$1, areContravariant$1, tparams$1, depth$1);
         }

         Object var33 = null;
         Object var35 = null;
         Object var37 = null;
         Object var39 = null;
         Object var41 = null;
         Object var43 = null;
         foreachWithIndex_ys = (List)foreachWithIndex_ys.tail();
      }

      Object var31 = null;
      if (!var29 && !this.containsSymbol(bound, tparam)) {
         Symbols.Symbol boundSym = bound.typeSymbol();
         if (up) {
            label127: {
               Symbols.ClassSymbol var13 = ((Definitions)this).definitions().AnyClass();
               if (boundSym == null) {
                  if (var13 == null) {
                     break label127;
                  }
               } else if (boundSym.equals(var13)) {
                  break label127;
               }

               tvar.addHiBound(bound.instantiateTypeParams(tparams$1, tvars$1), tvar.addHiBound$default$2());
            }
         } else {
            label177: {
               if (boundSym == null) {
                  if (tparam == null) {
                     break label177;
                  }
               } else if (boundSym.equals(tparam)) {
                  break label177;
               }

               Definitions.DefinitionsClass.NothingClass$ var14 = ((Definitions)this).definitions().NothingClass();
               if (boundSym == null) {
                  if (var14 == null) {
                     break label177;
                  }
               } else if (boundSym.equals(var14)) {
                  break label177;
               }

               tvar.addLoBound(bound.instantiateTypeParams(tparams$1, tvars$1), tvar.addLoBound$default$2());
            }
         }

         if (tvars$1 == null) {
            throw null;
         }

         for(List foreach_these = tvars$1; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
            Types.TypeVar var21 = (Types.TypeVar)foreach_these.head();
            $anonfun$solve$6(tparam, up, tvar, var21);
         }

         Object var30 = null;
      }

      label115: {
         tvar.constr().inst_$eq(((Types)this).NoType());
         if (!up) {
            List var46 = tvar.constr().hiBounds();
            if (var46 == null) {
               throw null;
            }

            List exists_these = var46;

            while(true) {
               if (exists_these.isEmpty()) {
                  var47 = false;
                  break;
               }

               Types.Type var22 = (Types.Type)exists_these.head();
               if (((Types)this).isSingleType(var22)) {
                  var47 = true;
                  break;
               }

               exists_these = (List)exists_these.tail();
            }

            Object var32 = null;
            if (!var47) {
               var48 = Depth$.MODULE$.isAnyDepth$extension(depth$1) ? ((GlbLubs)this).lub(tvar.constr().loBounds()) : ((GlbLubs)this).lub(tvar.constr().loBounds(), depth$1);
               break label115;
            }
         }

         var48 = Depth$.MODULE$.isAnyDepth$extension(depth$1) ? ((GlbLubs)this).glb(tvar.constr().hiBounds()) : ((GlbLubs)this).glb(tvar.constr().hiBounds(), depth$1);
      }

      Types.Type newInst = var48;
      tvar.setInst(newInst);
   }

   // $FF: synthetic method
   static void $anonfun$solve$8(final TypeConstraints $this, final BitSet areContravariant$1, final boolean upper$1, final List tvars$1, final List tparams$1, final int depth$1, final Types.TypeVar tvar, final int i) {
      Integer apply_elem = i;
      if (areContravariant$1 == null) {
         throw null;
      } else {
         boolean var10002 = areContravariant$1.contains(apply_elem);
         Object var9 = null;
         $this.solveOne$1(tvar, var10002, upper$1, tvars$1, areContravariant$1, tparams$1, depth$1);
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$solve$9(final Types.TypeVar x$8) {
      return x$8.instWithinBounds();
   }

   static void $init$(final TypeConstraints $this) {
      ReusableInstance$ var10001 = ReusableInstance$.MODULE$;
      Function0 var6 = () -> (SymbolTable)$this.new ContainsCollector((Symbols.Symbol)null);
      boolean apply_enabled = ((SymbolTable)$this).isCompilerUniverse();
      Function0 apply_make = var6;
      ReusableInstance var7;
      if (apply_enabled) {
         int apply_apply_apply_initialSize = 4;
         var7 = new ReusableInstance(apply_make, apply_apply_apply_initialSize);
      } else {
         int apply_apply_initialSize = -1;
         var7 = new ReusableInstance(apply_make, apply_apply_initialSize);
      }

      Object var5 = null;
      $this.scala$reflect$internal$tpe$TypeConstraints$_setter_$scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances_$eq(var7);
   }

   // $FF: synthetic method
   static Object $anonfun$containsSymbol$1$adapted(final Symbols.Symbol sym$1, final Types.Type tp$2, final TypeMaps.ContainsCollector cc) {
      return BoxesRunTime.boxToBoolean($anonfun$containsSymbol$1(sym$1, tp$2, cc));
   }

   // $FF: synthetic method
   static Object $anonfun$solve$4$adapted(final Variance.Extractor getVariance$1, final BitSet areContravariant$1, final Symbols.Symbol tparam, final Object ix) {
      $anonfun$solve$4(getVariance$1, areContravariant$1, tparam, BoxesRunTime.unboxToInt(ix));
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$solve$8$adapted(final TypeConstraints $this, final BitSet areContravariant$1, final boolean upper$1, final List tvars$1, final List tparams$1, final int depth$1, final Types.TypeVar tvar, final Object i) {
      $anonfun$solve$8($this, areContravariant$1, upper$1, tvars$1, tparams$1, depth$1, tvar, BoxesRunTime.unboxToInt(i));
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$solve$9$adapted(final Types.TypeVar x$8) {
      return BoxesRunTime.boxToBoolean($anonfun$solve$9(x$8));
   }

   // $FF: synthetic method
   static Object $anonfun$solve$5$adapted(final TypeConstraints $this, final Symbols.Symbol tparam$1, final Types.Type bound$1, final BooleanRef otherTypeVarBeingSolved$1, final BitSet areContravariant$1, final boolean up$1, final boolean upper$1, final List tvars$1, final List tparams$1, final int depth$1, final Types.TypeVar tvarOther, final Object ix) {
      $anonfun$solve$5($this, tparam$1, bound$1, otherTypeVarBeingSolved$1, areContravariant$1, up$1, upper$1, tvars$1, tparams$1, depth$1, tvarOther, BoxesRunTime.unboxToInt(ix));
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$solve$6$adapted(final Symbols.Symbol tparam$1, final boolean up$1, final Types.TypeVar tvar$1, final Types.TypeVar tvarOther) {
      $anonfun$solve$6(tparam$1, up$1, tvar$1, tvarOther);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static Object $anonfun$solve$7$adapted(final TypeConstraints $this, final Types.Type tp) {
      return BoxesRunTime.boxToBoolean($anonfun$solve$7($this, tp));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class UndoLog implements Clearable {
      private List log;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public List log() {
         return this.log;
      }

      public void log_$eq(final List x$1) {
         this.log = x$1;
      }

      public void undoTo(final List limit) {
         this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().assertCorrectThread();

         while(this.log() != limit && this.log().nonEmpty()) {
            UndoPair var2 = (UndoPair)this.log().head();
            if (var2 == null) {
               throw new MatchError((Object)null);
            }

            Types.TypeVar tv = var2.tv();
            TypeConstraint constr = var2.tConstraint();
            tv.constr_$eq(constr);
            this.log_$eq((List)this.log().tail());
         }

      }

      public void record(final Types.TypeVar tv) {
         List var10001 = this.log();
         UndoPair $colon$colon_elem = new UndoPair(tv, tv.constr().cloneInternal());
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            .colon.colon var6 = new .colon.colon($colon$colon_elem, $colon$colon_this);
            Object var4 = null;
            $colon$colon_elem = null;
            this.log_$eq(var6);
         }
      }

      public void clear() {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var5 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().settings();
         MutableSettings var6 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var6;
         boolean var7 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var7) {
            this.scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer().log(() -> {
               StringBuilder var10000 = (new StringBuilder(35)).append("Clearing ");
               List var10001 = this.log();
               if (var10001 == null) {
                  throw null;
               } else {
                  return var10000.append(SeqOps.size$(var10001)).append(" entries from the undoLog.").toString();
               }
            });
         }

         this.log_$eq(scala.collection.immutable.Nil..MODULE$);
      }

      public Object undo(final Function0 block) {
         List before = this.log();

         Object var10000;
         try {
            var10000 = block.apply();
         } finally {
            this.undoTo(before);
         }

         return var10000;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeConstraints$UndoLog$$$outer() {
         return this.$outer;
      }

      public UndoLog() {
         if (TypeConstraints.this == null) {
            throw null;
         } else {
            this.$outer = TypeConstraints.this;
            super();
            this.log = scala.collection.immutable.Nil..MODULE$;
            if (TypeConstraints.this.isCompilerUniverse()) {
               TypeConstraints.this.perRunCaches().recordCache(this);
            }

         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class TypeConstraint$ {
      public boolean $lessinit$greater$default$5() {
         return false;
      }
   }

   public class TypeConstraint {
      private List lobounds;
      private List hibounds;
      private Types.Type numlo;
      private Types.Type numhi;
      private boolean avoidWidening;
      private Types.Type inst;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public List loBounds() {
         Types.Type var10000 = this.numlo;
         Types.NoType$ var1 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
         if (var10000 == null) {
            if (var1 == null) {
               return this.lobounds;
            }
         } else if (var10000.equals(var1)) {
            return this.lobounds;
         }

         Types.Type var2 = this.numlo;
         List var4 = this.lobounds;
         if (var4 == null) {
            throw null;
         } else {
            List $colon$colon_this = var4;
            return new .colon.colon(var2, $colon$colon_this);
         }
      }

      public List hiBounds() {
         Types.Type var10000 = this.numhi;
         Types.NoType$ var1 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
         if (var10000 == null) {
            if (var1 == null) {
               return this.hibounds;
            }
         } else if (var10000.equals(var1)) {
            return this.hibounds;
         }

         Types.Type var2 = this.numhi;
         List var4 = this.hibounds;
         if (var4 == null) {
            throw null;
         } else {
            List $colon$colon_this = var4;
            return new .colon.colon(var2, $colon$colon_this);
         }
      }

      public boolean avoidWiden() {
         return this.avoidWidening;
      }

      public void stopWidening() {
         this.avoidWidening = true;
      }

      public void stopWideningIfPrecluded() {
         if (this.instValid() && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().TypeVar().precludesWidening(this.inst())) {
            this.stopWidening();
         }
      }

      public void addLoBound(final Types.Type tp, final boolean isNumericBound) {
         Symbols.Symbol var3 = tp.typeSymbol();
         if (this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().NothingClass().equals(var3) ? true : !this.lobounds.contains(tp)) {
            if (isNumericBound && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().isNumericValueType(tp)) {
               label38: {
                  Types.Type var10000 = this.numlo;
                  Types.NoType$ var4 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                  if (var10000 == null) {
                     if (var4 == null) {
                        break label38;
                     }
                  } else if (var10000.equals(var4)) {
                     break label38;
                  }

                  if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(this.numlo, tp)) {
                     if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(tp, this.numlo)) {
                        this.numlo = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().scala$reflect$internal$tpe$TypeConstraints$$numericLoBound();
                        return;
                     }

                     return;
                  }
               }

               this.numlo = tp;
            } else {
               List var10001 = this.lobounds;
               if (var10001 == null) {
                  throw null;
               } else {
                  List $colon$colon_this = var10001;
                  this.lobounds = new .colon.colon(tp, $colon$colon_this);
               }
            }
         }
      }

      public boolean addLoBound$default$2() {
         return false;
      }

      public void checkWidening(final Types.Type tp) {
         if (this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().TypeVar().precludesWidening(tp)) {
            this.stopWidening();
         } else if (tp != null && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().HasTypeMember().unapply(tp)) {
            this.stopWidening();
         }
      }

      public void addHiBound(final Types.Type tp, final boolean isNumericBound) {
         SymbolTable var10000 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer();
         if (var10000 == null) {
            throw null;
         } else if (Types.typeIsAnyOrJavaObject$(var10000, tp) || !this.hibounds.contains(tp)) {
            this.checkWidening(tp);
            if (isNumericBound && this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().definitions().isNumericValueType(tp)) {
               label37: {
                  Types.Type var5 = this.numhi;
                  Types.NoType$ var3 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                  if (var5 == null) {
                     if (var3 == null) {
                        break label37;
                     }
                  } else if (var5.equals(var3)) {
                     break label37;
                  }

                  if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(tp, this.numhi)) {
                     if (!this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().isNumericSubType(this.numhi, tp)) {
                        this.numhi = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().scala$reflect$internal$tpe$TypeConstraints$$numericHiBound();
                        return;
                     }

                     return;
                  }
               }

               this.numhi = tp;
            } else {
               List var10001 = this.hibounds;
               if (var10001 == null) {
                  throw null;
               } else {
                  List $colon$colon_this = var10001;
                  this.hibounds = new .colon.colon(tp, $colon$colon_this);
               }
            }
         }
      }

      public boolean addHiBound$default$2() {
         return false;
      }

      public boolean instWithinBounds() {
         return this.instValid() && this.isWithinBounds(this.inst());
      }

      public boolean isWithinBounds(final Types.Type tp) {
         List var10000 = this.lobounds;
         if (var10000 == null) {
            throw null;
         } else {
            List forall_these = var10000;

            while(true) {
               if (forall_these.isEmpty()) {
                  var9 = true;
                  break;
               }

               if (!((Types.Type)forall_these.head()).$less$colon$less(tp)) {
                  var9 = false;
                  break;
               }

               forall_these = (List)forall_these.tail();
            }

            Object var7 = null;
            if (var9) {
               var10000 = this.hibounds;
               if (var10000 == null) {
                  throw null;
               }

               List forall_these = var10000;

               while(true) {
                  if (forall_these.isEmpty()) {
                     var11 = true;
                     break;
                  }

                  Types.Type var6 = (Types.Type)forall_these.head();
                  if (!tp.$less$colon$less(var6)) {
                     var11 = false;
                     break;
                  }

                  forall_these = (List)forall_these.tail();
               }

               Object var8 = null;
               if (var11) {
                  label55: {
                     Types.Type var12 = this.numlo;
                     Types.NoType$ var2 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                     if (var12 == null) {
                        if (var2 == null) {
                           break label55;
                        }
                     } else if (var12.equals(var2)) {
                        break label55;
                     }

                     if (!this.numlo.weak_$less$colon$less(tp)) {
                        return false;
                     }
                  }

                  Types.Type var13 = this.numhi;
                  Types.NoType$ var3 = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
                  if (var13 == null) {
                     if (var3 == null) {
                        return true;
                     }
                  } else if (var13.equals(var3)) {
                     return true;
                  }

                  if (tp.weak_$less$colon$less(this.numhi)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }

      public Types.Type inst() {
         return this.inst;
      }

      public void inst_$eq(final Types.Type x$1) {
         this.inst = x$1;
      }

      public boolean instValid() {
         return this.inst() != null && this.inst() != this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType();
      }

      public TypeConstraint cloneInternal() {
         TypeConstraint tc = this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().new TypeConstraint(this.lobounds, this.hibounds, this.numlo, this.numhi, this.avoidWidening);
         tc.inst_$eq(this.inst());
         return tc;
      }

      public String toString() {
         List var10000 = this.loBounds();
         if (var10000 == null) {
            throw null;
         } else {
            List filterNot_this = var10000;
            boolean filterNot_filterCommon_isFlipped = true;
            List filterNot_filterCommon_noneIn$1_l = filterNot_this;

            while(true) {
               if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                  var125 = scala.collection.immutable.Nil..MODULE$;
                  break;
               }

               Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
               List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
               if (((Types.Type)filterNot_filterCommon_noneIn$1_h).isNothing() != filterNot_filterCommon_isFlipped) {
                  List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                  while(true) {
                     if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                        var125 = filterNot_filterCommon_noneIn$1_l;
                        break;
                     }

                     Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                     if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x).isNothing() == filterNot_filterCommon_isFlipped) {
                        .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new .colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                        .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                        for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                           filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                        }

                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                        while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                           Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                           if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isNothing() != filterNot_filterCommon_isFlipped) {
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                           } else {
                              while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                 .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                        var125 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                        Object var66 = null;
                        Object var69 = null;
                        Object var72 = null;
                        Object var75 = null;
                        Object var78 = null;
                        Object var81 = null;
                        Object var84 = null;
                        Object var87 = null;
                        break;
                     }

                     filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                  }

                  Object var62 = null;
                  Object var64 = null;
                  Object var67 = null;
                  Object var70 = null;
                  Object var73 = null;
                  Object var76 = null;
                  Object var79 = null;
                  Object var82 = null;
                  Object var85 = null;
                  Object var88 = null;
                  break;
               }

               filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
            }

            Object var59 = null;
            Object var60 = null;
            Object var61 = null;
            Object var63 = null;
            Object var65 = null;
            Object var68 = null;
            Object var71 = null;
            Object var74 = null;
            Object var77 = null;
            Object var80 = null;
            Object var83 = null;
            Object var86 = null;
            Object var89 = null;
            List filterNot_filterCommon_result = (List)var125;
            Statics.releaseFence();
            Object var126 = filterNot_filterCommon_result;
            Object var57 = null;
            filterNot_filterCommon_result = null;
            Object var3 = var126;
            String var127;
            if (scala.collection.immutable.Nil..MODULE$.equals(var3)) {
               var127 = "";
            } else {
               label126: {
                  if (var3 instanceof .colon.colon) {
                     .colon.colon var4 = (.colon.colon)var3;
                     Types.Type tp = (Types.Type)var4.head();
                     List var6 = var4.next$access$1();
                     if (scala.collection.immutable.Nil..MODULE$.equals(var6)) {
                        var127 = (new StringBuilder(4)).append(" >: ").append(tp).toString();
                        break label126;
                     }
                  }

                  String mkString_end = ")";
                  String mkString_sep = ", ";
                  String mkString_start = " >: (";
                  var127 = IterableOnceOps.mkString$((IterableOnceOps)var3, mkString_start, mkString_sep, mkString_end);
                  Object var51 = null;
                  Object var52 = null;
                  Object var53 = null;
               }
            }

            String lo = var127;
            List var128 = this.hiBounds();
            if (var128 == null) {
               throw null;
            } else {
               List filterNot_this = var128;
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = filterNot_this;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var129 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  Types.Type var50 = (Types.Type)filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$toString$2(this, var50) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var129 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var50 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$toString$2(this, var50) == filterNot_filterCommon_isFlipped) {
                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new .colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var50 = (Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$toString$2(this, var50) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                           var129 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var99 = null;
                           Object var102 = null;
                           Object var105 = null;
                           Object var108 = null;
                           Object var111 = null;
                           Object var114 = null;
                           Object var117 = null;
                           Object var120 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var95 = null;
                     Object var97 = null;
                     Object var100 = null;
                     Object var103 = null;
                     Object var106 = null;
                     Object var109 = null;
                     Object var112 = null;
                     Object var115 = null;
                     Object var118 = null;
                     Object var121 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var92 = null;
               Object var93 = null;
               Object var94 = null;
               Object var96 = null;
               Object var98 = null;
               Object var101 = null;
               Object var104 = null;
               Object var107 = null;
               Object var110 = null;
               Object var113 = null;
               Object var116 = null;
               Object var119 = null;
               Object var122 = null;
               List filterNot_filterCommon_result = (List)var129;
               Statics.releaseFence();
               Object var130 = filterNot_filterCommon_result;
               Object var90 = null;
               filterNot_filterCommon_result = null;
               Object var8 = var130;
               String var131;
               if (scala.collection.immutable.Nil..MODULE$.equals(var8)) {
                  var131 = "";
               } else {
                  label84: {
                     if (var8 instanceof .colon.colon) {
                        .colon.colon var9 = (.colon.colon)var8;
                        Types.Type tp = (Types.Type)var9.head();
                        List var11 = var9.next$access$1();
                        if (scala.collection.immutable.Nil..MODULE$.equals(var11)) {
                           var131 = (new StringBuilder(4)).append(" <: ").append(tp).toString();
                           break label84;
                        }
                     }

                     String mkString_endx = ")";
                     String mkString_sepx = ", ";
                     String mkString_start = " <: (";
                     var131 = IterableOnceOps.mkString$((IterableOnceOps)var8, mkString_start, mkString_sepx, mkString_endx);
                     Object var54 = null;
                     Object var55 = null;
                     Object var56 = null;
                  }
               }

               String hi = var131;
               String boundsStr = (new StringBuilder(0)).append(lo).append(hi).toString();
               return this.inst() == this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer().NoType() ? boundsStr : (new StringBuilder(4)).append(boundsStr).append(" _= ").append(this.inst().safeToString()).toString();
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$lobounds$1(final Types.Type x$2) {
         return x$2.isNothing();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hibounds$1(final Types.Type x$3) {
         return x$3.isAny();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isWithinBounds$1(final Types.Type tp$1, final Types.Type x$4) {
         return x$4.$less$colon$less(tp$1);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$isWithinBounds$2(final Types.Type tp$1, final Types.Type x$5) {
         return tp$1.$less$colon$less(x$5);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$toString$1(final Types.Type x$6) {
         return x$6.isNothing();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$toString$2(final TypeConstraint $this, final Types.Type tp) {
         SymbolTable var10000 = $this.scala$reflect$internal$tpe$TypeConstraints$TypeConstraint$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            return Types.typeIsAnyOrJavaObject$(var10000, tp);
         }
      }

      public TypeConstraint(final List lo0, final List hi0, final Types.Type numlo0, final Types.Type numhi0, final boolean avoidWidening0) {
         if (TypeConstraints.this == null) {
            throw null;
         } else {
            this.$outer = TypeConstraints.this;
            super();
            if (lo0 == null) {
               throw null;
            } else {
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = lo0;

               Object var10001;
               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var10001 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  if (((Types.Type)filterNot_filterCommon_noneIn$1_h).isNothing() != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var10001 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x).isNothing() == filterNot_filterCommon_isFlipped) {
                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new .colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isNothing() != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                           var10001 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var45 = null;
                           Object var48 = null;
                           Object var51 = null;
                           Object var54 = null;
                           Object var57 = null;
                           Object var60 = null;
                           Object var63 = null;
                           Object var66 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var41 = null;
                     Object var43 = null;
                     Object var46 = null;
                     Object var49 = null;
                     Object var52 = null;
                     Object var55 = null;
                     Object var58 = null;
                     Object var61 = null;
                     Object var64 = null;
                     Object var67 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var38 = null;
               Object var39 = null;
               Object var40 = null;
               Object var42 = null;
               Object var44 = null;
               Object var47 = null;
               Object var50 = null;
               Object var53 = null;
               Object var56 = null;
               Object var59 = null;
               Object var62 = null;
               Object var65 = null;
               Object var68 = null;
               List filterNot_filterCommon_result = (List)var10001;
               Statics.releaseFence();
               var10001 = filterNot_filterCommon_result;
               filterNot_filterCommon_result = null;
               this.lobounds = (List)var10001;
               if (hi0 == null) {
                  throw null;
               } else {
                  boolean filterNot_filterCommon_isFlipped = true;
                  List filterNot_filterCommon_noneIn$1_l = hi0;

                  while(true) {
                     if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                        var10001 = scala.collection.immutable.Nil..MODULE$;
                        break;
                     }

                     Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                     List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                     if (((Types.Type)filterNot_filterCommon_noneIn$1_h).isAny() != filterNot_filterCommon_isFlipped) {
                        List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                        while(true) {
                           if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                              var10001 = filterNot_filterCommon_noneIn$1_l;
                              break;
                           }

                           Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                           if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_x).isAny() == filterNot_filterCommon_isFlipped) {
                              .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new .colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                              List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                              .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                              for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                 .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                              }

                              List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                              List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                              while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                 Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                 if (((Types.Type)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isAny() != filterNot_filterCommon_isFlipped) {
                                    filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                 } else {
                                    while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                       .colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new .colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                              var10001 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                              Object var77 = null;
                              Object var80 = null;
                              Object var83 = null;
                              Object var86 = null;
                              Object var89 = null;
                              Object var92 = null;
                              Object var95 = null;
                              Object var98 = null;
                              break;
                           }

                           filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                        }

                        Object var73 = null;
                        Object var75 = null;
                        Object var78 = null;
                        Object var81 = null;
                        Object var84 = null;
                        Object var87 = null;
                        Object var90 = null;
                        Object var93 = null;
                        Object var96 = null;
                        Object var99 = null;
                        break;
                     }

                     filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
                  }

                  Object var70 = null;
                  Object var71 = null;
                  Object var72 = null;
                  Object var74 = null;
                  Object var76 = null;
                  Object var79 = null;
                  Object var82 = null;
                  Object var85 = null;
                  Object var88 = null;
                  Object var91 = null;
                  Object var94 = null;
                  Object var97 = null;
                  Object var100 = null;
                  List filterNot_filterCommon_result = (List)var10001;
                  Statics.releaseFence();
                  var10001 = filterNot_filterCommon_result;
                  filterNot_filterCommon_result = null;
                  this.hibounds = (List)var10001;
                  this.numlo = numlo0;
                  this.numhi = numhi0;
                  this.avoidWidening = avoidWidening0;
                  this.inst = TypeConstraints.this.NoType();
               }
            }
         }
      }

      public TypeConstraint(final List lo0, final List hi0) {
         Types.NoType$ var10004 = TypeConstraints.this.NoType();
         Types.NoType$ var10005 = TypeConstraints.this.NoType();
         if (TypeConstraints.this.TypeConstraint() == null) {
            throw null;
         } else {
            this(lo0, hi0, var10004, var10005, false);
         }
      }

      public TypeConstraint(final Types.TypeBounds bounds) {
         this(new .colon.colon(bounds.lo(), scala.collection.immutable.Nil..MODULE$), new .colon.colon(bounds.hi(), scala.collection.immutable.Nil..MODULE$));
      }

      public TypeConstraint() {
         this(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$);
      }

      // $FF: synthetic method
      public static final Object $anonfun$isWithinBounds$1$adapted(final Types.Type tp$1, final Types.Type x$4) {
         return BoxesRunTime.boxToBoolean($anonfun$isWithinBounds$1(tp$1, x$4));
      }

      // $FF: synthetic method
      public static final Object $anonfun$isWithinBounds$2$adapted(final Types.Type tp$1, final Types.Type x$5) {
         return BoxesRunTime.boxToBoolean($anonfun$isWithinBounds$2(tp$1, x$5));
      }

      // $FF: synthetic method
      public static final Object $anonfun$toString$1$adapted(final Types.Type x$6) {
         return BoxesRunTime.boxToBoolean($anonfun$toString$1(x$6));
      }

      // $FF: synthetic method
      public static final Object $anonfun$toString$2$adapted(final TypeConstraint $this, final Types.Type tp) {
         return BoxesRunTime.boxToBoolean($anonfun$toString$2($this, tp));
      }

      // $FF: synthetic method
      public static final Object $anonfun$lobounds$1$adapted(final Types.Type x$2) {
         return BoxesRunTime.boxToBoolean($anonfun$lobounds$1(x$2));
      }

      // $FF: synthetic method
      public static final Object $anonfun$hibounds$1$adapted(final Types.Type x$3) {
         return BoxesRunTime.boxToBoolean($anonfun$hibounds$1(x$3));
      }
   }

   public static final class UndoPair implements Product, Serializable {
      private final Types.TypeVar tv;
      private final TypeConstraint tConstraint;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Types.TypeVar tv() {
         return this.tv;
      }

      public TypeConstraint tConstraint() {
         return this.tConstraint;
      }

      public UndoPair copy(final Types.TypeVar tv, final TypeConstraint tConstraint) {
         return new UndoPair(tv, tConstraint);
      }

      public Types.TypeVar copy$default$1() {
         return this.tv();
      }

      public TypeConstraint copy$default$2() {
         return this.tConstraint();
      }

      public String productPrefix() {
         return "UndoPair";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.tv();
            case 1:
               return this.tConstraint();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof UndoPair;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "tv";
            case 1:
               return "tConstraint";
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
            if (x$1 instanceof UndoPair) {
               UndoPair var2 = (UndoPair)x$1;
               Types.TypeVar var10000 = this.tv();
               Types.TypeVar var3 = var2.tv();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               TypeConstraint var5 = this.tConstraint();
               TypeConstraint var4 = var2.tConstraint();
               if (var5 == null) {
                  if (var4 == null) {
                     return true;
                  }
               } else if (var5.equals(var4)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public UndoPair(final Types.TypeVar tv, final TypeConstraint tConstraint) {
         this.tv = tv;
         this.tConstraint = tConstraint;
      }
   }

   public static class UndoPair$ implements Serializable {
      public static final UndoPair$ MODULE$ = new UndoPair$();

      public final String toString() {
         return "UndoPair";
      }

      public UndoPair apply(final Types.TypeVar tv, final TypeConstraint tConstraint) {
         return new UndoPair(tv, tConstraint);
      }

      public Option unapply(final UndoPair x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.tv(), x$0.tConstraint())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(UndoPair$.class);
      }
   }
}
