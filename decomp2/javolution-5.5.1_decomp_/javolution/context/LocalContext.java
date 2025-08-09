package javolution.context;

import javolution.util.FastMap;

public class LocalContext extends Context {
   final FastMap _references = new FastMap();

   public static void enter() {
      Context.enter(LocalContext.class);
   }

   public static void exit() {
      Context.exit(LocalContext.class);
   }

   protected void enterAction() {
   }

   protected void exitAction() {
      this._references.clear();
   }

   public static class Reference implements javolution.lang.Reference {
      private Object _defaultValue;
      private boolean _hasBeenLocallyOverriden;

      public Reference() {
         this((Object)null);
      }

      public Reference(Object defaultValue) {
         this._defaultValue = defaultValue;
      }

      public final Object get() {
         return this._hasBeenLocallyOverriden ? this.retrieveValue() : this._defaultValue;
      }

      private Object retrieveValue() {
         for(Context ctx = Context.getCurrentContext(); ctx != null; ctx = ctx.getOuter()) {
            if (ctx instanceof LocalContext) {
               LocalContext localContext = (LocalContext)ctx;
               Object value = localContext._references.get(this);
               if (value != null) {
                  return value;
               }
            }
         }

         return this._defaultValue;
      }

      public void set(Object value) {
         LocalContext ctx = getLocalContext();
         if (ctx != null) {
            FastMap references = ctx._references;
            references.put(this, value);
            this._hasBeenLocallyOverriden = true;
         } else {
            this._defaultValue = value;
         }
      }

      public Object getDefault() {
         return this._defaultValue;
      }

      public Object getLocal() {
         LocalContext ctx = getLocalContext();
         return ctx != null ? ctx._references.get(this) : this._defaultValue;
      }

      public void setDefault(Object defaultValue) {
         this._defaultValue = defaultValue;
      }

      public String toString() {
         return String.valueOf(this.get());
      }

      private static LocalContext getLocalContext() {
         for(Context ctx = Context.getCurrentContext(); ctx != null; ctx = ctx.getOuter()) {
            if (ctx instanceof LocalContext) {
               return (LocalContext)ctx;
            }
         }

         return null;
      }
   }
}
