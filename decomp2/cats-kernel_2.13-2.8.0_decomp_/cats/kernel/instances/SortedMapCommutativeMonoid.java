package cats.kernel.instances;

import cats.kernel.CommutativeMonoid;
import cats.kernel.CommutativeSemigroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2A\u0001B\u0003\u0001\u0019!Aq\u0006\u0001B\u0001B\u0003-\u0001\u0007\u0003\u00054\u0001\t\u0005\t\u0015a\u00035\u0011\u00159\u0004\u0001\"\u00019\u0005i\u0019vN\u001d;fI6\u000b\u0007oQ8n[V$\u0018\r^5wK6{gn\\5e\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u0001QcA\u0007\u0015CM\u0019\u0001AD\u0012\u0011\t=\u0001\"\u0003I\u0007\u0002\u000b%\u0011\u0011#\u0002\u0002\u0010'>\u0014H/\u001a3NCBluN\\8jIB\u00111\u0003\u0006\u0007\u0001\t\u0015)\u0002A1\u0001\u0017\u0005\u0005Y\u0015CA\f\u001e!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0007\u0010\n\u0005}I\"aA!osB\u00111#\t\u0003\u0006E\u0001\u0011\rA\u0006\u0002\u0002-B\u0019A%J\u0014\u000e\u0003\u001dI!AJ\u0004\u0003#\r{W.\\;uCRLg/Z'p]>LG\r\u0005\u0003)[I\u0001S\"A\u0015\u000b\u0005)Z\u0013!C5n[V$\u0018M\u00197f\u0015\ta\u0013$\u0001\u0006d_2dWm\u0019;j_:L!AL\u0015\u0003\u0013M{'\u000f^3e\u001b\u0006\u0004\u0018!\u0001,\u0011\u0007\u0011\n\u0004%\u0003\u00023\u000f\t!2i\\7nkR\fG/\u001b<f'\u0016l\u0017n\u001a:pkB\f\u0011a\u0014\t\u0004IU\u0012\u0012B\u0001\u001c\b\u0005\u0015y%\u000fZ3s\u0003\u0019a\u0014N\\5u}Q\t\u0011\bF\u0002;wq\u0002Ba\u0004\u0001\u0013A!)qf\u0001a\u0002a!)1g\u0001a\u0002i\u0001"
)
public class SortedMapCommutativeMonoid extends SortedMapMonoid implements CommutativeMonoid {
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

   public SortedMapCommutativeMonoid(final CommutativeSemigroup V, final Order O) {
      super(V, O);
      CommutativeSemigroup.$init$(this);
      CommutativeMonoid.$init$(this);
   }
}
