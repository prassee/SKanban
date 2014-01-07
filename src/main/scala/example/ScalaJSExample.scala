package example

import scala.scalajs.js._
import scala.scalajs.js.Any._
import org.scalajs.dom.extensions._
import org.scalajs.dom
import org.scalajs.dom.WebSocket
import org.scalajs.dom.MessageEvent

object ScalaJSExample {

  val d = dom.document
  val target = d.getElementById("playground")

  def main(): Unit = {
    val p = d.createElement("p")
    p.innerHTML = "<strong>new content</storng>"
    target.appendChild(p)
  }

  def compute() = {
    val p = d.createElement("p")
    p.innerHTML = s"<strong>${2 * 2}</storng>"
    target.innerHTML = ""
    target.appendChild(p)
  }

}
