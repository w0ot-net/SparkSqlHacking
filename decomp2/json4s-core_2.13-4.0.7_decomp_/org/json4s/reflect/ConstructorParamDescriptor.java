package org.json4s.reflect;

import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mf\u0001\u0002\u0013&\u00012B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u000b\"Aa\n\u0001BK\u0002\u0013\u0005A\t\u0003\u0005P\u0001\tE\t\u0015!\u0003F\u0011!\u0001\u0006A!f\u0001\n\u0003\t\u0006\u0002C+\u0001\u0005#\u0005\u000b\u0011\u0002*\t\u0011Y\u0003!Q3A\u0005\u0002]C\u0001b\u0017\u0001\u0003\u0012\u0003\u0006I\u0001\u0017\u0005\t9\u0002\u0011)\u001a!C\u0001;\"Aq\r\u0001B\tB\u0003%a\fC\u0003i\u0001\u0011\u0005\u0011\u000e\u0003\u0005q\u0001!\u0015\r\u0011\"\u0001r\u0011!)\b\u0001#b\u0001\n\u0003\t\bb\u0002<\u0001\u0003\u0003%\ta\u001e\u0005\b{\u0002\t\n\u0011\"\u0001\u007f\u0011!\t\u0019\u0002AI\u0001\n\u0003q\b\"CA\u000b\u0001E\u0005I\u0011AA\f\u0011%\tY\u0002AI\u0001\n\u0003\ti\u0002C\u0005\u0002\"\u0001\t\n\u0011\"\u0001\u0002$!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\t\u0003s\u0001\u0011\u0011!C\u0001#\"I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0011Q\b\u0005\n\u0003\u0007\u0002\u0011\u0011!C!\u0003\u000bB\u0011\"a\u0015\u0001\u0003\u0003%\t!!\u0016\t\u0013\u0005e\u0003!!A\u0005B\u0005m\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u0011%\t\u0019\u0007AA\u0001\n\u0003\n)\u0007C\u0005\u0002h\u0001\t\t\u0011\"\u0011\u0002j\u001dI\u0011QN\u0013\u0002\u0002#\u0005\u0011q\u000e\u0004\tI\u0015\n\t\u0011#\u0001\u0002r!1\u0001N\bC\u0001\u0003\u0013C\u0011\"a\u0019\u001f\u0003\u0003%)%!\u001a\t\u0013\u0005-e$!A\u0005\u0002\u00065\u0005\"CAM=\u0005\u0005I\u0011QAN\u0011%\tIKHA\u0001\n\u0013\tYK\u0001\u000eD_:\u001cHO];di>\u0014\b+\u0019:b[\u0012+7o\u0019:jaR|'O\u0003\u0002'O\u00059!/\u001a4mK\u000e$(B\u0001\u0015*\u0003\u0019Q7o\u001c85g*\t!&A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001[E:\u0004C\u0001\u00180\u001b\u0005)\u0013B\u0001\u0019&\u0005)!Um]2sSB$xN\u001d\t\u0003eUj\u0011a\r\u0006\u0002i\u0005)1oY1mC&\u0011ag\r\u0002\b!J|G-^2u!\tA\u0004I\u0004\u0002:}9\u0011!(P\u0007\u0002w)\u0011AhK\u0001\u0007yI|w\u000e\u001e \n\u0003QJ!aP\u001a\u0002\u000fA\f7m[1hK&\u0011\u0011I\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u007fM\nAA\\1nKV\tQ\t\u0005\u0002G\u0015:\u0011q\t\u0013\t\u0003uMJ!!S\u001a\u0002\rA\u0013X\rZ3g\u0013\tYEJ\u0001\u0004TiJLgn\u001a\u0006\u0003\u0013N\nQA\\1nK\u0002\n1\"\\1oO2,GMT1nK\u0006aQ.\u00198hY\u0016$g*Y7fA\u0005A\u0011M]4J]\u0012,\u00070F\u0001S!\t\u00114+\u0003\u0002Ug\t\u0019\u0011J\u001c;\u0002\u0013\u0005\u0014x-\u00138eKb\u0004\u0013aB1sORK\b/Z\u000b\u00021B\u0011a&W\u0005\u00035\u0016\u0012\u0011bU2bY\u0006$\u0016\u0010]3\u0002\u0011\u0005\u0014x\rV=qK\u0002\nA\u0002Z3gCVdGOV1mk\u0016,\u0012A\u0018\t\u0004e}\u000b\u0017B\u000114\u0005\u0019y\u0005\u000f^5p]B\u0019!G\u00193\n\u0005\r\u001c$!\u0003$v]\u000e$\u0018n\u001c81!\t\u0011T-\u0003\u0002gg\t\u0019\u0011I\\=\u0002\u001b\u0011,g-Y;miZ\u000bG.^3!\u0003\u0019a\u0014N\\5u}Q1!n\u001b7n]>\u0004\"A\f\u0001\t\u000b\r[\u0001\u0019A#\t\u000b9[\u0001\u0019A#\t\u000bA[\u0001\u0019\u0001*\t\u000bY[\u0001\u0019\u0001-\t\u000bq[\u0001\u0019\u00010\u0002\u0015%\u001cx\n\u001d;j_:\fG.F\u0001s!\t\u00114/\u0003\u0002ug\t9!i\\8mK\u0006t\u0017A\u00035bg\u0012+g-Y;mi\u0006!1m\u001c9z)\u0019Q\u00070\u001f>|y\"91I\u0004I\u0001\u0002\u0004)\u0005b\u0002(\u000f!\u0003\u0005\r!\u0012\u0005\b!:\u0001\n\u00111\u0001S\u0011\u001d1f\u0002%AA\u0002aCq\u0001\u0018\b\u0011\u0002\u0003\u0007a,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003}T3!RA\u0001W\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005=QBAA\u0004\u0015\u0011\tI!a\u0003\u0002\u0013Ut7\r[3dW\u0016$'bAA\u0007g\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005E\u0011q\u0001\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\tIBK\u0002S\u0003\u0003\tabY8qs\u0012\"WMZ1vYR$C'\u0006\u0002\u0002 )\u001a\u0001,!\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%kU\u0011\u0011Q\u0005\u0016\u0004=\u0006\u0005\u0011!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002,A!\u0011QFA\u001c\u001b\t\tyC\u0003\u0003\u00022\u0005M\u0012\u0001\u00027b]\u001eT!!!\u000e\u0002\t)\fg/Y\u0005\u0004\u0017\u0006=\u0012\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0004I\u0006}\u0002\u0002CA!-\u0005\u0005\t\u0019\u0001*\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\u0005E\u0003\u0002J\u0005=C-\u0004\u0002\u0002L)\u0019\u0011QJ\u001a\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002R\u0005-#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$2A]A,\u0011!\t\t\u0005GA\u0001\u0002\u0004!\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\u000b\u0002^!A\u0011\u0011I\r\u0002\u0002\u0003\u0007!+\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0012AB3rk\u0006d7\u000fF\u0002s\u0003WB\u0001\"!\u0011\u001d\u0003\u0003\u0005\r\u0001Z\u0001\u001b\u0007>t7\u000f\u001e:vGR|'\u000fU1sC6$Um]2sSB$xN\u001d\t\u0003]y\u0019RAHA:\u0003\u007f\u0002\"\"!\u001e\u0002|\u0015+%\u000b\u00170k\u001b\t\t9HC\u0002\u0002zM\nqA];oi&lW-\u0003\u0003\u0002~\u0005]$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8okA!\u0011\u0011QAD\u001b\t\t\u0019I\u0003\u0003\u0002\u0006\u0006M\u0012AA5p\u0013\r\t\u00151\u0011\u000b\u0003\u0003_\nQ!\u00199qYf$2B[AH\u0003#\u000b\u0019*!&\u0002\u0018\")1)\ta\u0001\u000b\")a*\ta\u0001\u000b\")\u0001+\ta\u0001%\")a+\ta\u00011\")A,\ta\u0001=\u00069QO\\1qa2LH\u0003BAO\u0003K\u0003BAM0\u0002 BA!'!)F\u000bJCf,C\u0002\u0002$N\u0012a\u0001V;qY\u0016,\u0004\u0002CATE\u0005\u0005\t\u0019\u00016\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002.B!\u0011QFAX\u0013\u0011\t\t,a\f\u0003\r=\u0013'.Z2u\u0001"
)
public class ConstructorParamDescriptor extends Descriptor {
   private boolean isOptional;
   private boolean hasDefault;
   private final String name;
   private final String mangledName;
   private final int argIndex;
   private final ScalaType argType;
   private final Option defaultValue;
   private volatile byte bitmap$0;

   public static Option unapply(final ConstructorParamDescriptor x$0) {
      return ConstructorParamDescriptor$.MODULE$.unapply(x$0);
   }

   public static ConstructorParamDescriptor apply(final String name, final String mangledName, final int argIndex, final ScalaType argType, final Option defaultValue) {
      return ConstructorParamDescriptor$.MODULE$.apply(name, mangledName, argIndex, argType, defaultValue);
   }

   public static Function1 tupled() {
      return ConstructorParamDescriptor$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ConstructorParamDescriptor$.MODULE$.curried();
   }

   public String name() {
      return this.name;
   }

   public String mangledName() {
      return this.mangledName;
   }

   public int argIndex() {
      return this.argIndex;
   }

   public ScalaType argType() {
      return this.argType;
   }

   public Option defaultValue() {
      return this.defaultValue;
   }

   private boolean isOptional$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.isOptional = this.argType().isOption();
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.isOptional;
   }

   public boolean isOptional() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.isOptional$lzycompute() : this.isOptional;
   }

   private boolean hasDefault$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.hasDefault = this.defaultValue().isDefined();
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.hasDefault;
   }

   public boolean hasDefault() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.hasDefault$lzycompute() : this.hasDefault;
   }

   public ConstructorParamDescriptor copy(final String name, final String mangledName, final int argIndex, final ScalaType argType, final Option defaultValue) {
      return new ConstructorParamDescriptor(name, mangledName, argIndex, argType, defaultValue);
   }

   public String copy$default$1() {
      return this.name();
   }

   public String copy$default$2() {
      return this.mangledName();
   }

   public int copy$default$3() {
      return this.argIndex();
   }

   public ScalaType copy$default$4() {
      return this.argType();
   }

   public Option copy$default$5() {
      return this.defaultValue();
   }

   public String productPrefix() {
      return "ConstructorParamDescriptor";
   }

   public int productArity() {
      return 5;
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
            var10000 = BoxesRunTime.boxToInteger(this.argIndex());
            break;
         case 3:
            var10000 = this.argType();
            break;
         case 4:
            var10000 = this.defaultValue();
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
      return x$1 instanceof ConstructorParamDescriptor;
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
            var10000 = "argIndex";
            break;
         case 3:
            var10000 = "argType";
            break;
         case 4:
            var10000 = "defaultValue";
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
      var1 = Statics.mix(var1, Statics.anyHash(this.mangledName()));
      var1 = Statics.mix(var1, this.argIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.argType()));
      var1 = Statics.mix(var1, Statics.anyHash(this.defaultValue()));
      return Statics.finalizeHash(var1, 5);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var13;
      if (this != x$1) {
         label83: {
            boolean var2;
            if (x$1 instanceof ConstructorParamDescriptor) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label65: {
                  ConstructorParamDescriptor var4 = (ConstructorParamDescriptor)x$1;
                  if (this.argIndex() == var4.argIndex()) {
                     label74: {
                        String var10000 = this.name();
                        String var5 = var4.name();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label74;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label74;
                        }

                        var10000 = this.mangledName();
                        String var6 = var4.mangledName();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label74;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label74;
                        }

                        ScalaType var10 = this.argType();
                        ScalaType var7 = var4.argType();
                        if (var10 == null) {
                           if (var7 != null) {
                              break label74;
                           }
                        } else if (!var10.equals(var7)) {
                           break label74;
                        }

                        Option var11 = this.defaultValue();
                        Option var8 = var4.defaultValue();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label74;
                           }
                        } else if (!var11.equals(var8)) {
                           break label74;
                        }

                        if (var4.canEqual(this)) {
                           var13 = true;
                           break label65;
                        }
                     }
                  }

                  var13 = false;
               }

               if (var13) {
                  break label83;
               }
            }

            var13 = false;
            return var13;
         }
      }

      var13 = true;
      return var13;
   }

   public ConstructorParamDescriptor(final String name, final String mangledName, final int argIndex, final ScalaType argType, final Option defaultValue) {
      this.name = name;
      this.mangledName = mangledName;
      this.argIndex = argIndex;
      this.argType = argType;
      this.defaultValue = defaultValue;
   }
}
