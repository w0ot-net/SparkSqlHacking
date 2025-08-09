package org.apache.spark.sql;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.annotation.Experimental;
import org.apache.spark.annotation.Stable;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.sources.BaseRelation;
import org.apache.spark.sql.streaming.DataStreamReader;
import org.apache.spark.sql.streaming.StreamingQueryManager;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.sql.util.ExecutionListenerManager;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0015=g!\u0002*T\u0003\u0003a\u0006\u0002C;\u0001\u0005\u000b\u0007I\u0011\u0001<\t\u0011m\u0004!\u0011!Q\u0001\n]Da\u0001 \u0001\u0005\u0002Mk\bbBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u001b\u0001a\u0011AA\b\u0011\u001d\t\t\u0002\u0001D\u0001\u0003'Aq!!\t\u0001\r\u0003\t\u0019\u0003C\u0004\u0002\"\u0001!\t!!\u0010\t\u000f\u0005]\u0003\u0001\"\u0001\u0002Z!9\u0011q\u000b\u0001\u0005\u0002\u0005u\u0003bBA3\u0001\u0011\u0005\u0011q\r\u0005\b\u0003s\u0002a\u0011AA>\u0011\u001d\t\t\u000b\u0001C\u0001\u0003GCq!!-\u0001\r\u0003\t\u0019\fC\u0005\u0002<\u0002\u0011\rQ\"\u0001\u0002>\"9\u0011Q\u0019\u0001\u0005\u0002\u0005\u001d\u0007bBAj\u0001\u0011\u0005\u0011Q\u001b\u0005\b\u00033\u0004A\u0011AAn\u0011\u001d\ty\u000e\u0001C\u0001\u0003CDq!a9\u0001\t\u0003\t)\u000fC\u0004\u0002d\u0002!\tA!\u0010\t\u000f\t]\u0003\u0001\"\u0001\u0003Z!9\u00111\u001d\u0001\u0005\u0002\t-\u0004b\u0002BF\u0001\u0011\u0005!Q\u0012\u0005\b\u0005\u0017\u0003A\u0011\u0001BY\u0011\u001d\u0011Y\t\u0001C\u0001\u0005\u000fDq!a9\u0001\t\u0003\u0011\t\u000fC\u0004\u0002d\u0002!\tA!>\t\u000f\u0005\r\b\u0001\"\u0001\u0004\u0002!9\u00111\u001d\u0001\u0005\u0002\r\u0005\u0002bBAr\u0001\u0011\u000511\b\u0005\b\u0007+\u0002a\u0011AB,\u0011\u001d\u0019y\u0006\u0001D\u0001\u0007CBqaa\u001c\u0001\t\u0003\u0019\t\bC\u0004\u0004p\u0001!\ta!$\t\u000f\r=\u0004\u0001\"\u0001\u0004\u001a\"91q\u000e\u0001\u0005\u0002\r%\u0006bBB8\u0001\u0011\u00051q\u0017\u0005\b\u0007_\u0002A\u0011ABb\u0011\u001d\u0019y\r\u0001C\u0001\u0007#Dqa!6\u0001\t\u0003\u00199\u000eC\u0004\u0004V\u0002!\taa9\t\u000f\rU\u0007\u0001\"\u0001\u0004l\"91Q\u001b\u0001\u0005\u0002\rU\bB\u0002+\u0001\t\u0003!9\u0001C\u0004\u0005\u000e\u0001!\t\u0001b\u0004\t\u000f\u0011M\u0001\u0001\"\u0001\u0005\u0016!9A1\u0003\u0001\u0005\u0002\u0011]\u0001b\u0002C\u000f\u0001\u0011%Aq\u0004\u0005\b\tc\u0001a\u0011\u0001C\u001a\u0011\u001d!Y\u0004\u0001C\u0001\t{Aq\u0001b\u000f\u0001\t\u0003!)\u0005C\u0004\u0005J\u0001!\t\u0001b\u0013\t\u000f\u0011%\u0003\u0001\"\u0001\u0005\\!9A\u0011\n\u0001\u0005\u0002\u0011\r\u0004b\u0002C%\u0001\u0011\u0005Aq\u0010\u0005\b\t7\u0003A\u0011\u0001CO\u0011\u001d!y\f\u0001C\u0001\t\u0003Dq\u0001b0\u0001\t\u0003!Y\rC\u0004\u0005@\u0002!\t\u0001b5\t\u000f\u0011\r\b\u0001\"\u0001\u0005f\"9A1\u001d\u0001\u0005\u0002\u0011=\bb\u0002Cr\u0001\u0011\u0005Aq\u001f\u0005\b\tG\u0004A\u0011\u0001C\u0000\u0011\u001d!\u0019\u000f\u0001C\u0001\u000b\u000fAq\u0001b9\u0001\t\u0003)y\u0001C\u0004\u0006\u0018\u0001!\t!\"\u0007\t\u000f\u0015]\u0001\u0001\"\u0001\u0006$!9Qq\u0003\u0001\u0005\u0002\u0015=\u0002bBC\f\u0001\u0011\u0005Q1\b\u0005\b\u000b/\u0001A\u0011AC\"\u0011\u001d)9\u0002\u0001C\u0001\u000b#Bq!b\u0017\u0001\t\u0003)i\u0006C\u0004\u0006\\\u0001!\t!b\u001b\t\u000f\u0015m\u0003\u0001\"\u0001\u0006\u0002\u001e9QQS*\t\u0002\u0015]eA\u0002*T\u0011\u0003)I\n\u0003\u0004}\u001b\u0012\u0005Q1V\u0003\u0007\u000b[k\u0005a\u0015@\t\u000f\u0015=V\n\"\u0001\u00062\"IQqX'\u0002\u0002\u0013%Q\u0011\u0019\u0002\u000b'Fc5i\u001c8uKb$(B\u0001+V\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003-^\u000bQa\u001d9be.T!\u0001W-\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0016aA8sO\u000e\u00011\u0003\u0002\u0001^G&\u0004\"AX1\u000e\u0003}S\u0011\u0001Y\u0001\u0006g\u000e\fG.Y\u0005\u0003E~\u0013a!\u00118z%\u00164\u0007C\u00013h\u001b\u0005)'B\u00014V\u0003!Ig\u000e^3s]\u0006d\u0017B\u00015f\u0005\u001daunZ4j]\u001e\u0004\"A\u001b:\u000f\u0005-\u0004hB\u00017p\u001b\u0005i'B\u00018\\\u0003\u0019a$o\\8u}%\t\u0001-\u0003\u0002r?\u00069\u0001/Y2lC\u001e,\u0017BA:u\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\tx,\u0001\u0007ta\u0006\u00148nU3tg&|g.F\u0001x!\tA\u00180D\u0001T\u0013\tQ8K\u0001\u0007Ta\u0006\u00148nU3tg&|g.A\u0007ta\u0006\u00148nU3tg&|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005y|\bC\u0001=\u0001\u0011\u0015)8\u00011\u0001x\u00031\u0019\b/\u0019:l\u0007>tG/\u001a=u+\t\t)\u0001\u0005\u0003\u0002\b\u0005%Q\"A+\n\u0007\u0005-QK\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/\u0001\u0006oK^\u001cVm]:j_:$\u0012A`\u0001\u0010Y&\u001cH/\u001a8fe6\u000bg.Y4feV\u0011\u0011Q\u0003\t\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111D*\u0002\tU$\u0018\u000e\\\u0005\u0005\u0003?\tIB\u0001\rFq\u0016\u001cW\u000f^5p]2K7\u000f^3oKJl\u0015M\\1hKJ\fqa]3u\u0007>tg\r\u0006\u0003\u0002&\u0005-\u0002c\u00010\u0002(%\u0019\u0011\u0011F0\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003[9\u0001\u0019AA\u0018\u0003\u0015\u0001(o\u001c9t!\u0011\t\t$!\u000f\u000e\u0005\u0005M\"\u0002BA\u000e\u0003kQ!!a\u000e\u0002\t)\fg/Y\u0005\u0005\u0003w\t\u0019D\u0001\u0006Qe>\u0004XM\u001d;jKN$b!!\n\u0002@\u0005M\u0003bBA!\u0011\u0001\u0007\u00111I\u0001\u0004W\u0016L\b\u0003BA#\u0003\u001brA!a\u0012\u0002JA\u0011AnX\u0005\u0004\u0003\u0017z\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002P\u0005E#AB*ue&twMC\u0002\u0002L}Cq!!\u0016\t\u0001\u0004\t\u0019%A\u0003wC2,X-A\u0004hKR\u001cuN\u001c4\u0015\t\u0005\r\u00131\f\u0005\b\u0003\u0003J\u0001\u0019AA\")\u0019\t\u0019%a\u0018\u0002b!9\u0011\u0011\t\u0006A\u0002\u0005\r\u0003bBA2\u0015\u0001\u0007\u00111I\u0001\rI\u00164\u0017-\u001e7u-\u0006dW/Z\u0001\fO\u0016$\u0018\t\u001c7D_:47/\u0006\u0002\u0002jAA\u00111NA;\u0003\u0007\n\u0019%\u0004\u0002\u0002n)!\u0011qNA9\u0003%IW.\\;uC\ndWMC\u0002\u0002t}\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t9(!\u001c\u0003\u00075\u000b\u0007/\u0001\u0007fqB,'/[7f]R\fG.\u0006\u0002\u0002~A\u0019\u00010a \n\u0007\u0005\u00055KA\nFqB,'/[7f]R\fG.T3uQ>$7\u000fK\u0002\r\u0003\u000b\u0003B!a\"\u0002\u000e6\u0011\u0011\u0011\u0012\u0006\u0004\u0003\u0017+\u0016AC1o]>$\u0018\r^5p]&!\u0011qRAE\u00051)\u0005\u0010]3sS6,g\u000e^1mQ\ra\u00111\u0013\t\u0004=\u0006U\u0015bAAL?\nIAO]1og&,g\u000e\u001e\u0015\u0004\u0019\u0005m\u0005\u0003BAD\u0003;KA!a(\u0002\n\nAQK\\:uC\ndW-\u0001\bf[B$\u0018\u0010R1uC\u001a\u0013\u0018-\\3\u0016\u0005\u0005\u0015\u0006#\u0002=\u0002(\u0006-\u0016bAAU'\n9A)\u0019;bg\u0016$\bc\u0001=\u0002.&\u0019\u0011qV*\u0003\u0007I{w/A\u0002vI\u001a,\"!!.\u0011\u0007a\f9,C\u0002\u0002:N\u0013q\"\u0016#G%\u0016<\u0017n\u001d;sCRLwN\\\u0001\nS6\u0004H.[2jiN,\"!a0\u0011\u0007a\f\t-C\u0002\u0002DN\u0013AbU)M\u00136\u0004H.[2jiN\f\u0001\"[:DC\u000eDW\r\u001a\u000b\u0005\u0003\u0013\fy\rE\u0002_\u0003\u0017L1!!4`\u0005\u001d\u0011un\u001c7fC:Dq!!5\u0011\u0001\u0004\t\u0019%A\u0005uC\ndWMT1nK\u0006Q1-Y2iKR\u000b'\r\\3\u0015\t\u0005\u0015\u0012q\u001b\u0005\b\u0003#\f\u0002\u0019AA\"\u00031)hnY1dQ\u0016$\u0016M\u00197f)\u0011\t)#!8\t\u000f\u0005E'\u00031\u0001\u0002D\u0005Q1\r\\3be\u000e\u000b7\r[3\u0015\u0005\u0005\u0015\u0012aD2sK\u0006$X\rR1uC\u001a\u0013\u0018-\\3\u0016\t\u0005\u001d(Q\u0004\u000b\u0005\u0003S\u0014y\u0003\u0006\u0003\u0002&\u0006-\b\"CAw)\u0005\u0005\t9AAx\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003c\u0014iA!\u0007\u000f\t\u0005M(q\u0001\b\u0005\u0003k\u0014\u0019A\u0004\u0003\u0002x\u0006uhbA6\u0002z&\u0019\u00111`0\u0002\u000fI,g\r\\3di&!\u0011q B\u0001\u0003\u001d\u0011XO\u001c;j[\u0016T1!a?`\u0013\r\t(Q\u0001\u0006\u0005\u0003\u007f\u0014\t!\u0003\u0003\u0003\n\t-\u0011\u0001C;oSZ,'o]3\u000b\u0007E\u0014)!\u0003\u0003\u0003\u0010\tE!a\u0002+za\u0016$\u0016mZ\u0005\u0005\u0005'\u0011)B\u0001\u0005UsB,G+Y4t\u0015\u0011\u00119B!\u0001\u0002\u0007\u0005\u0004\u0018\u000e\u0005\u0003\u0003\u001c\tuA\u0002\u0001\u0003\b\u0005?!\"\u0019\u0001B\u0011\u0005\u0005\t\u0015\u0003\u0002B\u0012\u0005S\u00012A\u0018B\u0013\u0013\r\u00119c\u0018\u0002\b\u001d>$\b.\u001b8h!\rq&1F\u0005\u0004\u0005[y&a\u0002)s_\u0012,8\r\u001e\u0005\b\u0005c!\u0002\u0019\u0001B\u001a\u0003\r\u0011H\r\u001a\t\u0007\u0005k\u0011ID!\u0007\u000e\u0005\t]\"b\u0001B\u0019+&!!1\bB\u001c\u0005\r\u0011F\tR\u000b\u0005\u0005\u007f\u0011Y\u0005\u0006\u0003\u0003B\t5C\u0003BAS\u0005\u0007B\u0011B!\u0012\u0016\u0003\u0003\u0005\u001dAa\u0012\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u0002r\n5!\u0011\n\t\u0005\u00057\u0011Y\u0005B\u0004\u0003 U\u0011\rA!\t\t\u000f\t=S\u00031\u0001\u0003R\u0005!A-\u0019;b!\u0015Q'1\u000bB%\u0013\r\u0011)\u0006\u001e\u0002\u0004'\u0016\f\u0018a\u00062bg\u0016\u0014V\r\\1uS>tGk\u001c#bi\u00064%/Y7f)\u0011\t)Ka\u0017\t\u000f\tuc\u00031\u0001\u0003`\u0005a!-Y:f%\u0016d\u0017\r^5p]B!!\u0011\rB4\u001b\t\u0011\u0019GC\u0002\u0003fM\u000bqa]8ve\u000e,7/\u0003\u0003\u0003j\t\r$\u0001\u0004\"bg\u0016\u0014V\r\\1uS>tGCBAS\u0005[\u0012\u0019\bC\u0004\u0003p]\u0001\rA!\u001d\u0002\rI|wO\u0015#E!\u0019\u0011)D!\u000f\u0002,\"9!QO\fA\u0002\t]\u0014AB:dQ\u0016l\u0017\r\u0005\u0003\u0003z\t}TB\u0001B>\u0015\r\u0011ihU\u0001\u0006if\u0004Xm]\u0005\u0005\u0005\u0003\u0013YH\u0001\u0006TiJ,8\r\u001e+za\u0016D3a\u0006BC!\u0011\t9Ia\"\n\t\t%\u0015\u0011\u0012\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u000eGJ,\u0017\r^3ECR\f7/\u001a;\u0016\t\t=%q\u0013\u000b\u0005\u0005#\u0013i\u000b\u0006\u0003\u0003\u0014\n\r\u0006#\u0002=\u0002(\nU\u0005\u0003\u0002B\u000e\u0005/#qA!'\u0019\u0005\u0004\u0011YJA\u0001U#\u0011\u0011\u0019C!(\u0011\u0007y\u0013y*C\u0002\u0003\"~\u00131!\u00118z\u0011%\u0011)\u000bGA\u0001\u0002\b\u00119+\u0001\u0006fm&$WM\\2fIM\u0002R\u0001\u001fBU\u0005+K1Aa+T\u0005\u001d)enY8eKJDqAa\u0014\u0019\u0001\u0004\u0011y\u000bE\u0003k\u0005'\u0012)*\u0006\u0003\u00034\nmF\u0003\u0002B[\u0005\u0007$BAa.\u0003>B)\u00010a*\u0003:B!!1\u0004B^\t\u001d\u0011I*\u0007b\u0001\u00057C\u0011Ba0\u001a\u0003\u0003\u0005\u001dA!1\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007E\u0003y\u0005S\u0013I\fC\u0004\u0003Pe\u0001\rA!2\u0011\r\tU\"\u0011\bB]+\u0011\u0011IM!5\u0015\t\t-'\u0011\u001c\u000b\u0005\u0005\u001b\u0014\u0019\u000eE\u0003y\u0003O\u0013y\r\u0005\u0003\u0003\u001c\tEGa\u0002BM5\t\u0007!1\u0014\u0005\n\u0005+T\u0012\u0011!a\u0002\u0005/\f!\"\u001a<jI\u0016t7-\u001a\u00136!\u0015A(\u0011\u0016Bh\u0011\u001d\u0011yE\u0007a\u0001\u00057\u0004b!!\r\u0003^\n=\u0017\u0002\u0002Bp\u0003g\u0011A\u0001T5tiR1\u0011Q\u0015Br\u0005cDqAa\u001c\u001c\u0001\u0004\u0011)\u000f\u0005\u0004\u0003h\n5\u00181V\u0007\u0003\u0005STA!a\u000e\u0003l*\u0019!qC+\n\t\t=(\u0011\u001e\u0002\b\u0015\u00064\u0018M\u0015#E\u0011\u001d\u0011)h\u0007a\u0001\u0005oB3a\u0007BC)\u0019\t)Ka>\u0003~\"9!\u0011 \u000fA\u0002\tm\u0018\u0001\u0002:poN\u0004b!!\r\u0003^\u0006-\u0006b\u0002B;9\u0001\u0007!q\u000f\u0015\u00049\t\u0015ECBAS\u0007\u0007\u0019y\u0001C\u0004\u00032u\u0001\ra!\u00021\t\r\u001d11\u0002\t\u0007\u0005k\u0011Id!\u0003\u0011\t\tm11\u0002\u0003\r\u0007\u001b\u0019\u0019!!A\u0001\u0002\u000b\u0005!1\u0014\u0002\u0004?\u0012\n\u0004bBB\t;\u0001\u000711C\u0001\nE\u0016\fgn\u00117bgN\u0004Da!\u0006\u0004\u001eA1\u0011QIB\f\u00077IAa!\u0007\u0002R\t)1\t\\1tgB!!1DB\u000f\t1\u0019yba\u0004\u0002\u0002\u0003\u0005)\u0011\u0001BN\u0005\ryFE\r\u000b\u0007\u0003K\u001b\u0019ca\f\t\u000f\tEb\u00041\u0001\u0004&A\"1qEB\u0016!\u0019\u00119O!<\u0004*A!!1DB\u0016\t1\u0019ica\t\u0002\u0002\u0003\u0005)\u0011\u0001BN\u0005\ryFe\r\u0005\b\u0007#q\u0002\u0019AB\u0019a\u0011\u0019\u0019da\u000e\u0011\r\u0005\u00153qCB\u001b!\u0011\u0011Yba\u000e\u0005\u0019\re2qFA\u0001\u0002\u0003\u0015\tAa'\u0003\u0007}#C\u0007\u0006\u0004\u0002&\u000eu2\u0011\n\u0005\b\u0005\u001fz\u0002\u0019AB a\u0011\u0019\te!\u0012\u0011\r\u0005E\"Q\\B\"!\u0011\u0011Yb!\u0012\u0005\u0019\r\u001d3QHA\u0001\u0002\u0003\u0015\tAa'\u0003\u0007}#S\u0007C\u0004\u0004\u0012}\u0001\raa\u00131\t\r53\u0011\u000b\t\u0007\u0003\u000b\u001a9ba\u0014\u0011\t\tm1\u0011\u000b\u0003\r\u0007'\u001aI%!A\u0001\u0002\u000b\u0005!1\u0014\u0002\u0004?\u00122\u0014\u0001\u0002:fC\u0012,\"a!\u0017\u0011\u0007a\u001cY&C\u0002\u0004^M\u0013q\u0002R1uC\u001a\u0013\u0018-\\3SK\u0006$WM]\u0001\u000be\u0016\fGm\u0015;sK\u0006lWCAB2!\u0011\u0019)ga\u001b\u000e\u0005\r\u001d$bAB5'\u0006I1\u000f\u001e:fC6LgnZ\u0005\u0005\u0007[\u001a9G\u0001\tECR\f7\u000b\u001e:fC6\u0014V-\u00193fe\u0006\u00192M]3bi\u0016,\u0005\u0010^3s]\u0006dG+\u00192mKR1\u0011QUB:\u0007kBq!!5#\u0001\u0004\t\u0019\u0005C\u0004\u0004x\t\u0002\r!a\u0011\u0002\tA\fG\u000f\u001b\u0015\fE\rm4\u0011QBB\u0007\u000f\u001bI\tE\u0002_\u0007{J1aa `\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0019))A\u0017vg\u0016\u00043\u000f]1sWN+7o]5p]:\u001a\u0017\r^1m_\u001et3M]3bi\u0016$\u0016M\u00197fA%t7\u000f^3bI:\nQa]5oG\u0016\f#aa#\u0002\u000bIr#G\f\u0019\u0015\u0011\u0005\u00156qRBI\u0007'Cq!!5$\u0001\u0004\t\u0019\u0005C\u0004\u0004x\r\u0002\r!a\u0011\t\u000f\rU5\u00051\u0001\u0002D\u000511o\\;sG\u0016D3bIB>\u0007\u0003\u001b\u0019ia\"\u0004\nRA\u0011QUBN\u0007;\u001by\nC\u0004\u0002R\u0012\u0002\r!a\u0011\t\u000f\rUE\u00051\u0001\u0002D!91\u0011\u0015\u0013A\u0002\r\r\u0016aB8qi&|gn\u001d\t\t\u0003c\u0019)+a\u0011\u0002D%!\u0011qOA\u001aQ-!31PBA\u0007\u0007\u001b9i!#\u0015\u0011\u0005\u001561VBW\u0007_Cq!!5&\u0001\u0004\t\u0019\u0005C\u0004\u0004\u0016\u0016\u0002\r!a\u0011\t\u000f\r\u0005V\u00051\u0001\u00042BA\u0011QIBZ\u0003\u0007\n\u0019%\u0003\u0003\u0002x\u0005E\u0003fC\u0013\u0004|\r\u000551QBD\u0007\u0013#\"\"!*\u0004:\u000em6QXB`\u0011\u001d\t\tN\na\u0001\u0003\u0007Bqa!&'\u0001\u0004\t\u0019\u0005C\u0004\u0003v\u0019\u0002\rAa\u001e\t\u000f\r\u0005f\u00051\u0001\u0004$\"Zaea\u001f\u0004\u0002\u000e\r5qQBE))\t)k!2\u0004H\u000e%71\u001a\u0005\b\u0003#<\u0003\u0019AA\"\u0011\u001d\u0019)j\na\u0001\u0003\u0007BqA!\u001e(\u0001\u0004\u00119\bC\u0004\u0004\"\u001e\u0002\ra!-)\u0017\u001d\u001aYh!!\u0004\u0004\u000e\u001d5\u0011R\u0001\u000eIJ|\u0007\u000fV3naR\u000b'\r\\3\u0015\t\u0005\u001521\u001b\u0005\b\u0003#D\u0003\u0019AA\"\u0003\u0015\u0011\u0018M\\4f)\u0011\t)k!7\t\u000f\rm\u0017\u00061\u0001\u0004^\u0006\u0019QM\u001c3\u0011\u0007y\u001by.C\u0002\u0004b~\u0013A\u0001T8oOR1\u0011QUBs\u0007SDqaa:+\u0001\u0004\u0019i.A\u0003ti\u0006\u0014H\u000fC\u0004\u0004\\*\u0002\ra!8\u0015\u0011\u0005\u00156Q^Bx\u0007cDqaa:,\u0001\u0004\u0019i\u000eC\u0004\u0004\\.\u0002\ra!8\t\u000f\rM8\u00061\u0001\u0004^\u0006!1\u000f^3q))\t)ka>\u0004z\u000em8Q \u0005\b\u0007Od\u0003\u0019ABo\u0011\u001d\u0019Y\u000e\fa\u0001\u0007;Dqaa=-\u0001\u0004\u0019i\u000eC\u0004\u0004\u00002\u0002\r\u0001\"\u0001\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\rqF1A\u0005\u0004\t\u000by&aA%oiR!\u0011Q\u0015C\u0005\u0011\u001d!Y!\fa\u0001\u0003\u0007\nqa]9m)\u0016DH/A\u0003uC\ndW\r\u0006\u0003\u0002&\u0012E\u0001bBAi]\u0001\u0007\u00111I\u0001\u0007i\u0006\u0014G.Z:\u0015\u0005\u0005\u0015F\u0003BAS\t3Aq\u0001b\u00071\u0001\u0004\t\u0019%\u0001\u0007eCR\f'-Y:f\u001d\u0006lW-A\u000bnCB$\u0016M\u00197f\t\u0006$\u0018m]3u\u001fV$\b/\u001e;\u0015\t\u0005\u0015F\u0011\u0005\u0005\b\t'\t\u0004\u0019\u0001C\u0012!\u0015A\u0018q\u0015C\u0013!\u0011!9\u0003\"\f\u000e\u0005\u0011%\"b\u0001C\u0016'\u000691-\u0019;bY><\u0017\u0002\u0002C\u0018\tS\u0011Q\u0001V1cY\u0016\fqa\u001d;sK\u0006l7/\u0006\u0002\u00056A!1Q\rC\u001c\u0013\u0011!Ida\u001a\u0003+M#(/Z1nS:<\u0017+^3ss6\u000bg.Y4fe\u0006QA/\u00192mK:\u000bW.Z:\u0015\u0005\u0011}\u0002#\u00020\u0005B\u0005\r\u0013b\u0001C\"?\n)\u0011I\u001d:bsR!Aq\bC$\u0011\u001d!Y\u0002\u000ea\u0001\u0003\u0007\n1\"\u00199qYf\u001c6\r[3nCR1\u0011Q\u0015C'\t\u001fBqAa\u001c6\u0001\u0004\u0011\t\bC\u0004\u0003vU\u0002\rAa\u001e)\u0017U\u001aYh!!\u0005T\r\u001dEqK\u0011\u0003\t+\nA$V:fA\r\u0014X-\u0019;f\t\u0006$\u0018M\u0012:b[\u0016\u0004\u0013N\\:uK\u0006$g&\t\u0002\u0005Z\u0005)\u0011GL\u001a/aQ1\u0011Q\u0015C/\t?BqAa\u001c7\u0001\u0004\u0011)\u000fC\u0004\u0003vY\u0002\rAa\u001e)\u0017Y\u001aYh!!\u0005T\r\u001dEq\u000b\u000b\u0007\u0003K#)\u0007\"\u001d\t\u000f\tEr\u00071\u0001\u0005hA\"A\u0011\u000eC7!\u0019\u0011)D!\u000f\u0005lA!!1\u0004C7\t1!y\u0007\"\u001a\u0002\u0002\u0003\u0005)\u0011\u0001BN\u0005\ryFe\u000e\u0005\b\u0007#9\u0004\u0019\u0001C:a\u0011!)\b\"\u001f\u0011\r\u0005\u00153q\u0003C<!\u0011\u0011Y\u0002\"\u001f\u0005\u0019\u0011mD\u0011OA\u0001\u0002\u0003\u0015\tAa'\u0003\u0007}#\u0003\bK\u00068\u0007w\u001a\t\tb\u0015\u0004\b\u0012]CCBAS\t\u0003#i\tC\u0004\u00032a\u0002\r\u0001b!1\t\u0011\u0015E\u0011\u0012\t\u0007\u0005O\u0014i\u000fb\"\u0011\t\tmA\u0011\u0012\u0003\r\t\u0017#\t)!A\u0001\u0002\u000b\u0005!1\u0014\u0002\u0004?\u0012J\u0004bBB\tq\u0001\u0007Aq\u0012\u0019\u0005\t##)\n\u0005\u0004\u0002F\r]A1\u0013\t\u0005\u00057!)\n\u0002\u0007\u0005\u0018\u00125\u0015\u0011!A\u0001\u0006\u0003\u0011YJ\u0001\u0003`IE\u0002\u0004f\u0003\u001d\u0004|\r\u0005E1KBD\t/\n1\u0002]1scV,GOR5mKR!\u0011Q\u0015CP\u0011\u001d!\t+\u000fa\u0001\tG\u000bQ\u0001]1uQN\u0004RA\u0018CS\u0003\u0007J1\u0001b*`\u0005)a$/\u001a9fCR,GM\u0010\u0015\fs\rm4\u0011\u0011CV\u0007\u000f#y+\t\u0002\u0005.\u0006YRk]3!e\u0016\fGM\f9beF,X\r\u001e\u0015*A%t7\u000f^3bI:\n#\u0001\"-\u0002\u000bErCG\f\u0019)\u0007e\")\f\u0005\u0003\u00058\u0012mVB\u0001C]\u0015\r\tYiX\u0005\u0005\t{#ILA\u0004wCJ\f'oZ:\u0002\u0011)\u001cxN\u001c$jY\u0016$B!!*\u0005D\"91q\u000f\u001eA\u0002\u0005\r\u0003f\u0003\u001e\u0004|\r\u0005EqYBD\t_\u000b#\u0001\"3\u00021U\u001bX\r\t:fC\u0012t#n]8oQ%\u0002\u0013N\\:uK\u0006$g\u0006\u0006\u0004\u0002&\u00125Gq\u001a\u0005\b\u0007oZ\u0004\u0019AA\"\u0011\u001d\u0011)h\u000fa\u0001\u0005oB3bOB>\u0007\u0003#9ma\"\u00050R1\u0011Q\u0015Ck\t/Dqaa\u001e=\u0001\u0004\t\u0019\u0005C\u0004\u0005Zr\u0002\r\u0001b7\u0002\u001bM\fW\u000e\u001d7j]\u001e\u0014\u0016\r^5p!\rqFQ\\\u0005\u0004\t?|&A\u0002#pk\ndW\rK\u0006=\u0007w\u001a\t\tb2\u0004\b\u0012=\u0016a\u00026t_:\u0014F\t\u0012\u000b\u0005\u0003K#9\u000fC\u0004\u0005jv\u0002\r\u0001b;\u0002\t)\u001cxN\u001c\t\u0007\u0005k\u0011I$a\u0011)\u0017u\u001aYh!!\u0005H\u000e\u001dEq\u0016\u000b\u0005\u0003K#\t\u0010C\u0004\u0005jz\u0002\r\u0001b=\u0011\r\t\u001d(Q^A\"Q-q41PBA\t\u000f\u001c9\tb,\u0015\r\u0005\u0015F\u0011 C~\u0011\u001d!Io\u0010a\u0001\tWDqA!\u001e@\u0001\u0004\u00119\bK\u0006@\u0007w\u001a\t\tb2\u0004\b\u0012=FCBAS\u000b\u0003)\u0019\u0001C\u0004\u0005j\u0002\u0003\r\u0001b=\t\u000f\tU\u0004\t1\u0001\u0003x!Z\u0001ia\u001f\u0004\u0002\u0012\u001d7q\u0011CX)\u0019\t)+\"\u0003\u0006\f!9A\u0011^!A\u0002\u0011-\bb\u0002Cm\u0003\u0002\u0007A1\u001c\u0015\f\u0003\u000em4\u0011\u0011Cd\u0007\u000f#y\u000b\u0006\u0004\u0002&\u0016EQ1\u0003\u0005\b\tS\u0014\u0005\u0019\u0001Cz\u0011\u001d!IN\u0011a\u0001\t7D3BQB>\u0007\u0003#9ma\"\u00050\u0006!An\\1e)\u0011\t)+b\u0007\t\u000f\r]4\t1\u0001\u0002D!Z1ia\u001f\u0004\u0002\u0016}1q\u0011CXC\t)\t#\u0001\u000fVg\u0016\u0004#/Z1e]1|\u0017\r\u001a\u0015qCRD\u0017\u0006I5ogR,\u0017\r\u001a\u0018\u0015\r\u0005\u0015VQEC\u0014\u0011\u001d\u00199\b\u0012a\u0001\u0003\u0007Bqa!&E\u0001\u0004\t\u0019\u0005K\u0006E\u0007w\u001a\t)b\u000b\u0004\b\u0012=\u0016EAC\u0017\u0003-*6/\u001a\u0011sK\u0006$gFZ8s[\u0006$\bf]8ve\u000e,\u0017F\f7pC\u0012D\u0003/\u0019;iS\u0001Jgn\u001d;fC\u0012tCCBAS\u000bc)\u0019\u0004C\u0004\u0004\u0016\u0016\u0003\r!a\u0011\t\u000f\r\u0005V\t1\u0001\u0004$\"ZQia\u001f\u0004\u0002\u0016]2q\u0011CXC\t)I$\u0001\u001dVg\u0016\u0004#/Z1e]\u0019|'/\\1uQM|WO]2fS9z\u0007\u000f^5p]NDs\u000e\u001d;j_:\u001c\u0018F\f7pC\u0012D\u0013\u0006I5ogR,\u0017\r\u001a\u0018\u0015\r\u0005\u0015VQHC \u0011\u001d\u0019)J\u0012a\u0001\u0003\u0007Bqa!)G\u0001\u0004\u0019\t\fK\u0006G\u0007w\u001a\t)b\u000e\u0004\b\u0012=F\u0003CAS\u000b\u000b*9%\"\u0013\t\u000f\rUu\t1\u0001\u0002D!9!QO$A\u0002\t]\u0004bBBQ\u000f\u0002\u000711\u0015\u0015\f\u000f\u000em4\u0011QC'\u0007\u000f#y+\t\u0002\u0006P\u00059Uk]3!e\u0016\fGM\f4pe6\fG\u000fK:pkJ\u001cW-\u000b\u0018tG\",W.\u0019\u0015tG\",W.Y\u0015/_B$\u0018n\u001c8tQ=\u0004H/[8og&rCn\\1eQ%\u0002\u0013N\\:uK\u0006$g\u0006\u0006\u0005\u0002&\u0016MSQKC,\u0011\u001d\u0019)\n\u0013a\u0001\u0003\u0007BqA!\u001eI\u0001\u0004\u00119\bC\u0004\u0004\"\"\u0003\ra!-)\u0017!\u001bYh!!\u0006N\r\u001dEqV\u0001\u0005U\u0012\u00147\r\u0006\u0004\u0002&\u0016}S1\r\u0005\b\u000bCJ\u0005\u0019AA\"\u0003\r)(\u000f\u001c\u0005\b\t\u001bI\u0005\u0019AA\"Q-I51PBA\u000bO\u001a9\tb,\"\u0005\u0015%\u0014\u0001G+tK\u0002\u0012X-\u00193/U\u0012\u00147\rK\u0015!S:\u001cH/Z1e]Qq\u0011QUC7\u000b_*\t(\"\u001e\u0006z\u0015u\u0004bBC1\u0015\u0002\u0007\u00111\t\u0005\b\t\u001bQ\u0005\u0019AA\"\u0011\u001d)\u0019H\u0013a\u0001\u0003\u0007\n!bY8mk6tg*Y7f\u0011\u001d)9H\u0013a\u0001\u0007;\f!\u0002\\8xKJ\u0014u.\u001e8e\u0011\u001d)YH\u0013a\u0001\u0007;\f!\"\u001e9qKJ\u0014u.\u001e8e\u0011\u001d\u0019yP\u0013a\u0001\t\u0003A3BSB>\u0007\u0003+9ga\"\u00050RA\u0011QUCB\u000b\u000b+9\tC\u0004\u0006b-\u0003\r!a\u0011\t\u000f\u001151\n1\u0001\u0002D!9Q\u0011R&A\u0002\u0011}\u0012\u0001\u0003;iKB\u000b'\u000f^:)\u0017-\u001bYh!!\u0006h\r\u001dEq\u0016\u0015\u0004\u0001\u0015=\u0005\u0003BAD\u000b#KA!b%\u0002\n\n11\u000b^1cY\u0016\f!bU)M\u0007>tG/\u001a=u!\tAXj\u0005\u0004N;\u0016mU\u0011\u0015\t\u0004q\u0016u\u0015bACP'\n\u00192+\u0015'D_:$X\r\u001f;D_6\u0004\u0018M\\5p]B!Q1UCU\u001b\t))K\u0003\u0003\u0006(\u0006U\u0012AA5p\u0013\r\u0019XQ\u0015\u000b\u0003\u000b/\u0013abU)M\u0007>tG/\u001a=u\u00136\u0004H.A\u0006hKR|%o\u0011:fCR,Gc\u0001@\u00064\"9\u0011\u0011\u0001)A\u0002\u0005\u0015\u0001f\u0003)\u0004|\r\u0005UqWBD\u000bw\u000b#!\"/\u0002AU\u001bX\rI*qCJ\\7+Z:tS>tgFY;jY\u0012,'\u000fI5ogR,\u0017\rZ\u0011\u0003\u000b{\u000bQA\r\u00181]A\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!b1\u0011\t\u0015\u0015W1Z\u0007\u0003\u000b\u000fTA!\"3\u00026\u0005!A.\u00198h\u0013\u0011)i-b2\u0003\r=\u0013'.Z2u\u0001"
)
public abstract class SQLContext implements Logging, Serializable {
   private final SparkSession sparkSession;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   /** @deprecated */
   public static SQLContext getOrCreate(final SparkContext sparkContext) {
      return SQLContext$.MODULE$.getOrCreate(sparkContext);
   }

   /** @deprecated */
   public static void clearActive() {
      SQLContext$.MODULE$.clearActive();
   }

   /** @deprecated */
   public static void setActive(final SQLContext sqlContext) {
      SQLContext$.MODULE$.setActive(sqlContext);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public Dataset parquetFile(final String... paths) {
      return this.parquetFile((Seq).MODULE$.wrapRefArray((Object[])paths));
   }

   public SparkSession sparkSession() {
      return this.sparkSession;
   }

   public SparkContext sparkContext() {
      return this.sparkSession().sparkContext();
   }

   public abstract SQLContext newSession();

   public abstract ExecutionListenerManager listenerManager();

   public abstract void setConf(final Properties props);

   public void setConf(final String key, final String value) {
      this.sparkSession().conf().set(key, value);
   }

   public String getConf(final String key) {
      return this.sparkSession().conf().get(key);
   }

   public String getConf(final String key, final String defaultValue) {
      return this.sparkSession().conf().get(key, defaultValue);
   }

   public scala.collection.immutable.Map getAllConfs() {
      return this.sparkSession().conf().getAll();
   }

   @Experimental
   @Unstable
   public abstract ExperimentalMethods experimental();

   public Dataset emptyDataFrame() {
      return this.sparkSession().emptyDataFrame();
   }

   public abstract UDFRegistration udf();

   public abstract SQLImplicits implicits();

   public boolean isCached(final String tableName) {
      return this.sparkSession().catalog().isCached(tableName);
   }

   public void cacheTable(final String tableName) {
      this.sparkSession().catalog().cacheTable(tableName);
   }

   public void uncacheTable(final String tableName) {
      this.sparkSession().catalog().uncacheTable(tableName);
   }

   public void clearCache() {
      this.sparkSession().catalog().clearCache();
   }

   public Dataset createDataFrame(final RDD rdd, final TypeTags.TypeTag evidence$1) {
      return this.sparkSession().createDataFrame(rdd, evidence$1);
   }

   public Dataset createDataFrame(final Seq data, final TypeTags.TypeTag evidence$2) {
      return this.sparkSession().createDataFrame(data, evidence$2);
   }

   public Dataset baseRelationToDataFrame(final BaseRelation baseRelation) {
      return this.sparkSession().baseRelationToDataFrame(baseRelation);
   }

   @DeveloperApi
   public Dataset createDataFrame(final RDD rowRDD, final StructType schema) {
      return this.sparkSession().createDataFrame(rowRDD, schema);
   }

   public Dataset createDataset(final Seq data, final Encoder evidence$3) {
      return this.sparkSession().createDataset(data, evidence$3);
   }

   public Dataset createDataset(final RDD data, final Encoder evidence$4) {
      return this.sparkSession().createDataset(data, evidence$4);
   }

   public Dataset createDataset(final List data, final Encoder evidence$5) {
      return this.sparkSession().createDataset(data, evidence$5);
   }

   @DeveloperApi
   public Dataset createDataFrame(final JavaRDD rowRDD, final StructType schema) {
      return this.sparkSession().createDataFrame(rowRDD, schema);
   }

   @DeveloperApi
   public Dataset createDataFrame(final List rows, final StructType schema) {
      return this.sparkSession().createDataFrame(rows, schema);
   }

   public Dataset createDataFrame(final RDD rdd, final Class beanClass) {
      return this.sparkSession().createDataFrame(rdd, beanClass);
   }

   public Dataset createDataFrame(final JavaRDD rdd, final Class beanClass) {
      return this.sparkSession().createDataFrame(rdd, beanClass);
   }

   public Dataset createDataFrame(final List data, final Class beanClass) {
      return this.sparkSession().createDataFrame(data, beanClass);
   }

   public abstract DataFrameReader read();

   public abstract DataStreamReader readStream();

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String path) {
      return this.sparkSession().catalog().createTable(tableName, path);
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String path, final String source) {
      return this.sparkSession().catalog().createTable(tableName, path, source);
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final Map options) {
      return this.sparkSession().catalog().createTable(tableName, source, options);
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final scala.collection.immutable.Map options) {
      return this.sparkSession().catalog().createTable(tableName, source, options);
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final StructType schema, final Map options) {
      return this.sparkSession().catalog().createTable(tableName, source, schema, options);
   }

   /** @deprecated */
   public Dataset createExternalTable(final String tableName, final String source, final StructType schema, final scala.collection.immutable.Map options) {
      return this.sparkSession().catalog().createTable(tableName, source, schema, options);
   }

   public void dropTempTable(final String tableName) {
      this.sparkSession().catalog().dropTempView(tableName);
   }

   public Dataset range(final long end) {
      return this.sparkSession().range(end).toDF();
   }

   public Dataset range(final long start, final long end) {
      return this.sparkSession().range(start, end).toDF();
   }

   public Dataset range(final long start, final long end, final long step) {
      return this.sparkSession().range(start, end, step).toDF();
   }

   public Dataset range(final long start, final long end, final long step, final int numPartitions) {
      return this.sparkSession().range(start, end, step, numPartitions).toDF();
   }

   public Dataset sql(final String sqlText) {
      return this.sparkSession().sql(sqlText);
   }

   public Dataset table(final String tableName) {
      return this.sparkSession().table(tableName);
   }

   public Dataset tables() {
      return this.mapTableDatasetOutput(this.sparkSession().catalog().listTables());
   }

   public Dataset tables(final String databaseName) {
      return this.mapTableDatasetOutput(this.sparkSession().catalog().listTables(databaseName));
   }

   private Dataset mapTableDatasetOutput(final Dataset tables) {
      return tables.select((Seq).MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.when(functions$.MODULE$.coalesce((Seq).MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.array_size(functions$.MODULE$.col("namespace")), functions$.MODULE$.lit(BoxesRunTime.boxToInteger(0))})).equalTo(functions$.MODULE$.lit(BoxesRunTime.boxToInteger(1))), functions$.MODULE$.coalesce((Seq).MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.col("namespace").apply(BoxesRunTime.boxToInteger(0)), functions$.MODULE$.lit("")}))).otherwise(functions$.MODULE$.lit("")).as("namespace"), functions$.MODULE$.coalesce((Seq).MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.col("name"), functions$.MODULE$.lit("")})).as("tableName"), functions$.MODULE$.col("isTemporary")}));
   }

   public abstract StreamingQueryManager streams();

   public String[] tableNames() {
      return this.tableNames(this.sparkSession().catalog().currentDatabase());
   }

   public String[] tableNames(final String databaseName) {
      return (String[])this.sparkSession().catalog().listTables(databaseName).select((Seq).MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.col("name")})).as(Encoders$.MODULE$.STRING()).collect();
   }

   /** @deprecated */
   public Dataset applySchema(final RDD rowRDD, final StructType schema) {
      return this.createDataFrame(rowRDD, schema);
   }

   /** @deprecated */
   public Dataset applySchema(final JavaRDD rowRDD, final StructType schema) {
      return this.createDataFrame(rowRDD, schema);
   }

   /** @deprecated */
   public Dataset applySchema(final RDD rdd, final Class beanClass) {
      return this.createDataFrame(rdd, beanClass);
   }

   /** @deprecated */
   public Dataset applySchema(final JavaRDD rdd, final Class beanClass) {
      return this.createDataFrame(rdd, beanClass);
   }

   /** @deprecated */
   public Dataset parquetFile(final Seq paths) {
      return paths.isEmpty() ? this.emptyDataFrame() : this.read().parquet(paths);
   }

   /** @deprecated */
   public Dataset jsonFile(final String path) {
      return this.read().json(path);
   }

   /** @deprecated */
   public Dataset jsonFile(final String path, final StructType schema) {
      return this.read().schema(schema).json(path);
   }

   /** @deprecated */
   public Dataset jsonFile(final String path, final double samplingRatio) {
      return this.read().option("samplingRatio", Double.toString(samplingRatio)).json(path);
   }

   /** @deprecated */
   public Dataset jsonRDD(final RDD json) {
      return this.read().json(json);
   }

   /** @deprecated */
   public Dataset jsonRDD(final JavaRDD json) {
      return this.read().json(json);
   }

   /** @deprecated */
   public Dataset jsonRDD(final RDD json, final StructType schema) {
      return this.read().schema(schema).json(json);
   }

   /** @deprecated */
   public Dataset jsonRDD(final JavaRDD json, final StructType schema) {
      return this.read().schema(schema).json(json);
   }

   /** @deprecated */
   public Dataset jsonRDD(final RDD json, final double samplingRatio) {
      return this.read().option("samplingRatio", Double.toString(samplingRatio)).json(json);
   }

   /** @deprecated */
   public Dataset jsonRDD(final JavaRDD json, final double samplingRatio) {
      return this.read().option("samplingRatio", Double.toString(samplingRatio)).json(json);
   }

   /** @deprecated */
   public Dataset load(final String path) {
      return this.read().load(path);
   }

   /** @deprecated */
   public Dataset load(final String path, final String source) {
      return this.read().format(source).load(path);
   }

   /** @deprecated */
   public Dataset load(final String source, final Map options) {
      return this.read().options(options).format(source).load();
   }

   /** @deprecated */
   public Dataset load(final String source, final scala.collection.immutable.Map options) {
      return this.read().options((scala.collection.Map)options).format(source).load();
   }

   /** @deprecated */
   public Dataset load(final String source, final StructType schema, final Map options) {
      return this.read().format(source).schema(schema).options(options).load();
   }

   /** @deprecated */
   public Dataset load(final String source, final StructType schema, final scala.collection.immutable.Map options) {
      return this.read().format(source).schema(schema).options((scala.collection.Map)options).load();
   }

   /** @deprecated */
   public Dataset jdbc(final String url, final String table) {
      return this.read().jdbc(url, table, new Properties());
   }

   /** @deprecated */
   public Dataset jdbc(final String url, final String table, final String columnName, final long lowerBound, final long upperBound, final int numPartitions) {
      return this.read().jdbc(url, table, columnName, lowerBound, upperBound, numPartitions, new Properties());
   }

   /** @deprecated */
   public Dataset jdbc(final String url, final String table, final String[] theParts) {
      return this.read().jdbc(url, table, theParts, new Properties());
   }

   public SQLContext(final SparkSession sparkSession) {
      this.sparkSession = sparkSession;
      Logging.$init$(this);
   }
}
