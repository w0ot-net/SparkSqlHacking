package breeze.util;

import scala.Function0;
import scala.runtime.ModuleSerializationProxy;

public final class HashIndex$ implements SerializableLogging {
   public static final HashIndex$ MODULE$ = new HashIndex$();
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

   public void breeze$util$HashIndex$$logError(final Function0 str) {
      this.logger().error(str);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashIndex$.class);
   }

   private HashIndex$() {
   }
}
