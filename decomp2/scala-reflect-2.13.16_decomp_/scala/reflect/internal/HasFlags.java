package scala.reflect.internal;

import scala.collection.immutable.List;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]eaB\u001e=!\u0003\r\ta\u0011\u0005\u0006\u0011\u0002!\t!\u0013\u0003\u0006\u001b\u0002\u0011\tA\u0014\u0003\u0006+\u0002\u0011\tA\u0014\u0005\u0006-\u00021\tb\u0016\u0005\u00067\u00021\t\u0001\u0018\u0005\u0006?\u00021\t\u0001\u0019\u0005\u0006S\u00021\tA\u001b\u0005\u0006]\u00021\ta\u001c\u0005\u0006e\u00021\ta\u001d\u0005\u0006m\u0002!\ta\u001e\u0005\u0006s\u0002!\tA\u001f\u0005\u0007s\u0002!\t!!\u0004\t\r\u0005E\u0001\u0001\"\u0001X\u0011\u001d\t\u0019\u0002\u0001C\u0001\u0003+Aa!!\u0007\u0001\t\u0003Q\u0007BBA\u000e\u0001\u0011\u0005!\u000e\u0003\u0004\u0002\u001e\u0001!\tA\u001b\u0005\u0007\u0003?\u0001A\u0011\u00016\t\r\u0005\u0005\u0002\u0001\"\u0001k\u0011\u0019\t\u0019\u0003\u0001C\u0001U\"1\u0011\u0011\b\u0001\u0005\u0002)Da!a\u000f\u0001\t\u0003Q\u0007BBA\u001f\u0001\u0011\u0005!\u000e\u0003\u0004\u0002@\u0001!\tA\u001b\u0005\u0007\u0003\u0003\u0002A\u0011\u00016\t\r\u0005\r\u0003\u0001\"\u0001k\u0011\u0019\t)\u0005\u0001C\u0001U\"1\u0011q\t\u0001\u0005\u0002)Da!!\u0013\u0001\t\u0003Q\u0007BBA&\u0001\u0011\u0005!\u000e\u0003\u0004\u0002N\u0001!\tA\u001b\u0005\u0007\u0003\u001f\u0002A\u0011\u00016\t\r\u0005E\u0003\u0001\"\u0001k\u0011\u0019\t\u0019\u0006\u0001C\u0001U\"1\u0011Q\u000b\u0001\u0005\u0002)Da!a\u0016\u0001\t\u0003Q\u0007BBA-\u0001\u0011\u0005!\u000e\u0003\u0004\u0002\\\u0001!\tA\u001b\u0005\u0007\u0003;\u0002A\u0011\u00016\t\r\u0005}\u0003\u0001\"\u0001k\u0011\u0019\t\t\u0007\u0001C\u0001U\"1\u00111\r\u0001\u0005\u0002)Da!!\u001a\u0001\t\u0003Q\u0007BBA4\u0001\u0011\u0005!\u000e\u0003\u0004\u0002p\u0001!\tA\u001b\u0005\u0007\u0003c\u0002A\u0011\u00016\t\r\u0005M\u0004\u0001\"\u0001k\u0011\u0019\t)\b\u0001C\u0001U\"1\u0011q\u000f\u0001\u0005\u0002)Da!!\u001f\u0001\t\u0003Q\u0007BBA>\u0001\u0011\u0005!\u000e\u0003\u0004\u0002~\u0001!\tA\u001b\u0005\u0007\u0003\u007f\u0002A\u0011\u00016\t\r\u0005\u0005\u0005\u0001\"\u0001k\u0011\u001d\t\u0019\t\u0001C\u0001\u0003\u000bCa!a#\u0001\t\u0003Q\bbBAG\u0001\u0011E\u0011q\u0012\u0005\u0007\u0003+\u0003A\u0011\u00016\u0003\u0011!\u000b7O\u00127bONT!!\u0010 \u0002\u0011%tG/\u001a:oC2T!a\u0010!\u0002\u000fI,g\r\\3di*\t\u0011)A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001!\u0005CA#G\u001b\u0005\u0001\u0015BA$A\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0013\t\u0003\u000b.K!\u0001\u0014!\u0003\tUs\u0017\u000e\u001e\u0002\u0013\u0003\u000e\u001cWm]:C_VtG-\u0019:z)f\u0004X-\u0005\u0002P%B\u0011Q\tU\u0005\u0003#\u0002\u0013qAT8uQ&tw\r\u0005\u0002F'&\u0011A\u000b\u0011\u0002\u0004\u0003:L(AD!o]>$\u0018\r^5p]RK\b/Z\u0001\u0006M2\fwm]\u000b\u00021B\u0011Q)W\u0005\u00035\u0002\u0013A\u0001T8oO\u0006i\u0001O]5wCR,w+\u001b;iS:,\u0012!\u0018\t\u0003=\ni\u0011\u0001A\u0001\fC:tw\u000e^1uS>t7/F\u0001b!\r\u0011W\r\u001b\b\u0003\u000b\u000eL!\u0001\u001a!\u0002\u000fA\f7m[1hK&\u0011am\u001a\u0002\u0005\u0019&\u001cHO\u0003\u0002e\u0001B\u0011alA\u0001\u0012Q\u0006\u001c\u0018iY2fgN\u0014u.\u001e8eCJLX#A6\u0011\u0005\u0015c\u0017BA7A\u0005\u001d\u0011un\u001c7fC:\fq\u0001[1t\r2\fw\r\u0006\u0002la\")\u0011\u000f\u0003a\u00011\u0006!a\r\\1h\u0003-A\u0017m]!mY\u001ac\u0017mZ:\u0015\u0005-$\b\"B;\n\u0001\u0004A\u0016\u0001B7bg.\f!\u0002[1t\u001d>4E.Y4t)\tY\u0007\u0010C\u0003v\u0015\u0001\u0007\u0001,\u0001\u0006gY\u0006<7\u000b\u001e:j]\u001e,\u0012a\u001f\t\u0004y\u0006\u001dabA?\u0002\u0004A\u0011a\u0010Q\u0007\u0002\u007f*\u0019\u0011\u0011\u0001\"\u0002\rq\u0012xn\u001c;?\u0013\r\t)\u0001Q\u0001\u0007!J,G-\u001a4\n\t\u0005%\u00111\u0002\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005\u0015\u0001\tF\u0002|\u0003\u001fAQ!\u001e\u0007A\u0002a\u000b\u0001B\u001a7bO6\u000b7o[\u0001\u0016e\u0016\u001cx\u000e\u001c<f\u001fZ,'\u000f\\8bI\u0016$g\t\\1h)\rY\u0018q\u0003\u0005\u0006c:\u0001\r\u0001W\u0001\u0010Q\u0006\u001c\u0018IY:ue\u0006\u001cGO\u00127bO\u0006y\u0001.Y:BG\u000e,7o]8s\r2\fw-\u0001\u0006iCN$UMZ1vYR\fq\u0002[1t\u0015\u00064\u0018-\u00128v[\u001ac\u0017mZ\u0001\u0016Q\u0006\u001c(*\u0019<b\u0003:tw\u000e^1uS>tg\t\\1h\u00031A\u0017m\u001d'pG\u0006dg\t\\1hQ-!\u0012qEA\u0017\u0003_\t\u0019$!\u000e\u0011\u0007\u0015\u000bI#C\u0002\u0002,\u0001\u0013!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f#!!\r\u00023U\u001cX\rI5t\u0019>\u001c\u0017\r\u001c+p)\"L7\u000fI5ogR,\u0017\rZ\u0001\u0006g&t7-Z\u0011\u0003\u0003o\taA\r\u00182c9\u0002\u0014!D5t\u0019>\u001c\u0017\r\u001c+p)\"L7/A\u0007iCNlu\u000eZ;mK\u001ac\u0017mZ\u0001\u000fQ\u0006\u001c\b+Y2lC\u001e,g\t\\1h\u00035A\u0017m]*uC\ndWM\u00127bO\u0006i\u0001.Y:Ti\u0006$\u0018n\u0019$mC\u001e\f!#[:BEN$(/Y2u\u001fZ,'O]5eK\u0006i\u0011n]!os>3XM\u001d:jI\u0016\fa![:DCN,\u0017AD5t\u0007\u0006\u001cX-Q2dKN\u001cxN]\u0001\u000bSN$UMZ3se\u0016$\u0017aB5t\r&t\u0017\r\\\u0001\u000bSN\f%\u000f^5gC\u000e$\u0018AC5t\u00136\u0004H.[2ji\u0006Y\u0011n]%oi\u0016\u0014h-Y2f\u00035I7OS1wC\u0012+g-\u001b8fI\u00069\u0011n\u001d'bE\u0016d\u0017AB5t\u0019\u0006T\u00180\u0001\u0005jg2Kg\r^3e\u0003\u001dI7/T1de>\f\u0011\"[:NkR\f'\r\\3\u0002\u0015%\u001cxJ^3se&$W-A\bjgB\u000b'/Y7BG\u000e,7o]8s\u0003%I7\u000f\u0015:jm\u0006$X-A\u0005jgB\u000b7m[1hK\"ZA&a\n\u0002.\u0005-\u00141GA\u001bC\t\ti'\u0001\u000fvg\u0016\u0004\u0003\r[1t!\u0006\u001c7.Y4f\r2\fw\r\u0019\u0011j]N$X-\u00193\u0002\u001d%\u001c\bK]5wCR,Gj\\2bY\u0006Y\u0011n\u001d)s_R,7\r^3e\u0003AI7\u000f\u0015:pi\u0016\u001cG/\u001a3M_\u000e\fG.\u0001\u0005jgB+(\r\\5d\u0003!I7oU3bY\u0016$\u0017!D5t'B,7-[1mSj,G-A\bjgN+\b/\u001a:BG\u000e,7o]8s\u0003-I7oU=oi\",G/[2\u0002\u000f%\u001cHK]1ji\u0006\u0011\u0012n\u001d+sC&$xJ]%oi\u0016\u0014h-Y2f\u0003A1G.Y4CSR\u001cHk\\*ue&tw\rF\u0002|\u0003\u000fCa!!#8\u0001\u0004A\u0016\u0001\u00022jiN\fA\"Y2dKN\u001c8\u000b\u001e:j]\u001e\f1cY1mGVd\u0017\r^3GY\u0006<7\u000b\u001e:j]\u001e$2a_AI\u0011\u0019\t\u0019*\u000fa\u00011\u0006)!-Y:jg\u0006Y\u0011n\u001d)be\u0006lW\r^3s\u0001"
)
public interface HasFlags {
   long flags();

   Object privateWithin();

   List annotations();

   boolean hasAccessBoundary();

   boolean hasFlag(final long flag);

   boolean hasAllFlags(final long mask);

   // $FF: synthetic method
   static boolean hasNoFlags$(final HasFlags $this, final long mask) {
      return $this.hasNoFlags(mask);
   }

   default boolean hasNoFlags(final long mask) {
      return !this.hasFlag(mask);
   }

   // $FF: synthetic method
   static String flagString$(final HasFlags $this) {
      return $this.flagString();
   }

   default String flagString() {
      return this.flagString(this.flagMask());
   }

   // $FF: synthetic method
   static String flagString$(final HasFlags $this, final long mask) {
      return $this.flagString(mask);
   }

   default String flagString(final long mask) {
      return this.calculateFlagString(this.flags() & mask);
   }

   // $FF: synthetic method
   static long flagMask$(final HasFlags $this) {
      return $this.flagMask();
   }

   default long flagMask() {
      return -1L;
   }

   // $FF: synthetic method
   static String resolveOverloadedFlag$(final HasFlags $this, final long flag) {
      return $this.resolveOverloadedFlag(flag);
   }

   default String resolveOverloadedFlag(final long flag) {
      return Flags$.MODULE$.flagToString(flag);
   }

   // $FF: synthetic method
   static boolean hasAbstractFlag$(final HasFlags $this) {
      return $this.hasAbstractFlag();
   }

   default boolean hasAbstractFlag() {
      return this.hasFlag(8L);
   }

   // $FF: synthetic method
   static boolean hasAccessorFlag$(final HasFlags $this) {
      return $this.hasAccessorFlag();
   }

   default boolean hasAccessorFlag() {
      return this.hasFlag(134217728L);
   }

   // $FF: synthetic method
   static boolean hasDefault$(final HasFlags $this) {
      return $this.hasDefault();
   }

   default boolean hasDefault() {
      return this.hasFlag(33554432L) && this.hasFlag(8256L);
   }

   // $FF: synthetic method
   static boolean hasJavaEnumFlag$(final HasFlags $this) {
      return $this.hasJavaEnumFlag();
   }

   default boolean hasJavaEnumFlag() {
      return this.hasFlag(281474976710656L);
   }

   // $FF: synthetic method
   static boolean hasJavaAnnotationFlag$(final HasFlags $this) {
      return $this.hasJavaAnnotationFlag();
   }

   default boolean hasJavaAnnotationFlag() {
      return this.hasFlag(562949953421312L);
   }

   // $FF: synthetic method
   static boolean hasLocalFlag$(final HasFlags $this) {
      return $this.hasLocalFlag();
   }

   /** @deprecated */
   default boolean hasLocalFlag() {
      return this.hasFlag(524288L);
   }

   // $FF: synthetic method
   static boolean isLocalToThis$(final HasFlags $this) {
      return $this.isLocalToThis();
   }

   default boolean isLocalToThis() {
      return this.hasFlag(524288L);
   }

   // $FF: synthetic method
   static boolean hasModuleFlag$(final HasFlags $this) {
      return $this.hasModuleFlag();
   }

   default boolean hasModuleFlag() {
      return this.hasFlag(256L);
   }

   // $FF: synthetic method
   static boolean hasPackageFlag$(final HasFlags $this) {
      return $this.hasPackageFlag();
   }

   default boolean hasPackageFlag() {
      return this.hasFlag(16384L);
   }

   // $FF: synthetic method
   static boolean hasStableFlag$(final HasFlags $this) {
      return $this.hasStableFlag();
   }

   default boolean hasStableFlag() {
      return this.hasFlag(4194304L);
   }

   // $FF: synthetic method
   static boolean hasStaticFlag$(final HasFlags $this) {
      return $this.hasStaticFlag();
   }

   default boolean hasStaticFlag() {
      return this.hasFlag(8388608L);
   }

   // $FF: synthetic method
   static boolean isAbstractOverride$(final HasFlags $this) {
      return $this.isAbstractOverride();
   }

   default boolean isAbstractOverride() {
      return this.hasFlag(262144L);
   }

   // $FF: synthetic method
   static boolean isAnyOverride$(final HasFlags $this) {
      return $this.isAnyOverride();
   }

   default boolean isAnyOverride() {
      return this.hasFlag(262146L);
   }

   // $FF: synthetic method
   static boolean isCase$(final HasFlags $this) {
      return $this.isCase();
   }

   default boolean isCase() {
      return this.hasFlag(2048L);
   }

   // $FF: synthetic method
   static boolean isCaseAccessor$(final HasFlags $this) {
      return $this.isCaseAccessor();
   }

   default boolean isCaseAccessor() {
      return this.hasFlag(16777216L);
   }

   // $FF: synthetic method
   static boolean isDeferred$(final HasFlags $this) {
      return $this.isDeferred();
   }

   default boolean isDeferred() {
      return this.hasFlag(16L);
   }

   // $FF: synthetic method
   static boolean isFinal$(final HasFlags $this) {
      return $this.isFinal();
   }

   default boolean isFinal() {
      return this.hasFlag(32L);
   }

   // $FF: synthetic method
   static boolean isArtifact$(final HasFlags $this) {
      return $this.isArtifact();
   }

   default boolean isArtifact() {
      return this.hasFlag(70368744177664L);
   }

   // $FF: synthetic method
   static boolean isImplicit$(final HasFlags $this) {
      return $this.isImplicit();
   }

   default boolean isImplicit() {
      return this.hasFlag(512L);
   }

   // $FF: synthetic method
   static boolean isInterface$(final HasFlags $this) {
      return $this.isInterface();
   }

   default boolean isInterface() {
      return this.hasFlag(128L);
   }

   // $FF: synthetic method
   static boolean isJavaDefined$(final HasFlags $this) {
      return $this.isJavaDefined();
   }

   default boolean isJavaDefined() {
      return this.hasFlag(1048576L);
   }

   // $FF: synthetic method
   static boolean isLabel$(final HasFlags $this) {
      return $this.isLabel();
   }

   default boolean isLabel() {
      return this.hasAllFlags(131136L) && !this.hasAccessorFlag();
   }

   // $FF: synthetic method
   static boolean isLazy$(final HasFlags $this) {
      return $this.isLazy();
   }

   default boolean isLazy() {
      return this.hasFlag(2147483648L);
   }

   // $FF: synthetic method
   static boolean isLifted$(final HasFlags $this) {
      return $this.isLifted();
   }

   default boolean isLifted() {
      return this.hasFlag(17179869184L);
   }

   // $FF: synthetic method
   static boolean isMacro$(final HasFlags $this) {
      return $this.isMacro();
   }

   default boolean isMacro() {
      return this.hasFlag(32768L);
   }

   // $FF: synthetic method
   static boolean isMutable$(final HasFlags $this) {
      return $this.isMutable();
   }

   default boolean isMutable() {
      return this.hasFlag(4096L);
   }

   // $FF: synthetic method
   static boolean isOverride$(final HasFlags $this) {
      return $this.isOverride();
   }

   default boolean isOverride() {
      return this.hasFlag(2L);
   }

   // $FF: synthetic method
   static boolean isParamAccessor$(final HasFlags $this) {
      return $this.isParamAccessor();
   }

   default boolean isParamAccessor() {
      return this.hasFlag(536870912L);
   }

   // $FF: synthetic method
   static boolean isPrivate$(final HasFlags $this) {
      return $this.isPrivate();
   }

   default boolean isPrivate() {
      return this.hasFlag(4L);
   }

   // $FF: synthetic method
   static boolean isPackage$(final HasFlags $this) {
      return $this.isPackage();
   }

   /** @deprecated */
   default boolean isPackage() {
      return this.hasFlag(16384L);
   }

   // $FF: synthetic method
   static boolean isPrivateLocal$(final HasFlags $this) {
      return $this.isPrivateLocal();
   }

   default boolean isPrivateLocal() {
      return this.hasAllFlags(524292L);
   }

   // $FF: synthetic method
   static boolean isProtected$(final HasFlags $this) {
      return $this.isProtected();
   }

   default boolean isProtected() {
      return this.hasFlag(1L);
   }

   // $FF: synthetic method
   static boolean isProtectedLocal$(final HasFlags $this) {
      return $this.isProtectedLocal();
   }

   default boolean isProtectedLocal() {
      return this.hasAllFlags(524289L);
   }

   // $FF: synthetic method
   static boolean isPublic$(final HasFlags $this) {
      return $this.isPublic();
   }

   default boolean isPublic() {
      return this.hasNoFlags(5L) && !this.hasAccessBoundary();
   }

   // $FF: synthetic method
   static boolean isSealed$(final HasFlags $this) {
      return $this.isSealed();
   }

   default boolean isSealed() {
      return this.hasFlag(1024L);
   }

   // $FF: synthetic method
   static boolean isSpecialized$(final HasFlags $this) {
      return $this.isSpecialized();
   }

   default boolean isSpecialized() {
      return this.hasFlag(1099511627776L);
   }

   // $FF: synthetic method
   static boolean isSuperAccessor$(final HasFlags $this) {
      return $this.isSuperAccessor();
   }

   default boolean isSuperAccessor() {
      return this.hasFlag(268435456L);
   }

   // $FF: synthetic method
   static boolean isSynthetic$(final HasFlags $this) {
      return $this.isSynthetic();
   }

   default boolean isSynthetic() {
      return this.hasFlag(2097152L);
   }

   // $FF: synthetic method
   static boolean isTrait$(final HasFlags $this) {
      return $this.isTrait();
   }

   default boolean isTrait() {
      return this.hasFlag(33554432L) && !this.hasFlag(8192L);
   }

   // $FF: synthetic method
   static boolean isTraitOrInterface$(final HasFlags $this) {
      return $this.isTraitOrInterface();
   }

   default boolean isTraitOrInterface() {
      return this.isTrait() || this.isInterface();
   }

   // $FF: synthetic method
   static String flagBitsToString$(final HasFlags $this, final long bits) {
      return $this.flagBitsToString(bits);
   }

   default String flagBitsToString(final long bits) {
      if (bits == 0L) {
         return "";
      } else {
         StringBuilder sb = null;

         for(int i = 0; i <= 62; ++i) {
            long flag = Flags$.MODULE$.rawFlagPickledOrder()[i];
            if ((bits & flag) != 0L) {
               String s = Flags$.MODULE$.flagToString(flag);
               if (s.length() > 0) {
                  if (sb == null) {
                     sb = new StringBuilder();
                  } else if (!sb.isEmpty()) {
                     sb.append(" ");
                  }

                  sb.append(s);
               }
            }
         }

         if (sb == null) {
            return "";
         } else {
            return sb.toString();
         }
      }
   }

   // $FF: synthetic method
   static String accessString$(final HasFlags $this) {
      return $this.accessString();
   }

   default String accessString() {
      String pw = this.hasAccessBoundary() ? this.privateWithin().toString() : "";
      String var2 = "";
      if (pw != null) {
         if (pw.equals(var2)) {
            if (this.hasAllFlags(524292L)) {
               return "private[this]";
            }

            if (this.hasAllFlags(524289L)) {
               return "protected[this]";
            }

            if (this.hasFlag(4L)) {
               return "private";
            }

            if (this.hasFlag(1L)) {
               return "protected";
            }

            return "";
         }
      }

      return this.hasFlag(1L) ? (new java.lang.StringBuilder(11)).append("protected[").append(pw).append("]").toString() : (new java.lang.StringBuilder(9)).append("private[").append(pw).append("]").toString();
   }

   // $FF: synthetic method
   static String calculateFlagString$(final HasFlags $this, final long basis) {
      return $this.calculateFlagString(basis);
   }

   default String calculateFlagString(final long basis) {
      String access = this.accessString();
      String nonAccess = this.flagBitsToString(basis & -524294L);
      String var5 = "";
      if (access != null) {
         if (access.equals(var5)) {
            return nonAccess;
         }
      }

      String var6 = "";
      if (nonAccess != null) {
         if (nonAccess.equals(var6)) {
            return access;
         }
      }

      return (new java.lang.StringBuilder(1)).append(nonAccess).append(" ").append(access).toString();
   }

   // $FF: synthetic method
   static boolean isParameter$(final HasFlags $this) {
      return $this.isParameter();
   }

   default boolean isParameter() {
      return this.hasFlag(8192L);
   }

   static void $init$(final HasFlags $this) {
   }
}
