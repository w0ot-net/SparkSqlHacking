package scala.collection.parallel.immutable;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.immutable.Seq;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554qAC\u0006\u0011\u0002\u0007\u0005A\u0003C\u0003=\u0001\u0011\u0005Q\bC\u0003B\u0001\u0011\u0005#\tC\u0003G\u0001\u0011\u0005siB\u0003I\u0017!\u0005\u0011JB\u0003\u000b\u0017!\u0005!\nC\u0003O\u000b\u0011\u0005q\nC\u0003Q\u000b\u0011\r\u0011\u000bC\u0003^\u000b\u0011\u0005a\fC\u0003g\u000b\u0011\u0005qM\u0001\u0004QCJ\u001cV-\u001d\u0006\u0003\u00195\t\u0011\"[7nkR\f'\r\\3\u000b\u00059y\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005A\t\u0012AC2pY2,7\r^5p]*\t!#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005Uy2C\u0002\u0001\u00175!b3\u0007\u0005\u0002\u001815\t\u0011#\u0003\u0002\u001a#\t1\u0011I\\=SK\u001a\u00042a\u0007\u000f\u001e\u001b\u0005i\u0011B\u0001\u0006\u000e!\tqr\u0004\u0004\u0001\u0005\r\u0001\u0002AQ1\u0001\"\u0005\u0005!\u0016C\u0001\u0012&!\t92%\u0003\u0002%#\t9aj\u001c;iS:<\u0007CA\f'\u0013\t9\u0013CA\u0002B]f\u00042!\u000b\u0016\u001e\u001b\u0005Y\u0011BA\u0016\f\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\t5\u0002TDM\u0007\u0002])\u0011qfD\u0001\bO\u0016tWM]5d\u0013\t\tdF\u0001\nHK:,'/[2QCJ$V-\u001c9mCR,\u0007CA\u0015\u0001!\u0019YB'\b\u001a7o%\u0011Q'\u0004\u0002\u000b!\u0006\u00148+Z9MS.,\u0007cA\u0015\u0001;A\u0019\u0001HO\u000f\u000e\u0003eR!\u0001D\b\n\u0005mJ$aA*fc\u00061A%\u001b8ji\u0012\"\u0012A\u0010\t\u0003/}J!\u0001Q\t\u0003\tUs\u0017\u000e^\u0001\nG>l\u0007/\u00198j_:,\u0012a\u0011\t\u0004[\u0011\u0013\u0014BA#/\u0005M9UM\\3sS\u000e\u0004\u0016M]\"p[B\fg.[8o\u0003\u0015!xnU3r+\u00051\u0014A\u0002)beN+\u0017\u000f\u0005\u0002*\u000bM\u0011Qa\u0013\t\u0004[1\u0013\u0014BA'/\u0005)\u0001\u0016M\u001d$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%\u000bAbY1o\u0005VLG\u000e\u001a$s_6,2A\u0015-\\+\u0005\u0019\u0006#B\u0017U-jc\u0016BA+/\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u00042!\u000b\u0001X!\tq\u0002\fB\u0003Z\u000f\t\u0007\u0011EA\u0001T!\tq2\fB\u0003!\u000f\t\u0007\u0011\u0005E\u0002*\u0001i\u000b!B\\3x\u0005VLG\u000eZ3s+\tyF-F\u0001a!\u0011Y\u0012mY3\n\u0005\tl!\u0001C\"p[\nLg.\u001a:\u0011\u0005y!G!\u0002\u0011\t\u0005\u0004\t\u0003cA\u0015\u0001G\u0006Ya.Z<D_6\u0014\u0017N\\3s+\tA7.F\u0001j!\u0011Y\u0012M\u001b7\u0011\u0005yYG!\u0002\u0011\n\u0005\u0004\t\u0003cA\u0015\u0001U\u0002"
)
public interface ParSeq extends scala.collection.parallel.ParSeq, ParIterable {
   static CanCombineFrom canBuildFrom() {
      return ParSeq$.MODULE$.canBuildFrom();
   }

   static scala.collection.parallel.ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParSeq$.MODULE$.iterate(start, len, f);
   }

   static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParSeq$.MODULE$.range(start, end, step, evidence$2);
   }

   static scala.collection.parallel.ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParSeq$.MODULE$.range(start, end, evidence$1);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, f);
   }

   static scala.collection.parallel.ParIterable tabulate(final int n, final Function1 f) {
      return ParSeq$.MODULE$.tabulate(n, f);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, elem);
   }

   static scala.collection.parallel.ParIterable fill(final int n, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n, elem);
   }

   static scala.collection.parallel.ParIterable concat(final Seq xss) {
      return ParSeq$.MODULE$.concat(xss);
   }

   static Factory toFactory() {
      return ParSeq$.MODULE$.toFactory();
   }

   static scala.collection.parallel.ParIterable empty() {
      return ParSeq$.MODULE$.empty();
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParSeq $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParSeq$.MODULE$;
   }

   // $FF: synthetic method
   static ParSeq toSeq$(final ParSeq $this) {
      return $this.toSeq();
   }

   default ParSeq toSeq() {
      return this;
   }

   static void $init$(final ParSeq $this) {
   }
}
