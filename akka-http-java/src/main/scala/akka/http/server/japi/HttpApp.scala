/*
 * Copyright (C) 2009-2014 Typesafe Inc. <http://www.typesafe.com>
 */

package akka.http.server.japi

import akka.actor.ActorSystem
import akka.http.Http.ServerBinding
import akka.stream.javadsl.MaterializedMap

/**
 * A convenience class to derive from to get everything from HttpService and Directives into scope.
 * Implement the [[HttpApp.createRoute]] method to provide the Route and then call [[HttpApp.bindRoute]]
 * to start the server on the specified interface.
 */
abstract class HttpApp
  extends AllDirectives
  with HttpServiceBase {
  protected def createRoute(): Route

  /**
   * Starts an HTTP server on the given interface and port. Creates the route by calling the
   * user-implemented [[createRoute]] method and uses the route to handle requests of the server.
   */
  def bindRoute(interface: String, port: Int, system: ActorSystem): MaterializedMap =
    bindRoute(interface, port, createRoute(), system)
}