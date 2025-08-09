package org.apache.spark.sql.internal;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class UnresolvedNamedLambdaVariable$ implements Serializable {
   public static final UnresolvedNamedLambdaVariable$ MODULE$ = new UnresolvedNamedLambdaVariable$();
   private static final AtomicLong nextId = new AtomicLong();

   public Origin $lessinit$greater$default$2() {
      return CurrentOrigin$.MODULE$.get();
   }

   private AtomicLong nextId() {
      return nextId;
   }

   public UnresolvedNamedLambdaVariable apply(final String name) {
      return new UnresolvedNamedLambdaVariable(name + "_" + this.nextId().incrementAndGet(), this.$lessinit$greater$default$2());
   }

   public Origin apply$default$2() {
      return CurrentOrigin$.MODULE$.get();
   }

   public void resetIdGenerator() {
      this.nextId().set(0L);
   }

   public UnresolvedNamedLambdaVariable apply(final String name, final Origin origin) {
      return new UnresolvedNamedLambdaVariable(name, origin);
   }

   public Option unapply(final UnresolvedNamedLambdaVariable x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.name(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedNamedLambdaVariable$.class);
   }

   private UnresolvedNamedLambdaVariable$() {
   }
}
