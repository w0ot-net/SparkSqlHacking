package com.fasterxml.jackson.module.scala.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.SimpleType;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dh\u0001\u0002\u000f\u001e\t*B\u0001\u0002\u0012\u0001\u0003\u0016\u0004%\t!\u0012\u0005\t\r\u0002\u0011\t\u0012)A\u0005W!Aq\t\u0001BK\u0002\u0013\u0005\u0001\n\u0003\u0005N\u0001\tE\t\u0015!\u0003J\u0011\u0015q\u0005\u0001\"\u0001P\u0011\u0015\u0019\u0006\u0001\"\u0011U\u0011\u0015I\u0006\u0001\"\u0003[\u0011\u0015I\b\u0001\"\u0003{\u0011\u001d\ti\u0001\u0001C\u0005\u0003\u001fA\u0011\"a\n\u0001\u0003\u0003%\t!!\u000b\t\u0013\u0005=\u0002!%A\u0005\u0002\u0005E\u0002\"CA$\u0001E\u0005I\u0011AA%\u0011%\ti\u0005AA\u0001\n\u0003\ny\u0005C\u0005\u0002b\u0001\t\t\u0011\"\u0001\u0002d!I\u00111\u000e\u0001\u0002\u0002\u0013\u0005\u0011Q\u000e\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003kB\u0011\"a!\u0001\u0003\u0003%\t!!\"\t\u0013\u0005=\u0005!!A\u0005B\u0005E\u0005\"CAK\u0001\u0005\u0005I\u0011IAL\u0011%\tI\nAA\u0001\n\u0003\nYjB\u0005\u0002 v\t\t\u0011#\u0003\u0002\"\u001aAA$HA\u0001\u0012\u0013\t\u0019\u000b\u0003\u0004O-\u0011\u0005\u00111\u0018\u0005\n\u0003{3\u0012\u0011!C#\u0003\u007fC\u0011\"!1\u0017\u0003\u0003%\t)a1\t\u0013\u0005%g#!A\u0005\u0002\u0006-\u0007\"CAo-\u0005\u0005I\u0011BAp\u0005Y9&/\u00199qK\u0012\u001c%/Z1u_J\u0004&o\u001c9feRL(B\u0001\u0010 \u0003)Ig\u000e\u001e:pgB,7\r\u001e\u0006\u0003A\u0005\nQa]2bY\u0006T!AI\u0012\u0002\r5|G-\u001e7f\u0015\t!S%A\u0004kC\u000e\\7o\u001c8\u000b\u0005\u0019:\u0013!\u00034bgR,'\u000f_7m\u0015\u0005A\u0013aA2p[\u000e\u00011\u0003\u0002\u0001,ga\u0002\"\u0001L\u0019\u000e\u00035R!AL\u0018\u0002\u000b\u0011,7/\u001a:\u000b\u0005A\u001a\u0013\u0001\u00033bi\u0006\u0014\u0017N\u001c3\n\u0005Ij#aD\"sK\u0006$xN\u001d)s_B,'\u000f^=\u0011\u0005Q2T\"A\u001b\u000b\u0003\u0001J!aN\u001b\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011(\u0011\b\u0003u}r!a\u000f \u000e\u0003qR!!P\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0013B\u0001!6\u0003\u001d\u0001\u0018mY6bO\u0016L!AQ\"\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u0001+\u0014aD2sK\u0006$xN\u001d)s_B,'\u000f^=\u0016\u0003-\n\u0001c\u0019:fCR|'\u000f\u0015:pa\u0016\u0014H/\u001f\u0011\u0002\u0013I,g\rS8mI\u0016\u0014X#A%\u0011\u0005)[U\"A\u000f\n\u00051k\"aC\"mCN\u001c\bj\u001c7eKJ\f!B]3g\u0011>dG-\u001a:!\u0003\u0019a\u0014N\\5u}Q\u0019\u0001+\u0015*\u0011\u0005)\u0003\u0001\"\u0002#\u0006\u0001\u0004Y\u0003\"B$\u0006\u0001\u0004I\u0015aB4fiRK\b/\u001a\u000b\u0002+B\u0011akV\u0007\u0002_%\u0011\u0001l\f\u0002\t\u0015\u00064\u0018\rV=qK\u0006\u0019R\u000f\u001d3bi\u0016\u0014VMZ3sK:\u001cW\rV=qKR\u00191,Y2\u0011\u0005q{V\"A/\u000b\u0005y{\u0013\u0001\u0002;za\u0016L!\u0001Y/\u0003\u001bI+g-\u001a:f]\u000e,G+\u001f9f\u0011\u0015\u0011w\u00011\u0001\\\u0003\t\u0011H\u000fC\u0003e\u000f\u0001\u0007Q-A\u0006oK^\u0014VMZ\"mCN\u001c\bG\u00014q!\r97N\u001c\b\u0003Q&\u0004\"aO\u001b\n\u0005),\u0014A\u0002)sK\u0012,g-\u0003\u0002m[\n)1\t\\1tg*\u0011!.\u000e\t\u0003_Bd\u0001\u0001B\u0005rG\u0006\u0005\t\u0011!B\u0001e\n\u0019q\fJ\u001d\u0012\u0005M4\bC\u0001\u001bu\u0013\t)XGA\u0004O_RD\u0017N\\4\u0011\u0005Q:\u0018B\u0001=6\u0005\r\te._\u0001\u0015kB$\u0017\r^3D_2dWm\u0019;j_:$\u0016\u0010]3\u0015\tmt\u0018\u0011\u0001\t\u00039rL!!`/\u0003%\r{G\u000e\\3di&|g\u000eT5lKRK\b/\u001a\u0005\u0006\u007f\"\u0001\ra_\u0001\u0003GRDa\u0001\u001a\u0005A\u0002\u0005\r\u0001\u0007BA\u0003\u0003\u0013\u0001BaZ6\u0002\bA\u0019q.!\u0003\u0005\u0017\u0005-\u0011\u0011AA\u0001\u0002\u0003\u0015\tA\u001d\u0002\u0005?\u0012\n\u0004'A\u0007va\u0012\fG/Z'baRK\b/\u001a\u000b\u0007\u0003#\t9\"a\u0007\u0011\u0007q\u000b\u0019\"C\u0002\u0002\u0016u\u00131\"T1q\u0019&\\W\rV=qK\"9\u0011\u0011D\u0005A\u0002\u0005E\u0011AA7u\u0011\u0019!\u0017\u00021\u0001\u0002\u001eA\"\u0011qDA\u0012!\u001197.!\t\u0011\u0007=\f\u0019\u0003B\u0006\u0002&\u0005m\u0011\u0011!A\u0001\u0006\u0003\u0011(\u0001B0%cE\nAaY8qsR)\u0001+a\u000b\u0002.!9AI\u0003I\u0001\u0002\u0004Y\u0003bB$\u000b!\u0003\u0005\r!S\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019DK\u0002,\u0003kY#!a\u000e\u0011\t\u0005e\u00121I\u0007\u0003\u0003wQA!!\u0010\u0002@\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0003*\u0014AC1o]>$\u0018\r^5p]&!\u0011QIA\u001e\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tYEK\u0002J\u0003k\tQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA)!\u0011\t\u0019&!\u0018\u000e\u0005\u0005U#\u0002BA,\u00033\nA\u0001\\1oO*\u0011\u00111L\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002`\u0005U#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002fA\u0019A'a\u001a\n\u0007\u0005%TGA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002w\u0003_B\u0011\"!\u001d\u0010\u0003\u0003\u0005\r!!\u001a\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t9\bE\u0003\u0002z\u0005}d/\u0004\u0002\u0002|)\u0019\u0011QP\u001b\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0002\u0006m$\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\"\u0002\u000eB\u0019A'!#\n\u0007\u0005-UGA\u0004C_>dW-\u00198\t\u0011\u0005E\u0014#!AA\u0002Y\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011KAJ\u0011%\t\tHEA\u0001\u0002\u0004\t)'\u0001\u0005iCND7i\u001c3f)\t\t)'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u000f\u000bi\n\u0003\u0005\u0002rQ\t\t\u00111\u0001w\u0003Y9&/\u00199qK\u0012\u001c%/Z1u_J\u0004&o\u001c9feRL\bC\u0001&\u0017'\u00151\u0012QUAY!\u001d\t9+!,,\u0013Bk!!!+\u000b\u0007\u0005-V'A\u0004sk:$\u0018.\\3\n\t\u0005=\u0016\u0011\u0016\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BAZ\u0003sk!!!.\u000b\t\u0005]\u0016\u0011L\u0001\u0003S>L1AQA[)\t\t\t+\u0001\u0005u_N#(/\u001b8h)\t\t\t&A\u0003baBd\u0017\u0010F\u0003Q\u0003\u000b\f9\rC\u0003E3\u0001\u00071\u0006C\u0003H3\u0001\u0007\u0011*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u00055\u0017\u0011\u001c\t\u0006i\u0005=\u00171[\u0005\u0004\u0003#,$AB(qi&|g\u000eE\u00035\u0003+\\\u0013*C\u0002\u0002XV\u0012a\u0001V;qY\u0016\u0014\u0004\u0002CAn5\u0005\u0005\t\u0019\u0001)\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002bB!\u00111KAr\u0013\u0011\t)/!\u0016\u0003\r=\u0013'.Z2u\u0001"
)
public class WrappedCreatorProperty extends CreatorProperty implements Product {
   private final CreatorProperty creatorProperty;
   private final ClassHolder refHolder;

   public static Option unapply(final WrappedCreatorProperty x$0) {
      return WrappedCreatorProperty$.MODULE$.unapply(x$0);
   }

   public static WrappedCreatorProperty apply(final CreatorProperty creatorProperty, final ClassHolder refHolder) {
      return WrappedCreatorProperty$.MODULE$.apply(creatorProperty, refHolder);
   }

   public static Function1 tupled() {
      return WrappedCreatorProperty$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WrappedCreatorProperty$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public CreatorProperty creatorProperty() {
      return this.creatorProperty;
   }

   public ClassHolder refHolder() {
      return this.refHolder;
   }

   public JavaType getType() {
      JavaType var2 = super.getType();
      if (var2 instanceof ReferenceType) {
         ReferenceType var3 = (ReferenceType)var2;
         if (this.refHolder().valueClass().isDefined()) {
            return this.updateReferenceType(var3, (Class)this.refHolder().valueClass().get());
         }
      }

      if (var2 instanceof CollectionLikeType) {
         CollectionLikeType var4 = (CollectionLikeType)var2;
         if (this.refHolder().valueClass().isDefined()) {
            return this.updateCollectionType(var4, (Class)this.refHolder().valueClass().get());
         }
      }

      if (var2 instanceof MapLikeType) {
         MapLikeType var5 = (MapLikeType)var2;
         if (this.refHolder().valueClass().isDefined()) {
            return this.updateMapType(var5, (Class)this.refHolder().valueClass().get());
         }
      }

      return var2;
   }

   private ReferenceType updateReferenceType(final ReferenceType rt, final Class newRefClass) {
      JavaType var4 = rt.getContentType();
      if (var4 instanceof ReferenceType) {
         ReferenceType var5 = (ReferenceType)var4;
         return ReferenceType.upgradeFrom(rt, this.updateReferenceType(var5, newRefClass));
      } else if (var4 instanceof CollectionLikeType) {
         CollectionLikeType var6 = (CollectionLikeType)var4;
         return ReferenceType.upgradeFrom(rt, this.updateCollectionType(var6, newRefClass));
      } else if (var4 instanceof MapLikeType) {
         MapLikeType var7 = (MapLikeType)var4;
         return ReferenceType.upgradeFrom(rt, this.updateMapType(var7, newRefClass));
      } else {
         return ReferenceType.upgradeFrom(rt, SimpleType.constructUnsafe(newRefClass));
      }
   }

   private CollectionLikeType updateCollectionType(final CollectionLikeType ct, final Class newRefClass) {
      JavaType var4 = ct.getContentType();
      if (var4 instanceof ReferenceType) {
         ReferenceType var5 = (ReferenceType)var4;
         return CollectionLikeType.upgradeFrom(ct, this.updateReferenceType(var5, newRefClass));
      } else if (var4 instanceof CollectionLikeType) {
         CollectionLikeType var6 = (CollectionLikeType)var4;
         return CollectionLikeType.upgradeFrom(ct, this.updateCollectionType(var6, newRefClass));
      } else if (var4 instanceof MapLikeType) {
         MapLikeType var7 = (MapLikeType)var4;
         return CollectionLikeType.upgradeFrom(ct, this.updateMapType(var7, newRefClass));
      } else {
         return CollectionLikeType.upgradeFrom(ct, SimpleType.constructUnsafe(newRefClass));
      }
   }

   private MapLikeType updateMapType(final MapLikeType mt, final Class newRefClass) {
      JavaType var4 = mt.getContentType();
      if (var4 instanceof ReferenceType) {
         ReferenceType var5 = (ReferenceType)var4;
         return MapLikeType.upgradeFrom(mt, mt.getKeyType(), this.updateReferenceType(var5, newRefClass));
      } else if (var4 instanceof CollectionLikeType) {
         CollectionLikeType var6 = (CollectionLikeType)var4;
         return MapLikeType.upgradeFrom(mt, mt.getKeyType(), this.updateCollectionType(var6, newRefClass));
      } else if (var4 instanceof MapLikeType) {
         MapLikeType var7 = (MapLikeType)var4;
         return MapLikeType.upgradeFrom(mt, mt.getKeyType(), this.updateMapType(var7, newRefClass));
      } else {
         return MapLikeType.upgradeFrom(mt, mt.getKeyType(), SimpleType.constructUnsafe(newRefClass));
      }
   }

   public WrappedCreatorProperty copy(final CreatorProperty creatorProperty, final ClassHolder refHolder) {
      return new WrappedCreatorProperty(creatorProperty, refHolder);
   }

   public CreatorProperty copy$default$1() {
      return this.creatorProperty();
   }

   public ClassHolder copy$default$2() {
      return this.refHolder();
   }

   public String productPrefix() {
      return "WrappedCreatorProperty";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return this.creatorProperty();
         case 1:
            return this.refHolder();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof WrappedCreatorProperty;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "creatorProperty";
         case 1:
            return "refHolder";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof WrappedCreatorProperty) {
               label48: {
                  WrappedCreatorProperty var4 = (WrappedCreatorProperty)x$1;
                  CreatorProperty var10000 = this.creatorProperty();
                  CreatorProperty var5 = var4.creatorProperty();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label48;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label48;
                  }

                  ClassHolder var7 = this.refHolder();
                  ClassHolder var6 = var4.refHolder();
                  if (var7 == null) {
                     if (var6 != null) {
                        break label48;
                     }
                  } else if (!var7.equals(var6)) {
                     break label48;
                  }

                  if (var4.canEqual(this)) {
                     break label55;
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

   public WrappedCreatorProperty(final CreatorProperty creatorProperty, final ClassHolder refHolder) {
      super(creatorProperty, creatorProperty.getFullName());
      this.creatorProperty = creatorProperty;
      this.refHolder = refHolder;
      Product.$init$(this);
   }
}
