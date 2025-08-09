package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.Iterator;
import scala.math.Ordered;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.OrderedProxy;
import scala.runtime.RichInt;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b!B\u0013'\u0003\u0003i\u0003\u0002C\u001e\u0001\u0005\u000b\u0007I\u0011\u0001\u001f\t\u0011u\u0002!\u0011!Q\u0001\neBQA\u0010\u0001\u0005\u0002}*A!\u0011\u0001\u0001\u0005\"9Q\t\u0001b\u0001\n\u00031\u0005BB%\u0001A\u0003%q\tC\u0003K\u0001\u0011\u00051\nC\u0003P\u0001\u0011\u00051\nC\u0004Q\u0001\t\u0007I\u0011A&\t\rE\u0003\u0001\u0015!\u0003M\u0011\u0015\u0011\u0006\u0001\"\u0001L\u0011\u001d\u0019\u0006\u00011A\u0005\nqBq\u0001\u0016\u0001A\u0002\u0013%Q\u000b\u0003\u0004\\\u0001\u0001\u0006K!\u000f\u0005\u00069\u0002!\t\u0001\u0010\u0005\u0006;\u0002!\tA\u0018\u0005\u0006E\u0002!\ta\u0019\u0005\u0006U\u00021\ta\u001b\u0005\u0006o\u0002!\ta\u001b\u0005\u0006q\u0002!\tA\u0018\u0005\bs\u0002\u0011\r\u0011\"\u0002_\u0011\u0019Q\b\u0001)A\u0007?\"91\u0010\u0001b\u0001\n\u000bq\u0006B\u0002?\u0001A\u00035q\fC\u0004~\u0001\t\u0007IQ\u00010\t\ry\u0004\u0001\u0015!\u0004`\u0011\u001dy\bA1A\u0005\u0006yCq!!\u0001\u0001A\u00035q\f\u0003\u0005\u0002\u0004\u0001\u0011\r\u0011\"\u0002_\u0011\u001d\t)\u0001\u0001Q\u0001\u000e}Ca!a\u0002\u0001\t\u0003q\u0006bBA\u0005\u0001\u0019\u0005\u00111\u0002\u0005\b\u0003\u001b\u0001A\u0011IA\b\u0011\u001d\t\t\u0002\u0001C!\u0003'Aq!!\u0006\u0001\t\u0003\n9\u0002C\u0004\u0002$\u0001!\t%!\n\u0003\u000bAC\u0017m]3\u000b\u0005\u001dB\u0013\u0001C5oi\u0016\u0014h.\u00197\u000b\u0005%R\u0013a\u0002:fM2,7\r\u001e\u0006\u0002W\u0005)1oY1mC\u000e\u00011c\u0001\u0001/eA\u0011q\u0006M\u0007\u0002U%\u0011\u0011G\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007M2\u0014H\u0004\u00020i%\u0011QGK\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0004HA\u0004Pe\u0012,'/\u001a3\u000b\u0005UR\u0003C\u0001\u001e\u0001\u001b\u00051\u0013\u0001\u00029sKZ,\u0012!O\u0001\u0006aJ,g\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005e\u0002\u0005\"B\u001e\u0004\u0001\u0004I$AA%e!\ty3)\u0003\u0002EU\t\u0019\u0011J\u001c;\u0002\u0005%$W#A$\u0011\u0005!#Q\"\u0001\u0001\u0002\u0007%$\u0007%A\u0005oKb$h\t\\1hgV\tA\n\u0005\u00020\u001b&\u0011aJ\u000b\u0002\u0005\u0019>tw-\u0001\u0005oK^4E.Y4t\u0003\u00151W.Y:l\u0003\u00191W.Y:lA\u0005Aa\r\\1h\u001b\u0006\u001c8.\u0001\u0002oq\u00061a\u000e_0%KF$\"AV-\u0011\u0005=:\u0016B\u0001-+\u0005\u0011)f.\u001b;\t\u000fik\u0011\u0011!a\u0001s\u0005\u0019\u0001\u0010J\u0019\u0002\u00079D\b%\u0001\u0003oKb$\u0018a\u00025bg:+\u0007\u0010^\u000b\u0002?B\u0011q\u0006Y\u0005\u0003C*\u0012qAQ8pY\u0016\fg.\u0001\u0005ji\u0016\u0014\u0018\r^8s+\u0005!\u0007cA3is5\taM\u0003\u0002hU\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005%4'\u0001C%uKJ\fGo\u001c:\u0002\t9\fW.Z\u000b\u0002YB\u0011Q\u000e\u001e\b\u0003]J\u0004\"a\u001c\u0016\u000e\u0003AT!!\u001d\u0017\u0002\rq\u0012xn\u001c;?\u0013\t\u0019(&\u0001\u0004Qe\u0016$WMZ\u0005\u0003kZ\u0014aa\u0015;sS:<'BA:+\u0003-!Wm]2sSB$\u0018n\u001c8\u0002\u0013\rDWmY6bE2,\u0017aC3sCN,G\rV=qKN\fA\"\u001a:bg\u0016$G+\u001f9fg\u0002\n1B\u001a7bi\u000ec\u0017m]:fg\u0006aa\r\\1u\u00072\f7o]3tA\u0005Y1\u000f]3dS\u0006d\u0017N_3e\u00031\u0019\b/Z2jC2L'0\u001a3!\u0003)\u0011XMZ\"iK\u000e\\W\rZ\u0001\fe\u001647\t[3dW\u0016$\u0007%A\u0007bgNLwM\\:GS\u0016dGm]\u0001\u000fCN\u001c\u0018n\u001a8t\r&,G\u000eZ:!\u0003=YW-\u001a9t)f\u0004X\rU1sC6\u001c\u0018a\u0001:v]R\ta+\u0001\u0005u_N#(/\u001b8h)\u0005a\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003\t\u000ba!Z9vC2\u001cHcA0\u0002\u001a!9\u00111D\u0012A\u0002\u0005u\u0011!B8uQ\u0016\u0014\bcA\u0018\u0002 %\u0019\u0011\u0011\u0005\u0016\u0003\u0007\u0005s\u00170A\u0004d_6\u0004\u0018M]3\u0015\u0007\u001d\u000b9\u0003\u0003\u0004\u0002*\u0011\u0002\r!O\u0001\u0005i\"\fG\u000f"
)
public abstract class Phase implements Ordered {
   private final Phase prev;
   private final int id;
   private final long fmask;
   private Phase nx;
   private final boolean erasedTypes;
   private final boolean flatClasses;
   private final boolean specialized;
   private final boolean refChecked;
   private final boolean assignsFields;

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public Phase prev() {
      return this.prev;
   }

   public int id() {
      return this.id;
   }

   public long nextFlags() {
      return 0L;
   }

   public long newFlags() {
      return 0L;
   }

   public long fmask() {
      return this.fmask;
   }

   public long flagMask() {
      return this.fmask();
   }

   private Phase nx() {
      return this.nx;
   }

   private void nx_$eq(final Phase x$1) {
      this.nx = x$1;
   }

   public Phase next() {
      return this.nx() == NoPhase$.MODULE$ ? this : this.nx();
   }

   public boolean hasNext() {
      Phase var10000 = this.next();
      if (var10000 != null) {
         if (var10000.equals(this)) {
            return false;
         }
      }

      return true;
   }

   public Iterator iterator() {
      Iterator var10000 = .MODULE$.Iterator();
      Function1 iterate_f = (x$1) -> x$1.nx();
      if (var10000 == null) {
         throw null;
      } else {
         Iterator..anon.26 var3 = new Iterator..anon.26(this, iterate_f);
         Object var2 = null;
         return var3.takeWhile((x$2) -> BoxesRunTime.boxToBoolean($anonfun$iterator$2(x$2)));
      }
   }

   public abstract String name();

   public String description() {
      return this.name();
   }

   public boolean checkable() {
      return true;
   }

   public final boolean erasedTypes() {
      return this.erasedTypes;
   }

   public final boolean flatClasses() {
      return this.flatClasses;
   }

   public final boolean specialized() {
      return this.specialized;
   }

   public final boolean refChecked() {
      return this.refChecked;
   }

   public final boolean assignsFields() {
      return this.assignsFields;
   }

   public boolean keepsTypeParams() {
      return true;
   }

   public abstract void run();

   public String toString() {
      return this.name();
   }

   public int hashCode() {
      return this.id() + Statics.anyHash(this.name());
   }

   public boolean equals(final Object other) {
      if (!(other instanceof Phase)) {
         return false;
      } else {
         Phase var2 = (Phase)other;
         if (this.id() == var2.id()) {
            String var10000 = this.name();
            String var3 = var2.name();
            if (var10000 == null) {
               if (var3 == null) {
                  return true;
               }
            } else if (var10000.equals(var3)) {
               return true;
            }
         }

         return false;
      }
   }

   public int compare(final Phase that) {
      return OrderedProxy.compare$(new RichInt(this.id()), that.id());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$2(final Phase x$2) {
      return x$2 != NoPhase$.MODULE$;
   }

   public Phase(final Phase prev) {
      this.prev = prev;
      if (prev != null && prev != NoPhase$.MODULE$) {
         prev.nx_$eq(this);
      }

      boolean var7;
      label111: {
         label110: {
            this.id = prev == null ? 0 : prev.id() + 1;
            this.fmask = prev == null ? 1155173304420532223L : prev.flagMask() | prev.nextFlags() | this.newFlags();
            this.nx = NoPhase$.MODULE$;
            if (prev != null && prev != NoPhase$.MODULE$) {
               String var10001 = prev.name();
               String var2 = "erasure";
               if (var10001 != null) {
                  if (var10001.equals(var2)) {
                     break label110;
                  }
               }

               if (prev.erasedTypes()) {
                  break label110;
               }
            }

            var7 = false;
            break label111;
         }

         var7 = true;
      }

      label100: {
         label99: {
            this.erasedTypes = var7;
            if (prev != null && prev != NoPhase$.MODULE$) {
               String var8 = prev.name();
               String var3 = "flatten";
               if (var8 != null) {
                  if (var8.equals(var3)) {
                     break label99;
                  }
               }

               if (prev.flatClasses()) {
                  break label99;
               }
            }

            var7 = false;
            break label100;
         }

         var7 = true;
      }

      label89: {
         label88: {
            this.flatClasses = var7;
            if (prev != null && prev != NoPhase$.MODULE$) {
               String var10 = prev.name();
               String var4 = "specialize";
               if (var10 != null) {
                  if (var10.equals(var4)) {
                     break label88;
                  }
               }

               if (prev.specialized()) {
                  break label88;
               }
            }

            var7 = false;
            break label89;
         }

         var7 = true;
      }

      label78: {
         label77: {
            this.specialized = var7;
            if (prev != null && prev != NoPhase$.MODULE$) {
               String var12 = prev.name();
               String var5 = "refchecks";
               if (var12 != null) {
                  if (var12.equals(var5)) {
                     break label77;
                  }
               }

               if (prev.refChecked()) {
                  break label77;
               }
            }

            var7 = false;
            break label78;
         }

         var7 = true;
      }

      label67: {
         label66: {
            this.refChecked = var7;
            if (prev != null && prev != NoPhase$.MODULE$) {
               String var14 = prev.name();
               String var6 = "fields";
               if (var14 != null) {
                  if (var14.equals(var6)) {
                     break label66;
                  }
               }

               if (prev.assignsFields()) {
                  break label66;
               }
            }

            var7 = false;
            break label67;
         }

         var7 = true;
      }

      this.assignsFields = var7;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
