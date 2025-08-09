package scala.reflect.api;

import scala.Function1;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001df!\u0003#F!\u0003\r\t\u0001TAP\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001d1\u0006A1A\u0007\u0002]3qA\u0017\u0001\u0011\u0002\u0007\u00051\fC\u0003R\u0007\u0011\u0005!\u000bC\u0003u\u0007\u0019\u0005Q\u000fC\u0003|\u0007\u0019\u0005A\u0010\u0003\u0004\u0002\u0002\r1\t!\u001e\u0005\u0007\u0003\u0007\u0019a\u0011A;\t\r\u0005\u00151A\"\u0001v\u0011\u001d\t9a\u0001D\u0001\u0003\u0013Aa!!\u0005\u0004\r\u0003)\bBBA\n\u0007\u0019\u0005Q\u000f\u0003\u0004\u0002\u0016\r1\t!\u001e\u0005\u0007\u0003/\u0019a\u0011A;\t\r\u0005e1A\"\u0001v\u0011\u0019\tYb\u0001D\u0001k\"1\u0011QD\u0002\u0007\u0002UDa!a\b\u0004\r\u0003)\bBBA\u0011\u0007\u0019\u0005Q\u000f\u0003\u0004\u0002$\r1\t!\u001e\u0005\u0007\u0003K\u0019a\u0011A;\t\r\u0005\u001d2A\"\u0001v\u0011\u0019\tIc\u0001D\u0001k\"1\u00111F\u0002\u0007\u0002UDa!!\f\u0004\r\u0003)\bBBA\u0018\u0007\u0019\u0005A\u0010\u0003\u0004\u00022\r1\t!\u001e\u0005\u0007\u0003g\u0019a\u0011\u0001?\t\r\u0005U2A\"\u0001}\u0011\u001d\t9d\u0001D\u0001\u0003sAq!!\u0011\u0004\r\u0003\tI\u0004C\u0004\u0002D\r1\t!!\u000f\t\u000f\u0005\u00153A\"\u0001\u0002:!9\u0011qI\u0002\u0007\u0002\u0005e\u0002BBA%\u0007\u0019\u0005Q\u000f\u0003\u0004\u0002L\r1\t!\u001e\u0005\u0007\u0003\u001b\u001aa\u0011A;\t\r\u0005=3A\"\u0001}\u0011\u001d\t\tf\u0001D\u0001\u0003sAa!a\u0015\u0004\r\u0003a\bBBA+\u0007\u0019\u0005Q\u000f\u0003\u0004\u0002X\r1\t\u0001 \u0005\u0007\u00033\u001aa\u0011\u0001?\u0007\u000f\u0005m3!!\u0001\u0002^!9\u0011\u0011\u000f\u0017\u0005\u0002\u0005M\u0004bBA=Y\u0019\u0005\u00111\u0010\u0005\b\u0003\u0017\u001ba\u0011AAG\u0011\u001d\tyi\u0001D\u0001\u0003\u001bCq!!%\u0004\r\u0003\ti\tC\u0004\u0002\u0014\u000e1\t!!&\t\u000f\u0005u5A\"\u0001\u0002\u0016\u001a9Q\f\u0001I\u0001$\u0003q\u0006bB05\u0005\u00045\t\u0001\u0019\u0005\bMR\u0012\rQ\"\u0001a\u0011\u001d9GG1A\u0007\u0002\u0001Dq\u0001\u001b\u001bC\u0002\u001b\u0005\u0001\rC\u0004ji\t\u0007i\u0011\u00011\t\u000f)$$\u0019!D\u0001A\"91\u000e\u000eb\u0001\u000e\u0003\u0001\u0007b\u000275\u0005\u00045\t\u0001\u0019\u0005\b[R\u0012\rQ\"\u0001a\u0011\u001dqGG1A\u0007\u0002\u0001Dqa\u001c\u001bC\u0002\u001b\u0005\u0001\rC\u0004qi\t\u0007i\u0011\u00011\t\u000fE$$\u0019!D\u0001A\"9!\u000f\u000eb\u0001\u000e\u0003\u0001\u0007bB:5\u0005\u00045\t\u0001\u0019\u0002\u0014'R\fg\u000eZ1sI\u0012+g-\u001b8ji&|gn\u001d\u0006\u0003\r\u001e\u000b1!\u00199j\u0015\tA\u0015*A\u0004sK\u001adWm\u0019;\u000b\u0003)\u000bQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001\u001bB\u0011ajT\u0007\u0002\u0013&\u0011\u0001+\u0013\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0006C\u0001(U\u0013\t)\u0016J\u0001\u0003V]&$\u0018a\u00033fM&t\u0017\u000e^5p]N,\u0012\u0001\u0017\t\u00033\u000ei\u0011\u0001\u0001\u0002\u000f\t\u00164\u0017N\\5uS>t7/\u00119j'\r\u0019Q\n\u0018\t\u00033R\u0012Qb\u0015;b]\u0012\f'\u000f\u001a+za\u0016\u001c8C\u0001\u001bN\u0003\u001d)f.\u001b;Ua\u0016,\u0012!\u0019\t\u00033\nL!a\u00193\u0003\tQK\b/Z\u0005\u0003K\u0016\u0013Q\u0001V=qKN\fqAQ=uKR\u0003X-\u0001\u0005TQ>\u0014H\u000f\u00169f\u0003\u001d\u0019\u0005.\u0019:Ua\u0016\fa!\u00138u)B,\u0017a\u0002'p]\u001e$\u0006/Z\u0001\t\r2|\u0017\r\u001e+qK\u0006IAi\\;cY\u0016$\u0006/Z\u0001\u000b\u0005>|G.Z1o)B,\u0017AB!osR\u0003X-A\u0005B]f4\u0016\r\u001c+qK\u0006I\u0011I\\=SK\u001a$\u0006/Z\u0001\n\u001f\nTWm\u0019;Ua\u0016\f!BT8uQ&tw\r\u00169f\u0003\u001dqU\u000f\u001c7Ua\u0016\f\u0011cU2bY\u0006\u0004\u0016mY6bO\u0016\u001cE.Y:t+\u00051\bCA-x\u0013\tA\u0018PA\u0006DY\u0006\u001c8oU=nE>d\u0017B\u0001>F\u0005\u001d\u0019\u00160\u001c2pYN\fAbU2bY\u0006\u0004\u0016mY6bO\u0016,\u0012! \t\u00033zL!a`=\u0003\u00195{G-\u001e7f'fl'm\u001c7\u0002\u0011\u0005s\u0017p\u00117bgN\f1\"\u00118z-\u0006d7\t\\1tg\u0006YqJ\u00196fGR\u001cE.Y:t\u0003-\te.\u001f*fM\u000ec\u0017m]:\u0016\u0005\u0005-\u0001cA-\u0002\u000e%\u0019\u0011qB=\u0003\u0015QK\b/Z*z[\n|G.A\u0005Ok2d7\t\\1tg\u0006aaj\u001c;iS:<7\t\\1tg\u0006IQK\\5u\u00072\f7o]\u0001\n\u0005f$Xm\u00117bgN\f!b\u00155peR\u001cE.Y:t\u0003%\u0019\u0005.\u0019:DY\u0006\u001c8/\u0001\u0005J]R\u001cE.Y:t\u0003%auN\\4DY\u0006\u001c8/\u0001\u0006GY>\fGo\u00117bgN\f1\u0002R8vE2,7\t\\1tg\u0006a!i\\8mK\u0006t7\t\\1tg\u0006Y1\u000b\u001e:j]\u001e\u001cE.Y:t\u0003)\u0019E.Y:t\u00072\f7o]\u0001\u000b\u0003J\u0014\u0018-_\"mCN\u001c\u0018!\u0003'jgR\u001cE.Y:t\u00031\u0001&/\u001a3fM6{G-\u001e7f\u0003QQ\u0015M^1MC:<\u0007+Y2lC\u001e,7\t\\1tg\u0006y!*\u0019<b\u0019\u0006tw\rU1dW\u0006<W-A\u0006BeJ\f\u00170T8ek2,\u0017aG!se\u0006LXj\u001c3vY\u0016|vN^3sY>\fG-\u001a3BaBd\u00170\u0006\u0002\u0002<A\u0019\u0011,!\u0010\n\u0007\u0005}\u0012P\u0001\u0006UKJl7+_7c_2\f1\"\u0011:sCf|\u0016\r\u001d9ms\u0006Y\u0011I\u001d:bs~\u001bGn\u001c8f\u00031\t%O]1z?2,gn\u001a;i\u00031\t%O]1z?V\u0004H-\u0019;f\u0003A\u0011\u0015PT1nKB\u000b'/Y7DY\u0006\u001c8/\u0001\fKCZ\f'+\u001a9fCR,G\rU1sC6\u001cE.Y:t\u0003I\u0011V\r]3bi\u0016$\u0007+\u0019:b[\u000ec\u0017m]:\u0002\u00151K7\u000f^'pIVdW-\u0001\u0006MSN$x,\u00199qYf\f\u0011BT5m\u001b>$W\u000f\\3\u0002\u0017=\u0003H/[8o\u00072\f7o]\u0001\u000b\u001d>tW-T8ek2,\u0017AC*p[\u0016lu\u000eZ;mK\n\u0001b+\u0019:Be&$\u0018p\u00117bgN\f\u0005/[\n\u0005Y5\u000by\u0006E\u0004O\u0003C\n)'a\u001b\n\u0007\u0005\r\u0014JA\u0005Gk:\u001cG/[8ocA\u0019a*a\u001a\n\u0007\u0005%\u0014JA\u0002J]R\u00042!WA7\u0013\r\ty'\u001f\u0002\u0007'fl'm\u001c7\u0002\rqJg.\u001b;?)\t\t)\bE\u0002\u0002x1j\u0011aA\u0001\u0004g\u0016\fXCAA?!\u0015\ty(!\"w\u001d\rq\u0015\u0011Q\u0005\u0004\u0003\u0007K\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003\u000f\u000bIIA\u0002TKFT1!a!J\u00031\u0001&o\u001c3vGR\u001cE.Y:t+\t\t)(A\u0007Gk:\u001cG/[8o\u00072\f7o]\u0001\u000b)V\u0004H.Z\"mCN\u001c\u0018AG*dC2\f\u0007K]5nSRLg/\u001a,bYV,7\t\\1tg\u0016\u001cXCAAL!\u0015\ty(!'w\u0013\u0011\tY*!#\u0003\t1K7\u000f^\u0001\u0019'\u000e\fG.\u0019(v[\u0016\u0014\u0018n\u0019,bYV,7\t\\1tg\u0016\u001c\b\u0003BAQ\u0003Gk\u0011!R\u0005\u0004\u0003K+%\u0001C+oSZ,'o]3"
)
public interface StandardDefinitions {
   DefinitionsApi definitions();

   static void $init$(final StandardDefinitions $this) {
   }

   public interface DefinitionsApi extends StandardTypes {
      Symbols.ClassSymbolApi ScalaPackageClass();

      Symbols.ModuleSymbolApi ScalaPackage();

      Symbols.ClassSymbolApi AnyClass();

      Symbols.ClassSymbolApi AnyValClass();

      Symbols.ClassSymbolApi ObjectClass();

      Symbols.TypeSymbolApi AnyRefClass();

      Symbols.ClassSymbolApi NullClass();

      Symbols.ClassSymbolApi NothingClass();

      Symbols.ClassSymbolApi UnitClass();

      Symbols.ClassSymbolApi ByteClass();

      Symbols.ClassSymbolApi ShortClass();

      Symbols.ClassSymbolApi CharClass();

      Symbols.ClassSymbolApi IntClass();

      Symbols.ClassSymbolApi LongClass();

      Symbols.ClassSymbolApi FloatClass();

      Symbols.ClassSymbolApi DoubleClass();

      Symbols.ClassSymbolApi BooleanClass();

      Symbols.ClassSymbolApi StringClass();

      Symbols.ClassSymbolApi ClassClass();

      Symbols.ClassSymbolApi ArrayClass();

      Symbols.ClassSymbolApi ListClass();

      Symbols.ModuleSymbolApi PredefModule();

      Symbols.ClassSymbolApi JavaLangPackageClass();

      Symbols.ModuleSymbolApi JavaLangPackage();

      Symbols.ModuleSymbolApi ArrayModule();

      Symbols.TermSymbolApi ArrayModule_overloadedApply();

      Symbols.TermSymbolApi Array_apply();

      Symbols.TermSymbolApi Array_clone();

      Symbols.TermSymbolApi Array_length();

      Symbols.TermSymbolApi Array_update();

      Symbols.ClassSymbolApi ByNameParamClass();

      Symbols.ClassSymbolApi JavaRepeatedParamClass();

      Symbols.ClassSymbolApi RepeatedParamClass();

      Symbols.ModuleSymbolApi ListModule();

      Symbols.TermSymbolApi List_apply();

      Symbols.ModuleSymbolApi NilModule();

      Symbols.ClassSymbolApi OptionClass();

      Symbols.ModuleSymbolApi NoneModule();

      Symbols.ModuleSymbolApi SomeModule();

      VarArityClassApi ProductClass();

      VarArityClassApi FunctionClass();

      VarArityClassApi TupleClass();

      List ScalaPrimitiveValueClasses();

      List ScalaNumericValueClasses();

      // $FF: synthetic method
      StandardDefinitions scala$reflect$api$StandardDefinitions$DefinitionsApi$$$outer();

      static void $init$(final DefinitionsApi $this) {
      }

      public abstract class VarArityClassApi implements Function1 {
         // $FF: synthetic field
         public final DefinitionsApi $outer;

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         public abstract Seq seq();

         // $FF: synthetic method
         public DefinitionsApi scala$reflect$api$StandardDefinitions$DefinitionsApi$VarArityClassApi$$$outer() {
            return this.$outer;
         }

         public VarArityClassApi() {
            if (DefinitionsApi.this == null) {
               throw null;
            } else {
               this.$outer = DefinitionsApi.this;
               super();
            }
         }
      }
   }

   public interface StandardTypes {
      Types.TypeApi UnitTpe();

      Types.TypeApi ByteTpe();

      Types.TypeApi ShortTpe();

      Types.TypeApi CharTpe();

      Types.TypeApi IntTpe();

      Types.TypeApi LongTpe();

      Types.TypeApi FloatTpe();

      Types.TypeApi DoubleTpe();

      Types.TypeApi BooleanTpe();

      Types.TypeApi AnyTpe();

      Types.TypeApi AnyValTpe();

      Types.TypeApi AnyRefTpe();

      Types.TypeApi ObjectTpe();

      Types.TypeApi NothingTpe();

      Types.TypeApi NullTpe();
   }
}
