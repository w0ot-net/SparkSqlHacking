package com.fasterxml.jackson.module.scala.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdConverter;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import scala.None;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c\u0001B\u0007\u000f\tmA\u0001\u0002\u0013\u0001\u0003\u0002\u0003\u0006I!\u0013\u0005\t\u001b\u0002\u0011\t\u0011)A\u0005\u001d\")\u0011\u000b\u0001C\u0001%\u001a!q\u000b\u0001\u0003Y\u0011!\u0019GA!A!\u0002\u0013!\u0007\"B)\u0005\t\u0003)\u0007bB5\u0005\u0005\u0004%IA\u001b\u0005\u0007Y\u0012\u0001\u000b\u0011B6\t\u000b5$A\u0011\t8\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u0011\u0011\u0006\u0001\u0005B\u0005-\u0002bBA\u001f\u0001\u0011\u0005\u0013q\b\u0002\r\u001b\u0006\u00048i\u001c8wKJ$XM\u001d\u0006\u0003\u001fA\t1a]3s\u0015\t\t\"#A\u0003tG\u0006d\u0017M\u0003\u0002\u0014)\u00051Qn\u001c3vY\u0016T!!\u0006\f\u0002\u000f)\f7m[:p]*\u0011q\u0003G\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!G\u0001\u0004G>l7\u0001A\n\u0003\u0001q\u0001B!\b\u0012%w5\taD\u0003\u0002 A\u0005!Q\u000f^5m\u0015\t\tC#\u0001\u0005eCR\f'-\u001b8e\u0013\t\u0019cD\u0001\u0007Ti\u0012\u001cuN\u001c<feR,'\u000fM\u0002&]e\u0002BA\n\u0016-q5\tqE\u0003\u0002)S\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003EI!aK\u0014\u0003\u00075\u000b\u0007\u000f\u0005\u0002.]1\u0001A!C\u0018\u0001\u0003\u0003\u0005\tQ!\u00011\u0005\ryF%M\t\u0003cU\u0002\"AM\u001a\u000e\u0003%J!\u0001N\u0015\u0003\u000f9{G\u000f[5oOB\u0011!GN\u0005\u0003o%\u00121!\u00118z!\ti\u0013\bB\u0005;\u0001\u0005\u0005\t\u0011!B\u0001a\t\u0019q\f\n\u001a1\u0007q\u001ae\t\u0005\u0003>\u0003\n+U\"\u0001 \u000b\u0005}y$\"\u0001!\u0002\t)\fg/Y\u0005\u0003Wy\u0002\"!L\"\u0005\u0013\u0011\u0003\u0011\u0011!A\u0001\u0006\u0003\u0001$aA0%gA\u0011QF\u0012\u0003\n\u000f\u0002\t\t\u0011!A\u0003\u0002A\u00121a\u0018\u00135\u0003%Ig\u000e];u)f\u0004X\r\u0005\u0002K\u00176\t\u0001%\u0003\u0002MA\tA!*\u0019<b)f\u0004X-\u0001\u0004d_:4\u0017n\u001a\t\u0003\u0015>K!\u0001\u0015\u0011\u0003'M+'/[1mSj\fG/[8o\u0007>tg-[4\u0002\rqJg.\u001b;?)\r\u0019VK\u0016\t\u0003)\u0002i\u0011A\u0004\u0005\u0006\u0011\u000e\u0001\r!\u0013\u0005\u0006\u001b\u000e\u0001\rA\u0014\u0002\u000b\u001b\u0006\u0004xK]1qa\u0016\u0014XcA-_CN\u0011AA\u0017\t\u0005{mk\u0006-\u0003\u0002]}\tY\u0011IY:ue\u0006\u001cG/T1q!\tic\fB\u0003`\t\t\u0007\u0001GA\u0001B!\ti\u0013\rB\u0003c\t\t\u0007\u0001GA\u0001C\u0003!!W\r\\3hCR,\u0007\u0003\u0002\u0014+;\u0002$\"A\u001a5\u0011\t\u001d$Q\fY\u0007\u0002\u0001!)1M\u0002a\u0001I\u00069qO]1qa\u0016$W#A6\u0011\tu\nU\fY\u0001\toJ\f\u0007\u000f]3eA\u0005AQM\u001c;ssN+G\u000fF\u0001p!\ri\u0004O]\u0005\u0003cz\u00121aU3u!\u0011\u0019X0\u00181\u000f\u0005Q\\hBA;{\u001d\t1\u00180D\u0001x\u0015\tA($\u0001\u0004=e>|GOP\u0005\u0002\u0001&\u0011qdP\u0005\u0003yz\n1!T1q\u0013\tqxPA\u0003F]R\u0014\u0018P\u0003\u0002}}\u000591m\u001c8wKJ$H\u0003BA\u0003\u0003+\u0001d!a\u0002\u0002\f\u0005E\u0001CB\u001fB\u0003\u0013\ty\u0001E\u0002.\u0003\u0017!!\"!\u0004\u000b\u0003\u0003\u0005\tQ!\u00011\u0005\ryFe\u000e\t\u0004[\u0005EAACA\n\u0015\u0005\u0005\t\u0011!B\u0001a\t\u0019q\f\n\u001d\t\u000f\u0005]!\u00021\u0001\u0002\u001a\u0005)a/\u00197vKB2\u00111DA\u0010\u0003K\u0001bA\n\u0016\u0002\u001e\u0005\r\u0002cA\u0017\u0002 \u0011Y\u0011\u0011EA\u000b\u0003\u0003\u0005\tQ!\u00011\u0005\ryF%\u000e\t\u0004[\u0005\u0015BaCA\u0014\u0003+\t\t\u0011!A\u0003\u0002A\u00121a\u0018\u00137\u000319W\r^%oaV$H+\u001f9f)\rI\u0015Q\u0006\u0005\b\u0003_Y\u0001\u0019AA\u0019\u0003\u001d1\u0017m\u0019;pef\u0004B!a\r\u0002:5\u0011\u0011Q\u0007\u0006\u0004\u0003o\u0001\u0013\u0001\u0002;za\u0016LA!a\u000f\u00026\tYA+\u001f9f\r\u0006\u001cGo\u001c:z\u000359W\r^(viB,H\u000fV=qKR!\u0011\u0011IA$!\u0011\t\u0019$a\u0011\n\t\u0005\u0015\u0013Q\u0007\u0002\b\u001b\u0006\u0004H+\u001f9f\u0011\u001d\ty\u0003\u0004a\u0001\u0003c\u0001"
)
public class MapConverter extends StdConverter {
   private final JavaType inputType;
   private final SerializationConfig config;

   public Map convert(final scala.collection.Map value) {
      scala.collection.Map m = this.config.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? value : (scala.collection.Map)value.filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$convert$1(x$1)));
      return new MapWrapper(m);
   }

   public JavaType getInputType(final TypeFactory factory) {
      return this.inputType;
   }

   public MapType getOutputType(final TypeFactory factory) {
      return factory.constructMapType(Map.class, this.inputType.getKeyType(), this.inputType.getContentType()).withTypeHandler(this.inputType.getTypeHandler()).withValueHandler(this.inputType.getValueHandler());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convert$1(final Tuple2 x$1) {
      boolean var2;
      label23: {
         Object var10000 = x$1._2();
         None var1 = .MODULE$;
         if (var10000 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public MapConverter(final JavaType inputType, final SerializationConfig config) {
      this.inputType = inputType;
      this.config = config;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class MapWrapper extends AbstractMap {
      private final Map wrapped;
      // $FF: synthetic field
      public final MapConverter $outer;

      private Map wrapped() {
         return this.wrapped;
      }

      public Set entrySet() {
         return this.wrapped().entrySet();
      }

      // $FF: synthetic method
      public MapConverter com$fasterxml$jackson$module$scala$ser$MapConverter$MapWrapper$$$outer() {
         return this.$outer;
      }

      public MapWrapper(final scala.collection.Map delegate) {
         if (MapConverter.this == null) {
            throw null;
         } else {
            this.$outer = MapConverter.this;
            super();
            this.wrapped = (Map)scala.collection.JavaConverters..MODULE$.mapAsJavaMapConverter(delegate).asJava();
         }
      }
   }
}
