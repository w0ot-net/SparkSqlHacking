package scala.collection.immutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.ReusableBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class HashMap$ implements MapFactory {
   public static final HashMap$ MODULE$ = new HashMap$();
   private static final long serialVersionUID = 3L;
   private static final transient HashMap EmptyMap;

   static {
      HashMap$ var10000 = MODULE$;
      EmptyMap = new HashMap(MapNode$.MODULE$.empty());
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   private final HashMap EmptyMap() {
      return EmptyMap;
   }

   public HashMap empty() {
      return this.EmptyMap();
   }

   public HashMap from(final IterableOnce source) {
      return source instanceof HashMap ? (HashMap)source : (HashMap)(new HashMapBuilder()).addAll(source).result();
   }

   public ReusableBuilder newBuilder() {
      return new HashMapBuilder();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HashMap$.class);
   }

   private HashMap$() {
   }
}
