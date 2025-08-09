package scala.jdk.javaapi;

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
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.jdk.FunctionWrappers;

public final class FunctionConverters$ {
   public static final FunctionConverters$ MODULE$ = new FunctionConverters$();

   public Function2 asScalaFromBiConsumer(final BiConsumer jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaBiConsumer ? ((FunctionWrappers.AsJavaBiConsumer)jf).sf() : new FunctionWrappers.FromJavaBiConsumer(jf));
   }

   public BiConsumer asJavaBiConsumer(final Function2 sf) {
      return (BiConsumer)(sf instanceof FunctionWrappers.FromJavaBiConsumer ? ((FunctionWrappers.FromJavaBiConsumer)sf).jf() : new FunctionWrappers.AsJavaBiConsumer(sf));
   }

   public Function2 asScalaFromBiFunction(final BiFunction jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaBiFunction ? ((FunctionWrappers.AsJavaBiFunction)jf).sf() : new FunctionWrappers.FromJavaBiFunction(jf));
   }

   public BiFunction asJavaBiFunction(final Function2 sf) {
      return (BiFunction)(sf instanceof FunctionWrappers.FromJavaBiFunction ? ((FunctionWrappers.FromJavaBiFunction)sf).jf() : new FunctionWrappers.AsJavaBiFunction(sf));
   }

   public Function2 asScalaFromBiPredicate(final BiPredicate jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaBiPredicate ? ((FunctionWrappers.AsJavaBiPredicate)jf).sf() : new FunctionWrappers.FromJavaBiPredicate(jf));
   }

   public BiPredicate asJavaBiPredicate(final Function2 sf) {
      return (BiPredicate)(sf instanceof FunctionWrappers.FromJavaBiPredicate ? ((FunctionWrappers.FromJavaBiPredicate)sf).jf() : new FunctionWrappers.AsJavaBiPredicate(sf));
   }

   public Function2 asScalaFromBinaryOperator(final BinaryOperator jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaBinaryOperator ? ((FunctionWrappers.AsJavaBinaryOperator)jf).sf() : new FunctionWrappers.FromJavaBinaryOperator(jf));
   }

   public BinaryOperator asJavaBinaryOperator(final Function2 sf) {
      return (BinaryOperator)(sf instanceof FunctionWrappers.FromJavaBinaryOperator ? ((FunctionWrappers.FromJavaBinaryOperator)sf).jf() : new FunctionWrappers.AsJavaBinaryOperator(sf));
   }

   public Function0 asScalaFromBooleanSupplier(final BooleanSupplier jf) {
      return (Function0)(jf instanceof FunctionWrappers.AsJavaBooleanSupplier ? ((FunctionWrappers.AsJavaBooleanSupplier)jf).sf() : new FunctionWrappers.FromJavaBooleanSupplier(jf));
   }

   public BooleanSupplier asJavaBooleanSupplier(final Function0 sf) {
      return (BooleanSupplier)(sf instanceof FunctionWrappers.FromJavaBooleanSupplier ? ((FunctionWrappers.FromJavaBooleanSupplier)sf).jf() : new FunctionWrappers.AsJavaBooleanSupplier(sf));
   }

   public Function1 asScalaFromConsumer(final Consumer jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaConsumer ? ((FunctionWrappers.AsJavaConsumer)jf).sf() : new FunctionWrappers.FromJavaConsumer(jf));
   }

   public Consumer asJavaConsumer(final Function1 sf) {
      return (Consumer)(sf instanceof FunctionWrappers.FromJavaConsumer ? ((FunctionWrappers.FromJavaConsumer)sf).jf() : new FunctionWrappers.AsJavaConsumer(sf));
   }

   public Function2 asScalaFromDoubleBinaryOperator(final DoubleBinaryOperator jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaDoubleBinaryOperator ? ((FunctionWrappers.AsJavaDoubleBinaryOperator)jf).sf() : new FunctionWrappers.FromJavaDoubleBinaryOperator(jf));
   }

   public DoubleBinaryOperator asJavaDoubleBinaryOperator(final Function2 sf) {
      return (DoubleBinaryOperator)(sf instanceof FunctionWrappers.FromJavaDoubleBinaryOperator ? ((FunctionWrappers.FromJavaDoubleBinaryOperator)sf).jf() : new FunctionWrappers.AsJavaDoubleBinaryOperator(sf));
   }

   public Function1 asScalaFromDoubleConsumer(final DoubleConsumer jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoubleConsumer ? ((FunctionWrappers.AsJavaDoubleConsumer)jf).sf() : new FunctionWrappers.FromJavaDoubleConsumer(jf));
   }

   public DoubleConsumer asJavaDoubleConsumer(final Function1 sf) {
      return (DoubleConsumer)(sf instanceof FunctionWrappers.FromJavaDoubleConsumer ? ((FunctionWrappers.FromJavaDoubleConsumer)sf).jf() : new FunctionWrappers.AsJavaDoubleConsumer(sf));
   }

   public Function1 asScalaFromDoubleFunction(final DoubleFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoubleFunction ? ((FunctionWrappers.AsJavaDoubleFunction)jf).sf() : new FunctionWrappers.FromJavaDoubleFunction(jf));
   }

   public DoubleFunction asJavaDoubleFunction(final Function1 sf) {
      return (DoubleFunction)(sf instanceof FunctionWrappers.FromJavaDoubleFunction ? ((FunctionWrappers.FromJavaDoubleFunction)sf).jf() : new FunctionWrappers.AsJavaDoubleFunction(sf));
   }

   public Function1 asScalaFromDoublePredicate(final DoublePredicate jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoublePredicate ? ((FunctionWrappers.AsJavaDoublePredicate)jf).sf() : new FunctionWrappers.FromJavaDoublePredicate(jf));
   }

   public DoublePredicate asJavaDoublePredicate(final Function1 sf) {
      return (DoublePredicate)(sf instanceof FunctionWrappers.FromJavaDoublePredicate ? ((FunctionWrappers.FromJavaDoublePredicate)sf).jf() : new FunctionWrappers.AsJavaDoublePredicate(sf));
   }

   public Function0 asScalaFromDoubleSupplier(final DoubleSupplier jf) {
      return (Function0)(jf instanceof FunctionWrappers.AsJavaDoubleSupplier ? ((FunctionWrappers.AsJavaDoubleSupplier)jf).sf() : new FunctionWrappers.FromJavaDoubleSupplier(jf));
   }

   public DoubleSupplier asJavaDoubleSupplier(final Function0 sf) {
      return (DoubleSupplier)(sf instanceof FunctionWrappers.FromJavaDoubleSupplier ? ((FunctionWrappers.FromJavaDoubleSupplier)sf).jf() : new FunctionWrappers.AsJavaDoubleSupplier(sf));
   }

   public Function1 asScalaFromDoubleToIntFunction(final DoubleToIntFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoubleToIntFunction ? ((FunctionWrappers.AsJavaDoubleToIntFunction)jf).sf() : new FunctionWrappers.FromJavaDoubleToIntFunction(jf));
   }

   public DoubleToIntFunction asJavaDoubleToIntFunction(final Function1 sf) {
      return (DoubleToIntFunction)(sf instanceof FunctionWrappers.FromJavaDoubleToIntFunction ? ((FunctionWrappers.FromJavaDoubleToIntFunction)sf).jf() : new FunctionWrappers.AsJavaDoubleToIntFunction(sf));
   }

   public Function1 asScalaFromDoubleToLongFunction(final DoubleToLongFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoubleToLongFunction ? ((FunctionWrappers.AsJavaDoubleToLongFunction)jf).sf() : new FunctionWrappers.FromJavaDoubleToLongFunction(jf));
   }

   public DoubleToLongFunction asJavaDoubleToLongFunction(final Function1 sf) {
      return (DoubleToLongFunction)(sf instanceof FunctionWrappers.FromJavaDoubleToLongFunction ? ((FunctionWrappers.FromJavaDoubleToLongFunction)sf).jf() : new FunctionWrappers.AsJavaDoubleToLongFunction(sf));
   }

   public Function1 asScalaFromDoubleUnaryOperator(final DoubleUnaryOperator jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaDoubleUnaryOperator ? ((FunctionWrappers.AsJavaDoubleUnaryOperator)jf).sf() : new FunctionWrappers.FromJavaDoubleUnaryOperator(jf));
   }

   public DoubleUnaryOperator asJavaDoubleUnaryOperator(final Function1 sf) {
      return (DoubleUnaryOperator)(sf instanceof FunctionWrappers.FromJavaDoubleUnaryOperator ? ((FunctionWrappers.FromJavaDoubleUnaryOperator)sf).jf() : new FunctionWrappers.AsJavaDoubleUnaryOperator(sf));
   }

   public Function1 asScalaFromFunction(final Function jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaFunction ? ((FunctionWrappers.AsJavaFunction)jf).sf() : new FunctionWrappers.FromJavaFunction(jf));
   }

   public Function asJavaFunction(final Function1 sf) {
      return (Function)(sf instanceof FunctionWrappers.FromJavaFunction ? ((FunctionWrappers.FromJavaFunction)sf).jf() : new FunctionWrappers.AsJavaFunction(sf));
   }

   public Function2 asScalaFromIntBinaryOperator(final IntBinaryOperator jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaIntBinaryOperator ? ((FunctionWrappers.AsJavaIntBinaryOperator)jf).sf() : new FunctionWrappers.FromJavaIntBinaryOperator(jf));
   }

   public IntBinaryOperator asJavaIntBinaryOperator(final Function2 sf) {
      return (IntBinaryOperator)(sf instanceof FunctionWrappers.FromJavaIntBinaryOperator ? ((FunctionWrappers.FromJavaIntBinaryOperator)sf).jf() : new FunctionWrappers.AsJavaIntBinaryOperator(sf));
   }

   public Function1 asScalaFromIntConsumer(final IntConsumer jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntConsumer ? ((FunctionWrappers.AsJavaIntConsumer)jf).sf() : new FunctionWrappers.FromJavaIntConsumer(jf));
   }

   public IntConsumer asJavaIntConsumer(final Function1 sf) {
      return (IntConsumer)(sf instanceof FunctionWrappers.FromJavaIntConsumer ? ((FunctionWrappers.FromJavaIntConsumer)sf).jf() : new FunctionWrappers.AsJavaIntConsumer(sf));
   }

   public Function1 asScalaFromIntFunction(final IntFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntFunction ? ((FunctionWrappers.AsJavaIntFunction)jf).sf() : new FunctionWrappers.FromJavaIntFunction(jf));
   }

   public IntFunction asJavaIntFunction(final Function1 sf) {
      return (IntFunction)(sf instanceof FunctionWrappers.FromJavaIntFunction ? ((FunctionWrappers.FromJavaIntFunction)sf).jf() : new FunctionWrappers.AsJavaIntFunction(sf));
   }

   public Function1 asScalaFromIntPredicate(final IntPredicate jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntPredicate ? ((FunctionWrappers.AsJavaIntPredicate)jf).sf() : new FunctionWrappers.FromJavaIntPredicate(jf));
   }

   public IntPredicate asJavaIntPredicate(final Function1 sf) {
      return (IntPredicate)(sf instanceof FunctionWrappers.FromJavaIntPredicate ? ((FunctionWrappers.FromJavaIntPredicate)sf).jf() : new FunctionWrappers.AsJavaIntPredicate(sf));
   }

   public Function0 asScalaFromIntSupplier(final IntSupplier jf) {
      return (Function0)(jf instanceof FunctionWrappers.AsJavaIntSupplier ? ((FunctionWrappers.AsJavaIntSupplier)jf).sf() : new FunctionWrappers.FromJavaIntSupplier(jf));
   }

   public IntSupplier asJavaIntSupplier(final Function0 sf) {
      return (IntSupplier)(sf instanceof FunctionWrappers.FromJavaIntSupplier ? ((FunctionWrappers.FromJavaIntSupplier)sf).jf() : new FunctionWrappers.AsJavaIntSupplier(sf));
   }

   public Function1 asScalaFromIntToDoubleFunction(final IntToDoubleFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntToDoubleFunction ? ((FunctionWrappers.AsJavaIntToDoubleFunction)jf).sf() : new FunctionWrappers.FromJavaIntToDoubleFunction(jf));
   }

   public IntToDoubleFunction asJavaIntToDoubleFunction(final Function1 sf) {
      return (IntToDoubleFunction)(sf instanceof FunctionWrappers.FromJavaIntToDoubleFunction ? ((FunctionWrappers.FromJavaIntToDoubleFunction)sf).jf() : new FunctionWrappers.AsJavaIntToDoubleFunction(sf));
   }

   public Function1 asScalaFromIntToLongFunction(final IntToLongFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntToLongFunction ? ((FunctionWrappers.AsJavaIntToLongFunction)jf).sf() : new FunctionWrappers.FromJavaIntToLongFunction(jf));
   }

   public IntToLongFunction asJavaIntToLongFunction(final Function1 sf) {
      return (IntToLongFunction)(sf instanceof FunctionWrappers.FromJavaIntToLongFunction ? ((FunctionWrappers.FromJavaIntToLongFunction)sf).jf() : new FunctionWrappers.AsJavaIntToLongFunction(sf));
   }

   public Function1 asScalaFromIntUnaryOperator(final IntUnaryOperator jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaIntUnaryOperator ? ((FunctionWrappers.AsJavaIntUnaryOperator)jf).sf() : new FunctionWrappers.FromJavaIntUnaryOperator(jf));
   }

   public IntUnaryOperator asJavaIntUnaryOperator(final Function1 sf) {
      return (IntUnaryOperator)(sf instanceof FunctionWrappers.FromJavaIntUnaryOperator ? ((FunctionWrappers.FromJavaIntUnaryOperator)sf).jf() : new FunctionWrappers.AsJavaIntUnaryOperator(sf));
   }

   public Function2 asScalaFromLongBinaryOperator(final LongBinaryOperator jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaLongBinaryOperator ? ((FunctionWrappers.AsJavaLongBinaryOperator)jf).sf() : new FunctionWrappers.FromJavaLongBinaryOperator(jf));
   }

   public LongBinaryOperator asJavaLongBinaryOperator(final Function2 sf) {
      return (LongBinaryOperator)(sf instanceof FunctionWrappers.FromJavaLongBinaryOperator ? ((FunctionWrappers.FromJavaLongBinaryOperator)sf).jf() : new FunctionWrappers.AsJavaLongBinaryOperator(sf));
   }

   public Function1 asScalaFromLongConsumer(final LongConsumer jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongConsumer ? ((FunctionWrappers.AsJavaLongConsumer)jf).sf() : new FunctionWrappers.FromJavaLongConsumer(jf));
   }

   public LongConsumer asJavaLongConsumer(final Function1 sf) {
      return (LongConsumer)(sf instanceof FunctionWrappers.FromJavaLongConsumer ? ((FunctionWrappers.FromJavaLongConsumer)sf).jf() : new FunctionWrappers.AsJavaLongConsumer(sf));
   }

   public Function1 asScalaFromLongFunction(final LongFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongFunction ? ((FunctionWrappers.AsJavaLongFunction)jf).sf() : new FunctionWrappers.FromJavaLongFunction(jf));
   }

   public LongFunction asJavaLongFunction(final Function1 sf) {
      return (LongFunction)(sf instanceof FunctionWrappers.FromJavaLongFunction ? ((FunctionWrappers.FromJavaLongFunction)sf).jf() : new FunctionWrappers.AsJavaLongFunction(sf));
   }

   public Function1 asScalaFromLongPredicate(final LongPredicate jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongPredicate ? ((FunctionWrappers.AsJavaLongPredicate)jf).sf() : new FunctionWrappers.FromJavaLongPredicate(jf));
   }

   public LongPredicate asJavaLongPredicate(final Function1 sf) {
      return (LongPredicate)(sf instanceof FunctionWrappers.FromJavaLongPredicate ? ((FunctionWrappers.FromJavaLongPredicate)sf).jf() : new FunctionWrappers.AsJavaLongPredicate(sf));
   }

   public Function0 asScalaFromLongSupplier(final LongSupplier jf) {
      return (Function0)(jf instanceof FunctionWrappers.AsJavaLongSupplier ? ((FunctionWrappers.AsJavaLongSupplier)jf).sf() : new FunctionWrappers.FromJavaLongSupplier(jf));
   }

   public LongSupplier asJavaLongSupplier(final Function0 sf) {
      return (LongSupplier)(sf instanceof FunctionWrappers.FromJavaLongSupplier ? ((FunctionWrappers.FromJavaLongSupplier)sf).jf() : new FunctionWrappers.AsJavaLongSupplier(sf));
   }

   public Function1 asScalaFromLongToDoubleFunction(final LongToDoubleFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongToDoubleFunction ? ((FunctionWrappers.AsJavaLongToDoubleFunction)jf).sf() : new FunctionWrappers.FromJavaLongToDoubleFunction(jf));
   }

   public LongToDoubleFunction asJavaLongToDoubleFunction(final Function1 sf) {
      return (LongToDoubleFunction)(sf instanceof FunctionWrappers.FromJavaLongToDoubleFunction ? ((FunctionWrappers.FromJavaLongToDoubleFunction)sf).jf() : new FunctionWrappers.AsJavaLongToDoubleFunction(sf));
   }

   public Function1 asScalaFromLongToIntFunction(final LongToIntFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongToIntFunction ? ((FunctionWrappers.AsJavaLongToIntFunction)jf).sf() : new FunctionWrappers.FromJavaLongToIntFunction(jf));
   }

   public LongToIntFunction asJavaLongToIntFunction(final Function1 sf) {
      return (LongToIntFunction)(sf instanceof FunctionWrappers.FromJavaLongToIntFunction ? ((FunctionWrappers.FromJavaLongToIntFunction)sf).jf() : new FunctionWrappers.AsJavaLongToIntFunction(sf));
   }

   public Function1 asScalaFromLongUnaryOperator(final LongUnaryOperator jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaLongUnaryOperator ? ((FunctionWrappers.AsJavaLongUnaryOperator)jf).sf() : new FunctionWrappers.FromJavaLongUnaryOperator(jf));
   }

   public LongUnaryOperator asJavaLongUnaryOperator(final Function1 sf) {
      return (LongUnaryOperator)(sf instanceof FunctionWrappers.FromJavaLongUnaryOperator ? ((FunctionWrappers.FromJavaLongUnaryOperator)sf).jf() : new FunctionWrappers.AsJavaLongUnaryOperator(sf));
   }

   public Function2 asScalaFromObjDoubleConsumer(final ObjDoubleConsumer jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaObjDoubleConsumer ? ((FunctionWrappers.AsJavaObjDoubleConsumer)jf).sf() : new FunctionWrappers.FromJavaObjDoubleConsumer(jf));
   }

   public ObjDoubleConsumer asJavaObjDoubleConsumer(final Function2 sf) {
      return (ObjDoubleConsumer)(sf instanceof FunctionWrappers.FromJavaObjDoubleConsumer ? ((FunctionWrappers.FromJavaObjDoubleConsumer)sf).jf() : new FunctionWrappers.AsJavaObjDoubleConsumer(sf));
   }

   public Function2 asScalaFromObjIntConsumer(final ObjIntConsumer jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaObjIntConsumer ? ((FunctionWrappers.AsJavaObjIntConsumer)jf).sf() : new FunctionWrappers.FromJavaObjIntConsumer(jf));
   }

   public ObjIntConsumer asJavaObjIntConsumer(final Function2 sf) {
      return (ObjIntConsumer)(sf instanceof FunctionWrappers.FromJavaObjIntConsumer ? ((FunctionWrappers.FromJavaObjIntConsumer)sf).jf() : new FunctionWrappers.AsJavaObjIntConsumer(sf));
   }

   public Function2 asScalaFromObjLongConsumer(final ObjLongConsumer jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaObjLongConsumer ? ((FunctionWrappers.AsJavaObjLongConsumer)jf).sf() : new FunctionWrappers.FromJavaObjLongConsumer(jf));
   }

   public ObjLongConsumer asJavaObjLongConsumer(final Function2 sf) {
      return (ObjLongConsumer)(sf instanceof FunctionWrappers.FromJavaObjLongConsumer ? ((FunctionWrappers.FromJavaObjLongConsumer)sf).jf() : new FunctionWrappers.AsJavaObjLongConsumer(sf));
   }

   public Function1 asScalaFromPredicate(final Predicate jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaPredicate ? ((FunctionWrappers.AsJavaPredicate)jf).sf() : new FunctionWrappers.FromJavaPredicate(jf));
   }

   public Predicate asJavaPredicate(final Function1 sf) {
      return (Predicate)(sf instanceof FunctionWrappers.FromJavaPredicate ? ((FunctionWrappers.FromJavaPredicate)sf).jf() : new FunctionWrappers.AsJavaPredicate(sf));
   }

   public Function0 asScalaFromSupplier(final Supplier jf) {
      return (Function0)(jf instanceof FunctionWrappers.AsJavaSupplier ? ((FunctionWrappers.AsJavaSupplier)jf).sf() : new FunctionWrappers.FromJavaSupplier(jf));
   }

   public Supplier asJavaSupplier(final Function0 sf) {
      return (Supplier)(sf instanceof FunctionWrappers.FromJavaSupplier ? ((FunctionWrappers.FromJavaSupplier)sf).jf() : new FunctionWrappers.AsJavaSupplier(sf));
   }

   public Function2 asScalaFromToDoubleBiFunction(final ToDoubleBiFunction jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaToDoubleBiFunction ? ((FunctionWrappers.AsJavaToDoubleBiFunction)jf).sf() : new FunctionWrappers.FromJavaToDoubleBiFunction(jf));
   }

   public ToDoubleBiFunction asJavaToDoubleBiFunction(final Function2 sf) {
      return (ToDoubleBiFunction)(sf instanceof FunctionWrappers.FromJavaToDoubleBiFunction ? ((FunctionWrappers.FromJavaToDoubleBiFunction)sf).jf() : new FunctionWrappers.AsJavaToDoubleBiFunction(sf));
   }

   public Function1 asScalaFromToDoubleFunction(final ToDoubleFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaToDoubleFunction ? ((FunctionWrappers.AsJavaToDoubleFunction)jf).sf() : new FunctionWrappers.FromJavaToDoubleFunction(jf));
   }

   public ToDoubleFunction asJavaToDoubleFunction(final Function1 sf) {
      return (ToDoubleFunction)(sf instanceof FunctionWrappers.FromJavaToDoubleFunction ? ((FunctionWrappers.FromJavaToDoubleFunction)sf).jf() : new FunctionWrappers.AsJavaToDoubleFunction(sf));
   }

   public Function2 asScalaFromToIntBiFunction(final ToIntBiFunction jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaToIntBiFunction ? ((FunctionWrappers.AsJavaToIntBiFunction)jf).sf() : new FunctionWrappers.FromJavaToIntBiFunction(jf));
   }

   public ToIntBiFunction asJavaToIntBiFunction(final Function2 sf) {
      return (ToIntBiFunction)(sf instanceof FunctionWrappers.FromJavaToIntBiFunction ? ((FunctionWrappers.FromJavaToIntBiFunction)sf).jf() : new FunctionWrappers.AsJavaToIntBiFunction(sf));
   }

   public Function1 asScalaFromToIntFunction(final ToIntFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaToIntFunction ? ((FunctionWrappers.AsJavaToIntFunction)jf).sf() : new FunctionWrappers.FromJavaToIntFunction(jf));
   }

   public ToIntFunction asJavaToIntFunction(final Function1 sf) {
      return (ToIntFunction)(sf instanceof FunctionWrappers.FromJavaToIntFunction ? ((FunctionWrappers.FromJavaToIntFunction)sf).jf() : new FunctionWrappers.AsJavaToIntFunction(sf));
   }

   public Function2 asScalaFromToLongBiFunction(final ToLongBiFunction jf) {
      return (Function2)(jf instanceof FunctionWrappers.AsJavaToLongBiFunction ? ((FunctionWrappers.AsJavaToLongBiFunction)jf).sf() : new FunctionWrappers.FromJavaToLongBiFunction(jf));
   }

   public ToLongBiFunction asJavaToLongBiFunction(final Function2 sf) {
      return (ToLongBiFunction)(sf instanceof FunctionWrappers.FromJavaToLongBiFunction ? ((FunctionWrappers.FromJavaToLongBiFunction)sf).jf() : new FunctionWrappers.AsJavaToLongBiFunction(sf));
   }

   public Function1 asScalaFromToLongFunction(final ToLongFunction jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaToLongFunction ? ((FunctionWrappers.AsJavaToLongFunction)jf).sf() : new FunctionWrappers.FromJavaToLongFunction(jf));
   }

   public ToLongFunction asJavaToLongFunction(final Function1 sf) {
      return (ToLongFunction)(sf instanceof FunctionWrappers.FromJavaToLongFunction ? ((FunctionWrappers.FromJavaToLongFunction)sf).jf() : new FunctionWrappers.AsJavaToLongFunction(sf));
   }

   public Function1 asScalaFromUnaryOperator(final UnaryOperator jf) {
      return (Function1)(jf instanceof FunctionWrappers.AsJavaUnaryOperator ? ((FunctionWrappers.AsJavaUnaryOperator)jf).sf() : new FunctionWrappers.FromJavaUnaryOperator(jf));
   }

   public UnaryOperator asJavaUnaryOperator(final Function1 sf) {
      return (UnaryOperator)(sf instanceof FunctionWrappers.FromJavaUnaryOperator ? ((FunctionWrappers.FromJavaUnaryOperator)sf).jf() : new FunctionWrappers.AsJavaUnaryOperator(sf));
   }

   private FunctionConverters$() {
   }
}
