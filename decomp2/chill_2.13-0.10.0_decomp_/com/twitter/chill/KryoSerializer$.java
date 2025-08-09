package com.twitter.chill;

public final class KryoSerializer$ {
   public static final KryoSerializer$ MODULE$ = new KryoSerializer$();

   public KryoInstantiator empty() {
      return new EmptyScalaKryoInstantiator();
   }

   public KryoInstantiator registered() {
      return new ScalaKryoInstantiator();
   }

   public IKryoRegistrar registerCollectionSerializers() {
      return new ScalaCollectionsRegistrar();
   }

   public IKryoRegistrar registerAll() {
      return new AllScalaRegistrar();
   }

   private KryoSerializer$() {
   }
}
