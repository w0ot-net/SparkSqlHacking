package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$RenameSelector$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.ImportSelector apply(final Names.TermName name1, final int offset1, final Names.TermName name2, final int offset2) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new ImportSelector(name1, offset1, name2, offset2);
   }

   public Option unapply(final Trees.ImportSelector sel) {
      if (sel.isRename()) {
         Names.Name name1 = sel.name();
         int offset1 = sel.namePos();
         Names.Name name2 = sel.rename();
         int offset2 = sel.renamePos();
         return new Some(new Tuple4(name1.toTermName(), offset1, name2.toTermName(), offset2));
      } else {
         return .MODULE$;
      }
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$RenameSelector$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
