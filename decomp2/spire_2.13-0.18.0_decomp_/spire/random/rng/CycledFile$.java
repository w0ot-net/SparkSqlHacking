package spire.random.rng;

import java.io.File;

public final class CycledFile$ {
   public static final CycledFile$ MODULE$ = new CycledFile$();

   public CycledFile apply(final String path) {
      return new CycledFile(new File(path));
   }

   private CycledFile$() {
   }
}
