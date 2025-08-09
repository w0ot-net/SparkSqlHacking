package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import java.io.Serializable;
import scala.Tuple1;
import scala.Tuple10;
import scala.Tuple11;
import scala.Tuple12;
import scala.Tuple13;
import scala.Tuple14;
import scala.Tuple15;
import scala.Tuple16;
import scala.Tuple17;
import scala.Tuple18;
import scala.Tuple19;
import scala.Tuple2;
import scala.Tuple20;
import scala.Tuple21;
import scala.Tuple22;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Tuple6;
import scala.Tuple7;
import scala.Tuple8;
import scala.Tuple9;
import scala.runtime.ModuleSerializationProxy;

public final class ScalaTupleSerialization$ implements Serializable {
   public static final ScalaTupleSerialization$ MODULE$ = new ScalaTupleSerialization$();

   public IKryoRegistrar register() {
      return new IKryoRegistrar() {
         public void apply(final Kryo newK) {
            newK.register(Tuple1.class, new Tuple1Serializer());
            newK.register(Tuple2.class, new Tuple2Serializer());
            newK.register(Tuple3.class, new Tuple3Serializer());
            newK.register(Tuple4.class, new Tuple4Serializer());
            newK.register(Tuple5.class, new Tuple5Serializer());
            newK.register(Tuple6.class, new Tuple6Serializer());
            newK.register(Tuple7.class, new Tuple7Serializer());
            newK.register(Tuple8.class, new Tuple8Serializer());
            newK.register(Tuple9.class, new Tuple9Serializer());
            newK.register(Tuple10.class, new Tuple10Serializer());
            newK.register(Tuple11.class, new Tuple11Serializer());
            newK.register(Tuple12.class, new Tuple12Serializer());
            newK.register(Tuple13.class, new Tuple13Serializer());
            newK.register(Tuple14.class, new Tuple14Serializer());
            newK.register(Tuple15.class, new Tuple15Serializer());
            newK.register(Tuple16.class, new Tuple16Serializer());
            newK.register(Tuple17.class, new Tuple17Serializer());
            newK.register(Tuple18.class, new Tuple18Serializer());
            newK.register(Tuple19.class, new Tuple19Serializer());
            newK.register(Tuple20.class, new Tuple20Serializer());
            newK.register(Tuple21.class, new Tuple21Serializer());
            newK.register(Tuple22.class, new Tuple22Serializer());
            newK.register(Class.forName("scala.Tuple1$mcJ$sp"), new Tuple1LongSerializer());
            newK.register(Class.forName("scala.Tuple1$mcI$sp"), new Tuple1IntSerializer());
            newK.register(Class.forName("scala.Tuple1$mcD$sp"), new Tuple1DoubleSerializer());
            newK.register(Class.forName("scala.Tuple2$mcJJ$sp"), new Tuple2LongLongSerializer());
            newK.register(Class.forName("scala.Tuple2$mcJI$sp"), new Tuple2LongIntSerializer());
            newK.register(Class.forName("scala.Tuple2$mcJD$sp"), new Tuple2LongDoubleSerializer());
            newK.register(Class.forName("scala.Tuple2$mcIJ$sp"), new Tuple2IntLongSerializer());
            newK.register(Class.forName("scala.Tuple2$mcII$sp"), new Tuple2IntIntSerializer());
            newK.register(Class.forName("scala.Tuple2$mcID$sp"), new Tuple2IntDoubleSerializer());
            newK.register(Class.forName("scala.Tuple2$mcDJ$sp"), new Tuple2DoubleLongSerializer());
            newK.register(Class.forName("scala.Tuple2$mcDI$sp"), new Tuple2DoubleIntSerializer());
            newK.register(Class.forName("scala.Tuple2$mcDD$sp"), new Tuple2DoubleDoubleSerializer());
         }
      };
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ScalaTupleSerialization$.class);
   }

   private ScalaTupleSerialization$() {
   }
}
