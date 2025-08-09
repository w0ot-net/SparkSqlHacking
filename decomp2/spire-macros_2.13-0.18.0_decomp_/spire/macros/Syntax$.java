package spire.macros;

import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.collection.immutable.List;
import scala.reflect.api.Constants;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

public final class Syntax$ {
   public static final Syntax$ MODULE$ = new Syntax$();

   public Exprs.Expr cforMacro(final Context c, final Exprs.Expr init, final Exprs.Expr test, final Exprs.Expr next, final Exprs.Expr body) {
      SyntaxUtil util = new SyntaxUtil(c);
      Names.TermNameApi index = util.name("index");
      Trees.TreeApi var10000;
      if (util.isClean(.MODULE$.wrapRefArray((Object[])(new Exprs.Expr[]{test, next, body})))) {
         Names.TermNameApi nn$macro$10 = c.universe().internal().reificationSupport().freshTermName("while$");
         var10000 = c.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticVarDef().apply(c.universe().Modifiers().apply(c.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index, c.universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), c.universe().Liftable().liftExpr().apply(init)), new scala.collection.immutable..colon.colon(c.universe().LabelDef().apply(nn$macro$10, scala.collection.immutable.Nil..MODULE$, c.universe().If().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().Liftable().liftExpr().apply(test), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().Liftable().liftExpr().apply(body), (List)(new scala.collection.immutable..colon.colon((List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false)}))), scala.collection.immutable.Nil..MODULE$))), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticAssign().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false), c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().Liftable().liftExpr().apply(next), (List)(new scala.collection.immutable..colon.colon((List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false)}))), scala.collection.immutable.Nil..MODULE$)))), scala.collection.immutable.Nil..MODULE$)))), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$10, false), (List)(new scala.collection.immutable..colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), c.universe().Literal().apply(c.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$))));
      } else {
         Names.TermNameApi testName = util.name("test");
         Names.TermNameApi nextName = util.name("next");
         Names.TermNameApi bodyName = util.name("body");
         Names.TermNameApi nn$macro$26 = c.universe().internal().reificationSupport().freshTermName("while$");
         var10000 = c.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticValDef().apply(c.universe().NoMods(), testName, c.universe().internal().reificationSupport().SyntacticFunctionType().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Int")), scala.collection.immutable.Nil..MODULE$)), c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Boolean"))), c.universe().Liftable().liftExpr().apply(test)), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticValDef().apply(c.universe().NoMods(), nextName, c.universe().internal().reificationSupport().SyntacticFunctionType().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Int")), scala.collection.immutable.Nil..MODULE$)), c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Int"))), c.universe().Liftable().liftExpr().apply(next)), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticValDef().apply(c.universe().NoMods(), bodyName, c.universe().internal().reificationSupport().SyntacticFunctionType().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Int")), scala.collection.immutable.Nil..MODULE$)), c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Unit"))), c.universe().Liftable().liftExpr().apply(body)), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticVarDef().apply(c.universe().Modifiers().apply(c.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index, c.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c.universe().TypeName().apply("Int")), c.universe().Liftable().liftExpr().apply(init)), new scala.collection.immutable..colon.colon(c.universe().LabelDef().apply(nn$macro$26, scala.collection.immutable.Nil..MODULE$, c.universe().If().apply(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(testName, false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticBlock().apply((List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(bodyName, false), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false)})))})))), c.universe().internal().reificationSupport().SyntacticAssign().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false), c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(nextName, false), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().internal().reificationSupport().SyntacticTermIdent().apply(index, false)})))})))))})))), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$26, false), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{scala.collection.immutable.Nil..MODULE$})))), scala.collection.immutable.Nil..MODULE$)))), c.universe().Literal().apply(c.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$)))))));
      }

      Trees.TreeApi tree = var10000;
      InlineUtil var18 = new InlineUtil(c);
      return var18.inlineAndReset(tree);
   }

   public Exprs.Expr cforRangeMacro(final Context c, final Exprs.Expr r, final Exprs.Expr body) {
      Object tree;
      label99: {
         SyntaxUtil util = new SyntaxUtil(c);
         List names = util.names(.MODULE$.wrapRefArray((Object[])(new String[]{"range", "index", "end", "limit", "step"})));
         Names.TermNameApi index = (Names.TermNameApi)names.apply(1);
         Names.TermNameApi end = (Names.TermNameApi)names.apply(2);
         Names.TermNameApi limit = (Names.TermNameApi)names.apply(3);
         Trees.TreeApi var15 = r.tree();
         if (var15 != null) {
            Option var16 = ((<undefinedtype>)(new Object(c) {
               private final Context c$1;

               public Option unapply(final Object tree) {
                  Object var2;
                  if (tree != null) {
                     Option var4 = this.c$1.universe().TreeTag().unapply(tree);
                     if (!var4.isEmpty()) {
                        Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                        if (var5 != null) {
                           Some var6 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                           if (!var6.isEmpty()) {
                              Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              List var8 = (List)((Tuple2)var6.get())._2();
                              if (var7 != null) {
                                 Option var9 = this.c$1.universe().TreeTag().unapply(var7);
                                 if (!var9.isEmpty()) {
                                    Trees.TreeApi var10 = (Trees.TreeApi)var9.get();
                                    if (var10 != null) {
                                       Option var11 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var10);
                                       if (!var11.isEmpty()) {
                                          Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                          Names.TermNameApi var13 = (Names.TermNameApi)((Tuple2)var11.get())._2();
                                          if (var12 != null) {
                                             Option var14 = this.c$1.universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Some var16 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      List var18 = (List)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$1.universe().TreeTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Trees.TreeApi qq$macro$1 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                                  Names.TermNameApi var23 = (Names.TermNameApi)((Tuple2)var21.get())._2();
                                                                  if (var23 != null) {
                                                                     Option var24 = this.c$1.universe().TermNameTag().unapply(var23);
                                                                     if (!var24.isEmpty()) {
                                                                        Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Option var26 = this.c$1.universe().TermName().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              String var27 = (String)var26.get();
                                                                              if ("intWrapper".equals(var27) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                 scala.collection.immutable..colon.colon var28 = (scala.collection.immutable..colon.colon)var18;
                                                                                 List var29 = (List)var28.head();
                                                                                 List var30 = var28.next$access$1();
                                                                                 if (var29 instanceof scala.collection.immutable..colon.colon) {
                                                                                    scala.collection.immutable..colon.colon var31 = (scala.collection.immutable..colon.colon)var29;
                                                                                    Trees.TreeApi qq$macro$2 = (Trees.TreeApi)var31.head();
                                                                                    List var33 = var31.next$access$1();
                                                                                    if (scala.collection.immutable.Nil..MODULE$.equals(var33) && scala.collection.immutable.Nil..MODULE$.equals(var30) && var13 != null) {
                                                                                       Option var34 = this.c$1.universe().TermNameTag().unapply(var13);
                                                                                       if (!var34.isEmpty()) {
                                                                                          Names.TermNameApi var35 = (Names.TermNameApi)var34.get();
                                                                                          if (var35 != null) {
                                                                                             Option var36 = this.c$1.universe().TermName().unapply(var35);
                                                                                             if (!var36.isEmpty()) {
                                                                                                String var37 = (String)var36.get();
                                                                                                if ("until".equals(var37) && var8 instanceof scala.collection.immutable..colon.colon) {
                                                                                                   scala.collection.immutable..colon.colon var38 = (scala.collection.immutable..colon.colon)var8;
                                                                                                   List var39 = (List)var38.head();
                                                                                                   List var40 = var38.next$access$1();
                                                                                                   if (var39 instanceof scala.collection.immutable..colon.colon) {
                                                                                                      scala.collection.immutable..colon.colon var41 = (scala.collection.immutable..colon.colon)var39;
                                                                                                      Trees.TreeApi qq$macro$3 = (Trees.TreeApi)var41.head();
                                                                                                      List var43 = var41.next$access$1();
                                                                                                      if (scala.collection.immutable.Nil..MODULE$.equals(var43) && scala.collection.immutable.Nil..MODULE$.equals(var40)) {
                                                                                                         var2 = new Some(new Tuple3(qq$macro$1, qq$macro$2, qq$macro$3));
                                                                                                         return (Option)var2;
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
                  }

                  var2 = scala.None..MODULE$;
                  return (Option)var2;
               }

               public {
                  this.c$1 = c$1;
               }
            })).unapply(var15);
            if (!var16.isEmpty()) {
               Trees.TreeApi i = (Trees.TreeApi)((Tuple3)var16.get())._2();
               Trees.TreeApi j = (Trees.TreeApi)((Tuple3)var16.get())._3();
               tree = strideUpUntil$1(i, j, 1, c, index, limit, body);
               break label99;
            }
         }

         if (var15 != null) {
            Option var19 = ((<undefinedtype>)(new Object(c) {
               private final Context c$1;

               public Option unapply(final Object tree) {
                  Object var2;
                  if (tree != null) {
                     Option var4 = this.c$1.universe().TreeTag().unapply(tree);
                     if (!var4.isEmpty()) {
                        Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                        if (var5 != null) {
                           Some var6 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                           if (!var6.isEmpty()) {
                              Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              List var8 = (List)((Tuple2)var6.get())._2();
                              if (var7 != null) {
                                 Option var9 = this.c$1.universe().TreeTag().unapply(var7);
                                 if (!var9.isEmpty()) {
                                    Trees.TreeApi var10 = (Trees.TreeApi)var9.get();
                                    if (var10 != null) {
                                       Option var11 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var10);
                                       if (!var11.isEmpty()) {
                                          Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                          Names.TermNameApi var13 = (Names.TermNameApi)((Tuple2)var11.get())._2();
                                          if (var12 != null) {
                                             Option var14 = this.c$1.universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Some var16 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      List var18 = (List)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$1.universe().TreeTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Trees.TreeApi qq$macro$4 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                                  Names.TermNameApi var23 = (Names.TermNameApi)((Tuple2)var21.get())._2();
                                                                  if (var23 != null) {
                                                                     Option var24 = this.c$1.universe().TermNameTag().unapply(var23);
                                                                     if (!var24.isEmpty()) {
                                                                        Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Option var26 = this.c$1.universe().TermName().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              String var27 = (String)var26.get();
                                                                              if ("intWrapper".equals(var27) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                 scala.collection.immutable..colon.colon var28 = (scala.collection.immutable..colon.colon)var18;
                                                                                 List var29 = (List)var28.head();
                                                                                 List var30 = var28.next$access$1();
                                                                                 if (var29 instanceof scala.collection.immutable..colon.colon) {
                                                                                    scala.collection.immutable..colon.colon var31 = (scala.collection.immutable..colon.colon)var29;
                                                                                    Trees.TreeApi qq$macro$5 = (Trees.TreeApi)var31.head();
                                                                                    List var33 = var31.next$access$1();
                                                                                    if (scala.collection.immutable.Nil..MODULE$.equals(var33) && scala.collection.immutable.Nil..MODULE$.equals(var30) && var13 != null) {
                                                                                       Option var34 = this.c$1.universe().TermNameTag().unapply(var13);
                                                                                       if (!var34.isEmpty()) {
                                                                                          Names.TermNameApi var35 = (Names.TermNameApi)var34.get();
                                                                                          if (var35 != null) {
                                                                                             Option var36 = this.c$1.universe().TermName().unapply(var35);
                                                                                             if (!var36.isEmpty()) {
                                                                                                String var37 = (String)var36.get();
                                                                                                if ("to".equals(var37) && var8 instanceof scala.collection.immutable..colon.colon) {
                                                                                                   scala.collection.immutable..colon.colon var38 = (scala.collection.immutable..colon.colon)var8;
                                                                                                   List var39 = (List)var38.head();
                                                                                                   List var40 = var38.next$access$1();
                                                                                                   if (var39 instanceof scala.collection.immutable..colon.colon) {
                                                                                                      scala.collection.immutable..colon.colon var41 = (scala.collection.immutable..colon.colon)var39;
                                                                                                      Trees.TreeApi qq$macro$6 = (Trees.TreeApi)var41.head();
                                                                                                      List var43 = var41.next$access$1();
                                                                                                      if (scala.collection.immutable.Nil..MODULE$.equals(var43) && scala.collection.immutable.Nil..MODULE$.equals(var40)) {
                                                                                                         var2 = new Some(new Tuple3(qq$macro$4, qq$macro$5, qq$macro$6));
                                                                                                         return (Option)var2;
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
                  }

                  var2 = scala.None..MODULE$;
                  return (Option)var2;
               }

               public {
                  this.c$1 = c$1;
               }
            })).unapply(var15);
            if (!var19.isEmpty()) {
               Trees.TreeApi i = (Trees.TreeApi)((Tuple3)var19.get())._2();
               Trees.TreeApi j = (Trees.TreeApi)((Tuple3)var19.get())._3();
               tree = strideUpTo$1(i, j, 1, c, index, end, body);
               break label99;
            }
         }

         if (var15 != null) {
            Option var22 = ((<undefinedtype>)(new Object(c) {
               private final Context c$1;

               public Option unapply(final Object tree) {
                  Object var2;
                  if (tree != null) {
                     Option var4 = this.c$1.universe().TreeTag().unapply(tree);
                     if (!var4.isEmpty()) {
                        Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                        if (var5 != null) {
                           Some var6 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                           if (!var6.isEmpty()) {
                              Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              List var8 = (List)((Tuple2)var6.get())._2();
                              if (var7 != null) {
                                 Option var9 = this.c$1.universe().TreeTag().unapply(var7);
                                 if (!var9.isEmpty()) {
                                    Trees.TreeApi var10 = (Trees.TreeApi)var9.get();
                                    if (var10 != null) {
                                       Option var11 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var10);
                                       if (!var11.isEmpty()) {
                                          Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                          Names.TermNameApi var13 = (Names.TermNameApi)((Tuple2)var11.get())._2();
                                          if (var12 != null) {
                                             Option var14 = this.c$1.universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Some var16 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      List var18 = (List)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$1.universe().TreeTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Trees.TreeApi var22 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                                  Names.TermNameApi var23 = (Names.TermNameApi)((Tuple2)var21.get())._2();
                                                                  if (var22 != null) {
                                                                     Option var24 = this.c$1.universe().TreeTag().unapply(var22);
                                                                     if (!var24.isEmpty()) {
                                                                        Trees.TreeApi var25 = (Trees.TreeApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Some var26 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              Trees.TreeApi var27 = (Trees.TreeApi)((Tuple2)var26.get())._1();
                                                                              List var28 = (List)((Tuple2)var26.get())._2();
                                                                              if (var27 != null) {
                                                                                 Option var29 = this.c$1.universe().TreeTag().unapply(var27);
                                                                                 if (!var29.isEmpty()) {
                                                                                    Trees.TreeApi var30 = (Trees.TreeApi)var29.get();
                                                                                    if (var30 != null) {
                                                                                       Option var31 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var30);
                                                                                       if (!var31.isEmpty()) {
                                                                                          Trees.TreeApi qq$macro$7 = (Trees.TreeApi)((Tuple2)var31.get())._1();
                                                                                          Names.TermNameApi var33 = (Names.TermNameApi)((Tuple2)var31.get())._2();
                                                                                          if (var33 != null) {
                                                                                             Option var34 = this.c$1.universe().TermNameTag().unapply(var33);
                                                                                             if (!var34.isEmpty()) {
                                                                                                Names.TermNameApi var35 = (Names.TermNameApi)var34.get();
                                                                                                if (var35 != null) {
                                                                                                   Option var36 = this.c$1.universe().TermName().unapply(var35);
                                                                                                   if (!var36.isEmpty()) {
                                                                                                      String var37 = (String)var36.get();
                                                                                                      if ("intWrapper".equals(var37) && var28 instanceof scala.collection.immutable..colon.colon) {
                                                                                                         scala.collection.immutable..colon.colon var38 = (scala.collection.immutable..colon.colon)var28;
                                                                                                         List var39 = (List)var38.head();
                                                                                                         List var40 = var38.next$access$1();
                                                                                                         if (var39 instanceof scala.collection.immutable..colon.colon) {
                                                                                                            scala.collection.immutable..colon.colon var41 = (scala.collection.immutable..colon.colon)var39;
                                                                                                            Trees.TreeApi qq$macro$8 = (Trees.TreeApi)var41.head();
                                                                                                            List var43 = var41.next$access$1();
                                                                                                            if (scala.collection.immutable.Nil..MODULE$.equals(var43) && scala.collection.immutable.Nil..MODULE$.equals(var40) && var23 != null) {
                                                                                                               Option var44 = this.c$1.universe().TermNameTag().unapply(var23);
                                                                                                               if (!var44.isEmpty()) {
                                                                                                                  Names.TermNameApi var45 = (Names.TermNameApi)var44.get();
                                                                                                                  if (var45 != null) {
                                                                                                                     Option var46 = this.c$1.universe().TermName().unapply(var45);
                                                                                                                     if (!var46.isEmpty()) {
                                                                                                                        String var47 = (String)var46.get();
                                                                                                                        if ("until".equals(var47) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                           scala.collection.immutable..colon.colon var48 = (scala.collection.immutable..colon.colon)var18;
                                                                                                                           List var49 = (List)var48.head();
                                                                                                                           List var50 = var48.next$access$1();
                                                                                                                           if (var49 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                              scala.collection.immutable..colon.colon var51 = (scala.collection.immutable..colon.colon)var49;
                                                                                                                              Trees.TreeApi qq$macro$9 = (Trees.TreeApi)var51.head();
                                                                                                                              List var53 = var51.next$access$1();
                                                                                                                              if (scala.collection.immutable.Nil..MODULE$.equals(var53) && scala.collection.immutable.Nil..MODULE$.equals(var50) && var13 != null) {
                                                                                                                                 Option var54 = this.c$1.universe().TermNameTag().unapply(var13);
                                                                                                                                 if (!var54.isEmpty()) {
                                                                                                                                    Names.TermNameApi var55 = (Names.TermNameApi)var54.get();
                                                                                                                                    if (var55 != null) {
                                                                                                                                       Option var56 = this.c$1.universe().TermName().unapply(var55);
                                                                                                                                       if (!var56.isEmpty()) {
                                                                                                                                          String var57 = (String)var56.get();
                                                                                                                                          if ("by".equals(var57) && var8 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                                             scala.collection.immutable..colon.colon var58 = (scala.collection.immutable..colon.colon)var8;
                                                                                                                                             List var59 = (List)var58.head();
                                                                                                                                             List var60 = var58.next$access$1();
                                                                                                                                             if (var59 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                                                scala.collection.immutable..colon.colon var61 = (scala.collection.immutable..colon.colon)var59;
                                                                                                                                                Trees.TreeApi qq$macro$10 = (Trees.TreeApi)var61.head();
                                                                                                                                                List var63 = var61.next$access$1();
                                                                                                                                                if (scala.collection.immutable.Nil..MODULE$.equals(var63) && scala.collection.immutable.Nil..MODULE$.equals(var60)) {
                                                                                                                                                   var2 = new Some(new Tuple4(qq$macro$7, qq$macro$8, qq$macro$9, qq$macro$10));
                                                                                                                                                   return (Option)var2;
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
                  }

                  var2 = scala.None..MODULE$;
                  return (Option)var2;
               }

               public {
                  this.c$1 = c$1;
               }
            })).unapply(var15);
            if (!var22.isEmpty()) {
               Object var6;
               label100: {
                  Trees.TreeApi i = (Trees.TreeApi)((Tuple4)var22.get())._2();
                  Trees.TreeApi j = (Trees.TreeApi)((Tuple4)var22.get())._3();
                  Trees.TreeApi step = (Trees.TreeApi)((Tuple4)var22.get())._4();
                  boolean var26 = false;
                  Some var27 = null;
                  Option var28 = isLiteral$1(step, c);
                  if (var28 instanceof Some) {
                     var26 = true;
                     var27 = (Some)var28;
                     int k = BoxesRunTime.unboxToInt(var27.value());
                     if (k > 0) {
                        var6 = strideUpUntil$1(i, j, k, c, index, limit, body);
                        break label100;
                     }
                  }

                  if (var26) {
                     int k = BoxesRunTime.unboxToInt(var27.value());
                     if (k < 0) {
                        var6 = strideDownUntil$1(i, j, -k, c, index, limit, body);
                        break label100;
                     }
                  }

                  if (var26) {
                     c.error(c.enclosingPosition(), "zero stride");
                     var6 = c.universe().Literal().apply(c.universe().Constant().apply(BoxedUnit.UNIT));
                  } else {
                     if (!scala.None..MODULE$.equals(var28)) {
                        throw new MatchError(var28);
                     }

                     c.info(c.enclosingPosition(), "non-literal stride", true);
                     var6 = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(var15, c.universe().TermName().apply("foreach")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(body), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
                  }
               }

               tree = var6;
               break label99;
            }
         }

         if (var15 != null) {
            Option var31 = ((<undefinedtype>)(new Object(c) {
               private final Context c$1;

               public Option unapply(final Object tree) {
                  Object var2;
                  if (tree != null) {
                     Option var4 = this.c$1.universe().TreeTag().unapply(tree);
                     if (!var4.isEmpty()) {
                        Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                        if (var5 != null) {
                           Some var6 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var5);
                           if (!var6.isEmpty()) {
                              Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              List var8 = (List)((Tuple2)var6.get())._2();
                              if (var7 != null) {
                                 Option var9 = this.c$1.universe().TreeTag().unapply(var7);
                                 if (!var9.isEmpty()) {
                                    Trees.TreeApi var10 = (Trees.TreeApi)var9.get();
                                    if (var10 != null) {
                                       Option var11 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var10);
                                       if (!var11.isEmpty()) {
                                          Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                          Names.TermNameApi var13 = (Names.TermNameApi)((Tuple2)var11.get())._2();
                                          if (var12 != null) {
                                             Option var14 = this.c$1.universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Some var16 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      List var18 = (List)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$1.universe().TreeTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Trees.TreeApi var22 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                                  Names.TermNameApi var23 = (Names.TermNameApi)((Tuple2)var21.get())._2();
                                                                  if (var22 != null) {
                                                                     Option var24 = this.c$1.universe().TreeTag().unapply(var22);
                                                                     if (!var24.isEmpty()) {
                                                                        Trees.TreeApi var25 = (Trees.TreeApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Some var26 = this.c$1.universe().internal().reificationSupport().SyntacticApplied().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              Trees.TreeApi var27 = (Trees.TreeApi)((Tuple2)var26.get())._1();
                                                                              List var28 = (List)((Tuple2)var26.get())._2();
                                                                              if (var27 != null) {
                                                                                 Option var29 = this.c$1.universe().TreeTag().unapply(var27);
                                                                                 if (!var29.isEmpty()) {
                                                                                    Trees.TreeApi var30 = (Trees.TreeApi)var29.get();
                                                                                    if (var30 != null) {
                                                                                       Option var31 = this.c$1.universe().internal().reificationSupport().SyntacticSelectTerm().unapply(var30);
                                                                                       if (!var31.isEmpty()) {
                                                                                          Trees.TreeApi qq$macro$13 = (Trees.TreeApi)((Tuple2)var31.get())._1();
                                                                                          Names.TermNameApi var33 = (Names.TermNameApi)((Tuple2)var31.get())._2();
                                                                                          if (var33 != null) {
                                                                                             Option var34 = this.c$1.universe().TermNameTag().unapply(var33);
                                                                                             if (!var34.isEmpty()) {
                                                                                                Names.TermNameApi var35 = (Names.TermNameApi)var34.get();
                                                                                                if (var35 != null) {
                                                                                                   Option var36 = this.c$1.universe().TermName().unapply(var35);
                                                                                                   if (!var36.isEmpty()) {
                                                                                                      String var37 = (String)var36.get();
                                                                                                      if ("intWrapper".equals(var37) && var28 instanceof scala.collection.immutable..colon.colon) {
                                                                                                         scala.collection.immutable..colon.colon var38 = (scala.collection.immutable..colon.colon)var28;
                                                                                                         List var39 = (List)var38.head();
                                                                                                         List var40 = var38.next$access$1();
                                                                                                         if (var39 instanceof scala.collection.immutable..colon.colon) {
                                                                                                            scala.collection.immutable..colon.colon var41 = (scala.collection.immutable..colon.colon)var39;
                                                                                                            Trees.TreeApi qq$macro$14 = (Trees.TreeApi)var41.head();
                                                                                                            List var43 = var41.next$access$1();
                                                                                                            if (scala.collection.immutable.Nil..MODULE$.equals(var43) && scala.collection.immutable.Nil..MODULE$.equals(var40) && var23 != null) {
                                                                                                               Option var44 = this.c$1.universe().TermNameTag().unapply(var23);
                                                                                                               if (!var44.isEmpty()) {
                                                                                                                  Names.TermNameApi var45 = (Names.TermNameApi)var44.get();
                                                                                                                  if (var45 != null) {
                                                                                                                     Option var46 = this.c$1.universe().TermName().unapply(var45);
                                                                                                                     if (!var46.isEmpty()) {
                                                                                                                        String var47 = (String)var46.get();
                                                                                                                        if ("to".equals(var47) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                           scala.collection.immutable..colon.colon var48 = (scala.collection.immutable..colon.colon)var18;
                                                                                                                           List var49 = (List)var48.head();
                                                                                                                           List var50 = var48.next$access$1();
                                                                                                                           if (var49 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                              scala.collection.immutable..colon.colon var51 = (scala.collection.immutable..colon.colon)var49;
                                                                                                                              Trees.TreeApi qq$macro$15 = (Trees.TreeApi)var51.head();
                                                                                                                              List var53 = var51.next$access$1();
                                                                                                                              if (scala.collection.immutable.Nil..MODULE$.equals(var53) && scala.collection.immutable.Nil..MODULE$.equals(var50) && var13 != null) {
                                                                                                                                 Option var54 = this.c$1.universe().TermNameTag().unapply(var13);
                                                                                                                                 if (!var54.isEmpty()) {
                                                                                                                                    Names.TermNameApi var55 = (Names.TermNameApi)var54.get();
                                                                                                                                    if (var55 != null) {
                                                                                                                                       Option var56 = this.c$1.universe().TermName().unapply(var55);
                                                                                                                                       if (!var56.isEmpty()) {
                                                                                                                                          String var57 = (String)var56.get();
                                                                                                                                          if ("by".equals(var57) && var8 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                                             scala.collection.immutable..colon.colon var58 = (scala.collection.immutable..colon.colon)var8;
                                                                                                                                             List var59 = (List)var58.head();
                                                                                                                                             List var60 = var58.next$access$1();
                                                                                                                                             if (var59 instanceof scala.collection.immutable..colon.colon) {
                                                                                                                                                scala.collection.immutable..colon.colon var61 = (scala.collection.immutable..colon.colon)var59;
                                                                                                                                                Trees.TreeApi qq$macro$16 = (Trees.TreeApi)var61.head();
                                                                                                                                                List var63 = var61.next$access$1();
                                                                                                                                                if (scala.collection.immutable.Nil..MODULE$.equals(var63) && scala.collection.immutable.Nil..MODULE$.equals(var60)) {
                                                                                                                                                   var2 = new Some(new Tuple4(qq$macro$13, qq$macro$14, qq$macro$15, qq$macro$16));
                                                                                                                                                   return (Option)var2;
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
                  }

                  var2 = scala.None..MODULE$;
                  return (Option)var2;
               }

               public {
                  this.c$1 = c$1;
               }
            })).unapply(var15);
            if (!var31.isEmpty()) {
               Object var5;
               label101: {
                  Trees.TreeApi i = (Trees.TreeApi)((Tuple4)var31.get())._2();
                  Trees.TreeApi j = (Trees.TreeApi)((Tuple4)var31.get())._3();
                  Trees.TreeApi step = (Trees.TreeApi)((Tuple4)var31.get())._4();
                  boolean var35 = false;
                  Some var36 = null;
                  Option var37 = isLiteral$1(step, c);
                  if (var37 instanceof Some) {
                     var35 = true;
                     var36 = (Some)var37;
                     int k = BoxesRunTime.unboxToInt(var36.value());
                     if (k > 0) {
                        var5 = strideUpTo$1(i, j, k, c, index, end, body);
                        break label101;
                     }
                  }

                  if (var35) {
                     int k = BoxesRunTime.unboxToInt(var36.value());
                     if (k < 0) {
                        var5 = strideDownTo$1(i, j, -k, c, index, end, body);
                        break label101;
                     }
                  }

                  if (var35) {
                     c.error(c.enclosingPosition(), "zero stride");
                     var5 = c.universe().Literal().apply(c.universe().Constant().apply(BoxedUnit.UNIT));
                  } else {
                     if (!scala.None..MODULE$.equals(var37)) {
                        throw new MatchError(var37);
                     }

                     c.info(c.enclosingPosition(), "non-literal stride", true);
                     var5 = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(var15, c.universe().TermName().apply("foreach")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(body), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
                  }
               }

               tree = var5;
               break label99;
            }
         }

         c.info(c.enclosingPosition(), "non-literal range", true);
         tree = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(var15, c.universe().TermName().apply("foreach")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(body), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
      }

      InlineUtil var40 = new InlineUtil(c);
      return var40.inlineAndReset((Trees.TreeApi)tree);
   }

   public Exprs.Expr cforRange2Macro(final Context c, final Exprs.Expr r1, final Exprs.Expr r2, final Exprs.Expr body) {
      return c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("cforRange"), false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(r1), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticFunction().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticValDef().apply(c.universe().Modifiers().apply(c.universe().internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)c.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), c.universe().TermName().apply("i"), c.universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), c.universe().EmptyTree()), scala.collection.immutable.Nil..MODULE$)), c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("cforRange"), false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftExpr().apply(r2), scala.collection.immutable.Nil..MODULE$)), new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticFunction().apply((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticValDef().apply(c.universe().Modifiers().apply(c.universe().internal().reificationSupport().FlagsRepr().apply(8192L), (Names.NameApi)c.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), c.universe().TermName().apply("j"), c.universe().internal().reificationSupport().SyntacticEmptyTypeTree().apply(), c.universe().EmptyTree()), scala.collection.immutable.Nil..MODULE$)), c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().Liftable().liftExpr().apply(body), (List)(new scala.collection.immutable..colon.colon((List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("i"), false), c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("j"), false)}))), scala.collection.immutable.Nil..MODULE$)))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))))), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)))), c.universe().WeakTypeTag().Unit());
   }

   private static final Option isLiteral$1(final Trees.TreeApi t, final Context c$1) {
      Object var2;
      if (t != null) {
         Option var5 = c$1.universe().LiteralTag().unapply(t);
         if (!var5.isEmpty()) {
            Trees.LiteralApi var6 = (Trees.LiteralApi)var5.get();
            if (var6 != null) {
               Option var7 = c$1.universe().Literal().unapply(var6);
               if (!var7.isEmpty()) {
                  Constants.ConstantApi var8 = (Constants.ConstantApi)var7.get();
                  if (var8 != null) {
                     Option var9 = c$1.universe().ConstantTag().unapply(var8);
                     if (!var9.isEmpty()) {
                        Constants.ConstantApi var10 = (Constants.ConstantApi)var9.get();
                        if (var10 != null) {
                           Option var11 = c$1.universe().Constant().unapply(var10);
                           if (!var11.isEmpty()) {
                              Object a = var11.get();
                              Object var3;
                              if (a instanceof Integer) {
                                 int var14 = BoxesRunTime.unboxToInt(a);
                                 var3 = new Some(BoxesRunTime.boxToInteger(var14));
                              } else {
                                 var3 = scala.None..MODULE$;
                              }

                              var2 = var3;
                              return (Option)var2;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      var2 = scala.None..MODULE$;
      return (Option)var2;
   }

   private static final Trees.TreeApi strideUpTo$1(final Trees.TreeApi fromExpr, final Trees.TreeApi toExpr, final int stride, final Context c$1, final Names.TermNameApi index$1, final Names.TermNameApi end$1, final Exprs.Expr body$2$1) {
      Names.TermNameApi nn$macro$11 = c$1.universe().internal().reificationSupport().freshTermName("while$");
      return c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticVarDef().apply(c$1.universe().Modifiers().apply(c$1.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c$1.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), fromExpr), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticValDef().apply(c$1.universe().NoMods(), end$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), toExpr), new scala.collection.immutable..colon.colon(c$1.universe().LabelDef().apply(nn$macro$11, scala.collection.immutable.Nil..MODULE$, c$1.universe().If().apply(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$less$eq")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(end$1, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().Liftable().liftExpr().apply(body$2$1), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false)})))})))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$plus$eq")), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{c$1.universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(stride))})))})))), scala.collection.immutable.Nil..MODULE$)))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$11, false), (List)(new scala.collection.immutable..colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), c$1.universe().Literal().apply(c$1.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$)))));
   }

   private static final Trees.TreeApi strideUpUntil$1(final Trees.TreeApi fromExpr, final Trees.TreeApi untilExpr, final int stride, final Context c$1, final Names.TermNameApi index$1, final Names.TermNameApi limit$1, final Exprs.Expr body$2$1) {
      Names.TermNameApi nn$macro$11 = c$1.universe().internal().reificationSupport().freshTermName("while$");
      return c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticVarDef().apply(c$1.universe().Modifiers().apply(c$1.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c$1.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), fromExpr), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticValDef().apply(c$1.universe().NoMods(), limit$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), untilExpr), new scala.collection.immutable..colon.colon(c$1.universe().LabelDef().apply(nn$macro$11, scala.collection.immutable.Nil..MODULE$, c$1.universe().If().apply(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$less")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(limit$1, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().Liftable().liftExpr().apply(body$2$1), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false)})))})))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$plus$eq")), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{c$1.universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(stride))})))})))), scala.collection.immutable.Nil..MODULE$)))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$11, false), (List)(new scala.collection.immutable..colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), c$1.universe().Literal().apply(c$1.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$)))));
   }

   private static final Trees.TreeApi strideDownTo$1(final Trees.TreeApi fromExpr, final Trees.TreeApi toExpr, final int stride, final Context c$1, final Names.TermNameApi index$1, final Names.TermNameApi end$1, final Exprs.Expr body$2$1) {
      Names.TermNameApi nn$macro$11 = c$1.universe().internal().reificationSupport().freshTermName("while$");
      return c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticVarDef().apply(c$1.universe().Modifiers().apply(c$1.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c$1.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), fromExpr), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticValDef().apply(c$1.universe().NoMods(), end$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), toExpr), new scala.collection.immutable..colon.colon(c$1.universe().LabelDef().apply(nn$macro$11, scala.collection.immutable.Nil..MODULE$, c$1.universe().If().apply(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$greater$eq")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(end$1, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().Liftable().liftExpr().apply(body$2$1), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false)})))})))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$minus$eq")), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{c$1.universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(stride))})))})))), scala.collection.immutable.Nil..MODULE$)))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$11, false), (List)(new scala.collection.immutable..colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), c$1.universe().Literal().apply(c$1.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$)))));
   }

   private static final Trees.TreeApi strideDownUntil$1(final Trees.TreeApi fromExpr, final Trees.TreeApi untilExpr, final int stride, final Context c$1, final Names.TermNameApi index$1, final Names.TermNameApi limit$1, final Exprs.Expr body$2$1) {
      Names.TermNameApi nn$macro$11 = c$1.universe().internal().reificationSupport().freshTermName("while$");
      return c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticVarDef().apply(c$1.universe().Modifiers().apply(c$1.universe().internal().reificationSupport().FlagsRepr().apply(4096L), (Names.NameApi)c$1.universe().TypeName().apply(""), scala.collection.immutable.Nil..MODULE$), index$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), fromExpr), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticValDef().apply(c$1.universe().NoMods(), limit$1, c$1.universe().internal().reificationSupport().SyntacticTypeIdent().apply(c$1.universe().TypeName().apply("Int")), untilExpr), new scala.collection.immutable..colon.colon(c$1.universe().LabelDef().apply(nn$macro$11, scala.collection.immutable.Nil..MODULE$, c$1.universe().If().apply(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$greater")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(limit$1, false), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticBlock().apply((List)(new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().Liftable().liftExpr().apply(body$2$1), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false)})))})))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(index$1, false), c$1.universe().TermName().apply("$minus$eq")), (List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new List[]{(List)scala.collection.immutable.List..MODULE$.apply(.MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{c$1.universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(stride))})))})))), scala.collection.immutable.Nil..MODULE$)))), new scala.collection.immutable..colon.colon(c$1.universe().internal().reificationSupport().SyntacticApplied().apply(c$1.universe().internal().reificationSupport().SyntacticTermIdent().apply(nn$macro$11, false), (List)(new scala.collection.immutable..colon.colon(scala.collection.immutable.Nil..MODULE$, scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$)))), c$1.universe().Literal().apply(c$1.universe().Constant().apply(BoxedUnit.UNIT)))), scala.collection.immutable.Nil..MODULE$)))));
   }

   private Syntax$() {
   }
}
