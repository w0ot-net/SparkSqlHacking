package jakarta.ws.rs.client;

import jakarta.ws.rs.core.GenericType;
import java.util.concurrent.Future;

public interface AsyncInvoker {
   Future get();

   Future get(Class var1);

   Future get(GenericType var1);

   Future get(InvocationCallback var1);

   Future put(Entity var1);

   Future put(Entity var1, Class var2);

   Future put(Entity var1, GenericType var2);

   Future put(Entity var1, InvocationCallback var2);

   Future post(Entity var1);

   Future post(Entity var1, Class var2);

   Future post(Entity var1, GenericType var2);

   Future post(Entity var1, InvocationCallback var2);

   Future delete();

   Future delete(Class var1);

   Future delete(GenericType var1);

   Future delete(InvocationCallback var1);

   Future head();

   Future head(InvocationCallback var1);

   Future options();

   Future options(Class var1);

   Future options(GenericType var1);

   Future options(InvocationCallback var1);

   Future trace();

   Future trace(Class var1);

   Future trace(GenericType var1);

   Future trace(InvocationCallback var1);

   Future method(String var1);

   Future method(String var1, Class var2);

   Future method(String var1, GenericType var2);

   Future method(String var1, InvocationCallback var2);

   Future method(String var1, Entity var2);

   Future method(String var1, Entity var2, Class var3);

   Future method(String var1, Entity var2, GenericType var3);

   Future method(String var1, Entity var2, InvocationCallback var3);
}
