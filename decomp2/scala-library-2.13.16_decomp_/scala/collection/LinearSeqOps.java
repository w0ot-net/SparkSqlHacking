package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055ha\u0002\u000e\u001c!\u0003\r\t\u0001\t\u0005\u0006\r\u0002!\ta\u0012\u0005\u0006\u0017\u00021\t\u0005\u0014\u0005\u0006!\u00021\t!\u0015\u0005\u0006%\u00021\ta\u0015\u0005\u0006)\u0002!\t%\u0016\u0005\u00063\u0002!\tA\u0017\u0005\u0006=\u0002!\ta\u0018\u0005\u0006G\u0002!\t%\u0015\u0005\u0006I\u0002!\t%\u001a\u0005\u0006I\u0002!\t\u0005\u001b\u0005\u0006e\u0002!\te\u001d\u0005\u0006m\u0002!\te\u001e\u0005\b\u0003\u0013\u0002A\u0011IA&\u0011\u001d\ty\u0006\u0001C!\u0003CBq!!\u001b\u0001\t\u0003\nY\u0007C\u0004\u0002p\u0001!\t%!\u001d\t\u000f\u0005\u0005\u0005\u0001\"\u0011\u0002\u0004\"9\u0011q\u0011\u0001\u0005B\u0005%\u0005bBAR\u0001\u0011\u0005\u0013Q\u0015\u0005\b\u0003k\u0003A\u0011IA\\\u0011\u001d\ty\f\u0001C!\u0003\u0003Dq!a2\u0001\t\u0003\nI\rC\u0004\u0002R\u0002!\t%a5\t\u000f\u0005]\u0007\u0001\"\u0011\u0002Z\"q\u0011Q\u001c\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002`\u0006-(\u0001\u0004'j]\u0016\f'oU3r\u001fB\u001c(B\u0001\u000f\u001e\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002=\u0005)1oY1mC\u000e\u0001Q\u0003B\u0011-gy\u001a2\u0001\u0001\u0012'!\t\u0019C%D\u0001\u001e\u0013\t)SDA\u0002B]f\u0004Ra\n\u0015+euj\u0011aG\u0005\u0003Sm\u0011aaU3r\u001fB\u001c\bCA\u0016-\u0019\u0001!a!\f\u0001\u0005\u0006\u0004q#!A!\u0012\u0005=\u0012\u0003CA\u00121\u0013\t\tTDA\u0004O_RD\u0017N\\4\u0011\u0005-\u001aDA\u0002\u001b\u0001\t\u000b\u0007QG\u0001\u0002D\u0007V\u0011agO\t\u0003_]\u00022a\n\u001d;\u0013\tI4DA\u0005MS:,\u0017M]*fcB\u00111f\u000f\u0003\u0006yM\u0012\rA\f\u0002\u00021B\u00111F\u0010\u0003\u0007\u007f\u0001!)\u0019\u0001!\u0003\u0003\r\u000b\"aL!\u0013\u0007\t#UI\u0002\u0003D\u0001\u0001\t%\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004cA\u00149UA)q\u0005\u0001\u00163{\u00051A%\u001b8ji\u0012\"\u0012\u0001\u0013\t\u0003G%K!AS\u000f\u0003\tUs\u0017\u000e^\u0001\bSN,U\u000e\u001d;z+\u0005i\u0005CA\u0012O\u0013\tyUDA\u0004C_>dW-\u00198\u0002\t!,\u0017\rZ\u000b\u0002U\u0005!A/Y5m+\u0005i\u0014A\u00035fC\u0012|\u0005\u000f^5p]V\ta\u000bE\u0002$/*J!\u0001W\u000f\u0003\r=\u0003H/[8o\u0003!IG/\u001a:bi>\u0014X#A.\u0011\u0007\u001db&&\u0003\u0002^7\tA\u0011\n^3sCR|'/\u0001\u0004mK:<G\u000f[\u000b\u0002AB\u00111%Y\u0005\u0003Ev\u00111!\u00138u\u0003\u0011a\u0017m\u001d;\u0002\u001b1,gn\u001a;i\u0007>l\u0007/\u0019:f)\t\u0001g\rC\u0003h\u0013\u0001\u0007\u0001-A\u0002mK:$\"\u0001Y5\t\u000b)T\u0001\u0019A6\u0002\tQD\u0017\r\u001e\u0019\u0003YB\u00042aJ7p\u0013\tq7D\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\tY\u0003\u000fB\u0005rS\u0006\u0005\t\u0011!B\u0001]\t\u0019q\fJ\u0019\u0002\u0017%\u001cH)\u001a4j]\u0016$\u0017\t\u001e\u000b\u0003\u001bRDQ!^\u0006A\u0002\u0001\f\u0011\u0001_\u0001\u0006CB\u0004H.\u001f\u000b\u0003UaDQ!\u001f\u0007A\u0002\u0001\f\u0011A\u001c\u0015\u0005\u0019m\fY\u0001E\u0002$yzL!!`\u000f\u0003\rQD'o\\<t!\ry\u0018Q\u0001\b\u0004G\u0005\u0005\u0011bAA\u0002;\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0004\u0003\u0013\u0011\u0011$\u00138eKb|U\u000f^(g\u0005>,h\u000eZ:Fq\u000e,\u0007\u000f^5p]*\u0019\u00111A\u000f2\u000fy\ti!a\t\u0002HA!\u0011qBA\u000f\u001d\u0011\t\t\"!\u0007\u0011\u0007\u0005MQ$\u0004\u0002\u0002\u0016)\u0019\u0011qC\u0010\u0002\rq\u0012xn\u001c;?\u0013\r\tY\"H\u0001\u0007!J,G-\u001a4\n\t\u0005}\u0011\u0011\u0005\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005mQ$M\u0005$\u0003K\ti#!\u0010\u00020U!\u0011qEA\u0015+\t\ti\u0001B\u0004\u0002,}\u0011\r!!\u000e\u0003\u0003QKA!a\f\u00022\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIER1!a\r\u001e\u0003\u0019!\bN]8xgF\u0019q&a\u000e\u0011\u0007}\fI$\u0003\u0003\u0002<\u0005%!!\u0003+ie><\u0018M\u00197fc%\u0019\u0013qHA!\u0003\u0007\n\u0019DD\u0002$\u0003\u0003J1!a\r\u001ec\u0015\u00113%HA#\u0005\u0015\u00198-\u00197bc\t1c0A\u0004g_J,\u0017m\u00195\u0016\t\u00055\u00131\f\u000b\u0004\u0011\u0006=\u0003bBA)\u001b\u0001\u0007\u00111K\u0001\u0002MB11%!\u0016+\u00033J1!a\u0016\u001e\u0005%1UO\\2uS>t\u0017\u0007E\u0002,\u00037\"a!!\u0018\u000e\u0005\u0004q#!A+\u0002\r\u0019|'/\u00197m)\ri\u00151\r\u0005\b\u0003Kr\u0001\u0019AA4\u0003\u0005\u0001\b#B\u0012\u0002V)j\u0015AB3ySN$8\u000fF\u0002N\u0003[Bq!!\u001a\u0010\u0001\u0004\t9'\u0001\u0005d_:$\u0018-\u001b8t+\u0011\t\u0019(a\u001f\u0015\u00075\u000b)\bC\u0004\u0002xA\u0001\r!!\u001f\u0002\t\u0015dW-\u001c\t\u0004W\u0005mDaBA?!\t\u0007\u0011q\u0010\u0002\u0003\u0003F\n\"A\u000b\u0012\u0002\t\u0019Lg\u000e\u001a\u000b\u0004-\u0006\u0015\u0005bBA3#\u0001\u0007\u0011qM\u0001\tM>dG\rT3giV!\u00111RAI)\u0011\ti)a(\u0015\t\u0005=\u0015Q\u0013\t\u0004W\u0005EEABAJ%\t\u0007aFA\u0001C\u0011\u001d\t9J\u0005a\u0001\u00033\u000b!a\u001c9\u0011\u0011\r\nY*a$+\u0003\u001fK1!!(\u001e\u0005%1UO\\2uS>t'\u0007C\u0004\u0002\"J\u0001\r!a$\u0002\u0003i\fAb]1nK\u0016cW-\\3oiN,B!a*\u00024R\u0019Q*!+\t\r)\u001c\u0002\u0019AAV!\u00159\u0013QVAY\u0013\r\tyk\u0007\u0002\r\u0013R,'/\u00192mK>s7-\u001a\t\u0004W\u0005MFaBAJ'\t\u0007\u0011qP\u0001\u000eg\u0016<W.\u001a8u\u0019\u0016tw\r\u001e5\u0015\u000b\u0001\fI,a/\t\u000f\u0005\u0015D\u00031\u0001\u0002h!1\u0011Q\u0018\u000bA\u0002\u0001\fAA\u001a:p[\u0006Q\u0011N\u001c3fq^CWM]3\u0015\u000b\u0001\f\u0019-!2\t\u000f\u0005\u0015T\u00031\u0001\u0002h!1\u0011QX\u000bA\u0002\u0001\fa\u0002\\1ti&sG-\u001a=XQ\u0016\u0014X\rF\u0003a\u0003\u0017\fi\rC\u0004\u0002fY\u0001\r!a\u001a\t\r\u0005=g\u00031\u0001a\u0003\r)g\u000eZ\u0001\tM&tG\rT1tiR\u0019a+!6\t\u000f\u0005\u0015t\u00031\u0001\u0002h\u0005)A/Y5mgV\u0011\u00111\u001c\t\u0004Oqk\u0014AE:va\u0016\u0014He]1nK\u0016cW-\\3oiN,B!!9\u0002jR\u0019Q*a9\t\r)L\u0002\u0019AAs!\u00159\u0013QVAt!\rY\u0013\u0011\u001e\u0003\b\u0003'K\"\u0019AA@\u0013\r\t\u0019\u000b\u000b"
)
public interface LinearSeqOps extends SeqOps {
   // $FF: synthetic method
   boolean scala$collection$LinearSeqOps$$super$sameElements(final IterableOnce that);

   boolean isEmpty();

   Object head();

   LinearSeq tail();

   // $FF: synthetic method
   static Option headOption$(final LinearSeqOps $this) {
      return $this.headOption();
   }

   default Option headOption() {
      return (Option)(this.isEmpty() ? None$.MODULE$ : new Some(this.head()));
   }

   // $FF: synthetic method
   static Iterator iterator$(final LinearSeqOps $this) {
      return $this.iterator();
   }

   default Iterator iterator() {
      if (this.knownSize() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new LinearSeqIterator(this);
      }
   }

   // $FF: synthetic method
   static int length$(final LinearSeqOps $this) {
      return $this.length();
   }

   default int length() {
      LinearSeq these = (LinearSeq)this.coll();

      int len;
      for(len = 0; these.nonEmpty(); these = (LinearSeq)these.tail()) {
         ++len;
      }

      return len;
   }

   // $FF: synthetic method
   static Object last$(final LinearSeqOps $this) {
      return $this.last();
   }

   default Object last() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("LinearSeq.last");
      } else {
         LinearSeq these = (LinearSeq)this.coll();

         for(LinearSeq scout = (LinearSeq)this.tail(); scout.nonEmpty(); scout = (LinearSeq)scout.tail()) {
            these = scout;
         }

         return these.head();
      }
   }

   // $FF: synthetic method
   static int lengthCompare$(final LinearSeqOps $this, final int len) {
      return $this.lengthCompare(len);
   }

   default int lengthCompare(final int len) {
      return len < 0 ? 1 : this.loop$1(0, (LinearSeq)this.coll(), len);
   }

   // $FF: synthetic method
   static int lengthCompare$(final LinearSeqOps $this, final Iterable that) {
      return $this.lengthCompare(that);
   }

   default int lengthCompare(final Iterable that) {
      int thatKnownSize = that.knownSize();
      if (thatKnownSize >= 0) {
         return this.lengthCompare(thatKnownSize);
      } else if (that instanceof LinearSeq) {
         LinearSeq var3 = (LinearSeq)that;
         LinearSeqOps thisSeq = this;

         LinearSeq thatSeq;
         for(thatSeq = var3; thisSeq.nonEmpty() && thatSeq.nonEmpty(); thatSeq = (LinearSeq)thatSeq.tail()) {
            thisSeq = (LinearSeqOps)thisSeq.tail();
         }

         return Boolean.compare(thisSeq.nonEmpty(), thatSeq.nonEmpty());
      } else {
         LinearSeqOps thisSeq = this;
         Iterator thatIt = that.iterator();

         while(thisSeq.nonEmpty() && thatIt.hasNext()) {
            thisSeq = (LinearSeqOps)thisSeq.tail();
            thatIt.next();
         }

         return Boolean.compare(thisSeq.nonEmpty(), thatIt.hasNext());
      }
   }

   // $FF: synthetic method
   static boolean isDefinedAt$(final LinearSeqOps $this, final int x) {
      return $this.isDefinedAt(x);
   }

   default boolean isDefinedAt(final int x) {
      return x >= 0 && this.lengthCompare(x) > 0;
   }

   // $FF: synthetic method
   static Object apply$(final LinearSeqOps $this, final int n) {
      return $this.apply(n);
   }

   default Object apply(final int n) throws IndexOutOfBoundsException {
      if (n < 0) {
         throw new IndexOutOfBoundsException(Integer.toString(n));
      } else {
         LinearSeq skipped = (LinearSeq)this.drop(n);
         if (skipped.isEmpty()) {
            throw new IndexOutOfBoundsException(Integer.toString(n));
         } else {
            return skipped.head();
         }
      }
   }

   // $FF: synthetic method
   static void foreach$(final LinearSeqOps $this, final Function1 f) {
      $this.foreach(f);
   }

   default void foreach(final Function1 f) {
      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         f.apply(these.head());
      }

   }

   // $FF: synthetic method
   static boolean forall$(final LinearSeqOps $this, final Function1 p) {
      return $this.forall(p);
   }

   default boolean forall(final Function1 p) {
      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         if (!BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return false;
         }
      }

      return true;
   }

   // $FF: synthetic method
   static boolean exists$(final LinearSeqOps $this, final Function1 p) {
      return $this.exists(p);
   }

   default boolean exists(final Function1 p) {
      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean contains$(final LinearSeqOps $this, final Object elem) {
      return $this.contains(elem);
   }

   default boolean contains(final Object elem) {
      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         if (BoxesRunTime.equals(these.head(), elem)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static Option find$(final LinearSeqOps $this, final Function1 p) {
      return $this.find(p);
   }

   default Option find(final Function1 p) {
      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return new Some(these.head());
         }
      }

      return None$.MODULE$;
   }

   // $FF: synthetic method
   static Object foldLeft$(final LinearSeqOps $this, final Object z, final Function2 op) {
      return $this.foldLeft(z, op);
   }

   default Object foldLeft(final Object z, final Function2 op) {
      Object acc = z;

      for(LinearSeq these = (LinearSeq)this.coll(); !these.isEmpty(); these = (LinearSeq)these.tail()) {
         acc = op.apply(acc, these.head());
      }

      return acc;
   }

   // $FF: synthetic method
   static boolean sameElements$(final LinearSeqOps $this, final IterableOnce that) {
      return $this.sameElements(that);
   }

   default boolean sameElements(final IterableOnce that) {
      if (that instanceof LinearSeq) {
         LinearSeq var2 = (LinearSeq)that;
         return this.linearSeqEq$1((LinearSeq)this.coll(), var2);
      } else {
         return this.scala$collection$LinearSeqOps$$super$sameElements(that);
      }
   }

   // $FF: synthetic method
   static int segmentLength$(final LinearSeqOps $this, final Function1 p, final int from) {
      return $this.segmentLength(p, from);
   }

   default int segmentLength(final Function1 p, final int from) {
      int i = 0;

      for(LinearSeq seq = (LinearSeq)this.drop(from); seq.nonEmpty() && BoxesRunTime.unboxToBoolean(p.apply(seq.head())); seq = (LinearSeq)seq.tail()) {
         ++i;
      }

      return i;
   }

   // $FF: synthetic method
   static int indexWhere$(final LinearSeqOps $this, final Function1 p, final int from) {
      return $this.indexWhere(p, from);
   }

   default int indexWhere(final Function1 p, final int from) {
      scala.math.package$ var10000 = scala.math.package$.MODULE$;
      int max_y = 0;
      int i = Math.max(from, max_y);

      for(LinearSeq these = (LinearSeq)this.drop(from); these.nonEmpty(); these = (LinearSeq)these.tail()) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            return i;
         }

         ++i;
      }

      return -1;
   }

   // $FF: synthetic method
   static int lastIndexWhere$(final LinearSeqOps $this, final Function1 p, final int end) {
      return $this.lastIndexWhere(p, end);
   }

   default int lastIndexWhere(final Function1 p, final int end) {
      int i = 0;
      LinearSeq these = (LinearSeq)this.coll();

      int last;
      for(last = -1; !these.isEmpty() && i <= end; ++i) {
         if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
            last = i;
         }

         these = (LinearSeq)these.tail();
      }

      return last;
   }

   // $FF: synthetic method
   static Option findLast$(final LinearSeqOps $this, final Function1 p) {
      return $this.findLast(p);
   }

   default Option findLast(final Function1 p) {
      LinearSeq these = (LinearSeq)this.coll();
      boolean found = false;

      Object last;
      for(last = null; these.nonEmpty(); these = (LinearSeq)these.tail()) {
         Object elem = these.head();
         if (BoxesRunTime.unboxToBoolean(p.apply(elem))) {
            found = true;
            last = elem;
         }
      }

      if (found) {
         return new Some(last);
      } else {
         return None$.MODULE$;
      }
   }

   // $FF: synthetic method
   static Iterator tails$(final LinearSeqOps $this) {
      return $this.tails();
   }

   default Iterator tails() {
      Iterator$ var10000 = Iterator$.MODULE$;
      Object single_a = this.empty();
      AbstractIterator var9 = new AbstractIterator(single_a) {
         private boolean consumed;
         private final Object a$1;

         public boolean hasNext() {
            return !this.consumed;
         }

         public Object next() {
            if (this.consumed) {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            } else {
               this.consumed = true;
               return this.a$1;
            }
         }

         public Iterator sliceIterator(final int from, final int until) {
            if (!this.consumed && from <= 0 && until != 0) {
               return this;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty;
            }
         }

         public {
            this.a$1 = a$1;
            this.consumed = false;
         }
      };
      single_a = null;
      Iterator end = var9;
      Iterator$ var10 = Iterator$.MODULE$;
      var10 = (Iterator$)this.coll();
      Function1 iterate_f = (x$1) -> (LinearSeq)x$1.tail();
      Object iterate_start = var10;
      Iterator var12 = new AbstractIterator(iterate_start, iterate_f) {
         private boolean first;
         private Object acc;
         private final Function1 f$6;

         public boolean hasNext() {
            return true;
         }

         public Object next() {
            if (this.first) {
               this.first = false;
            } else {
               this.acc = this.f$6.apply(this.acc);
            }

            return this.acc;
         }

         public {
            this.f$6 = f$6;
            this.first = true;
            this.acc = start$3;
         }
      };
      iterate_start = null;
      iterate_f = null;
      var12 = var12.takeWhile((x$2) -> BoxesRunTime.boxToBoolean($anonfun$tails$2(x$2)));
      Function0 $plus$plus_xs = () -> end;
      if (var12 == null) {
         throw null;
      } else {
         return var12.concat($plus$plus_xs);
      }
   }

   private int loop$1(final int i, final LinearSeq xs, final int len$1) {
      while(i != len$1) {
         if (xs.isEmpty()) {
            return -1;
         }

         int var10000 = i + 1;
         xs = (LinearSeq)xs.tail();
         i = var10000;
      }

      if (xs.isEmpty()) {
         return 0;
      } else {
         return 1;
      }
   }

   private boolean linearSeqEq$1(final LinearSeq a, final LinearSeq b) {
      while(a != b) {
         if (!a.nonEmpty() || !b.nonEmpty() || !BoxesRunTime.equals(a.head(), b.head())) {
            if (a.isEmpty() && b.isEmpty()) {
               break;
            }

            return false;
         }

         LinearSeq var10000 = (LinearSeq)a.tail();
         b = (LinearSeq)b.tail();
         a = var10000;
      }

      return true;
   }

   // $FF: synthetic method
   static boolean $anonfun$tails$2(final LinearSeq x$2) {
      return x$2.nonEmpty();
   }

   static void $init$(final LinearSeqOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
