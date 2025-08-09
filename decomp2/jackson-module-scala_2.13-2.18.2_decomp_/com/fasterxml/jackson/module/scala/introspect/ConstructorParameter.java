package com.fasterxml.jackson.module.scala.introspect;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mf\u0001\u0002\u000f\u001e\u0001*B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t!\u0002\u0011\t\u0012)A\u0005\u0003\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005^\u0001\tE\t\u0015!\u0003[\u0011!q\u0006A!f\u0001\n\u0003y\u0006\u0002\u00034\u0001\u0005#\u0005\u000b\u0011\u00021\t\u000b\u001d\u0004A\u0011\u00015\t\u000fI\u0004\u0011\u0011!C\u0001g\"9q\u000fAI\u0001\n\u0003A\b\"CA\b\u0001E\u0005I\u0011AA\t\u0011%\t)\u0002AI\u0001\n\u0003\t9\u0002C\u0005\u0002\u001c\u0001\t\t\u0011\"\u0011\u0002\u001e!A\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0011\fC\u0005\u0002*\u0001\t\t\u0011\"\u0001\u0002,!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003\u0003\u0002\u0011\u0011!C\u0001\u0003\u0007B\u0011\"!\u0014\u0001\u0003\u0003%\t%a\u0014\t\u0013\u0005M\u0003!!A\u0005B\u0005U\u0003\"CA,\u0001\u0005\u0005I\u0011IA-\u0011%\tY\u0006AA\u0001\n\u0003\nifB\u0005\u0002bu\t\t\u0011#\u0001\u0002d\u0019AA$HA\u0001\u0012\u0003\t)\u0007\u0003\u0004h-\u0011\u0005\u0011Q\u0011\u0005\n\u0003/2\u0012\u0011!C#\u00033B\u0011\"a\"\u0017\u0003\u0003%\t)!#\t\u0013\u0005ee#!A\u0005\u0002\u0006m\u0005\"CAY-\u0005\u0005I\u0011BAZ\u0005Q\u0019uN\\:ueV\u001cGo\u001c:QCJ\fW.\u001a;fe*\u0011adH\u0001\u000bS:$(o\\:qK\u000e$(B\u0001\u0011\"\u0003\u0015\u00198-\u00197b\u0015\t\u00113%\u0001\u0004n_\u0012,H.\u001a\u0006\u0003I\u0015\nqA[1dWN|gN\u0003\u0002'O\u0005Ia-Y:uKJDX\u000e\u001c\u0006\u0002Q\u0005\u00191m\\7\u0004\u0001M!\u0001a\u000b\u00194!\tac&D\u0001.\u0015\u0005\u0001\u0013BA\u0018.\u0005\u0019\te.\u001f*fMB\u0011A&M\u0005\u0003e5\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00025y9\u0011QG\u000f\b\u0003mej\u0011a\u000e\u0006\u0003q%\na\u0001\u0010:p_Rt\u0014\"\u0001\u0011\n\u0005mj\u0013a\u00029bG.\fw-Z\u0005\u0003{y\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!aO\u0017\u0002\u0017\r|gn\u001d;sk\u000e$xN]\u000b\u0002\u0003B\u0012!I\u0014\t\u0004\u0007*cU\"\u0001#\u000b\u0005\u00153\u0015a\u0002:fM2,7\r\u001e\u0006\u0003\u000f\"\u000bA\u0001\\1oO*\t\u0011*\u0001\u0003kCZ\f\u0017BA&E\u0005-\u0019uN\\:ueV\u001cGo\u001c:\u0011\u00055sE\u0002\u0001\u0003\n\u001f\n\t\t\u0011!A\u0003\u0002E\u00131a\u0018\u00132\u00031\u0019wN\\:ueV\u001cGo\u001c:!#\t\u0011V\u000b\u0005\u0002-'&\u0011A+\f\u0002\b\u001d>$\b.\u001b8h!\tac+\u0003\u0002X[\t\u0019\u0011I\\=\u0002\u000b%tG-\u001a=\u0016\u0003i\u0003\"\u0001L.\n\u0005qk#aA%oi\u00061\u0011N\u001c3fq\u0002\nA\u0002Z3gCVdGOV1mk\u0016,\u0012\u0001\u0019\t\u0004Y\u0005\u001c\u0017B\u00012.\u0005\u0019y\u0005\u000f^5p]B\u0019A\u0006Z\u0016\n\u0005\u0015l#!\u0003$v]\u000e$\u0018n\u001c81\u00035!WMZ1vYR4\u0016\r\\;fA\u00051A(\u001b8jiz\"B![6qcB\u0011!\u000eA\u0007\u0002;!)qh\u0002a\u0001YB\u0012Qn\u001c\t\u0004\u0007*s\u0007CA'p\t%y5.!A\u0001\u0002\u000b\u0005\u0011\u000bC\u0003Y\u000f\u0001\u0007!\fC\u0003_\u000f\u0001\u0007\u0001-\u0001\u0003d_BLH\u0003B5ukZDqa\u0010\u0005\u0011\u0002\u0003\u0007A\u000eC\u0004Y\u0011A\u0005\t\u0019\u0001.\t\u000fyC\u0001\u0013!a\u0001A\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A=1\u0005il(FA>\u007f!\r\u0019%\n \t\u0003\u001bv$\u0011bT\u0005\u0002\u0002\u0003\u0005)\u0011A),\u0003}\u0004B!!\u0001\u0002\f5\u0011\u00111\u0001\u0006\u0005\u0003\u000b\t9!A\u0005v]\u000eDWmY6fI*\u0019\u0011\u0011B\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u000e\u0005\r!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\nU\tQf0\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005e!F\u00011\u007f\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011q\u0004\t\u0005\u0003C\t\u0019#D\u0001G\u0013\r\t)C\u0012\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019Q+!\f\t\u0011\u0005=b\"!AA\u0002i\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u001b!\u0015\t9$!\u0010V\u001b\t\tIDC\u0002\u0002<5\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\ty$!\u000f\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003\u000b\nY\u0005E\u0002-\u0003\u000fJ1!!\u0013.\u0005\u001d\u0011un\u001c7fC:D\u0001\"a\f\u0011\u0003\u0003\u0005\r!V\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002 \u0005E\u0003\u0002CA\u0018#\u0005\u0005\t\u0019\u0001.\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AW\u0001\ti>\u001cFO]5oOR\u0011\u0011qD\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005\u0015\u0013q\f\u0005\t\u0003_!\u0012\u0011!a\u0001+\u0006!2i\u001c8tiJ,8\r^8s!\u0006\u0014\u0018-\\3uKJ\u0004\"A\u001b\f\u0014\u000bY\t9'a\u001f\u0011\u0013\u0005%\u0014qNA:5\u0002LWBAA6\u0015\r\ti'L\u0001\beVtG/[7f\u0013\u0011\t\t(a\u001b\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t7\u0007\r\u0003\u0002v\u0005e\u0004\u0003B\"K\u0003o\u00022!TA=\t%ye#!A\u0001\u0002\u000b\u0005\u0011\u000b\u0005\u0003\u0002~\u0005\rUBAA@\u0015\r\t\t\tS\u0001\u0003S>L1!PA@)\t\t\u0019'A\u0003baBd\u0017\u0010F\u0004j\u0003\u0017\u000b)*a&\t\r}J\u0002\u0019AAGa\u0011\ty)a%\u0011\t\rS\u0015\u0011\u0013\t\u0004\u001b\u0006MEAC(\u0002\f\u0006\u0005\t\u0011!B\u0001#\")\u0001,\u0007a\u00015\")a,\u0007a\u0001A\u00069QO\\1qa2LH\u0003BAO\u0003[\u0003B\u0001L1\u0002 B9A&!)\u0002&j\u0003\u0017bAAR[\t1A+\u001e9mKN\u0002D!a*\u0002,B!1ISAU!\ri\u00151\u0016\u0003\n\u001fj\t\t\u0011!A\u0003\u0002EC\u0001\"a,\u001b\u0003\u0003\u0005\r![\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA[!\u0011\t\t#a.\n\u0007\u0005efI\u0001\u0004PE*,7\r\u001e"
)
public class ConstructorParameter implements Product, Serializable {
   private final Constructor constructor;
   private final int index;
   private final Option defaultValue;

   public static Option unapply(final ConstructorParameter x$0) {
      return ConstructorParameter$.MODULE$.unapply(x$0);
   }

   public static ConstructorParameter apply(final Constructor constructor, final int index, final Option defaultValue) {
      return ConstructorParameter$.MODULE$.apply(constructor, index, defaultValue);
   }

   public static Function1 tupled() {
      return ConstructorParameter$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ConstructorParameter$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Constructor constructor() {
      return this.constructor;
   }

   public int index() {
      return this.index;
   }

   public Option defaultValue() {
      return this.defaultValue;
   }

   public ConstructorParameter copy(final Constructor constructor, final int index, final Option defaultValue) {
      return new ConstructorParameter(constructor, index, defaultValue);
   }

   public Constructor copy$default$1() {
      return this.constructor();
   }

   public int copy$default$2() {
      return this.index();
   }

   public Option copy$default$3() {
      return this.defaultValue();
   }

   public String productPrefix() {
      return "ConstructorParameter";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.constructor();
         case 1:
            return BoxesRunTime.boxToInteger(this.index());
         case 2:
            return this.defaultValue();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ConstructorParameter;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "constructor";
         case 1:
            return "index";
         case 2:
            return "defaultValue";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.constructor()));
      var1 = Statics.mix(var1, this.index());
      var1 = Statics.mix(var1, Statics.anyHash(this.defaultValue()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ConstructorParameter) {
               ConstructorParameter var4 = (ConstructorParameter)x$1;
               if (this.index() == var4.index()) {
                  label52: {
                     Constructor var10000 = this.constructor();
                     Constructor var5 = var4.constructor();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.defaultValue();
                     Option var6 = var4.defaultValue();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
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

   public ConstructorParameter(final Constructor constructor, final int index, final Option defaultValue) {
      this.constructor = constructor;
      this.index = index;
      this.defaultValue = defaultValue;
      Product.$init$(this);
   }
}
