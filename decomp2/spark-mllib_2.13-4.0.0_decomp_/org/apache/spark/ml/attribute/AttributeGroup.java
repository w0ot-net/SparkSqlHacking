package org.apache.spark.ml.attribute;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.MetadataBuilder;
import org.apache.spark.sql.types.StructField;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee\u0001\u0002\u0012$\u00019B\u0001\"\u0011\u0001\u0003\u0006\u0004%\tA\u0011\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0007\"AA\n\u0001BC\u0002\u0013\u0005Q\n\u0003\u0005U\u0001\t\u0005\t\u0015!\u0003O\u0011!)\u0006A!A!\u0002\u00131\u0006\"\u00020\u0001\t\u0013y\u0006\"\u00020\u0001\t\u0003!\u0007\"\u00020\u0001\t\u00031\u0007\"\u00020\u0001\t\u0003I\u0007b\u00027\u0001\u0005\u0004%\t!\u001c\u0005\u0007]\u0002\u0001\u000b\u0011\u0002,\t\u0011=\u0004\u0001R1A\u0005\nADQ\u0001\u001e\u0001\u0005\u0002UDQA\u001e\u0001\u0005\u0002]DQ! \u0001\u0005\u0002yDq!!\u0001\u0001\t\u0003\t\u0019\u0001C\u0004\u0002\b\u0001!\t!!\u0003\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u000e!9\u0011q\u0001\u0001\u0005\u0002\u0005M\u0001\u0002CA\f\u0001\u0011\u00051%!\u0007\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.!9\u00111\u0006\u0001\u0005\u0002\u0005M\u0002bBA\u001b\u0001\u0011\u0005\u0011q\u0007\u0005\b\u0003k\u0001A\u0011AA!\u0011\u001d\t\u0019\u0005\u0001C!\u0003\u000bBq!!\u0015\u0001\t\u0003\n\u0019\u0006C\u0004\u0002V\u0001!\t%a\u0016\b\u000f\u0005e3\u0005#\u0001\u0002\\\u00191!e\tE\u0001\u0003;BaAX\u000f\u0005\u0002\u00055\u0004\u0002CA8;\u0011\u00051%!\u001d\t\u000f\u0005eT\u0004\"\u0001\u0002|!I\u0011\u0011Q\u000f\u0002\u0002\u0013%\u00111\u0011\u0002\u000f\u0003R$(/\u001b2vi\u0016<%o\\;q\u0015\t!S%A\u0005biR\u0014\u0018NY;uK*\u0011aeJ\u0001\u0003[2T!\u0001K\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005)Z\u0013AB1qC\u000eDWMC\u0001-\u0003\ry'oZ\u0002\u0001'\r\u0001q&\u000e\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YrdBA\u001c=\u001d\tA4(D\u0001:\u0015\tQT&\u0001\u0004=e>|GOP\u0005\u0002e%\u0011Q(M\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004I\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002>c\u0005!a.Y7f+\u0005\u0019\u0005C\u0001#I\u001d\t)e\t\u0005\u00029c%\u0011q)M\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002Hc\u0005)a.Y7fA\u0005ia.^7BiR\u0014\u0018NY;uKN,\u0012A\u0014\t\u0004a=\u000b\u0016B\u0001)2\u0005\u0019y\u0005\u000f^5p]B\u0011\u0001GU\u0005\u0003'F\u00121!\u00138u\u00039qW/\\!uiJL'-\u001e;fg\u0002\nQ!\u0019;ueN\u00042\u0001M(X!\r\u0001\u0004LW\u0005\u00033F\u0012Q!\u0011:sCf\u0004\"a\u0017/\u000e\u0003\rJ!!X\u0012\u0003\u0013\u0005#HO]5ckR,\u0017A\u0002\u001fj]&$h\b\u0006\u0003aC\n\u001c\u0007CA.\u0001\u0011\u0015\te\u00011\u0001D\u0011\u0015ae\u00011\u0001O\u0011\u0015)f\u00011\u0001W)\t\u0001W\rC\u0003B\u000f\u0001\u00071\tF\u0002aO\"DQ!\u0011\u0005A\u0002\rCQ\u0001\u0014\u0005A\u0002E#2\u0001\u00196l\u0011\u0015\t\u0015\u00021\u0001D\u0011\u0015)\u0016\u00021\u0001X\u0003)\tG\u000f\u001e:jEV$Xm]\u000b\u0002-\u0006Y\u0011\r\u001e;sS\n,H/Z:!\u0003-q\u0017-\\3U_&sG-\u001a=\u0016\u0003E\u0004B\u0001\u0012:D#&\u00111O\u0013\u0002\u0004\u001b\u0006\u0004\u0018\u0001B:ju\u0016,\u0012!U\u0001\bQ\u0006\u001c\u0018\t\u001e;s)\tA8\u0010\u0005\u00021s&\u0011!0\r\u0002\b\u0005>|G.Z1o\u0011\u0015ah\u00021\u0001D\u0003!\tG\u000f\u001e:OC6,\u0017aB5oI\u0016DxJ\u001a\u000b\u0003#~DQ\u0001`\bA\u0002\r\u000bQ!\u00199qYf$2AWA\u0003\u0011\u0015a\b\u00031\u0001D\u0003\u001d9W\r^!uiJ$2AWA\u0006\u0011\u0015a\u0018\u00031\u0001D)\rQ\u0016q\u0002\u0005\u0007\u0003#\u0011\u0002\u0019A)\u0002\u0013\u0005$HO]%oI\u0016DHc\u0001.\u0002\u0016!1\u0011\u0011C\nA\u0002E\u000ba\u0002^8NKR\fG-\u0019;b\u00136\u0004H.\u0006\u0002\u0002\u001cA!\u0011QDA\u0014\u001b\t\tyB\u0003\u0003\u0002\"\u0005\r\u0012!\u0002;za\u0016\u001c(bAA\u0013O\u0005\u00191/\u001d7\n\t\u0005%\u0012q\u0004\u0002\t\u001b\u0016$\u0018\rZ1uC\u0006QAo\\'fi\u0006$\u0017\r^1\u0015\t\u0005m\u0011q\u0006\u0005\b\u0003c)\u0002\u0019AA\u000e\u0003A)\u00070[:uS:<W*\u001a;bI\u0006$\u0018\r\u0006\u0002\u0002\u001c\u0005iAo\\*ueV\u001cGOR5fY\u0012$B!!\u000f\u0002@A!\u0011QDA\u001e\u0013\u0011\ti$a\b\u0003\u0017M#(/^2u\r&,G\u000e\u001a\u0005\b\u0003c9\u0002\u0019AA\u000e)\t\tI$\u0001\u0004fcV\fGn\u001d\u000b\u0004q\u0006\u001d\u0003bBA%3\u0001\u0007\u00111J\u0001\u0006_RDWM\u001d\t\u0004a\u00055\u0013bAA(c\t\u0019\u0011I\\=\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012!U\u0001\ti>\u001cFO]5oOR\t1)\u0001\bBiR\u0014\u0018NY;uK\u001e\u0013x.\u001e9\u0011\u0005mk2\u0003B\u000f0\u0003?\u0002B!!\u0019\u0002l5\u0011\u00111\r\u0006\u0005\u0003K\n9'\u0001\u0002j_*\u0011\u0011\u0011N\u0001\u0005U\u00064\u0018-C\u0002@\u0003G\"\"!a\u0017\u0002\u0019\u0019\u0014x.\\'fi\u0006$\u0017\r^1\u0015\u000b\u0001\f\u0019(a\u001e\t\u000f\u0005Ut\u00041\u0001\u0002\u001c\u0005AQ.\u001a;bI\u0006$\u0018\rC\u0003B?\u0001\u00071)A\bge>l7\u000b\u001e:vGR4\u0015.\u001a7e)\r\u0001\u0017Q\u0010\u0005\b\u0003\u007f\u0002\u0003\u0019AA\u001d\u0003\u00151\u0017.\u001a7e\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t)\t\u0005\u0003\u0002\b\u00065UBAAE\u0015\u0011\tY)a\u001a\u0002\t1\fgnZ\u0005\u0005\u0003\u001f\u000bII\u0001\u0004PE*,7\r\u001e"
)
public class AttributeGroup implements Serializable {
   private Map nameToIndex;
   private final String name;
   private final Option numAttributes;
   private final Option attributes;
   private volatile boolean bitmap$0;

   public static AttributeGroup fromStructField(final StructField field) {
      return AttributeGroup$.MODULE$.fromStructField(field);
   }

   public String name() {
      return this.name;
   }

   public Option numAttributes() {
      return this.numAttributes;
   }

   public Option attributes() {
      return this.attributes;
   }

   private Map nameToIndex$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.nameToIndex = (Map)this.attributes().map((x$2) -> .MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(x$2)).flatMap((attr) -> attr.name().map((x$3) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(x$3), attr.index().get()))).toMap(scala..less.colon.less..MODULE$.refl())).getOrElse(() -> scala.Predef..MODULE$.Map().empty());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nameToIndex;
   }

   private Map nameToIndex() {
      return !this.bitmap$0 ? this.nameToIndex$lzycompute() : this.nameToIndex;
   }

   public int size() {
      if (this.numAttributes().isDefined()) {
         return BoxesRunTime.unboxToInt(this.numAttributes().get());
      } else {
         return this.attributes().isDefined() ? ((Attribute[])this.attributes().get()).length : -1;
      }
   }

   public boolean hasAttr(final String attrName) {
      return this.nameToIndex().contains(attrName);
   }

   public int indexOf(final String attrName) {
      return BoxesRunTime.unboxToInt(this.nameToIndex().apply(attrName));
   }

   public Attribute apply(final String attrName) {
      return ((Attribute[])this.attributes().get())[this.indexOf(attrName)];
   }

   public Attribute getAttr(final String attrName) {
      return this.apply(attrName);
   }

   public Attribute apply(final int attrIndex) {
      return ((Attribute[])this.attributes().get())[attrIndex];
   }

   public Attribute getAttr(final int attrIndex) {
      return this.apply(attrIndex);
   }

   public Metadata toMetadataImpl() {
      MetadataBuilder bldr = new MetadataBuilder();
      if (this.attributes().isDefined()) {
         ArrayBuffer numericMetadata = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         ArrayBuffer nominalMetadata = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         ArrayBuffer binaryMetadata = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.attributes().get()), (x0$1) -> {
            if (!(x0$1 instanceof NumericAttribute var6)) {
               if (x0$1 instanceof NominalAttribute var8) {
                  return nominalMetadata.$plus$eq(var8.toMetadataImpl(false));
               } else if (x0$1 instanceof BinaryAttribute var9) {
                  return binaryMetadata.$plus$eq(var9.toMetadataImpl(false));
               } else if (UnresolvedAttribute$.MODULE$.equals(x0$1)) {
                  return BoxedUnit.UNIT;
               } else {
                  throw new MatchError(x0$1);
               }
            } else {
               NumericAttribute var10000 = var6.withoutIndex();
               NumericAttribute var7 = NumericAttribute$.MODULE$.defaultAttr();
               if (var10000 == null) {
                  if (var7 != null) {
                     return numericMetadata.$plus$eq(var6.toMetadataImpl(false));
                  }
               } else if (!var10000.equals(var7)) {
                  return numericMetadata.$plus$eq(var6.toMetadataImpl(false));
               }

               return BoxedUnit.UNIT;
            }
         });
         MetadataBuilder attrBldr = new MetadataBuilder();
         if (numericMetadata.nonEmpty()) {
            attrBldr.putMetadataArray(AttributeType$.MODULE$.Numeric().name(), (Metadata[])numericMetadata.toArray(scala.reflect.ClassTag..MODULE$.apply(Metadata.class)));
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         if (nominalMetadata.nonEmpty()) {
            attrBldr.putMetadataArray(AttributeType$.MODULE$.Nominal().name(), (Metadata[])nominalMetadata.toArray(scala.reflect.ClassTag..MODULE$.apply(Metadata.class)));
         } else {
            BoxedUnit var6 = BoxedUnit.UNIT;
         }

         if (binaryMetadata.nonEmpty()) {
            attrBldr.putMetadataArray(AttributeType$.MODULE$.Binary().name(), (Metadata[])binaryMetadata.toArray(scala.reflect.ClassTag..MODULE$.apply(Metadata.class)));
         } else {
            BoxedUnit var7 = BoxedUnit.UNIT;
         }

         bldr.putMetadata(AttributeKeys$.MODULE$.ATTRIBUTES(), attrBldr.build());
         bldr.putLong(AttributeKeys$.MODULE$.NUM_ATTRIBUTES(), (long)((Attribute[])this.attributes().get()).length);
      } else if (this.numAttributes().isDefined()) {
         bldr.putLong(AttributeKeys$.MODULE$.NUM_ATTRIBUTES(), (long)BoxesRunTime.unboxToInt(this.numAttributes().get()));
      } else {
         BoxedUnit var8 = BoxedUnit.UNIT;
      }

      return bldr.build();
   }

   public Metadata toMetadata(final Metadata existingMetadata) {
      return (new MetadataBuilder()).withMetadata(existingMetadata).putMetadata(AttributeKeys$.MODULE$.ML_ATTR(), this.toMetadataImpl()).build();
   }

   public Metadata toMetadata() {
      return this.toMetadata(org.apache.spark.sql.types.Metadata..MODULE$.empty());
   }

   public StructField toStructField(final Metadata existingMetadata) {
      return new StructField(this.name(), new VectorUDT(), false, this.toMetadata(existingMetadata));
   }

   public StructField toStructField() {
      return this.toStructField(org.apache.spark.sql.types.Metadata..MODULE$.empty());
   }

   public boolean equals(final Object other) {
      if (!(other instanceof AttributeGroup var4)) {
         return false;
      } else {
         boolean var10;
         label48: {
            label42: {
               String var10000 = this.name();
               String var5 = var4.name();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label42;
                  }
               } else if (!var10000.equals(var5)) {
                  break label42;
               }

               Option var8 = this.numAttributes();
               Option var6 = var4.numAttributes();
               if (var8 == null) {
                  if (var6 != null) {
                     break label42;
                  }
               } else if (!var8.equals(var6)) {
                  break label42;
               }

               var8 = this.attributes().map((x$4) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$4).toImmutableArraySeq());
               Option var7 = var4.attributes().map((x$5) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$5).toImmutableArraySeq());
               if (var8 == null) {
                  if (var7 == null) {
                     break label48;
                  }
               } else if (var8.equals(var7)) {
                  break label48;
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public int hashCode() {
      int sum = 17;
      sum = 37 * sum + this.name().hashCode();
      sum = 37 * sum + this.numAttributes().hashCode();
      sum = 37 * sum + this.attributes().map((x$6) -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$6).toImmutableArraySeq()).hashCode();
      return sum;
   }

   public String toString() {
      return this.toMetadata().toString();
   }

   private AttributeGroup(final String name, final Option numAttributes, final Option attrs) {
      this.name = name;
      this.numAttributes = numAttributes;
      scala.Predef..MODULE$.require(scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(name)), () -> "Cannot have an empty string for name.");
      scala.Predef..MODULE$.require(!numAttributes.isDefined() || !attrs.isDefined(), () -> "Cannot have both numAttributes and attrs defined.");
      this.attributes = attrs.map((x$1) -> (Attribute[]).MODULE$.iterator$extension(scala.Predef..MODULE$.refArrayOps(x$1)).zipWithIndex().map((x0$1) -> {
            if (x0$1 != null) {
               Attribute attr = (Attribute)x0$1._1();
               int i = x0$1._2$mcI$sp();
               return attr.withIndex(i);
            } else {
               throw new MatchError(x0$1);
            }
         }).toArray(scala.reflect.ClassTag..MODULE$.apply(Attribute.class)));
   }

   public AttributeGroup(final String name) {
      this(name, scala.None..MODULE$, scala.None..MODULE$);
   }

   public AttributeGroup(final String name, final int numAttributes) {
      this(name, new Some(BoxesRunTime.boxToInteger(numAttributes)), scala.None..MODULE$);
   }

   public AttributeGroup(final String name, final Attribute[] attrs) {
      this(name, scala.None..MODULE$, new Some(attrs));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
