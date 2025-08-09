package scala.collection.immutable;

import java.io.Serializable;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.collection.AnyStepper$;
import scala.collection.Factory;
import scala.collection.IndexedSeqView;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StringView;
import scala.collection.View;
import scala.collection.convert.impl.CharStringStepper;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015g\u0001\u0002\u00192\u0005aB\u0001\"\u0016\u0001\u0003\u0006\u0004%IA\u0016\u0005\t?\u0002\u0011\t\u0011)A\u0005/\")\u0001\r\u0001C\u0001C\")!\u000e\u0001C\u0001W\")\u0011\u000f\u0001C)e\")\u0011\u0010\u0001C)u\"9\u00111\u0001\u0001\u0005B\u0005\u0015\u0001bBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\b\u0003'\u0001A\u0011IA\u000b\u0011\u001d\t9\u0002\u0001C!\u00033Aq!a\u0007\u0001\t\u0003\ni\u0002C\u0004\u0002,\u0001!\t%!\f\t\u000f\u0005e\u0004\u0001\"\u0011\u0002|!I\u0011q\u0013\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0014\u0005\b\u0003g\u0003A\u0011IA[\u0011\u001d\t)\r\u0001C!\u0003\u000fD\u0011\"!6\u0001#\u0003%\t!a6\t\u000f\u0005m\u0007\u0001\"\u0011\u0002^\"I\u00111\u001e\u0001\u0012\u0002\u0013\u0005\u0011Q\u001e\u0005\b\u0003c\u0004A\u0011IAz\u0011\u001d\u0011i\u0001\u0001C!\u0005\u001fAqAa\b\u0001\t\u0003\u0012\t\u0003C\u0004\u00030\u0001\u0001K\u0011\u000b,\t\u000f\tE\u0002\u0001\"\u0016\u0002\u0016!9!1\u0007\u0001\u0005B\tUra\u0002B$c!\u0005!\u0011\n\u0004\u0007aEB\tAa\u0013\t\r\u0001\\B\u0011\u0001B2\u0011\u0019\t8\u0004\"\u0001\u0003f!I\u00111A\u000eC\u0002\u0013\u0005\u0011Q\u0001\u0005\b\u0005WZ\u0002\u0015!\u0003I\u0011\u0019\u0011ig\u0007C\u0001u\u001a1!qN\u000e\u0004\u0005cBaB!\u001f\"\t\u0003\u0005)Q!b\u0001\n\u0013\t)\u0001\u0003\u0006\u0003|\u0005\u0012)\u0011!Q\u0001\n!Ca\u0001Y\u0011\u0005\u0002\tu\u0004b\u0002BCC\u0011\u0005!q\u0011\u0005\n\u0005\u0013\u000b\u0013\u0011!C!\u0005\u0017C\u0011Ba\r\"\u0003\u0003%\tE!$\b\u0013\tM5$!A\t\u0002\tUe!\u0003B87\u0005\u0005\t\u0012\u0001BL\u0011\u0019\u0001\u0017\u0006\"\u0001\u0003\u001a\"9!1T\u0015\u0005\u0006\tu\u0005\"\u0003BRS\u0005\u0005IQ\u0001BS\u0011%\u0011I+KA\u0001\n\u000b\u0011Y\u000bC\u0005\u0003\u0014n\t\t\u0011b\u0001\u00034\"I!qW\u000e\u0002\u0002\u0013%!\u0011\u0018\u0002\u000e/J\f\u0007\u000f]3e'R\u0014\u0018N\\4\u000b\u0005I\u001a\u0014!C5n[V$\u0018M\u00197f\u0015\t!T'\u0001\u0006d_2dWm\u0019;j_:T\u0011AN\u0001\u0006g\u000e\fG.Y\u0002\u0001'\u0015\u0001\u0011(\u0011#J!\rQ4(P\u0007\u0002c%\u0011A(\r\u0002\f\u0003\n\u001cHO]1diN+\u0017\u000f\u0005\u0002?\u007f5\tQ'\u0003\u0002Ak\t!1\t[1s!\rQ$)P\u0005\u0003\u0007F\u0012!\"\u00138eKb,GmU3r!\u0015QT)P$I\u0013\t1\u0015GA\u0007J]\u0012,\u00070\u001a3TKF|\u0005o\u001d\t\u0003u\t\u0003\"A\u000f\u0001\u0011\u0005)\u0013fBA&Q\u001d\tau*D\u0001N\u0015\tqu'\u0001\u0004=e>|GOP\u0005\u0002m%\u0011\u0011+N\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019FK\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Rk\u0005!1/\u001a7g+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u000bM,GN\u001a\u0011\u0002\rqJg.\u001b;?)\tA%\rC\u0003V\u0007\u0001\u00071\r\u0005\u0002eQ:\u0011QM\u001a\t\u0003\u0019VJ!aZ\u001b\u0002\rA\u0013X\rZ3g\u0013\tq\u0016N\u0003\u0002hk\u0005)\u0011\r\u001d9msR\u0011Q\b\u001c\u0005\u0006[\u0012\u0001\rA\\\u0001\u0002SB\u0011ah\\\u0005\u0003aV\u00121!\u00138u\u000311'o\\7Ta\u0016\u001c\u0017NZ5d)\tA5\u000fC\u0003u\u000b\u0001\u0007Q/\u0001\u0003d_2d\u0007c\u0001<x{5\t1'\u0003\u0002yg\ta\u0011\n^3sC\ndWm\u00148dK\u0006\u0011b.Z<Ta\u0016\u001c\u0017NZ5d\u0005VLG\u000eZ3s+\u0005Y\b\u0003\u0002?\u0000{!k\u0011! \u0006\u0003}N\nq!\\;uC\ndW-C\u0002\u0002\u0002u\u0014qAQ;jY\u0012,'/A\u0003f[B$\u00180F\u0001I\u0003\u0015\u0019H.[2f)\u0015A\u00151BA\b\u0011\u0019\ti\u0001\u0003a\u0001]\u0006!aM]8n\u0011\u0019\t\t\u0002\u0003a\u0001]\u0006)QO\u001c;jY\u00061A.\u001a8hi\",\u0012A\\\u0001\ti>\u001cFO]5oOR\tq+\u0001\u0003wS\u0016<XCAA\u0010!\u0011\t\t#!\n\u000f\u0007i\n\u0019#\u0003\u0002Rc%!\u0011qEA\u0015\u0005)\u0019FO]5oOZKWm\u001e\u0006\u0003#F\nqa\u001d;faB,'/\u0006\u0003\u00020\u0005mB\u0003BA\u0019\u0003_\u0012b!a\r\u00028\u0005ucABA\u001b\u0001\u0001\t\tD\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0003\u0002:\u0005mB\u0002\u0001\u0003\b\u0003{a!\u0019AA \u0005\u0005\u0019\u0016\u0003BA!\u0003\u000f\u00022APA\"\u0013\r\t)%\u000e\u0002\b\u001d>$\b.\u001b8ha\u0011\tI%!\u0015\u0011\u000bY\fY%a\u0014\n\u0007\u000553GA\u0004Ti\u0016\u0004\b/\u001a:\u0011\t\u0005e\u0012\u0011\u000b\u0003\r\u0003'\nY$!A\u0001\u0002\u000b\u0005\u0011Q\u000b\u0002\u0004?\u0012\n\u0014\u0003BA!\u0003/\u00022APA-\u0013\r\tY&\u000e\u0002\u0004\u0003:L\b\u0003BA0\u0003SrA!!\u0019\u0002f9\u00191*a\u0019\n\u0005Q*\u0014bAA4g\u000591\u000b^3qa\u0016\u0014\u0018\u0002BA6\u0003[\u0012a\"\u00124gS\u000eLWM\u001c;Ta2LGOC\u0002\u0002hMBq!!\u001d\r\u0001\b\t\u0019(A\u0003tQ\u0006\u0004X\r\u0005\u0004w\u0003kj\u0014qG\u0005\u0004\u0003o\u001a$\u0001D*uKB\u0004XM]*iCB,\u0017AC:uCJ$8oV5uQV!\u0011QPAG)\u0019\ty(!\"\u0002\u0014B\u0019a(!!\n\u0007\u0005\rUGA\u0004C_>dW-\u00198\t\u000f\u0005\u001dU\u00021\u0001\u0002\n\u0006!A\u000f[1u!\u00111x/a#\u0011\t\u0005e\u0012Q\u0012\u0003\b\u0003\u001fk!\u0019AAI\u0005\u0005\u0011\u0015cA\u001f\u0002X!A\u0011QS\u0007\u0011\u0002\u0003\u0007a.\u0001\u0004pM\u001a\u001cX\r^\u0001\u0015gR\f'\u000f^:XSRDG\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005m\u0015\u0011W\u000b\u0003\u0003;S3A\\APW\t\t\t\u000b\u0005\u0003\u0002$\u00065VBAAS\u0015\u0011\t9+!+\u0002\u0013Ut7\r[3dW\u0016$'bAAVk\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005=\u0016Q\u0015\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,GaBAH\u001d\t\u0007\u0011\u0011S\u0001\tK:$7oV5uQV!\u0011qWAb)\u0011\ty(!/\t\u000f\u0005\u001du\u00021\u0001\u0002<B)a/!0\u0002B&\u0019\u0011qX\u001a\u0003\u0011%#XM]1cY\u0016\u0004B!!\u000f\u0002D\u00129\u0011qR\bC\u0002\u0005E\u0015aB5oI\u0016DxJZ\u000b\u0005\u0003\u0013\f\t\u000eF\u0003o\u0003\u0017\f\u0019\u000eC\u0004\u0002NB\u0001\r!a4\u0002\t\u0015dW-\u001c\t\u0005\u0003s\t\t\u000eB\u0004\u0002\u0010B\u0011\r!!%\t\u0011\u00055\u0001\u0003%AA\u00029\f\u0011#\u001b8eKb|e\r\n3fM\u0006,H\u000e\u001e\u00133+\u0011\tY*!7\u0005\u000f\u0005=\u0015C1\u0001\u0002\u0012\u0006YA.Y:u\u0013:$W\r_(g+\u0011\ty.!:\u0015\u000b9\f\t/a:\t\u000f\u00055'\u00031\u0001\u0002dB!\u0011\u0011HAs\t\u001d\tyI\u0005b\u0001\u0003#C\u0001\"!;\u0013!\u0003\u0005\rA\\\u0001\u0004K:$\u0017!\u00067bgRLe\u000eZ3y\u001f\u001a$C-\u001a4bk2$HEM\u000b\u0005\u00037\u000by\u000fB\u0004\u0002\u0010N\u0011\r!!%\u0002\u0017\r|\u0007/\u001f+p\u0003J\u0014\u0018-_\u000b\u0005\u0003k\u0014\u0019\u0001F\u0004o\u0003o\u0014)A!\u0003\t\u000f\u0005eH\u00031\u0001\u0002|\u0006\u0011\u0001p\u001d\t\u0006}\u0005u(\u0011A\u0005\u0004\u0003\u007f,$!B!se\u0006L\b\u0003BA\u001d\u0005\u0007!q!a$\u0015\u0005\u0004\t\t\n\u0003\u0004\u0003\bQ\u0001\rA\\\u0001\u0006gR\f'\u000f\u001e\u0005\u0007\u0005\u0017!\u0002\u0019\u00018\u0002\u00071,g.A\u0006baB,g\u000eZ3e\u00032dW\u0003\u0002B\t\u0005/!BAa\u0005\u0003\u001aA!!H\u0011B\u000b!\u0011\tIDa\u0006\u0005\u000f\u0005=UC1\u0001\u0002\u0012\"9!1D\u000bA\u0002\tu\u0011AB:vM\u001aL\u0007\u0010\u0005\u0003wo\nU\u0011\u0001D:b[\u0016,E.Z7f]R\u001cX\u0003\u0002B\u0012\u0005[!B!a \u0003&!9!q\u0005\fA\u0002\t%\u0012!A8\u0011\tY<(1\u0006\t\u0005\u0003s\u0011i\u0003B\u0004\u0002\u0010Z\u0011\r!!%\u0002\u0013\rd\u0017m]:OC6,\u0017aF1qa2L\bK]3gKJ\u0014X\rZ'bq2+gn\u001a;i\u0003\u0019)\u0017/^1mgR!\u0011q\u0010B\u001c\u0011\u001d\u0011I$\u0007a\u0001\u0003/\nQa\u001c;iKJDs\u0001\u0001B\u001f\u0005\u0007\u0012)\u0005E\u0002?\u0005\u007fI1A!\u00116\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0004\u000359&/\u00199qK\u0012\u001cFO]5oOB\u0011!hG\n\b7\t5#1\u000bB-!\rq$qJ\u0005\u0004\u0005#*$AB!osJ+g\rE\u0003w\u0005+j\u0004*C\u0002\u0003XM\u0012qc\u00159fG&4\u0017nY%uKJ\f'\r\\3GC\u000e$xN]=\u0011\t\tm#\u0011M\u0007\u0003\u0005;R1Aa\u0018\\\u0003\tIw.C\u0002T\u0005;\"\"A!\u0013\u0015\u0007!\u00139\u0007\u0003\u0004\u0003ju\u0001\r!^\u0001\u0003SR\fa!Z7qif\u0004\u0013A\u00038fo\n+\u0018\u000e\u001c3fe\nAQK\\<sCB|\u0005oE\u0002\"\u0005g\u00022A\u0010B;\u0013\r\u00119(\u000e\u0002\u0007\u0003:Lh+\u00197\u0002qM\u001c\u0017\r\\1%G>dG.Z2uS>tG%[7nkR\f'\r\\3%/J\f\u0007\u000f]3e'R\u0014\u0018N\\4%+:<(/\u00199Pa\u0012\"c/\u00197vK\u0006I4oY1mC\u0012\u001aw\u000e\u001c7fGRLwN\u001c\u0013j[6,H/\u00192mK\u0012:&/\u00199qK\u0012\u001cFO]5oO\u0012*fn\u001e:ba>\u0003H\u0005\n<bYV,\u0007\u0005\u0006\u0003\u0003\u0000\t\r\u0005c\u0001BAC5\t1\u0004\u0003\u0004\u0003D\u0011\u0002\r\u0001S\u0001\u0007k:<(/\u00199\u0016\u0003\r\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002]R!\u0011q\u0010BH\u0011%\u0011\tjJA\u0001\u0002\u0004\t9&A\u0002yIE\n\u0001\"\u00168xe\u0006\u0004x\n\u001d\t\u0004\u0005\u0003K3cA\u0015\u0003NQ\u0011!QS\u0001\u0011k:<(/\u00199%Kb$XM\\:j_:$2a\u0019BP\u0011\u001d\u0011\tk\u000ba\u0001\u0005\u007f\nQ\u0001\n;iSN\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R!!1\u0012BT\u0011\u001d\u0011\t\u000b\fa\u0001\u0005\u007f\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\t\t5&\u0011\u0017\u000b\u0005\u0003\u007f\u0012y\u000bC\u0005\u0003\u00126\n\t\u00111\u0001\u0002X!9!\u0011U\u0017A\u0002\t}D\u0003\u0002B@\u0005kCaAa\u0011/\u0001\u0004A\u0015\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B^!\rA&QX\u0005\u0004\u0005\u007fK&AB(cU\u0016\u001cG\u000fK\u0004\u001c\u0005{\u0011\u0019E!\u0012)\u000fi\u0011iDa\u0011\u0003F\u0001"
)
public final class WrappedString extends AbstractSeq implements IndexedSeq, Serializable {
   private static final long serialVersionUID = 3L;
   private final String scala$collection$immutable$WrappedString$$self;

   public static WrappedString UnwrapOp(final WrappedString value) {
      WrappedString$ var10000 = WrappedString$.MODULE$;
      return value;
   }

   public static Builder newBuilder() {
      return WrappedString$.MODULE$.newBuilder();
   }

   public static Factory specificIterableFactory() {
      return WrappedString$.MODULE$;
   }

   public static Object fill(final int n, final Function0 elem) {
      WrappedString$ fill_this = WrappedString$.MODULE$;
      IterableOnce fromSpecific_it = new View.Fill(n, elem);
      return fill_this.fromSpecific(fromSpecific_it);
   }

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   // $FF: synthetic method
   public boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public final IndexedSeq toIndexedSeq() {
      return IndexedSeq.toIndexedSeq$(this);
   }

   public boolean canEqual(final Object that) {
      return IndexedSeq.canEqual$(this, that);
   }

   public SeqFactory iterableFactory() {
      return IndexedSeq.iterableFactory$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public String stringPrefix() {
      return scala.collection.IndexedSeq.stringPrefix$(this);
   }

   public Iterator iterator() {
      return scala.collection.IndexedSeqOps.iterator$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object prepended(final Object elem) {
      return scala.collection.IndexedSeqOps.prepended$(this, elem);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return scala.collection.IndexedSeqOps.takeRight$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return scala.collection.IndexedSeqOps.dropRight$(this, n);
   }

   public Object map(final Function1 f) {
      return scala.collection.IndexedSeqOps.map$(this, f);
   }

   public Object reverse() {
      return scala.collection.IndexedSeqOps.reverse$(this);
   }

   public Object head() {
      return scala.collection.IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return scala.collection.IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public String scala$collection$immutable$WrappedString$$self() {
      return this.scala$collection$immutable$WrappedString$$self;
   }

   public char apply(final int i) {
      return this.scala$collection$immutable$WrappedString$$self().charAt(i);
   }

   public WrappedString fromSpecific(final IterableOnce coll) {
      return WrappedString$.MODULE$.fromSpecific(coll);
   }

   public Builder newSpecificBuilder() {
      return WrappedString$.MODULE$.newBuilder();
   }

   public WrappedString empty() {
      return WrappedString$.MODULE$.empty();
   }

   public WrappedString slice(final int from, final int until) {
      int start = from < 0 ? 0 : from;
      if (until > start && start < this.scala$collection$immutable$WrappedString$$self().length()) {
         int end = until > this.length() ? this.length() : until;
         return new WrappedString(this.scala$collection$immutable$WrappedString$$self().substring(start, end));
      } else {
         return WrappedString$.MODULE$.empty();
      }
   }

   public int length() {
      return this.scala$collection$immutable$WrappedString$$self().length();
   }

   public String toString() {
      return this.scala$collection$immutable$WrappedString$$self();
   }

   public StringView view() {
      return new StringView(this.scala$collection$immutable$WrappedString$$self());
   }

   public Stepper stepper(final StepperShape shape) {
      CharStringStepper st = new CharStringStepper(this.scala$collection$immutable$WrappedString$$self(), 0, this.scala$collection$immutable$WrappedString$$self().length());
      if (shape.shape() == StepperShape$.MODULE$.CharShape()) {
         return st;
      } else if (shape.shape() != StepperShape$.MODULE$.ReferenceShape()) {
         throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$stepper$1(shape)).toString());
      } else {
         AnyStepper$ var10000 = AnyStepper$.MODULE$;
         return new Stepper.EfficientSplit(st) {
         };
      }
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      if (that instanceof WrappedString) {
         WrappedString var3 = (WrappedString)that;
         return this.scala$collection$immutable$WrappedString$$self().startsWith(var3.scala$collection$immutable$WrappedString$$self(), offset);
      } else {
         return scala.collection.SeqOps.startsWith$(this, that, offset);
      }
   }

   public int startsWith$default$2() {
      return 0;
   }

   public boolean endsWith(final scala.collection.Iterable that) {
      if (that instanceof WrappedString) {
         WrappedString var2 = (WrappedString)that;
         return this.scala$collection$immutable$WrappedString$$self().endsWith(var2.scala$collection$immutable$WrappedString$$self());
      } else {
         return scala.collection.SeqOps.endsWith$(this, that);
      }
   }

   public int indexOf(final Object elem, final int from) {
      if (elem instanceof Character) {
         char var3 = BoxesRunTime.unboxToChar(elem);
         return this.scala$collection$immutable$WrappedString$$self().indexOf(var3, from);
      } else {
         return scala.collection.SeqOps.indexOf$(this, elem, from);
      }
   }

   public int indexOf$default$2() {
      return 0;
   }

   public int lastIndexOf(final Object elem, final int end) {
      if (elem instanceof Character) {
         char var3 = BoxesRunTime.unboxToChar(elem);
         return this.scala$collection$immutable$WrappedString$$self().lastIndexOf(var3, end);
      } else {
         return scala.collection.SeqOps.lastIndexOf$(this, elem, end);
      }
   }

   public int lastIndexOf$default$2() {
      return this.length() - 1;
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      if (xs instanceof char[]) {
         char[] var4 = (char[])xs;
         IterableOnce$ var10000 = IterableOnce$.MODULE$;
         int var8 = this.length();
         int elemsToCopyToArray_destLen = var4.length;
         int elemsToCopyToArray_srcLen = var8;
         scala.math.package$ var9 = scala.math.package$.MODULE$;
         var9 = scala.math.package$.MODULE$;
         var9 = scala.math.package$.MODULE$;
         int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
         this.scala$collection$immutable$WrappedString$$self().getChars(0, copied, var4, start);
         return copied;
      } else {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }
   }

   public IndexedSeq appendedAll(final IterableOnce suffix) {
      if (suffix instanceof WrappedString) {
         WrappedString var2 = (WrappedString)suffix;
         return new WrappedString(this.scala$collection$immutable$WrappedString$$self().concat(var2.scala$collection$immutable$WrappedString$$self()));
      } else {
         return (IndexedSeq)IterableOps.concat$(this, suffix);
      }
   }

   public boolean sameElements(final IterableOnce o) {
      if (!(o instanceof WrappedString)) {
         return IndexedSeq.sameElements$(this, o);
      } else {
         WrappedString var2 = (WrappedString)o;
         String var10000 = this.scala$collection$immutable$WrappedString$$self();
         String var3 = var2.scala$collection$immutable$WrappedString$$self();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }

         return false;
      }
   }

   public String className() {
      return "WrappedString";
   }

   public final int applyPreferredMaxLength() {
      return Integer.MAX_VALUE;
   }

   public boolean equals(final Object other) {
      if (!(other instanceof WrappedString)) {
         return scala.collection.Seq.equals$(this, other);
      } else {
         WrappedString var2 = (WrappedString)other;
         String var10000 = this.scala$collection$immutable$WrappedString$$self();
         String var3 = var2.scala$collection$immutable$WrappedString$$self();
         if (var10000 == null) {
            if (var3 == null) {
               return true;
            }
         } else if (var10000.equals(var3)) {
            return true;
         }

         return false;
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$stepper$1(final StepperShape shape$1) {
      return (new StringBuilder(25)).append("unexpected StepperShape: ").append(shape$1).toString();
   }

   public WrappedString(final String self) {
      this.scala$collection$immutable$WrappedString$$self = self;
   }

   public static final class UnwrapOp {
      private final WrappedString scala$collection$immutable$WrappedString$UnwrapOp$$value;

      public WrappedString scala$collection$immutable$WrappedString$UnwrapOp$$value() {
         return this.scala$collection$immutable$WrappedString$UnwrapOp$$value;
      }

      public String unwrap() {
         UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         return this.scala$collection$immutable$WrappedString$UnwrapOp$$value().scala$collection$immutable$WrappedString$$self();
      }

      public int hashCode() {
         UnwrapOp$ var10000 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString hashCode$extension_$this = this.scala$collection$immutable$WrappedString$UnwrapOp$$value();
         if (hashCode$extension_$this == null) {
            throw null;
         } else {
            return MurmurHash3$.MODULE$.seqHash(hashCode$extension_$this);
         }
      }

      public boolean equals(final Object x$1) {
         return WrappedString.UnwrapOp$.MODULE$.equals$extension(this.scala$collection$immutable$WrappedString$UnwrapOp$$value(), x$1);
      }

      public UnwrapOp(final WrappedString value) {
         this.scala$collection$immutable$WrappedString$UnwrapOp$$value = value;
      }
   }

   public static class UnwrapOp$ {
      public static final UnwrapOp$ MODULE$ = new UnwrapOp$();

      public final String unwrap$extension(final WrappedString $this) {
         return $this.scala$collection$immutable$WrappedString$$self();
      }

      public final int hashCode$extension(final WrappedString $this) {
         if ($this == null) {
            throw null;
         } else {
            return MurmurHash3$.MODULE$.seqHash($this);
         }
      }

      public final boolean equals$extension(final WrappedString $this, final Object x$1) {
         if (x$1 instanceof UnwrapOp) {
            WrappedString var3 = x$1 == null ? null : ((UnwrapOp)x$1).scala$collection$immutable$WrappedString$UnwrapOp$$value();
            if ($this == null) {
               if (var3 == null) {
                  return true;
               }
            } else if ($this.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }
}
