package org.apache.commons.collections;

import java.util.Collection;
import java.util.Map;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.CloneTransformer;
import org.apache.commons.collections.functors.ClosureTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.functors.ExceptionTransformer;
import org.apache.commons.collections.functors.FactoryTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.functors.MapTransformer;
import org.apache.commons.collections.functors.NOPTransformer;
import org.apache.commons.collections.functors.PredicateTransformer;
import org.apache.commons.collections.functors.StringValueTransformer;
import org.apache.commons.collections.functors.SwitchTransformer;

public class TransformerUtils {
   public static Transformer exceptionTransformer() {
      return ExceptionTransformer.INSTANCE;
   }

   public static Transformer nullTransformer() {
      return ConstantTransformer.NULL_INSTANCE;
   }

   public static Transformer nopTransformer() {
      return NOPTransformer.INSTANCE;
   }

   public static Transformer cloneTransformer() {
      return CloneTransformer.INSTANCE;
   }

   public static Transformer constantTransformer(Object constantToReturn) {
      return ConstantTransformer.getInstance(constantToReturn);
   }

   public static Transformer asTransformer(Closure closure) {
      return ClosureTransformer.getInstance(closure);
   }

   public static Transformer asTransformer(Predicate predicate) {
      return PredicateTransformer.getInstance(predicate);
   }

   public static Transformer asTransformer(Factory factory) {
      return FactoryTransformer.getInstance(factory);
   }

   public static Transformer chainedTransformer(Transformer transformer1, Transformer transformer2) {
      return ChainedTransformer.getInstance(transformer1, transformer2);
   }

   public static Transformer chainedTransformer(Transformer[] transformers) {
      return ChainedTransformer.getInstance(transformers);
   }

   public static Transformer chainedTransformer(Collection transformers) {
      return ChainedTransformer.getInstance(transformers);
   }

   public static Transformer switchTransformer(Predicate predicate, Transformer trueTransformer, Transformer falseTransformer) {
      return SwitchTransformer.getInstance(new Predicate[]{predicate}, new Transformer[]{trueTransformer}, falseTransformer);
   }

   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers) {
      return SwitchTransformer.getInstance(predicates, transformers, (Transformer)null);
   }

   public static Transformer switchTransformer(Predicate[] predicates, Transformer[] transformers, Transformer defaultTransformer) {
      return SwitchTransformer.getInstance(predicates, transformers, defaultTransformer);
   }

   public static Transformer switchTransformer(Map predicatesAndTransformers) {
      return SwitchTransformer.getInstance(predicatesAndTransformers);
   }

   public static Transformer switchMapTransformer(Map objectsAndTransformers) {
      Transformer[] trs = null;
      Predicate[] preds = null;
      if (objectsAndTransformers == null) {
         throw new IllegalArgumentException("The object and transformer map must not be null");
      } else {
         Transformer def = (Transformer)objectsAndTransformers.remove((Object)null);
         int size = objectsAndTransformers.size();
         trs = new Transformer[size];
         preds = new Predicate[size];
         int i = 0;

         for(Map.Entry entry : objectsAndTransformers.entrySet()) {
            preds[i] = EqualPredicate.getInstance(entry.getKey());
            trs[i] = (Transformer)entry.getValue();
            ++i;
         }

         return switchTransformer(preds, trs, def);
      }
   }

   public static Transformer instantiateTransformer() {
      return InstantiateTransformer.NO_ARG_INSTANCE;
   }

   public static Transformer instantiateTransformer(Class[] paramTypes, Object[] args) {
      return InstantiateTransformer.getInstance(paramTypes, args);
   }

   public static Transformer mapTransformer(Map map) {
      return MapTransformer.getInstance(map);
   }

   public static Transformer invokerTransformer(String methodName) {
      return InvokerTransformer.getInstance(methodName, (Class[])null, (Object[])null);
   }

   public static Transformer invokerTransformer(String methodName, Class[] paramTypes, Object[] args) {
      return InvokerTransformer.getInstance(methodName, paramTypes, args);
   }

   public static Transformer stringValueTransformer() {
      return StringValueTransformer.INSTANCE;
   }
}
