package cats.kernel;

import scala.Function2;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005y4q!\u0003\u0006\u0011\u0002\u0007\u0005q\u0002C\u0003D\u0001\u0011\u0005A\tC\u0003I\u0001\u0011\u0005\u0013jB\u0003L\u0015!\u0005AJB\u0003\n\u0015!\u0005Q\nC\u0003[\t\u0011\u00051\fC\u0003]\t\u0011\u0015Q\fC\u0003i\t\u0011\u0005\u0011\u000eC\u0004w\t\u0005\u0005I\u0011B<\u0003#\r{W.\\;uCRLg/Z'p]>LGM\u0003\u0002\f\u0019\u000511.\u001a:oK2T\u0011!D\u0001\u0005G\u0006$8o\u0001\u0001\u0016\u0005Ai2\u0003\u0002\u0001\u0012/\u0001\u0003\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u00111!\u00118z!\rA\u0012dG\u0007\u0002\u0015%\u0011!D\u0003\u0002\u0007\u001b>tw.\u001b3\u0011\u0005qiB\u0002\u0001\u0003\n=\u0001\u0001\u000b\u0011!AC\u0002}\u0011\u0011!Q\t\u0003AE\u0001\"AE\u0011\n\u0005\t\u001a\"a\u0002(pi\"Lgn\u001a\u0015\u0007;\u0011:\u0013GN\u001e\u0011\u0005I)\u0013B\u0001\u0014\u0014\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\rB\u0013f\u000b\u0016\u000f\u0005II\u0013B\u0001\u0016\u0014\u0003\rIe\u000e^\u0019\u0005I1\u0002DC\u0004\u0002.a5\taF\u0003\u00020\u001d\u00051AH]8pizJ\u0011\u0001F\u0019\u0006GI\u001aT\u0007\u000e\b\u0003%MJ!\u0001N\n\u0002\t1{gnZ\u0019\u0005I1\u0002D#M\u0003$oaR\u0014H\u0004\u0002\u0013q%\u0011\u0011hE\u0001\u0006\r2|\u0017\r^\u0019\u0005I1\u0002D#M\u0003$yuzdH\u0004\u0002\u0013{%\u0011ahE\u0001\u0007\t>,(\r\\32\t\u0011b\u0003\u0007\u0006\t\u00041\u0005[\u0012B\u0001\"\u000b\u0005Q\u0019u.\\7vi\u0006$\u0018N^3TK6LwM]8va\u00061A%\u001b8ji\u0012\"\u0012!\u0012\t\u0003%\u0019K!aR\n\u0003\tUs\u0017\u000e^\u0001\be\u00164XM]:f+\u0005Q\u0005c\u0001\r\u00017\u0005\t2i\\7nkR\fG/\u001b<f\u001b>tw.\u001b3\u0011\u0005a!1c\u0001\u0003O%B\u0019\u0001dT)\n\u0005AS!aD'p]>LGMR;oGRLwN\\:\u0011\u0005a\u0001\u0001CA*Y\u001b\u0005!&BA+W\u0003\tIwNC\u0001X\u0003\u0011Q\u0017M^1\n\u0005e#&\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001M\u0003\u0015\t\u0007\u000f\u001d7z+\tq\u0016\r\u0006\u0002`EB\u0019\u0001\u0004\u00011\u0011\u0005q\tG!\u0002\u0010\u0007\u0005\u0004y\u0002\"B2\u0007\u0001\by\u0016AA3wQ\t1Q\r\u0005\u0002\u0013M&\u0011qm\u0005\u0002\u0007S:d\u0017N\\3\u0002\u0011%t7\u000f^1oG\u0016,\"A[7\u0015\u0007-t\u0007\u000fE\u0002\u0019\u00011\u0004\"\u0001H7\u0005\u000by9!\u0019A\u0010\t\u000b=<\u0001\u0019\u00017\u0002\u0015\u0015l\u0007\u000f^=WC2,X\rC\u0003r\u000f\u0001\u0007!/A\u0002d[\n\u0004RAE:mY2L!\u0001^\n\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004FA\u0004f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005A\bCA=}\u001b\u0005Q(BA>W\u0003\u0011a\u0017M\\4\n\u0005uT(AB(cU\u0016\u001cG\u000f"
)
public interface CommutativeMonoid extends Monoid, CommutativeSemigroup {
   static CommutativeMonoid instance(final Object emptyValue, final Function2 cmb) {
      return CommutativeMonoid$.MODULE$.instance(emptyValue, cmb);
   }

   static CommutativeMonoid apply(final CommutativeMonoid ev) {
      return CommutativeMonoid$.MODULE$.apply(ev);
   }

   static boolean isIdempotent(final Semigroup ev) {
      return CommutativeMonoid$.MODULE$.isIdempotent(ev);
   }

   static boolean isCommutative(final Semigroup ev) {
      return CommutativeMonoid$.MODULE$.isCommutative(ev);
   }

   static Object maybeCombine(final Object x, final Option oy, final Semigroup ev) {
      return CommutativeMonoid$.MODULE$.maybeCombine(x, oy, ev);
   }

   static Object maybeCombine(final Option ox, final Object y, final Semigroup ev) {
      return CommutativeMonoid$.MODULE$.maybeCombine(ox, y, ev);
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$(final CommutativeMonoid $this) {
      return $this.reverse();
   }

   default CommutativeMonoid reverse() {
      return this;
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcD$sp$(final CommutativeMonoid $this) {
      return $this.reverse$mcD$sp();
   }

   default CommutativeMonoid reverse$mcD$sp() {
      return this.reverse();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcF$sp$(final CommutativeMonoid $this) {
      return $this.reverse$mcF$sp();
   }

   default CommutativeMonoid reverse$mcF$sp() {
      return this.reverse();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcI$sp$(final CommutativeMonoid $this) {
      return $this.reverse$mcI$sp();
   }

   default CommutativeMonoid reverse$mcI$sp() {
      return this.reverse();
   }

   // $FF: synthetic method
   static CommutativeMonoid reverse$mcJ$sp$(final CommutativeMonoid $this) {
      return $this.reverse$mcJ$sp();
   }

   default CommutativeMonoid reverse$mcJ$sp() {
      return this.reverse();
   }

   static void $init$(final CommutativeMonoid $this) {
   }
}
