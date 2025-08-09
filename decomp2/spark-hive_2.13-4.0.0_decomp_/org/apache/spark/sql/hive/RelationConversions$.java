package org.apache.spark.sql.hive;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class RelationConversions$ extends AbstractFunction1 implements Serializable {
   public static final RelationConversions$ MODULE$ = new RelationConversions$();

   public final String toString() {
      return "RelationConversions";
   }

   public RelationConversions apply(final HiveSessionCatalog sessionCatalog) {
      return new RelationConversions(sessionCatalog);
   }

   public Option unapply(final RelationConversions x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.sessionCatalog()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(RelationConversions$.class);
   }

   private RelationConversions$() {
   }
}
