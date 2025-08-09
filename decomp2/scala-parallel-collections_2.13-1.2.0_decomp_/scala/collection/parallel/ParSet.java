package scala.collection.parallel;

import scala.collection.Factory;
import scala.collection.generic.CanCombineFrom;
import scala.collection.generic.GenericParCompanion;
import scala.collection.immutable.Nil.;
import scala.collection.parallel.mutable.ParHashSet$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4qAC\u0006\u0011\u0002\u0007\u0005!\u0003C\u00037\u0001\u0011\u0005q\u0007C\u0003<\u0001\u0011\u0005C\bC\u0003>\u0001\u0011\u0005c\bC\u0003C\u0001\u0011\u00053iB\u0003M\u0017!\u0005QJB\u0003\u000b\u0017!\u0005a\nC\u0003S\r\u0011\u00051\u000bC\u0003U\r\u0011\u0005Q\u000bC\u0003^\r\u0011\raL\u0001\u0004QCJ\u001cV\r\u001e\u0006\u0003\u00195\t\u0001\u0002]1sC2dW\r\u001c\u0006\u0003\u001d=\t!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0001\u0012!B:dC2\f7\u0001A\u000b\u0003'\u0001\u001aR\u0001\u0001\u000b\u0019W9\u0002\"!\u0006\f\u000e\u0003=I!aF\b\u0003\r\u0005s\u0017PU3g!\u0011IBDH\u0015\u000e\u0003iQ!aG\u0007\u0002\u000f\u001d,g.\u001a:jG&\u0011QD\u0007\u0002\u0013\u000f\u0016tWM]5d!\u0006\u0014H+Z7qY\u0006$X\r\u0005\u0002 A1\u0001A!B\u0011\u0001\u0005\u0004\u0011#!\u0001+\u0012\u0005\r2\u0003CA\u000b%\u0013\t)sBA\u0004O_RD\u0017N\\4\u0011\u0005U9\u0013B\u0001\u0015\u0010\u0005\r\te.\u001f\t\u0003U\u0001i\u0011a\u0003\t\u0004U1r\u0012BA\u0017\f\u0005-\u0001\u0016M]%uKJ\f'\r\\3\u0011\r)zc$K\u00193\u0013\t\u00014B\u0001\u0006QCJ\u001cV\r\u001e'jW\u0016\u00042A\u000b\u0001\u001f!\r\u0019DGH\u0007\u0002\u001b%\u0011Q'\u0004\u0002\u0004'\u0016$\u0018A\u0002\u0013j]&$H\u0005F\u00019!\t)\u0012(\u0003\u0002;\u001f\t!QK\\5u\u0003\u0015)W\u000e\u001d;z+\u0005\t\u0014!C2p[B\fg.[8o+\u0005y\u0004cA\rAS%\u0011\u0011I\u0007\u0002\u0014\u000f\u0016tWM]5d!\u0006\u00148i\\7qC:LwN\\\u0001\rgR\u0014\u0018N\\4Qe\u00164\u0017\u000e_\u000b\u0002\tB\u0011QIS\u0007\u0002\r*\u0011q\tS\u0001\u0005Y\u0006twMC\u0001J\u0003\u0011Q\u0017M^1\n\u0005-3%AB*ue&tw-\u0001\u0004QCJ\u001cV\r\u001e\t\u0003U\u0019\u0019\"AB(\u0011\u0007e\u0001\u0016&\u0003\u0002R5\ti\u0001+\u0019:TKR4\u0015m\u0019;pef\fa\u0001P5oSRtD#A'\u0002\u00179,woQ8nE&tWM]\u000b\u0003-n+\u0012a\u0016\t\u0005UaSF,\u0003\u0002Z\u0017\tA1i\\7cS:,'\u000f\u0005\u0002 7\u0012)\u0011\u0005\u0003b\u0001EA\u0019!\u0006\u0001.\u0002\u0019\r\fgNQ;jY\u00124%o\\7\u0016\u0007}+\u0007.F\u0001a!\u0015I\u0012mY4j\u0013\t\u0011'D\u0001\bDC:\u001cu.\u001c2j]\u00164%o\\7\u0011\u0007)\u0002A\r\u0005\u0002 K\u0012)a-\u0003b\u0001E\t\t1\u000b\u0005\u0002 Q\u0012)\u0011%\u0003b\u0001EA\u0019!\u0006A4"
)
public interface ParSet extends ParIterable, ParSetLike {
   static CanCombineFrom canBuildFrom() {
      return ParSet$.MODULE$.canBuildFrom();
   }

   static Factory toFactory() {
      return ParSet$.MODULE$.toFactory();
   }

   // $FF: synthetic method
   static ParSet empty$(final ParSet $this) {
      return $this.empty();
   }

   default ParSet empty() {
      return (ParSet)ParHashSet$.MODULE$.apply(.MODULE$);
   }

   // $FF: synthetic method
   static GenericParCompanion companion$(final ParSet $this) {
      return $this.companion();
   }

   default GenericParCompanion companion() {
      return ParSet$.MODULE$;
   }

   // $FF: synthetic method
   static String stringPrefix$(final ParSet $this) {
      return $this.stringPrefix();
   }

   default String stringPrefix() {
      return "ParSet";
   }

   static void $init$(final ParSet $this) {
   }
}
