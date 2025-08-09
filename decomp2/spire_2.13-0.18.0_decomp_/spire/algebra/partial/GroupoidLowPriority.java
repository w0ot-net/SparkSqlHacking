package spire.algebra.partial;

import cats.kernel.Eq;
import cats.kernel.Group;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001DA\nHe>,\bo\\5e\u0019><\bK]5pe&$\u0018P\u0003\u0002\u0006\r\u00059\u0001/\u0019:uS\u0006d'BA\u0004\t\u0003\u001d\tGnZ3ce\u0006T\u0011!C\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001A\u0002\u0005\u0002\u000e!5\taBC\u0001\u0010\u0003\u0015\u00198-\u00197b\u0013\t\tbB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Q\u0001\"!D\u000b\n\u0005Yq!\u0001B+oSR\f\u0011B\u001a:p[\u001e\u0013x.\u001e9\u0016\u0005e\u0001CC\u0001\u000e*!\rYBDH\u0007\u0002\t%\u0011Q\u0004\u0002\u0002\t\u000fJ|W\u000f]8jIB\u0011q\u0004\t\u0007\u0001\t\u0015\t#A1\u0001#\u0005\u0005\t\u0015CA\u0012'!\tiA%\u0003\u0002&\u001d\t9aj\u001c;iS:<\u0007CA\u0007(\u0013\tAcBA\u0002B]fDQA\u000b\u0002A\u0004-\nQa\u001a:pkB\u00042\u0001\f\u0019\u001f\u001d\tic&D\u0001\u0007\u0013\tyc!A\u0004qC\u000e\\\u0017mZ3\n\u0005E\u0012$!B$s_V\u0004(BA\u0018\u0007\u0001"
)
public interface GroupoidLowPriority {
   // $FF: synthetic method
   static Groupoid fromGroup$(final GroupoidLowPriority $this, final Group group) {
      return $this.fromGroup(group);
   }

   default Groupoid fromGroup(final Group group) {
      return new Groupoid(group) {
         private final Group group$1;

         public boolean isId(final Object a, final Eq ev) {
            return Groupoid.isId$(this, a, ev);
         }

         public Object leftId(final Object a) {
            return Groupoid.leftId$(this, a);
         }

         public Object rightId(final Object a) {
            return Groupoid.rightId$(this, a);
         }

         public boolean opIsDefined(final Object x, final Object y) {
            return true;
         }

         public boolean opInverseIsDefined(final Object x, final Object y) {
            return true;
         }

         public Object inverse(final Object a) {
            return this.group$1.inverse(a);
         }

         public Object partialOp(final Object x, final Object y) {
            return .MODULE$.apply(this.group$1.combine(x, y));
         }

         public Object partialOpInverse(final Object x, final Object y) {
            return .MODULE$.apply(this.group$1.remove(x, y));
         }

         public {
            this.group$1 = group$1;
            Semigroupoid.$init$(this);
            Groupoid.$init$(this);
         }
      };
   }

   static void $init$(final GroupoidLowPriority $this) {
   }
}
