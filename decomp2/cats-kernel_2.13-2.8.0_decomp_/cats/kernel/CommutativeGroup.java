package cats.kernel;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154qAB\u0004\u0011\u0002G\u0005AbB\u0003A\u000f!\u0005\u0011IB\u0003\u0007\u000f!\u0005!\tC\u0003P\u0005\u0011\u0005\u0001\u000bC\u0003R\u0005\u0011\u0015!\u000bC\u0004^\u0005\u0005\u0005I\u0011\u00020\u0003!\r{W.\\;uCRLg/Z$s_V\u0004(B\u0001\u0005\n\u0003\u0019YWM\u001d8fY*\t!\"\u0001\u0003dCR\u001c8\u0001A\u000b\u0003\u001bi\u0019B\u0001\u0001\b\u0015{A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t\u0019\u0011I\\=\u0011\u0007U1\u0002$D\u0001\b\u0013\t9rAA\u0003He>,\b\u000f\u0005\u0002\u001a51\u0001A!C\u000e\u0001A\u0003\u0005\tQ1\u0001\u001d\u0005\u0005\t\u0015CA\u000f\u000f!\tya$\u0003\u0002 !\t9aj\u001c;iS:<\u0007F\u0002\u000e\"I9\u001a\u0004\b\u0005\u0002\u0010E%\u00111\u0005\u0005\u0002\fgB,7-[1mSj,G-M\u0003$K\u0019BsE\u0004\u0002\u0010M%\u0011q\u0005E\u0001\u0004\u0013:$\u0018\u0007\u0002\u0013*[Eq!AK\u0017\u000e\u0003-R!\u0001L\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012'B\u00120aI\ndBA\b1\u0013\t\t\u0004#\u0001\u0003M_:<\u0017\u0007\u0002\u0013*[E\tTa\t\u001b6oYr!aD\u001b\n\u0005Y\u0002\u0012!\u0002$m_\u0006$\u0018\u0007\u0002\u0013*[E\tTaI\u001d;ymr!a\u0004\u001e\n\u0005m\u0002\u0012A\u0002#pk\ndW-\r\u0003%S5\n\u0002cA\u000b?1%\u0011qh\u0002\u0002\u0012\u0007>lW.\u001e;bi&4X-T8o_&$\u0017\u0001E\"p[6,H/\u0019;jm\u0016<%o\\;q!\t)\"aE\u0002\u0003\u0007\u001e\u00032!\u0006#G\u0013\t)uA\u0001\bHe>,\bOR;oGRLwN\\:\u0011\u0005U\u0001\u0001C\u0001%N\u001b\u0005I%B\u0001&L\u0003\tIwNC\u0001M\u0003\u0011Q\u0017M^1\n\u00059K%\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001B\u0003\u0015\t\u0007\u000f\u001d7z+\t\u0019f\u000b\u0006\u0002U/B\u0019Q\u0003A+\u0011\u0005e1F!B\u000e\u0005\u0005\u0004a\u0002\"\u0002-\u0005\u0001\b!\u0016AA3wQ\t!!\f\u0005\u0002\u00107&\u0011A\f\u0005\u0002\u0007S:d\u0017N\\3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0003}\u0003\"\u0001Y2\u000e\u0003\u0005T!AY&\u0002\t1\fgnZ\u0005\u0003I\u0006\u0014aa\u00142kK\u000e$\b"
)
public interface CommutativeGroup extends Group, CommutativeMonoid {
   static CommutativeGroup apply(final CommutativeGroup ev) {
      return CommutativeGroup$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return CommutativeGroup$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return CommutativeGroup$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return CommutativeGroup$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return CommutativeGroup$.MODULE$.maybeCombine(ox, y, ev);
   }
}
