package jakarta.ws.rs.client;

import jakarta.ws.rs.core.GenericType;
import java.util.concurrent.CompletionStage;

public interface CompletionStageRxInvoker extends RxInvoker {
   CompletionStage get();

   CompletionStage get(Class var1);

   CompletionStage get(GenericType var1);

   CompletionStage put(Entity var1);

   CompletionStage put(Entity var1, Class var2);

   CompletionStage put(Entity var1, GenericType var2);

   CompletionStage post(Entity var1);

   CompletionStage post(Entity var1, Class var2);

   CompletionStage post(Entity var1, GenericType var2);

   CompletionStage delete();

   CompletionStage delete(Class var1);

   CompletionStage delete(GenericType var1);

   CompletionStage head();

   CompletionStage options();

   CompletionStage options(Class var1);

   CompletionStage options(GenericType var1);

   CompletionStage trace();

   CompletionStage trace(Class var1);

   CompletionStage trace(GenericType var1);

   CompletionStage method(String var1);

   CompletionStage method(String var1, Class var2);

   CompletionStage method(String var1, GenericType var2);

   CompletionStage method(String var1, Entity var2);

   CompletionStage method(String var1, Entity var2, Class var3);

   CompletionStage method(String var1, Entity var2, GenericType var3);
}
