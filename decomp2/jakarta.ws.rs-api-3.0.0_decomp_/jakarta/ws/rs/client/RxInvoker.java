package jakarta.ws.rs.client;

import jakarta.ws.rs.core.GenericType;

public interface RxInvoker {
   Object get();

   Object get(Class var1);

   Object get(GenericType var1);

   Object put(Entity var1);

   Object put(Entity var1, Class var2);

   Object put(Entity var1, GenericType var2);

   Object post(Entity var1);

   Object post(Entity var1, Class var2);

   Object post(Entity var1, GenericType var2);

   Object delete();

   Object delete(Class var1);

   Object delete(GenericType var1);

   Object head();

   Object options();

   Object options(Class var1);

   Object options(GenericType var1);

   Object trace();

   Object trace(Class var1);

   Object trace(GenericType var1);

   Object method(String var1);

   Object method(String var1, Class var2);

   Object method(String var1, GenericType var2);

   Object method(String var1, Entity var2);

   Object method(String var1, Entity var2, Class var3);

   Object method(String var1, Entity var2, GenericType var3);
}
