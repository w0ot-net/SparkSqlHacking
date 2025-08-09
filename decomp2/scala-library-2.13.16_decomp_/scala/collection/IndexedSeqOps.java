package scala.collection;

import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.convert.impl.AnyIndexedSeqStepper;
import scala.collection.convert.impl.DoubleIndexedSeqStepper;
import scala.collection.convert.impl.IntIndexedSeqStepper;
import scala.collection.convert.impl.LongIndexedSeqStepper;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015baB\u000e\u001d!\u0003\r\t!\t\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u0002!\te\u0012\u0005\u0006U\u0002!\tE\u0011\u0005\u0006W\u0002!I\u0001\u001c\u0005\b\u0003\u001b\u0001A\u0011IA\b\u0011\u001d\ty\u0002\u0001C!\u0003CAq!a\b\u0001\t\u0003\nI\u0003C\u0004\u0002H\u0001!\t&!\u0013\t\u000f\u0005E\u0003\u0001\"\u0011\u0002T!9\u00111\r\u0001\u0005B\u0005\u0015\u0004bBA6\u0001\u0011\u0005\u0013Q\u000e\u0005\b\u0003c\u0002A\u0011IA:\u0011\u001d\t9\b\u0001C!\u0003sBq!! \u0001\t\u0003\ny\bC\u0004\u0002\u0014\u0002!\t%!&\t\u000f\u0005]\u0005\u0001\"\u0011\u0002\u001a\"9\u0011q\u0014\u0001\u0005B\u0005\u0005\u0006bBAR\u0001\u0011\u0005\u0013Q\u0015\u0005\b\u0003[\u0003A\u0011IAQ\u0011\u001d\ty\u000b\u0001C#\u0003cCq!a.\u0001\t\u0003\nI\fC\u0004\u00020\u0002!)%a/\t\u000f\u0005-\u0007\u0001\"\u0011\u0002N\"9\u00111\u001a\u0001\u0005B\u0005]\b\u0002\u0003B\u0007\u0001\u0001&IAa\u0004\u0003\u001b%sG-\u001a=fIN+\u0017o\u00149t\u0015\tib$\u0001\u0006d_2dWm\u0019;j_:T\u0011aH\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u0011\u0011S\u0006\u000e\u001e\u0014\u0007\u0001\u0019s\u0005\u0005\u0002%K5\ta$\u0003\u0002'=\t\u0019\u0011I\\=\u0011\u000b!J3fM\u001d\u000e\u0003qI!A\u000b\u000f\u0003\rM+\u0017o\u00149t!\taS\u0006\u0004\u0001\u0005\r9\u0002AQ1\u00010\u0005\u0005\t\u0015C\u0001\u0019$!\t!\u0013'\u0003\u00023=\t9aj\u001c;iS:<\u0007C\u0001\u00175\t\u0019)\u0004\u0001\"b\u0001m\t\u00111iQ\u000b\u0003_]\"Q\u0001\u000f\u001bC\u0002=\u0012Aa\u0018\u0013%cA\u0011AF\u000f\u0003\u0007w\u0001!)\u0019A\u0018\u0003\u0003\r\u000ba\u0001J5oSR$C#\u0001 \u0011\u0005\u0011z\u0014B\u0001!\u001f\u0005\u0011)f.\u001b;\u0002\u0011%$XM]1u_J,\u0012a\u0011\t\u0004Q\u0011[\u0013BA#\u001d\u0005!IE/\u001a:bi>\u0014\u0018aB:uKB\u0004XM]\u000b\u0003\u00116#\"!S3\u0013\u0007)cuK\u0002\u0003L\u0001\u0001I%\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004C\u0001\u0017N\t\u0015q5A1\u0001P\u0005\u0005\u0019\u0016C\u0001\u0019Qa\t\tV\u000bE\u0002)%RK!a\u0015\u000f\u0003\u000fM#X\r\u001d9feB\u0011A&\u0016\u0003\n-6\u000b\t\u0011!A\u0003\u0002=\u00121a\u0018\u00132!\tA&M\u0004\u0002ZA:\u0011!l\u0018\b\u00037zk\u0011\u0001\u0018\u0006\u0003;\u0002\na\u0001\u0010:p_Rt\u0014\"A\u0010\n\u0005uq\u0012BA1\u001d\u0003\u001d\u0019F/\u001a9qKJL!a\u00193\u0003\u001d\u00153g-[2jK:$8\u000b\u001d7ji*\u0011\u0011\r\b\u0005\u0006M\u000e\u0001\u001daZ\u0001\u0006g\"\f\u0007/\u001a\t\u0005Q!\\C*\u0003\u0002j9\ta1\u000b^3qa\u0016\u00148\u000b[1qK\u0006y!/\u001a<feN,\u0017\n^3sCR|'/A\u0003g_2$'/\u0006\u0002n_R)a.\u001d<yuB\u0011Af\u001c\u0003\u0006a\u0016\u0011\ra\f\u0002\u0002\u0005\")!/\u0002a\u0001g\u0006)1\u000f^1siB\u0011A\u0005^\u0005\u0003kz\u00111!\u00138u\u0011\u00159X\u00011\u0001t\u0003\r)g\u000e\u001a\u0005\u0006s\u0016\u0001\rA\\\u0001\u0002u\")10\u0002a\u0001y\u0006\u0011q\u000e\u001d\t\u0006Iu\\cN\\\u0005\u0003}z\u0011\u0011BR;oGRLwN\u001c\u001a)\u0007\u0015\t\t\u0001\u0005\u0003\u0002\u0004\u0005%QBAA\u0003\u0015\r\t9AH\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0006\u0003\u000b\u0011q\u0001^1jYJ,7-A\u0005g_2$'+[4iiV!\u0011\u0011CA\f)\u0011\t\u0019\"!\b\u0015\t\u0005U\u0011\u0011\u0004\t\u0004Y\u0005]A!\u00029\u0007\u0005\u0004y\u0003BB>\u0007\u0001\u0004\tY\u0002E\u0004%{.\n)\"!\u0006\t\re4\u0001\u0019AA\u000b\u0003\u00111\u0018.Z<\u0016\u0005\u0005\r\u0002\u0003\u0002\u0015\u0002&-J1!a\n\u001d\u00059Ie\u000eZ3yK\u0012\u001cV-\u001d,jK^$b!a\t\u0002,\u0005=\u0002BBA\u0017\u0011\u0001\u00071/\u0001\u0003ge>l\u0007BBA\u0019\u0011\u0001\u00071/A\u0003v]RLG\u000eK\u0006\t\u0003k\tY$!\u0010\u0002B\u0005\r\u0003c\u0001\u0013\u00028%\u0019\u0011\u0011\b\u0010\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005}\u0012AO+tK\u0002rc/[3x]Md\u0017nY3)MJ|W\u000e\f\u0011v]RLG.\u000b\u0011j]N$X-\u00193!_\u001a\u0004cF^5fo\"2'o\\7-AUtG/\u001b7*\u0003\u0015\u0019\u0018N\\2fC\t\t)%\u0001\u00043]E\u001ad\u0006M\u0001\te\u00164XM]:fIV\u0011\u00111\n\t\u0005Q\u000553&C\u0002\u0002Pq\u0011\u0001\"\u0013;fe\u0006\u0014G.Z\u0001\naJ,\u0007/\u001a8eK\u0012,B!!\u0016\u0002\\Q!\u0011qKA0!\u0011aC'!\u0017\u0011\u00071\nY\u0006\u0002\u0004q\u0015\t\u0007\u0011QL\t\u0003W\rBq!!\u0019\u000b\u0001\u0004\tI&\u0001\u0003fY\u0016l\u0017\u0001\u0002;bW\u0016$2!OA4\u0011\u0019\tIg\u0003a\u0001g\u0006\ta.A\u0005uC.,'+[4iiR\u0019\u0011(a\u001c\t\r\u0005%D\u00021\u0001t\u0003\u0011!'o\u001c9\u0015\u0007e\n)\b\u0003\u0004\u0002j5\u0001\ra]\u0001\nIJ|\u0007OU5hQR$2!OA>\u0011\u0019\tIG\u0004a\u0001g\u0006\u0019Q.\u00199\u0016\t\u0005\u0005\u0015q\u0011\u000b\u0005\u0003\u0007\u000bI\t\u0005\u0003-i\u0005\u0015\u0005c\u0001\u0017\u0002\b\u0012)\u0001o\u0004b\u0001_!9\u00111R\bA\u0002\u00055\u0015!\u00014\u0011\r\u0011\nyiKAC\u0013\r\t\tJ\b\u0002\n\rVt7\r^5p]F\nqA]3wKJ\u001cX-F\u0001:\u0003\u0015\u0019H.[2f)\u0015I\u00141TAO\u0011\u0019\ti#\u0005a\u0001g\"1\u0011\u0011G\tA\u0002M\fA\u0001[3bIV\t1&\u0001\u0006iK\u0006$w\n\u001d;j_:,\"!a*\u0011\t\u0011\nIkK\u0005\u0004\u0003Ws\"AB(qi&|g.\u0001\u0003mCN$\u0018!\u00047f]\u001e$\bnQ8na\u0006\u0014X\rF\u0002t\u0003gCa!!.\u0016\u0001\u0004\u0019\u0018a\u00017f]\u0006I1N\\8x]NK'0Z\u000b\u0002gR\u00191/!0\t\u000f\u0005}v\u00031\u0001\u0002B\u0006!A\u000f[1ua\u0011\t\u0019-a2\u0011\u000b!\ni%!2\u0011\u00071\n9\rB\u0006\u0002J\u0006u\u0016\u0011!A\u0001\u0006\u0003y#aA0%k\u000511/Z1sG\",B!a4\u0002tR!\u0011\u0011[A{)\u0011\t\u0019.!9\u0011\t\u0005U\u00171\u001c\b\u00043\u0006]\u0017bAAm9\u0005I1+Z1sG\"LgnZ\u0005\u0005\u0003;\fyN\u0001\u0007TK\u0006\u00148\r\u001b*fgVdGOC\u0002\u0002ZrAq!a9\u0019\u0001\b\t)/A\u0002pe\u0012\u0004b!a:\u0002n\u0006EXBAAu\u0015\r\tYOH\u0001\u0005[\u0006$\b.\u0003\u0003\u0002p\u0006%(\u0001C(sI\u0016\u0014\u0018N\\4\u0011\u00071\n\u0019\u0010\u0002\u0004q1\t\u0007\u0011Q\f\u0005\b\u0003CB\u0002\u0019AAy+\u0011\tIPa\u0001\u0015\u0011\u0005m(Q\u0001B\u0004\u0005\u0013!B!a5\u0002~\"9\u00111]\rA\u0004\u0005}\bCBAt\u0003[\u0014\t\u0001E\u0002-\u0005\u0007!a\u0001]\rC\u0002\u0005u\u0003bBA13\u0001\u0007!\u0011\u0001\u0005\u0007\u0003[I\u0002\u0019A:\t\r\t-\u0011\u00041\u0001t\u0003\t!x.\u0001\u0007cS:\f'/_*fCJ\u001c\u0007.\u0006\u0003\u0003\u0012\tmA\u0003\u0003B\n\u0005;\u0011yB!\t\u0015\t\u0005M'Q\u0003\u0005\b\u0003GT\u00029\u0001B\f!\u0019\t9/!<\u0003\u001aA\u0019AFa\u0007\u0005\rAT\"\u0019AA/\u0011\u001d\t\tG\u0007a\u0001\u00053Aa!!\f\u001b\u0001\u0004\u0019\bB\u0002B\u00065\u0001\u00071\u000fK\u0002\u001b\u0003\u0003\u0001"
)
public interface IndexedSeqOps extends SeqOps {
   // $FF: synthetic method
   static Iterator iterator$(final IndexedSeqOps $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      return this.view().iterator();
   }

   // $FF: synthetic method
   static Stepper stepper$(final IndexedSeqOps $this, final StepperShape shape) {
      return $this.stepper(shape);
   }

   default Stepper stepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntIndexedSeqStepper(this, 0, this.length());
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongIndexedSeqStepper(this, 0, this.length());
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleIndexedSeqStepper(this, 0, this.length()) : shape.parUnbox(new AnyIndexedSeqStepper(this, 0, this.length())));
      }
   }

   // $FF: synthetic method
   static Iterator reverseIterator$(final IndexedSeqOps $this) {
      return $this.reverseIterator();
   }

   default Iterator reverseIterator() {
      return this.view().reverseIterator();
   }

   private Object foldr(final int start, final int end, final Object z, final Function2 op) {
      while(start != end) {
         int var10001 = end - 1;
         Object var10002 = op.apply(this.apply(end - 1), z);
         op = op;
         z = var10002;
         end = var10001;
         start = start;
      }

      return z;
   }

   // $FF: synthetic method
   static Object foldRight$(final IndexedSeqOps $this, final Object z, final Function2 op) {
      return $this.foldRight(z, op);
   }

   default Object foldRight(final Object z, final Function2 op) {
      int var10001 = this.length();
      Object foldr_z = z;
      int foldr_end = var10001;

      for(int foldr_start = 0; foldr_start != foldr_end; foldr_start = foldr_start) {
         var10001 = foldr_end - 1;
         foldr_z = op.apply(this.apply(foldr_end - 1), foldr_z);
         foldr_end = var10001;
      }

      return foldr_z;
   }

   // $FF: synthetic method
   static IndexedSeqView view$(final IndexedSeqOps $this) {
      return $this.view();
   }

   default IndexedSeqView view() {
      return new IndexedSeqView.Id(this);
   }

   // $FF: synthetic method
   static IndexedSeqView view$(final IndexedSeqOps $this, final int from, final int until) {
      return $this.view(from, until);
   }

   /** @deprecated */
   default IndexedSeqView view(final int from, final int until) {
      return this.view().slice(from, until);
   }

   // $FF: synthetic method
   static Iterable reversed$(final IndexedSeqOps $this) {
      return $this.reversed();
   }

   default Iterable reversed() {
      return new IndexedSeqView.Reverse(this);
   }

   // $FF: synthetic method
   static Object prepended$(final IndexedSeqOps $this, final Object elem) {
      return $this.prepended(elem);
   }

   default Object prepended(final Object elem) {
      return this.iterableFactory().from(new IndexedSeqView.Prepended(elem, this));
   }

   // $FF: synthetic method
   static Object take$(final IndexedSeqOps $this, final int n) {
      return $this.take(n);
   }

   default Object take(final int n) {
      return this.fromSpecific(new IndexedSeqView.Take(this, n));
   }

   // $FF: synthetic method
   static Object takeRight$(final IndexedSeqOps $this, final int n) {
      return $this.takeRight(n);
   }

   default Object takeRight(final int n) {
      return this.fromSpecific(new IndexedSeqView.TakeRight(this, n));
   }

   // $FF: synthetic method
   static Object drop$(final IndexedSeqOps $this, final int n) {
      return $this.drop(n);
   }

   default Object drop(final int n) {
      return this.fromSpecific(new IndexedSeqView.Drop(this, n));
   }

   // $FF: synthetic method
   static Object dropRight$(final IndexedSeqOps $this, final int n) {
      return $this.dropRight(n);
   }

   default Object dropRight(final int n) {
      return this.fromSpecific(new IndexedSeqView.DropRight(this, n));
   }

   // $FF: synthetic method
   static Object map$(final IndexedSeqOps $this, final Function1 f) {
      return $this.map(f);
   }

   default Object map(final Function1 f) {
      return this.iterableFactory().from(new IndexedSeqView.Map(this, f));
   }

   // $FF: synthetic method
   static Object reverse$(final IndexedSeqOps $this) {
      return $this.reverse();
   }

   default Object reverse() {
      return this.fromSpecific(new IndexedSeqView.Reverse(this));
   }

   // $FF: synthetic method
   static Object slice$(final IndexedSeqOps $this, final int from, final int until) {
      return $this.slice(from, until);
   }

   default Object slice(final int from, final int until) {
      return this.fromSpecific(new IndexedSeqView.Slice(this, from, until));
   }

   // $FF: synthetic method
   static Object head$(final IndexedSeqOps $this) {
      return $this.head();
   }

   default Object head() {
      if (!this.isEmpty()) {
         return this.apply(0);
      } else {
         throw new NoSuchElementException((new StringBuilder(14)).append("head of empty ").append(this instanceof IndexedSeq ? ((IndexedSeq)this).className() : this.toString()).toString());
      }
   }

   // $FF: synthetic method
   static Option headOption$(final IndexedSeqOps $this) {
      return $this.headOption();
   }

   default Option headOption() {
      return (Option)(this.isEmpty() ? None$.MODULE$ : new Some(this.head()));
   }

   // $FF: synthetic method
   static Object last$(final IndexedSeqOps $this) {
      return $this.last();
   }

   default Object last() {
      if (!this.isEmpty()) {
         return this.apply(this.length() - 1);
      } else {
         throw new NoSuchElementException((new StringBuilder(14)).append("last of empty ").append(this instanceof IndexedSeq ? ((IndexedSeq)this).className() : this.toString()).toString());
      }
   }

   // $FF: synthetic method
   static int lengthCompare$(final IndexedSeqOps $this, final int len) {
      return $this.lengthCompare(len);
   }

   default int lengthCompare(final int len) {
      return Integer.compare(this.length(), len);
   }

   // $FF: synthetic method
   static int knownSize$(final IndexedSeqOps $this) {
      return $this.knownSize();
   }

   default int knownSize() {
      return this.length();
   }

   // $FF: synthetic method
   static int lengthCompare$(final IndexedSeqOps $this, final Iterable that) {
      return $this.lengthCompare(that);
   }

   default int lengthCompare(final Iterable that) {
      int res = that.sizeCompare(this.length());
      return res == Integer.MIN_VALUE ? 1 : -res;
   }

   // $FF: synthetic method
   static Searching.SearchResult search$(final IndexedSeqOps $this, final Object elem, final Ordering ord) {
      return $this.search(elem, ord);
   }

   default Searching.SearchResult search(final Object elem, final Ordering ord) {
      return this.binarySearch(elem, 0, this.length(), ord);
   }

   // $FF: synthetic method
   static Searching.SearchResult search$(final IndexedSeqOps $this, final Object elem, final int from, final int to, final Ordering ord) {
      return $this.search(elem, from, to, ord);
   }

   default Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return this.binarySearch(elem, from, to, ord);
   }

   private Searching.SearchResult binarySearch(final Object elem, final int from, final int to, final Ordering ord) {
      while(true) {
         if (from < 0) {
            ord = ord;
            to = to;
            from = 0;
            elem = elem;
         } else if (to > this.length()) {
            int var10002 = this.length();
            ord = ord;
            to = var10002;
            from = from;
            elem = elem;
         } else {
            if (to <= from) {
               return new Searching.InsertionPoint(from);
            }

            int idx = from + (to - from - 1) / 2;
            scala.math.package$ var10000 = scala.math.package$.MODULE$;
            switch (Integer.signum(ord.compare(elem, this.apply(idx)))) {
               case -1:
                  ord = ord;
                  to = idx;
                  from = from;
                  elem = elem;
                  break;
               case 1:
                  int var10001 = idx + 1;
                  ord = ord;
                  to = to;
                  from = var10001;
                  elem = elem;
                  break;
               default:
                  return new Searching.Found(idx);
            }
         }
      }
   }

   static void $init$(final IndexedSeqOps $this) {
   }
}
