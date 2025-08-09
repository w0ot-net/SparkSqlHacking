package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.SQLQueryContext;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class ParseException$ implements Serializable {
   public static final ParseException$ MODULE$ = new ParseException$();

   private Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   private Map $lessinit$greater$default$6() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public QueryContext[] org$apache$spark$sql$catalyst$parser$ParseException$$$lessinit$greater$default$7() {
      return this.getQueryContext();
   }

   public QueryContext[] getQueryContext() {
      return (QueryContext[])scala.Option..MODULE$.option2Iterable((new Some(CurrentOrigin$.MODULE$.get().context())).collect(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final QueryContext x1, final Function1 default) {
            if (x1 instanceof SQLQueryContext var5) {
               if (var5.isValid()) {
                  return var5;
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final QueryContext x1) {
            if (x1 instanceof SQLQueryContext var4) {
               if (var4.isValid()) {
                  return true;
               }
            }

            return false;
         }
      })).toArray(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParseException$.class);
   }

   private ParseException$() {
   }
}
