package com.fasterxml.jackson.module.scala.modifiers;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.type.TypeModifier;
import java.lang.reflect.Type;
import scala.Option;
import scala.collection.IterableOnce;
import scala.collection.Map;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.LongMap;
import scala.reflect.ScalaSignature;
import scala.util.Either;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001B\u000b\u0017\u0001\rBQ\u0001\f\u0001\u0005\u00025Bq\u0001\r\u0001C\u0002\u0013%\u0011\u0007\u0003\u0004E\u0001\u0001\u0006IA\r\u0005\b\u0019\u0002\u0011\r\u0011\"\u0003N\u0011\u0019y\u0006\u0001)A\u0005\u001d\"91\r\u0001b\u0001\n\u0013!\u0007B\u00029\u0001A\u0003%Q\rC\u0004u\u0001\t\u0007I\u0011B;\t\ri\u0004\u0001\u0015!\u0003w\u0011\u001dY\bA1A\u0005\nqDq!!\u0005\u0001A\u0003%Q\u0010C\u0005\u0002\u0014\u0001\u0011\r\u0011\"\u0003\u0002\u0016!A\u0011q\u0004\u0001!\u0002\u0013\t9\u0002C\u0005\u0002\"\u0001\u0011\r\u0011\"\u0003\u0002$!A\u0011Q\u0007\u0001!\u0002\u0013\t)\u0003C\u0005\u00028\u0001\u0011\r\u0011\"\u0003\u0002:!A\u0011q\n\u0001!\u0002\u0013\tY\u0004C\u0005\u0002R\u0001\u0011\r\u0011\"\u0003\u0002T!A\u0011Q\r\u0001!\u0002\u0013\t)\u0006C\u0004\u0002h\u0001!\t%!\u001b\u0003#M\u001b\u0017\r\\1UsB,Wj\u001c3jM&,'O\u0003\u0002\u00181\u0005IQn\u001c3jM&,'o\u001d\u0006\u00033i\tQa]2bY\u0006T!a\u0007\u000f\u0002\r5|G-\u001e7f\u0015\tib$A\u0004kC\u000e\\7o\u001c8\u000b\u0005}\u0001\u0013!\u00034bgR,'\u000f_7m\u0015\u0005\t\u0013aA2p[\u000e\u00011C\u0001\u0001%!\t)#&D\u0001'\u0015\t9\u0003&\u0001\u0003usB,'BA\u0015\u001d\u0003!!\u0017\r^1cS:$\u0017BA\u0016'\u00051!\u0016\u0010]3N_\u0012Lg-[3s\u0003\u0019a\u0014N\\5u}Q\ta\u0006\u0005\u00020\u00015\ta#A\u0006paRLwN\\\"mCN\u001cX#\u0001\u001a\u0011\u0007MB$(D\u00015\u0015\t)d'\u0001\u0003mC:<'\"A\u001c\u0002\t)\fg/Y\u0005\u0003sQ\u0012Qa\u00117bgN\u0004$a\u000f\"\u0011\u0007qr\u0004)D\u0001>\u0015\u0005I\u0012BA >\u0005\u0019y\u0005\u000f^5p]B\u0011\u0011I\u0011\u0007\u0001\t%\u00195!!A\u0001\u0002\u000b\u0005QIA\u0002`IE\nAb\u001c9uS>t7\t\\1tg\u0002\n\"AR%\u0011\u0005q:\u0015B\u0001%>\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0010&\n\u0005-k$aA!os\u0006YQ-\u001b;iKJ\u001cE.Y:t+\u0005q\u0005cA\u001a9\u001fB\u001a\u0001+X1\u0011\tEKF\f\u0019\b\u0003%^s!a\u0015,\u000e\u0003QS!!\u0016\u0012\u0002\rq\u0012xn\u001c;?\u0013\u0005I\u0012B\u0001->\u0003\u001d\u0001\u0018mY6bO\u0016L!AW.\u0003\r\u0015KG\u000f[3s\u0015\tAV\b\u0005\u0002B;\u0012Ia,BA\u0001\u0002\u0003\u0015\t!\u0012\u0002\u0004?\u0012\u0012\u0014\u0001D3ji\",'o\u00117bgN\u0004\u0003CA!b\t%\u0011W!!A\u0001\u0002\u000b\u0005QIA\u0002`IM\n\u0001\"\\1q\u00072\f7o]\u000b\u0002KB\u00191\u0007\u000f41\u0007\u001dt'\u000f\u0005\u0003iW6\fX\"A5\u000b\u0005)l\u0014AC2pY2,7\r^5p]&\u0011A.\u001b\u0002\u0004\u001b\u0006\u0004\bCA!o\t%yw!!A\u0001\u0002\u000b\u0005QIA\u0002`IQ\n\u0011\"\\1q\u00072\f7o\u001d\u0011\u0011\u0005\u0005\u0013H!C:\b\u0003\u0003\u0005\tQ!\u0001F\u0005\ryF%N\u0001\tS:$8\t\\1tgV\ta\u000fE\u00024q]\u0004\"\u0001\u0010=\n\u0005el$aA%oi\u0006I\u0011N\u001c;DY\u0006\u001c8\u000fI\u0001\fS:$X*\u00199DY\u0006\u001c8/F\u0001~!\r\u0019\u0004H \u0019\u0004\u007f\u00065\u0001CBA\u0001\u0003\u000f\tY!\u0004\u0002\u0002\u0004)\u0019\u0011QA5\u0002\u0013%lW.\u001e;bE2,\u0017\u0002BA\u0005\u0003\u0007\u0011a!\u00138u\u001b\u0006\u0004\bcA!\u0002\u000e\u0011Q\u0011qB\u0006\u0002\u0002\u0003\u0005)\u0011A#\u0003\u0007}#c'\u0001\u0007j]Rl\u0015\r]\"mCN\u001c\b%A\u0005m_:<7\t\\1tgV\u0011\u0011q\u0003\t\u0005ga\nI\u0002E\u0002=\u00037I1!!\b>\u0005\u0011auN\\4\u0002\u00151|gnZ\"mCN\u001c\b%A\u000bj[6,H/\u00192mK2{gnZ'ba\u000ec\u0017m]:\u0016\u0005\u0005\u0015\u0002\u0003B\u001a9\u0003O\u0001D!!\u000b\u00022A1\u0011\u0011AA\u0016\u0003_IA!!\f\u0002\u0004\t9Aj\u001c8h\u001b\u0006\u0004\bcA!\u00022\u0011Q\u00111G\b\u0002\u0002\u0003\u0005)\u0011A#\u0003\u0007}#s'\u0001\fj[6,H/\u00192mK2{gnZ'ba\u000ec\u0017m]:!\u0003MiW\u000f^1cY\u0016duN\\4NCB\u001cE.Y:t+\t\tY\u0004\u0005\u00034q\u0005u\u0002\u0007BA \u0003\u0017\u0002b!!\u0011\u0002H\u0005%SBAA\"\u0015\r\t)%[\u0001\b[V$\u0018M\u00197f\u0013\u0011\ti#a\u0011\u0011\u0007\u0005\u000bY\u0005\u0002\u0006\u0002NE\t\t\u0011!A\u0003\u0002\u0015\u00131a\u0018\u00139\u0003QiW\u000f^1cY\u0016duN\\4NCB\u001cE.Y:tA\u0005\t\u0012\u000e^3sC\ndWm\u00148dK\u000ec\u0017m]:\u0016\u0005\u0005U\u0003\u0003B\u001a9\u0003/\u0002D!!\u0017\u0002bA)\u0001.a\u0017\u0002`%\u0019\u0011QL5\u0003\u0019%#XM]1cY\u0016|enY3\u0011\u0007\u0005\u000b\t\u0007\u0002\u0006\u0002dM\t\t\u0011!A\u0003\u0002\u0015\u00131a\u0018\u0013:\u0003IIG/\u001a:bE2,wJ\\2f\u00072\f7o\u001d\u0011\u0002\u00155|G-\u001b4z)f\u0004X\r\u0006\u0006\u0002l\u0005M\u0014qOAD\u0003#\u0003B!!\u001c\u0002p5\t\u0001&C\u0002\u0002r!\u0012\u0001BS1wCRK\b/\u001a\u0005\b\u0003k\"\u0002\u0019AA6\u0003!Q\u0017M^1UsB,\u0007bBA=)\u0001\u0007\u00111P\u0001\bU\u0012\\G+\u001f9f!\u0011\ti(a!\u000e\u0005\u0005}$bAAAi\u00059!/\u001a4mK\u000e$\u0018\u0002BAC\u0003\u007f\u0012A\u0001V=qK\"9\u0011\u0011\u0012\u000bA\u0002\u0005-\u0015aB2p]R,\u0007\u0010\u001e\t\u0004K\u00055\u0015bAAHM\taA+\u001f9f\u0005&tG-\u001b8hg\"9\u00111\u0013\u000bA\u0002\u0005U\u0015a\u0003;za\u00164\u0015m\u0019;pef\u00042!JAL\u0013\r\tIJ\n\u0002\f)f\u0004XMR1di>\u0014\u0018\u0010"
)
public class ScalaTypeModifier extends TypeModifier {
   private final Class optionClass = Option.class;
   private final Class eitherClass = Either.class;
   private final Class mapClass = Map.class;
   private final Class intClass;
   private final Class intMapClass;
   private final Class longClass;
   private final Class immutableLongMapClass;
   private final Class mutableLongMapClass;
   private final Class iterableOnceClass;

   private Class optionClass() {
      return this.optionClass;
   }

   private Class eitherClass() {
      return this.eitherClass;
   }

   private Class mapClass() {
      return this.mapClass;
   }

   private Class intClass() {
      return this.intClass;
   }

   private Class intMapClass() {
      return this.intMapClass;
   }

   private Class longClass() {
      return this.longClass;
   }

   private Class immutableLongMapClass() {
      return this.immutableLongMapClass;
   }

   private Class mutableLongMapClass() {
      return this.mutableLongMapClass;
   }

   private Class iterableOnceClass() {
      return this.iterableOnceClass;
   }

   public JavaType modifyType(final JavaType javaType, final Type jdkType, final TypeBindings context, final TypeFactory typeFactory) {
      if (javaType.isTypeOrSubTypeOf(this.optionClass())) {
         if (javaType instanceof ReferenceType) {
            ReferenceType var7 = (ReferenceType)javaType;
            return var7;
         } else {
            return ReferenceType.upgradeFrom(javaType, javaType.containedTypeOrUnknown(0));
         }
      } else if (javaType.isTypeOrSubTypeOf(this.mapClass())) {
         if (javaType.isTypeOrSubTypeOf(this.intMapClass())) {
            return MapLikeType.upgradeFrom(javaType, typeFactory.constructType(this.intClass()), javaType.containedTypeOrUnknown(0));
         } else {
            return !javaType.isTypeOrSubTypeOf(this.immutableLongMapClass()) && !javaType.isTypeOrSubTypeOf(this.mutableLongMapClass()) ? MapLikeType.upgradeFrom(javaType, javaType.containedTypeOrUnknown(0), javaType.containedTypeOrUnknown(1)) : MapLikeType.upgradeFrom(javaType, typeFactory.constructType(this.longClass()), javaType.containedTypeOrUnknown(0));
         }
      } else if (javaType.isTypeOrSubTypeOf(this.iterableOnceClass())) {
         return CollectionLikeType.upgradeFrom(javaType, javaType.containedTypeOrUnknown(0));
      } else {
         return (JavaType)(javaType.isTypeOrSubTypeOf(this.eitherClass()) ? ReferenceType.upgradeFrom(javaType, javaType) : javaType);
      }
   }

   public ScalaTypeModifier() {
      this.intClass = Integer.TYPE;
      this.intMapClass = IntMap.class;
      this.longClass = Long.TYPE;
      this.immutableLongMapClass = LongMap.class;
      this.mutableLongMapClass = scala.collection.mutable.LongMap.class;
      this.iterableOnceClass = IterableOnce.class;
   }
}
