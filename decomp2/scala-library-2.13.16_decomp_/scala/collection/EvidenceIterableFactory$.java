package scala.collection;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class EvidenceIterableFactory$ implements Serializable {
   public static final EvidenceIterableFactory$ MODULE$ = new EvidenceIterableFactory$();

   public Factory toFactory(final EvidenceIterableFactory factory, final Object evidence$14) {
      return new EvidenceIterableFactory.ToFactory(factory, evidence$14);
   }

   public BuildFrom toBuildFrom(final EvidenceIterableFactory factory, final Object evidence$16) {
      return new EvidenceIterableFactory.EvidenceIterableFactoryToBuildFrom(factory, evidence$16);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EvidenceIterableFactory$.class);
   }

   private EvidenceIterableFactory$() {
   }
}
