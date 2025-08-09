package scala.reflect.internal.pickling;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.AnnotationInfos;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Names;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Trees;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.package$;

@ScalaSignature(
   bytes = "\u0006\u0005E3\u0001\u0002C\u0005\u0011\u0002\u0007\u0005!#\u0014\u0005\u0006/\u0001!\t\u0001\u0007\u0005\u00069\u0001!\t!\b\u0005\u00069\u0001!\tA\n\u0005\u0006_\u0001!)\u0001\r\u0005\u0006_\u0001!\ta\r\u0005\u0006_\u0001!)a\u000f\u0005\u0006\u0015\u0002!\ta\u0013\u0002\r)J\fgn\u001d7bi&|gn\u001d\u0006\u0003\u0015-\t\u0001\u0002]5dW2Lgn\u001a\u0006\u0003\u00195\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003\u001d=\tqA]3gY\u0016\u001cGOC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001A\n\u0011\u0005Q)R\"A\b\n\u0005Yy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u00023A\u0011ACG\u0005\u00037=\u0011A!\u00168ji\u0006\u0019\u0012n\u001d+sK\u0016\u001c\u00160\u001c2pYBK7m\u001b7fIR\u0011a$\t\t\u0003)}I!\u0001I\b\u0003\u000f\t{w\u000e\\3b]\")!E\u0001a\u0001G\u0005!1m\u001c3f!\t!B%\u0003\u0002&\u001f\t\u0019\u0011J\u001c;\u0015\u0005y9\u0003\"\u0002\u0015\u0004\u0001\u0004I\u0013\u0001\u0002;sK\u0016\u0004\"AK\u0016\u000e\u0003\u0001I!\u0001L\u0017\u0003\tQ\u0013X-Z\u0005\u0003]-\u0011Q\u0001\u0016:fKN\f!\u0002]5dW2,'\u000fV1h)\t\u0019\u0013\u0007C\u00033\t\u0001\u00071#A\u0002sK\u001a$\"a\t\u001b\t\u000bU*\u0001\u0019\u0001\u001c\u0002\u0007MLX\u000e\u0005\u0002+o%\u0011\u0001(\u000f\u0002\u0007'fl'm\u001c7\n\u0005iZ!aB*z[\n|Gn\u001d\u000b\u0003GqBQ!\u0010\u0004A\u0002y\n1\u0001\u001e9f!\tQs(\u0003\u0002A\u0003\n!A+\u001f9f\u0013\t\u00115BA\u0003UsB,7\u000f\u000b\u0002\u0007\tB\u0011Q\tS\u0007\u0002\r*\u0011qiD\u0001\u000bC:tw\u000e^1uS>t\u0017BA%G\u0005\u001d!\u0018-\u001b7sK\u000e\fQ\u0002]5dW2,'oU;c)\u0006<GCA\u0012M\u0011\u0015As\u00011\u0001*!\tqu*D\u0001\f\u0013\t\u00016BA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007"
)
public interface Translations {
   // $FF: synthetic method
   static boolean isTreeSymbolPickled$(final Translations $this, final int code) {
      return $this.isTreeSymbolPickled(code);
   }

   default boolean isTreeSymbolPickled(final int code) {
      switch (code) {
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
            return true;
         case 9:
         case 12:
         case 18:
         case 21:
         case 25:
            return true;
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
            return true;
         default:
            return false;
      }
   }

   // $FF: synthetic method
   static boolean isTreeSymbolPickled$(final Translations $this, final Trees.Tree tree) {
      return $this.isTreeSymbolPickled(tree);
   }

   default boolean isTreeSymbolPickled(final Trees.Tree tree) {
      return this.isTreeSymbolPickled(this.picklerSubTag(tree));
   }

   // $FF: synthetic method
   static int picklerTag$(final Translations $this, final Object ref) {
      return $this.picklerTag(ref);
   }

   default int picklerTag(final Object ref) {
      boolean var2 = false;
      Tuple2 var3 = null;
      if (ref instanceof Types.Type && ((Types.Type)ref).scala$reflect$internal$Types$Type$$$outer() == this) {
         Types.Type var4 = (Types.Type)ref;
         return this.picklerTag(var4);
      } else if (ref instanceof Symbols.Symbol && ((Symbols.Symbol)ref).scala$reflect$internal$Symbols$Symbol$$$outer() == this) {
         Symbols.Symbol var5 = (Symbols.Symbol)ref;
         return this.picklerTag(var5);
      } else if (ref instanceof Constants.Constant && ((Constants.Constant)ref).scala$reflect$internal$Constants$Constant$$$outer() == this) {
         Constants.Constant var6 = (Constants.Constant)ref;
         return 23 + var6.tag();
      } else if (ref instanceof Trees.Tree && ((Trees.Tree)ref).scala$reflect$internal$Trees$Tree$$$outer() == this) {
         return 49;
      } else if (ref instanceof Names.TermName) {
         return 1;
      } else if (ref instanceof Names.TypeName) {
         return 2;
      } else if (ref instanceof AnnotationInfos.ArrayAnnotArg && ((AnnotationInfos.ArrayAnnotArg)ref).scala$reflect$internal$AnnotationInfos$ArrayAnnotArg$$$outer() == this) {
         return 44;
      } else if (ref instanceof AnnotationInfos.AnnotationInfo && ((AnnotationInfos.AnnotationInfo)ref).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() == this) {
         return 43;
      } else {
         if (ref instanceof Tuple2) {
            var2 = true;
            var3 = (Tuple2)ref;
            if (var3._1() instanceof Symbols.Symbol && ((Symbols.Symbol)var3._1()).scala$reflect$internal$Symbols$Symbol$$$outer() == this && var3._2() instanceof AnnotationInfos.AnnotationInfo && ((AnnotationInfos.AnnotationInfo)var3._2()).scala$reflect$internal$AnnotationInfos$AnnotationInfo$$$outer() == this) {
               return 40;
            }
         }

         if (var2 && var3._1() instanceof Symbols.Symbol && ((Symbols.Symbol)var3._1()).scala$reflect$internal$Symbols$Symbol$$$outer() == this && var3._2() instanceof List) {
            return 41;
         } else if (ref instanceof Trees.Modifiers && ((Trees.Modifiers)ref).scala$reflect$internal$Trees$Modifiers$$$outer() == this) {
            return 50;
         } else {
            throw new IllegalStateException((new StringBuilder(19)).append("unpicklable entry ").append(package$.MODULE$.shortClassOfInstance(ref)).append(" ").append(ref).toString());
         }
      }
   }

   // $FF: synthetic method
   static int picklerTag$(final Translations $this, final Symbols.Symbol sym) {
      return $this.picklerTag(sym);
   }

   default int picklerTag(final Symbols.Symbol sym) {
      boolean var2 = false;
      boolean var3 = false;
      Symbols.NoSymbol var10000 = ((Symbols)this).NoSymbol();
      if (var10000 == null) {
         if (sym == null) {
            return 3;
         }
      } else if (var10000.equals(sym)) {
         return 3;
      }

      if (sym instanceof Symbols.ClassSymbol) {
         return 6;
      } else {
         if (sym instanceof Symbols.TypeSymbol) {
            var2 = true;
            Symbols.TypeSymbol var4 = (Symbols.TypeSymbol)sym;
            if (sym.isAbstractType()) {
               return 4;
            }
         }

         if (var2) {
            return 5;
         } else {
            if (sym instanceof Symbols.TermSymbol) {
               var3 = true;
               Symbols.TermSymbol var5 = (Symbols.TermSymbol)sym;
               if (sym.isModule()) {
                  return 7;
               }
            }

            if (var3) {
               return 8;
            } else {
               throw new MatchError(sym);
            }
         }
      }
   }

   // $FF: synthetic method
   static int picklerTag$(final Translations $this, final Types.Type tpe) {
      return $this.picklerTag(tpe);
   }

   default int picklerTag(final Types.Type tpe) {
      while(!((Types)this).NoType().equals(tpe)) {
         if (((Types)this).NoPrefix().equals(tpe)) {
            return 12;
         }

         if (tpe instanceof Types.ThisType) {
            return 13;
         }

         if (tpe instanceof Types.SingleType) {
            return 14;
         }

         if (tpe instanceof Types.SuperType) {
            return 46;
         }

         if (tpe instanceof Types.ConstantType) {
            return 15;
         }

         if (tpe instanceof Types.TypeBounds) {
            return 17;
         }

         if (tpe instanceof Types.TypeRef) {
            return 16;
         }

         if (tpe instanceof Types.RefinedType) {
            return 18;
         }

         if (tpe instanceof Types.ClassInfoType) {
            return 19;
         }

         if (tpe instanceof Types.MethodType) {
            return 20;
         }

         if (tpe instanceof Types.PolyType) {
            return 21;
         }

         if (tpe instanceof Types.NullaryMethodType) {
            return 21;
         }

         if (tpe instanceof Types.ExistentialType) {
            return 48;
         }

         if (tpe != null && !((Types)this).StaticallyAnnotatedType().unapply(tpe).isEmpty()) {
            return 42;
         }

         if (!(tpe instanceof Types.AnnotatedType)) {
            throw new MatchError(tpe);
         }

         SymbolTable var10000 = (SymbolTable)this;
         tpe = tpe.underlying();
         this = var10000;
      }

      return 11;
   }

   // $FF: synthetic method
   static int picklerSubTag$(final Translations $this, final Trees.Tree tree) {
      return $this.picklerSubTag(tree);
   }

   default int picklerSubTag(final Trees.Tree tree) {
      if (((Trees)this).EmptyTree().equals(tree)) {
         return 1;
      } else if (tree instanceof Trees.PackageDef) {
         return 2;
      } else if (tree instanceof Trees.ClassDef) {
         return 3;
      } else if (tree instanceof Trees.ModuleDef) {
         return 4;
      } else if (tree instanceof Trees.ValDef) {
         return 5;
      } else if (tree instanceof Trees.DefDef) {
         return 6;
      } else if (tree instanceof Trees.TypeDef) {
         return 7;
      } else if (tree instanceof Trees.LabelDef) {
         return 8;
      } else if (tree instanceof Trees.Import) {
         return 9;
      } else if (tree instanceof Trees.Template) {
         return 12;
      } else if (tree instanceof Trees.Block) {
         return 13;
      } else if (tree instanceof Trees.CaseDef) {
         return 14;
      } else if (tree instanceof Trees.Alternative) {
         return 16;
      } else if (tree instanceof Trees.Star) {
         return 17;
      } else if (tree instanceof Trees.Bind) {
         return 18;
      } else if (tree instanceof Trees.UnApply) {
         return 19;
      } else if (tree instanceof Trees.ArrayValue) {
         return 20;
      } else if (tree instanceof Trees.Function) {
         return 21;
      } else if (tree instanceof Trees.Assign) {
         return 22;
      } else if (tree instanceof Trees.If) {
         return 23;
      } else if (tree instanceof Trees.Match) {
         return 24;
      } else if (tree instanceof Trees.Return) {
         return 25;
      } else if (tree instanceof Trees.Try) {
         return 26;
      } else if (tree instanceof Trees.Throw) {
         return 27;
      } else if (tree instanceof Trees.New) {
         return 28;
      } else if (tree instanceof Trees.Typed) {
         return 29;
      } else if (tree instanceof Trees.TypeApply) {
         return 30;
      } else if (tree instanceof Trees.Apply) {
         return 31;
      } else if (tree instanceof Trees.ApplyDynamic) {
         return 32;
      } else if (tree instanceof Trees.Super) {
         return 33;
      } else if (tree instanceof Trees.This) {
         return 34;
      } else if (tree instanceof Trees.Select) {
         return 35;
      } else if (tree instanceof Trees.Ident) {
         return 36;
      } else if (tree instanceof Trees.Literal) {
         return 37;
      } else if (tree instanceof Trees.TypeTree) {
         return 38;
      } else if (tree instanceof Trees.Annotated) {
         return 39;
      } else if (tree instanceof Trees.SingletonTypeTree) {
         return 40;
      } else if (tree instanceof Trees.SelectFromTypeTree) {
         return 41;
      } else if (tree instanceof Trees.CompoundTypeTree) {
         return 42;
      } else if (tree instanceof Trees.AppliedTypeTree) {
         return 43;
      } else if (tree instanceof Trees.TypeBoundsTree) {
         return 44;
      } else if (tree instanceof Trees.ExistentialTypeTree) {
         return 45;
      } else {
         throw new MatchError(tree);
      }
   }

   static void $init$(final Translations $this) {
   }
}
