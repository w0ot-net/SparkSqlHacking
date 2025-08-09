package org.apache.spark.sql;

import java.io.Serializable;
import org.apache.spark.QueryContext;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class AnalysisException$ implements Serializable {
   public static final AnalysisException$ MODULE$ = new AnalysisException$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public Map $lessinit$greater$default$6() {
      return scala.Predef..MODULE$.Map().empty();
   }

   public QueryContext[] $lessinit$greater$default$7() {
      return (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AnalysisException$.class);
   }

   private AnalysisException$() {
   }
}
