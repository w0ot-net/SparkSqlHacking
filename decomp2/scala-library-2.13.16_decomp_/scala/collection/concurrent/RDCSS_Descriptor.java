package scala.collection.concurrent;

import java.io.Serializable;
import scala.Option;
import scala.Product;
import scala.collection.AbstractIterator;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime$;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eg!B\u0010!\u0001\u00022\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011A\u0003!\u0011#Q\u0001\nyB\u0001\"\u0015\u0001\u0003\u0016\u0004%\tA\u0015\u0005\t-\u0002\u0011\t\u0012)A\u0005'\"Aq\u000b\u0001BK\u0002\u0013\u0005Q\b\u0003\u0005Y\u0001\tE\t\u0015!\u0003?\u0011\u0015I\u0006\u0001\"\u0001[\u0011\u001dy\u0006\u00011A\u0005\u0002\u0001Dq\u0001\u001a\u0001A\u0002\u0013\u0005Q\r\u0003\u0004l\u0001\u0001\u0006K!\u0019\u0005\ba\u0002\t\t\u0011\"\u0001r\u0011\u001di\b!%A\u0005\u0002yD\u0011\"!\u0007\u0001#\u0003%\t!a\u0007\t\u0013\u0005\u0015\u0002!%A\u0005\u0002\u0005\u001d\u0002\"CA\u0017\u0001\u0005\u0005I\u0011IA\u0018\u0011%\t\t\u0005AA\u0001\n\u0003\t\u0019\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011\u0011\u000b\u0001\u0002\u0002\u0013\u0005\u00131\u000b\u0005\n\u0003;\u0002\u0011\u0011!C\u0001\u0003?B\u0011\"a\u0019\u0001\u0003\u0003%\t%!\u001a\t\u0013\u0005%\u0004!!A\u0005B\u0005-\u0004\"CA7\u0001\u0005\u0005I\u0011IA8\u0011%\t\t\bAA\u0001\n\u0003\n\u0019h\u0002\u0006\u0002x\u0001\n\t\u0011#\u0001!\u0003s2\u0011b\b\u0011\u0002\u0002#\u0005\u0001%a\u001f\t\reKB\u0011AAD\u0011%\ti'GA\u0001\n\u000b\ny\u0007C\u0005\u0002\nf\t\t\u0011\"!\u0002\f\"I\u00111U\r\u0002\u0002\u0013\u0005\u0015Q\u0015\u0005\n\u0003\u000fL\u0012\u0011!C\u0005\u0003\u0013\u0014\u0001C\u0015#D'N{F)Z:de&\u0004Ho\u001c:\u000b\u0005\u0005\u0012\u0013AC2p]\u000e,(O]3oi*\u00111\u0005J\u0001\u000bG>dG.Z2uS>t'\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\u0016\u0007\u001d\"ej\u0005\u0003\u0001Q1z\u0003CA\u0015+\u001b\u0005!\u0013BA\u0016%\u0005\u0019\te.\u001f*fMB\u0011\u0011&L\u0005\u0003]\u0011\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00021s9\u0011\u0011g\u000e\b\u0003eYj\u0011a\r\u0006\u0003iU\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002K%\u0011\u0001\bJ\u0001\ba\u0006\u001c7.Y4f\u0013\tQ4H\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u00029I\u0005\u0019q\u000e\u001c3\u0016\u0003y\u0002Ba\u0010!C\u001b6\t\u0001%\u0003\u0002BA\t)\u0011JT8eKB\u00111\t\u0012\u0007\u0001\t\u0015)\u0005A1\u0001G\u0005\u0005Y\u0015CA$K!\tI\u0003*\u0003\u0002JI\t9aj\u001c;iS:<\u0007CA\u0015L\u0013\taEEA\u0002B]f\u0004\"a\u0011(\u0005\u000b=\u0003!\u0019\u0001$\u0003\u0003Y\u000bAa\u001c7eA\u0005aQ\r\u001f9fGR,G-\\1j]V\t1\u000b\u0005\u0003@)\nk\u0015BA+!\u0005!i\u0015-\u001b8O_\u0012,\u0017!D3ya\u0016\u001cG/\u001a3nC&t\u0007%\u0001\u0002om\u0006\u0019aN\u001e\u0011\u0002\rqJg.\u001b;?)\u0011YF,\u00180\u0011\t}\u0002!)\u0014\u0005\u0006y\u001d\u0001\rA\u0010\u0005\u0006#\u001e\u0001\ra\u0015\u0005\u0006/\u001e\u0001\rAP\u0001\nG>lW.\u001b;uK\u0012,\u0012!\u0019\t\u0003S\tL!a\u0019\u0013\u0003\u000f\t{w\u000e\\3b]\u0006i1m\\7nSR$X\rZ0%KF$\"AZ5\u0011\u0005%:\u0017B\u00015%\u0005\u0011)f.\u001b;\t\u000f)L\u0011\u0011!a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0002\u0015\r|W.\\5ui\u0016$\u0007\u0005\u000b\u0002\u000b[B\u0011\u0011F\\\u0005\u0003_\u0012\u0012\u0001B^8mCRLG.Z\u0001\u0005G>\u0004\u00180F\u0002sk^$Ba\u001d={yB!q\b\u0001;w!\t\u0019U\u000fB\u0003F\u0017\t\u0007a\t\u0005\u0002Do\u0012)qj\u0003b\u0001\r\"9Ah\u0003I\u0001\u0002\u0004I\b\u0003B AiZDq!U\u0006\u0011\u0002\u0003\u00071\u0010\u0005\u0003@)R4\bbB,\f!\u0003\u0005\r!_\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0015y\u0018QCA\f+\t\t\tAK\u0002?\u0003\u0007Y#!!\u0002\u0011\t\u0005\u001d\u0011\u0011C\u0007\u0003\u0003\u0013QA!a\u0003\u0002\u000e\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u001f!\u0013AC1o]>$\u0018\r^5p]&!\u00111CA\u0005\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006\u000b2\u0011\rA\u0012\u0003\u0006\u001f2\u0011\rAR\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0019\ti\"!\t\u0002$U\u0011\u0011q\u0004\u0016\u0004'\u0006\rA!B#\u000e\u0005\u00041E!B(\u000e\u0005\u00041\u0015AD2paf$C-\u001a4bk2$HeM\u000b\u0006\u007f\u0006%\u00121\u0006\u0003\u0006\u000b:\u0011\rA\u0012\u0003\u0006\u001f:\u0011\rAR\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005E\u0002\u0003BA\u001a\u0003{i!!!\u000e\u000b\t\u0005]\u0012\u0011H\u0001\u0005Y\u0006twM\u0003\u0002\u0002<\u0005!!.\u0019<b\u0013\u0011\ty$!\u000e\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t)\u0005E\u0002*\u0003\u000fJ1!!\u0013%\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\rQ\u0015q\n\u0005\tUF\t\t\u00111\u0001\u0002F\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002VA)\u0011qKA-\u00156\t!%C\u0002\u0002\\\t\u0012\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0019\u0011-!\u0019\t\u000f)\u001c\u0012\u0011!a\u0001\u0015\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\t$a\u001a\t\u0011)$\u0012\u0011!a\u0001\u0003\u000b\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u000b\n\u0001\u0002^8TiJLgn\u001a\u000b\u0003\u0003c\ta!Z9vC2\u001cHcA1\u0002v!9!nFA\u0001\u0002\u0004Q\u0015\u0001\u0005*E\u0007N\u001bv\fR3tGJL\u0007\u000f^8s!\ty\u0014d\u0005\u0003\u001aQ\u0005u\u0004\u0003BA@\u0003\u000bk!!!!\u000b\t\u0005\r\u0015\u0011H\u0001\u0003S>L1AOAA)\t\tI(A\u0003baBd\u00170\u0006\u0004\u0002\u000e\u0006M\u0015q\u0013\u000b\t\u0003\u001f\u000bI*!(\u0002\"B1q\bAAI\u0003+\u00032aQAJ\t\u0015)ED1\u0001G!\r\u0019\u0015q\u0013\u0003\u0006\u001fr\u0011\rA\u0012\u0005\u0007yq\u0001\r!a'\u0011\r}\u0002\u0015\u0011SAK\u0011\u0019\tF\u00041\u0001\u0002 B1q\bVAI\u0003+Caa\u0016\u000fA\u0002\u0005m\u0015aB;oCB\u0004H._\u000b\u0007\u0003O\u000bI,!0\u0015\t\u0005%\u0016\u0011\u0019\t\u0006S\u0005-\u0016qV\u0005\u0004\u0003[##AB(qi&|g\u000eE\u0005*\u0003c\u000b),a0\u00026&\u0019\u00111\u0017\u0013\u0003\rQ+\b\u000f\\34!\u0019y\u0004)a.\u0002<B\u00191)!/\u0005\u000b\u0015k\"\u0019\u0001$\u0011\u0007\r\u000bi\fB\u0003P;\t\u0007a\t\u0005\u0004@)\u0006]\u00161\u0018\u0005\n\u0003\u0007l\u0012\u0011!a\u0001\u0003\u000b\f1\u0001\u001f\u00131!\u0019y\u0004!a.\u0002<\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u001a\t\u0005\u0003g\ti-\u0003\u0003\u0002P\u0006U\"AB(cU\u0016\u001cG\u000f"
)
public class RDCSS_Descriptor implements Product, Serializable {
   private final INode old;
   private final MainNode expectedmain;
   private final INode nv;
   private volatile boolean committed;

   public static Option unapply(final RDCSS_Descriptor x$0) {
      return RDCSS_Descriptor$.MODULE$.unapply(x$0);
   }

   public static RDCSS_Descriptor apply(final INode old, final MainNode expectedmain, final INode nv) {
      RDCSS_Descriptor$ var10000 = RDCSS_Descriptor$.MODULE$;
      return new RDCSS_Descriptor(old, expectedmain, nv);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public INode old() {
      return this.old;
   }

   public MainNode expectedmain() {
      return this.expectedmain;
   }

   public INode nv() {
      return this.nv;
   }

   public boolean committed() {
      return this.committed;
   }

   public void committed_$eq(final boolean x$1) {
      this.committed = x$1;
   }

   public RDCSS_Descriptor copy(final INode old, final MainNode expectedmain, final INode nv) {
      return new RDCSS_Descriptor(old, expectedmain, nv);
   }

   public INode copy$default$1() {
      return this.old();
   }

   public MainNode copy$default$2() {
      return this.expectedmain();
   }

   public INode copy$default$3() {
      return this.nv();
   }

   public String productPrefix() {
      return "RDCSS_Descriptor";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.old();
         case 1:
            return this.expectedmain();
         case 2:
            return this.nv();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return new AbstractIterator(this) {
         private int c;
         private final int cmax;
         private final Product x$2;

         public boolean hasNext() {
            return this.c < this.cmax;
         }

         public Object next() {
            Object result = this.x$2.productElement(this.c);
            ++this.c;
            return result;
         }

         public {
            this.x$2 = x$2;
            this.c = 0;
            this.cmax = x$2.productArity();
         }
      };
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof RDCSS_Descriptor;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "old";
         case 1:
            return "expectedmain";
         case 2:
            return "nv";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return MurmurHash3$.MODULE$.productHash(this);
   }

   public String toString() {
      return ScalaRunTime$.MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      if (this != x$1) {
         if (x$1 instanceof RDCSS_Descriptor) {
            RDCSS_Descriptor var2 = (RDCSS_Descriptor)x$1;
            INode var10000 = this.old();
            INode var3 = var2.old();
            if (var10000 == null) {
               if (var3 != null) {
                  return false;
               }
            } else if (!var10000.equals(var3)) {
               return false;
            }

            MainNode var6 = this.expectedmain();
            MainNode var4 = var2.expectedmain();
            if (var6 == null) {
               if (var4 != null) {
                  return false;
               }
            } else if (!var6.equals(var4)) {
               return false;
            }

            INode var7 = this.nv();
            INode var5 = var2.nv();
            if (var7 == null) {
               if (var5 != null) {
                  return false;
               }
            } else if (!var7.equals(var5)) {
               return false;
            }

            if (var2.canEqual(this)) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public RDCSS_Descriptor(final INode old, final MainNode expectedmain, final INode nv) {
      this.old = old;
      this.expectedmain = expectedmain;
      this.nv = nv;
      this.committed = false;
   }
}
