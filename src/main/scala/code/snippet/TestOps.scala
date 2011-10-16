

package code {
package snippet {

import scala.xml.{NodeSeq, Text}
import net.liftweb._
import net.liftweb.http._
import net.liftweb.http.js.jquery.JqJsCmds._
import util._
import common._
import java.util.Date

import Helpers._
import java.io.File
import net.liftweb.http.js.JsCmds.{Alert,SetHtml}
import net.liftweb.http.js.JsCmd
import net.liftweb.common.Logger
import net.liftweb.mapper.IdPK
import net.liftweb.mapper.LongKeyedMapper
import net.liftweb.mapper.CreatedUpdated
import net.liftweb.mapper.LongKeyedMetaMapper
import net.liftweb.mapper.MappedString
import net.liftweb.mapper.OneToMany
import net.liftweb.mapper.OrderBy
import net.liftweb.mapper.Ascending
import net.liftweb.mapper.ManyToMany
import net.liftweb.mapper.CRUDify

object MyTestHolder extends RequestVar[MyTest](null)

class TestOps extends Logger {
  
	private var myOtherTest: MyTest = null
	
	def create = {
	  
	  MyTestHolder( MyTest.create )
	  
    "*" #> <table>
    {MyTestHolder.toForm(Full("Create"),x => {x.save;S.redirectTo("testlist")})}
    </table>
	}
	
  def list = {
    
    val test = MyTest.findAll.headOption.getOrElse(MyTest.create.name("fred"))
    
    "*" #> (Text(test.name) ++ Text(" ") ++ 
            SHtml.link("testedit", () => {MyTestHolder.set(test); myOtherTest = test}, Text("edit")) ++ Text(" ") ++
            SHtml.link("testlist", () => {S.error("delete not implemented")}, Text("delete")))
   }

  def edit = {
    println("MyOtherTest ******** "+myOtherTest)
    "*" #> <table>
    {MyTestHolder.toForm(Full("Update"),
        x => {x.save;S.redirectTo("testlist")})}
    </table>
  }
  
}

 class MyTest extends LongKeyedMapper[MyTest] 
    with IdPK {
  def getSingleton = MyTest 
  
  object name extends MappedString(this, 10) {}
  
 }
 
object MyTest 
  extends MyTest 
    with LongKeyedMetaMapper[MyTest] {
}

}}