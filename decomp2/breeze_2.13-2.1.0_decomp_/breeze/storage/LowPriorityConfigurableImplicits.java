package breeze.storage;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012b\n\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002!\u0019><\bK]5pe&$\u0018pQ8oM&<WO]1cY\u0016LU\u000e\u001d7jG&$8O\u0003\u0002\u0006\r\u000591\u000f^8sC\u001e,'\"A\u0004\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\u0006\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0003\u0005\u0002\f'%\u0011A\u0003\u0004\u0002\u0005+:LG/A\u0004eK\u001a\fW\u000f\u001c;\u0016\u0005]qR#\u0001\r\u0011\u0007eQB$D\u0001\u0005\u0013\tYBAA\nD_:4\u0017nZ;sC\ndW\rR3gCVdG\u000f\u0005\u0002\u001e=1\u0001A!B\u0010\u0003\u0005\u0004\u0001#!\u0001,\u0012\u0005\u0005\"\u0003CA\u0006#\u0013\t\u0019CBA\u0004O_RD\u0017N\\4\u0011\u0005-)\u0013B\u0001\u0014\r\u0005\r\te.\u001f\b\u00033!J!!\u000b\u0003\u0002'\r{gNZ5hkJ\f'\r\\3EK\u001a\fW\u000f\u001c;*\u0005\u0001Y#BA\u0015\u0005\u0001"
)
public interface LowPriorityConfigurableImplicits {
   // $FF: synthetic method
   static ConfigurableDefault default$(final LowPriorityConfigurableImplicits $this) {
      return $this.default();
   }

   default ConfigurableDefault default() {
      return new ConfigurableDefault.DefaultConfigurableDefault();
   }

   static void $init$(final LowPriorityConfigurableImplicits $this) {
   }
}
