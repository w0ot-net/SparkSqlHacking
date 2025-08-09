package spire.syntax;

import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u3qa\u0005\u000b\u0011\u0002\u0007\u0005\u0011\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003&\u0001\u0011\raeB\u00031\u0001!\u0005\u0011GB\u00034\u0001!\u0005A\u0007C\u00036\t\u0011\u0005a\u0007C\u00031\t\u0011\rqgB\u0003=\u0001!\u0005QHB\u0003?\u0001!\u0005q\bC\u00036\u0011\u0011\u0005\u0001\tC\u0003B\u0011\u0011\r!iB\u0003H\u0001!\u0005\u0001JB\u0003J\u0001!\u0005!\nC\u00036\u0019\u0011\u00051\nC\u0003M\u0019\u0011\rQjB\u0003S\u0001!\u00051KB\u0003U\u0001!\u0005Q\u000bC\u00036!\u0011\u0005a\u000bC\u0003X!\u0011\r\u0001L\u0001\bMSR,'/\u00197t'ftG/\u0019=\u000b\u0005U1\u0012AB:z]R\f\u0007PC\u0001\u0018\u0003\u0015\u0019\b/\u001b:f\u0007\u0001\u0019\"\u0001\u0001\u000e\u0011\u0005mqR\"\u0001\u000f\u000b\u0003u\tQa]2bY\u0006L!a\b\u000f\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0005\u0005\u0002\u001cG%\u0011A\u0005\b\u0002\u0005+:LG/\u0001\u0005mSR,'/\u00197t)\t93\u0006\u0005\u0002)S5\tA#\u0003\u0002+)\tAA*\u001b;fe\u0006d7\u000fC\u0003-\u0005\u0001\u0007Q&A\u0001t!\tYb&\u0003\u000209\ti1\u000b\u001e:j]\u001e\u001cuN\u001c;fqR\fQA]1eSb\u0004\"A\r\u0003\u000e\u0003\u0001\u0011QA]1eSb\u001c\"\u0001\u0002\u000e\u0002\rqJg.\u001b;?)\u0005\tDC\u0001\u001d<!\tA\u0013(\u0003\u0002;)\t)!+\u00193jq\")AF\u0002a\u0001[\u0005\u00111/\u001b\t\u0003e!\u0011!a]5\u0014\u0005!QB#A\u001f\u0002\u0015MLG*\u001b;fe\u0006d7\u000f\u0006\u0002D\rB\u0011\u0001\u0006R\u0005\u0003\u000bR\u0011!bU5MSR,'/\u00197t\u0011\u0015a#\u00021\u0001.\u0003\t)8\u000f\u0005\u00023\u0019\t\u0011Qo]\n\u0003\u0019i!\u0012\u0001S\u0001\u000bkNd\u0015\u000e^3sC2\u001cHC\u0001(R!\tAs*\u0003\u0002Q)\tQQk\u001d'ji\u0016\u0014\u0018\r\\:\t\u000b1r\u0001\u0019A\u0017\u0002\u0005\u0015,\bC\u0001\u001a\u0011\u0005\t)Wo\u0005\u0002\u00115Q\t1+\u0001\u0006fk2KG/\u001a:bYN$\"!\u0017/\u0011\u0005!R\u0016BA.\u0015\u0005))U\u000fT5uKJ\fGn\u001d\u0005\u0006YI\u0001\r!\f"
)
public interface LiteralsSyntax {
   radix$ radix();

   si$ si();

   us$ us();

   eu$ eu();

   // $FF: synthetic method
   static StringContext literals$(final LiteralsSyntax $this, final StringContext s) {
      return $this.literals(s);
   }

   default StringContext literals(final StringContext s) {
      return s;
   }

   static void $init$(final LiteralsSyntax $this) {
   }

   public class radix$ {
      public Radix radix(final StringContext s) {
         return new Radix(s);
      }
   }

   public class si$ {
      public SiLiterals siLiterals(final StringContext s) {
         return new SiLiterals(s);
      }
   }

   public class us$ {
      public UsLiterals usLiterals(final StringContext s) {
         return new UsLiterals(s);
      }
   }

   public class eu$ {
      public EuLiterals euLiterals(final StringContext s) {
         return new EuLiterals(s);
      }
   }
}
