package breeze.macros;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
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
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;
import scala.runtime.ScalaRunTime;

public final class AssertImpl$ {
   public static final AssertImpl$ MODULE$ = new AssertImpl$();

   public Exprs.Expr assertImpl(final Context c, final Exprs.Expr condition) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$1 extends TreeCreator {
            private final Exprs.Expr condExpr$1;
            private final Exprs.Expr messageExpr$1;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$1.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticClass("java.lang.AssertionError"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("assertion failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$1(final Exprs.Expr condExpr$1, final Exprs.Expr messageExpr$1) {
               this.condExpr$1 = condExpr$1;
               this.messageExpr$1 = messageExpr$1;
            }
         }


         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$1(condExpr, messageExpr), $u.TypeTag().apply($m, new $typecreator2$1()));
      });
   }

   public Exprs.Expr assertMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$2 extends TreeCreator {
            private final Exprs.Expr condExpr$2;
            private final Exprs.Expr message$1$1;
            private final Exprs.Expr messageExpr$2;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$2.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticClass("java.lang.AssertionError"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("assertion failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.message$1$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply(": ")), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$2.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$2(final Exprs.Expr condExpr$2, final Exprs.Expr message$1$1, final Exprs.Expr messageExpr$2) {
               this.condExpr$2 = condExpr$2;
               this.message$1$1 = message$1$1;
               this.messageExpr$2 = messageExpr$2;
            }
         }


         final class $typecreator2$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$2() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$2(condExpr, message, messageExpr), $u.TypeTag().apply($m, new $typecreator2$2()));
      });
   }

   public Exprs.Expr requireImpl(final Context c, final Exprs.Expr condition) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$3 extends TreeCreator {
            private final Exprs.Expr condExpr$3;
            private final Exprs.Expr messageExpr$3;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$3.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("scala.package")), (Names.NameApi)$u.TypeName().apply("IllegalArgumentException"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("requirement failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$3.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$3(final Exprs.Expr condExpr$3, final Exprs.Expr messageExpr$3) {
               this.condExpr$3 = condExpr$3;
               this.messageExpr$3 = messageExpr$3;
            }
         }


         final class $typecreator2$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$3() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$3(condExpr, messageExpr), $u.TypeTag().apply($m, new $typecreator2$3()));
      });
   }

   public Exprs.Expr requireMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$4 extends TreeCreator {
            private final Exprs.Expr condExpr$4;
            private final Exprs.Expr message$2$1;
            private final Exprs.Expr messageExpr$4;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$4.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("scala.package")), (Names.NameApi)$u.TypeName().apply("IllegalArgumentException"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("requirement failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.message$2$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply(": ")), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$4.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$4(final Exprs.Expr condExpr$4, final Exprs.Expr message$2$1, final Exprs.Expr messageExpr$4) {
               this.condExpr$4 = condExpr$4;
               this.message$2$1 = message$2$1;
               this.messageExpr$4 = messageExpr$4;
            }
         }


         final class $typecreator2$4 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$4() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$4(condExpr, message, messageExpr), $u.TypeTag().apply($m, new $typecreator2$4()));
      });
   }

   public Exprs.Expr assumeImpl(final Context c, final Exprs.Expr condition) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$5 extends TreeCreator {
            private final Exprs.Expr condExpr$5;
            private final Exprs.Expr messageExpr$5;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$5.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticClass("java.lang.AssertionError"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("assumption failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$5.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$5(final Exprs.Expr condExpr$5, final Exprs.Expr messageExpr$5) {
               this.condExpr$5 = condExpr$5;
               this.messageExpr$5 = messageExpr$5;
            }
         }


         final class $typecreator2$5 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$5() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$5(condExpr, messageExpr), $u.TypeTag().apply($m, new $typecreator2$5()));
      });
   }

   public Exprs.Expr assumeMsgImpl(final Context c, final Exprs.Expr condition, final Exprs.Expr message) {
      return this.assertLikeImpl(c, condition, (condExpr, messageExpr) -> {
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $treecreator1$6 extends TreeCreator {
            private final Exprs.Expr condExpr$6;
            private final Exprs.Expr message$3$1;
            private final Exprs.Expr messageExpr$6;

            public Trees.TreeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.If().apply($u.Select().apply(this.condExpr$6.in($m$untyped).tree(), (Names.NameApi)$u.TermName().apply("unary_$bang")), $u.Throw().apply($u.Apply().apply($u.Select().apply($u.New().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticClass("java.lang.AssertionError"))), (Names.NameApi)$u.TermName().apply("<init>")), (List)(new .colon.colon($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.Literal().apply($u.Constant().apply("assumption failed: ")), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.message$3$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply(": ")), scala.collection.immutable.Nil..MODULE$))), (Names.NameApi)$u.TermName().apply("$plus")), (List)(new .colon.colon(this.messageExpr$6.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), $u.Literal().apply($u.Constant().apply(BoxedUnit.UNIT)));
            }

            public $treecreator1$6(final Exprs.Expr condExpr$6, final Exprs.Expr message$3$1, final Exprs.Expr messageExpr$6) {
               this.condExpr$6 = condExpr$6;
               this.message$3$1 = message$3$1;
               this.messageExpr$6 = messageExpr$6;
            }
         }


         final class $typecreator2$6 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("scala.Unit").asType().toTypeConstructor();
            }

            public $typecreator2$6() {
            }
         }

         return $u.Expr().apply($m, new $treecreator1$6(condExpr, message, messageExpr), $u.TypeTag().apply($m, new $typecreator2$6()));
      });
   }

   public Exprs.Expr assertLikeImpl(final Context c, final Exprs.Expr condition, final Function2 callBuilder) {
      LazyRef EqualityOpName$module = new LazyRef();
      Trees.TreeApi typedCondition = c.typeCheck(condition.tree(), c.typeCheck$default$2(), c.typeCheck$default$3(), c.typeCheck$default$4(), c.typeCheck$default$5());
      Trees.BlockApi var4;
      if (typedCondition != null) {
         Option var11 = c.universe().ApplyTag().unapply(typedCondition);
         if (!var11.isEmpty()) {
            Trees.ApplyApi var12 = (Trees.ApplyApi)var11.get();
            if (var12 != null) {
               Option var13 = c.universe().Apply().unapply(var12);
               if (!var13.isEmpty()) {
                  Trees.TreeApi var14 = (Trees.TreeApi)((Tuple2)var13.get())._1();
                  List var15 = (List)((Tuple2)var13.get())._2();
                  if (var14 != null) {
                     Option var16 = c.universe().SelectTag().unapply(var14);
                     if (!var16.isEmpty()) {
                        Trees.SelectApi var17 = (Trees.SelectApi)var16.get();
                        if (var17 != null) {
                           Option var18 = c.universe().Select().unapply(var17);
                           if (!var18.isEmpty()) {
                              Trees.TreeApi left = (Trees.TreeApi)((Tuple2)var18.get())._1();
                              Names.NameApi op = (Names.NameApi)((Tuple2)var18.get())._2();
                              if (op != null) {
                                 Option var21 = c.universe().NameTag().unapply(op);
                                 if (!var21.isEmpty()) {
                                    Names.NameApi var22 = (Names.NameApi)var21.get();
                                    if (var22 != null) {
                                       Option var23 = this.EqualityOpName$2(EqualityOpName$module).unapply(var22);
                                       if (!var23.isEmpty()) {
                                          boolean isEqual = BoxesRunTime.unboxToBoolean(var23.get());
                                          if (var15 != null) {
                                             SeqOps var25 = scala.package..MODULE$.List().unapplySeq(var15);
                                             if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var25) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var25)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var25), 1) == 0) {
                                                Trees.TreeApi right = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var25), 0);
                                                Trees.ValDefApi leftDef = newValDef$1("left", left, newValDef$default$3$1(), c);
                                                Trees.ValDefApi rightDef = newValDef$1("right", right, newValDef$default$3$1(), c);
                                                Exprs.Expr leftExpr = c.Expr(c.universe().Ident().apply((Names.NameApi)leftDef.name()), c.universe().WeakTypeTag().Any());
                                                Exprs.Expr rightExpr = c.Expr(c.universe().Ident().apply((Names.NameApi)rightDef.name()), c.universe().WeakTypeTag().Any());
                                                Tuple2 rels = new Tuple2("==", "!=");
                                                Tuple2 var33 = isEqual ? rels : rels.swap();
                                                if (var33 == null) {
                                                   throw new MatchError(var33);
                                                }

                                                String expectedRel = (String)var33._1();
                                                String actualRel = (String)var33._2();
                                                Tuple2 var5 = new Tuple2(expectedRel, actualRel);
                                                String expectedRel = (String)var5._1();
                                                String actualRel = (String)var5._2();
                                                Trees.TreeApi actualRelExpr = c.universe().Liftable().liftString().apply(actualRel);
                                                Exprs.Expr str = c.literal((new StringBuilder(2)).append(left).append(" ").append(expectedRel).append(" ").append(right).toString());
                                                Exprs.Expr condExpr = c.Expr(c.universe().Apply().apply(c.universe().Select().apply(c.universe().Ident().apply((Names.NameApi)leftDef.name()), op), (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().Ident().apply((Names.NameApi)rightDef.name())})))), c.universe().WeakTypeTag().Boolean());
                                                Universe var10001 = c.universe();
                                                ScalaRunTime var48 = scala.runtime.ScalaRunTime..MODULE$;
                                                Trees.TreeApi[] var10003 = new Trees.TreeApi[]{leftDef, rightDef, null};
                                                Trees.TreeApi var10006;
                                                if (isEqual) {
                                                   var10006 = ((Exprs.Expr)callBuilder.apply(condExpr, c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftString().apply(""), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Liftable().liftExpr().apply(str), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Literal().apply(c.universe().Constant().apply(" (")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Liftable().liftExpr().apply(leftExpr), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Literal().apply(c.universe().Constant().apply(" ")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(actualRelExpr, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Literal().apply(c.universe().Constant().apply(" ")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Liftable().liftExpr().apply(rightExpr), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().TermName().apply("$plus")), (List)(new .colon.colon((List)(new .colon.colon(c.universe().Literal().apply(c.universe().Constant().apply(")")), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing()))).tree();
                                                } else if (!isConstant$1(left, c) && !isConstant$1(right, c)) {
                                                   Universe $u = c.universe();
                                                   Mirror $m = c.universe().rootMirror();

                                                   final class $treecreator1$7 extends TreeCreator {
                                                      private final Exprs.Expr str$1;
                                                      private final Exprs.Expr leftExpr$1;

                                                      public Trees.TreeApi apply(final Mirror $m$untyped) {
                                                         scala.reflect.api.Universe $u = $m$untyped.universe();
                                                         return $u.Apply().apply($u.Select().apply($u.Apply().apply($u.Select().apply($u.internal().reificationSupport().mkIdent($m$untyped.staticModule("scala.StringContext")), (Names.NameApi)$u.TermName().apply("apply")), (List)(new .colon.colon($u.Literal().apply($u.Constant().apply("")), new .colon.colon($u.Literal().apply($u.Constant().apply(" (== ")), new .colon.colon($u.Literal().apply($u.Constant().apply(")")), scala.collection.immutable.Nil..MODULE$))))), (Names.NameApi)$u.TermName().apply("s")), (List)(new .colon.colon(this.str$1.in($m$untyped).tree(), new .colon.colon(this.leftExpr$1.in($m$untyped).tree(), scala.collection.immutable.Nil..MODULE$))));
                                                      }

                                                      public $treecreator1$7(final Exprs.Expr str$1, final Exprs.Expr leftExpr$1) {
                                                         this.str$1 = str$1;
                                                         this.leftExpr$1 = leftExpr$1;
                                                      }
                                                   }


                                                   final class $typecreator2$7 extends TypeCreator {
                                                      public Types.TypeApi apply(final Mirror $m$untyped) {
                                                         scala.reflect.api.Universe $u = $m$untyped.universe();
                                                         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
                                                      }

                                                      public $typecreator2$7() {
                                                      }
                                                   }

                                                   var10006 = ((Exprs.Expr)callBuilder.apply(condExpr, $u.Expr().apply($m, new $treecreator1$7(str, leftExpr), $u.TypeTag().apply($m, new $typecreator2$7())))).tree();
                                                } else {
                                                   var10006 = ((Exprs.Expr)callBuilder.apply(condExpr, str)).tree();
                                                }

                                                var10003[2] = var10006;
                                                var4 = var10001.Block(var48.wrapRefArray((Object[])var10003));
                                                return c.Expr(var4, c.universe().WeakTypeTag().Unit());
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      Trees.ValDefApi condDef = newValDef$1("cond", typedCondition, newValDef$default$3$1(), c);
      Exprs.Expr condExpr = c.Expr(c.universe().Ident().apply((Names.NameApi)condDef.name()), c.universe().WeakTypeTag().Boolean());
      Object var10002 = isConstant$1(typedCondition, c) ? c.universe().Literal().apply(c.universe().Constant().apply("Always false!")) : c.universe().Liftable().liftString().apply(typedCondition.toString());
      Universe $u = c.universe();
      Mirror $m = c.universe().rootMirror();

      final class $typecreator3$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
         }

         public $typecreator3$1() {
         }
      }

      Exprs.Expr str = c.Expr((Trees.TreeApi)var10002, $u.TypeTag().apply($m, new $typecreator3$1()));
      var4 = c.universe().Block(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{condDef, ((Exprs.Expr)callBuilder.apply(condExpr, str)).tree()})));
      return c.Expr(var4, c.universe().WeakTypeTag().Unit());
   }

   private static final Trees.ValDefApi newValDef$1(final String name, final Trees.TreeApi rhs, final Types.TypeApi tpe, final Context c$7) {
      return c$7.universe().ValDef().apply(c$7.universe().NoMods(), c$7.universe().newTermName(c$7.fresh(name)), c$7.universe().TypeTree((Types.TypeApi)scala.Option..MODULE$.apply(tpe).getOrElse(() -> rhs.tpe().normalize())), rhs);
   }

   private static final Types.TypeApi newValDef$default$3$1() {
      return null;
   }

   // $FF: synthetic method
   private static final EqualityOpName$1$ EqualityOpName$lzycompute$1(final LazyRef EqualityOpName$module$1) {
      synchronized(EqualityOpName$module$1){}

      EqualityOpName$1$ var2;
      try {
         class EqualityOpName$1$ {
            public Option unapply(final Names.NameApi name) {
               Object var10000;
               label39: {
                  String s = name.toString();
                  String var3 = scala.reflect.NameTransformer..MODULE$.encode("==");
                  if (s == null) {
                     if (var3 == null) {
                        break label39;
                     }
                  } else if (s.equals(var3)) {
                     break label39;
                  }

                  label40: {
                     String var4 = scala.reflect.NameTransformer..MODULE$.encode("!=");
                     if (s == null) {
                        if (var4 == null) {
                           break label40;
                        }
                     } else if (s.equals(var4)) {
                        break label40;
                     }

                     var10000 = scala.None..MODULE$;
                     return (Option)var10000;
                  }

                  var10000 = new Some(BoxesRunTime.boxToBoolean(false));
                  return (Option)var10000;
               }

               var10000 = new Some(BoxesRunTime.boxToBoolean(true));
               return (Option)var10000;
            }

            public EqualityOpName$1$() {
            }
         }

         var2 = EqualityOpName$module$1.initialized() ? (EqualityOpName$1$)EqualityOpName$module$1.value() : (EqualityOpName$1$)EqualityOpName$module$1.initialize(new EqualityOpName$1$());
      } catch (Throwable var4) {
         throw var4;
      }

      return var2;
   }

   private final EqualityOpName$1$ EqualityOpName$2(final LazyRef EqualityOpName$module$1) {
      return EqualityOpName$module$1.initialized() ? (EqualityOpName$1$)EqualityOpName$module$1.value() : EqualityOpName$lzycompute$1(EqualityOpName$module$1);
   }

   private static final boolean isConstant$1(final Trees.TreeApi tree, final Context c$7) {
      boolean var2;
      if (tree != null) {
         Option var4 = c$7.universe().LiteralTag().unapply(tree);
         if (!var4.isEmpty()) {
            Trees.LiteralApi var5 = (Trees.LiteralApi)var4.get();
            if (var5 != null) {
               Option var6 = c$7.universe().Literal().unapply(var5);
               if (!var6.isEmpty()) {
                  Constants.ConstantApi var7 = (Constants.ConstantApi)var6.get();
                  if (var7 != null) {
                     Option var8 = c$7.universe().ConstantTag().unapply(var7);
                     if (!var8.isEmpty()) {
                        Constants.ConstantApi var9 = (Constants.ConstantApi)var8.get();
                        if (var9 != null) {
                           Option var10 = c$7.universe().Constant().unapply(var9);
                           if (!var10.isEmpty()) {
                              var2 = true;
                              return var2;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var2 = false;
      return var2;
   }

   private AssertImpl$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
