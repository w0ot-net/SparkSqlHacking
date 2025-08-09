package jakarta.servlet;

public interface AsyncContext {
   String ASYNC_REQUEST_URI = "jakarta.servlet.async.request_uri";
   String ASYNC_CONTEXT_PATH = "jakarta.servlet.async.context_path";
   String ASYNC_MAPPING = "jakarta.servlet.async.mapping";
   String ASYNC_PATH_INFO = "jakarta.servlet.async.path_info";
   String ASYNC_SERVLET_PATH = "jakarta.servlet.async.servlet_path";
   String ASYNC_QUERY_STRING = "jakarta.servlet.async.query_string";

   ServletRequest getRequest();

   ServletResponse getResponse();

   boolean hasOriginalRequestAndResponse();

   void dispatch();

   void dispatch(String var1);

   void dispatch(ServletContext var1, String var2);

   void complete();

   void start(Runnable var1);

   void addListener(AsyncListener var1);

   void addListener(AsyncListener var1, ServletRequest var2, ServletResponse var3);

   AsyncListener createListener(Class var1) throws ServletException;

   void setTimeout(long var1);

   long getTimeout();
}
