package jakarta.ws.rs.container;

public interface ResourceContext {
   Object getResource(Class var1);

   Object initResource(Object var1);
}
