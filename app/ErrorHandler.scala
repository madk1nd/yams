import javax.inject.Singleton
import play.api.http.HttpErrorHandler
import play.api.libs.json._
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
//      Status(statusCode)("A client error occurred: " + message)
      Ok(Json.obj(
        "method" -> request.method,
        "path" -> request.path,
        "rawQueryString" -> request.rawQueryString,
        "statusCode" -> statusCode,
        "message" -> message
      ))
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError("A server error occurred: " + exception.getMessage)
    )
  }

}