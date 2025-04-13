fun main() {
println("=== Bienvenido al Buscaminas ===")

// Pedimos tamaño del tablero y número de minas
print("Introduce número de filas: ")
val filas = readLine()?.toIntOrNull() ?: return

print("Introduce número de columnas: ")
val columnas = readLine()?.toIntOrNull() ?: return

print("Introduce número de minas: ")
val minas = readLine()?.toIntOrNull() ?: return

val juego = Buscaminas()

try {
    juego.crearTablero(columnas, filas, minas)
    println("Tablero creado correctamente.\n")
} catch (e: IllegalArgumentException) {
    println("Error al crear el tablero: ${e.message}")
    return
}

// Bucle del juego
while (true) {
    mostrarTableroConsola(juego)

    println("Escribe una acción: (D = destapar, B = bandera, S = salir)")
    val accion = readLine()?.uppercase()

    if (accion == "S") {
        println("Saliendo del juego...")
        break
    }

    print("Introduce la fila: ")
    val fila = readLine()?.toIntOrNull() ?: continue
    print("Introduce la columna: ")
    val columna = readLine()?.toIntOrNull() ?: continue

    when (accion) {
        "D" -> juego.destapar(fila, columna)
        "B" -> juego.colocarBandera(fila, columna)
        else -> println("Acción no válida, intenta de nuevo.")
    }

    if (juego.estaFinalizado()) {
        println("¡Juego terminado!")
        mostrarTableroConsola(juego)
        break
    }
}}

fun mostrarTableroConsola(juego: Buscaminas) {
    println("Tablero actual:")

    for (fila in 0 until juego.yTablero) {
        for (columna in 0 until juego.xTablero) {
            val celda = juego.tablero[fila][columna]

            when {
                celda.tieneBandera -> print(" B ")
                !celda.estaDestapada -> print(" * ")
                celda.tieneMina -> print(" M ")
                else -> print(" ${celda.minasCerca} ")
            }
        }
        println()
    }
}