package scala.util.parsing.input;

import java.io.File;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Option;
import scala.collection.AbstractSeq;
import scala.collection.IndexedSeq;
import scala.collection.IndexedSeqOps;
import scala.collection.IndexedSeqView;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.mutable.StringBuilder;
import scala.io.Source;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=x!B\u0012%\u0011\u0003ic!B\u0018%\u0011\u0003\u0001\u0004\"B\u001b\u0002\t\u00031\u0004bB\u001c\u0002\u0005\u0004%)\u0001\u000f\u0005\u0007w\u0005\u0001\u000bQB\u001d\t\u000bq\nA\u0011A\u001f\t\u000f\u0005u\u0014\u0001\"\u0001\u0002\u0000!9\u0011\u0011T\u0001\u0005\u0002\u0005m\u0005bBAM\u0003\u0011\u0005\u0011\u0011\u0016\u0005\b\u0003_\u000bA\u0011AAY\u0011\u001d\ty+\u0001C\u0001\u0003kCq!!/\u0002\t\u0003\tY\fC\u0004\u0002P\u0006!\t!!5\t\u000f\u0005=\u0017\u0001\"\u0001\u0002\\\"9\u0011q\\\u0001\u0005\u0002\u0005\u0005h\u0001B\u0018%\u0001\u0005C\u0001BW\b\u0003\u0002\u0003\u0006Ia\u0017\u0005\tI>\u0011\t\u0011)A\u0005K\"A\u0001n\u0004B\u0001B\u0003%\u0011\r\u0003\u0005j\u001f\t\u0005\t\u0015!\u0003b\u0011!QwBaA!\u0002\u0017Y\u0007\"B\u001b\u0010\t#\t\b\"B\u001b\u0010\t\u0003I\bb\u0002@\u0010\u0001\u0004%Ia \u0005\n\u0003\u0003y\u0001\u0019!C\u0005\u0003\u0007Aq!a\u0004\u0010A\u0003&Q\r\u0003\u0004\u0002\u0012=!Ia \u0005\b\u0003'yA\u0011BA\u000b\u0011\u001d\t9b\u0004C\u0005\u00033Aq!a\b\u0010\t\u0003\t\t\u0003C\u0004\u0002$=!\t!!\n\t\u000f\u0005-r\u0002\"\u0011\u0002.!9\u0011qG\b\u0005B\u0005e\u0002bBA\u001c\u001f\u0011\u0005\u00111\t\u0005\b\u0003\u000fzA\u0011IA%\u0003!\u0001\u0016mZ3e'\u0016\f(BA\u0013'\u0003\u0015Ig\u000e];u\u0015\t9\u0003&A\u0004qCJ\u001c\u0018N\\4\u000b\u0005%R\u0013\u0001B;uS2T\u0011aK\u0001\u0006g\u000e\fG.Y\u0002\u0001!\tq\u0013!D\u0001%\u0005!\u0001\u0016mZ3e'\u0016\f8CA\u00012!\t\u00114'D\u0001+\u0013\t!$F\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\nq\"\u00168eKR,'/\\5oK\u0012,e\u000eZ\u000b\u0002s=\t!(\b\u0003\u0000\u007f\u0000\u0000 \u0001E+oI\u0016$XM]7j]\u0016$WI\u001c3!\u000311'o\\7Ji\u0016\u0014\u0018\r^8s+\rq\u00141\r\u000b\u0004\u007f\u0005-Dc\u0001!\u0002fA!afDA1+\t\u00115j\u0005\u0003\u0010\u0007R;\u0006c\u0001#H\u00136\tQI\u0003\u0002GU\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005!+%aC!cgR\u0014\u0018m\u0019;TKF\u0004\"AS&\r\u0001\u0011)Aj\u0004b\u0001\u001b\n\tA+\u0005\u0002O#B\u0011!gT\u0005\u0003!*\u0012qAT8uQ&tw\r\u0005\u00023%&\u00111K\u000b\u0002\u0004\u0003:L\bc\u0001#V\u0013&\u0011a+\u0012\u0002\u000b\u0013:$W\r_3e'\u0016\f\bc\u0001\u0018Y\u0013&\u0011\u0011\f\n\u0002\u001d'\u000e\fG.\u0019,feNLwN\\*qK\u000eLg-[2QC\u001e,GmU3r\u0003\u0011iwN]3\u0011\rIbf,Y1b\u0013\ti&FA\u0005Gk:\u001cG/[8ogA\u0019!gX%\n\u0005\u0001T#!B!se\u0006L\bC\u0001\u001ac\u0013\t\u0019'FA\u0002J]R\faAZ5sgR\f\u0004c\u0001\u0018g\u0013&\u0011q\r\n\u0002\u0005!\u0006<W-A\u0003ti\u0006\u0014H/A\u0002f]\u0012\f!\"\u001a<jI\u0016t7-\u001a\u00134!\raw.S\u0007\u0002[*\u0011aNK\u0001\be\u00164G.Z2u\u0013\t\u0001XN\u0001\u0005DY\u0006\u001c8\u000fV1h)\u0015\u0011XO^<y)\t\u0019H\u000fE\u0002/\u001f%CQA[\u000bA\u0004-DQAW\u000bA\u0002mCQ\u0001Z\u000bA\u0002\u0015DQ\u0001[\u000bA\u0002\u0005DQ![\u000bA\u0002\u0005$\"A_?\u0015\u0005M\\\bb\u0002?\u0017\u0003\u0003\u0005\u001da[\u0001\u000bKZLG-\u001a8dK\u0012\"\u0004\"\u0002.\u0017\u0001\u0004Y\u0016aB2veJ,g\u000e^\u000b\u0002K\u0006Y1-\u001e:sK:$x\fJ3r)\u0011\t)!a\u0003\u0011\u0007I\n9!C\u0002\u0002\n)\u0012A!\u00168ji\"A\u0011Q\u0002\r\u0002\u0002\u0003\u0007Q-A\u0002yIE\n\u0001bY;se\u0016tG\u000fI\u0001\u0007Y\u0006$Xm\u001d;\u0002\u000f\u0005$G-T8sKR\tQ-\u0001\u0003qC\u001e,GcA3\u0002\u001c!1\u0011Q\u0004\u000fA\u0002\u0005\f\u0001\"\u00192tS:$W\r_\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0003\u0005\fQ!\u00199qYf$2!SA\u0014\u0011\u0019\tIC\ba\u0001C\u0006)\u0011N\u001c3fq\u0006Y\u0011n\u001d#fM&tW\rZ!u)\u0011\ty#!\u000e\u0011\u0007I\n\t$C\u0002\u00024)\u0012qAQ8pY\u0016\fg\u000e\u0003\u0004\u0002*}\u0001\r!Y\u0001\u0006g2L7-\u001a\u000b\u0006g\u0006m\u0012q\b\u0005\u0007\u0003{\u0001\u0003\u0019A1\u0002\r}\u001bH/\u0019:u\u0011\u0019\t\t\u0005\ta\u0001C\u0006!q,\u001a8e)\r\u0019\u0018Q\t\u0005\u0006Q\u0006\u0002\r!Y\u0001\ti>\u001cFO]5oOR\u0011\u00111\n\t\u0005\u0003\u001b\nYF\u0004\u0003\u0002P\u0005]\u0003cAA)U5\u0011\u00111\u000b\u0006\u0004\u0003+b\u0013A\u0002\u001fs_>$h(C\u0002\u0002Z)\na\u0001\u0015:fI\u00164\u0017\u0002BA/\u0003?\u0012aa\u0015;sS:<'bAA-UA\u0019!*a\u0019\u0005\u000b1+!\u0019A'\t\u0013\u0005\u001dT!!AA\u0004\u0005%\u0014AC3wS\u0012,gnY3%cA!An\\A1\u0011\u001d\ti'\u0002a\u0001\u0003_\naa]8ve\u000e,\u0007CBA9\u0003o\n\tGD\u00023\u0003gJ1!!\u001e+\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u001f\u0002|\tA\u0011\n^3sCR|'OC\u0002\u0002v)\nAB\u001a:p[&#XM]1cY\u0016,B!!!\u0002\nR!\u00111QAI)\u0011\t))a#\u0011\t9z\u0011q\u0011\t\u0004\u0015\u0006%E!\u0002'\u0007\u0005\u0004i\u0005\"CAG\r\u0005\u0005\t9AAH\u0003))g/\u001b3f]\u000e,GE\r\t\u0005Y>\f9\tC\u0004\u0002n\u0019\u0001\r!a%\u0011\r\u0005E\u0014QSAD\u0013\u0011\t9*a\u001f\u0003\u0011%#XM]1cY\u0016\f1B\u001a:p[N#(/\u001b8hgR!\u0011QTAS!\u0011qs\"a(\u0011\u0007I\n\t+C\u0002\u0002$*\u0012Aa\u00115be\"9\u0011QN\u0004A\u0002\u0005\u001d\u0006CBA9\u0003o\nY\u0005\u0006\u0003\u0002\u001e\u0006-\u0006bBA7\u0011\u0001\u0007\u0011Q\u0016\t\u0007\u0003c\n)*a\u0013\u0002\u0013\u0019\u0014x.\u001c'j]\u0016\u001cH\u0003BAO\u0003gCq!!\u001c\n\u0001\u0004\t9\u000b\u0006\u0003\u0002\u001e\u0006]\u0006bBA7\u0015\u0001\u0007\u0011QV\u0001\u000bMJ|WNU3bI\u0016\u0014H\u0003BAO\u0003{Cq!!\u001c\f\u0001\u0004\ty\f\u0005\u0003\u0002B\u0006-WBAAb\u0015\u0011\t)-a2\u0002\u0005%|'BAAe\u0003\u0011Q\u0017M^1\n\t\u00055\u00171\u0019\u0002\u0007%\u0016\fG-\u001a:\u0002\u0011\u0019\u0014x.\u001c$jY\u0016$B!!(\u0002T\"9\u0011Q\u000e\u0007A\u0002\u0005U\u0007\u0003BAa\u0003/LA!!7\u0002D\n!a)\u001b7f)\u0011\ti*!8\t\u000f\u00055T\u00021\u0001\u0002L\u0005QaM]8n'>,(oY3\u0015\t\u0005u\u00151\u001d\u0005\b\u0003[r\u0001\u0019AAs!\u0011\t9/a;\u000e\u0005\u0005%(bAAcU%!\u0011Q^Au\u0005\u0019\u0019v.\u001e:dK\u0002"
)
public class PagedSeq extends AbstractSeq implements IndexedSeq, ScalaVersionSpecificPagedSeq {
   private final Function3 more;
   private final Page first1;
   private final int start;
   private final int end;
   private final ClassTag evidence$3;
   private Page current;

   public static PagedSeq fromSource(final Source source) {
      return PagedSeq$.MODULE$.fromSource(source);
   }

   public static PagedSeq fromFile(final String source) {
      return PagedSeq$.MODULE$.fromFile(source);
   }

   public static PagedSeq fromFile(final File source) {
      return PagedSeq$.MODULE$.fromFile(source);
   }

   public static PagedSeq fromReader(final java.io.Reader source) {
      return PagedSeq$.MODULE$.fromReader(source);
   }

   public static PagedSeq fromLines(final Iterable source) {
      return PagedSeq$.MODULE$.fromLines(source);
   }

   public static PagedSeq fromLines(final Iterator source) {
      return PagedSeq$.MODULE$.fromLines(source);
   }

   public static PagedSeq fromStrings(final Iterable source) {
      return PagedSeq$.MODULE$.fromStrings(source);
   }

   public static PagedSeq fromStrings(final Iterator source) {
      return PagedSeq$.MODULE$.fromStrings(source);
   }

   public static PagedSeq fromIterable(final Iterable source, final ClassTag evidence$2) {
      return PagedSeq$.MODULE$.fromIterable(source, evidence$2);
   }

   public static PagedSeq fromIterator(final Iterator source, final ClassTag evidence$1) {
      return PagedSeq$.MODULE$.fromIterator(source, evidence$1);
   }

   public static int UndeterminedEnd() {
      return PagedSeq$.MODULE$.UndeterminedEnd();
   }

   public SeqFactory iterableFactory() {
      return ScalaVersionSpecificPagedSeq.iterableFactory$(this);
   }

   public String stringPrefix() {
      return IndexedSeq.stringPrefix$(this);
   }

   public Iterator iterator() {
      return IndexedSeqOps.iterator$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return IndexedSeqOps.stepper$(this, shape);
   }

   public Iterator reverseIterator() {
      return IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return IndexedSeqOps.view$(this, from, until);
   }

   public Iterable reversed() {
      return IndexedSeqOps.reversed$(this);
   }

   public Object prepended(final Object elem) {
      return IndexedSeqOps.prepended$(this, elem);
   }

   public Object take(final int n) {
      return IndexedSeqOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IndexedSeqOps.takeRight$(this, n);
   }

   public Object drop(final int n) {
      return IndexedSeqOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IndexedSeqOps.dropRight$(this, n);
   }

   public Object map(final Function1 f) {
      return IndexedSeqOps.map$(this, f);
   }

   public Object reverse() {
      return IndexedSeqOps.reverse$(this);
   }

   public Object head() {
      return IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final Iterable that) {
      return IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   private Page current() {
      return this.current;
   }

   private void current_$eq(final Page x$1) {
      this.current = x$1;
   }

   private Page latest() {
      return this.first1.latest();
   }

   private Page addMore() {
      return this.latest().addMore(this.more);
   }

   private Page page(final int absindex) {
      if (absindex < this.current().start()) {
         this.current_$eq(this.first1);
      }

      while(absindex >= this.current().end() && this.current().next() != null) {
         this.current_$eq(this.current().next());
      }

      while(absindex >= this.current().end() && !this.current().isLast()) {
         this.current_$eq(this.addMore());
      }

      return this.current();
   }

   public int length() {
      while(!this.latest().isLast() && this.latest().end() < this.end) {
         this.addMore();
      }

      return .MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.latest().end()), this.end) - this.start;
   }

   public Object apply(final int index) {
      if (this.isDefinedAt(index)) {
         return this.page(index + this.start).apply(index + this.start);
      } else {
         throw new IndexOutOfBoundsException(Integer.toString(index));
      }
   }

   public boolean isDefinedAt(final int index) {
      boolean var10000;
      if (index >= 0 && index < this.end - this.start) {
         int absidx = index + this.start;
         if (absidx >= 0 && absidx < this.page(absidx).end()) {
            var10000 = true;
            return var10000;
         }
      }

      var10000 = false;
      return var10000;
   }

   public PagedSeq slice(final int _start, final int _end) {
      this.page(this.start);
      int s = this.start + _start;
      int e = _end == Integer.MAX_VALUE ? _end : this.start + _end;
      Page f = this.first1;

      while(f.end() <= s && !f.isLast()) {
         if (f.next() == null) {
            f = f.addMore(this.more);
         } else {
            f = f.next();
         }
      }

      return new PagedSeq(this.more, f, s, e, this.evidence$3);
   }

   public PagedSeq slice(final int start) {
      return this.slice(start, Integer.MAX_VALUE);
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      this.iterator().foreach((ch) -> buf.append(ch));
      return buf.toString();
   }

   public PagedSeq(final Function3 more, final Page first1, final int start, final int end, final ClassTag evidence$3) {
      this.more = more;
      this.first1 = first1;
      this.start = start;
      this.end = end;
      this.evidence$3 = evidence$3;
      IndexedSeqOps.$init$(this);
      IndexedSeq.$init$(this);
      ScalaVersionSpecificPagedSeq.$init$(this);
      this.current = first1;
   }

   public PagedSeq(final Function3 more, final ClassTag evidence$4) {
      this(more, new Page(0, evidence$4), 0, Integer.MAX_VALUE, evidence$4);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
