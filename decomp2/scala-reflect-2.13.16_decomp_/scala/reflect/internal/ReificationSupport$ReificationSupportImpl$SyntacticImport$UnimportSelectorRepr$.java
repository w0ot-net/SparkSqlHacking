package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.internal.util.Position;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$UnimportSelectorRepr$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.Tree apply(final Names.TermName name, final Position pos) {
      return this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().atPos((Position)pos, (Trees.Tree)this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$Arrow().apply(this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$NameSelectorRepr().apply(name, pos), this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$WildcardSelectorRepr().apply(pos)));
   }

   public Option unapply(final Trees.Tree tree) {
      if (tree instanceof Trees.Apply) {
         Trees.Apply var2 = (Trees.Apply)tree;
         Option var3 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$Arrow().unapply(var2);
         if (!var3.isEmpty()) {
            Trees.Tree var4 = (Trees.Tree)((Tuple2)var3.get())._1();
            Trees.Tree var5 = (Trees.Tree)((Tuple2)var3.get())._2();
            if (var4 != null) {
               Option var6 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$NameSelectorRepr().unapply(var4);
               if (!var6.isEmpty()) {
                  Names.TermName name = (Names.TermName)((Tuple2)var6.get())._1();
                  Position pos = (Position)((Tuple2)var6.get())._2();
                  if (var5 != null && !this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$WildcardSelectorRepr().unapply(var5).isEmpty()) {
                     return new Some(new Tuple2(name, pos));
                  }
               }
            }
         }
      }

      return .MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$UnimportSelectorRepr$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
