package spire.math;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Point$ implements Serializable {
   public static final Point$ MODULE$ = new Point$();

   public final String toString() {
      return "Point";
   }

   public Point apply(final Object value) {
      return new Point(value);
   }

   public Option unapply(final Point x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.value()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Point$.class);
   }

   private Point$() {
   }
}
