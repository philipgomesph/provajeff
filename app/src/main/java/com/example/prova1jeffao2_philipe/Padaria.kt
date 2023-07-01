package com.example.prova1jeffao2_philipe

open class Padaria (nome: String, cnpj: String, caixa: Float, vendeProdLight:Boolean): Empresa(nome, cnpj, caixa) {

    var vendeProdLight:Boolean

    init {
        this.vendeProdLight=vendeProdLight
    }

    override fun toString(): String {
        return "Empresa:\n" +
                " nome='$nome',\n" +
                " cnpj='$cnpj',\n" +
                " caixa=$caixa\n" +
                " Tipo: Padaria\n" +
                " Vende ProdLight: $vendeProdLight"
    }


}