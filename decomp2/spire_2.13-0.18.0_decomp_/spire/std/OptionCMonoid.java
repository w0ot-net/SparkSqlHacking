package spire.std;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2Aa\u0001\u0003\u0001\u0013!A\u0001\u0007\u0001B\u0002B\u0003-\u0011\u0007C\u00035\u0001\u0011\u0005QGA\u0007PaRLwN\\\"N_:|\u0017\u000e\u001a\u0006\u0003\u000b\u0019\t1a\u001d;e\u0015\u00059\u0011!B:qSJ,7\u0001A\u000b\u0003\u0015E\u00192\u0001A\u0006\u001e!\raQbD\u0007\u0002\t%\u0011a\u0002\u0002\u0002\r\u001fB$\u0018n\u001c8N_:|\u0017\u000e\u001a\t\u0003!Ea\u0001\u0001B\u0003\u0013\u0001\t\u00071CA\u0001B#\t!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbCA\u0004O_RD\u0017N\\4\u0011\u0005UY\u0012B\u0001\u000f\u0017\u0005\r\te.\u001f\t\u0004=)jcBA\u0010(\u001d\t\u0001SE\u0004\u0002\"I5\t!E\u0003\u0002$\u0011\u00051AH]8pizJ\u0011aB\u0005\u0003M\u0019\tq!\u00197hK\n\u0014\u0018-\u0003\u0002)S\u00059\u0001/Y2lC\u001e,'B\u0001\u0014\u0007\u0013\tYCFA\u0004D\u001b>tw.\u001b3\u000b\u0005!J\u0003cA\u000b/\u001f%\u0011qF\u0006\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002\u001fe=I!a\r\u0017\u0003\u0015\r\u001bV-\\5he>,\b/\u0001\u0004=S:LGO\u0010\u000b\u0002mQ\u0011q\u0007\u000f\t\u0004\u0019\u0001y\u0001\"\u0002\u0019\u0003\u0001\b\t\u0004\u0006\u0002\u0001;{y\u0002\"!F\u001e\n\u0005q2\"\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0001\u0001"
)
public class OptionCMonoid extends OptionMonoid implements CommutativeMonoid {
   private static final long serialVersionUID = 0L;

   public CommutativeMonoid reverse() {
      return CommutativeMonoid.reverse$(this);
   }

   public CommutativeMonoid reverse$mcD$sp() {
      return CommutativeMonoid.reverse$mcD$sp$(this);
   }

   public CommutativeMonoid reverse$mcF$sp() {
      return CommutativeMonoid.reverse$mcF$sp$(this);
   }

   public CommutativeMonoid reverse$mcI$sp() {
      return CommutativeMonoid.reverse$mcI$sp$(this);
   }

   public CommutativeMonoid reverse$mcJ$sp() {
      return CommutativeMonoid.reverse$mcJ$sp$(this);
   }

   public CommutativeSemigroup intercalate(final Object middle) {
      return CommutativeSemigroup.intercalate$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcD$sp(final double middle) {
      return CommutativeSemigroup.intercalate$mcD$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcF$sp(final float middle) {
      return CommutativeSemigroup.intercalate$mcF$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcI$sp(final int middle) {
      return CommutativeSemigroup.intercalate$mcI$sp$(this, middle);
   }

   public CommutativeSemigroup intercalate$mcJ$sp(final long middle) {
      return CommutativeSemigroup.intercalate$mcJ$sp$(this, middle);
   }

   public OptionCMonoid(final CommutativeSemigroup evidence$2) {
      super(evidence$2);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
