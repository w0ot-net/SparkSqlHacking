package scala.collection.compat;

import scala.collection.BuildFrom;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Factory.;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final Factory Factory;
   private static final BuildFrom BuildFrom;
   private static final IterableOnce IterableOnce;

   static {
      Factory = .MODULE$;
      BuildFrom = scala.collection.BuildFrom..MODULE$;
      IterableOnce = scala.collection.IterableOnce..MODULE$;
   }

   public Factory Factory() {
      return Factory;
   }

   public BuildFrom BuildFrom() {
      return BuildFrom;
   }

   public IterableOnce IterableOnce() {
      return IterableOnce;
   }

   private package$() {
   }
}
