package exercicios

fun main() {
    val cinema = mutableListOf<MutableList<String>>()
    val numberComluns = mutableListOf<Int>()
    var income = 0


    println("Enter the number of rows: ")
    val numberOfRows = readln().toInt()

    println("Enter the number of seats in each row: ")
    val numberOfSeatsInRow = readln().toInt()

    gerarCinema(numberOfRows, numberOfSeatsInRow, cinema, numberComluns)

    while (cinema.isNotEmpty()) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")

        val opt = readln().toInt()

        println()

        when (opt) {
            1 -> imprimirAssentos(numberComluns, cinema)
            2 -> income += buyTicket(numberOfRows, numberOfSeatsInRow, cinema)
            3 -> getStatistics(cinema, income, numberOfRows, numberOfSeatsInRow)
            0 -> break
        }

        println()

    }


}

private fun buyTicket(
    numberOfRows: Int,
    numberOfSeatsInRow: Int,
    cinema: MutableList<MutableList<String>>,

    ): Int {
    var income = 0

    println()

    while (true) {
        println("Enter a row number:")
        val rowNumber = readln().toInt()

        println("Enter a seat number in that row:")
        val seatNumber = readln().toInt()

        try {

            if (cinema[rowNumber - 1][seatNumber - 1] == "B") {
                println("That ticket has already been purchased!\n")
            } else {

                cinema[rowNumber - 1][seatNumber - 1] = "B"

                var numberRows = 0

                if (numberOfRows % 2 == 1) numberRows = numberOfRows - 1

                if (numberOfRows * numberOfSeatsInRow < 60 || rowNumber <= numberRows / 2) {
                    income += 10
                    println("Ticket price: $10")
                } else {
                    income += 8
                    println("Ticket price: $8 ")
                }

                break
            }
        } catch (e: IndexOutOfBoundsException) {
            println("Wrong input!")
        }

        println("Income: $income")

    }

    return income

}

private fun imprimirAssentos(
    numberComluns: MutableList<Int>,
    cinema: MutableList<MutableList<String>>
) {
    var x = 1

    println("Cinema: ")
    print(" ")
    for (value in numberComluns) {
        print(" $value")
    }

    println()

    for (i in cinema) {
        print(x++)
        for (value in i) {
            print(" $value")
        }
        println()

    }
}

private fun gerarCinema(
    numberOfRows: Int,
    numberOfSeatsInRow: Int,
    cinema: MutableList<MutableList<String>>,
    numberComluns: MutableList<Int>
) {
    var x = 1

    for (i in 1..numberOfRows) {
        val array = mutableListOf<String>()

        for (j in 1..numberOfSeatsInRow) {
            array.add("S")
        }

        cinema += array
    }

    for (i in 1..numberOfSeatsInRow) {
        numberComluns.add(x++)
    }


}

private fun getStatistics(
    cinema: MutableList<MutableList<String>>,
    income: Int,
    numberOfRows: Int,
    numberOfSeatsInRow: Int
) {
//    Number of purchased tickets: 0
//    Percentage: 0.00%
//    Current income: $0
//    Total income: $360

    var numberOfPurchasedTickets = 0

    val totalIncome = getTotalIcome(numberOfRows, numberOfSeatsInRow)
    //val formatPercentage = "%.2f".format(percentage)

    for (assentos in cinema) {
        for (assInColumn in assentos) {
            if (assInColumn == "B") {
                numberOfPurchasedTickets++
            }
        }
    }

    val tst = (numberOfPurchasedTickets.toDouble() / (numberOfRows.toDouble() * numberOfSeatsInRow.toDouble())) * 100
    var percentage = "%.2f".format(tst)

    println("Number of purchased tickets: $numberOfPurchasedTickets")
    println("Percentage: $percentage %")
    println("Current income: $$income")
    println("Total income: $$totalIncome")
}

private fun getTotalIcome(numberOfRows: Int, numberOfSeatsInRow: Int): Int {

    var numberRows = 0
    var total: Int = 0

    if (numberOfRows % 2 == 1) {
        numberRows = numberOfRows - 1
    }

    if (numberOfRows * numberOfSeatsInRow < 60) {
        total += numberOfRows * numberOfSeatsInRow * 10
    } else {
        if (numberOfRows % 2 == 1) {
            total += (((numberOfRows - 1) / 2 * numberOfSeatsInRow) * 10) + (((numberOfRows + 1) / 2 * numberOfSeatsInRow) * 8)
        } else {
            total += ((numberOfRows / 2 * numberOfSeatsInRow) * 10) + ((numberOfRows / 2 * numberOfSeatsInRow) * 8)
        }

    }

    return total
}
