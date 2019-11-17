import java.math.BigDecimal
import java.time.DayOfWeek
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.util.*

class OrdersAnalyzer {
    data class Order(val orderId: Int, val creationDate: String, val orderLines: List<OrderLine>)
    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>) : Map<DayOfWeek, Int> {
        val mapOfDaysOfWeekOrders = mutableMapOf<DayOfWeek, Int>()
        for(order in orders) {
            var orderDayQuantity = 0
            for((index, orderLine) in  order.orderLines.withIndex()) {
                orderDayQuantity += orderLine.quantity
                var dayOfWeek: DayOfWeek
                if (index == order.orderLines.size - 1) {
                    dayOfWeek = JavaHelpFile.getDayOfWeek(order.creationDate)
                    var quantityCurrDayOfWeek: Int? = mapOfDaysOfWeekOrders.get(dayOfWeek)
                    if (quantityCurrDayOfWeek != null) {
                        mapOfDaysOfWeekOrders.set(dayOfWeek, quantityCurrDayOfWeek + orderDayQuantity)
                    }
                    else {
                        mapOfDaysOfWeekOrders.set(dayOfWeek, orderDayQuantity)
                    }
                }
            }
        }
        return mapOfDaysOfWeekOrders
    }

    fun printJsonFromMap(mapOfDaysOfWeekOrders : Map<DayOfWeek, Int>) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val jsonSortedByKeyMapOfDaysOfWeekOrders: String = gson.toJson(mapOfDaysOfWeekOrders.toSortedMap())
        println(jsonSortedByKeyMapOfDaysOfWeekOrders)
    }

    fun printFromMapWithDayOfWeekWordBefore(mapOfDaysOfWeekOrders : Map<DayOfWeek, Int>) {
        val mapSortedByKeyMapOfDaysOfWeekOrders: SortedMap<DayOfWeek, Int> = mapOfDaysOfWeekOrders.toSortedMap()
        println('{')
        var i = 0
        for((key, value) in mapSortedByKeyMapOfDaysOfWeekOrders) {
            if (i < mapSortedByKeyMapOfDaysOfWeekOrders.size - 1) {
                println("""  "DayOfWeek.$key" : $value,""")
                i++
            } else {
                println("""  "DayOfWeek.$key" : $value""")
            }
        }
        println('}')
    }


    fun getOrdersList() : List<Order> {
        val jsonList = """[
           {
               orderId: 554 ,
               creationDate: "2017-03-25T10:35:20" ,
               orderLines: [
               {productId: 9872 , name: "Pencil" , quantity: 3 , unitPrice: 3.00 }
               ]
           },
           {
               orderId: 555 ,
               creationDate: "2017-03-25T11:24:20" , 
               orderLines: [
               {productId: 9872 , name: "Pencil" , quantity: 2 , unitPrice: 3.00 },
               {productId: 1746 , name: "Eraser" , quantity: 1 , unitPrice: 1.00 }
               ]
           },
           {
               orderId: 453 ,
               creationDate: "2017-03-27T14:53:12" , 
               orderLines: [
               {productId: 5723 , name: "Pen" , quantity: 4 , unitPrice: 4.22 },
               {productId: 9872 , name: "Pencil" , quantity: 3 , unitPrice: 3.12 },
               {productId: 3433 , name: "Erasers Set" , quantity: 1 , unitPrice: 6.15 }
               ]
           },
           {
               orderId: 431 ,
               creationDate: "2017-03-20T12:15:02" , 
               orderLines: [
               {productId: 5723 , name: "Pen" , quantity: 7 , unitPrice: 4.22 },
               {productId: 3433 , name: "Erasers Set" , quantity: 2 , unitPrice: 6.15 }
               ]
           },
           {
               orderId: 690 ,
               creationDate: "2017-03-26T11:14:00" , 
               orderLines: [
               {productId: 9872 , name: "Pencil" , quantity: 4 , unitPrice: 3.12 },
               {productId: 4098 , name: "Marker" , quantity: 5 , unitPrice: 4.50 }
               ]
           }
        ]"""

        //val gson = GsonBuilder().setPrettyPrinting().create()
        val gson = Gson()
        //println("=== List from JSON ===")
        var ordersList: List<Order> = gson.fromJson(jsonList, object : TypeToken<List<Order>>() {}.type)
        //ordersList.forEach { println(it) }

        return ordersList
    }
}