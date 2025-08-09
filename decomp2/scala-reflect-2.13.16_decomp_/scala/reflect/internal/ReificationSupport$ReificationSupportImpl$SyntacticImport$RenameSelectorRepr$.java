package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.internal.util.Position;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$RenameSelectorRepr$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.Tree apply(final Names.TermName name1, final Position pos1, final Names.TermName name2, final Position pos2) {
      Trees.Tree left = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$NameSelectorRepr().apply(name1, pos1);
      Trees.Tree right = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$NameSelectorRepr().apply(name2, pos2);
      SymbolTable var10000 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer();
      SymbolTable var10001 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer();
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10002 = new scala.collection.immutable..colon.colon(right, $colon$colon_this);
      $colon$colon_this = null;
      List $colon$colon_this = var10002;
      var10002 = new scala.collection.immutable..colon.colon(left, $colon$colon_this);
      $colon$colon_this = null;
      return var10000.atPos((Position)var10001.wrappingPos(var10002), (Trees.Tree)this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$Arrow().apply(left, right));
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
                  Names.TermName name1 = (Names.TermName)((Tuple2)var6.get())._1();
                  Position pos1 = (Position)((Tuple2)var6.get())._2();
                  if (var5 != null) {
                     Option var9 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$NameSelectorRepr().unapply(var5);
                     if (!var9.isEmpty()) {
                        Names.TermName name2 = (Names.TermName)((Tuple2)var9.get())._1();
                        Position pos2 = (Position)((Tuple2)var9.get())._2();
                        Some var10000 = new Some;
                        Tuple4 var10002 = new Tuple4;
                        if (name1 == null) {
                           throw null;
                        }

                        if (name2 == null) {
                           throw null;
                        }

                        var10002.<init>(name1, pos1, name2, pos2);
                        var10000.<init>(var10002);
                        return var10000;
                     }
                  }
               }
            }
         }
      }

      return scala.None..MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$RenameSelectorRepr$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
