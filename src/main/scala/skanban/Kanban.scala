package skanban

import org.scalajs.dom
import org.scalajs.dom._

object SKanban {
    val d = dom.document
    var srcLane = ""
    var destLane = ""
    def main(): Unit = {
        val x = window.localStorage
        val cols = List("Todo", "Doing", "Done")
        cols.foreach(col => {
            val outerCol = d.createElement("div")
            outerCol.className = "col-md-4"
            outerCol.innerHTML = s"<h3><span class='label label-default'>${col}</span></h3>"
            val innerCol = d.createElement("div")
            innerCol.id = col
            innerCol.className = "breadcrumb lane"
            innerCol.ondrop = (de: DragEvent) => {
                destLane = (de.target.asInstanceOf[HTMLDivElement]).id
                ColumnEventHandlers.onDropFn(de, srcLane, destLane)
            }
            innerCol.ondragover = (de: DragEvent) => ColumnEventHandlers.ondragover(de)
            // innerCol.innerHTML = (x(col)).toString
            outerCol.appendChild(innerCol)
            d.getElementById("board").appendChild(outerCol)
        })
    }

    def createCard() {
        val x = ((d.getElementById("cname").asInstanceOf[HTMLInputElement]).value,
            (d.getElementById("cdesc").asInstanceOf[HTMLInputElement]).value)
        val card = d.createElement("div")
        card.className = "breadcrumb"
        card.draggable = true
        card.id = x._1
        card.ondblclick = (e: MouseEvent) => e.target
        card.ondragstart = (e: DragEvent) => {
            val par = (e.target.asInstanceOf[HTMLDivElement]).id
            srcLane = document.getElementById(par).parentElement.id
            ColumnEventHandlers.dragFn(e)
        }
        val content = d.createElement("div")
        content.className = "caption"
        content.innerHTML = s"<p>${x._2}</p>"
        card.appendChild(content)
        d.getElementById("Todo").appendChild(card)
    }

}

object ColumnEventHandlers {

    def dragFn(de: DragEvent) = {
        val targetId = (de.target.asInstanceOf[HTMLElement]).id
        de.dataTransfer.setData("Text", targetId)
    }

    def onDropFn(de: DragEvent, slane: String, dlane: String) = {
        val id = de.dataTransfer.getData("Text")
        (de.target.asInstanceOf[HTMLDivElement]).appendChild(SKanban.d.getElementById(id))
        // import skanban.Conversion._
        SKanbanStorage.saveToStorage(slane, document.getElementById(slane).innerHTML)
        SKanbanStorage.saveToStorage(dlane, document.getElementById(dlane).innerHTML)
        document.getElementById(dlane).innerHTML
    }

    def ondragover(de: DragEvent) = {
        de.preventDefault()
        val targetEle = (de.target.asInstanceOf[HTMLDivElement])
        de.dataTransfer.setData("Text", targetEle.id)
    }

}