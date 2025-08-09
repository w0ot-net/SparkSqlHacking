package jakarta.ws.rs.core;

import jakarta.ws.rs.RuntimeType;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Configuration {
   RuntimeType getRuntimeType();

   Map getProperties();

   Object getProperty(String var1);

   Collection getPropertyNames();

   boolean isEnabled(Feature var1);

   boolean isEnabled(Class var1);

   boolean isRegistered(Object var1);

   boolean isRegistered(Class var1);

   Map getContracts(Class var1);

   Set getClasses();

   Set getInstances();
}
