package scala.reflect.runtime;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e2Q\u0001B\u0003\u0001\u000f-A\u0001\"\u0005\u0001\u0003\u0006\u0004%\ta\u0005\u0005\t]\u0001\u0011\t\u0011)A\u0005)!)q\u0006\u0001C\u0001a\ta\u0001*Y:KCZ\f7\t\\1tg*\u0011aaB\u0001\beVtG/[7f\u0015\tA\u0011\"A\u0004sK\u001adWm\u0019;\u000b\u0003)\tQa]2bY\u0006,\"\u0001D\r\u0014\u0005\u0001i\u0001C\u0001\b\u0010\u001b\u0005I\u0011B\u0001\t\n\u0005\u0019\te.\u001f*fM\u0006Aq-\u001a;DY\u0006T(p\u0001\u0001\u0016\u0003Q\u0001BAD\u000b\u0018E%\u0011a#\u0003\u0002\n\rVt7\r^5p]F\u0002\"\u0001G\r\r\u0001\u0011)!\u0004\u0001b\u00017\t\t!*\u0005\u0002\u001d?A\u0011a\"H\u0005\u0003=%\u0011qAT8uQ&tw\r\u0005\u0002\u000fA%\u0011\u0011%\u0003\u0002\u0004\u0003:L\bGA\u0012-!\r!\u0013fK\u0007\u0002K)\u0011aeJ\u0001\u0005Y\u0006twMC\u0001)\u0003\u0011Q\u0017M^1\n\u0005)*#!B\"mCN\u001c\bC\u0001\r-\t%i#!!A\u0001\u0002\u000b\u00051D\u0001\u0003`IQB\u0014!C4fi\u000ec\u0017M\u001f>!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011g\r\t\u0004e\u00019R\"A\u0003\t\u000bE\u0019\u0001\u0019\u0001\u001b\u0011\t9)r#\u000e\u0019\u0003ma\u00022\u0001J\u00158!\tA\u0002\bB\u0005.g\u0005\u0005\t\u0011!B\u00017\u0001"
)
public class HasJavaClass {
   private final Function1 getClazz;

   public Function1 getClazz() {
      return this.getClazz;
   }

   public HasJavaClass(final Function1 getClazz) {
      this.getClazz = getClazz;
   }
}
