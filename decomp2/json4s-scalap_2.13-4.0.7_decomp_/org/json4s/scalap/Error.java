package org.json4s.scalap;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]c\u0001\u0002\f\u0018\u0001zA\u0001\"\u0011\u0001\u0003\u0016\u0004%\tA\u0011\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005I!)A\t\u0001C\u0001\u000b\"9\u0001\nAA\u0001\n\u0003I\u0005bB(\u0001#\u0003%\t\u0001\u0015\u0005\b;\u0002\t\t\u0011\"\u0011_\u0011\u001d9\u0007!!A\u0005\u0002!Dq\u0001\u001c\u0001\u0002\u0002\u0013\u0005Q\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001dI\u0011\u0011C\f\u0002\u0002#\u0005\u00111\u0003\u0004\t-]\t\t\u0011#\u0001\u0002\u0016!1A\t\u0005C\u0001\u0003OA\u0011\"a\u0002\u0011\u0003\u0003%)%!\u0003\t\u0013\u0005%\u0002#!A\u0005\u0002\u0006-\u0002\"CA\u001c!\u0005\u0005I\u0011QA\u001d\u0011%\ti\u0005EA\u0001\n\u0013\tyEA\u0003FeJ|'O\u0003\u0002\u00193\u000511oY1mCBT!AG\u000e\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005a\u0012aA8sO\u000e\u0001QCA\u0010''\u0011\u0001\u0001EM\u001b\u0011\u0007\u0005\u0012C%D\u0001\u0018\u0013\t\u0019sCA\u0005O_N+8mY3tgB\u0011QE\n\u0007\u0001\t\u00199\u0003\u0001\"b\u0001Q\t\t\u0001,\u0005\u0002*_A\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t9aj\u001c;iS:<\u0007C\u0001\u00161\u0013\t\t4FA\u0002B]f\u0004\"AK\u001a\n\u0005QZ#a\u0002)s_\u0012,8\r\u001e\t\u0003myr!a\u000e\u001f\u000f\u0005aZT\"A\u001d\u000b\u0005ij\u0012A\u0002\u001fs_>$h(C\u0001-\u0013\ti4&A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0002%\u0001D*fe&\fG.\u001b>bE2,'BA\u001f,\u0003\u0015)'O]8s+\u0005!\u0013AB3se>\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u0003\r\u001e\u00032!\t\u0001%\u0011\u0015\t5\u00011\u0001%\u0003\u0011\u0019w\u000e]=\u0016\u0005)kECA&O!\r\t\u0003\u0001\u0014\t\u0003K5#Qa\n\u0003C\u0002!Bq!\u0011\u0003\u0011\u0002\u0003\u0007A*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005EcV#\u0001*+\u0005\u0011\u001a6&\u0001+\u0011\u0005USV\"\u0001,\u000b\u0005]C\u0016!C;oG\",7m[3e\u0015\tI6&\u0001\u0006b]:|G/\u0019;j_:L!a\u0017,\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\rB\u0003(\u000b\t\u0007\u0001&A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002?B\u0011\u0001-Z\u0007\u0002C*\u0011!mY\u0001\u0005Y\u0006twMC\u0001e\u0003\u0011Q\u0017M^1\n\u0005\u0019\f'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001j!\tQ#.\u0003\u0002lW\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qF\u001c\u0005\b_\"\t\t\u00111\u0001j\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t!\u000fE\u0002tm>j\u0011\u0001\u001e\u0006\u0003k.\n!bY8mY\u0016\u001cG/[8o\u0013\t9HO\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGC\u0001>~!\tQ30\u0003\u0002}W\t9!i\\8mK\u0006t\u0007bB8\u000b\u0003\u0003\u0005\raL\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002`\u0003\u0003Aqa\\\u0006\u0002\u0002\u0003\u0007\u0011.\u0001\u0005iCND7i\u001c3f)\u0005I\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003}\u000ba!Z9vC2\u001cHc\u0001>\u0002\u0010!9qNDA\u0001\u0002\u0004y\u0013!B#se>\u0014\bCA\u0011\u0011'\u0015\u0001\u0012qCA\u000f!\rQ\u0013\u0011D\u0005\u0004\u00037Y#AB!osJ+g\r\u0005\u0003\u0002 \u0005\u0015RBAA\u0011\u0015\r\t\u0019cY\u0001\u0003S>L1aPA\u0011)\t\t\u0019\"A\u0003baBd\u00170\u0006\u0003\u0002.\u0005MB\u0003BA\u0018\u0003k\u0001B!\t\u0001\u00022A\u0019Q%a\r\u0005\u000b\u001d\u001a\"\u0019\u0001\u0015\t\r\u0005\u001b\u0002\u0019AA\u0019\u0003\u001d)h.\u00199qYf,B!a\u000f\u0002FQ!\u0011QHA$!\u0015Q\u0013qHA\"\u0013\r\t\te\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0007\u0015\n)\u0005B\u0003()\t\u0007\u0001\u0006C\u0005\u0002JQ\t\t\u00111\u0001\u0002L\u0005\u0019\u0001\u0010\n\u0019\u0011\t\u0005\u0002\u00111I\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003#\u00022\u0001YA*\u0013\r\t)&\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public class Error extends NoSuccess implements Product, Serializable {
   private final Object error;

   public static Option unapply(final Error x$0) {
      return Error$.MODULE$.unapply(x$0);
   }

   public static Error apply(final Object error) {
      return Error$.MODULE$.apply(error);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object error() {
      return this.error;
   }

   public Error copy(final Object error) {
      return new Error(error);
   }

   public Object copy$default$1() {
      return this.error();
   }

   public String productPrefix() {
      return "Error";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.error();
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
      return x$1 instanceof Error;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "error";
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
            if (x$1 instanceof Error) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               Error var4 = (Error)x$1;
               if (BoxesRunTime.equals(this.error(), var4.error()) && var4.canEqual(this)) {
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

   public Error(final Object error) {
      this.error = error;
      Product.$init$(this);
   }
}
