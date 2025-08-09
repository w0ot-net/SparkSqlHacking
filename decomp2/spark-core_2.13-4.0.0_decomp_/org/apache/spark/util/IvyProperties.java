package org.apache.spark.util;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=e!\u0002\u0012$\u0001\u0016Z\u0003\u0002\u0003\"\u0001\u0005+\u0007I\u0011A\"\t\u00111\u0003!\u0011#Q\u0001\n\u0011C\u0001\"\u0014\u0001\u0003\u0016\u0004%\ta\u0011\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\t\"Aq\n\u0001BK\u0002\u0013\u00051\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003E\u0011!\t\u0006A!f\u0001\n\u0003\u0019\u0005\u0002\u0003*\u0001\u0005#\u0005\u000b\u0011\u0002#\t\u0011M\u0003!Q3A\u0005\u0002\rC\u0001\u0002\u0016\u0001\u0003\u0012\u0003\u0006I\u0001\u0012\u0005\u0006+\u0002!\tA\u0016\u0005\b=\u0002\t\t\u0011\"\u0001`\u0011\u001d)\u0007!%A\u0005\u0002\u0019Dq!\u001d\u0001\u0012\u0002\u0013\u0005a\rC\u0004s\u0001E\u0005I\u0011\u00014\t\u000fM\u0004\u0011\u0013!C\u0001M\"9A\u000fAI\u0001\n\u00031\u0007bB;\u0001\u0003\u0003%\tE\u001e\u0005\b}\u0002\t\t\u0011\"\u0001\u0000\u0011%\t9\u0001AA\u0001\n\u0003\tI\u0001C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018!I\u0011Q\u0005\u0001\u0002\u0002\u0013\u0005\u0011q\u0005\u0005\n\u0003c\u0001\u0011\u0011!C!\u0003gA\u0011\"a\u000e\u0001\u0003\u0003%\t%!\u000f\t\u0013\u0005m\u0002!!A\u0005B\u0005u\u0002\"CA \u0001\u0005\u0005I\u0011IA!\u000f)\t)eIA\u0001\u0012\u0003)\u0013q\t\u0004\nE\r\n\t\u0011#\u0001&\u0003\u0013Ba!\u0016\u000f\u0005\u0002\u0005\u0005\u0004\"CA\u001e9\u0005\u0005IQIA\u001f\u0011%\t\u0019\u0007HA\u0001\n\u0003\u000b)\u0007C\u0005\u0002rq\t\t\u0011\"!\u0002t!I\u0011Q\u0011\u000f\u0002\u0002\u0013%\u0011q\u0011\u0002\u000e\u0013ZL\bK]8qKJ$\u0018.Z:\u000b\u0005\u0011*\u0013\u0001B;uS2T!AJ\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005!J\u0013AB1qC\u000eDWMC\u0001+\u0003\ry'oZ\n\u0005\u00011\u0012T\u0007\u0005\u0002.a5\taFC\u00010\u0003\u0015\u00198-\u00197b\u0013\t\tdF\u0001\u0004B]f\u0014VM\u001a\t\u0003[MJ!\u0001\u000e\u0018\u0003\u000fA\u0013x\u000eZ;diB\u0011ag\u0010\b\u0003our!\u0001\u000f\u001f\u000e\u0003eR!AO\u001e\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aL\u0005\u0003}9\nq\u0001]1dW\u0006<W-\u0003\u0002A\u0003\na1+\u001a:jC2L'0\u00192mK*\u0011aHL\u0001\u0013a\u0006\u001c7.Y4fg\u0016C8\r\\;tS>t7/F\u0001E!\t)\u0015J\u0004\u0002G\u000fB\u0011\u0001HL\u0005\u0003\u0011:\na\u0001\u0015:fI\u00164\u0017B\u0001&L\u0005\u0019\u0019FO]5oO*\u0011\u0001JL\u0001\u0014a\u0006\u001c7.Y4fg\u0016C8\r\\;tS>t7\u000fI\u0001\ta\u0006\u001c7.Y4fg\u0006I\u0001/Y2lC\u001e,7\u000fI\u0001\re\u0016\u0004xn]5u_JLWm]\u0001\u000ee\u0016\u0004xn]5u_JLWm\u001d\u0011\u0002\u0017%4\u0018PU3q_B\u000bG\u000f[\u0001\rSZL(+\u001a9p!\u0006$\b\u000eI\u0001\u0010SZL8+\u001a;uS:<7\u000fU1uQ\u0006\u0001\u0012N^=TKR$\u0018N\\4t!\u0006$\b\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\r]K&l\u0017/^!\tA\u0006!D\u0001$\u0011\u0015\u00115\u00021\u0001E\u0011\u0015i5\u00021\u0001E\u0011\u0015y5\u00021\u0001E\u0011\u0015\t6\u00021\u0001E\u0011\u0015\u00196\u00021\u0001E\u0003\u0011\u0019w\u000e]=\u0015\r]\u0003\u0017MY2e\u0011\u001d\u0011E\u0002%AA\u0002\u0011Cq!\u0014\u0007\u0011\u0002\u0003\u0007A\tC\u0004P\u0019A\u0005\t\u0019\u0001#\t\u000fEc\u0001\u0013!a\u0001\t\"91\u000b\u0004I\u0001\u0002\u0004!\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002O*\u0012A\t[\u0016\u0002SB\u0011!n\\\u0007\u0002W*\u0011A.\\\u0001\nk:\u001c\u0007.Z2lK\u0012T!A\u001c\u0018\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002qW\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000f\u0005\u0002y{6\t\u0011P\u0003\u0002{w\u0006!A.\u00198h\u0015\u0005a\u0018\u0001\u00026bm\u0006L!AS=\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005\u0005\u0001cA\u0017\u0002\u0004%\u0019\u0011Q\u0001\u0018\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005-\u0011\u0011\u0003\t\u0004[\u00055\u0011bAA\b]\t\u0019\u0011I\\=\t\u0013\u0005MA#!AA\u0002\u0005\u0005\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u001aA1\u00111DA\u0011\u0003\u0017i!!!\b\u000b\u0007\u0005}a&\u0001\u0006d_2dWm\u0019;j_:LA!a\t\u0002\u001e\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tI#a\f\u0011\u00075\nY#C\u0002\u0002.9\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\u0014Y\t\t\u00111\u0001\u0002\f\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r9\u0018Q\u0007\u0005\n\u0003'9\u0012\u0011!a\u0001\u0003\u0003\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0003\t\u0001\u0002^8TiJLgn\u001a\u000b\u0002o\u00061Q-];bYN$B!!\u000b\u0002D!I\u00111\u0003\u000e\u0002\u0002\u0003\u0007\u00111B\u0001\u000e\u0013ZL\bK]8qKJ$\u0018.Z:\u0011\u0005ac2#\u0002\u000f\u0002L\u0005]\u0003CCA'\u0003'\"E\t\u0012#E/6\u0011\u0011q\n\u0006\u0004\u0003#r\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003+\nyEA\tBEN$(/Y2u\rVt7\r^5p]V\u0002B!!\u0017\u0002`5\u0011\u00111\f\u0006\u0004\u0003;Z\u0018AA5p\u0013\r\u0001\u00151\f\u000b\u0003\u0003\u000f\nQ!\u00199qYf$2bVA4\u0003S\nY'!\u001c\u0002p!)!i\ba\u0001\t\")Qj\ba\u0001\t\")qj\ba\u0001\t\")\u0011k\ba\u0001\t\")1k\ba\u0001\t\u00069QO\\1qa2LH\u0003BA;\u0003\u0003\u0003R!LA<\u0003wJ1!!\u001f/\u0005\u0019y\u0005\u000f^5p]BAQ&! E\t\u0012#E)C\u0002\u0002\u00009\u0012a\u0001V;qY\u0016,\u0004\u0002CABA\u0005\u0005\t\u0019A,\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\nB\u0019\u00010a#\n\u0007\u00055\u0015P\u0001\u0004PE*,7\r\u001e"
)
public class IvyProperties implements Product, Serializable {
   private final String packagesExclusions;
   private final String packages;
   private final String repositories;
   private final String ivyRepoPath;
   private final String ivySettingsPath;

   public static Option unapply(final IvyProperties x$0) {
      return IvyProperties$.MODULE$.unapply(x$0);
   }

   public static IvyProperties apply(final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final String ivySettingsPath) {
      return IvyProperties$.MODULE$.apply(packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
   }

   public static Function1 tupled() {
      return IvyProperties$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return IvyProperties$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String packagesExclusions() {
      return this.packagesExclusions;
   }

   public String packages() {
      return this.packages;
   }

   public String repositories() {
      return this.repositories;
   }

   public String ivyRepoPath() {
      return this.ivyRepoPath;
   }

   public String ivySettingsPath() {
      return this.ivySettingsPath;
   }

   public IvyProperties copy(final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final String ivySettingsPath) {
      return new IvyProperties(packagesExclusions, packages, repositories, ivyRepoPath, ivySettingsPath);
   }

   public String copy$default$1() {
      return this.packagesExclusions();
   }

   public String copy$default$2() {
      return this.packages();
   }

   public String copy$default$3() {
      return this.repositories();
   }

   public String copy$default$4() {
      return this.ivyRepoPath();
   }

   public String copy$default$5() {
      return this.ivySettingsPath();
   }

   public String productPrefix() {
      return "IvyProperties";
   }

   public int productArity() {
      return 5;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.packagesExclusions();
         }
         case 1 -> {
            return this.packages();
         }
         case 2 -> {
            return this.repositories();
         }
         case 3 -> {
            return this.ivyRepoPath();
         }
         case 4 -> {
            return this.ivySettingsPath();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof IvyProperties;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "packagesExclusions";
         }
         case 1 -> {
            return "packages";
         }
         case 2 -> {
            return "repositories";
         }
         case 3 -> {
            return "ivyRepoPath";
         }
         case 4 -> {
            return "ivySettingsPath";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var14;
      if (this != x$1) {
         label79: {
            if (x$1 instanceof IvyProperties) {
               label72: {
                  IvyProperties var4 = (IvyProperties)x$1;
                  String var10000 = this.packagesExclusions();
                  String var5 = var4.packagesExclusions();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label72;
                  }

                  var10000 = this.packages();
                  String var6 = var4.packages();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label72;
                  }

                  var10000 = this.repositories();
                  String var7 = var4.repositories();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label72;
                  }

                  var10000 = this.ivyRepoPath();
                  String var8 = var4.ivyRepoPath();
                  if (var10000 == null) {
                     if (var8 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var8)) {
                     break label72;
                  }

                  var10000 = this.ivySettingsPath();
                  String var9 = var4.ivySettingsPath();
                  if (var10000 == null) {
                     if (var9 != null) {
                        break label72;
                     }
                  } else if (!var10000.equals(var9)) {
                     break label72;
                  }

                  if (var4.canEqual(this)) {
                     break label79;
                  }
               }
            }

            var14 = false;
            return var14;
         }
      }

      var14 = true;
      return var14;
   }

   public IvyProperties(final String packagesExclusions, final String packages, final String repositories, final String ivyRepoPath, final String ivySettingsPath) {
      this.packagesExclusions = packagesExclusions;
      this.packages = packages;
      this.repositories = repositories;
      this.ivyRepoPath = ivyRepoPath;
      this.ivySettingsPath = ivySettingsPath;
      Product.$init$(this);
   }
}
