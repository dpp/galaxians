package code.snippet

/**
 * Created by IntelliJ IDEA.
 * User: dpp
 * Date: 10/14/11
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */

import net.liftweb._
import http._
import js._
import js.jquery._
import JsCmds._
import JqJsCmds._
import util._
import Helpers._

object Names extends SessionVar(
Vector("David", "Annette", "Archer", "Madeline",
"Elwood")
)

class DeleteButton {
  def render =
  SHtml.idMemoize {
    redrawer =>
    "tr" #> Names.get.zipWithIndex.map {
      case (name, pos) =>
      "@name *" #> name &
      "button [onclick]" #> SHtml.
        ajaxInvoke(() => {
        Names.atomicUpdate(v => v.take(pos) ++
        v.drop(pos + 1))
        redrawer.setHtml()
      })
    }
  }

}