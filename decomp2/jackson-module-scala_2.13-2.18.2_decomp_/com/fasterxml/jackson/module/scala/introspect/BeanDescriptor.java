package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=f\u0001B\r\u001b\u0001\u001eB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0017\u0002\u0011\t\u0012)A\u0005}!A1\u000b\u0001BK\u0002\u0013\u0005A\u000b\u0003\u0005]\u0001\tE\t\u0015!\u0003V\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u001d1\u0007!!A\u0005\u0002\u001dDqA\u001b\u0001\u0012\u0002\u0013\u00051\u000eC\u0005\u0002\u0002\u0001\t\n\u0011\"\u0001\u0002\u0004!I\u0011q\u0001\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0002\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"a\u0007\u0001\u0003\u0003%\t!!\b\t\u0013\u0005\r\u0002!!A\u0005B\u0005\u0015\u0002\"CA\u001a\u0001\u0005\u0005I\u0011AA\u001b\u0011%\ty\u0004AA\u0001\n\u0003\n\t\u0005C\u0005\u0002F\u0001\t\t\u0011\"\u0011\u0002H!I\u0011\u0011\n\u0001\u0002\u0002\u0013\u0005\u00131\n\u0005\n\u0003\u001b\u0002\u0011\u0011!C!\u0003\u001f:\u0011\"a\u0015\u001b\u0003\u0003E\t!!\u0016\u0007\u0011eQ\u0012\u0011!E\u0001\u0003/Ba!X\n\u0005\u0002\u0005]\u0004\"CA%'\u0005\u0005IQIA&\u0011%\tIhEA\u0001\n\u0003\u000bY\bC\u0005\u0002\nN\t\t\u0011\"!\u0002\f\"I\u0011QU\n\u0002\u0002\u0013%\u0011q\u0015\u0002\u000f\u0005\u0016\fg\u000eR3tGJL\u0007\u000f^8s\u0015\tYB$\u0001\u0006j]R\u0014xn\u001d9fGRT!!\b\u0010\u0002\u000bM\u001c\u0017\r\\1\u000b\u0005}\u0001\u0013AB7pIVdWM\u0003\u0002\"E\u00059!.Y2lg>t'BA\u0012%\u0003%1\u0017m\u001d;feblGNC\u0001&\u0003\r\u0019w.\\\u0002\u0001'\u0011\u0001\u0001&\f\u0019\u0011\u0005%ZS\"\u0001\u0016\u000b\u0003uI!\u0001\f\u0016\u0003\r\u0005s\u0017PU3g!\tIc&\u0003\u00020U\t9\u0001K]8ek\u000e$\bCA\u0019:\u001d\t\u0011tG\u0004\u00024m5\tAG\u0003\u00026M\u00051AH]8pizJ\u0011!H\u0005\u0003q)\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001HK\u0001\tE\u0016\fg\u000eV=qKV\ta\b\r\u0002@\u0013B\u0019\u0001\tR$\u000f\u0005\u0005\u0013\u0005CA\u001a+\u0013\t\u0019%&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000b\u001a\u0013Qa\u00117bgNT!a\u0011\u0016\u0011\u0005!KE\u0002\u0001\u0003\n\u0015\n\t\t\u0011!A\u0003\u00021\u00131a\u0018\u00132\u0003%\u0011W-\u00198UsB,\u0007%\u0005\u0002N!B\u0011\u0011FT\u0005\u0003\u001f*\u0012qAT8uQ&tw\r\u0005\u0002*#&\u0011!K\u000b\u0002\u0004\u0003:L\u0018A\u00039s_B,'\u000f^5fgV\tQ\u000bE\u00022-bK!aV\u001e\u0003\u0007M+\u0017\u000f\u0005\u0002Z56\t!$\u0003\u0002\\5\t\u0011\u0002K]8qKJ$\u0018\u0010R3tGJL\u0007\u000f^8s\u0003-\u0001(o\u001c9feRLWm\u001d\u0011\u0002\rqJg.\u001b;?)\ry\u0006-\u001a\t\u00033\u0002AQ\u0001P\u0003A\u0002\u0005\u0004$A\u00193\u0011\u0007\u0001#5\r\u0005\u0002II\u0012I!\nYA\u0001\u0002\u0003\u0015\t\u0001\u0014\u0005\u0006'\u0016\u0001\r!V\u0001\u0005G>\u0004\u0018\u0010F\u0002`Q&Dq\u0001\u0010\u0004\u0011\u0002\u0003\u0007\u0011\rC\u0004T\rA\u0005\t\u0019A+\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tA\u000e\r\u0002nm*\u0012an\u001e\t\u0004_R,X\"\u00019\u000b\u0005E\u0014\u0018\u0001\u00027b]\u001eT\u0011a]\u0001\u0005U\u00064\u0018-\u0003\u0002FaB\u0011\u0001J\u001e\u0003\n\u0015\u001e\t\t\u0011!A\u0003\u00021[\u0013\u0001\u001f\t\u0003szl\u0011A\u001f\u0006\u0003wr\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005uT\u0013AC1o]>$\u0018\r^5p]&\u0011qP\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0003\u000bQ#!V<\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u0001E\u0002p\u0003\u001bI1!a\u0004q\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011Q\u0003\t\u0004S\u0005]\u0011bAA\rU\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019\u0001+a\b\t\u0013\u0005\u00052\"!AA\u0002\u0005U\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002(A)\u0011\u0011FA\u0018!6\u0011\u00111\u0006\u0006\u0004\u0003[Q\u0013AC2pY2,7\r^5p]&!\u0011\u0011GA\u0016\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005]\u0012Q\b\t\u0004S\u0005e\u0012bAA\u001eU\t9!i\\8mK\u0006t\u0007\u0002CA\u0011\u001b\u0005\u0005\t\u0019\u0001)\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003\u0017\t\u0019\u0005C\u0005\u0002\"9\t\t\u00111\u0001\u0002\u0016\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0016\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\f\u00051Q-];bYN$B!a\u000e\u0002R!A\u0011\u0011E\t\u0002\u0002\u0003\u0007\u0001+\u0001\bCK\u0006tG)Z:de&\u0004Ho\u001c:\u0011\u0005e\u001b2#B\n\u0002Z\u00055\u0004\u0003CA.\u0003C\n)'V0\u000e\u0005\u0005u#bAA0U\u00059!/\u001e8uS6,\u0017\u0002BA2\u0003;\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83a\u0011\t9'a\u001b\u0011\t\u0001#\u0015\u0011\u000e\t\u0004\u0011\u0006-D!\u0003&\u0014\u0003\u0003\u0005\tQ!\u0001M!\u0011\ty'!\u001e\u000e\u0005\u0005E$bAA:e\u0006\u0011\u0011n\\\u0005\u0004u\u0005EDCAA+\u0003\u0015\t\u0007\u000f\u001d7z)\u0015y\u0016QPAD\u0011\u0019ad\u00031\u0001\u0002\u0000A\"\u0011\u0011QAC!\u0011\u0001E)a!\u0011\u0007!\u000b)\t\u0002\u0006K\u0003{\n\t\u0011!A\u0003\u00021CQa\u0015\fA\u0002U\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u000e\u0006\u0005\u0006#B\u0015\u0002\u0010\u0006M\u0015bAAIU\t1q\n\u001d;j_:\u0004b!KAK\u00033+\u0016bAALU\t1A+\u001e9mKJ\u0002D!a'\u0002 B!\u0001\tRAO!\rA\u0015q\u0014\u0003\n\u0015^\t\t\u0011!A\u0003\u00021C\u0001\"a)\u0018\u0003\u0003\u0005\raX\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAU!\ry\u00171V\u0005\u0004\u0003[\u0003(AB(cU\u0016\u001cG\u000f"
)
public class BeanDescriptor implements Product, Serializable {
   private final Class beanType;
   private final Seq properties;

   public static Option unapply(final BeanDescriptor x$0) {
      return BeanDescriptor$.MODULE$.unapply(x$0);
   }

   public static BeanDescriptor apply(final Class beanType, final Seq properties) {
      return BeanDescriptor$.MODULE$.apply(beanType, properties);
   }

   public static Function1 tupled() {
      return BeanDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return BeanDescriptor$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Class beanType() {
      return this.beanType;
   }

   public Seq properties() {
      return this.properties;
   }

   public BeanDescriptor copy(final Class beanType, final Seq properties) {
      return new BeanDescriptor(beanType, properties);
   }

   public Class copy$default$1() {
      return this.beanType();
   }

   public Seq copy$default$2() {
      return this.properties();
   }

   public String productPrefix() {
      return "BeanDescriptor";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.beanType();
         case 1:
            return this.properties();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof BeanDescriptor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "beanType";
         case 1:
            return "properties";
         default:
            return (String)Statics.ioobe(x$1);
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
            if (x$1 instanceof BeanDescriptor) {
               label48: {
                  BeanDescriptor var4 = (BeanDescriptor)x$1;
                  Class var10000 = this.beanType();
                  Class var5 = var4.beanType();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  Seq var7 = this.properties();
                  Seq var6 = var4.properties();
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

   public BeanDescriptor(final Class beanType, final Seq properties) {
      this.beanType = beanType;
      this.properties = properties;
      Product.$init$(this);
   }
}
