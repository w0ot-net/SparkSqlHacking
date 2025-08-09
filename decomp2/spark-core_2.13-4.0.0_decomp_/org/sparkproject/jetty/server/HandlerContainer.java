package org.sparkproject.jetty.server;

import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.LifeCycle;

@ManagedObject("Handler of Multiple Handlers")
public interface HandlerContainer extends LifeCycle {
   @ManagedAttribute("handlers in this container")
   Handler[] getHandlers();

   @ManagedAttribute("all contained handlers")
   Handler[] getChildHandlers();

   Handler[] getChildHandlersByClass(Class var1);

   Handler getChildHandlerByClass(Class var1);
}
