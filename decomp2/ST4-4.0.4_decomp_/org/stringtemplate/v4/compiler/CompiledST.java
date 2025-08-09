package org.stringtemplate.v4.compiler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;

public class CompiledST {
   public String name;
   public String prefix = "/";
   public String template;
   public Token templateDefStartToken;
   public TokenStream tokens;
   public CommonTree ast;
   public Map formalArguments;
   public boolean hasFormalArgs;
   public int numberOfArgsWithDefaultValues;
   public List implicitlyDefinedTemplates;
   public STGroup nativeGroup;
   public boolean isRegion;
   public ST.RegionType regionDefType;
   public boolean isAnonSubtemplate;
   public String[] strings;
   public byte[] instrs;
   public int codeSize;
   public Interval[] sourceMap;

   public CompiledST() {
      this.nativeGroup = STGroup.defaultGroup;
      this.instrs = new byte[15];
      this.sourceMap = new Interval[15];
      this.template = "";
   }

   public void addImplicitlyDefinedTemplate(CompiledST sub) {
      sub.prefix = this.prefix;
      if (sub.name.charAt(0) != '/') {
         sub.name = sub.prefix + sub.name;
      }

      if (this.implicitlyDefinedTemplates == null) {
         this.implicitlyDefinedTemplates = new ArrayList();
      }

      this.implicitlyDefinedTemplates.add(sub);
   }

   public void defineArgDefaultValueTemplates(STGroup group) {
      if (this.formalArguments != null) {
         for(String a : this.formalArguments.keySet()) {
            FormalArgument fa = (FormalArgument)this.formalArguments.get(a);
            if (fa.defaultValueToken != null) {
               ++this.numberOfArgsWithDefaultValues;
               if (fa.defaultValueToken.getType() == 10) {
                  String argSTname = fa.name + "_default_value";
                  Compiler c2 = new Compiler(group);
                  String defArgTemplate = Misc.strip(fa.defaultValueToken.getText(), 1);
                  fa.compiledDefaultValue = c2.compile(group.getFileName(), argSTname, (List)null, defArgTemplate, fa.defaultValueToken);
                  fa.compiledDefaultValue.name = argSTname;
                  fa.compiledDefaultValue.defineImplicitlyDefinedTemplates(group);
               } else if (fa.defaultValueToken.getType() == 6) {
                  fa.defaultValue = Misc.strip(fa.defaultValueToken.getText(), 1);
               } else {
                  fa.defaultValue = fa.defaultValueToken.getType() == 4;
               }
            }
         }

      }
   }

   public void defineFormalArgs(List args) {
      this.hasFormalArgs = true;
      if (args == null) {
         this.formalArguments = null;
      } else {
         for(FormalArgument a : args) {
            this.addArg(a);
         }
      }

   }

   public void addArg(FormalArgument a) {
      if (this.formalArguments == null) {
         this.formalArguments = Collections.synchronizedMap(new LinkedHashMap());
      }

      a.index = this.formalArguments.size();
      this.formalArguments.put(a.name, a);
   }

   public void defineImplicitlyDefinedTemplates(STGroup group) {
      if (this.implicitlyDefinedTemplates != null) {
         for(CompiledST sub : this.implicitlyDefinedTemplates) {
            group.rawDefineTemplate(sub.name, sub, sub.templateDefStartToken);
            sub.defineImplicitlyDefinedTemplates(group);
         }
      }

   }

   public String getTemplateSource() {
      Interval r = this.getTemplateRange();
      return this.template.substring(r.a, r.b + 1);
   }

   public Interval getTemplateRange() {
      if (!this.isAnonSubtemplate) {
         return new Interval(0, this.template.length() - 1);
      } else {
         Interval start = this.sourceMap[0];
         Interval stop = null;

         for(int i = this.sourceMap.length - 1; i >= 0; --i) {
            Interval I = this.sourceMap[i];
            if (I != null) {
               stop = I;
               break;
            }
         }

         return new Interval(start.a, stop.b);
      }
   }

   public String instrs() {
      BytecodeDisassembler dis = new BytecodeDisassembler(this);
      return dis.instrs();
   }

   public void dump() {
      BytecodeDisassembler dis = new BytecodeDisassembler(this);
      System.out.println(this.name + ":");
      System.out.println(dis.disassemble());
      System.out.println("Strings:");
      System.out.println(dis.strings());
      System.out.println("Bytecode to template map:");
      System.out.println(dis.sourceMap());
   }

   public String disasm() {
      BytecodeDisassembler dis = new BytecodeDisassembler(this);
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      pw.println(dis.disassemble());
      pw.println("Strings:");
      pw.println(dis.strings());
      pw.println("Bytecode to template map:");
      pw.println(dis.sourceMap());
      pw.close();
      return sw.toString();
   }
}
