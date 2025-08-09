package scala.reflect.runtime;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.util.Position;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005efA\u0003\u000e\u001c!\u0003\r\t!H\u0011\u00024\")a\u0005\u0001C\u0001Q\u0019!A\u0006\u0001\u0001.\u0011!Y$A!A!\u0002\u0013a\u0004\u0002C!\u0003\u0005\u0003\u0005\u000b\u0011\u0002\"\t\u000b\u0015\u0013A\u0011\u0001$\t\u000b)\u0013A\u0011I&\t\u000bE\u0013A\u0011\t*\t\u000bQ\u0003A\u0011C+\t\u000b)\u0004A\u0011C6\t\u000bM\u0004A\u0011\u0003;\u0007\ta\u0004\u0001!\u001f\u0005\u0006\u000b.!\t! \u0005\u0006\u0015.!\te \u0004\u0007\u0003\u0007\u0001\u0001!!\u0002\t\u0013\u0005maB!A!\u0002\u0013q\u0005BB#\u000f\t\u0003\ti\u0002C\u0004\u0002$9!\t%!\n\t\u000f\u0005ub\u0002\"\u0011\u0002@!9\u0011\u0011\n\b\u0005B\u0005-\u0003\u0002CA3\u001d\u0001\u0006I!a\u001a\t\u000f\u0005ud\u0002\"\u0011\u0002\u0000!q\u0011\u0011\u0012\b\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\f\u0006=\u0005bBAI\u0001\u0011\u0005\u00131\u0013\u0005\b\u0003?\u0003A\u0011IAQ\u0011\u001d\t)\u000b\u0001C!\u0003O\u0013QbU=nE>dGj\\1eKJ\u001c(B\u0001\u000f\u001e\u0003\u001d\u0011XO\u001c;j[\u0016T!AH\u0010\u0002\u000fI,g\r\\3di*\t\u0001%A\u0003tG\u0006d\u0017m\u0005\u0002\u0001EA\u00111\u0005J\u0007\u0002?%\u0011Qe\b\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\u000b\t\u0003G)J!aK\u0010\u0003\tUs\u0017\u000e\u001e\u0002\u0012)>\u00048\t\\1tg\u000e{W\u000e\u001d7fi\u0016\u00148c\u0001\u0002/mA\u0011q\u0006M\u0007\u0002\u0001%\u0011\u0011G\r\u0002\n'flGj\\1eKJL!a\r\u001b\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a\u0006\u0003ku\t\u0001\"\u001b8uKJt\u0017\r\u001c\t\u0003_]J!\u0001O\u001d\u0003-\u0019c\u0017mZ!tg&<g.\u001b8h\u0007>l\u0007\u000f\\3uKJL!A\u000f\u001b\u0003\u000bQK\b/Z:\u0002\u000b\rd\u0017M\u001f>\u0011\u0005=j\u0014B\u0001 @\u0005-\u0019E.Y:t'fl'm\u001c7\n\u0005\u0001#$aB*z[\n|Gn]\u0001\u0007[>$W\u000f\\3\u0011\u0005=\u001a\u0015B\u0001#@\u00051iu\u000eZ;mKNKXNY8m\u0003\u0019a\u0014N\\5u}Q\u0019q\tS%\u0011\u0005=\u0012\u0001\"B\u001e\u0006\u0001\u0004a\u0004\"B!\u0006\u0001\u0004\u0011\u0015\u0001C2p[BdW\r^3\u0015\u0005%b\u0005\"B'\u0007\u0001\u0004q\u0015aA:z[B\u0011qfT\u0005\u0003!~\u0012aaU=nE>d\u0017\u0001\u00027pC\u0012$\"!K*\t\u000b5;\u0001\u0019\u0001(\u00025%t\u0017\u000e^!oI\u0016sG/\u001a:DY\u0006\u001c8/\u00118e\u001b>$W\u000f\\3\u0015\tYK6L\u0019\t\u0005G]c$)\u0003\u0002Y?\t1A+\u001e9mKJBQA\u0017\u0005A\u00029\u000bQa\\<oKJDQ\u0001\u0018\u0005A\u0002u\u000bAA\\1nKB\u0011qFX\u0005\u0003?\u0002\u0014\u0001\u0002V=qK:\u000bW.Z\u0005\u0003CR\u0012QAT1nKNDQa\u0019\u0005A\u0002\u0011\f\u0011bY8na2,G/\u001a:\u0011\u000b\r*GHQ4\n\u0005\u0019|\"!\u0003$v]\u000e$\u0018n\u001c83!\ty\u0003.\u0003\u0002js\tAA*\u0019>z)f\u0004X-A\u0006tKR\fE\u000e\\%oM>\u001cH\u0003B\u0015m[:DQaO\u0005A\u00029CQ!Q\u0005A\u00029CQa\\\u0005A\u0002A\fA!\u001b8g_B\u0011q&]\u0005\u0003ef\u0012A\u0001V=qK\u0006\u0011\u0012N\\5u\u00072\f7o]!oI6{G-\u001e7f)\u0011ISO^<\t\u000bmR\u0001\u0019\u0001(\t\u000b\u0005S\u0001\u0019\u0001(\t\u000b\rT\u0001\u0019A4\u0003\u001f1\u000b'0\u001f)bG.\fw-\u001a+za\u0016\u001c2aC4{!\ty30\u0003\u0002}s\t)b\t\\1h\u0003\u001etwn\u001d;jG\u000e{W\u000e\u001d7fi\u0016\u0014H#\u0001@\u0011\u0005=ZAcA\u0015\u0002\u0002!)Q*\u0004a\u0001\u001d\na\u0001+Y2lC\u001e,7kY8qKN)a\"a\u0002\u0002\u0012A\u0019q&!\u0003\n\t\u0005-\u0011Q\u0002\u0002\u0006'\u000e|\u0007/Z\u0005\u0004\u0003\u001f!$AB*d_B,7\u000fE\u00020\u0003'IA!!\u0006\u0002\u0018\t\t2+\u001f8dQJ|g.\u001b>fIN\u001bw\u000e]3\n\u0007\u0005e1DA\bTs:\u001c\u0007N]8oSj,Gm\u00149t\u0003!\u00018nZ\"mCN\u001cH\u0003BA\u0010\u0003C\u0001\"a\f\b\t\r\u0005m\u0001\u00031\u0001O\u0003\u0015)g\u000e^3s+\u0011\t9#!\r\u0015\t\u0005%\u0012Q\u0006\b\u0005\u0003W\ti\u0003\u0004\u0001\t\r5\u000b\u0002\u0019AA\u0018!\u0011\tY#!\r\u0005\u000f\u0005M\u0012C1\u0001\u00026\t\tA+E\u0002\u000289\u00032aIA\u001d\u0013\r\tYd\b\u0002\b\u001d>$\b.\u001b8h\u0003))g\u000e^3s\u0013\u001atUm^\u000b\u0005\u0003\u0003\n)\u0005\u0006\u0003\u0002D\u0005\u001d\u0003\u0003BA\u0016\u0003\u000b\"q!a\r\u0013\u0005\u0004\t)\u0004\u0003\u0004N%\u0001\u0007\u00111I\u0001\u0015gft7\rT8dWNKhn\u00195s_:L'0\u001a3\u0016\t\u00055\u0013\u0011\u000b\u000b\u0005\u0003\u001f\nY\u0006\u0005\u0003\u0002,\u0005ECaBA\u001a'\t\u0007\u00111K\t\u0005\u0003o\t)\u0006E\u0002$\u0003/J1!!\u0017 \u0005\r\te.\u001f\u0005\t\u0003;\u001aB\u00111\u0001\u0002`\u0005!!m\u001c3z!\u0015\u0019\u0013\u0011MA(\u0013\r\t\u0019g\b\u0002\ty\tLh.Y7f}\u0005Ia.Z4bi&4Xm\u001d\t\u0007\u0003S\n\u0019(a\u001e\u000e\u0005\u0005-$\u0002BA7\u0003_\nq!\\;uC\ndWMC\u0002\u0002r}\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)(a\u001b\u0003\u000f!\u000b7\u000f[*fiB\u0019q&!\u001f\n\u0007\u0005m\u0004M\u0001\u0003OC6,\u0017a\u00037p_.,\b/\u00128uef$B!!!\u0002\bB\u0019q&a!\n\t\u0005\u0015\u0015Q\u0002\u0002\u000b'\u000e|\u0007/Z#oiJL\bB\u0002/\u0016\u0001\u0004\t9(A\ttkB,'\u000f\n7p_.,\b/\u00128uef$B!!!\u0002\u000e\"1AL\u0006a\u0001\u0003oJA!! \u0002\u0014\u0005\tb/\u00197jI\u0006$Xm\u00117bgNLeNZ8\u0015\u0007%\n)\nC\u0004\u0002\u0018^\u0001\r!!'\u0002\u0005Q\u0004\bcA\u0018\u0002\u001c&\u0019\u0011QT\u001d\u0003\u001b\rc\u0017m]:J]\u001a|G+\u001f9f\u0003=qWm\u001e)bG.\fw-Z*d_B,G\u0003BA\u0010\u0003GCa!a\u0007\u0019\u0001\u0004q\u0015AD:d_B,GK]1og\u001a|'/\u001c\u000b\u0005\u0003S\u000b\t\f\u0006\u0003\u0002\b\u0005-\u0006\u0002CAW3\u0011\u0005\r!a,\u0002\u0005=\u0004\b#B\u0012\u0002b\u0005\u001d\u0001\"\u0002.\u001a\u0001\u0004q\u0005\u0003BA[\u0003ok\u0011aG\u0005\u0003gm\u0001"
)
public interface SymbolLoaders {
   // $FF: synthetic method
   static Tuple2 initAndEnterClassAndModule$(final SymbolLoaders $this, final Symbols.Symbol owner, final Names.TypeName name, final Function2 completer) {
      return $this.initAndEnterClassAndModule(owner, name, completer);
   }

   default Tuple2 initAndEnterClassAndModule(final Symbols.Symbol owner, final Names.TypeName name, final Function2 completer) {
      scala.reflect.internal.SymbolTable var10000 = (scala.reflect.internal.SymbolTable)this;
      boolean assert_assertion = !name.toString().endsWith("[]");
      scala.reflect.internal.SymbolTable assert_this = var10000;
      if (!assert_assertion) {
         throw assert_this.throwAssertionError(name);
      } else {
         Symbols.ClassSymbol clazz;
         Symbols.ModuleSymbol module;
         label22: {
            Position var13 = owner.newClass$default$2();
            long newClass_newFlags = 0L;
            Position newClass_pos = var13;
            Symbols.ClassSymbol var14 = owner.newClassSymbol(name, newClass_pos, newClass_newFlags);
            newClass_pos = null;
            clazz = var14;
            module = owner.newModule(name.toTermName(), owner.newModule$default$2(), 0L);
            Scopes.Scope var15 = owner.info().decls();
            Scopes.EmptyScope$ var6 = ((Scopes)this).EmptyScope();
            if (var15 == null) {
               if (var6 == null) {
                  break label22;
               }
            } else if (var15.equals(var6)) {
               break label22;
            }

            owner.info().decls().enter(clazz);
            owner.info().decls().enter(module);
         }

         this.initClassAndModule(clazz, module, (Types.LazyType)completer.apply(clazz, module));
         return new Tuple2(clazz, module);
      }
   }

   // $FF: synthetic method
   static void setAllInfos$(final SymbolLoaders $this, final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.Type info) {
      $this.setAllInfos(clazz, module, info);
   }

   default void setAllInfos(final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.Type info) {
      for(List foreach_these = new .colon.colon(clazz, new .colon.colon(module, new .colon.colon(module.moduleClass(), scala.collection.immutable.Nil..MODULE$))); !foreach_these.isEmpty(); foreach_these = (List)foreach_these.tail()) {
         ((Symbols.Symbol)foreach_these.head()).setInfo(info);
      }

   }

   // $FF: synthetic method
   static void initClassAndModule$(final SymbolLoaders $this, final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.LazyType completer) {
      $this.initClassAndModule(clazz, module, completer);
   }

   default void initClassAndModule(final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.LazyType completer) {
      this.setAllInfos(clazz, module, completer);
   }

   // $FF: synthetic method
   static void validateClassInfo$(final SymbolLoaders $this, final Types.ClassInfoType tp) {
      $this.validateClassInfo(tp);
   }

   default void validateClassInfo(final Types.ClassInfoType tp) {
      scala.reflect.internal.SymbolTable var10000 = (scala.reflect.internal.SymbolTable)this;
      boolean assert_assertion = !tp.typeSymbol().isPackageClass() || tp.decls() instanceof PackageScope;
      scala.reflect.internal.SymbolTable assert_this = var10000;
      if (!assert_assertion) {
         throw assert_this.throwAssertionError("Package must have package scope");
      }
   }

   // $FF: synthetic method
   static PackageScope newPackageScope$(final SymbolLoaders $this, final Symbols.Symbol pkgClass) {
      return $this.newPackageScope(pkgClass);
   }

   default PackageScope newPackageScope(final Symbols.Symbol pkgClass) {
      return (SymbolTable)this.new PackageScope(pkgClass);
   }

   // $FF: synthetic method
   static Scopes.Scope scopeTransform$(final SymbolLoaders $this, final Symbols.Symbol owner, final Function0 op) {
      return $this.scopeTransform(owner, op);
   }

   default Scopes.Scope scopeTransform(final Symbols.Symbol owner, final Function0 op) {
      return owner.isPackageClass() ? owner.info().decls() : (Scopes.Scope)op.apply();
   }

   // $FF: synthetic method
   static Names.TypeName $anonfun$initAndEnterClassAndModule$1(final Names.TypeName name$1) {
      return name$1;
   }

   // $FF: synthetic method
   static Symbols.Symbol $anonfun$setAllInfos$1(final Types.Type info$1, final Symbols.Symbol x$1) {
      return x$1.setInfo(info$1);
   }

   // $FF: synthetic method
   static String $anonfun$validateClassInfo$1() {
      return "Package must have package scope";
   }

   static void $init$(final SymbolLoaders $this) {
   }

   public class TopClassCompleter extends scala.reflect.internal.SymbolTable.SymLoader implements Types.FlagAssigningCompleter {
      private final Symbols.ClassSymbol clazz;
      private final Symbols.ModuleSymbol module;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public void complete(final Symbols.Symbol sym) {
         scala.reflect.internal.SymbolTable var10000;
         boolean var10001;
         label41: {
            label44: {
               this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer().debugInfo(() -> {
                  StringBuilder var10000 = (new StringBuilder(12)).append("completing ").append(sym).append("/");
                  Symbols.ClassSymbol var10001 = this.clazz;
                  if (var10001 == null) {
                     throw null;
                  } else {
                     return var10000.append(((Symbols.Symbol)var10001).fullName('.')).toString();
                  }
               });
               var10000 = (scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer();
               Symbols.ClassSymbol var2 = this.clazz;
               if (sym == null) {
                  if (var2 == null) {
                     break label44;
                  }
               } else if (sym.equals(var2)) {
                  break label44;
               }

               Symbols.ModuleSymbol var3 = this.module;
               if (sym == null) {
                  if (var3 == null) {
                     break label44;
                  }
               } else if (sym.equals(var3)) {
                  break label44;
               }

               Symbols.Symbol var4 = this.module.moduleClass();
               if (sym == null) {
                  if (var4 == null) {
                     break label44;
                  }
               } else if (sym.equals(var4)) {
                  break label44;
               }

               var10001 = false;
               break label41;
            }

            var10001 = true;
         }

         boolean assert_assertion = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            scala.reflect.internal.SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("Must be class or module");
            } else {
               assert_this = null;
               ((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer()).slowButSafeEnteringPhaseNotLaterThan(((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer()).picklerPhase(), (JFunction0.mcV.sp)() -> {
                  JavaMirrors.JavaMirror loadingMirror = this.scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer().mirrorThatLoaded(sym);
                  Class javaClass = loadingMirror.javaClass(this.clazz.javaClassName());
                  loadingMirror.unpickleClass(this.clazz, this.module, javaClass);
               });
            }
         }
      }

      public void load(final Symbols.Symbol sym) {
         this.complete(sym);
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$runtime$SymbolLoaders$TopClassCompleter$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$complete$2() {
         return "Must be class or module";
      }

      public TopClassCompleter(final Symbols.ClassSymbol clazz, final Symbols.ModuleSymbol module) {
         this.clazz = clazz;
         this.module = module;
         if (SymbolLoaders.this == null) {
            throw null;
         } else {
            this.$outer = SymbolLoaders.this;
            super();
            SymbolLoaders.this.markFlagsCompleted(clazz, module, -290463694721097408L);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class LazyPackageType extends Types.LazyType implements Types.FlagAgnosticCompleter {
      // $FF: synthetic field
      public final SymbolTable $outer;

      public void complete(final Symbols.Symbol sym) {
         scala.reflect.internal.SymbolTable var10000 = (scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer();
         boolean assert_assertion = sym.isPackageClass();
         if (var10000 == null) {
            throw null;
         } else {
            scala.reflect.internal.SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("Must be package");
            } else {
               assert_this = null;
               ((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer()).slowButSafeEnteringPhaseNotLaterThan(((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer()).picklerPhase(), (JFunction0.mcV.sp)() -> {
                  sym.setInfo((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer().new ClassInfoType(scala.collection.immutable.Nil..MODULE$, this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer().new PackageScope(sym), sym));
                  ((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer()).openPackageModule(sym, ((scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer()).openPackageModule$default$2());
                  this.scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer().markAllCompleted(sym);
               });
            }
         }
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$runtime$SymbolLoaders$LazyPackageType$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$complete$4() {
         return "Must be package";
      }

      public LazyPackageType() {
         if (SymbolLoaders.this == null) {
            throw null;
         } else {
            this.$outer = SymbolLoaders.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class PackageScope extends Scopes.Scope implements SynchronizedOps.SynchronizedScope {
      private final Symbols.Symbol pkgClass;
      private final HashSet negatives;
      private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final SymbolTable $outer;

      // $FF: synthetic method
      public boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty() {
         return super.isEmpty();
      }

      // $FF: synthetic method
      public int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size() {
         return super.size();
      }

      // $FF: synthetic method
      public Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(final Symbols.Symbol sym) {
         return super.enter(sym);
      }

      // $FF: synthetic method
      public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(final Symbols.Symbol sym, final Names.Name newname) {
         super.rehash(sym, newname);
      }

      // $FF: synthetic method
      public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Scopes.ScopeEntry e) {
         super.unlink(e);
      }

      // $FF: synthetic method
      public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Symbols.Symbol sym) {
         super.unlink(sym);
      }

      // $FF: synthetic method
      public Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(final Names.Name name) {
         return super.lookupAll(name);
      }

      // $FF: synthetic method
      public Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(final Names.Name name) {
         return super.lookupEntry(name);
      }

      // $FF: synthetic method
      public Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(final Scopes.ScopeEntry entry) {
         return super.lookupNextEntry(entry);
      }

      // $FF: synthetic method
      public List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList() {
         return super.toList();
      }

      public boolean isEmpty() {
         return SynchronizedOps.SynchronizedScope.isEmpty$(this);
      }

      public int size() {
         return SynchronizedOps.SynchronizedScope.size$(this);
      }

      public void rehash(final Symbols.Symbol sym, final Names.Name newname) {
         SynchronizedOps.SynchronizedScope.rehash$(this, sym, newname);
      }

      public void unlink(final Scopes.ScopeEntry e) {
         SynchronizedOps.SynchronizedScope.unlink$(this, (Scopes.ScopeEntry)e);
      }

      public void unlink(final Symbols.Symbol sym) {
         SynchronizedOps.SynchronizedScope.unlink$(this, (Symbols.Symbol)sym);
      }

      public Iterator lookupAll(final Names.Name name) {
         return SynchronizedOps.SynchronizedScope.lookupAll$(this, name);
      }

      public Scopes.ScopeEntry lookupNextEntry(final Scopes.ScopeEntry entry) {
         return SynchronizedOps.SynchronizedScope.lookupNextEntry$(this, entry);
      }

      public List toList() {
         return SynchronizedOps.SynchronizedScope.toList$(this);
      }

      private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock = new Object();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
      }

      public Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock() {
         return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() : this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
      }

      // $FF: synthetic method
      private Scopes.ScopeEntry super$lookupEntry(final Names.Name name) {
         return SynchronizedOps.SynchronizedScope.lookupEntry$(this, name);
      }

      public Symbols.Symbol enter(final Symbols.Symbol sym) {
         if (this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().isCompilerUniverse()) {
            return SynchronizedOps.SynchronizedScope.enter$(this, sym);
         } else {
            Scopes.ScopeEntry existing = SynchronizedOps.SynchronizedScope.lookupEntry$(this, sym.name());
            scala.reflect.internal.SymbolTable var10000 = (scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
            boolean assert_assertion = existing == null || eitherIsMethod$1(existing.sym(), sym);
            if (var10000 == null) {
               throw null;
            } else {
               scala.reflect.internal.SymbolTable assert_this = var10000;
               if (!assert_assertion) {
                  throw assert_this.throwAssertionError($anonfun$enter$1(this, sym, existing));
               } else {
                  assert_this = null;
                  return SynchronizedOps.SynchronizedScope.enter$(this, sym);
               }
            }
         }
      }

      public Symbols.Symbol enterIfNew(final Symbols.Symbol sym) {
         Scopes.ScopeEntry existing = SynchronizedOps.SynchronizedScope.lookupEntry$(this, sym.name());
         return existing == null ? this.enter(sym) : existing.sym();
      }

      public Object syncLockSynchronized(final Function0 body) {
         SymbolTable var10000 = this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (gilSynchronized_this.isCompilerUniverse()) {
               return body.apply();
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var10000 = (SymbolTable)body.apply();
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var10000;
            }
         }
      }

      public Scopes.ScopeEntry lookupEntry(final Names.Name name) {
         return (Scopes.ScopeEntry)this.syncLockSynchronized(() -> {
            Scopes.ScopeEntry e = SynchronizedOps.SynchronizedScope.lookupEntry$(this, name);
            if (e != null) {
               return e;
            } else if (this.negatives.contains(name)) {
               return null;
            } else {
               String var10000;
               if (this.pkgClass.isEmptyPackageClass()) {
                  var10000 = name.toString();
               } else {
                  StringBuilder var21 = new StringBuilder(1);
                  Symbols.Symbol var10001 = this.pkgClass;
                  if (var10001 == null) {
                     throw null;
                  }

                  var10000 = var21.append(var10001.fullName('.')).append(".").append(name).toString();
               }

               String path = var10000;
               JavaMirrors.JavaMirror currentMirror = this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().mirrorThatLoaded(this.pkgClass);
               Option var5 = currentMirror.tryJavaClass(path);
               if (!(var5 instanceof Some)) {
                  this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().debugInfo(() -> (new StringBuilder(16)).append("*** not found : ").append(path).toString());
                  HashSet var25 = this.negatives;
                  if (var25 == null) {
                     throw null;
                  } else {
                     var25.add(name);
                     return null;
                  }
               } else {
                  Class cls = (Class)((Some)var5).value();
                  JavaMirrors.JavaMirror loadingMirror = currentMirror.mirrorDefining(cls);
                  Tuple2 var22;
                  if (loadingMirror == currentMirror) {
                     var22 = this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().initAndEnterClassAndModule(this.pkgClass, name.toTypeName(), (x$2, x$3) -> this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().new TopClassCompleter(x$2, x$3));
                  } else {
                     Symbols.Symbol var26 = this.pkgClass;
                     if (var26 == null) {
                        throw null;
                     }

                     Symbols.Symbol clazz;
                     Symbols.Symbol module;
                     label79: {
                        label78: {
                           Symbols.ModuleSymbol origOwner = loadingMirror.packageNameToScala(var26.fullName('.'));
                           clazz = origOwner.info().decl((Names.Name)name.toTypeName());
                           module = origOwner.info().decl((Names.Name)name.toTermName());
                           var23 = (scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
                           Symbols.NoSymbol var12 = this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().NoSymbol();
                           if (clazz == null) {
                              if (var12 != null) {
                                 break label78;
                              }
                           } else if (!clazz.equals(var12)) {
                              break label78;
                           }

                           var27 = false;
                           break label79;
                        }

                        var27 = true;
                     }

                     boolean assert_assertion = var27;
                     if (var23 == null) {
                        throw null;
                     }

                     scala.reflect.internal.SymbolTable assert_this = var23;
                     if (!assert_assertion) {
                        throw assert_this.throwAssertionError("Missing class symbol");
                     }

                     label68: {
                        label67: {
                           assert_this = null;
                           var24 = (scala.reflect.internal.SymbolTable)this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
                           Symbols.NoSymbol var13 = this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().NoSymbol();
                           if (module == null) {
                              if (var13 != null) {
                                 break label67;
                              }
                           } else if (!module.equals(var13)) {
                              break label67;
                           }

                           var28 = false;
                           break label68;
                        }

                        var28 = true;
                     }

                     boolean assert_assertionx = var28;
                     if (var24 == null) {
                        throw null;
                     }

                     scala.reflect.internal.SymbolTable assert_this = var24;
                     if (!assert_assertionx) {
                        throw assert_this.throwAssertionError("Missing module symbol");
                     }

                     assert_this = null;
                     this.enterIfNew(clazz);
                     this.enterIfNew(module);
                     var22 = new Tuple2(clazz, module);
                  }

                  Tuple2 var8 = var22;
                  if (var8 != null) {
                     Symbols.Symbol module = (Symbols.Symbol)var8._2();
                     this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer().debugInfo(() -> (new StringBuilder(13)).append("created ").append(module).append("/").append(module.moduleClass()).append(" in ").append(this.pkgClass).toString());
                     return this.lookupEntry(name);
                  } else {
                     throw new MatchError((Object)null);
                  }
               }
            }
         });
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer() {
         return this.scala$reflect$runtime$SymbolLoaders$PackageScope$$$outer();
      }

      // $FF: synthetic method
      public static final String $anonfun$new$1() {
         return "Must be type";
      }

      private static final boolean eitherIsMethod$1(final Symbols.Symbol sym1, final Symbols.Symbol sym2) {
         return sym1.isMethod() || sym2.isMethod();
      }

      // $FF: synthetic method
      public static final String $anonfun$enter$1(final PackageScope $this, final Symbols.Symbol sym$3, final Scopes.ScopeEntry existing$1) {
         return (new StringBuilder(32)).append("pkgClass = ").append($this.pkgClass).append(", sym = ").append(sym$3).append(", existing = ").append(existing$1).toString();
      }

      // $FF: synthetic method
      public static final String $anonfun$lookupEntry$3() {
         return "Missing class symbol";
      }

      // $FF: synthetic method
      public static final String $anonfun$lookupEntry$4() {
         return "Missing module symbol";
      }

      public PackageScope(final Symbols.Symbol pkgClass) {
         this.pkgClass = pkgClass;
         if (SymbolLoaders.this == null) {
            throw null;
         } else {
            this.$outer = SymbolLoaders.this;
            super();
            scala.reflect.internal.SymbolTable var10000 = (scala.reflect.internal.SymbolTable)SymbolLoaders.this;
            boolean assert_assertion = pkgClass.isType();
            scala.reflect.internal.SymbolTable assert_this = var10000;
            if (!assert_assertion) {
               throw assert_this.throwAssertionError("Must be type");
            } else {
               assert_this = null;
               this.negatives = new HashSet();
            }
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
