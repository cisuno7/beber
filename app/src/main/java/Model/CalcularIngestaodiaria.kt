package Model

class CalcularIngestaodiaria {
    private val ml_joven = 40.0
    private val ml_adulto = 35.0
    private val ml_idoso = 30.0
    private val ml_mais_de66_anos = 25.0
    private var resultadoml = 0.0
    private var resultado_total = 0.0

    fun Calculartotalml(peso: Double, idade: Int) {
        if (idade <= 17) {
            resultadoml = peso * ml_joven
            resultado_total = resultadoml
        } else if (idade <= 55) {
            resultadoml = peso * ml_adulto
            resultado_total = resultadoml
        } else if (idade <= 65) {
            resultadoml = peso * ml_idoso
            resultadoml = resultado_total
        } else {
            resultadoml= peso*ml_mais_de66_anos
            resultadoml=resultado_total

        }

       }
    fun  Resultadoml():Double {
    return resultadoml
    }


}

