package scala.collection.immutable;

import scala.collection.StringOps$;
import scala.collection.StringView$;

public final class package$ {
   public static final package$ MODULE$ = new package$();
   private static final StringOps$ StringOps;
   private static final StringView$ StringView;
   /** @deprecated */
   private static final Iterable$ Traversable;

   static {
      StringOps = StringOps$.MODULE$;
      StringView = StringView$.MODULE$;
      Traversable = Iterable$.MODULE$;
   }

   public StringOps$ StringOps() {
      return StringOps;
   }

   public StringView$ StringView() {
      return StringView;
   }

   /** @deprecated */
   public Iterable$ Traversable() {
      return Traversable;
   }

   private package$() {
   }
}
