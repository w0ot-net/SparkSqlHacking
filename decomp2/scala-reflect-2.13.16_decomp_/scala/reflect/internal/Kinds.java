package scala.reflect.internal;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.settings.MutableSettings;
import scala.reflect.internal.settings.MutableSettings$;
import scala.reflect.internal.util.Collections;
import scala.reflect.internal.util.StatisticsStatics;
import scala.reflect.internal.util.StringOps$;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019%b\u0001DAX\u0003c\u0003\n1!\u0001\u0002@\u001a\r\u0002bBAe\u0001\u0011\u0005\u00111Z\u0003\u0007\u0003'\u0004A!!6\u0007\r\u0005\u001d\b\u0001QAu\u0011)\u0011Ia\u0001BK\u0002\u0013\u0005!1\u0002\u0005\u000b\u0005/\u0019!\u0011#Q\u0001\n\t5\u0001B\u0003B\r\u0007\tU\r\u0011\"\u0001\u0003\f!Q!1D\u0002\u0003\u0012\u0003\u0006IA!\u0004\t\u0015\tu1A!f\u0001\n\u0003\u0011Y\u0001\u0003\u0006\u0003 \r\u0011\t\u0012)A\u0005\u0005\u001bAqA!\t\u0004\t\u0003\u0011\u0019\u0003C\u0004\u0003.\r!\tAa\f\t\u000f\t]2\u0001\"\u0001\u0003:!9!qH\u0002\u0005\u0002\t\u0005\u0003b\u0002B#\u0007\u0011\u0005!q\t\u0005\b\u0005\u0017\u001aA\u0011\u0001B'\u0011\u001d\u0011\u0019f\u0001C\u0005\u0005+BqAa\u001b\u0004\t\u0013\u0011i\u0007C\u0004\u0003x\r!IA!\u001f\t\u000f\t=5\u0001\"\u0003\u0003\u0012\"9!qS\u0002\u0005\n\te\u0005b\u0002BP\u0007\u0011%!\u0011\u0015\u0005\b\u0005O\u001bA\u0011\u0002BU\u0011\u001d\u0011\u0019l\u0001C\u0001\u0005kC\u0011B!3\u0004\u0003\u0003%\tAa3\t\u0013\tM7!%A\u0005\u0002\tU\u0007\"\u0003Bv\u0007E\u0005I\u0011\u0001Bk\u0011%\u0011ioAI\u0001\n\u0003\u0011)\u000eC\u0005\u0003p\u000e\t\t\u0011\"\u0011\u0003r\"I1\u0011A\u0002\u0002\u0002\u0013\u000511\u0001\u0005\n\u0007\u0017\u0019\u0011\u0011!C\u0001\u0007\u001bA\u0011b!\u0007\u0004\u0003\u0003%\tea\u0007\t\u0013\r%2!!A\u0005\u0002\r-\u0002\"CB\u0018\u0007\u0005\u0005I\u0011IB\u0019\u0011%\u0019)dAA\u0001\n\u0003\u001a9\u0004C\u0005\u0004:\r\t\t\u0011\"\u0011\u0004<!I1QH\u0002\u0002\u0002\u0013\u00053qH\u0004\n\u0007\u0007\u0002\u0011\u0011!E\u0001\u0007\u000b2\u0011\"a:\u0001\u0003\u0003E\taa\u0012\t\u000f\t\u0005b\u0005\"\u0001\u0004b!I1\u0011\b\u0014\u0002\u0002\u0013\u001531\b\u0005\n\u0007G2\u0013\u0011!CA\u0007KB\u0011b!\u001c'#\u0003%\tA!6\t\u0013\r=d%%A\u0005\u0002\tU\u0007\"CB9ME\u0005I\u0011\u0001Bk\u0011%\u0019\u0019HJA\u0001\n\u0003\u001b)\bC\u0005\u0004\b\u001a\n\n\u0011\"\u0001\u0003V\"I1\u0011\u0012\u0014\u0012\u0002\u0013\u0005!Q\u001b\u0005\n\u0007\u00173\u0013\u0013!C\u0001\u0005+D\u0011b!$\u0001\u0005\u0004%\taa$\t\u000f\rE\u0005\u0001\"\u0001\u0004\u0014\"91\u0011\u0016\u0001\u0005\n\r-\u0006bBB[\u0001\u0011\u00051q\u0017\u0004\b\u0007\u0013\u0004\u0011\u0011ABf\u0011\u001d\u0011\t#\u000eC\u0001\u0007\u001bDqa!56\r\u0003\u0019\u0019\u000eC\u0004\u0004VV2\taa\u0001\t\u000f\r]WG\"\u0001\u0004Z\"91\u0011]\u001b\u0007\u0002\rM\u0007bBBrk\u0019\u000511\u001b\u0005\b\u0007K,D\u0011\u0001B\u0018\u0011%\u00199/\u000eD\u0001\u0003c\u001bIoB\u0004\u0004r\u0002A\taa=\u0007\u000f\r%\u0007\u0001#\u0001\u0004v\"9!\u0011E \u0005\u0002\r]haCB}\u007fA\u0005\u0019\u0013EAY\u0007w4\u0001ba@@!\u0006EF\u0011\u0001\u0005\u000b\u0007+\u0014%Q3A\u0005\u0002\r\r\u0001B\u0003C\u0004\u0005\nE\t\u0015!\u0003\u0004\u0006!QA\u0011\u0002\"\u0003\u0016\u0004%\t\u0001b\u0003\t\u0015\u0011=!I!E!\u0002\u0013!i\u0001\u0003\u0006\u0005\u0012\t\u0013)\u001a!C\u0001\t'A!\u0002b\u0006C\u0005#\u0005\u000b\u0011\u0002C\u000b\u0011\u001d\u0011\tC\u0011C\u0001\t3Aqa!\u000fC\t\u0003\"\u0019\u0003C\u0004\u0005&\t#I\u0001b\n\t\u0013\t%')!A\u0005\u0002\u00115\u0002\"\u0003Bj\u0005F\u0005I\u0011\u0001C\u001b\u0011%\u0011YOQI\u0001\n\u0003!I\u0004C\u0005\u0003n\n\u000b\n\u0011\"\u0001\u0005>!I!q\u001e\"\u0002\u0002\u0013\u0005#\u0011\u001f\u0005\n\u0007\u0003\u0011\u0015\u0011!C\u0001\u0007\u0007A\u0011ba\u0003C\u0003\u0003%\t\u0001\"\u0011\t\u0013\re!)!A\u0005B\rm\u0001\"CB\u0015\u0005\u0006\u0005I\u0011\u0001C#\u0011%\u0019yCQA\u0001\n\u0003\"I\u0005C\u0005\u00046\t\u000b\t\u0011\"\u0011\u00048!I1Q\b\"\u0002\u0002\u0013\u0005CQJ\u0004\f\toz\u0014\u0011!E\u0001\u0003c#IHB\u0006\u0004\u0000~\n\t\u0011#\u0001\u00022\u0012m\u0004b\u0002B\u00113\u0012\u0005Aq\u0010\u0005\n\u0007sI\u0016\u0011!C#\u0007wA\u0011ba\u0019Z\u0003\u0003%\t\t\"!\t\u0013\rM\u0014,!A\u0005\u0002\u0012%e\u0001\u0003C)\u007fA\u000b\t\fb\u0015\t\u0015\u0011UcL!f\u0001\n\u0003\u0019\u0019\u000e\u0003\u0006\u0005Xy\u0013\t\u0012)A\u0005\u0005/BqA!\t_\t\u0003!I\u0006C\u0004\u0004:y#\t\u0005b\t\t\u0013\t%g,!A\u0005\u0002\u0011}\u0003\"\u0003Bj=F\u0005I\u0011\u0001C2\u0011%\u0011yOXA\u0001\n\u0003\u0012\t\u0010C\u0005\u0004\u0002y\u000b\t\u0011\"\u0001\u0004\u0004!I11\u00020\u0002\u0002\u0013\u0005Aq\r\u0005\n\u00073q\u0016\u0011!C!\u00077A\u0011b!\u000b_\u0003\u0003%\t\u0001b\u001b\t\u0013\r=b,!A\u0005B\u0011=\u0004\"CB\u001b=\u0006\u0005I\u0011IB\u001c\u0011%\u0019iDXA\u0001\n\u0003\"\u0019hB\u0006\u0005\u0012~\n\t\u0011#\u0001\u00022\u0012Mea\u0003C)\u007f\u0005\u0005\t\u0012AAY\t+CqA!\to\t\u0003!i\nC\u0005\u0004:9\f\t\u0011\"\u0012\u0004<!I11\r8\u0002\u0002\u0013\u0005Eq\u0014\u0005\n\u0007gr\u0017\u0011!CA\tG3\u0001\u0002b*@\u0001\u0006EF\u0011\u0016\u0005\u000b\tW\u001b(Q3A\u0005\u0002\u00115\u0006B\u0003C[g\nE\t\u0015!\u0003\u00050\"9!\u0011E:\u0005\u0002\u0011]\u0006bBB\u001dg\u0012\u0005C1\u0005\u0005\b\t{\u001bH\u0011\u0001C`\u0011\u001d!\u0019m\u001dC\u0001\t\u000bDq\u0001\"4t\t\u0003!y\rC\u0004\u0005VN$\t\u0001b6\t\u000f\u0011e7\u000f\"\u0001\u0005X\"I!\u0011Z:\u0002\u0002\u0013\u0005A1\u001c\u0005\n\u0005'\u001c\u0018\u0013!C\u0001\t?D\u0011Ba<t\u0003\u0003%\tE!=\t\u0013\r\u00051/!A\u0005\u0002\r\r\u0001\"CB\u0006g\u0006\u0005I\u0011\u0001Cr\u0011%\u0019Ib]A\u0001\n\u0003\u001aY\u0002C\u0005\u0004*M\f\t\u0011\"\u0001\u0005h\"I1qF:\u0002\u0002\u0013\u0005C1\u001e\u0005\n\u0007k\u0019\u0018\u0011!C!\u0007oA\u0011b!\u0010t\u0003\u0003%\t\u0005b<\b\u0013\u0011Mx\b#\u0001\u00022\u0012Uh!\u0003CT\u007f!\u0005\u0011\u0011\u0017C|\u0011!\u0011\t#!\u0005\u0005\u0002\u0011e\b\u0002\u0003C~\u0003#!\t\u0001b6\t\u0015\r\r\u0014\u0011CA\u0001\n\u0003#i\u0010\u0003\u0006\u0004t\u0005E\u0011\u0011!CA\u000b\u0003Aq!b\u0002@\t\u0003)I\u0001C\u0004\u0006\u000e}\"\t!b\u0004\u0007\r\u0015\u0005\u0002\u0001AC\u0012\u0011-\u00199.a\b\u0003\u0006\u0004%\ta!7\t\u0017\u0015\u0015\u0012q\u0004B\u0001B\u0003%11\u001c\u0005\t\u0005C\ty\u0002\"\u0001\u0006(!Q1\u0011[A\u0010\u0005\u0004%\taa5\t\u0013\u00155\u0012q\u0004Q\u0001\n\t]\u0003BCBk\u0003?\u0011\r\u0011\"\u0001\u0004\u0004!IAqAA\u0010A\u0003%1Q\u0001\u0005\u000b\u0007O\fy\u0002\"\u0001\u00022\u0016=\u0002\u0002CBq\u0003?!\taa5\t\u0011\r\r\u0018q\u0004C\u0001\u0007'<q!\"\u000f\u0001\u0011\u0003)YDB\u0004\u0006\"\u0001A\t!\"\u0010\t\u0011\t\u0005\u0012q\u0007C\u0001\u000b\u007fA\u0001ba\u0019\u00028\u0011\u0005Q\u0011\t\u0005\t\u0007G\n9\u0004\"\u0001\u0006D!A11OA\u001c\t\u0003)9E\u0002\u0004\u0006T\u0001\u0001QQ\u000b\u0005\f\u0007/\f\tE!b\u0001\n\u0003\u0019I\u000eC\u0006\u0006&\u0005\u0005#\u0011!Q\u0001\n\rm\u0007bCC,\u0003\u0003\u0012)\u0019!C\u0001\u000b3B1\"\"7\u0002B\t\u0005\t\u0015!\u0003\u0006\\!A!\u0011EA!\t\u0003)Y\u000e\u0003\u0006\u0004V\u0006\u0005#\u0019!C\u0001\u0007\u0007A\u0011\u0002b\u0002\u0002B\u0001\u0006Ia!\u0002\t\u0011\rE\u0017\u0011\tC\u0001\u0007'D\u0001b!:\u0002B\u0011\u0005#q\u0006\u0005\t\u0007C\f\t\u0005\"\u0001\u0004T\"Q1q]A!\t\u0003\t\t,\"9\t\u0011\r\r\u0018\u0011\tC\u0001\u0007'<q!\"\u0019\u0001\u0011\u0003)\u0019GB\u0004\u0006T\u0001A\t!\"\u001a\t\u0011\t\u0005\u0012Q\fC\u0001\u000bOB\u0001ba\u0019\u0002^\u0011\u0005Q\u0011\u000e\u0005\t\u0007G\ni\u0006\"\u0001\u0006p!A11OA/\t\u0003))HB\u0004\u0006\u0000\u0005u\u0003)\"!\t\u0017\te\u0011q\rBK\u0002\u0013\u0005Q1\u0011\u0005\f\u00057\t9G!E!\u0002\u0013)I\u0002C\u0006\u0006\u0006\u0006\u001d$Q3A\u0005\u0002\u0015\u001d\u0005bCCE\u0003O\u0012\t\u0012)A\u0005\u0007\u001fD1\u0002b3\u0002h\t\u0015\r\u0011\"\u0001\u0006\f\"YQQRA4\u0005\u0003\u0005\u000b\u0011BAn\u0011!\u0011\t#a\u001a\u0005\u0002\u0015=\u0005B\u0003Be\u0003O\n\t\u0011\"\u0001\u0006\u001e\"Q!1[A4#\u0003%\t!b*\t\u0015\t-\u0018qMI\u0001\n\u0003)Y\u000b\u0003\u0006\u0003p\u0006\u001d\u0014\u0011!C!\u0005cD!b!\u0001\u0002h\u0005\u0005I\u0011AB\u0002\u0011)\u0019Y!a\u001a\u0002\u0002\u0013\u0005Qq\u0016\u0005\u000b\u00073\t9'!A\u0005B\rm\u0001BCB\u0015\u0003O\n\t\u0011\"\u0001\u00064\"Q1qFA4\u0003\u0003%\t%b.\t\u0015\rU\u0012qMA\u0001\n\u0003\u001a9\u0004\u0003\u0006\u0004:\u0005\u001d\u0014\u0011!C!\u0007wA!b!\u0010\u0002h\u0005\u0005I\u0011IC^\u000f))y,!\u0018\u0002\u0002#\u0005Q\u0011\u0019\u0004\u000b\u000b\u007f\ni&!A\t\u0002\u0015\r\u0007\u0002\u0003B\u0011\u0003##\t!\"2\t\u0015\re\u0012\u0011SA\u0001\n\u000b\u001aY\u0004\u0003\u0006\u0004d\u0005E\u0015\u0011!CA\u000b\u000fD!ba\u001d\u0002\u0012\u0006\u0005I\u0011QCi\u000f\u001d)i\u000f\u0001E\u0001\u000b_4q!\"=\u0001\u0011\u0003)\u0019\u0010\u0003\u0005\u0003\"\u0005uE\u0011AC{\r!)90!(\u0002\u0002\u0015e\b\u0002\u0003B\u0011\u0003C#\t!b?\t\u0011\u0019\u0005\u0011\u0011\u0015D\t\r\u0007A\u0001B\"\u0001\u0002\"\u0012Eaq\u0002\u0005\t\u0007G\n\t\u000b\"\u0001\u0007\u0016!A11MAQ\t\u00031I\u0002\u0003\u0005\u0004d\u0005uE\u0011\u0001D\u0010\u0005\u0015Y\u0015N\u001c3t\u0015\u0011\t\u0019,!.\u0002\u0011%tG/\u001a:oC2TA!a.\u0002:\u00069!/\u001a4mK\u000e$(BAA^\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001AAa!\u0011\t\u0019-!2\u000e\u0005\u0005e\u0016\u0002BAd\u0003s\u0013a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002NB!\u00111YAh\u0013\u0011\t\t.!/\u0003\tUs\u0017\u000e\u001e\u0002\b'fl\u0007+Y5s!!\t\u0019-a6\u0002\\\u0006m\u0017\u0002BAm\u0003s\u0013a\u0001V;qY\u0016\u0014\u0004\u0003BAo\u0003?l\u0011\u0001A\u0005\u0005\u0003C\f\u0019O\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0005\u0003K\f\tLA\u0004Ts6\u0014w\u000e\\:\u0003\u0015-Kg\u000eZ#se>\u00148oE\u0004\u0004\u0003\u0003\fY/!=\u0011\t\u0005\r\u0017Q^\u0005\u0005\u0003_\fILA\u0004Qe>$Wo\u0019;\u0011\t\u0005M(1\u0001\b\u0005\u0003k\fyP\u0004\u0003\u0002x\u0006uXBAA}\u0015\u0011\tY0!0\u0002\rq\u0012xn\u001c;?\u0013\t\tY,\u0003\u0003\u0003\u0002\u0005e\u0016a\u00029bG.\fw-Z\u0005\u0005\u0005\u000b\u00119A\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0003\u0002\u0005e\u0016!B1sSRLXC\u0001B\u0007!\u0019\u0011yA!\u0005\u0003\u00169!\u00111YA\u0000\u0013\u0011\u0011\u0019Ba\u0002\u0003\t1K7\u000f\u001e\t\u0004\u0003;\u0014\u0011AB1sSRL\b%\u0001\u0005wCJL\u0017M\\2f\u0003%1\u0018M]5b]\u000e,\u0007%\u0001\u0006tiJL7\r\u001e8fgN\f1b\u001d;sS\u000e$h.Z:tA\u00051A(\u001b8jiz\"\u0002B!\n\u0003(\t%\"1\u0006\t\u0004\u0003;\u001c\u0001\"\u0003B\u0005\u0015A\u0005\t\u0019\u0001B\u0007\u0011%\u0011IB\u0003I\u0001\u0002\u0004\u0011i\u0001C\u0005\u0003\u001e)\u0001\n\u00111\u0001\u0003\u000e\u00059\u0011n]#naRLXC\u0001B\u0019!\u0011\t\u0019Ma\r\n\t\tU\u0012\u0011\u0018\u0002\b\u0005>|G.Z1o\u0003)\t'/\u001b;z\u000bJ\u0014xN\u001d\u000b\u0005\u0005K\u0011Y\u0004C\u0004\u0003>1\u0001\rA!\u0006\u0002\tMLXn]\u0001\u000em\u0006\u0014\u0018.\u00198dK\u0016\u0013(o\u001c:\u0015\t\t\u0015\"1\t\u0005\b\u0005{i\u0001\u0019\u0001B\u000b\u0003=\u0019HO]5di:,7o]#se>\u0014H\u0003\u0002B\u0013\u0005\u0013BqA!\u0010\u000f\u0001\u0004\u0011)\"\u0001\u0006%a2,8\u000f\n9mkN$BA!\n\u0003P!9!\u0011K\bA\u0002\t\u0015\u0012\u0001B3seN\faA^1s'R\u0014H\u0003\u0002B,\u0005O\u0002BA!\u0017\u0003b9!!1\fB/!\u0011\t90!/\n\t\t}\u0013\u0011X\u0001\u0007!J,G-\u001a4\n\t\t\r$Q\r\u0002\u0007'R\u0014\u0018N\\4\u000b\t\t}\u0013\u0011\u0018\u0005\b\u0005S\u0002\u0002\u0019AAn\u0003\u0005\u0019\u0018aB9vC2Lg-\u001f\u000b\u0007\u0005/\u0012yGa\u001d\t\u000f\tE\u0014\u00031\u0001\u0002\\\u0006\u0011\u0011\r\r\u0005\b\u0005k\n\u0002\u0019AAn\u0003\t\u0011\u0007'A\u0006lS:$W*Z:tC\u001e,GC\u0002B>\u0005\u000f\u0013Y\t\u0006\u0003\u0003X\tu\u0004b\u0002B@%\u0001\u0007!\u0011Q\u0001\u0002MBQ\u00111\u0019BB\u0005/\u00129Fa\u0016\n\t\t\u0015\u0015\u0011\u0018\u0002\n\rVt7\r^5p]JBqA!#\u0013\u0001\u0004\tY.A\u0001b\u0011\u001d\u0011iI\u0005a\u0001\u00037\f\u0011\u0001]\u0001\u0012gR\u0014\u0018n\u0019;oKN\u001cX*Z:tC\u001e,GC\u0002B,\u0005'\u0013)\nC\u0004\u0003\nN\u0001\r!a7\t\u000f\t55\u00031\u0001\u0002\\\u0006ya/\u0019:jC:\u001cW-T3tg\u0006<W\r\u0006\u0004\u0003X\tm%Q\u0014\u0005\b\u0005\u0013#\u0002\u0019AAn\u0011\u001d\u0011i\t\u0006a\u0001\u00037\fA\"\u0019:jiflUm]:bO\u0016$bAa\u0016\u0003$\n\u0015\u0006b\u0002BE+\u0001\u0007\u00111\u001c\u0005\b\u0005\u001b+\u0002\u0019AAn\u00031\u0011W/\u001b7e\u001b\u0016\u001c8/Y4f)\u0019\u00119Fa+\u00030\"9!Q\u0016\fA\u0002\t5\u0011A\u0001=t\u0011\u001d\u0011yH\u0006a\u0001\u0005c\u0003\"\"a1\u0003\u0004\u0006m\u00171\u001cB,\u00031)'O]8s\u001b\u0016\u001c8/Y4f)\u0019\u00119Fa.\u0003F\"9!\u0011X\fA\u0002\tm\u0016\u0001\u0002;be\u001e\u0004B!!8\u0003>&!!q\u0018Ba\u0005\u0011!\u0016\u0010]3\n\t\t\r\u0017\u0011\u0017\u0002\u0006)f\u0004Xm\u001d\u0005\b\u0005\u000f<\u0002\u0019AAn\u0003\u0019!\b/\u0019:b[\u0006!1m\u001c9z)!\u0011)C!4\u0003P\nE\u0007\"\u0003B\u00051A\u0005\t\u0019\u0001B\u0007\u0011%\u0011I\u0002\u0007I\u0001\u0002\u0004\u0011i\u0001C\u0005\u0003\u001ea\u0001\n\u00111\u0001\u0003\u000e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BlU\u0011\u0011iA!7,\u0005\tm\u0007\u0003\u0002Bo\u0005Ol!Aa8\u000b\t\t\u0005(1]\u0001\nk:\u001c\u0007.Z2lK\u0012TAA!:\u0002:\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t%(q\u001c\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!1\u001f\t\u0005\u0005k\u0014y0\u0004\u0002\u0003x*!!\u0011 B~\u0003\u0011a\u0017M\\4\u000b\u0005\tu\u0018\u0001\u00026bm\u0006LAAa\u0019\u0003x\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u00111Q\u0001\t\u0005\u0003\u0007\u001c9!\u0003\u0003\u0004\n\u0005e&aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BB\b\u0007+\u0001B!a1\u0004\u0012%!11CA]\u0005\r\te.\u001f\u0005\n\u0007/q\u0012\u0011!a\u0001\u0007\u000b\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAB\u000f!\u0019\u0019yb!\n\u0004\u00105\u00111\u0011\u0005\u0006\u0005\u0007G\tI,\u0001\u0006d_2dWm\u0019;j_:LAaa\n\u0004\"\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\u0011\td!\f\t\u0013\r]\u0001%!AA\u0002\r=\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BAa=\u00044!I1qC\u0011\u0002\u0002\u0003\u00071QA\u0001\tQ\u0006\u001c\bnQ8eKR\u00111QA\u0001\ti>\u001cFO]5oOR\u0011!1_\u0001\u0007KF,\u0018\r\\:\u0015\t\tE2\u0011\t\u0005\n\u0007/!\u0013\u0011!a\u0001\u0007\u001f\t!bS5oI\u0016\u0013(o\u001c:t!\r\tiNJ\n\u0006M\r%3q\u000b\t\r\u0007\u0017\u001a\tf!\u0016\u0004V\rU#QE\u0007\u0003\u0007\u001bRAaa\u0014\u0002:\u00069!/\u001e8uS6,\u0017\u0002BB*\u0007\u001b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0019\u0011yA!\u0005\u0002VB!1\u0011LB0\u001b\t\u0019YF\u0003\u0003\u0004^\tm\u0018AA5p\u0013\u0011\u0011)aa\u0017\u0015\u0005\r\u0015\u0013!B1qa2LH\u0003\u0003B\u0013\u0007O\u001aIga\u001b\t\u0013\t%\u0011\u0006%AA\u0002\t5\u0001\"\u0003B\rSA\u0005\t\u0019\u0001B\u0007\u0011%\u0011i\"\u000bI\u0001\u0002\u0004\u0011i!A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00132\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0002\u000fUt\u0017\r\u001d9msR!1qOBB!\u0019\t\u0019m!\u001f\u0004~%!11PA]\u0005\u0019y\u0005\u000f^5p]BQ\u00111YB@\u0005\u001b\u0011iA!\u0004\n\t\r\u0005\u0015\u0011\u0018\u0002\u0007)V\u0004H.Z\u001a\t\u0013\r\u0015U&!AA\u0002\t\u0015\u0012a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u0012\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0001\u0007O_.Kg\u000eZ#se>\u00148/\u0006\u0002\u0003&\u0005a1.\u001b8eg\u000e{gNZ8s[RQ!\u0011GBK\u00077\u001b\tk!*\t\u000f\r]%\u00071\u0001\u0004\u001a\u00069A\u000f]1sC6\u001c\bC\u0002B\b\u0005#\tY\u000eC\u0004\u0004\u001eJ\u0002\raa(\u0002\u000bQ\f'oZ:\u0011\r\t=!\u0011\u0003B^\u0011\u001d\u0019\u0019K\ra\u0001\u0005w\u000b1\u0001\u001d:f\u0011\u001d\u00199K\ra\u0001\u00037\fQa\\<oKJ\faB^1sS\u0006t7-Z:NCR\u001c\u0007\u000e\u0006\u0004\u00032\r56\u0011\u0017\u0005\b\u0007_\u001b\u0004\u0019AAn\u0003\u0011\u0019\u00180\\\u0019\t\u000f\rM6\u00071\u0001\u0002\\\u0006!1/_73\u0003A\u0019\u0007.Z2l\u0017&tGMQ8v]\u0012\u001c\b\u0007\u0006\u0007\u0004:\u000eu6qXBa\u0007\u0007\u001c)\r\u0005\u0004\u0003\u0010\tE11\u0018\t\u000b\u0003\u0007\u001cyHa/\u0002\\\n\u0015\u0002bBBLi\u0001\u00071\u0011\u0014\u0005\b\u0007;#\u0004\u0019ABP\u0011\u001d\u0019\u0019\u000b\u000ea\u0001\u0005wCqaa*5\u0001\u0004\tY\u000eC\u0004\u0004HR\u0002\rA!\r\u0002\u001b\u0015D\b\u000f\\1j]\u0016\u0013(o\u001c:t\u0005\u0011Y\u0015N\u001c3\u0014\u0007U\n\t\r\u0006\u0002\u0004PB\u0019\u0011Q\\\u001b\u0002\u0017\u0011,7o\u0019:jaRLwN\\\u000b\u0003\u0005/\nQa\u001c:eKJ\faAY8v]\u0012\u001cXCABn!\u0011\tin!8\n\t\r}'\u0011\u0019\u0002\u000b)f\u0004XMQ8v]\u0012\u001c\u0018!D:dC2\fgj\u001c;bi&|g.\u0001\u0007ti\u0006\u0014hj\u001c;bi&|g.A\u0005iCN\u0014u.\u001e8eg\u0006Q!-^5mIN#\u0018\r^3\u0015\r\r-X1CC\u000b)\u0011\u0019i/\"\u0005\u0011\u0007\r=8OD\u0002\u0002^z\nAaS5oIB\u0019\u0011Q\\ \u0014\u0007}\n\t\r\u0006\u0002\u0004t\ni1kY1mC:{G/\u0019;j_:\u001c2!QAaS\r\t%I\u0018\u0002\u0005\u0011\u0016\fGmE\u0005C\u0003\u0003$\u0019!a;\u0002rB\u0019AQA!\u000e\u0003}\naa\u001c:eKJ\u0004\u0013!\u00018\u0016\u0005\u00115\u0001CBAb\u0007s\u001a)!\u0001\u0002oA\u0005)\u0011\r\\5bgV\u0011AQ\u0003\t\u0007\u0003\u0007\u001cIHa\u0016\u0002\r\u0005d\u0017.Y:!)!!Y\u0002\"\b\u0005 \u0011\u0005\u0002c\u0001C\u0003\u0005\"91Q[%A\u0002\r\u0015\u0001b\u0002C\u0005\u0013\u0002\u0007AQ\u0002\u0005\b\t#I\u0005\u0019\u0001C\u000b)\t\u00119&A\u0005usB,\u0017\t\\5bgR!!q\u000bC\u0015\u0011\u001d!Yc\u0013a\u0001\u0007\u000b\t\u0011\u0001\u001f\u000b\t\t7!y\u0003\"\r\u00054!I1Q\u001b'\u0011\u0002\u0003\u00071Q\u0001\u0005\n\t\u0013a\u0005\u0013!a\u0001\t\u001bA\u0011\u0002\"\u0005M!\u0003\u0005\r\u0001\"\u0006\u0016\u0005\u0011]\"\u0006BB\u0003\u00053,\"\u0001b\u000f+\t\u00115!\u0011\\\u000b\u0003\t\u007fQC\u0001\"\u0006\u0003ZR!1q\u0002C\"\u0011%\u00199BUA\u0001\u0002\u0004\u0019)\u0001\u0006\u0003\u00032\u0011\u001d\u0003\"CB\f)\u0006\u0005\t\u0019AB\b)\u0011\u0011\u0019\u0010b\u0013\t\u0013\r]Q+!AA\u0002\r\u0015A\u0003\u0002B\u0019\t\u001fB\u0011ba\u0006X\u0003\u0003\u0005\raa\u0004\u0003\tQ+\u0007\u0010^\n\n=\u0006\u0005G1AAv\u0003c\fQA^1mk\u0016\faA^1mk\u0016\u0004C\u0003\u0002C.\t;\u00022\u0001\"\u0002_\u0011\u001d!)&\u0019a\u0001\u0005/\"B\u0001b\u0017\u0005b!IAQK2\u0011\u0002\u0003\u0007!qK\u000b\u0003\tKRCAa\u0016\u0003ZR!1q\u0002C5\u0011%\u00199bZA\u0001\u0002\u0004\u0019)\u0001\u0006\u0003\u00032\u00115\u0004\"CB\fS\u0006\u0005\t\u0019AB\b)\u0011\u0011\u0019\u0010\"\u001d\t\u0013\r]!.!AA\u0002\r\u0015A\u0003\u0002B\u0019\tkB\u0011ba\u0006m\u0003\u0003\u0005\raa\u0004\u0002\t!+\u0017\r\u001a\t\u0004\t\u000bI6#B-\u0005~\r]\u0003\u0003DB&\u0007#\u001a)\u0001\"\u0004\u0005\u0016\u0011mAC\u0001C=)!!Y\u0002b!\u0005\u0006\u0012\u001d\u0005bBBk9\u0002\u00071Q\u0001\u0005\b\t\u0013a\u0006\u0019\u0001C\u0007\u0011\u001d!\t\u0002\u0018a\u0001\t+!B\u0001b#\u0005\u0010B1\u00111YB=\t\u001b\u0003\"\"a1\u0004\u0000\r\u0015AQ\u0002C\u000b\u0011%\u0019))XA\u0001\u0002\u0004!Y\"\u0001\u0003UKb$\bc\u0001C\u0003]N)a\u000eb&\u0004XAA11\nCM\u0005/\"Y&\u0003\u0003\u0005\u001c\u000e5#!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocQ\u0011A1\u0013\u000b\u0005\t7\"\t\u000bC\u0004\u0005VE\u0004\rAa\u0016\u0015\t\u0011UAQ\u0015\u0005\n\u0007\u000b\u0013\u0018\u0011!a\u0001\t7\u00121b\u0015;sS:<7\u000b^1uKN91/!1\u0002l\u0006E\u0018A\u0002;pW\u0016t7/\u0006\u0002\u00050B1!q\u0002CY\t\u0007IA\u0001b-\u0003\b\t\u00191+Z9\u0002\u000fQ|7.\u001a8tAQ!A\u0011\u0018C^!\r!)a\u001d\u0005\b\tW3\b\u0019\u0001CX\u0003\u0019\t\u0007\u000f]3oIR!A\u0011\u0018Ca\u0011\u001d!)\u0006\u001fa\u0001\u0005/\n!\"\u00199qK:$\u0007*Z1e)\u0019!I\fb2\u0005J\"91Q[=A\u0002\r\u0015\u0001b\u0002Cfs\u0002\u0007\u00111\\\u0001\u0004gfl\u0017\u0001D2pk:$()_(sI\u0016\u0014H\u0003BB\u0003\t#Dq\u0001b5{\u0001\u0004\u0019)!A\u0001p\u0003)\u0011X-\\8wK>sWm]\u000b\u0003\ts\u000b1B]3n_Z,\u0017\t\\5bgR!A\u0011\u0018Co\u0011%!Y+ I\u0001\u0002\u0004!y+\u0006\u0002\u0005b*\"Aq\u0016Bm)\u0011\u0019y\u0001\":\t\u0015\r]\u00111AA\u0001\u0002\u0004\u0019)\u0001\u0006\u0003\u00032\u0011%\bBCB\f\u0003\u000f\t\t\u00111\u0001\u0004\u0010Q!!1\u001fCw\u0011)\u00199\"!\u0003\u0002\u0002\u0003\u00071Q\u0001\u000b\u0005\u0005c!\t\u0010\u0003\u0006\u0004\u0018\u00055\u0011\u0011!a\u0001\u0007\u001f\t1b\u0015;sS:<7\u000b^1uKB!AQAA\t'\u0019\t\t\"!1\u0004XQ\u0011AQ_\u0001\u0006K6\u0004H/\u001f\u000b\u0005\ts#y\u0010\u0003\u0005\u0005,\u0006]\u0001\u0019\u0001CX)\u0011)\u0019!\"\u0002\u0011\r\u0005\r7\u0011\u0010CX\u0011)\u0019))!\u0007\u0002\u0002\u0003\u0007A\u0011X\u0001\u000b\rJ|W\u000eU1sC6\u001cH\u0003\u0002B^\u000b\u0017A\u0001ba&\u0002\u001c\u0001\u00071\u0011T\u0001\t/&dGmY1sIV\u0011!1\u0018\u0005\b\u0005Sj\u0004\u0019ABw\u0011\u001d!Y-\u0010a\u0001\u00037Dq!b\u0006>\u0001\u0004)I\"A\u0001w!\u0011)Y\"\"\b\u000e\u0005\u0005E\u0016\u0002BC\u0010\u0003c\u0013\u0001BV1sS\u0006t7-\u001a\u0002\u000f!J|\u0007/\u001a:UsB,7*\u001b8e'\u0011\tyba4\u0002\u000f\t|WO\u001c3tAQ!Q\u0011FC\u0016!\u0011\ti.a\b\t\u0011\r]\u0017Q\u0005a\u0001\u00077\fA\u0002Z3tGJL\u0007\u000f^5p]\u0002\"b!\"\r\u00066\u0015]B\u0003BBw\u000bgA\u0001B!\u001b\u00020\u0001\u00071Q\u001e\u0005\t\t\u0017\fy\u00031\u0001\u0002\\\"AQqCA\u0018\u0001\u0004)I\"\u0001\bQe>\u0004XM\u001d+za\u0016\\\u0015N\u001c3\u0011\t\u0005u\u0017qG\n\u0005\u0003o\t\t\r\u0006\u0002\u0006<U\u0011Q\u0011\u0006\u000b\u0005\u000bS))\u0005\u0003\u0005\u0004X\u0006u\u0002\u0019ABn)\u0011)I%b\u0014\u0011\r\u0005\rW1JBn\u0013\u0011)i%!/\u0003\tM{W.\u001a\u0005\t\u000b#\ny\u00041\u0001\u0006*\u0005\u0019\u0001\u000f^6\u0003\u0017QK\b/Z\"p].Kg\u000eZ\n\u0005\u0003\u0003\u001ay-\u0001\u0003be\u001e\u001cXCAC.!\u0019\u0011y\u0001\"-\u0006^A!QqLA4\u001d\u0011\ti.a\u0017\u0002\u0017QK\b/Z\"p].Kg\u000e\u001a\t\u0005\u0003;\fif\u0005\u0003\u0002^\u0005\u0005GCAC2)\u0011)Y'\"\u001c\u0011\t\u0005u\u0017\u0011\t\u0005\t\u000b/\n\t\u00071\u0001\u0006\\Q1Q1NC9\u000bgB\u0001ba6\u0002d\u0001\u000711\u001c\u0005\t\u000b/\n\u0019\u00071\u0001\u0006\\Q!QqOC>!\u0019\t\u0019-b\u0013\u0006zAA\u00111YAl\u00077,Y\u0006\u0003\u0005\u0006~\u0005\u0015\u0004\u0019AC6\u0003\r!8m\u001b\u0002\t\u0003J<W/\\3oiNA\u0011qMAa\u0003W\f\t0\u0006\u0002\u0006\u001a\u0005!1.\u001b8e+\t\u0019y-A\u0003lS:$\u0007%\u0006\u0002\u0002\\\u0006!1/_7!)\u0019)\t*\"'\u0006\u001cR!Q1SCL!\u0011))*a\u001a\u000e\u0005\u0005u\u0003\u0002\u0003Cf\u0003k\u0002\r!a7\t\u0011\te\u0011Q\u000fa\u0001\u000b3A\u0001\"\"\"\u0002v\u0001\u00071q\u001a\u000b\u0007\u000b?+\u0019+\"*\u0015\t\u0015MU\u0011\u0015\u0005\t\t\u0017\f9\b1\u0001\u0002\\\"Q!\u0011DA<!\u0003\u0005\r!\"\u0007\t\u0015\u0015\u0015\u0015q\u000fI\u0001\u0002\u0004\u0019y-\u0006\u0002\u0006**\"Q\u0011\u0004Bm+\t)iK\u000b\u0003\u0004P\neG\u0003BB\b\u000bcC!ba\u0006\u0002\u0002\u0006\u0005\t\u0019AB\u0003)\u0011\u0011\t$\".\t\u0015\r]\u0011QQA\u0001\u0002\u0004\u0019y\u0001\u0006\u0003\u0003t\u0016e\u0006BCB\f\u0003\u000f\u000b\t\u00111\u0001\u0004\u0006Q!!\u0011GC_\u0011)\u00199\"!$\u0002\u0002\u0003\u00071qB\u0001\t\u0003J<W/\\3oiB!QQSAI'\u0019\t\t*!1\u0004XQ\u0011Q\u0011\u0019\u000b\u0007\u000b\u0013,i-b4\u0015\t\u0015MU1\u001a\u0005\t\t\u0017\f9\n1\u0001\u0002\\\"A!\u0011DAL\u0001\u0004)I\u0002\u0003\u0005\u0006\u0006\u0006]\u0005\u0019ABh)\u0011)\u0019.b6\u0011\r\u0005\r7\u0011PCk!!\t\u0019-a6\u0006\u001a\r=\u0007BCBC\u00033\u000b\t\u00111\u0001\u0006\u0014\u0006)\u0011M]4tAQ1Q1NCo\u000b?D\u0001ba6\u0002L\u0001\u000711\u001c\u0005\t\u000b/\nY\u00051\u0001\u0006\\Q1Q1]Cu\u000bW$Ba!<\u0006f\"AQq]A,\u0001\u0004\u0019i/\u0001\u0002ta!AA1ZA,\u0001\u0004\tY\u000e\u0003\u0005\u0006\u0018\u0005]\u0003\u0019AC\r\u0003%IgNZ3s\u0017&tG\r\u0005\u0003\u0002^\u0006u%!C5oM\u0016\u00148*\u001b8e'\u0011\ti*!1\u0015\u0005\u0015=(!C%oM\u0016\u00148*\u001b8e'\u0011\t\t+!1\u0015\u0005\u0015u\b\u0003BC\u0000\u0003Ck!!!(\u0002\u000b%tg-\u001a:\u0015\u0011\r=gQ\u0001D\u0005\r\u0017A\u0001Bb\u0002\u0002&\u0002\u0007!1X\u0001\u0004iB,\u0007\u0002CBT\u0003K\u0003\r!a7\t\u0011\u00195\u0011Q\u0015a\u0001\u0005c\t\u0001\u0002^8q\u0019\u00164X\r\u001c\u000b\u0007\u0007\u001f4\tBb\u0005\t\u0011\u0011-\u0017q\u0015a\u0001\u00037D\u0001B\"\u0004\u0002(\u0002\u0007!\u0011\u0007\u000b\u0005\u0007\u001f49\u0002\u0003\u0005\u0005L\u0006%\u0006\u0019AAn)\u0019\u0019yMb\u0007\u0007\u001e!AaqAAV\u0001\u0004\u0011Y\f\u0003\u0005\u0004(\u0006-\u0006\u0019AAn)\u0011)iP\"\t\t\u0011\r\r\u0016Q\u0016a\u0001\u0005w\u0003B!b\u0007\u0007&%!aqEAY\u0005-\u0019\u00160\u001c2pYR\u000b'\r\\3"
)
public interface Kinds {
   KindErrors$ KindErrors();

   Kind$ Kind();

   ProperTypeKind$ ProperTypeKind();

   TypeConKind$ TypeConKind();

   inferKind$ inferKind();

   void scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq(final KindErrors x$1);

   KindErrors NoKindErrors();

   // $FF: synthetic method
   static boolean kindsConform$(final Kinds $this, final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner) {
      return $this.kindsConform(tparams, targs, pre, owner);
   }

   default boolean kindsConform(final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner) {
      return this.checkKindBounds0(tparams, targs, pre, owner, false).isEmpty();
   }

   private boolean variancesMatch(final Symbols.Symbol sym1, final Symbols.Symbol sym2) {
      return Variance$.MODULE$.isInvariant$extension(sym2.variance()) || sym1.variance() == sym2.variance();
   }

   // $FF: synthetic method
   static List checkKindBounds0$(final Kinds $this, final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner, final boolean explainErrors) {
      return $this.checkKindBounds0(tparams, targs, pre, owner, explainErrors);
   }

   default List checkKindBounds0(final List tparams, final List targs, final Types.Type pre, final Symbols.Symbol owner, final boolean explainErrors) {
      Object var6 = new Object();

      try {
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var19 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var20 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var20;
         boolean var21 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var21 && (tparams.nonEmpty() || targs.nonEmpty())) {
            ((SymbolTable)this).log(() -> (new StringBuilder(26)).append("checkKindBounds0(").append(tparams).append(", ").append(targs).append(", ").append(pre).append(", ").append(owner).append(", ").append(explainErrors).append(")").toString());
         }

         Collections var22 = (Collections)this;
         ListBuffer flatMap2_lb = null;
         List flatMap2_ys1 = tparams;

         for(List flatMap2_ys2 = targs; !flatMap2_ys1.isEmpty() && !flatMap2_ys2.isEmpty(); flatMap2_ys2 = (List)flatMap2_ys2.tail()) {
            var22 = (Collections)flatMap2_ys1.head();
            Types.Type var15 = (Types.Type)flatMap2_ys2.head();
            Symbols.Symbol var14 = (Symbols.Symbol)var22;
            List flatMap2_cs = $anonfun$checkKindBounds0$12(this, explainErrors, var6, tparams, targs, pre, owner, var14, var15);
            if (flatMap2_cs != .MODULE$) {
               if (flatMap2_lb == null) {
                  flatMap2_lb = new ListBuffer();
               }

               flatMap2_lb.$plus$plus$eq(flatMap2_cs);
            }

            flatMap2_ys1 = (List)flatMap2_ys1.tail();
         }

         return (List)(flatMap2_lb == null ? .MODULE$ : flatMap2_lb.result());
      } catch (NonLocalReturnControl var16) {
         if (var16.key() == var6) {
            return (List)var16.value();
         } else {
            throw var16;
         }
      }
   }

   private static void kindCheck$1(final boolean cond, final Function1 f, final ObjectRef kindErrors$1) {
      if (!cond) {
         kindErrors$1.elem = (KindErrors)f.apply((KindErrors)kindErrors$1.elem);
      }
   }

   // $FF: synthetic method
   static KindErrors $anonfun$checkKindBounds0$5(final Symbols.Symbol hkparam$1, final Symbols.Symbol hkarg$1, final KindErrors x$7) {
      Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
      return x$7.varianceError(new Tuple2(hkparam$1, hkarg$1));
   }

   // $FF: synthetic method
   static KindErrors $anonfun$checkKindBounds0$6(final Symbols.Symbol hkarg$1, final Symbols.Symbol hkparam$1, final KindErrors x$8) {
      Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
      return x$8.varianceError(new Tuple2(hkarg$1, hkparam$1));
   }

   // $FF: synthetic method
   static KindErrors $anonfun$checkKindBounds0$7(final Symbols.Symbol hkparam$1, final Symbols.Symbol hkarg$1, final KindErrors x$9) {
      Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
      return x$9.strictnessError(new Tuple2(hkparam$1, hkarg$1));
   }

   // $FF: synthetic method
   static KindErrors $anonfun$checkKindBounds0$8(final Symbols.Symbol hkarg$1, final Symbols.Symbol hkparam$1, final KindErrors x$10) {
      Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
      return x$10.strictnessError(new Tuple2(hkarg$1, hkparam$1));
   }

   // $FF: synthetic method
   static void $anonfun$checkKindBounds0$4(final Kinds $this, final boolean flip$1, final List tparams$1, final List targs$1, final Types.Type pre$1, final Symbols.Symbol paramOwner$1, final List underHKParams$1, final List withHKArgs$1, final Symbols.Symbol owner$1, final Types.Type argPre$1, final Symbols.Symbol argOwner$1, final ObjectRef kindErrors$1, final boolean explainErrors$1, final Object nonLocalReturnKey1$1, final Symbols.Symbol hkarg, final Symbols.Symbol hkparam) {
      if (hkparam.typeParams().isEmpty() && hkarg.typeParams().isEmpty()) {
         if (flip$1) {
            if (!$this.variancesMatch(hkparam, hkarg)) {
               KindErrors var21 = (KindErrors)kindErrors$1.elem;
               kindErrors$1.elem = $anonfun$checkKindBounds0$5(hkparam, hkarg, var21);
            }
         } else if (!$this.variancesMatch(hkarg, hkparam)) {
            KindErrors var22 = (KindErrors)kindErrors$1.elem;
            kindErrors$1.elem = $anonfun$checkKindBounds0$6(hkarg, hkparam, var22);
         }

         Types.Type declaredBounds = hkparam.info().instantiateTypeParams(tparams$1, targs$1).bounds().asSeenFrom(pre$1, paramOwner$1);
         Types.Type declaredBoundsInst = declaredBounds.substSym(underHKParams$1, withHKArgs$1).asSeenFrom(pre$1, owner$1);
         Types.Type argumentBounds = hkarg.info().bounds().asSeenFrom(argPre$1, argOwner$1).asSeenFrom(pre$1, owner$1);
         if (flip$1) {
            if (!argumentBounds.$less$colon$less(declaredBoundsInst)) {
               KindErrors var23 = (KindErrors)kindErrors$1.elem;
               kindErrors$1.elem = $anonfun$checkKindBounds0$7(hkparam, hkarg, var23);
            }
         } else if (!declaredBoundsInst.$less$colon$less(argumentBounds)) {
            KindErrors var24 = (KindErrors)kindErrors$1.elem;
            kindErrors$1.elem = $anonfun$checkKindBounds0$8(hkarg, hkparam, var24);
         }

         ((SymbolTable)$this).debuglog(() -> (new StringBuilder(129)).append("checkKindBoundsHK base case: ").append(var25).append(" declared bounds: ").append($anonfun$checkKindBounds0$4_declaredBounds).append(" after instantiating earlier hkparams: ").append($anonfun$checkKindBounds0$4_declaredBoundsInst).append("\n").append("checkKindBoundsHK base case: ").append(var24).append(" has bounds: ").append($anonfun$checkKindBounds0$4_argumentBounds).toString());
      } else {
         hkarg.initialize();
         ((SymbolTable)$this).debuglog(() -> (new StringBuilder(55)).append("checkKindBoundsHK recursing to compare params of ").append(var25).append(" with ").append(var24).toString());
         KindErrors var10001 = (KindErrors)kindErrors$1.elem;
         List var10003 = hkarg.typeParams();
         List $plus$plus_suffix = hkparam.typeParams();
         if (underHKParams$1 == null) {
            throw null;
         }

         List var10009 = underHKParams$1.appendedAll($plus$plus_suffix);
         Object var25 = null;
         List $plus$plus_suffix = hkarg.typeParams();
         if (withHKArgs$1 == null) {
            throw null;
         }

         List var10010 = withHKArgs$1.appendedAll($plus$plus_suffix);
         Object var26 = null;
         kindErrors$1.elem = var10001.$plus$plus($this.checkKindBoundsHK$1(var10003, hkarg, argPre$1, argOwner$1, hkparam, paramOwner$1, var10009, var10010, !flip$1, tparams$1, targs$1, pre$1, owner$1, explainErrors$1));
      }

      if (!explainErrors$1 && !((KindErrors)kindErrors$1.elem).isEmpty()) {
         throw new NonLocalReturnControl(nonLocalReturnKey1$1, (KindErrors)kindErrors$1.elem);
      }
   }

   private KindErrors checkKindBoundsHK$1(final List hkargs, final Symbols.Symbol arg, final Types.Type argPre, final Symbols.Symbol argOwner, final Symbols.Symbol param, final Symbols.Symbol paramOwner, final List underHKParams, final List withHKArgs, final boolean flip, final List tparams$1, final List targs$1, final Types.Type pre$1, final Symbols.Symbol owner$1, final boolean explainErrors$1) {
      Object var15 = new Object();

      try {
         KindErrors var31 = this.NoKindErrors();
         List hkparams = param.typeParams();
         MutableSettings.SettingsOps$ var10000 = MutableSettings.SettingsOps$.MODULE$;
         MutableSettings$ var44 = MutableSettings$.MODULE$;
         MutableSettings SettingsOps_settings = ((SymbolTable)this).settings();
         MutableSettings var45 = SettingsOps_settings;
         SettingsOps_settings = null;
         MutableSettings isDebug$extension_$this = var45;
         boolean var46 = StatisticsStatics.DEBUG_GETTER.invokeExact() && BoxesRunTime.unboxToBoolean(isDebug$extension_$this.debug().value());
         isDebug$extension_$this = null;
         if (var46) {
            ((SymbolTable)this).log(() -> (new StringBuilder(59)).append("checkKindBoundsHK expected: ").append(param).append(" with params ").append(hkparams).append(" by definition in ").append(paramOwner).toString());
            ((SymbolTable)this).log(() -> (new StringBuilder(47)).append("checkKindBoundsHK supplied: ").append(arg).append(" with params ").append(hkargs).append(" from ").append(argOwner).toString());
            ((SymbolTable)this).log(() -> (new StringBuilder(43)).append("checkKindBoundsHK under params: ").append(underHKParams).append(" with args ").append(withHKArgs).toString());
         }

         if (!((Collections)this).sameLength(hkargs, hkparams)) {
            Symbols.ClassSymbol var17 = ((Definitions)this).definitions().AnyClass();
            if (arg == null) {
               if (var17 == null) {
                  return this.NoKindErrors();
               }
            } else if (arg.equals(var17)) {
               return this.NoKindErrors();
            }

            Definitions.DefinitionsClass.NothingClass$ var18 = ((Definitions)this).definitions().NothingClass();
            if (arg == null) {
               if (var18 == null) {
                  return this.NoKindErrors();
               }
            } else if (arg.equals(var18)) {
               return this.NoKindErrors();
            }

            Predef.ArrowAssoc var10001 = scala.Predef.ArrowAssoc..MODULE$;
            return var31.arityError(new Tuple2(arg, param));
         } else {
            Collections var47 = (Collections)this;
            List foreach2_ys1 = hkargs;

            for(List foreach2_ys2 = hkparams; !foreach2_ys1.isEmpty() && !foreach2_ys2.isEmpty(); foreach2_ys2 = (List)foreach2_ys2.tail()) {
               var47 = (Collections)foreach2_ys1.head();
               Symbols.Symbol var25 = (Symbols.Symbol)foreach2_ys2.head();
               Symbols.Symbol var24 = (Symbols.Symbol)var47;
               if (var25.typeParams().isEmpty() && var24.typeParams().isEmpty()) {
                  if (flip) {
                     if (!this.variancesMatch(var25, var24)) {
                        var31 = $anonfun$checkKindBounds0$5(var25, var24, var31);
                     }
                  } else if (!this.variancesMatch(var24, var25)) {
                     var31 = $anonfun$checkKindBounds0$6(var24, var25, var31);
                  }

                  Types.Type $anonfun$checkKindBounds0$4_declaredBounds = var25.info().instantiateTypeParams(tparams$1, targs$1).bounds().asSeenFrom(pre$1, paramOwner);
                  Types.Type $anonfun$checkKindBounds0$4_declaredBoundsInst = $anonfun$checkKindBounds0$4_declaredBounds.substSym(underHKParams, withHKArgs).asSeenFrom(pre$1, owner$1);
                  Types.Type $anonfun$checkKindBounds0$4_argumentBounds = var24.info().bounds().asSeenFrom(argPre, argOwner).asSeenFrom(pre$1, owner$1);
                  if (flip) {
                     if (!$anonfun$checkKindBounds0$4_argumentBounds.$less$colon$less($anonfun$checkKindBounds0$4_declaredBoundsInst)) {
                        var31 = $anonfun$checkKindBounds0$7(var25, var24, var31);
                     }
                  } else if (!$anonfun$checkKindBounds0$4_declaredBoundsInst.$less$colon$less($anonfun$checkKindBounds0$4_argumentBounds)) {
                     var31 = $anonfun$checkKindBounds0$8(var24, var25, var31);
                  }

                  ((SymbolTable)this).debuglog(() -> (new StringBuilder(129)).append("checkKindBoundsHK base case: ").append(var25).append(" declared bounds: ").append($anonfun$checkKindBounds0$4_declaredBounds).append(" after instantiating earlier hkparams: ").append($anonfun$checkKindBounds0$4_declaredBoundsInst).append("\n").append("checkKindBoundsHK base case: ").append(var24).append(" has bounds: ").append($anonfun$checkKindBounds0$4_argumentBounds).toString());
               } else {
                  var24.initialize();
                  ((SymbolTable)this).debuglog(() -> (new StringBuilder(55)).append("checkKindBoundsHK recursing to compare params of ").append(var25).append(" with ").append(var24).toString());
                  List var10002 = var24.typeParams();
                  List $anonfun$checkKindBounds0$4_$plus$plus_suffix = var25.typeParams();
                  if (underHKParams == null) {
                     throw null;
                  }

                  List var10008 = underHKParams.appendedAll($anonfun$checkKindBounds0$4_$plus$plus_suffix);
                  Object var40 = null;
                  List $anonfun$checkKindBounds0$4_$plus$plus_suffix = var24.typeParams();
                  if (withHKArgs == null) {
                     throw null;
                  }

                  List var10009 = withHKArgs.appendedAll($anonfun$checkKindBounds0$4_$plus$plus_suffix);
                  Object var42 = null;
                  var31 = var31.$plus$plus(this.checkKindBoundsHK$1(var10002, var24, argPre, argOwner, var25, paramOwner, var10008, var10009, !flip, tparams$1, targs$1, pre$1, owner$1, explainErrors$1));
               }

               if (!explainErrors$1 && !var31.isEmpty()) {
                  throw new NonLocalReturnControl(var15, var31);
               }

               Object var37 = null;
               Object var38 = null;
               Object var39 = null;
               Object var41 = null;
               Object var43 = null;
               foreach2_ys1 = (List)foreach2_ys1.tail();
            }

            Object var34 = null;
            Object var35 = null;
            return explainErrors$1 ? var31 : this.NoKindErrors();
         }
      } catch (NonLocalReturnControl var32) {
         if (var32.key() == var15) {
            return (KindErrors)var32.value();
         } else {
            throw var32;
         }
      }
   }

   // $FF: synthetic method
   static List $anonfun$checkKindBounds0$12(final Kinds $this, final boolean explainErrors$1, final Object nonLocalReturnKey2$1, final List tparams$1, final List targs$1, final Types.Type pre$1, final Symbols.Symbol owner$1, final Symbols.Symbol tparam, final Types.Type targ) {
      Types.WildcardType$ var9 = ((Types)$this).WildcardType();
      if (targ == null) {
         if (var9 == null) {
            return .MODULE$;
         }
      } else if (targ.equals(var9)) {
         return .MODULE$;
      }

      Symbols.Symbol targSym = targ.typeSymbolDirect().initialize();
      Types.Type targPre = targ.prefixDirect();
      List tparamsHO = targ.typeParams();
      if (!targ.isHigherKinded() && !tparam.typeParams().nonEmpty()) {
         return .MODULE$;
      } else {
         KindErrors kindErrors = $this.checkKindBoundsHK$1(tparamsHO, targSym, targPre, targSym.owner(), tparam, tparam.owner(), tparam.typeParams(), tparamsHO, false, tparams$1, targs$1, pre$1, owner$1, explainErrors$1);
         if (kindErrors.isEmpty()) {
            return .MODULE$;
         } else if (explainErrors$1) {
            return new scala.collection.immutable..colon.colon(new Tuple3(targ, tparam, kindErrors), .MODULE$);
         } else {
            throw new NonLocalReturnControl(nonLocalReturnKey2$1, new scala.collection.immutable..colon.colon(new Tuple3(((Types)$this).NoType(), ((Symbols)$this).NoSymbol(), $this.NoKindErrors()), .MODULE$));
         }
      }
   }

   static void $init$(final Kinds $this) {
      $this.scala$reflect$internal$Kinds$_setter_$NoKindErrors_$eq((SymbolTable)$this.new KindErrors(.MODULE$, .MODULE$, .MODULE$));
   }

   // $FF: synthetic method
   static Object $anonfun$checkKindBounds0$4$adapted(final Kinds $this, final boolean flip$1, final List tparams$1, final List targs$1, final Types.Type pre$1, final Symbols.Symbol paramOwner$1, final List underHKParams$1, final List withHKArgs$1, final Symbols.Symbol owner$1, final Types.Type argPre$1, final Symbols.Symbol argOwner$1, final ObjectRef kindErrors$1, final boolean explainErrors$1, final Object nonLocalReturnKey1$1, final Symbols.Symbol hkarg, final Symbols.Symbol hkparam) {
      $anonfun$checkKindBounds0$4($this, flip$1, tparams$1, targs$1, pre$1, paramOwner$1, underHKParams$1, withHKArgs$1, owner$1, argPre$1, argOwner$1, kindErrors$1, explainErrors$1, nonLocalReturnKey1$1, hkarg, hkparam);
      return BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class KindErrors implements Product, Serializable {
      private final List arity;
      private final List variance;
      private final List strictness;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public List arity() {
         return this.arity;
      }

      public List variance() {
         return this.variance;
      }

      public List strictness() {
         return this.strictness;
      }

      public boolean isEmpty() {
         return this.arity().isEmpty() && this.variance().isEmpty() && this.strictness().isEmpty();
      }

      public KindErrors arityError(final Tuple2 syms) {
         List var10001 = this.arity();
         if (var10001 == null) {
            throw null;
         } else {
            return this.copy((List)StrictOptimizedSeqOps.appended$(var10001, syms), this.copy$default$2(), this.copy$default$3());
         }
      }

      public KindErrors varianceError(final Tuple2 syms) {
         List var10000 = this.variance();
         if (var10000 == null) {
            throw null;
         } else {
            List x$1 = (List)StrictOptimizedSeqOps.appended$(var10000, syms);
            List x$2 = this.copy$default$1();
            List x$3 = this.copy$default$3();
            return this.copy(x$2, x$1, x$3);
         }
      }

      public KindErrors strictnessError(final Tuple2 syms) {
         List var10000 = this.strictness();
         if (var10000 == null) {
            throw null;
         } else {
            List x$1 = (List)StrictOptimizedSeqOps.appended$(var10000, syms);
            List x$2 = this.copy$default$1();
            List x$3 = this.copy$default$2();
            return this.copy(x$2, x$3, x$1);
         }
      }

      public KindErrors $plus$plus(final KindErrors errs) {
         KindErrors var10000 = new KindErrors;
         SymbolTable var10002 = this.scala$reflect$internal$Kinds$KindErrors$$$outer();
         List var10003 = this.arity();
         List $plus$plus_suffix = errs.arity();
         if (var10003 == null) {
            throw null;
         } else {
            var10003 = var10003.appendedAll($plus$plus_suffix);
            Object var5 = null;
            List var10004 = this.variance();
            List $plus$plus_suffixx = errs.variance();
            if (var10004 == null) {
               throw null;
            } else {
               var10004 = var10004.appendedAll($plus$plus_suffixx);
               Object var6 = null;
               List var10005 = this.strictness();
               List $plus$plus_suffixxx = errs.strictness();
               if (var10005 == null) {
                  throw null;
               } else {
                  var10005 = var10005.appendedAll($plus$plus_suffixxx);
                  Object var7 = null;
                  var10000.<init>(var10003, var10004, var10005);
                  return var10000;
               }
            }
         }
      }

      private String varStr(final Symbols.Symbol s) {
         if (s.isCovariant()) {
            return "covariant";
         } else {
            return s.isContravariant() ? "contravariant" : "invariant";
         }
      }

      private String qualify(final Symbols.Symbol a0, final Symbols.Symbol b0) {
         String var10000 = a0.toString();
         String var3 = b0.toString();
         if (var10000 == null) {
            if (var3 != null) {
               return "";
            }
         } else if (!var10000.equals(var3)) {
            return "";
         }

         if (a0 != b0 && a0.owner() != b0.owner()) {
            Symbols.Symbol a = a0;
            Symbols.Symbol b = b0;

            while(true) {
               Names.Name var7 = a.owner().name();
               Names.Name var6 = b.owner().name();
               if (var7 == null) {
                  if (var6 != null) {
                     break;
                  }
               } else if (!var7.equals(var6)) {
                  break;
               }

               a = a.owner();
               b = b.owner();
            }

            if (a.locationString() != "") {
               return (new StringBuilder(3)).append(" (").append(a.locationString().trim()).append(")").toString();
            } else {
               return "";
            }
         } else {
            return "";
         }
      }

      private String kindMessage(final Symbols.Symbol a, final Symbols.Symbol p, final Function2 f) {
         return (String)f.apply((new StringBuilder(0)).append(a.toString()).append(this.qualify(a, p)).toString(), (new StringBuilder(0)).append(p.toString()).append(this.qualify(p, a)).toString());
      }

      private String strictnessMessage(final Symbols.Symbol a, final Symbols.Symbol p) {
         String var10000 = (new StringBuilder(0)).append(a.toString()).append(this.qualify(a, p)).toString();
         String var4 = (new StringBuilder(0)).append(p.toString()).append(this.qualify(p, a)).toString();
         String var3 = var10000;
         return $anonfun$strictnessMessage$1(a, p, var3, var4);
      }

      private String varianceMessage(final Symbols.Symbol a, final Symbols.Symbol p) {
         String var10000 = (new StringBuilder(0)).append(a.toString()).append(this.qualify(a, p)).toString();
         String var4 = (new StringBuilder(0)).append(p.toString()).append(this.qualify(p, a)).toString();
         String var3 = var10000;
         return $anonfun$varianceMessage$1(this, a, p, var3, var4);
      }

      private String arityMessage(final Symbols.Symbol a, final Symbols.Symbol p) {
         String var10000 = (new StringBuilder(0)).append(a.toString()).append(this.qualify(a, p)).toString();
         String var4 = (new StringBuilder(0)).append(p.toString()).append(this.qualify(p, a)).toString();
         String var3 = var10000;
         return $anonfun$arityMessage$1(a, p, var3, var4);
      }

      private String buildMessage(final List xs, final Function2 f) {
         if (xs.isEmpty()) {
            return "";
         } else {
            List var10000 = xs.map(f.tupled());
            String mkString_end = "";
            String mkString_sep = ", ";
            String mkString_start = "\n";
            if (var10000 == null) {
               throw null;
            } else {
               return IterableOnceOps.mkString$(var10000, mkString_start, mkString_sep, mkString_end);
            }
         }
      }

      public String errorMessage(final Types.Type targ, final Symbols.Symbol tparam) {
         StringBuilder var10000 = (new StringBuilder(55)).append(targ).append("'s type parameters do not match ").append(tparam).append("'s expected parameters:");
         List var10001 = this.arity();
         Function2 buildMessage_f = (a, p) -> this.arityMessage(a, p);
         List buildMessage_xs = var10001;
         String var44;
         if (buildMessage_xs.isEmpty()) {
            var44 = "";
         } else {
            List var45 = buildMessage_xs.map(buildMessage_f.tupled());
            String buildMessage_mkString_end = "";
            String buildMessage_mkString_sep = ", ";
            String buildMessage_mkString_start = "\n";
            if (var45 == null) {
               throw null;
            }

            var44 = IterableOnceOps.mkString$(var45, buildMessage_mkString_start, buildMessage_mkString_sep, buildMessage_mkString_end);
            Object var20 = null;
            Object var22 = null;
            Object var24 = null;
         }

         Object var18 = null;
         Object var19 = null;
         Object var21 = null;
         Object var23 = null;
         Object var25 = null;
         var10000 = var10000.append(var44);
         List var46 = this.variance();
         Function2 buildMessage_fx = (a, p) -> this.varianceMessage(a, p);
         List buildMessage_xs = var46;
         String var47;
         if (buildMessage_xs.isEmpty()) {
            var47 = "";
         } else {
            List var48 = buildMessage_xs.map(buildMessage_fx.tupled());
            String buildMessage_mkString_endx = "";
            String buildMessage_mkString_sepx = ", ";
            String buildMessage_mkString_startx = "\n";
            if (var48 == null) {
               throw null;
            }

            var47 = IterableOnceOps.mkString$(var48, buildMessage_mkString_startx, buildMessage_mkString_sepx, buildMessage_mkString_endx);
            Object var28 = null;
            Object var30 = null;
            Object var32 = null;
         }

         Object var26 = null;
         Object var27 = null;
         Object var29 = null;
         Object var31 = null;
         Object var33 = null;
         var10000 = var10000.append(var47);
         List var49 = this.strictness();
         Function2 buildMessage_fxx = (a, p) -> this.strictnessMessage(a, p);
         List buildMessage_xs = var49;
         String var50;
         if (buildMessage_xs.isEmpty()) {
            var50 = "";
         } else {
            List var51 = buildMessage_xs.map(buildMessage_fxx.tupled());
            String buildMessage_mkString_endxx = "";
            String buildMessage_mkString_sepxx = ", ";
            String buildMessage_mkString_startxx = "\n";
            if (var51 == null) {
               throw null;
            }

            var50 = IterableOnceOps.mkString$(var51, buildMessage_mkString_startxx, buildMessage_mkString_sepxx, buildMessage_mkString_endxx);
            Object var36 = null;
            Object var38 = null;
            Object var40 = null;
         }

         Object var34 = null;
         Object var35 = null;
         Object var37 = null;
         Object var39 = null;
         Object var41 = null;
         return var10000.append(var50).toString();
      }

      public KindErrors copy(final List arity, final List variance, final List strictness) {
         return this.scala$reflect$internal$Kinds$KindErrors$$$outer().new KindErrors(arity, variance, strictness);
      }

      public List copy$default$1() {
         return this.arity();
      }

      public List copy$default$2() {
         return this.variance();
      }

      public List copy$default$3() {
         return this.strictness();
      }

      public String productPrefix() {
         return "KindErrors";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.arity();
            case 1:
               return this.variance();
            case 2:
               return this.strictness();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return new ScalaRunTime..anon.1(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof KindErrors;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "arity";
            case 1:
               return "variance";
            case 2:
               return "strictness";
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
            if (x$1 instanceof KindErrors && ((KindErrors)x$1).scala$reflect$internal$Kinds$KindErrors$$$outer() == this.scala$reflect$internal$Kinds$KindErrors$$$outer()) {
               KindErrors var2 = (KindErrors)x$1;
               List var10000 = this.arity();
               List var3 = var2.arity();
               if (var10000 == null) {
                  if (var3 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var3)) {
                  return false;
               }

               var10000 = this.variance();
               List var4 = var2.variance();
               if (var10000 == null) {
                  if (var4 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var4)) {
                  return false;
               }

               var10000 = this.strictness();
               List var5 = var2.strictness();
               if (var10000 == null) {
                  if (var5 != null) {
                     return false;
                  }
               } else if (!var10000.equals(var5)) {
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
      public SymbolTable scala$reflect$internal$Kinds$KindErrors$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$strictnessMessage$1(final Symbols.Symbol a$1, final Symbols.Symbol p$1, final String x$1, final String x$2) {
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         ScalaRunTime var10002 = scala.runtime.ScalaRunTime..MODULE$;
         Object[] var10003 = new Object[]{x$1, a$1.info(), x$2, null};
         Types.Type var4 = p$1.info();
         var10003[3] = var4 instanceof Types.TypeBounds && ((Types.TypeBounds)var4).isEmptyBounds() ? " >: Nothing <: Any" : String.valueOf(var4);
         return var10000.format$extension("%s's bounds%s are stricter than %s's declared bounds%s", var10002.genericWrapArray(var10003));
      }

      // $FF: synthetic method
      public static final String $anonfun$varianceMessage$1(final KindErrors $this, final Symbols.Symbol a$2, final Symbols.Symbol p$2, final String x$3, final String x$4) {
         return scala.collection.StringOps..MODULE$.format$extension("%s is %s, but %s is declared %s", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{x$3, $this.varStr(a$2), x$4, $this.varStr(p$2)}));
      }

      // $FF: synthetic method
      public static final String $anonfun$arityMessage$1(final Symbols.Symbol a$3, final Symbols.Symbol p$3, final String x$5, final String x$6) {
         StringOps var10000 = scala.collection.StringOps..MODULE$;
         ScalaRunTime var10002 = scala.runtime.ScalaRunTime..MODULE$;
         Object[] var10003 = new Object[]{x$5, scala.reflect.internal.util.StringOps.countElementsAsString$(StringOps$.MODULE$, a$3.typeParams().length(), "type parameter"), x$6, null};
         StringOps$ var10006 = StringOps$.MODULE$;
         var10003[3] = Integer.toString(p$3.typeParams().length());
         return var10000.format$extension("%s has %s, but %s has %s", var10002.genericWrapArray(var10003));
      }

      public KindErrors(final List arity, final List variance, final List strictness) {
         this.arity = arity;
         this.variance = variance;
         this.strictness = strictness;
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class KindErrors$ extends AbstractFunction3 implements Serializable {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public List $lessinit$greater$default$1() {
         return .MODULE$;
      }

      public List $lessinit$greater$default$2() {
         return .MODULE$;
      }

      public List $lessinit$greater$default$3() {
         return .MODULE$;
      }

      public final String toString() {
         return "KindErrors";
      }

      public KindErrors apply(final List arity, final List variance, final List strictness) {
         return this.$outer.new KindErrors(arity, variance, strictness);
      }

      public List apply$default$1() {
         return .MODULE$;
      }

      public List apply$default$2() {
         return .MODULE$;
      }

      public List apply$default$3() {
         return .MODULE$;
      }

      public Option unapply(final KindErrors x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.arity(), x$0.variance(), x$0.strictness())));
      }

      public KindErrors$() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }

   public abstract class Kind {
      // $FF: synthetic field
      public final SymbolTable $outer;

      public abstract String description();

      public abstract int order();

      public abstract Types.TypeBounds bounds();

      public abstract String scalaNotation();

      public abstract String starNotation();

      public boolean hasBounds() {
         return !this.bounds().isEmptyBounds();
      }

      public abstract Kinds$Kind$StringState buildState(final Symbols.Symbol sym, final int v, final Kinds$Kind$StringState s);

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Kinds$Kind$$$outer() {
         return this.$outer;
      }

      public Kind() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }

   public class Kind$ {
      private volatile Kinds$Kind$Head$ Head$module;
      private volatile Kinds$Kind$Text$ Text$module;
      private volatile Kinds$Kind$StringState$ StringState$module;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Kinds$Kind$Head$ Head() {
         if (this.Head$module == null) {
            this.Head$lzycompute$1();
         }

         return this.Head$module;
      }

      public Kinds$Kind$Text$ Text() {
         if (this.Text$module == null) {
            this.Text$lzycompute$1();
         }

         return this.Text$module;
      }

      public Kinds$Kind$StringState$ StringState() {
         if (this.StringState$module == null) {
            this.StringState$lzycompute$1();
         }

         return this.StringState$module;
      }

      public Types.Type FromParams(final List tparams) {
         return this.$outer.GenPolyType().apply(tparams, this.$outer.definitions().AnyTpe());
      }

      public Types.Type Wildcard() {
         return this.$outer.WildcardType();
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Kinds$Kind$$$outer() {
         return this.$outer;
      }

      private final void Head$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Head$module == null) {
               this.Head$module = new Kinds$Kind$Head$(this);
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final void Text$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Text$module == null) {
               this.Text$module = new Kinds$Kind$Text$(this);
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      private final void StringState$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.StringState$module == null) {
               this.StringState$module = new Kinds$Kind$StringState$(this);
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      public Kind$() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }

   public class ProperTypeKind extends Kind {
      private final Types.TypeBounds bounds;
      private final String description;
      private final int order;

      public Types.TypeBounds bounds() {
         return this.bounds;
      }

      public String description() {
         return this.description;
      }

      public int order() {
         return this.order;
      }

      public Kinds$Kind$StringState buildState(final Symbols.Symbol sym, final int v, final Kinds$Kind$StringState s) {
         return s.append(Variance$.MODULE$.symbolicString$extension(v)).appendHead(this.order(), sym).append(this.bounds().scalaNotation((x$12) -> x$12.toString()));
      }

      public String scalaNotation() {
         return (new StringBuilder(0)).append((new Kinds$Kind$Head(this.scala$reflect$internal$Kinds$ProperTypeKind$$$outer().Kind(), this.order(), scala.None..MODULE$, scala.None..MODULE$)).toString()).append(this.bounds().scalaNotation((x$13) -> x$13.toString())).toString();
      }

      public String starNotation() {
         return (new StringBuilder(1)).append("*").append(this.bounds().starNotation((x$14) -> x$14.toString())).toString();
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Kinds$ProperTypeKind$$$outer() {
         return this.$outer;
      }

      public ProperTypeKind(final Types.TypeBounds bounds) {
         this.bounds = bounds;
         this.description = "This is a proper type.";
         this.order = 0;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ProperTypeKind$ {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public ProperTypeKind apply() {
         return this.apply(this.$outer.TypeBounds().empty());
      }

      public ProperTypeKind apply(final Types.TypeBounds bounds) {
         return this.$outer.new ProperTypeKind(bounds);
      }

      public Some unapply(final ProperTypeKind ptk) {
         return new Some(ptk.bounds());
      }

      public ProperTypeKind$() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }

   public class TypeConKind extends Kind {
      private final Types.TypeBounds bounds;
      private final Seq args;
      private final int order;

      public Types.TypeBounds bounds() {
         return this.bounds;
      }

      public Seq args() {
         return this.args;
      }

      public int order() {
         return this.order;
      }

      public String description() {
         return this.order() == 1 ? "This is a type constructor: a 1st-order-kinded type." : "This is a type constructor that takes type constructor(s): a higher-kinded type.";
      }

      public boolean hasBounds() {
         return super.hasBounds() || this.args().exists((x$16) -> BoxesRunTime.boxToBoolean($anonfun$hasBounds$1(x$16)));
      }

      public String scalaNotation() {
         Kinds$Kind$StringState s = this.buildState(this.scala$reflect$internal$Kinds$TypeConKind$$$outer().NoSymbol(), Variance$.MODULE$.Invariant(), this.scala$reflect$internal$Kinds$TypeConKind$$$outer().Kind().StringState().empty()).removeOnes();
         return (this.hasBounds() ? s : s.removeAlias()).toString();
      }

      public Kinds$Kind$StringState buildState(final Symbols.Symbol sym, final int v, final Kinds$Kind$StringState s0) {
         ObjectRef s = new ObjectRef(s0);
         s.elem = ((Kinds$Kind$StringState)s.elem).append(Variance$.MODULE$.symbolicString$extension(v)).appendHead(this.order(), sym).append("[");
         ((IterableOnceOps)this.args().zipWithIndex()).foreach((x0$1) -> {
            $anonfun$buildState$2(this, s, x0$1);
            return BoxedUnit.UNIT;
         });
         s.elem = ((Kinds$Kind$StringState)s.elem).append("]").append(this.bounds().scalaNotation((x$17) -> x$17.toString()));
         return (Kinds$Kind$StringState)s.elem;
      }

      public String starNotation() {
         StringBuilder var10000 = new StringBuilder(1);
         IterableOnceOps var10001 = (IterableOnceOps)this.args().map((arg) -> (new StringBuilder(0)).append(arg.kind().order() == 0 ? arg.kind().starNotation() : (new StringBuilder(2)).append("(").append(arg.kind().starNotation()).append(")").toString()).append(arg.variance() == Variance$.MODULE$.Invariant() ? " -> " : (new StringBuilder(7)).append(" -(").append(Variance$.MODULE$.symbolicString$extension(arg.variance())).append(")-> ").toString()).toString());
         if (var10001 == null) {
            throw null;
         } else {
            IterableOnceOps mkString_this = var10001;
            String mkString_mkString_sep = "";
            String var5 = mkString_this.mkString("", mkString_mkString_sep, "");
            Object var3 = null;
            Object var4 = null;
            return var10000.append(var5).append("*").append(this.bounds().starNotation((x$18) -> x$18.toString())).toString();
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Kinds$TypeConKind$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$order$1(final Kinds$TypeConKind$Argument x$15) {
         return x$15.kind().order();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$hasBounds$1(final Kinds$TypeConKind$Argument x$16) {
         return x$16.kind().hasBounds();
      }

      // $FF: synthetic method
      public static final void $anonfun$buildState$2(final TypeConKind $this, final ObjectRef s$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            Kinds$TypeConKind$Argument arg = (Kinds$TypeConKind$Argument)x0$1._1();
            int i = x0$1._2$mcI$sp();
            s$1.elem = arg.kind().buildState(arg.sym(), arg.variance(), (Kinds$Kind$StringState)s$1.elem);
            Seq var10001 = $this.args();
            if (var10001 == null) {
               throw null;
            } else if (i != var10001.length() - 1) {
               s$1.elem = ((Kinds$Kind$StringState)s$1.elem).append(",");
            }
         } else {
            throw new MatchError((Object)null);
         }
      }

      public TypeConKind(final Types.TypeBounds bounds, final Seq args) {
         this.bounds = bounds;
         this.args = args;
         this.order = BoxesRunTime.unboxToInt(((IterableOnceOps)args.map((x$15) -> BoxesRunTime.boxToInteger($anonfun$order$1(x$15)))).max(scala.math.Ordering.Int..MODULE$)) + 1;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class TypeConKind$ {
      private volatile Kinds$TypeConKind$Argument$ Argument$module;
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Kinds$TypeConKind$Argument$ Argument() {
         if (this.Argument$module == null) {
            this.Argument$lzycompute$1();
         }

         return this.Argument$module;
      }

      public TypeConKind apply(final Seq args) {
         return this.apply(this.$outer.TypeBounds().empty(), args);
      }

      public TypeConKind apply(final Types.TypeBounds bounds, final Seq args) {
         return this.$outer.new TypeConKind(bounds, args);
      }

      public Some unapply(final TypeConKind tck) {
         return new Some(new Tuple2(tck.bounds(), tck.args()));
      }

      private final void Argument$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Argument$module == null) {
               this.Argument$module = new Kinds$TypeConKind$Argument$(this);
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      public TypeConKind$() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }

   public class inferKind$ {
      // $FF: synthetic field
      private final SymbolTable $outer;

      public Kinds$inferKind$InferKind apply(final Types.Type pre) {
         return new Kinds$inferKind$InferKind(pre) {
            // $FF: synthetic field
            private final inferKind$ $outer;
            private final Types.Type pre$2;

            public Kind infer(final Types.Type tpe, final Symbols.Symbol owner, final boolean topLevel) {
               Types.TypeBounds bounds = topLevel ? this.$outer.scala$reflect$internal$Kinds$inferKind$$$outer().TypeBounds().empty() : tpe.asSeenFrom(this.pre$2, owner).bounds();
               if (!tpe.isHigherKinded()) {
                  return this.$outer.scala$reflect$internal$Kinds$inferKind$$$outer().ProperTypeKind().apply(bounds);
               } else {
                  TypeConKind$ var10000 = this.$outer.scala$reflect$internal$Kinds$inferKind$$$outer().TypeConKind();
                  List var10002 = tpe.typeParams();
                  if (var10002 == null) {
                     throw null;
                  } else {
                     List map_this = var10002;
                     Object var17;
                     if (map_this == .MODULE$) {
                        var17 = .MODULE$;
                     } else {
                        Symbols.Symbol var10 = (Symbols.Symbol)map_this.head();
                        scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$infer$1(this, var10), .MODULE$);
                        scala.collection.immutable..colon.colon map_t = map_h;

                        for(List map_rest = (List)map_this.tail(); map_rest != .MODULE$; map_rest = (List)map_rest.tail()) {
                           var10 = (Symbols.Symbol)map_rest.head();
                           scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$infer$1(this, var10), .MODULE$);
                           map_t.next_$eq(map_nx);
                           map_t = map_nx;
                        }

                        Statics.releaseFence();
                        var17 = map_h;
                     }

                     Object var11 = null;
                     Object var12 = null;
                     Object var13 = null;
                     Object var14 = null;
                     Object var15 = null;
                     return var10000.apply(bounds, (Seq)var17);
                  }
               }
            }

            // $FF: synthetic method
            public static final Kinds$TypeConKind$Argument $anonfun$infer$1(final Object $this, final Symbols.Symbol p) {
               return new Kinds$TypeConKind$Argument($this.$outer.scala$reflect$internal$Kinds$inferKind$$$outer().TypeConKind(), p.variance(), $this.infer(p, false), p);
            }

            public {
               if (inferKind$.this == null) {
                  throw null;
               } else {
                  this.$outer = inferKind$.this;
                  this.pre$2 = pre$2;
               }
            }
         };
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$Kinds$inferKind$$$outer() {
         return this.$outer;
      }

      public inferKind$() {
         if (Kinds.this == null) {
            throw null;
         } else {
            this.$outer = Kinds.this;
            super();
         }
      }
   }
}
