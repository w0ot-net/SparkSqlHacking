package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.internal.util.Position;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$WildcardSelectorRepr$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.Tree apply(final Position pos) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().atPos((Position)pos, (Trees.Tree)(this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new Ident(this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().WILDCARD())));
   }

   public Option unapply(final Trees.Tree tree) {
      if (tree instanceof Trees.Ident) {
         Names.Name var2 = ((Trees.Ident)tree).name();
         Names.Name var10000 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().WILDCARD();
         if (var10000 == null) {
            if (var2 == null) {
               return new Some(tree.pos());
            }
         } else if (var10000.equals(var2)) {
            return new Some(tree.pos());
         }
      }

      return .MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$WildcardSelectorRepr$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
