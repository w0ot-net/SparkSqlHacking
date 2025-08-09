package org.sparkproject.jetty.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.component.Destroyable;
import org.sparkproject.jetty.util.component.LifeCycle;

@ManagedObject("Jetty Handler")
public interface Handler extends LifeCycle, Destroyable {
   void handle(String var1, Request var2, HttpServletRequest var3, HttpServletResponse var4) throws IOException, ServletException;

   void setServer(Server var1);

   @ManagedAttribute(
      value = "the jetty server for this handler",
      readonly = true
   )
   Server getServer();

   @ManagedOperation(
      value = "destroy associated resources",
      impact = "ACTION"
   )
   void destroy();
}
