package breeze.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00059:Q!\u0003\u0006\t\u0002=1Q!\u0005\u0006\t\u0002IAQ!G\u0001\u0005\u0002iAqaG\u0001C\u0002\u0013\u0005A\u0004\u0003\u0004!\u0003\u0001\u0006I!\b\u0005\bC\u0005\u0011\r\u0011\"\u0001\u001d\u0011\u0019\u0011\u0013\u0001)A\u0005;!91%\u0001b\u0001\n\u0003!\u0003BB\u0017\u0002A\u0003%Q%\u0001\u0005UKJl\u0017N\\1m\u0015\tYA\"\u0001\u0003vi&d'\"A\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"\u0001E\u0001\u000e\u0003)\u0011\u0001\u0002V3s[&t\u0017\r\\\n\u0003\u0003M\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0010\u00035!XM]7j]\u0006dw+\u001b3uQV\tQ\u0004\u0005\u0002\u0015=%\u0011q$\u0006\u0002\u0004\u0013:$\u0018A\u0004;fe6Lg.\u00197XS\u0012$\b\u000eI\u0001\u000fi\u0016\u0014X.\u001b8bY\"+\u0017n\u001a5u\u0003=!XM]7j]\u0006d\u0007*Z5hQR\u0004\u0013a\u00028fo2Lg.Z\u000b\u0002KA\u0011aeK\u0007\u0002O)\u0011\u0001&K\u0001\u0005Y\u0006twMC\u0001+\u0003\u0011Q\u0017M^1\n\u00051:#AB*ue&tw-\u0001\u0005oK^d\u0017N\\3!\u0001"
)
public final class Terminal {
   public static String newline() {
      return Terminal$.MODULE$.newline();
   }

   public static int terminalHeight() {
      return Terminal$.MODULE$.terminalHeight();
   }

   public static int terminalWidth() {
      return Terminal$.MODULE$.terminalWidth();
   }
}
