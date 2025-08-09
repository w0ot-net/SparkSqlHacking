package scala.collection.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.immutable.Seq;
import scala.collection.mutable.GrowableBuilder;
import scala.runtime.ModuleSerializationProxy;

public final class TrieMap$ implements MapFactory {
   public static final TrieMap$ MODULE$ = new TrieMap$();
   private static final long serialVersionUID = 3L;
   private static final transient AtomicReferenceFieldUpdater inodeupdater;

   static {
      TrieMap$ var10000 = MODULE$;
      inodeupdater = AtomicReferenceFieldUpdater.newUpdater(INodeBase.class, MainNode.class, "mainnode");
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   public TrieMap empty() {
      return new TrieMap();
   }

   public TrieMap from(final IterableOnce it) {
      return (TrieMap)(new TrieMap()).addAll(it);
   }

   public GrowableBuilder newBuilder() {
      return new GrowableBuilder(new TrieMap());
   }

   public AtomicReferenceFieldUpdater inodeupdater() {
      return inodeupdater;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TrieMap$.class);
   }

   private TrieMap$() {
   }
}
