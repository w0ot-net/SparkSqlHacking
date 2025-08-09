package jakarta.ws.rs.client;

import java.util.concurrent.ExecutorService;

public interface RxInvokerProvider {
   boolean isProviderFor(Class var1);

   RxInvoker getRxInvoker(SyncInvoker var1, ExecutorService var2);
}
