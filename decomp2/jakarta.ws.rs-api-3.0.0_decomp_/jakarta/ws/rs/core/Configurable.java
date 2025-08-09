package jakarta.ws.rs.core;

import java.util.Map;

public interface Configurable {
   Configuration getConfiguration();

   Configurable property(String var1, Object var2);

   Configurable register(Class var1);

   Configurable register(Class var1, int var2);

   Configurable register(Class var1, Class... var2);

   Configurable register(Class var1, Map var2);

   Configurable register(Object var1);

   Configurable register(Object var1, int var2);

   Configurable register(Object var1, Class... var2);

   Configurable register(Object var1, Map var2);
}
