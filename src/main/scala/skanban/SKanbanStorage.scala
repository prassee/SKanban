package skanban

import org.scalajs.dom
import org.scalajs.dom._
import scala.scalajs.js

object SKanbanStorage {
  private val appstorage = window.localStorage

  def saveToStorage(key: String*) {
    key.foreach { card =>
      val f = dom.document.getElementById(card)
      if (f.children.length.intValue > 0) {
        appstorage.update(card, extractCards(f.children))
      }
    }
  }

  def logStorage {
    console.log(s"todo has ${appstorage("Todo")}")
    console.log(s"doing has ${appstorage("Doing")}")
    console.log(s"done has ${appstorage("Done")}")
  }

  private def extractCards(cardNodes: HTMLCollection) = {
    val buffer = new StringBuffer
    val len = cardNodes.length.intValue
    console.log(s"cards length ${len}")
    for (item <- 0 to len - 1) {
      val j = (cardNodes(item).asInstanceOf[HTMLDivElement])
      buffer.append((j.children(0).asInstanceOf[HTMLDivElement]).innerHTML)
      buffer.append("\n")
    }
    console.log(buffer.toString)
    buffer.toString
  }

  private def toNodeList(id: String): NodeList =
    dom.document.getElementById(id).getElementsByClassName("caption")

}

case class Task(name: String, desc: String)

object Conversion {
  implicit def toTask(name: (String, String)): Task = Task(name._1, name._2)
  implicit def fromTask(task: Task) = (task.name, task.desc)
  import java.io.File
  implicit def laneNameToFile(lane: String): File = {
    val path = this.getClass.getResource(s"/default/${lane}.txt").getPath()
    new File(path)
  }
  import org.scalajs.dom
  implicit def toElement(id: String): HTMLElement = dom.document.getElementById(id)
}
