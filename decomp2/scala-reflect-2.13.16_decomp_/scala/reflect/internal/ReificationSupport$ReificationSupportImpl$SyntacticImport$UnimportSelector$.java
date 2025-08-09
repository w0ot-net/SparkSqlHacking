package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$UnimportSelector$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.ImportSelector apply(final Names.TermName name, final int offset) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new ImportSelector(name, offset, this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().WILDCARD(), -1);
   }

   public Option unapply(final Trees.ImportSelector sel) {
      return (Option)(sel.isMask() ? new Some(new Tuple2(sel.name().toTermName(), sel.namePos())) : .MODULE$);
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$UnimportSelector$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
