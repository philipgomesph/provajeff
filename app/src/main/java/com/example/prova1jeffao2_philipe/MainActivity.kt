package com.example.prova1jeffao2_philipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import com.example.prova1jeffao2_philipe.databinding.ActivityMainBinding

// ViewBinding (Inflate)
private lateinit var viewBinding:ActivityMainBinding

//Dados Mocados
var cinemaMock = Cinema("CineJeffao","123",10200F,42)
var postoMock = PostoDeGasolina("Posto Breninho", "321",90500F,5000F)
var padariaMock = Padaria("Padaria da Manu","456",50400F,true)

//Array
private var listaEmpresa=ArrayList<Empresa>()

//List View
lateinit var lvEmpresas:ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        //setContentView(R.layout.activity_main)
        setContentView(view)

        //Adiciona Dados mocados
        listaEmpresa.add(cinemaMock)
        listaEmpresa.add(postoMock)
        listaEmpresa.add(padariaMock)


        lvEmpresas = viewBinding.lvEmpresa
        var arrayAdapterEmpresas = ArrayAdapter(this, android.R.layout.simple_list_item_1 , listaEmpresa)

        lvEmpresas.adapter = arrayAdapterEmpresas

        viewBinding.btCaixa.setOnClickListener{
            var caixaTotal=0F
            if (listaEmpresa.isEmpty()==true){
                Toast.makeText(this, "Nenhuma Empresa Cadastrada", Toast.LENGTH_SHORT).show()
            }else{
                listaEmpresa.forEach { 
                    caixaTotal += it.caixa
                    Toast.makeText(this, "Caixa Total: $caixaTotal", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewBinding.btInserir.setOnClickListener{
            if(viewBinding.etNome.isEnabled()==false){
                Toast.makeText(this, "Agora é possivel adicionar uma empresa", Toast.LENGTH_SHORT).show()
                viewBinding.etNome.setEnabled(true)
                viewBinding.etNome.text.clear()
                return@setOnClickListener
            }

            if(verificaCampoVazio()==true){
                Toast.makeText(this, "Existe Campos vazios, favor preencher", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Atributos
            var nome = viewBinding.etNome.text.toString()
            var cnpj = viewBinding.etCnpj.text.toString()
            var caixa = viewBinding.etCaixa.text.toString().toFloat()

            if(viewBinding.cbCinema.isChecked()==true){
                var numAcentos = viewBinding.etNumAcentos.text.toString().toInt()
                var cinema = Cinema(nome,cnpj,caixa,numAcentos)
                listaEmpresa.add(cinema)
                lvEmpresas.adapter = arrayAdapterEmpresas

                Toast.makeText(this, "Cinema adicionado: $cinema", Toast.LENGTH_SHORT).show()
                limpaTela()
            }

            if(viewBinding.cbPosto.isChecked()==true){
                var capTanque = viewBinding.etCapaciTanque.text.toString().toFloat()
                var posto = PostoDeGasolina(nome,cnpj,caixa,capTanque)

                listaEmpresa.add(posto)
                lvEmpresas.adapter = arrayAdapterEmpresas
                Toast.makeText(this, "Posto adicionado: $posto", Toast.LENGTH_SHORT).show()
                limpaTela()
            }

            if(viewBinding.cbPadaria.isChecked()==true){
                var vendeLight = viewBinding.cbVendeLight.isChecked()
                var padaria = Padaria(nome,cnpj,caixa,vendeLight)

                listaEmpresa.add(padaria)
                lvEmpresas.adapter = arrayAdapterEmpresas
                Toast.makeText(this, "Padaria adicionado: $padaria", Toast.LENGTH_SHORT).show()
                limpaTela()
                viewBinding.cbVendeLight.setChecked(false)

            }
        }

        viewBinding.lvEmpresa.onItemClickListener=AdapterView.OnItemClickListener{
            parent, view, position, id ->

            var pegaPosicao = listaEmpresa[position]
            viewBinding.etNome.setEnabled(false)

            viewBinding.etNome.setText(pegaPosicao.nome)
            viewBinding.etCnpj.text.clear()
            viewBinding.etCaixa.text.clear()

            listaEmpresa[position]

            if(pegaPosicao is Cinema){
                viewBinding.cbCinema.setChecked(true)
                viewBinding.cbPosto.setChecked(false)
                viewBinding.cbPadaria.setChecked(false)

                viewBinding.etNumAcentos.setVisibility(View.VISIBLE)
                viewBinding.etCapaciTanque.setVisibility(View.INVISIBLE)
                viewBinding.etCapaciTanque.text.clear()
                viewBinding.cbVendeLight.setVisibility(View.INVISIBLE)
                viewBinding.cbVendeLight.setChecked(false)
            }

            if(pegaPosicao is PostoDeGasolina){
                viewBinding.cbCinema.setChecked(false)
                viewBinding.cbPosto.setChecked(true)
                viewBinding.cbPadaria.setChecked(false)

                viewBinding.etNumAcentos.setVisibility(View.INVISIBLE)
                viewBinding.etNumAcentos.text.clear()
                viewBinding.etCapaciTanque.setVisibility(View.VISIBLE)
                viewBinding.cbVendeLight.setVisibility(View.INVISIBLE)
                viewBinding.cbVendeLight.setChecked(false)

            }

            if(pegaPosicao is Padaria){
                viewBinding.cbCinema.setChecked(false)
                viewBinding.cbPosto.setChecked(false)
                viewBinding.cbPadaria.setChecked(true)

                viewBinding.etNumAcentos.setVisibility(View.INVISIBLE)
                viewBinding.etNumAcentos.text.clear()
                viewBinding.etCapaciTanque.setVisibility(View.INVISIBLE)
                viewBinding.etCapaciTanque.text.clear()
                viewBinding.cbVendeLight.setVisibility(View.VISIBLE)
            }

            viewBinding.btRemover.setOnClickListener{

                if(listaEmpresa.isEmpty()){
                    Toast.makeText(this, "Nenhuma Empresa cadastrada", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                Toast.makeText(this, "Empresa removida: ${listaEmpresa[position]}", Toast.LENGTH_SHORT).show()
                listaEmpresa.removeAt(position)
                lvEmpresas.adapter=arrayAdapterEmpresas
                limpaTela()
            }

            viewBinding.btAlterar.setOnClickListener {
                if(verificaCampoVazio()==true){
                    Toast.makeText(this, "Existe Campos vazios, favor preencher", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                //Atributos
                var nome = viewBinding.etNome.text.toString()
                var cnpj = viewBinding.etCnpj.text.toString()
                var caixa = viewBinding.etCaixa.text.toString().toFloat()

                if(viewBinding.cbCinema.isChecked()==true){
                    var numAcentos = viewBinding.etNumAcentos.text.toString().toInt()
                    var cinema = Cinema(nome,cnpj,caixa,numAcentos)
                    listaEmpresa.add(position,cinema)
                    lvEmpresas.adapter = arrayAdapterEmpresas

                    Toast.makeText(this, "Cinema alterada: $cinema", Toast.LENGTH_SHORT).show()
                    limpaTela()
                }

                if(viewBinding.cbPosto.isChecked()==true){
                    var capTanque = viewBinding.etCapaciTanque.text.toString().toFloat()
                    var posto = PostoDeGasolina(nome,cnpj,caixa,capTanque)

                    listaEmpresa.add(position, posto)
                    lvEmpresas.adapter = arrayAdapterEmpresas
                    Toast.makeText(this, "Posto alterada: $posto", Toast.LENGTH_SHORT).show()
                    limpaTela()
                }

                if(viewBinding.cbPadaria.isChecked()==true){
                    var vendeLight = viewBinding.cbVendeLight.isChecked()
                    var padaria = Padaria(nome,cnpj,caixa,vendeLight)

                    listaEmpresa.add(position, padaria)
                    lvEmpresas.adapter = arrayAdapterEmpresas
                    Toast.makeText(this, "Padaria alterada: $padaria", Toast.LENGTH_SHORT).show()
                    limpaTela()
                    viewBinding.cbVendeLight.setChecked(false)

                }
                listaEmpresa.removeAt(position+1)
            }
        }

        //Logica Front
        viewBinding.cbCinema.setOnClickListener {
            if(viewBinding.cbCinema.isChecked()){
                viewBinding.etNumAcentos.setVisibility(View.VISIBLE)
                viewBinding.etCapaciTanque.setVisibility(View.INVISIBLE)
                viewBinding.etCapaciTanque.text.clear()
                viewBinding.cbVendeLight.setVisibility(View.INVISIBLE)
                viewBinding.cbVendeLight.setChecked(false)
            }
            viewBinding.cbPosto.setChecked(false)
            viewBinding.cbPadaria.setChecked(false)

        }

        viewBinding.cbPosto.setOnClickListener {
            if(viewBinding.cbPosto.isChecked()){
                viewBinding.etNumAcentos.setVisibility(View.INVISIBLE)
                viewBinding.etNumAcentos.text.clear()
                viewBinding.etCapaciTanque.setVisibility(View.VISIBLE)
                viewBinding.cbVendeLight.setVisibility(View.INVISIBLE)
                viewBinding.cbVendeLight.setChecked(false)

            }
            viewBinding.cbCinema.setChecked(false)
            viewBinding.cbPadaria.setChecked(false)
        }

        viewBinding.cbPadaria.setOnClickListener {
            if(viewBinding.cbPadaria.isChecked()){
                viewBinding.etNumAcentos.setVisibility(View.INVISIBLE)
                viewBinding.etNumAcentos.text.clear()
                viewBinding.etCapaciTanque.setVisibility(View.INVISIBLE)
                viewBinding.etCapaciTanque.text.clear()
                viewBinding.cbVendeLight.setVisibility(View.VISIBLE)
            }
            viewBinding.cbCinema.setChecked(false)
            viewBinding.cbPosto.setChecked(false)
        }

    }
}

private fun limpaTela(){
    viewBinding.etNome.text.clear()
    viewBinding.etCaixa.text.clear()
    viewBinding.etCnpj.text.clear()
    viewBinding.etNumAcentos.text.clear()
    viewBinding.etCapaciTanque.text.clear()

}


private fun verificaCampoVazio():Boolean{
    var verific=false

    if(viewBinding.etNome.text.isEmpty() ||
        viewBinding.etCaixa.text.isEmpty() ||
        viewBinding.etCnpj.text.isEmpty()
    ) verific=true
    return verific
}