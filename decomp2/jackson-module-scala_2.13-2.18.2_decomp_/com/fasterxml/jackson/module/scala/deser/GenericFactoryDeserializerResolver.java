package com.fasterxml.jackson.module.scala.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import java.lang.invoke.SerializedLambda;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import scala.MatchError;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rEb!B\u0015+\u0003\u00039\u0004\"\u0002#\u0001\t\u0003)U\u0001B1\u0001\u0001\t,Aa\u001a\u0001\u0001Q\u0016!\u0011\u000e\u0001\u0001k\u0011\u001dY\bA1A\u0007\u0002qD\u0011\"!\u0007\u0001\u0005\u00045\t!a\u0007\t\u000f\u0005\u0005\u0003A\"\u0001\u0002D!9\u0011\u0011\t\u0001\u0005\u0002\u0005u\u0003bBA<\u0001\u0011\u0005\u0013\u0011\u0010\u0005\b\u0003\u0017\u0004A\u0011CAg\u0011\u001d\t)\u0010\u0001C\u0005\u0003o4aA!\u0004\u0001\t\t=\u0001B\u0003B\u0014\u0019\t\u0015\r\u0011\"\u0001\u0003*!Q!Q\u0006\u0007\u0003\u0002\u0003\u0006IAa\u000b\t\r\u0011cA\u0011\u0001B\u0018\u0011%\u0011)\u0004\u0004a\u0001\n\u0003\u00119\u0004C\u0005\u0003:1\u0001\r\u0011\"\u0001\u0003<!A!q\t\u0007!B\u0013\tI\u0010C\u0004\u0003J1!\tEa\u0013\t\u000f\t]C\u0002\"\u0011\u0003Z!9!\u0011\r\u0007\u0005\u0002\t\rdA\u0002B6\u0001\u0011\u0011i\u0007\u0003\u0006\u0002\u001cZ\u0011\t\u0011)A\u0005\u0003;C!\"a#\u0017\u0005\u0003\u0005\u000b\u0011BA+\u0011)\t\u0019F\u0006B\u0001B\u0003%\u0011Q\u000b\u0005\u0007\tZ!\tAa\u001f\t\u000f\t\u0015e\u0003\"\u0011\u0003\b\"9!\u0011\u0012\f\u0005B\t-eA\u0002BP\u0001\u0011\u0011\t\u000b\u0003\u0006\u0002\fv\u0011\t\u0011)A\u0005\u0003+B!Ba.\u001e\u0005\u0003\u0005\u000b\u0011\u0002B]\u0011\u0019!U\u0004\"\u0001\u0003@\"1A)\bC\u0001\u0005\u000fDqAa;\u001e\t\u0003\u0012i\u000fC\u0004\u0003|v!\tE!@\t\u000f\t}X\u0004\"\u0011\u0004\u0002!91QA\u000f\u0005B\r\u001d\u0001bBB\u0003;\u0011\u000531\u0004\u0005\b\u0007KiB\u0011IB\u0014\u0011\u001d\u0019Y#\bC\u0005\u0007[\u0011!eR3oKJL7MR1di>\u0014\u0018\u0010R3tKJL\u0017\r\\5{KJ\u0014Vm]8mm\u0016\u0014(BA\u0016-\u0003\u0015!Wm]3s\u0015\tic&A\u0003tG\u0006d\u0017M\u0003\u00020a\u00051Qn\u001c3vY\u0016T!!\r\u001a\u0002\u000f)\f7m[:p]*\u00111\u0007N\u0001\nM\u0006\u001cH/\u001a:y[2T\u0011!N\u0001\u0004G>l7\u0001A\u000b\u0004q)K6C\u0001\u0001:!\tQ\u0014I\u0004\u0002<\u007f5\tAH\u0003\u0002,{)\u0011a\bM\u0001\tI\u0006$\u0018MY5oI&\u0011\u0001\tP\u0001\u000e\t\u0016\u001cXM]5bY&TXM]:\n\u0005\t\u001b%\u0001\u0002\"bg\u0016T!\u0001\u0011\u001f\u0002\rqJg.\u001b;?)\u00051\u0005\u0003B$\u0001\u0011bk\u0011A\u000b\t\u0003\u0013*c\u0001\u0001B\u0003L\u0001\t\u0007AJ\u0001\u0002D\u0007V\u0011QJV\t\u0003\u001dN\u0003\"aT)\u000e\u0003AS\u0011!L\u0005\u0003%B\u0013qAT8uQ&tw\r\u0005\u0002P)&\u0011Q\u000b\u0015\u0002\u0004\u0003:LH!B,K\u0005\u0004i%\u0001B0%IE\u0002\"!S-\u0005\u000bi\u0003!\u0019A.\u0003\u0005\r3UCA']\t\u0015i\u0016L1\u0001_\u0005\u0005AVCA'`\t\u0015\u0001GL1\u0001N\u0005\u0011yF\u0005\n\u001a\u0003\u0015\r{G\u000e\\3di&|g.\u0006\u0002dKB\u0019\u0011J\u00133\u0011\u0005%+G!\u00024\u0003\u0005\u0004i%!A!\u0003\u000f\u0019\u000b7\r^8ssB\u0019\u0011*\u0017%\u0003\u000f\t+\u0018\u000e\u001c3feV\u00111\u000e\u001e\u0019\u0003YZ\u0004B!\u001c:tk6\taN\u0003\u0002pa\u00069Q.\u001e;bE2,'BA9Q\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003S:\u0004\"!\u0013;\u0005\u000b\u0019$!\u0019A'\u0011\u0005%3H!C<\u0005\u0003\u0003\u0005\tQ!\u0001y\u0005\ryF%M\t\u0003\u001df\u00042A\u001f\u0002t\u001b\u0005\u0001\u0011\u0001D\"M\u0003N\u001bv\fR(N\u0003&sU#A?1\u0007y\f)\u0002E\u0003\u0000\u0003\u001b\t\u0019B\u0004\u0003\u0002\u0002\u0005%\u0001cAA\u0002!6\u0011\u0011Q\u0001\u0006\u0004\u0003\u000f1\u0014A\u0002\u001fs_>$h(C\u0002\u0002\fA\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\b\u0003#\u0011Qa\u00117bgNT1!a\u0003Q!\rI\u0015Q\u0003\u0003\u000b\u0003/)\u0011\u0011!A\u0001\u0006\u0003i%aA0%e\u0005Ia-Y2u_JLWm]\u000b\u0003\u0003;\u0001b!a\b\u0002*\u0005=b\u0002BA\u0011\u0003KqA!a\u0001\u0002$%\tQ&C\u0002\u0002(A\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002,\u00055\"\u0001C%uKJ\f'\r\\3\u000b\u0007\u0005\u001d\u0002\u000bE\u0004P\u0003c\t)$a\u0010\n\u0007\u0005M\u0002K\u0001\u0004UkBdWM\r\u0019\u0005\u0003o\tY\u0004E\u0003\u0000\u0003\u001b\tI\u0004E\u0002J\u0003w!!\"!\u0010\u0007\u0003\u0003\u0005\tQ!\u0001N\u0005\ryFe\r\t\u0003u\u000e\t!BY;jY\u0012,'OR8s+\u0011\t)%a\u0013\u0015\r\u0005\u001d\u0013QJA)!\u0011QH!!\u0013\u0011\u0007%\u000bY\u0005B\u0003g\u000f\t\u0007Q\nC\u0004\u0002P\u001d\u0001\r!a\u0010\u0002\u0005\r4\u0007bBA*\u000f\u0001\u0007\u0011QK\u0001\nm\u0006dW/\u001a+za\u0016\u0004B!a\u0016\u0002Z5\tQ(C\u0002\u0002\\u\u0012\u0001BS1wCRK\b/Z\u000b\u0005\u0003?\n)\u0007\u0006\u0004\u0002b\u0005\u001d\u0014Q\u000f\t\u0005u\u0012\t\u0019\u0007E\u0002J\u0003K\"QA\u001a\u0005C\u00025Cq!!\u001b\t\u0001\u0004\tY'A\u0002dYN\u0004D!!\u001c\u0002rA)q0!\u0004\u0002pA\u0019\u0011*!\u001d\u0005\u0017\u0005M\u0014qMA\u0001\u0002\u0003\u0015\t!\u0014\u0002\u0004?\u0012\"\u0004bBA*\u0011\u0001\u0007\u0011QK\u0001\u001fM&tGmQ8mY\u0016\u001cG/[8o\u0019&\\W\rR3tKJL\u0017\r\\5{KJ$B\"a\u001f\u0002\n\u0006e\u00151UAW\u0003{\u0003D!! \u0002\u0006B1\u0011qKA@\u0003\u0007K1!!!>\u0005AQ5o\u001c8EKN,'/[1mSj,'\u000fE\u0002J\u0003\u000b#!\"a\"\n\u0003\u0003\u0005\tQ!\u0001N\u0005\ryFE\u000e\u0005\b\u0003\u0017K\u0001\u0019AAG\u00039\u0019w\u000e\u001c7fGRLwN\u001c+za\u0016\u0004B!a$\u0002\u00166\u0011\u0011\u0011\u0013\u0006\u0004\u0003'k\u0014\u0001\u0002;za\u0016LA!a&\u0002\u0012\n\u00112i\u001c7mK\u000e$\u0018n\u001c8MS.,G+\u001f9f\u0011\u001d\tY*\u0003a\u0001\u0003;\u000baaY8oM&<\u0007\u0003BA,\u0003?K1!!)>\u0005U!Um]3sS\u0006d\u0017N_1uS>t7i\u001c8gS\u001eDq!!*\n\u0001\u0004\t9+\u0001\u0005cK\u0006tG)Z:d!\u0011\t9&!+\n\u0007\u0005-VHA\bCK\u0006tG)Z:de&\u0004H/[8o\u0011\u001d\ty+\u0003a\u0001\u0003c\u000bq#\u001a7f[\u0016tG\u000fV=qK\u0012+7/\u001a:jC2L'0\u001a:\u0011\t\u0005M\u0016\u0011X\u0007\u0003\u0003kS1!a.>\u0003!Q7o\u001c8usB,\u0017\u0002BA^\u0003k\u0013\u0001\u0003V=qK\u0012+7/\u001a:jC2L'0\u001a:\t\u000f\u0005}\u0016\u00021\u0001\u0002B\u0006\u0019R\r\\3nK:$H)Z:fe&\fG.\u001b>feB\"\u00111YAd!\u0019\t9&a \u0002FB\u0019\u0011*a2\u0005\u0017\u0005%\u0017QXA\u0001\u0002\u0003\u0015\t!\u0014\u0002\u0004?\u0012*\u0014!D:peR4\u0015m\u0019;pe&,7\u000f\u0006\u0003\u0002P\u0006\u0005\bCBA\u0010\u0003#\f).\u0003\u0003\u0002T\u00065\"aA*fcB9q*!\r\u0002X\u0006}\u0002\u0007BAm\u0003;\u0004Ra`A\u0007\u00037\u00042!SAo\t)\tyNCA\u0001\u0002\u0003\u0015\t!\u0014\u0002\u0004?\u0012B\u0004bBA\r\u0015\u0001\u0007\u00111\u001d\t\u0007\u0003?\t)/!;\n\t\u0005\u001d\u0018Q\u0006\u0002\u000b\u0013:$W\r_3e'\u0016\f\bcB(\u00022\u0005-\u0018q\b\u0019\u0005\u0003[\f\t\u0010E\u0003\u0000\u0003\u001b\ty\u000fE\u0002J\u0003c$1\"a=\u0002b\u0006\u0005\t\u0011!B\u0001\u001b\n\u0019q\fJ\u001c\u0002\u0015\u0011|G\u000f\u0015:pIV\u001cG\u000f\u0006\u0004\u0002z\u0006}(\u0011\u0002\t\u0004\u001f\u0006m\u0018bAA\u007f!\n\u0019\u0011J\u001c;\t\u000f\t\u00051\u00021\u0001\u0003\u0004\u0005\t\u0011\rE\u0003P\u0005\u000b\tI0C\u0002\u0003\bA\u0013Q!\u0011:sCfDqAa\u0003\f\u0001\u0004\u0011\u0019!A\u0001c\u00059\u0011U/\u001b7eKJ<&/\u00199qKJ,BA!\u0005\u0003&M\u0019ABa\u0005\u0011\r\tU!q\u0004B\u0012\u001b\t\u00119B\u0003\u0003\u0003\u001a\tm\u0011\u0001B;uS2T!A!\b\u0002\t)\fg/Y\u0005\u0005\u0005C\u00119B\u0001\nBEN$(/Y2u\u0007>dG.Z2uS>t\u0007cA%\u0003&\u0011)a\r\u0004b\u0001\u001b\u00069!-^5mI\u0016\u0014XC\u0001B\u0016!\u0011QHAa\t\u0002\u0011\t,\u0018\u000e\u001c3fe\u0002\"BA!\r\u00034A!!\u0010\u0004B\u0012\u0011\u001d\u00119c\u0004a\u0001\u0005W\tAa]5{KV\u0011\u0011\u0011`\u0001\tg&TXm\u0018\u0013fcR!!Q\bB\"!\ry%qH\u0005\u0004\u0005\u0003\u0002&\u0001B+oSRD\u0011B!\u0012\u0012\u0003\u0003\u0005\r!!?\u0002\u0007a$\u0013'A\u0003tSj,\u0007%A\u0002bI\u0012$BA!\u0014\u0003TA\u0019qJa\u0014\n\u0007\tE\u0003KA\u0004C_>dW-\u00198\t\u000f\tU3\u00031\u0001\u0003$\u0005\tQ-\u0001\u0005ji\u0016\u0014\u0018\r^8s)\t\u0011Y\u0006\u0005\u0004\u0003\u0016\tu#1E\u0005\u0005\u0005?\u00129B\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003=\u0019X\r^%oSRL\u0017\r\u001c,bYV,G\u0003\u0002B\u001f\u0005KBqAa\u001a\u0016\u0001\u0004\u0011I'\u0001\u0003j]&$\b\u0003\u0002>\u0003\u0005G\u0011A\"\u00138ti\u0006tG/[1u_J\u001c2A\u0006B8!\u0011\u0011\tHa\u001e\u000e\u0005\tM$b\u0001B;y\u0005\u00191\u000f\u001e3\n\t\te$1\u000f\u0002\u0015'R$g+\u00197vK&s7\u000f^1oi&\fGo\u001c:\u0015\u0011\tu$q\u0010BA\u0005\u0007\u0003\"A\u001f\f\t\u000f\u0005m%\u00041\u0001\u0002\u001e\"9\u00111\u0012\u000eA\u0002\u0005U\u0003bBA*5\u0001\u0007\u0011QK\u0001\u0016G\u0006t7I]3bi\u0016,6/\u001b8h\t\u00164\u0017-\u001e7u)\t\u0011i%\u0001\nde\u0016\fG/Z+tS:<G)\u001a4bk2$H\u0003\u0002BG\u0005+\u0003BA\u001f\u0007\u0003\u0010B\u0019qJ!%\n\u0007\tM\u0005K\u0001\u0004B]f\u0014VM\u001a\u0005\b\u0005/c\u0002\u0019\u0001BM\u0003\u0011\u0019G\u000f\u001f;\u0011\t\u0005]#1T\u0005\u0004\u0005;k$A\u0006#fg\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_:$X\r\u001f;\u0003\u0019\u0011+7/\u001a:jC2L'0\u001a:\u0016\t\t\r&qV\n\u0006;\t\u0015&\u0011\u0017\t\u0007\u0005c\u00129Ka+\n\t\t%&1\u000f\u0002\u001a\u0007>tG/Y5oKJ$Um]3sS\u0006d\u0017N_3s\u0005\u0006\u001cX\r\u0005\u0003J\u0015\n5\u0006cA%\u00030\u0012)a-\bb\u0001\u001bB\u00191Ha-\n\u0007\tUFH\u0001\fD_:$X\r\u001f;vC2$Um]3sS\u0006d\u0017N_3s\u0003U\u0019wN\u001c;bS:,'\u000fR3tKJL\u0017\r\\5{KJ\u0004BA!\u001d\u0003<&!!Q\u0018B:\u0005Y\u0019u\u000e\u001c7fGRLwN\u001c#fg\u0016\u0014\u0018.\u00197ju\u0016\u0014HC\u0002Ba\u0005\u0007\u0014)\r\u0005\u0003{;\t5\u0006bBAFA\u0001\u0007\u0011Q\u000b\u0005\b\u0005o\u0003\u0003\u0019\u0001B]))\u0011\tM!3\u0003L\nu'\u0011\u001d\u0005\b\u0003\u0017\u000b\u0003\u0019AA+\u0011\u001d\u0011i-\ta\u0001\u0005\u001f\f!B^1mk\u0016$Um]3s!\u0019\t9&a \u0003RB!!1\u001bBm\u001b\t\u0011)N\u0003\u0003\u0003X\nm\u0011\u0001\u00027b]\u001eLAAa7\u0003V\n1qJ\u00196fGRDqAa8\"\u0001\u0004\t\t,\u0001\bwC2,X\rV=qK\u0012+7/\u001a:\t\u000f\t\r\u0018\u00051\u0001\u0003f\u0006\tb/\u00197vK&s7\u000f^1oi&\fGo\u001c:\u0011\u0007m\u00129/C\u0002\u0003jr\u0012\u0011CV1mk\u0016Len\u001d;b]RL\u0017\r^8s\u0003A\u0019'/Z1uK\u000e{g\u000e^3yiV\fG\u000e\u0006\u0004\u0003B\n=(\u0011\u001f\u0005\b\u0005/\u0013\u0003\u0019\u0001BM\u0011\u001d\u0011\u0019P\ta\u0001\u0005k\f\u0001\u0002\u001d:pa\u0016\u0014H/\u001f\t\u0005\u0003/\u001290C\u0002\u0003zv\u0012ABQ3b]B\u0013x\u000e]3sif\fabZ3u\u0007>tG/\u001a8u)f\u0004X\r\u0006\u0002\u0002V\u00051r-\u001a;D_:$XM\u001c;EKN,'/[1mSj,'\u000f\u0006\u0002\u0004\u0004A1\u0011qKA@\u0005\u001f\u000b1\u0002Z3tKJL\u0017\r\\5{KR1!1VB\u0005\u00073Aqaa\u0003&\u0001\u0004\u0019i!\u0001\u0002kaB!1qBB\u000b\u001b\t\u0019\tBC\u0002\u0004\u0014A\nAaY8sK&!1qCB\t\u0005)Q5o\u001c8QCJ\u001cXM\u001d\u0005\b\u0005/+\u0003\u0019\u0001BM)!\u0011Yk!\b\u0004 \r\u0005\u0002bBB\u0006M\u0001\u00071Q\u0002\u0005\b\u0005/3\u0003\u0019\u0001BM\u0011\u001d\u0019\u0019C\na\u0001\u0005W\u000b\u0011\"\u001b8u_Z\u000bG.^3\u0002\u001b\u001d,G/R7qif4\u0016\r\\;f)\u0011\u0011\tn!\u000b\t\u000f\t]u\u00051\u0001\u0003\u001a\u0006\tb.Z<Ck&dG-\u001a:Xe\u0006\u0004\b/\u001a:\u0015\t\t55q\u0006\u0005\b\u0005/C\u0003\u0019\u0001BM\u0001"
)
public abstract class GenericFactoryDeserializerResolver extends Deserializers.Base {
   public abstract Class CLASS_DOMAIN();

   public abstract Iterable factories();

   public abstract Builder builderFor(final Object cf, final JavaType valueType);

   public Builder builderFor(final Class cls, final JavaType valueType) {
      return (Builder)this.factories().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$builderFor$1(cls, x$1))).map((x$2) -> x$2._2()).map((x$3) -> this.builderFor(x$3, valueType)).getOrElse(() -> {
         throw new IllegalStateException((new StringBuilder(86)).append("Could not find deserializer for ").append(cls.getCanonicalName()).append(". File issue on github:fasterxml/jackson-scala-module.").toString());
      });
   }

   public JsonDeserializer findCollectionLikeDeserializer(final CollectionLikeType collectionType, final DeserializationConfig config, final BeanDescription beanDesc, final TypeDeserializer elementTypeDeserializer, final JsonDeserializer elementDeserializer) {
      if (!this.CLASS_DOMAIN().isAssignableFrom(collectionType.getRawClass())) {
         return (JsonDeserializer).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      } else {
         Instantiator instantiator = new Instantiator(config, collectionType, collectionType.getContentType());
         return new Deserializer(collectionType, elementDeserializer, elementTypeDeserializer, instantiator);
      }
   }

   public Seq sortFactories(final IndexedSeq factories) {
      Tuple2[] cs = (Tuple2[])factories.toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      ListBuffer output = new ListBuffer();
      int[] remaining = (int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs), (x$4) -> BoxesRunTime.boxToInteger($anonfun$sortFactories$1(x$4)), scala.reflect.ClassTag..MODULE$.Int());
      int[][] adjMatrix = (int[][])scala.Array..MODULE$.ofDim(cs.length, cs.length, scala.reflect.ClassTag..MODULE$.Int());
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(j) -> {
            Tuple2 var7 = cs[i];
            if (var7 != null) {
               Class ic = (Class)var7._1();
               Tuple2 var10 = cs[j];
               if (var10 != null) {
                  Class jc = (Class)var10._1();
                  if (i != j && ic.isAssignableFrom(jc)) {
                     adjMatrix[i][j] = 1;
                  }
               } else {
                  throw new MatchError(var10);
               }
            } else {
               throw new MatchError(var7);
            }
         }));

      while(output.length() < cs.length) {
         int startLength = output.length();
         scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])cs)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> {
            if (remaining[i] == 1 && this.dotProduct(adjMatrix[i], remaining) == 0) {
               output.$plus$eq(factories.apply(i));
               remaining[i] = 0;
            }
         });
         if (output.length() == startLength) {
            throw new IllegalStateException("Companions contain a cycle.");
         }
      }

      return output.toSeq();
   }

   private int dotProduct(final int[] a, final int[] b) {
      if (a.length != b.length) {
         throw new IllegalArgumentException();
      } else {
         return BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.intArrayOps(a)).map((JFunction1.mcII.sp)(i) -> a[i] * b[i]).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$builderFor$1(final Class cls$1, final Tuple2 x$1) {
      return ((Class)x$1._1()).isAssignableFrom(cls$1);
   }

   // $FF: synthetic method
   public static final int $anonfun$sortFactories$1(final Tuple2 x$4) {
      return 1;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class BuilderWrapper extends AbstractCollection {
      private final Builder builder;
      private int size;
      // $FF: synthetic field
      public final GenericFactoryDeserializerResolver $outer;

      public Builder builder() {
         return this.builder;
      }

      public int size() {
         return this.size;
      }

      public void size_$eq(final int x$1) {
         this.size = x$1;
      }

      public boolean add(final Object e) {
         this.builder().$plus$eq(e);
         this.size_$eq(this.size() + 1);
         return true;
      }

      public Iterator iterator() {
         return (Iterator).MODULE$.orNull(scala..less.colon.less..MODULE$.refl());
      }

      public void setInitialValue(final Object init) {
         ((Iterable)init).foreach((e) -> BoxesRunTime.boxToBoolean($anonfun$setInitialValue$1(this, e)));
      }

      // $FF: synthetic method
      public GenericFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$BuilderWrapper$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final boolean $anonfun$setInitialValue$1(final BuilderWrapper $this, final Object e) {
         return $this.add(e);
      }

      public BuilderWrapper(final Builder builder) {
         this.builder = builder;
         if (GenericFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericFactoryDeserializerResolver.this;
            super();
            this.size = 0;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class Instantiator extends StdValueInstantiator {
      private final JavaType collectionType;
      private final JavaType valueType;
      // $FF: synthetic field
      public final GenericFactoryDeserializerResolver $outer;

      public boolean canCreateUsingDefault() {
         return true;
      }

      public BuilderWrapper createUsingDefault(final DeserializationContext ctxt) {
         return this.com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Instantiator$$$outer().new BuilderWrapper(this.com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Instantiator$$$outer().builderFor(this.collectionType.getRawClass(), this.valueType));
      }

      // $FF: synthetic method
      public GenericFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Instantiator$$$outer() {
         return this.$outer;
      }

      public Instantiator(final DeserializationConfig config, final JavaType collectionType, final JavaType valueType) {
         this.collectionType = collectionType;
         this.valueType = valueType;
         if (GenericFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericFactoryDeserializerResolver.this;
            super(config, collectionType);
         }
      }
   }

   private class Deserializer extends ContainerDeserializerBase implements ContextualDeserializer {
      private final JavaType collectionType;
      private final CollectionDeserializer containerDeserializer;
      // $FF: synthetic field
      public final GenericFactoryDeserializerResolver $outer;

      public Deserializer createContextual(final DeserializationContext ctxt, final BeanProperty property) {
         CollectionDeserializer newDelegate = this.containerDeserializer.createContextual(ctxt, property);
         return this.com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Deserializer$$$outer().new Deserializer(this.collectionType, newDelegate);
      }

      public JavaType getContentType() {
         return this.containerDeserializer.getContentType();
      }

      public JsonDeserializer getContentDeserializer() {
         return this.containerDeserializer.getContentDeserializer();
      }

      public Object deserialize(final JsonParser jp, final DeserializationContext ctxt) {
         Collection var4 = this.containerDeserializer.deserialize(jp, ctxt);
         if (var4 instanceof BuilderWrapper && ((BuilderWrapper)var4).com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$BuilderWrapper$$$outer() == this.com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Deserializer$$$outer()) {
            BuilderWrapper var5 = (BuilderWrapper)var4;
            return var5.builder().result();
         } else {
            throw new MatchError(var4);
         }
      }

      public Object deserialize(final JsonParser jp, final DeserializationContext ctxt, final Object intoValue) {
         BuilderWrapper bw = this.newBuilderWrapper(ctxt);
         bw.setInitialValue(intoValue);
         Collection var6 = this.containerDeserializer.deserialize(jp, ctxt, bw);
         if (var6 instanceof BuilderWrapper && ((BuilderWrapper)var6).com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$BuilderWrapper$$$outer() == this.com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Deserializer$$$outer()) {
            BuilderWrapper var7 = (BuilderWrapper)var6;
            return var7.builder().result();
         } else {
            throw new MatchError(var6);
         }
      }

      public Object getEmptyValue(final DeserializationContext ctxt) {
         BuilderWrapper bw = this.newBuilderWrapper(ctxt);
         return bw.builder().result();
      }

      private BuilderWrapper newBuilderWrapper(final DeserializationContext ctxt) {
         return (BuilderWrapper)this.containerDeserializer.getValueInstantiator().createUsingDefault(ctxt);
      }

      // $FF: synthetic method
      public GenericFactoryDeserializerResolver com$fasterxml$jackson$module$scala$deser$GenericFactoryDeserializerResolver$Deserializer$$$outer() {
         return this.$outer;
      }

      public Deserializer(final JavaType collectionType, final CollectionDeserializer containerDeserializer) {
         this.collectionType = collectionType;
         this.containerDeserializer = containerDeserializer;
         if (GenericFactoryDeserializerResolver.this == null) {
            throw null;
         } else {
            this.$outer = GenericFactoryDeserializerResolver.this;
            super(collectionType);
         }
      }

      public Deserializer(final JavaType collectionType, final JsonDeserializer valueDeser, final TypeDeserializer valueTypeDeser, final ValueInstantiator valueInstantiator) {
         this(collectionType, new CollectionDeserializer(collectionType, valueDeser, valueTypeDeser, valueInstantiator));
      }
   }
}
