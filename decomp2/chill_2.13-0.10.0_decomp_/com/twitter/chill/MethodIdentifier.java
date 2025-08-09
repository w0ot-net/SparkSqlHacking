package com.twitter.chill;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001\u0002\u000f\u001e\u0001\u0012B\u0001b\u000f\u0001\u0003\u0016\u0004%\t\u0001\u0010\u0005\t!\u0002\u0011\t\u0012)A\u0005{!A\u0011\u000b\u0001BK\u0002\u0013\u0005!\u000b\u0003\u0005W\u0001\tE\t\u0015!\u0003T\u0011!9\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011B*\t\u000be\u0003A\u0011\u0001.\t\u000f\u0001\u0004\u0011\u0011!C\u0001C\"9!\u000eAI\u0001\n\u0003Y\u0007b\u0002=\u0001#\u0003%\t!\u001f\u0005\b{\u0002\t\n\u0011\"\u0001\u007f\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0001\u0002\u0016!I\u0011Q\u0004\u0001\u0002\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003K\u0001\u0011\u0011!C!\u0003OA\u0011\"!\u000e\u0001\u0003\u0003%\t!a\u000e\t\u0013\u0005\u0005\u0003!!A\u0005B\u0005\r\u0003\"CA$\u0001\u0005\u0005I\u0011IA%\u0011%\tY\u0005AA\u0001\n\u0003\ni\u0005C\u0005\u0002P\u0001\t\t\u0011\"\u0011\u0002R\u001dI\u0011QK\u000f\u0002\u0002#\u0005\u0011q\u000b\u0004\t9u\t\t\u0011#\u0001\u0002Z!1\u0011L\u0006C\u0001\u0003KB\u0011\"a\u0013\u0017\u0003\u0003%)%!\u0014\t\u0013\u0005\u001dd#!A\u0005\u0002\u0006%\u0004\"CA>-\u0005\u0005I\u0011QA?\u0011%\tIJFA\u0001\n\u0013\tYJ\u0001\tNKRDw\u000eZ%eK:$\u0018NZ5fe*\u0011adH\u0001\u0006G\"LG\u000e\u001c\u0006\u0003A\u0005\nq\u0001^<jiR,'OC\u0001#\u0003\r\u0019w.\\\u0002\u0001+\t)si\u0005\u0003\u0001M1z\u0003CA\u0014+\u001b\u0005A#\"A\u0015\u0002\u000bM\u001c\u0017\r\\1\n\u0005-B#AB!osJ+g\r\u0005\u0002([%\u0011a\u0006\u000b\u0002\b!J|G-^2u!\t\u0001\u0004H\u0004\u00022m9\u0011!'N\u0007\u0002g)\u0011AgI\u0001\u0007yI|w\u000e\u001e \n\u0003%J!a\u000e\u0015\u0002\u000fA\f7m[1hK&\u0011\u0011H\u000f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003o!\n1a\u00197t+\u0005i\u0004c\u0001 C\u000b:\u0011q\b\u0011\t\u0003e!J!!\u0011\u0015\u0002\rA\u0013X\rZ3g\u0013\t\u0019EIA\u0003DY\u0006\u001c8O\u0003\u0002BQA\u0011ai\u0012\u0007\u0001\t\u0015A\u0005A1\u0001J\u0005\u0005!\u0016C\u0001&N!\t93*\u0003\u0002MQ\t9aj\u001c;iS:<\u0007CA\u0014O\u0013\ty\u0005FA\u0002B]f\fAa\u00197tA\u0005!a.Y7f+\u0005\u0019\u0006C\u0001 U\u0013\t)FI\u0001\u0004TiJLgnZ\u0001\u0006]\u0006lW\rI\u0001\u0005I\u0016\u001c8-A\u0003eKN\u001c\u0007%\u0001\u0004=S:LGO\u0010\u000b\u00057vsv\fE\u0002]\u0001\u0015k\u0011!\b\u0005\u0006w\u001d\u0001\r!\u0010\u0005\u0006#\u001e\u0001\ra\u0015\u0005\u0006/\u001e\u0001\raU\u0001\u0005G>\u0004\u00180\u0006\u0002cKR!1M\u001a5j!\ra\u0006\u0001\u001a\t\u0003\r\u0016$Q\u0001\u0013\u0005C\u0002%Cqa\u000f\u0005\u0011\u0002\u0003\u0007q\rE\u0002?\u0005\u0012Dq!\u0015\u0005\u0011\u0002\u0003\u00071\u000bC\u0004X\u0011A\u0005\t\u0019A*\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011An^\u000b\u0002[*\u0012QH\\\u0016\u0002_B\u0011\u0001/^\u0007\u0002c*\u0011!o]\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001\u001e\u0015\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002wc\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b!K!\u0019A%\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!\u0010`\u000b\u0002w*\u00121K\u001c\u0003\u0006\u0011*\u0011\r!S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\tQx\u0010B\u0003I\u0017\t\u0007\u0011*A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u000b\u0001B!a\u0002\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0005\u0003\u0017\ti!\u0001\u0003mC:<'BAA\b\u0003\u0011Q\u0017M^1\n\u0007U\u000bI!\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0018A\u0019q%!\u0007\n\u0007\u0005m\u0001FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002N\u0003CA\u0011\"a\t\u000f\u0003\u0003\u0005\r!a\u0006\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tI\u0003E\u0003\u0002,\u0005ER*\u0004\u0002\u0002.)\u0019\u0011q\u0006\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00024\u00055\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u000f\u0002@A\u0019q%a\u000f\n\u0007\u0005u\u0002FA\u0004C_>dW-\u00198\t\u0011\u0005\r\u0002#!AA\u00025\u000b!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QAA#\u0011%\t\u0019#EA\u0001\u0002\u0004\t9\"\u0001\u0005iCND7i\u001c3f)\t\t9\"\u0001\u0005u_N#(/\u001b8h)\t\t)!\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003s\t\u0019\u0006\u0003\u0005\u0002$Q\t\t\u00111\u0001N\u0003AiU\r\u001e5pI&#WM\u001c;jM&,'\u000f\u0005\u0002]-M!aCJA.!\u0011\ti&a\u0019\u000e\u0005\u0005}#\u0002BA1\u0003\u001b\t!![8\n\u0007e\ny\u0006\u0006\u0002\u0002X\u0005)\u0011\r\u001d9msV!\u00111NA9)!\ti'a\u001d\u0002x\u0005e\u0004\u0003\u0002/\u0001\u0003_\u00022ARA9\t\u0015A\u0015D1\u0001J\u0011\u0019Y\u0014\u00041\u0001\u0002vA!aHQA8\u0011\u0015\t\u0016\u00041\u0001T\u0011\u00159\u0016\u00041\u0001T\u0003\u001d)h.\u00199qYf,B!a \u0002\u0012R!\u0011\u0011QAJ!\u00159\u00131QAD\u0013\r\t)\t\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000f\u001d\nI)!$T'&\u0019\u00111\u0012\u0015\u0003\rQ+\b\u000f\\34!\u0011q$)a$\u0011\u0007\u0019\u000b\t\nB\u0003I5\t\u0007\u0011\nC\u0005\u0002\u0016j\t\t\u00111\u0001\u0002\u0018\u0006\u0019\u0001\u0010\n\u0019\u0011\tq\u0003\u0011qR\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003;\u0003B!a\u0002\u0002 &!\u0011\u0011UA\u0005\u0005\u0019y%M[3di\u0002"
)
public class MethodIdentifier implements Product, Serializable {
   private final Class cls;
   private final String name;
   private final String desc;

   public static Option unapply(final MethodIdentifier x$0) {
      return MethodIdentifier$.MODULE$.unapply(x$0);
   }

   public static MethodIdentifier apply(final Class cls, final String name, final String desc) {
      return MethodIdentifier$.MODULE$.apply(cls, name, desc);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class cls() {
      return this.cls;
   }

   public String name() {
      return this.name;
   }

   public String desc() {
      return this.desc;
   }

   public MethodIdentifier copy(final Class cls, final String name, final String desc) {
      return new MethodIdentifier(cls, name, desc);
   }

   public Class copy$default$1() {
      return this.cls();
   }

   public String copy$default$2() {
      return this.name();
   }

   public String copy$default$3() {
      return this.desc();
   }

   public String productPrefix() {
      return "MethodIdentifier";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.cls();
            break;
         case 1:
            var10000 = this.name();
            break;
         case 2:
            var10000 = this.desc();
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
      return x$1 instanceof MethodIdentifier;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "cls";
            break;
         case 1:
            var10000 = "name";
            break;
         case 2:
            var10000 = "desc";
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
      boolean var11;
      if (this != x$1) {
         label72: {
            boolean var2;
            if (x$1 instanceof MethodIdentifier) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label54: {
                  label63: {
                     MethodIdentifier var4 = (MethodIdentifier)x$1;
                     Class var10000 = this.cls();
                     Class var5 = var4.cls();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label63;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label63;
                     }

                     String var8 = this.name();
                     String var6 = var4.name();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label63;
                        }
                     } else if (!var8.equals(var6)) {
                        break label63;
                     }

                     var8 = this.desc();
                     String var7 = var4.desc();
                     if (var8 == null) {
                        if (var7 != null) {
                           break label63;
                        }
                     } else if (!var8.equals(var7)) {
                        break label63;
                     }

                     if (var4.canEqual(this)) {
                        var11 = true;
                        break label54;
                     }
                  }

                  var11 = false;
               }

               if (var11) {
                  break label72;
               }
            }

            var11 = false;
            return var11;
         }
      }

      var11 = true;
      return var11;
   }

   public MethodIdentifier(final Class cls, final String name, final String desc) {
      this.cls = cls;
      this.name = name;
      this.desc = desc;
      Product.$init$(this);
   }
}
