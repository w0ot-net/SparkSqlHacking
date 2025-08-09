package scala.collection.parallel;

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
   bytes = "\u0006\u0005\u0005%aa\u0002\u0007\u000e!\u0003\r\t\u0001\u0006\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u0001!\tE\u0010\u0005\u0006\u0005\u00021\ta\u0011\u0005\u0006\u0013\u0002!\tE\u0013\u0005\u0006-\u0002!\teV\u0004\u0006?6A\t\u0001\u0019\u0004\u0006\u00195A\t!\u0019\u0005\u0006K\u001e!\tA\u001a\u0005\u0006O\u001e!\u0019\u0001\u001b\u0005\u0006i\u001e!\t!\u001e\u0005\u0006{\u001e!\tA \u0002\u0007!\u0006\u00148+Z9\u000b\u00059y\u0011\u0001\u00039be\u0006dG.\u001a7\u000b\u0005A\t\u0012AC2pY2,7\r^5p]*\t!#A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005U\u00013#\u0002\u0001\u00175%\u0002\u0004CA\f\u0019\u001b\u0005\t\u0012BA\r\u0012\u0005\u0019\te.\u001f*fMB\u00191\u0004\b\u0010\u000e\u00035I!!H\u0007\u0003\u0017A\u000b'/\u0013;fe\u0006\u0014G.\u001a\t\u0003?\u0001b\u0001\u0001\u0002\u0004\"\u0001\u0011\u0015\rA\t\u0002\u0002)F\u00111E\n\t\u0003/\u0011J!!J\t\u0003\u000f9{G\u000f[5oOB\u0011qcJ\u0005\u0003QE\u00111!\u00118z!\u0011QSFH\u0018\u000e\u0003-R!\u0001L\b\u0002\u000f\u001d,g.\u001a:jG&\u0011af\u000b\u0002\u0013\u000f\u0016tWM]5d!\u0006\u0014H+Z7qY\u0006$X\r\u0005\u0002\u001c\u0001A11$\r\u00100gQJ!AM\u0007\u0003\u0015A\u000b'oU3r\u0019&\\W\rE\u0002\u001c\u0001y\u00012!\u000e\u001c\u001f\u001b\u0005y\u0011BA\u001c\u0010\u0005\r\u0019V-]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003i\u0002\"aF\u001e\n\u0005q\n\"\u0001B+oSR\f\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003}\u00022A\u000b!0\u0013\t\t5FA\nHK:,'/[2QCJ\u001cu.\u001c9b]&|g.A\u0003baBd\u0017\u0010\u0006\u0002\u001f\t\")Qi\u0001a\u0001\r\u0006\t\u0011\u000e\u0005\u0002\u0018\u000f&\u0011\u0001*\u0005\u0002\u0004\u0013:$\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003-\u0003\"\u0001T*\u000f\u00055\u000b\u0006C\u0001(\u0012\u001b\u0005y%B\u0001)\u0014\u0003\u0019a$o\\8u}%\u0011!+E\u0001\u0007!J,G-\u001a4\n\u0005Q+&AB*ue&twM\u0003\u0002S#\u0005a1\u000f\u001e:j]\u001e\u0004&/\u001a4jqV\t\u0001\f\u0005\u0002Z=6\t!L\u0003\u0002\\9\u0006!A.\u00198h\u0015\u0005i\u0016\u0001\u00026bm\u0006L!\u0001\u0016.\u0002\rA\u000b'oU3r!\tYra\u0005\u0002\bEB\u0019!fY\u0018\n\u0005\u0011\\#A\u0003)be\u001a\u000b7\r^8ss\u00061A(\u001b8jiz\"\u0012\u0001Y\u0001\rG\u0006t')^5mI\u001a\u0013x.\\\u000b\u0004S>\u0014X#\u00016\u0011\u000b)ZW.]:\n\u00051\\#AD\"b]\u000e{WNY5oK\u001a\u0013x.\u001c\t\u00047\u0001q\u0007CA\u0010p\t\u0015\u0001\u0018B1\u0001#\u0005\u0005\u0019\u0006CA\u0010s\t\u0015\t\u0013B1\u0001#!\rY\u0002!]\u0001\u000b]\u0016<()^5mI\u0016\u0014XC\u0001<|+\u00059\b\u0003B\u000eyurL!!_\u0007\u0003\u0011\r{WNY5oKJ\u0004\"aH>\u0005\u000b\u0005R!\u0019\u0001\u0012\u0011\u0007m\u0001!0A\u0006oK^\u001cu.\u001c2j]\u0016\u0014XcA@\u0002\u0006U\u0011\u0011\u0011\u0001\t\u00077a\f\u0019!a\u0002\u0011\u0007}\t)\u0001B\u0003\"\u0017\t\u0007!\u0005\u0005\u0003\u001c\u0001\u0005\r\u0001"
)
public interface ParSeq extends ParIterable, ParSeqLike {
   static CanCombineFrom canBuildFrom() {
      return ParSeq$.MODULE$.canBuildFrom();
   }

   static ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParSeq$.MODULE$.iterate(start, len, f);
   }

   static ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParSeq$.MODULE$.range(start, end, step, evidence$2);
   }

   static ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParSeq$.MODULE$.range(start, end, evidence$1);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, n3, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParSeq$.MODULE$.tabulate(n1, n2, f);
   }

   static ParIterable tabulate(final int n, final Function1 f) {
      return ParSeq$.MODULE$.tabulate(n, f);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, n3, elem);
   }

   static ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n1, n2, elem);
   }

   static ParIterable fill(final int n, final Function0 elem) {
      return ParSeq$.MODULE$.fill(n, elem);
   }

   static ParIterable concat(final Seq xss) {
      return ParSeq$.MODULE$.concat(xss);
   }

   static Factory toFactory() {
      return ParSeq$.MODULE$.toFactory();
   }

   static ParIterable empty() {
      return ParSeq$.MODULE$.empty();
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParSeq $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParSeq$.MODULE$;
   }

   Object apply(final int i);

   // $FF: synthetic method
   static String toString$(final ParSeq $this) {
      return $this.toString();
   }

   default String toString() {
      return ParIterableLike.toString$(this);
   }

   // $FF: synthetic method
   static String stringPrefix$(final ParSeq $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return this.getClass().getSimpleName();
   }

   static void $init$(final ParSeq $this) {
   }
}
