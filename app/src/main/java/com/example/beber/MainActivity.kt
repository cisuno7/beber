package com.example.beber

import Model.CalcularIngestaodiaria
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import androidx.appcompat.app.AlertDialog

import java.text.NumberFormat
import java.util.*
import kotlin.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var edit_peso: EditText
    private lateinit var edit_idade: EditText
    private lateinit var btn_buttom: Button
    private lateinit var txt_resultado_ml: TextView
    private lateinit var ic_redefinir: ImageView
    private lateinit var calcularIngestaodiaria: CalcularIngestaodiaria
    private lateinit var btn_lembrete: Button
    private lateinit var btn_alarme: Button
    private lateinit var txt_hora: TextView
    private lateinit var txt_minutos: TextView
    private var Resultadoml = 0.0

    lateinit var timePickerDialog: TimePickerDialog

    lateinit var calendario: Calendar
    var horarioatual = 0
    var minutosfinais = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
        Iniciarcomponentes()
        calcularIngestaodiaria = CalcularIngestaodiaria()
        btn_buttom.setOnClickListener {
            if (edit_peso.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_peso, Toast.LENGTH_SHORT).show()

            } else if (edit_idade.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.toast_informe_idade, Toast.LENGTH_SHORT).show()
                // capturar o que o usuário vai escrever //
            } else {
                val peso = edit_peso.text.toString().toDouble()
                val idade = edit_idade.text.toString().toInt()
                calcularIngestaodiaria.Calculartotalml(peso, idade)
                Resultadoml = calcularIngestaodiaria.Resultadoml()
                val formatar = NumberFormat.getNumberInstance(Locale("pt", "BR"))
                formatar.isGroupingUsed = false

                txt_resultado_ml.text = formatar.format(Resultadoml).toString() + "" + "" + "ml"

            }
            ic_redefinir.setOnClickListener {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle(R.string.dialog_titulo)
                    .setTitle(R.string.dialog_desc)
                    .setPositiveButton("ok", { DialogInterface, i ->
                        edit_peso.setText("")
                        edit_idade.setText("")
                        txt_resultado_ml.text = ""
                    })
                alertDialog.setNegativeButton("cancelar", { DialogInterface, i ->

                })
                val dialog = alertDialog.create()
                dialog.show()
            }
            btn_lembrete.setOnClickListener {
                calendario = Calendar.getInstance()
                horarioatual = calendario.get(Calendar.HOUR_OF_DAY)
                minutosfinais = calendario.get(Calendar.MINUTE)
                timePickerDialog =
                    TimePickerDialog(this, { timePicker: TimePicker, hourofday: Int, minutes: Int ->
                        txt_hora.text = String.format("%02d", hourofday)
                        txt_minutos.text = String.format("%02d", hourofday, minutes)
                    }, horarioatual, minutosfinais, true)
                timePickerDialog.show()
            }
            btn_alarme.setOnClickListener {
                if (!txt_hora.toString().isEmpty()) {
                    val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                    intent.putExtra(AlarmClock.EXTRA_HOUR, txt_hora.text.toString().toInt())
                    intent.putExtra(AlarmClock.EXTRA_MINUTES, txt_minutos.text.toString().toInt())
                    intent.putExtra(AlarmClock.EXTRA_MESSAGE, getString(R.string.time_alarm))
                    startActivity(intent)
                    if (intent.resolveActivity(packageManager) != null) {



                    }
                }
            }

        }
    }




        private fun Iniciarcomponentes() {
            edit_peso = findViewById(R.id.edit_peso)
            edit_idade = findViewById(R.id.edit_idade)
            btn_buttom = findViewById(R.id.btn_buttom)
            txt_resultado_ml = findViewById(R.id.txt_resultado_ml)
            ic_redefinir = findViewById(R.id.ic_redefinir)
            btn_alarme = findViewById(R.id.bnt_alarme)
            btn_lembrete = findViewById(R.id.bt_definir_lembrete)
            txt_hora = findViewById(R.id.text_hora)
            txt_minutos = findViewById(R.id.txt_minutos)

        }
    }







