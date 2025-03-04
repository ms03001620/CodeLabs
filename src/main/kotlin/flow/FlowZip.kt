package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


data class User(val id: Int, val name: String)
data class Order(val orderId: Int, val amount: Double)
data class UserWithOrder(
    val user: User,
    val order: List<Order>,
)

suspend fun getUserInfo(userId: Int): User {
    delay(1000)
    return User(id = userId, name = "user:$userId")
}

suspend fun getUserOrders(userId: Int): List<Order> {
    delay(2000)
    return listOf(
        Order(1, 99.99),
        Order(2, 50.99)
    )
}

fun fetchUserData(userId: Int): Flow<UserWithOrder> {
    val userFlow = flow {
        emit(getUserInfo(userId))
    }
    val ordersFlow = flow {
        emit(getUserOrders(userId))
    }

    return userFlow.zip(ordersFlow) { user, orders ->
        UserWithOrder(user, orders)
    }
}


fun main() = runBlocking {
    fetchUserData(1).collect {
        println("merge result: $it")
    }

}

