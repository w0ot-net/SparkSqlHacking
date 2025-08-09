package scala.collection.immutable;

import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.MapView;
import scala.collection.mutable.LinkedHashMap;
import scala.collection.mutable.ReusableBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class ListMap$ implements MapFactory {
   public static final ListMap$ MODULE$ = new ListMap$();
   private static final long serialVersionUID = 3L;

   static {
      ListMap$ var10000 = MODULE$;
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public ListMap empty() {
      return ListMap.EmptyListMap$.MODULE$;
   }

   public ListMap from(final IterableOnce it) {
      if (it instanceof ListMap) {
         return (ListMap)it;
      } else if (!(it instanceof LinkedHashMap)) {
         if (it instanceof scala.collection.Map ? true : it instanceof MapView) {
            ListMap current = ListMap.EmptyListMap$.MODULE$;

            Object k;
            Object v;
            for(Iterator iter = it.iterator(); iter.hasNext(); current = new ListMap.Node(k, v, current)) {
               Tuple2 var7 = (Tuple2)iter.next();
               if (var7 == null) {
                  throw new MatchError((Object)null);
               }

               k = var7._1();
               v = var7._2();
            }

            return current;
         } else {
            return (ListMap)(new ListMapBuilder()).addAll(it).result();
         }
      } else {
         LinkedHashMap var2 = (LinkedHashMap)it;
         ListMap current = ListMap.EmptyListMap$.MODULE$;

         for(LinkedHashMap.LinkedEntry firstEntry = var2._firstEntry(); firstEntry != null; firstEntry = firstEntry.later()) {
            current = new ListMap.Node(firstEntry.key(), firstEntry.value(), current);
         }

         return current;
      }
   }

   public ReusableBuilder newBuilder() {
      return new ListMapBuilder();
   }

   public Object scala$collection$immutable$ListMap$$foldRightInternal(final ListMap map, final Object prevValue, final Function2 op) {
      while(!map.isEmpty()) {
         ListMap var10000 = (ListMap)map.init();
         Object var10001 = op.apply(map.last(), prevValue);
         op = op;
         prevValue = var10001;
         map = var10000;
      }

      return prevValue;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ListMap$.class);
   }

   private ListMap$() {
   }
}
