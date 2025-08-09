package spire.macros;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Internals;
import scala.reflect.api.Mirror;
import scala.reflect.api.Names;
import scala.reflect.api.Symbols;
import scala.reflect.api.Trees;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.Types;
import scala.reflect.macros.Universe;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005y3AAB\u0004\u0001\u0019!AA\u0003\u0001BC\u0002\u0013\u0005Q\u0003\u0003\u00053\u0001\t\u0005\t\u0015!\u0003\u0017\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015Y\u0006\u0001\"\u0001]\u0005)Ie\u000e\\5oKV#\u0018\u000e\u001c\u0006\u0003\u0011%\ta!\\1de>\u001c(\"\u0001\u0006\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011Q\u0002G\n\u0003\u00019\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0017!A2\u0016\u0003Y\u0001\"a\u0006\r\r\u0001\u0011)\u0011\u0004\u0001b\u00015\t\t1)\u0005\u0002\u001c=A\u0011q\u0002H\u0005\u0003;A\u0011qAT8uQ&twME\u0002 C=2A\u0001\t\u0001\u0001=\taAH]3gS:,W.\u001a8u}A\u0011!\u0005\f\b\u0003G)r!\u0001J\u0015\u000f\u0005\u0015BS\"\u0001\u0014\u000b\u0005\u001dZ\u0011A\u0002\u001fs_>$h(C\u0001\u000b\u0013\tA\u0011\"\u0003\u0002,\u000f\u000511m\\7qCRL!!\f\u0018\u0003\u000f\r{g\u000e^3yi*\u00111f\u0002\t\u0003\u001fAJ!!\r\t\u0003\u0013MKgn\u001a7fi>t\u0017AA2!\u0003\u0019a\u0014N\\5u}Q\u0011Qg\u000e\t\u0004m\u00011R\"A\u0004\t\u000bQ\u0019\u0001\u0019\u0001\f\u0002\u001d%tG.\u001b8f\u0003:$'+Z:fiV\u0011!H\u0012\u000b\u0003w1\u00032\u0001\u0010 F\u001d\ti\u0014!D\u0001\u0001\u0013\ty\u0004I\u0001\u0003FqB\u0014\u0018BA!C\u0005\u001d\tE.[1tKNT!\u0001C\"\u000b\u0005\u0011\u0003\u0012a\u0002:fM2,7\r\u001e\t\u0003/\u0019#Qa\u0012\u0003C\u0002!\u0013\u0011\u0001V\t\u00037%\u0003\"a\u0004&\n\u0005-\u0003\"aA!os\")Q\n\u0002a\u0001\u001d\u0006!AO]3f!\tyUK\u0004\u0002=!&\u0011\u0011KU\u0001\tk:Lg/\u001a:tK&\u0011Qf\u0015\u0006\u0003)\n\u000b\u0001B\u00197bG.\u0014w\u000e_\u0005\u0003-^\u0013A\u0001\u0016:fK&\u0011\u0001,\u0017\u0002\u0006)J,Wm\u001d\u0006\u00035\u000e\u000b1!\u00199j\u0003QIg\u000e\\5oK\u0006\u0003\b\u000f\\=SK\u000e,(o]5wKR\u0011a*\u0018\u0005\u0006\u001b\u0016\u0001\rA\u0014"
)
public class InlineUtil {
   private final Context c;

   public Context c() {
      return this.c;
   }

   public Exprs.Expr inlineAndReset(final Trees.TreeApi tree) {
      Trees.TreeApi inlined = this.inlineApplyRecursive(tree);
      Context var10000 = this.c();
      Trees.TreeApi var10001 = compat$.MODULE$.resetLocalAttrs(this.c(), inlined);
      Universe $u = this.c().universe();
      Mirror $m = this.c().universe().rootMirror();

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            scala.reflect.api.Universe $u = $m$untyped.universe();
            Internals.FreeTypeSymbolApi free$T1 = $u.internal().reificationSupport().newFreeType("T", $u.internal().reificationSupport().FlagsRepr().apply(8208L), "defined by inlineAndReset in Syntax.scala:120:22");
            $u.internal().reificationSupport().setInfo(free$T1, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
            return $u.internal().reificationSupport().TypeRef($u.NoPrefix(), free$T1, .MODULE$);
         }

         public $typecreator1$2() {
         }
      }

      return var10000.Expr(var10001, $u.WeakTypeTag().apply($m, new $typecreator1$2()));
   }

   public Trees.TreeApi inlineApplyRecursive(final Trees.TreeApi tree) {
      LazyRef InlineApply$module = new LazyRef();
      Names.TermNameApi ApplyName = compat$.MODULE$.termName(this.c(), "apply");
      return this.InlineApply$2(InlineApply$module, ApplyName).transform(tree);

      class InlineSymbol$1 extends Trees.Transformer {
         private final Names.TermNameApi name;
         private final Symbols.SymbolApi symbol;
         private final Trees.TreeApi value;
         // $FF: synthetic field
         private final InlineUtil $outer;

         public Trees.TreeApi transform(final Trees.TreeApi tree) {
            Trees.TreeApi var2;
            if (tree != null) {
               Option var4 = this.$outer.c().universe().IdentTag().unapply(tree);
               if (!var4.isEmpty() && var4.get() != null) {
                  label70: {
                     Symbols.SymbolApi var10000 = ((Trees.SymTreeApi)tree).symbol();
                     Symbols.SymbolApi var5 = this.symbol;
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label70;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label70;
                     }

                     label41: {
                        label40: {
                           Names.NameApi var8 = ((Trees.IdentApi)tree).name();
                           Names.TermNameApi var6 = this.name;
                           if (var8 == null) {
                              if (var6 == null) {
                                 break label40;
                              }
                           } else if (var8.equals(var6)) {
                              break label40;
                           }

                           var9 = super.transform(tree);
                           break label41;
                        }

                        var9 = this.value;
                     }

                     var2 = var9;
                     return var2;
                  }
               }
            }

            if (tree != null) {
               Option var7 = this.$outer.c().universe().TypeTreeTag().unapply(tree);
               if (!var7.isEmpty() && var7.get() != null && ((Trees.TypeTreeApi)tree).original() != null) {
                  var2 = super.transform(compat$.MODULE$.setOrig(this.$outer.c(), this.$outer.c().universe().TypeTree().apply(), this.transform(((Trees.TypeTreeApi)tree).original())));
                  return var2;
               }
            }

            var2 = super.transform(tree);
            return var2;
         }

         public InlineSymbol$1(final Names.TermNameApi name, final Symbols.SymbolApi symbol, final Trees.TreeApi value) {
            this.name = name;
            this.symbol = symbol;
            this.value = value;
            if (InlineUtil.this == null) {
               throw null;
            } else {
               this.$outer = InlineUtil.this;
               super(InlineUtil.this.c().universe());
            }
         }
      }

   }

   // $FF: synthetic method
   private final InlineApply$1$ InlineApply$lzycompute$1(final LazyRef InlineApply$module$1, final Names.TermNameApi ApplyName$1) {
      synchronized(InlineApply$module$1){}

      InlineApply$1$ var4;
      try {
         class InlineApply$1$ extends Trees.Transformer {
            // $FF: synthetic field
            private final InlineUtil $outer;
            private final Names.TermNameApi ApplyName$1;

            public Trees.TreeApi inlineSymbol(final Names.TermNameApi name, final Symbols.SymbolApi symbol, final Trees.TreeApi body, final Trees.TreeApi arg) {
               return (new InlineSymbol$1(name, symbol, arg)).transform(body);
            }

            public Trees.TreeApi transform(final Trees.TreeApi tree) {
               Trees.TreeApi var2;
               List args;
               List params;
               Trees.TreeApi body;
               label91: {
                  if (tree != null) {
                     Option var4 = this.$outer.c().universe().ApplyTag().unapply(tree);
                     if (!var4.isEmpty()) {
                        Trees.ApplyApi var5 = (Trees.ApplyApi)var4.get();
                        if (var5 != null) {
                           Option var6 = this.$outer.c().universe().Apply().unapply(var5);
                           if (!var6.isEmpty()) {
                              Trees.TreeApi var7 = (Trees.TreeApi)((Tuple2)var6.get())._1();
                              args = (List)((Tuple2)var6.get())._2();
                              if (var7 != null) {
                                 Option var9 = this.$outer.c().universe().SelectTag().unapply(var7);
                                 if (!var9.isEmpty()) {
                                    Trees.SelectApi var10 = (Trees.SelectApi)var9.get();
                                    if (var10 != null) {
                                       Option var11 = this.$outer.c().universe().Select().unapply(var10);
                                       if (!var11.isEmpty()) {
                                          Trees.TreeApi var12 = (Trees.TreeApi)((Tuple2)var11.get())._1();
                                          Names.NameApi var13 = (Names.NameApi)((Tuple2)var11.get())._2();
                                          if (var12 != null) {
                                             Option var14 = this.$outer.c().universe().FunctionTag().unapply(var12);
                                             if (!var14.isEmpty()) {
                                                Trees.FunctionApi var15 = (Trees.FunctionApi)var14.get();
                                                if (var15 != null) {
                                                   Option var16 = this.$outer.c().universe().Function().unapply(var15);
                                                   if (!var16.isEmpty()) {
                                                      params = (List)((Tuple2)var16.get())._1();
                                                      body = (Trees.TreeApi)((Tuple2)var16.get())._2();
                                                      Names.TermNameApi var10000 = this.ApplyName$1;
                                                      if (var10000 == null) {
                                                         if (var13 == null) {
                                                            break label91;
                                                         }
                                                      } else if (var10000.equals(var13)) {
                                                         break label91;
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (tree != null) {
                     Option var20 = this.$outer.c().universe().ApplyTag().unapply(tree);
                     if (!var20.isEmpty()) {
                        Trees.ApplyApi var21 = (Trees.ApplyApi)var20.get();
                        if (var21 != null) {
                           Option var22 = this.$outer.c().universe().Apply().unapply(var21);
                           if (!var22.isEmpty()) {
                              Trees.TreeApi var23 = (Trees.TreeApi)((Tuple2)var22.get())._1();
                              List args = (List)((Tuple2)var22.get())._2();
                              if (var23 != null) {
                                 Option var25 = this.$outer.c().universe().FunctionTag().unapply(var23);
                                 if (!var25.isEmpty()) {
                                    Trees.FunctionApi var26 = (Trees.FunctionApi)var25.get();
                                    if (var26 != null) {
                                       Option var27 = this.$outer.c().universe().Function().unapply(var26);
                                       if (!var27.isEmpty()) {
                                          List params = (List)((Tuple2)var27.get())._1();
                                          Trees.TreeApi body = (Trees.TreeApi)((Tuple2)var27.get())._2();
                                          var2 = (Trees.TreeApi)((LinearSeqOps)params.zip(args)).foldLeft(body, (x0$2, x1$2) -> {
                                             Tuple2 var4 = new Tuple2(x0$2, x1$2);
                                             if (var4 != null) {
                                                Trees.TreeApi b = (Trees.TreeApi)var4._1();
                                                Tuple2 var6 = (Tuple2)var4._2();
                                                if (var6 != null) {
                                                   Trees.ValDefApi param = (Trees.ValDefApi)var6._1();
                                                   Trees.TreeApi arg = (Trees.TreeApi)var6._2();
                                                   Trees.TreeApi var3 = this.inlineSymbol(param.name(), param.symbol(), b, arg);
                                                   return var3;
                                                }
                                             }

                                             throw new MatchError(var4);
                                          });
                                          return var2;
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  var2 = super.transform(tree);
                  return var2;
               }

               var2 = (Trees.TreeApi)((LinearSeqOps)params.zip(args)).foldLeft(body, (x0$1, x1$1) -> {
                  Tuple2 var4 = new Tuple2(x0$1, x1$1);
                  if (var4 != null) {
                     Trees.TreeApi b = (Trees.TreeApi)var4._1();
                     Tuple2 var6 = (Tuple2)var4._2();
                     if (var6 != null) {
                        Trees.ValDefApi param = (Trees.ValDefApi)var6._1();
                        Trees.TreeApi arg = (Trees.TreeApi)var6._2();
                        Trees.TreeApi var3 = this.inlineSymbol(param.name(), param.symbol(), b, arg);
                        return var3;
                     }
                  }

                  throw new MatchError(var4);
               });
               return var2;
            }

            public InlineApply$1$(final Names.TermNameApi ApplyName$1) {
               if (InlineUtil.this == null) {
                  throw null;
               } else {
                  this.$outer = InlineUtil.this;
                  this.ApplyName$1 = ApplyName$1;
                  super(InlineUtil.this.c().universe());
               }
            }

            // $FF: synthetic method
            private static Object $deserializeLambda$(SerializedLambda var0) {
               return Class.lambdaDeserialize<invokedynamic>(var0);
            }
         }

         var4 = InlineApply$module$1.initialized() ? (InlineApply$1$)InlineApply$module$1.value() : (InlineApply$1$)InlineApply$module$1.initialize(new InlineApply$1$(ApplyName$1));
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private final InlineApply$1$ InlineApply$2(final LazyRef InlineApply$module$1, final Names.TermNameApi ApplyName$1) {
      return InlineApply$module$1.initialized() ? (InlineApply$1$)InlineApply$module$1.value() : this.InlineApply$lzycompute$1(InlineApply$module$1, ApplyName$1);
   }

   public InlineUtil(final Context c) {
      this.c = c;
   }
}
