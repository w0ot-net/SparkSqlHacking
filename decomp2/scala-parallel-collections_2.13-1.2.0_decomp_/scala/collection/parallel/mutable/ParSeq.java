package scala.collection.parallel.mutable;

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
   bytes = "\u0006\u0005i4q\u0001D\u0007\u0011\u0002\u0007\u0005a\u0003C\u0003?\u0001\u0011\u0005q\bC\u0003D\u0001\u0011\u0005C\tC\u0003I\u0001\u0019\u0005\u0011\nC\u0003R\u0001\u0019\u0005!\u000bC\u0003T\u0001\u0011\u0005CkB\u0003V\u001b!\u0005aKB\u0003\r\u001b!\u0005q\u000bC\u0003\\\u000f\u0011\u0005A\fC\u0003^\u000f\u0011\ra\fC\u0003k\u000f\u0011\u00051\u000eC\u0003t\u000f\u0011\u0005AO\u0001\u0004QCJ\u001cV-\u001d\u0006\u0003\u001d=\tq!\\;uC\ndWM\u0003\u0002\u0011#\u0005A\u0001/\u0019:bY2,GN\u0003\u0002\u0013'\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003Q\tQa]2bY\u0006\u001c\u0001!\u0006\u0002\u0018EM1\u0001\u0001\u0007\u000f,]U\u0002\"!\u0007\u000e\u000e\u0003MI!aG\n\u0003\r\u0005s\u0017PU3g!\rib\u0004I\u0007\u0002\u001b%\u0011q$\u0004\u0002\f!\u0006\u0014\u0018\n^3sC\ndW\r\u0005\u0002\"E1\u0001A!B\u0012\u0001\u0005\u0004!#!\u0001+\u0012\u0005\u0015B\u0003CA\r'\u0013\t93CA\u0004O_RD\u0017N\\4\u0011\u0005eI\u0013B\u0001\u0016\u0014\u0005\r\te.\u001f\t\u0004Y5\u0002S\"A\b\n\u00051y\u0001\u0003B\u00183AQj\u0011\u0001\r\u0006\u0003cE\tqaZ3oKJL7-\u0003\u00024a\t\u0011r)\u001a8fe&\u001c\u0007+\u0019:UK6\u0004H.\u0019;f!\ti\u0002\u0001\u0005\u0004-m\u0001\"\u0004(O\u0005\u0003o=\u0011!\u0002U1s'\u0016\fH*[6f!\ri\u0002\u0001\t\t\u0004uq\u0002S\"A\u001e\u000b\u00059\t\u0012BA\u001f<\u0005\r\u0019V-]\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0001\u0003\"!G!\n\u0005\t\u001b\"\u0001B+oSR\f\u0011bY8na\u0006t\u0017n\u001c8\u0016\u0003\u0015\u00032a\f$5\u0013\t9\u0005GA\nHK:,'/[2QCJ\u001cu.\u001c9b]&|g.\u0001\u0004va\u0012\fG/\u001a\u000b\u0004\u0001*{\u0005\"B&\u0004\u0001\u0004a\u0015!A5\u0011\u0005ei\u0015B\u0001(\u0014\u0005\rIe\u000e\u001e\u0005\u0006!\u000e\u0001\r\u0001I\u0001\u0005K2,W.A\u0002tKF,\u0012!O\u0001\u0006i>\u001cV-]\u000b\u0002q\u00051\u0001+\u0019:TKF\u0004\"!H\u0004\u0014\u0005\u001dA\u0006cA\u0018Zi%\u0011!\f\r\u0002\u000b!\u0006\u0014h)Y2u_JL\u0018A\u0002\u001fj]&$h\bF\u0001W\u00031\u0019\u0017M\u001c\"vS2$gI]8n+\ryV\r[\u000b\u0002AB)q&Y2hS&\u0011!\r\r\u0002\u000f\u0007\u0006t7i\\7cS:,gI]8n!\ri\u0002\u0001\u001a\t\u0003C\u0015$QAZ\u0005C\u0002\u0011\u0012\u0011a\u0015\t\u0003C!$QaI\u0005C\u0002\u0011\u00022!\b\u0001h\u0003)qWm\u001e\"vS2$WM]\u000b\u0003YF,\u0012!\u001c\t\u0005Y9\u0004(/\u0003\u0002p\u001f\tA1i\\7cS:,'\u000f\u0005\u0002\"c\u0012)1E\u0003b\u0001IA\u0019Q\u0004\u00019\u0002\u00179,woQ8nE&tWM]\u000b\u0003kb,\u0012A\u001e\t\u0005Y9<\u0018\u0010\u0005\u0002\"q\u0012)1e\u0003b\u0001IA\u0019Q\u0004A<"
)
public interface ParSeq extends ParIterable, scala.collection.parallel.ParSeq {
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

   void update(final int i, final Object elem);

   scala.collection.mutable.Seq seq();

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
