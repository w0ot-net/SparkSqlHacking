package scala.reflect.runtime;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static scala.reflect.api.JavaUniverse universe;
   private static volatile boolean bitmap$0;

   private scala.reflect.api.JavaUniverse universe$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            universe = new JavaUniverse();
            bitmap$0 = true;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return universe;
   }

   public scala.reflect.api.JavaUniverse universe() {
      return !bitmap$0 ? this.universe$lzycompute() : universe;
   }

   private package$() {
   }
}
