package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015g\u0001B\u0015+\u0001NB\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0017\"Aq\n\u0001BK\u0002\u0013\u0005!\n\u0003\u0005Q\u0001\tE\t\u0015!\u0003L\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002C,\u0001\u0005#\u0005\u000b\u0011B*\t\u0011a\u0003!Q3A\u0005\u0002)C\u0001\"\u0017\u0001\u0003\u0012\u0003\u0006Ia\u0013\u0005\t5\u0002\u0011)\u001a!C\u0001\u0015\"A1\f\u0001B\tB\u0003%1\n\u0003\u0005]\u0001\tU\r\u0011\"\u0001K\u0011!i\u0006A!E!\u0002\u0013Y\u0005\u0002\u00030\u0001\u0005+\u0007I\u0011A0\t\u0011\r\u0004!\u0011#Q\u0001\n\u0001DQ\u0001\u001a\u0001\u0005\u0002\u0015DQA\u001c\u0001\u0005\u0002=Dq!\u001e\u0001\u0002\u0002\u0013\u0005a\u000fC\u0004\u007f\u0001E\u0005I\u0011A@\t\u0011\u0005U\u0001!%A\u0005\u0002}D\u0011\"a\u0006\u0001#\u0003%\t!!\u0007\t\u0011\u0005u\u0001!%A\u0005\u0002}D\u0001\"a\b\u0001#\u0003%\ta \u0005\t\u0003C\u0001\u0011\u0013!C\u0001\u007f\"I\u00111\u0005\u0001\u0012\u0002\u0013\u0005\u0011Q\u0005\u0005\n\u0003S\u0001\u0011\u0011!C!\u0003WA\u0001\"!\u0010\u0001\u0003\u0003%\tA\u0013\u0005\n\u0003\u007f\u0001\u0011\u0011!C\u0001\u0003\u0003B\u0011\"a\u0012\u0001\u0003\u0003%\t%!\u0013\t\u0013\u0005]\u0003!!A\u0005\u0002\u0005e\u0003\"CA2\u0001\u0005\u0005I\u0011IA3\u0011%\tI\u0007AA\u0001\n\u0003\nY\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0011\u0002p!I\u0011\u0011\u000f\u0001\u0002\u0002\u0013\u0005\u00131O\u0004\n\u0003oR\u0013\u0011!E\u0001\u0003s2\u0001\"\u000b\u0016\u0002\u0002#\u0005\u00111\u0010\u0005\u0007I\u000e\"\t!a%\t\u0013\u000554%!A\u0005F\u0005=\u0004\"CAKG\u0005\u0005I\u0011QAL\u0011%\t9kIA\u0001\n\u0003\u000bI\u000bC\u0005\u0002<\u000e\n\t\u0011\"\u0003\u0002>\ny1\t\\1tg\u001aKG.\u001a%fC\u0012,'O\u0003\u0002,Y\u0005A1oY1mCNLwM\u0003\u0002.]\u000511oY1mCBT!a\f\u0019\u0002\r)\u001cxN\u001c\u001bt\u0015\u0005\t\u0014aA8sO\u000e\u00011\u0003\u0002\u00015uu\u0002\"!\u000e\u001d\u000e\u0003YR\u0011aN\u0001\u0006g\u000e\fG.Y\u0005\u0003sY\u0012a!\u00118z%\u00164\u0007CA\u001b<\u0013\tadGA\u0004Qe>$Wo\u0019;\u0011\u0005y2eBA E\u001d\t\u00015)D\u0001B\u0015\t\u0011%'\u0001\u0004=e>|GOP\u0005\u0002o%\u0011QIN\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0005J\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Fm\u0005)Q.\u001b8peV\t1\n\u0005\u00026\u0019&\u0011QJ\u000e\u0002\u0004\u0013:$\u0018AB7j]>\u0014\b%A\u0003nC*|'/\u0001\u0004nC*|'\u000fI\u0001\nG>t7\u000f^1oiN,\u0012a\u0015\t\u0003)Vk\u0011AK\u0005\u0003-*\u0012AbQ8ogR\fg\u000e\u001e)p_2\f!bY8ogR\fg\u000e^:!\u0003\u00151G.Y4t\u0003\u00191G.Y4tA\u0005Q1\r\\1tg&sG-\u001a=\u0002\u0017\rd\u0017m]:J]\u0012,\u0007\u0010I\u0001\u0010gV\u0004XM]\"mCN\u001c\u0018J\u001c3fq\u0006\u00012/\u001e9fe\u000ec\u0017m]:J]\u0012,\u0007\u0010I\u0001\u000bS:$XM\u001d4bG\u0016\u001cX#\u00011\u0011\u0007y\n7*\u0003\u0002c\u0011\n\u00191+Z9\u0002\u0017%tG/\u001a:gC\u000e,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011\u0019<\u0007.\u001b6lY6\u0004\"\u0001\u0016\u0001\t\u000b%{\u0001\u0019A&\t\u000b={\u0001\u0019A&\t\u000bE{\u0001\u0019A*\t\u000ba{\u0001\u0019A&\t\u000bi{\u0001\u0019A&\t\u000bq{\u0001\u0019A&\t\u000by{\u0001\u0019\u00011\u0002\u0011\r|gn\u001d;b]R$\"\u0001]:\u0011\u0005U\n\u0018B\u0001:7\u0005\r\te.\u001f\u0005\u0006iB\u0001\raS\u0001\u0006S:$W\r_\u0001\u0005G>\u0004\u0018\u0010\u0006\u0005gobL(p\u001f?~\u0011\u001dI\u0015\u0003%AA\u0002-CqaT\t\u0011\u0002\u0003\u00071\nC\u0004R#A\u0005\t\u0019A*\t\u000fa\u000b\u0002\u0013!a\u0001\u0017\"9!,\u0005I\u0001\u0002\u0004Y\u0005b\u0002/\u0012!\u0003\u0005\ra\u0013\u0005\b=F\u0001\n\u00111\u0001a\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\u0001+\u0007-\u000b\u0019a\u000b\u0002\u0002\u0006A!\u0011qAA\t\u001b\t\tIA\u0003\u0003\u0002\f\u00055\u0011!C;oG\",7m[3e\u0015\r\tyAN\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\n\u0003\u0013\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\u001c)\u001a1+a\u0001\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%i\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014AD2paf$C-\u001a4bk2$HEN\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+\t\t9CK\u0002a\u0003\u0007\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0017!\u0011\ty#!\u000f\u000e\u0005\u0005E\"\u0002BA\u001a\u0003k\tA\u0001\\1oO*\u0011\u0011qG\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002<\u0005E\"AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007A\f\u0019\u0005\u0003\u0005\u0002Fm\t\t\u00111\u0001L\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\n\t\u0006\u0003\u001b\n\u0019\u0006]\u0007\u0003\u0003\u001fR1!!\u00157\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003+\nyE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA.\u0003C\u00022!NA/\u0013\r\tyF\u000e\u0002\b\u0005>|G.Z1o\u0011!\t)%HA\u0001\u0002\u0004\u0001\u0018A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\f\u0002h!A\u0011Q\t\u0010\u0002\u0002\u0003\u00071*\u0001\u0005iCND7i\u001c3f)\u0005Y\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u00055\u0012AB3rk\u0006d7\u000f\u0006\u0003\u0002\\\u0005U\u0004\u0002CA#C\u0005\u0005\t\u0019\u00019\u0002\u001f\rc\u0017m]:GS2,\u0007*Z1eKJ\u0004\"\u0001V\u0012\u0014\u000b\r\ni(!#\u0011\u0019\u0005}\u0014QQ&L'.[5\n\u00194\u000e\u0005\u0005\u0005%bAABm\u00059!/\u001e8uS6,\u0017\u0002BAD\u0003\u0003\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c88!\u0011\tY)!%\u000e\u0005\u00055%\u0002BAH\u0003k\t!![8\n\u0007\u001d\u000bi\t\u0006\u0002\u0002z\u0005)\u0011\r\u001d9msRya-!'\u0002\u001c\u0006u\u0015qTAQ\u0003G\u000b)\u000bC\u0003JM\u0001\u00071\nC\u0003PM\u0001\u00071\nC\u0003RM\u0001\u00071\u000bC\u0003YM\u0001\u00071\nC\u0003[M\u0001\u00071\nC\u0003]M\u0001\u00071\nC\u0003_M\u0001\u0007\u0001-A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005-\u0016q\u0017\t\u0006k\u00055\u0016\u0011W\u0005\u0004\u0003_3$AB(qi&|g\u000e\u0005\u00066\u0003g[5jU&L\u0017\u0002L1!!.7\u0005\u0019!V\u000f\u001d7fo!A\u0011\u0011X\u0014\u0002\u0002\u0003\u0007a-A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a0\u0011\t\u0005=\u0012\u0011Y\u0005\u0005\u0003\u0007\f\tD\u0001\u0004PE*,7\r\u001e"
)
public class ClassFileHeader implements Product, Serializable {
   private final int minor;
   private final int major;
   private final ConstantPool constants;
   private final int flags;
   private final int classIndex;
   private final int superClassIndex;
   private final Seq interfaces;

   public static Option unapply(final ClassFileHeader x$0) {
      return ClassFileHeader$.MODULE$.unapply(x$0);
   }

   public static ClassFileHeader apply(final int minor, final int major, final ConstantPool constants, final int flags, final int classIndex, final int superClassIndex, final Seq interfaces) {
      return ClassFileHeader$.MODULE$.apply(minor, major, constants, flags, classIndex, superClassIndex, interfaces);
   }

   public static Function1 tupled() {
      return ClassFileHeader$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ClassFileHeader$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int minor() {
      return this.minor;
   }

   public int major() {
      return this.major;
   }

   public ConstantPool constants() {
      return this.constants;
   }

   public int flags() {
      return this.flags;
   }

   public int classIndex() {
      return this.classIndex;
   }

   public int superClassIndex() {
      return this.superClassIndex;
   }

   public Seq interfaces() {
      return this.interfaces;
   }

   public Object constant(final int index) {
      return this.constants().apply(index);
   }

   public ClassFileHeader copy(final int minor, final int major, final ConstantPool constants, final int flags, final int classIndex, final int superClassIndex, final Seq interfaces) {
      return new ClassFileHeader(minor, major, constants, flags, classIndex, superClassIndex, interfaces);
   }

   public int copy$default$1() {
      return this.minor();
   }

   public int copy$default$2() {
      return this.major();
   }

   public ConstantPool copy$default$3() {
      return this.constants();
   }

   public int copy$default$4() {
      return this.flags();
   }

   public int copy$default$5() {
      return this.classIndex();
   }

   public int copy$default$6() {
      return this.superClassIndex();
   }

   public Seq copy$default$7() {
      return this.interfaces();
   }

   public String productPrefix() {
      return "ClassFileHeader";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToInteger(this.minor());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToInteger(this.major());
            break;
         case 2:
            var10000 = this.constants();
            break;
         case 3:
            var10000 = BoxesRunTime.boxToInteger(this.flags());
            break;
         case 4:
            var10000 = BoxesRunTime.boxToInteger(this.classIndex());
            break;
         case 5:
            var10000 = BoxesRunTime.boxToInteger(this.superClassIndex());
            break;
         case 6:
            var10000 = this.interfaces();
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
      return x$1 instanceof ClassFileHeader;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "minor";
            break;
         case 1:
            var10000 = "major";
            break;
         case 2:
            var10000 = "constants";
            break;
         case 3:
            var10000 = "flags";
            break;
         case 4:
            var10000 = "classIndex";
            break;
         case 5:
            var10000 = "superClassIndex";
            break;
         case 6:
            var10000 = "interfaces";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.minor());
      var1 = Statics.mix(var1, this.major());
      var1 = Statics.mix(var1, Statics.anyHash(this.constants()));
      var1 = Statics.mix(var1, this.flags());
      var1 = Statics.mix(var1, this.classIndex());
      var1 = Statics.mix(var1, this.superClassIndex());
      var1 = Statics.mix(var1, Statics.anyHash(this.interfaces()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label73: {
            boolean var2;
            if (x$1 instanceof ClassFileHeader) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label55: {
                  ClassFileHeader var4 = (ClassFileHeader)x$1;
                  if (this.minor() == var4.minor() && this.major() == var4.major() && this.flags() == var4.flags() && this.classIndex() == var4.classIndex() && this.superClassIndex() == var4.superClassIndex()) {
                     label64: {
                        ConstantPool var10000 = this.constants();
                        ConstantPool var5 = var4.constants();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label64;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label64;
                        }

                        Seq var7 = this.interfaces();
                        Seq var6 = var4.interfaces();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label64;
                           }
                        } else if (!var7.equals(var6)) {
                           break label64;
                        }

                        if (var4.canEqual(this)) {
                           var9 = true;
                           break label55;
                        }
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label73;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   public ClassFileHeader(final int minor, final int major, final ConstantPool constants, final int flags, final int classIndex, final int superClassIndex, final Seq interfaces) {
      this.minor = minor;
      this.major = major;
      this.constants = constants;
      this.flags = flags;
      this.classIndex = classIndex;
      this.superClassIndex = superClassIndex;
      this.interfaces = interfaces;
      Product.$init$(this);
   }
}
