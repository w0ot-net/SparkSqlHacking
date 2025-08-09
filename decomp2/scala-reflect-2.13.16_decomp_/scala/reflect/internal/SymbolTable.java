package scala.reflect.internal;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.Member;
import java.net.URLClassLoader;
import java.util.concurrent.TimeUnit;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.Console.;
import scala.collection.AbstractIterable;
import scala.collection.ArrayOps;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.mutable.AnyRefMap;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArraySeq;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.Clearable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Stack;
import scala.collection.mutable.WeakHashMap;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.pickling.Translations;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.GlbLubs;
import scala.reflect.internal.tpe.TypeComparers;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.tpe.TypeMaps;
import scala.reflect.internal.tpe.TypeToStrings;
import scala.reflect.internal.transform.Erasure;
import scala.reflect.internal.transform.PostErasure;
import scala.reflect.internal.transform.Transforms;
import scala.reflect.internal.transform.UnCurry;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.JavaClearable;
import scala.reflect.internal.util.NoPosition$;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.ReusableInstance;
import scala.reflect.internal.util.SourceFile;
import scala.reflect.internal.util.Statistics;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.TraceSymbolActivity;
import scala.reflect.internal.util.WeakHashSet;
import scala.reflect.internal.util.WeakHashSet$;
import scala.reflect.io.AbstractFile;
import scala.reflect.macros.Universe;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u001def\u0001CA\u0001\u0003\u0007\t\t!!\u0005\t\u000f\u0005M\b\u0001\"\u0001\u0002v\"I\u0011\u0011 \u0001C\u0002\u0013\u0005\u00111 \u0005\t\u0005\u0007\u0001\u0001\u0015!\u0003\u0002~\u001aY!1\u0003\u0001\u0011\u0002G\u0005!Q\u0003B\"\u0011%\u0011y\u0005\u0001b\u0001\u000e\u0003\u0011\t\u0006C\u0004\u0003X\u00011\tA!\u0017\t\u000f\t-\u0004\u0001\"\u0005\u0003n!9!q\u0014\u0001\u0005\u0002\t\u0005\u0006b\u0002BS\u0001\u0011\u0005!q\u0015\u0005\b\u0005[\u0003AQ\u0001BX\u0011\u001d\u0011i\u000e\u0001C\u0001\u0005?DqAa:\u0001\t\u0003\u0011y\u000eC\u0004\u0003j\u0002!)Aa8\t\u000f\t5\bA\"\u0001\u0003p\"9!q\u001f\u0001\u0007\u0002\t=\bb\u0002B}\u0001\u0019\u0005!1 \u0005\b\u0007\u000f\u0001A\u0011\u0001Bp\u0011\u001d\u0019I\u0001\u0001C\u0001\u0007\u0017Aqaa\u0004\u0001\t\u000b\u0019\t\u0002C\u0004\u0004 \u0001!\ta!\t\t\u000f\r\u0015\u0002\u0001\"\u0001\u0004(!91Q\u0005\u0001\u0005\u0002\rm\u0002bBB%\u0001\u0011\u001511\n\u0005\b\u0007'\u0002A\u0011AB+\u0011%\u0019I\u0006\u0001C\u0001\u0003\u0017\u0019Y\u0006C\u0005\u0004l\u0001!\t!a\u0003\u0004n!I11\u0010\u0001\u0005\u0006\u0005-1Q\u0010\u0005\n\u0007\u001b\u0003AQAA\u0006\u0007\u001fC\u0011ba(\u0001\t\u000b\tYa!)\t\u0013\rE\u0006\u0001\"\u0002\u0002\f\rM\u0006\"CBf\u0001\u0011\u0015\u00111BBg\u0011\u001d\u0019\t\u000f\u0001C\u0003\u0007GDqa!9\u0001\t\u000b\u0019\t\u0010C\u0004\u0005\b\u0001!)\u0001\"\u0003\t\u000f\u0011\u001d\u0001\u0001\"\u0002\u0005\u0014!IA\u0011\u0004\u0001\u0005\u0002\u0005\rA1\u0004\u0005\n\t?\u0001A\u0011AA\u0002\tCAq\u0001\"\n\u0001\t\u000b!9\u0003C\u0004\u0005D\u0001!\u0019\u0001\"\u0012\b\u000f\u0011m\u0003\u0001#\u0003\u0005^\u00199Aq\f\u0001\t\n\u0011\u0005\u0004bBAzS\u0011\u0005A1\u000e\u0005\b\t[JC\u0011\u0001C8\u0011%!I\b\u0001b\u0001\n\u000b\u0011y\u000e\u0003\u0005\u0005|\u0001\u0001\u000bQ\u0002Bq\u000f\u001d!i\b\u0001E\u0001\t\u007f2q\u0001\"!\u0001\u0011\u0003!\u0019\tC\u0005\u0003\u000e=\u0012\r\u0011\"\u0001\u0003\u0010!YA1R\u0018\u0005\u0002\u0003\u0005\u000b\u0011\u0002B\t\u0011\u001d\t\u0019p\fC\u0001\t\u001bC\u0011\u0002b$\u0001\u0005\u00045\t\u0001\"%\t\u000f\u0011}\u0005\u0001\"\u0001\u0005\"\"9AQ\u0017\u0001\u0005\u0002\u0011]\u0006b\u0002Cb\u0001\u0019\u0005AQY\u0003\u0007\t#\u0004\u0001aa\u0011\t\u0013\u0011M\u0007A1A\u0005\u0006\u0011U\u0007\u0002\u0003Cn\u0001\u0001\u0006i\u0001b6\u0006\r\u0011u\u0007\u0001AB\"\u0011%!y\u000e\u0001b\u0001\n\u000b!)\u000e\u0003\u0005\u0005b\u0002\u0001\u000bQ\u0002Cl\u0011!!\u0019\u000f\u0001Q\u0001\n\u0011\u0015\b\u0002\u0003C{\u0001\u0001\u0006KA!=\t\u0011\u0011]\b\u0001)Q\u0005\u0007\u0007Bq\u0001\"?\u0001\t\u000b!Y\u0010C\u0004\u0006\u0004\u0001!)Aa<\t\u000f\u0015\u0015\u0001\u0001\"\u0001\u0006\b!9Q\u0011\u0002\u0001\u0005\u0006\u0015-\u0001bBC\b\u0001\u0011\u0015Q\u0011\u0003\u0005\b\u000b+\u0001AQAC\f\u0011%)Y\u0002\u0001a\u0001\n\u0003\u0011y\u000eC\u0005\u0006\u001e\u0001\u0001\r\u0011\"\u0001\u0006 !AQQ\u0005\u0001!B\u0013\u0011\t\u000fC\u0004\u0006(\u00011\t!\"\u000b\t\u000f\u00155\u0002\u0001\"\u0002\u00060!9Qq\u0007\u0001\u0005\u0006\u0015e\u0002bBC\u001f\u0001\u0011\u0015Qq\b\u0005\b\u000b\u0003\u0002AQAC\"\u0011\u001d)\u0019\u0004\u0001C\u0003\u000b\u000fBq!\"\u0015\u0001\t\u000b)\u0019\u0006C\u0004\u0006X\u0001!)!\"\u0017\t\u000f\u00155\u0004\u0001\"\u0002\u0006p!9QQ\u000f\u0001\u0005\u0006\u0015]\u0004bBCE\u0001\u0011\u0005Q1\u0012\u0005\b\u000b7\u0003AQACO\u0011\u001d)y\u000b\u0001C\u0003\u000bcCq!b0\u0001\t\u000b)\t\rC\u0004\u0006V\u0002!\t!b6\t\u000f\u0015\u001d\b\u0001\"\u0002\u0006j\"9QQ\u001e\u0001\u0005\u0006\u0015=\bbBCz\u0001\u0011\u0005QQ\u001f\u0005\b\u000b\u007f\u0004A\u0011\u0001D\u0001\r\u001d1i\u0001AA\u0001\r\u001fAq!a=]\t\u000319\u0002C\u0004\u0007\u001cq#\tAa8\t\u000f\u0015M\b\u0001\"\u0001\u0007\u001e!Iaq\u0005\u0001\u0012\u0002\u0013\u0005a\u0011F\u0004\b\rw\u0001\u0001\u0012\u0001D\u001f\r\u001d1y\u0004\u0001E\u0001\r\u0003Bq!a=c\t\u00031\u0019\u0005\u0003\u0005\u0007F\t\u0004\u000b\u0015\u0002D$\u0011!1\u0019G\u0019Q!\n\u0019\u0015\u0004b\u0002D;E\u0012\u0005aq\u000f\u0005\b\r\u000f\u0013GQ\u0001DE\u0011\u001d19J\u0019C\u0001\r3CqAb)c\t\u0003!\t\u000bC\u0004\u0007&\n$\tAb*\t\u000f\u0019u&\r\"\u0001\u0007@\"9a\u0011\u001b2\u0005\u0002\u0019M\u0007b\u0002DqE\u0012\u0005a1\u001d\u0005\b\rg\u0014G\u0011\u0001D{\u0011\u001d1\u0019P\u0019C\u0001\u000f\u000fAqab\u0007c\t\u00039i\u0002C\u0005\b8\t\f\n\u0011\"\u0001\b:!Iq1\t\u0001A\u0002\u0013\u0005qQ\t\u0005\n\u000f\u001b\u0002\u0001\u0019!C\u0001\u000f\u001fB\u0001bb\u0015\u0001A\u0003&qq\t\u0005\n\u000f+\u0002\u0001\u0019!C\u0001\u000f/B\u0011bb\u0018\u0001\u0001\u0004%\ta\"\u0019\t\u0011\u001d\u0015\u0004\u0001)Q\u0005\u000f3B\u0011bb\u001a\u0001\u0005\u0004%ia\"\u001b\t\u0011\u001d=\u0004\u0001)A\u0007\u000fWB\u0011b\"\u001d\u0001\u0005\u0004%)ab\u001d\t\u0011\u001d]\u0004\u0001)A\u0007\u000fkBqa\"\u001f\u0001\t\u0003\u0011y\u000eC\u0004\b|\u0001!)a\" \t\u0013\u001de\u0005\u0001\"\u0005\u0002\f\u001dm\u0005\"CDY\u0001\u0011E\u00111BDZ\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3\u000b\t\u0005\u0015\u0011qA\u0001\tS:$XM\u001d8bY*!\u0011\u0011BA\u0006\u0003\u001d\u0011XM\u001a7fGRT!!!\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0005!a\u0005\u0002 \u0005-\u00121GA\u001d\u0003\u007f\t)%a\u0013\u0002R\u0005]\u0013QLA2\u0003S\ny'!\u001e\u0002|\u0005\u001d\u0015QRAJ\u00033\u000by*!*\u0002,\u0006E\u0016qWA_\u0003\u0007\fI-a4\u0002V\u0006\u0005\u0018q]Aw!\u0011\t)\"a\u0007\u000e\u0005\u0005]!\u0002BA\r\u0003\u000f\ta!\\1de>\u001c\u0018\u0002BA\u000f\u0003/\u0011\u0001\"\u00168jm\u0016\u00148/\u001a\t\u0005\u0003C\t9#\u0004\u0002\u0002$)!\u0011QEA\u0002\u0003\u0011)H/\u001b7\n\t\u0005%\u00121\u0005\u0002\f\u0007>dG.Z2uS>t7\u000f\u0005\u0003\u0002.\u0005=RBAA\u0002\u0013\u0011\t\t$a\u0001\u0003\u000b9\u000bW.Z:\u0011\t\u00055\u0012QG\u0005\u0005\u0003o\t\u0019AA\u0004Ts6\u0014w\u000e\\:\u0011\t\u00055\u00121H\u0005\u0005\u0003{\t\u0019AA\u0003UsB,7\u000f\u0005\u0003\u0002.\u0005\u0005\u0013\u0002BA\"\u0003\u0007\u0011\u0011BV1sS\u0006t7-Z:\u0011\t\u00055\u0012qI\u0005\u0005\u0003\u0013\n\u0019AA\u0003LS:$7\u000f\u0005\u0003\u0002.\u00055\u0013\u0002BA(\u0003\u0007\u0011a#\u0012=jgR,g\u000e^5bYN\fe\u000eZ*l_2,Wn\u001d\t\u0005\u0003[\t\u0019&\u0003\u0003\u0002V\u0005\r!\u0001\u0003$mC\u001e\u001cV\r^:\u0011\t\u00055\u0012\u0011L\u0005\u0005\u00037\n\u0019A\u0001\u0004TG>\u0004Xm\u001d\t\u0005\u0003[\ty&\u0003\u0003\u0002b\u0005\r!aB'jeJ|'o\u001d\t\u0005\u0003[\t)'\u0003\u0003\u0002h\u0005\r!a\u0003#fM&t\u0017\u000e^5p]N\u0004B!!\f\u0002l%!\u0011QNA\u0002\u0005%\u0019uN\\:uC:$8\u000f\u0005\u0003\u0002.\u0005E\u0014\u0002BA:\u0003\u0007\u0011ABQ1tKRK\b/Z*fcN\u0004B!!\f\u0002x%!\u0011\u0011PA\u0002\u0005AIeNZ8Ue\u0006t7OZ8s[\u0016\u00148\u000f\u0005\u0003\u0002~\u0005\rUBAA@\u0015\u0011\t\t)a\u0001\u0002\u0013Q\u0014\u0018M\\:g_Jl\u0017\u0002BAC\u0003\u007f\u0012!\u0002\u0016:b]N4wN]7t!\u0011\ti#!#\n\t\u0005-\u00151\u0001\u0002\t'R$g*Y7fgB!\u0011QFAH\u0013\u0011\t\t*a\u0001\u0003\u001f\u0005sgn\u001c;bi&|g.\u00138g_N\u0004B!!\f\u0002\u0016&!\u0011qSA\u0002\u0005I\teN\\8uCRLwN\\\"iK\u000e\\WM]:\u0011\t\u00055\u00121T\u0005\u0005\u0003;\u000b\u0019AA\u0003Ue\u0016,7\u000f\u0005\u0003\u0002.\u0005\u0005\u0016\u0002BAR\u0003\u0007\u0011\u0001\u0002\u0015:j]R,'o\u001d\t\u0005\u0003[\t9+\u0003\u0003\u0002*\u0006\r!!\u0003)pg&$\u0018n\u001c8t!\u0011\ti#!,\n\t\u0005=\u00161\u0001\u0002\u000e)f\u0004X\rR3ck\u001e<\u0017N\\4\u0011\t\u00055\u00121W\u0005\u0005\u0003k\u000b\u0019AA\u0005J[B|'\u000f^3sgB!\u0011QFA]\u0013\u0011\tY,a\u0001\u0003#\r\u000b\u0007\u000f^;sK\u00124\u0016M]5bE2,7\u000f\u0005\u0003\u0002.\u0005}\u0016\u0002BAa\u0003\u0007\u0011ab\u0015;e\u0003R$\u0018m\u00195nK:$8\u000f\u0005\u0003\u0002.\u0005\u0015\u0017\u0002BAd\u0003\u0007\u00111b\u0015;e\u0007J,\u0017\r^8sgB!\u0011QFAf\u0013\u0011\ti-a\u0001\u0003%I+\u0017NZ5dCRLwN\\*vaB|'\u000f\u001e\t\u0005\u0003[\t\t.\u0003\u0003\u0002T\u0006\r!!\u0004)sSZ\fG/Z,ji\"Lg\u000e\u0005\u0003\u0002X\u0006uWBAAm\u0015\u0011\tY.a\u0001\u0002\u0011AL7m\u001b7j]\u001eLA!a8\u0002Z\naAK]1og2\fG/[8ogB!\u0011QFAr\u0013\u0011\t)/a\u0001\u0003\u0015\u0019\u0013Xm\u001d5OC6,7\u000f\u0005\u0003\u0002.\u0005%\u0018\u0002BAv\u0003\u0007\u0011\u0011\"\u00138uKJt\u0017\r\\:\u0011\t\u00055\u0012q^\u0005\u0005\u0003c\f\u0019AA\u0005SKB|'\u000f^5oO\u00061A(\u001b8jiz\"\"!a>\u0011\u0007\u00055\u0002!A\u0002hK:,\"!!@\u0013\t\u0005}(Q\u0001\u0004\u0007\u0005\u0003\u0019\u0001!!@\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0002\t\u001d,g\u000e\t\t\u0005\u0003[\u00119!\u0003\u0003\u0003\n\u0005\r!a\u0002+sK\u0016<UM\u001c\u0005\u000b\u0005\u001b\tyP1A\u0005\u0002\t=\u0011AB4m_\n\fG.\u0006\u0002\u0003\u00125\t\u0001A\u0001\u0007SK\u001adWm\u0019;Ti\u0006$8oE\b\u0005\u0005/\u0011yB!\n\u0003,\tE\"q\u0007B\u001f!\u0011\u0011IBa\u0007\u000e\u0005\u0005-\u0011\u0002\u0002B\u000f\u0003\u0017\u0011a!\u00118z%\u00164\u0007\u0003BA\u0017\u0005CIAAa\t\u0002\u0004\t\t\")Y:f)f\u0004XmU3rgN#\u0018\r^:\u0011\t\u00055\"qE\u0005\u0005\u0005S\t\u0019A\u0001\u0006UsB,7o\u0015;biN\u0004B!!\f\u0003.%!!qFA\u0002\u0005A\u0019\u00160\u001c2pYR\u000b'\r\\3Ti\u0006$8\u000f\u0005\u0003\u0002.\tM\u0012\u0002\u0002B\u001b\u0003\u0007\u0011!\u0002\u0016:fKN\u001cF/\u0019;t!\u0011\tiC!\u000f\n\t\tm\u00121\u0001\u0002\r'fl'm\u001c7t'R\fGo\u001d\t\u0005\u0003[\u0011y$\u0003\u0003\u0003B\u0005\r!AC*d_B,7\u000b^1ugJ1!Q\tB$\u0005\u00132aA!\u0001\u0001\u0001\t\r\u0003c\u0001B\t\tA!\u0011\u0011\u0005B&\u0013\u0011\u0011i%a\t\u0003\u0015M#\u0018\r^5ti&\u001c7/\u0001\u0006ti\u0006$\u0018n\u001d;jGN,\"Aa\u0015\u0013\r\tU#\u0011\nB$\r\u0019\u0011\t\u0001\u0001\u0001\u0003T\u0005\u0019An\\4\u0015\t\tm#\u0011\r\t\u0005\u00053\u0011i&\u0003\u0003\u0003`\u0005-!\u0001B+oSRD\u0001Ba\u0019\u0007\t\u0003\u0007!QM\u0001\u0004[N<\u0007C\u0002B\r\u0005O\u00129\"\u0003\u0003\u0003j\u0005-!\u0001\u0003\u001fcs:\fW.\u001a \u0002\u001d\u0015d\u0017\r]:fI6+7o]1hKR1!q\u000eB@\u0005+\u0003BA!\u001d\u0003|5\u0011!1\u000f\u0006\u0005\u0005k\u00129(\u0001\u0003mC:<'B\u0001B=\u0003\u0011Q\u0017M^1\n\t\tu$1\u000f\u0002\u0007'R\u0014\u0018N\\4\t\u000f\t\rt\u00011\u0001\u0003\u0002B!!1\u0011BI\u001d\u0011\u0011)I!$\u0011\t\t\u001d\u00151B\u0007\u0003\u0005\u0013SAAa#\u0002\u0010\u00051AH]8pizJAAa$\u0002\f\u00051\u0001K]3eK\u001aLAA! \u0003\u0014*!!qRA\u0006\u0011\u001d\u00119j\u0002a\u0001\u00053\u000bqa\u001d;beRt5\u000f\u0005\u0003\u0003\u001a\tm\u0015\u0002\u0002BO\u0003\u0017\u0011A\u0001T8oO\u0006q\u0011N\u001c4pe6\u0004&o\\4sKN\u001cH\u0003\u0002B.\u0005GCqAa\u0019\t\u0001\u0004\u0011\t)\u0001\u0006j]\u001a|'/\u001c+j[\u0016$bAa\u0017\u0003*\n-\u0006b\u0002B2\u0013\u0001\u0007!\u0011\u0011\u0005\b\u0005/K\u0001\u0019\u0001BM\u0003EIgNZ8s[&tw\r\u0015:pOJ,7o]\u000b\u0005\u0005c\u0013I\f\u0006\u0003\u00034\nEG\u0003\u0002B[\u0005\u0017\u0004BAa.\u0003:2\u0001Aa\u0002B^\u0015\t\u0007!Q\u0018\u0002\u0002)F!!q\u0018Bc!\u0011\u0011IB!1\n\t\t\r\u00171\u0002\u0002\b\u001d>$\b.\u001b8h!\u0011\u0011IBa2\n\t\t%\u00171\u0002\u0002\u0004\u0003:L\b\u0002\u0003Bg\u0015\u0011\u0005\rAa4\u0002\u0005\u0019t\u0007C\u0002B\r\u0005O\u0012)\f\u0003\u0005\u0003d)!\t\u0019\u0001Bj!\u0019\u0011IBa\u001a\u0003\u0002\"\u001a!Ba6\u0011\t\te!\u0011\\\u0005\u0005\u00057\fYA\u0001\u0004j]2Lg.Z\u0001\u0015g\"|W\u000f\u001c3M_\u001e\fE\u000f\u00165jgBC\u0017m]3\u0016\u0005\t\u0005\b\u0003\u0002B\r\u0005GLAA!:\u0002\f\t9!i\\8mK\u0006t\u0017aC5t!\u0006\u001cH\u000fV=qKJ\f1\"[:EKZ,Gn\u001c9fe\"\u001aQBa6\u0002\u0019AL7m\u001b7feBC\u0017m]3\u0016\u0005\tE\b\u0003BA\u0017\u0005gLAA!>\u0002\u0004\t)\u0001\u000b[1tK\u0006aQM]1tkJ,\u0007\u000b[1tK\u0006A1/\u001a;uS:<7/\u0006\u0002\u0003~B!!q`B\u0002\u001b\t\u0019\tA\u0003\u0003\u0003z\u0006\r\u0011\u0002BB\u0003\u0007\u0003\u0011q\"T;uC\ndWmU3ui&twm]\u0001\u001bSN\u001c\u00160\u001c2pY2{7m\u001b+sC\u000eLgnZ#oC\ndW\rZ\u0001\tI\u0016\u0014Wo\u001a7pOR!!1LB\u0007\u0011!\u0011\u0019G\u0005CA\u0002\tM\u0017\u0001\u00043fm^\u000b'O\\5oO&3G\u0003BB\n\u0007/!BAa\u0017\u0004\u0016!A!1M\n\u0005\u0002\u0004\u0011\u0019\u000e\u0003\u0005\u0004\u001aM!\t\u0019AB\u000e\u0003\u0011\u0019wN\u001c3\u0011\r\te!q\rBqQ\r\u0019\"q[\u0001\u000bI\u00164x+\u0019:oS:<G\u0003\u0002B.\u0007GA\u0001Ba\u0019\u0015\t\u0003\u0007!1[\u0001\u0012i\"\u0014xn^1cY\u0016\f5o\u0015;sS:<G\u0003\u0002BA\u0007SAqaa\u000b\u0016\u0001\u0004\u0019i#A\u0001u!\u0011\u0019yc!\u000e\u000f\t\te1\u0011G\u0005\u0005\u0007g\tY!A\u0004qC\u000e\\\u0017mZ3\n\t\r]2\u0011\b\u0002\n)\"\u0014xn^1cY\u0016TAaa\r\u0002\fQ1!\u0011QB\u001f\u0007\u007fAqaa\u000b\u0017\u0001\u0004\u0019i\u0003C\u0004\u0004BY\u0001\raa\u0011\u0002\u00135\f\u0007P\u0012:b[\u0016\u001c\b\u0003\u0002B\r\u0007\u000bJAaa\u0012\u0002\f\t\u0019\u0011J\u001c;\u0002'\u0011,goV1s]&tw\rR;naN#\u0018mY6\u0015\r\tm3QJB(\u0011!\u0011\u0019g\u0006CA\u0002\tM\u0007bBB!/\u0001\u000711\t\u0015\u0004/\t]\u0017A\u00033fEV<7\u000b^1dWR!!1LB,\u0011\u001d\u0019Y\u0003\u0007a\u0001\u0007[\t1\u0002\u001d:j]R\u001c\u0015\r\u001c7feV!1QLB2)\u0011\u0019yf!\u001b\u0015\t\r\u00054Q\r\t\u0005\u0005o\u001b\u0019\u0007B\u0004\u0003<f\u0011\rA!0\t\u000f\r\u001d\u0014\u00041\u0001\u0004b\u00051!/Z:vYRDqAa\u0019\u001a\u0001\u0004\u0011\t)A\u0006qe&tGOU3tk2$X\u0003BB8\u0007k\"Ba!\u001d\u0004zQ!11OB<!\u0011\u00119l!\u001e\u0005\u000f\tm&D1\u0001\u0003>\"91q\r\u000eA\u0002\rM\u0004b\u0002B25\u0001\u0007!\u0011Q\u0001\nY><'+Z:vYR,Baa \u0004\u0006R!1\u0011QBE)\u0011\u0019\u0019ia\"\u0011\t\t]6Q\u0011\u0003\b\u0005w[\"\u0019\u0001B_\u0011\u001d\u00199g\u0007a\u0001\u0007\u0007C\u0001Ba\u0019\u001c\t\u0003\u0007!1\u001b\u0015\u00047\t]\u0017A\u00043fEV<Gn\\4SKN,H\u000e^\u000b\u0005\u0007#\u001b9\n\u0006\u0003\u0004\u0014\u000emE\u0003BBK\u00073\u0003BAa.\u0004\u0018\u00129!1\u0018\u000fC\u0002\tu\u0006bBB49\u0001\u00071Q\u0013\u0005\t\u0005GbB\u00111\u0001\u0003T\"\u001aADa6\u0002!\u0011,goV1s]&twMU3tk2$X\u0003BBR\u0007S#Ba!*\u0004.R!1qUBV!\u0011\u00119l!+\u0005\u000f\tmVD1\u0001\u0003>\"91qM\u000fA\u0002\r\u001d\u0006\u0002\u0003B2;\u0011\u0005\rAa5)\u0007u\u00119.A\u0006m_\u001e\u0014Vm];mi&3W\u0003BB[\u0007w#baa.\u0004@\u000e\u0005G\u0003BB]\u0007{\u0003BAa.\u0004<\u00129!1\u0018\u0010C\u0002\tu\u0006bBB4=\u0001\u00071\u0011\u0018\u0005\t\u0005GrB\u00111\u0001\u0003T\"91\u0011\u0004\u0010A\u0002\r\r\u0007\u0003\u0003B\r\u0007\u000b\u001cIL!9\n\t\r\u001d\u00171\u0002\u0002\n\rVt7\r^5p]FB3A\bBl\u0003A!WMY;hY><'+Z:vYRLe-\u0006\u0003\u0004P\u000eUGCBBi\u00073\u001cY\u000e\u0006\u0003\u0004T\u000e]\u0007\u0003\u0002B\\\u0007+$qAa/ \u0005\u0004\u0011i\fC\u0004\u0004h}\u0001\raa5\t\u0011\t\rt\u0004\"a\u0001\u0005'Dqa!\u0007 \u0001\u0004\u0019i\u000e\u0005\u0005\u0003\u001a\r\u001571\u001bBqQ\ry\"q[\u0001\u0007CN\u001cXM\u001d;\u0015\r\tm3Q]Bu\u0011\u001d\u00199\u000f\ta\u0001\u0005C\f\u0011\"Y:tKJ$\u0018n\u001c8\t\u0011\r-\b\u0005\"a\u0001\u0007[\fq!\\3tg\u0006<W\r\u0005\u0004\u0003\u001a\t\u001d$Q\u0019\u0015\u0004A\t]G\u0003\u0002B.\u0007gDqaa:\"\u0001\u0004\u0011\t\u000fK\u0006\"\u0007o\u001cYo!@\u0005\u0002\u0011\r\u0001\u0003\u0002B\r\u0007sLAaa?\u0002\f\tQA-\u001a9sK\u000e\fG/\u001a3\"\u0005\r}\u0018!K2p]NLG-\u001a:!gV\u0004\b\u000f\\=j]\u001e\u0004\u0013M\u001c\u0011fqBd\u0017M\\1u_JL\b%\\3tg\u0006<W-A\u0003tS:\u001cW-\t\u0002\u0005\u0006\u00051!GL\u00193]U\nqA]3rk&\u0014X\r\u0006\u0004\u0003\\\u0011-Aq\u0002\u0005\b\t\u001b\u0011\u0003\u0019\u0001Bq\u0003-\u0011X-];je\u0016lWM\u001c;\t\u0011\r-(\u0005\"a\u0001\u0007[D3A\tBl)\u0011\u0011Y\u0006\"\u0006\t\u000f\u001151\u00051\u0001\u0003b\"Z1ea>\u0004l\u000euH\u0011\u0001C\u0002\u0003M!\bN]8x\u0003N\u001cXM\u001d;j_:,%O]8s)\u0011\u0011y\f\"\b\t\u000f\t\rD\u00051\u0001\u0003F\u0006)B\u000f\u001b:poJ+\u0017/^5sK6,g\u000e^#se>\u0014H\u0003\u0002B`\tGAqAa\u0019&\u0001\u0004\u0011)-\u0001\u0006gS:$7+_7c_2$B\u0001\"\u000b\u00058Q!A1\u0006C\u0019!\u0011\u0011\t\u0002\"\f\n\t\u0011=\u0012Q\u0007\u0002\u0007'fl'm\u001c7\t\u000f\u0011Mb\u00051\u0001\u00056\u0005\t\u0001\u000f\u0005\u0005\u0003\u001a\r\u0015G1\u0006Bq\u0011\u001d!ID\na\u0001\tw\t!\u0001_:\u0011\r\r=BQ\bC\u0016\u0013\u0011!yd!\u000f\u0003\u0019%#XM]1cY\u0016|enY3)\u0007\u0019\u00129.A\fm_^\u0004&/[8sSRLh*Y7f\u001fJ$WM]5oOV!Aq\tC)+\t!I\u0005\u0005\u0004\u00040\u0011-CqJ\u0005\u0005\t\u001b\u001aID\u0001\u0005Pe\u0012,'/\u001b8h!\u0011\u00119\f\"\u0015\u0005\u000f\tmvE1\u0001\u0005TE!!q\u0018C+!\u0011\tY\u0003b\u0016\n\t\u0011e\u0013q\u0006\u0002\u0005\u001d\u0006lW-\u0001\nTS6\u0004H.\u001a(b[\u0016|%\u000fZ3sS:<\u0007c\u0001B\tS\t\u00112+[7qY\u0016t\u0015-\\3Pe\u0012,'/\u001b8h'\u0015IC1\rC5!\u0011\u0011\t\b\"\u001a\n\t\u0011\u001d$1\u000f\u0002\u0007\u001f\nTWm\u0019;\u0011\r\r=B1\nC+)\t!i&A\u0004d_6\u0004\u0018M]3\u0015\r\r\rC\u0011\u000fC;\u0011\u001d!\u0019h\u000ba\u0001\t+\n!A\\\u0019\t\u000f\u0011]4\u00061\u0001\u0005V\u0005\u0011aNM\u0001\u0014iJ\f7-Z*z[\n|G.Q2uSZLG/_\u0001\u0015iJ\f7-Z*z[\n|G.Q2uSZLG/\u001f\u0011\u0002\u0019Q\u0014\u0018mY3Ts6\u0014w\u000e\\:\u0011\u0007\tEqF\u0001\u0007ue\u0006\u001cWmU=nE>d7oE\u00030\u0005/!)\t\u0005\u0003\u0002\"\u0011\u001d\u0015\u0002\u0002CE\u0003G\u00111\u0003\u0016:bG\u0016\u001c\u00160\u001c2pY\u0006\u001bG/\u001b<jif\fqa\u001a7pE\u0006d\u0007\u0005\u0006\u0002\u0005\u0000\u0005AAO]3f\u0013:4w.\u0006\u0002\u0005\u0014J!AQ\u0013CL\r\u0019\u0011\t\u0001\u0001\u0001\u0005\u0014B!\u0011Q\u0006CM\u0013\u0011!Y*a\u0001\u0003\u0011Q\u0013X-Z%oM>D!B!\u0004\u0005\u0016\n\u0007i\u0011\tB\b\u0003M\t7o]3si\u000e{'O]3diRC'/Z1e)\t\u0011Y\u0006K\u00045\tK#\t\fb-\u0011\t\u0011\u001dFQV\u0007\u0003\tSSA\u0001b+\u0002\f\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0011=F\u0011\u0016\u0002\tK2LG-\u00192mK\u0006)A.\u001a<fYv\u00111\u0001b\u0001\f[&\u001c8/\u001b8h\u0011>|7\u000e\u0006\u0004\u0005,\u0011eFQ\u0018\u0005\b\tw+\u0004\u0019\u0001C\u0016\u0003\u0015ywO\\3s\u0011\u001d!y,\u000ea\u0001\t\u0003\fAA\\1nKB!!\u0011\u0003C,\u0003Ai\u0017N\u001d:peRC\u0017\r\u001e'pC\u0012,G\r\u0006\u0003\u0005H\u00125\u0007\u0003\u0002B\t\t\u0013LA\u0001b3\u0002`\t1Q*\u001b:s_JDq\u0001b47\u0001\u0004!Y#A\u0002ts6\u0014a\u0001U3sS>$\u0017\u0001\u0003(p!\u0016\u0014\u0018n\u001c3\u0016\u0005\u0011]wB\u0001Cm;\u0005\u0001\u0011!\u0003(p!\u0016\u0014\u0018n\u001c3!\u0005\u0015\u0011VO\\%e\u0003\u001dquNU;o\u0013\u0012\f\u0001BT8Sk:LE\rI\u0001\ba\"\u001cF/Y2l!\u0019!9\u000f\"=\u0003r6\u0011A\u0011\u001e\u0006\u0005\tW$i/A\u0004nkR\f'\r\\3\u000b\t\u0011=\u00181B\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002Cz\tS\u0014Qa\u0015;bG.\f!\u0001\u001d5\u0002\u0007A,'/\u0001\u0007biBC\u0017m]3Ti\u0006\u001c7.\u0006\u0002\u0005~B11q\u0006C\u0000\u0005cLA!\"\u0001\u0004:\t!A*[:u\u0003\u0015\u0001\b.Y:f\u0003M\tG\u000f\u00155bg\u0016\u001cF/Y2l\u001b\u0016\u001c8/Y4f+\t\u0011\t)A\u0005qQ\u0006\u001cXm\u0018\u0013fcR!!1LC\u0007\u0011\u001d!\u0019d\u0011a\u0001\u0005c\f\u0011\u0002];tQBC\u0017m]3\u0015\t\tEX1\u0003\u0005\b\tk$\u0005\u0019\u0001By\u0003!\u0001x\u000e\u001d)iCN,G\u0003\u0002B.\u000b3Aq\u0001\">F\u0001\u0004\u0011\t0\u0001\blK\u0016\u0004\b\u000b[1tKN#\u0018mY6\u0002%-,W\r\u001d)iCN,7\u000b^1dW~#S-\u001d\u000b\u0005\u00057*\t\u0003C\u0005\u0006$\u001d\u000b\t\u00111\u0001\u0003b\u0006\u0019\u0001\u0010J\u0019\u0002\u001f-,W\r\u001d)iCN,7\u000b^1dW\u0002\nAbY;se\u0016tGOU;o\u0013\u0012,\"!b\u000b\u0011\u0007\tE!(A\u0003sk:LE\r\u0006\u0003\u0006,\u0015E\u0002bBC\u001a\u0015\u0002\u0007QQG\u0001\u0007a\u0016\u0014\u0018n\u001c3\u0011\u0007\tEq'A\u0004qQ\u0006\u001cX-\u00133\u0015\t\r\rS1\b\u0005\b\u000bgY\u0005\u0019AC\u001b\u00035\u0019WO\u001d:f]R\u0004VM]5pIV\u0011QQG\u0001\ba\"\f7/Z(g)\u0011\u0011\t0\"\u0012\t\u000f\u0015MR\n1\u0001\u00066Q1QQGC%\u000b\u001bBq!b\u0013O\u0001\u0004)Y#A\u0002sS\u0012Dq!b\u0014O\u0001\u0004\u0019\u0019%A\u0002qS\u0012\fa\"[:BiBC\u0017m]3BMR,'\u000f\u0006\u0003\u0003b\u0016U\u0003b\u0002C\u001a\u001f\u0002\u0007!\u0011_\u0001\u000eK:$XM]5oOBC\u0017m]3\u0016\t\u0015mS\u0011\r\u000b\u0005\u000b;*I\u0007\u0006\u0003\u0006`\u0015\r\u0004\u0003\u0002B\\\u000bC\"qAa/Q\u0005\u0004\u0011i\f\u0003\u0005\u0006fA#\t\u0019AC4\u0003\ty\u0007\u000f\u0005\u0004\u0003\u001a\t\u001dTq\f\u0005\b\tk\u0004\u0006\u0019\u0001ByQ\r\u0001&q[\u0001\u0012M&tG\r\u00155bg\u0016<\u0016\u000e\u001e5OC6,G\u0003\u0002By\u000bcBq!b\u001dR\u0001\u0004\u0011\t)A\u0005qQ\u0006\u001cXMT1nK\u0006)RM\u001c;fe&tw\r\u00155bg\u0016<\u0016\u000e\u001e5OC6,W\u0003BC=\u000b\u007f\"B!b\u001f\u0006\bR!QQPCA!\u0011\u00119,b \u0005\u000f\tm&K1\u0001\u0003>\"AQ1\u0011*\u0005\u0002\u0004)))\u0001\u0003c_\u0012L\bC\u0002B\r\u0005O*i\bC\u0004\u0006tI\u0003\rA!!\u00021Mdwn\u001e\"viN\u000bg-Z#oi\u0016\u0014\u0018N\\4QQ\u0006\u001cX-\u0006\u0003\u0006\u000e\u0016ME\u0003BCH\u000b3#B!\"%\u0006\u0016B!!qWCJ\t\u001d\u0011Yl\u0015b\u0001\u0005{C\u0001\"\"\u001aT\t\u0003\u0007Qq\u0013\t\u0007\u00053\u00119'\"%\t\u000f\u0011U8\u000b1\u0001\u0003r\u0006aQ\r_5uS:<\u0007\u000b[1tKV!QqTCS)\u0011)\t+b+\u0015\t\u0015\rVq\u0015\t\u0005\u0005o+)\u000bB\u0004\u0003<R\u0013\rA!0\t\u0011\u0015\u0015D\u000b\"a\u0001\u000bS\u0003bA!\u0007\u0003h\u0015\r\u0006b\u0002C{)\u0002\u0007!\u0011\u001f\u0015\u0004)\n]\u0017!E3oi\u0016\u0014\u0018N\\4Qe\u00164\b\u000b[1tKV!Q1WC\\)\u0011)),\"/\u0011\t\t]Vq\u0017\u0003\b\u0005w+&\u0019\u0001B_\u0011!))'\u0016CA\u0002\u0015m\u0006C\u0002B\r\u0005O*)\fK\u0002V\u0005/\f\u0011$\u001a8uKJLgn\u001a)iCN,gj\u001c;MCR,'\u000f\u00165b]V!Q1YCe)\u0011))-b4\u0015\t\u0015\u001dW1\u001a\t\u0005\u0005o+I\rB\u0004\u0003<Z\u0013\rA!0\t\u0011\u0015\u0015d\u000b\"a\u0001\u000b\u001b\u0004bA!\u0007\u0003h\u0015\u001d\u0007bBCi-\u0002\u0007!\u0011_\u0001\u0007i\u0006\u0014x-\u001a;)\u0007Y\u00139.\u0001\u0013tY><()\u001e;TC\u001a,WI\u001c;fe&tw\r\u00155bg\u0016tu\u000e\u001e'bi\u0016\u0014H\u000b[1o+\u0011)I.b8\u0015\t\u0015mWQ\u001d\u000b\u0005\u000b;,\t\u000f\u0005\u0003\u00038\u0016}Ga\u0002B^/\n\u0007!Q\u0018\u0005\t\u000bK:F\u00111\u0001\u0006dB1!\u0011\u0004B4\u000b;Dq!\"5X\u0001\u0004\u0011\t0A\u0004jgZ\u000bG.\u001b3\u0015\t\t\u0005X1\u001e\u0005\b\u000bgA\u0006\u0019AC\u001b\u0003UI7OV1mS\u00124uN\u001d\"bg\u0016\u001cE.Y:tKN$BA!9\u0006r\"9Q1G-A\u0002\u0015U\u0012!E8qK:\u0004\u0016mY6bO\u0016lu\u000eZ;mKR1!1LC|\u000bwDq!\"?[\u0001\u0004!Y#A\u0005d_:$\u0018-\u001b8fe\"9QQ .A\u0002\u0011-\u0012\u0001\u00023fgR\fq\"\u0019:sCf$vNU3qK\u0006$X\r\u001a\u000b\u0005\r\u00071I\u0001\u0005\u0003\u0003\u0012\u0019\u0015\u0011\u0002\u0002D\u0004\u0003w\u0011A\u0001V=qK\"9a1B.A\u0002\u0019\r\u0011A\u0001;q\u0005%\u0019\u00160\u001c'pC\u0012,'oE\u0002]\r#\u0001BA!\u0005\u0007\u0014%!aQCA\u001e\u0005!a\u0015M_=UsB,GC\u0001D\r!\r\u0011\t\u0002X\u0001\u000bMJ|WnU8ve\u000e,GC\u0002B.\r?1\u0019\u0003C\u0004\u0007\"}\u0003\r\u0001b\u000b\u0002\u0011A\\wm\u00117bgND\u0011B\"\n`!\u0003\u0005\rA!9\u0002\u000b\u0019|'oY3\u00027=\u0004XM\u001c)bG.\fw-Z'pIVdW\r\n3fM\u0006,H\u000e\u001e\u00133+\t1YC\u000b\u0003\u0003b\u001a52F\u0001D\u0018!\u00111\tDb\u000e\u000e\u0005\u0019M\"\u0002\u0002D\u001b\tS\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\n\t\u0019eb1\u0007\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017\u0001\u00049feJ+hnQ1dQ\u0016\u001c\bc\u0001B\tE\na\u0001/\u001a:Sk:\u001c\u0015m\u00195fgN\u0019!Ma\u0006\u0015\u0005\u0019u\u0012AB2bG\",7\u000f\u0005\u0004\u0007J\u0019=c\u0011K\u0007\u0003\r\u0017RAA\"\u0014\u0005n\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u000b\u00031Y\u0005\u0005\u0004\u0007T\u0019ecQL\u0007\u0003\r+RAAb\u0016\u0003t\u0005\u0019!/\u001a4\n\t\u0019mcQ\u000b\u0002\u000e/\u0016\f7NU3gKJ,gnY3\u0011\t\u0011\u001dhqL\u0005\u0005\rC\"IOA\u0005DY\u0016\f'/\u00192mK\u0006Q!.\u0019<b\u0007\u0006\u001c\u0007.Z:\u0011\r\u0019%cq\nD4a\u00111IG\"\u001d\u0011\r\u0005\u0005b1\u000eD8\u0013\u00111i'a\t\u0003\u001b)\u000bg/Y\"mK\u0006\u0014\u0018M\u00197f!\u0011\u00119L\"\u001d\u0005\u0017\u0019MT-!A\u0001\u0002\u000b\u0005!Q\u0018\u0002\u0004?\u0012\n\u0014a\u0003:fG>\u0014HmQ1dQ\u0016,BA\"\u001f\u0007\u0004R!a1\u0010D?\u001d\u0011\u00119L\" \t\u000f\u0019}d\r1\u0001\u0007\u0002\u0006)1-Y2iKB!!q\u0017DB\t\u001d\u0011YL\u001ab\u0001\r\u000b\u000bBAa0\u0007^\u0005\t\"/Z2pe\u0012\u001cE.Y:tY>\fG-\u001a:\u0015\t\u0019-eQ\u0012\b\u0005\u0005o3i\tC\u0004\u0007\u0010\u001e\u0004\rA\"%\u0002\r1|\u0017\rZ3s!\u0011\u0011\tHb%\n\t\u0019U%1\u000f\u0002\f\u00072\f7o\u001d'pC\u0012,'/A\u0007v]J,7m\u001c:e\u0007\u0006\u001c\u0007.Z\u000b\u0005\r73\t\u000b\u0006\u0003\u0003\\\u0019u\u0005b\u0002D@Q\u0002\u0007aq\u0014\t\u0005\u0005o3\t\u000bB\u0004\u0003<\"\u0014\rA\"\"\u0002\u0011\rdW-\u0019:BY2\f!B\\3x/\u0016\f7.T1q+\u00191IKb-\u0007:R\u0011a1\u0016\t\t\tO4iK\"-\u00078&!aq\u0016Cu\u0005-9V-Y6ICNDW*\u00199\u0011\t\t]f1\u0017\u0003\b\rkS'\u0019\u0001B_\u0005\u0005Y\u0005\u0003\u0002B\\\rs#qAb/k\u0005\u0004\u0011iLA\u0001W\u0003\u0019qWm^'baV1a\u0011\u0019Df\r\u001f$\"Ab1\u0011\u0011\u0011\u001dhQ\u0019De\r\u001bLAAb2\u0005j\n9\u0001*Y:i\u001b\u0006\u0004\b\u0003\u0002B\\\r\u0017$qA\".l\u0005\u0004\u0011i\f\u0005\u0003\u00038\u001a=Ga\u0002D^W\n\u0007!QX\u0001\u0007]\u0016<8+\u001a;\u0016\t\u0019Ugq\u001c\u000b\u0003\r/\u0004b\u0001b:\u0007Z\u001au\u0017\u0002\u0002Dn\tS\u0014q\u0001S1tQN+G\u000f\u0005\u0003\u00038\u001a}Ga\u0002D[Y\n\u0007!QX\u0001\u000b]\u0016<x+Z1l'\u0016$X\u0003\u0002Ds\r_$\"Ab:\u0011\r\u0005\u0005b\u0011\u001eDw\u0013\u00111Y/a\t\u0003\u0017]+\u0017m\u001b%bg\"\u001cV\r\u001e\t\u0005\u0005o3y\u000fB\u0004\u000766\u0014\rA\"=\u0012\t\t}&qC\u0001\r]\u0016<\u0018I\\=SK\u001al\u0015\r]\u000b\u0007\ro<\ta\"\u0002\u0015\u0005\u0019e\b\u0003\u0003Ct\rw4ypb\u0001\n\t\u0019uH\u0011\u001e\u0002\n\u0003:L(+\u001a4NCB\u0004BAa.\b\u0002\u00119aQ\u00178C\u0002\u0019E\b\u0003\u0002B\\\u000f\u000b!qAb/o\u0005\u0004\u0011i,\u0006\u0004\b\n\u001d=q1\u0003\u000b\u0005\u000f\u00179)\u0002\u0005\u0005\u0005h\u001amxQBD\t!\u0011\u00119lb\u0004\u0005\u000f\u0019UvN1\u0001\u0007rB!!qWD\n\t\u001d1Yl\u001cb\u0001\u0005{Cqab\u0006p\u0001\u00049I\"A\u0004eK\u001a\fW\u000f\u001c;\u0011\u0011\te1QYD\u0007\u000f#\t!B\\3x\u000f\u0016tWM]5d+\u00119yb\"\u000b\u0015\r\u001d\u0005r1FD\u0019!\u0019\u0011Ibb\t\b(%!qQEA\u0006\u0005%1UO\\2uS>t\u0007\u0007\u0005\u0003\u00038\u001e%Ba\u0002B^a\n\u0007!Q\u0018\u0005\t\u000f[\u0001H\u00111\u0001\b0\u0005\ta\r\u0005\u0004\u0003\u001a\t\u001dtq\u0005\u0005\n\u000fg\u0001\b\u0013!a\u0001\u000fk\tqa\u00197fC:,\b\u000f\u0005\u0005\u0003\u001a\r\u0015wq\u0005B.\u0003QqWm^$f]\u0016\u0014\u0018n\u0019\u0013eK\u001a\fW\u000f\u001c;%eU!q1HD!+\t9iD\u000b\u0003\b@\u00195\u0002\u0003\u0003B\r\u0007\u000b\u0014)Ma\u0017\u0005\u000f\tm\u0016O1\u0001\u0003>\u0006\u0001\u0012N\u001c4p)J\fgn\u001d4pe6,'o]\u000b\u0003\u000f\u000f\u0002BA!\u0005\bJ%!q1JA<\u0005=IeNZ8Ue\u0006t7OZ8s[\u0016\u0014\u0018\u0001F5oM>$&/\u00198tM>\u0014X.\u001a:t?\u0012*\u0017\u000f\u0006\u0003\u0003\\\u001dE\u0003\"CC\u0012g\u0006\u0005\t\u0019AD$\u0003EIgNZ8Ue\u0006t7OZ8s[\u0016\u00148\u000fI\u0001\t]\u0016DHO\u0012:p[V\u0011q\u0011\f\t\u0007\u000539Yfb\u0012\n\t\u001du\u00131\u0002\u0002\u0006\u0003J\u0014\u0018-_\u0001\r]\u0016DHO\u0012:p[~#S-\u001d\u000b\u0005\u00057:\u0019\u0007C\u0005\u0006$Y\f\t\u00111\u0001\bZ\u0005Ia.\u001a=u\rJ|W\u000eI\u0001\n\u001b\u0006D\b\u000b[1tKN,\"ab\u001b\u0010\u0005\u001d5TDA\u0001\u0001\u0003)i\u0015\r\u001f)iCN,7\u000fI\u0001\fa\"\f7/Z,ji\"LE-\u0006\u0002\bvA1!\u0011DD.\u0005c\fA\u0002\u001d5bg\u0016<\u0016\u000e\u001e5JI\u0002\n!#[:D_6\u0004\u0018\u000e\\3s+:Lg/\u001a:tK\u00069\u0011\r\u001e)iCN,W\u0003BD@\u000f\u000b#Ba\"!\b\fR!q1QDD!\u0011\u00119l\"\"\u0005\u000f\tmVP1\u0001\u0003>\"AQQM?\u0005\u0002\u00049I\t\u0005\u0004\u0003\u001a\t\u001dt1\u0011\u0005\b\tkl\b\u0019\u0001ByQ-i8q_Bv\u000f\u001f#\tab%\"\u0005\u001dE\u0015!E;tK\u0002*g\u000e^3sS:<\u0007\u000b[1tK\u0006\u0012qQS\u0001\u0007e9\n\u0004G\f\u0019)\u0007u\u00149.\u0001\u0012dkJ\u0014XM\u001c;Sk:\u0004&o\u001c4jY\u0016\u0014()\u001a4pe\u0016\u001cu.\u001c9mKRLwN\u001c\u000b\u0007\u00057:ij\")\t\u000f\u001d}e\u00101\u0001\u0005,\u0005!!o\\8u\u0011\u001d9\u0019K a\u0001\u000fK\u000ba\"Y:t_\u000eL\u0017\r^3e\r&dW\r\u0005\u0003\b(\u001e5VBADU\u0015\u00119Y+a\u0002\u0002\u0005%|\u0017\u0002BDX\u000fS\u0013A\"\u00112tiJ\f7\r\u001e$jY\u0016\f\u0011eY;se\u0016tGOU;o!J|g-\u001b7fe\u00063G/\u001a:D_6\u0004H.\u001a;j_:$bAa\u0017\b6\u001e]\u0006bBDP\u007f\u0002\u0007A1\u0006\u0005\b\u000fG{\b\u0019ADS\u0001"
)
public abstract class SymbolTable extends Universe implements Names, Symbols, Types, Variances, Kinds, ExistentialsAndSkolems, FlagSets, Scopes, Mirrors, Definitions, Constants, BaseTypeSeqs, InfoTransformers, Transforms, StdNames, AnnotationInfos, AnnotationCheckers, Trees, Printers, Positions, TypeDebugging, Importers, CapturedVariables, StdAttachments, StdCreators, ReificationSupport, PrivateWithin, Translations, FreshNames, Internals, Reporting {
   private volatile SimpleNameOrdering$ SimpleNameOrdering$module;
   private volatile traceSymbols$ traceSymbols$module;
   private volatile perRunCaches$ perRunCaches$module;
   private final scala.reflect.internal.TreeGen gen;
   private final boolean traceSymbolActivity;
   private final Stack phStack;
   private Phase ph;
   private int per;
   private boolean keepPhaseStack;
   private InfoTransformers.InfoTransformer infoTransformers;
   private InfoTransformers.InfoTransformer[] nextFrom;
   private final Phase[] phaseWithId;
   private Universe.MacroInternalApi internal;
   /** @deprecated */
   private Universe.MacroCompatApi compat;
   private Universe.TreeGen treeBuild;
   private FreshNameCreator globalFreshNameCreator;
   private volatile FreshNames.FreshNameExtractor$ FreshNameExtractor$module;
   private ReificationSupport.ReificationSupportImpl build;
   private volatile StdCreators.FixedMirrorTreeCreator$ FixedMirrorTreeCreator$module;
   private volatile StdCreators.FixedMirrorTypeCreator$ FixedMirrorTypeCreator$module;
   private volatile StdAttachments.CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment$module;
   private volatile StdAttachments.SAMFunction$ SAMFunction$module;
   private volatile StdAttachments.DelambdafyTarget$ DelambdafyTarget$module;
   private volatile StdAttachments.BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment$module;
   private volatile StdAttachments.PostfixAttachment$ PostfixAttachment$module;
   private volatile StdAttachments.InfixAttachment$ InfixAttachment$module;
   private volatile StdAttachments.AutoApplicationAttachment$ AutoApplicationAttachment$module;
   private volatile StdAttachments.NoWarnAttachment$ NoWarnAttachment$module;
   private volatile StdAttachments.PatShadowAttachment$ PatShadowAttachment$module;
   private volatile StdAttachments.PatVarDefAttachment$ PatVarDefAttachment$module;
   private volatile StdAttachments.MultiDefAttachment$ MultiDefAttachment$module;
   private volatile StdAttachments.ForAttachment$ ForAttachment$module;
   private volatile StdAttachments.SyntheticUnitAttachment$ SyntheticUnitAttachment$module;
   private volatile StdAttachments.SubpatternsAttachment$ SubpatternsAttachment$module;
   private volatile StdAttachments.NoInlineCallsiteAttachment$ NoInlineCallsiteAttachment$module;
   private volatile StdAttachments.InlineCallsiteAttachment$ InlineCallsiteAttachment$module;
   private volatile StdAttachments.OuterArgCanBeElided$ OuterArgCanBeElided$module;
   private volatile StdAttachments.UseInvokeSpecial$ UseInvokeSpecial$module;
   private volatile StdAttachments.TypeParamVarargsAttachment$ TypeParamVarargsAttachment$module;
   private volatile StdAttachments.KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled$module;
   private volatile StdAttachments.DottyEnumSingleton$ DottyEnumSingleton$module;
   private volatile StdAttachments.ConstructorNeedsFence$ ConstructorNeedsFence$module;
   private volatile StdAttachments.MultiargInfixAttachment$ MultiargInfixAttachment$module;
   private volatile StdAttachments.NullaryOverrideAdapted$ NullaryOverrideAdapted$module;
   private volatile StdAttachments.ChangeOwnerAttachment$ ChangeOwnerAttachment$module;
   private volatile StdAttachments.InterpolatedString$ InterpolatedString$module;
   private volatile StdAttachments.VirtualStringContext$ VirtualStringContext$module;
   private volatile StdAttachments.CaseApplyInheritAccess$ CaseApplyInheritAccess$module;
   private volatile StdAttachments.RootSelection$ RootSelection$module;
   private volatile StdAttachments.TypedExpectingUnitAttachment$ TypedExpectingUnitAttachment$module;
   private volatile StdAttachments.FieldTypeInferred$ FieldTypeInferred$module;
   private volatile StdAttachments.LookupAmbiguityWarning$ LookupAmbiguityWarning$module;
   private volatile StdAttachments.PermittedSubclasses$ PermittedSubclasses$module;
   private volatile StdAttachments.PermittedSubclassSymbols$ PermittedSubclassSymbols$module;
   private volatile StdAttachments.NamePos$ NamePos$module;
   private volatile StdAttachments.UnnamedArg$ UnnamedArg$module;
   private volatile StdAttachments.DiscardedValue$ DiscardedValue$module;
   private volatile StdAttachments.DiscardedExpr$ DiscardedExpr$module;
   private volatile StdAttachments.BooleanParameterType$ BooleanParameterType$module;
   private volatile TypeDebugging.noPrint$ noPrint$module;
   private volatile TypeDebugging.typeDebug$ typeDebug$module;
   private NoPosition$ NoPosition;
   private ClassTag PositionTag;
   private Ordering scala$reflect$internal$Positions$$posStartOrdering;
   private ReusableInstance scala$reflect$internal$Positions$$setChildrenPosAccumulator;
   private Positions.PosAssigner posAssigner;
   private volatile Printers.ConsoleWriter$ ConsoleWriter$module;
   private int nodeCount;
   private volatile Trees.RefTree$ RefTree$module;
   private volatile Trees.PackageDef$ PackageDef$module;
   private volatile Trees.ClassDef$ ClassDef$module;
   private volatile Trees.ModuleDef$ ModuleDef$module;
   private volatile Trees.ValOrDefDef$ ValOrDefDef$module;
   private volatile Trees.ValDef$ ValDef$module;
   private volatile Trees.DefDef$ DefDef$module;
   private volatile Trees.TypeDef$ TypeDef$module;
   private volatile Trees.LabelDef$ LabelDef$module;
   private volatile Trees.ImportSelector$ ImportSelector$module;
   private volatile Trees.Import$ Import$module;
   private volatile Trees.Template$ Template$module;
   private volatile Trees.Block$ Block$module;
   private volatile Trees.CaseDef$ CaseDef$module;
   private volatile Trees.Alternative$ Alternative$module;
   private volatile Trees.Star$ Star$module;
   private volatile Trees.Bind$ Bind$module;
   private volatile Trees.UnApply$ UnApply$module;
   private volatile Trees.ArrayValue$ ArrayValue$module;
   private volatile Trees.Function$ Function$module;
   private volatile Trees.Assign$ Assign$module;
   private volatile Trees.NamedArg$ NamedArg$module;
   private volatile Trees.If$ If$module;
   private volatile Trees.Match$ Match$module;
   private volatile Trees.Return$ Return$module;
   private volatile Trees.Try$ Try$module;
   private volatile Trees.Throw$ Throw$module;
   private volatile Trees.New$ New$module;
   private volatile Trees.Typed$ Typed$module;
   private volatile Trees.MethodValue$ MethodValue$module;
   private volatile Trees.TypeApply$ TypeApply$module;
   private volatile Trees.Apply$ Apply$module;
   private volatile Trees.ApplyDynamic$ ApplyDynamic$module;
   private volatile Trees.Super$ Super$module;
   private volatile Trees.This$ This$module;
   private volatile Trees.Select$ Select$module;
   private volatile Trees.Ident$ Ident$module;
   private volatile Trees.ReferenceToBoxed$ ReferenceToBoxed$module;
   private volatile Trees.Literal$ Literal$module;
   private volatile Trees.Annotated$ Annotated$module;
   private volatile Trees.SingletonTypeTree$ SingletonTypeTree$module;
   private volatile Trees.SelectFromTypeTree$ SelectFromTypeTree$module;
   private volatile Trees.CompoundTypeTree$ CompoundTypeTree$module;
   private volatile Trees.AppliedTypeTree$ AppliedTypeTree$module;
   private volatile Trees.TypeBoundsTree$ TypeBoundsTree$module;
   private volatile Trees.ExistentialTypeTree$ ExistentialTypeTree$module;
   private volatile Trees.TypeTree$ TypeTree$module;
   private volatile Trees.Modifiers$ Modifiers$module;
   private ClassTag ModifiersTag;
   private volatile Trees.EmptyTree$ EmptyTree$module;
   private volatile Trees.noSelfType$ noSelfType$module;
   private volatile Trees.pendingSuperCall$ pendingSuperCall$module;
   /** @deprecated */
   private Trees.noSelfType$ emptyValDef;
   private Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter;
   private Trees.Duplicator scala$reflect$internal$Trees$$duplicator;
   private volatile Trees.duplicateAndResetPos$ duplicateAndResetPos$module;
   private volatile Trees.focuser$ focuser$module;
   private ReusableInstance scala$reflect$internal$Trees$$onlyChildAccumulator;
   private ClassTag AlternativeTag;
   private ClassTag AnnotatedTag;
   private ClassTag AppliedTypeTreeTag;
   private ClassTag ApplyTag;
   private ClassTag NamedArgTag;
   private ClassTag AssignTag;
   private ClassTag BindTag;
   private ClassTag BlockTag;
   private ClassTag CaseDefTag;
   private ClassTag ClassDefTag;
   private ClassTag CompoundTypeTreeTag;
   private ClassTag DefDefTag;
   private ClassTag DefTreeTag;
   private ClassTag ExistentialTypeTreeTag;
   private ClassTag FunctionTag;
   private ClassTag GenericApplyTag;
   private ClassTag IdentTag;
   private ClassTag IfTag;
   private ClassTag ImplDefTag;
   private ClassTag ImportSelectorTag;
   private ClassTag ImportTag;
   private ClassTag LabelDefTag;
   private ClassTag LiteralTag;
   private ClassTag MatchTag;
   private ClassTag MemberDefTag;
   private ClassTag ModuleDefTag;
   private ClassTag NameTreeTag;
   private ClassTag NewTag;
   private ClassTag PackageDefTag;
   private ClassTag ReferenceToBoxedTag;
   private ClassTag RefTreeTag;
   private ClassTag ReturnTag;
   private ClassTag SelectFromTypeTreeTag;
   private ClassTag SelectTag;
   private ClassTag SingletonTypeTreeTag;
   private ClassTag StarTag;
   private ClassTag SuperTag;
   private ClassTag SymTreeTag;
   private ClassTag TemplateTag;
   private ClassTag TermTreeTag;
   private ClassTag ThisTag;
   private ClassTag ThrowTag;
   private ClassTag TreeTag;
   private ClassTag TryTag;
   private ClassTag TypTreeTag;
   private ClassTag TypeApplyTag;
   private ClassTag TypeBoundsTreeTag;
   private ClassTag TypeDefTag;
   private ClassTag TypeTreeTag;
   private ClassTag TypedTag;
   private ClassTag UnApplyTag;
   private ClassTag ValDefTag;
   private ClassTag ValOrDefDefTag;
   private List scala$reflect$internal$AnnotationCheckers$$annotationCheckers;
   private ClassTag JavaArgumentTag;
   private volatile AnnotationInfos.UnmappableAnnotArg$ UnmappableAnnotArg$module;
   private volatile AnnotationInfos.LiteralAnnotArg$ LiteralAnnotArg$module;
   private volatile AnnotationInfos.ArrayAnnotArg$ ArrayAnnotArg$module;
   private volatile AnnotationInfos.NestedAnnotArg$ NestedAnnotArg$module;
   private volatile AnnotationInfos.AnnotationInfo$ AnnotationInfo$module;
   private volatile AnnotationInfos.Annotation$ Annotation$module;
   private ClassTag AnnotationTag;
   private volatile AnnotationInfos.UnmappableAnnotation$ UnmappableAnnotation$module;
   private volatile AnnotationInfos.ThrownException$ ThrownException$module;
   private volatile StdNames.compactify$ compactify$module;
   private StdNames.tpnme$ typeNames;
   private volatile StdNames.tpnme$ tpnme$module;
   private volatile StdNames.fulltpnme$ fulltpnme$module;
   private volatile StdNames.binarynme$ binarynme$module;
   private StdNames.JavaKeywords javanme;
   private StdNames.nme$ termNames;
   private volatile StdNames.nme$ nme$module;
   private StdNames.SymbolNames sn;
   private Transforms.Lazy scala$reflect$internal$transform$Transforms$$uncurryLazy;
   private Transforms.Lazy scala$reflect$internal$transform$Transforms$$erasureLazy;
   private Transforms.Lazy scala$reflect$internal$transform$Transforms$$postErasureLazy;
   private BaseTypeSeqs.BaseTypeSeq undetBaseTypeSeq;
   private Throwable CyclicInheritance;
   private volatile Constants.Constant$ Constant$module;
   private ClassTag ConstantTag;
   private volatile Definitions.definitions$ definitions$module;
   private int scopeCount;
   private volatile Scopes.LookupSucceeded$ LookupSucceeded$module;
   private volatile Scopes.LookupAmbiguous$ LookupAmbiguous$module;
   private volatile Scopes.LookupInaccessible$ LookupInaccessible$module;
   private volatile Scopes.LookupNotFound$ LookupNotFound$module;
   private volatile Scopes.Scope$ Scope$module;
   private ClassTag ScopeTag;
   private ClassTag MemberScopeTag;
   private volatile Scopes.EmptyScope$ EmptyScope$module;
   private ClassTag FlagSetTag;
   private long NoFlags;
   private volatile FlagSets.Flag$ Flag$module;
   private volatile Kinds.KindErrors$ KindErrors$module;
   private Kinds.KindErrors NoKindErrors;
   private volatile Kinds.Kind$ Kind$module;
   private volatile Kinds.ProperTypeKind$ ProperTypeKind$module;
   private volatile Kinds.TypeConKind$ TypeConKind$module;
   private volatile Kinds.inferKind$ inferKind$module;
   private ReusableInstance scala$reflect$internal$Variances$$varianceInTypeCache;
   private boolean scala$reflect$internal$Types$$explainSwitch;
   private Set scala$reflect$internal$Types$$emptySymbolSet;
   private boolean scala$reflect$internal$Types$$breakCycles;
   private boolean scala$reflect$internal$Types$$sharperSkolems;
   private volatile Types.substTypeMapCache$ substTypeMapCache$module;
   private int scala$reflect$internal$Types$$_skolemizationLevel;
   private WeakHashMap scala$reflect$internal$Types$$_intersectionWitness;
   private volatile Types.UnmappableTree$ UnmappableTree$module;
   private volatile Types.ErrorType$ ErrorType$module;
   private volatile Types.WildcardType$ WildcardType$module;
   private volatile Types.BoundedWildcardType$ BoundedWildcardType$module;
   private volatile Types.OverloadedArgProto$ OverloadedArgProto$module;
   private volatile Types.NoType$ NoType$module;
   private volatile Types.NoPrefix$ NoPrefix$module;
   private volatile Types.ThisType$ ThisType$module;
   private volatile Types.SingleType$ SingleType$module;
   private volatile Types.SuperType$ SuperType$module;
   private volatile Types.TypeBounds$ TypeBounds$module;
   private volatile Types.CompoundType$ CompoundType$module;
   private volatile Types.RefinedType$ RefinedType$module;
   private volatile Types.ClassInfoType$ ClassInfoType$module;
   private volatile Types.ConstantType$ ConstantType$module;
   private volatile Types.FoldableConstantType$ FoldableConstantType$module;
   private volatile Types.LiteralType$ LiteralType$module;
   private volatile Types.TypeRef$ TypeRef$module;
   private volatile Types.MethodType$ MethodType$module;
   private volatile Types.NullaryMethodType$ NullaryMethodType$module;
   private volatile Types.PolyType$ PolyType$module;
   private volatile Types.ExistentialType$ ExistentialType$module;
   private volatile Types.OverloadedType$ OverloadedType$module;
   private volatile Types.ImportType$ ImportType$module;
   private volatile Types.AntiPolyType$ AntiPolyType$module;
   private volatile Types.HasTypeMember$ HasTypeMember$module;
   private volatile Types.ArrayTypeRef$ ArrayTypeRef$module;
   private volatile Types.TypeVar$ TypeVar$module;
   private volatile Types.AnnotatedType$ AnnotatedType$module;
   private volatile Types.StaticallyAnnotatedType$ StaticallyAnnotatedType$module;
   private volatile Types.NamedType$ NamedType$module;
   private volatile Types.RepeatedType$ RepeatedType$module;
   private volatile Types.ErasedValueType$ ErasedValueType$module;
   private ReusableInstance scala$reflect$internal$Types$$copyRefinedTypeSSM;
   private volatile Types.GenPolyType$ GenPolyType$module;
   private int scala$reflect$internal$Types$$initialUniquesCapacity;
   private WeakHashSet scala$reflect$internal$Types$$uniques;
   private int scala$reflect$internal$Types$$uniqueRunId;
   private volatile Types.unwrapToClass$ unwrapToClass$module;
   private volatile Types.unwrapToStableClass$ unwrapToStableClass$module;
   private volatile Types.unwrapWrapperTypes$ unwrapWrapperTypes$module;
   private Types.MissingAliasControl missingAliasException;
   private int scala$reflect$internal$Types$$_basetypeRecursions;
   private HashSet scala$reflect$internal$Types$$_pendingBaseTypes;
   private volatile Types.RecoverableCyclicReference$ RecoverableCyclicReference$module;
   private String scala$reflect$internal$Types$$_indent;
   private Set shorthands;
   private Function1 typeContainsTypeVar;
   private Function1 typeIsSubTypeOfSerializable;
   private Function1 typeIsHigherKinded;
   private ClassTag AnnotatedTypeTag;
   private ClassTag BoundedWildcardTypeTag;
   private ClassTag ClassInfoTypeTag;
   private ClassTag CompoundTypeTag;
   private ClassTag ConstantTypeTag;
   private ClassTag ExistentialTypeTag;
   private ClassTag MethodTypeTag;
   private ClassTag NullaryMethodTypeTag;
   private ClassTag PolyTypeTag;
   private ClassTag RefinedTypeTag;
   private ClassTag SingletonTypeTag;
   private ClassTag SingleTypeTag;
   private ClassTag SuperTypeTag;
   private ClassTag ThisTypeTag;
   private ClassTag TypeBoundsTag;
   private ClassTag TypeRefTag;
   private ClassTag TypeTagg;
   private ReusableInstance findMemberInstance;
   private TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
   private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
   private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
   private volatile TypeConstraints.TypeConstraint$ TypeConstraint$module;
   private ReusableInstance scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances;
   private volatile TypeMaps.normalizeAliases$ normalizeAliases$module;
   private volatile TypeMaps.dropSingletonType$ dropSingletonType$module;
   private volatile TypeMaps.abstractTypesToBounds$ abstractTypesToBounds$module;
   private volatile TypeMaps.dropIllegalStarTypes$ dropIllegalStarTypes$module;
   private volatile TypeMaps.wildcardExtrapolation$ wildcardExtrapolation$module;
   private volatile TypeMaps.SubstSymMap$ SubstSymMap$module;
   private volatile TypeMaps.IsDependentCollector$ IsDependentCollector$module;
   private volatile TypeMaps.ApproximateDependentMap$ ApproximateDependentMap$module;
   private volatile TypeMaps.identityTypeMap$ identityTypeMap$module;
   private volatile TypeMaps.typeVarToOriginMap$ typeVarToOriginMap$module;
   private volatile TypeMaps.ErroneousCollector$ ErroneousCollector$module;
   private volatile TypeMaps.adaptToNewRunMap$ adaptToNewRunMap$module;
   private volatile TypeMaps.UnrelatableCollector$ UnrelatableCollector$module;
   private volatile TypeMaps.IsRelatableCollector$ IsRelatableCollector$module;
   private boolean scala$reflect$internal$tpe$GlbLubs$$printLubs;
   private TypeMaps.FindTypeCollector scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector;
   private HashMap scala$reflect$internal$tpe$GlbLubs$$_lubResults;
   private HashMap scala$reflect$internal$tpe$GlbLubs$$_glbResults;
   private Throwable GlbFailure;
   private int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth;
   private int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit;
   private CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
   private int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions;
   private HashSet scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects;
   private HashSet scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes;
   private volatile TypeComparers.SubTypePair$ SubTypePair$module;
   private int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions;
   private int ids;
   private Map scala$reflect$internal$Symbols$$_recursionTable;
   private int scala$reflect$internal$Symbols$$_lockedCount;
   private ArrayBuffer scala$reflect$internal$Symbols$$_lockingTrace;
   private boolean scala$reflect$internal$Symbols$$lockTracing;
   /** @deprecated */
   private int scala$reflect$internal$Symbols$$existentialIds;
   private AnyRefMap scala$reflect$internal$Symbols$$originalOwnerMap;
   private volatile Symbols.SymbolKind$ SymbolKind$module;
   private ClassTag SymbolTag;
   private ClassTag TermSymbolTag;
   private ClassTag ModuleSymbolTag;
   private ClassTag MethodSymbolTag;
   private ClassTag TypeSymbolTag;
   private ClassTag ClassSymbolTag;
   private ClassTag FreeTermSymbolTag;
   private ClassTag FreeTypeSymbolTag;
   private Symbols.NoSymbol NoSymbol;
   private ReusableInstance scala$reflect$internal$Symbols$$cloneSymbolsSubstSymMap;
   private volatile Symbols.CyclicReference$ CyclicReference$module;
   private volatile Symbols.TypeHistory$ TypeHistory$module;
   private Symbols.TypeHistory scala$reflect$internal$Symbols$$noTypeHistory;
   private Function1 symbolIsPossibleInRefinement;
   private volatile Symbols.SymbolOps$ SymbolOps$module;
   private Symbols.SymbolOps AllOps;
   private Object scala$reflect$internal$Names$$nameLock;
   private char[] scala$reflect$internal$Names$$_chrs;
   private int scala$reflect$internal$Names$$nc;
   private Names.TermName[] scala$reflect$internal$Names$$termHashtable;
   private Names.TypeName[] scala$reflect$internal$Names$$typeHashtable;
   private ClassTag NameTag;
   private ClassTag TermNameTag;
   private volatile Names.TermName$ TermName$module;
   private ClassTag TypeNameTag;
   private volatile Names.TypeName$ TypeName$module;
   private Tuple2 scala$reflect$internal$util$Collections$$TupleOfNil;
   private volatile int bitmap$0;

   public String supplementTyperState(final String errorMessage) {
      return Reporting.supplementTyperState$(this, errorMessage);
   }

   public String supplementErrorMessage(final String errorMessage) {
      return Reporting.supplementErrorMessage$(this, errorMessage);
   }

   public void inform(final String msg) {
      Reporting.inform$(this, msg);
   }

   /** @deprecated */
   public void warning(final String msg) {
      Reporting.warning$(this, msg);
   }

   public void globalError(final String msg) {
      Reporting.globalError$(this, msg);
   }

   public Nothing abort(final String msg) {
      return Reporting.abort$(this, msg);
   }

   public void inform(final Position pos, final String msg) {
      Reporting.inform$(this, pos, msg);
   }

   /** @deprecated */
   public void warning(final Position pos, final String msg) {
      Reporting.warning$(this, pos, msg);
   }

   public void globalError(final Position pos, final String msg) {
      Reporting.globalError$(this, pos, msg);
   }

   public Names.TermName freshTermName(final String prefix, final FreshNameCreator creator) {
      return FreshNames.freshTermName$(this, prefix, creator);
   }

   public String freshTermName$default$1() {
      return FreshNames.freshTermName$default$1$(this);
   }

   public Names.TypeName freshTypeName(final String prefix, final FreshNameCreator creator) {
      return FreshNames.freshTypeName$(this, prefix, creator);
   }

   public boolean isTreeSymbolPickled(final int code) {
      return Translations.isTreeSymbolPickled$(this, code);
   }

   public boolean isTreeSymbolPickled(final Trees.Tree tree) {
      return Translations.isTreeSymbolPickled$(this, tree);
   }

   public final int picklerTag(final Object ref) {
      return Translations.picklerTag$(this, (Object)ref);
   }

   public int picklerTag(final Symbols.Symbol sym) {
      return Translations.picklerTag$(this, (Symbols.Symbol)sym);
   }

   public final int picklerTag(final Types.Type tpe) {
      return Translations.picklerTag$(this, (Types.Type)tpe);
   }

   public int picklerSubTag(final Trees.Tree tree) {
      return Translations.picklerSubTag$(this, tree);
   }

   public void propagatePackageBoundary(final Class c, final Seq syms) {
      PrivateWithin.propagatePackageBoundary$(this, (Class)c, syms);
   }

   public void propagatePackageBoundary(final Member m, final Seq syms) {
      PrivateWithin.propagatePackageBoundary$(this, (Member)m, syms);
   }

   public void propagatePackageBoundary(final int jflags, final Seq syms) {
      PrivateWithin.propagatePackageBoundary$(this, jflags, syms);
   }

   public Symbols.Symbol setPackageAccessBoundary(final Symbols.Symbol sym) {
      return PrivateWithin.setPackageAccessBoundary$(this, sym);
   }

   public boolean explicitlyUnit(final Trees.Tree tree) {
      return StdAttachments.explicitlyUnit$(this, tree);
   }

   public void captureVariable(final Symbols.Symbol vble) {
      CapturedVariables.captureVariable$(this, vble);
   }

   public Trees.Tree referenceCapturedVariable(final Symbols.Symbol vble) {
      return CapturedVariables.referenceCapturedVariable$(this, vble);
   }

   public Types.Type capturedVariableType(final Symbols.Symbol vble) {
      return CapturedVariables.capturedVariableType$(this, vble);
   }

   public Types.Type capturedVariableType(final Symbols.Symbol vble, final Types.Type tpe, final boolean erasedTypes) {
      return CapturedVariables.capturedVariableType$(this, vble, tpe, erasedTypes);
   }

   public Types.Type capturedVariableType$default$2() {
      return CapturedVariables.capturedVariableType$default$2$(this);
   }

   public boolean capturedVariableType$default$3() {
      return CapturedVariables.capturedVariableType$default$3$(this);
   }

   public scala.reflect.api.Internals.Importer mkImporter(final scala.reflect.api.Universe from0) {
      return Importers.mkImporter$(this, from0);
   }

   public String paramString(final Types.Type tp) {
      return TypeDebugging.paramString$(this, tp);
   }

   public String typeParamsString(final Types.Type tp) {
      return TypeDebugging.typeParamsString$(this, tp);
   }

   public String debugString(final Types.Type tp) {
      return TypeDebugging.debugString$(this, tp);
   }

   public boolean useOffsetPositions() {
      return Positions.useOffsetPositions$(this);
   }

   public Position wrappingPos(final Position default, final List trees) {
      return Positions.wrappingPos$(this, default, trees);
   }

   public Position wrappingPos(final List trees) {
      return Positions.wrappingPos$(this, trees);
   }

   public void ensureNonOverlapping(final Trees.Tree tree, final List others) {
      Positions.ensureNonOverlapping$(this, tree, others);
   }

   public void ensureNonOverlapping(final Trees.Tree tree, final List others, final boolean focus) {
      Positions.ensureNonOverlapping$(this, tree, others, focus);
   }

   public Position rangePos(final SourceFile source, final int start, final int point, final int end) {
      return Positions.rangePos$(this, source, start, point, end);
   }

   public void validatePositions(final Trees.Tree tree) {
      Positions.validatePositions$(this, tree);
   }

   public Trees.Tree atPos(final Position pos, final Trees.Tree tree) {
      return Positions.atPos$(this, pos, tree);
   }

   public String quotedName(final Names.Name name, final boolean decode) {
      return Printers.quotedName$(this, name, decode);
   }

   public String quotedName(final Names.Name name) {
      return Printers.quotedName$(this, (Names.Name)name);
   }

   public String quotedName(final String name) {
      return Printers.quotedName$(this, (String)name);
   }

   public String decodedSymName(final Trees.Tree tree, final Names.Name name) {
      return Printers.decodedSymName$(this, tree, name);
   }

   public String symName(final Trees.Tree tree, final Names.Name name) {
      return Printers.symName$(this, tree, name);
   }

   public String backquotedPath(final Trees.Tree t) {
      return Printers.backquotedPath$(this, t);
   }

   public void xprintTree(final Printers.TreePrinter treePrinter, final Trees.Tree tree) {
      Printers.xprintTree$(this, treePrinter, tree);
   }

   public Printers.TreePrinter newCodePrinter(final PrintWriter writer, final Trees.Tree tree, final boolean printRootPkg) {
      return Printers.newCodePrinter$(this, writer, tree, printRootPkg);
   }

   public Printers.TreePrinter newTreePrinter(final PrintWriter writer) {
      return Printers.newTreePrinter$(this, (PrintWriter)writer);
   }

   public Printers.TreePrinter newTreePrinter(final OutputStream stream) {
      return Printers.newTreePrinter$(this, (OutputStream)stream);
   }

   public Printers.TreePrinter newTreePrinter() {
      return Printers.newTreePrinter$(this);
   }

   public Printers.RawTreePrinter newRawTreePrinter(final PrintWriter writer) {
      return Printers.newRawTreePrinter$(this, writer);
   }

   public String show(final Names.Name name) {
      return Printers.show$(this, (Names.Name)name);
   }

   public String show(final long flags) {
      return Printers.show$(this, flags);
   }

   public String show(final Position position) {
      return Printers.show$(this, (Position)position);
   }

   public String showDecl(final Symbols.Symbol sym) {
      return Printers.showDecl$(this, sym);
   }

   public String treeLine(final Trees.Tree t) {
      return Trees.treeLine$(this, t);
   }

   public String treeStatus(final Trees.Tree t, final Trees.Tree enclosingTree) {
      return Trees.treeStatus$(this, t, enclosingTree);
   }

   public Trees.Tree treeStatus$default$2() {
      return Trees.treeStatus$default$2$(this);
   }

   public String treeSymStatus(final Trees.Tree t) {
      return Trees.treeSymStatus$(this, t);
   }

   public Trees.Apply ApplyConstructor(final Trees.Tree tpt, final List args) {
      return Trees.ApplyConstructor$(this, tpt, args);
   }

   public Trees.Apply NewFromConstructor(final Symbols.Symbol constructor, final Seq args) {
      return Trees.NewFromConstructor$(this, constructor, args);
   }

   public Trees.TypeTree TypeTree(final Types.Type tp) {
      return Trees.TypeTree$(this, tp);
   }

   public Trees.TypeBoundsTree TypeBoundsTree(final Types.TypeBounds bounds) {
      return Trees.TypeBoundsTree$(this, (Types.TypeBounds)bounds);
   }

   public Trees.TypeBoundsTree TypeBoundsTree(final Symbols.Symbol sym) {
      return Trees.TypeBoundsTree$(this, (Symbols.Symbol)sym);
   }

   public Trees.Template Template(final Symbols.Symbol sym, final List body) {
      return Trees.Template$(this, sym, body);
   }

   public Trees.ValDef newValDef(final Symbols.Symbol sym, final Trees.Tree rhs, final Trees.Modifiers mods, final Names.TermName name, final Trees.Tree tpt) {
      return Trees.newValDef$(this, sym, rhs, mods, name, tpt);
   }

   public Trees.Modifiers newValDef$default$3(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newValDef$default$3$(this, sym, rhs);
   }

   public Names.TermName newValDef$default$4(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newValDef$default$4$(this, sym, rhs);
   }

   public Trees.Tree newValDef$default$5(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newValDef$default$5$(this, sym, rhs);
   }

   public Trees.DefDef newDefDef(final Symbols.Symbol sym, final Trees.Tree rhs, final Trees.Modifiers mods, final Names.TermName name, final List tparams, final List vparamss, final Trees.Tree tpt) {
      return Trees.newDefDef$(this, sym, rhs, mods, name, tparams, vparamss, tpt);
   }

   public Trees.Modifiers newDefDef$default$3(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDef$default$3$(this, sym, rhs);
   }

   public Names.TermName newDefDef$default$4(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDef$default$4$(this, sym, rhs);
   }

   public List newDefDef$default$5(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDef$default$5$(this, sym, rhs);
   }

   public List newDefDef$default$6(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDef$default$6$(this, sym, rhs);
   }

   public Trees.Tree newDefDef$default$7(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDef$default$7$(this, sym, rhs);
   }

   public Trees.DefDef newDefDefAt(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs, final Trees.Modifiers mods, final Names.TermName name, final List tparams, final List vparamss, final Trees.Tree tpt) {
      return Trees.newDefDefAt$(this, pos, sym, rhs, mods, name, tparams, vparamss, tpt);
   }

   public Trees.Modifiers newDefDefAt$default$4(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDefAt$default$4$(this, pos, sym, rhs);
   }

   public Names.TermName newDefDefAt$default$5(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDefAt$default$5$(this, pos, sym, rhs);
   }

   public List newDefDefAt$default$6(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDefAt$default$6$(this, pos, sym, rhs);
   }

   public List newDefDefAt$default$7(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDefAt$default$7$(this, pos, sym, rhs);
   }

   public Trees.Tree newDefDefAt$default$8(final Position pos, final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newDefDefAt$default$8$(this, pos, sym, rhs);
   }

   public Trees.TypeDef newTypeDef(final Symbols.Symbol sym, final Trees.Tree rhs, final Trees.Modifiers mods, final Names.TypeName name, final List tparams) {
      return Trees.newTypeDef$(this, sym, rhs, mods, name, tparams);
   }

   public Trees.Modifiers newTypeDef$default$3(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newTypeDef$default$3$(this, sym, rhs);
   }

   public Names.TypeName newTypeDef$default$4(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newTypeDef$default$4$(this, sym, rhs);
   }

   public List newTypeDef$default$5(final Symbols.Symbol sym, final Trees.Tree rhs) {
      return Trees.newTypeDef$default$5$(this, sym, rhs);
   }

   public Trees.CaseDef CaseDef(final Trees.Tree pat, final Trees.Tree body) {
      return Trees.CaseDef$(this, pat, body);
   }

   public Trees.Bind Bind(final Symbols.Symbol sym, final Trees.Tree body) {
      return Trees.Bind$(this, sym, body);
   }

   public Trees.Try Try(final Trees.Tree body, final Seq cases) {
      return Trees.Try$(this, body, cases);
   }

   public Trees.Throw Throw(final Types.Type tpe, final Seq args) {
      return Trees.Throw$(this, tpe, args);
   }

   public Trees.Tree Apply(final Symbols.Symbol sym, final Seq args) {
      return Trees.Apply$(this, sym, args);
   }

   public Trees.Tree New(final Trees.Tree tpt, final List argss) {
      return Trees.New$(this, (Trees.Tree)tpt, (List)argss);
   }

   public Trees.Tree New(final Types.Type tpe, final Seq args) {
      return Trees.New$(this, (Types.Type)tpe, (Seq)args);
   }

   public Trees.Tree New(final Types.Type tpe, final List argss) {
      return Trees.New$(this, (Types.Type)tpe, (List)argss);
   }

   public Trees.Tree New(final Symbols.Symbol sym, final Seq args) {
      return Trees.New$(this, (Symbols.Symbol)sym, (Seq)args);
   }

   public Trees.Tree Super(final Symbols.Symbol sym, final Names.TypeName mix) {
      return Trees.Super$(this, sym, mix);
   }

   public Trees.Tree SuperSelect(final Symbols.Symbol clazz, final Symbols.Symbol sym) {
      return Trees.SuperSelect$(this, clazz, sym);
   }

   public Trees.Tree This(final Symbols.Symbol sym) {
      return Trees.This$(this, sym);
   }

   public Trees.Select Select(final Trees.Tree qualifier, final String name) {
      return Trees.Select$(this, qualifier, (String)name);
   }

   public Trees.Select Select(final Trees.Tree qualifier, final Symbols.Symbol sym) {
      return Trees.Select$(this, qualifier, (Symbols.Symbol)sym);
   }

   public Trees.Ident Ident(final String name) {
      return Trees.Ident$(this, (String)name);
   }

   public Trees.Ident Ident(final Symbols.Symbol sym) {
      return Trees.Ident$(this, (Symbols.Symbol)sym);
   }

   public Trees.Block Block(final Seq stats) {
      return Trees.Block$(this, stats);
   }

   public Symbols.Symbol typeTreeSymbol(final Trees.TypeTree tree) {
      return Trees.typeTreeSymbol$(this, tree);
   }

   /** @deprecated */
   public void itraverse(final scala.reflect.api.Trees.Traverser traverser, final Trees.Tree tree) {
      Trees.itraverse$(this, traverser, tree);
   }

   /** @deprecated */
   public Trees.Tree itransform(final scala.reflect.api.Trees.Transformer transformer, final Trees.Tree tree) {
      return Trees.itransform$(this, transformer, tree);
   }

   public void changeNonLocalOwners(final Trees.Tree tree, final Symbols.Symbol newowner) {
      Trees.changeNonLocalOwners$(this, tree, newowner);
   }

   public final Trees.Tree focusInPlace(final Trees.Tree t) {
      return Trees.focusInPlace$(this, t);
   }

   public Trees.Tree duplicateAndKeepPositions(final Trees.Tree tree) {
      return Trees.duplicateAndKeepPositions$(this, tree);
   }

   public Trees.Tree wrappingIntoTerm(final Trees.Tree tree0, final Function1 op) {
      return Trees.wrappingIntoTerm$(this, tree0, op);
   }

   public Trees.DefDef copyDefDef(final Trees.Tree tree, final Trees.Modifiers mods, final Names.Name name, final List tparams, final List vparamss, final Trees.Tree tpt, final Trees.Tree rhs) {
      return Trees.copyDefDef$(this, tree, mods, name, tparams, vparamss, tpt, rhs);
   }

   public Trees.Modifiers copyDefDef$default$2(final Trees.Tree tree) {
      return Trees.copyDefDef$default$2$(this, tree);
   }

   public Names.Name copyDefDef$default$3(final Trees.Tree tree) {
      return Trees.copyDefDef$default$3$(this, tree);
   }

   public List copyDefDef$default$4(final Trees.Tree tree) {
      return Trees.copyDefDef$default$4$(this, tree);
   }

   public List copyDefDef$default$5(final Trees.Tree tree) {
      return Trees.copyDefDef$default$5$(this, tree);
   }

   public Trees.Tree copyDefDef$default$6(final Trees.Tree tree) {
      return Trees.copyDefDef$default$6$(this, tree);
   }

   public Trees.Tree copyDefDef$default$7(final Trees.Tree tree) {
      return Trees.copyDefDef$default$7$(this, tree);
   }

   public Trees.ValDef copyValDef(final Trees.Tree tree, final Trees.Modifiers mods, final Names.Name name, final Trees.Tree tpt, final Trees.Tree rhs) {
      return Trees.copyValDef$(this, tree, mods, name, tpt, rhs);
   }

   public Trees.Modifiers copyValDef$default$2(final Trees.Tree tree) {
      return Trees.copyValDef$default$2$(this, tree);
   }

   public Names.Name copyValDef$default$3(final Trees.Tree tree) {
      return Trees.copyValDef$default$3$(this, tree);
   }

   public Trees.Tree copyValDef$default$4(final Trees.Tree tree) {
      return Trees.copyValDef$default$4$(this, tree);
   }

   public Trees.Tree copyValDef$default$5(final Trees.Tree tree) {
      return Trees.copyValDef$default$5$(this, tree);
   }

   public Trees.TypeDef copyTypeDef(final Trees.Tree tree, final Trees.Modifiers mods, final Names.Name name, final List tparams, final Trees.Tree rhs) {
      return Trees.copyTypeDef$(this, tree, mods, name, tparams, rhs);
   }

   public Trees.Modifiers copyTypeDef$default$2(final Trees.Tree tree) {
      return Trees.copyTypeDef$default$2$(this, tree);
   }

   public Names.Name copyTypeDef$default$3(final Trees.Tree tree) {
      return Trees.copyTypeDef$default$3$(this, tree);
   }

   public List copyTypeDef$default$4(final Trees.Tree tree) {
      return Trees.copyTypeDef$default$4$(this, tree);
   }

   public Trees.Tree copyTypeDef$default$5(final Trees.Tree tree) {
      return Trees.copyTypeDef$default$5$(this, tree);
   }

   public Trees.ClassDef copyClassDef(final Trees.Tree tree, final Trees.Modifiers mods, final Names.Name name, final List tparams, final Trees.Template impl) {
      return Trees.copyClassDef$(this, tree, mods, name, tparams, impl);
   }

   public Trees.Modifiers copyClassDef$default$2(final Trees.Tree tree) {
      return Trees.copyClassDef$default$2$(this, tree);
   }

   public Names.Name copyClassDef$default$3(final Trees.Tree tree) {
      return Trees.copyClassDef$default$3$(this, tree);
   }

   public List copyClassDef$default$4(final Trees.Tree tree) {
      return Trees.copyClassDef$default$4$(this, tree);
   }

   public Trees.Template copyClassDef$default$5(final Trees.Tree tree) {
      return Trees.copyClassDef$default$5$(this, tree);
   }

   public Trees.ModuleDef copyModuleDef(final Trees.Tree tree, final Trees.Modifiers mods, final Names.Name name, final Trees.Template impl) {
      return Trees.copyModuleDef$(this, tree, mods, name, impl);
   }

   public Trees.Modifiers copyModuleDef$default$2(final Trees.Tree tree) {
      return Trees.copyModuleDef$default$2$(this, tree);
   }

   public Names.Name copyModuleDef$default$3(final Trees.Tree tree) {
      return Trees.copyModuleDef$default$3$(this, tree);
   }

   public Trees.Template copyModuleDef$default$4(final Trees.Tree tree) {
      return Trees.copyModuleDef$default$4$(this, tree);
   }

   public Trees.DefDef deriveDefDef(final Trees.Tree ddef, final Function1 applyToRhs) {
      return Trees.deriveDefDef$(this, ddef, applyToRhs);
   }

   public Trees.ValDef deriveValDef(final Trees.Tree vdef, final Function1 applyToRhs) {
      return Trees.deriveValDef$(this, vdef, applyToRhs);
   }

   public Trees.Template deriveTemplate(final Trees.Tree templ, final Function1 applyToBody) {
      return Trees.deriveTemplate$(this, templ, applyToBody);
   }

   public Trees.ClassDef deriveClassDef(final Trees.Tree cdef, final Function1 applyToImpl) {
      return Trees.deriveClassDef$(this, cdef, applyToImpl);
   }

   public Trees.ModuleDef deriveModuleDef(final Trees.Tree mdef, final Function1 applyToImpl) {
      return Trees.deriveModuleDef$(this, mdef, applyToImpl);
   }

   public Trees.CaseDef deriveCaseDef(final Trees.Tree cdef, final Function1 applyToBody) {
      return Trees.deriveCaseDef$(this, cdef, applyToBody);
   }

   public Trees.LabelDef deriveLabelDef(final Trees.Tree ldef, final Function1 applyToRhs) {
      return Trees.deriveLabelDef$(this, ldef, applyToRhs);
   }

   public Trees.Function deriveFunction(final Trees.Tree func, final Function1 applyToRhs) {
      return Trees.deriveFunction$(this, func, applyToRhs);
   }

   public void addAnnotationChecker(final AnnotationCheckers.AnnotationChecker checker) {
      AnnotationCheckers.addAnnotationChecker$(this, checker);
   }

   public void removeAllAnnotationCheckers() {
      AnnotationCheckers.removeAllAnnotationCheckers$(this);
   }

   public boolean annotationsConform(final Types.Type tp1, final Types.Type tp2) {
      return AnnotationCheckers.annotationsConform$(this, tp1, tp2);
   }

   public Types.Type annotationsLub(final Types.Type tpe, final List ts) {
      return AnnotationCheckers.annotationsLub$(this, tpe, ts);
   }

   public Types.Type annotationsGlb(final Types.Type tpe, final List ts) {
      return AnnotationCheckers.annotationsGlb$(this, tpe, ts);
   }

   public List adaptBoundsToAnnotations(final List bounds, final List tparams, final List targs) {
      return AnnotationCheckers.adaptBoundsToAnnotations$(this, bounds, tparams, targs);
   }

   public Types.Type addAnnotations(final Trees.Tree tree, final Types.Type tpe) {
      return AnnotationCheckers.addAnnotations$(this, tree, tpe);
   }

   public boolean canAdaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
      return AnnotationCheckers.canAdaptAnnotations$(this, tree, mode, pt);
   }

   public Trees.Tree adaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
      return AnnotationCheckers.adaptAnnotations$(this, tree, mode, pt);
   }

   public Types.Type adaptTypeOfReturn(final Trees.Tree tree, final Types.Type pt, final Function0 default) {
      return AnnotationCheckers.adaptTypeOfReturn$(this, tree, pt, default);
   }

   public String completeAnnotationToString(final AnnotationInfos.AnnotationInfo annInfo) {
      return AnnotationInfos.completeAnnotationToString$(this, annInfo);
   }

   public Trees.Tree annotationToTree(final AnnotationInfos.AnnotationInfo ann) {
      return AnnotationInfos.annotationToTree$(this, ann);
   }

   public AnnotationInfos.AnnotationInfo treeToAnnotation(final Trees.Tree tree) {
      return AnnotationInfos.treeToAnnotation$(this, tree);
   }

   public Names.TermName encode(final String str) {
      return StdNames.encode$(this, str);
   }

   public String compactifyName(final String orig) {
      return StdNames.compactifyName$(this, orig);
   }

   public UnCurry uncurry() {
      return Transforms.uncurry$(this);
   }

   public Erasure erasure() {
      return Transforms.erasure$(this);
   }

   public PostErasure postErasure() {
      return Transforms.postErasure$(this);
   }

   public Types.Type transformedType(final Symbols.Symbol sym) {
      return Transforms.transformedType$(this, (Symbols.Symbol)sym);
   }

   public Types.Type transformedType(final Types.Type tpe) {
      return Transforms.transformedType$(this, (Types.Type)tpe);
   }

   public BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(final List parents, final Types.Type[] elems) {
      return BaseTypeSeqs.newBaseTypeSeq$(this, parents, elems);
   }

   public BaseTypeSeqs.MappedBaseTypeSeq newMappedBaseTypeSeq(final BaseTypeSeqs.BaseTypeSeq orig, final Function1 f) {
      return BaseTypeSeqs.newMappedBaseTypeSeq$(this, orig, f);
   }

   public BaseTypeSeqs.BaseTypeSeq baseTypeSingletonSeq(final Types.Type tp) {
      return BaseTypeSeqs.baseTypeSingletonSeq$(this, tp);
   }

   public BaseTypeSeqs.BaseTypeSeq compoundBaseTypeSeq(final Types.Type tp) {
      return BaseTypeSeqs.compoundBaseTypeSeq$(this, tp);
   }

   public Scopes.Scope newScope() {
      return Scopes.newScope$(this);
   }

   public Scopes.Scope newFindMemberScope() {
      return Scopes.newFindMemberScope$(this);
   }

   public final Scopes.Scope newNestedScope(final Scopes.Scope outer) {
      return Scopes.newNestedScope$(this, outer);
   }

   public Scopes.Scope newScopeWith(final Seq elems) {
      return Scopes.newScopeWith$(this, elems);
   }

   public Scopes.Scope newPackageScope(final Symbols.Symbol pkgClass) {
      return Scopes.newPackageScope$(this, pkgClass);
   }

   public Scopes.Scope scopeTransform(final Symbols.Symbol owner, final Function0 op) {
      return Scopes.scopeTransform$(this, owner, op);
   }

   public scala.reflect.api.FlagSets.FlagOps addFlagOps(final long left) {
      return FlagSets.addFlagOps$(this, left);
   }

   public List deriveFreshSkolems(final List tparams) {
      return ExistentialsAndSkolems.deriveFreshSkolems$(this, tparams);
   }

   public boolean isRawParameter(final Symbols.Symbol sym) {
      return ExistentialsAndSkolems.isRawParameter$(this, sym);
   }

   public final Object existentialTransform(final List rawSyms, final Types.Type tp, final Symbols.Symbol rawOwner, final Function2 creator) {
      return ExistentialsAndSkolems.existentialTransform$(this, rawSyms, tp, rawOwner, creator);
   }

   public final Symbols.Symbol existentialTransform$default$3() {
      return ExistentialsAndSkolems.existentialTransform$default$3$(this);
   }

   public final Types.Type packSymbols(final List hidden, final Types.Type tp, final Symbols.Symbol rawOwner) {
      return ExistentialsAndSkolems.packSymbols$(this, hidden, tp, rawOwner);
   }

   public final Symbols.Symbol packSymbols$default$3() {
      return ExistentialsAndSkolems.packSymbols$default$3$(this);
   }

   public boolean kindsConform(final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner) {
      return Kinds.kindsConform$(this, tparams, targs, pre, owner);
   }

   public List checkKindBounds0(final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner, final boolean explainErrors) {
      return Kinds.checkKindBounds0$(this, tparams, targs, pre, owner, explainErrors);
   }

   public final int varianceInTypes(final List tps, final Symbols.Symbol tparam) {
      return Variances.varianceInTypes$(this, tps, tparam);
   }

   public final int varianceInType(final Types.Type tp, final boolean considerUnchecked, final Symbols.Symbol tparam) {
      return Variances.varianceInType$(this, tp, considerUnchecked, tparam);
   }

   public final boolean varianceInType$default$2() {
      return Variances.varianceInType$default$2$(this);
   }

   public int skolemizationLevel() {
      return Types.skolemizationLevel$(this);
   }

   public void skolemizationLevel_$eq(final int value) {
      Types.skolemizationLevel_$eq$(this, value);
   }

   public WeakHashMap intersectionWitness() {
      return Types.intersectionWitness$(this);
   }

   public void defineUnderlyingOfSingleType(final Types.SingleType tpe) {
      Types.defineUnderlyingOfSingleType$(this, tpe);
   }

   public List computeBaseClasses(final Types.Type tpe) {
      return Types.computeBaseClasses$(this, tpe);
   }

   public void defineBaseTypeSeqOfCompoundType(final Types.CompoundType tpe) {
      Types.defineBaseTypeSeqOfCompoundType$(this, tpe);
   }

   public void defineBaseClassesOfCompoundType(final Types.CompoundType tpe) {
      Types.defineBaseClassesOfCompoundType$(this, tpe);
   }

   public void validateClassInfo(final Types.ClassInfoType tp) {
      Types.validateClassInfo$(this, tp);
   }

   public void defineNormalized(final Types.TypeRef tr) {
      Types.defineNormalized$(this, tr);
   }

   public void defineParentsOfTypeRef(final Types.TypeRef tpe) {
      Types.defineParentsOfTypeRef$(this, tpe);
   }

   public void defineBaseTypeSeqOfTypeRef(final Types.TypeRef tpe) {
      Types.defineBaseTypeSeqOfTypeRef$(this, tpe);
   }

   public final Types.Type newExistentialType(final List quantified, final Types.Type underlying) {
      return Types.newExistentialType$(this, quantified, underlying);
   }

   public Types.Type overloadedType(final Types.Type pre, final List alternatives) {
      return Types.overloadedType$(this, pre, alternatives);
   }

   public Types.Type annotatedType(final List annots, final Types.Type underlying) {
      return Types.annotatedType$(this, annots, underlying);
   }

   public Types.Type singleType(final Types.Type pre, final Symbols.Symbol sym) {
      return Types.singleType$(this, pre, sym);
   }

   public Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls, final Position pos) {
      return Types.refinedType$(this, parents, owner, decls, pos);
   }

   public Types.Type refinedType(final List parents, final Symbols.Symbol owner) {
      return Types.refinedType$(this, parents, owner);
   }

   public Types.Type copyRefinedType(final Types.RefinedType original, final List parents, final Scopes.Scope decls, final Symbols.Symbol owner) {
      return Types.copyRefinedType$(this, original, parents, decls, owner);
   }

   public Symbols.Symbol copyRefinedType$default$4() {
      return Types.copyRefinedType$default$4$(this);
   }

   public final Types.Type typeRef(final Types.Type pre, final Symbols.Symbol sym, final List args) {
      return Types.typeRef$(this, pre, sym, args);
   }

   public Types.Type copyTypeRef(final Types.Type tp, final Types.Type pre, final Symbols.Symbol sym, final List args) {
      return Types.copyTypeRef$(this, tp, pre, sym, args);
   }

   public Types.Type copyMethodType(final Types.Type tp, final List params, final Types.Type restpe) {
      return Types.copyMethodType$(this, tp, params, restpe);
   }

   public Types.Type intersectionType(final List tps, final Symbols.Symbol owner) {
      return Types.intersectionType$(this, tps, owner);
   }

   public Types.Type intersectionType(final List tps) {
      return Types.intersectionType$(this, tps);
   }

   public Types.Type intersectionTypeForLazyBaseType(final List tps) {
      return Types.intersectionTypeForLazyBaseType$(this, tps);
   }

   public boolean isIntersectionTypeForLazyBaseType(final Types.RefinedType tp) {
      return Types.isIntersectionTypeForLazyBaseType$(this, tp);
   }

   public Types.Type appliedType(final Types.Type tycon, final List args) {
      return Types.appliedType$(this, (Types.Type)tycon, (List)args);
   }

   public Types.Type appliedType(final Types.Type tycon, final Seq args) {
      return Types.appliedType$(this, (Types.Type)tycon, (Seq)args);
   }

   public Types.Type appliedType(final Symbols.Symbol tyconSym, final List args) {
      return Types.appliedType$(this, (Symbols.Symbol)tyconSym, (List)args);
   }

   public Types.Type appliedType(final Symbols.Symbol tyconSym, final Seq args) {
      return Types.appliedType$(this, (Symbols.Symbol)tyconSym, (Seq)args);
   }

   public Types.Type genPolyType(final List params, final Types.Type tpe) {
      return Types.genPolyType$(this, params, tpe);
   }

   /** @deprecated */
   public Types.Type polyType(final List params, final Types.Type tpe) {
      return Types.polyType$(this, params, tpe);
   }

   public Types.Type typeFun(final List tps, final Types.Type body) {
      return Types.typeFun$(this, tps, body);
   }

   public Types.Type extensionMethInfo(final Symbols.Symbol currentOwner, final Symbols.Symbol extensionMeth, final Types.Type origInfo, final Symbols.Symbol clazz) {
      return Types.extensionMethInfo$(this, currentOwner, extensionMeth, origInfo, clazz);
   }

   public Types.Type existentialAbstraction(final List tparams, final Types.Type tpe0, final boolean flipVariance) {
      return Types.existentialAbstraction$(this, tparams, tpe0, flipVariance);
   }

   public boolean existentialAbstraction$default$3() {
      return Types.existentialAbstraction$default$3$(this);
   }

   public final int howManyUniqueTypes() {
      return Types.howManyUniqueTypes$(this);
   }

   public Types.Type unique(final Types.Type tp) {
      return Types.unique$(this, tp);
   }

   public Types.Type elementExtract(final Symbols.Symbol container, final Types.Type tp) {
      return Types.elementExtract$(this, container, tp);
   }

   public Option elementExtractOption(final Symbols.Symbol container, final Types.Type tp) {
      return Types.elementExtractOption$(this, container, tp);
   }

   public boolean elementTest(final Symbols.Symbol container, final Types.Type tp, final Function1 f) {
      return Types.elementTest$(this, container, tp, f);
   }

   public Types.Type elementTransform(final Symbols.Symbol container, final Types.Type tp, final Function1 f) {
      return Types.elementTransform$(this, container, tp, f);
   }

   public Types.Type transparentShallowTransform(final Symbols.Symbol container, final Types.Type tp, final Function1 f) {
      return Types.transparentShallowTransform$(this, container, tp, f);
   }

   public Types.Type repackExistential(final Types.Type tp) {
      return Types.repackExistential$(this, tp);
   }

   public boolean containsExistential(final Types.Type tpe) {
      return Types.containsExistential$(this, tpe);
   }

   public List existentialsInType(final Types.Type tpe) {
      return Types.existentialsInType$(this, tpe);
   }

   public List typeParamsToExistentials(final Symbols.Symbol clazz, final List tparams) {
      return Types.typeParamsToExistentials$(this, clazz, tparams);
   }

   public List typeParamsToExistentials(final Symbols.Symbol clazz) {
      return Types.typeParamsToExistentials$(this, clazz);
   }

   public boolean isRawIfWithoutArgs(final Symbols.Symbol sym) {
      return Types.isRawIfWithoutArgs$(this, sym);
   }

   public boolean isRawType(final Types.Type tp) {
      return Types.isRawType$(this, tp);
   }

   /** @deprecated */
   public boolean isRaw(final Symbols.Symbol sym, final List args) {
      return Types.isRaw$(this, sym, args);
   }

   public Types.TypeBounds singletonBounds(final Types.Type hi) {
      return Types.singletonBounds$(this, hi);
   }

   public Types.Type nestedMemberType(final Symbols.Symbol sym, final Types.Type pre, final Symbols.Symbol owner) {
      return Types.nestedMemberType$(this, sym, pre, owner);
   }

   public int lubDepth(final List ts) {
      return Types.lubDepth$(this, ts);
   }

   public boolean isPopulated(final Types.Type tp1, final Types.Type tp2) {
      return Types.isPopulated$(this, tp1, tp2);
   }

   public final Types.Type normalizePlus(final Types.Type tp) {
      return Types.normalizePlus$(this, tp);
   }

   public boolean isSameTypes(final List tps1, final List tps2) {
      return Types.isSameTypes$(this, tps1, tps2);
   }

   public boolean isSameSymbolTypes(final List syms1, final List syms2) {
      return Types.isSameSymbolTypes$(this, syms1, syms2);
   }

   public int basetypeRecursions() {
      return Types.basetypeRecursions$(this);
   }

   public void basetypeRecursions_$eq(final int value) {
      Types.basetypeRecursions_$eq$(this, value);
   }

   public HashSet pendingBaseTypes() {
      return Types.pendingBaseTypes$(this);
   }

   public final boolean isEligibleForPrefixUnification(final Types.Type tp) {
      return Types.isEligibleForPrefixUnification$(this, tp);
   }

   public boolean isErrorOrWildcard(final Types.Type tp) {
      return Types.isErrorOrWildcard$(this, tp);
   }

   public boolean isSingleType(final Types.Type tp) {
      return Types.isSingleType$(this, tp);
   }

   public boolean isConstantType(final Types.Type tp) {
      return Types.isConstantType$(this, tp);
   }

   public final boolean isExistentialType(final Types.Type tp) {
      return Types.isExistentialType$(this, tp);
   }

   public boolean isImplicitMethodType(final Types.Type tp) {
      return Types.isImplicitMethodType$(this, tp);
   }

   public boolean isUseableAsTypeArg(final Types.Type tp) {
      return Types.isUseableAsTypeArg$(this, tp);
   }

   public final boolean isUseableAsTypeArgs(final List tps) {
      return Types.isUseableAsTypeArgs$(this, tps);
   }

   public boolean isNonRefinementClassType(final Types.Type tpe) {
      return Types.isNonRefinementClassType$(this, tpe);
   }

   public boolean isSubArgs(final List tps1, final List tps2, final List tparams, final int depth) {
      return Types.isSubArgs$(this, tps1, tps2, tparams, depth);
   }

   public boolean specializesSym(final Types.Type tp, final Symbols.Symbol sym, final int depth) {
      return Types.specializesSym$(this, tp, sym, depth);
   }

   public boolean specializesSym(final Types.Type preLo, final Symbols.Symbol symLo, final Types.Type preHi, final Symbols.Symbol symHi, final int depth) {
      return Types.specializesSym$(this, preLo, symLo, preHi, symHi, depth);
   }

   public final boolean matchesType(final Types.Type tp1, final Types.Type tp2, final boolean alwaysMatchSimple) {
      return Types.matchesType$(this, tp1, tp2, alwaysMatchSimple);
   }

   public boolean matchingParams(final List syms1, final List syms2) {
      return Types.matchingParams$(this, syms1, syms2);
   }

   public boolean isWithinBounds(final Types.Type pre, final Symbols.Symbol owner, final List tparams, final List targs) {
      return Types.isWithinBounds$(this, pre, owner, tparams, targs);
   }

   public Types.Type elimAnonymousClass(final Types.Type t) {
      return Types.elimAnonymousClass$(this, t);
   }

   public List typeVarsInType(final Types.Type tp) {
      return Types.typeVarsInType$(this, tp);
   }

   public final Object suspendingTypeVars(final List tvs, final Function0 op) {
      return Types.suspendingTypeVars$(this, tvs, op);
   }

   public final Tuple2 stripExistentialsAndTypeVars(final List ts, final boolean expandLazyBaseType) {
      return Types.stripExistentialsAndTypeVars$(this, ts, expandLazyBaseType);
   }

   public final boolean stripExistentialsAndTypeVars$default$2() {
      return Types.stripExistentialsAndTypeVars$default$2$(this);
   }

   public Types.Type mergePrefixAndArgs(final List tps0, final int variance, final int depth) {
      return Types.mergePrefixAndArgs$(this, tps0, variance, depth);
   }

   public void addMember(final Types.Type thistp, final Types.Type tp, final Symbols.Symbol sym) {
      Types.addMember$(this, thistp, tp, sym);
   }

   public void addMember(final Types.Type thistp, final Types.Type tp, final Symbols.Symbol sym, final int depth) {
      Types.addMember$(this, thistp, tp, sym, depth);
   }

   public boolean isJavaVarargsAncestor(final Symbols.Symbol clazz) {
      return Types.isJavaVarargsAncestor$(this, clazz);
   }

   public boolean inheritsJavaVarArgsMethod(final Symbols.Symbol clazz) {
      return Types.inheritsJavaVarArgsMethod$(this, clazz);
   }

   public String indent() {
      return Types.indent$(this);
   }

   public void indent_$eq(final String value) {
      Types.indent_$eq$(this, value);
   }

   public boolean explain(final String op, final Function2 p, final Types.Type tp1, final Object arg2) {
      return Types.explain$(this, op, p, tp1, arg2);
   }

   public void explainTypes(final Types.Type found, final Types.Type required) {
      Types.explainTypes$(this, found, required);
   }

   public void explainTypes(final Function2 op, final Types.Type found, final Types.Type required) {
      Types.explainTypes$(this, op, found, required);
   }

   public Object withTypesExplained(final Function0 op) {
      return Types.withTypesExplained$(this, op);
   }

   public boolean isUnboundedGeneric(final Types.Type tp) {
      return Types.isUnboundedGeneric$(this, tp);
   }

   public boolean isBoundedGeneric(final Types.Type tp) {
      return Types.isBoundedGeneric$(this, tp);
   }

   public List addSerializable(final Seq ps) {
      return Types.addSerializable$(this, ps);
   }

   public final Types.Type uncheckedBounds(final Types.Type tp) {
      return Types.uncheckedBounds$(this, tp);
   }

   public Scopes.Scope nonTrivialMembers(final Symbols.Symbol clazz) {
      return Types.nonTrivialMembers$(this, clazz);
   }

   public Scopes.Scope importableMembers(final Types.Type pre) {
      return Types.importableMembers$(this, pre);
   }

   public void invalidateTreeTpeCaches(final Trees.Tree tree, final scala.collection.Set updatedSyms) {
      Types.invalidateTreeTpeCaches$(this, tree, updatedSyms);
   }

   public void invalidateCaches(final Types.Type t, final scala.collection.Set updatedSyms) {
      Types.invalidateCaches$(this, t, updatedSyms);
   }

   public final boolean typeIsNothing(final Types.Type tp) {
      return Types.typeIsNothing$(this, tp);
   }

   public final boolean typeIsAnyOrJavaObject(final Types.Type tp) {
      return Types.typeIsAnyOrJavaObject$(this, tp);
   }

   public final boolean typeIsAnyExactly(final Types.Type tp) {
      return Types.typeIsAnyExactly$(this, tp);
   }

   public final int typeDepth(final Types.Type tp) {
      return Types.typeDepth$(this, tp);
   }

   public int maxDepth(final List tps) {
      return Types.maxDepth$(this, tps);
   }

   public TypeConstraints.UndoLog undoLog() {
      return TypeConstraints.undoLog$(this);
   }

   public boolean solve(final List tvars, final List tparams, final Variance.Extractor getVariance, final boolean upper, final int depth) {
      return TypeConstraints.solve$(this, tvars, tparams, getVariance, upper, depth);
   }

   public TypeMaps.TypeMap rawToExistential() {
      return TypeMaps.rawToExistential$(this);
   }

   public boolean isPossiblePrefix(final Symbols.Symbol clazz) {
      return TypeMaps.isPossiblePrefix$(this, clazz);
   }

   public boolean skipPrefixOf(final Types.Type pre, final Symbols.Symbol clazz) {
      return TypeMaps.skipPrefixOf$(this, pre, clazz);
   }

   /** @deprecated */
   public final TypeMaps.AsSeenFromMap newAsSeenFromMap(final Types.Type pre, final Symbols.Symbol clazz) {
      return TypeMaps.newAsSeenFromMap$(this, pre, clazz);
   }

   public List lubList(final List ts, final int depth) {
      return GlbLubs.lubList$(this, ts, depth);
   }

   public List spanningTypes(final List ts) {
      return GlbLubs.spanningTypes$(this, ts);
   }

   public boolean sameWeakLubAsLub(final List tps) {
      return GlbLubs.sameWeakLubAsLub$(this, tps);
   }

   public Types.Type weakLub(final List tps) {
      return GlbLubs.weakLub$(this, tps);
   }

   public Types.Type numericLub(final List ts) {
      return GlbLubs.numericLub$(this, ts);
   }

   public HashMap lubResults() {
      return GlbLubs.lubResults$(this);
   }

   public HashMap glbResults() {
      return GlbLubs.glbResults$(this);
   }

   public Types.Type lub(final List ts) {
      return GlbLubs.lub$(this, ts);
   }

   public Types.Type lub(final List ts0, final int depth) {
      return GlbLubs.lub$(this, ts0, depth);
   }

   public Types.Type glb(final List ts) {
      return GlbLubs.glb$(this, ts);
   }

   public Types.Type glb(final List ts, final int depth) {
      return GlbLubs.glb$(this, ts, depth);
   }

   public Types.Type glbNorm(final List ts0, final int depth) {
      return GlbLubs.glbNorm$(this, ts0, depth);
   }

   public Symbols.Symbol commonOwner(final Types.Type t) {
      return CommonOwners.commonOwner$(this, (Types.Type)t);
   }

   public Symbols.Symbol commonOwner(final List tps) {
      return CommonOwners.commonOwner$(this, (List)tps);
   }

   public CommonOwners.CommonOwnerMap commonOwnerMap() {
      return CommonOwners.commonOwnerMap$(this);
   }

   public int toStringRecursions() {
      return TypeToStrings.toStringRecursions$(this);
   }

   public void toStringRecursions_$eq(final int value) {
      TypeToStrings.toStringRecursions_$eq$(this, value);
   }

   public HashSet toStringSubjects() {
      return TypeToStrings.toStringSubjects$(this);
   }

   public String typeToString(final Types.Type tpe) {
      return TypeToStrings.typeToString$(this, tpe);
   }

   public HashSet pendingSubTypes() {
      return TypeComparers.pendingSubTypes$(this);
   }

   public int subsametypeRecursions() {
      return TypeComparers.subsametypeRecursions$(this);
   }

   public void subsametypeRecursions_$eq(final int value) {
      TypeComparers.subsametypeRecursions_$eq$(this, value);
   }

   public boolean isDifferentType(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isDifferentType$(this, tp1, tp2);
   }

   public boolean isDifferentTypeConstructor(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isDifferentTypeConstructor$(this, tp1, tp2);
   }

   public boolean isSameType(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isSameType$(this, tp1, tp2);
   }

   public boolean isSameType2(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isSameType2$(this, tp1, tp2);
   }

   public boolean isSubType(final Types.Type tp1, final Types.Type tp2, final int depth) {
      return TypeComparers.isSubType$(this, tp1, tp2, depth);
   }

   public int isSubType$default$3() {
      return TypeComparers.isSubType$default$3$(this);
   }

   public boolean isHKSubType(final Types.Type tp1, final Types.Type tp2, final int depth) {
      return TypeComparers.isHKSubType$(this, tp1, tp2, depth);
   }

   public boolean isWeakSubType(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isWeakSubType$(this, tp1, tp2);
   }

   public boolean isNumericSubType(final Types.Type tp1, final Types.Type tp2) {
      return TypeComparers.isNumericSubType$(this, tp1, tp2);
   }

   public int getCurrentSymbolIdCount() {
      return Symbols.getCurrentSymbolIdCount$(this);
   }

   public int nextId() {
      return Symbols.nextId$(this);
   }

   public Map recursionTable() {
      return Symbols.recursionTable$(this);
   }

   public void recursionTable_$eq(final Map value) {
      Symbols.recursionTable_$eq$(this, value);
   }

   public int lockedCount() {
      return Symbols.lockedCount$(this);
   }

   public void lockedCount_$eq(final int i) {
      Symbols.lockedCount_$eq$(this, i);
   }

   /** @deprecated */
   public int nextExistentialId() {
      return Symbols.nextExistentialId$(this);
   }

   /** @deprecated */
   public Names.TypeName freshExistentialName(final String suffix) {
      return Symbols.freshExistentialName$(this, suffix);
   }

   public Names.TypeName freshExistentialName(final String suffix, final int id) {
      return Symbols.freshExistentialName$(this, suffix, id);
   }

   public Symbols.ModuleSymbol connectModuleToClass(final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass) {
      return Symbols.connectModuleToClass$(this, m, moduleClass);
   }

   public Symbols.FreeTermSymbol newFreeTermSymbol(final Names.TermName name, final Function0 value, final long flags, final String origin) {
      return Symbols.newFreeTermSymbol$(this, name, value, flags, origin);
   }

   public long newFreeTermSymbol$default$3() {
      return Symbols.newFreeTermSymbol$default$3$(this);
   }

   public Symbols.FreeTypeSymbol newFreeTypeSymbol(final Names.TypeName name, final long flags, final String origin) {
      return Symbols.newFreeTypeSymbol$(this, name, flags, origin);
   }

   public long newFreeTypeSymbol$default$2() {
      return Symbols.newFreeTypeSymbol$default$2$(this);
   }

   public void saveOriginalOwner(final Symbols.Symbol sym) {
      Symbols.saveOriginalOwner$(this, sym);
   }

   public void defineOriginalOwner(final Symbols.Symbol sym, final Symbols.Symbol owner) {
      Symbols.defineOriginalOwner$(this, sym, owner);
   }

   public Symbols.TypeSymbol symbolOf(final TypeTags.WeakTypeTag evidence$1) {
      return Symbols.symbolOf$(this, evidence$1);
   }

   public Symbols.Symbol newStubSymbol(final Symbols.Symbol owner, final Names.Name name, final String missingMessage) {
      return Symbols.newStubSymbol$(this, owner, name, missingMessage);
   }

   public Symbols.NoSymbol makeNoSymbol() {
      return Symbols.makeNoSymbol$(this);
   }

   public List deriveSymbols(final List syms, final Function1 symFn) {
      return Symbols.deriveSymbols$(this, syms, symFn);
   }

   public List deriveSymbols2(final List syms, final List as, final Function2 symFn) {
      return Symbols.deriveSymbols2$(this, syms, as, symFn);
   }

   public Types.Type deriveType(final List syms, final Function1 symFn, final Types.Type tpe) {
      return Symbols.deriveType$(this, syms, symFn, tpe);
   }

   public Types.Type deriveType2(final List syms, final List as, final Function2 symFn, final Types.Type tpe) {
      return Symbols.deriveType2$(this, syms, as, symFn, tpe);
   }

   public Types.Type deriveTypeWithWildcards(final List syms, final Types.Type tpe) {
      return Symbols.deriveTypeWithWildcards$(this, syms, tpe);
   }

   public List cloneSymbols(final List syms) {
      return Symbols.cloneSymbols$(this, syms);
   }

   public List cloneSymbolsAtOwner(final List syms, final Symbols.Symbol owner) {
      return Symbols.cloneSymbolsAtOwner$(this, syms, owner);
   }

   public List cloneSymbolsAndModify(final List syms, final Function1 infoFn) {
      return Symbols.cloneSymbolsAndModify$(this, syms, infoFn);
   }

   public List cloneSymbolsAtOwnerAndModify(final List syms, final Symbols.Symbol owner, final Function1 infoFn) {
      return Symbols.cloneSymbolsAtOwnerAndModify$(this, syms, owner, infoFn);
   }

   public Object createFromClonedSymbols(final List syms, final Types.Type tpe, final Function2 creator) {
      return Symbols.createFromClonedSymbols$(this, syms, tpe, creator);
   }

   public Object createFromClonedSymbolsAtOwner(final List syms, final Symbols.Symbol owner, final Types.Type tpe, final Function2 creator) {
      return Symbols.createFromClonedSymbolsAtOwner$(this, syms, owner, tpe, creator);
   }

   public List mapParamss(final Symbols.Symbol sym, final Function1 f) {
      return Symbols.mapParamss$(this, sym, f);
   }

   public void foreachParamss(final Symbols.Symbol sym, final Function1 f) {
      Symbols.foreachParamss$(this, sym, f);
   }

   public List existingSymbols(final List syms) {
      return Symbols.existingSymbols$(this, syms);
   }

   public final Symbols.Symbol closestEnclMethod(final Symbols.Symbol from) {
      return Symbols.closestEnclMethod$(this, from);
   }

   public final boolean allSymbolsHaveOwner(final List syms, final Symbols.Symbol owner) {
      return Symbols.allSymbolsHaveOwner$(this, syms, owner);
   }

   public final boolean argsDependOnPrefix(final Symbols.Symbol sym) {
      return Symbols.argsDependOnPrefix$(this, sym);
   }

   public Symbols.SymbolOps FlagOps(final long mask) {
      return Symbols.FlagOps$(this, mask);
   }

   public final void markFlagsCompleted(final Symbols.Symbol sym, final long mask) {
      Symbols.markFlagsCompleted$(this, sym, mask);
   }

   public final void markFlagsCompleted(final Symbols.Symbol sym1, final Symbols.Symbol sym2, final long mask) {
      Symbols.markFlagsCompleted$(this, sym1, sym2, mask);
   }

   public final void markAllCompleted(final Symbols.Symbol sym) {
      Symbols.markAllCompleted$(this, sym);
   }

   public final void markAllCompleted(final Symbols.Symbol sym1, final Symbols.Symbol sym2) {
      Symbols.markAllCompleted$(this, sym1, sym2);
   }

   public boolean synchronizeNames() {
      return Names.synchronizeNames$(this);
   }

   /** @deprecated */
   public char[] chrs() {
      return Names.chrs$(this);
   }

   /** @deprecated */
   public void chrs_$eq(final char[] cs) {
      Names.chrs_$eq$(this, cs);
   }

   public final int nameTableSize() {
      return Names.nameTableSize$(this);
   }

   public final Iterator allNames() {
      return Names.allNames$(this);
   }

   public final Names.TermName newTermName(final char[] cs, final int offset, final int len) {
      return Names.newTermName$(this, (char[])cs, offset, len);
   }

   public final Names.TermName newTermName(final char[] cs) {
      return Names.newTermName$(this, (char[])cs);
   }

   public final Names.TypeName newTypeName(final char[] cs) {
      return Names.newTypeName$(this, (char[])cs);
   }

   public final Names.TermName newTermName(final char[] cs, final int offset, final int len0, final String cachedString) {
      return Names.newTermName$(this, cs, offset, len0, cachedString);
   }

   public final Names.TypeName newTypeName(final char[] cs, final int offset, final int len, final String cachedString) {
      return Names.newTypeName$(this, cs, offset, len, cachedString);
   }

   public Names.TermName newTermName(final String s) {
      return Names.newTermName$(this, (String)s);
   }

   public Names.TypeName newTypeName(final String s) {
      return Names.newTypeName$(this, (String)s);
   }

   public final Names.TermName newTermName(final byte[] bs, final int offset, final int len) {
      return Names.newTermName$(this, (byte[])bs, offset, len);
   }

   public final Names.TermName newTermNameCached(final String s) {
      return Names.newTermNameCached$(this, s);
   }

   public final Names.TypeName newTypeNameCached(final String s) {
      return Names.newTypeNameCached$(this, s);
   }

   public final Names.TypeName newTypeName(final char[] cs, final int offset, final int len) {
      return Names.newTypeName$(this, (char[])cs, offset, len);
   }

   public final Names.TypeName newTypeName(final byte[] bs, final int offset, final int len) {
      return Names.newTypeName$(this, (byte[])bs, offset, len);
   }

   public final Names.TypeName lookupTypeName(final char[] cs) {
      return Names.lookupTypeName$(this, cs);
   }

   public final boolean corresponds3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.corresponds3$(this, xs1, xs2, xs3, f);
   }

   public final boolean mexists(final List xss, final Function1 p) {
      return Collections.mexists$(this, xss, p);
   }

   public final boolean mforall(final List xss, final Function1 p) {
      return Collections.mforall$(this, xss, p);
   }

   public final List mmap(final List xss, final Function1 f) {
      return Collections.mmap$(this, xss, f);
   }

   public final Option mfind(final List xss, final Function1 p) {
      return Collections.mfind$(this, xss, p);
   }

   public final void mforeach(final List xss, final Function1 f) {
      Collections.mforeach$(this, (List)xss, f);
   }

   public final void mforeach(final Iterable xss, final Function1 f) {
      Collections.mforeach$(this, (Iterable)xss, f);
   }

   public final List mapList(final List as, final Function1 f) {
      return Collections.mapList$(this, as, f);
   }

   public final boolean sameElementsEquals(final List thiss, final List that) {
      return Collections.sameElementsEquals$(this, thiss, that);
   }

   public final Option collectFirst(final List as, final PartialFunction pf) {
      return Collections.collectFirst$(this, as, pf);
   }

   public final List map2(final List xs1, final List xs2, final Function2 f) {
      return Collections.map2$(this, xs1, xs2, f);
   }

   public final List map2Conserve(final List xs, final List ys, final Function2 f) {
      return Collections.map2Conserve$(this, xs, ys, f);
   }

   public final List map3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.map3$(this, xs1, xs2, xs3, f);
   }

   public final List flatMap2(final List xs1, final List xs2, final Function2 f) {
      return Collections.flatMap2$(this, xs1, xs2, f);
   }

   public final Object foldLeft2(final List xs1, final List xs2, final Object z0, final Function3 f) {
      return Collections.foldLeft2$(this, xs1, xs2, z0, f);
   }

   public final List flatCollect(final List elems, final PartialFunction pf) {
      return Collections.flatCollect$(this, elems, pf);
   }

   public final List distinctBy(final List xs, final Function1 f) {
      return Collections.distinctBy$(this, xs, f);
   }

   public final boolean flattensToEmpty(final Seq xss) {
      return Collections.flattensToEmpty$(this, xss);
   }

   public final void foreachWithIndex(final List xs, final Function2 f) {
      Collections.foreachWithIndex$(this, xs, f);
   }

   public final Object findOrElse(final IterableOnce xs, final Function1 p, final Function0 orElse) {
      return Collections.findOrElse$(this, xs, p, orElse);
   }

   public final Map mapFrom(final List xs, final Function1 f) {
      return Collections.mapFrom$(this, xs, f);
   }

   public final LinkedHashMap linkedMapFrom(final List xs, final Function1 f) {
      return Collections.linkedMapFrom$(this, xs, f);
   }

   public final List mapWithIndex(final List xs, final Function2 f) {
      return Collections.mapWithIndex$(this, xs, f);
   }

   public final Map collectMap2(final List xs1, final List xs2, final Function2 p) {
      return Collections.collectMap2$(this, xs1, xs2, p);
   }

   public final void foreach2(final List xs1, final List xs2, final Function2 f) {
      Collections.foreach2$(this, xs1, xs2, f);
   }

   public final void foreach3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      Collections.foreach3$(this, xs1, xs2, xs3, f);
   }

   public final boolean exists2(final List xs1, final List xs2, final Function2 f) {
      return Collections.exists2$(this, xs1, xs2, f);
   }

   public final boolean exists3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.exists3$(this, xs1, xs2, xs3, f);
   }

   public final boolean forall3(final List xs1, final List xs2, final List xs3, final Function3 f) {
      return Collections.forall3$(this, xs1, xs2, xs3, f);
   }

   public final Iterator mapFilter2(final Iterator itA, final Iterator itB, final Function2 f) {
      return Collections.mapFilter2$(this, itA, itB, f);
   }

   public final Object mapToArray(final List xs, final Function1 f, final ClassTag evidence$1) {
      return Collections.mapToArray$(this, xs, f, evidence$1);
   }

   public final List mapFromArray(final Object arr, final Function1 f) {
      return Collections.mapFromArray$(this, arr, f);
   }

   public final Option sequenceOpt(final List as) {
      return Collections.sequenceOpt$(this, as);
   }

   public final Option traverseOpt(final List as, final Function1 f) {
      return Collections.traverseOpt$(this, as, f);
   }

   public final void partitionInto(final List xs, final Function1 pred, final ListBuffer ayes, final ListBuffer nays) {
      Collections.partitionInto$(this, xs, pred, ayes, nays);
   }

   public final BitSet bitSetByPredicate(final List xs, final Function1 pred) {
      return Collections.bitSetByPredicate$(this, xs, pred);
   }

   public final Option transposeSafe(final List ass) {
      return Collections.transposeSafe$(this, ass);
   }

   public final boolean sameLength(final List xs1, final List xs2) {
      return Collections.sameLength$(this, xs1, xs2);
   }

   public final int compareLengths(final List xs1, final List xs2) {
      return Collections.compareLengths$(this, xs1, xs2);
   }

   public final boolean hasLength(final List xs, final int len) {
      return Collections.hasLength$(this, xs, len);
   }

   public final int sumSize(final List xss, final int acc) {
      return Collections.sumSize$(this, xss, acc);
   }

   public final List fillList(final int n, final Object t) {
      return Collections.fillList$(this, n, t);
   }

   public final void mapToArray(final List as, final Object arr, final int i, final Function1 f) {
      Collections.mapToArray$(this, as, arr, i, f);
   }

   public final Tuple2 partitionConserve(final List as, final Function1 p) {
      return Collections.partitionConserve$(this, as, p);
   }

   private SimpleNameOrdering$ SimpleNameOrdering() {
      if (this.SimpleNameOrdering$module == null) {
         this.SimpleNameOrdering$lzycompute$1();
      }

      return this.SimpleNameOrdering$module;
   }

   public traceSymbols$ traceSymbols() {
      if (this.traceSymbols$module == null) {
         this.traceSymbols$lzycompute$1();
      }

      return this.traceSymbols$module;
   }

   public perRunCaches$ perRunCaches() {
      if (this.perRunCaches$module == null) {
         this.perRunCaches$lzycompute$1();
      }

      return this.perRunCaches$module;
   }

   private Universe.MacroInternalApi internal$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.internal = Internals.internal$(this);
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.internal;
   }

   public Universe.MacroInternalApi internal() {
      return (this.bitmap$0 & 1) == 0 ? this.internal$lzycompute() : this.internal;
   }

   private Universe.MacroCompatApi compat$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.compat = Internals.compat$(this);
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.compat;
   }

   /** @deprecated */
   public Universe.MacroCompatApi compat() {
      return (this.bitmap$0 & 2) == 0 ? this.compat$lzycompute() : this.compat;
   }

   private Universe.TreeGen treeBuild$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.treeBuild = Internals.treeBuild$(this);
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.treeBuild;
   }

   public Universe.TreeGen treeBuild() {
      return (this.bitmap$0 & 4) == 0 ? this.treeBuild$lzycompute() : this.treeBuild;
   }

   public FreshNameCreator globalFreshNameCreator() {
      return this.globalFreshNameCreator;
   }

   public FreshNames.FreshNameExtractor$ FreshNameExtractor() {
      if (this.FreshNameExtractor$module == null) {
         this.FreshNameExtractor$lzycompute$1();
      }

      return this.FreshNameExtractor$module;
   }

   public void scala$reflect$internal$FreshNames$_setter_$globalFreshNameCreator_$eq(final FreshNameCreator x$1) {
      this.globalFreshNameCreator = x$1;
   }

   public ReificationSupport.ReificationSupportImpl build() {
      return this.build;
   }

   public void scala$reflect$internal$ReificationSupport$_setter_$build_$eq(final ReificationSupport.ReificationSupportImpl x$1) {
      this.build = x$1;
   }

   public StdCreators.FixedMirrorTreeCreator$ FixedMirrorTreeCreator() {
      if (this.FixedMirrorTreeCreator$module == null) {
         this.FixedMirrorTreeCreator$lzycompute$1();
      }

      return this.FixedMirrorTreeCreator$module;
   }

   public StdCreators.FixedMirrorTypeCreator$ FixedMirrorTypeCreator() {
      if (this.FixedMirrorTypeCreator$module == null) {
         this.FixedMirrorTypeCreator$lzycompute$1();
      }

      return this.FixedMirrorTypeCreator$module;
   }

   public StdAttachments.CompoundTypeTreeOriginalAttachment$ CompoundTypeTreeOriginalAttachment() {
      if (this.CompoundTypeTreeOriginalAttachment$module == null) {
         this.CompoundTypeTreeOriginalAttachment$lzycompute$1();
      }

      return this.CompoundTypeTreeOriginalAttachment$module;
   }

   public StdAttachments.SAMFunction$ SAMFunction() {
      if (this.SAMFunction$module == null) {
         this.SAMFunction$lzycompute$1();
      }

      return this.SAMFunction$module;
   }

   public StdAttachments.DelambdafyTarget$ DelambdafyTarget() {
      if (this.DelambdafyTarget$module == null) {
         this.DelambdafyTarget$lzycompute$1();
      }

      return this.DelambdafyTarget$module;
   }

   public StdAttachments.BackquotedIdentifierAttachment$ BackquotedIdentifierAttachment() {
      if (this.BackquotedIdentifierAttachment$module == null) {
         this.BackquotedIdentifierAttachment$lzycompute$1();
      }

      return this.BackquotedIdentifierAttachment$module;
   }

   public StdAttachments.PostfixAttachment$ PostfixAttachment() {
      if (this.PostfixAttachment$module == null) {
         this.PostfixAttachment$lzycompute$1();
      }

      return this.PostfixAttachment$module;
   }

   public StdAttachments.InfixAttachment$ InfixAttachment() {
      if (this.InfixAttachment$module == null) {
         this.InfixAttachment$lzycompute$1();
      }

      return this.InfixAttachment$module;
   }

   public StdAttachments.AutoApplicationAttachment$ AutoApplicationAttachment() {
      if (this.AutoApplicationAttachment$module == null) {
         this.AutoApplicationAttachment$lzycompute$1();
      }

      return this.AutoApplicationAttachment$module;
   }

   public StdAttachments.NoWarnAttachment$ NoWarnAttachment() {
      if (this.NoWarnAttachment$module == null) {
         this.NoWarnAttachment$lzycompute$1();
      }

      return this.NoWarnAttachment$module;
   }

   public StdAttachments.PatShadowAttachment$ PatShadowAttachment() {
      if (this.PatShadowAttachment$module == null) {
         this.PatShadowAttachment$lzycompute$1();
      }

      return this.PatShadowAttachment$module;
   }

   public StdAttachments.PatVarDefAttachment$ PatVarDefAttachment() {
      if (this.PatVarDefAttachment$module == null) {
         this.PatVarDefAttachment$lzycompute$1();
      }

      return this.PatVarDefAttachment$module;
   }

   public StdAttachments.MultiDefAttachment$ MultiDefAttachment() {
      if (this.MultiDefAttachment$module == null) {
         this.MultiDefAttachment$lzycompute$1();
      }

      return this.MultiDefAttachment$module;
   }

   public StdAttachments.ForAttachment$ ForAttachment() {
      if (this.ForAttachment$module == null) {
         this.ForAttachment$lzycompute$1();
      }

      return this.ForAttachment$module;
   }

   public StdAttachments.SyntheticUnitAttachment$ SyntheticUnitAttachment() {
      if (this.SyntheticUnitAttachment$module == null) {
         this.SyntheticUnitAttachment$lzycompute$1();
      }

      return this.SyntheticUnitAttachment$module;
   }

   public StdAttachments.SubpatternsAttachment$ SubpatternsAttachment() {
      if (this.SubpatternsAttachment$module == null) {
         this.SubpatternsAttachment$lzycompute$1();
      }

      return this.SubpatternsAttachment$module;
   }

   public StdAttachments.NoInlineCallsiteAttachment$ NoInlineCallsiteAttachment() {
      if (this.NoInlineCallsiteAttachment$module == null) {
         this.NoInlineCallsiteAttachment$lzycompute$1();
      }

      return this.NoInlineCallsiteAttachment$module;
   }

   public StdAttachments.InlineCallsiteAttachment$ InlineCallsiteAttachment() {
      if (this.InlineCallsiteAttachment$module == null) {
         this.InlineCallsiteAttachment$lzycompute$1();
      }

      return this.InlineCallsiteAttachment$module;
   }

   public StdAttachments.OuterArgCanBeElided$ OuterArgCanBeElided() {
      if (this.OuterArgCanBeElided$module == null) {
         this.OuterArgCanBeElided$lzycompute$1();
      }

      return this.OuterArgCanBeElided$module;
   }

   public StdAttachments.UseInvokeSpecial$ UseInvokeSpecial() {
      if (this.UseInvokeSpecial$module == null) {
         this.UseInvokeSpecial$lzycompute$1();
      }

      return this.UseInvokeSpecial$module;
   }

   public StdAttachments.TypeParamVarargsAttachment$ TypeParamVarargsAttachment() {
      if (this.TypeParamVarargsAttachment$module == null) {
         this.TypeParamVarargsAttachment$lzycompute$1();
      }

      return this.TypeParamVarargsAttachment$module;
   }

   public StdAttachments.KnownDirectSubclassesCalled$ KnownDirectSubclassesCalled() {
      if (this.KnownDirectSubclassesCalled$module == null) {
         this.KnownDirectSubclassesCalled$lzycompute$1();
      }

      return this.KnownDirectSubclassesCalled$module;
   }

   public StdAttachments.DottyEnumSingleton$ DottyEnumSingleton() {
      if (this.DottyEnumSingleton$module == null) {
         this.DottyEnumSingleton$lzycompute$1();
      }

      return this.DottyEnumSingleton$module;
   }

   public StdAttachments.ConstructorNeedsFence$ ConstructorNeedsFence() {
      if (this.ConstructorNeedsFence$module == null) {
         this.ConstructorNeedsFence$lzycompute$1();
      }

      return this.ConstructorNeedsFence$module;
   }

   public StdAttachments.MultiargInfixAttachment$ MultiargInfixAttachment() {
      if (this.MultiargInfixAttachment$module == null) {
         this.MultiargInfixAttachment$lzycompute$1();
      }

      return this.MultiargInfixAttachment$module;
   }

   public StdAttachments.NullaryOverrideAdapted$ NullaryOverrideAdapted() {
      if (this.NullaryOverrideAdapted$module == null) {
         this.NullaryOverrideAdapted$lzycompute$1();
      }

      return this.NullaryOverrideAdapted$module;
   }

   public StdAttachments.ChangeOwnerAttachment$ ChangeOwnerAttachment() {
      if (this.ChangeOwnerAttachment$module == null) {
         this.ChangeOwnerAttachment$lzycompute$1();
      }

      return this.ChangeOwnerAttachment$module;
   }

   public StdAttachments.InterpolatedString$ InterpolatedString() {
      if (this.InterpolatedString$module == null) {
         this.InterpolatedString$lzycompute$1();
      }

      return this.InterpolatedString$module;
   }

   public StdAttachments.VirtualStringContext$ VirtualStringContext() {
      if (this.VirtualStringContext$module == null) {
         this.VirtualStringContext$lzycompute$1();
      }

      return this.VirtualStringContext$module;
   }

   public StdAttachments.CaseApplyInheritAccess$ CaseApplyInheritAccess() {
      if (this.CaseApplyInheritAccess$module == null) {
         this.CaseApplyInheritAccess$lzycompute$1();
      }

      return this.CaseApplyInheritAccess$module;
   }

   public StdAttachments.RootSelection$ RootSelection() {
      if (this.RootSelection$module == null) {
         this.RootSelection$lzycompute$1();
      }

      return this.RootSelection$module;
   }

   public StdAttachments.TypedExpectingUnitAttachment$ TypedExpectingUnitAttachment() {
      if (this.TypedExpectingUnitAttachment$module == null) {
         this.TypedExpectingUnitAttachment$lzycompute$1();
      }

      return this.TypedExpectingUnitAttachment$module;
   }

   public StdAttachments.FieldTypeInferred$ FieldTypeInferred() {
      if (this.FieldTypeInferred$module == null) {
         this.FieldTypeInferred$lzycompute$1();
      }

      return this.FieldTypeInferred$module;
   }

   public StdAttachments.LookupAmbiguityWarning$ LookupAmbiguityWarning() {
      if (this.LookupAmbiguityWarning$module == null) {
         this.LookupAmbiguityWarning$lzycompute$1();
      }

      return this.LookupAmbiguityWarning$module;
   }

   public StdAttachments.PermittedSubclasses$ PermittedSubclasses() {
      if (this.PermittedSubclasses$module == null) {
         this.PermittedSubclasses$lzycompute$1();
      }

      return this.PermittedSubclasses$module;
   }

   public StdAttachments.PermittedSubclassSymbols$ PermittedSubclassSymbols() {
      if (this.PermittedSubclassSymbols$module == null) {
         this.PermittedSubclassSymbols$lzycompute$1();
      }

      return this.PermittedSubclassSymbols$module;
   }

   public StdAttachments.NamePos$ NamePos() {
      if (this.NamePos$module == null) {
         this.NamePos$lzycompute$1();
      }

      return this.NamePos$module;
   }

   public StdAttachments.UnnamedArg$ UnnamedArg() {
      if (this.UnnamedArg$module == null) {
         this.UnnamedArg$lzycompute$1();
      }

      return this.UnnamedArg$module;
   }

   public StdAttachments.DiscardedValue$ DiscardedValue() {
      if (this.DiscardedValue$module == null) {
         this.DiscardedValue$lzycompute$1();
      }

      return this.DiscardedValue$module;
   }

   public StdAttachments.DiscardedExpr$ DiscardedExpr() {
      if (this.DiscardedExpr$module == null) {
         this.DiscardedExpr$lzycompute$1();
      }

      return this.DiscardedExpr$module;
   }

   public StdAttachments.BooleanParameterType$ BooleanParameterType() {
      if (this.BooleanParameterType$module == null) {
         this.BooleanParameterType$lzycompute$1();
      }

      return this.BooleanParameterType$module;
   }

   public TypeDebugging.noPrint$ noPrint() {
      if (this.noPrint$module == null) {
         this.noPrint$lzycompute$1();
      }

      return this.noPrint$module;
   }

   public TypeDebugging.typeDebug$ typeDebug() {
      if (this.typeDebug$module == null) {
         this.typeDebug$lzycompute$1();
      }

      return this.typeDebug$module;
   }

   public NoPosition$ NoPosition() {
      return this.NoPosition;
   }

   public ClassTag PositionTag() {
      return this.PositionTag;
   }

   public Ordering scala$reflect$internal$Positions$$posStartOrdering() {
      return this.scala$reflect$internal$Positions$$posStartOrdering;
   }

   public ReusableInstance scala$reflect$internal$Positions$$setChildrenPosAccumulator() {
      return this.scala$reflect$internal$Positions$$setChildrenPosAccumulator;
   }

   private Positions.PosAssigner posAssigner$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            this.posAssigner = Positions.posAssigner$(this);
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.posAssigner;
   }

   public Positions.PosAssigner posAssigner() {
      return (this.bitmap$0 & 8) == 0 ? this.posAssigner$lzycompute() : this.posAssigner;
   }

   public void scala$reflect$internal$Positions$_setter_$NoPosition_$eq(final NoPosition$ x$1) {
      this.NoPosition = x$1;
   }

   public void scala$reflect$internal$Positions$_setter_$PositionTag_$eq(final ClassTag x$1) {
      this.PositionTag = x$1;
   }

   public final void scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$posStartOrdering_$eq(final Ordering x$1) {
      this.scala$reflect$internal$Positions$$posStartOrdering = x$1;
   }

   public final void scala$reflect$internal$Positions$_setter_$scala$reflect$internal$Positions$$setChildrenPosAccumulator_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$Positions$$setChildrenPosAccumulator = x$1;
   }

   public Printers.ConsoleWriter$ ConsoleWriter() {
      if (this.ConsoleWriter$module == null) {
         this.ConsoleWriter$lzycompute$1();
      }

      return this.ConsoleWriter$module;
   }

   public int nodeCount() {
      return this.nodeCount;
   }

   public void nodeCount_$eq(final int x$1) {
      this.nodeCount = x$1;
   }

   public Trees.RefTree$ RefTree() {
      if (this.RefTree$module == null) {
         this.RefTree$lzycompute$1();
      }

      return this.RefTree$module;
   }

   public Trees.PackageDef$ PackageDef() {
      if (this.PackageDef$module == null) {
         this.PackageDef$lzycompute$1();
      }

      return this.PackageDef$module;
   }

   public Trees.ClassDef$ ClassDef() {
      if (this.ClassDef$module == null) {
         this.ClassDef$lzycompute$1();
      }

      return this.ClassDef$module;
   }

   public Trees.ModuleDef$ ModuleDef() {
      if (this.ModuleDef$module == null) {
         this.ModuleDef$lzycompute$1();
      }

      return this.ModuleDef$module;
   }

   public Trees.ValOrDefDef$ ValOrDefDef() {
      if (this.ValOrDefDef$module == null) {
         this.ValOrDefDef$lzycompute$1();
      }

      return this.ValOrDefDef$module;
   }

   public Trees.ValDef$ ValDef() {
      if (this.ValDef$module == null) {
         this.ValDef$lzycompute$1();
      }

      return this.ValDef$module;
   }

   public Trees.DefDef$ DefDef() {
      if (this.DefDef$module == null) {
         this.DefDef$lzycompute$1();
      }

      return this.DefDef$module;
   }

   public Trees.TypeDef$ TypeDef() {
      if (this.TypeDef$module == null) {
         this.TypeDef$lzycompute$1();
      }

      return this.TypeDef$module;
   }

   public Trees.LabelDef$ LabelDef() {
      if (this.LabelDef$module == null) {
         this.LabelDef$lzycompute$1();
      }

      return this.LabelDef$module;
   }

   public Trees.ImportSelector$ ImportSelector() {
      if (this.ImportSelector$module == null) {
         this.ImportSelector$lzycompute$1();
      }

      return this.ImportSelector$module;
   }

   public Trees.Import$ Import() {
      if (this.Import$module == null) {
         this.Import$lzycompute$1();
      }

      return this.Import$module;
   }

   public Trees.Template$ Template() {
      if (this.Template$module == null) {
         this.Template$lzycompute$1();
      }

      return this.Template$module;
   }

   public Trees.Block$ Block() {
      if (this.Block$module == null) {
         this.Block$lzycompute$1();
      }

      return this.Block$module;
   }

   public Trees.CaseDef$ CaseDef() {
      if (this.CaseDef$module == null) {
         this.CaseDef$lzycompute$1();
      }

      return this.CaseDef$module;
   }

   public Trees.Alternative$ Alternative() {
      if (this.Alternative$module == null) {
         this.Alternative$lzycompute$1();
      }

      return this.Alternative$module;
   }

   public Trees.Star$ Star() {
      if (this.Star$module == null) {
         this.Star$lzycompute$1();
      }

      return this.Star$module;
   }

   public Trees.Bind$ Bind() {
      if (this.Bind$module == null) {
         this.Bind$lzycompute$1();
      }

      return this.Bind$module;
   }

   public Trees.UnApply$ UnApply() {
      if (this.UnApply$module == null) {
         this.UnApply$lzycompute$1();
      }

      return this.UnApply$module;
   }

   public Trees.ArrayValue$ ArrayValue() {
      if (this.ArrayValue$module == null) {
         this.ArrayValue$lzycompute$1();
      }

      return this.ArrayValue$module;
   }

   public Trees.Function$ Function() {
      if (this.Function$module == null) {
         this.Function$lzycompute$1();
      }

      return this.Function$module;
   }

   public Trees.Assign$ Assign() {
      if (this.Assign$module == null) {
         this.Assign$lzycompute$1();
      }

      return this.Assign$module;
   }

   public Trees.NamedArg$ NamedArg() {
      if (this.NamedArg$module == null) {
         this.NamedArg$lzycompute$1();
      }

      return this.NamedArg$module;
   }

   public Trees.If$ If() {
      if (this.If$module == null) {
         this.If$lzycompute$1();
      }

      return this.If$module;
   }

   public Trees.Match$ Match() {
      if (this.Match$module == null) {
         this.Match$lzycompute$1();
      }

      return this.Match$module;
   }

   public Trees.Return$ Return() {
      if (this.Return$module == null) {
         this.Return$lzycompute$1();
      }

      return this.Return$module;
   }

   public Trees.Try$ Try() {
      if (this.Try$module == null) {
         this.Try$lzycompute$1();
      }

      return this.Try$module;
   }

   public Trees.Throw$ Throw() {
      if (this.Throw$module == null) {
         this.Throw$lzycompute$1();
      }

      return this.Throw$module;
   }

   public Trees.New$ New() {
      if (this.New$module == null) {
         this.New$lzycompute$1();
      }

      return this.New$module;
   }

   public Trees.Typed$ Typed() {
      if (this.Typed$module == null) {
         this.Typed$lzycompute$1();
      }

      return this.Typed$module;
   }

   public Trees.MethodValue$ MethodValue() {
      if (this.MethodValue$module == null) {
         this.MethodValue$lzycompute$1();
      }

      return this.MethodValue$module;
   }

   public Trees.TypeApply$ TypeApply() {
      if (this.TypeApply$module == null) {
         this.TypeApply$lzycompute$1();
      }

      return this.TypeApply$module;
   }

   public Trees.Apply$ Apply() {
      if (this.Apply$module == null) {
         this.Apply$lzycompute$1();
      }

      return this.Apply$module;
   }

   public Trees.ApplyDynamic$ ApplyDynamic() {
      if (this.ApplyDynamic$module == null) {
         this.ApplyDynamic$lzycompute$1();
      }

      return this.ApplyDynamic$module;
   }

   public Trees.Super$ Super() {
      if (this.Super$module == null) {
         this.Super$lzycompute$1();
      }

      return this.Super$module;
   }

   public Trees.This$ This() {
      if (this.This$module == null) {
         this.This$lzycompute$1();
      }

      return this.This$module;
   }

   public Trees.Select$ Select() {
      if (this.Select$module == null) {
         this.Select$lzycompute$1();
      }

      return this.Select$module;
   }

   public Trees.Ident$ Ident() {
      if (this.Ident$module == null) {
         this.Ident$lzycompute$1();
      }

      return this.Ident$module;
   }

   public Trees.ReferenceToBoxed$ ReferenceToBoxed() {
      if (this.ReferenceToBoxed$module == null) {
         this.ReferenceToBoxed$lzycompute$1();
      }

      return this.ReferenceToBoxed$module;
   }

   public Trees.Literal$ Literal() {
      if (this.Literal$module == null) {
         this.Literal$lzycompute$1();
      }

      return this.Literal$module;
   }

   public Trees.Annotated$ Annotated() {
      if (this.Annotated$module == null) {
         this.Annotated$lzycompute$1();
      }

      return this.Annotated$module;
   }

   public Trees.SingletonTypeTree$ SingletonTypeTree() {
      if (this.SingletonTypeTree$module == null) {
         this.SingletonTypeTree$lzycompute$1();
      }

      return this.SingletonTypeTree$module;
   }

   public Trees.SelectFromTypeTree$ SelectFromTypeTree() {
      if (this.SelectFromTypeTree$module == null) {
         this.SelectFromTypeTree$lzycompute$1();
      }

      return this.SelectFromTypeTree$module;
   }

   public Trees.CompoundTypeTree$ CompoundTypeTree() {
      if (this.CompoundTypeTree$module == null) {
         this.CompoundTypeTree$lzycompute$1();
      }

      return this.CompoundTypeTree$module;
   }

   public Trees.AppliedTypeTree$ AppliedTypeTree() {
      if (this.AppliedTypeTree$module == null) {
         this.AppliedTypeTree$lzycompute$1();
      }

      return this.AppliedTypeTree$module;
   }

   public Trees.TypeBoundsTree$ TypeBoundsTree() {
      if (this.TypeBoundsTree$module == null) {
         this.TypeBoundsTree$lzycompute$1();
      }

      return this.TypeBoundsTree$module;
   }

   public Trees.ExistentialTypeTree$ ExistentialTypeTree() {
      if (this.ExistentialTypeTree$module == null) {
         this.ExistentialTypeTree$lzycompute$1();
      }

      return this.ExistentialTypeTree$module;
   }

   public Trees.TypeTree$ TypeTree() {
      if (this.TypeTree$module == null) {
         this.TypeTree$lzycompute$1();
      }

      return this.TypeTree$module;
   }

   public Trees.Modifiers$ Modifiers() {
      if (this.Modifiers$module == null) {
         this.Modifiers$lzycompute$1();
      }

      return this.Modifiers$module;
   }

   public ClassTag ModifiersTag() {
      return this.ModifiersTag;
   }

   public Trees.EmptyTree$ EmptyTree() {
      if (this.EmptyTree$module == null) {
         this.EmptyTree$lzycompute$1();
      }

      return this.EmptyTree$module;
   }

   public Trees.noSelfType$ noSelfType() {
      if (this.noSelfType$module == null) {
         this.noSelfType$lzycompute$1();
      }

      return this.noSelfType$module;
   }

   public Trees.pendingSuperCall$ pendingSuperCall() {
      if (this.pendingSuperCall$module == null) {
         this.pendingSuperCall$lzycompute$1();
      }

      return this.pendingSuperCall$module;
   }

   private Trees.noSelfType$ emptyValDef$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.emptyValDef = this.noSelfType();
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.emptyValDef;
   }

   /** @deprecated */
   public Trees.noSelfType$ emptyValDef() {
      return (this.bitmap$0 & 16) == 0 ? this.emptyValDef$lzycompute() : this.emptyValDef;
   }

   private Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.EmptyTreeTypeSubstituter = Trees.EmptyTreeTypeSubstituter$(this);
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.EmptyTreeTypeSubstituter;
   }

   public Trees.TreeTypeSubstituter EmptyTreeTypeSubstituter() {
      return (this.bitmap$0 & 32) == 0 ? this.EmptyTreeTypeSubstituter$lzycompute() : this.EmptyTreeTypeSubstituter;
   }

   private Trees.Duplicator scala$reflect$internal$Trees$$duplicator$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.scala$reflect$internal$Trees$$duplicator = Trees.scala$reflect$internal$Trees$$duplicator$(this);
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$internal$Trees$$duplicator;
   }

   public Trees.Duplicator scala$reflect$internal$Trees$$duplicator() {
      return (this.bitmap$0 & 64) == 0 ? this.scala$reflect$internal$Trees$$duplicator$lzycompute() : this.scala$reflect$internal$Trees$$duplicator;
   }

   public Trees.duplicateAndResetPos$ duplicateAndResetPos() {
      if (this.duplicateAndResetPos$module == null) {
         this.duplicateAndResetPos$lzycompute$1();
      }

      return this.duplicateAndResetPos$module;
   }

   public Trees.focuser$ scala$reflect$internal$Trees$$focuser() {
      if (this.focuser$module == null) {
         this.scala$reflect$internal$Trees$$focuser$lzycompute$1();
      }

      return this.focuser$module;
   }

   public ReusableInstance scala$reflect$internal$Trees$$onlyChildAccumulator() {
      return this.scala$reflect$internal$Trees$$onlyChildAccumulator;
   }

   public ClassTag AlternativeTag() {
      return this.AlternativeTag;
   }

   public ClassTag AnnotatedTag() {
      return this.AnnotatedTag;
   }

   public ClassTag AppliedTypeTreeTag() {
      return this.AppliedTypeTreeTag;
   }

   public ClassTag ApplyTag() {
      return this.ApplyTag;
   }

   public ClassTag NamedArgTag() {
      return this.NamedArgTag;
   }

   public ClassTag AssignTag() {
      return this.AssignTag;
   }

   public ClassTag BindTag() {
      return this.BindTag;
   }

   public ClassTag BlockTag() {
      return this.BlockTag;
   }

   public ClassTag CaseDefTag() {
      return this.CaseDefTag;
   }

   public ClassTag ClassDefTag() {
      return this.ClassDefTag;
   }

   public ClassTag CompoundTypeTreeTag() {
      return this.CompoundTypeTreeTag;
   }

   public ClassTag DefDefTag() {
      return this.DefDefTag;
   }

   public ClassTag DefTreeTag() {
      return this.DefTreeTag;
   }

   public ClassTag ExistentialTypeTreeTag() {
      return this.ExistentialTypeTreeTag;
   }

   public ClassTag FunctionTag() {
      return this.FunctionTag;
   }

   public ClassTag GenericApplyTag() {
      return this.GenericApplyTag;
   }

   public ClassTag IdentTag() {
      return this.IdentTag;
   }

   public ClassTag IfTag() {
      return this.IfTag;
   }

   public ClassTag ImplDefTag() {
      return this.ImplDefTag;
   }

   public ClassTag ImportSelectorTag() {
      return this.ImportSelectorTag;
   }

   public ClassTag ImportTag() {
      return this.ImportTag;
   }

   public ClassTag LabelDefTag() {
      return this.LabelDefTag;
   }

   public ClassTag LiteralTag() {
      return this.LiteralTag;
   }

   public ClassTag MatchTag() {
      return this.MatchTag;
   }

   public ClassTag MemberDefTag() {
      return this.MemberDefTag;
   }

   public ClassTag ModuleDefTag() {
      return this.ModuleDefTag;
   }

   public ClassTag NameTreeTag() {
      return this.NameTreeTag;
   }

   public ClassTag NewTag() {
      return this.NewTag;
   }

   public ClassTag PackageDefTag() {
      return this.PackageDefTag;
   }

   public ClassTag ReferenceToBoxedTag() {
      return this.ReferenceToBoxedTag;
   }

   public ClassTag RefTreeTag() {
      return this.RefTreeTag;
   }

   public ClassTag ReturnTag() {
      return this.ReturnTag;
   }

   public ClassTag SelectFromTypeTreeTag() {
      return this.SelectFromTypeTreeTag;
   }

   public ClassTag SelectTag() {
      return this.SelectTag;
   }

   public ClassTag SingletonTypeTreeTag() {
      return this.SingletonTypeTreeTag;
   }

   public ClassTag StarTag() {
      return this.StarTag;
   }

   public ClassTag SuperTag() {
      return this.SuperTag;
   }

   public ClassTag SymTreeTag() {
      return this.SymTreeTag;
   }

   public ClassTag TemplateTag() {
      return this.TemplateTag;
   }

   public ClassTag TermTreeTag() {
      return this.TermTreeTag;
   }

   public ClassTag ThisTag() {
      return this.ThisTag;
   }

   public ClassTag ThrowTag() {
      return this.ThrowTag;
   }

   public ClassTag TreeTag() {
      return this.TreeTag;
   }

   public ClassTag TryTag() {
      return this.TryTag;
   }

   public ClassTag TypTreeTag() {
      return this.TypTreeTag;
   }

   public ClassTag TypeApplyTag() {
      return this.TypeApplyTag;
   }

   public ClassTag TypeBoundsTreeTag() {
      return this.TypeBoundsTreeTag;
   }

   public ClassTag TypeDefTag() {
      return this.TypeDefTag;
   }

   public ClassTag TypeTreeTag() {
      return this.TypeTreeTag;
   }

   public ClassTag TypedTag() {
      return this.TypedTag;
   }

   public ClassTag UnApplyTag() {
      return this.UnApplyTag;
   }

   public ClassTag ValDefTag() {
      return this.ValDefTag;
   }

   public ClassTag ValOrDefDefTag() {
      return this.ValOrDefDefTag;
   }

   public void scala$reflect$internal$Trees$_setter_$ModifiersTag_$eq(final ClassTag x$1) {
      this.ModifiersTag = x$1;
   }

   public final void scala$reflect$internal$Trees$_setter_$scala$reflect$internal$Trees$$onlyChildAccumulator_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$Trees$$onlyChildAccumulator = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$AlternativeTag_$eq(final ClassTag x$1) {
      this.AlternativeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$AnnotatedTag_$eq(final ClassTag x$1) {
      this.AnnotatedTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$AppliedTypeTreeTag_$eq(final ClassTag x$1) {
      this.AppliedTypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ApplyTag_$eq(final ClassTag x$1) {
      this.ApplyTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$NamedArgTag_$eq(final ClassTag x$1) {
      this.NamedArgTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$AssignTag_$eq(final ClassTag x$1) {
      this.AssignTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$BindTag_$eq(final ClassTag x$1) {
      this.BindTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$BlockTag_$eq(final ClassTag x$1) {
      this.BlockTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$CaseDefTag_$eq(final ClassTag x$1) {
      this.CaseDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ClassDefTag_$eq(final ClassTag x$1) {
      this.ClassDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$CompoundTypeTreeTag_$eq(final ClassTag x$1) {
      this.CompoundTypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$DefDefTag_$eq(final ClassTag x$1) {
      this.DefDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$DefTreeTag_$eq(final ClassTag x$1) {
      this.DefTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ExistentialTypeTreeTag_$eq(final ClassTag x$1) {
      this.ExistentialTypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$FunctionTag_$eq(final ClassTag x$1) {
      this.FunctionTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$GenericApplyTag_$eq(final ClassTag x$1) {
      this.GenericApplyTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$IdentTag_$eq(final ClassTag x$1) {
      this.IdentTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$IfTag_$eq(final ClassTag x$1) {
      this.IfTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ImplDefTag_$eq(final ClassTag x$1) {
      this.ImplDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ImportSelectorTag_$eq(final ClassTag x$1) {
      this.ImportSelectorTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ImportTag_$eq(final ClassTag x$1) {
      this.ImportTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$LabelDefTag_$eq(final ClassTag x$1) {
      this.LabelDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$LiteralTag_$eq(final ClassTag x$1) {
      this.LiteralTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$MatchTag_$eq(final ClassTag x$1) {
      this.MatchTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$MemberDefTag_$eq(final ClassTag x$1) {
      this.MemberDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ModuleDefTag_$eq(final ClassTag x$1) {
      this.ModuleDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$NameTreeTag_$eq(final ClassTag x$1) {
      this.NameTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$NewTag_$eq(final ClassTag x$1) {
      this.NewTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$PackageDefTag_$eq(final ClassTag x$1) {
      this.PackageDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ReferenceToBoxedTag_$eq(final ClassTag x$1) {
      this.ReferenceToBoxedTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$RefTreeTag_$eq(final ClassTag x$1) {
      this.RefTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ReturnTag_$eq(final ClassTag x$1) {
      this.ReturnTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$SelectFromTypeTreeTag_$eq(final ClassTag x$1) {
      this.SelectFromTypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$SelectTag_$eq(final ClassTag x$1) {
      this.SelectTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$SingletonTypeTreeTag_$eq(final ClassTag x$1) {
      this.SingletonTypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$StarTag_$eq(final ClassTag x$1) {
      this.StarTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$SuperTag_$eq(final ClassTag x$1) {
      this.SuperTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$SymTreeTag_$eq(final ClassTag x$1) {
      this.SymTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TemplateTag_$eq(final ClassTag x$1) {
      this.TemplateTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TermTreeTag_$eq(final ClassTag x$1) {
      this.TermTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ThisTag_$eq(final ClassTag x$1) {
      this.ThisTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ThrowTag_$eq(final ClassTag x$1) {
      this.ThrowTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TreeTag_$eq(final ClassTag x$1) {
      this.TreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TryTag_$eq(final ClassTag x$1) {
      this.TryTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypTreeTag_$eq(final ClassTag x$1) {
      this.TypTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypeApplyTag_$eq(final ClassTag x$1) {
      this.TypeApplyTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypeBoundsTreeTag_$eq(final ClassTag x$1) {
      this.TypeBoundsTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypeDefTag_$eq(final ClassTag x$1) {
      this.TypeDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypeTreeTag_$eq(final ClassTag x$1) {
      this.TypeTreeTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$TypedTag_$eq(final ClassTag x$1) {
      this.TypedTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$UnApplyTag_$eq(final ClassTag x$1) {
      this.UnApplyTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ValDefTag_$eq(final ClassTag x$1) {
      this.ValDefTag = x$1;
   }

   public void scala$reflect$internal$Trees$_setter_$ValOrDefDefTag_$eq(final ClassTag x$1) {
      this.ValOrDefDefTag = x$1;
   }

   public List scala$reflect$internal$AnnotationCheckers$$annotationCheckers() {
      return this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers;
   }

   public void scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(final List x$1) {
      this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers = x$1;
   }

   public ClassTag JavaArgumentTag() {
      return this.JavaArgumentTag;
   }

   public AnnotationInfos.UnmappableAnnotArg$ UnmappableAnnotArg() {
      if (this.UnmappableAnnotArg$module == null) {
         this.UnmappableAnnotArg$lzycompute$1();
      }

      return this.UnmappableAnnotArg$module;
   }

   public AnnotationInfos.LiteralAnnotArg$ LiteralAnnotArg() {
      if (this.LiteralAnnotArg$module == null) {
         this.LiteralAnnotArg$lzycompute$1();
      }

      return this.LiteralAnnotArg$module;
   }

   public AnnotationInfos.ArrayAnnotArg$ ArrayAnnotArg() {
      if (this.ArrayAnnotArg$module == null) {
         this.ArrayAnnotArg$lzycompute$1();
      }

      return this.ArrayAnnotArg$module;
   }

   public AnnotationInfos.NestedAnnotArg$ NestedAnnotArg() {
      if (this.NestedAnnotArg$module == null) {
         this.NestedAnnotArg$lzycompute$1();
      }

      return this.NestedAnnotArg$module;
   }

   public AnnotationInfos.AnnotationInfo$ AnnotationInfo() {
      if (this.AnnotationInfo$module == null) {
         this.AnnotationInfo$lzycompute$1();
      }

      return this.AnnotationInfo$module;
   }

   public AnnotationInfos.Annotation$ Annotation() {
      if (this.Annotation$module == null) {
         this.Annotation$lzycompute$1();
      }

      return this.Annotation$module;
   }

   public ClassTag AnnotationTag() {
      return this.AnnotationTag;
   }

   public AnnotationInfos.UnmappableAnnotation$ UnmappableAnnotation() {
      if (this.UnmappableAnnotation$module == null) {
         this.UnmappableAnnotation$lzycompute$1();
      }

      return this.UnmappableAnnotation$module;
   }

   public AnnotationInfos.ThrownException$ ThrownException() {
      if (this.ThrownException$module == null) {
         this.ThrownException$lzycompute$1();
      }

      return this.ThrownException$module;
   }

   public void scala$reflect$internal$AnnotationInfos$_setter_$JavaArgumentTag_$eq(final ClassTag x$1) {
      this.JavaArgumentTag = x$1;
   }

   public void scala$reflect$internal$AnnotationInfos$_setter_$AnnotationTag_$eq(final ClassTag x$1) {
      this.AnnotationTag = x$1;
   }

   public StdNames.compactify$ scala$reflect$internal$StdNames$$compactify() {
      if (this.compactify$module == null) {
         this.scala$reflect$internal$StdNames$$compactify$lzycompute$1();
      }

      return this.compactify$module;
   }

   private StdNames.tpnme$ typeNames$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.typeNames = this.tpnme();
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.typeNames;
   }

   public StdNames.tpnme$ typeNames() {
      return (this.bitmap$0 & 128) == 0 ? this.typeNames$lzycompute() : this.typeNames;
   }

   public StdNames.tpnme$ tpnme() {
      if (this.tpnme$module == null) {
         this.tpnme$lzycompute$1();
      }

      return this.tpnme$module;
   }

   public StdNames.fulltpnme$ fulltpnme() {
      if (this.fulltpnme$module == null) {
         this.fulltpnme$lzycompute$1();
      }

      return this.fulltpnme$module;
   }

   public StdNames.binarynme$ binarynme() {
      if (this.binarynme$module == null) {
         this.binarynme$lzycompute$1();
      }

      return this.binarynme$module;
   }

   public StdNames.JavaKeywords javanme() {
      return this.javanme;
   }

   private StdNames.nme$ termNames$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.termNames = this.nme();
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.termNames;
   }

   public StdNames.nme$ termNames() {
      return (this.bitmap$0 & 256) == 0 ? this.termNames$lzycompute() : this.termNames;
   }

   public StdNames.nme$ nme() {
      if (this.nme$module == null) {
         this.nme$lzycompute$1();
      }

      return this.nme$module;
   }

   private StdNames.SymbolNames sn$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.sn = StdNames.sn$(this);
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.sn;
   }

   public StdNames.SymbolNames sn() {
      return (this.bitmap$0 & 512) == 0 ? this.sn$lzycompute() : this.sn;
   }

   public void scala$reflect$internal$StdNames$_setter_$javanme_$eq(final StdNames.JavaKeywords x$1) {
      this.javanme = x$1;
   }

   public Transforms.Lazy scala$reflect$internal$transform$Transforms$$uncurryLazy() {
      return this.scala$reflect$internal$transform$Transforms$$uncurryLazy;
   }

   public Transforms.Lazy scala$reflect$internal$transform$Transforms$$erasureLazy() {
      return this.scala$reflect$internal$transform$Transforms$$erasureLazy;
   }

   public Transforms.Lazy scala$reflect$internal$transform$Transforms$$postErasureLazy() {
      return this.scala$reflect$internal$transform$Transforms$$postErasureLazy;
   }

   public final void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq(final Transforms.Lazy x$1) {
      this.scala$reflect$internal$transform$Transforms$$uncurryLazy = x$1;
   }

   public final void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq(final Transforms.Lazy x$1) {
      this.scala$reflect$internal$transform$Transforms$$erasureLazy = x$1;
   }

   public final void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq(final Transforms.Lazy x$1) {
      this.scala$reflect$internal$transform$Transforms$$postErasureLazy = x$1;
   }

   private BaseTypeSeqs.BaseTypeSeq undetBaseTypeSeq$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            this.undetBaseTypeSeq = BaseTypeSeqs.undetBaseTypeSeq$(this);
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.undetBaseTypeSeq;
   }

   public BaseTypeSeqs.BaseTypeSeq undetBaseTypeSeq() {
      return (this.bitmap$0 & 1024) == 0 ? this.undetBaseTypeSeq$lzycompute() : this.undetBaseTypeSeq;
   }

   public Throwable CyclicInheritance() {
      return this.CyclicInheritance;
   }

   public void scala$reflect$internal$BaseTypeSeqs$_setter_$CyclicInheritance_$eq(final Throwable x$1) {
      this.CyclicInheritance = x$1;
   }

   public Constants.Constant$ Constant() {
      if (this.Constant$module == null) {
         this.Constant$lzycompute$1();
      }

      return this.Constant$module;
   }

   public ClassTag ConstantTag() {
      return this.ConstantTag;
   }

   public void scala$reflect$internal$Constants$_setter_$ConstantTag_$eq(final ClassTag x$1) {
      this.ConstantTag = x$1;
   }

   public Definitions.definitions$ definitions() {
      if (this.definitions$module == null) {
         this.definitions$lzycompute$1();
      }

      return this.definitions$module;
   }

   public int scopeCount() {
      return this.scopeCount;
   }

   public void scopeCount_$eq(final int x$1) {
      this.scopeCount = x$1;
   }

   public Scopes.LookupSucceeded$ LookupSucceeded() {
      if (this.LookupSucceeded$module == null) {
         this.LookupSucceeded$lzycompute$1();
      }

      return this.LookupSucceeded$module;
   }

   public Scopes.LookupAmbiguous$ LookupAmbiguous() {
      if (this.LookupAmbiguous$module == null) {
         this.LookupAmbiguous$lzycompute$1();
      }

      return this.LookupAmbiguous$module;
   }

   public Scopes.LookupInaccessible$ LookupInaccessible() {
      if (this.LookupInaccessible$module == null) {
         this.LookupInaccessible$lzycompute$1();
      }

      return this.LookupInaccessible$module;
   }

   public Scopes.LookupNotFound$ LookupNotFound() {
      if (this.LookupNotFound$module == null) {
         this.LookupNotFound$lzycompute$1();
      }

      return this.LookupNotFound$module;
   }

   public Scopes.Scope$ Scope() {
      if (this.Scope$module == null) {
         this.Scope$lzycompute$1();
      }

      return this.Scope$module;
   }

   public ClassTag ScopeTag() {
      return this.ScopeTag;
   }

   public ClassTag MemberScopeTag() {
      return this.MemberScopeTag;
   }

   public Scopes.EmptyScope$ EmptyScope() {
      if (this.EmptyScope$module == null) {
         this.EmptyScope$lzycompute$1();
      }

      return this.EmptyScope$module;
   }

   public void scala$reflect$internal$Scopes$_setter_$ScopeTag_$eq(final ClassTag x$1) {
      this.ScopeTag = x$1;
   }

   public void scala$reflect$internal$Scopes$_setter_$MemberScopeTag_$eq(final ClassTag x$1) {
      this.MemberScopeTag = x$1;
   }

   public ClassTag FlagSetTag() {
      return this.FlagSetTag;
   }

   public long NoFlags() {
      return this.NoFlags;
   }

   public FlagSets.Flag$ Flag() {
      if (this.Flag$module == null) {
         this.Flag$lzycompute$1();
      }

      return this.Flag$module;
   }

   public void scala$reflect$internal$FlagSets$_setter_$FlagSetTag_$eq(final ClassTag x$1) {
      this.FlagSetTag = x$1;
   }

   public void scala$reflect$internal$FlagSets$_setter_$NoFlags_$eq(final long x$1) {
      this.NoFlags = x$1;
   }

   public Kinds.KindErrors$ KindErrors() {
      if (this.KindErrors$module == null) {
         this.KindErrors$lzycompute$1();
      }

      return this.KindErrors$module;
   }

   public Kinds.KindErrors NoKindErrors() {
      return this.NoKindErrors;
   }

   public Kinds.Kind$ Kind() {
      if (this.Kind$module == null) {
         this.Kind$lzycompute$1();
      }

      return this.Kind$module;
   }

   public Kinds.ProperTypeKind$ ProperTypeKind() {
      if (this.ProperTypeKind$module == null) {
         this.ProperTypeKind$lzycompute$1();
      }

      return this.ProperTypeKind$module;
   }

   public Kinds.TypeConKind$ TypeConKind() {
      if (this.TypeConKind$module == null) {
         this.TypeConKind$lzycompute$1();
      }

      return this.TypeConKind$module;
   }

   public Kinds.inferKind$ inferKind() {
      if (this.inferKind$module == null) {
         this.inferKind$lzycompute$1();
      }

      return this.inferKind$module;
   }

   public void scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq(final Kinds.KindErrors x$1) {
      this.NoKindErrors = x$1;
   }

   public ReusableInstance scala$reflect$internal$Variances$$varianceInTypeCache() {
      return this.scala$reflect$internal$Variances$$varianceInTypeCache;
   }

   public final void scala$reflect$internal$Variances$_setter_$scala$reflect$internal$Variances$$varianceInTypeCache_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$Variances$$varianceInTypeCache = x$1;
   }

   public boolean scala$reflect$internal$Types$$explainSwitch() {
      return this.scala$reflect$internal$Types$$explainSwitch;
   }

   public void scala$reflect$internal$Types$$explainSwitch_$eq(final boolean x$1) {
      this.scala$reflect$internal$Types$$explainSwitch = x$1;
   }

   public final Set scala$reflect$internal$Types$$emptySymbolSet() {
      return this.scala$reflect$internal$Types$$emptySymbolSet;
   }

   public final boolean scala$reflect$internal$Types$$breakCycles() {
      return this.scala$reflect$internal$Types$$breakCycles;
   }

   public final boolean scala$reflect$internal$Types$$sharperSkolems() {
      return this.scala$reflect$internal$Types$$sharperSkolems;
   }

   public Types.substTypeMapCache$ scala$reflect$internal$Types$$substTypeMapCache() {
      if (this.substTypeMapCache$module == null) {
         this.scala$reflect$internal$Types$$substTypeMapCache$lzycompute$1();
      }

      return this.substTypeMapCache$module;
   }

   public int scala$reflect$internal$Types$$_skolemizationLevel() {
      return this.scala$reflect$internal$Types$$_skolemizationLevel;
   }

   public void scala$reflect$internal$Types$$_skolemizationLevel_$eq(final int x$1) {
      this.scala$reflect$internal$Types$$_skolemizationLevel = x$1;
   }

   public WeakHashMap scala$reflect$internal$Types$$_intersectionWitness() {
      return this.scala$reflect$internal$Types$$_intersectionWitness;
   }

   public Types.UnmappableTree$ UnmappableTree() {
      if (this.UnmappableTree$module == null) {
         this.UnmappableTree$lzycompute$1();
      }

      return this.UnmappableTree$module;
   }

   public Types.ErrorType$ ErrorType() {
      if (this.ErrorType$module == null) {
         this.ErrorType$lzycompute$1();
      }

      return this.ErrorType$module;
   }

   public Types.WildcardType$ WildcardType() {
      if (this.WildcardType$module == null) {
         this.WildcardType$lzycompute$1();
      }

      return this.WildcardType$module;
   }

   public Types.BoundedWildcardType$ BoundedWildcardType() {
      if (this.BoundedWildcardType$module == null) {
         this.BoundedWildcardType$lzycompute$1();
      }

      return this.BoundedWildcardType$module;
   }

   public Types.OverloadedArgProto$ OverloadedArgProto() {
      if (this.OverloadedArgProto$module == null) {
         this.OverloadedArgProto$lzycompute$1();
      }

      return this.OverloadedArgProto$module;
   }

   public Types.NoType$ NoType() {
      if (this.NoType$module == null) {
         this.NoType$lzycompute$1();
      }

      return this.NoType$module;
   }

   public Types.NoPrefix$ NoPrefix() {
      if (this.NoPrefix$module == null) {
         this.NoPrefix$lzycompute$1();
      }

      return this.NoPrefix$module;
   }

   public Types.ThisType$ ThisType() {
      if (this.ThisType$module == null) {
         this.ThisType$lzycompute$1();
      }

      return this.ThisType$module;
   }

   public Types.SingleType$ SingleType() {
      if (this.SingleType$module == null) {
         this.SingleType$lzycompute$1();
      }

      return this.SingleType$module;
   }

   public Types.SuperType$ SuperType() {
      if (this.SuperType$module == null) {
         this.SuperType$lzycompute$1();
      }

      return this.SuperType$module;
   }

   public Types.TypeBounds$ TypeBounds() {
      if (this.TypeBounds$module == null) {
         this.TypeBounds$lzycompute$1();
      }

      return this.TypeBounds$module;
   }

   public Types.CompoundType$ CompoundType() {
      if (this.CompoundType$module == null) {
         this.CompoundType$lzycompute$1();
      }

      return this.CompoundType$module;
   }

   public Types.RefinedType$ RefinedType() {
      if (this.RefinedType$module == null) {
         this.RefinedType$lzycompute$1();
      }

      return this.RefinedType$module;
   }

   public Types.ClassInfoType$ ClassInfoType() {
      if (this.ClassInfoType$module == null) {
         this.ClassInfoType$lzycompute$1();
      }

      return this.ClassInfoType$module;
   }

   public Types.ConstantType$ ConstantType() {
      if (this.ConstantType$module == null) {
         this.ConstantType$lzycompute$1();
      }

      return this.ConstantType$module;
   }

   public Types.FoldableConstantType$ FoldableConstantType() {
      if (this.FoldableConstantType$module == null) {
         this.FoldableConstantType$lzycompute$1();
      }

      return this.FoldableConstantType$module;
   }

   public Types.LiteralType$ LiteralType() {
      if (this.LiteralType$module == null) {
         this.LiteralType$lzycompute$1();
      }

      return this.LiteralType$module;
   }

   public Types.TypeRef$ TypeRef() {
      if (this.TypeRef$module == null) {
         this.TypeRef$lzycompute$1();
      }

      return this.TypeRef$module;
   }

   public Types.MethodType$ MethodType() {
      if (this.MethodType$module == null) {
         this.MethodType$lzycompute$1();
      }

      return this.MethodType$module;
   }

   public Types.NullaryMethodType$ NullaryMethodType() {
      if (this.NullaryMethodType$module == null) {
         this.NullaryMethodType$lzycompute$1();
      }

      return this.NullaryMethodType$module;
   }

   public Types.PolyType$ PolyType() {
      if (this.PolyType$module == null) {
         this.PolyType$lzycompute$1();
      }

      return this.PolyType$module;
   }

   public Types.ExistentialType$ ExistentialType() {
      if (this.ExistentialType$module == null) {
         this.ExistentialType$lzycompute$1();
      }

      return this.ExistentialType$module;
   }

   public Types.OverloadedType$ OverloadedType() {
      if (this.OverloadedType$module == null) {
         this.OverloadedType$lzycompute$1();
      }

      return this.OverloadedType$module;
   }

   public Types.ImportType$ ImportType() {
      if (this.ImportType$module == null) {
         this.ImportType$lzycompute$1();
      }

      return this.ImportType$module;
   }

   public Types.AntiPolyType$ AntiPolyType() {
      if (this.AntiPolyType$module == null) {
         this.AntiPolyType$lzycompute$1();
      }

      return this.AntiPolyType$module;
   }

   public Types.HasTypeMember$ HasTypeMember() {
      if (this.HasTypeMember$module == null) {
         this.HasTypeMember$lzycompute$1();
      }

      return this.HasTypeMember$module;
   }

   public Types.ArrayTypeRef$ ArrayTypeRef() {
      if (this.ArrayTypeRef$module == null) {
         this.ArrayTypeRef$lzycompute$1();
      }

      return this.ArrayTypeRef$module;
   }

   public Types.TypeVar$ TypeVar() {
      if (this.TypeVar$module == null) {
         this.TypeVar$lzycompute$1();
      }

      return this.TypeVar$module;
   }

   public Types.AnnotatedType$ AnnotatedType() {
      if (this.AnnotatedType$module == null) {
         this.AnnotatedType$lzycompute$1();
      }

      return this.AnnotatedType$module;
   }

   public Types.StaticallyAnnotatedType$ StaticallyAnnotatedType() {
      if (this.StaticallyAnnotatedType$module == null) {
         this.StaticallyAnnotatedType$lzycompute$1();
      }

      return this.StaticallyAnnotatedType$module;
   }

   public Types.NamedType$ NamedType() {
      if (this.NamedType$module == null) {
         this.NamedType$lzycompute$1();
      }

      return this.NamedType$module;
   }

   public Types.RepeatedType$ RepeatedType() {
      if (this.RepeatedType$module == null) {
         this.RepeatedType$lzycompute$1();
      }

      return this.RepeatedType$module;
   }

   public Types.ErasedValueType$ ErasedValueType() {
      if (this.ErasedValueType$module == null) {
         this.ErasedValueType$lzycompute$1();
      }

      return this.ErasedValueType$module;
   }

   public ReusableInstance scala$reflect$internal$Types$$copyRefinedTypeSSM() {
      return this.scala$reflect$internal$Types$$copyRefinedTypeSSM;
   }

   public Types.GenPolyType$ GenPolyType() {
      if (this.GenPolyType$module == null) {
         this.GenPolyType$lzycompute$1();
      }

      return this.GenPolyType$module;
   }

   public int scala$reflect$internal$Types$$initialUniquesCapacity() {
      return this.scala$reflect$internal$Types$$initialUniquesCapacity;
   }

   public WeakHashSet scala$reflect$internal$Types$$uniques() {
      return this.scala$reflect$internal$Types$$uniques;
   }

   public void scala$reflect$internal$Types$$uniques_$eq(final WeakHashSet x$1) {
      this.scala$reflect$internal$Types$$uniques = x$1;
   }

   public int scala$reflect$internal$Types$$uniqueRunId() {
      return this.scala$reflect$internal$Types$$uniqueRunId;
   }

   public void scala$reflect$internal$Types$$uniqueRunId_$eq(final int x$1) {
      this.scala$reflect$internal$Types$$uniqueRunId = x$1;
   }

   public Types.unwrapToClass$ unwrapToClass() {
      if (this.unwrapToClass$module == null) {
         this.unwrapToClass$lzycompute$1();
      }

      return this.unwrapToClass$module;
   }

   public Types.unwrapToStableClass$ unwrapToStableClass() {
      if (this.unwrapToStableClass$module == null) {
         this.unwrapToStableClass$lzycompute$1();
      }

      return this.unwrapToStableClass$module;
   }

   public Types.unwrapWrapperTypes$ unwrapWrapperTypes() {
      if (this.unwrapWrapperTypes$module == null) {
         this.unwrapWrapperTypes$lzycompute$1();
      }

      return this.unwrapWrapperTypes$module;
   }

   public Types.MissingAliasControl missingAliasException() {
      return this.missingAliasException;
   }

   public int scala$reflect$internal$Types$$_basetypeRecursions() {
      return this.scala$reflect$internal$Types$$_basetypeRecursions;
   }

   public void scala$reflect$internal$Types$$_basetypeRecursions_$eq(final int x$1) {
      this.scala$reflect$internal$Types$$_basetypeRecursions = x$1;
   }

   public HashSet scala$reflect$internal$Types$$_pendingBaseTypes() {
      return this.scala$reflect$internal$Types$$_pendingBaseTypes;
   }

   public Types.RecoverableCyclicReference$ RecoverableCyclicReference() {
      if (this.RecoverableCyclicReference$module == null) {
         this.RecoverableCyclicReference$lzycompute$1();
      }

      return this.RecoverableCyclicReference$module;
   }

   public String scala$reflect$internal$Types$$_indent() {
      return this.scala$reflect$internal$Types$$_indent;
   }

   public void scala$reflect$internal$Types$$_indent_$eq(final String x$1) {
      this.scala$reflect$internal$Types$$_indent = x$1;
   }

   public Set shorthands() {
      return this.shorthands;
   }

   public Function1 typeContainsTypeVar() {
      return this.typeContainsTypeVar;
   }

   public Function1 typeIsSubTypeOfSerializable() {
      return this.typeIsSubTypeOfSerializable;
   }

   public Function1 typeIsHigherKinded() {
      return this.typeIsHigherKinded;
   }

   public ClassTag AnnotatedTypeTag() {
      return this.AnnotatedTypeTag;
   }

   public ClassTag BoundedWildcardTypeTag() {
      return this.BoundedWildcardTypeTag;
   }

   public ClassTag ClassInfoTypeTag() {
      return this.ClassInfoTypeTag;
   }

   public ClassTag CompoundTypeTag() {
      return this.CompoundTypeTag;
   }

   public ClassTag ConstantTypeTag() {
      return this.ConstantTypeTag;
   }

   public ClassTag ExistentialTypeTag() {
      return this.ExistentialTypeTag;
   }

   public ClassTag MethodTypeTag() {
      return this.MethodTypeTag;
   }

   public ClassTag NullaryMethodTypeTag() {
      return this.NullaryMethodTypeTag;
   }

   public ClassTag PolyTypeTag() {
      return this.PolyTypeTag;
   }

   public ClassTag RefinedTypeTag() {
      return this.RefinedTypeTag;
   }

   public ClassTag SingletonTypeTag() {
      return this.SingletonTypeTag;
   }

   public ClassTag SingleTypeTag() {
      return this.SingleTypeTag;
   }

   public ClassTag SuperTypeTag() {
      return this.SuperTypeTag;
   }

   public ClassTag ThisTypeTag() {
      return this.ThisTypeTag;
   }

   public ClassTag TypeBoundsTag() {
      return this.TypeBoundsTag;
   }

   public ClassTag TypeRefTag() {
      return this.TypeRefTag;
   }

   public ClassTag TypeTagg() {
      return this.TypeTagg;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$emptySymbolSet_$eq(final Set x$1) {
      this.scala$reflect$internal$Types$$emptySymbolSet = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$breakCycles_$eq(final boolean x$1) {
      this.scala$reflect$internal$Types$$breakCycles = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$sharperSkolems_$eq(final boolean x$1) {
      this.scala$reflect$internal$Types$$sharperSkolems = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_intersectionWitness_$eq(final WeakHashMap x$1) {
      this.scala$reflect$internal$Types$$_intersectionWitness = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$copyRefinedTypeSSM_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$Types$$copyRefinedTypeSSM = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$initialUniquesCapacity_$eq(final int x$1) {
      this.scala$reflect$internal$Types$$initialUniquesCapacity = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$missingAliasException_$eq(final Types.MissingAliasControl x$1) {
      this.missingAliasException = x$1;
   }

   public final void scala$reflect$internal$Types$_setter_$scala$reflect$internal$Types$$_pendingBaseTypes_$eq(final HashSet x$1) {
      this.scala$reflect$internal$Types$$_pendingBaseTypes = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$shorthands_$eq(final Set x$1) {
      this.shorthands = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$typeContainsTypeVar_$eq(final Function1 x$1) {
      this.typeContainsTypeVar = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$typeIsSubTypeOfSerializable_$eq(final Function1 x$1) {
      this.typeIsSubTypeOfSerializable = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$typeIsHigherKinded_$eq(final Function1 x$1) {
      this.typeIsHigherKinded = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$AnnotatedTypeTag_$eq(final ClassTag x$1) {
      this.AnnotatedTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$BoundedWildcardTypeTag_$eq(final ClassTag x$1) {
      this.BoundedWildcardTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$ClassInfoTypeTag_$eq(final ClassTag x$1) {
      this.ClassInfoTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$CompoundTypeTag_$eq(final ClassTag x$1) {
      this.CompoundTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$ConstantTypeTag_$eq(final ClassTag x$1) {
      this.ConstantTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$ExistentialTypeTag_$eq(final ClassTag x$1) {
      this.ExistentialTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$MethodTypeTag_$eq(final ClassTag x$1) {
      this.MethodTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$NullaryMethodTypeTag_$eq(final ClassTag x$1) {
      this.NullaryMethodTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$PolyTypeTag_$eq(final ClassTag x$1) {
      this.PolyTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$RefinedTypeTag_$eq(final ClassTag x$1) {
      this.RefinedTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$SingletonTypeTag_$eq(final ClassTag x$1) {
      this.SingletonTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$SingleTypeTag_$eq(final ClassTag x$1) {
      this.SingleTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$SuperTypeTag_$eq(final ClassTag x$1) {
      this.SuperTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$ThisTypeTag_$eq(final ClassTag x$1) {
      this.ThisTypeTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$TypeBoundsTag_$eq(final ClassTag x$1) {
      this.TypeBoundsTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$TypeRefTag_$eq(final ClassTag x$1) {
      this.TypeRefTag = x$1;
   }

   public void scala$reflect$internal$Types$_setter_$TypeTagg_$eq(final ClassTag x$1) {
      this.TypeTagg = x$1;
   }

   public ReusableInstance findMemberInstance() {
      return this.findMemberInstance;
   }

   public void scala$reflect$internal$tpe$FindMembers$_setter_$findMemberInstance_$eq(final ReusableInstance x$1) {
      this.findMemberInstance = x$1;
   }

   private TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2048) == 0) {
            this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog = TypeConstraints.scala$reflect$internal$tpe$TypeConstraints$$_undoLog$(this);
            this.bitmap$0 |= 2048;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
   }

   public TypeConstraints.UndoLog scala$reflect$internal$tpe$TypeConstraints$$_undoLog() {
      return (this.bitmap$0 & 2048) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$_undoLog;
   }

   private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4096) == 0) {
            this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound = TypeConstraints.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$(this);
            this.bitmap$0 |= 4096;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
   }

   public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericLoBound() {
      return (this.bitmap$0 & 4096) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$numericLoBound;
   }

   private Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8192) == 0) {
            this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound = TypeConstraints.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$(this);
            this.bitmap$0 |= 8192;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
   }

   public Types.Type scala$reflect$internal$tpe$TypeConstraints$$numericHiBound() {
      return (this.bitmap$0 & 8192) == 0 ? this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound$lzycompute() : this.scala$reflect$internal$tpe$TypeConstraints$$numericHiBound;
   }

   public TypeConstraints.TypeConstraint$ TypeConstraint() {
      if (this.TypeConstraint$module == null) {
         this.TypeConstraint$lzycompute$1();
      }

      return this.TypeConstraint$module;
   }

   public ReusableInstance scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances() {
      return this.scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances;
   }

   public final void scala$reflect$internal$tpe$TypeConstraints$_setter_$scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$tpe$TypeConstraints$$containsCollectorInstances = x$1;
   }

   public TypeMaps.normalizeAliases$ normalizeAliases() {
      if (this.normalizeAliases$module == null) {
         this.normalizeAliases$lzycompute$1();
      }

      return this.normalizeAliases$module;
   }

   public TypeMaps.dropSingletonType$ dropSingletonType() {
      if (this.dropSingletonType$module == null) {
         this.dropSingletonType$lzycompute$1();
      }

      return this.dropSingletonType$module;
   }

   public TypeMaps.abstractTypesToBounds$ abstractTypesToBounds() {
      if (this.abstractTypesToBounds$module == null) {
         this.abstractTypesToBounds$lzycompute$1();
      }

      return this.abstractTypesToBounds$module;
   }

   public TypeMaps.dropIllegalStarTypes$ dropIllegalStarTypes() {
      if (this.dropIllegalStarTypes$module == null) {
         this.dropIllegalStarTypes$lzycompute$1();
      }

      return this.dropIllegalStarTypes$module;
   }

   public TypeMaps.wildcardExtrapolation$ wildcardExtrapolation() {
      if (this.wildcardExtrapolation$module == null) {
         this.wildcardExtrapolation$lzycompute$1();
      }

      return this.wildcardExtrapolation$module;
   }

   public TypeMaps.SubstSymMap$ SubstSymMap() {
      if (this.SubstSymMap$module == null) {
         this.SubstSymMap$lzycompute$1();
      }

      return this.SubstSymMap$module;
   }

   public TypeMaps.IsDependentCollector$ IsDependentCollector() {
      if (this.IsDependentCollector$module == null) {
         this.IsDependentCollector$lzycompute$1();
      }

      return this.IsDependentCollector$module;
   }

   public TypeMaps.ApproximateDependentMap$ ApproximateDependentMap() {
      if (this.ApproximateDependentMap$module == null) {
         this.ApproximateDependentMap$lzycompute$1();
      }

      return this.ApproximateDependentMap$module;
   }

   public TypeMaps.identityTypeMap$ identityTypeMap() {
      if (this.identityTypeMap$module == null) {
         this.identityTypeMap$lzycompute$1();
      }

      return this.identityTypeMap$module;
   }

   public TypeMaps.typeVarToOriginMap$ typeVarToOriginMap() {
      if (this.typeVarToOriginMap$module == null) {
         this.typeVarToOriginMap$lzycompute$1();
      }

      return this.typeVarToOriginMap$module;
   }

   public TypeMaps.ErroneousCollector$ ErroneousCollector() {
      if (this.ErroneousCollector$module == null) {
         this.ErroneousCollector$lzycompute$1();
      }

      return this.ErroneousCollector$module;
   }

   public TypeMaps.adaptToNewRunMap$ adaptToNewRunMap() {
      if (this.adaptToNewRunMap$module == null) {
         this.adaptToNewRunMap$lzycompute$1();
      }

      return this.adaptToNewRunMap$module;
   }

   public TypeMaps.UnrelatableCollector$ UnrelatableCollector() {
      if (this.UnrelatableCollector$module == null) {
         this.UnrelatableCollector$lzycompute$1();
      }

      return this.UnrelatableCollector$module;
   }

   public TypeMaps.IsRelatableCollector$ IsRelatableCollector() {
      if (this.IsRelatableCollector$module == null) {
         this.IsRelatableCollector$lzycompute$1();
      }

      return this.IsRelatableCollector$module;
   }

   public final boolean scala$reflect$internal$tpe$GlbLubs$$printLubs() {
      return this.scala$reflect$internal$tpe$GlbLubs$$printLubs;
   }

   public TypeMaps.FindTypeCollector scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector() {
      return this.scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector;
   }

   public HashMap scala$reflect$internal$tpe$GlbLubs$$_lubResults() {
      return this.scala$reflect$internal$tpe$GlbLubs$$_lubResults;
   }

   public HashMap scala$reflect$internal$tpe$GlbLubs$$_glbResults() {
      return this.scala$reflect$internal$tpe$GlbLubs$$_glbResults;
   }

   public Throwable GlbFailure() {
      return this.GlbFailure;
   }

   public int scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth() {
      return this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth;
   }

   public void scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth_$eq(final int x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$globalGlbDepth = x$1;
   }

   public final int scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit() {
      return this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit;
   }

   public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$printLubs_$eq(final boolean x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$printLubs = x$1;
   }

   public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector_$eq(final TypeMaps.FindTypeCollector x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$isWildCardOrNonGroundTypeVarCollector = x$1;
   }

   public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_lubResults_$eq(final HashMap x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$_lubResults = x$1;
   }

   public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$_glbResults_$eq(final HashMap x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$_glbResults = x$1;
   }

   public void scala$reflect$internal$tpe$GlbLubs$_setter_$GlbFailure_$eq(final Throwable x$1) {
      this.GlbFailure = x$1;
   }

   public final void scala$reflect$internal$tpe$GlbLubs$_setter_$scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit_$eq(final int x$1) {
      this.scala$reflect$internal$tpe$GlbLubs$$globalGlbLimit = x$1;
   }

   private CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16384) == 0) {
            this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj = CommonOwners.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$(this);
            this.bitmap$0 |= 16384;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
   }

   public CommonOwners.CommonOwnerMap scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj() {
      return (this.bitmap$0 & 16384) == 0 ? this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj$lzycompute() : this.scala$reflect$internal$tpe$CommonOwners$$commonOwnerMapObj;
   }

   public int scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions() {
      return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions;
   }

   public void scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions_$eq(final int x$1) {
      this.scala$reflect$internal$tpe$TypeToStrings$$_toStringRecursions = x$1;
   }

   public HashSet scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects() {
      return this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects;
   }

   public final void scala$reflect$internal$tpe$TypeToStrings$_setter_$scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects_$eq(final HashSet x$1) {
      this.scala$reflect$internal$tpe$TypeToStrings$$_toStringSubjects = x$1;
   }

   public HashSet scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes() {
      return this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes;
   }

   public TypeComparers.SubTypePair$ SubTypePair() {
      if (this.SubTypePair$module == null) {
         this.SubTypePair$lzycompute$1();
      }

      return this.SubTypePair$module;
   }

   public int scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions() {
      return this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions;
   }

   public void scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions_$eq(final int x$1) {
      this.scala$reflect$internal$tpe$TypeComparers$$_subsametypeRecursions = x$1;
   }

   public final void scala$reflect$internal$tpe$TypeComparers$_setter_$scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes_$eq(final HashSet x$1) {
      this.scala$reflect$internal$tpe$TypeComparers$$_pendingSubTypes = x$1;
   }

   public int ids() {
      return this.ids;
   }

   public void ids_$eq(final int x$1) {
      this.ids = x$1;
   }

   public Map scala$reflect$internal$Symbols$$_recursionTable() {
      return this.scala$reflect$internal$Symbols$$_recursionTable;
   }

   public void scala$reflect$internal$Symbols$$_recursionTable_$eq(final Map x$1) {
      this.scala$reflect$internal$Symbols$$_recursionTable = x$1;
   }

   public int scala$reflect$internal$Symbols$$_lockedCount() {
      return this.scala$reflect$internal$Symbols$$_lockedCount;
   }

   public void scala$reflect$internal$Symbols$$_lockedCount_$eq(final int x$1) {
      this.scala$reflect$internal$Symbols$$_lockedCount = x$1;
   }

   public ArrayBuffer scala$reflect$internal$Symbols$$_lockingTrace() {
      return this.scala$reflect$internal$Symbols$$_lockingTrace;
   }

   public boolean scala$reflect$internal$Symbols$$lockTracing() {
      return this.scala$reflect$internal$Symbols$$lockTracing;
   }

   /** @deprecated */
   public int scala$reflect$internal$Symbols$$existentialIds() {
      return this.scala$reflect$internal$Symbols$$existentialIds;
   }

   /** @deprecated */
   public void scala$reflect$internal$Symbols$$existentialIds_$eq(final int x$1) {
      this.scala$reflect$internal$Symbols$$existentialIds = x$1;
   }

   public AnyRefMap scala$reflect$internal$Symbols$$originalOwnerMap() {
      return this.scala$reflect$internal$Symbols$$originalOwnerMap;
   }

   public Symbols.SymbolKind$ SymbolKind() {
      if (this.SymbolKind$module == null) {
         this.SymbolKind$lzycompute$1();
      }

      return this.SymbolKind$module;
   }

   public ClassTag SymbolTag() {
      return this.SymbolTag;
   }

   public ClassTag TermSymbolTag() {
      return this.TermSymbolTag;
   }

   public ClassTag ModuleSymbolTag() {
      return this.ModuleSymbolTag;
   }

   public ClassTag MethodSymbolTag() {
      return this.MethodSymbolTag;
   }

   public ClassTag TypeSymbolTag() {
      return this.TypeSymbolTag;
   }

   public ClassTag ClassSymbolTag() {
      return this.ClassSymbolTag;
   }

   public ClassTag FreeTermSymbolTag() {
      return this.FreeTermSymbolTag;
   }

   public ClassTag FreeTypeSymbolTag() {
      return this.FreeTypeSymbolTag;
   }

   private Symbols.NoSymbol NoSymbol$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & '耀') == 0) {
            this.NoSymbol = this.makeNoSymbol();
            this.bitmap$0 |= 32768;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.NoSymbol;
   }

   public Symbols.NoSymbol NoSymbol() {
      return (this.bitmap$0 & '耀') == 0 ? this.NoSymbol$lzycompute() : this.NoSymbol;
   }

   public ReusableInstance scala$reflect$internal$Symbols$$cloneSymbolsSubstSymMap() {
      return this.scala$reflect$internal$Symbols$$cloneSymbolsSubstSymMap;
   }

   public Symbols.CyclicReference$ CyclicReference() {
      if (this.CyclicReference$module == null) {
         this.CyclicReference$lzycompute$1();
      }

      return this.CyclicReference$module;
   }

   public Symbols.TypeHistory$ scala$reflect$internal$Symbols$$TypeHistory() {
      if (this.TypeHistory$module == null) {
         this.scala$reflect$internal$Symbols$$TypeHistory$lzycompute$1();
      }

      return this.TypeHistory$module;
   }

   public final Symbols.TypeHistory scala$reflect$internal$Symbols$$noTypeHistory() {
      return this.scala$reflect$internal$Symbols$$noTypeHistory;
   }

   public final Function1 symbolIsPossibleInRefinement() {
      return this.symbolIsPossibleInRefinement;
   }

   public Symbols.SymbolOps$ SymbolOps() {
      if (this.SymbolOps$module == null) {
         this.SymbolOps$lzycompute$1();
      }

      return this.SymbolOps$module;
   }

   public Symbols.SymbolOps AllOps() {
      return this.AllOps;
   }

   public final void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$_lockingTrace_$eq(final ArrayBuffer x$1) {
      this.scala$reflect$internal$Symbols$$_lockingTrace = x$1;
   }

   public final void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$lockTracing_$eq(final boolean x$1) {
      this.scala$reflect$internal$Symbols$$lockTracing = x$1;
   }

   public final void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$originalOwnerMap_$eq(final AnyRefMap x$1) {
      this.scala$reflect$internal$Symbols$$originalOwnerMap = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$SymbolTag_$eq(final ClassTag x$1) {
      this.SymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$TermSymbolTag_$eq(final ClassTag x$1) {
      this.TermSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$ModuleSymbolTag_$eq(final ClassTag x$1) {
      this.ModuleSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$MethodSymbolTag_$eq(final ClassTag x$1) {
      this.MethodSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$TypeSymbolTag_$eq(final ClassTag x$1) {
      this.TypeSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$ClassSymbolTag_$eq(final ClassTag x$1) {
      this.ClassSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$FreeTermSymbolTag_$eq(final ClassTag x$1) {
      this.FreeTermSymbolTag = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$FreeTypeSymbolTag_$eq(final ClassTag x$1) {
      this.FreeTypeSymbolTag = x$1;
   }

   public final void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$cloneSymbolsSubstSymMap_$eq(final ReusableInstance x$1) {
      this.scala$reflect$internal$Symbols$$cloneSymbolsSubstSymMap = x$1;
   }

   public final void scala$reflect$internal$Symbols$_setter_$scala$reflect$internal$Symbols$$noTypeHistory_$eq(final Symbols.TypeHistory x$1) {
      this.scala$reflect$internal$Symbols$$noTypeHistory = x$1;
   }

   public final void scala$reflect$internal$Symbols$_setter_$symbolIsPossibleInRefinement_$eq(final Function1 x$1) {
      this.symbolIsPossibleInRefinement = x$1;
   }

   public void scala$reflect$internal$Symbols$_setter_$AllOps_$eq(final Symbols.SymbolOps x$1) {
      this.AllOps = x$1;
   }

   public Object scala$reflect$internal$Names$$nameLock() {
      return this.scala$reflect$internal$Names$$nameLock;
   }

   public char[] scala$reflect$internal$Names$$_chrs() {
      return this.scala$reflect$internal$Names$$_chrs;
   }

   public void scala$reflect$internal$Names$$_chrs_$eq(final char[] x$1) {
      this.scala$reflect$internal$Names$$_chrs = x$1;
   }

   public int scala$reflect$internal$Names$$nc() {
      return this.scala$reflect$internal$Names$$nc;
   }

   public void scala$reflect$internal$Names$$nc_$eq(final int x$1) {
      this.scala$reflect$internal$Names$$nc = x$1;
   }

   public Names.TermName[] scala$reflect$internal$Names$$termHashtable() {
      return this.scala$reflect$internal$Names$$termHashtable;
   }

   public Names.TypeName[] scala$reflect$internal$Names$$typeHashtable() {
      return this.scala$reflect$internal$Names$$typeHashtable;
   }

   public ClassTag NameTag() {
      return this.NameTag;
   }

   public ClassTag TermNameTag() {
      return this.TermNameTag;
   }

   public Names.TermName$ TermName() {
      if (this.TermName$module == null) {
         this.TermName$lzycompute$1();
      }

      return this.TermName$module;
   }

   public ClassTag TypeNameTag() {
      return this.TypeNameTag;
   }

   public Names.TypeName$ TypeName() {
      if (this.TypeName$module == null) {
         this.TypeName$lzycompute$1();
      }

      return this.TypeName$module;
   }

   public final void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$nameLock_$eq(final Object x$1) {
      this.scala$reflect$internal$Names$$nameLock = x$1;
   }

   public final void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$termHashtable_$eq(final Names.TermName[] x$1) {
      this.scala$reflect$internal$Names$$termHashtable = x$1;
   }

   public final void scala$reflect$internal$Names$_setter_$scala$reflect$internal$Names$$typeHashtable_$eq(final Names.TypeName[] x$1) {
      this.scala$reflect$internal$Names$$typeHashtable = x$1;
   }

   public void scala$reflect$internal$Names$_setter_$NameTag_$eq(final ClassTag x$1) {
      this.NameTag = x$1;
   }

   public void scala$reflect$internal$Names$_setter_$TermNameTag_$eq(final ClassTag x$1) {
      this.TermNameTag = x$1;
   }

   public void scala$reflect$internal$Names$_setter_$TypeNameTag_$eq(final ClassTag x$1) {
      this.TypeNameTag = x$1;
   }

   public Tuple2 scala$reflect$internal$util$Collections$$TupleOfNil() {
      return this.scala$reflect$internal$util$Collections$$TupleOfNil;
   }

   public final void scala$reflect$internal$util$Collections$_setter_$scala$reflect$internal$util$Collections$$TupleOfNil_$eq(final Tuple2 x$1) {
      this.scala$reflect$internal$util$Collections$$TupleOfNil = x$1;
   }

   public scala.reflect.internal.TreeGen gen() {
      return this.gen;
   }

   public abstract Statistics statistics();

   public abstract void log(final Function0 msg);

   public String elapsedMessage(final String msg, final long startNs) {
      return (new StringBuilder(6)).append(msg).append(" in ").append(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)).append("ms").toString();
   }

   public void informProgress(final String msg) {
      if (BoxesRunTime.unboxToBoolean(this.settings().verbose().value())) {
         this.inform((new StringBuilder(2)).append("[").append(msg).append("]").toString());
      }
   }

   public void informTime(final String msg, final long startNs) {
      if (BoxesRunTime.unboxToBoolean(this.settings().verbose().value())) {
         this.informProgress(this.elapsedMessage(msg, startNs));
      }
   }

   public final Object informingProgress(final Function0 msg, final Function0 fn) {
      boolean verbose = BoxesRunTime.unboxToBoolean(this.settings().verbose().value());
      long start = verbose ? System.nanoTime() : 0L;

      Object var10000;
      try {
         var10000 = fn.apply();
      } finally {
         if (verbose) {
            this.informTime((String)msg.apply(), start);
         }

      }

      return var10000;
   }

   public boolean shouldLogAtThisPhase() {
      return false;
   }

   public boolean isPastTyper() {
      return false;
   }

   public final boolean isDeveloper() {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var9 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = this.settings();
      MutableSettings var10 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings isDebug$extension_$this = var10;
      boolean var11 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
      isDebug$extension_$this = null;
      if (!var11) {
         MutableSettings.SettingsOps$ var12 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var13 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = this.settings();
         MutableSettings var14 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDeveloper$extension_$this = var14;
         boolean var15 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper$extension_$this.developer().value());
         isDeveloper$extension_$this = null;
         if (!var15) {
            return false;
         }
      }

      return true;
   }

   public abstract Phase picklerPhase();

   public abstract Phase erasurePhase();

   public abstract MutableSettings settings();

   public boolean isSymbolLockTracingEnabled() {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var9 = MutableSettings$.MODULE$;
      MutableSettings isDeveloper_SettingsOps_settings = this.settings();
      MutableSettings var10 = isDeveloper_SettingsOps_settings;
      isDeveloper_SettingsOps_settings = null;
      MutableSettings isDeveloper_isDebug$extension_$this = var10;
      boolean var11 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDebug$extension_$this.debug().value());
      isDeveloper_isDebug$extension_$this = null;
      if (!var11) {
         MutableSettings.SettingsOps$ var12 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var13 = MutableSettings$.MODULE$;
         MutableSettings isDeveloper_SettingsOps_settings = this.settings();
         MutableSettings var14 = isDeveloper_SettingsOps_settings;
         isDeveloper_SettingsOps_settings = null;
         MutableSettings isDeveloper_isDeveloper$extension_$this = var14;
         boolean var15 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDeveloper$extension_$this.developer().value());
         isDeveloper_isDeveloper$extension_$this = null;
         if (!var15) {
            return false;
         }
      }

      return true;
   }

   public void debuglog(final Function0 msg) {
      MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
      MutableSettings$ var6 = MutableSettings$.MODULE$;
      MutableSettings SettingsOps_settings = this.settings();
      MutableSettings var7 = SettingsOps_settings;
      SettingsOps_settings = null;
      MutableSettings isDebug$extension_$this = var7;
      boolean var8 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
      isDebug$extension_$this = null;
      if (var8) {
         this.log(msg);
      }
   }

   public final void devWarningIf(final Function0 cond, final Function0 msg) {
      boolean var20;
      label31: {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var13 = MutableSettings$.MODULE$;
         MutableSettings isDeveloper_SettingsOps_settings = this.settings();
         MutableSettings var14 = isDeveloper_SettingsOps_settings;
         isDeveloper_SettingsOps_settings = null;
         MutableSettings isDeveloper_isDebug$extension_$this = var14;
         boolean var15 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDebug$extension_$this.debug().value());
         isDeveloper_isDebug$extension_$this = null;
         if (!var15) {
            MutableSettings.SettingsOps$ var16 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var17 = MutableSettings$.MODULE$;
            MutableSettings isDeveloper_SettingsOps_settings = this.settings();
            MutableSettings var18 = isDeveloper_SettingsOps_settings;
            isDeveloper_SettingsOps_settings = null;
            MutableSettings isDeveloper_isDeveloper$extension_$this = var18;
            boolean var19 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDeveloper$extension_$this.developer().value());
            isDeveloper_isDeveloper$extension_$this = null;
            if (!var19) {
               var20 = false;
               break label31;
            }
         }

         var20 = true;
      }

      Object var9 = null;
      Object var12 = null;
      if (var20 && cond.apply$mcZ$sp()) {
         this.devWarning(msg);
      }
   }

   public void devWarning(final Function0 msg) {
      boolean var19;
      label29: {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var12 = MutableSettings$.MODULE$;
         MutableSettings isDeveloper_SettingsOps_settings = this.settings();
         MutableSettings var13 = isDeveloper_SettingsOps_settings;
         isDeveloper_SettingsOps_settings = null;
         MutableSettings isDeveloper_isDebug$extension_$this = var13;
         boolean var14 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDebug$extension_$this.debug().value());
         isDeveloper_isDebug$extension_$this = null;
         if (!var14) {
            MutableSettings.SettingsOps$ var15 = MutableSettings.SettingsOps$.MODULE$;
            MutableSettings$ var16 = MutableSettings$.MODULE$;
            MutableSettings isDeveloper_SettingsOps_settings = this.settings();
            MutableSettings var17 = isDeveloper_SettingsOps_settings;
            isDeveloper_SettingsOps_settings = null;
            MutableSettings isDeveloper_isDeveloper$extension_$this = var17;
            boolean var18 = StatisticsStatics.DEVELOPER_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDeveloper_isDeveloper$extension_$this.developer().value());
            isDeveloper_isDeveloper$extension_$this = null;
            if (!var18) {
               var19 = false;
               break label29;
            }
         }

         var19 = true;
      }

      Object var8 = null;
      Object var11 = null;
      if (var19) {
         .MODULE$.err().println((String)msg.apply());
      }
   }

   public String throwableAsString(final Throwable t) {
      return String.valueOf(t);
   }

   public String throwableAsString(final Throwable t, final int maxFrames) {
      ArraySeq.ofRef var10000 = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.slice$extension(t.getStackTrace(), 0, maxFrames));
      String mkString_sep = "\n  at ";
      if (var10000 == null) {
         throw null;
      } else {
         AbstractIterable mkString_this = var10000;
         String mkString_end = "";
         String mkString_start = "";
         return IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
      }
   }

   public final void devWarningDumpStack(final Function0 msg, final int maxFrames) {
      this.devWarning(() -> (new StringBuilder(1)).append((String)msg.apply()).append("\n").append(this.throwableAsString(new Throwable(), maxFrames)).toString());
   }

   public void debugStack(final Throwable t) {
      this.devWarning(() -> this.throwableAsString(t));
   }

   public Object printCaller(final String msg, final Object result) {
      PrintStream var10000 = .MODULE$.err();
      StringOps var10001 = scala.collection.StringOps..MODULE$;
      ScalaRunTime var10003 = scala.runtime.ScalaRunTime..MODULE$;
      Object[] var10004 = new Object[]{msg, result, null};
      Predef var10007 = scala.Predef..MODULE$;
      ArrayOps var10008 = scala.collection.ArrayOps..MODULE$;
      Object[] var10009 = scala.collection.ArrayOps..MODULE$.drop$extension((new Throwable()).getStackTrace(), 2);
      int take$extension_n = 50;
      ArraySeq.ofRef var12 = var10007.wrapRefArray(var10008.slice$extension(var10009, 0, take$extension_n));
      String mkString_sep = "\n";
      if (var12 == null) {
         throw null;
      } else {
         AbstractIterable mkString_this = var12;
         String mkString_end = "";
         String mkString_start = "";
         String var13 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         Object var10 = null;
         Object var11 = null;
         mkString_this = null;
         Object var9 = null;
         var10004[2] = var13;
         var10000.println(var10001.format$extension("%s: %s\nCalled from: %s", var10003.genericWrapArray(var10004)));
         return result;
      }
   }

   public Object printResult(final String msg, final Object result) {
      .MODULE$.err().println((new StringBuilder(2)).append(msg).append(": ").append(result).toString());
      return result;
   }

   public final Object logResult(final Function0 msg, final Object result) {
      this.log(() -> (new StringBuilder(2)).append((String)msg.apply()).append(": ").append(result).toString());
      return result;
   }

   public final Object debuglogResult(final Function0 msg, final Object result) {
      this.debuglog(() -> (new StringBuilder(2)).append((String)msg.apply()).append(": ").append(result).toString());
      return result;
   }

   public final Object devWarningResult(final Function0 msg, final Object result) {
      this.devWarning(() -> (new StringBuilder(2)).append((String)msg.apply()).append(": ").append(result).toString());
      return result;
   }

   public final Object logResultIf(final Function0 msg, final Function1 cond, final Object result) {
      if (BoxesRunTime.unboxToBoolean(cond.apply(result))) {
         this.log(() -> (new StringBuilder(2)).append((String)msg.apply()).append(": ").append(result).toString());
      }

      return result;
   }

   public final Object debuglogResultIf(final Function0 msg, final Function1 cond, final Object result) {
      if (BoxesRunTime.unboxToBoolean(cond.apply(result))) {
         this.debuglog(() -> (new StringBuilder(2)).append((String)msg.apply()).append(": ").append(result).toString());
      }

      return result;
   }

   public final void assert(final boolean assertion, final Function0 message) {
      if (!assertion) {
         throw this.throwAssertionError(message.apply());
      }
   }

   /** @deprecated */
   public final void assert(final boolean assertion) {
      if (!assertion) {
         throw this.throwAssertionError("");
      }
   }

   public final void require(final boolean requirement, final Function0 message) {
      if (!requirement) {
         throw this.throwRequirementError(message.apply());
      }
   }

   /** @deprecated */
   public final void require(final boolean requirement) {
      if (!requirement) {
         throw this.throwRequirementError("");
      }
   }

   public Nothing throwAssertionError(final Object msg) {
      throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append(this.supplementErrorMessage(String.valueOf(msg))).toString());
   }

   public Nothing throwRequirementError(final Object msg) {
      throw new IllegalArgumentException((new StringBuilder(20)).append("requirement failed: ").append(this.supplementErrorMessage(String.valueOf(msg))).toString());
   }

   public final Symbols.Symbol findSymbol(final IterableOnce xs, final Function1 p) {
      Option var10000 = xs.iterator().find(p);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         return (Symbols.Symbol)(getOrElse_this.isEmpty() ? this.NoSymbol() : getOrElse_this.get());
      }
   }

   public Ordering lowPriorityNameOrdering() {
      return this.SimpleNameOrdering();
   }

   public final boolean traceSymbolActivity() {
      return this.traceSymbolActivity;
   }

   public abstract TreeInfo treeInfo();

   public void assertCorrectThread() {
   }

   public Symbols.Symbol missingHook(final Symbols.Symbol owner, final Names.Name name) {
      return this.NoSymbol();
   }

   public abstract Mirrors.RootsBase mirrorThatLoaded(final Symbols.Symbol sym);

   public final int NoPeriod() {
      return 0;
   }

   public final int NoRunId() {
      return 0;
   }

   public final List atPhaseStack() {
      return this.phStack.toList();
   }

   public final Phase phase() {
      return this.ph;
   }

   public String atPhaseStackMessage() {
      List var1 = this.atPhaseStack();
      return scala.collection.immutable.Nil..MODULE$.equals(var1) ? "" : var1.reverseIterator().map((x$1) -> (new StringBuilder(2)).append("->").append(x$1).toString()).mkString("(", " ", ")");
   }

   public final void phase_$eq(final Phase p) {
      this.ph = p;
      int var10001 = this.currentRunId();
      int period_pid = p.id();
      this.per = (var10001 << 8) + period_pid;
   }

   public final Phase pushPhase(final Phase ph) {
      Phase current = this.phase();
      this.phase_$eq(ph);
      if (this.keepPhaseStack()) {
         this.phStack.push(ph);
      }

      return current;
   }

   public final void popPhase(final Phase ph) {
      if (this.keepPhaseStack()) {
         this.phStack.pop();
      }

      this.phase_$eq(ph);
   }

   public boolean keepPhaseStack() {
      return this.keepPhaseStack;
   }

   public void keepPhaseStack_$eq(final boolean x$1) {
      this.keepPhaseStack = x$1;
   }

   public abstract int currentRunId();

   public final int runId(final int period) {
      return period >> 8;
   }

   public final int phaseId(final int period) {
      return period & 255;
   }

   public final int currentPeriod() {
      return this.per;
   }

   public final Phase phaseOf(final int period) {
      return this.phaseWithId()[period & 255];
   }

   public final int period(final int rid, final int pid) {
      return (rid << 8) + pid;
   }

   public final boolean isAtPhaseAfter(final Phase p) {
      NoPhase$ var2 = NoPhase$.MODULE$;
      if (p != null) {
         if (p.equals(var2)) {
            return false;
         }
      }

      if (this.phase().id() > p.id()) {
         return true;
      } else {
         return false;
      }
   }

   public final Object enteringPhase(final Phase ph, final Function0 op) {
      if (ph == this.phase()) {
         return op.apply();
      } else {
         Phase saved = this.pushPhase(ph);

         Object var10000;
         try {
            var10000 = op.apply();
         } finally {
            this.popPhase(saved);
         }

         return var10000;
      }
   }

   public final Phase findPhaseWithName(final String phaseName) {
      Phase ph = this.phase();

      while(true) {
         NoPhase$ var3 = NoPhase$.MODULE$;
         if (ph != null) {
            if (ph.equals(var3)) {
               break;
            }
         }

         String var10000 = ph.name();
         if (var10000 == null) {
            if (phaseName == null) {
               break;
            }
         } else if (var10000.equals(phaseName)) {
            break;
         }

         ph = ph.prev();
      }

      return ph == NoPhase$.MODULE$ ? this.phase() : ph;
   }

   public final Object enteringPhaseWithName(final String phaseName, final Function0 body) {
      Phase phase = this.findPhaseWithName(phaseName);
      if (phase == this.phase()) {
         return body.apply();
      } else {
         Phase enteringPhase_saved = this.pushPhase(phase);

         Object var10000;
         try {
            var10000 = body.apply();
         } finally {
            this.popPhase(enteringPhase_saved);
         }

         return var10000;
      }
   }

   public Object slowButSafeEnteringPhase(final Phase ph, final Function0 op) {
      if (this.isCompilerUniverse()) {
         if (ph == this.phase()) {
            return op.apply();
         } else {
            Phase enteringPhase_saved = this.pushPhase(ph);

            Object var10000;
            try {
               var10000 = op.apply();
            } finally {
               this.popPhase(enteringPhase_saved);
            }

            return var10000;
         }
      } else {
         return op.apply();
      }
   }

   public final Object exitingPhase(final Phase ph, final Function0 op) {
      Phase enteringPhase_ph = ph.next();
      if (enteringPhase_ph == this.phase()) {
         return op.apply();
      } else {
         Phase enteringPhase_saved = this.pushPhase(enteringPhase_ph);

         Object var10000;
         try {
            var10000 = op.apply();
         } finally {
            this.popPhase(enteringPhase_saved);
         }

         return var10000;
      }
   }

   public final Object enteringPrevPhase(final Function0 op) {
      Phase enteringPhase_ph = this.phase().prev();
      if (enteringPhase_ph == this.phase()) {
         return op.apply();
      } else {
         Phase enteringPhase_saved = this.pushPhase(enteringPhase_ph);

         Object var10000;
         try {
            var10000 = op.apply();
         } finally {
            this.popPhase(enteringPhase_saved);
         }

         return var10000;
      }
   }

   public final Object enteringPhaseNotLaterThan(final Phase target, final Function0 op) {
      if (this.isAtPhaseAfter(target)) {
         if (target == this.phase()) {
            return op.apply();
         } else {
            Phase enteringPhase_saved = this.pushPhase(target);

            Object var10000;
            try {
               var10000 = op.apply();
            } finally {
               this.popPhase(enteringPhase_saved);
            }

            return var10000;
         }
      } else {
         return op.apply();
      }
   }

   public Object slowButSafeEnteringPhaseNotLaterThan(final Phase target, final Function0 op) {
      if (this.isCompilerUniverse()) {
         if (this.isAtPhaseAfter(target)) {
            if (target == this.phase()) {
               return op.apply();
            } else {
               Phase enteringPhaseNotLaterThan_enteringPhase_saved = this.pushPhase(target);

               Object var10000;
               try {
                  var10000 = op.apply();
               } finally {
                  this.popPhase(enteringPhaseNotLaterThan_enteringPhase_saved);
               }

               return var10000;
            }
         } else {
            return op.apply();
         }
      } else {
         return op.apply();
      }
   }

   public final boolean isValid(final int period) {
      if (period != 0 && period >> 8 == this.currentRunId()) {
         int pid = period & 255;
         if (this.phase().id() > pid) {
            if (this.nextFrom()[pid].pid() >= this.phase().id()) {
               return true;
            }
         } else if (this.nextFrom()[this.phase().id()].pid() >= pid) {
            return true;
         }
      }

      return false;
   }

   public final boolean isValidForBaseClasses(final int period) {
      if (period != 0 && period >> 8 == this.currentRunId()) {
         int pid = period & 255;
         if (this.phase().id() > pid) {
            if (this.noChangeInBaseClasses$1(this.nextFrom()[pid], this.phase().id())) {
               return true;
            }
         } else if (this.noChangeInBaseClasses$1(this.nextFrom()[this.phase().id()], pid)) {
            return true;
         }
      }

      return false;
   }

   public void openPackageModule(final Symbols.Symbol container, final Symbols.Symbol dest) {
      container.info().decls().iterator().foreach((member) -> {
         $anonfun$openPackageModule$1(dest, member);
         return BoxedUnit.UNIT;
      });
      container.info().decls().iterator().foreach((member) -> !member.isPrivate() && !member.isConstructor() ? dest.info().decls().enter(member) : BoxedUnit.UNIT);
      container.parentSymbolsIterator().foreach((p) -> {
         $anonfun$openPackageModule$4(this, dest, p);
         return BoxedUnit.UNIT;
      });
   }

   public Types.Type arrayToRepeated(final Types.Type tp) {
      if (!(tp instanceof Types.MethodType)) {
         if (tp instanceof Types.PolyType) {
            Types.PolyType var16 = (Types.PolyType)tp;
            List tparams = var16.typeParams();
            Types.Type rtpe = var16.resultType();
            return new Types.PolyType(tparams, this.arrayToRepeated(rtpe));
         } else {
            throw new MatchError(tp);
         }
      } else {
         List params;
         Types.Type rtpe;
         List formals;
         boolean var26;
         label58: {
            label57: {
               Types.MethodType var2 = (Types.MethodType)tp;
               params = var2.params();
               rtpe = var2.resultType();
               formals = tp.paramTypes();
               Symbols.Symbol var10000 = ((Types.Type)formals.last()).typeSymbol();
               Symbols.ClassSymbol var6 = this.definitions().ArrayClass();
               if (var10000 == null) {
                  if (var6 == null) {
                     break label57;
                  }
               } else if (var10000.equals(var6)) {
                  break label57;
               }

               var26 = false;
               break label58;
            }

            var26 = true;
         }

         if (!var26) {
            throw this.throwAssertionError(formals);
         } else {
            Symbols.Symbol method;
            label49: {
               Types.Type t1;
               label48: {
                  method = ((Symbols.Symbol)params.last()).owner();
                  Types.Type var9 = (Types.Type)((Types.Type)formals.last()).typeArgs().head();
                  if (var9 instanceof Types.RefinedType) {
                     List var10 = ((Types.RefinedType)var9).parents();
                     if (var10 != null) {
                        List var27 = scala.package..MODULE$.List();
                        if (var27 == null) {
                           throw null;
                        }

                        List unapplySeq_this = var27;
                        SeqOps var28 = SeqFactory.unapplySeq$(unapplySeq_this, var10);
                        Object var25 = null;
                        SeqOps var11 = var28;
                        SeqFactory.UnapplySeqWrapper var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        SeqFactory.UnapplySeqWrapper var10001 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        new SeqFactory.UnapplySeqWrapper(var11);
                        var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                        int lengthCompare$extension_len = 2;
                        if (var11.lengthCompare(lengthCompare$extension_len) == 0) {
                           var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                           var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                           int apply$extension_i = 0;
                           t1 = (Types.Type)var11.apply(apply$extension_i);
                           var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                           var29 = scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$;
                           int apply$extension_i = 1;
                           Types.Type t2 = (Types.Type)var11.apply(apply$extension_i);
                           if (t1.typeSymbol().isAbstractType()) {
                              Symbols.Symbol var36 = t2.typeSymbol();
                              Symbols.ClassSymbol var14 = this.definitions().ObjectClass();
                              if (var36 == null) {
                                 if (var14 == null) {
                                    break label48;
                                 }
                              } else if (var36.equals(var14)) {
                                 break label48;
                              }
                           }
                        }
                     }
                  }

                  var37 = var9;
                  break label49;
               }

               var37 = t1;
            }

            Types.Type elemtp = var37;
            SeqOps var38 = (SeqOps)formals.init();
            Types.Type $colon$plus_elem = this.definitions().javaRepeatedType(elemtp);
            if (var38 == null) {
               throw null;
            } else {
               Object var39 = var38.appended($colon$plus_elem);
               $colon$plus_elem = null;
               List newParams = method.newSyntheticValueParams((List)var39);
               return new Types.MethodType(newParams, rtpe);
            }
         }
      }
   }

   public void openPackageModule(final Symbols.Symbol pkgClass, final boolean force) {
      Symbols.Symbol pkgModule = pkgClass.packageObject();
      if (pkgModule.isModule() && !fromSource$1(pkgModule)) {
         this.openPackageModule(pkgModule, pkgClass);
      }
   }

   public boolean openPackageModule$default$2() {
      return false;
   }

   public InfoTransformers.InfoTransformer infoTransformers() {
      return this.infoTransformers;
   }

   public void infoTransformers_$eq(final InfoTransformers.InfoTransformer x$1) {
      this.infoTransformers = x$1;
   }

   public InfoTransformers.InfoTransformer[] nextFrom() {
      return this.nextFrom;
   }

   public void nextFrom_$eq(final InfoTransformers.InfoTransformer[] x$1) {
      this.nextFrom = x$1;
   }

   private final int MaxPhases() {
      return 256;
   }

   public final Phase[] phaseWithId() {
      return this.phaseWithId;
   }

   public boolean isCompilerUniverse() {
      return false;
   }

   /** @deprecated */
   public final Object atPhase(final Phase ph, final Function0 op) {
      if (ph == this.phase()) {
         return op.apply();
      } else {
         Phase enteringPhase_saved = this.pushPhase(ph);

         Object var10000;
         try {
            var10000 = op.apply();
         } finally {
            this.popPhase(enteringPhase_saved);
         }

         return var10000;
      }
   }

   public void currentRunProfilerBeforeCompletion(final Symbols.Symbol root, final AbstractFile associatedFile) {
   }

   public void currentRunProfilerAfterCompletion(final Symbols.Symbol root, final AbstractFile associatedFile) {
   }

   private final void SimpleNameOrdering$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SimpleNameOrdering$module == null) {
            this.SimpleNameOrdering$module = new SimpleNameOrdering$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void traceSymbols$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.traceSymbols$module == null) {
            this.traceSymbols$module = new traceSymbols$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void perRunCaches$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.perRunCaches$module == null) {
            this.perRunCaches$module = new perRunCaches$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void FreshNameExtractor$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.FreshNameExtractor$module == null) {
            this.FreshNameExtractor$module = new FreshNames.FreshNameExtractor$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void FixedMirrorTreeCreator$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.FixedMirrorTreeCreator$module == null) {
            this.FixedMirrorTreeCreator$module = new StdCreators.FixedMirrorTreeCreator$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void FixedMirrorTypeCreator$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.FixedMirrorTypeCreator$module == null) {
            this.FixedMirrorTypeCreator$module = new StdCreators.FixedMirrorTypeCreator$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CompoundTypeTreeOriginalAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CompoundTypeTreeOriginalAttachment$module == null) {
            this.CompoundTypeTreeOriginalAttachment$module = new StdAttachments.CompoundTypeTreeOriginalAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SAMFunction$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SAMFunction$module == null) {
            this.SAMFunction$module = new StdAttachments.SAMFunction$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DelambdafyTarget$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DelambdafyTarget$module == null) {
            this.DelambdafyTarget$module = new StdAttachments.DelambdafyTarget$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void BackquotedIdentifierAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.BackquotedIdentifierAttachment$module == null) {
            this.BackquotedIdentifierAttachment$module = new StdAttachments.BackquotedIdentifierAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PostfixAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PostfixAttachment$module == null) {
            this.PostfixAttachment$module = new StdAttachments.PostfixAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void InfixAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.InfixAttachment$module == null) {
            this.InfixAttachment$module = new StdAttachments.InfixAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void AutoApplicationAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.AutoApplicationAttachment$module == null) {
            this.AutoApplicationAttachment$module = new StdAttachments.AutoApplicationAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NoWarnAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoWarnAttachment$module == null) {
            this.NoWarnAttachment$module = new StdAttachments.NoWarnAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PatShadowAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PatShadowAttachment$module == null) {
            this.PatShadowAttachment$module = new StdAttachments.PatShadowAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PatVarDefAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PatVarDefAttachment$module == null) {
            this.PatVarDefAttachment$module = new StdAttachments.PatVarDefAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void MultiDefAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.MultiDefAttachment$module == null) {
            this.MultiDefAttachment$module = new StdAttachments.MultiDefAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ForAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ForAttachment$module == null) {
            this.ForAttachment$module = new StdAttachments.ForAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SyntheticUnitAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SyntheticUnitAttachment$module == null) {
            this.SyntheticUnitAttachment$module = new StdAttachments.SyntheticUnitAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SubpatternsAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SubpatternsAttachment$module == null) {
            this.SubpatternsAttachment$module = new StdAttachments.SubpatternsAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NoInlineCallsiteAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoInlineCallsiteAttachment$module == null) {
            this.NoInlineCallsiteAttachment$module = new StdAttachments.NoInlineCallsiteAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void InlineCallsiteAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.InlineCallsiteAttachment$module == null) {
            this.InlineCallsiteAttachment$module = new StdAttachments.InlineCallsiteAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void OuterArgCanBeElided$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.OuterArgCanBeElided$module == null) {
            this.OuterArgCanBeElided$module = new StdAttachments.OuterArgCanBeElided$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UseInvokeSpecial$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UseInvokeSpecial$module == null) {
            this.UseInvokeSpecial$module = new StdAttachments.UseInvokeSpecial$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeParamVarargsAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeParamVarargsAttachment$module == null) {
            this.TypeParamVarargsAttachment$module = new StdAttachments.TypeParamVarargsAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void KnownDirectSubclassesCalled$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.KnownDirectSubclassesCalled$module == null) {
            this.KnownDirectSubclassesCalled$module = new StdAttachments.KnownDirectSubclassesCalled$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DottyEnumSingleton$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DottyEnumSingleton$module == null) {
            this.DottyEnumSingleton$module = new StdAttachments.DottyEnumSingleton$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ConstructorNeedsFence$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ConstructorNeedsFence$module == null) {
            this.ConstructorNeedsFence$module = new StdAttachments.ConstructorNeedsFence$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void MultiargInfixAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.MultiargInfixAttachment$module == null) {
            this.MultiargInfixAttachment$module = new StdAttachments.MultiargInfixAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NullaryOverrideAdapted$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NullaryOverrideAdapted$module == null) {
            this.NullaryOverrideAdapted$module = new StdAttachments.NullaryOverrideAdapted$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ChangeOwnerAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ChangeOwnerAttachment$module == null) {
            this.ChangeOwnerAttachment$module = new StdAttachments.ChangeOwnerAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void InterpolatedString$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.InterpolatedString$module == null) {
            this.InterpolatedString$module = new StdAttachments.InterpolatedString$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void VirtualStringContext$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.VirtualStringContext$module == null) {
            this.VirtualStringContext$module = new StdAttachments.VirtualStringContext$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CaseApplyInheritAccess$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CaseApplyInheritAccess$module == null) {
            this.CaseApplyInheritAccess$module = new StdAttachments.CaseApplyInheritAccess$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RootSelection$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RootSelection$module == null) {
            this.RootSelection$module = new StdAttachments.RootSelection$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypedExpectingUnitAttachment$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypedExpectingUnitAttachment$module == null) {
            this.TypedExpectingUnitAttachment$module = new StdAttachments.TypedExpectingUnitAttachment$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void FieldTypeInferred$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.FieldTypeInferred$module == null) {
            this.FieldTypeInferred$module = new StdAttachments.FieldTypeInferred$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LookupAmbiguityWarning$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LookupAmbiguityWarning$module == null) {
            this.LookupAmbiguityWarning$module = new StdAttachments.LookupAmbiguityWarning$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PermittedSubclasses$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PermittedSubclasses$module == null) {
            this.PermittedSubclasses$module = new StdAttachments.PermittedSubclasses$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PermittedSubclassSymbols$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PermittedSubclassSymbols$module == null) {
            this.PermittedSubclassSymbols$module = new StdAttachments.PermittedSubclassSymbols$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NamePos$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NamePos$module == null) {
            this.NamePos$module = new StdAttachments.NamePos$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnnamedArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnnamedArg$module == null) {
            this.UnnamedArg$module = new StdAttachments.UnnamedArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DiscardedValue$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DiscardedValue$module == null) {
            this.DiscardedValue$module = new StdAttachments.DiscardedValue$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DiscardedExpr$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DiscardedExpr$module == null) {
            this.DiscardedExpr$module = new StdAttachments.DiscardedExpr$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void BooleanParameterType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.BooleanParameterType$module == null) {
            this.BooleanParameterType$module = new StdAttachments.BooleanParameterType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void noPrint$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.noPrint$module == null) {
            this.noPrint$module = new TypeDebugging.noPrint$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void typeDebug$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.typeDebug$module == null) {
            this.typeDebug$module = new TypeDebugging.typeDebug$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ConsoleWriter$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ConsoleWriter$module == null) {
            this.ConsoleWriter$module = new Printers.ConsoleWriter$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RefTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RefTree$module == null) {
            this.RefTree$module = new Trees.RefTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PackageDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PackageDef$module == null) {
            this.PackageDef$module = new Trees.PackageDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ClassDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ClassDef$module == null) {
            this.ClassDef$module = new Trees.ClassDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ModuleDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ModuleDef$module == null) {
            this.ModuleDef$module = new Trees.ModuleDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ValOrDefDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValOrDefDef$module == null) {
            this.ValOrDefDef$module = new Trees.ValOrDefDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ValDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValDef$module == null) {
            this.ValDef$module = new Trees.ValDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void DefDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.DefDef$module == null) {
            this.DefDef$module = new Trees.DefDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeDef$module == null) {
            this.TypeDef$module = new Trees.TypeDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LabelDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LabelDef$module == null) {
            this.LabelDef$module = new Trees.LabelDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ImportSelector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ImportSelector$module == null) {
            this.ImportSelector$module = new Trees.ImportSelector$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Import$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Import$module == null) {
            this.Import$module = new Trees.Import$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Template$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Template$module == null) {
            this.Template$module = new Trees.Template$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Block$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Block$module == null) {
            this.Block$module = new Trees.Block$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CaseDef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CaseDef$module == null) {
            this.CaseDef$module = new Trees.CaseDef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Alternative$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Alternative$module == null) {
            this.Alternative$module = new Trees.Alternative$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Star$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Star$module == null) {
            this.Star$module = new Trees.Star$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Bind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Bind$module == null) {
            this.Bind$module = new Trees.Bind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnApply$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnApply$module == null) {
            this.UnApply$module = new Trees.UnApply$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ArrayValue$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ArrayValue$module == null) {
            this.ArrayValue$module = new Trees.ArrayValue$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Function$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Function$module == null) {
            this.Function$module = new Trees.Function$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Assign$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Assign$module == null) {
            this.Assign$module = new Trees.Assign$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NamedArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NamedArg$module == null) {
            this.NamedArg$module = new Trees.NamedArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void If$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.If$module == null) {
            this.If$module = new Trees.If$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Match$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Match$module == null) {
            this.Match$module = new Trees.Match$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Return$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Return$module == null) {
            this.Return$module = new Trees.Return$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Try$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Try$module == null) {
            this.Try$module = new Trees.Try$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Throw$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Throw$module == null) {
            this.Throw$module = new Trees.Throw$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void New$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.New$module == null) {
            this.New$module = new Trees.New$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Typed$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Typed$module == null) {
            this.Typed$module = new Trees.Typed$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void MethodValue$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.MethodValue$module == null) {
            this.MethodValue$module = new Trees.MethodValue$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeApply$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeApply$module == null) {
            this.TypeApply$module = new Trees.TypeApply$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Apply$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Apply$module == null) {
            this.Apply$module = new Trees.Apply$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ApplyDynamic$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ApplyDynamic$module == null) {
            this.ApplyDynamic$module = new Trees.ApplyDynamic$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Super$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Super$module == null) {
            this.Super$module = new Trees.Super$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void This$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.This$module == null) {
            this.This$module = new Trees.This$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Select$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Select$module == null) {
            this.Select$module = new Trees.Select$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Ident$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Ident$module == null) {
            this.Ident$module = new Trees.Ident$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ReferenceToBoxed$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ReferenceToBoxed$module == null) {
            this.ReferenceToBoxed$module = new Trees.ReferenceToBoxed$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Literal$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Literal$module == null) {
            this.Literal$module = new Trees.Literal$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Annotated$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Annotated$module == null) {
            this.Annotated$module = new Trees.Annotated$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SingletonTypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SingletonTypeTree$module == null) {
            this.SingletonTypeTree$module = new Trees.SingletonTypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SelectFromTypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SelectFromTypeTree$module == null) {
            this.SelectFromTypeTree$module = new Trees.SelectFromTypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CompoundTypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CompoundTypeTree$module == null) {
            this.CompoundTypeTree$module = new Trees.CompoundTypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void AppliedTypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.AppliedTypeTree$module == null) {
            this.AppliedTypeTree$module = new Trees.AppliedTypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeBoundsTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeBoundsTree$module == null) {
            this.TypeBoundsTree$module = new Trees.TypeBoundsTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ExistentialTypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ExistentialTypeTree$module == null) {
            this.ExistentialTypeTree$module = new Trees.ExistentialTypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeTree$module == null) {
            this.TypeTree$module = new Trees.TypeTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Modifiers$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Modifiers$module == null) {
            this.Modifiers$module = new Trees.Modifiers$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void EmptyTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.EmptyTree$module == null) {
            this.EmptyTree$module = new Trees.EmptyTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void noSelfType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.noSelfType$module == null) {
            this.noSelfType$module = new Trees.noSelfType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void pendingSuperCall$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.pendingSuperCall$module == null) {
            this.pendingSuperCall$module = new Trees.pendingSuperCall$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void duplicateAndResetPos$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.duplicateAndResetPos$module == null) {
            this.duplicateAndResetPos$module = new Trees.duplicateAndResetPos$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void scala$reflect$internal$Trees$$focuser$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.focuser$module == null) {
            this.focuser$module = new Trees.focuser$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnmappableAnnotArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnmappableAnnotArg$module == null) {
            this.UnmappableAnnotArg$module = new AnnotationInfos.UnmappableAnnotArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LiteralAnnotArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LiteralAnnotArg$module == null) {
            this.LiteralAnnotArg$module = new AnnotationInfos.LiteralAnnotArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ArrayAnnotArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ArrayAnnotArg$module == null) {
            this.ArrayAnnotArg$module = new AnnotationInfos.ArrayAnnotArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NestedAnnotArg$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NestedAnnotArg$module == null) {
            this.NestedAnnotArg$module = new AnnotationInfos.NestedAnnotArg$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void AnnotationInfo$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.AnnotationInfo$module == null) {
            this.AnnotationInfo$module = new AnnotationInfos.AnnotationInfo$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Annotation$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Annotation$module == null) {
            this.Annotation$module = new AnnotationInfos.Annotation$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnmappableAnnotation$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnmappableAnnotation$module == null) {
            this.UnmappableAnnotation$module = new AnnotationInfos.UnmappableAnnotation$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ThrownException$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ThrownException$module == null) {
            this.ThrownException$module = new AnnotationInfos.ThrownException$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void scala$reflect$internal$StdNames$$compactify$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.compactify$module == null) {
            this.compactify$module = new StdNames.compactify$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void tpnme$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.tpnme$module == null) {
            this.tpnme$module = new StdNames.tpnme$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void fulltpnme$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.fulltpnme$module == null) {
            this.fulltpnme$module = new StdNames.fulltpnme$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void binarynme$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.binarynme$module == null) {
            this.binarynme$module = new StdNames.binarynme$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void nme$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.nme$module == null) {
            this.nme$module = new StdNames.nme$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Constant$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Constant$module == null) {
            this.Constant$module = new Constants.Constant$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void definitions$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.definitions$module == null) {
            this.definitions$module = new Definitions.definitions$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LookupSucceeded$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LookupSucceeded$module == null) {
            this.LookupSucceeded$module = new Scopes.LookupSucceeded$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LookupAmbiguous$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LookupAmbiguous$module == null) {
            this.LookupAmbiguous$module = new Scopes.LookupAmbiguous$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LookupInaccessible$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LookupInaccessible$module == null) {
            this.LookupInaccessible$module = new Scopes.LookupInaccessible$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LookupNotFound$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LookupNotFound$module == null) {
            this.LookupNotFound$module = new Scopes.LookupNotFound$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Scope$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Scope$module == null) {
            this.Scope$module = new Scopes.Scope$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void EmptyScope$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.EmptyScope$module == null) {
            this.EmptyScope$module = new Scopes.EmptyScope$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Flag$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Flag$module == null) {
            this.Flag$module = new FlagSets.Flag$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void KindErrors$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.KindErrors$module == null) {
            this.KindErrors$module = new Kinds.KindErrors$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void Kind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Kind$module == null) {
            this.Kind$module = new Kinds.Kind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ProperTypeKind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ProperTypeKind$module == null) {
            this.ProperTypeKind$module = new Kinds.ProperTypeKind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeConKind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeConKind$module == null) {
            this.TypeConKind$module = new Kinds.TypeConKind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void inferKind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.inferKind$module == null) {
            this.inferKind$module = new Kinds.inferKind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void scala$reflect$internal$Types$$substTypeMapCache$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.substTypeMapCache$module == null) {
            this.substTypeMapCache$module = new Types.substTypeMapCache$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnmappableTree$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnmappableTree$module == null) {
            this.UnmappableTree$module = new Types.UnmappableTree$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ErrorType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ErrorType$module == null) {
            this.ErrorType$module = new Types.ErrorType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void WildcardType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.WildcardType$module == null) {
            this.WildcardType$module = new Types.WildcardType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void BoundedWildcardType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.BoundedWildcardType$module == null) {
            this.BoundedWildcardType$module = new Types.BoundedWildcardType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void OverloadedArgProto$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.OverloadedArgProto$module == null) {
            this.OverloadedArgProto$module = new Types.OverloadedArgProto$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NoType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoType$module == null) {
            this.NoType$module = new Types.NoType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NoPrefix$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoPrefix$module == null) {
            this.NoPrefix$module = new Types.NoPrefix$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ThisType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ThisType$module == null) {
            this.ThisType$module = new Types.ThisType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SingleType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SingleType$module == null) {
            this.SingleType$module = new Types.SingleType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SuperType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SuperType$module == null) {
            this.SuperType$module = new Types.SuperType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeBounds$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeBounds$module == null) {
            this.TypeBounds$module = new Types.TypeBounds$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CompoundType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CompoundType$module == null) {
            this.CompoundType$module = new Types.CompoundType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RefinedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RefinedType$module == null) {
            this.RefinedType$module = new Types.RefinedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ClassInfoType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ClassInfoType$module == null) {
            this.ClassInfoType$module = new Types.ClassInfoType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ConstantType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ConstantType$module == null) {
            this.ConstantType$module = new Types.ConstantType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void FoldableConstantType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.FoldableConstantType$module == null) {
            this.FoldableConstantType$module = new Types.FoldableConstantType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void LiteralType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.LiteralType$module == null) {
            this.LiteralType$module = new Types.LiteralType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeRef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeRef$module == null) {
            this.TypeRef$module = new Types.TypeRef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void MethodType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.MethodType$module == null) {
            this.MethodType$module = new Types.MethodType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NullaryMethodType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NullaryMethodType$module == null) {
            this.NullaryMethodType$module = new Types.NullaryMethodType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void PolyType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.PolyType$module == null) {
            this.PolyType$module = new Types.PolyType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ExistentialType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ExistentialType$module == null) {
            this.ExistentialType$module = new Types.ExistentialType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void OverloadedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.OverloadedType$module == null) {
            this.OverloadedType$module = new Types.OverloadedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ImportType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ImportType$module == null) {
            this.ImportType$module = new Types.ImportType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void AntiPolyType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.AntiPolyType$module == null) {
            this.AntiPolyType$module = new Types.AntiPolyType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void HasTypeMember$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.HasTypeMember$module == null) {
            this.HasTypeMember$module = new Types.HasTypeMember$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ArrayTypeRef$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ArrayTypeRef$module == null) {
            this.ArrayTypeRef$module = new Types.ArrayTypeRef$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeVar$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeVar$module == null) {
            this.TypeVar$module = new Types.TypeVar$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void AnnotatedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.AnnotatedType$module == null) {
            this.AnnotatedType$module = new Types.AnnotatedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void StaticallyAnnotatedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.StaticallyAnnotatedType$module == null) {
            this.StaticallyAnnotatedType$module = new Types.StaticallyAnnotatedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NamedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NamedType$module == null) {
            this.NamedType$module = new Types.NamedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RepeatedType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RepeatedType$module == null) {
            this.RepeatedType$module = new Types.RepeatedType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ErasedValueType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ErasedValueType$module == null) {
            this.ErasedValueType$module = new Types.ErasedValueType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void GenPolyType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.GenPolyType$module == null) {
            this.GenPolyType$module = new Types.GenPolyType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void unwrapToClass$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.unwrapToClass$module == null) {
            this.unwrapToClass$module = new Types.unwrapToClass$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void unwrapToStableClass$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.unwrapToStableClass$module == null) {
            this.unwrapToStableClass$module = new Types.unwrapToStableClass$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void unwrapWrapperTypes$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.unwrapWrapperTypes$module == null) {
            this.unwrapWrapperTypes$module = new Types.unwrapWrapperTypes$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RecoverableCyclicReference$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RecoverableCyclicReference$module == null) {
            this.RecoverableCyclicReference$module = new Types.RecoverableCyclicReference$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeConstraint$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeConstraint$module == null) {
            this.TypeConstraint$module = new TypeConstraints.TypeConstraint$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void normalizeAliases$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.normalizeAliases$module == null) {
            this.normalizeAliases$module = new TypeMaps.normalizeAliases$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void dropSingletonType$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.dropSingletonType$module == null) {
            this.dropSingletonType$module = new TypeMaps.dropSingletonType$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void abstractTypesToBounds$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.abstractTypesToBounds$module == null) {
            this.abstractTypesToBounds$module = new TypeMaps.abstractTypesToBounds$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void dropIllegalStarTypes$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.dropIllegalStarTypes$module == null) {
            this.dropIllegalStarTypes$module = new TypeMaps.dropIllegalStarTypes$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void wildcardExtrapolation$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.wildcardExtrapolation$module == null) {
            this.wildcardExtrapolation$module = new TypeMaps.wildcardExtrapolation$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SubstSymMap$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SubstSymMap$module == null) {
            this.SubstSymMap$module = new TypeMaps.SubstSymMap$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void IsDependentCollector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.IsDependentCollector$module == null) {
            this.IsDependentCollector$module = new TypeMaps.IsDependentCollector$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ApproximateDependentMap$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ApproximateDependentMap$module == null) {
            this.ApproximateDependentMap$module = new TypeMaps.ApproximateDependentMap$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void identityTypeMap$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.identityTypeMap$module == null) {
            this.identityTypeMap$module = new TypeMaps.identityTypeMap$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void typeVarToOriginMap$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.typeVarToOriginMap$module == null) {
            this.typeVarToOriginMap$module = new TypeMaps.typeVarToOriginMap$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ErroneousCollector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ErroneousCollector$module == null) {
            this.ErroneousCollector$module = new TypeMaps.ErroneousCollector$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void adaptToNewRunMap$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.adaptToNewRunMap$module == null) {
            this.adaptToNewRunMap$module = new TypeMaps.adaptToNewRunMap$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void UnrelatableCollector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.UnrelatableCollector$module == null) {
            this.UnrelatableCollector$module = new TypeMaps.UnrelatableCollector$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void IsRelatableCollector$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.IsRelatableCollector$module == null) {
            this.IsRelatableCollector$module = new TypeMaps.IsRelatableCollector$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SubTypePair$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SubTypePair$module == null) {
            this.SubTypePair$module = new TypeComparers.SubTypePair$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SymbolKind$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SymbolKind$module == null) {
            this.SymbolKind$module = new Symbols.SymbolKind$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void CyclicReference$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.CyclicReference$module == null) {
            this.CyclicReference$module = new Symbols.CyclicReference$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void scala$reflect$internal$Symbols$$TypeHistory$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeHistory$module == null) {
            this.TypeHistory$module = new Symbols.TypeHistory$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void SymbolOps$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.SymbolOps$module == null) {
            this.SymbolOps$module = new Symbols.SymbolOps$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TermName$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TermName$module == null) {
            this.TermName$module = new Names.TermName$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void TypeName$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.TypeName$module == null) {
            this.TypeName$module = new Names.TypeName$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   // $FF: synthetic method
   public static final String $anonfun$assert$1() {
      return "";
   }

   // $FF: synthetic method
   public static final String $anonfun$require$1() {
      return "";
   }

   // $FF: synthetic method
   public static final Symbols.NoSymbol $anonfun$findSymbol$1(final SymbolTable $this) {
      return $this.NoSymbol();
   }

   private final boolean noChangeInBaseClasses$1(final InfoTransformers.InfoTransformer it, final int limit) {
      while(true) {
         if (it.pid() < limit) {
            if (!it.changesBaseClasses()) {
               InfoTransformers.InfoTransformer var10000 = it.next();
               limit = limit;
               it = var10000;
               continue;
            }

            return false;
         }

         return true;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$openPackageModule$2(final Symbols.Symbol dest$1, final Symbols.Symbol existing) {
      dest$1.info().decls().unlink(existing);
   }

   // $FF: synthetic method
   public static final void $anonfun$openPackageModule$1(final Symbols.Symbol dest$1, final Symbols.Symbol member) {
      if (!member.isPrivate() && !member.isConstructor()) {
         List var10000 = dest$1.info().decl(member.name()).alternatives();
         if (var10000 == null) {
            throw null;
         } else {
            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               Symbols.Symbol var3 = (Symbols.Symbol)foreach_these.head();
               $anonfun$openPackageModule$2(dest$1, var3);
            }

         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$openPackageModule$4(final SymbolTable $this, final Symbols.Symbol dest$1, final Symbols.Symbol p) {
      label14: {
         Symbols.ClassSymbol var3 = $this.definitions().ObjectClass();
         if (p == null) {
            if (var3 != null) {
               break label14;
            }
         } else if (!p.equals(var3)) {
            break label14;
         }

         return;
      }

      $this.openPackageModule(p, dest$1);
   }

   // $FF: synthetic method
   public static final List $anonfun$arrayToRepeated$1(final List formals$1) {
      return formals$1;
   }

   private static final boolean fromSource$1(final Symbols.Symbol pkgModule$1) {
      Types.Type var1 = pkgModule$1.rawInfo();
      return var1 instanceof SymLoader ? ((SymLoader)var1).fromSource() : false;
   }

   // $FF: synthetic method
   public static final NoPhase$ $anonfun$phaseWithId$1() {
      return NoPhase$.MODULE$;
   }

   public SymbolTable() {
      Collections.$init$(this);
      Names.$init$(this);
      Symbols.$init$(this);
      TypeComparers.$init$(this);
      TypeToStrings.$init$(this);
      GlbLubs.$init$(this);
      TypeConstraints.$init$(this);
      scala.reflect.internal.tpe.FindMembers.$init$(this);
      Types.$init$(this);
      Variances.$init$(this);
      Kinds.$init$(this);
      FlagSets.$init$(this);
      Scopes.$init$(this);
      Constants.$init$(this);
      BaseTypeSeqs.$init$(this);
      Transforms.$init$(this);
      StdNames.$init$(this);
      AnnotationInfos.$init$(this);
      this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(scala.collection.immutable.Nil..MODULE$);
      Trees.$init$(this);
      Positions.$init$(this);
      ReificationSupport.$init$(this);
      FreshNames.$init$(this);
      this.gen = new scala.reflect.internal.TreeGen() {
         private final SymbolTable global = SymbolTable.this;

         public SymbolTable global() {
            return this.global;
         }
      };
      this.traceSymbolActivity = System.getProperty("scalac.debug.syms") != null;
      Stack var10003 = scala.collection.mutable.Stack..MODULE$;
      this.phStack = new Stack(16);
      this.ph = NoPhase$.MODULE$;
      this.per = 0;
      this.keepPhaseStack = false;
      this.infoTransformers = new InfoTransformers.InfoTransformer() {
         private final int pid;
         private final boolean changesBaseClasses;

         public int pid() {
            return this.pid;
         }

         public boolean changesBaseClasses() {
            return this.changesBaseClasses;
         }

         public Types.Type transform(final Symbols.Symbol sym, final Types.Type tpe) {
            return tpe;
         }

         public {
            this.pid = NoPhase$.MODULE$.id();
            this.changesBaseClasses = true;
         }
      };
      this.nextFrom = null;
      int fill_n = 256;
      Object var10001;
      if (fill_n <= 0) {
         var10001 = new Phase[0];
      } else {
         Object fill_array = new Phase[fill_n];

         for(int fill_i = 0; fill_i < fill_n; ++fill_i) {
            Object array_update_value = NoPhase$.MODULE$;
            ((Object[])fill_array)[fill_i] = array_update_value;
            array_update_value = null;
         }

         var10001 = fill_array;
      }

      Object var5 = null;
      this.phaseWithId = (Phase[])var10001;
      Statics.releaseFence();
   }

   // $FF: synthetic method
   public static final Object $anonfun$openPackageModule$2$adapted(final Symbols.Symbol dest$1, final Symbols.Symbol existing) {
      $anonfun$openPackageModule$2(dest$1, existing);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class SimpleNameOrdering$ implements Ordering {
      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
      }

      public boolean lteq(final Object x, final Object y) {
         return Ordering.lteq$(this, x, y);
      }

      public boolean gteq(final Object x, final Object y) {
         return Ordering.gteq$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Ordering.lt$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Ordering.gt$(this, x, y);
      }

      public boolean equiv(final Object x, final Object y) {
         return Ordering.equiv$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Ordering.max$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Ordering.min$(this, x, y);
      }

      public Ordering reverse() {
         return Ordering.reverse$(this);
      }

      public boolean isReverseOf(final Ordering other) {
         return Ordering.isReverseOf$(this, other);
      }

      public Ordering on(final Function1 f) {
         return Ordering.on$(this, f);
      }

      public Ordering orElse(final Ordering other) {
         return Ordering.orElse$(this, other);
      }

      public Ordering orElseBy(final Function1 f, final Ordering ord) {
         return Ordering.orElseBy$(this, f, ord);
      }

      public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
         return Ordering.mkOrderingOps$(this, lhs);
      }

      public int compare(final Names.Name n1, final Names.Name n2) {
         return n1 == n2 ? 0 : n1.toString().compareTo(n2.toString());
      }

      public SimpleNameOrdering$() {
      }
   }

   public class traceSymbols$ implements TraceSymbolActivity {
      private final SymbolTable global;
      private boolean scala$reflect$internal$util$TraceSymbolActivity$$enabled;
      private scala.collection.mutable.Map allSymbols;
      private scala.collection.mutable.Map allChildren;
      private scala.collection.mutable.Map prevOwners;
      private scala.collection.mutable.Set allTrees;
      private Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase;
      private volatile boolean bitmap$0;

      public void recordSymbolsInTree(final Trees.Tree tree) {
         TraceSymbolActivity.recordSymbolsInTree$(this, tree);
      }

      public void recordNewSymbol(final Symbols.Symbol sym) {
         TraceSymbolActivity.recordNewSymbol$(this, sym);
      }

      public void recordNewSymbolOwner(final Symbols.Symbol sym, final Symbols.Symbol newOwner) {
         TraceSymbolActivity.recordNewSymbolOwner$(this, sym, newOwner);
      }

      public void showAllSymbols() {
         TraceSymbolActivity.showAllSymbols$(this);
      }

      public boolean scala$reflect$internal$util$TraceSymbolActivity$$enabled() {
         return this.scala$reflect$internal$util$TraceSymbolActivity$$enabled;
      }

      public void scala$reflect$internal$util$TraceSymbolActivity$$enabled_$eq(final boolean x$1) {
         this.scala$reflect$internal$util$TraceSymbolActivity$$enabled = x$1;
      }

      public scala.collection.mutable.Map allSymbols() {
         return this.allSymbols;
      }

      public scala.collection.mutable.Map allChildren() {
         return this.allChildren;
      }

      public scala.collection.mutable.Map prevOwners() {
         return this.prevOwners;
      }

      public scala.collection.mutable.Set allTrees() {
         return this.allTrees;
      }

      private Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase = TraceSymbolActivity.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase$(this);
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase;
      }

      public Phase scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase() {
         return !this.bitmap$0 ? this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase$lzycompute() : this.scala$reflect$internal$util$TraceSymbolActivity$$erasurePhase;
      }

      public void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allSymbols_$eq(final scala.collection.mutable.Map x$1) {
         this.allSymbols = x$1;
      }

      public void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allChildren_$eq(final scala.collection.mutable.Map x$1) {
         this.allChildren = x$1;
      }

      public void scala$reflect$internal$util$TraceSymbolActivity$_setter_$prevOwners_$eq(final scala.collection.mutable.Map x$1) {
         this.prevOwners = x$1;
      }

      public void scala$reflect$internal$util$TraceSymbolActivity$_setter_$allTrees_$eq(final scala.collection.mutable.Set x$1) {
         this.allTrees = x$1;
      }

      public SymbolTable global() {
         return this.global;
      }

      public traceSymbols$() {
         this.global = SymbolTable.this;
         TraceSymbolActivity.$init$(this);
         Statics.releaseFence();
      }
   }

   public abstract class SymLoader extends Types.LazyType {
      public boolean fromSource() {
         return false;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$SymbolTable$SymLoader$$$outer() {
         return (SymbolTable)this.$outer;
      }
   }

   public class perRunCaches$ {
      private List caches;
      private List javaCaches;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Clearable recordCache(final Clearable cache) {
         if (cache instanceof JavaClearable) {
            JavaClearable var2 = (JavaClearable)cache;
            List var10001 = this.javaCaches;
            if (var10001 == null) {
               throw null;
            }

            List $colon$colon_this = var10001;
            scala.collection.immutable..colon.colon var7 = new scala.collection.immutable..colon.colon(var2, $colon$colon_this);
            Object var6 = null;
            this.javaCaches = var7;
         } else {
            List var8 = this.caches;
            WeakReference $colon$colon_elem = new WeakReference(cache);
            if (var8 == null) {
               throw null;
            }

            List $colon$colon_this = var8;
            this.caches = new scala.collection.immutable..colon.colon($colon$colon_elem, $colon$colon_this);
         }

         return cache;
      }

      public final ClassLoader recordClassloader(final ClassLoader loader) {
         List var10001 = this.caches;
         WeakReference $colon$colon_elem = new WeakReference(new Clearable(loader) {
            // $FF: synthetic field
            private final perRunCaches$ $outer;
            private final ClassLoader loader$1;

            public void clear() {
               this.$outer.scala$reflect$internal$SymbolTable$perRunCaches$$attemptClose$1(this.loader$1);
            }

            public {
               if (perRunCaches$.this == null) {
                  throw null;
               } else {
                  this.$outer = perRunCaches$.this;
                  this.loader$1 = loader$1;
               }
            }
         });
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            this.caches = new scala.collection.immutable..colon.colon($colon$colon_elem, $colon$colon_this);
            return loader;
         }
      }

      public void unrecordCache(final Clearable cache) {
         if (cache instanceof JavaClearable) {
            List var103 = this.javaCaches;
            if (var103 == null) {
               throw null;
            } else {
               List filterNot_this = var103;
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = filterNot_this;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var104 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  JavaClearable var34 = (JavaClearable)filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$unrecordCache$1(cache, var34) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var104 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var34 = (JavaClearable)filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$unrecordCache$1(cache, var34) == filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var34 = (JavaClearable)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$unrecordCache$1(cache, var34) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                           var104 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var43 = null;
                           Object var46 = null;
                           Object var49 = null;
                           Object var52 = null;
                           Object var55 = null;
                           Object var58 = null;
                           Object var61 = null;
                           Object var64 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var39 = null;
                     Object var41 = null;
                     Object var44 = null;
                     Object var47 = null;
                     Object var50 = null;
                     Object var53 = null;
                     Object var56 = null;
                     Object var59 = null;
                     Object var62 = null;
                     Object var65 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var36 = null;
               Object var37 = null;
               Object var38 = null;
               Object var40 = null;
               Object var42 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               Object var60 = null;
               Object var63 = null;
               Object var66 = null;
               List filterNot_filterCommon_result = (List)var104;
               Statics.releaseFence();
               this.javaCaches = filterNot_filterCommon_result;
            }
         } else {
            List var10001 = this.caches;
            if (var10001 == null) {
               throw null;
            } else {
               List filterNot_this = var10001;
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = filterNot_this;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var102 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  WeakReference var35 = (WeakReference)filterNot_filterCommon_noneIn$1_h;
                  if ($anonfun$unrecordCache$2(cache, var35) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var102 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        var35 = (WeakReference)filterNot_filterCommon_noneIn$1_allIn$1_x;
                        if ($anonfun$unrecordCache$2(cache, var35) == filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              var35 = (WeakReference)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head;
                              if ($anonfun$unrecordCache$2(cache, var35) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                           var102 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
                           Object var74 = null;
                           Object var77 = null;
                           Object var80 = null;
                           Object var83 = null;
                           Object var86 = null;
                           Object var89 = null;
                           Object var92 = null;
                           Object var95 = null;
                           break;
                        }

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

                     Object var70 = null;
                     Object var72 = null;
                     Object var75 = null;
                     Object var78 = null;
                     Object var81 = null;
                     Object var84 = null;
                     Object var87 = null;
                     Object var90 = null;
                     Object var93 = null;
                     Object var96 = null;
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var67 = null;
               Object var68 = null;
               Object var69 = null;
               Object var71 = null;
               Object var73 = null;
               Object var76 = null;
               Object var79 = null;
               Object var82 = null;
               Object var85 = null;
               Object var88 = null;
               Object var91 = null;
               Object var94 = null;
               Object var97 = null;
               List filterNot_filterCommon_result = (List)var102;
               Statics.releaseFence();
               this.caches = filterNot_filterCommon_result;
            }
         }
      }

      public void clearAll() {
         this.$outer.debuglog(() -> {
            StringBuilder var10000 = (new StringBuilder(17)).append("Clearing ");
            List var10001 = this.caches;
            if (var10001 == null) {
               throw null;
            } else {
               int var1 = SeqOps.size$(var10001);
               List var10002 = this.javaCaches;
               if (var10002 == null) {
                  throw null;
               } else {
                  return var10000.append(var1 + SeqOps.size$(var10002)).append(" caches.").toString();
               }
            }
         });
         List var10000 = this.caches;
         if (var10000 == null) {
            throw null;
         } else {
            for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
               $anonfun$clearAll$2((WeakReference)foreach_these.head());
            }

            Object var35 = null;
            List var10001 = this.caches;
            if (var10001 == null) {
               throw null;
            } else {
               List filterNot_this = var10001;
               boolean filterNot_filterCommon_isFlipped = true;
               List filterNot_filterCommon_noneIn$1_l = filterNot_this;

               while(true) {
                  if (filterNot_filterCommon_noneIn$1_l.isEmpty()) {
                     var102 = scala.collection.immutable.Nil..MODULE$;
                     break;
                  }

                  Object filterNot_filterCommon_noneIn$1_h = filterNot_filterCommon_noneIn$1_l.head();
                  List filterNot_filterCommon_noneIn$1_t = (List)filterNot_filterCommon_noneIn$1_l.tail();
                  if ($anonfun$clearAll$4((WeakReference)filterNot_filterCommon_noneIn$1_h) != filterNot_filterCommon_isFlipped) {
                     List filterNot_filterCommon_noneIn$1_allIn$1_remaining = filterNot_filterCommon_noneIn$1_t;

                     while(true) {
                        if (filterNot_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                           var102 = filterNot_filterCommon_noneIn$1_l;
                           break;
                        }

                        Object filterNot_filterCommon_noneIn$1_allIn$1_x = filterNot_filterCommon_noneIn$1_allIn$1_remaining.head();
                        if ($anonfun$clearAll$4((WeakReference)filterNot_filterCommon_noneIn$1_allIn$1_x) == filterNot_filterCommon_isFlipped) {
                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_l.tail();

                           scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                           for(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filterNot_filterCommon_noneIn$1_allIn$1_remaining; filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                              scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                              filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                           }

                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                           List filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                           while(!filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                              Object filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                              if ($anonfun$clearAll$4((WeakReference)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_head) != filterNot_filterCommon_isFlipped) {
                                 filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                              } else {
                                 while(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                    scala.collection.immutable..colon.colon filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                           var102 = filterNot_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
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

                        filterNot_filterCommon_noneIn$1_allIn$1_remaining = (List)filterNot_filterCommon_noneIn$1_allIn$1_remaining.tail();
                     }

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
                     break;
                  }

                  filterNot_filterCommon_noneIn$1_l = filterNot_filterCommon_noneIn$1_t;
               }

               Object var39 = null;
               Object var40 = null;
               Object var41 = null;
               Object var43 = null;
               Object var45 = null;
               Object var48 = null;
               Object var51 = null;
               Object var54 = null;
               Object var57 = null;
               Object var60 = null;
               Object var63 = null;
               Object var66 = null;
               Object var69 = null;
               List filterNot_filterCommon_result = (List)var102;
               Statics.releaseFence();
               Object var103 = filterNot_filterCommon_result;
               Object var37 = null;
               filterNot_filterCommon_result = null;
               this.caches = (List)var103;
               var10000 = this.javaCaches;
               if (var10000 == null) {
                  throw null;
               } else {
                  for(List foreach_these = var10000; !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
                     ((JavaClearable)foreach_these.head()).clear();
                  }

                  Object var36 = null;
                  List var104 = this.javaCaches;
                  if (var104 == null) {
                     throw null;
                  } else {
                     List filter_this = var104;
                     boolean filter_filterCommon_isFlipped = false;
                     List filter_filterCommon_noneIn$1_l = filter_this;

                     while(true) {
                        if (filter_filterCommon_noneIn$1_l.isEmpty()) {
                           var105 = scala.collection.immutable.Nil..MODULE$;
                           break;
                        }

                        Object filter_filterCommon_noneIn$1_h = filter_filterCommon_noneIn$1_l.head();
                        List filter_filterCommon_noneIn$1_t = (List)filter_filterCommon_noneIn$1_l.tail();
                        if (((JavaClearable)filter_filterCommon_noneIn$1_h).isValid() != filter_filterCommon_isFlipped) {
                           List filter_filterCommon_noneIn$1_allIn$1_remaining = filter_filterCommon_noneIn$1_t;

                           while(true) {
                              if (filter_filterCommon_noneIn$1_allIn$1_remaining.isEmpty()) {
                                 var105 = filter_filterCommon_noneIn$1_l;
                                 break;
                              }

                              Object filter_filterCommon_noneIn$1_allIn$1_x = filter_filterCommon_noneIn$1_allIn$1_remaining.head();
                              if (((JavaClearable)filter_filterCommon_noneIn$1_allIn$1_x).isValid() == filter_filterCommon_isFlipped) {
                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_l.head(), scala.collection.immutable.Nil..MODULE$);
                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_l.tail();

                                 scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast;
                                 for(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess != filter_filterCommon_noneIn$1_allIn$1_remaining; filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.tail()) {
                                    scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_toProcess.head(), scala.collection.immutable.Nil..MODULE$);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast.next_$eq(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem);
                                    filter_filterCommon_noneIn$1_allIn$1_partialFill$1_currentLast = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem;
                                 }

                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
                                 List filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next;

                                 while(!filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.isEmpty()) {
                                    Object filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.head();
                                    if (((JavaClearable)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_head).isValid() != filter_filterCommon_isFlipped) {
                                       filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next = (List)filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next.tail();
                                    } else {
                                       while(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy != filter_filterCommon_noneIn$1_allIn$1_partialFill$1_next) {
                                          scala.collection.immutable..colon.colon filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newElem = new scala.collection.immutable..colon.colon(filter_filterCommon_noneIn$1_allIn$1_partialFill$1_nextToCopy.head(), scala.collection.immutable.Nil..MODULE$);
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

                                 var105 = filter_filterCommon_noneIn$1_allIn$1_partialFill$1_newHead;
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

                              filter_filterCommon_noneIn$1_allIn$1_remaining = (List)filter_filterCommon_noneIn$1_allIn$1_remaining.tail();
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

                        filter_filterCommon_noneIn$1_l = filter_filterCommon_noneIn$1_t;
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
                     List filter_filterCommon_result = (List)var105;
                     Statics.releaseFence();
                     this.javaCaches = filter_filterCommon_result;
                  }
               }
            }
         }
      }

      public WeakHashMap newWeakMap() {
         WeakHashMap var10001 = scala.collection.mutable.WeakHashMap..MODULE$;
         return (WeakHashMap)this.recordCache(new WeakHashMap());
      }

      public HashMap newMap() {
         HashMap var10001 = scala.collection.mutable.HashMap..MODULE$;
         return (HashMap)this.recordCache(new HashMap());
      }

      public HashSet newSet() {
         HashSet var10001 = scala.collection.mutable.HashSet..MODULE$;
         return (HashSet)this.recordCache(new HashSet());
      }

      public WeakHashSet newWeakSet() {
         WeakHashSet$ var10001 = WeakHashSet$.MODULE$;
         return (WeakHashSet)this.recordCache(new WeakHashSet());
      }

      public AnyRefMap newAnyRefMap() {
         AnyRefMap var10001 = scala.collection.mutable.AnyRefMap..MODULE$;
         return (AnyRefMap)this.recordCache(new AnyRefMap());
      }

      public AnyRefMap newAnyRefMap(final Function1 default) {
         AnyRefMap var10001 = scala.collection.mutable.AnyRefMap..MODULE$;
         return (AnyRefMap)this.recordCache(new AnyRefMap(default));
      }

      public Function0 newGeneric(final Function0 f, final Function1 cleanup) {
         ObjectRef cached = new ObjectRef((Object)null);
         int create_e = 0;
         IntRef cachedRunId = new IntRef(create_e);
         Clearable clearable = new Clearable(cached, (Object)null, cleanup, cachedRunId, f) {
            // $FF: synthetic field
            private final perRunCaches$ $outer;
            private final ObjectRef cached$1;
            private final Object NoCached$1;
            private final Function1 cleanup$1;
            private final IntRef cachedRunId$1;
            private final Function0 f$1;

            public boolean apply$mcZ$sp() {
               return Function0.apply$mcZ$sp$(this);
            }

            public byte apply$mcB$sp() {
               return Function0.apply$mcB$sp$(this);
            }

            public char apply$mcC$sp() {
               return Function0.apply$mcC$sp$(this);
            }

            public double apply$mcD$sp() {
               return Function0.apply$mcD$sp$(this);
            }

            public float apply$mcF$sp() {
               return Function0.apply$mcF$sp$(this);
            }

            public int apply$mcI$sp() {
               return Function0.apply$mcI$sp$(this);
            }

            public long apply$mcJ$sp() {
               return Function0.apply$mcJ$sp$(this);
            }

            public short apply$mcS$sp() {
               return Function0.apply$mcS$sp$(this);
            }

            public void apply$mcV$sp() {
               Function0.apply$mcV$sp$(this);
            }

            public String toString() {
               return Function0.toString$(this);
            }

            public void clear() {
               if (!BoxesRunTime.equals(this.cached$1.elem, this.NoCached$1)) {
                  this.cleanup$1.apply(this.cached$1.elem);
               }

               this.cached$1.elem = this.NoCached$1;
            }

            public Object apply() {
               if (this.$outer.scala$reflect$internal$SymbolTable$perRunCaches$$$outer().currentRunId() != this.cachedRunId$1.elem || BoxesRunTime.equals(this.cached$1.elem, this.NoCached$1)) {
                  this.cached$1.elem = this.f$1.apply();
                  this.cachedRunId$1.elem = this.$outer.scala$reflect$internal$SymbolTable$perRunCaches$$$outer().currentRunId();
               }

               return this.cached$1.elem;
            }

            public {
               if (perRunCaches$.this == null) {
                  throw null;
               } else {
                  this.$outer = perRunCaches$.this;
                  this.cached$1 = cached$1;
                  this.NoCached$1 = NoCached$1;
                  this.cleanup$1 = cleanup$1;
                  this.cachedRunId$1 = cachedRunId$1;
                  this.f$1 = f$1;
               }
            }
         };
         return (Function0)this.recordCache(clearable);
      }

      public Function1 newGeneric$default$2() {
         return (x) -> {
            $anonfun$newGeneric$default$2$1(x);
            return BoxedUnit.UNIT;
         };
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$SymbolTable$perRunCaches$$$outer() {
         return this.$outer;
      }

      public final void scala$reflect$internal$SymbolTable$perRunCaches$$attemptClose$1(final ClassLoader loader) {
         if (loader instanceof URLClassLoader) {
            URLClassLoader var2 = (URLClassLoader)loader;
            this.$outer.debuglog(() -> (new StringBuilder(20)).append("Closing classloader ").append(var2).toString());
            var2.close();
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$unrecordCache$1(final Clearable cache$1, final JavaClearable x$2) {
         if (cache$1 == null) {
            if (x$2 == null) {
               return true;
            }
         } else if (cache$1.equals(x$2)) {
            return true;
         }

         return false;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$unrecordCache$2(final Clearable cache$1, final WeakReference x$3) {
         return x$3.get() == cache$1;
      }

      // $FF: synthetic method
      public static final void $anonfun$clearAll$3(final Clearable x$4) {
         x$4.clear();
      }

      // $FF: synthetic method
      public static final void $anonfun$clearAll$2(final WeakReference ref) {
         Option var10000 = scala.Option..MODULE$.apply(ref.get());
         if (var10000 == null) {
            throw null;
         } else {
            Option foreach_this = var10000;
            if (!foreach_this.isEmpty()) {
               ((Clearable)foreach_this.get()).clear();
            }
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$clearAll$4(final WeakReference x$5) {
         return x$5.get() == null;
      }

      // $FF: synthetic method
      public static final void $anonfun$clearAll$5(final JavaClearable x$6) {
         x$6.clear();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$clearAll$6(final JavaClearable x$7) {
         return x$7.isValid();
      }

      // $FF: synthetic method
      public static final void $anonfun$newGeneric$default$2$1(final Object x) {
      }

      public perRunCaches$() {
         if (SymbolTable.this == null) {
            throw null;
         } else {
            this.$outer = SymbolTable.this;
            super();
            this.caches = scala.collection.immutable.Nil..MODULE$;
            this.javaCaches = scala.collection.immutable.Nil..MODULE$;
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$unrecordCache$1$adapted(final Clearable cache$1, final JavaClearable x$2) {
         return BoxesRunTime.boxToBoolean($anonfun$unrecordCache$1(cache$1, x$2));
      }

      // $FF: synthetic method
      public static final Object $anonfun$unrecordCache$2$adapted(final Clearable cache$1, final WeakReference x$3) {
         return BoxesRunTime.boxToBoolean($anonfun$unrecordCache$2(cache$1, x$3));
      }

      // $FF: synthetic method
      public static final Object $anonfun$clearAll$2$adapted(final WeakReference ref) {
         $anonfun$clearAll$2(ref);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$clearAll$4$adapted(final WeakReference x$5) {
         return BoxesRunTime.boxToBoolean($anonfun$clearAll$4(x$5));
      }

      // $FF: synthetic method
      public static final Object $anonfun$clearAll$5$adapted(final JavaClearable x$6) {
         $anonfun$clearAll$5(x$6);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final Object $anonfun$clearAll$6$adapted(final JavaClearable x$7) {
         return BoxesRunTime.boxToBoolean($anonfun$clearAll$6(x$7));
      }

      // $FF: synthetic method
      public static final Object $anonfun$clearAll$3$adapted(final Clearable x$4) {
         $anonfun$clearAll$3(x$4);
         return BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface ReflectStats extends BaseTypeSeqsStats, TypesStats, SymbolTableStats, TreesStats, SymbolsStats, ScopeStats {
   }
}
