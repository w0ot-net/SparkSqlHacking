package spire.syntax;

import algebra.lattice.Bool;
import algebra.lattice.Heyting;
import algebra.lattice.JoinSemilattice;
import algebra.lattice.Logic;
import algebra.lattice.MeetSemilattice;
import algebra.ring.AdditiveGroup;
import algebra.ring.AdditiveMonoid;
import algebra.ring.AdditiveSemigroup;
import algebra.ring.EuclideanRing;
import algebra.ring.GCDRing;
import algebra.ring.MultiplicativeGroup;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.MultiplicativeSemigroup;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import cats.kernel.Eq;
import cats.kernel.Group;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import cats.kernel.Semigroup;
import scala.StringContext;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import spire.NotGiven;
import spire.algebra.Action;
import spire.algebra.AdditiveAction;
import spire.algebra.CModule;
import spire.algebra.Involution;
import spire.algebra.IsReal;
import spire.algebra.MultiplicativeAction;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;
import spire.algebra.partial.Groupoid;
import spire.algebra.partial.Semigroupoid;
import spire.math.BitString;
import spire.math.ConvertableFrom;
import spire.math.ConvertableTo;
import spire.math.Integral;
import spire.math.IntegralOps;
import spire.syntax.std.ArrayOps;
import spire.syntax.std.ArraySyntax;
import spire.syntax.std.BigIntSyntax;
import spire.syntax.std.DoubleSyntax;
import spire.syntax.std.IndexedSeqOps;
import spire.syntax.std.IntSyntax;
import spire.syntax.std.LongSyntax;
import spire.syntax.std.SeqOps;
import spire.syntax.std.SeqSyntax;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011Ew\u0001CA#\u0003\u000fB\t!!\u0015\u0007\u0011\u0005U\u0013q\tE\u0001\u0003/Bq!!\u001a\u0002\t\u0003\t9gB\u0004\u0002j\u0005A\t!a\u001b\u0007\u000f\u0005=\u0014\u0001#\u0001\u0002r!9\u0011Q\r\u0003\u0005\u0002\u0005etaBA>\u0003!\u0005\u0011Q\u0010\u0004\b\u0003\u007f\n\u0001\u0012AAA\u0011\u001d\t)g\u0002C\u0001\u0003\u0013;q!a#\u0002\u0011\u0003\tiIB\u0004\u0002\u0010\u0006A\t!!%\t\u000f\u0005\u0015$\u0002\"\u0001\u0002\u001a\u001e9\u00111T\u0001\t\u0002\u0005ueaBAP\u0003!\u0005\u0011\u0011\u0015\u0005\b\u0003KjA\u0011AAU\u000f\u001d\tY+\u0001E\u0001\u0003[3q!a,\u0002\u0011\u0003\t\t\fC\u0004\u0002fA!\t!!/\b\u000f\u0005m\u0016\u0001#\u0001\u0002>\u001a9\u0011qX\u0001\t\u0002\u0005\u0005\u0007bBA3'\u0011\u0005\u0011\u0011Z\u0004\b\u0003\u0017\f\u0001\u0012AAg\r\u001d\ty-\u0001E\u0001\u0003#Dq!!\u001a\u0017\t\u0003\tInB\u0004\u0002\\\u0006A\t!!8\u0007\u000f\u0005}\u0017\u0001#\u0001\u0002b\"9\u0011QM\r\u0005\u0002\u0005%xaBAv\u0003!\u0005\u0011Q\u001e\u0004\b\u0003_\f\u0001\u0012AAy\u0011\u001d\t)\u0007\bC\u0001\u0003s<q!a?\u0002\u0011\u0003\tiPB\u0004\u0002\u0000\u0006A\tA!\u0001\t\u000f\u0005\u0015t\u0004\"\u0001\u0003\n\u001d9!1B\u0001\t\u0002\t5aa\u0002B\b\u0003!\u0005!\u0011\u0003\u0005\b\u0003K\u0012C\u0011\u0001B\r\u000f\u001d\u0011Y\"\u0001E\u0001\u0005;1qAa\b\u0002\u0011\u0003\u0011\t\u0003C\u0004\u0002f\u0015\"\tA!\u000b\b\u000f\t-\u0012\u0001#\u0001\u0003.\u00199!qF\u0001\t\u0002\tE\u0002bBA3Q\u0011\u0005!\u0011H\u0004\b\u0005w\t\u0001\u0012\u0001B\u001f\r\u001d\u0011y$\u0001E\u0001\u0005\u0003Bq!!\u001a,\t\u0003\u0011IeB\u0004\u0003L\u0005A\tA!\u0014\u0007\u000f\t=\u0013\u0001#\u0001\u0003R!9\u0011Q\r\u0018\u0005\u0002\tesa\u0002B.\u0003!\u0005!Q\f\u0004\b\u0005?\n\u0001\u0012\u0001B1\u0011\u001d\t)'\rC\u0001\u0005S:qAa\u001b\u0002\u0011\u0003\u0011iGB\u0004\u0003p\u0005A\tA!\u001d\t\u000f\u0005\u0015D\u0007\"\u0001\u0003z\u001d9!1P\u0001\t\u0002\tuda\u0002B@\u0003!\u0005!\u0011\u0011\u0005\b\u0003K:D\u0011\u0001BE\u000f\u001d\u0011Y)\u0001E\u0001\u0005\u001b3qAa$\u0002\u0011\u0003\u0011\t\nC\u0004\u0002fi\"\tA!'\b\u000f\tm\u0015\u0001#\u0001\u0003\u001e\u001a9!qT\u0001\t\u0002\t\u0005\u0006bBA3{\u0011\u0005!\u0011V\u0004\b\u0005W\u000b\u0001\u0012\u0001BW\r\u001d\u0011y+\u0001E\u0001\u0005cCq!!\u001aA\t\u0003\u0011IlB\u0004\u0003<\u0006A\tA!0\u0007\u000f\t}\u0016\u0001#\u0001\u0003B\"9\u0011QM\"\u0005\u0002\t%wa\u0002Bf\u0003!\u0005!Q\u001a\u0004\b\u0005\u001f\f\u0001\u0012\u0001Bi\u0011\u001d\t)G\u0012C\u0001\u00053<qAa7\u0002\u0011\u0003\u0011iNB\u0004\u0003`\u0006A\tA!9\t\u000f\u0005\u0015\u0014\n\"\u0001\u0003j\u001e9!1^\u0001\t\u0002\t5ha\u0002Bx\u0003!\u0005!\u0011\u001f\u0005\b\u0003KbE\u0011\u0001B}\u000f\u001d\u0011Y0\u0001E\u0001\u0005{4qAa@\u0002\u0011\u0003\u0019\t\u0001C\u0004\u0002f=#\ta!\u0003\b\u000f\r-\u0011\u0001#\u0001\u0004\u000e\u001991qB\u0001\t\u0002\rE\u0001bBA3%\u0012\u00051\u0011D\u0004\b\u00077\t\u0001\u0012AB\u000f\r\u001d\u0019y\"\u0001E\u0001\u0007CAq!!\u001aV\t\u0003\u0019IcB\u0004\u0004,\u0005A\ta!\f\u0007\u000f\r=\u0012\u0001#\u0001\u00042!9\u0011Q\r-\u0005\u0002\reraBB\u001e\u0003!\u00051Q\b\u0004\b\u0007\u007f\t\u0001\u0012AB!\u0011\u001d\t)g\u0017C\u0001\u0007\u0013:qaa\u0013\u0002\u0011\u0003\u0019iEB\u0004\u0004P\u0005A\ta!\u0015\t\u000f\u0005\u0015d\f\"\u0001\u0004Z\u001d911L\u0001\t\u0002\rucaBB0\u0003!\u00051\u0011\r\u0005\b\u0003K\nG\u0011AB5\u000f\u001d\u0019Y'\u0001E\u0001\u0007[2qaa\u001c\u0002\u0011\u0003\u0019\t\bC\u0004\u0002f\u0011$\ta!\u001f\b\u000f\rm\u0014\u0001#\u0001\u0004~\u001991qP\u0001\t\u0002\r\u0005\u0005bBA3O\u0012\u00051\u0011R\u0004\b\u0007\u0017\u000b\u0001\u0012ABG\r\u001d\u0019y)\u0001E\u0001\u0007#Cq!!\u001ak\t\u0003\u0019IjB\u0004\u0004\u001c\u0006A\ta!(\u0007\u000f\r}\u0015\u0001#\u0001\u0004\"\"9\u0011QM7\u0005\u0002\r%vaBBV\u0003!\u00051Q\u0016\u0004\b\u0007_\u000b\u0001\u0012ABY\u0011\u001d\t)\u0007\u001dC\u0001\u0007s;qaa/\u0002\u0011\u0003\u0019iLB\u0004\u0004@\u0006A\ta!1\t\u000f\u0005\u00154\u000f\"\u0001\u0004J\u001e911Z\u0001\t\u0002\r5gaBBh\u0003!\u00051\u0011\u001b\u0005\b\u0003K2H\u0011ABm\u000f\u001d\u0019Y.\u0001E\u0001\u0007;4qaa8\u0002\u0011\u0003\u0019\t\u000fC\u0004\u0002fe$\ta!;\b\u000f\r-\u0018\u0001#\u0001\u0004n\u001a91q^\u0001\t\u0002\rE\bbBA3y\u0012\u00051\u0011`\u0004\b\u0007w\f\u0001\u0012AB\u007f\r\u001d\u0019y0\u0001E\u0001\t\u0003Aq!!\u001a\u0000\t\u0003!IaB\u0004\u0005\f\u0005A\t\u0001\"\u0004\u0007\u000f\u0011=\u0011\u0001#\u0001\u0005\u0012!A\u0011QMA\u0003\t\u0003!IbB\u0004\u0005\u001c\u0005A\t\u0001\"\b\u0007\u000f\u0011}\u0011\u0001#\u0001\u0005\"!A\u0011QMA\u0006\t\u0003!IcB\u0004\u0005,\u0005A\t\u0001\"\f\u0007\u000f\u0011=\u0012\u0001#\u0001\u00052!A\u0011QMA\t\t\u0003!IdB\u0004\u0005<\u0005A\t\u0001\"\u0010\u0007\u000f\u0011}\u0012\u0001#\u0001\u0005B!A\u0011QMA\f\t\u0003!IeB\u0004\u0005L\u0005A\t\u0001\"\u0014\u0007\u000f\u0011=\u0013\u0001#\u0001\u0005R!A\u0011QMA\u000f\t\u0003!IfB\u0004\u0005\\\u0005A\t\u0001\"\u0018\u0007\u000f\u0011}\u0013\u0001#\u0001\u0005b!A\u0011QMA\u0012\t\u0003!IgB\u0004\u0005l\u0005A\t\u0001\"\u001c\u0007\u000f\u0011=\u0014\u0001#\u0001\u0005r!A\u0011QMA\u0015\t\u0003!IhB\u0004\u0005|\u0005A\t\u0001\" \u0007\u000f\u0011}\u0014\u0001#\u0001\u0005\u0002\"A\u0011QMA\u0018\t\u0003!IiB\u0004\u0005\f\u0006A\t\u0001\"$\u0007\u000f\u0011=\u0015\u0001#\u0001\u0005\u0012\"A\u0011QMA\u001b\t\u0003!IjB\u0004\u0005\u001c\u0006A\t\u0001\"(\u0007\u000f\u0011}\u0015\u0001#\u0001\u0005\"\"A\u0011QMA\u001e\t\u0003!IkB\u0004\u0005B\u0006A\t\u0001b1\u0007\u000f\u0011\u0015\u0017\u0001#\u0001\u0005H\"A\u0011QMA!\t\u0003!y-A\u0004qC\u000e\\\u0017mZ3\u000b\t\u0005%\u00131J\u0001\u0007gftG/\u0019=\u000b\u0005\u00055\u0013!B:qSJ,7\u0001\u0001\t\u0004\u0003'\nQBAA$\u0005\u001d\u0001\u0018mY6bO\u0016\u001c2!AA-!\u0011\tY&!\u0019\u000e\u0005\u0005u#BAA0\u0003\u0015\u00198-\u00197b\u0013\u0011\t\u0019'!\u0018\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0011K\u0001\u0005G\u001a|'\u000fE\u0002\u0002n\u0011i\u0011!\u0001\u0002\u0005G\u001a|'oE\u0003\u0005\u00033\n\u0019\b\u0005\u0003\u0002T\u0005U\u0014\u0002BA<\u0003\u000f\u0012!b\u00114peNKh\u000e^1y)\t\tY'\u0001\u0005mSR,'/\u00197t!\r\tig\u0002\u0002\tY&$XM]1mgN)q!!\u0017\u0002\u0004B!\u00111KAC\u0013\u0011\t9)a\u0012\u0003\u001d1KG/\u001a:bYN\u001c\u0016P\u001c;bqR\u0011\u0011QP\u0001\u0003KF\u00042!!\u001c\u000b\u0005\t)\u0017oE\u0003\u000b\u00033\n\u0019\n\u0005\u0003\u0002T\u0005U\u0015\u0002BAL\u0003\u000f\u0012\u0001\"R9Ts:$\u0018\r\u001f\u000b\u0003\u0003\u001b\u000bA\u0002]1si&\fGn\u0014:eKJ\u00042!!\u001c\u000e\u00051\u0001\u0018M\u001d;jC2|%\u000fZ3s'\u0015i\u0011\u0011LAR!\u0011\t\u0019&!*\n\t\u0005\u001d\u0016q\t\u0002\u0013!\u0006\u0014H/[1m\u001fJ$WM]*z]R\f\u0007\u0010\u0006\u0002\u0002\u001e\u0006)qN\u001d3feB\u0019\u0011Q\u000e\t\u0003\u000b=\u0014H-\u001a:\u0014\u000bA\tI&a-\u0011\t\u0005M\u0013QW\u0005\u0005\u0003o\u000b9EA\u0006Pe\u0012,'oU=oi\u0006DHCAAW\u0003\u0019\u0019\u0018n\u001a8fIB\u0019\u0011QN\n\u0003\rMLwM\\3e'\u0015\u0019\u0012\u0011LAb!\u0011\t\u0019&!2\n\t\u0005\u001d\u0017q\t\u0002\r'&<g.\u001a3Ts:$\u0018\r\u001f\u000b\u0003\u0003{\u000b\u0011\u0003\u001e:v]\u000e\fG/\u001a3ESZL7/[8o!\r\tiG\u0006\u0002\u0012iJ,hnY1uK\u0012$\u0015N^5tS>t7#\u0002\f\u0002Z\u0005M\u0007\u0003BA*\u0003+LA!a6\u0002H\t9BK];oG\u0006$X\r\u001a#jm&\u001c\u0018n\u001c8Ts:$\u0018\r\u001f\u000b\u0003\u0003\u001b\f!\"\u001b8w_2,H/[8o!\r\ti'\u0007\u0002\u000bS:4x\u000e\\;uS>t7#B\r\u0002Z\u0005\r\b\u0003BA*\u0003KLA!a:\u0002H\t\u0001\u0012J\u001c<pYV$\u0018n\u001c8Ts:$\u0018\r\u001f\u000b\u0003\u0003;\fa![:SK\u0006d\u0007cAA79\t1\u0011n\u001d*fC2\u001cR\u0001HA-\u0003g\u0004B!a\u0015\u0002v&!\u0011q_A$\u00051I5OU3bYNKh\u000e^1y)\t\ti/A\bd_:4XM\u001d;bE2,gI]8n!\r\tig\b\u0002\u0010G>tg/\u001a:uC\ndWM\u0012:p[N)q$!\u0017\u0003\u0004A!\u00111\u000bB\u0003\u0013\u0011\u00119!a\u0012\u0003+\r{gN^3si\u0006\u0014G.\u001a$s_6\u001c\u0016P\u001c;bqR\u0011\u0011Q`\u0001\rg\u0016l\u0017n\u001a:pkB|\u0017\u000e\u001a\t\u0004\u0003[\u0012#\u0001D:f[&<'o\\;q_&$7#\u0002\u0012\u0002Z\tM\u0001\u0003BA*\u0005+IAAa\u0006\u0002H\t\u00112+Z7jOJ|W\u000f]8jINKh\u000e^1y)\t\u0011i!\u0001\u0005he>,\bo\\5e!\r\ti'\n\u0002\tOJ|W\u000f]8jIN)Q%!\u0017\u0003$A!\u00111\u000bB\u0013\u0013\u0011\u00119#a\u0012\u0003\u001d\u001d\u0013x.\u001e9pS\u0012\u001c\u0016P\u001c;bqR\u0011!QD\u0001\ng\u0016l\u0017n\u001a:pkB\u00042!!\u001c)\u0005%\u0019X-\\5he>,\boE\u0003)\u00033\u0012\u0019\u0004\u0005\u0003\u0002T\tU\u0012\u0002\u0002B\u001c\u0003\u000f\u0012qbU3nS\u001e\u0014x.\u001e9Ts:$\u0018\r\u001f\u000b\u0003\u0005[\ta!\\8o_&$\u0007cAA7W\t1Qn\u001c8pS\u0012\u001cRaKA-\u0005\u0007\u0002B!a\u0015\u0003F%!!qIA$\u00051iuN\\8jINKh\u000e^1y)\t\u0011i$A\u0003he>,\b\u000fE\u0002\u0002n9\u0012Qa\u001a:pkB\u001cRALA-\u0005'\u0002B!a\u0015\u0003V%!!qKA$\u0005-9%o\\;q'ftG/\u0019=\u0015\u0005\t5\u0013!E1eI&$\u0018N^3TK6LwM]8vaB\u0019\u0011QN\u0019\u0003#\u0005$G-\u001b;jm\u0016\u001cV-\\5he>,\boE\u00032\u00033\u0012\u0019\u0007\u0005\u0003\u0002T\t\u0015\u0014\u0002\u0002B4\u0003\u000f\u0012q#\u00113eSRLg/Z*f[&<'o\\;q'ftG/\u0019=\u0015\u0005\tu\u0013AD1eI&$\u0018N^3N_:|\u0017\u000e\u001a\t\u0004\u0003[\"$AD1eI&$\u0018N^3N_:|\u0017\u000eZ\n\u0006i\u0005e#1\u000f\t\u0005\u0003'\u0012)(\u0003\u0003\u0003x\u0005\u001d#\u0001F!eI&$\u0018N^3N_:|\u0017\u000eZ*z]R\f\u0007\u0010\u0006\u0002\u0003n\u0005i\u0011\r\u001a3ji&4Xm\u0012:pkB\u00042!!\u001c8\u00055\tG\rZ5uSZ,wI]8vaN)q'!\u0017\u0003\u0004B!\u00111\u000bBC\u0013\u0011\u00119)a\u0012\u0003'\u0005#G-\u001b;jm\u0016<%o\\;q'ftG/\u0019=\u0015\u0005\tu\u0014aF7vYRL\u0007\u000f\\5dCRLg/Z*f[&<'o\\;q!\r\tiG\u000f\u0002\u0018[VdG/\u001b9mS\u000e\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\u001cRAOA-\u0005'\u0003B!a\u0015\u0003\u0016&!!qSA$\u0005uiU\u000f\u001c;ja2L7-\u0019;jm\u0016\u001cV-\\5he>,\boU=oi\u0006DHC\u0001BG\u0003QiW\u000f\u001c;ja2L7-\u0019;jm\u0016luN\\8jIB\u0019\u0011QN\u001f\u0003)5,H\u000e^5qY&\u001c\u0017\r^5wK6{gn\\5e'\u0015i\u0014\u0011\fBR!\u0011\t\u0019F!*\n\t\t\u001d\u0016q\t\u0002\u001b\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u001b>tw.\u001b3Ts:$\u0018\r\u001f\u000b\u0003\u0005;\u000b1#\\;mi&\u0004H.[2bi&4Xm\u0012:pkB\u00042!!\u001cA\u0005MiW\u000f\u001c;ja2L7-\u0019;jm\u0016<%o\\;q'\u0015\u0001\u0015\u0011\fBZ!\u0011\t\u0019F!.\n\t\t]\u0016q\t\u0002\u001a\u001bVdG/\u001b9mS\u000e\fG/\u001b<f\u000fJ|W\u000f]*z]R\f\u0007\u0010\u0006\u0002\u0003.\u0006A1/Z7je&tw\rE\u0002\u0002n\r\u0013\u0001b]3nSJLgnZ\n\u0006\u0007\u0006e#1\u0019\t\u0005\u0003'\u0012)-\u0003\u0003\u0003H\u0006\u001d#AD*f[&\u0014\u0018N\\4Ts:$\u0018\r\u001f\u000b\u0003\u0005{\u000b1A]5h!\r\tiG\u0012\u0002\u0004e&<7#\u0002$\u0002Z\tM\u0007\u0003BA*\u0005+LAAa6\u0002H\tI!+[4Ts:$\u0018\r\u001f\u000b\u0003\u0005\u001b\f1A\u001d8h!\r\ti'\u0013\u0002\u0004e:<7#B%\u0002Z\t\r\b\u0003BA*\u0005KLAAa:\u0002H\tI!K\\4Ts:$\u0018\r\u001f\u000b\u0003\u0005;\fAA]5oOB\u0019\u0011Q\u000e'\u0003\tILgnZ\n\u0006\u0019\u0006e#1\u001f\t\u0005\u0003'\u0012)0\u0003\u0003\u0003x\u0006\u001d#A\u0003*j]\u001e\u001c\u0016P\u001c;bqR\u0011!Q^\u0001\bO\u000e$'+\u001b8h!\r\tig\u0014\u0002\bO\u000e$'+\u001b8h'\u0015y\u0015\u0011LB\u0002!\u0011\t\u0019f!\u0002\n\t\r\u001d\u0011q\t\u0002\u000e\u000f\u000e#%+\u001b8h'ftG/\u0019=\u0015\u0005\tu\u0018!D3vG2LG-Z1o%&tw\rE\u0002\u0002nI\u0013Q\"Z;dY&$W-\u00198SS:<7#\u0002*\u0002Z\rM\u0001\u0003BA*\u0007+IAaa\u0006\u0002H\t\u0019R)^2mS\u0012,\u0017M\u001c*j]\u001e\u001c\u0016P\u001c;bqR\u00111QB\u0001\u0006M&,G\u000e\u001a\t\u0004\u0003[*&!\u00024jK2$7#B+\u0002Z\r\r\u0002\u0003BA*\u0007KIAaa\n\u0002H\tYa)[3mINKh\u000e^1y)\t\u0019i\"A\u0003oe>|G\u000fE\u0002\u0002na\u0013QA\u001c:p_R\u001cR\u0001WA-\u0007g\u0001B!a\u0015\u00046%!1qGA$\u0005-q%k\\8u'ftG/\u0019=\u0015\u0005\r5\u0012\u0001\u0002;sS\u001e\u00042!!\u001c\\\u0005\u0011!(/[4\u0014\u000bm\u000bIfa\u0011\u0011\t\u0005M3QI\u0005\u0005\u0007\u000f\n9E\u0001\u0006Ue&<7+\u001f8uCb$\"a!\u0010\u0002\u00151,g\r^'pIVdW\rE\u0002\u0002ny\u0013!\u0002\\3gi6{G-\u001e7f'\u0015q\u0016\u0011LB*!\u0011\t\u0019f!\u0016\n\t\r]\u0013q\t\u0002\u0011\u0019\u00164G/T8ek2,7+\u001f8uCb$\"a!\u0014\u0002\u0017ILw\r\u001b;N_\u0012,H.\u001a\t\u0004\u0003[\n'a\u0003:jO\"$Xj\u001c3vY\u0016\u001cR!YA-\u0007G\u0002B!a\u0015\u0004f%!1qMA$\u0005E\u0011\u0016n\u001a5u\u001b>$W\u000f\\3Ts:$\u0018\r\u001f\u000b\u0003\u0007;\nqaY'pIVdW\rE\u0002\u0002n\u0011\u0014qaY'pIVdWmE\u0003e\u00033\u001a\u0019\b\u0005\u0003\u0002T\rU\u0014\u0002BB<\u0003\u000f\u0012QbQ'pIVdWmU=oi\u0006DHCAB7\u0003-1Xm\u0019;peN\u0003\u0018mY3\u0011\u0007\u00055tMA\u0006wK\u000e$xN]*qC\u000e,7#B4\u0002Z\r\r\u0005\u0003BA*\u0007\u000bKAaa\"\u0002H\t\tb+Z2u_J\u001c\u0006/Y2f'ftG/\u0019=\u0015\u0005\ru\u0014aC7fiJL7m\u00159bG\u0016\u00042!!\u001ck\u0005-iW\r\u001e:jGN\u0003\u0018mY3\u0014\u000b)\fIfa%\u0011\t\u0005M3QS\u0005\u0005\u0007/\u000b9EA\tNKR\u0014\u0018nY*qC\u000e,7+\u001f8uCb$\"a!$\u0002#9|'/\\3e-\u0016\u001cGo\u001c:Ta\u0006\u001cW\rE\u0002\u0002n5\u0014\u0011C\\8s[\u0016$g+Z2u_J\u001c\u0006/Y2f'\u0015i\u0017\u0011LBR!\u0011\t\u0019f!*\n\t\r\u001d\u0016q\t\u0002\u0018\u001d>\u0014X.\u001a3WK\u000e$xN]*qC\u000e,7+\u001f8uCb$\"a!(\u0002#%tg.\u001a:Qe>$Wo\u0019;Ta\u0006\u001cW\rE\u0002\u0002nA\u0014\u0011#\u001b8oKJ\u0004&o\u001c3vGR\u001c\u0006/Y2f'\u0015\u0001\u0018\u0011LBZ!\u0011\t\u0019f!.\n\t\r]\u0016q\t\u0002\u0018\u0013:tWM\u001d)s_\u0012,8\r^*qC\u000e,7+\u001f8uCb$\"a!,\u0002\u001f\r|wN\u001d3j]\u0006$Xm\u00159bG\u0016\u00042!!\u001ct\u0005=\u0019wn\u001c:eS:\fG/Z*qC\u000e,7#B:\u0002Z\r\r\u0007\u0003BA*\u0007\u000bLAaa2\u0002H\t)2i\\8sI&t\u0017\r^3Ta\u0006\u001cWmU=oi\u0006DHCAB_\u0003\u001da\u0017\r\u001e;jG\u0016\u00042!!\u001cw\u0005\u001da\u0017\r\u001e;jG\u0016\u001cRA^A-\u0007'\u0004B!a\u0015\u0004V&!1q[A$\u00055a\u0015\r\u001e;jG\u0016\u001c\u0016P\u001c;bqR\u00111QZ\u0001\bQ\u0016LH/\u001b8h!\r\ti'\u001f\u0002\bQ\u0016LH/\u001b8h'\u0015I\u0018\u0011LBr!\u0011\t\u0019f!:\n\t\r\u001d\u0018q\t\u0002\u000e\u0011\u0016LH/\u001b8h'ftG/\u0019=\u0015\u0005\ru\u0017!\u00027pO&\u001c\u0007cAA7y\n)An\\4jGN)A0!\u0017\u0004tB!\u00111KB{\u0013\u0011\u001990a\u0012\u0003\u00171{w-[2Ts:$\u0018\r\u001f\u000b\u0003\u0007[\fAAY8pYB\u0019\u0011QN@\u0003\t\t|w\u000e\\\n\u0006\u007f\u0006eC1\u0001\t\u0005\u0003'\")!\u0003\u0003\u0005\b\u0005\u001d#A\u0003\"p_2\u001c\u0016P\u001c;bqR\u00111Q`\u0001\nE&$8\u000b\u001e:j]\u001e\u0004B!!\u001c\u0002\u0006\tI!-\u001b;TiJLgnZ\n\u0007\u0003\u000b\tI\u0006b\u0005\u0011\t\u0005MCQC\u0005\u0005\t/\t9EA\bCSR\u001cFO]5oONKh\u000e^1y)\t!i!A\u0007qCJ$\u0018.\u00197BGRLwN\u001c\t\u0005\u0003[\nYAA\u0007qCJ$\u0018.\u00197BGRLwN\\\n\u0007\u0003\u0017\tI\u0006b\t\u0011\t\u0005MCQE\u0005\u0005\tO\t9EA\nQCJ$\u0018.\u00197BGRLwN\\*z]R\f\u0007\u0010\u0006\u0002\u0005\u001e\u00051\u0011m\u0019;j_:\u0004B!!\u001c\u0002\u0012\t1\u0011m\u0019;j_:\u001cb!!\u0005\u0002Z\u0011M\u0002\u0003BA*\tkIA\u0001b\u000e\u0002H\ta\u0011i\u0019;j_:\u001c\u0016P\u001c;bqR\u0011AQF\u0001\u0007i>\u00148o\u001c:\u0011\t\u00055\u0014q\u0003\u0002\u0007i>\u00148o\u001c:\u0014\r\u0005]\u0011\u0011\fC\"!\u0011\t\u0019\u0006\"\u0012\n\t\u0011\u001d\u0013q\t\u0002\r)>\u00148o\u001c:Ts:$\u0018\r\u001f\u000b\u0003\t{\t\u0001\"\u001b8uK\u001e\u0014\u0018\r\u001c\t\u0005\u0003[\niB\u0001\u0005j]R,wM]1m'\u0019\ti\"!\u0017\u0005TA!\u00111\u000bC+\u0013\u0011!9&a\u0012\u0003\u001d%sG/Z4sC2\u001c\u0016P\u001c;bqR\u0011AQJ\u0001\u000bMJ\f7\r^5p]\u0006d\u0007\u0003BA7\u0003G\u0011!B\u001a:bGRLwN\\1m'\u0019\t\u0019#!\u0017\u0005dA!\u00111\u000bC3\u0013\u0011!9'a\u0012\u0003!\u0019\u0013\u0018m\u0019;j_:\fGnU=oi\u0006DHC\u0001C/\u0003\u001dqW/\\3sS\u000e\u0004B!!\u001c\u0002*\t9a.^7fe&\u001c7CBA\u0015\u00033\"\u0019\b\u0005\u0003\u0002T\u0011U\u0014\u0002\u0002C<\u0003\u000f\u0012QBT;nKJL7mU=oi\u0006DHC\u0001C7\u0003\r\tG\u000e\u001c\t\u0005\u0003[\nyCA\u0002bY2\u001cb!a\f\u0002Z\u0011\r\u0005\u0003BA*\t\u000bKA\u0001b\"\u0002H\tI\u0011\t\u001c7Ts:$\u0018\r\u001f\u000b\u0003\t{\n!\"\u00197m\u0005V$8)\u0019;t!\u0011\ti'!\u000e\u0003\u0015\u0005dGNQ;u\u0007\u0006$8o\u0005\u0004\u00026\u0005eC1\u0013\t\u0005\u0003'\")*\u0003\u0003\u0005\u0018\u0006\u001d#\u0001E!mY\n+HoQ1ugNKh\u000e^1y)\t!i)A\u0004v]\n|WO\u001c3\u0011\t\u00055\u00141\b\u0002\bk:\u0014w.\u001e8e'\u0019\tY$!\u0017\u0005$B!\u00111\u000bCS\u0013\u0011!9+a\u0012\u0003\u001bUs'm\\;oINKh\u000e^1y)\t!i\n\u000b\u0007\u0002<\u00115F1\u0017C[\ts#Y\f\u0005\u0003\u0002\\\u0011=\u0016\u0002\u0002CY\u0003;\u0012!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#\u0001b.\u0002=Us'm\\;oI\u0002\u001a\u0018P\u001c;bq\u0002:\u0018\u000e\u001c7!E\u0016\u0004#/Z7pm\u0016$\u0017!B:j]\u000e,\u0017E\u0001C_\u00031\u0019\b/\u001b:fAAr\u0013\u0007\u000f\u00181Q1\tI\u0004\",\u00054\u0012UF\u0011\u0018C^\u0003!Ig\u000e^3sm\u0006d\u0007\u0003BA7\u0003\u0003\u0012\u0001\"\u001b8uKJ4\u0018\r\\\n\u0007\u0003\u0003\nI\u0006\"3\u0011\t\u0005MC1Z\u0005\u0005\t\u001b\f9E\u0001\bJ]R,'O^1m'ftG/\u0019=\u0015\u0005\u0011\r\u0007"
)
public final class package {
   public static class cfor$ implements CforSyntax {
      public static final cfor$ MODULE$ = new cfor$();

      static {
         CforSyntax.$init$(MODULE$);
      }
   }

   public static class literals$ implements LiteralsSyntax {
      public static final literals$ MODULE$ = new literals$();
      private static volatile LiteralsSyntax.radix$ radix$module;
      private static volatile LiteralsSyntax.si$ si$module;
      private static volatile LiteralsSyntax.us$ us$module;
      private static volatile LiteralsSyntax.eu$ eu$module;

      static {
         LiteralsSyntax.$init$(MODULE$);
      }

      public StringContext literals(final StringContext s) {
         return LiteralsSyntax.literals$(this, s);
      }

      public LiteralsSyntax.radix$ radix() {
         if (radix$module == null) {
            this.radix$lzycompute$1();
         }

         return radix$module;
      }

      public LiteralsSyntax.si$ si() {
         if (si$module == null) {
            this.si$lzycompute$1();
         }

         return si$module;
      }

      public LiteralsSyntax.us$ us() {
         if (us$module == null) {
            this.us$lzycompute$1();
         }

         return us$module;
      }

      public LiteralsSyntax.eu$ eu() {
         if (eu$module == null) {
            this.eu$lzycompute$1();
         }

         return eu$module;
      }

      private final void radix$lzycompute$1() {
         synchronized(this){}

         try {
            if (radix$module == null) {
               radix$module = new LiteralsSyntax.radix$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void si$lzycompute$1() {
         synchronized(this){}

         try {
            if (si$module == null) {
               si$module = new LiteralsSyntax.si$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void us$lzycompute$1() {
         synchronized(this){}

         try {
            if (us$module == null) {
               us$module = new LiteralsSyntax.us$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void eu$lzycompute$1() {
         synchronized(this){}

         try {
            if (eu$module == null) {
               eu$module = new LiteralsSyntax.eu$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }
   }

   public static class eq$ implements EqSyntax {
      public static final eq$ MODULE$ = new eq$();

      static {
         EqSyntax.$init$(MODULE$);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class partialOrder$ implements PartialOrderSyntax {
      public static final partialOrder$ MODULE$ = new partialOrder$();

      static {
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class order$ implements OrderSyntax {
      public static final order$ MODULE$ = new order$();

      static {
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class signed$ implements SignedSyntax {
      public static final signed$ MODULE$ = new signed$();

      static {
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class truncatedDivision$ implements TruncatedDivisionSyntax {
      public static final truncatedDivision$ MODULE$ = new truncatedDivision$();

      static {
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
         TruncatedDivisionSyntax.$init$(MODULE$);
      }

      public TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
         return TruncatedDivisionSyntax.truncatedDivisionOps$(this, a, evidence$5);
      }

      public int literalIntTruncatedDivisionOps(final int lhs) {
         return TruncatedDivisionSyntax.literalIntTruncatedDivisionOps$(this, lhs);
      }

      public long literalLongTruncatedDivisionOps(final long lhs) {
         return TruncatedDivisionSyntax.literalLongTruncatedDivisionOps$(this, lhs);
      }

      public double literalDoubleTruncatedDivisionOps(final double lhs) {
         return TruncatedDivisionSyntax.literalDoubleTruncatedDivisionOps$(this, lhs);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class involution$ implements InvolutionSyntax {
      public static final involution$ MODULE$ = new involution$();

      static {
         InvolutionSyntax.$init$(MODULE$);
      }

      public InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
         return InvolutionSyntax.involutionOps$(this, lhs, evidence$6);
      }
   }

   public static class isReal$ implements IsRealSyntax {
      public static final isReal$ MODULE$ = new isReal$();

      static {
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
         IsRealSyntax.$init$(MODULE$);
      }

      public IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
         return IsRealSyntax.isRealOps$(this, a, evidence$7);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }
   }

   public static class convertableFrom$ implements ConvertableFromSyntax {
      public static final convertableFrom$ MODULE$ = new convertableFrom$();

      static {
         ConvertableFromSyntax.$init$(MODULE$);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }
   }

   public static class semigroupoid$ implements SemigroupoidSyntax {
      public static final semigroupoid$ MODULE$ = new semigroupoid$();

      static {
         SemigroupoidSyntax.$init$(MODULE$);
      }

      public SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
         return SemigroupoidSyntax.semigroupoidOps$(this, a, evidence$8);
      }
   }

   public static class groupoid$ implements GroupoidSyntax {
      public static final groupoid$ MODULE$ = new groupoid$();

      static {
         SemigroupoidSyntax.$init$(MODULE$);
         GroupoidSyntax.$init$(MODULE$);
      }

      public GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
         return GroupoidSyntax.groupoidCommonOps$(this, a, ev, ni);
      }

      public GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
         return GroupoidSyntax.groupoidOps$(this, a, ev);
      }

      public SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
         return SemigroupoidSyntax.semigroupoidOps$(this, a, evidence$8);
      }
   }

   public static class semigroup$ implements SemigroupSyntax {
      public static final semigroup$ MODULE$ = new semigroup$();

      static {
         SemigroupSyntax.$init$(MODULE$);
      }

      public SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
         return SemigroupSyntax.semigroupOps$(this, a, evidence$9);
      }
   }

   public static class monoid$ implements MonoidSyntax {
      public static final monoid$ MODULE$ = new monoid$();

      static {
         SemigroupSyntax.$init$(MODULE$);
         MonoidSyntax.$init$(MODULE$);
      }

      public MonoidOps monoidOps(final Object a, final Monoid ev) {
         return MonoidSyntax.monoidOps$(this, a, ev);
      }

      public SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
         return SemigroupSyntax.semigroupOps$(this, a, evidence$9);
      }
   }

   public static class group$ implements GroupSyntax {
      public static final group$ MODULE$ = new group$();

      static {
         SemigroupSyntax.$init$(MODULE$);
         MonoidSyntax.$init$(MODULE$);
         GroupSyntax.$init$(MODULE$);
      }

      public GroupOps groupOps(final Object a, final Group evidence$10) {
         return GroupSyntax.groupOps$(this, a, evidence$10);
      }

      public MonoidOps monoidOps(final Object a, final Monoid ev) {
         return MonoidSyntax.monoidOps$(this, a, ev);
      }

      public SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
         return SemigroupSyntax.semigroupOps$(this, a, evidence$9);
      }
   }

   public static class additiveSemigroup$ implements AdditiveSemigroupSyntax {
      public static final additiveSemigroup$ MODULE$ = new additiveSemigroup$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class additiveMonoid$ implements AdditiveMonoidSyntax {
      public static final additiveMonoid$ MODULE$ = new additiveMonoid$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class additiveGroup$ implements AdditiveGroupSyntax {
      public static final additiveGroup$ MODULE$ = new additiveGroup$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class multiplicativeSemigroup$ implements MultiplicativeSemigroupSyntax {
      public static final multiplicativeSemigroup$ MODULE$ = new multiplicativeSemigroup$();

      static {
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }
   }

   public static class multiplicativeMonoid$ implements MultiplicativeMonoidSyntax {
      public static final multiplicativeMonoid$ MODULE$ = new multiplicativeMonoid$();

      static {
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }
   }

   public static class multiplicativeGroup$ implements MultiplicativeGroupSyntax {
      public static final multiplicativeGroup$ MODULE$ = new multiplicativeGroup$();

      static {
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }
   }

   public static class semiring$ implements SemiringSyntax {
      public static final semiring$ MODULE$ = new semiring$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class rig$ implements RigSyntax {
      public static final rig$ MODULE$ = new rig$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class rng$ implements RngSyntax {
      public static final rng$ MODULE$ = new rng$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class ring$ implements RingSyntax {
      public static final ring$ MODULE$ = new ring$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class gcdRing$ implements GCDRingSyntax {
      public static final gcdRing$ MODULE$ = new gcdRing$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class euclideanRing$ implements EuclideanRingSyntax {
      public static final euclideanRing$ MODULE$ = new euclideanRing$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class field$ implements FieldSyntax {
      public static final field$ MODULE$ = new field$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class nroot$ implements NRootSyntax {
      public static final nroot$ MODULE$ = new nroot$();

      static {
         NRootSyntax.$init$(MODULE$);
      }

      public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
         return NRootSyntax.nrootOps$(this, a, evidence$18);
      }
   }

   public static class trig$ implements TrigSyntax {
      public static final trig$ MODULE$ = new trig$();

      static {
         TrigSyntax.$init$(MODULE$);
      }

      public TrigOps trigOps(final Object a, final Trig evidence$19) {
         return TrigSyntax.trigOps$(this, a, evidence$19);
      }
   }

   public static class leftModule$ implements LeftModuleSyntax {
      public static final leftModule$ MODULE$ = new leftModule$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class rightModule$ implements RightModuleSyntax {
      public static final rightModule$ MODULE$ = new rightModule$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class cModule$ implements CModuleSyntax {
      public static final cModule$ MODULE$ = new cModule$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class vectorSpace$ implements VectorSpaceSyntax {
      public static final vectorSpace$ MODULE$ = new vectorSpace$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class metricSpace$ implements MetricSpaceSyntax {
      public static final metricSpace$ MODULE$ = new metricSpace$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         MetricSpaceSyntax.$init$(MODULE$);
      }

      public MetricSpaceOps metricSpaceOps(final Object v) {
         return MetricSpaceSyntax.metricSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class normedVectorSpace$ implements NormedVectorSpaceSyntax {
      public static final normedVectorSpace$ MODULE$ = new normedVectorSpace$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         MetricSpaceSyntax.$init$(MODULE$);
         NormedVectorSpaceSyntax.$init$(MODULE$);
      }

      public NormedVectorSpaceOps normedVectorSpaceOps(final Object v) {
         return NormedVectorSpaceSyntax.normedVectorSpaceOps$(this, v);
      }

      public MetricSpaceOps metricSpaceOps(final Object v) {
         return MetricSpaceSyntax.metricSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class innerProductSpace$ implements InnerProductSpaceSyntax {
      public static final innerProductSpace$ MODULE$ = new innerProductSpace$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         InnerProductSpaceSyntax.$init$(MODULE$);
      }

      public InnerProductSpaceOps innerProductSpaceOps(final Object v) {
         return InnerProductSpaceSyntax.innerProductSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class coordinateSpace$ implements CoordinateSpaceSyntax {
      public static final coordinateSpace$ MODULE$ = new coordinateSpace$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         InnerProductSpaceSyntax.$init$(MODULE$);
         CoordinateSpaceSyntax.$init$(MODULE$);
      }

      public CoordinateSpaceOps coordinateSpaceOps(final Object v) {
         return CoordinateSpaceSyntax.coordinateSpaceOps$(this, v);
      }

      public InnerProductSpaceOps innerProductSpaceOps(final Object v) {
         return InnerProductSpaceSyntax.innerProductSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class lattice$ implements LatticeSyntax {
      public static final lattice$ MODULE$ = new lattice$();

      static {
         LatticeSyntax.$init$(MODULE$);
      }

      public MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
         return LatticeSyntax.meetOps$(this, a, evidence$20);
      }

      public JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
         return LatticeSyntax.joinOps$(this, a, evidence$21);
      }
   }

   public static class heyting$ implements HeytingSyntax {
      public static final heyting$ MODULE$ = new heyting$();

      static {
         HeytingSyntax.$init$(MODULE$);
      }

      public HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
         return HeytingSyntax.heytingOps$(this, a, evidence$22);
      }
   }

   public static class logic$ implements LogicSyntax {
      public static final logic$ MODULE$ = new logic$();

      static {
         LogicSyntax.$init$(MODULE$);
      }

      public LogicOps logicOps(final Object a, final Logic evidence$23) {
         return LogicSyntax.logicOps$(this, a, evidence$23);
      }
   }

   public static class bool$ implements BoolSyntax {
      public static final bool$ MODULE$ = new bool$();

      static {
         HeytingSyntax.$init$(MODULE$);
         BoolSyntax.$init$(MODULE$);
      }

      public BoolOps boolOps(final Object a, final Bool evidence$24) {
         return BoolSyntax.boolOps$(this, a, evidence$24);
      }

      public HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
         return HeytingSyntax.heytingOps$(this, a, evidence$22);
      }
   }

   public static class bitString$ implements BitStringSyntax {
      public static final bitString$ MODULE$ = new bitString$();

      static {
         BitStringSyntax.$init$(MODULE$);
      }

      public BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
         return BitStringSyntax.bitStringOps$(this, a, evidence$25);
      }
   }

   public static class partialAction$ implements PartialActionSyntax {
      public static final partialAction$ MODULE$ = new partialAction$();

      static {
         PartialActionSyntax.$init$(MODULE$);
      }

      public LeftPartialActionOps leftPartialActionOps(final Object g) {
         return PartialActionSyntax.leftPartialActionOps$(this, g);
      }

      public RightPartialActionOps rightPartialActionOps(final Object p) {
         return PartialActionSyntax.rightPartialActionOps$(this, p);
      }
   }

   public static class action$ implements ActionSyntax {
      public static final action$ MODULE$ = new action$();

      static {
         ActionSyntax.$init$(MODULE$);
      }

      public LeftActionOps leftActionOps(final Object g) {
         return ActionSyntax.leftActionOps$(this, g);
      }

      public RightActionOps rightActionOps(final Object p) {
         return ActionSyntax.rightActionOps$(this, p);
      }
   }

   public static class torsor$ implements TorsorSyntax {
      public static final torsor$ MODULE$ = new torsor$();

      static {
         TorsorSyntax.$init$(MODULE$);
      }

      public TorsorPointOps torsorPointOps(final Object p) {
         return TorsorSyntax.torsorPointOps$(this, p);
      }
   }

   public static class integral$ implements IntegralSyntax {
      public static final integral$ MODULE$ = new integral$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         ConvertableFromSyntax.$init$(MODULE$);
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
         IntegralSyntax.$init$(MODULE$);
      }

      public IntegralOps integralOps(final Object a, final Integral evidence$28) {
         return IntegralSyntax.integralOps$(this, a, evidence$28);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class fractional$ implements FractionalSyntax {
      public static final fractional$ MODULE$ = new fractional$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         NRootSyntax.$init$(MODULE$);
         ConvertableFromSyntax.$init$(MODULE$);
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }

      public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
         return NRootSyntax.nrootOps$(this, a, evidence$18);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class numeric$ implements NumericSyntax {
      public static final numeric$ MODULE$ = new numeric$();

      static {
         AdditiveSemigroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         NRootSyntax.$init$(MODULE$);
         ConvertableFromSyntax.$init$(MODULE$);
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }

      public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
         return NRootSyntax.nrootOps$(this, a, evidence$18);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }
   }

   public static class all$ implements AllSyntax {
      public static final all$ MODULE$ = new all$();
      private static volatile LiteralsSyntax.radix$ radix$module;
      private static volatile LiteralsSyntax.si$ si$module;
      private static volatile LiteralsSyntax.us$ us$module;
      private static volatile LiteralsSyntax.eu$ eu$module;

      static {
         LiteralsSyntax.$init$(MODULE$);
         CforSyntax.$init$(MODULE$);
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
         TruncatedDivisionSyntax.$init$(MODULE$);
         InvolutionSyntax.$init$(MODULE$);
         IsRealSyntax.$init$(MODULE$);
         ConvertableFromSyntax.$init$(MODULE$);
         SemigroupoidSyntax.$init$(MODULE$);
         GroupoidSyntax.$init$(MODULE$);
         AdditiveSemigroupSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         NRootSyntax.$init$(MODULE$);
         TrigSyntax.$init$(MODULE$);
         IntervalSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         MetricSpaceSyntax.$init$(MODULE$);
         NormedVectorSpaceSyntax.$init$(MODULE$);
         InnerProductSpaceSyntax.$init$(MODULE$);
         CoordinateSpaceSyntax.$init$(MODULE$);
         LatticeSyntax.$init$(MODULE$);
         LogicSyntax.$init$(MODULE$);
         HeytingSyntax.$init$(MODULE$);
         BoolSyntax.$init$(MODULE$);
         BitStringSyntax.$init$(MODULE$);
         PartialActionSyntax.$init$(MODULE$);
         ActionSyntax.$init$(MODULE$);
         TorsorSyntax.$init$(MODULE$);
         IntegralSyntax.$init$(MODULE$);
         IntSyntax.$init$(MODULE$);
         LongSyntax.$init$(MODULE$);
         DoubleSyntax.$init$(MODULE$);
         BigIntSyntax.$init$(MODULE$);
         ArraySyntax.$init$(MODULE$);
         SeqSyntax.$init$(MODULE$);
         SemigroupSyntax.$init$(MODULE$);
         MonoidSyntax.$init$(MODULE$);
         GroupSyntax.$init$(MODULE$);
      }

      public GroupOps groupOps(final Object a, final Group evidence$10) {
         return GroupSyntax.groupOps$(this, a, evidence$10);
      }

      public MonoidOps monoidOps(final Object a, final Monoid ev) {
         return MonoidSyntax.monoidOps$(this, a, ev);
      }

      public SemigroupOps semigroupOps(final Object a, final Semigroup evidence$9) {
         return SemigroupSyntax.semigroupOps$(this, a, evidence$9);
      }

      public SeqOps seqOps(final Iterable lhs) {
         return SeqSyntax.seqOps$(this, lhs);
      }

      public IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
         return SeqSyntax.indexedSeqOps$(this, lhs);
      }

      public ArrayOps arrayOps(final Object lhs) {
         return ArraySyntax.arrayOps$(this, lhs);
      }

      public ArrayOps arrayOps$mZc$sp(final boolean[] lhs) {
         return ArraySyntax.arrayOps$mZc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mBc$sp(final byte[] lhs) {
         return ArraySyntax.arrayOps$mBc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mCc$sp(final char[] lhs) {
         return ArraySyntax.arrayOps$mCc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mDc$sp(final double[] lhs) {
         return ArraySyntax.arrayOps$mDc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mFc$sp(final float[] lhs) {
         return ArraySyntax.arrayOps$mFc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mIc$sp(final int[] lhs) {
         return ArraySyntax.arrayOps$mIc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mJc$sp(final long[] lhs) {
         return ArraySyntax.arrayOps$mJc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mSc$sp(final short[] lhs) {
         return ArraySyntax.arrayOps$mSc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mVc$sp(final BoxedUnit[] lhs) {
         return ArraySyntax.arrayOps$mVc$sp$(this, lhs);
      }

      public BigInt literalBigIntOps(final BigInt b) {
         return BigIntSyntax.literalBigIntOps$(this, b);
      }

      public double literalDoubleOps(final double n) {
         return DoubleSyntax.literalDoubleOps$(this, n);
      }

      public long literalLongOps(final long n) {
         return LongSyntax.literalLongOps$(this, n);
      }

      public int literalIntOps(final int n) {
         return IntSyntax.literalIntOps$(this, n);
      }

      public Object intToA(final int n, final ConvertableTo c) {
         return IntSyntax.intToA$(this, n, c);
      }

      public IntegralOps integralOps(final Object a, final Integral evidence$28) {
         return IntegralSyntax.integralOps$(this, a, evidence$28);
      }

      public TorsorPointOps torsorPointOps(final Object p) {
         return TorsorSyntax.torsorPointOps$(this, p);
      }

      public LeftActionOps leftActionOps(final Object g) {
         return ActionSyntax.leftActionOps$(this, g);
      }

      public RightActionOps rightActionOps(final Object p) {
         return ActionSyntax.rightActionOps$(this, p);
      }

      public LeftPartialActionOps leftPartialActionOps(final Object g) {
         return PartialActionSyntax.leftPartialActionOps$(this, g);
      }

      public RightPartialActionOps rightPartialActionOps(final Object p) {
         return PartialActionSyntax.rightPartialActionOps$(this, p);
      }

      public BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
         return BitStringSyntax.bitStringOps$(this, a, evidence$25);
      }

      public BoolOps boolOps(final Object a, final Bool evidence$24) {
         return BoolSyntax.boolOps$(this, a, evidence$24);
      }

      public HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
         return HeytingSyntax.heytingOps$(this, a, evidence$22);
      }

      public LogicOps logicOps(final Object a, final Logic evidence$23) {
         return LogicSyntax.logicOps$(this, a, evidence$23);
      }

      public MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
         return LatticeSyntax.meetOps$(this, a, evidence$20);
      }

      public JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
         return LatticeSyntax.joinOps$(this, a, evidence$21);
      }

      public CoordinateSpaceOps coordinateSpaceOps(final Object v) {
         return CoordinateSpaceSyntax.coordinateSpaceOps$(this, v);
      }

      public InnerProductSpaceOps innerProductSpaceOps(final Object v) {
         return InnerProductSpaceSyntax.innerProductSpaceOps$(this, v);
      }

      public NormedVectorSpaceOps normedVectorSpaceOps(final Object v) {
         return NormedVectorSpaceSyntax.normedVectorSpaceOps$(this, v);
      }

      public MetricSpaceOps metricSpaceOps(final Object v) {
         return MetricSpaceSyntax.metricSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
         return IntervalSyntax.intervalOps$(this, a, evidence$26, evidence$27);
      }

      public TrigOps trigOps(final Object a, final Trig evidence$19) {
         return TrigSyntax.trigOps$(this, a, evidence$19);
      }

      public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
         return NRootSyntax.nrootOps$(this, a, evidence$18);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }

      public GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
         return GroupoidSyntax.groupoidCommonOps$(this, a, ev, ni);
      }

      public GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
         return GroupoidSyntax.groupoidOps$(this, a, ev);
      }

      public SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
         return SemigroupoidSyntax.semigroupoidOps$(this, a, evidence$8);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }

      public IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
         return IsRealSyntax.isRealOps$(this, a, evidence$7);
      }

      public InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
         return InvolutionSyntax.involutionOps$(this, lhs, evidence$6);
      }

      public TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
         return TruncatedDivisionSyntax.truncatedDivisionOps$(this, a, evidence$5);
      }

      public int literalIntTruncatedDivisionOps(final int lhs) {
         return TruncatedDivisionSyntax.literalIntTruncatedDivisionOps$(this, lhs);
      }

      public long literalLongTruncatedDivisionOps(final long lhs) {
         return TruncatedDivisionSyntax.literalLongTruncatedDivisionOps$(this, lhs);
      }

      public double literalDoubleTruncatedDivisionOps(final double lhs) {
         return TruncatedDivisionSyntax.literalDoubleTruncatedDivisionOps$(this, lhs);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }

      public StringContext literals(final StringContext s) {
         return LiteralsSyntax.literals$(this, s);
      }

      public LiteralsSyntax.radix$ radix() {
         if (radix$module == null) {
            this.radix$lzycompute$2();
         }

         return radix$module;
      }

      public LiteralsSyntax.si$ si() {
         if (si$module == null) {
            this.si$lzycompute$2();
         }

         return si$module;
      }

      public LiteralsSyntax.us$ us() {
         if (us$module == null) {
            this.us$lzycompute$2();
         }

         return us$module;
      }

      public LiteralsSyntax.eu$ eu() {
         if (eu$module == null) {
            this.eu$lzycompute$2();
         }

         return eu$module;
      }

      private final void radix$lzycompute$2() {
         synchronized(this){}

         try {
            if (radix$module == null) {
               radix$module = new LiteralsSyntax.radix$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void si$lzycompute$2() {
         synchronized(this){}

         try {
            if (si$module == null) {
               si$module = new LiteralsSyntax.si$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void us$lzycompute$2() {
         synchronized(this){}

         try {
            if (us$module == null) {
               us$module = new LiteralsSyntax.us$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void eu$lzycompute$2() {
         synchronized(this){}

         try {
            if (eu$module == null) {
               eu$module = new LiteralsSyntax.eu$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }
   }

   public static class allButCats$ implements AllButCatsSyntax {
      public static final allButCats$ MODULE$ = new allButCats$();
      private static volatile LiteralsSyntax.radix$ radix$module;
      private static volatile LiteralsSyntax.si$ si$module;
      private static volatile LiteralsSyntax.us$ us$module;
      private static volatile LiteralsSyntax.eu$ eu$module;

      static {
         LiteralsSyntax.$init$(MODULE$);
         CforSyntax.$init$(MODULE$);
         EqSyntax.$init$(MODULE$);
         PartialOrderSyntax.$init$(MODULE$);
         OrderSyntax.$init$(MODULE$);
         SignedSyntax.$init$(MODULE$);
         TruncatedDivisionSyntax.$init$(MODULE$);
         InvolutionSyntax.$init$(MODULE$);
         IsRealSyntax.$init$(MODULE$);
         ConvertableFromSyntax.$init$(MODULE$);
         SemigroupoidSyntax.$init$(MODULE$);
         GroupoidSyntax.$init$(MODULE$);
         AdditiveSemigroupSyntax.$init$(MODULE$);
         AdditiveMonoidSyntax.$init$(MODULE$);
         AdditiveGroupSyntax.$init$(MODULE$);
         MultiplicativeSemigroupSyntax.$init$(MODULE$);
         MultiplicativeMonoidSyntax.$init$(MODULE$);
         MultiplicativeGroupSyntax.$init$(MODULE$);
         SemiringSyntax.$init$(MODULE$);
         GCDRingSyntax.$init$(MODULE$);
         EuclideanRingSyntax.$init$(MODULE$);
         NRootSyntax.$init$(MODULE$);
         TrigSyntax.$init$(MODULE$);
         IntervalSyntax.$init$(MODULE$);
         LeftModuleSyntax.$init$(MODULE$);
         RightModuleSyntax.$init$(MODULE$);
         VectorSpaceSyntax.$init$(MODULE$);
         MetricSpaceSyntax.$init$(MODULE$);
         NormedVectorSpaceSyntax.$init$(MODULE$);
         InnerProductSpaceSyntax.$init$(MODULE$);
         CoordinateSpaceSyntax.$init$(MODULE$);
         LatticeSyntax.$init$(MODULE$);
         LogicSyntax.$init$(MODULE$);
         HeytingSyntax.$init$(MODULE$);
         BoolSyntax.$init$(MODULE$);
         BitStringSyntax.$init$(MODULE$);
         PartialActionSyntax.$init$(MODULE$);
         ActionSyntax.$init$(MODULE$);
         TorsorSyntax.$init$(MODULE$);
         IntegralSyntax.$init$(MODULE$);
         IntSyntax.$init$(MODULE$);
         LongSyntax.$init$(MODULE$);
         DoubleSyntax.$init$(MODULE$);
         BigIntSyntax.$init$(MODULE$);
         ArraySyntax.$init$(MODULE$);
         SeqSyntax.$init$(MODULE$);
      }

      public SeqOps seqOps(final Iterable lhs) {
         return SeqSyntax.seqOps$(this, lhs);
      }

      public IndexedSeqOps indexedSeqOps(final IndexedSeq lhs) {
         return SeqSyntax.indexedSeqOps$(this, lhs);
      }

      public ArrayOps arrayOps(final Object lhs) {
         return ArraySyntax.arrayOps$(this, lhs);
      }

      public ArrayOps arrayOps$mZc$sp(final boolean[] lhs) {
         return ArraySyntax.arrayOps$mZc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mBc$sp(final byte[] lhs) {
         return ArraySyntax.arrayOps$mBc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mCc$sp(final char[] lhs) {
         return ArraySyntax.arrayOps$mCc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mDc$sp(final double[] lhs) {
         return ArraySyntax.arrayOps$mDc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mFc$sp(final float[] lhs) {
         return ArraySyntax.arrayOps$mFc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mIc$sp(final int[] lhs) {
         return ArraySyntax.arrayOps$mIc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mJc$sp(final long[] lhs) {
         return ArraySyntax.arrayOps$mJc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mSc$sp(final short[] lhs) {
         return ArraySyntax.arrayOps$mSc$sp$(this, lhs);
      }

      public ArrayOps arrayOps$mVc$sp(final BoxedUnit[] lhs) {
         return ArraySyntax.arrayOps$mVc$sp$(this, lhs);
      }

      public BigInt literalBigIntOps(final BigInt b) {
         return BigIntSyntax.literalBigIntOps$(this, b);
      }

      public double literalDoubleOps(final double n) {
         return DoubleSyntax.literalDoubleOps$(this, n);
      }

      public long literalLongOps(final long n) {
         return LongSyntax.literalLongOps$(this, n);
      }

      public int literalIntOps(final int n) {
         return IntSyntax.literalIntOps$(this, n);
      }

      public Object intToA(final int n, final ConvertableTo c) {
         return IntSyntax.intToA$(this, n, c);
      }

      public IntegralOps integralOps(final Object a, final Integral evidence$28) {
         return IntegralSyntax.integralOps$(this, a, evidence$28);
      }

      public TorsorPointOps torsorPointOps(final Object p) {
         return TorsorSyntax.torsorPointOps$(this, p);
      }

      public LeftActionOps leftActionOps(final Object g) {
         return ActionSyntax.leftActionOps$(this, g);
      }

      public RightActionOps rightActionOps(final Object p) {
         return ActionSyntax.rightActionOps$(this, p);
      }

      public LeftPartialActionOps leftPartialActionOps(final Object g) {
         return PartialActionSyntax.leftPartialActionOps$(this, g);
      }

      public RightPartialActionOps rightPartialActionOps(final Object p) {
         return PartialActionSyntax.rightPartialActionOps$(this, p);
      }

      public BitStringOps bitStringOps(final Object a, final BitString evidence$25) {
         return BitStringSyntax.bitStringOps$(this, a, evidence$25);
      }

      public BoolOps boolOps(final Object a, final Bool evidence$24) {
         return BoolSyntax.boolOps$(this, a, evidence$24);
      }

      public HeytingOps heytingOps(final Object a, final Heyting evidence$22) {
         return HeytingSyntax.heytingOps$(this, a, evidence$22);
      }

      public LogicOps logicOps(final Object a, final Logic evidence$23) {
         return LogicSyntax.logicOps$(this, a, evidence$23);
      }

      public MeetOps meetOps(final Object a, final MeetSemilattice evidence$20) {
         return LatticeSyntax.meetOps$(this, a, evidence$20);
      }

      public JoinOps joinOps(final Object a, final JoinSemilattice evidence$21) {
         return LatticeSyntax.joinOps$(this, a, evidence$21);
      }

      public CoordinateSpaceOps coordinateSpaceOps(final Object v) {
         return CoordinateSpaceSyntax.coordinateSpaceOps$(this, v);
      }

      public InnerProductSpaceOps innerProductSpaceOps(final Object v) {
         return InnerProductSpaceSyntax.innerProductSpaceOps$(this, v);
      }

      public NormedVectorSpaceOps normedVectorSpaceOps(final Object v) {
         return NormedVectorSpaceSyntax.normedVectorSpaceOps$(this, v);
      }

      public MetricSpaceOps metricSpaceOps(final Object v) {
         return MetricSpaceSyntax.metricSpaceOps$(this, v);
      }

      public VectorSpaceOps vectorSpaceOps(final Object v) {
         return VectorSpaceSyntax.vectorSpaceOps$(this, v);
      }

      public RightModuleOps rightModuleOps(final Object v) {
         return RightModuleSyntax.rightModuleOps$(this, v);
      }

      public LeftModuleOps leftModuleOps(final Object v) {
         return LeftModuleSyntax.leftModuleOps$(this, v);
      }

      public IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
         return IntervalSyntax.intervalOps$(this, a, evidence$26, evidence$27);
      }

      public TrigOps trigOps(final Object a, final Trig evidence$19) {
         return TrigSyntax.trigOps$(this, a, evidence$19);
      }

      public NRootOps nrootOps(final Object a, final NRoot evidence$18) {
         return NRootSyntax.nrootOps$(this, a, evidence$18);
      }

      public EuclideanRingOps euclideanRingOps(final Object a, final EuclideanRing evidence$17) {
         return EuclideanRingSyntax.euclideanRingOps$(this, a, evidence$17);
      }

      public int literalIntEuclideanRingOps(final int lhs) {
         return EuclideanRingSyntax.literalIntEuclideanRingOps$(this, lhs);
      }

      public long literalLongEuclideanRingOps(final long lhs) {
         return EuclideanRingSyntax.literalLongEuclideanRingOps$(this, lhs);
      }

      public double literalDoubleEuclideanRingOps(final double lhs) {
         return EuclideanRingSyntax.literalDoubleEuclideanRingOps$(this, lhs);
      }

      public GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
         return GCDRingSyntax.gcdRingOps$(this, a, evidence$16);
      }

      public SemiringOps semiringOps(final Object a, final Semiring evidence$15) {
         return SemiringSyntax.semiringOps$(this, a, evidence$15);
      }

      public MultiplicativeGroupOps multiplicativeGroupOps(final Object a, final MultiplicativeGroup evidence$14) {
         return MultiplicativeGroupSyntax.multiplicativeGroupOps$(this, a, evidence$14);
      }

      public int literalIntMultiplicativeGroupOps(final int lhs) {
         return MultiplicativeGroupSyntax.literalIntMultiplicativeGroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeGroupOps(final long lhs) {
         return MultiplicativeGroupSyntax.literalLongMultiplicativeGroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeGroupOps(final double lhs) {
         return MultiplicativeGroupSyntax.literalDoubleMultiplicativeGroupOps$(this, lhs);
      }

      public MultiplicativeMonoidOps multiplicativeMonoidOps(final Object a, final MultiplicativeMonoid ev) {
         return MultiplicativeMonoidSyntax.multiplicativeMonoidOps$(this, a, ev);
      }

      public MultiplicativeSemigroupOps multiplicativeSemigroupOps(final Object a, final MultiplicativeSemigroup evidence$13) {
         return MultiplicativeSemigroupSyntax.multiplicativeSemigroupOps$(this, a, evidence$13);
      }

      public int literalIntMultiplicativeSemigroupOps(final int lhs) {
         return MultiplicativeSemigroupSyntax.literalIntMultiplicativeSemigroupOps$(this, lhs);
      }

      public long literalLongMultiplicativeSemigroupOps(final long lhs) {
         return MultiplicativeSemigroupSyntax.literalLongMultiplicativeSemigroupOps$(this, lhs);
      }

      public double literalDoubleMultiplicativeSemigroupOps(final double lhs) {
         return MultiplicativeSemigroupSyntax.literalDoubleMultiplicativeSemigroupOps$(this, lhs);
      }

      public AdditiveGroupOps additiveGroupOps(final Object a, final AdditiveGroup evidence$12) {
         return AdditiveGroupSyntax.additiveGroupOps$(this, a, evidence$12);
      }

      public int literalIntAdditiveGroupOps(final int lhs) {
         return AdditiveGroupSyntax.literalIntAdditiveGroupOps$(this, lhs);
      }

      public long literalLongAdditiveGroupOps(final long lhs) {
         return AdditiveGroupSyntax.literalLongAdditiveGroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveGroupOps(final double lhs) {
         return AdditiveGroupSyntax.literalDoubleAdditiveGroupOps$(this, lhs);
      }

      public AdditiveMonoidOps additiveMonoidOps(final Object a, final AdditiveMonoid ev) {
         return AdditiveMonoidSyntax.additiveMonoidOps$(this, a, ev);
      }

      public AdditiveSemigroupOps additiveSemigroupOps(final Object a, final AdditiveSemigroup evidence$11) {
         return AdditiveSemigroupSyntax.additiveSemigroupOps$(this, a, evidence$11);
      }

      public int literalIntAdditiveSemigroupOps(final int lhs) {
         return AdditiveSemigroupSyntax.literalIntAdditiveSemigroupOps$(this, lhs);
      }

      public long literalLongAdditiveSemigroupOps(final long lhs) {
         return AdditiveSemigroupSyntax.literalLongAdditiveSemigroupOps$(this, lhs);
      }

      public double literalDoubleAdditiveSemigroupOps(final double lhs) {
         return AdditiveSemigroupSyntax.literalDoubleAdditiveSemigroupOps$(this, lhs);
      }

      public GroupoidCommonOps groupoidCommonOps(final Object a, final Groupoid ev, final NotGiven ni) {
         return GroupoidSyntax.groupoidCommonOps$(this, a, ev, ni);
      }

      public GroupoidOps groupoidOps(final Object a, final Groupoid ev) {
         return GroupoidSyntax.groupoidOps$(this, a, ev);
      }

      public SemigroupoidOps semigroupoidOps(final Object a, final Semigroupoid evidence$8) {
         return SemigroupoidSyntax.semigroupoidOps$(this, a, evidence$8);
      }

      public ConvertableFromOps convertableOps(final Object a, final ConvertableFrom evidence$29) {
         return ConvertableFromSyntax.convertableOps$(this, a, evidence$29);
      }

      public IsRealOps isRealOps(final Object a, final IsReal evidence$7) {
         return IsRealSyntax.isRealOps$(this, a, evidence$7);
      }

      public InvolutionOps involutionOps(final Object lhs, final Involution evidence$6) {
         return InvolutionSyntax.involutionOps$(this, lhs, evidence$6);
      }

      public TruncatedDivisionOps truncatedDivisionOps(final Object a, final TruncatedDivision evidence$5) {
         return TruncatedDivisionSyntax.truncatedDivisionOps$(this, a, evidence$5);
      }

      public int literalIntTruncatedDivisionOps(final int lhs) {
         return TruncatedDivisionSyntax.literalIntTruncatedDivisionOps$(this, lhs);
      }

      public long literalLongTruncatedDivisionOps(final long lhs) {
         return TruncatedDivisionSyntax.literalLongTruncatedDivisionOps$(this, lhs);
      }

      public double literalDoubleTruncatedDivisionOps(final double lhs) {
         return TruncatedDivisionSyntax.literalDoubleTruncatedDivisionOps$(this, lhs);
      }

      public SignedOps signedOps(final Object a, final Signed evidence$4) {
         return SignedSyntax.signedOps$(this, a, evidence$4);
      }

      public OrderOps orderOps(final Object a, final Order evidence$3) {
         return OrderSyntax.orderOps$(this, a, evidence$3);
      }

      public int literalIntOrderOps(final int lhs) {
         return OrderSyntax.literalIntOrderOps$(this, lhs);
      }

      public long literalLongOrderOps(final long lhs) {
         return OrderSyntax.literalLongOrderOps$(this, lhs);
      }

      public double literalDoubleOrderOps(final double lhs) {
         return OrderSyntax.literalDoubleOrderOps$(this, lhs);
      }

      public PartialOrderOps partialOrderOps(final Object a, final PartialOrder evidence$2) {
         return PartialOrderSyntax.partialOrderOps$(this, a, evidence$2);
      }

      public EqOps eqOps(final Object a, final Eq evidence$1) {
         return EqSyntax.eqOps$(this, a, evidence$1);
      }

      public StringContext literals(final StringContext s) {
         return LiteralsSyntax.literals$(this, s);
      }

      public LiteralsSyntax.radix$ radix() {
         if (radix$module == null) {
            this.radix$lzycompute$3();
         }

         return radix$module;
      }

      public LiteralsSyntax.si$ si() {
         if (si$module == null) {
            this.si$lzycompute$3();
         }

         return si$module;
      }

      public LiteralsSyntax.us$ us() {
         if (us$module == null) {
            this.us$lzycompute$3();
         }

         return us$module;
      }

      public LiteralsSyntax.eu$ eu() {
         if (eu$module == null) {
            this.eu$lzycompute$3();
         }

         return eu$module;
      }

      private final void radix$lzycompute$3() {
         synchronized(this){}

         try {
            if (radix$module == null) {
               radix$module = new LiteralsSyntax.radix$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void si$lzycompute$3() {
         synchronized(this){}

         try {
            if (si$module == null) {
               si$module = new LiteralsSyntax.si$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void us$lzycompute$3() {
         synchronized(this){}

         try {
            if (us$module == null) {
               us$module = new LiteralsSyntax.us$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      private final void eu$lzycompute$3() {
         synchronized(this){}

         try {
            if (eu$module == null) {
               eu$module = new LiteralsSyntax.eu$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }
   }

   /** @deprecated */
   public static class unbound$ implements UnboundSyntax {
      public static final unbound$ MODULE$ = new unbound$();

      static {
         UnboundSyntax.$init$(MODULE$);
      }

      public ModuleUnboundOps moduleUnboundOps(final Object f, final CModule ev) {
         return UnboundSyntax.moduleUnboundOps$(this, f, ev);
      }

      public VectorSpaceUnboundOps vectorSpaceUnboundOps(final Object f, final VectorSpace ev) {
         return UnboundSyntax.vectorSpaceUnboundOps$(this, f, ev);
      }

      public ActionUnboundOps groupActionUnboundOps(final Object g, final Action ev) {
         return UnboundSyntax.groupActionUnboundOps$(this, g, ev);
      }

      public AdditiveActionUnboundOps additiveActionUnboundOps(final Object g, final AdditiveAction ev) {
         return UnboundSyntax.additiveActionUnboundOps$(this, g, ev);
      }

      public MultiplicativeActionUnboundOps multiplicativeActionUnboundOps(final Object g, final MultiplicativeAction ev) {
         return UnboundSyntax.multiplicativeActionUnboundOps$(this, g, ev);
      }
   }

   public static class interval$ implements IntervalSyntax {
      public static final interval$ MODULE$ = new interval$();

      static {
         IntervalSyntax.$init$(MODULE$);
      }

      public IntervalPointOps intervalOps(final Object a, final Order evidence$26, final AdditiveGroup evidence$27) {
         return IntervalSyntax.intervalOps$(this, a, evidence$26, evidence$27);
      }
   }
}
