package jakarta.ws.rs.client;

import jakarta.ws.rs.core.CacheControl;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import java.util.Locale;
import java.util.concurrent.Future;

public interface Invocation {
   Invocation property(String var1, Object var2);

   Response invoke();

   Object invoke(Class var1);

   Object invoke(GenericType var1);

   Future submit();

   Future submit(Class var1);

   Future submit(GenericType var1);

   Future submit(InvocationCallback var1);

   public interface Builder extends SyncInvoker {
      Invocation build(String var1);

      Invocation build(String var1, Entity var2);

      Invocation buildGet();

      Invocation buildDelete();

      Invocation buildPost(Entity var1);

      Invocation buildPut(Entity var1);

      AsyncInvoker async();

      Builder accept(String... var1);

      Builder accept(MediaType... var1);

      Builder acceptLanguage(Locale... var1);

      Builder acceptLanguage(String... var1);

      Builder acceptEncoding(String... var1);

      Builder cookie(Cookie var1);

      Builder cookie(String var1, String var2);

      Builder cacheControl(CacheControl var1);

      Builder header(String var1, Object var2);

      Builder headers(MultivaluedMap var1);

      Builder property(String var1, Object var2);

      CompletionStageRxInvoker rx();

      RxInvoker rx(Class var1);
   }
}
