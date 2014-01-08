package example

import scala.scalajs.js.String
import org.scalajs.dom
import org.scalajs.dom.ErrorEvent
import org.scalajs.dom.Event
import org.scalajs.dom.HTMLInputElement
import org.scalajs.dom.MessageEvent
import org.scalajs.dom.CloseEvent

/**
 *
 */
object ScalaJSExample {

  val d = dom.document
  val target = d.getElementById("playground")
  val ws = new dom.WebSocket("ws://127.0.0.1:9898")

  def main(): Unit = {
    val p = d.createElement("p")
    p.innerHTML = "<strong>new content</storng>"
    target.appendChild(p)
  }

  def populate(data: String) {
    val p = d.createElement("p")
    p.innerHTML = s"<strong>${data}</storng>"
    target.innerHTML = ""
    target.appendChild(p)
  }

  def initWS() {
    ws.onmessage = (x: MessageEvent) => populate(x.data.toString())
    ws.onopen = (x: Event) => {}
    ws.onerror = (x: ErrorEvent) => Console.println("some error has occured " + x.message)
    ws.onclose = (x: CloseEvent) => {}
  }

  def sendData() {
    val data = (d.getElementById("chat").asInstanceOf[HTMLInputElement]).value
    Console.println("data" + data)
    ws.send(data)
  }

}
