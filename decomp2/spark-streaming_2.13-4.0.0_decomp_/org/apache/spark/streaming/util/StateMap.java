package org.apache.spark.streaming.util;

import java.io.Serializable;
import org.apache.spark.SparkConf;
import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015cAB\b\u0011\u0003\u0003\u0011\"\u0004C\u0003+\u0001\u0011\u0005A\u0006C\u0003>\u0001\u0019\u0005a\bC\u0003E\u0001\u0019\u0005Q\tC\u0003[\u0001\u0019\u00051\fC\u0003]\u0001\u0019\u0005Q\fC\u0003g\u0001\u0019\u0005q\rC\u0003j\u0001\u0019\u0005A\u0006C\u0003k\u0001\u0011\u00051n\u0002\u0004u!!\u0005!#\u001e\u0004\u0007\u001fAA\tA\u0005<\t\u000b)RA\u0011A<\t\u000baTA\u0011A=\t\u000f\u0005\u0005!\u0002\"\u0001\u0002\u0004!I\u0011Q\u0007\u0006\u0002\u0002\u0013%\u0011q\u0007\u0002\t'R\fG/Z'ba*\u0011\u0011CE\u0001\u0005kRLGN\u0003\u0002\u0014)\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003+Y\tQa\u001d9be.T!a\u0006\r\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0012aA8sOV\u00191$M\u001e\u0014\u0007\u0001a\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0003G!j\u0011\u0001\n\u0006\u0003K\u0019\n!![8\u000b\u0003\u001d\nAA[1wC&\u0011\u0011\u0006\n\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tQ\u0006\u0005\u0003/\u0001=RT\"\u0001\t\u0011\u0005A\nD\u0002\u0001\u0003\u0006e\u0001\u0011\ra\r\u0002\u0002\u0017F\u0011Ag\u000e\t\u0003;UJ!A\u000e\u0010\u0003\u000f9{G\u000f[5oOB\u0011Q\u0004O\u0005\u0003sy\u00111!\u00118z!\t\u00014\bB\u0003=\u0001\t\u00071GA\u0001T\u0003\r9W\r\u001e\u000b\u0003\u007f\t\u00032!\b!;\u0013\t\teD\u0001\u0004PaRLwN\u001c\u0005\u0006\u0007\n\u0001\raL\u0001\u0004W\u0016L\u0018!C4fi\nKH+[7f)\t1\u0005\fE\u0002H\u001fJs!\u0001S'\u000f\u0005%cU\"\u0001&\u000b\u0005-[\u0013A\u0002\u001fs_>$h(C\u0001 \u0013\tqe$A\u0004qC\u000e\\\u0017mZ3\n\u0005A\u000b&\u0001C%uKJ\fGo\u001c:\u000b\u00059s\u0002#B\u000fT_i*\u0016B\u0001+\u001f\u0005\u0019!V\u000f\u001d7fgA\u0011QDV\u0005\u0003/z\u0011A\u0001T8oO\")\u0011l\u0001a\u0001+\u0006\tB\u000f\u001b:fg\",\u0006\u000fZ1uK\u0012$\u0016.\\3\u0002\r\u001d,G/\u00117m)\u00051\u0015a\u00019viR!a,\u00192e!\tir,\u0003\u0002a=\t!QK\\5u\u0011\u0015\u0019U\u00011\u00010\u0011\u0015\u0019W\u00011\u0001;\u0003\u0015\u0019H/\u0019;f\u0011\u0015)W\u00011\u0001V\u0003-)\b\u000fZ1uK\u0012$\u0016.\\3\u0002\rI,Wn\u001c<f)\tq\u0006\u000eC\u0003D\r\u0001\u0007q&\u0001\u0003d_BL\u0018!\u0004;p\t\u0016\u0014WoZ*ue&tw\rF\u0001m!\ti\u0017O\u0004\u0002o_B\u0011\u0011JH\u0005\u0003az\ta\u0001\u0015:fI\u00164\u0017B\u0001:t\u0005\u0019\u0019FO]5oO*\u0011\u0001OH\u0001\t'R\fG/Z'baB\u0011aFC\n\u0004\u0015q\u0011C#A;\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0007ilx0F\u0001|!\u0011q\u0003\u0001 @\u0011\u0005AjH!\u0002\u001a\r\u0005\u0004\u0019\u0004C\u0001\u0019\u0000\t\u0015aDB1\u00014\u0003\u0019\u0019'/Z1uKV1\u0011QAA\u0007\u0003#!B!a\u0002\u0002*Q1\u0011\u0011BA\n\u0003G\u0001bA\f\u0001\u0002\f\u0005=\u0001c\u0001\u0019\u0002\u000e\u0011)!'\u0004b\u0001gA\u0019\u0001'!\u0005\u0005\u000bqj!\u0019A\u001a\t\u0013\u0005UQ\"!AA\u0004\u0005]\u0011AC3wS\u0012,gnY3%cA1\u0011\u0011DA\u0010\u0003\u0017i!!a\u0007\u000b\u0007\u0005ua$A\u0004sK\u001adWm\u0019;\n\t\u0005\u0005\u00121\u0004\u0002\t\u00072\f7o\u001d+bO\"I\u0011QE\u0007\u0002\u0002\u0003\u000f\u0011qE\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004CBA\r\u0003?\ty\u0001C\u0004\u0002,5\u0001\r!!\f\u0002\t\r|gN\u001a\t\u0005\u0003_\t\t$D\u0001\u0015\u0013\r\t\u0019\u0004\u0006\u0002\n'B\f'o[\"p]\u001a\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u000f\u0011\t\u0005m\u0012\u0011I\u0007\u0003\u0003{Q1!a\u0010'\u0003\u0011a\u0017M\\4\n\t\u0005\r\u0013Q\b\u0002\u0007\u001f\nTWm\u0019;"
)
public abstract class StateMap implements Serializable {
   public static StateMap create(final SparkConf conf, final ClassTag evidence$1, final ClassTag evidence$2) {
      return StateMap$.MODULE$.create(conf, evidence$1, evidence$2);
   }

   public static StateMap empty() {
      return StateMap$.MODULE$.empty();
   }

   public abstract Option get(final Object key);

   public abstract Iterator getByTime(final long threshUpdatedTime);

   public abstract Iterator getAll();

   public abstract void put(final Object key, final Object state, final long updatedTime);

   public abstract void remove(final Object key);

   public abstract StateMap copy();

   public String toDebugString() {
      return this.toString();
   }
}
