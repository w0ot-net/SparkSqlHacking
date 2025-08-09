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
   bytes = "\u0006\u0005\t4A\u0001C\u0005\u0001\u001d!AQ\u0005\u0001B\u0001B\u0003%a\u0005\u0003\u00057\u0001\t\r\t\u0015a\u00038\u0011\u0015i\u0004\u0001\"\u0001?\u0011\u001d\u0019\u0005A1A\u0005\u0002\u0011Ca!\u0015\u0001!\u0002\u0013)\u0005\"\u0002*\u0001\t\u0003\u0019\u0006\"B0\u0001\t\u0003\u0001'\u0001E\"vgR|WnU3sS\u0006d\u0017N_3s\u0015\tQ1\"\u0001\u0004kg>tGg\u001d\u0006\u0002\u0019\u0005\u0019qN]4\u0004\u0001U\u0011q\u0002H\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u00181ii\u0011!C\u0005\u00033%\u0011!bU3sS\u0006d\u0017N_3s!\tYB\u0004\u0004\u0001\u0005\u000bu\u0001!\u0019\u0001\u0010\u0003\u0003\u0005\u000b\"a\b\u0012\u0011\u0005E\u0001\u0013BA\u0011\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u0012\n\u0005\u0011\u0012\"aA!os\u0006\u00191/\u001a:\u0011\tE9\u0013\u0006L\u0005\u0003QI\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005]Q\u0013BA\u0016\n\u0005\u001d1uN]7biN\u0004B!E\u00170k%\u0011aF\u0005\u0002\u0007)V\u0004H.\u001a\u001a\u0011\tE\u0001$GG\u0005\u0003cI\u0011q\u0002U1si&\fGNR;oGRLwN\u001c\t\u0003/MJ!\u0001N\u0005\u0003\r)3\u0016\r\\;f!\u0011\t\u0002G\t\u001a\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00029wii\u0011!\u000f\u0006\u0003uI\tqA]3gY\u0016\u001cG/\u0003\u0002=s\tA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0003\u007f\t#\"\u0001Q!\u0011\u0007]\u0001!\u0004C\u00037\u0007\u0001\u000fq\u0007C\u0003&\u0007\u0001\u0007a%A\u0003DY\u0006\u001c8/F\u0001Fa\t1u\nE\u0002H\u0019:k\u0011\u0001\u0013\u0006\u0003\u0013*\u000bA\u0001\\1oO*\t1*\u0001\u0003kCZ\f\u0017BA'I\u0005\u0015\u0019E.Y:t!\tYr\nB\u0005Q\u000b\u0005\u0005\t\u0011!B\u0001=\t\u0019q\fJ\u0019\u0002\r\rc\u0017m]:!\u0003-!Wm]3sS\u0006d\u0017N_3\u0015\u0005Qk\u0006\u0003B\t1+j\u0001B!E\u0017WeA\u0011qK\u0017\b\u0003/aK!!W\u0005\u0002\u000fA\f7m[1hK&\u00111\f\u0018\u0002\t)f\u0004X-\u00138g_*\u0011\u0011,\u0003\u0005\u0006=\u001a\u0001\u001d!K\u0001\u0007M>\u0014X.\u0019;\u0002\u0013M,'/[1mSj,GCA\u001bb\u0011\u0015qv\u0001q\u0001*\u0001"
)
public class CustomSerializer implements Serializer {
   public final Function1 org$json4s$CustomSerializer$$ser;
   private final Class Class;

   public Class Class() {
      return this.Class;
   }

   public PartialFunction deserialize(final Formats format) {
      return new Serializable(format) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final CustomSerializer $outer;
         private final Formats format$1;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            Object var3;
            if (x1 != null) {
               TypeInfo var5 = (TypeInfo)x1._1();
               JValue json = (JValue)x1._2();
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

                     if (!((PartialFunction)((Tuple2)this.$outer.org$json4s$CustomSerializer$$ser.apply(this.format$1))._1()).isDefinedAt(json)) {
                        throw new MappingException((new StringBuilder(18)).append("Can't convert ").append(json).append(" to ").append(this.$outer.Class()).toString());
                     }

                     var3 = ((Function1)((Tuple2)this.$outer.org$json4s$CustomSerializer$$ser.apply(this.format$1))._1()).apply(json);
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
            if (CustomSerializer.this == null) {
               throw null;
            } else {
               this.$outer = CustomSerializer.this;
               this.format$1 = format$1;
            }
         }
      };
   }

   public PartialFunction serialize(final Formats format) {
      return (PartialFunction)((Tuple2)this.org$json4s$CustomSerializer$$ser.apply(format))._2();
   }

   public CustomSerializer(final Function1 ser, final ClassTag evidence$1) {
      this.org$json4s$CustomSerializer$$ser = ser;
      this.Class = .MODULE$.classTag(evidence$1).runtimeClass();
   }
}
