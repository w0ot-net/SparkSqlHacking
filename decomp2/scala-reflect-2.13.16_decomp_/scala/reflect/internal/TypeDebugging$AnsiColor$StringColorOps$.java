package scala.reflect.internal;

public class TypeDebugging$AnsiColor$StringColorOps$ {
   public static final TypeDebugging$AnsiColor$StringColorOps$ MODULE$ = new TypeDebugging$AnsiColor$StringColorOps$();

   public final String red$extension(final String $this) {
      return TypeDebugging.AnsiColor.inLightRed$(TypeDebugging.AnsiColor$.MODULE$, $this);
   }

   public final String green$extension(final String $this) {
      return TypeDebugging.AnsiColor.inLightGreen$(TypeDebugging.AnsiColor$.MODULE$, $this);
   }

   public final String yellow$extension(final String $this) {
      return TypeDebugging.AnsiColor.inLightYellow$(TypeDebugging.AnsiColor$.MODULE$, $this);
   }

   public final String blue$extension(final String $this) {
      return TypeDebugging.AnsiColor.inLightBlue$(TypeDebugging.AnsiColor$.MODULE$, $this);
   }

   public final int hashCode$extension(final String $this) {
      return $this.hashCode();
   }

   public final boolean equals$extension(final String $this, final Object x$1) {
      if (x$1 instanceof TypeDebugging$AnsiColor$StringColorOps) {
         String var3 = x$1 == null ? null : ((TypeDebugging$AnsiColor$StringColorOps)x$1).scala$reflect$internal$TypeDebugging$AnsiColor$StringColorOps$$s();
         if ($this == null) {
            if (var3 == null) {
               return true;
            }
         } else if ($this.equals(var3)) {
            return true;
         }
      }

      return false;
   }
}
