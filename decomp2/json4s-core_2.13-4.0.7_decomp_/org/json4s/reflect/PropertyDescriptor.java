package org.json4s.reflect;

import java.lang.reflect.Field;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ef\u0001B\u0011#\u0001&B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0015\u0002\u0011\t\u0012)A\u0005\u0005\"A1\n\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005M\u0001\tE\t\u0015!\u0003C\u0011!i\u0005A!f\u0001\n\u0003q\u0005\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011B(\t\u0011M\u0003!Q3A\u0005\u0002QC\u0001B\u0018\u0001\u0003\u0012\u0003\u0006I!\u0016\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006M\u0002!\ta\u001a\u0005\u0006e\u0002!\ta\u001d\u0005\by\u0002\t\t\u0011\"\u0001~\u0011%\t)\u0001AI\u0001\n\u0003\t9\u0001C\u0005\u0002\u001e\u0001\t\n\u0011\"\u0001\u0002\b!I\u0011q\u0004\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0005\u0005\n\u0003K\u0001\u0011\u0013!C\u0001\u0003OA\u0011\"a\u000b\u0001\u0003\u0003%\t%!\f\t\u0013\u0005M\u0002!!A\u0005\u0002\u0005U\u0002\"CA\u001f\u0001\u0005\u0005I\u0011AA \u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002V\u0001\t\t\u0011\"\u0001\u0002X!I\u0011\u0011\r\u0001\u0002\u0002\u0013\u0005\u00131\r\u0005\n\u0003O\u0002\u0011\u0011!C!\u0003SB\u0011\"a\u001b\u0001\u0003\u0003%\t%!\u001c\t\u0013\u0005=\u0004!!A\u0005B\u0005Et!CA;E\u0005\u0005\t\u0012AA<\r!\t#%!A\t\u0002\u0005e\u0004BB0\u001c\t\u0003\t\t\nC\u0005\u0002lm\t\t\u0011\"\u0012\u0002n!I\u00111S\u000e\u0002\u0002\u0013\u0005\u0015Q\u0013\u0005\n\u0003?[\u0012\u0011!CA\u0003CC\u0011\"a-\u001c\u0003\u0003%I!!.\u0003%A\u0013x\u000e]3sif$Um]2sSB$xN\u001d\u0006\u0003G\u0011\nqA]3gY\u0016\u001cGO\u0003\u0002&M\u00051!n]8oiMT\u0011aJ\u0001\u0004_J<7\u0001A\n\u0005\u0001)rC\u0007\u0005\u0002,Y5\t!%\u0003\u0002.E\tQA)Z:de&\u0004Ho\u001c:\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011Q'\u0010\b\u0003mmr!a\u000e\u001e\u000e\u0003aR!!\u000f\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0014B\u0001\u001f1\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005q\u0002\u0014\u0001\u00028b[\u0016,\u0012A\u0011\t\u0003\u0007\u001es!\u0001R#\u0011\u0005]\u0002\u0014B\u0001$1\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001*\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0019\u0003\u0014!\u00028b[\u0016\u0004\u0013aC7b]\u001edW\r\u001a(b[\u0016\fA\"\\1oO2,GMT1nK\u0002\n!B]3ukJtG+\u001f9f+\u0005y\u0005CA\u0016Q\u0013\t\t&EA\u0005TG\u0006d\u0017\rV=qK\u0006Y!/\u001a;ve:$\u0016\u0010]3!\u0003\u00151\u0017.\u001a7e+\u0005)\u0006C\u0001,]\u001b\u00059&BA\u0012Y\u0015\tI&,\u0001\u0003mC:<'\"A.\u0002\t)\fg/Y\u0005\u0003;^\u0013QAR5fY\u0012\faAZ5fY\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0003bE\u000e$W\r\u0005\u0002,\u0001!)\u0001)\u0003a\u0001\u0005\")1*\u0003a\u0001\u0005\")Q*\u0003a\u0001\u001f\")1+\u0003a\u0001+\u0006\u00191/\u001a;\u0015\u0007!\\\u0007\u000f\u0005\u00020S&\u0011!\u000e\r\u0002\u0005+:LG\u000fC\u0003m\u0015\u0001\u0007Q.\u0001\u0005sK\u000e,\u0017N^3s!\tyc.\u0003\u0002pa\t\u0019\u0011I\\=\t\u000bET\u0001\u0019A7\u0002\u000bY\fG.^3\u0002\u0007\u001d,G\u000f\u0006\u0002uqB\u0011QO^\u0007\u00021&\u0011q\u000f\u0017\u0002\u0007\u001f\nTWm\u0019;\t\u000b1\\\u0001\u0019A=\u0011\u0005=R\u0018BA>1\u0005\u0019\te.\u001f*fM\u0006!1m\u001c9z)\u001d\tgp`A\u0001\u0003\u0007Aq\u0001\u0011\u0007\u0011\u0002\u0003\u0007!\tC\u0004L\u0019A\u0005\t\u0019\u0001\"\t\u000f5c\u0001\u0013!a\u0001\u001f\"91\u000b\u0004I\u0001\u0002\u0004)\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0003\u0013Q3AQA\u0006W\t\ti\u0001\u0005\u0003\u0002\u0010\u0005eQBAA\t\u0015\u0011\t\u0019\"!\u0006\u0002\u0013Ut7\r[3dW\u0016$'bAA\fa\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005m\u0011\u0011\u0003\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\t\u0019CK\u0002P\u0003\u0017\tabY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0002*)\u001aQ+a\u0003\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ty\u0003E\u0002v\u0003cI!\u0001\u0013-\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005]\u0002cA\u0018\u0002:%\u0019\u00111\b\u0019\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u00075\f\t\u0005C\u0005\u0002DM\t\t\u00111\u0001\u00028\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!\u0013\u0011\u000b\u0005-\u0013\u0011K7\u000e\u0005\u00055#bAA(a\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005M\u0013Q\n\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002Z\u0005}\u0003cA\u0018\u0002\\%\u0019\u0011Q\f\u0019\u0003\u000f\t{w\u000e\\3b]\"A\u00111I\u000b\u0002\u0002\u0003\u0007Q.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\u0018\u0003KB\u0011\"a\u0011\u0017\u0003\u0003\u0005\r!a\u000e\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u000e\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a\f\u0002\r\u0015\fX/\u00197t)\u0011\tI&a\u001d\t\u0011\u0005\r\u0013$!AA\u00025\f!\u0003\u0015:pa\u0016\u0014H/\u001f#fg\u000e\u0014\u0018\u000e\u001d;peB\u00111fG\n\u00067\u0005m\u0014q\u0011\t\n\u0003{\n\u0019I\u0011\"P+\u0006l!!a \u000b\u0007\u0005\u0005\u0005'A\u0004sk:$\u0018.\\3\n\t\u0005\u0015\u0015q\u0010\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:$\u0004\u0003BAE\u0003\u001fk!!a#\u000b\u0007\u00055%,\u0001\u0002j_&\u0019a(a#\u0015\u0005\u0005]\u0014!B1qa2LH#C1\u0002\u0018\u0006e\u00151TAO\u0011\u0015\u0001e\u00041\u0001C\u0011\u0015Ye\u00041\u0001C\u0011\u0015ie\u00041\u0001P\u0011\u0015\u0019f\u00041\u0001V\u0003\u001d)h.\u00199qYf$B!a)\u00020B)q&!*\u0002*&\u0019\u0011q\u0015\u0019\u0003\r=\u0003H/[8o!\u001dy\u00131\u0016\"C\u001fVK1!!,1\u0005\u0019!V\u000f\u001d7fi!A\u0011\u0011W\u0010\u0002\u0002\u0003\u0007\u0011-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a.\u0011\u0005U4\b"
)
public class PropertyDescriptor extends Descriptor {
   private final String name;
   private final String mangledName;
   private final ScalaType returnType;
   private final Field field;

   public static Option unapply(final PropertyDescriptor x$0) {
      return PropertyDescriptor$.MODULE$.unapply(x$0);
   }

   public static PropertyDescriptor apply(final String name, final String mangledName, final ScalaType returnType, final Field field) {
      return PropertyDescriptor$.MODULE$.apply(name, mangledName, returnType, field);
   }

   public static Function1 tupled() {
      return PropertyDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PropertyDescriptor$.MODULE$.curried();
   }

   public String name() {
      return this.name;
   }

   public String mangledName() {
      return this.mangledName;
   }

   public ScalaType returnType() {
      return this.returnType;
   }

   public Field field() {
      return this.field;
   }

   public void set(final Object receiver, final Object value) {
      this.field().set(receiver, value);
   }

   public Object get(final Object receiver) {
      return this.field().get(receiver);
   }

   public PropertyDescriptor copy(final String name, final String mangledName, final ScalaType returnType, final Field field) {
      return new PropertyDescriptor(name, mangledName, returnType, field);
   }

   public String copy$default$1() {
      return this.name();
   }

   public String copy$default$2() {
      return this.mangledName();
   }

   public ScalaType copy$default$3() {
      return this.returnType();
   }

   public Field copy$default$4() {
      return this.field();
   }

   public String productPrefix() {
      return "PropertyDescriptor";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.name();
            break;
         case 1:
            var10000 = this.mangledName();
            break;
         case 2:
            var10000 = this.returnType();
            break;
         case 3:
            var10000 = this.field();
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
      return x$1 instanceof PropertyDescriptor;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "name";
            break;
         case 1:
            var10000 = "mangledName";
            break;
         case 2:
            var10000 = "returnType";
            break;
         case 3:
            var10000 = "field";
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
      boolean var13;
      if (this != x$1) {
         label81: {
            boolean var2;
            if (x$1 instanceof PropertyDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label63: {
                  label72: {
                     PropertyDescriptor var4 = (PropertyDescriptor)x$1;
                     String var10000 = this.name();
                     String var5 = var4.name();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     var10000 = this.mangledName();
                     String var6 = var4.mangledName();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label72;
                     }

                     ScalaType var10 = this.returnType();
                     ScalaType var7 = var4.returnType();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var10.equals(var7)) {
                        break label72;
                     }

                     Field var11 = this.field();
                     Field var8 = var4.field();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var11.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        var13 = true;
                        break label63;
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label81;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public PropertyDescriptor(final String name, final String mangledName, final ScalaType returnType, final Field field) {
      this.name = name;
      this.mangledName = mangledName;
      this.returnType = returnType;
      this.field = field;
   }
}
