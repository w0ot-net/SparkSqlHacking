package org.json4s;

import java.io.Serializable;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t%b\u0001\u0002\u0014(\u00012B\u0001b\u0011\u0001\u0003\u0016\u0004%\t\u0001\u0012\u0005\t3\u0002\u0011\t\u0012)A\u0005\u000b\"A!\f\u0001BK\u0002\u0013\u00051\f\u0003\u0005e\u0001\tE\t\u0015!\u0003]\u0011!)\u0007A!f\u0001\n\u00031\u0007\u0002\u00036\u0001\u0005#\u0005\u000b\u0011B4\t\u0011-\u0004!Q1A\u0005\u00041D\u0001b\u001f\u0001\u0003\u0002\u0003\u0006I!\u001c\u0005\u0006y\u0002!\t! \u0005\n\u0003\u0013\u0001\u0011\u0011!C\u0001\u0003\u0017A\u0011\"!\t\u0001#\u0003%\t!a\t\t\u0013\u0005u\u0002!%A\u0005\u0002\u0005}\u0002\"CA$\u0001E\u0005I\u0011AA%\u0011%\t\t\u0006AA\u0001\n\u0003\n\u0019\u0006C\u0005\u0002d\u0001\t\t\u0011\"\u0001\u0002f!I\u0011Q\u000e\u0001\u0002\u0002\u0013\u0005\u0011q\u000e\u0005\n\u0003k\u0002\u0011\u0011!C!\u0003oB\u0011\"!\"\u0001\u0003\u0003%\t!a\"\t\u0013\u0005-\u0005!!A\u0005B\u00055\u0005\"CAI\u0001\u0005\u0005I\u0011IAJ\u0011%\t)\nAA\u0001\n\u0003\n9\nC\u0005\u0002\u001a\u0002\t\t\u0011\"\u0011\u0002\u001c\u001e9\u0011qT\u0014\t\u0002\u0005\u0005fA\u0002\u0014(\u0011\u0003\t\u0019\u000b\u0003\u0004}1\u0011\u0005\u0011q\u0016\u0005\b\u0003cCB\u0011AAZ\u0011\u001d\ti\f\u0007C\u0001\u0003\u007fCq!a1\u0019\t\u0003\t)\rC\u0005\u0002Lb\t\t\u0011\"!\u0002N\"I\u00111\u001d\r\u0012\u0002\u0013\u0005\u0011Q\u001d\u0005\n\u0003SD\u0012\u0013!C\u0001\u0003WD\u0011\"a<\u0019#\u0003%\t!!=\t\u0013\u0005U\b$!A\u0005\u0002\u0006]\b\"\u0003B\u00071E\u0005I\u0011\u0001B\b\u0011%\u0011\u0019\u0002GI\u0001\n\u0003\u0011)\u0002C\u0005\u0003\u001aa\t\n\u0011\"\u0001\u0003\u001c!I!q\u0004\r\u0002\u0002\u0013%!\u0011\u0005\u0002\u0010\r&,G\u000eZ*fe&\fG.\u001b>fe*\u0011\u0001&K\u0001\u0007UN|g\u000eN:\u000b\u0003)\n1a\u001c:h\u0007\u0001)\"!L;\u0014\t\u0001qCg\u000e\t\u0003_Ij\u0011\u0001\r\u0006\u0002c\u0005)1oY1mC&\u00111\u0007\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=*\u0014B\u0001\u001c1\u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\u000f!\u000f\u0005erdB\u0001\u001e>\u001b\u0005Y$B\u0001\u001f,\u0003\u0019a$o\\8u}%\t\u0011'\u0003\u0002@a\u00059\u0001/Y2lC\u001e,\u0017BA!C\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ty\u0004'\u0001\u0006tKJL\u0017\r\\5{KJ,\u0012!\u0012\t\u0005_\u0019Ce+\u0003\u0002Ha\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u00030\u0013.\u001b\u0016B\u0001&1\u0005\u0019!V\u000f\u001d7feA\u0011A\n\u0015\b\u0003\u001b:\u0003\"A\u000f\u0019\n\u0005=\u0003\u0014A\u0002)sK\u0012,g-\u0003\u0002R%\n11\u000b\u001e:j]\u001eT!a\u0014\u0019\u0011\u0005=\"\u0016BA+1\u0005\r\te.\u001f\t\u0004_]C\u0015B\u0001-1\u0005\u0019y\u0005\u000f^5p]\u0006Y1/\u001a:jC2L'0\u001a:!\u00031!Wm]3sS\u0006d\u0017N_3s+\u0005a\u0006\u0003B\u0018G;v\u0003\"AX1\u000f\u0005}\u0003W\"A\u0014\n\u0005}:\u0013B\u00012d\u0005\u0019Qe)[3mI*\u0011qhJ\u0001\u000eI\u0016\u001cXM]5bY&TXM\u001d\u0011\u0002\u001d%t7\r\\;eK2\u000b'0\u001f,bYV\tq\r\u0005\u00020Q&\u0011\u0011\u000e\r\u0002\b\u0005>|G.Z1o\u0003=Ign\u00197vI\u0016d\u0015M_=WC2\u0004\u0013AA7g+\u0005i\u0007c\u00018rg6\tqN\u0003\u0002qa\u00059!/\u001a4mK\u000e$\u0018B\u0001:p\u0005!\u0019E.Y:t)\u0006<\u0007C\u0001;v\u0019\u0001!QA\u001e\u0001C\u0002]\u0014\u0011!Q\t\u0003qN\u0003\"aL=\n\u0005i\u0004$a\u0002(pi\"LgnZ\u0001\u0004[\u001a\u0004\u0013A\u0002\u001fj]&$h\bF\u0004\u007f\u0003\u0007\t)!a\u0002\u0015\u0007}\f\t\u0001E\u0002`\u0001MDQa[\u0005A\u00045DqaQ\u0005\u0011\u0002\u0003\u0007Q\tC\u0004[\u0013A\u0005\t\u0019\u0001/\t\u000f\u0015L\u0001\u0013!a\u0001O\u0006!1m\u001c9z+\u0011\ti!!\u0006\u0015\u0011\u0005=\u00111DA\u000f\u0003?!B!!\u0005\u0002\u0018A!q\fAA\n!\r!\u0018Q\u0003\u0003\u0006m*\u0011\ra\u001e\u0005\u0007W*\u0001\u001d!!\u0007\u0011\t9\f\u00181\u0003\u0005\b\u0007*\u0001\n\u00111\u0001F\u0011\u001dQ&\u0002%AA\u0002qCq!\u001a\u0006\u0011\u0002\u0003\u0007q-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\t\u0005\u0015\u00121H\u000b\u0003\u0003OQ3!RA\u0015W\t\tY\u0003\u0005\u0003\u0002.\u0005]RBAA\u0018\u0015\u0011\t\t$a\r\u0002\u0013Ut7\r[3dW\u0016$'bAA\u001ba\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005e\u0012q\u0006\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,G!\u0002<\f\u0005\u00049\u0018AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0003\u0003\n)%\u0006\u0002\u0002D)\u001aA,!\u000b\u0005\u000bYd!\u0019A<\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111JA(+\t\tiEK\u0002h\u0003S!QA^\u0007C\u0002]\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA+!\u0011\t9&!\u0019\u000e\u0005\u0005e#\u0002BA.\u0003;\nA\u0001\\1oO*\u0011\u0011qL\u0001\u0005U\u00064\u0018-C\u0002R\u00033\nA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u001a\u0011\u0007=\nI'C\u0002\u0002lA\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2aUA9\u0011%\t\u0019\bEA\u0001\u0002\u0004\t9'A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003s\u0002R!a\u001f\u0002\u0002Nk!!! \u000b\u0007\u0005}\u0004'\u0001\u0006d_2dWm\u0019;j_:LA!a!\u0002~\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r9\u0017\u0011\u0012\u0005\t\u0003g\u0012\u0012\u0011!a\u0001'\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t)&a$\t\u0013\u0005M4#!AA\u0002\u0005\u001d\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005\u001d\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005U\u0013AB3rk\u0006d7\u000fF\u0002h\u0003;C\u0001\"a\u001d\u0017\u0003\u0003\u0005\raU\u0001\u0010\r&,G\u000eZ*fe&\fG.\u001b>feB\u0011q\fG\n\u000519\n)\u000b\u0005\u0003\u0002(\u00065VBAAU\u0015\u0011\tY+!\u0018\u0002\u0005%|\u0017bA!\u0002*R\u0011\u0011\u0011U\u0001\u000be\u0016t\u0017-\\3Ge>lG#\u0002/\u00026\u0006e\u0006BBA\\5\u0001\u00071*\u0001\u0003oC6,\u0007BBA^5\u0001\u00071*A\u0004oK^t\u0015-\\3\u0002\r%<gn\u001c:f)\r)\u0015\u0011\u0019\u0005\u0007\u0003o[\u0002\u0019A&\u0002\u0011I,g.Y7f)>$R!RAd\u0003\u0013Da!a.\u001d\u0001\u0004Y\u0005BBA^9\u0001\u00071*A\u0003baBd\u00170\u0006\u0003\u0002P\u0006]G\u0003CAi\u0003;\fy.!9\u0015\t\u0005M\u0017\u0011\u001c\t\u0005?\u0002\t)\u000eE\u0002u\u0003/$QA^\u000fC\u0002]Daa[\u000fA\u0004\u0005m\u0007\u0003\u00028r\u0003+DqaQ\u000f\u0011\u0002\u0003\u0007Q\tC\u0004[;A\u0005\t\u0019\u0001/\t\u000f\u0015l\u0002\u0013!a\u0001O\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$\u0013'\u0006\u0003\u0002&\u0005\u001dH!\u0002<\u001f\u0005\u00049\u0018aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005\u0005\u0013Q\u001e\u0003\u0006m~\u0011\ra^\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111JAz\t\u00151\bE1\u0001x\u0003\u001d)h.\u00199qYf,B!!?\u0003\fQ!\u00111 B\u0002!\u0011ys+!@\u0011\r=\ny0\u0012/h\u0013\r\u0011\t\u0001\r\u0002\u0007)V\u0004H.Z\u001a\t\u0013\t\u0015\u0011%!AA\u0002\t\u001d\u0011a\u0001=%aA!q\f\u0001B\u0005!\r!(1\u0002\u0003\u0006m\u0006\u0012\ra^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u0016\t\u0005\u0015\"\u0011\u0003\u0003\u0006m\n\u0012\ra^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001a\u0016\t\u0005\u0005#q\u0003\u0003\u0006m\u000e\u0012\ra^\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\t\u0005-#Q\u0004\u0003\u0006m\u0012\u0012\ra^\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005G\u0001B!a\u0016\u0003&%!!qEA-\u0005\u0019y%M[3di\u0002"
)
public class FieldSerializer implements Product, Serializable {
   private final PartialFunction serializer;
   private final PartialFunction deserializer;
   private final boolean includeLazyVal;
   private final ClassTag mf;

   public static boolean $lessinit$greater$default$3() {
      return FieldSerializer$.MODULE$.$lessinit$greater$default$3();
   }

   public static PartialFunction $lessinit$greater$default$2() {
      return FieldSerializer$.MODULE$.$lessinit$greater$default$2();
   }

   public static PartialFunction $lessinit$greater$default$1() {
      return FieldSerializer$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final FieldSerializer x$0) {
      return FieldSerializer$.MODULE$.unapply(x$0);
   }

   public static boolean apply$default$3() {
      return FieldSerializer$.MODULE$.apply$default$3();
   }

   public static PartialFunction apply$default$2() {
      return FieldSerializer$.MODULE$.apply$default$2();
   }

   public static PartialFunction apply$default$1() {
      return FieldSerializer$.MODULE$.apply$default$1();
   }

   public static FieldSerializer apply(final PartialFunction serializer, final PartialFunction deserializer, final boolean includeLazyVal, final ClassTag mf) {
      return FieldSerializer$.MODULE$.apply(serializer, deserializer, includeLazyVal, mf);
   }

   public static PartialFunction renameTo(final String name, final String newName) {
      return FieldSerializer$.MODULE$.renameTo(name, newName);
   }

   public static PartialFunction ignore(final String name) {
      return FieldSerializer$.MODULE$.ignore(name);
   }

   public static PartialFunction renameFrom(final String name, final String newName) {
      return FieldSerializer$.MODULE$.renameFrom(name, newName);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public PartialFunction serializer() {
      return this.serializer;
   }

   public PartialFunction deserializer() {
      return this.deserializer;
   }

   public boolean includeLazyVal() {
      return this.includeLazyVal;
   }

   public ClassTag mf() {
      return this.mf;
   }

   public FieldSerializer copy(final PartialFunction serializer, final PartialFunction deserializer, final boolean includeLazyVal, final ClassTag mf) {
      return new FieldSerializer(serializer, deserializer, includeLazyVal, mf);
   }

   public PartialFunction copy$default$1() {
      return this.serializer();
   }

   public PartialFunction copy$default$2() {
      return this.deserializer();
   }

   public boolean copy$default$3() {
      return this.includeLazyVal();
   }

   public String productPrefix() {
      return "FieldSerializer";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.serializer();
            break;
         case 1:
            var10000 = this.deserializer();
            break;
         case 2:
            var10000 = BoxesRunTime.boxToBoolean(this.includeLazyVal());
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
      return x$1 instanceof FieldSerializer;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "serializer";
            break;
         case 1:
            var10000 = "deserializer";
            break;
         case 2:
            var10000 = "includeLazyVal";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.serializer()));
      var1 = Statics.mix(var1, Statics.anyHash(this.deserializer()));
      var1 = Statics.mix(var1, this.includeLazyVal() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label65: {
            boolean var2;
            if (x$1 instanceof FieldSerializer) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label47: {
                  FieldSerializer var4 = (FieldSerializer)x$1;
                  if (this.includeLazyVal() == var4.includeLazyVal()) {
                     label56: {
                        PartialFunction var10000 = this.serializer();
                        PartialFunction var5 = var4.serializer();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label56;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label56;
                        }

                        var10000 = this.deserializer();
                        PartialFunction var6 = var4.deserializer();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label56;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label56;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label47;
                        }
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label65;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public FieldSerializer(final PartialFunction serializer, final PartialFunction deserializer, final boolean includeLazyVal, final ClassTag mf) {
      this.serializer = serializer;
      this.deserializer = deserializer;
      this.includeLazyVal = includeLazyVal;
      this.mf = mf;
      Product.$init$(this);
   }
}
