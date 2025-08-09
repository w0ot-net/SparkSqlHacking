package breeze.math;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A<Qa\u0002\u0005\t\n51Qa\u0004\u0005\t\nAAQaF\u0001\u0005\u0002aAQ!G\u0001\u0005\u0004iAQaS\u0001\u0005\u00041CQaV\u0001\u0005\u0004aCQAZ\u0001\u0005\u0004\u001d\f1D\u00127pCR$u.\u001e2mK>\u0003XM]1u_J\fE-\u00199u_J\u001c(BA\u0005\u000b\u0003\u0011i\u0017\r\u001e5\u000b\u0003-\taA\u0019:fKj,7\u0001\u0001\t\u0003\u001d\u0005i\u0011\u0001\u0003\u0002\u001c\r2|\u0017\r\u001e#pk\ndWm\u00149fe\u0006$xN]!eCB$xN]:\u0014\u0005\u0005\t\u0002C\u0001\n\u0016\u001b\u0005\u0019\"\"\u0001\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u0019\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u00059A.\u001b4u\u001fB\u0014T\u0003B\u000e/s\r#\"\u0001H#\u0011\ruIC\u0006O C\u001d\tqbE\u0004\u0002 I9\u0011\u0001eI\u0007\u0002C)\u0011!\u0005D\u0001\u0007yI|w\u000e\u001e \n\u0003-I!!\n\u0006\u0002\u000f\u001d,g.\u001a:jG&\u0011q\u0005K\u0001\u0006+\u001a+hn\u0019\u0006\u0003K)I!AK\u0016\u0003\rUKU\u000e\u001d73\u0015\t9\u0003\u0006\u0005\u0002.]1\u0001A!B\u0018\u0004\u0005\u0004\u0001$AA(q#\t\tD\u0007\u0005\u0002\u0013e%\u00111g\u0005\u0002\b\u001d>$\b.\u001b8h!\t)d'D\u0001)\u0013\t9\u0004FA\u0003V\rVt7\r\u0005\u0002.s\u0011)!h\u0001b\u0001w\t\ta+\u0005\u00022yA\u0011!#P\u0005\u0003}M\u00111!\u00118z!\t\u0011\u0002)\u0003\u0002B'\t1Ai\\;cY\u0016\u0004\"!L\"\u0005\u000b\u0011\u001b!\u0019A\u001e\u0003\u0003ICQAR\u0002A\u0002\u001d\u000b!a\u001c9\u0011\ruIC\u0006\u000f%C!\t\u0011\u0012*\u0003\u0002K'\t)a\t\\8bi\u0006qA.\u001b4u\u0013:\u0004F.Y2f\u001fB\u0014TcA'S)R\u0011a*\u0016\t\u0006;=\u000b6kP\u0005\u0003!.\u0012A\"\u00138QY\u0006\u001cW-S7qYJ\u0002\"!\f*\u0005\u000b=\"!\u0019\u0001\u0019\u0011\u00055\"F!\u0002\u001e\u0005\u0005\u0004Y\u0004\"\u0002$\u0005\u0001\u00041\u0006#B\u000fP#NC\u0015A\u00047jMRLe\u000e\u00157bG\u0016|\u0005oM\u000b\u00053z\u0003'\r\u0006\u0002[IB1QdW/`\u007f\u0005L!\u0001X\u0016\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0011\u00055rF!B\u0018\u0006\u0005\u0004\u0001\u0004CA\u0017a\t\u0015QTA1\u0001<!\ti#\rB\u0003d\u000b\t\u00071H\u0001\u0002Wg!)a)\u0002a\u0001KB1QdW/`\u0011\u0006\f\u0011\u0003\\5gi>\u0003(+\u001a;ve:4En\\1u+\rA7.\u001c\u000b\u0003S:\u0004b!H\u0015kY2|\u0004CA\u0017l\t\u0015ycA1\u00011!\tiS\u000eB\u0003;\r\t\u00071\bC\u0003G\r\u0001\u0007q\u000e\u0005\u0004\u001eS)dG\u000e\u0013"
)
public final class FloatDoubleOperatorAdaptors {
   public static UFunc.UImpl2 liftOpReturnFloat(final UFunc.UImpl2 op) {
      return FloatDoubleOperatorAdaptors$.MODULE$.liftOpReturnFloat(op);
   }

   public static UFunc.InPlaceImpl3 liftInPlaceOp3(final UFunc.InPlaceImpl3 op) {
      return FloatDoubleOperatorAdaptors$.MODULE$.liftInPlaceOp3(op);
   }

   public static UFunc.InPlaceImpl2 liftInPlaceOp2(final UFunc.InPlaceImpl2 op) {
      return FloatDoubleOperatorAdaptors$.MODULE$.liftInPlaceOp2(op);
   }

   public static UFunc.UImpl2 liftOp2(final UFunc.UImpl2 op) {
      return FloatDoubleOperatorAdaptors$.MODULE$.liftOp2(op);
   }
}
