package spire.util;

import scala.Option;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.api.Constants;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.TreeCreator;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxesRunTime;

public final class PackMacros$ {
   public static final PackMacros$ MODULE$ = new PackMacros$();

   public Exprs.Expr intToByteMacro(final Context c, final Exprs.Expr n, final Exprs.Expr index) {
      Trees.TreeApi var7 = index.tree();
      Exprs.Expr var4;
      if (var7 != null) {
         Option var8 = c.universe().LiteralTag().unapply(var7);
         if (!var8.isEmpty()) {
            Trees.LiteralApi var9 = (Trees.LiteralApi)var8.get();
            if (var9 != null) {
               Option var10 = c.universe().Literal().unapply(var9);
               if (!var10.isEmpty()) {
                  Constants.ConstantApi var11 = (Constants.ConstantApi)var10.get();
                  if (var11 != null) {
                     Option var12 = c.universe().ConstantTag().unapply(var11);
                     if (!var12.isEmpty()) {
                        Constants.ConstantApi var13 = (Constants.ConstantApi)var12.get();
                        if (var13 != null) {
                           Option var14 = c.universe().Constant().unapply(var13);
                           if (!var14.isEmpty()) {
                              Object i = var14.get();
                              if (i instanceof Integer) {
                                 int var16 = BoxesRunTime.unboxToInt(i);
                                 if (0 > var16 || var16 >= 4) {
                                    throw c.abort(c.enclosingPosition(), "index outside of 0-3");
                                 }

                                 Exprs.Expr offset = c.Expr(c.universe().Literal().apply(c.universe().Constant().apply(BoxesRunTime.boxToInteger(24 - var16 * 8))), c.universe().WeakTypeTag().Int());
                                 Universe $u = c.universe();
                                 Mirror $m = c.universe().rootMirror();

                                 final class $treecreator1$1 extends TreeCreator {
                                    private final Exprs.Expr n$1$1;
                                    private final Exprs.Expr offset$1;

                                    public Trees.TreeApi apply(final Mirror $m$untyped) {
                                       scala.reflect.api.Universe $u = $m$untyped.universe();
                                       return $u.Select().apply($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply(this.n$1$1.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("$greater$greater$greater")), (List)(new .colon.colon(this.offset$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$amp")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply(BoxesRunTime.boxToInteger(255))), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("toByte"));
                                    }

                                    public $treecreator1$1(final Exprs.Expr n$1$1, final Exprs.Expr offset$1) {
                                       this.n$1$1 = n$1$1;
                                       this.offset$1 = offset$1;
                                    }
                                 }


                                 final class $typecreator2$1 extends TypeCreator {
                                    public Types.TypeApi apply(final Mirror $m$untyped) {
                                       scala.reflect.api.Universe $u = $m$untyped.universe();
                                       return $m$untyped.staticClass("scala.Byte").asType().toTypeConstructor();
                                    }

                                    public $typecreator2$1() {
                                    }
                                 }

                                 var4 = $u.Expr().apply($m, new $treecreator1$1(n, offset), $u.TypeTag().apply($m, new $typecreator2$1()));
                                 return var4;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $treecreator2$1 extends TreeCreator {
         private final Exprs.Expr n$1$1;
         private final Exprs.Expr index$1$1;

         public Trees.TreeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.Apply().apply($u.Apply().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("spire.util.Pack")), (Names.NameApi)$u.TermName().apply("intToByteRuntime")), (List)(new .colon.colon(this.n$1$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (List)(new .colon.colon(this.index$1$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $treecreator2$1(final Exprs.Expr n$1$1, final Exprs.Expr index$1$1) {
            this.n$1$1 = n$1$1;
            this.index$1$1 = index$1$1;
         }
      }


      final class $typecreator4$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("scala.Byte").asType().toTypeConstructor();
         }

         public $typecreator4$1() {
         }
      }

      var4 = $u.Expr().apply($m, new $treecreator2$1(n, index), $u.TypeTag().apply($m, new $typecreator4$1()));
      return var4;
   }

   public Exprs.Expr longToByteMacro(final Context c, final Exprs.Expr n, final Exprs.Expr index) {
      Trees.TreeApi var7 = index.tree();
      Exprs.Expr var4;
      if (var7 != null) {
         Option var8 = c.universe().LiteralTag().unapply(var7);
         if (!var8.isEmpty()) {
            Trees.LiteralApi var9 = (Trees.LiteralApi)var8.get();
            if (var9 != null) {
               Option var10 = c.universe().Literal().unapply(var9);
               if (!var10.isEmpty()) {
                  Constants.ConstantApi var11 = (Constants.ConstantApi)var10.get();
                  if (var11 != null) {
                     Option var12 = c.universe().ConstantTag().unapply(var11);
                     if (!var12.isEmpty()) {
                        Constants.ConstantApi var13 = (Constants.ConstantApi)var12.get();
                        if (var13 != null) {
                           Option var14 = c.universe().Constant().unapply(var13);
                           if (!var14.isEmpty()) {
                              Object i = var14.get();
                              if (i instanceof Integer) {
                                 int var16 = BoxesRunTime.unboxToInt(i);
                                 if (0 > var16 || var16 >= 8) {
                                    throw c.abort(c.enclosingPosition(), "index outside of 0-7");
                                 }

                                 Exprs.Expr offset = c.Expr(c.universe().Literal().apply(c.universe().Constant().apply(BoxesRunTime.boxToInteger(56 - var16 * 8))), c.universe().WeakTypeTag().Int());
                                 Universe $u = c.universe();
                                 Mirror $m = c.universe().rootMirror();

                                 final class $treecreator1$2 extends TreeCreator {
                                    private final Exprs.Expr n$2$1;
                                    private final Exprs.Expr offset$2;

                                    public Trees.TreeApi apply(final Mirror $m$untyped) {
                                       scala.reflect.api.Universe $u = $m$untyped.universe();
                                       return $u.Select().apply($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply(this.n$2$1.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("$greater$greater$greater")), (List)(new .colon.colon(this.offset$2.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$amp")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply(BoxesRunTime.boxToInteger(255))), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("toByte"));
                                    }

                                    public $treecreator1$2(final Exprs.Expr n$2$1, final Exprs.Expr offset$2) {
                                       this.n$2$1 = n$2$1;
                                       this.offset$2 = offset$2;
                                    }
                                 }


                                 final class $typecreator2$2 extends TypeCreator {
                                    public Types.TypeApi apply(final Mirror $m$untyped) {
                                       scala.reflect.api.Universe $u = $m$untyped.universe();
                                       return $m$untyped.staticClass("scala.Byte").asType().toTypeConstructor();
                                    }

                                    public $typecreator2$2() {
                                    }
                                 }

                                 var4 = $u.Expr().apply($m, new $treecreator1$2(n, offset), $u.TypeTag().apply($m, new $typecreator2$2()));
                                 return var4;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $treecreator2$2 extends TreeCreator {
         private final Exprs.Expr n$2$1;
         private final Exprs.Expr index$2$1;

         public Trees.TreeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.Apply().apply($u.Apply().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("spire.util.Pack")), (Names.NameApi)$u.TermName().apply("longToByteRuntime")), (List)(new .colon.colon(this.n$2$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (List)(new .colon.colon(this.index$2$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $treecreator2$2(final Exprs.Expr n$2$1, final Exprs.Expr index$2$1) {
            this.n$2$1 = n$2$1;
            this.index$2$1 = index$2$1;
         }
      }


      final class $typecreator4$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("scala.Byte").asType().toTypeConstructor();
         }

         public $typecreator4$2() {
         }
      }

      var4 = $u.Expr().apply($m, new $treecreator2$2(n, index), $u.TypeTag().apply($m, new $typecreator4$2()));
      return var4;
   }

   private PackMacros$() {
   }
}
