package javolution.context;

import javolution.lang.Configurable;

public abstract class SecurityContext extends Context {
   private static volatile SecurityContext _Default = new Default();
   public static final Configurable DEFAULT = new Configurable(Default.class) {
      protected void notifyChange(Object oldValue, Object newValue) {
         SecurityContext._Default = (SecurityContext)ObjectFactory.getInstance((Class)newValue).object();
      }
   };

   protected SecurityContext() {
   }

   public static SecurityContext getCurrentSecurityContext() {
      for(Context ctx = Context.getCurrentContext(); ctx != null; ctx = ctx.getOuter()) {
         if (ctx instanceof SecurityContext) {
            return (SecurityContext)ctx;
         }
      }

      return _Default;
   }

   public static SecurityContext getDefault() {
      return _Default;
   }

   protected final void enterAction() {
      SecurityContext previousPolicy = _Default;

      for(Context ctx = this.getOuter(); ctx != null; ctx = ctx.getOuter()) {
         if (ctx instanceof SecurityContext) {
            previousPolicy = (SecurityContext)ctx;
            break;
         }
      }

      if (!previousPolicy.isReplaceable()) {
         throw new SecurityException("Current Security Context not Replaceable");
      }
   }

   protected final void exitAction() {
   }

   public boolean isReplaceable() {
      return true;
   }

   public boolean isConfigurable(Configurable cfg) {
      return true;
   }

   static {
      ObjectFactory.setInstance(new ObjectFactory() {
         protected Object create() {
            return new Default();
         }
      }, Default.class);
   }

   private static class Default extends SecurityContext {
      private Default() {
      }
   }
}
