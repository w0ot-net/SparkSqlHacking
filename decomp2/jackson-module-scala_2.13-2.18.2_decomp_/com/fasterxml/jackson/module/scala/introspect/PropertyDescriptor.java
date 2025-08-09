package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%h\u0001\u0002\u0015*\u0001ZB\u0001b\u0013\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t+\u0002\u0011\t\u0012)A\u0005\u001b\"Aa\u000b\u0001BK\u0002\u0013\u0005q\u000b\u0003\u0005`\u0001\tE\t\u0015!\u0003Y\u0011!\u0001\u0007A!f\u0001\n\u0003\t\u0007\u0002C7\u0001\u0005#\u0005\u000b\u0011\u00022\t\u00119\u0004!Q3A\u0005\u0002=D\u0001\u0002\u001e\u0001\u0003\u0012\u0003\u0006I\u0001\u001d\u0005\tk\u0002\u0011)\u001a!C\u0001_\"Aa\u000f\u0001B\tB\u0003%\u0001\u000f\u0003\u0005x\u0001\tU\r\u0011\"\u0001p\u0011!A\bA!E!\u0002\u0013\u0001\b\u0002C=\u0001\u0005+\u0007I\u0011A8\t\u0011i\u0004!\u0011#Q\u0001\nADQa\u001f\u0001\u0005\u0002qD\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005u\u0001!%A\u0005\u0002\u0005}\u0001\"CA\u001b\u0001E\u0005I\u0011AA\u001c\u0011%\tY\u0004AI\u0001\n\u0003\ti\u0004C\u0005\u0002B\u0001\t\n\u0011\"\u0001\u0002D!I\u0011q\t\u0001\u0012\u0002\u0013\u0005\u00111\t\u0005\n\u0003\u0013\u0002\u0011\u0013!C\u0001\u0003\u0007B\u0011\"a\u0013\u0001#\u0003%\t!a\u0011\t\u0013\u00055\u0003!!A\u0005B\u0005=\u0003\"CA,\u0001\u0005\u0005I\u0011AA-\u0011%\t\t\u0007AA\u0001\n\u0003\t\u0019\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u0011q\u0010\u0001\u0002\u0002\u0013\u0005\u0011\u0011\u0011\u0005\n\u0003\u0017\u0003\u0011\u0011!C!\u0003\u001bC\u0011\"!%\u0001\u0003\u0003%\t%a%\t\u0013\u0005U\u0005!!A\u0005B\u0005]\u0005\"CAM\u0001\u0005\u0005I\u0011IAN\u000f%\ty*KA\u0001\u0012\u0003\t\tK\u0002\u0005)S\u0005\u0005\t\u0012AAR\u0011\u0019Y(\u0005\"\u0001\u0002<\"I\u0011Q\u0013\u0012\u0002\u0002\u0013\u0015\u0013q\u0013\u0005\n\u0003{\u0013\u0013\u0011!CA\u0003\u007fC\u0011\"a4#\u0003\u0003%\t)!5\t\u0013\u0005}'%!A\u0005\n\u0005\u0005(A\u0005)s_B,'\u000f^=EKN\u001c'/\u001b9u_JT!AK\u0016\u0002\u0015%tGO]8ta\u0016\u001cGO\u0003\u0002-[\u0005)1oY1mC*\u0011afL\u0001\u0007[>$W\u000f\\3\u000b\u0005A\n\u0014a\u00026bG.\u001cxN\u001c\u0006\u0003eM\n\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003Q\n1aY8n\u0007\u0001\u0019B\u0001A\u001c=\u007fA\u0011\u0001HO\u0007\u0002s)\tA&\u0003\u0002<s\t1\u0011I\\=SK\u001a\u0004\"\u0001O\u001f\n\u0005yJ$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0001\"s!!\u0011$\u000f\u0005\t+U\"A\"\u000b\u0005\u0011+\u0014A\u0002\u001fs_>$h(C\u0001-\u0013\t9\u0015(A\u0004qC\u000e\\\u0017mZ3\n\u0005%S%\u0001D*fe&\fG.\u001b>bE2,'BA$:\u0003\u0011q\u0017-\\3\u0016\u00035\u0003\"A\u0014*\u000f\u0005=\u0003\u0006C\u0001\":\u0013\t\t\u0016(\u0001\u0004Qe\u0016$WMZ\u0005\u0003'R\u0013aa\u0015;sS:<'BA):\u0003\u0015q\u0017-\\3!\u0003\u0015\u0001\u0018M]1n+\u0005A\u0006c\u0001\u001dZ7&\u0011!,\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005qkV\"A\u0015\n\u0005yK#\u0001F\"p]N$(/^2u_J\u0004\u0016M]1nKR,'/\u0001\u0004qCJ\fW\u000eI\u0001\u0006M&,G\u000eZ\u000b\u0002EB\u0019\u0001(W2\u0011\u0005\u0011\\W\"A3\u000b\u0005\u0019<\u0017a\u0002:fM2,7\r\u001e\u0006\u0003Q&\fA\u0001\\1oO*\t!.\u0001\u0003kCZ\f\u0017B\u00017f\u0005\u00151\u0015.\u001a7e\u0003\u00191\u0017.\u001a7eA\u00051q-\u001a;uKJ,\u0012\u0001\u001d\t\u0004qe\u000b\bC\u00013s\u0013\t\u0019XM\u0001\u0004NKRDw\u000eZ\u0001\bO\u0016$H/\u001a:!\u0003\u0019\u0019X\r\u001e;fe\u000691/\u001a;uKJ\u0004\u0013A\u00032fC:<U\r\u001e;fe\u0006Y!-Z1o\u000f\u0016$H/\u001a:!\u0003)\u0011W-\u00198TKR$XM]\u0001\fE\u0016\fgnU3ui\u0016\u0014\b%\u0001\u0004=S:LGO\u0010\u000b\u000e{z|\u0018\u0011AA\u0002\u0003\u000b\t9!!\u0003\u0011\u0005q\u0003\u0001\"B&\u0010\u0001\u0004i\u0005\"\u0002,\u0010\u0001\u0004A\u0006\"\u00021\u0010\u0001\u0004\u0011\u0007\"\u00028\u0010\u0001\u0004\u0001\b\"B;\u0010\u0001\u0004\u0001\b\"B<\u0010\u0001\u0004\u0001\b\"B=\u0010\u0001\u0004\u0001\u0018\u0001B2paf$r\"`A\b\u0003#\t\u0019\"!\u0006\u0002\u0018\u0005e\u00111\u0004\u0005\b\u0017B\u0001\n\u00111\u0001N\u0011\u001d1\u0006\u0003%AA\u0002aCq\u0001\u0019\t\u0011\u0002\u0003\u0007!\rC\u0004o!A\u0005\t\u0019\u00019\t\u000fU\u0004\u0002\u0013!a\u0001a\"9q\u000f\u0005I\u0001\u0002\u0004\u0001\bbB=\u0011!\u0003\u0005\r\u0001]\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\tCK\u0002N\u0003GY#!!\n\u0011\t\u0005\u001d\u0012\u0011G\u0007\u0003\u0003SQA!a\u000b\u0002.\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003_I\u0014AC1o]>$\u0018\r^5p]&!\u00111GA\u0015\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tIDK\u0002Y\u0003G\tabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002@)\u001a!-a\t\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011Q\t\u0016\u0004a\u0006\r\u0012AD2paf$C-\u001a4bk2$H%N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uI]\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA)!\u0011\t\u0019&!\u0016\u000e\u0003\u001dL!aU4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005m\u0003c\u0001\u001d\u0002^%\u0019\u0011qL\u001d\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\u0015\u00141\u000e\t\u0004q\u0005\u001d\u0014bAA5s\t\u0019\u0011I\\=\t\u0013\u00055$$!AA\u0002\u0005m\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002tA1\u0011QOA>\u0003Kj!!a\u001e\u000b\u0007\u0005e\u0014(\u0001\u0006d_2dWm\u0019;j_:LA!! \u0002x\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019)!#\u0011\u0007a\n))C\u0002\u0002\bf\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002nq\t\t\u00111\u0001\u0002f\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\t&a$\t\u0013\u00055T$!AA\u0002\u0005m\u0013\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005m\u0013\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005E\u0013AB3rk\u0006d7\u000f\u0006\u0003\u0002\u0004\u0006u\u0005\"CA7A\u0005\u0005\t\u0019AA3\u0003I\u0001&o\u001c9feRLH)Z:de&\u0004Ho\u001c:\u0011\u0005q\u00133#\u0002\u0012\u0002&\u0006E\u0006\u0003DAT\u0003[k\u0005L\u00199qaBlXBAAU\u0015\r\tY+O\u0001\beVtG/[7f\u0013\u0011\ty+!+\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tw\u0007\u0005\u0003\u00024\u0006eVBAA[\u0015\r\t9,[\u0001\u0003S>L1!SA[)\t\t\t+A\u0003baBd\u0017\u0010F\b~\u0003\u0003\f\u0019-!2\u0002H\u0006%\u00171ZAg\u0011\u0015YU\u00051\u0001N\u0011\u00151V\u00051\u0001Y\u0011\u0015\u0001W\u00051\u0001c\u0011\u0015qW\u00051\u0001q\u0011\u0015)X\u00051\u0001q\u0011\u00159X\u00051\u0001q\u0011\u0015IX\u00051\u0001q\u0003\u001d)h.\u00199qYf$B!a5\u0002\\B!\u0001(WAk!)A\u0014q['YEB\u0004\b\u000f]\u0005\u0004\u00033L$A\u0002+va2,w\u0007\u0003\u0005\u0002^\u001a\n\t\u00111\u0001~\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003G\u0004B!a\u0015\u0002f&\u0019\u0011q]4\u0003\r=\u0013'.Z2u\u0001"
)
public class PropertyDescriptor implements Product, Serializable {
   private final String name;
   private final Option param;
   private final Option field;
   private final Option getter;
   private final Option setter;
   private final Option beanGetter;
   private final Option beanSetter;

   public static Option unapply(final PropertyDescriptor x$0) {
      return PropertyDescriptor$.MODULE$.unapply(x$0);
   }

   public static PropertyDescriptor apply(final String name, final Option param, final Option field, final Option getter, final Option setter, final Option beanGetter, final Option beanSetter) {
      return PropertyDescriptor$.MODULE$.apply(name, param, field, getter, setter, beanGetter, beanSetter);
   }

   public static Function1 tupled() {
      return PropertyDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PropertyDescriptor$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String name() {
      return this.name;
   }

   public Option param() {
      return this.param;
   }

   public Option field() {
      return this.field;
   }

   public Option getter() {
      return this.getter;
   }

   public Option setter() {
      return this.setter;
   }

   public Option beanGetter() {
      return this.beanGetter;
   }

   public Option beanSetter() {
      return this.beanSetter;
   }

   public PropertyDescriptor copy(final String name, final Option param, final Option field, final Option getter, final Option setter, final Option beanGetter, final Option beanSetter) {
      return new PropertyDescriptor(name, param, field, getter, setter, beanGetter, beanSetter);
   }

   public String copy$default$1() {
      return this.name();
   }

   public Option copy$default$2() {
      return this.param();
   }

   public Option copy$default$3() {
      return this.field();
   }

   public Option copy$default$4() {
      return this.getter();
   }

   public Option copy$default$5() {
      return this.setter();
   }

   public Option copy$default$6() {
      return this.beanGetter();
   }

   public Option copy$default$7() {
      return this.beanSetter();
   }

   public String productPrefix() {
      return "PropertyDescriptor";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.name();
         case 1:
            return this.param();
         case 2:
            return this.field();
         case 3:
            return this.getter();
         case 4:
            return this.setter();
         case 5:
            return this.beanGetter();
         case 6:
            return this.beanSetter();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof PropertyDescriptor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "name";
         case 1:
            return "param";
         case 2:
            return "field";
         case 3:
            return "getter";
         case 4:
            return "setter";
         case 5:
            return "beanGetter";
         case 6:
            return "beanSetter";
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
      boolean var18;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof PropertyDescriptor) {
               label88: {
                  PropertyDescriptor var4 = (PropertyDescriptor)x$1;
                  String var10000 = this.name();
                  String var5 = var4.name();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label88;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label88;
                  }

                  Option var12 = this.param();
                  Option var6 = var4.param();
                  if (var12 == null) {
                     if (var6 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var6)) {
                     break label88;
                  }

                  var12 = this.field();
                  Option var7 = var4.field();
                  if (var12 == null) {
                     if (var7 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var7)) {
                     break label88;
                  }

                  var12 = this.getter();
                  Option var8 = var4.getter();
                  if (var12 == null) {
                     if (var8 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var8)) {
                     break label88;
                  }

                  var12 = this.setter();
                  Option var9 = var4.setter();
                  if (var12 == null) {
                     if (var9 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var9)) {
                     break label88;
                  }

                  var12 = this.beanGetter();
                  Option var10 = var4.beanGetter();
                  if (var12 == null) {
                     if (var10 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var10)) {
                     break label88;
                  }

                  var12 = this.beanSetter();
                  Option var11 = var4.beanSetter();
                  if (var12 == null) {
                     if (var11 != null) {
                        break label88;
                     }
                  } else if (!var12.equals(var11)) {
                     break label88;
                  }

                  if (var4.canEqual(this)) {
                     break label95;
                  }
               }
            }

            var18 = false;
            return var18;
         }
      }

      var18 = true;
      return var18;
   }

   public PropertyDescriptor(final String name, final Option param, final Option field, final Option getter, final Option setter, final Option beanGetter, final Option beanSetter) {
      this.name = name;
      this.param = param;
      this.field = field;
      this.getter = getter;
      this.setter = setter;
      this.beanGetter = beanGetter;
      this.beanSetter = beanSetter;
      Product.$init$(this);
      if (((List)(new scala.collection.immutable..colon.colon(field, new scala.collection.immutable..colon.colon(getter, scala.collection.immutable.Nil..MODULE$))).flatten(scala.Predef..MODULE$.$conforms())).isEmpty()) {
         throw new IllegalArgumentException("One of field or getter must be defined.");
      }
   }
}
