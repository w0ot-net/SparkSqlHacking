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
import scala.collection.generic.GenericParTemplate;
import scala.collection.immutable.Seq;
import scala.math.Integral;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u00037\u0001\u0011\u0005q\u0007C\u0003<\u0001\u0011\u0005A\bC\u0003A\u0001\u0011\u0005\u0011iB\u0003K\u0017!\u00051JB\u0003\u000b\u0017!\u0005A\nC\u0003Q\u000b\u0011\u0005\u0011\u000bC\u0003S\u000b\u0011\r1\u000bC\u0003`\u000b\u0011\u0005\u0001\rC\u0003i\u000b\u0011\u0005\u0011NA\u0006QCJLE/\u001a:bE2,'B\u0001\u0007\u000e\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\b\u0010\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002!\u0005)1oY1mC\u000e\u0001QCA\n!'\u0011\u0001A\u0003G\u0016\u0011\u0005U1R\"A\b\n\u0005]y!AB!osJ+g\r\u0005\u0003\u001a9yIS\"\u0001\u000e\u000b\u0005mi\u0011aB4f]\u0016\u0014\u0018nY\u0005\u0003;i\u0011!cR3oKJL7\rU1s)\u0016l\u0007\u000f\\1uKB\u0011q\u0004\t\u0007\u0001\t\u0019\t\u0003\u0001\"b\u0001E\t\tA+\u0005\u0002$MA\u0011Q\u0003J\u0005\u0003K=\u0011qAT8uQ&tw\r\u0005\u0002\u0016O%\u0011\u0001f\u0004\u0002\u0004\u0003:L\bC\u0001\u0016\u0001\u001b\u0005Y\u0001C\u0002\u0016-=%rs&\u0003\u0002.\u0017\ty\u0001+\u0019:Ji\u0016\u0014\u0018M\u00197f\u0019&\\W\rE\u0002+\u0001y\u00012\u0001M\u001a\u001f\u001d\t)\u0012'\u0003\u00023\u001f\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001b6\u0005!IE/\u001a:bE2,'B\u0001\u001a\u0010\u0003\u0019!\u0013N\\5uIQ\t\u0001\b\u0005\u0002\u0016s%\u0011!h\u0004\u0002\u0005+:LG/A\u0005d_6\u0004\u0018M\\5p]V\tQ\bE\u0002\u001a}%J!a\u0010\u000e\u0003'\u001d+g.\u001a:jGB\u000b'oQ8na\u0006t\u0017n\u001c8\u0002\u0019M$(/\u001b8h!J,g-\u001b=\u0016\u0003\t\u0003\"a\u0011%\u000e\u0003\u0011S!!\u0012$\u0002\t1\fgn\u001a\u0006\u0002\u000f\u0006!!.\u0019<b\u0013\tIEI\u0001\u0004TiJLgnZ\u0001\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002+\u000bM\u0011Q!\u0014\t\u000439K\u0013BA(\u001b\u0005)\u0001\u0016M\u001d$bGR|'/_\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u000bAbY1o\u0005VLG\u000e\u001a$s_6,2\u0001V/[+\u0005)\u0006#B\rW1rs\u0016BA,\u001b\u00059\u0019\u0015M\\\"p[\nLg.\u001a$s_6\u00042A\u000b\u0001Z!\ty\"\fB\u0003\\\u000f\t\u0007!EA\u0001T!\tyR\fB\u0003\"\u000f\t\u0007!\u0005E\u0002+\u0001q\u000b!B\\3x\u0005VLG\u000eZ3s+\t\tg-F\u0001c!\u0011Q3-Z4\n\u0005\u0011\\!\u0001C\"p[\nLg.\u001a:\u0011\u0005}1G!B\u0011\t\u0005\u0004\u0011\u0003c\u0001\u0016\u0001K\u0006Ya.Z<D_6\u0014\u0017N\\3s+\tQW.F\u0001l!\u0011Q3\r\u001c8\u0011\u0005}iG!B\u0011\n\u0005\u0004\u0011\u0003c\u0001\u0016\u0001Y\u0002"
)
public interface ParIterable extends GenericParTemplate, ParIterableLike {
   static CanCombineFrom canBuildFrom() {
      return ParIterable$.MODULE$.canBuildFrom();
   }

   static ParIterable iterate(final Object start, final int len, final Function1 f) {
      return ParIterable$.MODULE$.iterate(start, len, f);
   }

   static ParIterable range(final Object start, final Object end, final Object step, final Integral evidence$2) {
      return ParIterable$.MODULE$.range(start, end, step, evidence$2);
   }

   static ParIterable range(final Object start, final Object end, final Integral evidence$1) {
      return ParIterable$.MODULE$.range(start, end, evidence$1);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, n3, f);
   }

   static ParIterable tabulate(final int n1, final int n2, final Function2 f) {
      return ParIterable$.MODULE$.tabulate(n1, n2, f);
   }

   static ParIterable tabulate(final int n, final Function1 f) {
      return ParIterable$.MODULE$.tabulate(n, f);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   static ParIterable fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, n3, elem);
   }

   static ParIterable fill(final int n1, final int n2, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n1, n2, elem);
   }

   static ParIterable fill(final int n, final Function0 elem) {
      return ParIterable$.MODULE$.fill(n, elem);
   }

   static ParIterable concat(final Seq xss) {
      return ParIterable$.MODULE$.concat(xss);
   }

   static Factory toFactory() {
      return ParIterable$.MODULE$.toFactory();
   }

   static ParIterable apply(final Seq elems) {
      return ParIterable$.MODULE$.apply(elems);
   }

   static ParIterable empty() {
      return ParIterable$.MODULE$.empty();
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParIterable $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParIterable$.MODULE$;
   }

   // $FF: synthetic method
   static String stringPrefix$(final ParIterable $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "ParIterable";
   }

   static void $init$(final ParIterable $this) {
   }
}
