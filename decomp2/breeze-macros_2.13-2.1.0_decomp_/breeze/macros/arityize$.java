package breeze.macros;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Tuple4;
import scala.Tuple6;
import scala.collection.IterableOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.api.Constants;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

public final class arityize$ {
   public static final arityize$ MODULE$ = new arityize$();

   public Exprs.Expr arityizeImpl(final Context c, final Seq annottees) {
      Trees.TreeApi var5 = ((Exprs.Expr)annottees.head()).tree();
      Exprs.Expr var3;
      if (var5 != null) {
         Option var6 = c.mirror().universe().ClassDefTag().unapply(var5);
         if (!var6.isEmpty()) {
            Trees.ClassDefApi var7 = (Trees.ClassDefApi)var6.get();
            if (var7 != null) {
               Option var8 = c.mirror().universe().ClassDef().unapply(var7);
               if (!var8.isEmpty()) {
                  Trees.ModifiersApi mods = (Trees.ModifiersApi)((Tuple4)var8.get())._1();
                  Names.TypeNameApi name = (Names.TypeNameApi)((Tuple4)var8.get())._2();
                  List targs = (List)((Tuple4)var8.get())._3();
                  Trees.TemplateApi impl = (Trees.TemplateApi)((Tuple4)var8.get())._4();
                  int maxOrder = this.extractOrder(c);
                  IndexedSeq results = .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), maxOrder).map((order) -> $anonfun$arityizeImpl$1(name, c, impl, targs, mods, BoxesRunTime.unboxToInt(order)));
                  Exprs.Expr ret = c.Expr(c.mirror().universe().Block().apply(results.toList(), c.mirror().universe().Literal().apply(c.mirror().universe().Constant().apply(BoxedUnit.UNIT))), c.universe().WeakTypeTag().Nothing());
                  var3 = ret;
                  return var3;
               }
            }
         }
      }

      if (var5 == null) {
         throw scala.Predef..MODULE$.$qmark$qmark$qmark();
      } else {
         Option var16 = c.mirror().universe().DefDefTag().unapply(var5);
         if (var16.isEmpty()) {
            throw scala.Predef..MODULE$.$qmark$qmark$qmark();
         } else {
            Trees.DefDefApi var17 = (Trees.DefDefApi)var16.get();
            if (var17 == null) {
               throw scala.Predef..MODULE$.$qmark$qmark$qmark();
            } else {
               Option var18 = c.mirror().universe().DefDef().unapply(var17);
               if (var18.isEmpty()) {
                  throw scala.Predef..MODULE$.$qmark$qmark$qmark();
               } else {
                  Trees.ModifiersApi mods = (Trees.ModifiersApi)((Tuple6)var18.get())._1();
                  Names.TermNameApi name = (Names.TermNameApi)((Tuple6)var18.get())._2();
                  List targs = (List)((Tuple6)var18.get())._3();
                  List vargs = (List)((Tuple6)var18.get())._4();
                  Trees.TreeApi tpt = (Trees.TreeApi)((Tuple6)var18.get())._5();
                  Trees.TreeApi impl = (Trees.TreeApi)((Tuple6)var18.get())._6();
                  int maxOrder = this.extractOrder(c);
                  IndexedSeq results = .MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(1), maxOrder).map((order) -> $anonfun$arityizeImpl$4(name, c, impl, vargs, targs, tpt, mods, BoxesRunTime.unboxToInt(order)));
                  Exprs.Expr ret = c.Expr(c.mirror().universe().Block().apply(results.toList(), c.mirror().universe().Literal().apply(c.mirror().universe().Constant().apply(BoxedUnit.UNIT))), c.universe().WeakTypeTag().Nothing());
                  var3 = ret;
                  return var3;
               }
            }
         }
      }
   }

   public Seq expandArity(final Context c, final int order, final Map bindings, final Trees.TreeApi tree) {
      Object var5;
      if (tree != null) {
         Option var12 = c.mirror().universe().DefDefTag().unapply(tree);
         if (!var12.isEmpty()) {
            Trees.DefDefApi var13 = (Trees.DefDefApi)var12.get();
            if (var13 != null) {
               Option var14 = c.mirror().universe().DefDef().unapply(var13);
               if (!var14.isEmpty()) {
                  Trees.ModifiersApi mods = (Trees.ModifiersApi)((Tuple6)var14.get())._1();
                  Names.TermNameApi name = (Names.TermNameApi)((Tuple6)var14.get())._2();
                  List targs = (List)((Tuple6)var14.get())._3();
                  List vargs = (List)((Tuple6)var14.get())._4();
                  Trees.TreeApi ret = (Trees.TreeApi)((Tuple6)var14.get())._5();
                  Trees.TreeApi impl = (Trees.TreeApi)((Tuple6)var14.get())._6();
                  Trees.TreeApi newImpl = (Trees.TreeApi)this.expandArity(c, order, bindings, impl).head();
                  List newVargs = vargs.map((x$2) -> x$2.flatMap((arg) -> MODULE$.expandValDef(c, order, bindings, arg)));
                  List newTargs = targs.flatMap((arg) -> MODULE$.expandTypeDef(c, order, bindings, arg));
                  Trees.TreeApi newRet = (Trees.TreeApi)this.expandArity(c, order, bindings, ret).head();
                  var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.DefDefApi[]{c.mirror().universe().DefDef().apply(mods, name, newTargs, newVargs, newRet, newImpl)})));
                  return (Seq)var5;
               }
            }
         }
      }

      if (tree != null) {
         Option var25 = c.mirror().universe().ClassDefTag().unapply(tree);
         if (!var25.isEmpty()) {
            Trees.ClassDefApi var26 = (Trees.ClassDefApi)var25.get();
            if (var26 != null) {
               Option var27 = c.mirror().universe().ClassDef().unapply(var26);
               if (!var27.isEmpty()) {
                  Trees.ModifiersApi mods = (Trees.ModifiersApi)((Tuple4)var27.get())._1();
                  Names.TypeNameApi name = (Names.TypeNameApi)((Tuple4)var27.get())._2();
                  List targs = (List)((Tuple4)var27.get())._3();
                  Trees.TemplateApi impl = (Trees.TemplateApi)((Tuple4)var27.get())._4();
                  List newParents = impl.parents().flatMap((treex) -> MODULE$.expandArity(c, order, bindings, treex));
                  Trees.TemplateApi newTemplate = c.mirror().universe().Template().apply(newParents, impl.self(), impl.body().flatMap((x) -> MODULE$.expandArity(c, order, bindings, x)));
                  List newTargs = targs.flatMap((arg) -> MODULE$.expandTypeDef(c, order, bindings, arg));
                  var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.ClassDefApi[]{c.mirror().universe().ClassDef().apply(mods, name, newTargs, newTemplate)})));
                  return (Seq)var5;
               }
            }
         }
      }

      if (tree != null) {
         Option var35 = c.mirror().universe().ValDefTag().unapply(tree);
         if (!var35.isEmpty()) {
            Trees.ValDefApi var36 = (Trees.ValDefApi)var35.get();
            if (var36 != null) {
               Option var37 = c.mirror().universe().ValDef().unapply(var36);
               if (!var37.isEmpty()) {
                  var5 = this.expandValDef(c, order, bindings, (Trees.ValDefApi)tree);
                  return (Seq)var5;
               }
            }
         }
      }

      if (tree != null) {
         Option var38 = c.mirror().universe().TypeDefTag().unapply(tree);
         if (!var38.isEmpty()) {
            Trees.TypeDefApi var39 = (Trees.TypeDefApi)var38.get();
            if (var39 != null) {
               Option var40 = c.mirror().universe().TypeDef().unapply(var39);
               if (!var40.isEmpty()) {
                  var5 = this.expandTypeDef(c, order, bindings, (Trees.TypeDefApi)tree);
                  return (Seq)var5;
               }
            }
         }
      }

      if (tree != null) {
         Option var41 = c.mirror().universe().AnnotatedTag().unapply(tree);
         if (!var41.isEmpty()) {
            Trees.AnnotatedApi var42 = (Trees.AnnotatedApi)var41.get();
            if (var42 != null) {
               Option var43 = c.mirror().universe().Annotated().unapply(var42);
               if (!var43.isEmpty()) {
                  Seq var6;
                  label395: {
                     Trees.TreeApi ann = (Trees.TreeApi)((Tuple2)var43.get())._1();
                     Trees.TreeApi tree = (Trees.TreeApi)((Tuple2)var43.get())._2();
                     if (ann != null) {
                        Option var47 = ((<undefinedtype>)(new Object(c) {
                           private final Context c$2;

                           public Option unapply(final Object tree) {
                              Object var2;
                              if (tree != null) {
                                 Option var4 = this.c$2.mirror().universe().TreeTag().unapply(tree);
                                 if (!var4.isEmpty()) {
                                    Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                                    if (var5 != null) {
                                       Option var6 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                                       if (!var6.isEmpty()) {
                                          List var7 = (List)((Tuple4)var6.get())._1();
                                          List var8 = (List)((Tuple4)var6.get())._2();
                                          Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                                          List var10 = (List)((Tuple4)var6.get())._4();
                                          if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                                             scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                                             Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                                             List var13 = var11.next$access$1();
                                             if (var12 != null) {
                                                Option var14 = this.c$2.mirror().universe().TreeTag().unapply(var12);
                                                if (!var14.isEmpty()) {
                                                   Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                   if (var15 != null) {
                                                      Some var16 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                                      if (!var16.isEmpty()) {
                                                         Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                         List var18 = (List)((Tuple2)var16.get())._2();
                                                         if (var17 != null) {
                                                            Option var19 = this.c$2.mirror().universe().TreeTag().unapply(var17);
                                                            if (!var19.isEmpty()) {
                                                               Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                               if (var20 != null) {
                                                                  Option var21 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var20);
                                                                  if (!var21.isEmpty()) {
                                                                     Trees.TreeApi var22 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                                     Names.TypeNameApi var23 = (Names.TypeNameApi)((Tuple2)var21.get())._2();
                                                                     if (var22 != null) {
                                                                        Option var24 = this.c$2.mirror().universe().IdentTag().unapply(var22);
                                                                        if (!var24.isEmpty()) {
                                                                           Trees.IdentApi var25 = (Trees.IdentApi)var24.get();
                                                                           if (var25 != null) {
                                                                              Option var26 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var25);
                                                                              if (!var26.isEmpty()) {
                                                                                 Names.TermNameApi var27 = (Names.TermNameApi)((Tuple2)var26.get())._1();
                                                                                 boolean var28 = ((Tuple2)var26.get())._2$mcZ$sp();
                                                                                 if (var27 != null) {
                                                                                    Option var29 = this.c$2.mirror().universe().TermNameTag().unapply(var27);
                                                                                    if (!var29.isEmpty()) {
                                                                                       Names.TermNameApi var30 = (Names.TermNameApi)var29.get();
                                                                                       if (var30 != null) {
                                                                                          Option var31 = this.c$2.mirror().universe().TermName().unapply(var30);
                                                                                          if (!var31.isEmpty()) {
                                                                                             String var32 = (String)var31.get();
                                                                                             if ("arityize".equals(var32) && !var28 && var23 != null) {
                                                                                                Option var33 = this.c$2.mirror().universe().TypeNameTag().unapply(var23);
                                                                                                if (!var33.isEmpty()) {
                                                                                                   Names.TypeNameApi var34 = (Names.TypeNameApi)var33.get();
                                                                                                   if (var34 != null) {
                                                                                                      Option var35 = this.c$2.mirror().universe().TypeName().unapply(var34);
                                                                                                      if (!var35.isEmpty()) {
                                                                                                         String var36 = (String)var35.get();
                                                                                                         if ("relative".equals(var36) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                                            scala.collection.immutable..colon.colon var37 = (scala.collection.immutable..colon.colon)var18;
                                                                                                            List var38 = (List)var37.head();
                                                                                                            List var39 = var37.next$access$1();
                                                                                                            if (var38 instanceof scala.collection.immutable..colon.colon) {
                                                                                                               scala.collection.immutable..colon.colon var40 = (scala.collection.immutable..colon.colon)var38;
                                                                                                               Trees.TreeApi qq$macro$1 = (Trees.TreeApi)var40.head();
                                                                                                               List var42 = var40.next$access$1();
                                                                                                               if (scala.collection.immutable.Nil..MODULE$.equals(var42) && scala.collection.immutable.Nil..MODULE$.equals(var39) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                                                                  label80: {
                                                                                                                     Trees.ValDefApi var10000 = this.c$2.mirror().universe().noSelfType();
                                                                                                                     if (var10000 == null) {
                                                                                                                        if (var9 != null) {
                                                                                                                           break label80;
                                                                                                                        }
                                                                                                                     } else if (!var10000.equals(var9)) {
                                                                                                                        break label80;
                                                                                                                     }

                                                                                                                     if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
                                                                                                                        var2 = new Some(qq$macro$1);
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

                              var2 = scala.None..MODULE$;
                              return (Option)var2;
                           }

                           public {
                              this.c$2 = c$2;
                           }
                        })).unapply(ann);
                        if (!var47.isEmpty()) {
                           Seq var9;
                           label396: {
                              Trees.TreeApi sym = (Trees.TreeApi)var47.get();
                              if (tree != null) {
                                 Option var50 = c.mirror().universe().IdentTag().unapply(tree);
                                 if (!var50.isEmpty()) {
                                    Trees.IdentApi var51 = (Trees.IdentApi)var50.get();
                                    if (var51 != null) {
                                       Option var52 = c.mirror().universe().Ident().unapply(var51);
                                       if (!var52.isEmpty()) {
                                          Names.NameApi nme = (Names.NameApi)var52.get();
                                          if (nme.isTypeName()) {
                                             var9 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.mirror().universe().Ident().apply((Names.NameApi)c.mirror().universe().newTypeName((new StringBuilder(0)).append(nme.encoded()).append(bindings.apply(sym.toString())).toString()))})));
                                             break label396;
                                          }
                                       }
                                    }
                                 }
                              }

                              if (tree != null) {
                                 Option var54 = c.mirror().universe().IdentTag().unapply(tree);
                                 if (!var54.isEmpty()) {
                                    Trees.IdentApi var55 = (Trees.IdentApi)var54.get();
                                    if (var55 != null) {
                                       Option var56 = c.mirror().universe().Ident().unapply(var55);
                                       if (!var56.isEmpty()) {
                                          Names.NameApi nme = (Names.NameApi)var56.get();
                                          if (nme.isTermName()) {
                                             var9 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{c.mirror().universe().Ident().apply((Names.NameApi)c.mirror().universe().newTermName((new StringBuilder(0)).append(nme.encoded()).append(bindings.apply(sym.toString())).toString()))})));
                                             break label396;
                                          }
                                       }
                                    }
                                 }
                              }

                              if (tree == null) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Option var58 = c.mirror().universe().AppliedTypeTreeTag().unapply(tree);
                              if (var58.isEmpty()) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Trees.AppliedTypeTreeApi var59 = (Trees.AppliedTypeTreeApi)var58.get();
                              if (var59 == null) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Option var60 = c.mirror().universe().AppliedTypeTree().unapply(var59);
                              if (var60.isEmpty()) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Trees.TreeApi var61 = (Trees.TreeApi)((Tuple2)var60.get())._1();
                              List targs = (List)((Tuple2)var60.get())._2();
                              if (var61 == null) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Option var63 = c.mirror().universe().IdentTag().unapply(var61);
                              if (var63.isEmpty()) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Trees.IdentApi var64 = (Trees.IdentApi)var63.get();
                              if (var64 == null) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Option var65 = c.mirror().universe().Ident().unapply(var64);
                              if (var65.isEmpty()) {
                                 throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                              }

                              Names.NameApi nme = (Names.NameApi)var65.get();
                              Trees.IdentApi newName = c.mirror().universe().Ident().apply((Names.NameApi)c.mirror().universe().newTypeName((new StringBuilder(0)).append(nme.encoded()).append(bindings.apply(sym.toString())).toString()));
                              List newTargs = targs.flatMap((arg) -> MODULE$.expandArity(c, order, bindings, arg));
                              var9 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.AppliedTypeTreeApi[]{c.mirror().universe().AppliedTypeTree().apply(newName, newTargs)})));
                           }

                           var6 = var9;
                           break label395;
                        }
                     }

                     if (ann != null && ((<undefinedtype>)(new Object(c) {
                        private final Context c$2;

                        public boolean unapply(final Object tree) {
                           boolean var2;
                           if (tree != null) {
                              Option var4 = this.c$2.mirror().universe().TreeTag().unapply(tree);
                              if (!var4.isEmpty()) {
                                 Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                                 if (var5 != null) {
                                    Option var6 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                                    if (!var6.isEmpty()) {
                                       List var7 = (List)((Tuple4)var6.get())._1();
                                       List var8 = (List)((Tuple4)var6.get())._2();
                                       Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                                       List var10 = (List)((Tuple4)var6.get())._4();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                                          scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                                          Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                                          List var13 = var11.next$access$1();
                                          if (var12 != null) {
                                             Option var14 = this.c$2.mirror().universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Option var16 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      Names.TypeNameApi var18 = (Names.TypeNameApi)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$2.mirror().universe().IdentTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.IdentApi var20 = (Trees.IdentApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Names.TermNameApi var22 = (Names.TermNameApi)((Tuple2)var21.get())._1();
                                                                  boolean var23 = ((Tuple2)var21.get())._2$mcZ$sp();
                                                                  if (var22 != null) {
                                                                     Option var24 = this.c$2.mirror().universe().TermNameTag().unapply(var22);
                                                                     if (!var24.isEmpty()) {
                                                                        Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Option var26 = this.c$2.mirror().universe().TermName().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              String var27 = (String)var26.get();
                                                                              if ("arityize".equals(var27) && !var23 && var18 != null) {
                                                                                 Option var28 = this.c$2.mirror().universe().TypeNameTag().unapply(var18);
                                                                                 if (!var28.isEmpty()) {
                                                                                    Names.TypeNameApi var29 = (Names.TypeNameApi)var28.get();
                                                                                    if (var29 != null) {
                                                                                       Option var30 = this.c$2.mirror().universe().TypeName().unapply(var29);
                                                                                       if (!var30.isEmpty()) {
                                                                                          String var31 = (String)var30.get();
                                                                                          if ("replicate".equals(var31) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                                             label66: {
                                                                                                Trees.ValDefApi var10000 = this.c$2.mirror().universe().noSelfType();
                                                                                                if (var10000 == null) {
                                                                                                   if (var9 != null) {
                                                                                                      break label66;
                                                                                                   }
                                                                                                } else if (!var10000.equals(var9)) {
                                                                                                   break label66;
                                                                                                }

                                                                                                if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
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

                           var2 = false;
                           return var2;
                        }

                        public {
                           this.c$2 = c$2;
                        }
                     })).unapply(ann)) {
                        Seq var8;
                        label397: {
                           if (tree != null) {
                              Option var70 = c.mirror().universe().IdentTag().unapply(tree);
                              if (!var70.isEmpty()) {
                                 Trees.IdentApi var71 = (Trees.IdentApi)var70.get();
                                 if (var71 != null) {
                                    Option var72 = c.mirror().universe().Ident().unapply(var71);
                                    if (!var72.isEmpty()) {
                                       Names.NameApi nme = (Names.NameApi)var72.get();
                                       if (nme.isTypeName()) {
                                          var8 = (Seq)scala.package..MODULE$.List().tabulate(order, (i) -> $anonfun$expandArity$8(c, nme, BoxesRunTime.unboxToInt(i)));
                                          break label397;
                                       }
                                    }
                                 }
                              }
                           }

                           if (tree == null) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Option var74 = c.mirror().universe().IdentTag().unapply(tree);
                           if (var74.isEmpty()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Trees.IdentApi var75 = (Trees.IdentApi)var74.get();
                           if (var75 == null) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Option var76 = c.mirror().universe().Ident().unapply(var75);
                           if (var76.isEmpty()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Names.NameApi nme = (Names.NameApi)var76.get();
                           if (!nme.isTermName()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           var8 = (Seq)scala.package..MODULE$.List().tabulate(order, (i) -> $anonfun$expandArity$9(c, nme, BoxesRunTime.unboxToInt(i)));
                        }

                        var6 = var8;
                     } else if (ann != null && ((<undefinedtype>)(new Object(c) {
                        private final Context c$2;

                        public boolean unapply(final Object tree) {
                           boolean var2;
                           if (tree != null) {
                              Option var4 = this.c$2.mirror().universe().TreeTag().unapply(tree);
                              if (!var4.isEmpty()) {
                                 Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                                 if (var5 != null) {
                                    Option var6 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                                    if (!var6.isEmpty()) {
                                       List var7 = (List)((Tuple4)var6.get())._1();
                                       List var8 = (List)((Tuple4)var6.get())._2();
                                       Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                                       List var10 = (List)((Tuple4)var6.get())._4();
                                       if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                                          scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                                          Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                                          List var13 = var11.next$access$1();
                                          if (var12 != null) {
                                             Option var14 = this.c$2.mirror().universe().TreeTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                                if (var15 != null) {
                                                   Option var16 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                      Names.TypeNameApi var18 = (Names.TypeNameApi)((Tuple2)var16.get())._2();
                                                      if (var17 != null) {
                                                         Option var19 = this.c$2.mirror().universe().IdentTag().unapply(var17);
                                                         if (!var19.isEmpty()) {
                                                            Trees.IdentApi var20 = (Trees.IdentApi)var19.get();
                                                            if (var20 != null) {
                                                               Option var21 = this.c$2.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var20);
                                                               if (!var21.isEmpty()) {
                                                                  Names.TermNameApi var22 = (Names.TermNameApi)((Tuple2)var21.get())._1();
                                                                  boolean var23 = ((Tuple2)var21.get())._2$mcZ$sp();
                                                                  if (var22 != null) {
                                                                     Option var24 = this.c$2.mirror().universe().TermNameTag().unapply(var22);
                                                                     if (!var24.isEmpty()) {
                                                                        Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                                        if (var25 != null) {
                                                                           Option var26 = this.c$2.mirror().universe().TermName().unapply(var25);
                                                                           if (!var26.isEmpty()) {
                                                                              String var27 = (String)var26.get();
                                                                              if ("arityize".equals(var27) && !var23 && var18 != null) {
                                                                                 Option var28 = this.c$2.mirror().universe().TypeNameTag().unapply(var18);
                                                                                 if (!var28.isEmpty()) {
                                                                                    Names.TypeNameApi var29 = (Names.TypeNameApi)var28.get();
                                                                                    if (var29 != null) {
                                                                                       Option var30 = this.c$2.mirror().universe().TypeName().unapply(var29);
                                                                                       if (!var30.isEmpty()) {
                                                                                          String var31 = (String)var30.get();
                                                                                          if ("repeat".equals(var31) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                                             label66: {
                                                                                                Trees.ValDefApi var10000 = this.c$2.mirror().universe().noSelfType();
                                                                                                if (var10000 == null) {
                                                                                                   if (var9 != null) {
                                                                                                      break label66;
                                                                                                   }
                                                                                                } else if (!var10000.equals(var9)) {
                                                                                                   break label66;
                                                                                                }

                                                                                                if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
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

                           var2 = false;
                           return var2;
                        }

                        public {
                           this.c$2 = c$2;
                        }
                     })).unapply(ann)) {
                        Seq var7;
                        label398: {
                           if (tree != null) {
                              Option var79 = c.mirror().universe().IdentTag().unapply(tree);
                              if (!var79.isEmpty()) {
                                 Trees.IdentApi var80 = (Trees.IdentApi)var79.get();
                                 if (var80 != null) {
                                    Option var81 = c.mirror().universe().Ident().unapply(var80);
                                    if (!var81.isEmpty()) {
                                       Names.NameApi nme = (Names.NameApi)var81.get();
                                       if (nme.isTypeName()) {
                                          var7 = (Seq)scala.package..MODULE$.List().fill(order, () -> tree);
                                          break label398;
                                       }
                                    }
                                 }
                              }
                           }

                           if (tree == null) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Option var83 = c.mirror().universe().IdentTag().unapply(tree);
                           if (var83.isEmpty()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Trees.IdentApi var84 = (Trees.IdentApi)var83.get();
                           if (var84 == null) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Option var85 = c.mirror().universe().Ident().unapply(var84);
                           if (var85.isEmpty()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           Names.NameApi nme = (Names.NameApi)var85.get();
                           if (!nme.isTermName()) {
                              throw scala.Predef..MODULE$.$qmark$qmark$qmark();
                           }

                           var7 = (Seq)scala.package..MODULE$.List().fill(order, () -> tree);
                        }

                        var6 = var7;
                     } else {
                        var6 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{tree})));
                     }
                  }

                  var5 = var6;
                  return (Seq)var5;
               }
            }
         }
      }

      if (tree != null) {
         Option var87 = c.mirror().universe().BlockTag().unapply(tree);
         if (!var87.isEmpty()) {
            Trees.BlockApi var88 = (Trees.BlockApi)var87.get();
            if (var88 != null) {
               Option var89 = c.mirror().universe().Block().unapply(var88);
               if (!var89.isEmpty()) {
                  List stats = (List)((Tuple2)var89.get())._1();
                  Trees.TreeApi ret = (Trees.TreeApi)((Tuple2)var89.get())._2();
                  var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.BlockApi[]{c.mirror().universe().Block().apply(stats.flatMap((st) -> MODULE$.expandArity(c, order, bindings, st)), (Trees.TreeApi)this.expandArity(c, order, bindings, ret).last())})));
                  return (Seq)var5;
               }
            }
         }
      }

      label477: {
         if (tree != null) {
            Option var92 = c.mirror().universe().IdentTag().unapply(tree);
            if (!var92.isEmpty()) {
               Trees.IdentApi var93 = (Trees.IdentApi)var92.get();
               if (var93 != null) {
                  Option var94 = c.mirror().universe().Ident().unapply(var93);
                  if (!var94.isEmpty()) {
                     Names.NameApi nme = (Names.NameApi)var94.get();
                     String var10000 = nme.encoded();
                     String var96 = "__order__";
                     if (var10000 == null) {
                        if (var96 == null) {
                           break label477;
                        }
                     } else if (var10000.equals(var96)) {
                        break label477;
                     }
                  }
               }
            }
         }

         if (tree != null) {
            Option var97 = c.mirror().universe().IdentTag().unapply(tree);
            if (!var97.isEmpty()) {
               Trees.IdentApi var98 = (Trees.IdentApi)var97.get();
               if (var98 != null) {
                  Option var99 = c.mirror().universe().Ident().unapply(var98);
                  if (!var99.isEmpty()) {
                     var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.IdentApi[]{(Trees.IdentApi)tree})));
                     return (Seq)var5;
                  }
               }
            }
         }

         if (tree != null) {
            Option var100 = c.mirror().universe().LiteralTag().unapply(tree);
            if (!var100.isEmpty()) {
               Trees.LiteralApi var101 = (Trees.LiteralApi)var100.get();
               if (var101 != null) {
                  Option var102 = c.mirror().universe().Literal().unapply(var101);
                  if (!var102.isEmpty()) {
                     var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.LiteralApi[]{(Trees.LiteralApi)tree})));
                     return (Seq)var5;
                  }
               }
            }
         }

         if (tree != null) {
            Option var103 = c.mirror().universe().ApplyTag().unapply(tree);
            if (!var103.isEmpty()) {
               Trees.ApplyApi var104 = (Trees.ApplyApi)var103.get();
               if (var104 != null) {
                  Option var105 = c.mirror().universe().Apply().unapply(var104);
                  if (!var105.isEmpty()) {
                     Trees.TreeApi who = (Trees.TreeApi)((Tuple2)var105.get())._1();
                     List args = (List)((Tuple2)var105.get())._2();
                     var5 = (Seq)((IterableOps)this.expandArity(c, order, bindings, who).map((w2) -> {
                        List args2 = args.flatMap((arg) -> MODULE$.expandArity(c, order, bindings, arg));
                        return new Tuple2(w2, args2);
                     })).map((x$3) -> {
                        if (x$3 != null) {
                           Trees.TreeApi w2 = (Trees.TreeApi)x$3._1();
                           List args2 = (List)x$3._2();
                           Trees.ApplyApi var2 = c.mirror().universe().Apply().apply(w2, args2);
                           return var2;
                        } else {
                           throw new MatchError(x$3);
                        }
                     });
                     return (Seq)var5;
                  }
               }
            }
         }

         if (tree != null) {
            Option var108 = c.mirror().universe().SelectTag().unapply(tree);
            if (!var108.isEmpty()) {
               Trees.SelectApi var109 = (Trees.SelectApi)var108.get();
               if (var109 != null) {
                  Option var110 = c.mirror().universe().Select().unapply(var109);
                  if (!var110.isEmpty()) {
                     Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var110.get())._1();
                     Names.NameApi name = (Names.NameApi)((Tuple2)var110.get())._2();
                     var5 = (Seq)this.expandArity(c, order, bindings, lhs).map((w2) -> c.mirror().universe().Select().apply(w2, name));
                     return (Seq)var5;
                  }
               }
            }
         }

         if (tree != null) {
            Option var113 = c.mirror().universe().AppliedTypeTreeTag().unapply(tree);
            if (!var113.isEmpty()) {
               Trees.AppliedTypeTreeApi var114 = (Trees.AppliedTypeTreeApi)var113.get();
               if (var114 != null) {
                  Option var115 = c.mirror().universe().AppliedTypeTree().unapply(var114);
                  if (!var115.isEmpty()) {
                     Trees.TreeApi lhs = (Trees.TreeApi)((Tuple2)var115.get())._1();
                     List targs = (List)((Tuple2)var115.get())._2();
                     Trees.TreeApi newLHS = (Trees.TreeApi)this.expandArity(c, order, bindings, lhs).head();
                     List newTargs = targs.flatMap((arg) -> MODULE$.expandArity(c, order, bindings, arg));
                     var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.AppliedTypeTreeApi[]{c.mirror().universe().AppliedTypeTree().apply(newLHS, newTargs)})));
                     return (Seq)var5;
                  }
               }
            }
         }

         if (tree != null) {
            Option var120 = c.mirror().universe().NewTag().unapply(tree);
            if (!var120.isEmpty()) {
               Trees.NewApi var121 = (Trees.NewApi)var120.get();
               if (var121 != null) {
                  Option var122 = c.mirror().universe().New().unapply(var121);
                  if (!var122.isEmpty()) {
                     Trees.TreeApi tree = (Trees.TreeApi)var122.get();
                     var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.NewApi[]{c.mirror().universe().New().apply((Trees.TreeApi)this.expandArity(c, order, bindings, tree).head())})));
                     return (Seq)var5;
                  }
               }
            }
         }

         var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TreeApi[]{tree})));
         return (Seq)var5;
      }

      var5 = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.LiteralApi[]{c.mirror().universe().Literal().apply(c.mirror().universe().Constant().apply(BoxesRunTime.boxToInteger(order)))})));
      return (Seq)var5;
   }

   public List expandValDef(final Context c, final int order, final Map bindings, final Trees.ValDefApi vdef) {
      List var10000;
      if (this.shouldExpand(c, vdef.mods())) {
         var10000 = (List)scala.package..MODULE$.List().tabulate(order, (i) -> $anonfun$expandValDef$1(bindings, vdef, c, order, BoxesRunTime.unboxToInt(i)));
      } else {
         Option var7 = this.shouldRelativize(c, vdef.mods());
         List var5;
         if (var7 instanceof Some) {
            Some var8 = (Some)var7;
            String x = (String)var8.value();
            Map newBindings = (Map)bindings.$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((Names.NameApi)vdef.name()).encoded()), bindings.apply(x)));
            Trees.TreeApi newTpt = (Trees.TreeApi)this.expandArity(c, order, newBindings, vdef.tpt()).head();
            var5 = (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.ValDefApi[]{c.mirror().universe().ValDef().apply(vdef.mods(), c.mirror().universe().newTermName((new StringBuilder(0)).append(((Names.NameApi)vdef.name()).encoded()).append(bindings.apply(x)).toString()), newTpt, vdef.rhs())})));
         } else {
            Trees.TreeApi newTpt = (Trees.TreeApi)this.expandArity(c, order, bindings, vdef.tpt()).head();
            var5 = (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.ValDefApi[]{c.mirror().universe().ValDef().apply(vdef.mods(), vdef.name(), newTpt, vdef.rhs())})));
         }

         var10000 = var5;
      }

      return var10000;
   }

   public List expandTypeDef(final Context c, final int order, final Map bindings, final Trees.TypeDefApi vdef) {
      List var10000;
      if (this.shouldExpand(c, vdef.mods())) {
         var10000 = (List)scala.package..MODULE$.List().tabulate(order, (i) -> $anonfun$expandTypeDef$1(c, vdef, BoxesRunTime.unboxToInt(i)));
      } else if (this.shouldRepeat(c, vdef.mods())) {
         var10000 = (List)scala.package..MODULE$.List().fill(order, () -> vdef);
      } else {
         Option var7 = this.shouldRelativize(c, vdef.mods());
         List var5;
         if (var7 instanceof Some) {
            Some var8 = (Some)var7;
            String x = (String)var8.value();
            var5 = (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TypeDefApi[]{c.mirror().universe().TypeDef().apply(vdef.mods(), c.mirror().universe().newTypeName((new StringBuilder(0)).append(((Names.NameApi)vdef.name()).encoded()).append(bindings.apply(x)).toString()), vdef.tparams(), vdef.rhs())})));
         } else {
            var5 = (List)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.TypeDefApi[]{vdef})));
         }

         var10000 = var5;
      }

      return var10000;
   }

   private boolean shouldExpand(final Context c, final Trees.ModifiersApi td) {
      return td.annotations().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$shouldExpand$1(c, x0$1)));
   }

   private boolean shouldRepeat(final Context c, final Trees.ModifiersApi td) {
      return td.annotations().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$shouldRepeat$1(c, x0$1)));
   }

   private Option shouldRelativize(final Context c, final Trees.ModifiersApi td) {
      return td.annotations().collectFirst(new Serializable(c) {
         private static final long serialVersionUID = 0L;
         public final Context c$7;

         public final Object applyOrElse(final Trees.TreeApi x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               Option var5 = ((<undefinedtype>)(new Object() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Option unapply(final Object tree) {
                     Object var2;
                     if (tree != null) {
                        Option var4 = this.$outer.c$7.mirror().universe().TreeTag().unapply(tree);
                        if (!var4.isEmpty()) {
                           Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                           if (var5 != null) {
                              Option var6 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                              if (!var6.isEmpty()) {
                                 List var7 = (List)((Tuple4)var6.get())._1();
                                 List var8 = (List)((Tuple4)var6.get())._2();
                                 Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                                 List var10 = (List)((Tuple4)var6.get())._4();
                                 if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                                    scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                                    Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                                    List var13 = var11.next$access$1();
                                    if (var12 != null) {
                                       Option var14 = this.$outer.c$7.mirror().universe().TreeTag().unapply(var12);
                                       if (!var14.isEmpty()) {
                                          Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                          if (var15 != null) {
                                             Some var16 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                             if (!var16.isEmpty()) {
                                                Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                List var18 = (List)((Tuple2)var16.get())._2();
                                                if (var17 != null) {
                                                   Option var19 = this.$outer.c$7.mirror().universe().TreeTag().unapply(var17);
                                                   if (!var19.isEmpty()) {
                                                      Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                      if (var20 != null) {
                                                         Option var21 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var20);
                                                         if (!var21.isEmpty()) {
                                                            Trees.TreeApi var22 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                            Names.TypeNameApi var23 = (Names.TypeNameApi)((Tuple2)var21.get())._2();
                                                            if (var22 != null) {
                                                               Option var24 = this.$outer.c$7.mirror().universe().IdentTag().unapply(var22);
                                                               if (!var24.isEmpty()) {
                                                                  Trees.IdentApi var25 = (Trees.IdentApi)var24.get();
                                                                  if (var25 != null) {
                                                                     Option var26 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var25);
                                                                     if (!var26.isEmpty()) {
                                                                        Names.TermNameApi var27 = (Names.TermNameApi)((Tuple2)var26.get())._1();
                                                                        boolean var28 = ((Tuple2)var26.get())._2$mcZ$sp();
                                                                        if (var27 != null) {
                                                                           Option var29 = this.$outer.c$7.mirror().universe().TermNameTag().unapply(var27);
                                                                           if (!var29.isEmpty()) {
                                                                              Names.TermNameApi var30 = (Names.TermNameApi)var29.get();
                                                                              if (var30 != null) {
                                                                                 Option var31 = this.$outer.c$7.mirror().universe().TermName().unapply(var30);
                                                                                 if (!var31.isEmpty()) {
                                                                                    String var32 = (String)var31.get();
                                                                                    if ("arityize".equals(var32) && !var28 && var23 != null) {
                                                                                       Option var33 = this.$outer.c$7.mirror().universe().TypeNameTag().unapply(var23);
                                                                                       if (!var33.isEmpty()) {
                                                                                          Names.TypeNameApi var34 = (Names.TypeNameApi)var33.get();
                                                                                          if (var34 != null) {
                                                                                             Option var35 = this.$outer.c$7.mirror().universe().TypeName().unapply(var34);
                                                                                             if (!var35.isEmpty()) {
                                                                                                String var36 = (String)var35.get();
                                                                                                if ("relative".equals(var36) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                                   scala.collection.immutable..colon.colon var37 = (scala.collection.immutable..colon.colon)var18;
                                                                                                   List var38 = (List)var37.head();
                                                                                                   List var39 = var37.next$access$1();
                                                                                                   if (var38 instanceof scala.collection.immutable..colon.colon) {
                                                                                                      scala.collection.immutable..colon.colon var40 = (scala.collection.immutable..colon.colon)var38;
                                                                                                      Trees.TreeApi qq$macro$1 = (Trees.TreeApi)var40.head();
                                                                                                      List var42 = var40.next$access$1();
                                                                                                      if (scala.collection.immutable.Nil..MODULE$.equals(var42) && scala.collection.immutable.Nil..MODULE$.equals(var39) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                                                         label80: {
                                                                                                            Trees.ValDefApi var10000 = this.$outer.c$7.mirror().universe().noSelfType();
                                                                                                            if (var10000 == null) {
                                                                                                               if (var9 != null) {
                                                                                                                  break label80;
                                                                                                               }
                                                                                                            } else if (!var10000.equals(var9)) {
                                                                                                               break label80;
                                                                                                            }

                                                                                                            if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
                                                                                                               var2 = new Some(qq$macro$1);
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

                     var2 = scala.None..MODULE$;
                     return (Option)var2;
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               })).unapply(x1);
               if (!var5.isEmpty()) {
                  Trees.TreeApi q = (Trees.TreeApi)var5.get();
                  var3 = q.toString();
                  return var3;
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Trees.TreeApi x1) {
            boolean var2;
            if (x1 != null) {
               Option var4 = ((<undefinedtype>)(new Object() {
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public Option unapply(final Object tree) {
                     Object var2;
                     if (tree != null) {
                        Option var4 = this.$outer.c$7.mirror().universe().TreeTag().unapply(tree);
                        if (!var4.isEmpty()) {
                           Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                           if (var5 != null) {
                              Option var6 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                              if (!var6.isEmpty()) {
                                 List var7 = (List)((Tuple4)var6.get())._1();
                                 List var8 = (List)((Tuple4)var6.get())._2();
                                 Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                                 List var10 = (List)((Tuple4)var6.get())._4();
                                 if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                                    scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                                    Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                                    List var13 = var11.next$access$1();
                                    if (var12 != null) {
                                       Option var14 = this.$outer.c$7.mirror().universe().TreeTag().unapply(var12);
                                       if (!var14.isEmpty()) {
                                          Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                          if (var15 != null) {
                                             Some var16 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticApplied().unapply(var15);
                                             if (!var16.isEmpty()) {
                                                Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                                List var18 = (List)((Tuple2)var16.get())._2();
                                                if (var17 != null) {
                                                   Option var19 = this.$outer.c$7.mirror().universe().TreeTag().unapply(var17);
                                                   if (!var19.isEmpty()) {
                                                      Trees.TreeApi var20 = (Trees.TreeApi)var19.get();
                                                      if (var20 != null) {
                                                         Option var21 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var20);
                                                         if (!var21.isEmpty()) {
                                                            Trees.TreeApi var22 = (Trees.TreeApi)((Tuple2)var21.get())._1();
                                                            Names.TypeNameApi var23 = (Names.TypeNameApi)((Tuple2)var21.get())._2();
                                                            if (var22 != null) {
                                                               Option var24 = this.$outer.c$7.mirror().universe().IdentTag().unapply(var22);
                                                               if (!var24.isEmpty()) {
                                                                  Trees.IdentApi var25 = (Trees.IdentApi)var24.get();
                                                                  if (var25 != null) {
                                                                     Option var26 = this.$outer.c$7.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var25);
                                                                     if (!var26.isEmpty()) {
                                                                        Names.TermNameApi var27 = (Names.TermNameApi)((Tuple2)var26.get())._1();
                                                                        boolean var28 = ((Tuple2)var26.get())._2$mcZ$sp();
                                                                        if (var27 != null) {
                                                                           Option var29 = this.$outer.c$7.mirror().universe().TermNameTag().unapply(var27);
                                                                           if (!var29.isEmpty()) {
                                                                              Names.TermNameApi var30 = (Names.TermNameApi)var29.get();
                                                                              if (var30 != null) {
                                                                                 Option var31 = this.$outer.c$7.mirror().universe().TermName().unapply(var30);
                                                                                 if (!var31.isEmpty()) {
                                                                                    String var32 = (String)var31.get();
                                                                                    if ("arityize".equals(var32) && !var28 && var23 != null) {
                                                                                       Option var33 = this.$outer.c$7.mirror().universe().TypeNameTag().unapply(var23);
                                                                                       if (!var33.isEmpty()) {
                                                                                          Names.TypeNameApi var34 = (Names.TypeNameApi)var33.get();
                                                                                          if (var34 != null) {
                                                                                             Option var35 = this.$outer.c$7.mirror().universe().TypeName().unapply(var34);
                                                                                             if (!var35.isEmpty()) {
                                                                                                String var36 = (String)var35.get();
                                                                                                if ("relative".equals(var36) && var18 instanceof scala.collection.immutable..colon.colon) {
                                                                                                   scala.collection.immutable..colon.colon var37 = (scala.collection.immutable..colon.colon)var18;
                                                                                                   List var38 = (List)var37.head();
                                                                                                   List var39 = var37.next$access$1();
                                                                                                   if (var38 instanceof scala.collection.immutable..colon.colon) {
                                                                                                      scala.collection.immutable..colon.colon var40 = (scala.collection.immutable..colon.colon)var38;
                                                                                                      Trees.TreeApi qq$macro$1 = (Trees.TreeApi)var40.head();
                                                                                                      List var42 = var40.next$access$1();
                                                                                                      if (scala.collection.immutable.Nil..MODULE$.equals(var42) && scala.collection.immutable.Nil..MODULE$.equals(var39) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                                                         label80: {
                                                                                                            Trees.ValDefApi var10000 = this.$outer.c$7.mirror().universe().noSelfType();
                                                                                                            if (var10000 == null) {
                                                                                                               if (var9 != null) {
                                                                                                                  break label80;
                                                                                                               }
                                                                                                            } else if (!var10000.equals(var9)) {
                                                                                                               break label80;
                                                                                                            }

                                                                                                            if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
                                                                                                               var2 = new Some(qq$macro$1);
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

                     var2 = scala.None..MODULE$;
                     return (Option)var2;
                  }

                  public {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                     }
                  }
               })).unapply(x1);
               if (!var4.isEmpty()) {
                  var2 = true;
                  return var2;
               }
            }

            var2 = false;
            return var2;
         }

         public {
            this.c$7 = c$7;
         }
      });
   }

   private int extractOrder(final Context c) {
      int order = BoxesRunTime.unboxToInt(c.macroApplication().collect(new Serializable(c) {
         private static final long serialVersionUID = 0L;
         private final Context c$8;

         public final Object applyOrElse(final Trees.TreeApi x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               Option var5 = this.c$8.mirror().universe().LiteralTag().unapply(x1);
               if (!var5.isEmpty()) {
                  Trees.LiteralApi var6 = (Trees.LiteralApi)var5.get();
                  if (var6 != null) {
                     Option var7 = this.c$8.mirror().universe().Literal().unapply(var6);
                     if (!var7.isEmpty()) {
                        Constants.ConstantApi x = (Constants.ConstantApi)var7.get();
                        if (x.value() instanceof Integer) {
                           var3 = BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x.value().toString())));
                           return var3;
                        }
                     }
                  }
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Trees.TreeApi x1) {
            boolean var2;
            if (x1 != null) {
               Option var4 = this.c$8.mirror().universe().LiteralTag().unapply(x1);
               if (!var4.isEmpty()) {
                  Trees.LiteralApi var5 = (Trees.LiteralApi)var4.get();
                  if (var5 != null) {
                     Option var6 = this.c$8.mirror().universe().Literal().unapply(var5);
                     if (!var6.isEmpty()) {
                        Constants.ConstantApi x = (Constants.ConstantApi)var6.get();
                        if (x.value() instanceof Integer) {
                           var2 = true;
                           return var2;
                        }
                     }
                  }
               }
            }

            var2 = false;
            return var2;
         }

         public {
            this.c$8 = c$8;
         }
      }).head());
      return order;
   }

   // $FF: synthetic method
   public static final Trees.ClassDefApi $anonfun$arityizeImpl$1(final Names.TypeNameApi name$1, final Context c$1, final Trees.TemplateApi impl$1, final List targs$1, final Trees.ModifiersApi mods$1, final int order) {
      Map bindings = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((Names.NameApi)name$1).encoded()), BoxesRunTime.boxToInteger(order))})));
      Trees.TemplateApi newTemplate = c$1.mirror().universe().Template().apply(impl$1.parents(), impl$1.self(), impl$1.body().flatMap((x) -> MODULE$.expandArity(c$1, order, bindings, x)));
      List newTargs = targs$1.flatMap((arg) -> MODULE$.expandTypeDef(c$1, order, bindings, arg));
      return c$1.mirror().universe().ClassDef().apply(mods$1, c$1.mirror().universe().newTypeName((new StringBuilder(0)).append(((Names.NameApi)name$1).encoded()).append(order).toString()), newTargs, newTemplate);
   }

   // $FF: synthetic method
   public static final Trees.DefDefApi $anonfun$arityizeImpl$4(final Names.TermNameApi name$2, final Context c$1, final Trees.TreeApi impl$2, final List vargs$1, final List targs$2, final Trees.TreeApi tpt$1, final Trees.ModifiersApi mods$2, final int order) {
      Map bindings = (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((Names.NameApi)name$2).encoded()), BoxesRunTime.boxToInteger(order))})));
      Trees.TreeApi newImpl = (Trees.TreeApi)MODULE$.expandArity(c$1, order, bindings, impl$2).head();
      List newVargs = vargs$1.map((x$1) -> x$1.flatMap((arg) -> MODULE$.expandValDef(c$1, order, bindings, arg)));
      List newTargs = targs$2.flatMap((arg) -> MODULE$.expandTypeDef(c$1, order, bindings, arg));
      Trees.TreeApi newRet = (Trees.TreeApi)MODULE$.expandArity(c$1, order, bindings, tpt$1).head();
      return c$1.mirror().universe().DefDef().apply(mods$2, c$1.mirror().universe().newTermName((new StringBuilder(0)).append(((Names.NameApi)name$2).encoded()).append(order).toString()), newTargs, newVargs, newRet, newImpl);
   }

   // $FF: synthetic method
   public static final Trees.IdentApi $anonfun$expandArity$8(final Context c$2, final Names.NameApi nme$1, final int i) {
      return c$2.mirror().universe().Ident().apply((Names.NameApi)c$2.mirror().universe().newTypeName((new StringBuilder(0)).append(nme$1.encoded()).append(i + 1).toString()));
   }

   // $FF: synthetic method
   public static final Trees.IdentApi $anonfun$expandArity$9(final Context c$2, final Names.NameApi nme$2, final int i) {
      return c$2.mirror().universe().Ident().apply((Names.NameApi)c$2.mirror().universe().newTermName((new StringBuilder(0)).append(nme$2.encoded()).append(i + 1).toString()));
   }

   // $FF: synthetic method
   public static final Trees.ValDefApi $anonfun$expandValDef$1(final Map bindings$4, final Trees.ValDefApi vdef$1$1, final Context c$3, final int order$4, final int i) {
      Map newBindings = (Map)bindings$4.$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(((Names.NameApi)vdef$1$1.name()).encoded()), BoxesRunTime.boxToInteger(i + 1)));
      return c$3.mirror().universe().ValDef().apply(vdef$1$1.mods(), c$3.mirror().universe().newTermName((new StringBuilder(0)).append(((Names.NameApi)vdef$1$1.name()).encoded()).append(i + 1).toString()), (Trees.TreeApi)MODULE$.expandArity(c$3, order$4, newBindings, vdef$1$1.tpt()).head(), vdef$1$1.rhs());
   }

   // $FF: synthetic method
   public static final Trees.TypeDefApi $anonfun$expandTypeDef$1(final Context c$4, final Trees.TypeDefApi vdef$2$1, final int i) {
      return c$4.mirror().universe().TypeDef().apply(vdef$2$1.mods(), c$4.mirror().universe().newTypeName((new StringBuilder(0)).append(((Names.NameApi)vdef$2$1.name()).encoded()).append(i + 1).toString()), vdef$2$1.tparams(), vdef$2$1.rhs());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$shouldExpand$1(final Context c$5, final Trees.TreeApi x0$1) {
      boolean var2;
      if (x0$1 != null && ((<undefinedtype>)(new Object(c$5) {
         private final Context c$5;

         public boolean unapply(final Object tree) {
            boolean var2;
            if (tree != null) {
               Option var4 = this.c$5.mirror().universe().TreeTag().unapply(tree);
               if (!var4.isEmpty()) {
                  Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                  if (var5 != null) {
                     Option var6 = this.c$5.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                     if (!var6.isEmpty()) {
                        List var7 = (List)((Tuple4)var6.get())._1();
                        List var8 = (List)((Tuple4)var6.get())._2();
                        Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                        List var10 = (List)((Tuple4)var6.get())._4();
                        if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                           scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                           Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                           List var13 = var11.next$access$1();
                           if (var12 != null) {
                              Option var14 = this.c$5.mirror().universe().TreeTag().unapply(var12);
                              if (!var14.isEmpty()) {
                                 Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                 if (var15 != null) {
                                    Option var16 = this.c$5.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var15);
                                    if (!var16.isEmpty()) {
                                       Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                       Names.TypeNameApi var18 = (Names.TypeNameApi)((Tuple2)var16.get())._2();
                                       if (var17 != null) {
                                          Option var19 = this.c$5.mirror().universe().IdentTag().unapply(var17);
                                          if (!var19.isEmpty()) {
                                             Trees.IdentApi var20 = (Trees.IdentApi)var19.get();
                                             if (var20 != null) {
                                                Option var21 = this.c$5.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var20);
                                                if (!var21.isEmpty()) {
                                                   Names.TermNameApi var22 = (Names.TermNameApi)((Tuple2)var21.get())._1();
                                                   boolean var23 = ((Tuple2)var21.get())._2$mcZ$sp();
                                                   if (var22 != null) {
                                                      Option var24 = this.c$5.mirror().universe().TermNameTag().unapply(var22);
                                                      if (!var24.isEmpty()) {
                                                         Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                         if (var25 != null) {
                                                            Option var26 = this.c$5.mirror().universe().TermName().unapply(var25);
                                                            if (!var26.isEmpty()) {
                                                               String var27 = (String)var26.get();
                                                               if ("arityize".equals(var27) && !var23 && var18 != null) {
                                                                  Option var28 = this.c$5.mirror().universe().TypeNameTag().unapply(var18);
                                                                  if (!var28.isEmpty()) {
                                                                     Names.TypeNameApi var29 = (Names.TypeNameApi)var28.get();
                                                                     if (var29 != null) {
                                                                        Option var30 = this.c$5.mirror().universe().TypeName().unapply(var29);
                                                                        if (!var30.isEmpty()) {
                                                                           String var31 = (String)var30.get();
                                                                           if ("replicate".equals(var31) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                              label66: {
                                                                                 Trees.ValDefApi var10000 = this.c$5.mirror().universe().noSelfType();
                                                                                 if (var10000 == null) {
                                                                                    if (var9 != null) {
                                                                                       break label66;
                                                                                    }
                                                                                 } else if (!var10000.equals(var9)) {
                                                                                    break label66;
                                                                                 }

                                                                                 if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
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

            var2 = false;
            return var2;
         }

         public {
            this.c$5 = c$5;
         }
      })).unapply(x0$1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$shouldRepeat$1(final Context c$6, final Trees.TreeApi x0$1) {
      boolean var2;
      if (x0$1 != null && ((<undefinedtype>)(new Object(c$6) {
         private final Context c$6;

         public boolean unapply(final Object tree) {
            boolean var2;
            if (tree != null) {
               Option var4 = this.c$6.mirror().universe().TreeTag().unapply(tree);
               if (!var4.isEmpty()) {
                  Trees.TreeApi var5 = (Trees.TreeApi)var4.get();
                  if (var5 != null) {
                     Option var6 = this.c$6.mirror().universe().internal().reificationSupport().SyntacticNew().unapply(var5);
                     if (!var6.isEmpty()) {
                        List var7 = (List)((Tuple4)var6.get())._1();
                        List var8 = (List)((Tuple4)var6.get())._2();
                        Trees.ValDefApi var9 = (Trees.ValDefApi)((Tuple4)var6.get())._3();
                        List var10 = (List)((Tuple4)var6.get())._4();
                        if (scala.collection.immutable.Nil..MODULE$.equals(var7) && var8 instanceof scala.collection.immutable..colon.colon) {
                           scala.collection.immutable..colon.colon var11 = (scala.collection.immutable..colon.colon)var8;
                           Trees.TreeApi var12 = (Trees.TreeApi)var11.head();
                           List var13 = var11.next$access$1();
                           if (var12 != null) {
                              Option var14 = this.c$6.mirror().universe().TreeTag().unapply(var12);
                              if (!var14.isEmpty()) {
                                 Trees.TreeApi var15 = (Trees.TreeApi)var14.get();
                                 if (var15 != null) {
                                    Option var16 = this.c$6.mirror().universe().internal().reificationSupport().SyntacticSelectType().unapply(var15);
                                    if (!var16.isEmpty()) {
                                       Trees.TreeApi var17 = (Trees.TreeApi)((Tuple2)var16.get())._1();
                                       Names.TypeNameApi var18 = (Names.TypeNameApi)((Tuple2)var16.get())._2();
                                       if (var17 != null) {
                                          Option var19 = this.c$6.mirror().universe().IdentTag().unapply(var17);
                                          if (!var19.isEmpty()) {
                                             Trees.IdentApi var20 = (Trees.IdentApi)var19.get();
                                             if (var20 != null) {
                                                Option var21 = this.c$6.mirror().universe().internal().reificationSupport().SyntacticTermIdent().unapply(var20);
                                                if (!var21.isEmpty()) {
                                                   Names.TermNameApi var22 = (Names.TermNameApi)((Tuple2)var21.get())._1();
                                                   boolean var23 = ((Tuple2)var21.get())._2$mcZ$sp();
                                                   if (var22 != null) {
                                                      Option var24 = this.c$6.mirror().universe().TermNameTag().unapply(var22);
                                                      if (!var24.isEmpty()) {
                                                         Names.TermNameApi var25 = (Names.TermNameApi)var24.get();
                                                         if (var25 != null) {
                                                            Option var26 = this.c$6.mirror().universe().TermName().unapply(var25);
                                                            if (!var26.isEmpty()) {
                                                               String var27 = (String)var26.get();
                                                               if ("arityize".equals(var27) && !var23 && var18 != null) {
                                                                  Option var28 = this.c$6.mirror().universe().TypeNameTag().unapply(var18);
                                                                  if (!var28.isEmpty()) {
                                                                     Names.TypeNameApi var29 = (Names.TypeNameApi)var28.get();
                                                                     if (var29 != null) {
                                                                        Option var30 = this.c$6.mirror().universe().TypeName().unapply(var29);
                                                                        if (!var30.isEmpty()) {
                                                                           String var31 = (String)var30.get();
                                                                           if ("repeat".equals(var31) && scala.collection.immutable.Nil..MODULE$.equals(var13)) {
                                                                              label66: {
                                                                                 Trees.ValDefApi var10000 = this.c$6.mirror().universe().noSelfType();
                                                                                 if (var10000 == null) {
                                                                                    if (var9 != null) {
                                                                                       break label66;
                                                                                    }
                                                                                 } else if (!var10000.equals(var9)) {
                                                                                    break label66;
                                                                                 }

                                                                                 if (scala.collection.immutable.Nil..MODULE$.equals(var10)) {
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

            var2 = false;
            return var2;
         }

         public {
            this.c$6 = c$6;
         }
      })).unapply(x0$1)) {
         var2 = true;
      } else {
         var2 = false;
      }

      return var2;
   }

   private arityize$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
