package spire.syntax.std;

import scala.reflect.ScalaSignature;
import spire.math.ConvertableTo;

@ScalaSignature(
   bytes = "\u0006\u0005m2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011\u0004C\u0003$\u0001\u0011\rAEA\u0005J]R\u001c\u0016P\u001c;bq*\u0011aaB\u0001\u0004gR$'B\u0001\u0005\n\u0003\u0019\u0019\u0018P\u001c;bq*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0014\u0005\u0001i\u0001C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002+A\u0011aBF\u0005\u0003/=\u0011A!\u00168ji\u0006iA.\u001b;fe\u0006d\u0017J\u001c;PaN$\"A\u0007\u0010\u0011\u0005maR\"A\u0003\n\u0005u)!!\u0004'ji\u0016\u0014\u0018\r\\%oi>\u00038\u000fC\u0003 \u0005\u0001\u0007\u0001%A\u0001o!\tq\u0011%\u0003\u0002#\u001f\t\u0019\u0011J\u001c;\u0002\r%tG\u000fV8B+\t)\u0013\u0006\u0006\u0002'uQ\u0011qE\r\t\u0003Q%b\u0001\u0001B\u0003+\u0007\t\u00071FA\u0001B#\tas\u0006\u0005\u0002\u000f[%\u0011af\u0004\u0002\b\u001d>$\b.\u001b8h!\tq\u0001'\u0003\u00022\u001f\t\u0019\u0011I\\=\t\u000bM\u001a\u00019\u0001\u001b\u0002\u0003\r\u00042!\u000e\u001d(\u001b\u00051$BA\u001c\n\u0003\u0011i\u0017\r\u001e5\n\u0005e2$!D\"p]Z,'\u000f^1cY\u0016$v\u000eC\u0003 \u0007\u0001\u0007\u0001\u0005"
)
public interface IntSyntax {
   // $FF: synthetic method
   static int literalIntOps$(final IntSyntax $this, final int n) {
      return $this.literalIntOps(n);
   }

   default int literalIntOps(final int n) {
      return n;
   }

   // $FF: synthetic method
   static Object intToA$(final IntSyntax $this, final int n, final ConvertableTo c) {
      return $this.intToA(n, c);
   }

   default Object intToA(final int n, final ConvertableTo c) {
      return c.fromInt(n);
   }

   static void $init$(final IntSyntax $this) {
   }
}
