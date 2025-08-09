package breeze.optimize;

import breeze.linalg.DenseVector;
import breeze.util.LazyLogger;
import breeze.util.SerializableLogging;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Predef.;
import scala.runtime.ModuleSerializationProxy;

public final class ProjectedQuasiNewton$ implements SerializableLogging {
   public static final ProjectedQuasiNewton$ MODULE$ = new ProjectedQuasiNewton$();
   private static transient volatile LazyLogger breeze$util$SerializableLogging$$_the_logger;

   static {
      SerializableLogging.$init$(MODULE$);
   }

   public LazyLogger logger() {
      return SerializableLogging.logger$(this);
   }

   public LazyLogger breeze$util$SerializableLogging$$_the_logger() {
      return breeze$util$SerializableLogging$$_the_logger;
   }

   public void breeze$util$SerializableLogging$$_the_logger_$eq(final LazyLogger x$1) {
      breeze$util$SerializableLogging$$_the_logger = x$1;
   }

   public double $lessinit$greater$default$1() {
      return 1.0E-6;
   }

   public int $lessinit$greater$default$2() {
      return 10;
   }

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public boolean $lessinit$greater$default$4() {
      return true;
   }

   public int $lessinit$greater$default$5() {
      return -1;
   }

   public int $lessinit$greater$default$6() {
      return 50;
   }

   public double $lessinit$greater$default$7() {
      return 1.0E-4;
   }

   public Function1 $lessinit$greater$default$8() {
      return (x) -> (DenseVector).MODULE$.identity(x);
   }

   public boolean $lessinit$greater$default$9() {
      return true;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ProjectedQuasiNewton$.class);
   }

   private ProjectedQuasiNewton$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
