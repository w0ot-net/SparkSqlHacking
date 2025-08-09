package javax.servlet;

import java.util.Set;

public interface ServletContainerInitializer {
   void onStartup(Set var1, ServletContext var2) throws ServletException;
}
