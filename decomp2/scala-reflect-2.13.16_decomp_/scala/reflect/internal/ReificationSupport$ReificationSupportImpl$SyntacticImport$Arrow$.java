package scala.reflect.internal;

import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;

public class ReificationSupport$ReificationSupportImpl$SyntacticImport$Arrow$ {
   // $FF: synthetic field
   private final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer;

   public Trees.Apply apply(final Trees.Tree left, final Trees.Tree right) {
      SymbolTable var10002 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer();
      Trees.Ident var10003 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().new Ident(this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().MINGT());
      List $colon$colon_this = .MODULE$;
      scala.collection.immutable..colon.colon var10004 = new scala.collection.immutable..colon.colon(right, $colon$colon_this);
      $colon$colon_this = null;
      List $colon$colon_this = var10004;
      var10004 = new scala.collection.immutable..colon.colon(left, $colon$colon_this);
      $colon$colon_this = null;
      return var10002.new Apply(var10003, var10004);
   }

   public Option unapply(final Trees.Apply tree) {
      if (tree != null) {
         Trees.Tree var2 = tree.fun();
         List var3 = tree.args();
         if (var2 instanceof Trees.Ident) {
            Names.Name var4 = ((Trees.Ident)var2).name();
            Names.TermName var10000 = this.$outer.scala$reflect$internal$ReificationSupport$ReificationSupportImpl$SyntacticImport$$$outer().scala$reflect$internal$ReificationSupport$ReificationSupportImpl$$$outer().nme().MINGT();
            if (var10000 == null) {
               if (var4 != null) {
                  return scala.None..MODULE$;
               }
            } else if (!var10000.equals(var4)) {
               return scala.None..MODULE$;
            }

            if (var3 instanceof scala.collection.immutable..colon.colon) {
               scala.collection.immutable..colon.colon var5 = (scala.collection.immutable..colon.colon)var3;
               Trees.Tree left = (Trees.Tree)var5.head();
               List var7 = var5.next$access$1();
               if (var7 instanceof scala.collection.immutable..colon.colon) {
                  scala.collection.immutable..colon.colon var8 = (scala.collection.immutable..colon.colon)var7;
                  Trees.Tree right = (Trees.Tree)var8.head();
                  List var10 = var8.next$access$1();
                  if (.MODULE$.equals(var10)) {
                     return new Some(new Tuple2(left, right));
                  }
               }
            }
         }
      }

      return scala.None..MODULE$;
   }

   public ReificationSupport$ReificationSupportImpl$SyntacticImport$Arrow$(final ReificationSupport.ReificationSupportImpl.SyntacticImport$ $outer) {
      if ($outer == null) {
         throw null;
      } else {
         this.$outer = $outer;
         super();
      }
   }
}
