package com.example.vector_con_archivos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    val arreglo2 = intArrayOf (0,0,0,0,0,0,0,0,0,0)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            arreglo2.set(editText2.text.toString().toInt(),editText.text.toString().toInt())

        }

        button2.setOnClickListener {
            val arreglo: String = arreglo2.contentToString()
            Toast.makeText( this, arreglo, Toast.LENGTH_LONG)
                .show()


        }

        button5.setOnClickListener {
            var archivo = File(getExternalFilesDir("MyDirectory"), editText2.text.toString())
            try {
                val flujoDeSalida = FileOutputStream(archivo)
                var cadena=arreglo2.contentToString()
                cadena=cadena.replace("[","")
                cadena=cadena.replace("]","")
                flujoDeSalida.write(cadena.toByteArray())
                flujoDeSalida.close()
                AlertDialog.Builder(this).setMessage("Se creo el archivo correctamente").setTitle("COMPLETO").setPositiveButton("OK"){ i, d->}.show()
            } catch (error: IOException) {
            }


        }
        button6.setOnClickListener {
            var vectorAux : List<String> = emptyList<String>()
            val archivo = editText2.text.toString()
            var externo = File(getExternalFilesDir("memoria"), archivo)
            if (archivo != null && archivo.trim() != "") {
                var flujoDeEntrada = FileInputStream(externo)
                var inputStreamReader = InputStreamReader(flujoDeEntrada)
                val bufferedReader = BufferedReader(inputStreamReader)
                var data: String? = null

                while ({ data = bufferedReader.readLine(); data }() != null) {
                    vectorAux=data.toString().split(",")
                }
                (0..9).forEach { arreglo2[it]=vectorAux[it].toInt()}
                flujoDeEntrada.close()
            }


    }
    }

}
