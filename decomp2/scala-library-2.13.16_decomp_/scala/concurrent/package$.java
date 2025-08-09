package scala.concurrent;

import scala.Function0;

public final class package$ {
   public static final package$ MODULE$ = new package$();

   public final Object blocking(final Function0 body) throws Exception {
      return BlockContext$.MODULE$.current().blockOn(body, AwaitPermission$.MODULE$);
   }

   private package$() {
   }
}
