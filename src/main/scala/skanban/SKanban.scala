package skanban

import org.scalajs.dom
import org.scalajs.dom._

object SKanban {
    val d = dom.document
    var srcLane = ""
    var destLane = ""
    val cols = List("Todo", "Doing", "Done")
    val localstore = window.localStorage

    /**
     * *
     * this method plots all lanes in the board
     * and called in the startup of the app
     */
    def main(): Unit = {
        cols.foreach(col => {
            val panelDiv = d.createElement("div") 
            panelDiv.className = "col-md-4"
            
            val outerCol = d.createElement("div")
            outerCol.className = "panel panel-info"
            outerCol.innerHTML = s"<div class='panel-heading'><h3 class='panel-title'><b>${col}</b></h3></div>"
            val innerCol = d.createElement("div")
            innerCol.id = col
            innerCol.className = "panel-body lane"
            innerCol.ondrop = (de: DragEvent) => {
                destLane = (de.target.asInstanceOf[HTMLDivElement]).id
                ColumnEventHandlers.onDropFn(de, srcLane, destLane)
            }
            innerCol.ondragover = (de: DragEvent) => ColumnEventHandlers.ondragover(de)
            // innerCol.innerHTML = (x(col)).toString
            outerCol.appendChild(innerCol)
            panelDiv.appendChild(outerCol)
            d.getElementById("board").appendChild(panelDiv)
        })

        cols.foreach(col => restoreBoards(col))
    }

    def restoreBoards(lane: String) {
        val cards = localstore(lane).toString.split("\n")
        cards.foreach { card =>
            val cardDetail = card.split(":")
            addCardToBoard(cardDetail(0), cardDetail(1), lane)
            console.log(s"${lane} -> ${cardDetail(0)} , ${cardDetail(1)}")
        }
    }

    def addCardToBoard(title: String, desc: String, lane: String) {
        val card = d.createElement("div")
        card.className = "breadcrumb"
        card.draggable = true
        card.id = title
        card.innerHTML = title
        card.ondragstart = (e: DragEvent) => {
            val par = (e.target.asInstanceOf[HTMLDivElement]).id
            srcLane = document.getElementById(par).parentElement.id
            ColumnEventHandlers.dragFn(e)
        }
        val content = d.createElement("div")
        content.className = "caption hid"
        content.innerHTML = s"<p>${desc}</p>"
        card.ondblclick = (e: MouseEvent) => {
            alert(content.innerHTML)
            e.target
        }
        card.appendChild(content)
        d.getElementById(lane).appendChild(card)
    }

    def createCard() {
        val x = ((d.getElementById("cname").asInstanceOf[HTMLInputElement]).value,
            (d.getElementById("cdesc").asInstanceOf[HTMLInputElement]).value)
        addCardToBoard(x._1, x._2, "Todo")
        (d.getElementById("cname").asInstanceOf[HTMLInputElement]).value = ""
        (d.getElementById("cdesc").asInstanceOf[HTMLInputElement]).value = ""
    }

    def clearBoard {
        localstore.clear()
        cols.foreach { col =>
            d.getElementById(col).innerHTML = ""
        }
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
        SKanbanStorage.saveToStorage(slane, dlane)
        document.getElementById(dlane).innerHTML
    }

    def ondragover(de: DragEvent) = {
        de.preventDefault()
        val targetEle = (de.target.asInstanceOf[HTMLDivElement])
        de.dataTransfer.setData("Text", targetEle.id)
    }

}