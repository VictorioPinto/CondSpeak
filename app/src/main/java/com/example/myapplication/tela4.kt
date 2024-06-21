package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class tela4 : AppCompatActivity() {
    private lateinit var serviceImage: ImageView

    private lateinit var floorEditText: EditText
    private lateinit var apartmentEditText: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela4)


        serviceImage = findViewById(R.id.serviceImage)

        floorEditText = findViewById(R.id.floorEditText)
        apartmentEditText = findViewById(R.id.apartmentEditText)
        sendButton = findViewById(R.id.sendButton)

        // Configura clique do botao enviar
        sendButton.setOnClickListener {
            val floor = floorEditText.text.toString()
            val apartment = apartmentEditText.text.toString()

            if (floor.isEmpty() || apartment.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val serviceRequestedMessage = "Seu serviço foi solicitado.\nAndar: $floor\nApartamento: $apartment"
                Toast.makeText(this, serviceRequestedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }
}
