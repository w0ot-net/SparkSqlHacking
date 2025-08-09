package scala.collection.generic;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.IndexedSeq;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.collection.parallel.Combiner;
import scala.collection.parallel.ParIterable;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Numeric.IntIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t=d!\u0002\u000b\u0016\u0003\u0003a\u0002\"\u0002\"\u0001\t\u0003\u0019\u0005\"B#\u0001\t\u00031\u0005\"B+\u0001\t\u00031\u0006\"B+\u0001\t\u00031\u0007\"B+\u0001\t\u0003\u0019\bBB+\u0001\t\u0003\t\u0019\u0001\u0003\u0004V\u0001\u0011\u0005\u00111\u0005\u0005\b\u0003\u000f\u0002A\u0011AA%\u0011\u001d\t9\u0005\u0001C\u0001\u0003CBq!a\u0012\u0001\t\u0003\tY\bC\u0004\u0002H\u0001!\t!!'\t\u000f\u0005\u001d\u0003\u0001\"\u0001\u0002<\"9\u0011\u0011\u001d\u0001\u0005\u0002\u0005\r\bbBAq\u0001\u0011\u0005!1\u0002\u0005\b\u0005K\u0001A\u0011\u0001B\u0014\r\u0019\u0011i\u0004\u0001\u0001\u0003@!1!\t\u0005C\u0001\u00053BqAa\u0018\u0011\t\u0003\u0012\t\u0007C\u0004\u0003`A!\tE!\u001c\u0003\u0015A\u000b'OR1di>\u0014\u0018P\u0003\u0002\u0017/\u00059q-\u001a8fe&\u001c'B\u0001\r\u001a\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u00025\u0005)1oY1mC\u000e\u0001QCA\u000f)'\r\u0001aD\t\t\u0003?\u0001j\u0011!G\u0005\u0003Ce\u0011a!\u00118z%\u00164\u0007cA\u0012%M5\tQ#\u0003\u0002&+\t\u0019r)\u001a8fe&\u001c\u0007+\u0019:D_6\u0004\u0018M\\5p]B\u0011q\u0005\u000b\u0007\u0001\t\u0015I\u0003A1\u0001+\u0005\t\u00195)\u0006\u0002,sE\u0011Af\f\t\u0003?5J!AL\r\u0003\u000f9{G\u000f[5oOJ\u0019\u0001GM \u0007\tE\u0002\u0001a\f\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004gYBT\"\u0001\u001b\u000b\u0005U:\u0012\u0001\u00039be\u0006dG.\u001a7\n\u0005]\"$a\u0003)be&#XM]1cY\u0016\u0004\"aJ\u001d\u0005\u000biB#\u0019A\u001e\u0003\u0003a\u000b\"\u0001\f\u001f\u0011\u0005}i\u0014B\u0001 \u001a\u0005\r\te.\u001f\t\u0005G\u0001Cd%\u0003\u0002B+\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f\u0003\u0019a\u0014N\\5u}Q\tA\tE\u0002$\u0001\u0019\naaY8oG\u0006$XCA$K)\tAE\nE\u0002(Q%\u0003\"a\n&\u0005\u000b-\u0013!\u0019A\u001e\u0003\u0003\u0005CQ!\u0014\u0002A\u00029\u000b1\u0001_:t!\ryr*U\u0005\u0003!f\u0011!\u0002\u0010:fa\u0016\fG/\u001a3?!\r\u00116+S\u0007\u0002/%\u0011Ak\u0006\u0002\t\u0013R,'/\u00192mK\u0006!a-\u001b7m+\t96\f\u0006\u0002YCR\u0011\u0011\f\u0018\t\u0004O!R\u0006CA\u0014\\\t\u0015Y5A1\u0001<\u0011\u0019i6\u0001\"a\u0001=\u0006!Q\r\\3n!\ryrLW\u0005\u0003Af\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\u0006E\u000e\u0001\raY\u0001\u0002]B\u0011q\u0004Z\u0005\u0003Kf\u00111!\u00138u+\t9G\u000eF\u0002i_F$\"![7\u0011\u0007\u001dB#\u000eE\u0002(Q-\u0004\"a\n7\u0005\u000b-#!\u0019A\u001e\t\ru#A\u00111\u0001o!\ryrl\u001b\u0005\u0006a\u0012\u0001\raY\u0001\u0003]FBQA\u001d\u0003A\u0002\r\f!A\u001c\u001a\u0016\u0005QTH\u0003B;~}~$\"A^>\u0011\u0007\u001dBs\u000fE\u0002(Qa\u00042a\n\u0015z!\t9#\u0010B\u0003L\u000b\t\u00071\b\u0003\u0004^\u000b\u0011\u0005\r\u0001 \t\u0004?}K\b\"\u00029\u0006\u0001\u0004\u0019\u0007\"\u0002:\u0006\u0001\u0004\u0019\u0007BBA\u0001\u000b\u0001\u00071-\u0001\u0002ogU!\u0011QAA\n))\t9!!\u0007\u0002\u001c\u0005u\u0011q\u0004\u000b\u0005\u0003\u0013\t)\u0002\u0005\u0003(Q\u0005-\u0001\u0003B\u0014)\u0003\u001b\u0001Ba\n\u0015\u0002\u0010A!q\u0005KA\t!\r9\u00131\u0003\u0003\u0006\u0017\u001a\u0011\ra\u000f\u0005\b;\u001a!\t\u0019AA\f!\u0011yr,!\u0005\t\u000bA4\u0001\u0019A2\t\u000bI4\u0001\u0019A2\t\r\u0005\u0005a\u00011\u0001d\u0011\u0019\t\tC\u0002a\u0001G\u0006\u0011a\u000eN\u000b\u0005\u0003K\t)\u0004\u0006\u0007\u0002(\u0005m\u0012QHA \u0003\u0003\n\u0019\u0005\u0006\u0003\u0002*\u0005]\u0002\u0003B\u0014)\u0003W\u0001Ba\n\u0015\u0002.A!q\u0005KA\u0018!\u00119\u0003&!\r\u0011\t\u001dB\u00131\u0007\t\u0004O\u0005UB!B&\b\u0005\u0004Y\u0004bB/\b\t\u0003\u0007\u0011\u0011\b\t\u0005?}\u000b\u0019\u0004C\u0003q\u000f\u0001\u00071\rC\u0003s\u000f\u0001\u00071\r\u0003\u0004\u0002\u0002\u001d\u0001\ra\u0019\u0005\u0007\u0003C9\u0001\u0019A2\t\r\u0005\u0015s\u00011\u0001d\u0003\tqW'\u0001\u0005uC\n,H.\u0019;f+\u0011\tY%a\u0015\u0015\t\u00055\u0013q\f\u000b\u0005\u0003\u001f\n)\u0006\u0005\u0003(Q\u0005E\u0003cA\u0014\u0002T\u0011)1\n\u0003b\u0001w!9\u0011q\u000b\u0005A\u0002\u0005e\u0013!\u00014\u0011\r}\tYfYA)\u0013\r\ti&\u0007\u0002\n\rVt7\r^5p]FBQA\u0019\u0005A\u0002\r,B!a\u0019\u0002nQ1\u0011QMA<\u0003s\"B!a\u001a\u0002pA!q\u0005KA5!\u00119\u0003&a\u001b\u0011\u0007\u001d\ni\u0007B\u0003L\u0013\t\u00071\bC\u0004\u0002X%\u0001\r!!\u001d\u0011\u000f}\t\u0019hY2\u0002l%\u0019\u0011QO\r\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\"\u00029\n\u0001\u0004\u0019\u0007\"\u0002:\n\u0001\u0004\u0019W\u0003BA?\u0003\u0013#\u0002\"a \u0002\u0014\u0006U\u0015q\u0013\u000b\u0005\u0003\u0003\u000bY\t\u0005\u0003(Q\u0005\r\u0005\u0003B\u0014)\u0003\u000b\u0003Ba\n\u0015\u0002\bB\u0019q%!#\u0005\u000b-S!\u0019A\u001e\t\u000f\u0005]#\u00021\u0001\u0002\u000eBAq$a$dG\u000e\f9)C\u0002\u0002\u0012f\u0011\u0011BR;oGRLwN\\\u001a\t\u000bAT\u0001\u0019A2\t\u000bIT\u0001\u0019A2\t\r\u0005\u0005!\u00021\u0001d+\u0011\tY*!+\u0015\u0015\u0005u\u00151WA[\u0003o\u000bI\f\u0006\u0003\u0002 \u0006-\u0006\u0003B\u0014)\u0003C\u0003Ba\n\u0015\u0002$B!q\u0005KAS!\u00119\u0003&a*\u0011\u0007\u001d\nI\u000bB\u0003L\u0017\t\u00071\bC\u0004\u0002X-\u0001\r!!,\u0011\u0013}\tykY2dG\u0006\u001d\u0016bAAY3\tIa)\u001e8di&|g\u000e\u000e\u0005\u0006a.\u0001\ra\u0019\u0005\u0006e.\u0001\ra\u0019\u0005\u0007\u0003\u0003Y\u0001\u0019A2\t\r\u0005\u00052\u00021\u0001d+\u0011\ti,!4\u0015\u0019\u0005}\u0016q[Am\u00037\fi.a8\u0015\t\u0005\u0005\u0017q\u001a\t\u0005O!\n\u0019\r\u0005\u0003(Q\u0005\u0015\u0007\u0003B\u0014)\u0003\u000f\u0004Ba\n\u0015\u0002JB!q\u0005KAf!\r9\u0013Q\u001a\u0003\u0006\u00172\u0011\ra\u000f\u0005\b\u0003/b\u0001\u0019AAi!)y\u00121[2dG\u000e\u001c\u00171Z\u0005\u0004\u0003+L\"!\u0003$v]\u000e$\u0018n\u001c86\u0011\u0015\u0001H\u00021\u0001d\u0011\u0015\u0011H\u00021\u0001d\u0011\u0019\t\t\u0001\u0004a\u0001G\"1\u0011\u0011\u0005\u0007A\u0002\rDa!!\u0012\r\u0001\u0004\u0019\u0017!\u0002:b]\u001e,W\u0003BAs\u0003[$b!a:\u0003\u0004\t\u001dA\u0003BAu\u0003c\u0004Ba\n\u0015\u0002lB\u0019q%!<\u0005\r\u0005=XB1\u0001<\u0005\u0005!\u0006\"CAz\u001b\u0005\u0005\t9AA{\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003o\fi0a;\u000f\u0007}\tI0C\u0002\u0002|f\tq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u0000\n\u0005!\u0001C%oi\u0016<'/\u00197\u000b\u0007\u0005m\u0018\u0004C\u0004\u0003\u00065\u0001\r!a;\u0002\u000bM$\u0018M\u001d;\t\u000f\t%Q\u00021\u0001\u0002l\u0006\u0019QM\u001c3\u0016\t\t5!Q\u0003\u000b\t\u0005\u001f\u0011iBa\b\u0003\"Q!!\u0011\u0003B\f!\u00119\u0003Fa\u0005\u0011\u0007\u001d\u0012)\u0002\u0002\u0004\u0002p:\u0011\ra\u000f\u0005\n\u00053q\u0011\u0011!a\u0002\u00057\t!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\t90!@\u0003\u0014!9!Q\u0001\bA\u0002\tM\u0001b\u0002B\u0005\u001d\u0001\u0007!1\u0003\u0005\b\u0005Gq\u0001\u0019\u0001B\n\u0003\u0011\u0019H/\u001a9\u0002\u000f%$XM]1uKV!!\u0011\u0006B\u0019)\u0019\u0011YCa\u000e\u0003:Q!!Q\u0006B\u001a!\u00119\u0003Fa\f\u0011\u0007\u001d\u0012\t\u0004B\u0003L\u001f\t\u00071\bC\u0004\u0002X=\u0001\rA!\u000e\u0011\u000f}\tYFa\f\u00030!9!QA\bA\u0002\t=\u0002B\u0002B\u001e\u001f\u0001\u00071-A\u0002mK:\u0014QcR3oKJL7mQ1o\u0007>l'-\u001b8f\rJ|W.\u0006\u0004\u0003B\t5#1K\n\u0005!y\u0011\u0019\u0005E\u0005$\u0005\u000b\u0012IE!\u0015\u0003X%\u0019!qI\u000b\u0003\u001d\r\u000bgnQ8nE&tWM\u0012:p[B!q\u0005\u000bB&!\r9#Q\n\u0003\u0007\u0005\u001f\u0002\"\u0019A\u001e\u0003\t\u0019\u0013x.\u001c\t\u0004O\tMCA\u0002B+!\t\u00071H\u0001\u0002U_B!q\u0005\u000bB))\t\u0011Y\u0006E\u0004\u0003^A\u0011YE!\u0015\u000e\u0003\u0001\tQ!\u00199qYf$BAa\u0019\u0003jA91G!\u001a\u0003R\t]\u0013b\u0001B4i\tA1i\\7cS:,'\u000fC\u0004\u0003lI\u0001\rA!\u0013\u0002\t\u0019\u0014x.\u001c\u000b\u0003\u0005G\u0002"
)
public abstract class ParFactory implements GenericParCompanion {
   public ParIterable empty() {
      return GenericParCompanion.empty$(this);
   }

   public ParIterable apply(final Seq elems) {
      return GenericParCompanion.apply$(this, elems);
   }

   public Factory toFactory() {
      return GenericParCompanion.toFactory$(this);
   }

   public ParIterable concat(final Seq xss) {
      Combiner b = this.newBuilder();
      if (xss.forall((x$1) -> BoxesRunTime.boxToBoolean($anonfun$concat$1(x$1)))) {
         b.sizeHint(BoxesRunTime.unboxToInt(((IterableOnceOps)xss.map((x$2) -> BoxesRunTime.boxToInteger($anonfun$concat$2(x$2)))).sum(.MODULE$)));
      }

      xss.foreach((xs) -> (Combiner)b.$plus$plus$eq(xs));
      return (ParIterable)b.result();
   }

   public ParIterable fill(final int n, final Function0 elem) {
      Combiner b = this.newBuilder();
      b.sizeHint(n);

      for(int i = 0; i < n; ++i) {
         b.$plus$eq(elem.apply());
      }

      return (ParIterable)b.result();
   }

   public ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return this.tabulate(n1, (x$3) -> $anonfun$fill$1(this, n2, elem, BoxesRunTime.unboxToInt(x$3)));
   }

   public ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return this.tabulate(n1, (x$4) -> $anonfun$fill$2(this, n2, n3, elem, BoxesRunTime.unboxToInt(x$4)));
   }

   public ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return this.tabulate(n1, (x$5) -> $anonfun$fill$3(this, n2, n3, n4, elem, BoxesRunTime.unboxToInt(x$5)));
   }

   public ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return this.tabulate(n1, (x$6) -> $anonfun$fill$4(this, n2, n3, n4, n5, elem, BoxesRunTime.unboxToInt(x$6)));
   }

   public ParIterable tabulate(final int n, final Function1 f) {
      Combiner b = this.newBuilder();
      b.sizeHint(n);

      for(int i = 0; i < n; ++i) {
         b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
      }

      return (ParIterable)b.result();
   }

   public ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$1(this, n2, f, BoxesRunTime.unboxToInt(i1)));
   }

   public ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$3(this, n2, n3, f, BoxesRunTime.unboxToInt(i1)));
   }

   public ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$5(this, n2, n3, n4, f, BoxesRunTime.unboxToInt(i1)));
   }

   public ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return this.tabulate(n1, (i1) -> $anonfun$tabulate$7(this, n2, n3, n4, n5, f, BoxesRunTime.unboxToInt(i1)));
   }

   public ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return this.range(start, end, ((Numeric)scala.Predef..MODULE$.implicitly(evidence$1)).one(), evidence$1);
   }

   public ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      Integral num = (Integral)scala.Predef..MODULE$.implicitly(evidence$2);
      if (BoxesRunTime.equals(step, num.zero())) {
         throw new IllegalArgumentException("zero step");
      } else {
         Combiner b = this.newBuilder();
         b.sizeHint(scala.collection.immutable.NumericRange..MODULE$.count(start, end, step, false, evidence$2));
         Object i = start;

         while(true) {
            if (num.mkOrderingOps(step).$less(num.zero())) {
               if (!num.mkOrderingOps(end).$less(i)) {
                  break;
               }
            } else if (!num.mkOrderingOps(i).$less(end)) {
               break;
            }

            b.$plus$eq(i);
            i = num.mkNumericOps(i).$plus(step);
         }

         return (ParIterable)b.result();
      }
   }

   public ParIterable iterate(final Object start, final int len, final Function1 f) {
      Combiner b = this.newBuilder();
      if (len > 0) {
         b.sizeHint(len);
         Object acc = start;
         int i = 1;
         b.$plus$eq(start);

         while(i < len) {
            acc = f.apply(acc);
            ++i;
            b.$plus$eq(acc);
         }
      }

      return (ParIterable)b.result();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$concat$1(final Iterable x$1) {
      return x$1 instanceof IndexedSeq;
   }

   // $FF: synthetic method
   public static final int $anonfun$concat$2(final Iterable x$2) {
      return x$2.size();
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$fill$1(final ParFactory $this, final int n2$1, final Function0 elem$1, final int x$3) {
      return $this.fill(n2$1, elem$1);
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$fill$2(final ParFactory $this, final int n2$2, final int n3$1, final Function0 elem$2, final int x$4) {
      return $this.fill(n2$2, n3$1, elem$2);
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$fill$3(final ParFactory $this, final int n2$3, final int n3$2, final int n4$1, final Function0 elem$3, final int x$5) {
      return $this.fill(n2$3, n3$2, n4$1, elem$3);
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$fill$4(final ParFactory $this, final int n2$4, final int n3$3, final int n4$2, final int n5$1, final Function0 elem$4, final int x$6) {
      return $this.fill(n2$4, n3$3, n4$2, n5$1, elem$4);
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$2(final Function2 f$1, final int i1$1, final int x$7) {
      return f$1.apply(BoxesRunTime.boxToInteger(i1$1), BoxesRunTime.boxToInteger(x$7));
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$tabulate$1(final ParFactory $this, final int n2$5, final Function2 f$1, final int i1) {
      return $this.tabulate(n2$5, (x$7) -> $anonfun$tabulate$2(f$1, i1, BoxesRunTime.unboxToInt(x$7)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$4(final Function3 f$2, final int i1$2, final int x$8, final int x$9) {
      return f$2.apply(BoxesRunTime.boxToInteger(i1$2), BoxesRunTime.boxToInteger(x$8), BoxesRunTime.boxToInteger(x$9));
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$tabulate$3(final ParFactory $this, final int n2$6, final int n3$4, final Function3 f$2, final int i1) {
      return $this.tabulate(n2$6, n3$4, (x$8, x$9) -> $anonfun$tabulate$4(f$2, i1, BoxesRunTime.unboxToInt(x$8), BoxesRunTime.unboxToInt(x$9)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$6(final Function4 f$3, final int i1$3, final int x$10, final int x$11, final int x$12) {
      return f$3.apply(BoxesRunTime.boxToInteger(i1$3), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11), BoxesRunTime.boxToInteger(x$12));
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$tabulate$5(final ParFactory $this, final int n2$7, final int n3$5, final int n4$3, final Function4 f$3, final int i1) {
      return $this.tabulate(n2$7, n3$5, n4$3, (x$10, x$11, x$12) -> $anonfun$tabulate$6(f$3, i1, BoxesRunTime.unboxToInt(x$10), BoxesRunTime.unboxToInt(x$11), BoxesRunTime.unboxToInt(x$12)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$tabulate$8(final Function5 f$4, final int i1$4, final int x$13, final int x$14, final int x$15, final int x$16) {
      return f$4.apply(BoxesRunTime.boxToInteger(i1$4), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16));
   }

   // $FF: synthetic method
   public static final ParIterable $anonfun$tabulate$7(final ParFactory $this, final int n2$8, final int n3$6, final int n4$4, final int n5$2, final Function5 f$4, final int i1) {
      return $this.tabulate(n2$8, n3$6, n4$4, n5$2, (x$13, x$14, x$15, x$16) -> $anonfun$tabulate$8(f$4, i1, BoxesRunTime.unboxToInt(x$13), BoxesRunTime.unboxToInt(x$14), BoxesRunTime.unboxToInt(x$15), BoxesRunTime.unboxToInt(x$16)));
   }

   public ParFactory() {
      GenericParCompanion.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class GenericCanCombineFrom implements CanCombineFrom {
      // $FF: synthetic field
      public final ParFactory $outer;

      public Combiner apply(final ParIterable from) {
         return from.genericCombiner();
      }

      public Combiner apply() {
         return this.scala$collection$generic$ParFactory$GenericCanCombineFrom$$$outer().newBuilder();
      }

      // $FF: synthetic method
      public ParFactory scala$collection$generic$ParFactory$GenericCanCombineFrom$$$outer() {
         return this.$outer;
      }

      public GenericCanCombineFrom() {
         if (ParFactory.this == null) {
            throw null;
         } else {
            this.$outer = ParFactory.this;
            super();
         }
      }
   }
}
