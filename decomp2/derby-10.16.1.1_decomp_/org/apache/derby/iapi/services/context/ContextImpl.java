package org.apache.derby.iapi.services.context;

public abstract class ContextImpl implements Context {
   private final String myIdName;
   private final ContextManager myContextManager;

   protected ContextImpl(ContextManager var1, String var2) {
      this.myIdName = var2;
      this.myContextManager = var1;
      var1.pushContext(this);
   }

   public final ContextManager getContextManager() {
      return this.myContextManager;
   }

   public final String getIdName() {
      return this.myIdName;
   }

   public final void pushMe() {
      this.getContextManager().pushContext(this);
   }

   public final void popMe() {
      this.getContextManager().popContext(this);
   }

   public boolean isLastHandler(int var1) {
      return false;
   }

   public StringBuffer appendErrorInfo() {
      return null;
   }
}
