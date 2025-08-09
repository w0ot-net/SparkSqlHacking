package org.apache.spark.ml.attribute;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3Q\u0001E\t\u0002\"qA\u0001b\t\u0001\u0003\u0006\u0004%\t\u0001\n\u0005\ta\u0001\u0011\t\u0011)A\u0005K!)\u0011\u0007\u0001C\u0001e\u001d)!(\u0005E\u0001w\u0019)\u0001#\u0005E\u0001y!)\u0011'\u0002C\u0001{!9a(\u0002b\u0001\n\u0003y\u0004B\u0002!\u0006A\u0003%1\u0007C\u0004B\u000b\t\u0007I\u0011A \t\r\t+\u0001\u0015!\u00034\u0011\u001d\u0019UA1A\u0005\u0002}Ba\u0001R\u0003!\u0002\u0013\u0019\u0004bB#\u0006\u0005\u0004%\ta\u0010\u0005\u0007\r\u0016\u0001\u000b\u0011B\u001a\t\u000b\u001d+A\u0011\u0001%\u0003\u001b\u0005#HO]5ckR,G+\u001f9f\u0015\t\u00112#A\u0005biR\u0014\u0018NY;uK*\u0011A#F\u0001\u0003[2T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u0002\u0001'\t\u0001Q\u0004\u0005\u0002\u001fC5\tqDC\u0001!\u0003\u0015\u00198-\u00197b\u0013\t\u0011sD\u0001\u0004B]f\u0014VMZ\u0001\u0005]\u0006lW-F\u0001&!\t1SF\u0004\u0002(WA\u0011\u0001fH\u0007\u0002S)\u0011!fG\u0001\u0007yI|w\u000e\u001e \n\u00051z\u0012A\u0002)sK\u0012,g-\u0003\u0002/_\t11\u000b\u001e:j]\u001eT!\u0001L\u0010\u0002\u000b9\fW.\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0019T\u0007\u0005\u00025\u00015\t\u0011\u0003C\u0003$\u0007\u0001\u0007Q%\u000b\u0002\u0001o\u0019!\u0001\b\u0001\u0001:\u00055aDn\\2bY\u0002\u001a\u0007.\u001b7e}M\u0011qgM\u0001\u000e\u0003R$(/\u001b2vi\u0016$\u0016\u0010]3\u0011\u0005Q*1CA\u0003\u001e)\u0005Y\u0014a\u0002(v[\u0016\u0014\u0018nY\u000b\u0002g\u0005Aa*^7fe&\u001c\u0007%A\u0004O_6Lg.\u00197\u0002\u00119{W.\u001b8bY\u0002\naAQ5oCJL\u0018a\u0002\"j]\u0006\u0014\u0018\u0010I\u0001\u000b+:\u0014Xm]8mm\u0016$\u0017aC+oe\u0016\u001cx\u000e\u001c<fI\u0002\n\u0001B\u001a:p[:\u000bW.\u001a\u000b\u0003g%CQaI\bA\u0002\u0015\u0002"
)
public abstract class AttributeType {
   private final String name;

   public static AttributeType fromName(final String name) {
      return AttributeType$.MODULE$.fromName(name);
   }

   public static AttributeType Unresolved() {
      return AttributeType$.MODULE$.Unresolved();
   }

   public static AttributeType Binary() {
      return AttributeType$.MODULE$.Binary();
   }

   public static AttributeType Nominal() {
      return AttributeType$.MODULE$.Nominal();
   }

   public static AttributeType Numeric() {
      return AttributeType$.MODULE$.Numeric();
   }

   public String name() {
      return this.name;
   }

   public AttributeType(final String name) {
      this.name = name;
   }
}
