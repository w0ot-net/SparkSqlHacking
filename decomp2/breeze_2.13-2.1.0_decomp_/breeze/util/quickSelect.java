package breeze.util;

import breeze.generic.UFunc;
import scala.;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mq!\u0002\u0006\f\u0011\u0003\u0001b!\u0002\n\f\u0011\u0003\u0019\u0002\"\u0002\u0011\u0002\t\u0003\t\u0003\"\u0002\u0012\u0002\t\u0007\u0019\u0003\"\u0002!\u0002\t\u0007\t\u0005\"\u0002#\u0002\t\u0007)\u0005\"B&\u0002\t\u0007a\u0005\"\u0002*\u0002\t\u0007\u0019\u0006\"B-\u0002\t\u0007Q\u0006\"B>\u0002\t\u0007a\u0018aC9vS\u000e\\7+\u001a7fGRT!\u0001D\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001d\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0012\u00035\t1BA\u0006rk&\u001c7nU3mK\u000e$8cA\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u0004\"a\u0007\u0010\u000e\u0003qQ!!H\u0007\u0002\u000f\u001d,g.\u001a:jG&\u0011q\u0004\b\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003A\t\u0011#[7qY\u001a\u0013x.\\)T\u0013:\u0004F.Y2f+\t!c\u0006\u0006\u0002&uA)aeJ\u00158Y5\t\u0011!\u0003\u0002)=\t)\u0011*\u001c9meA\u0019QC\u000b\u0017\n\u0005-2\"!B!se\u0006L\bCA\u0017/\u0019\u0001!QaL\u0002C\u0002A\u0012\u0011\u0001V\t\u0003cQ\u0002\"!\u0006\u001a\n\u0005M2\"a\u0002(pi\"Lgn\u001a\t\u0003+UJ!A\u000e\f\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u0016q%\u0011\u0011H\u0006\u0002\u0004\u0013:$\b\"B\u001e\u0004\u0001\ba\u0014AA8q!\u0011id(K\u001c\u000f\u0005E\u0001\u0011BA \u001f\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73\u0003AIg\u000e\u00157bG\u0016LU\u000e\u001d73?&sG/F\u0001C!\u00111chQ\u001c\u0011\u0007UQs'A\tj]Bc\u0017mY3J[Bd'g\u0018'p]\u001e,\u0012A\u0012\t\u0005My:u\u0007E\u0002\u0016U!\u0003\"!F%\n\u0005)3\"\u0001\u0002'p]\u001e\f1#\u001b8QY\u0006\u001cW-S7qYJzFi\\;cY\u0016,\u0012!\u0014\t\u0005Myru\u0007E\u0002\u0016U=\u0003\"!\u0006)\n\u0005E3\"A\u0002#pk\ndW-\u0001\nj]Bc\u0017mY3J[Bd'g\u0018$m_\u0006$X#\u0001+\u0011\t\u0019rTk\u000e\t\u0004+)2\u0006CA\u000bX\u0013\tAfCA\u0003GY>\fG/A\u000bj[BdgI]8n#NKe\u000e\u00157bG\u0016\u001cu\u000e\u001c7\u0016\u0007ms\u0016\rF\u0002]E6\u0004RAJ\u0014^o\u0001\u0004\"!\f0\u0005\u000b}C!\u0019\u0001\u0019\u0003\t\r{G\u000e\u001c\t\u0003[\u0005$Qa\f\u0005C\u0002ABQa\u0019\u0005A\u0004\u0011\fAA^5foB!Q#Z/h\u0013\t1gC\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tgB\u0019\u0001n\u001b1\u000e\u0003%T!A\u001b\f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002mS\n\u00191+Z9\t\u000b9D\u00019A8\u0002\u0011=\u0014H-\u001a:j]\u001e\u00042\u0001\u001d=a\u001d\t\thO\u0004\u0002sk6\t1O\u0003\u0002u\u001f\u00051AH]8pizJ\u0011aF\u0005\u0003oZ\tq\u0001]1dW\u0006<W-\u0003\u0002zu\nAqJ\u001d3fe&twM\u0003\u0002x-\u0005\u0001\u0012.\u001c9m\rJ|Wn\u0014:eKJLgnZ\u000b\u0006{\u0006U\u0011\u0011\u0001\u000b\u0006}\u0006\r\u0011q\u0003\t\u0005Myzx\u0007E\u0002.\u0003\u0003!QaX\u0005C\u0002ABaaY\u0005A\u0004\u0005\u0015\u0001#B\u000bf\u007f\u0006\u001d\u0001CBA\u0005\u0003\u001f\t\u0019\"\u0004\u0002\u0002\f)\u0019\u0011QB5\u0002\u000f5,H/\u00192mK&!\u0011\u0011CA\u0006\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\t\u0004[\u0005UA!B\u0018\n\u0005\u0004\u0001\u0004B\u00028\n\u0001\b\tI\u0002\u0005\u0003qq\u0006M\u0001"
)
public final class quickSelect {
   public static UFunc.InPlaceImpl2 implFromOrdering(final .less.colon.less view, final Ordering ordering) {
      return quickSelect$.MODULE$.implFromOrdering(view, ordering);
   }

   public static UFunc.UImpl2 implFromQSInPlaceColl(final .less.colon.less view, final Ordering ordering) {
      return quickSelect$.MODULE$.implFromQSInPlaceColl(view, ordering);
   }

   public static UFunc.InPlaceImpl2 inPlaceImpl2_Float() {
      return quickSelect$.MODULE$.inPlaceImpl2_Float();
   }

   public static UFunc.InPlaceImpl2 inPlaceImpl2_Double() {
      return quickSelect$.MODULE$.inPlaceImpl2_Double();
   }

   public static UFunc.InPlaceImpl2 inPlaceImpl2_Long() {
      return quickSelect$.MODULE$.inPlaceImpl2_Long();
   }

   public static UFunc.InPlaceImpl2 inPlaceImpl2_Int() {
      return quickSelect$.MODULE$.inPlaceImpl2_Int();
   }

   public static UFunc.UImpl2 implFromQSInPlace(final UFunc.InPlaceImpl2 op) {
      return quickSelect$.MODULE$.implFromQSInPlace(op);
   }

   public static Object withSink(final Object s) {
      return quickSelect$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return quickSelect$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return quickSelect$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return quickSelect$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return quickSelect$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return quickSelect$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return quickSelect$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return quickSelect$.MODULE$.apply(v, impl);
   }
}
