package scala.collection;

import scala.reflect.ClassTag;

public final class Factory$ {
   public static final Factory$ MODULE$ = new Factory$();
   private static final Factory stringFactory = new Factory.StringFactory();

   public Factory stringFactory() {
      return stringFactory;
   }

   public Factory arrayFactory(final ClassTag evidence$1) {
      return new Factory.ArrayFactory(evidence$1);
   }

   private Factory$() {
   }
}
