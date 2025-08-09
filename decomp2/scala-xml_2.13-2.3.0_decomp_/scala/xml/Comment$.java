package scala.xml;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class Comment$ extends AbstractFunction1 implements Serializable {
   public static final Comment$ MODULE$ = new Comment$();

   public final String toString() {
      return "Comment";
   }

   public Comment apply(final String commentText) {
      return new Comment(commentText);
   }

   public Option unapply(final Comment x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.commentText()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Comment$.class);
   }

   private Comment$() {
   }
}
