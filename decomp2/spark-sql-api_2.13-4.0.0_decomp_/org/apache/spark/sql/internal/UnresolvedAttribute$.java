package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.util.AttributeNameParser$;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class UnresolvedAttribute$ implements Serializable {
   public static final UnresolvedAttribute$ MODULE$ = new UnresolvedAttribute$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      return new UnresolvedAttribute(AttributeNameParser$.MODULE$.parseAttributeName(unparsedIdentifier), planId, isMetadataColumn, origin);
   }

   public UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId, final boolean isMetadataColumn) {
      return this.apply(unparsedIdentifier, planId, isMetadataColumn, CurrentOrigin$.MODULE$.get());
   }

   public UnresolvedAttribute apply(final String unparsedIdentifier, final Option planId) {
      return this.apply(unparsedIdentifier, planId, false, CurrentOrigin$.MODULE$.get());
   }

   public UnresolvedAttribute apply(final String unparsedIdentifier) {
      return this.apply((String)unparsedIdentifier, .MODULE$, false, CurrentOrigin$.MODULE$.get());
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public boolean apply$default$3() {
      return false;
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public UnresolvedAttribute apply(final Seq nameParts, final Option planId, final boolean isMetadataColumn, final Origin origin) {
      return new UnresolvedAttribute(nameParts, planId, isMetadataColumn, origin);
   }

   public Option unapply(final UnresolvedAttribute x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.nameParts(), x$0.planId(), BoxesRunTime.boxToBoolean(x$0.isMetadataColumn()), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedAttribute$.class);
   }

   private UnresolvedAttribute$() {
   }
}
