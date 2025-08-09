package spire.macros;

import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.api.Constants;
import scala.reflect.api.Exprs;
import scala.reflect.api.Mirror;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxesRunTime;
import scala.util.Either;
import scala.util.Left;
import scala.util.Right;
import spire.math.Rational;
import spire.math.Rational$;
import spire.math.SafeLong;

public final class Macros$ {
   public static final Macros$ MODULE$ = new Macros$();

   public Either parseContext(final Context c, final BigInt lower, final BigInt upper) {
      return this.parseNumber((new Macros.LiteralUtil(c)).getString(), lower, upper);
   }

   public Either parseNumber(final String s, final BigInt lower, final BigInt upper) {
      Object var10000;
      try {
         BigInt n = .MODULE$.BigInt().apply(s);
         var10000 = !n.$less(lower) && !n.$greater(upper) ? .MODULE$.Right().apply(n) : .MODULE$.Left().apply(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal constant: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{s})));
      } catch (Exception var5) {
         var10000 = .MODULE$.Left().apply(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal constant: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{s})));
      }

      return (Either)var10000;
   }

   public Exprs.Expr byte(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(-128), .MODULE$.BigInt().apply(255));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().Liftable().liftByte().apply(BoxesRunTime.boxToByte(n.toByte())), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr ubyte(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(0), .MODULE$.BigInt().apply(255));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("UByte")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftByte().apply(BoxesRunTime.boxToByte(n.toByte())), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr short(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(-32768), .MODULE$.BigInt().apply(65535));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().Liftable().liftShort().apply(BoxesRunTime.boxToShort(n.toShort())), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr ushort(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(0), .MODULE$.BigInt().apply(65535));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("UShort")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftShort().apply(BoxesRunTime.boxToShort(n.toShort())), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr uint(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(0), .MODULE$.BigInt().apply(4294967295L));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("UInt")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftInt().apply(BoxesRunTime.boxToInteger(n.toInt())), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr ulong(final Context c) {
      Either var3 = this.parseContext(c, .MODULE$.BigInt().apply(0), .MODULE$.BigInt().apply("18446744073709551615"));
      if (var3 instanceof Right) {
         Right var4 = (Right)var3;
         BigInt n = (BigInt)var4.value();
         Exprs.Expr var2 = c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("ULong")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftLong().apply(BoxesRunTime.boxToLong(n.toLong())), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing());
         return var2;
      } else if (var3 instanceof Left) {
         Left var6 = (Left)var3;
         String s = (String)var6.value();
         throw new NumberFormatException(s);
      } else {
         throw new MatchError(var3);
      }
   }

   public Exprs.Expr rational(final Context c) {
      Trees.TreeApi var5 = c.prefix().tree();
      if (var5 != null) {
         Option var6 = c.universe().ApplyTag().unapply(var5);
         if (!var6.isEmpty()) {
            Trees.ApplyApi var7 = (Trees.ApplyApi)var6.get();
            if (var7 != null) {
               Option var8 = c.universe().Apply().unapply(var7);
               if (!var8.isEmpty()) {
                  List var9 = (List)((Tuple2)var8.get())._2();
                  if (var9 != null) {
                     SeqOps var10 = .MODULE$.List().unapplySeq(var9);
                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var10) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var10)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var10), 1) == 0) {
                        Trees.TreeApi var11 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var10), 0);
                        if (var11 != null) {
                           Option var12 = c.universe().ApplyTag().unapply(var11);
                           if (!var12.isEmpty()) {
                              Trees.ApplyApi var13 = (Trees.ApplyApi)var12.get();
                              if (var13 != null) {
                                 Option var14 = c.universe().Apply().unapply(var13);
                                 if (!var14.isEmpty()) {
                                    List var15 = (List)((Tuple2)var14.get())._2();
                                    if (var15 != null) {
                                       SeqOps var16 = .MODULE$.List().unapplySeq(var15);
                                       if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var16) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 1) == 0) {
                                          Trees.TreeApi var17 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 0);
                                          if (var17 != null) {
                                             Option var18 = c.universe().LiteralTag().unapply(var17);
                                             if (!var18.isEmpty()) {
                                                Trees.LiteralApi var19 = (Trees.LiteralApi)var18.get();
                                                if (var19 != null) {
                                                   Option var20 = c.universe().Literal().unapply(var19);
                                                   if (!var20.isEmpty()) {
                                                      Constants.ConstantApi var21 = (Constants.ConstantApi)var20.get();
                                                      if (var21 != null) {
                                                         Option var22 = c.universe().ConstantTag().unapply(var21);
                                                         if (!var22.isEmpty()) {
                                                            Constants.ConstantApi var23 = (Constants.ConstantApi)var22.get();
                                                            if (var23 != null) {
                                                               Option var24 = c.universe().Constant().unapply(var23);
                                                               if (!var24.isEmpty()) {
                                                                  Object s = var24.get();
                                                                  if (s instanceof String) {
                                                                     String var26 = (String)s;
                                                                     Rational r = Rational$.MODULE$.apply(var26);
                                                                     Tuple2 var29 = new Tuple2(r.numerator(), r.denominator());
                                                                     if (var29 == null) {
                                                                        throw new MatchError(var29);
                                                                     }

                                                                     SafeLong n = (SafeLong)var29._1();
                                                                     SafeLong d = (SafeLong)var29._2();
                                                                     Tuple2 var2 = new Tuple2(n, d);
                                                                     SafeLong n = (SafeLong)var2._1();
                                                                     SafeLong d = (SafeLong)var2._2();
                                                                     return n.isValidLong() && d.isValidLong() ? c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("Rational")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftLong().apply(BoxesRunTime.boxToLong(n.toLong())), new scala.collection.immutable..colon.colon(c.universe().Liftable().liftLong().apply(BoxesRunTime.boxToLong(d.toLong())), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing()) : c.Expr(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("spire")), c.universe().TermName().apply("math")), c.universe().TermName().apply("Rational")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("BigInt"), false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftString().apply(n.toString()), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), new scala.collection.immutable..colon.colon(c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("BigInt"), false), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(c.universe().Liftable().liftString().apply(d.toString()), scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), scala.collection.immutable.Nil..MODULE$))), c.universe().WeakTypeTag().Nothing());
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

      throw new MatchError(var5);
   }

   public String formatWhole(final Context c, final String sep) {
      String var10000;
      label95: {
         label94: {
            String var5 = ".";
            if (sep == null) {
               if (var5 == null) {
                  break label94;
               }
            } else if (sep.equals(var5)) {
               break label94;
            }

            var10000 = sep;
            break label95;
         }

         var10000 = "\\.";
      }

      String esep = var10000;
      String regex = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("(0|-?[1-9][0-9]{0,2}(%s[0-9]{3})*)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{esep}));
      Trees.TreeApi var8 = c.prefix().tree();
      if (var8 != null) {
         Option var9 = c.universe().ApplyTag().unapply(var8);
         if (!var9.isEmpty()) {
            Trees.ApplyApi var10 = (Trees.ApplyApi)var9.get();
            if (var10 != null) {
               Option var11 = c.universe().Apply().unapply(var10);
               if (!var11.isEmpty()) {
                  List var12 = (List)((Tuple2)var11.get())._2();
                  if (var12 != null) {
                     SeqOps var13 = .MODULE$.List().unapplySeq(var12);
                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1) == 0) {
                        Trees.TreeApi var14 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
                        if (var14 != null) {
                           Option var15 = c.universe().ApplyTag().unapply(var14);
                           if (!var15.isEmpty()) {
                              Trees.ApplyApi var16 = (Trees.ApplyApi)var15.get();
                              if (var16 != null) {
                                 Option var17 = c.universe().Apply().unapply(var16);
                                 if (!var17.isEmpty()) {
                                    List var18 = (List)((Tuple2)var17.get())._2();
                                    if (var18 != null) {
                                       SeqOps var19 = .MODULE$.List().unapplySeq(var18);
                                       if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var19) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19), 1) == 0) {
                                          Trees.TreeApi var20 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var19), 0);
                                          if (var20 != null) {
                                             Option var21 = c.universe().LiteralTag().unapply(var20);
                                             if (!var21.isEmpty()) {
                                                Trees.LiteralApi var22 = (Trees.LiteralApi)var21.get();
                                                if (var22 != null) {
                                                   Option var23 = c.universe().Literal().unapply(var22);
                                                   if (!var23.isEmpty()) {
                                                      Constants.ConstantApi var24 = (Constants.ConstantApi)var23.get();
                                                      if (var24 != null) {
                                                         Option var25 = c.universe().ConstantTag().unapply(var24);
                                                         if (!var25.isEmpty()) {
                                                            Constants.ConstantApi var26 = (Constants.ConstantApi)var25.get();
                                                            if (var26 != null) {
                                                               Option var27 = c.universe().Constant().unapply(var26);
                                                               if (!var27.isEmpty()) {
                                                                  Object s = var27.get();
                                                                  if (s instanceof String) {
                                                                     String var29 = (String)s;
                                                                     if (!var29.matches(regex)) {
                                                                        c.error(c.enclosingPosition(), "invalid whole number");
                                                                     }

                                                                     return var29.replace(sep, "");
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

      throw new MatchError(var8);
   }

   public String formatDecimal(final Context c, final String sep, final String dec) {
      String var10000;
      label109: {
         label108: {
            String var6 = ".";
            if (sep == null) {
               if (var6 == null) {
                  break label108;
               }
            } else if (sep.equals(var6)) {
               break label108;
            }

            var10000 = sep;
            break label109;
         }

         var10000 = "\\.";
      }

      String esep;
      label101: {
         label100: {
            esep = var10000;
            String var8 = ".";
            if (dec == null) {
               if (var8 == null) {
                  break label100;
               }
            } else if (dec.equals(var8)) {
               break label100;
            }

            var10000 = dec;
            break label101;
         }

         var10000 = "\\.";
      }

      String edec = var10000;
      String regex = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("-?(0|[1-9][0-9]{0,2}(%s[0-9]{3})*)(%s[0-9]+)?"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{esep, edec}));
      Trees.TreeApi var11 = c.prefix().tree();
      if (var11 != null) {
         Option var12 = c.universe().ApplyTag().unapply(var11);
         if (!var12.isEmpty()) {
            Trees.ApplyApi var13 = (Trees.ApplyApi)var12.get();
            if (var13 != null) {
               Option var14 = c.universe().Apply().unapply(var13);
               if (!var14.isEmpty()) {
                  List var15 = (List)((Tuple2)var14.get())._2();
                  if (var15 != null) {
                     SeqOps var16 = .MODULE$.List().unapplySeq(var15);
                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var16) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 1) == 0) {
                        Trees.TreeApi var17 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var16), 0);
                        if (var17 != null) {
                           Option var18 = c.universe().ApplyTag().unapply(var17);
                           if (!var18.isEmpty()) {
                              Trees.ApplyApi var19 = (Trees.ApplyApi)var18.get();
                              if (var19 != null) {
                                 Option var20 = c.universe().Apply().unapply(var19);
                                 if (!var20.isEmpty()) {
                                    List var21 = (List)((Tuple2)var20.get())._2();
                                    if (var21 != null) {
                                       SeqOps var22 = .MODULE$.List().unapplySeq(var21);
                                       if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var22) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var22)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var22), 1) == 0) {
                                          Trees.TreeApi var23 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var22), 0);
                                          if (var23 != null) {
                                             Option var24 = c.universe().LiteralTag().unapply(var23);
                                             if (!var24.isEmpty()) {
                                                Trees.LiteralApi var25 = (Trees.LiteralApi)var24.get();
                                                if (var25 != null) {
                                                   Option var26 = c.universe().Literal().unapply(var25);
                                                   if (!var26.isEmpty()) {
                                                      Constants.ConstantApi var27 = (Constants.ConstantApi)var26.get();
                                                      if (var27 != null) {
                                                         Option var28 = c.universe().ConstantTag().unapply(var27);
                                                         if (!var28.isEmpty()) {
                                                            Constants.ConstantApi var29 = (Constants.ConstantApi)var28.get();
                                                            if (var29 != null) {
                                                               Option var30 = c.universe().Constant().unapply(var29);
                                                               if (!var30.isEmpty()) {
                                                                  Object s = var30.get();
                                                                  if (s instanceof String) {
                                                                     String var32 = (String)s;
                                                                     if (!var32.matches(regex)) {
                                                                        c.error(c.enclosingPosition(), "invalid decimal number");
                                                                     }

                                                                     return var32.replace(sep, "").replace(dec, ".");
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

      throw new MatchError(var11);
   }

   public Exprs.Expr handleInt(final Context c, final String name, final String sep) {
      try {
         return c.Expr(c.universe().Literal().apply(c.universe().Constant().apply(BoxesRunTime.boxToInteger(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.formatWhole(c, sep)))))), c.universe().WeakTypeTag().Int());
      } catch (Exception var5) {
         throw new NumberFormatException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal %s Int constant"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name})));
      }
   }

   public Exprs.Expr handleLong(final Context c, final String name, final String sep) {
      try {
         return c.Expr(c.universe().Literal().apply(c.universe().Constant().apply(BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(this.formatWhole(c, sep)))))), c.universe().WeakTypeTag().Long());
      } catch (Exception var5) {
         throw new NumberFormatException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal %s Long constant"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name})));
      }
   }

   public Exprs.Expr handleBigInt(final Context c, final String name, final String sep) {
      try {
         String s = this.formatWhole(c, sep);
         .MODULE$.BigInt().apply(s);
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("scala")), c.universe().TermName().apply("math")), c.universe().TermName().apply("BigInt")), c.universe().TermName().apply("apply")), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.LiteralApi[]{c.universe().Literal().apply(c.universe().Constant().apply(s))}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigInt"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         return c.Expr(var10001, $u.TypeTag().apply($m, new $typecreator1$1()));
      } catch (Exception var8) {
         throw new NumberFormatException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal %s BigInt constant"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name})));
      }
   }

   public Exprs.Expr handleBigDecimal(final Context c, final String name, final String sep, final String dec) {
      try {
         String s = this.formatDecimal(c, sep, dec);
         .MODULE$.BigDecimal().apply(s);
         Trees.ApplyApi var10001 = c.universe().Apply().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().internal().reificationSupport().SyntacticTermIdent().apply(c.universe().TermName().apply("_root_"), false), c.universe().TermName().apply("scala")), c.universe().TermName().apply("math")), c.universe().TermName().apply("BigDecimal")), c.universe().TermName().apply("apply")), (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Trees.LiteralApi[]{c.universe().Literal().apply(c.universe().Constant().apply(s))}))));
         Universe $u = c.universe();
         Mirror $m = c.universe().rootMirror();

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               scala.reflect.api.Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigDecimal"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         return c.Expr(var10001, $u.TypeTag().apply($m, new $typecreator1$2()));
      } catch (Exception var9) {
         throw new NumberFormatException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal %s BigDecimal constant"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{name})));
      }
   }

   public Exprs.Expr siInt(final Context c) {
      return this.handleInt(c, "SI", " ");
   }

   public Exprs.Expr siLong(final Context c) {
      return this.handleLong(c, "SI", " ");
   }

   public Exprs.Expr siBigInt(final Context c) {
      return this.handleBigInt(c, "SI", " ");
   }

   public Exprs.Expr siBigDecimal(final Context c) {
      return this.handleBigDecimal(c, "SI", " ", "\\.");
   }

   public Exprs.Expr usInt(final Context c) {
      return this.handleInt(c, "US", ",");
   }

   public Exprs.Expr usLong(final Context c) {
      return this.handleLong(c, "US", ",");
   }

   public Exprs.Expr usBigInt(final Context c) {
      return this.handleBigInt(c, "US", ",");
   }

   public Exprs.Expr usBigDecimal(final Context c) {
      return this.handleBigDecimal(c, "US", ",", "\\.");
   }

   public Exprs.Expr euInt(final Context c) {
      return this.handleInt(c, "EU", ".");
   }

   public Exprs.Expr euLong(final Context c) {
      return this.handleLong(c, "EU", ".");
   }

   public Exprs.Expr euBigInt(final Context c) {
      return this.handleBigInt(c, "EU", ".");
   }

   public Exprs.Expr euBigDecimal(final Context c) {
      return this.handleBigDecimal(c, "EU", ".", ",");
   }

   public Exprs.Expr radix(final Context c) {
      Trees.TreeApi var4 = c.prefix().tree();
      if (var4 != null) {
         Option var5 = c.universe().ApplyTag().unapply(var4);
         if (!var5.isEmpty()) {
            Trees.ApplyApi var6 = (Trees.ApplyApi)var5.get();
            if (var6 != null) {
               Option var7 = c.universe().Apply().unapply(var6);
               if (!var7.isEmpty()) {
                  List var8 = (List)((Tuple2)var7.get())._2();
                  if (var8 != null) {
                     SeqOps var9 = .MODULE$.List().unapplySeq(var8);
                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var9) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var9)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var9), 1) == 0) {
                        Trees.TreeApi var10 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var9), 0);
                        if (var10 != null) {
                           Option var11 = c.universe().ApplyTag().unapply(var10);
                           if (!var11.isEmpty()) {
                              Trees.ApplyApi var12 = (Trees.ApplyApi)var11.get();
                              if (var12 != null) {
                                 Option var13 = c.universe().Apply().unapply(var12);
                                 if (!var13.isEmpty()) {
                                    List var14 = (List)((Tuple2)var13.get())._2();
                                    if (var14 != null) {
                                       SeqOps var15 = .MODULE$.List().unapplySeq(var14);
                                       if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var15) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15), 1) == 0) {
                                          Trees.TreeApi var16 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var15), 0);
                                          if (var16 != null) {
                                             Option var17 = c.universe().LiteralTag().unapply(var16);
                                             if (!var17.isEmpty()) {
                                                Trees.LiteralApi var18 = (Trees.LiteralApi)var17.get();
                                                if (var18 != null) {
                                                   Option var19 = c.universe().Literal().unapply(var18);
                                                   if (!var19.isEmpty()) {
                                                      Constants.ConstantApi var20 = (Constants.ConstantApi)var19.get();
                                                      if (var20 != null) {
                                                         Option var21 = c.universe().ConstantTag().unapply(var20);
                                                         if (!var21.isEmpty()) {
                                                            Constants.ConstantApi var22 = (Constants.ConstantApi)var21.get();
                                                            if (var22 != null) {
                                                               Option var23 = c.universe().Constant().unapply(var22);
                                                               if (!var23.isEmpty()) {
                                                                  Object s = var23.get();
                                                                  if (s instanceof String) {
                                                                     String var25 = (String)s;
                                                                     String name = c.macroApplication().symbol().name().toString();
                                                                     int base = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(name.substring(1)));
                                                                     if (base >= 2 && 36 >= base) {
                                                                        int n = Integer.parseInt(var25, base);
                                                                        return c.Expr(c.universe().Literal().apply(c.universe().Constant().apply(BoxesRunTime.boxToInteger(n))), c.universe().WeakTypeTag().Int());
                                                                     }

                                                                     throw new NumberFormatException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("invalid radix: %s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(base)})));
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

      throw new MatchError(var4);
   }

   public Exprs.Expr intAs(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$1) {
      Trees.TreeApi var7 = c.prefix().tree();
      Object var4;
      if (var7 != null) {
         Option var8 = c.universe().ApplyTag().unapply(var7);
         if (!var8.isEmpty()) {
            Trees.ApplyApi var9 = (Trees.ApplyApi)var8.get();
            if (var9 != null) {
               Option var10 = c.universe().Apply().unapply(var9);
               if (!var10.isEmpty()) {
                  Tuple2 var11 = (Tuple2)var10.get();
                  if (var11 != null) {
                     List var12 = (List)var11._2();
                     if (var12 != null) {
                        SeqOps var13 = .MODULE$.List().unapplySeq(var12);
                        if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1) == 0) {
                           Trees.TreeApi var14 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
                           if (var14 != null) {
                              Option var15 = c.universe().LiteralTag().unapply(var14);
                              if (!var15.isEmpty()) {
                                 Trees.LiteralApi var16 = (Trees.LiteralApi)var15.get();
                                 if (var16 != null) {
                                    Option var17 = c.universe().Literal().unapply(var16);
                                    if (!var17.isEmpty()) {
                                       Constants.ConstantApi var18 = (Constants.ConstantApi)var17.get();
                                       if (var18 != null) {
                                          Option var19 = c.universe().ConstantTag().unapply(var18);
                                          if (!var19.isEmpty()) {
                                             Constants.ConstantApi var20 = (Constants.ConstantApi)var19.get();
                                             if (var20 != null) {
                                                Option var21 = c.universe().Constant().unapply(var20);
                                                if (!var21.isEmpty()) {
                                                   Object var22 = var21.get();
                                                   if (BoxesRunTime.equals(BoxesRunTime.boxToInteger(0), var22)) {
                                                      var4 = c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("zero"));
                                                      return c.Expr((Trees.TreeApi)var4, evidence$1);
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

      if (var7 != null) {
         Option var23 = c.universe().ApplyTag().unapply(var7);
         if (!var23.isEmpty()) {
            Trees.ApplyApi var24 = (Trees.ApplyApi)var23.get();
            if (var24 != null) {
               Option var25 = c.universe().Apply().unapply(var24);
               if (!var25.isEmpty()) {
                  Tuple2 var26 = (Tuple2)var25.get();
                  if (var26 != null) {
                     List var27 = (List)var26._2();
                     if (var27 != null) {
                        SeqOps var28 = .MODULE$.List().unapplySeq(var27);
                        if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var28) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28), 1) == 0) {
                           Trees.TreeApi var29 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28), 0);
                           if (var29 != null) {
                              Option var30 = c.universe().LiteralTag().unapply(var29);
                              if (!var30.isEmpty()) {
                                 Trees.LiteralApi var31 = (Trees.LiteralApi)var30.get();
                                 if (var31 != null) {
                                    Option var32 = c.universe().Literal().unapply(var31);
                                    if (!var32.isEmpty()) {
                                       Constants.ConstantApi var33 = (Constants.ConstantApi)var32.get();
                                       if (var33 != null) {
                                          Option var34 = c.universe().ConstantTag().unapply(var33);
                                          if (!var34.isEmpty()) {
                                             Constants.ConstantApi var35 = (Constants.ConstantApi)var34.get();
                                             if (var35 != null) {
                                                Option var36 = c.universe().Constant().unapply(var35);
                                                if (!var36.isEmpty()) {
                                                   Object var37 = var36.get();
                                                   if (BoxesRunTime.equals(BoxesRunTime.boxToInteger(1), var37)) {
                                                      var4 = c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("one"));
                                                      return c.Expr((Trees.TreeApi)var4, evidence$1);
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

      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         Option var38 = c.universe().ApplyTag().unapply(var7);
         if (var38.isEmpty()) {
            throw new MatchError(var7);
         } else {
            Trees.ApplyApi var39 = (Trees.ApplyApi)var38.get();
            if (var39 == null) {
               throw new MatchError(var7);
            } else {
               Option var40 = c.universe().Apply().unapply(var39);
               if (var40.isEmpty()) {
                  throw new MatchError(var7);
               } else {
                  Tuple2 var41 = (Tuple2)var40.get();
                  if (var41 == null) {
                     throw new MatchError(var7);
                  } else {
                     List var42 = (List)var41._2();
                     if (var42 == null) {
                        throw new MatchError(var7);
                     } else {
                        SeqOps var43 = .MODULE$.List().unapplySeq(var42);
                        if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var43) || new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43)) == null || scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 1) != 0) {
                           throw new MatchError(var7);
                        } else {
                           Trees.TreeApi n = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 0);
                           var4 = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("fromInt")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(n, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
                           return c.Expr((Trees.TreeApi)var4, evidence$1);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public Exprs.Expr dblAs(final Context c, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$2) {
      Trees.TreeApi var7 = c.prefix().tree();
      Object var4;
      if (var7 != null) {
         Option var8 = c.universe().ApplyTag().unapply(var7);
         if (!var8.isEmpty()) {
            Trees.ApplyApi var9 = (Trees.ApplyApi)var8.get();
            if (var9 != null) {
               Option var10 = c.universe().Apply().unapply(var9);
               if (!var10.isEmpty()) {
                  Tuple2 var11 = (Tuple2)var10.get();
                  if (var11 != null) {
                     List var12 = (List)var11._2();
                     if (var12 != null) {
                        SeqOps var13 = .MODULE$.List().unapplySeq(var12);
                        if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var13) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 1) == 0) {
                           Trees.TreeApi var14 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var13), 0);
                           if (var14 != null) {
                              Option var15 = c.universe().LiteralTag().unapply(var14);
                              if (!var15.isEmpty()) {
                                 Trees.LiteralApi var16 = (Trees.LiteralApi)var15.get();
                                 if (var16 != null) {
                                    Option var17 = c.universe().Literal().unapply(var16);
                                    if (!var17.isEmpty()) {
                                       Constants.ConstantApi var18 = (Constants.ConstantApi)var17.get();
                                       if (var18 != null) {
                                          Option var19 = c.universe().ConstantTag().unapply(var18);
                                          if (!var19.isEmpty()) {
                                             Constants.ConstantApi var20 = (Constants.ConstantApi)var19.get();
                                             if (var20 != null) {
                                                Option var21 = c.universe().Constant().unapply(var20);
                                                if (!var21.isEmpty()) {
                                                   Object var22 = var21.get();
                                                   if (BoxesRunTime.equals(BoxesRunTime.boxToDouble((double)0.0F), var22)) {
                                                      var4 = c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("zero"));
                                                      return c.Expr((Trees.TreeApi)var4, evidence$2);
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

      if (var7 != null) {
         Option var23 = c.universe().ApplyTag().unapply(var7);
         if (!var23.isEmpty()) {
            Trees.ApplyApi var24 = (Trees.ApplyApi)var23.get();
            if (var24 != null) {
               Option var25 = c.universe().Apply().unapply(var24);
               if (!var25.isEmpty()) {
                  Tuple2 var26 = (Tuple2)var25.get();
                  if (var26 != null) {
                     List var27 = (List)var26._2();
                     if (var27 != null) {
                        SeqOps var28 = .MODULE$.List().unapplySeq(var27);
                        if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var28) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28), 1) == 0) {
                           Trees.TreeApi var29 = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var28), 0);
                           if (var29 != null) {
                              Option var30 = c.universe().LiteralTag().unapply(var29);
                              if (!var30.isEmpty()) {
                                 Trees.LiteralApi var31 = (Trees.LiteralApi)var30.get();
                                 if (var31 != null) {
                                    Option var32 = c.universe().Literal().unapply(var31);
                                    if (!var32.isEmpty()) {
                                       Constants.ConstantApi var33 = (Constants.ConstantApi)var32.get();
                                       if (var33 != null) {
                                          Option var34 = c.universe().ConstantTag().unapply(var33);
                                          if (!var34.isEmpty()) {
                                             Constants.ConstantApi var35 = (Constants.ConstantApi)var34.get();
                                             if (var35 != null) {
                                                Option var36 = c.universe().Constant().unapply(var35);
                                                if (!var36.isEmpty()) {
                                                   Object var37 = var36.get();
                                                   if (BoxesRunTime.equals(BoxesRunTime.boxToDouble((double)1.0F), var37)) {
                                                      var4 = c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("one"));
                                                      return c.Expr((Trees.TreeApi)var4, evidence$2);
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

      if (var7 == null) {
         throw new MatchError(var7);
      } else {
         Option var38 = c.universe().ApplyTag().unapply(var7);
         if (var38.isEmpty()) {
            throw new MatchError(var7);
         } else {
            Trees.ApplyApi var39 = (Trees.ApplyApi)var38.get();
            if (var39 == null) {
               throw new MatchError(var7);
            } else {
               Option var40 = c.universe().Apply().unapply(var39);
               if (var40.isEmpty()) {
                  throw new MatchError(var7);
               } else {
                  Tuple2 var41 = (Tuple2)var40.get();
                  if (var41 == null) {
                     throw new MatchError(var7);
                  } else {
                     List var42 = (List)var41._2();
                     if (var42 == null) {
                        throw new MatchError(var7);
                     } else {
                        SeqOps var43 = .MODULE$.List().unapplySeq(var42);
                        if (scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var43) || new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43)) == null || scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 1) != 0) {
                           throw new MatchError(var7);
                        } else {
                           Trees.TreeApi n = (Trees.TreeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var43), 0);
                           var4 = c.universe().internal().reificationSupport().SyntacticApplied().apply(c.universe().internal().reificationSupport().SyntacticSelectTerm().apply(c.universe().Liftable().liftExpr().apply(ev), c.universe().TermName().apply("fromDouble")), (List)(new scala.collection.immutable..colon.colon((List)(new scala.collection.immutable..colon.colon(n, scala.collection.immutable.Nil..MODULE$)), scala.collection.immutable.Nil..MODULE$)));
                           return c.Expr((Trees.TreeApi)var4, evidence$2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private Macros$() {
   }
}
