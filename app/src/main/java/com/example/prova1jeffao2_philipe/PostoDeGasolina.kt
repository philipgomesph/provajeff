package com.example.prova1jeffao2_philipe

class PostoDeGasolina (nome: String, cnpj: String, caixa: Float, capaciTanque:Float): Empresa(nome, cnpj, caixa) {

    var capaciTanque:Float

    init {
        this.capaciTanque=capaciTanque
    }

    override fun toString(): String {
        return "Empresa:\n" +
                " nome='$nome',\n" +
                " cnpj='$cnpj',\n" +
                " caixa=$caixa\n" +
                " Tipo: PostoDeGasolina\n" +
                " Tanque: $capaciTanque"
    }


}