package scala.runtime;

public final class ClassValueCompat$ {
   public static final ClassValueCompat$ MODULE$ = new ClassValueCompat$();
   private static final boolean scala$runtime$ClassValueCompat$$classValueAvailable = liftedTree1$1();

   public boolean scala$runtime$ClassValueCompat$$classValueAvailable() {
      return scala$runtime$ClassValueCompat$$classValueAvailable;
   }

   // $FF: synthetic method
   private static final boolean liftedTree1$1() {
      try {
         Class.forName("java.lang.ClassValue", false, Object.class.getClassLoader());
         return true;
      } catch (ClassNotFoundException var0) {
         return false;
      }
   }

   private ClassValueCompat$() {
   }
}
