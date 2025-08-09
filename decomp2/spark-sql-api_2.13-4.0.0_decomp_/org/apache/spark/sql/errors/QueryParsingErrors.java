package org.apache.spark.sql.errors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.AbstractDataType;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.math.BigDecimal;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019UsAB9s\u0011\u0003!HP\u0002\u0004\u007fe\"\u0005Ao \u0005\b\u0003'\tA\u0011AA\f\u0011\u001d\tI\"\u0001C\u0001\u00037Aq!a\u001a\u0002\t\u0003\tI\u0007C\u0004\u0002\u0004\u0006!\t!!\"\t\u000f\u0005\u001d\u0015\u0001\"\u0001\u0002\n\"9\u0011qU\u0001\u0005\u0002\u0005%\u0006bBAZ\u0003\u0011\u0005\u0011Q\u0017\u0005\b\u0003\u007f\u000bA\u0011AAa\u0011\u001d\t)-\u0001C\u0001\u0003\u000fDq!a3\u0002\t\u0003\ti\rC\u0004\u0002R\u0006!\t!a5\t\u000f\u0005]\u0017\u0001\"\u0001\u0002Z\"9\u0011q]\u0001\u0005\u0002\u0005%\bbBA|\u0003\u0011\u0005\u0011\u0011 \u0005\b\u0005\u000b\tA\u0011\u0001B\u0004\u0011\u001d\u0011Y!\u0001C\u0001\u0005\u001bAqA!\u0006\u0002\t\u0003\u00119\u0002C\u0004\u0003\u001c\u0005!\tA!\b\t\u000f\t\u0005\u0012\u0001\"\u0001\u0003$!9!qE\u0001\u0005\u0002\t%\u0002b\u0002B\u0017\u0003\u0011\u0005!q\u0006\u0005\b\u0005g\tA\u0011\u0001B\u001b\u0011\u001d\u0011I$\u0001C\u0001\u0005wAqAa\u0010\u0002\t\u0003\u0011\t\u0005C\u0004\u0003F\u0005!\tAa\u0012\t\u000f\t=\u0013\u0001\"\u0001\u0003R!9!1L\u0001\u0005\u0002\tu\u0003b\u0002B6\u0003\u0011\u0005!Q\u000e\u0005\b\u0005g\nA\u0011\u0001B;\u0011\u001d\u0011Y(\u0001C\u0001\u0005{BqA!#\u0002\t\u0003\u0011Y\tC\u0004\u0003\u0010\u0006!\tA!%\t\u000f\t}\u0015\u0001\"\u0001\u0003\"\"9!qV\u0001\u0005\u0002\tE\u0006b\u0002B`\u0003\u0011\u0005!\u0011\u0019\u0005\b\u0005+\fA\u0011\u0001Bl\u0011\u001d\u0011y.\u0001C\u0001\u0005CDqAa=\u0002\t\u0003\u0011)\u0010C\u0004\u0004\b\u0005!\ta!\u0003\t\u000f\r%\u0012\u0001\"\u0001\u0004,!91qF\u0001\u0005\u0002\rE\u0002bBB\u001f\u0003\u0011\u00051q\b\u0005\b\u0007\u0013\nA\u0011AB&\u0011\u001d\u00199&\u0001C\u0001\u00073Bqa!\u0019\u0002\t\u0003\u0019\u0019\u0007C\u0004\u0004r\u0005!\taa\u001d\t\u000f\re\u0014\u0001\"\u0001\u0004|!91\u0011Q\u0001\u0005\u0002\r\r\u0005bBBJ\u0003\u0011\u00051Q\u0013\u0005\b\u0007?\u000bA\u0011ABQ\u0011\u001d\u0019I+\u0001C\u0001\u0007WCqa!.\u0002\t\u0003\u00199\fC\u0004\u0004B\u0006!\taa1\t\u000f\r-\u0017\u0001\"\u0001\u0004N\"91\u0011\\\u0001\u0005\u0002\rm\u0007bBBs\u0003\u0011\u00051q\u001d\u0005\n\t\u0007\t\u0011\u0013!C\u0001\t\u000bAq\u0001b\u0007\u0002\t\u0003!i\u0002C\u0004\u0005&\u0005!\t\u0001b\n\t\u000f\u00115\u0012\u0001\"\u0001\u00050!9A\u0011H\u0001\u0005\u0002\u0011m\u0002b\u0002C#\u0003\u0011\u0005Aq\t\u0005\b\t+\nA\u0011\u0001C,\u0011\u001d!y&\u0001C\u0001\tCBq\u0001\"\u001b\u0002\t\u0003!Y\u0007C\u0004\u0005z\u0005!\t\u0001b\u001f\t\u000f\u0011E\u0015\u0001\"\u0001\u0005\u0014\"9A1T\u0001\u0005\u0002\u0011u\u0005b\u0002CS\u0003\u0011\u0005Aq\u0015\u0005\b\t[\u000bA\u0011\u0001CX\u0011\u001d!\u0019,\u0001C\u0001\tkCq\u0001\"1\u0002\t\u0003!\u0019\rC\u0004\u0005P\u0006!\t\u0001\"5\t\u000f\u0011m\u0017\u0001\"\u0001\u0005^\"9A1^\u0001\u0005\u0002\u00115\bb\u0002C|\u0003\u0011\u0005A\u0011 \u0005\b\u000b\u0007\tA\u0011AC\u0003\u0011\u001d)y!\u0001C\u0001\u000b#Aq!\"\u0006\u0002\t\u0003)9\u0002C\u0004\u0006\"\u0005!\t!b\t\t\u000f\u0015-\u0012\u0001\"\u0001\u0006.!9Q1H\u0001\u0005\u0002\u0015u\u0002bBC%\u0003\u0011\u0005Q1\n\u0005\b\u000b+\nA\u0011AC,\u0011\u001d)y&\u0001C\u0001\u000bCBq!b\u001b\u0002\t\u0003)i\u0007C\u0004\u0006r\u0005!\t!b\u001d\t\u000f\u0015u\u0014\u0001\"\u0001\u0006\u0000!9Q1Q\u0001\u0005\u0002\u0015\u0015\u0005bBCG\u0003\u0011\u0005Qq\u0012\u0005\b\u000b'\u000bA\u0011ACK\u0011\u001d)I*\u0001C\u0001\u000b7Cq!b(\u0002\t\u0003)\t\u000bC\u0004\u0006&\u0006!\t!b*\t\u000f\u0015=\u0016\u0001\"\u0001\u00062\"9Q\u0011X\u0001\u0005\u0002\u0015m\u0006bBCd\u0003\u0011\u0005Q\u0011\u001a\u0005\b\u000b+\fA\u0011ACl\u0011\u001d)y.\u0001C\u0001\u000bCDq!b:\u0002\t\u0003)I\u000fC\u0004\u0006p\u0006!\t!\"=\t\u000f\u0015U\u0018\u0001\"\u0001\u0006x\"9Q1`\u0001\u0005\u0002\u0015u\bb\u0002D\u0001\u0003\u0011\u0005a1\u0001\u0005\b\r\u0017\tA\u0011\u0001D\u0007\u0011%19#AI\u0001\n\u00031I\u0003C\u0005\u0007.\u0005\t\n\u0011\"\u0001\u00070!9a1G\u0001\u0005\u0002\u0019U\u0002b\u0002D \u0003\u0011\u0005a\u0011\t\u0005\b\r\u0013\nA\u0011\u0001D&\u0011\u001d1y%\u0001C\u0001\r#\n!#U;fef\u0004\u0016M]:j]\u001e,%O]8sg*\u00111\u000f^\u0001\u0007KJ\u0014xN]:\u000b\u0005U4\u0018aA:rY*\u0011q\u000f_\u0001\u0006gB\f'o\u001b\u0006\u0003sj\fa!\u00199bG\",'\"A>\u0002\u0007=\u0014x\r\u0005\u0002~\u00035\t!O\u0001\nRk\u0016\u0014\u0018\u0010U1sg&tw-\u0012:s_J\u001c8#B\u0001\u0002\u0002\u00055\u0001\u0003BA\u0002\u0003\u0013i!!!\u0002\u000b\u0005\u0005\u001d\u0011!B:dC2\f\u0017\u0002BA\u0006\u0003\u000b\u0011a!\u00118z%\u00164\u0007cA?\u0002\u0010%\u0019\u0011\u0011\u0003:\u0003%\u0011\u000bG/\u0019+za\u0016,%O]8sg\n\u000b7/Z\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tA0\u0001\fj]Z\fG.\u001b3J]N,'\u000f^%oi>,%O]8s)\u0011\ti\"!\u000e\u0011\t\u0005}\u0011q\u0006\b\u0005\u0003C\tYC\u0004\u0003\u0002$\u0005%RBAA\u0013\u0015\u0011\t9#!\u0006\u0002\rq\u0012xn\u001c;?\u0013\t\t9!\u0003\u0003\u0002.\u0005\u0015\u0011a\u00029bG.\fw-Z\u0005\u0005\u0003c\t\u0019DA\u0005UQJ|w/\u00192mK*!\u0011QFA\u0003\u0011\u001d\t9d\u0001a\u0001\u0003s\t1a\u0019;y!\u0011\tY$!\u0019\u000f\t\u0005u\u00121\f\b\u0005\u0003\u007f\t)F\u0004\u0003\u0002B\u0005Ec\u0002BA\"\u0003\u001frA!!\u0012\u0002N9!\u0011qIA&\u001d\u0011\t\u0019#!\u0013\n\u0003mL!!\u001f>\n\u0005]D\u0018BA;w\u0013\r\t\u0019\u0006^\u0001\tG\u0006$\u0018\r\\=ti&!\u0011qKA-\u0003\u0019\u0001\u0018M]:fe*\u0019\u00111\u000b;\n\t\u0005u\u0013qL\u0001\u000e'Fd')Y:f!\u0006\u00148/\u001a:\u000b\t\u0005]\u0013\u0011L\u0005\u0005\u0003G\n)GA\tJ]N,'\u000f^%oi>\u001cuN\u001c;fqRTA!!\u0018\u0002`\u0005\u0019\u0002/\u0019:tKJ\u001cF/Y2l\u001fZ,'O\u001a7poR!\u0011QDA6\u0011\u001d\ti\u0007\u0002a\u0001\u0003_\n\u0011\u0003]1sg\u0016\u0014(+\u001e7f\u0007>tG/\u001a=u!\u0011\t\t(a \u000e\u0005\u0005M$\u0002BA;\u0003o\nqA];oi&lWM\u0003\u0003\u0002z\u0005m\u0014A\u0001<5\u0015\r\tiH_\u0001\u0006C:$HN]\u0005\u0005\u0003\u0003\u000b\u0019HA\tQCJ\u001cXM\u001d*vY\u0016\u001cuN\u001c;fqR\f\u0001&\u001b8tKJ$xJ^3soJLG/\u001a#je\u0016\u001cGo\u001c:z+:\u001cX\u000f\u001d9peR,G-\u0012:s_J$\"!!\b\u0002K\r|G.^7o\u00032L\u0017m]%o\u001fB,'/\u0019;j_:tu\u000e^!mY><X\rZ#se>\u0014HCBA\u000f\u0003\u0017\u000by\nC\u0004\u0002\u000e\u001a\u0001\r!a$\u0002\u0005=\u0004\b\u0003BAI\u00033sA!a%\u0002\u0016B!\u00111EA\u0003\u0013\u0011\t9*!\u0002\u0002\rA\u0013X\rZ3g\u0013\u0011\tY*!(\u0003\rM#(/\u001b8h\u0015\u0011\t9*!\u0002\t\u000f\u0005]b\u00011\u0001\u0002\"B!\u00111HAR\u0013\u0011\t)+!\u001a\u0003#Q\u000b'\r\\3BY&\f7oQ8oi\u0016DH/\u0001\rf[B$\u0018pU8ve\u000e,gi\u001c:NKJ<W-\u0012:s_J$B!!\b\u0002,\"9\u0011qG\u0004A\u0002\u00055\u0006\u0003BA\u001e\u0003_KA!!-\u0002f\t)R*\u001a:hK&sGo\u001c+bE2,7i\u001c8uKb$\u0018aK5og\u0016\u0014H/\u001a3WC2,XMT;nE\u0016\u0014hj\u001c;NCR\u001c\u0007NR5fY\u0012tU/\u001c2fe\u0016\u0013(o\u001c:\u0015\t\u0005u\u0011q\u0017\u0005\b\u0003oA\u0001\u0019AA]!\u0011\tY$a/\n\t\u0005u\u0016Q\r\u0002\u0018\u001d>$X*\u0019;dQ\u0016$7\t\\1vg\u0016\u001cuN\u001c;fqR\fA%\\3sO\u0016\u001cF/\u0019;f[\u0016tGoV5uQ>,Ho\u00165f]\u000ec\u0017-^:f\u000bJ\u0014xN\u001d\u000b\u0005\u0003;\t\u0019\rC\u0004\u00028%\u0001\r!!,\u0002M9|g\u000eT1ti6\u000bGo\u00195fI\u000ec\u0017-^:f\u001f6LGoQ8oI&$\u0018n\u001c8FeJ|'\u000f\u0006\u0003\u0002\u001e\u0005%\u0007bBA\u001c\u0015\u0001\u0007\u0011QV\u0001*]>tG*Y:u\u001d>$X*\u0019;dQ\u0016$7\t\\1vg\u0016|U.\u001b;D_:$\u0017\u000e^5p]\u0016\u0013(o\u001c:\u0015\t\u0005u\u0011q\u001a\u0005\b\u0003oY\u0001\u0019AAW\u0003ErwN\u001c'bgRtu\u000e^'bi\u000eDW\r\u001a\"z'>,(oY3DY\u0006,8/Z(nSR\u001cuN\u001c3ji&|g.\u0012:s_J$B!!\b\u0002V\"9\u0011q\u0007\u0007A\u0002\u00055\u0016AF3naRL\b+\u0019:uSRLwN\\&fs\u0016\u0013(o\u001c:\u0015\r\u0005u\u00111\\Ap\u0011\u001d\ti.\u0004a\u0001\u0003\u001f\u000b1a[3z\u0011\u001d\t9$\u0004a\u0001\u0003C\u0004B!a\u000f\u0002d&!\u0011Q]A3\u0005Q\u0001\u0016M\u001d;ji&|gn\u00159fG\u000e{g\u000e^3yi\u0006A3\r\\1vg\u0016\u001cx+\u001b;i!&\u0004Xm\u00149fe\u0006$xN]:V]N,\b\u000f]8si\u0016$WI\u001d:peR1\u0011QDAv\u0003gDq!a\u000e\u000f\u0001\u0004\ti\u000f\u0005\u0003\u0002<\u0005=\u0018\u0002BAy\u0003K\u0012\u0001$U;fef|%oZ1oSj\fG/[8o\u0007>tG/\u001a=u\u0011\u001d\t)P\u0004a\u0001\u0003\u001f\u000bqa\u00197bkN,7/A\u001enk2$\u0018\u000e\u001d7f#V,'/\u001f*fgVdGo\u00117bkN,7oV5uQBK\u0007/Z(qKJ\fGo\u001c:t+:\u001cX\u000f\u001d9peR,G-\u0012:s_J$\u0002\"!\b\u0002|\u0006u(\u0011\u0001\u0005\b\u0003oy\u0001\u0019AAw\u0011\u001d\typ\u0004a\u0001\u0003\u001f\u000bqa\u00197bkN,\u0017\u0007C\u0004\u0003\u0004=\u0001\r!a$\u0002\u000f\rd\u0017-^:fe\u0005i3m\\7cS:\fG/[8o#V,'/\u001f*fgVdGo\u00117bkN,7/\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\t\u0005u!\u0011\u0002\u0005\b\u0003o\u0001\u0002\u0019AAw\u0003%\u0002\u0018\u000e]3Pa\u0016\u0014\u0018\r^8s\u0003\u001e<'/Z4bi\u0016,fn];qa>\u0014H/\u001a3DCN,WI\u001d:peR1\u0011Q\u0004B\b\u0005'AqA!\u0005\u0012\u0001\u0004\ty)\u0001\u0007dCN,\u0017I]4v[\u0016tG\u000fC\u0004\u00028E\u0001\r!a\u001c\u0002i]Lg\u000eZ8x\u00072\fWo]3J]BK\u0007/Z(qKJ\fGo\u001c:XQ\u0016\u0014Xm\u00117bkN,gj\u001c;BY2|w/\u001a3FeJ|'\u000f\u0006\u0003\u0002\u001e\te\u0001bBA\u001c%\u0001\u0007\u0011qN\u0001\u001dI&\u001cHO]5ckR,')_+ogV\u0004\bo\u001c:uK\u0012,%O]8s)\u0011\tiBa\b\t\u000f\u0005]2\u00031\u0001\u0002n\u0006\u0011CO]1og\u001a|'/\u001c(piN+\b\u000f]8siF+\u0018M\u001c;jM&,'/\u0012:s_J$B!!\b\u0003&!9\u0011q\u0007\u000bA\u0002\u0005=\u0014A\t;sC:\u001chm\u001c:n/&$\bnU3sI\u0016,fn];qa>\u0014H/\u001a3FeJ|'\u000f\u0006\u0003\u0002\u001e\t-\u0002bBA\u001c+\u0001\u0007\u0011qN\u0001,k:\u0004\u0018N^8u/&$\b\u000eU5w_RLeN\u0012:p[\u000ec\u0017-^:f\u001d>$\u0018\t\u001c7po\u0016$WI\u001d:peR!\u0011Q\u0004B\u0019\u0011\u001d\t9D\u0006a\u0001\u0003_\n1\u0006\\1uKJ\fGnV5uQBKgo\u001c;J]\u001a\u0013x.\\\"mCV\u001cXMT8u\u00032dwn^3e\u000bJ\u0014xN\u001d\u000b\u0005\u0003;\u00119\u0004C\u0004\u00028]\u0001\r!a\u001c\u0002[1\fG/\u001a:bY^KG\u000f[+oa&4x\u000e^%o\rJ|Wn\u00117bkN,gj\u001c;BY2|w/\u001a3FeJ|'\u000f\u0006\u0003\u0002\u001e\tu\u0002bBA\u001c1\u0001\u0007\u0011qN\u0001)Y\u0006$XM]1m\u0015>LgnV5uQV\u001b\u0018N\\4K_&tWK\\:vaB|'\u000f^3e\u000bJ\u0014xN\u001d\u000b\u0005\u0003;\u0011\u0019\u0005C\u0004\u00028e\u0001\r!a\u001c\u0002?Ut7/\u001e9q_J$X\r\u001a'bi\u0016\u0014\u0018\r\u001c&pS:$\u0016\u0010]3FeJ|'\u000f\u0006\u0004\u0002\u001e\t%#1\n\u0005\b\u0003oQ\u0002\u0019AA8\u0011\u001d\u0011iE\u0007a\u0001\u0003\u001f\u000b\u0001B[8j]RK\b/Z\u0001 S:4\u0018\r\\5e\u0019\u0006$XM]1m\u0015>LgNU3mCRLwN\\#se>\u0014H\u0003BA\u000f\u0005'Bq!a\u000e\u001c\u0001\u0004\u0011)\u0006\u0005\u0003\u0002<\t]\u0013\u0002\u0002B-\u0003K\u0012aCU3mCRLwN\u001c)sS6\f'/_\"p]R,\u0007\u0010^\u0001 e\u0016\u0004X\r^5uSZ,w+\u001b8e_^$UMZ5oSRLwN\\#se>\u0014HCBA\u000f\u0005?\u0012\u0019\u0007C\u0004\u0003bq\u0001\r!a$\u0002\t9\fW.\u001a\u0005\b\u0003oa\u0002\u0019\u0001B3!\u0011\tYDa\u001a\n\t\t%\u0014Q\r\u0002\u0014/&tGm\\<DY\u0006,8/Z\"p]R,\u0007\u0010^\u0001\u001cS:4\u0018\r\\5e/&tGm\\<SK\u001a,'/\u001a8dK\u0016\u0013(o\u001c:\u0015\r\u0005u!q\u000eB9\u0011\u001d\u0011\t'\ba\u0001\u0003\u001fCq!a\u000e\u001e\u0001\u0004\u0011)'A\u0011dC:tw\u000e\u001e*fg>dg/Z,j]\u0012|wOU3gKJ,gnY3FeJ|'\u000f\u0006\u0004\u0002\u001e\t]$\u0011\u0010\u0005\b\u0005Cr\u0002\u0019AAH\u0011\u001d\t9D\ba\u0001\u0005K\n!$\u001b8d_6\u0004\u0018\r^5cY\u0016Tu.\u001b8UsB,7/\u0012:s_J$\u0002\"!\b\u0003\u0000\t\r%q\u0011\u0005\b\u0005\u0003{\u0002\u0019AAH\u0003%Qw.\u001b8UsB,\u0017\u0007C\u0004\u0003\u0006~\u0001\r!a$\u0002\u0013)|\u0017N\u001c+za\u0016\u0014\u0004bBA\u001c?\u0001\u0007\u0011qN\u0001\u001eK6\u0004H/_%oaV$hi\u001c:UC\ndWmU1na2,WI\u001d:peR!\u0011Q\u0004BG\u0011\u001d\t9\u0004\ta\u0001\u0003_\n!\u0005^1cY\u0016\u001c\u0016-\u001c9mK\nK()\u001f;fgVs7/\u001e9q_J$X\rZ#se>\u0014HCBA\u000f\u0005'\u00139\nC\u0004\u0003\u0016\u0006\u0002\r!a$\u0002\u00075\u001cx\rC\u0004\u00028\u0005\u0002\rA!'\u0011\t\u0005m\"1T\u0005\u0005\u0005;\u000b)GA\nTC6\u0004H.Z'fi\"|GmQ8oi\u0016DH/A\u000fj]Z\fG.\u001b3CsR,G*\u001a8hi\"d\u0015\u000e^3sC2,%O]8s)\u0019\tiBa)\u0003(\"9!Q\u0015\u0012A\u0002\u0005=\u0015\u0001\u00032zi\u0016\u001c8\u000b\u001e:\t\u000f\u0005]\"\u00051\u0001\u0003*B!\u00111\bBV\u0013\u0011\u0011i+!\u001a\u0003)M\u000bW\u000e\u001d7f\u0005f\u0014\u0015\u0010^3t\u0007>tG/\u001a=u\u0003aIgN^1mS\u0012,5oY1qKN#(/\u001b8h\u000bJ\u0014xN\u001d\u000b\u0007\u0003;\u0011\u0019La.\t\u000f\tU6\u00051\u0001\u0002\u0010\u0006i\u0011N\u001c<bY&$Wi]2ba\u0016Dq!a\u000e$\u0001\u0004\u0011I\f\u0005\u0003\u0002<\tm\u0016\u0002\u0002B_\u0003K\u0012\u0001\u0003\u0015:fI&\u001c\u0017\r^3D_:$X\r\u001f;\u00025Q\u0014\u0018.\\(qi&|g.\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\r\u0005u!1\u0019Bg\u0011\u001d\u0011)\r\na\u0001\u0005\u000f\f!\u0002\u001e:j[>\u0003H/[8o!\u0011\t\u0019A!3\n\t\t-\u0017Q\u0001\u0002\u0004\u0013:$\bbBA\u001cI\u0001\u0007!q\u001a\t\u0005\u0003w\u0011\t.\u0003\u0003\u0003T\u0006\u0015$a\u0003+sS6\u001cuN\u001c;fqR\fADZ;oGRLwN\u001c(b[\u0016,fn];qa>\u0014H/\u001a3FeJ|'\u000f\u0006\u0004\u0002\u001e\te'Q\u001c\u0005\b\u00057,\u0003\u0019AAH\u000311WO\\2uS>tg*Y7f\u0011\u001d\t9$\na\u0001\u0003_\n\u0011dY1o]>$\b+\u0019:tKZ\u000bG.^3UsB,WI\u001d:peRA\u0011Q\u0004Br\u0005O\u0014Y\u000fC\u0004\u0003f\u001a\u0002\r!a$\u0002\u0013Y\fG.^3UsB,\u0007b\u0002BuM\u0001\u0007\u0011qR\u0001\u0006m\u0006dW/\u001a\u0005\b\u0003o1\u0003\u0019\u0001Bw!\u0011\tYDa<\n\t\tE\u0018Q\r\u0002\u0017)f\u0004XmQ8ogR\u0014Xo\u0019;pe\u000e{g\u000e^3yi\u0006\u0001C.\u001b;fe\u0006dg+\u00197vKRK\b/Z+ogV\u0004\bo\u001c:uK\u0012,%O]8s)!\tiBa>\u0003|\u000e\u0015\u0001b\u0002B}O\u0001\u0007\u0011qR\u0001\u0010k:\u001cX\u000f\u001d9peR,G\rV=qK\"9!Q`\u0014A\u0002\t}\u0018AD:vaB|'\u000f^3e)f\u0004Xm\u001d\t\u0007\u0003?\u0019\t!a$\n\t\r\r\u00111\u0007\u0002\u0004'\u0016\f\bbBA\u001cO\u0001\u0007!Q^\u0001 S:4\u0018\r\\5e\u001dVlWM]5d\u0019&$XM]1m%\u0006tw-Z#se>\u0014H\u0003DA\u000f\u0007\u0017\u0019ya!\u0007\u0004\u001e\r\u0005\u0002bBB\u0007Q\u0001\u0007\u0011qR\u0001\u0015e\u0006<8\u000b\u001e:jaB,G-U;bY&4\u0017.\u001a:\t\u000f\rE\u0001\u00061\u0001\u0004\u0014\u0005AQ.\u001b8WC2,X\r\u0005\u0003\u0002 \rU\u0011\u0002BB\f\u0003g\u0011!BQ5h\t\u0016\u001c\u0017.\\1m\u0011\u001d\u0019Y\u0002\u000ba\u0001\u0007'\t\u0001\"\\1y-\u0006dW/\u001a\u0005\b\u0007?A\u0003\u0019AAH\u0003!!\u0018\u0010]3OC6,\u0007bBA\u001cQ\u0001\u000711\u0005\t\u0005\u0003w\u0019)#\u0003\u0003\u0004(\u0005\u0015$!\u0004(v[\n,'oQ8oi\u0016DH/A\u0016n_J,G\u000b[1o\u001f:,gI]8n)>,f.\u001b;J]&sG/\u001a:wC2d\u0015\u000e^3sC2,%O]8s)\u0011\tib!\f\t\u000f\u0005]\u0012\u00061\u0001\u0002p\u0005A\u0012N\u001c<bY&$\u0017J\u001c;feZ\fGNR8s[\u0016\u0013(o\u001c:\u0015\r\u0005u11GB\u001b\u0011\u001d\u0011IO\u000ba\u0001\u0003\u001fCq!a\u000e+\u0001\u0004\u00199\u0004\u0005\u0003\u0002<\re\u0012\u0002BB\u001e\u0003K\u0012\u0011$T;mi&,f.\u001b;t\u0013:$XM\u001d<bY\u000e{g\u000e^3yi\u0006Y\u0012N\u001c<bY&$gI]8n)>,f.\u001b;WC2,X-\u0012:s_J$B!!\b\u0004B!9\u0011qG\u0016A\u0002\r\r\u0003\u0003BA\u001e\u0007\u000bJAaa\u0012\u0002f\t!\u0012J\u001c;feZ\fGNV1mk\u0016\u001cuN\u001c;fqR\faD\u001a:p[R{\u0017J\u001c;feZ\fG.\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\u0011\u0005u1QJB)\u0007+Bqaa\u0014-\u0001\u0004\ty)\u0001\u0003ge>l\u0007bBB*Y\u0001\u0007\u0011qR\u0001\u0003i>Dq!a\u000e-\u0001\u0004\ty'A\fnSb,G-\u00138uKJ4\u0018\r\\+oSR\u001cXI\u001d:peR1\u0011QDB.\u0007?Bqa!\u0018.\u0001\u0004\ty)A\u0004mSR,'/\u00197\t\u000f\u0005]R\u00061\u0001\u0002p\u0005AB-\u0019;b)f\u0004X-\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\r\u0005u1QMB5\u0011\u001d\u00199G\fa\u0001\u0003\u001f\u000b\u0001\u0002Z1uCRK\b/\u001a\u0005\b\u0003oq\u0003\u0019AB6!\u0011\tYd!\u001c\n\t\r=\u0014Q\r\u0002\u0019!JLW.\u001b;jm\u0016$\u0015\r^1UsB,7i\u001c8uKb$\u0018AG2iCJ$\u0016\u0010]3NSN\u001c\u0018N\\4MK:<G\u000f[#se>\u0014HCBA\u000f\u0007k\u001a9\bC\u0004\u0004h=\u0002\r!a$\t\u000f\u0005]r\u00061\u0001\u0004l\u0005\tc.Z:uK\u0012$\u0016\u0010]3NSN\u001c\u0018N\\4FY\u0016lWM\u001c;UsB,WI\u001d:peR1\u0011QDB?\u0007\u007fBqaa\u001a1\u0001\u0004\ty\tC\u0004\u00028A\u0002\raa\u001b\u0002EA\f'\u000f^5uS>tGK]1og\u001a|'/\u001c(pi\u0016C\b/Z2uK\u0012,%O]8s)!\tib!\"\u0004\b\u000e-\u0005b\u0002B1c\u0001\u0007\u0011q\u0012\u0005\b\u0007\u0013\u000b\u0004\u0019AAH\u0003\u0011)\u0007\u0010\u001d:\t\u000f\u0005]\u0012\u00071\u0001\u0004\u000eB!\u00111HBH\u0013\u0011\u0019\t*!\u001a\u0003+\u0005\u0003\b\u000f\\=Ue\u0006t7OZ8s[\u000e{g\u000e^3yi\u0006)sO]8oO:+XNY3s\u0003J<W/\\3oiN4uN\u001d+sC:\u001chm\u001c:n\u000bJ\u0014xN\u001d\u000b\t\u0003;\u00199j!'\u0004\u001e\"9!\u0011\r\u001aA\u0002\u0005=\u0005bBBNe\u0001\u0007!qY\u0001\nC\u000e$X/\u00197Ok6Dq!a\u000e3\u0001\u0004\u0019i)A\rj]Z\fG.\u001b3Ck\u000e\\W\r^:Ok6\u0014WM]#se>\u0014HCBA\u000f\u0007G\u001b9\u000bC\u0004\u0004&N\u0002\r!a$\u0002\u0011\u0011,7o\u0019:jE\u0016Dq!a\u000e4\u0001\u0004\u0019i)A\u0015dC:tw\u000e^\"mK\u0006t'+Z:feZ,GMT1nKN\u0004\u0018mY3Qe>\u0004XM\u001d;z\u000bJ\u0014xN\u001d\u000b\t\u0003;\u0019ik!-\u00044\"91q\u0016\u001bA\u0002\u0005=\u0015\u0001\u00039s_B,'\u000f^=\t\u000f\u0005]B\u00071\u0001\u0002p!9!Q\u0013\u001bA\u0002\u0005=\u0015a\u000b9s_B,'\u000f^5fg\u0006sG\r\u00122Qe>\u0004XM\u001d;jKN\u0014u\u000e\u001e5Ta\u0016\u001c\u0017NZ5fI\u0016\u0013(o\u001c:\u0015\t\u0005u1\u0011\u0018\u0005\b\u0003o)\u0004\u0019AB^!\u0011\tYd!0\n\t\r}\u0016Q\r\u0002\u0017\u0007J,\u0017\r^3OC6,7\u000f]1dK\u000e{g\u000e^3yi\u0006)3-\u00198o_R\u001cE.Z1o%\u0016\u001cXM\u001d<fIR\u000b'\r\\3Qe>\u0004XM\u001d;z\u000bJ\u0014xN\u001d\u000b\t\u0003;\u0019)ma2\u0004J\"91q\u0016\u001cA\u0002\u0005=\u0005bBA\u001cm\u0001\u0007\u0011q\u000e\u0005\b\u0005+3\u0004\u0019AAH\u0003y!W\u000f\u001d7jG\u0006$X\r\u001a+bE2,\u0007+\u0019;ig\u001a{WO\u001c3FeJ|'\u000f\u0006\u0005\u0002\u001e\r=71[Bl\u0011\u001d\u0019\tn\u000ea\u0001\u0003\u001f\u000bq\u0001]1uQ>sW\rC\u0004\u0004V^\u0002\r!a$\u0002\u000fA\fG\u000f\u001b+x_\"9\u0011qG\u001cA\u0002\u0005=\u0014!J:u_J,G-Q:B]\u0012\u001cFo\u001c:fI\nK(i\u001c;i'B,7-\u001b4jK\u0012,%O]8s)\u0011\tib!8\t\u000f\u0005]\u0002\b1\u0001\u0004`B!\u00111HBq\u0013\u0011\u0019\u0019/!\u001a\u0003/\r\u0013X-\u0019;f\r&dWMR8s[\u0006$8i\u001c8uKb$\u0018aK8qKJ\fG/[8o\u0013:D\u0015N^3TifdWmQ8n[\u0006tG-\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:\u0015\u0015\u0005u1\u0011^Bw\u0007c\u001cI\u0010C\u0004\u0004lf\u0002\r!a$\u0002\u0013=\u0004XM]1uS>t\u0007bBBxs\u0001\u0007\u0011qR\u0001\bG>lW.\u00198e\u0011\u001d\t9$\u000fa\u0001\u0007g\u0004B!a\u000f\u0004v&!1q_A3\u0005A\u0019F/\u0019;f[\u0016tGoQ8oi\u0016DH\u000fC\u0005\u0004|f\u0002\n\u00111\u0001\u0004~\u00061Qn]4PaR\u0004b!a\u0001\u0004\u0000\u0006=\u0015\u0002\u0002C\u0001\u0003\u000b\u0011aa\u00149uS>t\u0017!N8qKJ\fG/[8o\u0013:D\u0015N^3TifdWmQ8n[\u0006tG-\u00168tkB\u0004xN\u001d;fI\u0016\u0013(o\u001c:%I\u00164\u0017-\u001e7uIQ*\"\u0001b\u0002+\t\ruH\u0011B\u0016\u0003\t\u0017\u0001B\u0001\"\u0004\u0005\u00185\u0011Aq\u0002\u0006\u0005\t#!\u0019\"A\u0005v]\u000eDWmY6fI*!AQCA\u0003\u0003)\tgN\\8uCRLwN\\\u0005\u0005\t3!yAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\f\u0001d\u001c9fe\u0006$\u0018n\u001c8O_R\fE\u000e\\8xK\u0012,%O]8s)\u0019\ti\u0002b\b\u0005$!9A\u0011E\u001eA\u0002\u0005=\u0015aB7fgN\fw-\u001a\u0005\b\u0003oY\u0004\u0019AA8\u0003UIgN^1mS\u0012\u001cF/\u0019;f[\u0016tG/\u0012:s_J$b!!\b\u0005*\u0011-\u0002bBBvy\u0001\u0007\u0011q\u0012\u0005\b\u0003oa\u0004\u0019AA8\u0003\u0019\"Wm]2D_2,XN\u001c$peB\u000b'\u000f^5uS>tWK\\:vaB|'\u000f^3e\u000bJ\u0014xN\u001d\u000b\u0005\u0003;!\t\u0004C\u0004\u00028u\u0002\r\u0001b\r\u0011\t\u0005mBQG\u0005\u0005\to\t)GA\fEKN\u001c'/\u001b2f%\u0016d\u0017\r^5p]\u000e{g\u000e^3yi\u0006\t3m\\7qkR,7\u000b^1uSN$\u0018nY:O_R,\u0005\u0010]3di\u0016$WI\u001d:peR!\u0011Q\u0004C\u001f\u0011\u001d\t9D\u0010a\u0001\t\u007f\u0001B!a\u000f\u0005B%!A1IA3\u0005EIE-\u001a8uS\u001aLWM]\"p]R,\u0007\u0010^\u0001.C\u0012$7)\u0019;bY><\u0017J\\\"bG\",G+\u00192mK\u0006\u001b8+\u001a7fGRtu\u000e^!mY><X\rZ#se>\u0014HCBA\u000f\t\u0013\"i\u0005C\u0004\u0005L}\u0002\r!a$\u0002\rE,x\u000e^3e\u0011\u001d\t9d\u0010a\u0001\t\u001f\u0002B!a\u000f\u0005R%!A1KA3\u0005E\u0019\u0015m\u00195f)\u0006\u0014G.Z\"p]R,\u0007\u0010^\u0001\u001eg\"|wOR;oGRLwN\\:V]N,\b\u000f]8si\u0016$WI\u001d:peR1\u0011Q\u0004C-\t;Bq\u0001b\u0017A\u0001\u0004\ty)\u0001\u0006jI\u0016tG/\u001b4jKJDq!a\u000eA\u0001\u0004!y$\u0001\u0011tQ><h)\u001e8di&|gn]%om\u0006d\u0017\u000e\u001a)biR,'O\\#se>\u0014HCBA\u000f\tG\"9\u0007C\u0004\u0005f\u0005\u0003\r!a$\u0002\u000fA\fG\u000f^3s]\"9\u0011qG!A\u0002\u0005=\u0014\u0001\t3va2L7-\u0019;f\u0007R,G)\u001a4j]&$\u0018n\u001c8OC6,7/\u0012:s_J$b!!\b\u0005n\u0011E\u0004b\u0002C8\u0005\u0002\u0007\u0011qR\u0001\u000fIV\u0004H.[2bi\u0016t\u0015-\\3t\u0011\u001d\t9D\u0011a\u0001\tg\u0002B!a\u000f\u0005v%!AqOA3\u0005-\u0019E/Z:D_:$X\r\u001f;\u00029M\fHn\u0015;bi\u0016lWM\u001c;V]N,\b\u000f]8si\u0016$WI\u001d:peR1\u0011Q\u0004C?\t\u0003Cq\u0001b D\u0001\u0004\ty)A\u0004tc2$V\r\u001f;\t\u000f\u0011\r5\t1\u0001\u0005\u0006\u0006A\u0001o\\:ji&|g\u000e\u0005\u0003\u0005\b\u00125UB\u0001CE\u0015\u0011!Y)!\u0017\u0002\u000bQ\u0014X-Z:\n\t\u0011=E\u0011\u0012\u0002\u0007\u001fJLw-\u001b8\u0002-%tg/\u00197jI&#WM\u001c;jM&,'/\u0012:s_J$b!!\b\u0005\u0016\u0012e\u0005b\u0002CL\t\u0002\u0007\u0011qR\u0001\u0006S\u0012,g\u000e\u001e\u0005\b\u0003o!\u0005\u0019AA8\u0003U!W\u000f\u001d7jG\u0006$Xm\u00117bkN,7/\u0012:s_J$b!!\b\u0005 \u0012\r\u0006b\u0002CQ\u000b\u0002\u0007\u0011qR\u0001\u000bG2\fWo]3OC6,\u0007bBA\u001c\u000b\u0002\u0007\u0011qN\u0001\u0013IV\u0004H.[2bi\u0016\\U-_:FeJ|'\u000f\u0006\u0004\u0002\u001e\u0011%F1\u0016\u0005\b\u0003;4\u0005\u0019AAH\u0011\u001d\t9D\u0012a\u0001\u0003_\n\u0001&\u001e8fqB,7\r^3e\r>\u0014X.\u0019;G_J\u001cV\r^\"p]\u001aLw-\u001e:bi&|g.\u0012:s_J$B!!\b\u00052\"9\u0011qG$A\u0002\u0005=\u0014\u0001M5om\u0006d\u0017\u000e\u001a)s_B,'\u000f^=LKf4uN]*fiF+x\u000e^3e\u0007>tg-[4ve\u0006$\u0018n\u001c8FeJ|'\u000f\u0006\u0005\u0002\u001e\u0011]F1\u0018C`\u0011\u001d!I\f\u0013a\u0001\u0003\u001f\u000bAb[3z\u0007\u0006tG-\u001b3bi\u0016Dq\u0001\"0I\u0001\u0004\ty)\u0001\u0005wC2,Xm\u0015;s\u0011\u001d\t9\u0004\u0013a\u0001\u0003_\n!'\u001b8wC2LG\r\u0015:pa\u0016\u0014H/\u001f,bYV,gi\u001c:TKR\fVo\u001c;fI\u000e{gNZ5hkJ\fG/[8o\u000bJ\u0014xN\u001d\u000b\t\u0003;!)\r\"3\u0005N\"9AqY%A\u0002\u0005=\u0015A\u0004<bYV,7)\u00198eS\u0012\fG/\u001a\u0005\b\t\u0017L\u0005\u0019AAH\u0003\u0019YW-_*ue\"9\u0011qG%A\u0002\u0005=\u0014AK;oKb\u0004Xm\u0019;fI\u001a{'/\\1u\r>\u0014(+Z:fi\u000e{gNZ5hkJ\fG/[8o\u000bJ\u0014xN\u001d\u000b\u0005\u0003;!\u0019\u000eC\u0004\u00028)\u0003\r\u0001\"6\u0011\t\u0005mBq[\u0005\u0005\t3\f)GA\rSKN,GoQ8oM&<WO]1uS>t7i\u001c8uKb$\u0018\u0001H5oi\u0016\u0014h/\u00197WC2,XmT;u\u001f\u001a\u0014\u0016M\\4f\u000bJ\u0014xN\u001d\u000b\u0007\u0003;!y\u000eb9\t\u000f\u0011\u00058\n1\u0001\u0002\u0010\u0006)\u0011N\u001c9vi\"9\u0011qG&A\u0002\u0011\u0015\b\u0003BA\u001e\tOLA\u0001\";\u0002f\ty\u0011J\u001c;feZ\fGnQ8oi\u0016DH/A\u0013j]Z\fG.\u001b3US6,'l\u001c8f\t&\u001c\b\u000f\\1dK6,g\u000e\u001e,bYV,WI\u001d:peR!\u0011Q\u0004Cx\u0011\u001d\t9\u0004\u0014a\u0001\tc\u0004B!a\u000f\u0005t&!AQ_A3\u0005I\u0019V\r\u001e+j[\u0016TvN\\3D_:$X\r\u001f;\u0002M\r\u0014X-\u0019;f)\u0016l\u0007\u000fV1cY\u0016tu\u000e^*qK\u000eLg-\u001f)s_ZLG-\u001a:FeJ|'\u000f\u0006\u0003\u0002\u001e\u0011m\bbBA\u001c\u001b\u0002\u0007AQ \t\u0005\u0003w!y0\u0003\u0003\u0006\u0002\u0005\u0015$AE\"sK\u0006$X\rV1cY\u0016\u001cuN\u001c;fqR\f\u0011E]8x\r>\u0014X.\u0019;O_R,6/\u001a3XSRD7\u000b^8sK\u0012\f5/\u0012:s_J$B!!\b\u0006\b!9\u0011q\u0007(A\u0002\u0015%\u0001\u0003BA\u001e\u000b\u0017IA!\"\u0004\u0002f\t12I]3bi\u0016$\u0016M\u00197f\u0019&\\WmQ8oi\u0016DH/\u0001\u0016vg\u0016$UMZ5oK\u0012\u0014VmY8sIJ+\u0017\rZ3s\u001fJ<&/\u001b;fe\u000ec\u0017m]:fg\u0016\u0013(o\u001c:\u0015\t\u0005uQ1\u0003\u0005\b\u0003oy\u0005\u0019AA8\u00035\"\u0017N]3di>\u0014\u0018\u0010U1uQ\u0006sGm\u00149uS>t7\u000fU1uQ\n{G\u000f[*qK\u000eLg-[3e\u000bJ\u0014xN\u001d\u000b\u0005\u0003;)I\u0002C\u0004\u00028A\u0003\r!b\u0007\u0011\t\u0005mRQD\u0005\u0005\u000b?\t)GA\rJ]N,'\u000f^(wKJ<(/\u001b;f\t&\u00148i\u001c8uKb$\u0018aH;ogV\u0004\bo\u001c:uK\u0012dunY1m\r&dWmU2iK6,WI\u001d:peR1\u0011QDC\u0013\u000bOAq!a\u000eR\u0001\u0004)Y\u0002C\u0004\u0006*E\u0003\r!a$\u0002\u0019\u0005\u001cG/^1m'\u000eDW-\\1\u0002/%tg/\u00197jI\u001e\u0013x.\u001e9j]\u001e\u001cV\r^#se>\u0014HCBA\u000f\u000b_)\u0019\u0004C\u0004\u00062I\u0003\r!a$\u0002\u000f\u0015dW-\\3oi\"9\u0011q\u0007*A\u0002\u0015U\u0002\u0003BA\u001e\u000boIA!\"\u000f\u0002f\tArI]8va&tw-\u00118bYf$\u0018nY:D_:$X\r\u001f;\u0002C%$WM\u001c;jif\u001cu\u000e\\;n]Vs7/\u001e9q_J$X\r\u001a#bi\u0006$\u0016\u0010]3\u0015\r\u0005uQqHC$\u0011\u001d\t9d\u0015a\u0001\u000b\u0003\u0002B!a\u000f\u0006D%!QQIA3\u0005UIE-\u001a8uSRL8i\u001c7v[:\u001cuN\u001c;fqRDqaa\u001aT\u0001\u0004\ty)A\rjI\u0016tG/\u001b;z\u0007>dW/\u001c8JY2,w-\u00197Ti\u0016\u0004H\u0003BA\u000f\u000b\u001bBq!a\u000eU\u0001\u0004)y\u0005\u0005\u0003\u0002<\u0015E\u0013\u0002BC*\u0003K\u0012a#\u00133f]RLG/_\"pYN\u0003XmY\"p]R,\u0007\u0010^\u00010S\u0012,g\u000e^5us\u000e{G.^7o\tV\u0004H.[2bi\u0016$7+Z9vK:\u001cWmR3oKJ\fGo\u001c:PaRLwN\u001c\u000b\u0007\u0003;)I&b\u0017\t\u000f\u0005]R\u000b1\u0001\u0006P!9QQL+A\u0002\u0005=\u0015aF:fcV,gnY3HK:,'/\u0019;pe>\u0003H/[8o\u00031\u001a'/Z1uKZKWm^,ji\"\u0014u\u000e\u001e5JM:{G/\u0012=jgR\u001c\u0018I\u001c3SKBd\u0017mY3FeJ|'\u000f\u0006\u0003\u0002\u001e\u0015\r\u0004bBA\u001c-\u0002\u0007QQ\r\t\u0005\u0003w)9'\u0003\u0003\u0006j\u0005\u0015$!E\"sK\u0006$XMV5fo\u000e{g\u000e^3yi\u0006\u0011C/Z7q_J\f'/\u001f,jK^<\u0016\u000e\u001e5TG\",W.\u0019\"j]\u0012LgnZ'pI\u0016$B!!\b\u0006p!9\u0011qG,A\u0002\rM\u0018!\u00079be\u0006lW\r^3s\u001b\u0006\u00148.\u001a:O_R\fE\u000e\\8xK\u0012$b!!\b\u0006v\u0015e\u0004bBC<1\u0002\u0007\u0011qR\u0001\ngR\fG/Z7f]RDq!b\u001fY\u0001\u0004!))\u0001\u0004pe&<\u0017N\\\u0001#I\u00164\u0017N\\3UK6\u0004h+[3x/&$\b.\u00134O_R,\u00050[:ug\u0016\u0013(o\u001c:\u0015\t\u0005uQ\u0011\u0011\u0005\b\u0003oI\u0006\u0019AC3\u0003\u001drw\u000e^!mY><X\r\u001a+p\u0003\u0012$GI\u0011)sK\u001aL\u0007PR8s)\u0016l\u0007OV5fo\u0016\u0013(o\u001c:\u0015\r\u0005uQqQCF\u0011\u001d)II\u0017a\u0001\u0005\u007f\f\u0011B\\1nKB\u000b'\u000f^:\t\u000f\u0005]\"\f1\u0001\u0006f\u0005a3M]3bi\u00164UO\\2XSRD'i\u001c;i\u0013\u001atu\u000e^#ySN$8/\u00118e%\u0016\u0004H.Y2f\u000bJ\u0014xN\u001d\u000b\u0005\u0003;)\t\nC\u0004\u00028m\u0003\r!a\u001c\u0002G\r\u0014X-\u0019;f\rVt7mV5uQ\u001e+g.\u001a:bi\u0016$7i\u001c7v[:\u001cXI\u001d:peR!\u0011QDCL\u0011\u001d\t9\u0004\u0018a\u0001\u0003_\nQd\u0019:fCR,g)\u001e8d/&$\bnQ8ogR\u0014\u0018-\u001b8u\u000bJ\u0014xN\u001d\u000b\u0005\u0003;)i\nC\u0004\u00028u\u0003\r!a\u001c\u0002E\u0011,g-\u001b8f)\u0016l\u0007OR;oG^KG\u000f[%g\u001d>$X\t_5tiN,%O]8s)\u0011\ti\"b)\t\u000f\u0005]b\f1\u0001\u0002p\u0005aRO\\:vaB|'\u000f^3e\rVt7\r^5p]:\u000bW.Z#se>\u0014HCBA\u000f\u000bS+i\u000bC\u0004\u0006,~\u0003\rAa@\u0002\u0011\u0019,hn\u0019(b[\u0016Dq!a\u000e`\u0001\u0004\ty'A\u0011ta\u0016\u001c\u0017NZ=j]\u001e$%)\u00138De\u0016\fG/\u001a+f[B4UO\\2FeJ|'\u000f\u0006\u0004\u0002\u001e\u0015MVq\u0017\u0005\b\u000bk\u0003\u0007\u0019AAH\u00031!\u0017\r^1cCN,g*Y7f\u0011\u001d\t9\u0004\u0019a\u0001\u0003_\n1%\u001b8wC2LG\rV1cY\u00164\u0016\r\\;fI\u001a+hn\u0019;j_:t\u0015-\\3FeJ|'\u000f\u0006\u0004\u0002\u001e\u0015uVq\u0018\u0005\b\u0005C\n\u0007\u0019\u0001B\u0000\u0011\u001d\t9$\u0019a\u0001\u000b\u0003\u0004B!a\u000f\u0006D&!QQYA3\u0005i!\u0016M\u00197f-\u0006dW/\u001a3Gk:\u001cG/[8o\u0007>tG/\u001a=u\u0003u)hn\u00197pg\u0016$'I]1dW\u0016$X\rZ\"p[6,g\u000e^#se>\u0014H\u0003CA\u000f\u000b\u0017,i-\"5\t\u000f\r=(\r1\u0001\u0002\u0010\"9Qq\u001a2A\u0002\u0011\u0015\u0015!B:uCJ$\bbBCjE\u0002\u0007AQQ\u0001\u0005gR|\u0007/A\u000bj]Z\fG.\u001b3US6,GK]1wK2\u001c\u0006/Z2\u0015\r\u0005uQ\u0011\\Co\u0011\u001d)Yn\u0019a\u0001\u0003\u001f\u000baA]3bg>t\u0007bBA\u001cG\u0002\u0007\u0011qN\u0001\u001bS:4\u0018\r\\5e\u001d\u0006lWMR8s\tJ|\u0007\u000fV3na\u001a+hn\u0019\u000b\u0007\u0003;)\u0019/\":\t\u000f\t\u0005D\r1\u0001\u0003\u0000\"9\u0011q\u00073A\u0002\u0005=\u0014\u0001G5om\u0006d\u0017\u000e\u001a(b[\u00164uN]*fi\u000e\u000bG/\u00197pOR1\u0011QDCv\u000b[DqA!\u0019f\u0001\u0004\u0011y\u0010C\u0004\u00028\u0015\u0004\r!a\u001c\u0002G\u0011,g-Y;mi\u000e{G.^7o\u001d>$\u0018*\u001c9mK6,g\u000e^3e3\u0016$XI\u001d:peR!\u0011QDCz\u0011\u001d\t9D\u001aa\u0001\u0003_\nA\u0004Z3gCVdGoQ8mk6tgj\u001c;F]\u0006\u0014G.\u001a3FeJ|'\u000f\u0006\u0003\u0002\u001e\u0015e\bbBA\u001cO\u0002\u0007\u0011qN\u00011I\u00164\u0017-\u001e7u\u0007>dW/\u001c8SK\u001a,'/\u001a8dKNtu\u000e^!mY><X\rZ%o!\u0006\u0014H/\u001b;j_:\u001c\u0006/Z2\u0015\t\u0005uQq \u0005\b\u0003oA\u0007\u0019AA8\u0003m!W\u000f\u001d7jG\u0006$X-\u0011:hk6,g\u000e\u001e(b[\u0016\u001cXI\u001d:peR1\u0011Q\u0004D\u0003\r\u0013AqAb\u0002j\u0001\u0004\u0011y0A\u0005be\u001e,X.\u001a8ug\"9\u0011qG5A\u0002\u0005=\u0014A\b3va2L7-\u0019;f)\u0006\u0014G.Z\"pYVlg\u000eR3tGJL\u0007\u000f^8s)1\tiBb\u0004\u0007\u0012\u0019Ua\u0011\u0004D\u0012\u0011\u001d\t9D\u001ba\u0001\u0003_BqAb\u0005k\u0001\u0004\ty)\u0001\u0006d_2,XN\u001c(b[\u0016DqAb\u0006k\u0001\u0004\ty)\u0001\u0006paRLwN\u001c(b[\u0016D\u0011Bb\u0007k!\u0003\u0005\rA\"\b\u0002\u0011%\u001c8I]3bi\u0016\u0004B!a\u0001\u0007 %!a\u0011EA\u0003\u0005\u001d\u0011un\u001c7fC:D\u0011B\"\nk!\u0003\u0005\r!a$\u0002\u0013\u0005dG/\u001a:UsB,\u0017\u0001\u000b3va2L7-\u0019;f)\u0006\u0014G.Z\"pYVlg\u000eR3tGJL\u0007\u000f^8sI\u0011,g-Y;mi\u0012\"TC\u0001D\u0016U\u00111i\u0002\"\u0003\u0002Q\u0011,\b\u000f\\5dCR,G+\u00192mK\u000e{G.^7o\t\u0016\u001c8M]5qi>\u0014H\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0019E\"\u0006BAH\t\u0013\t\u0001$\u001b8wC2LG\rR1uKRLW.Z+oSR,%O]8s)!\tiBb\u000e\u0007:\u0019m\u0002bBA\u001c[\u0002\u0007\u0011q\u000e\u0005\b\u00057l\u0007\u0019AAH\u0011\u001d1i$\u001ca\u0001\u0003\u001f\u000bA\"\u001b8wC2LGMV1mk\u0016\f\u0001(\u001b8wC2LG\rV1cY\u00164UO\\2uS>t\u0017\nZ3oi&4\u0017.\u001a:Be\u001e,X.\u001a8u\u001b&\u001c8/\u001b8h!\u0006\u0014XM\u001c;iKN,7\u000f\u0006\u0004\u0002\u001e\u0019\rcQ\t\u0005\b\u0003oq\u0007\u0019AA8\u0011\u001d19E\u001ca\u0001\u0003\u001f\u000bA\"\u0019:hk6,g\u000e\u001e(b[\u0016\f!d\u00197vgR,'OQ=XSRD\u0007+\u0019:uSRLwN\\3e\u0005f$B!!\b\u0007N!9\u0011qG8A\u0002\u0005=\u0014AF2mkN$XM\u001d\"z/&$\bNQ;dW\u0016$\u0018N\\4\u0015\t\u0005ua1\u000b\u0005\b\u0003o\u0001\b\u0019AA8\u0001"
)
public final class QueryParsingErrors {
   public static Throwable clusterByWithBucketing(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.clusterByWithBucketing(ctx);
   }

   public static Throwable clusterByWithPartitionedBy(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.clusterByWithPartitionedBy(ctx);
   }

   public static Throwable invalidTableFunctionIdentifierArgumentMissingParentheses(final ParserRuleContext ctx, final String argumentName) {
      return QueryParsingErrors$.MODULE$.invalidTableFunctionIdentifierArgumentMissingParentheses(ctx, argumentName);
   }

   public static Throwable invalidDatetimeUnitError(final ParserRuleContext ctx, final String functionName, final String invalidValue) {
      return QueryParsingErrors$.MODULE$.invalidDatetimeUnitError(ctx, functionName, invalidValue);
   }

   public static String duplicateTableColumnDescriptor$default$5() {
      return QueryParsingErrors$.MODULE$.duplicateTableColumnDescriptor$default$5();
   }

   public static boolean duplicateTableColumnDescriptor$default$4() {
      return QueryParsingErrors$.MODULE$.duplicateTableColumnDescriptor$default$4();
   }

   public static Throwable duplicateTableColumnDescriptor(final ParserRuleContext ctx, final String columnName, final String optionName, final boolean isCreate, final String alterType) {
      return QueryParsingErrors$.MODULE$.duplicateTableColumnDescriptor(ctx, columnName, optionName, isCreate, alterType);
   }

   public static Throwable duplicateArgumentNamesError(final Seq arguments, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.duplicateArgumentNamesError(arguments, ctx);
   }

   public static Throwable defaultColumnReferencesNotAllowedInPartitionSpec(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.defaultColumnReferencesNotAllowedInPartitionSpec(ctx);
   }

   public static Throwable defaultColumnNotEnabledError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.defaultColumnNotEnabledError(ctx);
   }

   public static Throwable defaultColumnNotImplementedYetError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.defaultColumnNotImplementedYetError(ctx);
   }

   public static Throwable invalidNameForSetCatalog(final Seq name, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidNameForSetCatalog(name, ctx);
   }

   public static Throwable invalidNameForDropTempFunc(final Seq name, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidNameForDropTempFunc(name, ctx);
   }

   public static Throwable invalidTimeTravelSpec(final String reason, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidTimeTravelSpec(reason, ctx);
   }

   public static Throwable unclosedBracketedCommentError(final String command, final Origin start, final Origin stop) {
      return QueryParsingErrors$.MODULE$.unclosedBracketedCommentError(command, start, stop);
   }

   public static Throwable invalidTableValuedFunctionNameError(final Seq name, final SqlBaseParser.TableValuedFunctionContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidTableValuedFunctionNameError(name, ctx);
   }

   public static Throwable specifyingDBInCreateTempFuncError(final String databaseName, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.specifyingDBInCreateTempFuncError(databaseName, ctx);
   }

   public static Throwable unsupportedFunctionNameError(final Seq funcName, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.unsupportedFunctionNameError(funcName, ctx);
   }

   public static Throwable defineTempFuncWithIfNotExistsError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.defineTempFuncWithIfNotExistsError(ctx);
   }

   public static Throwable createFuncWithConstraintError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.createFuncWithConstraintError(ctx);
   }

   public static Throwable createFuncWithGeneratedColumnsError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.createFuncWithGeneratedColumnsError(ctx);
   }

   public static Throwable createFuncWithBothIfNotExistsAndReplaceError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.createFuncWithBothIfNotExistsAndReplaceError(ctx);
   }

   public static Throwable notAllowedToAddDBPrefixForTempViewError(final Seq nameParts, final SqlBaseParser.CreateViewContext ctx) {
      return QueryParsingErrors$.MODULE$.notAllowedToAddDBPrefixForTempViewError(nameParts, ctx);
   }

   public static Throwable defineTempViewWithIfNotExistsError(final SqlBaseParser.CreateViewContext ctx) {
      return QueryParsingErrors$.MODULE$.defineTempViewWithIfNotExistsError(ctx);
   }

   public static Throwable parameterMarkerNotAllowed(final String statement, final Origin origin) {
      return QueryParsingErrors$.MODULE$.parameterMarkerNotAllowed(statement, origin);
   }

   public static Throwable temporaryViewWithSchemaBindingMode(final SqlBaseParser.StatementContext ctx) {
      return QueryParsingErrors$.MODULE$.temporaryViewWithSchemaBindingMode(ctx);
   }

   public static Throwable createViewWithBothIfNotExistsAndReplaceError(final SqlBaseParser.CreateViewContext ctx) {
      return QueryParsingErrors$.MODULE$.createViewWithBothIfNotExistsAndReplaceError(ctx);
   }

   public static Throwable identityColumnDuplicatedSequenceGeneratorOption(final SqlBaseParser.IdentityColSpecContext ctx, final String sequenceGeneratorOption) {
      return QueryParsingErrors$.MODULE$.identityColumnDuplicatedSequenceGeneratorOption(ctx, sequenceGeneratorOption);
   }

   public static Throwable identityColumnIllegalStep(final SqlBaseParser.IdentityColSpecContext ctx) {
      return QueryParsingErrors$.MODULE$.identityColumnIllegalStep(ctx);
   }

   public static Throwable identityColumnUnsupportedDataType(final SqlBaseParser.IdentityColumnContext ctx, final String dataType) {
      return QueryParsingErrors$.MODULE$.identityColumnUnsupportedDataType(ctx, dataType);
   }

   public static Throwable invalidGroupingSetError(final String element, final SqlBaseParser.GroupingAnalyticsContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidGroupingSetError(element, ctx);
   }

   public static Throwable unsupportedLocalFileSchemeError(final SqlBaseParser.InsertOverwriteDirContext ctx, final String actualSchema) {
      return QueryParsingErrors$.MODULE$.unsupportedLocalFileSchemeError(ctx, actualSchema);
   }

   public static Throwable directoryPathAndOptionsPathBothSpecifiedError(final SqlBaseParser.InsertOverwriteDirContext ctx) {
      return QueryParsingErrors$.MODULE$.directoryPathAndOptionsPathBothSpecifiedError(ctx);
   }

   public static Throwable useDefinedRecordReaderOrWriterClassesError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.useDefinedRecordReaderOrWriterClassesError(ctx);
   }

   public static Throwable rowFormatNotUsedWithStoredAsError(final SqlBaseParser.CreateTableLikeContext ctx) {
      return QueryParsingErrors$.MODULE$.rowFormatNotUsedWithStoredAsError(ctx);
   }

   public static Throwable createTempTableNotSpecifyProviderError(final SqlBaseParser.CreateTableContext ctx) {
      return QueryParsingErrors$.MODULE$.createTempTableNotSpecifyProviderError(ctx);
   }

   public static Throwable invalidTimeZoneDisplacementValueError(final SqlBaseParser.SetTimeZoneContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidTimeZoneDisplacementValueError(ctx);
   }

   public static Throwable intervalValueOutOfRangeError(final String input, final SqlBaseParser.IntervalContext ctx) {
      return QueryParsingErrors$.MODULE$.intervalValueOutOfRangeError(input, ctx);
   }

   public static Throwable unexpectedFormatForResetConfigurationError(final SqlBaseParser.ResetConfigurationContext ctx) {
      return QueryParsingErrors$.MODULE$.unexpectedFormatForResetConfigurationError(ctx);
   }

   public static Throwable invalidPropertyValueForSetQuotedConfigurationError(final String valueCandidate, final String keyStr, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidPropertyValueForSetQuotedConfigurationError(valueCandidate, keyStr, ctx);
   }

   public static Throwable invalidPropertyKeyForSetQuotedConfigurationError(final String keyCandidate, final String valueStr, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidPropertyKeyForSetQuotedConfigurationError(keyCandidate, valueStr, ctx);
   }

   public static Throwable unexpectedFormatForSetConfigurationError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.unexpectedFormatForSetConfigurationError(ctx);
   }

   public static Throwable duplicateKeysError(final String key, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.duplicateKeysError(key, ctx);
   }

   public static Throwable duplicateClausesError(final String clauseName, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.duplicateClausesError(clauseName, ctx);
   }

   public static Throwable invalidIdentifierError(final String ident, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidIdentifierError(ident, ctx);
   }

   public static Throwable sqlStatementUnsupportedError(final String sqlText, final Origin position) {
      return QueryParsingErrors$.MODULE$.sqlStatementUnsupportedError(sqlText, position);
   }

   public static Throwable duplicateCteDefinitionNamesError(final String duplicateNames, final SqlBaseParser.CtesContext ctx) {
      return QueryParsingErrors$.MODULE$.duplicateCteDefinitionNamesError(duplicateNames, ctx);
   }

   public static Throwable showFunctionsInvalidPatternError(final String pattern, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.showFunctionsInvalidPatternError(pattern, ctx);
   }

   public static Throwable showFunctionsUnsupportedError(final String identifier, final SqlBaseParser.IdentifierContext ctx) {
      return QueryParsingErrors$.MODULE$.showFunctionsUnsupportedError(identifier, ctx);
   }

   public static Throwable addCatalogInCacheTableAsSelectNotAllowedError(final String quoted, final SqlBaseParser.CacheTableContext ctx) {
      return QueryParsingErrors$.MODULE$.addCatalogInCacheTableAsSelectNotAllowedError(quoted, ctx);
   }

   public static Throwable computeStatisticsNotExpectedError(final SqlBaseParser.IdentifierContext ctx) {
      return QueryParsingErrors$.MODULE$.computeStatisticsNotExpectedError(ctx);
   }

   public static Throwable descColumnForPartitionUnsupportedError(final SqlBaseParser.DescribeRelationContext ctx) {
      return QueryParsingErrors$.MODULE$.descColumnForPartitionUnsupportedError(ctx);
   }

   public static Throwable invalidStatementError(final String operation, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidStatementError(operation, ctx);
   }

   public static Throwable operationNotAllowedError(final String message, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.operationNotAllowedError(message, ctx);
   }

   public static Option operationInHiveStyleCommandUnsupportedError$default$4() {
      return QueryParsingErrors$.MODULE$.operationInHiveStyleCommandUnsupportedError$default$4();
   }

   public static Throwable operationInHiveStyleCommandUnsupportedError(final String operation, final String command, final SqlBaseParser.StatementContext ctx, final Option msgOpt) {
      return QueryParsingErrors$.MODULE$.operationInHiveStyleCommandUnsupportedError(operation, command, ctx, msgOpt);
   }

   public static Throwable storedAsAndStoredByBothSpecifiedError(final SqlBaseParser.CreateFileFormatContext ctx) {
      return QueryParsingErrors$.MODULE$.storedAsAndStoredByBothSpecifiedError(ctx);
   }

   public static Throwable duplicatedTablePathsFoundError(final String pathOne, final String pathTwo, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.duplicatedTablePathsFoundError(pathOne, pathTwo, ctx);
   }

   public static Throwable cannotCleanReservedTablePropertyError(final String property, final ParserRuleContext ctx, final String msg) {
      return QueryParsingErrors$.MODULE$.cannotCleanReservedTablePropertyError(property, ctx, msg);
   }

   public static Throwable propertiesAndDbPropertiesBothSpecifiedError(final SqlBaseParser.CreateNamespaceContext ctx) {
      return QueryParsingErrors$.MODULE$.propertiesAndDbPropertiesBothSpecifiedError(ctx);
   }

   public static Throwable cannotCleanReservedNamespacePropertyError(final String property, final ParserRuleContext ctx, final String msg) {
      return QueryParsingErrors$.MODULE$.cannotCleanReservedNamespacePropertyError(property, ctx, msg);
   }

   public static Throwable invalidBucketsNumberError(final String describe, final SqlBaseParser.ApplyTransformContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidBucketsNumberError(describe, ctx);
   }

   public static Throwable wrongNumberArgumentsForTransformError(final String name, final int actualNum, final SqlBaseParser.ApplyTransformContext ctx) {
      return QueryParsingErrors$.MODULE$.wrongNumberArgumentsForTransformError(name, actualNum, ctx);
   }

   public static Throwable partitionTransformNotExpectedError(final String name, final String expr, final SqlBaseParser.ApplyTransformContext ctx) {
      return QueryParsingErrors$.MODULE$.partitionTransformNotExpectedError(name, expr, ctx);
   }

   public static Throwable nestedTypeMissingElementTypeError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return QueryParsingErrors$.MODULE$.nestedTypeMissingElementTypeError(dataType, ctx);
   }

   public static Throwable charTypeMissingLengthError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return QueryParsingErrors$.MODULE$.charTypeMissingLengthError(dataType, ctx);
   }

   public static Throwable dataTypeUnsupportedError(final String dataType, final SqlBaseParser.PrimitiveDataTypeContext ctx) {
      return QueryParsingErrors$.MODULE$.dataTypeUnsupportedError(dataType, ctx);
   }

   public static Throwable mixedIntervalUnitsError(final String literal, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.mixedIntervalUnitsError(literal, ctx);
   }

   public static Throwable fromToIntervalUnsupportedError(final String from, final String to, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.fromToIntervalUnsupportedError(from, to, ctx);
   }

   public static Throwable invalidFromToUnitValueError(final SqlBaseParser.IntervalValueContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidFromToUnitValueError(ctx);
   }

   public static Throwable invalidIntervalFormError(final String value, final SqlBaseParser.MultiUnitsIntervalContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidIntervalFormError(value, ctx);
   }

   public static Throwable moreThanOneFromToUnitInIntervalLiteralError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.moreThanOneFromToUnitInIntervalLiteralError(ctx);
   }

   public static Throwable invalidNumericLiteralRangeError(final String rawStrippedQualifier, final BigDecimal minValue, final BigDecimal maxValue, final String typeName, final SqlBaseParser.NumberContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidNumericLiteralRangeError(rawStrippedQualifier, minValue, maxValue, typeName, ctx);
   }

   public static Throwable literalValueTypeUnsupportedError(final String unsupportedType, final Seq supportedTypes, final SqlBaseParser.TypeConstructorContext ctx) {
      return QueryParsingErrors$.MODULE$.literalValueTypeUnsupportedError(unsupportedType, supportedTypes, ctx);
   }

   public static Throwable cannotParseValueTypeError(final String valueType, final String value, final SqlBaseParser.TypeConstructorContext ctx) {
      return QueryParsingErrors$.MODULE$.cannotParseValueTypeError(valueType, value, ctx);
   }

   public static Throwable functionNameUnsupportedError(final String functionName, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.functionNameUnsupportedError(functionName, ctx);
   }

   public static Throwable trimOptionUnsupportedError(final int trimOption, final SqlBaseParser.TrimContext ctx) {
      return QueryParsingErrors$.MODULE$.trimOptionUnsupportedError(trimOption, ctx);
   }

   public static Throwable invalidEscapeStringError(final String invalidEscape, final SqlBaseParser.PredicateContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidEscapeStringError(invalidEscape, ctx);
   }

   public static Throwable invalidByteLengthLiteralError(final String bytesStr, final SqlBaseParser.SampleByBytesContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidByteLengthLiteralError(bytesStr, ctx);
   }

   public static Throwable tableSampleByBytesUnsupportedError(final String msg, final SqlBaseParser.SampleMethodContext ctx) {
      return QueryParsingErrors$.MODULE$.tableSampleByBytesUnsupportedError(msg, ctx);
   }

   public static Throwable emptyInputForTableSampleError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.emptyInputForTableSampleError(ctx);
   }

   public static Throwable incompatibleJoinTypesError(final String joinType1, final String joinType2, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.incompatibleJoinTypesError(joinType1, joinType2, ctx);
   }

   public static Throwable cannotResolveWindowReferenceError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return QueryParsingErrors$.MODULE$.cannotResolveWindowReferenceError(name, ctx);
   }

   public static Throwable invalidWindowReferenceError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidWindowReferenceError(name, ctx);
   }

   public static Throwable repetitiveWindowDefinitionError(final String name, final SqlBaseParser.WindowClauseContext ctx) {
      return QueryParsingErrors$.MODULE$.repetitiveWindowDefinitionError(name, ctx);
   }

   public static Throwable invalidLateralJoinRelationError(final SqlBaseParser.RelationPrimaryContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidLateralJoinRelationError(ctx);
   }

   public static Throwable unsupportedLateralJoinTypeError(final ParserRuleContext ctx, final String joinType) {
      return QueryParsingErrors$.MODULE$.unsupportedLateralJoinTypeError(ctx, joinType);
   }

   public static Throwable lateralJoinWithUsingJoinUnsupportedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.lateralJoinWithUsingJoinUnsupportedError(ctx);
   }

   public static Throwable lateralWithUnpivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.lateralWithUnpivotInFromClauseNotAllowedError(ctx);
   }

   public static Throwable lateralWithPivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.lateralWithPivotInFromClauseNotAllowedError(ctx);
   }

   public static Throwable unpivotWithPivotInFromClauseNotAllowedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.unpivotWithPivotInFromClauseNotAllowedError(ctx);
   }

   public static Throwable transformWithSerdeUnsupportedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.transformWithSerdeUnsupportedError(ctx);
   }

   public static Throwable transformNotSupportQuantifierError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.transformNotSupportQuantifierError(ctx);
   }

   public static Throwable distributeByUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx) {
      return QueryParsingErrors$.MODULE$.distributeByUnsupportedError(ctx);
   }

   public static Throwable windowClauseInPipeOperatorWhereClauseNotAllowedError(final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.windowClauseInPipeOperatorWhereClauseNotAllowedError(ctx);
   }

   public static Throwable pipeOperatorAggregateUnsupportedCaseError(final String caseArgument, final ParserRuleContext ctx) {
      return QueryParsingErrors$.MODULE$.pipeOperatorAggregateUnsupportedCaseError(caseArgument, ctx);
   }

   public static Throwable combinationQueryResultClausesUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx) {
      return QueryParsingErrors$.MODULE$.combinationQueryResultClausesUnsupportedError(ctx);
   }

   public static Throwable multipleQueryResultClausesWithPipeOperatorsUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx, final String clause1, final String clause2) {
      return QueryParsingErrors$.MODULE$.multipleQueryResultClausesWithPipeOperatorsUnsupportedError(ctx, clause1, clause2);
   }

   public static Throwable clausesWithPipeOperatorsUnsupportedError(final SqlBaseParser.QueryOrganizationContext ctx, final String clauses) {
      return QueryParsingErrors$.MODULE$.clausesWithPipeOperatorsUnsupportedError(ctx, clauses);
   }

   public static Throwable emptyPartitionKeyError(final String key, final SqlBaseParser.PartitionSpecContext ctx) {
      return QueryParsingErrors$.MODULE$.emptyPartitionKeyError(key, ctx);
   }

   public static Throwable nonLastNotMatchedBySourceClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return QueryParsingErrors$.MODULE$.nonLastNotMatchedBySourceClauseOmitConditionError(ctx);
   }

   public static Throwable nonLastNotMatchedClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return QueryParsingErrors$.MODULE$.nonLastNotMatchedClauseOmitConditionError(ctx);
   }

   public static Throwable nonLastMatchedClauseOmitConditionError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return QueryParsingErrors$.MODULE$.nonLastMatchedClauseOmitConditionError(ctx);
   }

   public static Throwable mergeStatementWithoutWhenClauseError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return QueryParsingErrors$.MODULE$.mergeStatementWithoutWhenClauseError(ctx);
   }

   public static Throwable insertedValueNumberNotMatchFieldNumberError(final SqlBaseParser.NotMatchedClauseContext ctx) {
      return QueryParsingErrors$.MODULE$.insertedValueNumberNotMatchFieldNumberError(ctx);
   }

   public static Throwable emptySourceForMergeError(final SqlBaseParser.MergeIntoTableContext ctx) {
      return QueryParsingErrors$.MODULE$.emptySourceForMergeError(ctx);
   }

   public static Throwable columnAliasInOperationNotAllowedError(final String op, final SqlBaseParser.TableAliasContext ctx) {
      return QueryParsingErrors$.MODULE$.columnAliasInOperationNotAllowedError(op, ctx);
   }

   public static Throwable insertOverwriteDirectoryUnsupportedError() {
      return QueryParsingErrors$.MODULE$.insertOverwriteDirectoryUnsupportedError();
   }

   public static Throwable parserStackOverflow(final ParserRuleContext parserRuleContext) {
      return QueryParsingErrors$.MODULE$.parserStackOverflow(parserRuleContext);
   }

   public static Throwable invalidInsertIntoError(final SqlBaseParser.InsertIntoContext ctx) {
      return QueryParsingErrors$.MODULE$.invalidInsertIntoError(ctx);
   }

   public static String toDSOption(final String option) {
      return QueryParsingErrors$.MODULE$.toDSOption(option);
   }

   public static QueryContext[] getQueryContext(final QueryContext context) {
      return QueryParsingErrors$.MODULE$.getQueryContext(context);
   }

   public static String getSummary(final QueryContext sqlContext) {
      return QueryParsingErrors$.MODULE$.getSummary(sqlContext);
   }

   public static String toSQLValue(final double value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final float value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final long value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final int value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final short value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final UTF8String value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLValue(final String value) {
      return QueryParsingErrors$.MODULE$.toSQLValue(value);
   }

   public static String toSQLType(final AbstractDataType t) {
      return QueryParsingErrors$.MODULE$.toSQLType(t);
   }

   public static String toSQLType(final String text) {
      return QueryParsingErrors$.MODULE$.toSQLType(text);
   }

   public static String toSQLConf(final String conf) {
      return QueryParsingErrors$.MODULE$.toSQLConf(conf);
   }

   public static String toSQLStmt(final String text) {
      return QueryParsingErrors$.MODULE$.toSQLStmt(text);
   }

   public static String toSQLId(final Seq parts) {
      return QueryParsingErrors$.MODULE$.toSQLId(parts);
   }

   public static String toSQLId(final String parts) {
      return QueryParsingErrors$.MODULE$.toSQLId(parts);
   }
}
