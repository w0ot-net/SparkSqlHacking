package scala.collection;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0019\u0005q\u0003C\u0003%\u0001\u0011\u0005Q\u0005\u0003\u0004.\u0001\u00016\tB\f\u0002\u000f!\u0006\u0014\u0018\r\u001c7fY&T\u0018M\u00197f\u0015\t9\u0001\"\u0001\u0006d_2dWm\u0019;j_:T\u0011!C\u0001\u0006g\u000e\fG.Y\u0002\u0001+\raadJ\n\u0003\u00015\u0001\"AD\b\u000e\u0003!I!\u0001\u0005\u0005\u0003\u0007\u0005s\u00170\u0001\u0004%S:LG\u000f\n\u000b\u0002'A\u0011a\u0002F\u0005\u0003+!\u0011A!\u00168ji\u0006\u00191/Z9\u0016\u0003a\u00012!\u0007\u000e\u001d\u001b\u00051\u0011BA\u000e\u0007\u00051IE/\u001a:bE2,wJ\\2f!\tib\u0004\u0004\u0001\u0005\r}\u0001AQ1\u0001!\u0005\u0005\t\u0015CA\u0011\u000e!\tq!%\u0003\u0002$\u0011\t9aj\u001c;iS:<\u0017a\u00019beV\ta\u0005\u0005\u0002\u001eO\u00111\u0001\u0006\u0001CC\u0002%\u0012q\u0001U1s%\u0016\u0004(/\u0005\u0002\"UA\u0011\u0011dK\u0005\u0003Y\u0019\u0011\u0001\u0002U1sC2dW\r\\\u0001\fa\u0006\u00148i\\7cS:,'/F\u00010!\u0011\u00014'\u000e\u0014\u000e\u0003ER!A\r\u0004\u0002\u0011A\f'/\u00197mK2L!\u0001N\u0019\u0003\u0011\r{WNY5oKJT#\u0001\b\u001c,\u0003]\u0002\"\u0001O\u001f\u000e\u0003eR!AO\u001e\u0002\u0013Ut7\r[3dW\u0016$'B\u0001\u001f\t\u0003)\tgN\\8uCRLwN\\\u0005\u0003}e\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0001"
)
public interface Parallelizable {
   IterableOnce seq();

   // $FF: synthetic method
   static Parallel par$(final Parallelizable $this) {
      return $this.par();
   }

   default Parallel par() {
      return (Parallel)this.parCombiner().fromSequential(this.seq());
   }

   Combiner parCombiner();

   static void $init$(final Parallelizable $this) {
   }
}
