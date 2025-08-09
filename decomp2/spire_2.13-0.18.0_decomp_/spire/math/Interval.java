package spire.math;

import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.Field;
import algebra.ring.Ring;
import algebra.ring.Semiring;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.math.Ordering;
import scala.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import spire.algebra.NRoot;
import spire.math.interval.Bound;
import spire.math.interval.Bound$;
import spire.math.interval.Closed;
import spire.math.interval.EmptyBound;
import spire.math.interval.Open;
import spire.math.interval.Overlap;
import spire.math.interval.Overlap$;
import spire.math.interval.Unbound;
import spire.math.interval.ValueBound;
import spire.math.interval.ValueBound$;
import spire.math.poly.PolySparse;
import spire.math.poly.Term;
import spire.random.Dist;
import spire.random.Uniform;

@ScalaSignature(
   bytes = "\u0006\u0005\u001dmf!B={\u0003Cy\bbBA\u0014\u0001\u0011\u0005\u0011\u0011\u0006\u0005\t\u0003\u000b\u0002\u0001\u0015\"\u0006\u0002H!A\u0011\u0011\r\u0001!\n+\t\u0019\u0007\u0003\u0005\u0002j\u0001\u0001KQCA6\u0011!\t\t\b\u0001Q\u0005\u0016\u0005M\u0004\u0002CA=\u0001\u0001&)\"a\u001f\t\u0011\u0005\u0005\u0005\u0001)C\u000b\u0003\u0007C\u0001\"!#\u0001A\u0013U\u00111\u0012\u0005\t\u0003#\u0003\u0001\u0015\"\u0006\u0002\u0014\"A\u0011\u0011\u0014\u0001!\n+\tY\n\u0003\u0005\u0002\"\u0002\u0001KQCAR\u0011!\tI\u000b\u0001Q\u0005\u0016\u0005-\u0006\u0002CAY\u0001\u0001&)\"a-\t\u0011\u0005]\u0006\u0001)C\u000b\u0003sC\u0001\"!0\u0001A\u0013U\u0011q\u0018\u0005\t\u0003\u000b\u0004\u0001\u0015\"\u0005\u0002H\"A\u0011q\u001f\u0001!\n#\tI\u0010C\u0004\u0003\f\u0001!\tA!\u0004\t\u000f\t=\u0001\u0001\"\u0001\u0003\u000e!9!\u0011\u0003\u0001\u0005\u0002\t5\u0001b\u0002B\n\u0001\u0011\u0005!Q\u0003\u0005\b\u0005?\u0001A\u0011\u0001B\u0011\u0011\u001d\u0011I\u0003\u0001C\u0001\u0005WAqAa\r\u0001\t\u0003\u0011)\u0004C\u0004\u0003D\u0001!\tA!\u0004\t\u000f\t\u0015\u0003A\"\u0001\u0003H!9!Q\u000b\u0001\u0007\u0002\t\u001d\u0003b\u0002B,\u0001\u0011\u0005!\u0011\f\u0005\b\u0005o\u0002A\u0011\u0001B=\u0011\u001d\u0011I\t\u0001C\u0001\u0005\u0017CqA!&\u0001\t\u0003\u00119\nC\u0004\u0003 \u0002!\tA!)\t\u000f\t%\u0006\u0001\"\u0001\u0003,\"9!1\u0017\u0001\u0005\u0002\tU\u0006b\u0002B_\u0001\u0011\u0005!q\u0018\u0005\b\u0005\u000f\u0004A\u0011\u0001Be\u0011\u001d\u0011\t\u000e\u0001C\u0001\u0005'DqAa7\u0001\t\u0003\u0011i\u000eC\u0004\u0003l\u0002!\tA!<\t\u000f\tU\b\u0001\"\u0001\u0003x\"9!q \u0001\u0005\u0002\r\u0005\u0001bBB\u0005\u0001\u0011\u000511\u0002\u0005\b\u0007+\u0001A\u0011AB\f\u0011\u001d\u0019y\u0002\u0001C\u0001\u0007CAqaa\f\u0001\t\u0003\u0019\t\u0004C\u0004\u00048\u0001!\ta!\u000f\t\u000f\r5\u0003\u0001\"\u0001\u0004P!91q\u000b\u0001\u0005\u0002\re\u0003bBB1\u0001\u0011\u000531\r\u0005\b\u0007g\u0002A\u0011AB;\u0011\u001d\u0019\u0019\t\u0001C\u0001\u0007\u000bCqa!$\u0001\t\u0003\u0019y\tC\u0004\u0004\u0018\u0002!\ta!'\t\u000f\r\u001d\u0006\u0001\"\u0001\u0004*\"91\u0011\u0018\u0001\u0005\u0002\rm\u0006bBBc\u0001\u0011\u00051q\u0019\u0005\b\u0007/\u0004A\u0011ABm\u0011\u001d\u0019)\u000f\u0001C\u0001\u0007ODqa!:\u0001\t\u0003\u0019\t\u0010C\u0004\u0004(\u0002!\taa?\t\u000f\re\u0006\u0001\"\u0001\u0005\u0004!9A1\u0002\u0001\u0005\u0002\u00115\u0001bBBc\u0001\u0011\u0005A\u0011\u0003\u0005\b\t7\u0001A\u0011\u0001C\u000f\u0011\u001d!\t\u0004\u0001C\u0001\tgAq\u0001\"\u0013\u0001\t\u0003!Y\u0005C\u0004\u0005T\u0001!\t\u0001\"\u0016\t\u000f\u0011\u0015\u0004\u0001\"\u0001\u0005h!9Aq\u000e\u0001\u0005\u0002\u0011E\u0004b\u0002CL\u0001\u0011\u0005A\u0011\u0014\u0005\b\tW\u0003A\u0011\u0001CW\u0011\u001d!)\f\u0001C\u0001\toCq\u0001b0\u0001\t\u0003!\t\rC\u0004\u0005L\u0002!\t\u0001\"4\t\u000f\u0011U\u0007\u0001\"\u0001\u0005X\"9Aq\u001c\u0001\u0005\u0002\u0011\u0005\bb\u0002Cu\u0001\u0011\u0005A1\u001e\u0005\b\tg\u0004A\u0011\u0001C{\u0011\u001d!i\u0010\u0001C\u0001\t\u007fDq!b\u0002\u0001\t\u0003)I\u0001C\u0004\u0006\u0012\u0001!\t!b\u0005\t\u0011\u0015m\u0001\u0001)C\u0005\u000b;Aq!b\f\u0001\t\u0003)\t\u0004C\u0004\u0006L\u0001!\t!\"\u0014\t\u000f\u0015\u0015\u0004\u0001\"\u0001\u0006h!9Q1\u0011\u0001\u0005\u0002\u0015\u0015uaBCWu\"\u0005Qq\u0016\u0004\u0007sjD\t!\"-\t\u000f\u0005\u001d\u0002\f\"\u0001\u0006B\"AQ1\u0019-\u0005\u0002q,)\rC\u0004\u0006bb#\t!b9\t\u000f\u0015M\b\f\"\u0001\u0006v\"9a\u0011\u0002-\u0005\u0002\u0019-\u0001b\u0002D\u00101\u0012\u0005a\u0011\u0005\u0005\b\rcAF\u0011\u0001D\u001a\u0011\u001d1I\u0005\u0017C\u0001\r\u0017B\u0001Bb\u0018Y\t\u000bah\u0011\r\u0005\t\rKBFQ\u0001?\u0007b!Aa\u0011\u000e-\u0005\u0006q4\t\u0007\u0003\u0005\u0007na#)\u0001 D1\u0011!1\t\b\u0017C\u0001y\u001aM\u0004b\u0002DF1\u0012\u0005aQ\u0012\u0005\b\rKCF\u0011\u0001DT\u0011\u001d1i\f\u0017C\u0001\r\u007fCqA\"6Y\t\u000319\u000eC\u0004\u0007nb#\tAb<\t\u000f\u001d\u0015\u0001\f\"\u0001\b\b!9q1\u0004-\u0005\u0002\u001du\u0001bBD\u00191\u0012\u0005q1\u0007\u0005\b\u000f\u000fBF\u0011AD%\u0011%9i\u0006\u0017b\u0001\n\u00139y\u0006\u0003\u0005\bra\u0003\u000b\u0011BD1\u0011%9\u0019\b\u0017b\u0001\n\u00139y\u0006\u0003\u0005\bva\u0003\u000b\u0011BD1\u0011%99\b\u0017b\u0001\n\u00139y\u0006\u0003\u0005\bza\u0003\u000b\u0011BD1\u0011\u001d1\t\u0004\u0017C\u0001\u000fwBqa\"!Y\t\u00079\u0019\tC\u0004\b\u0016b#\u0019ab&\t\u0013\u001d-\u0006,!A\u0005\n\u001d5&\u0001C%oi\u0016\u0014h/\u00197\u000b\u0005md\u0018\u0001B7bi\"T\u0011!`\u0001\u0006gBL'/Z\u0002\u0001+\u0011\t\t!a\r\u0014\u000b\u0001\t\u0019!a\u0004\u0011\t\u0005\u0015\u00111B\u0007\u0003\u0003\u000fQ!!!\u0003\u0002\u000bM\u001c\u0017\r\\1\n\t\u00055\u0011q\u0001\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005E\u0011\u0011\u0005\b\u0005\u0003'\tiB\u0004\u0003\u0002\u0016\u0005mQBAA\f\u0015\r\tIB`\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005%\u0011\u0002BA\u0010\u0003\u000f\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002$\u0005\u0015\"\u0001D*fe&\fG.\u001b>bE2,'\u0002BA\u0010\u0003\u000f\ta\u0001P5oSRtDCAA\u0016!\u0015\ti\u0003AA\u0018\u001b\u0005Q\b\u0003BA\u0019\u0003ga\u0001\u0001B\u0004\u00026\u0001\u0011\r!a\u000e\u0003\u0003\u0005\u000bB!!\u000f\u0002@A!\u0011QAA\u001e\u0013\u0011\ti$a\u0002\u0003\u000f9{G\u000f[5oOB!\u0011QAA!\u0013\u0011\t\u0019%a\u0002\u0003\u0007\u0005s\u00170\u0001\u0005jg\u000ecwn]3e)\u0011\tI%a\u0014\u0011\t\u0005\u0015\u00111J\u0005\u0005\u0003\u001b\n9AA\u0004C_>dW-\u00198\t\u000f\u0005E#\u00011\u0001\u0002T\u0005)a\r\\1hgB!\u0011QAA+\u0013\u0011\t9&a\u0002\u0003\u0007%sG\u000fK\u0002\u0003\u00037\u0002B!!\u0002\u0002^%!\u0011qLA\u0004\u0005\u0019Ig\u000e\\5oK\u0006i\u0011n]\"m_N,G\rT8xKJ$B!!\u0013\u0002f!9\u0011\u0011K\u0002A\u0002\u0005M\u0003fA\u0002\u0002\\\u0005i\u0011n]\"m_N,G-\u00169qKJ$B!!\u0013\u0002n!9\u0011\u0011\u000b\u0003A\u0002\u0005M\u0003f\u0001\u0003\u0002\\\u00051\u0011n](qK:$B!!\u0013\u0002v!9\u0011\u0011K\u0003A\u0002\u0005M\u0003fA\u0003\u0002\\\u0005Y\u0011n](qK:dun^3s)\u0011\tI%! \t\u000f\u0005Ec\u00011\u0001\u0002T!\u001aa!a\u0017\u0002\u0017%\u001cx\n]3o+B\u0004XM\u001d\u000b\u0005\u0003\u0013\n)\tC\u0004\u0002R\u001d\u0001\r!a\u0015)\u0007\u001d\tY&A\u0005m_^,'O\u00127bOR!\u00111KAG\u0011\u001d\t\t\u0006\u0003a\u0001\u0003'B3\u0001CA.\u0003%)\b\u000f]3s\r2\fw\r\u0006\u0003\u0002T\u0005U\u0005bBA)\u0013\u0001\u0007\u00111\u000b\u0015\u0004\u0013\u0005m\u0013\u0001\u0005:fm\u0016\u00148/\u001a'po\u0016\u0014h\t\\1h)\u0011\t\u0019&!(\t\u000f\u0005E#\u00021\u0001\u0002T!\u001a!\"a\u0017\u0002!I,g/\u001a:tKV\u0003\b/\u001a:GY\u0006<G\u0003BA*\u0003KCq!!\u0015\f\u0001\u0004\t\u0019\u0006K\u0002\f\u00037\nAB]3wKJ\u001cXM\u00127bON$B!a\u0015\u0002.\"9\u0011\u0011\u000b\u0007A\u0002\u0005M\u0003f\u0001\u0007\u0002\\\u0005\u0001Bn\\<fe\u001ac\u0017m\u001a+p+B\u0004XM\u001d\u000b\u0005\u0003'\n)\fC\u0004\u0002R5\u0001\r!a\u0015\u0002!U\u0004\b/\u001a:GY\u0006<Gk\u001c'po\u0016\u0014H\u0003BA*\u0003wCq!!\u0015\u000f\u0001\u0004\t\u0019&A\u0005to\u0006\u0004h\t\\1hgR!\u00111KAa\u0011\u001d\t\tf\u0004a\u0001\u0003'B3aDA.\u00039awn^3s!\u0006L'OQ3m_^$\"\"!3\u0002h\u0006-\u0018q^Az)\u0011\tI%a3\t\u000f\u00055\u0007\u0003q\u0001\u0002P\u0006\tq\u000e\u0005\u0004\u0002R\u0006\u0005\u0018q\u0006\b\u0005\u0003'\fiN\u0004\u0003\u0002V\u0006eg\u0002BA\u000b\u0003/L\u0011!`\u0005\u0004\u00037d\u0018aB1mO\u0016\u0014'/Y\u0005\u0005\u0003?\tyNC\u0002\u0002\\rLA!a9\u0002f\n)qJ\u001d3fe*!\u0011qDAp\u0011\u001d\tI\u000f\u0005a\u0001\u0003_\ta\u0001\\8xKJ\f\u0004bBAw!\u0001\u0007\u00111K\u0001\u0007M2\fwm]\u0019\t\u000f\u0005E\b\u00031\u0001\u00020\u00051An\\<feJBq!!>\u0011\u0001\u0004\t\u0019&\u0001\u0004gY\u0006<7OM\u0001\u000fkB\u0004XM\u001d)bSJ\f%m\u001c<f))\tY0a@\u0003\u0004\t\u0015!\u0011\u0002\u000b\u0005\u0003\u0013\ni\u0010C\u0004\u0002NF\u0001\u001d!a4\t\u000f\t\u0005\u0011\u00031\u0001\u00020\u00051Q\u000f\u001d9feFBq!!<\u0012\u0001\u0004\t\u0019\u0006C\u0004\u0003\bE\u0001\r!a\f\u0002\rU\u0004\b/\u001a:3\u0011\u001d\t)0\u0005a\u0001\u0003'\nq![:F[B$\u00180\u0006\u0002\u0002J\u0005Aan\u001c8F[B$\u00180A\u0004jgB{\u0017N\u001c;\u0002\u0011\r|g\u000e^1j]N$BAa\u0006\u0003\u001cQ!\u0011\u0011\nB\r\u0011\u001d\ti-\u0006a\u0002\u0003\u001fDqA!\b\u0016\u0001\u0004\ty#A\u0001u\u00039!w.Z:O_R\u001cuN\u001c;bS:$BAa\t\u0003(Q!\u0011\u0011\nB\u0013\u0011\u001d\tiM\u0006a\u0002\u0003\u001fDqA!\b\u0017\u0001\u0004\ty#A\u0004de>\u001c8/Z:\u0015\t\t5\"\u0011\u0007\u000b\u0005\u0003\u0013\u0012y\u0003C\u0004\u0002N^\u0001\u001d!a4\t\u000f\tuq\u00031\u0001\u00020\u0005Y1M]8tg\u0016\u001c(,\u001a:p)\u0019\tIEa\u000e\u0003:!9\u0011Q\u001a\rA\u0004\u0005=\u0007b\u0002B\u001e1\u0001\u000f!QH\u0001\u0003KZ\u0004b!!5\u0003@\u0005=\u0012\u0002\u0002B!\u0003K\u0014a\"\u00113eSRLg/Z'p]>LG-A\u0005jg\n{WO\u001c3fI\u0006QAn\\<fe\n{WO\u001c3\u0016\u0005\t%\u0003C\u0002B&\u0005#\ny#\u0004\u0002\u0003N)\u0019!q\n>\u0002\u0011%tG/\u001a:wC2LAAa\u0015\u0003N\t)!i\\;oI\u0006QQ\u000f\u001d9fe\n{WO\u001c3\u0002\u00135\f\u0007OQ8v]\u0012\u001cX\u0003\u0002B.\u0005G\"BA!\u0018\u0003nQ!!q\fB4!\u0015\ti\u0003\u0001B1!\u0011\t\tDa\u0019\u0005\u000f\t\u0015DD1\u0001\u00028\t\t!\tC\u0005\u0003jq\t\t\u0011q\u0001\u0003l\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\u0005E\u0017\u0011\u001dB1\u0011\u001d\u0011y\u0007\ba\u0001\u0005c\n\u0011A\u001a\t\t\u0003\u000b\u0011\u0019(a\f\u0003b%!!QOA\u0004\u0005%1UO\\2uS>t\u0017'\u0001\u0003g_2$W\u0003\u0002B>\u0005\u007f\"BA! \u0003\u0002B!\u0011\u0011\u0007B@\t\u001d\u0011)'\bb\u0001\u0003oAqAa\u001c\u001e\u0001\u0004\u0011\u0019\t\u0005\u0006\u0002\u0006\t\u0015%\u0011\nB%\u0005{JAAa\"\u0002\b\tIa)\u001e8di&|gNM\u0001\rSN\u001cV\u000f]3sg\u0016$xJ\u001a\u000b\u0005\u0005\u001b\u0013\t\n\u0006\u0003\u0002J\t=\u0005bBAg=\u0001\u000f\u0011q\u001a\u0005\b\u0005's\u0002\u0019AA\u0016\u0003\r\u0011\bn]\u0001\u0013SN\u0004&o\u001c9feN+\b/\u001a:tKR|e\r\u0006\u0003\u0003\u001a\nuE\u0003BA%\u00057Cq!!4 \u0001\b\ty\rC\u0004\u0003\u0014~\u0001\r!a\u000b\u0002\u0015%\u001c8+\u001e2tKR|e\r\u0006\u0003\u0003$\n\u001dF\u0003BA%\u0005KCq!!4!\u0001\b\ty\rC\u0004\u0003\u0014\u0002\u0002\r!a\u000b\u0002!%\u001c\bK]8qKJ\u001cVOY:fi>3G\u0003\u0002BW\u0005c#B!!\u0013\u00030\"9\u0011QZ\u0011A\u0004\u0005=\u0007b\u0002BJC\u0001\u0007\u00111F\u0001\tQ\u0006\u001c\u0018IY8wKR!!q\u0017B^)\u0011\tIE!/\t\u000f\u00055'\u0005q\u0001\u0002P\"9!Q\u0004\u0012A\u0002\u0005=\u0012\u0001\u00035bg\n+Gn\\<\u0015\t\t\u0005'Q\u0019\u000b\u0005\u0003\u0013\u0012\u0019\rC\u0004\u0002N\u000e\u0002\u001d!a4\t\u000f\tu1\u00051\u0001\u00020\u0005a\u0001.Y:Bi>\u0013\u0018IY8wKR!!1\u001aBh)\u0011\tIE!4\t\u000f\u00055G\u0005q\u0001\u0002P\"9!Q\u0004\u0013A\u0002\u0005=\u0012\u0001\u00045bg\u0006#xJ\u001d\"fY><H\u0003\u0002Bk\u00053$B!!\u0013\u0003X\"9\u0011QZ\u0013A\u0004\u0005=\u0007b\u0002B\u000fK\u0001\u0007\u0011qF\u0001\u0005SN\fE\u000f\u0006\u0003\u0003`\n%H\u0003BA%\u0005CDq!!4'\u0001\b\u0011\u0019\u000f\u0005\u0004\u0002R\n\u0015\u0018qF\u0005\u0005\u0005O\f)O\u0001\u0002Fc\"9!Q\u0004\u0014A\u0002\u0005=\u0012AC5oi\u0016\u00148/Z2ugR!!q\u001eBz)\u0011\tIE!=\t\u000f\u00055w\u0005q\u0001\u0002P\"9!1S\u0014A\u0002\u0005-\u0012\u0001\u0002\u0013b[B$BA!?\u0003~R!\u00111\u0006B~\u0011\u001d\ti\r\u000ba\u0002\u0003\u001fDqAa%)\u0001\u0004\tY#A\u0005j]R,'o]3diR!11AB\u0004)\u0011\tYc!\u0002\t\u000f\u00055\u0017\u0006q\u0001\u0002P\"9!1S\u0015A\u0002\u0005-\u0012\u0001D;oCJLx\f\n;jY\u0012,G\u0003BB\u0007\u0007'\u0001b!!\u0005\u0004\u0010\u0005-\u0012\u0002BB\t\u0003K\u0011A\u0001T5ti\"9\u0011Q\u001a\u0016A\u0004\u0005=\u0017\u0001\u0004\u0013nS:,8\u000fJ7j]V\u001cH\u0003BB\r\u0007;!Ba!\u0004\u0004\u001c!9\u0011QZ\u0016A\u0004\u0005=\u0007b\u0002BJW\u0001\u0007\u00111F\u0001\u0006gBd\u0017\u000e\u001e\u000b\u0005\u0007G\u0019i\u0003\u0006\u0003\u0004&\r-\u0002\u0003CA\u0003\u0007O\tY#a\u000b\n\t\r%\u0012q\u0001\u0002\u0007)V\u0004H.\u001a\u001a\t\u000f\u00055G\u0006q\u0001\u0002P\"9!Q\u0004\u0017A\u0002\u0005=\u0012aC:qY&$\u0018\t\u001e.fe>$ba!\n\u00044\rU\u0002bBAg[\u0001\u000f\u0011q\u001a\u0005\b\u0005wi\u00039\u0001B\u001f\u00035i\u0017\r]!s_VtGMW3s_V!11HB\")\u0011\u0019id!\u0013\u0015\r\r}2QIB$!!\t)aa\n\u0004B\r\u0005\u0003\u0003BA\u0019\u0007\u0007\"qA!\u001a/\u0005\u0004\t9\u0004C\u0004\u0002N:\u0002\u001d!a4\t\u000f\tmb\u0006q\u0001\u0003>!9!q\u000e\u0018A\u0002\r-\u0003\u0003CA\u0003\u0005g\nYc!\u0011\u0002\t\u0011\u0012\u0017M\u001d\u000b\u0005\u0007#\u001a)\u0006\u0006\u0003\u0002,\rM\u0003bBAg_\u0001\u000f\u0011q\u001a\u0005\b\u0005'{\u0003\u0019AA\u0016\u0003\u0015)h.[8o)\u0011\u0019Yfa\u0018\u0015\t\u0005-2Q\f\u0005\b\u0003\u001b\u0004\u00049AAh\u0011\u001d\u0011\u0019\n\ra\u0001\u0003W\t\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0007K\u0002Baa\u001a\u0004n9!\u0011QAB5\u0013\u0011\u0019Y'a\u0002\u0002\rA\u0013X\rZ3g\u0013\u0011\u0019yg!\u001d\u0003\rM#(/\u001b8h\u0015\u0011\u0019Y'a\u0002\u0002\u0007\u0005\u00147\u000f\u0006\u0004\u0002,\r]4\u0011\u0010\u0005\b\u0003\u001b\u0014\u00049AAh\u0011\u001d\u0019YH\ra\u0002\u0007{\n\u0011!\u001c\t\u0007\u0003#\u001cy(a\f\n\t\r\u0005\u0015Q\u001d\u0002\u000e\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9\u0002\tYl\u0017N\u001c\u000b\u0005\u0007\u000f\u001bY\t\u0006\u0003\u0002,\r%\u0005bBAgg\u0001\u000f\u0011q\u001a\u0005\b\u0005'\u001b\u0004\u0019AA\u0016\u0003\u00111X.\u0019=\u0015\t\rE5Q\u0013\u000b\u0005\u0003W\u0019\u0019\nC\u0004\u0002NR\u0002\u001d!a4\t\u000f\tME\u00071\u0001\u0002,\u000591m\\7cS:,G\u0003BBN\u0007K#Ba!(\u0004\"R!\u00111FBP\u0011\u001d\ti-\u000ea\u0002\u0003\u001fDqAa\u001c6\u0001\u0004\u0019\u0019\u000b\u0005\u0006\u0002\u0006\t\u0015\u0015qFA\u0018\u0003_AqAa%6\u0001\u0004\tY#A\u0003%a2,8\u000f\u0006\u0003\u0004,\u000e]FCBA\u0016\u0007[\u001by\u000bC\u0004\u0002NZ\u0002\u001d!a4\t\u000f\tmb\u0007q\u0001\u00042B1\u0011\u0011[BZ\u0003_IAa!.\u0002f\n\t\u0012\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9\t\u000f\tMe\u00071\u0001\u0002,\u00051A%\\5okN$Ba!0\u0004DR1\u00111FB`\u0007\u0003Dq!!48\u0001\b\ty\rC\u0004\u0003<]\u0002\u001da! \t\u000f\tMu\u00071\u0001\u0002,\u00051A\u0005^5nKN$Ba!3\u0004VR1\u00111FBf\u0007\u001bDq!!49\u0001\b\ty\rC\u0004\u0003<a\u0002\u001daa4\u0011\r\u0005E7\u0011[A\u0018\u0013\u0011\u0019\u0019.!:\u0003\u0011M+W.\u001b:j]\u001eDqAa%9\u0001\u0004\tY#\u0001\u0006sK\u000eL\u0007O]8dC2$b!a\u000b\u0004\\\u000eu\u0007bBAgs\u0001\u000f\u0011q\u001a\u0005\b\u0005wI\u00049ABp!\u0019\t\tn!9\u00020%!11]As\u0005\u00151\u0015.\u001a7e\u0003\u0011!C-\u001b<\u0015\t\r%8q\u001e\u000b\u0007\u0003W\u0019Yo!<\t\u000f\u00055'\bq\u0001\u0002P\"9!1\b\u001eA\u0004\r}\u0007b\u0002BJu\u0001\u0007\u00111\u0006\u000b\u0005\u0007g\u001cI\u0010\u0006\u0004\u0002,\rU8q\u001f\u0005\b\u0003\u001b\\\u00049AAh\u0011\u001d\u0011Yd\u000fa\u0002\u0007?DqAa%<\u0001\u0004\ty\u0003\u0006\u0003\u0004~\u0012\u0005A\u0003BA\u0016\u0007\u007fDqAa\u000f=\u0001\b\u0019\t\fC\u0004\u0003\u0014r\u0002\r!a\f\u0015\t\u0011\u0015A\u0011\u0002\u000b\u0005\u0003W!9\u0001C\u0004\u0003<u\u0002\u001da! \t\u000f\tMU\b1\u0001\u00020\u0005aQO\\1ss~#S.\u001b8vgR!\u00111\u0006C\b\u0011\u001d\u0011YD\u0010a\u0002\u0007{\"B\u0001b\u0005\u0005\u001aQ1\u00111\u0006C\u000b\t/Aq!!4@\u0001\b\ty\rC\u0004\u0003<}\u0002\u001daa4\t\u000f\tMu\b1\u0001\u00020\u0005\u0019\u0001o\\<\u0015\t\u0011}AQ\u0006\u000b\u0007\u0003W!\t\u0003b\t\t\u000f\u00055\u0007\tq\u0001\u0002P\"9AQ\u0005!A\u0004\u0011\u001d\u0012!\u0001:\u0011\r\u0005EG\u0011FA\u0018\u0013\u0011!Y#!:\u0003\tIKgn\u001a\u0005\b\t_\u0001\u0005\u0019AA*\u0003\u0005Y\u0017!\u00028s_>$H\u0003\u0002C\u001b\t\u000f\"\u0002\"a\u000b\u00058\u0011eB1\b\u0005\b\u0003\u001b\f\u00059AAh\u0011\u001d!)#\u0011a\u0002\tOAq\u0001\"\u0010B\u0001\b!y$A\u0001o!\u0019!\t\u0005b\u0011\u000205\u0011\u0011q\\\u0005\u0005\t\u000b\nyNA\u0003O%>|G\u000fC\u0004\u00050\u0005\u0003\r!a\u0015\u0002\tM\f(\u000f\u001e\u000b\t\u0003W!i\u0005b\u0014\u0005R!9\u0011Q\u001a\"A\u0004\u0005=\u0007b\u0002C\u0013\u0005\u0002\u000fAq\u0005\u0005\b\t{\u0011\u00059\u0001C \u0003\r!x\u000e\u001d\u000b\u0005\t/\"\t\u0007\u0006\u0003\u0005Z\u0011}\u0003CBA\u0003\t7\ny#\u0003\u0003\u0005^\u0005\u001d!AB(qi&|g\u000eC\u0004\u0005&\r\u0003\u001da! \t\u000f\u0011\r4\t1\u0001\u00020\u00059Q\r]:jY>t\u0017A\u00022piR|W\u000e\u0006\u0003\u0005j\u00115D\u0003\u0002C-\tWBq\u0001\"\nE\u0001\b\u0019i\bC\u0004\u0005d\u0011\u0003\r!a\f\u0002\t\u0011L7\u000f\u001e\u000b\t\tg\"i\t\"%\u0005\u0016R1AQ\u000fCA\t\u0017\u0003b\u0001b\u001e\u0005~\u0005=RB\u0001C=\u0015\r!Y\b`\u0001\u0007e\u0006tGm\\7\n\t\u0011}D\u0011\u0010\u0002\u0005\t&\u001cH\u000fC\u0004\u0005\u0004\u0016\u0003\u001d\u0001\"\"\u0002\u0003U\u0004b\u0001b\u001e\u0005\b\u0006=\u0012\u0002\u0002CE\ts\u0012q!\u00168jM>\u0014X\u000eC\u0004\u0005&\u0015\u0003\u001da! \t\u000f\u0011=U\t1\u0001\u00020\u0005\u0019Q.\u001b8\t\u000f\u0011MU\t1\u0001\u00020\u0005\u0019Q.\u0019=\t\u000f\u0011\rT\t1\u0001\u00020\u0005IAO]1og2\fG/\u001a\u000b\u0005\t7#\t\u000b\u0006\u0004\u0002,\u0011uEq\u0014\u0005\b\u0003\u001b4\u00059AAh\u0011\u001d\u0011YD\u0012a\u0002\u0007?Dq\u0001b)G\u0001\u0004!)+A\u0001q!\u0019\ti\u0003b*\u00020%\u0019A\u0011\u0016>\u0003\u0015A{G.\u001f8p[&\fG.\u0001\u0004%kJ\u0012\u0004G\u0011\u000b\u0005\t_#\u0019\f\u0006\u0003\u0002J\u0011E\u0006bBAg\u000f\u0002\u000f\u0011q\u001a\u0005\b\u0005';\u0005\u0019AA\u0018\u0003\u0019!SO\r\u001a1\u0007R!A\u0011\u0018C_)\u0011\tI\u0005b/\t\u000f\u00055\u0007\nq\u0001\u0002P\"9!1\u0013%A\u0002\u0005=\u0012\u0001\u0004\u0013veI\u0002\u0004\bJ2pY>tG\u0003\u0002Cb\t\u000f$B!!\u0013\u0005F\"9\u0011QZ%A\u0004\u0005=\u0007b\u0002Ce\u0013\u0002\u0007\u0011qF\u0001\u0002C\u0006aA%\u001e\u001a3ae\"3m\u001c7p]R!Aq\u001aCj)\u0011\tI\u0005\"5\t\u000f\u00055'\nq\u0001\u0002P\"9A\u0011\u001a&A\u0002\u0005=\u0012A\u0002\u0013veI\u0012\u0014\b\u0006\u0003\u0005Z\u0012uG\u0003BA\u0016\t7Dq!!4L\u0001\b\ty\rC\u0004\u0003\u0014.\u0003\r!a\u000b\u0002\r\u0011*(G\r\u001aB)\u0011!\u0019\u000fb:\u0015\t\u0005-BQ\u001d\u0005\b\u0003\u001bd\u00059AAh\u0011\u001d\u0011\u0019\n\u0014a\u0001\u0003W\tq\u0001\n2tY\u0006\u001c\b\u000e\u0006\u0003\u0005n\u0012EH\u0003BB\u0007\t_Dq!!4N\u0001\b\ty\rC\u0004\u0003\u00146\u0003\r!a\u000b\u0002\r\u0011*(G\r\u001d3)\u0011!9\u0010b?\u0015\t\u0005%C\u0011 \u0005\b\u0003\u001bt\u00059AAh\u0011\u001d\u0011\u0019J\u0014a\u0001\u0003W\ta\u0001J;3ea\u001aD\u0003BC\u0001\u000b\u000b!B!!\u0013\u0006\u0004!9\u0011QZ(A\u0004\u0005=\u0007b\u0002BJ\u001f\u0002\u0007\u00111F\u0001\u0007IU\u0014$\u0007\u000f\u001c\u0015\t\u0015-Qq\u0002\u000b\u0005\u0003\u0013*i\u0001C\u0004\u0002NB\u0003\u001d!a4\t\u000f\tM\u0005\u000b1\u0001\u0002,\u00051A%\u001e\u001a3q]\"B!\"\u0006\u0006\u001aQ!\u0011\u0011JC\f\u0011\u001d\ti-\u0015a\u0002\u0003\u001fDqAa%R\u0001\u0004\tY#\u0001\u0005hKR\u001cF/\u0019:u)!)y\"b\t\u0006(\u0015-B\u0003BA\u0018\u000bCAqAa\u000fS\u0001\b\u0011i\u0004C\u0004\u0006&I\u0003\rA!\u0013\u0002\u000b\t|WO\u001c3\t\u000f\u0015%\"\u000b1\u0001\u00020\u0005!1\u000f^3q\u0011\u001d)iC\u0015a\u0001\u0007K\nA\"\u001e8c_VtG-\u0012:s_J\f\u0001\"\u001b;fe\u0006$xN\u001d\u000b\u0005\u000bg)I\u0005\u0006\u0005\u00066\u0015mRQHC !\u0019\t\t\"b\u000e\u00020%!Q\u0011HA\u0013\u0005!IE/\u001a:bi>\u0014\bbBAg'\u0002\u000f\u0011q\u001a\u0005\b\u0005w\u0019\u00069\u0001B\u001f\u0011\u001d)\te\u0015a\u0002\u000b\u0007\n!A\u001c;\u0011\r\u00055RQIA\u0018\u0013\r)9E\u001f\u0002\n\u001dVl'-\u001a:UC\u001eDq!\"\u000bT\u0001\u0004\ty#\u0001\u0003m_>\u0004H\u0003BC(\u000bG\"B!\"\u0015\u0006`QAQ1KC-\u000b7*i\u0006\u0005\u0003\u0002\u0006\u0015U\u0013\u0002BC,\u0003\u000f\u0011A!\u00168ji\"9\u0011Q\u001a+A\u0004\u0005=\u0007b\u0002B\u001e)\u0002\u000f!Q\b\u0005\b\u000b\u0003\"\u00069AC\"\u0011\u001d\u0011y\u0007\u0016a\u0001\u000bC\u0002\u0002\"!\u0002\u0003t\u0005=R1\u000b\u0005\b\u000bS!\u0006\u0019AA\u0018\u0003!1w\u000e\u001c3Pm\u0016\u0014X\u0003BC5\u000bc\"b!b\u001b\u0006~\u0015\u0005E\u0003BC7\u000bs\"\u0002\"b\u001c\u0006t\u0015UTq\u000f\t\u0005\u0003c)\t\bB\u0004\u0003fU\u0013\r!a\u000e\t\u000f\u00055W\u000bq\u0001\u0002P\"9!1H+A\u0004\tu\u0002bBC!+\u0002\u000fQ1\t\u0005\b\u0005_*\u0006\u0019AC>!)\t)A!\"\u0006p\u0005=Rq\u000e\u0005\b\u000b\u007f*\u0006\u0019AC8\u0003\u0011Ig.\u001b;\t\u000f\u0015%R\u000b1\u0001\u00020\u00059qN^3sY\u0006\u0004H\u0003BCD\u000b##B!\"#\u0006\u0010B1!1JCF\u0003_IA!\"$\u0003N\t9qJ^3sY\u0006\u0004\bbBAg-\u0002\u000f\u0011q\u001a\u0005\b\u0005'3\u0006\u0019AA\u0016S5\u0001QQSCM\u000b;+\t+\"*\u0006*&\u0019Qq\u0013>\u0003\u000b\u0005\u0013wN^3\n\u0007\u0015m%PA\u0002BY2L1!b({\u0005\u0015\u0011U\r\\8x\u0013\r)\u0019K\u001f\u0002\b\u0005>,h\u000eZ3e\u0013\r)9K\u001f\u0002\u0006\u000b6\u0004H/_\u0005\u0004\u000bWS(!\u0002)pS:$\u0018\u0001C%oi\u0016\u0014h/\u00197\u0011\u0007\u00055\u0002lE\u0003Y\u0003\u0007)\u0019\f\u0005\u0003\u00066\u0016}VBAC\\\u0015\u0011)I,b/\u0002\u0005%|'BAC_\u0003\u0011Q\u0017M^1\n\t\u0005\rRq\u0017\u000b\u0003\u000b_\u000b\u0011b^5uQ\u001ac\u0017mZ:\u0016\t\u0015\u001dWq\u001a\u000b\t\u000b\u0013,9.b7\u0006`R!Q1ZCi!\u0015\ti\u0003ACg!\u0011\t\t$b4\u0005\u000f\u0005U\"L1\u0001\u00028!IQ1\u001b.\u0002\u0002\u0003\u000fQQ[\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBAi\u0003C,i\rC\u0004\u0006Zj\u0003\r!\"4\u0002\u000b1|w/\u001a:\t\u000f\u0015u'\f1\u0001\u0006N\u0006)Q\u000f\u001d9fe\"9\u0011\u0011\u000b.A\u0002\u0005M\u0013!B3naRLX\u0003BCs\u000bW$B!b:\u0006nB)\u0011Q\u0006\u0001\u0006jB!\u0011\u0011GCv\t\u001d\t)d\u0017b\u0001\u0003oA\u0011\"b<\\\u0003\u0003\u0005\u001d!\"=\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$3\u0007\u0005\u0004\u0002R\u0006\u0005X\u0011^\u0001\u0006a>Lg\u000e^\u000b\u0005\u000bo,y\u0010\u0006\u0003\u0006z\u001a\u001dA\u0003BC~\r\u0003\u0001R!!\f\u0001\u000b{\u0004B!!\r\u0006\u0000\u00129\u0011Q\u0007/C\u0002\u0005]\u0002\"\u0003D\u00029\u0006\u0005\t9\u0001D\u0003\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0003#\f\t/\"@\t\u000f\u0011%G\f1\u0001\u0006~\u0006!!0\u001a:p+\u00111iAb\u0005\u0015\r\u0019=aQ\u0003D\u000e!\u0015\ti\u0003\u0001D\t!\u0011\t\tDb\u0005\u0005\u000f\u0005URL1\u0001\u00028!IaqC/\u0002\u0002\u0003\u000fa\u0011D\u0001\u000bKZLG-\u001a8dK\u0012*\u0004CBAi\u0003C4\t\u0002C\u0004\u0005&u\u0003\u001dA\"\b\u0011\r\u0005E7\u0011\u001bD\t\u0003\r\tG\u000e\\\u000b\u0005\rG1I\u0003\u0006\u0003\u0007&\u0019-\u0002#BA\u0017\u0001\u0019\u001d\u0002\u0003BA\u0019\rS!q!!\u000e_\u0005\u0004\t9\u0004C\u0005\u0007.y\u000b\t\u0011q\u0001\u00070\u0005QQM^5eK:\u001cW\r\n\u001c\u0011\r\u0005E\u0017\u0011\u001dD\u0014\u0003\u0015\t\u0007\u000f\u001d7z+\u00111)D\"\u0010\u0015\r\u0019]bQ\tD$)\u00111IDb\u0010\u0011\u000b\u00055\u0002Ab\u000f\u0011\t\u0005EbQ\b\u0003\b\u0003ky&\u0019AA\u001c\u0011%1\teXA\u0001\u0002\b1\u0019%\u0001\u0006fm&$WM\\2fI]\u0002b!!5\u0002b\u001am\u0002bBCm?\u0002\u0007a1\b\u0005\b\u000b;|\u0006\u0019\u0001D\u001e\u0003-)'O]8s\u0005>,h\u000eZ:\u0015\t\u00195cQ\u000b\t\u0006\u0003[\u0001aq\n\t\u0005\u0003[1\t&C\u0002\u0007Ti\u0014\u0001BU1uS>t\u0017\r\u001c\u0005\b\r/\u0002\u0007\u0019\u0001D-\u0003\u0005!\u0007\u0003BA\u0003\r7JAA\"\u0018\u0002\b\t1Ai\\;cY\u0016\f\u0001c\u00197pg\u0016$Gj\\<fe\u001ac\u0017mZ:\u0016\u0005\u0005M\u0003fA1\u0002\\\u0005qq\u000e]3o\u0019><XM\u001d$mC\u001e\u001c\bf\u00012\u0002\\\u0005\u00012\r\\8tK\u0012,\u0006\u000f]3s\r2\fwm\u001d\u0015\u0004G\u0006m\u0013AD8qK:,\u0006\u000f]3s\r2\fwm\u001d\u0015\u0004I\u0006m\u0013!\u00054s_6|%\u000fZ3sK\u0012\u0014u.\u001e8egV!aQ\u000fD?)\u001919H\"\"\u0007\nR!a\u0011\u0010D@!\u0015\ti\u0003\u0001D>!\u0011\t\tD\" \u0005\u000f\u0005URM1\u0001\u00028!Ia\u0011Q3\u0002\u0002\u0003\u000fa1Q\u0001\u000bKZLG-\u001a8dK\u0012B\u0004CBAi\u0003C4Y\bC\u0004\u0006Z\u0016\u0004\rAb\"\u0011\r\t-#\u0011\u000bD>\u0011\u001d)i.\u001aa\u0001\r\u000f\u000b!B\u001a:p[\n{WO\u001c3t+\u00111yIb&\u0015\r\u0019Eeq\u0014DR)\u00111\u0019J\"'\u0011\u000b\u00055\u0002A\"&\u0011\t\u0005Ebq\u0013\u0003\b\u0003k1'\u0019AA\u001c\u0011%1YJZA\u0001\u0002\b1i*\u0001\u0006fm&$WM\\2fIe\u0002b!!5\u0002b\u001aU\u0005bBCmM\u0002\u0007a\u0011\u0015\t\u0007\u0005\u0017\u0012\tF\"&\t\u000f\u0015ug\r1\u0001\u0007\"\u000611\r\\8tK\u0012,BA\"+\u00072R1a1\u0016D]\rw#BA\",\u00074B)\u0011Q\u0006\u0001\u00070B!\u0011\u0011\u0007DY\t\u001d\t)d\u001ab\u0001\u0003oA\u0011B\".h\u0003\u0003\u0005\u001dAb.\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007\r\t\u0007\u0003#\f\tOb,\t\u000f\u0015ew\r1\u0001\u00070\"9QQ\\4A\u0002\u0019=\u0016\u0001B8qK:,BA\"1\u0007JR1a1\u0019Di\r'$BA\"2\u0007LB)\u0011Q\u0006\u0001\u0007HB!\u0011\u0011\u0007De\t\u001d\t)\u0004\u001bb\u0001\u0003oA\u0011B\"4i\u0003\u0003\u0005\u001dAb4\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0007\u0003#\f\tOb2\t\u000f\u0015e\u0007\u000e1\u0001\u0007H\"9QQ\u001c5A\u0002\u0019\u001d\u0017!C8qK:dun^3s+\u00111IN\"9\u0015\r\u0019mg\u0011\u001eDv)\u00111iNb9\u0011\u000b\u00055\u0002Ab8\u0011\t\u0005Eb\u0011\u001d\u0003\b\u0003kI'\u0019AA\u001c\u0011%1)/[A\u0001\u0002\b19/A\u0006fm&$WM\\2fIE\u0012\u0004CBAi\u0003C4y\u000eC\u0004\u0006Z&\u0004\rAb8\t\u000f\u0015u\u0017\u000e1\u0001\u0007`\u0006Iq\u000e]3o+B\u0004XM]\u000b\u0005\rc4I\u0010\u0006\u0004\u0007t\u001e\u0005q1\u0001\u000b\u0005\rk4Y\u0010E\u0003\u0002.\u000119\u0010\u0005\u0003\u00022\u0019eHaBA\u001bU\n\u0007\u0011q\u0007\u0005\n\r{T\u0017\u0011!a\u0002\r\u007f\f1\"\u001a<jI\u0016t7-\u001a\u00132gA1\u0011\u0011[Aq\roDq!\"7k\u0001\u000419\u0010C\u0004\u0006^*\u0004\rAb>\u0002\u000b\u0005\u0014wN^3\u0016\t\u001d%q\u0011\u0003\u000b\u0005\u000f\u00179I\u0002\u0006\u0003\b\u000e\u001dM\u0001#BA\u0017\u0001\u001d=\u0001\u0003BA\u0019\u000f#!q!!\u000el\u0005\u0004\t9\u0004C\u0005\b\u0016-\f\t\u0011q\u0001\b\u0018\u0005YQM^5eK:\u001cW\rJ\u00195!\u0019\t\t.!9\b\u0010!9A\u0011Z6A\u0002\u001d=\u0011!\u00022fY><X\u0003BD\u0010\u000fO!Ba\"\t\b0Q!q1ED\u0015!\u0015\ti\u0003AD\u0013!\u0011\t\tdb\n\u0005\u000f\u0005UBN1\u0001\u00028!Iq1\u00067\u0002\u0002\u0003\u000fqQF\u0001\fKZLG-\u001a8dK\u0012\nT\u0007\u0005\u0004\u0002R\u0006\u0005xQ\u0005\u0005\b\t\u0013d\u0007\u0019AD\u0013\u0003%\tGo\u0014:BE>4X-\u0006\u0003\b6\u001duB\u0003BD\u001c\u000f\u000b\"Ba\"\u000f\b@A)\u0011Q\u0006\u0001\b<A!\u0011\u0011GD\u001f\t\u001d\t)$\u001cb\u0001\u0003oA\u0011b\"\u0011n\u0003\u0003\u0005\u001dab\u0011\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\u000e\t\u0007\u0003#\f\tob\u000f\t\u000f\u0011%W\u000e1\u0001\b<\u0005I\u0011\r^(s\u0005\u0016dwn^\u000b\u0005\u000f\u0017:\u0019\u0006\u0006\u0003\bN\u001dmC\u0003BD(\u000f+\u0002R!!\f\u0001\u000f#\u0002B!!\r\bT\u00119\u0011Q\u00078C\u0002\u0005]\u0002\"CD,]\u0006\u0005\t9AD-\u0003-)g/\u001b3f]\u000e,G%M\u001c\u0011\r\u0005E\u0017\u0011]D)\u0011\u001d!IM\u001ca\u0001\u000f#\naAT;mYJ+WCAD1!\u00119\u0019g\"\u001c\u000e\u0005\u001d\u0015$\u0002BD4\u000fS\n\u0001\"\\1uG\"Lgn\u001a\u0006\u0005\u000fW\n9!\u0001\u0003vi&d\u0017\u0002BD8\u000fK\u0012QAU3hKb\fqAT;mYJ+\u0007%\u0001\u0005TS:<G.\u001a*f\u0003%\u0019\u0016N\\4mKJ+\u0007%\u0001\u0004QC&\u0014(+Z\u0001\b!\u0006L'OU3!)\u00111ie\" \t\u000f\u001d}T\u000f1\u0001\u0004f\u0005\t1/\u0001\u0002fcV!qQQDG)\u001199ib$\u0011\r\u0005E'Q]DE!\u0015\ti\u0003ADF!\u0011\t\td\"$\u0005\u000f\u0005UbO1\u0001\u00028!Iq\u0011\u0013<\u0002\u0002\u0003\u000fq1S\u0001\fKZLG-\u001a8dK\u0012\n\u0004\b\u0005\u0004\u0002R\n\u0015x1R\u0001\tg\u0016l\u0017N]5oOV!q\u0011TDQ)\u00199Yjb)\b(B1\u0011\u0011[Bi\u000f;\u0003R!!\f\u0001\u000f?\u0003B!!\r\b\"\u00129\u0011QG<C\u0002\u0005]\u0002b\u0002B\u001eo\u0002\u000fqQ\u0015\t\u0007\u0003#$Icb(\t\u000f\u00055w\u000fq\u0001\b*B1\u0011\u0011[Aq\u000f?\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"ab,\u0011\t\u001dEvqW\u0007\u0003\u000fgSAa\".\u0006<\u0006!A.\u00198h\u0013\u00119Ilb-\u0003\r=\u0013'.Z2u\u0001"
)
public abstract class Interval implements Serializable {
   public static Semiring semiring(final Ring ev, final Order o) {
      return Interval$.MODULE$.semiring(ev, o);
   }

   public static Interval apply(final String s) {
      return Interval$.MODULE$.apply(s);
   }

   public static Interval atOrBelow(final Object a, final Order evidence$17) {
      return Interval$.MODULE$.atOrBelow(a, evidence$17);
   }

   public static Interval atOrAbove(final Object a, final Order evidence$16) {
      return Interval$.MODULE$.atOrAbove(a, evidence$16);
   }

   public static Interval below(final Object a, final Order evidence$15) {
      return Interval$.MODULE$.below(a, evidence$15);
   }

   public static Interval above(final Object a, final Order evidence$14) {
      return Interval$.MODULE$.above(a, evidence$14);
   }

   public static Interval openUpper(final Object lower, final Object upper, final Order evidence$13) {
      return Interval$.MODULE$.openUpper(lower, upper, evidence$13);
   }

   public static Interval openLower(final Object lower, final Object upper, final Order evidence$12) {
      return Interval$.MODULE$.openLower(lower, upper, evidence$12);
   }

   public static Interval open(final Object lower, final Object upper, final Order evidence$11) {
      return Interval$.MODULE$.open(lower, upper, evidence$11);
   }

   public static Interval closed(final Object lower, final Object upper, final Order evidence$10) {
      return Interval$.MODULE$.closed(lower, upper, evidence$10);
   }

   public static Interval fromBounds(final Bound lower, final Bound upper, final Order evidence$9) {
      return Interval$.MODULE$.fromBounds(lower, upper, evidence$9);
   }

   public static Interval errorBounds(final double d) {
      return Interval$.MODULE$.errorBounds(d);
   }

   public static Interval apply(final Object lower, final Object upper, final Order evidence$7) {
      return Interval$.MODULE$.apply(lower, upper, evidence$7);
   }

   public static Interval all(final Order evidence$6) {
      return Interval$.MODULE$.all(evidence$6);
   }

   public static Interval zero(final Order evidence$5, final Semiring r) {
      return Interval$.MODULE$.zero(evidence$5, r);
   }

   public static Interval point(final Object a, final Order evidence$4) {
      return Interval$.MODULE$.point(a, evidence$4);
   }

   public static Interval empty(final Order evidence$3) {
      return Interval$.MODULE$.empty(evidence$3);
   }

   public final boolean isClosed(final int flags) {
      return flags == 0;
   }

   public final boolean isClosedLower(final int flags) {
      return (flags & 1) == 0;
   }

   public final boolean isClosedUpper(final int flags) {
      return (flags & 2) == 0;
   }

   public final boolean isOpen(final int flags) {
      return flags == 3;
   }

   public final boolean isOpenLower(final int flags) {
      return (flags & 1) == 1;
   }

   public final boolean isOpenUpper(final int flags) {
      return (flags & 2) == 2;
   }

   public final int lowerFlag(final int flags) {
      return flags & 1;
   }

   public final int upperFlag(final int flags) {
      return flags & 2;
   }

   public final int reverseLowerFlag(final int flags) {
      return flags ^ 1;
   }

   public final int reverseUpperFlag(final int flags) {
      return flags ^ 2;
   }

   public final int reverseFlags(final int flags) {
      return flags ^ 3;
   }

   public final int lowerFlagToUpper(final int flags) {
      return (flags & 1) << 1;
   }

   public final int upperFlagToLower(final int flags) {
      return (flags & 2) >>> 1;
   }

   public final int swapFlags(final int flags) {
      return (flags & 1) << 1 | (flags & 2) >>> 1;
   }

   public boolean lowerPairBelow(final Object lower1, final int flags1, final Object lower2, final int flags2, final Order o) {
      return o.lt(lower1, lower2) || o.eqv(lower1, lower2) && (this.isClosedLower(flags1) || this.isOpenLower(flags2));
   }

   public boolean upperPairAbove(final Object upper1, final int flags1, final Object upper2, final int flags2, final Order o) {
      return o.gt(upper1, upper2) || o.eqv(upper1, upper2) && (this.isClosedUpper(flags1) || this.isOpenUpper(flags2));
   }

   public boolean isEmpty() {
      return this instanceof Empty;
   }

   public boolean nonEmpty() {
      return !this.isEmpty();
   }

   public boolean isPoint() {
      return this instanceof Point;
   }

   public boolean contains(final Object t, final Order o) {
      return this.hasAtOrBelow(t, o) && this.hasAtOrAbove(t, o);
   }

   public boolean doesNotContain(final Object t, final Order o) {
      return !this.hasAtOrBelow(t, o) || !this.hasAtOrAbove(t, o);
   }

   public boolean crosses(final Object t, final Order o) {
      return this.hasBelow(t, o) && this.hasAbove(t, o);
   }

   public boolean crossesZero(final Order o, final AdditiveMonoid ev) {
      return this.hasBelow(ev.zero(), o) && this.hasAbove(ev.zero(), o);
   }

   public boolean isBounded() {
      boolean var2;
      if (this instanceof Below) {
         var2 = true;
      } else if (this instanceof Above) {
         var2 = true;
      } else if (this instanceof All) {
         var2 = true;
      } else {
         var2 = false;
      }

      boolean var1;
      if (var2) {
         var1 = false;
      } else {
         var1 = true;
      }

      return var1;
   }

   public abstract Bound lowerBound();

   public abstract Bound upperBound();

   public Interval mapBounds(final Function1 f, final Order evidence$1) {
      return Interval$.MODULE$.fromBounds(this.lowerBound().map(f), this.upperBound().map(f), evidence$1);
   }

   public Object fold(final Function2 f) {
      return f.apply(this.lowerBound(), this.upperBound());
   }

   public boolean isSupersetOf(final Interval rhs, final Order o) {
      Tuple2 var4 = new Tuple2(this, rhs);
      boolean var3;
      if (var4 != null) {
         Interval var5 = (Interval)var4._1();
         if (var5 instanceof All) {
            var3 = true;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var6 = (Interval)var4._2();
         if (var6 instanceof All) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var7 = (Interval)var4._2();
         if (var7 instanceof Empty) {
            var3 = true;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var8 = (Interval)var4._1();
         if (var8 instanceof Empty) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var9 = (Interval)var4._1();
         Interval var10 = (Interval)var4._2();
         if (var9 instanceof Point) {
            Point var11 = (Point)var9;
            Object lhsval = var11.value();
            if (var10 instanceof Point) {
               Point var13 = (Point)var10;
               Object rhsval = var13.value();
               var3 = o.eqv(lhsval, rhsval);
               return var3;
            }
         }
      }

      if (var4 != null) {
         Interval var15 = (Interval)var4._1();
         if (var15 instanceof Point) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var16 = (Interval)var4._2();
         if (var16 instanceof Point) {
            Point var17 = (Point)var16;
            Object rhsval = var17.value();
            var3 = this.contains(rhsval, o);
            return var3;
         }
      }

      if (var4 != null) {
         Interval var19 = (Interval)var4._1();
         Interval var20 = (Interval)var4._2();
         if (var19 instanceof Above && var20 instanceof Below) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var21 = (Interval)var4._1();
         Interval var22 = (Interval)var4._2();
         if (var21 instanceof Below && var22 instanceof Above) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var23 = (Interval)var4._1();
         Interval var24 = (Interval)var4._2();
         if (var23 instanceof Bounded && var24 instanceof Below) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var25 = (Interval)var4._1();
         Interval var26 = (Interval)var4._2();
         if (var25 instanceof Bounded && var26 instanceof Above) {
            var3 = false;
            return var3;
         }
      }

      if (var4 != null) {
         Interval var27 = (Interval)var4._1();
         Interval var28 = (Interval)var4._2();
         if (var27 instanceof Above) {
            Above var29 = (Above)var27;
            Object lower1 = var29.lower();
            int flags1 = var29.flags();
            if (var28 instanceof Bounded) {
               Bounded var32 = (Bounded)var28;
               Object lower2 = var32.lower();
               int flags2 = var32.flags();
               var3 = this.lowerPairBelow(lower1, flags1, lower2, flags2, o);
               return var3;
            }
         }
      }

      if (var4 != null) {
         Interval var35 = (Interval)var4._1();
         Interval var36 = (Interval)var4._2();
         if (var35 instanceof Above) {
            Above var37 = (Above)var35;
            Object lower1 = var37.lower();
            int flags1 = var37.flags();
            if (var36 instanceof Above) {
               Above var40 = (Above)var36;
               Object lower2 = var40.lower();
               int flags2 = var40.flags();
               var3 = this.lowerPairBelow(lower1, flags1, lower2, flags2, o);
               return var3;
            }
         }
      }

      if (var4 != null) {
         Interval var43 = (Interval)var4._1();
         Interval var44 = (Interval)var4._2();
         if (var43 instanceof Below) {
            Below var45 = (Below)var43;
            Object upper1 = var45.upper();
            int flags1 = var45.flags();
            if (var44 instanceof Below) {
               Below var48 = (Below)var44;
               Object upper2 = var48.upper();
               int flags2 = var48.flags();
               var3 = this.upperPairAbove(upper1, flags1, upper2, flags2, o);
               return var3;
            }
         }
      }

      if (var4 != null) {
         Interval var51 = (Interval)var4._1();
         Interval var52 = (Interval)var4._2();
         if (var51 instanceof Below) {
            Below var53 = (Below)var51;
            Object upper1 = var53.upper();
            int flags1 = var53.flags();
            if (var52 instanceof Bounded) {
               Bounded var56 = (Bounded)var52;
               Object upper2 = var56.upper();
               int flags2 = var56.flags();
               var3 = this.upperPairAbove(upper1, flags1, upper2, flags2, o);
               return var3;
            }
         }
      }

      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         Interval var59 = (Interval)var4._1();
         Interval var60 = (Interval)var4._2();
         if (!(var59 instanceof Bounded)) {
            throw new MatchError(var4);
         } else {
            Bounded var61 = (Bounded)var59;
            Object lower1 = var61.lower();
            Object upper1 = var61.upper();
            int flags1 = var61.flags();
            if (!(var60 instanceof Bounded)) {
               throw new MatchError(var4);
            } else {
               Bounded var65 = (Bounded)var60;
               Object lower2 = var65.lower();
               Object upper2 = var65.upper();
               int flags2 = var65.flags();
               var3 = this.lowerPairBelow(lower1, flags1, lower2, flags2, o) && this.upperPairAbove(upper1, flags1, upper2, flags2, o);
               return var3;
            }
         }
      }
   }

   public boolean isProperSupersetOf(final Interval rhs, final Order o) {
      boolean var10000;
      label18: {
         if (this == null) {
            if (rhs == null) {
               break label18;
            }
         } else if (this.equals(rhs)) {
            break label18;
         }

         if (this.isSupersetOf(rhs, o)) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public boolean isSubsetOf(final Interval rhs, final Order o) {
      return rhs.isSupersetOf(this, o);
   }

   public boolean isProperSubsetOf(final Interval rhs, final Order o) {
      return rhs.isProperSupersetOf(this, o);
   }

   public boolean hasAbove(final Object t, final Order o) {
      boolean var3;
      if (this instanceof Empty) {
         var3 = false;
      } else if (this instanceof Point) {
         Point var5 = (Point)this;
         Object p = var5.value();
         var3 = o.gt(p, t);
      } else if (this instanceof Below) {
         Below var7 = (Below)this;
         Object upper = var7.upper();
         var3 = o.gt(upper, t);
      } else if (this instanceof Bounded) {
         Bounded var9 = (Bounded)this;
         Object upper = var9.upper();
         var3 = o.gt(upper, t);
      } else if (this instanceof All) {
         var3 = true;
      } else {
         if (!(this instanceof Above)) {
            throw new MatchError(this);
         }

         var3 = true;
      }

      return var3;
   }

   public boolean hasBelow(final Object t, final Order o) {
      boolean var3;
      if (this instanceof Empty) {
         var3 = false;
      } else if (this instanceof Point) {
         Point var5 = (Point)this;
         Object p = var5.value();
         var3 = o.lt(p, t);
      } else if (this instanceof Above) {
         Above var7 = (Above)this;
         Object lower = var7.lower();
         var3 = o.lt(lower, t);
      } else if (this instanceof Bounded) {
         Bounded var9 = (Bounded)this;
         Object lower = var9.lower();
         var3 = o.lt(lower, t);
      } else if (this instanceof Below) {
         var3 = true;
      } else {
         if (!(this instanceof All)) {
            throw new MatchError(this);
         }

         var3 = true;
      }

      return var3;
   }

   public boolean hasAtOrAbove(final Object t, final Order o) {
      boolean var3;
      if (this instanceof Empty) {
         var3 = false;
      } else if (this instanceof Point) {
         Point var5 = (Point)this;
         Object p = var5.value();
         var3 = o.gteqv(p, t);
      } else if (this instanceof Below) {
         Below var7 = (Below)this;
         Object upper = var7.upper();
         int flags = var7.flags();
         var3 = o.gt(upper, t) || this.isClosedUpper(flags) && o.eqv(upper, t);
      } else if (this instanceof Bounded) {
         Bounded var10 = (Bounded)this;
         Object upper = var10.upper();
         int flags = var10.flags();
         var3 = o.gt(upper, t) || this.isClosedUpper(flags) && o.eqv(upper, t);
      } else if (this instanceof Above) {
         var3 = true;
      } else {
         if (!(this instanceof All)) {
            throw new MatchError(this);
         }

         var3 = true;
      }

      return var3;
   }

   public boolean hasAtOrBelow(final Object t, final Order o) {
      boolean var3;
      if (this instanceof Empty) {
         var3 = false;
      } else if (this instanceof Point) {
         Point var5 = (Point)this;
         Object p = var5.value();
         var3 = o.lteqv(p, t);
      } else if (this instanceof Above) {
         Above var7 = (Above)this;
         Object lower = var7.lower();
         int flags = var7.flags();
         var3 = o.lt(lower, t) || this.isClosedLower(flags) && o.eqv(lower, t);
      } else if (this instanceof Bounded) {
         Bounded var10 = (Bounded)this;
         Object lower = var10.lower();
         int flags = var10.flags();
         var3 = o.lt(lower, t) || this.isClosedLower(flags) && o.eqv(lower, t);
      } else if (this instanceof Below) {
         var3 = true;
      } else {
         if (!(this instanceof All)) {
            throw new MatchError(this);
         }

         var3 = true;
      }

      return var3;
   }

   public boolean isAt(final Object t, final Eq o) {
      boolean var3;
      if (this instanceof Point) {
         Point var5 = (Point)this;
         Object p = var5.value();
         var3 = o.eqv(t, p);
      } else {
         var3 = false;
      }

      return var3;
   }

   public boolean intersects(final Interval rhs, final Order o) {
      return !this.intersect(rhs, o).isEmpty();
   }

   public Interval $amp(final Interval rhs, final Order o) {
      return this.intersect(rhs, o);
   }

   public Interval intersect(final Interval rhs, final Order o) {
      return Interval$.MODULE$.fromBounds(Bound$.MODULE$.maxLower(this.lowerBound(), rhs.lowerBound(), true, o), Bound$.MODULE$.minUpper(this.upperBound(), rhs.upperBound(), true, o), o);
   }

   public List unary_$tilde(final Order o) {
      Object var2;
      if (this instanceof All) {
         var2 = .MODULE$.Nil();
      } else if (this instanceof Empty) {
         var2 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new All[]{All$.MODULE$.apply()}));
      } else if (this instanceof Above) {
         Above var4 = (Above)this;
         Object lower = var4.lower();
         int lf = var4.flags();
         var2 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Below[]{Below$.MODULE$.apply(lower, this.lowerFlagToUpper(this.reverseLowerFlag(lf)))}));
      } else if (this instanceof Below) {
         Below var7 = (Below)this;
         Object upper = var7.upper();
         int uf = var7.flags();
         var2 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Above[]{Above$.MODULE$.apply(upper, this.upperFlagToLower(this.reverseUpperFlag(uf)))}));
      } else if (this instanceof Point) {
         Point var10 = (Point)this;
         Object p = var10.value();
         var2 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Interval[]{Interval$.MODULE$.below(p, o), Interval$.MODULE$.above(p, o)}));
      } else {
         if (!(this instanceof Bounded)) {
            throw new MatchError(this);
         }

         Bounded var12 = (Bounded)this;
         Object lower = var12.lower();
         Object upper = var12.upper();
         int flags = var12.flags();
         int lx = this.lowerFlagToUpper(this.reverseLowerFlag(this.lowerFlag(flags)));
         int ux = this.upperFlagToLower(this.reverseUpperFlag(this.upperFlag(flags)));
         var2 = (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Interval[]{Below$.MODULE$.apply(lower, lx), Above$.MODULE$.apply(upper, ux)}));
      }

      return (List)var2;
   }

   public List $minus$minus(final Interval rhs, final Order o) {
      return (List)(this.intersects(rhs, o) ? rhs.unary_$tilde(o).map((x$1) -> this.$amp(x$1, o)).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$$minus$minus$2(x$2))) : (this.isEmpty() ? .MODULE$.Nil() : (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Interval[]{this}))));
   }

   public Tuple2 split(final Object t, final Order o) {
      return new Tuple2(this.intersect(Interval$.MODULE$.below(t, o), o), this.intersect(Interval$.MODULE$.above(t, o), o));
   }

   public Tuple2 splitAtZero(final Order o, final AdditiveMonoid ev) {
      return this.split(ev.zero(), o);
   }

   public Tuple2 mapAroundZero(final Function1 f, final Order o, final AdditiveMonoid ev) {
      Tuple2 var5 = this.splitAtZero(o, ev);
      if (var5 != null) {
         Interval a = (Interval)var5._1();
         Interval b = (Interval)var5._2();
         Tuple2 var4 = new Tuple2(f.apply(a), f.apply(b));
         return var4;
      } else {
         throw new MatchError(var5);
      }
   }

   public Interval $bar(final Interval rhs, final Order o) {
      return this.union(rhs, o);
   }

   public Interval union(final Interval rhs, final Order o) {
      return Interval$.MODULE$.fromBounds(Bound$.MODULE$.minLower(this.lowerBound(), rhs.lowerBound(), false, o), Bound$.MODULE$.maxUpper(this.upperBound(), rhs.upperBound(), false, o), o);
   }

   public String toString() {
      String var1;
      if (this instanceof All) {
         var1 = "(-∞, ∞)";
      } else if (this instanceof Empty) {
         var1 = "(Ø)";
      } else if (this instanceof Above) {
         Above var3 = (Above)this;
         Object lower = var3.lower();
         int flags = var3.flags();
         var1 = this.isClosedLower(flags) ? (new StringBuilder(5)).append("[").append(lower).append(", ∞)").toString() : (new StringBuilder(5)).append("(").append(lower).append(", ∞)").toString();
      } else if (this instanceof Below) {
         Below var6 = (Below)this;
         Object upper = var6.upper();
         int flags = var6.flags();
         var1 = this.isClosedUpper(flags) ? (new StringBuilder(6)).append("(-∞, ").append(upper).append("]").toString() : (new StringBuilder(6)).append("(-∞, ").append(upper).append(")").toString();
      } else if (this instanceof Point) {
         Point var9 = (Point)this;
         Object p = var9.value();
         var1 = (new StringBuilder(2)).append("[").append(p).append("]").toString();
      } else {
         if (!(this instanceof Bounded)) {
            throw new MatchError(this);
         }

         Bounded var11 = (Bounded)this;
         Object lower = var11.lower();
         Object upper = var11.upper();
         int flags = var11.flags();
         String s1 = this.isClosedLower(flags) ? (new StringBuilder(1)).append("[").append(lower).toString() : (new StringBuilder(1)).append("(").append(lower).toString();
         String s2 = this.isClosedUpper(flags) ? (new StringBuilder(1)).append(upper).append("]").toString() : (new StringBuilder(1)).append(upper).append(")").toString();
         var1 = (new StringBuilder(2)).append(s1).append(", ").append(s2).toString();
      }

      return var1;
   }

   public Interval abs(final Order o, final AdditiveGroup m) {
      Object var10000;
      if (this.crossesZero(o, m)) {
         Object var3;
         if (this instanceof Bounded) {
            Bounded var5 = (Bounded)this;
            Object lower = var5.lower();
            Object upper = var5.upper();
            int fs = var5.flags();
            Object x = m.negate(lower);
            var3 = o.gt(x, upper) ? Bounded$.MODULE$.apply(m.zero(), x, this.lowerFlagToUpper(fs)) : (o.gt(upper, x) ? Bounded$.MODULE$.apply(m.zero(), upper, this.upperFlag(fs)) : Bounded$.MODULE$.apply(m.zero(), x, this.lowerFlagToUpper(fs) & this.upperFlag(fs)));
         } else {
            var3 = Interval$.MODULE$.atOrAbove(m.zero(), o);
         }

         var10000 = var3;
      } else {
         var10000 = this.hasBelow(m.zero(), o) ? this.unary_$minus(m) : this;
      }

      return (Interval)var10000;
   }

   public Interval vmin(final Interval rhs, final Order o) {
      return Interval$.MODULE$.fromBounds(Bound$.MODULE$.minLower(this.lowerBound(), rhs.lowerBound(), true, o), Bound$.MODULE$.minUpper(this.upperBound(), rhs.upperBound(), true, o), o);
   }

   public Interval vmax(final Interval rhs, final Order o) {
      return Interval$.MODULE$.fromBounds(Bound$.MODULE$.maxLower(this.lowerBound(), rhs.lowerBound(), true, o), Bound$.MODULE$.maxUpper(this.upperBound(), rhs.upperBound(), true, o), o);
   }

   public Interval combine(final Interval rhs, final Function2 f, final Order o) {
      Bound lb = this.lowerBound().combine(rhs.lowerBound(), f);
      Bound ub = this.upperBound().combine(rhs.upperBound(), f);
      return Interval$.MODULE$.fromBounds(lb, ub, o);
   }

   public Interval $plus(final Interval rhs, final Order o, final AdditiveSemigroup ev) {
      return this.combine(rhs, (x$3, x$4) -> ev.plus(x$3, x$4), o);
   }

   public Interval $minus(final Interval rhs, final Order o, final AdditiveGroup ev) {
      return this.$plus(rhs.unary_$minus(ev), o, ev);
   }

   public Interval $times(final Interval rhs, final Order o, final Semiring ev) {
      Object z = ev.zero();
      Tuple2 var6 = new Tuple2(this, rhs);
      Interval var4;
      if (var6 != null) {
         Interval var7 = (Interval)var6._1();
         if (var7 instanceof Empty) {
            var4 = this;
            return var4;
         }
      }

      if (var6 != null) {
         Interval var8 = (Interval)var6._2();
         if (var8 instanceof Empty) {
            var4 = rhs;
            return var4;
         }
      }

      if (var6 != null) {
         Interval var9 = (Interval)var6._1();
         if (var9 instanceof Point) {
            Point var10 = (Point)var9;
            Object lv = var10.value();
            var4 = rhs.$times(lv, o, ev);
            return var4;
         }
      }

      if (var6 != null) {
         Interval var12 = (Interval)var6._2();
         if (var12 instanceof Point) {
            Point var13 = (Point)var12;
            Object rv = var13.value();
            var4 = this.$times(rv, o, ev);
            return var4;
         }
      }

      if (var6 != null) {
         Interval var15 = (Interval)var6._1();
         if (var15 instanceof All) {
            var4 = this;
            return var4;
         }
      }

      if (var6 != null) {
         Interval var16 = (Interval)var6._2();
         if (var16 instanceof All) {
            var4 = rhs;
            return var4;
         }
      }

      if (var6 != null) {
         Interval var17 = (Interval)var6._1();
         Interval var18 = (Interval)var6._2();
         if (var17 instanceof Above) {
            Above var19 = (Above)var17;
            Object lower1 = var19.lower();
            int lf1 = var19.flags();
            if (var18 instanceof Above) {
               Above var22 = (Above)var18;
               Object lower2 = var22.lower();
               int lf2 = var22.flags();
               var4 = this.aboveAbove$1(lower1, lf1, lower2, lf2, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var25 = (Interval)var6._1();
         Interval var26 = (Interval)var6._2();
         if (var25 instanceof Above) {
            Above var27 = (Above)var25;
            Object lower1 = var27.lower();
            int lf1 = var27.flags();
            if (var26 instanceof Below) {
               Below var30 = (Below)var26;
               Object upper2 = var30.upper();
               int uf2 = var30.flags();
               var4 = this.aboveBelow$1(lower1, lf1, upper2, uf2, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var33 = (Interval)var6._1();
         Interval var34 = (Interval)var6._2();
         if (var33 instanceof Below) {
            Below var35 = (Below)var33;
            Object upper1 = var35.upper();
            int uf1 = var35.flags();
            if (var34 instanceof Above) {
               Above var38 = (Above)var34;
               Object lower2 = var38.lower();
               int lf2 = var38.flags();
               var4 = this.aboveBelow$1(lower2, lf2, upper1, uf1, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var41 = (Interval)var6._1();
         Interval var42 = (Interval)var6._2();
         if (var41 instanceof Below) {
            Below var43 = (Below)var41;
            Object upper1 = var43.upper();
            int uf1 = var43.flags();
            if (var42 instanceof Below) {
               Below var46 = (Below)var42;
               Object upper2 = var46.upper();
               int uf2 = var46.flags();
               var4 = this.belowBelow$1(upper1, uf1, upper2, uf2, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var49 = (Interval)var6._1();
         Interval var50 = (Interval)var6._2();
         if (var49 instanceof Above) {
            Above var51 = (Above)var49;
            Object lower1 = var51.lower();
            int lf1 = var51.flags();
            if (var50 instanceof Bounded) {
               Bounded var54 = (Bounded)var50;
               Object lower2 = var54.lower();
               Object upper2 = var54.upper();
               int flags2 = var54.flags();
               var4 = this.aboveBounded$1(lower1, lf1, lower2, upper2, flags2, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var58 = (Interval)var6._1();
         Interval var59 = (Interval)var6._2();
         if (var58 instanceof Bounded) {
            Bounded var60 = (Bounded)var58;
            Object lower1 = var60.lower();
            Object upper1 = var60.upper();
            int flags1 = var60.flags();
            if (var59 instanceof Above) {
               Above var64 = (Above)var59;
               Object lower2 = var64.lower();
               int lf2 = var64.flags();
               var4 = this.aboveBounded$1(lower2, lf2, lower1, upper1, flags1, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var67 = (Interval)var6._1();
         Interval var68 = (Interval)var6._2();
         if (var67 instanceof Below) {
            Below var69 = (Below)var67;
            Object upper1 = var69.upper();
            int uf1 = var69.flags();
            if (var68 instanceof Bounded) {
               Bounded var72 = (Bounded)var68;
               Object lower2 = var72.lower();
               Object upper2 = var72.upper();
               int flags2 = var72.flags();
               var4 = this.belowBounded$1(upper1, uf1, lower2, upper2, flags2, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 != null) {
         Interval var76 = (Interval)var6._1();
         Interval var77 = (Interval)var6._2();
         if (var76 instanceof Bounded) {
            Bounded var78 = (Bounded)var76;
            Object lower1 = var78.lower();
            Object upper1 = var78.upper();
            int flags1 = var78.flags();
            if (var77 instanceof Below) {
               Below var82 = (Below)var77;
               Object upper2 = var82.upper();
               int uf2 = var82.flags();
               var4 = this.belowBounded$1(upper2, uf2, lower1, upper1, flags1, o, z, ev);
               return var4;
            }
         }
      }

      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         Interval bd1 = (Interval)var6._1();
         Interval bd2 = (Interval)var6._2();
         if (!(bd1 instanceof Bounded)) {
            throw new MatchError(var6);
         } else {
            Bounded var87 = (Bounded)bd1;
            if (!(bd2 instanceof Bounded)) {
               throw new MatchError(var6);
            } else {
               Bounded var88 = (Bounded)bd2;
               var4 = boundedBounded$1(var87, var88, o, z, ev);
               return var4;
            }
         }
      }
   }

   public Interval reciprocal(final Order o, final Field ev) {
      Object z = ev.zero();
      if (this instanceof All) {
         throw error$1();
      } else {
         Object var3;
         if (this instanceof Empty) {
            var3 = this;
         } else if (this instanceof Above) {
            Above var9 = (Above)this;
            Object lower = var9.lower();
            int lf = var9.flags();
            Tuple2.mcIZ.sp var12 = new Tuple2.mcIZ.sp(o.compare(lower, z), this.isClosedLower(lf));
            if (var12 != null) {
               int x = ((Tuple2)var12)._1$mcI$sp();
               if (x < 0) {
                  throw error$1();
               }
            }

            if (var12 != null) {
               int var14 = ((Tuple2)var12)._1$mcI$sp();
               boolean var15 = ((Tuple2)var12)._2$mcZ$sp();
               if (0 == var14 && var15) {
                  throw error$1();
               }
            }

            Object var6;
            label139: {
               if (var12 != null) {
                  int var16 = ((Tuple2)var12)._1$mcI$sp();
                  boolean var17 = ((Tuple2)var12)._2$mcZ$sp();
                  if (0 == var16 && !var17) {
                     var6 = this;
                     break label139;
                  }
               }

               var6 = Bounded$.MODULE$.apply(z, ev.reciprocal(lower), 1 | this.lowerFlagToUpper(lf));
            }

            var3 = var6;
         } else if (this instanceof Below) {
            Below var18 = (Below)this;
            Object upper = var18.upper();
            int uf = var18.flags();
            Tuple2.mcIZ.sp var21 = new Tuple2.mcIZ.sp(o.compare(upper, z), this.isClosedUpper(uf));
            if (var21 != null) {
               int x = ((Tuple2)var21)._1$mcI$sp();
               if (x > 0) {
                  throw error$1();
               }
            }

            if (var21 != null) {
               int var23 = ((Tuple2)var21)._1$mcI$sp();
               boolean var24 = ((Tuple2)var21)._2$mcZ$sp();
               if (0 == var23 && var24) {
                  throw error$1();
               }
            }

            Object var5;
            label129: {
               if (var21 != null) {
                  int var25 = ((Tuple2)var21)._1$mcI$sp();
                  boolean var26 = ((Tuple2)var21)._2$mcZ$sp();
                  if (0 == var25 && !var26) {
                     var5 = this;
                     break label129;
                  }
               }

               var5 = Bounded$.MODULE$.apply(ev.reciprocal(upper), z, 2 | this.upperFlagToLower(uf));
            }

            var3 = var5;
         } else if (this instanceof Point) {
            Point var27 = (Point)this;
            Object v = var27.value();
            var3 = Point$.MODULE$.apply(ev.reciprocal(v));
         } else {
            if (!(this instanceof Bounded)) {
               throw new MatchError(this);
            }

            Bounded var29 = (Bounded)this;
            Object lower = var29.lower();
            Object upper = var29.upper();
            int flags = var29.flags();
            Tuple4 var33 = new Tuple4(BoxesRunTime.boxToInteger(o.compare(lower, z)), BoxesRunTime.boxToInteger(o.compare(upper, z)), BoxesRunTime.boxToBoolean(this.isClosedLower(flags)), BoxesRunTime.boxToBoolean(this.isClosedUpper(flags)));
            if (var33 != null) {
               int x = BoxesRunTime.unboxToInt(var33._1());
               int y = BoxesRunTime.unboxToInt(var33._2());
               if (x < 0 && y > 0) {
                  throw error$1();
               }
            }

            if (var33 != null) {
               int var36 = BoxesRunTime.unboxToInt(var33._1());
               boolean var37 = BoxesRunTime.unboxToBoolean(var33._3());
               if (0 == var36 && var37) {
                  throw error$1();
               }
            }

            if (var33 != null) {
               int var38 = BoxesRunTime.unboxToInt(var33._2());
               boolean var39 = BoxesRunTime.unboxToBoolean(var33._4());
               if (0 == var38 && var39) {
                  throw error$1();
               }
            }

            Object var4;
            label152: {
               if (var33 != null) {
                  int var40 = BoxesRunTime.unboxToInt(var33._1());
                  boolean var41 = BoxesRunTime.unboxToBoolean(var33._3());
                  if (0 == var40 && !var41) {
                     var4 = Above$.MODULE$.apply(ev.reciprocal(upper), this.upperFlagToLower(flags));
                     break label152;
                  }
               }

               if (var33 != null) {
                  int var42 = BoxesRunTime.unboxToInt(var33._2());
                  boolean var43 = BoxesRunTime.unboxToBoolean(var33._4());
                  if (0 == var42 && !var43) {
                     var4 = Below$.MODULE$.apply(ev.reciprocal(lower), this.lowerFlagToUpper(flags));
                     break label152;
                  }
               }

               var4 = Bounded$.MODULE$.apply(ev.reciprocal(upper), ev.reciprocal(lower), this.swapFlags(flags));
            }

            var3 = var4;
         }

         return (Interval)var3;
      }
   }

   public Interval $div(final Interval rhs, final Order o, final Field ev) {
      Tuple2 var5 = new Tuple2(this, rhs);
      Interval var4;
      if (var5 != null) {
         Interval var6 = (Interval)var5._1();
         if (var6 instanceof Point) {
            Point var7 = (Point)var6;
            Object lv = var7.value();
            var4 = rhs.reciprocal(o, ev).$times((Object)lv, o, ev);
            return var4;
         }
      }

      if (var5 != null) {
         Interval var9 = (Interval)var5._2();
         if (var9 instanceof Point) {
            Point var10 = (Point)var9;
            Object rv = var10.value();
            var4 = this.$times((Object)ev.reciprocal(rv), o, ev);
            return var4;
         }
      }

      if (var5 == null) {
         throw new MatchError(var5);
      } else {
         var4 = this.$times((Interval)rhs.reciprocal(o, ev), o, ev);
         return var4;
      }
   }

   public Interval $div(final Object rhs, final Order o, final Field ev) {
      return this.$times((Object)ev.reciprocal(rhs), o, ev);
   }

   public Interval $plus(final Object rhs, final AdditiveSemigroup ev) {
      Object var3;
      if (this instanceof Point) {
         Point var6 = (Point)this;
         Object v = var6.value();
         var3 = Point$.MODULE$.apply(ev.plus(v, rhs));
      } else if (this instanceof Bounded) {
         Bounded var8 = (Bounded)this;
         Object l = var8.lower();
         Object u = var8.upper();
         int flags = var8.flags();
         var3 = Bounded$.MODULE$.apply(ev.plus(l, rhs), ev.plus(u, rhs), flags);
      } else if (this instanceof Above) {
         Above var12 = (Above)this;
         Object l = var12.lower();
         int lf = var12.flags();
         var3 = Above$.MODULE$.apply(ev.plus(l, rhs), lf);
      } else if (this instanceof Below) {
         Below var15 = (Below)this;
         Object u = var15.upper();
         int uf = var15.flags();
         var3 = Below$.MODULE$.apply(ev.plus(u, rhs), uf);
      } else {
         boolean var4;
         if (this instanceof All) {
            var4 = true;
         } else if (this instanceof Empty) {
            var4 = true;
         } else {
            var4 = false;
         }

         if (!var4) {
            throw new MatchError(this);
         }

         var3 = this;
      }

      return (Interval)var3;
   }

   public Interval $minus(final Object rhs, final AdditiveGroup ev) {
      return this.$plus(ev.negate(rhs), ev);
   }

   public Interval unary_$minus(final AdditiveGroup ev) {
      Object var2;
      if (this instanceof Point) {
         Point var5 = (Point)this;
         Object v = var5.value();
         var2 = Point$.MODULE$.apply(ev.negate(v));
      } else if (this instanceof Bounded) {
         Bounded var7 = (Bounded)this;
         Object l = var7.lower();
         Object u = var7.upper();
         int f = var7.flags();
         var2 = Bounded$.MODULE$.apply(ev.negate(u), ev.negate(l), this.swapFlags(f));
      } else if (this instanceof Above) {
         Above var11 = (Above)this;
         Object l = var11.lower();
         int lf = var11.flags();
         var2 = Below$.MODULE$.apply(ev.negate(l), this.lowerFlagToUpper(lf));
      } else if (this instanceof Below) {
         Below var14 = (Below)this;
         Object u = var14.upper();
         int uf = var14.flags();
         var2 = Above$.MODULE$.apply(ev.negate(u), this.upperFlagToLower(uf));
      } else {
         boolean var3;
         if (this instanceof All) {
            var3 = true;
         } else if (this instanceof Empty) {
            var3 = true;
         } else {
            var3 = false;
         }

         if (!var3) {
            throw new MatchError(this);
         }

         var2 = this;
      }

      return (Interval)var2;
   }

   public Interval $times(final Object rhs, final Order o, final Semiring ev) {
      Object var10000;
      if (o.lt(rhs, ev.zero())) {
         Object var6;
         if (this instanceof Point) {
            Point var9 = (Point)this;
            Object v = var9.value();
            var6 = Point$.MODULE$.apply(ev.times(v, rhs));
         } else if (this instanceof Bounded) {
            Bounded var11 = (Bounded)this;
            Object l = var11.lower();
            Object u = var11.upper();
            int f = var11.flags();
            var6 = Bounded$.MODULE$.apply(ev.times(u, rhs), ev.times(l, rhs), this.swapFlags(f));
         } else if (this instanceof Above) {
            Above var15 = (Above)this;
            Object l = var15.lower();
            int lf = var15.flags();
            var6 = Below$.MODULE$.apply(ev.times(l, rhs), this.lowerFlagToUpper(lf));
         } else if (this instanceof Below) {
            Below var18 = (Below)this;
            Object u = var18.upper();
            int uf = var18.flags();
            var6 = Above$.MODULE$.apply(ev.times(u, rhs), this.upperFlagToLower(uf));
         } else {
            boolean var7;
            if (this instanceof All) {
               var7 = true;
            } else if (this instanceof Empty) {
               var7 = true;
            } else {
               var7 = false;
            }

            if (!var7) {
               throw new MatchError(this);
            }

            var6 = this;
         }

         var10000 = var6;
      } else if (o.eqv(rhs, ev.zero())) {
         var10000 = Interval$.MODULE$.zero(o, ev);
      } else {
         Object var4;
         if (this instanceof Point) {
            Point var22 = (Point)this;
            Object v = var22.value();
            var4 = Point$.MODULE$.apply(ev.times(v, rhs));
         } else if (this instanceof Bounded) {
            Bounded var24 = (Bounded)this;
            Object l = var24.lower();
            Object u = var24.upper();
            int flags = var24.flags();
            var4 = Bounded$.MODULE$.apply(ev.times(l, rhs), ev.times(u, rhs), flags);
         } else if (this instanceof Above) {
            Above var28 = (Above)this;
            Object l = var28.lower();
            int lf = var28.flags();
            var4 = Above$.MODULE$.apply(ev.times(l, rhs), lf);
         } else if (this instanceof Below) {
            Below var31 = (Below)this;
            Object u = var31.upper();
            int uf = var31.flags();
            var4 = Below$.MODULE$.apply(ev.times(u, rhs), uf);
         } else {
            boolean var5;
            if (this instanceof All) {
               var5 = true;
            } else if (this instanceof Empty) {
               var5 = true;
            } else {
               var5 = false;
            }

            if (!var5) {
               throw new MatchError(this);
            }

            var4 = this;
         }

         var10000 = var4;
      }

      return (Interval)var10000;
   }

   public Interval pow(final int k, final Order o, final Ring r) {
      if (k < 0) {
         throw new IllegalArgumentException((new StringBuilder(19)).append("negative exponent: ").append(k).toString());
      } else {
         Interval var10000;
         if (k == 0) {
            var10000 = Interval$.MODULE$.point(r.one(), o);
         } else if (k == 1) {
            var10000 = this;
         } else if ((k & 1) == 0) {
            Interval t = this.abs(o, r);
            var10000 = this.loop$1(t, k - 1, t, o, r);
         } else {
            var10000 = this.loop$1(this, k - 1, this, o, r);
         }

         return var10000;
      }
   }

   public Interval nroot(final int k, final Order o, final Ring r, final NRoot n) {
      Object var10000;
      if (k == 1) {
         var10000 = this;
      } else {
         if ((k & 1) == 0 && this.hasBelow(r.zero(), o)) {
            throw new IllegalArgumentException("can't take even root of negative number");
         }

         boolean var6;
         if (this instanceof All) {
            var6 = true;
         } else if (this instanceof Empty) {
            var6 = true;
         } else {
            var6 = false;
         }

         Object var5;
         if (var6) {
            var5 = this;
         } else if (this instanceof Point) {
            Point var8 = (Point)this;
            Object v = var8.value();
            var5 = Point$.MODULE$.apply(n.nroot(v, k));
         } else if (this instanceof Above) {
            Above var10 = (Above)this;
            Object l = var10.lower();
            int lf = var10.flags();
            var5 = Above$.MODULE$.apply(n.nroot(l, k), lf);
         } else if (this instanceof Below) {
            Below var13 = (Below)this;
            Object u = var13.upper();
            int uf = var13.flags();
            var5 = Below$.MODULE$.apply(n.nroot(u, k), uf);
         } else {
            if (!(this instanceof Bounded)) {
               throw new MatchError(this);
            }

            Bounded var16 = (Bounded)this;
            Object l = var16.lower();
            Object u = var16.upper();
            int flags = var16.flags();
            var5 = Bounded$.MODULE$.apply(n.nroot(l, k), n.nroot(u, k), flags);
         }

         var10000 = var5;
      }

      return (Interval)var10000;
   }

   public Interval sqrt(final Order o, final Ring r, final NRoot n) {
      return this.nroot(2, o, r, n);
   }

   public Option top(final Object epsilon, final AdditiveGroup r) {
      boolean var4;
      if (this instanceof Empty) {
         var4 = true;
      } else if (this instanceof All) {
         var4 = true;
      } else if (this instanceof Above) {
         var4 = true;
      } else {
         var4 = false;
      }

      Object var3;
      if (var4) {
         var3 = scala.None..MODULE$;
      } else if (this instanceof Below) {
         Below var6 = (Below)this;
         Object upper = var6.upper();
         int uf = var6.flags();
         var3 = new Some(this.isOpenUpper(uf) ? r.minus(upper, epsilon) : upper);
      } else if (this instanceof Point) {
         Point var9 = (Point)this;
         Object v = var9.value();
         var3 = new Some(v);
      } else {
         if (!(this instanceof Bounded)) {
            throw new MatchError(this);
         }

         Bounded var11 = (Bounded)this;
         Object upper = var11.upper();
         int flags = var11.flags();
         var3 = new Some(this.isOpenUpper(flags) ? r.minus(upper, epsilon) : upper);
      }

      return (Option)var3;
   }

   public Option bottom(final Object epsilon, final AdditiveGroup r) {
      boolean var4;
      if (this instanceof Empty) {
         var4 = true;
      } else if (this instanceof All) {
         var4 = true;
      } else if (this instanceof Below) {
         var4 = true;
      } else {
         var4 = false;
      }

      Object var3;
      if (var4) {
         var3 = scala.None..MODULE$;
      } else if (this instanceof Above) {
         Above var6 = (Above)this;
         Object lower = var6.lower();
         int lf = var6.flags();
         var3 = new Some(this.isOpenLower(lf) ? r.plus(lower, epsilon) : lower);
      } else if (this instanceof Point) {
         Point var9 = (Point)this;
         Object v = var9.value();
         var3 = new Some(v);
      } else {
         if (!(this instanceof Bounded)) {
            throw new MatchError(this);
         }

         Bounded var11 = (Bounded)this;
         Object lower = var11.lower();
         int flags = var11.flags();
         var3 = new Some(this.isOpenLower(flags) ? r.plus(lower, epsilon) : lower);
      }

      return (Option)var3;
   }

   public Dist dist(final Object min, final Object max, final Object epsilon, final Uniform u, final AdditiveGroup r) {
      return u.apply(this.bottom(epsilon, r).getOrElse(() -> min), this.top(epsilon, r).getOrElse(() -> max));
   }

   public Interval translate(final Polynomial p, final Order o, final Field ev) {
      List terms2 = p.terms(ev, o).map((x0$1) -> {
         if (x0$1 != null) {
            Object c = x0$1.coeff();
            int e = x0$1.exp();
            Term var2 = new Term(Interval$.MODULE$.point(c, o), e);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      });
      PolySparse p2 = Polynomial$.MODULE$.apply((IterableOnce)terms2, Interval$.MODULE$.semiring(ev, o), Interval$.MODULE$.eq(o), scala.reflect.ClassTag..MODULE$.apply(Interval.class));
      return (Interval)p2.apply(this, Interval$.MODULE$.semiring(ev, o));
   }

   public boolean $u220B(final Object rhs, final Order o) {
      return this.contains(rhs, o);
   }

   public boolean $u220C(final Object rhs, final Order o) {
      return !this.contains(rhs, o);
   }

   public boolean $u2208$colon(final Object a, final Order o) {
      return this.contains(a, o);
   }

   public boolean $u2209$colon(final Object a, final Order o) {
      return !this.contains(a, o);
   }

   public Interval $u2229(final Interval rhs, final Order o) {
      return this.intersect(rhs, o);
   }

   public Interval $u222A(final Interval rhs, final Order o) {
      return this.union(rhs, o);
   }

   public List $bslash(final Interval rhs, final Order o) {
      return this.$minus$minus(rhs, o);
   }

   public boolean $u2282(final Interval rhs, final Order o) {
      return this.isProperSubsetOf(rhs, o);
   }

   public boolean $u2283(final Interval rhs, final Order o) {
      return this.isProperSupersetOf(rhs, o);
   }

   public boolean $u2286(final Interval rhs, final Order o) {
      return this.isSubsetOf(rhs, o);
   }

   public boolean $u2287(final Interval rhs, final Order o) {
      return this.isSupersetOf(rhs, o);
   }

   private Object getStart(final Bound bound, final Object step, final String unboundError, final AdditiveMonoid ev) {
      Object var5;
      if (bound instanceof EmptyBound) {
         var5 = ev.zero();
      } else if (bound instanceof Open) {
         Open var7 = (Open)bound;
         Object x = var7.a();
         var5 = ev.plus(x, step);
      } else {
         if (!(bound instanceof Closed)) {
            if (bound instanceof Unbound) {
               throw new IllegalArgumentException(unboundError);
            }

            throw new MatchError(bound);
         }

         Closed var9 = (Closed)bound;
         Object x = var9.a();
         var5 = x;
      }

      return var5;
   }

   public Iterator iterator(final Object step, final Order o, final AdditiveMonoid ev, final NumberTag nt) {
      if (o.eqv(step, ev.zero())) {
         throw new IllegalArgumentException("zero step");
      } else {
         Iterator var10000;
         if (o.gt(step, ev.zero())) {
            Object x = this.getStart(this.lowerBound(), step, "positive step with no lower bound", ev);
            Function2 test = (x1, x2) -> BoxesRunTime.boxToBoolean($anonfun$iterator$1(o, x1, x2));
            Bound var9 = this.upperBound();
            Iterator var6;
            if (var9 instanceof EmptyBound) {
               var6 = .MODULE$.Iterator().empty();
            } else if (var9 instanceof Unbound) {
               var6 = iter$1(x, false, (x$5) -> BoxesRunTime.boxToBoolean($anonfun$iterator$2(x$5)), test, nt, ev, step);
            } else if (var9 instanceof Closed) {
               Closed var10 = (Closed)var9;
               Object y = var10.a();
               var6 = iter$1(x, o.gt(ev.plus(y, step), y), (x$6) -> BoxesRunTime.boxToBoolean($anonfun$iterator$3(o, y, x$6)), test, nt, ev, step);
            } else {
               if (!(var9 instanceof Open)) {
                  throw new MatchError(var9);
               }

               Open var12 = (Open)var9;
               Object y = var12.a();
               var6 = iter$1(x, o.gt(ev.plus(y, step), y), (x$7) -> BoxesRunTime.boxToBoolean($anonfun$iterator$4(o, y, x$7)), test, nt, ev, step);
            }

            var10000 = var6;
         } else {
            Object x = this.getStart(this.upperBound(), step, "negative step with no lower bound", ev);
            Function2 test = (x1, x2) -> BoxesRunTime.boxToBoolean($anonfun$iterator$5(o, x1, x2));
            Bound var16 = this.lowerBound();
            Iterator var5;
            if (var16 instanceof EmptyBound) {
               var5 = .MODULE$.Iterator().empty();
            } else if (var16 instanceof Unbound) {
               var5 = iter$1(x, false, (x$8) -> BoxesRunTime.boxToBoolean($anonfun$iterator$6(x$8)), test, nt, ev, step);
            } else if (var16 instanceof Closed) {
               Closed var17 = (Closed)var16;
               Object y = var17.a();
               var5 = iter$1(x, o.lt(ev.plus(y, step), y), (x$9) -> BoxesRunTime.boxToBoolean($anonfun$iterator$7(o, y, x$9)), test, nt, ev, step);
            } else {
               if (!(var16 instanceof Open)) {
                  throw new MatchError(var16);
               }

               Open var19 = (Open)var16;
               Object y = var19.a();
               var5 = iter$1(x, o.lt(ev.plus(y, step), y), (x$10) -> BoxesRunTime.boxToBoolean($anonfun$iterator$8(o, y, x$10)), test, nt, ev, step);
            }

            var10000 = var5;
         }

         return var10000;
      }
   }

   public void loop(final Object step, final Function1 f, final Order o, final AdditiveMonoid ev, final NumberTag nt) {
      this.iterator(step, o, ev, nt).foreach(f);
   }

   public Object foldOver(final Object init, final Object step, final Function2 f, final Order o, final AdditiveMonoid ev, final NumberTag nt) {
      return this.iterator(step, o, ev, nt).foldLeft(init, f);
   }

   public Overlap overlap(final Interval rhs, final Order o) {
      return Overlap$.MODULE$.apply(this, rhs, o);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$$minus$minus$2(final Interval x$2) {
      return x$2.nonEmpty();
   }

   private final Interval aboveAbove$1(final Object lower1, final int lf1, final Object lower2, final int lf2, final Order o$2, final Object z$1, final Semiring ev$2) {
      int lower1s = o$2.compare(lower1, z$1);
      int lower2s = o$2.compare(lower2, z$1);
      Object var10000;
      if (lower1s >= 0 && lower2s >= 0) {
         boolean strongZero = lower1s == 0 && this.isClosedLower(lf1) || lower2s == 0 && this.isClosedLower(lf2);
         int flags = strongZero ? 0 : lf1 | lf2;
         var10000 = Above$.MODULE$.apply(ev$2.times(lower1, lower2), flags);
      } else {
         var10000 = All$.MODULE$.apply();
      }

      return (Interval)var10000;
   }

   private final Interval belowBelow$1(final Object upper1, final int uf1, final Object upper2, final int uf2, final Order o$2, final Object z$1, final Semiring ev$2) {
      int upper1s = o$2.compare(upper1, z$1);
      int upper2s = o$2.compare(upper2, z$1);
      Object var10000;
      if (upper1s <= 0 && upper2s <= 0) {
         boolean strongZero = upper1s == 0 && this.isClosedUpper(uf1) || upper2s == 0 && this.isClosedUpper(uf2);
         int flags = strongZero ? 0 : this.upperFlagToLower(uf1) | this.upperFlagToLower(uf2);
         var10000 = Above$.MODULE$.apply(ev$2.times(upper1, upper2), flags);
      } else {
         var10000 = All$.MODULE$.apply();
      }

      return (Interval)var10000;
   }

   private final Interval aboveBelow$1(final Object lower1, final int lf1, final Object upper2, final int uf2, final Order o$2, final Object z$1, final Semiring ev$2) {
      int lower1s = o$2.compare(lower1, z$1);
      int upper2s = o$2.compare(upper2, z$1);
      Object var10000;
      if (lower1s >= 0 && upper2s <= 0) {
         boolean strongZero = lower1s == 0 && this.isClosedLower(lf1) || upper2s == 0 && this.isClosedUpper(uf2);
         int flags = strongZero ? 0 : this.lowerFlagToUpper(lf1) | uf2;
         var10000 = Below$.MODULE$.apply(ev$2.times(lower1, upper2), flags);
      } else {
         var10000 = All$.MODULE$.apply();
      }

      return (Interval)var10000;
   }

   private final Interval aboveBounded$1(final Object lower1, final int lf1, final Object lower2, final Object upper2, final int flags2, final Order o$2, final Object z$1, final Semiring ev$2) {
      int lower1s = o$2.compare(lower1, z$1);
      int lower2s = o$2.compare(lower2, z$1);
      int upper2s = o$2.compare(upper2, z$1);
      boolean hasBelowZero1 = lower1s < 0;
      boolean hasBelowZero2 = lower2s < 0;
      boolean hasAboveZero2 = upper2s > 0;
      Object var10000;
      if (hasBelowZero2 && hasAboveZero2) {
         var10000 = All$.MODULE$.apply();
      } else if (hasAboveZero2) {
         if (hasBelowZero1) {
            var10000 = Above$.MODULE$.apply(ev$2.times(lower1, upper2), lf1 | this.upperFlagToLower(flags2));
         } else {
            boolean strongZero = lower1s == 0 && this.isClosedLower(lf1) || lower2s == 0 && this.isClosedLower(flags2);
            int flags = strongZero ? 0 : lf1 | this.lowerFlag(flags2);
            var10000 = Above$.MODULE$.apply(ev$2.times(lower1, lower2), flags);
         }
      } else {
         scala.Predef..MODULE$.assert(hasBelowZero2);
         if (hasBelowZero1) {
            boolean strongZero = lower1s == 0 && this.isClosedLower(lf1) || lower2s == 0 && this.isClosedLower(flags2);
            int flags = strongZero ? 0 : this.lowerFlagToUpper(lf1) | this.lowerFlagToUpper(flags2);
            var10000 = Below$.MODULE$.apply(ev$2.times(lower1, lower2), flags);
         } else {
            boolean strongZero = lower1s == 0 && this.isClosedLower(lf1) || upper2s == 0 && this.isClosedUpper(flags2);
            int flags = strongZero ? 0 : this.lowerFlagToUpper(lf1) | this.upperFlag(flags2);
            var10000 = Below$.MODULE$.apply(ev$2.times(lower1, upper2), flags);
         }
      }

      return (Interval)var10000;
   }

   private final Interval belowBounded$1(final Object upper1, final int uf1, final Object lower2, final Object upper2, final int flags2, final Order o$2, final Object z$1, final Semiring ev$2) {
      int upper1s = o$2.compare(upper1, z$1);
      int lower2s = o$2.compare(lower2, z$1);
      int upper2s = o$2.compare(upper2, z$1);
      boolean hasAboveZero1 = upper1s > 0;
      boolean hasBelowZero2 = lower2s < 0;
      boolean hasAboveZero2 = upper2s > 0;
      Object var10000;
      if (hasBelowZero2 && hasAboveZero2) {
         var10000 = All$.MODULE$.apply();
      } else if (hasAboveZero2) {
         if (hasAboveZero1) {
            var10000 = Below$.MODULE$.apply(ev$2.times(upper1, upper2), uf1 | this.upperFlag(flags2));
         } else {
            boolean strongZero = upper1s == 0 && this.isClosedUpper(uf1) || lower2s == 0 && this.isClosedLower(flags2);
            int flags = strongZero ? 0 : uf1 | this.lowerFlagToUpper(flags2);
            var10000 = Below$.MODULE$.apply(ev$2.times(upper1, lower2), flags);
         }
      } else if (hasAboveZero1) {
         boolean strongZero = lower2s == 0 && this.isClosedLower(flags2);
         int flags = strongZero ? 0 : this.upperFlagToLower(uf1) | this.lowerFlag(flags2);
         var10000 = Above$.MODULE$.apply(ev$2.times(upper1, lower2), flags);
      } else {
         boolean strongZero = upper1s == 0 && this.isClosedUpper(uf1) || upper2s == 0 && this.isClosedUpper(flags2);
         int flags = strongZero ? 0 : this.upperFlagToLower(uf1) | this.upperFlagToLower(flags2);
         var10000 = Above$.MODULE$.apply(ev$2.times(upper1, upper2), flags);
      }

      return (Interval)var10000;
   }

   private static final Interval boundedBounded$1(final Bounded bd1, final Bounded bd2, final Order o$2, final Object z$1, final Semiring ev$2) {
      ValueBound lb1 = bd1.lowerBound();
      ValueBound ub1 = bd1.upperBound();
      ValueBound lb2 = bd2.lowerBound();
      ValueBound ub2 = bd2.upperBound();
      boolean lb1sz = o$2.eqv(lb1.a(), z$1) && lb1.isClosed();
      boolean lb2sz = o$2.eqv(lb2.a(), z$1) && lb2.isClosed();
      boolean ub1sz = o$2.eqv(ub1.a(), z$1) && ub1.isClosed();
      boolean ub2sz = o$2.eqv(ub2.a(), z$1) && ub2.isClosed();
      ValueBound ll = (ValueBound)(!lb1sz && !lb2sz ? lb1.$times$tilde(lb2, ev$2) : new Closed(z$1));
      ValueBound lu = (ValueBound)(!lb1sz && !ub2sz ? lb1.$times$tilde(ub2, ev$2) : new Closed(z$1));
      ValueBound ul = (ValueBound)(!ub1sz && !lb2sz ? ub1.$times$tilde(lb2, ev$2) : new Closed(z$1));
      ValueBound uu = (ValueBound)(!ub1sz && !ub2sz ? ub1.$times$tilde(ub2, ev$2) : new Closed(z$1));
      return ValueBound$.MODULE$.union4(ll, lu, ul, uu, o$2);
   }

   private static final Nothing error$1() {
      throw new ArithmeticException("/ by zero");
   }

   private final Interval loop$1(final Interval b, final int k, final Interval extra, final Order o$3, final Ring r$1) {
      while(k != 1) {
         Interval var10000 = b.$times((Interval)b, o$3, r$1);
         int var10001 = k >>> 1;
         extra = (k & 1) == 1 ? b.$times((Interval)extra, o$3, r$1) : extra;
         k = var10001;
         b = var10000;
      }

      return b.$times((Interval)extra, o$3, r$1);
   }

   private static final Iterator iter0$1(final Object start, final Function1 continue, final AdditiveMonoid ev$3, final Object step$1) {
      return new Iterator(start, continue, ev$3, step$1) {
         private Object x;
         private final Function1 continue$1;
         private final AdditiveMonoid ev$3;
         private final Object step$1;

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

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
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

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
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

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
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

         public String toString() {
            return Iterator.toString$(this);
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

         public Object sum(final scala.math.Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final scala.math.Numeric num) {
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

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxBy$(this, f, cmp);
         }

         public Option maxByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxByOption$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minBy$(this, f, cmp);
         }

         public Option minByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minByOption$(this, f, cmp);
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

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
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

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private Object x() {
            return this.x;
         }

         private void x_$eq(final Object x$1) {
            this.x = x$1;
         }

         public boolean hasNext() {
            return BoxesRunTime.unboxToBoolean(this.continue$1.apply(this.x()));
         }

         public Object next() {
            Object r = this.x();
            this.x_$eq(this.ev$3.plus(this.x(), this.step$1));
            return r;
         }

         public {
            this.continue$1 = continue$1;
            this.ev$3 = ev$3;
            this.step$1 = step$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.x = start$1;
         }
      };
   }

   private static final Iterator iter1$1(final Object start, final Function1 continue, final Function2 test, final AdditiveMonoid ev$3, final Object step$1) {
      return new Iterator(start, continue, ev$3, step$1, test) {
         private Object x;
         private boolean ok;
         private final Function1 continue$2;
         private final AdditiveMonoid ev$3;
         private final Object step$1;
         private final Function2 test$1;

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

         public BufferedIterator buffered() {
            return Iterator.buffered$(this);
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

         public Iterator map(final Function1 f) {
            return Iterator.map$(this, f);
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

         public Iterator take(final int n) {
            return Iterator.take$(this, n);
         }

         public Iterator takeWhile(final Function1 p) {
            return Iterator.takeWhile$(this, p);
         }

         public Iterator drop(final int n) {
            return Iterator.drop$(this, n);
         }

         public Iterator dropWhile(final Function1 p) {
            return Iterator.dropWhile$(this, p);
         }

         public Tuple2 span(final Function1 p) {
            return Iterator.span$(this, p);
         }

         public Iterator slice(final int from, final int until) {
            return Iterator.slice$(this, from, until);
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

         public String toString() {
            return Iterator.toString$(this);
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

         public Object sum(final scala.math.Numeric num) {
            return IterableOnceOps.sum$(this, num);
         }

         public Object product(final scala.math.Numeric num) {
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

         public Object maxBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxBy$(this, f, cmp);
         }

         public Option maxByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.maxByOption$(this, f, cmp);
         }

         public Object minBy(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minBy$(this, f, cmp);
         }

         public Option minByOption(final Function1 f, final Ordering cmp) {
            return IterableOnceOps.minByOption$(this, f, cmp);
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

         public Map toMap(final scala..less.colon.less ev) {
            return IterableOnceOps.toMap$(this, ev);
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

         public Iterable reversed() {
            return IterableOnceOps.reversed$(this);
         }

         public Stepper stepper(final StepperShape shape) {
            return IterableOnce.stepper$(this, shape);
         }

         public int knownSize() {
            return IterableOnce.knownSize$(this);
         }

         private Object x() {
            return this.x;
         }

         private void x_$eq(final Object x$1) {
            this.x = x$1;
         }

         private boolean ok() {
            return this.ok;
         }

         private void ok_$eq(final boolean x$1) {
            this.ok = x$1;
         }

         public boolean hasNext() {
            return this.ok() && BoxesRunTime.unboxToBoolean(this.continue$2.apply(this.x()));
         }

         public Object next() {
            Object r = this.x();
            Object next = this.ev$3.plus(this.x(), this.step$1);
            if (BoxesRunTime.unboxToBoolean(this.test$1.apply(this.x(), next))) {
               this.x_$eq(next);
            } else {
               this.ok_$eq(false);
            }

            return r;
         }

         public {
            this.continue$2 = continue$2;
            this.ev$3 = ev$3;
            this.step$1 = step$1;
            this.test$1 = test$1;
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.x = start$2;
            this.ok = true;
         }
      };
   }

   private static final Iterator iter$1(final Object start, final boolean safe, final Function1 continue, final Function2 test, final NumberTag nt$1, final AdditiveMonoid ev$3, final Object step$1) {
      return nt$1.overflows() && !safe ? iter1$1(start, continue, test, ev$3, step$1) : iter0$1(start, continue, ev$3, step$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$1(final Order o$5, final Object x1, final Object x2) {
      return o$5.lt(x1, x2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$2(final Object x$5) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$3(final Order o$5, final Object y$1, final Object x$6) {
      return o$5.lteqv(x$6, y$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$4(final Order o$5, final Object y$2, final Object x$7) {
      return o$5.lt(x$7, y$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$5(final Order o$5, final Object x1, final Object x2) {
      return o$5.gt(x1, x2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$6(final Object x$8) {
      return true;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$7(final Order o$5, final Object y$3, final Object x$9) {
      return o$5.gteqv(x$9, y$3);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$8(final Order o$5, final Object y$4, final Object x$10) {
      return o$5.gt(x$10, y$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
