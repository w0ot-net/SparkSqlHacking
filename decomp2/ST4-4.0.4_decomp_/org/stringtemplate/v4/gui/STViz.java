package org.stringtemplate.v4.gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.tree.TreePath;
import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.stringtemplate.v4.InstanceScope;
import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.STGroupString;
import org.stringtemplate.v4.debug.EvalTemplateEvent;
import org.stringtemplate.v4.debug.InterpEvent;
import org.stringtemplate.v4.misc.ErrorManager;
import org.stringtemplate.v4.misc.Interval;
import org.stringtemplate.v4.misc.Misc;
import org.stringtemplate.v4.misc.STMessage;
import org.stringtemplate.v4.misc.STRuntimeMessage;

public class STViz {
   public EvalTemplateEvent root;
   public InstanceScope currentScope;
   public List allEvents;
   public JTreeSTModel tmodel;
   public ErrorManager errMgr;
   public Interpreter interp;
   public String output;
   public List trace;
   public List errors;
   public STViewFrame viewFrame;

   public STViz(ErrorManager errMgr, EvalTemplateEvent root, String output, Interpreter interp, List trace, List errors) {
      this.errMgr = errMgr;
      this.currentScope = root.scope;
      this.output = output;
      this.interp = interp;
      this.allEvents = interp.getEvents();
      this.trace = trace;
      this.errors = errors;
   }

   public void open() {
      this.viewFrame = new STViewFrame();
      this.updateStack(this.currentScope, this.viewFrame);
      this.updateAttributes(this.currentScope, this.viewFrame);
      List<InterpEvent> events = this.currentScope.events;
      this.tmodel = new JTreeSTModel(this.interp, (EvalTemplateEvent)events.get(events.size() - 1));
      this.viewFrame.tree.setModel(this.tmodel);
      this.viewFrame.tree.addTreeSelectionListener(new TreeSelectionListener() {
         public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
            STViz.this.currentScope = ((JTreeSTModel.Wrapper)STViz.this.viewFrame.tree.getLastSelectedPathComponent()).event.scope;
            STViz.this.updateCurrentST(STViz.this.viewFrame);
         }
      });
      JTreeASTModel astModel = new JTreeASTModel(new CommonTreeAdaptor(), this.currentScope.st.impl.ast);
      this.viewFrame.ast.setModel(astModel);
      this.viewFrame.ast.addTreeSelectionListener(new TreeSelectionListener() {
         public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
            TreePath path = treeSelectionEvent.getNewLeadSelectionPath();
            if (path != null) {
               CommonTree node = (CommonTree)treeSelectionEvent.getNewLeadSelectionPath().getLastPathComponent();
               CommonToken a = (CommonToken)STViz.this.currentScope.st.impl.tokens.get(node.getTokenStartIndex());
               CommonToken b = (CommonToken)STViz.this.currentScope.st.impl.tokens.get(node.getTokenStopIndex());
               STViz.this.highlight(STViz.this.viewFrame.template, a.getStartIndex(), b.getStopIndex());
            }
         }
      });
      this.viewFrame.output.setText(this.output);
      this.viewFrame.template.setText(this.currentScope.st.impl.template);
      this.viewFrame.bytecode.setText(this.currentScope.st.impl.disasm());
      this.viewFrame.trace.setText(Misc.join(this.trace.iterator(), "\n"));
      CaretListener caretListenerLabel = new CaretListener() {
         public void caretUpdate(CaretEvent e) {
            int dot = e.getDot();
            InterpEvent de = STViz.this.findEventAtOutputLocation(STViz.this.allEvents, dot);
            if (de == null) {
               STViz.this.currentScope = STViz.this.tmodel.root.event.scope;
            } else {
               STViz.this.currentScope = de.scope;
            }

            List<EvalTemplateEvent> stack = Interpreter.getEvalTemplateEventStack(STViz.this.currentScope, true);
            Object[] path = new Object[stack.size()];
            int j = 0;

            for(EvalTemplateEvent s : stack) {
               path[j++] = new JTreeSTModel.Wrapper(s);
            }

            TreePath p = new TreePath(path);
            STViz.this.viewFrame.tree.setSelectionPath(p);
            STViz.this.viewFrame.tree.scrollPathToVisible(p);
            STViz.this.updateCurrentST(STViz.this.viewFrame);
         }
      };
      this.viewFrame.output.addCaretListener(caretListenerLabel);
      if (this.errors != null && this.errors.size() != 0) {
         DefaultListModel errorListModel = new DefaultListModel();

         for(STMessage msg : this.errors) {
            errorListModel.addElement(msg);
         }

         this.viewFrame.errorList.setModel(errorListModel);
      } else {
         this.viewFrame.errorScrollPane.setVisible(false);
      }

      this.viewFrame.errorList.addListSelectionListener(new ListSelectionListener() {
         public void valueChanged(ListSelectionEvent e) {
            int minIndex = STViz.this.viewFrame.errorList.getMinSelectionIndex();
            int maxIndex = STViz.this.viewFrame.errorList.getMaxSelectionIndex();

            int i;
            for(i = minIndex; i <= maxIndex && !STViz.this.viewFrame.errorList.isSelectedIndex(i); ++i) {
            }

            ListModel model = STViz.this.viewFrame.errorList.getModel();
            STMessage msg = (STMessage)model.getElementAt(i);
            if (msg instanceof STRuntimeMessage) {
               STRuntimeMessage rmsg = (STRuntimeMessage)msg;
               Interval I = rmsg.self.impl.sourceMap[rmsg.ip];
               STViz.this.currentScope = ((STRuntimeMessage)msg).scope;
               STViz.this.updateCurrentST(STViz.this.viewFrame);
               if (I != null) {
                  STViz.this.highlight(STViz.this.viewFrame.template, I.a, I.b);
               }
            }

         }
      });
      Border empty = BorderFactory.createEmptyBorder();
      this.viewFrame.treeContentSplitPane.setBorder(empty);
      this.viewFrame.outputTemplateSplitPane.setBorder(empty);
      this.viewFrame.templateBytecodeTraceTabPanel.setBorder(empty);
      this.viewFrame.treeAttributesSplitPane.setBorder(empty);
      this.viewFrame.treeContentSplitPane.setOneTouchExpandable(true);
      this.viewFrame.outputTemplateSplitPane.setOneTouchExpandable(true);
      this.viewFrame.treeContentSplitPane.setDividerSize(10);
      this.viewFrame.outputTemplateSplitPane.setDividerSize(8);
      this.viewFrame.treeContentSplitPane.setContinuousLayout(true);
      this.viewFrame.treeAttributesSplitPane.setContinuousLayout(true);
      this.viewFrame.outputTemplateSplitPane.setContinuousLayout(true);
      this.viewFrame.setDefaultCloseOperation(2);
      this.viewFrame.pack();
      this.viewFrame.setSize(900, 700);
      this.viewFrame.setVisible(true);
   }

   private void updateCurrentST(STViewFrame m) {
      this.updateStack(this.currentScope, m);
      this.updateAttributes(this.currentScope, m);
      m.bytecode.moveCaretPosition(0);
      m.bytecode.setText(this.currentScope.st.impl.disasm());
      m.template.moveCaretPosition(0);
      m.template.setText(this.currentScope.st.impl.template);
      JTreeASTModel astModel = new JTreeASTModel(new CommonTreeAdaptor(), this.currentScope.st.impl.ast);
      this.viewFrame.ast.setModel(astModel);
      List<InterpEvent> events = this.currentScope.events;
      EvalTemplateEvent e = (EvalTemplateEvent)events.get(events.size() - 1);
      this.highlight(m.output, e.outputStartChar, e.outputStopChar);

      try {
         m.output.scrollRectToVisible(m.output.modelToView(e.outputStartChar));
      } catch (BadLocationException ble) {
         this.currentScope.st.groupThatCreatedThisInstance.errMgr.internalError(this.currentScope.st, "bad location: char index " + e.outputStartChar, ble);
      }

      if (this.currentScope.st.isAnonSubtemplate()) {
         Interval r = this.currentScope.st.impl.getTemplateRange();
         this.highlight(m.template, r.a, r.b);
      }

   }

   protected void highlight(JTextComponent comp, int i, int j) {
      Highlighter highlighter = comp.getHighlighter();
      highlighter.removeAllHighlights();

      try {
         highlighter.addHighlight(i, j + 1, DefaultHighlighter.DefaultPainter);
      } catch (BadLocationException ble) {
         this.errMgr.internalError(this.tmodel.root.event.scope.st, "bad highlight location", ble);
      }

   }

   protected void updateAttributes(InstanceScope scope, STViewFrame m) {
      m.attributes.setModel(new JTreeScopeStackModel(scope));
      m.attributes.setRootVisible(false);
      m.attributes.setShowsRootHandles(true);
   }

   protected void updateStack(InstanceScope scope, STViewFrame m) {
      List<ST> stack = Interpreter.getEnclosingInstanceStack(scope, true);
      m.setTitle("STViz - [" + Misc.join(stack.iterator(), " ") + "]");
   }

   public InterpEvent findEventAtOutputLocation(List events, int charIndex) {
      for(InterpEvent e : events) {
         if (charIndex >= e.outputStartChar && charIndex <= e.outputStopChar) {
            return e;
         }
      }

      return null;
   }

   public static void main(String[] args) throws IOException {
      if (args.length > 0 && args[0].equals("1")) {
         test1();
      } else if (args.length > 0 && args[0].equals("2")) {
         test2();
      } else if (args.length > 0 && args[0].equals("3")) {
         test3();
      } else if (args.length > 0 && args[0].equals("4")) {
         test4();
      }

   }

   public static void test1() throws IOException {
      String templates = "method(type,name,locals,args,stats) ::= <<\npublic <type> <name>(<args:{a| int <a>}; separator=\", \">) {\n    <if(locals)>int locals[<locals>];<endif>\n    <stats;separator=\"\\n\">\n}\n>>\nassign(a,b) ::= \"<a> = <b>;\"\nreturn(x) ::= <<return <x>;>>\nparen(x) ::= \"(<x>)\"\n";
      String tmpdir = System.getProperty("java.io.tmpdir");
      writeFile(tmpdir, "t.stg", templates);
      STGroup group = new STGroupFile(tmpdir + "/" + "t.stg");
      ST st = group.getInstanceOf("method");
      st.impl.dump();
      st.add("type", "float");
      st.add("name", "foo");
      st.add("locals", 3);
      st.add("args", new String[]{"x", "y", "z"});
      ST s1 = group.getInstanceOf("assign");
      ST paren = group.getInstanceOf("paren");
      paren.add("x", "x");
      s1.add("a", paren);
      s1.add("b", "y");
      ST s2 = group.getInstanceOf("assign");
      s2.add("a", "y");
      s2.add("b", "z");
      ST s3 = group.getInstanceOf("return");
      s3.add("x", "3.14159");
      st.add("stats", s1);
      st.add("stats", s2);
      st.add("stats", s3);
      STViz viz = st.inspect();
      System.out.println(st.render());
   }

   public static void test2() throws IOException {
      String templates = "t1(q1=\"Some\\nText\") ::= <<\n<q1>\n>>\n\nt2(p1) ::= <<\n<p1>\n>>\n\nmain() ::= <<\nSTART-<t1()>-END\n\nSTART-<t2(p1=\"Some\\nText\")>-END\n>>\n";
      String tmpdir = System.getProperty("java.io.tmpdir");
      writeFile(tmpdir, "t.stg", templates);
      STGroup group = new STGroupFile(tmpdir + "/" + "t.stg");
      ST st = group.getInstanceOf("main");
      STViz viz = st.inspect();
   }

   public static void test3() throws IOException {
      String templates = "main() ::= <<\nFoo: <{bar};format=\"lower\">\n>>\n";
      String tmpdir = System.getProperty("java.io.tmpdir");
      writeFile(tmpdir, "t.stg", templates);
      STGroup group = new STGroupFile(tmpdir + "/" + "t.stg");
      ST st = group.getInstanceOf("main");
      st.inspect();
   }

   public static void test4() throws IOException {
      String templates = "main(t) ::= <<\nhi: <t>\n>>\nfoo(x,y={hi}) ::= \"<bar(x,y)>\"\nbar(x,y) ::= << <y> >>\nignore(m) ::= \"<m>\"\n";
      STGroup group = new STGroupString(templates);
      ST st = group.getInstanceOf("main");
      ST foo = group.getInstanceOf("foo");
      st.add("t", foo);
      ST ignore = group.getInstanceOf("ignore");
      ignore.add("m", foo);
      st.inspect();
      st.render();
   }

   public static void writeFile(String dir, String fileName, String content) {
      try {
         File f = new File(dir, fileName);
         if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
         }

         FileWriter w = new FileWriter(f);
         BufferedWriter bw = new BufferedWriter(w);
         bw.write(content);
         bw.close();
         w.close();
      } catch (IOException ioe) {
         System.err.println("can't write file");
         ioe.printStackTrace(System.err);
      }

   }
}
