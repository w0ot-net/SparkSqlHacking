package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.internal.util.Position;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$NameSelectorRepr$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.Tree apply(final Names.TermName name, final Position pos) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().atPos((Position)pos, (Trees.Tree)(this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new Bind(name, this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$WildcardSelectorRepr().apply(pos))));
   }

   public Option unapply(final Trees.Tree tree) {
      if (tree instanceof Trees.Bind) {
         Trees.Bind var2 = (Trees.Bind)tree;
         Names.Name name = var2.name();
         Trees.Tree var4 = var2.body();
         if (var4 != null && !this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$WildcardSelectorRepr().unapply(var4).isEmpty()) {
            return new Some(new Tuple2(name.toTermName(), tree.pos()));
         }
      }

      return .MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$NameSelectorRepr$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
