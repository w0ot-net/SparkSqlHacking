package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug\u0001\u0002\u0014(\u0001BB\u0001B\u0012\u0001\u0003\u0016\u0004%\ta\u0012\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0011\"A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005X\u0001\tE\t\u0015!\u0003T\u0011!A\u0006A!f\u0001\n\u0003I\u0006\u0002C/\u0001\u0005#\u0005\u000b\u0011\u0002.\t\u0011y\u0003!Q3A\u0005\u0002}C\u0001b\u0019\u0001\u0003\u0012\u0003\u0006I\u0001\u0019\u0005\tI\u0002\u0011)\u001a!C\u00013\"AQ\r\u0001B\tB\u0003%!\f\u0003\u0005g\u0001\tU\r\u0011\"\u0001h\u0011!q\u0007A!E!\u0002\u0013A\u0007\"B8\u0001\t\u0003\u0001\b\"\u0002=\u0001\t\u0003I\bbBA\u0004\u0001\u0011\u0005\u0013\u0011\u0002\u0005\n\u0003\u0017\u0001\u0011\u0011!C\u0001\u0003\u001bA\u0011\"a\u0007\u0001#\u0003%\t!!\b\t\u0013\u0005M\u0002!%A\u0005\u0002\u0005U\u0002\"CA\u001d\u0001E\u0005I\u0011AA\u001e\u0011%\ty\u0004AI\u0001\n\u0003\t\t\u0005C\u0005\u0002F\u0001\t\n\u0011\"\u0001\u0002<!I\u0011q\t\u0001\u0012\u0002\u0013\u0005\u0011\u0011\n\u0005\n\u0003\u001b\u0002\u0011\u0011!C!\u0003\u001fB\u0001\"!\u0015\u0001\u0003\u0003%\t!\u0017\u0005\n\u0003'\u0002\u0011\u0011!C\u0001\u0003+B\u0011\"!\u0019\u0001\u0003\u0003%\t%a\u0019\t\u0013\u0005E\u0004!!A\u0005\u0002\u0005M\u0004\"CA?\u0001\u0005\u0005I\u0011IA@\u0011%\t\u0019\tAA\u0001\n\u0003\n)\tC\u0005\u0002\b\u0002\t\t\u0011\"\u0011\u0002\n\u001eI\u0011QR\u0014\u0002\u0002#\u0005\u0011q\u0012\u0004\tM\u001d\n\t\u0011#\u0001\u0002\u0012\"1q\u000e\tC\u0001\u0003SC\u0011\"a\u0002!\u0003\u0003%)%!\u0003\t\u0013\u0005-\u0006%!A\u0005\u0002\u00065\u0006\"CA^A\u0005\u0005I\u0011QA_\u0011%\tY\rIA\u0001\n\u0013\tiM\u0001\u0006Ts6\u0014w\u000e\\%oM>T!\u0001K\u0015\u0002\u0011M\u001c\u0017\r\\1tS\u001eT!AK\u0016\u0002\rM\u001c\u0017\r\\1q\u0015\taS&\u0001\u0004kg>tGg\u001d\u0006\u0002]\u0005\u0019qN]4\u0004\u0001M!\u0001!M\u001c;!\t\u0011T'D\u00014\u0015\u0005!\u0014!B:dC2\f\u0017B\u0001\u001c4\u0005\u0019\te.\u001f*fMB\u0011!\u0007O\u0005\u0003sM\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002<\u0007:\u0011A(\u0011\b\u0003{\u0001k\u0011A\u0010\u0006\u0003\u007f=\na\u0001\u0010:p_Rt\u0014\"\u0001\u001b\n\u0005\t\u001b\u0014a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!AQ\u001a\u0002\t9\fW.Z\u000b\u0002\u0011B\u0011\u0011*\u0014\b\u0003\u0015.\u0003\"!P\u001a\n\u00051\u001b\u0014A\u0002)sK\u0012,g-\u0003\u0002O\u001f\n11\u000b\u001e:j]\u001eT!\u0001T\u001a\u0002\u000b9\fW.\u001a\u0011\u0002\u000b=<h.\u001a:\u0016\u0003M\u0003\"\u0001V+\u000e\u0003\u001dJ!AV\u0014\u0003\rMKXNY8m\u0003\u0019ywO\\3sA\u0005)a\r\\1hgV\t!\f\u0005\u000237&\u0011Al\r\u0002\u0004\u0013:$\u0018A\u00024mC\u001e\u001c\b%A\u0007qe&4\u0018\r^3XSRD\u0017N\\\u000b\u0002AB\u0019!'Y\u0019\n\u0005\t\u001c$AB(qi&|g.\u0001\bqe&4\u0018\r^3XSRD\u0017N\u001c\u0011\u0002\t%tgm\\\u0001\u0006S:4w\u000eI\u0001\u0006K:$(/_\u000b\u0002QB\u0011\u0011\u000e\u001c\t\u0003)*L!a[\u0014\u0003\u0011M\u001b\u0017\r\\1TS\u001eL!!\u001c6\u0003\u000b\u0015sGO]=\u0002\r\u0015tGO]=!\u0003\u0019a\u0014N\\5u}Q9\u0011O]:ukZ<\bC\u0001+\u0001\u0011\u00151U\u00021\u0001I\u0011\u0015\tV\u00021\u0001T\u0011\u0015AV\u00021\u0001[\u0011\u0015qV\u00021\u0001a\u0011\u0015!W\u00021\u0001[\u0011\u00151W\u00021\u0001i\u00031\u0019\u00180\u001c2pYN#(/\u001b8h)\rQ\u00181\u0001\t\u0004w\u0006\u0005Q\"\u0001?\u000b\u0005ut\u0018\u0001\u00027b]\u001eT\u0011a`\u0001\u0005U\u00064\u0018-\u0003\u0002Oy\"1\u0011Q\u0001\bA\u0002E\n1!\u00198z\u0003!!xn\u0015;sS:<G#\u0001>\u0002\t\r|\u0007/\u001f\u000b\u000ec\u0006=\u0011\u0011CA\n\u0003+\t9\"!\u0007\t\u000f\u0019\u0003\u0002\u0013!a\u0001\u0011\"9\u0011\u000b\u0005I\u0001\u0002\u0004\u0019\u0006b\u0002-\u0011!\u0003\u0005\rA\u0017\u0005\b=B\u0001\n\u00111\u0001a\u0011\u001d!\u0007\u0003%AA\u0002iCqA\u001a\t\u0011\u0002\u0003\u0007\u0001.\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005}!f\u0001%\u0002\"-\u0012\u00111\u0005\t\u0005\u0003K\ty#\u0004\u0002\u0002()!\u0011\u0011FA\u0016\u0003%)hn\u00195fG.,GMC\u0002\u0002.M\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t$a\n\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005]\"fA*\u0002\"\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAA\u001fU\rQ\u0016\u0011E\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\t\u0019EK\u0002a\u0003C\tabY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005-#f\u00015\u0002\"\u0005i\u0001O]8ek\u000e$\bK]3gSb,\u0012A_\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9&!\u0018\u0011\u0007I\nI&C\u0002\u0002\\M\u00121!\u00118z\u0011!\ty&GA\u0001\u0002\u0004Q\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002fA1\u0011qMA7\u0003/j!!!\u001b\u000b\u0007\u0005-4'\u0001\u0006d_2dWm\u0019;j_:LA!a\u001c\u0002j\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t)(a\u001f\u0011\u0007I\n9(C\u0002\u0002zM\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002`m\t\t\u00111\u0001\u0002X\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u0018\u0011\u0011\u0005\t\u0003?b\u0012\u0011!a\u00015\u0006A\u0001.Y:i\u0007>$W\rF\u0001[\u0003\u0019)\u0017/^1mgR!\u0011QOAF\u0011%\tyFHA\u0001\u0002\u0004\t9&\u0001\u0006Ts6\u0014w\u000e\\%oM>\u0004\"\u0001\u0016\u0011\u0014\u000b\u0001\n\u0019*a(\u0011\u0017\u0005U\u00151\u0014%T5\u0002T\u0006.]\u0007\u0003\u0003/S1!!'4\u0003\u001d\u0011XO\u001c;j[\u0016LA!!(\u0002\u0018\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0011\t\u0005\u0005\u0016qU\u0007\u0003\u0003GS1!!*\u007f\u0003\tIw.C\u0002E\u0003G#\"!a$\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u001bE\fy+!-\u00024\u0006U\u0016qWA]\u0011\u001515\u00051\u0001I\u0011\u0015\t6\u00051\u0001T\u0011\u0015A6\u00051\u0001[\u0011\u0015q6\u00051\u0001a\u0011\u0015!7\u00051\u0001[\u0011\u001517\u00051\u0001i\u0003\u001d)h.\u00199qYf$B!a0\u0002HB!!'YAa!%\u0011\u00141\u0019%T5\u0002T\u0006.C\u0002\u0002FN\u0012a\u0001V;qY\u00164\u0004\u0002CAeI\u0005\u0005\t\u0019A9\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002PB\u001910!5\n\u0007\u0005MGP\u0001\u0004PE*,7\r\u001e"
)
public class SymbolInfo implements Product, Serializable {
   private final String name;
   private final Symbol owner;
   private final int flags;
   private final Option privateWithin;
   private final int info;
   private final ScalaSig.Entry entry;

   public static Option unapply(final SymbolInfo x$0) {
      return SymbolInfo$.MODULE$.unapply(x$0);
   }

   public static SymbolInfo apply(final String name, final Symbol owner, final int flags, final Option privateWithin, final int info, final ScalaSig.Entry entry) {
      return SymbolInfo$.MODULE$.apply(name, owner, flags, privateWithin, info, entry);
   }

   public static Function1 tupled() {
      return SymbolInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SymbolInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public Symbol owner() {
      return this.owner;
   }

   public int flags() {
      return this.flags;
   }

   public Option privateWithin() {
      return this.privateWithin;
   }

   public int info() {
      return this.info;
   }

   public ScalaSig.Entry entry() {
      return this.entry;
   }

   public String symbolString(final Object any) {
      String var2;
      if (any instanceof SymbolInfoSymbol) {
         SymbolInfoSymbol var4 = (SymbolInfoSymbol)any;
         var2 = Integer.toString(var4.index());
      } else {
         var2 = any.toString();
      }

      return var2;
   }

   public String toString() {
      StringBuilder var10000 = (new StringBuilder(23)).append(this.name()).append(", owner=").append(this.symbolString(this.owner())).append(", flags=").append(.MODULE$.toHexString$extension(scala.Predef..MODULE$.intWrapper(this.flags()))).append(", info=").append(this.info());
      Option var2 = this.privateWithin();
      String var1;
      if (var2 instanceof Some) {
         Some var3 = (Some)var2;
         Object any = var3.value();
         var1 = (new StringBuilder(16)).append(", privateWithin=").append(this.symbolString(any)).toString();
      } else {
         if (!scala.None..MODULE$.equals(var2)) {
            throw new MatchError(var2);
         }

         var1 = " ";
      }

      return var10000.append(var1).toString();
   }

   public SymbolInfo copy(final String name, final Symbol owner, final int flags, final Option privateWithin, final int info, final ScalaSig.Entry entry) {
      return new SymbolInfo(name, owner, flags, privateWithin, info, entry);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Symbol copy$default$2() {
      return this.owner();
   }

   public int copy$default$3() {
      return this.flags();
   }

   public Option copy$default$4() {
      return this.privateWithin();
   }

   public int copy$default$5() {
      return this.info();
   }

   public ScalaSig.Entry copy$default$6() {
      return this.entry();
   }

   public String productPrefix() {
      return "SymbolInfo";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.name();
            break;
         case 1:
            var10000 = this.owner();
            break;
         case 2:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
            break;
         case 3:
            var10000 = this.privateWithin();
            break;
         case 4:
            var10000 = BoxesRunTime.boxToInteger(this.info());
            break;
         case 5:
            var10000 = this.entry();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof SymbolInfo;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "name";
            break;
         case 1:
            var10000 = "owner";
            break;
         case 2:
            var10000 = "flags";
            break;
         case 3:
            var10000 = "privateWithin";
            break;
         case 4:
            var10000 = "info";
            break;
         case 5:
            var10000 = "entry";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.owner()));
      var1 = Statics.mix(var1, this.flags());
      var1 = Statics.mix(var1, Statics.anyHash(this.privateWithin()));
      var1 = Statics.mix(var1, this.info());
      var1 = Statics.mix(var1, Statics.anyHash(this.entry()));
      return Statics.finalizeHash(var1, 6);
   }

   public boolean equals(final Object x$1) {
      boolean var13;
      if (this != x$1) {
         label85: {
            boolean var2;
            if (x$1 instanceof SymbolInfo) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label67: {
                  SymbolInfo var4 = (SymbolInfo)x$1;
                  if (this.flags() == var4.flags() && this.info() == var4.info()) {
                     label76: {
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label76;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label76;
                        }

                        Symbol var9 = this.owner();
                        Symbol var6 = var4.owner();
                        if (var9 == null) {
                           if (var6 != null) {
                              break label76;
                           }
                        } else if (!var9.equals(var6)) {
                           break label76;
                        }

                        Option var10 = this.privateWithin();
                        Option var7 = var4.privateWithin();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label76;
                           }
                        } else if (!var10.equals(var7)) {
                           break label76;
                        }

                        ScalaSig.Entry var11 = this.entry();
                        ScalaSig.Entry var8 = var4.entry();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label76;
                           }
                        } else if (!var11.equals(var8)) {
                           break label76;
                        }

                        if (var4.canEqual(this)) {
                           var13 = true;
                           break label67;
                        }
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label85;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public SymbolInfo(final String name, final Symbol owner, final int flags, final Option privateWithin, final int info, final ScalaSig.Entry entry) {
      this.name = name;
      this.owner = owner;
      this.flags = flags;
      this.privateWithin = privateWithin;
      this.info = info;
      this.entry = entry;
      Product.$init$(this);
   }
}
