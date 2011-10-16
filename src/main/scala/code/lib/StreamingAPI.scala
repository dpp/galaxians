package code.lib

/**
 * Created by IntelliJ IDEA.
 * User: dpp
 * Date: 10/14/11
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */

import net.liftweb._
import http._
import rest._
import common._

class StreamingAPI extends RestHelper {
  serve {
    case "foo" :: "streaming" :: _ Get _ =>
      for {
        (info,len) <- getLength
      } yield
    StreamingResponse(info, () => {},
      len, Nil, Nil, 200)
  }

  def getLength: Box[({ def read(buf: Array[Byte]): Int }, Long)] =
    Empty
}