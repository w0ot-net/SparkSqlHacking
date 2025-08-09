package scala.xml;

import scala.Some;
import scala.Tuple4;

public final class QNode$ {
   public static final QNode$ MODULE$ = new QNode$();

   public Some unapplySeq(final Node n) {
      return new Some(new Tuple4(n.scope().getURI(n.prefix()), n.label(), n.attributes(), n.child().toSeq()));
   }

   private QNode$() {
   }
}
