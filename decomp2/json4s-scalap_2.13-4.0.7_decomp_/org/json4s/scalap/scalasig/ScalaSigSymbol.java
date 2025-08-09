package org.json4s.scalap.scalasig;

import java.lang.invoke.SerializedLambda;
import org.json4s.scalap.Rule;
import scala.Option;
import scala.Some;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q\u0001C\u0005\u0002\u0002IAQ!\b\u0001\u0005\u0002yAQ\u0001\t\u0001\u0005\u0002\u0005BQa\u000e\u0001\u0005\u0002aBQ\u0001\u0012\u0001\u0007\u0002\u0015CQ\u0001\u0014\u0001\u0005\u00025C\u0001\"\u0015\u0001\t\u0006\u0004%\tA\u0015\u0005\t?\u0002A)\u0019!C\u0001A\nq1kY1mCNKwmU=nE>d'B\u0001\u0006\f\u0003!\u00198-\u00197bg&<'B\u0001\u0007\u000e\u0003\u0019\u00198-\u00197ba*\u0011abD\u0001\u0007UN|g\u000eN:\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0011!dG\u0007\u0002\u0013%\u0011A$\u0003\u0002\u0007'fl'm\u001c7\u0002\rqJg.\u001b;?)\u0005y\u0002C\u0001\u000e\u0001\u0003%\t\u0007\u000f\u001d7z%VdW-\u0006\u0002#KQ\u00111E\f\t\u0003I\u0015b\u0001\u0001B\u0003'\u0005\t\u0007qEA\u0001B#\tA3\u0006\u0005\u0002\u0015S%\u0011!&\u0006\u0002\b\u001d>$\b.\u001b8h!\t!B&\u0003\u0002.+\t\u0019\u0011I\\=\t\u000b=\u0012\u0001\u0019\u0001\u0019\u0002\tI,H.\u001a\t\u0004cQ\u001acB\u0001\u000e3\u0013\t\u0019\u0014\"\u0001\u000bTG\u0006d\u0017mU5h\u000b:$(/\u001f)beN,'o]\u0005\u0003kY\u00121\"\u00128uef\u0004\u0016M]:fe*\u00111'C\u0001\u0012CB\u0004H._*dC2\f7+[4Sk2,WCA\u001d<)\tQD\b\u0005\u0002%w\u0011)ae\u0001b\u0001O!)qf\u0001a\u0001{A\u0019a(\u0011\u001e\u000f\u0005iy\u0014B\u0001!\n\u0003=\u00196-\u00197b'&<\u0007+\u0019:tKJ\u001c\u0018B\u0001\"D\u0005\u0019\u0001\u0016M]:fe*\u0011\u0001)C\u0001\u0006K:$(/_\u000b\u0002\rB\u0011qI\u0013\t\u00035!K!!S\u0005\u0003\u0011M\u001b\u0017\r\\1TS\u001eL!a\u0013%\u0003\u000b\u0015sGO]=\u0002\u000b%tG-\u001a=\u0016\u00039\u0003\"\u0001F(\n\u0005A+\"aA%oi\u0006A1\r[5mIJ,g.F\u0001T!\r!F,\u0007\b\u0003+js!AV-\u000e\u0003]S!\u0001W\t\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0012BA.\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u00180\u0003\u0007M+\u0017O\u0003\u0002\\+\u0005Q\u0011\r\u001e;sS\n,H/Z:\u0016\u0003\u0005\u00042\u0001\u0016/c!\tQ2-\u0003\u0002e\u0013\ti\u0011\t\u001e;sS\n,H/Z%oM>\u0004"
)
public abstract class ScalaSigSymbol implements Symbol {
   private Seq children;
   private Seq attributes;
   private volatile byte bitmap$0;

   public String path() {
      return Symbol.path$(this);
   }

   public boolean isImplicit() {
      return Flags.isImplicit$(this);
   }

   public boolean isFinal() {
      return Flags.isFinal$(this);
   }

   public boolean isPrivate() {
      return Flags.isPrivate$(this);
   }

   public boolean isProtected() {
      return Flags.isProtected$(this);
   }

   public boolean isSealed() {
      return Flags.isSealed$(this);
   }

   public boolean isOverride() {
      return Flags.isOverride$(this);
   }

   public boolean isCase() {
      return Flags.isCase$(this);
   }

   public boolean isAbstract() {
      return Flags.isAbstract$(this);
   }

   public boolean isDeferred() {
      return Flags.isDeferred$(this);
   }

   public boolean isMethod() {
      return Flags.isMethod$(this);
   }

   public boolean isModule() {
      return Flags.isModule$(this);
   }

   public boolean isInterface() {
      return Flags.isInterface$(this);
   }

   public boolean isMutable() {
      return Flags.isMutable$(this);
   }

   public boolean isParam() {
      return Flags.isParam$(this);
   }

   public boolean isPackage() {
      return Flags.isPackage$(this);
   }

   public boolean isDeprecated() {
      return Flags.isDeprecated$(this);
   }

   public boolean isCovariant() {
      return Flags.isCovariant$(this);
   }

   public boolean isCaptured() {
      return Flags.isCaptured$(this);
   }

   public boolean isByNameParam() {
      return Flags.isByNameParam$(this);
   }

   public boolean isContravariant() {
      return Flags.isContravariant$(this);
   }

   public boolean isLabel() {
      return Flags.isLabel$(this);
   }

   public boolean isInConstructor() {
      return Flags.isInConstructor$(this);
   }

   public boolean isAbstractOverride() {
      return Flags.isAbstractOverride$(this);
   }

   public boolean isLocal() {
      return Flags.isLocal$(this);
   }

   public boolean isJava() {
      return Flags.isJava$(this);
   }

   public boolean isSynthetic() {
      return Flags.isSynthetic$(this);
   }

   public boolean isStable() {
      return Flags.isStable$(this);
   }

   public boolean isStatic() {
      return Flags.isStatic$(this);
   }

   public boolean isCaseAccessor() {
      return Flags.isCaseAccessor$(this);
   }

   public boolean isTrait() {
      return Flags.isTrait$(this);
   }

   public boolean isBridge() {
      return Flags.isBridge$(this);
   }

   public boolean isAccessor() {
      return Flags.isAccessor$(this);
   }

   public boolean isSuperAccessor() {
      return Flags.isSuperAccessor$(this);
   }

   public boolean isParamAccessor() {
      return Flags.isParamAccessor$(this);
   }

   public boolean isModuleVar() {
      return Flags.isModuleVar$(this);
   }

   public boolean isMonomorphic() {
      return Flags.isMonomorphic$(this);
   }

   public boolean isLazy() {
      return Flags.isLazy$(this);
   }

   public boolean isError() {
      return Flags.isError$(this);
   }

   public boolean isOverloaded() {
      return Flags.isOverloaded$(this);
   }

   public boolean isLifted() {
      return Flags.isLifted$(this);
   }

   public boolean isMixedIn() {
      return Flags.isMixedIn$(this);
   }

   public boolean isExistential() {
      return Flags.isExistential$(this);
   }

   public boolean isExpandedName() {
      return Flags.isExpandedName$(this);
   }

   public boolean isImplementationClass() {
      return Flags.isImplementationClass$(this);
   }

   public boolean isPreSuper() {
      return Flags.isPreSuper$(this);
   }

   public Object applyRule(final Rule rule) {
      return ScalaSigEntryParsers$.MODULE$.expect(rule).apply(this.entry());
   }

   public Object applyScalaSigRule(final Rule rule) {
      return ScalaSigParsers$.MODULE$.expect(rule).apply(this.entry().scalaSig());
   }

   public abstract ScalaSig.Entry entry();

   public int index() {
      return this.entry().index();
   }

   private Seq children$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.children = ((List)this.applyScalaSigRule(ScalaSigParsers$.MODULE$.symbols())).filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$children$1(this, x$2)));
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.children;
   }

   public Seq children() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.children$lzycompute() : this.children;
   }

   private Seq attributes$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.attributes = ((List)this.applyScalaSigRule(ScalaSigParsers$.MODULE$.attributes())).filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$attributes$1(this, x$3)));
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.attributes;
   }

   public Seq attributes() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.attributes$lzycompute() : this.attributes;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$children$1(final ScalaSigSymbol $this, final Symbol x$2) {
      boolean var3;
      label23: {
         Option var10000 = x$2.parent();
         Some var2 = new Some($this);
         if (var10000 == null) {
            if (var2 == null) {
               break label23;
            }
         } else if (var10000.equals(var2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$attributes$1(final ScalaSigSymbol $this, final AttributeInfo x$3) {
      boolean var3;
      label23: {
         Symbol var10000 = x$3.symbol();
         if (var10000 == null) {
            if ($this == null) {
               break label23;
            }
         } else if (var10000.equals($this)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public ScalaSigSymbol() {
      Flags.$init$(this);
      Symbol.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
