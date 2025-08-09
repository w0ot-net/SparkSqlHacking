package org.sparkproject.jetty.server.handler;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.AsyncContextEvent;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HttpChannelState;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.component.Graceful;
import org.sparkproject.jetty.util.statistic.CounterStatistic;
import org.sparkproject.jetty.util.statistic.SampleStatistic;

@ManagedObject("Request Statistics Gathering")
public class StatisticsHandler extends HandlerWrapper implements Graceful {
   private static final Logger LOG = LoggerFactory.getLogger(StatisticsHandler.class);
   private final AtomicLong _statsStartedAt = new AtomicLong();
   private final Graceful.Shutdown _shutdown = new Graceful.Shutdown(this) {
      public boolean isShutdownDone() {
         if (StatisticsHandler.this._gracefulShutdownWaitsForRequests) {
            return StatisticsHandler.this._requestStats.getCurrent() == 0L;
         } else {
            return StatisticsHandler.this._dispatchedStats.getCurrent() == 0L;
         }
      }
   };
   private final CounterStatistic _requestStats = new CounterStatistic();
   private final SampleStatistic _requestTimeStats = new SampleStatistic();
   private final CounterStatistic _dispatchedStats = new CounterStatistic();
   private final SampleStatistic _dispatchedTimeStats = new SampleStatistic();
   private final CounterStatistic _asyncWaitStats = new CounterStatistic();
   private final LongAdder _asyncDispatches = new LongAdder();
   private final LongAdder _expires = new LongAdder();
   private final LongAdder _errors = new LongAdder();
   private final LongAdder _responsesThrown = new LongAdder();
   private final LongAdder _responses1xx = new LongAdder();
   private final LongAdder _responses2xx = new LongAdder();
   private final LongAdder _responses3xx = new LongAdder();
   private final LongAdder _responses4xx = new LongAdder();
   private final LongAdder _responses5xx = new LongAdder();
   private final LongAdder _responsesTotalBytes = new LongAdder();
   private boolean _gracefulShutdownWaitsForRequests = true;
   private final AsyncListener _onCompletion = new AsyncListener() {
      public void onStartAsync(AsyncEvent event) {
         event.getAsyncContext().addListener(this);
      }

      public void onTimeout(AsyncEvent event) {
         StatisticsHandler.this._expires.increment();
      }

      public void onError(AsyncEvent event) {
         StatisticsHandler.this._errors.increment();
      }

      public void onComplete(AsyncEvent event) {
         Request request = ((AsyncContextEvent)event).getHttpChannelState().getBaseRequest();
         long elapsed = System.currentTimeMillis() - request.getTimeStamp();
         StatisticsHandler.this._requestStats.decrement();
         StatisticsHandler.this._requestTimeStats.record(elapsed);
         StatisticsHandler.this.updateResponse(request, false);
         StatisticsHandler.this._asyncWaitStats.decrement();
         if (StatisticsHandler.this._shutdown.isShutdown()) {
            StatisticsHandler.this._shutdown.check();
         }

      }
   };

   @ManagedOperation(
      value = "resets statistics",
      impact = "ACTION"
   )
   public void statsReset() {
      this._statsStartedAt.set(System.currentTimeMillis());
      this._requestStats.reset();
      this._requestTimeStats.reset();
      this._dispatchedStats.reset();
      this._dispatchedTimeStats.reset();
      this._asyncWaitStats.reset();
      this._asyncDispatches.reset();
      this._expires.reset();
      this._responses1xx.reset();
      this._responses2xx.reset();
      this._responses3xx.reset();
      this._responses4xx.reset();
      this._responses5xx.reset();
      this._responsesTotalBytes.reset();
   }

   public void handle(String path, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      Handler handler = this.getHandler();
      if (handler != null && this.isStarted() && !this.isShutdown()) {
         this._dispatchedStats.increment();
         HttpChannelState state = baseRequest.getHttpChannelState();
         long start;
         if (state.isInitial()) {
            this._requestStats.increment();
            start = baseRequest.getTimeStamp();
         } else {
            start = System.currentTimeMillis();
            this._asyncDispatches.increment();
         }

         boolean thrownError = false;
         boolean var21 = false;

         try {
            var21 = true;
            handler.handle(path, baseRequest, request, response);
            var21 = false;
         } catch (Throwable t) {
            thrownError = true;
            throw t;
         } finally {
            if (var21) {
               long now = System.currentTimeMillis();
               long dispatched = now - start;
               this._dispatchedStats.decrement();
               this._dispatchedTimeStats.record(dispatched);
               if (state.isInitial()) {
                  if (state.isAsyncStarted()) {
                     state.addListener(this._onCompletion);
                     this._asyncWaitStats.increment();
                  } else {
                     this._requestStats.decrement();
                     this._requestTimeStats.record(dispatched);
                     this.updateResponse(baseRequest, thrownError);
                  }
               }

               if (this._shutdown.isShutdown()) {
                  this._shutdown.check();
               }

            }
         }

         long now = System.currentTimeMillis();
         long dispatched = now - start;
         this._dispatchedStats.decrement();
         this._dispatchedTimeStats.record(dispatched);
         if (state.isInitial()) {
            if (state.isAsyncStarted()) {
               state.addListener(this._onCompletion);
               this._asyncWaitStats.increment();
            } else {
               this._requestStats.decrement();
               this._requestTimeStats.record(dispatched);
               this.updateResponse(baseRequest, thrownError);
            }
         }

         if (this._shutdown.isShutdown()) {
            this._shutdown.check();
         }

      } else {
         if (!baseRequest.getResponse().isCommitted()) {
            response.sendError(503);
         }

      }
   }

   protected void updateResponse(Request request, boolean thrownError) {
      Response response = request.getResponse();
      if (thrownError) {
         this._responsesThrown.increment();
      } else if (request.isHandled()) {
         switch (response.getStatus() / 100) {
            case 1:
               this._responses1xx.increment();
               break;
            case 2:
               this._responses2xx.increment();
               break;
            case 3:
               this._responses3xx.increment();
               break;
            case 4:
               this._responses4xx.increment();
               break;
            case 5:
               this._responses5xx.increment();
         }
      } else {
         this._responses4xx.increment();
      }

      this._responsesTotalBytes.add(response.getContentCount());
   }

   protected void doStart() throws Exception {
      if (this.getHandler() == null) {
         throw new IllegalStateException("StatisticsHandler has no Wrapped Handler");
      } else {
         this._shutdown.cancel();
         super.doStart();
         this.statsReset();
      }
   }

   protected void doStop() throws Exception {
      this._shutdown.cancel();
      super.doStop();
   }

   public void setGracefulShutdownWaitsForRequests(boolean gracefulShutdownWaitsForRequests) {
      this._gracefulShutdownWaitsForRequests = gracefulShutdownWaitsForRequests;
   }

   @ManagedAttribute("if graceful shutdown will wait for all requests")
   public boolean getGracefulShutdownWaitsForRequests() {
      return this._gracefulShutdownWaitsForRequests;
   }

   @ManagedAttribute("number of requests")
   public int getRequests() {
      return (int)this._requestStats.getTotal();
   }

   @ManagedAttribute("number of requests currently active")
   public int getRequestsActive() {
      return (int)this._requestStats.getCurrent();
   }

   @ManagedAttribute("maximum number of active requests")
   public int getRequestsActiveMax() {
      return (int)this._requestStats.getMax();
   }

   @ManagedAttribute("maximum time spend handling requests (in ms)")
   public long getRequestTimeMax() {
      return this._requestTimeStats.getMax();
   }

   @ManagedAttribute("total time spend in all request handling (in ms)")
   public long getRequestTimeTotal() {
      return this._requestTimeStats.getTotal();
   }

   @ManagedAttribute("mean time spent handling requests (in ms)")
   public double getRequestTimeMean() {
      return this._requestTimeStats.getMean();
   }

   @ManagedAttribute("standard deviation for request handling (in ms)")
   public double getRequestTimeStdDev() {
      return this._requestTimeStats.getStdDev();
   }

   @ManagedAttribute("number of dispatches")
   public int getDispatched() {
      return (int)this._dispatchedStats.getTotal();
   }

   @ManagedAttribute("number of dispatches currently active")
   public int getDispatchedActive() {
      return (int)this._dispatchedStats.getCurrent();
   }

   @ManagedAttribute("maximum number of active dispatches being handled")
   public int getDispatchedActiveMax() {
      return (int)this._dispatchedStats.getMax();
   }

   @ManagedAttribute("maximum time spend in dispatch handling")
   public long getDispatchedTimeMax() {
      return this._dispatchedTimeStats.getMax();
   }

   @ManagedAttribute("total time spent in dispatch handling (in ms)")
   public long getDispatchedTimeTotal() {
      return this._dispatchedTimeStats.getTotal();
   }

   @ManagedAttribute("mean time spent in dispatch handling (in ms)")
   public double getDispatchedTimeMean() {
      return this._dispatchedTimeStats.getMean();
   }

   @ManagedAttribute("standard deviation for dispatch handling (in ms)")
   public double getDispatchedTimeStdDev() {
      return this._dispatchedTimeStats.getStdDev();
   }

   @ManagedAttribute("total number of async requests")
   public int getAsyncRequests() {
      return (int)this._asyncWaitStats.getTotal();
   }

   @ManagedAttribute("currently waiting async requests")
   public int getAsyncRequestsWaiting() {
      return (int)this._asyncWaitStats.getCurrent();
   }

   @ManagedAttribute("maximum number of waiting async requests")
   public int getAsyncRequestsWaitingMax() {
      return (int)this._asyncWaitStats.getMax();
   }

   @ManagedAttribute("number of requested that have been asynchronously dispatched")
   public int getAsyncDispatches() {
      return this._asyncDispatches.intValue();
   }

   @ManagedAttribute("number of async requests requests that have expired")
   public int getExpires() {
      return this._expires.intValue();
   }

   @ManagedAttribute("number of async errors that occurred")
   public int getErrors() {
      return this._errors.intValue();
   }

   @ManagedAttribute("number of requests with 1xx response status")
   public int getResponses1xx() {
      return this._responses1xx.intValue();
   }

   @ManagedAttribute("number of requests with 2xx response status")
   public int getResponses2xx() {
      return this._responses2xx.intValue();
   }

   @ManagedAttribute("number of requests with 3xx response status")
   public int getResponses3xx() {
      return this._responses3xx.intValue();
   }

   @ManagedAttribute("number of requests with 4xx response status")
   public int getResponses4xx() {
      return this._responses4xx.intValue();
   }

   @ManagedAttribute("number of requests with 5xx response status")
   public int getResponses5xx() {
      return this._responses5xx.intValue();
   }

   @ManagedAttribute("number of requests that threw an exception during handling")
   public int getResponsesThrown() {
      return this._responsesThrown.intValue();
   }

   @ManagedAttribute("time in milliseconds stats have been collected for")
   public long getStatsOnMs() {
      return System.currentTimeMillis() - this._statsStartedAt.get();
   }

   @ManagedAttribute("total number of bytes across all responses")
   public long getResponsesBytesTotal() {
      return this._responsesTotalBytes.longValue();
   }

   public String toStatsHTML() {
      StringBuilder sb = new StringBuilder();
      sb.append("<h1>Statistics:</h1>\n");
      sb.append("Statistics gathering started ").append(this.getStatsOnMs()).append("ms ago").append("<br />\n");
      sb.append("<h2>Requests:</h2>\n");
      sb.append("Total requests: ").append(this.getRequests()).append("<br />\n");
      sb.append("Active requests: ").append(this.getRequestsActive()).append("<br />\n");
      sb.append("Max active requests: ").append(this.getRequestsActiveMax()).append("<br />\n");
      sb.append("Total requests time: ").append(this.getRequestTimeTotal()).append("<br />\n");
      sb.append("Mean request time: ").append(this.getRequestTimeMean()).append("<br />\n");
      sb.append("Max request time: ").append(this.getRequestTimeMax()).append("<br />\n");
      sb.append("Request time standard deviation: ").append(this.getRequestTimeStdDev()).append("<br />\n");
      sb.append("<h2>Dispatches:</h2>\n");
      sb.append("Total dispatched: ").append(this.getDispatched()).append("<br />\n");
      sb.append("Active dispatched: ").append(this.getDispatchedActive()).append("<br />\n");
      sb.append("Max active dispatched: ").append(this.getDispatchedActiveMax()).append("<br />\n");
      sb.append("Total dispatched time: ").append(this.getDispatchedTimeTotal()).append("<br />\n");
      sb.append("Mean dispatched time: ").append(this.getDispatchedTimeMean()).append("<br />\n");
      sb.append("Max dispatched time: ").append(this.getDispatchedTimeMax()).append("<br />\n");
      sb.append("Dispatched time standard deviation: ").append(this.getDispatchedTimeStdDev()).append("<br />\n");
      sb.append("Total requests suspended: ").append(this.getAsyncRequests()).append("<br />\n");
      sb.append("Total requests expired: ").append(this.getExpires()).append("<br />\n");
      sb.append("Total requests resumed: ").append(this.getAsyncDispatches()).append("<br />\n");
      sb.append("<h2>Responses:</h2>\n");
      sb.append("1xx responses: ").append(this.getResponses1xx()).append("<br />\n");
      sb.append("2xx responses: ").append(this.getResponses2xx()).append("<br />\n");
      sb.append("3xx responses: ").append(this.getResponses3xx()).append("<br />\n");
      sb.append("4xx responses: ").append(this.getResponses4xx()).append("<br />\n");
      sb.append("5xx responses: ").append(this.getResponses5xx()).append("<br />\n");
      sb.append("responses thrown: ").append(this.getResponsesThrown()).append("<br />\n");
      sb.append("Bytes sent total: ").append(this.getResponsesBytesTotal()).append("<br />\n");
      return sb.toString();
   }

   public CompletableFuture shutdown() {
      return this._shutdown.shutdown();
   }

   public boolean isShutdown() {
      return this._shutdown.isShutdown();
   }

   public String toString() {
      return String.format("%s@%x{%s,r=%d,d=%d}", this.getClass().getSimpleName(), this.hashCode(), this.getState(), this._requestStats.getCurrent(), this._dispatchedStats.getCurrent());
   }
}
