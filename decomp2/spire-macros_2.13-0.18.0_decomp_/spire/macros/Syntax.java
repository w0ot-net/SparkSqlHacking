package spire.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dq!\u0002\u0004\b\u0011\u0003aa!\u0002\b\b\u0011\u0003y\u0001\"\u0002\f\u0002\t\u00039\u0002\"\u0002\r\u0002\t\u0003I\u0002\"B-\u0002\t\u0003Q\u0006\"\u0002:\u0002\t\u0003\u0019\u0018AB*z]R\f\u0007P\u0003\u0002\t\u0013\u00051Q.Y2s_NT\u0011AC\u0001\u0006gBL'/Z\u0002\u0001!\ti\u0011!D\u0001\b\u0005\u0019\u0019\u0016P\u001c;bqN\u0011\u0011\u0001\u0005\t\u0003#Qi\u0011A\u0005\u0006\u0002'\u0005)1oY1mC&\u0011QC\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a\u0011!C2g_Jl\u0015m\u0019:p+\tQ\"\t\u0006\u0002\u001cCQ\u0011AD\u0016\u000b\u0004;-\u0013FC\u0001\u0010<!\ry\u0012\u0007\u000f\b\u0003A\u0005b\u0001\u0001C\u0003#\u0007\u0001\u00071%A\u0001d!\t!cF\u0004\u0002&Y9\u0011ae\u000b\b\u0003O)j\u0011\u0001\u000b\u0006\u0003S-\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0006\n\u0005!I\u0011BA\u0017\b\u0003\u0019\u0019w.\u001c9bi&\u0011q\u0006\r\u0002\b\u0007>tG/\u001a=u\u0015\tis!\u0003\u00023g\t!Q\t\u001f9s\u0013\t!TGA\u0004BY&\f7/Z:\u000b\u0005!1$BA\u001c\u0013\u0003\u001d\u0011XM\u001a7fGR\u0004\"!E\u001d\n\u0005i\u0012\"\u0001B+oSRDQ\u0001P\u0002A\u0002u\nAAY8esB\u0019q$\r \u0011\tEy\u0014\tO\u0005\u0003\u0001J\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005\u0001\u0012E!B\"\u0004\u0005\u0004!%!A!\u0012\u0005\u0015C\u0005CA\tG\u0013\t9%CA\u0004O_RD\u0017N\\4\u0011\u0005EI\u0015B\u0001&\u0013\u0005\r\te.\u001f\u0005\u0006\u0019\u000e\u0001\r!T\u0001\u0005i\u0016\u001cH\u000fE\u0002 c9\u0003B!E B\u001fB\u0011\u0011\u0003U\u0005\u0003#J\u0011qAQ8pY\u0016\fg\u000eC\u0003T\u0007\u0001\u0007A+\u0001\u0003oKb$\bcA\u00102+B!\u0011cP!B\u0011\u001596\u00011\u0001Y\u0003\u0011Ig.\u001b;\u0011\u0007}\t\u0014)\u0001\bdM>\u0014(+\u00198hK6\u000b7M]8\u0015\u0005m{FC\u0001/g)\ti\u0006\rE\u0002_car!\u0001I0\t\u000b\t\"\u0001\u0019A\u0012\t\u000bq\"\u0001\u0019A1\u0011\u0007y\u000b$\r\u0005\u0003\u0012\u007f\rD\u0004CA\te\u0013\t)'CA\u0002J]RDQa\u001a\u0003A\u0002!\f\u0011A\u001d\t\u0004=FJ\u0007C\u00016p\u001d\tYWN\u0004\u0002(Y&\t1#\u0003\u0002o%\u00059\u0001/Y2lC\u001e,\u0017B\u00019r\u0005\u0015\u0011\u0016M\\4f\u0015\tq'#A\bdM>\u0014(+\u00198hKJj\u0015m\u0019:p)\t!\b\u0010\u0006\u0003v}\u0006\rAC\u0001<z!\r9\u0018\u0007\u000f\b\u0003AaDQAI\u0003A\u0002\rBQ\u0001P\u0003A\u0002i\u00042a^\u0019|!\u0015\tBpY29\u0013\ti(CA\u0005Gk:\u001cG/[8oe!1q0\u0002a\u0001\u0003\u0003\t!A]\u0019\u0011\u0007]\f\u0014\u000eC\u0004\u0002\u0006\u0015\u0001\r!!\u0001\u0002\u0005I\u0014\u0004"
)
public final class Syntax {
   public static Exprs.Expr cforRange2Macro(final Context c, final Exprs.Expr r1, final Exprs.Expr r2, final Exprs.Expr body) {
      return Syntax$.MODULE$.cforRange2Macro(c, r1, r2, body);
   }

   public static Exprs.Expr cforRangeMacro(final Context c, final Exprs.Expr r, final Exprs.Expr body) {
      return Syntax$.MODULE$.cforRangeMacro(c, r, body);
   }

   public static Exprs.Expr cforMacro(final Context c, final Exprs.Expr init, final Exprs.Expr test, final Exprs.Expr next, final Exprs.Expr body) {
      return Syntax$.MODULE$.cforMacro(c, init, test, next, body);
   }
}
