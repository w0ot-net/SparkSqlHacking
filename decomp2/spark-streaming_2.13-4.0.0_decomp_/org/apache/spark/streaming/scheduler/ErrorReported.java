package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d!B\r\u001b\u0001j!\u0003\u0002C \u0001\u0005+\u0007I\u0011\u0001!\t\u0011%\u0003!\u0011#Q\u0001\n\u0005C\u0001B\u0013\u0001\u0003\u0016\u0004%\ta\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0019\")\u0001\u000b\u0001C\u0001#\"9Q\u000bAA\u0001\n\u00031\u0006bB-\u0001#\u0003%\tA\u0017\u0005\bK\u0002\t\n\u0011\"\u0001g\u0011\u001dA\u0007!!A\u0005B%Dq!\u001d\u0001\u0002\u0002\u0013\u0005!\u000fC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fu\u0004\u0011\u0011!C!}\"I\u00111\u0002\u0001\u0002\u0002\u0013\u0005\u0011Q\u0002\u0005\n\u0003/\u0001\u0011\u0011!C!\u00033A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\t\u0013\u0005\u0005\u0002!!A\u0005B\u0005\r\u0002\"CA\u0013\u0001\u0005\u0005I\u0011IA\u0014\u000f)\tYCGA\u0001\u0012\u0003Q\u0012Q\u0006\u0004\n3i\t\t\u0011#\u0001\u001b\u0003_Aa\u0001U\n\u0005\u0002\u0005\u001d\u0003\"CA\u0011'\u0005\u0005IQIA\u0012\u0011%\tIeEA\u0001\n\u0003\u000bY\u0005C\u0005\u0002RM\t\t\u0011\"!\u0002T!I\u0011QM\n\u0002\u0002\u0013%\u0011q\r\u0002\u000e\u000bJ\u0014xN\u001d*fa>\u0014H/\u001a3\u000b\u0005ma\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tib$A\u0005tiJ,\u0017-\\5oO*\u0011q\u0004I\u0001\u0006gB\f'o\u001b\u0006\u0003C\t\na!\u00199bG\",'\"A\u0012\u0002\u0007=\u0014xmE\u0003\u0001K-z#\u0007\u0005\u0002'S5\tqEC\u0001)\u0003\u0015\u00198-\u00197b\u0013\tQsE\u0001\u0004B]f\u0014VM\u001a\t\u0003Y5j\u0011AG\u0005\u0003]i\u0011\u0011CS8c'\u000eDW\rZ;mKJ,e/\u001a8u!\t1\u0003'\u0003\u00022O\t9\u0001K]8ek\u000e$\bCA\u001a=\u001d\t!$H\u0004\u00026s5\taG\u0003\u00028q\u00051AH]8piz\u001a\u0001!C\u0001)\u0013\tYt%A\u0004qC\u000e\\\u0017mZ3\n\u0005ur$\u0001D*fe&\fG.\u001b>bE2,'BA\u001e(\u0003\ri7oZ\u000b\u0002\u0003B\u0011!I\u0012\b\u0003\u0007\u0012\u0003\"!N\u0014\n\u0005\u0015;\u0013A\u0002)sK\u0012,g-\u0003\u0002H\u0011\n11\u000b\u001e:j]\u001eT!!R\u0014\u0002\t5\u001cx\rI\u0001\u0002KV\tA\n\u0005\u00024\u001b&\u0011aJ\u0010\u0002\n)\"\u0014xn^1cY\u0016\f!!\u001a\u0011\u0002\rqJg.\u001b;?)\r\u00116\u000b\u0016\t\u0003Y\u0001AQaP\u0003A\u0002\u0005CQAS\u0003A\u00021\u000bAaY8qsR\u0019!k\u0016-\t\u000f}2\u0001\u0013!a\u0001\u0003\"9!J\u0002I\u0001\u0002\u0004a\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u00027*\u0012\u0011\tX\u0016\u0002;B\u0011alY\u0007\u0002?*\u0011\u0001-Y\u0001\nk:\u001c\u0007.Z2lK\u0012T!AY\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002e?\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tqM\u000b\u0002M9\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012A\u001b\t\u0003WBl\u0011\u0001\u001c\u0006\u0003[:\fA\u0001\\1oO*\tq.\u0001\u0003kCZ\f\u0017BA$m\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005\u0019\bC\u0001\u0014u\u0013\t)xEA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002ywB\u0011a%_\u0005\u0003u\u001e\u00121!\u00118z\u0011\u001da8\"!AA\u0002M\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A@\u0011\u000b\u0005\u0005\u0011q\u0001=\u000e\u0005\u0005\r!bAA\u0003O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005%\u00111\u0001\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002\u0010\u0005U\u0001c\u0001\u0014\u0002\u0012%\u0019\u00111C\u0014\u0003\u000f\t{w\u000e\\3b]\"9A0DA\u0001\u0002\u0004A\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$2A[A\u000e\u0011\u001dah\"!AA\u0002M\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002g\u0006AAo\\*ue&tw\rF\u0001k\u0003\u0019)\u0017/^1mgR!\u0011qBA\u0015\u0011\u001da\u0018#!AA\u0002a\fQ\"\u0012:s_J\u0014V\r]8si\u0016$\u0007C\u0001\u0017\u0014'\u0015\u0019\u0012\u0011GA\u001f!\u001d\t\u0019$!\u000fB\u0019Jk!!!\u000e\u000b\u0007\u0005]r%A\u0004sk:$\u0018.\\3\n\t\u0005m\u0012Q\u0007\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA \u0003\u000bj!!!\u0011\u000b\u0007\u0005\rc.\u0001\u0002j_&\u0019Q(!\u0011\u0015\u0005\u00055\u0012!B1qa2LH#\u0002*\u0002N\u0005=\u0003\"B \u0017\u0001\u0004\t\u0005\"\u0002&\u0017\u0001\u0004a\u0015aB;oCB\u0004H.\u001f\u000b\u0005\u0003+\n\t\u0007E\u0003'\u0003/\nY&C\u0002\u0002Z\u001d\u0012aa\u00149uS>t\u0007#\u0002\u0014\u0002^\u0005c\u0015bAA0O\t1A+\u001e9mKJB\u0001\"a\u0019\u0018\u0003\u0003\u0005\rAU\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA5!\rY\u00171N\u0005\u0004\u0003[b'AB(cU\u0016\u001cG\u000f"
)
public class ErrorReported implements JobSchedulerEvent, Product, Serializable {
   private final String msg;
   private final Throwable e;

   public static Option unapply(final ErrorReported x$0) {
      return ErrorReported$.MODULE$.unapply(x$0);
   }

   public static ErrorReported apply(final String msg, final Throwable e) {
      return ErrorReported$.MODULE$.apply(msg, e);
   }

   public static Function1 tupled() {
      return ErrorReported$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ErrorReported$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String msg() {
      return this.msg;
   }

   public Throwable e() {
      return this.e;
   }

   public ErrorReported copy(final String msg, final Throwable e) {
      return new ErrorReported(msg, e);
   }

   public String copy$default$1() {
      return this.msg();
   }

   public Throwable copy$default$2() {
      return this.e();
   }

   public String productPrefix() {
      return "ErrorReported";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.msg();
         }
         case 1 -> {
            return this.e();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ErrorReported;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "msg";
         }
         case 1 -> {
            return "e";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ErrorReported) {
               label48: {
                  ErrorReported var4 = (ErrorReported)x$1;
                  String var10000 = this.msg();
                  String var5 = var4.msg();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Throwable var7 = this.e();
                  Throwable var6 = var4.e();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ErrorReported(final String msg, final Throwable e) {
      this.msg = msg;
      this.e = e;
      Product.$init$(this);
   }
}
