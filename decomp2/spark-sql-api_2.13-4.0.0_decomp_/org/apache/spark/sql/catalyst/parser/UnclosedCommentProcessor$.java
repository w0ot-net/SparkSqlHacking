package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import org.antlr.v4.runtime.CommonTokenStream;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class UnclosedCommentProcessor$ extends AbstractFunction2 implements Serializable {
   public static final UnclosedCommentProcessor$ MODULE$ = new UnclosedCommentProcessor$();

   public final String toString() {
      return "UnclosedCommentProcessor";
   }

   public UnclosedCommentProcessor apply(final String command, final CommonTokenStream tokenStream) {
      return new UnclosedCommentProcessor(command, tokenStream);
   }

   public Option unapply(final UnclosedCommentProcessor x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.command(), x$0.tokenStream())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnclosedCommentProcessor$.class);
   }

   private UnclosedCommentProcessor$() {
   }
}
