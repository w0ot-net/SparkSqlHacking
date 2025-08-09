package com.fasterxml.jackson.module.scala.util;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration;
import scala.Enumeration;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mq!\u0002\b\u0010\u0011\u0003ab!\u0002\u0010\u0010\u0011\u0003y\u0002\"B\u0013\u0002\t\u00031\u0003\"B\u0014\u0002\t\u0003A\u0003\"B\u0014\u0002\t\u00039\b\"B\u0014\u0002\t\u0003q\bBB\u0014\u0002\t\u0003\tiA\u0002\u0003\u001f\u001f\u0001i\u0003\u0002\u0003\u0018\b\u0005\u0003\u0005\u000b\u0011B\u0018\t\u0011\u0019;!\u0011!Q\u0001\n\u001dC\u0001\"T\u0004\u0003\u0002\u0003\u0006IA\u0014\u0005\u0006K\u001d!\ta\u0016\u0005\u0006?\u001e!\t\u0001\u0019\u0005\u0006G\u001e!\t\u0001Z\u0001\r\u000b:,XNU3t_24XM\u001d\u0006\u0003!E\tA!\u001e;jY*\u0011!cE\u0001\u0006g\u000e\fG.\u0019\u0006\u0003)U\ta!\\8ek2,'B\u0001\f\u0018\u0003\u001dQ\u0017mY6t_:T!\u0001G\r\u0002\u0013\u0019\f7\u000f^3sq6d'\"\u0001\u000e\u0002\u0007\r|Wn\u0001\u0001\u0011\u0005u\tQ\"A\b\u0003\u0019\u0015sW/\u001c*fg>dg/\u001a:\u0014\u0005\u0005\u0001\u0003CA\u0011$\u001b\u0005\u0011#\"\u0001\n\n\u0005\u0011\u0012#AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u00029\u0005)\u0011\r\u001d9msR\u0011\u0011f\u001c\t\u0004C)b\u0013BA\u0016#\u0005\u0019y\u0005\u000f^5p]B\u0011QdB\n\u0003\u000f\u0001\n1a\u00197ta\t\u0001T\bE\u00022qmr!A\r\u001c\u0011\u0005M\u0012S\"\u0001\u001b\u000b\u0005UZ\u0012A\u0002\u001fs_>$h(\u0003\u00028E\u00051\u0001K]3eK\u001aL!!\u000f\u001e\u0003\u000b\rc\u0017m]:\u000b\u0005]\u0012\u0003C\u0001\u001f>\u0019\u0001!\u0011B\u0010\u0005\u0002\u0002\u0003\u0005)\u0011A \u0003\u0007}#\u0013'\u0005\u0002A\u0007B\u0011\u0011%Q\u0005\u0003\u0005\n\u0012qAT8uQ&tw\r\u0005\u0002\"\t&\u0011QI\t\u0002\u0004\u0003:L\u0018\u0001\u0003<bYV,7+\u001a;\u0011\u0005![\u0005CA\u0011J\u0013\tQ%EA\u0006F]VlWM]1uS>t\u0017B\u0001'J\u0005!1\u0016\r\\;f'\u0016$\u0018aC3ok6\u001c()\u001f(b[\u0016\u0004B!M(R)&\u0011\u0001K\u000f\u0002\u0004\u001b\u0006\u0004\bCA\u0019S\u0013\t\u0019&H\u0001\u0004TiJLgn\u001a\t\u0003\u0011VK!AV%\u0003\u000bY\u000bG.^3\u0015\t1BVL\u0018\u0005\u0006]-\u0001\r!\u0017\u0019\u00035r\u00032!\r\u001d\\!\taD\fB\u0005?1\u0006\u0005\t\u0011!B\u0001\u007f!)ai\u0003a\u0001\u000f\")Qj\u0003a\u0001\u001d\u00069q-\u001a;F]VlGC\u0001+b\u0011\u0015\u0011G\u00021\u0001R\u0003\rYW-_\u0001\rO\u0016$XI\\;n\u00072\f7o]\u000b\u0002KB\u0012aM\u001c\t\u0004O2lW\"\u00015\u000b\u0005%T\u0017\u0001\u00027b]\u001eT\u0011a[\u0001\u0005U\u00064\u0018-\u0003\u0002:QB\u0011AH\u001c\u0003\n}5\t\t\u0011!A\u0003\u0002}BQ\u0001]\u0002A\u0002E\f\u0001\u0002\u001d:pa\u0016\u0014H/\u001f\t\u0003eVl\u0011a\u001d\u0006\u0003iV\t\u0001\u0002Z1uC\nLg\u000eZ\u0005\u0003mN\u0014ABQ3b]B\u0013x\u000e]3sif$\"\u0001\f=\t\u000be$\u0001\u0019\u0001>\u0002\u0003\u0005\u0004\"a\u001f?\u000e\u0003EI!!`\t\u0003))\u001bxN\\*dC2\fWI\\;nKJ\fG/[8o+\ry\u0018q\u0001\u000b\u0004Y\u0005\u0005\u0001B\u0002\u0018\u0006\u0001\u0004\t\u0019\u0001\u0005\u00032q\u0005\u0015\u0001c\u0001\u001f\u0002\b\u00119\u0011\u0011B\u0003C\u0002\u0005-!!\u0001+\u0012\u0005\u0001CEc\u0001\u0017\u0002\u0010!1\u0011\u0011\u0003\u0004A\u0002!\u000b\u0011!\u001a"
)
public class EnumResolver {
   private final Class cls;
   private final Map enumsByName;

   public static EnumResolver apply(final Enumeration e) {
      return EnumResolver$.MODULE$.apply(e);
   }

   public static EnumResolver apply(final Class cls) {
      return EnumResolver$.MODULE$.apply(cls);
   }

   public static EnumResolver apply(final JsonScalaEnumeration a) {
      return EnumResolver$.MODULE$.apply(a);
   }

   public static Option apply(final BeanProperty property) {
      return EnumResolver$.MODULE$.apply(property);
   }

   public Enumeration.Value getEnum(final String key) {
      return (Enumeration.Value)this.enumsByName.apply(key);
   }

   public Class getEnumClass() {
      return this.cls;
   }

   public EnumResolver(final Class cls, final Enumeration.ValueSet valueSet, final Map enumsByName) {
      this.cls = cls;
      this.enumsByName = enumsByName;
   }
}
