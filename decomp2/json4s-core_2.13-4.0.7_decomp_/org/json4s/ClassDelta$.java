package org.json4s;

import scala.collection.ArrayOps.;

public final class ClassDelta$ {
   public static final ClassDelta$ MODULE$ = new ClassDelta$();

   public int delta(final Class class1, final Class class2) {
      int var10000;
      label41: {
         if (class1 == null) {
            if (class2 == null) {
               break label41;
            }
         } else if (class1.equals(class2)) {
            break label41;
         }

         if (class1 == null) {
            var10000 = 1;
            return var10000;
         } else if (class2 == null) {
            var10000 = -1;
            return var10000;
         } else if (.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])class1.getInterfaces()), class2)) {
            var10000 = 0;
            return var10000;
         } else if (.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])class2.getInterfaces()), class1)) {
            var10000 = 0;
            return var10000;
         } else {
            if (class1.isAssignableFrom(class2)) {
               var10000 = 1 + this.delta(class1, class2.getSuperclass());
            } else {
               if (!class2.isAssignableFrom(class1)) {
                  throw scala.sys.package..MODULE$.error("Don't call delta unless one class is assignable from the other");
               }

               var10000 = 1 + this.delta(class1.getSuperclass(), class2);
            }

            return var10000;
         }
      }

      var10000 = 0;
      return var10000;
   }

   private ClassDelta$() {
   }
}
