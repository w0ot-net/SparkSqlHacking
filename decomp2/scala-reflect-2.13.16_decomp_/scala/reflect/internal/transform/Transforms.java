package scala.reflect.internal.transform;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.SymbolTable;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.TypeMaps;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b!C\t\u0013!\u0003\r\taGA\u0011\u0011\u0015\u0001\u0003\u0001\"\u0001\"\r\u0011)\u0003\u0001\u0002\u0014\t\u0011!\u0012!\u0011!S\u0001\n%BQa\u000e\u0002\u0005\u0002aB\u0011\u0002\u0010\u0002A\u0002\u0003\u0005\u000b\u0015\u0002\u0017\t\ru\u0012\u0001\u0015)\u0003?\u0011\u0015\t%\u0001\"\u0001C\u0011\u0015\u0019%\u0001\"\u0001E\u0011\u001d)\u0005A1Q\u0005\n\u0019CqA\u0015\u0001CB\u0013%1\u000bC\u0004\\\u0001\t\u0007K\u0011\u0002/\t\u000b\u0011\u0004A\u0011A3\t\u000b\u0019\u0004A\u0011A4\t\u000b!\u0004A\u0011A5\t\u000b)\u0004A\u0011A6\t\r)\u0004A\u0011AA\u0004\u0005)!&/\u00198tM>\u0014Xn\u001d\u0006\u0003'Q\t\u0011\u0002\u001e:b]N4wN]7\u000b\u0005U1\u0012\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005]A\u0012a\u0002:fM2,7\r\u001e\u0006\u00023\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u001d!\tib$D\u0001\u0019\u0013\ty\u0002D\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0002\"!H\u0012\n\u0005\u0011B\"\u0001B+oSR\u0014A\u0001T1{sV\u0011qEL\n\u0003\u0005q\t!a\u001c9\u0011\u0007uQC&\u0003\u0002,1\tAAHY=oC6,g\b\u0005\u0002.]1\u0001A!B\u0018\u0003\u0005\u0004\u0001$!\u0001+\u0012\u0005E\"\u0004CA\u000f3\u0013\t\u0019\u0004DA\u0004O_RD\u0017N\\4\u0011\u0005u)\u0014B\u0001\u001c\u0019\u0005\r\te._\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005eZ\u0004c\u0001\u001e\u0003Y5\t\u0001\u0001\u0003\u0004)\t\u0011\u0005\r!K\u0001\u0006m\u0006dW/Z\u0001\u000b?&\u001cH)\u001a4j]\u0016$\u0007CA\u000f@\u0013\t\u0001\u0005DA\u0004C_>dW-\u00198\u0002\u0013%\u001cH)\u001a4j]\u0016$W#\u0001 \u0002\u000b\u0019|'oY3\u0016\u00031\n1\"\u001e8dkJ\u0014\u0018\u0010T1{sV\tq\tE\u0002;\u0005!\u00132!\u0013\u000fL\r\u0011Q\u0015\u0002\u0001%\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u00051kU\"\u0001\n\n\u00059\u0013\"aB+o\u0007V\u0014(/\u001f\u0005\b!&\u0013\r\u0011\"\u0001R\u0003\u00199Gn\u001c2bYV\t!(A\u0006fe\u0006\u001cXO]3MCjLX#\u0001+\u0011\u0007i\u0012QKE\u0002W9]3AA\u0013\u0006\u0001+B\u0011A\nW\u0005\u00033J\u0011q!\u0012:bgV\u0014X\rC\u0004Q-\n\u0007I\u0011A)\u0002\u001fA|7\u000f^#sCN,(/\u001a'buf,\u0012!\u0018\t\u0004u\tq&cA0\u001dA\u001a!!j\u0003\u0001_!\ta\u0015-\u0003\u0002c%\tY\u0001k\\:u\u000bJ\f7/\u001e:f\u0011\u001d\u0001vL1A\u0005\u0002E\u000bq!\u001e8dkJ\u0014\u00180F\u0001I\u0003\u001d)'/Y:ve\u0016,\u0012!V\u0001\fa>\u001cH/\u0012:bgV\u0014X-F\u0001_\u0003=!(/\u00198tM>\u0014X.\u001a3UsB,GC\u00017}a\ti\u0007\u000f\u0005\u0002oq:\u0011qn\u0019\t\u0003[A$\u0011\"]\b\u0002\u0002\u0003\u0005)\u0011\u0001:\u0003#M$\u0018MY5mSj,'\u000fJ\u0019/if\u0004X-\u0005\u00022gJ\u0019AOX;\u0007\t)\u0003\u0001a\u001d\t\u0003;YL!a\u001e\r\u0003\u0013MKgn\u001a7fi>t\u0017BA={\u0005\u0011!\u0016\u0010]3\n\u0005m$\"!\u0002+za\u0016\u001c\b\"B?\u0010\u0001\u0004q\u0018aA:z[B\u0011!h`\u0005\u0005\u0003\u0003\t\u0019A\u0001\u0004Ts6\u0014w\u000e\\\u0005\u0004\u0003\u000b!\"aB*z[\n|Gn\u001d\u000b\u0005\u0003\u0013\tY\u0002\r\u0003\u0002\f\u0005E\u0001cAA\u0007q:\u0019\u0011qB2\u0011\u00075\n\t\u0002B\u0006\u0002\u0014A\t\t\u0011!A\u0003\u0002\u0005U!aB02]QL\b/Z\t\u0004c\u0005]!\u0003BA\r=V4QA\u0013\u0001\u0001\u0003/Aq!!\b\u0011\u0001\u0004\ty\"A\u0002ua\u0016\u0004\"A\u000f=\u0011\t\u0005\r\u0012QE\u0007\u0002)%\u0019\u0011q\u0005\u000b\u0003\u0017MKXNY8m)\u0006\u0014G.\u001a"
)
public interface Transforms {
   void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq(final Lazy x$1);

   void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq(final Lazy x$1);

   void scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq(final Lazy x$1);

   Lazy scala$reflect$internal$transform$Transforms$$uncurryLazy();

   Lazy scala$reflect$internal$transform$Transforms$$erasureLazy();

   Lazy scala$reflect$internal$transform$Transforms$$postErasureLazy();

   // $FF: synthetic method
   static UnCurry uncurry$(final Transforms $this) {
      return $this.uncurry();
   }

   default UnCurry uncurry() {
      return (UnCurry)this.scala$reflect$internal$transform$Transforms$$uncurryLazy().force();
   }

   // $FF: synthetic method
   static Erasure erasure$(final Transforms $this) {
      return $this.erasure();
   }

   default Erasure erasure() {
      return (Erasure)this.scala$reflect$internal$transform$Transforms$$erasureLazy().force();
   }

   // $FF: synthetic method
   static PostErasure postErasure$(final Transforms $this) {
      return $this.postErasure();
   }

   default PostErasure postErasure() {
      return (PostErasure)this.scala$reflect$internal$transform$Transforms$$postErasureLazy().force();
   }

   // $FF: synthetic method
   static Types.Type transformedType$(final Transforms $this, final Symbols.Symbol sym) {
      return $this.transformedType(sym);
   }

   default Types.Type transformedType(final Symbols.Symbol sym) {
      return this.postErasure().transformInfo(sym, this.erasure().transformInfo(sym, this.uncurry().transformInfo(sym, sym.info())));
   }

   // $FF: synthetic method
   static Types.Type transformedType$(final Transforms $this, final Types.Type tpe) {
      return $this.transformedType(tpe);
   }

   default Types.Type transformedType(final Types.Type tpe) {
      Symbols.Symbol symbol = tpe.widen().typeSymbol();
      if (symbol == null) {
         throw null;
      } else {
         Erasure.ErasureMap erasureMap = (Erasure.ErasureMap)(symbol.hasFlag(1152921504606846976L) ? this.erasure().scala3Erasure() : this.erasure().scalaErasure());
         return this.postErasure().elimErasedValueType().apply(erasureMap.apply(this.uncurry().uncurry().apply(tpe)));
      }
   }

   static void $init$(final Transforms $this) {
      $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$uncurryLazy_$eq((SymbolTable)$this.new Lazy(() -> new UnCurry() {
            private final SymbolTable global = Transforms.this;
            private volatile UnCurry.VarargsSymbolAttachment$ VarargsSymbolAttachment$module;
            private TypeMaps.TypeMap uncurry;
            private volatile UnCurry.DesugaredParameterType$ DesugaredParameterType$module;
            private TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType;

            public Types.Type transformInfo(final Symbols.Symbol sym, final Types.Type tp) {
               return UnCurry.transformInfo$(this, sym, tp);
            }

            public UnCurry.VarargsSymbolAttachment$ VarargsSymbolAttachment() {
               if (this.VarargsSymbolAttachment$module == null) {
                  this.VarargsSymbolAttachment$lzycompute$1();
               }

               return this.VarargsSymbolAttachment$module;
            }

            public TypeMaps.TypeMap uncurry() {
               return this.uncurry;
            }

            public UnCurry.DesugaredParameterType$ DesugaredParameterType() {
               if (this.DesugaredParameterType$module == null) {
                  this.DesugaredParameterType$lzycompute$1();
               }

               return this.DesugaredParameterType$module;
            }

            public TypeMaps.TypeMap scala$reflect$internal$transform$UnCurry$$uncurryType() {
               return this.scala$reflect$internal$transform$UnCurry$$uncurryType;
            }

            public void scala$reflect$internal$transform$UnCurry$_setter_$uncurry_$eq(final TypeMaps.TypeMap x$1) {
               this.uncurry = x$1;
            }

            public final void scala$reflect$internal$transform$UnCurry$_setter_$scala$reflect$internal$transform$UnCurry$$uncurryType_$eq(final TypeMaps.TypeMap x$1) {
               this.scala$reflect$internal$transform$UnCurry$$uncurryType = x$1;
            }

            public SymbolTable global() {
               return this.global;
            }

            private final void VarargsSymbolAttachment$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.VarargsSymbolAttachment$module == null) {
                     this.VarargsSymbolAttachment$module = new UnCurry.VarargsSymbolAttachment$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void DesugaredParameterType$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.DesugaredParameterType$module == null) {
                     this.DesugaredParameterType$module = new UnCurry.DesugaredParameterType$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            public {
               UnCurry.$init$(this);
               Statics.releaseFence();
            }
         }));
      $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$erasureLazy_$eq((SymbolTable)$this.new Lazy(() -> new Erasure() {
            private final SymbolTable global = Transforms.this;
            private volatile Erasure.GenericArray$ GenericArray$module;
            private volatile Erasure.scalaErasure$ scalaErasure$module;
            private volatile Erasure.scala3Erasure$ scala3Erasure$module;
            private volatile Erasure.specialScalaErasure$ specialScalaErasure$module;
            private volatile Erasure.specialScala3Erasure$ specialScala3Erasure$module;
            private volatile Erasure.javaErasure$ javaErasure$module;
            private volatile Erasure.verifiedJavaErasure$ verifiedJavaErasure$module;
            private volatile Erasure.boxingErasure$ boxingErasure$module;
            private volatile Erasure.boxing3Erasure$ boxing3Erasure$module;

            public int unboundedGenericArrayLevel(final Types.Type tp) {
               return Erasure.unboundedGenericArrayLevel$(this, tp);
            }

            public Types.Type rebindInnerClass(final Types.Type pre, final Symbols.Symbol cls) {
               return Erasure.rebindInnerClass$(this, pre, cls);
            }

            public Types.Type erasedValueClassArg(final Types.TypeRef tref) {
               return Erasure.erasedValueClassArg$(this, tref);
            }

            public boolean valueClassIsParametric(final Symbols.Symbol clazz) {
               return Erasure.valueClassIsParametric$(this, clazz);
            }

            public boolean verifyJavaErasure() {
               return Erasure.verifyJavaErasure$(this);
            }

            public Erasure.ErasureMap erasure(final Symbols.Symbol sym) {
               return Erasure.erasure$(this, sym);
            }

            public Types.Type specialErasure(final Symbols.Symbol sym, final Types.Type tp) {
               return Erasure.specialErasure$(this, sym, tp);
            }

            public Types.Type specialConstructorErasure(final Symbols.Symbol clazz, final Types.Type tpe) {
               return Erasure.specialConstructorErasure$(this, clazz, tpe);
            }

            public Erasure.ErasureMap specialScalaErasureFor(final Symbols.Symbol sym) {
               return Erasure.specialScalaErasureFor$(this, sym);
            }

            public Types.Type intersectionDominator(final List parents) {
               return Erasure.intersectionDominator$(this, parents);
            }

            public Types.Type transparentDealias(final Symbols.Symbol sym, final Types.Type pre, final Symbols.Symbol owner) {
               return Erasure.transparentDealias$(this, sym, pre, owner);
            }

            public Types.Type transformInfo(final Symbols.Symbol sym, final Types.Type tp) {
               return Erasure.transformInfo$(this, sym, tp);
            }

            public Erasure.GenericArray$ GenericArray() {
               if (this.GenericArray$module == null) {
                  this.GenericArray$lzycompute$1();
               }

               return this.GenericArray$module;
            }

            public Erasure.scalaErasure$ scalaErasure() {
               if (this.scalaErasure$module == null) {
                  this.scalaErasure$lzycompute$1();
               }

               return this.scalaErasure$module;
            }

            public Erasure.scala3Erasure$ scala3Erasure() {
               if (this.scala3Erasure$module == null) {
                  this.scala3Erasure$lzycompute$1();
               }

               return this.scala3Erasure$module;
            }

            public Erasure.specialScalaErasure$ specialScalaErasure() {
               if (this.specialScalaErasure$module == null) {
                  this.specialScalaErasure$lzycompute$1();
               }

               return this.specialScalaErasure$module;
            }

            public Erasure.specialScala3Erasure$ specialScala3Erasure() {
               if (this.specialScala3Erasure$module == null) {
                  this.specialScala3Erasure$lzycompute$1();
               }

               return this.specialScala3Erasure$module;
            }

            public Erasure.javaErasure$ javaErasure() {
               if (this.javaErasure$module == null) {
                  this.javaErasure$lzycompute$1();
               }

               return this.javaErasure$module;
            }

            public Erasure.verifiedJavaErasure$ verifiedJavaErasure() {
               if (this.verifiedJavaErasure$module == null) {
                  this.verifiedJavaErasure$lzycompute$1();
               }

               return this.verifiedJavaErasure$module;
            }

            public Erasure.boxingErasure$ boxingErasure() {
               if (this.boxingErasure$module == null) {
                  this.boxingErasure$lzycompute$1();
               }

               return this.boxingErasure$module;
            }

            public Erasure.boxing3Erasure$ boxing3Erasure() {
               if (this.boxing3Erasure$module == null) {
                  this.boxing3Erasure$lzycompute$1();
               }

               return this.boxing3Erasure$module;
            }

            public SymbolTable global() {
               return this.global;
            }

            private final void GenericArray$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.GenericArray$module == null) {
                     this.GenericArray$module = new Erasure.GenericArray$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void scalaErasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.scalaErasure$module == null) {
                     this.scalaErasure$module = new Erasure.scalaErasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void scala3Erasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.scala3Erasure$module == null) {
                     this.scala3Erasure$module = new Erasure.scala3Erasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void specialScalaErasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.specialScalaErasure$module == null) {
                     this.specialScalaErasure$module = new Erasure.specialScalaErasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void specialScala3Erasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.specialScala3Erasure$module == null) {
                     this.specialScala3Erasure$module = new Erasure.specialScala3Erasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void javaErasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.javaErasure$module == null) {
                     this.javaErasure$module = new Erasure.javaErasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void verifiedJavaErasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.verifiedJavaErasure$module == null) {
                     this.verifiedJavaErasure$module = new Erasure.verifiedJavaErasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void boxingErasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.boxingErasure$module == null) {
                     this.boxingErasure$module = new Erasure.boxingErasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }

            private final void boxing3Erasure$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.boxing3Erasure$module == null) {
                     this.boxing3Erasure$module = new Erasure.boxing3Erasure$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }
         }));
      $this.scala$reflect$internal$transform$Transforms$_setter_$scala$reflect$internal$transform$Transforms$$postErasureLazy_$eq((SymbolTable)$this.new Lazy(() -> new PostErasure() {
            private final SymbolTable global = Transforms.this;
            private volatile PostErasure.elimErasedValueType$ elimErasedValueType$module;

            public Types.Type transformInfo(final Symbols.Symbol sym, final Types.Type tp) {
               return PostErasure.transformInfo$(this, sym, tp);
            }

            public PostErasure.elimErasedValueType$ elimErasedValueType() {
               if (this.elimErasedValueType$module == null) {
                  this.elimErasedValueType$lzycompute$1();
               }

               return this.elimErasedValueType$module;
            }

            public SymbolTable global() {
               return this.global;
            }

            private final void elimErasedValueType$lzycompute$1() {
               synchronized(this){}

               try {
                  if (this.elimErasedValueType$module == null) {
                     this.elimErasedValueType$module = new PostErasure.elimErasedValueType$();
                  }
               } catch (Throwable var2) {
                  throw var2;
               }

            }
         }));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class Lazy {
      private final Function0 op;
      private Object value;
      private boolean _isDefined;
      // $FF: synthetic field
      public final SymbolTable $outer;

      public boolean isDefined() {
         return this._isDefined;
      }

      public Object force() {
         if (!this.isDefined()) {
            this.value = this.op.apply();
            this._isDefined = true;
         }

         return this.value;
      }

      // $FF: synthetic method
      public SymbolTable scala$reflect$internal$transform$Transforms$Lazy$$$outer() {
         return this.$outer;
      }

      public Lazy(final Function0 op) {
         this.op = op;
         if (Transforms.this == null) {
            throw null;
         } else {
            this.$outer = Transforms.this;
            super();
            this._isDefined = false;
         }
      }
   }
}
