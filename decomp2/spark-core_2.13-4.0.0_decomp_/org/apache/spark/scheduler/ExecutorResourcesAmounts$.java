package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class ExecutorResourcesAmounts$ implements Serializable {
   public static final ExecutorResourcesAmounts$ MODULE$ = new ExecutorResourcesAmounts$();

   public ExecutorResourcesAmounts empty() {
      return new ExecutorResourcesAmounts(.MODULE$.Map().empty());
   }

   public ExecutorResourcesAmounts apply(final Map executorInfos) {
      return new ExecutorResourcesAmounts((Map)executorInfos.map((x0$1) -> {
         if (x0$1 != null) {
            String rName = (String)x0$1._1();
            ExecutorResourceInfo rInfo = (ExecutorResourceInfo)x0$1._2();
            return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc(rName), rInfo.resourcesAmounts());
         } else {
            throw new MatchError(x0$1);
         }
      }));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExecutorResourcesAmounts$.class);
   }

   private ExecutorResourcesAmounts$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
