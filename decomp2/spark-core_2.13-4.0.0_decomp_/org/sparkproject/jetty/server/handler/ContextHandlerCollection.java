package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.HttpChannelState;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.util.ArrayUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.thread.SerializedExecutor;

@ManagedObject("Context Handler Collection")
public class ContextHandlerCollection extends HandlerCollection {
   private static final Logger LOG = LoggerFactory.getLogger(ContextHandlerCollection.class);
   private final SerializedExecutor _serializedExecutor = new SerializedExecutor();

   public ContextHandlerCollection() {
      super(true);
   }

   public ContextHandlerCollection(ContextHandler... contexts) {
      super(true);
      this.setHandlers(contexts);
   }

   @ManagedOperation("Update the mapping of context path to context")
   public void mapContexts() {
      this._serializedExecutor.execute(() -> {
         HandlerCollection.Handlers handlers;
         do {
            handlers = (HandlerCollection.Handlers)this._handlers.get();
         } while(handlers != null && !this.updateHandlers(handlers, this.newHandlers(handlers.getHandlers())));

      });
   }

   protected HandlerCollection.Handlers newHandlers(Handler[] handlers) {
      if (handlers != null && handlers.length != 0) {
         Map<String, Branch[]> path2Branches = new HashMap();

         for(Handler handler : handlers) {
            Branch branch = new Branch(handler);

            for(String contextPath : branch.getContextPaths()) {
               Branch[] branches = (Branch[])path2Branches.get(contextPath);
               path2Branches.put(contextPath, (Branch[])ArrayUtil.addToArray(branches, branch, Branch.class));
            }
         }

         for(Map.Entry entry : path2Branches.entrySet()) {
            Branch[] branches = (Branch[])entry.getValue();
            Branch[] sorted = new Branch[branches.length];
            int i = 0;

            for(Branch branch : branches) {
               if (branch.hasVirtualHost()) {
                  sorted[i++] = branch;
               }
            }

            for(Branch branch : branches) {
               if (!branch.hasVirtualHost()) {
                  sorted[i++] = branch;
               }
            }

            entry.setValue(sorted);
         }

         Mapping mapping = new Mapping(handlers, path2Branches);
         if (LOG.isDebugEnabled()) {
            LOG.debug("{}", mapping._pathBranches);
         }

         return mapping;
      } else {
         return null;
      }
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Mapping mapping = (Mapping)this._handlers.get();
      if (mapping != null) {
         Handler[] handlers = mapping.getHandlers();
         if (handlers != null && handlers.length != 0) {
            if (handlers.length == 1) {
               handlers[0].handle(target, baseRequest, request, response);
            } else {
               HttpChannelState async = baseRequest.getHttpChannelState();
               if (async.isAsync()) {
                  ContextHandler context = async.getContextHandler();
                  if (context != null) {
                     Handler branch = (Handler)mapping._contextBranches.get(context);
                     if (branch == null) {
                        context.handle(target, baseRequest, request, response);
                     } else {
                        branch.handle(target, baseRequest, request, response);
                     }

                     return;
                  }
               }

               if (target.startsWith("/")) {
                  Index<Map.Entry<String, Branch[]>> pathBranches = mapping._pathBranches;
                  if (pathBranches == null) {
                     return;
                  }

                  int l;
                  for(int limit = target.length() - 1; limit >= 0; limit = l - 2) {
                     Map.Entry<String, Branch[]> branches = (Map.Entry)pathBranches.getBest((String)target, 1, limit);
                     if (branches == null) {
                        break;
                     }

                     l = ((String)branches.getKey()).length();
                     if (l == 1 || target.length() == l || target.charAt(l) == '/') {
                        for(Branch branch : (Branch[])branches.getValue()) {
                           branch.getHandler().handle(target, baseRequest, request, response);
                           if (baseRequest.isHandled()) {
                              return;
                           }
                        }
                     }
                  }
               } else {
                  for(Handler handler : handlers) {
                     handler.handle(target, baseRequest, request, response);
                     if (baseRequest.isHandled()) {
                        return;
                     }
                  }
               }

            }
         }
      }
   }

   public void deployHandler(final Handler handler, final Callback callback) {
      if (handler.getServer() != this.getServer()) {
         handler.setServer(this.getServer());
      }

      this._serializedExecutor.execute(new SerializedExecutor.ErrorHandlingTask() {
         public void run() {
            ContextHandlerCollection.this.addHandler(handler);
            callback.succeeded();
         }

         public void accept(Throwable throwable) {
            callback.failed(throwable);
         }
      });
   }

   public void undeployHandler(final Handler handler, final Callback callback) {
      this._serializedExecutor.execute(new SerializedExecutor.ErrorHandlingTask() {
         public void run() {
            ContextHandlerCollection.this.removeHandler(handler);
            callback.succeeded();
         }

         public void accept(Throwable throwable) {
            callback.failed(throwable);
         }
      });
   }

   private static final class Branch {
      private final Handler _handler;
      private final ContextHandler[] _contexts;

      Branch(Handler handler) {
         this._handler = handler;
         if (handler instanceof ContextHandler) {
            this._contexts = new ContextHandler[]{(ContextHandler)handler};
         } else if (handler instanceof HandlerContainer) {
            Handler[] contexts = ((HandlerContainer)handler).getChildHandlersByClass(ContextHandler.class);
            this._contexts = new ContextHandler[contexts.length];
            System.arraycopy(contexts, 0, this._contexts, 0, contexts.length);
         } else {
            this._contexts = new ContextHandler[0];
         }

      }

      Set getContextPaths() {
         Set<String> set = new HashSet();

         for(ContextHandler context : this._contexts) {
            set.add(context.getContextPath());
         }

         return set;
      }

      boolean hasVirtualHost() {
         for(ContextHandler context : this._contexts) {
            if (context.getVirtualHosts() != null && context.getVirtualHosts().length > 0) {
               return true;
            }
         }

         return false;
      }

      ContextHandler[] getContextHandlers() {
         return this._contexts;
      }

      Handler getHandler() {
         return this._handler;
      }

      public String toString() {
         return String.format("{%s,%s}", this._handler, Arrays.asList(this._contexts));
      }
   }

   private static class Mapping extends HandlerCollection.Handlers {
      private final Map _contextBranches;
      private final Index _pathBranches;

      private Mapping(Handler[] handlers, Map path2Branches) {
         super(handlers);
         this._pathBranches = (new Index.Builder()).caseSensitive(true).withAll(() -> {
            Map<String, Map.Entry<String, Branch[]>> result = new LinkedHashMap();

            for(Map.Entry entry : path2Branches.entrySet()) {
               result.put(((String)entry.getKey()).substring(1), entry);
            }

            return result;
         }).build();
         Map<ContextHandler, Handler> contextBranches = new HashMap();

         for(Branch[] branches : path2Branches.values()) {
            for(Branch branch : branches) {
               for(ContextHandler context : branch.getContextHandlers()) {
                  contextBranches.put(context, branch.getHandler());
               }
            }
         }

         this._contextBranches = Collections.unmodifiableMap(contextBranches);
      }
   }
}
