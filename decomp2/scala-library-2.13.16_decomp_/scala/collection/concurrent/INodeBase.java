package scala.collection.concurrent;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

abstract class INodeBase extends BasicNode {
   public static final AtomicReferenceFieldUpdater updater = AtomicReferenceFieldUpdater.newUpdater(INodeBase.class, MainNode.class, "mainnode");
   static final Object RESTART = new Object();
   static final Object NO_SUCH_ELEMENT_SENTINEL = new Object();
   public volatile MainNode mainnode = null;
   public final Gen gen;

   public INodeBase(Gen generation) {
      this.gen = generation;
   }

   public BasicNode prev() {
      return null;
   }
}
