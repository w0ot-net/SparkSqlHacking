package breeze.macros;

import scala.Function2;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mq!\u0002\u0006\f\u0011\u0003\u0001b!\u0002\n\f\u0011\u0003\u0019\u0002\"\u0002\u000e\u0002\t\u0003Y\u0002\"\u0002\u000f\u0002\t\u0003i\u0002\"\u0002#\u0002\t\u0003)\u0005\"B,\u0002\t\u0003A\u0006\"B0\u0002\t\u0003\u0001\u0007\"B5\u0002\t\u0003Q\u0007\"B9\u0002\t\u0003\u0011\b\"B>\u0002\t\u0003a\u0018AC!tg\u0016\u0014H/S7qY*\u0011A\"D\u0001\u0007[\u0006\u001c'o\\:\u000b\u00039\taA\u0019:fKj,7\u0001\u0001\t\u0003#\u0005i\u0011a\u0003\u0002\u000b\u0003N\u001cXM\u001d;J[Bd7CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001E\u0001\u000bCN\u001cXM\u001d;J[BdGC\u0001\u0010#)\tyb\bE\u0002!omr!!\t\u0012\r\u0001!)1e\u0001a\u0001I\u0005\t1\r\u0005\u0002&i9\u0011a%\r\b\u0003O=r!\u0001K\u0017\u000f\u0005%bS\"\u0001\u0016\u000b\u0005-z\u0011A\u0002\u001fs_>$h(C\u0001\u0018\u0013\tqc#A\u0004sK\u001adWm\u0019;\n\u00051\u0001$B\u0001\u0018\u0017\u0013\t\u00114'A\u0004qC\u000e\\\u0017mZ3\u000b\u00051\u0001\u0014BA\u001b7\u0005\u001d\u0019uN\u001c;fqRT!AM\u001a\n\u0005aJ$\u0001B#yaJL!AO\u001a\u0003\u000f\u0005c\u0017.Y:fgB\u0011Q\u0003P\u0005\u0003{Y\u0011A!\u00168ji\")qh\u0001a\u0001\u0001\u0006I1m\u001c8eSRLwN\u001c\t\u0004A]\n\u0005CA\u000bC\u0013\t\u0019eCA\u0004C_>dW-\u00198\u0002\u001b\u0005\u001c8/\u001a:u\u001bN<\u0017*\u001c9m)\t1\u0015\nF\u0002H\u00152\u00032\u0001S\u001c<\u001d\t\t\u0013\nC\u0003$\t\u0001\u0007A\u0005C\u0003@\t\u0001\u00071\nE\u0002Io\u0005CQ!\u0014\u0003A\u00029\u000bq!\\3tg\u0006<W\rE\u0002Io=\u0003\"\u0001\u0015+\u000f\u0005E\u0013\u0006CA\u0015\u0017\u0013\t\u0019f#\u0001\u0004Qe\u0016$WMZ\u0005\u0003+Z\u0013aa\u0015;sS:<'BA*\u0017\u0003-\u0011X-];je\u0016LU\u000e\u001d7\u0015\u0005ecFC\u0001.^!\rYvg\u000f\b\u0003CqCQaI\u0003A\u0002\u0011BQaP\u0003A\u0002y\u00032aW\u001cB\u00039\u0011X-];je\u0016l5oZ%na2$\"!\u00193\u0015\u0007\t,w\rE\u0002domr!!\t3\t\u000b\r2\u0001\u0019\u0001\u0013\t\u000b}2\u0001\u0019\u00014\u0011\u0007\r<\u0014\tC\u0003N\r\u0001\u0007\u0001\u000eE\u0002do=\u000b!\"Y:tk6,\u0017*\u001c9m)\tYg\u000e\u0006\u0002m_B\u0019QnN\u001e\u000f\u0005\u0005r\u0007\"B\u0012\b\u0001\u0004!\u0003\"B \b\u0001\u0004\u0001\bcA78\u0003\u0006i\u0011m]:v[\u0016l5oZ%na2$\"a\u001d<\u0015\u0007Q<\u0018\u0010E\u0002vomr!!\t<\t\u000b\rB\u0001\u0019\u0001\u0013\t\u000b}B\u0001\u0019\u0001=\u0011\u0007U<\u0014\tC\u0003N\u0011\u0001\u0007!\u0010E\u0002vo=\u000ba\"Y:tKJ$H*[6f\u00136\u0004H\u000eF\u0002~\u0003\u0003!RA`A\u0002\u0003\u000f\u00012a`\u001c<\u001d\r\t\u0013\u0011\u0001\u0005\u0006G%\u0001\r\u0001\n\u0005\u0007\u007f%\u0001\r!!\u0002\u0011\u0007}<\u0014\tC\u0004\u0002\n%\u0001\r!a\u0003\u0002\u0017\r\fG\u000e\u001c\"vS2$WM\u001d\t\t+\u00055\u0011QAA\t}&\u0019\u0011q\u0002\f\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004cA@8\u001f\u0002"
)
public final class AssertImpl {
   public static Exprs.Expr assertLikeImpl(final Context c, final Exprs.Expr condition, final Function2 callBuilder) {
      return AssertImpl$.MODULE$.assertLikeImpl(c, condition, callBuilder);
   }

   public static Exprs.Expr assumeMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return AssertImpl$.MODULE$.assumeMsgImpl(c, condition, message);
   }

   public static Exprs.Expr assumeImpl(final Context c, final Exprs.Expr condition) {
      return AssertImpl$.MODULE$.assumeImpl(c, condition);
   }

   public static Exprs.Expr requireMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return AssertImpl$.MODULE$.requireMsgImpl(c, condition, message);
   }

   public static Exprs.Expr requireImpl(final Context c, final Exprs.Expr condition) {
      return AssertImpl$.MODULE$.requireImpl(c, condition);
   }

   public static Exprs.Expr assertMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return AssertImpl$.MODULE$.assertMsgImpl(c, condition, message);
   }

   public static Exprs.Expr assertImpl(final Context c, final Exprs.Expr condition) {
      return AssertImpl$.MODULE$.assertImpl(c, condition);
   }
}
