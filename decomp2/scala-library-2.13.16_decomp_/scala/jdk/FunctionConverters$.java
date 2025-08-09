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

public final class FunctionConverters$ implements Priority0FunctionExtensions {
   public static final FunctionConverters$ MODULE$ = new FunctionConverters$();

   static {
      FunctionConverters$ var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
   }

   public Function0 enrichAsJavaBooleanSupplier(final Function0 sf) {
      return Priority0FunctionExtensions.enrichAsJavaBooleanSupplier$(this, sf);
   }

   public Function2 enrichAsJavaDoubleBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleBinaryOperator$(this, sf, evA0, evA1);
   }

   public Function1 enrichAsJavaDoubleConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleConsumer$(this, sf, evA0);
   }

   public Function1 enrichAsJavaDoublePredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaDoublePredicate$(this, sf, evA0);
   }

   public Function0 enrichAsJavaDoubleSupplier(final Function0 sf) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleSupplier$(this, sf);
   }

   public Function1 enrichAsJavaDoubleToIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleToIntFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaDoubleToLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleToLongFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaDoubleUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaDoubleUnaryOperator$(this, sf, evA0);
   }

   public Function2 enrichAsJavaIntBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return Priority0FunctionExtensions.enrichAsJavaIntBinaryOperator$(this, sf, evA0, evA1);
   }

   public Function1 enrichAsJavaIntConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaIntConsumer$(this, sf, evA0);
   }

   public Function1 enrichAsJavaIntPredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaIntPredicate$(this, sf, evA0);
   }

   public Function0 enrichAsJavaIntSupplier(final Function0 sf) {
      return Priority0FunctionExtensions.enrichAsJavaIntSupplier$(this, sf);
   }

   public Function1 enrichAsJavaIntToDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaIntToDoubleFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaIntToLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaIntToLongFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaIntUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaIntUnaryOperator$(this, sf, evA0);
   }

   public Function2 enrichAsJavaLongBinaryOperator(final Function2 sf, final $eq$colon$eq evA0, final $eq$colon$eq evA1) {
      return Priority0FunctionExtensions.enrichAsJavaLongBinaryOperator$(this, sf, evA0, evA1);
   }

   public Function1 enrichAsJavaLongConsumer(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaLongConsumer$(this, sf, evA0);
   }

   public Function1 enrichAsJavaLongPredicate(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaLongPredicate$(this, sf, evA0);
   }

   public Function0 enrichAsJavaLongSupplier(final Function0 sf) {
      return Priority0FunctionExtensions.enrichAsJavaLongSupplier$(this, sf);
   }

   public Function1 enrichAsJavaLongToDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaLongToDoubleFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaLongToIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaLongToIntFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaLongUnaryOperator(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority0FunctionExtensions.enrichAsJavaLongUnaryOperator$(this, sf, evA0);
   }

   public BiConsumer enrichAsScalaFromBiConsumer(final BiConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromBiConsumer$(this, jf);
   }

   public BiFunction enrichAsScalaFromBiFunction(final BiFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromBiFunction$(this, jf);
   }

   public BiPredicate enrichAsScalaFromBiPredicate(final BiPredicate jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromBiPredicate$(this, jf);
   }

   public BinaryOperator enrichAsScalaFromBinaryOperator(final BinaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromBinaryOperator$(this, jf);
   }

   public BooleanSupplier enrichAsScalaFromBooleanSupplier(final BooleanSupplier jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromBooleanSupplier$(this, jf);
   }

   public Consumer enrichAsScalaFromConsumer(final Consumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromConsumer$(this, jf);
   }

   public DoubleBinaryOperator enrichAsScalaFromDoubleBinaryOperator(final DoubleBinaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleBinaryOperator$(this, jf);
   }

   public DoubleConsumer enrichAsScalaFromDoubleConsumer(final DoubleConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleConsumer$(this, jf);
   }

   public DoubleFunction enrichAsScalaFromDoubleFunction(final DoubleFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleFunction$(this, jf);
   }

   public DoublePredicate enrichAsScalaFromDoublePredicate(final DoublePredicate jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoublePredicate$(this, jf);
   }

   public DoubleSupplier enrichAsScalaFromDoubleSupplier(final DoubleSupplier jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleSupplier$(this, jf);
   }

   public DoubleToIntFunction enrichAsScalaFromDoubleToIntFunction(final DoubleToIntFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleToIntFunction$(this, jf);
   }

   public DoubleToLongFunction enrichAsScalaFromDoubleToLongFunction(final DoubleToLongFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleToLongFunction$(this, jf);
   }

   public DoubleUnaryOperator enrichAsScalaFromDoubleUnaryOperator(final DoubleUnaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromDoubleUnaryOperator$(this, jf);
   }

   public Function enrichAsScalaFromFunction(final Function jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromFunction$(this, jf);
   }

   public IntBinaryOperator enrichAsScalaFromIntBinaryOperator(final IntBinaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntBinaryOperator$(this, jf);
   }

   public IntConsumer enrichAsScalaFromIntConsumer(final IntConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntConsumer$(this, jf);
   }

   public IntFunction enrichAsScalaFromIntFunction(final IntFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntFunction$(this, jf);
   }

   public IntPredicate enrichAsScalaFromIntPredicate(final IntPredicate jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntPredicate$(this, jf);
   }

   public IntSupplier enrichAsScalaFromIntSupplier(final IntSupplier jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntSupplier$(this, jf);
   }

   public IntToDoubleFunction enrichAsScalaFromIntToDoubleFunction(final IntToDoubleFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntToDoubleFunction$(this, jf);
   }

   public IntToLongFunction enrichAsScalaFromIntToLongFunction(final IntToLongFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntToLongFunction$(this, jf);
   }

   public IntUnaryOperator enrichAsScalaFromIntUnaryOperator(final IntUnaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromIntUnaryOperator$(this, jf);
   }

   public LongBinaryOperator enrichAsScalaFromLongBinaryOperator(final LongBinaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongBinaryOperator$(this, jf);
   }

   public LongConsumer enrichAsScalaFromLongConsumer(final LongConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongConsumer$(this, jf);
   }

   public LongFunction enrichAsScalaFromLongFunction(final LongFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongFunction$(this, jf);
   }

   public LongPredicate enrichAsScalaFromLongPredicate(final LongPredicate jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongPredicate$(this, jf);
   }

   public LongSupplier enrichAsScalaFromLongSupplier(final LongSupplier jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongSupplier$(this, jf);
   }

   public LongToDoubleFunction enrichAsScalaFromLongToDoubleFunction(final LongToDoubleFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongToDoubleFunction$(this, jf);
   }

   public LongToIntFunction enrichAsScalaFromLongToIntFunction(final LongToIntFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongToIntFunction$(this, jf);
   }

   public LongUnaryOperator enrichAsScalaFromLongUnaryOperator(final LongUnaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromLongUnaryOperator$(this, jf);
   }

   public ObjDoubleConsumer enrichAsScalaFromObjDoubleConsumer(final ObjDoubleConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromObjDoubleConsumer$(this, jf);
   }

   public ObjIntConsumer enrichAsScalaFromObjIntConsumer(final ObjIntConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromObjIntConsumer$(this, jf);
   }

   public ObjLongConsumer enrichAsScalaFromObjLongConsumer(final ObjLongConsumer jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromObjLongConsumer$(this, jf);
   }

   public Predicate enrichAsScalaFromPredicate(final Predicate jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromPredicate$(this, jf);
   }

   public Supplier enrichAsScalaFromSupplier(final Supplier jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromSupplier$(this, jf);
   }

   public ToDoubleBiFunction enrichAsScalaFromToDoubleBiFunction(final ToDoubleBiFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToDoubleBiFunction$(this, jf);
   }

   public ToDoubleFunction enrichAsScalaFromToDoubleFunction(final ToDoubleFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToDoubleFunction$(this, jf);
   }

   public ToIntBiFunction enrichAsScalaFromToIntBiFunction(final ToIntBiFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToIntBiFunction$(this, jf);
   }

   public ToIntFunction enrichAsScalaFromToIntFunction(final ToIntFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToIntFunction$(this, jf);
   }

   public ToLongBiFunction enrichAsScalaFromToLongBiFunction(final ToLongBiFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToLongBiFunction$(this, jf);
   }

   public ToLongFunction enrichAsScalaFromToLongFunction(final ToLongFunction jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromToLongFunction$(this, jf);
   }

   public UnaryOperator enrichAsScalaFromUnaryOperator(final UnaryOperator jf) {
      return Priority0FunctionExtensions.enrichAsScalaFromUnaryOperator$(this, jf);
   }

   public Function2 enrichAsJavaBinaryOperator(final Function2 sf, final $eq$colon$eq evA1, final $eq$colon$eq evA2) {
      return Priority1FunctionExtensions.enrichAsJavaBinaryOperator$(this, sf, evA1, evA2);
   }

   public Function1 enrichAsJavaConsumer(final Function1 sf) {
      return Priority1FunctionExtensions.enrichAsJavaConsumer$(this, sf);
   }

   public Function1 enrichAsJavaDoubleFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority1FunctionExtensions.enrichAsJavaDoubleFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaIntFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority1FunctionExtensions.enrichAsJavaIntFunction$(this, sf, evA0);
   }

   public Function1 enrichAsJavaLongFunction(final Function1 sf, final $eq$colon$eq evA0) {
      return Priority1FunctionExtensions.enrichAsJavaLongFunction$(this, sf, evA0);
   }

   public Function2 enrichAsJavaObjDoubleConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return Priority1FunctionExtensions.enrichAsJavaObjDoubleConsumer$(this, sf, evA1);
   }

   public Function2 enrichAsJavaObjIntConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return Priority1FunctionExtensions.enrichAsJavaObjIntConsumer$(this, sf, evA1);
   }

   public Function2 enrichAsJavaObjLongConsumer(final Function2 sf, final $eq$colon$eq evA1) {
      return Priority1FunctionExtensions.enrichAsJavaObjLongConsumer$(this, sf, evA1);
   }

   public Function1 enrichAsJavaPredicate(final Function1 sf) {
      return Priority1FunctionExtensions.enrichAsJavaPredicate$(this, sf);
   }

   public Function0 enrichAsJavaSupplier(final Function0 sf) {
      return Priority1FunctionExtensions.enrichAsJavaSupplier$(this, sf);
   }

   public Function1 enrichAsJavaToDoubleFunction(final Function1 sf) {
      return Priority1FunctionExtensions.enrichAsJavaToDoubleFunction$(this, sf);
   }

   public Function1 enrichAsJavaToIntFunction(final Function1 sf) {
      return Priority1FunctionExtensions.enrichAsJavaToIntFunction$(this, sf);
   }

   public Function1 enrichAsJavaToLongFunction(final Function1 sf) {
      return Priority1FunctionExtensions.enrichAsJavaToLongFunction$(this, sf);
   }

   public Function1 enrichAsJavaUnaryOperator(final Function1 sf, final $eq$colon$eq evA1) {
      return Priority1FunctionExtensions.enrichAsJavaUnaryOperator$(this, sf, evA1);
   }

   public Function2 enrichAsJavaBiConsumer(final Function2 sf) {
      return Priority2FunctionExtensions.enrichAsJavaBiConsumer$(this, sf);
   }

   public Function2 enrichAsJavaBiPredicate(final Function2 sf) {
      return Priority2FunctionExtensions.enrichAsJavaBiPredicate$(this, sf);
   }

   public Function1 enrichAsJavaFunction(final Function1 sf) {
      return Priority2FunctionExtensions.enrichAsJavaFunction$(this, sf);
   }

   public Function2 enrichAsJavaToDoubleBiFunction(final Function2 sf) {
      return Priority2FunctionExtensions.enrichAsJavaToDoubleBiFunction$(this, sf);
   }

   public Function2 enrichAsJavaToIntBiFunction(final Function2 sf) {
      return Priority2FunctionExtensions.enrichAsJavaToIntBiFunction$(this, sf);
   }

   public Function2 enrichAsJavaToLongBiFunction(final Function2 sf) {
      return Priority2FunctionExtensions.enrichAsJavaToLongBiFunction$(this, sf);
   }

   public Function2 enrichAsJavaBiFunction(final Function2 sf) {
      return Priority3FunctionExtensions.enrichAsJavaBiFunction$(this, sf);
   }

   private FunctionConverters$() {
   }
}
