package scala.sys.process;

import java.io.File;
import scala.Function0;
import scala.Function1;

public final class ProcessLogger$ {
   public static final ProcessLogger$ MODULE$ = new ProcessLogger$();

   public FileProcessLogger apply(final File file) {
      return new FileProcessLogger(file);
   }

   public ProcessLogger apply(final Function1 fn) {
      return new ProcessLogger(fn, fn) {
         private final Function1 fout$1;
         private final Function1 ferr$1;

         public void out(final Function0 s) {
            this.fout$1.apply(s.apply());
         }

         public void err(final Function0 s) {
            this.ferr$1.apply(s.apply());
         }

         public Object buffer(final Function0 f) {
            return f.apply();
         }

         public {
            this.fout$1 = fout$1;
            this.ferr$1 = ferr$1;
         }
      };
   }

   public ProcessLogger apply(final Function1 fout, final Function1 ferr) {
      return new ProcessLogger(fout, ferr) {
         private final Function1 fout$1;
         private final Function1 ferr$1;

         public void out(final Function0 s) {
            this.fout$1.apply(s.apply());
         }

         public void err(final Function0 s) {
            this.ferr$1.apply(s.apply());
         }

         public Object buffer(final Function0 f) {
            return f.apply();
         }

         public {
            this.fout$1 = fout$1;
            this.ferr$1 = ferr$1;
         }
      };
   }

   private ProcessLogger$() {
   }
}
