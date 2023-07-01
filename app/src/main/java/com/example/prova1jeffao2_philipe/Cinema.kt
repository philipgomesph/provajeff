package com.example.prova1jeffao2_philipe

class Cinema (nome: String, cnpj: String, caixa: Float,numAcentos:Int) : Empresa(nome, cnpj, caixa) {

    var numAcentos:Int

    init {
        this.numAcentos=numAcentos
    }

    override fun toString(): String {
        return "Empresa:\n" +
                " nome='$nome',\n" +
                " cnpj='$cnpj',\n" +
                " caixa=$caixa\n" +
                "Tipo: Cinema\n" +
                "Numero Acentos: $numAcentos"
    }
}