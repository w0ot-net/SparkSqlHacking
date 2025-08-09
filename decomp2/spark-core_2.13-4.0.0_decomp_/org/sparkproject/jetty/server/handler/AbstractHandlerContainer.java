package org.sparkproject.jetty.server.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.Server;

public abstract class AbstractHandlerContainer extends AbstractHandler implements HandlerContainer {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractHandlerContainer.class);

   public Handler[] getChildHandlers() {
      List<Handler> list = new ArrayList();
      this.expandChildren(list, (Class)null);
      return (Handler[])list.toArray(new Handler[list.size()]);
   }

   public Handler[] getChildHandlersByClass(Class byclass) {
      List<Handler> list = new ArrayList();
      this.expandChildren(list, byclass);
      return (Handler[])list.toArray(new Handler[list.size()]);
   }

   public Handler getChildHandlerByClass(Class byclass) {
      List<Handler> list = new ArrayList();
      this.expandChildren(list, byclass);
      return list.isEmpty() ? null : (Handler)list.get(0);
   }

   protected void expandChildren(List list, Class byClass) {
   }

   protected void expandHandler(Handler handler, List list, Class byClass) {
      if (handler != null) {
         if (byClass == null || byClass.isAssignableFrom(handler.getClass())) {
            list.add(handler);
         }

         if (handler instanceof AbstractHandlerContainer) {
            ((AbstractHandlerContainer)handler).expandChildren(list, byClass);
         } else if (handler instanceof HandlerContainer) {
            HandlerContainer container = (HandlerContainer)handler;
            Handler[] handlers = byClass == null ? container.getChildHandlers() : container.getChildHandlersByClass(byClass);
            list.addAll(Arrays.asList(handlers));
         }

      }
   }

   public static HandlerContainer findContainerOf(HandlerContainer root, Class type, Handler handler) {
      if (root != null && handler != null) {
         Handler[] branches = root.getChildHandlersByClass(type);
         if (branches != null) {
            for(Handler h : branches) {
               T container = (T)((HandlerContainer)h);
               Handler[] candidates = container.getChildHandlersByClass(handler.getClass());
               if (candidates != null) {
                  for(Handler c : candidates) {
                     if (c == handler) {
                        return container;
                     }
                  }
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void setServer(Server server) {
      if (server != this.getServer()) {
         if (this.isStarted()) {
            throw new IllegalStateException(this.getState());
         } else {
            super.setServer(server);
            Handler[] handlers = this.getHandlers();
            if (handlers != null) {
               for(Handler h : handlers) {
                  h.setServer(server);
               }
            }

         }
      }
   }
}
