package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SeqFactory;
import scala.collection.View;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005a4qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u00034\u0001\u0011\u0005A\u0007C\u00039\u0001\u0011\u0005\u0013\bC\u0003>\u0001\u0011\u0005a\bC\u0003I\u0001\u0011\u0005\u0011\nC\u0003Q\u0001\u0011\u0005\u0011kB\u0003\\\u0017!\u0005ALB\u0003\u000b\u0017!\u0005Q\fC\u0003f\u000f\u0011\u0005a\rC\u0004h\u000f\u0005\u0005I\u0011\u00025\u0003\u001b%sG-\u001a=fI\n+hMZ3s\u0015\taQ\"A\u0004nkR\f'\r\\3\u000b\u00059y\u0011AC2pY2,7\r^5p]*\t\u0001#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005Mq2C\u0002\u0001\u00151\u001dbs\u0006\u0005\u0002\u0016-5\tq\"\u0003\u0002\u0018\u001f\t1\u0011I\\=SK\u001a\u00042!\u0007\u000e\u001d\u001b\u0005Y\u0011BA\u000e\f\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0016E%\u00111e\u0004\u0002\b\u001d>$\b.\u001b8h!\t)R%\u0003\u0002'\u001f\t\u0019\u0011I\\=\u0011\u000beACDK\u0016\n\u0005%Z!!D%oI\u0016DX\rZ*fc>\u00038\u000f\u0005\u0002\u001a\u0001A\u0019\u0011\u0004\u0001\u000f\u0011\u0007eiC$\u0003\u0002/\u0017\t1!)\u001e4gKJ\u0004B\u0001M\u0019\u001dU5\tQ\"\u0003\u00023\u001b\t9\u0012\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003U\u0002\"!\u0006\u001c\n\u0005]z!\u0001B+oSR\fq\"\u001b;fe\u0006\u0014G.\u001a$bGR|'/_\u000b\u0002uA\u0019\u0001g\u000f\u0016\n\u0005qj!AC*fc\u001a\u000b7\r^8ss\u0006qa\r\\1u\u001b\u0006\u0004\u0018J\u001c)mC\u000e,GCA A\u001b\u0005\u0001\u0001\"B!\u0004\u0001\u0004\u0011\u0015!\u00014\u0011\tU\u0019E$R\u0005\u0003\t>\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0007A2E$\u0003\u0002H\u001b\ta\u0011\n^3sC\ndWm\u00148dK\u0006ia-\u001b7uKJLe\u000e\u00157bG\u0016$\"a\u0010&\t\u000b-#\u0001\u0019\u0001'\u0002\u0003A\u0004B!F\"\u001d\u001bB\u0011QCT\u0005\u0003\u001f>\u0011qAQ8pY\u0016\fg.\u0001\u0007qCR\u001c\u0007.\u00138QY\u0006\u001cW\r\u0006\u0003@%^K\u0006\"B*\u0006\u0001\u0004!\u0016\u0001\u00024s_6\u0004\"!F+\n\u0005Y{!aA%oi\")\u0001,\u0002a\u0001\u000b\u0006)\u0001/\u0019;dQ\")!,\u0002a\u0001)\u0006A!/\u001a9mC\u000e,G-A\u0007J]\u0012,\u00070\u001a3Ck\u001a4WM\u001d\t\u00033\u001d\u0019\"a\u00020\u0011\u0007}\u0013'F\u0004\u00021A&\u0011\u0011-D\u0001\u000b'\u0016\fh)Y2u_JL\u0018BA2e\u0005!!U\r\\3hCR,'BA1\u000e\u0003\u0019a\u0014N\\5u}Q\tA,\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001j!\tQw.D\u0001l\u0015\taW.\u0001\u0003mC:<'\"\u00018\u0002\t)\fg/Y\u0005\u0003a.\u0014aa\u00142kK\u000e$\b\u0006B\u0004skZ\u0004\"!F:\n\u0005Q|!\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019\u0001\u0006\u0002\u0004skZ\u0004"
)
public interface IndexedBuffer extends IndexedSeq, Buffer {
   static Builder newBuilder() {
      return IndexedBuffer$.MODULE$.newBuilder();
   }

   static scala.collection.SeqOps from(final IterableOnce it) {
      return IndexedBuffer$.MODULE$.from(it);
   }

   static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      IndexedBuffer$ var10000 = IndexedBuffer$.MODULE$;
      return x;
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      SeqFactory.Delegate tabulate_this = IndexedBuffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$7$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      SeqFactory.Delegate tabulate_this = IndexedBuffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$5$adapted);
   }

   static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      SeqFactory.Delegate tabulate_this = IndexedBuffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$3$adapted);
   }

   static Object tabulate(final int n1, final int n2, final Function2 f) {
      SeqFactory.Delegate tabulate_this = IndexedBuffer$.MODULE$;
      return tabulate_this.tabulate(n1, IterableFactory::$anonfun$tabulate$1$adapted);
   }

   static Object tabulate(final int n, final Function1 f) {
      return IndexedBuffer$.MODULE$.from(new View.Tabulate(n, f));
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedBuffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$4);
   }

   static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedBuffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$3);
   }

   static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedBuffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$2);
   }

   static Object fill(final int n1, final int n2, final Function0 elem) {
      SeqFactory.Delegate fill_this = IndexedBuffer$.MODULE$;
      return fill_this.fill(n1, IterableFactory::$anonfun$fill$1);
   }

   static Object fill(final int n, final Function0 elem) {
      return IndexedBuffer$.MODULE$.from(new View.Fill(n, elem));
   }

   static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(IndexedBuffer$.MODULE$, start, end, step, evidence$4);
   }

   static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(IndexedBuffer$.MODULE$, start, end, evidence$3);
   }

   static Object unfold(final Object init, final Function1 f) {
      return IndexedBuffer$.MODULE$.from(new View.Unfold(init, f));
   }

   static Object iterate(final Object start, final int len, final Function1 f) {
      return IndexedBuffer$.MODULE$.from(new View.Iterate(start, len, f));
   }

   // $FF: synthetic method
   static SeqFactory iterableFactory$(final IndexedBuffer $this) {
      return $this.iterableFactory();
   }

   default SeqFactory iterableFactory() {
      return IndexedBuffer$.MODULE$;
   }

   // $FF: synthetic method
   static IndexedBuffer flatMapInPlace$(final IndexedBuffer $this, final Function1 f) {
      return $this.flatMapInPlace(f);
   }

   default IndexedBuffer flatMapInPlace(final Function1 f) {
      int i = 0;
      int s = this.length();

      IterableOnce[] newElems;
      for(newElems = new IterableOnce[s]; i < s; ++i) {
         newElems[i] = (IterableOnce)f.apply(this.apply(i));
      }

      this.clear();

      for(int var6 = 0; var6 < s; ++var6) {
         IterableOnce $plus$plus$eq_elems = newElems[var6];
         this.addAll($plus$plus$eq_elems);
         $plus$plus$eq_elems = null;
      }

      return this;
   }

   // $FF: synthetic method
   static IndexedBuffer filterInPlace$(final IndexedBuffer $this, final Function1 p) {
      return $this.filterInPlace(p);
   }

   default IndexedBuffer filterInPlace(final Function1 p) {
      int i = 0;

      int j;
      for(j = 0; i < this.length(); ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(this.apply(i)))) {
            if (i != j) {
               this.update(j, this.apply(i));
            }

            ++j;
         }
      }

      if (i == j) {
         return this;
      } else {
         return (IndexedBuffer)this.takeInPlace(j);
      }
   }

   // $FF: synthetic method
   static IndexedBuffer patchInPlace$(final IndexedBuffer $this, final int from, final IterableOnce patch, final int replaced) {
      return $this.patchInPlace(from, patch, replaced);
   }

   default IndexedBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int replaced0 = Math.min(Math.max(replaced, max_y), this.length());
      var10000 = scala.math.package$.MODULE$;
      var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int i = Math.min(Math.max(from, max_y), this.length());
      int j = 0;

      Iterator iter;
      for(iter = patch.iterator(); iter.hasNext() && j < replaced0 && i + j < this.length(); ++j) {
         this.update(i + j, iter.next());
      }

      if (iter.hasNext()) {
         this.insertAll(i + j, iter);
      } else if (j < replaced0) {
         int var10001 = i + j;
         scala.math.package$ var10002 = scala.math.package$.MODULE$;
         this.remove(var10001, Math.min(replaced0 - j, this.length() - i - j));
      }

      return this;
   }

   static void $init$(final IndexedBuffer $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
