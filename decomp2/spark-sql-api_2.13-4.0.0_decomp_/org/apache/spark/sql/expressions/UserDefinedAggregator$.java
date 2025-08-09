package org.apache.spark.sql.expressions;

import java.io.Serializable;
import org.apache.spark.sql.Encoder;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class UserDefinedAggregator$ implements Serializable {
   public static final UserDefinedAggregator$ MODULE$ = new UserDefinedAggregator$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$4() {
      return true;
   }

   public boolean $lessinit$greater$default$5() {
      return true;
   }

   public final String toString() {
      return "UserDefinedAggregator";
   }

   public UserDefinedAggregator apply(final Aggregator aggregator, final Encoder inputEncoder, final Option givenName, final boolean nullable, final boolean deterministic) {
      return new UserDefinedAggregator(aggregator, inputEncoder, givenName, nullable, deterministic);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public boolean apply$default$4() {
      return true;
   }

   public boolean apply$default$5() {
      return true;
   }

   public Option unapply(final UserDefinedAggregator x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.aggregator(), x$0.inputEncoder(), x$0.givenName(), BoxesRunTime.boxToBoolean(x$0.nullable()), BoxesRunTime.boxToBoolean(x$0.deterministic()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UserDefinedAggregator$.class);
   }

   private UserDefinedAggregator$() {
   }
}
