package com.example.prova1jeffao2_philipe

open class Empresa (nome:String, cnpj:String, caixa:Float){
    var nome:String
    var cnpj:String
    var caixa:Float

    init {
        this.nome=nome
        this.cnpj=cnpj
        this.caixa=caixa
    }

    override fun toString(): String {
        return "Empresa:\n" +
                " nome='$nome',\n" +
                " cnpj='$cnpj',\n" +
                " caixa=$caixa\n"
    }


}