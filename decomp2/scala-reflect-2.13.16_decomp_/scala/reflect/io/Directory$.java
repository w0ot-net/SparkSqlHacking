package scala.reflect.io;

import scala.Option;
import scala.Some;
import scala.util.PropertiesTrait;
import scala.util.Properties.;

public final class Directory$ {
   public static final Directory$ MODULE$ = new Directory$();

   private Some normalizePath(final String s) {
      return new Some(Path$.MODULE$.apply(s).normalize().toDirectory());
   }

   public Option Current() {
      String var10000 = PropertiesTrait.userDir$(.MODULE$);
      String var1 = "";
      if (var10000 != null) {
         if (var10000.equals(var1)) {
            return scala.None..MODULE$;
         }
      }

      return this.normalizePath(PropertiesTrait.userDir$(.MODULE$));
   }

   public Directory apply(final Path path) {
      return path.toDirectory();
   }

   public Directory makeTemp(final String prefix, final String suffix, final java.io.File dir) {
      File path = File$.MODULE$.makeTemp(prefix, suffix, dir);
      path.delete();
      return path.createDirectory(path.createDirectory$default$1(), path.createDirectory$default$2());
   }

   public String makeTemp$default$1() {
      return Path$.MODULE$.randomPrefix();
   }

   public String makeTemp$default$2() {
      return null;
   }

   public java.io.File makeTemp$default$3() {
      return null;
   }

   private Directory$() {
   }
}
