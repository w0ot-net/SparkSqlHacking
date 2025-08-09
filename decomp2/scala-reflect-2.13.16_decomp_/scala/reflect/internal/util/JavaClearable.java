package scala.reflect.internal.util;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Map;
import scala.Option;
import scala.Option.;
import scala.collection.mutable.Clearable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ms!\u0002\f\u0018\u0011\u0003\u0001c!\u0002\u0012\u0018\u0011\u0003\u0019\u0003\"\u0002\u0015\u0002\t\u0003I\u0003\"\u0002\u0016\u0002\t\u0003Y\u0003bBA\u001e\u0003\u0011\u0005\u0011Q\b\u0004\u0005}\u00061q\u0010\u0003\u00069\u000b\t\u0005\t\u0015!\u0003\u0002 9Aa\u0001K\u0003\u0005\u0002\u0005\u0005\u0002\"\u00021\u0006\t\u0003\ng\u0001\u00025\u0002\r%D\u0011\u0002O\u0005\u0003\u0002\u0003\u0006I!\u001f\b\t\u000b!JA\u0011\u0001>\t\u000b\u0001LA\u0011I1\u0007\u000b\t:\u0012\u0011\u0005\u0018\t\u0011aj!Q1A\u0005\u0012eB\u0001\u0002T\u0007\u0003\u0002\u0003\u0006IA\u000f\u0005\u0006Q5!\t\"\u0014\u0005\b!6\u0011\r\u0011\"\u0011R\u0011\u0019)V\u0002)A\u0005%\")a+\u0004C!/\")\u0001-\u0004D\u0001C\")Q-\u0004C\u0001M\u0006i!*\u0019<b\u00072,\u0017M]1cY\u0016T!\u0001G\r\u0002\tU$\u0018\u000e\u001c\u0006\u00035m\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00039u\tqA]3gY\u0016\u001cGOC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0007\u0001\u0001\"!I\u0001\u000e\u0003]\u0011QBS1wC\u000ecW-\u0019:bE2,7CA\u0001%!\t)c%D\u0001\u001e\u0013\t9SD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0001\nQBZ8s\u0007>dG.Z2uS>tWc\u0001\u0017\u0002*Q\u0019Q&a\u000e\u0011\t\u0005j\u0011qE\u000b\u0003_\u0019\u001b2!\u0004\u00131!\t\td'D\u00013\u0015\t\u0019D'A\u0004nkR\f'\r\\3\u000b\u0005Uj\u0012AC2pY2,7\r^5p]&\u0011qG\r\u0002\n\u00072,\u0017M]1cY\u0016\fq\u0001Z1uCJ+g-F\u0001;!\rY$\tR\u0007\u0002y)\u0011QHP\u0001\u0004e\u00164'BA A\u0003\u0011a\u0017M\\4\u000b\u0003\u0005\u000bAA[1wC&\u00111\t\u0010\u0002\u000e/\u0016\f7NU3gKJ,gnY3\u0011\u0005\u00153E\u0002\u0001\u0003\u0006\u000f6\u0011\r\u0001\u0013\u0002\u0002)F\u0011\u0011\n\n\t\u0003K)K!aS\u000f\u0003\u000f9{G\u000f[5oO\u0006AA-\u0019;b%\u00164\u0007\u0005\u0006\u0002O\u001fB\u0019\u0011%\u0004#\t\u000ba\u0002\u0002\u0019\u0001\u001e\u0002\u0011!\f7\u000f[\"pI\u0016,\u0012A\u0015\t\u0003KMK!\u0001V\u000f\u0003\u0007%sG/A\u0005iCND7i\u001c3fA\u00051Q-];bYN$\"\u0001W.\u0011\u0005\u0015J\u0016B\u0001.\u001e\u0005\u001d\u0011un\u001c7fC:DQ\u0001X\nA\u0002u\u000b1a\u001c2k!\t)c,\u0003\u0002`;\t\u0019\u0011I\\=\u0002\u000b\rdW-\u0019:\u0015\u0003\t\u0004\"!J2\n\u0005\u0011l\"\u0001B+oSR\fq![:WC2LG-F\u0001YS\ri\u0011\"\u0002\u0002\u0018\u0015\u00064\u0018m\u00117fCJ\f'\r\\3D_2dWm\u0019;j_:,\"A[7\u0014\u0005%Y\u0007cA\u0011\u000eYB\u0011Q)\u001c\u0003\u0006\u000f&\u0011\rA\\\t\u0003\u0013>\u0004$\u0001\u001d<\u0011\u0007E\u001cX/D\u0001s\u0015\tA\u0002)\u0003\u0002ue\nQ1i\u001c7mK\u000e$\u0018n\u001c8\u0011\u0005\u00153H!C<n\u0003\u0003\u0005\tQ!\u0001y\u0005\ryFEN\t\u0003\u0013v\u00032a\u000f\"m)\tYX\u0010E\u0002}\u00131l\u0011!\u0001\u0005\u0006q-\u0001\r!\u001f\u0002\u0011\u0015\u00064\u0018m\u00117fCJ\f'\r\\3NCB,B!!\u0001\u0002\bM\u0019Q!a\u0001\u0011\t\u0005j\u0011Q\u0001\t\u0004\u000b\u0006\u001dAAB$\u0006\u0005\u0004\tI!E\u0002J\u0003\u0017\u0001d!!\u0004\u0002\u0016\u0005m\u0001cB9\u0002\u0010\u0005M\u0011\u0011D\u0005\u0004\u0003#\u0011(aA'baB\u0019Q)!\u0006\u0005\u0017\u0005]\u0011qAA\u0001\u0002\u0003\u0015\t\u0001\u001f\u0002\u0004?\u0012\"\u0004cA#\u0002\u001c\u0011Y\u0011QDA\u0004\u0003\u0003\u0005\tQ!\u0001y\u0005\ryF%\u000e\t\u0005w\t\u000b)\u0001\u0006\u0003\u0002$\u0005\u0015\u0002\u0003\u0002?\u0006\u0003\u000bAa\u0001O\u0004A\u0002\u0005}\u0001cA#\u0002*\u00111qi\u0001b\u0001\u0003W\t2!SA\u0017a\u0011\ty#a\r\u0011\tE\u001c\u0018\u0011\u0007\t\u0004\u000b\u0006MBaCA\u001b\u0003S\t\t\u0011!A\u0003\u0002a\u00141a\u0018\u00132\u0011\u001d\tId\u0001a\u0001\u0003O\tA\u0001Z1uC\u00061am\u001c:NCB,B!a\u0010\u0002FQ!\u0011\u0011IA-!\u0011\tS\"a\u0011\u0011\u0007\u0015\u000b)\u0005\u0002\u0004H\t\t\u0007\u0011qI\t\u0004\u0013\u0006%\u0003GBA&\u0003\u001f\n)\u0006E\u0004r\u0003\u001f\ti%a\u0015\u0011\u0007\u0015\u000by\u0005B\u0006\u0002R\u0005\u0015\u0013\u0011!A\u0001\u0006\u0003A(aA0%eA\u0019Q)!\u0016\u0005\u0017\u0005]\u0013QIA\u0001\u0002\u0003\u0015\t\u0001\u001f\u0002\u0004?\u0012\u001a\u0004bBA\u001d\t\u0001\u0007\u00111\t"
)
public abstract class JavaClearable implements Clearable {
   private final WeakReference dataRef;
   private final int hashCode;

   public static JavaClearable forMap(final Map data) {
      return JavaClearable$.MODULE$.forMap(data);
   }

   public static JavaClearable forCollection(final Collection data) {
      return JavaClearable$.MODULE$.forCollection(data);
   }

   public WeakReference dataRef() {
      return this.dataRef;
   }

   public int hashCode() {
      return this.hashCode;
   }

   public boolean equals(final Object obj) {
      if (obj instanceof JavaClearable) {
         JavaClearable var2 = (JavaClearable)obj;
         if (this == var2) {
            return true;
         } else {
            Object thisData = this.dataRef().get();
            Object thatData = var2.dataRef().get();
            return thisData == thatData && thisData != null;
         }
      } else {
         return false;
      }
   }

   public abstract void clear();

   public boolean isValid() {
      return this.dataRef().get() != null;
   }

   public JavaClearable(final WeakReference dataRef) {
      this.dataRef = dataRef;
      this.hashCode = System.identityHashCode(dataRef.get());
   }

   private static final class JavaClearableMap extends JavaClearable {
      public void clear() {
         Option var10000 = .MODULE$.apply(super.dataRef().get());
         if (var10000 == null) {
            throw null;
         } else {
            Option foreach_this = var10000;
            if (!foreach_this.isEmpty()) {
               ((Map)foreach_this.get()).clear();
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$clear$1(final Map x$1) {
         x$1.clear();
      }

      public JavaClearableMap(final WeakReference dataRef) {
         super(dataRef);
      }

      // $FF: synthetic method
      public static final Object $anonfun$clear$1$adapted(final Map x$1) {
         $anonfun$clear$1(x$1);
         return BoxedUnit.UNIT;
      }
   }

   private static final class JavaClearableCollection extends JavaClearable {
      public void clear() {
         Option var10000 = .MODULE$.apply(super.dataRef().get());
         if (var10000 == null) {
            throw null;
         } else {
            Option foreach_this = var10000;
            if (!foreach_this.isEmpty()) {
               ((Collection)foreach_this.get()).clear();
            }
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$clear$2(final Collection x$2) {
         x$2.clear();
      }

      public JavaClearableCollection(final WeakReference dataRef) {
         super(dataRef);
      }

      // $FF: synthetic method
      public static final Object $anonfun$clear$2$adapted(final Collection x$2) {
         $anonfun$clear$2(x$2);
         return BoxedUnit.UNIT;
      }
   }
}
