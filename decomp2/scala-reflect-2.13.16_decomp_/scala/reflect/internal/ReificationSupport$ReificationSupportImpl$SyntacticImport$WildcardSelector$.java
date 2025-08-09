package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.None.;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$WildcardSelector$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.ImportSelector apply(final int offset) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().ImportSelector().wildAt(offset);
   }

   public Option unapply(final Trees.ImportSelector sel) {
      return (Option)(sel.isWildcard() ? new Some(sel.namePos()) : .MODULE$);
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$WildcardSelector$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
