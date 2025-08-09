package org.apache.spark.deploy.k8s.submit;

import org.apache.spark.SparkConf;
import org.apache.spark.deploy.k8s.Config$;
import scala.Option;

public final class K8SSparkSubmitOperation$ {
   public static final K8SSparkSubmitOperation$ MODULE$ = new K8SSparkSubmitOperation$();

   public Option getGracePeriod(final SparkConf sparkConf) {
      return (Option)sparkConf.get(Config$.MODULE$.KUBERNETES_SUBMIT_GRACE_PERIOD());
   }

   private K8SSparkSubmitOperation$() {
   }
}
