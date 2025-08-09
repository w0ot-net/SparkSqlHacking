package scala.jdk;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import scala.$eq$colon$eq;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y9Qa\u0001\u0003\t\u0002%1Qa\u0003\u0003\t\u00021AQ\u0001F\u0001\u0005\u0002U\t!CR;oGRLwN\\\"p]Z,'\u000f^3sg*\u0011QAB\u0001\u0004U\u0012\\'\"A\u0004\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001A\u0011!\"A\u0007\u0002\t\t\u0011b)\u001e8di&|gnQ8om\u0016\u0014H/\u001a:t'\r\tQ\"\u0005\t\u0003\u001d=i\u0011AB\u0005\u0003!\u0019\u0011a!\u00118z%\u00164\u0007C\u0001\u0006\u0013\u0013\t\u0019BAA\u000eQe&|'/\u001b;za\u0019+hn\u0019;j_:,\u0005\u0010^3og&|gn]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%\u0001"
)
public final class FunctionConverters {
   public static UnaryOperator enrichAsScalaFromUnaryOperator(final UnaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromUnaryOperator(jf);
   }

   public static ToLongFunction enrichAsScalaFromToLongFunction(final ToLongFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToLongFunction(jf);
   }

   public static ToLongBiFunction enrichAsScalaFromToLongBiFunction(final ToLongBiFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToLongBiFunction(jf);
   }

   public static ToIntFunction enrichAsScalaFromToIntFunction(final ToIntFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToIntFunction(jf);
   }

   public static ToIntBiFunction enrichAsScalaFromToIntBiFunction(final ToIntBiFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToIntBiFunction(jf);
   }

   public static ToDoubleFunction enrichAsScalaFromToDoubleFunction(final ToDoubleFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToDoubleFunction(jf);
   }

   public static ToDoubleBiFunction enrichAsScalaFromToDoubleBiFunction(final ToDoubleBiFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromToDoubleBiFunction(jf);
   }

   public static Supplier enrichAsScalaFromSupplier(final Supplier jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromSupplier(jf);
   }

   public static Predicate enrichAsScalaFromPredicate(final Predicate jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromPredicate(jf);
   }

   public static ObjLongConsumer enrichAsScalaFromObjLongConsumer(final ObjLongConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromObjLongConsumer(jf);
   }

   public static ObjIntConsumer enrichAsScalaFromObjIntConsumer(final ObjIntConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromObjIntConsumer(jf);
   }

   public static ObjDoubleConsumer enrichAsScalaFromObjDoubleConsumer(final ObjDoubleConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromObjDoubleConsumer(jf);
   }

   public static LongUnaryOperator enrichAsScalaFromLongUnaryOperator(final LongUnaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongUnaryOperator(jf);
   }

   public static LongToIntFunction enrichAsScalaFromLongToIntFunction(final LongToIntFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongToIntFunction(jf);
   }

   public static LongToDoubleFunction enrichAsScalaFromLongToDoubleFunction(final LongToDoubleFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongToDoubleFunction(jf);
   }

   public static LongSupplier enrichAsScalaFromLongSupplier(final LongSupplier jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongSupplier(jf);
   }

   public static LongPredicate enrichAsScalaFromLongPredicate(final LongPredicate jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongPredicate(jf);
   }

   public static LongFunction enrichAsScalaFromLongFunction(final LongFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongFunction(jf);
   }

   public static LongConsumer enrichAsScalaFromLongConsumer(final LongConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongConsumer(jf);
   }

   public static LongBinaryOperator enrichAsScalaFromLongBinaryOperator(final LongBinaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromLongBinaryOperator(jf);
   }

   public static IntUnaryOperator enrichAsScalaFromIntUnaryOperator(final IntUnaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntUnaryOperator(jf);
   }

   public static IntToLongFunction enrichAsScalaFromIntToLongFunction(final IntToLongFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntToLongFunction(jf);
   }

   public static IntToDoubleFunction enrichAsScalaFromIntToDoubleFunction(final IntToDoubleFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntToDoubleFunction(jf);
   }

   public static IntSupplier enrichAsScalaFromIntSupplier(final IntSupplier jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntSupplier(jf);
   }

   public static IntPredicate enrichAsScalaFromIntPredicate(final IntPredicate jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntPredicate(jf);
   }

   public static IntFunction enrichAsScalaFromIntFunction(final IntFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntFunction(jf);
   }

   public static IntConsumer enrichAsScalaFromIntConsumer(final IntConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntConsumer(jf);
   }

   public static IntBinaryOperator enrichAsScalaFromIntBinaryOperator(final IntBinaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromIntBinaryOperator(jf);
   }

   public static Function enrichAsScalaFromFunction(final Function jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromFunction(jf);
   }

   public static DoubleUnaryOperator enrichAsScalaFromDoubleUnaryOperator(final DoubleUnaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleUnaryOperator(jf);
   }

   public static DoubleToLongFunction enrichAsScalaFromDoubleToLongFunction(final DoubleToLongFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleToLongFunction(jf);
   }

   public static DoubleToIntFunction enrichAsScalaFromDoubleToIntFunction(final DoubleToIntFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleToIntFunction(jf);
   }

   public static DoubleSupplier enrichAsScalaFromDoubleSupplier(final DoubleSupplier jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleSupplier(jf);
   }

   public static DoublePredicate enrichAsScalaFromDoublePredicate(final DoublePredicate jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoublePredicate(jf);
   }

   public static DoubleFunction enrichAsScalaFromDoubleFunction(final DoubleFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleFunction(jf);
   }

   public static DoubleConsumer enrichAsScalaFromDoubleConsumer(final DoubleConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleConsumer(jf);
   }

   public static DoubleBinaryOperator enrichAsScalaFromDoubleBinaryOperator(final DoubleBinaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromDoubleBinaryOperator(jf);
   }

   public static Consumer enrichAsScalaFromConsumer(final Consumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromConsumer(jf);
   }

   public static BooleanSupplier enrichAsScalaFromBooleanSupplier(final BooleanSupplier jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromBooleanSupplier(jf);
   }

   public static BinaryOperator enrichAsScalaFromBinaryOperator(final BinaryOperator jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromBinaryOperator(jf);
   }

   public static BiPredicate enrichAsScalaFromBiPredicate(final BiPredicate jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromBiPredicate(jf);
   }

   public static BiFunction enrichAsScalaFromBiFunction(final BiFunction jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromBiFunction(jf);
   }

   public static BiConsumer enrichAsScalaFromBiConsumer(final BiConsumer jf) {
      return FunctionConverters$.MODULE$.enrichAsScalaFromBiConsumer(jf);
   }

   public static Function1 enrichAsJavaLongUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongUnaryOperator(sf, evA0);
   }

   public static Function1 enrichAsJavaLongToIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongToIntFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaLongToDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongToDoubleFunction(sf, evA0);
   }

   public static Function0 enrichAsJavaLongSupplier(final Function0 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongSupplier(sf);
   }

   public static Function1 enrichAsJavaLongPredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongPredicate(sf, evA0);
   }

   public static Function1 enrichAsJavaLongConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongConsumer(sf, evA0);
   }

   public static Function2 enrichAsJavaLongBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongBinaryOperator(sf, evA0, evA1);
   }

   public static Function1 enrichAsJavaIntUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntUnaryOperator(sf, evA0);
   }

   public static Function1 enrichAsJavaIntToLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntToLongFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaIntToDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntToDoubleFunction(sf, evA0);
   }

   public static Function0 enrichAsJavaIntSupplier(final Function0 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntSupplier(sf);
   }

   public static Function1 enrichAsJavaIntPredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntPredicate(sf, evA0);
   }

   public static Function1 enrichAsJavaIntConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntConsumer(sf, evA0);
   }

   public static Function2 enrichAsJavaIntBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntBinaryOperator(sf, evA0, evA1);
   }

   public static Function1 enrichAsJavaDoubleUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleUnaryOperator(sf, evA0);
   }

   public static Function1 enrichAsJavaDoubleToLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleToLongFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaDoubleToIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleToIntFunction(sf, evA0);
   }

   public static Function0 enrichAsJavaDoubleSupplier(final Function0 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleSupplier(sf);
   }

   public static Function1 enrichAsJavaDoublePredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoublePredicate(sf, evA0);
   }

   public static Function1 enrichAsJavaDoubleConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleConsumer(sf, evA0);
   }

   public static Function2 enrichAsJavaDoubleBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleBinaryOperator(sf, evA0, evA1);
   }

   public static Function0 enrichAsJavaBooleanSupplier(final Function0 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaBooleanSupplier(sf);
   }

   public static Function1 enrichAsJavaUnaryOperator(final Function1 sf, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaUnaryOperator(sf, evA1);
   }

   public static Function1 enrichAsJavaToLongFunction(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToLongFunction(sf);
   }

   public static Function1 enrichAsJavaToIntFunction(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToIntFunction(sf);
   }

   public static Function1 enrichAsJavaToDoubleFunction(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToDoubleFunction(sf);
   }

   public static Function0 enrichAsJavaSupplier(final Function0 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaSupplier(sf);
   }

   public static Function1 enrichAsJavaPredicate(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaPredicate(sf);
   }

   public static Function2 enrichAsJavaObjLongConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaObjLongConsumer(sf, evA1);
   }

   public static Function2 enrichAsJavaObjIntConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaObjIntConsumer(sf, evA1);
   }

   public static Function2 enrichAsJavaObjDoubleConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return FunctionConverters$.MODULE$.enrichAsJavaObjDoubleConsumer(sf, evA1);
   }

   public static Function1 enrichAsJavaLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaLongFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaIntFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return FunctionConverters$.MODULE$.enrichAsJavaDoubleFunction(sf, evA0);
   }

   public static Function1 enrichAsJavaConsumer(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaConsumer(sf);
   }

   public static Function2 enrichAsJavaBinaryOperator(final Function2 sf, final $eq$colon$eq evA1, final $eq$colon$eq evA2) {
      return FunctionConverters$.MODULE$.enrichAsJavaBinaryOperator(sf, evA1, evA2);
   }

   public static Function2 enrichAsJavaToLongBiFunction(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToLongBiFunction(sf);
   }

   public static Function2 enrichAsJavaToIntBiFunction(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToIntBiFunction(sf);
   }

   public static Function2 enrichAsJavaToDoubleBiFunction(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaToDoubleBiFunction(sf);
   }

   public static Function1 enrichAsJavaFunction(final Function1 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaFunction(sf);
   }

   public static Function2 enrichAsJavaBiPredicate(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaBiPredicate(sf);
   }

   public static Function2 enrichAsJavaBiConsumer(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaBiConsumer(sf);
   }

   public static Function2 enrichAsJavaBiFunction(final Function2 sf) {
      return FunctionConverters$.MODULE$.enrichAsJavaBiFunction(sf);
   }
}
