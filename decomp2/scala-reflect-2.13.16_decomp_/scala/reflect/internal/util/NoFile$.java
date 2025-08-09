package scala.reflect.internal.util;

import scala.reflect.io.VirtualFile;

public final class NoFile$ extends VirtualFile {
   public static final NoFile$ MODULE$ = new NoFile$();

   private NoFile$() {
      super("<no file>", "<no file>");
   }
}
