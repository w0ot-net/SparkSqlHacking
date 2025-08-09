package org.apache.jute.compiler.generated;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.apache.jute.compiler.JBoolean;
import org.apache.jute.compiler.JBuffer;
import org.apache.jute.compiler.JByte;
import org.apache.jute.compiler.JDouble;
import org.apache.jute.compiler.JField;
import org.apache.jute.compiler.JFile;
import org.apache.jute.compiler.JFloat;
import org.apache.jute.compiler.JInt;
import org.apache.jute.compiler.JLong;
import org.apache.jute.compiler.JMap;
import org.apache.jute.compiler.JRecord;
import org.apache.jute.compiler.JString;
import org.apache.jute.compiler.JType;
import org.apache.jute.compiler.JVector;

public class Rcc implements RccConstants {
   private static Hashtable recTab = new Hashtable();
   private static String curDir = System.getProperty("user.dir");
   private static String curFileName;
   private static String curModuleName;
   public RccTokenManager token_source;
   SimpleCharStream jj_input_stream;
   public Token token;
   public Token jj_nt;
   private int jj_ntk;
   private Token jj_scanpos;
   private Token jj_lastpos;
   private int jj_la;
   private int jj_gen;
   private final int[] jj_la1;
   private static int[] jj_la1_0;
   private static int[] jj_la1_1;
   private final JJCalls[] jj_2_rtns;
   private boolean jj_rescan;
   private int jj_gc;
   private final LookaheadSuccess jj_ls;
   private List jj_expentries;
   private int[] jj_expentry;
   private int jj_kind;
   private int[] jj_lasttokens;
   private int jj_endpos;

   public static void main(String[] args) {
      String language = "java";
      ArrayList<String> recFiles = new ArrayList();
      JFile curFile = null;

      for(int i = 0; i < args.length; ++i) {
         if (!"-l".equalsIgnoreCase(args[i]) && !"--language".equalsIgnoreCase(args[i])) {
            recFiles.add(args[i]);
         } else {
            language = args[i + 1].toLowerCase();
            ++i;
         }
      }

      if (!"c++".equals(language) && !"java".equals(language) && !"c".equals(language)) {
         System.out.println("Cannot recognize language:" + language);
         System.exit(1);
      }

      if (recFiles.size() == 0) {
         System.out.println("No record files specified. Exiting.");
         System.exit(1);
      }

      for(int i = 0; i < recFiles.size(); ++i) {
         curFileName = (String)recFiles.get(i);
         File file = new File(curFileName);

         try {
            curFile = parseFile(file);
         } catch (FileNotFoundException var8) {
            System.out.println("File " + (String)recFiles.get(i) + " Not found.");
            System.exit(1);
         } catch (ParseException e) {
            System.out.println(e.toString());
            System.exit(1);
         }

         System.out.println((String)recFiles.get(i) + " Parsed Successfully");

         try {
            curFile.genCode(language, new File("."));
         } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(1);
         }
      }

   }

   public static JFile parseFile(File file) throws FileNotFoundException, ParseException {
      curDir = file.getParent();
      curFileName = file.getName();
      FileReader reader = new FileReader(file);

      JFile var3;
      try {
         Rcc parser = new Rcc(reader);
         recTab = new Hashtable();
         var3 = parser.Input();
      } finally {
         try {
            reader.close();
         } catch (IOException var10) {
         }

      }

      return var3;
   }

   public final JFile Input() throws ParseException {
      ArrayList<JFile> ilist = new ArrayList();
      ArrayList<JRecord> rlist = new ArrayList();

      do {
         if (this.jj_2_1(2)) {
            JFile i = this.Include();
            ilist.add(i);
         } else {
            if (!this.jj_2_2(2)) {
               this.jj_consume_token(-1);
               throw new ParseException();
            }

            ArrayList<JRecord> l = this.Module();
            rlist.addAll(l);
         }
      } while(this.jj_2_3(2));

      this.jj_consume_token(0);
      return new JFile(curFileName, ilist, rlist);
   }

   public final JFile Include() throws ParseException {
      this.jj_consume_token(13);
      Token t = this.jj_consume_token(31);
      JFile ret = null;
      String fname = t.image.replaceAll("^\"", "").replaceAll("\"$", "");
      File file = new File(curDir, fname);
      String tmpDir = curDir;
      String tmpFile = curFileName;
      curDir = file.getParent();
      curFileName = file.getName();

      try {
         FileReader reader = new FileReader(file);
         Rcc parser = new Rcc(reader);

         try {
            ret = parser.Input();
            System.out.println(fname + " Parsed Successfully");
         } catch (ParseException e) {
            System.out.println(e.toString());
            System.exit(1);
         }

         try {
            reader.close();
         } catch (IOException var10) {
         }
      } catch (FileNotFoundException var12) {
         System.out.println("File " + fname + " Not found.");
         System.exit(1);
      }

      curDir = tmpDir;
      curFileName = tmpFile;
      return ret;
   }

   public final ArrayList Module() throws ParseException {
      this.jj_consume_token(11);
      String mName = this.ModuleName();
      curModuleName = mName;
      this.jj_consume_token(24);
      ArrayList<JRecord> rlist = this.RecordList();
      this.jj_consume_token(25);
      return rlist;
   }

   public final String ModuleName() throws ParseException {
      String name = "";
      Token t = this.jj_consume_token(32);

      for(name = name + t.image; this.jj_2_4(2); name = name + "." + t.image) {
         this.jj_consume_token(30);
         t = this.jj_consume_token(32);
      }

      return name;
   }

   public final ArrayList RecordList() throws ParseException {
      ArrayList<JRecord> rlist = new ArrayList();

      do {
         JRecord r = this.Record();
         rlist.add(r);
      } while(this.jj_2_5(2));

      return rlist;
   }

   public final JRecord Record() throws ParseException {
      ArrayList<JField> flist = new ArrayList();
      this.jj_consume_token(12);
      Token t = this.jj_consume_token(32);
      String rname = t.image;
      this.jj_consume_token(24);

      do {
         JField f = this.Field();
         flist.add(f);
         this.jj_consume_token(28);
      } while(this.jj_2_6(2));

      this.jj_consume_token(25);
      String fqn = curModuleName + "." + rname;
      JRecord r = new JRecord(fqn, flist);
      recTab.put(fqn, r);
      return r;
   }

   public final JField Field() throws ParseException {
      JType jt = this.Type();
      Token t = this.jj_consume_token(32);
      return new JField(jt, t.image);
   }

   public final JType Type() throws ParseException {
      if (this.jj_2_7(2)) {
         JType jt = this.Map();
         return jt;
      } else if (this.jj_2_8(2)) {
         JType jt = this.Vector();
         return jt;
      } else if (this.jj_2_9(2)) {
         this.jj_consume_token(14);
         return new JByte();
      } else if (this.jj_2_10(2)) {
         this.jj_consume_token(15);
         return new JBoolean();
      } else if (this.jj_2_11(2)) {
         this.jj_consume_token(16);
         return new JInt();
      } else if (this.jj_2_12(2)) {
         this.jj_consume_token(17);
         return new JLong();
      } else if (this.jj_2_13(2)) {
         this.jj_consume_token(18);
         return new JFloat();
      } else if (this.jj_2_14(2)) {
         this.jj_consume_token(19);
         return new JDouble();
      } else if (this.jj_2_15(2)) {
         this.jj_consume_token(20);
         return new JString();
      } else if (this.jj_2_16(2)) {
         this.jj_consume_token(21);
         return new JBuffer();
      } else if (this.jj_2_17(2)) {
         String rname = this.ModuleName();
         if (rname.indexOf(46, 0) < 0) {
            rname = curModuleName + "." + rname;
         }

         JRecord r = (JRecord)recTab.get(rname);
         if (r == null) {
            System.out.println("Type " + rname + " not known. Exiting.");
            System.exit(1);
         }

         return r;
      } else {
         this.jj_consume_token(-1);
         throw new ParseException();
      }
   }

   public final JMap Map() throws ParseException {
      this.jj_consume_token(23);
      this.jj_consume_token(26);
      JType jt1 = this.Type();
      this.jj_consume_token(29);
      JType jt2 = this.Type();
      this.jj_consume_token(27);
      return new JMap(jt1, jt2);
   }

   public final JVector Vector() throws ParseException {
      this.jj_consume_token(22);
      this.jj_consume_token(26);
      JType jt = this.Type();
      this.jj_consume_token(27);
      return new JVector(jt);
   }

   private boolean jj_2_1(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_1();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(0, xla);
      }

      return var3;
   }

   private boolean jj_2_2(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_2();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(1, xla);
      }

      return var3;
   }

   private boolean jj_2_3(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_3();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(2, xla);
      }

      return var3;
   }

   private boolean jj_2_4(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_4();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(3, xla);
      }

      return var3;
   }

   private boolean jj_2_5(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_5();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(4, xla);
      }

      return var3;
   }

   private boolean jj_2_6(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_6();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(5, xla);
      }

      return var3;
   }

   private boolean jj_2_7(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_7();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(6, xla);
      }

      return var3;
   }

   private boolean jj_2_8(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_8();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(7, xla);
      }

      return var3;
   }

   private boolean jj_2_9(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_9();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(8, xla);
      }

      return var3;
   }

   private boolean jj_2_10(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_10();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(9, xla);
      }

      return var3;
   }

   private boolean jj_2_11(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_11();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(10, xla);
      }

      return var3;
   }

   private boolean jj_2_12(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_12();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(11, xla);
      }

      return var3;
   }

   private boolean jj_2_13(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_13();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(12, xla);
      }

      return var3;
   }

   private boolean jj_2_14(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_14();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(13, xla);
      }

      return var3;
   }

   private boolean jj_2_15(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_15();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(14, xla);
      }

      return var3;
   }

   private boolean jj_2_16(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_16();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(15, xla);
      }

      return var3;
   }

   private boolean jj_2_17(int xla) {
      this.jj_la = xla;
      this.jj_lastpos = this.jj_scanpos = this.token;

      boolean var3;
      try {
         boolean var2 = !this.jj_3_17();
         return var2;
      } catch (LookaheadSuccess var7) {
         var3 = true;
      } finally {
         this.jj_save(16, xla);
      }

      return var3;
   }

   private boolean jj_3_11() {
      return this.jj_scan_token(16);
   }

   private boolean jj_3R_10() {
      if (this.jj_scan_token(22)) {
         return true;
      } else {
         return this.jj_scan_token(26);
      }
   }

   private boolean jj_3_3() {
      Token xsp = this.jj_scanpos;
      if (this.jj_3_1()) {
         this.jj_scanpos = xsp;
         if (this.jj_3_2()) {
            return true;
         }
      }

      return false;
   }

   private boolean jj_3_1() {
      return this.jj_3R_5();
   }

   private boolean jj_3_10() {
      return this.jj_scan_token(15);
   }

   private boolean jj_3_9() {
      return this.jj_scan_token(14);
   }

   private boolean jj_3_8() {
      return this.jj_3R_10();
   }

   private boolean jj_3_5() {
      return this.jj_3R_7();
   }

   private boolean jj_3R_12() {
      Token xsp = this.jj_scanpos;
      if (this.jj_3_7()) {
         this.jj_scanpos = xsp;
         if (this.jj_3_8()) {
            this.jj_scanpos = xsp;
            if (this.jj_3_9()) {
               this.jj_scanpos = xsp;
               if (this.jj_3_10()) {
                  this.jj_scanpos = xsp;
                  if (this.jj_3_11()) {
                     this.jj_scanpos = xsp;
                     if (this.jj_3_12()) {
                        this.jj_scanpos = xsp;
                        if (this.jj_3_13()) {
                           this.jj_scanpos = xsp;
                           if (this.jj_3_14()) {
                              this.jj_scanpos = xsp;
                              if (this.jj_3_15()) {
                                 this.jj_scanpos = xsp;
                                 if (this.jj_3_16()) {
                                    this.jj_scanpos = xsp;
                                    if (this.jj_3_17()) {
                                       return true;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private boolean jj_3_7() {
      return this.jj_3R_9();
   }

   private boolean jj_3R_9() {
      if (this.jj_scan_token(23)) {
         return true;
      } else {
         return this.jj_scan_token(26);
      }
   }

   private boolean jj_3R_8() {
      if (this.jj_3R_12()) {
         return true;
      } else {
         return this.jj_scan_token(32);
      }
   }

   private boolean jj_3_4() {
      if (this.jj_scan_token(30)) {
         return true;
      } else {
         return this.jj_scan_token(32);
      }
   }

   private boolean jj_3R_11() {
      if (this.jj_scan_token(32)) {
         return true;
      } else {
         Token xsp;
         do {
            xsp = this.jj_scanpos;
         } while(!this.jj_3_4());

         this.jj_scanpos = xsp;
         return false;
      }
   }

   private boolean jj_3_6() {
      return this.jj_3R_8();
   }

   private boolean jj_3_17() {
      return this.jj_3R_11();
   }

   private boolean jj_3R_5() {
      if (this.jj_scan_token(13)) {
         return true;
      } else {
         return this.jj_scan_token(31);
      }
   }

   private boolean jj_3_16() {
      return this.jj_scan_token(21);
   }

   private boolean jj_3_15() {
      return this.jj_scan_token(20);
   }

   private boolean jj_3_14() {
      return this.jj_scan_token(19);
   }

   private boolean jj_3R_6() {
      if (this.jj_scan_token(11)) {
         return true;
      } else {
         return this.jj_3R_11();
      }
   }

   private boolean jj_3_13() {
      return this.jj_scan_token(18);
   }

   private boolean jj_3R_7() {
      if (this.jj_scan_token(12)) {
         return true;
      } else {
         return this.jj_scan_token(32);
      }
   }

   private boolean jj_3_12() {
      return this.jj_scan_token(17);
   }

   private boolean jj_3_2() {
      return this.jj_3R_6();
   }

   private static void jj_la1_init_0() {
      jj_la1_0 = new int[0];
   }

   private static void jj_la1_init_1() {
      jj_la1_1 = new int[0];
   }

   public Rcc(InputStream stream) {
      this(stream, (String)null);
   }

   public Rcc(InputStream stream, String encoding) {
      this.jj_la1 = new int[0];
      this.jj_2_rtns = new JJCalls[17];
      this.jj_rescan = false;
      this.jj_gc = 0;
      this.jj_ls = new LookaheadSuccess();
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;
      this.jj_lasttokens = new int[100];

      try {
         this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }

      this.token_source = new RccTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   public void ReInit(InputStream stream) {
      this.ReInit(stream, (String)null);
   }

   public void ReInit(InputStream stream, String encoding) {
      try {
         this.jj_input_stream.ReInit(stream, encoding, 1, 1);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }

      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   public Rcc(Reader stream) {
      this.jj_la1 = new int[0];
      this.jj_2_rtns = new JJCalls[17];
      this.jj_rescan = false;
      this.jj_gc = 0;
      this.jj_ls = new LookaheadSuccess();
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;
      this.jj_lasttokens = new int[100];
      this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
      this.token_source = new RccTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   public void ReInit(Reader stream) {
      this.jj_input_stream.ReInit((Reader)stream, 1, 1);
      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   public Rcc(RccTokenManager tm) {
      this.jj_la1 = new int[0];
      this.jj_2_rtns = new JJCalls[17];
      this.jj_rescan = false;
      this.jj_gc = 0;
      this.jj_ls = new LookaheadSuccess();
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;
      this.jj_lasttokens = new int[100];
      this.token_source = tm;
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   public void ReInit(RccTokenManager tm) {
      this.token_source = tm;
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 0; ++i) {
         this.jj_la1[i] = -1;
      }

      for(int i = 0; i < this.jj_2_rtns.length; ++i) {
         this.jj_2_rtns[i] = new JJCalls();
      }

   }

   private Token jj_consume_token(int kind) throws ParseException {
      Token oldToken;
      if ((oldToken = this.token).next != null) {
         this.token = this.token.next;
      } else {
         this.token = this.token.next = this.token_source.getNextToken();
      }

      this.jj_ntk = -1;
      if (this.token.kind != kind) {
         this.token = oldToken;
         this.jj_kind = kind;
         throw this.generateParseException();
      } else {
         ++this.jj_gen;
         if (++this.jj_gc > 100) {
            this.jj_gc = 0;

            for(int i = 0; i < this.jj_2_rtns.length; ++i) {
               for(JJCalls c = this.jj_2_rtns[i]; c != null; c = c.next) {
                  if (c.gen < this.jj_gen) {
                     c.first = null;
                  }
               }
            }
         }

         return this.token;
      }
   }

   private boolean jj_scan_token(int kind) {
      if (this.jj_scanpos == this.jj_lastpos) {
         --this.jj_la;
         if (this.jj_scanpos.next == null) {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next = this.token_source.getNextToken();
         } else {
            this.jj_lastpos = this.jj_scanpos = this.jj_scanpos.next;
         }
      } else {
         this.jj_scanpos = this.jj_scanpos.next;
      }

      if (this.jj_rescan) {
         int i = 0;

         Token tok;
         for(tok = this.token; tok != null && tok != this.jj_scanpos; tok = tok.next) {
            ++i;
         }

         if (tok != null) {
            this.jj_add_error_token(kind, i);
         }
      }

      if (this.jj_scanpos.kind != kind) {
         return true;
      } else if (this.jj_la == 0 && this.jj_scanpos == this.jj_lastpos) {
         throw this.jj_ls;
      } else {
         return false;
      }
   }

   public final Token getNextToken() {
      if (this.token.next != null) {
         this.token = this.token.next;
      } else {
         this.token = this.token.next = this.token_source.getNextToken();
      }

      this.jj_ntk = -1;
      ++this.jj_gen;
      return this.token;
   }

   public final Token getToken(int index) {
      Token t = this.token;

      for(int i = 0; i < index; ++i) {
         if (t.next != null) {
            t = t.next;
         } else {
            t = t.next = this.token_source.getNextToken();
         }
      }

      return t;
   }

   private int jj_ntk() {
      return (this.jj_nt = this.token.next) == null ? (this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind) : (this.jj_ntk = this.jj_nt.kind);
   }

   private void jj_add_error_token(int kind, int pos) {
      if (pos < 100) {
         if (pos == this.jj_endpos + 1) {
            this.jj_lasttokens[this.jj_endpos++] = kind;
         } else if (this.jj_endpos != 0) {
            this.jj_expentry = new int[this.jj_endpos];

            for(int i = 0; i < this.jj_endpos; ++i) {
               this.jj_expentry[i] = this.jj_lasttokens[i];
            }

            label41:
            for(int[] oldentry : this.jj_expentries) {
               if (oldentry.length == this.jj_expentry.length) {
                  for(int i = 0; i < this.jj_expentry.length; ++i) {
                     if (oldentry[i] != this.jj_expentry[i]) {
                        continue label41;
                     }
                  }

                  this.jj_expentries.add(this.jj_expentry);
                  break;
               }
            }

            if (pos != 0) {
               this.jj_lasttokens[(this.jj_endpos = pos) - 1] = kind;
            }
         }

      }
   }

   public ParseException generateParseException() {
      this.jj_expentries.clear();
      boolean[] la1tokens = new boolean[33];
      if (this.jj_kind >= 0) {
         la1tokens[this.jj_kind] = true;
         this.jj_kind = -1;
      }

      for(int i = 0; i < 0; ++i) {
         if (this.jj_la1[i] == this.jj_gen) {
            for(int j = 0; j < 32; ++j) {
               if ((jj_la1_0[i] & 1 << j) != 0) {
                  la1tokens[j] = true;
               }

               if ((jj_la1_1[i] & 1 << j) != 0) {
                  la1tokens[32 + j] = true;
               }
            }
         }
      }

      for(int i = 0; i < 33; ++i) {
         if (la1tokens[i]) {
            this.jj_expentry = new int[1];
            this.jj_expentry[0] = i;
            this.jj_expentries.add(this.jj_expentry);
         }
      }

      this.jj_endpos = 0;
      this.jj_rescan_token();
      this.jj_add_error_token(0, 0);
      int[][] exptokseq = new int[this.jj_expentries.size()][];

      for(int i = 0; i < this.jj_expentries.size(); ++i) {
         exptokseq[i] = (int[])this.jj_expentries.get(i);
      }

      return new ParseException(this.token, exptokseq, tokenImage);
   }

   public final void enable_tracing() {
   }

   public final void disable_tracing() {
   }

   private void jj_rescan_token() {
      this.jj_rescan = true;

      for(int i = 0; i < 17; ++i) {
         try {
            JJCalls p = this.jj_2_rtns[i];

            while(true) {
               if (p.gen > this.jj_gen) {
                  this.jj_la = p.arg;
                  this.jj_lastpos = this.jj_scanpos = p.first;
                  switch (i) {
                     case 0:
                        this.jj_3_1();
                        break;
                     case 1:
                        this.jj_3_2();
                        break;
                     case 2:
                        this.jj_3_3();
                        break;
                     case 3:
                        this.jj_3_4();
                        break;
                     case 4:
                        this.jj_3_5();
                        break;
                     case 5:
                        this.jj_3_6();
                        break;
                     case 6:
                        this.jj_3_7();
                        break;
                     case 7:
                        this.jj_3_8();
                        break;
                     case 8:
                        this.jj_3_9();
                        break;
                     case 9:
                        this.jj_3_10();
                        break;
                     case 10:
                        this.jj_3_11();
                        break;
                     case 11:
                        this.jj_3_12();
                        break;
                     case 12:
                        this.jj_3_13();
                        break;
                     case 13:
                        this.jj_3_14();
                        break;
                     case 14:
                        this.jj_3_15();
                        break;
                     case 15:
                        this.jj_3_16();
                        break;
                     case 16:
                        this.jj_3_17();
                  }
               }

               p = p.next;
               if (p == null) {
                  break;
               }
            }
         } catch (LookaheadSuccess var3) {
         }
      }

      this.jj_rescan = false;
   }

   private void jj_save(int index, int xla) {
      JJCalls p;
      for(p = this.jj_2_rtns[index]; p.gen > this.jj_gen; p = p.next) {
         if (p.next == null) {
            p = p.next = new JJCalls();
            break;
         }
      }

      p.gen = this.jj_gen + xla - this.jj_la;
      p.first = this.token;
      p.arg = xla;
   }

   static {
      jj_la1_init_0();
      jj_la1_init_1();
   }

   private static final class LookaheadSuccess extends Error {
      private LookaheadSuccess() {
      }
   }

   static final class JJCalls {
      int gen;
      Token first;
      int arg;
      JJCalls next;
   }
}
