package breeze.signal;

import breeze.util.Opt;
import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055h!\u0002\u001f>\u0003\u0003\u0011\u0005\"B%\u0001\t\u0003Qu!B'>\u0011\u0003qe!\u0002\u001f>\u0011\u0003y\u0005\"B%\u0004\t\u00031v!B,\u0004\u0011\u0003Cf!\u0002.\u0004\u0011\u0003[\u0006\"B%\u0007\t\u0003Y\u0007b\u00027\u0007\u0003\u0003%\t%\u001c\u0005\bm\u001a\t\t\u0011\"\u0001x\u0011\u001dYh!!A\u0005\u0002qD\u0011\"!\u0002\u0007\u0003\u0003%\t%a\u0002\t\u0013\u0005Ua!!A\u0005\u0002\u0005]\u0001\"CA\u0011\r\u0005\u0005I\u0011IA\u0012\u0011%\t)CBA\u0001\n\u0003\n9\u0003C\u0005\u0002*\u0019\t\t\u0011\"\u0003\u0002,\u001d9\u00111G\u0002\t\u0002\u0006UbaBA\u001c\u0007!\u0005\u0015\u0011\b\u0005\u0007\u0013F!\t!a\u000f\t\u000f1\f\u0012\u0011!C![\"9a/EA\u0001\n\u00039\b\u0002C>\u0012\u0003\u0003%\t!!\u0010\t\u0013\u0005\u0015\u0011#!A\u0005B\u0005\u001d\u0001\"CA\u000b#\u0005\u0005I\u0011AA!\u0011%\t\t#EA\u0001\n\u0003\n\u0019\u0003C\u0005\u0002&E\t\t\u0011\"\u0011\u0002(!I\u0011\u0011F\t\u0002\u0002\u0013%\u00111\u0006\u0004\u0007\u0003\u000b\u001a\u0001)a\u0012\t\u0015\u0005-3D!f\u0001\n\u0003\ti\u0005\u0003\u0006\u0002`m\u0011\t\u0012)A\u0005\u0003\u001fBa!S\u000e\u0005\u0002\u0005\u0005\u0004\"CA47\u0005\u0005I\u0011AA5\u0011%\t)hGI\u0001\n\u0003\t9\bC\u0004m7\u0005\u0005I\u0011I7\t\u000fY\\\u0012\u0011!C\u0001o\"A1pGA\u0001\n\u0003\t\t\nC\u0005\u0002\u0006m\t\t\u0011\"\u0011\u0002\b!I\u0011QC\u000e\u0002\u0002\u0013\u0005\u0011Q\u0013\u0005\n\u00033[\u0012\u0011!C!\u00037C\u0011\"!\t\u001c\u0003\u0003%\t%a\t\t\u0013\u0005\u00152$!A\u0005B\u0005\u001d\u0002\"CAP7\u0005\u0005I\u0011IAQ\u000f%\t)kAA\u0001\u0012\u0003\t9KB\u0005\u0002F\r\t\t\u0011#\u0001\u0002*\"1\u0011j\u000bC\u0001\u0003kC\u0011\"!\n,\u0003\u0003%)%a\n\t\u0013\u0005]6&!A\u0005\u0002\u0006e\u0006\"CAcW\u0005\u0005I\u0011QAd\u0011%\tIcKA\u0001\n\u0013\tYcB\u0004\u0002\\\u000eA\t)!8\u0007\u000f\u0005}7\u0001#!\u0002b\"1\u0011J\rC\u0001\u0003GDq\u0001\u001c\u001a\u0002\u0002\u0013\u0005S\u000eC\u0004we\u0005\u0005I\u0011A<\t\u0011m\u0014\u0014\u0011!C\u0001\u0003KD\u0011\"!\u00023\u0003\u0003%\t%a\u0002\t\u0013\u0005U!'!A\u0005\u0002\u0005%\b\"CA\u0011e\u0005\u0005I\u0011IA\u0012\u0011%\t)CMA\u0001\n\u0003\n9\u0003C\u0005\u0002*I\n\t\u0011\"\u0003\u0002,\tQq\n\u001d;QC\u0012$\u0017N\\4\u000b\u0005yz\u0014AB:jO:\fGNC\u0001A\u0003\u0019\u0011'/Z3{K\u000e\u00011C\u0001\u0001D!\t!u)D\u0001F\u0015\t1u(\u0001\u0003vi&d\u0017B\u0001%F\u0005\ry\u0005\u000f^\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0003\"\u0001\u0014\u0001\u000e\u0003u\n!b\u00149u!\u0006$G-\u001b8h!\ta5a\u0005\u0002\u0004!B\u0011\u0011\u000bV\u0007\u0002%*\t1+A\u0003tG\u0006d\u0017-\u0003\u0002V%\n1\u0011I\\=SK\u001a$\u0012AT\u0001\t\u0007f\u001cG.[2bYB\u0011\u0011LB\u0007\u0002\u0007\tA1)_2mS\u000e\fGn\u0005\u0003\u0007\u0017r{\u0006CA)^\u0013\tq&KA\u0004Qe>$Wo\u0019;\u0011\u0005\u0001DgBA1g\u001d\t\u0011W-D\u0001d\u0015\t!\u0017)\u0001\u0004=e>|GOP\u0005\u0002'&\u0011qMU\u0001\ba\u0006\u001c7.Y4f\u0013\tI'N\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002h%R\t\u0001,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002]B\u0011q\u000e^\u0007\u0002a*\u0011\u0011O]\u0001\u0005Y\u0006twMC\u0001t\u0003\u0011Q\u0017M^1\n\u0005U\u0004(AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001y!\t\t\u00160\u0003\u0002{%\n\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019Q0!\u0001\u0011\u0005Es\u0018BA@S\u0005\r\te.\u001f\u0005\t\u0003\u0007Q\u0011\u0011!a\u0001q\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0003\u0011\u000b\u0005-\u0011\u0011C?\u000e\u0005\u00055!bAA\b%\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005M\u0011Q\u0002\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u001a\u0005}\u0001cA)\u0002\u001c%\u0019\u0011Q\u0004*\u0003\u000f\t{w\u000e\\3b]\"A\u00111\u0001\u0007\u0002\u0002\u0003\u0007Q0\u0001\u0005iCND7i\u001c3f)\u0005A\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u00039\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\f\u0011\u0007=\fy#C\u0002\u00022A\u0014aa\u00142kK\u000e$\u0018\u0001\u0003\"pk:$\u0017M]=\u0011\u0005e\u000b\"\u0001\u0003\"pk:$\u0017M]=\u0014\tEYEl\u0018\u000b\u0003\u0003k!2!`A \u0011!\t\u0019!FA\u0001\u0002\u0004AH\u0003BA\r\u0003\u0007B\u0001\"a\u0001\u0018\u0003\u0003\u0005\r! \u0002\t-\u0006dW/Z(qiV!\u0011\u0011JA*'\u0011Y2\nX0\u0002\u000bY\fG.^3\u0016\u0005\u0005=\u0003\u0003BA)\u0003'b\u0001\u0001B\u0004\u0002Vm\u0011\r!a\u0016\u0003\u0003Q\u000b2!!\u0017~!\r\t\u00161L\u0005\u0004\u0003;\u0012&a\u0002(pi\"LgnZ\u0001\u0007m\u0006dW/\u001a\u0011\u0015\t\u0005\r\u0014Q\r\t\u00053n\ty\u0005C\u0004\u0002Ly\u0001\r!a\u0014\u0002\t\r|\u0007/_\u000b\u0005\u0003W\n\t\b\u0006\u0003\u0002n\u0005M\u0004\u0003B-\u001c\u0003_\u0002B!!\u0015\u0002r\u00119\u0011QK\u0010C\u0002\u0005]\u0003\"CA&?A\u0005\t\u0019AA8\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B!!\u001f\u0002\u0010V\u0011\u00111\u0010\u0016\u0005\u0003\u001f\nih\u000b\u0002\u0002\u0000A!\u0011\u0011QAF\u001b\t\t\u0019I\u0003\u0003\u0002\u0006\u0006\u001d\u0015!C;oG\",7m[3e\u0015\r\tIIU\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAG\u0003\u0007\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u001d\t)\u0006\tb\u0001\u0003/\"2!`AJ\u0011!\t\u0019aIA\u0001\u0002\u0004AH\u0003BA\r\u0003/C\u0001\"a\u0001&\u0003\u0003\u0005\r!`\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002o\u0003;C\u0001\"a\u0001'\u0003\u0003\u0005\r\u0001_\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005e\u00111\u0015\u0005\t\u0003\u0007I\u0013\u0011!a\u0001{\u0006Aa+\u00197vK>\u0003H\u000f\u0005\u0002ZWM!1\u0006UAV!\u0011\ti+a-\u000e\u0005\u0005=&bAAYe\u0006\u0011\u0011n\\\u0005\u0004S\u0006=FCAAT\u0003\u0015\t\u0007\u000f\u001d7z+\u0011\tY,!1\u0015\t\u0005u\u00161\u0019\t\u00053n\ty\f\u0005\u0003\u0002R\u0005\u0005GaBA+]\t\u0007\u0011q\u000b\u0005\b\u0003\u0017r\u0003\u0019AA`\u0003\u001d)h.\u00199qYf,B!!3\u0002TR!\u00111ZAk!\u0015\t\u0016QZAi\u0013\r\tyM\u0015\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\u0005E\u00131\u001b\u0003\b\u0003+z#\u0019AA,\u0011%\t9nLA\u0001\u0002\u0004\tI.A\u0002yIA\u0002B!W\u000e\u0002R\u0006!!,\u001a:p!\tI&G\u0001\u0003[KJ|7\u0003\u0002\u001aL9~#\"!!8\u0015\u0007u\f9\u000f\u0003\u0005\u0002\u0004Y\n\t\u00111\u0001y)\u0011\tI\"a;\t\u0011\u0005\r\u0001(!AA\u0002u\u0004"
)
public abstract class OptPadding extends Opt {
   public static class Cyclical$ extends OptPadding implements Product, Serializable {
      public static final Cyclical$ MODULE$ = new Cyclical$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Cyclical";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Cyclical$;
      }

      public int hashCode() {
         return -539776316;
      }

      public String toString() {
         return "Cyclical";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Cyclical$.class);
      }
   }

   public static class Boundary$ extends OptPadding implements Product, Serializable {
      public static final Boundary$ MODULE$ = new Boundary$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Boundary";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Boundary$;
      }

      public int hashCode() {
         return -2050667446;
      }

      public String toString() {
         return "Boundary";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Boundary$.class);
      }
   }

   public static class ValueOpt extends OptPadding implements Product, Serializable {
      private final Object value;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Object value() {
         return this.value;
      }

      public ValueOpt copy(final Object value) {
         return new ValueOpt(value);
      }

      public Object copy$default$1() {
         return this.value();
      }

      public String productPrefix() {
         return "ValueOpt";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         Object var10000;
         switch (x$1) {
            case 0:
               var10000 = this.value();
               break;
            default:
               var10000 = Statics.ioobe(x$1);
         }

         return var10000;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ValueOpt;
      }

      public String productElementName(final int x$1) {
         String var10000;
         switch (x$1) {
            case 0:
               var10000 = "value";
               break;
            default:
               var10000 = (String)Statics.ioobe(x$1);
         }

         return var10000;
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public String toString() {
         return .MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var10000;
         if (this != x$1) {
            label49: {
               boolean var2;
               if (x$1 instanceof ValueOpt) {
                  var2 = true;
               } else {
                  var2 = false;
               }

               if (var2) {
                  ValueOpt var4 = (ValueOpt)x$1;
                  if (BoxesRunTime.equals(this.value(), var4.value()) && var4.canEqual(this)) {
                     break label49;
                  }
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public ValueOpt(final Object value) {
         this.value = value;
         Product.$init$(this);
      }
   }

   public static class ValueOpt$ implements Serializable {
      public static final ValueOpt$ MODULE$ = new ValueOpt$();

      public final String toString() {
         return "ValueOpt";
      }

      public ValueOpt apply(final Object value) {
         return new ValueOpt(value);
      }

      public Option unapply(final ValueOpt x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.value()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ValueOpt$.class);
      }
   }

   public static class Zero$ extends OptPadding implements Product, Serializable {
      public static final Zero$ MODULE$ = new Zero$();

      static {
         Product.$init$(MODULE$);
      }

      public String productElementName(final int n) {
         return Product.productElementName$(this, n);
      }

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String productPrefix() {
         return "Zero";
      }

      public int productArity() {
         return 0;
      }

      public Object productElement(final int x$1) {
         Object var2 = Statics.ioobe(x$1);
         return var2;
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof Zero$;
      }

      public int hashCode() {
         return 2781896;
      }

      public String toString() {
         return "Zero";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Zero$.class);
      }
   }
}
