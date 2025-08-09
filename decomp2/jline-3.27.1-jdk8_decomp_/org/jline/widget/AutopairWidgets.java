package org.jline.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.Buffer;
import org.jline.reader.LineReader;
import org.jline.reader.Reference;

public class AutopairWidgets extends Widgets {
   private static final Map LBOUNDS = new HashMap();
   private static final Map RBOUNDS;
   private final Map pairs;
   private final Map defaultBindings;
   private boolean enabled;

   public AutopairWidgets(LineReader reader) {
      this(reader, false);
   }

   public AutopairWidgets(LineReader reader, boolean addCurlyBrackets) {
      super(reader);
      this.defaultBindings = new HashMap();
      this.pairs = new HashMap();
      this.pairs.put("`", "`");
      this.pairs.put("'", "'");
      this.pairs.put("\"", "\"");
      this.pairs.put("[", "]");
      this.pairs.put("(", ")");
      this.pairs.put(" ", " ");
      if (this.existsWidget("_autopair-insert")) {
         throw new IllegalStateException("AutopairWidgets already created!");
      } else {
         if (addCurlyBrackets) {
            this.pairs.put("{", "}");
         }

         this.addWidget("_autopair-insert", this::autopairInsert);
         this.addWidget("_autopair-close", this::autopairClose);
         this.addWidget("_autopair-backward-delete-char", this::autopairDelete);
         this.addWidget("autopair-toggle", this::toggleKeyBindings);
         KeyMap<Binding> map = this.getKeyMap();

         for(Map.Entry p : this.pairs.entrySet()) {
            this.defaultBindings.put((String)p.getKey(), (Binding)map.getBound((CharSequence)p.getKey()));
            if (!((String)p.getKey()).equals(p.getValue())) {
               this.defaultBindings.put((String)p.getValue(), (Binding)map.getBound((CharSequence)p.getValue()));
            }
         }

      }
   }

   public void enable() {
      if (!this.enabled) {
         this.toggle();
      }

   }

   public void disable() {
      if (this.enabled) {
         this.toggle();
      }

   }

   public boolean toggle() {
      boolean before = this.enabled;
      this.toggleKeyBindings();
      return !before;
   }

   public boolean autopairInsert() {
      if (this.pairs.containsKey(this.lastBinding())) {
         if (this.canSkip(this.lastBinding())) {
            this.callWidget("forward-char");
         } else if (this.canPair(this.lastBinding())) {
            this.callWidget("self-insert");
            this.putString((String)this.pairs.get(this.lastBinding()));
            this.callWidget("backward-char");
         } else {
            this.callWidget("self-insert");
         }
      } else {
         this.callWidget("self-insert");
      }

      return true;
   }

   public boolean autopairClose() {
      if (this.pairs.containsValue(this.lastBinding()) && this.currChar().equals(this.lastBinding())) {
         this.callWidget("forward-char");
      } else {
         this.callWidget("self-insert");
      }

      return true;
   }

   public boolean autopairDelete() {
      if (this.pairs.containsKey(this.prevChar()) && ((String)this.pairs.get(this.prevChar())).equals(this.currChar()) && this.canDelete(this.prevChar())) {
         this.callWidget("delete-char");
      }

      this.callWidget("backward-delete-char");
      return true;
   }

   public boolean toggleKeyBindings() {
      if (this.enabled) {
         this.defaultBindings();
      } else {
         this.customBindings();
      }

      return this.enabled;
   }

   private void customBindings() {
      boolean ttActive = this.tailtipEnabled();
      if (ttActive) {
         this.callWidget("tailtip-toggle");
      }

      KeyMap<Binding> map = this.getKeyMap();

      for(Map.Entry p : this.pairs.entrySet()) {
         map.bind(new Reference("_autopair-insert"), (CharSequence)((CharSequence)p.getKey()));
         if (!((String)p.getKey()).equals(p.getValue())) {
            map.bind(new Reference("_autopair-close"), (CharSequence)((CharSequence)p.getValue()));
         }
      }

      this.aliasWidget("_autopair-backward-delete-char", "backward-delete-char");
      if (ttActive) {
         this.callWidget("tailtip-toggle");
      }

      this.enabled = true;
   }

   private void defaultBindings() {
      KeyMap<Binding> map = this.getKeyMap();

      for(Map.Entry p : this.pairs.entrySet()) {
         map.bind((Binding)this.defaultBindings.get(p.getKey()), (CharSequence)((CharSequence)p.getKey()));
         if (!((String)p.getKey()).equals(p.getValue())) {
            map.bind((Binding)this.defaultBindings.get(p.getValue()), (CharSequence)((CharSequence)p.getValue()));
         }
      }

      this.aliasWidget(".backward-delete-char", "backward-delete-char");
      if (this.tailtipEnabled()) {
         this.callWidget("tailtip-toggle");
         this.callWidget("tailtip-toggle");
      }

      this.enabled = false;
   }

   private boolean tailtipEnabled() {
      return this.getWidget("accept-line").equals("_tailtip-accept-line");
   }

   private boolean canPair(String d) {
      if (this.balanced(d) && !this.nexToBoundary(d)) {
         return !d.equals(" ") || !this.prevChar().equals(" ") && !this.currChar().equals(" ");
      } else {
         return false;
      }
   }

   private boolean canSkip(String d) {
      return ((String)this.pairs.get(d)).equals(d) && d.charAt(0) != ' ' && this.currChar().equals(d) && this.balanced(d);
   }

   private boolean canDelete(String d) {
      return this.balanced(d);
   }

   private boolean balanced(String d) {
      boolean out = false;
      Buffer buf = this.buffer();
      String lbuf = buf.upToCursor();
      String rbuf = buf.substring(lbuf.length());
      String regx1 = ((String)this.pairs.get(d)).equals(d) ? d : "\\" + d;
      String regx2 = ((String)this.pairs.get(d)).equals(d) ? (String)this.pairs.get(d) : "\\" + (String)this.pairs.get(d);
      int llen = lbuf.length() - lbuf.replaceAll(regx1, "").length();
      int rlen = rbuf.length() - rbuf.replaceAll(regx2, "").length();
      if (llen == 0 && rlen == 0) {
         out = true;
      } else if (d.charAt(0) == ' ') {
         out = true;
      } else if (((String)this.pairs.get(d)).equals(d)) {
         if (llen == rlen || (llen + rlen) % 2 == 0) {
            out = true;
         }
      } else {
         int l2len = lbuf.length() - lbuf.replaceAll(regx2, "").length();
         int r2len = rbuf.length() - rbuf.replaceAll(regx1, "").length();
         int ltotal = llen - l2len;
         int rtotal = rlen - r2len;
         if (ltotal < 0) {
            ltotal = 0;
         }

         if (ltotal >= rtotal) {
            out = true;
         }
      }

      return out;
   }

   private boolean boundary(String lb, String rb) {
      return lb.length() > 0 && this.prevChar().matches(lb) || rb.length() > 0 && this.currChar().matches(rb);
   }

   private boolean nexToBoundary(String d) {
      List<String> bk = new ArrayList();
      bk.add("all");
      if (d.matches("['\"`]")) {
         bk.add("quotes");
      } else if (d.matches("[{\\[(<]")) {
         bk.add("braces");
      } else if (d.charAt(0) == ' ') {
         bk.add("spaces");
      }

      if (LBOUNDS.containsKey(d) && RBOUNDS.containsKey(d)) {
         bk.add(d);
      }

      for(String k : bk) {
         if (this.boundary((String)LBOUNDS.get(k), (String)RBOUNDS.get(k))) {
            return true;
         }
      }

      return false;
   }

   static {
      LBOUNDS.put("all", "[.:/\\!]");
      LBOUNDS.put("quotes", "[\\]})a-zA-Z0-9]");
      LBOUNDS.put("spaces", "[^{(\\[]");
      LBOUNDS.put("braces", "");
      LBOUNDS.put("`", "`");
      LBOUNDS.put("\"", "\"");
      LBOUNDS.put("'", "'");
      RBOUNDS = new HashMap();
      RBOUNDS.put("all", "[\\[{(<,.:?/%$!a-zA-Z0-9]");
      RBOUNDS.put("quotes", "[a-zA-Z0-9]");
      RBOUNDS.put("spaces", "[^\\]})]");
      RBOUNDS.put("braces", "");
      RBOUNDS.put("`", "");
      RBOUNDS.put("\"", "");
      RBOUNDS.put("'", "");
   }
}
