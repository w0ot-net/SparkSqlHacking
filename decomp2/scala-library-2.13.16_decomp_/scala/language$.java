package scala;

public final class language$ {
   public static final language$ MODULE$ = new language$();
   private static languageFeature.dynamics dynamics;
   private static languageFeature.postfixOps postfixOps;
   private static languageFeature.reflectiveCalls reflectiveCalls;
   private static languageFeature.implicitConversions implicitConversions;
   /** @deprecated */
   private static languageFeature.higherKinds higherKinds;
   private static languageFeature.existentials existentials;
   private static volatile byte bitmap$0;

   private languageFeature.dynamics dynamics$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 1) == 0) {
            dynamics = languageFeature.dynamics$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 1);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return dynamics;
   }

   public languageFeature.dynamics dynamics() {
      return (byte)(bitmap$0 & 1) == 0 ? this.dynamics$lzycompute() : dynamics;
   }

   private languageFeature.postfixOps postfixOps$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 2) == 0) {
            postfixOps = languageFeature.postfixOps$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 2);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return postfixOps;
   }

   public languageFeature.postfixOps postfixOps() {
      return (byte)(bitmap$0 & 2) == 0 ? this.postfixOps$lzycompute() : postfixOps;
   }

   private languageFeature.reflectiveCalls reflectiveCalls$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 4) == 0) {
            reflectiveCalls = languageFeature.reflectiveCalls$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 4);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return reflectiveCalls;
   }

   public languageFeature.reflectiveCalls reflectiveCalls() {
      return (byte)(bitmap$0 & 4) == 0 ? this.reflectiveCalls$lzycompute() : reflectiveCalls;
   }

   private languageFeature.implicitConversions implicitConversions$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 8) == 0) {
            implicitConversions = languageFeature.implicitConversions$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 8);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return implicitConversions;
   }

   public languageFeature.implicitConversions implicitConversions() {
      return (byte)(bitmap$0 & 8) == 0 ? this.implicitConversions$lzycompute() : implicitConversions;
   }

   private languageFeature.higherKinds higherKinds$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 16) == 0) {
            higherKinds = languageFeature.higherKinds$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 16);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return higherKinds;
   }

   /** @deprecated */
   public languageFeature.higherKinds higherKinds() {
      return (byte)(bitmap$0 & 16) == 0 ? this.higherKinds$lzycompute() : higherKinds;
   }

   private languageFeature.existentials existentials$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(bitmap$0 & 32) == 0) {
            existentials = languageFeature.existentials$.MODULE$;
            bitmap$0 = (byte)(bitmap$0 | 32);
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return existentials;
   }

   public languageFeature.existentials existentials() {
      return (byte)(bitmap$0 & 32) == 0 ? this.existentials$lzycompute() : existentials;
   }

   private language$() {
   }
}
