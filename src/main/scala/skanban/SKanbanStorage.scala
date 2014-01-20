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

    private def extractCards(cardNodes: HTMLCollection) = {
        val buffer = new StringBuffer
        val len = cardNodes.length.intValue
        console.log(s"cards length ${len}")
        for (item <- 0 to len - 1) {
            val j = (cardNodes(item).asInstanceOf[HTMLDivElement])
            buffer.append(j.id)
            buffer.append(":")
            buffer.append("{")
            buffer.append((j.children(0).asInstanceOf[HTMLDivElement]).innerHTML)
            buffer.append("}")
            buffer.append("\n")
        }
        console.log(buffer.toString)
        buffer.toString
    }

    private def toNodeList(id: String): NodeList =
        dom.document.getElementById(id).getElementsByClassName("caption")

}

object Conversion {
    import java.io.File
    implicit def laneNameToFile(lane: String): File = {
        val path = this.getClass.getResource(s"/default/${lane}.txt").getPath()
        new File(path)
    }
    import org.scalajs.dom
    implicit def toElement(id: String): HTMLElement = dom.document.getElementById(id)
}
