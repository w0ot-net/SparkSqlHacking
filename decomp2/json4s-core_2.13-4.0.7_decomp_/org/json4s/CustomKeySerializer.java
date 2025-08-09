package org.json4s;

import java.io.Serializable;
import org.json4s.reflect.TypeInfo;
import scala.Function1;
import scala.PartialFunction;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.package.;

@ScalaSignature(
   bytes = "\u0006\u0005)4A\u0001C\u0005\u0001\u001d!AQ\u0005\u0001B\u0001B\u0003%a\u0005\u0003\u0005?\u0001\t\r\t\u0015a\u0003@\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u001dY\u0005A1A\u0005\u00021Ca!\u0017\u0001!\u0002\u0013i\u0005\"\u0002.\u0001\t\u0003Y\u0006\"B4\u0001\t\u0003A'aE\"vgR|WnS3z'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\u0006\f\u0003\u0019Q7o\u001c85g*\tA\"A\u0002pe\u001e\u001c\u0001!\u0006\u0002\u00109M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\r9\u0002DG\u0007\u0002\u0013%\u0011\u0011$\u0003\u0002\u000e\u0017\u0016L8+\u001a:jC2L'0\u001a:\u0011\u0005maB\u0002\u0001\u0003\u0006;\u0001\u0011\rA\b\u0002\u0002\u0003F\u0011qD\t\t\u0003#\u0001J!!\t\n\u0003\u000f9{G\u000f[5oOB\u0011\u0011cI\u0005\u0003II\u00111!\u00118z\u0003\r\u0019XM\u001d\t\u0005#\u001dJC&\u0003\u0002)%\tIa)\u001e8di&|g.\r\t\u0003/)J!aK\u0005\u0003\u000f\u0019{'/\\1ugB!\u0011#L\u0018>\u0013\tq#C\u0001\u0004UkBdWM\r\t\u0005#A\u0012$$\u0003\u00022%\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u00024u9\u0011A\u0007\u000f\t\u0003kIi\u0011A\u000e\u0006\u0003o5\ta\u0001\u0010:p_Rt\u0014BA\u001d\u0013\u0003\u0019\u0001&/\u001a3fM&\u00111\b\u0010\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005e\u0012\u0002\u0003B\t1EI\n!\"\u001a<jI\u0016t7-\u001a\u00132!\r\u00015IG\u0007\u0002\u0003*\u0011!IE\u0001\be\u00164G.Z2u\u0013\t!\u0015I\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q\u0011qI\u0013\u000b\u0003\u0011&\u00032a\u0006\u0001\u001b\u0011\u0015q4\u0001q\u0001@\u0011\u0015)3\u00011\u0001'\u0003\u0015\u0019E.Y:t+\u0005i\u0005G\u0001(X!\ryEKV\u0007\u0002!*\u0011\u0011KU\u0001\u0005Y\u0006twMC\u0001T\u0003\u0011Q\u0017M^1\n\u0005U\u0003&!B\"mCN\u001c\bCA\u000eX\t%AV!!A\u0001\u0002\u000b\u0005aDA\u0002`IE\naa\u00117bgN\u0004\u0013a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016$\"\u0001X3\u0011\tE\u0001TL\u0007\t\u0005#5r&\u0007\u0005\u0002`E:\u0011q\u0003Y\u0005\u0003C&\tq\u0001]1dW\u0006<W-\u0003\u0002dI\nAA+\u001f9f\u0013:4wN\u0003\u0002b\u0013!)aM\u0002a\u0002S\u00051am\u001c:nCR\f\u0011b]3sS\u0006d\u0017N_3\u0015\u0005uJ\u0007\"\u00024\b\u0001\bI\u0003"
)
public class CustomKeySerializer implements KeySerializer {
   public final Function1 org$json4s$CustomKeySerializer$$ser;
   private final Class Class;

   public Class Class() {
      return this.Class;
   }

   public PartialFunction deserialize(final Formats format) {
      return new Serializable(format) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final CustomKeySerializer $outer;
         private final Formats format$1;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               TypeInfo var5 = (TypeInfo)x1._1();
               String json = (String)x1._2();
               if (var5 != null) {
                  label41: {
                     Class var7 = var5.clazz();
                     Class var10000 = this.$outer.Class();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label41;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label41;
                     }

                     if (!((PartialFunction)((Tuple2)this.$outer.org$json4s$CustomKeySerializer$$ser.apply(this.format$1))._1()).isDefinedAt(json)) {
                        throw new MappingException((new StringBuilder(18)).append("Can't convert ").append(json).append(" to ").append(this.$outer.Class()).toString());
                     }

                     var3 = ((Function1)((Tuple2)this.$outer.org$json4s$CustomKeySerializer$$ser.apply(this.format$1))._1()).apply(json);
                     return var3;
                  }
               }
            }

            var3 = default.apply(x1);
            return var3;
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            boolean var2;
            label30: {
               if (x1 != null) {
                  TypeInfo var4 = (TypeInfo)x1._1();
                  if (var4 != null) {
                     Class var5 = var4.clazz();
                     Class var10000 = this.$outer.Class();
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label30;
                        }
                     } else if (var10000.equals(var5)) {
                        break label30;
                     }
                  }
               }

               var2 = false;
               return var2;
            }

            var2 = true;
            return var2;
         }

         public {
            if (CustomKeySerializer.this == null) {
               throw null;
            } else {
               this.$outer = CustomKeySerializer.this;
               this.format$1 = format$1;
            }
         }
      };
   }

   public PartialFunction serialize(final Formats format) {
      return (PartialFunction)((Tuple2)this.org$json4s$CustomKeySerializer$$ser.apply(format))._2();
   }

   public CustomKeySerializer(final Function1 ser, final ClassTag evidence$1) {
      this.org$json4s$CustomKeySerializer$$ser = ser;
      this.Class = .MODULE$.classTag(evidence$1).runtimeClass();
   }
}
