package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sparkproject.jetty.server.Request;

public abstract class ScopedHandler extends HandlerWrapper {
   private static final ThreadLocal __outerScope = new ThreadLocal();
   protected ScopedHandler _outerScope;
   protected ScopedHandler _nextScope;

   protected void doStart() throws Exception {
      try {
         this._outerScope = (ScopedHandler)__outerScope.get();
         if (this._outerScope == null) {
            __outerScope.set(this);
         }

         super.doStart();
         this._nextScope = (ScopedHandler)this.getChildHandlerByClass(ScopedHandler.class);
      } finally {
         if (this._outerScope == null) {
            __outerScope.set((Object)null);
         }

      }

   }

   public final void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (this.isStarted()) {
         if (this._outerScope == null) {
            this.doScope(target, baseRequest, request, response);
         } else {
            this.doHandle(target, baseRequest, request, response);
         }
      }

   }

   public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      this.nextScope(target, baseRequest, request, response);
   }

   public final void nextScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (this._nextScope != null) {
         this._nextScope.doScope(target, baseRequest, request, response);
      } else if (this._outerScope != null) {
         this._outerScope.doHandle(target, baseRequest, request, response);
      } else {
         this.doHandle(target, baseRequest, request, response);
      }

   }

   public abstract void doHandle(String var1, Request var2, HttpServletRequest var3, HttpServletResponse var4) throws IOException, ServletException;

   public final void nextHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (this._nextScope != null && this._nextScope == this._handler) {
         this._nextScope.doHandle(target, baseRequest, request, response);
      } else if (this._handler != null) {
         super.handle(target, baseRequest, request, response);
      }

   }
}
