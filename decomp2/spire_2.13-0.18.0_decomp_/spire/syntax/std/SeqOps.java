package spire.syntax.std;

import algebra.ring.AdditiveMonoid;
import algebra.ring.Field;
import algebra.ring.MultiplicativeMonoid;
import algebra.ring.Signed;
import cats.kernel.Monoid;
import cats.kernel.Order;
import cats.kernel.PartialOrder;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime.;
import spire.algebra.NRoot;
import spire.math.QuickSort$;
import spire.math.Searching$;
import spire.math.Selection$;
import spire.math.Sorting$;
import spire.random.Generator;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u001dc\u0001B\r\u001b\u0005\u0005B\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006%\u0002!\ta\u0015\u0005\u0006E\u0002!\ta\u0019\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006]\u0002!\ta\u001c\u0005\b\u0003\u0017\u0001A\u0011AA\u0007\u0011\u001d\t\u0019\u0004\u0001C\u0001\u0003kAq!!\u0012\u0001\t\u0003\t9\u0005C\u0004\u0002L\u0001!\t!!\u0014\t\u000f\u0005]\u0003\u0001\"\u0001\u0002Z!9\u0011Q\f\u0001\u0005\u0002\u0005}\u0003bBA2\u0001\u0011\u0005\u0011Q\r\u0005\t\u0003o\u0002\u0001\u0015\"\u0005\u0002z!A\u0011q\u0013\u0001!\n#\tI\nC\u0004\u0002&\u0002!\t!a*\t\u000f\u0005}\u0006\u0001\"\u0001\u0002B\"9\u00111\u001c\u0001\u0005\u0002\u0005u\u0007bBAw\u0001\u0011\u0005\u0011q\u001e\u0005\b\u0003{\u0004A\u0011AA\u0000\u0011\u001d\u0011Y\u0001\u0001C\u0001\u0005\u001bAqA!\u0007\u0001\t\u0003\u0011Y\u0002C\u0004\u00032\u0001!\tAa\r\t\u000f\t\u0005\u0003\u0001\"\u0001\u0003D\t11+Z9PaNT!a\u0007\u000f\u0002\u0007M$HM\u0003\u0002\u001e=\u000511/\u001f8uCbT\u0011aH\u0001\u0006gBL'/Z\u0002\u0001+\r\u0011s\tL\n\u0003\u0001\r\u0002\"\u0001J\u0014\u000e\u0003\u0015R\u0011AJ\u0001\u0006g\u000e\fG.Y\u0005\u0003Q\u0015\u0012a!\u00118z%\u00164\u0017AA1t!\rYCF\u0012\u0007\u0001\t\u0015i\u0003A1\u0001/\u0005\t\u00195)\u0006\u00020\u0001F\u0011\u0001g\r\t\u0003IEJ!AM\u0013\u0003\u000f9{G\u000f[5oOB\u0019A\u0007P \u000f\u0005URdB\u0001\u001c:\u001b\u00059$B\u0001\u001d!\u0003\u0019a$o\\8u}%\ta%\u0003\u0002<K\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u0005!IE/\u001a:bE2,'BA\u001e&!\tY\u0003\tB\u0003BY\t\u0007!IA\u0001B#\t\u00014\t\u0005\u0002%\t&\u0011Q)\n\u0002\u0004\u0003:L\bCA\u0016H\t%\t\u0005\u0001)A\u0001\u0002\u000b\u0007!\t\u000b\u0002H\u0013B\u0011AES\u0005\u0003\u0017\u0016\u00121b\u001d9fG&\fG.\u001b>fI\u00061A(\u001b8jiz\"\"AT)\u0011\t=\u0003a\tU\u0007\u00025A\u00111\u0006\f\u0005\u0006S\t\u0001\rAK\u0001\u0005cN,X\u000e\u0006\u0002G)\")Qk\u0001a\u0002-\u0006\u0011QM\u001e\t\u0004/~3eB\u0001-^\u001d\tI6L\u0004\u000275&\tq$\u0003\u0002]=\u00059\u0011\r\\4fEJ\f\u0017BA\u001e_\u0015\taf$\u0003\u0002aC\nq\u0011\t\u001a3ji&4X-T8o_&$'BA\u001e_\u0003!\t\bO]8ek\u000e$HC\u0001$e\u0011\u0015)F\u0001q\u0001f!\r9fMR\u0005\u0003O\u0006\u0014A#T;mi&\u0004H.[2bi&4X-T8o_&$\u0017\u0001C9d_6\u0014\u0017N\\3\u0015\u0005\u0019S\u0007\"B+\u0006\u0001\bY\u0007cA,m\r&\u0011Q.\u0019\u0002\u0007\u001b>tw.\u001b3\u0002\u000bEtwN]7\u0015\u0007A\f\t\u0001\u0006\u0003GcVT\b\"B+\u0007\u0001\b\u0011\bcA,t\r&\u0011A/\u0019\u0002\u0006\r&,G\u000e\u001a\u0005\u0006m\u001a\u0001\u001da^\u0001\u0002gB\u0019q\u000b\u001f$\n\u0005e\f'AB*jO:,G\rC\u0003|\r\u0001\u000fA0\u0001\u0002oeB\u0019QP $\u000e\u0003yK!a 0\u0003\u000b9\u0013vn\u001c;\t\u000f\u0005\ra\u00011\u0001\u0002\u0006\u0005\t\u0001\u000fE\u0002%\u0003\u000fI1!!\u0003&\u0005\rIe\u000e^\u0001\nc:|'/\\,ji\",B!a\u0004\u0002\u0018Q!\u0011\u0011CA\u0019)\u0011\t\u0019\"a\n\u0015\u0011\u0005U\u00111DA\u0010\u0003G\u00012aKA\f\t\u0019\tIb\u0002b\u0001\u0005\n\t!\u000b\u0003\u0004V\u000f\u0001\u000f\u0011Q\u0004\t\u0005/N\f)\u0002\u0003\u0004w\u000f\u0001\u000f\u0011\u0011\u0005\t\u0005/b\f)\u0002\u0003\u0004|\u000f\u0001\u000f\u0011Q\u0005\t\u0005{z\f)\u0002C\u0004\u0002*\u001d\u0001\r!a\u000b\u0002\u0003\u0019\u0004b\u0001JA\u0017\r\u0006U\u0011bAA\u0018K\tIa)\u001e8di&|g.\r\u0005\b\u0003\u00079\u0001\u0019AA\u0003\u0003\u0011\u0001X.\u001b8\u0015\t\u0005]\u0012Q\b\t\u0005i\u0005eb)C\u0002\u0002<y\u00121aU3r\u0011\u0019)\u0006\u0002q\u0001\u0002@A!q+!\u0011G\u0013\r\t\u0019%\u0019\u0002\r!\u0006\u0014H/[1m\u001fJ$WM]\u0001\u0005a6\f\u0007\u0010\u0006\u0003\u00028\u0005%\u0003BB+\n\u0001\b\ty$\u0001\u0003r[&tGc\u0001$\u0002P!1QK\u0003a\u0002\u0003#\u0002BaVA*\r&\u0019\u0011QK1\u0003\u000b=\u0013H-\u001a:\u0002\tEl\u0017\r\u001f\u000b\u0004\r\u0006m\u0003BB+\f\u0001\b\t\t&A\u0003r[\u0016\fg\u000eF\u0002G\u0003CBQ!\u0016\u0007A\u0004I\f\u0011\"]7fC:<\u0016\u000e\u001e5\u0016\t\u0005\u001d\u0014Q\u000e\u000b\u0005\u0003S\n\u0019\b\u0006\u0003\u0002l\u0005=\u0004cA\u0016\u0002n\u00111\u0011\u0011D\u0007C\u0002\tCa!V\u0007A\u0004\u0005E\u0004\u0003B,t\u0003WBq!!\u000b\u000e\u0001\u0004\t)\b\u0005\u0004%\u0003[1\u00151N\u0001\nMJ|W.\u0011:sCf$B!a\u001f\u0002\u000eR\u0019!&! \t\u000f\u0005}d\u0002q\u0001\u0002\u0002\u0006\u00191M\u00194\u0011\r\u0005\r\u0015\u0011\u0012$+\u001b\t\t)IC\u0002\u0002\b\u0016\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\tY)!\"\u0003\u000f\u0019\u000b7\r^8ss\"9\u0011q\u0012\bA\u0002\u0005E\u0015aA1seB!A%a%G\u0013\r\t)*\n\u0002\u0006\u0003J\u0014\u0018-_\u0001\u0011MJ|WnU5{K\u0006sG-\u0011:sCf$b!a'\u0002 \u0006\rFc\u0001\u0016\u0002\u001e\"9\u0011qP\bA\u0004\u0005\u0005\u0005bBAQ\u001f\u0001\u0007\u0011QA\u0001\u0005g&TX\rC\u0004\u0002\u0010>\u0001\r!!%\u0002\u000fE\u001cxN\u001d;fIR9!&!+\u0002,\u0006u\u0006BB+\u0011\u0001\b\t\t\u0006C\u0004\u0002.B\u0001\u001d!a,\u0002\u0005\r$\b#BAY\u0003o3e\u0002BAZ\u0003kk\u0011AH\u0005\u0003wyIA!!/\u0002<\nA1\t\\1tgR\u000bwM\u0003\u0002<=!9\u0011q\u0010\tA\u0004\u0005\u0005\u0015!C9t_J$X\r\u001a\"z+\u0011\t\u0019-!4\u0015\t\u0005\u0015\u0017q\u001b\u000b\bU\u0005\u001d\u00171[Ak\u0011\u0019)\u0016\u0003q\u0001\u0002JB)q+a\u0015\u0002LB\u00191&!4\u0005\u0015\u0005=\u0017\u0003)A\u0001\u0002\u000b\u0007!IA\u0001CQ\r\ti-\u0013\u0005\b\u0003[\u000b\u00029AAX\u0011\u001d\ty(\u0005a\u0002\u0003\u0003Cq!!\u000b\u0012\u0001\u0004\tI\u000e\u0005\u0004%\u0003[1\u00151Z\u0001\fcN|'\u000f^3e/&$\b\u000e\u0006\u0003\u0002`\u0006\u0015H#\u0002\u0016\u0002b\u0006\r\bbBAW%\u0001\u000f\u0011q\u0016\u0005\b\u0003\u007f\u0012\u00029AAA\u0011\u001d\tIC\u0005a\u0001\u0003O\u0004r\u0001JAu\r\u001a\u000b)!C\u0002\u0002l\u0016\u0012\u0011BR;oGRLwN\u001c\u001a\u0002\u0013E\u001cX\r\\3di\u0016$G\u0003BAy\u0003s$rAKAz\u0003k\f9\u0010\u0003\u0004V'\u0001\u000f\u0011\u0011\u000b\u0005\b\u0003[\u001b\u00029AAX\u0011\u001d\tyh\u0005a\u0002\u0003\u0003Cq!a?\u0014\u0001\u0004\t)!A\u0001l\u0003!\t8/\u001a7fGR\\G\u0003\u0002B\u0001\u0005\u0013!rA\u000bB\u0002\u0005\u000b\u00119\u0001\u0003\u0004V)\u0001\u000f\u0011\u0011\u000b\u0005\b\u0003[#\u00029AAX\u0011\u001d\ty\b\u0006a\u0002\u0003\u0003Cq!a?\u0015\u0001\u0004\t)!A\u0003ri>\u00048\u000e\u0006\u0003\u0003\u0010\t]Ac\u0002\u0016\u0003\u0012\tM!Q\u0003\u0005\u0007+V\u0001\u001d!!\u0015\t\u000f\u00055V\u0003q\u0001\u00020\"9\u0011qP\u000bA\u0004\u0005\u0005\u0005bBA~+\u0001\u0007\u0011QA\u0001\ncNDWO\u001a4mK\u0012$rA\u000bB\u000f\u0005[\u0011y\u0003C\u0004\u0003 Y\u0001\u001dA!\t\u0002\u0007\u001d,g\u000e\u0005\u0003\u0003$\t%RB\u0001B\u0013\u0015\r\u00119CH\u0001\u0007e\u0006tGm\\7\n\t\t-\"Q\u0005\u0002\n\u000f\u0016tWM]1u_JDq!!,\u0017\u0001\b\ty\u000bC\u0004\u0002\u0000Y\u0001\u001d!!!\u0002\u0011E\u001c\u0018-\u001c9mK\u0012$BA!\u000e\u0003>Q9!Fa\u000e\u0003:\tm\u0002b\u0002B\u0010/\u0001\u000f!\u0011\u0005\u0005\b\u0003[;\u00029AAX\u0011\u001d\tyh\u0006a\u0002\u0003\u0003CqAa\u0010\u0018\u0001\u0004\t)!A\u0001o\u0003\u001d\t8\r[8pg\u0016$2A\u0012B#\u0011\u001d\u0011y\u0002\u0007a\u0002\u0005C\u0001"
)
public class SeqOps {
   public final Iterable spire$syntax$std$SeqOps$$as;

   public Object qsum(final AdditiveMonoid ev) {
      return this.spire$syntax$std$SeqOps$$as.foldLeft(ev.zero(), (x, y) -> ev.plus(x, y));
   }

   public Object qproduct(final MultiplicativeMonoid ev) {
      return this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x, y) -> ev.times(x, y));
   }

   public Object qcombine(final Monoid ev) {
      return this.spire$syntax$std$SeqOps$$as.foldLeft(ev.empty(), (x, y) -> ev.combine(x, y));
   }

   public Object qnorm(final int p, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (x$19, x$20) -> ev.plus(x$19, ev.pow(s.abs(x$20), p))), p);
   }

   public Object qnormWith(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return nr.nroot(this.spire$syntax$std$SeqOps$$as.foldLeft(ev.one(), (t, a) -> ev.plus(t, ev.pow(s.abs(f.apply(a)), p))), p);
   }

   public Seq pmin(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, ev);
   }

   public Seq pmax(final PartialOrder ev) {
      return Searching$.MODULE$.minimalElements(this.spire$syntax$std$SeqOps$$as, spire.algebra.package$.MODULE$.PartialOrder().reverse(ev));
   }

   public Object qmin(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> ev.min(x, y));
      }
   }

   public Object qmax(final Order ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         return this.spire$syntax$std$SeqOps$$as.foldLeft(this.spire$syntax$std$SeqOps$$as.head(), (x, y) -> ev.max(x, y));
      }
   }

   public Object qmean(final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmean$1(ev, mean, i, j, a);
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Object qmeanWith(final Function1 f, final Field ev) {
      if (this.spire$syntax$std$SeqOps$$as.isEmpty()) {
         throw new UnsupportedOperationException("empty seq");
      } else {
         ObjectRef mean = ObjectRef.create(ev.zero());
         IntRef i = IntRef.create(0);
         IntRef j = IntRef.create(1);
         this.spire$syntax$std$SeqOps$$as.foreach((a) -> {
            $anonfun$qmeanWith$1(ev, mean, i, j, f, a);
            return BoxedUnit.UNIT;
         });
         return mean.elem;
      }
   }

   public Iterable fromArray(final Object arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(.MODULE$.array_length(arr));

      for(int index$macro$1 = 0; index$macro$1 < .MODULE$.array_length(arr); ++index$macro$1) {
         b.$plus$eq(.MODULE$.array_apply(arr, index$macro$1));
      }

      return (Iterable)b.result();
   }

   public Iterable fromSizeAndArray(final int size, final Object arr, final Factory cbf) {
      Builder b = cbf.newBuilder();
      b.sizeHint(size);

      for(int index$macro$1 = 0; index$macro$1 < size; ++index$macro$1) {
         b.$plus$eq(.MODULE$.array_apply(arr, index$macro$1));
      }

      return (Iterable)b.result();
   }

   public Iterable qsorted(final Order ev, final ClassTag ct, final Factory cbf) {
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ev, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedWith(final Function2 f, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().from(f);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qselected(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Selection$.MODULE$.select(arr, k, ev, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qselectk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (.MODULE$.array_length(arr) <= k) {
         var10000 = this.fromArray(arr, cbf);
      } else {
         Selection$.MODULE$.select(arr, k, ev, ct);
         var10000 = this.fromSizeAndArray(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qtopk(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Iterable var10000;
      if (.MODULE$.array_length(arr) <= k) {
         Sorting$.MODULE$.sort(arr, ev, ct);
         var10000 = this.fromArray(arr, cbf);
      } else {
         Selection$.MODULE$.select(arr, k, ev, ct);
         QuickSort$.MODULE$.qsort(arr, 0, k, ev, ct);
         var10000 = this.fromSizeAndArray(k, arr, cbf);
      }

      return var10000;
   }

   public Iterable qshuffled(final Generator gen, final ClassTag ct, final Factory cbf) {
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      gen.shuffle(arr, gen);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsampled(final int n, final Generator gen, final ClassTag ct, final Factory cbf) {
      return this.fromArray(gen.sampleFromIterable(this.spire$syntax$std$SeqOps$$as, n, ct, gen), cbf);
   }

   public Object qchoose(final Generator gen) {
      return gen.chooseFromIterable(this.spire$syntax$std$SeqOps$$as, gen);
   }

   public boolean qsum$mcZ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qsum(ev));
   }

   public byte qsum$mcB$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToByte(this.qsum(ev));
   }

   public char qsum$mcC$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToChar(this.qsum(ev));
   }

   public double qsum$mcD$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.qsum(ev));
   }

   public float qsum$mcF$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.qsum(ev));
   }

   public int qsum$mcI$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToInt(this.qsum(ev));
   }

   public long qsum$mcJ$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToLong(this.qsum(ev));
   }

   public short qsum$mcS$sp(final AdditiveMonoid ev) {
      return BoxesRunTime.unboxToShort(this.qsum(ev));
   }

   public void qsum$mcV$sp(final AdditiveMonoid ev) {
      this.qsum(ev);
   }

   public boolean qproduct$mcZ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qproduct(ev));
   }

   public byte qproduct$mcB$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToByte(this.qproduct(ev));
   }

   public char qproduct$mcC$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToChar(this.qproduct(ev));
   }

   public double qproduct$mcD$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToDouble(this.qproduct(ev));
   }

   public float qproduct$mcF$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToFloat(this.qproduct(ev));
   }

   public int qproduct$mcI$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToInt(this.qproduct(ev));
   }

   public long qproduct$mcJ$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToLong(this.qproduct(ev));
   }

   public short qproduct$mcS$sp(final MultiplicativeMonoid ev) {
      return BoxesRunTime.unboxToShort(this.qproduct(ev));
   }

   public void qproduct$mcV$sp(final MultiplicativeMonoid ev) {
      this.qproduct(ev);
   }

   public boolean qcombine$mcZ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToBoolean(this.qcombine(ev));
   }

   public byte qcombine$mcB$sp(final Monoid ev) {
      return BoxesRunTime.unboxToByte(this.qcombine(ev));
   }

   public char qcombine$mcC$sp(final Monoid ev) {
      return BoxesRunTime.unboxToChar(this.qcombine(ev));
   }

   public double qcombine$mcD$sp(final Monoid ev) {
      return BoxesRunTime.unboxToDouble(this.qcombine(ev));
   }

   public float qcombine$mcF$sp(final Monoid ev) {
      return BoxesRunTime.unboxToFloat(this.qcombine(ev));
   }

   public int qcombine$mcI$sp(final Monoid ev) {
      return BoxesRunTime.unboxToInt(this.qcombine(ev));
   }

   public long qcombine$mcJ$sp(final Monoid ev) {
      return BoxesRunTime.unboxToLong(this.qcombine(ev));
   }

   public short qcombine$mcS$sp(final Monoid ev) {
      return BoxesRunTime.unboxToShort(this.qcombine(ev));
   }

   public void qcombine$mcV$sp(final Monoid ev) {
      this.qcombine(ev);
   }

   public boolean qnorm$mcZ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToBoolean(this.qnorm(p, ev, s, nr));
   }

   public byte qnorm$mcB$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToByte(this.qnorm(p, ev, s, nr));
   }

   public char qnorm$mcC$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToChar(this.qnorm(p, ev, s, nr));
   }

   public double qnorm$mcD$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToDouble(this.qnorm(p, ev, s, nr));
   }

   public float qnorm$mcF$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToFloat(this.qnorm(p, ev, s, nr));
   }

   public int qnorm$mcI$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToInt(this.qnorm(p, ev, s, nr));
   }

   public long qnorm$mcJ$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToLong(this.qnorm(p, ev, s, nr));
   }

   public short qnorm$mcS$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      return BoxesRunTime.unboxToShort(this.qnorm(p, ev, s, nr));
   }

   public void qnorm$mcV$sp(final int p, final Field ev, final Signed s, final NRoot nr) {
      this.qnorm(p, ev, s, nr);
   }

   public Object qnormWith$mcZ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcB$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcC$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcD$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcF$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcI$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcJ$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcS$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Object qnormWith$mcV$sp(final int p, final Function1 f, final Field ev, final Signed s, final NRoot nr) {
      return this.qnormWith(p, f, ev, s, nr);
   }

   public Seq pmin$mcZ$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcB$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcC$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcD$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcF$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcI$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcJ$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcS$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmin$mcV$sp(final PartialOrder ev) {
      return this.pmin(ev);
   }

   public Seq pmax$mcZ$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcB$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcC$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcD$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcF$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcI$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcJ$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcS$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public Seq pmax$mcV$sp(final PartialOrder ev) {
      return this.pmax(ev);
   }

   public boolean qmin$mcZ$sp(final Order ev) {
      return BoxesRunTime.unboxToBoolean(this.qmin(ev));
   }

   public byte qmin$mcB$sp(final Order ev) {
      return BoxesRunTime.unboxToByte(this.qmin(ev));
   }

   public char qmin$mcC$sp(final Order ev) {
      return BoxesRunTime.unboxToChar(this.qmin(ev));
   }

   public double qmin$mcD$sp(final Order ev) {
      return BoxesRunTime.unboxToDouble(this.qmin(ev));
   }

   public float qmin$mcF$sp(final Order ev) {
      return BoxesRunTime.unboxToFloat(this.qmin(ev));
   }

   public int qmin$mcI$sp(final Order ev) {
      return BoxesRunTime.unboxToInt(this.qmin(ev));
   }

   public long qmin$mcJ$sp(final Order ev) {
      return BoxesRunTime.unboxToLong(this.qmin(ev));
   }

   public short qmin$mcS$sp(final Order ev) {
      return BoxesRunTime.unboxToShort(this.qmin(ev));
   }

   public void qmin$mcV$sp(final Order ev) {
      this.qmin(ev);
   }

   public boolean qmax$mcZ$sp(final Order ev) {
      return BoxesRunTime.unboxToBoolean(this.qmax(ev));
   }

   public byte qmax$mcB$sp(final Order ev) {
      return BoxesRunTime.unboxToByte(this.qmax(ev));
   }

   public char qmax$mcC$sp(final Order ev) {
      return BoxesRunTime.unboxToChar(this.qmax(ev));
   }

   public double qmax$mcD$sp(final Order ev) {
      return BoxesRunTime.unboxToDouble(this.qmax(ev));
   }

   public float qmax$mcF$sp(final Order ev) {
      return BoxesRunTime.unboxToFloat(this.qmax(ev));
   }

   public int qmax$mcI$sp(final Order ev) {
      return BoxesRunTime.unboxToInt(this.qmax(ev));
   }

   public long qmax$mcJ$sp(final Order ev) {
      return BoxesRunTime.unboxToLong(this.qmax(ev));
   }

   public short qmax$mcS$sp(final Order ev) {
      return BoxesRunTime.unboxToShort(this.qmax(ev));
   }

   public void qmax$mcV$sp(final Order ev) {
      this.qmax(ev);
   }

   public boolean qmean$mcZ$sp(final Field ev) {
      return BoxesRunTime.unboxToBoolean(this.qmean(ev));
   }

   public byte qmean$mcB$sp(final Field ev) {
      return BoxesRunTime.unboxToByte(this.qmean(ev));
   }

   public char qmean$mcC$sp(final Field ev) {
      return BoxesRunTime.unboxToChar(this.qmean(ev));
   }

   public double qmean$mcD$sp(final Field ev) {
      return BoxesRunTime.unboxToDouble(this.qmean(ev));
   }

   public float qmean$mcF$sp(final Field ev) {
      return BoxesRunTime.unboxToFloat(this.qmean(ev));
   }

   public int qmean$mcI$sp(final Field ev) {
      return BoxesRunTime.unboxToInt(this.qmean(ev));
   }

   public long qmean$mcJ$sp(final Field ev) {
      return BoxesRunTime.unboxToLong(this.qmean(ev));
   }

   public short qmean$mcS$sp(final Field ev) {
      return BoxesRunTime.unboxToShort(this.qmean(ev));
   }

   public void qmean$mcV$sp(final Field ev) {
      this.qmean(ev);
   }

   public Object qmeanWith$mcZ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcB$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcC$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcD$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcF$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcI$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcJ$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcS$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Object qmeanWith$mcV$sp(final Function1 f, final Field ev) {
      return this.qmeanWith(f, ev);
   }

   public Iterable fromArray$mcZ$sp(final boolean[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcB$sp(final byte[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcC$sp(final char[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcD$sp(final double[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcF$sp(final float[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcI$sp(final int[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcJ$sp(final long[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcS$sp(final short[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromArray$mcV$sp(final BoxedUnit[] arr, final Factory cbf) {
      return this.fromArray(arr, cbf);
   }

   public Iterable fromSizeAndArray$mcZ$sp(final int size, final boolean[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcB$sp(final int size, final byte[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcC$sp(final int size, final char[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcD$sp(final int size, final double[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcF$sp(final int size, final float[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcI$sp(final int size, final int[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcJ$sp(final int size, final long[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcS$sp(final int size, final short[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable fromSizeAndArray$mcV$sp(final int size, final BoxedUnit[] arr, final Factory cbf) {
      return this.fromSizeAndArray(size, arr, cbf);
   }

   public Iterable qsorted$mcZ$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcB$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcC$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcD$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcF$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcI$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcJ$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcS$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsorted$mcV$sp(final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsorted(ev, ct, cbf);
   }

   public Iterable qsortedBy$mcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mZcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mZcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mZc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mBcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mBcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mBc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mCcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mCcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mCc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mDcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mDcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mDc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mFcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mFcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mFc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mIcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mIcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mIc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mJcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mJcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mJc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mSc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mScZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mScV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mSc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVc$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      Order ord = spire.algebra.package$.MODULE$.Order().by(f, ev);
      Object arr = this.spire$syntax$std$SeqOps$$as.toArray(ct);
      Sorting$.MODULE$.sort(arr, ord, ct);
      return this.fromArray(arr, cbf);
   }

   public Iterable qsortedBy$mVcZ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcB$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcC$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcD$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcF$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcI$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcJ$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcS$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedBy$mVcV$sp(final Function1 f, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qsortedBy$mVc$sp(f, ev, ct, cbf);
   }

   public Iterable qsortedWith$mcZ$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcB$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcC$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcD$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcF$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcI$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcJ$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcS$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qsortedWith$mcV$sp(final Function2 f, final ClassTag ct, final Factory cbf) {
      return this.qsortedWith(f, ct, cbf);
   }

   public Iterable qselected$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselected$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselected(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qselectk$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qselectk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcZ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcB$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcC$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcD$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcF$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcI$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcJ$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcS$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public Iterable qtopk$mcV$sp(final int k, final Order ev, final ClassTag ct, final Factory cbf) {
      return this.qtopk(k, ev, ct, cbf);
   }

   public boolean qchoose$mcZ$sp(final Generator gen) {
      return BoxesRunTime.unboxToBoolean(this.qchoose(gen));
   }

   public byte qchoose$mcB$sp(final Generator gen) {
      return BoxesRunTime.unboxToByte(this.qchoose(gen));
   }

   public char qchoose$mcC$sp(final Generator gen) {
      return BoxesRunTime.unboxToChar(this.qchoose(gen));
   }

   public double qchoose$mcD$sp(final Generator gen) {
      return BoxesRunTime.unboxToDouble(this.qchoose(gen));
   }

   public float qchoose$mcF$sp(final Generator gen) {
      return BoxesRunTime.unboxToFloat(this.qchoose(gen));
   }

   public int qchoose$mcI$sp(final Generator gen) {
      return BoxesRunTime.unboxToInt(this.qchoose(gen));
   }

   public long qchoose$mcJ$sp(final Generator gen) {
      return BoxesRunTime.unboxToLong(this.qchoose(gen));
   }

   public short qchoose$mcS$sp(final Generator gen) {
      return BoxesRunTime.unboxToShort(this.qchoose(gen));
   }

   public void qchoose$mcV$sp(final Generator gen) {
      this.qchoose(gen);
   }

   // $FF: synthetic method
   public static final void $anonfun$qmean$1(final Field ev$8, final ObjectRef mean$1, final IntRef i$1, final IntRef j$1, final Object a) {
      Object t = ev$8.div(ev$8.times(mean$1.elem, ev$8.fromInt(i$1.elem)), ev$8.fromInt(j$1.elem));
      Object z = ev$8.div(a, ev$8.fromInt(j$1.elem));
      mean$1.elem = ev$8.plus(t, z);
      ++i$1.elem;
      ++j$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$qmeanWith$1(final Field ev$9, final ObjectRef mean$2, final IntRef i$2, final IntRef j$2, final Function1 f$2, final Object a) {
      Object t = ev$9.div(ev$9.times(mean$2.elem, ev$9.fromInt(i$2.elem)), ev$9.fromInt(j$2.elem));
      Object z = ev$9.div(f$2.apply(a), ev$9.fromInt(j$2.elem));
      mean$2.elem = ev$9.plus(t, z);
      ++i$2.elem;
      ++j$2.elem;
   }

   public SeqOps(final Iterable as) {
      this.spire$syntax$std$SeqOps$$as = as;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
