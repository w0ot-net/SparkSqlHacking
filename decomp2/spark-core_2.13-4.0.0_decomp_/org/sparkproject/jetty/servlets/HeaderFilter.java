package org.sparkproject.jetty.servlets;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.StringUtil;

public class HeaderFilter extends IncludeExcludeBasedFilter {
   private List _configuredHeaders = new ArrayList();
   private static final Logger LOG = LoggerFactory.getLogger(HeaderFilter.class);

   public void init(FilterConfig filterConfig) throws ServletException {
      super.init(filterConfig);
      String headerConfig = filterConfig.getInitParameter("headerConfig");
      if (headerConfig != null) {
         String[] configs = StringUtil.csvSplit(headerConfig);

         for(String config : configs) {
            this._configuredHeaders.add(this.parseHeaderConfiguration(config));
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug(this.toString());
      }

   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpServletResponse httpResponse = (HttpServletResponse)response;
      if (super.shouldFilter(httpRequest, httpResponse)) {
         for(ConfiguredHeader header : this._configuredHeaders) {
            if (header.isDate()) {
               long headerValue = System.currentTimeMillis() + header.getMsOffset();
               if (header.isAdd()) {
                  httpResponse.addDateHeader(header.getName(), headerValue);
               } else {
                  httpResponse.setDateHeader(header.getName(), headerValue);
               }
            } else if (header.isAdd()) {
               httpResponse.addHeader(header.getName(), header.getValue());
            } else {
               httpResponse.setHeader(header.getName(), header.getValue());
            }
         }
      }

      chain.doFilter(request, response);
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(super.toString()).append("\n");
      sb.append("configured headers:\n");

      for(ConfiguredHeader c : this._configuredHeaders) {
         sb.append(c).append("\n");
      }

      return sb.toString();
   }

   private ConfiguredHeader parseHeaderConfiguration(String config) {
      String[] configTokens = config.trim().split(" ", 2);
      String method = configTokens[0].trim();
      String header = configTokens[1];
      String[] headerTokens = header.trim().split(":", 2);
      String headerName = headerTokens[0].trim();
      String headerValue = headerTokens[1].trim();
      ConfiguredHeader configuredHeader = new ConfiguredHeader(headerName, headerValue, method.startsWith("add"), method.endsWith("Date"));
      return configuredHeader;
   }

   private static class ConfiguredHeader {
      private String _name;
      private String _value;
      private long _msOffset;
      private boolean _add;
      private boolean _date;

      public ConfiguredHeader(String name, String value, boolean add, boolean date) {
         this._name = name;
         this._value = value;
         this._add = add;
         this._date = date;
         if (this._date) {
            this._msOffset = Long.parseLong(this._value);
         }

      }

      public String getName() {
         return this._name;
      }

      public String getValue() {
         return this._value;
      }

      public boolean isAdd() {
         return this._add;
      }

      public boolean isDate() {
         return this._date;
      }

      public long getMsOffset() {
         return this._msOffset;
      }

      public String toString() {
         String var10000 = this._add ? "add" : "set";
         return var10000 + (this._date ? "Date" : "") + " " + this._name + ": " + this._value;
      }
   }
}
