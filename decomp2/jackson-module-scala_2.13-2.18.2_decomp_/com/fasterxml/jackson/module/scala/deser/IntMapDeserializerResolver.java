package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.MapDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.immutable.IntMap;
import scala.collection.immutable.IntMap.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t%sAB\u0010!\u0011\u0003\u0001CF\u0002\u0004/A!\u0005\u0001e\f\u0005\u0006w\u0005!\t!\u0010\u0005\b}\u0005\u0011\r\u0011\"\u0003@\u0011\u00191\u0016\u0001)A\u0005\u0001\")q,\u0001C!A\u001a1\u0011qD\u0001\u0005\u0003CA\u0011\"a\u0010\u0007\u0005\u0003\u0005\u000b\u0011B6\t\u0015\u0005\u0005cA!A!\u0002\u0013\t\u0019\u0005\u0003\u0004<\r\u0011\u0005\u0011\u0011\n\u0005\b\u0003'2A\u0011IA+\u0011\u001d\tiF\u0002C!\u0003?Bq!!\u001b\u0007\t\u0003\nY\u0007C\u0004\u0002\f\u001a!\t%!$\t\u000f\u0005-e\u0001\"\u0011\u0002\"\"9\u00111\u0016\u0004\u0005B\u00055fABA\\\u0003\u0011\tI\f\u0003\u0005s!\t\u0005\t\u0015!\u0003t\u0011%\ty\u0004\u0005B\u0001B\u0003%1\u000e\u0003\u0004<!\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u0013\u0004B\u0011IAf\u0011\u001d\t\u0019\u000e\u0005C!\u0003+4a!!7\u0002\t\u0005m\u0007BB\u001e\u0017\t\u0003\tI\u000fC\u0005\u0002lZ\u0001\r\u0011\"\u0001\u0002n\"I\u0011\u0011\u001f\fA\u0002\u0013\u0005\u00111\u001f\u0005\t\u0003\u007f4\u0002\u0015)\u0003\u0002p\"9!\u0011\u0001\f\u0005B\t\r\u0001b\u0002B\u0007-\u0011\u0005#q\u0002\u0005\b\u0005+1B\u0011\tB\f\u0011\u001d\u0011YD\u0006C\u0001\u0005{\t!$\u00138u\u001b\u0006\u0004H)Z:fe&\fG.\u001b>feJ+7o\u001c7wKJT!!\t\u0012\u0002\u000b\u0011,7/\u001a:\u000b\u0005\r\"\u0013!B:dC2\f'BA\u0013'\u0003\u0019iw\u000eZ;mK*\u0011q\u0005K\u0001\bU\u0006\u001c7n]8o\u0015\tI#&A\u0005gCN$XM\u001d=nY*\t1&A\u0002d_6\u0004\"!L\u0001\u000e\u0003\u0001\u0012!$\u00138u\u001b\u0006\u0004H)Z:fe&\fG.\u001b>feJ+7o\u001c7wKJ\u001c\"!\u0001\u0019\u0011\u0005EBdB\u0001\u001a7\u001b\u0005\u0019$BA\u00115\u0015\t)d%\u0001\u0005eCR\f'-\u001b8e\u0013\t94'A\u0007EKN,'/[1mSj,'o]\u0005\u0003si\u0012AAQ1tK*\u0011qgM\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA&A\u0006j]Rl\u0015\r]\"mCN\u001cX#\u0001!\u0011\u0007\u00053\u0005*D\u0001C\u0015\t\u0019E)\u0001\u0003mC:<'\"A#\u0002\t)\fg/Y\u0005\u0003\u000f\n\u0013Qa\u00117bgN\u0004$!\u0013+\u0011\u0007)\u0003&+D\u0001L\u0015\taU*A\u0005j[6,H/\u00192mK*\u0011ajT\u0001\u000bG>dG.Z2uS>t'\"A\u0012\n\u0005E[%AB%oi6\u000b\u0007\u000f\u0005\u0002T)2\u0001A!C+\u0005\u0003\u0003\u0005\tQ!\u0001X\u0005\ryF%M\u0001\rS:$X*\u00199DY\u0006\u001c8\u000fI\t\u00031r\u0003\"!\u0017.\u000e\u0003=K!aW(\u0003\u000f9{G\u000f[5oOB\u0011\u0011,X\u0005\u0003=>\u00131!\u00118z\u0003]1\u0017N\u001c3NCBd\u0015n[3EKN,'/[1mSj,'\u000fF\u0005bSF480!\u0001\u0002\u0012A\u0012!m\u001a\t\u0004G\u00124W\"\u0001\u001b\n\u0005\u0015$$\u0001\u0005&t_:$Um]3sS\u0006d\u0017N_3s!\t\u0019v\rB\u0005i\u000b\u0005\u0005\t\u0011!B\u0001/\n\u0019q\fJ\u001a\t\u000b),\u0001\u0019A6\u0002\u000fQDW\rV=qKB\u0011An\\\u0007\u0002[*\u0011a\u000eN\u0001\u0005if\u0004X-\u0003\u0002q[\nYQ*\u00199MS.,G+\u001f9f\u0011\u0015\u0011X\u00011\u0001t\u0003\u0019\u0019wN\u001c4jOB\u00111\r^\u0005\u0003kR\u0012Q\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]\u001aLw\rC\u0003x\u000b\u0001\u0007\u00010\u0001\u0005cK\u0006tG)Z:d!\t\u0019\u00170\u0003\u0002{i\ty!)Z1o\t\u0016\u001c8M]5qi&|g\u000eC\u0003}\u000b\u0001\u0007Q0A\blKf$Um]3sS\u0006d\u0017N_3s!\t\u0019g0\u0003\u0002\u0000i\ty1*Z=EKN,'/[1mSj,'\u000fC\u0004\u0002\u0004\u0015\u0001\r!!\u0002\u0002/\u0015dW-\\3oiRK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\b\u0003BA\u0004\u0003\u001bi!!!\u0003\u000b\u0007\u0005-A'\u0001\u0005kg>tG/\u001f9f\u0013\u0011\ty!!\u0003\u0003!QK\b/\u001a#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014\bbBA\n\u000b\u0001\u0007\u0011QC\u0001\u0014K2,W.\u001a8u\t\u0016\u001cXM]5bY&TXM\u001d\u0019\u0005\u0003/\tY\u0002\u0005\u0003dI\u0006e\u0001cA*\u0002\u001c\u0011Y\u0011QDA\t\u0003\u0003\u0005\tQ!\u0001X\u0005\ryFE\r\u0002\u0013\u0013:$X*\u00199EKN,'/[1mSj,'/\u0006\u0003\u0002$\u0005U2#\u0002\u0004\u0002&\u0005e\u0002CBA\u0014\u0003[\t\t$\u0004\u0002\u0002*)\u0019\u00111F\u001a\u0002\u0007M$H-\u0003\u0003\u00020\u0005%\"!G\"p]R\f\u0017N\\3s\t\u0016\u001cXM]5bY&TXM\u001d\"bg\u0016\u0004BA\u0013)\u00024A\u00191+!\u000e\u0005\r\u0005]bA1\u0001X\u0005\u00051\u0006c\u0001\u001a\u0002<%\u0019\u0011QH\u001a\u0003-\r{g\u000e^3yiV\fG\u000eR3tKJL\u0017\r\\5{KJ\fq!\\1q)f\u0004X-A\u000bd_:$\u0018-\u001b8fe\u0012+7/\u001a:jC2L'0\u001a:\u0011\t\u0005\u001d\u0012QI\u0005\u0005\u0003\u000f\nICA\bNCB$Um]3sS\u0006d\u0017N_3s)\u0019\tY%a\u0014\u0002RA)\u0011Q\n\u0004\u000245\t\u0011\u0001\u0003\u0004\u0002@%\u0001\ra\u001b\u0005\b\u0003\u0003J\u0001\u0019AA\"\u000399W\r^\"p]R,g\u000e\u001e+za\u0016$\"!a\u0016\u0011\u0007\r\fI&C\u0002\u0002\\Q\u0012\u0001BS1wCRK\b/Z\u0001\u0017O\u0016$8i\u001c8uK:$H)Z:fe&\fG.\u001b>feR\u0011\u0011\u0011\r\t\u0005G\u0012\f\u0019\u0007E\u0002Z\u0003KJ1!a\u001aP\u0005\u0019\te.\u001f*fM\u0006\u00012M]3bi\u0016\u001cuN\u001c;fqR,\u0018\r\u001c\u000b\u0007\u0003[\n9(!!1\t\u0005=\u00141\u000f\t\u0005G\u0012\f\t\bE\u0002T\u0003g\"!\"!\u001e\r\u0003\u0003\u0005\tQ!\u0001X\u0005\ryF\u0005\u000e\u0005\b\u0003sb\u0001\u0019AA>\u0003\u0011\u0019G\u000f\u001f;\u0011\u0007\r\fi(C\u0002\u0002\u0000Q\u0012a\u0003R3tKJL\u0017\r\\5{CRLwN\\\"p]R,\u0007\u0010\u001e\u0005\b\u0003\u0007c\u0001\u0019AAC\u0003!\u0001(o\u001c9feRL\bcA2\u0002\b&\u0019\u0011\u0011\u0012\u001b\u0003\u0019\t+\u0017M\u001c)s_B,'\u000f^=\u0002\u0017\u0011,7/\u001a:jC2L'0\u001a\u000b\u0007\u0003c\ty)a(\t\u000f\u0005EU\u00021\u0001\u0002\u0014\u0006\u0011!\u000e\u001d\t\u0005\u0003+\u000bY*\u0004\u0002\u0002\u0018*\u0019\u0011\u0011\u0014\u0014\u0002\t\r|'/Z\u0005\u0005\u0003;\u000b9J\u0001\u0006Kg>t\u0007+\u0019:tKJDq!!\u001f\u000e\u0001\u0004\tY\b\u0006\u0005\u00022\u0005\r\u0016QUAT\u0011\u001d\t\tJ\u0004a\u0001\u0003'Cq!!\u001f\u000f\u0001\u0004\tY\bC\u0004\u0002*:\u0001\r!!\r\u0002\u0013%tGo\u001c,bYV,\u0017!D4fi\u0016k\u0007\u000f^=WC2,X\r\u0006\u0003\u00020\u0006U\u0006cA!\u00022&\u0019\u00111\u0017\"\u0003\r=\u0013'.Z2u\u0011\u001d\tIh\u0004a\u0001\u0003w\u0012!#\u00138u\u001b\u0006\u0004\u0018J\\:uC:$\u0018.\u0019;peN\u0019\u0001#a/\u0011\t\u0005\u001d\u0012QX\u0005\u0005\u0003\u007f\u000bIC\u0001\u000bTi\u00124\u0016\r\\;f\u0013:\u001cH/\u00198uS\u0006$xN\u001d\u000b\u0007\u0003\u0007\f)-a2\u0011\u0007\u00055\u0003\u0003C\u0003s'\u0001\u00071\u000f\u0003\u0004\u0002@M\u0001\ra[\u0001\u0016G\u0006t7I]3bi\u0016,6/\u001b8h\t\u00164\u0017-\u001e7u)\t\ti\rE\u0002Z\u0003\u001fL1!!5P\u0005\u001d\u0011un\u001c7fC:\f!c\u0019:fCR,Wk]5oO\u0012+g-Y;miR!\u0011q\u001bB$!\r\tiE\u0006\u0002\u000f\u0005VLG\u000eZ3s/J\f\u0007\u000f]3s'\r1\u0012Q\u001c\t\t\u0003?\f)/a,\u000206\u0011\u0011\u0011\u001d\u0006\u0004\u0003G$\u0015\u0001B;uS2LA!a:\u0002b\nY\u0011IY:ue\u0006\u001cG/T1q)\t\t9.A\u0004cCN,W*\u00199\u0016\u0005\u0005=\b\u0003\u0002&Q\u0003_\u000b1BY1tK6\u000b\u0007o\u0018\u0013fcR!\u0011Q_A~!\rI\u0016q_\u0005\u0004\u0003s|%\u0001B+oSRD\u0011\"!@\u001a\u0003\u0003\u0005\r!a<\u0002\u0007a$\u0013'\u0001\u0005cCN,W*\u00199!\u0003\r\u0001X\u000f\u001e\u000b\u0007\u0003_\u0013)A!\u0003\t\u000f\t\u001d1\u00041\u0001\u00020\u0006\t1\u000eC\u0004\u0003\fm\u0001\r!a,\u0002\u0003Y\f1aZ3u)\u0011\tyK!\u0005\t\u000f\tMA\u00041\u0001\u00020\u0006\u00191.Z=\u0002\u0011\u0015tGO]=TKR$\"A!\u0007\u0011\r\u0005}'1\u0004B\u0010\u0013\u0011\u0011i\"!9\u0003\u0007M+G\u000f\u0005\u0005\u0003\"\tU\u0012qVAX\u001d\u0011\u0011\u0019C!\r\u000f\t\t\u0015\"q\u0006\b\u0005\u0005O\u0011i#\u0004\u0002\u0003*)\u0019!1\u0006\u001f\u0002\rq\u0012xn\u001c;?\u0013\u0005)\u0015bAAr\t&!!1GAq\u0003\ri\u0015\r]\u0005\u0005\u0005o\u0011IDA\u0003F]R\u0014\u0018P\u0003\u0003\u00034\u0005\u0005\u0018\u0001C1t\u0013:$X*\u00199\u0016\t\t}\"Q\t\u000b\u0003\u0005\u0003\u0002BA\u0013)\u0003DA\u00191K!\u0012\u0005\r\u0005]bD1\u0001X\u0011\u001d\tI(\u0006a\u0001\u0003w\u0002"
)
public final class IntMapDeserializerResolver {
   public static JsonDeserializer findMapLikeDeserializer(final MapLikeType theType, final DeserializationConfig config, final BeanDescription beanDesc, final KeyDeserializer keyDeserializer, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      return IntMapDeserializerResolver$.MODULE$.findMapLikeDeserializer(theType, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
   }

   public static JsonDeserializer findMapDeserializer(final MapType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final KeyDeserializer x$4, final TypeDeserializer x$5, final JsonDeserializer x$6) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findMapDeserializer(x$1, x$2, x$3, x$4, x$5, x$6);
   }

   public static JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findCollectionLikeDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findCollectionDeserializer(final CollectionType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findCollectionDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findArrayDeserializer(final ArrayType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findArrayDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findBeanDeserializer(final JavaType x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findBeanDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findReferenceDeserializer(final ReferenceType x$1, final DeserializationConfig x$2, final BeanDescription x$3, final TypeDeserializer x$4, final JsonDeserializer x$5) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findReferenceDeserializer(x$1, x$2, x$3, x$4, x$5);
   }

   public static JsonDeserializer findTreeNodeDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findTreeNodeDeserializer(x$1, x$2, x$3);
   }

   public static JsonDeserializer findEnumDeserializer(final Class x$1, final DeserializationConfig x$2, final BeanDescription x$3) throws JsonMappingException {
      return IntMapDeserializerResolver$.MODULE$.findEnumDeserializer(x$1, x$2, x$3);
   }

   public static boolean hasDeserializerFor(final DeserializationConfig x$1, final Class x$2) {
      return IntMapDeserializerResolver$.MODULE$.hasDeserializerFor(x$1, x$2);
   }

   private static class IntMapDeserializer extends ContainerDeserializerBase implements ContextualDeserializer {
      private final MapLikeType mapType;
      private final MapDeserializer containerDeserializer;

      public JavaType getContentType() {
         return this.containerDeserializer.getContentType();
      }

      public JsonDeserializer getContentDeserializer() {
         return this.containerDeserializer.getContentDeserializer();
      }

      public JsonDeserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
         MapDeserializer newDelegate = (MapDeserializer)this.containerDeserializer.createContextual(ctxt, property);
         return new IntMapDeserializer(this.mapType, newDelegate);
      }

      public IntMap deserialize(final JsonParser jp, final DeserializationContext ctxt) {
         Map var4 = this.containerDeserializer.deserialize(jp, ctxt);
         if (var4 instanceof BuilderWrapper) {
            BuilderWrapper var5 = (BuilderWrapper)var4;
            return var5.asIntMap();
         } else {
            throw new MatchError(var4);
         }
      }

      public IntMap deserialize(final JsonParser jp, final DeserializationContext ctxt, final IntMap intoValue) {
         IntMap newMap = this.deserialize(jp, ctxt);
         return newMap.isEmpty() ? intoValue : intoValue.$plus$plus(newMap);
      }

      public Object getEmptyValue(final DeserializationContext ctxt) {
         return .MODULE$.empty();
      }

      public IntMapDeserializer(final MapLikeType mapType, final MapDeserializer containerDeserializer) {
         super(mapType);
         this.mapType = mapType;
         this.containerDeserializer = containerDeserializer;
      }
   }

   private static class IntMapInstantiator extends StdValueInstantiator {
      public boolean canCreateUsingDefault() {
         return true;
      }

      public BuilderWrapper createUsingDefault(final DeserializationContext ctxt) {
         return new BuilderWrapper();
      }

      public IntMapInstantiator(final DeserializationConfig config, final MapLikeType mapType) {
         super(config, mapType);
      }
   }

   private static class BuilderWrapper extends AbstractMap {
      private IntMap baseMap;

      public IntMap baseMap() {
         return this.baseMap;
      }

      public void baseMap_$eq(final IntMap x$1) {
         this.baseMap = x$1;
      }

      public Object put(final Object k, final Object v) {
         if (k instanceof Number) {
            Number var6 = (Number)k;
            int i = var6.intValue();
            Option oldValue = this.baseMap().get(i);
            this.baseMap_$eq(this.baseMap().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i)), v)));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else if (k instanceof String) {
            String var9 = (String)k;
            int i = scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(var9));
            Option oldValue = this.baseMap().get(i);
            this.baseMap_$eq(this.baseMap().$plus(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(i)), v)));
            return oldValue.orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            Option var13 = scala.Option..MODULE$.apply(k);
            String var10000;
            if (var13 instanceof Some) {
               Some var14 = (Some)var13;
               Object n = var14.value();
               var10000 = n.getClass().getName();
            } else {
               var10000 = "null";
            }

            String typeName = var10000;
            throw new IllegalArgumentException((new StringBuilder(37)).append("IntMap does not support keys of type ").append(typeName).toString());
         }
      }

      public Object get(final Object key) {
         if (key instanceof Number) {
            Number var4 = (Number)key;
            return this.baseMap().get(var4.intValue()).orNull(scala..less.colon.less..MODULE$.refl());
         } else if (key instanceof String) {
            String var5 = (String)key;
            return this.baseMap().get(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(var5))).orNull(scala..less.colon.less..MODULE$.refl());
         } else {
            return scala.None..MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
         }
      }

      public Set entrySet() {
         return ((Map)scala.collection.JavaConverters..MODULE$.mapAsJavaMapConverter(this.baseMap()).asJava()).entrySet();
      }

      public IntMap asIntMap() {
         return this.baseMap();
      }

      public BuilderWrapper() {
         this.baseMap = .MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      }
   }
}
