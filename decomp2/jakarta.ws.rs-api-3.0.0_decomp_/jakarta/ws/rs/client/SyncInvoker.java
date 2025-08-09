package jakarta.ws.rs.client;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

public interface SyncInvoker {
   Response get();

   Object get(Class var1);

   Object get(GenericType var1);

   Response put(Entity var1);

   Object put(Entity var1, Class var2);

   Object put(Entity var1, GenericType var2);

   Response post(Entity var1);

   Object post(Entity var1, Class var2);

   Object post(Entity var1, GenericType var2);

   Response delete();

   Object delete(Class var1);

   Object delete(GenericType var1);

   Response head();

   Response options();

   Object options(Class var1);

   Object options(GenericType var1);

   Response trace();

   Object trace(Class var1);

   Object trace(GenericType var1);

   Response method(String var1);

   Object method(String var1, Class var2);

   Object method(String var1, GenericType var2);

   Response method(String var1, Entity var2);

   Object method(String var1, Entity var2, Class var3);

   Object method(String var1, Entity var2, GenericType var3);
}
