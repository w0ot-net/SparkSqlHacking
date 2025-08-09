package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$NameSelector$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.ImportSelector apply(final Names.TermName name, final int offset) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new ImportSelector(name, offset, name, offset);
   }

   public Option unapply(final Trees.ImportSelector sel) {
      if (sel != null) {
         Names.Name name1 = sel.name();
         int offset1 = sel.namePos();
         Names.Name name2 = sel.rename();
         int offset2 = sel.renamePos();
         if (name1 == null) {
            if (name2 != null) {
               return .MODULE$;
            }
         } else if (!name1.equals(name2)) {
            return .MODULE$;
         }

         if (offset1 == offset2) {
            return new Some(new Tuple2(name1.toTermName(), offset1));
         }
      }

      return .MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$NameSelector$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
