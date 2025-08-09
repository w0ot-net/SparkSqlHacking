package org.stringtemplate.v4;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.stringtemplate.v4.compiler.BytecodeDisassembler;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.Compiler;
import org.stringtemplate.v4.compiler.FormalArgument;
import org.stringtemplate.v4.debug.EvalExprEvent;
import org.stringtemplate.v4.debug.EvalTemplateEvent;
import org.stringtemplate.v4.debug.IndentEvent;
import org.stringtemplate.v4.debug.InterpEvent;
import org.stringtemplate.v4.misc.ArrayIterator;
import org.stringtemplate.v4.misc.ErrorManager;
import org.stringtemplate.v4.misc.ErrorType;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;
import org.stringtemplate.v4.misc.STNoSuchAttributeException;
import org.stringtemplate.v4.misc.STNoSuchPropertyException;

public class Interpreter {
   public static final int DEFAULT_OPERAND_STACK_SIZE = 100;
   public static final Set predefinedAnonSubtemplateAttributes = new HashSet() {
      {
         this.add("i");
         this.add("i0");
      }
   };
   Object[] operands;
   int sp;
   int current_ip;
   int nwline;
   public InstanceScope currentScope;
   STGroup group;
   Locale locale;
   ErrorManager errMgr;
   public static boolean trace = false;
   protected List executeTrace;
   public boolean debug;
   protected List events;

   public Interpreter(STGroup group, boolean debug) {
      this(group, Locale.getDefault(), group.errMgr, debug);
   }

   public Interpreter(STGroup group, Locale locale, boolean debug) {
      this(group, locale, group.errMgr, debug);
   }

   public Interpreter(STGroup group, ErrorManager errMgr, boolean debug) {
      this(group, Locale.getDefault(), errMgr, debug);
   }

   public Interpreter(STGroup group, Locale locale, ErrorManager errMgr, boolean debug) {
      this.operands = new Object[100];
      this.sp = -1;
      this.current_ip = 0;
      this.nwline = 0;
      this.currentScope = null;
      this.debug = false;
      this.group = group;
      this.locale = locale;
      this.errMgr = errMgr;
      this.debug = debug;
      if (debug) {
         this.events = new ArrayList();
         this.executeTrace = new ArrayList();
      }

   }

   public int exec(STWriter out, ST self) {
      this.pushScope(self);

      byte var6;
      try {
         this.setDefaultArguments(out, self);
         int var3 = this._exec(out, self);
         return var3;
      } catch (Exception e) {
         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);
         e.printStackTrace(pw);
         pw.flush();
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.INTERNAL_ERROR, "internal error: " + sw.toString());
         var6 = 0;
      } finally {
         this.popScope();
      }

      return var6;
   }

   protected int _exec(STWriter out, ST self) {
      int start = out.index();
      int prevOpcode = 0;
      int n = 0;
      byte[] code = self.impl.instrs;

      short opcode;
      for(int ip = 0; ip < self.impl.codeSize; prevOpcode = opcode) {
         if (trace || this.debug) {
            this.trace(self, ip);
         }

         opcode = (short)code[ip];
         this.current_ip = ip++;
         switch (opcode) {
            case 1:
               this.load_str(self, ip);
               ip += 2;
               break;
            case 2:
               int nameIndex = getShort(code, ip);
               ip += 2;
               String name = self.impl.strings[nameIndex];

               Object o;
               try {
                  o = this.getAttribute(self, name);
                  if (o == ST.EMPTY_ATTR) {
                     o = null;
                  }
               } catch (STNoSuchAttributeException var30) {
                  this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_SUCH_ATTRIBUTE, name);
                  o = null;
               }

               this.operands[++this.sp] = o;
               break;
            case 3:
               int valueIndex = getShort(code, ip);
               ip += 2;
               Object var61 = self.locals[valueIndex];
               if (var61 == ST.EMPTY_ATTR) {
                  var61 = null;
               }

               this.operands[++this.sp] = var61;
               break;
            case 4:
               int var39 = getShort(code, ip);
               ip += 2;
               Object var60 = this.operands[this.sp--];
               String var47 = self.impl.strings[var39];
               this.operands[++this.sp] = this.getObjectProperty(out, self, var60, var47);
               break;
            case 5:
               Object propName = this.operands[this.sp--];
               Object var59 = this.operands[this.sp];
               this.operands[this.sp] = this.getObjectProperty(out, self, var59, propName);
               break;
            case 6:
               int optionIndex = getShort(code, ip);
               ip += 2;
               Object var58 = this.operands[this.sp--];
               Object[] options = this.operands[this.sp];
               options[optionIndex] = var58;
               break;
            case 7:
               int var38 = getShort(code, ip);
               String var46 = self.impl.strings[var38];
               ip += 2;
               Object var57 = this.operands[this.sp--];
               Map var76 = (Map)this.operands[this.sp];
               var76.put(var46, var57);
               break;
            case 8:
               int var37 = getShort(code, ip);
               int var71 = ip + 2;
               String var45 = self.impl.strings[var37];
               int nargs = getShort(code, var71);
               ip = var71 + 2;
               ST st = self.groupThatCreatedThisInstance.getEmbeddedInstanceOf(this, self, ip, var45);
               this.storeArgs(self, nargs, st);
               this.sp -= nargs;
               this.operands[++this.sp] = st;
               break;
            case 9:
               int var32 = getShort(code, ip);
               ip += 2;
               String var44 = (String)this.operands[this.sp - var32];
               ST var67 = self.groupThatCreatedThisInstance.getEmbeddedInstanceOf(this, self, ip, var44);
               this.storeArgs(self, var32, var67);
               this.sp -= var32;
               --this.sp;
               this.operands[++this.sp] = var67;
               break;
            case 10:
               int var36 = getShort(code, ip);
               ip += 2;
               String var43 = self.impl.strings[var36];
               Map<String, Object> attrs = (Map)this.operands[this.sp--];
               ST var66 = self.groupThatCreatedThisInstance.getEmbeddedInstanceOf(this, self, ip, var43);
               this.storeArgs(self, attrs, var66);
               this.operands[++this.sp] = var66;
               break;
            case 11:
               int var35 = getShort(code, ip);
               ip += 2;
               String var42 = self.impl.strings[var35];
               int nargs = getShort(code, ip);
               ip += 2;
               this.super_new(self, var42, nargs);
               break;
            case 12:
               int var34 = getShort(code, ip);
               ip += 2;
               String var41 = self.impl.strings[var34];
               Map var74 = (Map)this.operands[this.sp--];
               this.super_new(self, var41, var74);
               break;
            case 13:
               Object var56 = this.operands[this.sp--];
               int n1 = this.writeObjectNoOptions(out, self, var56);
               n += n1;
               this.nwline += n1;
               break;
            case 14:
               Object[] options = this.operands[this.sp--];
               Object var55 = this.operands[this.sp--];
               int n2 = this.writeObjectWithOptions(out, self, var55, options);
               n += n2;
               this.nwline += n2;
               break;
            case 15:
               ST var65 = (ST)this.operands[this.sp--];
               Object var54 = this.operands[this.sp--];
               this.map(self, var54, var65);
               break;
            case 16:
               int nmaps = getShort(code, ip);
               ip += 2;
               List<ST> templates = new ArrayList();

               for(int i = nmaps - 1; i >= 0; --i) {
                  templates.add((ST)this.operands[this.sp - i]);
               }

               this.sp -= nmaps;
               Object var53 = this.operands[this.sp--];
               if (var53 != null) {
                  this.rot_map(self, var53, templates);
               }
               break;
            case 17:
               ST st = (ST)this.operands[this.sp--];
               int nmaps = getShort(code, ip);
               ip += 2;
               List<Object> exprs = new ArrayList();

               for(int i = nmaps - 1; i >= 0; --i) {
                  exprs.add(this.operands[this.sp - i]);
               }

               this.sp -= nmaps;
               this.operands[++this.sp] = this.zip_map(self, exprs, st);
               break;
            case 18:
               ip = getShort(code, ip);
               break;
            case 19:
               int addr = getShort(code, ip);
               ip += 2;
               Object var52 = this.operands[this.sp--];
               if (!this.testAttributeTrue(var52)) {
                  ip = addr;
               }
               break;
            case 20:
               this.operands[++this.sp] = new Object[Compiler.NUM_OPTIONS];
               break;
            case 21:
               this.operands[++this.sp] = new HashMap();
               break;
            case 22:
               int nameIndex = getShort(code, ip);
               ip += 2;
               String name = self.impl.strings[nameIndex];
               Map attrs = (Map)this.operands[this.sp];
               this.passthru(self, name, attrs);
               break;
            case 23:
            default:
               this.errMgr.internalError(self, "invalid bytecode @ " + (ip - 1) + ": " + opcode, (Throwable)null);
               self.impl.dump();
               break;
            case 24:
               this.operands[++this.sp] = new ArrayList();
               break;
            case 25:
               Object var51 = this.operands[this.sp--];
               List<Object> list = (List)this.operands[this.sp];
               this.addToList(list, var51);
               break;
            case 26:
               this.operands[this.sp] = this.toString(out, self, this.operands[this.sp]);
               break;
            case 27:
               this.operands[this.sp] = this.first(this.operands[this.sp]);
               break;
            case 28:
               this.operands[this.sp] = this.last(this.operands[this.sp]);
               break;
            case 29:
               this.operands[this.sp] = this.rest(this.operands[this.sp]);
               break;
            case 30:
               this.operands[this.sp] = this.trunc(this.operands[this.sp]);
               break;
            case 31:
               this.operands[this.sp] = this.strip(this.operands[this.sp]);
               break;
            case 32:
               Object var50 = this.operands[this.sp--];
               if (var50.getClass() == String.class) {
                  this.operands[++this.sp] = ((String)var50).trim();
               } else {
                  this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.EXPECTING_STRING, (Object)"trim", var50.getClass().getName());
                  this.operands[++this.sp] = var50;
               }
               break;
            case 33:
               this.operands[this.sp] = this.length(this.operands[this.sp]);
               break;
            case 34:
               Object var49 = this.operands[this.sp--];
               if (var49.getClass() == String.class) {
                  this.operands[++this.sp] = ((String)var49).length();
               } else {
                  this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.EXPECTING_STRING, (Object)"strlen", var49.getClass().getName());
                  this.operands[++this.sp] = 0;
               }
               break;
            case 35:
               this.operands[this.sp] = this.reverse(this.operands[this.sp]);
               break;
            case 36:
               this.operands[this.sp] = !this.testAttributeTrue(this.operands[this.sp]);
               break;
            case 37:
               Object right = this.operands[this.sp--];
               Object left = this.operands[this.sp--];
               this.operands[++this.sp] = this.testAttributeTrue(left) || this.testAttributeTrue(right);
               break;
            case 38:
               Object right = this.operands[this.sp--];
               Object left = this.operands[this.sp--];
               this.operands[++this.sp] = this.testAttributeTrue(left) && this.testAttributeTrue(right);
               break;
            case 39:
               int strIndex = getShort(code, ip);
               ip += 2;
               this.indent(out, self, strIndex);
               break;
            case 40:
               out.popIndentation();
               break;
            case 41:
               try {
                  if (prevOpcode == 41 || prevOpcode == 39 || this.nwline > 0) {
                     out.write(Misc.newline);
                  }

                  this.nwline = 0;
               } catch (IOException ioe) {
                  this.errMgr.IOError(self, ErrorType.WRITE_IO_ERROR, ioe);
               }
            case 42:
               break;
            case 43:
               --this.sp;
               break;
            case 44:
               this.operands[++this.sp] = null;
               break;
            case 45:
               this.operands[++this.sp] = true;
               break;
            case 46:
               this.operands[++this.sp] = false;
               break;
            case 47:
               int strIndex = getShort(code, ip);
               ip += 2;
               Object o = self.impl.strings[strIndex];
               int n1 = this.writeObjectNoOptions(out, self, o);
               n += n1;
               this.nwline += n1;
         }
      }

      if (this.debug) {
         opcode = (short)(out.index() - 1);
         EvalTemplateEvent e = new EvalTemplateEvent(this.currentScope, start, opcode);
         this.trackDebugEvent(self, e);
      }

      return n;
   }

   void load_str(ST self, int ip) {
      int strIndex = getShort(self.impl.instrs, ip);
      ip += 2;
      this.operands[++this.sp] = self.impl.strings[strIndex];
   }

   void super_new(ST self, String name, int nargs) {
      ST st = null;
      CompiledST imported = self.impl.nativeGroup.lookupImportedTemplate(name);
      if (imported == null) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_IMPORTED_TEMPLATE, name);
         st = self.groupThatCreatedThisInstance.createStringTemplateInternally(new CompiledST());
      } else {
         st = imported.nativeGroup.getEmbeddedInstanceOf(this, self, this.current_ip, name);
         st.groupThatCreatedThisInstance = this.group;
      }

      this.storeArgs(self, nargs, st);
      this.sp -= nargs;
      this.operands[++this.sp] = st;
   }

   void super_new(ST self, String name, Map attrs) {
      ST st = null;
      CompiledST imported = self.impl.nativeGroup.lookupImportedTemplate(name);
      if (imported == null) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_IMPORTED_TEMPLATE, name);
         st = self.groupThatCreatedThisInstance.createStringTemplateInternally(new CompiledST());
      } else {
         st = imported.nativeGroup.createStringTemplateInternally(imported);
         st.groupThatCreatedThisInstance = this.group;
      }

      this.storeArgs(self, attrs, st);
      this.operands[++this.sp] = st;
   }

   void passthru(ST self, String templateName, Map attrs) {
      CompiledST c = this.group.lookupTemplate(templateName);
      if (c != null) {
         if (c.formalArguments != null) {
            for(FormalArgument arg : c.formalArguments.values()) {
               if (!attrs.containsKey(arg.name)) {
                  try {
                     Object o = this.getAttribute(self, arg.name);
                     if (o == ST.EMPTY_ATTR && arg.defaultValueToken == null) {
                        attrs.put(arg.name, (Object)null);
                     } else if (o != ST.EMPTY_ATTR) {
                        attrs.put(arg.name, o);
                     }
                  } catch (STNoSuchAttributeException var8) {
                     if (arg.defaultValueToken == null) {
                        attrs.put(arg.name, (Object)null);
                     }
                  }
               }
            }

         }
      }
   }

   void storeArgs(ST self, Map attrs, ST st) {
      int nformalArgs = 0;
      if (st.impl.formalArguments != null) {
         nformalArgs = st.impl.formalArguments.size();
      }

      int nargs = 0;
      if (attrs != null) {
         nargs = attrs.size();
      }

      if (nargs < nformalArgs - st.impl.numberOfArgsWithDefaultValues || nargs > nformalArgs) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.ARGUMENT_COUNT_MISMATCH, nargs, st.impl.name, nformalArgs);
      }

      for(String argName : attrs.keySet()) {
         if (st.impl.formalArguments != null && st.impl.formalArguments.containsKey(argName)) {
            Object o = attrs.get(argName);
            st.rawSetAttribute(argName, o);
         } else {
            this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_SUCH_ATTRIBUTE, argName);
         }
      }

   }

   void storeArgs(ST self, int nargs, ST st) {
      int nformalArgs = 0;
      if (st.impl.formalArguments != null) {
         nformalArgs = st.impl.formalArguments.size();
      }

      int firstArg = this.sp - (nargs - 1);
      int numToStore = Math.min(nargs, nformalArgs);
      if (st.impl.isAnonSubtemplate) {
         nformalArgs -= predefinedAnonSubtemplateAttributes.size();
      }

      if (nargs < nformalArgs - st.impl.numberOfArgsWithDefaultValues || nargs > nformalArgs) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.ARGUMENT_COUNT_MISMATCH, nargs, st.impl.name, nformalArgs);
      }

      if (st.impl.formalArguments != null) {
         Iterator<String> argNames = st.impl.formalArguments.keySet().iterator();

         for(int i = 0; i < numToStore; ++i) {
            Object o = this.operands[firstArg + i];
            String argName = (String)argNames.next();
            st.rawSetAttribute(argName, o);
         }

      }
   }

   protected void indent(STWriter out, ST self, int strIndex) {
      String indent = self.impl.strings[strIndex];
      if (this.debug) {
         int start = out.index();
         EvalExprEvent e = new IndentEvent(this.currentScope, start, start + indent.length() - 1, this.getExprStartChar(self), this.getExprStopChar(self));
         this.trackDebugEvent(self, e);
      }

      out.pushIndentation(indent);
   }

   protected int writeObjectNoOptions(STWriter out, ST self, Object o) {
      int start = out.index();
      int n = this.writeObject(out, self, o, (String[])null);
      if (this.debug) {
         EvalExprEvent e = new EvalExprEvent(this.currentScope, start, out.index() - 1, this.getExprStartChar(self), this.getExprStopChar(self));
         this.trackDebugEvent(self, e);
      }

      return n;
   }

   protected int writeObjectWithOptions(STWriter out, ST self, Object o, Object[] options) {
      int start = out.index();
      String[] optionStrings = null;
      if (options != null) {
         optionStrings = new String[options.length];

         for(int i = 0; i < Compiler.NUM_OPTIONS; ++i) {
            optionStrings[i] = this.toString(out, self, options[i]);
         }
      }

      if (options != null && options[Interpreter.Option.ANCHOR.ordinal()] != null) {
         out.pushAnchorPoint();
      }

      int n = this.writeObject(out, self, o, optionStrings);
      if (options != null && options[Interpreter.Option.ANCHOR.ordinal()] != null) {
         out.popAnchorPoint();
      }

      if (this.debug) {
         EvalExprEvent e = new EvalExprEvent(this.currentScope, start, out.index() - 1, this.getExprStartChar(self), this.getExprStopChar(self));
         this.trackDebugEvent(self, e);
      }

      return n;
   }

   protected int writeObject(STWriter out, ST self, Object o, String[] options) {
      int n = 0;
      if (o == null) {
         if (options == null || options[Interpreter.Option.NULL.ordinal()] == null) {
            return 0;
         }

         o = options[Interpreter.Option.NULL.ordinal()];
      }

      if (o instanceof ST) {
         ST st = (ST)o;
         if (options != null && options[Interpreter.Option.WRAP.ordinal()] != null) {
            try {
               out.writeWrap(options[Interpreter.Option.WRAP.ordinal()]);
            } catch (IOException ioe) {
               this.errMgr.IOError(self, ErrorType.WRITE_IO_ERROR, ioe);
            }
         }

         n = this.exec(out, st);
      } else {
         o = this.convertAnythingIteratableToIterator(o);

         try {
            if (o instanceof Iterator) {
               n = this.writeIterator(out, self, o, options);
            } else {
               n = this.writePOJO(out, o, options);
            }
         } catch (IOException ioe) {
            this.errMgr.IOError(self, ErrorType.WRITE_IO_ERROR, ioe, o);
         }
      }

      return n;
   }

   protected int writeIterator(STWriter out, ST self, Object o, String[] options) throws IOException {
      if (o == null) {
         return 0;
      } else {
         int n = 0;
         Iterator it = (Iterator)o;
         String separator = null;
         if (options != null) {
            separator = options[Interpreter.Option.SEPARATOR.ordinal()];
         }

         int nw;
         for(boolean seenAValue = false; it.hasNext(); n += nw) {
            Object iterValue = it.next();
            boolean needSeparator = seenAValue && separator != null && (iterValue != null || options[Interpreter.Option.NULL.ordinal()] != null);
            if (needSeparator) {
               n += out.writeSeparator(separator);
            }

            nw = this.writeObject(out, self, iterValue, options);
            if (nw > 0) {
               seenAValue = true;
            }
         }

         return n;
      }
   }

   protected int writePOJO(STWriter out, Object o, String[] options) throws IOException {
      String formatString = null;
      if (options != null) {
         formatString = options[Interpreter.Option.FORMAT.ordinal()];
      }

      AttributeRenderer r = this.currentScope.st.impl.nativeGroup.getAttributeRenderer(o.getClass());
      String v;
      if (r != null) {
         v = r.toString(o, formatString, this.locale);
      } else {
         v = o.toString();
      }

      int n;
      if (options != null && options[Interpreter.Option.WRAP.ordinal()] != null) {
         n = out.write(v, options[Interpreter.Option.WRAP.ordinal()]);
      } else {
         n = out.write(v);
      }

      return n;
   }

   protected int getExprStartChar(ST self) {
      Interval templateLocation = self.impl.sourceMap[this.current_ip];
      return templateLocation != null ? templateLocation.a : -1;
   }

   protected int getExprStopChar(ST self) {
      Interval templateLocation = self.impl.sourceMap[this.current_ip];
      return templateLocation != null ? templateLocation.b : -1;
   }

   protected void map(ST self, Object attr, final ST st) {
      this.rot_map(self, attr, new ArrayList() {
         {
            this.add(st);
         }
      });
   }

   protected void rot_map(ST self, Object attr, List prototypes) {
      if (attr == null) {
         this.operands[++this.sp] = null;
      } else {
         attr = this.convertAnythingIteratableToIterator(attr);
         if (attr instanceof Iterator) {
            List<ST> mapped = this.rot_map_iterator(self, (Iterator)attr, prototypes);
            this.operands[++this.sp] = mapped;
         } else {
            ST proto = (ST)prototypes.get(0);
            ST st = this.group.createStringTemplateInternally(proto);
            if (st != null) {
               this.setFirstArgument(self, st, attr);
               if (st.impl.isAnonSubtemplate) {
                  st.rawSetAttribute("i0", 0);
                  st.rawSetAttribute("i", 1);
               }

               this.operands[++this.sp] = st;
            } else {
               this.operands[++this.sp] = null;
            }
         }

      }
   }

   protected List rot_map_iterator(ST self, Iterator attr, List prototypes) {
      List<ST> mapped = new ArrayList();
      Iterator iter = attr;
      int i0 = 0;
      int i = 1;
      int ti = 0;

      while(iter.hasNext()) {
         Object iterValue = iter.next();
         if (iterValue == null) {
            mapped.add((Object)null);
         } else {
            int templateIndex = ti % prototypes.size();
            ++ti;
            ST proto = (ST)prototypes.get(templateIndex);
            ST st = this.group.createStringTemplateInternally(proto);
            this.setFirstArgument(self, st, iterValue);
            if (st.impl.isAnonSubtemplate) {
               st.rawSetAttribute("i0", i0);
               st.rawSetAttribute("i", i);
            }

            mapped.add(st);
            ++i0;
            ++i;
         }
      }

      return mapped;
   }

   protected ST.AttributeList zip_map(ST self, List exprs, ST prototype) {
      if (exprs != null && prototype != null && exprs.size() != 0) {
         for(int i = 0; i < exprs.size(); ++i) {
            Object attr = exprs.get(i);
            if (attr != null) {
               exprs.set(i, this.convertAnythingToIterator(attr));
            }
         }

         int numExprs = exprs.size();
         CompiledST code = prototype.impl;
         Map formalArguments = code.formalArguments;
         if (code.hasFormalArgs && formalArguments != null) {
            Object[] formalArgumentNames = formalArguments.keySet().toArray();
            int nformalArgs = formalArgumentNames.length;
            if (prototype.isAnonSubtemplate()) {
               nformalArgs -= predefinedAnonSubtemplateAttributes.size();
            }

            if (nformalArgs != numExprs) {
               this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.MAP_ARGUMENT_COUNT_MISMATCH, (Object)numExprs, nformalArgs);
               int shorterSize = Math.min(formalArgumentNames.length, numExprs);
               numExprs = shorterSize;
               Object[] newFormalArgumentNames = new Object[shorterSize];
               System.arraycopy(formalArgumentNames, 0, newFormalArgumentNames, 0, shorterSize);
               formalArgumentNames = newFormalArgumentNames;
            }

            ST.AttributeList results = new ST.AttributeList();
            int i = 0;

            while(true) {
               int numEmpty = 0;
               ST embedded = this.group.createStringTemplateInternally(prototype);
               embedded.rawSetAttribute("i0", i);
               embedded.rawSetAttribute("i", i + 1);

               for(int a = 0; a < numExprs; ++a) {
                  Iterator it = (Iterator)exprs.get(a);
                  if (it != null && it.hasNext()) {
                     String argName = (String)formalArgumentNames[a];
                     Object iteratedValue = it.next();
                     embedded.rawSetAttribute(argName, iteratedValue);
                  } else {
                     ++numEmpty;
                  }
               }

               if (numEmpty == numExprs) {
                  return results;
               }

               results.add(embedded);
               ++i;
            }
         } else {
            this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.MISSING_FORMAL_ARGUMENTS);
            return null;
         }
      } else {
         return null;
      }
   }

   protected void setFirstArgument(ST self, ST st, Object attr) {
      if (st.impl.formalArguments == null) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.ARGUMENT_COUNT_MISMATCH, 1, st.impl.name, 0);
      } else {
         st.locals[0] = attr;
      }
   }

   protected void addToList(List list, Object o) {
      o = this.convertAnythingIteratableToIterator(o);
      if (o instanceof Iterator) {
         Iterator it = (Iterator)o;

         while(it.hasNext()) {
            list.add(it.next());
         }
      } else {
         list.add(o);
      }

   }

   public Object first(Object v) {
      if (v == null) {
         return null;
      } else {
         Object r = v;
         v = this.convertAnythingIteratableToIterator(v);
         if (v instanceof Iterator) {
            Iterator it = (Iterator)v;
            if (it.hasNext()) {
               r = it.next();
            }
         }

         return r;
      }
   }

   public Object last(Object v) {
      if (v == null) {
         return null;
      } else if (v instanceof List) {
         return ((List)v).get(((List)v).size() - 1);
      } else if (v.getClass().isArray()) {
         Object[] elems = v;
         return elems[elems.length - 1];
      } else {
         Object last = v;
         v = this.convertAnythingIteratableToIterator(v);
         if (v instanceof Iterator) {
            for(Iterator it = (Iterator)v; it.hasNext(); last = it.next()) {
            }
         }

         return last;
      }
   }

   public Object rest(Object v) {
      if (v == null) {
         return null;
      } else if (v instanceof List) {
         List elems = (List)v;
         return elems.size() <= 1 ? null : elems.subList(1, elems.size());
      } else {
         v = this.convertAnythingIteratableToIterator(v);
         if (!(v instanceof Iterator)) {
            return null;
         } else {
            List a = new ArrayList();
            Iterator it = (Iterator)v;
            if (!it.hasNext()) {
               return null;
            } else {
               it.next();

               while(it.hasNext()) {
                  Object o = it.next();
                  a.add(o);
               }

               return a;
            }
         }
      }
   }

   public Object trunc(Object v) {
      if (v == null) {
         return null;
      } else if (v instanceof List) {
         List elems = (List)v;
         return elems.size() <= 1 ? null : elems.subList(0, elems.size() - 1);
      } else {
         v = this.convertAnythingIteratableToIterator(v);
         if (v instanceof Iterator) {
            List a = new ArrayList();
            Iterator it = (Iterator)v;

            while(it.hasNext()) {
               Object o = it.next();
               if (it.hasNext()) {
                  a.add(o);
               }
            }

            return a;
         } else {
            return null;
         }
      }
   }

   public Object strip(Object v) {
      if (v == null) {
         return null;
      } else {
         v = this.convertAnythingIteratableToIterator(v);
         if (v instanceof Iterator) {
            List a = new ArrayList();
            Iterator it = (Iterator)v;

            while(it.hasNext()) {
               Object o = it.next();
               if (o != null) {
                  a.add(o);
               }
            }

            return a;
         } else {
            return v;
         }
      }
   }

   public Object reverse(Object v) {
      if (v == null) {
         return null;
      } else {
         v = this.convertAnythingIteratableToIterator(v);
         if (!(v instanceof Iterator)) {
            return v;
         } else {
            List a = new LinkedList();
            Iterator it = (Iterator)v;

            while(it.hasNext()) {
               a.add(0, it.next());
            }

            return a;
         }
      }
   }

   public Object length(Object v) {
      if (v == null) {
         return 0;
      } else {
         int i = 1;
         if (v instanceof Map) {
            i = ((Map)v).size();
         } else if (v instanceof Collection) {
            i = ((Collection)v).size();
         } else if (v instanceof Object[]) {
            i = ((Object[])((Object[])v)).length;
         } else if (v instanceof int[]) {
            i = ((int[])((int[])v)).length;
         } else if (v instanceof long[]) {
            i = ((long[])((long[])v)).length;
         } else if (v instanceof float[]) {
            i = ((float[])((float[])v)).length;
         } else if (v instanceof double[]) {
            i = ((double[])((double[])v)).length;
         } else if (v instanceof Iterator) {
            Iterator it = (Iterator)v;

            for(i = 0; it.hasNext(); ++i) {
               it.next();
            }
         }

         return i;
      }
   }

   protected String toString(STWriter out, ST self, Object value) {
      if (value != null) {
         if (value.getClass() == String.class) {
            return (String)value;
         } else {
            StringWriter sw = new StringWriter();
            STWriter stw = null;

            try {
               Class writerClass = out.getClass();
               Constructor ctor = writerClass.getConstructor(Writer.class);
               stw = (STWriter)ctor.newInstance(sw);
            } catch (Exception var8) {
               stw = new AutoIndentWriter(sw);
               this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.WRITER_CTOR_ISSUE, out.getClass().getSimpleName());
            }

            this.writeObjectNoOptions(stw, self, value);
            return sw.toString();
         }
      } else {
         return null;
      }
   }

   public Object convertAnythingIteratableToIterator(Object o) {
      Iterator iter = null;
      if (o == null) {
         return null;
      } else {
         if (o instanceof Collection) {
            iter = ((Collection)o).iterator();
         } else if (o.getClass().isArray()) {
            iter = new ArrayIterator(o);
         } else if (this.currentScope.st.groupThatCreatedThisInstance.iterateAcrossValues && o instanceof Map) {
            iter = ((Map)o).values().iterator();
         } else if (o instanceof Map) {
            iter = ((Map)o).keySet().iterator();
         } else if (o instanceof Iterator) {
            iter = (Iterator)o;
         }

         return iter == null ? o : iter;
      }
   }

   public Iterator convertAnythingToIterator(Object o) {
      o = this.convertAnythingIteratableToIterator(o);
      if (o instanceof Iterator) {
         return (Iterator)o;
      } else {
         List singleton = new ST.AttributeList(1);
         singleton.add(o);
         return singleton.iterator();
      }
   }

   protected boolean testAttributeTrue(Object a) {
      if (a == null) {
         return false;
      } else if (a instanceof Boolean) {
         return (Boolean)a;
      } else if (a instanceof Collection) {
         return ((Collection)a).size() > 0;
      } else if (a instanceof Map) {
         return ((Map)a).size() > 0;
      } else {
         return a instanceof Iterator ? ((Iterator)a).hasNext() : true;
      }
   }

   protected Object getObjectProperty(STWriter out, ST self, Object o, Object property) {
      if (o == null) {
         this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_SUCH_PROPERTY, "null attribute");
         return null;
      } else {
         try {
            ModelAdaptor adap = self.groupThatCreatedThisInstance.getModelAdaptor(o.getClass());
            return adap.getProperty(this, self, o, property, this.toString(out, self, property));
         } catch (STNoSuchPropertyException e) {
            this.errMgr.runTimeError(this, self, this.current_ip, ErrorType.NO_SUCH_PROPERTY, (Throwable)e, o.getClass().getName() + "." + property);
            return null;
         }
      }
   }

   public Object getAttribute(ST self, String name) {
      for(InstanceScope scope = this.currentScope; scope != null; scope = scope.parent) {
         ST p = scope.st;
         FormalArgument localArg = null;
         if (p.impl.formalArguments != null) {
            localArg = (FormalArgument)p.impl.formalArguments.get(name);
         }

         if (localArg != null) {
            Object o = p.locals[localArg.index];
            return o;
         }
      }

      STGroup g = self.impl.nativeGroup;
      Object o = this.getDictionary(g, name);
      if (o != null) {
         return o;
      } else {
         if (ST.cachedNoSuchAttrException == null) {
            ST.cachedNoSuchAttrException = new STNoSuchAttributeException();
         }

         ST.cachedNoSuchAttrException.name = name;
         ST.cachedNoSuchAttrException.scope = this.currentScope;
         throw ST.cachedNoSuchAttrException;
      }
   }

   public Object getDictionary(STGroup g, String name) {
      if (g.isDictionary(name)) {
         return g.rawGetDictionary(name);
      } else {
         if (g.imports != null) {
            for(STGroup sup : g.imports) {
               Object o = this.getDictionary(sup, name);
               if (o != null) {
                  return o;
               }
            }
         }

         return null;
      }
   }

   public void setDefaultArguments(STWriter out, ST invokedST) {
      if (invokedST.impl.formalArguments != null && invokedST.impl.numberOfArgsWithDefaultValues != 0) {
         for(FormalArgument arg : invokedST.impl.formalArguments.values()) {
            if (invokedST.locals[arg.index] == ST.EMPTY_ATTR && arg.defaultValueToken != null) {
               if (arg.defaultValueToken.getType() == 10) {
                  CompiledST code = arg.compiledDefaultValue;
                  if (code == null) {
                     code = new CompiledST();
                  }

                  ST defaultArgST = this.group.createStringTemplateInternally(code);
                  defaultArgST.groupThatCreatedThisInstance = this.group;
                  String defArgTemplate = arg.defaultValueToken.getText();
                  if (defArgTemplate.startsWith("{" + this.group.delimiterStartChar + "(") && defArgTemplate.endsWith(")" + this.group.delimiterStopChar + "}")) {
                     invokedST.rawSetAttribute(arg.name, this.toString(out, invokedST, defaultArgST));
                  } else {
                     invokedST.rawSetAttribute(arg.name, defaultArgST);
                  }
               } else {
                  invokedST.rawSetAttribute(arg.name, arg.defaultValue);
               }
            }
         }

      }
   }

   private void popScope() {
      this.current_ip = this.currentScope.ret_ip;
      this.currentScope = this.currentScope.parent;
   }

   private void pushScope(ST self) {
      this.currentScope = new InstanceScope(this.currentScope, self);
      if (this.debug) {
         this.currentScope.events = new ArrayList();
         this.currentScope.childEvalTemplateEvents = new ArrayList();
      }

      this.currentScope.ret_ip = this.current_ip;
   }

   public static String getEnclosingInstanceStackString(InstanceScope scope) {
      List<ST> templates = getEnclosingInstanceStack(scope, true);
      StringBuilder buf = new StringBuilder();
      int i = 0;

      for(ST st : templates) {
         if (i > 0) {
            buf.append(" ");
         }

         buf.append(st.getName());
         ++i;
      }

      return buf.toString();
   }

   public static List getEnclosingInstanceStack(InstanceScope scope, boolean topdown) {
      List<ST> stack = new LinkedList();

      for(InstanceScope p = scope; p != null; p = p.parent) {
         if (topdown) {
            stack.add(0, p.st);
         } else {
            stack.add(p.st);
         }
      }

      return stack;
   }

   public static List getScopeStack(InstanceScope scope, boolean topdown) {
      List<InstanceScope> stack = new LinkedList();

      for(InstanceScope p = scope; p != null; p = p.parent) {
         if (topdown) {
            stack.add(0, p);
         } else {
            stack.add(p);
         }
      }

      return stack;
   }

   public static List getEvalTemplateEventStack(InstanceScope scope, boolean topdown) {
      List<EvalTemplateEvent> stack = new LinkedList();

      for(InstanceScope p = scope; p != null; p = p.parent) {
         EvalTemplateEvent eval = (EvalTemplateEvent)p.events.get(p.events.size() - 1);
         if (topdown) {
            stack.add(0, eval);
         } else {
            stack.add(eval);
         }
      }

      return stack;
   }

   protected void trace(ST self, int ip) {
      StringBuilder tr = new StringBuilder();
      BytecodeDisassembler dis = new BytecodeDisassembler(self.impl);
      StringBuilder buf = new StringBuilder();
      dis.disassembleInstruction(buf, ip);
      String name = self.impl.name + ":";
      if (self.impl.name == "anonymous") {
         name = "";
      }

      tr.append(String.format("%-40s", name + buf));
      tr.append("\tstack=[");

      for(int i = 0; i <= this.sp; ++i) {
         Object o = this.operands[i];
         this.printForTrace(tr, o);
      }

      tr.append(" ], calls=");
      tr.append(getEnclosingInstanceStackString(this.currentScope));
      tr.append(", sp=" + this.sp + ", nw=" + this.nwline);
      String s = tr.toString();
      if (this.debug) {
         this.executeTrace.add(s);
      }

      if (trace) {
         System.out.println(s);
      }

   }

   protected void printForTrace(StringBuilder tr, Object o) {
      if (o instanceof ST) {
         if (((ST)o).impl == null) {
            tr.append("bad-template()");
         } else {
            tr.append(" " + ((ST)o).impl.name + "()");
         }

      } else {
         o = this.convertAnythingIteratableToIterator(o);
         if (o instanceof Iterator) {
            Iterator it = (Iterator)o;
            tr.append(" [");

            while(it.hasNext()) {
               Object iterValue = it.next();
               this.printForTrace(tr, iterValue);
            }

            tr.append(" ]");
         } else {
            tr.append(" " + o);
         }

      }
   }

   public List getEvents() {
      return this.events;
   }

   protected void trackDebugEvent(ST self, InterpEvent e) {
      this.events.add(e);
      this.currentScope.events.add(e);
      if (e instanceof EvalTemplateEvent) {
         InstanceScope parent = this.currentScope.parent;
         if (parent != null) {
            this.currentScope.parent.childEvalTemplateEvents.add((EvalTemplateEvent)e);
         }
      }

   }

   public List getExecutionTrace() {
      return this.executeTrace;
   }

   public static int getShort(byte[] memory, int index) {
      int b1 = memory[index] & 255;
      int b2 = memory[index + 1] & 255;
      return b1 << 8 | b2;
   }

   public static enum Option {
      ANCHOR,
      FORMAT,
      NULL,
      SEPARATOR,
      WRAP;
   }
}
