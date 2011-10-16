package code.snippet

/**
 * Created by IntelliJ IDEA.
 * User: dpp
 * Date: 10/14/11
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */

import net.liftweb._
import util._
import Helpers._
import http._
import js._
import js.jquery._
import JsCmds._
import JqJsCmds._

case class Tweet(name: String, msg: String)

class ShowTweets {
  def render =
    "li" #> tweets.map{
      t =>
      val id = Helpers.nextFuncName
      "li [id]" #> id & "div *" #> (t.name+": "+t.msg) &
      "button [onclick]" #>
        AppendHtml(id,
          <div><hr/><b>This is the inert thing</b></div>).toJsCmd
    }

  def tweets = List(Tweet("dpp", "hi"),
    Tweet("tackers", "hello"))
}