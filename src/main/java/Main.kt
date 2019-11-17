import java.time.DayOfWeek

fun main(args: Array<String>) {
    val ordersAnalyzer = OrdersAnalyzer()
    var mapOfDaysOfWeekOrders: Map<DayOfWeek, Int> = ordersAnalyzer.totalDailySales(ordersAnalyzer.getOrdersList())

    ordersAnalyzer.printJsonFromMap(mapOfDaysOfWeekOrders)
    println()
    ordersAnalyzer.printFromMapWithDayOfWeekWordBefore(mapOfDaysOfWeekOrders)

}