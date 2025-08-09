package breeze.storage;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ConfigurableDefault$ implements LowPriorityConfigurableImplicits, Serializable {
   public static final ConfigurableDefault$ MODULE$ = new ConfigurableDefault$();

   static {
      LowPriorityConfigurableImplicits.$init$(MODULE$);
   }

   public ConfigurableDefault default() {
      return LowPriorityConfigurableImplicits.default$(this);
   }

   public ConfigurableDefault fromV(final Object v) {
      return new ConfigurableDefault.ValuedDefault(v);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ConfigurableDefault$.class);
   }

   private ConfigurableDefault$() {
   }
}
