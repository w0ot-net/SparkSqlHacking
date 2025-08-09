package org.json4s.prefs;

import java.io.Serializable;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Er!B\u0014)\u0011\u0003yc!B\u0019)\u0011\u0003\u0011\u0004\"B!\u0002\t\u0003\u0011u!B\"\u0002\u0011\u0003#e!\u0002$\u0002\u0011\u0003;\u0005BB!\u0005\t\u0003\t\u0019\u0003C\u0004_\t\u0005\u0005I\u0011I0\t\u000f\u0019$\u0011\u0011!C\u0001O\"A1\u000eBA\u0001\n\u0003\t)\u0003C\u0004s\t\u0005\u0005I\u0011I:\t\u0011i$\u0011\u0011!C\u0001\u0003SA\u0011\"!\u0001\u0005\u0003\u0003%\t%a\u0001\t\u0013\u0005\u0015A!!A\u0005B\u0005\u001d\u0001\"CA\u0005\t\u0005\u0005I\u0011BA\u0006\u000f\u0019\ti#\u0001EA;\u001a)!,\u0001EA7\")\u0011i\u0004C\u00019\"9alDA\u0001\n\u0003z\u0006b\u00024\u0010\u0003\u0003%\ta\u001a\u0005\bW>\t\t\u0011\"\u0001m\u0011\u001d\u0011x\"!A\u0005BMDqA_\b\u0002\u0002\u0013\u00051\u0010C\u0005\u0002\u0002=\t\t\u0011\"\u0011\u0002\u0004!I\u0011QA\b\u0002\u0002\u0013\u0005\u0013q\u0001\u0005\n\u0003\u0013y\u0011\u0011!C\u0005\u0003\u00179q!a\f\u0002\u0011\u0003\u000bIBB\u0004\u0002\u0014\u0005A\t)!\u0006\t\r\u0005SB\u0011AA\f\u0011\u001dq&$!A\u0005B}CqA\u001a\u000e\u0002\u0002\u0013\u0005q\r\u0003\u0005l5\u0005\u0005I\u0011AA\u000e\u0011\u001d\u0011($!A\u0005BMD\u0001B\u001f\u000e\u0002\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003\u0003Q\u0012\u0011!C!\u0003\u0007A\u0011\"!\u0002\u001b\u0003\u0003%\t%a\u0002\t\u0013\u0005%!$!A\u0005\n\u0005-\u0001\"CA\u0005\u0003\u0005\u0005I\u0011BA\u0006\r\u0015\t\u0004&!\tJ\u0011\u0015\tU\u0005\"\u0001Y\u0003Y)\u0005\u0010\u001e:bGRLwN\u001c(vY2\u001cFO]1uK\u001eL(BA\u0015+\u0003\u0015\u0001(/\u001a4t\u0015\tYC&\u0001\u0004kg>tGg\u001d\u0006\u0002[\u0005\u0019qN]4\u0004\u0001A\u0011\u0001'A\u0007\u0002Q\t1R\t\u001f;sC\u000e$\u0018n\u001c8Ok2d7\u000b\u001e:bi\u0016<\u0017pE\u0002\u0002ge\u0002\"\u0001N\u001c\u000e\u0003UR\u0011AN\u0001\u0006g\u000e\fG.Y\u0005\u0003qU\u0012a!\u00118z%\u00164\u0007C\u0001\u001e@\u001b\u0005Y$B\u0001\u001f>\u0003\tIwNC\u0001?\u0003\u0011Q\u0017M^1\n\u0005\u0001[$\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u00010\u0003\u0011YU-\u001a9\u0011\u0005\u0015#Q\"A\u0001\u0003\t-+W\r]\n\u0005\t!SU\n\u0005\u00021KM!Qe\r&N!\t!4*\u0003\u0002Mk\t9\u0001K]8ek\u000e$\bC\u0001(W\u001d\tyEK\u0004\u0002Q'6\t\u0011K\u0003\u0002S]\u00051AH]8pizJ\u0011AN\u0005\u0003+V\nq\u0001]1dW\u0006<W-\u0003\u0002A/*\u0011Q+\u000e\u000b\u0002\u0011&\"Qe\u0004\u0003\u001b\u0005!!\u0015n]1mY><8\u0003B\bI\u00156#\u0012!\u0018\t\u0003\u000b>\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#\u00011\u0011\u0005\u0005$W\"\u00012\u000b\u0005\rl\u0014\u0001\u00027b]\u001eL!!\u001a2\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005A\u0007C\u0001\u001bj\u0013\tQWGA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002naB\u0011AG\\\u0005\u0003_V\u00121!\u00118z\u0011\u001d\t8#!AA\u0002!\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001;\u0011\u0007UDX.D\u0001w\u0015\t9X'\u0001\u0006d_2dWm\u0019;j_:L!!\u001f<\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003y~\u0004\"\u0001N?\n\u0005y,$a\u0002\"p_2,\u0017M\u001c\u0005\bcV\t\t\u00111\u0001n\u0003!A\u0017m\u001d5D_\u0012,G#\u00015\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Y\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001b\u00012!YA\b\u0013\r\t\tB\u0019\u0002\u0007\u001f\nTWm\u0019;\u0003\u001bQ\u0013X-\u0019;Bg\u0006\u00137/\u001a8u'\u0011Q\u0002JS'\u0015\u0005\u0005e\u0001CA#\u001b)\ri\u0017Q\u0004\u0005\bcz\t\t\u00111\u0001i)\ra\u0018\u0011\u0005\u0005\bc\u0002\n\t\u00111\u0001n)\u0005!EcA7\u0002(!9\u0011\u000fCA\u0001\u0002\u0004AGc\u0001?\u0002,!9\u0011OCA\u0001\u0002\u0004i\u0017\u0001\u0003#jg\u0006dGn\\<\u0002\u001bQ\u0013X-\u0019;Bg\u0006\u00137/\u001a8u\u0001"
)
public abstract class ExtractionNullStrategy implements Product, Serializable {
   public Iterator productIterator() {
      return Product.productIterator$(this);
   }

   public String productPrefix() {
      return Product.productPrefix$(this);
   }

   public String productElementName(final int n) {
      return Product.productElementName$(this, n);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public ExtractionNullStrategy() {
      Product.$init$(this);
   }

   public static class Keep$ extends ExtractionNullStrategy {
      public static final Keep$ MODULE$ = new Keep$();

      public String productPrefix() {
         return "Keep";
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
         return x$1 instanceof Keep$;
      }

      public int hashCode() {
         return 2334629;
      }

      public String toString() {
         return "Keep";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Keep$.class);
      }
   }

   public static class Disallow$ extends ExtractionNullStrategy {
      public static final Disallow$ MODULE$ = new Disallow$();

      public String productPrefix() {
         return "Disallow";
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
         return x$1 instanceof Disallow$;
      }

      public int hashCode() {
         return 335883163;
      }

      public String toString() {
         return "Disallow";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Disallow$.class);
      }
   }

   public static class TreatAsAbsent$ extends ExtractionNullStrategy {
      public static final TreatAsAbsent$ MODULE$ = new TreatAsAbsent$();

      public String productPrefix() {
         return "TreatAsAbsent";
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
         return x$1 instanceof TreatAsAbsent$;
      }

      public int hashCode() {
         return -985053147;
      }

      public String toString() {
         return "TreatAsAbsent";
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(TreatAsAbsent$.class);
      }
   }
}
